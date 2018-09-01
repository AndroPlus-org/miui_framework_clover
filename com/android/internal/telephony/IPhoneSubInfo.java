// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.os.*;
import android.telephony.ImsiEncryptionInfo;

public interface IPhoneSubInfo
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IPhoneSubInfo
    {

        public static IPhoneSubInfo asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.IPhoneSubInfo");
            if(iinterface != null && (iinterface instanceof IPhoneSubInfo))
                return (IPhoneSubInfo)iinterface;
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
                parcel1.writeString("com.android.internal.telephony.IPhoneSubInfo");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getDeviceId(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getNaiForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getDeviceIdForPhone(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getImeiForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getDeviceSvn(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getDeviceSvnUsingSubId(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getSubscriberId(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getSubscriberIdForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getGroupIdLevel1(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getGroupIdLevel1ForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getIccSerialNumber(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getIccSerialNumberForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getLine1Number(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getLine1NumberForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getLine1AlphaTag(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getLine1AlphaTagForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getMsisdn(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getMsisdnForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getVoiceMailNumber(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getVoiceMailNumberForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getCompleteVoiceMailNumber();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getCompleteVoiceMailNumberForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getCarrierInfoForImsiEncryption(parcel.readInt(), parcel.readInt(), parcel.readString());
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
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                i = parcel.readInt();
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ImsiEncryptionInfo)ImsiEncryptionInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setCarrierInfoForImsiEncryption(i, s, parcel);
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getVoiceMailAlphaTag(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getVoiceMailAlphaTagForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getIsimImpi();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getIsimDomain();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getIsimImpu();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getIsimIst();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getIsimPcscf();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getIsimChallengeResponse(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.android.internal.telephony.IPhoneSubInfo");
                parcel = getIccSimChallengeResponse(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.telephony.IPhoneSubInfo";
        static final int TRANSACTION_getCarrierInfoForImsiEncryption = 23;
        static final int TRANSACTION_getCompleteVoiceMailNumber = 21;
        static final int TRANSACTION_getCompleteVoiceMailNumberForSubscriber = 22;
        static final int TRANSACTION_getDeviceId = 1;
        static final int TRANSACTION_getDeviceIdForPhone = 3;
        static final int TRANSACTION_getDeviceSvn = 5;
        static final int TRANSACTION_getDeviceSvnUsingSubId = 6;
        static final int TRANSACTION_getGroupIdLevel1 = 9;
        static final int TRANSACTION_getGroupIdLevel1ForSubscriber = 10;
        static final int TRANSACTION_getIccSerialNumber = 11;
        static final int TRANSACTION_getIccSerialNumberForSubscriber = 12;
        static final int TRANSACTION_getIccSimChallengeResponse = 33;
        static final int TRANSACTION_getImeiForSubscriber = 4;
        static final int TRANSACTION_getIsimChallengeResponse = 32;
        static final int TRANSACTION_getIsimDomain = 28;
        static final int TRANSACTION_getIsimImpi = 27;
        static final int TRANSACTION_getIsimImpu = 29;
        static final int TRANSACTION_getIsimIst = 30;
        static final int TRANSACTION_getIsimPcscf = 31;
        static final int TRANSACTION_getLine1AlphaTag = 15;
        static final int TRANSACTION_getLine1AlphaTagForSubscriber = 16;
        static final int TRANSACTION_getLine1Number = 13;
        static final int TRANSACTION_getLine1NumberForSubscriber = 14;
        static final int TRANSACTION_getMsisdn = 17;
        static final int TRANSACTION_getMsisdnForSubscriber = 18;
        static final int TRANSACTION_getNaiForSubscriber = 2;
        static final int TRANSACTION_getSubscriberId = 7;
        static final int TRANSACTION_getSubscriberIdForSubscriber = 8;
        static final int TRANSACTION_getVoiceMailAlphaTag = 25;
        static final int TRANSACTION_getVoiceMailAlphaTagForSubscriber = 26;
        static final int TRANSACTION_getVoiceMailNumber = 19;
        static final int TRANSACTION_getVoiceMailNumberForSubscriber = 20;
        static final int TRANSACTION_setCarrierInfoForImsiEncryption = 24;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.IPhoneSubInfo");
        }
    }

    private static class Stub.Proxy
        implements IPhoneSubInfo
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public ImsiEncryptionInfo getCarrierInfoForImsiEncryption(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ImsiEncryptionInfo)ImsiEncryptionInfo.CREATOR.createFromParcel(parcel1);
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

        public String getCompleteVoiceMailNumber()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            mRemote.transact(21, parcel, parcel1, 0);
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

        public String getCompleteVoiceMailNumberForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public String getDeviceId(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeString(s);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public String getDeviceIdForPhone(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public String getDeviceSvn(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public String getDeviceSvnUsingSubId(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public String getGroupIdLevel1(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeString(s);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public String getGroupIdLevel1ForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public String getIccSerialNumber(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeString(s);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public String getIccSerialNumberForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(12, parcel, parcel1, 0);
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

        public String getIccSimChallengeResponse(int i, int j, int k, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s);
            mRemote.transact(33, parcel, parcel1, 0);
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

        public String getImeiForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.IPhoneSubInfo";
        }

        public String getIsimChallengeResponse(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeString(s);
            mRemote.transact(32, parcel, parcel1, 0);
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

        public String getIsimDomain()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            mRemote.transact(28, parcel, parcel1, 0);
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

        public String getIsimImpi()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            mRemote.transact(27, parcel, parcel1, 0);
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

        public String[] getIsimImpu()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getIsimIst()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            mRemote.transact(30, parcel, parcel1, 0);
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

        public String[] getIsimPcscf()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            as = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return as;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getLine1AlphaTag(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeString(s);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public String getLine1AlphaTagForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
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

        public String getLine1Number(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeString(s);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public String getLine1NumberForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public String getMsisdn(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
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

        public String getMsisdnForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
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

        public String getNaiForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public String getSubscriberId(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeString(s);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public String getSubscriberIdForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public String getVoiceMailAlphaTag(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeString(s);
            mRemote.transact(25, parcel, parcel1, 0);
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

        public String getVoiceMailAlphaTagForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(26, parcel, parcel1, 0);
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

        public String getVoiceMailNumber(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
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

        public String getVoiceMailNumberForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public void setCarrierInfoForImsiEncryption(int i, String s, ImsiEncryptionInfo imsiencryptioninfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.IPhoneSubInfo");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(imsiencryptioninfo == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            imsiencryptioninfo.writeToParcel(parcel, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract ImsiEncryptionInfo getCarrierInfoForImsiEncryption(int i, int j, String s)
        throws RemoteException;

    public abstract String getCompleteVoiceMailNumber()
        throws RemoteException;

    public abstract String getCompleteVoiceMailNumberForSubscriber(int i)
        throws RemoteException;

    public abstract String getDeviceId(String s)
        throws RemoteException;

    public abstract String getDeviceIdForPhone(int i, String s)
        throws RemoteException;

    public abstract String getDeviceSvn(String s)
        throws RemoteException;

    public abstract String getDeviceSvnUsingSubId(int i, String s)
        throws RemoteException;

    public abstract String getGroupIdLevel1(String s)
        throws RemoteException;

    public abstract String getGroupIdLevel1ForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getIccSerialNumber(String s)
        throws RemoteException;

    public abstract String getIccSerialNumberForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getIccSimChallengeResponse(int i, int j, int k, String s)
        throws RemoteException;

    public abstract String getImeiForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getIsimChallengeResponse(String s)
        throws RemoteException;

    public abstract String getIsimDomain()
        throws RemoteException;

    public abstract String getIsimImpi()
        throws RemoteException;

    public abstract String[] getIsimImpu()
        throws RemoteException;

    public abstract String getIsimIst()
        throws RemoteException;

    public abstract String[] getIsimPcscf()
        throws RemoteException;

    public abstract String getLine1AlphaTag(String s)
        throws RemoteException;

    public abstract String getLine1AlphaTagForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getLine1Number(String s)
        throws RemoteException;

    public abstract String getLine1NumberForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getMsisdn(String s)
        throws RemoteException;

    public abstract String getMsisdnForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getNaiForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getSubscriberId(String s)
        throws RemoteException;

    public abstract String getSubscriberIdForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getVoiceMailAlphaTag(String s)
        throws RemoteException;

    public abstract String getVoiceMailAlphaTagForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getVoiceMailNumber(String s)
        throws RemoteException;

    public abstract String getVoiceMailNumberForSubscriber(int i, String s)
        throws RemoteException;

    public abstract void setCarrierInfoForImsiEncryption(int i, String s, ImsiEncryptionInfo imsiencryptioninfo)
        throws RemoteException;
}
