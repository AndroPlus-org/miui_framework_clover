// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mms;

import android.os.*;

public interface IMxStatusService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMxStatusService
    {

        public static IMxStatusService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.mms.IMxStatusService");
            if(iinterface != null && (iinterface instanceof IMxStatusService))
                return (IMxStatusService)iinterface;
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
                parcel1.writeString("miui.mms.IMxStatusService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.mms.IMxStatusService");
                flag = isMxOnline(parcel.readString());
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

        private static final String DESCRIPTOR = "miui.mms.IMxStatusService";
        static final int TRANSACTION_isMxOnline = 1;

        public Stub()
        {
            attachInterface(this, "miui.mms.IMxStatusService");
        }
    }

    private static class Stub.Proxy
        implements IMxStatusService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.mms.IMxStatusService";
        }

        public boolean isMxOnline(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.mms.IMxStatusService");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean isMxOnline(String s)
        throws RemoteException;
}
