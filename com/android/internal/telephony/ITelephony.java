// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.telephony;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.NetworkStats;
import android.net.Uri;
import android.os.*;
import android.service.carrier.CarrierIdentifier;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telephony.*;
import com.android.ims.internal.IImsServiceController;
import com.android.ims.internal.IImsServiceFeatureListener;
import java.util.List;

// Referenced classes of package com.android.internal.telephony:
//            CellNetworkScanResult, OperatorInfo

public interface ITelephony
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITelephony
    {

        public static ITelephony asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.telephony.ITelephony");
            if(iinterface != null && (iinterface instanceof ITelephony))
                return (ITelephony)iinterface;
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
                parcel1.writeString("com.android.internal.telephony.ITelephony");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                dial(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                call(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag = endCall();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag1 = endCallForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                answerRingingCall();
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                answerRingingCallForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                silenceRinger();
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag2 = isOffhook(parcel.readString());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag3 = isOffhookForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag4 = isRingingForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag5 = isRinging(parcel.readString());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag6 = isIdle(parcel.readString());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag7 = isIdleForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag8 = isRadioOn(parcel.readString());
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag9 = isRadioOnForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag10 = supplyPin(parcel.readString());
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag11 = supplyPinForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag12 = supplyPuk(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag13 = supplyPukForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = supplyPinReportResult(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = supplyPinReportResultForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = supplyPukReportResult(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = supplyPukReportResultForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag14 = handlePinMmi(parcel.readString());
                parcel1.writeNoException();
                if(flag14)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = parcel.readInt();
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                handleUssdRequest(i, s, parcel);
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag15 = handlePinMmiForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag15)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                toggleRadioOnOff();
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                toggleRadioOnOffForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag16;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                flag16 = setRadio(flag16);
                parcel1.writeNoException();
                if(flag16)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = parcel.readInt();
                boolean flag17;
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                flag17 = setRadioForSubscriber(i, flag17);
                parcel1.writeNoException();
                if(flag17)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag18;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                flag18 = setRadioPower(flag18);
                parcel1.writeNoException();
                if(flag18)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                updateServiceLocation();
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                updateServiceLocationForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                enableLocationUpdates();
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                enableLocationUpdatesForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                disableLocationUpdates();
                parcel1.writeNoException();
                return true;

            case 37: // '%'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                disableLocationUpdatesForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag19 = enableDataConnectivity();
                parcel1.writeNoException();
                if(flag19)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 39: // '\''
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag20 = disableDataConnectivity();
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 40: // '('
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag21 = isDataConnectivityPossible(parcel.readInt());
                parcel1.writeNoException();
                if(flag21)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 41: // ')'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getCellLocation(parcel.readString());
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

            case 42: // '*'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getNetworkCountryIsoForPhone(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 43: // '+'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getNeighboringCellInfo(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 44: // ','
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getCallState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 45: // '-'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getCallStateForSlot(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 46: // '.'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getDataActivity();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 47: // '/'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getDataState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 48: // '0'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getActivePhoneType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 49: // '1'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getActivePhoneTypeForSlot(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 50: // '2'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getCdmaEriIconIndex(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 51: // '3'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getCdmaEriIconIndexForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 52: // '4'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getCdmaEriIconMode(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 53: // '5'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getCdmaEriIconModeForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 54: // '6'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getCdmaEriText(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 55: // '7'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getCdmaEriTextForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 56: // '8'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag22 = needsOtaServiceProvisioning();
                parcel1.writeNoException();
                if(flag22)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 57: // '9'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag23 = setVoiceMailNumber(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag23)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 58: // ':'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                setVoiceActivationState(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 59: // ';'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                setDataActivationState(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 60: // '<'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getVoiceActivationState(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 61: // '='
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getDataActivationState(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 62: // '>'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getVoiceMessageCount();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 63: // '?'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getVoiceMessageCountForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 64: // '@'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag24 = isConcurrentVoiceAndDataAllowed(parcel.readInt());
                parcel1.writeNoException();
                if(flag24)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 65: // 'A'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getVisualVoicemailSettings(parcel.readString(), parcel.readInt());
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

            case 66: // 'B'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getVisualVoicemailPackageName(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 67: // 'C'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                String s1 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (VisualVoicemailSmsFilterSettings)VisualVoicemailSmsFilterSettings.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                enableVisualVoicemailSmsFilter(s1, i, parcel);
                parcel1.writeNoException();
                return true;

            case 68: // 'D'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                disableVisualVoicemailSmsFilter(parcel.readString(), parcel.readInt());
                return true;

            case 69: // 'E'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getVisualVoicemailSmsFilterSettings(parcel.readString(), parcel.readInt());
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

            case 70: // 'F'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getActiveVisualVoicemailSmsFilterSettings(parcel.readInt());
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

            case 71: // 'G'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                String s3 = parcel.readString();
                i = parcel.readInt();
                String s4 = parcel.readString();
                j = parcel.readInt();
                String s2 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                sendVisualVoicemailSmsForSubscriber(s3, i, s4, j, s2, parcel);
                parcel1.writeNoException();
                return true;

            case 72: // 'H'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                sendDialerSpecialCode(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 73: // 'I'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getNetworkType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 74: // 'J'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getNetworkTypeForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 75: // 'K'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getDataNetworkType(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 76: // 'L'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getDataNetworkTypeForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 77: // 'M'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getVoiceNetworkTypeForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 78: // 'N'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag25 = hasIccCard();
                parcel1.writeNoException();
                if(flag25)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 79: // 'O'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag26 = hasIccCardUsingSlotIndex(parcel.readInt());
                parcel1.writeNoException();
                if(flag26)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 80: // 'P'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getLteOnCdmaMode(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 81: // 'Q'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getLteOnCdmaModeForSubscriber(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 82: // 'R'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getAllCellInfo(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 83: // 'S'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                setCellInfoListRate(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 84: // 'T'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getDefaultSim();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 85: // 'U'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = iccOpenLogicalChannel(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
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

            case 86: // 'V'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag27 = iccCloseLogicalChannel(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag27)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 87: // 'W'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = iccTransmitApduLogicalChannel(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 88: // 'X'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = iccTransmitApduBasicChannel(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 89: // 'Y'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = iccExchangeSimIO(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 90: // 'Z'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = sendEnvelopeWithStatus(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 91: // '['
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = nvReadItem(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 92: // '\\'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag28 = nvWriteItem(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag28)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 93: // ']'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag29 = nvWriteCdmaPrl(parcel.createByteArray());
                parcel1.writeNoException();
                if(flag29)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 94: // '^'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag30 = nvResetConfig(parcel.readInt());
                parcel1.writeNoException();
                if(flag30)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 95: // '_'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getCalculatedPreferredNetworkType(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 96: // '`'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getPreferredNetworkType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 97: // 'a'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getTetherApnRequired();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 98: // 'b'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getImsServiceControllerAndListen(parcel.readInt(), parcel.readInt(), com.android.ims.internal.IImsServiceFeatureListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 99: // 'c'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                setNetworkSelectionModeAutomatic(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 100: // 'd'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getCellNetworkScanResults(parcel.readInt());
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

            case 101: // 'e'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = parcel.readInt();
                NetworkScanRequest networkscanrequest;
                Messenger messenger;
                if(parcel.readInt() != 0)
                    networkscanrequest = (NetworkScanRequest)NetworkScanRequest.CREATOR.createFromParcel(parcel);
                else
                    networkscanrequest = null;
                if(parcel.readInt() != 0)
                    messenger = (Messenger)Messenger.CREATOR.createFromParcel(parcel);
                else
                    messenger = null;
                i = requestNetworkScan(i, networkscanrequest, messenger, parcel.readStrongBinder());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 102: // 'f'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                stopNetworkScan(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 103: // 'g'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = parcel.readInt();
                boolean flag31;
                OperatorInfo operatorinfo;
                if(parcel.readInt() != 0)
                    operatorinfo = (OperatorInfo)OperatorInfo.CREATOR.createFromParcel(parcel);
                else
                    operatorinfo = null;
                if(parcel.readInt() != 0)
                    flag31 = true;
                else
                    flag31 = false;
                flag31 = setNetworkSelectionModeManual(i, operatorinfo, flag31);
                parcel1.writeNoException();
                if(flag31)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 104: // 'h'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag32 = setPreferredNetworkType(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag32)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 105: // 'i'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = parcel.readInt();
                boolean flag33;
                if(parcel.readInt() != 0)
                    flag33 = true;
                else
                    flag33 = false;
                setDataEnabled(i, flag33);
                parcel1.writeNoException();
                return true;

            case 106: // 'j'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag34 = getDataEnabled(parcel.readInt());
                parcel1.writeNoException();
                if(flag34)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 107: // 'k'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getPcscfAddress(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 108: // 'l'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag35;
                if(parcel.readInt() != 0)
                    flag35 = true;
                else
                    flag35 = false;
                setImsRegistrationState(flag35);
                parcel1.writeNoException();
                return true;

            case 109: // 'm'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getCdmaMdn(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 110: // 'n'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getCdmaMin(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 111: // 'o'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getCarrierPrivilegeStatus(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 112: // 'p'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = checkCarrierPrivilegesForPackage(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 113: // 'q'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = checkCarrierPrivilegesForPackageAnyPhone(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 114: // 'r'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                Intent intent;
                if(parcel.readInt() != 0)
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent = null;
                parcel = getCarrierPackageNamesForIntentAndPhone(intent, parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 115: // 's'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag36 = setLine1NumberForDisplayForSubscriber(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag36)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 116: // 't'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getLine1NumberForDisplay(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 117: // 'u'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getLine1AlphaTagForDisplay(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 118: // 'v'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getMergedSubscriberIds(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 119: // 'w'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag37 = setOperatorBrandOverride(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag37)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 120: // 'x'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag38 = setRoamingOverride(parcel.readInt(), parcel.createStringArrayList(), parcel.createStringArrayList(), parcel.createStringArrayList(), parcel.createStringArrayList());
                parcel1.writeNoException();
                if(flag38)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 121: // 'y'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                byte abyte0[] = parcel.createByteArray();
                i = parcel.readInt();
                if(i < 0)
                    parcel = null;
                else
                    parcel = new byte[i];
                i = invokeOemRilRequestRaw(abyte0, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                parcel1.writeByteArray(parcel);
                return true;

            case 122: // 'z'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag39 = needMobileRadioShutdown();
                parcel1.writeNoException();
                if(flag39)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 123: // '{'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                shutdownMobileRadios();
                parcel1.writeNoException();
                return true;

            case 124: // '|'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                setRadioCapability((RadioAccessFamily[])parcel.createTypedArray(RadioAccessFamily.CREATOR));
                parcel1.writeNoException();
                return true;

            case 125: // '}'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = getRadioAccessFamily(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 126: // '~'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag40;
                if(parcel.readInt() != 0)
                    flag40 = true;
                else
                    flag40 = false;
                enableVideoCalling(flag40);
                parcel1.writeNoException();
                return true;

            case 127: // '\177'
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag41 = isVideoCallingEnabled(parcel.readString());
                parcel1.writeNoException();
                if(flag41)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 128: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag42 = canChangeDtmfToneLength();
                parcel1.writeNoException();
                if(flag42)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 129: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag43 = isWorldPhone();
                parcel1.writeNoException();
                if(flag43)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 130: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag44 = isTtyModeSupported();
                parcel1.writeNoException();
                if(flag44)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 131: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag45 = isHearingAidCompatibilitySupported();
                parcel1.writeNoException();
                if(flag45)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 132: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag46 = isImsRegistered();
                parcel1.writeNoException();
                if(flag46)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 133: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag47 = isImsRegisteredForSubscriber(parcel.readInt());
                parcel1.writeNoException();
                if(flag47)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 134: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag48 = isWifiCallingAvailable();
                parcel1.writeNoException();
                if(flag48)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 135: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag49 = isVolteAvailable();
                parcel1.writeNoException();
                if(flag49)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 136: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag50 = isVideoTelephonyAvailable();
                parcel1.writeNoException();
                if(flag50)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 137: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getDeviceId(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 138: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getImeiForSlot(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 139: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getMeidForSlot(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 140: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getDeviceSoftwareVersionForSlot(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 141: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                if(parcel.readInt() != 0)
                    parcel = (PhoneAccount)PhoneAccount.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getSubIdForPhoneAccount(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 142: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                factoryReset(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 143: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getLocaleFromDefaultSim();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 144: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                if(parcel.readInt() != 0)
                    parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestModemActivityInfo(parcel);
                return true;

            case 145: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getServiceStateForSubscriber(parcel.readInt(), parcel.readString());
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

            case 146: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                if(parcel.readInt() != 0)
                    parcel = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getVoicemailRingtoneUri(parcel);
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

            case 147: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                String s5 = parcel.readString();
                PhoneAccountHandle phoneaccounthandle;
                if(parcel.readInt() != 0)
                    phoneaccounthandle = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    phoneaccounthandle = null;
                if(parcel.readInt() != 0)
                    parcel = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setVoicemailRingtoneUri(s5, phoneaccounthandle, parcel);
                parcel1.writeNoException();
                return true;

            case 148: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag51;
                if(parcel.readInt() != 0)
                    parcel = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag51 = isVoicemailVibrationEnabled(parcel);
                parcel1.writeNoException();
                if(flag51)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 149: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                String s6 = parcel.readString();
                boolean flag52;
                PhoneAccountHandle phoneaccounthandle1;
                if(parcel.readInt() != 0)
                    phoneaccounthandle1 = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
                else
                    phoneaccounthandle1 = null;
                if(parcel.readInt() != 0)
                    flag52 = true;
                else
                    flag52 = false;
                setVoicemailVibrationEnabled(s6, phoneaccounthandle1, flag52);
                parcel1.writeNoException();
                return true;

            case 150: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getPackagesWithCarrierPrivileges();
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 151: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getAidForAppType(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 152: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getEsn(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 153: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getCdmaPrlVersion(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 154: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getTelephonyHistograms();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 155: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = setAllowedCarriers(parcel.readInt(), parcel.createTypedArrayList(CarrierIdentifier.CREATOR));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 156: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getAllowedCarriers(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 157: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = parcel.readInt();
                boolean flag53;
                if(parcel.readInt() != 0)
                    flag53 = true;
                else
                    flag53 = false;
                carrierActionSetMeteredApnsEnabled(i, flag53);
                parcel1.writeNoException();
                return true;

            case 158: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = parcel.readInt();
                boolean flag54;
                if(parcel.readInt() != 0)
                    flag54 = true;
                else
                    flag54 = false;
                carrierActionSetRadioEnabled(i, flag54);
                parcel1.writeNoException();
                return true;

            case 159: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = parcel.readInt();
                boolean flag55;
                if(parcel.readInt() != 0)
                    flag55 = true;
                else
                    flag55 = false;
                carrierActionReportDefaultNetworkStatus(i, flag55);
                parcel1.writeNoException();
                return true;

            case 160: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                i = parcel.readInt();
                boolean flag56;
                if(parcel.readInt() != 0)
                    flag56 = true;
                else
                    flag56 = false;
                parcel = getVtDataUsage(i, flag56);
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

            case 161: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag57;
                if(parcel.readInt() != 0)
                    flag57 = true;
                else
                    flag57 = false;
                setPolicyDataEnabled(flag57, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 162: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getClientRequestStats(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 163: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                setSimPowerStateForSlot(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 164: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getForbiddenPlmns(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 165: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                boolean flag58 = getEmergencyCallbackMode(parcel.readInt());
                parcel1.writeNoException();
                if(flag58)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 166: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getAtr();
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 167: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getAtrUsingSubId(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 168: 
                parcel.enforceInterface("com.android.internal.telephony.ITelephony");
                parcel = getSignalStrength(parcel.readInt());
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

        private static final String DESCRIPTOR = "com.android.internal.telephony.ITelephony";
        static final int TRANSACTION_answerRingingCall = 5;
        static final int TRANSACTION_answerRingingCallForSubscriber = 6;
        static final int TRANSACTION_call = 2;
        static final int TRANSACTION_canChangeDtmfToneLength = 128;
        static final int TRANSACTION_carrierActionReportDefaultNetworkStatus = 159;
        static final int TRANSACTION_carrierActionSetMeteredApnsEnabled = 157;
        static final int TRANSACTION_carrierActionSetRadioEnabled = 158;
        static final int TRANSACTION_checkCarrierPrivilegesForPackage = 112;
        static final int TRANSACTION_checkCarrierPrivilegesForPackageAnyPhone = 113;
        static final int TRANSACTION_dial = 1;
        static final int TRANSACTION_disableDataConnectivity = 39;
        static final int TRANSACTION_disableLocationUpdates = 36;
        static final int TRANSACTION_disableLocationUpdatesForSubscriber = 37;
        static final int TRANSACTION_disableVisualVoicemailSmsFilter = 68;
        static final int TRANSACTION_enableDataConnectivity = 38;
        static final int TRANSACTION_enableLocationUpdates = 34;
        static final int TRANSACTION_enableLocationUpdatesForSubscriber = 35;
        static final int TRANSACTION_enableVideoCalling = 126;
        static final int TRANSACTION_enableVisualVoicemailSmsFilter = 67;
        static final int TRANSACTION_endCall = 3;
        static final int TRANSACTION_endCallForSubscriber = 4;
        static final int TRANSACTION_factoryReset = 142;
        static final int TRANSACTION_getActivePhoneType = 48;
        static final int TRANSACTION_getActivePhoneTypeForSlot = 49;
        static final int TRANSACTION_getActiveVisualVoicemailSmsFilterSettings = 70;
        static final int TRANSACTION_getAidForAppType = 151;
        static final int TRANSACTION_getAllCellInfo = 82;
        static final int TRANSACTION_getAllowedCarriers = 156;
        static final int TRANSACTION_getAtr = 166;
        static final int TRANSACTION_getAtrUsingSubId = 167;
        static final int TRANSACTION_getCalculatedPreferredNetworkType = 95;
        static final int TRANSACTION_getCallState = 44;
        static final int TRANSACTION_getCallStateForSlot = 45;
        static final int TRANSACTION_getCarrierPackageNamesForIntentAndPhone = 114;
        static final int TRANSACTION_getCarrierPrivilegeStatus = 111;
        static final int TRANSACTION_getCdmaEriIconIndex = 50;
        static final int TRANSACTION_getCdmaEriIconIndexForSubscriber = 51;
        static final int TRANSACTION_getCdmaEriIconMode = 52;
        static final int TRANSACTION_getCdmaEriIconModeForSubscriber = 53;
        static final int TRANSACTION_getCdmaEriText = 54;
        static final int TRANSACTION_getCdmaEriTextForSubscriber = 55;
        static final int TRANSACTION_getCdmaMdn = 109;
        static final int TRANSACTION_getCdmaMin = 110;
        static final int TRANSACTION_getCdmaPrlVersion = 153;
        static final int TRANSACTION_getCellLocation = 41;
        static final int TRANSACTION_getCellNetworkScanResults = 100;
        static final int TRANSACTION_getClientRequestStats = 162;
        static final int TRANSACTION_getDataActivationState = 61;
        static final int TRANSACTION_getDataActivity = 46;
        static final int TRANSACTION_getDataEnabled = 106;
        static final int TRANSACTION_getDataNetworkType = 75;
        static final int TRANSACTION_getDataNetworkTypeForSubscriber = 76;
        static final int TRANSACTION_getDataState = 47;
        static final int TRANSACTION_getDefaultSim = 84;
        static final int TRANSACTION_getDeviceId = 137;
        static final int TRANSACTION_getDeviceSoftwareVersionForSlot = 140;
        static final int TRANSACTION_getEmergencyCallbackMode = 165;
        static final int TRANSACTION_getEsn = 152;
        static final int TRANSACTION_getForbiddenPlmns = 164;
        static final int TRANSACTION_getImeiForSlot = 138;
        static final int TRANSACTION_getImsServiceControllerAndListen = 98;
        static final int TRANSACTION_getLine1AlphaTagForDisplay = 117;
        static final int TRANSACTION_getLine1NumberForDisplay = 116;
        static final int TRANSACTION_getLocaleFromDefaultSim = 143;
        static final int TRANSACTION_getLteOnCdmaMode = 80;
        static final int TRANSACTION_getLteOnCdmaModeForSubscriber = 81;
        static final int TRANSACTION_getMeidForSlot = 139;
        static final int TRANSACTION_getMergedSubscriberIds = 118;
        static final int TRANSACTION_getNeighboringCellInfo = 43;
        static final int TRANSACTION_getNetworkCountryIsoForPhone = 42;
        static final int TRANSACTION_getNetworkType = 73;
        static final int TRANSACTION_getNetworkTypeForSubscriber = 74;
        static final int TRANSACTION_getPackagesWithCarrierPrivileges = 150;
        static final int TRANSACTION_getPcscfAddress = 107;
        static final int TRANSACTION_getPreferredNetworkType = 96;
        static final int TRANSACTION_getRadioAccessFamily = 125;
        static final int TRANSACTION_getServiceStateForSubscriber = 145;
        static final int TRANSACTION_getSignalStrength = 168;
        static final int TRANSACTION_getSubIdForPhoneAccount = 141;
        static final int TRANSACTION_getTelephonyHistograms = 154;
        static final int TRANSACTION_getTetherApnRequired = 97;
        static final int TRANSACTION_getVisualVoicemailPackageName = 66;
        static final int TRANSACTION_getVisualVoicemailSettings = 65;
        static final int TRANSACTION_getVisualVoicemailSmsFilterSettings = 69;
        static final int TRANSACTION_getVoiceActivationState = 60;
        static final int TRANSACTION_getVoiceMessageCount = 62;
        static final int TRANSACTION_getVoiceMessageCountForSubscriber = 63;
        static final int TRANSACTION_getVoiceNetworkTypeForSubscriber = 77;
        static final int TRANSACTION_getVoicemailRingtoneUri = 146;
        static final int TRANSACTION_getVtDataUsage = 160;
        static final int TRANSACTION_handlePinMmi = 24;
        static final int TRANSACTION_handlePinMmiForSubscriber = 26;
        static final int TRANSACTION_handleUssdRequest = 25;
        static final int TRANSACTION_hasIccCard = 78;
        static final int TRANSACTION_hasIccCardUsingSlotIndex = 79;
        static final int TRANSACTION_iccCloseLogicalChannel = 86;
        static final int TRANSACTION_iccExchangeSimIO = 89;
        static final int TRANSACTION_iccOpenLogicalChannel = 85;
        static final int TRANSACTION_iccTransmitApduBasicChannel = 88;
        static final int TRANSACTION_iccTransmitApduLogicalChannel = 87;
        static final int TRANSACTION_invokeOemRilRequestRaw = 121;
        static final int TRANSACTION_isConcurrentVoiceAndDataAllowed = 64;
        static final int TRANSACTION_isDataConnectivityPossible = 40;
        static final int TRANSACTION_isHearingAidCompatibilitySupported = 131;
        static final int TRANSACTION_isIdle = 12;
        static final int TRANSACTION_isIdleForSubscriber = 13;
        static final int TRANSACTION_isImsRegistered = 132;
        static final int TRANSACTION_isImsRegisteredForSubscriber = 133;
        static final int TRANSACTION_isOffhook = 8;
        static final int TRANSACTION_isOffhookForSubscriber = 9;
        static final int TRANSACTION_isRadioOn = 14;
        static final int TRANSACTION_isRadioOnForSubscriber = 15;
        static final int TRANSACTION_isRinging = 11;
        static final int TRANSACTION_isRingingForSubscriber = 10;
        static final int TRANSACTION_isTtyModeSupported = 130;
        static final int TRANSACTION_isVideoCallingEnabled = 127;
        static final int TRANSACTION_isVideoTelephonyAvailable = 136;
        static final int TRANSACTION_isVoicemailVibrationEnabled = 148;
        static final int TRANSACTION_isVolteAvailable = 135;
        static final int TRANSACTION_isWifiCallingAvailable = 134;
        static final int TRANSACTION_isWorldPhone = 129;
        static final int TRANSACTION_needMobileRadioShutdown = 122;
        static final int TRANSACTION_needsOtaServiceProvisioning = 56;
        static final int TRANSACTION_nvReadItem = 91;
        static final int TRANSACTION_nvResetConfig = 94;
        static final int TRANSACTION_nvWriteCdmaPrl = 93;
        static final int TRANSACTION_nvWriteItem = 92;
        static final int TRANSACTION_requestModemActivityInfo = 144;
        static final int TRANSACTION_requestNetworkScan = 101;
        static final int TRANSACTION_sendDialerSpecialCode = 72;
        static final int TRANSACTION_sendEnvelopeWithStatus = 90;
        static final int TRANSACTION_sendVisualVoicemailSmsForSubscriber = 71;
        static final int TRANSACTION_setAllowedCarriers = 155;
        static final int TRANSACTION_setCellInfoListRate = 83;
        static final int TRANSACTION_setDataActivationState = 59;
        static final int TRANSACTION_setDataEnabled = 105;
        static final int TRANSACTION_setImsRegistrationState = 108;
        static final int TRANSACTION_setLine1NumberForDisplayForSubscriber = 115;
        static final int TRANSACTION_setNetworkSelectionModeAutomatic = 99;
        static final int TRANSACTION_setNetworkSelectionModeManual = 103;
        static final int TRANSACTION_setOperatorBrandOverride = 119;
        static final int TRANSACTION_setPolicyDataEnabled = 161;
        static final int TRANSACTION_setPreferredNetworkType = 104;
        static final int TRANSACTION_setRadio = 29;
        static final int TRANSACTION_setRadioCapability = 124;
        static final int TRANSACTION_setRadioForSubscriber = 30;
        static final int TRANSACTION_setRadioPower = 31;
        static final int TRANSACTION_setRoamingOverride = 120;
        static final int TRANSACTION_setSimPowerStateForSlot = 163;
        static final int TRANSACTION_setVoiceActivationState = 58;
        static final int TRANSACTION_setVoiceMailNumber = 57;
        static final int TRANSACTION_setVoicemailRingtoneUri = 147;
        static final int TRANSACTION_setVoicemailVibrationEnabled = 149;
        static final int TRANSACTION_shutdownMobileRadios = 123;
        static final int TRANSACTION_silenceRinger = 7;
        static final int TRANSACTION_stopNetworkScan = 102;
        static final int TRANSACTION_supplyPin = 16;
        static final int TRANSACTION_supplyPinForSubscriber = 17;
        static final int TRANSACTION_supplyPinReportResult = 20;
        static final int TRANSACTION_supplyPinReportResultForSubscriber = 21;
        static final int TRANSACTION_supplyPuk = 18;
        static final int TRANSACTION_supplyPukForSubscriber = 19;
        static final int TRANSACTION_supplyPukReportResult = 22;
        static final int TRANSACTION_supplyPukReportResultForSubscriber = 23;
        static final int TRANSACTION_toggleRadioOnOff = 27;
        static final int TRANSACTION_toggleRadioOnOffForSubscriber = 28;
        static final int TRANSACTION_updateServiceLocation = 32;
        static final int TRANSACTION_updateServiceLocationForSubscriber = 33;

        public Stub()
        {
            attachInterface(this, "com.android.internal.telephony.ITelephony");
        }
    }

    private static class Stub.Proxy
        implements ITelephony
    {

        public void answerRingingCall()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public void answerRingingCallForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void call(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean canChangeDtmfToneLength()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(128, parcel, parcel1, 0);
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

        public void carrierActionReportDefaultNetworkStatus(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(159, parcel, parcel1, 0);
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

        public void carrierActionSetMeteredApnsEnabled(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(157, parcel, parcel1, 0);
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

        public void carrierActionSetRadioEnabled(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(158, parcel, parcel1, 0);
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

        public int checkCarrierPrivilegesForPackage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(112, parcel, parcel1, 0);
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

        public int checkCarrierPrivilegesForPackageAnyPhone(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(113, parcel, parcel1, 0);
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

        public void dial(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
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

        public boolean disableDataConnectivity()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(39, parcel, parcel1, 0);
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

        public void disableLocationUpdates()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(36, parcel, parcel1, 0);
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

        public void disableLocationUpdatesForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(37, parcel, parcel1, 0);
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

        public void disableVisualVoicemailSmsFilter(String s, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(68, parcel, null, 1);
            parcel.recycle();
            return;
            s;
            parcel.recycle();
            throw s;
        }

        public boolean enableDataConnectivity()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(38, parcel, parcel1, 0);
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

        public void enableLocationUpdates()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(34, parcel, parcel1, 0);
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

        public void enableLocationUpdatesForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(35, parcel, parcel1, 0);
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

        public void enableVideoCalling(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(126, parcel, parcel1, 0);
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

        public void enableVisualVoicemailSmsFilter(String s, int i, VisualVoicemailSmsFilterSettings visualvoicemailsmsfiltersettings)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(visualvoicemailsmsfiltersettings == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            visualvoicemailsmsfiltersettings.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(67, parcel, parcel1, 0);
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

        public boolean endCall()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
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

        public boolean endCallForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
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

        public void factoryReset(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(142, parcel, parcel1, 0);
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

        public int getActivePhoneType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(48, parcel, parcel1, 0);
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

        public int getActivePhoneTypeForSlot(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(49, parcel, parcel1, 0);
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

        public VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(70, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            VisualVoicemailSmsFilterSettings visualvoicemailsmsfiltersettings = (VisualVoicemailSmsFilterSettings)VisualVoicemailSmsFilterSettings.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return visualvoicemailsmsfiltersettings;
_L2:
            visualvoicemailsmsfiltersettings = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getAidForAppType(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(151, parcel, parcel1, 0);
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

        public List getAllCellInfo(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(82, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(CellInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getAllowedCarriers(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(156, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(CarrierIdentifier.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public byte[] getAtr()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(166, parcel, parcel1, 0);
            parcel1.readException();
            abyte0 = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public byte[] getAtrUsingSubId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(167, parcel, parcel1, 0);
            parcel1.readException();
            abyte0 = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return abyte0;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getCalculatedPreferredNetworkType(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(95, parcel, parcel1, 0);
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

        public int getCallState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(44, parcel, parcel1, 0);
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

        public int getCallStateForSlot(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(45, parcel, parcel1, 0);
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

        public List getCarrierPackageNamesForIntentAndPhone(Intent intent, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            if(intent == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(114, parcel, parcel1, 0);
            parcel1.readException();
            intent = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return intent;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
        }

        public int getCarrierPrivilegeStatus(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(111, parcel, parcel1, 0);
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

        public int getCdmaEriIconIndex(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(50, parcel, parcel1, 0);
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

        public int getCdmaEriIconIndexForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(51, parcel, parcel1, 0);
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

        public int getCdmaEriIconMode(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(52, parcel, parcel1, 0);
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

        public int getCdmaEriIconModeForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(53, parcel, parcel1, 0);
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

        public String getCdmaEriText(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(54, parcel, parcel1, 0);
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

        public String getCdmaEriTextForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(55, parcel, parcel1, 0);
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

        public String getCdmaMdn(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(109, parcel, parcel1, 0);
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

        public String getCdmaMin(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(110, parcel, parcel1, 0);
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

        public String getCdmaPrlVersion(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(153, parcel, parcel1, 0);
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

        public Bundle getCellLocation(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(41, parcel, parcel1, 0);
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

        public CellNetworkScanResult getCellNetworkScanResults(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(100, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            CellNetworkScanResult cellnetworkscanresult = (CellNetworkScanResult)CellNetworkScanResult.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return cellnetworkscanresult;
_L2:
            cellnetworkscanresult = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getClientRequestStats(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(162, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(ClientRequestStats.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getDataActivationState(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(61, parcel, parcel1, 0);
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

        public int getDataActivity()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(46, parcel, parcel1, 0);
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

        public boolean getDataEnabled(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(106, parcel, parcel1, 0);
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

        public int getDataNetworkType(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(75, parcel, parcel1, 0);
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

        public int getDataNetworkTypeForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(76, parcel, parcel1, 0);
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

        public int getDataState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(47, parcel, parcel1, 0);
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

        public int getDefaultSim()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(84, parcel, parcel1, 0);
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

        public String getDeviceId(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(137, parcel, parcel1, 0);
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

        public String getDeviceSoftwareVersionForSlot(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(140, parcel, parcel1, 0);
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

        public boolean getEmergencyCallbackMode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(165, parcel, parcel1, 0);
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

        public String getEsn(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(152, parcel, parcel1, 0);
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

        public String[] getForbiddenPlmns(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(164, parcel, parcel1, 0);
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

        public String getImeiForSlot(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(138, parcel, parcel1, 0);
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

        public IImsServiceController getImsServiceControllerAndListen(int i, int j, IImsServiceFeatureListener iimsservicefeaturelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            if(iimsservicefeaturelistener == null)
                break MISSING_BLOCK_LABEL_44;
            ibinder = iimsservicefeaturelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(98, parcel, parcel1, 0);
            parcel1.readException();
            iimsservicefeaturelistener = com.android.ims.internal.IImsServiceController.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iimsservicefeaturelistener;
            iimsservicefeaturelistener;
            parcel1.recycle();
            parcel.recycle();
            throw iimsservicefeaturelistener;
        }

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.telephony.ITelephony";
        }

        public String getLine1AlphaTagForDisplay(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(117, parcel, parcel1, 0);
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

        public String getLine1NumberForDisplay(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(116, parcel, parcel1, 0);
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

        public String getLocaleFromDefaultSim()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(143, parcel, parcel1, 0);
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

        public int getLteOnCdmaMode(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(80, parcel, parcel1, 0);
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

        public int getLteOnCdmaModeForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(81, parcel, parcel1, 0);
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

        public String getMeidForSlot(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(139, parcel, parcel1, 0);
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

        public String[] getMergedSubscriberIds(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(118, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public List getNeighboringCellInfo(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(NeighboringCellInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getNetworkCountryIsoForPhone(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(42, parcel, parcel1, 0);
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

        public int getNetworkType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(73, parcel, parcel1, 0);
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

        public int getNetworkTypeForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(74, parcel, parcel1, 0);
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

        public List getPackagesWithCarrierPrivileges()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(150, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String[] getPcscfAddress(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(107, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getPreferredNetworkType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(96, parcel, parcel1, 0);
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

        public int getRadioAccessFamily(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(125, parcel, parcel1, 0);
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

        public ServiceState getServiceStateForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(145, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ServiceState)ServiceState.CREATOR.createFromParcel(parcel1);
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

        public SignalStrength getSignalStrength(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(168, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            SignalStrength signalstrength = (SignalStrength)SignalStrength.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return signalstrength;
_L2:
            signalstrength = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getSubIdForPhoneAccount(PhoneAccount phoneaccount)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            if(phoneaccount == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            phoneaccount.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(141, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            phoneaccount;
            parcel1.recycle();
            parcel.recycle();
            throw phoneaccount;
        }

        public List getTelephonyHistograms()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(154, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(TelephonyHistogram.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getTetherApnRequired()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(97, parcel, parcel1, 0);
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

        public String getVisualVoicemailPackageName(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(66, parcel, parcel1, 0);
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

        public Bundle getVisualVoicemailSettings(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(65, parcel, parcel1, 0);
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

        public VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(69, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (VisualVoicemailSmsFilterSettings)VisualVoicemailSmsFilterSettings.CREATOR.createFromParcel(parcel1);
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

        public int getVoiceActivationState(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(60, parcel, parcel1, 0);
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

        public int getVoiceMessageCount()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(62, parcel, parcel1, 0);
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

        public int getVoiceMessageCountForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(63, parcel, parcel1, 0);
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

        public int getVoiceNetworkTypeForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(77, parcel, parcel1, 0);
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

        public Uri getVoicemailRingtoneUri(PhoneAccountHandle phoneaccounthandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            if(phoneaccounthandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(146, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_98;
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

        public NetworkStats getVtDataUsage(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(160, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            NetworkStats networkstats = (NetworkStats)NetworkStats.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networkstats;
_L2:
            networkstats = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean handlePinMmi(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(24, parcel, parcel1, 0);
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

        public boolean handlePinMmiForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
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

        public void handleUssdRequest(int i, String s, ResultReceiver resultreceiver)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(25, parcel, parcel1, 0);
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

        public boolean hasIccCard()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(78, parcel, parcel1, 0);
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

        public boolean hasIccCardUsingSlotIndex(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(79, parcel, parcel1, 0);
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

        public boolean iccCloseLogicalChannel(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(86, parcel, parcel1, 0);
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

        public byte[] iccExchangeSimIO(int i, int j, int k, int l, int i1, int j1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeString(s);
            mRemote.transact(89, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createByteArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IccOpenLogicalChannelResponse iccOpenLogicalChannel(int i, String s, String s1, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(j);
            mRemote.transact(85, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (IccOpenLogicalChannelResponse)IccOpenLogicalChannelResponse.CREATOR.createFromParcel(parcel1);
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

        public String iccTransmitApduBasicChannel(int i, int j, int k, int l, int i1, int j1, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeString(s);
            mRemote.transact(88, parcel, parcel1, 0);
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

        public String iccTransmitApduLogicalChannel(int i, int j, int k, int l, int i1, int j1, int k1, 
                String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            parcel.writeString(s);
            mRemote.transact(87, parcel, parcel1, 0);
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

        public int invokeOemRilRequestRaw(byte abyte0[], byte abyte1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeByteArray(abyte0);
            if(abyte1 != null)
                break MISSING_BLOCK_LABEL_75;
            parcel.writeInt(-1);
_L1:
            int i;
            mRemote.transact(121, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.readByteArray(abyte1);
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(abyte1.length);
              goto _L1
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public boolean isConcurrentVoiceAndDataAllowed(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(64, parcel, parcel1, 0);
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

        public boolean isDataConnectivityPossible(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(40, parcel, parcel1, 0);
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

        public boolean isHearingAidCompatibilitySupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(131, parcel, parcel1, 0);
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

        public boolean isIdle(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isIdleForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isImsRegistered()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(132, parcel, parcel1, 0);
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

        public boolean isImsRegisteredForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(133, parcel, parcel1, 0);
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

        public boolean isOffhook(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isOffhookForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
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

        public boolean isRadioOn(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
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

        public boolean isRadioOnForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public boolean isRinging(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
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

        public boolean isRingingForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isTtyModeSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(130, parcel, parcel1, 0);
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

        public boolean isVideoCallingEnabled(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(127, parcel, parcel1, 0);
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

        public boolean isVideoTelephonyAvailable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(136, parcel, parcel1, 0);
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

        public boolean isVoicemailVibrationEnabled(PhoneAccountHandle phoneaccounthandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(148, parcel, parcel1, 0);
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

        public boolean isVolteAvailable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(135, parcel, parcel1, 0);
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

        public boolean isWifiCallingAvailable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(134, parcel, parcel1, 0);
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

        public boolean isWorldPhone()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(129, parcel, parcel1, 0);
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

        public boolean needMobileRadioShutdown()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(122, parcel, parcel1, 0);
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

        public boolean needsOtaServiceProvisioning()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(56, parcel, parcel1, 0);
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

        public String nvReadItem(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(91, parcel, parcel1, 0);
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

        public boolean nvResetConfig(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(94, parcel, parcel1, 0);
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

        public boolean nvWriteCdmaPrl(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeByteArray(abyte0);
            mRemote.transact(93, parcel, parcel1, 0);
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
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public boolean nvWriteItem(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(92, parcel, parcel1, 0);
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

        public void requestModemActivityInfo(ResultReceiver resultreceiver)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_46;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(144, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            resultreceiver;
            parcel.recycle();
            throw resultreceiver;
        }

        public int requestNetworkScan(int i, NetworkScanRequest networkscanrequest, Messenger messenger, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            if(networkscanrequest == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networkscanrequest.writeToParcel(parcel, 0);
_L3:
            if(messenger == null)
                break MISSING_BLOCK_LABEL_126;
            parcel.writeInt(1);
            messenger.writeToParcel(parcel, 0);
_L4:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(101, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            networkscanrequest;
            parcel1.recycle();
            parcel.recycle();
            throw networkscanrequest;
            parcel.writeInt(0);
              goto _L4
        }

        public void sendDialerSpecialCode(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(72, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String sendEnvelopeWithStatus(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(90, parcel, parcel1, 0);
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

        public void sendVisualVoicemailSmsForSubscriber(String s, int i, String s1, int j, String s2, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeInt(j);
            parcel.writeString(s2);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_101;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(71, parcel, parcel1, 0);
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

        public int setAllowedCarriers(int i, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeTypedList(list);
            mRemote.transact(155, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void setCellInfoListRate(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(83, parcel, parcel1, 0);
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

        public void setDataActivationState(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(59, parcel, parcel1, 0);
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

        public void setDataEnabled(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(105, parcel, parcel1, 0);
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

        public void setImsRegistrationState(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(108, parcel, parcel1, 0);
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

        public boolean setLine1NumberForDisplayForSubscriber(int i, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(115, parcel, parcel1, 0);
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

        public void setNetworkSelectionModeAutomatic(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(99, parcel, parcel1, 0);
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

        public boolean setNetworkSelectionModeManual(int i, OperatorInfo operatorinfo, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            if(operatorinfo == null)
                break MISSING_BLOCK_LABEL_102;
            parcel.writeInt(1);
            operatorinfo.writeToParcel(parcel, 0);
_L1:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(103, parcel, parcel1, 0);
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
            operatorinfo;
            parcel1.recycle();
            parcel.recycle();
            throw operatorinfo;
        }

        public boolean setOperatorBrandOverride(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(119, parcel, parcel1, 0);
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

        public void setPolicyDataEnabled(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(161, parcel, parcel1, 0);
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

        public boolean setPreferredNetworkType(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(104, parcel, parcel1, 0);
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

        public boolean setRadio(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public void setRadioCapability(RadioAccessFamily aradioaccessfamily[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeTypedArray(aradioaccessfamily, 0);
            mRemote.transact(124, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            aradioaccessfamily;
            parcel1.recycle();
            parcel.recycle();
            throw aradioaccessfamily;
        }

        public boolean setRadioForSubscriber(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public boolean setRadioPower(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public boolean setRoamingOverride(int i, List list, List list1, List list2, List list3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeStringList(list);
            parcel.writeStringList(list1);
            parcel.writeStringList(list2);
            parcel.writeStringList(list3);
            mRemote.transact(120, parcel, parcel1, 0);
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
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void setSimPowerStateForSlot(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(163, parcel, parcel1, 0);
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

        public void setVoiceActivationState(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(58, parcel, parcel1, 0);
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

        public boolean setVoiceMailNumber(int i, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(57, parcel, parcel1, 0);
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

        public void setVoicemailRingtoneUri(String s, PhoneAccountHandle phoneaccounthandle, Uri uri)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            if(phoneaccounthandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L3:
            if(uri == null)
                break MISSING_BLOCK_LABEL_113;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(147, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
            parcel.writeInt(0);
              goto _L4
        }

        public void setVoicemailVibrationEnabled(String s, PhoneAccountHandle phoneaccounthandle, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            if(phoneaccounthandle == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            phoneaccounthandle.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(149, parcel, parcel1, 0);
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

        public void shutdownMobileRadios()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(123, parcel, parcel1, 0);
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

        public void silenceRinger()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void stopNetworkScan(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(102, parcel, parcel1, 0);
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

        public boolean supplyPin(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public boolean supplyPinForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public int[] supplyPinReportResult(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int[] supplyPinReportResultForSubscriber(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean supplyPuk(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean supplyPukForSubscriber(int i, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(19, parcel, parcel1, 0);
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

        public int[] supplyPukReportResult(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int[] supplyPukReportResultForSubscriber(int i, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void toggleRadioOnOff()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(27, parcel, parcel1, 0);
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

        public void toggleRadioOnOffForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(28, parcel, parcel1, 0);
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

        public void updateServiceLocation()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            mRemote.transact(32, parcel, parcel1, 0);
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

        public void updateServiceLocationForSubscriber(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.telephony.ITelephony");
            parcel.writeInt(i);
            mRemote.transact(33, parcel, parcel1, 0);
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


    public abstract void answerRingingCall()
        throws RemoteException;

    public abstract void answerRingingCallForSubscriber(int i)
        throws RemoteException;

    public abstract void call(String s, String s1)
        throws RemoteException;

    public abstract boolean canChangeDtmfToneLength()
        throws RemoteException;

    public abstract void carrierActionReportDefaultNetworkStatus(int i, boolean flag)
        throws RemoteException;

    public abstract void carrierActionSetMeteredApnsEnabled(int i, boolean flag)
        throws RemoteException;

    public abstract void carrierActionSetRadioEnabled(int i, boolean flag)
        throws RemoteException;

    public abstract int checkCarrierPrivilegesForPackage(String s)
        throws RemoteException;

    public abstract int checkCarrierPrivilegesForPackageAnyPhone(String s)
        throws RemoteException;

    public abstract void dial(String s)
        throws RemoteException;

    public abstract boolean disableDataConnectivity()
        throws RemoteException;

    public abstract void disableLocationUpdates()
        throws RemoteException;

    public abstract void disableLocationUpdatesForSubscriber(int i)
        throws RemoteException;

    public abstract void disableVisualVoicemailSmsFilter(String s, int i)
        throws RemoteException;

    public abstract boolean enableDataConnectivity()
        throws RemoteException;

    public abstract void enableLocationUpdates()
        throws RemoteException;

    public abstract void enableLocationUpdatesForSubscriber(int i)
        throws RemoteException;

    public abstract void enableVideoCalling(boolean flag)
        throws RemoteException;

    public abstract void enableVisualVoicemailSmsFilter(String s, int i, VisualVoicemailSmsFilterSettings visualvoicemailsmsfiltersettings)
        throws RemoteException;

    public abstract boolean endCall()
        throws RemoteException;

    public abstract boolean endCallForSubscriber(int i)
        throws RemoteException;

    public abstract void factoryReset(int i)
        throws RemoteException;

    public abstract int getActivePhoneType()
        throws RemoteException;

    public abstract int getActivePhoneTypeForSlot(int i)
        throws RemoteException;

    public abstract VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int i)
        throws RemoteException;

    public abstract String getAidForAppType(int i, int j)
        throws RemoteException;

    public abstract List getAllCellInfo(String s)
        throws RemoteException;

    public abstract List getAllowedCarriers(int i)
        throws RemoteException;

    public abstract byte[] getAtr()
        throws RemoteException;

    public abstract byte[] getAtrUsingSubId(int i)
        throws RemoteException;

    public abstract int getCalculatedPreferredNetworkType(String s)
        throws RemoteException;

    public abstract int getCallState()
        throws RemoteException;

    public abstract int getCallStateForSlot(int i)
        throws RemoteException;

    public abstract List getCarrierPackageNamesForIntentAndPhone(Intent intent, int i)
        throws RemoteException;

    public abstract int getCarrierPrivilegeStatus(int i)
        throws RemoteException;

    public abstract int getCdmaEriIconIndex(String s)
        throws RemoteException;

    public abstract int getCdmaEriIconIndexForSubscriber(int i, String s)
        throws RemoteException;

    public abstract int getCdmaEriIconMode(String s)
        throws RemoteException;

    public abstract int getCdmaEriIconModeForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getCdmaEriText(String s)
        throws RemoteException;

    public abstract String getCdmaEriTextForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getCdmaMdn(int i)
        throws RemoteException;

    public abstract String getCdmaMin(int i)
        throws RemoteException;

    public abstract String getCdmaPrlVersion(int i)
        throws RemoteException;

    public abstract Bundle getCellLocation(String s)
        throws RemoteException;

    public abstract CellNetworkScanResult getCellNetworkScanResults(int i)
        throws RemoteException;

    public abstract List getClientRequestStats(String s, int i)
        throws RemoteException;

    public abstract int getDataActivationState(int i, String s)
        throws RemoteException;

    public abstract int getDataActivity()
        throws RemoteException;

    public abstract boolean getDataEnabled(int i)
        throws RemoteException;

    public abstract int getDataNetworkType(String s)
        throws RemoteException;

    public abstract int getDataNetworkTypeForSubscriber(int i, String s)
        throws RemoteException;

    public abstract int getDataState()
        throws RemoteException;

    public abstract int getDefaultSim()
        throws RemoteException;

    public abstract String getDeviceId(String s)
        throws RemoteException;

    public abstract String getDeviceSoftwareVersionForSlot(int i, String s)
        throws RemoteException;

    public abstract boolean getEmergencyCallbackMode(int i)
        throws RemoteException;

    public abstract String getEsn(int i)
        throws RemoteException;

    public abstract String[] getForbiddenPlmns(int i, int j)
        throws RemoteException;

    public abstract String getImeiForSlot(int i, String s)
        throws RemoteException;

    public abstract IImsServiceController getImsServiceControllerAndListen(int i, int j, IImsServiceFeatureListener iimsservicefeaturelistener)
        throws RemoteException;

    public abstract String getLine1AlphaTagForDisplay(int i, String s)
        throws RemoteException;

    public abstract String getLine1NumberForDisplay(int i, String s)
        throws RemoteException;

    public abstract String getLocaleFromDefaultSim()
        throws RemoteException;

    public abstract int getLteOnCdmaMode(String s)
        throws RemoteException;

    public abstract int getLteOnCdmaModeForSubscriber(int i, String s)
        throws RemoteException;

    public abstract String getMeidForSlot(int i, String s)
        throws RemoteException;

    public abstract String[] getMergedSubscriberIds(String s)
        throws RemoteException;

    public abstract List getNeighboringCellInfo(String s)
        throws RemoteException;

    public abstract String getNetworkCountryIsoForPhone(int i)
        throws RemoteException;

    public abstract int getNetworkType()
        throws RemoteException;

    public abstract int getNetworkTypeForSubscriber(int i, String s)
        throws RemoteException;

    public abstract List getPackagesWithCarrierPrivileges()
        throws RemoteException;

    public abstract String[] getPcscfAddress(String s, String s1)
        throws RemoteException;

    public abstract int getPreferredNetworkType(int i)
        throws RemoteException;

    public abstract int getRadioAccessFamily(int i, String s)
        throws RemoteException;

    public abstract ServiceState getServiceStateForSubscriber(int i, String s)
        throws RemoteException;

    public abstract SignalStrength getSignalStrength(int i)
        throws RemoteException;

    public abstract int getSubIdForPhoneAccount(PhoneAccount phoneaccount)
        throws RemoteException;

    public abstract List getTelephonyHistograms()
        throws RemoteException;

    public abstract int getTetherApnRequired()
        throws RemoteException;

    public abstract String getVisualVoicemailPackageName(String s, int i)
        throws RemoteException;

    public abstract Bundle getVisualVoicemailSettings(String s, int i)
        throws RemoteException;

    public abstract VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(String s, int i)
        throws RemoteException;

    public abstract int getVoiceActivationState(int i, String s)
        throws RemoteException;

    public abstract int getVoiceMessageCount()
        throws RemoteException;

    public abstract int getVoiceMessageCountForSubscriber(int i)
        throws RemoteException;

    public abstract int getVoiceNetworkTypeForSubscriber(int i, String s)
        throws RemoteException;

    public abstract Uri getVoicemailRingtoneUri(PhoneAccountHandle phoneaccounthandle)
        throws RemoteException;

    public abstract NetworkStats getVtDataUsage(int i, boolean flag)
        throws RemoteException;

    public abstract boolean handlePinMmi(String s)
        throws RemoteException;

    public abstract boolean handlePinMmiForSubscriber(int i, String s)
        throws RemoteException;

    public abstract void handleUssdRequest(int i, String s, ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract boolean hasIccCard()
        throws RemoteException;

    public abstract boolean hasIccCardUsingSlotIndex(int i)
        throws RemoteException;

    public abstract boolean iccCloseLogicalChannel(int i, int j)
        throws RemoteException;

    public abstract byte[] iccExchangeSimIO(int i, int j, int k, int l, int i1, int j1, String s)
        throws RemoteException;

    public abstract IccOpenLogicalChannelResponse iccOpenLogicalChannel(int i, String s, String s1, int j)
        throws RemoteException;

    public abstract String iccTransmitApduBasicChannel(int i, int j, int k, int l, int i1, int j1, String s)
        throws RemoteException;

    public abstract String iccTransmitApduLogicalChannel(int i, int j, int k, int l, int i1, int j1, int k1, 
            String s)
        throws RemoteException;

    public abstract int invokeOemRilRequestRaw(byte abyte0[], byte abyte1[])
        throws RemoteException;

    public abstract boolean isConcurrentVoiceAndDataAllowed(int i)
        throws RemoteException;

    public abstract boolean isDataConnectivityPossible(int i)
        throws RemoteException;

    public abstract boolean isHearingAidCompatibilitySupported()
        throws RemoteException;

    public abstract boolean isIdle(String s)
        throws RemoteException;

    public abstract boolean isIdleForSubscriber(int i, String s)
        throws RemoteException;

    public abstract boolean isImsRegistered()
        throws RemoteException;

    public abstract boolean isImsRegisteredForSubscriber(int i)
        throws RemoteException;

    public abstract boolean isOffhook(String s)
        throws RemoteException;

    public abstract boolean isOffhookForSubscriber(int i, String s)
        throws RemoteException;

    public abstract boolean isRadioOn(String s)
        throws RemoteException;

    public abstract boolean isRadioOnForSubscriber(int i, String s)
        throws RemoteException;

    public abstract boolean isRinging(String s)
        throws RemoteException;

    public abstract boolean isRingingForSubscriber(int i, String s)
        throws RemoteException;

    public abstract boolean isTtyModeSupported()
        throws RemoteException;

    public abstract boolean isVideoCallingEnabled(String s)
        throws RemoteException;

    public abstract boolean isVideoTelephonyAvailable()
        throws RemoteException;

    public abstract boolean isVoicemailVibrationEnabled(PhoneAccountHandle phoneaccounthandle)
        throws RemoteException;

    public abstract boolean isVolteAvailable()
        throws RemoteException;

    public abstract boolean isWifiCallingAvailable()
        throws RemoteException;

    public abstract boolean isWorldPhone()
        throws RemoteException;

    public abstract boolean needMobileRadioShutdown()
        throws RemoteException;

    public abstract boolean needsOtaServiceProvisioning()
        throws RemoteException;

    public abstract String nvReadItem(int i)
        throws RemoteException;

    public abstract boolean nvResetConfig(int i)
        throws RemoteException;

    public abstract boolean nvWriteCdmaPrl(byte abyte0[])
        throws RemoteException;

    public abstract boolean nvWriteItem(int i, String s)
        throws RemoteException;

    public abstract void requestModemActivityInfo(ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract int requestNetworkScan(int i, NetworkScanRequest networkscanrequest, Messenger messenger, IBinder ibinder)
        throws RemoteException;

    public abstract void sendDialerSpecialCode(String s, String s1)
        throws RemoteException;

    public abstract String sendEnvelopeWithStatus(int i, String s)
        throws RemoteException;

    public abstract void sendVisualVoicemailSmsForSubscriber(String s, int i, String s1, int j, String s2, PendingIntent pendingintent)
        throws RemoteException;

    public abstract int setAllowedCarriers(int i, List list)
        throws RemoteException;

    public abstract void setCellInfoListRate(int i)
        throws RemoteException;

    public abstract void setDataActivationState(int i, int j)
        throws RemoteException;

    public abstract void setDataEnabled(int i, boolean flag)
        throws RemoteException;

    public abstract void setImsRegistrationState(boolean flag)
        throws RemoteException;

    public abstract boolean setLine1NumberForDisplayForSubscriber(int i, String s, String s1)
        throws RemoteException;

    public abstract void setNetworkSelectionModeAutomatic(int i)
        throws RemoteException;

    public abstract boolean setNetworkSelectionModeManual(int i, OperatorInfo operatorinfo, boolean flag)
        throws RemoteException;

    public abstract boolean setOperatorBrandOverride(int i, String s)
        throws RemoteException;

    public abstract void setPolicyDataEnabled(boolean flag, int i)
        throws RemoteException;

    public abstract boolean setPreferredNetworkType(int i, int j)
        throws RemoteException;

    public abstract boolean setRadio(boolean flag)
        throws RemoteException;

    public abstract void setRadioCapability(RadioAccessFamily aradioaccessfamily[])
        throws RemoteException;

    public abstract boolean setRadioForSubscriber(int i, boolean flag)
        throws RemoteException;

    public abstract boolean setRadioPower(boolean flag)
        throws RemoteException;

    public abstract boolean setRoamingOverride(int i, List list, List list1, List list2, List list3)
        throws RemoteException;

    public abstract void setSimPowerStateForSlot(int i, int j)
        throws RemoteException;

    public abstract void setVoiceActivationState(int i, int j)
        throws RemoteException;

    public abstract boolean setVoiceMailNumber(int i, String s, String s1)
        throws RemoteException;

    public abstract void setVoicemailRingtoneUri(String s, PhoneAccountHandle phoneaccounthandle, Uri uri)
        throws RemoteException;

    public abstract void setVoicemailVibrationEnabled(String s, PhoneAccountHandle phoneaccounthandle, boolean flag)
        throws RemoteException;

    public abstract void shutdownMobileRadios()
        throws RemoteException;

    public abstract void silenceRinger()
        throws RemoteException;

    public abstract void stopNetworkScan(int i, int j)
        throws RemoteException;

    public abstract boolean supplyPin(String s)
        throws RemoteException;

    public abstract boolean supplyPinForSubscriber(int i, String s)
        throws RemoteException;

    public abstract int[] supplyPinReportResult(String s)
        throws RemoteException;

    public abstract int[] supplyPinReportResultForSubscriber(int i, String s)
        throws RemoteException;

    public abstract boolean supplyPuk(String s, String s1)
        throws RemoteException;

    public abstract boolean supplyPukForSubscriber(int i, String s, String s1)
        throws RemoteException;

    public abstract int[] supplyPukReportResult(String s, String s1)
        throws RemoteException;

    public abstract int[] supplyPukReportResultForSubscriber(int i, String s, String s1)
        throws RemoteException;

    public abstract void toggleRadioOnOff()
        throws RemoteException;

    public abstract void toggleRadioOnOffForSubscriber(int i)
        throws RemoteException;

    public abstract void updateServiceLocation()
        throws RemoteException;

    public abstract void updateServiceLocationForSubscriber(int i)
        throws RemoteException;
}
