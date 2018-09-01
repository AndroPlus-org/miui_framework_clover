// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.projection;

import android.os.*;

// Referenced classes of package android.media.projection:
//            IMediaProjectionWatcherCallback, IMediaProjection, MediaProjectionInfo

public interface IMediaProjectionManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaProjectionManager
    {

        public static IMediaProjectionManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.projection.IMediaProjectionManager");
            if(iinterface != null && (iinterface instanceof IMediaProjectionManager))
                return (IMediaProjectionManager)iinterface;
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
                parcel1.writeString("android.media.projection.IMediaProjectionManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.projection.IMediaProjectionManager");
                boolean flag = hasProjectionPermission(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.projection.IMediaProjectionManager");
                i = parcel.readInt();
                String s = parcel.readString();
                j = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                parcel = createProjection(i, s, j, flag1);
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.projection.IMediaProjectionManager");
                boolean flag2 = isValidMediaProjection(IMediaProjection.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.projection.IMediaProjectionManager");
                parcel = getActiveProjectionInfo();
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

            case 5: // '\005'
                parcel.enforceInterface("android.media.projection.IMediaProjectionManager");
                stopActiveProjection();
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.projection.IMediaProjectionManager");
                addCallback(IMediaProjectionWatcherCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.projection.IMediaProjectionManager");
                removeCallback(IMediaProjectionWatcherCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.projection.IMediaProjectionManager";
        static final int TRANSACTION_addCallback = 6;
        static final int TRANSACTION_createProjection = 2;
        static final int TRANSACTION_getActiveProjectionInfo = 4;
        static final int TRANSACTION_hasProjectionPermission = 1;
        static final int TRANSACTION_isValidMediaProjection = 3;
        static final int TRANSACTION_removeCallback = 7;
        static final int TRANSACTION_stopActiveProjection = 5;

        public Stub()
        {
            attachInterface(this, "android.media.projection.IMediaProjectionManager");
        }
    }

    private static class Stub.Proxy
        implements IMediaProjectionManager
    {

        public void addCallback(IMediaProjectionWatcherCallback imediaprojectionwatchercallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjectionManager");
            if(imediaprojectionwatchercallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imediaprojectionwatchercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediaprojectionwatchercallback;
            parcel1.recycle();
            parcel.recycle();
            throw imediaprojectionwatchercallback;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public IMediaProjection createProjection(int i, String s, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjectionManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            s = IMediaProjection.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public MediaProjectionInfo getActiveProjectionInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjectionManager");
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            MediaProjectionInfo mediaprojectioninfo = (MediaProjectionInfo)MediaProjectionInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return mediaprojectioninfo;
_L2:
            mediaprojectioninfo = null;
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
            return "android.media.projection.IMediaProjectionManager";
        }

        public boolean hasProjectionPermission(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjectionManager");
            parcel.writeInt(i);
            parcel.writeString(s);
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

        public boolean isValidMediaProjection(IMediaProjection imediaprojection)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjectionManager");
            if(imediaprojection == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imediaprojection.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
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
            imediaprojection;
            parcel1.recycle();
            parcel.recycle();
            throw imediaprojection;
        }

        public void removeCallback(IMediaProjectionWatcherCallback imediaprojectionwatchercallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjectionManager");
            if(imediaprojectionwatchercallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = imediaprojectionwatchercallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            imediaprojectionwatchercallback;
            parcel1.recycle();
            parcel.recycle();
            throw imediaprojectionwatchercallback;
        }

        public void stopActiveProjection()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjectionManager");
            mRemote.transact(5, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addCallback(IMediaProjectionWatcherCallback imediaprojectionwatchercallback)
        throws RemoteException;

    public abstract IMediaProjection createProjection(int i, String s, int j, boolean flag)
        throws RemoteException;

    public abstract MediaProjectionInfo getActiveProjectionInfo()
        throws RemoteException;

    public abstract boolean hasProjectionPermission(int i, String s)
        throws RemoteException;

    public abstract boolean isValidMediaProjection(IMediaProjection imediaprojection)
        throws RemoteException;

    public abstract void removeCallback(IMediaProjectionWatcherCallback imediaprojectionwatchercallback)
        throws RemoteException;

    public abstract void stopActiveProjection()
        throws RemoteException;
}
