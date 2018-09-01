// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech;

import android.content.Intent;
import android.os.*;

// Referenced classes of package android.speech:
//            IRecognitionListener

public interface IRecognitionService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRecognitionService
    {

        public static IRecognitionService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.speech.IRecognitionService");
            if(iinterface != null && (iinterface instanceof IRecognitionService))
                return (IRecognitionService)iinterface;
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
                parcel1.writeString("android.speech.IRecognitionService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.speech.IRecognitionService");
                if(parcel.readInt() != 0)
                    parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                startListening(parcel1, IRecognitionListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.speech.IRecognitionService");
                stopListening(IRecognitionListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.speech.IRecognitionService");
                cancel(IRecognitionListener.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.speech.IRecognitionService";
        static final int TRANSACTION_cancel = 3;
        static final int TRANSACTION_startListening = 1;
        static final int TRANSACTION_stopListening = 2;

        public Stub()
        {
            attachInterface(this, "android.speech.IRecognitionService");
        }
    }

    private static class Stub.Proxy
        implements IRecognitionService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancel(IRecognitionListener irecognitionlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionService");
            if(irecognitionlistener == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = irecognitionlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            irecognitionlistener;
            parcel.recycle();
            throw irecognitionlistener;
        }

        public String getInterfaceDescriptor()
        {
            return "android.speech.IRecognitionService";
        }

        public void startListening(Intent intent, IRecognitionListener irecognitionlistener)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionService");
            if(intent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L4:
            intent = obj;
            if(irecognitionlistener == null)
                break MISSING_BLOCK_LABEL_44;
            intent = irecognitionlistener.asBinder();
            parcel.writeStrongBinder(intent);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            intent;
            parcel.recycle();
            throw intent;
        }

        public void stopListening(IRecognitionListener irecognitionlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionService");
            if(irecognitionlistener == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = irecognitionlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            irecognitionlistener;
            parcel.recycle();
            throw irecognitionlistener;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void cancel(IRecognitionListener irecognitionlistener)
        throws RemoteException;

    public abstract void startListening(Intent intent, IRecognitionListener irecognitionlistener)
        throws RemoteException;

    public abstract void stopListening(IRecognitionListener irecognitionlistener)
        throws RemoteException;
}
