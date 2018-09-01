// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class UserIcons
{

    public UserIcons()
    {
    }

    public static Bitmap convertToBitmap(Drawable drawable)
    {
        if(drawable == null)
        {
            return null;
        } else
        {
            int i = drawable.getIntrinsicWidth();
            int j = drawable.getIntrinsicHeight();
            Bitmap bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, i, j);
            drawable.draw(canvas);
            return bitmap;
        }
    }

    public static Drawable getDefaultUserIcon(int i, boolean flag)
    {
        int j;
        Drawable drawable;
        if(flag)
            j = 0x1060172;
        else
            j = 0x1060171;
        if(i != -10000)
            j = USER_ICON_COLORS[i % USER_ICON_COLORS.length];
        drawable = Resources.getSystem().getDrawable(0x10802f1, null).mutate();
        drawable.setColorFilter(Resources.getSystem().getColor(j, null), android.graphics.PorterDuff.Mode.SRC_IN);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }

    private static final int USER_ICON_COLORS[] = {
        0x1060169, 0x106016a, 0x106016b, 0x106016c, 0x106016d, 0x106016e, 0x106016f, 0x1060170
    };

}
