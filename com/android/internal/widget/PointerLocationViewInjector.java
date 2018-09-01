// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.graphics.*;
import android.os.SystemProperties;
import android.util.Log;

public class PointerLocationViewInjector
{

    public PointerLocationViewInjector()
    {
    }

    public static void drawOval(Canvas canvas, float f, float f1, float f2, float f3, float f4, Paint paint)
    {
        float f5;
        float f6;
        paint = new Paint(paint);
        f5 = f2;
        f6 = f3;
        if(!isCustomTouchStyleEnabled()) goto _L2; else goto _L1
_L1:
        int i;
        String s;
        paint.setStyle(android.graphics.Paint.Style.FILL);
        i = SystemProperties.getInt("debug.customtouchstyle.paintcolor", -1);
        if(i != -1)
            paint.setColor(i);
        s = SystemProperties.get("debug.customtouchstyle.ovalsize");
        f5 = f2;
        f6 = f3;
        if(s == null)
            break MISSING_BLOCK_LABEL_100;
        f5 = f2;
        f6 = f3;
        if(!(s.isEmpty() ^ true))
            break MISSING_BLOCK_LABEL_100;
        f6 = Float.parseFloat(s);
        f5 = f6;
_L4:
        Log.i(TAG, (new StringBuilder()).append("customColor=").append(i).append(" customSize=").append(s).toString());
_L2:
        canvas.save(1);
        canvas.rotate((float)((double)(180F * f4) / 3.1415926535897931D), f, f1);
        RectF rectf = new RectF();
        rectf.left = f - f6 / 2.0F;
        rectf.right = f6 / 2.0F + f;
        rectf.top = f1 - f5 / 2.0F;
        rectf.bottom = f5 / 2.0F + f1;
        canvas.drawOval(rectf, paint);
        canvas.restore();
        return;
        NumberFormatException numberformatexception;
        numberformatexception;
        Log.e(TAG, numberformatexception.getMessage());
        f5 = f2;
        f6 = f3;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isCustomTouchStyleEnabled()
    {
        return SystemProperties.getBoolean("debug.customtouchstyle.enabled", false);
    }

    private static final String CUSTOM_TOUCH_STYLE_ENABLED = "debug.customtouchstyle.enabled";
    private static final String CUSTOM_TOUCH_STYLE_OVAL_SIZE = "debug.customtouchstyle.ovalsize";
    private static final String CUSTOM_TOUCH_STYLE_PAINT_COLOR = "debug.customtouchstyle.paintcolor";
    private static final String TAG = com/android/internal/widget/PointerLocationViewInjector.getSimpleName();

}
