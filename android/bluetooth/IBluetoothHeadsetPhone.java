// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.bluetooth;

import android.os.*;

public interface IBluetoothHeadsetPhone
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBluetoothHeadsetPhone
    {

        public static IBluetoothHeadsetPhone asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.bluetooth.IBluetoothHeadsetPhone");
            if(iinterface != null && (iinterface instanceof IBluetoothHeadsetPhone))
                return (IBluetoothHeadsetPhone)iinterface;
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
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.bluetooth.IBluetoothHeadsetPhone");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                boolean flag6 = answerCall();
                parcel1.writeNoException();
                i = ((flag5) ? 1 : 0);
                if(flag6)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                boolean flag7 = hangupCall();
                parcel1.writeNoException();
                i = ((flag) ? 1 : 0);
                if(flag7)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                boolean flag8 = sendDtmf(parcel.readInt());
                parcel1.writeNoException();
                i = ((flag1) ? 1 : 0);
                if(flag8)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                boolean flag9 = processChld(parcel.readInt());
                parcel1.writeNoException();
                i = ((flag2) ? 1 : 0);
                if(flag9)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                parcel = getNetworkOperator();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                parcel = getSubscriberNumber();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                boolean flag10 = listCurrentCalls();
                parcel1.writeNoException();
                i = ((flag3) ? 1 : 0);
                if(flag10)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                boolean flag11 = queryPhoneState();
                parcel1.writeNoException();
                i = ((flag4) ? 1 : 0);
                if(flag11)
                    i = 1;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                updateBtHandsfreeAfterRadioTechnologyChange();
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                cdmaSwapSecondCallState();
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.bluetooth.IBluetoothHeadsetPhone");
                break;
            }
            boolean flag12;
            if(parcel.readInt() != 0)
                flag12 = true;
            else
                flag12 = false;
            cdmaSetSecondCallState(flag12);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHeadsetPhone";
        static final int TRANSACTION_answerCall = 1;
        static final int TRANSACTION_cdmaSetSecondCallState = 11;
        static final int TRANSACTION_cdmaSwapSecondCallState = 10;
        static final int TRANSACTION_getNetworkOperator = 5;
        static final int TRANSACTION_getSubscriberNumber = 6;
        static final int TRANSACTION_hangupCall = 2;
        static final int TRANSACTION_listCurrentCalls = 7;
        static final int TRANSACTION_processChld = 4;
        static final int TRANSACTION_queryPhoneState = 8;
        static final int TRANSACTION_sendDtmf = 3;
        static final int TRANSACTION_updateBtHandsfreeAfterRadioTechnologyChange = 9;

        public Stub()
        {
            attachInterface(this, "android.bluetooth.IBluetoothHeadsetPhone");
        }
    }

    private static class Stub.Proxy
        implements IBluetoothHeadsetPhone
    {

        public boolean answerCall()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
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

        public void cdmaSetSecondCallState(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void cdmaSwapSecondCallState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "android.bluetooth.IBluetoothHeadsetPhone";
        }

        public String getNetworkOperator()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public String getSubscriberNumber()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
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

        public boolean hangupCall()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
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

        public boolean listCurrentCalls()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
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

        public boolean processChld(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
            parcel.writeInt(i);
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

        public boolean queryPhoneState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
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

        public boolean sendDtmf(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
            parcel.writeInt(i);
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

        public void updateBtHandsfreeAfterRadioTechnologyChange()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.bluetooth.IBluetoothHeadsetPhone");
            mRemote.transact(9, parcel, parcel1, 0);
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


    public abstract boolean answerCall()
        throws RemoteException;

    public abstract void cdmaSetSecondCallState(boolean flag)
        throws RemoteException;

    public abstract void cdmaSwapSecondCallState()
        throws RemoteException;

    public abstract String getNetworkOperator()
        throws RemoteException;

    public abstract String getSubscriberNumber()
        throws RemoteException;

    public abstract boolean hangupCall()
        throws RemoteException;

    public abstract boolean listCurrentCalls()
        throws RemoteException;

    public abstract boolean processChld(int i)
        throws RemoteException;

    public abstract boolean queryPhoneState()
        throws RemoteException;

    public abstract boolean sendDtmf(int i)
        throws RemoteException;

    public abstract void updateBtHandsfreeAfterRadioTechnologyChange()
        throws RemoteException;
}
