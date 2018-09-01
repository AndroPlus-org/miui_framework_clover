// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telecom;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.telecom.*;
import java.util.List;

public interface ITelecomService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITelecomService
    {

        public static ITelecomService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telecom.ITelecomService");
            if(iinterface != null && (iinterface instanceof ITelecomService))
                return (ITelecomService)iinterface;
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
                parcel1.writeString("com.android.internal.telecom.ITelecomService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                showInCallScreen(flag, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getDefaultOutgoingPhoneAccount(parcel.readString(), parcel.readString());
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
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getUserSelectedOutgoingPhoneAccount();
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

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                if(parcel.readInt() != 0)
                    parcel = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setUserSelectedOutgoingPhoneAccount(parcel);
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                parcel = getCallCapablePhoneAccounts(flag1, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getSelfManagedPhoneAccounts(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getPhoneAccountsSupportingScheme(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getPhoneAccountsForPackage(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                if(parcel.readInt() != 0)
                    parcel = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPhoneAccount(parcel);
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

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                i = getAllPhoneAccountsCount();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getAllPhoneAccounts();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getAllPhoneAccountHandles();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getSimCallManager();
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

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getSimCallManagerForUser(parcel.readInt());
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

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                if(parcel.readInt() != 0)
                    parcel = (PhoneAccount)PhoneAccount.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                registerPhoneAccount(parcel);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                if(parcel.readInt() != 0)
                    parcel = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                unregisterPhoneAccount(parcel);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                clearAccounts(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag2;
                PhoneAccountHandle phoneaccounthandle;
                if(parcel.readInt() != 0)
                    phoneaccounthandle = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    phoneaccounthandle = null;
                flag2 = isVoiceMailNumber(phoneaccounthandle, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                PhoneAccountHandle phoneaccounthandle1;
                if(parcel.readInt() != 0)
                    phoneaccounthandle1 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    phoneaccounthandle1 = null;
                parcel = getVoiceMailNumber(phoneaccounthandle1, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                PhoneAccountHandle phoneaccounthandle2;
                if(parcel.readInt() != 0)
                    phoneaccounthandle2 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    phoneaccounthandle2 = null;
                parcel = getLine1Number(phoneaccounthandle2, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getDefaultPhoneApp();
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

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getDefaultDialerPackage();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = getSystemDialerPackage();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = dumpCallAnalytics();
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

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                silenceRinger(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag3 = isInCall(parcel.readString());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag4 = isInManagedCall(parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag5 = isRinging(parcel.readString());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                i = getCallState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag6 = endCall();
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                acceptRingingCall(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                acceptRingingCallWithVideoState(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                cancelMissedCallsNotification(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag7 = handlePinMmi(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 35: // '#'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag8;
                PhoneAccountHandle phoneaccounthandle3;
                if(parcel.readInt() != 0)
                    phoneaccounthandle3 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    phoneaccounthandle3 = null;
                flag8 = handlePinMmiForPhoneAccount(phoneaccounthandle3, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 36: // '$'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                PhoneAccountHandle phoneaccounthandle4;
                if(parcel.readInt() != 0)
                    phoneaccounthandle4 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    phoneaccounthandle4 = null;
                parcel = getAdnUriForPhoneAccount(phoneaccounthandle4, parcel.readString());
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

            case 37: // '%'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag9 = isTtySupported(parcel.readString());
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 38: // '&'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                i = getCurrentTtyMode(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 39: // '\''
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                PhoneAccountHandle phoneaccounthandle5;
                if(parcel.readInt() != 0)
                    phoneaccounthandle5 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    phoneaccounthandle5 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addNewIncomingCall(phoneaccounthandle5, parcel);
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                PhoneAccountHandle phoneaccounthandle6;
                if(parcel.readInt() != 0)
                    phoneaccounthandle6 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    phoneaccounthandle6 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addNewUnknownCall(phoneaccounthandle6, parcel);
                parcel1.writeNoException();
                return true;

            case 41: // ')'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                Uri uri;
                Bundle bundle;
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                if(parcel.readInt() != 0)
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    bundle = null;
                placeCall(uri, bundle, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag10;
                PhoneAccountHandle phoneaccounthandle7;
                if(parcel.readInt() != 0)
                    phoneaccounthandle7 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    phoneaccounthandle7 = null;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                flag10 = enablePhoneAccount(phoneaccounthandle7, flag10);
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 43: // '+'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag11 = setDefaultDialer(parcel.readString());
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 44: // ','
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                parcel = createManageBlockedNumbersIntent();
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

            case 45: // '-'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag12;
                if(parcel.readInt() != 0)
                    parcel = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag12 = isIncomingCallPermitted(parcel);
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 46: // '.'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                boolean flag13;
                if(parcel.readInt() != 0)
                    parcel = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag13 = isOutgoingCallPermitted(parcel);
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 47: // '/'
                parcel.enforceInterface("com.android.internal.telecom.ITelecomService");
                waitOnHandlers();
                parcel1.writeNoException();
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.android.internal.telecom.ITelecomService";
        static final int TRANSACTION_acceptRingingCall = 31;
        static final int TRANSACTION_acceptRingingCallWithVideoState = 32;
        static final int TRANSACTION_addNewIncomingCall = 39;
        static final int TRANSACTION_addNewUnknownCall = 40;
        static final int TRANSACTION_cancelMissedCallsNotification = 33;
        static final int TRANSACTION_clearAccounts = 17;
        static final int TRANSACTION_createManageBlockedNumbersIntent = 44;
        static final int TRANSACTION_dumpCallAnalytics = 24;
        static final int TRANSACTION_enablePhoneAccount = 42;
        static final int TRANSACTION_endCall = 30;
        static final int TRANSACTION_getAdnUriForPhoneAccount = 36;
        static final int TRANSACTION_getAllPhoneAccountHandles = 12;
        static final int TRANSACTION_getAllPhoneAccounts = 11;
        static final int TRANSACTION_getAllPhoneAccountsCount = 10;
        static final int TRANSACTION_getCallCapablePhoneAccounts = 5;
        static final int TRANSACTION_getCallState = 29;
        static final int TRANSACTION_getCurrentTtyMode = 38;
        static final int TRANSACTION_getDefaultDialerPackage = 22;
        static final int TRANSACTION_getDefaultOutgoingPhoneAccount = 2;
        static final int TRANSACTION_getDefaultPhoneApp = 21;
        static final int TRANSACTION_getLine1Number = 20;
        static final int TRANSACTION_getPhoneAccount = 9;
        static final int TRANSACTION_getPhoneAccountsForPackage = 8;
        static final int TRANSACTION_getPhoneAccountsSupportingScheme = 7;
        static final int TRANSACTION_getSelfManagedPhoneAccounts = 6;
        static final int TRANSACTION_getSimCallManager = 13;
        static final int TRANSACTION_getSimCallManagerForUser = 14;
        static final int TRANSACTION_getSystemDialerPackage = 23;
        static final int TRANSACTION_getUserSelectedOutgoingPhoneAccount = 3;
        static final int TRANSACTION_getVoiceMailNumber = 19;
        static final int TRANSACTION_handlePinMmi = 34;
        static final int TRANSACTION_handlePinMmiForPhoneAccount = 35;
        static final int TRANSACTION_isInCall = 26;
        static final int TRANSACTION_isInManagedCall = 27;
        static final int TRANSACTION_isIncomingCallPermitted = 45;
        static final int TRANSACTION_isOutgoingCallPermitted = 46;
        static final int TRANSACTION_isRinging = 28;
        static final int TRANSACTION_isTtySupported = 37;
        static final int TRANSACTION_isVoiceMailNumber = 18;
        static final int TRANSACTION_placeCall = 41;
        static final int TRANSACTION_registerPhoneAccount = 15;
        static final int TRANSACTION_setDefaultDialer = 43;
        static final int TRANSACTION_setUserSelectedOutgoingPhoneAccount = 4;
        static final int TRANSACTION_showInCallScreen = 1;
        static final int TRANSACTION_silenceRinger = 25;
        static final int TRANSACTION_unregisterPhoneAccount = 16;
        static final int TRANSACTION_waitOnHandlers = 47;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telecom.ITelecomService");
        }
    }

    private static class Stub.Proxy
        implements ITelecomService
    {

        public void acceptRingingCall(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void acceptRingingCallWithVideoState(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void addNewIncomingCall(PhoneAccountHandle phoneaccounthandle, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
            parcel.writeInt(0);
              goto _L4
        }

        public void addNewUnknownCall(PhoneAccountHandle phoneaccounthandle, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
            parcel.writeInt(0);
              goto _L4
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelMissedCallsNotification(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearAccounts(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public Intent createManageBlockedNumbersIntent()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(44, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Intent intent = (Intent)Intent.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return intent;
_L2:
            intent = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public TelecomAnalytics dumpCallAnalytics()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            TelecomAnalytics telecomanalytics = (TelecomAnalytics)TelecomAnalytics.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return telecomanalytics;
_L2:
            telecomanalytics = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean enablePhoneAccount(PhoneAccountHandle phoneaccounthandle, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_92;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
        }

        public boolean endCall()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(30, parcel, parcel1, 0);
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

        public Uri getAdnUriForPhoneAccount(PhoneAccountHandle phoneaccounthandle, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_109;
            phoneaccounthandle = (Uri)Uri.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return phoneaccounthandle;
_L2:
            parcel.writeInt(0);
              goto _L3
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
            phoneaccounthandle = null;
              goto _L4
        }

        public List getAllPhoneAccountHandles()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(PhoneAccountHandle.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getAllPhoneAccounts()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(PhoneAccount.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getAllPhoneAccountsCount()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
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

        public List getCallCapablePhoneAccounts(boolean flag, String s)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(PhoneAccountHandle.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getCallState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(29, parcel, parcel1, 0);
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

        public int getCurrentTtyMode(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(38, parcel, parcel1, 0);
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

        public String getDefaultDialerPackage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
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

        public PhoneAccountHandle getDefaultOutgoingPhoneAccount(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel1);
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

        public ComponentName getDefaultPhoneApp()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ComponentName componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            componentname = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telecom.ITelecomService";
        }

        public String getLine1Number(PhoneAccountHandle phoneaccounthandle, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            phoneaccounthandle = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return phoneaccounthandle;
            parcel.writeInt(0);
              goto _L1
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
        }

        public PhoneAccount getPhoneAccount(PhoneAccountHandle phoneaccounthandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            phoneaccounthandle = (PhoneAccount)PhoneAccount.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return phoneaccounthandle;
_L2:
            parcel.writeInt(0);
              goto _L3
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
            phoneaccounthandle = null;
              goto _L4
        }

        public List getPhoneAccountsForPackage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(PhoneAccountHandle.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getPhoneAccountsSupportingScheme(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(PhoneAccountHandle.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getSelfManagedPhoneAccounts(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(PhoneAccountHandle.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public PhoneAccountHandle getSimCallManager()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            PhoneAccountHandle phoneaccounthandle = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return phoneaccounthandle;
_L2:
            phoneaccounthandle = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public PhoneAccountHandle getSimCallManagerForUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            PhoneAccountHandle phoneaccounthandle = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return phoneaccounthandle;
_L2:
            phoneaccounthandle = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getSystemDialerPackage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(23, parcel, parcel1, 0);
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

        public PhoneAccountHandle getUserSelectedOutgoingPhoneAccount()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            PhoneAccountHandle phoneaccounthandle = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return phoneaccounthandle;
_L2:
            phoneaccounthandle = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getVoiceMailNumber(PhoneAccountHandle phoneaccounthandle, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            phoneaccounthandle = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return phoneaccounthandle;
            parcel.writeInt(0);
              goto _L1
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
        }

        public boolean handlePinMmi(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(34, parcel, parcel1, 0);
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

        public boolean handlePinMmiForPhoneAccount(PhoneAccountHandle phoneaccounthandle, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(35, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
        }

        public boolean isInCall(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(26, parcel, parcel1, 0);
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

        public boolean isInManagedCall(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(27, parcel, parcel1, 0);
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

        public boolean isIncomingCallPermitted(PhoneAccountHandle phoneaccounthandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(45, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
        }

        public boolean isOutgoingCallPermitted(PhoneAccountHandle phoneaccounthandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(46, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
        }

        public boolean isRinging(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(28, parcel, parcel1, 0);
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

        public boolean isTtySupported(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(37, parcel, parcel1, 0);
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

        public boolean isVoiceMailNumber(PhoneAccountHandle phoneaccounthandle, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(18, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
        }

        public void placeCall(Uri uri, Bundle bundle, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(uri == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L3:
            if(bundle == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            parcel.writeString(s);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
            parcel.writeInt(0);
              goto _L4
        }

        public void registerPhoneAccount(PhoneAccount phoneaccount)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccount == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            phoneaccount.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            phoneaccount;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccount;
        }

        public boolean setDefaultDialer(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(43, parcel, parcel1, 0);
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

        public void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle phoneaccounthandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_56;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
        }

        public void showInCallScreen(boolean flag, String s)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(!flag)
                i = 0;
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

        public void silenceRinger(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            parcel.writeString(s);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void unregisterPhoneAccount(PhoneAccountHandle phoneaccounthandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            phoneaccounthandle;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccounthandle;
        }

        public void waitOnHandlers()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telecom.ITelecomService");
            mRemote.transact(47, parcel, parcel1, 0);
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


    public abstract void acceptRingingCall(String s)
        throws RemoteException;

    public abstract void acceptRingingCallWithVideoState(String s, int i)
        throws RemoteException;

    public abstract void addNewIncomingCall(PhoneAccountHandle phoneaccounthandle, Bundle bundle)
        throws RemoteException;

    public abstract void addNewUnknownCall(PhoneAccountHandle phoneaccounthandle, Bundle bundle)
        throws RemoteException;

    public abstract void cancelMissedCallsNotification(String s)
        throws RemoteException;

    public abstract void clearAccounts(String s)
        throws RemoteException;

    public abstract Intent createManageBlockedNumbersIntent()
        throws RemoteException;

    public abstract TelecomAnalytics dumpCallAnalytics()
        throws RemoteException;

    public abstract boolean enablePhoneAccount(PhoneAccountHandle phoneaccounthandle, boolean flag)
        throws RemoteException;

    public abstract boolean endCall()
        throws RemoteException;

    public abstract Uri getAdnUriForPhoneAccount(PhoneAccountHandle phoneaccounthandle, String s)
        throws RemoteException;

    public abstract List getAllPhoneAccountHandles()
        throws RemoteException;

    public abstract List getAllPhoneAccounts()
        throws RemoteException;

    public abstract int getAllPhoneAccountsCount()
        throws RemoteException;

    public abstract List getCallCapablePhoneAccounts(boolean flag, String s)
        throws RemoteException;

    public abstract int getCallState()
        throws RemoteException;

    public abstract int getCurrentTtyMode(String s)
        throws RemoteException;

    public abstract String getDefaultDialerPackage()
        throws RemoteException;

    public abstract PhoneAccountHandle getDefaultOutgoingPhoneAccount(String s, String s1)
        throws RemoteException;

    public abstract ComponentName getDefaultPhoneApp()
        throws RemoteException;

    public abstract String getLine1Number(PhoneAccountHandle phoneaccounthandle, String s)
        throws RemoteException;

    public abstract PhoneAccount getPhoneAccount(PhoneAccountHandle phoneaccounthandle)
        throws RemoteException;

    public abstract List getPhoneAccountsForPackage(String s)
        throws RemoteException;

    public abstract List getPhoneAccountsSupportingScheme(String s, String s1)
        throws RemoteException;

    public abstract List getSelfManagedPhoneAccounts(String s)
        throws RemoteException;

    public abstract PhoneAccountHandle getSimCallManager()
        throws RemoteException;

    public abstract PhoneAccountHandle getSimCallManagerForUser(int i)
        throws RemoteException;

    public abstract String getSystemDialerPackage()
        throws RemoteException;

    public abstract PhoneAccountHandle getUserSelectedOutgoingPhoneAccount()
        throws RemoteException;

    public abstract String getVoiceMailNumber(PhoneAccountHandle phoneaccounthandle, String s)
        throws RemoteException;

    public abstract boolean handlePinMmi(String s, String s1)
        throws RemoteException;

    public abstract boolean handlePinMmiForPhoneAccount(PhoneAccountHandle phoneaccounthandle, String s, String s1)
        throws RemoteException;

    public abstract boolean isInCall(String s)
        throws RemoteException;

    public abstract boolean isInManagedCall(String s)
        throws RemoteException;

    public abstract boolean isIncomingCallPermitted(PhoneAccountHandle phoneaccounthandle)
        throws RemoteException;

    public abstract boolean isOutgoingCallPermitted(PhoneAccountHandle phoneaccounthandle)
        throws RemoteException;

    public abstract boolean isRinging(String s)
        throws RemoteException;

    public abstract boolean isTtySupported(String s)
        throws RemoteException;

    public abstract boolean isVoiceMailNumber(PhoneAccountHandle phoneaccounthandle, String s, String s1)
        throws RemoteException;

    public abstract void placeCall(Uri uri, Bundle bundle, String s)
        throws RemoteException;

    public abstract void registerPhoneAccount(PhoneAccount phoneaccount)
        throws RemoteException;

    public abstract boolean setDefaultDialer(String s)
        throws RemoteException;

    public abstract void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle phoneaccounthandle)
        throws RemoteException;

    public abstract void showInCallScreen(boolean flag, String s)
        throws RemoteException;

    public abstract void silenceRinger(String s)
        throws RemoteException;

    public abstract void unregisterPhoneAccount(PhoneAccountHandle phoneaccounthandle)
        throws RemoteException;

    public abstract void waitOnHandlers()
        throws RemoteException;
}
