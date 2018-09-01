// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone.server;

import android.content.ComponentName;
import android.os.*;
import com.miui.whetstone.IPowerKeeperPolicy;
import com.miui.whetstone.component.ComponentPolicyInfo;
import com.miui.whetstone.strategy.WhetstonePackageInfo;
import java.util.List;

public interface IWhetstoneActivityManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWhetstoneActivityManager
    {

        public static IWhetstoneActivityManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
            if(iinterface != null && (iinterface instanceof IWhetstoneActivityManager))
                return (IWhetstoneActivityManager)iinterface;
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
                parcel1.writeString("com.miui.whetstone.server.IWhetstoneActivityManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                parcel = getPackageNamebyPid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                parcel = getBackgroundAPPS();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                boolean flag = scheduleTrimMemory(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                String s = parcel.readString();
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag1 = scheduleStopService(s, parcel);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                boolean flag2 = distoryActivity(parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                bindWhetstoneService(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                i = getSystemPid();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                boolean flag3 = setPerformanceComponents((ComponentName[])parcel.createTypedArray(ComponentName.CREATOR));
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                long l = getAndroidCachedEmptyProcessMemory();
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                updateApplicationsMemoryThreshold(parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                checkApplicationsMemoryThreshold(parcel.readString(), parcel.readInt(), parcel.readLong());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                boolean flag4 = putUidFrozenState(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                String s1 = parcel.readString();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                updateApplicationByLockedState(s1, flag5);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                String s2 = parcel.readString();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                updateApplicationByLockedStateWithUserId(s2, flag6, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                parcel = getPowerKeeperPolicy();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                i = getPartialWakeLockHoldByUid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                clearDeadAppFromNative();
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                updateUserLockedAppList(parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                updateUserLockedAppListWithUserId(parcel.createStringArrayList(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                boolean flag7 = checkIfPackageIsLocked(parcel.readString());
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                boolean flag8 = checkIfPackageIsLockedWithUserId(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                updateFrameworkCommonConfig(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                boolean flag9 = getProcessReceiverState(parcel.readInt());
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                boolean flag10 = isProcessExecutingServices(parcel.readInt());
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                addAppToServiceControlWhitelist(parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                removeAppFromServiceControlWhitelist(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                i = parcel.readInt();
                boolean flag11;
                if(parcel.readInt() != 0)
                    flag11 = true;
                else
                    flag11 = false;
                flag11 = removeTaskById(i, flag11);
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                String s3 = parcel.readString();
                i = parcel.readInt();
                parcel = parcel.createStringArrayList();
                boolean flag12 = getConnProviderNames(s3, i, parcel);
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                parcel1.writeStringList(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                parcel1 = parcel.createTypedArrayList(WhetstonePackageInfo.CREATOR);
                boolean flag13;
                if(parcel.readInt() != 0)
                    flag13 = true;
                else
                    flag13 = false;
                setWhetstonePackageInfo(parcel1, flag13);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                parcel = updatePackagesPolicies(parcel.createTypedArrayList(ComponentPolicyInfo.CREATOR));
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                removePackagesPolicies(parcel.createIntArray());
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                removePackagesPoliciesWithSetter(parcel.readString());
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                removePackagesPoliciesWithModule(parcel.readString());
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                removePackagePoliciesByPackageInfos(parcel.createStringArrayList());
                return true;

            case 35: // '#'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                parcel = getPolicy(parcel.readInt());
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

            case 36: // '$'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                parcel1 = parcel.readString();
                boolean flag14;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                policyEnableWithSetter(parcel1, flag14);
                return true;

            case 37: // '%'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                parcel1 = parcel.readString();
                boolean flag15;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                policyEnableWithModule(parcel1, flag15);
                return true;

            case 38: // '&'
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                updateCheckList(parcel.createStringArrayList());
                return true;

            case 39: // '\''
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                String s4 = parcel.readString();
                parcel1 = parcel.createStringArrayList();
                boolean flag16;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                updateIntentsWhiteList(s4, parcel1, flag16);
                return true;

            case 40: // '('
                parcel.enforceInterface("com.miui.whetstone.server.IWhetstoneActivityManager");
                parcel1 = parcel.readString();
                break;
            }
            boolean flag17;
            if(parcel.readInt() != 0)
                flag17 = true;
            else
                flag17 = false;
            updateInputMethod(parcel1, flag17);
            return true;
        }

        private static final String DESCRIPTOR = "com.miui.whetstone.server.IWhetstoneActivityManager";
        static final int TRANSACTION_addAppToServiceControlWhitelist = 25;
        static final int TRANSACTION_bindWhetstoneService = 6;
        static final int TRANSACTION_checkApplicationsMemoryThreshold = 11;
        static final int TRANSACTION_checkIfPackageIsLocked = 20;
        static final int TRANSACTION_checkIfPackageIsLockedWithUserId = 21;
        static final int TRANSACTION_clearDeadAppFromNative = 17;
        static final int TRANSACTION_distoryActivity = 5;
        static final int TRANSACTION_getAndroidCachedEmptyProcessMemory = 9;
        static final int TRANSACTION_getBackgroundAPPS = 2;
        static final int TRANSACTION_getConnProviderNames = 28;
        static final int TRANSACTION_getPackageNamebyPid = 1;
        static final int TRANSACTION_getPartialWakeLockHoldByUid = 16;
        static final int TRANSACTION_getPolicy = 35;
        static final int TRANSACTION_getPowerKeeperPolicy = 15;
        static final int TRANSACTION_getProcessReceiverState = 23;
        static final int TRANSACTION_getSystemPid = 7;
        static final int TRANSACTION_isProcessExecutingServices = 24;
        static final int TRANSACTION_policyEnableWithModule = 37;
        static final int TRANSACTION_policyEnableWithSetter = 36;
        static final int TRANSACTION_putUidFrozenState = 12;
        static final int TRANSACTION_removeAppFromServiceControlWhitelist = 26;
        static final int TRANSACTION_removePackagePoliciesByPackageInfos = 34;
        static final int TRANSACTION_removePackagesPolicies = 31;
        static final int TRANSACTION_removePackagesPoliciesWithModule = 33;
        static final int TRANSACTION_removePackagesPoliciesWithSetter = 32;
        static final int TRANSACTION_removeTaskById = 27;
        static final int TRANSACTION_scheduleStopService = 4;
        static final int TRANSACTION_scheduleTrimMemory = 3;
        static final int TRANSACTION_setPerformanceComponents = 8;
        static final int TRANSACTION_setWhetstonePackageInfo = 29;
        static final int TRANSACTION_updateApplicationByLockedState = 13;
        static final int TRANSACTION_updateApplicationByLockedStateWithUserId = 14;
        static final int TRANSACTION_updateApplicationsMemoryThreshold = 10;
        static final int TRANSACTION_updateCheckList = 38;
        static final int TRANSACTION_updateFrameworkCommonConfig = 22;
        static final int TRANSACTION_updateInputMethod = 40;
        static final int TRANSACTION_updateIntentsWhiteList = 39;
        static final int TRANSACTION_updatePackagesPolicies = 30;
        static final int TRANSACTION_updateUserLockedAppList = 18;
        static final int TRANSACTION_updateUserLockedAppListWithUserId = 19;

        public Stub()
        {
            attachInterface(this, "com.miui.whetstone.server.IWhetstoneActivityManager");
        }
    }

    private static class Stub.Proxy
        implements IWhetstoneActivityManager
    {

        public void addAppToServiceControlWhitelist(List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeStringList(list);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void bindWhetstoneService(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
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

        public void checkApplicationsMemoryThreshold(String s, int i, long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeLong(l);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public boolean checkIfPackageIsLocked(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public boolean checkIfPackageIsLockedWithUserId(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
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

        public void clearDeadAppFromNative()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
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

        public boolean distoryActivity(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public long getAndroidCachedEmptyProcessMemory()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public String[] getBackgroundAPPS()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getConnProviderNames(String s, int i, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeStringList(list);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            boolean flag;
            if(parcel1.readInt() != 0)
                flag = true;
            else
                flag = false;
            parcel1.readStringList(list);
            parcel1.recycle();
            parcel.recycle();
            return flag;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.whetstone.server.IWhetstoneActivityManager";
        }

        public String getPackageNamebyPid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getPartialWakeLockHoldByUid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public ComponentPolicyInfo getPolicy(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeInt(i);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ComponentPolicyInfo componentpolicyinfo = (ComponentPolicyInfo)ComponentPolicyInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentpolicyinfo;
_L2:
            componentpolicyinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IPowerKeeperPolicy getPowerKeeperPolicy()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IPowerKeeperPolicy ipowerkeeperpolicy;
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            ipowerkeeperpolicy = com.miui.whetstone.IPowerKeeperPolicy.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ipowerkeeperpolicy;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getProcessReceiverState(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
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

        public int getSystemPid()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public boolean isProcessExecutingServices(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
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

        public void policyEnableWithModule(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(37, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void policyEnableWithSetter(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(36, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public boolean putUidFrozenState(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(12, parcel, parcel1, 0);
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

        public void removeAppFromServiceControlWhitelist(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removePackagePoliciesByPackageInfos(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeStringList(list);
            mRemote.transact(34, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void removePackagesPolicies(int ai[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeIntArray(ai);
            mRemote.transact(31, parcel, null, 1);
            parcel.recycle();
            return;
            ai;
            parcel.recycle();
            throw ai;
        }

        public void removePackagesPoliciesWithModule(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            mRemote.transact(33, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void removePackagesPoliciesWithSetter(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            mRemote.transact(32, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public boolean removeTaskById(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(27, parcel, parcel1, 0);
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

        public boolean scheduleStopService(String s, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            if(componentname == null)
                break MISSING_BLOCK_LABEL_82;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(4, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean scheduleTrimMemory(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public boolean setPerformanceComponents(ComponentName acomponentname[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeTypedArray(acomponentname, 0);
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
            acomponentname;
            parcel1.recycle();
            parcel.recycle();
            throw acomponentname;
        }

        public void setWhetstonePackageInfo(List list, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeTypedList(list);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(29, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void updateApplicationByLockedState(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateApplicationByLockedStateWithUserId(String s, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            if(flag)
                j = 1;
            parcel.writeInt(j);
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

        public void updateApplicationsMemoryThreshold(List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeStringList(list);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void updateCheckList(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeStringList(list);
            mRemote.transact(38, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void updateFrameworkCommonConfig(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateInputMethod(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(40, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void updateIntentsWhiteList(String s, List list, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeString(s);
            parcel.writeStringList(list);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(39, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public int[] updatePackagesPolicies(List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeTypedList(list);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            list = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return list;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void updateUserLockedAppList(List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeStringList(list);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void updateUserLockedAppListWithUserId(List list, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.server.IWhetstoneActivityManager");
            parcel.writeStringList(list);
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addAppToServiceControlWhitelist(List list)
        throws RemoteException;

    public abstract void bindWhetstoneService(IBinder ibinder)
        throws RemoteException;

    public abstract void checkApplicationsMemoryThreshold(String s, int i, long l)
        throws RemoteException;

    public abstract boolean checkIfPackageIsLocked(String s)
        throws RemoteException;

    public abstract boolean checkIfPackageIsLockedWithUserId(String s, int i)
        throws RemoteException;

    public abstract void clearDeadAppFromNative()
        throws RemoteException;

    public abstract boolean distoryActivity(int i)
        throws RemoteException;

    public abstract long getAndroidCachedEmptyProcessMemory()
        throws RemoteException;

    public abstract String[] getBackgroundAPPS()
        throws RemoteException;

    public abstract boolean getConnProviderNames(String s, int i, List list)
        throws RemoteException;

    public abstract String getPackageNamebyPid(int i)
        throws RemoteException;

    public abstract int getPartialWakeLockHoldByUid(int i)
        throws RemoteException;

    public abstract ComponentPolicyInfo getPolicy(int i)
        throws RemoteException;

    public abstract IPowerKeeperPolicy getPowerKeeperPolicy()
        throws RemoteException;

    public abstract boolean getProcessReceiverState(int i)
        throws RemoteException;

    public abstract int getSystemPid()
        throws RemoteException;

    public abstract boolean isProcessExecutingServices(int i)
        throws RemoteException;

    public abstract void policyEnableWithModule(String s, boolean flag)
        throws RemoteException;

    public abstract void policyEnableWithSetter(String s, boolean flag)
        throws RemoteException;

    public abstract boolean putUidFrozenState(int i, int j)
        throws RemoteException;

    public abstract void removeAppFromServiceControlWhitelist(String s)
        throws RemoteException;

    public abstract void removePackagePoliciesByPackageInfos(List list)
        throws RemoteException;

    public abstract void removePackagesPolicies(int ai[])
        throws RemoteException;

    public abstract void removePackagesPoliciesWithModule(String s)
        throws RemoteException;

    public abstract void removePackagesPoliciesWithSetter(String s)
        throws RemoteException;

    public abstract boolean removeTaskById(int i, boolean flag)
        throws RemoteException;

    public abstract boolean scheduleStopService(String s, ComponentName componentname)
        throws RemoteException;

    public abstract boolean scheduleTrimMemory(int i, int j)
        throws RemoteException;

    public abstract boolean setPerformanceComponents(ComponentName acomponentname[])
        throws RemoteException;

    public abstract void setWhetstonePackageInfo(List list, boolean flag)
        throws RemoteException;

    public abstract void updateApplicationByLockedState(String s, boolean flag)
        throws RemoteException;

    public abstract void updateApplicationByLockedStateWithUserId(String s, boolean flag, int i)
        throws RemoteException;

    public abstract void updateApplicationsMemoryThreshold(List list)
        throws RemoteException;

    public abstract void updateCheckList(List list)
        throws RemoteException;

    public abstract void updateFrameworkCommonConfig(String s)
        throws RemoteException;

    public abstract void updateInputMethod(String s, boolean flag)
        throws RemoteException;

    public abstract void updateIntentsWhiteList(String s, List list, boolean flag)
        throws RemoteException;

    public abstract int[] updatePackagesPolicies(List list)
        throws RemoteException;

    public abstract void updateUserLockedAppList(List list)
        throws RemoteException;

    public abstract void updateUserLockedAppListWithUserId(List list, int i)
        throws RemoteException;
}
