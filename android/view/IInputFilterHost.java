// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.*;

// Referenced classes of package android.view:
//            InputEvent

public interface IInputFilterHost
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputFilterHost
    {

        public static IInputFilterHost asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.view.IInputFilterHost");
            if(iinterface != null && (iinterface instanceof IInputFilterHost))
                return (IInputFilterHost)iinterface;
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
                parcel1.writeString("android.view.IInputFilterHost");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.view.IInputFilterHost");
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (InputEvent)InputEvent.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            sendInputEvent(parcel1, parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "android.view.IInputFilterHost";
        static final int TRANSACTION_sendInputEvent = 1;

        public Stub()
        {
            attachInterface(this, "android.view.IInputFilterHost");
        }
    }

    private static class Stub.Proxy
        implements IInputFilterHost
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.view.IInputFilterHost";
        }

        public void sendInputEvent(InputEvent inputevent, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.view.IInputFilterHost");
            if(inputevent == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            inputevent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            inputevent;
            parcel.recycle();
            throw inputevent;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void sendInputEvent(InputEvent inputevent, int i)
        throws RemoteException;
}
