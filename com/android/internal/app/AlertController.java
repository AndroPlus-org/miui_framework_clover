// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;
import java.lang.ref.WeakReference;

// Referenced classes of package com.android.internal.app:
//            MicroAlertController

public class AlertController
{
    public static class AlertParams
    {

        private void createListView(AlertController alertcontroller)
        {
            final RecycleListView listView = (RecycleListView)mInflater.inflate(AlertController._2D_get9(alertcontroller), null);
            Object obj;
            if(mIsMultiChoice)
            {
                if(mCursor == null)
                    obj = mContext. new ArrayAdapter(AlertController._2D_get10(alertcontroller), 0x1020014, mItems, listView) {

                        public View getView(int i, View view, ViewGroup viewgroup)
                        {
                            view = super.getView(i, view, viewgroup);
                            if(mCheckedItems != null && mCheckedItems[i])
                                listView.setItemChecked(i, true);
                            return view;
                        }

                        final AlertParams this$1;
                        final RecycleListView val$listView;

            
            {
                this$1 = final_alertparams;
                listView = recyclelistview;
                super(Context.this, i, j, acharsequence);
            }
                    }
;
                else
                    obj = mCursor. new CursorAdapter(false, listView, alertcontroller) {

                        public void bindView(View view, Context context, Cursor cursor)
                        {
                            boolean flag = true;
                            ((CheckedTextView)view.findViewById(0x1020014)).setText(cursor.getString(mLabelIndex));
                            view = listView;
                            int i = cursor.getPosition();
                            if(cursor.getInt(mIsCheckedIndex) != 1)
                                flag = false;
                            view.setItemChecked(i, flag);
                        }

                        public View newView(Context context, Cursor cursor, ViewGroup viewgroup)
                        {
                            return mInflater.inflate(AlertController._2D_get10(dialog), viewgroup, false);
                        }

                        private final int mIsCheckedIndex;
                        private final int mLabelIndex;
                        final AlertParams this$1;
                        final AlertController val$dialog;
                        final RecycleListView val$listView;

            
            {
                this$1 = final_alertparams;
                listView = recyclelistview;
                dialog = alertcontroller;
                super(final_context, Cursor.this, flag);
                final_alertparams = getCursor();
                mLabelIndex = final_alertparams.getColumnIndexOrThrow(mLabelColumn);
                mIsCheckedIndex = final_alertparams.getColumnIndexOrThrow(mIsCheckedColumn);
            }
                    }
;
            } else
            {
                int i;
                if(mIsSingleChoice)
                    i = AlertController._2D_get11(alertcontroller);
                else
                    i = AlertController._2D_get8(alertcontroller);
                if(mCursor != null)
                    obj = new SimpleCursorAdapter(mContext, i, mCursor, new String[] {
                        mLabelColumn
                    }, new int[] {
                        0x1020014
                    });
                else
                if(mAdapter != null)
                    obj = mAdapter;
                else
                    obj = new CheckedItemAdapter(mContext, i, 0x1020014, mItems);
            }
            if(mOnPrepareListViewListener != null)
                mOnPrepareListViewListener.onPrepareListView(listView);
            AlertController._2D_set0(alertcontroller, ((ListAdapter) (obj)));
            AlertController._2D_set1(alertcontroller, mCheckedItem);
            if(mOnClickListener != null)
                listView.setOnItemClickListener(alertcontroller. new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int i, long l)
                    {
                        mOnClickListener.onClick(AlertController._2D_get6(dialog), i);
                        if(!mIsSingleChoice)
                            AlertController._2D_get6(dialog).dismiss();
                    }

                    final AlertParams this$1;
                    final AlertController val$dialog;

            
            {
                this$1 = final_alertparams;
                dialog = AlertController.this;
                super();
            }
                }
);
            else
            if(mOnCheckboxClickListener != null)
                listView.setOnItemClickListener(alertcontroller. new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int i, long l)
                    {
                        if(mCheckedItems != null)
                            mCheckedItems[i] = listView.isItemChecked(i);
                        mOnCheckboxClickListener.onClick(AlertController._2D_get6(dialog), i, listView.isItemChecked(i));
                    }

                    final AlertParams this$1;
                    final AlertController val$dialog;
                    final RecycleListView val$listView;

            
            {
                this$1 = final_alertparams;
                listView = recyclelistview;
                dialog = AlertController.this;
                super();
            }
                }
);
            if(mOnItemSelectedListener != null)
                listView.setOnItemSelectedListener(mOnItemSelectedListener);
            if(mIsSingleChoice)
                listView.setChoiceMode(1);
            else
            if(mIsMultiChoice)
                listView.setChoiceMode(2);
            listView.mRecycleOnMeasure = mRecycleOnMeasure;
            alertcontroller.mListView = listView;
        }

        public void apply(AlertController alertcontroller)
        {
            if(mCustomTitleView != null)
            {
                alertcontroller.setCustomTitle(mCustomTitleView);
            } else
            {
                if(mTitle != null)
                    alertcontroller.setTitle(mTitle);
                if(mIcon != null)
                    alertcontroller.setIcon(mIcon);
                if(mIconId != 0)
                    alertcontroller.setIcon(mIconId);
                if(mIconAttrId != 0)
                    alertcontroller.setIcon(alertcontroller.getIconAttributeResId(mIconAttrId));
            }
            if(mMessage != null)
                alertcontroller.setMessage(mMessage);
            if(mPositiveButtonText != null)
                alertcontroller.setButton(-1, mPositiveButtonText, mPositiveButtonListener, null);
            if(mNegativeButtonText != null)
                alertcontroller.setButton(-2, mNegativeButtonText, mNegativeButtonListener, null);
            if(mNeutralButtonText != null)
                alertcontroller.setButton(-3, mNeutralButtonText, mNeutralButtonListener, null);
            if(mForceInverseBackground)
                alertcontroller.setInverseBackgroundForced(true);
            break MISSING_BLOCK_LABEL_107;
            if(mItems != null || mCursor != null || mAdapter != null)
                createListView(alertcontroller);
            if(mView != null)
            {
                if(mViewSpacingSpecified)
                    alertcontroller.setView(mView, mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight, mViewSpacingBottom);
                else
                    alertcontroller.setView(mView);
            } else
            if(mViewLayoutResId != 0)
                alertcontroller.setView(mViewLayoutResId);
            return;
        }

        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem;
        public boolean mCheckedItems[];
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconAttrId;
        public int mIconId;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence mItems[];
        public String mLabelColumn;
        public CharSequence mMessage;
        public android.content.DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public android.content.DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public android.content.DialogInterface.OnCancelListener mOnCancelListener;
        public android.content.DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        public android.content.DialogInterface.OnClickListener mOnClickListener;
        public android.content.DialogInterface.OnDismissListener mOnDismissListener;
        public android.widget.AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        public android.content.DialogInterface.OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareListViewListener;
        public android.content.DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified;
        public int mViewSpacingTop;

        public AlertParams(Context context)
        {
            mIconId = 0;
            mIconAttrId = 0;
            mViewSpacingSpecified = false;
            mCheckedItem = -1;
            mRecycleOnMeasure = true;
            mContext = context;
            mCancelable = true;
            mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        }
    }

    public static interface AlertParams.OnPrepareListViewListener
    {

        public abstract void onPrepareListView(ListView listview);
    }

    private static final class ButtonHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch -3 1: default 40
        //                       -3 41
        //                       -2 41
        //                       -1 41
        //                       0 40
        //                       1 70;
               goto _L1 _L2 _L2 _L2 _L1 _L3
_L1:
            return;
_L2:
            ((android.content.DialogInterface.OnClickListener)message.obj).onClick((DialogInterface)mDialog.get(), message.what);
            continue; /* Loop/switch isn't completed */
_L3:
            ((DialogInterface)message.obj).dismiss();
            if(true) goto _L1; else goto _L4
_L4:
        }

        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference mDialog;

        public ButtonHandler(DialogInterface dialoginterface)
        {
            mDialog = new WeakReference(dialoginterface);
        }
    }

    private static class CheckedItemAdapter extends ArrayAdapter
    {

        public long getItemId(int i)
        {
            return (long)i;
        }

        public boolean hasStableIds()
        {
            return true;
        }

        public CheckedItemAdapter(Context context, int i, int j, CharSequence acharsequence[])
        {
            super(context, i, j, acharsequence);
        }
    }

    public static class RecycleListView extends ListView
    {

        protected boolean recycleOnMeasure()
        {
            return mRecycleOnMeasure;
        }

        public void setHasDecor(boolean flag, boolean flag1)
        {
            if(!flag1 || flag ^ true)
            {
                int i = getPaddingLeft();
                int j;
                int k;
                int l;
                if(flag)
                    j = getPaddingTop();
                else
                    j = mPaddingTopNoTitle;
                k = getPaddingRight();
                if(flag1)
                    l = getPaddingBottom();
                else
                    l = mPaddingBottomNoButtons;
                setPadding(i, j, k, l);
            }
        }

        private final int mPaddingBottomNoButtons;
        private final int mPaddingTopNoTitle;
        boolean mRecycleOnMeasure;

        public RecycleListView(Context context)
        {
            this(context, null);
        }

        public RecycleListView(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mRecycleOnMeasure = true;
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.RecycleListView);
            mPaddingBottomNoButtons = context.getDimensionPixelOffset(0, -1);
            mPaddingTopNoTitle = context.getDimensionPixelOffset(1, -1);
        }
    }


    static Button _2D_get0(AlertController alertcontroller)
    {
        return alertcontroller.mButtonNegative;
    }

    static Message _2D_get1(AlertController alertcontroller)
    {
        return alertcontroller.mButtonNegativeMessage;
    }

    static int _2D_get10(AlertController alertcontroller)
    {
        return alertcontroller.mMultiChoiceItemLayout;
    }

    static int _2D_get11(AlertController alertcontroller)
    {
        return alertcontroller.mSingleChoiceItemLayout;
    }

    static Button _2D_get2(AlertController alertcontroller)
    {
        return alertcontroller.mButtonNeutral;
    }

    static Message _2D_get3(AlertController alertcontroller)
    {
        return alertcontroller.mButtonNeutralMessage;
    }

    static Button _2D_get4(AlertController alertcontroller)
    {
        return alertcontroller.mButtonPositive;
    }

    static Message _2D_get5(AlertController alertcontroller)
    {
        return alertcontroller.mButtonPositiveMessage;
    }

    static DialogInterface _2D_get6(AlertController alertcontroller)
    {
        return alertcontroller.mDialogInterface;
    }

    static Handler _2D_get7(AlertController alertcontroller)
    {
        return alertcontroller.mHandler;
    }

    static int _2D_get8(AlertController alertcontroller)
    {
        return alertcontroller.mListItemLayout;
    }

    static int _2D_get9(AlertController alertcontroller)
    {
        return alertcontroller.mListLayout;
    }

    static ListAdapter _2D_set0(AlertController alertcontroller, ListAdapter listadapter)
    {
        alertcontroller.mAdapter = listadapter;
        return listadapter;
    }

    static int _2D_set1(AlertController alertcontroller, int i)
    {
        alertcontroller.mCheckedItem = i;
        return i;
    }

    protected AlertController(Context context, DialogInterface dialoginterface, Window window)
    {
        mViewSpacingSpecified = false;
        mIconId = 0;
        mCheckedItem = -1;
        mButtonPanelLayoutHint = 0;
        mContext = context;
        mDialogInterface = dialoginterface;
        mWindow = window;
        mHandler = new ButtonHandler(dialoginterface);
        context = context.obtainStyledAttributes(null, com.android.internal.R.styleable.AlertDialog, 0x101005d, 0);
        mAlertDialogLayout = context.getResourceId(10, 0x1090028);
        mButtonPanelSideLayout = context.getResourceId(11, 0);
        mListLayout = context.getResourceId(15, 0x10900ed);
        mMultiChoiceItemLayout = context.getResourceId(16, 0x1090013);
        mSingleChoiceItemLayout = context.getResourceId(21, 0x1090012);
        mListItemLayout = context.getResourceId(14, 0x1090011);
        mShowTitle = context.getBoolean(20, true);
        context.recycle();
        window.requestFeature(1);
    }

    static boolean canTextInput(View view)
    {
        if(view.onCheckIsTextEditor())
            return true;
        if(!(view instanceof ViewGroup))
            return false;
        view = (ViewGroup)view;
        for(int i = view.getChildCount(); i > 0;)
        {
            int j = i - 1;
            i = j;
            if(canTextInput(view.getChildAt(j)))
                return true;
        }

        return false;
    }

    private void centerButton(Button button)
    {
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)button.getLayoutParams();
        layoutparams.gravity = 1;
        layoutparams.weight = 0.5F;
        button.setLayoutParams(layoutparams);
        button = mWindow.findViewById(0x10202d8);
        if(button != null)
            button.setVisibility(0);
        button = mWindow.findViewById(0x10203af);
        if(button != null)
            button.setVisibility(0);
    }

    public static final AlertController create(Context context, DialogInterface dialoginterface, Window window)
    {
        TypedArray typedarray = context.obtainStyledAttributes(null, com.android.internal.R.styleable.AlertDialog, 0x101005d, 0);
        int i = typedarray.getInt(12, 0);
        typedarray.recycle();
        switch(i)
        {
        default:
            return new AlertController(context, dialoginterface, window);

        case 1: // '\001'
            return new MicroAlertController(context, dialoginterface, window);
        }
    }

    private static void manageScrollIndicators(View view, View view1, View view2)
    {
        boolean flag = false;
        int i;
        if(view1 != null)
        {
            if(view.canScrollVertically(-1))
                i = 0;
            else
                i = 4;
            view1.setVisibility(i);
        }
        if(view2 != null)
        {
            if(view.canScrollVertically(1))
                i = ((flag) ? 1 : 0);
            else
                i = 4;
            view2.setVisibility(i);
        }
    }

    private ViewGroup resolvePanel(View view, View view1)
    {
        if(view == null)
        {
            view = view1;
            if(view1 instanceof ViewStub)
                view = ((ViewStub)view1).inflate();
            return (ViewGroup)view;
        }
        if(view1 != null)
        {
            android.view.ViewParent viewparent = view1.getParent();
            if(viewparent instanceof ViewGroup)
                ((ViewGroup)viewparent).removeView(view1);
        }
        view1 = view;
        if(view instanceof ViewStub)
            view1 = ((ViewStub)view).inflate();
        return (ViewGroup)view1;
    }

    private int selectContentView()
    {
        if(mButtonPanelSideLayout == 0)
            return mAlertDialogLayout;
        if(mButtonPanelLayoutHint == 1)
            return mButtonPanelSideLayout;
        else
            return mAlertDialogLayout;
    }

    private void setBackground(TypedArray typedarray, View view, View view1, View view2, View view3, boolean flag, boolean flag1, 
            boolean flag2)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        int i2 = 0;
        int i3 = 0;
        int j3 = 0;
        if(typedarray.getBoolean(17, true))
        {
            i = 0x1080694;
            j = 0x10806a2;
            k = 0x1080691;
            j1 = 0x108068e;
            k1 = 0x1080693;
            l1 = 0x10806a1;
            i2 = 0x1080690;
            i3 = 0x108068d;
            j3 = 0x108068f;
        }
        l1 = typedarray.getResourceId(5, l1);
        int k3 = typedarray.getResourceId(1, j);
        int l3 = typedarray.getResourceId(6, i2);
        int i4 = typedarray.getResourceId(2, k);
        View aview[] = new View[4];
        boolean aflag[] = new boolean[4];
        Object obj = null;
        boolean flag3 = false;
        k = 0;
        if(flag)
        {
            aview[0] = view;
            aflag[0] = false;
            k = 1;
        }
        view = view1;
        if(view1.getVisibility() == 8)
            view = null;
        aview[k] = view;
        if(mListView != null)
            flag = true;
        else
            flag = false;
        aflag[k] = flag;
        i2 = k + 1;
        k = i2;
        if(flag1)
        {
            aview[i2] = view2;
            aflag[i2] = mForceInverseBackground;
            k = i2 + 1;
        }
        if(flag2)
        {
            aview[k] = view3;
            aflag[k] = true;
        }
        k = 0;
        j = 0;
        view = obj;
        flag = flag3;
        while(j < aview.length) 
        {
            view1 = aview[j];
            if(view1 != null)
            {
                int j2 = k;
                if(view != null)
                {
                    if(k == 0)
                    {
                        if(flag)
                            k = l1;
                        else
                            k = k3;
                        view.setBackgroundResource(k);
                    } else
                    {
                        if(flag)
                            k = l3;
                        else
                            k = i4;
                        view.setBackgroundResource(k);
                    }
                    j2 = 1;
                }
                view = view1;
                flag = aflag[j];
                k = j2;
            }
            j++;
        }
        if(view != null)
            if(k != 0)
            {
                int l = typedarray.getResourceId(7, i3);
                int k2 = typedarray.getResourceId(8, j3);
                j1 = typedarray.getResourceId(3, j1);
                if(flag)
                {
                    if(flag2)
                        l = k2;
                } else
                {
                    l = j1;
                }
                view.setBackgroundResource(l);
            } else
            {
                int i1 = typedarray.getResourceId(4, k1);
                int l2 = typedarray.getResourceId(0, i);
                if(!flag)
                    i1 = l2;
                view.setBackgroundResource(i1);
            }
        view = mListView;
        if(view != null && mAdapter != null)
        {
            view.setAdapter(mAdapter);
            l = mCheckedItem;
            if(l > -1)
            {
                view.setItemChecked(l, true);
                view.setSelectionFromTop(l, typedarray.getDimensionPixelSize(19, 0));
            }
        }
    }

    private void setupCustomContent(ViewGroup viewgroup)
    {
        View view;
        boolean flag;
        if(mView != null)
            view = mView;
        else
        if(mViewLayoutResId != 0)
            view = LayoutInflater.from(mContext).inflate(mViewLayoutResId, viewgroup, false);
        else
            view = null;
        if(view != null)
            flag = true;
        else
            flag = false;
        if(!flag || canTextInput(view) ^ true)
            mWindow.setFlags(0x20000, 0x20000);
        if(flag)
        {
            FrameLayout framelayout = (FrameLayout)mWindow.findViewById(0x102002b);
            framelayout.addView(view, new android.view.ViewGroup.LayoutParams(-1, -1));
            if(mViewSpacingSpecified)
                framelayout.setPadding(mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight, mViewSpacingBottom);
            if(mListView != null)
                ((android.widget.LinearLayout.LayoutParams)viewgroup.getLayoutParams()).weight = 0.0F;
        } else
        {
            viewgroup.setVisibility(8);
        }
    }

    private void setupView()
    {
        if(obj2 != null)
            ((View) (obj2)).setVisibility(0);
_L1:
        if(mListView instanceof RecycleListView)
            ((RecycleListView)mListView).setHasDecor(flag1, flag2);
        if(!flag)
        {
            if(mListView != null)
                obj2 = mListView;
            else
                obj2 = mScrollView;
            if(obj2 != null)
            {
                boolean flag3;
                byte byte0;
                if(flag1)
                    flag3 = true;
                else
                    flag3 = false;
                if(flag2)
                    byte0 = 2;
                else
                    byte0 = 0;
                ((View) (obj2)).setScrollIndicators(flag3 | byte0, 3);
            }
        }
        obj2 = mContext.obtainStyledAttributes(null, com.android.internal.R.styleable.AlertDialog, 0x101005d, 0);
        setBackground(((TypedArray) (obj2)), ((View) (obj1)), ((View) (obj3)), ((View) (obj)), ((View) (obj4)), flag1, flag, flag2);
        ((TypedArray) (obj2)).recycle();
        return;
        Object obj3;
label0:
        {
            Object obj = mWindow.findViewById(0x1020363);
            Object obj1 = ((View) (obj)).findViewById(0x1020464);
            View view = ((View) (obj)).findViewById(0x1020206);
            obj2 = ((View) (obj)).findViewById(0x10201da);
            obj = (ViewGroup)((View) (obj)).findViewById(0x102020e);
            setupCustomContent(((ViewGroup) (obj)));
            View view1 = ((ViewGroup) (obj)).findViewById(0x1020464);
            obj3 = ((ViewGroup) (obj)).findViewById(0x1020206);
            Object obj4 = ((ViewGroup) (obj)).findViewById(0x10201da);
            obj1 = resolvePanel(view1, ((View) (obj1)));
            obj3 = resolvePanel(((View) (obj3)), view);
            obj4 = resolvePanel(((View) (obj4)), ((View) (obj2)));
            setupContent(((ViewGroup) (obj3)));
            setupButtons(((ViewGroup) (obj4)));
            setupTitle(((ViewGroup) (obj1)));
            boolean flag;
            boolean flag1;
            boolean flag2;
            if(obj != null)
            {
                if(((ViewGroup) (obj)).getVisibility() != 8)
                    flag = true;
                else
                    flag = false;
            } else
            {
                flag = false;
            }
            if(obj1 != null)
            {
                if(((ViewGroup) (obj1)).getVisibility() != 8)
                    flag1 = true;
                else
                    flag1 = false;
            } else
            {
                flag1 = false;
            }
            if(obj4 != null)
            {
                if(((ViewGroup) (obj4)).getVisibility() != 8)
                    flag2 = true;
                else
                    flag2 = false;
            } else
            {
                flag2 = false;
            }
            if(!flag2)
            {
                if(obj3 != null)
                {
                    obj2 = ((ViewGroup) (obj3)).findViewById(0x1020443);
                    if(obj2 != null)
                        ((View) (obj2)).setVisibility(0);
                }
                mWindow.setCloseOnTouchOutsideIfNotSet(true);
            }
            if(!flag1)
                break label0;
            if(mScrollView != null)
                mScrollView.setClipToPadding(true);
            view = null;
            if(mMessage != null || mListView != null || flag)
            {
                if(!flag)
                    view = ((ViewGroup) (obj1)).findViewById(0x1020455);
                obj2 = view;
                if(view == null)
                    obj2 = ((ViewGroup) (obj1)).findViewById(0x1020454);
            } else
            {
                obj2 = ((ViewGroup) (obj1)).findViewById(0x1020456);
            }
            break MISSING_BLOCK_LABEL_277;
        }
        if(obj3 != null)
        {
            obj2 = ((ViewGroup) (obj3)).findViewById(0x1020444);
            if(obj2 != null)
                ((View) (obj2)).setVisibility(0);
        }
          goto _L1
    }

    private static boolean shouldCenterSingleButton(Context context)
    {
        boolean flag = true;
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x111000a, typedvalue, true);
        if(typedvalue.data == 0)
            flag = false;
        return flag;
    }

    public Button getButton(int i)
    {
        switch(i)
        {
        default:
            return null;

        case -1: 
            return mButtonPositive;

        case -2: 
            return mButtonNegative;

        case -3: 
            return mButtonNeutral;
        }
    }

    public int getIconAttributeResId(int i)
    {
        TypedValue typedvalue = new TypedValue();
        mContext.getTheme().resolveAttribute(i, typedvalue, true);
        return typedvalue.resourceId;
    }

    public ListView getListView()
    {
        return mListView;
    }

    public void installContent()
    {
        int i = selectContentView();
        mWindow.setContentView(i);
        setupView();
    }

    public void installContent(AlertParams alertparams)
    {
        alertparams.apply(this);
        installContent();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(mScrollView != null)
            flag = mScrollView.executeKeyEvent(keyevent);
        else
            flag = false;
        return flag;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(mScrollView != null)
            flag = mScrollView.executeKeyEvent(keyevent);
        else
            flag = false;
        return flag;
    }

    public void setButton(int i, CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener, Message message)
    {
        Message message1;
        message1 = message;
        if(message == null)
        {
            message1 = message;
            if(onclicklistener != null)
                message1 = mHandler.obtainMessage(i, onclicklistener);
        }
        i;
        JVM INSTR tableswitch -3 -1: default 56
    //                   -3 93
    //                   -2 79
    //                   -1 67;
           goto _L1 _L2 _L3 _L4
_L1:
        throw new IllegalArgumentException("Button does not exist");
_L4:
        mButtonPositiveText = charsequence;
        mButtonPositiveMessage = message1;
_L6:
        return;
_L3:
        mButtonNegativeText = charsequence;
        mButtonNegativeMessage = message1;
        continue; /* Loop/switch isn't completed */
_L2:
        mButtonNeutralText = charsequence;
        mButtonNeutralMessage = message1;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void setButtonPanelLayoutHint(int i)
    {
        mButtonPanelLayoutHint = i;
    }

    public void setCustomTitle(View view)
    {
        mCustomTitleView = view;
    }

    public void setIcon(int i)
    {
        mIcon = null;
        mIconId = i;
        if(mIconView != null)
            if(i != 0)
            {
                mIconView.setVisibility(0);
                mIconView.setImageResource(mIconId);
            } else
            {
                mIconView.setVisibility(8);
            }
    }

    public void setIcon(Drawable drawable)
    {
        mIcon = drawable;
        mIconId = 0;
        if(mIconView != null)
            if(drawable != null)
            {
                mIconView.setVisibility(0);
                mIconView.setImageDrawable(drawable);
            } else
            {
                mIconView.setVisibility(8);
            }
    }

    public void setInverseBackgroundForced(boolean flag)
    {
        mForceInverseBackground = flag;
    }

    public void setMessage(CharSequence charsequence)
    {
        mMessage = charsequence;
        if(mMessageView != null)
            mMessageView.setText(charsequence);
    }

    public void setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        if(mTitleView != null)
            mTitleView.setText(charsequence);
    }

    public void setView(int i)
    {
        mView = null;
        mViewLayoutResId = i;
        mViewSpacingSpecified = false;
    }

    public void setView(View view)
    {
        mView = view;
        mViewLayoutResId = 0;
        mViewSpacingSpecified = false;
    }

    public void setView(View view, int i, int j, int k, int l)
    {
        mView = view;
        mViewLayoutResId = 0;
        mViewSpacingSpecified = true;
        mViewSpacingLeft = i;
        mViewSpacingTop = j;
        mViewSpacingRight = k;
        mViewSpacingBottom = l;
    }

    protected void setupButtons(ViewGroup viewgroup)
    {
        int i = 0;
        mButtonPositive = (Button)viewgroup.findViewById(0x1020019);
        mButtonPositive.setOnClickListener(mButtonHandler);
        if(TextUtils.isEmpty(mButtonPositiveText))
        {
            mButtonPositive.setVisibility(8);
        } else
        {
            mButtonPositive.setText(mButtonPositiveText);
            mButtonPositive.setVisibility(0);
            i = 1;
        }
        mButtonNegative = (Button)viewgroup.findViewById(0x102001a);
        mButtonNegative.setOnClickListener(mButtonHandler);
        if(TextUtils.isEmpty(mButtonNegativeText))
        {
            mButtonNegative.setVisibility(8);
        } else
        {
            mButtonNegative.setText(mButtonNegativeText);
            mButtonNegative.setVisibility(0);
            i |= 2;
        }
        mButtonNeutral = (Button)viewgroup.findViewById(0x102001b);
        mButtonNeutral.setOnClickListener(mButtonHandler);
        if(TextUtils.isEmpty(mButtonNeutralText))
        {
            mButtonNeutral.setVisibility(8);
        } else
        {
            mButtonNeutral.setText(mButtonNeutralText);
            mButtonNeutral.setVisibility(0);
            i |= 4;
        }
        if(shouldCenterSingleButton(mContext))
            if(i == 1)
                centerButton(mButtonPositive);
            else
            if(i == 2)
                centerButton(mButtonNegative);
            else
            if(i == 4)
                centerButton(mButtonNeutral);
        if(i != 0)
            i = 1;
        else
            i = 0;
        if(i == 0)
            viewgroup.setVisibility(8);
    }

    protected void setupContent(ViewGroup viewgroup)
    {
        mScrollView = (ScrollView)viewgroup.findViewById(0x10203c2);
        mScrollView.setFocusable(false);
        mMessageView = (TextView)viewgroup.findViewById(0x102000b);
        if(mMessageView == null)
            return;
        if(mMessage != null)
        {
            mMessageView.setText(mMessage);
        } else
        {
            mMessageView.setVisibility(8);
            mScrollView.removeView(mMessageView);
            if(mListView != null)
            {
                viewgroup = (ViewGroup)mScrollView.getParent();
                int i = viewgroup.indexOfChild(mScrollView);
                viewgroup.removeViewAt(i);
                viewgroup.addView(mListView, i, new android.view.ViewGroup.LayoutParams(-1, -1));
            } else
            {
                viewgroup.setVisibility(8);
            }
        }
    }

    protected void setupTitle(ViewGroup viewgroup)
    {
        if(mCustomTitleView != null && mShowTitle)
        {
            android.view.ViewGroup.LayoutParams layoutparams = new android.view.ViewGroup.LayoutParams(-1, -2);
            viewgroup.addView(mCustomTitleView, 0, layoutparams);
            mWindow.findViewById(0x102045a).setVisibility(8);
        } else
        {
            mIconView = (ImageView)mWindow.findViewById(0x1020006);
            if(TextUtils.isEmpty(mTitle) ^ true && mShowTitle)
            {
                mTitleView = (TextView)mWindow.findViewById(0x1020199);
                mTitleView.setText(mTitle);
                if(mIconId != 0)
                    mIconView.setImageResource(mIconId);
                else
                if(mIcon != null)
                {
                    mIconView.setImageDrawable(mIcon);
                } else
                {
                    mTitleView.setPadding(mIconView.getPaddingLeft(), mIconView.getPaddingTop(), mIconView.getPaddingRight(), mIconView.getPaddingBottom());
                    mIconView.setVisibility(8);
                }
            } else
            {
                mWindow.findViewById(0x102045a).setVisibility(8);
                mIconView.setVisibility(8);
                viewgroup.setVisibility(8);
            }
        }
    }

    public static final int MICRO = 1;
    private ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final android.view.View.OnClickListener mButtonHandler = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            if(view == AlertController._2D_get4(AlertController.this) && AlertController._2D_get5(AlertController.this) != null)
                view = Message.obtain(AlertController._2D_get5(AlertController.this));
            else
            if(view == AlertController._2D_get0(AlertController.this) && AlertController._2D_get1(AlertController.this) != null)
                view = Message.obtain(AlertController._2D_get1(AlertController.this));
            else
            if(view == AlertController._2D_get2(AlertController.this) && AlertController._2D_get3(AlertController.this) != null)
                view = Message.obtain(AlertController._2D_get3(AlertController.this));
            else
                view = null;
            if(view != null)
                view.sendToTarget();
            AlertController._2D_get7(AlertController.this).obtainMessage(1, AlertController._2D_get6(AlertController.this)).sendToTarget();
        }

        final AlertController this$0;

            
            {
                this$0 = AlertController.this;
                super();
            }
    }
;
    private Button mButtonNegative;
    private Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    private Button mButtonNeutral;
    private Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint;
    private int mButtonPanelSideLayout;
    private Button mButtonPositive;
    private Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    private int mCheckedItem;
    private final Context mContext;
    private View mCustomTitleView;
    private final DialogInterface mDialogInterface;
    private boolean mForceInverseBackground;
    private Handler mHandler;
    private Drawable mIcon;
    private int mIconId;
    private ImageView mIconView;
    private int mListItemLayout;
    private int mListLayout;
    protected ListView mListView;
    protected CharSequence mMessage;
    protected TextView mMessageView;
    private int mMultiChoiceItemLayout;
    protected ScrollView mScrollView;
    private boolean mShowTitle;
    private int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified;
    private int mViewSpacingTop;
    protected final Window mWindow;
}
