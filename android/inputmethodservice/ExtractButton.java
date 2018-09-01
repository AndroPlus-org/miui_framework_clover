// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.inputmethodservice;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

class ExtractButton extends Button
{

    public ExtractButton(Context context)
    {
        super(context, null);
    }

    public ExtractButton(Context context, AttributeSet attributeset)
    {
        super(context, attributeset, 0x1010048);
    }

    public ExtractButton(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ExtractButton(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public boolean hasWindowFocus()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(isEnabled())
        {
            flag1 = flag;
            if(getVisibility() == 0)
                flag1 = true;
        }
        return flag1;
    }
}
