// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.content.Intent;
import android.os.*;

public interface IWapPushManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWapPushManager
    {

        public static IWapPushManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.IWapPushManager");
            if(iinterface != null && (iinterface instanceof IWapPushManager))
                return (IWapPushManager)iinterface;
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
            boolean flag2;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.android.internal.telephony.IWapPushManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.IWapPushManager");
                String s = parcel.readString();
                String s3 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = processMessage(s, s3, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telephony.IWapPushManager");
                String s4 = parcel.readString();
                String s6 = parcel.readString();
                String s1 = parcel.readString();
                String s8 = parcel.readString();
                i = parcel.readInt();
                boolean flag;
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                flag = addPackage(s4, s6, s1, s8, i, flag, flag3);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telephony.IWapPushManager");
                String s2 = parcel.readString();
                String s7 = parcel.readString();
                String s5 = parcel.readString();
                String s9 = parcel.readString();
                i = parcel.readInt();
                boolean flag1;
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                flag1 = updatePackage(s2, s7, s5, s9, i, flag1, flag4);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telephony.IWapPushManager");
                flag2 = deletePackage(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                break;
            }
            if(flag2)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.telephony.IWapPushManager";
        static final int TRANSACTION_addPackage = 2;
        static final int TRANSACTION_deletePackage = 4;
        static final int TRANSACTION_processMessage = 1;
        static final int TRANSACTION_updatePackage = 3;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.IWapPushManager");
        }
    }

    private static class Stub.Proxy
        implements IWapPushManager
    {

        public boolean addPackage(String s, String s1, String s2, String s3, int i, boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IWapPushManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeInt(i);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean deletePackage(String s, String s1, String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.IWapPushManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.IWapPushManager";
        }

        public int processMessage(String s, String s1, Intent intent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IWapPushManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(intent == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean updatePackage(String s, String s1, String s2, String s3, int i, boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IWapPushManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeInt(i);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
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


    public abstract boolean addPackage(String s, String s1, String s2, String s3, int i, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract boolean deletePackage(String s, String s1, String s2, String s3)
        throws RemoteException;

    public abstract int processMessage(String s, String s1, Intent intent)
        throws RemoteException;

    public abstract boolean updatePackage(String s, String s1, String s2, String s3, int i, boolean flag, boolean flag1)
        throws RemoteException;
}
