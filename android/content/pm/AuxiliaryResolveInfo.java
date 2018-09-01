// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.*;

// Referenced classes of package android.content.pm:
//            InstantAppResolveInfo

public final class AuxiliaryResolveInfo extends IntentFilter
{

    public AuxiliaryResolveInfo(InstantAppResolveInfo instantappresolveinfo, IntentFilter intentfilter, String s, String s1, boolean flag, Intent intent)
    {
        super(intentfilter);
        resolveInfo = instantappresolveinfo;
        packageName = instantappresolveinfo.getPackageName();
        splitName = s;
        token = s1;
        needsPhaseTwo = flag;
        versionCode = instantappresolveinfo.getVersionCode();
        failureIntent = intent;
        installFailureActivity = null;
    }

    public AuxiliaryResolveInfo(String s, String s1, ComponentName componentname, int i, Intent intent)
    {
        packageName = s;
        installFailureActivity = componentname;
        splitName = s1;
        versionCode = i;
        resolveInfo = null;
        token = null;
        needsPhaseTwo = false;
        failureIntent = intent;
    }

    public final Intent failureIntent;
    public final ComponentName installFailureActivity;
    public final boolean needsPhaseTwo;
    public final String packageName;
    public final InstantAppResolveInfo resolveInfo;
    public final String splitName;
    public final String token;
    public final int versionCode;
}
