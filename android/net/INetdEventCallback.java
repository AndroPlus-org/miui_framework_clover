// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;

public interface INetdEventCallback
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetdEventCallback
    {

        public static INetdEventCallback asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.INetdEventCallback");
            if(iinterface != null && (iinterface instanceof INetdEventCallback))
                return (INetdEventCallback)iinterface;
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
                parcel1.writeString("android.net.INetdEventCallback");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.INetdEventCallback");
                onDnsEvent(parcel.readString(), parcel.createStringArray(), parcel.readInt(), parcel.readLong(), parcel.readInt());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.INetdEventCallback");
                onConnectEvent(parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.INetdEventCallback";
        static final int TRANSACTION_onConnectEvent = 2;
        static final int TRANSACTION_onDnsEvent = 1;

        public Stub()
        {
            attachInterface(this, "android.net.INetdEventCallback");
        }
    }

    private static class Stub.Proxy
        implements INetdEventCallback
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.net.INetdEventCallback";
        }

        public void onConnectEvent(String s, int i, long l, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetdEventCallback");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onDnsEvent(String s, String as[], int i, long l, int j)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.INetdEventCallback");
            parcel.writeString(s);
            parcel.writeStringArray(as);
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onConnectEvent(String s, int i, long l, int j)
        throws RemoteException;

    public abstract void onDnsEvent(String s, String as[], int i, long l, int j)
        throws RemoteException;
}
