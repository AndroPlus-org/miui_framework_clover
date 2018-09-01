// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.ScreenElementRoot;
import miui.maml.util.TextFormatter;
import org.w3c.dom.*;

// Referenced classes of package miui.maml.data:
//            ContentProviderBinder, VariableBinder, WebServiceBinder, SensorBinder, 
//            LocationBinder, BroadcastBinder, FileBinder, SettingsBinder, 
//            VariableBinderVisitor

public class VariableBinderManager
    implements ContentProviderBinder.QueryCompleteListener
{

    public VariableBinderManager(Element element, ScreenElementRoot screenelementroot)
    {
        mVariableBinders = new ArrayList();
        mRoot = screenelementroot;
        if(element != null)
            load(element, screenelementroot);
    }

    private static VariableBinder createBinder(Element element, ScreenElementRoot screenelementroot, VariableBinderManager variablebindermanager)
    {
        String s;
        Object obj;
        s = element.getTagName();
        obj = null;
        if(!s.equalsIgnoreCase("ContentProviderBinder")) goto _L2; else goto _L1
_L1:
        obj = new ContentProviderBinder(element, screenelementroot);
_L4:
        if(obj != null)
            ((VariableBinder) (obj)).setQueryCompleteListener(variablebindermanager);
        return ((VariableBinder) (obj));
_L2:
        if(s.equalsIgnoreCase("WebServiceBinder"))
            obj = new WebServiceBinder(element, screenelementroot);
        else
        if(s.equalsIgnoreCase("SensorBinder"))
            obj = new SensorBinder(element, screenelementroot);
        else
        if(s.equalsIgnoreCase("LocationBinder"))
            obj = new LocationBinder(element, screenelementroot);
        else
        if(s.equalsIgnoreCase("BroadcastBinder"))
            obj = new BroadcastBinder(element, screenelementroot);
        else
        if(s.equalsIgnoreCase("FileBinder"))
            obj = new FileBinder(element, screenelementroot);
        else
        if(s.equalsIgnoreCase("SettingsBinder"))
            obj = new SettingsBinder(element, screenelementroot);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void load(Element element, ScreenElementRoot screenelementroot)
    {
        if(element == null)
        {
            Log.e("VariableBinderManager", "node is null");
            throw new NullPointerException("node is null");
        } else
        {
            loadBinders(element, screenelementroot);
            return;
        }
    }

    private void loadBinders(Element element, ScreenElementRoot screenelementroot)
    {
        element = element.getChildNodes();
        for(int i = 0; i < element.getLength(); i++)
        {
            if(element.item(i).getNodeType() != 1)
                continue;
            VariableBinder variablebinder = createBinder((Element)element.item(i), screenelementroot, this);
            if(variablebinder != null)
                mVariableBinders.add(variablebinder);
        }

    }

    public final void acceptVisitor(VariableBinderVisitor variablebindervisitor)
    {
        for(Iterator iterator = mVariableBinders.iterator(); iterator.hasNext(); ((VariableBinder)iterator.next()).accept(variablebindervisitor));
    }

    public ContentProviderBinder.Builder addContentProviderBinder(String s)
    {
        return addContentProviderBinder(new TextFormatter(mRoot.getVariables(), s));
    }

    public ContentProviderBinder.Builder addContentProviderBinder(String s, String s1)
    {
        return addContentProviderBinder(new TextFormatter(mRoot.getVariables(), s, s1));
    }

    public ContentProviderBinder.Builder addContentProviderBinder(TextFormatter textformatter)
    {
        ContentProviderBinder contentproviderbinder = new ContentProviderBinder(mRoot);
        contentproviderbinder.setQueryCompleteListener(this);
        contentproviderbinder.mUriFormatter = textformatter;
        mVariableBinders.add(contentproviderbinder);
        return new ContentProviderBinder.Builder(contentproviderbinder);
    }

    public VariableBinder findBinder(String s)
    {
        for(Iterator iterator = mVariableBinders.iterator(); iterator.hasNext();)
        {
            VariableBinder variablebinder = (VariableBinder)iterator.next();
            if(TextUtils.equals(s, variablebinder.getName()))
                return variablebinder;
        }

        return null;
    }

    public void finish()
    {
        for(Iterator iterator = mVariableBinders.iterator(); iterator.hasNext(); ((VariableBinder)iterator.next()).finish());
    }

    public void init()
    {
        for(Iterator iterator = mVariableBinders.iterator(); iterator.hasNext(); ((VariableBinder)iterator.next()).init());
    }

    public void onQueryCompleted(String s)
    {
        if(TextUtils.isEmpty(s))
            return;
        Iterator iterator = mVariableBinders.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            VariableBinder variablebinder = (VariableBinder)iterator.next();
            String s1 = variablebinder.getDependency();
            if(!TextUtils.isEmpty(s1) && s1.equals(s))
                variablebinder.startQuery();
        } while(true);
    }

    public void pause()
    {
        for(Iterator iterator = mVariableBinders.iterator(); iterator.hasNext(); ((VariableBinder)iterator.next()).pause());
    }

    public void resume()
    {
        for(Iterator iterator = mVariableBinders.iterator(); iterator.hasNext(); ((VariableBinder)iterator.next()).resume());
    }

    public void tick()
    {
        for(Iterator iterator = mVariableBinders.iterator(); iterator.hasNext(); ((VariableBinder)iterator.next()).tick());
    }

    private static final String LOG_TAG = "VariableBinderManager";
    public static final String TAG_NAME = "VariableBinders";
    private ScreenElementRoot mRoot;
    private ArrayList mVariableBinders;
}
