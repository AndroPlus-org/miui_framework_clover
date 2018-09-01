// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.os.*;

public interface IRttManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRttManager
    {

        public static IRttManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.wifi.IRttManager");
            if(iinterface != null && (iinterface instanceof IRttManager))
                return (IRttManager)iinterface;
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
                parcel1.writeString("android.net.wifi.IRttManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.wifi.IRttManager");
                Object obj = parcel.readStrongBinder();
                i = parcel.readInt();
                if(i < 0)
                    parcel = null;
                else
                    parcel = new int[i];
                obj = getMessenger(((IBinder) (obj)), parcel);
                parcel1.writeNoException();
                if(obj != null)
                {
                    parcel1.writeInt(1);
                    ((Messenger) (obj)).writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                parcel1.writeIntArray(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.wifi.IRttManager");
                parcel = getRttCapabilities();
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

        private static final String DESCRIPTOR = "android.net.wifi.IRttManager";
        static final int TRANSACTION_getMessenger = 1;
        static final int TRANSACTION_getRttCapabilities = 2;

        public Stub()
        {
            attachInterface(this, "android.net.wifi.IRttManager");
        }
    }

    private static class Stub.Proxy
        implements IRttManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.wifi.IRttManager";
        }

        public Messenger getMessenger(IBinder ibinder, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IRttManager");
            parcel.writeStrongBinder(ibinder);
            if(ai != null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(-1);
_L3:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_109;
            ibinder = (Messenger)Messenger.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.readIntArray(ai);
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            parcel.writeInt(ai.length);
              goto _L3
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
            ibinder = null;
              goto _L4
        }

        public RttManager.RttCapabilities getRttCapabilities()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IRttManager");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            RttManager.RttCapabilities rttcapabilities = (RttManager.RttCapabilities)RttManager.RttCapabilities.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return rttcapabilities;
_L2:
            rttcapabilities = null;
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


    public abstract Messenger getMessenger(IBinder ibinder, int ai[])
        throws RemoteException;

    public abstract RttManager.RttCapabilities getRttCapabilities()
        throws RemoteException;
}
