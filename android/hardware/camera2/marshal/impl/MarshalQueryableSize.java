// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.util.Size;
import java.nio.ByteBuffer;

public class MarshalQueryableSize
    implements MarshalQueryable
{
    private class MarshalerSize extends Marshaler
    {

        public int getNativeSize()
        {
            return 8;
        }

        public void marshal(Size size, ByteBuffer bytebuffer)
        {
            bytebuffer.putInt(size.getWidth());
            bytebuffer.putInt(size.getHeight());
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((Size)obj, bytebuffer);
        }

        public Size unmarshal(ByteBuffer bytebuffer)
        {
            return new Size(bytebuffer.getInt(), bytebuffer.getInt());
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableSize this$0;

        protected MarshalerSize(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableSize.this;
            super(MarshalQueryableSize.this, typereference, i);
        }
    }


    public MarshalQueryableSize()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerSize(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 1)
            flag = android/util/Size.equals(typereference.getType());
        else
            flag = false;
        return flag;
    }

    private static final int SIZE = 8;
}
