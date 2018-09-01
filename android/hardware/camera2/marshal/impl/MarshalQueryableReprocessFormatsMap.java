// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.ReprocessFormatsMap;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class MarshalQueryableReprocessFormatsMap
    implements MarshalQueryable
{
    private class MarshalerReprocessFormatsMap extends Marshaler
    {

        public int calculateMarshalSize(ReprocessFormatsMap reprocessformatsmap)
        {
            int i = 0;
            int ai[] = reprocessformatsmap.getInputs();
            int j = 0;
            for(int k = ai.length; j < k; j++)
                i = i + 1 + 1 + reprocessformatsmap.getOutputs(ai[j]).length;

            return i * 4;
        }

        public volatile int calculateMarshalSize(Object obj)
        {
            return calculateMarshalSize((ReprocessFormatsMap)obj);
        }

        public int getNativeSize()
        {
            return NATIVE_SIZE_DYNAMIC;
        }

        public void marshal(ReprocessFormatsMap reprocessformatsmap, ByteBuffer bytebuffer)
        {
            int ai[] = StreamConfigurationMap.imageFormatToInternal(reprocessformatsmap.getInputs());
            int i = ai.length;
            for(int j = 0; j < i; j++)
            {
                int k = ai[j];
                bytebuffer.putInt(k);
                int ai1[] = StreamConfigurationMap.imageFormatToInternal(reprocessformatsmap.getOutputs(k));
                bytebuffer.putInt(ai1.length);
                int i1 = ai1.length;
                for(int l = 0; l < i1; l++)
                    bytebuffer.putInt(ai1[l]);

            }

        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((ReprocessFormatsMap)obj, bytebuffer);
        }

        public ReprocessFormatsMap unmarshal(ByteBuffer bytebuffer)
        {
            int i = bytebuffer.remaining() / 4;
            if(bytebuffer.remaining() % 4 != 0)
            {
                throw new AssertionError("ReprocessFormatsMap was not TYPE_INT32");
            } else
            {
                int ai[] = new int[i];
                bytebuffer.asIntBuffer().get(ai);
                return new ReprocessFormatsMap(ai);
            }
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableReprocessFormatsMap this$0;

        protected MarshalerReprocessFormatsMap(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableReprocessFormatsMap.this;
            super(MarshalQueryableReprocessFormatsMap.this, typereference, i);
        }
    }


    public MarshalQueryableReprocessFormatsMap()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerReprocessFormatsMap(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 1)
            flag = typereference.getType().equals(android/hardware/camera2/params/ReprocessFormatsMap);
        else
            flag = false;
        return flag;
    }
}
