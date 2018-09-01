// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.StreamConfiguration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableStreamConfiguration
    implements MarshalQueryable
{
    private class MarshalerStreamConfiguration extends Marshaler
    {

        public int getNativeSize()
        {
            return 16;
        }

        public void marshal(StreamConfiguration streamconfiguration, ByteBuffer bytebuffer)
        {
            bytebuffer.putInt(streamconfiguration.getFormat());
            bytebuffer.putInt(streamconfiguration.getWidth());
            bytebuffer.putInt(streamconfiguration.getHeight());
            int i;
            if(streamconfiguration.isInput())
                i = 1;
            else
                i = 0;
            bytebuffer.putInt(i);
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((StreamConfiguration)obj, bytebuffer);
        }

        public StreamConfiguration unmarshal(ByteBuffer bytebuffer)
        {
            int i = bytebuffer.getInt();
            int j = bytebuffer.getInt();
            int k = bytebuffer.getInt();
            boolean flag;
            if(bytebuffer.getInt() != 0)
                flag = true;
            else
                flag = false;
            return new StreamConfiguration(i, j, k, flag);
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableStreamConfiguration this$0;

        protected MarshalerStreamConfiguration(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableStreamConfiguration.this;
            super(MarshalQueryableStreamConfiguration.this, typereference, i);
        }
    }


    public MarshalQueryableStreamConfiguration()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerStreamConfiguration(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 1)
            flag = typereference.getType().equals(android/hardware/camera2/params/StreamConfiguration);
        else
            flag = false;
        return flag;
    }

    private static final int SIZE = 16;
}
