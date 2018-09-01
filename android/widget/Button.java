// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.PointerIcon;

// Referenced classes of package android.widget:
//            TextView

public class Button extends TextView
{

    public Button(Context context)
    {
        this(context, null);
    }

    public Button(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010048);
    }

    public Button(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public Button(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/Button.getName();
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        if(getPointerIcon() == null && isClickable() && isEnabled())
            return PointerIcon.getSystemIcon(getContext(), 1002);
        else
            return super.onResolvePointerIcon(motionevent, i);
    }
}
