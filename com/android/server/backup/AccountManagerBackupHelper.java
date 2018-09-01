// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.backup;

import android.accounts.AccountManagerInternal;
import android.app.backup.BlobBackupHelper;
import android.util.Slog;
import com.android.server.LocalServices;

public class AccountManagerBackupHelper extends BlobBackupHelper
{

    public AccountManagerBackupHelper()
    {
        super(1, new String[] {
            "account_access_grants"
        });
    }

    protected void applyRestoredPayload(String s, byte abyte0[])
    {
        AccountManagerInternal accountmanagerinternal = (AccountManagerInternal)LocalServices.getService(android/accounts/AccountManagerInternal);
        if(!s.equals("account_access_grants"))
            break MISSING_BLOCK_LABEL_25;
        accountmanagerinternal.restoreAccountAccessPermissions(abyte0, 0);
_L1:
        return;
        try
        {
            abyte0 = JVM INSTR new #47  <Class StringBuilder>;
            abyte0.StringBuilder();
            Slog.w("AccountsBackup", abyte0.append("Unexpected restore key ").append(s).toString());
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Slog.w("AccountsBackup", (new StringBuilder()).append("Unable to restore key ").append(s).toString());
        }
          goto _L1
    }

    protected byte[] getBackupPayload(String s)
    {
        Object obj = (AccountManagerInternal)LocalServices.getService(android/accounts/AccountManagerInternal);
        try
        {
            if(s.equals("account_access_grants"))
                return ((AccountManagerInternal) (obj)).backupAccountAccessPermissions(0);
            obj = JVM INSTR new #47  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Slog.w("AccountsBackup", ((StringBuilder) (obj)).append("Unexpected backup key ").append(s).toString());
        }
        catch(Exception exception)
        {
            Slog.e("AccountsBackup", (new StringBuilder()).append("Unable to store payload ").append(s).toString());
        }
        return new byte[0];
    }

    private static final boolean DEBUG = false;
    private static final String KEY_ACCOUNT_ACCESS_GRANTS = "account_access_grants";
    private static final int STATE_VERSION = 1;
    private static final String TAG = "AccountsBackup";
}
