// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.*;

// Referenced classes of package com.android.internal.telephony:
//            SmsAuthorizationRequest

public interface ISmsSecurityAgent
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISmsSecurityAgent
    {

        public static ISmsSecurityAgent asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.ISmsSecurityAgent");
            if(iinterface != null && (iinterface instanceof ISmsSecurityAgent))
                return (ISmsSecurityAgent)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.telephony.ISmsSecurityAgent");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.ISmsSecurityAgent");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (SmsAuthorizationRequest)SmsAuthorizationRequest.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onAuthorize(parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.telephony.ISmsSecurityAgent";
        static final int TRANSACTION_onAuthorize = 1;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.ISmsSecurityAgent");
        }
    }

    private static class Stub.Proxy
        implements ISmsSecurityAgent
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.ISmsSecurityAgent";
        }

        public void onAuthorize(SmsAuthorizationRequest smsauthorizationrequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ISmsSecurityAgent");
            if(smsauthorizationrequest == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            smsauthorizationrequest.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            smsauthorizationrequest;
            parcel1.recycle();
            parcel.recycle();
            throw smsauthorizationrequest;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onAuthorize(SmsAuthorizationRequest smsauthorizationrequest)
        throws RemoteException;
}
