// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import java.io.FileDescriptor;
import java.io.IOException;

public class BackupDataInput
{
    private static class EntityHeader
    {

        int dataSize;
        String key;

        private EntityHeader()
        {
        }

        EntityHeader(EntityHeader entityheader)
        {
            this();
        }
    }


    public BackupDataInput(FileDescriptor filedescriptor)
    {
        mHeader = new EntityHeader(null);
        if(filedescriptor == null)
            throw new NullPointerException();
        mBackupReader = ctor(filedescriptor);
        if(mBackupReader == 0L)
            throw new RuntimeException((new StringBuilder()).append("Native initialization failed with fd=").append(filedescriptor).toString());
        else
            return;
    }

    private static native long ctor(FileDescriptor filedescriptor);

    private static native void dtor(long l);

    private native int readEntityData_native(long l, byte abyte0[], int i, int j);

    private native int readNextHeader_native(long l, EntityHeader entityheader);

    private native int skipEntityData_native(long l);

    protected void finalize()
        throws Throwable
    {
        dtor(mBackupReader);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int getDataSize()
    {
        if(mHeaderReady)
            return mHeader.dataSize;
        else
            throw new IllegalStateException("Entity header not read");
    }

    public String getKey()
    {
        if(mHeaderReady)
            return mHeader.key;
        else
            throw new IllegalStateException("Entity header not read");
    }

    public int readEntityData(byte abyte0[], int i, int j)
        throws IOException
    {
        if(mHeaderReady)
        {
            i = readEntityData_native(mBackupReader, abyte0, i, j);
            if(i >= 0)
                return i;
            else
                throw new IOException((new StringBuilder()).append("result=0x").append(Integer.toHexString(i)).toString());
        } else
        {
            throw new IllegalStateException("Entity header not read");
        }
    }

    public boolean readNextHeader()
        throws IOException
    {
        int i = readNextHeader_native(mBackupReader, mHeader);
        if(i == 0)
        {
            mHeaderReady = true;
            return true;
        }
        if(i > 0)
        {
            mHeaderReady = false;
            return false;
        } else
        {
            mHeaderReady = false;
            throw new IOException((new StringBuilder()).append("failed: 0x").append(Integer.toHexString(i)).toString());
        }
    }

    public void skipEntityData()
        throws IOException
    {
        if(mHeaderReady)
        {
            skipEntityData_native(mBackupReader);
            return;
        } else
        {
            throw new IllegalStateException("Entity header not read");
        }
    }

    long mBackupReader;
    private EntityHeader mHeader;
    private boolean mHeaderReady;
}
