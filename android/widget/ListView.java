// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Trace;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// Referenced classes of package android.widget:
//            AbsListView, ArrayAdapter, ListAdapter, Checkable, 
//            HeaderViewListAdapter, Adapter

public class ListView extends AbsListView
{
    private static class ArrowScrollFocusResult
    {

        public int getAmountToScroll()
        {
            return mAmountToScroll;
        }

        public int getSelectedPosition()
        {
            return mSelectedPosition;
        }

        void populate(int i, int j)
        {
            mSelectedPosition = i;
            mAmountToScroll = j;
        }

        private int mAmountToScroll;
        private int mSelectedPosition;

        private ArrowScrollFocusResult()
        {
        }

        ArrowScrollFocusResult(ArrowScrollFocusResult arrowscrollfocusresult)
        {
            this();
        }
    }

    public class FixedViewInfo
    {

        public Object data;
        public boolean isSelectable;
        final ListView this$0;
        public View view;

        public FixedViewInfo()
        {
            this$0 = ListView.this;
            super();
        }
    }

    private class FocusSelector
        implements Runnable
    {

        void onLayoutComplete()
        {
            if(mAction == 2)
                mAction = -1;
        }

        public void run()
        {
            if(mAction != 1) goto _L2; else goto _L1
_L1:
            setSelectionFromTop(mPosition, mPositionTop);
            mAction = 2;
_L4:
            return;
_L2:
            if(mAction == 3)
            {
                int i = mPosition;
                int j = mFirstPosition;
                View view = getChildAt(i - j);
                if(view != null)
                    view.requestFocus();
                mAction = -1;
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        Runnable setupFocusIfValid(int i)
        {
            if(mAction != 2 || i != mPosition)
            {
                return null;
            } else
            {
                mAction = 3;
                return this;
            }
        }

        FocusSelector setupForSetSelection(int i, int j)
        {
            mPosition = i;
            mPositionTop = j;
            mAction = 1;
            return this;
        }

        private static final int STATE_REQUEST_FOCUS = 3;
        private static final int STATE_SET_SELECTION = 1;
        private static final int STATE_WAIT_FOR_LAYOUT = 2;
        private int mAction;
        private int mPosition;
        private int mPositionTop;
        final ListView this$0;

        private FocusSelector()
        {
            this$0 = ListView.this;
            super();
        }

        FocusSelector(FocusSelector focusselector)
        {
            this();
        }
    }


    public ListView(Context context)
    {
        this(context, null);
    }

    public ListView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010074);
    }

    public ListView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ListView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mHeaderViewInfos = Lists.newArrayList();
        mFooterViewInfos = Lists.newArrayList();
        mAreAllItemsSelectable = true;
        mItemsCanFocus = false;
        mTempRect = new Rect();
        mArrowScrollFocusResult = new ArrowScrollFocusResult(null);
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ListView, i, j);
        CharSequence acharsequence[] = attributeset.getTextArray(0);
        if(acharsequence != null)
            setAdapter(new ArrayAdapter(context, 0x1090003, acharsequence));
        context = attributeset.getDrawable(1);
        if(context != null)
            setDivider(context);
        context = attributeset.getDrawable(5);
        if(context != null)
            setOverscrollHeader(context);
        context = attributeset.getDrawable(6);
        if(context != null)
            setOverscrollFooter(context);
        if(attributeset.hasValueOrEmpty(2))
        {
            i = attributeset.getDimensionPixelSize(2, 0);
            if(i != 0)
                setDividerHeight(i);
        }
        mHeaderDividersEnabled = attributeset.getBoolean(3, true);
        mFooterDividersEnabled = attributeset.getBoolean(4, true);
        attributeset.recycle();
    }

    private View addViewAbove(View view, int i)
    {
        i--;
        View view1 = obtainView(i, mIsScrap);
        setupChild(view1, i, view.getTop() - mDividerHeight, false, mListPadding.left, false, mIsScrap[0]);
        return view1;
    }

    private View addViewBelow(View view, int i)
    {
        i++;
        View view1 = obtainView(i, mIsScrap);
        setupChild(view1, i, view.getBottom() + mDividerHeight, true, mListPadding.left, false, mIsScrap[0]);
        return view1;
    }

    private void adjustViewsUpOrDown()
    {
        int i = getChildCount();
        if(i <= 0) goto _L2; else goto _L1
_L1:
        if(mStackFromBottom) goto _L4; else goto _L3
_L3:
        int j;
        j = getChildAt(0).getTop() - mListPadding.top;
        int k = j;
        if(mFirstPosition != 0)
            k = j - mDividerHeight;
        j = k;
        if(k < 0)
            j = 0;
_L6:
        if(j != 0)
            offsetChildrenTopAndBottom(-j);
_L2:
        return;
_L4:
        j = getChildAt(i - 1).getBottom() - (getHeight() - mListPadding.bottom);
        int l = j;
        if(mFirstPosition + i < mItemCount)
            l = j + mDividerHeight;
        j = l;
        if(l > 0)
            j = 0;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private int amountToScroll(int i, int j)
    {
        int k = getHeight() - mListPadding.bottom;
        int l = mListPadding.top;
        int i1 = getChildCount();
        if(i == 130)
        {
            int j1 = i1 - 1;
            i = i1;
            if(j != -1)
            {
                j1 = j - mFirstPosition;
                i = i1;
            }
            for(; i <= j1; i++)
                addViewBelow(getChildAt(i - 1), (mFirstPosition + i) - 1);

            i1 = mFirstPosition;
            View view = getChildAt(j1);
            l = k;
            if(i1 + j1 < mItemCount - 1)
                l = k - getArrowScrollPreviewLength();
            if(view.getBottom() <= l)
                return 0;
            if(j != -1 && l - view.getTop() >= getMaxScrollAmount())
                return 0;
            j1 = view.getBottom() - l;
            j = j1;
            if(mFirstPosition + i == mItemCount)
                j = Math.min(j1, getChildAt(i - 1).getBottom() - k);
            return Math.min(j, getMaxScrollAmount());
        }
        i = 0;
        if(j != -1)
            i = j - mFirstPosition;
        for(; i < 0; i = j - mFirstPosition)
        {
            addViewAbove(getChildAt(0), mFirstPosition);
            mFirstPosition = mFirstPosition - 1;
        }

        k = mFirstPosition;
        View view1 = getChildAt(i);
        int k1 = l;
        if(k + i > 0)
            k1 = l + getArrowScrollPreviewLength();
        if(view1.getTop() >= k1)
            return 0;
        if(j != -1 && view1.getBottom() - k1 >= getMaxScrollAmount())
            return 0;
        j = k1 - view1.getTop();
        i = j;
        if(mFirstPosition == 0)
            i = Math.min(j, l - getChildAt(0).getTop());
        return Math.min(i, getMaxScrollAmount());
    }

    private int amountToScrollToNewFocus(int i, View view, int j)
    {
        int k;
        k = 0;
        view.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(view, mTempRect);
        if(i != 33) goto _L2; else goto _L1
_L1:
        i = k;
        if(mTempRect.top < mListPadding.top)
        {
            k = mListPadding.top - mTempRect.top;
            i = k;
            if(j > 0)
                i = k + getArrowScrollPreviewLength();
        }
_L4:
        return i;
_L2:
        int i1 = getHeight() - mListPadding.bottom;
        i = k;
        if(mTempRect.bottom > i1)
        {
            int l = mTempRect.bottom - i1;
            i = l;
            if(j < mItemCount - 1)
                i = l + getArrowScrollPreviewLength();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private ArrowScrollFocusResult arrowScrollFocused(int i)
    {
        View view = getSelectedView();
        if(view != null && view.hasFocus())
        {
            view = view.findFocus();
            view = FocusFinder.getInstance().findNextFocus(this, view, i);
        } else
        {
            if(i == 130)
            {
                if(mFirstPosition > 0)
                    j = 1;
                else
                    j = 0;
                k = mListPadding.top;
                if(j != 0)
                    j = getArrowScrollPreviewLength();
                else
                    j = 0;
                j = k + j;
                if(view != null && view.getTop() > j)
                    j = view.getTop();
                mTempRect.set(0, j, 0, j);
            } else
            {
                int i1;
                if((mFirstPosition + getChildCount()) - 1 < mItemCount)
                    j = 1;
                else
                    j = 0;
                i1 = getHeight();
                k = mListPadding.bottom;
                if(j != 0)
                    j = getArrowScrollPreviewLength();
                else
                    j = 0;
                j = i1 - k - j;
                if(view != null && view.getBottom() < j)
                    j = view.getBottom();
                mTempRect.set(0, j, 0, j);
            }
            view = FocusFinder.getInstance().findNextFocusFromRect(this, mTempRect, i);
        }
        do
        {
            if(view != null)
            {
                int j = positionOfNewFocus(view);
                if(mSelectedPosition != -1 && j != mSelectedPosition)
                {
                    for(int k = lookForSelectablePositionOnScreen(i); k != -1 && (i == 130 && k < j || i == 33 && k > j);)
                        return null;

                }
                int l = amountToScrollToNewFocus(i, view, j);
                int j1 = getMaxScrollAmount();
                if(l < j1)
                {
                    view.requestFocus(i);
                    mArrowScrollFocusResult.populate(j, l);
                    return mArrowScrollFocusResult;
                }
                if(distanceToView(view) < j1)
                {
                    view.requestFocus(i);
                    mArrowScrollFocusResult.populate(j, j1);
                    return mArrowScrollFocusResult;
                }
            }
            return null;
        } while(true);
    }

    private boolean arrowScrollImpl(int i)
    {
        if(getChildCount() <= 0)
            return false;
        View view = getSelectedView();
        int j = mSelectedPosition;
        int k = nextSelectedPositionForDirection(view, j, i);
        int l = amountToScroll(i, k);
        Object obj;
        boolean flag;
        View view2;
        if(mItemsCanFocus)
            obj = arrowScrollFocused(i);
        else
            obj = null;
        if(obj != null)
        {
            k = ((ArrowScrollFocusResult) (obj)).getSelectedPosition();
            l = ((ArrowScrollFocusResult) (obj)).getAmountToScroll();
        }
        if(obj != null)
            flag = true;
        else
            flag = false;
        view2 = view;
        if(k != -1)
        {
            boolean flag1;
            if(obj != null)
                flag1 = true;
            else
                flag1 = false;
            handleNewSelectionChange(view, i, k, flag1);
            setSelectedPositionInt(k);
            setNextSelectedPositionInt(k);
            view2 = getSelectedView();
            j = k;
            if(mItemsCanFocus && obj == null)
            {
                View view1 = getFocusedChild();
                if(view1 != null)
                    view1.clearFocus();
            }
            flag = true;
            checkSelectionChanged();
        }
        if(l > 0)
        {
            if(i != 33)
                l = -l;
            scrollListItemsBy(l);
            flag = true;
        }
        if(mItemsCanFocus && obj == null && view2 != null && view2.hasFocus())
        {
            obj = view2.findFocus();
            if(obj != null && (!isViewAncestorOf(((View) (obj)), this) || distanceToView(((View) (obj))) > 0))
                ((View) (obj)).clearFocus();
        }
        obj = view2;
        if(k == -1)
        {
            obj = view2;
            if(view2 != null)
            {
                obj = view2;
                if(isViewAncestorOf(view2, this) ^ true)
                {
                    obj = null;
                    hideSelector();
                    mResurrectToPosition = -1;
                }
            }
        }
        if(flag)
        {
            if(obj != null)
            {
                positionSelectorLikeFocus(j, ((View) (obj)));
                mSelectedTop = ((View) (obj)).getTop();
            }
            if(!awakenScrollBars())
                invalidate();
            invokeOnItemScrollListener();
            return true;
        } else
        {
            return false;
        }
    }

    private void clearRecycledState(ArrayList arraylist)
    {
        if(arraylist != null)
        {
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
            {
                AbsListView.LayoutParams layoutparams = (AbsListView.LayoutParams)((FixedViewInfo)arraylist.get(j)).view.getLayoutParams();
                if(layoutparams != null)
                    layoutparams.recycledHeaderFooter = false;
            }

        }
    }

    private boolean commonKey(int i, int j, KeyEvent keyevent)
    {
        boolean flag;
        int k;
        boolean flag1;
        int l;
        if(mAdapter == null || isAttachedToWindow() ^ true)
            return false;
        if(mDataChanged)
            layoutChildren();
        flag = false;
        k = keyevent.getAction();
        flag1 = flag;
        if(KeyEvent.isConfirmKey(i))
        {
            flag1 = flag;
            if(keyevent.hasNoModifiers())
            {
                flag1 = flag;
                if(k != 1)
                {
                    flag = resurrectSelectionIfNeeded();
                    flag1 = flag;
                    if(!flag)
                    {
                        flag1 = flag;
                        if(keyevent.getRepeatCount() == 0)
                        {
                            flag1 = flag;
                            if(getChildCount() > 0)
                            {
                                keyPressed();
                                flag1 = true;
                            }
                        }
                    }
                }
            }
        }
        flag = flag1;
        l = j;
        if(flag1) goto _L2; else goto _L1
_L1:
        flag = flag1;
        l = j;
        if(k == 1) goto _L2; else goto _L3
_L3:
        i;
        JVM INSTR lookupswitch 9: default 224
    //                   19: 238
    //                   20: 350
    //                   21: 464
    //                   22: 492
    //                   61: 775
    //                   92: 520
    //                   93: 602
    //                   122: 686
    //                   123: 730;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L4:
        l = j;
        flag = flag1;
_L2:
        if(flag)
            return true;
        break; /* Loop/switch isn't completed */
_L5:
        if(keyevent.hasNoModifiers())
        {
            flag1 = resurrectSelectionIfNeeded();
            flag = flag1;
            l = j;
            if(flag1)
                continue; /* Loop/switch isn't completed */
            do
            {
                int i1 = j;
                j = i1 - 1;
                flag = flag1;
                l = j;
                if(i1 <= 0)
                    continue; /* Loop/switch isn't completed */
                flag = flag1;
                l = j;
                if(!arrowScroll(33))
                    continue; /* Loop/switch isn't completed */
                flag1 = true;
            } while(true);
        }
        flag = flag1;
        l = j;
        if(keyevent.hasModifiers(2))
            if(!resurrectSelectionIfNeeded())
            {
                flag = fullScroll(33);
                l = j;
            } else
            {
                flag = true;
                l = j;
            }
        continue; /* Loop/switch isn't completed */
_L6:
        if(keyevent.hasNoModifiers())
        {
            flag1 = resurrectSelectionIfNeeded();
            flag = flag1;
            l = j;
            if(flag1)
                continue; /* Loop/switch isn't completed */
            do
            {
                int j1 = j;
                j = j1 - 1;
                flag = flag1;
                l = j;
                if(j1 <= 0)
                    continue; /* Loop/switch isn't completed */
                flag = flag1;
                l = j;
                if(!arrowScroll(130))
                    continue; /* Loop/switch isn't completed */
                flag1 = true;
            } while(true);
        }
        flag = flag1;
        l = j;
        if(keyevent.hasModifiers(2))
            if(!resurrectSelectionIfNeeded())
            {
                flag = fullScroll(130);
                l = j;
            } else
            {
                flag = true;
                l = j;
            }
        continue; /* Loop/switch isn't completed */
_L7:
        flag = flag1;
        l = j;
        if(keyevent.hasNoModifiers())
        {
            flag = handleHorizontalFocusWithinListItem(17);
            l = j;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        flag = flag1;
        l = j;
        if(keyevent.hasNoModifiers())
        {
            flag = handleHorizontalFocusWithinListItem(66);
            l = j;
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if(keyevent.hasNoModifiers())
        {
            if(!resurrectSelectionIfNeeded())
            {
                flag = pageScroll(33);
                l = j;
            } else
            {
                flag = true;
                l = j;
            }
        } else
        {
            flag = flag1;
            l = j;
            if(keyevent.hasModifiers(2))
                if(!resurrectSelectionIfNeeded())
                {
                    flag = fullScroll(33);
                    l = j;
                } else
                {
                    flag = true;
                    l = j;
                }
        }
        continue; /* Loop/switch isn't completed */
_L11:
        if(keyevent.hasNoModifiers())
        {
            if(!resurrectSelectionIfNeeded())
            {
                flag = pageScroll(130);
                l = j;
            } else
            {
                flag = true;
                l = j;
            }
        } else
        {
            flag = flag1;
            l = j;
            if(keyevent.hasModifiers(2))
                if(!resurrectSelectionIfNeeded())
                {
                    flag = fullScroll(130);
                    l = j;
                } else
                {
                    flag = true;
                    l = j;
                }
        }
        continue; /* Loop/switch isn't completed */
_L12:
        flag = flag1;
        l = j;
        if(keyevent.hasNoModifiers())
            if(!resurrectSelectionIfNeeded())
            {
                flag = fullScroll(33);
                l = j;
            } else
            {
                flag = true;
                l = j;
            }
        continue; /* Loop/switch isn't completed */
_L13:
        flag = flag1;
        l = j;
        if(keyevent.hasNoModifiers())
            if(!resurrectSelectionIfNeeded())
            {
                flag = fullScroll(130);
                l = j;
            } else
            {
                flag = true;
                l = j;
            }
        continue; /* Loop/switch isn't completed */
_L9:
        if(keyevent.hasNoModifiers())
        {
            if(!resurrectSelectionIfNeeded())
            {
                flag = arrowScroll(130);
                l = j;
            } else
            {
                flag = true;
                l = j;
            }
        } else
        {
            flag = flag1;
            l = j;
            if(keyevent.hasModifiers(1))
                if(!resurrectSelectionIfNeeded())
                {
                    flag = arrowScroll(33);
                    l = j;
                } else
                {
                    flag = true;
                    l = j;
                }
        }
        if(true) goto _L2; else goto _L14
_L14:
        if(sendToTextFilter(i, l, keyevent))
            return true;
        switch(k)
        {
        default:
            return false;

        case 0: // '\0'
            return super.onKeyDown(i, keyevent);

        case 1: // '\001'
            return super.onKeyUp(i, keyevent);

        case 2: // '\002'
            return super.onKeyMultiple(i, l, keyevent);
        }
    }

    private void correctTooHigh(int i)
    {
        if((mFirstPosition + i) - 1 == mItemCount - 1 && i > 0)
        {
            i = getChildAt(i - 1).getBottom();
            int j = mBottom - mTop - mListPadding.bottom - i;
            View view = getChildAt(0);
            int k = view.getTop();
            if(j > 0 && (mFirstPosition > 0 || k < mListPadding.top))
            {
                i = j;
                if(mFirstPosition == 0)
                    i = Math.min(j, mListPadding.top - k);
                offsetChildrenTopAndBottom(i);
                if(mFirstPosition > 0)
                {
                    fillUp(mFirstPosition - 1, view.getTop() - mDividerHeight);
                    adjustViewsUpOrDown();
                }
            }
        }
    }

    private void correctTooLow(int i)
    {
        if(mFirstPosition != 0 || i <= 0) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        int l;
        View view;
        int i1;
        j = getChildAt(0).getTop();
        k = mListPadding.top;
        l = mBottom - mTop - mListPadding.bottom;
        k = j - k;
        view = getChildAt(i - 1);
        j = view.getBottom();
        i1 = (mFirstPosition + i) - 1;
        if(k <= 0) goto _L2; else goto _L3
_L3:
        if(i1 >= mItemCount - 1 && j <= l) goto _L5; else goto _L4
_L4:
        i = k;
        if(i1 == mItemCount - 1)
            i = Math.min(k, j - l);
        offsetChildrenTopAndBottom(-i);
        if(i1 < mItemCount - 1)
        {
            fillDown(i1 + 1, view.getBottom() + mDividerHeight);
            adjustViewsUpOrDown();
        }
_L2:
        return;
_L5:
        if(i1 == mItemCount - 1)
            adjustViewsUpOrDown();
        if(true) goto _L2; else goto _L6
_L6:
    }

    private int distanceToView(View view)
    {
        int i;
        int j;
        i = 0;
        view.getDrawingRect(mTempRect);
        offsetDescendantRectToMyCoords(view, mTempRect);
        j = mBottom - mTop - mListPadding.bottom;
        if(mTempRect.bottom >= mListPadding.top) goto _L2; else goto _L1
_L1:
        i = mListPadding.top - mTempRect.bottom;
_L4:
        return i;
_L2:
        if(mTempRect.top > j)
            i = mTempRect.top - j;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void fillAboveAndBelow(View view, int i)
    {
        int j = mDividerHeight;
        if(!mStackFromBottom)
        {
            fillUp(i - 1, view.getTop() - j);
            adjustViewsUpOrDown();
            fillDown(i + 1, view.getBottom() + j);
        } else
        {
            fillDown(i + 1, view.getBottom() + j);
            adjustViewsUpOrDown();
            fillUp(i - 1, view.getTop() - j);
        }
    }

    private View fillDown(int i, int j)
    {
        Object obj = null;
        int k = mBottom - mTop;
        int l = k;
        View view1 = obj;
        int i1 = i;
        int j1 = j;
        if((mGroupFlags & 0x22) == 34)
        {
            l = k - mListPadding.bottom;
            j1 = j;
            i1 = i;
            view1 = obj;
        }
        while(j1 < l && i1 < mItemCount) 
        {
            View view;
            boolean flag;
            if(i1 == mSelectedPosition)
                flag = true;
            else
                flag = false;
            view = makeAndAddView(i1, j1, true, mListPadding.left, flag);
            j1 = view.getBottom() + mDividerHeight;
            if(flag)
                view1 = view;
            i1++;
        }
        setVisibleRangeHint(mFirstPosition, (mFirstPosition + getChildCount()) - 1);
        return view1;
    }

    private View fillFromMiddle(int i, int j)
    {
        j -= i;
        int k = reconcileSelectedPosition();
        View view = makeAndAddView(k, i, true, mListPadding.left, true);
        mFirstPosition = k;
        i = view.getMeasuredHeight();
        if(i <= j)
            view.offsetTopAndBottom((j - i) / 2);
        fillAboveAndBelow(view, k);
        if(!mStackFromBottom)
            correctTooHigh(getChildCount());
        else
            correctTooLow(getChildCount());
        return view;
    }

    private View fillFromSelection(int i, int j, int k)
    {
        int l = getVerticalFadingEdgeLength();
        int i1 = mSelectedPosition;
        j = getTopSelectionPixel(j, l, i1);
        k = getBottomSelectionPixel(k, l, i1);
        View view = makeAndAddView(i1, i, true, mListPadding.left, true);
        if(view.getBottom() > k)
            view.offsetTopAndBottom(-Math.min(view.getTop() - j, view.getBottom() - k));
        else
        if(view.getTop() < j)
            view.offsetTopAndBottom(Math.min(j - view.getTop(), k - view.getBottom()));
        fillAboveAndBelow(view, i1);
        if(!mStackFromBottom)
            correctTooHigh(getChildCount());
        else
            correctTooLow(getChildCount());
        return view;
    }

    private View fillFromTop(int i)
    {
        mFirstPosition = Math.min(mFirstPosition, mSelectedPosition);
        mFirstPosition = Math.min(mFirstPosition, mItemCount - 1);
        if(mFirstPosition < 0)
            mFirstPosition = 0;
        return fillDown(mFirstPosition, i);
    }

    private View fillSpecific(int i, int j)
    {
        View view;
        View view5;
        View view6;
        boolean flag;
        View view1;
        View view3;
        if(i == mSelectedPosition)
            flag = true;
        else
            flag = false;
        view = makeAndAddView(i, j, true, mListPadding.left, flag);
        mFirstPosition = i;
        j = mDividerHeight;
        if(mStackFromBottom) goto _L2; else goto _L1
_L1:
        view1 = fillUp(i - 1, view.getTop() - j);
        adjustViewsUpOrDown();
        view3 = fillDown(i + 1, view.getBottom() + j);
        i = getChildCount();
        view5 = view1;
        view6 = view3;
        if(i > 0)
        {
            correctTooHigh(i);
            view6 = view3;
            view5 = view1;
        }
_L4:
        if(flag)
            return view;
        break; /* Loop/switch isn't completed */
_L2:
        View view4 = fillDown(i + 1, view.getBottom() + j);
        adjustViewsUpOrDown();
        View view2 = fillUp(i - 1, view.getTop() - j);
        i = getChildCount();
        view5 = view2;
        view6 = view4;
        if(i > 0)
        {
            correctTooLow(i);
            view5 = view2;
            view6 = view4;
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(view5 != null)
            return view5;
        else
            return view6;
    }

    private View fillUp(int i, int j)
    {
        Object obj = null;
        int k = 0;
        View view1 = obj;
        int l = i;
        int i1 = j;
        if((mGroupFlags & 0x22) == 34)
        {
            k = mListPadding.top;
            i1 = j;
            l = i;
            view1 = obj;
        }
        while(i1 > k && l >= 0) 
        {
            View view;
            boolean flag;
            if(l == mSelectedPosition)
                flag = true;
            else
                flag = false;
            view = makeAndAddView(l, i1, false, mListPadding.left, flag);
            i1 = view.getTop() - mDividerHeight;
            if(flag)
                view1 = view;
            l--;
        }
        mFirstPosition = l + 1;
        setVisibleRangeHint(mFirstPosition, (mFirstPosition + getChildCount()) - 1);
        return view1;
    }

    private int getArrowScrollPreviewLength()
    {
        return Math.max(2, getVerticalFadingEdgeLength());
    }

    private int getBottomSelectionPixel(int i, int j, int k)
    {
        int l = i;
        if(k != mItemCount - 1)
            l = i - j;
        return l;
    }

    private int getTopSelectionPixel(int i, int j, int k)
    {
        int l = i;
        if(k > 0)
            l = i + j;
        return l;
    }

    private boolean handleHorizontalFocusWithinListItem(int i)
    {
        if(i != 17 && i != 66)
            throw new IllegalArgumentException("direction must be one of {View.FOCUS_LEFT, View.FOCUS_RIGHT}");
        int j = getChildCount();
        if(mItemsCanFocus && j > 0 && mSelectedPosition != -1)
        {
            Object obj = getSelectedView();
            if(obj != null && ((View) (obj)).hasFocus() && (obj instanceof ViewGroup))
            {
                View view = ((View) (obj)).findFocus();
                View view1 = FocusFinder.getInstance().findNextFocus((ViewGroup)obj, view, i);
                if(view1 != null)
                {
                    obj = mTempRect;
                    if(view != null)
                    {
                        view.getFocusedRect(((Rect) (obj)));
                        offsetDescendantRectToMyCoords(view, ((Rect) (obj)));
                        offsetRectIntoDescendantCoords(view1, ((Rect) (obj)));
                    } else
                    {
                        obj = null;
                    }
                    if(view1.requestFocus(i, ((Rect) (obj))))
                        return true;
                }
                obj = FocusFinder.getInstance().findNextFocus((ViewGroup)getRootView(), view, i);
                if(obj != null)
                    return isViewAncestorOf(((View) (obj)), this);
            }
        }
        return false;
    }

    private void handleNewSelectionChange(View view, int i, int j, boolean flag)
    {
        boolean flag1 = false;
        if(j == -1)
            throw new IllegalArgumentException("newSelectedPosition needs to be valid");
        boolean flag2 = false;
        int k = mSelectedPosition - mFirstPosition;
        j -= mFirstPosition;
        View view1;
        if(i == 33)
        {
            int l = j;
            i = k;
            view1 = getChildAt(j);
            flag2 = true;
            j = l;
        } else
        {
            i = j;
            view1 = view;
            view = getChildAt(j);
            j = k;
        }
        k = getChildCount();
        if(view1 != null)
        {
            boolean flag3;
            if(!flag)
                flag3 = flag2;
            else
                flag3 = false;
            view1.setSelected(flag3);
            measureAndAdjustDown(view1, j, k);
        }
        if(view != null)
        {
            flag3 = flag1;
            if(!flag)
                flag3 = flag2 ^ true;
            view.setSelected(flag3);
            measureAndAdjustDown(view, i, k);
        }
    }

    private boolean isDirectChildHeaderOrFooter(View view)
    {
        ArrayList arraylist = mHeaderViewInfos;
        int i = arraylist.size();
        for(int j = 0; j < i; j++)
            if(view == ((FixedViewInfo)arraylist.get(j)).view)
                return true;

        arraylist = mFooterViewInfos;
        i = arraylist.size();
        for(int k = 0; k < i; k++)
            if(view == ((FixedViewInfo)arraylist.get(k)).view)
                return true;

        return false;
    }

    private boolean isViewAncestorOf(View view, View view1)
    {
        if(view == view1)
            return true;
        view = view.getParent();
        boolean flag;
        if(view instanceof ViewGroup)
            flag = isViewAncestorOf((View)view, view1);
        else
            flag = false;
        return flag;
    }

    private int lookForSelectablePositionOnScreen(int i)
    {
        int j = mFirstPosition;
        if(i == 130)
        {
            int k;
            if(mSelectedPosition != -1)
                k = mSelectedPosition + 1;
            else
                k = j;
            if(k >= mAdapter.getCount())
                return -1;
            i = k;
            if(k < j)
                i = j;
            k = getLastVisiblePosition();
            ListAdapter listadapter = getAdapter();
            for(; i <= k; i++)
                if(listadapter.isEnabled(i) && getChildAt(i - j).getVisibility() == 0)
                    return i;

        } else
        {
            int i1 = (getChildCount() + j) - 1;
            if(mSelectedPosition != -1)
                i = mSelectedPosition - 1;
            else
                i = (getChildCount() + j) - 1;
            if(i < 0 || i >= mAdapter.getCount())
                return -1;
            int l = i;
            if(i > i1)
                l = i1;
            ListAdapter listadapter1 = getAdapter();
            for(; l >= j; l--)
                if(listadapter1.isEnabled(l) && getChildAt(l - j).getVisibility() == 0)
                    return l;

        }
        return -1;
    }

    private View makeAndAddView(int i, int j, boolean flag, int k, boolean flag1)
    {
        if(!mDataChanged)
        {
            View view = mRecycler.getActiveView(i);
            if(view != null)
            {
                setupChild(view, i, j, flag, k, flag1, true);
                return view;
            }
        }
        View view1 = obtainView(i, mIsScrap);
        setupChild(view1, i, j, flag, k, flag1, mIsScrap[0]);
        return view1;
    }

    private void measureAndAdjustDown(View view, int i, int j)
    {
        int k = view.getHeight();
        measureItem(view);
        if(view.getMeasuredHeight() != k)
        {
            relayoutMeasuredItem(view);
            int l = view.getMeasuredHeight();
            for(i++; i < j; i++)
                getChildAt(i).offsetTopAndBottom(l - k);

        }
    }

    private void measureItem(View view)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        android.view.ViewGroup.LayoutParams layoutparams1 = layoutparams;
        if(layoutparams == null)
            layoutparams1 = new android.view.ViewGroup.LayoutParams(-1, -2);
        int i = ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, mListPadding.left + mListPadding.right, layoutparams1.width);
        int j = layoutparams1.height;
        if(j > 0)
            j = android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000);
        else
            j = android.view.View.MeasureSpec.makeSafeMeasureSpec(getMeasuredHeight(), 0);
        view.measure(i, j);
    }

    private void measureScrapChild(View view, int i, int j, int k)
    {
        AbsListView.LayoutParams layoutparams = (AbsListView.LayoutParams)view.getLayoutParams();
        AbsListView.LayoutParams layoutparams1 = layoutparams;
        if(layoutparams == null)
        {
            layoutparams1 = (AbsListView.LayoutParams)generateDefaultLayoutParams();
            view.setLayoutParams(layoutparams1);
        }
        layoutparams1.viewType = mAdapter.getItemViewType(i);
        layoutparams1.isEnabled = mAdapter.isEnabled(i);
        layoutparams1.forceAdd = true;
        j = ViewGroup.getChildMeasureSpec(j, mListPadding.left + mListPadding.right, layoutparams1.width);
        i = layoutparams1.height;
        if(i > 0)
            i = android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000);
        else
            i = android.view.View.MeasureSpec.makeSafeMeasureSpec(k, 0);
        view.measure(j, i);
        view.forceLayout();
    }

    private View moveSelection(View view, View view1, int i, int j, int k)
    {
        int l = getVerticalFadingEdgeLength();
        int i1 = mSelectedPosition;
        int j1 = getTopSelectionPixel(j, l, i1);
        l = getBottomSelectionPixel(j, l, i1);
        if(i > 0)
        {
            view1 = makeAndAddView(i1 - 1, view.getTop(), true, mListPadding.left, false);
            i = mDividerHeight;
            view = makeAndAddView(i1, view1.getBottom() + i, true, mListPadding.left, true);
            if(view.getBottom() > l)
            {
                int k1 = view.getTop();
                i1 = view.getBottom();
                j = (k - j) / 2;
                j = Math.min(Math.min(k1 - j1, i1 - l), j);
                view1.offsetTopAndBottom(-j);
                view.offsetTopAndBottom(-j);
            }
            if(!mStackFromBottom)
            {
                fillUp(mSelectedPosition - 2, view.getTop() - i);
                adjustViewsUpOrDown();
                fillDown(mSelectedPosition + 1, view.getBottom() + i);
            } else
            {
                fillDown(mSelectedPosition + 1, view.getBottom() + i);
                adjustViewsUpOrDown();
                fillUp(mSelectedPosition - 2, view.getTop() - i);
            }
        } else
        if(i < 0)
        {
            if(view1 != null)
                view = makeAndAddView(i1, view1.getTop(), true, mListPadding.left, true);
            else
                view = makeAndAddView(i1, view.getTop(), false, mListPadding.left, true);
            if(view.getTop() < j1)
            {
                i = view.getTop();
                int l1 = view.getBottom();
                j = (k - j) / 2;
                view.offsetTopAndBottom(Math.min(Math.min(j1 - i, l - l1), j));
            }
            fillAboveAndBelow(view, i1);
        } else
        {
            i = view.getTop();
            view = makeAndAddView(i1, i, true, mListPadding.left, true);
            if(i < j && view.getBottom() < j + 20)
                view.offsetTopAndBottom(j - view.getTop());
            fillAboveAndBelow(view, i1);
        }
        return view;
    }

    private final int nextSelectedPositionForDirection(View view, int i, int j)
    {
        boolean flag = false;
        if(j != 130) goto _L2; else goto _L1
_L1:
        int k;
        int j1;
        k = getHeight();
        j1 = mListPadding.bottom;
        if(view == null || view.getBottom() > k - j1) goto _L4; else goto _L3
_L3:
        if(i != -1 && i >= mFirstPosition)
            i++;
        else
            i = mFirstPosition;
_L6:
        if(i < 0 || i >= mAdapter.getCount())
            return -1;
        break; /* Loop/switch isn't completed */
_L4:
        return -1;
_L2:
        int l = mListPadding.top;
        if(view != null && view.getTop() >= l)
        {
            int i1 = (mFirstPosition + getChildCount()) - 1;
            if(i != -1 && i <= i1)
                i--;
            else
                i = i1;
        } else
        {
            return -1;
        }
        if(true) goto _L6; else goto _L5
_L5:
        if(j == 130)
            flag = true;
        return lookForSelectablePosition(i, flag);
    }

    private int positionOfNewFocus(View view)
    {
        int i = getChildCount();
        for(int j = 0; j < i; j++)
            if(isViewAncestorOf(view, getChildAt(j)))
                return mFirstPosition + j;

        throw new IllegalArgumentException("newFocus is not a child of any of the children of the list!");
    }

    private void relayoutMeasuredItem(View view)
    {
        int i = view.getMeasuredWidth();
        int j = view.getMeasuredHeight();
        int k = mListPadding.left;
        int l = view.getTop();
        view.layout(k, l, k + i, l + j);
    }

    private void removeFixedViewInfo(View view, ArrayList arraylist)
    {
        int i = arraylist.size();
        int j = 0;
        do
        {
label0:
            {
                if(j < i)
                {
                    if(((FixedViewInfo)arraylist.get(j)).view != view)
                        break label0;
                    arraylist.remove(j);
                }
                return;
            }
            j++;
        } while(true);
    }

    private void removeUnusedFixedViews(List list)
    {
        if(list == null)
            return;
        for(int i = list.size() - 1; i >= 0; i--)
        {
            View view = ((FixedViewInfo)list.get(i)).view;
            AbsListView.LayoutParams layoutparams = (AbsListView.LayoutParams)view.getLayoutParams();
            if(view.getParent() == null && layoutparams != null && layoutparams.recycledHeaderFooter)
            {
                removeDetachedView(view, false);
                layoutparams.recycledHeaderFooter = false;
            }
        }

    }

    private void scrollListItemsBy(int i)
    {
        offsetChildrenTopAndBottom(i);
        int j = getHeight() - mListPadding.bottom;
        int k = mListPadding.top;
        AbsListView.RecycleBin recyclebin = mRecycler;
        if(i < 0)
        {
            i = getChildCount();
            View view = getChildAt(i - 1);
            do
            {
                if(view.getBottom() >= j)
                    break;
                int l = (mFirstPosition + i) - 1;
                if(l >= mItemCount - 1)
                    break;
                view = addViewBelow(view, l);
                i++;
            } while(true);
            if(view.getBottom() < j)
                offsetChildrenTopAndBottom(j - view.getBottom());
            for(View view1 = getChildAt(0); view1.getBottom() < k;)
            {
                if(recyclebin.shouldRecycleViewType(((AbsListView.LayoutParams)view1.getLayoutParams()).viewType))
                    recyclebin.addScrapView(view1, mFirstPosition);
                detachViewFromParent(view1);
                view1 = getChildAt(0);
                mFirstPosition = mFirstPosition + 1;
            }

        } else
        {
            View view2;
            for(view2 = getChildAt(0); view2.getTop() > k && mFirstPosition > 0; mFirstPosition = mFirstPosition - 1)
                view2 = addViewAbove(view2, mFirstPosition);

            if(view2.getTop() > k)
                offsetChildrenTopAndBottom(k - view2.getTop());
            i = getChildCount() - 1;
            for(View view3 = getChildAt(i); view3.getTop() > j; view3 = getChildAt(i))
            {
                if(recyclebin.shouldRecycleViewType(((AbsListView.LayoutParams)view3.getLayoutParams()).viewType))
                    recyclebin.addScrapView(view3, mFirstPosition + i);
                detachViewFromParent(view3);
                i--;
            }

        }
        recyclebin.fullyDetachScrapViews();
        removeUnusedFixedViews(mHeaderViewInfos);
        removeUnusedFixedViews(mFooterViewInfos);
    }

    private void setupChild(View view, int i, int j, boolean flag, int k, boolean flag1, boolean flag2)
    {
        Trace.traceBegin(8L, "setupListItem");
        boolean flag3;
        int l;
        int i1;
        boolean flag4;
        AbsListView.LayoutParams layoutparams;
        AbsListView.LayoutParams layoutparams1;
        if(flag1)
            flag3 = shouldShowSelector();
        else
            flag3 = false;
        if(flag3 != view.isSelected())
            l = 1;
        else
            l = 0;
        i1 = mTouchMode;
        if(i1 > 0 && i1 < 3)
        {
            if(mMotionPosition == i)
                flag1 = true;
            else
                flag1 = false;
        } else
        {
            flag1 = false;
        }
        if(flag1 != view.isPressed())
            i1 = 1;
        else
            i1 = 0;
        if(flag2 && l == 0)
            flag4 = view.isLayoutRequested();
        else
            flag4 = true;
        layoutparams = (AbsListView.LayoutParams)view.getLayoutParams();
        layoutparams1 = layoutparams;
        if(layoutparams == null)
            layoutparams1 = (AbsListView.LayoutParams)generateDefaultLayoutParams();
        layoutparams1.viewType = mAdapter.getItemViewType(i);
        layoutparams1.isEnabled = mAdapter.isEnabled(i);
        if(l != 0)
            view.setSelected(flag3);
        if(i1 != 0)
            view.setPressed(flag1);
        if(mChoiceMode != 0 && mCheckStates != null)
            if(view instanceof Checkable)
                ((Checkable)view).setChecked(mCheckStates.get(i));
            else
            if(getContext().getApplicationInfo().targetSdkVersion >= 11)
                view.setActivated(mCheckStates.get(i));
        if(flag2 && layoutparams1.forceAdd ^ true || layoutparams1.recycledHeaderFooter && layoutparams1.viewType == -2)
        {
            if(flag)
                l = -1;
            else
                l = 0;
            attachViewToParent(view, l, layoutparams1);
            if(flag2 && ((AbsListView.LayoutParams)view.getLayoutParams()).scrappedFromPosition != i)
                view.jumpDrawablesToCurrentState();
        } else
        {
            layoutparams1.forceAdd = false;
            if(layoutparams1.viewType == -2)
                layoutparams1.recycledHeaderFooter = true;
            if(flag)
                i = -1;
            else
                i = 0;
            addViewInLayout(view, i, layoutparams1, true);
            view.resolveRtlPropertiesIfNeeded();
        }
        if(flag4)
        {
            l = ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, mListPadding.left + mListPadding.right, layoutparams1.width);
            i = layoutparams1.height;
            if(i > 0)
                i = android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000);
            else
                i = android.view.View.MeasureSpec.makeSafeMeasureSpec(getMeasuredHeight(), 0);
            view.measure(l, i);
        } else
        {
            cleanupLayoutState(view);
        }
        l = view.getMeasuredWidth();
        i1 = view.getMeasuredHeight();
        if(flag)
            i = j;
        else
            i = j - i1;
        if(flag4)
        {
            view.layout(k, i, k + l, i + i1);
        } else
        {
            view.offsetLeftAndRight(k - view.getLeft());
            view.offsetTopAndBottom(i - view.getTop());
        }
        if(mCachingStarted && view.isDrawingCacheEnabled() ^ true)
            view.setDrawingCacheEnabled(true);
        Trace.traceEnd(8L);
    }

    private boolean shouldAdjustHeightForDivider(int i)
    {
        int j = mDividerHeight;
        Drawable drawable = mOverScrollHeader;
        Drawable drawable1 = mOverScrollFooter;
        boolean flag;
        boolean flag1;
        int l;
        if(drawable != null)
            flag1 = true;
        else
            flag1 = false;
        if(drawable1 != null)
            l = 1;
        else
            l = 0;
        if(j > 0 && mDivider != null)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            int j1;
            int k1;
            int l1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            if(isOpaque())
                flag = super.isOpaque() ^ true;
            else
                flag = false;
            j1 = mItemCount;
            k1 = getHeaderViewsCount();
            l1 = j1 - mFooterViewInfos.size();
            if(i < k1)
                flag2 = true;
            else
                flag2 = false;
            if(i >= l1)
                flag3 = true;
            else
                flag3 = false;
            flag4 = mHeaderDividersEnabled;
            flag5 = mFooterDividersEnabled;
            if((flag4 || flag2 ^ true) && (flag5 || flag3 ^ true))
            {
                ListAdapter listadapter = mAdapter;
                if(!mStackFromBottom)
                {
                    if(i == j1 - 1)
                        flag1 = true;
                    else
                        flag1 = false;
                    if(l == 0 || flag1 ^ true)
                    {
                        l = i + 1;
                        if(listadapter.isEnabled(i) && (flag4 || !flag2 && l >= k1) && (flag1 || listadapter.isEnabled(l) && (flag5 || !flag3 && l < l1)))
                            return true;
                        if(flag)
                            return true;
                    }
                } else
                {
                    int i1;
                    if(flag1)
                        i1 = 1;
                    else
                        i1 = 0;
                    if(i == i1)
                        i1 = 1;
                    else
                        i1 = 0;
                    if(i1 == 0)
                    {
                        int k = i - 1;
                        if(listadapter.isEnabled(i) && (flag4 || !flag2 && k >= k1) && (i1 != 0 || listadapter.isEnabled(k) && (flag5 || !flag3 && k < l1)))
                            return true;
                        if(flag)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean showingBottomFadingEdge()
    {
        boolean flag = true;
        int i = getChildCount();
        int j = getChildAt(i - 1).getBottom();
        int k = mFirstPosition;
        int l = mScrollY;
        int i1 = getHeight();
        int j1 = mListPadding.bottom;
        boolean flag1 = flag;
        if((k + i) - 1 >= mItemCount - 1)
            if(j < (l + i1) - j1)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    private boolean showingTopFadingEdge()
    {
        boolean flag = true;
        int i = mScrollY;
        int j = mListPadding.top;
        boolean flag1 = flag;
        if(mFirstPosition <= 0)
            if(getChildAt(0).getTop() > i + j)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public void addFooterView(View view)
    {
        addFooterView(view, null, true);
    }

    public void addFooterView(View view, Object obj, boolean flag)
    {
        if(view.getParent() != null && view.getParent() != this && Log.isLoggable("ListView", 5))
            Log.w("ListView", "The specified child already has a parent. You must call removeView() on the child's parent first.");
        FixedViewInfo fixedviewinfo = new FixedViewInfo();
        fixedviewinfo.view = view;
        fixedviewinfo.data = obj;
        fixedviewinfo.isSelectable = flag;
        mFooterViewInfos.add(fixedviewinfo);
        mAreAllItemsSelectable = mAreAllItemsSelectable & flag;
        if(mAdapter != null)
        {
            if(!(mAdapter instanceof HeaderViewListAdapter))
                wrapHeaderListAdapterInternal();
            if(mDataSetObserver != null)
                mDataSetObserver.onChanged();
        }
    }

    public void addHeaderView(View view)
    {
        addHeaderView(view, null, true);
    }

    public void addHeaderView(View view, Object obj, boolean flag)
    {
        if(view.getParent() != null && view.getParent() != this && Log.isLoggable("ListView", 5))
            Log.w("ListView", "The specified child already has a parent. You must call removeView() on the child's parent first.");
        FixedViewInfo fixedviewinfo = new FixedViewInfo();
        fixedviewinfo.view = view;
        fixedviewinfo.data = obj;
        fixedviewinfo.isSelectable = flag;
        mHeaderViewInfos.add(fixedviewinfo);
        mAreAllItemsSelectable = mAreAllItemsSelectable & flag;
        if(mAdapter != null)
        {
            if(!(mAdapter instanceof HeaderViewListAdapter))
                wrapHeaderListAdapterInternal();
            if(mDataSetObserver != null)
                mDataSetObserver.onChanged();
        }
    }

    public boolean areFooterDividersEnabled()
    {
        return mFooterDividersEnabled;
    }

    public boolean areHeaderDividersEnabled()
    {
        return mHeaderDividersEnabled;
    }

    boolean arrowScroll(int i)
    {
        boolean flag;
        mInLayout = true;
        flag = arrowScrollImpl(i);
        if(!flag)
            break MISSING_BLOCK_LABEL_23;
        playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        mInLayout = false;
        return flag;
        Exception exception;
        exception;
        mInLayout = false;
        throw exception;
    }

    protected boolean canAnimate()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(super.canAnimate())
        {
            flag1 = flag;
            if(mItemCount > 0)
                flag1 = true;
        }
        return flag1;
    }

    protected void dispatchDataSetObserverOnChangedInternal()
    {
        if(mDataSetObserver != null)
            mDataSetObserver.onChanged();
    }

    protected void dispatchDraw(Canvas canvas)
    {
        int i;
        Drawable drawable;
        Drawable drawable1;
        int j;
        boolean flag;
        int l;
        Rect rect;
        int i1;
        int j1;
        int k1;
        int i2;
        boolean flag1;
        boolean flag2;
        int j2;
        ListAdapter listadapter;
        boolean flag4;
        Paint paint;
        int k2;
        int j3;
        if(mCachingStarted)
            mCachingActive = true;
        i = mDividerHeight;
        drawable = mOverScrollHeader;
        drawable1 = mOverScrollFooter;
        boolean flag3;
        int l2;
        int k3;
        if(drawable != null)
            j = 1;
        else
            j = 0;
        if(drawable1 != null)
            flag = true;
        else
            flag = false;
        if(i > 0 && mDivider != null)
            l = 1;
        else
            l = 0;
        if(l == 0 && j == 0 && !flag) goto _L2; else goto _L1
_L1:
        rect = mTempRect;
        rect.left = mPaddingLeft;
        rect.right = mRight - mLeft - mPaddingRight;
        i1 = getChildCount();
        j1 = getHeaderViewsCount();
        k1 = mItemCount;
        i2 = k1 - mFooterViewInfos.size();
        flag1 = mHeaderDividersEnabled;
        flag2 = mFooterDividersEnabled;
        j2 = mFirstPosition;
        flag3 = mAreAllItemsSelectable;
        listadapter = mAdapter;
        if(isOpaque())
            flag4 = super.isOpaque() ^ true;
        else
            flag4 = false;
        if(flag4 && mDividerPaint == null && mIsCacheColorOpaque)
        {
            mDividerPaint = new Paint();
            mDividerPaint.setColor(getCacheColorHint());
        }
        paint = mDividerPaint;
        k2 = 0;
        l2 = 0;
        if((mGroupFlags & 0x22) == 34)
        {
            k2 = mListPadding.top;
            l2 = mListPadding.bottom;
        }
        j3 = (mBottom - mTop - l2) + mScrollY;
        if(mStackFromBottom) goto _L4; else goto _L3
_L3:
        l2 = 0;
        k2 = mScrollY;
        if(i1 <= 0 || k2 >= 0) goto _L6; else goto _L5
_L5:
        if(j == 0) goto _L8; else goto _L7
_L7:
        rect.bottom = 0;
        rect.top = k2;
        drawOverscrollHeader(canvas, drawable, rect);
          goto _L6
_L8:
        if(l != 0)
        {
            rect.bottom = 0;
            rect.top = -i;
            drawDivider(canvas, rect, -1);
        }
        continue; /* Loop/switch isn't completed */
_L6:
        k2 = 0;
        j = l2;
label0:
        do
        {
label1:
            {
                if(k2 >= i1)
                    break label0;
                k3 = j2 + k2;
                boolean flag5;
                boolean flag6;
                int i4;
                int j4;
                if(k3 < j1)
                    flag5 = true;
                else
                    flag5 = false;
                if(k3 >= i2)
                    flag6 = true;
                else
                    flag6 = false;
                if(!flag1)
                {
                    i4 = j;
                    if(!(flag5 ^ true))
                        break label1;
                }
                if(!flag2)
                {
                    i4 = j;
                    if(!(flag6 ^ true))
                        break label1;
                }
                j4 = getChildAt(k2).getBottom();
                if(k2 == i1 - 1)
                    j = 1;
                else
                    j = 0;
                i4 = j4;
                if(l != 0)
                {
                    i4 = j4;
                    if(j4 < j3)
                    {
                        int l4;
                        if(flag)
                            l4 = j;
                        else
                            l4 = 0;
                        i4 = j4;
                        if((l4 ^ 1) != 0)
                        {
                            i4 = k3 + 1;
                            if(listadapter.isEnabled(k3) && (flag1 || !flag5 && i4 >= j1) && (j != 0 || listadapter.isEnabled(i4) && (flag2 || !flag6 && i4 < i2)))
                            {
                                rect.top = j4;
                                rect.bottom = j4 + i;
                                drawDivider(canvas, rect, k2);
                                i4 = j4;
                            } else
                            {
                                i4 = j4;
                                if(flag4)
                                {
                                    rect.top = j4;
                                    rect.bottom = j4 + i;
                                    canvas.drawRect(rect, paint);
                                    i4 = j4;
                                }
                            }
                        }
                    }
                }
            }
            k2++;
            j = i4;
        } while(true);
        l = mBottom + mScrollY;
        if(flag && j2 + i1 == k1 && l > j)
        {
            rect.top = j;
            rect.bottom = l;
            drawOverscrollFooter(canvas, drawable1, rect);
        }
_L2:
        super.dispatchDraw(canvas);
        return;
_L4:
        int k4 = mScrollY;
        if(i1 > 0 && j != 0)
        {
            rect.top = k4;
            rect.bottom = getChildAt(0).getTop();
            drawOverscrollHeader(canvas, drawable, rect);
        }
        int i3;
        if(j != 0)
            j = 1;
        else
            j = 0;
        i3 = j;
        while(i3 < i1) 
        {
            int l1 = j2 + i3;
            int l3;
            boolean flag7;
            boolean flag8;
            if(l1 < j1)
                flag7 = true;
            else
                flag7 = false;
            if(l1 >= i2)
                flag8 = true;
            else
                flag8 = false;
            if(!flag1 && !(flag7 ^ true) || !flag2 && !(flag8 ^ true))
                continue;
            l3 = getChildAt(i3).getTop();
            if(l != 0 && l3 > k2)
            {
                boolean flag9;
                int i5;
                if(i3 == j)
                    flag9 = true;
                else
                    flag9 = false;
                i5 = l1 - 1;
                if(listadapter.isEnabled(l1) && (flag1 || !flag7 && i5 >= j1) && (flag9 || listadapter.isEnabled(i5) && (flag2 || !flag8 && i5 < i2)))
                {
                    rect.top = l3 - i;
                    rect.bottom = l3;
                    drawDivider(canvas, rect, i3 - 1);
                } else
                if(flag4)
                {
                    rect.top = l3 - i;
                    rect.bottom = l3;
                    canvas.drawRect(rect, paint);
                }
            }
            i3++;
        }
        if(i1 > 0 && k4 > 0)
            if(flag)
            {
                int k = mBottom;
                rect.top = k;
                rect.bottom = k + k4;
                drawOverscrollFooter(canvas, drawable1, rect);
            } else
            if(l != 0)
            {
                rect.top = j3;
                rect.bottom = j3 + i;
                drawDivider(canvas, rect, -1);
            }
        if(true) goto _L2; else goto _L9
_L9:
        if(true) goto _L6; else goto _L10
_L10:
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        boolean flag = super.dispatchKeyEvent(keyevent);
        boolean flag1 = flag;
        if(!flag)
        {
            flag1 = flag;
            if(getFocusedChild() != null)
            {
                flag1 = flag;
                if(keyevent.getAction() == 0)
                    flag1 = onKeyDown(keyevent.getKeyCode(), keyevent);
            }
        }
        return flag1;
    }

    protected boolean drawChild(Canvas canvas, View view, long l)
    {
        boolean flag = super.drawChild(canvas, view, l);
        if(mCachingActive && view.mCachingFailed)
            mCachingActive = false;
        return flag;
    }

    void drawDivider(Canvas canvas, Rect rect, int i)
    {
        Drawable drawable = mDivider;
        drawable.setBounds(rect);
        drawable.draw(canvas);
    }

    void drawOverscrollFooter(Canvas canvas, Drawable drawable, Rect rect)
    {
        int i = drawable.getMinimumHeight();
        canvas.save();
        canvas.clipRect(rect);
        if(rect.bottom - rect.top < i)
            rect.bottom = rect.top + i;
        drawable.setBounds(rect);
        drawable.draw(canvas);
        canvas.restore();
    }

    void drawOverscrollHeader(Canvas canvas, Drawable drawable, Rect rect)
    {
        int i = drawable.getMinimumHeight();
        canvas.save();
        canvas.clipRect(rect);
        if(rect.bottom - rect.top < i)
            rect.top = rect.bottom - i;
        drawable.setBounds(rect);
        drawable.draw(canvas);
        canvas.restore();
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("recycleOnMeasure", recycleOnMeasure());
    }

    void fillGap(boolean flag)
    {
        int i = getChildCount();
        if(flag)
        {
            int j = 0;
            if((mGroupFlags & 0x22) == 34)
                j = getListPaddingTop();
            if(i > 0)
                j = getChildAt(i - 1).getBottom() + mDividerHeight;
            fillDown(mFirstPosition + i, j);
            correctTooHigh(getChildCount());
        } else
        {
            int k = 0;
            if((mGroupFlags & 0x22) == 34)
                k = getListPaddingBottom();
            if(i > 0)
                k = getChildAt(0).getTop() - mDividerHeight;
            else
                k = getHeight() - k;
            fillUp(mFirstPosition - 1, k);
            correctTooLow(getChildCount());
        }
    }

    int findMotionRow(int i)
    {
        int j = getChildCount();
        if(j > 0)
            if(!mStackFromBottom)
            {
                for(int k = 0; k < j; k++)
                    if(i <= getChildAt(k).getBottom())
                        return mFirstPosition + k;

            } else
            {
                for(int l = j - 1; l >= 0; l--)
                    if(i >= getChildAt(l).getTop())
                        return mFirstPosition + l;

            }
        return -1;
    }

    View findViewByPredicateInHeadersOrFooters(ArrayList arraylist, Predicate predicate, View view)
    {
        if(arraylist != null)
        {
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
            {
                View view1 = ((FixedViewInfo)arraylist.get(j)).view;
                if(view1 == view || !(view1.isRootNamespace() ^ true))
                    continue;
                view1 = view1.findViewByPredicate(predicate);
                if(view1 != null)
                    return view1;
            }

        }
        return null;
    }

    protected View findViewByPredicateTraversal(Predicate predicate, View view)
    {
        View view1 = super.findViewByPredicateTraversal(predicate, view);
        Object obj = view1;
        if(view1 == null)
        {
            obj = findViewByPredicateInHeadersOrFooters(mHeaderViewInfos, predicate, view);
            if(obj != null)
                return ((View) (obj));
            predicate = findViewByPredicateInHeadersOrFooters(mFooterViewInfos, predicate, view);
            obj = predicate;
            if(predicate != null)
                return predicate;
        }
        return ((View) (obj));
    }

    View findViewInHeadersOrFooters(ArrayList arraylist, int i)
    {
        if(arraylist != null)
        {
            int j = arraylist.size();
            for(int k = 0; k < j; k++)
            {
                View view = ((FixedViewInfo)arraylist.get(k)).view;
                if(view.isRootNamespace())
                    continue;
                view = view.findViewById(i);
                if(view != null)
                    return view;
            }

        }
        return null;
    }

    protected View findViewTraversal(int i)
    {
        View view = super.findViewTraversal(i);
        View view2 = view;
        if(view == null)
        {
            view2 = findViewInHeadersOrFooters(mHeaderViewInfos, i);
            if(view2 != null)
                return view2;
            View view1 = findViewInHeadersOrFooters(mFooterViewInfos, i);
            view2 = view1;
            if(view1 != null)
                return view1;
        }
        return view2;
    }

    View findViewWithTagInHeadersOrFooters(ArrayList arraylist, Object obj)
    {
        if(arraylist != null)
        {
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
            {
                View view = ((FixedViewInfo)arraylist.get(j)).view;
                if(view.isRootNamespace())
                    continue;
                view = view.findViewWithTag(obj);
                if(view != null)
                    return view;
            }

        }
        return null;
    }

    protected View findViewWithTagTraversal(Object obj)
    {
        View view = super.findViewWithTagTraversal(obj);
        Object obj1 = view;
        if(view == null)
        {
            obj1 = findViewWithTagInHeadersOrFooters(mHeaderViewInfos, obj);
            if(obj1 != null)
                return ((View) (obj1));
            obj = findViewWithTagInHeadersOrFooters(mFooterViewInfos, obj);
            obj1 = obj;
            if(obj != null)
                return ((View) (obj));
        }
        return ((View) (obj1));
    }

    boolean fullScroll(int i)
    {
        boolean flag = false;
        if(i != 33) goto _L2; else goto _L1
_L1:
        boolean flag1;
        flag1 = flag;
        if(mSelectedPosition != 0)
        {
            i = lookForSelectablePositionAfter(mSelectedPosition, 0, true);
            if(i >= 0)
            {
                mLayoutMode = 1;
                setSelectionInt(i);
                invokeOnItemScrollListener();
            }
            flag1 = true;
        }
_L4:
        if(flag1 && awakenScrollBars() ^ true)
        {
            awakenScrollBars();
            invalidate();
        }
        return flag1;
_L2:
        flag1 = flag;
        if(i == 130)
        {
            i = mItemCount - 1;
            flag1 = flag;
            if(mSelectedPosition < i)
            {
                i = lookForSelectablePositionAfter(mSelectedPosition, i, false);
                if(i >= 0)
                {
                    mLayoutMode = 3;
                    setSelectionInt(i);
                    invokeOnItemScrollListener();
                }
                flag1 = true;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/ListView.getName();
    }

    public volatile Adapter getAdapter()
    {
        return getAdapter();
    }

    public ListAdapter getAdapter()
    {
        return mAdapter;
    }

    public long[] getCheckItemIds()
    {
        if(mAdapter != null && mAdapter.hasStableIds())
            return getCheckedItemIds();
        if(mChoiceMode != 0 && mCheckStates != null && mAdapter != null)
        {
            SparseBooleanArray sparsebooleanarray = mCheckStates;
            int i = sparsebooleanarray.size();
            long al1[] = new long[i];
            ListAdapter listadapter = mAdapter;
            int j = 0;
            int k = 0;
            for(; j < i; j++)
                if(sparsebooleanarray.valueAt(j))
                {
                    int l = k + 1;
                    al1[k] = listadapter.getItemId(sparsebooleanarray.keyAt(j));
                    k = l;
                }

            if(k == i)
            {
                return al1;
            } else
            {
                long al[] = new long[k];
                System.arraycopy(al1, 0, al, 0, k);
                return al;
            }
        } else
        {
            return new long[0];
        }
    }

    public Drawable getDivider()
    {
        return mDivider;
    }

    public int getDividerHeight()
    {
        return mDividerHeight;
    }

    public int getFooterViewsCount()
    {
        return mFooterViewInfos.size();
    }

    public int getHeaderViewsCount()
    {
        return mHeaderViewInfos.size();
    }

    int getHeightForPosition(int i)
    {
        int j = super.getHeightForPosition(i);
        if(shouldAdjustHeightForDivider(i))
            return mDividerHeight + j;
        else
            return j;
    }

    public boolean getItemsCanFocus()
    {
        return mItemsCanFocus;
    }

    public int getMaxScrollAmount()
    {
        return (int)((float)(mBottom - mTop) * 0.33F);
    }

    public Drawable getOverscrollFooter()
    {
        return mOverScrollFooter;
    }

    public Drawable getOverscrollHeader()
    {
        return mOverScrollHeader;
    }

    public boolean isOpaque()
    {
        boolean flag;
        if(!mCachingActive || !mIsCacheColorOpaque || !mDividerIsOpaque || !hasOpaqueScrollbars())
            flag = super.isOpaque();
        else
            flag = true;
        if(flag)
        {
            int i;
            View view;
            if(mListPadding != null)
                i = mListPadding.top;
            else
                i = mPaddingTop;
            view = getChildAt(0);
            if(view == null || view.getTop() > i)
                return false;
            int j = getHeight();
            if(mListPadding != null)
                i = mListPadding.bottom;
            else
                i = mPaddingBottom;
            view = getChildAt(getChildCount() - 1);
            if(view == null || view.getBottom() < j - i)
                return false;
        }
        return flag;
    }

    protected void layoutChildren()
    {
        boolean flag;
        flag = mBlockLayoutRequests;
        if(flag)
            return;
        mBlockLayoutRequests = true;
        super.layoutChildren();
        invalidate();
        if(mAdapter != null)
            break MISSING_BLOCK_LABEL_62;
        resetList();
        invokeOnItemScrollListener();
        if(mFocusSelector != null)
            mFocusSelector.onLayoutComplete();
        if(!flag)
            mBlockLayoutRequests = false;
        return;
        int i;
        int j;
        int k;
        i = mListPadding.top;
        j = mBottom - mTop - mListPadding.bottom;
        k = getChildCount();
        int l;
        int i1;
        View view;
        View view1;
        Object obj;
        AccessibilityNodeInfo accessibilitynodeinfo;
        Object obj1;
        Object obj2;
        int j1;
        View view2;
        l = 0;
        i1 = 0;
        view = null;
        view1 = null;
        obj = null;
        accessibilitynodeinfo = null;
        obj1 = view;
        obj2 = accessibilitynodeinfo;
        j1 = l;
        view2 = ((View) (obj));
        mLayoutMode;
        JVM INSTR tableswitch 1 5: default 168
    //                   1 251
    //                   2 305
    //                   3 251
    //                   4 251
    //                   5 251;
           goto _L1 _L2 _L3 _L2 _L2 _L2
_L1:
        l = mSelectedPosition - mFirstPosition;
        obj = view1;
        if(l < 0)
            break MISSING_BLOCK_LABEL_207;
        obj = view1;
        if(l >= k)
            break MISSING_BLOCK_LABEL_207;
        obj = getChildAt(l);
        view2 = getChildAt(0);
        j1 = i1;
        if(mNextSelectedPosition >= 0)
            j1 = mNextSelectedPosition - mSelectedPosition;
        obj2 = getChildAt(l + j1);
        obj1 = obj;
_L2:
        boolean flag1 = mDataChanged;
        if(!flag1) goto _L5; else goto _L4
_L4:
        handleDataChanged();
_L5:
        if(mItemCount != 0)
            break; /* Loop/switch isn't completed */
        resetList();
        invokeOnItemScrollListener();
        if(mFocusSelector != null)
            mFocusSelector.onLayoutComplete();
        if(!flag)
            mBlockLayoutRequests = false;
        return;
_L3:
        i1 = mNextSelectedPosition - mFirstPosition;
        obj1 = view;
        obj2 = accessibilitynodeinfo;
        j1 = l;
        view2 = ((View) (obj));
        if(i1 < 0)
            continue; /* Loop/switch isn't completed */
        obj1 = view;
        obj2 = accessibilitynodeinfo;
        j1 = l;
        view2 = ((View) (obj));
        if(i1 >= k)
            continue; /* Loop/switch isn't completed */
        obj2 = getChildAt(i1);
        obj1 = view;
        j1 = l;
        view2 = ((View) (obj));
        if(true) goto _L2; else goto _L6
_L6:
        if(mItemCount != mAdapter.getCount())
        {
            obj = JVM INSTR new #1108 <Class IllegalStateException>;
            obj2 = JVM INSTR new #1110 <Class StringBuilder>;
            ((StringBuilder) (obj2)).StringBuilder();
            ((IllegalStateException) (obj)).IllegalStateException(((StringBuilder) (obj2)).append("The content of the adapter has changed but ListView did not receive a notification. Make sure the content of your adapter is not modified from a background thread, but only from the UI thread. Make sure your adapter calls notifyDataSetChanged() when its content changes. [in ListView(").append(getId()).append(", ").append(getClass()).append(") with Adapter(").append(mAdapter.getClass()).append(")]").toString());
            throw obj;
        }
          goto _L7
        obj2;
        if(mFocusSelector != null)
            mFocusSelector.onLayoutComplete();
        if(!flag)
            mBlockLayoutRequests = false;
        throw obj2;
_L7:
        setSelectedPositionInt(mNextSelectedPosition);
        View view3;
        Object obj3;
        obj = null;
        view3 = null;
        view1 = null;
        obj3 = null;
        l = -1;
        ViewRootImpl viewrootimpl = getViewRootImpl();
        accessibilitynodeinfo = ((AccessibilityNodeInfo) (obj));
        view = view1;
        i1 = l;
        if(viewrootimpl == null)
            break MISSING_BLOCK_LABEL_670;
        View view4 = viewrootimpl.getAccessibilityFocusedHost();
        accessibilitynodeinfo = ((AccessibilityNodeInfo) (obj));
        view = view1;
        i1 = l;
        if(view4 == null)
            break MISSING_BLOCK_LABEL_670;
        View view5 = getAccessibilityFocusedChild(view4);
        accessibilitynodeinfo = ((AccessibilityNodeInfo) (obj));
        view = view1;
        i1 = l;
        if(view5 == null)
            break MISSING_BLOCK_LABEL_670;
        if(!flag1)
            break MISSING_BLOCK_LABEL_643;
        if(isDirectChildHeaderOrFooter(view5))
            break MISSING_BLOCK_LABEL_643;
        obj = view3;
        view1 = ((View) (obj3));
        if(!view5.hasTransientState())
            break MISSING_BLOCK_LABEL_654;
        obj = view3;
        view1 = ((View) (obj3));
        if(!mAdapterHasStableIds)
            break MISSING_BLOCK_LABEL_654;
        view1 = view4;
        obj = viewrootimpl.getAccessibilityFocusedVirtualView();
        i1 = getPositionForView(view5);
        view = view1;
        accessibilitynodeinfo = ((AccessibilityNodeInfo) (obj));
        obj = null;
        view4 = null;
        view1 = null;
        view3 = null;
        obj3 = getFocusedChild();
        if(obj3 == null) goto _L9; else goto _L8
_L8:
        if(!flag1)
            break MISSING_BLOCK_LABEL_730;
        if(isDirectChildHeaderOrFooter(((View) (obj3))) || ((View) (obj3)).hasTransientState())
            break MISSING_BLOCK_LABEL_730;
        obj = view4;
        view1 = view3;
        if(!mAdapterHasStableIds)
            break MISSING_BLOCK_LABEL_762;
        view3 = findFocus();
        obj = obj3;
        view1 = view3;
        if(view3 == null)
            break MISSING_BLOCK_LABEL_762;
        view3.dispatchStartTemporaryDetach();
        view1 = view3;
        obj = obj3;
        requestFocus();
_L9:
        int k1;
        k1 = mFirstPosition;
        obj3 = mRecycler;
        if(!flag1) goto _L11; else goto _L10
_L10:
        l = 0;
_L13:
        if(l >= k)
            break; /* Loop/switch isn't completed */
        ((AbsListView.RecycleBin) (obj3)).addScrapView(getChildAt(l), k1 + l);
        l++;
        if(true) goto _L13; else goto _L12
_L11:
        ((AbsListView.RecycleBin) (obj3)).fillActiveViews(k, k1);
_L12:
        detachAllViewsFromParent();
        ((AbsListView.RecycleBin) (obj3)).removeSkippedScrap();
        mLayoutMode;
        JVM INSTR tableswitch 1 6: default 876
    //                   1 1253
    //                   2 1184
    //                   3 1233
    //                   4 1272
    //                   5 1216
    //                   6 1344;
           goto _L14 _L15 _L16 _L17 _L18 _L19 _L20
_L14:
        if(k != 0) goto _L22; else goto _L21
_L21:
        if(mStackFromBottom) goto _L24; else goto _L23
_L23:
        setSelectedPositionInt(lookForSelectablePosition(0, true));
        obj2 = fillFromTop(i);
_L48:
        ((AbsListView.RecycleBin) (obj3)).scrapActiveViews();
        removeUnusedFixedViews(mHeaderViewInfos);
        removeUnusedFixedViews(mFooterViewInfos);
        if(obj2 == null) goto _L26; else goto _L25
_L25:
        if(!mItemsCanFocus || !hasFocus() || !(((View) (obj2)).hasFocus() ^ true)) goto _L28; else goto _L27
_L27:
        if(obj2 != obj || view1 == null) goto _L30; else goto _L29
_L29:
        if(view1.requestFocus()) goto _L31; else goto _L30
_L30:
        flag1 = ((View) (obj2)).requestFocus();
_L60:
        if(flag1) goto _L33; else goto _L32
_L32:
        obj = getFocusedChild();
        if(obj == null) goto _L35; else goto _L34
_L34:
        ((View) (obj)).clearFocus();
_L35:
        positionSelector(-1, ((View) (obj2)));
_L61:
        mSelectedTop = ((View) (obj2)).getTop();
_L67:
        if(viewrootimpl == null) goto _L37; else goto _L36
_L36:
        if(viewrootimpl.getAccessibilityFocusedHost() != null) goto _L37; else goto _L38
_L38:
        if(view == null) goto _L40; else goto _L39
_L39:
        if(!view.isAttachedToWindow()) goto _L40; else goto _L41
_L41:
        obj2 = view.getAccessibilityNodeProvider();
        if(accessibilitynodeinfo == null || obj2 == null) goto _L43; else goto _L42
_L42:
        ((AccessibilityNodeProvider) (obj2)).performAction(AccessibilityNodeInfo.getVirtualDescendantId(accessibilitynodeinfo.getSourceNodeId()), 64, null);
_L37:
        if(view1 == null) goto _L45; else goto _L44
_L44:
        if(view1.getWindowToken() != null)
            view1.dispatchFinishTemporaryDetach();
_L45:
        mLayoutMode = 0;
        mDataChanged = false;
        if(mPositionScrollAfterLayout != null)
        {
            post(mPositionScrollAfterLayout);
            mPositionScrollAfterLayout = null;
        }
        mNeedSync = false;
        setNextSelectedPositionInt(mSelectedPosition);
        updateScrollIndicators();
        if(mItemCount > 0)
            checkSelectionChanged();
        invokeOnItemScrollListener();
        if(mFocusSelector != null)
            mFocusSelector.onLayoutComplete();
        if(!flag)
            mBlockLayoutRequests = false;
        return;
_L16:
        if(obj2 == null) goto _L47; else goto _L46
_L46:
        obj2 = fillFromSelection(((View) (obj2)).getTop(), i, j);
          goto _L48
_L47:
        obj2 = fillFromMiddle(i, j);
          goto _L48
_L19:
        obj2 = fillSpecific(mSyncPosition, mSpecificTop);
          goto _L48
_L17:
        obj2 = fillUp(mItemCount - 1, j);
        adjustViewsUpOrDown();
          goto _L48
_L15:
        mFirstPosition = 0;
        obj2 = fillFromTop(i);
        adjustViewsUpOrDown();
          goto _L48
_L18:
        j1 = reconcileSelectedPosition();
        view2 = fillSpecific(j1, mSpecificTop);
        obj2 = view2;
        if(view2 != null) goto _L48; else goto _L49
_L49:
        obj2 = view2;
        if(mFocusSelector == null) goto _L48; else goto _L50
_L50:
        obj1 = mFocusSelector.setupFocusIfValid(j1);
        obj2 = view2;
        if(obj1 == null) goto _L48; else goto _L51
_L51:
        post(((Runnable) (obj1)));
        obj2 = view2;
          goto _L48
_L20:
        obj2 = moveSelection(((View) (obj1)), ((View) (obj2)), j1, i, j);
          goto _L48
_L24:
        setSelectedPositionInt(lookForSelectablePosition(mItemCount - 1, false));
        obj2 = fillUp(mItemCount - 1, j);
          goto _L48
_L22:
        if(mSelectedPosition < 0 || mSelectedPosition >= mItemCount) goto _L53; else goto _L52
_L52:
        j1 = mSelectedPosition;
        if(obj1 != null) goto _L55; else goto _L54
_L54:
        obj2 = fillSpecific(j1, i);
          goto _L48
_L55:
        i = ((View) (obj1)).getTop();
          goto _L54
_L53:
        if(mFirstPosition >= mItemCount) goto _L57; else goto _L56
_L56:
        j1 = mFirstPosition;
        if(view2 != null) goto _L59; else goto _L58
_L58:
        obj2 = fillSpecific(j1, i);
          goto _L48
_L59:
        i = view2.getTop();
          goto _L58
_L57:
        obj2 = fillSpecific(0, i);
          goto _L48
_L31:
        flag1 = true;
          goto _L60
_L33:
        ((View) (obj2)).setSelected(false);
        mSelectorRect.setEmpty();
          goto _L61
_L28:
        positionSelector(-1, ((View) (obj2)));
          goto _L61
_L26:
        if(mTouchMode != 1)
        {
            if(mTouchMode == 2)
                j1 = 1;
            else
                j1 = 0;
        } else
        {
            j1 = 1;
        }
        if(!j1) goto _L63; else goto _L62
_L62:
        obj2 = getChildAt(mMotionPosition - mFirstPosition);
        if(obj2 == null) goto _L65; else goto _L64
_L64:
        positionSelector(mMotionPosition, ((View) (obj2)));
_L65:
        if(!hasFocus() || view1 == null) goto _L67; else goto _L66
_L66:
        view1.requestFocus();
          goto _L67
_L63:
        if(mSelectorPosition == -1) goto _L69; else goto _L68
_L68:
        obj2 = getChildAt(mSelectorPosition - mFirstPosition);
        if(obj2 == null) goto _L65; else goto _L70
_L70:
        positionSelector(mSelectorPosition, ((View) (obj2)));
          goto _L65
_L69:
        mSelectedTop = 0;
        mSelectorRect.setEmpty();
          goto _L65
_L43:
        view.requestAccessibilityFocus();
          goto _L37
_L40:
        if(i1 == -1) goto _L37; else goto _L71
_L71:
        obj2 = getChildAt(MathUtils.constrain(i1 - mFirstPosition, 0, getChildCount() - 1));
        if(obj2 == null) goto _L37; else goto _L72
_L72:
        ((View) (obj2)).requestAccessibilityFocus();
          goto _L37
    }

    int lookForSelectablePosition(int i, boolean flag)
    {
        ListAdapter listadapter = mAdapter;
        if(listadapter == null || isInTouchMode())
            return -1;
        int j = listadapter.getCount();
        int k = i;
        if(!mAreAllItemsSelectable)
            if(flag)
            {
                i = Math.max(0, i);
                do
                {
                    k = i;
                    if(i >= j)
                        break;
                    k = i;
                    if(!(listadapter.isEnabled(i) ^ true))
                        break;
                    i++;
                } while(true);
            } else
            {
                i = Math.min(i, j - 1);
                do
                {
                    k = i;
                    if(i < 0)
                        break;
                    k = i;
                    if(!(listadapter.isEnabled(i) ^ true))
                        break;
                    i--;
                } while(true);
            }
        if(k < 0 || k >= j)
            return -1;
        else
            return k;
    }

    int lookForSelectablePositionAfter(int i, int j, boolean flag)
    {
        ListAdapter listadapter = mAdapter;
        if(listadapter == null || isInTouchMode())
            return -1;
        int k = lookForSelectablePosition(j, flag);
        if(k != -1)
            return k;
        int l = listadapter.getCount();
        k = MathUtils.constrain(i, -1, l - 1);
        if(flag)
        {
            for(i = Math.min(j - 1, l - 1); i > k && listadapter.isEnabled(i) ^ true; i--);
            j = i;
            if(i <= k)
                return -1;
        } else
        {
            for(i = Math.max(0, j + 1); i < k && listadapter.isEnabled(i) ^ true; i++);
            j = i;
            if(i >= k)
                return -1;
        }
        return j;
    }

    final int measureHeightOfChildren(int i, int j, int k, int l, int i1)
    {
        ListAdapter listadapter = mAdapter;
        if(listadapter == null)
            return mListPadding.top + mListPadding.bottom;
        int j1 = mListPadding.top + mListPadding.bottom;
        int k1 = mDividerHeight;
        int l1 = 0;
        int i2 = k;
        if(k == -1)
            i2 = listadapter.getCount() - 1;
        AbsListView.RecycleBin recyclebin = mRecycler;
        boolean flag = recycleOnMeasure();
        boolean aflag[] = mIsScrap;
        int j2 = j;
        k = j1;
        for(j = l1; j2 <= i2; j = l1)
        {
            View view = obtainView(j2, aflag);
            measureScrapChild(view, j2, i, l);
            l1 = k;
            if(j2 > 0)
                l1 = k + k1;
            if(flag && recyclebin.shouldRecycleViewType(((AbsListView.LayoutParams)view.getLayoutParams()).viewType))
                recyclebin.addScrapView(view, -1);
            k = l1 + view.getMeasuredHeight();
            if(k >= l)
            {
                if(i1 < 0 || j2 <= i1 || j <= 0 || k == l)
                    j = l;
                return j;
            }
            l1 = j;
            if(i1 >= 0)
            {
                l1 = j;
                if(j2 >= i1)
                    l1 = k;
            }
            j2++;
        }

        return k;
    }

    protected void onDetachedFromWindow()
    {
        if(mFocusSelector != null)
        {
            removeCallbacks(mFocusSelector);
            mFocusSelector = null;
        }
        super.onDetachedFromWindow();
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        int i = getChildCount();
        if(i > 0)
        {
            for(int j = 0; j < i; j++)
                addHeaderView(getChildAt(j));

            removeAllViews();
        }
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        ListAdapter listadapter = mAdapter;
        int j = -1;
        boolean flag1 = false;
        int l = 0;
        int i1 = ((flag1) ? 1 : 0);
        int j1 = j;
        if(listadapter != null)
        {
            i1 = ((flag1) ? 1 : 0);
            j1 = j;
            if(flag)
            {
                i1 = ((flag1) ? 1 : 0);
                j1 = j;
                if(rect != null)
                {
                    rect.offset(mScrollX, mScrollY);
                    if(listadapter.getCount() < getChildCount() + mFirstPosition)
                    {
                        mLayoutMode = 0;
                        layoutChildren();
                    }
                    Rect rect1 = mTempRect;
                    int k1 = 0x7fffffff;
                    int l1 = getChildCount();
                    int i2 = mFirstPosition;
                    int k = 0;
                    do
                    {
                        i1 = l;
                        j1 = j;
                        if(k >= l1)
                            break;
                        if(!listadapter.isEnabled(i2 + k))
                        {
                            i1 = k1;
                        } else
                        {
                            View view = getChildAt(k);
                            view.getDrawingRect(rect1);
                            offsetDescendantRectToMyCoords(view, rect1);
                            j1 = getDistance(rect, rect1, i);
                            i1 = k1;
                            if(j1 < k1)
                            {
                                i1 = j1;
                                j = k;
                                l = view.getTop();
                            }
                        }
                        k++;
                        k1 = i1;
                    } while(true);
                }
            }
        }
        if(j1 >= 0)
            setSelectionFromTop(mFirstPosition + j1, i1);
        else
            requestLayout();
    }

    public void onInitializeAccessibilityNodeInfoForItem(View view, int i, AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoForItem(view, i, accessibilitynodeinfo);
        view = (AbsListView.LayoutParams)view.getLayoutParams();
        boolean flag;
        if(view != null && ((AbsListView.LayoutParams) (view)).viewType == -2)
            flag = true;
        else
            flag = false;
        accessibilitynodeinfo.setCollectionItemInfo(android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(i, 1, 0, 1, flag, isItemChecked(i)));
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        int i = getCount();
        accessibilitynodeinfo.setCollectionInfo(android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(i, 1, false, getSelectionModeForAccessibility()));
        if(i > 0)
            accessibilitynodeinfo.addAction(android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION);
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        return commonKey(i, 1, keyevent);
    }

    public boolean onKeyMultiple(int i, int j, KeyEvent keyevent)
    {
        return commonKey(i, j, keyevent);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        return commonKey(i, 1, keyevent);
    }

    protected void onMeasure(int i, int j)
    {
        int i1;
        int k2;
label0:
        {
            super.onMeasure(i, j);
            int k = android.view.View.MeasureSpec.getMode(i);
            int l = android.view.View.MeasureSpec.getMode(j);
            i1 = android.view.View.MeasureSpec.getSize(i);
            int j1 = android.view.View.MeasureSpec.getSize(j);
            int k1 = 0;
            int l1 = 0;
            int i2 = 0;
            int j2;
            View view;
            if(mAdapter == null)
                j = 0;
            else
                j = mAdapter.getCount();
            mItemCount = j;
            j2 = l1;
            k2 = i2;
            j = k1;
            if(mItemCount <= 0)
                break label0;
            if(k != 0)
            {
                j2 = l1;
                k2 = i2;
                j = k1;
                if(l != 0)
                    break label0;
            }
            view = obtainView(0, mIsScrap);
            measureScrapChild(view, 0, i, j1);
            i2 = view.getMeasuredWidth();
            k1 = view.getMeasuredHeight();
            l1 = combineMeasuredStates(0, view.getMeasuredState());
            j2 = k1;
            k2 = l1;
            j = i2;
            if(recycleOnMeasure())
            {
                j2 = k1;
                k2 = l1;
                j = i2;
                if(mRecycler.shouldRecycleViewType(((AbsListView.LayoutParams)view.getLayoutParams()).viewType))
                {
                    mRecycler.addScrapView(view, 0);
                    j = i2;
                    k2 = l1;
                    j2 = k1;
                }
            }
        }
        if(k == 0)
            k2 = mListPadding.left + mListPadding.right + j + getVerticalScrollbarWidth();
        else
            k2 = i1 | 0xff000000 & k2;
        j = j1;
        if(l == 0)
            j = mListPadding.top + mListPadding.bottom + j2 + getVerticalFadingEdgeLength() * 2;
        j2 = j;
        if(l == 0x80000000)
            j2 = measureHeightOfChildren(i, 0, -1, j, -1);
        setMeasuredDimension(k2, j2);
        mWidthMeasureSpec = i;
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        if(getChildCount() > 0)
        {
            View view = getFocusedChild();
            if(view != null)
            {
                int i1 = mFirstPosition;
                int j1 = indexOfChild(view);
                int k1 = Math.max(0, view.getBottom() - (j - mPaddingTop));
                int l1 = view.getTop();
                if(mFocusSelector == null)
                    mFocusSelector = new FocusSelector(null);
                post(mFocusSelector.setupForSetSelection(i1 + j1, l1 - k1));
            }
        }
        super.onSizeChanged(i, j, k, l);
    }

    boolean pageScroll(int i)
    {
        boolean flag;
        if(i == 33)
        {
            i = Math.max(0, mSelectedPosition - getChildCount() - 1);
            flag = false;
        } else
        if(i == 130)
        {
            i = Math.min(mItemCount - 1, (mSelectedPosition + getChildCount()) - 1);
            flag = true;
        } else
        {
            return false;
        }
        if(i >= 0)
        {
            i = lookForSelectablePositionAfter(mSelectedPosition, i, flag);
            if(i >= 0)
            {
                mLayoutMode = 4;
                mSpecificTop = mPaddingTop + getVerticalFadingEdgeLength();
                if(flag && i > mItemCount - getChildCount())
                    mLayoutMode = 3;
                if(!flag && i < getChildCount())
                    mLayoutMode = 1;
                setSelectionInt(i);
                invokeOnItemScrollListener();
                if(!awakenScrollBars())
                    invalidate();
                return true;
            }
        }
        return false;
    }

    public boolean performAccessibilityActionInternal(int i, Bundle bundle)
    {
        if(super.performAccessibilityActionInternal(i, bundle))
            return true;
        i;
        JVM INSTR tableswitch 16908343 16908343: default 32
    //                   16908343 34;
           goto _L1 _L2
_L1:
        return false;
_L2:
        i = bundle.getInt("android.view.accessibility.action.ARGUMENT_ROW_INT", -1);
        int j = Math.min(i, getCount() - 1);
        if(i >= 0)
        {
            smoothScrollToPosition(j);
            return true;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected boolean recycleOnMeasure()
    {
        return true;
    }

    public boolean removeFooterView(View view)
    {
        if(mFooterViewInfos.size() > 0)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mAdapter != null)
            {
                flag1 = flag;
                if(((HeaderViewListAdapter)mAdapter).removeFooter(view))
                {
                    if(mDataSetObserver != null)
                        mDataSetObserver.onChanged();
                    flag1 = true;
                }
            }
            removeFixedViewInfo(view, mFooterViewInfos);
            return flag1;
        } else
        {
            return false;
        }
    }

    public boolean removeHeaderView(View view)
    {
        if(mHeaderViewInfos.size() > 0)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mAdapter != null)
            {
                flag1 = flag;
                if(((HeaderViewListAdapter)mAdapter).removeHeader(view))
                {
                    if(mDataSetObserver != null)
                        mDataSetObserver.onChanged();
                    flag1 = true;
                }
            }
            removeFixedViewInfo(view, mHeaderViewInfos);
            return flag1;
        } else
        {
            return false;
        }
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean flag)
    {
        int k;
        int j1;
        int k1;
        int l1;
label0:
        {
            int i = rect.top;
            rect.offset(view.getLeft(), view.getTop());
            rect.offset(-view.getScrollX(), -view.getScrollY());
            k = getHeight();
            int l = getScrollY();
            j1 = l + k;
            k1 = getVerticalFadingEdgeLength();
            l1 = l;
            if(!showingTopFadingEdge())
                break label0;
            if(mSelectedPosition <= 0)
            {
                l1 = l;
                if(i <= k1)
                    break label0;
            }
            l1 = l + k1;
        }
        int j;
        int i1;
label1:
        {
            j = getChildAt(getChildCount() - 1).getBottom();
            i1 = j1;
            if(!showingBottomFadingEdge())
                break label1;
            if(mSelectedPosition >= mItemCount - 1)
            {
                i1 = j1;
                if(rect.bottom >= j - k1)
                    break label1;
            }
            i1 = j1 - k1;
        }
        k1 = 0;
        if(rect.bottom > i1 && rect.top > l1)
        {
            if(rect.height() > k)
                j1 = (rect.top - l1) + 0;
            else
                j1 = (rect.bottom - i1) + 0;
            j1 = Math.min(j1, j - i1);
        } else
        {
            j1 = k1;
            if(rect.top < l1)
            {
                j1 = k1;
                if(rect.bottom < i1)
                {
                    if(rect.height() > k)
                        j1 = 0 - (i1 - rect.bottom);
                    else
                        j1 = 0 - (l1 - rect.top);
                    j1 = Math.max(j1, getChildAt(0).getTop() - l1);
                }
            }
        }
        if(j1 != 0)
            flag = true;
        else
            flag = false;
        if(flag)
        {
            scrollListItemsBy(-j1);
            positionSelector(-1, view);
            mSelectedTop = view.getTop();
            invalidate();
        }
        return flag;
    }

    void resetList()
    {
        clearRecycledState(mHeaderViewInfos);
        clearRecycledState(mFooterViewInfos);
        super.resetList();
        mLayoutMode = 0;
    }

    public void setAdapter(ListAdapter listadapter)
    {
        if(mAdapter != null && mDataSetObserver != null)
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        resetList();
        mRecycler.clear();
        if(mHeaderViewInfos.size() > 0 || mFooterViewInfos.size() > 0)
            mAdapter = wrapHeaderListAdapterInternal(mHeaderViewInfos, mFooterViewInfos, listadapter);
        else
            mAdapter = listadapter;
        mOldSelectedPosition = -1;
        mOldSelectedRowId = 0x8000000000000000L;
        super.setAdapter(listadapter);
        if(mAdapter != null)
        {
            mAreAllItemsSelectable = mAdapter.areAllItemsEnabled();
            mOldItemCount = mItemCount;
            mItemCount = mAdapter.getCount();
            checkFocus();
            mDataSetObserver = new AbsListView.AdapterDataSetObserver(this);
            mAdapter.registerDataSetObserver(mDataSetObserver);
            mRecycler.setViewTypeCount(mAdapter.getViewTypeCount());
            int i;
            if(mStackFromBottom)
                i = lookForSelectablePosition(mItemCount - 1, false);
            else
                i = lookForSelectablePosition(0, true);
            setSelectedPositionInt(i);
            setNextSelectedPositionInt(i);
            if(mItemCount == 0)
                checkSelectionChanged();
        } else
        {
            mAreAllItemsSelectable = true;
            checkFocus();
            checkSelectionChanged();
        }
        requestLayout();
    }

    public void setCacheColorHint(int i)
    {
        boolean flag;
        if(i >>> 24 == 255)
            flag = true;
        else
            flag = false;
        mIsCacheColorOpaque = flag;
        if(flag)
        {
            if(mDividerPaint == null)
                mDividerPaint = new Paint();
            mDividerPaint.setColor(i);
        }
        super.setCacheColorHint(i);
    }

    public void setDivider(Drawable drawable)
    {
        boolean flag = true;
        boolean flag1;
        if(drawable != null)
            mDividerHeight = drawable.getIntrinsicHeight();
        else
            mDividerHeight = 0;
        mDivider = drawable;
        flag1 = flag;
        if(drawable != null)
            if(drawable.getOpacity() == -1)
                flag1 = flag;
            else
                flag1 = false;
        mDividerIsOpaque = flag1;
        requestLayout();
        invalidate();
    }

    public void setDividerHeight(int i)
    {
        mDividerHeight = i;
        requestLayout();
        invalidate();
    }

    public void setFooterDividersEnabled(boolean flag)
    {
        mFooterDividersEnabled = flag;
        invalidate();
    }

    public void setHeaderDividersEnabled(boolean flag)
    {
        mHeaderDividersEnabled = flag;
        invalidate();
    }

    public void setItemsCanFocus(boolean flag)
    {
        mItemsCanFocus = flag;
        if(!flag)
            setDescendantFocusability(0x60000);
    }

    public void setOverscrollFooter(Drawable drawable)
    {
        mOverScrollFooter = drawable;
        invalidate();
    }

    public void setOverscrollHeader(Drawable drawable)
    {
        mOverScrollHeader = drawable;
        if(mScrollY < 0)
            invalidate();
    }

    public void setRemoteViewsAdapter(Intent intent)
    {
        super.setRemoteViewsAdapter(intent);
    }

    public void setSelection(int i)
    {
        setSelectionFromTop(i, 0);
    }

    public void setSelectionAfterHeaderView()
    {
        int i = getHeaderViewsCount();
        if(i > 0)
        {
            mNextSelectedPosition = 0;
            return;
        }
        if(mAdapter != null)
        {
            setSelection(i);
        } else
        {
            mNextSelectedPosition = i;
            mLayoutMode = 2;
        }
    }

    void setSelectionInt(int i)
    {
        boolean flag;
        int j;
        boolean flag1;
        setNextSelectedPositionInt(i);
        flag = false;
        j = mSelectedPosition;
        flag1 = flag;
        if(j < 0) goto _L2; else goto _L1
_L1:
        if(i != j - 1) goto _L4; else goto _L3
_L3:
        flag1 = true;
_L2:
        if(mPositionScroller != null)
            mPositionScroller.stop();
        layoutChildren();
        if(flag1)
            awakenScrollBars();
        return;
_L4:
        flag1 = flag;
        if(i == j + 1)
            flag1 = true;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public void smoothScrollByOffset(int i)
    {
        super.smoothScrollByOffset(i);
    }

    public void smoothScrollToPosition(int i)
    {
        super.smoothScrollToPosition(i);
    }

    boolean trackMotionScroll(int i, int j)
    {
        boolean flag = super.trackMotionScroll(i, j);
        removeUnusedFixedViews(mHeaderViewInfos);
        removeUnusedFixedViews(mFooterViewInfos);
        return flag;
    }

    protected HeaderViewListAdapter wrapHeaderListAdapterInternal(ArrayList arraylist, ArrayList arraylist1, ListAdapter listadapter)
    {
        return new HeaderViewListAdapter(arraylist, arraylist1, listadapter);
    }

    protected void wrapHeaderListAdapterInternal()
    {
        mAdapter = wrapHeaderListAdapterInternal(mHeaderViewInfos, mFooterViewInfos, mAdapter);
    }

    private static final float MAX_SCROLL_FACTOR = 0.33F;
    private static final int MIN_SCROLL_PREVIEW_PIXELS = 2;
    static final int NO_POSITION = -1;
    static final String TAG = "ListView";
    private boolean mAreAllItemsSelectable;
    private final ArrowScrollFocusResult mArrowScrollFocusResult;
    Drawable mDivider;
    int mDividerHeight;
    private boolean mDividerIsOpaque;
    private Paint mDividerPaint;
    private FocusSelector mFocusSelector;
    private boolean mFooterDividersEnabled;
    ArrayList mFooterViewInfos;
    private boolean mHeaderDividersEnabled;
    ArrayList mHeaderViewInfos;
    private boolean mIsCacheColorOpaque;
    private boolean mItemsCanFocus;
    Drawable mOverScrollFooter;
    Drawable mOverScrollHeader;
    private final Rect mTempRect;
}
