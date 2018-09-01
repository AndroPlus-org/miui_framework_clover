// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.os.*;

public interface IServiceConnection
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IServiceConnection
    {

        public static IServiceConnection asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.IServiceConnection");
            if(iinterface != null && (iinterface instanceof IServiceConnection))
                return (IServiceConnection)iinterface;
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
                parcel1.writeString("android.app.IServiceConnection");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.IServiceConnection");
                break;
            }
            IBinder ibinder;
            boolean flag;
            if(parcel.readInt() != 0)
                parcel1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            ibinder = parcel.readStrongBinder();
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            connected(parcel1, ibinder, flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.app.IServiceConnection";
        static final int TRANSACTION_connected = 1;

        public Stub()
        {
            attachInterface(this, "android.app.IServiceConnection");
        }
    }

    private static class Stub.Proxy
        implements IServiceConnection
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void connected(ComponentName componentname, IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.IServiceConnection");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel.recycle();
            throw componentname;
        }

        public String getInterfaceDescriptor()
        {
            return "android.app.IServiceConnection";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void connected(ComponentName componentname, IBinder ibinder, boolean flag)
        throws RemoteException;
}
