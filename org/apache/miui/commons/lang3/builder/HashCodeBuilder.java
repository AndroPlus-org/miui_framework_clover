// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.builder;

import java.lang.reflect.*;
import java.util.*;
import org.apache.miui.commons.lang3.ArrayUtils;

// Referenced classes of package org.apache.miui.commons.lang3.builder:
//            Builder, IDKey, ReflectionToStringBuilder

public class HashCodeBuilder
    implements Builder
{

    public HashCodeBuilder()
    {
        iTotal = 0;
        iConstant = 37;
        iTotal = 17;
    }

    public HashCodeBuilder(int i, int j)
    {
        iTotal = 0;
        if(i == 0)
            throw new IllegalArgumentException("HashCodeBuilder requires a non zero initial value");
        if(i % 2 == 0)
            throw new IllegalArgumentException("HashCodeBuilder requires an odd initial value");
        if(j == 0)
            throw new IllegalArgumentException("HashCodeBuilder requires a non zero multiplier");
        if(j % 2 == 0)
        {
            throw new IllegalArgumentException("HashCodeBuilder requires an odd multiplier");
        } else
        {
            iConstant = j;
            iTotal = i;
            return;
        }
    }

    static Set getRegistry()
    {
        return (Set)REGISTRY.get();
    }

    static boolean isRegistered(Object obj)
    {
        Set set = getRegistry();
        boolean flag;
        if(set != null)
            flag = set.contains(new IDKey(obj));
        else
            flag = false;
        return flag;
    }

    private static void reflectionAppend(Object obj, Class class1, HashCodeBuilder hashcodebuilder, boolean flag, String as[])
    {
        if(isRegistered(obj))
            return;
        Field afield[];
        register(obj);
        afield = class1.getDeclaredFields();
        AccessibleObject.setAccessible(afield, true);
        int i = 0;
        int j = afield.length;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        class1 = afield[i];
        if(ArrayUtils.contains(as, class1.getName()) || class1.getName().indexOf('$') != -1)
            break MISSING_BLOCK_LABEL_112;
        if(flag)
            break MISSING_BLOCK_LABEL_86;
        if(!(Modifier.isTransient(class1.getModifiers()) ^ true))
            break MISSING_BLOCK_LABEL_112;
        boolean flag1 = Modifier.isStatic(class1.getModifiers());
        if(!(flag1 ^ true))
            break MISSING_BLOCK_LABEL_112;
        hashcodebuilder.append(class1.get(obj));
        i++;
        if(true) goto _L2; else goto _L1
        class1;
        class1 = JVM INSTR new #124 <Class InternalError>;
        class1.InternalError("Unexpected IllegalAccessException");
        throw class1;
        class1;
        unregister(obj);
        throw class1;
_L1:
        unregister(obj);
        return;
    }

    public static int reflectionHashCode(int i, int j, Object obj)
    {
        return reflectionHashCode(i, j, obj, false, null, new String[0]);
    }

    public static int reflectionHashCode(int i, int j, Object obj, boolean flag)
    {
        return reflectionHashCode(i, j, obj, flag, null, new String[0]);
    }

    public static transient int reflectionHashCode(int i, int j, Object obj, boolean flag, Class class1, String as[])
    {
        if(obj == null)
            throw new IllegalArgumentException("The object to build a hash code for must not be null");
        HashCodeBuilder hashcodebuilder = new HashCodeBuilder(i, j);
        Class class2 = obj.getClass();
        reflectionAppend(obj, class2, hashcodebuilder, flag, as);
        for(; class2.getSuperclass() != null && class2 != class1; reflectionAppend(obj, class2, hashcodebuilder, flag, as))
            class2 = class2.getSuperclass();

        return hashcodebuilder.toHashCode();
    }

    public static int reflectionHashCode(Object obj, Collection collection)
    {
        return reflectionHashCode(obj, ReflectionToStringBuilder.toNoNullStringArray(collection));
    }

    public static int reflectionHashCode(Object obj, boolean flag)
    {
        return reflectionHashCode(17, 37, obj, flag, null, new String[0]);
    }

    public static transient int reflectionHashCode(Object obj, String as[])
    {
        return reflectionHashCode(17, 37, obj, false, null, as);
    }

    static void register(Object obj)
    {
        org/apache/miui/commons/lang3/builder/HashCodeBuilder;
        JVM INSTR monitorenter ;
        if(getRegistry() == null)
        {
            ThreadLocal threadlocal = REGISTRY;
            HashSet hashset = JVM INSTR new #168 <Class HashSet>;
            hashset.HashSet();
            threadlocal.set(hashset);
        }
        org/apache/miui/commons/lang3/builder/HashCodeBuilder;
        JVM INSTR monitorexit ;
        getRegistry().add(new IDKey(obj));
        return;
        obj;
        throw obj;
    }

    static void unregister(Object obj)
    {
        Set set = getRegistry();
        if(set == null) goto _L2; else goto _L1
_L1:
        set.remove(new IDKey(obj));
        org/apache/miui/commons/lang3/builder/HashCodeBuilder;
        JVM INSTR monitorenter ;
        obj = getRegistry();
        if(obj == null)
            break MISSING_BLOCK_LABEL_49;
        if(((Set) (obj)).isEmpty())
            REGISTRY.remove();
        org/apache/miui/commons/lang3/builder/HashCodeBuilder;
        JVM INSTR monitorexit ;
_L2:
        return;
        obj;
        throw obj;
    }

    public HashCodeBuilder append(byte byte0)
    {
        iTotal = iTotal * iConstant + byte0;
        return this;
    }

    public HashCodeBuilder append(char c)
    {
        iTotal = iTotal * iConstant + c;
        return this;
    }

    public HashCodeBuilder append(double d)
    {
        return append(Double.doubleToLongBits(d));
    }

    public HashCodeBuilder append(float f)
    {
        iTotal = iTotal * iConstant + Float.floatToIntBits(f);
        return this;
    }

    public HashCodeBuilder append(int i)
    {
        iTotal = iTotal * iConstant + i;
        return this;
    }

    public HashCodeBuilder append(long l)
    {
        iTotal = iTotal * iConstant + (int)(l >> 32 ^ l);
        return this;
    }

    public HashCodeBuilder append(Object obj)
    {
        if(obj == null)
            iTotal = iTotal * iConstant;
        else
        if(obj.getClass().isArray())
        {
            if(obj instanceof long[])
                append((long[])obj);
            else
            if(obj instanceof int[])
                append((int[])obj);
            else
            if(obj instanceof short[])
                append((short[])obj);
            else
            if(obj instanceof char[])
                append((char[])obj);
            else
            if(obj instanceof byte[])
                append((byte[])obj);
            else
            if(obj instanceof double[])
                append((double[])obj);
            else
            if(obj instanceof float[])
                append((float[])obj);
            else
            if(obj instanceof boolean[])
                append((boolean[])obj);
            else
                append((Object[])obj);
        } else
        {
            iTotal = iTotal * iConstant + obj.hashCode();
        }
        return this;
    }

    public HashCodeBuilder append(short word0)
    {
        iTotal = iTotal * iConstant + word0;
        return this;
    }

    public HashCodeBuilder append(boolean flag)
    {
        int i = iTotal;
        int j = iConstant;
        int k;
        if(flag)
            k = 0;
        else
            k = 1;
        iTotal = k + j * i;
        return this;
    }

    public HashCodeBuilder append(byte abyte0[])
    {
        if(abyte0 == null)
        {
            iTotal = iTotal * iConstant;
        } else
        {
            int i = 0;
            int j = abyte0.length;
            while(i < j) 
            {
                append(abyte0[i]);
                i++;
            }
        }
        return this;
    }

    public HashCodeBuilder append(char ac[])
    {
        if(ac == null)
        {
            iTotal = iTotal * iConstant;
        } else
        {
            int i = 0;
            int j = ac.length;
            while(i < j) 
            {
                append(ac[i]);
                i++;
            }
        }
        return this;
    }

    public HashCodeBuilder append(double ad[])
    {
        if(ad == null)
        {
            iTotal = iTotal * iConstant;
        } else
        {
            int i = 0;
            int j = ad.length;
            while(i < j) 
            {
                append(ad[i]);
                i++;
            }
        }
        return this;
    }

    public HashCodeBuilder append(float af[])
    {
        if(af == null)
        {
            iTotal = iTotal * iConstant;
        } else
        {
            int i = 0;
            int j = af.length;
            while(i < j) 
            {
                append(af[i]);
                i++;
            }
        }
        return this;
    }

    public HashCodeBuilder append(int ai[])
    {
        if(ai == null)
        {
            iTotal = iTotal * iConstant;
        } else
        {
            int i = 0;
            int j = ai.length;
            while(i < j) 
            {
                append(ai[i]);
                i++;
            }
        }
        return this;
    }

    public HashCodeBuilder append(long al[])
    {
        if(al == null)
        {
            iTotal = iTotal * iConstant;
        } else
        {
            int i = 0;
            int j = al.length;
            while(i < j) 
            {
                append(al[i]);
                i++;
            }
        }
        return this;
    }

    public HashCodeBuilder append(Object aobj[])
    {
        if(aobj == null)
        {
            iTotal = iTotal * iConstant;
        } else
        {
            int i = 0;
            int j = aobj.length;
            while(i < j) 
            {
                append(aobj[i]);
                i++;
            }
        }
        return this;
    }

    public HashCodeBuilder append(short aword0[])
    {
        if(aword0 == null)
        {
            iTotal = iTotal * iConstant;
        } else
        {
            int i = 0;
            int j = aword0.length;
            while(i < j) 
            {
                append(aword0[i]);
                i++;
            }
        }
        return this;
    }

    public HashCodeBuilder append(boolean aflag[])
    {
        if(aflag == null)
        {
            iTotal = iTotal * iConstant;
        } else
        {
            int i = 0;
            int j = aflag.length;
            while(i < j) 
            {
                append(aflag[i]);
                i++;
            }
        }
        return this;
    }

    public HashCodeBuilder appendSuper(int i)
    {
        iTotal = iTotal * iConstant + i;
        return this;
    }

    public Integer build()
    {
        return Integer.valueOf(toHashCode());
    }

    public volatile Object build()
    {
        return build();
    }

    public int hashCode()
    {
        return toHashCode();
    }

    public int toHashCode()
    {
        return iTotal;
    }

    private static final ThreadLocal REGISTRY = new ThreadLocal();
    private final int iConstant;
    private int iTotal;

}
