// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.backup;

import android.app.AppGlobals;
import android.app.backup.BlobBackupHelper;
import android.content.pm.IPackageManager;
import android.util.Slog;

public class PermissionBackupHelper extends BlobBackupHelper
{

    public PermissionBackupHelper()
    {
        super(1, new String[] {
            "permissions"
        });
    }

    protected void applyRestoredPayload(String s, byte abyte0[])
    {
        IPackageManager ipackagemanager = AppGlobals.getPackageManager();
        if(!s.equals("permissions"))
            break MISSING_BLOCK_LABEL_22;
        ipackagemanager.restorePermissionGrants(abyte0, 0);
_L1:
        return;
        try
        {
            abyte0 = JVM INSTR new #47  <Class StringBuilder>;
            abyte0.StringBuilder();
            Slog.w("PermissionBackup", abyte0.append("Unexpected restore key ").append(s).toString());
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Slog.w("PermissionBackup", (new StringBuilder()).append("Unable to restore key ").append(s).toString());
        }
          goto _L1
    }

    protected byte[] getBackupPayload(String s)
    {
        Object obj = AppGlobals.getPackageManager();
        try
        {
            if(s.equals("permissions"))
                return ((IPackageManager) (obj)).getPermissionGrantBackup(0);
            obj = JVM INSTR new #47  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Slog.w("PermissionBackup", ((StringBuilder) (obj)).append("Unexpected backup key ").append(s).toString());
        }
        catch(Exception exception)
        {
            Slog.e("PermissionBackup", (new StringBuilder()).append("Unable to store payload ").append(s).toString());
        }
        return null;
    }

    private static final boolean DEBUG = false;
    private static final String KEY_PERMISSIONS = "permissions";
    private static final int STATE_VERSION = 1;
    private static final String TAG = "PermissionBackup";
}
