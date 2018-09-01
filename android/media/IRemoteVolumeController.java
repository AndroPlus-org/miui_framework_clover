// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.media.session.ISessionController;
import android.os.*;

public interface IRemoteVolumeController
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRemoteVolumeController
    {

        public static IRemoteVolumeController asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IRemoteVolumeController");
            if(iinterface != null && (iinterface instanceof IRemoteVolumeController))
                return (IRemoteVolumeController)iinterface;
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
                parcel1.writeString("android.media.IRemoteVolumeController");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IRemoteVolumeController");
                remoteVolumeChanged(android.media.session.ISessionController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.IRemoteVolumeController");
                updateRemoteController(android.media.session.ISessionController.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.IRemoteVolumeController";
        static final int TRANSACTION_remoteVolumeChanged = 1;
        static final int TRANSACTION_updateRemoteController = 2;

        public Stub()
        {
            attachInterface(this, "android.media.IRemoteVolumeController");
        }
    }

    private static class Stub.Proxy
        implements IRemoteVolumeController
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IRemoteVolumeController";
        }

        public void remoteVolumeChanged(ISessionController isessioncontroller, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRemoteVolumeController");
            if(isessioncontroller == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = isessioncontroller.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            isessioncontroller;
            parcel.recycle();
            throw isessioncontroller;
        }

        public void updateRemoteController(ISessionController isessioncontroller)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRemoteVolumeController");
            if(isessioncontroller == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = isessioncontroller.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            isessioncontroller;
            parcel.recycle();
            throw isessioncontroller;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void remoteVolumeChanged(ISessionController isessioncontroller, int i)
        throws RemoteException;

    public abstract void updateRemoteController(ISessionController isessioncontroller)
        throws RemoteException;
}
