// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.params.BlackLevelPattern;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;

public class MarshalQueryableBlackLevelPattern
    implements MarshalQueryable
{
    private class MarshalerBlackLevelPattern extends Marshaler
    {

        public int getNativeSize()
        {
            return 16;
        }

        public void marshal(BlackLevelPattern blacklevelpattern, ByteBuffer bytebuffer)
        {
            for(int i = 0; i < 2; i++)
            {
                for(int j = 0; j < 2; j++)
                    bytebuffer.putInt(blacklevelpattern.getOffsetForIndex(j, i));

            }

        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((BlackLevelPattern)obj, bytebuffer);
        }

        public BlackLevelPattern unmarshal(ByteBuffer bytebuffer)
        {
            int ai[] = new int[4];
            for(int i = 0; i < 4; i++)
                ai[i] = bytebuffer.getInt();

            return new BlackLevelPattern(ai);
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        final MarshalQueryableBlackLevelPattern this$0;

        protected MarshalerBlackLevelPattern(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableBlackLevelPattern.this;
            super(MarshalQueryableBlackLevelPattern.this, typereference, i);
        }
    }


    public MarshalQueryableBlackLevelPattern()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerBlackLevelPattern(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag;
        if(i == 1)
            flag = android/hardware/camera2/params/BlackLevelPattern.equals(typereference.getType());
        else
            flag = false;
        return flag;
    }

    private static final int SIZE = 16;
}
