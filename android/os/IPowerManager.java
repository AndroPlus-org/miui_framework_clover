// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, IBinder, WorkSource, 
//            PowerSaveState, Binder, Parcel

public interface IPowerManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPowerManager
    {

        public static IPowerManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IPowerManager");
            if(iinterface != null && (iinterface instanceof IPowerManager))
                return (IPowerManager)iinterface;
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
                parcel1.writeString("android.os.IPowerManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IPowerManager");
                IBinder ibinder = parcel.readStrongBinder();
                i = parcel.readInt();
                String s = parcel.readString();
                String s1 = parcel.readString();
                WorkSource worksource;
                if(parcel.readInt() != 0)
                    worksource = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource = null;
                acquireWakeLock(ibinder, i, s, s1, worksource, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IPowerManager");
                acquireWakeLockWithUid(parcel.readStrongBinder(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IPowerManager");
                releaseWakeLock(parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.os.IPowerManager");
                updateWakeLockUids(parcel.readStrongBinder(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.os.IPowerManager");
                powerHint(parcel.readInt(), parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.os.IPowerManager");
                IBinder ibinder1 = parcel.readStrongBinder();
                WorkSource worksource1;
                if(parcel.readInt() != 0)
                    worksource1 = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource1 = null;
                updateWakeLockWorkSource(ibinder1, worksource1, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.os.IPowerManager");
                boolean flag = isWakeLockLevelSupported(parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.os.IPowerManager");
                userActivity(parcel.readLong(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.os.IPowerManager");
                wakeUp(parcel.readLong(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.os.IPowerManager");
                goToSleep(parcel.readLong(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.os.IPowerManager");
                nap(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.os.IPowerManager");
                boolean flag1 = isInteractive();
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.os.IPowerManager");
                boolean flag2 = isPowerSaveMode();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.os.IPowerManager");
                parcel = getPowerSaveState(parcel.readInt());
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

            case 15: // '\017'
                parcel.enforceInterface("android.os.IPowerManager");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                flag3 = setPowerSaveMode(flag3);
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.os.IPowerManager");
                boolean flag4 = isDeviceIdleMode();
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.os.IPowerManager");
                boolean flag5 = isLightDeviceIdleMode();
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.os.IPowerManager");
                String s2;
                boolean flag6;
                boolean flag12;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                s2 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag12 = true;
                else
                    flag12 = false;
                reboot(flag6, s2, flag12);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.os.IPowerManager");
                boolean flag7;
                boolean flag13;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                if(parcel.readInt() != 0)
                    flag13 = true;
                else
                    flag13 = false;
                rebootSafeMode(flag7, flag13);
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.os.IPowerManager");
                String s3;
                boolean flag8;
                boolean flag14;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                s3 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                shutdown(flag8, s3, flag14);
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.os.IPowerManager");
                crash(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.os.IPowerManager");
                i = getLastShutdownReason();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.os.IPowerManager");
                setStayOnSetting(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.os.IPowerManager");
                boostScreenBrightness(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.os.IPowerManager");
                boolean flag9 = isScreenBrightnessBoosted();
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.os.IPowerManager");
                setTemporaryScreenBrightnessSettingOverride(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.os.IPowerManager");
                setTemporaryScreenAutoBrightnessAdjustmentSettingOverride(parcel.readFloat());
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.os.IPowerManager");
                i = getScreenBrightnessReal();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.os.IPowerManager");
                boolean flag10;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                setAttentionLight(flag10, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.os.IPowerManager");
                i = parcel.readInt();
                break;
            }
            boolean flag11;
            if(parcel.readInt() != 0)
                flag11 = true;
            else
                flag11 = false;
            updateBlockedUids(i, flag11);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.os.IPowerManager";
        static final int TRANSACTION_acquireWakeLock = 1;
        static final int TRANSACTION_acquireWakeLockWithUid = 2;
        static final int TRANSACTION_boostScreenBrightness = 24;
        static final int TRANSACTION_crash = 21;
        static final int TRANSACTION_getLastShutdownReason = 22;
        static final int TRANSACTION_getPowerSaveState = 14;
        static final int TRANSACTION_getScreenBrightnessReal = 28;
        static final int TRANSACTION_goToSleep = 10;
        static final int TRANSACTION_isDeviceIdleMode = 16;
        static final int TRANSACTION_isInteractive = 12;
        static final int TRANSACTION_isLightDeviceIdleMode = 17;
        static final int TRANSACTION_isPowerSaveMode = 13;
        static final int TRANSACTION_isScreenBrightnessBoosted = 25;
        static final int TRANSACTION_isWakeLockLevelSupported = 7;
        static final int TRANSACTION_nap = 11;
        static final int TRANSACTION_powerHint = 5;
        static final int TRANSACTION_reboot = 18;
        static final int TRANSACTION_rebootSafeMode = 19;
        static final int TRANSACTION_releaseWakeLock = 3;
        static final int TRANSACTION_setAttentionLight = 29;
        static final int TRANSACTION_setPowerSaveMode = 15;
        static final int TRANSACTION_setStayOnSetting = 23;
        static final int TRANSACTION_setTemporaryScreenAutoBrightnessAdjustmentSettingOverride = 27;
        static final int TRANSACTION_setTemporaryScreenBrightnessSettingOverride = 26;
        static final int TRANSACTION_shutdown = 20;
        static final int TRANSACTION_updateBlockedUids = 30;
        static final int TRANSACTION_updateWakeLockUids = 4;
        static final int TRANSACTION_updateWakeLockWorkSource = 6;
        static final int TRANSACTION_userActivity = 8;
        static final int TRANSACTION_wakeUp = 9;

        public Stub()
        {
            attachInterface(this, "android.os.IPowerManager");
        }
    }

    private static class Stub.Proxy
        implements IPowerManager
    {

        public void acquireWakeLock(IBinder ibinder, int i, String s, String s1, WorkSource worksource, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            if(worksource == null)
                break MISSING_BLOCK_LABEL_100;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s2);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void acquireWakeLockWithUid(IBinder ibinder, int i, String s, String s1, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(j);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void boostScreenBrightness(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeLong(l);
            mRemote.transact(24, parcel, parcel1, 0);
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

        public void crash(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
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
            return "android.os.IPowerManager";
        }

        public int getLastShutdownReason()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IPowerManager");
            mRemote.transact(22, parcel, parcel1, 0);
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

        public PowerSaveState getPowerSaveState(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            PowerSaveState powersavestate = (PowerSaveState)PowerSaveState.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return powersavestate;
_L2:
            powersavestate = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getScreenBrightnessReal()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IPowerManager");
            mRemote.transact(28, parcel, parcel1, 0);
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

        public void goToSleep(long l, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean isDeviceIdleMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IPowerManager");
            mRemote.transact(16, parcel, parcel1, 0);
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

        public boolean isInteractive()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IPowerManager");
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

        public boolean isLightDeviceIdleMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IPowerManager");
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

        public boolean isPowerSaveMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IPowerManager");
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

        public boolean isScreenBrightnessBoosted()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IPowerManager");
            mRemote.transact(25, parcel, parcel1, 0);
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

        public boolean isWakeLockLevelSupported(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
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

        public void nap(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeLong(l);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void powerHint(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
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

        public void reboot(boolean flag, String s, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void rebootSafeMode(boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
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
            mRemote.transact(19, parcel, parcel1, 0);
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

        public void releaseWakeLock(IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
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

        public void setAttentionLight(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
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

        public boolean setPowerSaveMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public void setStayOnSetting(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
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

        public void setTemporaryScreenAutoBrightnessAdjustmentSettingOverride(float f)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeFloat(f);
            mRemote.transact(27, parcel, parcel1, 0);
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

        public void setTemporaryScreenBrightnessSettingOverride(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeInt(i);
            mRemote.transact(26, parcel, parcel1, 0);
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

        public void shutdown(boolean flag, String s, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateBlockedUids(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(30, parcel, parcel1, 0);
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

        public void updateWakeLockUids(IBinder ibinder, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeIntArray(ai);
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

        public void updateWakeLockWorkSource(IBinder ibinder, WorkSource worksource, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeStrongBinder(ibinder);
            if(worksource == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void userActivity(long l, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public void wakeUp(long l, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IPowerManager");
            parcel.writeLong(l);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(9, parcel, parcel1, 0);
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


    public abstract void acquireWakeLock(IBinder ibinder, int i, String s, String s1, WorkSource worksource, String s2)
        throws RemoteException;

    public abstract void acquireWakeLockWithUid(IBinder ibinder, int i, String s, String s1, int j)
        throws RemoteException;

    public abstract void boostScreenBrightness(long l)
        throws RemoteException;

    public abstract void crash(String s)
        throws RemoteException;

    public abstract int getLastShutdownReason()
        throws RemoteException;

    public abstract PowerSaveState getPowerSaveState(int i)
        throws RemoteException;

    public abstract int getScreenBrightnessReal()
        throws RemoteException;

    public abstract void goToSleep(long l, int i, int j)
        throws RemoteException;

    public abstract boolean isDeviceIdleMode()
        throws RemoteException;

    public abstract boolean isInteractive()
        throws RemoteException;

    public abstract boolean isLightDeviceIdleMode()
        throws RemoteException;

    public abstract boolean isPowerSaveMode()
        throws RemoteException;

    public abstract boolean isScreenBrightnessBoosted()
        throws RemoteException;

    public abstract boolean isWakeLockLevelSupported(int i)
        throws RemoteException;

    public abstract void nap(long l)
        throws RemoteException;

    public abstract void powerHint(int i, int j)
        throws RemoteException;

    public abstract void reboot(boolean flag, String s, boolean flag1)
        throws RemoteException;

    public abstract void rebootSafeMode(boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void releaseWakeLock(IBinder ibinder, int i)
        throws RemoteException;

    public abstract void setAttentionLight(boolean flag, int i)
        throws RemoteException;

    public abstract boolean setPowerSaveMode(boolean flag)
        throws RemoteException;

    public abstract void setStayOnSetting(int i)
        throws RemoteException;

    public abstract void setTemporaryScreenAutoBrightnessAdjustmentSettingOverride(float f)
        throws RemoteException;

    public abstract void setTemporaryScreenBrightnessSettingOverride(int i)
        throws RemoteException;

    public abstract void shutdown(boolean flag, String s, boolean flag1)
        throws RemoteException;

    public abstract void updateBlockedUids(int i, boolean flag)
        throws RemoteException;

    public abstract void updateWakeLockUids(IBinder ibinder, int ai[])
        throws RemoteException;

    public abstract void updateWakeLockWorkSource(IBinder ibinder, WorkSource worksource, String s)
        throws RemoteException;

    public abstract void userActivity(long l, int i, int j)
        throws RemoteException;

    public abstract void wakeUp(long l, String s, String s1)
        throws RemoteException;
}
