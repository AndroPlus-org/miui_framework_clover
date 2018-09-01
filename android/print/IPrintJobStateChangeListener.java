// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.*;

// Referenced classes of package android.print:
//            PrintJobId

public interface IPrintJobStateChangeListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrintJobStateChangeListener
    {

        public static IPrintJobStateChangeListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.IPrintJobStateChangeListener");
            if(iinterface != null && (iinterface instanceof IPrintJobStateChangeListener))
                return (IPrintJobStateChangeListener)iinterface;
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
                parcel1.writeString("android.print.IPrintJobStateChangeListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.IPrintJobStateChangeListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (PrintJobId)PrintJobId.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onPrintJobStateChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.print.IPrintJobStateChangeListener";
        static final int TRANSACTION_onPrintJobStateChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.print.IPrintJobStateChangeListener");
        }
    }

    private static class Stub.Proxy
        implements IPrintJobStateChangeListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.IPrintJobStateChangeListener";
        }

        public void onPrintJobStateChanged(PrintJobId printjobid)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintJobStateChangeListener");
            if(printjobid == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            printjobid.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printjobid;
            parcel.recycle();
            throw printjobid;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onPrintJobStateChanged(PrintJobId printjobid)
        throws RemoteException;
}
