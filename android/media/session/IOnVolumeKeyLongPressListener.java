// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.os.*;
import android.view.KeyEvent;

public interface IOnVolumeKeyLongPressListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOnVolumeKeyLongPressListener
    {

        public static IOnVolumeKeyLongPressListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.session.IOnVolumeKeyLongPressListener");
            if(iinterface != null && (iinterface instanceof IOnVolumeKeyLongPressListener))
                return (IOnVolumeKeyLongPressListener)iinterface;
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
                parcel1.writeString("android.media.session.IOnVolumeKeyLongPressListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.session.IOnVolumeKeyLongPressListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (KeyEvent)KeyEvent.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onVolumeKeyLongPress(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.session.IOnVolumeKeyLongPressListener";
        static final int TRANSACTION_onVolumeKeyLongPress = 1;

        public Stub()
        {
            attachInterface(this, "android.media.session.IOnVolumeKeyLongPressListener");
        }
    }

    private static class Stub.Proxy
        implements IOnVolumeKeyLongPressListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.session.IOnVolumeKeyLongPressListener";
        }

        public void onVolumeKeyLongPress(KeyEvent keyevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.IOnVolumeKeyLongPressListener");
            if(keyevent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            keyevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            keyevent;
            parcel.recycle();
            throw keyevent;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onVolumeKeyLongPress(KeyEvent keyevent)
        throws RemoteException;
}
