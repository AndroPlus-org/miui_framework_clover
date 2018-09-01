// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.util.SparseArray;
import java.util.HashMap;

// Referenced classes of package miui.log:
//            MiuiTag

public final class MiuiTags
{

    public MiuiTags()
    {
    }

    public static MiuiTag get(int i)
    {
        return (MiuiTag)miuiTagSparseArray.get(i);
    }

    public static MiuiTag get(String s)
    {
        return (MiuiTag)miuiTagMap.get(s);
    }

    public static boolean isOn(int i)
    {
        MiuiTag miuitag = (MiuiTag)miuiTagSparseArray.get(i);
        boolean flag;
        if(miuitag == null)
            flag = false;
        else
            flag = miuitag.isOn();
        return flag;
    }

    public static boolean isOn(String s)
    {
        s = (MiuiTag)miuiTagMap.get(s);
        boolean flag;
        if(s == null)
            flag = false;
        else
            flag = s.isOn();
        return flag;
    }

    public static void switchOff(int i)
    {
        MiuiTag miuitag = (MiuiTag)miuiTagSparseArray.get(i);
        if(miuitag != null)
            miuitag.switchOff();
    }

    public static void switchOff(String s)
    {
        s = (MiuiTag)miuiTagMap.get(s);
        if(s != null)
            s.switchOff();
    }

    public static void switchOn(int i)
    {
        MiuiTag miuitag = (MiuiTag)miuiTagSparseArray.get(i);
        if(miuitag != null)
            miuitag.switchOn();
    }

    public static void switchOn(String s)
    {
        s = (MiuiTag)miuiTagMap.get(s);
        if(s != null)
            s.switchOn();
    }

    public static final int TAG_ID_SEND_BROADCAST = 0;
    public static final String TAG_SEND_BROADCAST = "SendBroadcast";
    public static final boolean TAG_SEND_BROADCAST_DEFAULT = false;
    public static final MiuiTag TagSendBroadcast;
    private static final MiuiTag allMiuiTags[];
    private static final HashMap miuiTagMap;
    private static final SparseArray miuiTagSparseArray;

    static 
    {
        TagSendBroadcast = new MiuiTag(0, "SendBroadcast", false);
        allMiuiTags = (new MiuiTag[] {
            TagSendBroadcast
        });
        miuiTagMap = new HashMap();
        miuiTagSparseArray = new SparseArray();
        for(int i = 0; i < allMiuiTags.length; i++)
        {
            MiuiTag miuitag = allMiuiTags[i];
            miuiTagMap.put(miuitag.name, miuitag);
            miuiTagSparseArray.append(miuitag.id, miuitag);
        }

    }
}
