// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.SurfaceControl;

public class CompatibilityHelper
{

    public CompatibilityHelper()
    {
    }

    public static Bitmap screenshot(int i, int j)
    {
        return SurfaceControl.screenshot(i, j);
    }

    public static Bitmap screenshot(int i, int j, int k, int l)
    {
        return SurfaceControl.screenshot(new Rect(), i, j, k, l, false, 0);
    }
}
