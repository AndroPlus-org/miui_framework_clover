// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.util.ArraySet;
import miui.os.Build;

public class AppOpsManagerInjector
{

    public AppOpsManagerInjector()
    {
    }

    public static boolean isAutoStartRestriction(String s)
    {
        return sAutoStartRestrictions.contains(s);
    }

    public static int opToDefaultMode(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 10008: 
        case 10019: 
            return 1;
        }
    }

    private static ArraySet sAutoStartRestrictions;

    static 
    {
        sAutoStartRestrictions = new ArraySet();
        sAutoStartRestrictions.add("com.android.fileexplorer");
        sAutoStartRestrictions.add("com.android.thememanager");
        sAutoStartRestrictions.add("com.android.browser");
        sAutoStartRestrictions.add("com.android.soundrecorder");
        sAutoStartRestrictions.add("com.android.calculator2");
        sAutoStartRestrictions.add("com.android.camera");
        sAutoStartRestrictions.add("com.android.quicksearchbox");
        sAutoStartRestrictions.add("com.android.providers.downloads.ui");
        sAutoStartRestrictions.add("com.android.email");
        sAutoStartRestrictions.add("com.android.midrive");
        sAutoStartRestrictions.add("com.miui.mipub");
        sAutoStartRestrictions.add("com.miui.video");
        sAutoStartRestrictions.add("com.miui.cleanmaster");
        sAutoStartRestrictions.add("com.miui.varcodescanner");
        sAutoStartRestrictions.add("com.miui.compass");
        sAutoStartRestrictions.add("com.miui.voiceassist");
        sAutoStartRestrictions.add("com.miui.yellowpage");
        sAutoStartRestrictions.add("com.miui.personalassistant");
        sAutoStartRestrictions.add("com.miui.backup");
        sAutoStartRestrictions.add("com.miui.notes");
        sAutoStartRestrictions.add("com.miui.translation.kingsoft");
        sAutoStartRestrictions.add("com.miui.home.launcher.assistant");
        sAutoStartRestrictions.add("com.miui.fm");
        sAutoStartRestrictions.add("com.mi.misupport");
        sAutoStartRestrictions.add("com.xiaomi.scanner");
        sAutoStartRestrictions.add("com.xiaomi.vip");
        sAutoStartRestrictions.add("com.xiaomi.pass");
        sAutoStartRestrictions.add("com.xiaomi.account");
        sAutoStartRestrictions.add("com.xiaomi.pricecheck");
        sAutoStartRestrictions.add("com.xiaomi.gamecenter");
        sAutoStartRestrictions.add("com.xiaomi.gamecenter.sdk.service");
        sAutoStartRestrictions.add("com.xiaomi.midrop");
        sAutoStartRestrictions.add("com.xiaomi.miplay");
        sAutoStartRestrictions.add("com.cleanmaster.sdk");
        sAutoStartRestrictions.add("com.sohu.inputmethod.sogou.xiaomi");
        sAutoStartRestrictions.add("com.miui.securityadd");
        sAutoStartRestrictions.add("com.miui.guardprovider");
        sAutoStartRestrictions.add("com.miui.gallery");
        sAutoStartRestrictions.add("com.miui.player");
        sAutoStartRestrictions.add("com.miui.virtualsim");
        if(Build.IS_TABLET)
            sAutoStartRestrictions.clear();
    }
}
