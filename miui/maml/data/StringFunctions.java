// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.util.Log;
import java.util.regex.PatternSyntaxException;
import miui.maml.util.Utils;

// Referenced classes of package miui.maml.data:
//            Expression, Variables

public class StringFunctions extends Expression.FunctionImpl
{
    private static final class Fun extends Enum
    {

        public static Fun valueOf(String s)
        {
            return (Fun)Enum.valueOf(miui/maml/data/StringFunctions$Fun, s);
        }

        public static Fun[] values()
        {
            return $VALUES;
        }

        private static final Fun $VALUES[];
        public static final Fun INVALID;
        public static final Fun STR_CONTAINS;
        public static final Fun STR_ENDSWITH;
        public static final Fun STR_INDEXOF;
        public static final Fun STR_ISEMPTY;
        public static final Fun STR_LASTINDEXOF;
        public static final Fun STR_MATCHES;
        public static final Fun STR_REPLACE;
        public static final Fun STR_REPLACEALL;
        public static final Fun STR_REPLACEFIRST;
        public static final Fun STR_STARTWITH;
        public static final Fun STR_TOLOWER;
        public static final Fun STR_TOUPPER;
        public static final Fun STR_TRIM;

        static 
        {
            INVALID = new Fun("INVALID", 0);
            STR_TOLOWER = new Fun("STR_TOLOWER", 1);
            STR_TOUPPER = new Fun("STR_TOUPPER", 2);
            STR_TRIM = new Fun("STR_TRIM", 3);
            STR_REPLACE = new Fun("STR_REPLACE", 4);
            STR_REPLACEALL = new Fun("STR_REPLACEALL", 5);
            STR_REPLACEFIRST = new Fun("STR_REPLACEFIRST", 6);
            STR_CONTAINS = new Fun("STR_CONTAINS", 7);
            STR_STARTWITH = new Fun("STR_STARTWITH", 8);
            STR_ENDSWITH = new Fun("STR_ENDSWITH", 9);
            STR_ISEMPTY = new Fun("STR_ISEMPTY", 10);
            STR_MATCHES = new Fun("STR_MATCHES", 11);
            STR_INDEXOF = new Fun("STR_INDEXOF", 12);
            STR_LASTINDEXOF = new Fun("STR_LASTINDEXOF", 13);
            $VALUES = (new Fun[] {
                INVALID, STR_TOLOWER, STR_TOUPPER, STR_TRIM, STR_REPLACE, STR_REPLACEALL, STR_REPLACEFIRST, STR_CONTAINS, STR_STARTWITH, STR_ENDSWITH, 
                STR_ISEMPTY, STR_MATCHES, STR_INDEXOF, STR_LASTINDEXOF
            });
        }

        private Fun(String s, int i)
        {
            super(s, i);
        }
    }


    private static int[] _2D_getmiui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues != null)
            return _2D_miui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues;
        int ai[] = new int[Fun.values().length];
        try
        {
            ai[Fun.INVALID.ordinal()] = 14;
        }
        catch(NoSuchFieldError nosuchfielderror13) { }
        try
        {
            ai[Fun.STR_CONTAINS.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror12) { }
        try
        {
            ai[Fun.STR_ENDSWITH.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror11) { }
        try
        {
            ai[Fun.STR_INDEXOF.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror10) { }
        try
        {
            ai[Fun.STR_ISEMPTY.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai[Fun.STR_LASTINDEXOF.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai[Fun.STR_MATCHES.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[Fun.STR_REPLACE.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[Fun.STR_REPLACEALL.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[Fun.STR_REPLACEFIRST.ordinal()] = 9;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[Fun.STR_STARTWITH.ordinal()] = 10;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Fun.STR_TOLOWER.ordinal()] = 11;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Fun.STR_TOUPPER.ordinal()] = 12;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Fun.STR_TRIM.ordinal()] = 13;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues = ai;
        return ai;
    }

    private StringFunctions(Fun fun, int i)
    {
        super(i);
        mFun = fun;
    }

    public static void load()
    {
        Expression.FunctionExpression.registerFunction("strToLowerCase", new StringFunctions(Fun.STR_TOLOWER, 1));
        Expression.FunctionExpression.registerFunction("strToUpperCase", new StringFunctions(Fun.STR_TOUPPER, 1));
        Expression.FunctionExpression.registerFunction("strTrim", new StringFunctions(Fun.STR_TRIM, 1));
        Expression.FunctionExpression.registerFunction("strReplace", new StringFunctions(Fun.STR_REPLACE, 3));
        Expression.FunctionExpression.registerFunction("strReplaceAll", new StringFunctions(Fun.STR_REPLACEALL, 3));
        Expression.FunctionExpression.registerFunction("strReplaceFirst", new StringFunctions(Fun.STR_REPLACEFIRST, 3));
        Expression.FunctionExpression.registerFunction("strContains", new StringFunctions(Fun.STR_CONTAINS, 2));
        Expression.FunctionExpression.registerFunction("strStartsWith", new StringFunctions(Fun.STR_STARTWITH, 2));
        Expression.FunctionExpression.registerFunction("strEndsWith", new StringFunctions(Fun.STR_ENDSWITH, 2));
        Expression.FunctionExpression.registerFunction("strIsEmpty", new StringFunctions(Fun.STR_ISEMPTY, 1));
        Expression.FunctionExpression.registerFunction("strMatches", new StringFunctions(Fun.STR_MATCHES, 2));
        Expression.FunctionExpression.registerFunction("strIndexOf", new StringFunctions(Fun.STR_INDEXOF, 2));
        Expression.FunctionExpression.registerFunction("strLastIndexOf", new StringFunctions(Fun.STR_LASTINDEXOF, 2));
    }

    public double evaluate(Expression aexpression[], Variables variables)
    {
        byte byte0;
        boolean flag;
        boolean flag1;
        boolean flag2;
        int i;
        boolean flag3;
        byte0 = -1;
        flag = true;
        flag1 = true;
        flag2 = true;
        i = 1;
        flag3 = false;
        _2D_getmiui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues()[mFun.ordinal()];
        JVM INSTR tableswitch 7 13: default 72
    //                   7 213
    //                   8 213
    //                   9 213
    //                   10 72
    //                   11 213
    //                   12 213
    //                   13 213;
           goto _L1 _L2 _L2 _L2 _L1 _L2 _L2 _L2
_L1:
        variables = aexpression[0].evaluateStr();
        _2D_getmiui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues()[mFun.ordinal()];
        JVM INSTR tableswitch 4 4: default 108
    //                   4 224;
           goto _L3 _L4
_L3:
        aexpression = aexpression[1].evaluateStr();
        _2D_getmiui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues()[mFun.ordinal()];
        JVM INSTR tableswitch 1 10: default 180
    //                   1 246
    //                   2 302
    //                   3 394
    //                   4 180
    //                   5 419
    //                   6 332
    //                   7 180
    //                   8 180
    //                   9 180
    //                   10 272;
           goto _L5 _L6 _L7 _L8 _L5 _L9 _L10 _L5 _L5 _L5 _L11
_L5:
        Log.e("Expression", (new StringBuilder()).append("fail to evalute FunctionExpression, invalid function: ").append(mFun.toString()).toString());
        return 0.0D;
_L2:
        return Utils.stringToDouble(evaluateStr(aexpression, variables), 0.0D);
_L4:
        if(variables == null) goto _L13; else goto _L12
_L12:
        i = ((flag3) ? 1 : 0);
        if(!variables.isEmpty()) goto _L14; else goto _L13
_L13:
        i = 1;
_L14:
        return (double)i;
_L6:
        if(variables == null || aexpression == null || !variables.contains(aexpression))
            i = 0;
        return (double)i;
_L11:
        if(variables != null && aexpression != null && variables.startsWith(aexpression))
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        return (double)i;
_L7:
        if(variables != null && aexpression != null && variables.endsWith(aexpression))
            i = ((flag1) ? 1 : 0);
        else
            i = 0;
        return (double)i;
_L10:
        if(variables == null || aexpression == null) goto _L16; else goto _L15
_L15:
        boolean flag4;
        try
        {
            flag4 = variables.matches(aexpression);
        }
        // Misplaced declaration of an exception variable
        catch(Variables variables)
        {
            Log.w("Expression", (new StringBuilder()).append("invaid pattern of matches: ").append(aexpression).toString());
            return 0.0D;
        }
_L18:
        if(!flag4) goto _L16; else goto _L17
_L17:
        i = ((flag2) ? 1 : 0);
_L19:
        return (double)i;
_L16:
        i = 0;
        if(true) goto _L19; else goto _L18
_L8:
        int j;
        if(variables != null && aexpression != null)
            j = variables.indexOf(aexpression);
        else
            j = -1;
        return (double)j;
_L9:
        int k = byte0;
        if(variables != null)
        {
            k = byte0;
            if(aexpression != null)
                k = variables.lastIndexOf(aexpression);
        }
        return (double)k;
    }

    public String evaluateStr(Expression aexpression[], Variables variables)
    {
        switch(_2D_getmiui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues()[mFun.ordinal()])
        {
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        default:
            variables = aexpression[0].evaluateStr();
            if(variables == null)
                return null;
            break;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 10: // '\n'
            return Utils.doubleToString(evaluate(aexpression, variables));
        }
        String s;
        switch(_2D_getmiui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues()[mFun.ordinal()])
        {
        default:
            s = aexpression[1].evaluateStr();
            aexpression = aexpression[2].evaluateStr();
            if(s == null || aexpression == null)
                return variables;
            break;

        case 11: // '\013'
            return variables.toLowerCase();

        case 12: // '\f'
            return variables.toUpperCase();

        case 13: // '\r'
            return variables.trim();
        }
        switch(_2D_getmiui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues()[mFun.ordinal()])
        {
        default:
            Log.e("Expression", (new StringBuilder()).append("fail to evaluteStr FunctionExpression, invalid function: ").append(mFun.toString()).toString());
            return null;

        case 7: // '\007'
            return variables.replace(s, aexpression);

        case 8: // '\b'
            try
            {
                aexpression = variables.replaceAll(s, aexpression);
            }
            // Misplaced declaration of an exception variable
            catch(Expression aexpression[])
            {
                Log.w("Expression", (new StringBuilder()).append("invaid pattern of replaceAll: ").append(s).toString());
                return variables;
            }
            return aexpression;

        case 9: // '\t'
            break;
        }
        try
        {
            aexpression = variables.replaceFirst(s, aexpression);
        }
        // Misplaced declaration of an exception variable
        catch(Expression aexpression[])
        {
            Log.w("Expression", (new StringBuilder()).append("invaid pattern of replaceFirst:").append(s).toString());
            return variables;
        }
        return aexpression;
    }

    private static final int _2D_miui_2D_maml_2D_data_2D_StringFunctions$FunSwitchesValues[];
    private static final String LOG_TAG = "Expression";
    private final Fun mFun;
}
