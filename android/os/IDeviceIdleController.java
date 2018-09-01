// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, IMaintenanceActivityListener, Binder, 
//            IBinder, Parcel

public interface IDeviceIdleController
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDeviceIdleController
    {

        public static IDeviceIdleController asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IDeviceIdleController");
            if(iinterface != null && (iinterface instanceof IDeviceIdleController))
                return (IDeviceIdleController)iinterface;
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
                parcel1.writeString("android.os.IDeviceIdleController");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                addPowerSaveWhitelistApp(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                removePowerSaveWhitelistApp(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                parcel = getSystemPowerWhitelistExceptIdle();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                parcel = getSystemPowerWhitelist();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                parcel = getUserPowerWhitelist();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                parcel = getFullPowerWhitelistExceptIdle();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                parcel = getFullPowerWhitelist();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                parcel = getAppIdWhitelistExceptIdle();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                parcel = getAppIdWhitelist();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                parcel = getAppIdUserWhitelist();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                parcel = getAppIdTempWhitelist();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                boolean flag = isPowerSaveWhitelistExceptIdleApp(parcel.readString());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                boolean flag1 = isPowerSaveWhitelistApp(parcel.readString());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                addPowerSaveTempWhitelistApp(parcel.readString(), parcel.readLong(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                long l = addPowerSaveTempWhitelistAppForMms(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                long l1 = addPowerSaveTempWhitelistAppForSms(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                exitIdle(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                boolean flag2 = registerMaintenanceActivityListener(IMaintenanceActivityListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                unregisterMaintenanceActivityListener(IMaintenanceActivityListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                i = getIdleStateDetailed();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.os.IDeviceIdleController");
                i = getLightIdleStateDetailed();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IDeviceIdleController";
        static final int TRANSACTION_addPowerSaveTempWhitelistApp = 14;
        static final int TRANSACTION_addPowerSaveTempWhitelistAppForMms = 15;
        static final int TRANSACTION_addPowerSaveTempWhitelistAppForSms = 16;
        static final int TRANSACTION_addPowerSaveWhitelistApp = 1;
        static final int TRANSACTION_exitIdle = 17;
        static final int TRANSACTION_getAppIdTempWhitelist = 11;
        static final int TRANSACTION_getAppIdUserWhitelist = 10;
        static final int TRANSACTION_getAppIdWhitelist = 9;
        static final int TRANSACTION_getAppIdWhitelistExceptIdle = 8;
        static final int TRANSACTION_getFullPowerWhitelist = 7;
        static final int TRANSACTION_getFullPowerWhitelistExceptIdle = 6;
        static final int TRANSACTION_getIdleStateDetailed = 20;
        static final int TRANSACTION_getLightIdleStateDetailed = 21;
        static final int TRANSACTION_getSystemPowerWhitelist = 4;
        static final int TRANSACTION_getSystemPowerWhitelistExceptIdle = 3;
        static final int TRANSACTION_getUserPowerWhitelist = 5;
        static final int TRANSACTION_isPowerSaveWhitelistApp = 13;
        static final int TRANSACTION_isPowerSaveWhitelistExceptIdleApp = 12;
        static final int TRANSACTION_registerMaintenanceActivityListener = 18;
        static final int TRANSACTION_removePowerSaveWhitelistApp = 2;
        static final int TRANSACTION_unregisterMaintenanceActivityListener = 19;

        public Stub()
        {
            attachInterface(this, "android.os.IDeviceIdleController");
        }
    }

    private static class Stub.Proxy
        implements IDeviceIdleController
    {

        public void addPowerSaveTempWhitelistApp(String s, long l, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            parcel.writeString(s);
            parcel.writeLong(l);
            parcel.writeInt(i);
            parcel.writeString(s1);
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

        public long addPowerSaveTempWhitelistAppForMms(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public long addPowerSaveTempWhitelistAppForSms(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public void addPowerSaveWhitelistApp(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void exitIdle(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            parcel.writeString(s);
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

        public int[] getAppIdTempWhitelist()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(11, parcel, parcel1, 0);
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

        public int[] getAppIdUserWhitelist()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public int[] getAppIdWhitelist()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public int[] getAppIdWhitelistExceptIdle()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(8, parcel, parcel1, 0);
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

        public String[] getFullPowerWhitelist()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public String[] getFullPowerWhitelistExceptIdle()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(6, parcel, parcel1, 0);
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

        public int getIdleStateDetailed()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(20, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "android.os.IDeviceIdleController";
        }

        public int getLightIdleStateDetailed()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(21, parcel, parcel1, 0);
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

        public String[] getSystemPowerWhitelist()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(4, parcel, parcel1, 0);
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

        public String[] getSystemPowerWhitelistExceptIdle()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(3, parcel, parcel1, 0);
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

        public String[] getUserPowerWhitelist()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public boolean isPowerSaveWhitelistApp(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isPowerSaveWhitelistExceptIdleApp(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean registerMaintenanceActivityListener(IMaintenanceActivityListener imaintenanceactivitylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            if(imaintenanceactivitylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imaintenanceactivitylistener.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(18, parcel, parcel1, 0);
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
            imaintenanceactivitylistener;
            parcel1.recycle();
            parcel.recycle();
            throw imaintenanceactivitylistener;
        }

        public void removePowerSaveWhitelistApp(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void unregisterMaintenanceActivityListener(IMaintenanceActivityListener imaintenanceactivitylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IDeviceIdleController");
            if(imaintenanceactivitylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imaintenanceactivitylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imaintenanceactivitylistener;
            parcel1.recycle();
            parcel.recycle();
            throw imaintenanceactivitylistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addPowerSaveTempWhitelistApp(String s, long l, int i, String s1)
        throws RemoteException;

    public abstract long addPowerSaveTempWhitelistAppForMms(String s, int i, String s1)
        throws RemoteException;

    public abstract long addPowerSaveTempWhitelistAppForSms(String s, int i, String s1)
        throws RemoteException;

    public abstract void addPowerSaveWhitelistApp(String s)
        throws RemoteException;

    public abstract void exitIdle(String s)
        throws RemoteException;

    public abstract int[] getAppIdTempWhitelist()
        throws RemoteException;

    public abstract int[] getAppIdUserWhitelist()
        throws RemoteException;

    public abstract int[] getAppIdWhitelist()
        throws RemoteException;

    public abstract int[] getAppIdWhitelistExceptIdle()
        throws RemoteException;

    public abstract String[] getFullPowerWhitelist()
        throws RemoteException;

    public abstract String[] getFullPowerWhitelistExceptIdle()
        throws RemoteException;

    public abstract int getIdleStateDetailed()
        throws RemoteException;

    public abstract int getLightIdleStateDetailed()
        throws RemoteException;

    public abstract String[] getSystemPowerWhitelist()
        throws RemoteException;

    public abstract String[] getSystemPowerWhitelistExceptIdle()
        throws RemoteException;

    public abstract String[] getUserPowerWhitelist()
        throws RemoteException;

    public abstract boolean isPowerSaveWhitelistApp(String s)
        throws RemoteException;

    public abstract boolean isPowerSaveWhitelistExceptIdleApp(String s)
        throws RemoteException;

    public abstract boolean registerMaintenanceActivityListener(IMaintenanceActivityListener imaintenanceactivitylistener)
        throws RemoteException;

    public abstract void removePowerSaveWhitelistApp(String s)
        throws RemoteException;

    public abstract void unregisterMaintenanceActivityListener(IMaintenanceActivityListener imaintenanceactivitylistener)
        throws RemoteException;
}
