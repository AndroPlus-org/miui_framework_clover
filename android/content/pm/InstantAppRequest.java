// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.Intent;
import android.os.Bundle;

// Referenced classes of package android.content.pm:
//            AuxiliaryResolveInfo

public final class InstantAppRequest
{

    public InstantAppRequest(AuxiliaryResolveInfo auxiliaryresolveinfo, Intent intent, String s, String s1, int i, Bundle bundle, boolean flag)
    {
        responseObj = auxiliaryresolveinfo;
        origIntent = intent;
        resolvedType = s;
        callingPackage = s1;
        userId = i;
        verificationBundle = bundle;
        resolveForStart = flag;
    }

    public final String callingPackage;
    public final Intent origIntent;
    public final boolean resolveForStart;
    public final String resolvedType;
    public final AuxiliaryResolveInfo responseObj;
    public final int userId;
    public final Bundle verificationBundle;
}
