// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.*;
import android.hardware.camera2.utils.TypeReference;
import android.util.Range;
import java.lang.reflect.*;
import java.nio.ByteBuffer;

public class MarshalQueryableRange
    implements MarshalQueryable
{
    private class MarshalerRange extends Marshaler
    {

        public int calculateMarshalSize(Range range)
        {
            int i = getNativeSize();
            if(i != NATIVE_SIZE_DYNAMIC)
                return i;
            else
                return mNestedTypeMarshaler.calculateMarshalSize(range.getLower()) + mNestedTypeMarshaler.calculateMarshalSize(range.getUpper());
        }

        public volatile int calculateMarshalSize(Object obj)
        {
            return calculateMarshalSize((Range)obj);
        }

        public int getNativeSize()
        {
            int i = mNestedTypeMarshaler.getNativeSize();
            if(i != NATIVE_SIZE_DYNAMIC)
                return i * 2;
            else
                return NATIVE_SIZE_DYNAMIC;
        }

        public void marshal(Range range, ByteBuffer bytebuffer)
        {
            mNestedTypeMarshaler.marshal(range.getLower(), bytebuffer);
            mNestedTypeMarshaler.marshal(range.getUpper(), bytebuffer);
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((Range)obj, bytebuffer);
        }

        public Range unmarshal(ByteBuffer bytebuffer)
        {
            Comparable comparable = (Comparable)mNestedTypeMarshaler.unmarshal(bytebuffer);
            bytebuffer = (Comparable)mNestedTypeMarshaler.unmarshal(bytebuffer);
            try
            {
                bytebuffer = (Range)mConstructor.newInstance(new Object[] {
                    comparable, bytebuffer
                });
            }
            // Misplaced declaration of an exception variable
            catch(ByteBuffer bytebuffer)
            {
                throw new AssertionError(bytebuffer);
            }
            // Misplaced declaration of an exception variable
            catch(ByteBuffer bytebuffer)
            {
                throw new AssertionError(bytebuffer);
            }
            // Misplaced declaration of an exception variable
            catch(ByteBuffer bytebuffer)
            {
                throw new AssertionError(bytebuffer);
            }
            // Misplaced declaration of an exception variable
            catch(ByteBuffer bytebuffer)
            {
                throw new AssertionError(bytebuffer);
            }
            return bytebuffer;
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        private final Class mClass;
        private final Constructor mConstructor;
        private final Marshaler mNestedTypeMarshaler;
        final MarshalQueryableRange this$0;

        protected MarshalerRange(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableRange.this;
            super(MarshalQueryableRange.this, typereference, i);
            mClass = typereference.getRawType();
            try
            {
                marshalqueryablerange = (ParameterizedType)typereference.getType();
            }
            // Misplaced declaration of an exception variable
            catch(MarshalQueryableRange marshalqueryablerange)
            {
                throw new AssertionError("Raw use of Range is not supported", MarshalQueryableRange.this);
            }
            mNestedTypeMarshaler = MarshalRegistry.getMarshaler(TypeReference.createSpecializedTypeReference(getActualTypeArguments()[0]), mNativeType);
            try
            {
                mConstructor = mClass.getConstructor(new Class[] {
                    java/lang/Comparable, java/lang/Comparable
                });
                return;
            }
            // Misplaced declaration of an exception variable
            catch(MarshalQueryableRange marshalqueryablerange)
            {
                throw new AssertionError(MarshalQueryableRange.this);
            }
        }
    }


    public MarshalQueryableRange()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerRange(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        return android/util/Range.equals(typereference.getRawType());
    }

    private static final int RANGE_COUNT = 2;
}
