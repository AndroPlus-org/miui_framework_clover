// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.Intent;
import android.content.pm.IPackageDeleteObserver2;

public class PackageDeleteObserver
{

    public PackageDeleteObserver()
    {
    }

    public IPackageDeleteObserver2 getBinder()
    {
        return mBinder;
    }

    public void onPackageDeleted(String s, int i, String s1)
    {
    }

    public void onUserActionRequired(Intent intent)
    {
    }

    private final android.content.pm.IPackageDeleteObserver2.Stub mBinder = new android.content.pm.IPackageDeleteObserver2.Stub() {

        public void onPackageDeleted(String s, int i, String s1)
        {
            PackageDeleteObserver.this.onPackageDeleted(s, i, s1);
        }

        public void onUserActionRequired(Intent intent)
        {
            PackageDeleteObserver.this.onUserActionRequired(intent);
        }

        final PackageDeleteObserver this$0;

            
            {
                this$0 = PackageDeleteObserver.this;
                super();
            }
    }
;
}
