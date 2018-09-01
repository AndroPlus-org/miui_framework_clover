// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.os.*;
import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.io.IOException;
import java.util.UUID;
import libcore.io.IoUtils;

public final class MemoryIntArray
    implements Parcelable, Closeable
{

    public MemoryIntArray(int i)
        throws IOException
    {
        mCloseGuard = CloseGuard.get();
        mFd = -1;
        if(i > 1024)
        {
            throw new IllegalArgumentException("Max size is 1024");
        } else
        {
            mIsOwner = true;
            mFd = nativeCreate(UUID.randomUUID().toString(), i);
            mMemoryAddr = nativeOpen(mFd, mIsOwner);
            mCloseGuard.open("close");
            return;
        }
    }

    private MemoryIntArray(Parcel parcel)
        throws IOException
    {
        mCloseGuard = CloseGuard.get();
        mFd = -1;
        mIsOwner = false;
        parcel = (ParcelFileDescriptor)parcel.readParcelable(null);
        if(parcel == null)
        {
            throw new IOException("No backing file descriptor");
        } else
        {
            mFd = parcel.detachFd();
            mMemoryAddr = nativeOpen(mFd, mIsOwner);
            mCloseGuard.open("close");
            return;
        }
    }

    MemoryIntArray(Parcel parcel, MemoryIntArray memoryintarray)
    {
        this(parcel);
    }

    private void enforceNotClosed()
    {
        if(isClosed())
            throw new IllegalStateException("cannot interact with a closed instance");
        else
            return;
    }

    private void enforceValidIndex(int i)
        throws IOException
    {
        int j = size();
        if(i < 0 || i > j - 1)
            throw new IndexOutOfBoundsException((new StringBuilder()).append(i).append(" not between 0 and ").append(j - 1).toString());
        else
            return;
    }

    private void enforceWritable()
    {
        if(!isWritable())
            throw new UnsupportedOperationException("array is not writable");
        else
            return;
    }

    public static int getMaxSize()
    {
        return 1024;
    }

    private native void nativeClose(int i, long l, boolean flag);

    private native int nativeCreate(String s, int i);

    private native int nativeGet(int i, long l, int j);

    private native long nativeOpen(int i, boolean flag);

    private native void nativeSet(int i, long l, int j, int k);

    private native int nativeSize(int i);

    public void close()
        throws IOException
    {
        if(!isClosed())
        {
            nativeClose(mFd, mMemoryAddr, mIsOwner);
            mFd = -1;
            mCloseGuard.close();
        }
    }

    public int describeContents()
    {
        return 1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(getClass() != obj.getClass())
            return false;
        obj = (MemoryIntArray)obj;
        if(mFd != ((MemoryIntArray) (obj)).mFd)
            flag = false;
        return flag;
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        IoUtils.closeQuietly(this);
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public int get(int i)
        throws IOException
    {
        enforceNotClosed();
        enforceValidIndex(i);
        return nativeGet(mFd, mMemoryAddr, i);
    }

    public int hashCode()
    {
        return mFd;
    }

    public boolean isClosed()
    {
        boolean flag;
        if(mFd == -1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isWritable()
    {
        enforceNotClosed();
        return mIsOwner;
    }

    public void set(int i, int j)
        throws IOException
    {
        enforceNotClosed();
        enforceWritable();
        enforceValidIndex(i);
        nativeSet(mFd, mMemoryAddr, i, j);
    }

    public int size()
        throws IOException
    {
        enforceNotClosed();
        return nativeSize(mFd);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        ParcelFileDescriptor parcelfiledescriptor = ParcelFileDescriptor.adoptFd(mFd);
        parcel.writeParcelable(parcelfiledescriptor, i & -2);
        parcelfiledescriptor.detachFd();
        return;
        parcel;
        parcelfiledescriptor.detachFd();
        throw parcel;
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MemoryIntArray createFromParcel(Parcel parcel)
        {
            try
            {
                parcel = new MemoryIntArray(parcel, null);
            }
            // Misplaced declaration of an exception variable
            catch(Parcel parcel)
            {
                throw new IllegalArgumentException("Error unparceling MemoryIntArray");
            }
            return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MemoryIntArray[] newArray(int i)
        {
            return new MemoryIntArray[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int MAX_SIZE = 1024;
    private static final String TAG = "MemoryIntArray";
    private final CloseGuard mCloseGuard;
    private int mFd;
    private final boolean mIsOwner;
    private final long mMemoryAddr;

}
