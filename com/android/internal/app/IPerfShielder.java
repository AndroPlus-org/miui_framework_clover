// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.pm.PackageInfo;
import android.os.*;
import java.util.List;

// Referenced classes of package com.android.internal.app:
//            MiuiServicePriority

public interface IPerfShielder
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPerfShielder
    {

        public static IPerfShielder asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IPerfShielder");
            if(iinterface != null && (iinterface instanceof IPerfShielder))
                return (IPerfShielder)iinterface;
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
                parcel1.writeString("com.android.internal.app.IPerfShielder");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                reportPerceptibleJank(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readLong(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                parcel1 = parcel.readString();
                String s = parcel.readString();
                long l = parcel.readLong();
                long l1 = parcel.readLong();
                boolean flag;
                boolean flag10;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                addActivityLaunchTime(parcel1, s, l, l1, flag, flag10);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                setSchedFgPid(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                killUnusedApp(parcel.readInt(), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                parcel = getPackageNameByPid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                setForkedProcessGroup(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                parcel = getAllRunningProcessMemInfos();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                parcel = updateProcessFullMemInfoByPids(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                parcel = updateProcessPartialMemInfoByPids(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                setServicePriority(parcel.createTypedArrayList(MiuiServicePriority.CREATOR));
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                setServicePriorityWithNoProc(parcel.createTypedArrayList(MiuiServicePriority.CREATOR), parcel.readLong());
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel1 = (MiuiServicePriority)MiuiServicePriority.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                removeServicePriority(parcel1, flag1);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                closeCheckPriority();
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                setMiuiContentProviderControl(flag2);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setMiuiBroadcastDispatchEnable(flag3);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                addTimeConsumingIntent(parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                removeTimeConsumingIntent(parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                clearTimeConsumingIntent();
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                i = getMemoryTrimLevel();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                String s1 = parcel.readString();
                String s2 = parcel.readString();
                String s3 = parcel.readString();
                boolean flag4;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag4 = insertRedirectRule(s1, s2, s3, parcel);
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                boolean flag5 = deleteRedirectRule(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                boolean flag6;
                if(parcel.readInt() != 0)
                    parcel = (PackageInfo)PackageInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag6 = insertPackageInfo(parcel);
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                boolean flag7 = deletePackageInfo(parcel.readString());
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                long l2 = getFreeMemory();
                parcel1.writeNoException();
                parcel1.writeLong(l2);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                reportAnr(parcel.readInt(), parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readString());
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                boolean flag8 = addCallingPkgHookRule(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                boolean flag9 = removeCallingPkgHookRule(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.app.IPerfShielder");
                parcel = getPerfEventSocketFd();
                parcel1.writeNoException();
                break;
            }
            if(parcel != null)
            {
                parcel1.writeInt(1);
                parcel.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IPerfShielder";
        static final int TRANSACTION_addActivityLaunchTime = 2;
        static final int TRANSACTION_addCallingPkgHookRule = 26;
        static final int TRANSACTION_addTimeConsumingIntent = 16;
        static final int TRANSACTION_clearTimeConsumingIntent = 18;
        static final int TRANSACTION_closeCheckPriority = 13;
        static final int TRANSACTION_deletePackageInfo = 23;
        static final int TRANSACTION_deleteRedirectRule = 21;
        static final int TRANSACTION_getAllRunningProcessMemInfos = 7;
        static final int TRANSACTION_getFreeMemory = 24;
        static final int TRANSACTION_getMemoryTrimLevel = 19;
        static final int TRANSACTION_getPackageNameByPid = 5;
        static final int TRANSACTION_getPerfEventSocketFd = 28;
        static final int TRANSACTION_insertPackageInfo = 22;
        static final int TRANSACTION_insertRedirectRule = 20;
        static final int TRANSACTION_killUnusedApp = 4;
        static final int TRANSACTION_removeCallingPkgHookRule = 27;
        static final int TRANSACTION_removeServicePriority = 12;
        static final int TRANSACTION_removeTimeConsumingIntent = 17;
        static final int TRANSACTION_reportAnr = 25;
        static final int TRANSACTION_reportPerceptibleJank = 1;
        static final int TRANSACTION_setForkedProcessGroup = 6;
        static final int TRANSACTION_setMiuiBroadcastDispatchEnable = 15;
        static final int TRANSACTION_setMiuiContentProviderControl = 14;
        static final int TRANSACTION_setSchedFgPid = 3;
        static final int TRANSACTION_setServicePriority = 10;
        static final int TRANSACTION_setServicePriorityWithNoProc = 11;
        static final int TRANSACTION_updateProcessFullMemInfoByPids = 8;
        static final int TRANSACTION_updateProcessPartialMemInfoByPids = 9;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IPerfShielder");
        }
    }

    private static class Stub.Proxy
        implements IPerfShielder
    {

        public void addActivityLaunchTime(String s, String s1, long l, long l1, boolean flag, 
                boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeLong(l);
            parcel.writeLong(l1);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public boolean addCallingPkgHookRule(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(26, parcel, parcel1, 0);
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

        public void addTimeConsumingIntent(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeStringArray(as);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearTimeConsumingIntent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            mRemote.transact(18, parcel, parcel1, 0);
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

        public void closeCheckPriority()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
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

        public boolean deletePackageInfo(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean deleteRedirectRule(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeString(s);
            parcel.writeString(s1);
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

        public List getAllRunningProcessMemInfos()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(Bundle.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public long getFreeMemory()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            mRemote.transact(24, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IPerfShielder";
        }

        public int getMemoryTrimLevel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            mRemote.transact(19, parcel, parcel1, 0);
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

        public String getPackageNameByPid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public ParcelFileDescriptor getPerfEventSocketFd()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParcelFileDescriptor parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parcelfiledescriptor;
_L2:
            parcelfiledescriptor = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean insertPackageInfo(PackageInfo packageinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            if(packageinfo == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            packageinfo.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(22, parcel, parcel1, 0);
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
            packageinfo;
            parcel1.recycle();
            parcel.recycle();
            throw packageinfo;
        }

        public boolean insertRedirectRule(String s, String s1, String s2, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_104;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void killUnusedApp(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public boolean removeCallingPkgHookRule(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(27, parcel, parcel1, 0);
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

        public void removeServicePriority(MiuiServicePriority miuiservicepriority, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            if(miuiservicepriority == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            miuiservicepriority.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(12, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            miuiservicepriority;
            parcel.recycle();
            throw miuiservicepriority;
        }

        public void removeTimeConsumingIntent(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeStringArray(as);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void reportAnr(int i, String s, long l, long l1, String s1)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeLong(l1);
            parcel.writeString(s1);
            mRemote.transact(25, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void reportPerceptibleJank(int i, int j, String s, long l, long l1, 
                long l2, int k)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeLong(l1);
            parcel.writeLong(l2);
            parcel.writeInt(k);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void setForkedProcessGroup(int i, int j, int k, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
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

        public void setMiuiBroadcastDispatchEnable(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
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

        public void setMiuiContentProviderControl(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setSchedFgPid(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setServicePriority(List list)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeTypedList(list);
            mRemote.transact(10, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public void setServicePriorityWithNoProc(List list, long l)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeTypedList(list);
            parcel.writeLong(l);
            mRemote.transact(11, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public List updateProcessFullMemInfoByPids(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeIntArray(ai);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createTypedArrayList(Bundle.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public List updateProcessPartialMemInfoByPids(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IPerfShielder");
            parcel.writeIntArray(ai);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createTypedArrayList(Bundle.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addActivityLaunchTime(String s, String s1, long l, long l1, boolean flag, 
            boolean flag1)
        throws RemoteException;

    public abstract boolean addCallingPkgHookRule(String s, String s1, String s2)
        throws RemoteException;

    public abstract void addTimeConsumingIntent(String as[])
        throws RemoteException;

    public abstract void clearTimeConsumingIntent()
        throws RemoteException;

    public abstract void closeCheckPriority()
        throws RemoteException;

    public abstract boolean deletePackageInfo(String s)
        throws RemoteException;

    public abstract boolean deleteRedirectRule(String s, String s1)
        throws RemoteException;

    public abstract List getAllRunningProcessMemInfos()
        throws RemoteException;

    public abstract long getFreeMemory()
        throws RemoteException;

    public abstract int getMemoryTrimLevel()
        throws RemoteException;

    public abstract String getPackageNameByPid(int i)
        throws RemoteException;

    public abstract ParcelFileDescriptor getPerfEventSocketFd()
        throws RemoteException;

    public abstract boolean insertPackageInfo(PackageInfo packageinfo)
        throws RemoteException;

    public abstract boolean insertRedirectRule(String s, String s1, String s2, Bundle bundle)
        throws RemoteException;

    public abstract void killUnusedApp(int i, int j)
        throws RemoteException;

    public abstract boolean removeCallingPkgHookRule(String s, String s1)
        throws RemoteException;

    public abstract void removeServicePriority(MiuiServicePriority miuiservicepriority, boolean flag)
        throws RemoteException;

    public abstract void removeTimeConsumingIntent(String as[])
        throws RemoteException;

    public abstract void reportAnr(int i, String s, long l, long l1, String s1)
        throws RemoteException;

    public abstract void reportPerceptibleJank(int i, int j, String s, long l, long l1, 
            long l2, int k)
        throws RemoteException;

    public abstract void setForkedProcessGroup(int i, int j, int k, String s)
        throws RemoteException;

    public abstract void setMiuiBroadcastDispatchEnable(boolean flag)
        throws RemoteException;

    public abstract void setMiuiContentProviderControl(boolean flag)
        throws RemoteException;

    public abstract void setSchedFgPid(int i)
        throws RemoteException;

    public abstract void setServicePriority(List list)
        throws RemoteException;

    public abstract void setServicePriorityWithNoProc(List list, long l)
        throws RemoteException;

    public abstract List updateProcessFullMemInfoByPids(int ai[])
        throws RemoteException;

    public abstract List updateProcessPartialMemInfoByPids(int ai[])
        throws RemoteException;
}
