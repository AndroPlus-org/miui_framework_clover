// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.trust;

import android.os.*;
import android.text.TextUtils;

public interface ITrustAgentServiceCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITrustAgentServiceCallback
    {

        public static ITrustAgentServiceCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.trust.ITrustAgentServiceCallback");
            if(iinterface != null && (iinterface instanceof ITrustAgentServiceCallback))
                return (ITrustAgentServiceCallback)iinterface;
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
                parcel1.writeString("android.service.trust.ITrustAgentServiceCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.trust.ITrustAgentServiceCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                grantTrust(parcel1, parcel.readLong(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.trust.ITrustAgentServiceCallback");
                revokeTrust();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.trust.ITrustAgentServiceCallback");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setManagingTrust(flag);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.trust.ITrustAgentServiceCallback");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                onConfigureCompleted(flag1, parcel.readStrongBinder());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.trust.ITrustAgentServiceCallback");
                addEscrowToken(parcel.createByteArray(), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.trust.ITrustAgentServiceCallback");
                isEscrowTokenActive(parcel.readLong(), parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.trust.ITrustAgentServiceCallback");
                removeEscrowToken(parcel.readLong(), parcel.readInt());
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.service.trust.ITrustAgentServiceCallback");
                unlockUserWithToken(parcel.readLong(), parcel.createByteArray(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.trust.ITrustAgentServiceCallback";
        static final int TRANSACTION_addEscrowToken = 5;
        static final int TRANSACTION_grantTrust = 1;
        static final int TRANSACTION_isEscrowTokenActive = 6;
        static final int TRANSACTION_onConfigureCompleted = 4;
        static final int TRANSACTION_removeEscrowToken = 7;
        static final int TRANSACTION_revokeTrust = 2;
        static final int TRANSACTION_setManagingTrust = 3;
        static final int TRANSACTION_unlockUserWithToken = 8;

        public Stub()
        {
            attachInterface(this, "android.service.trust.ITrustAgentServiceCallback");
        }
    }

    private static class Stub.Proxy
        implements ITrustAgentServiceCallback
    {

        public void addEscrowToken(byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentServiceCallback");
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.trust.ITrustAgentServiceCallback";
        }

        public void grantTrust(CharSequence charsequence, long l, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentServiceCallback");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel.recycle();
            throw charsequence;
        }

        public void isEscrowTokenActive(long l, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentServiceCallback");
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onConfigureCompleted(boolean flag, IBinder ibinder)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentServiceCallback");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        public void removeEscrowToken(long l, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentServiceCallback");
            parcel.writeLong(l);
            parcel.writeInt(i);
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void revokeTrust()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentServiceCallback");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setManagingTrust(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentServiceCallback");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void unlockUserWithToken(long l, byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.trust.ITrustAgentServiceCallback");
            parcel.writeLong(l);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addEscrowToken(byte abyte0[], int i)
        throws RemoteException;

    public abstract void grantTrust(CharSequence charsequence, long l, int i)
        throws RemoteException;

    public abstract void isEscrowTokenActive(long l, int i)
        throws RemoteException;

    public abstract void onConfigureCompleted(boolean flag, IBinder ibinder)
        throws RemoteException;

    public abstract void removeEscrowToken(long l, int i)
        throws RemoteException;

    public abstract void revokeTrust()
        throws RemoteException;

    public abstract void setManagingTrust(boolean flag)
        throws RemoteException;

    public abstract void unlockUserWithToken(long l, byte abyte0[], int i)
        throws RemoteException;
}
