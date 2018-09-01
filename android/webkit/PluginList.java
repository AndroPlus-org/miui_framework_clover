// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.webkit:
//            Plugin

public class PluginList
{

    public PluginList()
    {
        mPlugins = new ArrayList();
    }

    public void addPlugin(Plugin plugin)
    {
        this;
        JVM INSTR monitorenter ;
        if(!mPlugins.contains(plugin))
            mPlugins.add(plugin);
        this;
        JVM INSTR monitorexit ;
        return;
        plugin;
        throw plugin;
    }

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        mPlugins.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public List getList()
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = mPlugins;
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public void pluginClicked(Context context, int i)
    {
        this;
        JVM INSTR monitorenter ;
        try
        {
            ((Plugin)mPlugins.get(i)).dispatchClickEvent(context);
        }
        // Misplaced declaration of an exception variable
        catch(Context context) { }
        this;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
    }

    public void removePlugin(Plugin plugin)
    {
        this;
        JVM INSTR monitorenter ;
        int i = mPlugins.indexOf(plugin);
        if(i == -1)
            break MISSING_BLOCK_LABEL_25;
        mPlugins.remove(i);
        this;
        JVM INSTR monitorexit ;
        return;
        plugin;
        throw plugin;
    }

    private ArrayList mPlugins;
}
