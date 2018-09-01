// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.*;

// Referenced classes of package com.miui.whetstone:
//            IWhetstoneResult, WhetstoneResultProxy, WhetstoneResult

public class WhetstoneResultBinder extends Binder
    implements IWhetstoneResult
{

    public WhetstoneResultBinder()
    {
    }

    public static IWhetstoneResult asInterface(IBinder ibinder)
    {
        if(ibinder == null)
            return null;
        IWhetstoneResult iwhetstoneresult = (IWhetstoneResult)ibinder.queryLocalInterface("com.miui.whetstone.IWhetstoneResult");
        if(iwhetstoneresult != null)
            return iwhetstoneresult;
        else
            return new WhetstoneResultProxy(ibinder);
    }

    public IBinder asBinder()
    {
        return this;
    }

    public void onResult(WhetstoneResult whetstoneresult)
    {
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
        throws RemoteException
    {
        switch(i)
        {
        default:
            return super.onTransact(i, parcel, parcel1, j);

        case 1: // '\001'
            parcel.enforceInterface("com.miui.whetstone.IWhetstoneResult");
            break;
        }
        onResult((WhetstoneResult)WhetstoneResult.CREATOR.createFromParcel(parcel));
        parcel1.writeNoException();
        return true;
    }
}
