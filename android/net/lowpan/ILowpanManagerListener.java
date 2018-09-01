// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.os.*;

// Referenced classes of package android.net.lowpan:
//            ILowpanInterface

public interface ILowpanManagerListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILowpanManagerListener
    {

        public static ILowpanManagerListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.lowpan.ILowpanManagerListener");
            if(iinterface != null && (iinterface instanceof ILowpanManagerListener))
                return (ILowpanManagerListener)iinterface;
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
                parcel1.writeString("android.net.lowpan.ILowpanManagerListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.lowpan.ILowpanManagerListener");
                onInterfaceAdded(ILowpanInterface.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.lowpan.ILowpanManagerListener");
                onInterfaceRemoved(ILowpanInterface.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.lowpan.ILowpanManagerListener";
        static final int TRANSACTION_onInterfaceAdded = 1;
        static final int TRANSACTION_onInterfaceRemoved = 2;

        public Stub()
        {
            attachInterface(this, "android.net.lowpan.ILowpanManagerListener");
        }
    }

    private static class Stub.Proxy
        implements ILowpanManagerListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.lowpan.ILowpanManagerListener";
        }

        public void onInterfaceAdded(ILowpanInterface ilowpaninterface)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanManagerListener");
            if(ilowpaninterface == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ilowpaninterface.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            ilowpaninterface;
            parcel.recycle();
            throw ilowpaninterface;
        }

        public void onInterfaceRemoved(ILowpanInterface ilowpaninterface)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.lowpan.ILowpanManagerListener");
            if(ilowpaninterface == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ilowpaninterface.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            ilowpaninterface;
            parcel.recycle();
            throw ilowpaninterface;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onInterfaceAdded(ILowpanInterface ilowpaninterface)
        throws RemoteException;

    public abstract void onInterfaceRemoved(ILowpanInterface ilowpaninterface)
        throws RemoteException;
}
