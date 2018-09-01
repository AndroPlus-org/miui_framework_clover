// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import miui.maml.ScreenElementRoot;

// Referenced classes of package miui.maml.data:
//            DateTimeVariableUpdater, BatteryVariableUpdater, VariableUpdater

public class VariableUpdaterManager
{

    public VariableUpdaterManager(ScreenElementRoot screenelementroot)
    {
        mUpdaters = new ArrayList();
        mRoot = screenelementroot;
    }

    public void add(VariableUpdater variableupdater)
    {
        mUpdaters.add(variableupdater);
    }

    public void addFromTag(String s)
    {
        if(TextUtils.isEmpty(s) || "none".equalsIgnoreCase(s))
            return;
        String as[] = s.split(",");
        int i = as.length;
        int j = 0;
        while(j < i) 
        {
            String s1 = as[j].trim();
            String s2 = s1;
            s = null;
            int k = s1.indexOf('.');
            if(k != -1)
            {
                s2 = s1.substring(0, k);
                s = s1.substring(k + 1);
            }
            if(s2.equals("DateTime"))
                add(new DateTimeVariableUpdater(this, s));
            else
            if(s2.equals("Battery"))
                add(new BatteryVariableUpdater(this));
            j++;
        }
    }

    public void finish()
    {
        for(Iterator iterator = mUpdaters.iterator(); iterator.hasNext(); ((VariableUpdater)iterator.next()).finish());
    }

    public ScreenElementRoot getRoot()
    {
        return mRoot;
    }

    public void init()
    {
        for(Iterator iterator = mUpdaters.iterator(); iterator.hasNext(); ((VariableUpdater)iterator.next()).init());
    }

    public void pause()
    {
        for(Iterator iterator = mUpdaters.iterator(); iterator.hasNext(); ((VariableUpdater)iterator.next()).pause());
    }

    public void remove(VariableUpdater variableupdater)
    {
        mUpdaters.remove(variableupdater);
    }

    public void resume()
    {
        for(Iterator iterator = mUpdaters.iterator(); iterator.hasNext(); ((VariableUpdater)iterator.next()).resume());
    }

    public void tick(long l)
    {
        for(Iterator iterator = mUpdaters.iterator(); iterator.hasNext(); ((VariableUpdater)iterator.next()).tick(l));
    }

    public static final String USE_TAG_NONE = "none";
    private ScreenElementRoot mRoot;
    private ArrayList mUpdaters;
}
