// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.text.TextUtils;
import android.util.Log;
import java.math.BigDecimal;
import miui.maml.util.Utils;
import miui.util.HashUtils;

// Referenced classes of package miui.maml.data:
//            Expression, Variables

public class BaseFunctions extends Expression.FunctionImpl
{
    private static final class Fun extends Enum
    {

        public static Fun valueOf(String s)
        {
            return (Fun)Enum.valueOf(miui/maml/data/BaseFunctions$Fun, s);
        }

        public static Fun[] values()
        {
            return $VALUES;
        }

        private static final Fun $VALUES[];
        public static final Fun ABS;
        public static final Fun ACOS;
        public static final Fun AND;
        public static final Fun ASIN;
        public static final Fun ATAN;
        public static final Fun COS;
        public static final Fun COSH;
        public static final Fun DIGIT;
        public static final Fun EQ;
        public static final Fun EQS;
        public static final Fun EVAL;
        public static final Fun GE;
        public static final Fun GT;
        public static final Fun HASH;
        public static final Fun IFELSE;
        public static final Fun INT;
        public static final Fun INVALID;
        public static final Fun ISNULL;
        public static final Fun LE;
        public static final Fun LEN;
        public static final Fun LOG;
        public static final Fun LOG10;
        public static final Fun LT;
        public static final Fun MAX;
        public static final Fun MIN;
        public static final Fun NE;
        public static final Fun NOT;
        public static final Fun NUM;
        public static final Fun OR;
        public static final Fun POW;
        public static final Fun PRECISE_EVAL;
        public static final Fun RAND;
        public static final Fun ROUND;
        public static final Fun SIN;
        public static final Fun SINH;
        public static final Fun SQRT;
        public static final Fun SUBSTR;
        public static final Fun TAN;

        static 
        {
            INVALID = new Fun("INVALID", 0);
            RAND = new Fun("RAND", 1);
            SIN = new Fun("SIN", 2);
            COS = new Fun("COS", 3);
            TAN = new Fun("TAN", 4);
            ASIN = new Fun("ASIN", 5);
            ACOS = new Fun("ACOS", 6);
            ATAN = new Fun("ATAN", 7);
            SINH = new Fun("SINH", 8);
            COSH = new Fun("COSH", 9);
            SQRT = new Fun("SQRT", 10);
            ABS = new Fun("ABS", 11);
            LEN = new Fun("LEN", 12);
            EVAL = new Fun("EVAL", 13);
            PRECISE_EVAL = new Fun("PRECISE_EVAL", 14);
            ROUND = new Fun("ROUND", 15);
            INT = new Fun("INT", 16);
            NUM = new Fun("NUM", 17);
            MIN = new Fun("MIN", 18);
            MAX = new Fun("MAX", 19);
            POW = new Fun("POW", 20);
            LOG = new Fun("LOG", 21);
            LOG10 = new Fun("LOG10", 22);
            DIGIT = new Fun("DIGIT", 23);
            EQ = new Fun("EQ", 24);
            NE = new Fun("NE", 25);
            GE = new Fun("GE", 26);
            GT = new Fun("GT", 27);
            LE = new Fun("LE", 28);
            LT = new Fun("LT", 29);
            ISNULL = new Fun("ISNULL", 30);
            NOT = new Fun("NOT", 31);
            IFELSE = new Fun("IFELSE", 32);
            AND = new Fun("AND", 33);
            OR = new Fun("OR", 34);
            EQS = new Fun("EQS", 35);
            SUBSTR = new Fun("SUBSTR", 36);
            HASH = new Fun("HASH", 37);
            $VALUES = (new Fun[] {
                INVALID, RAND, SIN, COS, TAN, ASIN, ACOS, ATAN, SINH, COSH, 
                SQRT, ABS, LEN, EVAL, PRECISE_EVAL, ROUND, INT, NUM, MIN, MAX, 
                POW, LOG, LOG10, DIGIT, EQ, NE, GE, GT, LE, LT, 
                ISNULL, NOT, IFELSE, AND, OR, EQS, SUBSTR, HASH
            });
        }

        private Fun(String s, int i)
        {
            super(s, i);
        }
    }

    private static class NullObjFunction extends Expression.FunctionImpl
    {

        public double evaluate(Expression aexpression[], Variables variables)
        {
            int i = 0;
            aexpression = aexpression[0].evaluateStr();
            if(aexpression != mObjName)
            {
                mObjName = aexpression;
                if(TextUtils.isEmpty(mObjName) || variables.existsObj(mObjName) ^ true)
                    mVarIndex = -1;
                else
                    mVarIndex = variables.registerVariable(mObjName);
            }
            if(mVarIndex == -1)
                return 1.0D;
            if(variables.get(mVarIndex) == null)
                i = 1;
            return (double)i;
        }

        public String evaluateStr(Expression aexpression[], Variables variables)
        {
            return null;
        }

        private String mObjName;
        private int mVarIndex;

        public NullObjFunction()
        {
            super(1);
            mVarIndex = -1;
        }
    }


    private static int[] _2D_getmiui_2D_maml_2D_data_2D_BaseFunctions$FunSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_data_2D_BaseFunctions$FunSwitchesValues != null)
            return _2D_miui_2D_maml_2D_data_2D_BaseFunctions$FunSwitchesValues;
        int ai[] = new int[Fun.values().length];
        try
        {
            ai[Fun.ABS.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror37) { }
        try
        {
            ai[Fun.ACOS.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror36) { }
        try
        {
            ai[Fun.AND.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror35) { }
        try
        {
            ai[Fun.ASIN.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror34) { }
        try
        {
            ai[Fun.ATAN.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror33) { }
        try
        {
            ai[Fun.COS.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror32) { }
        try
        {
            ai[Fun.COSH.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror31) { }
        try
        {
            ai[Fun.DIGIT.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror30) { }
        try
        {
            ai[Fun.EQ.ordinal()] = 9;
        }
        catch(NoSuchFieldError nosuchfielderror29) { }
        try
        {
            ai[Fun.EQS.ordinal()] = 10;
        }
        catch(NoSuchFieldError nosuchfielderror28) { }
        try
        {
            ai[Fun.EVAL.ordinal()] = 11;
        }
        catch(NoSuchFieldError nosuchfielderror27) { }
        try
        {
            ai[Fun.GE.ordinal()] = 12;
        }
        catch(NoSuchFieldError nosuchfielderror26) { }
        try
        {
            ai[Fun.GT.ordinal()] = 13;
        }
        catch(NoSuchFieldError nosuchfielderror25) { }
        try
        {
            ai[Fun.HASH.ordinal()] = 14;
        }
        catch(NoSuchFieldError nosuchfielderror24) { }
        try
        {
            ai[Fun.IFELSE.ordinal()] = 15;
        }
        catch(NoSuchFieldError nosuchfielderror23) { }
        try
        {
            ai[Fun.INT.ordinal()] = 16;
        }
        catch(NoSuchFieldError nosuchfielderror22) { }
        try
        {
            ai[Fun.INVALID.ordinal()] = 38;
        }
        catch(NoSuchFieldError nosuchfielderror21) { }
        try
        {
            ai[Fun.ISNULL.ordinal()] = 17;
        }
        catch(NoSuchFieldError nosuchfielderror20) { }
        try
        {
            ai[Fun.LE.ordinal()] = 18;
        }
        catch(NoSuchFieldError nosuchfielderror19) { }
        try
        {
            ai[Fun.LEN.ordinal()] = 19;
        }
        catch(NoSuchFieldError nosuchfielderror18) { }
        try
        {
            ai[Fun.LOG.ordinal()] = 20;
        }
        catch(NoSuchFieldError nosuchfielderror17) { }
        try
        {
            ai[Fun.LOG10.ordinal()] = 21;
        }
        catch(NoSuchFieldError nosuchfielderror16) { }
        try
        {
            ai[Fun.LT.ordinal()] = 22;
        }
        catch(NoSuchFieldError nosuchfielderror15) { }
        try
        {
            ai[Fun.MAX.ordinal()] = 23;
        }
        catch(NoSuchFieldError nosuchfielderror14) { }
        try
        {
            ai[Fun.MIN.ordinal()] = 24;
        }
        catch(NoSuchFieldError nosuchfielderror13) { }
        try
        {
            ai[Fun.NE.ordinal()] = 25;
        }
        catch(NoSuchFieldError nosuchfielderror12) { }
        try
        {
            ai[Fun.NOT.ordinal()] = 26;
        }
        catch(NoSuchFieldError nosuchfielderror11) { }
        try
        {
            ai[Fun.NUM.ordinal()] = 27;
        }
        catch(NoSuchFieldError nosuchfielderror10) { }
        try
        {
            ai[Fun.OR.ordinal()] = 28;
        }
        catch(NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai[Fun.POW.ordinal()] = 29;
        }
        catch(NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai[Fun.PRECISE_EVAL.ordinal()] = 30;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[Fun.RAND.ordinal()] = 31;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[Fun.ROUND.ordinal()] = 32;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[Fun.SIN.ordinal()] = 33;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[Fun.SINH.ordinal()] = 34;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Fun.SQRT.ordinal()] = 35;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Fun.SUBSTR.ordinal()] = 36;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Fun.TAN.ordinal()] = 37;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_data_2D_BaseFunctions$FunSwitchesValues = ai;
        return ai;
    }

    private BaseFunctions(Fun fun1, int i)
    {
        super(i);
        fun = fun1;
    }

    private int digit(int i, int j)
    {
        byte byte0 = -1;
        if(j <= 0)
            return -1;
        if(i == 0 && j == 1)
            return 0;
        for(int k = 0; i > 0 && k < j - 1; k++)
            i /= 10;

        j = byte0;
        if(i > 0)
            j = i % 10;
        return j;
    }

    public static void load()
    {
        Expression.FunctionExpression.registerFunction("rand", new BaseFunctions(Fun.RAND, 0));
        Expression.FunctionExpression.registerFunction("sin", new BaseFunctions(Fun.SIN, 1));
        Expression.FunctionExpression.registerFunction("cos", new BaseFunctions(Fun.COS, 1));
        Expression.FunctionExpression.registerFunction("tan", new BaseFunctions(Fun.TAN, 1));
        Expression.FunctionExpression.registerFunction("asin", new BaseFunctions(Fun.ASIN, 1));
        Expression.FunctionExpression.registerFunction("acos", new BaseFunctions(Fun.ACOS, 1));
        Expression.FunctionExpression.registerFunction("atan", new BaseFunctions(Fun.ATAN, 1));
        Expression.FunctionExpression.registerFunction("sinh", new BaseFunctions(Fun.SINH, 1));
        Expression.FunctionExpression.registerFunction("cosh", new BaseFunctions(Fun.COSH, 1));
        Expression.FunctionExpression.registerFunction("sqrt", new BaseFunctions(Fun.SQRT, 1));
        Expression.FunctionExpression.registerFunction("abs", new BaseFunctions(Fun.ABS, 1));
        Expression.FunctionExpression.registerFunction("len", new BaseFunctions(Fun.LEN, 1));
        Expression.FunctionExpression.registerFunction("eval", new BaseFunctions(Fun.EVAL, 1));
        Expression.FunctionExpression.registerFunction("preciseeval", new BaseFunctions(Fun.PRECISE_EVAL, 2));
        Expression.FunctionExpression.registerFunction("round", new BaseFunctions(Fun.ROUND, 1));
        Expression.FunctionExpression.registerFunction("int", new BaseFunctions(Fun.INT, 1));
        Expression.FunctionExpression.registerFunction("num", new BaseFunctions(Fun.NUM, 1));
        Expression.FunctionExpression.registerFunction("isnull", new BaseFunctions(Fun.ISNULL, 1));
        Expression.FunctionExpression.registerFunction("not", new BaseFunctions(Fun.NOT, 1));
        Expression.FunctionExpression.registerFunction("min", new BaseFunctions(Fun.MIN, 2));
        Expression.FunctionExpression.registerFunction("max", new BaseFunctions(Fun.MAX, 2));
        Expression.FunctionExpression.registerFunction("pow", new BaseFunctions(Fun.POW, 2));
        Expression.FunctionExpression.registerFunction("log", new BaseFunctions(Fun.LOG, 1));
        Expression.FunctionExpression.registerFunction("log10", new BaseFunctions(Fun.LOG10, 1));
        Expression.FunctionExpression.registerFunction("digit", new BaseFunctions(Fun.DIGIT, 2));
        Expression.FunctionExpression.registerFunction("eq", new BaseFunctions(Fun.EQ, 2));
        Expression.FunctionExpression.registerFunction("ne", new BaseFunctions(Fun.NE, 2));
        Expression.FunctionExpression.registerFunction("ge", new BaseFunctions(Fun.GE, 2));
        Expression.FunctionExpression.registerFunction("gt", new BaseFunctions(Fun.GT, 2));
        Expression.FunctionExpression.registerFunction("le", new BaseFunctions(Fun.LE, 2));
        Expression.FunctionExpression.registerFunction("lt", new BaseFunctions(Fun.LT, 2));
        Expression.FunctionExpression.registerFunction("ifelse", new BaseFunctions(Fun.IFELSE, 3));
        Expression.FunctionExpression.registerFunction("and", new BaseFunctions(Fun.AND, 2));
        Expression.FunctionExpression.registerFunction("or", new BaseFunctions(Fun.OR, 2));
        Expression.FunctionExpression.registerFunction("eqs", new BaseFunctions(Fun.EQS, 2));
        Expression.FunctionExpression.registerFunction("substr", new BaseFunctions(Fun.SUBSTR, 2));
        Expression.FunctionExpression.registerFunction("hash", new BaseFunctions(Fun.HASH, 2));
        Expression.FunctionExpression.registerFunction("nullobj", new NullObjFunction());
    }

    public double evaluate(Expression aexpression[], Variables variables)
    {
        switch(_2D_getmiui_2D_maml_2D_data_2D_BaseFunctions$FunSwitchesValues()[fun.ordinal()])
        {
        default:
            double d = aexpression[0].evaluate();
            switch(_2D_getmiui_2D_maml_2D_data_2D_BaseFunctions$FunSwitchesValues()[fun.ordinal()])
            {
            case 14: // '\016'
            case 31: // '\037'
            default:
                Log.e("Expression", (new StringBuilder()).append("fail to evalute FunctionExpression, invalid function: ").append(fun.toString()).toString());
                return 0.0D;

            case 33: // '!'
                return Math.sin(d);

            case 6: // '\006'
                return Math.cos(d);

            case 37: // '%'
                return Math.tan(d);

            case 4: // '\004'
                return Math.asin(d);

            case 2: // '\002'
                return Math.acos(d);

            case 5: // '\005'
                return Math.atan(d);

            case 34: // '"'
                return Math.sinh(d);

            case 7: // '\007'
                return Math.cosh(d);

            case 35: // '#'
                return Math.sqrt(d);

            case 1: // '\001'
                return Math.abs(d);

            case 19: // '\023'
                aexpression = aexpression[0].evaluateStr();
                if(aexpression == null)
                    return 0.0D;
                else
                    return (double)aexpression.length();

            case 11: // '\013'
                aexpression = aexpression[0].evaluateStr();
                if(aexpression == null)
                    return 0.0D;
                if(!aexpression.equals(mEvalExpStr))
                {
                    mEvalExpStr = aexpression;
                    mEvalExp = Expression.build(variables, mEvalExpStr);
                }
                if(mEvalExp == null)
                    d = 0.0D;
                else
                    d = mEvalExp.evaluate();
                return d;

            case 30: // '\036'
                String s = aexpression[0].evaluateStr();
                if(s == null)
                    return 0.0D;
                if(!s.equals(mEvalExpStr))
                {
                    mEvalExpStr = s;
                    mEvalExp = Expression.build(variables, mEvalExpStr);
                }
                if(mEvalExp != null)
                    variables = mEvalExp.preciseEvaluate();
                else
                    variables = null;
                if(variables != null)
                {
                    int i = variables.scale();
                    int j3 = (int)aexpression[1].evaluate();
                    aexpression = variables;
                    if(j3 > 0)
                    {
                        aexpression = variables;
                        if(i > j3)
                            aexpression = variables.setScale(j3, 4);
                    }
                    return aexpression.doubleValue();
                } else
                {
                    return (0.0D / 0.0D);
                }

            case 32: // ' '
                return (double)Math.round(d);

            case 16: // '\020'
                return (double)(int)(long)d;

            case 27: // '\033'
                return d;

            case 17: // '\021'
                int j;
                if(aexpression[0].isNull())
                    j = 1;
                else
                    j = 0;
                return (double)j;

            case 26: // '\032'
                int k;
                if(d > 0.0D)
                    k = 0;
                else
                    k = 1;
                return (double)k;

            case 24: // '\030'
                return Math.min(d, aexpression[1].evaluate());

            case 23: // '\027'
                return Math.max(d, aexpression[1].evaluate());

            case 29: // '\035'
                return Math.pow(d, aexpression[1].evaluate());

            case 20: // '\024'
                return Math.log(d);

            case 21: // '\025'
                return Math.log10(d);

            case 8: // '\b'
                return (double)digit((int)d, (int)aexpression[1].evaluate());

            case 9: // '\t'
                int l;
                if(d == aexpression[1].evaluate())
                    l = 1;
                else
                    l = 0;
                return (double)l;

            case 25: // '\031'
                int i1;
                if(d != aexpression[1].evaluate())
                    i1 = 1;
                else
                    i1 = 0;
                return (double)i1;

            case 12: // '\f'
                int j1;
                if(d >= aexpression[1].evaluate())
                    j1 = 1;
                else
                    j1 = 0;
                return (double)j1;

            case 13: // '\r'
                int k1;
                if(d > aexpression[1].evaluate())
                    k1 = 1;
                else
                    k1 = 0;
                return (double)k1;

            case 18: // '\022'
                int l1;
                if(d <= aexpression[1].evaluate())
                    l1 = 1;
                else
                    l1 = 0;
                return (double)l1;

            case 22: // '\026'
                int i2;
                if(d < aexpression[1].evaluate())
                    i2 = 1;
                else
                    i2 = 0;
                return (double)i2;

            case 15: // '\017'
                int k3 = aexpression.length;
                if(k3 % 2 != 1)
                {
                    Log.e("Expression", (new StringBuilder()).append("function parameter number should be 2*n+1: ").append(fun.toString()).toString());
                    return 0.0D;
                }
                for(int j2 = 0; j2 < (k3 - 1) / 2; j2++)
                    if(aexpression[j2 * 2].evaluate() > 0.0D)
                        return aexpression[j2 * 2 + 1].evaluate();

                return aexpression[k3 - 1].evaluate();

            case 3: // '\003'
                int l3 = aexpression.length;
                for(int k2 = 0; k2 < l3; k2++)
                    if(aexpression[k2].evaluate() <= 0.0D)
                        return 0.0D;

                return 1.0D;

            case 28: // '\034'
                int i4 = aexpression.length;
                for(int l2 = 0; l2 < i4; l2++)
                    if(aexpression[l2].evaluate() > 0.0D)
                        return 1.0D;

                return 0.0D;

            case 10: // '\n'
                int i3;
                if(TextUtils.equals(aexpression[0].evaluateStr(), aexpression[1].evaluateStr()))
                    i3 = 1;
                else
                    i3 = 0;
                return (double)i3;

            case 36: // '$'
                return Utils.stringToDouble(evaluateStr(aexpression, variables), 0.0D);
            }

        case 31: // '\037'
            return Math.random();
        }
    }

    public String evaluateStr(Expression aexpression[], Variables variables)
    {
        _2D_getmiui_2D_maml_2D_data_2D_BaseFunctions$FunSwitchesValues()[fun.ordinal()];
        JVM INSTR lookupswitch 4: default 52
    //                   11: 243
    //                   14: 306
    //                   15: 62
    //                   36: 160;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return Utils.doubleToString(evaluate(aexpression, variables));
_L4:
        int i = aexpression.length;
        if(i % 2 != 1)
        {
            Log.e("Expression", (new StringBuilder()).append("function parameter number should be 2*n+1: ").append(fun.toString()).toString());
            return null;
        }
        for(int k = 0; k < (i - 1) / 2; k++)
            if(aexpression[k * 2].evaluate() > 0.0D)
                return aexpression[k * 2 + 1].evaluateStr();

        return aexpression[i - 1].evaluateStr();
_L5:
        int l;
        int j1;
        variables = aexpression[0].evaluateStr();
        if(variables == null)
            return null;
        l = aexpression.length;
        j1 = (int)aexpression[1].evaluate();
        if(l < 3) goto _L7; else goto _L6
_L6:
        int j;
        int i1;
        int k1;
        try
        {
            k1 = (int)aexpression[2].evaluate();
            j = variables.length();
        }
        // Misplaced declaration of an exception variable
        catch(Expression aexpression[])
        {
            return null;
        }
        i1 = k1;
        if(k1 > j)
            i1 = j;
        return variables.substring(j1, j1 + i1);
_L7:
        aexpression = variables.substring(j1);
        return aexpression;
_L2:
        aexpression = aexpression[0].evaluateStr();
        if(aexpression == null)
            return null;
        if(!aexpression.equals(mEvalExpStr))
        {
            mEvalExpStr = aexpression;
            mEvalExp = Expression.build(variables, mEvalExpStr);
        }
        if(mEvalExp == null)
            aexpression = null;
        else
            aexpression = mEvalExp.evaluateStr();
        return aexpression;
_L3:
        variables = aexpression[0].evaluateStr();
        aexpression = aexpression[1].evaluateStr();
        if(variables == null || aexpression == null)
            return null;
        else
            return HashUtils.getHash(variables, aexpression);
    }

    private static final int _2D_miui_2D_maml_2D_data_2D_BaseFunctions$FunSwitchesValues[];
    private static final String LOG_TAG = "Expression";
    private Fun fun;
    private Expression mEvalExp;
    private String mEvalExpStr;
}
