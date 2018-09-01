// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.os;

import android.os.*;

public interface IMiuiDexoptObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiDexoptObserver
    {

        public static IMiuiDexoptObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.os.IMiuiDexoptObserver");
            if(iinterface != null && (iinterface instanceof IMiuiDexoptObserver))
                return (IMiuiDexoptObserver)iinterface;
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
                parcel1.writeString("miui.os.IMiuiDexoptObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.os.IMiuiDexoptObserver");
                onStart(parcel.readString());
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.os.IMiuiDexoptObserver");
                onFinished(parcel.readString(), parcel.readInt());
                return true;
            }
        }

        private static final String DESCRIPTOR = "miui.os.IMiuiDexoptObserver";
        static final int TRANSACTION_onFinished = 2;
        static final int TRANSACTION_onStart = 1;

        public Stub()
        {
            attachInterface(this, "miui.os.IMiuiDexoptObserver");
        }
    }

    private static class Stub.Proxy
        implements IMiuiDexoptObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.os.IMiuiDexoptObserver";
        }

        public void onFinished(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiDexoptObserver");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public void onStart(String s)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiDexoptObserver");
            parcel.writeString(s);
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


    public abstract void onFinished(String s, int i)
        throws RemoteException;

    public abstract void onStart(String s)
        throws RemoteException;
}
