// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3;

import java.util.*;

// Referenced classes of package org.apache.miui.commons.lang3:
//            Validate

public class EnumUtils
{

    public EnumUtils()
    {
    }

    private static Class checkBitVectorable(Class class1)
    {
        Validate.notNull(class1, "EnumClass must be defined.", new Object[0]);
        Enum aenum[] = (Enum[])class1.getEnumConstants();
        boolean flag;
        if(aenum != null)
            flag = true;
        else
            flag = false;
        Validate.isTrue(flag, "%s does not seem to be an Enum type", new Object[] {
            class1
        });
        if(aenum.length <= 64)
            flag = true;
        else
            flag = false;
        Validate.isTrue(flag, "Cannot store %s %s values in %s bits", new Object[] {
            Integer.valueOf(aenum.length), class1.getSimpleName(), Integer.valueOf(64)
        });
        return class1;
    }

    public static long generateBitVector(Class class1, Iterable iterable)
    {
        checkBitVectorable(class1);
        Validate.notNull(iterable);
        long l = 0L;
        for(class1 = iterable.iterator(); class1.hasNext();)
            l |= 1 << ((Enum)class1.next()).ordinal();

        return l;
    }

    public static transient long generateBitVector(Class class1, Enum aenum[])
    {
        Validate.noNullElements(aenum);
        return generateBitVector(class1, ((Iterable) (Arrays.asList(aenum))));
    }

    public static Enum getEnum(Class class1, String s)
    {
        if(s == null)
            return null;
        try
        {
            class1 = Enum.valueOf(class1, s);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return null;
        }
        return class1;
    }

    public static List getEnumList(Class class1)
    {
        return new ArrayList(Arrays.asList((Enum[])class1.getEnumConstants()));
    }

    public static Map getEnumMap(Class class1)
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        class1 = (Enum[])class1.getEnumConstants();
        int i = 0;
        for(int j = class1.length; i < j; i++)
        {
            Enum enum = class1[i];
            linkedhashmap.put(enum.name(), enum);
        }

        return linkedhashmap;
    }

    public static boolean isValidEnum(Class class1, String s)
    {
        if(s == null)
            return false;
        try
        {
            Enum.valueOf(class1, s);
        }
        // Misplaced declaration of an exception variable
        catch(Class class1)
        {
            return false;
        }
        return true;
    }

    public static EnumSet processBitVector(Class class1, long l)
    {
        Enum aenum[] = (Enum[])checkBitVectorable(class1).getEnumConstants();
        class1 = EnumSet.noneOf(class1);
        int i = 0;
        for(int j = aenum.length; i < j; i++)
        {
            Enum enum = aenum[i];
            if(((long)(1 << enum.ordinal()) & l) != 0L)
                class1.add(enum);
        }

        return class1;
    }
}
