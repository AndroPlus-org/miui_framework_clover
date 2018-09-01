// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.animation.*;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.*;
import android.view.*;

// Referenced classes of package android.widget:
//            AbsListView, ImageView, TextView, SectionIndexer, 
//            HeaderViewListAdapter, ExpandableListConnector, ExpandableListView, ListView, 
//            Adapter

class FastScroller
{

    static boolean _2D_get0(FastScroller fastscroller)
    {
        return fastscroller.mShowingPrimary;
    }

    static boolean _2D_set0(FastScroller fastscroller, boolean flag)
    {
        fastscroller.mShowingPrimary = flag;
        return flag;
    }

    static void _2D_wrap0(FastScroller fastscroller, int i)
    {
        fastscroller.setState(i);
    }

    public FastScroller(AbsListView abslistview, int i)
    {
        boolean flag = true;
        super();
        mCurrentSection = -1;
        mScrollbarPosition = -1;
        mPendingDrag = -1L;
        mList = abslistview;
        mOldItemCount = abslistview.getCount();
        mOldChildCount = abslistview.getChildCount();
        Object obj = abslistview.getContext();
        mScaledTouchSlop = ViewConfiguration.get(((Context) (obj))).getScaledTouchSlop();
        mScrollBarStyle = abslistview.getScrollBarStyle();
        mScrollCompleted = true;
        mState = 1;
        if(((Context) (obj)).getApplicationInfo().targetSdkVersion < 11)
            flag = false;
        mMatchDragPosition = flag;
        mTrackImage = new ImageView(((Context) (obj)));
        mTrackImage.setScaleType(ImageView.ScaleType.FIT_XY);
        mThumbImage = new ImageView(((Context) (obj)));
        mThumbImage.setScaleType(ImageView.ScaleType.FIT_XY);
        mPreviewImage = new View(((Context) (obj)));
        mPreviewImage.setAlpha(0.0F);
        mPrimaryText = createPreviewTextView(((Context) (obj)));
        mSecondaryText = createPreviewTextView(((Context) (obj)));
        mMinimumTouchTarget = abslistview.getResources().getDimensionPixelSize(0x1050084);
        setStyle(i);
        obj = abslistview.getOverlay();
        mOverlay = ((ViewGroupOverlay) (obj));
        ((ViewGroupOverlay) (obj)).add(mTrackImage);
        ((ViewGroupOverlay) (obj)).add(mThumbImage);
        ((ViewGroupOverlay) (obj)).add(mPreviewImage);
        ((ViewGroupOverlay) (obj)).add(mPrimaryText);
        ((ViewGroupOverlay) (obj)).add(mSecondaryText);
        getSectionsFromIndexer();
        updateLongList(mOldChildCount, mOldItemCount);
        setScrollbarPosition(abslistview.getVerticalScrollbarPosition());
        postAutoHide();
    }

    private static Animator animateAlpha(View view, float f)
    {
        return ObjectAnimator.ofFloat(view, View.ALPHA, new float[] {
            f
        });
    }

    private static Animator animateBounds(View view, Rect rect)
    {
        return ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[] {
            PropertyValuesHolder.ofInt(LEFT, new int[] {
                rect.left
            }), PropertyValuesHolder.ofInt(TOP, new int[] {
                rect.top
            }), PropertyValuesHolder.ofInt(RIGHT, new int[] {
                rect.right
            }), PropertyValuesHolder.ofInt(BOTTOM, new int[] {
                rect.bottom
            })
        });
    }

    private static Animator animateScaleX(View view, float f)
    {
        return ObjectAnimator.ofFloat(view, View.SCALE_X, new float[] {
            f
        });
    }

    private void applyLayout(View view, Rect rect)
    {
        view.layout(rect.left, rect.top, rect.right, rect.bottom);
        int i;
        if(mLayoutFromRight)
            i = rect.right - rect.left;
        else
            i = 0;
        view.setPivotX(i);
    }

    private void beginDrag()
    {
        mPendingDrag = -1L;
        setState(2);
        if(mListAdapter == null && mList != null)
            getSectionsFromIndexer();
        if(mList != null)
        {
            mList.requestDisallowInterceptTouchEvent(true);
            mList.reportScrollStateChange(1);
        }
        cancelFling();
    }

    private void cancelFling()
    {
        MotionEvent motionevent = MotionEvent.obtain(0L, 0L, 3, 0.0F, 0.0F, 0);
        mList.onTouchEvent(motionevent);
        motionevent.recycle();
    }

    private void cancelPendingDrag()
    {
        mPendingDrag = -1L;
    }

    private TextView createPreviewTextView(Context context)
    {
        android.view.ViewGroup.LayoutParams layoutparams = new android.view.ViewGroup.LayoutParams(-2, -2);
        context = new TextView(context);
        context.setLayoutParams(layoutparams);
        context.setSingleLine(true);
        context.setEllipsize(android.text.TextUtils.TruncateAt.MIDDLE);
        context.setGravity(17);
        context.setAlpha(0.0F);
        context.setLayoutDirection(mList.getLayoutDirection());
        return context;
    }

    private float getPosFromItemCount(int i, int j, int k)
    {
        SectionIndexer sectionindexer = mSectionIndexer;
        if(sectionindexer == null || mListAdapter == null)
            getSectionsFromIndexer();
        if(j == 0 || k == 0)
            return 0.0F;
        int l;
        if(sectionindexer != null && mSections != null)
        {
            if(mSections.length > 0)
                l = 1;
            else
                l = 0;
        } else
        {
            l = 0;
        }
        if(!l || mMatchDragPosition ^ true)
            if(j == k)
                return 0.0F;
            else
                return (float)i / (float)(k - j);
        l = i - mHeaderCount;
        if(l < 0)
            return 0.0F;
        int i1 = k - mHeaderCount;
        View view = mList.getChildAt(0);
        float f;
        int j1;
        int k1;
        if(view == null || view.getHeight() == 0)
            f = 0.0F;
        else
            f = (float)(mList.getPaddingTop() - view.getTop()) / (float)view.getHeight();
        j1 = sectionindexer.getSectionForPosition(l);
        k = sectionindexer.getPositionForSection(j1);
        k1 = mSections.length;
        if(j1 < k1 - 1)
        {
            float f1;
            if(j1 + 1 < k1)
                i = sectionindexer.getPositionForSection(j1 + 1);
            else
                i = i1 - 1;
            i -= k;
        } else
        {
            i = i1 - k;
        }
        if(i == 0)
            f = 0.0F;
        else
            f = (((float)l + f) - (float)k) / (float)i;
        f1 = ((float)j1 + f) / (float)k1;
        f = f1;
        if(l > 0)
        {
            f = f1;
            if(l + j == i1)
            {
                view = mList.getChildAt(j - 1);
                j = mList.getPaddingBottom();
                if(mList.getClipToPadding())
                {
                    i = view.getHeight();
                    j = mList.getHeight() - j - view.getTop();
                } else
                {
                    i = view.getHeight() + j;
                    j = mList.getHeight() - view.getTop();
                }
                f = f1;
                if(j > 0)
                {
                    f = f1;
                    if(i > 0)
                        f = f1 + (1.0F - f1) * ((float)j / (float)i);
                }
            }
        }
        return f;
    }

    private float getPosFromMotionEvent(float f)
    {
        if(mThumbRange <= 0.0F)
            return 0.0F;
        else
            return MathUtils.constrain((f - mThumbOffset) / mThumbRange, 0.0F, 1.0F);
    }

    private void getSectionsFromIndexer()
    {
        mSectionIndexer = null;
        Adapter adapter = mList.getAdapter();
        Object obj = adapter;
        if(adapter instanceof HeaderViewListAdapter)
        {
            mHeaderCount = ((HeaderViewListAdapter)adapter).getHeadersCount();
            obj = ((HeaderViewListAdapter)adapter).getWrappedAdapter();
        }
        if(obj instanceof ExpandableListConnector)
        {
            ExpandableListAdapter expandablelistadapter = ((ExpandableListConnector)obj).getAdapter();
            if(expandablelistadapter instanceof SectionIndexer)
            {
                mSectionIndexer = (SectionIndexer)expandablelistadapter;
                mListAdapter = ((Adapter) (obj));
                mSections = mSectionIndexer.getSections();
            }
        } else
        if(obj instanceof SectionIndexer)
        {
            mListAdapter = ((Adapter) (obj));
            mSectionIndexer = (SectionIndexer)obj;
            mSections = mSectionIndexer.getSections();
        } else
        {
            mListAdapter = ((Adapter) (obj));
            mSections = null;
        }
    }

    private static transient Animator groupAnimatorOfFloat(Property property, float f, View aview[])
    {
        AnimatorSet animatorset = new AnimatorSet();
        android.animation.AnimatorSet.Builder builder = null;
        int i = aview.length - 1;
        while(i >= 0) 
        {
            ObjectAnimator objectanimator = ObjectAnimator.ofFloat(aview[i], property, new float[] {
                f
            });
            if(builder == null)
                builder = animatorset.play(objectanimator);
            else
                builder.with(objectanimator);
            i--;
        }
        return animatorset;
    }

    private boolean isPointInside(float f, float f1)
    {
        boolean flag;
        if(isPointInsideX(f))
        {
            if(mTrackDrawable == null)
                flag = isPointInsideY(f1);
            else
                flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private boolean isPointInsideX(float f)
    {
        boolean flag = true;
        boolean flag1 = true;
        float f1 = mThumbImage.getTranslationX();
        float f2 = mThumbImage.getLeft();
        float f3 = mThumbImage.getRight();
        f2 = (float)mMinimumTouchTarget - ((f3 + f1) - (f2 + f1));
        if(f2 <= 0.0F)
            f2 = 0.0F;
        if(mLayoutFromRight)
        {
            if(f < (float)mThumbImage.getLeft() - f2)
                flag1 = false;
            return flag1;
        }
        if(f <= (float)mThumbImage.getRight() + f2)
            flag1 = flag;
        else
            flag1 = false;
        return flag1;
    }

    private boolean isPointInsideY(float f)
    {
        boolean flag = false;
        float f1 = mThumbImage.getTranslationY();
        float f2 = (float)mThumbImage.getTop() + f1;
        float f3 = (float)mThumbImage.getBottom() + f1;
        f1 = (float)mMinimumTouchTarget - (f3 - f2);
        boolean flag1;
        if(f1 > 0.0F)
            f1 /= 2.0F;
        else
            f1 = 0.0F;
        flag1 = flag;
        if(f >= f2 - f1)
        {
            flag1 = flag;
            if(f <= f3 + f1)
                flag1 = true;
        }
        return flag1;
    }

    private void layoutThumb()
    {
        Rect rect = mTempBounds;
        measureViewToSide(mThumbImage, null, null, rect);
        applyLayout(mThumbImage, rect);
    }

    private void layoutTrack()
    {
        ImageView imageview = mTrackImage;
        ImageView imageview1 = mThumbImage;
        Rect rect = mContainerRect;
        int i = Math.max(0, rect.width());
        int j = Math.max(0, rect.height());
        imageview.measure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x80000000), android.view.View.MeasureSpec.makeSafeMeasureSpec(j, 0));
        int k;
        int l;
        if(mThumbPosition == 1)
        {
            i = rect.top;
            j = rect.bottom;
        } else
        {
            j = imageview1.getHeight() / 2;
            i = rect.top + j;
            j = rect.bottom - j;
        }
        k = imageview.getMeasuredWidth();
        l = imageview1.getLeft() + (imageview1.getWidth() - k) / 2;
        imageview.layout(l, i, l + k, j);
    }

    private void measureFloating(View view, Rect rect, Rect rect1)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        if(rect == null)
        {
            i = 0;
            j = 0;
            k = 0;
        } else
        {
            i = rect.left;
            j = rect.top;
            k = rect.right;
        }
        rect = mContainerRect;
        l = rect.width();
        i1 = Math.max(0, rect.height());
        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(Math.max(0, l - i - k), 0x80000000), android.view.View.MeasureSpec.makeSafeMeasureSpec(i1, 0));
        k = rect.height();
        i = view.getMeasuredWidth();
        k = k / 10 + j + rect.top;
        j = view.getMeasuredHeight();
        l = (l - i) / 2 + rect.left;
        rect1.set(l, k, l + i, k + j);
    }

    private void measurePreview(View view, Rect rect)
    {
        Rect rect1 = mTempMargins;
        rect1.left = mPreviewImage.getPaddingLeft();
        rect1.top = mPreviewImage.getPaddingTop();
        rect1.right = mPreviewImage.getPaddingRight();
        rect1.bottom = mPreviewImage.getPaddingBottom();
        if(mOverlayPosition == 0)
            measureFloating(view, rect1, rect);
        else
            measureViewToSide(view, mThumbImage, rect1, rect);
    }

    private void measureViewToSide(View view, View view1, Rect rect, Rect rect1)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        if(rect == null)
        {
            i = 0;
            j = 0;
            k = 0;
        } else
        {
            i = rect.left;
            j = rect.top;
            k = rect.right;
        }
        rect = mContainerRect;
        l = rect.width();
        if(view1 != null)
            if(mLayoutFromRight)
                l = view1.getLeft();
            else
                l -= view1.getRight();
        i1 = Math.max(0, rect.height());
        l = Math.max(0, l - i - k);
        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(l, 0x80000000), android.view.View.MeasureSpec.makeSafeMeasureSpec(i1, 0));
        i1 = Math.min(l, view.getMeasuredWidth());
        if(mLayoutFromRight)
        {
            if(view1 == null)
                l = rect.right;
            else
                l = view1.getLeft();
            l -= k;
            i = l - i1;
        } else
        {
            if(view1 == null)
                l = rect.left;
            else
                l = view1.getRight();
            i = l + i;
            l = i + i1;
        }
        rect1.set(i, j, l, j + view.getMeasuredHeight());
    }

    private void onStateDependencyChanged(boolean flag)
    {
        if(!isEnabled()) goto _L2; else goto _L1
_L1:
        if(!isAlwaysShowEnabled()) goto _L4; else goto _L3
_L3:
        setState(1);
_L6:
        mList.resolvePadding();
        return;
_L4:
        if(mState == 1)
            postAutoHide();
        else
        if(flag)
        {
            setState(1);
            postAutoHide();
        }
        continue; /* Loop/switch isn't completed */
_L2:
        stop();
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void postAutoHide()
    {
        mList.removeCallbacks(mDeferHide);
        mList.postDelayed(mDeferHide, 1500L);
    }

    private void refreshDrawablePressedState()
    {
        boolean flag;
        if(mState == 2)
            flag = true;
        else
            flag = false;
        mThumbImage.setPressed(flag);
        mTrackImage.setPressed(flag);
    }

    private void scrollTo(float f)
    {
        int i;
        int j;
        int k;
        int l;
        int k1;
        int l1;
        int i2;
        int k2;
        int l2;
        int i3;
        mScrollCompleted = false;
        i = mList.getCount();
        Object aobj[] = mSections;
        int i1;
        int j1;
        int j2;
        if(aobj == null)
            j = 0;
        else
            j = aobj.length;
        if(aobj == null || j <= 1)
            break MISSING_BLOCK_LABEL_476;
        k = MathUtils.constrain((int)((float)j * f), 0, j - 1);
        l = k;
        i1 = mSectionIndexer.getPositionForSection(k);
        j1 = k;
        k1 = i;
        l1 = i1;
        i2 = k;
        j2 = k + 1;
        if(k < j - 1)
            k1 = mSectionIndexer.getPositionForSection(k + 1);
        k2 = l1;
        l2 = i2;
        i3 = j1;
        if(k1 != i1) goto _L2; else goto _L1
_L1:
        k2 = l1;
_L4:
        l2 = i2;
        i3 = j1;
        if(l > 0)
        {
            i3 = l - 1;
            l1 = mSectionIndexer.getPositionForSection(i3);
            if(l1 != i1)
            {
                l2 = i3;
                k2 = l1;
            } else
            {
                k2 = l1;
                l = i3;
                if(i3 != 0)
                    continue; /* Loop/switch isn't completed */
                i3 = 0;
                k2 = l1;
                l2 = i2;
            }
        }
_L2:
        i2 = j2 + 1;
        for(l1 = j2; i2 < j && mSectionIndexer.getPositionForSection(i2) == k1; l1++)
            i2++;

        break; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        float f1 = (float)l2 / (float)j;
        float f2 = (float)l1 / (float)j;
        float f3;
        if(i == 0)
            f3 = 3.402823E+038F;
        else
            f3 = 0.125F / (float)i;
        if(l2 != k || f - f1 >= f3)
            k2 += (int)(((float)(k1 - k2) * (f - f1)) / (f2 - f1));
        k2 = MathUtils.constrain(k2, 0, i - 1);
        if(mList instanceof ExpandableListView)
        {
            ExpandableListView expandablelistview = (ExpandableListView)mList;
            expandablelistview.setSelectionFromTop(expandablelistview.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(mHeaderCount + k2)), 0);
        } else
        if(mList instanceof ListView)
            ((ListView)mList).setSelectionFromTop(mHeaderCount + k2, 0);
        else
            mList.setSelection(mHeaderCount + k2);
_L5:
        if(mCurrentSection != i3)
        {
            mCurrentSection = i3;
            boolean flag = transitionPreviewLayout(i3);
            ExpandableListView expandablelistview1;
            if(!mShowingPreview && flag)
                transitionToDragging();
            else
            if(mShowingPreview && flag ^ true)
                transitionToVisible();
        }
        return;
        i3 = MathUtils.constrain((int)((float)i * f), 0, i - 1);
        if(mList instanceof ExpandableListView)
        {
            expandablelistview1 = (ExpandableListView)mList;
            expandablelistview1.setSelectionFromTop(expandablelistview1.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(mHeaderCount + i3)), 0);
        } else
        if(mList instanceof ListView)
            ((ListView)mList).setSelectionFromTop(mHeaderCount + i3, 0);
        else
            mList.setSelection(mHeaderCount + i3);
        i3 = -1;
          goto _L5
    }

    private void setState(int i)
    {
        int j;
        mList.removeCallbacks(mDeferHide);
        j = i;
        if(mAlwaysShow)
        {
            j = i;
            if(i == 0)
                j = 1;
        }
        if(j == mState)
            return;
        j;
        JVM INSTR tableswitch 0 2: default 64
    //                   0 74
    //                   1 81
    //                   2 88;
           goto _L1 _L2 _L3 _L4
_L1:
        mState = j;
        refreshDrawablePressedState();
        return;
_L2:
        transitionToHidden();
        continue; /* Loop/switch isn't completed */
_L3:
        transitionToVisible();
        continue; /* Loop/switch isn't completed */
_L4:
        if(transitionPreviewLayout(mCurrentSection))
            transitionToDragging();
        else
            transitionToVisible();
        if(true) goto _L1; else goto _L5
_L5:
    }

    private void setThumbPos(float f)
    {
        View view;
        float f1;
        f = mThumbRange * f + mThumbOffset;
        mThumbImage.setTranslationY(f - (float)mThumbImage.getHeight() / 2.0F);
        view = mPreviewImage;
        f1 = (float)view.getHeight() / 2.0F;
        mOverlayPosition;
        JVM INSTR tableswitch 1 2: default 72
    //                   1 133
    //                   2 136;
           goto _L1 _L2 _L3
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        f = 0.0F;
_L5:
        Rect rect = mContainerRect;
        int i = rect.top;
        int j = rect.bottom;
        f = MathUtils.constrain(f, (float)i + f1, (float)j - f1) - f1;
        view.setTranslationY(f);
        mPrimaryText.setTranslationY(f);
        mSecondaryText.setTranslationY(f);
        return;
_L3:
        f -= f1;
        if(true) goto _L5; else goto _L4
_L4:
    }

    private void startPendingDrag()
    {
        mPendingDrag = SystemClock.uptimeMillis() + TAP_TIMEOUT;
    }

    private boolean transitionPreviewLayout(int i)
    {
        Object aobj[] = mSections;
        TextView textview1 = null;
        String s = textview1;
        if(aobj != null)
        {
            s = textview1;
            if(i >= 0)
            {
                s = textview1;
                if(i < aobj.length)
                {
                    Object obj = aobj[i];
                    s = textview1;
                    if(obj != null)
                        s = obj.toString();
                }
            }
        }
        Object obj1 = mTempBounds;
        View view = mPreviewImage;
        TextView textview;
        Object obj2;
        Animator animator;
        int j;
        if(mShowingPrimary)
        {
            textview1 = mPrimaryText;
            textview = mSecondaryText;
        } else
        {
            textview1 = mSecondaryText;
            textview = mPrimaryText;
        }
        textview.setText(s);
        measurePreview(textview, ((Rect) (obj1)));
        applyLayout(textview, ((Rect) (obj1)));
        if(mPreviewAnimation != null)
            mPreviewAnimation.cancel();
        obj2 = animateAlpha(textview, 1.0F).setDuration(50L);
        animator = animateAlpha(textview1, 0.0F).setDuration(50L);
        animator.addListener(mSwitchPrimaryListener);
        obj1.left = ((Rect) (obj1)).left - view.getPaddingLeft();
        obj1.top = ((Rect) (obj1)).top - view.getPaddingTop();
        obj1.right = ((Rect) (obj1)).right + view.getPaddingRight();
        obj1.bottom = ((Rect) (obj1)).bottom + view.getPaddingBottom();
        obj1 = animateBounds(view, ((Rect) (obj1)));
        ((Animator) (obj1)).setDuration(100L);
        mPreviewAnimation = new AnimatorSet();
        obj2 = mPreviewAnimation.play(animator).with(((Animator) (obj2)));
        ((android.animation.AnimatorSet.Builder) (obj2)).with(((Animator) (obj1)));
        j = view.getWidth() - view.getPaddingLeft() - view.getPaddingRight();
        i = textview.getWidth();
        if(i > j)
        {
            textview.setScaleX((float)j / (float)i);
            ((android.animation.AnimatorSet.Builder) (obj2)).with(animateScaleX(textview, 1.0F).setDuration(100L));
        } else
        {
            textview.setScaleX(1.0F);
        }
        j = textview1.getWidth();
        if(j > i)
            ((android.animation.AnimatorSet.Builder) (obj2)).with(animateScaleX(textview1, (float)i / (float)j).setDuration(100L));
        mPreviewAnimation.start();
        return TextUtils.isEmpty(s) ^ true;
    }

    private void transitionToDragging()
    {
        if(mDecorAnimation != null)
            mDecorAnimation.cancel();
        Animator animator = groupAnimatorOfFloat(View.ALPHA, 1.0F, new View[] {
            mThumbImage, mTrackImage, mPreviewImage
        }).setDuration(150L);
        Animator animator1 = groupAnimatorOfFloat(View.TRANSLATION_X, 0.0F, new View[] {
            mThumbImage, mTrackImage
        }).setDuration(150L);
        mDecorAnimation = new AnimatorSet();
        mDecorAnimation.playTogether(new Animator[] {
            animator, animator1
        });
        mDecorAnimation.start();
        mShowingPreview = true;
    }

    private void transitionToHidden()
    {
        if(mDecorAnimation != null)
            mDecorAnimation.cancel();
        Animator animator = groupAnimatorOfFloat(View.ALPHA, 0.0F, new View[] {
            mThumbImage, mTrackImage, mPreviewImage, mPrimaryText, mSecondaryText
        }).setDuration(300L);
        int i;
        float f;
        Animator animator1;
        if(mLayoutFromRight)
            i = mThumbImage.getWidth();
        else
            i = -mThumbImage.getWidth();
        f = i;
        animator1 = groupAnimatorOfFloat(View.TRANSLATION_X, f, new View[] {
            mThumbImage, mTrackImage
        }).setDuration(300L);
        mDecorAnimation = new AnimatorSet();
        mDecorAnimation.playTogether(new Animator[] {
            animator, animator1
        });
        mDecorAnimation.start();
        mShowingPreview = false;
    }

    private void transitionToVisible()
    {
        if(mDecorAnimation != null)
            mDecorAnimation.cancel();
        Animator animator = groupAnimatorOfFloat(View.ALPHA, 1.0F, new View[] {
            mThumbImage, mTrackImage
        }).setDuration(150L);
        Animator animator1 = groupAnimatorOfFloat(View.ALPHA, 0.0F, new View[] {
            mPreviewImage, mPrimaryText, mSecondaryText
        }).setDuration(300L);
        Animator animator2 = groupAnimatorOfFloat(View.TRANSLATION_X, 0.0F, new View[] {
            mThumbImage, mTrackImage
        }).setDuration(150L);
        mDecorAnimation = new AnimatorSet();
        mDecorAnimation.playTogether(new Animator[] {
            animator, animator1, animator2
        });
        mDecorAnimation.start();
        mShowingPreview = false;
    }

    private void updateAppearance()
    {
        int i = 0;
        mTrackImage.setImageDrawable(mTrackDrawable);
        if(mTrackDrawable != null)
            i = Math.max(0, mTrackDrawable.getIntrinsicWidth());
        mThumbImage.setImageDrawable(mThumbDrawable);
        mThumbImage.setMinimumWidth(mThumbMinWidth);
        mThumbImage.setMinimumHeight(mThumbMinHeight);
        int j = i;
        if(mThumbDrawable != null)
            j = Math.max(i, mThumbDrawable.getIntrinsicWidth());
        mWidth = Math.max(j, mThumbMinWidth);
        if(mTextAppearance != 0)
        {
            mPrimaryText.setTextAppearance(mTextAppearance);
            mSecondaryText.setTextAppearance(mTextAppearance);
        }
        if(mTextColor != null)
        {
            mPrimaryText.setTextColor(mTextColor);
            mSecondaryText.setTextColor(mTextColor);
        }
        if(mTextSize > 0.0F)
        {
            mPrimaryText.setTextSize(0, mTextSize);
            mSecondaryText.setTextSize(0, mTextSize);
        }
        i = mPreviewPadding;
        mPrimaryText.setIncludeFontPadding(false);
        mPrimaryText.setPadding(i, i, i, i);
        mSecondaryText.setIncludeFontPadding(false);
        mSecondaryText.setPadding(i, i, i, i);
        refreshDrawablePressedState();
    }

    private void updateContainerRect()
    {
        AbsListView abslistview = mList;
        abslistview.resolvePadding();
        Rect rect = mContainerRect;
        rect.left = 0;
        rect.top = 0;
        rect.right = abslistview.getWidth();
        rect.bottom = abslistview.getHeight();
        int i = mScrollBarStyle;
        if(i == 0x1000000 || i == 0)
        {
            rect.left = rect.left + abslistview.getPaddingLeft();
            rect.top = rect.top + abslistview.getPaddingTop();
            rect.right = rect.right - abslistview.getPaddingRight();
            rect.bottom = rect.bottom - abslistview.getPaddingBottom();
            if(i == 0x1000000)
            {
                int j = getWidth();
                if(mScrollbarPosition == 2)
                    rect.right = rect.right + j;
                else
                    rect.left = rect.left - j;
            }
        }
    }

    private void updateLongList(int i, int j)
    {
        boolean flag;
        if(i > 0 && j / i >= 4)
            flag = true;
        else
            flag = false;
        if(mLongList != flag)
        {
            mLongList = flag;
            onStateDependencyChanged(false);
        }
    }

    private void updateOffsetAndRange()
    {
        ImageView imageview = mTrackImage;
        ImageView imageview1 = mThumbImage;
        float f;
        float f1;
        if(mThumbPosition == 1)
        {
            f = (float)imageview1.getHeight() / 2.0F;
            f1 = (float)imageview.getTop() + f;
            f = (float)imageview.getBottom() - f;
        } else
        {
            f1 = imageview.getTop();
            f = imageview.getBottom();
        }
        mThumbOffset = f1;
        mThumbRange = f - f1;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public boolean isAlwaysShowEnabled()
    {
        return mAlwaysShow;
    }

    public boolean isEnabled()
    {
        boolean flag;
        if(mEnabled)
        {
            if(!mLongList)
                flag = mAlwaysShow;
            else
                flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public boolean onInterceptHoverEvent(MotionEvent motionevent)
    {
        if(!isEnabled())
            return false;
        int i = motionevent.getActionMasked();
        if((i == 9 || i == 7) && mState == 0 && isPointInside(motionevent.getX(), motionevent.getY()))
        {
            setState(1);
            postAutoHide();
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionevent)
    {
        if(!isEnabled())
            return false;
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 44
    //                   0 46
    //                   1 152
    //                   2 88
    //                   3 152;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        return false;
_L2:
        if(isPointInside(motionevent.getX(), motionevent.getY()))
        {
            if(!mList.isInScrollingContainer())
                return true;
            mInitialTouchY = motionevent.getY();
            startPendingDrag();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(!isPointInside(motionevent.getX(), motionevent.getY()))
            cancelPendingDrag();
        else
        if(mPendingDrag >= 0L && mPendingDrag <= SystemClock.uptimeMillis())
        {
            beginDrag();
            scrollTo(getPosFromMotionEvent(mInitialTouchY));
            return onTouchEvent(motionevent);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        cancelPendingDrag();
        if(true) goto _L1; else goto _L5
_L5:
    }

    public void onItemCountChanged(int i, int j)
    {
        if(mOldItemCount != j || mOldChildCount != i)
        {
            mOldItemCount = j;
            mOldChildCount = i;
            boolean flag;
            if(j - i > 0)
                flag = true;
            else
                flag = false;
            if(flag && mState != 2)
                setThumbPos(getPosFromItemCount(mList.getFirstVisiblePosition(), i, j));
            updateLongList(i, j);
        }
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        if(mState == 2 || isPointInside(motionevent.getX(), motionevent.getY()))
            return PointerIcon.getSystemIcon(mList.getContext(), 1000);
        else
            return null;
    }

    public void onScroll(int i, int j, int k)
    {
        if(!isEnabled())
        {
            setState(0);
            return;
        }
        boolean flag;
        if(k - j > 0)
            flag = true;
        else
            flag = false;
        if(flag && mState != 2)
            setThumbPos(getPosFromItemCount(i, j, k));
        mScrollCompleted = true;
        if(mFirstVisibleItem != i)
        {
            mFirstVisibleItem = i;
            if(mState != 2)
            {
                setState(1);
                postAutoHide();
            }
        }
    }

    public void onSectionsChanged()
    {
        mListAdapter = null;
    }

    public void onSizeChanged(int i, int j, int k, int l)
    {
        updateLayout();
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(!isEnabled())
            return false;
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 44
    //                   0 46
    //                   1 77
    //                   2 151
    //                   3 221;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return false;
_L2:
        if(isPointInside(motionevent.getX(), motionevent.getY()) && !mList.isInScrollingContainer())
        {
            beginDrag();
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(mPendingDrag >= 0L)
        {
            beginDrag();
            float f = getPosFromMotionEvent(motionevent.getY());
            setThumbPos(f);
            scrollTo(f);
        }
        if(mState == 2)
        {
            if(mList != null)
            {
                mList.requestDisallowInterceptTouchEvent(false);
                mList.reportScrollStateChange(0);
            }
            setState(1);
            postAutoHide();
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(mPendingDrag >= 0L && Math.abs(motionevent.getY() - mInitialTouchY) > (float)mScaledTouchSlop)
            beginDrag();
        if(mState == 2)
        {
            float f1 = getPosFromMotionEvent(motionevent.getY());
            setThumbPos(f1);
            if(mScrollCompleted)
                scrollTo(f1);
            return true;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        cancelPendingDrag();
        if(true) goto _L1; else goto _L6
_L6:
    }

    public void remove()
    {
        mOverlay.remove(mTrackImage);
        mOverlay.remove(mThumbImage);
        mOverlay.remove(mPreviewImage);
        mOverlay.remove(mPrimaryText);
        mOverlay.remove(mSecondaryText);
    }

    public void setAlwaysShow(boolean flag)
    {
        if(mAlwaysShow != flag)
        {
            mAlwaysShow = flag;
            onStateDependencyChanged(false);
        }
    }

    public void setEnabled(boolean flag)
    {
        if(mEnabled != flag)
        {
            mEnabled = flag;
            onStateDependencyChanged(true);
        }
    }

    public void setScrollBarStyle(int i)
    {
        if(mScrollBarStyle != i)
        {
            mScrollBarStyle = i;
            updateLayout();
        }
    }

    public void setScrollbarPosition(int i)
    {
        boolean flag = true;
        int j = i;
        if(i == 0)
            if(mList.isLayoutRtl())
                j = 1;
            else
                j = 2;
        if(mScrollbarPosition != j)
        {
            mScrollbarPosition = j;
            boolean flag1;
            int ai[];
            if(j != 1)
                flag1 = true;
            else
                flag1 = false;
            mLayoutFromRight = flag1;
            ai = mPreviewResId;
            if(mLayoutFromRight)
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            i = ai[i];
            mPreviewImage.setBackgroundResource(i);
            i = Math.max(0, mPreviewMinWidth - mPreviewImage.getPaddingLeft() - mPreviewImage.getPaddingRight());
            mPrimaryText.setMinimumWidth(i);
            mSecondaryText.setMinimumWidth(i);
            i = Math.max(0, mPreviewMinHeight - mPreviewImage.getPaddingTop() - mPreviewImage.getPaddingBottom());
            mPrimaryText.setMinimumHeight(i);
            mSecondaryText.setMinimumHeight(i);
            updateLayout();
        }
    }

    public void setStyle(int i)
    {
        TypedArray typedarray;
        int j;
        typedarray = mList.getContext().obtainStyledAttributes(null, com.android.internal.R.styleable.FastScroll, 0x10103f7, i);
        j = typedarray.getIndexCount();
        i = 0;
_L17:
        int k;
        if(i >= j)
            break MISSING_BLOCK_LABEL_316;
        k = typedarray.getIndex(i);
        k;
        JVM INSTR tableswitch 0 13: default 112
    //                   0 190
    //                   1 217
    //                   2 204
    //                   3 288
    //                   4 232
    //                   5 246
    //                   6 302
    //                   7 132
    //                   8 148
    //                   9 118
    //                   10 164
    //                   11 274
    //                   12 260
    //                   13 177;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15
_L8:
        break MISSING_BLOCK_LABEL_302;
_L1:
        break; /* Loop/switch isn't completed */
_L11:
        break; /* Loop/switch isn't completed */
_L18:
        i++;
        if(true) goto _L17; else goto _L16
_L16:
        mOverlayPosition = typedarray.getInt(k, 0);
          goto _L18
_L9:
        mPreviewResId[0] = typedarray.getResourceId(k, 0);
          goto _L18
_L10:
        mPreviewResId[1] = typedarray.getResourceId(k, 0);
          goto _L18
_L12:
        mThumbDrawable = typedarray.getDrawable(k);
          goto _L18
_L15:
        mTrackDrawable = typedarray.getDrawable(k);
          goto _L18
_L2:
        mTextAppearance = typedarray.getResourceId(k, 0);
          goto _L18
_L4:
        mTextColor = typedarray.getColorStateList(k);
          goto _L18
_L3:
        mTextSize = typedarray.getDimensionPixelSize(k, 0);
          goto _L18
_L6:
        mPreviewMinWidth = typedarray.getDimensionPixelSize(k, 0);
          goto _L18
_L7:
        mPreviewMinHeight = typedarray.getDimensionPixelSize(k, 0);
          goto _L18
_L14:
        mThumbMinWidth = typedarray.getDimensionPixelSize(k, 0);
          goto _L18
_L13:
        mThumbMinHeight = typedarray.getDimensionPixelSize(k, 0);
          goto _L18
_L5:
        mPreviewPadding = typedarray.getDimensionPixelSize(k, 0);
          goto _L18
        mThumbPosition = typedarray.getInt(k, 0);
          goto _L18
        typedarray.recycle();
        updateAppearance();
        return;
    }

    public void stop()
    {
        setState(0);
    }

    public void updateLayout()
    {
        if(mUpdatingLayout)
            return;
        mUpdatingLayout = true;
        updateContainerRect();
        layoutThumb();
        layoutTrack();
        updateOffsetAndRange();
        Rect rect = mTempBounds;
        measurePreview(mPrimaryText, rect);
        applyLayout(mPrimaryText, rect);
        measurePreview(mSecondaryText, rect);
        applyLayout(mSecondaryText, rect);
        if(mPreviewImage != null)
        {
            rect.left = rect.left - mPreviewImage.getPaddingLeft();
            rect.top = rect.top - mPreviewImage.getPaddingTop();
            rect.right = rect.right + mPreviewImage.getPaddingRight();
            rect.bottom = rect.bottom + mPreviewImage.getPaddingBottom();
            applyLayout(mPreviewImage, rect);
        }
        mUpdatingLayout = false;
    }

    private static Property BOTTOM = new IntProperty("bottom") {

        public Integer get(View view)
        {
            return Integer.valueOf(view.getBottom());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, int i)
        {
            view.setBottom(i);
        }

        public volatile void setValue(Object obj, int i)
        {
            setValue((View)obj, i);
        }

    }
;
    private static final int DURATION_CROSS_FADE = 50;
    private static final int DURATION_FADE_IN = 150;
    private static final int DURATION_FADE_OUT = 300;
    private static final int DURATION_RESIZE = 100;
    private static final long FADE_TIMEOUT = 1500L;
    private static Property LEFT = new IntProperty("left") {

        public Integer get(View view)
        {
            return Integer.valueOf(view.getLeft());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, int i)
        {
            view.setLeft(i);
        }

        public volatile void setValue(Object obj, int i)
        {
            setValue((View)obj, i);
        }

    }
;
    private static final int MIN_PAGES = 4;
    private static final int OVERLAY_ABOVE_THUMB = 2;
    private static final int OVERLAY_AT_THUMB = 1;
    private static final int OVERLAY_FLOATING = 0;
    private static final int PREVIEW_LEFT = 0;
    private static final int PREVIEW_RIGHT = 1;
    private static Property RIGHT = new IntProperty("right") {

        public Integer get(View view)
        {
            return Integer.valueOf(view.getRight());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, int i)
        {
            view.setRight(i);
        }

        public volatile void setValue(Object obj, int i)
        {
            setValue((View)obj, i);
        }

    }
;
    private static final int STATE_DRAGGING = 2;
    private static final int STATE_NONE = 0;
    private static final int STATE_VISIBLE = 1;
    private static final long TAP_TIMEOUT = (long)ViewConfiguration.getTapTimeout();
    private static final int THUMB_POSITION_INSIDE = 1;
    private static final int THUMB_POSITION_MIDPOINT = 0;
    private static Property TOP = new IntProperty("top") {

        public Integer get(View view)
        {
            return Integer.valueOf(view.getTop());
        }

        public volatile Object get(Object obj)
        {
            return get((View)obj);
        }

        public void setValue(View view, int i)
        {
            view.setTop(i);
        }

        public volatile void setValue(Object obj, int i)
        {
            setValue((View)obj, i);
        }

    }
;
    private boolean mAlwaysShow;
    private final Rect mContainerRect = new Rect();
    private int mCurrentSection;
    private AnimatorSet mDecorAnimation;
    private final Runnable mDeferHide = new Runnable() {

        public void run()
        {
            FastScroller._2D_wrap0(FastScroller.this, 0);
        }

        final FastScroller this$0;

            
            {
                this$0 = FastScroller.this;
                super();
            }
    }
;
    private boolean mEnabled;
    private int mFirstVisibleItem;
    private int mHeaderCount;
    private float mInitialTouchY;
    private boolean mLayoutFromRight;
    private final AbsListView mList;
    private Adapter mListAdapter;
    private boolean mLongList;
    private boolean mMatchDragPosition;
    private final int mMinimumTouchTarget;
    private int mOldChildCount;
    private int mOldItemCount;
    private final ViewGroupOverlay mOverlay;
    private int mOverlayPosition;
    private long mPendingDrag;
    private AnimatorSet mPreviewAnimation;
    private final View mPreviewImage;
    private int mPreviewMinHeight;
    private int mPreviewMinWidth;
    private int mPreviewPadding;
    private final int mPreviewResId[] = new int[2];
    private final TextView mPrimaryText;
    private int mScaledTouchSlop;
    private int mScrollBarStyle;
    private boolean mScrollCompleted;
    private int mScrollbarPosition;
    private final TextView mSecondaryText;
    private SectionIndexer mSectionIndexer;
    private Object mSections[];
    private boolean mShowingPreview;
    private boolean mShowingPrimary;
    private int mState;
    private final android.animation.Animator.AnimatorListener mSwitchPrimaryListener = new AnimatorListenerAdapter() {

        public void onAnimationEnd(Animator animator)
        {
            FastScroller._2D_set0(FastScroller.this, FastScroller._2D_get0(FastScroller.this) ^ true);
        }

        final FastScroller this$0;

            
            {
                this$0 = FastScroller.this;
                super();
            }
    }
;
    private final Rect mTempBounds = new Rect();
    private final Rect mTempMargins = new Rect();
    private int mTextAppearance;
    private ColorStateList mTextColor;
    private float mTextSize;
    private Drawable mThumbDrawable;
    private final ImageView mThumbImage;
    private int mThumbMinHeight;
    private int mThumbMinWidth;
    private float mThumbOffset;
    private int mThumbPosition;
    private float mThumbRange;
    private Drawable mTrackDrawable;
    private final ImageView mTrackImage;
    private boolean mUpdatingLayout;
    private int mWidth;

}
