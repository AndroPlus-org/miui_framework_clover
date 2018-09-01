// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

// Referenced classes of package android.media:
//            RemoteDisplayState

public interface IRemoteDisplayCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRemoteDisplayCallback
    {

        public static IRemoteDisplayCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IRemoteDisplayCallback");
            if(iinterface != null && (iinterface instanceof IRemoteDisplayCallback))
                return (IRemoteDisplayCallback)iinterface;
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
                parcel1.writeString("android.media.IRemoteDisplayCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IRemoteDisplayCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (RemoteDisplayState)RemoteDisplayState.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onStateChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.IRemoteDisplayCallback";
        static final int TRANSACTION_onStateChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.media.IRemoteDisplayCallback");
        }
    }

    private static class Stub.Proxy
        implements IRemoteDisplayCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IRemoteDisplayCallback";
        }

        public void onStateChanged(RemoteDisplayState remotedisplaystate)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IRemoteDisplayCallback");
            if(remotedisplaystate == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            remotedisplaystate.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            remotedisplaystate;
            parcel.recycle();
            throw remotedisplaystate;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onStateChanged(RemoteDisplayState remotedisplaystate)
        throws RemoteException;
}
