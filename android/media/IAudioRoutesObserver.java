// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.*;

// Referenced classes of package android.media:
//            AudioRoutesInfo

public interface IAudioRoutesObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAudioRoutesObserver
    {

        public static IAudioRoutesObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.IAudioRoutesObserver");
            if(iinterface != null && (iinterface instanceof IAudioRoutesObserver))
                return (IAudioRoutesObserver)iinterface;
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
                parcel1.writeString("android.media.IAudioRoutesObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.IAudioRoutesObserver");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (AudioRoutesInfo)AudioRoutesInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            dispatchAudioRoutesChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.IAudioRoutesObserver";
        static final int TRANSACTION_dispatchAudioRoutesChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.media.IAudioRoutesObserver");
        }
    }

    private static class Stub.Proxy
        implements IAudioRoutesObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void dispatchAudioRoutesChanged(AudioRoutesInfo audioroutesinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.IAudioRoutesObserver");
            if(audioroutesinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            audioroutesinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            audioroutesinfo;
            parcel.recycle();
            throw audioroutesinfo;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.IAudioRoutesObserver";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void dispatchAudioRoutesChanged(AudioRoutesInfo audioroutesinfo)
        throws RemoteException;
}
