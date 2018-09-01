// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class MarshalQueryableString
    implements MarshalQueryable
{
    private class MarshalerString extends Marshaler
    {

        public volatile int calculateMarshalSize(Object obj)
        {
            return calculateMarshalSize((String)obj);
        }

        public int calculateMarshalSize(String s)
        {
            return s.getBytes(PreloadHolder.UTF8_CHARSET).length + 1;
        }

        public int getNativeSize()
        {
            return NATIVE_SIZE_DYNAMIC;
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((String)obj, bytebuffer);
        }

        public void marshal(String s, ByteBuffer bytebuffer)
        {
            bytebuffer.put(s.getBytes(PreloadHolder.UTF8_CHARSET));
            bytebuffer.put((byte)0);
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        public String unmarshal(ByteBuffer bytebuffer)
        {
            bytebuffer.mark();
            boolean flag = false;
            int i = 0;
            do
            {
label0:
                {
                    boolean flag1 = flag;
                    if(bytebuffer.hasRemaining())
                    {
                        if(bytebuffer.get() != 0)
                            break label0;
                        flag1 = true;
                    }
                    if(!flag1)
                    {
                        throw new UnsupportedOperationException("Strings must be null-terminated");
                    } else
                    {
                        bytebuffer.reset();
                        byte abyte0[] = new byte[i + 1];
                        bytebuffer.get(abyte0, 0, i + 1);
                        return new String(abyte0, 0, i, PreloadHolder.UTF8_CHARSET);
                    }
                }
                i++;
            } while(true);
        }

        final MarshalQueryableString this$0;

        protected MarshalerString(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableString.this;
            super(MarshalQueryableString.this, typereference, i);
        }
    }

    private static class PreloadHolder
    {

        public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");


        private PreloadHolder()
        {
        }
    }


    public MarshalQueryableString()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerString(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        boolean flag = false;
        if(i == 0)
            flag = java/lang/String.equals(typereference.getType());
        return flag;
    }

    private static final boolean DEBUG = false;
    private static final byte NUL = 0;
    private static final String TAG = android/hardware/camera2/marshal/impl/MarshalQueryableString.getSimpleName();

}
