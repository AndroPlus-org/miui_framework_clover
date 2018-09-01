// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.RggbChannelVector;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableRggbChannelVector
    implements MarshalQueryable
{
    private class MarshalerRggbChannelVector extends Marshaler
    {

        public int getNativeSize()
        {
            return 16;
        }

        public void marshal(RggbChannelVector rggbchannelvector, ByteBuffer bytebuffer)
        {
            for(int i = 0; i < 4; i++)
                bytebuffer.putFloat(rggbchannelvector.getComponent(i));

        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((RggbChannelVector)obj, bytebuffer);
        }

        public RggbChannelVector unmarshal(ByteBuffer bytebuffer)
        {
            return new RggbChannelVector(bytebuffer.getFloat(), bytebuffer.getFloat(), bytebuffer.getFloat(), bytebuffer.getFloat());
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableRggbChannelVector this$0;

        protected MarshalerRggbChannelVector(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableRggbChannelVector.this;
            super(MarshalQueryableRggbChannelVector.this, typereference, i);
        }
    }


    public MarshalQueryableRggbChannelVector()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerRggbChannelVector(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 2)
            flag = android/hardware/camera2/params/RggbChannelVector.equals(typereference.getType());
        else
            flag = false;
        return flag;
    }

    private static final int SIZE = 16;
}
