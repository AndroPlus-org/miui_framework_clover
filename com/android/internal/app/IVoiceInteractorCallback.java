// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.*;

// Referenced classes of package com.android.internal.app:
//            IVoiceInteractorRequest

public interface IVoiceInteractorCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVoiceInteractorCallback
    {

        public static IVoiceInteractorCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IVoiceInteractorCallback");
            if(iinterface != null && (iinterface instanceof IVoiceInteractorCallback))
                return (IVoiceInteractorCallback)iinterface;
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
                parcel1.writeString("com.android.internal.app.IVoiceInteractorCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractorCallback");
                parcel1 = IVoiceInteractorRequest.Stub.asInterface(parcel.readStrongBinder());
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                deliverConfirmationResult(parcel1, flag, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractorCallback");
                IVoiceInteractorRequest ivoiceinteractorrequest = IVoiceInteractorRequest.Stub.asInterface(parcel.readStrongBinder());
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                parcel1 = (android.app.VoiceInteractor.PickOptionRequest.Option[])parcel.createTypedArray(android.app.VoiceInteractor.PickOptionRequest.Option.CREATOR);
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                deliverPickOptionResult(ivoiceinteractorrequest, flag1, parcel1, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractorCallback");
                parcel1 = IVoiceInteractorRequest.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                deliverCompleteVoiceResult(parcel1, parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractorCallback");
                parcel1 = IVoiceInteractorRequest.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                deliverAbortVoiceResult(parcel1, parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractorCallback");
                parcel1 = IVoiceInteractorRequest.Stub.asInterface(parcel.readStrongBinder());
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                deliverCommandResult(parcel1, flag2, parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractorCallback");
                deliverCancel(IVoiceInteractorRequest.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IVoiceInteractorCallback";
        static final int TRANSACTION_deliverAbortVoiceResult = 4;
        static final int TRANSACTION_deliverCancel = 6;
        static final int TRANSACTION_deliverCommandResult = 5;
        static final int TRANSACTION_deliverCompleteVoiceResult = 3;
        static final int TRANSACTION_deliverConfirmationResult = 1;
        static final int TRANSACTION_deliverPickOptionResult = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IVoiceInteractorCallback");
        }
    }

    private static class Stub.Proxy
        implements IVoiceInteractorCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void deliverAbortVoiceResult(IVoiceInteractorRequest ivoiceinteractorrequest, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractorCallback");
            if(ivoiceinteractorrequest == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = ivoiceinteractorrequest.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ivoiceinteractorrequest;
            parcel.recycle();
            throw ivoiceinteractorrequest;
        }

        public void deliverCancel(IVoiceInteractorRequest ivoiceinteractorrequest)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractorCallback");
            if(ivoiceinteractorrequest == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = ivoiceinteractorrequest.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            ivoiceinteractorrequest;
            parcel.recycle();
            throw ivoiceinteractorrequest;
        }

        public void deliverCommandResult(IVoiceInteractorRequest ivoiceinteractorrequest, boolean flag, Bundle bundle)
            throws RemoteException
        {
            int i;
            IBinder ibinder;
            Parcel parcel;
            i = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractorCallback");
            if(ivoiceinteractorrequest == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ivoiceinteractorrequest.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_92;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ivoiceinteractorrequest;
            parcel.recycle();
            throw ivoiceinteractorrequest;
        }

        public void deliverCompleteVoiceResult(IVoiceInteractorRequest ivoiceinteractorrequest, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractorCallback");
            if(ivoiceinteractorrequest == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = ivoiceinteractorrequest.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ivoiceinteractorrequest;
            parcel.recycle();
            throw ivoiceinteractorrequest;
        }

        public void deliverConfirmationResult(IVoiceInteractorRequest ivoiceinteractorrequest, boolean flag, Bundle bundle)
            throws RemoteException
        {
            IBinder ibinder;
            int i;
            Parcel parcel;
            ibinder = null;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractorCallback");
            if(ivoiceinteractorrequest == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ivoiceinteractorrequest.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_92;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ivoiceinteractorrequest;
            parcel.recycle();
            throw ivoiceinteractorrequest;
        }

        public void deliverPickOptionResult(IVoiceInteractorRequest ivoiceinteractorrequest, boolean flag, android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
            throws RemoteException
        {
            int i;
            IBinder ibinder;
            Parcel parcel;
            i = 1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractorCallback");
            if(ivoiceinteractorrequest == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ivoiceinteractorrequest.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcel.writeTypedArray(aoption, 0);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_101;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ivoiceinteractorrequest;
            parcel.recycle();
            throw ivoiceinteractorrequest;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IVoiceInteractorCallback";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void deliverAbortVoiceResult(IVoiceInteractorRequest ivoiceinteractorrequest, Bundle bundle)
        throws RemoteException;

    public abstract void deliverCancel(IVoiceInteractorRequest ivoiceinteractorrequest)
        throws RemoteException;

    public abstract void deliverCommandResult(IVoiceInteractorRequest ivoiceinteractorrequest, boolean flag, Bundle bundle)
        throws RemoteException;

    public abstract void deliverCompleteVoiceResult(IVoiceInteractorRequest ivoiceinteractorrequest, Bundle bundle)
        throws RemoteException;

    public abstract void deliverConfirmationResult(IVoiceInteractorRequest ivoiceinteractorrequest, boolean flag, Bundle bundle)
        throws RemoteException;

    public abstract void deliverPickOptionResult(IVoiceInteractorRequest ivoiceinteractorrequest, boolean flag, android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
        throws RemoteException;
}
