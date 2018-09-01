// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;

// Referenced classes of package android.widget:
//            LinearLayout, RadioButton, CompoundButton

public class RadioGroup extends LinearLayout
{
    private class CheckedStateTracker
        implements CompoundButton.OnCheckedChangeListener
    {

        public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            if(RadioGroup._2D_get2(RadioGroup.this))
                return;
            RadioGroup._2D_set0(RadioGroup.this, true);
            if(RadioGroup._2D_get0(RadioGroup.this) != -1)
                RadioGroup._2D_wrap1(RadioGroup.this, RadioGroup._2D_get0(RadioGroup.this), false);
            RadioGroup._2D_set0(RadioGroup.this, false);
            int i = compoundbutton.getId();
            RadioGroup._2D_wrap0(RadioGroup.this, i);
        }

        final RadioGroup this$0;

        private CheckedStateTracker()
        {
            this$0 = RadioGroup.this;
            super();
        }

        CheckedStateTracker(CheckedStateTracker checkedstatetracker)
        {
            this();
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams
    {

        protected void setBaseAttributes(TypedArray typedarray, int i, int j)
        {
            if(typedarray.hasValue(i))
                width = typedarray.getLayoutDimension(i, "layout_width");
            else
                width = -2;
            if(typedarray.hasValue(j))
                height = typedarray.getLayoutDimension(j, "layout_height");
            else
                height = -2;
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
        }

        public LayoutParams(int i, int j, float f)
        {
            super(i, j, f);
        }

        public LayoutParams(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
        {
            super(layoutparams);
        }

        public LayoutParams(android.view.ViewGroup.MarginLayoutParams marginlayoutparams)
        {
            super(marginlayoutparams);
        }
    }

    public static interface OnCheckedChangeListener
    {

        public abstract void onCheckedChanged(RadioGroup radiogroup, int i);
    }

    private class PassThroughHierarchyChangeListener
        implements android.view.ViewGroup.OnHierarchyChangeListener
    {

        static android.view.ViewGroup.OnHierarchyChangeListener _2D_set0(PassThroughHierarchyChangeListener passthroughhierarchychangelistener, android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
        {
            passthroughhierarchychangelistener.mOnHierarchyChangeListener = onhierarchychangelistener;
            return onhierarchychangelistener;
        }

        public void onChildViewAdded(View view, View view1)
        {
            if(view == RadioGroup.this && (view1 instanceof RadioButton))
            {
                if(view1.getId() == -1)
                    view1.setId(View.generateViewId());
                ((RadioButton)view1).setOnCheckedChangeWidgetListener(RadioGroup._2D_get1(RadioGroup.this));
            }
            if(mOnHierarchyChangeListener != null)
                mOnHierarchyChangeListener.onChildViewAdded(view, view1);
        }

        public void onChildViewRemoved(View view, View view1)
        {
            if(view == RadioGroup.this && (view1 instanceof RadioButton))
                ((RadioButton)view1).setOnCheckedChangeWidgetListener(null);
            if(mOnHierarchyChangeListener != null)
                mOnHierarchyChangeListener.onChildViewRemoved(view, view1);
        }

        private android.view.ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
        final RadioGroup this$0;

        private PassThroughHierarchyChangeListener()
        {
            this$0 = RadioGroup.this;
            super();
        }

        PassThroughHierarchyChangeListener(PassThroughHierarchyChangeListener passthroughhierarchychangelistener)
        {
            this();
        }
    }


    static int _2D_get0(RadioGroup radiogroup)
    {
        return radiogroup.mCheckedId;
    }

    static CompoundButton.OnCheckedChangeListener _2D_get1(RadioGroup radiogroup)
    {
        return radiogroup.mChildOnCheckedChangeListener;
    }

    static boolean _2D_get2(RadioGroup radiogroup)
    {
        return radiogroup.mProtectFromCheckedChange;
    }

    static boolean _2D_set0(RadioGroup radiogroup, boolean flag)
    {
        radiogroup.mProtectFromCheckedChange = flag;
        return flag;
    }

    static void _2D_wrap0(RadioGroup radiogroup, int i)
    {
        radiogroup.setCheckedId(i);
    }

    static void _2D_wrap1(RadioGroup radiogroup, int i, boolean flag)
    {
        radiogroup.setCheckedStateForView(i, flag);
    }

    public RadioGroup(Context context)
    {
        super(context);
        mCheckedId = -1;
        mProtectFromCheckedChange = false;
        mInitialCheckedId = -1;
        setOrientation(1);
        init();
    }

    public RadioGroup(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mCheckedId = -1;
        mProtectFromCheckedChange = false;
        mInitialCheckedId = -1;
        if(getImportantForAutofill() == 0)
            setImportantForAutofill(1);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.RadioGroup, 0x101007e, 0);
        int i = context.getResourceId(1, -1);
        if(i != -1)
        {
            mCheckedId = i;
            mInitialCheckedId = i;
        }
        setOrientation(context.getInt(0, 1));
        context.recycle();
        init();
    }

    private void init()
    {
        mChildOnCheckedChangeListener = new CheckedStateTracker(null);
        mPassThroughListener = new PassThroughHierarchyChangeListener(null);
        super.setOnHierarchyChangeListener(mPassThroughListener);
    }

    private void setCheckedId(int i)
    {
        mCheckedId = i;
        if(mOnCheckedChangeListener != null)
            mOnCheckedChangeListener.onCheckedChanged(this, mCheckedId);
        AutofillManager autofillmanager = (AutofillManager)mContext.getSystemService(android/view/autofill/AutofillManager);
        if(autofillmanager != null)
            autofillmanager.notifyValueChanged(this);
    }

    private void setCheckedStateForView(int i, boolean flag)
    {
        View view = findViewById(i);
        if(view != null && (view instanceof RadioButton))
            ((RadioButton)view).setChecked(flag);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if(view instanceof RadioButton)
        {
            RadioButton radiobutton = (RadioButton)view;
            if(radiobutton.isChecked())
            {
                mProtectFromCheckedChange = true;
                if(mCheckedId != -1)
                    setCheckedStateForView(mCheckedId, false);
                mProtectFromCheckedChange = false;
                setCheckedId(radiobutton.getId());
            }
        }
        super.addView(view, i, layoutparams);
    }

    public void autofill(AutofillValue autofillvalue)
    {
        if(!isEnabled())
            return;
        if(!autofillvalue.isList())
        {
            Log.w(LOG_TAG, (new StringBuilder()).append(autofillvalue).append(" could not be autofilled into ").append(this).toString());
            return;
        }
        int i = autofillvalue.getListValue();
        autofillvalue = getChildAt(i);
        if(autofillvalue == null)
        {
            Log.w("View", (new StringBuilder()).append("RadioGroup.autoFill(): no child with index ").append(i).toString());
            return;
        } else
        {
            check(autofillvalue.getId());
            return;
        }
    }

    public void check(int i)
    {
        if(i != -1 && i == mCheckedId)
            return;
        if(mCheckedId != -1)
            setCheckedStateForView(mCheckedId, false);
        if(i != -1)
            setCheckedStateForView(i, true);
        setCheckedId(i);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutparams)
    {
        return layoutparams instanceof LayoutParams;
    }

    public void clearCheck()
    {
        check(-1);
    }

    protected volatile android.view.ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return generateDefaultLayoutParams();
    }

    protected LinearLayout.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(-2, -2);
    }

    public volatile android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    public volatile LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return generateLayoutParams(attributeset);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeset)
    {
        return new LayoutParams(getContext(), attributeset);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/RadioGroup.getName();
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
        if(!isEnabled())
            return null;
        int i = getChildCount();
        for(int j = 0; j < i; j++)
            if(getChildAt(j).getId() == mCheckedId)
                return AutofillValue.forList(j);

        return null;
    }

    public int getCheckedRadioButtonId()
    {
        return mCheckedId;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        if(mCheckedId != -1)
        {
            mProtectFromCheckedChange = true;
            setCheckedStateForView(mCheckedId, true);
            mProtectFromCheckedChange = false;
            setCheckedId(mCheckedId);
        }
    }

    public void onProvideAutofillStructure(ViewStructure viewstructure, int i)
    {
        super.onProvideAutofillStructure(viewstructure, i);
        boolean flag;
        if(mCheckedId != mInitialCheckedId)
            flag = true;
        else
            flag = false;
        viewstructure.setDataIsSensitive(flag);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener oncheckedchangelistener)
    {
        mOnCheckedChangeListener = oncheckedchangelistener;
    }

    public void setOnHierarchyChangeListener(android.view.ViewGroup.OnHierarchyChangeListener onhierarchychangelistener)
    {
        PassThroughHierarchyChangeListener._2D_set0(mPassThroughListener, onhierarchychangelistener);
    }

    private static final String LOG_TAG = android/widget/RadioGroup.getSimpleName();
    private int mCheckedId;
    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    private int mInitialCheckedId;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener mPassThroughListener;
    private boolean mProtectFromCheckedChange;

}
