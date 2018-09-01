// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal.impl;

import android.hardware.camera2.marshal.MarshalQueryable;
import android.hardware.camera2.marshal.Marshaler;
import android.hardware.camera2.utils.TypeReference;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class MarshalQueryableParcelable
    implements MarshalQueryable
{
    private class MarshalerParcelable extends Marshaler
    {

        public int calculateMarshalSize(Parcelable parcelable)
        {
            Parcel parcel = Parcel.obtain();
            int i;
            parcelable.writeToParcel(parcel, 0);
            i = parcel.marshall().length;
            parcel.recycle();
            return i;
            parcelable;
            parcel.recycle();
            throw parcelable;
        }

        public volatile int calculateMarshalSize(Object obj)
        {
            return calculateMarshalSize((Parcelable)obj);
        }

        public int getNativeSize()
        {
            return NATIVE_SIZE_DYNAMIC;
        }

        public void marshal(Parcelable parcelable, ByteBuffer bytebuffer)
        {
            Parcel parcel = Parcel.obtain();
            parcelable.writeToParcel(parcel, 0);
            if(parcel.hasFileDescriptors())
            {
                bytebuffer = JVM INSTR new #103 <Class UnsupportedOperationException>;
                StringBuilder stringbuilder = JVM INSTR new #105 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                bytebuffer.UnsupportedOperationException(stringbuilder.append("Parcelable ").append(parcelable).append(" must not have file descriptors").toString());
                throw bytebuffer;
            }
            break MISSING_BLOCK_LABEL_65;
            parcelable;
            parcel.recycle();
            throw parcelable;
            byte abyte0[] = parcel.marshall();
            parcel.recycle();
            if(abyte0.length == 0)
            {
                throw new AssertionError((new StringBuilder()).append("No data marshaled for ").append(parcelable).toString());
            } else
            {
                bytebuffer.put(abyte0);
                return;
            }
        }

        public volatile void marshal(Object obj, ByteBuffer bytebuffer)
        {
            marshal((Parcelable)obj, bytebuffer);
        }

        public Parcelable unmarshal(ByteBuffer bytebuffer)
        {
            Parcel parcel;
            bytebuffer.mark();
            parcel = Parcel.obtain();
            int i;
            Parcelable parcelable;
            i = bytebuffer.remaining();
            byte abyte0[] = new byte[i];
            bytebuffer.get(abyte0);
            parcel.unmarshall(abyte0, 0, i);
            parcel.setDataPosition(0);
            parcelable = (Parcelable)mCreator.createFromParcel(parcel);
            i = parcel.dataPosition();
            if(i != 0)
                break MISSING_BLOCK_LABEL_105;
            bytebuffer = JVM INSTR new #57  <Class AssertionError>;
            StringBuilder stringbuilder = JVM INSTR new #105 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            bytebuffer.AssertionError(stringbuilder.append("No data marshaled for ").append(parcelable).toString());
            throw bytebuffer;
            bytebuffer;
            parcel.recycle();
            throw bytebuffer;
            bytebuffer.reset();
            bytebuffer.position(bytebuffer.position() + i);
            bytebuffer = (Parcelable)mClass.cast(parcelable);
            parcel.recycle();
            return bytebuffer;
        }

        public volatile Object unmarshal(ByteBuffer bytebuffer)
        {
            return unmarshal(bytebuffer);
        }

        private final Class mClass;
        private final android.os.Parcelable.Creator mCreator;
        final MarshalQueryableParcelable this$0;

        protected MarshalerParcelable(TypeReference typereference, int i)
        {
            this$0 = MarshalQueryableParcelable.this;
            super(MarshalQueryableParcelable.this, typereference, i);
            mClass = typereference.getRawType();
            try
            {
                marshalqueryableparcelable = mClass.getDeclaredField("CREATOR");
            }
            // Misplaced declaration of an exception variable
            catch(MarshalQueryableParcelable marshalqueryableparcelable)
            {
                throw new AssertionError(MarshalQueryableParcelable.this);
            }
            try
            {
                mCreator = (android.os.Parcelable.Creator)get(null);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(MarshalQueryableParcelable marshalqueryableparcelable)
            {
                throw new AssertionError(MarshalQueryableParcelable.this);
            }
            // Misplaced declaration of an exception variable
            catch(MarshalQueryableParcelable marshalqueryableparcelable)
            {
                throw new AssertionError(MarshalQueryableParcelable.this);
            }
        }
    }


    public MarshalQueryableParcelable()
    {
    }

    public Marshaler createMarshaler(TypeReference typereference, int i)
    {
        return new MarshalerParcelable(typereference, i);
    }

    public boolean isTypeMappingSupported(TypeReference typereference, int i)
    {
        return android/os/Parcelable.isAssignableFrom(typereference.getRawType());
    }

    private static final boolean DEBUG = false;
    private static final String FIELD_CREATOR = "CREATOR";
    private static final String TAG = "MarshalParcelable";
}
