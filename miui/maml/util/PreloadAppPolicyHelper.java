// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import miui.content.pm.PreloadedAppPolicy;

public class PreloadAppPolicyHelper
{

    public PreloadAppPolicyHelper()
    {
    }

    public static boolean installPreloadedDataApp(Context context, String s, Intent intent, Bundle bundle)
    {
        return PreloadedAppPolicy.installPreloadedDataApp(context, s, new android.content.pm.IPackageInstallObserver.Stub(context, intent, bundle) {

            public void packageInstalled(String s1, int i)
                throws RemoteException
            {
                context.startActivity(intent, bundle);
            }

            final Bundle val$bundle;
            final Context val$context;
            final Intent val$intent;

            
            {
                context = context1;
                intent = intent1;
                bundle = bundle1;
                super();
            }
        }
, 1);
    }
}
