// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.database;

import android.content.res.Resources;
import android.database.sqlite.SQLiteClosable;
import android.os.*;
import android.util.*;
import dalvik.system.CloseGuard;

// Referenced classes of package android.database:
//            CursorWindowAllocationException, CharArrayBuffer

public class CursorWindow extends SQLiteClosable
    implements Parcelable
{

    private CursorWindow(Parcel parcel)
    {
        mCloseGuard = CloseGuard.get();
        mStartPos = parcel.readInt();
        mWindowPtr = nativeCreateFromParcel(parcel);
        if(mWindowPtr == 0L)
        {
            throw new CursorWindowAllocationException("Cursor window could not be created from binder.");
        } else
        {
            mName = nativeGetName(mWindowPtr);
            mCloseGuard.open("close");
            return;
        }
    }

    CursorWindow(Parcel parcel, CursorWindow cursorwindow)
    {
        this(parcel);
    }

    public CursorWindow(String s)
    {
        mCloseGuard = CloseGuard.get();
        mStartPos = 0;
        if(s == null || s.length() == 0)
            s = "<unnamed>";
        mName = s;
        if(sCursorWindowSize < 0)
            sCursorWindowSize = Resources.getSystem().getInteger(0x10e0038) * 1024;
        mWindowPtr = nativeCreate(mName, sCursorWindowSize);
        if(mWindowPtr == 0L)
        {
            throw new CursorWindowAllocationException((new StringBuilder()).append("Cursor window allocation of ").append(sCursorWindowSize / 1024).append(" kb failed. ").append(printStats()).toString());
        } else
        {
            mCloseGuard.open("close");
            recordNewWindow(Binder.getCallingPid(), mWindowPtr);
            return;
        }
    }

    public CursorWindow(boolean flag)
    {
        this((String)null);
    }

    private void dispose()
    {
        if(mCloseGuard != null)
            mCloseGuard.close();
        if(mWindowPtr != 0L)
        {
            recordClosingOfWindow(mWindowPtr);
            nativeDispose(mWindowPtr);
            mWindowPtr = 0L;
        }
    }

    private static native boolean nativeAllocRow(long l);

    private static native void nativeClear(long l);

    private static native void nativeCopyStringToBuffer(long l, int i, int j, CharArrayBuffer chararraybuffer);

    private static native long nativeCreate(String s, int i);

    private static native long nativeCreateFromParcel(Parcel parcel);

    private static native void nativeDispose(long l);

    private static native void nativeFreeLastRow(long l);

    private static native byte[] nativeGetBlob(long l, int i, int j);

    private static native double nativeGetDouble(long l, int i, int j);

    private static native long nativeGetLong(long l, int i, int j);

    private static native String nativeGetName(long l);

    private static native int nativeGetNumRows(long l);

    private static native String nativeGetString(long l, int i, int j);

    private static native int nativeGetType(long l, int i, int j);

    private static native boolean nativePutBlob(long l, byte abyte0[], int i, int j);

    private static native boolean nativePutDouble(long l, double d, int i, int j);

    private static native boolean nativePutLong(long l, long l1, int i, int j);

    private static native boolean nativePutNull(long l, int i, int j);

    private static native boolean nativePutString(long l, String s, int i, int j);

    private static native boolean nativeSetNumColumns(long l, int i);

    private static native void nativeWriteToParcel(long l, Parcel parcel);

    public static CursorWindow newFromParcel(Parcel parcel)
    {
        return (CursorWindow)CREATOR.createFromParcel(parcel);
    }

    private String printStats()
    {
        Object obj;
        int i;
        int j;
        SparseIntArray sparseintarray;
        obj = new StringBuilder();
        i = Process.myPid();
        j = 0;
        sparseintarray = new SparseIntArray();
        LongSparseArray longsparsearray = sWindowToPidMap;
        longsparsearray;
        JVM INSTR monitorenter ;
        int k = sWindowToPidMap.size();
        if(k != 0)
            break MISSING_BLOCK_LABEL_50;
        longsparsearray;
        JVM INSTR monitorexit ;
        return "";
        int i1 = 0;
_L2:
        if(i1 >= k)
            break; /* Loop/switch isn't completed */
        int k1 = ((Integer)sWindowToPidMap.valueAt(i1)).intValue();
        sparseintarray.put(k1, sparseintarray.get(k1) + 1);
        i1++;
        if(true) goto _L2; else goto _L1
_L1:
        int l = sparseintarray.size();
        int j1 = 0;
        while(j1 < l) 
        {
            ((StringBuilder) (obj)).append(" (# cursors opened by ");
            int l1 = sparseintarray.keyAt(j1);
            if(l1 == i)
                ((StringBuilder) (obj)).append("this proc=");
            else
                ((StringBuilder) (obj)).append("pid ").append(l1).append("=");
            l1 = sparseintarray.get(l1);
            ((StringBuilder) (obj)).append(l1).append(")");
            j += l1;
            j1++;
        }
        break MISSING_BLOCK_LABEL_205;
        obj;
        throw obj;
        String s;
        if(((StringBuilder) (obj)).length() > 980)
            s = ((StringBuilder) (obj)).substring(0, 980);
        else
            s = ((StringBuilder) (obj)).toString();
        return (new StringBuilder()).append("# Open Cursors=").append(j).append(s).toString();
    }

    private void recordClosingOfWindow(long l)
    {
        LongSparseArray longsparsearray = sWindowToPidMap;
        longsparsearray;
        JVM INSTR monitorenter ;
        int i = sWindowToPidMap.size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_22;
        longsparsearray;
        JVM INSTR monitorexit ;
        return;
        sWindowToPidMap.delete(l);
        longsparsearray;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void recordNewWindow(int i, long l)
    {
        LongSparseArray longsparsearray = sWindowToPidMap;
        longsparsearray;
        JVM INSTR monitorenter ;
        sWindowToPidMap.put(l, Integer.valueOf(i));
        if(Log.isLoggable("CursorWindowStats", 2))
        {
            StringBuilder stringbuilder = JVM INSTR new #108 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("CursorWindowStats", stringbuilder.append("Created a new Cursor. ").append(printStats()).toString());
        }
        longsparsearray;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean allocRow()
    {
        acquireReference();
        boolean flag = nativeAllocRow(mWindowPtr);
        releaseReference();
        return flag;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public void clear()
    {
        acquireReference();
        mStartPos = 0;
        nativeClear(mWindowPtr);
        releaseReference();
        return;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public void copyStringToBuffer(int i, int j, CharArrayBuffer chararraybuffer)
    {
        if(chararraybuffer == null)
            throw new IllegalArgumentException("CharArrayBuffer should not be null");
        acquireReference();
        nativeCopyStringToBuffer(mWindowPtr, i - mStartPos, j, chararraybuffer);
        releaseReference();
        return;
        chararraybuffer;
        releaseReference();
        throw chararraybuffer;
    }

    public int describeContents()
    {
        return 0;
    }

    protected void finalize()
        throws Throwable
    {
        if(mCloseGuard != null)
            mCloseGuard.warnIfOpen();
        dispose();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public void freeLastRow()
    {
        acquireReference();
        nativeFreeLastRow(mWindowPtr);
        releaseReference();
        return;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public byte[] getBlob(int i, int j)
    {
        acquireReference();
        byte abyte0[] = nativeGetBlob(mWindowPtr, i - mStartPos, j);
        releaseReference();
        return abyte0;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public double getDouble(int i, int j)
    {
        acquireReference();
        double d = nativeGetDouble(mWindowPtr, i - mStartPos, j);
        releaseReference();
        return d;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public float getFloat(int i, int j)
    {
        return (float)getDouble(i, j);
    }

    public int getInt(int i, int j)
    {
        return (int)getLong(i, j);
    }

    public long getLong(int i, int j)
    {
        acquireReference();
        long l = nativeGetLong(mWindowPtr, i - mStartPos, j);
        releaseReference();
        return l;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public String getName()
    {
        return mName;
    }

    public int getNumRows()
    {
        acquireReference();
        int i = nativeGetNumRows(mWindowPtr);
        releaseReference();
        return i;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public short getShort(int i, int j)
    {
        return (short)(int)getLong(i, j);
    }

    public int getStartPosition()
    {
        return mStartPos;
    }

    public String getString(int i, int j)
    {
        acquireReference();
        String s = nativeGetString(mWindowPtr, i - mStartPos, j);
        releaseReference();
        return s;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public int getType(int i, int j)
    {
        acquireReference();
        i = nativeGetType(mWindowPtr, i - mStartPos, j);
        releaseReference();
        return i;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public boolean isBlob(int i, int j)
    {
        boolean flag = true;
        i = getType(i, j);
        boolean flag1 = flag;
        if(i != 4)
            if(i == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public boolean isFloat(int i, int j)
    {
        boolean flag;
        if(getType(i, j) == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isLong(int i, int j)
    {
        boolean flag = true;
        if(getType(i, j) != 1)
            flag = false;
        return flag;
    }

    public boolean isNull(int i, int j)
    {
        boolean flag = false;
        if(getType(i, j) == 0)
            flag = true;
        return flag;
    }

    public boolean isString(int i, int j)
    {
        boolean flag = true;
        i = getType(i, j);
        boolean flag1 = flag;
        if(i != 3)
            if(i == 0)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    protected void onAllReferencesReleased()
    {
        dispose();
    }

    public boolean putBlob(byte abyte0[], int i, int j)
    {
        acquireReference();
        boolean flag = nativePutBlob(mWindowPtr, abyte0, i - mStartPos, j);
        releaseReference();
        return flag;
        abyte0;
        releaseReference();
        throw abyte0;
    }

    public boolean putDouble(double d, int i, int j)
    {
        acquireReference();
        boolean flag = nativePutDouble(mWindowPtr, d, i - mStartPos, j);
        releaseReference();
        return flag;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public boolean putLong(long l, int i, int j)
    {
        acquireReference();
        boolean flag = nativePutLong(mWindowPtr, l, i - mStartPos, j);
        releaseReference();
        return flag;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public boolean putNull(int i, int j)
    {
        acquireReference();
        boolean flag = nativePutNull(mWindowPtr, i - mStartPos, j);
        releaseReference();
        return flag;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public boolean putString(String s, int i, int j)
    {
        acquireReference();
        boolean flag = nativePutString(mWindowPtr, s, i - mStartPos, j);
        releaseReference();
        return flag;
        s;
        releaseReference();
        throw s;
    }

    public boolean setNumColumns(int i)
    {
        acquireReference();
        boolean flag = nativeSetNumColumns(mWindowPtr, i);
        releaseReference();
        return flag;
        Exception exception;
        exception;
        releaseReference();
        throw exception;
    }

    public void setStartPosition(int i)
    {
        mStartPos = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getName()).append(" {").append(Long.toHexString(mWindowPtr)).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        acquireReference();
        parcel.writeInt(mStartPos);
        nativeWriteToParcel(mWindowPtr, parcel);
        releaseReference();
        if((i & 1) != 0)
            releaseReference();
        return;
        parcel;
        releaseReference();
        throw parcel;
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CursorWindow createFromParcel(Parcel parcel)
        {
            return new CursorWindow(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CursorWindow[] newArray(int i)
        {
            return new CursorWindow[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String STATS_TAG = "CursorWindowStats";
    private static int sCursorWindowSize = -1;
    private static final LongSparseArray sWindowToPidMap = new LongSparseArray();
    private final CloseGuard mCloseGuard;
    private final String mName;
    private int mStartPos;
    public long mWindowPtr;

}
