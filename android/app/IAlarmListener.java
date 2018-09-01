// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.os.*;

// Referenced classes of package android.app:
//            IAlarmCompleteListener

public interface IAlarmListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IAlarmListener
    {

        public static IAlarmListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IAlarmListener");
            if(iinterface != null && (iinterface instanceof IAlarmListener))
                return (IAlarmListener)iinterface;
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
                parcel1.writeString("android.app.IAlarmListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IAlarmListener");
                doAlarm(IAlarmCompleteListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.app.IAlarmListener";
        static final int TRANSACTION_doAlarm = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IAlarmListener");
        }
    }

    private static class Stub.Proxy
        implements IAlarmListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void doAlarm(IAlarmCompleteListener ialarmcompletelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IAlarmListener");
            if(ialarmcompletelistener == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ialarmcompletelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ialarmcompletelistener;
            parcel.recycle();
            throw ialarmcompletelistener;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IAlarmListener";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void doAlarm(IAlarmCompleteListener ialarmcompletelistener)
        throws RemoteException;
}
