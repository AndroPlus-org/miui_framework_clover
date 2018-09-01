// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.fingerprint;

import android.os.*;

public interface IFingerprintServiceLockoutResetCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IFingerprintServiceLockoutResetCallback
    {

        public static IFingerprintServiceLockoutResetCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.fingerprint.IFingerprintServiceLockoutResetCallback");
            if(iinterface != null && (iinterface instanceof IFingerprintServiceLockoutResetCallback))
                return (IFingerprintServiceLockoutResetCallback)iinterface;
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
                parcel1.writeString("android.hardware.fingerprint.IFingerprintServiceLockoutResetCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintServiceLockoutResetCallback");
                onLockoutReset(parcel.readLong(), android.os.IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.fingerprint.IFingerprintServiceLockoutResetCallback";
        static final int TRANSACTION_onLockoutReset = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.fingerprint.IFingerprintServiceLockoutResetCallback");
        }
    }

    private static class Stub.Proxy
        implements IFingerprintServiceLockoutResetCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.fingerprint.IFingerprintServiceLockoutResetCallback";
        }

        public void onLockoutReset(long l, IRemoteCallback iremotecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceLockoutResetCallback");
            parcel.writeLong(l);
            if(iremotecallback == null)
                break MISSING_BLOCK_LABEL_33;
            ibinder = iremotecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iremotecallback;
            parcel.recycle();
            throw iremotecallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onLockoutReset(long l, IRemoteCallback iremotecallback)
        throws RemoteException;
}
