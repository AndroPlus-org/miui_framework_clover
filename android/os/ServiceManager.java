// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Log;
import com.android.internal.os.BinderInternal;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package android.os:
//            RemoteException, IServiceManager, IBinder, Binder, 
//            ServiceManagerNative

public final class ServiceManager
{
    public static class ServiceNotFoundException extends Exception
    {

        public ServiceNotFoundException(String s)
        {
            super((new StringBuilder()).append("No service published for: ").append(s).toString());
        }
    }


    public ServiceManager()
    {
    }

    public static void addService(String s, IBinder ibinder)
    {
        getIServiceManager().addService(s, ibinder, false);
_L1:
        return;
        s;
        Log.e("ServiceManager", "error in addService", s);
          goto _L1
    }

    public static void addService(String s, IBinder ibinder, boolean flag)
    {
        getIServiceManager().addService(s, ibinder, flag);
_L1:
        return;
        s;
        Log.e("ServiceManager", "error in addService", s);
          goto _L1
    }

    public static IBinder checkService(String s)
    {
        IBinder ibinder;
        try
        {
            ibinder = (IBinder)sCache.get(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("ServiceManager", "error in checkService", s);
            return null;
        }
        if(ibinder != null)
            return ibinder;
        s = Binder.allowBlocking(getIServiceManager().checkService(s));
        return s;
    }

    private static IServiceManager getIServiceManager()
    {
        if(sServiceManager != null)
        {
            return sServiceManager;
        } else
        {
            sServiceManager = ServiceManagerNative.asInterface(Binder.allowBlocking(BinderInternal.getContextObject()));
            return sServiceManager;
        }
    }

    public static IBinder getService(String s)
    {
        IBinder ibinder;
        try
        {
            ibinder = (IBinder)sCache.get(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("ServiceManager", "error in getService", s);
            return null;
        }
        if(ibinder != null)
            return ibinder;
        s = Binder.allowBlocking(getIServiceManager().getService(s));
        return s;
    }

    public static IBinder getServiceOrThrow(String s)
        throws ServiceNotFoundException
    {
        IBinder ibinder = getService(s);
        if(ibinder != null)
            return ibinder;
        else
            throw new ServiceNotFoundException(s);
    }

    public static void initServiceCache(Map map)
    {
        if(sCache.size() != 0)
        {
            throw new IllegalStateException("setServiceCache may only be called once");
        } else
        {
            sCache.putAll(map);
            return;
        }
    }

    public static String[] listServices()
    {
        String as[];
        try
        {
            as = getIServiceManager().listServices();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("ServiceManager", "error in listServices", remoteexception);
            return null;
        }
        return as;
    }

    private static final String TAG = "ServiceManager";
    private static HashMap sCache = new HashMap();
    private static IServiceManager sServiceManager;

}
