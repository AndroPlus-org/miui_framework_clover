// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.app.backup;

import android.app.backup.FullBackup;
import android.app.backup.FullBackupDataOutput;

public class FullBackupProxy
{

    public FullBackupProxy()
    {
    }

    public static int backupToTar(String s, String s1, String s2, String s3, String s4, FullBackupDataOutput fullbackupdataoutput)
    {
        return FullBackup.backupToTar(s, s1, s2, s3, s4, fullbackupdataoutput);
    }
}
