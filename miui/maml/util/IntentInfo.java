// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.data.Expression;
import miui.maml.data.Variables;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.util:
//            Task, Utils

public class IntentInfo
{
    private class Extra
    {

        private void load(Element element)
        {
            String s;
            if(element == null)
            {
                Log.e("TaskVariable", "node is null");
                return;
            }
            mName = element.getAttribute("name");
            s = element.getAttribute("type");
            if(!"string".equalsIgnoreCase(s)) goto _L2; else goto _L1
_L1:
            mType = Type.STRING;
_L4:
            mExpression = Expression.build(IntentInfo._2D_get1(IntentInfo.this), element.getAttribute("expression"));
            if(mExpression == null)
                Log.e("TaskVariable", "invalid expression in IntentCommand");
            mCondition = Expression.build(IntentInfo._2D_get1(IntentInfo.this), element.getAttribute("condition"));
            return;
_L2:
            if("int".equalsIgnoreCase(s) || "integer".equalsIgnoreCase(s))
                mType = Type.INT;
            else
            if("long".equalsIgnoreCase(s))
                mType = Type.LONG;
            else
            if("float".equalsIgnoreCase(s))
                mType = Type.FLOAT;
            else
            if("double".equalsIgnoreCase(s))
                mType = Type.DOUBLE;
            else
            if("boolean".equalsIgnoreCase(s))
                mType = Type.BOOLEAN;
            if(true) goto _L4; else goto _L3
_L3:
        }

        public double getDouble()
        {
            if(mExpression == null)
                return 0.0D;
            else
                return mExpression.evaluate();
        }

        public String getName()
        {
            return mName;
        }

        public String getString()
        {
            if(mExpression == null)
                return null;
            else
                return mExpression.evaluateStr();
        }

        public boolean isConditionTrue()
        {
            boolean flag = true;
            if(mCondition == null)
                return true;
            if(mCondition.evaluate() <= 0.0D)
                flag = false;
            return flag;
        }

        public static final String TAG_NAME = "Extra";
        private Expression mCondition;
        private Expression mExpression;
        private String mName;
        protected Type mType;
        final IntentInfo this$0;

        public Extra(Element element)
        {
            this$0 = IntentInfo.this;
            super();
            mType = Type.DOUBLE;
            load(element);
        }
    }

    private static final class Type extends Enum
    {

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(miui/maml/util/IntentInfo$Type, s);
        }

        public static Type[] values()
        {
            return $VALUES;
        }

        private static final Type $VALUES[];
        public static final Type BOOLEAN;
        public static final Type DOUBLE;
        public static final Type FLOAT;
        public static final Type INT;
        public static final Type LONG;
        public static final Type STRING;

        static 
        {
            STRING = new Type("STRING", 0);
            INT = new Type("INT", 1);
            LONG = new Type("LONG", 2);
            FLOAT = new Type("FLOAT", 3);
            DOUBLE = new Type("DOUBLE", 4);
            BOOLEAN = new Type("BOOLEAN", 5);
            $VALUES = (new Type[] {
                STRING, INT, LONG, FLOAT, DOUBLE, BOOLEAN
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }


    static ArrayList _2D_get0(IntentInfo intentinfo)
    {
        return intentinfo.mExtraList;
    }

    static Variables _2D_get1(IntentInfo intentinfo)
    {
        return intentinfo.mVariables;
    }

    private static int[] _2D_getmiui_2D_maml_2D_util_2D_IntentInfo$TypeSwitchesValues()
    {
        if(_2D_miui_2D_maml_2D_util_2D_IntentInfo$TypeSwitchesValues != null)
            return _2D_miui_2D_maml_2D_util_2D_IntentInfo$TypeSwitchesValues;
        int ai[] = new int[Type.values().length];
        try
        {
            ai[Type.BOOLEAN.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[Type.DOUBLE.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[Type.FLOAT.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[Type.INT.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[Type.LONG.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[Type.STRING.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_miui_2D_maml_2D_util_2D_IntentInfo$TypeSwitchesValues = ai;
        return ai;
    }

    public IntentInfo(Element element, Variables variables)
    {
        mExtraList = new ArrayList();
        if(element == null)
        {
            return;
        } else
        {
            mTask = Task.load(element);
            mVariables = variables;
            mPackageNameExp = Expression.build(variables, element.getAttribute("packageExp"));
            mClassNameExp = Expression.build(variables, element.getAttribute("classExp"));
            mUri = element.getAttribute("uri");
            mUriExp = Expression.build(variables, element.getAttribute("uriExp"));
            loadExtras(element);
            return;
        }
    }

    private void loadExtras(Element element)
    {
        Utils.traverseXmlElementChildren(element, "Extra", new Utils.XmlTraverseListener() {

            public void onChild(Element element1)
            {
                IntentInfo._2D_get0(IntentInfo.this).add(new Extra(element1));
            }

            final IntentInfo this$0;

            
            {
                this$0 = IntentInfo.this;
                super();
            }
        }
);
    }

    public String getAction()
    {
        String s = null;
        if(mTask != null)
            s = mTask.action;
        return s;
    }

    public String getId()
    {
        String s = null;
        if(mTask != null)
            s = mTask.id;
        return s;
    }

    public void set(Task task)
    {
        mTask = task;
    }

    public void update(Intent intent)
    {
        Object obj;
        Object obj1;
        if(mTask != null)
            obj = mTask.action;
        else
            obj = null;
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            intent.setAction(((String) (obj)));
        if(mTask != null)
            obj = mTask.type;
        else
            obj = null;
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            intent.setType(((String) (obj)));
        if(mTask != null)
            obj = mTask.category;
        else
            obj = null;
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            intent.addCategory(((String) (obj)));
        if(mTask != null)
            obj = mTask.packageName;
        else
            obj = null;
        obj1 = obj;
        if(mPackageNameExp != null)
            obj1 = mPackageNameExp.evaluateStr();
        if(mTask != null)
            obj = mTask.className;
        else
            obj = null;
        if(mClassNameExp != null)
            obj = mClassNameExp.evaluateStr();
        if(!TextUtils.isEmpty(((CharSequence) (obj1))))
            if(!TextUtils.isEmpty(((CharSequence) (obj))))
                intent.setClassName(((String) (obj1)), ((String) (obj)));
            else
                intent.setPackage(((String) (obj1)));
        obj = mUri;
        if(mUriExp != null)
            obj = mUriExp.evaluateStr();
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            intent.setData(Uri.parse(((String) (obj))));
        if(mExtraList != null)
        {
            obj = mExtraList.iterator();
            do
            {
                if(!((Iterator) (obj)).hasNext())
                    break;
                obj1 = (Extra)((Iterator) (obj)).next();
                if(((Extra) (obj1)).isConditionTrue())
                    switch(_2D_getmiui_2D_maml_2D_util_2D_IntentInfo$TypeSwitchesValues()[((Extra) (obj1)).mType.ordinal()])
                    {
                    case 1: // '\001'
                        String s = ((Extra) (obj1)).getName();
                        boolean flag;
                        if(((Extra) (obj1)).getDouble() > 0.0D)
                            flag = true;
                        else
                            flag = false;
                        intent.putExtra(s, flag);
                        break;

                    case 6: // '\006'
                        intent.putExtra(((Extra) (obj1)).getName(), ((Extra) (obj1)).getString());
                        break;

                    case 4: // '\004'
                        intent.putExtra(((Extra) (obj1)).getName(), (int)((Extra) (obj1)).getDouble());
                        break;

                    case 5: // '\005'
                        intent.putExtra(((Extra) (obj1)).getName(), (long)((Extra) (obj1)).getDouble());
                        break;

                    case 3: // '\003'
                        intent.putExtra(((Extra) (obj1)).getName(), (float)((Extra) (obj1)).getDouble());
                        break;

                    case 2: // '\002'
                        intent.putExtra(((Extra) (obj1)).getName(), ((Extra) (obj1)).getDouble());
                        break;
                    }
                else
                    intent.removeExtra(((Extra) (obj1)).getName());
            } while(true);
        }
    }

    private static final int _2D_miui_2D_maml_2D_util_2D_IntentInfo$TypeSwitchesValues[];
    private static final String LOG_TAG = "TaskVariable";
    private Expression mClassNameExp;
    private ArrayList mExtraList;
    private Expression mPackageNameExp;
    private Task mTask;
    private String mUri;
    private Expression mUriExp;
    private Variables mVariables;
}
