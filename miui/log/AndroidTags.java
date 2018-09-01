// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;

import android.text.TextUtils;
import java.util.HashMap;
import miui.util.ObjectReference;
import miui.util.ReflectionUtils;

// Referenced classes of package miui.log:
//            AndroidTag

public final class AndroidTags
{

    public AndroidTags()
    {
    }

    public static AndroidTag add(Class class1, String s, boolean flag)
    {
        miui/log/AndroidTags;
        JVM INSTR monitorenter ;
        AndroidTag androidtag = (AndroidTag)androidTagMap.get(AndroidTag.buildFieldFullPath(class1, s));
        AndroidTag androidtag1;
        androidtag1 = androidtag;
        if(androidtag != null)
            break MISSING_BLOCK_LABEL_52;
        androidtag1 = JVM INSTR new #26  <Class AndroidTag>;
        androidtag1.AndroidTag(class1, s, flag);
        androidTagMap.put(androidtag1.fieldFullPath, androidtag1);
        miui/log/AndroidTags;
        JVM INSTR monitorexit ;
        return androidtag1;
        class1;
        throw class1;
    }

    private static AndroidTag create(String s)
    {
        Object obj;
        ObjectReference objectreference;
        int i = s.lastIndexOf('.');
        if(i < 0)
            return null;
        obj = s.substring(0, i).trim();
        s = s.substring(i + 1, s.length()).trim();
        if(TextUtils.isEmpty(((CharSequence) (obj))) || TextUtils.isEmpty(s))
            return null;
        obj = ReflectionUtils.tryFindClass(((String) (obj)), AndroidTag.BOOTCLASSLOADER);
        if(obj == null)
            return null;
        objectreference = ReflectionUtils.tryGetStaticObjectField(((Class) (obj)), s, java/lang/Boolean);
        break MISSING_BLOCK_LABEL_76;
        boolean flag;
        if(objectreference == null || objectreference.get() == null)
            flag = false;
        else
            flag = ((Boolean)objectreference.get()).booleanValue();
        return new AndroidTag(((Class) (obj)), s, flag);
    }

    public static AndroidTag get(String s)
    {
        miui/log/AndroidTags;
        JVM INSTR monitorenter ;
        if(!androidTagMap.containsKey(s))
            break MISSING_BLOCK_LABEL_29;
        s = (AndroidTag)androidTagMap.get(s);
        miui/log/AndroidTags;
        JVM INSTR monitorexit ;
        return s;
        AndroidTag androidtag;
        androidtag = create(s);
        androidTagMap.put(s, androidtag);
        miui/log/AndroidTags;
        JVM INSTR monitorexit ;
        return androidtag;
        s;
        throw s;
    }

    public static boolean isOn(String s)
    {
        miui/log/AndroidTags;
        JVM INSTR monitorenter ;
        s = get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_22;
        boolean flag = s.isOn();
        miui/log/AndroidTags;
        JVM INSTR monitorexit ;
        return flag;
        miui/log/AndroidTags;
        JVM INSTR monitorexit ;
        return false;
        s;
        throw s;
    }

    public static void switchOff(String s)
    {
        miui/log/AndroidTags;
        JVM INSTR monitorenter ;
        s = get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_16;
        s.switchOff();
        miui/log/AndroidTags;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public static void switchOn(String s)
    {
        miui/log/AndroidTags;
        JVM INSTR monitorenter ;
        s = get(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_16;
        s.switchOn();
        miui/log/AndroidTags;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    static final String TAG_AM_DEBUG_BROADCAST = "com.android.server.am.ActivityManagerService.DEBUG_BROADCAST";
    private static final HashMap androidTagMap = new HashMap();

}
