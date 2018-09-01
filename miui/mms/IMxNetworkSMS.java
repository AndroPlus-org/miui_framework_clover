// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mms;

import android.os.*;

public interface IMxNetworkSMS
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMxNetworkSMS
    {

        public static IMxNetworkSMS asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.mms.IMxNetworkSMS");
            if(iinterface != null && (iinterface instanceof IMxNetworkSMS))
                return (IMxNetworkSMS)iinterface;
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
            boolean flag = false;
            String s;
            String s1;
            long l;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("miui.mms.IMxNetworkSMS");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.mms.IMxNetworkSMS");
                boolean flag1 = isMxOnline(parcel.readString());
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag1)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.mms.IMxNetworkSMS");
                s = parcel.readString();
                s1 = parcel.readString();
                l = parcel.readLong();
                break;
            }
            boolean flag2;
            if(parcel.readInt() != 0)
                flag2 = true;
            else
                flag2 = false;
            parcel = sendNetwokSMS(s, s1, l, flag2, parcel.readInt());
            parcel1.writeNoException();
            parcel1.writeStringArray(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "miui.mms.IMxNetworkSMS";
        static final int TRANSACTION_isMxOnline = 1;
        static final int TRANSACTION_sendNetwokSMS = 2;

        public Stub()
        {
            attachInterface(this, "miui.mms.IMxNetworkSMS");
        }
    }

    private static class Stub.Proxy
        implements IMxNetworkSMS
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.mms.IMxNetworkSMS";
        }

        public boolean isMxOnline(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.mms.IMxNetworkSMS");
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

        public String[] sendNetwokSMS(String s, String s1, long l, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mms.IMxNetworkSMS");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeLong(l);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
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

    public abstract String[] sendNetwokSMS(String s, String s1, long l, boolean flag, int i)
        throws RemoteException;
}
