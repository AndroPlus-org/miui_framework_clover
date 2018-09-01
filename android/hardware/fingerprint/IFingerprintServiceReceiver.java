// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.fingerprint;

import android.os.*;

// Referenced classes of package android.hardware.fingerprint:
//            Fingerprint

public interface IFingerprintServiceReceiver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IFingerprintServiceReceiver
    {

        public static IFingerprintServiceReceiver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
            if(iinterface != null && (iinterface instanceof IFingerprintServiceReceiver))
                return (IFingerprintServiceReceiver)iinterface;
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
                parcel1.writeString("android.hardware.fingerprint.IFingerprintServiceReceiver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
                onEnrollResult(parcel.readLong(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
                onAcquired(parcel.readLong(), parcel.readInt(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
                long l = parcel.readLong();
                if(parcel.readInt() != 0)
                    parcel1 = (Fingerprint)Fingerprint.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onAuthenticationSucceeded(l, parcel1, parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
                onAuthenticationFailed(parcel.readLong());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
                onError(parcel.readLong(), parcel.readInt(), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
                onRemoved(parcel.readLong(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintServiceReceiver");
                onEnumerated(parcel.readLong(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.fingerprint.IFingerprintServiceReceiver";
        static final int TRANSACTION_onAcquired = 2;
        static final int TRANSACTION_onAuthenticationFailed = 4;
        static final int TRANSACTION_onAuthenticationSucceeded = 3;
        static final int TRANSACTION_onEnrollResult = 1;
        static final int TRANSACTION_onEnumerated = 7;
        static final int TRANSACTION_onError = 5;
        static final int TRANSACTION_onRemoved = 6;

        public Stub()
        {
            attachInterface(this, "android.hardware.fingerprint.IFingerprintServiceReceiver");
        }
    }

    private static class Stub.Proxy
        implements IFingerprintServiceReceiver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.fingerprint.IFingerprintServiceReceiver";
        }

        public void onAcquired(long l, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onAuthenticationFailed(long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
            parcel.writeLong(l);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onAuthenticationSucceeded(long l, Fingerprint fingerprint, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
            parcel.writeLong(l);
            if(fingerprint == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            fingerprint.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            fingerprint;
            parcel.recycle();
            throw fingerprint;
        }

        public void onEnrollResult(long l, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onEnumerated(long l, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onError(long l, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onRemoved(long l, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintServiceReceiver");
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onAcquired(long l, int i, int j)
        throws RemoteException;

    public abstract void onAuthenticationFailed(long l)
        throws RemoteException;

    public abstract void onAuthenticationSucceeded(long l, Fingerprint fingerprint, int i)
        throws RemoteException;

    public abstract void onEnrollResult(long l, int i, int j, int k)
        throws RemoteException;

    public abstract void onEnumerated(long l, int i, int j, int k)
        throws RemoteException;

    public abstract void onError(long l, int i, int j)
        throws RemoteException;

    public abstract void onRemoved(long l, int i, int j, int k)
        throws RemoteException;
}
