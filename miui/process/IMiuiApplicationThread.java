// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.process;

import android.os.*;

public interface IMiuiApplicationThread
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiApplicationThread
    {

        public static IMiuiApplicationThread asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.process.IMiuiApplicationThread");
            if(iinterface != null && (iinterface instanceof IMiuiApplicationThread))
                return (IMiuiApplicationThread)iinterface;
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
            boolean flag;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("miui.process.IMiuiApplicationThread");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.process.IMiuiApplicationThread");
                flag = longScreenshot(parcel.readInt());
                parcel1.writeNoException();
                break;
            }
            if(flag)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "miui.process.IMiuiApplicationThread";
        static final int TRANSACTION_longScreenshot = 1;

        public Stub()
        {
            attachInterface(this, "miui.process.IMiuiApplicationThread");
        }
    }

    private static class Stub.Proxy
        implements IMiuiApplicationThread
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.process.IMiuiApplicationThread";
        }

        public boolean longScreenshot(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.process.IMiuiApplicationThread");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean longScreenshot(int i)
        throws RemoteException;
}
