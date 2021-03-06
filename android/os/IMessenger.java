// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Message, Binder, 
//            IBinder, Parcel

public interface IMessenger
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMessenger
    {

        public static IMessenger asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IMessenger");
            if(iinterface != null && (iinterface instanceof IMessenger))
                return (IMessenger)iinterface;
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
                parcel1.writeString("android.os.IMessenger");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IMessenger");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (Message)Message.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            send(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.os.IMessenger";
        static final int TRANSACTION_send = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IMessenger");
        }
    }

    private static class Stub.Proxy
        implements IMessenger
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IMessenger";
        }

        public void send(Message message)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IMessenger");
            if(message == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            message.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            message;
            parcel.recycle();
            throw message;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void send(Message message)
        throws RemoteException;
}
