// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package android.widget:
//            CompoundButton

public class RadioButton extends CompoundButton
{

    public RadioButton(Context context)
    {
        this(context, null);
    }

    public RadioButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101007e);
    }

    public RadioButton(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public RadioButton(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/RadioButton.getName();
    }

    public void toggle()
    {
        if(!isChecked())
            super.toggle();
    }
}
