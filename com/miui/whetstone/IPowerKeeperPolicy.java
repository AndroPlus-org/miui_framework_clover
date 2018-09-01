// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.content.Intent;
import android.os.*;

// Referenced classes of package com.miui.whetstone:
//            AlarmPolicy, BroadcastPolicy

public interface IPowerKeeperPolicy
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPowerKeeperPolicy
    {

        public static IPowerKeeperPolicy asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.whetstone.IPowerKeeperPolicy");
            if(iinterface != null && (iinterface instanceof IPowerKeeperPolicy))
                return (IPowerKeeperPolicy)iinterface;
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
                parcel1.writeString("com.miui.whetstone.IPowerKeeperPolicy");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                BroadcastPolicy abroadcastpolicy[] = (BroadcastPolicy[])parcel.createTypedArray(BroadcastPolicy.CREATOR);
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setBroadcastPolicy(abroadcastpolicy, flag);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                parcel = getBroadcastPolicies();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                AlarmPolicy aalarmpolicy[] = (AlarmPolicy[])parcel.createTypedArray(AlarmPolicy.CREATOR);
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setAlarmPolicy(aalarmpolicy, flag1);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                parcel = getAlarmPolicies();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                i = parcel.readInt();
                String s = parcel.readString();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                updateWakelockBlockedUid(i, s, flag2);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                offerPowerKeeperIBinder(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                i = parcel.readInt();
                Intent intent;
                boolean flag3;
                if(parcel.readInt() != 0)
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent = null;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setAppPushAlarmProperty(i, intent, flag3);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setAppPushAlarmLeader(i, parcel);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setLeScanFeature(flag4);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                boolean flag5 = isLeScanAllowed(parcel.readInt());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startLeScan(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                stopLeScan(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                i = parcel.readInt();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                setAppBroadcastControlStat(i, flag6);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                boolean flag7 = getAppBroadcastControlStat(parcel.readInt());
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setLeScanParam(parcel);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                setAppBGIdleFeatureEnable(flag8);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.miui.whetstone.IPowerKeeperPolicy");
                setAppBGIdleLevel(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.miui.whetstone.IPowerKeeperPolicy";
        static final int TRANSACTION_getAlarmPolicies = 4;
        static final int TRANSACTION_getAppBroadcastControlStat = 14;
        static final int TRANSACTION_getBroadcastPolicies = 2;
        static final int TRANSACTION_isLeScanAllowed = 10;
        static final int TRANSACTION_offerPowerKeeperIBinder = 6;
        static final int TRANSACTION_setAlarmPolicy = 3;
        static final int TRANSACTION_setAppBGIdleFeatureEnable = 16;
        static final int TRANSACTION_setAppBGIdleLevel = 17;
        static final int TRANSACTION_setAppBroadcastControlStat = 13;
        static final int TRANSACTION_setAppPushAlarmLeader = 8;
        static final int TRANSACTION_setAppPushAlarmProperty = 7;
        static final int TRANSACTION_setBroadcastPolicy = 1;
        static final int TRANSACTION_setLeScanFeature = 9;
        static final int TRANSACTION_setLeScanParam = 15;
        static final int TRANSACTION_startLeScan = 11;
        static final int TRANSACTION_stopLeScan = 12;
        static final int TRANSACTION_updateWakelockBlockedUid = 5;

        public Stub()
        {
            attachInterface(this, "com.miui.whetstone.IPowerKeeperPolicy");
        }
    }

    private static class Stub.Proxy
        implements IPowerKeeperPolicy
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public AlarmPolicy[] getAlarmPolicies()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            AlarmPolicy aalarmpolicy[];
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            aalarmpolicy = (AlarmPolicy[])parcel1.createTypedArray(AlarmPolicy.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return aalarmpolicy;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getAppBroadcastControlStat(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            parcel.writeInt(i);
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

        public BroadcastPolicy[] getBroadcastPolicies()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            BroadcastPolicy abroadcastpolicy[];
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            abroadcastpolicy = (BroadcastPolicy[])parcel1.createTypedArray(BroadcastPolicy.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return abroadcastpolicy;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.whetstone.IPowerKeeperPolicy";
        }

        public boolean isLeScanAllowed(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public void offerPowerKeeperIBinder(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setAlarmPolicy(AlarmPolicy aalarmpolicy[], boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            parcel.writeTypedArray(aalarmpolicy, 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            aalarmpolicy;
            parcel1.recycle();
            parcel.recycle();
            throw aalarmpolicy;
        }

        public void setAppBGIdleFeatureEnable(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
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

        public void setAppBGIdleLevel(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public void setAppBroadcastControlStat(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
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

        public void setAppPushAlarmLeader(int i, Intent intent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            parcel.writeInt(i);
            if(intent == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
        }

        public void setAppPushAlarmProperty(int i, Intent intent, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            parcel.writeInt(i);
            if(intent == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
        }

        public void setBroadcastPolicy(BroadcastPolicy abroadcastpolicy[], boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            parcel.writeTypedArray(abroadcastpolicy, 0);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abroadcastpolicy;
            parcel1.recycle();
            parcel.recycle();
            throw abroadcastpolicy;
        }

        public void setLeScanFeature(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public void setLeScanParam(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public void startLeScan(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void stopLeScan(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void updateWakelockBlockedUid(int i, String s, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IPowerKeeperPolicy");
            parcel.writeInt(i);
            parcel.writeString(s);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract AlarmPolicy[] getAlarmPolicies()
        throws RemoteException;

    public abstract boolean getAppBroadcastControlStat(int i)
        throws RemoteException;

    public abstract BroadcastPolicy[] getBroadcastPolicies()
        throws RemoteException;

    public abstract boolean isLeScanAllowed(int i)
        throws RemoteException;

    public abstract void offerPowerKeeperIBinder(IBinder ibinder)
        throws RemoteException;

    public abstract void setAlarmPolicy(AlarmPolicy aalarmpolicy[], boolean flag)
        throws RemoteException;

    public abstract void setAppBGIdleFeatureEnable(boolean flag)
        throws RemoteException;

    public abstract void setAppBGIdleLevel(int i, int j)
        throws RemoteException;

    public abstract void setAppBroadcastControlStat(int i, boolean flag)
        throws RemoteException;

    public abstract void setAppPushAlarmLeader(int i, Intent intent)
        throws RemoteException;

    public abstract void setAppPushAlarmProperty(int i, Intent intent, boolean flag)
        throws RemoteException;

    public abstract void setBroadcastPolicy(BroadcastPolicy abroadcastpolicy[], boolean flag)
        throws RemoteException;

    public abstract void setLeScanFeature(boolean flag)
        throws RemoteException;

    public abstract void setLeScanParam(Bundle bundle)
        throws RemoteException;

    public abstract void startLeScan(Bundle bundle)
        throws RemoteException;

    public abstract void stopLeScan(Bundle bundle)
        throws RemoteException;

    public abstract void updateWakelockBlockedUid(int i, String s, boolean flag)
        throws RemoteException;
}
