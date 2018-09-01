// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;


// Referenced classes of package android.content.res:
//            ThemedResourceCache, ConstantState, Configuration, Resources

public class ConfigurationBoundResourceCache extends ThemedResourceCache
{

    public ConfigurationBoundResourceCache()
    {
    }

    public volatile Object get(long l, Resources.Theme theme)
    {
        return super.get(l, theme);
    }

    public Object getInstance(long l, Resources resources, Resources.Theme theme)
    {
        ConstantState constantstate = (ConstantState)get(l, theme);
        if(constantstate != null)
            return constantstate.newInstance(resources, theme);
        else
            return null;
    }

    public volatile void onConfigurationChange(int i)
    {
        super.onConfigurationChange(i);
    }

    public volatile void put(long l, Resources.Theme theme, Object obj)
    {
        super.put(l, theme, obj);
    }

    public volatile void put(long l, Resources.Theme theme, Object obj, boolean flag)
    {
        super.put(l, theme, obj, flag);
    }

    public boolean shouldInvalidateEntry(ConstantState constantstate, int i)
    {
        return Configuration.needNewResources(i, constantstate.getChangingConfigurations());
    }

    public volatile boolean shouldInvalidateEntry(Object obj, int i)
    {
        return shouldInvalidateEntry((ConstantState)obj, i);
    }
}
