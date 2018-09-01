// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.os.Parcel;
import android.util.Slog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

// Referenced classes of package android.view.inputmethod:
//            InputMethodSubtype

public class InputMethodSubtypeArray
{

    public InputMethodSubtypeArray(Parcel parcel)
    {
        mLockObject = new Object();
        mCount = parcel.readInt();
        if(mCount > 0)
        {
            mDecompressedSize = parcel.readInt();
            mCompressedData = parcel.createByteArray();
        }
    }

    public InputMethodSubtypeArray(List list)
    {
        mLockObject = new Object();
        if(list == null)
        {
            mCount = 0;
            return;
        } else
        {
            mCount = list.size();
            mInstance = (InputMethodSubtype[])list.toArray(new InputMethodSubtype[mCount]);
            return;
        }
    }

    private static byte[] compress(byte abyte0[])
    {
        Object obj;
        Object obj1;
        Throwable throwable;
        Object obj3;
        obj = null;
        obj1 = null;
        throwable = null;
        obj3 = null;
        Object obj4;
        obj4 = JVM INSTR new #66  <Class ByteArrayOutputStream>;
        ((ByteArrayOutputStream) (obj4)).ByteArrayOutputStream();
        Object obj5;
        obj5 = JVM INSTR new #69  <Class GZIPOutputStream>;
        ((GZIPOutputStream) (obj5)).GZIPOutputStream(((java.io.OutputStream) (obj4)));
        ((GZIPOutputStream) (obj5)).write(abyte0);
        ((GZIPOutputStream) (obj5)).finish();
        obj1 = ((ByteArrayOutputStream) (obj4)).toByteArray();
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_58;
        ((GZIPOutputStream) (obj5)).close();
        abyte0 = null;
_L3:
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_70;
        ((ByteArrayOutputStream) (obj4)).close();
_L7:
        obj4 = abyte0;
_L5:
        if(obj4 == null) goto _L2; else goto _L1
_L1:
        try
        {
            throw obj4;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
_L8:
        Slog.e("InputMethodSubtypeArray", "Failed to compress the data.", abyte0);
        return null;
        abyte0;
          goto _L3
        obj5;
        obj4 = obj5;
        if(abyte0 == null) goto _L5; else goto _L4
_L4:
        if(abyte0 == obj5) goto _L7; else goto _L6
_L6:
        abyte0.addSuppressed(((Throwable) (obj5)));
        obj4 = abyte0;
          goto _L5
_L2:
        return ((byte []) (obj1));
        abyte0;
        obj4 = obj3;
_L18:
        throw abyte0;
        obj5;
_L17:
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_146;
        ((GZIPOutputStream) (obj4)).close();
_L12:
        obj4 = abyte0;
_L10:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_157;
        ((ByteArrayOutputStream) (obj1)).close();
_L16:
        abyte0 = ((byte []) (obj4));
_L14:
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_220;
        try
        {
            throw abyte0;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
          goto _L8
        throwable;
        obj4 = throwable;
        if(abyte0 == null) goto _L10; else goto _L9
_L9:
        if(abyte0 == throwable) goto _L12; else goto _L11
_L11:
        abyte0.addSuppressed(throwable);
        obj4 = abyte0;
          goto _L10
        obj1;
        abyte0 = ((byte []) (obj1));
        if(obj4 == null) goto _L14; else goto _L13
_L13:
        if(obj4 == obj1) goto _L16; else goto _L15
_L15:
        ((Throwable) (obj4)).addSuppressed(((Throwable) (obj1)));
        abyte0 = ((byte []) (obj4));
          goto _L14
        throw obj5;
        obj5;
        abyte0 = null;
        obj1 = obj;
        obj4 = throwable;
          goto _L17
        obj5;
        abyte0 = null;
        obj1 = obj4;
        obj4 = throwable;
          goto _L17
        abyte0;
        Object obj2 = null;
        obj1 = obj4;
        obj4 = obj5;
        obj5 = abyte0;
        abyte0 = obj2;
          goto _L17
        abyte0;
        obj1 = obj4;
        obj4 = obj3;
          goto _L18
        abyte0;
        obj1 = obj4;
        obj4 = obj5;
          goto _L18
    }

    private static byte[] decompress(byte abyte0[], int i)
    {
        Object obj;
        Object obj1;
        Throwable throwable;
        Object obj3;
        obj = null;
        obj1 = null;
        throwable = null;
        obj3 = null;
        Object obj4;
        obj4 = JVM INSTR new #102 <Class ByteArrayInputStream>;
        ((ByteArrayInputStream) (obj4)).ByteArrayInputStream(abyte0);
        abyte0 = JVM INSTR new #106 <Class GZIPInputStream>;
        abyte0.GZIPInputStream(((java.io.InputStream) (obj4)));
        obj1 = new byte[i];
        int j = 0;
_L8:
        if(j >= obj1.length) goto _L2; else goto _L1
_L1:
        int k = abyte0.read(((byte []) (obj1)), j, obj1.length - j);
        if(k >= 0) goto _L3; else goto _L2
_L2:
        if(i == j) goto _L5; else goto _L4
_L4:
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_78;
        abyte0.close();
        abyte0 = null;
_L9:
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_90;
        ((ByteArrayInputStream) (obj4)).close();
_L13:
        obj4 = abyte0;
_L11:
        if(obj4 == null) goto _L7; else goto _L6
_L6:
        try
        {
            throw obj4;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
_L19:
        Slog.e("InputMethodSubtypeArray", "Failed to decompress the data.", abyte0);
        return null;
_L3:
        j += k;
          goto _L8
        abyte0;
          goto _L9
        obj3;
        obj4 = obj3;
        if(abyte0 == null) goto _L11; else goto _L10
_L10:
        if(abyte0 == obj3) goto _L13; else goto _L12
_L12:
        abyte0.addSuppressed(((Throwable) (obj3)));
        obj4 = abyte0;
          goto _L11
_L7:
        return null;
_L5:
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_165;
        abyte0.close();
        abyte0 = null;
_L14:
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_177;
        ((ByteArrayInputStream) (obj4)).close();
_L18:
        obj4 = abyte0;
_L16:
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_220;
        throw obj4;
        abyte0;
          goto _L14
        obj3;
        obj4 = obj3;
        if(abyte0 == null) goto _L16; else goto _L15
_L15:
        if(abyte0 == obj3) goto _L18; else goto _L17
_L17:
        abyte0.addSuppressed(((Throwable) (obj3)));
        obj4 = abyte0;
          goto _L16
        return ((byte []) (obj1));
        abyte0;
        obj4 = obj3;
_L29:
        throw abyte0;
        obj3;
_L28:
        if(obj4 == null)
            break MISSING_BLOCK_LABEL_241;
        ((GZIPInputStream) (obj4)).close();
_L23:
        obj4 = abyte0;
_L21:
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_252;
        ((ByteArrayInputStream) (obj1)).close();
_L27:
        abyte0 = ((byte []) (obj4));
_L25:
        if(abyte0 == null)
            break MISSING_BLOCK_LABEL_319;
        try
        {
            throw abyte0;
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[]) { }
          goto _L19
        throwable;
        obj4 = throwable;
        if(abyte0 == null) goto _L21; else goto _L20
_L20:
        if(abyte0 == throwable) goto _L23; else goto _L22
_L22:
        abyte0.addSuppressed(throwable);
        obj4 = abyte0;
          goto _L21
        obj1;
        abyte0 = ((byte []) (obj1));
        if(obj4 == null) goto _L25; else goto _L24
_L24:
        if(obj4 == obj1) goto _L27; else goto _L26
_L26:
        ((Throwable) (obj4)).addSuppressed(((Throwable) (obj1)));
        abyte0 = ((byte []) (obj4));
          goto _L25
        throw obj3;
        obj3;
        abyte0 = null;
        obj1 = obj;
        obj4 = throwable;
          goto _L28
        obj3;
        abyte0 = null;
        obj1 = obj4;
        obj4 = throwable;
          goto _L28
        obj3;
        Object obj2 = null;
        obj1 = obj4;
        obj4 = abyte0;
        abyte0 = obj2;
          goto _L28
        abyte0;
        obj1 = obj4;
        obj4 = obj3;
          goto _L29
        Throwable throwable1;
        throwable1;
        obj1 = obj4;
        obj4 = abyte0;
        abyte0 = throwable1;
          goto _L29
    }

    private static byte[] marshall(InputMethodSubtype ainputmethodsubtype[])
    {
        Parcel parcel = null;
        Parcel parcel1 = Parcel.obtain();
        parcel = parcel1;
        parcel1.writeTypedArray(ainputmethodsubtype, 0);
        parcel = parcel1;
        ainputmethodsubtype = parcel1.marshall();
        if(parcel1 != null)
            parcel1.recycle();
        return ainputmethodsubtype;
        ainputmethodsubtype;
        if(parcel != null)
            parcel.recycle();
        throw ainputmethodsubtype;
    }

    private static InputMethodSubtype[] unmarshall(byte abyte0[])
    {
        Parcel parcel = null;
        Parcel parcel1 = Parcel.obtain();
        parcel = parcel1;
        parcel1.unmarshall(abyte0, 0, abyte0.length);
        parcel = parcel1;
        parcel1.setDataPosition(0);
        parcel = parcel1;
        abyte0 = (InputMethodSubtype[])parcel1.createTypedArray(InputMethodSubtype.CREATOR);
        if(parcel1 != null)
            parcel1.recycle();
        return abyte0;
        abyte0;
        if(parcel != null)
            parcel.recycle();
        throw abyte0;
    }

    public InputMethodSubtype get(int i)
    {
        InputMethodSubtype ainputmethodsubtype[];
        Object aobj[];
        if(i < 0 || mCount <= i)
            throw new ArrayIndexOutOfBoundsException();
        ainputmethodsubtype = mInstance;
        aobj = ainputmethodsubtype;
        if(ainputmethodsubtype != null) goto _L2; else goto _L1
_L1:
        Object obj = mLockObject;
        obj;
        JVM INSTR monitorenter ;
        ainputmethodsubtype = mInstance;
        aobj = ainputmethodsubtype;
        if(ainputmethodsubtype != null) goto _L4; else goto _L3
_L3:
        aobj = decompress(mCompressedData, mDecompressedSize);
        mCompressedData = null;
        mDecompressedSize = 0;
        if(aobj == null)
            break MISSING_BLOCK_LABEL_94;
        aobj = unmarshall(((byte []) (aobj)));
_L5:
        mInstance = ((InputMethodSubtype []) (aobj));
_L4:
        obj;
        JVM INSTR monitorexit ;
_L2:
        return ((InputMethodSubtype) (aobj[i]));
        Slog.e("InputMethodSubtypeArray", "Failed to decompress data. Returns null as fallback.");
        aobj = new InputMethodSubtype[mCount];
          goto _L5
        Exception exception;
        exception;
        throw exception;
    }

    public int getCount()
    {
        return mCount;
    }

    public void writeToParcel(Parcel parcel)
    {
        byte abyte0[];
        int i;
        byte abyte1[];
        int j;
        if(mCount == 0)
        {
            parcel.writeInt(mCount);
            return;
        }
        abyte0 = mCompressedData;
        i = mDecompressedSize;
        abyte1 = abyte0;
        j = i;
        if(abyte0 != null) goto _L2; else goto _L1
_L1:
        abyte1 = abyte0;
        j = i;
        if(i != 0) goto _L2; else goto _L3
_L3:
        Object obj = mLockObject;
        obj;
        JVM INSTR monitorenter ;
        abyte0 = mCompressedData;
        i = mDecompressedSize;
        abyte1 = abyte0;
        j = i;
        if(abyte0 != null) goto _L5; else goto _L4
_L4:
        abyte1 = abyte0;
        j = i;
        if(i != 0) goto _L5; else goto _L6
_L6:
        abyte0 = marshall(mInstance);
        abyte1 = compress(abyte0);
        if(abyte1 != null)
            break MISSING_BLOCK_LABEL_161;
        j = -1;
        Slog.i("InputMethodSubtypeArray", "Failed to compress data.");
_L7:
        mDecompressedSize = j;
        mCompressedData = abyte1;
_L5:
        obj;
        JVM INSTR monitorexit ;
_L2:
        if(abyte1 != null && j > 0)
        {
            parcel.writeInt(mCount);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte1);
        } else
        {
            Slog.i("InputMethodSubtypeArray", "Unexpected state. Behaving as an empty array.");
            parcel.writeInt(0);
        }
        return;
        j = abyte0.length;
          goto _L7
        parcel;
        throw parcel;
    }

    private static final String TAG = "InputMethodSubtypeArray";
    private volatile byte mCompressedData[];
    private final int mCount;
    private volatile int mDecompressedSize;
    private volatile InputMethodSubtype mInstance[];
    private final Object mLockObject;
}
