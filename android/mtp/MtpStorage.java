// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.mtp;

import android.content.Context;
import android.os.storage.StorageVolume;

public class MtpStorage
{

    public MtpStorage(StorageVolume storagevolume, Context context)
    {
        mStorageId = storagevolume.getStorageId();
        mPath = storagevolume.getPath();
        mDescription = storagevolume.getDescription(context);
        mReserveSpace = (long)storagevolume.getMtpReserveSpace() * 1024L * 1024L;
        mRemovable = storagevolume.isRemovable();
        mMaxFileSize = storagevolume.getMaxFileSize();
    }

    public final String getDescription()
    {
        return mDescription;
    }

    public long getMaxFileSize()
    {
        return mMaxFileSize;
    }

    public final String getPath()
    {
        return mPath;
    }

    public final long getReserveSpace()
    {
        return mReserveSpace;
    }

    public final int getStorageId()
    {
        return mStorageId;
    }

    public final boolean isRemovable()
    {
        return mRemovable;
    }

    private final String mDescription;
    private final long mMaxFileSize;
    private final String mPath;
    private final boolean mRemovable;
    private final long mReserveSpace;
    private final int mStorageId;
}
