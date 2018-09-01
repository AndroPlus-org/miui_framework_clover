// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.tuple;


// Referenced classes of package org.apache.miui.commons.lang3.tuple:
//            Pair

public class MutablePair extends Pair
{

    public MutablePair()
    {
    }

    public MutablePair(Object obj, Object obj1)
    {
        left = obj;
        right = obj1;
    }

    public static MutablePair of(Object obj, Object obj1)
    {
        return new MutablePair(obj, obj1);
    }

    public Object getLeft()
    {
        return left;
    }

    public Object getRight()
    {
        return right;
    }

    public void setLeft(Object obj)
    {
        left = obj;
    }

    public void setRight(Object obj)
    {
        right = obj;
    }

    public Object setValue(Object obj)
    {
        Object obj1 = getRight();
        setRight(obj);
        return obj1;
    }

    private static final long serialVersionUID = 0x44c3687a6deaffd1L;
    public Object left;
    public Object right;
}
