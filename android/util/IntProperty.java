// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;


// Referenced classes of package android.util:
//            Property

public abstract class IntProperty extends Property
{

    public IntProperty(String s)
    {
        super(java/lang/Integer, s);
    }

    public final void set(Object obj, Integer integer)
    {
        setValue(obj, integer.intValue());
    }

    public volatile void set(Object obj, Object obj1)
    {
        set(obj, (Integer)obj1);
    }

    public abstract void setValue(Object obj, int i);
}
