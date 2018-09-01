// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.juphoon.service.cap;

import android.os.*;

public interface IRcsCapService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRcsCapService
    {

        public static IRcsCapService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.juphoon.service.cap.IRcsCapService");
            if(iinterface != null && (iinterface instanceof IRcsCapService))
                return (IRcsCapService)iinterface;
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
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            boolean flag5 = false;
            boolean flag6 = false;
            boolean flag7 = false;
            boolean flag8 = false;
            boolean flag17;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.juphoon.service.cap.IRcsCapService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.juphoon.service.cap.IRcsCapService");
                i = Mtc_CapQryOneImd(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.juphoon.service.cap.IRcsCapService");
                boolean flag9 = Mtc_CapDbGetCapFTEn();
                parcel1.writeNoException();
                i = ((flag8) ? 1 : 0);
                if(flag9)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.juphoon.service.cap.IRcsCapService");
                boolean flag10 = Mtc_CapDbGetCapVoicCallEn();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag10)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.juphoon.service.cap.IRcsCapService");
                boolean flag11 = Mtc_CapDbGetCapVideoCallEn();
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag11)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.juphoon.service.cap.IRcsCapService");
                boolean flag12 = Mtc_CapDbGetCapGeoPullEn();
                parcel1.writeNoException();
                i = ((flag2) ? 1 : 0);
                if(flag12)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.juphoon.service.cap.IRcsCapService");
                boolean flag13 = Mtc_CapDbGetCapGeoPushEn();
                parcel1.writeNoException();
                i = ((flag3) ? 1 : 0);
                if(flag13)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.juphoon.service.cap.IRcsCapService");
                boolean flag14 = Mtc_CapDbGetCapIMEn();
                parcel1.writeNoException();
                i = ((flag4) ? 1 : 0);
                if(flag14)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.juphoon.service.cap.IRcsCapService");
                boolean flag15 = Mtc_CapDbGetCapBurnEn();
                parcel1.writeNoException();
                i = ((flag5) ? 1 : 0);
                if(flag15)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.juphoon.service.cap.IRcsCapService");
                boolean flag16 = Mtc_CapDbGetCapVemEn();
                parcel1.writeNoException();
                i = ((flag6) ? 1 : 0);
                if(flag16)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.juphoon.service.cap.IRcsCapService");
                flag17 = Mtc_CapDbGetCapPubMsgEn();
                parcel1.writeNoException();
                i = ((flag7) ? 1 : 0);
                break;
            }
            if(flag17)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "com.juphoon.service.cap.IRcsCapService";
        static final int TRANSACTION_Mtc_CapDbGetCapBurnEn = 8;
        static final int TRANSACTION_Mtc_CapDbGetCapFTEn = 2;
        static final int TRANSACTION_Mtc_CapDbGetCapGeoPullEn = 5;
        static final int TRANSACTION_Mtc_CapDbGetCapGeoPushEn = 6;
        static final int TRANSACTION_Mtc_CapDbGetCapIMEn = 7;
        static final int TRANSACTION_Mtc_CapDbGetCapPubMsgEn = 10;
        static final int TRANSACTION_Mtc_CapDbGetCapVemEn = 9;
        static final int TRANSACTION_Mtc_CapDbGetCapVideoCallEn = 4;
        static final int TRANSACTION_Mtc_CapDbGetCapVoicCallEn = 3;
        static final int TRANSACTION_Mtc_CapQryOneImd = 1;

        public Stub()
        {
            attachInterface(this, "com.juphoon.service.cap.IRcsCapService");
        }
    }

    private static class Stub.Proxy
        implements IRcsCapService
    {

        public boolean Mtc_CapDbGetCapBurnEn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cap.IRcsCapService");
            mRemote.transact(8, parcel, parcel1, 0);
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

        public boolean Mtc_CapDbGetCapFTEn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cap.IRcsCapService");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean Mtc_CapDbGetCapGeoPullEn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cap.IRcsCapService");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public boolean Mtc_CapDbGetCapGeoPushEn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cap.IRcsCapService");
            mRemote.transact(6, parcel, parcel1, 0);
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

        public boolean Mtc_CapDbGetCapIMEn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cap.IRcsCapService");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public boolean Mtc_CapDbGetCapPubMsgEn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cap.IRcsCapService");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean Mtc_CapDbGetCapVemEn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cap.IRcsCapService");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public boolean Mtc_CapDbGetCapVideoCallEn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cap.IRcsCapService");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean Mtc_CapDbGetCapVoicCallEn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cap.IRcsCapService");
            mRemote.transact(3, parcel, parcel1, 0);
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

        public int Mtc_CapQryOneImd(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cap.IRcsCapService");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.juphoon.service.cap.IRcsCapService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract boolean Mtc_CapDbGetCapBurnEn()
        throws RemoteException;

    public abstract boolean Mtc_CapDbGetCapFTEn()
        throws RemoteException;

    public abstract boolean Mtc_CapDbGetCapGeoPullEn()
        throws RemoteException;

    public abstract boolean Mtc_CapDbGetCapGeoPushEn()
        throws RemoteException;

    public abstract boolean Mtc_CapDbGetCapIMEn()
        throws RemoteException;

    public abstract boolean Mtc_CapDbGetCapPubMsgEn()
        throws RemoteException;

    public abstract boolean Mtc_CapDbGetCapVemEn()
        throws RemoteException;

    public abstract boolean Mtc_CapDbGetCapVideoCallEn()
        throws RemoteException;

    public abstract boolean Mtc_CapDbGetCapVoicCallEn()
        throws RemoteException;

    public abstract int Mtc_CapQryOneImd(String s)
        throws RemoteException;
}
