// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.projection;

import android.os.*;

// Referenced classes of package android.media.projection:
//            IMediaProjectionCallback

public interface IMediaProjection
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaProjection
    {

        public static IMediaProjection asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.projection.IMediaProjection");
            if(iinterface != null && (iinterface instanceof IMediaProjection))
                return (IMediaProjection)iinterface;
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
            boolean flag2 = false;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.media.projection.IMediaProjection");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.projection.IMediaProjection");
                start(IMediaProjectionCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.projection.IMediaProjection");
                stop();
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.projection.IMediaProjection");
                boolean flag3 = canProjectAudio();
                parcel1.writeNoException();
                i = ((flag2) ? 1 : 0);
                if(flag3)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.projection.IMediaProjection");
                boolean flag4 = canProjectVideo();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag4)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.projection.IMediaProjection");
                boolean flag5 = canProjectSecureVideo();
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag5)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.projection.IMediaProjection");
                i = applyVirtualDisplayFlags(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.projection.IMediaProjection");
                registerCallback(IMediaProjectionCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.projection.IMediaProjection");
                unregisterCallback(IMediaProjectionCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.projection.IMediaProjection";
        static final int TRANSACTION_applyVirtualDisplayFlags = 6;
        static final int TRANSACTION_canProjectAudio = 3;
        static final int TRANSACTION_canProjectSecureVideo = 5;
        static final int TRANSACTION_canProjectVideo = 4;
        static final int TRANSACTION_registerCallback = 7;
        static final int TRANSACTION_start = 1;
        static final int TRANSACTION_stop = 2;
        static final int TRANSACTION_unregisterCallback = 8;

        public Stub()
        {
            attachInterface(this, "android.media.projection.IMediaProjection");
        }
    }

    private static class Stub.Proxy
        implements IMediaProjection
    {

        public int applyVirtualDisplayFlags(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjection");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean canProjectAudio()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.projection.IMediaProjection");
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

        public boolean canProjectSecureVideo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.projection.IMediaProjection");
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

        public boolean canProjectVideo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.media.projection.IMediaProjection");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.projection.IMediaProjection";
        }

        public void registerCallback(IMediaProjectionCallback imediaprojectioncallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjection");
            if(imediaprojectioncallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imediaprojectioncallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediaprojectioncallback;
            parcel1.recycle();
            parcel.recycle();
            throw imediaprojectioncallback;
        }

        public void start(IMediaProjectionCallback imediaprojectioncallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjection");
            if(imediaprojectioncallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imediaprojectioncallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediaprojectioncallback;
            parcel1.recycle();
            parcel.recycle();
            throw imediaprojectioncallback;
        }

        public void stop()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjection");
            mRemote.transact(2, parcel, parcel1, 0);
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

        public void unregisterCallback(IMediaProjectionCallback imediaprojectioncallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjection");
            if(imediaprojectioncallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imediaprojectioncallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediaprojectioncallback;
            parcel1.recycle();
            parcel.recycle();
            throw imediaprojectioncallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int applyVirtualDisplayFlags(int i)
        throws RemoteException;

    public abstract boolean canProjectAudio()
        throws RemoteException;

    public abstract boolean canProjectSecureVideo()
        throws RemoteException;

    public abstract boolean canProjectVideo()
        throws RemoteException;

    public abstract void registerCallback(IMediaProjectionCallback imediaprojectioncallback)
        throws RemoteException;

    public abstract void start(IMediaProjectionCallback imediaprojectioncallback)
        throws RemoteException;

    public abstract void stop()
        throws RemoteException;

    public abstract void unregisterCallback(IMediaProjectionCallback imediaprojectioncallback)
        throws RemoteException;
}
