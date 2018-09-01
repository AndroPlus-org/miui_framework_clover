// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.whetstone;

import android.os.*;

public interface IWhetstoneSysInfoService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWhetstoneSysInfoService
    {

        public static IWhetstoneSysInfoService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.miui.whetstone.IWhetstoneSysInfoService");
            if(iinterface != null && (iinterface instanceof IWhetstoneSysInfoService))
                return (IWhetstoneSysInfoService)iinterface;
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
                parcel1.writeString("com.miui.whetstone.IWhetstoneSysInfoService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.miui.whetstone.IWhetstoneSysInfoService");
                long l = getSysInfo(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.miui.whetstone.IWhetstoneSysInfoService");
                parcel = getSysInfos(parcel.createStringArray());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.miui.whetstone.IWhetstoneSysInfoService";
        static final int TRANSACTION_getSysInfo = 1;
        static final int TRANSACTION_getSysInfos = 2;

        public Stub()
        {
            attachInterface(this, "com.miui.whetstone.IWhetstoneSysInfoService");
        }
    }

    private static class Stub.Proxy
        implements IWhetstoneSysInfoService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.miui.whetstone.IWhetstoneSysInfoService";
        }

        public long getSysInfo(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.miui.whetstone.IWhetstoneSysInfoService");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String[] getSysInfos(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.miui.whetstone.IWhetstoneSysInfoService");
            parcel.writeStringArray(as);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract long getSysInfo(String s)
        throws RemoteException;

    public abstract String[] getSysInfos(String as[])
        throws RemoteException;
}
