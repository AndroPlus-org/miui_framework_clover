// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.text.TextUtils;
import java.util.HashMap;

public class LogSwitch
    implements Cloneable
{

    public LogSwitch(String s, String s1, boolean flag)
    {
        tagName = s;
        tagGroupName = s1;
        isOn = flag;
        if(!TextUtils.isEmpty(tagName))
            uniqueName = (new StringBuilder()).append("tag_").append(tagName).toString();
        else
            uniqueName = (new StringBuilder()).append("group_").append(tagGroupName).toString();
    }

    public static LogSwitch parseLogSwitch(String s)
    {
        s = s.trim();
        if(s.length() < 4)
            return null;
        s = s.split("\\s+");
        if(s.length != 3)
            return null;
        if(s[0].equalsIgnoreCase("Tag"))
            return new LogSwitch(s[1].trim(), "", s[2].trim().equalsIgnoreCase("on"));
        if(s[0].equalsIgnoreCase("Group"))
            return new LogSwitch("", s[1].trim(), s[2].trim().equalsIgnoreCase("on"));
        else
            return null;
    }

    public static HashMap parseLogSwitches(String s)
    {
        String as[] = s.split(",");
        s = new HashMap();
        int i = 0;
        for(int j = as.length; i < j; i++)
        {
            LogSwitch logswitch = parseLogSwitch(as[i]);
            if(logswitch != null)
                s.put(logswitch.uniqueName, logswitch);
        }

        return s;
    }

    public Object clone()
    {
        Object obj;
        try
        {
            obj = super.clone();
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            return null;
        }
        return obj;
    }

    public String toString()
    {
        String s;
        if(isOn)
            s = "on";
        else
            s = "off";
        if(!TextUtils.isEmpty(tagName))
            return (new StringBuilder()).append("tag ").append(tagName).append(" ").append(s).toString();
        else
            return (new StringBuilder()).append("group ").append(tagGroupName).append(" ").append(s).toString();
    }

    public final boolean isOn;
    public final String tagGroupName;
    public final String tagName;
    public final String uniqueName;
}
