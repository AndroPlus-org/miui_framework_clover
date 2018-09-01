// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.app.PendingIntent;
import android.hardware.soundtrigger.IRecognitionStatusCallback;
import android.os.*;

public interface ISoundTriggerService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISoundTriggerService
    {

        public static ISoundTriggerService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.ISoundTriggerService");
            if(iinterface != null && (iinterface instanceof ISoundTriggerService))
                return (ISoundTriggerService)iinterface;
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
                parcel1.writeString("com.android.internal.app.ISoundTriggerService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                if(parcel.readInt() != 0)
                    parcel = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getSoundModel(parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                if(parcel.readInt() != 0)
                    parcel = (android.hardware.soundtrigger.SoundTrigger.GenericSoundModel)android.hardware.soundtrigger.SoundTrigger.GenericSoundModel.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateSoundModel(parcel);
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                if(parcel.readInt() != 0)
                    parcel = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                deleteSoundModel(parcel);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                ParcelUuid parceluuid;
                IRecognitionStatusCallback irecognitionstatuscallback;
                if(parcel.readInt() != 0)
                    parceluuid = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parceluuid = null;
                irecognitionstatuscallback = android.hardware.soundtrigger.IRecognitionStatusCallback.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (android.hardware.soundtrigger.SoundTrigger.RecognitionConfig)android.hardware.soundtrigger.SoundTrigger.RecognitionConfig.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = startRecognition(parceluuid, irecognitionstatuscallback, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                ParcelUuid parceluuid1;
                if(parcel.readInt() != 0)
                    parceluuid1 = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parceluuid1 = null;
                i = stopRecognition(parceluuid1, android.hardware.soundtrigger.IRecognitionStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                if(parcel.readInt() != 0)
                    parcel = (android.hardware.soundtrigger.SoundTrigger.GenericSoundModel)android.hardware.soundtrigger.SoundTrigger.GenericSoundModel.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = loadGenericSoundModel(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                if(parcel.readInt() != 0)
                    parcel = (android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel)android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = loadKeyphraseSoundModel(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                ParcelUuid parceluuid2;
                PendingIntent pendingintent;
                if(parcel.readInt() != 0)
                    parceluuid2 = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parceluuid2 = null;
                if(parcel.readInt() != 0)
                    pendingintent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    pendingintent = null;
                if(parcel.readInt() != 0)
                    parcel = (android.hardware.soundtrigger.SoundTrigger.RecognitionConfig)android.hardware.soundtrigger.SoundTrigger.RecognitionConfig.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = startRecognitionForIntent(parceluuid2, pendingintent, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                if(parcel.readInt() != 0)
                    parcel = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = stopRecognitionForIntent(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                if(parcel.readInt() != 0)
                    parcel = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = unloadSoundModel(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.app.ISoundTriggerService");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                parcel = (ParcelUuid)ParcelUuid.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            flag = isRecognitionActive(parcel);
            parcel1.writeNoException();
            if(flag)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.app.ISoundTriggerService";
        static final int TRANSACTION_deleteSoundModel = 3;
        static final int TRANSACTION_getSoundModel = 1;
        static final int TRANSACTION_isRecognitionActive = 11;
        static final int TRANSACTION_loadGenericSoundModel = 6;
        static final int TRANSACTION_loadKeyphraseSoundModel = 7;
        static final int TRANSACTION_startRecognition = 4;
        static final int TRANSACTION_startRecognitionForIntent = 8;
        static final int TRANSACTION_stopRecognition = 5;
        static final int TRANSACTION_stopRecognitionForIntent = 9;
        static final int TRANSACTION_unloadSoundModel = 10;
        static final int TRANSACTION_updateSoundModel = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.ISoundTriggerService");
        }
    }

    private static class Stub.Proxy
        implements ISoundTriggerService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void deleteSoundModel(ParcelUuid parceluuid)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(parceluuid == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceluuid;
            parcel1.recycle();
            parcel.recycle();
            throw parceluuid;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.ISoundTriggerService";
        }

        public android.hardware.soundtrigger.SoundTrigger.GenericSoundModel getSoundModel(ParcelUuid parceluuid)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(parceluuid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_96;
            parceluuid = (android.hardware.soundtrigger.SoundTrigger.GenericSoundModel)android.hardware.soundtrigger.SoundTrigger.GenericSoundModel.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parceluuid;
_L2:
            parcel.writeInt(0);
              goto _L3
            parceluuid;
            parcel1.recycle();
            parcel.recycle();
            throw parceluuid;
            parceluuid = null;
              goto _L4
        }

        public boolean isRecognitionActive(ParcelUuid parceluuid)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(parceluuid == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            parceluuid;
            parcel1.recycle();
            parcel.recycle();
            throw parceluuid;
        }

        public int loadGenericSoundModel(android.hardware.soundtrigger.SoundTrigger.GenericSoundModel genericsoundmodel)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(genericsoundmodel == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            genericsoundmodel.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            genericsoundmodel;
            parcel1.recycle();
            parcel.recycle();
            throw genericsoundmodel;
        }

        public int loadKeyphraseSoundModel(android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel keyphrasesoundmodel)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(keyphrasesoundmodel == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            keyphrasesoundmodel.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            keyphrasesoundmodel;
            parcel1.recycle();
            parcel.recycle();
            throw keyphrasesoundmodel;
        }

        public int startRecognition(ParcelUuid parceluuid, IRecognitionStatusCallback irecognitionstatuscallback, android.hardware.soundtrigger.SoundTrigger.RecognitionConfig recognitionconfig)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(parceluuid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L3:
            parceluuid = obj;
            if(irecognitionstatuscallback == null)
                break MISSING_BLOCK_LABEL_51;
            parceluuid = irecognitionstatuscallback.asBinder();
            parcel.writeStrongBinder(parceluuid);
            if(recognitionconfig == null)
                break MISSING_BLOCK_LABEL_137;
            parcel.writeInt(1);
            recognitionconfig.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            parceluuid;
            parcel1.recycle();
            parcel.recycle();
            throw parceluuid;
            parcel.writeInt(0);
              goto _L4
        }

        public int startRecognitionForIntent(ParcelUuid parceluuid, PendingIntent pendingintent, android.hardware.soundtrigger.SoundTrigger.RecognitionConfig recognitionconfig)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(parceluuid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L5:
            if(pendingintent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L6:
            if(recognitionconfig == null)
                break MISSING_BLOCK_LABEL_141;
            parcel.writeInt(1);
            recognitionconfig.writeToParcel(parcel, 0);
_L7:
            int i;
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L5
            parceluuid;
            parcel1.recycle();
            parcel.recycle();
            throw parceluuid;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public int stopRecognition(ParcelUuid parceluuid, IRecognitionStatusCallback irecognitionstatuscallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(parceluuid == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L4:
            parceluuid = obj;
            if(irecognitionstatuscallback == null)
                break MISSING_BLOCK_LABEL_49;
            parceluuid = irecognitionstatuscallback.asBinder();
            int i;
            parcel.writeStrongBinder(parceluuid);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            parceluuid;
            parcel1.recycle();
            parcel.recycle();
            throw parceluuid;
        }

        public int stopRecognitionForIntent(ParcelUuid parceluuid)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(parceluuid == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            parceluuid;
            parcel1.recycle();
            parcel.recycle();
            throw parceluuid;
        }

        public int unloadSoundModel(ParcelUuid parceluuid)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(parceluuid == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            parceluuid.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            parceluuid;
            parcel1.recycle();
            parcel.recycle();
            throw parceluuid;
        }

        public void updateSoundModel(android.hardware.soundtrigger.SoundTrigger.GenericSoundModel genericsoundmodel)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.ISoundTriggerService");
            if(genericsoundmodel == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            genericsoundmodel.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            genericsoundmodel;
            parcel1.recycle();
            parcel.recycle();
            throw genericsoundmodel;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void deleteSoundModel(ParcelUuid parceluuid)
        throws RemoteException;

    public abstract android.hardware.soundtrigger.SoundTrigger.GenericSoundModel getSoundModel(ParcelUuid parceluuid)
        throws RemoteException;

    public abstract boolean isRecognitionActive(ParcelUuid parceluuid)
        throws RemoteException;

    public abstract int loadGenericSoundModel(android.hardware.soundtrigger.SoundTrigger.GenericSoundModel genericsoundmodel)
        throws RemoteException;

    public abstract int loadKeyphraseSoundModel(android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel keyphrasesoundmodel)
        throws RemoteException;

    public abstract int startRecognition(ParcelUuid parceluuid, IRecognitionStatusCallback irecognitionstatuscallback, android.hardware.soundtrigger.SoundTrigger.RecognitionConfig recognitionconfig)
        throws RemoteException;

    public abstract int startRecognitionForIntent(ParcelUuid parceluuid, PendingIntent pendingintent, android.hardware.soundtrigger.SoundTrigger.RecognitionConfig recognitionconfig)
        throws RemoteException;

    public abstract int stopRecognition(ParcelUuid parceluuid, IRecognitionStatusCallback irecognitionstatuscallback)
        throws RemoteException;

    public abstract int stopRecognitionForIntent(ParcelUuid parceluuid)
        throws RemoteException;

    public abstract int unloadSoundModel(ParcelUuid parceluuid)
        throws RemoteException;

    public abstract void updateSoundModel(android.hardware.soundtrigger.SoundTrigger.GenericSoundModel genericsoundmodel)
        throws RemoteException;
}
