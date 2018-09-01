// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.*;
import android.hardware.camera2.utils.TypeReference;
import android.util.Rational;
import java.nio.ByteBuffer;

public final class MarshalQueryablePrimitive
    implements MarshalQueryable
{
    private class MarshalerPrimitive extends Marshaler
    {

        private void marshalPrimitive(byte byte0, ByteBuffer bytebuffer)
        {
            bytebuffer.put(byte0);
        }

        private void marshalPrimitive(double d, ByteBuffer bytebuffer)
        {
            bytebuffer.putDouble(d);
        }

        private void marshalPrimitive(float f, ByteBuffer bytebuffer)
        {
            bytebuffer.putFloat(f);
        }

        private void marshalPrimitive(int i, ByteBuffer bytebuffer)
        {
            bytebuffer.putInt(i);
        }

        private void marshalPrimitive(long l, ByteBuffer bytebuffer)
        {
            bytebuffer.putLong(l);
        }

        private void marshalPrimitive(Rational rational, ByteBuffer bytebuffer)
        {
            bytebuffer.putInt(rational.getNumerator());
            bytebuffer.putInt(rational.getDenominator());
        }

        private Object unmarshalObject(ByteBuffer bytebuffer)
        {
            switch(mNativeType)
            {
            default:
                throw new UnsupportedOperationException((new StringBuilder()).append("Can't unmarshal native type ").append(mNativeType).toString());

            case 1: // '\001'
                return Integer.valueOf(bytebuffer.getInt());

            case 2: // '\002'
                return Float.valueOf(bytebuffer.getFloat());

            case 3: // '\003'
                return Long.valueOf(bytebuffer.getLong());

            case 5: // '\005'
                return new Rational(bytebuffer.getInt(), bytebuffer.getInt());

            case 4: // '\004'
                return Double.valueOf(bytebuffer.getDouble());

            case 0: // '\0'
                return Byte.valueOf(bytebuffer.get());
            }
        }

        public int calculateMarshalSize(Object obj)
        {
            return MarshalHelpers.getPrimitiveTypeSize(mNativeType);
        }

        public int getNativeSize()
        {
            return MarshalHelpers.getPrimitiveTypeSize(mNativeType);
        }

        public void marshal(Object obj, ByteBuffer bytebuffer)
        {
            if(obj instanceof Integer)
            {
                MarshalHelpers.checkNativeTypeEquals(1, mNativeType);
                marshalPrimitive(((Integer)obj).intValue(), bytebuffer);
            } else
            if(obj instanceof Float)
            {
                MarshalHelpers.checkNativeTypeEquals(2, mNativeType);
                marshalPrimitive(((Float)obj).floatValue(), bytebuffer);
            } else
            if(obj instanceof Long)
            {
                MarshalHelpers.checkNativeTypeEquals(3, mNativeType);
                marshalPrimitive(((Long)obj).longValue(), bytebuffer);
            } else
            if(obj instanceof Rational)
            {
                MarshalHelpers.checkNativeTypeEquals(5, mNativeType);
                marshalPrimitive((Rational)obj, bytebuffer);
            } else
            if(obj instanceof Double)
            {
                MarshalHelpers.checkNativeTypeEquals(4, mNativeType);
                marshalPrimitive(((Double)obj).doubleValue(), bytebuffer);
            } else
            if(obj instanceof Byte)
            {
                MarshalHelpers.checkNativeTypeEquals(0, mNativeType);
                marshalPrimitive(((Byte)obj).byteValue(), bytebuffer);
            } else
            {
                throw new UnsupportedOperationException((new StringBuilder()).append("Can't marshal managed type ").append(mTypeReference).toString());
            }
        }

        public Object unmarshal(ByteBuffer bytebuffer)
        {
            return mClass.cast(unmarshalObject(bytebuffer));
        }

        private final Class mClass;
        final MarshalQueryablePrimitive this$0;

        protected MarshalerPrimitive(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryablePrimitive.this;
            super(MarshalQueryablePrimitive.this, typereference, i);
            mClass = MarshalHelpers.wrapClassIfPrimitive(typereference.getRawType());
        }
    }


    public MarshalQueryablePrimitive()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerPrimitive(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag = true;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        boolean flag5 = true;
        if(typereference.getType() instanceof Class)
        {
            typereference = (Class)typereference.getType();
            if(typereference == Byte.TYPE || typereference == java/lang/Byte)
            {
                if(i != 0)
                    flag5 = false;
                return flag5;
            }
            if(typereference == Integer.TYPE || typereference == java/lang/Integer)
            {
                boolean flag6;
                if(i == 1)
                    flag6 = flag;
                else
                    flag6 = false;
                return flag6;
            }
            if(typereference == Float.TYPE || typereference == java/lang/Float)
            {
                boolean flag7;
                if(i == 2)
                    flag7 = flag1;
                else
                    flag7 = false;
                return flag7;
            }
            if(typereference == Long.TYPE || typereference == java/lang/Long)
            {
                boolean flag8;
                if(i == 3)
                    flag8 = flag2;
                else
                    flag8 = false;
                return flag8;
            }
            if(typereference == Double.TYPE || typereference == java/lang/Double)
            {
                boolean flag9;
                if(i == 4)
                    flag9 = flag3;
                else
                    flag9 = false;
                return flag9;
            }
            if(typereference == android/util/Rational)
            {
                boolean flag10;
                if(i == 5)
                    flag10 = flag4;
                else
                    flag10 = false;
                return flag10;
            }
        }
        return false;
    }
}
