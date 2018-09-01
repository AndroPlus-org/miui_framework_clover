// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.policy;

import android.content.Context;
import android.view.KeyEvent;
import android.view.Window;

// Referenced classes of package com.android.internal.policy:
//            PhoneWindow

public class MiuiPhoneWindow extends PhoneWindow
{

    public MiuiPhoneWindow(Context context)
    {
        super(context);
    }

    public MiuiPhoneWindow(Context context, Window window, android.view.ViewRootImpl.ActivityConfigCallback activityconfigcallback)
    {
        super(context, window, activityconfigcallback);
    }

    protected boolean onKeyDown(int i, int j, KeyEvent keyevent)
    {
        int k;
        KeyEvent keyevent1;
        k = j;
        keyevent1 = keyevent;
        if(j == 187)
        {
            keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 82, keyevent.getRepeatCount(), keyevent.getMetaState(), keyevent.getDeviceId(), keyevent.getScanCode(), keyevent.getFlags());
            k = keyevent1.getKeyCode();
        }
        k;
        JVM INSTR tableswitch 82 82: default 84
    //                   82 94;
           goto _L1 _L2
_L1:
        return super.onKeyDown(i, k, keyevent1);
_L2:
        if(mMenuDownCount == 0)
            keyevent1 = new KeyEvent(0, 82);
        mMenuDownCount = mMenuDownCount + 1;
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected boolean onKeyUp(int i, int j, KeyEvent keyevent)
    {
        int k;
        KeyEvent keyevent1;
        k = j;
        keyevent1 = keyevent;
        if(j == 187)
        {
            keyevent1 = new KeyEvent(keyevent.getDownTime(), keyevent.getEventTime(), keyevent.getAction(), 82, keyevent.getRepeatCount(), keyevent.getMetaState(), keyevent.getDeviceId(), keyevent.getScanCode(), keyevent.getFlags());
            k = keyevent1.getKeyCode();
        }
        k;
        JVM INSTR tableswitch 82 82: default 84
    //                   82 94;
           goto _L1 _L2
_L1:
        return super.onKeyUp(i, k, keyevent1);
_L2:
        if(mMenuDownCount == 1)
            keyevent1 = new KeyEvent(1, 82);
        mMenuDownCount = 0;
        if(true) goto _L1; else goto _L3
_L3:
    }

    private int mMenuDownCount;
}
