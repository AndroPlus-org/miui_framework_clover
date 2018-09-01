// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal;

import android.util.Rational;
import com.android.internal.util.Preconditions;

public final class MarshalHelpers
{

    private MarshalHelpers()
    {
        throw new AssertionError();
    }

    public static int checkNativeType(int i)
    {
        switch(i)
        {
        default:
            throw new UnsupportedOperationException((new StringBuilder()).append("Unknown nativeType ").append(i).toString());

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
            return i;
        }
    }

    public static int checkNativeTypeEquals(int i, int j)
    {
        if(i != j)
            throw new UnsupportedOperationException(String.format("Expected native type %d, but got %d", new Object[] {
                Integer.valueOf(i), Integer.valueOf(j)
            }));
        else
            return j;
    }

    public static Class checkPrimitiveClass(Class class1)
    {
        Preconditions.checkNotNull(class1, "klass must not be null");
        if(isPrimitiveClass(class1))
            return class1;
        else
            throw new UnsupportedOperationException((new StringBuilder()).append("Unsupported class '").append(class1).append("'; expected a metadata primitive class").toString());
    }

    public static int getPrimitiveTypeSize(int i)
    {
        switch(i)
        {
        default:
            throw new UnsupportedOperationException((new StringBuilder()).append("Unknown type, can't get size for ").append(i).toString());

        case 0: // '\0'
            return 1;

        case 1: // '\001'
            return 4;

        case 2: // '\002'
            return 4;

        case 3: // '\003'
            return 8;

        case 4: // '\004'
            return 8;

        case 5: // '\005'
            return 8;
        }
    }

    public static boolean isPrimitiveClass(Class class1)
    {
        if(class1 == null)
            return false;
        if(class1 == Byte.TYPE || class1 == java/lang/Byte)
            return true;
        if(class1 == Integer.TYPE || class1 == java/lang/Integer)
            return true;
        if(class1 == Float.TYPE || class1 == java/lang/Float)
            return true;
        if(class1 == Long.TYPE || class1 == java/lang/Long)
            return true;
        if(class1 == Double.TYPE || class1 == java/lang/Double)
            return true;
        return class1 == android/util/Rational;
    }

    public static String toStringNativeType(int i)
    {
        switch(i)
        {
        default:
            return (new StringBuilder()).append("UNKNOWN(").append(i).append(")").toString();

        case 0: // '\0'
            return "TYPE_BYTE";

        case 1: // '\001'
            return "TYPE_INT32";

        case 2: // '\002'
            return "TYPE_FLOAT";

        case 3: // '\003'
            return "TYPE_INT64";

        case 4: // '\004'
            return "TYPE_DOUBLE";

        case 5: // '\005'
            return "TYPE_RATIONAL";
        }
    }

    public static Class wrapClassIfPrimitive(Class class1)
    {
        if(class1 == Byte.TYPE)
            return java/lang/Byte;
        if(class1 == Integer.TYPE)
            return java/lang/Integer;
        if(class1 == Float.TYPE)
            return java/lang/Float;
        if(class1 == Long.TYPE)
            return java/lang/Long;
        if(class1 == Double.TYPE)
            return java/lang/Double;
        else
            return class1;
    }

    public static final int SIZEOF_BYTE = 1;
    public static final int SIZEOF_DOUBLE = 8;
    public static final int SIZEOF_FLOAT = 4;
    public static final int SIZEOF_INT32 = 4;
    public static final int SIZEOF_INT64 = 8;
    public static final int SIZEOF_RATIONAL = 8;
}
