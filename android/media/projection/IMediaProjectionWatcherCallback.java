// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.projection;

import android.os.*;

// Referenced classes of package android.media.projection:
//            MediaProjectionInfo

public interface IMediaProjectionWatcherCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMediaProjectionWatcherCallback
    {

        public static IMediaProjectionWatcherCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.projection.IMediaProjectionWatcherCallback");
            if(iinterface != null && (iinterface instanceof IMediaProjectionWatcherCallback))
                return (IMediaProjectionWatcherCallback)iinterface;
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
                parcel1.writeString("android.media.projection.IMediaProjectionWatcherCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.projection.IMediaProjectionWatcherCallback");
                if(parcel.readInt() != 0)
                    parcel = (MediaProjectionInfo)MediaProjectionInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onStart(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.projection.IMediaProjectionWatcherCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (MediaProjectionInfo)MediaProjectionInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onStop(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.projection.IMediaProjectionWatcherCallback";
        static final int TRANSACTION_onStart = 1;
        static final int TRANSACTION_onStop = 2;

        public Stub()
        {
            attachInterface(this, "android.media.projection.IMediaProjectionWatcherCallback");
        }
    }

    private static class Stub.Proxy
        implements IMediaProjectionWatcherCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.projection.IMediaProjectionWatcherCallback";
        }

        public void onStart(MediaProjectionInfo mediaprojectioninfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjectionWatcherCallback");
            if(mediaprojectioninfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            mediaprojectioninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            mediaprojectioninfo;
            parcel.recycle();
            throw mediaprojectioninfo;
        }

        public void onStop(MediaProjectionInfo mediaprojectioninfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.projection.IMediaProjectionWatcherCallback");
            if(mediaprojectioninfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            mediaprojectioninfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            mediaprojectioninfo;
            parcel.recycle();
            throw mediaprojectioninfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onStart(MediaProjectionInfo mediaprojectioninfo)
        throws RemoteException;

    public abstract void onStop(MediaProjectionInfo mediaprojectioninfo)
        throws RemoteException;
}
