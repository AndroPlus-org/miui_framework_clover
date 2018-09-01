// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import java.io.FileDescriptor;
import java.io.IOException;

public class BackupDataOutput
{

    public BackupDataOutput(FileDescriptor filedescriptor)
    {
        this(filedescriptor, -1L);
    }

    public BackupDataOutput(FileDescriptor filedescriptor, long l)
    {
        if(filedescriptor == null)
            throw new NullPointerException();
        mQuota = l;
        mBackupWriter = ctor(filedescriptor);
        if(mBackupWriter == 0L)
            throw new RuntimeException((new StringBuilder()).append("Native initialization failed with fd=").append(filedescriptor).toString());
        else
            return;
    }

    private static native long ctor(FileDescriptor filedescriptor);

    private static native void dtor(long l);

    private static native void setKeyPrefix_native(long l, String s);

    private static native int writeEntityData_native(long l, byte abyte0[], int i);

    private static native int writeEntityHeader_native(long l, String s, int i);

    protected void finalize()
        throws Throwable
    {
        dtor(mBackupWriter);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public long getQuota()
    {
        return mQuota;
    }

    public void setKeyPrefix(String s)
    {
        setKeyPrefix_native(mBackupWriter, s);
    }

    public int writeEntityData(byte abyte0[], int i)
        throws IOException
    {
        i = writeEntityData_native(mBackupWriter, abyte0, i);
        if(i >= 0)
            return i;
        else
            throw new IOException((new StringBuilder()).append("result=0x").append(Integer.toHexString(i)).toString());
    }

    public int writeEntityHeader(String s, int i)
        throws IOException
    {
        i = writeEntityHeader_native(mBackupWriter, s, i);
        if(i >= 0)
            return i;
        else
            throw new IOException((new StringBuilder()).append("result=0x").append(Integer.toHexString(i)).toString());
    }

    long mBackupWriter;
    final long mQuota;
}
