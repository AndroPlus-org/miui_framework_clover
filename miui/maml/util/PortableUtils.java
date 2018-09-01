// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;

class PortableUtils
{

    PortableUtils()
    {
    }

    public static Drawable getUserBadgedIcon(Context context, Drawable drawable, UserHandle userhandle)
    {
        return context.getPackageManager().getUserBadgedIcon(drawable, userhandle);
    }
}
