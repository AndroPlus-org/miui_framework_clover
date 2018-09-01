// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.*;
import android.hardware.camera2.utils.TypeReference;
import android.util.Pair;
import java.lang.reflect.*;
import java.nio.ByteBuffer;

public class MarshalQueryablePair
    implements MarshalQueryable
{
    private class MarshalerPair extends Marshaler
    {

        public int calculateMarshalSize(Pair pair)
        {
            int i = getNativeSize();
            if(i != NATIVE_SIZE_DYNAMIC)
                return i;
            else
                return mNestedTypeMarshalerFirst.calculateMarshalSize(pair.first) + mNestedTypeMarshalerSecond.calculateMarshalSize(pair.second);
        }

        public volatile int calculateMarshalSize(Object obj)
        {
            return calculateMarshalSize((Pair)obj);
        }

        public int getNativeSize()
        {
            int i = mNestedTypeMarshalerFirst.getNativeSize();
            int j = mNestedTypeMarshalerSecond.getNativeSize();
            if(i != NATIVE_SIZE_DYNAMIC && j != NATIVE_SIZE_DYNAMIC)
                return i + j;
            else
                return NATIVE_SIZE_DYNAMIC;
        }

        public void marshal(Pair pair, ByteBuffer bytebuffer)
        {
            if(pair.first == null)
                throw new UnsupportedOperationException("Pair#first must not be null");
            if(pair.second == null)
            {
                throw new UnsupportedOperationException("Pair#second must not be null");
            } else
            {
                mNestedTypeMarshalerFirst.marshal(pair.first, bytebuffer);
                mNestedTypeMarshalerSecond.marshal(pair.second, bytebuffer);
                return;
            }
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((Pair)obj, bytebuffer);
        }

        public Pair unmarshal(ByteBuffer bytebuffer)
        {
            Object obj = mNestedTypeMarshalerFirst.unmarshal(bytebuffer);
            bytebuffer = ((ByteBuffer) (mNestedTypeMarshalerSecond.unmarshal(bytebuffer)));
            try
            {
                bytebuffer = (Pair)mConstructor.newInstance(new Object[] {
                    obj, bytebuffer
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
        private final Marshaler mNestedTypeMarshalerFirst;
        private final Marshaler mNestedTypeMarshalerSecond;
        final MarshalQueryablePair this$0;

        protected MarshalerPair(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryablePair.this;
            super(MarshalQueryablePair.this, typereference, i);
            mClass = typereference.getRawType();
            try
            {
                marshalqueryablepair = (ParameterizedType)typereference.getType();
            }
            // Misplaced declaration of an exception variable
            catch(MarshalQueryablePair marshalqueryablepair)
            {
                throw new AssertionError("Raw use of Pair is not supported", MarshalQueryablePair.this);
            }
            mNestedTypeMarshalerFirst = MarshalRegistry.getMarshaler(TypeReference.createSpecializedTypeReference(getActualTypeArguments()[0]), mNativeType);
            mNestedTypeMarshalerSecond = MarshalRegistry.getMarshaler(TypeReference.createSpecializedTypeReference(getActualTypeArguments()[1]), mNativeType);
            try
            {
                mConstructor = mClass.getConstructor(new Class[] {
                    java/lang/Object, java/lang/Object
                });
                return;
            }
            // Misplaced declaration of an exception variable
            catch(MarshalQueryablePair marshalqueryablepair)
            {
                throw new AssertionError(MarshalQueryablePair.this);
            }
        }
    }


    public MarshalQueryablePair()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerPair(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        return android/util/Pair.equals(typereference.getRawType());
    }
}
