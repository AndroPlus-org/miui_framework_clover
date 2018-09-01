// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.tuple;


// Referenced classes of package org.apache.miui.commons.lang3.tuple:
//            Pair

public final class ImmutablePair extends Pair
{

    public ImmutablePair(Object obj, Object obj1)
    {
        left = obj;
        right = obj1;
    }

    public static ImmutablePair of(Object obj, Object obj1)
    {
        return new ImmutablePair(obj, obj1);
    }

    public Object getLeft()
    {
        return left;
    }

    public Object getRight()
    {
        return right;
    }

    public Object setValue(Object obj)
    {
        throw new UnsupportedOperationException();
    }

    private static final long serialVersionUID = 0x44c3687a6deaffd1L;
    public final Object left;
    public final Object right;
}
