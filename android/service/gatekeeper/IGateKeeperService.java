// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.gatekeeper;

import android.os.*;

// Referenced classes of package android.service.gatekeeper:
//            GateKeeperResponse

public interface IGateKeeperService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IGateKeeperService
    {

        public static IGateKeeperService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.gatekeeper.IGateKeeperService");
            if(iinterface != null && (iinterface instanceof IGateKeeperService))
                return (IGateKeeperService)iinterface;
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
                parcel1.writeString("android.service.gatekeeper.IGateKeeperService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                parcel = enroll(parcel.readInt(), parcel.createByteArray(), parcel.createByteArray(), parcel.createByteArray());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                parcel = verify(parcel.readInt(), parcel.createByteArray(), parcel.createByteArray());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                parcel = verifyChallenge(parcel.readInt(), parcel.readLong(), parcel.createByteArray(), parcel.createByteArray());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                long l = getSecureUserId(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                clearSecureUserId(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.gatekeeper.IGateKeeperService");
                reportDeviceSetupComplete();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.gatekeeper.IGateKeeperService";
        static final int TRANSACTION_clearSecureUserId = 5;
        static final int TRANSACTION_enroll = 1;
        static final int TRANSACTION_getSecureUserId = 4;
        static final int TRANSACTION_reportDeviceSetupComplete = 6;
        static final int TRANSACTION_verify = 2;
        static final int TRANSACTION_verifyChallenge = 3;

        public Stub()
        {
            attachInterface(this, "android.service.gatekeeper.IGateKeeperService");
        }
    }

    private static class Stub.Proxy
        implements IGateKeeperService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearSecureUserId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public GateKeeperResponse enroll(int i, byte abyte0[], byte abyte1[], byte abyte2[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            parcel.writeByteArray(abyte2);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            abyte0 = (GateKeeperResponse)GateKeeperResponse.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
_L2:
            abyte0 = null;
            if(true) goto _L4; else goto _L3
_L3:
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.gatekeeper.IGateKeeperService";
        }

        public long getSecureUserId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void reportDeviceSetupComplete()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public GateKeeperResponse verify(int i, byte abyte0[], byte abyte1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            abyte0 = (GateKeeperResponse)GateKeeperResponse.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
_L2:
            abyte0 = null;
            if(true) goto _L4; else goto _L3
_L3:
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public GateKeeperResponse verifyChallenge(int i, long l, byte abyte0[], byte abyte1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.gatekeeper.IGateKeeperService");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            abyte0 = (GateKeeperResponse)GateKeeperResponse.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
_L2:
            abyte0 = null;
            if(true) goto _L4; else goto _L3
_L3:
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void clearSecureUserId(int i)
        throws RemoteException;

    public abstract GateKeeperResponse enroll(int i, byte abyte0[], byte abyte1[], byte abyte2[])
        throws RemoteException;

    public abstract long getSecureUserId(int i)
        throws RemoteException;

    public abstract void reportDeviceSetupComplete()
        throws RemoteException;

    public abstract GateKeeperResponse verify(int i, byte abyte0[], byte abyte1[])
        throws RemoteException;

    public abstract GateKeeperResponse verifyChallenge(int i, long l, byte abyte0[], byte abyte1[])
        throws RemoteException;
}
