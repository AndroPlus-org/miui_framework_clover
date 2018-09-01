// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.*;
import android.view.*;
import com.android.internal.view.menu.ShowableListMenu;

// Referenced classes of package android.widget:
//            PopupWindow, DropDownListView, LinearLayout, ListAdapter, 
//            ListView, AbsListView, ForwardingListener, AdapterView

public class ListPopupWindow
    implements ShowableListMenu
{
    private class ListSelectorHider
        implements Runnable
    {

        public void run()
        {
            clearListSelection();
        }

        final ListPopupWindow this$0;

        private ListSelectorHider()
        {
            this$0 = ListPopupWindow.this;
            super();
        }

        ListSelectorHider(ListSelectorHider listselectorhider)
        {
            this();
        }
    }

    private class PopupDataSetObserver extends DataSetObserver
    {

        public void onChanged()
        {
            if(isShowing())
                show();
        }

        public void onInvalidated()
        {
            dismiss();
        }

        final ListPopupWindow this$0;

        private PopupDataSetObserver()
        {
            this$0 = ListPopupWindow.this;
            super();
        }

        PopupDataSetObserver(PopupDataSetObserver popupdatasetobserver)
        {
            this();
        }
    }

    private class PopupScrollListener
        implements AbsListView.OnScrollListener
    {

        public void onScroll(AbsListView abslistview, int i, int j, int k)
        {
        }

        public void onScrollStateChanged(AbsListView abslistview, int i)
        {
            if(i == 1 && isInputMethodNotNeeded() ^ true && mPopup.getContentView() != null)
            {
                ListPopupWindow._2D_get1(ListPopupWindow.this).removeCallbacks(ListPopupWindow._2D_get2(ListPopupWindow.this));
                ListPopupWindow._2D_get2(ListPopupWindow.this).run();
            }
        }

        final ListPopupWindow this$0;

        private PopupScrollListener()
        {
            this$0 = ListPopupWindow.this;
            super();
        }

        PopupScrollListener(PopupScrollListener popupscrolllistener)
        {
            this();
        }
    }

    private class PopupTouchInterceptor
        implements android.view.View.OnTouchListener
    {

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            int i;
            int j;
            int k;
            i = motionevent.getAction();
            j = (int)motionevent.getX();
            k = (int)motionevent.getY();
            if(i != 0 || mPopup == null || !mPopup.isShowing() || j < 0 || j >= mPopup.getWidth() || k < 0 || k >= mPopup.getHeight()) goto _L2; else goto _L1
_L1:
            ListPopupWindow._2D_get1(ListPopupWindow.this).postDelayed(ListPopupWindow._2D_get2(ListPopupWindow.this), 250L);
_L4:
            return false;
_L2:
            if(i == 1)
                ListPopupWindow._2D_get1(ListPopupWindow.this).removeCallbacks(ListPopupWindow._2D_get2(ListPopupWindow.this));
            if(true) goto _L4; else goto _L3
_L3:
        }

        final ListPopupWindow this$0;

        private PopupTouchInterceptor()
        {
            this$0 = ListPopupWindow.this;
            super();
        }

        PopupTouchInterceptor(PopupTouchInterceptor popuptouchinterceptor)
        {
            this();
        }
    }

    private class ResizePopupRunnable
        implements Runnable
    {

        public void run()
        {
            if(ListPopupWindow._2D_get0(ListPopupWindow.this) != null && ListPopupWindow._2D_get0(ListPopupWindow.this).isAttachedToWindow() && ListPopupWindow._2D_get0(ListPopupWindow.this).getCount() > ListPopupWindow._2D_get0(ListPopupWindow.this).getChildCount() && ListPopupWindow._2D_get0(ListPopupWindow.this).getChildCount() <= mListItemExpandMaximum)
            {
                mPopup.setInputMethodMode(2);
                show();
            }
        }

        final ListPopupWindow this$0;

        private ResizePopupRunnable()
        {
            this$0 = ListPopupWindow.this;
            super();
        }

        ResizePopupRunnable(ResizePopupRunnable resizepopuprunnable)
        {
            this();
        }
    }


    static DropDownListView _2D_get0(ListPopupWindow listpopupwindow)
    {
        return listpopupwindow.mDropDownList;
    }

    static Handler _2D_get1(ListPopupWindow listpopupwindow)
    {
        return listpopupwindow.mHandler;
    }

    static ResizePopupRunnable _2D_get2(ListPopupWindow listpopupwindow)
    {
        return listpopupwindow.mResizePopupRunnable;
    }

    public ListPopupWindow(Context context)
    {
        this(context, null, 0x10102ff, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x10102ff, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeset, int i, int j)
    {
        mDropDownHeight = -2;
        mDropDownWidth = -2;
        mDropDownWindowLayoutType = 1002;
        mIsAnimatedFromAnchor = true;
        mDropDownGravity = 0;
        mDropDownAlwaysVisible = false;
        mForceIgnoreOutsideTouch = false;
        mListItemExpandMaximum = 0x7fffffff;
        mPromptPosition = 0;
        mResizePopupRunnable = new ResizePopupRunnable(null);
        mTouchInterceptor = new PopupTouchInterceptor(null);
        mScrollListener = new PopupScrollListener(null);
        mHideSelector = new ListSelectorHider(null);
        mTempRect = new Rect();
        mContext = context;
        mHandler = new Handler(context.getMainLooper());
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.ListPopupWindow, i, j);
        mDropDownHorizontalOffset = typedarray.getDimensionPixelOffset(0, 0);
        mDropDownVerticalOffset = typedarray.getDimensionPixelOffset(1, 0);
        if(mDropDownVerticalOffset != 0)
            mDropDownVerticalOffsetSet = true;
        typedarray.recycle();
        mPopup = new PopupWindow(context, attributeset, i, j);
        mPopup.setInputMethodMode(1);
    }

    private int buildDropDown()
    {
        int i;
        int j;
        Object obj;
        Object obj1;
        View view;
        Object obj2;
        i = 0;
        j = 0;
        if(mDropDownList != null)
            break MISSING_BLOCK_LABEL_471;
        obj = mContext;
        mShowDropDownRunnable = new Runnable() {

            public void run()
            {
                View view1 = getAnchorView();
                if(view1 != null && view1.getWindowToken() != null)
                    show();
            }

            final ListPopupWindow this$0;

            
            {
                this$0 = ListPopupWindow.this;
                super();
            }
        }
;
        mDropDownList = createDropDownListView(((Context) (obj)), mModal ^ true);
        if(mDropDownListHighlight != null)
            mDropDownList.setSelector(mDropDownListHighlight);
        mDropDownList.setAdapter(mAdapter);
        mDropDownList.setOnItemClickListener(mItemClickListener);
        mDropDownList.setFocusable(true);
        mDropDownList.setFocusableInTouchMode(true);
        mDropDownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view1, int i1, long l1)
            {
                if(i1 != -1)
                {
                    adapterview = ListPopupWindow._2D_get0(ListPopupWindow.this);
                    if(adapterview != null)
                        adapterview.setListSelectionHidden(false);
                }
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            final ListPopupWindow this$0;

            
            {
                this$0 = ListPopupWindow.this;
                super();
            }
        }
);
        mDropDownList.setOnScrollListener(mScrollListener);
        if(mItemSelectedListener != null)
            mDropDownList.setOnItemSelectedListener(mItemSelectedListener);
        obj1 = mDropDownList;
        view = mPromptView;
        obj2 = obj1;
        if(view == null) goto _L2; else goto _L1
_L1:
        obj2 = new LinearLayout(((Context) (obj)));
        ((LinearLayout) (obj2)).setOrientation(1);
        obj = new LinearLayout.LayoutParams(-1, 0, 1.0F);
        mPromptPosition;
        JVM INSTR tableswitch 0 1: default 216
    //                   0 446
    //                   1 428;
           goto _L3 _L4 _L5
_L3:
        Log.e("ListPopupWindow", (new StringBuilder()).append("Invalid hint position ").append(mPromptPosition).toString());
_L6:
        int k;
        int l;
        if(mDropDownWidth >= 0)
        {
            i = 0x80000000;
            j = mDropDownWidth;
        } else
        {
            i = 0;
            j = 0;
        }
        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(j, i), 0);
        obj1 = (LinearLayout.LayoutParams)view.getLayoutParams();
        j = view.getMeasuredHeight() + ((LinearLayout.LayoutParams) (obj1)).topMargin + ((LinearLayout.LayoutParams) (obj1)).bottomMargin;
_L2:
        mPopup.setContentView(((View) (obj2)));
_L7:
        obj2 = mPopup.getBackground();
        boolean flag;
        if(obj2 != null)
        {
            ((Drawable) (obj2)).getPadding(mTempRect);
            i = mTempRect.top + mTempRect.bottom;
            k = i;
            if(!mDropDownVerticalOffsetSet)
            {
                mDropDownVerticalOffset = -mTempRect.top;
                k = i;
            }
        } else
        {
            mTempRect.setEmpty();
            k = 0;
        }
        if(mPopup.getInputMethodMode() == 2)
            flag = true;
        else
            flag = false;
        l = mPopup.getMaxAvailableHeight(getAnchorView(), mDropDownVerticalOffset, flag);
        if(mDropDownAlwaysVisible || mDropDownHeight == -1)
            return l + k;
        break MISSING_BLOCK_LABEL_534;
_L5:
        ((LinearLayout) (obj2)).addView(((View) (obj1)), ((android.view.ViewGroup.LayoutParams) (obj)));
        ((LinearLayout) (obj2)).addView(view);
          goto _L6
_L4:
        ((LinearLayout) (obj2)).addView(view);
        ((LinearLayout) (obj2)).addView(((View) (obj1)), ((android.view.ViewGroup.LayoutParams) (obj)));
          goto _L6
        obj2 = mPromptView;
        j = i;
        if(obj2 != null)
        {
            obj1 = (LinearLayout.LayoutParams)((View) (obj2)).getLayoutParams();
            j = ((View) (obj2)).getMeasuredHeight() + ((LinearLayout.LayoutParams) (obj1)).topMargin + ((LinearLayout.LayoutParams) (obj1)).bottomMargin;
        }
          goto _L7
        mDropDownWidth;
        JVM INSTR tableswitch -2 -1: default 560
    //                   -2 621
    //                   -1 660;
           goto _L8 _L9 _L10
_L8:
        i = android.view.View.MeasureSpec.makeMeasureSpec(mDropDownWidth, 0x40000000);
_L12:
        l = mDropDownList.measureHeightOfChildren(i, 0, -1, l - j, -1);
        i = j;
        if(l > 0)
            i = j + (k + (mDropDownList.getPaddingTop() + mDropDownList.getPaddingBottom()));
        return l + i;
_L9:
        i = android.view.View.MeasureSpec.makeMeasureSpec(mContext.getResources().getDisplayMetrics().widthPixels - (mTempRect.left + mTempRect.right), 0x80000000);
        continue; /* Loop/switch isn't completed */
_L10:
        i = android.view.View.MeasureSpec.makeMeasureSpec(mContext.getResources().getDisplayMetrics().widthPixels - (mTempRect.left + mTempRect.right), 0x40000000);
        if(true) goto _L12; else goto _L11
_L11:
    }

    private void removePromptView()
    {
        if(mPromptView != null)
        {
            android.view.ViewParent viewparent = mPromptView.getParent();
            if(viewparent instanceof ViewGroup)
                ((ViewGroup)viewparent).removeView(mPromptView);
        }
    }

    public void clearListSelection()
    {
        DropDownListView dropdownlistview = mDropDownList;
        if(dropdownlistview != null)
        {
            dropdownlistview.setListSelectionHidden(true);
            dropdownlistview.hideSelector();
            dropdownlistview.requestLayout();
        }
    }

    public android.view.View.OnTouchListener createDragToOpenListener(View view)
    {
        return new ForwardingListener(view) {

            public ShowableListMenu getPopup()
            {
                return ListPopupWindow.this;
            }

            final ListPopupWindow this$0;

            
            {
                this$0 = ListPopupWindow.this;
                super(view);
            }
        }
;
    }

    DropDownListView createDropDownListView(Context context, boolean flag)
    {
        return new DropDownListView(context, flag);
    }

    public void dismiss()
    {
        mPopup.dismiss();
        removePromptView();
        mPopup.setContentView(null);
        mDropDownList = null;
        mHandler.removeCallbacks(mResizePopupRunnable);
    }

    public View getAnchorView()
    {
        return mDropDownAnchorView;
    }

    public int getAnimationStyle()
    {
        return mPopup.getAnimationStyle();
    }

    public Drawable getBackground()
    {
        return mPopup.getBackground();
    }

    public int getHeight()
    {
        return mDropDownHeight;
    }

    public int getHorizontalOffset()
    {
        return mDropDownHorizontalOffset;
    }

    public int getInputMethodMode()
    {
        return mPopup.getInputMethodMode();
    }

    public ListView getListView()
    {
        return mDropDownList;
    }

    public int getPromptPosition()
    {
        return mPromptPosition;
    }

    public Object getSelectedItem()
    {
        if(!isShowing())
            return null;
        else
            return mDropDownList.getSelectedItem();
    }

    public long getSelectedItemId()
    {
        if(!isShowing())
            return 0x8000000000000000L;
        else
            return mDropDownList.getSelectedItemId();
    }

    public int getSelectedItemPosition()
    {
        if(!isShowing())
            return -1;
        else
            return mDropDownList.getSelectedItemPosition();
    }

    public View getSelectedView()
    {
        if(!isShowing())
            return null;
        else
            return mDropDownList.getSelectedView();
    }

    public int getSoftInputMode()
    {
        return mPopup.getSoftInputMode();
    }

    public int getVerticalOffset()
    {
        if(!mDropDownVerticalOffsetSet)
            return 0;
        else
            return mDropDownVerticalOffset;
    }

    public int getWidth()
    {
        return mDropDownWidth;
    }

    public boolean isDropDownAlwaysVisible()
    {
        return mDropDownAlwaysVisible;
    }

    public boolean isInputMethodNotNeeded()
    {
        boolean flag;
        if(mPopup.getInputMethodMode() == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isModal()
    {
        return mModal;
    }

    public boolean isShowing()
    {
        return mPopup.isShowing();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(!isShowing() || i == 62 || mDropDownList.getSelectedItemPosition() < 0 && !(KeyEvent.isConfirmKey(i) ^ true)) goto _L2; else goto _L1
_L1:
        int j;
        boolean flag;
        int k;
        int l;
        j = mDropDownList.getSelectedItemPosition();
        flag = mPopup.isAboveAnchor() ^ true;
        ListAdapter listadapter = mAdapter;
        k = 0x7fffffff;
        l = 0x80000000;
        if(listadapter != null)
        {
            boolean flag1 = listadapter.areAllItemsEnabled();
            if(flag1)
                k = 0;
            else
                k = mDropDownList.lookForSelectablePosition(0, true);
            if(flag1)
                l = listadapter.getCount() - 1;
            else
                l = mDropDownList.lookForSelectablePosition(listadapter.getCount() - 1, false);
        }
        while(flag && i == 19 && j <= k || !flag && i == 20 && j >= l) 
        {
            clearListSelection();
            mPopup.setInputMethodMode(1);
            show();
            return true;
        }
        mDropDownList.setListSelectionHidden(false);
        if(!mDropDownList.onKeyDown(i, keyevent)) goto _L4; else goto _L3
_L3:
        mPopup.setInputMethodMode(2);
        mDropDownList.requestFocusFromTouch();
        show();
        i;
        JVM INSTR lookupswitch 4: default 276
    //                   19: 278
    //                   20: 278
    //                   23: 278
    //                   66: 278;
           goto _L2 _L5 _L5 _L5 _L5
_L2:
        return false;
_L5:
        return true;
_L4:
        if(!flag || i != 20)
            continue; /* Loop/switch isn't completed */
        if(j != l) goto _L2; else goto _L6
_L6:
        return true;
        if(flag || i != 19 || j != k) goto _L2; else goto _L7
_L7:
        return true;
    }

    public boolean onKeyPreIme(int i, KeyEvent keyevent)
    {
        if(i == 4 && isShowing())
        {
            Object obj = mDropDownAnchorView;
            if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
            {
                obj = ((View) (obj)).getKeyDispatcherState();
                if(obj != null)
                    ((android.view.KeyEvent.DispatcherState) (obj)).startTracking(keyevent, this);
                return true;
            }
            if(keyevent.getAction() == 1)
            {
                obj = ((View) (obj)).getKeyDispatcherState();
                if(obj != null)
                    ((android.view.KeyEvent.DispatcherState) (obj)).handleUpEvent(keyevent);
                if(keyevent.isTracking() && keyevent.isCanceled() ^ true)
                {
                    dismiss();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(isShowing() && mDropDownList.getSelectedItemPosition() >= 0)
        {
            boolean flag = mDropDownList.onKeyUp(i, keyevent);
            if(flag && KeyEvent.isConfirmKey(i))
                dismiss();
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean performItemClick(int i)
    {
        if(isShowing())
        {
            if(mItemClickListener != null)
            {
                DropDownListView dropdownlistview = mDropDownList;
                View view = dropdownlistview.getChildAt(i - dropdownlistview.getFirstVisiblePosition());
                ListAdapter listadapter = dropdownlistview.getAdapter();
                mItemClickListener.onItemClick(dropdownlistview, view, i, listadapter.getItemId(i));
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void postShow()
    {
        mHandler.post(mShowDropDownRunnable);
    }

    public void setAdapter(ListAdapter listadapter)
    {
        if(mObserver != null) goto _L2; else goto _L1
_L1:
        mObserver = new PopupDataSetObserver(null);
_L4:
        mAdapter = listadapter;
        if(mAdapter != null)
            listadapter.registerDataSetObserver(mObserver);
        if(mDropDownList != null)
            mDropDownList.setAdapter(mAdapter);
        return;
_L2:
        if(mAdapter != null)
            mAdapter.unregisterDataSetObserver(mObserver);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setAnchorView(View view)
    {
        mDropDownAnchorView = view;
    }

    public void setAnimationStyle(int i)
    {
        mPopup.setAnimationStyle(i);
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        mPopup.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i)
    {
        Drawable drawable = mPopup.getBackground();
        if(drawable != null)
        {
            drawable.getPadding(mTempRect);
            mDropDownWidth = mTempRect.left + mTempRect.right + i;
        } else
        {
            setWidth(i);
        }
    }

    public void setDropDownAlwaysVisible(boolean flag)
    {
        mDropDownAlwaysVisible = flag;
    }

    public void setDropDownGravity(int i)
    {
        mDropDownGravity = i;
    }

    public void setEpicenterBounds(Rect rect)
    {
        mEpicenterBounds = rect;
    }

    public void setForceIgnoreOutsideTouch(boolean flag)
    {
        mForceIgnoreOutsideTouch = flag;
    }

    public void setHeight(int i)
    {
label0:
        {
            if(i < 0 && -2 != i && -1 != i)
            {
                if(mContext.getApplicationInfo().targetSdkVersion >= 26)
                    break label0;
                Log.e("ListPopupWindow", (new StringBuilder()).append("Negative value ").append(i).append(" passed to ListPopupWindow#setHeight").append(" produces undefined results").toString());
            }
            mDropDownHeight = i;
            return;
        }
        throw new IllegalArgumentException("Invalid height. Must be a positive value, MATCH_PARENT, or WRAP_CONTENT.");
    }

    public void setHorizontalOffset(int i)
    {
        mDropDownHorizontalOffset = i;
    }

    public void setInputMethodMode(int i)
    {
        mPopup.setInputMethodMode(i);
    }

    void setListItemExpandMax(int i)
    {
        mListItemExpandMaximum = i;
    }

    public void setListSelector(Drawable drawable)
    {
        mDropDownListHighlight = drawable;
    }

    public void setModal(boolean flag)
    {
        mModal = flag;
        mPopup.setFocusable(flag);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener ondismisslistener)
    {
        mPopup.setOnDismissListener(ondismisslistener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onitemclicklistener)
    {
        mItemClickListener = onitemclicklistener;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onitemselectedlistener)
    {
        mItemSelectedListener = onitemselectedlistener;
    }

    public void setOverlapAnchor(boolean flag)
    {
        mOverlapAnchorSet = true;
        mOverlapAnchor = flag;
    }

    public void setPromptPosition(int i)
    {
        mPromptPosition = i;
    }

    public void setPromptView(View view)
    {
        boolean flag = isShowing();
        if(flag)
            removePromptView();
        mPromptView = view;
        if(flag)
            show();
    }

    public void setSelection(int i)
    {
        DropDownListView dropdownlistview = mDropDownList;
        if(isShowing() && dropdownlistview != null)
        {
            dropdownlistview.setListSelectionHidden(false);
            dropdownlistview.setSelection(i);
            if(dropdownlistview.getChoiceMode() != 0)
                dropdownlistview.setItemChecked(i, true);
        }
    }

    public void setSoftInputMode(int i)
    {
        mPopup.setSoftInputMode(i);
    }

    public void setVerticalOffset(int i)
    {
        mDropDownVerticalOffset = i;
        mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int i)
    {
        mDropDownWidth = i;
    }

    public void setWindowLayoutType(int i)
    {
        mDropDownWindowLayoutType = i;
    }

    public void show()
    {
        boolean flag;
        boolean flag1;
        byte byte0;
        int i;
        boolean flag3;
        flag = false;
        flag1 = false;
        byte0 = -1;
        i = buildDropDown();
        flag3 = isInputMethodNotNeeded();
        mPopup.setAllowScrollingAnchorParent(flag3 ^ true);
        mPopup.setWindowLayoutType(mDropDownWindowLayoutType);
        if(!mPopup.isShowing()) goto _L2; else goto _L1
_L1:
        if(!getAnchorView().isAttachedToWindow())
            return;
        int j;
        if(mDropDownWidth == -1)
            j = -1;
        else
        if(mDropDownWidth == -2)
            j = getAnchorView().getWidth();
        else
            j = mDropDownWidth;
        if(mDropDownHeight == -1)
        {
            if(!flag3)
                i = -1;
            if(flag3)
            {
                Object obj = mPopup;
                int l;
                PopupWindow popupwindow2;
                int i1;
                if(mDropDownWidth == -1)
                    l = -1;
                else
                    l = 0;
                ((PopupWindow) (obj)).setWidth(l);
                mPopup.setHeight(0);
            } else
            {
                PopupWindow popupwindow = mPopup;
                byte byte1;
                if(mDropDownWidth == -1)
                    byte1 = -1;
                else
                    byte1 = 0;
                popupwindow.setWidth(byte1);
                mPopup.setHeight(-1);
            }
        } else
        if(mDropDownHeight != -2)
            i = mDropDownHeight;
        obj = mPopup;
        if(!mForceIgnoreOutsideTouch)
            flag1 = mDropDownAlwaysVisible ^ true;
        ((PopupWindow) (obj)).setOutsideTouchable(flag1);
        popupwindow2 = mPopup;
        obj = getAnchorView();
        i1 = mDropDownHorizontalOffset;
        l = mDropDownVerticalOffset;
        if(j < 0)
            j = -1;
        if(i < 0)
            i = byte0;
        popupwindow2.update(((View) (obj)), i1, l, j, i);
_L4:
        return;
_L2:
        boolean flag2;
        int k;
        PopupWindow popupwindow1;
        if(mDropDownWidth == -1)
            k = -1;
        else
        if(mDropDownWidth == -2)
            k = getAnchorView().getWidth();
        else
            k = mDropDownWidth;
        if(mDropDownHeight != -1)
            break; /* Loop/switch isn't completed */
        i = -1;
_L5:
        mPopup.setWidth(k);
        mPopup.setHeight(i);
        mPopup.setClipToScreenEnabled(true);
        popupwindow1 = mPopup;
        flag2 = flag;
        if(!mForceIgnoreOutsideTouch)
            flag2 = mDropDownAlwaysVisible ^ true;
        popupwindow1.setOutsideTouchable(flag2);
        mPopup.setTouchInterceptor(mTouchInterceptor);
        mPopup.setEpicenterBounds(mEpicenterBounds);
        if(mOverlapAnchorSet)
            mPopup.setOverlapAnchor(mOverlapAnchor);
        mPopup.showAsDropDown(getAnchorView(), mDropDownHorizontalOffset, mDropDownVerticalOffset, mDropDownGravity);
        mDropDownList.setSelection(-1);
        if(!mModal || mDropDownList.isInTouchMode())
            clearListSelection();
        if(!mModal)
            mHandler.post(mHideSelector);
        if(true) goto _L4; else goto _L3
_L3:
        if(mDropDownHeight != -2) goto _L6; else goto _L5
_L6:
        i = mDropDownHeight;
          goto _L5
    }

    private static final boolean DEBUG = false;
    private static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    private DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    private final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private boolean mIsAnimatedFromAnchor;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    private boolean mOverlapAnchor;
    private boolean mOverlapAnchorSet;
    PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    private final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;
}
