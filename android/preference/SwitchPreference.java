// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;

// Referenced classes of package android.preference:
//            TwoStatePreference

public class SwitchPreference extends TwoStatePreference
{
    private class Listener
        implements android.widget.CompoundButton.OnCheckedChangeListener
    {

        public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            if(!callChangeListener(Boolean.valueOf(flag)))
            {
                compoundbutton.setChecked(flag ^ true);
                return;
            } else
            {
                setChecked(flag);
                return;
            }
        }

        final SwitchPreference this$0;

        private Listener()
        {
            this$0 = SwitchPreference.this;
            super();
        }

        Listener(Listener listener)
        {
            this();
        }
    }


    public SwitchPreference(Context context)
    {
        this(context, null);
    }

    public SwitchPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101036d);
    }

    public SwitchPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public SwitchPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mListener = new Listener(null);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.SwitchPreference, i, j);
        setSummaryOn(context.getString(0));
        setSummaryOff(context.getString(1));
        setSwitchTextOn(context.getString(3));
        setSwitchTextOff(context.getString(4));
        setDisableDependentsState(context.getBoolean(2, false));
        context.recycle();
    }

    public CharSequence getSwitchTextOff()
    {
        return mSwitchOff;
    }

    public CharSequence getSwitchTextOn()
    {
        return mSwitchOn;
    }

    protected void onBindView(View view)
    {
        super.onBindView(view);
        Object obj = view.findViewById(0x1020040);
        if(obj != null && (obj instanceof Checkable))
        {
            if(obj instanceof Switch)
                ((Switch)obj).setOnCheckedChangeListener(null);
            ((Checkable)obj).setChecked(mChecked);
            if(obj instanceof Switch)
            {
                obj = (Switch)obj;
                ((Switch) (obj)).setTextOn(mSwitchOn);
                ((Switch) (obj)).setTextOff(mSwitchOff);
                ((Switch) (obj)).setOnCheckedChangeListener(mListener);
            }
        }
        syncSummaryView(view);
    }

    public void setSwitchTextOff(int i)
    {
        setSwitchTextOff(((CharSequence) (getContext().getString(i))));
    }

    public void setSwitchTextOff(CharSequence charsequence)
    {
        mSwitchOff = charsequence;
        notifyChanged();
    }

    public void setSwitchTextOn(int i)
    {
        setSwitchTextOn(((CharSequence) (getContext().getString(i))));
    }

    public void setSwitchTextOn(CharSequence charsequence)
    {
        mSwitchOn = charsequence;
        notifyChanged();
    }

    private final Listener mListener;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;
}
