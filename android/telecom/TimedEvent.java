// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import java.util.*;

public abstract class TimedEvent
{

    public TimedEvent()
    {
    }

    public static Map averageTimings(Collection collection)
    {
        HashMap hashmap = new HashMap();
        HashMap hashmap1 = new HashMap();
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            collection = (TimedEvent)iterator.next();
            if(hashmap.containsKey(collection.getKey()))
            {
                hashmap.put(collection.getKey(), Integer.valueOf(((Integer)hashmap.get(collection.getKey())).intValue() + 1));
                hashmap1.put(collection.getKey(), Double.valueOf(((Double)hashmap1.get(collection.getKey())).doubleValue() + (double)collection.getTime()));
            } else
            {
                hashmap.put(collection.getKey(), Integer.valueOf(1));
                hashmap1.put(collection.getKey(), Double.valueOf(collection.getTime()));
            }
        }

        for(Iterator iterator1 = hashmap1.entrySet().iterator(); iterator1.hasNext(); hashmap1.put(collection.getKey(), Double.valueOf(((Double)collection.getValue()).doubleValue() / (double)((Integer)hashmap.get(collection.getKey())).intValue())))
            collection = (java.util.Map.Entry)iterator1.next();

        return hashmap1;
    }

    public abstract Object getKey();

    public abstract long getTime();
}
