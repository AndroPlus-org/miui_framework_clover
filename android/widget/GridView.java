// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Trace;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

// Referenced classes of package android.widget:
//            AbsListView, ListAdapter, Checkable, Adapter

public class GridView extends AbsListView
{

    public GridView(Context context)
    {
        this(context, null);
    }

    public GridView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010071);
    }

    public GridView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public GridView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mNumColumns = -1;
        mHorizontalSpacing = 0;
        mVerticalSpacing = 0;
        mStretchMode = 2;
        mReferenceView = null;
        mReferenceViewInSelectedRow = null;
        mGravity = 0x800003;
        mTempRect = new Rect();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.GridView, i, j);
        setHorizontalSpacing(context.getDimensionPixelOffset(1, 0));
        setVerticalSpacing(context.getDimensionPixelOffset(2, 0));
        i = context.getInt(3, 2);
        if(i >= 0)
            setStretchMode(i);
        i = context.getDimensionPixelOffset(4, -1);
        if(i > 0)
            setColumnWidth(i);
        setNumColumns(context.getInt(5, 1));
        i = context.getInt(0, -1);
        if(i >= 0)
            setGravity(i);
        context.recycle();
    }

    private void adjustForBottomFadingEdge(View view, int i, int j)
    {
        if(view.getBottom() > j)
            offsetChildrenTopAndBottom(-Math.min(view.getTop() - i, view.getBottom() - j));
    }

    private void adjustForTopFadingEdge(View view, int i, int j)
    {
        if(view.getTop() < i)
            offsetChildrenTopAndBottom(Math.min(i - view.getTop(), j - view.getBottom()));
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
            k = j - mVerticalSpacing;
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
            l = j + mVerticalSpacing;
        j = l;
        if(l > 0)
            j = 0;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private boolean commonKey(int i, int j, KeyEvent keyevent)
    {
        boolean flag;
        int k;
        boolean flag1;
        if(mAdapter == null)
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
        if(flag1) goto _L2; else goto _L1
_L1:
        flag = flag1;
        if(k == 1) goto _L2; else goto _L3
_L3:
        i;
        JVM INSTR lookupswitch 9: default 208
    //                   19: 289
    //                   20: 356
    //                   21: 219
    //                   22: 254
    //                   61: 632
    //                   92: 425
    //                   93: 492
    //                   122: 561
    //                   123: 596;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L4:
        flag = flag1;
_L2:
        if(flag)
            return true;
        break; /* Loop/switch isn't completed */
_L7:
        flag = flag1;
        if(keyevent.hasNoModifiers())
            if(!resurrectSelectionIfNeeded())
                flag = arrowScroll(17);
            else
                flag = true;
        continue; /* Loop/switch isn't completed */
_L8:
        flag = flag1;
        if(keyevent.hasNoModifiers())
            if(!resurrectSelectionIfNeeded())
                flag = arrowScroll(66);
            else
                flag = true;
        continue; /* Loop/switch isn't completed */
_L5:
        if(keyevent.hasNoModifiers())
        {
            if(!resurrectSelectionIfNeeded())
                flag = arrowScroll(33);
            else
                flag = true;
        } else
        {
            flag = flag1;
            if(keyevent.hasModifiers(2))
                if(!resurrectSelectionIfNeeded())
                    flag = fullScroll(33);
                else
                    flag = true;
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if(keyevent.hasNoModifiers())
        {
            if(!resurrectSelectionIfNeeded())
                flag = arrowScroll(130);
            else
                flag = true;
        } else
        {
            flag = flag1;
            if(keyevent.hasModifiers(2))
                if(!resurrectSelectionIfNeeded())
                    flag = fullScroll(130);
                else
                    flag = true;
        }
        continue; /* Loop/switch isn't completed */
_L10:
        if(keyevent.hasNoModifiers())
        {
            if(!resurrectSelectionIfNeeded())
                flag = pageScroll(33);
            else
                flag = true;
        } else
        {
            flag = flag1;
            if(keyevent.hasModifiers(2))
                if(!resurrectSelectionIfNeeded())
                    flag = fullScroll(33);
                else
                    flag = true;
        }
        continue; /* Loop/switch isn't completed */
_L11:
        if(keyevent.hasNoModifiers())
        {
            if(!resurrectSelectionIfNeeded())
                flag = pageScroll(130);
            else
                flag = true;
        } else
        {
            flag = flag1;
            if(keyevent.hasModifiers(2))
                if(!resurrectSelectionIfNeeded())
                    flag = fullScroll(130);
                else
                    flag = true;
        }
        continue; /* Loop/switch isn't completed */
_L12:
        flag = flag1;
        if(keyevent.hasNoModifiers())
            if(!resurrectSelectionIfNeeded())
                flag = fullScroll(33);
            else
                flag = true;
        continue; /* Loop/switch isn't completed */
_L13:
        flag = flag1;
        if(keyevent.hasNoModifiers())
            if(!resurrectSelectionIfNeeded())
                flag = fullScroll(130);
            else
                flag = true;
        continue; /* Loop/switch isn't completed */
_L9:
        if(keyevent.hasNoModifiers())
        {
            if(!resurrectSelectionIfNeeded())
                flag = sequenceScroll(2);
            else
                flag = true;
        } else
        {
            flag = flag1;
            if(keyevent.hasModifiers(1))
                if(!resurrectSelectionIfNeeded())
                    flag = sequenceScroll(1);
                else
                    flag = true;
        }
        if(true) goto _L2; else goto _L14
_L14:
        if(sendToTextFilter(i, j, keyevent))
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
            return super.onKeyMultiple(i, j, keyevent);
        }
    }

    private void correctTooHigh(int i, int j, int k)
    {
        if((mFirstPosition + k) - 1 == mItemCount - 1 && k > 0)
        {
            k = getChildAt(k - 1).getBottom();
            int l = mBottom - mTop - mListPadding.bottom - k;
            View view = getChildAt(0);
            int i1 = view.getTop();
            if(l > 0 && (mFirstPosition > 0 || i1 < mListPadding.top))
            {
                k = l;
                if(mFirstPosition == 0)
                    k = Math.min(l, mListPadding.top - i1);
                offsetChildrenTopAndBottom(k);
                if(mFirstPosition > 0)
                {
                    k = mFirstPosition;
                    if(mStackFromBottom)
                        i = 1;
                    fillUp(k - i, view.getTop() - j);
                    adjustViewsUpOrDown();
                }
            }
        }
    }

    private void correctTooLow(int i, int j, int k)
    {
        if(mFirstPosition == 0 && k > 0)
        {
            int l = getChildAt(0).getTop();
            int i1 = mListPadding.top;
            int j1 = mBottom - mTop - mListPadding.bottom;
            i1 = l - i1;
            View view = getChildAt(k - 1);
            int k1 = view.getBottom();
            l = (mFirstPosition + k) - 1;
            if(i1 > 0 && (l < mItemCount - 1 || k1 > j1))
            {
                k = i1;
                if(l == mItemCount - 1)
                    k = Math.min(i1, k1 - j1);
                offsetChildrenTopAndBottom(-k);
                if(l < mItemCount - 1)
                {
                    if(!mStackFromBottom)
                        i = 1;
                    fillDown(l + i, view.getBottom() + j);
                    adjustViewsUpOrDown();
                }
            }
        }
    }

    private boolean determineColumns(int i)
    {
        int j;
        int l;
        boolean flag;
        boolean flag1;
        j = mRequestedHorizontalSpacing;
        int k = mStretchMode;
        l = mRequestedColumnWidth;
        flag = false;
        flag1 = false;
        if(mRequestedNumColumns == -1)
        {
            if(l > 0)
                mNumColumns = (i + j) / (l + j);
            else
                mNumColumns = 2;
        } else
        {
            mNumColumns = mRequestedNumColumns;
        }
        if(mNumColumns <= 0)
            mNumColumns = 1;
        k;
        JVM INSTR tableswitch 0 0: default 80
    //                   0 154;
           goto _L1 _L2
_L1:
        i = i - mNumColumns * l - (mNumColumns - 1) * j;
        if(i < 0)
            flag1 = true;
        k;
        JVM INSTR tableswitch 1 3: default 132
    //                   1 193
    //                   2 172
    //                   3 234;
           goto _L3 _L4 _L5 _L6
_L3:
        return flag1;
_L2:
        mColumnWidth = l;
        mHorizontalSpacing = j;
        flag1 = flag;
        continue; /* Loop/switch isn't completed */
_L5:
        mColumnWidth = i / mNumColumns + l;
        mHorizontalSpacing = j;
        continue; /* Loop/switch isn't completed */
_L4:
        mColumnWidth = l;
        if(mNumColumns > 1)
            mHorizontalSpacing = i / (mNumColumns - 1) + j;
        else
            mHorizontalSpacing = j + i;
        continue; /* Loop/switch isn't completed */
_L6:
        mColumnWidth = l;
        if(mNumColumns > 1)
            mHorizontalSpacing = i / (mNumColumns + 1) + j;
        else
            mHorizontalSpacing = j + i;
        if(true) goto _L3; else goto _L7
_L7:
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
        for(; j1 < l && i1 < mItemCount; i1 += mNumColumns)
        {
            View view = makeRow(i1, j1, true);
            if(view != null)
                view1 = view;
            j1 = mReferenceView.getBottom() + mVerticalSpacing;
        }

        setVisibleRangeHint(mFirstPosition, (mFirstPosition + getChildCount()) - 1);
        return view1;
    }

    private View fillFromBottom(int i, int j)
    {
        i = Math.min(Math.max(i, mSelectedPosition), mItemCount - 1);
        i = mItemCount - 1 - i;
        return fillUp(mItemCount - 1 - (i - i % mNumColumns), j);
    }

    private View fillFromSelection(int i, int j, int k)
    {
        int l = getVerticalFadingEdgeLength();
        int i1 = mSelectedPosition;
        int j1 = mNumColumns;
        int k1 = mVerticalSpacing;
        int l1 = -1;
        int i2;
        View view;
        View view1;
        if(!mStackFromBottom)
        {
            i1 -= i1 % j1;
        } else
        {
            i1 = mItemCount - 1 - i1;
            l1 = mItemCount - 1 - (i1 - i1 % j1);
            i1 = Math.max(0, (l1 - j1) + 1);
        }
        i2 = getTopSelectionPixel(j, l, i1);
        k = getBottomSelectionPixel(k, l, j1, i1);
        if(mStackFromBottom)
            j = l1;
        else
            j = i1;
        view = makeRow(j, i, true);
        mFirstPosition = i1;
        view1 = mReferenceView;
        adjustForTopFadingEdge(view1, i2, k);
        adjustForBottomFadingEdge(view1, i2, k);
        if(!mStackFromBottom)
        {
            fillUp(i1 - j1, view1.getTop() - k1);
            adjustViewsUpOrDown();
            fillDown(i1 + j1, view1.getBottom() + k1);
        } else
        {
            fillDown(l1 + j1, view1.getBottom() + k1);
            adjustViewsUpOrDown();
            fillUp(i1 - 1, view1.getTop() - k1);
        }
        return view;
    }

    private View fillFromTop(int i)
    {
        mFirstPosition = Math.min(mFirstPosition, mSelectedPosition);
        mFirstPosition = Math.min(mFirstPosition, mItemCount - 1);
        if(mFirstPosition < 0)
            mFirstPosition = 0;
        mFirstPosition = mFirstPosition - mFirstPosition % mNumColumns;
        return fillDown(mFirstPosition, i);
    }

    private View fillSelection(int i, int j)
    {
        int k = reconcileSelectedPosition();
        int l = mNumColumns;
        int i1 = mVerticalSpacing;
        int j1 = -1;
        int k1;
        int l1;
        int i2;
        View view;
        View view1;
        if(!mStackFromBottom)
        {
            k -= k % l;
        } else
        {
            j1 = mItemCount - 1 - k;
            j1 = mItemCount - 1 - (j1 - j1 % l);
            k = Math.max(0, (j1 - l) + 1);
        }
        k1 = getVerticalFadingEdgeLength();
        l1 = getTopSelectionPixel(i, k1, k);
        if(mStackFromBottom)
            i2 = j1;
        else
            i2 = k;
        view = makeRow(i2, l1, true);
        mFirstPosition = k;
        view1 = mReferenceView;
        if(!mStackFromBottom)
        {
            fillDown(k + l, view1.getBottom() + i1);
            pinToBottom(j);
            fillUp(k - l, view1.getTop() - i1);
            adjustViewsUpOrDown();
        } else
        {
            offsetChildrenTopAndBottom(getBottomSelectionPixel(j, k1, l, k) - view1.getBottom());
            fillUp(k - 1, view1.getTop() - i1);
            pinToTop(i);
            fillDown(j1 + l, view1.getBottom() + i1);
            adjustViewsUpOrDown();
        }
        return view;
    }

    private View fillSpecific(int i, int j)
    {
        int k;
        int i1;
        View view;
        View view1;
        k = mNumColumns;
        int l = -1;
        if(!mStackFromBottom)
        {
            i1 = i - i % k;
            i = l;
        } else
        {
            i = mItemCount - 1 - i;
            i = mItemCount - 1 - (i - i % k);
            i1 = Math.max(0, (i - k) + 1);
        }
        if(mStackFromBottom)
            l = i;
        else
            l = i1;
        view = makeRow(l, j, true);
        mFirstPosition = i1;
        view1 = mReferenceView;
        if(view1 == null)
            return null;
        j = mVerticalSpacing;
        if(mStackFromBottom) goto _L2; else goto _L1
_L1:
        View view6;
        View view2 = fillUp(i1 - k, view1.getTop() - j);
        adjustViewsUpOrDown();
        View view4 = fillDown(i1 + k, view1.getBottom() + j);
        i = getChildCount();
        view6 = view2;
        view1 = view4;
        if(i > 0)
        {
            correctTooHigh(k, j, i);
            view1 = view4;
            view6 = view2;
        }
_L4:
        if(view != null)
            return view;
        break; /* Loop/switch isn't completed */
_L2:
        View view3 = fillDown(i + k, view1.getBottom() + j);
        adjustViewsUpOrDown();
        View view5 = fillUp(i1 - 1, view1.getTop() - j);
        i = getChildCount();
        view6 = view5;
        view1 = view3;
        if(i > 0)
        {
            correctTooLow(k, j, i);
            view6 = view5;
            view1 = view3;
        }
        if(true) goto _L4; else goto _L3
_L3:
        if(view6 != null)
            return view6;
        else
            return view1;
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
        for(; i1 > k && l >= 0; l -= mNumColumns)
        {
            View view = makeRow(l, i1, false);
            if(view != null)
                view1 = view;
            i1 = mReferenceView.getTop() - mVerticalSpacing;
            mFirstPosition = l;
        }

        if(mStackFromBottom)
            mFirstPosition = Math.max(0, l + 1);
        setVisibleRangeHint(mFirstPosition, (mFirstPosition + getChildCount()) - 1);
        return view1;
    }

    private int getBottomSelectionPixel(int i, int j, int k, int l)
    {
        int i1 = i;
        if((l + k) - 1 < mItemCount - 1)
            i1 = i - j;
        return i1;
    }

    private int getTopSelectionPixel(int i, int j, int k)
    {
        int l = i;
        if(k > 0)
            l = i + j;
        return l;
    }

    private boolean isCandidateSelection(int i, int j)
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = false;
        boolean flag5 = false;
        int k = getChildCount();
        int l = k - 1 - i;
        int i1;
        if(!mStackFromBottom)
        {
            l = i - i % mNumColumns;
            i1 = Math.min((mNumColumns + l) - 1, k);
        } else
        {
            i1 = k - 1 - (l - l % mNumColumns);
            l = Math.max(0, (i1 - mNumColumns) + 1);
        }
        switch(j)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT, FOCUS_FORWARD, FOCUS_BACKWARD}.");

        case 66: // 'B'
            if(i != l)
                flag3 = false;
            return flag3;

        case 130: 
            if(l == 0)
                flag3 = flag;
            else
                flag3 = false;
            return flag3;

        case 17: // '\021'
            if(i == i1)
                flag3 = flag1;
            else
                flag3 = false;
            return flag3;

        case 33: // '!'
            if(i1 == k - 1)
                flag3 = flag2;
            else
                flag3 = false;
            return flag3;

        case 2: // '\002'
            flag3 = flag5;
            if(i == l)
            {
                flag3 = flag5;
                if(l == 0)
                    flag3 = true;
            }
            return flag3;

        case 1: // '\001'
            flag3 = flag4;
            break;
        }
        if(i == i1)
        {
            flag3 = flag4;
            if(i1 == k - 1)
                flag3 = true;
        }
        return flag3;
    }

    private View makeAndAddView(int i, int j, boolean flag, int k, boolean flag1, int l)
    {
        if(!mDataChanged)
        {
            View view = mRecycler.getActiveView(i);
            if(view != null)
            {
                setupChild(view, i, j, flag, k, flag1, true, l);
                return view;
            }
        }
        View view1 = obtainView(i, mIsScrap);
        setupChild(view1, i, j, flag, k, flag1, mIsScrap[0], l);
        return view1;
    }

    private View makeRow(int i, int j, boolean flag)
    {
        int k = mColumnWidth;
        int l = mHorizontalSpacing;
        boolean flag1 = isLayoutRtl();
        int i1;
        int j1;
        int k1;
        View view;
        View view1;
        int i2;
        if(flag1)
        {
            i1 = getWidth();
            j1 = mListPadding.right;
            boolean flag2;
            boolean flag3;
            int l1;
            int j2;
            View view2;
            if(mStretchMode == 3)
                k1 = l;
            else
                k1 = 0;
            k1 = i1 - j1 - k - k1;
        } else
        {
            j1 = mListPadding.left;
            if(mStretchMode == 3)
                k1 = l;
            else
                k1 = 0;
            k1 = j1 + k1;
        }
        if(!mStackFromBottom)
        {
            i1 = Math.min(mNumColumns + i, mItemCount);
            j1 = k1;
        } else
        {
            i2 = i + 1;
            k2 = Math.max(0, (i - mNumColumns) + 1);
            j1 = k1;
            i1 = i2;
            i = k2;
            if(i2 - k2 < mNumColumns)
            {
                j1 = mNumColumns;
                if(flag1)
                    i = -1;
                else
                    i = 1;
                j1 = k1 + i * ((j1 - (i2 - k2)) * (k + l));
                i1 = i2;
                i = k2;
            }
        }
        view = null;
        flag2 = shouldShowSelector();
        flag3 = touchModeDrawsInPressedState();
        l1 = mSelectedPosition;
        view1 = null;
        if(flag1)
            i2 = -1;
        else
            i2 = 1;
        j2 = i;
        k1 = j1;
        j1 = j2;
label0:
        do
        {
label1:
            {
                if(j1 >= i1)
                    break label0;
                int k2;
                if(j1 == l1)
                    flag1 = true;
                else
                    flag1 = false;
                if(flag)
                    k2 = -1;
                else
                    k2 = j1 - i;
                view1 = makeAndAddView(j1, j, flag, k1, flag1, k2);
                k2 = k1 + i2 * k;
                k1 = k2;
                if(j1 < i1 - 1)
                    k1 = k2 + i2 * l;
                view2 = view;
                if(!flag1)
                    break label1;
                if(!flag2)
                {
                    view2 = view;
                    if(!flag3)
                        break label1;
                }
                view2 = view1;
            }
            j1++;
            view = view2;
        } while(true);
        mReferenceView = view1;
        if(view != null)
            mReferenceViewInSelectedRow = mReferenceView;
        return view;
    }

    private View moveSelection(int i, int j, int k)
    {
        int l = getVerticalFadingEdgeLength();
        int i1 = mSelectedPosition;
        int j1 = mNumColumns;
        int k1 = mVerticalSpacing;
        int l1 = -1;
        int j2;
        int k2;
        View view;
        View view1;
        if(!mStackFromBottom)
        {
            int i2 = i1 - i - (i1 - i) % j1;
            k2 = i1 - i1 % j1;
            i = l1;
            l1 = i2;
        } else
        {
            k2 = mItemCount - 1 - i1;
            j2 = mItemCount - 1 - (k2 - k2 % j1);
            k2 = Math.max(0, (j2 - j1) + 1);
            i = mItemCount - 1 - (i1 - i);
            l1 = Math.max(0, (mItemCount - 1 - (i - i % j1) - j1) + 1);
            i = j2;
        }
        i1 = k2 - l1;
        l1 = getTopSelectionPixel(j, l, k2);
        j2 = getBottomSelectionPixel(k, l, j1, k2);
        mFirstPosition = k2;
        if(i1 > 0)
        {
            if(mReferenceViewInSelectedRow == null)
                j = 0;
            else
                j = mReferenceViewInSelectedRow.getBottom();
            if(mStackFromBottom)
                k = i;
            else
                k = k2;
            view = makeRow(k, j + k1, true);
            view1 = mReferenceView;
            adjustForBottomFadingEdge(view1, l1, j2);
        } else
        if(i1 < 0)
        {
            if(mReferenceViewInSelectedRow == null)
                j = 0;
            else
                j = mReferenceViewInSelectedRow.getTop();
            if(mStackFromBottom)
                k = i;
            else
                k = k2;
            view = makeRow(k, j - k1, false);
            view1 = mReferenceView;
            adjustForTopFadingEdge(view1, l1, j2);
        } else
        {
            if(mReferenceViewInSelectedRow == null)
                j = 0;
            else
                j = mReferenceViewInSelectedRow.getTop();
            if(mStackFromBottom)
                k = i;
            else
                k = k2;
            view = makeRow(k, j, true);
            view1 = mReferenceView;
        }
        if(!mStackFromBottom)
        {
            fillUp(k2 - j1, view1.getTop() - k1);
            adjustViewsUpOrDown();
            fillDown(k2 + j1, view1.getBottom() + k1);
        } else
        {
            fillDown(i + j1, view1.getBottom() + k1);
            adjustViewsUpOrDown();
            fillUp(k2 - 1, view1.getTop() - k1);
        }
        return view;
    }

    private void pinToBottom(int i)
    {
        int j = getChildCount();
        if(mFirstPosition + j == mItemCount)
        {
            i -= getChildAt(j - 1).getBottom();
            if(i > 0)
                offsetChildrenTopAndBottom(i);
        }
    }

    private void pinToTop(int i)
    {
        if(mFirstPosition == 0)
        {
            i -= getChildAt(0).getTop();
            if(i < 0)
                offsetChildrenTopAndBottom(i);
        }
    }

    private void setupChild(View view, int i, int j, boolean flag, int k, boolean flag1, boolean flag2, 
            int l)
    {
        int i1;
        Trace.traceBegin(8L, "setupGridItem");
        boolean flag3;
        int j1;
        boolean flag4;
        boolean flag5;
        AbsListView.LayoutParams layoutparams;
        AbsListView.LayoutParams layoutparams1;
        if(flag1)
            flag3 = shouldShowSelector();
        else
            flag3 = false;
        if(flag3 != view.isSelected())
            i1 = 1;
        else
            i1 = 0;
        j1 = mTouchMode;
        if(j1 > 0 && j1 < 3)
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
            flag4 = true;
        else
            flag4 = false;
        if(flag2 && i1 == 0)
            flag5 = view.isLayoutRequested();
        else
            flag5 = true;
        layoutparams = (AbsListView.LayoutParams)view.getLayoutParams();
        layoutparams1 = layoutparams;
        if(layoutparams == null)
            layoutparams1 = (AbsListView.LayoutParams)generateDefaultLayoutParams();
        layoutparams1.viewType = mAdapter.getItemViewType(i);
        layoutparams1.isEnabled = mAdapter.isEnabled(i);
        if(i1 != 0)
        {
            view.setSelected(flag3);
            if(flag3)
                requestFocus();
        }
        if(flag4)
            view.setPressed(flag1);
        if(mChoiceMode != 0 && mCheckStates != null)
            if(view instanceof Checkable)
                ((Checkable)view).setChecked(mCheckStates.get(i));
            else
            if(getContext().getApplicationInfo().targetSdkVersion >= 11)
                view.setActivated(mCheckStates.get(i));
        if(flag2 && layoutparams1.forceAdd ^ true)
        {
            attachViewToParent(view, l, layoutparams1);
            if(!flag2 || ((AbsListView.LayoutParams)view.getLayoutParams()).scrappedFromPosition != i)
                view.jumpDrawablesToCurrentState();
        } else
        {
            layoutparams1.forceAdd = false;
            addViewInLayout(view, l, layoutparams1, true);
        }
        if(flag5)
        {
            i = ViewGroup.getChildMeasureSpec(android.view.View.MeasureSpec.makeMeasureSpec(0, 0), 0, layoutparams1.height);
            view.measure(ViewGroup.getChildMeasureSpec(android.view.View.MeasureSpec.makeMeasureSpec(mColumnWidth, 0x40000000), 0, layoutparams1.width), i);
        } else
        {
            cleanupLayoutState(view);
        }
        i1 = view.getMeasuredWidth();
        l = view.getMeasuredHeight();
        if(!flag)
            j -= l;
        i = getLayoutDirection();
        Gravity.getAbsoluteGravity(mGravity, i) & 7;
        JVM INSTR tableswitch 1 5: default 372
    //                   1 530
    //                   2 372
    //                   3 524
    //                   4 372
    //                   5 546;
           goto _L1 _L2 _L1 _L3 _L1 _L4
_L4:
        break MISSING_BLOCK_LABEL_546;
_L1:
        i = k;
_L5:
        if(flag5)
        {
            view.layout(i, j, i + i1, j + l);
        } else
        {
            view.offsetLeftAndRight(i - view.getLeft());
            view.offsetTopAndBottom(j - view.getTop());
        }
        if(mCachingStarted && view.isDrawingCacheEnabled() ^ true)
            view.setDrawingCacheEnabled(true);
        Trace.traceEnd(8L);
        return;
_L3:
        i = k;
          goto _L5
_L2:
        i = k + (mColumnWidth - i1) / 2;
          goto _L5
        i = (mColumnWidth + k) - i1;
          goto _L5
    }

    boolean arrowScroll(int i)
    {
        int j;
        int k;
        boolean flag;
        int l;
        int i1;
        boolean flag1;
        boolean flag2;
        j = mSelectedPosition;
        k = mNumColumns;
        flag = false;
        if(!mStackFromBottom)
        {
            l = (j / k) * k;
            i1 = Math.min((l + k) - 1, mItemCount - 1);
        } else
        {
            l = mItemCount;
            i1 = mItemCount - 1 - ((l - 1 - j) / k) * k;
            l = Math.max(0, (i1 - k) + 1);
        }
        i;
        JVM INSTR lookupswitch 2: default 72
    //                   33: 198
    //                   130: 226;
           goto _L1 _L2 _L3
_L1:
        flag1 = isLayoutRtl();
        if(j <= l || (i != 17 || !(flag1 ^ true)) && (i != 66 || !flag1)) goto _L5; else goto _L4
_L4:
        mLayoutMode = 6;
        setSelectionInt(Math.max(0, j - 1));
        flag2 = true;
_L7:
        if(flag2)
        {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
            invokeOnItemScrollListener();
        }
        if(flag2)
            awakenScrollBars();
        return flag2;
_L2:
        if(l > 0)
        {
            mLayoutMode = 6;
            setSelectionInt(Math.max(0, j - k));
            flag = true;
        }
          goto _L1
_L3:
        if(i1 < mItemCount - 1)
        {
            mLayoutMode = 6;
            setSelectionInt(Math.min(j + k, mItemCount - 1));
            flag = true;
        }
          goto _L1
_L5:
        flag2 = flag;
        if(j >= i1) goto _L7; else goto _L6
_L6:
        if(i == 17 && flag1) goto _L9; else goto _L8
_L8:
        flag2 = flag;
        if(i != 66) goto _L7; else goto _L10
_L10:
        flag2 = flag;
        if(!(flag1 ^ true)) goto _L7; else goto _L9
_L9:
        mLayoutMode = 6;
        setSelectionInt(Math.min(j + 1, mItemCount - 1));
        flag2 = true;
          goto _L7
    }

    protected void attachLayoutAnimationParameters(View view, android.view.ViewGroup.LayoutParams layoutparams, int i, int j)
    {
        android.view.animation.GridLayoutAnimationController.AnimationParameters animationparameters = (android.view.animation.GridLayoutAnimationController.AnimationParameters)layoutparams.layoutAnimationParameters;
        view = animationparameters;
        if(animationparameters == null)
        {
            view = new android.view.animation.GridLayoutAnimationController.AnimationParameters();
            layoutparams.layoutAnimationParameters = view;
        }
        view.count = j;
        view.index = i;
        view.columnsCount = mNumColumns;
        view.rowsCount = j / mNumColumns;
        if(!mStackFromBottom)
        {
            view.column = i % mNumColumns;
            view.row = i / mNumColumns;
        } else
        {
            i = j - 1 - i;
            view.column = mNumColumns - 1 - i % mNumColumns;
            view.row = ((android.view.animation.GridLayoutAnimationController.AnimationParameters) (view)).rowsCount - 1 - i / mNumColumns;
        }
    }

    protected int computeVerticalScrollExtent()
    {
        int i = getChildCount();
        if(i > 0)
        {
            int j = mNumColumns;
            int k = (((i + j) - 1) / j) * 100;
            View view = getChildAt(0);
            int l = view.getTop();
            int i1 = view.getHeight();
            j = k;
            if(i1 > 0)
                j = k + (l * 100) / i1;
            view = getChildAt(i - 1);
            l = view.getBottom();
            i = view.getHeight();
            k = j;
            if(i > 0)
                k = j - ((l - getHeight()) * 100) / i;
            return k;
        } else
        {
            return 0;
        }
    }

    protected int computeVerticalScrollOffset()
    {
        if(mFirstPosition >= 0 && getChildCount() > 0)
        {
            View view = getChildAt(0);
            int i = view.getTop();
            int j = view.getHeight();
            if(j > 0)
            {
                int k = mNumColumns;
                int l = ((mItemCount + k) - 1) / k;
                int i1;
                if(isStackFromBottom())
                    i1 = l * k - mItemCount;
                else
                    i1 = 0;
                return Math.max((((mFirstPosition + i1) / k) * 100 - (i * 100) / j) + (int)(((float)mScrollY / (float)getHeight()) * (float)l * 100F), 0);
            }
        }
        return 0;
    }

    protected int computeVerticalScrollRange()
    {
        int i = mNumColumns;
        int j = ((mItemCount + i) - 1) / i;
        int k = Math.max(j * 100, 0);
        i = k;
        if(mScrollY != 0)
            i = k + Math.abs((int)(((float)mScrollY / (float)getHeight()) * (float)j * 100F));
        return i;
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("numColumns", getNumColumns());
    }

    void fillGap(boolean flag)
    {
        int i = mNumColumns;
        int j = mVerticalSpacing;
        int k = getChildCount();
        if(flag)
        {
            int l = 0;
            if((mGroupFlags & 0x22) == 34)
                l = getListPaddingTop();
            if(k > 0)
                l = getChildAt(k - 1).getBottom() + j;
            int j1 = mFirstPosition + k;
            k = j1;
            if(mStackFromBottom)
                k = j1 + (i - 1);
            fillDown(k, l);
            correctTooHigh(i, j, getChildCount());
        } else
        {
            int i1 = 0;
            if((mGroupFlags & 0x22) == 34)
                i1 = getListPaddingBottom();
            if(k > 0)
                i1 = getChildAt(0).getTop() - j;
            else
                i1 = getHeight() - i1;
            k = mFirstPosition;
            if(!mStackFromBottom)
                k -= i;
            else
                k--;
            fillUp(k, i1);
            correctTooLow(i, j, getChildCount());
        }
    }

    int findMotionRow(int i)
    {
        int j = getChildCount();
        if(j > 0)
        {
            int k = mNumColumns;
            if(!mStackFromBottom)
            {
                for(int l = 0; l < j; l += k)
                    if(i <= getChildAt(l).getBottom())
                        return mFirstPosition + l;

            } else
            {
                for(int i1 = j - 1; i1 >= 0; i1 -= k)
                    if(i >= getChildAt(i1).getTop())
                        return mFirstPosition + i1;

            }
        }
        return -1;
    }

    boolean fullScroll(int i)
    {
        boolean flag = false;
        if(i != 33) goto _L2; else goto _L1
_L1:
        mLayoutMode = 2;
        setSelectionInt(0);
        invokeOnItemScrollListener();
        flag = true;
_L4:
        if(flag)
            awakenScrollBars();
        return flag;
_L2:
        if(i == 130)
        {
            mLayoutMode = 2;
            setSelectionInt(mItemCount - 1);
            invokeOnItemScrollListener();
            flag = true;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/GridView.getName();
    }

    public volatile Adapter getAdapter()
    {
        return getAdapter();
    }

    public ListAdapter getAdapter()
    {
        return mAdapter;
    }

    public int getColumnWidth()
    {
        return mColumnWidth;
    }

    public int getGravity()
    {
        return mGravity;
    }

    public int getHorizontalSpacing()
    {
        return mHorizontalSpacing;
    }

    public int getNumColumns()
    {
        return mNumColumns;
    }

    public int getRequestedColumnWidth()
    {
        return mRequestedColumnWidth;
    }

    public int getRequestedHorizontalSpacing()
    {
        return mRequestedHorizontalSpacing;
    }

    public int getStretchMode()
    {
        return mStretchMode;
    }

    public int getVerticalSpacing()
    {
        return mVerticalSpacing;
    }

    protected void layoutChildren()
    {
        boolean flag;
        flag = mBlockLayoutRequests;
        if(!flag)
            mBlockLayoutRequests = true;
        super.layoutChildren();
        invalidate();
        if(mAdapter != null)
            break MISSING_BLOCK_LABEL_47;
        resetList();
        invokeOnItemScrollListener();
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
        Object obj;
        AccessibilityNodeInfo accessibilitynodeinfo;
        Object obj1;
        View view;
        int i1;
        View view1;
        View view2;
        View view3;
        l = 0;
        obj = null;
        accessibilitynodeinfo = null;
        obj1 = null;
        view = null;
        i1 = l;
        view1 = view;
        view2 = ((View) (obj1));
        view3 = ((View) (obj));
        mLayoutMode;
        JVM INSTR tableswitch 1 6: default 152
    //                   1 210
    //                   2 250
    //                   3 210
    //                   4 210
    //                   5 210
    //                   6 328;
           goto _L1 _L2 _L3 _L2 _L2 _L2 _L4
_L1:
        i1 = mSelectedPosition - mFirstPosition;
        obj1 = accessibilitynodeinfo;
        if(i1 < 0)
            break MISSING_BLOCK_LABEL_191;
        obj1 = accessibilitynodeinfo;
        if(i1 >= k)
            break MISSING_BLOCK_LABEL_191;
        obj1 = getChildAt(i1);
        view2 = getChildAt(0);
        view3 = ((View) (obj1));
        view1 = view;
        i1 = l;
_L2:
        boolean flag1 = mDataChanged;
        if(!flag1) goto _L6; else goto _L5
_L5:
        handleDataChanged();
_L6:
        if(mItemCount != 0)
            break; /* Loop/switch isn't completed */
        resetList();
        invokeOnItemScrollListener();
        if(!flag)
            mBlockLayoutRequests = false;
        return;
_L3:
        int j1 = mNextSelectedPosition - mFirstPosition;
        i1 = l;
        view1 = view;
        view2 = ((View) (obj1));
        view3 = ((View) (obj));
        if(j1 < 0)
            continue; /* Loop/switch isn't completed */
        i1 = l;
        view1 = view;
        view2 = ((View) (obj1));
        view3 = ((View) (obj));
        if(j1 >= k)
            continue; /* Loop/switch isn't completed */
        view1 = getChildAt(j1);
        i1 = l;
        view2 = ((View) (obj1));
        view3 = ((View) (obj));
        continue; /* Loop/switch isn't completed */
_L4:
        i1 = l;
        view1 = view;
        view2 = ((View) (obj1));
        view3 = ((View) (obj));
        if(mNextSelectedPosition < 0)
            continue; /* Loop/switch isn't completed */
        i1 = mNextSelectedPosition - mSelectedPosition;
        view1 = view;
        view2 = ((View) (obj1));
        view3 = ((View) (obj));
        if(true) goto _L2; else goto _L7
_L7:
        setSelectedPositionInt(mNextSelectedPosition);
        Object obj2;
        Object obj3;
        Object obj4;
        obj2 = null;
        obj1 = null;
        obj3 = null;
        obj4 = null;
        j1 = -1;
        ViewRootImpl viewrootimpl = getViewRootImpl();
        accessibilitynodeinfo = obj2;
        view = obj3;
        l = j1;
        if(viewrootimpl == null)
            break MISSING_BLOCK_LABEL_519;
        obj = viewrootimpl.getAccessibilityFocusedHost();
        accessibilitynodeinfo = obj2;
        view = obj3;
        l = j1;
        if(obj == null)
            break MISSING_BLOCK_LABEL_519;
        View view4 = getAccessibilityFocusedChild(((View) (obj)));
        accessibilitynodeinfo = obj2;
        view = obj3;
        l = j1;
        if(view4 == null)
            break MISSING_BLOCK_LABEL_519;
        if(!flag1) goto _L9; else goto _L8
_L8:
        if(view4.hasTransientState()) goto _L9; else goto _L10
_L10:
        view = obj4;
        if(!mAdapterHasStableIds) goto _L11; else goto _L9
_L9:
        view = ((View) (obj));
        obj1 = viewrootimpl.getAccessibilityFocusedVirtualView();
_L11:
        l = getPositionForView(view4);
        accessibilitynodeinfo = ((AccessibilityNodeInfo) (obj1));
        int k1;
        k1 = mFirstPosition;
        obj = mRecycler;
        if(!flag1) goto _L13; else goto _L12
_L12:
        j1 = 0;
_L15:
        if(j1 >= k)
            break; /* Loop/switch isn't completed */
        ((AbsListView.RecycleBin) (obj)).addScrapView(getChildAt(j1), k1 + j1);
        j1++;
        if(true) goto _L15; else goto _L14
_L13:
        ((AbsListView.RecycleBin) (obj)).fillActiveViews(k, k1);
_L14:
        detachAllViewsFromParent();
        ((AbsListView.RecycleBin) (obj)).removeSkippedScrap();
        mLayoutMode;
        JVM INSTR tableswitch 1 6: default 628
    //                   1 861
    //                   2 829
    //                   3 894
    //                   4 914
    //                   5 931
    //                   6 948;
           goto _L16 _L17 _L18 _L19 _L20 _L21 _L22
_L16:
        if(k != 0) goto _L24; else goto _L23
_L23:
        if(mStackFromBottom) goto _L26; else goto _L25
_L25:
        if(mAdapter == null || isInTouchMode())
            i1 = -1;
        else
            i1 = 0;
        setSelectedPositionInt(i1);
        obj1 = fillFromTop(i);
_L39:
        ((AbsListView.RecycleBin) (obj)).scrapActiveViews();
        if(obj1 == null) goto _L28; else goto _L27
_L27:
        positionSelector(-1, ((View) (obj1)));
        mSelectedTop = ((View) (obj1)).getTop();
_L51:
        if(viewrootimpl == null) goto _L30; else goto _L29
_L29:
        if(viewrootimpl.getAccessibilityFocusedHost() != null) goto _L30; else goto _L31
_L31:
        if(view == null) goto _L33; else goto _L32
_L32:
        if(!view.isAttachedToWindow()) goto _L33; else goto _L34
_L34:
        obj1 = view.getAccessibilityNodeProvider();
        if(accessibilitynodeinfo == null || obj1 == null) goto _L36; else goto _L35
_L35:
        ((AccessibilityNodeProvider) (obj1)).performAction(AccessibilityNodeInfo.getVirtualDescendantId(accessibilitynodeinfo.getSourceNodeId()), 64, null);
_L30:
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
        if(!flag)
            mBlockLayoutRequests = false;
        return;
_L18:
        if(view1 == null) goto _L38; else goto _L37
_L37:
        obj1 = fillFromSelection(view1.getTop(), i, j);
          goto _L39
_L38:
        obj1 = fillSelection(i, j);
          goto _L39
_L17:
        mFirstPosition = 0;
        obj1 = fillFromTop(i);
        adjustViewsUpOrDown();
          goto _L39
        obj1;
        if(!flag)
            mBlockLayoutRequests = false;
        throw obj1;
_L19:
        obj1 = fillUp(mItemCount - 1, j);
        adjustViewsUpOrDown();
          goto _L39
_L20:
        obj1 = fillSpecific(mSelectedPosition, mSpecificTop);
          goto _L39
_L21:
        obj1 = fillSpecific(mSyncPosition, mSpecificTop);
          goto _L39
_L22:
        obj1 = moveSelection(i1, i, j);
          goto _L39
_L26:
        i1 = mItemCount - 1;
        if(mAdapter == null || isInTouchMode())
            i = -1;
        else
            i = i1;
        setSelectedPositionInt(i);
        obj1 = fillFromBottom(i1, j);
          goto _L39
_L24:
        if(mSelectedPosition < 0 || mSelectedPosition >= mItemCount) goto _L41; else goto _L40
_L40:
        i1 = mSelectedPosition;
        if(view3 != null) goto _L43; else goto _L42
_L42:
        obj1 = fillSpecific(i1, i);
          goto _L39
_L43:
        i = view3.getTop();
          goto _L42
_L41:
        if(mFirstPosition >= mItemCount) goto _L45; else goto _L44
_L44:
        i1 = mFirstPosition;
        if(view2 != null) goto _L47; else goto _L46
_L46:
        obj1 = fillSpecific(i1, i);
          goto _L39
_L47:
        i = view2.getTop();
          goto _L46
_L45:
        obj1 = fillSpecific(0, i);
          goto _L39
_L28:
        if(mTouchMode > 0)
        {
            if(mTouchMode < 3)
                i = 1;
            else
                i = 0;
        } else
        {
            i = 0;
        }
        if(!i) goto _L49; else goto _L48
_L48:
        obj1 = getChildAt(mMotionPosition - mFirstPosition);
        if(obj1 == null) goto _L51; else goto _L50
_L50:
        positionSelector(mMotionPosition, ((View) (obj1)));
          goto _L51
_L49:
        if(mSelectedPosition == -1) goto _L53; else goto _L52
_L52:
        obj1 = getChildAt(mSelectorPosition - mFirstPosition);
        if(obj1 == null) goto _L51; else goto _L54
_L54:
        positionSelector(mSelectorPosition, ((View) (obj1)));
          goto _L51
_L53:
        mSelectedTop = 0;
        mSelectorRect.setEmpty();
          goto _L51
_L36:
        view.requestAccessibilityFocus();
          goto _L30
_L33:
        if(l == -1) goto _L30; else goto _L55
_L55:
        obj1 = getChildAt(MathUtils.constrain(l - mFirstPosition, 0, getChildCount() - 1));
        if(obj1 == null) goto _L30; else goto _L56
_L56:
        ((View) (obj1)).requestAccessibilityFocus();
          goto _L30
    }

    int lookForSelectablePosition(int i, boolean flag)
    {
        if(mAdapter == null || isInTouchMode())
            return -1;
        if(i < 0 || i >= mItemCount)
            return -1;
        else
            return i;
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        int j = -1;
        int k = j;
        if(flag)
        {
            k = j;
            if(rect != null)
            {
                rect.offset(mScrollX, mScrollY);
                Rect rect1 = mTempRect;
                int l = 0x7fffffff;
                int i1 = getChildCount();
                int j1 = 0;
                do
                {
                    k = j;
                    if(j1 >= i1)
                        break;
                    if(!isCandidateSelection(j1, i))
                    {
                        k = l;
                    } else
                    {
                        View view = getChildAt(j1);
                        view.getDrawingRect(rect1);
                        offsetDescendantRectToMyCoords(view, rect1);
                        int k1 = getDistance(rect, rect1, i);
                        k = l;
                        if(k1 < l)
                        {
                            k = k1;
                            j = j1;
                        }
                    }
                    j1++;
                    l = k;
                } while(true);
            }
        }
        if(k >= 0)
            setSelection(mFirstPosition + k);
        else
            requestLayout();
    }

    public void onInitializeAccessibilityNodeInfoForItem(View view, int i, AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoForItem(view, i, accessibilitynodeinfo);
        int j = getCount();
        int k = getNumColumns();
        int l = j / k;
        boolean flag;
        if(!mStackFromBottom)
        {
            j = i % k;
            k = i / k;
        } else
        {
            int i1 = j - 1 - i;
            j = k - 1 - i1 % k;
            k = l - 1 - i1 / k;
        }
        view = (AbsListView.LayoutParams)view.getLayoutParams();
        if(view != null && ((AbsListView.LayoutParams) (view)).viewType == -2)
            flag = true;
        else
            flag = false;
        accessibilitynodeinfo.setCollectionItemInfo(android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(k, 1, j, 1, flag, isItemChecked(i)));
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        int i = getNumColumns();
        int j = getCount() / i;
        accessibilitynodeinfo.setCollectionInfo(android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.obtain(j, i, false, getSelectionModeForAccessibility()));
        if(i > 0 || j > 0)
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
        int l;
        int j2;
        super.onMeasure(i, j);
        int k = android.view.View.MeasureSpec.getMode(i);
        l = android.view.View.MeasureSpec.getMode(j);
        int i1 = android.view.View.MeasureSpec.getSize(i);
        int j1 = android.view.View.MeasureSpec.getSize(j);
        int l1;
        if(k == 0)
        {
            boolean flag;
            int k1;
            int i2;
            View view;
            AbsListView.LayoutParams layoutparams;
            AbsListView.LayoutParams layoutparams1;
            if(mColumnWidth > 0)
                i1 = mColumnWidth + mListPadding.left + mListPadding.right;
            else
                i1 = mListPadding.left + mListPadding.right;
            i1 += getVerticalScrollbarWidth();
        }
        flag = determineColumns(i1 - mListPadding.left - mListPadding.right);
        k1 = 0;
        if(mAdapter == null)
            l1 = 0;
        else
            l1 = mAdapter.getCount();
        mItemCount = l1;
        i2 = mItemCount;
        if(i2 > 0)
        {
            view = obtainView(0, mIsScrap);
            layoutparams = (AbsListView.LayoutParams)view.getLayoutParams();
            layoutparams1 = layoutparams;
            if(layoutparams == null)
            {
                layoutparams1 = (AbsListView.LayoutParams)generateDefaultLayoutParams();
                view.setLayoutParams(layoutparams1);
            }
            layoutparams1.viewType = mAdapter.getItemViewType(0);
            layoutparams1.isEnabled = mAdapter.isEnabled(0);
            layoutparams1.forceAdd = true;
            j = getChildMeasureSpec(android.view.View.MeasureSpec.makeSafeMeasureSpec(android.view.View.MeasureSpec.getSize(j), 0), 0, layoutparams1.height);
            view.measure(getChildMeasureSpec(android.view.View.MeasureSpec.makeMeasureSpec(mColumnWidth, 0x40000000), 0, layoutparams1.width), j);
            j = view.getMeasuredHeight();
            combineMeasuredStates(0, view.getMeasuredState());
            k1 = j;
            if(mRecycler.shouldRecycleViewType(layoutparams1.viewType))
            {
                mRecycler.addScrapView(view, -1);
                k1 = j;
            }
        }
        j = j1;
        if(l == 0)
            j = mListPadding.top + mListPadding.bottom + k1 + getVerticalFadingEdgeLength() * 2;
        l1 = j;
        if(l != 0x80000000) goto _L2; else goto _L1
_L1:
        l1 = mListPadding.top + mListPadding.bottom;
        j2 = mNumColumns;
        l = 0;
_L8:
        j1 = l1;
        if(l >= i2) goto _L4; else goto _L3
_L3:
        j1 = l1 + k1;
        l1 = j1;
        if(l + j2 < i2)
            l1 = j1 + mVerticalSpacing;
        if(l1 < j) goto _L6; else goto _L5
_L5:
        j1 = j;
_L4:
        l1 = j1;
_L2:
label0:
        {
            j = i1;
            if(k != 0x80000000)
                break label0;
            j = i1;
            if(mRequestedNumColumns == -1)
                break label0;
            if(mRequestedNumColumns * mColumnWidth + (mRequestedNumColumns - 1) * mHorizontalSpacing + mListPadding.left + mListPadding.right <= i1)
            {
                j = i1;
                if(!flag)
                    break label0;
            }
            j = i1 | 0x1000000;
        }
        setMeasuredDimension(j, l1);
        mWidthMeasureSpec = i;
        return;
_L6:
        l += j2;
        if(true) goto _L8; else goto _L7
_L7:
    }

    boolean pageScroll(int i)
    {
        int j = -1;
        if(i == 33)
            j = Math.max(0, mSelectedPosition - getChildCount());
        else
        if(i == 130)
            j = Math.min(mItemCount - 1, mSelectedPosition + getChildCount());
        if(j >= 0)
        {
            setSelectionInt(j);
            invokeOnItemScrollListener();
            awakenScrollBars();
            return true;
        } else
        {
            return false;
        }
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
        int j = getNumColumns();
        i = bundle.getInt("android.view.accessibility.action.ARGUMENT_ROW_INT", -1);
        j = Math.min(i * j, getCount() - 1);
        if(i >= 0)
        {
            smoothScrollToPosition(j);
            return true;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    boolean sequenceScroll(int i)
    {
        int j;
        int k;
        int l;
        int i1;
        int j1;
        boolean flag;
        j = mSelectedPosition;
        k = mNumColumns;
        l = mItemCount;
        if(!mStackFromBottom)
        {
            i1 = (j / k) * k;
            j1 = Math.min((i1 + k) - 1, l - 1);
        } else
        {
            j1 = l - 1 - ((l - 1 - j) / k) * k;
            i1 = Math.max(0, (j1 - k) + 1);
        }
        flag = false;
        k = 0;
        i;
        JVM INSTR tableswitch 1 2: default 72
    //                   1 173
    //                   2 133;
           goto _L1 _L2 _L3
_L1:
        if(flag)
        {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
            invokeOnItemScrollListener();
        }
        if(k != 0)
            awakenScrollBars();
        return flag;
_L3:
        if(j < l - 1)
        {
            mLayoutMode = 6;
            setSelectionInt(j + 1);
            flag = true;
            if(j == j1)
                k = 1;
            else
                k = 0;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(j > 0)
        {
            mLayoutMode = 6;
            setSelectionInt(j - 1);
            flag = true;
            if(j == i1)
                k = 1;
            else
                k = 0;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    public void setAdapter(ListAdapter listadapter)
    {
        if(mAdapter != null && mDataSetObserver != null)
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        resetList();
        mRecycler.clear();
        mAdapter = listadapter;
        mOldSelectedPosition = -1;
        mOldSelectedRowId = 0x8000000000000000L;
        super.setAdapter(listadapter);
        if(mAdapter != null)
        {
            mOldItemCount = mItemCount;
            mItemCount = mAdapter.getCount();
            mDataChanged = true;
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
            checkSelectionChanged();
        } else
        {
            checkFocus();
            checkSelectionChanged();
        }
        requestLayout();
    }

    public void setColumnWidth(int i)
    {
        if(i != mRequestedColumnWidth)
        {
            mRequestedColumnWidth = i;
            requestLayoutIfNecessary();
        }
    }

    public void setGravity(int i)
    {
        if(mGravity != i)
        {
            mGravity = i;
            requestLayoutIfNecessary();
        }
    }

    public void setHorizontalSpacing(int i)
    {
        if(i != mRequestedHorizontalSpacing)
        {
            mRequestedHorizontalSpacing = i;
            requestLayoutIfNecessary();
        }
    }

    public void setNumColumns(int i)
    {
        if(i != mRequestedNumColumns)
        {
            mRequestedNumColumns = i;
            requestLayoutIfNecessary();
        }
    }

    public void setRemoteViewsAdapter(Intent intent)
    {
        super.setRemoteViewsAdapter(intent);
    }

    public void setSelection(int i)
    {
        if(!isInTouchMode())
            setNextSelectedPositionInt(i);
        else
            mResurrectToPosition = i;
        mLayoutMode = 2;
        if(mPositionScroller != null)
            mPositionScroller.stop();
        requestLayout();
    }

    void setSelectionInt(int i)
    {
        int j = mNextSelectedPosition;
        if(mPositionScroller != null)
            mPositionScroller.stop();
        setNextSelectedPositionInt(i);
        layoutChildren();
        if(mStackFromBottom)
            i = mItemCount - 1 - mNextSelectedPosition;
        else
            i = mNextSelectedPosition;
        if(mStackFromBottom)
            j = mItemCount - 1 - j;
        if(i / mNumColumns != j / mNumColumns)
            awakenScrollBars();
    }

    public void setStretchMode(int i)
    {
        if(i != mStretchMode)
        {
            mStretchMode = i;
            requestLayoutIfNecessary();
        }
    }

    public void setVerticalSpacing(int i)
    {
        if(i != mVerticalSpacing)
        {
            mVerticalSpacing = i;
            requestLayoutIfNecessary();
        }
    }

    public void smoothScrollByOffset(int i)
    {
        super.smoothScrollByOffset(i);
    }

    public void smoothScrollToPosition(int i)
    {
        super.smoothScrollToPosition(i);
    }

    public static final int AUTO_FIT = -1;
    public static final int NO_STRETCH = 0;
    public static final int STRETCH_COLUMN_WIDTH = 2;
    public static final int STRETCH_SPACING = 1;
    public static final int STRETCH_SPACING_UNIFORM = 3;
    private int mColumnWidth;
    private int mGravity;
    private int mHorizontalSpacing;
    private int mNumColumns;
    private View mReferenceView;
    private View mReferenceViewInSelectedRow;
    private int mRequestedColumnWidth;
    private int mRequestedHorizontalSpacing;
    private int mRequestedNumColumns;
    private int mStretchMode;
    private final Rect mTempRect;
    private int mVerticalSpacing;
}
