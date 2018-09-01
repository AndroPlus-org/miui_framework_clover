// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.*;
import java.util.List;

// Referenced classes of package com.android.internal.app:
//            IOpsCallback, IAppOpsCallback

public interface IAppOpsService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAppOpsService
    {

        public static IAppOpsService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IAppOpsService");
            if(iinterface != null && (iinterface instanceof IAppOpsService))
                return (IAppOpsService)iinterface;
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
                parcel1.writeString("com.android.internal.app.IAppOpsService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                i = checkOperation(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                i = noteOperation(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                i = startOperation(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                finishOperation(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                startWatchingMode(parcel.readInt(), parcel.readString(), IAppOpsCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                stopWatchingMode(IAppOpsCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                parcel = getToken(parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeStrongBinder(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                i = permissionToOpCode(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                i = noteProxyOperation(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                i = checkPackage(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                parcel = getPackagesForOps(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                parcel = getOpsForPackage(parcel.readInt(), parcel.readString(), parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                parcel = getUidOps(parcel.readInt(), parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                setUidMode(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                setMode(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                resetAllModes(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                i = checkAudioOperation(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                setAudioRestriction(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                Bundle bundle;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                setUserRestrictions(bundle, parcel.readStrongBinder(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setUserRestriction(i, flag, parcel.readStrongBinder(), parcel.readInt(), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                removeUser(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                boolean flag1 = isOperationActive(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                i = registerCallback(IOpsCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.app.IAppOpsService");
                i = checkOperationInternal(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IAppOpsService";
        static final int TRANSACTION_checkAudioOperation = 17;
        static final int TRANSACTION_checkOperation = 1;
        static final int TRANSACTION_checkOperationInternal = 24;
        static final int TRANSACTION_checkPackage = 10;
        static final int TRANSACTION_finishOperation = 4;
        static final int TRANSACTION_getOpsForPackage = 12;
        static final int TRANSACTION_getPackagesForOps = 11;
        static final int TRANSACTION_getToken = 7;
        static final int TRANSACTION_getUidOps = 13;
        static final int TRANSACTION_isOperationActive = 22;
        static final int TRANSACTION_noteOperation = 2;
        static final int TRANSACTION_noteProxyOperation = 9;
        static final int TRANSACTION_permissionToOpCode = 8;
        static final int TRANSACTION_registerCallback = 23;
        static final int TRANSACTION_removeUser = 21;
        static final int TRANSACTION_resetAllModes = 16;
        static final int TRANSACTION_setAudioRestriction = 18;
        static final int TRANSACTION_setMode = 15;
        static final int TRANSACTION_setUidMode = 14;
        static final int TRANSACTION_setUserRestriction = 20;
        static final int TRANSACTION_setUserRestrictions = 19;
        static final int TRANSACTION_startOperation = 3;
        static final int TRANSACTION_startWatchingMode = 5;
        static final int TRANSACTION_stopWatchingMode = 6;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IAppOpsService");
        }
    }

    private static class Stub.Proxy
        implements IAppOpsService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int checkAudioOperation(int i, int j, int k, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public int checkOperation(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public int checkOperationInternal(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(24, parcel, parcel1, 0);
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

        public int checkPackage(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public void finishOperation(IBinder ibinder, int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
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

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IAppOpsService";
        }

        public List getOpsForPackage(int i, String s, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeIntArray(ai);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(android.app.AppOpsManager.PackageOps.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getPackagesForOps(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeIntArray(ai);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createTypedArrayList(android.app.AppOpsManager.PackageOps.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public IBinder getToken(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public List getUidOps(int i, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeIntArray(ai);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createTypedArrayList(android.app.AppOpsManager.PackageOps.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public boolean isOperationActive(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int noteOperation(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public int noteProxyOperation(int i, String s, int j, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeString(s1);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public int permissionToOpCode(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public int registerCallback(IOpsCallback iopscallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            if(iopscallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iopscallback.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            iopscallback;
            parcel1.recycle();
            parcel.recycle();
            throw iopscallback;
        }

        public void removeUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
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

        public void resetAllModes(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setAudioRestriction(int i, int j, int k, int l, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeStringArray(as);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void setMode(int i, int j, String s, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeInt(k);
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

        public void setUidMode(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public void setUserRestriction(int i, boolean flag, IBinder ibinder, int j, String as[])
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(j);
            parcel.writeStringArray(as);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setUserRestrictions(Bundle bundle, IBinder ibinder, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
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

        public int startOperation(IBinder ibinder, int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void startWatchingMode(int i, String s, IAppOpsCallback iappopscallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            parcel.writeInt(i);
            parcel.writeString(s);
            s = obj;
            if(iappopscallback == null)
                break MISSING_BLOCK_LABEL_46;
            s = iappopscallback.asBinder();
            parcel.writeStrongBinder(s);
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

        public void stopWatchingMode(IAppOpsCallback iappopscallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IAppOpsService");
            if(iappopscallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iappopscallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iappopscallback;
            parcel1.recycle();
            parcel.recycle();
            throw iappopscallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int checkAudioOperation(int i, int j, int k, String s)
        throws RemoteException;

    public abstract int checkOperation(int i, int j, String s)
        throws RemoteException;

    public abstract int checkOperationInternal(int i, int j, String s)
        throws RemoteException;

    public abstract int checkPackage(int i, String s)
        throws RemoteException;

    public abstract void finishOperation(IBinder ibinder, int i, int j, String s)
        throws RemoteException;

    public abstract List getOpsForPackage(int i, String s, int ai[])
        throws RemoteException;

    public abstract List getPackagesForOps(int ai[])
        throws RemoteException;

    public abstract IBinder getToken(IBinder ibinder)
        throws RemoteException;

    public abstract List getUidOps(int i, int ai[])
        throws RemoteException;

    public abstract boolean isOperationActive(int i, int j, String s)
        throws RemoteException;

    public abstract int noteOperation(int i, int j, String s)
        throws RemoteException;

    public abstract int noteProxyOperation(int i, String s, int j, String s1)
        throws RemoteException;

    public abstract int permissionToOpCode(String s)
        throws RemoteException;

    public abstract int registerCallback(IOpsCallback iopscallback)
        throws RemoteException;

    public abstract void removeUser(int i)
        throws RemoteException;

    public abstract void resetAllModes(int i, String s)
        throws RemoteException;

    public abstract void setAudioRestriction(int i, int j, int k, int l, String as[])
        throws RemoteException;

    public abstract void setMode(int i, int j, String s, int k)
        throws RemoteException;

    public abstract void setUidMode(int i, int j, int k)
        throws RemoteException;

    public abstract void setUserRestriction(int i, boolean flag, IBinder ibinder, int j, String as[])
        throws RemoteException;

    public abstract void setUserRestrictions(Bundle bundle, IBinder ibinder, int i)
        throws RemoteException;

    public abstract int startOperation(IBinder ibinder, int i, int j, String s)
        throws RemoteException;

    public abstract void startWatchingMode(int i, String s, IAppOpsCallback iappopscallback)
        throws RemoteException;

    public abstract void stopWatchingMode(IAppOpsCallback iappopscallback)
        throws RemoteException;
}
