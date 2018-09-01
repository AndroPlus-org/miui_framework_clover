// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.*;
import android.text.TextUtils;

// Referenced classes of package android.print:
//            PrintDocumentInfo

public interface ILayoutResultCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ILayoutResultCallback
    {

        public static ILayoutResultCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.ILayoutResultCallback");
            if(iinterface != null && (iinterface instanceof ILayoutResultCallback))
                return (ILayoutResultCallback)iinterface;
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
                parcel1.writeString("android.print.ILayoutResultCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.ILayoutResultCallback");
                onLayoutStarted(android.os.ICancellationSignal.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.print.ILayoutResultCallback");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel1 = (PrintDocumentInfo)PrintDocumentInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                onLayoutFinished(parcel1, flag, parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.print.ILayoutResultCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onLayoutFailed(parcel1, parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.print.ILayoutResultCallback");
                onLayoutCanceled(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.print.ILayoutResultCallback";
        static final int TRANSACTION_onLayoutCanceled = 4;
        static final int TRANSACTION_onLayoutFailed = 3;
        static final int TRANSACTION_onLayoutFinished = 2;
        static final int TRANSACTION_onLayoutStarted = 1;

        public Stub()
        {
            attachInterface(this, "android.print.ILayoutResultCallback");
        }
    }

    private static class Stub.Proxy
        implements ILayoutResultCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.ILayoutResultCallback";
        }

        public void onLayoutCanceled(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.ILayoutResultCallback");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onLayoutFailed(CharSequence charsequence, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.ILayoutResultCallback");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel.recycle();
            throw charsequence;
        }

        public void onLayoutFinished(PrintDocumentInfo printdocumentinfo, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            j = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.ILayoutResultCallback");
            if(printdocumentinfo == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            printdocumentinfo.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            printdocumentinfo;
            parcel.recycle();
            throw printdocumentinfo;
        }

        public void onLayoutStarted(ICancellationSignal icancellationsignal, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.ILayoutResultCallback");
            if(icancellationsignal == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = icancellationsignal.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            icancellationsignal;
            parcel.recycle();
            throw icancellationsignal;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onLayoutCanceled(int i)
        throws RemoteException;

    public abstract void onLayoutFailed(CharSequence charsequence, int i)
        throws RemoteException;

    public abstract void onLayoutFinished(PrintDocumentInfo printdocumentinfo, boolean flag, int i)
        throws RemoteException;

    public abstract void onLayoutStarted(ICancellationSignal icancellationsignal, int i)
        throws RemoteException;
}
