// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.backup;

import android.app.AppGlobals;
import android.app.backup.BlobBackupHelper;
import android.content.pm.IPackageManager;
import android.util.Slog;

public class PreferredActivityBackupHelper extends BlobBackupHelper
{

    public PreferredActivityBackupHelper()
    {
        super(3, new String[] {
            "preferred-activity", "default-apps", "intent-verification"
        });
    }

    protected void applyRestoredPayload(String s, byte abyte0[])
    {
        IPackageManager ipackagemanager = AppGlobals.getPackageManager();
        if(!s.equals("preferred-activity")) goto _L2; else goto _L1
_L1:
        ipackagemanager.restorePreferredActivities(abyte0, 0);
_L3:
        return;
_L2:
label0:
        {
            if(!s.equals("default-apps"))
                break label0;
            ipackagemanager.restoreDefaultApps(abyte0, 0);
        }
          goto _L3
label1:
        {
            if(!s.equals("intent-verification"))
                break label1;
            ipackagemanager.restoreIntentFilterVerification(abyte0, 0);
        }
          goto _L3
        try
        {
            abyte0 = JVM INSTR new #56  <Class StringBuilder>;
            abyte0.StringBuilder();
            Slog.w("PreferredBackup", abyte0.append("Unexpected restore key ").append(s).toString());
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            Slog.w("PreferredBackup", (new StringBuilder()).append("Unable to restore key ").append(s).toString());
        }
          goto _L3
    }

    protected byte[] getBackupPayload(String s)
    {
        Object obj = AppGlobals.getPackageManager();
        try
        {
            if(s.equals("preferred-activity"))
                return ((IPackageManager) (obj)).getPreferredActivityBackup(0);
            if(s.equals("default-apps"))
                return ((IPackageManager) (obj)).getDefaultAppsBackup(0);
            if(s.equals("intent-verification"))
                return ((IPackageManager) (obj)).getIntentFilterVerificationBackup(0);
            obj = JVM INSTR new #56  <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Slog.w("PreferredBackup", ((StringBuilder) (obj)).append("Unexpected backup key ").append(s).toString());
        }
        catch(Exception exception)
        {
            Slog.e("PreferredBackup", (new StringBuilder()).append("Unable to store payload ").append(s).toString());
        }
        return null;
    }

    private static final boolean DEBUG = false;
    private static final String KEY_DEFAULT_APPS = "default-apps";
    private static final String KEY_INTENT_VERIFICATION = "intent-verification";
    private static final String KEY_PREFERRED = "preferred-activity";
    private static final int STATE_VERSION = 3;
    private static final String TAG = "PreferredBackup";
}
