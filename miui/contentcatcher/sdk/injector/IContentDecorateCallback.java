// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.contentcatcher.sdk.injector;

import android.os.*;
import miui.contentcatcher.sdk.DecorateContentParam;

public interface IContentDecorateCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IContentDecorateCallback
    {

        public static IContentDecorateCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.contentcatcher.sdk.injector.IContentDecorateCallback");
            if(iinterface != null && (iinterface instanceof IContentDecorateCallback))
                return (IContentDecorateCallback)iinterface;
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
                parcel1.writeString("miui.contentcatcher.sdk.injector.IContentDecorateCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.contentcatcher.sdk.injector.IContentDecorateCallback");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (DecorateContentParam)DecorateContentParam.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            updateContent(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "miui.contentcatcher.sdk.injector.IContentDecorateCallback";
        static final int TRANSACTION_updateContent = 1;

        public Stub()
        {
            attachInterface(this, "miui.contentcatcher.sdk.injector.IContentDecorateCallback");
        }
    }

    private static class Stub.Proxy
        implements IContentDecorateCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.contentcatcher.sdk.injector.IContentDecorateCallback";
        }

        public void updateContent(DecorateContentParam decoratecontentparam)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.contentcatcher.sdk.injector.IContentDecorateCallback");
            if(decoratecontentparam == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            decoratecontentparam.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            decoratecontentparam;
            parcel.recycle();
            throw decoratecontentparam;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void updateContent(DecorateContentParam decoratecontentparam)
        throws RemoteException;
}
