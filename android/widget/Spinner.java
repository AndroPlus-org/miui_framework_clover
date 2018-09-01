// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.*;
import android.view.*;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.view.menu.ShowableListMenu;

// Referenced classes of package android.widget:
//            AbsSpinner, SpinnerAdapter, ForwardingListener, ListAdapter, 
//            ListView, ThemedSpinnerAdapter, ListPopupWindow, AdapterView

public class Spinner extends AbsSpinner
    implements android.content.DialogInterface.OnClickListener
{
    private class DialogPopup
        implements SpinnerPopup, android.content.DialogInterface.OnClickListener
    {

        public void dismiss()
        {
            if(mPopup != null)
            {
                mPopup.dismiss();
                mPopup = null;
            }
        }

        public Drawable getBackground()
        {
            return null;
        }

        public CharSequence getHintText()
        {
            return mPrompt;
        }

        public int getHorizontalOffset()
        {
            return 0;
        }

        public int getVerticalOffset()
        {
            return 0;
        }

        public boolean isShowing()
        {
            boolean flag;
            if(mPopup != null)
                flag = mPopup.isShowing();
            else
                flag = false;
            return flag;
        }

        public void onClick(DialogInterface dialoginterface, int i)
        {
            setSelection(i);
            if(mOnItemClickListener != null)
                performItemClick(null, i, mListAdapter.getItemId(i));
            dismiss();
        }

        public void setAdapter(ListAdapter listadapter)
        {
            mListAdapter = listadapter;
        }

        public void setBackgroundDrawable(Drawable drawable)
        {
            Log.e("Spinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }

        public void setHorizontalOffset(int i)
        {
            Log.e("Spinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        public void setPromptText(CharSequence charsequence)
        {
            mPrompt = charsequence;
        }

        public void setVerticalOffset(int i)
        {
            Log.e("Spinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        public void show(int i, int j)
        {
            if(mListAdapter == null)
                return;
            Object obj = new android.app.AlertDialog.Builder(getPopupContext());
            if(mPrompt != null)
                ((android.app.AlertDialog.Builder) (obj)).setTitle(mPrompt);
            mPopup = ((android.app.AlertDialog.Builder) (obj)).setSingleChoiceItems(mListAdapter, getSelectedItemPosition(), this).create();
            obj = mPopup.getListView();
            ((ListView) (obj)).setTextDirection(i);
            ((ListView) (obj)).setTextAlignment(j);
            mPopup.show();
        }

        private ListAdapter mListAdapter;
        private AlertDialog mPopup;
        private CharSequence mPrompt;
        final Spinner this$0;

        private DialogPopup()
        {
            this$0 = Spinner.this;
            super();
        }

        DialogPopup(DialogPopup dialogpopup)
        {
            this();
        }
    }

    private static class DropDownAdapter
        implements ListAdapter, SpinnerAdapter
    {

        public boolean areAllItemsEnabled()
        {
            ListAdapter listadapter = mListAdapter;
            if(listadapter != null)
                return listadapter.areAllItemsEnabled();
            else
                return true;
        }

        public int getCount()
        {
            int i;
            if(mAdapter == null)
                i = 0;
            else
                i = mAdapter.getCount();
            return i;
        }

        public View getDropDownView(int i, View view, ViewGroup viewgroup)
        {
            Object obj = null;
            if(mAdapter == null)
                view = obj;
            else
                view = mAdapter.getDropDownView(i, view, viewgroup);
            return view;
        }

        public Object getItem(int i)
        {
            Object obj = null;
            if(mAdapter != null)
                obj = mAdapter.getItem(i);
            return obj;
        }

        public long getItemId(int i)
        {
            long l;
            if(mAdapter == null)
                l = -1L;
            else
                l = mAdapter.getItemId(i);
            return l;
        }

        public int getItemViewType(int i)
        {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            return getDropDownView(i, view, viewgroup);
        }

        public int getViewTypeCount()
        {
            return 1;
        }

        public boolean hasStableIds()
        {
            boolean flag;
            if(mAdapter != null)
                flag = mAdapter.hasStableIds();
            else
                flag = false;
            return flag;
        }

        public boolean isEmpty()
        {
            boolean flag = false;
            if(getCount() == 0)
                flag = true;
            return flag;
        }

        public boolean isEnabled(int i)
        {
            ListAdapter listadapter = mListAdapter;
            if(listadapter != null)
                return listadapter.isEnabled(i);
            else
                return true;
        }

        public void registerDataSetObserver(DataSetObserver datasetobserver)
        {
            if(mAdapter != null)
                mAdapter.registerDataSetObserver(datasetobserver);
        }

        public void unregisterDataSetObserver(DataSetObserver datasetobserver)
        {
            if(mAdapter != null)
                mAdapter.unregisterDataSetObserver(datasetobserver);
        }

        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(SpinnerAdapter spinneradapter, android.content.res.Resources.Theme theme)
        {
            mAdapter = spinneradapter;
            if(spinneradapter instanceof ListAdapter)
                mListAdapter = (ListAdapter)spinneradapter;
            if(theme != null && (spinneradapter instanceof ThemedSpinnerAdapter))
            {
                spinneradapter = (ThemedSpinnerAdapter)spinneradapter;
                if(spinneradapter.getDropDownViewTheme() == null)
                    spinneradapter.setDropDownViewTheme(theme);
            }
        }
    }

    private class DropdownPopup extends ListPopupWindow
        implements SpinnerPopup
    {

        static ListAdapter _2D_get0(DropdownPopup dropdownpopup)
        {
            return dropdownpopup.mAdapter;
        }

        static void _2D_wrap0(DropdownPopup dropdownpopup)
        {
            dropdownpopup.super.show();
        }

        void computeContentWidth()
        {
            Drawable drawable = getBackground();
            int i = 0;
            int j;
            int k;
            int l;
            if(drawable != null)
            {
                drawable.getPadding(Spinner._2D_get2(Spinner.this));
                int i1;
                int j1;
                int k1;
                if(isLayoutRtl())
                    i = Spinner._2D_get2(Spinner.this).right;
                else
                    i = -Spinner._2D_get2(Spinner.this).left;
            } else
            {
                Rect rect = Spinner._2D_get2(Spinner.this);
                Spinner._2D_get2(Spinner.this).right = 0;
                rect.left = 0;
            }
            j = getPaddingLeft();
            k = getPaddingRight();
            l = getWidth();
            if(mDropDownWidth == -2)
            {
                i1 = measureContentWidth((SpinnerAdapter)mAdapter, getBackground());
                j1 = Spinner._2D_get0(Spinner.this).getResources().getDisplayMetrics().widthPixels - Spinner._2D_get2(Spinner.this).left - Spinner._2D_get2(Spinner.this).right;
                k1 = i1;
                if(i1 > j1)
                    k1 = j1;
                setContentWidth(Math.max(k1, l - j - k));
            } else
            if(mDropDownWidth == -1)
                setContentWidth(l - j - k);
            else
                setContentWidth(mDropDownWidth);
            if(isLayoutRtl())
                i += l - k - getWidth();
            else
                i += j;
            setHorizontalOffset(i);
        }

        public CharSequence getHintText()
        {
            return mHintText;
        }

        public void setAdapter(ListAdapter listadapter)
        {
            super.setAdapter(listadapter);
            mAdapter = listadapter;
        }

        public void setPromptText(CharSequence charsequence)
        {
            mHintText = charsequence;
        }

        public void show(int i, int j)
        {
            boolean flag = isShowing();
            computeContentWidth();
            setInputMethodMode(2);
            super.show();
            ListView listview = getListView();
            listview.setChoiceMode(1);
            listview.setTextDirection(i);
            listview.setTextAlignment(j);
            setSelection(getSelectedItemPosition());
            if(flag)
                return;
            ViewTreeObserver viewtreeobserver = getViewTreeObserver();
            if(viewtreeobserver != null)
            {
                android.view.ViewTreeObserver.OnGlobalLayoutListener ongloballayoutlistener = new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

                    public void onGlobalLayout()
                    {
                        if(!Spinner._2D_wrap0(_fld0))
                        {
                            dismiss();
                        } else
                        {
                            computeContentWidth();
                            DropdownPopup._2D_wrap0(DropdownPopup.this);
                        }
                    }

                    final DropdownPopup this$1;

            
            {
                this$1 = DropdownPopup.this;
                super();
            }
                }
;
                viewtreeobserver.addOnGlobalLayoutListener(ongloballayoutlistener);
                setOnDismissListener(ongloballayoutlistener. new PopupWindow.OnDismissListener() {

                    public void onDismiss()
                    {
                        ViewTreeObserver viewtreeobserver = getViewTreeObserver();
                        if(viewtreeobserver != null)
                            viewtreeobserver.removeOnGlobalLayoutListener(layoutListener);
                    }

                    final DropdownPopup this$1;
                    final android.view.ViewTreeObserver.OnGlobalLayoutListener val$layoutListener;

            
            {
                this$1 = final_dropdownpopup;
                layoutListener = android.view.ViewTreeObserver.OnGlobalLayoutListener.this;
                super();
            }
                }
);
            }
        }

        private ListAdapter mAdapter;
        private CharSequence mHintText;
        final Spinner this$0;

        public DropdownPopup(Context context, AttributeSet attributeset, int i, int j)
        {
            this$0 = Spinner.this;
            super(context, attributeset, i, j);
            setAnchorView(Spinner.this);
            setModal(true);
            setPromptPosition(0);
            setOnItemClickListener(new _cls1());
        }
    }

    static class SavedState extends AbsSpinner.SavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            if(showDropdown)
                i = 1;
            else
                i = 0;
            parcel.writeByte((byte)i);
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
        boolean showDropdown;


        private SavedState(Parcel parcel)
        {
            boolean flag = false;
            super(parcel);
            if(parcel.readByte() != 0)
                flag = true;
            showDropdown = flag;
        }

        SavedState(Parcel parcel, SavedState savedstate)
        {
            this(parcel);
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    private static interface SpinnerPopup
    {

        public abstract void dismiss();

        public abstract Drawable getBackground();

        public abstract CharSequence getHintText();

        public abstract int getHorizontalOffset();

        public abstract int getVerticalOffset();

        public abstract boolean isShowing();

        public abstract void setAdapter(ListAdapter listadapter);

        public abstract void setBackgroundDrawable(Drawable drawable);

        public abstract void setHorizontalOffset(int i);

        public abstract void setPromptText(CharSequence charsequence);

        public abstract void setVerticalOffset(int i);

        public abstract void show(int i, int j);
    }


    static Context _2D_get0(Spinner spinner)
    {
        return spinner.mContext;
    }

    static SpinnerPopup _2D_get1(Spinner spinner)
    {
        return spinner.mPopup;
    }

    static Rect _2D_get2(Spinner spinner)
    {
        return spinner.mTempRect;
    }

    static boolean _2D_wrap0(Spinner spinner)
    {
        return spinner.isVisibleToUser();
    }

    public Spinner(Context context)
    {
        this(context, ((AttributeSet) (null)));
    }

    public Spinner(Context context, int i)
    {
        this(context, null, 0x1010081, i);
    }

    public Spinner(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010081);
    }

    public Spinner(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0, -1);
    }

    public Spinner(Context context, AttributeSet attributeset, int i, int j)
    {
        this(context, attributeset, i, 0, j);
    }

    public Spinner(Context context, AttributeSet attributeset, int i, int j, int k)
    {
        this(context, attributeset, i, j, k, null);
    }

    public Spinner(Context context, AttributeSet attributeset, int i, int j, int k, android.content.res.Resources.Theme theme)
    {
        TypedArray typedarray;
        super(context, attributeset, i, j);
        mTempRect = new Rect();
        typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Spinner, i, j);
        int l;
        if(theme != null)
        {
            mPopupContext = new ContextThemeWrapper(context, theme);
        } else
        {
            int i1 = typedarray.getResourceId(7, 0);
            if(i1 != 0)
                mPopupContext = new ContextThemeWrapper(context, i1);
            else
                mPopupContext = context;
        }
        l = k;
        if(k == -1)
            l = typedarray.getInt(5, 0);
        l;
        JVM INSTR tableswitch 0 1: default 96
    //                   0 186
    //                   1 217;
           goto _L1 _L2 _L3
_L1:
        mGravity = typedarray.getInt(0, 17);
        mDisableChildrenWhenDisabled = typedarray.getBoolean(8, false);
        typedarray.recycle();
        if(mTempAdapter != null)
        {
            setAdapter(mTempAdapter);
            mTempAdapter = null;
        }
        return;
_L2:
        mPopup = new DialogPopup(null);
        mPopup.setPromptText(typedarray.getString(3));
        continue; /* Loop/switch isn't completed */
_L3:
        context = new DropdownPopup(mPopupContext, attributeset, i, j);
        attributeset = mPopupContext.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Spinner, i, j);
        mDropDownWidth = attributeset.getLayoutDimension(4, -2);
        if(attributeset.hasValueOrEmpty(1))
            context.setListSelector(attributeset.getDrawable(1));
        context.setBackgroundDrawable(attributeset.getDrawable(2));
        context.setPromptText(typedarray.getString(3));
        attributeset.recycle();
        mPopup = context;
        mForwardingListener = new ForwardingListener(context) {

            public ShowableListMenu getPopup()
            {
                return popup;
            }

            public boolean onForwardingStarted()
            {
                if(!Spinner._2D_get1(Spinner.this).isShowing())
                    Spinner._2D_get1(Spinner.this).show(getTextDirection(), getTextAlignment());
                return true;
            }

            final Spinner this$0;
            final DropdownPopup val$popup;

            
            {
                this$0 = Spinner.this;
                popup = dropdownpopup;
                super(final_view);
            }
        }
;
        if(true) goto _L1; else goto _L4
_L4:
    }

    private View makeView(int i, boolean flag)
    {
        if(!mDataChanged)
        {
            View view = mRecycler.get(i);
            if(view != null)
            {
                setUpChild(view, flag);
                return view;
            }
        }
        View view1 = mAdapter.getView(i, null, this);
        setUpChild(view1, flag);
        return view1;
    }

    private void setUpChild(View view, boolean flag)
    {
        android.view.ViewGroup.LayoutParams layoutparams = view.getLayoutParams();
        android.view.ViewGroup.LayoutParams layoutparams1 = layoutparams;
        if(layoutparams == null)
            layoutparams1 = generateDefaultLayoutParams();
        addViewInLayout(view, 0, layoutparams1);
        view.setSelected(hasFocus());
        if(mDisableChildrenWhenDisabled)
            view.setEnabled(isEnabled());
        int i = ViewGroup.getChildMeasureSpec(mHeightMeasureSpec, mSpinnerPadding.top + mSpinnerPadding.bottom, layoutparams1.height);
        view.measure(ViewGroup.getChildMeasureSpec(mWidthMeasureSpec, mSpinnerPadding.left + mSpinnerPadding.right, layoutparams1.width), i);
        int j = mSpinnerPadding.top + (getMeasuredHeight() - mSpinnerPadding.bottom - mSpinnerPadding.top - view.getMeasuredHeight()) / 2;
        i = view.getMeasuredHeight();
        view.layout(0, j, view.getMeasuredWidth() + 0, j + i);
        if(!flag)
            removeViewInLayout(view);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/Spinner.getName();
    }

    public int getBaseline()
    {
        int i = -1;
        Object obj = null;
        View view;
        if(getChildCount() > 0)
        {
            view = getChildAt(0);
        } else
        {
            view = obj;
            if(mAdapter != null)
            {
                view = obj;
                if(mAdapter.getCount() > 0)
                {
                    view = makeView(0, false);
                    mRecycler.put(0, view);
                }
            }
        }
        if(view != null)
        {
            int j = view.getBaseline();
            if(j >= 0)
                i = view.getTop() + j;
            return i;
        } else
        {
            return -1;
        }
    }

    public int getDropDownHorizontalOffset()
    {
        return mPopup.getHorizontalOffset();
    }

    public int getDropDownVerticalOffset()
    {
        return mPopup.getVerticalOffset();
    }

    public int getDropDownWidth()
    {
        return mDropDownWidth;
    }

    public int getGravity()
    {
        return mGravity;
    }

    public Drawable getPopupBackground()
    {
        return mPopup.getBackground();
    }

    public Context getPopupContext()
    {
        return mPopupContext;
    }

    public CharSequence getPrompt()
    {
        return mPopup.getHintText();
    }

    public boolean isPopupShowing()
    {
        boolean flag;
        if(mPopup != null)
            flag = mPopup.isShowing();
        else
            flag = false;
        return flag;
    }

    void layout(int i, boolean flag)
    {
        int j;
        int k;
        j = mSpinnerPadding.left;
        k = mRight - mLeft - mSpinnerPadding.left - mSpinnerPadding.right;
        if(mDataChanged)
            handleDataChanged();
        if(mItemCount == 0)
        {
            resetList();
            return;
        }
        if(mNextSelectedPosition >= 0)
            setSelectedPositionInt(mNextSelectedPosition);
        recycleAllViews();
        removeAllViewsInLayout();
        mFirstPosition = mSelectedPosition;
        if(mAdapter == null) goto _L2; else goto _L1
_L1:
        View view;
        int l;
        int i1;
        view = makeView(mSelectedPosition, true);
        l = view.getMeasuredWidth();
        i = j;
        i1 = getLayoutDirection();
        Gravity.getAbsoluteGravity(mGravity, i1) & 7;
        JVM INSTR lookupswitch 2: default 160
    //                   1: 200
    //                   5: 215;
           goto _L3 _L4 _L5
_L3:
        view.offsetLeftAndRight(i);
_L2:
        mRecycler.clear();
        invalidate();
        checkSelectionChanged();
        mDataChanged = false;
        mNeedSync = false;
        setNextSelectedPositionInt(mSelectedPosition);
        return;
_L4:
        i = (k / 2 + j) - l / 2;
        continue; /* Loop/switch isn't completed */
_L5:
        i = (j + k) - l;
        if(true) goto _L3; else goto _L6
_L6:
    }

    int measureContentWidth(SpinnerAdapter spinneradapter, Drawable drawable)
    {
        if(spinneradapter == null)
            return 0;
        int i = 0;
        View view = null;
        int j = 0;
        int k = android.view.View.MeasureSpec.makeSafeMeasureSpec(getMeasuredWidth(), 0);
        int l = android.view.View.MeasureSpec.makeSafeMeasureSpec(getMeasuredHeight(), 0);
        int i1 = Math.max(0, getSelectedItemPosition());
        int j1 = Math.min(spinneradapter.getCount(), i1 + 15);
        for(int k1 = Math.max(0, i1 - (15 - (j1 - i1))); k1 < j1;)
        {
            int l1 = spinneradapter.getItemViewType(k1);
            i1 = j;
            if(l1 != j)
            {
                i1 = l1;
                view = null;
            }
            view = spinneradapter.getView(k1, view, this);
            if(view.getLayoutParams() == null)
                view.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
            view.measure(k, l);
            i = Math.max(i, view.getMeasuredWidth());
            k1++;
            j = i1;
        }

        i1 = i;
        if(drawable != null)
        {
            drawable.getPadding(mTempRect);
            i1 = i + (mTempRect.left + mTempRect.right);
        }
        return i1;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        setSelection(i);
        dialoginterface.dismiss();
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if(mPopup != null && mPopup.isShowing())
            mPopup.dismiss();
    }

    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilitynodeinfo);
        if(mAdapter != null)
            accessibilitynodeinfo.setCanOpenPopup(true);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        mInLayout = true;
        layout(0, false);
        mInLayout = false;
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if(mPopup != null && android.view.View.MeasureSpec.getMode(i) == 0x80000000)
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), measureContentWidth(getAdapter(), getBackground())), android.view.View.MeasureSpec.getSize(i)), getMeasuredHeight());
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        if(getPointerIcon() == null && isClickable() && isEnabled())
            return PointerIcon.getSystemIcon(getContext(), 1002);
        else
            return super.onResolvePointerIcon(motionevent, i);
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        if(((SavedState) (parcelable)).showDropdown)
        {
            parcelable = getViewTreeObserver();
            if(parcelable != null)
                parcelable.addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

                    public void onGlobalLayout()
                    {
                        if(!Spinner._2D_get1(Spinner.this).isShowing())
                            Spinner._2D_get1(Spinner.this).show(getTextDirection(), getTextAlignment());
                        ViewTreeObserver viewtreeobserver = getViewTreeObserver();
                        if(viewtreeobserver != null)
                            viewtreeobserver.removeOnGlobalLayoutListener(this);
                    }

                    final Spinner this$0;

            
            {
                this$0 = Spinner.this;
                super();
            }
                }
);
        }
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        boolean flag;
        if(mPopup != null)
            flag = mPopup.isShowing();
        else
            flag = false;
        savedstate.showDropdown = flag;
        return savedstate;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mForwardingListener != null && mForwardingListener.onTouch(this, motionevent))
            return true;
        else
            return super.onTouchEvent(motionevent);
    }

    public boolean performClick()
    {
        boolean flag = super.performClick();
        boolean flag2 = flag;
        if(!flag)
        {
            boolean flag1 = true;
            flag2 = flag1;
            if(!mPopup.isShowing())
            {
                mPopup.show(getTextDirection(), getTextAlignment());
                flag2 = flag1;
            }
        }
        return flag2;
    }

    public void setAdapter(SpinnerAdapter spinneradapter)
    {
        if(mPopup == null)
        {
            mTempAdapter = spinneradapter;
            return;
        }
        super.setAdapter(spinneradapter);
        mRecycler.clear();
        if(mContext.getApplicationInfo().targetSdkVersion >= 21 && spinneradapter != null && spinneradapter.getViewTypeCount() != 1)
            throw new IllegalArgumentException("Spinner adapter view type count must be 1");
        Context context;
        if(mPopupContext == null)
            context = mContext;
        else
            context = mPopupContext;
        mPopup.setAdapter(new DropDownAdapter(spinneradapter, context.getTheme()));
    }

    public void setDropDownHorizontalOffset(int i)
    {
        mPopup.setHorizontalOffset(i);
    }

    public void setDropDownVerticalOffset(int i)
    {
        mPopup.setVerticalOffset(i);
    }

    public void setDropDownWidth(int i)
    {
        if(!(mPopup instanceof DropdownPopup))
        {
            Log.e("Spinner", "Cannot set dropdown width for MODE_DIALOG, ignoring");
            return;
        } else
        {
            mDropDownWidth = i;
            return;
        }
    }

    public void setEnabled(boolean flag)
    {
        super.setEnabled(flag);
        if(mDisableChildrenWhenDisabled)
        {
            int i = getChildCount();
            for(int j = 0; j < i; j++)
                getChildAt(j).setEnabled(flag);

        }
    }

    public void setGravity(int i)
    {
        if(mGravity != i)
        {
            int j = i;
            if((i & 7) == 0)
                j = i | 0x800003;
            mGravity = j;
            requestLayout();
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onitemclicklistener)
    {
        throw new RuntimeException("setOnItemClickListener cannot be used with a spinner.");
    }

    public void setOnItemClickListenerInt(AdapterView.OnItemClickListener onitemclicklistener)
    {
        super.setOnItemClickListener(onitemclicklistener);
    }

    public void setPopupBackgroundDrawable(Drawable drawable)
    {
        if(!(mPopup instanceof DropdownPopup))
        {
            Log.e("Spinner", "setPopupBackgroundDrawable: incompatible spinner mode; ignoring...");
            return;
        } else
        {
            mPopup.setBackgroundDrawable(drawable);
            return;
        }
    }

    public void setPopupBackgroundResource(int i)
    {
        setPopupBackgroundDrawable(getPopupContext().getDrawable(i));
    }

    public void setPrompt(CharSequence charsequence)
    {
        mPopup.setPromptText(charsequence);
    }

    public void setPromptId(int i)
    {
        setPrompt(getContext().getText(i));
    }

    private static final int MAX_ITEMS_MEASURED = 15;
    public static final int MODE_DIALOG = 0;
    public static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "Spinner";
    private boolean mDisableChildrenWhenDisabled;
    int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    private int mGravity;
    private SpinnerPopup mPopup;
    private final Context mPopupContext;
    private SpinnerAdapter mTempAdapter;
    private final Rect mTempRect;

    // Unreferenced inner class android/widget/Spinner$DropdownPopup$1

/* anonymous class */
    class DropdownPopup._cls1
        implements AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            setSelection(i);
            if(mOnItemClickListener != null)
                performItemClick(view, i, DropdownPopup._2D_get0(DropdownPopup.this).getItemId(i));
            dismiss();
        }

        final DropdownPopup this$1;

            
            {
                this$1 = DropdownPopup.this;
                super();
            }
    }

}
