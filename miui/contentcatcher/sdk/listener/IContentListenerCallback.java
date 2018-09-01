// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk.listener;

import android.os.*;
import miui.contentcatcher.sdk.ClientCatcherResult;

public interface IContentListenerCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IContentListenerCallback
    {

        public static IContentListenerCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.contentcatcher.sdk.listener.IContentListenerCallback");
            if(iinterface != null && (iinterface instanceof IContentListenerCallback))
                return (IContentListenerCallback)iinterface;
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
                parcel1.writeString("miui.contentcatcher.sdk.listener.IContentListenerCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.contentcatcher.sdk.listener.IContentListenerCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ClientCatcherResult)ClientCatcherResult.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onContentReceived(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "miui.contentcatcher.sdk.listener.IContentListenerCallback";
        static final int TRANSACTION_onContentReceived = 1;

        public Stub()
        {
            attachInterface(this, "miui.contentcatcher.sdk.listener.IContentListenerCallback");
        }
    }

    private static class Stub.Proxy
        implements IContentListenerCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.contentcatcher.sdk.listener.IContentListenerCallback";
        }

        public void onContentReceived(ClientCatcherResult clientcatcherresult)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.sdk.listener.IContentListenerCallback");
            if(clientcatcherresult == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            clientcatcherresult.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            clientcatcherresult;
            parcel.recycle();
            throw clientcatcherresult;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onContentReceived(ClientCatcherResult clientcatcherresult)
        throws RemoteException;
}
