// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.os.Message;

// Referenced classes of package com.android.internal.util:
//            IState

public class State
    implements IState
{

    protected State()
    {
    }

    public void enter()
    {
    }

    public void exit()
    {
    }

    public String getName()
    {
        String s = getClass().getName();
        return s.substring(s.lastIndexOf('$') + 1);
    }

    public boolean processMessage(Message message)
    {
        return false;
    }
}
