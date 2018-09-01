// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.trust;

import android.os.*;
import java.util.List;

// Referenced classes of package android.service.trust:
//            ITrustAgentServiceCallback

public interface ITrustAgentService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITrustAgentService
    {

        public static ITrustAgentService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.trust.ITrustAgentService");
            if(iinterface != null && (iinterface instanceof ITrustAgentService))
                return (ITrustAgentService)iinterface;
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
            long l1;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.service.trust.ITrustAgentService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.trust.ITrustAgentService");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onUnlockAttempt(flag);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.trust.ITrustAgentService");
                onUnlockLockout(parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.trust.ITrustAgentService");
                onTrustTimeout();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.trust.ITrustAgentService");
                onDeviceLocked();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.trust.ITrustAgentService");
                onDeviceUnlocked();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.trust.ITrustAgentService");
                onConfigure(parcel.createTypedArrayList(PersistableBundle.CREATOR), parcel.readStrongBinder());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.trust.ITrustAgentService");
                setCallback(ITrustAgentServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.service.trust.ITrustAgentService");
                parcel1 = parcel.createByteArray();
                long l = parcel.readLong();
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onEscrowTokenAdded(parcel1, l, parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.service.trust.ITrustAgentService");
                onTokenStateReceived(parcel.readLong(), parcel.readInt());
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.service.trust.ITrustAgentService");
                l1 = parcel.readLong();
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            onEscrowTokenRemoved(l1, flag1);
            return true;
        }

        private static final String DESCRIPTOR = "android.service.trust.ITrustAgentService";
        static final int TRANSACTION_onConfigure = 6;
        static final int TRANSACTION_onDeviceLocked = 4;
        static final int TRANSACTION_onDeviceUnlocked = 5;
        static final int TRANSACTION_onEscrowTokenAdded = 8;
        static final int TRANSACTION_onEscrowTokenRemoved = 10;
        static final int TRANSACTION_onTokenStateReceived = 9;
        static final int TRANSACTION_onTrustTimeout = 3;
        static final int TRANSACTION_onUnlockAttempt = 1;
        static final int TRANSACTION_onUnlockLockout = 2;
        static final int TRANSACTION_setCallback = 7;

        public Stub()
        {
            attachInterface(this, "android.service.trust.ITrustAgentService");
        }
    }

    private static class Stub.Proxy
        implements ITrustAgentService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.trust.ITrustAgentService";
        }

        public void onConfigure(List list, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentService");
            parcel.writeTypedList(list);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void onDeviceLocked()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentService");
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onDeviceUnlocked()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentService");
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onEscrowTokenAdded(byte abyte0[], long l, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentService");
            parcel.writeByteArray(abyte0);
            parcel.writeLong(l);
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void onEscrowTokenRemoved(long l, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentService");
            parcel.writeLong(l);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTokenStateReceived(long l, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentService");
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onTrustTimeout()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentService");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUnlockAttempt(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentService");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onUnlockLockout(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentService");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setCallback(ITrustAgentServiceCallback itrustagentservicecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentService");
            if(itrustagentservicecallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = itrustagentservicecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            itrustagentservicecallback;
            parcel.recycle();
            throw itrustagentservicecallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onConfigure(List list, IBinder ibinder)
        throws RemoteException;

    public abstract void onDeviceLocked()
        throws RemoteException;

    public abstract void onDeviceUnlocked()
        throws RemoteException;

    public abstract void onEscrowTokenAdded(byte abyte0[], long l, UserHandle userhandle)
        throws RemoteException;

    public abstract void onEscrowTokenRemoved(long l, boolean flag)
        throws RemoteException;

    public abstract void onTokenStateReceived(long l, int i)
        throws RemoteException;

    public abstract void onTrustTimeout()
        throws RemoteException;

    public abstract void onUnlockAttempt(boolean flag)
        throws RemoteException;

    public abstract void onUnlockLockout(int i)
        throws RemoteException;

    public abstract void setCallback(ITrustAgentServiceCallback itrustagentservicecallback)
        throws RemoteException;
}
