// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableColorSpaceTransform
    implements MarshalQueryable
{
    private class MarshalerColorSpaceTransform extends Marshaler
    {

        public int getNativeSize()
        {
            return 72;
        }

        public void marshal(ColorSpaceTransform colorspacetransform, ByteBuffer bytebuffer)
        {
            int ai[] = new int[18];
            colorspacetransform.copyElements(ai, 0);
            for(int i = 0; i < 18; i++)
                bytebuffer.putInt(ai[i]);

        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((ColorSpaceTransform)obj, bytebuffer);
        }

        public ColorSpaceTransform unmarshal(ByteBuffer bytebuffer)
        {
            int ai[] = new int[18];
            for(int i = 0; i < 18; i++)
                ai[i] = bytebuffer.getInt();

            return new ColorSpaceTransform(ai);
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableColorSpaceTransform this$0;

        protected MarshalerColorSpaceTransform(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableColorSpaceTransform.this;
            super(MarshalQueryableColorSpaceTransform.this, typereference, i);
        }
    }


    public MarshalQueryableColorSpaceTransform()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerColorSpaceTransform(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 5)
            flag = android/hardware/camera2/params/ColorSpaceTransform.equals(typereference.getType());
        else
            flag = false;
        return flag;
    }

    private static final int ELEMENTS_INT32 = 18;
    private static final int SIZE = 72;
}
