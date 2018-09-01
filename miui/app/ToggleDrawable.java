// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

public class ToggleDrawable extends LayerDrawable
{

    public ToggleDrawable(Drawable drawable, Drawable drawable1)
    {
        super(getArray(drawable, drawable1));
        setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    private static Drawable[] getArray(Drawable drawable, Drawable drawable1)
    {
        return (new Drawable[] {
            drawable, drawable1
        });
    }
}
