// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.StreamConfigurationDuration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableStreamConfigurationDuration
    implements MarshalQueryable
{
    private class MarshalerStreamConfigurationDuration extends Marshaler
    {

        public int getNativeSize()
        {
            return 32;
        }

        public void marshal(StreamConfigurationDuration streamconfigurationduration, ByteBuffer bytebuffer)
        {
            bytebuffer.putLong((long)streamconfigurationduration.getFormat() & 0xffffffffL);
            bytebuffer.putLong(streamconfigurationduration.getWidth());
            bytebuffer.putLong(streamconfigurationduration.getHeight());
            bytebuffer.putLong(streamconfigurationduration.getDuration());
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((StreamConfigurationDuration)obj, bytebuffer);
        }

        public StreamConfigurationDuration unmarshal(ByteBuffer bytebuffer)
        {
            return new StreamConfigurationDuration((int)bytebuffer.getLong(), (int)bytebuffer.getLong(), (int)bytebuffer.getLong(), bytebuffer.getLong());
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableStreamConfigurationDuration this$0;

        protected MarshalerStreamConfigurationDuration(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableStreamConfigurationDuration.this;
            super(MarshalQueryableStreamConfigurationDuration.this, typereference, i);
        }
    }


    public MarshalQueryableStreamConfigurationDuration()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerStreamConfigurationDuration(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 3)
            flag = android/hardware/camera2/params/StreamConfigurationDuration.equals(typereference.getType());
        else
            flag = false;
        return flag;
    }

    private static final long MASK_UNSIGNED_INT = 0xffffffffL;
    private static final int SIZE = 32;
}
