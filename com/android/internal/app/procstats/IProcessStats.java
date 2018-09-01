// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app.procstats;

import android.os.*;
import java.util.ArrayList;
import java.util.List;

public interface IProcessStats
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IProcessStats
    {

        public static IProcessStats asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.procstats.IProcessStats");
            if(iinterface != null && (iinterface instanceof IProcessStats))
                return (IProcessStats)iinterface;
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
                parcel1.writeString("com.android.internal.app.procstats.IProcessStats");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.procstats.IProcessStats");
                ArrayList arraylist = new ArrayList();
                parcel = getCurrentStats(arraylist);
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                parcel1.writeTypedList(arraylist);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.procstats.IProcessStats");
                parcel = getStatsOverTime(parcel.readLong());
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
                parcel.enforceInterface("com.android.internal.app.procstats.IProcessStats");
                i = getCurrentMemoryState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.app.procstats.IProcessStats";
        static final int TRANSACTION_getCurrentMemoryState = 3;
        static final int TRANSACTION_getCurrentStats = 1;
        static final int TRANSACTION_getStatsOverTime = 2;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.procstats.IProcessStats");
        }
    }

    private static class Stub.Proxy
        implements IProcessStats
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int getCurrentMemoryState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.procstats.IProcessStats");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public byte[] getCurrentStats(List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("com.android.internal.app.procstats.IProcessStats");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            abyte0 = parcel1.createByteArray();
            parcel1.readTypedList(list, ParcelFileDescriptor.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.procstats.IProcessStats";
        }

        public ParcelFileDescriptor getStatsOverTime(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.procstats.IProcessStats");
            parcel.writeLong(l);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParcelFileDescriptor parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parcelfiledescriptor;
_L2:
            parcelfiledescriptor = null;
            if(true) goto _L4; else goto _L3
_L3:
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


    public abstract int getCurrentMemoryState()
        throws RemoteException;

    public abstract byte[] getCurrentStats(List list)
        throws RemoteException;

    public abstract ParcelFileDescriptor getStatsOverTime(long l)
        throws RemoteException;
}
