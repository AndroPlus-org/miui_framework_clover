// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.os.*;
import android.view.KeyEvent;

public interface IOnMediaKeyListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IOnMediaKeyListener
    {

        public static IOnMediaKeyListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.session.IOnMediaKeyListener");
            if(iinterface != null && (iinterface instanceof IOnMediaKeyListener))
                return (IOnMediaKeyListener)iinterface;
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
                parcel1.writeString("android.media.session.IOnMediaKeyListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.session.IOnMediaKeyListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (KeyEvent)KeyEvent.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            if(parcel.readInt() != 0)
                parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onMediaKey(parcel1, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.session.IOnMediaKeyListener";
        static final int TRANSACTION_onMediaKey = 1;

        public Stub()
        {
            attachInterface(this, "android.media.session.IOnMediaKeyListener");
        }
    }

    private static class Stub.Proxy
        implements IOnMediaKeyListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.session.IOnMediaKeyListener";
        }

        public void onMediaKey(KeyEvent keyevent, ResultReceiver resultreceiver)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.IOnMediaKeyListener");
            if(keyevent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            keyevent.writeToParcel(parcel, 0);
_L3:
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            keyevent;
            parcel.recycle();
            throw keyevent;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onMediaKey(KeyEvent keyevent, ResultReceiver resultreceiver)
        throws RemoteException;
}
