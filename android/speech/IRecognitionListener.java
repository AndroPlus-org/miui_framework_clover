// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech;

import android.os.*;

public interface IRecognitionListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRecognitionListener
    {

        public static IRecognitionListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.speech.IRecognitionListener");
            if(iinterface != null && (iinterface instanceof IRecognitionListener))
                return (IRecognitionListener)iinterface;
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
                parcel1.writeString("android.speech.IRecognitionListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.speech.IRecognitionListener");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onReadyForSpeech(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.speech.IRecognitionListener");
                onBeginningOfSpeech();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.speech.IRecognitionListener");
                onRmsChanged(parcel.readFloat());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.speech.IRecognitionListener");
                onBufferReceived(parcel.createByteArray());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.speech.IRecognitionListener");
                onEndOfSpeech();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.speech.IRecognitionListener");
                onError(parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.speech.IRecognitionListener");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onResults(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.speech.IRecognitionListener");
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onPartialResults(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.speech.IRecognitionListener");
                i = parcel.readInt();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onEvent(i, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.speech.IRecognitionListener";
        static final int TRANSACTION_onBeginningOfSpeech = 2;
        static final int TRANSACTION_onBufferReceived = 4;
        static final int TRANSACTION_onEndOfSpeech = 5;
        static final int TRANSACTION_onError = 6;
        static final int TRANSACTION_onEvent = 9;
        static final int TRANSACTION_onPartialResults = 8;
        static final int TRANSACTION_onReadyForSpeech = 1;
        static final int TRANSACTION_onResults = 7;
        static final int TRANSACTION_onRmsChanged = 3;

        public Stub()
        {
            attachInterface(this, "android.speech.IRecognitionListener");
        }
    }

    private static class Stub.Proxy
        implements IRecognitionListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.speech.IRecognitionListener";
        }

        public void onBeginningOfSpeech()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionListener");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onBufferReceived(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionListener");
            parcel.writeByteArray(abyte0);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            abyte0;
            parcel.recycle();
            throw abyte0;
        }

        public void onEndOfSpeech()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionListener");
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onError(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionListener");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onEvent(int i, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionListener");
            parcel.writeInt(i);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void onPartialResults(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionListener");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void onReadyForSpeech(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionListener");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void onResults(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionListener");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void onRmsChanged(float f)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.speech.IRecognitionListener");
            parcel.writeFloat(f);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onBeginningOfSpeech()
        throws RemoteException;

    public abstract void onBufferReceived(byte abyte0[])
        throws RemoteException;

    public abstract void onEndOfSpeech()
        throws RemoteException;

    public abstract void onError(int i)
        throws RemoteException;

    public abstract void onEvent(int i, Bundle bundle)
        throws RemoteException;

    public abstract void onPartialResults(Bundle bundle)
        throws RemoteException;

    public abstract void onReadyForSpeech(Bundle bundle)
        throws RemoteException;

    public abstract void onResults(Bundle bundle)
        throws RemoteException;

    public abstract void onRmsChanged(float f)
        throws RemoteException;
}
