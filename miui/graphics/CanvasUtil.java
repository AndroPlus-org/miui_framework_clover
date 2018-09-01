// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.graphics;

import android.graphics.Canvas;

public class CanvasUtil
{

    public CanvasUtil()
    {
    }

    public static void release(Canvas canvas)
    {
        canvas.release();
    }
}
