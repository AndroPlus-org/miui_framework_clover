// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.backup;

import android.app.INotificationManager;
import android.app.backup.BlobBackupHelper;
import android.content.Context;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Slog;

public class NotificationBackupHelper extends BlobBackupHelper
{

    public NotificationBackupHelper(Context context)
    {
        super(1, new String[] {
            "notifications"
        });
    }

    protected void applyRestoredPayload(String s, byte abyte0[])
    {
        if(DEBUG)
            Slog.v("NotifBackupHelper", (new StringBuilder()).append("Got restore of ").append(s).toString());
        if(!"notifications".equals(s))
            break MISSING_BLOCK_LABEL_55;
        android.app.INotificationManager.Stub.asInterface(ServiceManager.getService("notification")).applyRestore(abyte0, 0);
_L1:
        return;
        s;
        Slog.e("NotifBackupHelper", "Couldn't communicate with notification manager");
          goto _L1
    }

    protected byte[] getBackupPayload(String s)
    {
        byte abyte0[] = null;
        if("notifications".equals(s))
            try
            {
                abyte0 = android.app.INotificationManager.Stub.asInterface(ServiceManager.getService("notification")).getBackupPayload(0);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Slog.e("NotifBackupHelper", "Couldn't communicate with notification manager");
                abyte0 = null;
            }
        return abyte0;
    }

    static final int BLOB_VERSION = 1;
    static final boolean DEBUG = Log.isLoggable("NotifBackupHelper", 3);
    static final String KEY_NOTIFICATIONS = "notifications";
    static final String TAG = "NotifBackupHelper";

}
