// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.server.backup;

import android.app.backup.BlobBackupHelper;
import android.app.usage.UsageStatsManagerInternal;
import android.content.Context;
import com.android.server.LocalServices;
import java.io.*;

public class UsageStatsBackupHelper extends BlobBackupHelper
{

    public UsageStatsBackupHelper(Context context)
    {
        super(1, new String[] {
            "usage_stats"
        });
    }

    protected void applyRestoredPayload(String s, byte abyte0[])
    {
        UsageStatsManagerInternal usagestatsmanagerinternal;
        DataInputStream datainputstream;
        if(!"usage_stats".equals(s))
            break MISSING_BLOCK_LABEL_67;
        usagestatsmanagerinternal = (UsageStatsManagerInternal)LocalServices.getService(android/app/usage/UsageStatsManagerInternal);
        datainputstream = new DataInputStream(new ByteArrayInputStream(abyte0));
        int i = datainputstream.readInt();
        abyte0 = new byte[abyte0.length - 4];
        datainputstream.read(abyte0, 0, abyte0.length);
        usagestatsmanagerinternal.applyRestoredPayload(i, s, abyte0);
_L2:
        return;
        s;
        if(true) goto _L2; else goto _L1
_L1:
    }

    protected byte[] getBackupPayload(String s)
    {
        if("usage_stats".equals(s))
        {
            UsageStatsManagerInternal usagestatsmanagerinternal = (UsageStatsManagerInternal)LocalServices.getService(android/app/usage/UsageStatsManagerInternal);
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);
            try
            {
                dataoutputstream.writeInt(0);
                dataoutputstream.write(usagestatsmanagerinternal.getBackupPayload(0, s));
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                bytearrayoutputstream.reset();
            }
            return bytearrayoutputstream.toByteArray();
        } else
        {
            return null;
        }
    }

    static final int BLOB_VERSION = 1;
    static final boolean DEBUG = false;
    static final String KEY_USAGE_STATS = "usage_stats";
    static final String TAG = "UsgStatsBackupHelper";
}
