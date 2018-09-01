// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher;

import android.app.*;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.util.Log;
import com.miui.internal.contentcatcher.IInterceptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// Referenced classes of package miui.contentcatcher:
//            InterceptorProxy

public class InterceptorFactory
{

    public InterceptorFactory()
    {
    }

    public static IInterceptor createInterceptor(Activity activity)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Activity activity1;
        Activity activity2;
        Activity activity3;
        Activity activity4;
        Activity activity5;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        activity1 = ((Activity) (obj4));
        activity2 = obj;
        activity3 = obj1;
        activity4 = obj2;
        activity5 = obj3;
        long l = SystemClock.uptimeMillis();
        activity1 = ((Activity) (obj4));
        activity2 = obj;
        activity3 = obj1;
        activity4 = obj2;
        activity5 = obj3;
        Object obj5 = initInterceptorClass();
        if(obj5 == null)
            return null;
        activity1 = ((Activity) (obj4));
        activity2 = obj;
        activity3 = obj1;
        activity4 = obj2;
        activity5 = obj3;
        obj5 = ((Class) (obj5)).getConstructor(new Class[] {
            android/app/Activity
        });
        if(obj5 == null)
            return null;
        activity1 = ((Activity) (obj4));
        activity2 = obj;
        activity3 = obj1;
        activity4 = obj2;
        activity5 = obj3;
        if(((Constructor) (obj5)).isAccessible())
            break MISSING_BLOCK_LABEL_151;
        activity1 = ((Activity) (obj4));
        activity2 = obj;
        activity3 = obj1;
        activity4 = obj2;
        activity5 = obj3;
        ((Constructor) (obj5)).setAccessible(true);
        activity1 = ((Activity) (obj4));
        activity2 = obj;
        activity3 = obj1;
        activity4 = obj2;
        activity5 = obj3;
        activity = (IInterceptor)((Constructor) (obj5)).newInstance(new Object[] {
            activity
        });
        obj4 = activity;
        activity1 = activity;
        activity2 = activity;
        activity3 = activity;
        activity4 = activity;
        activity5 = activity;
        if(!DBG)
            break MISSING_BLOCK_LABEL_297;
        activity1 = activity;
        activity2 = activity;
        activity3 = activity;
        activity4 = activity;
        activity5 = activity;
        obj4 = JVM INSTR new #91  <Class StringBuilder>;
        activity1 = activity;
        activity2 = activity;
        activity3 = activity;
        activity4 = activity;
        activity5 = activity;
        ((StringBuilder) (obj4)).StringBuilder();
        activity1 = activity;
        activity2 = activity;
        activity3 = activity;
        activity4 = activity;
        activity5 = activity;
        Log.d("InterceptorFactory", ((StringBuilder) (obj4)).append("createInterceptor took ").append(SystemClock.uptimeMillis() - l).append("ms").toString());
        obj4 = activity;
_L2:
        return ((IInterceptor) (obj4));
        activity;
        Log.e("InterceptorFactory", "Exception", activity);
        obj4 = activity1;
        continue; /* Loop/switch isn't completed */
        activity;
        Log.e("InterceptorFactory", "NoSuchMethodException", activity);
        obj4 = activity2;
        continue; /* Loop/switch isn't completed */
        activity;
        Log.e("InterceptorFactory", "InvocationTargetException", activity);
        obj4 = activity3;
        continue; /* Loop/switch isn't completed */
        activity;
        Log.e("InterceptorFactory", "IllegalAccessException", activity);
        obj4 = activity4;
        continue; /* Loop/switch isn't completed */
        activity;
        Log.e("InterceptorFactory", "InstantiationException", activity);
        obj4 = activity5;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public static PackageInfo getInterceptorPackageInfo()
    {
        if(DBG)
            Log.d("InterceptorFactory", "getInterceptorPackageInfo");
        if(mPackageInfo != null) goto _L2; else goto _L1
_L1:
        Object obj = mPackageInfoLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = mPackageInfo;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_105;
        long l;
        l = SystemClock.uptimeMillis();
        obj1 = AppGlobals.getInitialApplication().getPackageManager();
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_53;
        obj;
        JVM INSTR monitorexit ;
        return null;
        mPackageInfo = ((PackageManager) (obj1)).getPackageInfo("com.miui.contentcatcher", 0);
        if(DBG)
        {
            StringBuilder stringbuilder = JVM INSTR new #91  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("InterceptorFactory", stringbuilder.append("getPackageInfo took ").append(SystemClock.uptimeMillis() - l).append("ms").toString());
        }
_L3:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return mPackageInfo;
        Object obj2;
        obj2;
        Log.e("InterceptorFactory", "Exception", ((Throwable) (obj2)));
          goto _L3
        obj2;
        throw obj2;
        obj2;
        Log.e("InterceptorFactory", "NameNotFoundException", ((Throwable) (obj2)));
          goto _L3
    }

    private static Class initInterceptorClass()
    {
        if(DBG)
            Log.d("InterceptorFactory", "initInterceptorClass");
        if(sInterceptorClazz != null) goto _L2; else goto _L1
_L1:
        miui/contentcatcher/InterceptorFactory;
        JVM INSTR monitorenter ;
        long l;
        PackageInfo packageinfo;
        if(sInterceptorClazz != null)
            break MISSING_BLOCK_LABEL_147;
        l = SystemClock.uptimeMillis();
        packageinfo = getInterceptorPackageInfo();
        if(packageinfo == null)
            break MISSING_BLOCK_LABEL_147;
        Application application = AppGlobals.getInitialApplication();
        if(DBG)
        {
            StringBuilder stringbuilder1 = JVM INSTR new #91  <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Log.d("InterceptorFactory", stringbuilder1.append("packageInfo.packageName: ").append(packageinfo.packageName).toString());
        }
        sInterceptorClazz = Class.forName("com.miui.contentcatcher.Interceptor", true, application.createPackageContext(packageinfo.packageName, 3).getClassLoader());
        if(DBG)
        {
            StringBuilder stringbuilder = JVM INSTR new #91  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("InterceptorFactory", stringbuilder.append("initInterceptorClass took ").append(SystemClock.uptimeMillis() - l).append("ms").toString());
        }
_L3:
        miui/contentcatcher/InterceptorFactory;
        JVM INSTR monitorexit ;
_L2:
        return sInterceptorClazz;
        Object obj;
        obj;
        Log.e("InterceptorFactory", "Error ", ((Throwable) (obj)));
          goto _L3
        obj;
        throw obj;
        obj;
        Log.e("InterceptorFactory", "Exception ", ((Throwable) (obj)));
          goto _L3
        obj;
        Log.e("InterceptorFactory", "NameNotFoundException", ((Throwable) (obj)));
          goto _L3
    }

    private static final String CONTENT_CATCHER_PACKAGE_NAME = "com.miui.contentcatcher";
    private static final String CONTENT_INJECTOR_CLASS_NAME = "com.miui.contentcatcher.Interceptor";
    private static final boolean DBG;
    private static final String TAG = "InterceptorFactory";
    private static volatile PackageInfo mPackageInfo = null;
    private static final Object mPackageInfoLock = new Object();
    private static volatile Class sInterceptorClazz = null;

    static 
    {
        DBG = InterceptorProxy.DBG;
    }
}
