// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.content.ComponentName;
import android.content.Intent;
import android.hardware.soundtrigger.IRecognitionStatusCallback;
import android.os.*;
import android.service.voice.IVoiceInteractionService;
import android.service.voice.IVoiceInteractionSession;

// Referenced classes of package com.android.internal.app:
//            IVoiceInteractor, IVoiceInteractionSessionListener, IVoiceInteractionSessionShowCallback

public interface IVoiceInteractionManagerService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVoiceInteractionManagerService
    {

        public static IVoiceInteractionManagerService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IVoiceInteractionManagerService");
            if(iinterface != null && (iinterface instanceof IVoiceInteractionManagerService))
                return (IVoiceInteractionManagerService)iinterface;
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
                parcel1.writeString("com.android.internal.app.IVoiceInteractionManagerService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                IVoiceInteractionService ivoiceinteractionservice = android.service.voice.IVoiceInteractionService.Stub.asInterface(parcel.readStrongBinder());
                Bundle bundle;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                showSession(ivoiceinteractionservice, bundle, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                boolean flag = deliverNewSession(parcel.readStrongBinder(), android.service.voice.IVoiceInteractionSession.Stub.asInterface(parcel.readStrongBinder()), IVoiceInteractor.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                IBinder ibinder = parcel.readStrongBinder();
                Bundle bundle1;
                boolean flag1;
                if(parcel.readInt() != 0)
                    bundle1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle1 = null;
                flag1 = showSessionFromSession(ibinder, bundle1, parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                boolean flag2 = hideSessionFromSession(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                IBinder ibinder1 = parcel.readStrongBinder();
                Intent intent;
                if(parcel.readInt() != 0)
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent = null;
                i = startVoiceActivity(ibinder1, intent, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                IBinder ibinder2 = parcel.readStrongBinder();
                Intent intent1;
                if(parcel.readInt() != 0)
                    intent1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent1 = null;
                i = startAssistantActivity(ibinder2, intent1, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                IBinder ibinder3 = parcel.readStrongBinder();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setKeepAwake(ibinder3, flag3);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                closeSystemDialogs(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                finish(parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                setDisabledShowContext(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                i = getDisabledShowContext();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                i = getUserDisabledShowContext();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                parcel = getKeyphraseSoundModel(parcel.readInt(), parcel.readString());
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

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                if(parcel.readInt() != 0)
                    parcel = (android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel)android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = updateKeyphraseSoundModel(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                i = deleteKeyphraseSoundModel(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                parcel = getDspModuleProperties(android.service.voice.IVoiceInteractionService.Stub.asInterface(parcel.readStrongBinder()));
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

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                boolean flag4 = isEnrolledForKeyphrase(android.service.voice.IVoiceInteractionService.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                IVoiceInteractionService ivoiceinteractionservice1 = android.service.voice.IVoiceInteractionService.Stub.asInterface(parcel.readStrongBinder());
                i = parcel.readInt();
                String s = parcel.readString();
                IRecognitionStatusCallback irecognitionstatuscallback = android.hardware.soundtrigger.IRecognitionStatusCallback.Stub.asInterface(parcel.readStrongBinder());
                if(parcel.readInt() != 0)
                    parcel = (android.hardware.soundtrigger.SoundTrigger.RecognitionConfig)android.hardware.soundtrigger.SoundTrigger.RecognitionConfig.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = startRecognition(ivoiceinteractionservice1, i, s, irecognitionstatuscallback, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                i = stopRecognition(android.service.voice.IVoiceInteractionService.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), android.hardware.soundtrigger.IRecognitionStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                parcel = getActiveServiceComponentName();
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

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                Bundle bundle2;
                boolean flag5;
                if(parcel.readInt() != 0)
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle2 = null;
                flag5 = showSessionForActiveService(bundle2, parcel.readInt(), IVoiceInteractionSessionShowCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                hideCurrentSession();
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                launchVoiceAssistFromKeyguard();
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                boolean flag6 = isSessionRunning();
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                boolean flag7 = activeServiceSupportsAssist();
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                boolean flag8 = activeServiceSupportsLaunchFromKeyguard();
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                onLockscreenShown();
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.app.IVoiceInteractionManagerService");
                registerVoiceInteractionSessionListener(IVoiceInteractionSessionListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IVoiceInteractionManagerService";
        static final int TRANSACTION_activeServiceSupportsAssist = 25;
        static final int TRANSACTION_activeServiceSupportsLaunchFromKeyguard = 26;
        static final int TRANSACTION_closeSystemDialogs = 8;
        static final int TRANSACTION_deleteKeyphraseSoundModel = 15;
        static final int TRANSACTION_deliverNewSession = 2;
        static final int TRANSACTION_finish = 9;
        static final int TRANSACTION_getActiveServiceComponentName = 20;
        static final int TRANSACTION_getDisabledShowContext = 11;
        static final int TRANSACTION_getDspModuleProperties = 16;
        static final int TRANSACTION_getKeyphraseSoundModel = 13;
        static final int TRANSACTION_getUserDisabledShowContext = 12;
        static final int TRANSACTION_hideCurrentSession = 22;
        static final int TRANSACTION_hideSessionFromSession = 4;
        static final int TRANSACTION_isEnrolledForKeyphrase = 17;
        static final int TRANSACTION_isSessionRunning = 24;
        static final int TRANSACTION_launchVoiceAssistFromKeyguard = 23;
        static final int TRANSACTION_onLockscreenShown = 27;
        static final int TRANSACTION_registerVoiceInteractionSessionListener = 28;
        static final int TRANSACTION_setDisabledShowContext = 10;
        static final int TRANSACTION_setKeepAwake = 7;
        static final int TRANSACTION_showSession = 1;
        static final int TRANSACTION_showSessionForActiveService = 21;
        static final int TRANSACTION_showSessionFromSession = 3;
        static final int TRANSACTION_startAssistantActivity = 6;
        static final int TRANSACTION_startRecognition = 18;
        static final int TRANSACTION_startVoiceActivity = 5;
        static final int TRANSACTION_stopRecognition = 19;
        static final int TRANSACTION_updateKeyphraseSoundModel = 14;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IVoiceInteractionManagerService");
        }
    }

    private static class Stub.Proxy
        implements IVoiceInteractionManagerService
    {

        public boolean activeServiceSupportsAssist()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            mRemote.transact(25, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean activeServiceSupportsLaunchFromKeyguard()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            mRemote.transact(26, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void closeSystemDialogs(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int deleteKeyphraseSoundModel(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean deliverNewSession(IBinder ibinder, IVoiceInteractionSession ivoiceinteractionsession, IVoiceInteractor ivoiceinteractor)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeStrongBinder(ibinder);
            if(ivoiceinteractionsession == null)
                break MISSING_BLOCK_LABEL_112;
            ibinder = ivoiceinteractionsession.asBinder();
_L1:
            parcel.writeStrongBinder(ibinder);
            ibinder = obj;
            if(ivoiceinteractor == null)
                break MISSING_BLOCK_LABEL_57;
            ibinder = ivoiceinteractor.asBinder();
            int i;
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
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
            ibinder = null;
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void finish(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public ComponentName getActiveServiceComponentName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ComponentName componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            componentname = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getDisabledShowContext()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public android.hardware.soundtrigger.SoundTrigger.ModuleProperties getDspModuleProperties(IVoiceInteractionService ivoiceinteractionservice)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            if(ivoiceinteractionservice == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ivoiceinteractionservice.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ivoiceinteractionservice = (android.hardware.soundtrigger.SoundTrigger.ModuleProperties)android.hardware.soundtrigger.SoundTrigger.ModuleProperties.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return ivoiceinteractionservice;
_L2:
            ivoiceinteractionservice = null;
            if(true) goto _L4; else goto _L3
_L3:
            ivoiceinteractionservice;
            parcel1.recycle();
            parcel.recycle();
            throw ivoiceinteractionservice;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IVoiceInteractionManagerService";
        }

        public android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel getKeyphraseSoundModel(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel)android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getUserDisabledShowContext()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void hideCurrentSession()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean hideSessionFromSession(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
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
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean isEnrolledForKeyphrase(IVoiceInteractionService ivoiceinteractionservice, int i, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            if(ivoiceinteractionservice == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = ivoiceinteractionservice.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
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
            ivoiceinteractionservice;
            parcel1.recycle();
            parcel.recycle();
            throw ivoiceinteractionservice;
        }

        public boolean isSessionRunning()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            mRemote.transact(24, parcel, parcel1, 0);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void launchVoiceAssistFromKeyguard()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void onLockscreenShown()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void registerVoiceInteractionSessionListener(IVoiceInteractionSessionListener ivoiceinteractionsessionlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            if(ivoiceinteractionsessionlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ivoiceinteractionsessionlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ivoiceinteractionsessionlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ivoiceinteractionsessionlistener;
        }

        public void setDisabledShowContext(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setKeepAwake(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void showSession(IVoiceInteractionService ivoiceinteractionservice, Bundle bundle, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            if(ivoiceinteractionservice == null)
                break MISSING_BLOCK_LABEL_32;
            ibinder = ivoiceinteractionservice.asBinder();
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ivoiceinteractionservice;
            parcel1.recycle();
            parcel.recycle();
            throw ivoiceinteractionservice;
        }

        public boolean showSessionForActiveService(Bundle bundle, int i, IVoiceInteractionSessionShowCallback ivoiceinteractionsessionshowcallback, IBinder ibinder)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_118;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            bundle = obj;
            if(ivoiceinteractionsessionshowcallback == null)
                break MISSING_BLOCK_LABEL_57;
            bundle = ivoiceinteractionsessionshowcallback.asBinder();
            parcel.writeStrongBinder(bundle);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(21, parcel, parcel1, 0);
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
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public boolean showSessionFromSession(IBinder ibinder, Bundle bundle, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
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
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int startAssistantActivity(IBinder ibinder, Intent intent, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeStrongBinder(ibinder);
            if(intent == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int startRecognition(IVoiceInteractionService ivoiceinteractionservice, int i, String s, IRecognitionStatusCallback irecognitionstatuscallback, android.hardware.soundtrigger.SoundTrigger.RecognitionConfig recognitionconfig)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            if(ivoiceinteractionservice == null) goto _L2; else goto _L1
_L1:
            ivoiceinteractionservice = ivoiceinteractionservice.asBinder();
_L5:
            parcel.writeStrongBinder(ivoiceinteractionservice);
            parcel.writeInt(i);
            parcel.writeString(s);
            ivoiceinteractionservice = obj;
            if(irecognitionstatuscallback == null)
                break MISSING_BLOCK_LABEL_65;
            ivoiceinteractionservice = irecognitionstatuscallback.asBinder();
            parcel.writeStrongBinder(ivoiceinteractionservice);
            if(recognitionconfig == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            recognitionconfig.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            ivoiceinteractionservice = null;
              goto _L5
_L4:
            parcel.writeInt(0);
              goto _L6
            ivoiceinteractionservice;
            parcel1.recycle();
            parcel.recycle();
            throw ivoiceinteractionservice;
              goto _L5
        }

        public int startVoiceActivity(IBinder ibinder, Intent intent, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            parcel.writeStrongBinder(ibinder);
            if(intent == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int stopRecognition(IVoiceInteractionService ivoiceinteractionservice, int i, IRecognitionStatusCallback irecognitionstatuscallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            if(ivoiceinteractionservice == null)
                break MISSING_BLOCK_LABEL_103;
            ivoiceinteractionservice = ivoiceinteractionservice.asBinder();
_L1:
            parcel.writeStrongBinder(ivoiceinteractionservice);
            parcel.writeInt(i);
            ivoiceinteractionservice = obj;
            if(irecognitionstatuscallback == null)
                break MISSING_BLOCK_LABEL_57;
            ivoiceinteractionservice = irecognitionstatuscallback.asBinder();
            parcel.writeStrongBinder(ivoiceinteractionservice);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            ivoiceinteractionservice = null;
              goto _L1
            ivoiceinteractionservice;
            parcel1.recycle();
            parcel.recycle();
            throw ivoiceinteractionservice;
        }

        public int updateKeyphraseSoundModel(android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel keyphrasesoundmodel)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IVoiceInteractionManagerService");
            if(keyphrasesoundmodel == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            keyphrasesoundmodel.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(14, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean activeServiceSupportsAssist()
        throws RemoteException;

    public abstract boolean activeServiceSupportsLaunchFromKeyguard()
        throws RemoteException;

    public abstract void closeSystemDialogs(IBinder ibinder)
        throws RemoteException;

    public abstract int deleteKeyphraseSoundModel(int i, String s)
        throws RemoteException;

    public abstract boolean deliverNewSession(IBinder ibinder, IVoiceInteractionSession ivoiceinteractionsession, IVoiceInteractor ivoiceinteractor)
        throws RemoteException;

    public abstract void finish(IBinder ibinder)
        throws RemoteException;

    public abstract ComponentName getActiveServiceComponentName()
        throws RemoteException;

    public abstract int getDisabledShowContext()
        throws RemoteException;

    public abstract android.hardware.soundtrigger.SoundTrigger.ModuleProperties getDspModuleProperties(IVoiceInteractionService ivoiceinteractionservice)
        throws RemoteException;

    public abstract android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel getKeyphraseSoundModel(int i, String s)
        throws RemoteException;

    public abstract int getUserDisabledShowContext()
        throws RemoteException;

    public abstract void hideCurrentSession()
        throws RemoteException;

    public abstract boolean hideSessionFromSession(IBinder ibinder)
        throws RemoteException;

    public abstract boolean isEnrolledForKeyphrase(IVoiceInteractionService ivoiceinteractionservice, int i, String s)
        throws RemoteException;

    public abstract boolean isSessionRunning()
        throws RemoteException;

    public abstract void launchVoiceAssistFromKeyguard()
        throws RemoteException;

    public abstract void onLockscreenShown()
        throws RemoteException;

    public abstract void registerVoiceInteractionSessionListener(IVoiceInteractionSessionListener ivoiceinteractionsessionlistener)
        throws RemoteException;

    public abstract void setDisabledShowContext(int i)
        throws RemoteException;

    public abstract void setKeepAwake(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void showSession(IVoiceInteractionService ivoiceinteractionservice, Bundle bundle, int i)
        throws RemoteException;

    public abstract boolean showSessionForActiveService(Bundle bundle, int i, IVoiceInteractionSessionShowCallback ivoiceinteractionsessionshowcallback, IBinder ibinder)
        throws RemoteException;

    public abstract boolean showSessionFromSession(IBinder ibinder, Bundle bundle, int i)
        throws RemoteException;

    public abstract int startAssistantActivity(IBinder ibinder, Intent intent, String s)
        throws RemoteException;

    public abstract int startRecognition(IVoiceInteractionService ivoiceinteractionservice, int i, String s, IRecognitionStatusCallback irecognitionstatuscallback, android.hardware.soundtrigger.SoundTrigger.RecognitionConfig recognitionconfig)
        throws RemoteException;

    public abstract int startVoiceActivity(IBinder ibinder, Intent intent, String s)
        throws RemoteException;

    public abstract int stopRecognition(IVoiceInteractionService ivoiceinteractionservice, int i, IRecognitionStatusCallback irecognitionstatuscallback)
        throws RemoteException;

    public abstract int updateKeyphraseSoundModel(android.hardware.soundtrigger.SoundTrigger.KeyphraseSoundModel keyphrasesoundmodel)
        throws RemoteException;
}
