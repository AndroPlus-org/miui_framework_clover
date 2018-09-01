// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;


// Referenced classes of package miui.log:
//            AndroidTags, MiuiTags, TagGroups, AndroidTag, 
//            MiuiTag, TagGroup

public final class Tags
{

    public Tags()
    {
    }

    public static AndroidTag getAndroidTag(String s)
    {
        return AndroidTags.get(s);
    }

    public static MiuiTag getMiuiTag(int i)
    {
        return MiuiTags.get(i);
    }

    public static MiuiTag getMiuiTag(String s)
    {
        return MiuiTags.get(s);
    }

    public static TagGroup getTagGroup(String s)
    {
        return TagGroups.get(s);
    }

    public static boolean isAndroidTagOn(String s)
    {
        return AndroidTags.isOn(s);
    }

    public static boolean isMiuiTagOn(int i)
    {
        return MiuiTags.isOn(i);
    }

    public static boolean isMiuiTagOn(String s)
    {
        return MiuiTags.isOn(s);
    }

    public static boolean isTagGroupOn(String s)
    {
        return TagGroups.isOn(s);
    }

    public static void switchOffAndroidTag(String s)
    {
        AndroidTags.switchOff(s);
    }

    public static void switchOffMiuiTag(int i)
    {
        MiuiTags.switchOff(i);
    }

    public static void switchOffMiuiTag(String s)
    {
        MiuiTags.switchOff(s);
    }

    public static void switchOffTagGroup(String s)
    {
        miui/log/Tags;
        JVM INSTR monitorenter ;
        TagGroups.switchOff(s);
        miui/log/Tags;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public static void switchOnAndroidTag(String s)
    {
        AndroidTags.switchOn(s);
    }

    public static void switchOnMiuiTag(int i)
    {
        MiuiTags.switchOn(i);
    }

    public static void switchOnMiuiTag(String s)
    {
        MiuiTags.switchOn(s);
    }

    public static void switchOnTagGroup(String s)
    {
        miui/log/Tags;
        JVM INSTR monitorenter ;
        TagGroups.switchOn(s);
        miui/log/Tags;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }
}
