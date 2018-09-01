// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.juphoon.service.im;

import android.os.*;

public interface IRcsImService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRcsImService
    {

        public static IRcsImService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.juphoon.service.im.IRcsImService");
            if(iinterface != null && (iinterface instanceof IRcsImService))
                return (IRcsImService)iinterface;
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
                parcel1.writeString("com.juphoon.service.im.IRcsImService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag = isSession1To1();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSend(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSendB(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSendP(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSendS(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSendV(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSendVU(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSendC(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSendCU(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSendD(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSendDU(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSend(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSendB(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSendP(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSendS(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSendV(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSendVU(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSendC(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSendCU(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSendD(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSendDU(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImFileTrsf(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImFileTrsfX(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImFileTrsfU(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImFileTrsfB(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImFileTrsfP(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImFileTrsfS(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                i = Mtc_ImFileResume(flag1, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                i = Mtc_ImFileResumeP(flag2, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImFileAcceptResume(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImFileFetchViaMsrp(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                String s = parcel.readString();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                i = Mtc_ImFileRelease(s, flag3);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_GsGInfoPushCoord(parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_GsGInfoPushCoordP(parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 35: // '#'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_GsGInfoPushCoordU(parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 36: // '$'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_GsGInfoPushCoordS(parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 37: // '%'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_GsGInfoPushBoth(parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 38: // '&'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_GsGInfoPushBothP(parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 39: // '\''
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_GsGInfoPushBothU(parcel.readString(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 40: // '('
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_GsGInfoPushAcpt(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 41: // ')'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_GsGInfoPushDeny(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 42: // '*'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag4 = Mtc_ImSessIsExist(parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 43: // '+'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImSessEstabU(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 44: // ','
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessReJoinG(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 45: // '-'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessReStartG(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 46: // '.'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessAddPartp(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 47: // '/'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessAddPartpU(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 48: // '0'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessEplPartp(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 49: // '1'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessEplPartpU(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 50: // '2'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessAccept(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 51: // '3'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessAcceptPhone(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 52: // '4'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessReject(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 53: // '5'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessRejectPhone(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 54: // '6'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessCancel(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 55: // '7'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessLeave(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 56: // '8'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImSessMsgSendM(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 57: // '9'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImSessMsgSendMV(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 58: // ':'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImSessMsgSendMC(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 59: // ';'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImSessMsgSendMD(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 60: // '<'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImSessMsgSendMPhone(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 61: // '='
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImSessMsgSendMPhoneV(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 62: // '>'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImSessMsgSendMPhoneC(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 63: // '?'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                i = Mtc_ImFileResumeX(flag5, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 64: // '@'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImFileResumeU(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 65: // 'A'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImFileResumeS(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 66: // 'B'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                i = Mtc_ImFileResumeB(flag6, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 67: // 'C'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImFileFetchViaMsrpX(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_GsGInfoPushCoordX(parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 69: // 'E'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag7 = Mtc_ImDbGetAutAcceptGroupChat();
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 70: // 'F'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag8 = Mtc_ImDbGetImdnSendDeliReqEnable();
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 71: // 'G'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImPMsgSendU(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 72: // 'H'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImLMsgSendU(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 73: // 'I'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImdnSendDisp(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 74: // 'J'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessMdfySubject(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 75: // 'K'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                String s3 = parcel.readString();
                String s5 = parcel.readString();
                double d = parcel.readDouble();
                double d2 = parcel.readDouble();
                float f = parcel.readFloat();
                String s1 = parcel.readString();
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                i = Mtc_ImSessMsgSendGeoCoord(s3, s5, d, d2, f, s1, flag9);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 76: // 'L'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                String s6 = parcel.readString();
                String s7 = parcel.readString();
                String s4 = parcel.readString();
                double d3 = parcel.readDouble();
                double d1 = parcel.readDouble();
                float f1 = parcel.readFloat();
                String s2 = parcel.readString();
                boolean flag10;
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                i = Mtc_ImSessMsgSendGeoBoth(s6, s7, s4, d3, d1, f1, s2, flag10);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 77: // 'M'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag11 = Mtc_ImDbGetFtAutAccept();
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 78: // 'N'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag12 = Mtc_ImDbGetFtThumb();
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 79: // 'O'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImDbGetFtMaxRunVusers();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 80: // 'P'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImDbGetMaxSizeFileTr();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 81: // 'Q'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImDbGetFtWarnSize();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 82: // 'R'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImFileAccept(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 83: // 'S'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImFileReject(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 84: // 'T'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessDissolve(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 85: // 'U'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessMdfyChairMan(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 86: // 'V'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessAcptMdfyChairMan(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 87: // 'W'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessRjctMdfyChairMan(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 88: // 'X'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessMdfyDispName(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 89: // 'Y'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsCreate(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 90: // 'Z'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsDelete(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 91: // '['
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsSetCookie(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 92: // '\\'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImMsGetCookie(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 93: // ']'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsBackupBegin(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 94: // '^'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsBackupEnd(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 95: // '_'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsBackupAppend(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 96: // '`'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsRestoreBegin(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 97: // 'a'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsRestoreEnd(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 98: // 'b'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsGetObjCount(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 99: // 'c'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsObjCreate(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 100: // 'd'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsObjGetType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 101: // 'e'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsObjDelete(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 102: // 'f'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsObjAddChild(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 103: // 'g'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsObjGetChildCount(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 104: // 'h'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsObjEnumChild(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 105: // 'i'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsObjAddStr(parcel.readInt(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 106: // 'j'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsObjAddInt(parcel.readInt(), parcel.readInt(), parcel.readLong());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 107: // 'k'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImMsObjAddData(parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 108: // 'l'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImMsObjGetStr(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 109: // 'm'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                long l = Mtc_ImMsObjGetInt(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 110: // 'n'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImMsObjGetData(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 111: // 'o'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImDbGetMaxAdhocGroupSize();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 112: // 'p'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImSessGetMaxUsrCnt(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 113: // 'q'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImGenPartpLstId(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 114: // 'r'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_ImParsePartpLstId(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 115: // 's'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImConfMSubsConfLst();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 116: // 't'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImConfMSubsConf(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 117: // 'u'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_GsGInfoSaveData(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readFloat(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 118: // 'v'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_GsGInfoLoadData(parcel.readInt(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 119: // 'w'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_ImmsGetProcessingSessId();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 120: // 'x'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag13 = Mtc_ImDbGetCpimBase64EncodeEnable();
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 121: // 'y'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                boolean flag14 = Mtc_ImDbGetThumbBase64EncodeEnable();
                parcel1.writeNoException();
                if(flag14)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 122: // 'z'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                parcel = Mtc_PaSessEncodeMsg(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 123: // '{'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_GInfoFetchViaMsrp(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 124: // '|'
                parcel.enforceInterface("com.juphoon.service.im.IRcsImService");
                i = Mtc_GInfoFetchViaMsrpX(parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.juphoon.service.im.IRcsImService";
        static final int TRANSACTION_Mtc_GInfoFetchViaMsrp = 123;
        static final int TRANSACTION_Mtc_GInfoFetchViaMsrpX = 124;
        static final int TRANSACTION_Mtc_GsGInfoLoadData = 118;
        static final int TRANSACTION_Mtc_GsGInfoPushAcpt = 40;
        static final int TRANSACTION_Mtc_GsGInfoPushBoth = 37;
        static final int TRANSACTION_Mtc_GsGInfoPushBothP = 38;
        static final int TRANSACTION_Mtc_GsGInfoPushBothU = 39;
        static final int TRANSACTION_Mtc_GsGInfoPushCoord = 33;
        static final int TRANSACTION_Mtc_GsGInfoPushCoordP = 34;
        static final int TRANSACTION_Mtc_GsGInfoPushCoordS = 36;
        static final int TRANSACTION_Mtc_GsGInfoPushCoordU = 35;
        static final int TRANSACTION_Mtc_GsGInfoPushCoordX = 68;
        static final int TRANSACTION_Mtc_GsGInfoPushDeny = 41;
        static final int TRANSACTION_Mtc_GsGInfoSaveData = 117;
        static final int TRANSACTION_Mtc_ImConfMSubsConf = 116;
        static final int TRANSACTION_Mtc_ImConfMSubsConfLst = 115;
        static final int TRANSACTION_Mtc_ImDbGetAutAcceptGroupChat = 69;
        static final int TRANSACTION_Mtc_ImDbGetCpimBase64EncodeEnable = 120;
        static final int TRANSACTION_Mtc_ImDbGetFtAutAccept = 77;
        static final int TRANSACTION_Mtc_ImDbGetFtMaxRunVusers = 79;
        static final int TRANSACTION_Mtc_ImDbGetFtThumb = 78;
        static final int TRANSACTION_Mtc_ImDbGetFtWarnSize = 81;
        static final int TRANSACTION_Mtc_ImDbGetImdnSendDeliReqEnable = 70;
        static final int TRANSACTION_Mtc_ImDbGetMaxAdhocGroupSize = 111;
        static final int TRANSACTION_Mtc_ImDbGetMaxSizeFileTr = 80;
        static final int TRANSACTION_Mtc_ImDbGetThumbBase64EncodeEnable = 121;
        static final int TRANSACTION_Mtc_ImFileAccept = 82;
        static final int TRANSACTION_Mtc_ImFileAcceptResume = 30;
        static final int TRANSACTION_Mtc_ImFileFetchViaMsrp = 31;
        static final int TRANSACTION_Mtc_ImFileFetchViaMsrpX = 67;
        static final int TRANSACTION_Mtc_ImFileReject = 83;
        static final int TRANSACTION_Mtc_ImFileRelease = 32;
        static final int TRANSACTION_Mtc_ImFileResume = 28;
        static final int TRANSACTION_Mtc_ImFileResumeB = 66;
        static final int TRANSACTION_Mtc_ImFileResumeP = 29;
        static final int TRANSACTION_Mtc_ImFileResumeS = 65;
        static final int TRANSACTION_Mtc_ImFileResumeU = 64;
        static final int TRANSACTION_Mtc_ImFileResumeX = 63;
        static final int TRANSACTION_Mtc_ImFileTrsf = 22;
        static final int TRANSACTION_Mtc_ImFileTrsfB = 25;
        static final int TRANSACTION_Mtc_ImFileTrsfP = 26;
        static final int TRANSACTION_Mtc_ImFileTrsfS = 27;
        static final int TRANSACTION_Mtc_ImFileTrsfU = 24;
        static final int TRANSACTION_Mtc_ImFileTrsfX = 23;
        static final int TRANSACTION_Mtc_ImGenPartpLstId = 113;
        static final int TRANSACTION_Mtc_ImLMsgSend = 12;
        static final int TRANSACTION_Mtc_ImLMsgSendB = 13;
        static final int TRANSACTION_Mtc_ImLMsgSendC = 18;
        static final int TRANSACTION_Mtc_ImLMsgSendCU = 19;
        static final int TRANSACTION_Mtc_ImLMsgSendD = 20;
        static final int TRANSACTION_Mtc_ImLMsgSendDU = 21;
        static final int TRANSACTION_Mtc_ImLMsgSendP = 14;
        static final int TRANSACTION_Mtc_ImLMsgSendS = 15;
        static final int TRANSACTION_Mtc_ImLMsgSendU = 72;
        static final int TRANSACTION_Mtc_ImLMsgSendV = 16;
        static final int TRANSACTION_Mtc_ImLMsgSendVU = 17;
        static final int TRANSACTION_Mtc_ImMsBackupAppend = 95;
        static final int TRANSACTION_Mtc_ImMsBackupBegin = 93;
        static final int TRANSACTION_Mtc_ImMsBackupEnd = 94;
        static final int TRANSACTION_Mtc_ImMsCreate = 89;
        static final int TRANSACTION_Mtc_ImMsDelete = 90;
        static final int TRANSACTION_Mtc_ImMsGetCookie = 92;
        static final int TRANSACTION_Mtc_ImMsGetObjCount = 98;
        static final int TRANSACTION_Mtc_ImMsObjAddChild = 102;
        static final int TRANSACTION_Mtc_ImMsObjAddData = 107;
        static final int TRANSACTION_Mtc_ImMsObjAddInt = 106;
        static final int TRANSACTION_Mtc_ImMsObjAddStr = 105;
        static final int TRANSACTION_Mtc_ImMsObjCreate = 99;
        static final int TRANSACTION_Mtc_ImMsObjDelete = 101;
        static final int TRANSACTION_Mtc_ImMsObjEnumChild = 104;
        static final int TRANSACTION_Mtc_ImMsObjGetChildCount = 103;
        static final int TRANSACTION_Mtc_ImMsObjGetData = 110;
        static final int TRANSACTION_Mtc_ImMsObjGetInt = 109;
        static final int TRANSACTION_Mtc_ImMsObjGetStr = 108;
        static final int TRANSACTION_Mtc_ImMsObjGetType = 100;
        static final int TRANSACTION_Mtc_ImMsRestoreBegin = 96;
        static final int TRANSACTION_Mtc_ImMsRestoreEnd = 97;
        static final int TRANSACTION_Mtc_ImMsSetCookie = 91;
        static final int TRANSACTION_Mtc_ImPMsgSend = 2;
        static final int TRANSACTION_Mtc_ImPMsgSendB = 3;
        static final int TRANSACTION_Mtc_ImPMsgSendC = 8;
        static final int TRANSACTION_Mtc_ImPMsgSendCU = 9;
        static final int TRANSACTION_Mtc_ImPMsgSendD = 10;
        static final int TRANSACTION_Mtc_ImPMsgSendDU = 11;
        static final int TRANSACTION_Mtc_ImPMsgSendP = 4;
        static final int TRANSACTION_Mtc_ImPMsgSendS = 5;
        static final int TRANSACTION_Mtc_ImPMsgSendU = 71;
        static final int TRANSACTION_Mtc_ImPMsgSendV = 6;
        static final int TRANSACTION_Mtc_ImPMsgSendVU = 7;
        static final int TRANSACTION_Mtc_ImParsePartpLstId = 114;
        static final int TRANSACTION_Mtc_ImSessAccept = 50;
        static final int TRANSACTION_Mtc_ImSessAcceptPhone = 51;
        static final int TRANSACTION_Mtc_ImSessAcptMdfyChairMan = 86;
        static final int TRANSACTION_Mtc_ImSessAddPartp = 46;
        static final int TRANSACTION_Mtc_ImSessAddPartpU = 47;
        static final int TRANSACTION_Mtc_ImSessCancel = 54;
        static final int TRANSACTION_Mtc_ImSessDissolve = 84;
        static final int TRANSACTION_Mtc_ImSessEplPartp = 48;
        static final int TRANSACTION_Mtc_ImSessEplPartpU = 49;
        static final int TRANSACTION_Mtc_ImSessEstabU = 43;
        static final int TRANSACTION_Mtc_ImSessGetMaxUsrCnt = 112;
        static final int TRANSACTION_Mtc_ImSessIsExist = 42;
        static final int TRANSACTION_Mtc_ImSessLeave = 55;
        static final int TRANSACTION_Mtc_ImSessMdfyChairMan = 85;
        static final int TRANSACTION_Mtc_ImSessMdfyDispName = 88;
        static final int TRANSACTION_Mtc_ImSessMdfySubject = 74;
        static final int TRANSACTION_Mtc_ImSessMsgSendGeoBoth = 76;
        static final int TRANSACTION_Mtc_ImSessMsgSendGeoCoord = 75;
        static final int TRANSACTION_Mtc_ImSessMsgSendM = 56;
        static final int TRANSACTION_Mtc_ImSessMsgSendMC = 58;
        static final int TRANSACTION_Mtc_ImSessMsgSendMD = 59;
        static final int TRANSACTION_Mtc_ImSessMsgSendMPhone = 60;
        static final int TRANSACTION_Mtc_ImSessMsgSendMPhoneC = 62;
        static final int TRANSACTION_Mtc_ImSessMsgSendMPhoneV = 61;
        static final int TRANSACTION_Mtc_ImSessMsgSendMV = 57;
        static final int TRANSACTION_Mtc_ImSessReJoinG = 44;
        static final int TRANSACTION_Mtc_ImSessReStartG = 45;
        static final int TRANSACTION_Mtc_ImSessReject = 52;
        static final int TRANSACTION_Mtc_ImSessRejectPhone = 53;
        static final int TRANSACTION_Mtc_ImSessRjctMdfyChairMan = 87;
        static final int TRANSACTION_Mtc_ImdnSendDisp = 73;
        static final int TRANSACTION_Mtc_ImmsGetProcessingSessId = 119;
        static final int TRANSACTION_Mtc_PaSessEncodeMsg = 122;
        static final int TRANSACTION_isSession1To1 = 1;

        public Stub()
        {
            attachInterface(this, "com.juphoon.service.im.IRcsImService");
        }
    }

    private static class Stub.Proxy
        implements IRcsImService
    {

        public int Mtc_GInfoFetchViaMsrp(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(123, parcel, parcel1, 0);
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

        public int Mtc_GInfoFetchViaMsrpX(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(124, parcel, parcel1, 0);
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

        public String Mtc_GsGInfoLoadData(int i, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(118, parcel, parcel1, 0);
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

        public int Mtc_GsGInfoPushAcpt(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(40, parcel, parcel1, 0);
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

        public String Mtc_GsGInfoPushBoth(String s, String s1, double d, double d1, float f, 
                String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(37, parcel, parcel1, 0);
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

        public String Mtc_GsGInfoPushBothP(String s, String s1, double d, double d1, float f, 
                String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(38, parcel, parcel1, 0);
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

        public String Mtc_GsGInfoPushBothU(String s, String s1, double d, double d1, float f, 
                String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(39, parcel, parcel1, 0);
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

        public String Mtc_GsGInfoPushCoord(String s, double d, double d1, float f, String s1, 
                String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_GsGInfoPushCoordP(String s, double d, double d1, float f, String s1, 
                String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(34, parcel, parcel1, 0);
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

        public String Mtc_GsGInfoPushCoordS(String s, double d, double d1, float f, String s1, 
                String s2, String s3, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeLong(l);
            mRemote.transact(36, parcel, parcel1, 0);
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

        public String Mtc_GsGInfoPushCoordU(String s, double d, double d1, float f, String s1, 
                String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(35, parcel, parcel1, 0);
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

        public String Mtc_GsGInfoPushCoordX(String s, double d, double d1, float f, String s1, 
                String s2, String s3, String s4)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeString(s4);
            mRemote.transact(68, parcel, parcel1, 0);
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

        public int Mtc_GsGInfoPushDeny(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(41, parcel, parcel1, 0);
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

        public String Mtc_GsGInfoSaveData(int i, int j, String s, double d, double d1, 
                float f, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public int Mtc_ImConfMSubsConf(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(116, parcel, parcel1, 0);
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

        public int Mtc_ImConfMSubsConfLst()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            mRemote.transact(115, parcel, parcel1, 0);
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

        public boolean Mtc_ImDbGetAutAcceptGroupChat()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            mRemote.transact(69, parcel, parcel1, 0);
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

        public boolean Mtc_ImDbGetCpimBase64EncodeEnable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean Mtc_ImDbGetFtAutAccept()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            mRemote.transact(77, parcel, parcel1, 0);
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

        public int Mtc_ImDbGetFtMaxRunVusers()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            mRemote.transact(79, parcel, parcel1, 0);
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

        public boolean Mtc_ImDbGetFtThumb()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
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

        public int Mtc_ImDbGetFtWarnSize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            mRemote.transact(81, parcel, parcel1, 0);
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

        public boolean Mtc_ImDbGetImdnSendDeliReqEnable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            mRemote.transact(70, parcel, parcel1, 0);
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

        public int Mtc_ImDbGetMaxAdhocGroupSize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
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

        public int Mtc_ImDbGetMaxSizeFileTr()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            mRemote.transact(80, parcel, parcel1, 0);
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

        public boolean Mtc_ImDbGetThumbBase64EncodeEnable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            mRemote.transact(121, parcel, parcel1, 0);
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

        public int Mtc_ImFileAccept(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(82, parcel, parcel1, 0);
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

        public int Mtc_ImFileAcceptResume(String s, String s1, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(30, parcel, parcel1, 0);
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

        public int Mtc_ImFileFetchViaMsrp(String s, String s1, String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(31, parcel, parcel1, 0);
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

        public int Mtc_ImFileFetchViaMsrpX(String s, String s1, String s2, String s3, String s4, String s5)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeString(s4);
            parcel.writeString(s5);
            mRemote.transact(67, parcel, parcel1, 0);
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

        public int Mtc_ImFileReject(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(83, parcel, parcel1, 0);
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

        public int Mtc_ImFileRelease(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(32, parcel, parcel1, 0);
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

        public int Mtc_ImFileResume(boolean flag, String s, String s1, String s2, String s3, String s4, String s5, 
                int i, int j, int k, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            int l;
            if(flag)
                l = 1;
            else
                l = 0;
            parcel.writeInt(l);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeString(s4);
            parcel.writeString(s5);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeByteArray(abyte0);
            mRemote.transact(28, parcel, parcel1, 0);
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

        public int Mtc_ImFileResumeB(boolean flag, String s, String s1, String s2, String s3, String s4, String s5, 
                int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            int l;
            if(flag)
                l = 1;
            else
                l = 0;
            parcel.writeInt(l);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeString(s4);
            parcel.writeString(s5);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(66, parcel, parcel1, 0);
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

        public int Mtc_ImFileResumeP(boolean flag, String s, String s1, String s2, String s3, String s4, String s5, 
                int i, int j, int k, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            int l;
            if(flag)
                l = 1;
            else
                l = 0;
            parcel.writeInt(l);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeString(s4);
            parcel.writeString(s5);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeByteArray(abyte0);
            mRemote.transact(29, parcel, parcel1, 0);
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

        public int Mtc_ImFileResumeS(String s, String s1, String s2, String s3, String s4, int i, int j, 
                int k, String s5, String s6, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeString(s4);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeString(s5);
            parcel.writeString(s6);
            parcel.writeLong(l);
            mRemote.transact(65, parcel, parcel1, 0);
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

        public int Mtc_ImFileResumeU(String s, String s1, String s2, String s3, String s4, String s5, int i, 
                int j, int k, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeString(s4);
            parcel.writeString(s5);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeByteArray(abyte0);
            mRemote.transact(64, parcel, parcel1, 0);
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

        public int Mtc_ImFileResumeX(boolean flag, String s, String s1, String s2, String s3, String s4, String s5, 
                String s6, int i, int j, int k, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            int l;
            if(flag)
                l = 1;
            else
                l = 0;
            parcel.writeInt(l);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeString(s4);
            parcel.writeString(s5);
            parcel.writeString(s6);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeByteArray(abyte0);
            mRemote.transact(63, parcel, parcel1, 0);
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

        public String Mtc_ImFileTrsf(String s, String s1, String s2, String s3, int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
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

        public String Mtc_ImFileTrsfB(String s, String s1, String s2, String s3, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeInt(i);
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

        public String Mtc_ImFileTrsfP(String s, String s1, String s2, String s3, int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
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

        public String Mtc_ImFileTrsfS(String s, String s1, int i, String s2, String s3, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeLong(l);
            mRemote.transact(27, parcel, parcel1, 0);
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

        public String Mtc_ImFileTrsfU(String s, String s1, String s2, String s3, int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(24, parcel, parcel1, 0);
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

        public String Mtc_ImFileTrsfX(String s, String s1, String s2, String s3, String s4, String s5, int i, 
                byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeString(s4);
            parcel.writeString(s5);
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(23, parcel, parcel1, 0);
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

        public int Mtc_ImGenPartpLstId(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
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

        public String Mtc_ImLMsgSend(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImLMsgSendB(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImLMsgSendC(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImLMsgSendCU(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImLMsgSendD(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImLMsgSendDU(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImLMsgSendP(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImLMsgSendS(String s, String s1, String s2, String s3, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeLong(l);
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

        public String Mtc_ImLMsgSendU(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(72, parcel, parcel1, 0);
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

        public String Mtc_ImLMsgSendV(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImLMsgSendVU(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public int Mtc_ImMsBackupAppend(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(95, parcel, parcel1, 0);
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

        public int Mtc_ImMsBackupBegin(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(93, parcel, parcel1, 0);
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

        public int Mtc_ImMsBackupEnd(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            mRemote.transact(94, parcel, parcel1, 0);
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

        public int Mtc_ImMsCreate(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(89, parcel, parcel1, 0);
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

        public int Mtc_ImMsDelete(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            mRemote.transact(90, parcel, parcel1, 0);
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

        public String Mtc_ImMsGetCookie(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            mRemote.transact(92, parcel, parcel1, 0);
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

        public int Mtc_ImMsGetObjCount(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            mRemote.transact(98, parcel, parcel1, 0);
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

        public int Mtc_ImMsObjAddChild(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(102, parcel, parcel1, 0);
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

        public int Mtc_ImMsObjAddData(int i, int j, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            mRemote.transact(107, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public int Mtc_ImMsObjAddInt(int i, int j, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeLong(l);
            mRemote.transact(106, parcel, parcel1, 0);
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

        public int Mtc_ImMsObjAddStr(int i, int j, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            mRemote.transact(105, parcel, parcel1, 0);
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

        public int Mtc_ImMsObjCreate(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            mRemote.transact(99, parcel, parcel1, 0);
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

        public int Mtc_ImMsObjDelete(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            mRemote.transact(101, parcel, parcel1, 0);
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

        public int Mtc_ImMsObjEnumChild(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(104, parcel, parcel1, 0);
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

        public int Mtc_ImMsObjGetChildCount(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            mRemote.transact(103, parcel, parcel1, 0);
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

        public byte[] Mtc_ImMsObjGetData(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(110, parcel, parcel1, 0);
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

        public long Mtc_ImMsObjGetInt(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(109, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String Mtc_ImMsObjGetStr(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(108, parcel, parcel1, 0);
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

        public int Mtc_ImMsObjGetType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            mRemote.transact(100, parcel, parcel1, 0);
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

        public int Mtc_ImMsRestoreBegin(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(96, parcel, parcel1, 0);
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

        public int Mtc_ImMsRestoreEnd(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
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

        public int Mtc_ImMsSetCookie(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(91, parcel, parcel1, 0);
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

        public String Mtc_ImPMsgSend(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImPMsgSendB(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImPMsgSendC(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImPMsgSendCU(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImPMsgSendD(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImPMsgSendDU(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImPMsgSendP(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImPMsgSendS(String s, String s1, String s2, String s3, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeLong(l);
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

        public String Mtc_ImPMsgSendU(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(71, parcel, parcel1, 0);
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

        public String Mtc_ImPMsgSendV(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImPMsgSendVU(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
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

        public String Mtc_ImParsePartpLstId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeInt(i);
            mRemote.transact(114, parcel, parcel1, 0);
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

        public int Mtc_ImSessAccept(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
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

        public int Mtc_ImSessAcceptPhone(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
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

        public int Mtc_ImSessAcptMdfyChairMan(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(86, parcel, parcel1, 0);
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

        public int Mtc_ImSessAddPartp(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(46, parcel, parcel1, 0);
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

        public int Mtc_ImSessAddPartpU(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(47, parcel, parcel1, 0);
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

        public int Mtc_ImSessCancel(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(54, parcel, parcel1, 0);
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

        public int Mtc_ImSessDissolve(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(84, parcel, parcel1, 0);
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

        public int Mtc_ImSessEplPartp(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(48, parcel, parcel1, 0);
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

        public int Mtc_ImSessEplPartpU(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(49, parcel, parcel1, 0);
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

        public String Mtc_ImSessEstabU(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(43, parcel, parcel1, 0);
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

        public int Mtc_ImSessGetMaxUsrCnt(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
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

        public boolean Mtc_ImSessIsExist(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(42, parcel, parcel1, 0);
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

        public int Mtc_ImSessLeave(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(55, parcel, parcel1, 0);
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

        public int Mtc_ImSessMdfyChairMan(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(85, parcel, parcel1, 0);
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

        public int Mtc_ImSessMdfyDispName(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(88, parcel, parcel1, 0);
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

        public int Mtc_ImSessMdfySubject(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
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

        public int Mtc_ImSessMsgSendGeoBoth(String s, String s1, String s2, double d, double d1, 
                float f, String s3, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s3);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
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

        public int Mtc_ImSessMsgSendGeoCoord(String s, String s1, double d, double d1, float f, 
                String s2, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeDouble(d);
            parcel.writeDouble(d1);
            parcel.writeFloat(f);
            parcel.writeString(s2);
            if(flag)
                i = 1;
            parcel.writeInt(i);
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

        public String Mtc_ImSessMsgSendM(String s, String s1, String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(56, parcel, parcel1, 0);
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

        public String Mtc_ImSessMsgSendMC(String s, String s1, String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(58, parcel, parcel1, 0);
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

        public String Mtc_ImSessMsgSendMD(String s, String s1, String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(59, parcel, parcel1, 0);
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

        public String Mtc_ImSessMsgSendMPhone(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(60, parcel, parcel1, 0);
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

        public String Mtc_ImSessMsgSendMPhoneC(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(62, parcel, parcel1, 0);
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

        public String Mtc_ImSessMsgSendMPhoneV(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(61, parcel, parcel1, 0);
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

        public String Mtc_ImSessMsgSendMV(String s, String s1, String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(57, parcel, parcel1, 0);
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

        public int Mtc_ImSessReJoinG(String s, String s1, String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(44, parcel, parcel1, 0);
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

        public int Mtc_ImSessReStartG(String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(45, parcel, parcel1, 0);
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

        public int Mtc_ImSessReject(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public int Mtc_ImSessRejectPhone(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public int Mtc_ImSessRjctMdfyChairMan(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(87, parcel, parcel1, 0);
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

        public int Mtc_ImdnSendDisp(String s, String s1, String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(73, parcel, parcel1, 0);
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

        public int Mtc_ImmsGetProcessingSessId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            mRemote.transact(119, parcel, parcel1, 0);
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

        public String Mtc_PaSessEncodeMsg(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
            parcel.writeString(s);
            mRemote.transact(122, parcel, parcel1, 0);
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.juphoon.service.im.IRcsImService";
        }

        public boolean isSession1To1()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.im.IRcsImService");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int Mtc_GInfoFetchViaMsrp(String s, String s1)
        throws RemoteException;

    public abstract int Mtc_GInfoFetchViaMsrpX(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_GsGInfoLoadData(int i, String s, String s1)
        throws RemoteException;

    public abstract int Mtc_GsGInfoPushAcpt(String s)
        throws RemoteException;

    public abstract String Mtc_GsGInfoPushBoth(String s, String s1, double d, double d1, float f, 
            String s2, String s3)
        throws RemoteException;

    public abstract String Mtc_GsGInfoPushBothP(String s, String s1, double d, double d1, float f, 
            String s2, String s3)
        throws RemoteException;

    public abstract String Mtc_GsGInfoPushBothU(String s, String s1, double d, double d1, float f, 
            String s2, String s3)
        throws RemoteException;

    public abstract String Mtc_GsGInfoPushCoord(String s, double d, double d1, float f, String s1, 
            String s2)
        throws RemoteException;

    public abstract String Mtc_GsGInfoPushCoordP(String s, double d, double d1, float f, String s1, 
            String s2)
        throws RemoteException;

    public abstract String Mtc_GsGInfoPushCoordS(String s, double d, double d1, float f, String s1, 
            String s2, String s3, long l)
        throws RemoteException;

    public abstract String Mtc_GsGInfoPushCoordU(String s, double d, double d1, float f, String s1, 
            String s2)
        throws RemoteException;

    public abstract String Mtc_GsGInfoPushCoordX(String s, double d, double d1, float f, String s1, 
            String s2, String s3, String s4)
        throws RemoteException;

    public abstract int Mtc_GsGInfoPushDeny(String s)
        throws RemoteException;

    public abstract String Mtc_GsGInfoSaveData(int i, int j, String s, double d, double d1, 
            float f, String s1, String s2)
        throws RemoteException;

    public abstract int Mtc_ImConfMSubsConf(String s, String s1)
        throws RemoteException;

    public abstract int Mtc_ImConfMSubsConfLst()
        throws RemoteException;

    public abstract boolean Mtc_ImDbGetAutAcceptGroupChat()
        throws RemoteException;

    public abstract boolean Mtc_ImDbGetCpimBase64EncodeEnable()
        throws RemoteException;

    public abstract boolean Mtc_ImDbGetFtAutAccept()
        throws RemoteException;

    public abstract int Mtc_ImDbGetFtMaxRunVusers()
        throws RemoteException;

    public abstract boolean Mtc_ImDbGetFtThumb()
        throws RemoteException;

    public abstract int Mtc_ImDbGetFtWarnSize()
        throws RemoteException;

    public abstract boolean Mtc_ImDbGetImdnSendDeliReqEnable()
        throws RemoteException;

    public abstract int Mtc_ImDbGetMaxAdhocGroupSize()
        throws RemoteException;

    public abstract int Mtc_ImDbGetMaxSizeFileTr()
        throws RemoteException;

    public abstract boolean Mtc_ImDbGetThumbBase64EncodeEnable()
        throws RemoteException;

    public abstract int Mtc_ImFileAccept(String s, String s1)
        throws RemoteException;

    public abstract int Mtc_ImFileAcceptResume(String s, String s1, int i, int j)
        throws RemoteException;

    public abstract int Mtc_ImFileFetchViaMsrp(String s, String s1, String s2, String s3)
        throws RemoteException;

    public abstract int Mtc_ImFileFetchViaMsrpX(String s, String s1, String s2, String s3, String s4, String s5)
        throws RemoteException;

    public abstract int Mtc_ImFileReject(String s)
        throws RemoteException;

    public abstract int Mtc_ImFileRelease(String s, boolean flag)
        throws RemoteException;

    public abstract int Mtc_ImFileResume(boolean flag, String s, String s1, String s2, String s3, String s4, String s5, 
            int i, int j, int k, byte abyte0[])
        throws RemoteException;

    public abstract int Mtc_ImFileResumeB(boolean flag, String s, String s1, String s2, String s3, String s4, String s5, 
            int i, int j, int k)
        throws RemoteException;

    public abstract int Mtc_ImFileResumeP(boolean flag, String s, String s1, String s2, String s3, String s4, String s5, 
            int i, int j, int k, byte abyte0[])
        throws RemoteException;

    public abstract int Mtc_ImFileResumeS(String s, String s1, String s2, String s3, String s4, int i, int j, 
            int k, String s5, String s6, long l)
        throws RemoteException;

    public abstract int Mtc_ImFileResumeU(String s, String s1, String s2, String s3, String s4, String s5, int i, 
            int j, int k, byte abyte0[])
        throws RemoteException;

    public abstract int Mtc_ImFileResumeX(boolean flag, String s, String s1, String s2, String s3, String s4, String s5, 
            String s6, int i, int j, int k, byte abyte0[])
        throws RemoteException;

    public abstract String Mtc_ImFileTrsf(String s, String s1, String s2, String s3, int i, byte abyte0[])
        throws RemoteException;

    public abstract String Mtc_ImFileTrsfB(String s, String s1, String s2, String s3, int i)
        throws RemoteException;

    public abstract String Mtc_ImFileTrsfP(String s, String s1, String s2, String s3, int i, byte abyte0[])
        throws RemoteException;

    public abstract String Mtc_ImFileTrsfS(String s, String s1, int i, String s2, String s3, long l)
        throws RemoteException;

    public abstract String Mtc_ImFileTrsfU(String s, String s1, String s2, String s3, int i, byte abyte0[])
        throws RemoteException;

    public abstract String Mtc_ImFileTrsfX(String s, String s1, String s2, String s3, String s4, String s5, int i, 
            byte abyte0[])
        throws RemoteException;

    public abstract int Mtc_ImGenPartpLstId(String s)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSend(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSendB(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSendC(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSendCU(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSendD(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSendDU(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSendP(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSendS(String s, String s1, String s2, String s3, long l)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSendU(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSendV(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImLMsgSendVU(String s, String s1, String s2)
        throws RemoteException;

    public abstract int Mtc_ImMsBackupAppend(int i, int j)
        throws RemoteException;

    public abstract int Mtc_ImMsBackupBegin(int i, String s)
        throws RemoteException;

    public abstract int Mtc_ImMsBackupEnd(int i)
        throws RemoteException;

    public abstract int Mtc_ImMsCreate(String s)
        throws RemoteException;

    public abstract int Mtc_ImMsDelete(int i)
        throws RemoteException;

    public abstract String Mtc_ImMsGetCookie(int i)
        throws RemoteException;

    public abstract int Mtc_ImMsGetObjCount(int i)
        throws RemoteException;

    public abstract int Mtc_ImMsObjAddChild(int i, int j)
        throws RemoteException;

    public abstract int Mtc_ImMsObjAddData(int i, int j, byte abyte0[])
        throws RemoteException;

    public abstract int Mtc_ImMsObjAddInt(int i, int j, long l)
        throws RemoteException;

    public abstract int Mtc_ImMsObjAddStr(int i, int j, String s)
        throws RemoteException;

    public abstract int Mtc_ImMsObjCreate(int i)
        throws RemoteException;

    public abstract int Mtc_ImMsObjDelete(int i)
        throws RemoteException;

    public abstract int Mtc_ImMsObjEnumChild(int i, int j)
        throws RemoteException;

    public abstract int Mtc_ImMsObjGetChildCount(int i)
        throws RemoteException;

    public abstract byte[] Mtc_ImMsObjGetData(int i, int j)
        throws RemoteException;

    public abstract long Mtc_ImMsObjGetInt(int i, int j)
        throws RemoteException;

    public abstract String Mtc_ImMsObjGetStr(int i, int j)
        throws RemoteException;

    public abstract int Mtc_ImMsObjGetType(int i)
        throws RemoteException;

    public abstract int Mtc_ImMsRestoreBegin(int i, String s)
        throws RemoteException;

    public abstract int Mtc_ImMsRestoreEnd(int i)
        throws RemoteException;

    public abstract int Mtc_ImMsSetCookie(int i, String s)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSend(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSendB(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSendC(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSendCU(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSendD(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSendDU(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSendP(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSendS(String s, String s1, String s2, String s3, long l)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSendU(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSendV(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImPMsgSendVU(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImParsePartpLstId(int i)
        throws RemoteException;

    public abstract int Mtc_ImSessAccept(String s, String s1)
        throws RemoteException;

    public abstract int Mtc_ImSessAcceptPhone(String s)
        throws RemoteException;

    public abstract int Mtc_ImSessAcptMdfyChairMan(String s)
        throws RemoteException;

    public abstract int Mtc_ImSessAddPartp(String s, String s1)
        throws RemoteException;

    public abstract int Mtc_ImSessAddPartpU(String s, String s1)
        throws RemoteException;

    public abstract int Mtc_ImSessCancel(String s)
        throws RemoteException;

    public abstract int Mtc_ImSessDissolve(String s)
        throws RemoteException;

    public abstract int Mtc_ImSessEplPartp(String s, String s1)
        throws RemoteException;

    public abstract int Mtc_ImSessEplPartpU(String s, String s1)
        throws RemoteException;

    public abstract String Mtc_ImSessEstabU(String s, String s1, String s2)
        throws RemoteException;

    public abstract int Mtc_ImSessGetMaxUsrCnt(String s)
        throws RemoteException;

    public abstract boolean Mtc_ImSessIsExist(String s)
        throws RemoteException;

    public abstract int Mtc_ImSessLeave(String s)
        throws RemoteException;

    public abstract int Mtc_ImSessMdfyChairMan(String s, String s1)
        throws RemoteException;

    public abstract int Mtc_ImSessMdfyDispName(String s, String s1)
        throws RemoteException;

    public abstract int Mtc_ImSessMdfySubject(String s, String s1)
        throws RemoteException;

    public abstract int Mtc_ImSessMsgSendGeoBoth(String s, String s1, String s2, double d, double d1, 
            float f, String s3, boolean flag)
        throws RemoteException;

    public abstract int Mtc_ImSessMsgSendGeoCoord(String s, String s1, double d, double d1, float f, 
            String s2, boolean flag)
        throws RemoteException;

    public abstract String Mtc_ImSessMsgSendM(String s, String s1, String s2, String s3)
        throws RemoteException;

    public abstract String Mtc_ImSessMsgSendMC(String s, String s1, String s2, String s3)
        throws RemoteException;

    public abstract String Mtc_ImSessMsgSendMD(String s, String s1, String s2, String s3)
        throws RemoteException;

    public abstract String Mtc_ImSessMsgSendMPhone(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImSessMsgSendMPhoneC(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImSessMsgSendMPhoneV(String s, String s1, String s2)
        throws RemoteException;

    public abstract String Mtc_ImSessMsgSendMV(String s, String s1, String s2, String s3)
        throws RemoteException;

    public abstract int Mtc_ImSessReJoinG(String s, String s1, String s2, String s3)
        throws RemoteException;

    public abstract int Mtc_ImSessReStartG(String s, String s1, String s2)
        throws RemoteException;

    public abstract int Mtc_ImSessReject(String s, int i)
        throws RemoteException;

    public abstract int Mtc_ImSessRejectPhone(String s, int i)
        throws RemoteException;

    public abstract int Mtc_ImSessRjctMdfyChairMan(String s)
        throws RemoteException;

    public abstract int Mtc_ImdnSendDisp(String s, String s1, String s2, String s3)
        throws RemoteException;

    public abstract int Mtc_ImmsGetProcessingSessId()
        throws RemoteException;

    public abstract String Mtc_PaSessEncodeMsg(String s)
        throws RemoteException;

    public abstract boolean isSession1To1()
        throws RemoteException;
}
