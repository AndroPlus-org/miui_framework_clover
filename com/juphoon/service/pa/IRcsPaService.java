// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.juphoon.service.pa;

import android.os.*;

public interface IRcsPaService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRcsPaService
    {

        public static IRcsPaService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.juphoon.service.pa.IRcsPaService");
            if(iinterface != null && (iinterface instanceof IRcsPaService))
                return (IRcsPaService)iinterface;
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
                parcel1.writeString("com.juphoon.service.pa.IRcsPaService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessAddSubs(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessCancelSubs(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessQryUsrSubs(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                Mtc_PaSessQryAllUsrSubs();
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetPubLst(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetPubLstRecmd(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetPubDetail(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetPubMenu(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetPrevMsg(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessComplainPub(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                String s = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                i = Mtc_PaSessSetAcptStat(s, flag);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaSessEncodeMsg(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessDecodeMsg(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                Mtc_PaSessRelease(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetPaSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetPaId(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetMenuSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetMenuId(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetMsgSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaSessGetMsgId(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaSessGetResult(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaBscGetPaUuid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaBscGetName(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaBscGetRecmdLv(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaBscGetLogoUrl(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetPaUuid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetName(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetCompany(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetIntro(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaDetailGetUpdTime(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaDetailGetMenuTimestamp(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaDetailGetRecmdLv(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaDetailGetMenuType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 35: // '#'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaDetailGetSubsStat(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 36: // '$'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaDetailGetAcptStat(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 37: // '%'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaDetailGetActiveStat(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 38: // '&'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetTel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 39: // '\''
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetEmail(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 40: // '('
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetZip(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 41: // ')'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetAddr(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 42: // '*'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetField(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 43: // '+'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetLogoUrl(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 44: // ','
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaDetailGetQrCode(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 45: // '-'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaMenuGetCmdId(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 46: // '.'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMenuGetCmdType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 47: // '/'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaMenuGetTitle(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 48: // '0'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMenuGetPriority(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 49: // '1'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMenuGetSubmenuSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 50: // '2'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMenuGetSubmenuId(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 51: // '3'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMsgGetMediaType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 52: // '4'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaMsgGetMsgUuid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 53: // '5'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaMsgGetPaUuid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 54: // '6'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaMsgGetSmsDigest(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 55: // '7'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaMsgGetText(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 56: // '8'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMsgGetCreateTime(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 57: // '9'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMsgGetActiveStat(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 58: // ':'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMsgGetFwdable(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 59: // ';'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMsgGetGInfoId(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 60: // '<'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMsgGetArtMsgSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 61: // '='
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaMsgGetArtMsgId(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 62: // '>'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaArtMsgGetTitle(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 63: // '?'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaArtMsgGetAuthor(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 64: // '@'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaArtMsgGetThumbLink(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 65: // 'A'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaArtMsgGetOrigLink(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 66: // 'B'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaArtMsgGetSrcLink(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 67: // 'C'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaArtMsgGetMainText(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaArtMsgGetMediaUuid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 69: // 'E'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaBscMsgGetTitle(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 70: // 'F'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaBscMsgGetFileSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 71: // 'G'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaBscMsgGetFileType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 72: // 'H'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaBscMsgGetFileDuration(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 73: // 'I'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaBscMsgGetThumbLink(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 74: // 'J'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaBscMsgGetOrigLink(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 75: // 'K'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                parcel = Mtc_PaBscMsgGetMediaUuid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 76: // 'L'
                parcel.enforceInterface("com.juphoon.service.pa.IRcsPaService");
                i = Mtc_PaBscMsgGetCreateTime(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.juphoon.service.pa.IRcsPaService";
        static final int TRANSACTION_Mtc_PaArtMsgGetAuthor = 63;
        static final int TRANSACTION_Mtc_PaArtMsgGetMainText = 67;
        static final int TRANSACTION_Mtc_PaArtMsgGetMediaUuid = 68;
        static final int TRANSACTION_Mtc_PaArtMsgGetOrigLink = 65;
        static final int TRANSACTION_Mtc_PaArtMsgGetSrcLink = 66;
        static final int TRANSACTION_Mtc_PaArtMsgGetThumbLink = 64;
        static final int TRANSACTION_Mtc_PaArtMsgGetTitle = 62;
        static final int TRANSACTION_Mtc_PaBscGetLogoUrl = 25;
        static final int TRANSACTION_Mtc_PaBscGetName = 23;
        static final int TRANSACTION_Mtc_PaBscGetPaUuid = 22;
        static final int TRANSACTION_Mtc_PaBscGetRecmdLv = 24;
        static final int TRANSACTION_Mtc_PaBscMsgGetCreateTime = 76;
        static final int TRANSACTION_Mtc_PaBscMsgGetFileDuration = 72;
        static final int TRANSACTION_Mtc_PaBscMsgGetFileSize = 70;
        static final int TRANSACTION_Mtc_PaBscMsgGetFileType = 71;
        static final int TRANSACTION_Mtc_PaBscMsgGetMediaUuid = 75;
        static final int TRANSACTION_Mtc_PaBscMsgGetOrigLink = 74;
        static final int TRANSACTION_Mtc_PaBscMsgGetThumbLink = 73;
        static final int TRANSACTION_Mtc_PaBscMsgGetTitle = 69;
        static final int TRANSACTION_Mtc_PaDetailGetAcptStat = 36;
        static final int TRANSACTION_Mtc_PaDetailGetActiveStat = 37;
        static final int TRANSACTION_Mtc_PaDetailGetAddr = 41;
        static final int TRANSACTION_Mtc_PaDetailGetCompany = 28;
        static final int TRANSACTION_Mtc_PaDetailGetEmail = 39;
        static final int TRANSACTION_Mtc_PaDetailGetField = 42;
        static final int TRANSACTION_Mtc_PaDetailGetIntro = 29;
        static final int TRANSACTION_Mtc_PaDetailGetLogoUrl = 43;
        static final int TRANSACTION_Mtc_PaDetailGetMenuTimestamp = 31;
        static final int TRANSACTION_Mtc_PaDetailGetMenuType = 34;
        static final int TRANSACTION_Mtc_PaDetailGetName = 27;
        static final int TRANSACTION_Mtc_PaDetailGetPaUuid = 26;
        static final int TRANSACTION_Mtc_PaDetailGetQrCode = 44;
        static final int TRANSACTION_Mtc_PaDetailGetRecmdLv = 33;
        static final int TRANSACTION_Mtc_PaDetailGetSubsStat = 35;
        static final int TRANSACTION_Mtc_PaDetailGetTel = 38;
        static final int TRANSACTION_Mtc_PaDetailGetType = 32;
        static final int TRANSACTION_Mtc_PaDetailGetUpdTime = 30;
        static final int TRANSACTION_Mtc_PaDetailGetZip = 40;
        static final int TRANSACTION_Mtc_PaMenuGetCmdId = 45;
        static final int TRANSACTION_Mtc_PaMenuGetCmdType = 46;
        static final int TRANSACTION_Mtc_PaMenuGetPriority = 48;
        static final int TRANSACTION_Mtc_PaMenuGetSubmenuId = 50;
        static final int TRANSACTION_Mtc_PaMenuGetSubmenuSize = 49;
        static final int TRANSACTION_Mtc_PaMenuGetTitle = 47;
        static final int TRANSACTION_Mtc_PaMsgGetActiveStat = 57;
        static final int TRANSACTION_Mtc_PaMsgGetArtMsgId = 61;
        static final int TRANSACTION_Mtc_PaMsgGetArtMsgSize = 60;
        static final int TRANSACTION_Mtc_PaMsgGetCreateTime = 56;
        static final int TRANSACTION_Mtc_PaMsgGetFwdable = 58;
        static final int TRANSACTION_Mtc_PaMsgGetGInfoId = 59;
        static final int TRANSACTION_Mtc_PaMsgGetMediaType = 51;
        static final int TRANSACTION_Mtc_PaMsgGetMsgUuid = 52;
        static final int TRANSACTION_Mtc_PaMsgGetPaUuid = 53;
        static final int TRANSACTION_Mtc_PaMsgGetSmsDigest = 54;
        static final int TRANSACTION_Mtc_PaMsgGetText = 55;
        static final int TRANSACTION_Mtc_PaSessAddSubs = 1;
        static final int TRANSACTION_Mtc_PaSessCancelSubs = 2;
        static final int TRANSACTION_Mtc_PaSessComplainPub = 10;
        static final int TRANSACTION_Mtc_PaSessDecodeMsg = 13;
        static final int TRANSACTION_Mtc_PaSessEncodeMsg = 12;
        static final int TRANSACTION_Mtc_PaSessGetMenuId = 18;
        static final int TRANSACTION_Mtc_PaSessGetMenuSize = 17;
        static final int TRANSACTION_Mtc_PaSessGetMsgId = 20;
        static final int TRANSACTION_Mtc_PaSessGetMsgSize = 19;
        static final int TRANSACTION_Mtc_PaSessGetPaId = 16;
        static final int TRANSACTION_Mtc_PaSessGetPaSize = 15;
        static final int TRANSACTION_Mtc_PaSessGetPrevMsg = 9;
        static final int TRANSACTION_Mtc_PaSessGetPubDetail = 7;
        static final int TRANSACTION_Mtc_PaSessGetPubLst = 5;
        static final int TRANSACTION_Mtc_PaSessGetPubLstRecmd = 6;
        static final int TRANSACTION_Mtc_PaSessGetPubMenu = 8;
        static final int TRANSACTION_Mtc_PaSessGetResult = 21;
        static final int TRANSACTION_Mtc_PaSessQryAllUsrSubs = 4;
        static final int TRANSACTION_Mtc_PaSessQryUsrSubs = 3;
        static final int TRANSACTION_Mtc_PaSessRelease = 14;
        static final int TRANSACTION_Mtc_PaSessSetAcptStat = 11;

        public Stub()
        {
            attachInterface(this, "com.juphoon.service.pa.IRcsPaService");
        }
    }

    private static class Stub.Proxy
        implements IRcsPaService
    {

        public String Mtc_PaArtMsgGetAuthor(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(63, parcel, parcel1, 0);
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

        public String Mtc_PaArtMsgGetMainText(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(67, parcel, parcel1, 0);
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

        public String Mtc_PaArtMsgGetMediaUuid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(68, parcel, parcel1, 0);
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

        public String Mtc_PaArtMsgGetOrigLink(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(65, parcel, parcel1, 0);
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

        public String Mtc_PaArtMsgGetSrcLink(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(66, parcel, parcel1, 0);
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

        public String Mtc_PaArtMsgGetThumbLink(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(64, parcel, parcel1, 0);
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

        public String Mtc_PaArtMsgGetTitle(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(62, parcel, parcel1, 0);
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

        public String Mtc_PaBscGetLogoUrl(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
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

        public String Mtc_PaBscGetName(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
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

        public String Mtc_PaBscGetPaUuid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
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

        public int Mtc_PaBscGetRecmdLv(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
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

        public int Mtc_PaBscMsgGetCreateTime(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(76, parcel, parcel1, 0);
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

        public int Mtc_PaBscMsgGetFileDuration(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(72, parcel, parcel1, 0);
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

        public int Mtc_PaBscMsgGetFileSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(70, parcel, parcel1, 0);
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

        public String Mtc_PaBscMsgGetFileType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(71, parcel, parcel1, 0);
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

        public String Mtc_PaBscMsgGetMediaUuid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(75, parcel, parcel1, 0);
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

        public String Mtc_PaBscMsgGetOrigLink(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(74, parcel, parcel1, 0);
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

        public String Mtc_PaBscMsgGetThumbLink(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(73, parcel, parcel1, 0);
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

        public String Mtc_PaBscMsgGetTitle(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(69, parcel, parcel1, 0);
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

        public int Mtc_PaDetailGetAcptStat(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(36, parcel, parcel1, 0);
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

        public int Mtc_PaDetailGetActiveStat(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(37, parcel, parcel1, 0);
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

        public String Mtc_PaDetailGetAddr(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(41, parcel, parcel1, 0);
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

        public String Mtc_PaDetailGetCompany(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
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

        public String Mtc_PaDetailGetEmail(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
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

        public String Mtc_PaDetailGetField(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
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

        public String Mtc_PaDetailGetIntro(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
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

        public String Mtc_PaDetailGetLogoUrl(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(43, parcel, parcel1, 0);
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

        public int Mtc_PaDetailGetMenuTimestamp(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
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

        public int Mtc_PaDetailGetMenuType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(34, parcel, parcel1, 0);
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

        public String Mtc_PaDetailGetName(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
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

        public String Mtc_PaDetailGetPaUuid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(26, parcel, parcel1, 0);
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

        public String Mtc_PaDetailGetQrCode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(44, parcel, parcel1, 0);
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

        public int Mtc_PaDetailGetRecmdLv(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(33, parcel, parcel1, 0);
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

        public int Mtc_PaDetailGetSubsStat(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(35, parcel, parcel1, 0);
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

        public String Mtc_PaDetailGetTel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(38, parcel, parcel1, 0);
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

        public String Mtc_PaDetailGetType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(32, parcel, parcel1, 0);
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

        public int Mtc_PaDetailGetUpdTime(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(30, parcel, parcel1, 0);
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

        public String Mtc_PaDetailGetZip(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(40, parcel, parcel1, 0);
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

        public String Mtc_PaMenuGetCmdId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(45, parcel, parcel1, 0);
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

        public int Mtc_PaMenuGetCmdType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
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

        public int Mtc_PaMenuGetPriority(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
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

        public int Mtc_PaMenuGetSubmenuId(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(50, parcel, parcel1, 0);
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

        public int Mtc_PaMenuGetSubmenuSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
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

        public String Mtc_PaMenuGetTitle(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(47, parcel, parcel1, 0);
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

        public int Mtc_PaMsgGetActiveStat(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(57, parcel, parcel1, 0);
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

        public int Mtc_PaMsgGetArtMsgId(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(61, parcel, parcel1, 0);
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

        public int Mtc_PaMsgGetArtMsgSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(60, parcel, parcel1, 0);
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

        public int Mtc_PaMsgGetCreateTime(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(56, parcel, parcel1, 0);
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

        public int Mtc_PaMsgGetFwdable(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(58, parcel, parcel1, 0);
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

        public int Mtc_PaMsgGetGInfoId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(59, parcel, parcel1, 0);
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

        public int Mtc_PaMsgGetMediaType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(51, parcel, parcel1, 0);
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

        public String Mtc_PaMsgGetMsgUuid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(52, parcel, parcel1, 0);
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

        public String Mtc_PaMsgGetPaUuid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(53, parcel, parcel1, 0);
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

        public String Mtc_PaMsgGetSmsDigest(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(54, parcel, parcel1, 0);
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

        public String Mtc_PaMsgGetText(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(55, parcel, parcel1, 0);
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

        public int Mtc_PaSessAddSubs(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
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

        public int Mtc_PaSessCancelSubs(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public int Mtc_PaSessComplainPub(String s, int i, String s1, String s2, String s3)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public int Mtc_PaSessDecodeMsg(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeString(s);
            mRemote.transact(13, parcel, parcel1, 0);
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

        public String Mtc_PaSessEncodeMsg(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
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

        public int Mtc_PaSessGetMenuId(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(18, parcel, parcel1, 0);
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

        public int Mtc_PaSessGetMenuSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
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

        public int Mtc_PaSessGetMsgId(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public int Mtc_PaSessGetMsgSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
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

        public int Mtc_PaSessGetPaId(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public int Mtc_PaSessGetPaSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
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

        public int Mtc_PaSessGetPrevMsg(String s, int i, int j, int k, int l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public int Mtc_PaSessGetPubDetail(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public int Mtc_PaSessGetPubLst(String s, int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public int Mtc_PaSessGetPubLstRecmd(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(6, parcel, parcel1, 0);
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

        public int Mtc_PaSessGetPubMenu(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public String Mtc_PaSessGetResult(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
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

        public void Mtc_PaSessQryAllUsrSubs()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
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

        public int Mtc_PaSessQryUsrSubs(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
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

        public void Mtc_PaSessRelease(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
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

        public int Mtc_PaSessSetAcptStat(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.pa.IRcsPaService");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
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
            return "com.juphoon.service.pa.IRcsPaService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract String Mtc_PaArtMsgGetAuthor(int i)
        throws RemoteException;

    public abstract String Mtc_PaArtMsgGetMainText(int i)
        throws RemoteException;

    public abstract String Mtc_PaArtMsgGetMediaUuid(int i)
        throws RemoteException;

    public abstract String Mtc_PaArtMsgGetOrigLink(int i)
        throws RemoteException;

    public abstract String Mtc_PaArtMsgGetSrcLink(int i)
        throws RemoteException;

    public abstract String Mtc_PaArtMsgGetThumbLink(int i)
        throws RemoteException;

    public abstract String Mtc_PaArtMsgGetTitle(int i)
        throws RemoteException;

    public abstract String Mtc_PaBscGetLogoUrl(int i)
        throws RemoteException;

    public abstract String Mtc_PaBscGetName(int i)
        throws RemoteException;

    public abstract String Mtc_PaBscGetPaUuid(int i)
        throws RemoteException;

    public abstract int Mtc_PaBscGetRecmdLv(int i)
        throws RemoteException;

    public abstract int Mtc_PaBscMsgGetCreateTime(int i)
        throws RemoteException;

    public abstract int Mtc_PaBscMsgGetFileDuration(int i)
        throws RemoteException;

    public abstract int Mtc_PaBscMsgGetFileSize(int i)
        throws RemoteException;

    public abstract String Mtc_PaBscMsgGetFileType(int i)
        throws RemoteException;

    public abstract String Mtc_PaBscMsgGetMediaUuid(int i)
        throws RemoteException;

    public abstract String Mtc_PaBscMsgGetOrigLink(int i)
        throws RemoteException;

    public abstract String Mtc_PaBscMsgGetThumbLink(int i)
        throws RemoteException;

    public abstract String Mtc_PaBscMsgGetTitle(int i)
        throws RemoteException;

    public abstract int Mtc_PaDetailGetAcptStat(int i)
        throws RemoteException;

    public abstract int Mtc_PaDetailGetActiveStat(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetAddr(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetCompany(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetEmail(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetField(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetIntro(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetLogoUrl(int i)
        throws RemoteException;

    public abstract int Mtc_PaDetailGetMenuTimestamp(int i)
        throws RemoteException;

    public abstract int Mtc_PaDetailGetMenuType(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetName(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetPaUuid(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetQrCode(int i)
        throws RemoteException;

    public abstract int Mtc_PaDetailGetRecmdLv(int i)
        throws RemoteException;

    public abstract int Mtc_PaDetailGetSubsStat(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetTel(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetType(int i)
        throws RemoteException;

    public abstract int Mtc_PaDetailGetUpdTime(int i)
        throws RemoteException;

    public abstract String Mtc_PaDetailGetZip(int i)
        throws RemoteException;

    public abstract String Mtc_PaMenuGetCmdId(int i)
        throws RemoteException;

    public abstract int Mtc_PaMenuGetCmdType(int i)
        throws RemoteException;

    public abstract int Mtc_PaMenuGetPriority(int i)
        throws RemoteException;

    public abstract int Mtc_PaMenuGetSubmenuId(int i, int j)
        throws RemoteException;

    public abstract int Mtc_PaMenuGetSubmenuSize(int i)
        throws RemoteException;

    public abstract String Mtc_PaMenuGetTitle(int i)
        throws RemoteException;

    public abstract int Mtc_PaMsgGetActiveStat(int i)
        throws RemoteException;

    public abstract int Mtc_PaMsgGetArtMsgId(int i, int j)
        throws RemoteException;

    public abstract int Mtc_PaMsgGetArtMsgSize(int i)
        throws RemoteException;

    public abstract int Mtc_PaMsgGetCreateTime(int i)
        throws RemoteException;

    public abstract int Mtc_PaMsgGetFwdable(int i)
        throws RemoteException;

    public abstract int Mtc_PaMsgGetGInfoId(int i)
        throws RemoteException;

    public abstract int Mtc_PaMsgGetMediaType(int i)
        throws RemoteException;

    public abstract String Mtc_PaMsgGetMsgUuid(int i)
        throws RemoteException;

    public abstract String Mtc_PaMsgGetPaUuid(int i)
        throws RemoteException;

    public abstract String Mtc_PaMsgGetSmsDigest(int i)
        throws RemoteException;

    public abstract String Mtc_PaMsgGetText(int i)
        throws RemoteException;

    public abstract int Mtc_PaSessAddSubs(String s)
        throws RemoteException;

    public abstract int Mtc_PaSessCancelSubs(String s)
        throws RemoteException;

    public abstract int Mtc_PaSessComplainPub(String s, int i, String s1, String s2, String s3)
        throws RemoteException;

    public abstract int Mtc_PaSessDecodeMsg(String s)
        throws RemoteException;

    public abstract String Mtc_PaSessEncodeMsg(String s)
        throws RemoteException;

    public abstract int Mtc_PaSessGetMenuId(int i, int j)
        throws RemoteException;

    public abstract int Mtc_PaSessGetMenuSize(int i)
        throws RemoteException;

    public abstract int Mtc_PaSessGetMsgId(int i, int j)
        throws RemoteException;

    public abstract int Mtc_PaSessGetMsgSize(int i)
        throws RemoteException;

    public abstract int Mtc_PaSessGetPaId(int i, int j)
        throws RemoteException;

    public abstract int Mtc_PaSessGetPaSize(int i)
        throws RemoteException;

    public abstract int Mtc_PaSessGetPrevMsg(String s, int i, int j, int k, int l)
        throws RemoteException;

    public abstract int Mtc_PaSessGetPubDetail(String s, int i)
        throws RemoteException;

    public abstract int Mtc_PaSessGetPubLst(String s, int i, int j, int k)
        throws RemoteException;

    public abstract int Mtc_PaSessGetPubLstRecmd(int i, int j, int k)
        throws RemoteException;

    public abstract int Mtc_PaSessGetPubMenu(String s, int i)
        throws RemoteException;

    public abstract String Mtc_PaSessGetResult(int i)
        throws RemoteException;

    public abstract void Mtc_PaSessQryAllUsrSubs()
        throws RemoteException;

    public abstract int Mtc_PaSessQryUsrSubs(int i, int j, int k)
        throws RemoteException;

    public abstract void Mtc_PaSessRelease(int i)
        throws RemoteException;

    public abstract int Mtc_PaSessSetAcptStat(String s, boolean flag)
        throws RemoteException;
}
