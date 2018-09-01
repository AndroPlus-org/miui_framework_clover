// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.keymaster;

import android.os.*;

// Referenced classes of package android.security.keymaster:
//            KeyAttestationApplicationId

public interface IKeyAttestationApplicationIdProvider
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IKeyAttestationApplicationIdProvider
    {

        public static IKeyAttestationApplicationIdProvider asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.security.keymaster.IKeyAttestationApplicationIdProvider");
            if(iinterface != null && (iinterface instanceof IKeyAttestationApplicationIdProvider))
                return (IKeyAttestationApplicationIdProvider)iinterface;
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
                parcel1.writeString("android.security.keymaster.IKeyAttestationApplicationIdProvider");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.security.keymaster.IKeyAttestationApplicationIdProvider");
                parcel = getKeyAttestationApplicationId(parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            if(parcel != null)
            {
                parcel1.writeInt(1);
                parcel.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        private static final String DESCRIPTOR = "android.security.keymaster.IKeyAttestationApplicationIdProvider";
        static final int TRANSACTION_getKeyAttestationApplicationId = 1;

        public Stub()
        {
            attachInterface(this, "android.security.keymaster.IKeyAttestationApplicationIdProvider");
        }
    }

    private static class Stub.Proxy
        implements IKeyAttestationApplicationIdProvider
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.security.keymaster.IKeyAttestationApplicationIdProvider";
        }

        public KeyAttestationApplicationId getKeyAttestationApplicationId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.security.keymaster.IKeyAttestationApplicationIdProvider");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            KeyAttestationApplicationId keyattestationapplicationid = (KeyAttestationApplicationId)KeyAttestationApplicationId.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return keyattestationapplicationid;
_L2:
            keyattestationapplicationid = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract KeyAttestationApplicationId getKeyAttestationApplicationId(int i)
        throws RemoteException;
}
