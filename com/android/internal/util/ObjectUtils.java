// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;


// Referenced classes of package com.android.internal.util:
//            Preconditions

public class ObjectUtils
{

    private ObjectUtils()
    {
    }

    public static int compare(Comparable comparable, Comparable comparable1)
    {
        if(comparable != null)
        {
            int i;
            if(comparable1 != null)
                i = comparable.compareTo(comparable1);
            else
                i = 1;
            return i;
        }
        byte byte0;
        if(comparable1 != null)
            byte0 = -1;
        else
            byte0 = 0;
        return byte0;
    }

    public static Object firstNotNull(Object obj, Object obj1)
    {
        if(obj == null)
            obj = Preconditions.checkNotNull(obj1);
        return obj;
    }
}
