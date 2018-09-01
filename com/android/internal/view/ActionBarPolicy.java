// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.*;
import android.util.DisplayMetrics;

public class ActionBarPolicy
{

    private ActionBarPolicy(Context context)
    {
        mContext = context;
    }

    public static ActionBarPolicy get(Context context)
    {
        return new ActionBarPolicy(context);
    }

    public boolean enableHomeButtonByDefault()
    {
        boolean flag;
        if(mContext.getApplicationInfo().targetSdkVersion < 14)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int getEmbeddedMenuWidthLimit()
    {
        return mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int getMaxActionButtons()
    {
        Configuration configuration = mContext.getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int j;
        for(j = configuration.screenHeightDp; configuration.smallestScreenWidthDp > 600 || i > 960 && j > 720 || i > 720 && j > 960;)
            return 5;

        while(i >= 500 || i > 640 && j > 480 || i > 480 && j > 640) 
            return 4;
        return i < 360 ? 2 : 3;
    }

    public int getStackedTabMaxWidth()
    {
        return mContext.getResources().getDimensionPixelSize(0x1050015);
    }

    public int getTabContainerHeight()
    {
        TypedArray typedarray = mContext.obtainStyledAttributes(null, com.android.internal.R.styleable.ActionBar, 0x10102ce, 0);
        int i = typedarray.getLayoutDimension(4, 0);
        Resources resources = mContext.getResources();
        int j = i;
        if(!hasEmbeddedTabs())
            j = Math.min(i, resources.getDimensionPixelSize(0x1050014));
        typedarray.recycle();
        return j;
    }

    public boolean hasEmbeddedTabs()
    {
        boolean flag;
        Configuration configuration;
        int i;
        int j;
        boolean flag1;
        flag = true;
        if(mContext.getApplicationInfo().targetSdkVersion >= 16)
            return mContext.getResources().getBoolean(0x1120001);
        configuration = mContext.getResources().getConfiguration();
        i = configuration.screenWidthDp;
        j = configuration.screenHeightDp;
        flag1 = flag;
        if(configuration.orientation == 2) goto _L2; else goto _L1
_L1:
        if(i < 480) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        if(i >= 640)
        {
            flag1 = flag;
            if(j >= 480)
                continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    public boolean showsOverflowMenuButton()
    {
        return true;
    }

    private Context mContext;
}
