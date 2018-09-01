// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import java.util.HashMap;

// Referenced classes of package miui.log:
//            TagGroup

public final class TagGroups
{

    public TagGroups()
    {
    }

    public static TagGroup get(String s)
    {
        return (TagGroup)tagGroupMap.get(s);
    }

    public static boolean isOn(String s)
    {
        s = (TagGroup)tagGroupMap.get(s);
        boolean flag;
        if(s == null)
            flag = false;
        else
            flag = s.isOn();
        return flag;
    }

    public static void switchOff(String s)
    {
        s = (TagGroup)tagGroupMap.get(s);
        if(s != null)
            s.switchOff();
    }

    public static void switchOn(String s)
    {
        s = (TagGroup)tagGroupMap.get(s);
        if(s != null)
            s.switchOn();
    }

    public static final String TAGGROUP_BROADCAST = "Broadcast";
    public static final TagGroup TagGroupBroadcast;
    private static final TagGroup allTagGroups[];
    private static final HashMap tagGroupMap;

    static 
    {
        int i = 0;
        TagGroupBroadcast = new TagGroup("Broadcast", new String[] {
            "SendBroadcast"
        });
        allTagGroups = (new TagGroup[] {
            TagGroupBroadcast
        });
        tagGroupMap = new HashMap();
        TagGroup ataggroup[] = allTagGroups;
        for(int j = ataggroup.length; i < j; i++)
        {
            TagGroup taggroup = ataggroup[i];
            tagGroupMap.put(taggroup.name, taggroup);
        }

    }
}
