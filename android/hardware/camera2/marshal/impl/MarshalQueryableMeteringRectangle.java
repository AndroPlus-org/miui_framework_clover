// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableMeteringRectangle
    implements MarshalQueryable
{
    private class MarshalerMeteringRectangle extends Marshaler
    {

        public int getNativeSize()
        {
            return 20;
        }

        public void marshal(MeteringRectangle meteringrectangle, ByteBuffer bytebuffer)
        {
            int i = meteringrectangle.getX();
            int j = meteringrectangle.getY();
            int k = meteringrectangle.getWidth();
            int l = meteringrectangle.getHeight();
            int i1 = meteringrectangle.getMeteringWeight();
            bytebuffer.putInt(i);
            bytebuffer.putInt(j);
            bytebuffer.putInt(i + k);
            bytebuffer.putInt(j + l);
            bytebuffer.putInt(i1);
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((MeteringRectangle)obj, bytebuffer);
        }

        public MeteringRectangle unmarshal(ByteBuffer bytebuffer)
        {
            int i = bytebuffer.getInt();
            int j = bytebuffer.getInt();
            return new MeteringRectangle(i, j, bytebuffer.getInt() - i, bytebuffer.getInt() - j, bytebuffer.getInt());
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableMeteringRectangle this$0;

        protected MarshalerMeteringRectangle(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableMeteringRectangle.this;
            super(MarshalQueryableMeteringRectangle.this, typereference, i);
        }
    }


    public MarshalQueryableMeteringRectangle()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerMeteringRectangle(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 1)
            flag = android/hardware/camera2/params/MeteringRectangle.equals(typereference.getType());
        else
            flag = false;
        return flag;
    }

    private static final int SIZE = 20;
}
