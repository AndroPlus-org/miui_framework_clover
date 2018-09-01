// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

public interface IVolumeController
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVolumeController
    {

        public static IVolumeController asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IVolumeController");
            if(iinterface != null && (iinterface instanceof IVolumeController))
                return (IVolumeController)iinterface;
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
                parcel1.writeString("android.media.IVolumeController");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IVolumeController");
                displaySafeVolumeWarning(parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.IVolumeController");
                volumeChanged(parcel.readInt(), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.IVolumeController");
                masterMuteChanged(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.IVolumeController");
                setLayoutDirection(parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.IVolumeController");
                dismiss();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.IVolumeController");
                setA11yMode(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.IVolumeController";
        static final int TRANSACTION_dismiss = 5;
        static final int TRANSACTION_displaySafeVolumeWarning = 1;
        static final int TRANSACTION_masterMuteChanged = 3;
        static final int TRANSACTION_setA11yMode = 6;
        static final int TRANSACTION_setLayoutDirection = 4;
        static final int TRANSACTION_volumeChanged = 2;

        public Stub()
        {
            attachInterface(this, "android.media.IVolumeController");
        }
    }

    private static class Stub.Proxy
        implements IVolumeController
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void dismiss()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IVolumeController");
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void displaySafeVolumeWarning(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IVolumeController");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IVolumeController";
        }

        public void masterMuteChanged(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IVolumeController");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setA11yMode(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IVolumeController");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void setLayoutDirection(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IVolumeController");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void volumeChanged(int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IVolumeController");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void dismiss()
        throws RemoteException;

    public abstract void displaySafeVolumeWarning(int i)
        throws RemoteException;

    public abstract void masterMuteChanged(int i)
        throws RemoteException;

    public abstract void setA11yMode(int i)
        throws RemoteException;

    public abstract void setLayoutDirection(int i)
        throws RemoteException;

    public abstract void volumeChanged(int i, int j)
        throws RemoteException;
}
