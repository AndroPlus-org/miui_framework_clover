// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import java.util.Locale;

public class NetworkConfig
{

    public NetworkConfig(String s)
    {
        s = s.split(",");
        name = s[0].trim().toLowerCase(Locale.ROOT);
        type = Integer.parseInt(s[1]);
        radio = Integer.parseInt(s[2]);
        priority = Integer.parseInt(s[3]);
        restoreTime = Integer.parseInt(s[4]);
        dependencyMet = Boolean.parseBoolean(s[5]);
    }

    public boolean isDefault()
    {
        boolean flag;
        if(type == radio)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean dependencyMet;
    public String name;
    public int priority;
    public int radio;
    public int restoreTime;
    public int type;
}
