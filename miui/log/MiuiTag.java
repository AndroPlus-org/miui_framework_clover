// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.log;


// Referenced classes of package miui.log:
//            ILogTag

public final class MiuiTag
    implements ILogTag
{

    MiuiTag(int i, String s, boolean flag)
    {
        id = i;
        name = s;
        defaultOn = flag;
        isTagOn = flag;
        onNumber = 0;
    }

    public boolean isOn()
    {
        return isTagOn;
    }

    public void switchOff()
    {
        this;
        JVM INSTR monitorenter ;
        onNumber = onNumber - 1;
        if(onNumber != 0) goto _L2; else goto _L1
_L1:
        isTagOn = defaultOn;
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(onNumber != -1) goto _L4; else goto _L3
_L3:
        isTagOn = false;
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public void switchOn()
    {
        this;
        JVM INSTR monitorenter ;
        onNumber = onNumber + 1;
        if(onNumber != 0) goto _L2; else goto _L1
_L1:
        isTagOn = defaultOn;
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(onNumber != 1) goto _L4; else goto _L3
_L3:
        isTagOn = true;
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean defaultOn;
    public final int id;
    private boolean isTagOn;
    public final String name;
    private int onNumber;
}
