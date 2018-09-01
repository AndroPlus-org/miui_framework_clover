// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.os.*;
import java.io.*;

public class AssetFileDescriptor
    implements Parcelable, Closeable
{
    public static class AutoCloseInputStream extends android.os.ParcelFileDescriptor.AutoCloseInputStream
    {

        public int available()
            throws IOException
        {
            int i;
            if(mRemaining >= 0L)
            {
                if(mRemaining < 0x7fffffffL)
                    i = (int)mRemaining;
                else
                    i = 0x7fffffff;
            } else
            {
                i = super.available();
            }
            return i;
        }

        public void mark(int i)
        {
            if(mRemaining >= 0L)
            {
                return;
            } else
            {
                super.mark(i);
                return;
            }
        }

        public boolean markSupported()
        {
            if(mRemaining >= 0L)
                return false;
            else
                return super.markSupported();
        }

        public int read()
            throws IOException
        {
            int i = -1;
            byte abyte0[] = new byte[1];
            if(read(abyte0, 0, 1) != -1)
                i = abyte0[0] & 0xff;
            return i;
        }

        public int read(byte abyte0[])
            throws IOException
        {
            return read(abyte0, 0, abyte0.length);
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            if(mRemaining >= 0L)
            {
                if(mRemaining == 0L)
                    return -1;
                int k = j;
                if((long)j > mRemaining)
                    k = (int)mRemaining;
                i = super.read(abyte0, i, k);
                if(i >= 0)
                    mRemaining = mRemaining - (long)i;
                return i;
            } else
            {
                return super.read(abyte0, i, j);
            }
        }

        public void reset()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            long l = mRemaining;
            if(l < 0L)
                break MISSING_BLOCK_LABEL_16;
            this;
            JVM INSTR monitorexit ;
            return;
            super.reset();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public long skip(long l)
            throws IOException
        {
            if(mRemaining >= 0L)
            {
                if(mRemaining == 0L)
                    return -1L;
                long l1 = l;
                if(l > mRemaining)
                    l1 = mRemaining;
                l = super.skip(l1);
                if(l >= 0L)
                    mRemaining = mRemaining - l;
                return l;
            } else
            {
                return super.skip(l);
            }
        }

        private long mRemaining;

        public AutoCloseInputStream(AssetFileDescriptor assetfiledescriptor)
            throws IOException
        {
            super(assetfiledescriptor.getParcelFileDescriptor());
            super.skip(assetfiledescriptor.getStartOffset());
            mRemaining = (int)assetfiledescriptor.getLength();
        }
    }

    public static class AutoCloseOutputStream extends android.os.ParcelFileDescriptor.AutoCloseOutputStream
    {

        public void write(int i)
            throws IOException
        {
            if(mRemaining >= 0L)
            {
                if(mRemaining == 0L)
                {
                    return;
                } else
                {
                    super.write(i);
                    mRemaining = mRemaining - 1L;
                    return;
                }
            } else
            {
                super.write(i);
                return;
            }
        }

        public void write(byte abyte0[])
            throws IOException
        {
            if(mRemaining >= 0L)
            {
                if(mRemaining == 0L)
                    return;
                int i = abyte0.length;
                int j = i;
                if((long)i > mRemaining)
                    j = (int)mRemaining;
                super.write(abyte0);
                mRemaining = mRemaining - (long)j;
                return;
            } else
            {
                super.write(abyte0);
                return;
            }
        }

        public void write(byte abyte0[], int i, int j)
            throws IOException
        {
            if(mRemaining >= 0L)
            {
                if(mRemaining == 0L)
                    return;
                int k = j;
                if((long)j > mRemaining)
                    k = (int)mRemaining;
                super.write(abyte0, i, k);
                mRemaining = mRemaining - (long)k;
                return;
            } else
            {
                super.write(abyte0, i, j);
                return;
            }
        }

        private long mRemaining;

        public AutoCloseOutputStream(AssetFileDescriptor assetfiledescriptor)
            throws IOException
        {
            super(assetfiledescriptor.getParcelFileDescriptor());
            if(assetfiledescriptor.getParcelFileDescriptor().seekTo(assetfiledescriptor.getStartOffset()) < 0L)
            {
                throw new IOException("Unable to seek");
            } else
            {
                mRemaining = (int)assetfiledescriptor.getLength();
                return;
            }
        }
    }


    AssetFileDescriptor(Parcel parcel)
    {
        mFd = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
        mStartOffset = parcel.readLong();
        mLength = parcel.readLong();
        if(parcel.readInt() != 0)
            mExtras = parcel.readBundle();
        else
            mExtras = null;
    }

    public AssetFileDescriptor(ParcelFileDescriptor parcelfiledescriptor, long l, long l1)
    {
        this(parcelfiledescriptor, l, l1, null);
    }

    public AssetFileDescriptor(ParcelFileDescriptor parcelfiledescriptor, long l, long l1, Bundle bundle)
    {
        if(parcelfiledescriptor == null)
            throw new IllegalArgumentException("fd must not be null");
        if(l1 < 0L && l != 0L)
        {
            throw new IllegalArgumentException("startOffset must be 0 when using UNKNOWN_LENGTH");
        } else
        {
            mFd = parcelfiledescriptor;
            mStartOffset = l;
            mLength = l1;
            mExtras = bundle;
            return;
        }
    }

    public void close()
        throws IOException
    {
        mFd.close();
    }

    public FileInputStream createInputStream()
        throws IOException
    {
        if(mLength < 0L)
            return new android.os.ParcelFileDescriptor.AutoCloseInputStream(mFd);
        else
            return new AutoCloseInputStream(this);
    }

    public FileOutputStream createOutputStream()
        throws IOException
    {
        if(mLength < 0L)
            return new android.os.ParcelFileDescriptor.AutoCloseOutputStream(mFd);
        else
            return new AutoCloseOutputStream(this);
    }

    public int describeContents()
    {
        return mFd.describeContents();
    }

    public long getDeclaredLength()
    {
        return mLength;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public FileDescriptor getFileDescriptor()
    {
        return mFd.getFileDescriptor();
    }

    public long getLength()
    {
        if(mLength >= 0L)
            return mLength;
        long l = mFd.getStatSize();
        if(l < 0L)
            l = -1L;
        return l;
    }

    public ParcelFileDescriptor getParcelFileDescriptor()
    {
        return mFd;
    }

    public long getStartOffset()
    {
        return mStartOffset;
    }

    public String toString()
    {
        return (new StringBuilder()).append("{AssetFileDescriptor: ").append(mFd).append(" start=").append(mStartOffset).append(" len=").append(mLength).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        mFd.writeToParcel(parcel, i);
        parcel.writeLong(mStartOffset);
        parcel.writeLong(mLength);
        if(mExtras != null)
        {
            parcel.writeInt(1);
            parcel.writeBundle(mExtras);
        } else
        {
            parcel.writeInt(0);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AssetFileDescriptor createFromParcel(Parcel parcel)
        {
            return new AssetFileDescriptor(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AssetFileDescriptor[] newArray(int i)
        {
            return new AssetFileDescriptor[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final long UNKNOWN_LENGTH = -1L;
    private final Bundle mExtras;
    private final ParcelFileDescriptor mFd;
    private final long mLength;
    private final long mStartOffset;

}
