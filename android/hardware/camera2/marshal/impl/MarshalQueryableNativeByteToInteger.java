// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableNativeByteToInteger
    implements MarshalQueryable
{
    private class MarshalerNativeByteToInteger extends Marshaler
    {

        public int getNativeSize()
        {
            return 1;
        }

        public void marshal(Integer integer, ByteBuffer bytebuffer)
        {
            bytebuffer.put((byte)integer.intValue());
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((Integer)obj, bytebuffer);
        }

        public Integer unmarshal(ByteBuffer bytebuffer)
        {
            return Integer.valueOf(bytebuffer.get() & 0xff);
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableNativeByteToInteger this$0;

        protected MarshalerNativeByteToInteger(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableNativeByteToInteger.this;
            super(MarshalQueryableNativeByteToInteger.this, typereference, i);
        }
    }


    public MarshalQueryableNativeByteToInteger()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerNativeByteToInteger(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag1;
label0:
        {
            boolean flag = false;
            if(!java/lang/Integer.equals(typereference.getType()))
            {
                flag1 = flag;
                if(!Integer.TYPE.equals(typereference.getType()))
                    break label0;
            }
            flag1 = flag;
            if(i == 0)
                flag1 = true;
        }
        return flag1;
    }

    private static final int UINT8_MASK = 255;
}
