// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import miui.content.res.IconCustomizer;
import miui.maml.*;

// Referenced classes of package miui.maml.util:
//            RendererCoreCache, FancyIconResourceLoader, PortableUtils

public class AppIconsHelper
{

    private AppIconsHelper()
    {
    }

    private static void checkVersion(Context context)
    {
        int i = context.getResources().getConfiguration().extraConfig.themeChanged;
        if(i > mThemeChanged)
        {
            clearCache();
            mThemeChanged = i;
        }
    }

    public static void cleanUp()
    {
        RenderThread.globalThreadStop();
    }

    public static void clearCache()
    {
        if(mRendererCoreCache != null)
            mRendererCoreCache.clear();
    }

    public static Drawable getFancyIconDrawable(Context context, String s, String s1, long l, UserHandle userhandle)
    {
        android.content.pm.ActivityInfo activityinfo = null;
        Object obj;
        PackageManager packagemanager = context.getPackageManager();
        obj = JVM INSTR new #88  <Class ComponentName>;
        ((ComponentName) (obj)).ComponentName(s, s1);
        obj = packagemanager.getActivityInfo(((ComponentName) (obj)), 0);
        activityinfo = ((android.content.pm.ActivityInfo) (obj));
_L2:
        return getIconDrawable(context, activityinfo, s, s1, l, userhandle, true);
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static Drawable getIconDrawable(Context context, PackageItemInfo packageiteminfo, PackageManager packagemanager)
    {
        return getIconDrawable(context, packageiteminfo, packagemanager, 0L);
    }

    public static Drawable getIconDrawable(Context context, PackageItemInfo packageiteminfo, PackageManager packagemanager, long l)
    {
        return getIconDrawable(context, packageiteminfo, packagemanager, l, new UserHandle(context.getUserId()));
    }

    public static Drawable getIconDrawable(Context context, PackageItemInfo packageiteminfo, PackageManager packagemanager, long l, UserHandle userhandle)
    {
        String s = packageiteminfo.packageName;
        String s1 = null;
        if(android.os.Build.VERSION.SDK_INT <= 24 || (packageiteminfo instanceof ApplicationInfo) ^ true)
            s1 = packageiteminfo.name;
        context = getIconDrawable(context, packageiteminfo, s, s1, l, userhandle);
        if(context != null)
            return context;
        else
            return packageiteminfo.loadIcon(packagemanager);
    }

    public static Drawable getIconDrawable(Context context, PackageItemInfo packageiteminfo, String s, String s1, long l)
    {
        return getIconDrawable(context, packageiteminfo, s, s1, l, new UserHandle(context.getUserId()));
    }

    public static Drawable getIconDrawable(Context context, PackageItemInfo packageiteminfo, String s, String s1, long l, UserHandle userhandle)
    {
        return getIconDrawable(context, packageiteminfo, s, s1, l, userhandle, false);
    }

    public static Drawable getIconDrawable(Context context, PackageItemInfo packageiteminfo, String s, String s1, long l, UserHandle userhandle, boolean flag)
    {
        if(mRendererCoreCache == null)
            mRendererCoreCache = new RendererCoreCache(new Handler(Looper.getMainLooper()));
        Object obj;
        String s2;
        String s3;
        Object obj1;
        try
        {
            checkVersion(context);
            StringBuilder stringbuilder = JVM INSTR new #159 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            s2 = stringbuilder.append(s).append(s1).append(userhandle.getIdentifier()).toString();
            s3 = IconCustomizer.getAnimatingIconRelativePath(packageiteminfo, s, s1);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("MAML AppIconsHelper", context.toString());
            return null;
        }
        if(s3 == null || !(flag ^ true)) goto _L2; else goto _L1
_L1:
        packageiteminfo = (WeakReference)mAnimatingIconsResourceManagers.get(s2);
        if(packageiteminfo != null) goto _L4; else goto _L3
_L3:
        packageiteminfo = null;
_L5:
        obj = packageiteminfo;
        if(packageiteminfo != null)
            break MISSING_BLOCK_LABEL_184;
        packageiteminfo = JVM INSTR new #159 <Class StringBuilder>;
        packageiteminfo.StringBuilder();
        packageiteminfo = packageiteminfo.append(s3).append("quiet/").toString();
        obj = JVM INSTR new #190 <Class LifecycleResourceManager>;
        obj1 = JVM INSTR new #192 <Class FancyIconResourceLoader>;
        ((FancyIconResourceLoader) (obj1)).FancyIconResourceLoader(packageiteminfo);
        ((LifecycleResourceManager) (obj)).LifecycleResourceManager(((miui.maml.ResourceLoader) (obj1)), 0x36ee80L, 0x57e40L);
        obj1 = mAnimatingIconsResourceManagers;
        packageiteminfo = JVM INSTR new #186 <Class WeakReference>;
        packageiteminfo.WeakReference(obj);
        ((HashMap) (obj1)).put(s2, packageiteminfo);
        if(obj == null)
            break MISSING_BLOCK_LABEL_363;
        packageiteminfo = JVM INSTR new #211 <Class AnimatingDrawable>;
        packageiteminfo.AnimatingDrawable(context, s, s1, ((ResourceManager) (obj)), userhandle);
_L8:
        for(; packageiteminfo == null; packageiteminfo = null)
            break MISSING_BLOCK_LABEL_216;

        PortableUtils.getUserBadgedIcon(context, packageiteminfo, userhandle);
        return packageiteminfo;
_L4:
        packageiteminfo = (ResourceManager)packageiteminfo.get();
          goto _L5
_L2:
        obj1 = mRendererCoreCache.get(s2, l);
        obj = obj1;
        if(obj1 != null) goto _L7; else goto _L6
_L6:
        if(s3 == null)
            break MISSING_BLOCK_LABEL_335;
        packageiteminfo = JVM INSTR new #159 <Class StringBuilder>;
        packageiteminfo.StringBuilder();
        packageiteminfo = packageiteminfo.append(s3).append("fancy/").toString();
_L9:
        s = mRendererCoreCache;
        s1 = JVM INSTR new #192 <Class FancyIconResourceLoader>;
        s1.FancyIconResourceLoader(packageiteminfo);
        obj = s.get(s2, context, l, s1, mOnCreateRootCallback);
_L7:
        if(obj == null)
            break MISSING_BLOCK_LABEL_345;
        if(((RendererCoreCache.RendererCoreInfo) (obj)).r == null)
            break MISSING_BLOCK_LABEL_345;
        packageiteminfo = new FancyDrawable(((RendererCoreCache.RendererCoreInfo) (obj)).r);
          goto _L8
        packageiteminfo = IconCustomizer.getFancyIconRelativePath(packageiteminfo, s, s1);
          goto _L9
        packageiteminfo = null;
          goto _L8
    }

    public static Drawable getIconDrawable(Context context, ResolveInfo resolveinfo, PackageManager packagemanager)
    {
        return getIconDrawable(context, resolveinfo, packagemanager, 0L);
    }

    public static Drawable getIconDrawable(Context context, ResolveInfo resolveinfo, PackageManager packagemanager, long l)
    {
        if(resolveinfo.activityInfo != null)
            resolveinfo = resolveinfo.activityInfo;
        else
            resolveinfo = resolveinfo.serviceInfo;
        return getIconDrawable(context, ((PackageItemInfo) (resolveinfo)), packagemanager, l);
    }

    public static Drawable getIconDrawable(Context context, String s, String s1, long l)
    {
        return getIconDrawable(context, s, s1, l, new UserHandle(context.getUserId()));
    }

    public static Drawable getIconDrawable(Context context, String s, String s1, long l, UserHandle userhandle)
    {
        android.content.pm.ActivityInfo activityinfo = null;
        Object obj;
        obj = context.getPackageManager();
        ComponentName componentname = JVM INSTR new #88  <Class ComponentName>;
        componentname.ComponentName(s, s1);
        obj = ((PackageManager) (obj)).getActivityInfo(componentname, 0);
        activityinfo = ((android.content.pm.ActivityInfo) (obj));
_L2:
        return getIconDrawable(context, ((PackageItemInfo) (activityinfo)), s, s1, l, userhandle);
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static final int TIME_DAY = 0x5265c00;
    public static final int TIME_HOUR = 0x36ee80;
    public static final int TIME_MIN = 60000;
    private static HashMap mAnimatingIconsResourceManagers = new HashMap();
    private static final RendererCoreCache.OnCreateRootCallback mOnCreateRootCallback = new RendererCoreCache.OnCreateRootCallback() {

        public void onCreateRoot(ScreenElementRoot screenelementroot)
        {
            if(screenelementroot != null)
                screenelementroot.setScaleByDensity(true);
        }

    }
;
    private static RendererCoreCache mRendererCoreCache;
    private static int mThemeChanged;

}
