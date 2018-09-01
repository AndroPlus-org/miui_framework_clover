// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.SizeF;
import java.nio.ByteBuffer;

public class MarshalQueryableSizeF
    implements MarshalQueryable
{
    private class MarshalerSizeF extends Marshaler
    {

        public int getNativeSize()
        {
            return 8;
        }

        public void marshal(SizeF sizef, ByteBuffer bytebuffer)
        {
            bytebuffer.putFloat(sizef.getWidth());
            bytebuffer.putFloat(sizef.getHeight());
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((SizeF)obj, bytebuffer);
        }

        public SizeF unmarshal(ByteBuffer bytebuffer)
        {
            return new SizeF(bytebuffer.getFloat(), bytebuffer.getFloat());
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableSizeF this$0;

        protected MarshalerSizeF(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableSizeF.this;
            super(MarshalQueryableSizeF.this, typereference, i);
        }
    }


    public MarshalQueryableSizeF()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerSizeF(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 2)
            flag = android/util/SizeF.equals(typereference.getType());
        else
            flag = false;
        return flag;
    }

    private static final int SIZE = 8;
}
