// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import android.util.Log;
import miui.maml.CommandTrigger;
import miui.maml.ScreenElementRoot;
import miui.maml.animation.BaseAnimation;
import miui.maml.animation.VariableAnimation;
import miui.maml.data.*;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ScreenElement

public class VariableElement extends ScreenElement
{

    private static int[] _2D_getmiui_2D_maml_2D_data_2D_VariableTypeSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_data_2D_VariableTypeSwitchesValues != null)
            return _2D_miui_2D_maml_2D_data_2D_VariableTypeSwitchesValues;
        int ai[] = new int[VariableType.values().length];
        try
        {
            ai[VariableType.BOOLEAN_ARR.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror14) { }
        try
        {
            ai[VariableType.BYTE_ARR.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror13) { }
        try
        {
            ai[VariableType.CHAR_ARR.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror12) { }
        try
        {
            ai[VariableType.DOUBLE_ARR.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror11) { }
        try
        {
            ai[VariableType.FLOAT_ARR.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror10) { }
        try
        {
            ai[VariableType.INT_ARR.ordinal()] = 9;
        }
        catch(NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai[VariableType.INVALID.ordinal()] = 10;
        }
        catch(NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai[VariableType.LONG_ARR.ordinal()] = 11;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[VariableType.NUM.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[VariableType.NUM_ARR.ordinal()] = 12;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[VariableType.OBJ.ordinal()] = 13;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[VariableType.OBJ_ARR.ordinal()] = 14;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[VariableType.SHORT_ARR.ordinal()] = 15;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[VariableType.STR.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[VariableType.STR_ARR.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_data_2D_VariableTypeSwitchesValues = ai;
        return ai;
    }

    public VariableElement(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        if(element != null)
        {
            mExpression = Expression.build(getVariables(), element.getAttribute("expression"));
            mIndexExpression = Expression.build(getVariables(), element.getAttribute("index"));
            mThreshold = Math.abs(Utils.getAttrAsFloat(element, "threshold", 1.0F));
            mType = VariableType.parseType(element.getAttribute("type"));
            mConst = Boolean.parseBoolean(element.getAttribute("const"));
            mArraySize = Utils.getAttrAsInt(element, "size", 0);
            Variables variables = getVariables();
            mVar = new IndexedVariable(mName, variables, mType.isNumber());
            mOldVar = new IndexedVariable((new StringBuilder()).append(mName).append(".").append("old_value").toString(), variables, mType.isNumber());
            mTrigger = CommandTrigger.fromParentElement(element, screenelementroot);
            if(mType.isArray())
            {
                mArrayValues = Expression.buildMultiple(variables, element.getAttribute("values"));
                if(mArrayValues != null)
                    mArraySize = mArrayValues.length;
                if(mArraySize > 0)
                {
                    if(!variables.createArray(mName, mArraySize, mType.mTypeClass))
                        Log.e("VariableElement", (new StringBuilder()).append("fail to create array:").append(mName).toString());
                } else
                {
                    Log.e("VariableElement", (new StringBuilder()).append("array size is 0:").append(mName).toString());
                }
            }
        }
    }

    private double getDouble(boolean flag, int i)
    {
        if(mAnimation != null)
            return mAnimation.getValue();
        if(mExpression != null)
            return mExpression.evaluate();
        double d;
        if(flag)
            d = mVar.getArrDouble(i);
        else
            d = mVar.getDouble();
        return d;
    }

    private void onValueChange(double d)
    {
        if(!mInited)
            mOldValue = d;
        if(mTrigger != null && Math.abs(d - mOldValue) >= mThreshold)
        {
            mOldVar.set(mOldValue);
            mOldValue = d;
            mTrigger.perform();
        }
    }

    private void update()
    {
        _2D_getmiui_2D_maml_2D_data_2D_VariableTypeSwitchesValues()[mType.ordinal()];
        JVM INSTR tableswitch 1 3: default 36
    //                   1 316
    //                   2 85
    //                   3 157;
           goto _L1 _L2 _L3 _L4
_L1:
        if(!mType.isNumberArray())
            break; /* Loop/switch isn't completed */
        if(mIndexExpression == null) goto _L6; else goto _L5
_L5:
        int i = (int)mIndexExpression.evaluate();
        double d = getDouble(true, i);
        mVar.setArr(i, d);
        onValueChange(d);
_L7:
        return;
_L3:
        if(mExpression != null)
        {
            String s = mExpression.evaluateStr();
            String s2 = mVar.getString();
            if(!Utils.equals(s, s2))
            {
                mOldVar.set(s2);
                mVar.set(s);
                if(mTrigger != null)
                    mTrigger.perform();
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if(mExpression != null)
            if(mIndexExpression != null)
            {
                String s3 = mExpression.evaluateStr();
                int j = (int)mIndexExpression.evaluate();
                String s1 = mVar.getArrString(j);
                if(!Utils.equals(s3, s1))
                {
                    mOldVar.set(s1);
                    mVar.setArr(j, s3);
                    if(mTrigger != null)
                        mTrigger.perform();
                }
            } else
            if(mArrayValues != null)
            {
                int i1 = mArrayValues.length;
                int k = 0;
                while(k < i1) 
                {
                    Object obj = mArrayValues[k];
                    IndexedVariable indexedvariable = mVar;
                    if(obj == null)
                        obj = null;
                    else
                        obj = ((Expression) (obj)).evaluateStr();
                    indexedvariable.setArr(k, obj);
                    k++;
                }
            }
        continue; /* Loop/switch isn't completed */
_L2:
        double d1 = getDouble(false, 0);
        mVar.set(d1);
        onValueChange(d1);
        if(true) goto _L7; else goto _L6
_L6:
        if(mArrayValues != null)
        {
            int j1 = mArrayValues.length;
            int l = 0;
            while(l < j1) 
            {
                Expression expression = mArrayValues[l];
                IndexedVariable indexedvariable1 = mVar;
                double d2;
                if(expression == null)
                    d2 = 0.0D;
                else
                    d2 = expression.evaluate();
                indexedvariable1.setArr(l, d2);
                l++;
            }
        }
        if(true) goto _L7; else goto _L8
_L8:
    }

    protected void doRender(Canvas canvas)
    {
    }

    protected void doTick(long l)
    {
        if(mConst)
        {
            return;
        } else
        {
            super.doTick(l);
            update();
            return;
        }
    }

    public void finish()
    {
        super.finish();
        if(mTrigger != null)
            mTrigger.finish();
        mInited = false;
    }

    public void init()
    {
        super.init();
        if(mTrigger != null)
            mTrigger.init();
        update();
        mInited = true;
    }

    protected BaseAnimation onCreateAnimation(String s, Element element)
    {
        if("VariableAnimation".equals(s))
        {
            s = new VariableAnimation(element, this);
            mAnimation = s;
            return s;
        } else
        {
            return super.onCreateAnimation(s, element);
        }
    }

    protected void onSetAnimBefore()
    {
        mAnimation = null;
    }

    protected void onSetAnimEnable(BaseAnimation baseanimation)
    {
        if(baseanimation instanceof VariableAnimation)
            mAnimation = (VariableAnimation)baseanimation;
    }

    public void pause()
    {
        super.pause();
        if(mTrigger != null)
            mTrigger.pause();
    }

    protected void pauseAnim(long l)
    {
        super.pauseAnim(l);
        update();
    }

    protected void playAnim(long l, long l1, long l2, boolean flag, 
            boolean flag1)
    {
        super.playAnim(l, l1, l2, flag, flag1);
        update();
    }

    public void reset(long l)
    {
        super.reset(l);
        update();
    }

    public void resume()
    {
        super.resume();
        if(mTrigger != null)
            mTrigger.resume();
    }

    protected void resumeAnim(long l)
    {
        super.resumeAnim(l);
        update();
    }

    private static final int _2D_miui_2D_maml_2D_data_2D_VariableTypeSwitchesValues[];
    private static final String LOG_TAG = "VariableElement";
    private static final String OLD_VALUE = "old_value";
    public static final String TAG_NAME = "Var";
    private VariableAnimation mAnimation;
    private int mArraySize;
    private Expression mArrayValues[];
    private boolean mConst;
    private Expression mExpression;
    private Expression mIndexExpression;
    private boolean mInited;
    private double mOldValue;
    private IndexedVariable mOldVar;
    private double mThreshold;
    private CommandTrigger mTrigger;
    private VariableType mType;
    private IndexedVariable mVar;
}
