// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.text.TextUtils;
import android.util.*;
import dalvik.system.VMRuntime;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;
import libcore.util.SneakyThrow;

// Referenced classes of package android.os:
//            IBinder, Bundle, Parcelable, BadParcelableException, 
//            NetworkOnMainThreadException, ServiceSpecificException, StrictMode, ParcelFileDescriptor, 
//            PersistableBundle, IInterface

public final class Parcel
{
    public static class ReadWriteHelper
    {

        public String readString(Parcel parcel)
        {
            return Parcel.nativeReadString(Parcel._2D_get0(parcel));
        }

        public void writeString(Parcel parcel, String s)
        {
            Parcel.nativeWriteString(Parcel._2D_get0(parcel), s);
        }

        public static final ReadWriteHelper DEFAULT = new ReadWriteHelper();


        public ReadWriteHelper()
        {
        }
    }


    static long _2D_get0(Parcel parcel)
    {
        return parcel.mNativePtr;
    }

    private Parcel(long l)
    {
        mReadWriteHelper = ReadWriteHelper.DEFAULT;
        init(l);
    }

    static native void clearFileDescriptor(FileDescriptor filedescriptor);

    static native void closeFileDescriptor(FileDescriptor filedescriptor)
        throws IOException;

    private void destroy()
    {
        if(mNativePtr != 0L)
        {
            if(mOwnsNativeParcelObject)
            {
                nativeDestroy(mNativePtr);
                updateNativeSize(0L);
            }
            mNativePtr = 0L;
        }
        mReadWriteHelper = null;
    }

    static native FileDescriptor dupFileDescriptor(FileDescriptor filedescriptor)
        throws IOException;

    private void freeBuffer()
    {
        if(mOwnsNativeParcelObject)
            updateNativeSize(nativeFreeBuffer(mNativePtr));
        mReadWriteHelper = ReadWriteHelper.DEFAULT;
    }

    public static native long getGlobalAllocCount();

    public static native long getGlobalAllocSize();

    private void init(long l)
    {
        if(l != 0L)
        {
            mNativePtr = l;
            mOwnsNativeParcelObject = false;
        } else
        {
            mNativePtr = nativeCreate();
            mOwnsNativeParcelObject = true;
        }
    }

    private static native long nativeAppendFrom(long l, long l1, int i, int j);

    private static native int nativeCompareData(long l, long l1);

    private static native long nativeCreate();

    private static native byte[] nativeCreateByteArray(long l);

    private static native int nativeDataAvail(long l);

    private static native int nativeDataCapacity(long l);

    private static native int nativeDataPosition(long l);

    private static native int nativeDataSize(long l);

    private static native void nativeDestroy(long l);

    private static native void nativeEnforceInterface(long l, String s);

    private static native long nativeFreeBuffer(long l);

    private static native long nativeGetBlobAshmemSize(long l);

    private static native boolean nativeHasFileDescriptors(long l);

    private static native byte[] nativeMarshall(long l);

    private static native boolean nativePushAllowFds(long l, boolean flag);

    private static native byte[] nativeReadBlob(long l);

    private static native boolean nativeReadByteArray(long l, byte abyte0[], int i);

    private static native double nativeReadDouble(long l);

    private static native FileDescriptor nativeReadFileDescriptor(long l);

    private static native float nativeReadFloat(long l);

    private static native int nativeReadInt(long l);

    private static native long nativeReadLong(long l);

    static native String nativeReadString(long l);

    private static native IBinder nativeReadStrongBinder(long l);

    private static native void nativeRestoreAllowFds(long l, boolean flag);

    private static native void nativeSetDataCapacity(long l, int i);

    private static native void nativeSetDataPosition(long l, int i);

    private static native long nativeSetDataSize(long l, int i);

    private static native long nativeUnmarshall(long l, byte abyte0[], int i, int j);

    private static native void nativeWriteBlob(long l, byte abyte0[], int i, int j);

    private static native void nativeWriteByteArray(long l, byte abyte0[], int i, int j);

    private static native void nativeWriteDouble(long l, double d);

    private static native long nativeWriteFileDescriptor(long l, FileDescriptor filedescriptor);

    private static native void nativeWriteFloat(long l, float f);

    private static native void nativeWriteInt(long l, int i);

    private static native void nativeWriteInterfaceToken(long l, String s);

    private static native void nativeWriteLong(long l, long l1);

    static native void nativeWriteString(long l, String s);

    private static native void nativeWriteStrongBinder(long l, IBinder ibinder);

    public static Parcel obtain()
    {
        Parcel aparcel[] = sOwnedPool;
        aparcel;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        Parcel parcel;
        if(i >= 6)
            break; /* Loop/switch isn't completed */
        parcel = aparcel[i];
        if(parcel == null)
            break MISSING_BLOCK_LABEL_37;
        aparcel[i] = null;
        parcel.mReadWriteHelper = ReadWriteHelper.DEFAULT;
        aparcel;
        JVM INSTR monitorexit ;
        return parcel;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return new Parcel(0L);
        Exception exception;
        exception;
        throw exception;
    }

    protected static final Parcel obtain(int i)
    {
        throw new UnsupportedOperationException();
    }

    protected static final Parcel obtain(long l)
    {
        Parcel aparcel[] = sHolderPool;
        aparcel;
        JVM INSTR monitorenter ;
        int i = 0;
_L2:
        Parcel parcel;
        if(i >= 6)
            break; /* Loop/switch isn't completed */
        parcel = aparcel[i];
        if(parcel == null)
            break MISSING_BLOCK_LABEL_39;
        aparcel[i] = null;
        parcel.init(l);
        aparcel;
        JVM INSTR monitorexit ;
        return parcel;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return new Parcel(l);
        Exception exception;
        exception;
        throw exception;
    }

    static native FileDescriptor openFileDescriptor(String s, int i)
        throws FileNotFoundException;

    private void readArrayInternal(Object aobj[], int i, ClassLoader classloader)
    {
        for(int j = 0; j < i; j++)
            aobj[j] = readValue(classloader);

    }

    private void readListInternal(List list, int i, ClassLoader classloader)
    {
        for(; i > 0; i--)
            list.add(readValue(classloader));

    }

    private final Serializable readSerializable(ClassLoader classloader)
    {
        String s = readString();
        if(s == null)
            return null;
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(createByteArray());
        try
        {
            ObjectInputStream objectinputstream = JVM INSTR new #8   <Class Parcel$2>;
            objectinputstream.this. _cls2(classloader);
            classloader = (Serializable)objectinputstream.readObject();
        }
        // Misplaced declaration of an exception variable
        catch(ClassLoader classloader)
        {
            throw new RuntimeException((new StringBuilder()).append("Parcelable encountered IOException reading a Serializable object (name = ").append(s).append(")").toString(), classloader);
        }
        // Misplaced declaration of an exception variable
        catch(ClassLoader classloader)
        {
            throw new RuntimeException((new StringBuilder()).append("Parcelable encountered ClassNotFoundException reading a Serializable object (name = ").append(s).append(")").toString(), classloader);
        }
        return classloader;
    }

    private void readSparseArrayInternal(SparseArray sparsearray, int i, ClassLoader classloader)
    {
        for(; i > 0; i--)
            sparsearray.append(readInt(), readValue(classloader));

    }

    private void readSparseBooleanArrayInternal(SparseBooleanArray sparsebooleanarray, int i)
    {
        while(i > 0) 
        {
            int j = readInt();
            boolean flag;
            if(readByte() == 1)
                flag = true;
            else
                flag = false;
            sparsebooleanarray.append(j, flag);
            i--;
        }
    }

    private void readSparseIntArrayInternal(SparseIntArray sparseintarray, int i)
    {
        for(; i > 0; i--)
            sparseintarray.append(readInt(), readInt());

    }

    private void updateNativeSize(long l)
    {
        if(mOwnsNativeParcelObject)
        {
            long l1 = l;
            if(l > 0x7fffffffL)
                l1 = 0x7fffffffL;
            if(l1 != mNativeSize)
            {
                int i = (int)(l1 - mNativeSize);
                if(i > 0)
                    VMRuntime.getRuntime().registerNativeAllocation(i);
                else
                    VMRuntime.getRuntime().registerNativeFree(-i);
                mNativeSize = l1;
            }
        }
    }

    public final void adoptClassCookies(Parcel parcel)
    {
        mClassCookies = parcel.mClassCookies;
    }

    public final void appendFrom(Parcel parcel, int i, int j)
    {
        updateNativeSize(nativeAppendFrom(mNativePtr, parcel.mNativePtr, i, j));
    }

    public final int compareData(Parcel parcel)
    {
        return nativeCompareData(mNativePtr, parcel.mNativePtr);
    }

    public final IBinder[] createBinderArray()
    {
        int i = readInt();
        if(i >= 0)
        {
            IBinder aibinder[] = new IBinder[i];
            for(int j = 0; j < i; j++)
                aibinder[j] = readStrongBinder();

            return aibinder;
        } else
        {
            return null;
        }
    }

    public final ArrayList createBinderArrayList()
    {
        int i = readInt();
        if(i < 0)
            return null;
        ArrayList arraylist = new ArrayList(i);
        for(; i > 0; i--)
            arraylist.add(readStrongBinder());

        return arraylist;
    }

    public final boolean[] createBooleanArray()
    {
        int i = readInt();
        if(i >= 0 && i <= dataAvail() >> 2)
        {
            boolean aflag[] = new boolean[i];
            int j = 0;
            while(j < i) 
            {
                boolean flag;
                if(readInt() != 0)
                    flag = true;
                else
                    flag = false;
                aflag[j] = flag;
                j++;
            }
            return aflag;
        } else
        {
            return null;
        }
    }

    public final byte[] createByteArray()
    {
        return nativeCreateByteArray(mNativePtr);
    }

    public final char[] createCharArray()
    {
        int i = readInt();
        if(i >= 0 && i <= dataAvail() >> 2)
        {
            char ac[] = new char[i];
            for(int j = 0; j < i; j++)
                ac[j] = (char)readInt();

            return ac;
        } else
        {
            return null;
        }
    }

    public final double[] createDoubleArray()
    {
        int i = readInt();
        if(i >= 0 && i <= dataAvail() >> 3)
        {
            double ad[] = new double[i];
            for(int j = 0; j < i; j++)
                ad[j] = readDouble();

            return ad;
        } else
        {
            return null;
        }
    }

    public final float[] createFloatArray()
    {
        int i = readInt();
        if(i >= 0 && i <= dataAvail() >> 2)
        {
            float af[] = new float[i];
            for(int j = 0; j < i; j++)
                af[j] = readFloat();

            return af;
        } else
        {
            return null;
        }
    }

    public final int[] createIntArray()
    {
        int i = readInt();
        if(i >= 0 && i <= dataAvail() >> 2)
        {
            int ai[] = new int[i];
            for(int j = 0; j < i; j++)
                ai[j] = readInt();

            return ai;
        } else
        {
            return null;
        }
    }

    public final long[] createLongArray()
    {
        int i = readInt();
        if(i >= 0 && i <= dataAvail() >> 3)
        {
            long al[] = new long[i];
            for(int j = 0; j < i; j++)
                al[j] = readLong();

            return al;
        } else
        {
            return null;
        }
    }

    public final FileDescriptor[] createRawFileDescriptorArray()
    {
        int i = readInt();
        if(i < 0)
            return null;
        FileDescriptor afiledescriptor[] = new FileDescriptor[i];
        for(int j = 0; j < i; j++)
            afiledescriptor[j] = readRawFileDescriptor();

        return afiledescriptor;
    }

    public final String[] createStringArray()
    {
        int i = readInt();
        if(i >= 0)
        {
            String as[] = new String[i];
            for(int j = 0; j < i; j++)
                as[j] = readString();

            return as;
        } else
        {
            return null;
        }
    }

    public final ArrayList createStringArrayList()
    {
        int i = readInt();
        if(i < 0)
            return null;
        ArrayList arraylist = new ArrayList(i);
        for(; i > 0; i--)
            arraylist.add(readString());

        return arraylist;
    }

    public final Object[] createTypedArray(Parcelable.Creator creator)
    {
        int i = readInt();
        if(i < 0)
            return null;
        Object aobj[] = creator.newArray(i);
        for(int j = 0; j < i; j++)
            if(readInt() != 0)
                aobj[j] = creator.createFromParcel(this);

        return aobj;
    }

    public final ArrayList createTypedArrayList(Parcelable.Creator creator)
    {
        int i = readInt();
        if(i < 0)
            return null;
        ArrayList arraylist = new ArrayList(i);
        while(i > 0) 
        {
            if(readInt() != 0)
                arraylist.add(creator.createFromParcel(this));
            else
                arraylist.add(null);
            i--;
        }
        return arraylist;
    }

    public final int dataAvail()
    {
        return nativeDataAvail(mNativePtr);
    }

    public final int dataCapacity()
    {
        return nativeDataCapacity(mNativePtr);
    }

    public final int dataPosition()
    {
        return nativeDataPosition(mNativePtr);
    }

    public final int dataSize()
    {
        return nativeDataSize(mNativePtr);
    }

    public final void enforceInterface(String s)
    {
        nativeEnforceInterface(mNativePtr, s);
    }

    protected void finalize()
        throws Throwable
    {
        destroy();
    }

    public long getBlobAshmemSize()
    {
        return nativeGetBlobAshmemSize(mNativePtr);
    }

    public final Object getClassCookie(Class class1)
    {
        Object obj = null;
        if(mClassCookies != null)
            obj = mClassCookies.get(class1);
        return obj;
    }

    public final boolean hasFileDescriptors()
    {
        return nativeHasFileDescriptors(mNativePtr);
    }

    public boolean hasReadWriteHelper()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mReadWriteHelper != null)
        {
            flag1 = flag;
            if(mReadWriteHelper != ReadWriteHelper.DEFAULT)
                flag1 = true;
        }
        return flag1;
    }

    public final byte[] marshall()
    {
        return nativeMarshall(mNativePtr);
    }

    public final boolean pushAllowFds(boolean flag)
    {
        return nativePushAllowFds(mNativePtr, flag);
    }

    public final Object[] readArray(ClassLoader classloader)
    {
        int i = readInt();
        if(i < 0)
        {
            return null;
        } else
        {
            Object aobj[] = new Object[i];
            readArrayInternal(aobj, i, classloader);
            return aobj;
        }
    }

    public final ArrayList readArrayList(ClassLoader classloader)
    {
        int i = readInt();
        if(i < 0)
        {
            return null;
        } else
        {
            ArrayList arraylist = new ArrayList(i);
            readListInternal(arraylist, i, classloader);
            return arraylist;
        }
    }

    public void readArrayMap(ArrayMap arraymap, ClassLoader classloader)
    {
        int i = readInt();
        if(i < 0)
        {
            return;
        } else
        {
            readArrayMapInternal(arraymap, i, classloader);
            return;
        }
    }

    void readArrayMapInternal(ArrayMap arraymap, int i, ClassLoader classloader)
    {
        for(; i > 0; i--)
            arraymap.append(readString(), readValue(classloader));

        arraymap.validate();
    }

    void readArrayMapSafelyInternal(ArrayMap arraymap, int i, ClassLoader classloader)
    {
        for(; i > 0; i--)
            arraymap.put(readString(), readValue(classloader));

    }

    public ArraySet readArraySet(ClassLoader classloader)
    {
        int i = readInt();
        if(i < 0)
            return null;
        ArraySet arrayset = new ArraySet(i);
        for(int j = 0; j < i; j++)
            arrayset.append(readValue(classloader));

        return arrayset;
    }

    public final void readBinderArray(IBinder aibinder[])
    {
        int i = readInt();
        if(i == aibinder.length)
        {
            for(int j = 0; j < i; j++)
                aibinder[j] = readStrongBinder();

        } else
        {
            throw new RuntimeException("bad array lengths");
        }
    }

    public final void readBinderList(List list)
    {
        int i = list.size();
        int j = readInt();
        int k = 0;
        int l;
        do
        {
            l = k;
            if(k >= i)
                break;
            l = k;
            if(k >= j)
                break;
            list.set(k, readStrongBinder());
            k++;
        } while(true);
        do
        {
            k = l;
            if(l >= j)
                break;
            list.add(readStrongBinder());
            l++;
        } while(true);
        for(; k < i; k++)
            list.remove(j);

    }

    public final byte[] readBlob()
    {
        return nativeReadBlob(mNativePtr);
    }

    public final boolean readBoolean()
    {
        boolean flag = false;
        if(readInt() != 0)
            flag = true;
        return flag;
    }

    public final void readBooleanArray(boolean aflag[])
    {
        int i = readInt();
        if(i == aflag.length)
        {
            int j = 0;
            while(j < i) 
            {
                boolean flag;
                if(readInt() != 0)
                    flag = true;
                else
                    flag = false;
                aflag[j] = flag;
                j++;
            }
        } else
        {
            throw new RuntimeException("bad array lengths");
        }
    }

    public final Bundle readBundle()
    {
        return readBundle(null);
    }

    public final Bundle readBundle(ClassLoader classloader)
    {
        int i = readInt();
        if(i < 0)
            return null;
        Bundle bundle = new Bundle(this, i);
        if(classloader != null)
            bundle.setClassLoader(classloader);
        return bundle;
    }

    public final byte readByte()
    {
        return (byte)(readInt() & 0xff);
    }

    public final void readByteArray(byte abyte0[])
    {
        long l = mNativePtr;
        int i;
        if(abyte0 != null)
            i = abyte0.length;
        else
            i = 0;
        if(!nativeReadByteArray(l, abyte0, i))
            throw new RuntimeException("bad array lengths");
        else
            return;
    }

    public final void readCharArray(char ac[])
    {
        int i = readInt();
        if(i == ac.length)
        {
            for(int j = 0; j < i; j++)
                ac[j] = (char)readInt();

        } else
        {
            throw new RuntimeException("bad array lengths");
        }
    }

    public final CharSequence readCharSequence()
    {
        return (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(this);
    }

    public final CharSequence[] readCharSequenceArray()
    {
        CharSequence acharsequence[] = null;
        int i = readInt();
        if(i >= 0)
        {
            CharSequence acharsequence1[] = new CharSequence[i];
            int j = 0;
            do
            {
                acharsequence = acharsequence1;
                if(j >= i)
                    break;
                acharsequence1[j] = readCharSequence();
                j++;
            } while(true);
        }
        return acharsequence;
    }

    public final ArrayList readCharSequenceList()
    {
        ArrayList arraylist = null;
        int i = readInt();
        if(i >= 0)
        {
            ArrayList arraylist1 = new ArrayList(i);
            int j = 0;
            do
            {
                arraylist = arraylist1;
                if(j >= i)
                    break;
                arraylist1.add(readCharSequence());
                j++;
            } while(true);
        }
        return arraylist;
    }

    public final Parcelable readCreator(Parcelable.Creator creator, ClassLoader classloader)
    {
        if(creator instanceof Parcelable.ClassLoaderCreator)
            return (Parcelable)((Parcelable.ClassLoaderCreator)creator).createFromParcel(this, classloader);
        else
            return (Parcelable)creator.createFromParcel(this);
    }

    public final double readDouble()
    {
        return nativeReadDouble(mNativePtr);
    }

    public final void readDoubleArray(double ad[])
    {
        int i = readInt();
        if(i == ad.length)
        {
            for(int j = 0; j < i; j++)
                ad[j] = readDouble();

        } else
        {
            throw new RuntimeException("bad array lengths");
        }
    }

    public final void readException()
    {
        int i = readExceptionCode();
        if(i != 0)
            readException(i, readString());
    }

    public final void readException(int i, String s)
    {
        i;
        JVM INSTR tableswitch -9 -1: default 52
    //                   -9 90
    //                   -8 203
    //                   -7 194
    //                   -6 186
    //                   -5 177
    //                   -4 168
    //                   -3 159
    //                   -2 150
    //                   -1 113;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
        throw new RuntimeException((new StringBuilder()).append("Unknown exception code: ").append(i).append(" msg ").append(s).toString());
_L2:
        if(readInt() <= 0) goto _L12; else goto _L11
_L11:
        SneakyThrow.sneakyThrow((Exception)readParcelable(android/os/Parcelable.getClassLoader()));
_L10:
        throw new SecurityException(s);
_L12:
        throw new RuntimeException((new StringBuilder()).append(s).append(" [missing Parcelable]").toString());
_L9:
        throw new BadParcelableException(s);
_L8:
        throw new IllegalArgumentException(s);
_L7:
        throw new NullPointerException(s);
_L6:
        throw new IllegalStateException(s);
_L5:
        throw new NetworkOnMainThreadException();
_L4:
        throw new UnsupportedOperationException(s);
_L3:
        throw new ServiceSpecificException(readInt(), s);
    }

    public final int readExceptionCode()
    {
        int i = readInt();
        if(i == -128)
        {
            if(readInt() == 0)
                Log.e("Parcel", "Unexpected zero-sized Parcel reply header.");
            else
                StrictMode.readAndHandleBinderCallViolations(this);
            return 0;
        } else
        {
            return i;
        }
    }

    public final ParcelFileDescriptor readFileDescriptor()
    {
        ParcelFileDescriptor parcelfiledescriptor = null;
        FileDescriptor filedescriptor = nativeReadFileDescriptor(mNativePtr);
        if(filedescriptor != null)
            parcelfiledescriptor = new ParcelFileDescriptor(filedescriptor);
        return parcelfiledescriptor;
    }

    public final float readFloat()
    {
        return nativeReadFloat(mNativePtr);
    }

    public final void readFloatArray(float af[])
    {
        int i = readInt();
        if(i == af.length)
        {
            for(int j = 0; j < i; j++)
                af[j] = readFloat();

        } else
        {
            throw new RuntimeException("bad array lengths");
        }
    }

    public final HashMap readHashMap(ClassLoader classloader)
    {
        int i = readInt();
        if(i < 0)
        {
            return null;
        } else
        {
            HashMap hashmap = new HashMap(i);
            readMapInternal(hashmap, i, classloader);
            return hashmap;
        }
    }

    public final int readInt()
    {
        return nativeReadInt(mNativePtr);
    }

    public final void readIntArray(int ai[])
    {
        int i = readInt();
        if(i == ai.length)
        {
            for(int j = 0; j < i; j++)
                ai[j] = readInt();

        } else
        {
            throw new RuntimeException("bad array lengths");
        }
    }

    public final void readList(List list, ClassLoader classloader)
    {
        readListInternal(list, readInt(), classloader);
    }

    public final long readLong()
    {
        return nativeReadLong(mNativePtr);
    }

    public final void readLongArray(long al[])
    {
        int i = readInt();
        if(i == al.length)
        {
            for(int j = 0; j < i; j++)
                al[j] = readLong();

        } else
        {
            throw new RuntimeException("bad array lengths");
        }
    }

    public final void readMap(Map map, ClassLoader classloader)
    {
        readMapInternal(map, readInt(), classloader);
    }

    void readMapInternal(Map map, int i, ClassLoader classloader)
    {
        for(; i > 0; i--)
            map.put(readValue(classloader), readValue(classloader));

    }

    public final Parcelable readParcelable(ClassLoader classloader)
    {
        Parcelable.Creator creator = readParcelableCreator(classloader);
        if(creator == null)
            return null;
        if(creator instanceof Parcelable.ClassLoaderCreator)
            return (Parcelable)((Parcelable.ClassLoaderCreator)creator).createFromParcel(this, classloader);
        else
            return (Parcelable)creator.createFromParcel(this);
    }

    public final Parcelable[] readParcelableArray(ClassLoader classloader)
    {
        int i = readInt();
        if(i < 0)
            return null;
        Parcelable aparcelable[] = new Parcelable[i];
        for(int j = 0; j < i; j++)
            aparcelable[j] = readParcelable(classloader);

        return aparcelable;
    }

    public final Parcelable[] readParcelableArray(ClassLoader classloader, Class class1)
    {
        int i = readInt();
        if(i < 0)
            return null;
        class1 = (Parcelable[])Array.newInstance(class1, i);
        for(int j = 0; j < i; j++)
            class1[j] = readParcelable(classloader);

        return class1;
    }

    public final Parcelable.Creator readParcelableCreator(ClassLoader classloader)
    {
        String s;
        s = readString();
        if(s == null)
            return null;
        HashMap hashmap = mCreators;
        hashmap;
        JVM INSTR monitorenter ;
        Object obj = (HashMap)mCreators.get(classloader);
        Object obj1;
        obj1 = obj;
        if(obj != null)
            break MISSING_BLOCK_LABEL_58;
        obj1 = JVM INSTR new #139 <Class HashMap>;
        ((HashMap) (obj1)).HashMap();
        mCreators.put(classloader, obj1);
        Parcelable.Creator creator = (Parcelable.Creator)((HashMap) (obj1)).get(s);
        obj = creator;
        if(creator != null)
            break MISSING_BLOCK_LABEL_460;
        if(classloader != null)
            break MISSING_BLOCK_LABEL_90;
        classloader = getClass().getClassLoader();
        classloader = Class.forName(s, false, classloader);
        if(!android/os/Parcelable.isAssignableFrom(classloader))
        {
            classloader = JVM INSTR new #641 <Class BadParcelableException>;
            classloader.BadParcelableException("Parcelable protocol requires that the class implements Parcelable");
            throw classloader;
        }
        break MISSING_BLOCK_LABEL_196;
        classloader;
        obj1 = JVM INSTR new #300 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.e("Parcel", ((StringBuilder) (obj1)).append("Illegal access when unmarshalling: ").append(s).toString(), classloader);
        classloader = JVM INSTR new #641 <Class BadParcelableException>;
        obj1 = JVM INSTR new #300 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        classloader.BadParcelableException(((StringBuilder) (obj1)).append("IllegalAccessException when unmarshalling: ").append(s).toString());
        throw classloader;
        classloader;
        hashmap;
        JVM INSTR monitorexit ;
        throw classloader;
        classloader = classloader.getField("CREATOR");
        if((classloader.getModifiers() & 8) == 0)
        {
            classloader = JVM INSTR new #641 <Class BadParcelableException>;
            obj1 = JVM INSTR new #300 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            classloader.BadParcelableException(((StringBuilder) (obj1)).append("Parcelable protocol requires the CREATOR object to be static on class ").append(s).toString());
            throw classloader;
        }
        break MISSING_BLOCK_LABEL_317;
        classloader;
        obj1 = JVM INSTR new #300 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.e("Parcel", ((StringBuilder) (obj1)).append("Class not found when unmarshalling: ").append(s).toString(), classloader);
        obj1 = JVM INSTR new #641 <Class BadParcelableException>;
        classloader = JVM INSTR new #300 <Class StringBuilder>;
        classloader.StringBuilder();
        ((BadParcelableException) (obj1)).BadParcelableException(classloader.append("ClassNotFoundException when unmarshalling: ").append(s).toString());
        throw obj1;
        if(!android/os/Parcelable$Creator.isAssignableFrom(classloader.getType()))
        {
            classloader = JVM INSTR new #641 <Class BadParcelableException>;
            obj1 = JVM INSTR new #300 <Class StringBuilder>;
            ((StringBuilder) (obj1)).StringBuilder();
            classloader.BadParcelableException(((StringBuilder) (obj1)).append("Parcelable protocol requires a Parcelable.Creator object called CREATOR on class ").append(s).toString());
            throw classloader;
        }
        break MISSING_BLOCK_LABEL_401;
        classloader;
        classloader = JVM INSTR new #641 <Class BadParcelableException>;
        obj1 = JVM INSTR new #300 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        classloader.BadParcelableException(((StringBuilder) (obj1)).append("Parcelable protocol requires a Parcelable.Creator object called CREATOR on class ").append(s).toString());
        throw classloader;
        obj = (Parcelable.Creator)classloader.get(null);
        if(obj != null)
            break MISSING_BLOCK_LABEL_451;
        obj1 = JVM INSTR new #641 <Class BadParcelableException>;
        classloader = JVM INSTR new #300 <Class StringBuilder>;
        classloader.StringBuilder();
        ((BadParcelableException) (obj1)).BadParcelableException(classloader.append("Parcelable protocol requires a non-null Parcelable.Creator object called CREATOR on class ").append(s).toString());
        throw obj1;
        ((HashMap) (obj1)).put(s, obj);
        hashmap;
        JVM INSTR monitorexit ;
        return ((Parcelable.Creator) (obj));
    }

    public final List readParcelableList(List list, ClassLoader classloader)
    {
        int i = readInt();
        if(i == -1)
        {
            list.clear();
            return list;
        }
        int j = list.size();
        int k = 0;
        int l;
        do
        {
            l = k;
            if(k >= j)
                break;
            l = k;
            if(k >= i)
                break;
            list.set(k, readParcelable(classloader));
            k++;
        } while(true);
        do
        {
            k = l;
            if(l >= i)
                break;
            list.add(readParcelable(classloader));
            l++;
        } while(true);
        for(; k < j; k++)
            list.remove(i);

        return list;
    }

    public final PersistableBundle readPersistableBundle()
    {
        return readPersistableBundle(null);
    }

    public final PersistableBundle readPersistableBundle(ClassLoader classloader)
    {
        int i = readInt();
        if(i < 0)
            return null;
        PersistableBundle persistablebundle = new PersistableBundle(this, i);
        if(classloader != null)
            persistablebundle.setClassLoader(classloader);
        return persistablebundle;
    }

    public final FileDescriptor readRawFileDescriptor()
    {
        return nativeReadFileDescriptor(mNativePtr);
    }

    public final void readRawFileDescriptorArray(FileDescriptor afiledescriptor[])
    {
        int i = readInt();
        if(i == afiledescriptor.length)
        {
            for(int j = 0; j < i; j++)
                afiledescriptor[j] = readRawFileDescriptor();

        } else
        {
            throw new RuntimeException("bad array lengths");
        }
    }

    public final Serializable readSerializable()
    {
        return readSerializable(null);
    }

    public final Size readSize()
    {
        return new Size(readInt(), readInt());
    }

    public final SizeF readSizeF()
    {
        return new SizeF(readFloat(), readFloat());
    }

    public final SparseArray readSparseArray(ClassLoader classloader)
    {
        int i = readInt();
        if(i < 0)
        {
            return null;
        } else
        {
            SparseArray sparsearray = new SparseArray(i);
            readSparseArrayInternal(sparsearray, i, classloader);
            return sparsearray;
        }
    }

    public final SparseBooleanArray readSparseBooleanArray()
    {
        int i = readInt();
        if(i < 0)
        {
            return null;
        } else
        {
            SparseBooleanArray sparsebooleanarray = new SparseBooleanArray(i);
            readSparseBooleanArrayInternal(sparsebooleanarray, i);
            return sparsebooleanarray;
        }
    }

    public final SparseIntArray readSparseIntArray()
    {
        int i = readInt();
        if(i < 0)
        {
            return null;
        } else
        {
            SparseIntArray sparseintarray = new SparseIntArray(i);
            readSparseIntArrayInternal(sparseintarray, i);
            return sparseintarray;
        }
    }

    public final String readString()
    {
        return mReadWriteHelper.readString(this);
    }

    public final void readStringArray(String as[])
    {
        int i = readInt();
        if(i == as.length)
        {
            for(int j = 0; j < i; j++)
                as[j] = readString();

        } else
        {
            throw new RuntimeException("bad array lengths");
        }
    }

    public final String[] readStringArray()
    {
        String as[] = null;
        int i = readInt();
        if(i >= 0)
        {
            String as1[] = new String[i];
            int j = 0;
            do
            {
                as = as1;
                if(j >= i)
                    break;
                as1[j] = readString();
                j++;
            } while(true);
        }
        return as;
    }

    public final void readStringList(List list)
    {
        int i = list.size();
        int j = readInt();
        int k = 0;
        int l;
        do
        {
            l = k;
            if(k >= i)
                break;
            l = k;
            if(k >= j)
                break;
            list.set(k, readString());
            k++;
        } while(true);
        do
        {
            k = l;
            if(l >= j)
                break;
            list.add(readString());
            l++;
        } while(true);
        for(; k < i; k++)
            list.remove(j);

    }

    public String readStringNoHelper()
    {
        return nativeReadString(mNativePtr);
    }

    public final IBinder readStrongBinder()
    {
        return nativeReadStrongBinder(mNativePtr);
    }

    public final void readTypedArray(Object aobj[], Parcelable.Creator creator)
    {
        int i = readInt();
        if(i == aobj.length)
        {
            int j = 0;
            while(j < i) 
            {
                if(readInt() != 0)
                    aobj[j] = creator.createFromParcel(this);
                else
                    aobj[j] = null;
                j++;
            }
        } else
        {
            throw new RuntimeException("bad array lengths");
        }
    }

    public final Object[] readTypedArray(Parcelable.Creator creator)
    {
        return createTypedArray(creator);
    }

    public final ArrayList readTypedArrayList(ClassLoader classloader)
    {
        int i = readInt();
        if(i <= 0)
            return null;
        Object obj = null;
        ArrayList arraylist = new ArrayList(i);
        int j = 0;
        while(j < i) 
        {
            Object obj1;
            if(readInt() != 0)
            {
                obj1 = obj;
                if(obj == null)
                {
                    obj = readParcelableCreator(classloader);
                    obj1 = obj;
                    if(obj == null)
                        return null;
                }
                if(obj1 instanceof Parcelable.ClassLoaderCreator)
                    obj = ((Parcelable.ClassLoaderCreator)obj1).createFromParcel(this, classloader);
                else
                    obj = ((Parcelable.Creator) (obj1)).createFromParcel(this);
                arraylist.add(obj);
            } else
            {
                arraylist.add(null);
                obj1 = obj;
            }
            j++;
            obj = obj1;
        }
        return arraylist;
    }

    public final ArraySet readTypedArraySet(ClassLoader classloader)
    {
        int i = readInt();
        if(i <= 0)
            return null;
        Parcelable.Creator creator = null;
        ArraySet arrayset = new ArraySet(i);
        int j = 0;
        while(j < i) 
        {
            Object obj = null;
            Parcelable.Creator creator1 = creator;
            if(readInt() != 0)
            {
                creator1 = creator;
                if(creator == null)
                {
                    creator = readParcelableCreator(classloader);
                    creator1 = creator;
                    if(creator == null)
                        return null;
                }
                if(creator1 instanceof Parcelable.ClassLoaderCreator)
                    obj = ((Parcelable.ClassLoaderCreator)creator1).createFromParcel(this, classloader);
                else
                    obj = creator1.createFromParcel(this);
            }
            arrayset.append(obj);
            j++;
            creator = creator1;
        }
        return arrayset;
    }

    public final void readTypedList(List list, Parcelable.Creator creator)
    {
        int i = list.size();
        int j = readInt();
        int k = 0;
        int l;
        do
        {
            l = k;
            if(k >= i)
                break;
            l = k;
            if(k >= j)
                break;
            if(readInt() != 0)
                list.set(k, creator.createFromParcel(this));
            else
                list.set(k, null);
            k++;
        } while(true);
        do
        {
            k = l;
            if(l >= j)
                break;
            if(readInt() != 0)
                list.add(creator.createFromParcel(this));
            else
                list.add(null);
            l++;
        } while(true);
        for(; k < i; k++)
            list.remove(j);

    }

    public final Object readTypedObject(Parcelable.Creator creator)
    {
        if(readInt() != 0)
            return creator.createFromParcel(this);
        else
            return null;
    }

    public final Object readValue(ClassLoader classloader)
    {
        boolean flag = true;
        int i = readInt();
        switch(i)
        {
        default:
            int j = dataPosition();
            throw new RuntimeException((new StringBuilder()).append("Parcel ").append(this).append(": Unmarshalling unknown type code ").append(i).append(" at offset ").append(j - 4).toString());

        case -1: 
            return null;

        case 0: // '\0'
            return readString();

        case 1: // '\001'
            return Integer.valueOf(readInt());

        case 2: // '\002'
            return readHashMap(classloader);

        case 4: // '\004'
            return readParcelable(classloader);

        case 5: // '\005'
            return Short.valueOf((short)readInt());

        case 6: // '\006'
            return Long.valueOf(readLong());

        case 7: // '\007'
            return Float.valueOf(readFloat());

        case 8: // '\b'
            return Double.valueOf(readDouble());

        case 9: // '\t'
            if(readInt() != 1)
                flag = false;
            return Boolean.valueOf(flag);

        case 10: // '\n'
            return readCharSequence();

        case 11: // '\013'
            return readArrayList(classloader);

        case 23: // '\027'
            return createBooleanArray();

        case 13: // '\r'
            return createByteArray();

        case 14: // '\016'
            return readStringArray();

        case 24: // '\030'
            return readCharSequenceArray();

        case 15: // '\017'
            return readStrongBinder();

        case 17: // '\021'
            return ((Object) (readArray(classloader)));

        case 18: // '\022'
            return createIntArray();

        case 19: // '\023'
            return createLongArray();

        case 20: // '\024'
            return Byte.valueOf(readByte());

        case 21: // '\025'
            return readSerializable(classloader);

        case 16: // '\020'
            return readParcelableArray(classloader);

        case 12: // '\f'
            return readSparseArray(classloader);

        case 22: // '\026'
            return readSparseBooleanArray();

        case 3: // '\003'
            return readBundle(classloader);

        case 25: // '\031'
            return readPersistableBundle(classloader);

        case 26: // '\032'
            return readSize();

        case 27: // '\033'
            return readSizeF();

        case 28: // '\034'
            return createDoubleArray();
        }
    }

    public final void recycle()
    {
        freeBuffer();
        if(!mOwnsNativeParcelObject) goto _L2; else goto _L1
_L1:
        Parcel aparcel[] = sOwnedPool;
_L6:
        aparcel;
        JVM INSTR monitorenter ;
        int i = 0;
_L4:
        if(i >= 6)
            break; /* Loop/switch isn't completed */
        if(aparcel[i] == null)
        {
            aparcel[i] = this;
            return;
        }
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        mNativePtr = 0L;
        aparcel = sHolderPool;
        continue; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        return;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public final void restoreAllowFds(boolean flag)
    {
        nativeRestoreAllowFds(mNativePtr, flag);
    }

    public final void setClassCookie(Class class1, Object obj)
    {
        if(mClassCookies == null)
            mClassCookies = new ArrayMap();
        mClassCookies.put(class1, obj);
    }

    public final void setDataCapacity(int i)
    {
        nativeSetDataCapacity(mNativePtr, i);
    }

    public final void setDataPosition(int i)
    {
        nativeSetDataPosition(mNativePtr, i);
    }

    public final void setDataSize(int i)
    {
        updateNativeSize(nativeSetDataSize(mNativePtr, i));
    }

    public void setReadWriteHelper(ReadWriteHelper readwritehelper)
    {
        if(readwritehelper == null)
            readwritehelper = ReadWriteHelper.DEFAULT;
        mReadWriteHelper = readwritehelper;
    }

    public final void unmarshall(byte abyte0[], int i, int j)
    {
        updateNativeSize(nativeUnmarshall(mNativePtr, abyte0, i, j));
    }

    public final void writeArray(Object aobj[])
    {
        if(aobj == null)
        {
            writeInt(-1);
            return;
        }
        int i = aobj.length;
        int j = 0;
        writeInt(i);
        for(; j < i; j++)
            writeValue(aobj[j]);

    }

    public void writeArrayMap(ArrayMap arraymap)
    {
        writeArrayMapInternal(arraymap);
    }

    void writeArrayMapInternal(ArrayMap arraymap)
    {
        if(arraymap == null)
        {
            writeInt(-1);
            return;
        }
        int i = arraymap.size();
        writeInt(i);
        for(int j = 0; j < i; j++)
        {
            writeString((String)arraymap.keyAt(j));
            writeValue(arraymap.valueAt(j));
        }

    }

    public void writeArraySet(ArraySet arrayset)
    {
        int i;
        if(arrayset != null)
            i = arrayset.size();
        else
            i = -1;
        writeInt(i);
        for(int j = 0; j < i; j++)
            writeValue(arrayset.valueAt(j));

    }

    public final void writeBinderArray(IBinder aibinder[])
    {
        if(aibinder != null)
        {
            int i = aibinder.length;
            writeInt(i);
            for(int j = 0; j < i; j++)
                writeStrongBinder(aibinder[j]);

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeBinderList(List list)
    {
        if(list == null)
        {
            writeInt(-1);
            return;
        }
        int i = list.size();
        int j = 0;
        writeInt(i);
        for(; j < i; j++)
            writeStrongBinder((IBinder)list.get(j));

    }

    public final void writeBlob(byte abyte0[])
    {
        int i;
        if(abyte0 != null)
            i = abyte0.length;
        else
            i = 0;
        writeBlob(abyte0, 0, i);
    }

    public final void writeBlob(byte abyte0[], int i, int j)
    {
        if(abyte0 == null)
        {
            writeInt(-1);
            return;
        } else
        {
            Arrays.checkOffsetAndCount(abyte0.length, i, j);
            nativeWriteBlob(mNativePtr, abyte0, i, j);
            return;
        }
    }

    public final void writeBoolean(boolean flag)
    {
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        writeInt(i);
    }

    public final void writeBooleanArray(boolean aflag[])
    {
        if(aflag != null)
        {
            int i = aflag.length;
            writeInt(i);
            int j = 0;
            while(j < i) 
            {
                int k;
                if(aflag[j])
                    k = 1;
                else
                    k = 0;
                writeInt(k);
                j++;
            }
        } else
        {
            writeInt(-1);
        }
    }

    public final void writeBundle(Bundle bundle)
    {
        if(bundle == null)
        {
            writeInt(-1);
            return;
        } else
        {
            bundle.writeToParcel(this, 0);
            return;
        }
    }

    public final void writeByte(byte byte0)
    {
        writeInt(byte0);
    }

    public final void writeByteArray(byte abyte0[])
    {
        int i;
        if(abyte0 != null)
            i = abyte0.length;
        else
            i = 0;
        writeByteArray(abyte0, 0, i);
    }

    public final void writeByteArray(byte abyte0[], int i, int j)
    {
        if(abyte0 == null)
        {
            writeInt(-1);
            return;
        } else
        {
            Arrays.checkOffsetAndCount(abyte0.length, i, j);
            nativeWriteByteArray(mNativePtr, abyte0, i, j);
            return;
        }
    }

    public final void writeCharArray(char ac[])
    {
        if(ac != null)
        {
            int i = ac.length;
            writeInt(i);
            for(int j = 0; j < i; j++)
                writeInt(ac[j]);

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeCharSequence(CharSequence charsequence)
    {
        TextUtils.writeToParcel(charsequence, this, 0);
    }

    public final void writeCharSequenceArray(CharSequence acharsequence[])
    {
        if(acharsequence != null)
        {
            int i = acharsequence.length;
            writeInt(i);
            for(int j = 0; j < i; j++)
                writeCharSequence(acharsequence[j]);

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeCharSequenceList(ArrayList arraylist)
    {
        if(arraylist != null)
        {
            int i = arraylist.size();
            writeInt(i);
            for(int j = 0; j < i; j++)
                writeCharSequence((CharSequence)arraylist.get(j));

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeDouble(double d)
    {
        nativeWriteDouble(mNativePtr, d);
    }

    public final void writeDoubleArray(double ad[])
    {
        if(ad != null)
        {
            int i = ad.length;
            writeInt(i);
            for(int j = 0; j < i; j++)
                writeDouble(ad[j]);

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeException(Exception exception)
    {
        byte byte0;
        byte0 = 0;
        if((exception instanceof Parcelable) && exception.getClass().getClassLoader() == android/os/Parcelable.getClassLoader())
            byte0 = -9;
        else
        if(exception instanceof SecurityException)
            byte0 = -1;
        else
        if(exception instanceof BadParcelableException)
            byte0 = -2;
        else
        if(exception instanceof IllegalArgumentException)
            byte0 = -3;
        else
        if(exception instanceof NullPointerException)
            byte0 = -4;
        else
        if(exception instanceof IllegalStateException)
            byte0 = -5;
        else
        if(exception instanceof NetworkOnMainThreadException)
            byte0 = -6;
        else
        if(exception instanceof UnsupportedOperationException)
            byte0 = -7;
        else
        if(exception instanceof ServiceSpecificException)
            byte0 = -8;
        writeInt(byte0);
        StrictMode.clearGatheredViolations();
        if(byte0 == 0)
            if(exception instanceof RuntimeException)
                throw (RuntimeException)exception;
            else
                throw new RuntimeException(exception);
        writeString(exception.getMessage());
        byte0;
        JVM INSTR tableswitch -9 -8: default 196
    //                   -9 211
    //                   -8 197;
           goto _L1 _L2 _L3
_L1:
        return;
_L3:
        writeInt(((ServiceSpecificException)exception).errorCode);
        continue; /* Loop/switch isn't completed */
_L2:
        int i = dataPosition();
        writeInt(0);
        writeParcelable((Parcelable)exception, 1);
        int j = dataPosition();
        setDataPosition(i);
        writeInt(j - i);
        setDataPosition(j);
        if(true) goto _L1; else goto _L4
_L4:
    }

    public final void writeFileDescriptor(FileDescriptor filedescriptor)
    {
        updateNativeSize(nativeWriteFileDescriptor(mNativePtr, filedescriptor));
    }

    public final void writeFloat(float f)
    {
        nativeWriteFloat(mNativePtr, f);
    }

    public final void writeFloatArray(float af[])
    {
        if(af != null)
        {
            int i = af.length;
            writeInt(i);
            for(int j = 0; j < i; j++)
                writeFloat(af[j]);

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeInt(int i)
    {
        nativeWriteInt(mNativePtr, i);
    }

    public final void writeIntArray(int ai[])
    {
        if(ai != null)
        {
            int i = ai.length;
            writeInt(i);
            for(int j = 0; j < i; j++)
                writeInt(ai[j]);

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeInterfaceToken(String s)
    {
        nativeWriteInterfaceToken(mNativePtr, s);
    }

    public final void writeList(List list)
    {
        if(list == null)
        {
            writeInt(-1);
            return;
        }
        int i = list.size();
        int j = 0;
        writeInt(i);
        for(; j < i; j++)
            writeValue(list.get(j));

    }

    public final void writeLong(long l)
    {
        nativeWriteLong(mNativePtr, l);
    }

    public final void writeLongArray(long al[])
    {
        if(al != null)
        {
            int i = al.length;
            writeInt(i);
            for(int j = 0; j < i; j++)
                writeLong(al[j]);

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeMap(Map map)
    {
        writeMapInternal(map);
    }

    void writeMapInternal(Map map)
    {
        if(map == null)
        {
            writeInt(-1);
            return;
        }
        map = map.entrySet();
        writeInt(map.size());
        for(Iterator iterator = map.iterator(); iterator.hasNext(); writeValue(map.getValue()))
        {
            map = (java.util.Map.Entry)iterator.next();
            writeValue(map.getKey());
        }

    }

    public final void writeNoException()
    {
        if(StrictMode.hasGatheredViolations())
        {
            writeInt(-128);
            int i = dataPosition();
            writeInt(0);
            StrictMode.writeGatheredViolationsToParcel(this);
            int j = dataPosition();
            setDataPosition(i);
            writeInt(j - i);
            setDataPosition(j);
        } else
        {
            writeInt(0);
        }
    }

    public final void writeParcelable(Parcelable parcelable, int i)
    {
        if(parcelable == null)
        {
            writeString(null);
            return;
        } else
        {
            writeParcelableCreator(parcelable);
            parcelable.writeToParcel(this, i);
            return;
        }
    }

    public final void writeParcelableArray(Parcelable aparcelable[], int i)
    {
        if(aparcelable != null)
        {
            int j = aparcelable.length;
            writeInt(j);
            for(int k = 0; k < j; k++)
                writeParcelable(aparcelable[k], i);

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeParcelableCreator(Parcelable parcelable)
    {
        writeString(parcelable.getClass().getName());
    }

    public final void writeParcelableList(List list, int i)
    {
        if(list == null)
        {
            writeInt(-1);
            return;
        }
        int j = list.size();
        int k = 0;
        writeInt(j);
        for(; k < j; k++)
            writeParcelable((Parcelable)list.get(k), i);

    }

    public final void writePersistableBundle(PersistableBundle persistablebundle)
    {
        if(persistablebundle == null)
        {
            writeInt(-1);
            return;
        } else
        {
            persistablebundle.writeToParcel(this, 0);
            return;
        }
    }

    public final void writeRawFileDescriptor(FileDescriptor filedescriptor)
    {
        nativeWriteFileDescriptor(mNativePtr, filedescriptor);
    }

    public final void writeRawFileDescriptorArray(FileDescriptor afiledescriptor[])
    {
        if(afiledescriptor != null)
        {
            int i = afiledescriptor.length;
            writeInt(i);
            for(int j = 0; j < i; j++)
                writeRawFileDescriptor(afiledescriptor[j]);

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeSerializable(Serializable serializable)
    {
        if(serializable == null)
        {
            writeString(null);
            return;
        }
        String s = serializable.getClass().getName();
        writeString(s);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        try
        {
            ObjectOutputStream objectoutputstream = JVM INSTR new #1150 <Class ObjectOutputStream>;
            objectoutputstream.ObjectOutputStream(bytearrayoutputstream);
            objectoutputstream.writeObject(serializable);
            objectoutputstream.close();
            writeByteArray(bytearrayoutputstream.toByteArray());
            return;
        }
        // Misplaced declaration of an exception variable
        catch(Serializable serializable)
        {
            throw new RuntimeException((new StringBuilder()).append("Parcelable encountered IOException writing serializable object (name = ").append(s).append(")").toString(), serializable);
        }
    }

    public final void writeSize(Size size)
    {
        writeInt(size.getWidth());
        writeInt(size.getHeight());
    }

    public final void writeSizeF(SizeF sizef)
    {
        writeFloat(sizef.getWidth());
        writeFloat(sizef.getHeight());
    }

    public final void writeSparseArray(SparseArray sparsearray)
    {
        if(sparsearray == null)
        {
            writeInt(-1);
            return;
        }
        int i = sparsearray.size();
        writeInt(i);
        for(int j = 0; j < i; j++)
        {
            writeInt(sparsearray.keyAt(j));
            writeValue(sparsearray.valueAt(j));
        }

    }

    public final void writeSparseBooleanArray(SparseBooleanArray sparsebooleanarray)
    {
        if(sparsebooleanarray == null)
        {
            writeInt(-1);
            return;
        }
        int i = sparsebooleanarray.size();
        writeInt(i);
        int j = 0;
        while(j < i) 
        {
            writeInt(sparsebooleanarray.keyAt(j));
            int k;
            if(sparsebooleanarray.valueAt(j))
                k = 1;
            else
                k = 0;
            writeByte((byte)k);
            j++;
        }
    }

    public final void writeSparseIntArray(SparseIntArray sparseintarray)
    {
        if(sparseintarray == null)
        {
            writeInt(-1);
            return;
        }
        int i = sparseintarray.size();
        writeInt(i);
        for(int j = 0; j < i; j++)
        {
            writeInt(sparseintarray.keyAt(j));
            writeInt(sparseintarray.valueAt(j));
        }

    }

    public final void writeString(String s)
    {
        mReadWriteHelper.writeString(this, s);
    }

    public final void writeStringArray(String as[])
    {
        if(as != null)
        {
            int i = as.length;
            writeInt(i);
            for(int j = 0; j < i; j++)
                writeString(as[j]);

        } else
        {
            writeInt(-1);
        }
    }

    public final void writeStringList(List list)
    {
        if(list == null)
        {
            writeInt(-1);
            return;
        }
        int i = list.size();
        int j = 0;
        writeInt(i);
        for(; j < i; j++)
            writeString((String)list.get(j));

    }

    public void writeStringNoHelper(String s)
    {
        nativeWriteString(mNativePtr, s);
    }

    public final void writeStrongBinder(IBinder ibinder)
    {
        nativeWriteStrongBinder(mNativePtr, ibinder);
    }

    public final void writeStrongInterface(IInterface iinterface)
    {
        Object obj = null;
        if(iinterface == null)
            iinterface = obj;
        else
            iinterface = iinterface.asBinder();
        writeStrongBinder(iinterface);
    }

    public final void writeTypedArray(Parcelable aparcelable[], int i)
    {
        if(aparcelable != null)
        {
            int j = aparcelable.length;
            writeInt(j);
            int k = 0;
            while(k < j) 
            {
                Parcelable parcelable = aparcelable[k];
                if(parcelable != null)
                {
                    writeInt(1);
                    parcelable.writeToParcel(this, i);
                } else
                {
                    writeInt(0);
                }
                k++;
            }
        } else
        {
            writeInt(-1);
        }
    }

    public final void writeTypedArrayList(ArrayList arraylist, int i)
    {
        if(arraylist != null)
        {
            int j = arraylist.size();
            writeInt(j);
            boolean flag = false;
            int k = 0;
            while(k < j) 
            {
                Parcelable parcelable = (Parcelable)arraylist.get(k);
                if(parcelable != null)
                {
                    writeInt(1);
                    boolean flag1 = flag;
                    if(!flag)
                    {
                        writeParcelableCreator(parcelable);
                        flag1 = true;
                    }
                    parcelable.writeToParcel(this, i);
                    flag = flag1;
                } else
                {
                    writeInt(0);
                }
                k++;
            }
        } else
        {
            writeInt(-1);
        }
    }

    public final void writeTypedArraySet(ArraySet arrayset, int i)
    {
        if(arrayset != null)
        {
            int j = arrayset.size();
            writeInt(j);
            boolean flag = false;
            int k = 0;
            while(k < j) 
            {
                Parcelable parcelable = (Parcelable)arrayset.valueAt(k);
                if(parcelable != null)
                {
                    writeInt(1);
                    boolean flag1 = flag;
                    if(!flag)
                    {
                        writeParcelableCreator(parcelable);
                        flag1 = true;
                    }
                    parcelable.writeToParcel(this, i);
                    flag = flag1;
                } else
                {
                    writeInt(0);
                }
                k++;
            }
        } else
        {
            writeInt(-1);
        }
    }

    public final void writeTypedList(List list)
    {
        writeTypedList(list, 0);
    }

    public void writeTypedList(List list, int i)
    {
        if(list == null)
        {
            writeInt(-1);
            return;
        }
        int j = list.size();
        int k = 0;
        writeInt(j);
        for(; k < j; k++)
            writeTypedObject((Parcelable)list.get(k), i);

    }

    public final void writeTypedObject(Parcelable parcelable, int i)
    {
        if(parcelable != null)
        {
            writeInt(1);
            parcelable.writeToParcel(this, i);
        } else
        {
            writeInt(0);
        }
    }

    public final void writeValue(Object obj)
    {
        int i = 1;
        if(obj == null)
            writeInt(-1);
        else
        if(obj instanceof String)
        {
            writeInt(0);
            writeString((String)obj);
        } else
        if(obj instanceof Integer)
        {
            writeInt(1);
            writeInt(((Integer)obj).intValue());
        } else
        if(obj instanceof Map)
        {
            writeInt(2);
            writeMap((Map)obj);
        } else
        if(obj instanceof Bundle)
        {
            writeInt(3);
            writeBundle((Bundle)obj);
        } else
        if(obj instanceof PersistableBundle)
        {
            writeInt(25);
            writePersistableBundle((PersistableBundle)obj);
        } else
        if(obj instanceof Parcelable)
        {
            writeInt(4);
            writeParcelable((Parcelable)obj, 0);
        } else
        if(obj instanceof Short)
        {
            writeInt(5);
            writeInt(((Short)obj).intValue());
        } else
        if(obj instanceof Long)
        {
            writeInt(6);
            writeLong(((Long)obj).longValue());
        } else
        if(obj instanceof Float)
        {
            writeInt(7);
            writeFloat(((Float)obj).floatValue());
        } else
        if(obj instanceof Double)
        {
            writeInt(8);
            writeDouble(((Double)obj).doubleValue());
        } else
        if(obj instanceof Boolean)
        {
            writeInt(9);
            if(!((Boolean)obj).booleanValue())
                i = 0;
            writeInt(i);
        } else
        if(obj instanceof CharSequence)
        {
            writeInt(10);
            writeCharSequence((CharSequence)obj);
        } else
        if(obj instanceof List)
        {
            writeInt(11);
            writeList((List)obj);
        } else
        if(obj instanceof SparseArray)
        {
            writeInt(12);
            writeSparseArray((SparseArray)obj);
        } else
        if(obj instanceof boolean[])
        {
            writeInt(23);
            writeBooleanArray((boolean[])obj);
        } else
        if(obj instanceof byte[])
        {
            writeInt(13);
            writeByteArray((byte[])obj);
        } else
        if(obj instanceof String[])
        {
            writeInt(14);
            writeStringArray((String[])obj);
        } else
        if(obj instanceof CharSequence[])
        {
            writeInt(24);
            writeCharSequenceArray((CharSequence[])obj);
        } else
        if(obj instanceof IBinder)
        {
            writeInt(15);
            writeStrongBinder((IBinder)obj);
        } else
        if(obj instanceof Parcelable[])
        {
            writeInt(16);
            writeParcelableArray((Parcelable[])obj, 0);
        } else
        if(obj instanceof int[])
        {
            writeInt(18);
            writeIntArray((int[])obj);
        } else
        if(obj instanceof long[])
        {
            writeInt(19);
            writeLongArray((long[])obj);
        } else
        if(obj instanceof Byte)
        {
            writeInt(20);
            writeInt(((Byte)obj).byteValue());
        } else
        if(obj instanceof Size)
        {
            writeInt(26);
            writeSize((Size)obj);
        } else
        if(obj instanceof SizeF)
        {
            writeInt(27);
            writeSizeF((SizeF)obj);
        } else
        if(obj instanceof double[])
        {
            writeInt(28);
            writeDoubleArray((double[])obj);
        } else
        {
            Class class1 = obj.getClass();
            if(class1.isArray() && class1.getComponentType() == java/lang/Object)
            {
                writeInt(17);
                writeArray((Object[])obj);
            } else
            if(obj instanceof Serializable)
            {
                writeInt(21);
                writeSerializable((Serializable)obj);
            } else
            {
                throw new RuntimeException((new StringBuilder()).append("Parcel: unable to marshal value ").append(obj).toString());
            }
        }
    }

    private static final boolean DEBUG_ARRAY_MAP = false;
    private static final boolean DEBUG_RECYCLE = false;
    private static final int EX_BAD_PARCELABLE = -2;
    private static final int EX_HAS_REPLY_HEADER = -128;
    private static final int EX_ILLEGAL_ARGUMENT = -3;
    private static final int EX_ILLEGAL_STATE = -5;
    private static final int EX_NETWORK_MAIN_THREAD = -6;
    private static final int EX_NULL_POINTER = -4;
    private static final int EX_PARCELABLE = -9;
    private static final int EX_SECURITY = -1;
    private static final int EX_SERVICE_SPECIFIC = -8;
    private static final int EX_TRANSACTION_FAILED = -129;
    private static final int EX_UNSUPPORTED_OPERATION = -7;
    private static final int POOL_SIZE = 6;
    public static final Parcelable.Creator STRING_CREATOR = new Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public String createFromParcel(Parcel parcel)
        {
            return parcel.readString();
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public String[] newArray(int i)
        {
            return new String[i];
        }

    }
;
    private static final String TAG = "Parcel";
    private static final int VAL_BOOLEAN = 9;
    private static final int VAL_BOOLEANARRAY = 23;
    private static final int VAL_BUNDLE = 3;
    private static final int VAL_BYTE = 20;
    private static final int VAL_BYTEARRAY = 13;
    private static final int VAL_CHARSEQUENCE = 10;
    private static final int VAL_CHARSEQUENCEARRAY = 24;
    private static final int VAL_DOUBLE = 8;
    private static final int VAL_DOUBLEARRAY = 28;
    private static final int VAL_FLOAT = 7;
    private static final int VAL_IBINDER = 15;
    private static final int VAL_INTARRAY = 18;
    private static final int VAL_INTEGER = 1;
    private static final int VAL_LIST = 11;
    private static final int VAL_LONG = 6;
    private static final int VAL_LONGARRAY = 19;
    private static final int VAL_MAP = 2;
    private static final int VAL_NULL = -1;
    private static final int VAL_OBJECTARRAY = 17;
    private static final int VAL_PARCELABLE = 4;
    private static final int VAL_PARCELABLEARRAY = 16;
    private static final int VAL_PERSISTABLEBUNDLE = 25;
    private static final int VAL_SERIALIZABLE = 21;
    private static final int VAL_SHORT = 5;
    private static final int VAL_SIZE = 26;
    private static final int VAL_SIZEF = 27;
    private static final int VAL_SPARSEARRAY = 12;
    private static final int VAL_SPARSEBOOLEANARRAY = 22;
    private static final int VAL_STRING = 0;
    private static final int VAL_STRINGARRAY = 14;
    private static final HashMap mCreators = new HashMap();
    private static final Parcel sHolderPool[] = new Parcel[6];
    private static final Parcel sOwnedPool[] = new Parcel[6];
    private ArrayMap mClassCookies;
    private long mNativePtr;
    private long mNativeSize;
    private boolean mOwnsNativeParcelObject;
    private ReadWriteHelper mReadWriteHelper;
    private RuntimeException mStack;


    // Unreferenced inner class android/os/Parcel$2

/* anonymous class */
    class _cls2 extends ObjectInputStream
    {

        protected Class resolveClass(ObjectStreamClass objectstreamclass)
            throws IOException, ClassNotFoundException
        {
            if(loader != null)
            {
                Class class1 = Class.forName(objectstreamclass.getName(), false, loader);
                if(class1 != null)
                    return class1;
            }
            return super.resolveClass(objectstreamclass);
        }

        final Parcel this$0;
        final ClassLoader val$loader;

            
                throws IOException
            {
                this$0 = Parcel.this;
                loader = classloader;
                super(final_inputstream);
            }
    }

}
