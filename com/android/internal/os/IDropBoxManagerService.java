// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.*;

public interface IDropBoxManagerService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDropBoxManagerService
    {

        public static IDropBoxManagerService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.os.IDropBoxManagerService");
            if(iinterface != null && (iinterface instanceof IDropBoxManagerService))
                return (IDropBoxManagerService)iinterface;
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
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.os.IDropBoxManagerService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.os.IDropBoxManagerService");
                if(parcel.readInt() != 0)
                    parcel = (android.os.DropBoxManager.Entry)android.os.DropBoxManager.Entry.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                add(parcel);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.os.IDropBoxManagerService");
                boolean flag1 = isTagEnabled(parcel.readString());
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag1)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.os.IDropBoxManagerService");
                parcel = getNextEntry(parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                break;
            }
            if(parcel != null)
            {
                parcel1.writeInt(1);
                parcel.writeToParcel(parcel1, 1);
            } else
            {
                parcel1.writeInt(0);
            }
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.os.IDropBoxManagerService";
        static final int TRANSACTION_add = 1;
        static final int TRANSACTION_getNextEntry = 3;
        static final int TRANSACTION_isTagEnabled = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.os.IDropBoxManagerService");
        }
    }

    private static class Stub.Proxy
        implements IDropBoxManagerService
    {

        public void add(android.os.DropBoxManager.Entry entry)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.os.IDropBoxManagerService");
            if(entry == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            entry.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            entry;
            parcel1.recycle();
            parcel.recycle();
            throw entry;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.os.IDropBoxManagerService";
        }

        public android.os.DropBoxManager.Entry getNextEntry(String s, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.os.IDropBoxManagerService");
            parcel.writeString(s);
            parcel.writeLong(l);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (android.os.DropBoxManager.Entry)android.os.DropBoxManager.Entry.CREATOR.createFromParcel(parcel1);
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

        public boolean isTagEnabled(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.os.IDropBoxManagerService");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
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


    public abstract void add(android.os.DropBoxManager.Entry entry)
        throws RemoteException;

    public abstract android.os.DropBoxManager.Entry getNextEntry(String s, long l)
        throws RemoteException;

    public abstract boolean isTagEnabled(String s)
        throws RemoteException;
}
