// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.backup;

import android.app.backup.BlobBackupHelper;
import android.content.pm.IShortcutService;
import android.os.ServiceManager;
import android.util.Slog;

public class ShortcutBackupHelper extends BlobBackupHelper
{

    public ShortcutBackupHelper()
    {
        super(1, new String[] {
            "shortcutuser.xml"
        });
    }

    private IShortcutService getShortcutService()
    {
        return android.content.pm.IShortcutService.Stub.asInterface(ServiceManager.getService("shortcut"));
    }

    protected void applyRestoredPayload(String s, byte abyte0[])
    {
        if(!s.equals("shortcutuser.xml"))
            break MISSING_BLOCK_LABEL_34;
        getShortcutService().applyRestore(abyte0, 0);
_L1:
        return;
        s;
        Slog.wtf("ShortcutBackupAgent", "Restore failed", s);
          goto _L1
        Slog.w("ShortcutBackupAgent", (new StringBuilder()).append("Unknown key: ").append(s).toString());
          goto _L1
    }

    protected byte[] getBackupPayload(String s)
    {
        if(!s.equals("shortcutuser.xml")) goto _L2; else goto _L1
_L1:
        s = getShortcutService().getBackupPayload(0);
        return s;
        s;
        Slog.wtf("ShortcutBackupAgent", "Backup failed", s);
_L4:
        return null;
_L2:
        Slog.w("ShortcutBackupAgent", (new StringBuilder()).append("Unknown key: ").append(s).toString());
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final int BLOB_VERSION = 1;
    private static final String KEY_USER_FILE = "shortcutuser.xml";
    private static final String TAG = "ShortcutBackupAgent";
}
