// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;
import android.view.View;
import android.view.autofill.AutofillValue;

// Referenced classes of package android.widget:
//            AdapterView, ArrayAdapter, SpinnerAdapter, Adapter

public abstract class AbsSpinner extends AdapterView
{
    class RecycleBin
    {

        void clear()
        {
            SparseArray sparsearray = mScrapHeap;
            int i = sparsearray.size();
            for(int j = 0; j < i; j++)
            {
                View view = (View)sparsearray.valueAt(j);
                if(view != null)
                    AbsSpinner._2D_wrap0(AbsSpinner.this, view, true);
            }

            sparsearray.clear();
        }

        View get(int i)
        {
            View view = (View)mScrapHeap.get(i);
            if(view != null)
                mScrapHeap.delete(i);
            return view;
        }

        public void put(int i, View view)
        {
            mScrapHeap.put(i, view);
        }

        private final SparseArray mScrapHeap = new SparseArray();
        final AbsSpinner this$0;

        RecycleBin()
        {
            this$0 = AbsSpinner.this;
            super();
        }
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public String toString()
        {
            return (new StringBuilder()).append("AbsSpinner.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" selectedId=").append(selectedId).append(" position=").append(position).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeLong(selectedId);
            parcel.writeInt(position);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
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
        int position;
        long selectedId;


        SavedState(Parcel parcel)
        {
            super(parcel);
            selectedId = parcel.readLong();
            position = parcel.readInt();
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    static void _2D_wrap0(AbsSpinner absspinner, View view, boolean flag)
    {
        absspinner.removeDetachedView(view, flag);
    }

    public AbsSpinner(Context context)
    {
        super(context);
        mSelectionLeftPadding = 0;
        mSelectionTopPadding = 0;
        mSelectionRightPadding = 0;
        mSelectionBottomPadding = 0;
        mSpinnerPadding = new Rect();
        mRecycler = new RecycleBin();
        initAbsSpinner();
    }

    public AbsSpinner(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public AbsSpinner(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public AbsSpinner(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mSelectionLeftPadding = 0;
        mSelectionTopPadding = 0;
        mSelectionRightPadding = 0;
        mSelectionBottomPadding = 0;
        mSpinnerPadding = new Rect();
        mRecycler = new RecycleBin();
        if(getImportantForAutofill() == 0)
            setImportantForAutofill(1);
        initAbsSpinner();
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AbsSpinner, i, j);
        CharSequence acharsequence[] = attributeset.getTextArray(0);
        if(acharsequence != null)
        {
            context = new ArrayAdapter(context, 0x1090008, acharsequence);
            context.setDropDownViewResource(0x1090009);
            setAdapter(context);
        }
        attributeset.recycle();
    }

    private void initAbsSpinner()
    {
        setFocusable(true);
        setWillNotDraw(false);
    }

    public void autofill(AutofillValue autofillvalue)
    {
        if(!isEnabled())
            return;
        if(!autofillvalue.isList())
        {
            Log.w(LOG_TAG, (new StringBuilder()).append(autofillvalue).append(" could not be autofilled into ").append(this).toString());
            return;
        } else
        {
            setSelection(autofillvalue.getListValue());
            return;
        }
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        super.dispatchRestoreInstanceState(sparsearray);
        handleDataChanged();
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new android.view.ViewGroup.LayoutParams(-1, -2);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/AbsSpinner.getName();
    }

    public volatile Adapter getAdapter()
    {
        return getAdapter();
    }

    public SpinnerAdapter getAdapter()
    {
        return mAdapter;
    }

    public int getAutofillType()
    {
        byte byte0;
        if(isEnabled())
            byte0 = 3;
        else
            byte0 = 0;
        return byte0;
    }

    public AutofillValue getAutofillValue()
    {
        AutofillValue autofillvalue;
        if(isEnabled())
            autofillvalue = AutofillValue.forList(getSelectedItemPosition());
        else
            autofillvalue = null;
        return autofillvalue;
    }

    int getChildHeight(View view)
    {
        return view.getMeasuredHeight();
    }

    int getChildWidth(View view)
    {
        return view.getMeasuredWidth();
    }

    public int getCount()
    {
        return mItemCount;
    }

    public View getSelectedView()
    {
        if(mItemCount > 0 && mSelectedPosition >= 0)
            return getChildAt(mSelectedPosition - mFirstPosition);
        else
            return null;
    }

    abstract void layout(int i, boolean flag);

    protected void onMeasure(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(i);
        Rect rect = mSpinnerPadding;
        int l;
        int i1;
        boolean flag;
        boolean flag1;
        int j1;
        boolean flag2;
        int l1;
        if(mPaddingLeft > mSelectionLeftPadding)
            l = mPaddingLeft;
        else
            l = mSelectionLeftPadding;
        rect.left = l;
        rect = mSpinnerPadding;
        if(mPaddingTop > mSelectionTopPadding)
            l = mPaddingTop;
        else
            l = mSelectionTopPadding;
        rect.top = l;
        rect = mSpinnerPadding;
        if(mPaddingRight > mSelectionRightPadding)
            l = mPaddingRight;
        else
            l = mSelectionRightPadding;
        rect.right = l;
        rect = mSpinnerPadding;
        if(mPaddingBottom > mSelectionBottomPadding)
            l = mPaddingBottom;
        else
            l = mSelectionBottomPadding;
        rect.bottom = l;
        if(mDataChanged)
            handleDataChanged();
        i1 = 0;
        flag = false;
        flag1 = true;
        j1 = getSelectedItemPosition();
        flag2 = flag1;
        l1 = i1;
        l = ((flag) ? 1 : 0);
        if(j1 >= 0)
        {
            flag2 = flag1;
            l1 = i1;
            l = ((flag) ? 1 : 0);
            if(mAdapter != null)
            {
                flag2 = flag1;
                l1 = i1;
                l = ((flag) ? 1 : 0);
                if(j1 < mAdapter.getCount())
                {
                    View view1 = mRecycler.get(j1);
                    View view = view1;
                    if(view1 == null)
                    {
                        View view2 = mAdapter.getView(j1, null, this);
                        view = view2;
                        if(view2.getImportantForAccessibility() == 0)
                        {
                            view2.setImportantForAccessibility(1);
                            view = view2;
                        }
                    }
                    flag2 = flag1;
                    l1 = i1;
                    l = ((flag) ? 1 : 0);
                    if(view != null)
                    {
                        mRecycler.put(j1, view);
                        if(view.getLayoutParams() == null)
                        {
                            mBlockLayoutRequests = true;
                            view.setLayoutParams(generateDefaultLayoutParams());
                            mBlockLayoutRequests = false;
                        }
                        measureChild(view, i, j);
                        l1 = getChildHeight(view) + mSpinnerPadding.top + mSpinnerPadding.bottom;
                        l = getChildWidth(view) + mSpinnerPadding.left + mSpinnerPadding.right;
                        flag2 = false;
                    }
                }
            }
        }
        i1 = l1;
        l1 = l;
        if(flag2)
        {
            int k1 = mSpinnerPadding.top + mSpinnerPadding.bottom;
            i1 = k1;
            l1 = l;
            if(k == 0)
            {
                l1 = mSpinnerPadding.left + mSpinnerPadding.right;
                i1 = k1;
            }
        }
        l = Math.max(i1, getSuggestedMinimumHeight());
        l1 = Math.max(l1, getSuggestedMinimumWidth());
        l = resolveSizeAndState(l, j, 0);
        setMeasuredDimension(resolveSizeAndState(l1, i, 0), l);
        mHeightMeasureSpec = j;
        mWidthMeasureSpec = i;
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        if(((SavedState) (parcelable)).selectedId >= 0L)
        {
            mDataChanged = true;
            mNeedSync = true;
            mSyncRowId = ((SavedState) (parcelable)).selectedId;
            mSyncPosition = ((SavedState) (parcelable)).position;
            mSyncMode = 0;
            requestLayout();
        }
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.selectedId = getSelectedItemId();
        if(savedstate.selectedId >= 0L)
            savedstate.position = getSelectedItemPosition();
        else
            savedstate.position = -1;
        return savedstate;
    }

    public int pointToPosition(int i, int j)
    {
        Rect rect = mTouchFrame;
        Rect rect1 = rect;
        if(rect == null)
        {
            mTouchFrame = new Rect();
            rect1 = mTouchFrame;
        }
        for(int k = getChildCount() - 1; k >= 0; k--)
        {
            View view = getChildAt(k);
            if(view.getVisibility() != 0)
                continue;
            view.getHitRect(rect1);
            if(rect1.contains(i, j))
                return mFirstPosition + k;
        }

        return -1;
    }

    void recycleAllViews()
    {
        int i = getChildCount();
        RecycleBin recyclebin = mRecycler;
        int j = mFirstPosition;
        for(int k = 0; k < i; k++)
            recyclebin.put(j + k, getChildAt(k));

    }

    public void requestLayout()
    {
        if(!mBlockLayoutRequests)
            super.requestLayout();
    }

    void resetList()
    {
        mDataChanged = false;
        mNeedSync = false;
        removeAllViewsInLayout();
        mOldSelectedPosition = -1;
        mOldSelectedRowId = 0x8000000000000000L;
        setSelectedPositionInt(-1);
        setNextSelectedPositionInt(-1);
        invalidate();
    }

    public volatile void setAdapter(Adapter adapter)
    {
        setAdapter((SpinnerAdapter)adapter);
    }

    public void setAdapter(SpinnerAdapter spinneradapter)
    {
        if(mAdapter != null)
        {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
            resetList();
        }
        mAdapter = spinneradapter;
        mOldSelectedPosition = -1;
        mOldSelectedRowId = 0x8000000000000000L;
        if(mAdapter != null)
        {
            mOldItemCount = mItemCount;
            mItemCount = mAdapter.getCount();
            checkFocus();
            mDataSetObserver = new AdapterView.AdapterDataSetObserver(this);
            mAdapter.registerDataSetObserver(mDataSetObserver);
            int i;
            if(mItemCount > 0)
                i = 0;
            else
                i = -1;
            setSelectedPositionInt(i);
            setNextSelectedPositionInt(i);
            if(mItemCount == 0)
                checkSelectionChanged();
        } else
        {
            checkFocus();
            resetList();
            checkSelectionChanged();
        }
        requestLayout();
    }

    public void setSelection(int i)
    {
        setNextSelectedPositionInt(i);
        requestLayout();
        invalidate();
    }

    public void setSelection(int i, boolean flag)
    {
        if(flag && mFirstPosition <= i)
        {
            if(i <= (mFirstPosition + getChildCount()) - 1)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        setSelectionInt(i, flag);
    }

    void setSelectionInt(int i, boolean flag)
    {
        if(i != mOldSelectedPosition)
        {
            mBlockLayoutRequests = true;
            int j = mSelectedPosition;
            setNextSelectedPositionInt(i);
            layout(i - j, flag);
            mBlockLayoutRequests = false;
        }
    }

    private static final String LOG_TAG = android/widget/AbsSpinner.getSimpleName();
    SpinnerAdapter mAdapter;
    private DataSetObserver mDataSetObserver;
    int mHeightMeasureSpec;
    final RecycleBin mRecycler;
    int mSelectionBottomPadding;
    int mSelectionLeftPadding;
    int mSelectionRightPadding;
    int mSelectionTopPadding;
    final Rect mSpinnerPadding;
    private Rect mTouchFrame;
    int mWidthMeasureSpec;

}
