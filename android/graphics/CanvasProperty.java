// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import com.android.internal.util.VirtualRefBasePtr;

// Referenced classes of package android.graphics:
//            Paint

public final class CanvasProperty
{

    private CanvasProperty(long l)
    {
        mProperty = new VirtualRefBasePtr(l);
    }

    public static CanvasProperty createFloat(float f)
    {
        return new CanvasProperty(nCreateFloat(f));
    }

    public static CanvasProperty createPaint(Paint paint)
    {
        return new CanvasProperty(nCreatePaint(paint.getNativeInstance()));
    }

    private static native long nCreateFloat(float f);

    private static native long nCreatePaint(long l);

    public long getNativeContainer()
    {
        return mProperty.get();
    }

    private VirtualRefBasePtr mProperty;
}
