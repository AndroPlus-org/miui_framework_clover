// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.fingerprint;

import android.os.*;
import java.util.List;

// Referenced classes of package android.hardware.fingerprint:
//            IFingerprintClientActiveCallback, IFingerprintServiceLockoutResetCallback, IFingerprintServiceReceiver, Fingerprint

public interface IFingerprintService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IFingerprintService
    {

        public static IFingerprintService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.fingerprint.IFingerprintService");
            if(iinterface != null && (iinterface instanceof IFingerprintService))
                return (IFingerprintService)iinterface;
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
                parcel1.writeString("android.hardware.fingerprint.IFingerprintService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                authenticate(parcel.readStrongBinder(), parcel.readLong(), parcel.readInt(), IFingerprintServiceReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                cancelAuthentication(parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                enroll(parcel.readStrongBinder(), parcel.createByteArray(), parcel.readInt(), IFingerprintServiceReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                cancelEnrollment(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                remove(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt(), IFingerprintServiceReceiver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                rename(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                parcel = getEnrolledFingerprints(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                boolean flag = isHardwareDetected(parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                long l = preEnroll(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                i = postEnroll(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                boolean flag1 = hasEnrolledFingerprints(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                long l1 = getAuthenticatorId(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                resetTimeout(parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                addLockoutResetCallback(IFingerprintServiceLockoutResetCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                setActiveUser(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                enumerate(parcel.readStrongBinder(), parcel.readInt(), IFingerprintServiceReceiver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                boolean flag2 = isClientActive();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                addClientActiveCallback(IFingerprintClientActiveCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.hardware.fingerprint.IFingerprintService");
                removeClientActiveCallback(IFingerprintClientActiveCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.fingerprint.IFingerprintService";
        static final int TRANSACTION_addClientActiveCallback = 18;
        static final int TRANSACTION_addLockoutResetCallback = 14;
        static final int TRANSACTION_authenticate = 1;
        static final int TRANSACTION_cancelAuthentication = 2;
        static final int TRANSACTION_cancelEnrollment = 4;
        static final int TRANSACTION_enroll = 3;
        static final int TRANSACTION_enumerate = 16;
        static final int TRANSACTION_getAuthenticatorId = 12;
        static final int TRANSACTION_getEnrolledFingerprints = 7;
        static final int TRANSACTION_hasEnrolledFingerprints = 11;
        static final int TRANSACTION_isClientActive = 17;
        static final int TRANSACTION_isHardwareDetected = 8;
        static final int TRANSACTION_postEnroll = 10;
        static final int TRANSACTION_preEnroll = 9;
        static final int TRANSACTION_remove = 5;
        static final int TRANSACTION_removeClientActiveCallback = 19;
        static final int TRANSACTION_rename = 6;
        static final int TRANSACTION_resetTimeout = 13;
        static final int TRANSACTION_setActiveUser = 15;

        public Stub()
        {
            attachInterface(this, "android.hardware.fingerprint.IFingerprintService");
        }
    }

    private static class Stub.Proxy
        implements IFingerprintService
    {

        public void addClientActiveCallback(IFingerprintClientActiveCallback ifingerprintclientactivecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            if(ifingerprintclientactivecallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ifingerprintclientactivecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ifingerprintclientactivecallback;
            parcel1.recycle();
            parcel.recycle();
            throw ifingerprintclientactivecallback;
        }

        public void addLockoutResetCallback(IFingerprintServiceLockoutResetCallback ifingerprintservicelockoutresetcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            if(ifingerprintservicelockoutresetcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ifingerprintservicelockoutresetcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ifingerprintservicelockoutresetcallback;
            parcel1.recycle();
            parcel.recycle();
            throw ifingerprintservicelockoutresetcallback;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void authenticate(IBinder ibinder, long l, int i, IFingerprintServiceReceiver ifingerprintservicereceiver, int j, String s)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeLong(l);
            parcel.writeInt(i);
            ibinder = obj;
            if(ifingerprintservicereceiver == null)
                break MISSING_BLOCK_LABEL_55;
            ibinder = ifingerprintservicereceiver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void cancelAuthentication(IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void cancelEnrollment(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void enroll(IBinder ibinder, byte abyte0[], int i, IFingerprintServiceReceiver ifingerprintservicereceiver, int j, String s)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            ibinder = obj;
            if(ifingerprintservicereceiver == null)
                break MISSING_BLOCK_LABEL_54;
            ibinder = ifingerprintservicereceiver.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void enumerate(IBinder ibinder, int i, IFingerprintServiceReceiver ifingerprintservicereceiver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            ibinder = obj;
            if(ifingerprintservicereceiver == null)
                break MISSING_BLOCK_LABEL_46;
            ibinder = ifingerprintservicereceiver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public long getAuthenticatorId(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getEnrolledFingerprints(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(Fingerprint.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.fingerprint.IFingerprintService";
        }

        public boolean hasEnrolledFingerprints(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isClientActive()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isHardwareDetected(long l, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeLong(l);
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int postEnroll(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public long preEnroll(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void remove(IBinder ibinder, int i, int j, int k, IFingerprintServiceReceiver ifingerprintservicereceiver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            ibinder = obj;
            if(ifingerprintservicereceiver == null)
                break MISSING_BLOCK_LABEL_61;
            ibinder = ifingerprintservicereceiver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void removeClientActiveCallback(IFingerprintClientActiveCallback ifingerprintclientactivecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            if(ifingerprintclientactivecallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ifingerprintclientactivecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ifingerprintclientactivecallback;
            parcel1.recycle();
            parcel.recycle();
            throw ifingerprintclientactivecallback;
        }

        public void rename(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void resetTimeout(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeByteArray(abyte0);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void setActiveUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addClientActiveCallback(IFingerprintClientActiveCallback ifingerprintclientactivecallback)
        throws RemoteException;

    public abstract void addLockoutResetCallback(IFingerprintServiceLockoutResetCallback ifingerprintservicelockoutresetcallback)
        throws RemoteException;

    public abstract void authenticate(IBinder ibinder, long l, int i, IFingerprintServiceReceiver ifingerprintservicereceiver, int j, String s)
        throws RemoteException;

    public abstract void cancelAuthentication(IBinder ibinder, String s)
        throws RemoteException;

    public abstract void cancelEnrollment(IBinder ibinder)
        throws RemoteException;

    public abstract void enroll(IBinder ibinder, byte abyte0[], int i, IFingerprintServiceReceiver ifingerprintservicereceiver, int j, String s)
        throws RemoteException;

    public abstract void enumerate(IBinder ibinder, int i, IFingerprintServiceReceiver ifingerprintservicereceiver)
        throws RemoteException;

    public abstract long getAuthenticatorId(String s)
        throws RemoteException;

    public abstract List getEnrolledFingerprints(int i, String s)
        throws RemoteException;

    public abstract boolean hasEnrolledFingerprints(int i, String s)
        throws RemoteException;

    public abstract boolean isClientActive()
        throws RemoteException;

    public abstract boolean isHardwareDetected(long l, String s)
        throws RemoteException;

    public abstract int postEnroll(IBinder ibinder)
        throws RemoteException;

    public abstract long preEnroll(IBinder ibinder)
        throws RemoteException;

    public abstract void remove(IBinder ibinder, int i, int j, int k, IFingerprintServiceReceiver ifingerprintservicereceiver)
        throws RemoteException;

    public abstract void removeClientActiveCallback(IFingerprintClientActiveCallback ifingerprintclientactivecallback)
        throws RemoteException;

    public abstract void rename(int i, int j, String s)
        throws RemoteException;

    public abstract void resetTimeout(byte abyte0[])
        throws RemoteException;

    public abstract void setActiveUser(int i)
        throws RemoteException;
}
