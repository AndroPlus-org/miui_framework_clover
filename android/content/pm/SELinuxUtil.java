// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;


// Referenced classes of package android.content.pm:
//            PackageUserState

public final class SELinuxUtil
{

    public SELinuxUtil()
    {
    }

    public static String assignSeinfoUser(PackageUserState packageuserstate)
    {
        if(packageuserstate.instantApp)
            return ":ephemeralapp:complete";
        else
            return ":complete";
    }

    public static final String COMPLETE_STR = ":complete";
    private static final String INSTANT_APP_STR = ":ephemeralapp";
}
