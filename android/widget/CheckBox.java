// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package android.widget:
//            CompoundButton

public class CheckBox extends CompoundButton
{

    public CheckBox(Context context)
    {
        this(context, null);
    }

    public CheckBox(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101006c);
    }

    public CheckBox(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public CheckBox(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/CheckBox.getName();
    }
}
