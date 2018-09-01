// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import libcore.util.NativeAllocationRegistry;

public class HwBlob
{

    public HwBlob(int i)
    {
        native_setup(i);
        sNativeRegistry.registerNativeAllocation(this, mNativeContext);
    }

    private static final native long native_init();

    private final native void native_setup(int i);

    public static Boolean[] wrapArray(boolean aflag[])
    {
        int i = aflag.length;
        Boolean aboolean[] = new Boolean[i];
        for(int j = 0; j < i; j++)
            aboolean[j] = Boolean.valueOf(aflag[j]);

        return aboolean;
    }

    public static Byte[] wrapArray(byte abyte0[])
    {
        int i = abyte0.length;
        Byte abyte[] = new Byte[i];
        for(int j = 0; j < i; j++)
            abyte[j] = Byte.valueOf(abyte0[j]);

        return abyte;
    }

    public static Double[] wrapArray(double ad[])
    {
        int i = ad.length;
        Double adouble[] = new Double[i];
        for(int j = 0; j < i; j++)
            adouble[j] = Double.valueOf(ad[j]);

        return adouble;
    }

    public static Float[] wrapArray(float af[])
    {
        int i = af.length;
        Float afloat[] = new Float[i];
        for(int j = 0; j < i; j++)
            afloat[j] = Float.valueOf(af[j]);

        return afloat;
    }

    public static Integer[] wrapArray(int ai[])
    {
        int i = ai.length;
        Integer ainteger[] = new Integer[i];
        for(int j = 0; j < i; j++)
            ainteger[j] = Integer.valueOf(ai[j]);

        return ainteger;
    }

    public static Long[] wrapArray(long al[])
    {
        int i = al.length;
        Long along[] = new Long[i];
        for(int j = 0; j < i; j++)
            along[j] = Long.valueOf(al[j]);

        return along;
    }

    public static Short[] wrapArray(short aword0[])
    {
        int i = aword0.length;
        Short ashort[] = new Short[i];
        for(int j = 0; j < i; j++)
            ashort[j] = Short.valueOf(aword0[j]);

        return ashort;
    }

    public final native boolean getBool(long l);

    public final native double getDouble(long l);

    public final native float getFloat(long l);

    public final native short getInt16(long l);

    public final native int getInt32(long l);

    public final native long getInt64(long l);

    public final native byte getInt8(long l);

    public final native String getString(long l);

    public final native long handle();

    public final native void putBlob(long l, HwBlob hwblob);

    public final native void putBool(long l, boolean flag);

    public final native void putDouble(long l, double d);

    public final native void putFloat(long l, float f);

    public final native void putInt16(long l, short word0);

    public final native void putInt32(long l, int i);

    public final native void putInt64(long l, long l1);

    public final native void putInt8(long l, byte byte0);

    public final native void putString(long l, String s);

    private static final String TAG = "HwBlob";
    private static final NativeAllocationRegistry sNativeRegistry;
    private long mNativeContext;

    static 
    {
        long l = native_init();
        sNativeRegistry = new NativeAllocationRegistry(android/os/HwBlob.getClassLoader(), l, 128L);
    }
}
