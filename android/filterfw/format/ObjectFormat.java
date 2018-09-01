// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.format;

import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.NativeBuffer;

public class ObjectFormat
{

    public ObjectFormat()
    {
    }

    private static int bytesPerSampleForClass(Class class1, int i)
    {
        if(i == 2)
        {
            if(!android/filterfw/core/NativeBuffer.isAssignableFrom(class1))
                throw new IllegalArgumentException((new StringBuilder()).append("Native object-based formats must be of a NativeBuffer subclass! (Received class: ").append(class1).append(").").toString());
            try
            {
                i = ((NativeBuffer)class1.newInstance()).getElementSize();
            }
            catch(Exception exception)
            {
                throw new RuntimeException((new StringBuilder()).append("Could not determine the size of an element in a native object-based frame of type ").append(class1).append("! Perhaps it is missing a ").append("default constructor?").toString());
            }
            return i;
        } else
        {
            return 1;
        }
    }

    public static MutableFrameFormat fromClass(Class class1, int i)
    {
        return fromClass(class1, 0, i);
    }

    public static MutableFrameFormat fromClass(Class class1, int i, int j)
    {
        MutableFrameFormat mutableframeformat = new MutableFrameFormat(8, j);
        mutableframeformat.setObjectClass(getBoxedClass(class1));
        if(i != 0)
            mutableframeformat.setDimensions(i);
        mutableframeformat.setBytesPerSample(bytesPerSampleForClass(class1, j));
        return mutableframeformat;
    }

    public static MutableFrameFormat fromObject(Object obj, int i)
    {
        if(obj == null)
            obj = new MutableFrameFormat(8, i);
        else
            obj = fromClass(obj.getClass(), 0, i);
        return ((MutableFrameFormat) (obj));
    }

    public static MutableFrameFormat fromObject(Object obj, int i, int j)
    {
        if(obj == null)
            obj = new MutableFrameFormat(8, j);
        else
            obj = fromClass(obj.getClass(), i, j);
        return ((MutableFrameFormat) (obj));
    }

    private static Class getBoxedClass(Class class1)
    {
        if(class1.isPrimitive())
        {
            if(class1 == Boolean.TYPE)
                return java/lang/Boolean;
            if(class1 == Byte.TYPE)
                return java/lang/Byte;
            if(class1 == Character.TYPE)
                return java/lang/Character;
            if(class1 == Short.TYPE)
                return java/lang/Short;
            if(class1 == Integer.TYPE)
                return java/lang/Integer;
            if(class1 == Long.TYPE)
                return java/lang/Long;
            if(class1 == Float.TYPE)
                return java/lang/Float;
            if(class1 == Double.TYPE)
                return java/lang/Double;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown primitive type: ").append(class1.getSimpleName()).append("!").toString());
        } else
        {
            return class1;
        }
    }
}
