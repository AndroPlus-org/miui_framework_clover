// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.*;
import android.view.accessibility.*;
import android.view.autofill.AutofillManager;

// Referenced classes of package android.widget:
//            Adapter

public abstract class AdapterView extends ViewGroup
{
    public static class AdapterContextMenuInfo
        implements android.view.ContextMenu.ContextMenuInfo
    {

        public long id;
        public int position;
        public View targetView;

        public AdapterContextMenuInfo(View view, int i, long l)
        {
            targetView = view;
            position = i;
            id = l;
        }
    }

    class AdapterDataSetObserver extends DataSetObserver
    {

        public void clearSavedState()
        {
            mInstanceState = null;
        }

        public void onChanged()
        {
            mDataChanged = true;
            mOldItemCount = mItemCount;
            mItemCount = getAdapter().getCount();
            if(getAdapter().hasStableIds() && mInstanceState != null && mOldItemCount == 0 && mItemCount > 0)
            {
                AdapterView._2D_wrap2(AdapterView.this, mInstanceState);
                mInstanceState = null;
            } else
            {
                rememberSyncState();
            }
            checkFocus();
            requestLayout();
        }

        public void onInvalidated()
        {
            mDataChanged = true;
            if(getAdapter().hasStableIds())
                mInstanceState = AdapterView._2D_wrap0(AdapterView.this);
            mOldItemCount = mItemCount;
            mItemCount = 0;
            mSelectedPosition = -1;
            mSelectedRowId = 0x8000000000000000L;
            mNextSelectedPosition = -1;
            mNextSelectedRowId = 0x8000000000000000L;
            mNeedSync = false;
            checkFocus();
            requestLayout();
        }

        private Parcelable mInstanceState;
        final AdapterView this$0;

        AdapterDataSetObserver()
        {
            this$0 = AdapterView.this;
            super();
            mInstanceState = null;
        }
    }

    public static interface OnItemClickListener
    {

        public abstract void onItemClick(AdapterView adapterview, View view, int i, long l);
    }

    public static interface OnItemLongClickListener
    {

        public abstract boolean onItemLongClick(AdapterView adapterview, View view, int i, long l);
    }

    public static interface OnItemSelectedListener
    {

        public abstract void onItemSelected(AdapterView adapterview, View view, int i, long l);

        public abstract void onNothingSelected(AdapterView adapterview);
    }

    private class SelectionNotifier
        implements Runnable
    {

        public void run()
        {
            AdapterView._2D_set0(AdapterView.this, null);
            if(mDataChanged && getViewRootImpl() != null && getViewRootImpl().isLayoutRequested())
            {
                if(getAdapter() != null)
                    AdapterView._2D_set0(AdapterView.this, this);
            } else
            {
                AdapterView._2D_wrap1(AdapterView.this);
            }
        }

        final AdapterView this$0;

        private SelectionNotifier()
        {
            this$0 = AdapterView.this;
            super();
        }

        SelectionNotifier(SelectionNotifier selectionnotifier)
        {
            this();
        }
    }


    static SelectionNotifier _2D_set0(AdapterView adapterview, SelectionNotifier selectionnotifier)
    {
        adapterview.mPendingSelectionNotifier = selectionnotifier;
        return selectionnotifier;
    }

    static Parcelable _2D_wrap0(AdapterView adapterview)
    {
        return adapterview.onSaveInstanceState();
    }

    static void _2D_wrap1(AdapterView adapterview)
    {
        adapterview.dispatchOnItemSelected();
    }

    static void _2D_wrap2(AdapterView adapterview, Parcelable parcelable)
    {
        adapterview.onRestoreInstanceState(parcelable);
    }

    public AdapterView(Context context)
    {
        this(context, null);
    }

    public AdapterView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public AdapterView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public AdapterView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mFirstPosition = 0;
        mSyncRowId = 0x8000000000000000L;
        mNeedSync = false;
        mInLayout = false;
        mNextSelectedPosition = -1;
        mNextSelectedRowId = 0x8000000000000000L;
        mSelectedPosition = -1;
        mSelectedRowId = 0x8000000000000000L;
        mOldSelectedPosition = -1;
        mOldSelectedRowId = 0x8000000000000000L;
        mDesiredFocusableState = 16;
        mBlockLayoutRequests = false;
        if(getImportantForAccessibility() == 0)
            setImportantForAccessibility(1);
        mDesiredFocusableState = getFocusable();
        if(mDesiredFocusableState == 16)
            super.setFocusable(0);
    }

    private void dispatchOnItemSelected()
    {
        fireOnSelected();
        performAccessibilityActionsOnSelected();
    }

    private void fireOnSelected()
    {
        if(mOnItemSelectedListener == null)
            return;
        int i = getSelectedItemPosition();
        if(i >= 0)
        {
            View view = getSelectedView();
            mOnItemSelectedListener.onItemSelected(this, view, i, getAdapter().getItemId(i));
        } else
        {
            mOnItemSelectedListener.onNothingSelected(this);
        }
    }

    private boolean isScrollableForAccessibility()
    {
        boolean flag = true;
        Adapter adapter = getAdapter();
        if(adapter != null)
        {
            int i = adapter.getCount();
            boolean flag1;
            if(i > 0)
            {
                flag1 = flag;
                if(getFirstVisiblePosition() <= 0)
                    if(getLastVisiblePosition() < i - 1)
                        flag1 = flag;
                    else
                        flag1 = false;
            } else
            {
                flag1 = false;
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    private void performAccessibilityActionsOnSelected()
    {
        if(!AccessibilityManager.getInstance(mContext).isEnabled())
            return;
        if(getSelectedItemPosition() >= 0)
            sendAccessibilityEvent(4);
    }

    private void updateEmptyStatus(boolean flag)
    {
        if(isInFilterMode())
            flag = false;
        if(flag)
        {
            if(mEmptyView != null)
            {
                mEmptyView.setVisibility(0);
                setVisibility(8);
            } else
            {
                setVisibility(0);
            }
            if(mDataChanged)
                onLayout(false, mLeft, mTop, mRight, mBottom);
        } else
        {
            if(mEmptyView != null)
                mEmptyView.setVisibility(8);
            setVisibility(0);
        }
    }

    public void addView(View view)
    {
        throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
    }

    public void addView(View view, int i)
    {
        throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
    }

    public void addView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
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

    void checkFocus()
    {
        boolean flag = false;
        Adapter adapter = getAdapter();
        int i;
        boolean flag1;
        boolean flag2;
        if(adapter == null || adapter.getCount() == 0)
            i = 1;
        else
            i = 0;
        if(i != 0)
            flag1 = isInFilterMode();
        else
            flag1 = true;
        if(flag1)
            flag2 = mDesiredFocusableInTouchModeState;
        else
            flag2 = false;
        super.setFocusableInTouchMode(flag2);
        i = ((flag) ? 1 : 0);
        if(flag1)
            i = mDesiredFocusableState;
        super.setFocusable(i);
        if(mEmptyView != null)
        {
            if(adapter != null)
                flag1 = adapter.isEmpty();
            else
                flag1 = true;
            updateEmptyStatus(flag1);
        }
    }

    void checkSelectionChanged()
    {
        if(mSelectedPosition != mOldSelectedPosition || mSelectedRowId != mOldSelectedRowId)
        {
            selectionChanged();
            mOldSelectedPosition = mSelectedPosition;
            mOldSelectedRowId = mSelectedRowId;
        }
        if(mPendingSelectionNotifier != null)
            mPendingSelectionNotifier.run();
    }

    public boolean dispatchPopulateAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        View view = getSelectedView();
        return view != null && view.getVisibility() == 0 && view.dispatchPopulateAccessibilityEvent(accessibilityevent);
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        dispatchThawSelfOnly(sparsearray);
    }

    protected void dispatchSaveInstanceState(SparseArray sparsearray)
    {
        dispatchFreezeSelfOnly(sparsearray);
    }

    protected void encodeProperties(ViewHierarchyEncoder viewhierarchyencoder)
    {
        super.encodeProperties(viewhierarchyencoder);
        viewhierarchyencoder.addProperty("scrolling:firstPosition", mFirstPosition);
        viewhierarchyencoder.addProperty("list:nextSelectedPosition", mNextSelectedPosition);
        viewhierarchyencoder.addProperty("list:nextSelectedRowId", mNextSelectedRowId);
        viewhierarchyencoder.addProperty("list:selectedPosition", mSelectedPosition);
        viewhierarchyencoder.addProperty("list:itemCount", mItemCount);
    }

    int findSyncPosition()
    {
        int i;
        long l;
        boolean flag;
        int k;
        long l1;
        int i1;
        int j1;
        Adapter adapter;
        boolean flag1;
        boolean flag2;
        i = mItemCount;
        if(i == 0)
            return -1;
        l = mSyncRowId;
        int j = mSyncPosition;
        if(l == 0x8000000000000000L)
            return -1;
        k = Math.min(i - 1, Math.max(0, j));
        l1 = SystemClock.uptimeMillis();
        i1 = k;
        j1 = k;
        flag = false;
        adapter = getAdapter();
        if(adapter == null)
            return -1;
_L4:
        if(SystemClock.uptimeMillis() > l1 + 100L)
            break; /* Loop/switch isn't completed */
        if(adapter.getItemId(k) == l)
            return k;
        if(j1 == i - 1)
            flag2 = true;
        else
            flag2 = false;
        if(i1 == 0)
            flag1 = true;
        else
            flag1 = false;
        if(!flag2 || !flag1) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        if(flag1 || flag && flag2 ^ true)
        {
            k = ++j1;
            flag = false;
        } else
        if(flag2 || !flag && flag1 ^ true)
        {
            k = --i1;
            flag = true;
        }
        if(true) goto _L4; else goto _L3
_L3:
        return -1;
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/AdapterView.getName();
    }

    public abstract Adapter getAdapter();

    public int getCount()
    {
        return mItemCount;
    }

    public View getEmptyView()
    {
        return mEmptyView;
    }

    public int getFirstVisiblePosition()
    {
        return mFirstPosition;
    }

    public Object getItemAtPosition(int i)
    {
        Object obj = null;
        Adapter adapter = getAdapter();
        Object obj1 = obj;
        if(adapter != null)
            if(i < 0)
                obj1 = obj;
            else
                obj1 = adapter.getItem(i);
        return obj1;
    }

    public long getItemIdAtPosition(int i)
    {
        Adapter adapter = getAdapter();
        long l;
        if(adapter == null || i < 0)
            l = 0x8000000000000000L;
        else
            l = adapter.getItemId(i);
        return l;
    }

    public int getLastVisiblePosition()
    {
        return (mFirstPosition + getChildCount()) - 1;
    }

    public final OnItemClickListener getOnItemClickListener()
    {
        return mOnItemClickListener;
    }

    public final OnItemLongClickListener getOnItemLongClickListener()
    {
        return mOnItemLongClickListener;
    }

    public final OnItemSelectedListener getOnItemSelectedListener()
    {
        return mOnItemSelectedListener;
    }

    public int getPositionForView(View view)
    {
_L1:
        View view1;
        boolean flag;
        try
        {
            view1 = (View)view.getParent();
        }
        // Misplaced declaration of an exception variable
        catch(View view)
        {
            return -1;
        }
        if(view1 == null)
            break MISSING_BLOCK_LABEL_32;
        flag = view1.equals(this);
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_32;
        view = view1;
          goto _L1
        if(view != null)
        {
            int i = getChildCount();
            for(int j = 0; j < i; j++)
                if(getChildAt(j).equals(view))
                    return mFirstPosition + j;

        }
        return -1;
    }

    public Object getSelectedItem()
    {
        Adapter adapter = getAdapter();
        int i = getSelectedItemPosition();
        if(adapter != null && adapter.getCount() > 0 && i >= 0)
            return adapter.getItem(i);
        else
            return null;
    }

    public long getSelectedItemId()
    {
        return mNextSelectedRowId;
    }

    public int getSelectedItemPosition()
    {
        return mNextSelectedPosition;
    }

    public abstract View getSelectedView();

    void handleDataChanged()
    {
        int i = mItemCount;
        int j = 0;
        boolean flag = false;
        if(i > 0)
        {
            boolean flag1 = flag;
            if(mNeedSync)
            {
                mNeedSync = false;
                j = findSyncPosition();
                flag1 = flag;
                if(j >= 0)
                {
                    flag1 = flag;
                    if(lookForSelectablePosition(j, true) == j)
                    {
                        setNextSelectedPositionInt(j);
                        flag1 = true;
                    }
                }
            }
            j = ((flag1) ? 1 : 0);
            if(!flag1)
            {
                int k = getSelectedItemPosition();
                j = k;
                if(k >= i)
                    j = i - 1;
                k = j;
                if(j < 0)
                    k = 0;
                j = lookForSelectablePosition(k, true);
                i = j;
                if(j < 0)
                    i = lookForSelectablePosition(k, false);
                j = ((flag1) ? 1 : 0);
                if(i >= 0)
                {
                    setNextSelectedPositionInt(i);
                    checkSelectionChanged();
                    j = 1;
                }
            }
        }
        if(j == 0)
        {
            mSelectedPosition = -1;
            mSelectedRowId = 0x8000000000000000L;
            mNextSelectedPosition = -1;
            mNextSelectedRowId = 0x8000000000000000L;
            mNeedSync = false;
            checkSelectionChanged();
        }
        notifySubtreeAccessibilityStateChangedIfNeeded();
    }

    boolean isInFilterMode()
    {
        return false;
    }

    int lookForSelectablePosition(int i, boolean flag)
    {
        return i;
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        removeCallbacks(mSelectionNotifier);
    }

    public void onInitializeAccessibilityEventInternal(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEventInternal(accessibilityevent);
        accessibilityevent.setScrollable(isScrollableForAccessibility());
        View view = getSelectedView();
        if(view != null)
            accessibilityevent.setEnabled(view.isEnabled());
        accessibilityevent.setCurrentItemIndex(getSelectedItemPosition());
        accessibilityevent.setFromIndex(getFirstVisiblePosition());
        accessibilityevent.setToIndex(getLastVisiblePosition());
        accessibilityevent.setItemCount(getCount());
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        accessibilitynodeinfo.setScrollable(isScrollableForAccessibility());
        View view = getSelectedView();
        if(view != null)
            accessibilitynodeinfo.setEnabled(view.isEnabled());
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        mLayoutHeight = getHeight();
    }

    public void onProvideAutofillStructure(ViewStructure viewstructure, int i)
    {
        super.onProvideAutofillStructure(viewstructure, i);
        Adapter adapter = getAdapter();
        if(adapter == null)
            return;
        CharSequence acharsequence[] = adapter.getAutofillOptions();
        if(acharsequence != null)
            viewstructure.setAutofillOptions(acharsequence);
    }

    public boolean onRequestSendAccessibilityEventInternal(View view, AccessibilityEvent accessibilityevent)
    {
        if(super.onRequestSendAccessibilityEventInternal(view, accessibilityevent))
        {
            AccessibilityEvent accessibilityevent1 = AccessibilityEvent.obtain();
            onInitializeAccessibilityEvent(accessibilityevent1);
            view.dispatchPopulateAccessibilityEvent(accessibilityevent1);
            accessibilityevent.appendRecord(accessibilityevent1);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean performItemClick(View view, int i, long l)
    {
        boolean flag;
        if(mOnItemClickListener != null)
        {
            playSoundEffect(0);
            mOnItemClickListener.onItemClick(this, view, i, l);
            flag = true;
        } else
        {
            flag = false;
        }
        if(view != null)
            view.sendAccessibilityEvent(1);
        return flag;
    }

    void rememberSyncState()
    {
        if(getChildCount() > 0)
        {
            mNeedSync = true;
            mSyncHeight = mLayoutHeight;
            if(mSelectedPosition >= 0)
            {
                View view = getChildAt(mSelectedPosition - mFirstPosition);
                mSyncRowId = mNextSelectedRowId;
                mSyncPosition = mNextSelectedPosition;
                if(view != null)
                    mSpecificTop = view.getTop();
                mSyncMode = 0;
            } else
            {
                View view1 = getChildAt(0);
                Adapter adapter = getAdapter();
                if(mFirstPosition >= 0 && mFirstPosition < adapter.getCount())
                    mSyncRowId = adapter.getItemId(mFirstPosition);
                else
                    mSyncRowId = -1L;
                mSyncPosition = mFirstPosition;
                if(view1 != null)
                    mSpecificTop = view1.getTop();
                mSyncMode = 1;
            }
        }
    }

    public void removeAllViews()
    {
        throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
    }

    public void removeView(View view)
    {
        throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
    }

    public void removeViewAt(int i)
    {
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
    }

    void selectionChanged()
    {
        mPendingSelectionNotifier = null;
        if(mOnItemSelectedListener != null || AccessibilityManager.getInstance(mContext).isEnabled())
            if(mInLayout || mBlockLayoutRequests)
            {
                AutofillManager autofillmanager;
                if(mSelectionNotifier == null)
                    mSelectionNotifier = new SelectionNotifier(null);
                else
                    removeCallbacks(mSelectionNotifier);
                post(mSelectionNotifier);
            } else
            {
                dispatchOnItemSelected();
            }
        autofillmanager = (AutofillManager)mContext.getSystemService(android/view/autofill/AutofillManager);
        if(autofillmanager != null)
            autofillmanager.notifyValueChanged(this);
    }

    public abstract void setAdapter(Adapter adapter);

    public void setEmptyView(View view)
    {
        mEmptyView = view;
        if(view != null && view.getImportantForAccessibility() == 0)
            view.setImportantForAccessibility(1);
        view = getAdapter();
        boolean flag;
        if(view != null)
            flag = view.isEmpty();
        else
            flag = true;
        updateEmptyStatus(flag);
    }

    public void setFocusable(int i)
    {
        Adapter adapter = getAdapter();
        boolean flag;
        int j;
        if(adapter == null || adapter.getCount() == 0)
            flag = true;
        else
            flag = false;
        mDesiredFocusableState = i;
        if((i & 0x11) == 0)
            mDesiredFocusableInTouchModeState = false;
        j = i;
        if(flag)
            if(isInFilterMode())
                j = i;
            else
                j = 0;
        super.setFocusable(j);
    }

    public void setFocusableInTouchMode(boolean flag)
    {
        boolean flag1 = true;
        Adapter adapter = getAdapter();
        boolean flag2;
        if(adapter == null || adapter.getCount() == 0)
            flag2 = true;
        else
            flag2 = false;
        mDesiredFocusableInTouchModeState = flag;
        if(flag)
            mDesiredFocusableState = 1;
        if(flag)
        {
            flag = flag1;
            if(flag2)
                flag = isInFilterMode();
        } else
        {
            flag = false;
        }
        super.setFocusableInTouchMode(flag);
    }

    void setNextSelectedPositionInt(int i)
    {
        mNextSelectedPosition = i;
        mNextSelectedRowId = getItemIdAtPosition(i);
        if(mNeedSync && mSyncMode == 0 && i >= 0)
        {
            mSyncPosition = i;
            mSyncRowId = mNextSelectedRowId;
        }
    }

    public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
    }

    public void setOnItemClickListener(OnItemClickListener onitemclicklistener)
    {
        mOnItemClickListener = onitemclicklistener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onitemlongclicklistener)
    {
        if(!isLongClickable())
            setLongClickable(true);
        mOnItemLongClickListener = onitemlongclicklistener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onitemselectedlistener)
    {
        mOnItemSelectedListener = onitemselectedlistener;
    }

    void setSelectedPositionInt(int i)
    {
        mSelectedPosition = i;
        mSelectedRowId = getItemIdAtPosition(i);
    }

    public abstract void setSelection(int i);

    public static final int INVALID_POSITION = -1;
    public static final long INVALID_ROW_ID = 0x8000000000000000L;
    public static final int ITEM_VIEW_TYPE_HEADER_OR_FOOTER = -2;
    public static final int ITEM_VIEW_TYPE_IGNORE = -1;
    static final int SYNC_FIRST_POSITION = 1;
    static final int SYNC_MAX_DURATION_MILLIS = 100;
    static final int SYNC_SELECTED_POSITION = 0;
    boolean mBlockLayoutRequests;
    boolean mDataChanged;
    private boolean mDesiredFocusableInTouchModeState;
    private int mDesiredFocusableState;
    private View mEmptyView;
    int mFirstPosition;
    boolean mInLayout;
    int mItemCount;
    private int mLayoutHeight;
    boolean mNeedSync;
    int mNextSelectedPosition;
    long mNextSelectedRowId;
    int mOldItemCount;
    int mOldSelectedPosition;
    long mOldSelectedRowId;
    OnItemClickListener mOnItemClickListener;
    OnItemLongClickListener mOnItemLongClickListener;
    OnItemSelectedListener mOnItemSelectedListener;
    private SelectionNotifier mPendingSelectionNotifier;
    int mSelectedPosition;
    long mSelectedRowId;
    private SelectionNotifier mSelectionNotifier;
    int mSpecificTop;
    long mSyncHeight;
    int mSyncMode;
    int mSyncPosition;
    long mSyncRowId;
}
