// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;

import android.os.*;
import java.util.List;

public interface IMiuiTelephony
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IMiuiTelephony
    {

        public static IMiuiTelephony asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.telephony.IMiuiTelephony");
            if(iinterface != null && (iinterface instanceof IMiuiTelephony))
                return (IMiuiTelephony)iinterface;
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
            String s1;
            int k;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("miui.telephony.IMiuiTelephony");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                setDefaultVoiceSlotId(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                boolean flag = setDefaultDataSlotId(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                boolean flag1 = isImsRegistered(parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                boolean flag2 = isVolteEnabledByUser();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                boolean flag3 = isVolteEnabledByUserForSlot(parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                boolean flag4 = isVtEnabledByPlatform();
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                boolean flag5 = isVtEnabledByPlatformForSlot(parcel.readInt());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                boolean flag6 = isVolteEnabledByPlatform();
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                boolean flag7 = isVolteEnabledByPlatformForSlot(parcel.readInt());
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                i = getSystemDefaultSlotId();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                boolean flag8 = isIccCardActivate(parcel.readInt());
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                i = parcel.readInt();
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                setIccCardActivate(i, flag9);
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                parcel = getDeviceIdList(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                parcel = getImeiList(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                parcel = getMeidList(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                parcel = getDeviceId(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                parcel = getImei(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                parcel = getMeid(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                parcel = getSmallDeviceId(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                boolean flag10 = isSameOperator(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                String s = parcel.readString();
                i = parcel.readInt();
                String s2 = parcel.readString();
                boolean flag11;
                if(parcel.readInt() != 0)
                    flag11 = true;
                else
                    flag11 = false;
                parcel = getSpn(s, i, s2, flag11);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                parcel = onOperatorNumericOrNameSet(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                parcel = getCellLocationForSlot(parcel.readInt(), parcel.readString());
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

            case 24: // '\030'
                parcel.enforceInterface("miui.telephony.IMiuiTelephony");
                i = parcel.readInt();
                k = parcel.readInt();
                j = parcel.readInt();
                s1 = parcel.readString();
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            setCallForwardingOption(i, k, j, s1, parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "miui.telephony.IMiuiTelephony";
        static final int TRANSACTION_getCellLocationForSlot = 23;
        static final int TRANSACTION_getDeviceId = 16;
        static final int TRANSACTION_getDeviceIdList = 13;
        static final int TRANSACTION_getImei = 17;
        static final int TRANSACTION_getImeiList = 14;
        static final int TRANSACTION_getMeid = 18;
        static final int TRANSACTION_getMeidList = 15;
        static final int TRANSACTION_getSmallDeviceId = 19;
        static final int TRANSACTION_getSpn = 21;
        static final int TRANSACTION_getSystemDefaultSlotId = 10;
        static final int TRANSACTION_isIccCardActivate = 11;
        static final int TRANSACTION_isImsRegistered = 3;
        static final int TRANSACTION_isSameOperator = 20;
        static final int TRANSACTION_isVolteEnabledByPlatform = 8;
        static final int TRANSACTION_isVolteEnabledByPlatformForSlot = 9;
        static final int TRANSACTION_isVolteEnabledByUser = 4;
        static final int TRANSACTION_isVolteEnabledByUserForSlot = 5;
        static final int TRANSACTION_isVtEnabledByPlatform = 6;
        static final int TRANSACTION_isVtEnabledByPlatformForSlot = 7;
        static final int TRANSACTION_onOperatorNumericOrNameSet = 22;
        static final int TRANSACTION_setCallForwardingOption = 24;
        static final int TRANSACTION_setDefaultDataSlotId = 2;
        static final int TRANSACTION_setDefaultVoiceSlotId = 1;
        static final int TRANSACTION_setIccCardActivate = 12;

        public Stub()
        {
            attachInterface(this, "miui.telephony.IMiuiTelephony");
        }
    }

    private static class Stub.Proxy
        implements IMiuiTelephony
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public Bundle getCellLocationForSlot(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
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

        public String getDeviceId(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeString(s);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getDeviceIdList(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeString(s);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public String getImei(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getImeiList(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "miui.telephony.IMiuiTelephony";
        }

        public String getMeid(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getMeidList(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeString(s);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public String getSmallDeviceId(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeString(s);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getSpn(String s, int i, String s1, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getSystemDefaultSlotId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            mRemote.transact(10, parcel, parcel1, 0);
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

        public boolean isIccCardActivate(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isImsRegistered(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
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

        public boolean isSameOperator(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeString(s);
            parcel.writeString(s1);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isVolteEnabledByPlatform()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
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

        public boolean isVolteEnabledByPlatformForSlot(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
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

        public boolean isVolteEnabledByUser()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
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

        public boolean isVolteEnabledByUserForSlot(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
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

        public boolean isVtEnabledByPlatform()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
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

        public boolean isVtEnabledByPlatformForSlot(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
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

        public String onOperatorNumericOrNameSet(int i, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setCallForwardingOption(int i, int j, int k, String s, ResultReceiver resultreceiver)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean setDefaultDataSlotId(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
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

        public void setDefaultVoiceSlotId(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
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

        public void setIccCardActivate(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.telephony.IMiuiTelephony");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
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


    public abstract Bundle getCellLocationForSlot(int i, String s)
        throws RemoteException;

    public abstract String getDeviceId(String s)
        throws RemoteException;

    public abstract List getDeviceIdList(String s)
        throws RemoteException;

    public abstract String getImei(int i, String s)
        throws RemoteException;

    public abstract List getImeiList(String s)
        throws RemoteException;

    public abstract String getMeid(int i, String s)
        throws RemoteException;

    public abstract List getMeidList(String s)
        throws RemoteException;

    public abstract String getSmallDeviceId(String s)
        throws RemoteException;

    public abstract String getSpn(String s, int i, String s1, boolean flag)
        throws RemoteException;

    public abstract int getSystemDefaultSlotId()
        throws RemoteException;

    public abstract boolean isIccCardActivate(int i)
        throws RemoteException;

    public abstract boolean isImsRegistered(int i)
        throws RemoteException;

    public abstract boolean isSameOperator(String s, String s1)
        throws RemoteException;

    public abstract boolean isVolteEnabledByPlatform()
        throws RemoteException;

    public abstract boolean isVolteEnabledByPlatformForSlot(int i)
        throws RemoteException;

    public abstract boolean isVolteEnabledByUser()
        throws RemoteException;

    public abstract boolean isVolteEnabledByUserForSlot(int i)
        throws RemoteException;

    public abstract boolean isVtEnabledByPlatform()
        throws RemoteException;

    public abstract boolean isVtEnabledByPlatformForSlot(int i)
        throws RemoteException;

    public abstract String onOperatorNumericOrNameSet(int i, String s, String s1)
        throws RemoteException;

    public abstract void setCallForwardingOption(int i, int j, int k, String s, ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract boolean setDefaultDataSlotId(int i, String s)
        throws RemoteException;

    public abstract void setDefaultVoiceSlotId(int i, String s)
        throws RemoteException;

    public abstract void setIccCardActivate(int i, boolean flag)
        throws RemoteException;
}
