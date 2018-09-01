// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;
import android.telephony.SubscriptionPlan;

// Referenced classes of package android.net:
//            NetworkPolicy, NetworkState, NetworkQuotaInfo, INetworkPolicyListener, 
//            NetworkTemplate

public interface INetworkPolicyManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetworkPolicyManager
    {

        public static INetworkPolicyManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.INetworkPolicyManager");
            if(iinterface != null && (iinterface instanceof INetworkPolicyManager))
                return (INetworkPolicyManager)iinterface;
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
                parcel1.writeString("android.net.INetworkPolicyManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                setUidPolicy(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                addUidPolicy(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                removeUidPolicy(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                i = getUidPolicy(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                parcel = getUidsWithPolicy(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                boolean flag = isUidForeground(parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                registerListener(INetworkPolicyListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                unregisterListener(INetworkPolicyListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                setNetworkPolicies((NetworkPolicy[])parcel.createTypedArray(NetworkPolicy.CREATOR));
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                parcel = getNetworkPolicies(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (NetworkTemplate)NetworkTemplate.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                snoozeLimit(parcel);
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setRestrictBackground(flag1);
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                boolean flag2 = getRestrictBackground();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                String s = parcel.readString();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                onTetheringChanged(s, flag3);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                i = getRestrictBackgroundByCaller();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setDeviceIdleMode(flag4);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                setWifiMeteredOverride(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (NetworkState)NetworkState.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getNetworkQuotaInfo(parcel);
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

            case 19: // '\023'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                parcel = getSubscriptionPlans(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                setSubscriptionPlans(parcel.readInt(), (SubscriptionPlan[])parcel.createTypedArray(SubscriptionPlan.CREATOR), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                factoryReset(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.net.INetworkPolicyManager");
                i = parcel.readInt();
                break;
            }
            boolean flag5;
            if(parcel.readInt() != 0)
                flag5 = true;
            else
                flag5 = false;
            flag5 = isUidNetworkingBlocked(i, flag5);
            parcel1.writeNoException();
            if(flag5)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.net.INetworkPolicyManager";
        static final int TRANSACTION_addUidPolicy = 2;
        static final int TRANSACTION_factoryReset = 21;
        static final int TRANSACTION_getNetworkPolicies = 10;
        static final int TRANSACTION_getNetworkQuotaInfo = 18;
        static final int TRANSACTION_getRestrictBackground = 13;
        static final int TRANSACTION_getRestrictBackgroundByCaller = 15;
        static final int TRANSACTION_getSubscriptionPlans = 19;
        static final int TRANSACTION_getUidPolicy = 4;
        static final int TRANSACTION_getUidsWithPolicy = 5;
        static final int TRANSACTION_isUidForeground = 6;
        static final int TRANSACTION_isUidNetworkingBlocked = 22;
        static final int TRANSACTION_onTetheringChanged = 14;
        static final int TRANSACTION_registerListener = 7;
        static final int TRANSACTION_removeUidPolicy = 3;
        static final int TRANSACTION_setDeviceIdleMode = 16;
        static final int TRANSACTION_setNetworkPolicies = 9;
        static final int TRANSACTION_setRestrictBackground = 12;
        static final int TRANSACTION_setSubscriptionPlans = 20;
        static final int TRANSACTION_setUidPolicy = 1;
        static final int TRANSACTION_setWifiMeteredOverride = 17;
        static final int TRANSACTION_snoozeLimit = 11;
        static final int TRANSACTION_unregisterListener = 8;

        public Stub()
        {
            attachInterface(this, "android.net.INetworkPolicyManager");
        }
    }

    private static class Stub.Proxy
        implements INetworkPolicyManager
    {

        public void addUidPolicy(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void factoryReset(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeString(s);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.INetworkPolicyManager";
        }

        public NetworkPolicy[] getNetworkPolicies(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeString(s);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            s = (NetworkPolicy[])parcel1.createTypedArray(NetworkPolicy.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public NetworkQuotaInfo getNetworkQuotaInfo(NetworkState networkstate)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            if(networkstate == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networkstate.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            networkstate = (NetworkQuotaInfo)NetworkQuotaInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networkstate;
_L2:
            parcel.writeInt(0);
              goto _L3
            networkstate;
            parcel1.recycle();
            parcel.recycle();
            throw networkstate;
            networkstate = null;
              goto _L4
        }

        public boolean getRestrictBackground()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            mRemote.transact(13, parcel, parcel1, 0);
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

        public int getRestrictBackgroundByCaller()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            mRemote.transact(15, parcel, parcel1, 0);
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

        public SubscriptionPlan[] getSubscriptionPlans(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            s = (SubscriptionPlan[])parcel1.createTypedArray(SubscriptionPlan.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getUidPolicy(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
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
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
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

        public boolean isUidNetworkingBlocked(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public void onTetheringChanged(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void registerListener(INetworkPolicyListener inetworkpolicylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            if(inetworkpolicylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = inetworkpolicylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inetworkpolicylistener;
            parcel1.recycle();
            parcel.recycle();
            throw inetworkpolicylistener;
        }

        public void removeUidPolicy(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public void setDeviceIdleMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public void setNetworkPolicies(NetworkPolicy anetworkpolicy[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeTypedArray(anetworkpolicy, 0);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            anetworkpolicy;
            parcel1.recycle();
            parcel.recycle();
            throw anetworkpolicy;
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
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
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

        public void setSubscriptionPlans(int i, SubscriptionPlan asubscriptionplan[], String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeInt(i);
            parcel.writeTypedArray(asubscriptionplan, 0);
            parcel.writeString(s);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            asubscriptionplan;
            parcel1.recycle();
            parcel.recycle();
            throw asubscriptionplan;
        }

        public void setUidPolicy(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
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

        public void setWifiMeteredOverride(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void snoozeLimit(NetworkTemplate networktemplate)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            if(networktemplate == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            networktemplate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            networktemplate;
            parcel1.recycle();
            parcel.recycle();
            throw networktemplate;
        }

        public void unregisterListener(INetworkPolicyListener inetworkpolicylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetworkPolicyManager");
            if(inetworkpolicylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = inetworkpolicylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inetworkpolicylistener;
            parcel1.recycle();
            parcel.recycle();
            throw inetworkpolicylistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addUidPolicy(int i, int j)
        throws RemoteException;

    public abstract void factoryReset(String s)
        throws RemoteException;

    public abstract NetworkPolicy[] getNetworkPolicies(String s)
        throws RemoteException;

    public abstract NetworkQuotaInfo getNetworkQuotaInfo(NetworkState networkstate)
        throws RemoteException;

    public abstract boolean getRestrictBackground()
        throws RemoteException;

    public abstract int getRestrictBackgroundByCaller()
        throws RemoteException;

    public abstract SubscriptionPlan[] getSubscriptionPlans(int i, String s)
        throws RemoteException;

    public abstract int getUidPolicy(int i)
        throws RemoteException;

    public abstract int[] getUidsWithPolicy(int i)
        throws RemoteException;

    public abstract boolean isUidForeground(int i)
        throws RemoteException;

    public abstract boolean isUidNetworkingBlocked(int i, boolean flag)
        throws RemoteException;

    public abstract void onTetheringChanged(String s, boolean flag)
        throws RemoteException;

    public abstract void registerListener(INetworkPolicyListener inetworkpolicylistener)
        throws RemoteException;

    public abstract void removeUidPolicy(int i, int j)
        throws RemoteException;

    public abstract void setDeviceIdleMode(boolean flag)
        throws RemoteException;

    public abstract void setNetworkPolicies(NetworkPolicy anetworkpolicy[])
        throws RemoteException;

    public abstract void setRestrictBackground(boolean flag)
        throws RemoteException;

    public abstract void setSubscriptionPlans(int i, SubscriptionPlan asubscriptionplan[], String s)
        throws RemoteException;

    public abstract void setUidPolicy(int i, int j)
        throws RemoteException;

    public abstract void setWifiMeteredOverride(String s, int i)
        throws RemoteException;

    public abstract void snoozeLimit(NetworkTemplate networktemplate)
        throws RemoteException;

    public abstract void unregisterListener(INetworkPolicyListener inetworkpolicylistener)
        throws RemoteException;
}
