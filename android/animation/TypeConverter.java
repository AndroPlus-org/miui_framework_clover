// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.animation;


public abstract class TypeConverter
{

    public TypeConverter(Class class1, Class class2)
    {
        mFromClass = class1;
        mToClass = class2;
    }

    public abstract Object convert(Object obj);

    Class getSourceType()
    {
        return mFromClass;
    }

    Class getTargetType()
    {
        return mToClass;
    }

    private Class mFromClass;
    private Class mToClass;
}
