// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.content.pm.ParceledListSlice;
import android.os.*;

public interface IPrinterDiscoveryObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrinterDiscoveryObserver
    {

        public static IPrinterDiscoveryObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.IPrinterDiscoveryObserver");
            if(iinterface != null && (iinterface instanceof IPrinterDiscoveryObserver))
                return (IPrinterDiscoveryObserver)iinterface;
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
                parcel1.writeString("android.print.IPrinterDiscoveryObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.IPrinterDiscoveryObserver");
                if(parcel.readInt() != 0)
                    parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPrintersAdded(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.print.IPrinterDiscoveryObserver");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onPrintersRemoved(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.print.IPrinterDiscoveryObserver";
        static final int TRANSACTION_onPrintersAdded = 1;
        static final int TRANSACTION_onPrintersRemoved = 2;

        public Stub()
        {
            attachInterface(this, "android.print.IPrinterDiscoveryObserver");
        }
    }

    private static class Stub.Proxy
        implements IPrinterDiscoveryObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.IPrinterDiscoveryObserver";
        }

        public void onPrintersAdded(ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrinterDiscoveryObserver");
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel.recycle();
            throw parceledlistslice;
        }

        public void onPrintersRemoved(ParceledListSlice parceledlistslice)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrinterDiscoveryObserver");
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel.recycle();
            throw parceledlistslice;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onPrintersAdded(ParceledListSlice parceledlistslice)
        throws RemoteException;

    public abstract void onPrintersRemoved(ParceledListSlice parceledlistslice)
        throws RemoteException;
}
