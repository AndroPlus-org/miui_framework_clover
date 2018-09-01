// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.IInterface;
import android.os.RemoteException;

// Referenced classes of package com.miui.whetstone:
//            WhetstoneResult

public interface IWhetstoneResult
    extends IInterface
{

    public abstract void onResult(WhetstoneResult whetstoneresult)
        throws RemoteException;

    public static final int TRACSACTION_onResult = 1;
    public static final String descriptor = "com.miui.whetstone.IWhetstoneResult";
}
