// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.browse;

import android.os.Bundle;

public class MediaBrowserUtils
{

    public MediaBrowserUtils()
    {
    }

    public static boolean areSameOptions(Bundle bundle, Bundle bundle1)
    {
        boolean flag = true;
        boolean flag1 = false;
        boolean flag2 = false;
        if(bundle == bundle1)
            return true;
        if(bundle == null)
        {
            if(bundle1.getInt("android.media.browse.extra.PAGE", -1) == -1)
            {
                if(bundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1) != -1)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        }
        if(bundle1 == null)
        {
            flag = flag2;
            if(bundle.getInt("android.media.browse.extra.PAGE", -1) == -1)
            {
                flag = flag2;
                if(bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1)
                    flag = true;
            }
            return flag;
        }
        flag = flag1;
        if(bundle.getInt("android.media.browse.extra.PAGE", -1) == bundle1.getInt("android.media.browse.extra.PAGE", -1))
        {
            flag = flag1;
            if(bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) == bundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1))
                flag = true;
        }
        return flag;
    }

    public static boolean hasDuplicatedItems(Bundle bundle, Bundle bundle1)
    {
        int i;
        int j;
        int k;
        int l;
        if(bundle == null)
            i = -1;
        else
            i = bundle.getInt("android.media.browse.extra.PAGE", -1);
        if(bundle1 == null)
            j = -1;
        else
            j = bundle1.getInt("android.media.browse.extra.PAGE", -1);
        if(bundle == null)
            k = -1;
        else
            k = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if(bundle1 == null)
            l = -1;
        else
            l = bundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if(i == -1 || k == -1)
        {
            i = 0;
            k = 0x7fffffff;
        } else
        {
            i = k * i;
            k = (i + k) - 1;
        }
        if(j == -1 || l == -1)
        {
            j = 0;
            l = 0x7fffffff;
        } else
        {
            j = l * j;
            l = (j + l) - 1;
        }
        if(i <= j && j <= k)
            return true;
        return i <= l && l <= k;
    }
}
