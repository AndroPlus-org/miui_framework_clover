// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.quicksettings;

import android.graphics.drawable.Icon;
import android.os.*;

// Referenced classes of package android.service.quicksettings:
//            Tile

public interface IQSService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IQSService
    {

        public static IQSService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.quicksettings.IQSService");
            if(iinterface != null && (iinterface instanceof IQSService))
                return (IQSService)iinterface;
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
            boolean flag = false;
            boolean flag1 = false;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.service.quicksettings.IQSService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.quicksettings.IQSService");
                parcel = getTile(parcel.readStrongBinder());
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

            case 2: // '\002'
                parcel.enforceInterface("android.service.quicksettings.IQSService");
                Tile tile;
                if(parcel.readInt() != 0)
                    tile = (Tile)Tile.CREATOR.createFromParcel(parcel);
                else
                    tile = null;
                updateQsTile(tile, parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.quicksettings.IQSService");
                IBinder ibinder = parcel.readStrongBinder();
                Icon icon;
                if(parcel.readInt() != 0)
                    icon = (Icon)Icon.CREATOR.createFromParcel(parcel);
                else
                    icon = null;
                updateStatusIcon(ibinder, icon, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.quicksettings.IQSService");
                onShowDialog(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.quicksettings.IQSService");
                onStartActivity(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.quicksettings.IQSService");
                boolean flag2 = isLocked();
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag2)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.quicksettings.IQSService");
                boolean flag3 = isSecure();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag3)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.service.quicksettings.IQSService");
                startUnlockAndRun(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.service.quicksettings.IQSService");
                onDialogHidden(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.service.quicksettings.IQSService");
                onStartSuccessful(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.quicksettings.IQSService";
        static final int TRANSACTION_getTile = 1;
        static final int TRANSACTION_isLocked = 6;
        static final int TRANSACTION_isSecure = 7;
        static final int TRANSACTION_onDialogHidden = 9;
        static final int TRANSACTION_onShowDialog = 4;
        static final int TRANSACTION_onStartActivity = 5;
        static final int TRANSACTION_onStartSuccessful = 10;
        static final int TRANSACTION_startUnlockAndRun = 8;
        static final int TRANSACTION_updateQsTile = 2;
        static final int TRANSACTION_updateStatusIcon = 3;

        public Stub()
        {
            attachInterface(this, "android.service.quicksettings.IQSService");
        }
    }

    private static class Stub.Proxy
        implements IQSService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.quicksettings.IQSService";
        }

        public Tile getTile(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ibinder = (Tile)Tile.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
_L2:
            ibinder = null;
            if(true) goto _L4; else goto _L3
_L3:
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean isLocked()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.quicksettings.IQSService");
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

        public boolean isSecure()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.service.quicksettings.IQSService");
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

        public void onDialogHidden(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void onShowDialog(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSService");
            parcel.writeStrongBinder(ibinder);
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

        public void onStartActivity(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void onStartSuccessful(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void startUnlockAndRun(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void updateQsTile(Tile tile, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSService");
            if(tile == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            tile.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            tile;
            parcel1.recycle();
            parcel.recycle();
            throw tile;
        }

        public void updateStatusIcon(IBinder ibinder, Icon icon, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.quicksettings.IQSService");
            parcel.writeStrongBinder(ibinder);
            if(icon == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            icon.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract Tile getTile(IBinder ibinder)
        throws RemoteException;

    public abstract boolean isLocked()
        throws RemoteException;

    public abstract boolean isSecure()
        throws RemoteException;

    public abstract void onDialogHidden(IBinder ibinder)
        throws RemoteException;

    public abstract void onShowDialog(IBinder ibinder)
        throws RemoteException;

    public abstract void onStartActivity(IBinder ibinder)
        throws RemoteException;

    public abstract void onStartSuccessful(IBinder ibinder)
        throws RemoteException;

    public abstract void startUnlockAndRun(IBinder ibinder)
        throws RemoteException;

    public abstract void updateQsTile(Tile tile, IBinder ibinder)
        throws RemoteException;

    public abstract void updateStatusIcon(IBinder ibinder, Icon icon, String s)
        throws RemoteException;
}
