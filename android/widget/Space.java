// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public final class Space extends View
{

    public Space(Context context)
    {
        this(context, null);
    }

    public Space(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public Space(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public Space(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        if(getVisibility() == 0)
            setVisibility(4);
    }

    private static int getDefaultSize2(int i, int j)
    {
        int k;
        int l;
        k = i;
        l = android.view.View.MeasureSpec.getMode(j);
        j = android.view.View.MeasureSpec.getSize(j);
        l;
        JVM INSTR lookupswitch 3: default 48
    //                   -2147483648: 55
    //                   0: 52
    //                   1073741824: 64;
           goto _L1 _L2 _L3 _L4
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        i = k;
_L6:
        return i;
_L2:
        i = Math.min(i, j);
        continue; /* Loop/switch isn't completed */
_L4:
        i = j;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void draw(Canvas canvas)
    {
    }

    protected void onMeasure(int i, int j)
    {
        setMeasuredDimension(getDefaultSize2(getSuggestedMinimumWidth(), i), getDefaultSize2(getSuggestedMinimumHeight(), j));
    }
}
