// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.*;

// Referenced classes of package android.print:
//            PrintAttributes, ILayoutResultCallback, IPrintDocumentAdapterObserver, PageRange, 
//            IWriteResultCallback

public interface IPrintDocumentAdapter
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPrintDocumentAdapter
    {

        public static IPrintDocumentAdapter asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.IPrintDocumentAdapter");
            if(iinterface != null && (iinterface instanceof IPrintDocumentAdapter))
                return (IPrintDocumentAdapter)iinterface;
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
                parcel1.writeString("android.print.IPrintDocumentAdapter");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.IPrintDocumentAdapter");
                setObserver(IPrintDocumentAdapterObserver.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.print.IPrintDocumentAdapter");
                start();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.print.IPrintDocumentAdapter");
                PrintAttributes printattributes;
                ILayoutResultCallback ilayoutresultcallback;
                Bundle bundle;
                if(parcel.readInt() != 0)
                    parcel1 = (PrintAttributes)PrintAttributes.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    printattributes = (PrintAttributes)PrintAttributes.CREATOR.createFromParcel(parcel);
                else
                    printattributes = null;
                ilayoutresultcallback = ILayoutResultCallback.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                layout(parcel1, printattributes, ilayoutresultcallback, bundle, parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.print.IPrintDocumentAdapter");
                PageRange apagerange[] = (PageRange[])parcel.createTypedArray(PageRange.CREATOR);
                if(parcel.readInt() != 0)
                    parcel1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                write(apagerange, parcel1, IWriteResultCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.print.IPrintDocumentAdapter");
                finish();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.print.IPrintDocumentAdapter");
                kill(parcel.readString());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.print.IPrintDocumentAdapter";
        static final int TRANSACTION_finish = 5;
        static final int TRANSACTION_kill = 6;
        static final int TRANSACTION_layout = 3;
        static final int TRANSACTION_setObserver = 1;
        static final int TRANSACTION_start = 2;
        static final int TRANSACTION_write = 4;

        public Stub()
        {
            attachInterface(this, "android.print.IPrintDocumentAdapter");
        }
    }

    private static class Stub.Proxy
        implements IPrintDocumentAdapter
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void finish()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintDocumentAdapter");
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.IPrintDocumentAdapter";
        }

        public void kill(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintDocumentAdapter");
            parcel.writeString(s);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void layout(PrintAttributes printattributes, PrintAttributes printattributes1, ILayoutResultCallback ilayoutresultcallback, Bundle bundle, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintDocumentAdapter");
            if(printattributes == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            printattributes.writeToParcel(parcel, 0);
_L5:
            if(printattributes1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            printattributes1.writeToParcel(parcel, 0);
_L6:
            printattributes = obj;
            if(ilayoutresultcallback == null)
                break MISSING_BLOCK_LABEL_63;
            printattributes = ilayoutresultcallback.asBinder();
            parcel.writeStrongBinder(printattributes);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_142;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L7:
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            printattributes;
            parcel.recycle();
            throw printattributes;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void setObserver(IPrintDocumentAdapterObserver iprintdocumentadapterobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintDocumentAdapter");
            if(iprintdocumentadapterobserver == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = iprintdocumentadapterobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            iprintdocumentadapterobserver;
            parcel.recycle();
            throw iprintdocumentadapterobserver;
        }

        public void start()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintDocumentAdapter");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void write(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, IWriteResultCallback iwriteresultcallback, int i)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IPrintDocumentAdapter");
            parcel.writeTypedArray(apagerange, 0);
            if(parcelfiledescriptor == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parcelfiledescriptor.writeToParcel(parcel, 0);
_L4:
            apagerange = obj;
            if(iwriteresultcallback == null)
                break MISSING_BLOCK_LABEL_53;
            apagerange = iwriteresultcallback.asBinder();
            parcel.writeStrongBinder(apagerange);
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            apagerange;
            parcel.recycle();
            throw apagerange;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void finish()
        throws RemoteException;

    public abstract void kill(String s)
        throws RemoteException;

    public abstract void layout(PrintAttributes printattributes, PrintAttributes printattributes1, ILayoutResultCallback ilayoutresultcallback, Bundle bundle, int i)
        throws RemoteException;

    public abstract void setObserver(IPrintDocumentAdapterObserver iprintdocumentadapterobserver)
        throws RemoteException;

    public abstract void start()
        throws RemoteException;

    public abstract void write(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, IWriteResultCallback iwriteresultcallback, int i)
        throws RemoteException;
}
