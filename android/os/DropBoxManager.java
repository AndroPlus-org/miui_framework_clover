// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import com.android.internal.os.IDropBoxManagerService;
import java.io.*;
import java.util.zip.GZIPInputStream;

// Referenced classes of package android.os:
//            RemoteException, TransactionTooLargeException, Parcelable, ParcelFileDescriptor, 
//            Parcel

public class DropBoxManager
{
    public static class Entry
        implements Parcelable, Closeable
    {

        public void close()
        {
            if(mFileDescriptor != null)
                mFileDescriptor.close();
_L2:
            return;
            IOException ioexception;
            ioexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public int describeContents()
        {
            int i;
            if(mFileDescriptor != null)
                i = 1;
            else
                i = 0;
            return i;
        }

        public int getFlags()
        {
            return mFlags & -5;
        }

        public InputStream getInputStream()
            throws IOException
        {
            Object obj;
            Object obj1;
            if(mData != null)
                obj = new ByteArrayInputStream(mData);
            else
            if(mFileDescriptor != null)
                obj = new ParcelFileDescriptor.AutoCloseInputStream(mFileDescriptor);
            else
                return null;
            obj1 = obj;
            if((mFlags & 4) != 0)
                obj1 = new GZIPInputStream(((InputStream) (obj)));
            return ((InputStream) (obj1));
        }

        public String getTag()
        {
            return mTag;
        }

        public String getText(int i)
        {
            Object obj;
            Object obj1;
            if((mFlags & 2) == 0)
                return null;
            if(mData != null)
                return new String(mData, 0, Math.min(i, mData.length));
            obj = null;
            obj1 = null;
            Object obj2;
            IOException ioexception1;
            byte abyte0[];
            String s;
            int j;
            int k;
            int l;
            try
            {
                obj2 = getInputStream();
            }
            catch(IOException ioexception2)
            {
                if(obj1 != null)
                    try
                    {
                        ((InputStream) (obj1)).close();
                    }
                    catch(IOException ioexception3) { }
                return null;
            }
            if(obj2 == null)
            {
                if(obj2 != null)
                    try
                    {
                        ((InputStream) (obj2)).close();
                    }
                    // Misplaced declaration of an exception variable
                    catch(Object obj2) { }
                return null;
            }
            obj1 = obj2;
            obj = obj2;
            abyte0 = new byte[i];
            j = 0;
            k = 0;
_L2:
            l = j;
            if(k < 0)
                break; /* Loop/switch isn't completed */
            j += k;
            l = j;
            if(j >= i)
                break; /* Loop/switch isn't completed */
            obj1 = obj2;
            obj = obj2;
            k = ((InputStream) (obj2)).read(abyte0, j, i - j);
            if(true) goto _L2; else goto _L1
_L1:
            obj1 = obj2;
            obj = obj2;
            s = new String(abyte0, 0, l);
            if(obj2 != null)
                try
                {
                    ((InputStream) (obj2)).close();
                }
                // Misplaced declaration of an exception variable
                catch(IOException ioexception1) { }
            return s;
            Exception exception;
            exception;
            if(obj != null)
                try
                {
                    ((InputStream) (obj)).close();
                }
                catch(IOException ioexception) { }
            throw exception;
        }

        public long getTimeMillis()
        {
            return mTimeMillis;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(mTag);
            parcel.writeLong(mTimeMillis);
            if(mFileDescriptor != null)
            {
                parcel.writeInt(mFlags & -9);
                mFileDescriptor.writeToParcel(parcel, i);
            } else
            {
                parcel.writeInt(mFlags | 8);
                parcel.writeByteArray(mData);
            }
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

            public Entry createFromParcel(Parcel parcel)
            {
                String s = parcel.readString();
                long l = parcel.readLong();
                int i = parcel.readInt();
                if((i & 8) != 0)
                    return new Entry(s, l, parcel.createByteArray(), i & -9);
                else
                    return new Entry(s, l, (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel), i);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Entry[] newArray(int i)
            {
                return new Entry[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final byte mData[];
        private final ParcelFileDescriptor mFileDescriptor;
        private final int mFlags;
        private final String mTag;
        private final long mTimeMillis;


        public Entry(String s, long l)
        {
            if(s == null)
            {
                throw new NullPointerException("tag == null");
            } else
            {
                mTag = s;
                mTimeMillis = l;
                mData = null;
                mFileDescriptor = null;
                mFlags = 1;
                return;
            }
        }

        public Entry(String s, long l, ParcelFileDescriptor parcelfiledescriptor, int i)
        {
            boolean flag = true;
            super();
            if(s == null)
                throw new NullPointerException("tag == null");
            boolean flag1;
            if((i & 1) != 0)
                flag1 = true;
            else
                flag1 = false;
            if(parcelfiledescriptor != null)
                flag = false;
            if(flag1 != flag)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Bad flags: ").append(i).toString());
            } else
            {
                mTag = s;
                mTimeMillis = l;
                mData = null;
                mFileDescriptor = parcelfiledescriptor;
                mFlags = i;
                return;
            }
        }

        public Entry(String s, long l, File file, int i)
            throws IOException
        {
            if(s == null)
                throw new NullPointerException("tag == null");
            if((i & 1) != 0)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Bad flags: ").append(i).toString());
            } else
            {
                mTag = s;
                mTimeMillis = l;
                mData = null;
                mFileDescriptor = ParcelFileDescriptor.open(file, 0x10000000);
                mFlags = i;
                return;
            }
        }

        public Entry(String s, long l, String s1)
        {
            if(s == null)
                throw new NullPointerException("tag == null");
            if(s1 == null)
            {
                throw new NullPointerException("text == null");
            } else
            {
                mTag = s;
                mTimeMillis = l;
                mData = s1.getBytes();
                mFileDescriptor = null;
                mFlags = 2;
                return;
            }
        }

        public Entry(String s, long l, byte abyte0[], int i)
        {
            boolean flag = true;
            super();
            if(s == null)
                throw new NullPointerException("tag == null");
            boolean flag1;
            if((i & 1) != 0)
                flag1 = true;
            else
                flag1 = false;
            if(abyte0 != null)
                flag = false;
            if(flag1 != flag)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Bad flags: ").append(i).toString());
            } else
            {
                mTag = s;
                mTimeMillis = l;
                mData = abyte0;
                mFileDescriptor = null;
                mFlags = i;
                return;
            }
        }
    }


    protected DropBoxManager()
    {
        mContext = null;
        mService = null;
    }

    public DropBoxManager(Context context, IDropBoxManagerService idropboxmanagerservice)
    {
        mContext = context;
        mService = idropboxmanagerservice;
    }

    public void addData(String s, byte abyte0[], int i)
    {
        if(abyte0 == null)
            throw new NullPointerException("data == null");
        try
        {
            IDropBoxManagerService idropboxmanagerservice = mService;
            Entry entry = JVM INSTR new #6   <Class DropBoxManager$Entry>;
            entry.Entry(s, 0L, abyte0, i);
            idropboxmanagerservice.add(entry);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        if((s instanceof TransactionTooLargeException) && mContext.getApplicationInfo().targetSdkVersion < 24)
        {
            Log.e("DropBoxManager", "App sent too much data, so it was ignored", s);
            return;
        } else
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void addFile(String s, File file, int i)
        throws IOException
    {
        if(file == null)
            throw new NullPointerException("file == null");
        s = new Entry(s, 0L, file, i);
        mService.add(s);
        s.close();
        return;
        file;
        throw file.rethrowFromSystemServer();
        file;
        s.close();
        throw file;
    }

    public void addText(String s, String s1)
    {
        try
        {
            IDropBoxManagerService idropboxmanagerservice = mService;
            Entry entry = JVM INSTR new #6   <Class DropBoxManager$Entry>;
            entry.Entry(s, 0L, s1);
            idropboxmanagerservice.add(entry);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        if((s instanceof TransactionTooLargeException) && mContext.getApplicationInfo().targetSdkVersion < 24)
        {
            Log.e("DropBoxManager", "App sent too much data, so it was ignored", s);
            return;
        } else
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public Entry getNextEntry(String s, long l)
    {
        try
        {
            s = mService.getNextEntry(s, l);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public boolean isTagEnabled(String s)
    {
        boolean flag;
        try
        {
            flag = mService.isTagEnabled(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public static final String ACTION_DROPBOX_ENTRY_ADDED = "android.intent.action.DROPBOX_ENTRY_ADDED";
    public static final String EXTRA_TAG = "tag";
    public static final String EXTRA_TIME = "time";
    private static final int HAS_BYTE_ARRAY = 8;
    public static final int IS_EMPTY = 1;
    public static final int IS_GZIPPED = 4;
    public static final int IS_TEXT = 2;
    private static final String TAG = "DropBoxManager";
    private final Context mContext;
    private final IDropBoxManagerService mService;
}
