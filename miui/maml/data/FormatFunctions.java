// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import java.util.IllegalFormatException;

// Referenced classes of package miui.maml.data:
//            Expression, DateTimeVariableUpdater, Variables

public class FormatFunctions extends Expression.FunctionImpl
{
    private static final class Fun extends Enum
    {

        public static Fun valueOf(String s)
        {
            return (Fun)Enum.valueOf(miui/maml/data/FormatFunctions$Fun, s);
        }

        public static Fun[] values()
        {
            return $VALUES;
        }

        private static final Fun $VALUES[];
        public static final Fun FORMAT_DATE;
        public static final Fun FORMAT_FLOAT;
        public static final Fun FORMAT_INT;
        public static final Fun INVALID;

        static 
        {
            INVALID = new Fun("INVALID", 0);
            FORMAT_DATE = new Fun("FORMAT_DATE", 1);
            FORMAT_FLOAT = new Fun("FORMAT_FLOAT", 2);
            FORMAT_INT = new Fun("FORMAT_INT", 3);
            $VALUES = (new Fun[] {
                INVALID, FORMAT_DATE, FORMAT_FLOAT, FORMAT_INT
            });
        }

        private Fun(String s, int i)
        {
            super(s, i);
        }
    }


    private static int[] _2D_getmiui_2D_maml_2D_data_2D_FormatFunctions$FunSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_data_2D_FormatFunctions$FunSwitchesValues != null)
            return _2D_miui_2D_maml_2D_data_2D_FormatFunctions$FunSwitchesValues;
        int ai[] = new int[Fun.values().length];
        try
        {
            ai[Fun.FORMAT_DATE.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Fun.FORMAT_FLOAT.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Fun.FORMAT_INT.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Fun.INVALID.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_data_2D_FormatFunctions$FunSwitchesValues = ai;
        return ai;
    }

    private FormatFunctions(Fun fun, int i)
    {
        super(i);
        mFun = fun;
    }

    public static void load()
    {
        Expression.FunctionExpression.registerFunction("formatDate", new FormatFunctions(Fun.FORMAT_DATE, 2));
        Expression.FunctionExpression.registerFunction("formatFloat", new FormatFunctions(Fun.FORMAT_FLOAT, 2));
        Expression.FunctionExpression.registerFunction("formatInt", new FormatFunctions(Fun.FORMAT_INT, 2));
    }

    public double evaluate(Expression aexpression[], Variables variables)
    {
        return 0.0D;
    }

    public String evaluateStr(Expression aexpression[], Variables variables)
    {
        variables = aexpression[0].evaluateStr();
        if(variables == null)
            return null;
        _2D_getmiui_2D_maml_2D_data_2D_FormatFunctions$FunSwitchesValues()[mFun.ordinal()];
        JVM INSTR tableswitch 1 3: default 52
    //                   1 54
    //                   2 66
    //                   3 89;
           goto _L1 _L2 _L3 _L4
_L1:
        return null;
_L2:
        return DateTimeVariableUpdater.formatDate(variables, (long)aexpression[1].evaluate());
_L3:
        aexpression = String.format(variables, new Object[] {
            Double.valueOf(aexpression[1].evaluate())
        });
        return aexpression;
_L4:
        try
        {
            aexpression = String.format(variables, new Object[] {
                Integer.valueOf((int)aexpression[1].evaluate())
            });
        }
        // Misplaced declaration of an exception variable
        catch(Expression aexpression[])
        {
            continue; /* Loop/switch isn't completed */
        }
        return aexpression;
        aexpression;
        if(true) goto _L1; else goto _L5
_L5:
    }

    private static final int _2D_miui_2D_maml_2D_data_2D_FormatFunctions$FunSwitchesValues[];
    private final Fun mFun;
}
