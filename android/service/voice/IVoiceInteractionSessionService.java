// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.voice;

import android.os.*;

public interface IVoiceInteractionSessionService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IVoiceInteractionSessionService
    {

        public static IVoiceInteractionSessionService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.service.voice.IVoiceInteractionSessionService");
            if(iinterface != null && (iinterface instanceof IVoiceInteractionSessionService))
                return (IVoiceInteractionSessionService)iinterface;
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
            IBinder ibinder;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.service.voice.IVoiceInteractionSessionService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.service.voice.IVoiceInteractionSessionService");
                ibinder = parcel.readStrongBinder();
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            newSession(ibinder, parcel1, parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "android.service.voice.IVoiceInteractionSessionService";
        static final int TRANSACTION_newSession = 1;

        public Stub()
        {
            attachInterface(this, "android.service.voice.IVoiceInteractionSessionService");
        }
    }

    private static class Stub.Proxy
        implements IVoiceInteractionSessionService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.service.voice.IVoiceInteractionSessionService";
        }

        public void newSession(IBinder ibinder, Bundle bundle, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.service.voice.IVoiceInteractionSessionService");
            parcel.writeStrongBinder(ibinder);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel.recycle();
            throw ibinder;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void newSession(IBinder ibinder, Bundle bundle, int i)
        throws RemoteException;
}
