// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.push;

import android.text.TextUtils;
import com.google.android.collect.Maps;
import java.util.*;

public class PushAttributes
{

    public PushAttributes(Map map)
    {
        mAttrs.putAll(map);
    }

    public static PushAttributes parse(String s)
    {
        HashMap hashmap = Maps.newHashMap();
        if(!TextUtils.isEmpty(s))
        {
            String as[] = s.split(",");
            int i = as.length;
            for(int j = 0; j < i; j++)
            {
                s = as[j].split(":");
                if(s.length == 2)
                    hashmap.put(s[0], s[1]);
            }

        }
        return new PushAttributes(hashmap);
    }

    public String get(String s)
    {
        return (String)mAttrs.get(s);
    }

    public String toPlain()
    {
        StringBuilder stringbuilder = new StringBuilder();
        java.util.Map.Entry entry;
        for(Iterator iterator = mAttrs.entrySet().iterator(); iterator.hasNext(); stringbuilder.append((String)entry.getValue()))
        {
            entry = (java.util.Map.Entry)iterator.next();
            if(stringbuilder.length() > 0)
                stringbuilder.append(',');
            stringbuilder.append((String)entry.getKey());
            stringbuilder.append(':');
        }

        return stringbuilder.toString();
    }

    public String toString()
    {
        return toPlain();
    }

    private final Map mAttrs = Maps.newHashMap();
}
