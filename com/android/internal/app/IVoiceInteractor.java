// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.os.*;

// Referenced classes of package com.android.internal.app:
//            IVoiceInteractorCallback, IVoiceInteractorRequest

public interface IVoiceInteractor
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVoiceInteractor
    {

        public static IVoiceInteractor asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IVoiceInteractor");
            if(iinterface != null && (iinterface instanceof IVoiceInteractor))
                return (IVoiceInteractor)iinterface;
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
                parcel1.writeString("com.android.internal.app.IVoiceInteractor");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractor");
                String s = parcel.readString();
                IVoiceInteractorCallback ivoiceinteractorcallback = IVoiceInteractorCallback.Stub.asInterface(parcel.readStrongBinder());
                android.app.VoiceInteractor.Prompt prompt;
                if(parcel.readInt() != 0)
                    prompt = (android.app.VoiceInteractor.Prompt)android.app.VoiceInteractor.Prompt.CREATOR.createFromParcel(parcel);
                else
                    prompt = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = startConfirmation(s, ivoiceinteractorcallback, prompt, parcel);
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractor");
                String s1 = parcel.readString();
                IVoiceInteractorCallback ivoiceinteractorcallback1 = IVoiceInteractorCallback.Stub.asInterface(parcel.readStrongBinder());
                android.app.VoiceInteractor.Prompt prompt1;
                android.app.VoiceInteractor.PickOptionRequest.Option aoption[];
                if(parcel.readInt() != 0)
                    prompt1 = (android.app.VoiceInteractor.Prompt)android.app.VoiceInteractor.Prompt.CREATOR.createFromParcel(parcel);
                else
                    prompt1 = null;
                aoption = (android.app.VoiceInteractor.PickOptionRequest.Option[])parcel.createTypedArray(android.app.VoiceInteractor.PickOptionRequest.Option.CREATOR);
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = startPickOption(s1, ivoiceinteractorcallback1, prompt1, aoption, parcel);
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractor");
                String s2 = parcel.readString();
                IVoiceInteractorCallback ivoiceinteractorcallback2 = IVoiceInteractorCallback.Stub.asInterface(parcel.readStrongBinder());
                android.app.VoiceInteractor.Prompt prompt2;
                if(parcel.readInt() != 0)
                    prompt2 = (android.app.VoiceInteractor.Prompt)android.app.VoiceInteractor.Prompt.CREATOR.createFromParcel(parcel);
                else
                    prompt2 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = startCompleteVoice(s2, ivoiceinteractorcallback2, prompt2, parcel);
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractor");
                String s3 = parcel.readString();
                IVoiceInteractorCallback ivoiceinteractorcallback3 = IVoiceInteractorCallback.Stub.asInterface(parcel.readStrongBinder());
                android.app.VoiceInteractor.Prompt prompt3;
                if(parcel.readInt() != 0)
                    prompt3 = (android.app.VoiceInteractor.Prompt)android.app.VoiceInteractor.Prompt.CREATOR.createFromParcel(parcel);
                else
                    prompt3 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = startAbortVoice(s3, ivoiceinteractorcallback3, prompt3, parcel);
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractor");
                String s4 = parcel.readString();
                IVoiceInteractorCallback ivoiceinteractorcallback4 = IVoiceInteractorCallback.Stub.asInterface(parcel.readStrongBinder());
                String s5 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = startCommand(s4, ivoiceinteractorcallback4, s5, parcel);
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractor");
                parcel = supportsCommands(parcel.readString(), parcel.createStringArray());
                parcel1.writeNoException();
                parcel1.writeBooleanArray(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IVoiceInteractor";
        static final int TRANSACTION_startAbortVoice = 4;
        static final int TRANSACTION_startCommand = 5;
        static final int TRANSACTION_startCompleteVoice = 3;
        static final int TRANSACTION_startConfirmation = 1;
        static final int TRANSACTION_startPickOption = 2;
        static final int TRANSACTION_supportsCommands = 6;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IVoiceInteractor");
        }
    }

    private static class Stub.Proxy
        implements IVoiceInteractor
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IVoiceInteractor";
        }

        public IVoiceInteractorRequest startAbortVoice(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractor");
            parcel.writeString(s);
            s = obj;
            if(ivoiceinteractorcallback == null)
                break MISSING_BLOCK_LABEL_40;
            s = ivoiceinteractorcallback.asBinder();
            parcel.writeStrongBinder(s);
            if(prompt == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            prompt.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_146;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            s = IVoiceInteractorRequest.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public IVoiceInteractorRequest startCommand(String s, IVoiceInteractorCallback ivoiceinteractorcallback, String s1, Bundle bundle)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractor");
            parcel.writeString(s);
            s = obj;
            if(ivoiceinteractorcallback == null)
                break MISSING_BLOCK_LABEL_40;
            s = ivoiceinteractorcallback.asBinder();
            parcel.writeStrongBinder(s);
            parcel.writeString(s1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_113;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            s = IVoiceInteractorRequest.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IVoiceInteractorRequest startCompleteVoice(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractor");
            parcel.writeString(s);
            s = obj;
            if(ivoiceinteractorcallback == null)
                break MISSING_BLOCK_LABEL_40;
            s = ivoiceinteractorcallback.asBinder();
            parcel.writeStrongBinder(s);
            if(prompt == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            prompt.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_146;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            s = IVoiceInteractorRequest.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public IVoiceInteractorRequest startConfirmation(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractor");
            parcel.writeString(s);
            s = obj;
            if(ivoiceinteractorcallback == null)
                break MISSING_BLOCK_LABEL_40;
            s = ivoiceinteractorcallback.asBinder();
            parcel.writeStrongBinder(s);
            if(prompt == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            prompt.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_146;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = IVoiceInteractorRequest.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public IVoiceInteractorRequest startPickOption(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractor");
            parcel.writeString(s);
            s = obj;
            if(ivoiceinteractorcallback == null)
                break MISSING_BLOCK_LABEL_40;
            s = ivoiceinteractorcallback.asBinder();
            parcel.writeStrongBinder(s);
            if(prompt == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            prompt.writeToParcel(parcel, 0);
_L3:
            parcel.writeTypedArray(aoption, 0);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_154;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            s = IVoiceInteractorRequest.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean[] supportsCommands(String s, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractor");
            parcel.writeString(s);
            parcel.writeStringArray(as);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createBooleanArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract IVoiceInteractorRequest startAbortVoice(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
        throws RemoteException;

    public abstract IVoiceInteractorRequest startCommand(String s, IVoiceInteractorCallback ivoiceinteractorcallback, String s1, Bundle bundle)
        throws RemoteException;

    public abstract IVoiceInteractorRequest startCompleteVoice(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
        throws RemoteException;

    public abstract IVoiceInteractorRequest startConfirmation(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, Bundle bundle)
        throws RemoteException;

    public abstract IVoiceInteractorRequest startPickOption(String s, IVoiceInteractorCallback ivoiceinteractorcallback, android.app.VoiceInteractor.Prompt prompt, android.app.VoiceInteractor.PickOptionRequest.Option aoption[], Bundle bundle)
        throws RemoteException;

    public abstract boolean[] supportsCommands(String s, String as[])
        throws RemoteException;
}
