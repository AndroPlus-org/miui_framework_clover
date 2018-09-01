// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableBoolean
    implements MarshalQueryable
{
    private class MarshalerBoolean extends Marshaler
    {

        public int getNativeSize()
        {
            return 1;
        }

        public void marshal(Boolean boolean1, ByteBuffer bytebuffer)
        {
            int i;
            if(boolean1.booleanValue())
                i = 1;
            else
                i = 0;
            bytebuffer.put((byte)i);
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((Boolean)obj, bytebuffer);
        }

        public Boolean unmarshal(ByteBuffer bytebuffer)
        {
            boolean flag = false;
            if(bytebuffer.get() != 0)
                flag = true;
            return Boolean.valueOf(flag);
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableBoolean this$0;

        protected MarshalerBoolean(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableBoolean.this;
            super(MarshalQueryableBoolean.this, typereference, i);
        }
    }


    public MarshalQueryableBoolean()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerBoolean(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag1;
label0:
        {
            boolean flag = false;
            if(!java/lang/Boolean.equals(typereference.getType()))
            {
                flag1 = flag;
                if(!Boolean.TYPE.equals(typereference.getType()))
                    break label0;
            }
            flag1 = flag;
            if(i == 0)
                flag1 = true;
        }
        return flag1;
    }
}
