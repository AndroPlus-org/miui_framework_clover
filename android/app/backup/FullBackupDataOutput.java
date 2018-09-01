// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.os.ParcelFileDescriptor;

// Referenced classes of package android.app.backup:
//            BackupDataOutput

public class FullBackupDataOutput
{

    public FullBackupDataOutput(long l)
    {
        mData = null;
        mQuota = l;
        mSize = 0L;
    }

    public FullBackupDataOutput(ParcelFileDescriptor parcelfiledescriptor)
    {
        this(parcelfiledescriptor, -1L);
    }

    public FullBackupDataOutput(ParcelFileDescriptor parcelfiledescriptor, long l)
    {
        mData = new BackupDataOutput(parcelfiledescriptor.getFileDescriptor(), l);
        mQuota = l;
    }

    public void addSize(long l)
    {
        if(l > 0L)
            mSize = mSize + l;
    }

    public BackupDataOutput getData()
    {
        return mData;
    }

    public long getQuota()
    {
        return mQuota;
    }

    public long getSize()
    {
        return mSize;
    }

    private final BackupDataOutput mData;
    private final long mQuota;
    private long mSize;
}
