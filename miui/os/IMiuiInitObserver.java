// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.os;

import android.os.*;

public interface IMiuiInitObserver
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiInitObserver
    {

        public static IMiuiInitObserver asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.os.IMiuiInitObserver");
            if(iinterface != null && (iinterface instanceof IMiuiInitObserver))
                return (IMiuiInitObserver)iinterface;
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
                parcel1.writeString("miui.os.IMiuiInitObserver");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.os.IMiuiInitObserver");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            initDone(flag);
            return true;
        }

        private static final String DESCRIPTOR = "miui.os.IMiuiInitObserver";
        static final int TRANSACTION_initDone = 1;

        public Stub()
        {
            attachInterface(this, "miui.os.IMiuiInitObserver");
        }
    }

    private static class Stub.Proxy
        implements IMiuiInitObserver
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.os.IMiuiInitObserver";
        }

        public void initDone(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.os.IMiuiInitObserver");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
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


    public abstract void initDone(boolean flag)
        throws RemoteException;
}
