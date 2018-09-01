// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import java.util.HashSet;

// Referenced classes of package miui.maml.data:
//            Expression, Variables, ExpressionVisitor

public class RootExpression extends Expression
{
    public static class VarVersion
    {

        public boolean equals(Object obj)
        {
            boolean flag = false;
            if(obj instanceof VarVersion)
            {
                obj = (VarVersion)obj;
                boolean flag1 = flag;
                if(((VarVersion) (obj)).mIsNumber == mIsNumber)
                {
                    flag1 = flag;
                    if(((VarVersion) (obj)).mIndex == mIndex)
                        flag1 = true;
                }
                return flag1;
            } else
            {
                return false;
            }
        }

        public int getVer(Variables variables)
        {
            return variables.getVer(mIndex, mIsNumber);
        }

        public int hashCode()
        {
            int i;
            if(mIsNumber)
                i = mIndex;
            else
                i = -mIndex - 1;
            return i;
        }

        int mIndex;
        private boolean mIsNumber;
        int mVersion;

        public VarVersion(int i, int j, boolean flag)
        {
            mIndex = i;
            mVersion = j;
            mIsNumber = flag;
        }
    }

    private static class VarVersionVisitor extends ExpressionVisitor
    {

        public void visit(Expression expression)
        {
            if(!(expression instanceof Expression.VariableExpression)) goto _L2; else goto _L1
_L1:
            Expression.VariableExpression variableexpression = (Expression.VariableExpression)expression;
            variableexpression.evaluate();
            mRoot.addVarVersion(new VarVersion(variableexpression.getIndex(), variableexpression.getVersion(), expression instanceof Expression.NumberVariableExpression));
_L4:
            return;
_L2:
            if(expression instanceof Expression.FunctionExpression)
            {
                expression = ((Expression.FunctionExpression)expression).getFunName();
                if("rand".equals(expression) || "eval".equals(expression) || "preciseeval".equals(expression))
                    RootExpression._2D_set0(mRoot, true);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private RootExpression mRoot;

        public VarVersionVisitor(RootExpression rootexpression)
        {
            mRoot = rootexpression;
        }
    }


    static boolean _2D_set0(RootExpression rootexpression, boolean flag)
    {
        rootexpression.mAlwaysEvaluate = flag;
        return flag;
    }

    public RootExpression(Variables variables, Expression expression)
    {
        mVersionSet = new HashSet();
        mIsNumInit = false;
        mIsStrInit = false;
        mVarVersionVisitor = null;
        mVars = variables;
        mExp = expression;
    }

    public void accept(ExpressionVisitor expressionvisitor)
    {
    }

    public void addVarVersion(VarVersion varversion)
    {
        mVersionSet.add(varversion);
    }

    public double evaluate()
    {
        if(mIsNumInit) goto _L2; else goto _L1
_L1:
        mDoubleValue = mExp.evaluate();
        if(mVarVersionVisitor == null)
        {
            mVarVersionVisitor = new VarVersionVisitor(this);
            mExp.accept(mVarVersionVisitor);
            if(mVersionSet.size() <= 0)
            {
                mVersions = null;
            } else
            {
                mVersions = new VarVersion[mVersionSet.size()];
                mVersionSet.toArray(mVersions);
            }
        }
        mIsNumInit = true;
_L4:
        return mDoubleValue;
_L2:
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = false;
        if(!mAlwaysEvaluate)
            break; /* Loop/switch isn't completed */
        flag1 = true;
_L6:
        if(flag1)
            mDoubleValue = mExp.evaluate();
        if(true) goto _L4; else goto _L3
_L3:
        if(mVersions == null) goto _L6; else goto _L5
_L5:
        int i = 0;
_L8:
        flag1 = flag;
        if(i >= mVersions.length) goto _L6; else goto _L7
_L7:
        VarVersion varversion = mVersions[i];
        boolean flag2 = flag;
        if(varversion != null)
        {
            int j = varversion.getVer(mVars);
            flag2 = flag;
            if(varversion.mVersion != j)
            {
                flag2 = true;
                varversion.mVersion = j;
            }
        }
        i++;
        flag = flag2;
          goto _L8
    }

    public String evaluateStr()
    {
        if(mIsStrInit) goto _L2; else goto _L1
_L1:
        mStringValue = mExp.evaluateStr();
        if(mVarVersionVisitor == null)
        {
            mVarVersionVisitor = new VarVersionVisitor(this);
            mExp.accept(mVarVersionVisitor);
            mVersions = new VarVersion[mVersionSet.size()];
            mVersionSet.toArray(mVersions);
        }
        mIsStrInit = true;
_L4:
        return mStringValue;
_L2:
        boolean flag;
        boolean flag1;
        flag = false;
        flag1 = false;
        if(!mAlwaysEvaluate)
            break; /* Loop/switch isn't completed */
        flag1 = true;
_L6:
        if(flag1)
            mStringValue = mExp.evaluateStr();
        if(true) goto _L4; else goto _L3
_L3:
        if(mVersions == null) goto _L6; else goto _L5
_L5:
        int i = 0;
_L8:
        flag1 = flag;
        if(i >= mVersions.length) goto _L6; else goto _L7
_L7:
        VarVersion varversion = mVersions[i];
        boolean flag2 = flag;
        if(varversion != null)
        {
            int j = varversion.getVer(mVars);
            flag2 = flag;
            if(varversion.mVersion != j)
            {
                flag2 = true;
                varversion.mVersion = j;
            }
        }
        i++;
        flag = flag2;
          goto _L8
    }

    public boolean isNull()
    {
        return mExp.isNull();
    }

    public static final String LOG_TAG = "RootExression";
    private boolean mAlwaysEvaluate;
    private double mDoubleValue;
    private Expression mExp;
    private boolean mIsNumInit;
    private boolean mIsStrInit;
    private String mStringValue;
    private VarVersionVisitor mVarVersionVisitor;
    private Variables mVars;
    private HashSet mVersionSet;
    private VarVersion mVersions[];
}
