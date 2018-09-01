// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas;

import android.os.*;
import java.util.List;

public interface IMQSNative
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMQSNative
    {

        public static IMQSNative asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.mqsas.IMQSNative");
            if(iinterface != null && (iinterface instanceof IMQSNative))
                return (IMQSNative)iinterface;
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
                parcel1.writeString("miui.mqsas.IMQSNative");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.mqsas.IMQSNative");
                String s = parcel.readString();
                String s1 = parcel.readString();
                java.util.ArrayList arraylist = parcel.createStringArrayList();
                java.util.ArrayList arraylist1 = parcel.createStringArrayList();
                boolean flag;
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                captureLog(s, s1, arraylist, arraylist1, flag, i, flag2, parcel.readString(), parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.mqsas.IMQSNative");
                parcel = attachSockFilter(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 3: // '\003'
                parcel.enforceInterface("miui.mqsas.IMQSNative");
                i = parcel.readInt();
                break;
            }
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            setCoreFilter(i, flag1);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "miui.mqsas.IMQSNative";
        static final int TRANSACTION_attachSockFilter = 2;
        static final int TRANSACTION_captureLog = 1;
        static final int TRANSACTION_setCoreFilter = 3;

        public Stub()
        {
            attachInterface(this, "miui.mqsas.IMQSNative");
        }
    }

    private static class Stub.Proxy
        implements IMQSNative
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public ParcelFileDescriptor attachSockFilter(String s, String s1, int i, int j, int k, int l, int i1, 
                byte abyte0[], int j1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSNative");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(j1);
            parcel.writeString(s2);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void captureLog(String s, String s1, List list, List list1, boolean flag, int i, boolean flag1, 
                String s2, List list2)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSNative");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeStringList(list);
            parcel.writeStringList(list1);
            int j;
            if(flag)
                j = 1;
            else
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s2);
            parcel.writeStringList(list2);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getInterfaceDescriptor()
        {
            return "miui.mqsas.IMQSNative";
        }

        public void setCoreFilter(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.mqsas.IMQSNative");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
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


    public abstract ParcelFileDescriptor attachSockFilter(String s, String s1, int i, int j, int k, int l, int i1, 
            byte abyte0[], int j1, String s2)
        throws RemoteException;

    public abstract void captureLog(String s, String s1, List list, List list1, boolean flag, int i, boolean flag1, 
            String s2, List list2)
        throws RemoteException;

    public abstract void setCoreFilter(int i, boolean flag)
        throws RemoteException;
}
