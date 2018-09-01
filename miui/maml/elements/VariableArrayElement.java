// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.HashSet;
import miui.maml.ScreenElementRoot;
import miui.maml.data.*;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ScreenElement

public class VariableArrayElement extends ScreenElement
{
    private class Item
    {

        public Double evaluate()
        {
            Object obj = null;
            Double double1 = null;
            if(mExpression != null)
            {
                if(!mExpression.isNull())
                    double1 = Double.valueOf(mExpression.evaluate());
                return double1;
            }
            double1 = obj;
            if(mValue instanceof Number)
                double1 = Double.valueOf(((Number)mValue).doubleValue());
            return double1;
        }

        public String evaluateStr()
        {
            String s = null;
            if(mExpression != null)
                return mExpression.evaluateStr();
            if(mValue instanceof String)
                s = (String)mValue;
            return s;
        }

        public boolean isExpression()
        {
            boolean flag;
            if(mExpression != null)
                flag = true;
            else
                flag = false;
            return flag;
        }

        public Expression mExpression;
        public Object mValue;
        final VariableArrayElement this$0;

        public Item(Object obj)
        {
            this$0 = VariableArrayElement.this;
            super();
            mValue = obj;
            mExpression = null;
        }

        public Item(Variables variables, Element element)
        {
            this$0 = VariableArrayElement.this;
            super();
            if(element == null) goto _L2; else goto _L1
_L1:
            mExpression = Expression.build(variables, element.getAttribute("expression"));
            variables = element.getAttribute("value");
            if(mType != Type.DOUBLE) goto _L4; else goto _L3
_L3:
            mValue = Double.valueOf(Double.parseDouble(variables));
_L2:
            return;
_L4:
            mValue = variables;
            continue; /* Loop/switch isn't completed */
            variablearrayelement;
            if(true) goto _L2; else goto _L5
_L5:
        }
    }

    public static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(miui/maml/elements/VariableArrayElement$Type, s);
        }

        public static Type[] values()
        {
            return $VALUES;
        }

        private static final Type $VALUES[];
        public static final Type DOUBLE;
        public static final Type STRING;

        static 
        {
            DOUBLE = new Type("DOUBLE", 0);
            STRING = new Type("STRING", 1);
            $VALUES = (new Type[] {
                DOUBLE, STRING
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }

    private class Var
    {

        private void update()
        {
            int i;
            if(mIndexExpression == null)
                return;
            i = (int)mIndexExpression.evaluate();
            if(i >= 0 && i < VariableArrayElement._2D_get0(VariableArrayElement.this).size()) goto _L2; else goto _L1
_L1:
            if(mType != Type.STRING) goto _L4; else goto _L3
_L3:
            mVar.set(null);
_L5:
            return;
_L4:
            if(mType == Type.DOUBLE)
                mVar.set(0.0D);
            if(true) goto _L5; else goto _L2
_L2:
            Item item;
            if(mIndex == i && mCurrentItemIsExpression ^ true)
                return;
            item = (Item)VariableArrayElement._2D_get0(VariableArrayElement.this).get(i);
            if(mIndex != i)
            {
                mIndex = i;
                mCurrentItemIsExpression = item.isExpression();
            }
            if(mType != Type.STRING) goto _L7; else goto _L6
_L6:
            mVar.set(item.evaluateStr());
_L9:
            return;
_L7:
            if(mType == Type.DOUBLE)
                mVar.set(item.evaluate());
            if(true) goto _L9; else goto _L8
_L8:
        }

        public void init()
        {
            mIndex = -1;
            update();
        }

        public void tick()
        {
            if(!mConst)
                update();
        }

        private boolean mConst;
        private boolean mCurrentItemIsExpression;
        private int mIndex;
        private Expression mIndexExpression;
        private String mName;
        private IndexedVariable mVar;
        final VariableArrayElement this$0;

        public Var(Variables variables, Element element)
        {
            this$0 = VariableArrayElement.this;
            super();
            mIndex = -1;
            if(element != null)
            {
                mName = element.getAttribute("name");
                mIndexExpression = Expression.build(variables, element.getAttribute("index"));
                mConst = Boolean.parseBoolean(element.getAttribute("const"));
                variables = mName;
                element = getVariables();
                boolean flag;
                if(mType != Type.STRING)
                    flag = true;
                else
                    flag = false;
                mVar = new IndexedVariable(variables, element, flag);
            }
        }
    }

    public static interface VarObserver
    {

        public abstract void onDataChange(Object aobj[]);
    }


    static ArrayList _2D_get0(VariableArrayElement variablearrayelement)
    {
        return variablearrayelement.mArray;
    }

    static ArrayList _2D_get1(VariableArrayElement variablearrayelement)
    {
        return variablearrayelement.mVars;
    }

    public VariableArrayElement(Element element, final ScreenElementRoot vars)
    {
        super(element, vars);
        mArray = new ArrayList();
        mVars = new ArrayList();
        mType = Type.DOUBLE;
        mVarObserver = new HashSet();
        if(element != null)
        {
            if("string".equalsIgnoreCase(element.getAttribute("type")))
                mType = Type.STRING;
            else
                mType = Type.DOUBLE;
            vars = getVariables();
            Utils.traverseXmlElementChildren(Utils.getChild(element, "Vars"), "Var", new miui.maml.util.Utils.XmlTraverseListener() {

                public void onChild(Element element1)
                {
                    VariableArrayElement._2D_get1(VariableArrayElement.this).add(new Var(vars, element1));
                }

                final VariableArrayElement this$0;
                final Variables val$vars;

            
            {
                this$0 = VariableArrayElement.this;
                vars = variables;
                super();
            }
            }
);
            Utils.traverseXmlElementChildren(Utils.getChild(element, "Items"), "Item", new miui.maml.util.Utils.XmlTraverseListener() {

                public void onChild(Element element1)
                {
                    VariableArrayElement._2D_get0(VariableArrayElement.this).add(new Item(vars, element1));
                }

                final VariableArrayElement this$0;
                final Variables val$vars;

            
            {
                this$0 = VariableArrayElement.this;
                vars = variables;
                super();
            }
            }
);
            if(mHasName)
                mItemCountVar = new IndexedVariable((new StringBuilder()).append(mName).append(".count").toString(), vars, true);
        }
    }

    protected void doRender(Canvas canvas)
    {
    }

    protected void doTick(long l)
    {
        int i = mVars.size();
        for(int j = 0; j < i; j++)
            ((Var)mVars.get(j)).tick();

    }

    public int getItemSize()
    {
        return mItemCount;
    }

    public void init()
    {
        super.init();
        int i = mVars.size();
        for(int j = 0; j < i; j++)
            ((Var)mVars.get(j)).init();

        mItemCount = mArray.size();
        if(mItemCountVar != null)
            mItemCountVar.set(mItemCount);
        if(mData == null)
        {
            mData = new Object[mItemCount];
            for(int k = 0; k < mItemCount; k++)
                mData[k] = ((Item)mArray.get(k)).mValue;

        }
    }

    public void registerVarObserver(VarObserver varobserver, boolean flag)
    {
        if(varobserver == null)
            return;
        if(flag)
        {
            mVarObserver.add(varobserver);
            varobserver.onDataChange(mData);
        } else
        {
            mVarObserver.remove(varobserver);
        }
    }

    public static final String TAG_NAME = "VarArray";
    private ArrayList mArray;
    Object mData[];
    private int mItemCount;
    private IndexedVariable mItemCountVar;
    Type mType;
    HashSet mVarObserver;
    private ArrayList mVars;
}
