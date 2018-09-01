// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.location;

import android.os.*;

// Referenced classes of package android.location:
//            LocationPolicy, ILocationPolicyListener, Location

public interface ILocationPolicyManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILocationPolicyManager
    {

        public static ILocationPolicyManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.location.ILocationPolicyManager");
            if(iinterface != null && (iinterface instanceof ILocationPolicyManager))
                return (ILocationPolicyManager)iinterface;
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
                parcel1.writeString("android.location.ILocationPolicyManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                setUidPolicy(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                i = getUidPolicy(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                parcel = getUidsWithPolicy(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                setUidNavigationStart(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                setUidNavigationStop(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                boolean flag = checkUidNavigationScreenLock(parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                boolean flag1 = isUidForeground(parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                boolean flag2 = checkUidLocationOp(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                registerListener(ILocationPolicyListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                unregisterListener(ILocationPolicyListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                setLocationPolicies((LocationPolicy[])parcel.createTypedArray(LocationPolicy.CREATOR));
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                parcel = getLocationPolicies();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setRestrictBackground(flag3);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                boolean flag4 = getRestrictBackground();
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                setFakeGpsFeatureOnState(flag5);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.location.ILocationPolicyManager");
                break;
            }
            boolean flag6;
            if(parcel.readInt() != 0)
                flag6 = true;
            else
                flag6 = false;
            if(parcel.readInt() != 0)
                parcel = (Location)Location.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            setPhoneStationary(flag6, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.location.ILocationPolicyManager";
        static final int TRANSACTION_checkUidLocationOp = 8;
        static final int TRANSACTION_checkUidNavigationScreenLock = 6;
        static final int TRANSACTION_getLocationPolicies = 12;
        static final int TRANSACTION_getRestrictBackground = 14;
        static final int TRANSACTION_getUidPolicy = 2;
        static final int TRANSACTION_getUidsWithPolicy = 3;
        static final int TRANSACTION_isUidForeground = 7;
        static final int TRANSACTION_registerListener = 9;
        static final int TRANSACTION_setFakeGpsFeatureOnState = 15;
        static final int TRANSACTION_setLocationPolicies = 11;
        static final int TRANSACTION_setPhoneStationary = 16;
        static final int TRANSACTION_setRestrictBackground = 13;
        static final int TRANSACTION_setUidNavigationStart = 4;
        static final int TRANSACTION_setUidNavigationStop = 5;
        static final int TRANSACTION_setUidPolicy = 1;
        static final int TRANSACTION_unregisterListener = 10;

        public Stub()
        {
            attachInterface(this, "android.location.ILocationPolicyManager");
        }
    }

    private static class Stub.Proxy
        implements ILocationPolicyManager
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean checkUidLocationOp(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean checkUidNavigationScreenLock(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "android.location.ILocationPolicyManager";
        }

        public LocationPolicy[] getLocationPolicies()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            LocationPolicy alocationpolicy[];
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            alocationpolicy = (LocationPolicy[])parcel1.createTypedArray(LocationPolicy.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return alocationpolicy;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getRestrictBackground()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            mRemote.transact(14, parcel, parcel1, 0);
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

        public int getUidPolicy(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int[] getUidsWithPolicy(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isUidForeground(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void registerListener(ILocationPolicyListener ilocationpolicylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            if(ilocationpolicylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ilocationpolicylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ilocationpolicylistener;
            parcel1.recycle();
            parcel.recycle();
            throw ilocationpolicylistener;
        }

        public void setFakeGpsFeatureOnState(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setLocationPolicies(LocationPolicy alocationpolicy[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            parcel.writeTypedArray(alocationpolicy, 0);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            alocationpolicy;
            parcel1.recycle();
            parcel.recycle();
            throw alocationpolicy;
        }

        public void setPhoneStationary(boolean flag, Location location)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(location == null)
                break MISSING_BLOCK_LABEL_68;
            parcel.writeInt(1);
            location.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(16, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            location;
            parcel.recycle();
            throw location;
        }

        public void setRestrictBackground(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public void setUidNavigationStart(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public void setUidNavigationStop(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
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

        public void setUidPolicy(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void unregisterListener(ILocationPolicyListener ilocationpolicylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.location.ILocationPolicyManager");
            if(ilocationpolicylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ilocationpolicylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ilocationpolicylistener;
            parcel1.recycle();
            parcel.recycle();
            throw ilocationpolicylistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean checkUidLocationOp(int i, int j)
        throws RemoteException;

    public abstract boolean checkUidNavigationScreenLock(int i)
        throws RemoteException;

    public abstract LocationPolicy[] getLocationPolicies()
        throws RemoteException;

    public abstract boolean getRestrictBackground()
        throws RemoteException;

    public abstract int getUidPolicy(int i)
        throws RemoteException;

    public abstract int[] getUidsWithPolicy(int i)
        throws RemoteException;

    public abstract boolean isUidForeground(int i)
        throws RemoteException;

    public abstract void registerListener(ILocationPolicyListener ilocationpolicylistener)
        throws RemoteException;

    public abstract void setFakeGpsFeatureOnState(boolean flag)
        throws RemoteException;

    public abstract void setLocationPolicies(LocationPolicy alocationpolicy[])
        throws RemoteException;

    public abstract void setPhoneStationary(boolean flag, Location location)
        throws RemoteException;

    public abstract void setRestrictBackground(boolean flag)
        throws RemoteException;

    public abstract void setUidNavigationStart(int i)
        throws RemoteException;

    public abstract void setUidNavigationStop(int i)
        throws RemoteException;

    public abstract void setUidPolicy(int i, int j)
        throws RemoteException;

    public abstract void unregisterListener(ILocationPolicyListener ilocationpolicylistener)
        throws RemoteException;
}
