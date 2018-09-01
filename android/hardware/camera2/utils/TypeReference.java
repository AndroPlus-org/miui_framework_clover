// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.camera2.utils;

import com.android.internal.util.Preconditions;
import java.lang.reflect.*;

public abstract class TypeReference
{
    private static class SpecializedBaseTypeReference extends TypeReference
    {

        public SpecializedBaseTypeReference(Type type)
        {
            super(type, null);
        }
    }

    private static class SpecializedTypeReference extends TypeReference
    {

        public SpecializedTypeReference(Class class1)
        {
            super(class1, null);
        }
    }


    protected TypeReference()
    {
        mType = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if(containsTypeVariable(mType))
        {
            throw new IllegalArgumentException("Including a type variable in a type reference is not allowed");
        } else
        {
            mHash = mType.hashCode();
            return;
        }
    }

    private TypeReference(Type type)
    {
        mType = type;
        if(containsTypeVariable(mType))
        {
            throw new IllegalArgumentException("Including a type variable in a type reference is not allowed");
        } else
        {
            mHash = mType.hashCode();
            return;
        }
    }

    TypeReference(Type type, TypeReference typereference)
    {
        this(type);
    }

    public static boolean containsTypeVariable(Type type)
    {
        boolean flag = true;
        if(type == null)
            return false;
        if(type instanceof TypeVariable)
            return true;
        if(type instanceof Class)
        {
            type = (Class)type;
            if(type.getTypeParameters().length != 0)
                return true;
            else
                return containsTypeVariable(((Type) (type.getDeclaringClass())));
        }
        if(type instanceof ParameterizedType)
        {
            type = ((ParameterizedType)type).getActualTypeArguments();
            int i = type.length;
            for(int j = 0; j < i; j++)
                if(containsTypeVariable(type[j]))
                    return true;

            return false;
        }
        if(type instanceof WildcardType)
        {
            type = (WildcardType)type;
            if(!containsTypeVariable(type.getLowerBounds()))
                flag = containsTypeVariable(type.getUpperBounds());
            return flag;
        } else
        {
            return false;
        }
    }

    private static boolean containsTypeVariable(Type atype[])
    {
        if(atype == null)
            return false;
        int i = atype.length;
        for(int j = 0; j < i; j++)
            if(containsTypeVariable(atype[j]))
                return true;

        return false;
    }

    public static TypeReference createSpecializedTypeReference(Class class1)
    {
        return new SpecializedTypeReference(class1);
    }

    public static TypeReference createSpecializedTypeReference(Type type)
    {
        return new SpecializedBaseTypeReference(type);
    }

    private static final Class getArrayClass(Class class1)
    {
        return Array.newInstance(class1, 0).getClass();
    }

    private static Type getComponentType(Type type)
    {
        Preconditions.checkNotNull(type, "type must not be null");
        if(type instanceof Class)
            return ((Class)type).getComponentType();
        if(type instanceof ParameterizedType)
            return null;
        if(type instanceof GenericArrayType)
            return ((GenericArrayType)type).getGenericComponentType();
        if(type instanceof WildcardType)
            throw new UnsupportedOperationException("TODO: support wild card components");
        if(type instanceof TypeVariable)
            throw new AssertionError("Type variables are not allowed in type references");
        else
            throw new AssertionError((new StringBuilder()).append("Unhandled branch to get component type for type ").append(type).toString());
    }

    private static final Class getRawType(Type type)
    {
        if(type == null)
            throw new NullPointerException("type must not be null");
        if(type instanceof Class)
            return (Class)type;
        if(type instanceof ParameterizedType)
            return (Class)((ParameterizedType)type).getRawType();
        if(type instanceof GenericArrayType)
            return getArrayClass(getRawType(((GenericArrayType)type).getGenericComponentType()));
        if(type instanceof WildcardType)
            return getRawType(((WildcardType)type).getUpperBounds());
        if(type instanceof TypeVariable)
            throw new AssertionError("Type variables are not allowed in type references");
        else
            throw new AssertionError((new StringBuilder()).append("Unhandled branch to get raw type for type ").append(type).toString());
    }

    private static final Class getRawType(Type atype[])
    {
        if(atype == null)
            return null;
        int i = 0;
        for(int j = atype.length; i < j; i++)
        {
            Class class1 = getRawType(atype[i]);
            if(class1 != null)
                return class1;
        }

        return null;
    }

    private static void toString(Type type, StringBuilder stringbuilder)
    {
        if(type == null)
            return;
        if(type instanceof TypeVariable)
            stringbuilder.append(((TypeVariable)type).getName());
        else
        if(type instanceof Class)
        {
            type = (Class)type;
            stringbuilder.append(type.getName());
            toString(((Type []) (type.getTypeParameters())), stringbuilder);
        } else
        if(type instanceof ParameterizedType)
        {
            type = (ParameterizedType)type;
            stringbuilder.append(((Class)type.getRawType()).getName());
            toString(type.getActualTypeArguments(), stringbuilder);
        } else
        if(type instanceof GenericArrayType)
        {
            toString(((GenericArrayType)type).getGenericComponentType(), stringbuilder);
            stringbuilder.append("[]");
        } else
        {
            stringbuilder.append(type.toString());
        }
    }

    private static void toString(Type atype[], StringBuilder stringbuilder)
    {
        if(atype == null)
            return;
        if(atype.length == 0)
            return;
        stringbuilder.append("<");
        for(int i = 0; i < atype.length; i++)
        {
            toString(atype[i], stringbuilder);
            if(i != atype.length - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append(">");
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        if(obj instanceof TypeReference)
            flag = mType.equals(((TypeReference)obj).mType);
        else
            flag = false;
        return flag;
    }

    public TypeReference getComponentType()
    {
        TypeReference typereference = null;
        Type type = getComponentType(mType);
        if(type != null)
            typereference = createSpecializedTypeReference(type);
        return typereference;
    }

    public final Class getRawType()
    {
        return getRawType(mType);
    }

    public Type getType()
    {
        return mType;
    }

    public int hashCode()
    {
        return mHash;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("TypeReference<");
        toString(getType(), stringbuilder);
        stringbuilder.append(">");
        return stringbuilder.toString();
    }

    private final int mHash;
    private final Type mType;
}
