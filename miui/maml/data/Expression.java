// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.text.TextUtils;
import android.util.Log;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import miui.maml.util.Utils;

// Referenced classes of package miui.maml.data:
//            RootExpression, ExpressionVisitor, Variables, IndexedVariable, 
//            FunctionsLoader

public abstract class Expression
{
    static abstract class ArrayVariableExpression extends VariableExpression
    {

        public void accept(ExpressionVisitor expressionvisitor)
        {
            expressionvisitor.visit(this);
            mIndexExp.accept(expressionvisitor);
        }

        protected Expression mIndexExp;

        public ArrayVariableExpression(Variables variables, String s, Expression expression)
        {
            super(variables, s, false);
            mIndexExp = expression;
        }
    }

    static class BinaryExpression extends Expression
    {

        private static int[] _2D_getmiui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues;
            int ai[] = new int[Ope.values().length];
            try
            {
                ai[Ope.ADD.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror20) { }
            try
            {
                ai[Ope.AND.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror19) { }
            try
            {
                ai[Ope.BIT_AND.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror18) { }
            try
            {
                ai[Ope.BIT_LSHIFT.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror17) { }
            try
            {
                ai[Ope.BIT_NOT.ordinal()] = 19;
            }
            catch(NoSuchFieldError nosuchfielderror16) { }
            try
            {
                ai[Ope.BIT_OR.ordinal()] = 5;
            }
            catch(NoSuchFieldError nosuchfielderror15) { }
            try
            {
                ai[Ope.BIT_RSHIFT.ordinal()] = 6;
            }
            catch(NoSuchFieldError nosuchfielderror14) { }
            try
            {
                ai[Ope.BIT_XOR.ordinal()] = 7;
            }
            catch(NoSuchFieldError nosuchfielderror13) { }
            try
            {
                ai[Ope.DIV.ordinal()] = 8;
            }
            catch(NoSuchFieldError nosuchfielderror12) { }
            try
            {
                ai[Ope.EQ.ordinal()] = 9;
            }
            catch(NoSuchFieldError nosuchfielderror11) { }
            try
            {
                ai[Ope.GE.ordinal()] = 10;
            }
            catch(NoSuchFieldError nosuchfielderror10) { }
            try
            {
                ai[Ope.GT.ordinal()] = 11;
            }
            catch(NoSuchFieldError nosuchfielderror9) { }
            try
            {
                ai[Ope.INVALID.ordinal()] = 20;
            }
            catch(NoSuchFieldError nosuchfielderror8) { }
            try
            {
                ai[Ope.LE.ordinal()] = 12;
            }
            catch(NoSuchFieldError nosuchfielderror7) { }
            try
            {
                ai[Ope.LT.ordinal()] = 13;
            }
            catch(NoSuchFieldError nosuchfielderror6) { }
            try
            {
                ai[Ope.MIN.ordinal()] = 14;
            }
            catch(NoSuchFieldError nosuchfielderror5) { }
            try
            {
                ai[Ope.MOD.ordinal()] = 15;
            }
            catch(NoSuchFieldError nosuchfielderror4) { }
            try
            {
                ai[Ope.MUL.ordinal()] = 16;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[Ope.NEQ.ordinal()] = 17;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[Ope.NOT.ordinal()] = 21;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[Ope.OR.ordinal()] = 18;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues = ai;
            return ai;
        }

        public void accept(ExpressionVisitor expressionvisitor)
        {
            expressionvisitor.visit(this);
            mExp1.accept(expressionvisitor);
            mExp2.accept(expressionvisitor);
        }

        public double evaluate()
        {
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            int i;
            boolean flag6;
            flag = true;
            flag1 = true;
            flag2 = true;
            flag3 = true;
            flag4 = true;
            flag5 = true;
            i = 1;
            flag6 = false;
            _2D_getmiui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues()[mOpe.ordinal()];
            JVM INSTR tableswitch 1 18: default 120
        //                       1 130
        //                       2 366
        //                       3 210
        //                       4 267
        //                       5 229
        //                       6 287
        //                       7 248
        //                       8 178
        //                       9 307
        //                       10 469
        //                       11 438
        //                       12 533
        //                       13 501
        //                       14 146
        //                       15 194
        //                       16 162
        //                       17 335
        //                       18 403;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19
_L1:
            Log.e("Expression", "fail to evalute BinaryExpression, invalid operator");
            return 0.0D;
_L2:
            return mExp1.evaluate() + mExp2.evaluate();
_L15:
            return mExp1.evaluate() - mExp2.evaluate();
_L17:
            return mExp1.evaluate() * mExp2.evaluate();
_L9:
            return mExp1.evaluate() / mExp2.evaluate();
_L16:
            return mExp1.evaluate() % mExp2.evaluate();
_L4:
            return (double)((long)mExp1.evaluate() & (long)mExp2.evaluate());
_L6:
            return (double)((long)mExp1.evaluate() | (long)mExp2.evaluate());
_L8:
            return (double)((long)mExp1.evaluate() ^ (long)mExp2.evaluate());
_L5:
            return (double)((long)mExp1.evaluate() << (int)(long)mExp2.evaluate());
_L7:
            return (double)((long)mExp1.evaluate() >> (int)(long)mExp2.evaluate());
_L10:
            if(mExp1.evaluate() != mExp2.evaluate())
                i = 0;
            return (double)i;
_L18:
            if(mExp1.evaluate() != mExp2.evaluate())
                i = ((flag) ? 1 : 0);
            else
                i = 0;
            return (double)i;
_L3:
            if(mExp1.evaluate() > 0.0D && mExp2.evaluate() > 0.0D)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            return (double)i;
_L19:
            if(mExp1.evaluate() > 0.0D) goto _L21; else goto _L20
_L20:
            i = ((flag6) ? 1 : 0);
            if(mExp2.evaluate() <= 0.0D) goto _L22; else goto _L21
_L21:
            i = 1;
_L22:
            return (double)i;
_L12:
            int j;
            if(mExp1.evaluate() > mExp2.evaluate())
                j = ((flag2) ? 1 : 0);
            else
                j = 0;
            return (double)j;
_L11:
            int k;
            if(mExp1.evaluate() >= mExp2.evaluate())
                k = ((flag3) ? 1 : 0);
            else
                k = 0;
            return (double)k;
_L14:
            int l;
            if(mExp1.evaluate() < mExp2.evaluate())
                l = ((flag4) ? 1 : 0);
            else
                l = 0;
            return (double)l;
_L13:
            int i1;
            if(mExp1.evaluate() <= mExp2.evaluate())
                i1 = ((flag5) ? 1 : 0);
            else
                i1 = 0;
            return (double)i1;
        }

        public String evaluateStr()
        {
            String s = mExp1.evaluateStr();
            String s1 = mExp2.evaluateStr();
            switch(_2D_getmiui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues()[mOpe.ordinal()])
            {
            default:
                Log.e("Expression", "fail to evalute string BinaryExpression, invalid operator");
                return null;

            case 1: // '\001'
                break;
            }
            if(s == null && s1 == null)
                return null;
            if(s == null)
                return s1;
            if(s1 == null)
                return s;
            else
                return (new StringBuilder()).append(s).append(s1).toString();
        }

        public boolean isNull()
        {
            boolean flag = true;
            switch(_2D_getmiui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues()[mOpe.ordinal()])
            {
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            default:
                return true;

            case 1: // '\001'
            case 14: // '\016'
                if(mExp1.isNull())
                    flag = mExp2.isNull();
                else
                    flag = false;
                return flag;

            case 2: // '\002'
            case 8: // '\b'
            case 9: // '\t'
            case 15: // '\017'
            case 16: // '\020'
            case 17: // '\021'
            case 18: // '\022'
                break;
            }
            if(!mExp1.isNull())
                flag = mExp2.isNull();
            return flag;
        }

        public BigDecimal preciseEvaluate()
        {
            if(mOpe == Ope.INVALID) goto _L2; else goto _L1
_L1:
            Object obj;
            BigDecimal bigdecimal;
            obj = mExp1.preciseEvaluate();
            bigdecimal = mExp2.preciseEvaluate();
            if(obj == null || bigdecimal == null) goto _L2; else goto _L3
_L3:
            _2D_getmiui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues()[mOpe.ordinal()];
            JVM INSTR lookupswitch 5: default 96
        //                       1: 106
        //                       8: 124
        //                       14: 112
        //                       15: 138
        //                       16: 118;
               goto _L2 _L4 _L5 _L6 _L7 _L8
_L2:
            Log.e("Expression", "fail to PRECISE evalute BinaryExpression, invalid operator");
            return null;
_L4:
            return ((BigDecimal) (obj)).add(bigdecimal);
_L6:
            return ((BigDecimal) (obj)).subtract(bigdecimal);
_L8:
            return ((BigDecimal) (obj)).multiply(bigdecimal);
_L5:
            try
            {
                obj = ((BigDecimal) (obj)).divide(bigdecimal, MathContext.DECIMAL128);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                return null;
            }
            return ((BigDecimal) (obj));
_L7:
            try
            {
                obj = ((BigDecimal) (obj)).remainder(bigdecimal);
            }
            catch(Exception exception)
            {
                return null;
            }
            return ((BigDecimal) (obj));
        }

        private static final int _2D_miui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues[];
        private Expression mExp1;
        private Expression mExp2;
        private Ope mOpe;

        public BinaryExpression(Expression expression, Expression expression1, Ope ope)
        {
            mOpe = Ope.INVALID;
            mExp1 = expression;
            mExp2 = expression1;
            mOpe = ope;
            if(mOpe == Ope.INVALID)
                Log.e("Expression", (new StringBuilder()).append("BinaryExpression: invalid operator:").append(ope).toString());
        }
    }

    public static class FunctionExpression extends Expression
    {

        private void parseFunction(String s)
            throws Exception
        {
            boolean flag = true;
            FunctionImpl functionimpl = (FunctionImpl)sFunMap.get(s);
            boolean flag1;
            if(functionimpl != null)
                flag1 = true;
            else
                flag1 = false;
            Utils.asserts(flag1, (new StringBuilder()).append("invalid function:").append(s).toString());
            mFun = functionimpl;
            if(mParaExps.length >= functionimpl.params)
                flag1 = flag;
            else
                flag1 = false;
            Utils.asserts(flag1, (new StringBuilder()).append("parameters count not matching for function: ").append(s).toString());
        }

        public static void registerFunction(String s, FunctionImpl functionimpl)
        {
            if((FunctionImpl)sFunMap.put(s, functionimpl) != null)
                Log.w("Expression", (new StringBuilder()).append("duplicated function name registation: ").append(s).toString());
        }

        public static void removeFunction(String s, FunctionImpl functionimpl)
        {
            sFunMap.remove(s);
        }

        public void accept(ExpressionVisitor expressionvisitor)
        {
            expressionvisitor.visit(this);
            for(int i = 0; i < mParaExps.length; i++)
                mParaExps[i].accept(expressionvisitor);

        }

        public double evaluate()
        {
            return mFun.evaluate(mParaExps, mVariables);
        }

        public String evaluateStr()
        {
            return mFun.evaluateStr(mParaExps, mVariables);
        }

        public String getFunName()
        {
            return mFunName;
        }

        protected static HashMap sFunMap = new HashMap();
        private FunctionImpl mFun;
        private String mFunName;
        private Expression mParaExps[];
        private Variables mVariables;

        static 
        {
            FunctionsLoader.load();
        }

        public FunctionExpression(Variables variables, Expression aexpression[], String s)
            throws Exception
        {
            mVariables = variables;
            mParaExps = aexpression;
            mFunName = s;
            parseFunction(s);
        }
    }

    public static abstract class FunctionImpl
    {

        public abstract double evaluate(Expression aexpression[], Variables variables);

        public abstract String evaluateStr(Expression aexpression[], Variables variables);

        public int params;

        public FunctionImpl(int i)
        {
            params = i;
        }
    }

    static class NumberArrayVariableExpression extends ArrayVariableExpression
    {

        public double evaluate()
        {
            return mIndexedVar.getArrDouble((int)mIndexExp.evaluate());
        }

        public String evaluateStr()
        {
            return Utils.doubleToString(evaluate());
        }

        public boolean isNull()
        {
            return mIndexedVar.isNull((int)mIndexExp.evaluate());
        }

        public NumberArrayVariableExpression(Variables variables, String s, Expression expression)
        {
            super(variables, s, expression);
        }
    }

    public static class NumberExpression extends Expression
    {

        public double evaluate()
        {
            return mValue;
        }

        public String evaluateStr()
        {
            if(mString == null)
                mString = Utils.doubleToString(mValue);
            return mString;
        }

        public void setValue(double d)
        {
            mValue = d;
        }

        private String mString;
        private double mValue;

        public NumberExpression(double d)
        {
            mValue = d;
        }

        public NumberExpression(String s)
        {
            if(s == null)
            {
                Log.e("Expression", "invalid NumberExpression: null");
                return;
            }
            if(s.length() <= 2 || s.indexOf("0x") != 0)
                break MISSING_BLOCK_LABEL_50;
            mValue = Long.parseLong(s.substring(2), 16);
_L1:
            return;
            try
            {
                mValue = Double.parseDouble(s);
            }
            catch(NumberFormatException numberformatexception)
            {
                Log.e("Expression", (new StringBuilder()).append("invalid NumberExpression:").append(s).toString());
            }
              goto _L1
        }
    }

    static class NumberVariableExpression extends VariableExpression
    {

        public double evaluate()
        {
            return mIndexedVar.getDouble();
        }

        public String evaluateStr()
        {
            return Utils.doubleToString(evaluate());
        }

        public boolean isNull()
        {
            return mIndexedVar.isNull();
        }

        public NumberVariableExpression(Variables variables, String s)
        {
            super(variables, s, true);
        }
    }

    private static final class Ope extends Enum
    {

        public static Ope valueOf(String s)
        {
            return (Ope)Enum.valueOf(miui/maml/data/Expression$Ope, s);
        }

        public static Ope[] values()
        {
            return $VALUES;
        }

        private static final Ope $VALUES[];
        public static final Ope ADD;
        public static final Ope AND;
        public static final Ope BIT_AND;
        public static final Ope BIT_LSHIFT;
        public static final Ope BIT_NOT;
        public static final Ope BIT_OR;
        public static final Ope BIT_RSHIFT;
        public static final Ope BIT_XOR;
        public static final Ope DIV;
        public static final Ope EQ;
        public static final Ope GE;
        public static final Ope GT;
        public static final Ope INVALID;
        public static final Ope LE;
        public static final Ope LT;
        public static final Ope MIN;
        public static final Ope MOD;
        public static final Ope MUL;
        public static final Ope NEQ;
        public static final Ope NOT;
        public static final Ope OR;

        static 
        {
            ADD = new Ope("ADD", 0);
            MIN = new Ope("MIN", 1);
            MUL = new Ope("MUL", 2);
            DIV = new Ope("DIV", 3);
            MOD = new Ope("MOD", 4);
            BIT_AND = new Ope("BIT_AND", 5);
            BIT_OR = new Ope("BIT_OR", 6);
            BIT_XOR = new Ope("BIT_XOR", 7);
            BIT_NOT = new Ope("BIT_NOT", 8);
            BIT_LSHIFT = new Ope("BIT_LSHIFT", 9);
            BIT_RSHIFT = new Ope("BIT_RSHIFT", 10);
            NOT = new Ope("NOT", 11);
            EQ = new Ope("EQ", 12);
            NEQ = new Ope("NEQ", 13);
            AND = new Ope("AND", 14);
            OR = new Ope("OR", 15);
            GT = new Ope("GT", 16);
            GE = new Ope("GE", 17);
            LT = new Ope("LT", 18);
            LE = new Ope("LE", 19);
            INVALID = new Ope("INVALID", 20);
            $VALUES = (new Ope[] {
                ADD, MIN, MUL, DIV, MOD, BIT_AND, BIT_OR, BIT_XOR, BIT_NOT, BIT_LSHIFT, 
                BIT_RSHIFT, NOT, EQ, NEQ, AND, OR, GT, GE, LT, LE, 
                INVALID
            });
        }

        private Ope(String s, int i)
        {
            super(s, i);
        }
    }

    private static class OpeInfo
    {

        static int _2D_get0()
        {
            return OPE_SIZE;
        }

        static String[] _2D_get1()
        {
            return mOpes;
        }

        public static OpeInfo getOpeInfo(int i)
        {
            OpeInfo opeinfo = new OpeInfo();
            opeinfo.priority = mOpePriority[i];
            opeinfo.participants = mOpePar[i];
            opeinfo.str = mOpes[i];
            return opeinfo;
        }

        private static final int OPE_SIZE = mOpes.length;
        private static final int mOpePar[] = {
            2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 
            2, 1, 2, 2, 2, 2, 2, 2, 2, 2
        };
        private static final int mOpePriority[] = {
            4, 4, 3, 3, 3, 8, 9, 10, 2, 5, 
            5, 2, 7, 7, 11, 12, 6, 6, 6, 6
        };
        private static final String mOpes[] = {
            "+", "-", "*", "/", "%", "&", "|", "^", "~", "{{", 
            "}}", "!", "==", "!=", "**", "||", "}", "}=", "{", "{="
        };
        public int participants;
        public int priority;
        public String str;
        public boolean unary;


        private OpeInfo()
        {
        }
    }

    public static class OpeInfo.Parser
    {

        public boolean accept(char c, boolean flag)
        {
            if(flag)
            {
                for(int i = 0; i < OpeInfo._2D_get0(); i++)
                    mFlags[i] = 0;

                mStep = 0;
                mMatch = -1;
            }
            flag = false;
            int j = 0;
            while(j < OpeInfo._2D_get0()) 
            {
                if(mFlags[j] != -1)
                {
                    String s = OpeInfo._2D_get1()[j];
                    if(s.length() > mStep && s.charAt(mStep) == c)
                    {
                        boolean flag1;
                        boolean flag2;
                        if(mStep == s.length() - 1)
                            flag1 = true;
                        else
                            flag1 = false;
                        mFlags[j] = 0;
                        flag2 = true;
                        flag = flag2;
                        if(flag1)
                        {
                            mMatch = j;
                            flag = flag2;
                        }
                    } else
                    {
                        mFlags[j] = -1;
                    }
                }
                j++;
            }
            if(flag)
                mStep = mStep + 1;
            return flag;
        }

        public Ope getMatch()
        {
            Ope ope;
            if(mMatch == -1)
                ope = Ope.INVALID;
            else
                ope = Ope.values()[mMatch];
            return ope;
        }

        private int mFlags[];
        private int mMatch;
        private int mStep;

        public OpeInfo.Parser()
        {
            mFlags = new int[OpeInfo._2D_get0()];
        }
    }

    static class StringArrayVariableExpression extends ArrayVariableExpression
    {

        public double evaluate()
        {
            String s = evaluateStr();
            if(s == null)
                return 0.0D;
            double d;
            try
            {
                d = Double.parseDouble(s);
            }
            catch(NumberFormatException numberformatexception)
            {
                return 0.0D;
            }
            return d;
        }

        public String evaluateStr()
        {
            return mIndexedVar.getArrString((int)mIndexExp.evaluate());
        }

        public boolean isNull()
        {
            return mIndexedVar.isNull((int)mIndexExp.evaluate());
        }

        public StringArrayVariableExpression(Variables variables, String s, Expression expression)
        {
            super(variables, s, expression);
        }
    }

    static class StringExpression extends Expression
    {

        public double evaluate()
        {
            double d;
            try
            {
                d = Double.parseDouble(mValue);
            }
            catch(NumberFormatException numberformatexception)
            {
                return 0.0D;
            }
            return d;
        }

        public String evaluateStr()
        {
            return mValue;
        }

        private String mValue;

        public StringExpression(String s)
        {
            mValue = s;
        }
    }

    static class StringVariableExpression extends VariableExpression
    {

        public double evaluate()
        {
            String s = evaluateStr();
            if(s == null)
                return 0.0D;
            double d;
            try
            {
                d = Double.parseDouble(s);
            }
            catch(NumberFormatException numberformatexception)
            {
                return 0.0D;
            }
            return d;
        }

        public String evaluateStr()
        {
            return mIndexedVar.getString();
        }

        public boolean isNull()
        {
            return mIndexedVar.isNull();
        }

        public StringVariableExpression(Variables variables, String s)
        {
            super(variables, s, false);
        }
    }

    private static class Tokenizer
    {

        public Token getToken()
        {
            int i;
            int j;
            int k;
            int l;
            int i1;
            TokenType tokentype;
            int j1;
            int k1;
            i = 0;
            j = -1;
            k = 0;
            l = 0;
            i1 = 0;
            tokentype = TokenType.INVALID;
            j1 = mString.length();
            k1 = mPos;
_L13:
            char c;
            int i2;
            int j2;
            int k2;
            int l2;
            Object obj;
            int i3;
            Object obj1;
label0:
            {
                if(k1 >= j1)
                    break MISSING_BLOCK_LABEL_865;
                c = mString.charAt(k1);
                int l1 = k;
                if(k != 0)
                    break label0;
                if(c == '#' || c == '@')
                {
                    i1 = k1 + 1;
                    do
                    {
                        if(i1 >= j1 || !Expression._2D_wrap4(mString.charAt(i1)))
                        {
                            if(i1 == k1 + 1)
                            {
                                Log.e("Expression", (new StringBuilder()).append("invalid variable name:").append(mString).toString());
                                return null;
                            }
                            break;
                        }
                        i1++;
                    } while(true);
                    mPos = i1;
                    if(c == '#')
                        tokentype = TokenType.VAR_NUM;
                    else
                        tokentype = TokenType.VAR_STR;
                    return new Token(tokentype, mString.substring(k1 + 1, i1));
                }
                if(c == '\'')
                {
                    i1 = 0;
                    l = k1 + 1;
                    do
                    {
label1:
                        {
                            if(l < j1)
                            {
                                l1 = mString.charAt(l);
                                if(i1 != 0 || l1 != '\'')
                                    break label1;
                            }
                            mPos = l + 1;
                            return new Token(TokenType.STR, mString.substring(k1 + 1, l).replace("\\'", "'"));
                        }
                        if(l1 == '\\')
                            i1 = 1;
                        else
                            i1 = 0;
                        l++;
                    } while(true);
                }
                if(c == '(')
                    l1 = 1;
                else
                if(c == '[')
                {
                    l1 = 2;
                } else
                {
                    if(Expression._2D_wrap1(c))
                    {
                        l = k1 + 1;
                        i1 = l;
                        if(mString.charAt(k1) == '0')
                        {
                            i1 = l;
                            if(l < j1)
                            {
                                i1 = l;
                                if(mString.charAt(l) == 'x')
                                    i1 = l + 1;
                            }
                        }
                        do
                        {
                            if(i1 >= j1 || !Expression._2D_wrap0(mString.charAt(i1)))
                            {
                                mPos = i1;
                                return new Token(TokenType.NUM, mString.substring(k1, i1));
                            }
                            i1++;
                        } while(true);
                    }
                    if(Expression._2D_wrap3(c))
                    {
                        i1 = k1 + 1;
                        do
                        {
                            if(i1 >= j1 || !Expression._2D_wrap2(mString.charAt(i1)))
                            {
                                mPos = i1;
                                return new Token(TokenType.FUN, mString.substring(k1, i1));
                            }
                            i1++;
                        } while(true);
                    }
                    l1 = k;
                    if(mOpeParser.accept(c, true))
                    {
label2:
                        {
                            for(i3 = k1 + 1; i3 < j1 && mOpeParser.accept(mString.charAt(i3), false); i3++)
                                break label2;

                            obj1 = mOpeParser.getMatch();
                            l1 = k;
                            if(obj1 != Ope.INVALID)
                            {
                                mPos = i3;
                                return new Token(TokenType.OPE, mString.substring(k1, mPos), ((Ope) (obj1)));
                            }
                        }
                    }
                }
            }
            i2 = l;
            j2 = i1;
            k2 = i;
            l2 = j;
            obj = tokentype;
            if(l1 == 0) goto _L2; else goto _L1
_L1:
            i3 = l;
            k = i1;
            obj1 = tokentype;
            if(i != 0) goto _L4; else goto _L3
_L3:
            l1;
            JVM INSTR tableswitch 1 2: default 372
        //                       1 754
        //                       2 770;
               goto _L5 _L6 _L7
_L5:
            j = k1 + 1;
            obj1 = tokentype;
            k = i1;
            i3 = l;
_L4:
            if(c != i3) goto _L9; else goto _L8
_L8:
            k2 = i + 1;
            obj = obj1;
            l2 = j;
            j2 = k;
            i2 = i3;
_L2:
            k1++;
            l = i2;
            i1 = j2;
            i = k2;
            k = l1;
            j = l2;
            tokentype = ((TokenType) (obj));
            continue; /* Loop/switch isn't completed */
_L6:
            l = 40;
            i1 = 41;
            tokentype = TokenType.BRACKET_ROUND;
              goto _L5
_L7:
            l = 91;
            i1 = 93;
            tokentype = TokenType.BRACKET_SQUARE;
              goto _L5
_L9:
            i2 = i3;
            j2 = k;
            k2 = i;
            l2 = j;
            obj = obj1;
            if(c != k) goto _L2; else goto _L10
_L10:
            i1 = i - 1;
            i2 = i3;
            j2 = k;
            k2 = i1;
            l2 = j;
            obj = obj1;
            if(i1 != 0) goto _L2; else goto _L11
_L11:
            mPos = k1 + 1;
            return new Token(((TokenType) (obj1)), mString.substring(j, k1));
            if(i != 0)
                Log.e("Expression", (new StringBuilder()).append("mismatched bracket:").append(mString).toString());
            return null;
            if(true) goto _L13; else goto _L12
_L12:
        }

        public void reset()
        {
            mPos = 0;
        }

        private static final int BRACKET_MODE_NONE = 0;
        private static final int BRACKET_MODE_ROUND = 1;
        private static final int BRACKET_MODE_SQUARE = 2;
        private OpeInfo.Parser mOpeParser;
        private int mPos;
        private String mString;

        public Tokenizer(String s)
        {
            mOpeParser = new OpeInfo.Parser();
            mString = s;
            reset();
        }
    }

    public static class Tokenizer.Token
    {

        public OpeInfo info;
        public Ope op;
        public String token;
        public Tokenizer.TokenType type;

        public Tokenizer.Token(Tokenizer.TokenType tokentype, String s)
        {
            type = Tokenizer.TokenType.INVALID;
            type = tokentype;
            token = s;
        }

        public Tokenizer.Token(Tokenizer.TokenType tokentype, String s, Ope ope)
        {
            type = Tokenizer.TokenType.INVALID;
            type = tokentype;
            token = s;
            op = ope;
            info = OpeInfo.getOpeInfo(op.ordinal());
        }
    }

    public static final class Tokenizer.TokenType extends Enum
    {

        public static Tokenizer.TokenType valueOf(String s)
        {
            return (Tokenizer.TokenType)Enum.valueOf(miui/maml/data/Expression$Tokenizer$TokenType, s);
        }

        public static Tokenizer.TokenType[] values()
        {
            return $VALUES;
        }

        private static final Tokenizer.TokenType $VALUES[];
        public static final Tokenizer.TokenType BRACKET_ROUND;
        public static final Tokenizer.TokenType BRACKET_SQUARE;
        public static final Tokenizer.TokenType FUN;
        public static final Tokenizer.TokenType INVALID;
        public static final Tokenizer.TokenType NUM;
        public static final Tokenizer.TokenType OPE;
        public static final Tokenizer.TokenType STR;
        public static final Tokenizer.TokenType VAR_NUM;
        public static final Tokenizer.TokenType VAR_STR;

        static 
        {
            INVALID = new Tokenizer.TokenType("INVALID", 0);
            VAR_NUM = new Tokenizer.TokenType("VAR_NUM", 1);
            VAR_STR = new Tokenizer.TokenType("VAR_STR", 2);
            NUM = new Tokenizer.TokenType("NUM", 3);
            STR = new Tokenizer.TokenType("STR", 4);
            OPE = new Tokenizer.TokenType("OPE", 5);
            FUN = new Tokenizer.TokenType("FUN", 6);
            BRACKET_ROUND = new Tokenizer.TokenType("BRACKET_ROUND", 7);
            BRACKET_SQUARE = new Tokenizer.TokenType("BRACKET_SQUARE", 8);
            $VALUES = (new Tokenizer.TokenType[] {
                INVALID, VAR_NUM, VAR_STR, NUM, STR, OPE, FUN, BRACKET_ROUND, BRACKET_SQUARE
            });
        }

        private Tokenizer.TokenType(String s, int i)
        {
            super(s, i);
        }
    }

    static class UnaryExpression extends Expression
    {

        private static int[] _2D_getmiui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues()
        {
            if(_2D_miui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues != null)
                return _2D_miui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues;
            int ai[] = new int[Ope.values().length];
            try
            {
                ai[Ope.ADD.ordinal()] = 4;
            }
            catch(NoSuchFieldError nosuchfielderror20) { }
            try
            {
                ai[Ope.AND.ordinal()] = 5;
            }
            catch(NoSuchFieldError nosuchfielderror19) { }
            try
            {
                ai[Ope.BIT_AND.ordinal()] = 6;
            }
            catch(NoSuchFieldError nosuchfielderror18) { }
            try
            {
                ai[Ope.BIT_LSHIFT.ordinal()] = 7;
            }
            catch(NoSuchFieldError nosuchfielderror17) { }
            try
            {
                ai[Ope.BIT_NOT.ordinal()] = 1;
            }
            catch(NoSuchFieldError nosuchfielderror16) { }
            try
            {
                ai[Ope.BIT_OR.ordinal()] = 8;
            }
            catch(NoSuchFieldError nosuchfielderror15) { }
            try
            {
                ai[Ope.BIT_RSHIFT.ordinal()] = 9;
            }
            catch(NoSuchFieldError nosuchfielderror14) { }
            try
            {
                ai[Ope.BIT_XOR.ordinal()] = 10;
            }
            catch(NoSuchFieldError nosuchfielderror13) { }
            try
            {
                ai[Ope.DIV.ordinal()] = 11;
            }
            catch(NoSuchFieldError nosuchfielderror12) { }
            try
            {
                ai[Ope.EQ.ordinal()] = 12;
            }
            catch(NoSuchFieldError nosuchfielderror11) { }
            try
            {
                ai[Ope.GE.ordinal()] = 13;
            }
            catch(NoSuchFieldError nosuchfielderror10) { }
            try
            {
                ai[Ope.GT.ordinal()] = 14;
            }
            catch(NoSuchFieldError nosuchfielderror9) { }
            try
            {
                ai[Ope.INVALID.ordinal()] = 15;
            }
            catch(NoSuchFieldError nosuchfielderror8) { }
            try
            {
                ai[Ope.LE.ordinal()] = 16;
            }
            catch(NoSuchFieldError nosuchfielderror7) { }
            try
            {
                ai[Ope.LT.ordinal()] = 17;
            }
            catch(NoSuchFieldError nosuchfielderror6) { }
            try
            {
                ai[Ope.MIN.ordinal()] = 2;
            }
            catch(NoSuchFieldError nosuchfielderror5) { }
            try
            {
                ai[Ope.MOD.ordinal()] = 18;
            }
            catch(NoSuchFieldError nosuchfielderror4) { }
            try
            {
                ai[Ope.MUL.ordinal()] = 19;
            }
            catch(NoSuchFieldError nosuchfielderror3) { }
            try
            {
                ai[Ope.NEQ.ordinal()] = 20;
            }
            catch(NoSuchFieldError nosuchfielderror2) { }
            try
            {
                ai[Ope.NOT.ordinal()] = 3;
            }
            catch(NoSuchFieldError nosuchfielderror1) { }
            try
            {
                ai[Ope.OR.ordinal()] = 21;
            }
            catch(NoSuchFieldError nosuchfielderror) { }
            _2D_miui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues = ai;
            return ai;
        }

        public void accept(ExpressionVisitor expressionvisitor)
        {
            expressionvisitor.visit(this);
            mExp.accept(expressionvisitor);
        }

        public double evaluate()
        {
            switch(_2D_getmiui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues()[mOpe.ordinal()])
            {
            default:
                Log.e("Expression", "fail to evalute UnaryExpression, invalid operator");
                return mExp.evaluate();

            case 2: // '\002'
                return 0.0D - mExp.evaluate();

            case 3: // '\003'
                int i;
                if(mExp.evaluate() <= 0.0D)
                    i = 1;
                else
                    i = 0;
                return (double)i;

            case 1: // '\001'
                return (double)(int)mExp.evaluate();
            }
        }

        public String evaluateStr()
        {
            return Utils.doubleToString(evaluate());
        }

        public boolean isNull()
        {
            return mExp.isNull();
        }

        private static final int _2D_miui_2D_maml_2D_data_2D_Expression$OpeSwitchesValues[];
        private Expression mExp;
        private Ope mOpe;

        public UnaryExpression(Expression expression, Ope ope)
        {
            mOpe = Ope.INVALID;
            mExp = expression;
            mOpe = ope;
            if(mOpe == Ope.INVALID)
                Log.e("Expression", (new StringBuilder()).append("UnaryExpression: invalid operator:").append(ope).toString());
        }
    }

    static abstract class VariableExpression extends Expression
    {

        public int getIndex()
        {
            return mIndexedVar.getIndex();
        }

        public String getName()
        {
            return mName;
        }

        public int getVersion()
        {
            return mIndexedVar.getVersion();
        }

        protected IndexedVariable mIndexedVar;
        protected String mName;

        public VariableExpression(Variables variables, String s, boolean flag)
        {
            mName = s;
            mIndexedVar = new IndexedVariable(mName, variables, flag);
        }
    }


    private static int[] _2D_getmiui_2D_maml_2D_data_2D_Expression$Tokenizer$TokenTypeSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_data_2D_Expression$Tokenizer$TokenTypeSwitchesValues != null)
            return _2D_miui_2D_maml_2D_data_2D_Expression$Tokenizer$TokenTypeSwitchesValues;
        int ai[] = new int[Tokenizer.TokenType.values().length];
        try
        {
            ai[Tokenizer.TokenType.BRACKET_ROUND.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai[Tokenizer.TokenType.BRACKET_SQUARE.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[Tokenizer.TokenType.FUN.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[Tokenizer.TokenType.INVALID.ordinal()] = 9;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[Tokenizer.TokenType.NUM.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[Tokenizer.TokenType.OPE.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Tokenizer.TokenType.STR.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Tokenizer.TokenType.VAR_NUM.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Tokenizer.TokenType.VAR_STR.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_data_2D_Expression$Tokenizer$TokenTypeSwitchesValues = ai;
        return ai;
    }

    static boolean _2D_wrap0(char c)
    {
        return isDigitCharRest(c);
    }

    static boolean _2D_wrap1(char c)
    {
        return isDigitCharStart(c);
    }

    static boolean _2D_wrap2(char c)
    {
        return isFunctionCharRest(c);
    }

    static boolean _2D_wrap3(char c)
    {
        return isFunctionCharStart(c);
    }

    static boolean _2D_wrap4(char c)
    {
        return isVariableChar(c);
    }

    public Expression()
    {
    }

    public static Expression build(Variables variables, String s)
    {
        Object obj = null;
        s = buildInner(variables, s);
        if(s == null)
            variables = obj;
        else
            variables = new RootExpression(variables, s);
        return variables;
    }

    private static Expression buildBracket(Variables variables, Tokenizer.Token token, Stack stack)
    {
        Expression aexpression[];
        aexpression = buildMultipleInner(variables, token.token);
        if(!checkParams(aexpression))
        {
            Log.e("Expression", (new StringBuilder()).append("invalid expressions: ").append(token.token).toString());
            return null;
        }
        if(!stack.isEmpty() && ((Tokenizer.Token)stack.peek()).type == Tokenizer.TokenType.FUN)
            return new FunctionExpression(variables, aexpression, ((Tokenizer.Token)stack.pop()).token);
        if(aexpression.length == 1)
        {
            variables = aexpression[0];
            return variables;
        }
        break MISSING_BLOCK_LABEL_116;
        variables;
        variables.printStackTrace();
        Log.e("Expression", variables.toString());
        Log.e("Expression", (new StringBuilder()).append("fail to buid: multiple expressions in brackets, but seems no function presents:").append(token.token).toString());
        return null;
    }

    private static Expression buildInner(Variables variables, String s)
    {
        Tokenizer tokenizer;
        Object obj;
        Stack stack;
        Stack stack1;
        if(TextUtils.isEmpty(s.trim()))
            return null;
        tokenizer = new Tokenizer(s);
        obj = null;
        stack = new Stack();
        stack1 = new Stack();
_L5:
        Tokenizer.Token token;
        token = tokenizer.getToken();
        if(token == null)
            break MISSING_BLOCK_LABEL_834;
        _2D_getmiui_2D_maml_2D_data_2D_Expression$Tokenizer$TokenTypeSwitchesValues()[token.type.ordinal()];
        JVM INSTR tableswitch 1 8: default 112
    //                   1 118
    //                   2 118
    //                   3 823
    //                   4 118
    //                   5 636
    //                   6 118
    //                   7 118
    //                   8 118;
           goto _L1 _L2 _L2 _L3 _L2 _L4 _L2 _L2 _L2
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_823;
_L16:
        obj = token;
          goto _L5
_L2:
        Object obj1;
        Object obj2;
        obj1 = null;
        obj2 = null;
        obj = obj2;
        _2D_getmiui_2D_maml_2D_data_2D_Expression$Tokenizer$TokenTypeSwitchesValues()[token.type.ordinal()];
        JVM INSTR tableswitch 1 8: default 184
    //                   1 415
    //                   2 435
    //                   3 187
    //                   4 283
    //                   5 187
    //                   6 399
    //                   7 249
    //                   8 266;
           goto _L6 _L7 _L8 _L6 _L9 _L6 _L10 _L11 _L12
_L6:
        for(obj = obj2; !stack.empty() && ((Tokenizer.Token)stack.peek()).info != null && ((Tokenizer.Token)stack.peek()).info.unary; obj = new UnaryExpression(((Expression) (obj)), ((Tokenizer.Token)stack.pop()).op));
        break; /* Loop/switch isn't completed */
_L11:
        obj = new NumberVariableExpression(variables, token.token);
        continue; /* Loop/switch isn't completed */
_L12:
        obj = new StringVariableExpression(variables, token.token);
        continue; /* Loop/switch isn't completed */
_L9:
        boolean flag;
        if(!stack.empty() && ((Tokenizer.Token)stack.peek()).op == Ope.MIN)
            flag = ((Tokenizer.Token)stack.peek()).info.unary;
        else
            flag = false;
        obj1 = new StringBuilder();
        if(flag)
            obj = "-";
        else
            obj = "";
        obj1 = new NumberExpression(((StringBuilder) (obj1)).append(((String) (obj))).append(token.token).toString());
        obj = obj1;
        if(flag)
        {
            stack.pop();
            obj = obj1;
        }
        continue; /* Loop/switch isn't completed */
_L10:
        obj = new StringExpression(token.token);
        if(true) goto _L6; else goto _L13
_L13:
        break; /* Loop/switch isn't completed */
_L7:
        obj1 = buildBracket(variables, token, stack);
        obj = obj1;
        if(obj1 == null)
            return null;
        if(true) goto _L6; else goto _L14
_L14:
        break; /* Loop/switch isn't completed */
_L8:
        if(stack1.size() < 1)
        {
            Log.e("Expression", (new StringBuilder()).append("fail to buid: no array name before []:").append(s).toString());
            return null;
        }
        Expression expression1 = (Expression)stack1.pop();
        if(expression1 instanceof VariableExpression)
        {
            obj = buildInner(variables, token.token);
            if(obj == null)
            {
                Log.e("Expression", (new StringBuilder()).append("fail to buid: no index expression in []:").append(s).toString());
                return null;
            }
            String s1 = ((VariableExpression)expression1).getName();
            if(expression1 instanceof NumberVariableExpression)
                obj1 = new NumberArrayVariableExpression(variables, s1, ((Expression) (obj)));
            else
            if(expression1 instanceof StringVariableExpression)
                obj1 = new StringArrayVariableExpression(variables, s1, ((Expression) (obj)));
        } else
        {
            Log.e("Expression", (new StringBuilder()).append("fail to buid: the expression before [] is not a variable:").append(s).toString());
        }
        obj = obj1;
        if(obj1 == null)
            return null;
        if(true) goto _L6; else goto _L15
_L15:
        stack1.push(obj);
          goto _L16
_L4:
        if(token.info.participants != 1 || obj != null && ((Tokenizer.Token) (obj)).type != Tokenizer.TokenType.OPE) goto _L18; else goto _L17
_L17:
        token.info.unary = true;
        stack.push(token);
          goto _L16
_L20:
        Expression expression = (Expression)stack1.pop();
        stack1.push(new BinaryExpression((Expression)stack1.pop(), expression, ((Tokenizer.Token)stack.pop()).op));
_L18:
        if(stack.size() <= 0 || ((Tokenizer.Token)stack.peek()).type != Tokenizer.TokenType.OPE || ((Tokenizer.Token)stack.peek()).info.priority - token.info.priority > 0)
            break; /* Loop/switch isn't completed */
        if(stack1.size() < 2)
        {
            Log.e("Expression", (new StringBuilder()).append("fail to buid: invalid operator position:").append(s).toString());
            return null;
        }
        if(true) goto _L20; else goto _L19
_L19:
        stack.push(token);
          goto _L16
        stack.push(token);
          goto _L16
        if(stack1.size() != stack.size() + 1)
        {
            Log.e("Expression", (new StringBuilder()).append("fail to buid: invalid expression:").append(s).toString());
            return null;
        }
        for(variables = (Expression)stack1.pop(); stack.size() > 0; variables = new BinaryExpression((Expression)stack1.pop(), variables, ((Tokenizer.Token)stack.pop()).op));
        return variables;
    }

    public static Expression[] buildMultiple(Variables variables, String s)
    {
        if(TextUtils.isEmpty(s))
            return null;
        s = buildMultipleInner(variables, s);
        Expression aexpression[] = new Expression[s.length];
        int i = 0;
        while(i < s.length) 
        {
            Expression expression = s[i];
            if(expression == null || (expression instanceof NumberExpression) || (expression instanceof StringExpression))
                aexpression[i] = expression;
            else
                aexpression[i] = new RootExpression(variables, expression);
            i++;
        }
        return aexpression;
    }

    private static Expression[] buildMultipleInner(Variables variables, String s)
    {
        int i = 0;
        int j = 0;
        int k = 0;
        ArrayList arraylist = new ArrayList();
        int l = 0;
        while(l < s.length()) 
        {
            char c = s.charAt(l);
            int i1 = i;
            int j1 = k;
            if(j == 0)
                if(c == ',' && i == 0)
                {
                    arraylist.add(buildInner(variables, s.substring(k, l)));
                    j1 = l + 1;
                    i1 = i;
                } else
                if(c == '(')
                {
                    i1 = i + 1;
                    j1 = k;
                } else
                {
                    i1 = i;
                    j1 = k;
                    if(c == ')')
                    {
                        i1 = i - 1;
                        j1 = k;
                    }
                }
            k = j;
            if(c == '\'')
                k = j ^ true;
            l++;
            i = i1;
            j = k;
            k = j1;
        }
        if(k < s.length())
            arraylist.add(buildInner(variables, s.substring(k)));
        return (Expression[])arraylist.toArray(new Expression[arraylist.size()]);
    }

    private static boolean checkParams(Expression aexpression[])
    {
        for(int i = 0; i < aexpression.length; i++)
            if(aexpression[i] == null)
                return false;

        return true;
    }

    private static boolean isDigitCharRest(char c)
    {
        boolean flag = true;
        if(c < '0' || c > '9') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        if(c >= 'a')
        {
            flag1 = flag;
            if(c <= 'f')
                continue; /* Loop/switch isn't completed */
        }
        if(c >= 'A')
        {
            flag1 = flag;
            if(c <= 'F')
                continue; /* Loop/switch isn't completed */
        }
        flag1 = flag;
        if(c != '.')
            flag1 = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static boolean isDigitCharStart(char c)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if((c < '0' || c > '9') && c != '.')
            flag = false;
        return flag;
    }

    private static boolean isFunctionCharRest(char c)
    {
        boolean flag;
        boolean flag1;
        flag = true;
        flag1 = flag;
        if(isFunctionCharStart(c)) goto _L2; else goto _L1
_L1:
        if(c != '_') goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        if(c >= '0')
        {
            flag1 = flag;
            if(c <= '9')
                continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static boolean isFunctionCharStart(char c)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))
            flag = false;
        return flag;
    }

    private static boolean isVariableChar(char c)
    {
        boolean flag = true;
        if(c < 'a' || c > 'z') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        if(c >= 'A')
        {
            flag1 = flag;
            if(c <= 'Z')
                continue; /* Loop/switch isn't completed */
        }
        flag1 = flag;
        if(c == '_')
            continue; /* Loop/switch isn't completed */
        flag1 = flag;
        if(c == '.')
            continue; /* Loop/switch isn't completed */
        if(c >= '0')
        {
            flag1 = flag;
            if(c <= '9')
                continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void accept(ExpressionVisitor expressionvisitor)
    {
        expressionvisitor.visit(this);
    }

    public abstract double evaluate();

    public String evaluateStr()
    {
        return null;
    }

    public boolean isNull()
    {
        return false;
    }

    public BigDecimal preciseEvaluate()
    {
        double d = evaluate();
        BigDecimal bigdecimal;
        try
        {
            bigdecimal = BigDecimal.valueOf(d);
        }
        catch(NumberFormatException numberformatexception)
        {
            return null;
        }
        return bigdecimal;
    }

    private static final int _2D_miui_2D_maml_2D_data_2D_Expression$Tokenizer$TokenTypeSwitchesValues[];
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "Expression";
}
