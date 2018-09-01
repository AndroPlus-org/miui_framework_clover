// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.autofill;

import android.os.*;
import android.text.TextUtils;

// Referenced classes of package android.service.autofill:
//            FillResponse

public interface IFillCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IFillCallback
    {

        public static IFillCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.autofill.IFillCallback");
            if(iinterface != null && (iinterface instanceof IFillCallback))
                return (IFillCallback)iinterface;
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
                parcel1.writeString("android.service.autofill.IFillCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.autofill.IFillCallback");
                onCancellable(android.os.ICancellationSignal.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.autofill.IFillCallback");
                if(parcel.readInt() != 0)
                    parcel = (FillResponse)FillResponse.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onSuccess(parcel);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.autofill.IFillCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onFailure(parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.service.autofill.IFillCallback";
        static final int TRANSACTION_onCancellable = 1;
        static final int TRANSACTION_onFailure = 3;
        static final int TRANSACTION_onSuccess = 2;

        public Stub()
        {
            attachInterface(this, "android.service.autofill.IFillCallback");
        }
    }

    private static class Stub.Proxy
        implements IFillCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.autofill.IFillCallback";
        }

        public void onCancellable(ICancellationSignal icancellationsignal)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.autofill.IFillCallback");
            if(icancellationsignal == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = icancellationsignal.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            icancellationsignal;
            parcel1.recycle();
            parcel.recycle();
            throw icancellationsignal;
        }

        public void onFailure(CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.autofill.IFillCallback");
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L1:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            charsequence;
            parcel1.recycle();
            parcel.recycle();
            throw charsequence;
        }

        public void onSuccess(FillResponse fillresponse)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.autofill.IFillCallback");
            if(fillresponse == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            fillresponse.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            fillresponse;
            parcel1.recycle();
            parcel.recycle();
            throw fillresponse;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onCancellable(ICancellationSignal icancellationsignal)
        throws RemoteException;

    public abstract void onFailure(CharSequence charsequence)
        throws RemoteException;

    public abstract void onSuccess(FillResponse fillresponse)
        throws RemoteException;
}
