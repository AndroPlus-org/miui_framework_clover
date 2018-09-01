// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.*;
import android.telephony.euicc.EuiccInfo;

public interface IGetEuiccInfoCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGetEuiccInfoCallback
    {

        public static IGetEuiccInfoCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.euicc.IGetEuiccInfoCallback");
            if(iinterface != null && (iinterface instanceof IGetEuiccInfoCallback))
                return (IGetEuiccInfoCallback)iinterface;
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
                parcel1.writeString("android.service.euicc.IGetEuiccInfoCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.euicc.IGetEuiccInfoCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (EuiccInfo)EuiccInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onSuccess(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.service.euicc.IGetEuiccInfoCallback";
        static final int TRANSACTION_onSuccess = 1;

        public Stub()
        {
            attachInterface(this, "android.service.euicc.IGetEuiccInfoCallback");
        }
    }

    private static class Stub.Proxy
        implements IGetEuiccInfoCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.euicc.IGetEuiccInfoCallback";
        }

        public void onSuccess(EuiccInfo euiccinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IGetEuiccInfoCallback");
            if(euiccinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            euiccinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            euiccinfo;
            parcel.recycle();
            throw euiccinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onSuccess(EuiccInfo euiccinfo)
        throws RemoteException;
}
