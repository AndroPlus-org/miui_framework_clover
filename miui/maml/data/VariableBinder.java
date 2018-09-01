// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.text.TextUtils;
import java.util.ArrayList;
import miui.maml.*;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.data:
//            VariableBinderVisitor, Variables, IndexedVariable, Expression

public abstract class VariableBinder
{
    public static class TypedValue
    {

        private void initInner(String s, String s1)
        {
            mName = s;
            mTypeStr = s1;
            mType = parseType(mTypeStr);
        }

        public boolean isArray()
        {
            boolean flag = true;
            boolean flag1 = flag;
            if(mType != 8)
                if(mType == 9)
                    flag1 = flag;
                else
                    flag1 = false;
            return flag1;
        }

        public boolean isNumber()
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mType >= 3)
            {
                flag1 = flag;
                if(mType <= 6)
                    flag1 = true;
            }
            return flag1;
        }

        protected int parseType(String s)
        {
            if("string".equalsIgnoreCase(s))
                return 2;
            if("double".equalsIgnoreCase(s) || "number".equalsIgnoreCase(s))
                return 6;
            if("float".equalsIgnoreCase(s))
                return 5;
            if("int".equalsIgnoreCase(s) || "integer".equalsIgnoreCase(s))
                return 3;
            if("long".equalsIgnoreCase(s))
                return 4;
            if("bitmap".equalsIgnoreCase(s))
                return 7;
            if("number[]".equalsIgnoreCase(s))
                return 8;
            return !"string[]".equalsIgnoreCase(s) ? 6 : 9;
        }

        public static final int BITMAP = 7;
        public static final int DOUBLE = 6;
        public static final int FLOAT = 5;
        public static final int INT = 3;
        public static final int LONG = 4;
        public static final int NUM_ARR = 8;
        public static final int STRING = 2;
        public static final int STR_ARR = 9;
        public static final int TYPE_BASE = 1000;
        public String mName;
        public int mType;
        public String mTypeStr;

        public TypedValue(String s, String s1)
        {
            initInner(s, s1);
        }

        public TypedValue(Element element)
        {
            if(element == null)
            {
                throw new NullPointerException("node is null");
            } else
            {
                initInner(element.getAttribute("name"), element.getAttribute("type"));
                return;
            }
        }
    }

    public static class Variable extends TypedValue
    {

        public void set(double d)
        {
            if(mArrayIndex != null)
                mVar.setArr((int)mArrayIndex.evaluate(), d);
            else
                mVar.set(d);
        }

        public void set(Object obj)
        {
            if(!isNumber()) goto _L2; else goto _L1
_L1:
            double d = 0.0D;
            if(!(obj instanceof String)) goto _L4; else goto _L3
_L3:
            double d1 = Utils.parseDouble((String)obj);
            d = d1;
_L8:
            if(mArrayIndex != null)
                mVar.setArr((int)mArrayIndex.evaluate(), d);
            else
                mVar.set(d);
_L6:
            return;
_L4:
            if(obj instanceof Number)
                d = ((Number)obj).doubleValue();
            continue; /* Loop/switch isn't completed */
_L2:
            Object obj1 = obj;
            if(obj instanceof Number)
                obj1 = Utils.numberToString((Number)obj);
            if(mArrayIndex != null)
                mVar.setArr((int)mArrayIndex.evaluate(), obj1);
            else
                mVar.set(obj1);
            if(true) goto _L6; else goto _L5
_L5:
            obj;
            if(true) goto _L8; else goto _L7
_L7:
        }

        public static final String TAG_NAME = "Variable";
        private Expression mArrayIndex;
        protected double mDefNumberValue;
        protected String mDefStringValue;
        protected IndexedVariable mVar;

        public Variable(String s, String s1, Variables variables)
        {
            super(s, s1);
            mVar = new IndexedVariable(mName, variables, isNumber());
        }

        public Variable(Element element, Variables variables)
        {
            boolean flag = false;
            super(element);
            mArrayIndex = Expression.build(variables, element.getAttribute("arrIndex"));
            String s = mName;
            boolean flag1 = flag;
            if(isNumber())
            {
                flag1 = flag;
                if(mArrayIndex == null)
                    flag1 = true;
            }
            mVar = new IndexedVariable(s, variables, flag1);
            mDefStringValue = element.getAttribute("default");
            if(!isNumber())
                break MISSING_BLOCK_LABEL_98;
            mDefNumberValue = Double.parseDouble(mDefStringValue);
_L1:
            return;
            element;
            mDefStringValue = null;
            mDefNumberValue = 0.0D;
              goto _L1
        }
    }


    public VariableBinder(Element element, ScreenElementRoot screenelementroot)
    {
        mQueryAtStart = true;
        mVariables = new ArrayList();
        mRoot = screenelementroot;
        if(element != null)
        {
            mName = element.getAttribute("name");
            mDependency = element.getAttribute("dependency");
            mQueryAtStart = "false".equalsIgnoreCase(element.getAttribute("queryAtStart")) ^ true;
            mTrigger = CommandTrigger.fromParentElement(element, mRoot);
        }
    }

    public final void accept(VariableBinderVisitor variablebindervisitor)
    {
        variablebindervisitor.visit(this);
    }

    protected void addVariable(Variable variable)
    {
        mVariables.add(variable);
    }

    public void finish()
    {
        if(mTrigger != null)
            mTrigger.finish();
        mFinished = true;
    }

    protected ScreenContext getContext()
    {
        return mRoot.getContext();
    }

    public String getDependency()
    {
        return mDependency;
    }

    public String getName()
    {
        return mName;
    }

    public Variables getVariables()
    {
        return mRoot.getVariables();
    }

    public void init()
    {
        mFinished = false;
        mPaused = false;
        if(mTrigger != null)
            mTrigger.init();
        if(TextUtils.isEmpty(getDependency()) && mQueryAtStart)
            startQuery();
    }

    protected void loadVariables(Element element)
    {
        Utils.traverseXmlElementChildren(element, "Variable", new miui.maml.util.Utils.XmlTraverseListener() {

            public void onChild(Element element1)
            {
                element1 = onLoadVariable(element1);
                if(element1 != null)
                    mVariables.add(element1);
            }

            final VariableBinder this$0;

            
            {
                this$0 = VariableBinder.this;
                super();
            }
        }
);
    }

    protected Variable onLoadVariable(Element element)
    {
        return null;
    }

    protected final void onUpdateComplete()
    {
        if(mTrigger != null)
            mTrigger.perform();
        if(mQueryCompletedListener != null && TextUtils.isEmpty(mName) ^ true)
            mQueryCompletedListener.onQueryCompleted(mName);
        mRoot.requestUpdate();
    }

    public void pause()
    {
        if(mTrigger != null)
            mTrigger.pause();
        mPaused = true;
    }

    public void refresh()
    {
    }

    public void resume()
    {
        if(mTrigger != null)
            mTrigger.resume();
        mPaused = false;
    }

    public void setQueryCompleteListener(ContentProviderBinder.QueryCompleteListener querycompletelistener)
    {
        mQueryCompletedListener = querycompletelistener;
    }

    public void startQuery()
    {
    }

    public void tick()
    {
    }

    private String mDependency;
    protected boolean mFinished;
    protected String mName;
    protected boolean mPaused;
    protected boolean mQueryAtStart;
    private ContentProviderBinder.QueryCompleteListener mQueryCompletedListener;
    protected ScreenElementRoot mRoot;
    protected CommandTrigger mTrigger;
    protected ArrayList mVariables;
}
