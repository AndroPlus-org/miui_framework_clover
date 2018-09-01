// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.soundtrigger;

import android.os.*;

public interface IRecognitionStatusCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRecognitionStatusCallback
    {

        public static IRecognitionStatusCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.soundtrigger.IRecognitionStatusCallback");
            if(iinterface != null && (iinterface instanceof IRecognitionStatusCallback))
                return (IRecognitionStatusCallback)iinterface;
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
                parcel1.writeString("android.hardware.soundtrigger.IRecognitionStatusCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.soundtrigger.IRecognitionStatusCallback");
                if(parcel.readInt() != 0)
                    parcel = (SoundTrigger.KeyphraseRecognitionEvent)SoundTrigger.KeyphraseRecognitionEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onKeyphraseDetected(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.soundtrigger.IRecognitionStatusCallback");
                if(parcel.readInt() != 0)
                    parcel = (SoundTrigger.GenericRecognitionEvent)SoundTrigger.GenericRecognitionEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onGenericSoundTriggerDetected(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.soundtrigger.IRecognitionStatusCallback");
                onError(parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.soundtrigger.IRecognitionStatusCallback");
                onRecognitionPaused();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.soundtrigger.IRecognitionStatusCallback");
                onRecognitionResumed();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.hardware.soundtrigger.IRecognitionStatusCallback";
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onGenericSoundTriggerDetected = 2;
        static final int TRANSACTION_onKeyphraseDetected = 1;
        static final int TRANSACTION_onRecognitionPaused = 4;
        static final int TRANSACTION_onRecognitionResumed = 5;

        public Stub()
        {
            attachInterface(this, "android.hardware.soundtrigger.IRecognitionStatusCallback");
        }
    }

    private static class Stub.Proxy
        implements IRecognitionStatusCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.soundtrigger.IRecognitionStatusCallback";
        }

        public void onError(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.soundtrigger.IRecognitionStatusCallback");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onGenericSoundTriggerDetected(SoundTrigger.GenericRecognitionEvent genericrecognitionevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.soundtrigger.IRecognitionStatusCallback");
            if(genericrecognitionevent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            genericrecognitionevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            genericrecognitionevent;
            parcel.recycle();
            throw genericrecognitionevent;
        }

        public void onKeyphraseDetected(SoundTrigger.KeyphraseRecognitionEvent keyphraserecognitionevent)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.soundtrigger.IRecognitionStatusCallback");
            if(keyphraserecognitionevent == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            keyphraserecognitionevent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            keyphraserecognitionevent;
            parcel.recycle();
            throw keyphraserecognitionevent;
        }

        public void onRecognitionPaused()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.soundtrigger.IRecognitionStatusCallback");
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onRecognitionResumed()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.soundtrigger.IRecognitionStatusCallback");
            mRemote.transact(5, parcel, null, 1);
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


    public abstract void onError(int i)
        throws RemoteException;

    public abstract void onGenericSoundTriggerDetected(SoundTrigger.GenericRecognitionEvent genericrecognitionevent)
        throws RemoteException;

    public abstract void onKeyphraseDetected(SoundTrigger.KeyphraseRecognitionEvent keyphraserecognitionevent)
        throws RemoteException;

    public abstract void onRecognitionPaused()
        throws RemoteException;

    public abstract void onRecognitionResumed()
        throws RemoteException;
}
