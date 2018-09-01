// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.reflect;

import java.lang.reflect.*;
import org.apache.miui.commons.lang3.ClassUtils;

public abstract class MemberUtils
{

    public MemberUtils()
    {
    }

    public static int compareParameterTypes(Class aclass[], Class aclass1[], Class aclass2[])
    {
        float f = getTotalTransformationCost(aclass2, aclass);
        float f1 = getTotalTransformationCost(aclass2, aclass1);
        byte byte0;
        if(f < f1)
            byte0 = -1;
        else
        if(f1 < f)
            byte0 = 1;
        else
            byte0 = 0;
        return byte0;
    }

    private static float getObjectTransformationCost(Class class1, Class class2)
    {
        if(class2.isPrimitive())
            return getPrimitivePromotionCost(class1, class2);
        float f = 0.0F;
        do
        {
label0:
            {
                float f1 = f;
                if(class1 != null)
                {
                    f1 = f;
                    if(class2.equals(class1) ^ true)
                    {
                        if(!class2.isInterface() || !ClassUtils.isAssignable(class1, class2))
                            break label0;
                        f1 = f + 0.25F;
                    }
                }
                f = f1;
                if(class1 == null)
                    f = f1 + 1.5F;
                return f;
            }
            f++;
            class1 = class1.getSuperclass();
        } while(true);
    }

    private static float getPrimitivePromotionCost(Class class1, Class class2)
    {
        float f = 0.0F;
        Class class3 = class1;
        if(!class1.isPrimitive())
        {
            f = 0.1F;
            class3 = ClassUtils.wrapperToPrimitive(class1);
        }
        int i = 0;
        float f1;
        for(f1 = f; class3 != class2 && i < ORDERED_PRIMITIVE_TYPES.length; f1 = f)
        {
            class1 = class3;
            f = f1;
            if(class3 == ORDERED_PRIMITIVE_TYPES[i])
            {
                f1 += 0.1F;
                class1 = class3;
                f = f1;
                if(i < ORDERED_PRIMITIVE_TYPES.length - 1)
                {
                    class1 = ORDERED_PRIMITIVE_TYPES[i + 1];
                    f = f1;
                }
            }
            i++;
            class3 = class1;
        }

        return f1;
    }

    private static float getTotalTransformationCost(Class aclass[], Class aclass1[])
    {
        float f = 0.0F;
        for(int i = 0; i < aclass.length; i++)
            f += getObjectTransformationCost(aclass[i], aclass1[i]);

        return f;
    }

    static boolean isAccessible(Member member)
    {
        boolean flag;
        if(member != null && Modifier.isPublic(member.getModifiers()))
            flag = member.isSynthetic() ^ true;
        else
            flag = false;
        return flag;
    }

    static boolean isPackageAccess(int i)
    {
        boolean flag = false;
        if((i & 7) == 0)
            flag = true;
        return flag;
    }

    static void setAccessibleWorkaround(AccessibleObject accessibleobject)
    {
        if(accessibleobject == null || accessibleobject.isAccessible())
            return;
        Member member = (Member)accessibleobject;
        if(!Modifier.isPublic(member.getModifiers()) || !isPackageAccess(member.getDeclaringClass().getModifiers()))
            break MISSING_BLOCK_LABEL_49;
        accessibleobject.setAccessible(true);
_L2:
        return;
        accessibleobject;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final int ACCESS_TEST = 7;
    private static final Class ORDERED_PRIMITIVE_TYPES[];

    static 
    {
        ORDERED_PRIMITIVE_TYPES = (new Class[] {
            Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE
        });
    }
}
