// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.content.*;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.ScreenContext;
import miui.maml.ScreenElementRoot;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.data:
//            VariableBinder, Variables

public class BroadcastBinder extends VariableBinder
{
    private static class Variable extends VariableBinder.Variable
    {

        public String mExtraName;

        public Variable(Element element, Variables variables)
        {
            super(element, variables);
            mExtraName = element.getAttribute("extra");
        }
    }


    public BroadcastBinder(Element element, ScreenElementRoot screenelementroot)
    {
        super(element, screenelementroot);
        load(element);
    }

    private void load(Element element)
    {
        if(element == null)
        {
            Log.e("BroadcastBinder", "ContentProviderBinder node is null");
            throw new NullPointerException("node is null");
        }
        mAction = element.getAttribute("action");
        if(TextUtils.isEmpty(mAction))
        {
            Log.e("BroadcastBinder", "no action in broadcast binder");
            throw new IllegalArgumentException("no action in broadcast binder element");
        } else
        {
            mIntentFilter = new IntentFilter(mAction);
            loadVariables(element);
            return;
        }
    }

    private void updateVariables(Intent intent)
    {
        Iterator iterator;
        if(intent == null)
            return;
        Log.d("BroadcastBinder", (new StringBuilder()).append("updateVariables: ").append(intent).toString());
        iterator = mVariables.iterator();
_L13:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Variable variable;
        double d;
        variable = (Variable)(VariableBinder.Variable)iterator.next();
        d = 0.0D;
        variable.mType;
        JVM INSTR tableswitch 2 2: default 88
    //                   2 236;
           goto _L3 _L4
_L3:
        variable.mType;
        JVM INSTR tableswitch 3 6: default 124
    //                   3 309
    //                   4 328
    //                   5 290
    //                   6 273;
           goto _L5 _L6 _L7 _L8 _L9
_L5:
        Log.w("BroadcastBinder", (new StringBuilder()).append("invalide type").append(variable.mTypeStr).toString());
_L11:
        String s;
        variable.set(d);
        s = String.format("%f", new Object[] {
            Double.valueOf(d)
        });
_L10:
        s = String.format("name:%s type:%s value:%s", new Object[] {
            variable.mName, variable.mTypeStr, s
        });
        Log.d("BroadcastBinder", (new StringBuilder()).append("updateVariables: ").append(s).toString());
        continue; /* Loop/switch isn't completed */
_L4:
        s = intent.getStringExtra(variable.mExtraName);
        String s1;
        if(s == null)
            s1 = variable.mDefStringValue;
        else
            s1 = s;
        variable.set(s1);
        if(true) goto _L10; else goto _L9
_L9:
        d = intent.getDoubleExtra(variable.mExtraName, variable.mDefNumberValue);
        continue; /* Loop/switch isn't completed */
_L8:
        d = intent.getFloatExtra(variable.mExtraName, (float)variable.mDefNumberValue);
        continue; /* Loop/switch isn't completed */
_L6:
        d = intent.getIntExtra(variable.mExtraName, (int)variable.mDefNumberValue);
        continue; /* Loop/switch isn't completed */
_L7:
        d = intent.getLongExtra(variable.mExtraName, (long)variable.mDefNumberValue);
        if(true) goto _L11; else goto _L2
_L2:
        return;
        if(true) goto _L13; else goto _L12
_L12:
    }

    protected void addVariable(Variable variable)
    {
        mVariables.add(variable);
    }

    public void finish()
    {
        super.finish();
        unregister();
    }

    public void init()
    {
        super.init();
        register();
    }

    protected Variable onLoadVariable(Element element)
    {
        return new Variable(element, getContext().mVariables);
    }

    protected volatile VariableBinder.Variable onLoadVariable(Element element)
    {
        return onLoadVariable(element);
    }

    protected void onNotify(Context context, Intent intent, Object obj)
    {
        updateVariables(intent);
        onUpdateComplete();
    }

    protected void onRegister()
    {
        updateVariables(getContext().mContext.registerReceiver(mIntentReceiver, mIntentFilter));
        onUpdateComplete();
    }

    protected void onUnregister()
    {
        getContext().mContext.unregisterReceiver(mIntentReceiver);
    }

    protected void register()
    {
        if(mRegistered)
        {
            return;
        } else
        {
            onRegister();
            mRegistered = true;
            return;
        }
    }

    protected void unregister()
    {
        if(!mRegistered)
            return;
        try
        {
            onUnregister();
        }
        catch(IllegalArgumentException illegalargumentexception) { }
        mRegistered = false;
    }

    private static final boolean DBG = true;
    private static final String LOG_TAG = "BroadcastBinder";
    public static final String TAG_NAME = "BroadcastBinder";
    private String mAction;
    private IntentFilter mIntentFilter;
    private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent)
        {
            Log.i("BroadcastBinder", (new StringBuilder()).append("onNotify: ").append(toString()).toString());
            onNotify(context, intent, null);
        }

        final BroadcastBinder this$0;

            
            {
                this$0 = BroadcastBinder.this;
                super();
            }
    }
;
    private boolean mRegistered;
}
