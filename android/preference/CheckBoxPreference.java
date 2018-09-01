// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

// Referenced classes of package android.preference:
//            TwoStatePreference

public class CheckBoxPreference extends TwoStatePreference
{

    public CheckBoxPreference(Context context)
    {
        this(context, null);
    }

    public CheckBoxPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101008f);
    }

    public CheckBoxPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public CheckBoxPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.CheckBoxPreference, i, j);
        setSummaryOn(context.getString(0));
        setSummaryOff(context.getString(1));
        setDisableDependentsState(context.getBoolean(2, false));
        context.recycle();
    }

    protected void onBindView(View view)
    {
        super.onBindView(view);
        View view1 = view.findViewById(0x1020001);
        if(view1 != null && (view1 instanceof Checkable))
            ((Checkable)view1).setChecked(mChecked);
        syncSummaryView(view);
    }
}
