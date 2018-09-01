// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securityspace;

import java.util.*;

public class XSpaceConstant
{

    public XSpaceConstant()
    {
    }

    public static final ArrayList REQUIRED_APPS;
    public static final Map SPECIAL_APPS;

    static 
    {
        REQUIRED_APPS = new ArrayList();
        SPECIAL_APPS = new HashMap();
        REQUIRED_APPS.add("android");
        REQUIRED_APPS.add("com.android.keychain");
        REQUIRED_APPS.add("com.google.android.webview");
        REQUIRED_APPS.add("com.google.android.packageinstaller");
        REQUIRED_APPS.add("com.xiaomi.gamecenter.sdk.service");
        REQUIRED_APPS.add("com.miui.securitycore");
        REQUIRED_APPS.add("com.miui.analytics");
        ArrayList arraylist = new ArrayList();
        arraylist.add("com.android.packageinstaller.permission.ui.GrantPermissionsActivity");
        arraylist.add("com.android.packageinstaller.permission.ui.ManagePermissionsActivity");
        SPECIAL_APPS.put("com.google.android.packageinstaller", arraylist);
        arraylist = new ArrayList();
        arraylist.add("com.miui.xspace.receiver.MediaScannerReceiver");
        arraylist.add("com.miui.xspace.receiver.InstallShortcutReceiver");
        SPECIAL_APPS.put("com.miui.securitycore", arraylist);
    }
}
