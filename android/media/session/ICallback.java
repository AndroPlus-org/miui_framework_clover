// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.session;

import android.content.ComponentName;
import android.os.*;
import android.view.KeyEvent;

public interface ICallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ICallback
    {

        public static ICallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.session.ICallback");
            if(iinterface != null && (iinterface instanceof ICallback))
                return (ICallback)iinterface;
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
                parcel1.writeString("android.media.session.ICallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.session.ICallback");
                if(parcel.readInt() != 0)
                    parcel1 = (KeyEvent)KeyEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (MediaSession.Token)MediaSession.Token.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onMediaKeyEventDispatchedToMediaSession(parcel1, parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.session.ICallback");
                if(parcel.readInt() != 0)
                    parcel1 = (KeyEvent)KeyEvent.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onMediaKeyEventDispatchedToMediaButtonReceiver(parcel1, parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.session.ICallback");
                if(parcel.readInt() != 0)
                    parcel = (MediaSession.Token)MediaSession.Token.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                onAddressedPlayerChangedToMediaSession(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.session.ICallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onAddressedPlayerChangedToMediaButtonReceiver(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.session.ICallback";
        static final int TRANSACTION_onAddressedPlayerChangedToMediaButtonReceiver = 4;
        static final int TRANSACTION_onAddressedPlayerChangedToMediaSession = 3;
        static final int TRANSACTION_onMediaKeyEventDispatchedToMediaButtonReceiver = 2;
        static final int TRANSACTION_onMediaKeyEventDispatchedToMediaSession = 1;

        public Stub()
        {
            attachInterface(this, "android.media.session.ICallback");
        }
    }

    private static class Stub.Proxy
        implements ICallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.session.ICallback";
        }

        public void onAddressedPlayerChangedToMediaButtonReceiver(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ICallback");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public void onAddressedPlayerChangedToMediaSession(MediaSession.Token token)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ICallback");
            if(token == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            token.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            token;
            parcel.recycle();
            throw token;
        }

        public void onMediaKeyEventDispatchedToMediaButtonReceiver(KeyEvent keyevent, ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ICallback");
            if(keyevent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            keyevent.writeToParcel(parcel, 0);
_L3:
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            keyevent;
            parcel.recycle();
            throw keyevent;
            parcel.writeInt(0);
              goto _L4
        }

        public void onMediaKeyEventDispatchedToMediaSession(KeyEvent keyevent, MediaSession.Token token)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.session.ICallback");
            if(keyevent == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            keyevent.writeToParcel(parcel, 0);
_L3:
            if(token == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            token.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            keyevent;
            parcel.recycle();
            throw keyevent;
            parcel.writeInt(0);
              goto _L4
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onAddressedPlayerChangedToMediaButtonReceiver(ComponentName componentname)
        throws RemoteException;

    public abstract void onAddressedPlayerChangedToMediaSession(MediaSession.Token token)
        throws RemoteException;

    public abstract void onMediaKeyEventDispatchedToMediaButtonReceiver(KeyEvent keyevent, ComponentName componentname)
        throws RemoteException;

    public abstract void onMediaKeyEventDispatchedToMediaSession(KeyEvent keyevent, MediaSession.Token token)
        throws RemoteException;
}
