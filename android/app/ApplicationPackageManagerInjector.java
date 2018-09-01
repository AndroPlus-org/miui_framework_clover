// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import miui.maml.MamlDrawable;
import miui.os.Build;
import miui.os.MiuiInit;

public class ApplicationPackageManagerInjector
{

    public ApplicationPackageManagerInjector()
    {
    }

    public static boolean hookGetBadgedDrawable(Drawable drawable, Drawable drawable1, Rect rect)
    {
        if(drawable instanceof MamlDrawable)
        {
            ((MamlDrawable)drawable).setBadgeInfo(drawable1, rect);
            return true;
        } else
        {
            return false;
        }
    }

    public static PackageInfo hookGetPackageInfo(Context context, PackageInfo packageinfo, int i)
    {
        if(!Build.IS_INTERNATIONAL_BUILD)
            return packageinfo;
        if(packageinfo == null || "com.netflix.mediaclient".equals(packageinfo.packageName) ^ true)
            return packageinfo;
        if(!MiuiInit.isPreinstalledPackage(packageinfo.packageName))
            return packageinfo;
        i = UserHandle.getAppId(Process.myUid());
        if(i != UserHandle.getAppId(packageinfo.applicationInfo.uid))
            return packageinfo;
        Log.i("ApplicationPackageManagerInjector", (new StringBuilder()).append("HookPackageInfo for ").append(packageinfo.packageName).append(" with version ").append(packageinfo.versionCode).append(" from appId ").append(i).toString());
        context = packageinfo.applicationInfo;
        context.flags = ((ApplicationInfo) (context)).flags | 1;
        if(MiuiInit.getPreinstalledAppVersion(packageinfo.packageName) < packageinfo.versionCode)
        {
            context = packageinfo.applicationInfo;
            context.flags = ((ApplicationInfo) (context)).flags | 0x80;
        }
        return packageinfo;
    }

    private static final String TAG = "ApplicationPackageManagerInjector";
    private static final String sNetflixPackage = "com.netflix.mediaclient";
}
