// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            IInterface, RemoteException, Binder, IBinder, 
//            Parcel

public interface IUpdateEngineCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUpdateEngineCallback
    {

        public static IUpdateEngineCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.IUpdateEngineCallback");
            if(iinterface != null && (iinterface instanceof IUpdateEngineCallback))
                return (IUpdateEngineCallback)iinterface;
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
                parcel1.writeString("android.os.IUpdateEngineCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.IUpdateEngineCallback");
                onStatusUpdate(parcel.readInt(), parcel.readFloat());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.IUpdateEngineCallback");
                onPayloadApplicationComplete(parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.IUpdateEngineCallback";
        static final int TRANSACTION_onPayloadApplicationComplete = 2;
        static final int TRANSACTION_onStatusUpdate = 1;

        public Stub()
        {
            attachInterface(this, "android.os.IUpdateEngineCallback");
        }
    }

    private static class Stub.Proxy
        implements IUpdateEngineCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.os.IUpdateEngineCallback";
        }

        public void onPayloadApplicationComplete(int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateEngineCallback");
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void onStatusUpdate(int i, float f)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.IUpdateEngineCallback");
            parcel.writeInt(i);
            parcel.writeFloat(f);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onPayloadApplicationComplete(int i)
        throws RemoteException;

    public abstract void onStatusUpdate(int i, float f)
        throws RemoteException;
}
