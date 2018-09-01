// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal;

import android.hardware.camera2.utils.TypeReference;
import com.android.internal.util.Preconditions;
import java.nio.ByteBuffer;

// Referenced classes of package android.hardware.camera2.marshal:
//            MarshalHelpers, MarshalQueryable

public abstract class Marshaler
{

    protected Marshaler(MarshalQueryable marshalqueryable, TypeReference typereference, int i)
    {
        mTypeReference = (TypeReference)Preconditions.checkNotNull(typereference, "typeReference must not be null");
        mNativeType = MarshalHelpers.checkNativeType(i);
        if(!marshalqueryable.isTypeMappingSupported(typereference, i))
            throw new UnsupportedOperationException((new StringBuilder()).append("Unsupported type marshaling for managed type ").append(typereference).append(" and native type ").append(MarshalHelpers.toStringNativeType(i)).toString());
        else
            return;
    }

    public int calculateMarshalSize(Object obj)
    {
        int i = getNativeSize();
        if(i == NATIVE_SIZE_DYNAMIC)
            throw new AssertionError("Override this function for dynamically-sized objects");
        else
            return i;
    }

    public abstract int getNativeSize();

    public int getNativeType()
    {
        return mNativeType;
    }

    public TypeReference getTypeReference()
    {
        return mTypeReference;
    }

    public abstract void marshal(Object obj, ByteBuffer bytebuffer);

    public abstract Object unmarshal(ByteBuffer bytebuffer);

    public static int NATIVE_SIZE_DYNAMIC = -1;
    protected final int mNativeType;
    protected final TypeReference mTypeReference;

}
