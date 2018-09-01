// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.*;
import java.util.List;

public interface IRegionalizationService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRegionalizationService
    {

        public static IRegionalizationService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.os.IRegionalizationService");
            if(iinterface != null && (iinterface instanceof IRegionalizationService))
                return (IRegionalizationService)iinterface;
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
            boolean flag1 = false;
            String s1;
            String s3;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.os.IRegionalizationService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.os.IRegionalizationService");
                boolean flag2 = checkFileExists(parcel.readString());
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag2)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.os.IRegionalizationService");
                parcel = readFile(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.os.IRegionalizationService");
                String s = parcel.readString();
                String s2 = parcel.readString();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                flag3 = writeFile(s, s2, flag3);
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag3)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.os.IRegionalizationService");
                s1 = parcel.readString();
                s3 = parcel.readString();
                break;
            }
            boolean flag4;
            if(parcel.readInt() != 0)
                flag4 = true;
            else
                flag4 = false;
            deleteFilesUnderDir(s1, s3, flag4);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.os.IRegionalizationService";
        static final int TRANSACTION_checkFileExists = 1;
        static final int TRANSACTION_deleteFilesUnderDir = 4;
        static final int TRANSACTION_readFile = 2;
        static final int TRANSACTION_writeFile = 3;

        public Stub()
        {
            attachInterface(this, "com.android.internal.os.IRegionalizationService");
        }
    }

    private static class Stub.Proxy
        implements IRegionalizationService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean checkFileExists(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.os.IRegionalizationService");
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

        public void deleteFilesUnderDir(String s, String s1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.os.IRegionalizationService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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
            return "com.android.internal.os.IRegionalizationService";
        }

        public List readFile(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.os.IRegionalizationService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean writeFile(String s, String s1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.os.IRegionalizationService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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


    public abstract boolean checkFileExists(String s)
        throws RemoteException;

    public abstract void deleteFilesUnderDir(String s, String s1, boolean flag)
        throws RemoteException;

    public abstract List readFile(String s, String s1)
        throws RemoteException;

    public abstract boolean writeFile(String s, String s1, boolean flag)
        throws RemoteException;
}
