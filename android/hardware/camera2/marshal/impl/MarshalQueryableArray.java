// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.*;
import android.hardware.camera2.utils.TypeReference;
import android.util.Log;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class MarshalQueryableArray
    implements MarshalQueryable
{
    private class MarshalerArray extends Marshaler
    {

        private int calculateElementMarshalSize(Marshaler marshaler, Object obj, int i)
        {
            return marshaler.calculateMarshalSize(Array.get(obj, i));
        }

        private Object copyListToArray(ArrayList arraylist, Object obj)
        {
            return ((Object) (arraylist.toArray((Object[])obj)));
        }

        private void marshalArrayElement(Marshaler marshaler, ByteBuffer bytebuffer, Object obj, int i)
        {
            marshaler.marshal(Array.get(obj, i), bytebuffer);
        }

        public int calculateMarshalSize(Object obj)
        {
            int i = mComponentMarshaler.getNativeSize();
            int k = Array.getLength(obj);
            if(i != Marshaler.NATIVE_SIZE_DYNAMIC)
                return i * k;
            int l = 0;
            for(int j = 0; j < k; j++)
                l += calculateElementMarshalSize(mComponentMarshaler, obj, j);

            return l;
        }

        public int getNativeSize()
        {
            return NATIVE_SIZE_DYNAMIC;
        }

        public void marshal(Object obj, ByteBuffer bytebuffer)
        {
            int i = Array.getLength(obj);
            for(int j = 0; j < i; j++)
                marshalArrayElement(mComponentMarshaler, bytebuffer, obj, j);

        }

        public Object unmarshal(ByteBuffer bytebuffer)
        {
            int i = mComponentMarshaler.getNativeSize();
            Object obj1;
            if(i != Marshaler.NATIVE_SIZE_DYNAMIC)
            {
                int k = bytebuffer.remaining();
                int l = k / i;
                if(k % i != 0)
                    throw new UnsupportedOperationException((new StringBuilder()).append("Arrays for ").append(mTypeReference).append(" must be packed tighly into a multiple of ").append(i).append("; but there are ").append(k % i).append(" left over bytes").toString());
                Object obj = Array.newInstance(mComponentClass, l);
                i = 0;
                do
                {
                    obj1 = obj;
                    if(i >= l)
                        break;
                    Array.set(obj, i, mComponentMarshaler.unmarshal(bytebuffer));
                    i++;
                } while(true);
            } else
            {
                obj1 = new ArrayList();
                for(; bytebuffer.hasRemaining(); ((ArrayList) (obj1)).add(mComponentMarshaler.unmarshal(bytebuffer)));
                int j = ((ArrayList) (obj1)).size();
                obj1 = copyListToArray(((ArrayList) (obj1)), Array.newInstance(mComponentClass, j));
            }
            if(bytebuffer.remaining() != 0)
                Log.e(MarshalQueryableArray._2D_get0(), (new StringBuilder()).append("Trailing bytes (").append(bytebuffer.remaining()).append(") left over after unpacking ").append(mClass).toString());
            return mClass.cast(obj1);
        }

        private final Class mClass;
        private final Class mComponentClass;
        private final Marshaler mComponentMarshaler;
        final MarshalQueryableArray this$0;

        protected MarshalerArray(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableArray.this;
            super(MarshalQueryableArray.this, typereference, i);
            mClass = typereference.getRawType();
            marshalqueryablearray = typereference.getComponentType();
            mComponentMarshaler = MarshalRegistry.getMarshaler(MarshalQueryableArray.this, mNativeType);
            mComponentClass = getRawType();
        }
    }


    static String _2D_get0()
    {
        return TAG;
    }

    public MarshalQueryableArray()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerArray(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        return typereference.getRawType().isArray();
    }

    private static final boolean DEBUG = false;
    private static final String TAG = android/hardware/camera2/marshal/impl/MarshalQueryableArray.getSimpleName();

}
