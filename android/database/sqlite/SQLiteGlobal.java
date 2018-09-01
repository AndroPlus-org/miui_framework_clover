// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database.sqlite;

import android.content.res.Resources;
import android.os.StatFs;
import android.os.SystemProperties;

public final class SQLiteGlobal
{

    private SQLiteGlobal()
    {
    }

    public static String getDefaultJournalMode()
    {
        return SystemProperties.get("debug.sqlite.journalmode", Resources.getSystem().getString(0x1040195));
    }

    public static int getDefaultPageSize()
    {
        Object obj = sLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        if(sDefaultPageSize == 0)
        {
            StatFs statfs = JVM INSTR new #47  <Class StatFs>;
            statfs.StatFs("/data");
            sDefaultPageSize = statfs.getBlockSize();
        }
        i = SystemProperties.getInt("debug.sqlite.pagesize", sDefaultPageSize);
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public static String getDefaultSyncMode()
    {
        return SystemProperties.get("debug.sqlite.syncmode", Resources.getSystem().getString(0x1040196));
    }

    public static int getIdleConnectionTimeout()
    {
        return SystemProperties.getInt("debug.sqlite.idle_connection_timeout", Resources.getSystem().getInteger(0x10e00e9));
    }

    public static int getJournalSizeLimit()
    {
        return SystemProperties.getInt("debug.sqlite.journalsizelimit", Resources.getSystem().getInteger(0x10e00ea));
    }

    public static int getWALAutoCheckpoint()
    {
        return Math.max(1, SystemProperties.getInt("debug.sqlite.wal.autocheckpoint", Resources.getSystem().getInteger(0x10e00eb)));
    }

    public static int getWALConnectionPoolSize()
    {
        return Math.max(2, SystemProperties.getInt("debug.sqlite.wal.poolsize", Resources.getSystem().getInteger(0x10e00e8)));
    }

    public static String getWALSyncMode()
    {
        return SystemProperties.get("debug.sqlite.wal.syncmode", Resources.getSystem().getString(0x1040197));
    }

    private static native int nativeReleaseMemory();

    public static int releaseMemory()
    {
        return nativeReleaseMemory();
    }

    private static final String TAG = "SQLiteGlobal";
    private static int sDefaultPageSize;
    private static final Object sLock = new Object();

}
