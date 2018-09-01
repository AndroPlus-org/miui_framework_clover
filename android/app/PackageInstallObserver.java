// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.content.pm.IPackageInstallObserver2;
import android.os.Bundle;

public class PackageInstallObserver
{

    public PackageInstallObserver()
    {
    }

    public IPackageInstallObserver2 getBinder()
    {
        return mBinder;
    }

    public void onPackageInstalled(String s, int i, String s1, Bundle bundle)
    {
    }

    public void onUserActionRequired(Intent intent)
    {
    }

    private final android.content.pm.IPackageInstallObserver2.Stub mBinder = new android.content.pm.IPackageInstallObserver2.Stub() {

        public void onPackageInstalled(String s, int i, String s1, Bundle bundle)
        {
            PackageInstallObserver.this.onPackageInstalled(s, i, s1, bundle);
        }

        public void onUserActionRequired(Intent intent)
        {
            PackageInstallObserver.this.onUserActionRequired(intent);
        }

        final PackageInstallObserver this$0;

            
            {
                this$0 = PackageInstallObserver.this;
                super();
            }
    }
;
}
