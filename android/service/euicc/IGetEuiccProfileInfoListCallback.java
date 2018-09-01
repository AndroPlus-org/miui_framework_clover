// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.euicc;

import android.os.*;

// Referenced classes of package android.service.euicc:
//            GetEuiccProfileInfoListResult

public interface IGetEuiccProfileInfoListCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGetEuiccProfileInfoListCallback
    {

        public static IGetEuiccProfileInfoListCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.euicc.IGetEuiccProfileInfoListCallback");
            if(iinterface != null && (iinterface instanceof IGetEuiccProfileInfoListCallback))
                return (IGetEuiccProfileInfoListCallback)iinterface;
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
                parcel1.writeString("android.service.euicc.IGetEuiccProfileInfoListCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.euicc.IGetEuiccProfileInfoListCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (GetEuiccProfileInfoListResult)GetEuiccProfileInfoListResult.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onComplete(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.service.euicc.IGetEuiccProfileInfoListCallback";
        static final int TRANSACTION_onComplete = 1;

        public Stub()
        {
            attachInterface(this, "android.service.euicc.IGetEuiccProfileInfoListCallback");
        }
    }

    private static class Stub.Proxy
        implements IGetEuiccProfileInfoListCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.euicc.IGetEuiccProfileInfoListCallback";
        }

        public void onComplete(GetEuiccProfileInfoListResult geteuiccprofileinfolistresult)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.euicc.IGetEuiccProfileInfoListCallback");
            if(geteuiccprofileinfolistresult == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            geteuiccprofileinfolistresult.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            geteuiccprofileinfolistresult;
            parcel.recycle();
            throw geteuiccprofileinfolistresult;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onComplete(GetEuiccProfileInfoListResult geteuiccprofileinfolistresult)
        throws RemoteException;
}
