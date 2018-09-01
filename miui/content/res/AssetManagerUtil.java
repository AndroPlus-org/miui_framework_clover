// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.content.res;

import android.content.res.AssetManager;

public class AssetManagerUtil
{

    public AssetManagerUtil()
    {
    }

    public static String getCookieName(AssetManager assetmanager, int i)
    {
        return assetmanager.getCookieName(i);
    }
}
