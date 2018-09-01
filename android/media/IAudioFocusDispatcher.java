// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

public interface IAudioFocusDispatcher
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAudioFocusDispatcher
    {

        public static IAudioFocusDispatcher asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IAudioFocusDispatcher");
            if(iinterface != null && (iinterface instanceof IAudioFocusDispatcher))
                return (IAudioFocusDispatcher)iinterface;
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
                parcel1.writeString("android.media.IAudioFocusDispatcher");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IAudioFocusDispatcher");
                dispatchAudioFocusChange(parcel.readInt(), parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.media.IAudioFocusDispatcher";
        static final int TRANSACTION_dispatchAudioFocusChange = 1;

        public Stub()
        {
            attachInterface(this, "android.media.IAudioFocusDispatcher");
        }
    }

    private static class Stub.Proxy
        implements IAudioFocusDispatcher
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void dispatchAudioFocusChange(int i, String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioFocusDispatcher");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IAudioFocusDispatcher";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void dispatchAudioFocusChange(int i, String s)
        throws RemoteException;
}
