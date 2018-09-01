// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.juphoon.service.rcs;

import android.os.*;

public interface IRcsService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRcsService
    {

        public static IRcsService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.juphoon.service.rcs.IRcsService");
            if(iinterface != null && (iinterface instanceof IRcsService))
                return (IRcsService)iinterface;
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
            boolean flag19;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("com.juphoon.service.rcs.IRcsService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                parcel = Mtc_CliDbGetUserName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                parcel = Mtc_CliDbGetAccount();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                i = getState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                login();
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                boolean flag7 = Mtc_IsNeedSms();
                parcel1.writeNoException();
                i = ((flag6) ? 1 : 0);
                if(flag7)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                parcel = Mtc_ProfDbGetCountryCode();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                getCmccToken(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                String s = parcel.readString();
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                setCanCallSensitiveDeviceApi(s, flag8);
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                boolean flag9 = getCanCallSensitiveDeviceApi(parcel.readString());
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag9)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                String s2 = parcel.readString();
                String s1 = parcel.readString();
                boolean flag10;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                setNeedNotifySensitiveDeviceApi(s2, s1, flag10);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                boolean flag11 = getNeedNotifySensitiveDeviceApi(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag11)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                boolean flag12 = getUseRcs();
                parcel1.writeNoException();
                i = ((flag2) ? 1 : 0);
                if(flag12)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                boolean flag13 = getTranslateSms();
                parcel1.writeNoException();
                i = ((flag3) ? 1 : 0);
                if(flag13)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                boolean flag14;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                setUseRcs(flag14);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                boolean flag15;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                setTranslateSms(flag15);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                parcel = getStringForSensitive();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                parcel = Mtc_CliDbGetPcImpu();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                i = getGbaBtidKsNaf(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                boolean flag16;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                setRcsApplicationDealFirstNotify(flag16);
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                boolean flag17 = getRcsApplicationDealFirstNotify();
                parcel1.writeNoException();
                i = ((flag4) ? 1 : 0);
                if(flag17)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                boolean flag18;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                setRcsApplicationDealFirstNotifyByBroadcast(flag18);
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.juphoon.service.rcs.IRcsService");
                flag19 = getRcsApplicationDealFirstNotifyByBroadcast();
                parcel1.writeNoException();
                i = ((flag5) ? 1 : 0);
                break;
            }
            if(flag19)
                i = 1;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "com.juphoon.service.rcs.IRcsService";
        static final int TRANSACTION_Mtc_CliDbGetAccount = 2;
        static final int TRANSACTION_Mtc_CliDbGetPcImpu = 17;
        static final int TRANSACTION_Mtc_CliDbGetUserName = 1;
        static final int TRANSACTION_Mtc_IsNeedSms = 5;
        static final int TRANSACTION_Mtc_ProfDbGetCountryCode = 6;
        static final int TRANSACTION_getCanCallSensitiveDeviceApi = 9;
        static final int TRANSACTION_getCmccToken = 7;
        static final int TRANSACTION_getGbaBtidKsNaf = 18;
        static final int TRANSACTION_getNeedNotifySensitiveDeviceApi = 11;
        static final int TRANSACTION_getRcsApplicationDealFirstNotify = 20;
        static final int TRANSACTION_getRcsApplicationDealFirstNotifyByBroadcast = 22;
        static final int TRANSACTION_getState = 3;
        static final int TRANSACTION_getStringForSensitive = 16;
        static final int TRANSACTION_getTranslateSms = 13;
        static final int TRANSACTION_getUseRcs = 12;
        static final int TRANSACTION_login = 4;
        static final int TRANSACTION_setCanCallSensitiveDeviceApi = 8;
        static final int TRANSACTION_setNeedNotifySensitiveDeviceApi = 10;
        static final int TRANSACTION_setRcsApplicationDealFirstNotify = 19;
        static final int TRANSACTION_setRcsApplicationDealFirstNotifyByBroadcast = 21;
        static final int TRANSACTION_setTranslateSms = 15;
        static final int TRANSACTION_setUseRcs = 14;

        public Stub()
        {
            attachInterface(this, "com.juphoon.service.rcs.IRcsService");
        }
    }

    private static class Stub.Proxy
        implements IRcsService
    {

        public String Mtc_CliDbGetAccount()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String Mtc_CliDbGetPcImpu()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String Mtc_CliDbGetUserName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean Mtc_IsNeedSms()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
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

        public String Mtc_ProfDbGetCountryCode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean getCanCallSensitiveDeviceApi(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void getCmccToken(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            parcel.writeString(s);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getGbaBtidKsNaf(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            parcel.writeString(s);
            mRemote.transact(18, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "com.juphoon.service.rcs.IRcsService";
        }

        public boolean getNeedNotifySensitiveDeviceApi(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public boolean getRcsApplicationDealFirstNotify()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            mRemote.transact(20, parcel, parcel1, 0);
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

        public boolean getRcsApplicationDealFirstNotifyByBroadcast()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            mRemote.transact(22, parcel, parcel1, 0);
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

        public int getState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
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

        public String getStringForSensitive()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getTranslateSms()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            mRemote.transact(13, parcel, parcel1, 0);
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

        public boolean getUseRcs()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            mRemote.transact(12, parcel, parcel1, 0);
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

        public void login()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            mRemote.transact(4, parcel, parcel1, 0);
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

        public void setCanCallSensitiveDeviceApi(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setNeedNotifySensitiveDeviceApi(String s, String s1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setRcsApplicationDealFirstNotify(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
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

        public void setRcsApplicationDealFirstNotifyByBroadcast(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
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

        public void setTranslateSms(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public void setUseRcs(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.rcs.IRcsService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
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


    public abstract String Mtc_CliDbGetAccount()
        throws RemoteException;

    public abstract String Mtc_CliDbGetPcImpu()
        throws RemoteException;

    public abstract String Mtc_CliDbGetUserName()
        throws RemoteException;

    public abstract boolean Mtc_IsNeedSms()
        throws RemoteException;

    public abstract String Mtc_ProfDbGetCountryCode()
        throws RemoteException;

    public abstract boolean getCanCallSensitiveDeviceApi(String s)
        throws RemoteException;

    public abstract void getCmccToken(String s)
        throws RemoteException;

    public abstract int getGbaBtidKsNaf(String s)
        throws RemoteException;

    public abstract boolean getNeedNotifySensitiveDeviceApi(String s, String s1)
        throws RemoteException;

    public abstract boolean getRcsApplicationDealFirstNotify()
        throws RemoteException;

    public abstract boolean getRcsApplicationDealFirstNotifyByBroadcast()
        throws RemoteException;

    public abstract int getState()
        throws RemoteException;

    public abstract String getStringForSensitive()
        throws RemoteException;

    public abstract boolean getTranslateSms()
        throws RemoteException;

    public abstract boolean getUseRcs()
        throws RemoteException;

    public abstract void login()
        throws RemoteException;

    public abstract void setCanCallSensitiveDeviceApi(String s, boolean flag)
        throws RemoteException;

    public abstract void setNeedNotifySensitiveDeviceApi(String s, String s1, boolean flag)
        throws RemoteException;

    public abstract void setRcsApplicationDealFirstNotify(boolean flag)
        throws RemoteException;

    public abstract void setRcsApplicationDealFirstNotifyByBroadcast(boolean flag)
        throws RemoteException;

    public abstract void setTranslateSms(boolean flag)
        throws RemoteException;

    public abstract void setUseRcs(boolean flag)
        throws RemoteException;
}
