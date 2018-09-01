// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.os;

import android.os.*;

// Referenced classes of package miui.os:
//            IMiuiInitObserver

public interface IMiuiInit
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiInit
    {

        public static IMiuiInit asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.os.IMiuiInit");
            if(iinterface != null && (iinterface instanceof IMiuiInit))
                return (IMiuiInit)iinterface;
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
            String s1;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("miui.os.IMiuiInit");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.os.IMiuiInit");
                boolean flag = initCustEnvironment(parcel.readString(), IMiuiInitObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.os.IMiuiInit");
                parcel = getCustVariants();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("miui.os.IMiuiInit");
                installPreinstallApp();
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("miui.os.IMiuiInit");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                doFactoryReset(flag1);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("miui.os.IMiuiInit");
                boolean flag2 = isPreinstalledPackage(parcel.readString());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("miui.os.IMiuiInit");
                parcel = getMiuiChannelPath(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("miui.os.IMiuiInit");
                removeFromPreinstallList(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("miui.os.IMiuiInit");
                parcel = getMiuiPreinstallAppPath(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("miui.os.IMiuiInit");
                String s = parcel.readString();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setRestrictAspect(s, flag3);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("miui.os.IMiuiInit");
                boolean flag4 = isRestrictAspect(parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("miui.os.IMiuiInit");
                float f = getAspectRatio(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeFloat(f);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("miui.os.IMiuiInit");
                i = getDefaultAspectType(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("miui.os.IMiuiInit");
                i = getNotchConfig(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("miui.os.IMiuiInit");
                i = getPreinstalledAppVersion(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("miui.os.IMiuiInit");
                s1 = parcel.readString();
                break;
            }
            boolean flag5;
            if(parcel.readInt() != 0)
                flag5 = true;
            else
                flag5 = false;
            setNotchSpecialMode(s1, flag5);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "miui.os.IMiuiInit";
        static final int TRANSACTION_doFactoryReset = 4;
        static final int TRANSACTION_getAspectRatio = 11;
        static final int TRANSACTION_getCustVariants = 2;
        static final int TRANSACTION_getDefaultAspectType = 12;
        static final int TRANSACTION_getMiuiChannelPath = 6;
        static final int TRANSACTION_getMiuiPreinstallAppPath = 8;
        static final int TRANSACTION_getNotchConfig = 13;
        static final int TRANSACTION_getPreinstalledAppVersion = 14;
        static final int TRANSACTION_initCustEnvironment = 1;
        static final int TRANSACTION_installPreinstallApp = 3;
        static final int TRANSACTION_isPreinstalledPackage = 5;
        static final int TRANSACTION_isRestrictAspect = 10;
        static final int TRANSACTION_removeFromPreinstallList = 7;
        static final int TRANSACTION_setNotchSpecialMode = 15;
        static final int TRANSACTION_setRestrictAspect = 9;

        public Stub()
        {
            attachInterface(this, "miui.os.IMiuiInit");
        }
    }

    private static class Stub.Proxy
        implements IMiuiInit
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void doFactoryReset(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            if(flag)
                i = 1;
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

        public float getAspectRatio(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            float f;
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            f = parcel1.readFloat();
            parcel1.recycle();
            parcel.recycle();
            return f;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String[] getCustVariants()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
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

        public int getDefaultAspectType(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.os.IMiuiInit";
        }

        public String getMiuiChannelPath(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getMiuiPreinstallAppPath(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getNotchConfig(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getPreinstalledAppVersion(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean initCustEnvironment(String s, IMiuiInitObserver imiuiinitobserver)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
            s = obj;
            if(imiuiinitobserver == null)
                break MISSING_BLOCK_LABEL_38;
            s = imiuiinitobserver.asBinder();
            int i;
            parcel.writeStrongBinder(s);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void installPreinstallApp()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
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

        public boolean isPreinstalledPackage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isRestrictAspect(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeFromPreinstallList(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setNotchSpecialMode(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setRestrictAspect(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiInit");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
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


    public abstract void doFactoryReset(boolean flag)
        throws RemoteException;

    public abstract float getAspectRatio(String s)
        throws RemoteException;

    public abstract String[] getCustVariants()
        throws RemoteException;

    public abstract int getDefaultAspectType(String s)
        throws RemoteException;

    public abstract String getMiuiChannelPath(String s)
        throws RemoteException;

    public abstract String getMiuiPreinstallAppPath(String s)
        throws RemoteException;

    public abstract int getNotchConfig(String s)
        throws RemoteException;

    public abstract int getPreinstalledAppVersion(String s)
        throws RemoteException;

    public abstract boolean initCustEnvironment(String s, IMiuiInitObserver imiuiinitobserver)
        throws RemoteException;

    public abstract void installPreinstallApp()
        throws RemoteException;

    public abstract boolean isPreinstalledPackage(String s)
        throws RemoteException;

    public abstract boolean isRestrictAspect(String s)
        throws RemoteException;

    public abstract void removeFromPreinstallList(String s)
        throws RemoteException;

    public abstract void setNotchSpecialMode(String s, boolean flag)
        throws RemoteException;

    public abstract void setRestrictAspect(String s, boolean flag)
        throws RemoteException;
}
