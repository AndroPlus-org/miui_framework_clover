// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.IntentSender;
import android.graphics.Bitmap;
import android.os.*;

// Referenced classes of package android.content.pm:
//            ParceledListSlice, IPackageInstallerSession, IPackageInstallerCallback, VersionedPackage

public interface IPackageInstaller
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPackageInstaller
    {

        public static IPackageInstaller asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.content.pm.IPackageInstaller");
            if(iinterface != null && (iinterface instanceof IPackageInstaller))
                return (IPackageInstaller)iinterface;
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
                parcel1.writeString("android.content.pm.IPackageInstaller");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                PackageInstaller.SessionParams sessionparams;
                if(parcel.readInt() != 0)
                    sessionparams = (PackageInstaller.SessionParams)PackageInstaller.SessionParams.CREATOR.createFromParcel(parcel);
                else
                    sessionparams = null;
                i = createSession(sessionparams, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateSessionAppIcon(i, parcel);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                updateSessionAppLabel(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                abandonSession(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                parcel = openSession(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                parcel = getSessionInfo(parcel.readInt());
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

            case 7: // '\007'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                parcel = getAllSessions(parcel.readInt());
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

            case 8: // '\b'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                parcel = getMySessions(parcel.readString(), parcel.readInt());
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

            case 9: // '\t'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                registerCallback(IPackageInstallerCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                unregisterCallback(IPackageInstallerCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                VersionedPackage versionedpackage;
                String s;
                IntentSender intentsender;
                if(parcel.readInt() != 0)
                    versionedpackage = (VersionedPackage)VersionedPackage.CREATOR.createFromParcel(parcel);
                else
                    versionedpackage = null;
                s = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    intentsender = (IntentSender)IntentSender.CREATOR.createFromParcel(parcel);
                else
                    intentsender = null;
                uninstall(versionedpackage, s, i, intentsender, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.content.pm.IPackageInstaller");
                i = parcel.readInt();
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            setPermissionsResult(i, flag);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.content.pm.IPackageInstaller";
        static final int TRANSACTION_abandonSession = 4;
        static final int TRANSACTION_createSession = 1;
        static final int TRANSACTION_getAllSessions = 7;
        static final int TRANSACTION_getMySessions = 8;
        static final int TRANSACTION_getSessionInfo = 6;
        static final int TRANSACTION_openSession = 5;
        static final int TRANSACTION_registerCallback = 9;
        static final int TRANSACTION_setPermissionsResult = 12;
        static final int TRANSACTION_uninstall = 11;
        static final int TRANSACTION_unregisterCallback = 10;
        static final int TRANSACTION_updateSessionAppIcon = 2;
        static final int TRANSACTION_updateSessionAppLabel = 3;

        public Stub()
        {
            attachInterface(this, "android.content.pm.IPackageInstaller");
        }
    }

    private static class Stub.Proxy
        implements IPackageInstaller
    {

        public void abandonSession(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int createSession(PackageInstaller.SessionParams sessionparams, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            if(sessionparams == null)
                break MISSING_BLOCK_LABEL_85;
            parcel.writeInt(1);
            sessionparams.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            sessionparams;
            parcel1.recycle();
            parcel.recycle();
            throw sessionparams;
        }

        public ParceledListSlice getAllSessions(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParceledListSlice parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parceledlistslice;
_L2:
            parceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.content.pm.IPackageInstaller";
        }

        public ParceledListSlice getMySessions(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public PackageInstaller.SessionInfo getSessionInfo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            PackageInstaller.SessionInfo sessioninfo = (PackageInstaller.SessionInfo)PackageInstaller.SessionInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return sessioninfo;
_L2:
            sessioninfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IPackageInstallerSession openSession(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IPackageInstallerSession ipackageinstallersession;
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            ipackageinstallersession = IPackageInstallerSession.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return ipackageinstallersession;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void registerCallback(IPackageInstallerCallback ipackageinstallercallback, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            if(ipackageinstallercallback == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ipackageinstallercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ipackageinstallercallback;
            parcel1.recycle();
            parcel.recycle();
            throw ipackageinstallercallback;
        }

        public void setPermissionsResult(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
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

        public void uninstall(VersionedPackage versionedpackage, String s, int i, IntentSender intentsender, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            if(versionedpackage == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            versionedpackage.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeInt(i);
            if(intentsender == null)
                break MISSING_BLOCK_LABEL_127;
            parcel.writeInt(1);
            intentsender.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(j);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            versionedpackage;
            parcel1.recycle();
            parcel.recycle();
            throw versionedpackage;
            parcel.writeInt(0);
              goto _L4
        }

        public void unregisterCallback(IPackageInstallerCallback ipackageinstallercallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            if(ipackageinstallercallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ipackageinstallercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ipackageinstallercallback;
            parcel1.recycle();
            parcel.recycle();
            throw ipackageinstallercallback;
        }

        public void updateSessionAppIcon(int i, Bitmap bitmap)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            parcel.writeInt(i);
            if(bitmap == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            bitmap.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bitmap;
            parcel1.recycle();
            parcel.recycle();
            throw bitmap;
        }

        public void updateSessionAppLabel(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.content.pm.IPackageInstaller");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
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


    public abstract void abandonSession(int i)
        throws RemoteException;

    public abstract int createSession(PackageInstaller.SessionParams sessionparams, String s, int i)
        throws RemoteException;

    public abstract ParceledListSlice getAllSessions(int i)
        throws RemoteException;

    public abstract ParceledListSlice getMySessions(String s, int i)
        throws RemoteException;

    public abstract PackageInstaller.SessionInfo getSessionInfo(int i)
        throws RemoteException;

    public abstract IPackageInstallerSession openSession(int i)
        throws RemoteException;

    public abstract void registerCallback(IPackageInstallerCallback ipackageinstallercallback, int i)
        throws RemoteException;

    public abstract void setPermissionsResult(int i, boolean flag)
        throws RemoteException;

    public abstract void uninstall(VersionedPackage versionedpackage, String s, int i, IntentSender intentsender, int j)
        throws RemoteException;

    public abstract void unregisterCallback(IPackageInstallerCallback ipackageinstallercallback)
        throws RemoteException;

    public abstract void updateSessionAppIcon(int i, Bitmap bitmap)
        throws RemoteException;

    public abstract void updateSessionAppLabel(int i, String s)
        throws RemoteException;
}
