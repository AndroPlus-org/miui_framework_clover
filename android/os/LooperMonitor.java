// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import miui.util.ReflectionUtils;

// Referenced classes of package android.os:
//            ILooperMonitorable, MessageQueue

public class LooperMonitor
    implements ILooperMonitorable
{

    public LooperMonitor()
    {
    }

    public void enableMonitor(boolean flag)
    {
        mEnableMonitor = flag;
        if(!flag)
            break MISSING_BLOCK_LABEL_42;
        MessageQueue messagequeue = (MessageQueue)ReflectionUtils.callMethod(this, "getQueue", android/os/MessageQueue, new Object[0]);
        if(messagequeue == null)
            break MISSING_BLOCK_LABEL_42;
        ReflectionUtils.callMethod(messagequeue, "enableMonitor", java/lang/Void, new Object[0]);
_L2:
        return;
        Exception exception;
        exception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean isMonitorLooper()
    {
        return mEnableMonitor;
    }

    private boolean mEnableMonitor;
}
