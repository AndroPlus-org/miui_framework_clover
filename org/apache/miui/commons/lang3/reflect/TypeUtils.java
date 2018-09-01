// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.reflect;

import java.lang.reflect.*;
import java.util.*;
import org.apache.miui.commons.lang3.ClassUtils;

public class TypeUtils
{

    public TypeUtils()
    {
    }

    public static Map determineTypeArguments(Class class1, ParameterizedType parameterizedtype)
    {
        Object obj = getRawType(parameterizedtype);
        if(!isAssignable(class1, ((Class) (obj))))
            return null;
        if(class1.equals(obj))
            return getTypeArguments(parameterizedtype, ((Class) (obj)), null);
        obj = getClosestParentType(class1, ((Class) (obj)));
        if(obj instanceof Class)
        {
            return determineTypeArguments((Class)obj, parameterizedtype);
        } else
        {
            obj = (ParameterizedType)obj;
            parameterizedtype = determineTypeArguments(getRawType(((ParameterizedType) (obj))), parameterizedtype);
            mapTypeVariablesToArguments(class1, ((ParameterizedType) (obj)), parameterizedtype);
            return parameterizedtype;
        }
    }

    public static Type getArrayComponentType(Type type)
    {
        Object obj = null;
        if(type instanceof Class)
        {
            Class class1 = (Class)type;
            type = obj;
            if(class1.isArray())
                type = class1.getComponentType();
            return type;
        }
        if(type instanceof GenericArrayType)
            return ((GenericArrayType)type).getGenericComponentType();
        else
            return null;
    }

    private static Type getClosestParentType(Class class1, Class class2)
    {
        if(class2.isInterface())
        {
            Type atype[] = class1.getGenericInterfaces();
            Type type = null;
            int i = atype.length;
            int j = 0;
            while(j < i) 
            {
                Type type1 = atype[j];
                Class class3;
                Type type2;
                if(type1 instanceof ParameterizedType)
                    class3 = getRawType((ParameterizedType)type1);
                else
                if(type1 instanceof Class)
                    class3 = (Class)type1;
                else
                    throw new IllegalStateException((new StringBuilder()).append("Unexpected generic interface type found: ").append(type1).toString());
                type2 = type;
                if(isAssignable(class3, class2))
                {
                    type2 = type;
                    if(isAssignable(type, class3))
                        type2 = type1;
                }
                j++;
                type = type2;
            }
            if(type != null)
                return type;
        }
        return class1.getGenericSuperclass();
    }

    public static Type[] getImplicitBounds(TypeVariable typevariable)
    {
        typevariable = typevariable.getBounds();
        if(typevariable.length == 0)
        {
            typevariable = new Type[1];
            typevariable[0] = java/lang/Object;
        } else
        {
            typevariable = normalizeUpperBounds(typevariable);
        }
        return typevariable;
    }

    public static Type[] getImplicitLowerBounds(WildcardType wildcardtype)
    {
        Type atype[] = wildcardtype.getLowerBounds();
        wildcardtype = atype;
        if(atype.length == 0)
        {
            wildcardtype = new Type[1];
            wildcardtype[0] = null;
        }
        return wildcardtype;
    }

    public static Type[] getImplicitUpperBounds(WildcardType wildcardtype)
    {
        wildcardtype = wildcardtype.getUpperBounds();
        if(wildcardtype.length == 0)
        {
            wildcardtype = new Type[1];
            wildcardtype[0] = java/lang/Object;
        } else
        {
            wildcardtype = normalizeUpperBounds(wildcardtype);
        }
        return wildcardtype;
    }

    private static Class getRawType(ParameterizedType parameterizedtype)
    {
        parameterizedtype = parameterizedtype.getRawType();
        if(!(parameterizedtype instanceof Class))
            throw new IllegalStateException((new StringBuilder()).append("Wait... What!? Type of rawType: ").append(parameterizedtype).toString());
        else
            return (Class)parameterizedtype;
    }

    public static Class getRawType(Type type, Type type1)
    {
        if(type instanceof Class)
            return (Class)type;
        if(type instanceof ParameterizedType)
            return getRawType((ParameterizedType)type);
        if(type instanceof TypeVariable)
        {
            if(type1 == null)
                return null;
            Object obj = ((TypeVariable)type).getGenericDeclaration();
            if(!(obj instanceof Class))
                return null;
            obj = getTypeArguments(type1, (Class)obj);
            if(obj == null)
                return null;
            type = (Type)((Map) (obj)).get(type);
            if(type == null)
                return null;
            else
                return getRawType(type, type1);
        }
        if(type instanceof GenericArrayType)
            return Array.newInstance(getRawType(((GenericArrayType)type).getGenericComponentType(), type1), 0).getClass();
        if(type instanceof WildcardType)
            return null;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown type: ").append(type).toString());
    }

    private static Map getTypeArguments(Class class1, Class class2, Map map)
    {
        if(!isAssignable(class1, class2))
            return null;
        Class class3 = class1;
        if(class1.isPrimitive())
        {
            if(class2.isPrimitive())
                return new HashMap();
            class3 = ClassUtils.primitiveToWrapper(class1);
        }
        if(map == null)
            class1 = new HashMap();
        else
            class1 = new HashMap(map);
        if(class3.getTypeParameters().length > 0 || class2.equals(class3))
            return class1;
        else
            return getTypeArguments(getClosestParentType(class3, class2), class2, ((Map) (class1)));
    }

    public static Map getTypeArguments(ParameterizedType parameterizedtype)
    {
        return getTypeArguments(parameterizedtype, getRawType(parameterizedtype), null);
    }

    private static Map getTypeArguments(ParameterizedType parameterizedtype, Class class1, Map map)
    {
        Class class2 = getRawType(parameterizedtype);
        if(!isAssignable(class2, class1))
            return null;
        Object obj = parameterizedtype.getOwnerType();
        Type atype[];
        TypeVariable atypevariable[];
        if(obj instanceof ParameterizedType)
        {
            obj = (ParameterizedType)obj;
            map = getTypeArguments(((ParameterizedType) (obj)), getRawType(((ParameterizedType) (obj))), map);
        } else
        if(map == null)
            map = new HashMap();
        else
            map = new HashMap(map);
        atype = parameterizedtype.getActualTypeArguments();
        atypevariable = class2.getTypeParameters();
        for(int i = 0; i < atypevariable.length; i++)
        {
            parameterizedtype = atype[i];
            TypeVariable typevariable = atypevariable[i];
            if(map.containsKey(parameterizedtype))
                parameterizedtype = (Type)map.get(parameterizedtype);
            map.put(typevariable, parameterizedtype);
        }

        if(class1.equals(class2))
            return map;
        else
            return getTypeArguments(getClosestParentType(class2, class1), class1, map);
    }

    public static Map getTypeArguments(Type type, Class class1)
    {
        return getTypeArguments(type, class1, null);
    }

    private static Map getTypeArguments(Type type, Class class1, Map map)
    {
        int i = 0;
        int j = 0;
        if(type instanceof Class)
            return getTypeArguments((Class)type, class1, map);
        if(type instanceof ParameterizedType)
            return getTypeArguments((ParameterizedType)type, class1, map);
        if(type instanceof GenericArrayType)
        {
            Type type1 = ((GenericArrayType)type).getGenericComponentType();
            type = class1;
            if(class1.isArray())
                type = class1.getComponentType();
            return getTypeArguments(type1, ((Class) (type)), map);
        }
        if(type instanceof WildcardType)
        {
            type = getImplicitUpperBounds((WildcardType)type);
            for(i = type.length; j < i; j++)
            {
                Type type2 = type[j];
                if(isAssignable(type2, class1))
                    return getTypeArguments(type2, class1, map);
            }

            return null;
        }
        if(type instanceof TypeVariable)
        {
            Type atype[] = getImplicitBounds((TypeVariable)type);
            int l = atype.length;
            for(int k = i; k < l; k++)
            {
                type = atype[k];
                if(isAssignable(type, class1))
                    return getTypeArguments(type, class1, map);
            }

            return null;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("found an unhandled type: ").append(type).toString());
        }
    }

    public static boolean isArrayType(Type type)
    {
        boolean flag;
        if(!(type instanceof GenericArrayType))
        {
            if(type instanceof Class)
                flag = ((Class)type).isArray();
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    private static boolean isAssignable(Type type, Class class1)
    {
        boolean flag = true;
        boolean flag1 = true;
        if(type == null)
        {
            if(class1 != null)
                flag1 = class1.isPrimitive() ^ true;
            return flag1;
        }
        if(class1 == null)
            return false;
        if(class1.equals(type))
            return true;
        if(type instanceof Class)
            return ClassUtils.isAssignable((Class)type, class1);
        if(type instanceof ParameterizedType)
            return isAssignable(((Type) (getRawType((ParameterizedType)type))), class1);
        if(type instanceof TypeVariable)
        {
            type = ((TypeVariable)type).getBounds();
            int i = type.length;
            for(int j = 0; j < i; j++)
                if(isAssignable(type[j], class1))
                    return true;

            return false;
        }
        if(type instanceof GenericArrayType)
        {
            boolean flag2 = flag;
            if(!class1.equals(java/lang/Object))
                if(class1.isArray())
                    flag2 = isAssignable(((GenericArrayType)type).getGenericComponentType(), class1.getComponentType());
                else
                    flag2 = false;
            return flag2;
        }
        if(type instanceof WildcardType)
            return false;
        else
            throw new IllegalStateException((new StringBuilder()).append("found an unhandled type: ").append(type).toString());
    }

    private static boolean isAssignable(Type type, GenericArrayType genericarraytype, Map map)
    {
        boolean flag = false;
        if(type == null)
            return true;
        if(genericarraytype == null)
            return false;
        if(genericarraytype.equals(type))
            return true;
        Type type1 = genericarraytype.getGenericComponentType();
        if(type instanceof Class)
        {
            type = (Class)type;
            if(type.isArray())
                flag = isAssignable(((Type) (type.getComponentType())), type1, map);
            return flag;
        }
        if(type instanceof GenericArrayType)
            return isAssignable(((GenericArrayType)type).getGenericComponentType(), type1, map);
        if(type instanceof WildcardType)
        {
            type = getImplicitUpperBounds((WildcardType)type);
            int i = type.length;
            for(int k = 0; k < i; k++)
                if(isAssignable(type[k], ((Type) (genericarraytype))))
                    return true;

            return false;
        }
        if(type instanceof TypeVariable)
        {
            type = getImplicitBounds((TypeVariable)type);
            int j = type.length;
            for(int l = 0; l < j; l++)
                if(isAssignable(type[l], ((Type) (genericarraytype))))
                    return true;

            return false;
        }
        if(type instanceof ParameterizedType)
            return false;
        else
            throw new IllegalStateException((new StringBuilder()).append("found an unhandled type: ").append(type).toString());
    }

    private static boolean isAssignable(Type type, ParameterizedType parameterizedtype, Map map)
    {
        if(type == null)
            return true;
        if(parameterizedtype == null)
            return false;
        if(parameterizedtype.equals(type))
            return true;
        Object obj = getRawType(parameterizedtype);
        type = getTypeArguments(type, ((Class) (obj)), null);
        if(type == null)
            return false;
        if(type.isEmpty())
            return true;
        for(obj = getTypeArguments(parameterizedtype, ((Class) (obj)), map).entrySet().iterator(); ((Iterator) (obj)).hasNext();)
        {
            Object obj1 = (java.util.Map.Entry)((Iterator) (obj)).next();
            parameterizedtype = (Type)((java.util.Map.Entry) (obj1)).getValue();
            obj1 = (Type)type.get(((java.util.Map.Entry) (obj1)).getKey());
            if(obj1 != null && parameterizedtype.equals(obj1) ^ true)
            {
                boolean flag;
                if(parameterizedtype instanceof WildcardType)
                    flag = isAssignable(((Type) (obj1)), ((Type) (parameterizedtype)), map);
                else
                    flag = false;
                if(flag ^ true)
                    return false;
            }
        }

        return true;
    }

    public static boolean isAssignable(Type type, Type type1)
    {
        return isAssignable(type, type1, null);
    }

    private static boolean isAssignable(Type type, Type type1, Map map)
    {
        if(type1 == null || (type1 instanceof Class))
            return isAssignable(type, (Class)type1);
        if(type1 instanceof ParameterizedType)
            return isAssignable(type, (ParameterizedType)type1, map);
        if(type1 instanceof GenericArrayType)
            return isAssignable(type, (GenericArrayType)type1, map);
        if(type1 instanceof WildcardType)
            return isAssignable(type, (WildcardType)type1, map);
        if(type1 instanceof TypeVariable)
            return isAssignable(type, (TypeVariable)type1, map);
        else
            throw new IllegalStateException((new StringBuilder()).append("found an unhandled type: ").append(type1).toString());
    }

    private static boolean isAssignable(Type type, TypeVariable typevariable, Map map)
    {
        if(type == null)
            return true;
        if(typevariable == null)
            return false;
        if(typevariable.equals(type))
            return true;
        if(type instanceof TypeVariable)
        {
            Type atype[] = getImplicitBounds((TypeVariable)type);
            int i = atype.length;
            for(int j = 0; j < i; j++)
                if(isAssignable(atype[j], typevariable, map))
                    return true;

        }
        if((type instanceof Class) || (type instanceof ParameterizedType) || (type instanceof GenericArrayType) || (type instanceof WildcardType))
            return false;
        else
            throw new IllegalStateException((new StringBuilder()).append("found an unhandled type: ").append(type).toString());
    }

    private static boolean isAssignable(Type type, WildcardType wildcardtype, Map map)
    {
        if(type == null)
            return true;
        if(wildcardtype == null)
            return false;
        if(wildcardtype.equals(type))
            return true;
        Type atype[] = getImplicitUpperBounds(wildcardtype);
        wildcardtype = getImplicitLowerBounds(wildcardtype);
        if(type instanceof WildcardType)
        {
            WildcardType wildcardtype1 = (WildcardType)type;
            type = getImplicitUpperBounds(wildcardtype1);
            Type atype1[] = getImplicitLowerBounds(wildcardtype1);
            int i = atype.length;
            for(int j = 0; j < i; j++)
            {
                Type type1 = substituteTypeVariables(atype[j], map);
                int i1 = 0;
                for(int i2 = type.length; i1 < i2; i1++)
                    if(!isAssignable(type[i1], type1, map))
                        return false;

            }

            i = wildcardtype.length;
            for(int k = 0; k < i; k++)
            {
                type = substituteTypeVariables(wildcardtype[k], map);
                int j1 = 0;
                for(int j2 = atype1.length; j1 < j2; j1++)
                    if(!isAssignable(type, atype1[j1], map))
                        return false;

            }

            return true;
        }
        int l = 0;
        for(int k1 = atype.length; l < k1; l++)
            if(!isAssignable(type, substituteTypeVariables(atype[l], map), map))
                return false;

        l = 0;
        for(int l1 = wildcardtype.length; l < l1; l++)
            if(!isAssignable(substituteTypeVariables(wildcardtype[l], map), type, map))
                return false;

        return true;
    }

    public static boolean isInstance(Object obj, Type type)
    {
        if(type == null)
            return false;
        boolean flag;
        if(obj == null)
        {
            if(type instanceof Class)
                flag = ((Class)type).isPrimitive() ^ true;
            else
                flag = true;
        } else
        {
            flag = isAssignable(obj.getClass(), type, null);
        }
        return flag;
    }

    private static void mapTypeVariablesToArguments(Class class1, ParameterizedType parameterizedtype, Map map)
    {
        Type type = parameterizedtype.getOwnerType();
        if(type instanceof ParameterizedType)
            mapTypeVariablesToArguments(class1, (ParameterizedType)type, map);
        Type atype[] = parameterizedtype.getActualTypeArguments();
        parameterizedtype = getRawType(parameterizedtype).getTypeParameters();
        class1 = Arrays.asList(class1.getTypeParameters());
        for(int i = 0; i < atype.length; i++)
        {
            Object obj = parameterizedtype[i];
            Type type1 = atype[i];
            if(class1.contains(type1) && map.containsKey(obj))
                map.put((TypeVariable)type1, (Type)map.get(obj));
        }

    }

    public static Type[] normalizeUpperBounds(Type atype[])
    {
        if(atype.length < 2)
            return atype;
        HashSet hashset = new HashSet(atype.length);
        int i = atype.length;
        int j = 0;
label0:
        do
        {
            if(j < i)
            {
                Type type = atype[j];
                boolean flag = false;
                int k = atype.length;
                int l = 0;
                do
                {
label1:
                    {
                        boolean flag1 = flag;
                        if(l < k)
                        {
                            Type type1 = atype[l];
                            if(type == type1 || !isAssignable(type1, type, null))
                                break label1;
                            flag1 = true;
                        }
                        if(!flag1)
                            hashset.add(type);
                        j++;
                        continue label0;
                    }
                    l++;
                } while(true);
            }
            return (Type[])hashset.toArray(new Type[hashset.size()]);
        } while(true);
    }

    private static Type substituteTypeVariables(Type type, Map map)
    {
        if((type instanceof TypeVariable) && map != null)
        {
            map = (Type)map.get(type);
            if(map == null)
                throw new IllegalArgumentException((new StringBuilder()).append("missing assignment type for type variable ").append(type).toString());
            else
                return map;
        } else
        {
            return type;
        }
    }

    public static boolean typesSatisfyVariables(Map map)
    {
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            Object obj = (java.util.Map.Entry)iterator.next();
            TypeVariable typevariable = (TypeVariable)((java.util.Map.Entry) (obj)).getKey();
            obj = (Type)((java.util.Map.Entry) (obj)).getValue();
            Type atype[] = getImplicitBounds(typevariable);
            int i = atype.length;
            int j = 0;
            while(j < i) 
            {
                if(!isAssignable(((Type) (obj)), substituteTypeVariables(atype[j], map), map))
                    return false;
                j++;
            }
        }

        return true;
    }
}
