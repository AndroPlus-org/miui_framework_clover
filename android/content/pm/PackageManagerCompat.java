// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import java.util.List;

// Referenced classes of package android.content.pm:
//            PackageManager

public class PackageManagerCompat
{

    public PackageManagerCompat()
    {
    }

    public static List getInstalledPackagesAsUser(PackageManager packagemanager, int i, int j)
    {
        return packagemanager.getInstalledPackagesAsUser(i, j);
    }

    public static int getPackageUidAsUser(PackageManager packagemanager, String s, int i)
        throws PackageManager.NameNotFoundException
    {
        return packagemanager.getPackageUidAsUser(s, 8192, i);
    }
}
