// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.ArrayList;
import java.util.Arrays;
import libcore.util.NativeAllocationRegistry;

// Referenced classes of package android.os:
//            HwBlob, IHwBinder

public class HwParcel
{

    public HwParcel()
    {
        native_setup(true);
        sNativeRegistry.registerNativeAllocation(this, mNativeContext);
    }

    private HwParcel(boolean flag)
    {
        native_setup(flag);
        sNativeRegistry.registerNativeAllocation(this, mNativeContext);
    }

    private static final native long native_init();

    private final native void native_setup(boolean flag);

    private final native boolean[] readBoolVectorAsArray();

    private final native double[] readDoubleVectorAsArray();

    private final native float[] readFloatVectorAsArray();

    private final native short[] readInt16VectorAsArray();

    private final native int[] readInt32VectorAsArray();

    private final native long[] readInt64VectorAsArray();

    private final native byte[] readInt8VectorAsArray();

    private final native String[] readStringVectorAsArray();

    private final native void writeBoolVector(boolean aflag[]);

    private final native void writeDoubleVector(double ad[]);

    private final native void writeFloatVector(float af[]);

    private final native void writeInt16Vector(short aword0[]);

    private final native void writeInt32Vector(int ai[]);

    private final native void writeInt64Vector(long al[]);

    private final native void writeInt8Vector(byte abyte0[]);

    private final native void writeStringVector(String as[]);

    public final native void enforceInterface(String s);

    public final native boolean readBool();

    public final ArrayList readBoolVector()
    {
        return new ArrayList(Arrays.asList(HwBlob.wrapArray(readBoolVectorAsArray())));
    }

    public final native HwBlob readBuffer(long l);

    public final native double readDouble();

    public final ArrayList readDoubleVector()
    {
        return new ArrayList(Arrays.asList(HwBlob.wrapArray(readDoubleVectorAsArray())));
    }

    public final native HwBlob readEmbeddedBuffer(long l, long l1, long l2, boolean flag);

    public final native float readFloat();

    public final ArrayList readFloatVector()
    {
        return new ArrayList(Arrays.asList(HwBlob.wrapArray(readFloatVectorAsArray())));
    }

    public final native short readInt16();

    public final ArrayList readInt16Vector()
    {
        return new ArrayList(Arrays.asList(HwBlob.wrapArray(readInt16VectorAsArray())));
    }

    public final native int readInt32();

    public final ArrayList readInt32Vector()
    {
        return new ArrayList(Arrays.asList(HwBlob.wrapArray(readInt32VectorAsArray())));
    }

    public final native long readInt64();

    public final ArrayList readInt64Vector()
    {
        return new ArrayList(Arrays.asList(HwBlob.wrapArray(readInt64VectorAsArray())));
    }

    public final native byte readInt8();

    public final ArrayList readInt8Vector()
    {
        return new ArrayList(Arrays.asList(HwBlob.wrapArray(readInt8VectorAsArray())));
    }

    public final native String readString();

    public final ArrayList readStringVector()
    {
        return new ArrayList(Arrays.asList(readStringVectorAsArray()));
    }

    public final native IHwBinder readStrongBinder();

    public final native void release();

    public final native void releaseTemporaryStorage();

    public final native void send();

    public final native void verifySuccess();

    public final native void writeBool(boolean flag);

    public final void writeBoolVector(ArrayList arraylist)
    {
        int i = arraylist.size();
        boolean aflag[] = new boolean[i];
        for(int j = 0; j < i; j++)
            aflag[j] = ((Boolean)arraylist.get(j)).booleanValue();

        writeBoolVector(aflag);
    }

    public final native void writeBuffer(HwBlob hwblob);

    public final native void writeDouble(double d);

    public final void writeDoubleVector(ArrayList arraylist)
    {
        int i = arraylist.size();
        double ad[] = new double[i];
        for(int j = 0; j < i; j++)
            ad[j] = ((Double)arraylist.get(j)).doubleValue();

        writeDoubleVector(ad);
    }

    public final native void writeFloat(float f);

    public final void writeFloatVector(ArrayList arraylist)
    {
        int i = arraylist.size();
        float af[] = new float[i];
        for(int j = 0; j < i; j++)
            af[j] = ((Float)arraylist.get(j)).floatValue();

        writeFloatVector(af);
    }

    public final native void writeInt16(short word0);

    public final void writeInt16Vector(ArrayList arraylist)
    {
        int i = arraylist.size();
        short aword0[] = new short[i];
        for(int j = 0; j < i; j++)
            aword0[j] = ((Short)arraylist.get(j)).shortValue();

        writeInt16Vector(aword0);
    }

    public final native void writeInt32(int i);

    public final void writeInt32Vector(ArrayList arraylist)
    {
        int i = arraylist.size();
        int ai[] = new int[i];
        for(int j = 0; j < i; j++)
            ai[j] = ((Integer)arraylist.get(j)).intValue();

        writeInt32Vector(ai);
    }

    public final native void writeInt64(long l);

    public final void writeInt64Vector(ArrayList arraylist)
    {
        int i = arraylist.size();
        long al[] = new long[i];
        for(int j = 0; j < i; j++)
            al[j] = ((Long)arraylist.get(j)).longValue();

        writeInt64Vector(al);
    }

    public final native void writeInt8(byte byte0);

    public final void writeInt8Vector(ArrayList arraylist)
    {
        int i = arraylist.size();
        byte abyte0[] = new byte[i];
        for(int j = 0; j < i; j++)
            abyte0[j] = ((Byte)arraylist.get(j)).byteValue();

        writeInt8Vector(abyte0);
    }

    public final native void writeInterfaceToken(String s);

    public final native void writeStatus(int i);

    public final native void writeString(String s);

    public final void writeStringVector(ArrayList arraylist)
    {
        writeStringVector((String[])arraylist.toArray(new String[arraylist.size()]));
    }

    public final native void writeStrongBinder(IHwBinder ihwbinder);

    public static final int STATUS_ERROR = -1;
    public static final int STATUS_SUCCESS = 0;
    private static final String TAG = "HwParcel";
    private static final NativeAllocationRegistry sNativeRegistry;
    private long mNativeContext;

    static 
    {
        long l = native_init();
        sNativeRegistry = new NativeAllocationRegistry(android/os/HwParcel.getClassLoader(), l, 128L);
    }
}
