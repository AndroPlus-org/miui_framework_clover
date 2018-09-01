// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;


// Referenced classes of package miui.log:
//            ILogTag, MiuiTags, AndroidTags

public final class TagGroup
    implements ILogTag
{

    TagGroup(String s, String as[])
    {
        this(s, as, null);
    }

    TagGroup(String s, String as[], String as1[])
    {
        name = s;
        s = as;
        if(as == null)
            s = new String[0];
        miuiTags = s;
        s = as1;
        if(as1 == null)
            s = new String[0];
        androidTags = s;
        isGroupOn = false;
        onNumber = 0;
    }

    public boolean isOn()
    {
        return isGroupOn;
    }

    public void switchOff()
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        onNumber = onNumber - 1;
        int i = 0;
        if(onNumber != 0) goto _L2; else goto _L1
_L1:
        isGroupOn = false;
        i = 1;
_L9:
        if(i == 0) goto _L4; else goto _L3
_L3:
        String as[];
        int j;
        as = miuiTags;
        j = as.length;
        i = 0;
_L7:
        if(i >= j) goto _L6; else goto _L5
_L5:
        MiuiTags.switchOff(as[i]);
        i++;
          goto _L7
_L2:
        if(onNumber != -1) goto _L9; else goto _L8
_L8:
        isGroupOn = false;
        i = 1;
          goto _L9
_L6:
        as = androidTags;
        j = as.length;
        i = ((flag) ? 1 : 0);
_L10:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        AndroidTags.switchOff(as[i]);
        i++;
        if(true) goto _L10; else goto _L4
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void switchOn()
    {
        boolean flag = false;
        this;
        JVM INSTR monitorenter ;
        onNumber = onNumber + 1;
        int i = 0;
        if(onNumber != 0) goto _L2; else goto _L1
_L1:
        isGroupOn = false;
        i = 1;
_L9:
        if(i == 0) goto _L4; else goto _L3
_L3:
        String as[];
        int j;
        as = androidTags;
        j = as.length;
        i = 0;
_L7:
        if(i >= j) goto _L6; else goto _L5
_L5:
        AndroidTags.switchOn(as[i]);
        i++;
          goto _L7
_L2:
        if(onNumber != 1) goto _L9; else goto _L8
_L8:
        isGroupOn = true;
        i = 1;
          goto _L9
_L6:
        as = miuiTags;
        j = as.length;
        i = ((flag) ? 1 : 0);
_L10:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        MiuiTags.switchOn(as[i]);
        i++;
        if(true) goto _L10; else goto _L4
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private final String androidTags[];
    private boolean isGroupOn;
    private final String miuiTags[];
    public final String name;
    private int onNumber;
}
