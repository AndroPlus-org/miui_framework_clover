// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.voice;

import android.os.*;

public interface IVoiceInteractionService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVoiceInteractionService
    {

        public static IVoiceInteractionService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.voice.IVoiceInteractionService");
            if(iinterface != null && (iinterface instanceof IVoiceInteractionService))
                return (IVoiceInteractionService)iinterface;
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
                parcel1.writeString("android.service.voice.IVoiceInteractionService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionService");
                ready();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionService");
                soundModelsChanged();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionService");
                shutdown();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionService");
                launchVoiceAssistFromKeyguard();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.voice.IVoiceInteractionService";
        static final int TRANSACTION_launchVoiceAssistFromKeyguard = 4;
        static final int TRANSACTION_ready = 1;
        static final int TRANSACTION_shutdown = 3;
        static final int TRANSACTION_soundModelsChanged = 2;

        public Stub()
        {
            attachInterface(this, "android.service.voice.IVoiceInteractionService");
        }
    }

    private static class Stub.Proxy
        implements IVoiceInteractionService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.voice.IVoiceInteractionService";
        }

        public void launchVoiceAssistFromKeyguard()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionService");
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void ready()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionService");
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void shutdown()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionService");
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void soundModelsChanged()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionService");
            mRemote.transact(2, parcel, null, 1);
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


    public abstract void launchVoiceAssistFromKeyguard()
        throws RemoteException;

    public abstract void ready()
        throws RemoteException;

    public abstract void shutdown()
        throws RemoteException;

    public abstract void soundModelsChanged()
        throws RemoteException;
}
