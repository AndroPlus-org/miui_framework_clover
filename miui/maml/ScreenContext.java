// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.ContextThemeWrapper;
import java.util.HashMap;
import miui.maml.data.ContextVariables;
import miui.maml.data.Variables;
import miui.maml.elements.ScreenElementFactory;

// Referenced classes of package miui.maml:
//            ResourceManager, ObjectFactory, ResourceLoader

public class ScreenContext
{

    public ScreenContext(Context context, ResourceLoader resourceloader)
    {
        this(context, resourceloader, new ScreenElementFactory());
    }

    public ScreenContext(Context context, ResourceLoader resourceloader, ScreenElementFactory screenelementfactory)
    {
        this(context, new ResourceManager(resourceloader), screenelementfactory);
    }

    public ScreenContext(Context context, ResourceManager resourcemanager)
    {
        this(context, resourcemanager, new ScreenElementFactory());
    }

    public ScreenContext(Context context, ResourceManager resourcemanager, ScreenElementFactory screenelementfactory)
    {
        this(context, resourcemanager, screenelementfactory, new Variables());
    }

    public ScreenContext(Context context, ResourceManager resourcemanager, ScreenElementFactory screenelementfactory, Variables variables)
    {
        Context context1 = context.getApplicationContext();
        int i;
        if(context1 == null)
            context1 = context;
        i = context.getThemeResId();
        if(i != 0)
            mContext = new ContextThemeWrapper(context1, i);
        else
            mContext = context1;
        mResourceManager = resourcemanager;
        mFactory = screenelementfactory;
        mHandler = new Handler(Looper.getMainLooper());
        mVariables = variables;
        mContextVariables = new ContextVariables();
    }

    public Handler getHandler()
    {
        return mHandler;
    }

    public final ObjectFactory getObjectFactory(String s)
    {
        this;
        JVM INSTR monitorenter ;
        HashMap hashmap;
        try
        {
            hashmap = mObjectFactories;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return null;
        }
        if(hashmap != null) goto _L2; else goto _L1
_L1:
        s = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return s;
_L2:
        s = (ObjectFactory)mObjectFactories.get(s);
        if(true) goto _L4; else goto _L3
_L3:
        s;
        throw s;
    }

    public boolean postDelayed(Runnable runnable, long l)
    {
        return mHandler.postDelayed(runnable, l);
    }

    public final void registerObjectFactory(String s, ObjectFactory objectfactory)
    {
        this;
        JVM INSTR monitorenter ;
        if(objectfactory != null)
            break MISSING_BLOCK_LABEL_25;
        if(mObjectFactories != null)
            mObjectFactories.remove(s);
        this;
        JVM INSTR monitorexit ;
        return;
        if(!s.equals(objectfactory.getName()))
        {
            objectfactory = JVM INSTR new #126 <Class IllegalArgumentException>;
            StringBuilder stringbuilder = JVM INSTR new #128 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            objectfactory.IllegalArgumentException(stringbuilder.append("ObjectFactory name mismatchs ").append(s).toString());
            throw objectfactory;
        }
        break MISSING_BLOCK_LABEL_72;
        s;
        this;
        JVM INSTR monitorexit ;
        throw s;
        ObjectFactory objectfactory2;
        if(mObjectFactories == null)
        {
            HashMap hashmap = JVM INSTR new #97  <Class HashMap>;
            hashmap.HashMap();
            mObjectFactories = hashmap;
        }
        objectfactory2 = (ObjectFactory)mObjectFactories.get(s);
        ObjectFactory objectfactory1 = objectfactory2;
_L1:
        if(objectfactory1 == null)
            break MISSING_BLOCK_LABEL_128;
        if(objectfactory1 == objectfactory)
            return;
        objectfactory1 = objectfactory1.getOld();
          goto _L1
        objectfactory.setOld(objectfactory2);
        mObjectFactories.put(s, objectfactory);
        this;
        JVM INSTR monitorexit ;
    }

    public void removeCallbacks(Runnable runnable)
    {
        mHandler.removeCallbacks(runnable);
    }

    public final Context mContext;
    public final ContextVariables mContextVariables;
    public final ScreenElementFactory mFactory;
    private final Handler mHandler;
    private HashMap mObjectFactories;
    public final ResourceManager mResourceManager;
    public final Variables mVariables;
}
