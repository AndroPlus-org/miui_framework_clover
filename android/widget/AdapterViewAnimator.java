// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.*;
import android.util.AttributeSet;
import android.view.*;
import java.util.*;

// Referenced classes of package android.widget:
//            AdapterView, Advanceable, FrameLayout, RemoteViewsAdapter, 
//            Adapter

public abstract class AdapterViewAnimator extends AdapterView
    implements RemoteViewsAdapter.RemoteAdapterConnectionCallback, Advanceable
{
    final class CheckForTap
        implements Runnable
    {

        public void run()
        {
            if(AdapterViewAnimator._2D_get0(AdapterViewAnimator.this) == 1)
            {
                View view = getCurrentView();
                showTapFeedback(view);
            }
        }

        final AdapterViewAnimator this$0;

        CheckForTap()
        {
            this$0 = AdapterViewAnimator.this;
            super();
        }
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public String toString()
        {
            return (new StringBuilder()).append("AdapterViewAnimator.SavedState{ whichChild = ").append(whichChild).append(" }").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeInt(whichChild);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        int whichChild;


        private SavedState(Parcel parcel)
        {
            super(parcel);
            whichChild = parcel.readInt();
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        SavedState(Parcelable parcelable, int i)
        {
            super(parcelable);
            whichChild = i;
        }
    }

    class ViewAndMetaData
    {

        int adapterPosition;
        long itemId;
        int relativeIndex;
        final AdapterViewAnimator this$0;
        View view;

        ViewAndMetaData(View view1, int i, int j, long l)
        {
            this$0 = AdapterViewAnimator.this;
            super();
            view = view1;
            relativeIndex = i;
            adapterPosition = j;
            itemId = l;
        }
    }


    static int _2D_get0(AdapterViewAnimator adapterviewanimator)
    {
        return adapterviewanimator.mTouchMode;
    }

    public AdapterViewAnimator(Context context)
    {
        this(context, null);
    }

    public AdapterViewAnimator(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public AdapterViewAnimator(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public AdapterViewAnimator(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mWhichChild = 0;
        mRestoreWhichChild = -1;
        mAnimateFirstTime = true;
        mActiveOffset = 0;
        mMaxNumActiveViews = 1;
        mViewsMap = new HashMap();
        mCurrentWindowStart = 0;
        mCurrentWindowEnd = -1;
        mCurrentWindowStartUnbounded = 0;
        mDeferNotifyDataSetChanged = false;
        mFirstTime = true;
        mLoopViews = true;
        mReferenceChildWidth = -1;
        mReferenceChildHeight = -1;
        mTouchMode = 0;
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AdapterViewAnimator, i, j);
        i = attributeset.getResourceId(0, 0);
        if(i > 0)
            setInAnimation(context, i);
        else
            setInAnimation(getDefaultInAnimation());
        i = attributeset.getResourceId(1, 0);
        if(i > 0)
            setOutAnimation(context, i);
        else
            setOutAnimation(getDefaultOutAnimation());
        setAnimateFirstView(attributeset.getBoolean(2, true));
        mLoopViews = attributeset.getBoolean(3, false);
        attributeset.recycle();
        initViewAnimator();
    }

    private void addChild(View view)
    {
        addViewInLayout(view, -1, createOrReuseLayoutParams(view));
        if(mReferenceChildWidth == -1 || mReferenceChildHeight == -1)
        {
            int i = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
            view.measure(i, i);
            mReferenceChildWidth = view.getMeasuredWidth();
            mReferenceChildHeight = view.getMeasuredHeight();
        }
    }

    private ViewAndMetaData getMetaDataForChild(View view)
    {
        for(Iterator iterator = mViewsMap.values().iterator(); iterator.hasNext();)
        {
            ViewAndMetaData viewandmetadata = (ViewAndMetaData)iterator.next();
            if(viewandmetadata.view == view)
                return viewandmetadata;
        }

        return null;
    }

    private void initViewAnimator()
    {
        mPreviousViews = new ArrayList();
    }

    private void measureChildren()
    {
        int i = getChildCount();
        int j = getMeasuredWidth();
        int k = mPaddingLeft;
        int l = mPaddingRight;
        int i1 = getMeasuredHeight();
        int j1 = mPaddingTop;
        int k1 = mPaddingBottom;
        for(int l1 = 0; l1 < i; l1++)
            getChildAt(l1).measure(android.view.View.MeasureSpec.makeMeasureSpec(j - k - l, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(i1 - j1 - k1, 0x40000000));

    }

    private void setDisplayedChild(int i, boolean flag)
    {
        boolean flag1 = false;
        boolean flag2 = false;
        if(mAdapter != null)
        {
            mWhichChild = i;
            if(i >= getWindowSize())
            {
                if(mLoopViews)
                    i = ((flag2) ? 1 : 0);
                else
                    i = getWindowSize() - 1;
                mWhichChild = i;
            } else
            if(i < 0)
            {
                i = ((flag1) ? 1 : 0);
                if(mLoopViews)
                    i = getWindowSize() - 1;
                mWhichChild = i;
            }
            if(getFocusedChild() != null)
                i = 1;
            else
                i = 0;
            showOnly(mWhichChild, flag);
            if(i != 0)
                requestFocus(2);
        }
    }

    public void advance()
    {
        showNext();
    }

    void applyTransformForChildAtIndex(View view, int i)
    {
    }

    void cancelHandleClick()
    {
        View view = getCurrentView();
        if(view != null)
            hideTapFeedback(view);
        mTouchMode = 0;
    }

    void checkForAndHandleDataChanged()
    {
        if(mDataChanged)
            post(new Runnable() {

                public void run()
                {
                    handleDataChanged();
                    if(mWhichChild < getWindowSize()) goto _L2; else goto _L1
_L1:
                    mWhichChild = 0;
                    showOnly(mWhichChild, false);
_L4:
                    refreshChildren();
                    requestLayout();
                    return;
_L2:
                    if(mOldItemCount != getCount())
                        showOnly(mWhichChild, false);
                    if(true) goto _L4; else goto _L3
_L3:
                }

                final AdapterViewAnimator this$0;

            
            {
                this$0 = AdapterViewAnimator.this;
                super();
            }
            }
);
        mDataChanged = false;
    }

    void configureViewAnimator(int i, int j)
    {
        mMaxNumActiveViews = i;
        mActiveOffset = j;
        mPreviousViews.clear();
        mViewsMap.clear();
        removeAllViewsInLayout();
        mCurrentWindowStart = 0;
        mCurrentWindowEnd = -1;
    }

    android.view.ViewGroup.LayoutParams createOrReuseLayoutParams(View view)
    {
        view = view.getLayoutParams();
        if(view != null)
            return view;
        else
            return new android.view.ViewGroup.LayoutParams(0, 0);
    }

    public void deferNotifyDataSetChanged()
    {
        mDeferNotifyDataSetChanged = true;
    }

    public void fyiWillBeAdvancedByHostKThx()
    {
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/AdapterViewAnimator.getName();
    }

    public Adapter getAdapter()
    {
        return mAdapter;
    }

    public int getBaseline()
    {
        int i;
        if(getCurrentView() != null)
            i = getCurrentView().getBaseline();
        else
            i = super.getBaseline();
        return i;
    }

    public View getCurrentView()
    {
        return getViewAtRelativeIndex(mActiveOffset);
    }

    ObjectAnimator getDefaultInAnimation()
    {
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(null, "alpha", new float[] {
            0.0F, 1.0F
        });
        objectanimator.setDuration(200L);
        return objectanimator;
    }

    ObjectAnimator getDefaultOutAnimation()
    {
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(null, "alpha", new float[] {
            1.0F, 0.0F
        });
        objectanimator.setDuration(200L);
        return objectanimator;
    }

    public int getDisplayedChild()
    {
        return mWhichChild;
    }

    FrameLayout getFrameForChild()
    {
        return new FrameLayout(mContext);
    }

    public ObjectAnimator getInAnimation()
    {
        return mInAnimation;
    }

    int getNumActiveViews()
    {
        if(mAdapter != null)
            return Math.min(getCount() + 1, mMaxNumActiveViews);
        else
            return mMaxNumActiveViews;
    }

    public ObjectAnimator getOutAnimation()
    {
        return mOutAnimation;
    }

    public View getSelectedView()
    {
        return getViewAtRelativeIndex(mActiveOffset);
    }

    View getViewAtRelativeIndex(int i)
    {
        if(i >= 0 && i <= getNumActiveViews() - 1 && mAdapter != null)
        {
            i = modulo(mCurrentWindowStartUnbounded + i, getWindowSize());
            if(mViewsMap.get(Integer.valueOf(i)) != null)
                return ((ViewAndMetaData)mViewsMap.get(Integer.valueOf(i))).view;
        }
        return null;
    }

    int getWindowSize()
    {
        if(mAdapter != null)
        {
            int i = getCount();
            if(i <= getNumActiveViews() && mLoopViews)
                return mMaxNumActiveViews * i;
            else
                return i;
        } else
        {
            return 0;
        }
    }

    void hideTapFeedback(View view)
    {
        view.setPressed(false);
    }

    int modulo(int i, int j)
    {
        if(j > 0)
            return (i % j + j) % j;
        else
            return 0;
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        checkForAndHandleDataChanged();
        j = getChildCount();
        for(i = 0; i < j; i++)
        {
            View view = getChildAt(i);
            l = mPaddingLeft;
            k = view.getMeasuredWidth();
            int i1 = mPaddingTop;
            int j1 = view.getMeasuredHeight();
            view.layout(mPaddingLeft, mPaddingTop, l + k, i1 + j1);
        }

    }

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getSize(i);
        int l = android.view.View.MeasureSpec.getSize(j);
        int i1 = android.view.View.MeasureSpec.getMode(i);
        int j1 = android.view.View.MeasureSpec.getMode(j);
        boolean flag;
        if(mReferenceChildWidth != -1 && mReferenceChildHeight != -1)
            flag = true;
        else
            flag = false;
        if(j1 == 0)
        {
            if(flag)
                i = mReferenceChildHeight + mPaddingTop + mPaddingBottom;
            else
                i = 0;
        } else
        {
            i = l;
            if(j1 == 0x80000000)
            {
                i = l;
                if(flag)
                {
                    i = mReferenceChildHeight + mPaddingTop + mPaddingBottom;
                    if(i > l)
                        i = l | 0x1000000;
                }
            }
        }
        if(i1 == 0)
        {
            if(flag)
                j = mReferenceChildWidth + mPaddingLeft + mPaddingRight;
            else
                j = 0;
        } else
        {
            j = k;
            if(j1 == 0x80000000)
            {
                j = k;
                if(flag)
                {
                    j = mReferenceChildWidth + mPaddingLeft + mPaddingRight;
                    if(j > k)
                        j = k | 0x1000000;
                }
            }
        }
        setMeasuredDimension(j, i);
        measureChildren();
    }

    public boolean onRemoteAdapterConnected()
    {
        if(mRemoteViewsAdapter != mAdapter)
        {
            setAdapter(mRemoteViewsAdapter);
            if(mDeferNotifyDataSetChanged)
            {
                mRemoteViewsAdapter.notifyDataSetChanged();
                mDeferNotifyDataSetChanged = false;
            }
            if(mRestoreWhichChild > -1)
            {
                setDisplayedChild(mRestoreWhichChild, false);
                mRestoreWhichChild = -1;
            }
            return false;
        }
        if(mRemoteViewsAdapter != null)
        {
            mRemoteViewsAdapter.superNotifyDataSetChanged();
            return true;
        } else
        {
            return false;
        }
    }

    public void onRemoteAdapterDisconnected()
    {
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        mWhichChild = ((SavedState) (parcelable)).whichChild;
        if(mRemoteViewsAdapter != null && mAdapter == null)
            mRestoreWhichChild = mWhichChild;
        else
            setDisplayedChild(mWhichChild, false);
    }

    public Parcelable onSaveInstanceState()
    {
        Parcelable parcelable = super.onSaveInstanceState();
        if(mRemoteViewsAdapter != null)
            mRemoteViewsAdapter.saveRemoteViewsCache();
        return new SavedState(parcelable, mWhichChild);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag;
        boolean flag1;
        boolean flag2;
        i = motionevent.getAction();
        flag = false;
        flag1 = false;
        flag2 = flag1;
        i;
        JVM INSTR tableswitch 0 6: default 56
    //                   0 63
    //                   1 144
    //                   2 60
    //                   3 253
    //                   4 60
    //                   5 60
    //                   6 60;
           goto _L1 _L2 _L3 _L4 _L5 _L4 _L4 _L4
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        flag2 = flag1;
_L7:
        return flag2;
_L2:
        View view = getCurrentView();
        flag2 = flag1;
        if(view != null)
        {
            flag2 = flag1;
            if(isTransformedTouchPointInView(motionevent.getX(), motionevent.getY(), view, null))
            {
                if(mPendingCheckForTap == null)
                    mPendingCheckForTap = new CheckForTap();
                mTouchMode = 1;
                postDelayed(mPendingCheckForTap, ViewConfiguration.getTapTimeout());
                flag2 = flag1;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        flag2 = flag;
        if(mTouchMode == 1)
        {
            final View v = getCurrentView();
            final ViewAndMetaData viewData = getMetaDataForChild(v);
            flag2 = flag;
            if(v != null)
            {
                flag2 = flag;
                if(isTransformedTouchPointInView(motionevent.getX(), motionevent.getY(), v, null))
                {
                    motionevent = getHandler();
                    if(motionevent != null)
                        motionevent.removeCallbacks(mPendingCheckForTap);
                    showTapFeedback(v);
                    postDelayed(new Runnable() {

                        public void run()
                        {
                            hideTapFeedback(v);
                            post(v. new Runnable() {

                                public void run()
                                {
                                    if(viewData != null)
                                        performItemClick(v, viewData.adapterPosition, viewData.itemId);
                                    else
                                        performItemClick(v, 0, 0L);
                                }

                                final _cls1 this$1;
                                final View val$v;
                                final ViewAndMetaData val$viewData;

            
            {
                this$1 = final__pcls1;
                viewData = viewandmetadata;
                v = View.this;
                super();
            }
                            }
);
                        }

                        final AdapterViewAnimator this$0;
                        final View val$v;
                        final ViewAndMetaData val$viewData;

            
            {
                this$0 = AdapterViewAnimator.this;
                v = view;
                viewData = viewandmetadata;
                super();
            }
                    }
, ViewConfiguration.getPressedStateDuration());
                    flag2 = true;
                }
            }
        }
        mTouchMode = 0;
        continue; /* Loop/switch isn't completed */
_L5:
        motionevent = getCurrentView();
        if(motionevent != null)
            hideTapFeedback(motionevent);
        mTouchMode = 0;
        flag2 = flag1;
        if(true) goto _L7; else goto _L6
_L6:
    }

    void refreshChildren()
    {
        if(mAdapter == null)
            return;
        for(int i = mCurrentWindowStart; i <= mCurrentWindowEnd; i++)
        {
            int j = modulo(i, getWindowSize());
            int k = getCount();
            View view = mAdapter.getView(modulo(i, k), null, this);
            if(view.getImportantForAccessibility() == 0)
                view.setImportantForAccessibility(1);
            if(!mViewsMap.containsKey(Integer.valueOf(j)))
                continue;
            FrameLayout framelayout = (FrameLayout)((ViewAndMetaData)mViewsMap.get(Integer.valueOf(j))).view;
            if(view != null)
            {
                framelayout.removeAllViewsInLayout();
                framelayout.addView(view);
            }
        }

    }

    public void setAdapter(Adapter adapter)
    {
        if(mAdapter != null && mDataSetObserver != null)
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        mAdapter = adapter;
        checkFocus();
        if(mAdapter != null)
        {
            mDataSetObserver = new AdapterView.AdapterDataSetObserver(this);
            mAdapter.registerDataSetObserver(mDataSetObserver);
            mItemCount = mAdapter.getCount();
        }
        setFocusable(true);
        mWhichChild = 0;
        showOnly(mWhichChild, false);
    }

    public void setAnimateFirstView(boolean flag)
    {
        mAnimateFirstTime = flag;
    }

    public void setDisplayedChild(int i)
    {
        setDisplayedChild(i, true);
    }

    public void setInAnimation(ObjectAnimator objectanimator)
    {
        mInAnimation = objectanimator;
    }

    public void setInAnimation(Context context, int i)
    {
        setInAnimation((ObjectAnimator)AnimatorInflater.loadAnimator(context, i));
    }

    public void setOutAnimation(ObjectAnimator objectanimator)
    {
        mOutAnimation = objectanimator;
    }

    public void setOutAnimation(Context context, int i)
    {
        setOutAnimation((ObjectAnimator)AnimatorInflater.loadAnimator(context, i));
    }

    public void setRemoteViewsAdapter(Intent intent)
    {
        setRemoteViewsAdapter(intent, false);
    }

    public void setRemoteViewsAdapter(Intent intent, boolean flag)
    {
        if(mRemoteViewsAdapter != null && (new android.content.Intent.FilterComparison(intent)).equals(new android.content.Intent.FilterComparison(mRemoteViewsAdapter.getRemoteViewsServiceIntent())))
            return;
        mDeferNotifyDataSetChanged = false;
        mRemoteViewsAdapter = new RemoteViewsAdapter(getContext(), intent, this, flag);
        if(mRemoteViewsAdapter.isDataReady())
            setAdapter(mRemoteViewsAdapter);
    }

    public Runnable setRemoteViewsAdapterAsync(Intent intent)
    {
        return new RemoteViewsAdapter.AsyncRemoteAdapterAction(this, intent);
    }

    public void setRemoteViewsOnClickHandler(RemoteViews.OnClickHandler onclickhandler)
    {
        if(mRemoteViewsAdapter != null)
            mRemoteViewsAdapter.setRemoteViewsOnClickHandler(onclickhandler);
    }

    public void setSelection(int i)
    {
        setDisplayedChild(i);
    }

    public void showNext()
    {
        setDisplayedChild(mWhichChild + 1);
    }

    void showOnly(int i, boolean flag)
    {
        int j;
        int l1;
        int i2;
        int j2;
        int l2;
        boolean flag1;
        Iterator iterator;
        if(mAdapter == null)
            return;
        j = getCount();
        if(j == 0)
            return;
        for(int k = 0; k < mPreviousViews.size(); k++)
        {
            View view = ((ViewAndMetaData)mViewsMap.get(mPreviousViews.get(k))).view;
            mViewsMap.remove(mPreviousViews.get(k));
            view.clearAnimation();
            if(view instanceof ViewGroup)
                ((ViewGroup)view).removeAllViewsInLayout();
            applyTransformForChildAtIndex(view, -1);
            removeViewInLayout(view);
        }

        mPreviousViews.clear();
        l1 = i - mActiveOffset;
        int l = (getNumActiveViews() + l1) - 1;
        i = Math.max(0, l1);
        i2 = Math.min(j - 1, l);
        if(mLoopViews)
        {
            i = l1;
            i2 = l;
        }
        j2 = modulo(i, getWindowSize());
        l2 = modulo(i2, getWindowSize());
        flag1 = false;
        if(j2 > l2)
            flag1 = true;
        iterator = mViewsMap.keySet().iterator();
_L10:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Integer integer;
        boolean flag2;
        integer = (Integer)iterator.next();
        flag2 = false;
        if(flag1 || integer.intValue() >= j2 && integer.intValue() <= l2) goto _L4; else goto _L3
_L3:
        int i1 = 1;
_L6:
        if(i1 != 0)
        {
            View view1 = ((ViewAndMetaData)mViewsMap.get(integer)).view;
            i1 = ((ViewAndMetaData)mViewsMap.get(integer)).relativeIndex;
            mPreviousViews.add(integer);
            transformViewForTransition(i1, -1, view1, flag);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        i1 = ((flag2) ? 1 : 0);
        if(flag1)
        {
            i1 = ((flag2) ? 1 : 0);
            if(integer.intValue() > l2)
            {
                i1 = ((flag2) ? 1 : 0);
                if(integer.intValue() < j2)
                    i1 = 1;
            }
        }
        if(true) goto _L6; else goto _L5
_L2:
        if(i != mCurrentWindowStart || i2 != mCurrentWindowEnd || l1 != mCurrentWindowStartUnbounded) goto _L5; else goto _L7
_L7:
        requestLayout();
        invalidate();
        return;
_L5:
        int j1 = i;
        while(j1 <= i2) 
        {
            int i3 = modulo(j1, getWindowSize());
            int k2;
            int j3;
            boolean flag3;
            if(mViewsMap.containsKey(Integer.valueOf(i3)))
                j3 = ((ViewAndMetaData)mViewsMap.get(Integer.valueOf(i3))).relativeIndex;
            else
                j3 = -1;
            k2 = j1 - l1;
            if(mViewsMap.containsKey(Integer.valueOf(i3)))
                flag3 = mPreviousViews.contains(Integer.valueOf(i3)) ^ true;
            else
                flag3 = false;
            if(flag3)
            {
                View view2 = ((ViewAndMetaData)mViewsMap.get(Integer.valueOf(i3))).view;
                ((ViewAndMetaData)mViewsMap.get(Integer.valueOf(i3))).relativeIndex = k2;
                applyTransformForChildAtIndex(view2, k2);
                transformViewForTransition(j3, k2, view2, flag);
            } else
            {
                int k3 = modulo(j1, j);
                View view3 = mAdapter.getView(k3, null, this);
                long l3 = mAdapter.getItemId(k3);
                FrameLayout framelayout = getFrameForChild();
                if(view3 != null)
                    framelayout.addView(view3);
                mViewsMap.put(Integer.valueOf(i3), new ViewAndMetaData(framelayout, k2, k3, l3));
                addChild(framelayout);
                applyTransformForChildAtIndex(framelayout, k2);
                transformViewForTransition(-1, k2, framelayout, flag);
            }
            ((ViewAndMetaData)mViewsMap.get(Integer.valueOf(i3))).view.bringToFront();
            j1++;
        }
        mCurrentWindowStart = i;
        mCurrentWindowEnd = i2;
        mCurrentWindowStartUnbounded = l1;
        if(mRemoteViewsAdapter != null)
        {
            i = modulo(mCurrentWindowStart, j);
            int k1 = modulo(mCurrentWindowEnd, j);
            mRemoteViewsAdapter.setVisibleRangeHint(i, k1);
        }
        if(true) goto _L7; else goto _L8
_L8:
        if(true) goto _L10; else goto _L9
_L9:
    }

    public void showPrevious()
    {
        setDisplayedChild(mWhichChild - 1);
    }

    void showTapFeedback(View view)
    {
        view.setPressed(true);
    }

    void transformViewForTransition(int i, int j, View view, boolean flag)
    {
        if(i != -1) goto _L2; else goto _L1
_L1:
        mInAnimation.setTarget(view);
        mInAnimation.start();
_L4:
        return;
_L2:
        if(j == -1)
        {
            mOutAnimation.setTarget(view);
            mOutAnimation.start();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final int DEFAULT_ANIMATION_DURATION = 200;
    private static final String TAG = "RemoteViewAnimator";
    static final int TOUCH_MODE_DOWN_IN_CURRENT_VIEW = 1;
    static final int TOUCH_MODE_HANDLED = 2;
    static final int TOUCH_MODE_NONE = 0;
    int mActiveOffset;
    Adapter mAdapter;
    boolean mAnimateFirstTime;
    int mCurrentWindowEnd;
    int mCurrentWindowStart;
    int mCurrentWindowStartUnbounded;
    AdapterView.AdapterDataSetObserver mDataSetObserver;
    boolean mDeferNotifyDataSetChanged;
    boolean mFirstTime;
    ObjectAnimator mInAnimation;
    boolean mLoopViews;
    int mMaxNumActiveViews;
    ObjectAnimator mOutAnimation;
    private Runnable mPendingCheckForTap;
    ArrayList mPreviousViews;
    int mReferenceChildHeight;
    int mReferenceChildWidth;
    RemoteViewsAdapter mRemoteViewsAdapter;
    private int mRestoreWhichChild;
    private int mTouchMode;
    HashMap mViewsMap;
    int mWhichChild;
}
