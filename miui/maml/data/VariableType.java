// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;


public final class VariableType extends Enum
{

    private VariableType(String s, int i, Class class1)
    {
        super(s, i);
        mTypeClass = class1;
    }

    public static VariableType parseType(String s)
    {
        if("number".equalsIgnoreCase(s))
            return NUM;
        if("string".equalsIgnoreCase(s))
            return STR;
        if("object".equalsIgnoreCase(s))
            return OBJ;
        if("number[]".equalsIgnoreCase(s))
            return NUM_ARR;
        if("double[]".equalsIgnoreCase(s))
            return DOUBLE_ARR;
        if("float[]".equalsIgnoreCase(s))
            return FLOAT_ARR;
        if("int[]".equalsIgnoreCase(s))
            return INT_ARR;
        if("short[]".equalsIgnoreCase(s))
            return SHORT_ARR;
        if("byte[]".equalsIgnoreCase(s))
            return BYTE_ARR;
        if("long[]".equalsIgnoreCase(s))
            return LONG_ARR;
        if("boolean[]".equalsIgnoreCase(s))
            return BOOLEAN_ARR;
        if("char[]".equalsIgnoreCase(s))
            return CHAR_ARR;
        if("string[]".equalsIgnoreCase(s))
            return STR_ARR;
        if("object[]".equalsIgnoreCase(s))
            return OBJ_ARR;
        else
            return NUM;
    }

    public static VariableType valueOf(String s)
    {
        return (VariableType)Enum.valueOf(miui/maml/data/VariableType, s);
    }

    public static VariableType[] values()
    {
        return $VALUES;
    }

    public boolean isArray()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(ordinal() >= NUM_ARR.ordinal())
        {
            flag1 = flag;
            if(ordinal() <= OBJ_ARR.ordinal())
                flag1 = true;
        }
        return flag1;
    }

    public boolean isNumber()
    {
        boolean flag;
        if(this == NUM)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isNumberArray()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(ordinal() >= NUM_ARR.ordinal())
        {
            flag1 = flag;
            if(ordinal() <= CHAR_ARR.ordinal())
                flag1 = true;
        }
        return flag1;
    }

    private static final VariableType $VALUES[];
    public static final VariableType BOOLEAN_ARR;
    public static final VariableType BYTE_ARR;
    public static final VariableType CHAR_ARR;
    public static final VariableType DOUBLE_ARR;
    public static final VariableType FLOAT_ARR;
    public static final VariableType INT_ARR;
    public static final VariableType INVALID;
    public static final VariableType LONG_ARR;
    public static final VariableType NUM;
    public static final VariableType NUM_ARR;
    public static final VariableType OBJ;
    public static final VariableType OBJ_ARR;
    public static final VariableType SHORT_ARR;
    public static final VariableType STR;
    public static final VariableType STR_ARR;
    public final Class mTypeClass;

    static 
    {
        INVALID = new VariableType("INVALID", 0, null);
        NUM = new VariableType("NUM", 1, Double.TYPE);
        STR = new VariableType("STR", 2, java/lang/String);
        OBJ = new VariableType("OBJ", 3, java/lang/Object);
        NUM_ARR = new VariableType("NUM_ARR", 4, Double.TYPE);
        DOUBLE_ARR = new VariableType("DOUBLE_ARR", 5, Double.TYPE);
        FLOAT_ARR = new VariableType("FLOAT_ARR", 6, Float.TYPE);
        INT_ARR = new VariableType("INT_ARR", 7, Integer.TYPE);
        SHORT_ARR = new VariableType("SHORT_ARR", 8, Short.TYPE);
        BYTE_ARR = new VariableType("BYTE_ARR", 9, Byte.TYPE);
        LONG_ARR = new VariableType("LONG_ARR", 10, Long.TYPE);
        BOOLEAN_ARR = new VariableType("BOOLEAN_ARR", 11, Boolean.TYPE);
        CHAR_ARR = new VariableType("CHAR_ARR", 12, Character.TYPE);
        STR_ARR = new VariableType("STR_ARR", 13, java/lang/String);
        OBJ_ARR = new VariableType("OBJ_ARR", 14, java/lang/Object);
        $VALUES = (new VariableType[] {
            INVALID, NUM, STR, OBJ, NUM_ARR, DOUBLE_ARR, FLOAT_ARR, INT_ARR, SHORT_ARR, BYTE_ARR, 
            LONG_ARR, BOOLEAN_ARR, CHAR_ARR, STR_ARR, OBJ_ARR
        });
    }
}
