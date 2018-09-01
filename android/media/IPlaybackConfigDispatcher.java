// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;
import java.util.List;

// Referenced classes of package android.media:
//            AudioPlaybackConfiguration

public interface IPlaybackConfigDispatcher
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPlaybackConfigDispatcher
    {

        public static IPlaybackConfigDispatcher asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IPlaybackConfigDispatcher");
            if(iinterface != null && (iinterface instanceof IPlaybackConfigDispatcher))
                return (IPlaybackConfigDispatcher)iinterface;
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
                parcel1.writeString("android.media.IPlaybackConfigDispatcher");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IPlaybackConfigDispatcher");
                parcel1 = parcel.createTypedArrayList(AudioPlaybackConfiguration.CREATOR);
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            dispatchPlaybackConfigChange(parcel1, flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.IPlaybackConfigDispatcher";
        static final int TRANSACTION_dispatchPlaybackConfigChange = 1;

        public Stub()
        {
            attachInterface(this, "android.media.IPlaybackConfigDispatcher");
        }
    }

    private static class Stub.Proxy
        implements IPlaybackConfigDispatcher
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void dispatchPlaybackConfigChange(List list, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IPlaybackConfigDispatcher");
            parcel.writeTypedList(list);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            list;
            parcel.recycle();
            throw list;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IPlaybackConfigDispatcher";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void dispatchPlaybackConfigChange(List list, boolean flag)
        throws RemoteException;
}
