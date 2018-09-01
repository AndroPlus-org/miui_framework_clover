// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.HighSpeedVideoConfiguration;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableHighSpeedVideoConfiguration
    implements MarshalQueryable
{
    private class MarshalerHighSpeedVideoConfiguration extends Marshaler
    {

        public int getNativeSize()
        {
            return 20;
        }

        public void marshal(HighSpeedVideoConfiguration highspeedvideoconfiguration, ByteBuffer bytebuffer)
        {
            bytebuffer.putInt(highspeedvideoconfiguration.getWidth());
            bytebuffer.putInt(highspeedvideoconfiguration.getHeight());
            bytebuffer.putInt(highspeedvideoconfiguration.getFpsMin());
            bytebuffer.putInt(highspeedvideoconfiguration.getFpsMax());
            bytebuffer.putInt(highspeedvideoconfiguration.getBatchSizeMax());
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((HighSpeedVideoConfiguration)obj, bytebuffer);
        }

        public HighSpeedVideoConfiguration unmarshal(ByteBuffer bytebuffer)
        {
            return new HighSpeedVideoConfiguration(bytebuffer.getInt(), bytebuffer.getInt(), bytebuffer.getInt(), bytebuffer.getInt(), bytebuffer.getInt());
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableHighSpeedVideoConfiguration this$0;

        protected MarshalerHighSpeedVideoConfiguration(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableHighSpeedVideoConfiguration.this;
            super(MarshalQueryableHighSpeedVideoConfiguration.this, typereference, i);
        }
    }


    public MarshalQueryableHighSpeedVideoConfiguration()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerHighSpeedVideoConfiguration(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 1)
            flag = typereference.getType().equals(android/hardware/camera2/params/HighSpeedVideoConfiguration);
        else
            flag = false;
        return flag;
    }

    private static final int SIZE = 20;
}
