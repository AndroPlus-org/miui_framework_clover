// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.*;
import android.hardware.camera2.utils.TypeReference;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class MarshalQueryableEnum
    implements MarshalQueryable
{
    private class MarshalerEnum extends Marshaler
    {

        public int getNativeSize()
        {
            return MarshalHelpers.getPrimitiveTypeSize(mNativeType);
        }

        public void marshal(Enum enum, ByteBuffer bytebuffer)
        {
            int i = MarshalQueryableEnum._2D_wrap0(enum);
            if(mNativeType == 1)
                bytebuffer.putInt(i);
            else
            if(mNativeType == 0)
            {
                if(i < 0 || i > 255)
                    throw new UnsupportedOperationException(String.format("Enum value %x too large to fit into unsigned byte", new Object[] {
                        Integer.valueOf(i)
                    }));
                bytebuffer.put((byte)i);
            } else
            {
                throw new AssertionError();
            }
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((Enum)obj, bytebuffer);
        }

        public Enum unmarshal(ByteBuffer bytebuffer)
        {
            mNativeType;
            JVM INSTR tableswitch 0 1: default 28
        //                       0 52
        //                       1 38;
               goto _L1 _L2 _L3
_L1:
            throw new AssertionError("Unexpected native type; impossible since its not supported");
_L3:
            int i = bytebuffer.getInt();
_L5:
            return MarshalQueryableEnum._2D_wrap1(mClass, i);
_L2:
            i = bytebuffer.get() & 0xff;
            if(true) goto _L5; else goto _L4
_L4:
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        private final Class mClass;
        final MarshalQueryableEnum this$0;

        protected MarshalerEnum(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableEnum.this;
            super(MarshalQueryableEnum.this, typereference, i);
            mClass = typereference.getRawType();
        }
    }


    static int _2D_wrap0(Enum enum)
    {
        return getEnumValue(enum);
    }

    static Enum _2D_wrap1(Class class1, int i)
    {
        return getEnumFromValue(class1, i);
    }

    public MarshalQueryableEnum()
    {
    }

    private static Enum getEnumFromValue(Class class1, int i)
    {
        boolean flag;
        int ai[];
        byte byte0;
        int j;
        flag = true;
        ai = (int[])sEnumValues.get(class1);
        if(ai == null)
            break MISSING_BLOCK_LABEL_113;
        byte0 = -1;
        j = 0;
_L5:
        int k = byte0;
        if(j >= ai.length) goto _L2; else goto _L1
_L1:
        if(ai[j] != i) goto _L4; else goto _L3
_L3:
        k = j;
_L2:
        Enum aenum[] = (Enum[])class1.getEnumConstants();
        if(k < 0 || k >= aenum.length)
        {
            if(ai == null)
                flag = false;
            throw new IllegalArgumentException(String.format("Argument 'value' (%d) was not a valid enum value for type %s (registered? %b)", new Object[] {
                Integer.valueOf(i), class1, Boolean.valueOf(flag)
            }));
        } else
        {
            return aenum[k];
        }
_L4:
        j++;
          goto _L5
        k = i;
          goto _L2
    }

    private static int getEnumValue(Enum enum)
    {
        int ai[] = (int[])sEnumValues.get(enum.getClass());
        int i = enum.ordinal();
        if(ai != null)
            return ai[i];
        else
            return i;
    }

    public static void registerEnumValues(Class class1, int ai[])
    {
        if(((Enum[])class1.getEnumConstants()).length != ai.length)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Expected values array to be the same size as the enumTypes values ").append(ai.length).append(" for type ").append(class1).toString());
        } else
        {
            sEnumValues.put(class1, ai);
            return;
        }
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerEnum(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        if(i != 1 && i != 0 || !(typereference.getType() instanceof Class))
            break MISSING_BLOCK_LABEL_88;
        typereference = (Class)typereference.getType();
        if(!typereference.isEnum())
            break MISSING_BLOCK_LABEL_88;
        typereference.getDeclaredConstructor(new Class[] {
            java/lang/String, Integer.TYPE
        });
        return true;
        Object obj;
        obj;
        Log.e(TAG, (new StringBuilder()).append("Can't marshal class ").append(typereference).append("; not accessible").toString());
_L2:
        return false;
        obj;
        Log.e(TAG, (new StringBuilder()).append("Can't marshal class ").append(typereference).append("; no default constructor").toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final boolean DEBUG = false;
    private static final String TAG = android/hardware/camera2/marshal/impl/MarshalQueryableEnum.getSimpleName();
    private static final int UINT8_MASK = 255;
    private static final int UINT8_MAX = 255;
    private static final int UINT8_MIN = 0;
    private static final HashMap sEnumValues = new HashMap();

}
