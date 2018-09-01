// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.securityspace;

import android.content.*;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.ArrayMap;
import java.lang.ref.WeakReference;
import java.util.*;
import miui.security.ISecurityManager;

// Referenced classes of package miui.securityspace:
//            CrossUserUtilsCompat, XSpaceUserHandle

public class CrossUserUtils
{

    public CrossUserUtils()
    {
    }

    public static Uri addUserIdForUri(Uri uri, int i)
    {
        return CrossUserUtilsCompat.addUserIdForUri(uri, i);
    }

    public static Uri addUserIdForUri(Uri uri, Context context, String s, Intent intent)
    {
        return CrossUserUtilsCompat.addUserIdForUri(uri, context, s, intent);
    }

    public static boolean checkCrossPermission(String s, int i)
    {
        return s != null && noCheckContentProviderPermissionPkg.containsKey(s) && i == 0;
    }

    public static boolean checkUidPermission(Context context, String s)
    {
        return CrossUserUtilsCompat.checkUidPermission(context, s);
    }

    static Drawable createDrawableWithCache(Context context, Bitmap bitmap)
    {
        ArrayMap arraymap = sBitmapCache;
        arraymap;
        JVM INSTR monitorenter ;
        Object obj = (WeakReference)sBitmapCache.get(Integer.valueOf(bitmap.hashCode()));
        if(obj == null)
            break MISSING_BLOCK_LABEL_46;
        if(((WeakReference) (obj)).get() == null)
            break MISSING_BLOCK_LABEL_46;
        context = (Drawable)((WeakReference) (obj)).get();
        arraymap;
        JVM INSTR monitorexit ;
        return context;
        if(obj == null)
            break MISSING_BLOCK_LABEL_53;
        recycleCacheMap();
        obj = JVM INSTR new #102 <Class BitmapDrawable>;
        ((BitmapDrawable) (obj)).BitmapDrawable(context.getResources(), bitmap.copy(bitmap.getConfig(), true));
        context = sBitmapCache;
        int i = bitmap.hashCode();
        bitmap = JVM INSTR new #92  <Class WeakReference>;
        bitmap.WeakReference(obj);
        context.put(Integer.valueOf(i), bitmap);
        arraymap;
        JVM INSTR monitorexit ;
        return ((Drawable) (obj));
        context;
        throw context;
    }

    public static String getComponentStringWithUserId(ComponentName componentname, int i)
    {
        return (new StringBuilder()).append(componentname.flattenToShortString()).append("_").append(i).toString();
    }

    public static String getComponentStringWithUserIdAndTaskId(ComponentName componentname, int i, int j)
    {
        return (new StringBuilder()).append(componentname.flattenToShortString()).append("_").append(i).append("_").append(j).toString();
    }

    public static int getCurrentUserId()
    {
        int i;
        try
        {
            if(sISecurityManager == null)
                sISecurityManager = miui.security.ISecurityManager.Stub.asInterface(ServiceManager.getService("security"));
            i = sISecurityManager.getCurrentUserId();
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return 0;
        }
        return i;
    }

    public static Drawable getOriginalAppIcon(Context context, String s)
    {
        return CrossUserUtilsCompat.getOriginalAppIcon(context, s);
    }

    public static int getSecondSpaceId()
    {
        int i;
        try
        {
            if(sISecurityManager == null)
                sISecurityManager = miui.security.ISecurityManager.Stub.asInterface(ServiceManager.getService("security"));
            i = sISecurityManager.getSecondSpaceId();
        }
        catch(RemoteException remoteexception)
        {
            remoteexception.printStackTrace();
            return -10000;
        }
        return i;
    }

    public static boolean hasAirSpace(Context context)
    {
        return false;
    }

    public static boolean hasSecondSpace(Context context)
    {
        return CrossUserUtilsCompat.hasSecondSpace(context);
    }

    public static boolean hasXSpaceUser(Context context)
    {
        return CrossUserUtilsCompat.hasXSpaceUser(context);
    }

    public static boolean isAirSpace(Context context, int i)
    {
        return false;
    }

    public static boolean needCheckUser(ProviderInfo providerinfo, String s, int i, boolean flag)
    {
        if(i == 0 && XSpaceUserHandle.isXSpaceUserCalling())
            return false;
        if(flag && providerinfo != null && s != null)
        {
            s = (String)noCheckContentProviderPermissionPkg.get(s);
            if(s != null && s.equals(providerinfo.authority))
                return false;
        }
        return flag;
    }

    private static void recycleCacheMap()
    {
        ArrayMap arraymap = sBitmapCache;
        arraymap;
        JVM INSTR monitorenter ;
        Iterator iterator = sBitmapCache.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(((WeakReference)((java.util.Map.Entry)iterator.next()).getValue()).get() == null)
                iterator.remove();
        } while(true);
        break MISSING_BLOCK_LABEL_64;
        Exception exception;
        exception;
        throw exception;
        arraymap;
        JVM INSTR monitorexit ;
    }

    public static final String ACTION_XSPACE_RESOLVER_ACTIVITY = "miui.intent.action.ACTION_XSPACE_RESOLVER_ACTIVITY";
    public static final String EXTRA_PICKED_USER_ID = "android.intent.extra.picked_user_id";
    public static final String EXTRA_XSPACE_RESOLVER_ACTIVITY_AIM_PACKAGE = "android.intent.extra.xspace_resolver_activity_aim_package";
    public static final String EXTRA_XSPACE_RESOLVER_ACTIVITY_ORIGINAL_INTENT = "android.intent.extra.xspace_resolver_activity_original_intent";
    private static Map noCheckContentProviderPermissionPkg;
    private static ArrayMap sBitmapCache = new ArrayMap();
    private static ISecurityManager sISecurityManager = null;

    static 
    {
        noCheckContentProviderPermissionPkg = new HashMap();
        noCheckContentProviderPermissionPkg.put("com.android.incallui", "contacts;com.android.contacts");
    }
}
