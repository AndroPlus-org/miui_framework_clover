// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

// Referenced classes of package android.net:
//            ICaptivePortal

public class CaptivePortal
    implements Parcelable
{

    public CaptivePortal(IBinder ibinder)
    {
        mBinder = ibinder;
    }

    public int describeContents()
    {
        return 0;
    }

    public void ignoreNetwork()
    {
        ICaptivePortal.Stub.asInterface(mBinder).appResponse(1);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void reportCaptivePortalDismissed()
    {
        ICaptivePortal.Stub.asInterface(mBinder).appResponse(0);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void useNetwork()
    {
        ICaptivePortal.Stub.asInterface(mBinder).appResponse(2);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongBinder(mBinder);
    }

    public static final int APP_RETURN_DISMISSED = 0;
    public static final int APP_RETURN_UNWANTED = 1;
    public static final int APP_RETURN_WANTED_AS_IS = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public CaptivePortal createFromParcel(Parcel parcel)
        {
            return new CaptivePortal(parcel.readStrongBinder());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public CaptivePortal[] newArray(int i)
        {
            return new CaptivePortal[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final IBinder mBinder;

}
