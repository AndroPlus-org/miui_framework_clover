// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.*;
import android.text.TextUtils;

// Referenced classes of package android.print:
//            PageRange

public interface IWriteResultCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWriteResultCallback
    {

        public static IWriteResultCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.print.IWriteResultCallback");
            if(iinterface != null && (iinterface instanceof IWriteResultCallback))
                return (IWriteResultCallback)iinterface;
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
                parcel1.writeString("android.print.IWriteResultCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.print.IWriteResultCallback");
                onWriteStarted(android.os.ICancellationSignal.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.print.IWriteResultCallback");
                onWriteFinished((PageRange[])parcel.createTypedArray(PageRange.CREATOR), parcel.readInt());
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.print.IWriteResultCallback");
                if(parcel.readInt() != 0)
                    parcel1 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                onWriteFailed(parcel1, parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.print.IWriteResultCallback");
                onWriteCanceled(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.print.IWriteResultCallback";
        static final int TRANSACTION_onWriteCanceled = 4;
        static final int TRANSACTION_onWriteFailed = 3;
        static final int TRANSACTION_onWriteFinished = 2;
        static final int TRANSACTION_onWriteStarted = 1;

        public Stub()
        {
            attachInterface(this, "android.print.IWriteResultCallback");
        }
    }

    private static class Stub.Proxy
        implements IWriteResultCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.print.IWriteResultCallback";
        }

        public void onWriteCanceled(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IWriteResultCallback");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onWriteFailed(CharSequence charsequence, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IWriteResultCallback");
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

        public void onWriteFinished(PageRange apagerange[], int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IWriteResultCallback");
            parcel.writeTypedArray(apagerange, 0);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            apagerange;
            parcel.recycle();
            throw apagerange;
        }

        public void onWriteStarted(ICancellationSignal icancellationsignal, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.print.IWriteResultCallback");
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


    public abstract void onWriteCanceled(int i)
        throws RemoteException;

    public abstract void onWriteFailed(CharSequence charsequence, int i)
        throws RemoteException;

    public abstract void onWriteFinished(PageRange apagerange[], int i)
        throws RemoteException;

    public abstract void onWriteStarted(ICancellationSignal icancellationsignal, int i)
        throws RemoteException;
}
