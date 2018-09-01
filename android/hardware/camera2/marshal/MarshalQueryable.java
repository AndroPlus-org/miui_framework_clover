// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.marshal;

import android.hardware.camera2.utils.TypeReference;

// Referenced classes of package android.hardware.camera2.marshal:
//            Marshaler

public interface MarshalQueryable
{

    public abstract Marshaler createMarshaler(TypeReference typereference, int i);

    public abstract boolean isTypeMappingSupported(TypeReference typereference, int i);
}
