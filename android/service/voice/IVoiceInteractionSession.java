// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.voice;

import android.app.assist.AssistContent;
import android.app.assist.AssistStructure;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.*;
import com.android.internal.app.IVoiceInteractionSessionShowCallback;

public interface IVoiceInteractionSession
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVoiceInteractionSession
    {

        public static IVoiceInteractionSession asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.voice.IVoiceInteractionSession");
            if(iinterface != null && (iinterface instanceof IVoiceInteractionSession))
                return (IVoiceInteractionSession)iinterface;
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
                parcel1.writeString("android.service.voice.IVoiceInteractionSession");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionSession");
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                show(parcel1, parcel.readInt(), com.android.internal.app.IVoiceInteractionSessionShowCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionSession");
                hide();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionSession");
                AssistStructure assiststructure;
                AssistContent assistcontent;
                if(parcel.readInt() != 0)
                    parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    assiststructure = (AssistStructure)AssistStructure.CREATOR.createFromParcel(parcel);
                else
                    assiststructure = null;
                if(parcel.readInt() != 0)
                    assistcontent = (AssistContent)AssistContent.CREATOR.createFromParcel(parcel);
                else
                    assistcontent = null;
                handleAssist(parcel1, assiststructure, assistcontent, parcel.readInt(), parcel.readInt());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionSession");
                if(parcel.readInt() != 0)
                    parcel = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                handleScreenshot(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionSession");
                if(parcel.readInt() != 0)
                    parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                taskStarted(parcel1, parcel.readInt());
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionSession");
                if(parcel.readInt() != 0)
                    parcel1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                taskFinished(parcel1, parcel.readInt());
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionSession");
                closeSystemDialogs();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionSession");
                onLockscreenShown();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionSession");
                destroy();
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.service.voice.IVoiceInteractionSession";
        static final int TRANSACTION_closeSystemDialogs = 7;
        static final int TRANSACTION_destroy = 9;
        static final int TRANSACTION_handleAssist = 3;
        static final int TRANSACTION_handleScreenshot = 4;
        static final int TRANSACTION_hide = 2;
        static final int TRANSACTION_onLockscreenShown = 8;
        static final int TRANSACTION_show = 1;
        static final int TRANSACTION_taskFinished = 6;
        static final int TRANSACTION_taskStarted = 5;

        public Stub()
        {
            attachInterface(this, "android.service.voice.IVoiceInteractionSession");
        }
    }

    private static class Stub.Proxy
        implements IVoiceInteractionSession
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void closeSystemDialogs()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionSession");
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void destroy()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionSession");
            mRemote.transact(9, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.voice.IVoiceInteractionSession";
        }

        public void handleAssist(Bundle bundle, AssistStructure assiststructure, AssistContent assistcontent, int i, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionSession");
            if(bundle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L5:
            if(assiststructure == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            assiststructure.writeToParcel(parcel, 0);
_L6:
            if(assistcontent == null)
                break MISSING_BLOCK_LABEL_124;
            parcel.writeInt(1);
            assistcontent.writeToParcel(parcel, 0);
_L7:
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            bundle;
            parcel.recycle();
            throw bundle;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void handleScreenshot(Bitmap bitmap)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionSession");
            if(bitmap == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            bitmap.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bitmap;
            parcel.recycle();
            throw bitmap;
        }

        public void hide()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionSession");
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onLockscreenShown()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionSession");
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void show(Bundle bundle, int i, IVoiceInteractionSessionShowCallback ivoiceinteractionsessionshowcallback)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionSession");
            if(bundle == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            bundle = obj;
            if(ivoiceinteractionsessionshowcallback == null)
                break MISSING_BLOCK_LABEL_52;
            bundle = ivoiceinteractionsessionshowcallback.asBinder();
            parcel.writeStrongBinder(bundle);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bundle;
            parcel.recycle();
            throw bundle;
        }

        public void taskFinished(Intent intent, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionSession");
            if(intent == null)
                break MISSING_BLOCK_LABEL_50;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel.recycle();
            throw intent;
        }

        public void taskStarted(Intent intent, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionSession");
            if(intent == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel.recycle();
            throw intent;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void closeSystemDialogs()
        throws RemoteException;

    public abstract void destroy()
        throws RemoteException;

    public abstract void handleAssist(Bundle bundle, AssistStructure assiststructure, AssistContent assistcontent, int i, int j)
        throws RemoteException;

    public abstract void handleScreenshot(Bitmap bitmap)
        throws RemoteException;

    public abstract void hide()
        throws RemoteException;

    public abstract void onLockscreenShown()
        throws RemoteException;

    public abstract void show(Bundle bundle, int i, IVoiceInteractionSessionShowCallback ivoiceinteractionsessionshowcallback)
        throws RemoteException;

    public abstract void taskFinished(Intent intent, int i)
        throws RemoteException;

    public abstract void taskStarted(Intent intent, int i)
        throws RemoteException;
}
