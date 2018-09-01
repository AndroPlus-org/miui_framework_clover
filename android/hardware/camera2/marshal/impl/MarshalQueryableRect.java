// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.graphics.Rect;
import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableRect
    implements MarshalQueryable
{
    private class MarshalerRect extends Marshaler
    {

        public int getNativeSize()
        {
            return 16;
        }

        public void marshal(Rect rect, ByteBuffer bytebuffer)
        {
            bytebuffer.putInt(rect.left);
            bytebuffer.putInt(rect.top);
            bytebuffer.putInt(rect.width());
            bytebuffer.putInt(rect.height());
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((Rect)obj, bytebuffer);
        }

        public Rect unmarshal(ByteBuffer bytebuffer)
        {
            int i = bytebuffer.getInt();
            int j = bytebuffer.getInt();
            return new Rect(i, j, i + bytebuffer.getInt(), j + bytebuffer.getInt());
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableRect this$0;

        protected MarshalerRect(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableRect.this;
            super(MarshalQueryableRect.this, typereference, i);
        }
    }


    public MarshalQueryableRect()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerRect(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 1)
            flag = android/graphics/Rect.equals(typereference.getType());
        else
            flag = false;
        return flag;
    }

    private static final int SIZE = 16;
}
