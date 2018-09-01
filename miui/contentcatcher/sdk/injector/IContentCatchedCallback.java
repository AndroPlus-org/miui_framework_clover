// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk.injector;

import android.os.*;
import miui.contentcatcher.sdk.Content;

public interface IContentCatchedCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IContentCatchedCallback
    {

        public static IContentCatchedCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.contentcatcher.sdk.injector.IContentCatchedCallback");
            if(iinterface != null && (iinterface instanceof IContentCatchedCallback))
                return (IContentCatchedCallback)iinterface;
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
                parcel1.writeString("miui.contentcatcher.sdk.injector.IContentCatchedCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.contentcatcher.sdk.injector.IContentCatchedCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Content)Content.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onContentCatched(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "miui.contentcatcher.sdk.injector.IContentCatchedCallback";
        static final int TRANSACTION_onContentCatched = 1;

        public Stub()
        {
            attachInterface(this, "miui.contentcatcher.sdk.injector.IContentCatchedCallback");
        }
    }

    private static class Stub.Proxy
        implements IContentCatchedCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.contentcatcher.sdk.injector.IContentCatchedCallback";
        }

        public void onContentCatched(Content content)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.sdk.injector.IContentCatchedCallback");
            if(content == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            content.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            content;
            parcel.recycle();
            throw content;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onContentCatched(Content content)
        throws RemoteException;
}
