// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;

import android.os.RemoteCallback;

// Referenced classes of package android.accounts:
//            Account

public abstract class AccountManagerInternal
{
    public static interface OnAppPermissionChangeListener
    {

        public abstract void onAppPermissionChanged(Account account, int i);
    }


    public AccountManagerInternal()
    {
    }

    public abstract void addOnAppPermissionChangeListener(OnAppPermissionChangeListener onapppermissionchangelistener);

    public abstract byte[] backupAccountAccessPermissions(int i);

    public abstract boolean hasAccountAccess(Account account, int i);

    public abstract void requestAccountAccess(Account account, String s, int i, RemoteCallback remotecallback);

    public abstract void restoreAccountAccessPermissions(byte abyte0[], int i);
}
