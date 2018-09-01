// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.InputMethodManager;
import java.lang.ref.WeakReference;

// Referenced classes of package android.widget:
//            EditText, ListPopupWindow, ListAdapter, Filter, 
//            Filterable, TextView, ListView, AdapterView

public class AutoCompleteTextView extends EditText
    implements Filter.FilterListener
{
    private class DropDownItemClickListener
        implements AdapterView.OnItemClickListener
    {

        public void onItemClick(AdapterView adapterview, View view, int i, long l)
        {
            AutoCompleteTextView._2D_wrap1(AutoCompleteTextView.this, view, i, l);
        }

        final AutoCompleteTextView this$0;

        private DropDownItemClickListener()
        {
            this$0 = AutoCompleteTextView.this;
            super();
        }

        DropDownItemClickListener(DropDownItemClickListener dropdownitemclicklistener)
        {
            this();
        }
    }

    private class MyWatcher
        implements TextWatcher
    {

        public void afterTextChanged(Editable editable)
        {
            doAfterTextChanged();
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
            doBeforeTextChanged();
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        final AutoCompleteTextView this$0;

        private MyWatcher()
        {
            this$0 = AutoCompleteTextView.this;
            super();
        }

        MyWatcher(MyWatcher mywatcher)
        {
            this();
        }
    }

    public static interface OnDismissListener
    {

        public abstract void onDismiss();
    }

    private class PassThroughClickListener
        implements android.view.View.OnClickListener
    {

        static android.view.View.OnClickListener _2D_set0(PassThroughClickListener passthroughclicklistener, android.view.View.OnClickListener onclicklistener)
        {
            passthroughclicklistener.mWrapped = onclicklistener;
            return onclicklistener;
        }

        public void onClick(View view)
        {
            AutoCompleteTextView._2D_wrap0(AutoCompleteTextView.this);
            if(mWrapped != null)
                mWrapped.onClick(view);
        }

        private android.view.View.OnClickListener mWrapped;
        final AutoCompleteTextView this$0;

        private PassThroughClickListener()
        {
            this$0 = AutoCompleteTextView.this;
            super();
        }

        PassThroughClickListener(PassThroughClickListener passthroughclicklistener)
        {
            this();
        }
    }

    private static class PopupDataSetObserver extends DataSetObserver
    {

        static WeakReference _2D_get0(PopupDataSetObserver popupdatasetobserver)
        {
            return popupdatasetobserver.mViewReference;
        }

        public void onChanged()
        {
            AutoCompleteTextView autocompletetextview = (AutoCompleteTextView)mViewReference.get();
            if(autocompletetextview != null && AutoCompleteTextView._2D_get0(autocompletetextview) != null)
                autocompletetextview.post(updateRunnable);
        }

        private final WeakReference mViewReference;
        private final Runnable updateRunnable;

        private PopupDataSetObserver(AutoCompleteTextView autocompletetextview)
        {
            updateRunnable = new _cls1();
            mViewReference = new WeakReference(autocompletetextview);
        }

        PopupDataSetObserver(AutoCompleteTextView autocompletetextview, PopupDataSetObserver popupdatasetobserver)
        {
            this(autocompletetextview);
        }
    }

    public static interface Validator
    {

        public abstract CharSequence fixText(CharSequence charsequence);

        public abstract boolean isValid(CharSequence charsequence);
    }


    static ListAdapter _2D_get0(AutoCompleteTextView autocompletetextview)
    {
        return autocompletetextview.mAdapter;
    }

    static void _2D_wrap0(AutoCompleteTextView autocompletetextview)
    {
        autocompletetextview.onClickImpl();
    }

    static void _2D_wrap1(AutoCompleteTextView autocompletetextview, View view, int i, long l)
    {
        autocompletetextview.performCompletion(view, i, l);
    }

    static void _2D_wrap2(AutoCompleteTextView autocompletetextview, int i)
    {
        autocompletetextview.updateDropDownForFilter(i);
    }

    public AutoCompleteTextView(Context context)
    {
        this(context, null);
    }

    public AutoCompleteTextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101006b);
    }

    public AutoCompleteTextView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public AutoCompleteTextView(Context context, AttributeSet attributeset, int i, int j)
    {
        this(context, attributeset, i, j, null);
    }

    public AutoCompleteTextView(Context context, AttributeSet attributeset, int i, int j, android.content.res.Resources.Theme theme)
    {
        super(context, attributeset, i, j);
        mDropDownDismissedOnCompletion = true;
        mLastKeyCode = 0;
        mValidator = null;
        mPopupCanBeUpdated = true;
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AutoCompleteTextView, i, j);
        int k;
        int l;
        int j1;
        CharSequence charsequence;
        if(theme != null)
        {
            mPopupContext = new ContextThemeWrapper(context, theme);
        } else
        {
            int i1 = typedarray.getResourceId(8, 0);
            if(i1 != 0)
                mPopupContext = new ContextThemeWrapper(context, i1);
            else
                mPopupContext = context;
        }
        if(mPopupContext != context)
            context = mPopupContext.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.AutoCompleteTextView, i, j);
        else
            context = typedarray;
        theme = context.getDrawable(3);
        k = context.getLayoutDimension(5, -2);
        l = context.getLayoutDimension(7, -2);
        j1 = context.getResourceId(1, 0x10900f8);
        charsequence = context.getText(0);
        if(context != typedarray)
            context.recycle();
        mPopup = new ListPopupWindow(mPopupContext, attributeset, i, j);
        mPopup.setSoftInputMode(16);
        mPopup.setPromptPosition(1);
        mPopup.setListSelector(theme);
        mPopup.setOnItemClickListener(new DropDownItemClickListener(null));
        mPopup.setWidth(k);
        mPopup.setHeight(l);
        mHintResource = j1;
        setCompletionHint(charsequence);
        mDropDownAnchorId = typedarray.getResourceId(6, -1);
        mThreshold = typedarray.getInt(2, 2);
        typedarray.recycle();
        i = getInputType();
        if((i & 0xf) == 1)
            setRawInputType(i | 0x10000);
        setFocusable(true);
        addTextChangedListener(new MyWatcher(null));
        mPassThroughClickListener = new PassThroughClickListener(null);
        super.setOnClickListener(mPassThroughClickListener);
    }

    private void buildImeCompletions()
    {
        ListAdapter listadapter = mAdapter;
        if(listadapter != null)
        {
            InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
            if(inputmethodmanager != null)
            {
                int i = Math.min(listadapter.getCount(), 20);
                CompletionInfo acompletioninfo1[] = new CompletionInfo[i];
                int j = 0;
                for(int k = 0; k < i;)
                {
                    int l = j;
                    if(listadapter.isEnabled(k))
                    {
                        Object obj = listadapter.getItem(k);
                        acompletioninfo1[j] = new CompletionInfo(listadapter.getItemId(k), j, convertSelectionToString(obj));
                        l = j + 1;
                    }
                    k++;
                    j = l;
                }

                CompletionInfo acompletioninfo[] = acompletioninfo1;
                if(j != i)
                {
                    acompletioninfo = new CompletionInfo[j];
                    System.arraycopy(acompletioninfo1, 0, acompletioninfo, 0, j);
                }
                inputmethodmanager.displayCompletions(this, acompletioninfo);
            }
        }
    }

    private void onClickImpl()
    {
        if(isPopupShowing())
            ensureImeVisible(true);
    }

    private void performCompletion(View view, int i, long l)
    {
label0:
        {
            Object obj;
            int j;
label1:
            {
                if(!isPopupShowing())
                    break label0;
                if(i < 0)
                    obj = mPopup.getSelectedItem();
                else
                    obj = mAdapter.getItem(i);
                if(obj == null)
                {
                    Log.w("AutoCompleteTextView", "performCompletion: no selected item");
                    return;
                }
                mBlockCompletion = true;
                replaceText(convertSelectionToString(obj));
                mBlockCompletion = false;
                if(mItemClickListener == null)
                    break label0;
                obj = mPopup;
                if(view != null)
                {
                    j = i;
                    if(i >= 0)
                        break label1;
                }
                view = ((ListPopupWindow) (obj)).getSelectedView();
                j = ((ListPopupWindow) (obj)).getSelectedItemPosition();
                l = ((ListPopupWindow) (obj)).getSelectedItemId();
            }
            mItemClickListener.onItemClick(((ListPopupWindow) (obj)).getListView(), view, j, l);
        }
        if(mDropDownDismissedOnCompletion && mPopup.isDropDownAlwaysVisible() ^ true)
            dismissDropDown();
    }

    private void updateDropDownForFilter(int i)
    {
        boolean flag;
        boolean flag1;
        if(getWindowVisibility() == 8)
            return;
        flag = mPopup.isDropDownAlwaysVisible();
        flag1 = enoughToFilter();
        if(i <= 0 && !flag || !flag1) goto _L2; else goto _L1
_L1:
        if(hasFocus() && hasWindowFocus() && mPopupCanBeUpdated)
            showDropDown();
_L4:
        return;
_L2:
        if(!flag && isPopupShowing())
        {
            dismissDropDown();
            mPopupCanBeUpdated = true;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void clearListSelection()
    {
        mPopup.clearListSelection();
    }

    protected CharSequence convertSelectionToString(Object obj)
    {
        return mFilter.convertResultToString(obj);
    }

    public void dismissDropDown()
    {
        InputMethodManager inputmethodmanager = InputMethodManager.peekInstance();
        if(inputmethodmanager != null)
            inputmethodmanager.displayCompletions(this, null);
        mPopup.dismiss();
        mPopupCanBeUpdated = false;
    }

    void doAfterTextChanged()
    {
        if(mBlockCompletion)
            return;
        if(mOpenBefore && isPopupShowing() ^ true)
            return;
        if(!enoughToFilter()) goto _L2; else goto _L1
_L1:
        if(mFilter != null)
        {
            mPopupCanBeUpdated = true;
            performFiltering(getText(), mLastKeyCode);
        }
_L4:
        return;
_L2:
        if(!mPopup.isDropDownAlwaysVisible())
            dismissDropDown();
        if(mFilter != null)
            mFilter.filter(null);
        if(true) goto _L4; else goto _L3
_L3:
    }

    void doBeforeTextChanged()
    {
        if(mBlockCompletion)
        {
            return;
        } else
        {
            mOpenBefore = isPopupShowing();
            return;
        }
    }

    public boolean enoughToFilter()
    {
        boolean flag;
        if(getText().length() >= mThreshold)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void ensureImeVisible(boolean flag)
    {
        ListPopupWindow listpopupwindow = mPopup;
        int i;
        if(flag)
            i = 1;
        else
            i = 2;
        listpopupwindow.setInputMethodMode(i);
        if(mPopup.isDropDownAlwaysVisible() || mFilter != null && enoughToFilter())
            showDropDown();
    }

    public ListAdapter getAdapter()
    {
        return mAdapter;
    }

    public CharSequence getCompletionHint()
    {
        return mHintText;
    }

    public int getDropDownAnchor()
    {
        return mDropDownAnchorId;
    }

    public int getDropDownAnimationStyle()
    {
        return mPopup.getAnimationStyle();
    }

    public Drawable getDropDownBackground()
    {
        return mPopup.getBackground();
    }

    public int getDropDownHeight()
    {
        return mPopup.getHeight();
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
        return mPopup.getWidth();
    }

    protected Filter getFilter()
    {
        return mFilter;
    }

    public AdapterView.OnItemClickListener getItemClickListener()
    {
        return mItemClickListener;
    }

    public AdapterView.OnItemSelectedListener getItemSelectedListener()
    {
        return mItemSelectedListener;
    }

    public int getListSelection()
    {
        return mPopup.getSelectedItemPosition();
    }

    public AdapterView.OnItemClickListener getOnItemClickListener()
    {
        return mItemClickListener;
    }

    public AdapterView.OnItemSelectedListener getOnItemSelectedListener()
    {
        return mItemSelectedListener;
    }

    public int getThreshold()
    {
        return mThreshold;
    }

    public Validator getValidator()
    {
        return mValidator;
    }

    public boolean isDropDownAlwaysVisible()
    {
        return mPopup.isDropDownAlwaysVisible();
    }

    public boolean isDropDownDismissedOnCompletion()
    {
        return mDropDownDismissedOnCompletion;
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

    public boolean isPerformingCompletion()
    {
        return mBlockCompletion;
    }

    public boolean isPopupShowing()
    {
        return mPopup.isShowing();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
    }

    public void onCommitCompletion(CompletionInfo completioninfo)
    {
        if(isPopupShowing())
            mPopup.performItemClick(completioninfo.getPosition());
    }

    protected void onDetachedFromWindow()
    {
        dismissDropDown();
        super.onDetachedFromWindow();
    }

    protected void onDisplayHint(int i)
    {
        super.onDisplayHint(i);
        i;
        JVM INSTR tableswitch 4 4: default 24
    //                   4 25;
           goto _L1 _L2
_L1:
        return;
_L2:
        if(!mPopup.isDropDownAlwaysVisible())
            dismissDropDown();
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void onFilterComplete(int i)
    {
        updateDropDownForFilter(i);
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect)
    {
        super.onFocusChanged(flag, i, rect);
        if(isTemporarilyDetached())
            return;
        if(!flag)
            performValidation();
        if(!flag && mPopup.isDropDownAlwaysVisible() ^ true)
            dismissDropDown();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(mPopup.onKeyDown(i, keyevent))
            return true;
        if(isPopupShowing()) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 20 20: default 40
    //                   20 62;
           goto _L2 _L3
_L2:
        if(isPopupShowing() && i == 61 && keyevent.hasNoModifiers())
            return true;
        break; /* Loop/switch isn't completed */
_L3:
        if(keyevent.hasNoModifiers())
            performValidation();
        if(true) goto _L2; else goto _L4
_L4:
        mLastKeyCode = i;
        boolean flag = super.onKeyDown(i, keyevent);
        mLastKeyCode = 0;
        if(flag && isPopupShowing())
            clearListSelection();
        return flag;
    }

    public boolean onKeyPreIme(int i, KeyEvent keyevent)
    {
        if(i == 4 && isPopupShowing() && mPopup.isDropDownAlwaysVisible() ^ true)
        {
            if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
            {
                android.view.KeyEvent.DispatcherState dispatcherstate = getKeyDispatcherState();
                if(dispatcherstate != null)
                    dispatcherstate.startTracking(keyevent, this);
                return true;
            }
            if(keyevent.getAction() == 1)
            {
                android.view.KeyEvent.DispatcherState dispatcherstate1 = getKeyDispatcherState();
                if(dispatcherstate1 != null)
                    dispatcherstate1.handleUpEvent(keyevent);
                if(keyevent.isTracking() && keyevent.isCanceled() ^ true)
                {
                    dismissDropDown();
                    return true;
                }
            }
        }
        return super.onKeyPreIme(i, keyevent);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(!mPopup.onKeyUp(i, keyevent)) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR lookupswitch 3: default 48
    //                   23: 74
    //                   61: 74
    //                   66: 74;
           goto _L2 _L3 _L3 _L3
_L2:
        if(isPopupShowing() && i == 61 && keyevent.hasNoModifiers())
        {
            performCompletion();
            return true;
        } else
        {
            return super.onKeyUp(i, keyevent);
        }
_L3:
        if(keyevent.hasNoModifiers())
            performCompletion();
        return true;
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        if(!flag && mPopup.isDropDownAlwaysVisible() ^ true)
            dismissDropDown();
    }

    public void performCompletion()
    {
        performCompletion(null, -1, -1L);
    }

    protected void performFiltering(CharSequence charsequence, int i)
    {
        mFilter.filter(charsequence, this);
    }

    public void performValidation()
    {
        if(mValidator == null)
            return;
        Editable editable = getText();
        if(!TextUtils.isEmpty(editable) && mValidator.isValid(editable) ^ true)
            setText(mValidator.fixText(editable));
    }

    protected void replaceText(CharSequence charsequence)
    {
        clearComposingText();
        setText(charsequence);
        charsequence = getText();
        Selection.setSelection(charsequence, charsequence.length());
    }

    public void setAdapter(ListAdapter listadapter)
    {
        if(mObserver == null)
            mObserver = new PopupDataSetObserver(this, null);
        else
        if(mAdapter != null)
            mAdapter.unregisterDataSetObserver(mObserver);
        mAdapter = listadapter;
        if(mAdapter != null)
        {
            mFilter = ((Filterable)mAdapter).getFilter();
            listadapter.registerDataSetObserver(mObserver);
        } else
        {
            mFilter = null;
        }
        mPopup.setAdapter(mAdapter);
    }

    public void setCompletionHint(CharSequence charsequence)
    {
        mHintText = charsequence;
        if(charsequence != null)
        {
            if(mHintView == null)
            {
                charsequence = (TextView)LayoutInflater.from(mPopupContext).inflate(mHintResource, null).findViewById(0x1020014);
                charsequence.setText(mHintText);
                mHintView = charsequence;
                mPopup.setPromptView(charsequence);
            } else
            {
                mHintView.setText(charsequence);
            }
        } else
        {
            mPopup.setPromptView(null);
            mHintView = null;
        }
    }

    public void setDropDownAlwaysVisible(boolean flag)
    {
        mPopup.setDropDownAlwaysVisible(flag);
    }

    public void setDropDownAnchor(int i)
    {
        mDropDownAnchorId = i;
        mPopup.setAnchorView(null);
    }

    public void setDropDownAnimationStyle(int i)
    {
        mPopup.setAnimationStyle(i);
    }

    public void setDropDownBackgroundDrawable(Drawable drawable)
    {
        mPopup.setBackgroundDrawable(drawable);
    }

    public void setDropDownBackgroundResource(int i)
    {
        mPopup.setBackgroundDrawable(getContext().getDrawable(i));
    }

    public void setDropDownDismissedOnCompletion(boolean flag)
    {
        mDropDownDismissedOnCompletion = flag;
    }

    public void setDropDownHeight(int i)
    {
        mPopup.setHeight(i);
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
        mPopup.setWidth(i);
    }

    public void setForceIgnoreOutsideTouch(boolean flag)
    {
        mPopup.setForceIgnoreOutsideTouch(flag);
    }

    protected boolean setFrame(int i, int j, int k, int l)
    {
        boolean flag = super.setFrame(i, j, k, l);
        if(isPopupShowing())
            showDropDown();
        return flag;
    }

    public void setListSelection(int i)
    {
        mPopup.setSelection(i);
    }

    public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        PassThroughClickListener._2D_set0(mPassThroughClickListener, onclicklistener);
    }

    public void setOnDismissListener(final OnDismissListener dismissListener)
    {
        PopupWindow.OnDismissListener ondismisslistener = null;
        if(dismissListener != null)
            ondismisslistener = new PopupWindow.OnDismissListener() {

                public void onDismiss()
                {
                    dismissListener.onDismiss();
                }

                final AutoCompleteTextView this$0;
                final OnDismissListener val$dismissListener;

            
            {
                this$0 = AutoCompleteTextView.this;
                dismissListener = ondismisslistener;
                super();
            }
            }
;
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

    public void setText(CharSequence charsequence, boolean flag)
    {
        if(flag)
        {
            setText(charsequence);
        } else
        {
            mBlockCompletion = true;
            setText(charsequence);
            mBlockCompletion = false;
        }
    }

    public void setThreshold(int i)
    {
        int j = i;
        if(i <= 0)
            j = 1;
        mThreshold = j;
    }

    public void setValidator(Validator validator)
    {
        mValidator = validator;
    }

    public void showDropDown()
    {
        buildImeCompletions();
        if(mPopup.getAnchorView() == null)
            if(mDropDownAnchorId != -1)
                mPopup.setAnchorView(getRootView().findViewById(mDropDownAnchorId));
            else
                mPopup.setAnchorView(this);
        if(!isPopupShowing())
        {
            mPopup.setInputMethodMode(1);
            mPopup.setListItemExpandMax(3);
        }
        mPopup.show();
        mPopup.getListView().setOverScrollMode(0);
    }

    public void showDropDownAfterLayout()
    {
        mPopup.postShow();
    }

    static final boolean DEBUG = false;
    static final int EXPAND_MAX = 3;
    static final String TAG = "AutoCompleteTextView";
    private ListAdapter mAdapter;
    private boolean mBlockCompletion;
    private int mDropDownAnchorId;
    private boolean mDropDownDismissedOnCompletion;
    private Filter mFilter;
    private int mHintResource;
    private CharSequence mHintText;
    private TextView mHintView;
    private AdapterView.OnItemClickListener mItemClickListener;
    private AdapterView.OnItemSelectedListener mItemSelectedListener;
    private int mLastKeyCode;
    private PopupDataSetObserver mObserver;
    private boolean mOpenBefore;
    private final PassThroughClickListener mPassThroughClickListener;
    private final ListPopupWindow mPopup;
    private boolean mPopupCanBeUpdated;
    private final Context mPopupContext;
    private int mThreshold;
    private Validator mValidator;

    // Unreferenced inner class android/widget/AutoCompleteTextView$PopupDataSetObserver$1

/* anonymous class */
    class PopupDataSetObserver._cls1
        implements Runnable
    {

        public void run()
        {
            AutoCompleteTextView autocompletetextview = (AutoCompleteTextView)PopupDataSetObserver._2D_get0(PopupDataSetObserver.this).get();
            if(autocompletetextview == null)
                return;
            ListAdapter listadapter = AutoCompleteTextView._2D_get0(autocompletetextview);
            if(listadapter == null)
            {
                return;
            } else
            {
                AutoCompleteTextView._2D_wrap2(autocompletetextview, listadapter.getCount());
                return;
            }
        }

        final PopupDataSetObserver this$1;

            
            {
                this$1 = PopupDataSetObserver.this;
                super();
            }
    }

}
