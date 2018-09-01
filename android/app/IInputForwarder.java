// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;
import android.view.InputEvent;

public interface IInputForwarder
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputForwarder
    {

        public static IInputForwarder asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IInputForwarder");
            if(iinterface != null && (iinterface instanceof IInputForwarder))
                return (IInputForwarder)iinterface;
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
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.app.IInputForwarder");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IInputForwarder");
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                parcel = (InputEvent)InputEvent.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            flag1 = forwardEvent(parcel);
            parcel1.writeNoException();
            i = ((flag) ? 1 : 0);
            if(flag1)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.app.IInputForwarder";
        static final int TRANSACTION_forwardEvent = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IInputForwarder");
        }
    }

    private static class Stub.Proxy
        implements IInputForwarder
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean forwardEvent(InputEvent inputevent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IInputForwarder");
            if(inputevent == null)
                break MISSING_BLOCK_LABEL_72;
            parcel.writeInt(1);
            inputevent.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            inputevent;
            parcel1.recycle();
            parcel.recycle();
            throw inputevent;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IInputForwarder";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean forwardEvent(InputEvent inputevent)
        throws RemoteException;
}
