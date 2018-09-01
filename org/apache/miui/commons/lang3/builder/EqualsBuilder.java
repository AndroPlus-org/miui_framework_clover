// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.builder;

import java.lang.reflect.*;
import java.util.*;
import org.apache.miui.commons.lang3.ArrayUtils;
import org.apache.miui.commons.lang3.tuple.Pair;

// Referenced classes of package org.apache.miui.commons.lang3.builder:
//            Builder, IDKey, ReflectionToStringBuilder

public class EqualsBuilder
    implements Builder
{

    public EqualsBuilder()
    {
        isEquals = true;
    }

    static Pair getRegisterPair(Object obj, Object obj1)
    {
        return Pair.of(new IDKey(obj), new IDKey(obj1));
    }

    static Set getRegistry()
    {
        return (Set)REGISTRY.get();
    }

    static boolean isRegistered(Object obj, Object obj1)
    {
        Set set = getRegistry();
        obj1 = getRegisterPair(obj, obj1);
        obj = Pair.of((IDKey)((Pair) (obj1)).getLeft(), (IDKey)((Pair) (obj1)).getRight());
        boolean flag;
        if(set != null)
        {
            if(!set.contains(obj1))
                flag = set.contains(obj);
            else
                flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private static void reflectionAppend(Object obj, Object obj1, Class class1, EqualsBuilder equalsbuilder, boolean flag, String as[])
    {
        if(isRegistered(obj, obj1))
            return;
        register(obj, obj1);
        class1 = class1.getDeclaredFields();
        AccessibleObject.setAccessible(class1, true);
        int i = 0;
_L1:
        if(i >= class1.length || !equalsbuilder.isEquals)
            break MISSING_BLOCK_LABEL_153;
        Field field = class1[i];
        if(ArrayUtils.contains(as, field.getName()) || field.getName().indexOf('$') != -1)
            break MISSING_BLOCK_LABEL_126;
        if(flag)
            break MISSING_BLOCK_LABEL_92;
        if(!(Modifier.isTransient(field.getModifiers()) ^ true))
            break MISSING_BLOCK_LABEL_126;
        boolean flag1 = Modifier.isStatic(field.getModifiers());
        if(!(flag1 ^ true))
            break MISSING_BLOCK_LABEL_126;
        equalsbuilder.append(field.get(obj), field.get(obj1));
        i++;
          goto _L1
        class1;
        class1 = JVM INSTR new #125 <Class InternalError>;
        class1.InternalError("Unexpected IllegalAccessException");
        throw class1;
        class1;
        unregister(obj, obj1);
        throw class1;
        unregister(obj, obj1);
        return;
    }

    public static boolean reflectionEquals(Object obj, Object obj1, Collection collection)
    {
        return reflectionEquals(obj, obj1, ReflectionToStringBuilder.toNoNullStringArray(collection));
    }

    public static boolean reflectionEquals(Object obj, Object obj1, boolean flag)
    {
        return reflectionEquals(obj, obj1, flag, null, new String[0]);
    }

    public static transient boolean reflectionEquals(Object obj, Object obj1, boolean flag, Class class1, String as[])
    {
        Object obj2;
        Class class2;
        if(obj == obj1)
            return true;
        if(obj == null || obj1 == null)
            return false;
        obj2 = obj.getClass();
        class2 = obj1.getClass();
        if(!((Class) (obj2)).isInstance(obj1)) goto _L2; else goto _L1
_L1:
        Object obj3;
        obj3 = obj2;
        if(!class2.isInstance(obj))
            obj3 = class2;
_L4:
        obj2 = new EqualsBuilder();
        reflectionAppend(obj, obj1, ((Class) (obj3)), ((EqualsBuilder) (obj2)), flag, as);
        while(((Class) (obj3)).getSuperclass() != null && obj3 != class1) 
            try
            {
                obj3 = ((Class) (obj3)).getSuperclass();
                reflectionAppend(obj, obj1, ((Class) (obj3)), ((EqualsBuilder) (obj2)), flag, as);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                return false;
            }
        break MISSING_BLOCK_LABEL_146;
_L2:
        if(!class2.isInstance(obj))
            break; /* Loop/switch isn't completed */
        obj3 = class2;
        if(!((Class) (obj2)).isInstance(obj1))
            obj3 = obj2;
        if(true) goto _L4; else goto _L3
_L3:
        return false;
        return ((EqualsBuilder) (obj2)).isEquals();
    }

    public static transient boolean reflectionEquals(Object obj, Object obj1, String as[])
    {
        return reflectionEquals(obj, obj1, false, null, as);
    }

    static void register(Object obj, Object obj1)
    {
        org/apache/miui/commons/lang3/builder/EqualsBuilder;
        JVM INSTR monitorenter ;
        if(getRegistry() == null)
        {
            ThreadLocal threadlocal = REGISTRY;
            HashSet hashset = JVM INSTR new #171 <Class HashSet>;
            hashset.HashSet();
            threadlocal.set(hashset);
        }
        org/apache/miui/commons/lang3/builder/EqualsBuilder;
        JVM INSTR monitorexit ;
        getRegistry().add(getRegisterPair(obj, obj1));
        return;
        obj;
        throw obj;
    }

    static void unregister(Object obj, Object obj1)
    {
        Set set = getRegistry();
        if(set == null) goto _L2; else goto _L1
_L1:
        set.remove(getRegisterPair(obj, obj1));
        org/apache/miui/commons/lang3/builder/EqualsBuilder;
        JVM INSTR monitorenter ;
        obj = getRegistry();
        if(obj == null)
            break MISSING_BLOCK_LABEL_46;
        if(((Set) (obj)).isEmpty())
            REGISTRY.remove();
        org/apache/miui/commons/lang3/builder/EqualsBuilder;
        JVM INSTR monitorexit ;
_L2:
        return;
        obj;
        throw obj;
    }

    public EqualsBuilder append(byte byte0, byte byte1)
    {
        if(!isEquals)
            return this;
        boolean flag;
        if(byte0 == byte1)
            flag = true;
        else
            flag = false;
        isEquals = flag;
        return this;
    }

    public EqualsBuilder append(char c, char c1)
    {
        if(!isEquals)
            return this;
        boolean flag;
        if(c == c1)
            flag = true;
        else
            flag = false;
        isEquals = flag;
        return this;
    }

    public EqualsBuilder append(double d, double d1)
    {
        if(!isEquals)
            return this;
        else
            return append(Double.doubleToLongBits(d), Double.doubleToLongBits(d1));
    }

    public EqualsBuilder append(float f, float f1)
    {
        if(!isEquals)
            return this;
        else
            return append(Float.floatToIntBits(f), Float.floatToIntBits(f1));
    }

    public EqualsBuilder append(int i, int j)
    {
        if(!isEquals)
            return this;
        boolean flag;
        if(i == j)
            flag = true;
        else
            flag = false;
        isEquals = flag;
        return this;
    }

    public EqualsBuilder append(long l, long l1)
    {
        if(!isEquals)
            return this;
        boolean flag;
        if(l == l1)
            flag = true;
        else
            flag = false;
        isEquals = flag;
        return this;
    }

    public EqualsBuilder append(Object obj, Object obj1)
    {
        if(!isEquals)
            return this;
        if(obj == obj1)
            return this;
        if(obj == null || obj1 == null)
        {
            setEquals(false);
            return this;
        }
        if(!obj.getClass().isArray())
            isEquals = obj.equals(obj1);
        else
        if(obj.getClass() != obj1.getClass())
            setEquals(false);
        else
        if(obj instanceof long[])
            append((long[])obj, (long[])obj1);
        else
        if(obj instanceof int[])
            append((int[])obj, (int[])obj1);
        else
        if(obj instanceof short[])
            append((short[])obj, (short[])obj1);
        else
        if(obj instanceof char[])
            append((char[])obj, (char[])obj1);
        else
        if(obj instanceof byte[])
            append((byte[])obj, (byte[])obj1);
        else
        if(obj instanceof double[])
            append((double[])obj, (double[])obj1);
        else
        if(obj instanceof float[])
            append((float[])obj, (float[])obj1);
        else
        if(obj instanceof boolean[])
            append((boolean[])obj, (boolean[])obj1);
        else
            append((Object[])obj, (Object[])obj1);
        return this;
    }

    public EqualsBuilder append(short word0, short word1)
    {
        if(!isEquals)
            return this;
        boolean flag;
        if(word0 == word1)
            flag = true;
        else
            flag = false;
        isEquals = flag;
        return this;
    }

    public EqualsBuilder append(boolean flag, boolean flag1)
    {
        if(!isEquals)
            return this;
        if(flag == flag1)
            flag = true;
        else
            flag = false;
        isEquals = flag;
        return this;
    }

    public EqualsBuilder append(byte abyte0[], byte abyte1[])
    {
        if(!isEquals)
            return this;
        if(abyte0 == abyte1)
            return this;
        if(abyte0 == null || abyte1 == null)
        {
            setEquals(false);
            return this;
        }
        if(abyte0.length != abyte1.length)
        {
            setEquals(false);
            return this;
        }
        for(int i = 0; i < abyte0.length && isEquals; i++)
            append(abyte0[i], abyte1[i]);

        return this;
    }

    public EqualsBuilder append(char ac[], char ac1[])
    {
        if(!isEquals)
            return this;
        if(ac == ac1)
            return this;
        if(ac == null || ac1 == null)
        {
            setEquals(false);
            return this;
        }
        if(ac.length != ac1.length)
        {
            setEquals(false);
            return this;
        }
        for(int i = 0; i < ac.length && isEquals; i++)
            append(ac[i], ac1[i]);

        return this;
    }

    public EqualsBuilder append(double ad[], double ad1[])
    {
        if(!isEquals)
            return this;
        if(ad == ad1)
            return this;
        if(ad == null || ad1 == null)
        {
            setEquals(false);
            return this;
        }
        if(ad.length != ad1.length)
        {
            setEquals(false);
            return this;
        }
        for(int i = 0; i < ad.length && isEquals; i++)
            append(ad[i], ad1[i]);

        return this;
    }

    public EqualsBuilder append(float af[], float af1[])
    {
        if(!isEquals)
            return this;
        if(af == af1)
            return this;
        if(af == null || af1 == null)
        {
            setEquals(false);
            return this;
        }
        if(af.length != af1.length)
        {
            setEquals(false);
            return this;
        }
        for(int i = 0; i < af.length && isEquals; i++)
            append(af[i], af1[i]);

        return this;
    }

    public EqualsBuilder append(int ai[], int ai1[])
    {
        if(!isEquals)
            return this;
        if(ai == ai1)
            return this;
        if(ai == null || ai1 == null)
        {
            setEquals(false);
            return this;
        }
        if(ai.length != ai1.length)
        {
            setEquals(false);
            return this;
        }
        for(int i = 0; i < ai.length && isEquals; i++)
            append(ai[i], ai1[i]);

        return this;
    }

    public EqualsBuilder append(long al[], long al1[])
    {
        if(!isEquals)
            return this;
        if(al == al1)
            return this;
        if(al == null || al1 == null)
        {
            setEquals(false);
            return this;
        }
        if(al.length != al1.length)
        {
            setEquals(false);
            return this;
        }
        for(int i = 0; i < al.length && isEquals; i++)
            append(al[i], al1[i]);

        return this;
    }

    public EqualsBuilder append(Object aobj[], Object aobj1[])
    {
        if(!isEquals)
            return this;
        if(aobj == aobj1)
            return this;
        if(aobj == null || aobj1 == null)
        {
            setEquals(false);
            return this;
        }
        if(aobj.length != aobj1.length)
        {
            setEquals(false);
            return this;
        }
        for(int i = 0; i < aobj.length && isEquals; i++)
            append(aobj[i], aobj1[i]);

        return this;
    }

    public EqualsBuilder append(short aword0[], short aword1[])
    {
        if(!isEquals)
            return this;
        if(aword0 == aword1)
            return this;
        if(aword0 == null || aword1 == null)
        {
            setEquals(false);
            return this;
        }
        if(aword0.length != aword1.length)
        {
            setEquals(false);
            return this;
        }
        for(int i = 0; i < aword0.length && isEquals; i++)
            append(aword0[i], aword1[i]);

        return this;
    }

    public EqualsBuilder append(boolean aflag[], boolean aflag1[])
    {
        if(!isEquals)
            return this;
        if(aflag == aflag1)
            return this;
        if(aflag == null || aflag1 == null)
        {
            setEquals(false);
            return this;
        }
        if(aflag.length != aflag1.length)
        {
            setEquals(false);
            return this;
        }
        for(int i = 0; i < aflag.length && isEquals; i++)
            append(aflag[i], aflag1[i]);

        return this;
    }

    public EqualsBuilder appendSuper(boolean flag)
    {
        if(!isEquals)
        {
            return this;
        } else
        {
            isEquals = flag;
            return this;
        }
    }

    public Boolean build()
    {
        return Boolean.valueOf(isEquals());
    }

    public volatile Object build()
    {
        return build();
    }

    public boolean isEquals()
    {
        return isEquals;
    }

    public void reset()
    {
        isEquals = true;
    }

    protected void setEquals(boolean flag)
    {
        isEquals = flag;
    }

    private static final ThreadLocal REGISTRY = new ThreadLocal();
    private boolean isEquals;

}
