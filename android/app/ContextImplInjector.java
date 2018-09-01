// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.location.LocationPolicyManager;
import android.os.ServiceManager;
import miui.security.SecurityManager;

// Referenced classes of package android.app:
//            SystemServiceRegistry, ContextImpl

class ContextImplInjector
{

    ContextImplInjector()
    {
    }

    static void registerMiuiServices()
    {
        SystemServiceRegistry.registerService("security", miui/security/SecurityManager, new SystemServiceRegistry.CachedServiceFetcher() {

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

            public SecurityManager createService(ContextImpl contextimpl)
            {
                contextimpl = ServiceManager.getService("security");
                if(contextimpl == null)
                    return null;
                else
                    return new SecurityManager(miui.security.ISecurityManager.Stub.asInterface(contextimpl));
            }

        }
);
        SystemServiceRegistry.registerService("locationpolicy", android/location/LocationPolicyManager, new SystemServiceRegistry.CachedServiceFetcher() {

            public LocationPolicyManager createService(ContextImpl contextimpl)
            {
                return new LocationPolicyManager(android.location.ILocationPolicyManager.Stub.asInterface(ServiceManager.getService("locationpolicy")));
            }

            public volatile Object createService(ContextImpl contextimpl)
                throws android.os.ServiceManager.ServiceNotFoundException
            {
                return createService(contextimpl);
            }

        }
);
    }
}
