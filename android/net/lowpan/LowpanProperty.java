// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import java.util.Map;

public abstract class LowpanProperty
{

    public LowpanProperty()
    {
    }

    public Object getFromMap(Map map)
    {
        return map.get(getName());
    }

    public abstract String getName();

    public abstract Class getType();

    public void putInMap(Map map, Object obj)
    {
        map.put(getName(), obj);
    }
}
