// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.location;

import android.os.*;

// Referenced classes of package android.hardware.location:
//            ContextHubMessage

public interface IContextHubCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IContextHubCallback
    {

        public static IContextHubCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.location.IContextHubCallback");
            if(iinterface != null && (iinterface instanceof IContextHubCallback))
                return (IContextHubCallback)iinterface;
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
                parcel1.writeString("android.hardware.location.IContextHubCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.location.IContextHubCallback");
                j = parcel.readInt();
                i = parcel.readInt();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ContextHubMessage)ContextHubMessage.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onMessageReceipt(j, i, parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.location.IContextHubCallback";
        static final int TRANSACTION_onMessageReceipt = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.location.IContextHubCallback");
        }
    }

    private static class Stub.Proxy
        implements IContextHubCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.location.IContextHubCallback";
        }

        public void onMessageReceipt(int i, int j, ContextHubMessage contexthubmessage)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.location.IContextHubCallback");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(contexthubmessage == null)
                break MISSING_BLOCK_LABEL_62;
            parcel.writeInt(1);
            contexthubmessage.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            contexthubmessage;
            parcel.recycle();
            throw contexthubmessage;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onMessageReceipt(int i, int j, ContextHubMessage contexthubmessage)
        throws RemoteException;
}
