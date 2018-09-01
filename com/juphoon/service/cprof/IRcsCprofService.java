// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.juphoon.service.cprof;

import android.os.*;

public interface IRcsCprofService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IRcsCprofService
    {

        public static IRcsCprofService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.juphoon.service.cprof.IRcsCprofService");
            if(iinterface != null && (iinterface instanceof IRcsCprofService))
                return (IRcsCprofService)iinterface;
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
                parcel1.writeString("com.juphoon.service.cprof.IRcsCprofService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccLoadAll();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUploadAll();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccLoadName();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUploadName();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccLoadAddr();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUploadAddr();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccLoadCommAddr();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUploadCommAddr();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccLoadBirth();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUploadBirth();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccLoadCareer();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUploadCareer();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccLoadIcon(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUploadIcon();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardLoadAll();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardLoadFlag();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardUploadFlag();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardLoadPcc(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardLoadPccIcon(parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccGetPccType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccSetPccType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccGetNameSize();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccGetNameId(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccAddName();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccRmvName(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccNameGetGiven(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccNameSetGiven(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccNameGetMiddle(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccNameSetMiddle(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccNameGetFamily(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccNameSetFamily(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccNameGetDispName(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccNameSetDispName(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccNameGetFirst(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 35: // '#'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccNameSetFirst(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 36: // '$'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccNameGetType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 37: // '%'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccNameSetType(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 38: // '&'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccNameGetPref(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 39: // '\''
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccNameSetPref(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 40: // '('
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccGetAddrSize();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 41: // ')'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccGetAddrId(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 42: // '*'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccAddAddr();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 43: // '+'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccRmvAddr(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 44: // ','
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccAddrGetLabel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 45: // '-'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccAddrSetLabel(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 46: // '.'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccAddrGetAddr(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 47: // '/'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccAddrSetAddr(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 48: // '0'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccAddrGetType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 49: // '1'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccAddrSetType(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 50: // '2'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccAddrGetPref(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 51: // '3'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccAddrSetPref(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 52: // '4'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccCommAddrGetUriSize();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 53: // '5'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccCommAddrGetUriId(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 54: // '6'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccCommAddrAddUri();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 55: // '7'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccCommAddrRmvUri(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 56: // '8'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccUriGetLabel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 57: // '9'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUriSetLabel(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 58: // ':'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccUriGetUri(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 59: // ';'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUriSetUri(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 60: // '<'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUriGetType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 61: // '='
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUriSetType(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 62: // '>'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUriGetPref(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 63: // '?'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccUriSetPref(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 64: // '@'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccCommAddrGetTelSize();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 65: // 'A'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccCommAddrGetTelId(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 66: // 'B'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccCommAddrAddTel();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 67: // 'C'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccCommAddrRmvTel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccTelGetLabel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 69: // 'E'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccTelSetLabel(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 70: // 'F'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccTelGetTel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 71: // 'G'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccTelSetTel(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 72: // 'H'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccTelGetTelType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 73: // 'I'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccTelSetTelType(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 74: // 'J'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccTelGetXuiType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 75: // 'K'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccTelSetXuiType(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 76: // 'L'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccTelGetPref(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 77: // 'M'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccTelSetPref(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 78: // 'N'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccBirthGetDate();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 79: // 'O'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccBirthSetDate(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 80: // 'P'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccBirthGetNonGregDate();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 81: // 'Q'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccBirthSetNonGregDate(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 82: // 'R'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccBirthGetPlace();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 83: // 'S'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccBirthSetPlace(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 84: // 'T'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccBirthGetCalType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 85: // 'U'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccBirthSetCalType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 86: // 'V'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccCareerGetEmployer();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 87: // 'W'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccCareerSetEmployer(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 88: // 'X'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccCareerGetDuty();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 89: // 'Y'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccCareerSetDuty(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 90: // 'Z'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccIconGetMimeType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 91: // '['
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccIconSetMimeType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 92: // '\\'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccIconGetEncodingType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 93: // ']'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccIconSetEncodingType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 94: // '^'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccIconGetDesc();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 95: // '_'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccIconSetDesc(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 96: // '`'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofPccIconGetData();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 97: // 'a'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccIconSetData(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 98: // 'b'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccIconExportFile(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 99: // 'c'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofPccIconImportFile(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 100: // 'd'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                boolean flag = Mtc_CprofQrCardGetBusinessFlag();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 101: // 'e'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                i = Mtc_CprofQrCardSetBusinessFlag(flag1);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 102: // 'f'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardCttGetMimeType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 103: // 'g'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardCttGetEncodingType();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 104: // 'h'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardCttGetDesc();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 105: // 'i'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardCttGetData();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 106: // 'j'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardCttExportFile(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 107: // 'k'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccGetUrl(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 108: // 'l'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                boolean flag2 = Mtc_CprofQrCardPccHasVcard(parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 109: // 'm'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccGetVcard(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 110: // 'n'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccGetPccType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 111: // 'o'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccGetNameSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 112: // 'p'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccGetNameId(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 113: // 'q'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccNameGetGiven(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 114: // 'r'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccNameGetMiddle(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 115: // 's'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccNameGetFamily(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 116: // 't'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccNameGetDispName(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 117: // 'u'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccNameGetFirst(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 118: // 'v'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccNameGetType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 119: // 'w'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccNameGetPref(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 120: // 'x'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccGetAddrSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 121: // 'y'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccGetAddrId(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 122: // 'z'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccAddrGetLabel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 123: // '{'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccAddrGetAddr(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 124: // '|'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccAddrGetType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 125: // '}'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccAddrGetPref(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 126: // '~'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccGetMediaSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 127: // '\177'
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccGetMediaId(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 128: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccMediaGetLabel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 129: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccMediaGetMediaUrl(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 130: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccMediaGetType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 131: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccMediaGetPref(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 132: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccCommAddrGetUriSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 133: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccCommAddrGetUriId(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 134: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccUriGetLabel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 135: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccUriGetUri(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 136: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccUriGetType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 137: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccUriGetPref(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 138: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccCommAddrGetTelSize(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 139: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccCommAddrGetTelId(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 140: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccTelGetLabel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 141: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccTelGetTel(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 142: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccTelGetTelType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 143: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccTelGetXuiType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 144: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccTelGetPref(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 145: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccBirthGetDate(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 146: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccBirthGetNonGregDate(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 147: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccBirthGetPlace(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 148: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccBirthGetCalType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 149: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccCareerGetEmployer(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 150: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccCareerGetDuty(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 151: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccIconGetUri(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 152: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccIconGetMimeType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 153: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccIconGetEncodingType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 154: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccIconGetDesc(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 155: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                parcel = Mtc_CprofQrCardPccIconGetData(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 156: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                boolean flag3 = Mtc_CprofQrCardPccIconHasData(parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 157: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccIconClrData(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 158: 
                parcel.enforceInterface("com.juphoon.service.cprof.IRcsCprofService");
                i = Mtc_CprofQrCardPccIconExportFile(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "com.juphoon.service.cprof.IRcsCprofService";
        static final int TRANSACTION_Mtc_CprofPccAddAddr = 42;
        static final int TRANSACTION_Mtc_CprofPccAddName = 24;
        static final int TRANSACTION_Mtc_CprofPccAddrGetAddr = 46;
        static final int TRANSACTION_Mtc_CprofPccAddrGetLabel = 44;
        static final int TRANSACTION_Mtc_CprofPccAddrGetPref = 50;
        static final int TRANSACTION_Mtc_CprofPccAddrGetType = 48;
        static final int TRANSACTION_Mtc_CprofPccAddrSetAddr = 47;
        static final int TRANSACTION_Mtc_CprofPccAddrSetLabel = 45;
        static final int TRANSACTION_Mtc_CprofPccAddrSetPref = 51;
        static final int TRANSACTION_Mtc_CprofPccAddrSetType = 49;
        static final int TRANSACTION_Mtc_CprofPccBirthGetCalType = 84;
        static final int TRANSACTION_Mtc_CprofPccBirthGetDate = 78;
        static final int TRANSACTION_Mtc_CprofPccBirthGetNonGregDate = 80;
        static final int TRANSACTION_Mtc_CprofPccBirthGetPlace = 82;
        static final int TRANSACTION_Mtc_CprofPccBirthSetCalType = 85;
        static final int TRANSACTION_Mtc_CprofPccBirthSetDate = 79;
        static final int TRANSACTION_Mtc_CprofPccBirthSetNonGregDate = 81;
        static final int TRANSACTION_Mtc_CprofPccBirthSetPlace = 83;
        static final int TRANSACTION_Mtc_CprofPccCareerGetDuty = 88;
        static final int TRANSACTION_Mtc_CprofPccCareerGetEmployer = 86;
        static final int TRANSACTION_Mtc_CprofPccCareerSetDuty = 89;
        static final int TRANSACTION_Mtc_CprofPccCareerSetEmployer = 87;
        static final int TRANSACTION_Mtc_CprofPccCommAddrAddTel = 66;
        static final int TRANSACTION_Mtc_CprofPccCommAddrAddUri = 54;
        static final int TRANSACTION_Mtc_CprofPccCommAddrGetTelId = 65;
        static final int TRANSACTION_Mtc_CprofPccCommAddrGetTelSize = 64;
        static final int TRANSACTION_Mtc_CprofPccCommAddrGetUriId = 53;
        static final int TRANSACTION_Mtc_CprofPccCommAddrGetUriSize = 52;
        static final int TRANSACTION_Mtc_CprofPccCommAddrRmvTel = 67;
        static final int TRANSACTION_Mtc_CprofPccCommAddrRmvUri = 55;
        static final int TRANSACTION_Mtc_CprofPccGetAddrId = 41;
        static final int TRANSACTION_Mtc_CprofPccGetAddrSize = 40;
        static final int TRANSACTION_Mtc_CprofPccGetNameId = 23;
        static final int TRANSACTION_Mtc_CprofPccGetNameSize = 22;
        static final int TRANSACTION_Mtc_CprofPccGetPccType = 20;
        static final int TRANSACTION_Mtc_CprofPccIconExportFile = 98;
        static final int TRANSACTION_Mtc_CprofPccIconGetData = 96;
        static final int TRANSACTION_Mtc_CprofPccIconGetDesc = 94;
        static final int TRANSACTION_Mtc_CprofPccIconGetEncodingType = 92;
        static final int TRANSACTION_Mtc_CprofPccIconGetMimeType = 90;
        static final int TRANSACTION_Mtc_CprofPccIconImportFile = 99;
        static final int TRANSACTION_Mtc_CprofPccIconSetData = 97;
        static final int TRANSACTION_Mtc_CprofPccIconSetDesc = 95;
        static final int TRANSACTION_Mtc_CprofPccIconSetEncodingType = 93;
        static final int TRANSACTION_Mtc_CprofPccIconSetMimeType = 91;
        static final int TRANSACTION_Mtc_CprofPccLoadAddr = 5;
        static final int TRANSACTION_Mtc_CprofPccLoadAll = 1;
        static final int TRANSACTION_Mtc_CprofPccLoadBirth = 9;
        static final int TRANSACTION_Mtc_CprofPccLoadCareer = 11;
        static final int TRANSACTION_Mtc_CprofPccLoadCommAddr = 7;
        static final int TRANSACTION_Mtc_CprofPccLoadIcon = 13;
        static final int TRANSACTION_Mtc_CprofPccLoadName = 3;
        static final int TRANSACTION_Mtc_CprofPccNameGetDispName = 32;
        static final int TRANSACTION_Mtc_CprofPccNameGetFamily = 30;
        static final int TRANSACTION_Mtc_CprofPccNameGetFirst = 34;
        static final int TRANSACTION_Mtc_CprofPccNameGetGiven = 26;
        static final int TRANSACTION_Mtc_CprofPccNameGetMiddle = 28;
        static final int TRANSACTION_Mtc_CprofPccNameGetPref = 38;
        static final int TRANSACTION_Mtc_CprofPccNameGetType = 36;
        static final int TRANSACTION_Mtc_CprofPccNameSetDispName = 33;
        static final int TRANSACTION_Mtc_CprofPccNameSetFamily = 31;
        static final int TRANSACTION_Mtc_CprofPccNameSetFirst = 35;
        static final int TRANSACTION_Mtc_CprofPccNameSetGiven = 27;
        static final int TRANSACTION_Mtc_CprofPccNameSetMiddle = 29;
        static final int TRANSACTION_Mtc_CprofPccNameSetPref = 39;
        static final int TRANSACTION_Mtc_CprofPccNameSetType = 37;
        static final int TRANSACTION_Mtc_CprofPccRmvAddr = 43;
        static final int TRANSACTION_Mtc_CprofPccRmvName = 25;
        static final int TRANSACTION_Mtc_CprofPccSetPccType = 21;
        static final int TRANSACTION_Mtc_CprofPccTelGetLabel = 68;
        static final int TRANSACTION_Mtc_CprofPccTelGetPref = 76;
        static final int TRANSACTION_Mtc_CprofPccTelGetTel = 70;
        static final int TRANSACTION_Mtc_CprofPccTelGetTelType = 72;
        static final int TRANSACTION_Mtc_CprofPccTelGetXuiType = 74;
        static final int TRANSACTION_Mtc_CprofPccTelSetLabel = 69;
        static final int TRANSACTION_Mtc_CprofPccTelSetPref = 77;
        static final int TRANSACTION_Mtc_CprofPccTelSetTel = 71;
        static final int TRANSACTION_Mtc_CprofPccTelSetTelType = 73;
        static final int TRANSACTION_Mtc_CprofPccTelSetXuiType = 75;
        static final int TRANSACTION_Mtc_CprofPccUploadAddr = 6;
        static final int TRANSACTION_Mtc_CprofPccUploadAll = 2;
        static final int TRANSACTION_Mtc_CprofPccUploadBirth = 10;
        static final int TRANSACTION_Mtc_CprofPccUploadCareer = 12;
        static final int TRANSACTION_Mtc_CprofPccUploadCommAddr = 8;
        static final int TRANSACTION_Mtc_CprofPccUploadIcon = 14;
        static final int TRANSACTION_Mtc_CprofPccUploadName = 4;
        static final int TRANSACTION_Mtc_CprofPccUriGetLabel = 56;
        static final int TRANSACTION_Mtc_CprofPccUriGetPref = 62;
        static final int TRANSACTION_Mtc_CprofPccUriGetType = 60;
        static final int TRANSACTION_Mtc_CprofPccUriGetUri = 58;
        static final int TRANSACTION_Mtc_CprofPccUriSetLabel = 57;
        static final int TRANSACTION_Mtc_CprofPccUriSetPref = 63;
        static final int TRANSACTION_Mtc_CprofPccUriSetType = 61;
        static final int TRANSACTION_Mtc_CprofPccUriSetUri = 59;
        static final int TRANSACTION_Mtc_CprofQrCardCttExportFile = 106;
        static final int TRANSACTION_Mtc_CprofQrCardCttGetData = 105;
        static final int TRANSACTION_Mtc_CprofQrCardCttGetDesc = 104;
        static final int TRANSACTION_Mtc_CprofQrCardCttGetEncodingType = 103;
        static final int TRANSACTION_Mtc_CprofQrCardCttGetMimeType = 102;
        static final int TRANSACTION_Mtc_CprofQrCardGetBusinessFlag = 100;
        static final int TRANSACTION_Mtc_CprofQrCardLoadAll = 15;
        static final int TRANSACTION_Mtc_CprofQrCardLoadFlag = 16;
        static final int TRANSACTION_Mtc_CprofQrCardLoadPcc = 18;
        static final int TRANSACTION_Mtc_CprofQrCardLoadPccIcon = 19;
        static final int TRANSACTION_Mtc_CprofQrCardPccAddrGetAddr = 123;
        static final int TRANSACTION_Mtc_CprofQrCardPccAddrGetLabel = 122;
        static final int TRANSACTION_Mtc_CprofQrCardPccAddrGetPref = 125;
        static final int TRANSACTION_Mtc_CprofQrCardPccAddrGetType = 124;
        static final int TRANSACTION_Mtc_CprofQrCardPccBirthGetCalType = 148;
        static final int TRANSACTION_Mtc_CprofQrCardPccBirthGetDate = 145;
        static final int TRANSACTION_Mtc_CprofQrCardPccBirthGetNonGregDate = 146;
        static final int TRANSACTION_Mtc_CprofQrCardPccBirthGetPlace = 147;
        static final int TRANSACTION_Mtc_CprofQrCardPccCareerGetDuty = 150;
        static final int TRANSACTION_Mtc_CprofQrCardPccCareerGetEmployer = 149;
        static final int TRANSACTION_Mtc_CprofQrCardPccCommAddrGetTelId = 139;
        static final int TRANSACTION_Mtc_CprofQrCardPccCommAddrGetTelSize = 138;
        static final int TRANSACTION_Mtc_CprofQrCardPccCommAddrGetUriId = 133;
        static final int TRANSACTION_Mtc_CprofQrCardPccCommAddrGetUriSize = 132;
        static final int TRANSACTION_Mtc_CprofQrCardPccGetAddrId = 121;
        static final int TRANSACTION_Mtc_CprofQrCardPccGetAddrSize = 120;
        static final int TRANSACTION_Mtc_CprofQrCardPccGetMediaId = 127;
        static final int TRANSACTION_Mtc_CprofQrCardPccGetMediaSize = 126;
        static final int TRANSACTION_Mtc_CprofQrCardPccGetNameId = 112;
        static final int TRANSACTION_Mtc_CprofQrCardPccGetNameSize = 111;
        static final int TRANSACTION_Mtc_CprofQrCardPccGetPccType = 110;
        static final int TRANSACTION_Mtc_CprofQrCardPccGetUrl = 107;
        static final int TRANSACTION_Mtc_CprofQrCardPccGetVcard = 109;
        static final int TRANSACTION_Mtc_CprofQrCardPccHasVcard = 108;
        static final int TRANSACTION_Mtc_CprofQrCardPccIconClrData = 157;
        static final int TRANSACTION_Mtc_CprofQrCardPccIconExportFile = 158;
        static final int TRANSACTION_Mtc_CprofQrCardPccIconGetData = 155;
        static final int TRANSACTION_Mtc_CprofQrCardPccIconGetDesc = 154;
        static final int TRANSACTION_Mtc_CprofQrCardPccIconGetEncodingType = 153;
        static final int TRANSACTION_Mtc_CprofQrCardPccIconGetMimeType = 152;
        static final int TRANSACTION_Mtc_CprofQrCardPccIconGetUri = 151;
        static final int TRANSACTION_Mtc_CprofQrCardPccIconHasData = 156;
        static final int TRANSACTION_Mtc_CprofQrCardPccMediaGetLabel = 128;
        static final int TRANSACTION_Mtc_CprofQrCardPccMediaGetMediaUrl = 129;
        static final int TRANSACTION_Mtc_CprofQrCardPccMediaGetPref = 131;
        static final int TRANSACTION_Mtc_CprofQrCardPccMediaGetType = 130;
        static final int TRANSACTION_Mtc_CprofQrCardPccNameGetDispName = 116;
        static final int TRANSACTION_Mtc_CprofQrCardPccNameGetFamily = 115;
        static final int TRANSACTION_Mtc_CprofQrCardPccNameGetFirst = 117;
        static final int TRANSACTION_Mtc_CprofQrCardPccNameGetGiven = 113;
        static final int TRANSACTION_Mtc_CprofQrCardPccNameGetMiddle = 114;
        static final int TRANSACTION_Mtc_CprofQrCardPccNameGetPref = 119;
        static final int TRANSACTION_Mtc_CprofQrCardPccNameGetType = 118;
        static final int TRANSACTION_Mtc_CprofQrCardPccTelGetLabel = 140;
        static final int TRANSACTION_Mtc_CprofQrCardPccTelGetPref = 144;
        static final int TRANSACTION_Mtc_CprofQrCardPccTelGetTel = 141;
        static final int TRANSACTION_Mtc_CprofQrCardPccTelGetTelType = 142;
        static final int TRANSACTION_Mtc_CprofQrCardPccTelGetXuiType = 143;
        static final int TRANSACTION_Mtc_CprofQrCardPccUriGetLabel = 134;
        static final int TRANSACTION_Mtc_CprofQrCardPccUriGetPref = 137;
        static final int TRANSACTION_Mtc_CprofQrCardPccUriGetType = 136;
        static final int TRANSACTION_Mtc_CprofQrCardPccUriGetUri = 135;
        static final int TRANSACTION_Mtc_CprofQrCardSetBusinessFlag = 101;
        static final int TRANSACTION_Mtc_CprofQrCardUploadFlag = 17;

        public Stub()
        {
            attachInterface(this, "com.juphoon.service.cprof.IRcsCprofService");
        }
    }

    private static class Stub.Proxy
        implements IRcsCprofService
    {

        public int Mtc_CprofPccAddAddr()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(42, parcel, parcel1, 0);
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

        public int Mtc_CprofPccAddName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public String Mtc_CprofPccAddrGetAddr(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(46, parcel, parcel1, 0);
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

        public String Mtc_CprofPccAddrGetLabel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccAddrGetPref(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
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

        public int Mtc_CprofPccAddrGetType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccAddrSetAddr(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
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

        public int Mtc_CprofPccAddrSetLabel(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
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

        public int Mtc_CprofPccAddrSetPref(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public int Mtc_CprofPccAddrSetType(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public int Mtc_CprofPccBirthGetCalType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public String Mtc_CprofPccBirthGetDate()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(78, parcel, parcel1, 0);
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

        public String Mtc_CprofPccBirthGetNonGregDate()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(80, parcel, parcel1, 0);
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

        public String Mtc_CprofPccBirthGetPlace()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(82, parcel, parcel1, 0);
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

        public int Mtc_CprofPccBirthSetCalType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(85, parcel, parcel1, 0);
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

        public int Mtc_CprofPccBirthSetDate(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeString(s);
            mRemote.transact(79, parcel, parcel1, 0);
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

        public int Mtc_CprofPccBirthSetNonGregDate(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccBirthSetPlace(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public String Mtc_CprofPccCareerGetDuty()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(88, parcel, parcel1, 0);
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

        public String Mtc_CprofPccCareerGetEmployer()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(86, parcel, parcel1, 0);
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

        public int Mtc_CprofPccCareerSetDuty(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccCareerSetEmployer(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccCommAddrAddTel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(66, parcel, parcel1, 0);
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

        public int Mtc_CprofPccCommAddrAddUri()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(54, parcel, parcel1, 0);
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

        public int Mtc_CprofPccCommAddrGetTelId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(65, parcel, parcel1, 0);
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

        public int Mtc_CprofPccCommAddrGetTelSize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(64, parcel, parcel1, 0);
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

        public int Mtc_CprofPccCommAddrGetUriId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(53, parcel, parcel1, 0);
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

        public int Mtc_CprofPccCommAddrGetUriSize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(52, parcel, parcel1, 0);
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

        public int Mtc_CprofPccCommAddrRmvTel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(67, parcel, parcel1, 0);
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

        public int Mtc_CprofPccCommAddrRmvUri(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(55, parcel, parcel1, 0);
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

        public int Mtc_CprofPccGetAddrId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(41, parcel, parcel1, 0);
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

        public int Mtc_CprofPccGetAddrSize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(40, parcel, parcel1, 0);
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

        public int Mtc_CprofPccGetNameId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
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

        public int Mtc_CprofPccGetNameSize()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(22, parcel, parcel1, 0);
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

        public int Mtc_CprofPccGetPccType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccIconExportFile(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeString(s);
            mRemote.transact(98, parcel, parcel1, 0);
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

        public String Mtc_CprofPccIconGetData()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(96, parcel, parcel1, 0);
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

        public String Mtc_CprofPccIconGetDesc()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(94, parcel, parcel1, 0);
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

        public int Mtc_CprofPccIconGetEncodingType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(92, parcel, parcel1, 0);
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

        public int Mtc_CprofPccIconGetMimeType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccIconImportFile(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeString(s);
            mRemote.transact(99, parcel, parcel1, 0);
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

        public int Mtc_CprofPccIconSetData(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeString(s);
            mRemote.transact(97, parcel, parcel1, 0);
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

        public int Mtc_CprofPccIconSetDesc(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccIconSetEncodingType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(93, parcel, parcel1, 0);
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

        public int Mtc_CprofPccIconSetMimeType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(91, parcel, parcel1, 0);
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

        public int Mtc_CprofPccLoadAddr()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public int Mtc_CprofPccLoadAll()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public int Mtc_CprofPccLoadBirth()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(9, parcel, parcel1, 0);
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

        public int Mtc_CprofPccLoadCareer()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(11, parcel, parcel1, 0);
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

        public int Mtc_CprofPccLoadCommAddr()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(7, parcel, parcel1, 0);
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

        public int Mtc_CprofPccLoadIcon(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
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

        public int Mtc_CprofPccLoadName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public String Mtc_CprofPccNameGetDispName(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public String Mtc_CprofPccNameGetFamily(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
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

        public String Mtc_CprofPccNameGetFirst(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(34, parcel, parcel1, 0);
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

        public String Mtc_CprofPccNameGetGiven(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public String Mtc_CprofPccNameGetMiddle(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccNameGetPref(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(38, parcel, parcel1, 0);
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

        public int Mtc_CprofPccNameGetType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccNameSetDispName(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(33, parcel, parcel1, 0);
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

        public int Mtc_CprofPccNameSetFamily(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
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

        public int Mtc_CprofPccNameSetFirst(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(35, parcel, parcel1, 0);
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

        public int Mtc_CprofPccNameSetGiven(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(27, parcel, parcel1, 0);
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

        public int Mtc_CprofPccNameSetMiddle(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
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

        public int Mtc_CprofPccNameSetPref(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(39, parcel, parcel1, 0);
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

        public int Mtc_CprofPccNameSetType(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public int Mtc_CprofPccRmvAddr(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(43, parcel, parcel1, 0);
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

        public int Mtc_CprofPccRmvName(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
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

        public int Mtc_CprofPccSetPccType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
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

        public String Mtc_CprofPccTelGetLabel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccTelGetPref(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public String Mtc_CprofPccTelGetTel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(70, parcel, parcel1, 0);
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

        public int Mtc_CprofPccTelGetTelType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccTelGetXuiType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(74, parcel, parcel1, 0);
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

        public int Mtc_CprofPccTelSetLabel(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(69, parcel, parcel1, 0);
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

        public int Mtc_CprofPccTelSetPref(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(77, parcel, parcel1, 0);
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

        public int Mtc_CprofPccTelSetTel(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(71, parcel, parcel1, 0);
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

        public int Mtc_CprofPccTelSetTelType(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public int Mtc_CprofPccTelSetXuiType(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(75, parcel, parcel1, 0);
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

        public int Mtc_CprofPccUploadAddr()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccUploadAll()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(2, parcel, parcel1, 0);
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

        public int Mtc_CprofPccUploadBirth()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccUploadCareer()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(12, parcel, parcel1, 0);
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

        public int Mtc_CprofPccUploadCommAddr()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(8, parcel, parcel1, 0);
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

        public int Mtc_CprofPccUploadIcon()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(14, parcel, parcel1, 0);
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

        public int Mtc_CprofPccUploadName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(4, parcel, parcel1, 0);
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

        public String Mtc_CprofPccUriGetLabel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(56, parcel, parcel1, 0);
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

        public int Mtc_CprofPccUriGetPref(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
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

        public int Mtc_CprofPccUriGetType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public String Mtc_CprofPccUriGetUri(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(58, parcel, parcel1, 0);
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

        public int Mtc_CprofPccUriSetLabel(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(57, parcel, parcel1, 0);
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

        public int Mtc_CprofPccUriSetPref(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public int Mtc_CprofPccUriSetType(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofPccUriSetUri(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(59, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardCttExportFile(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeString(s);
            mRemote.transact(106, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardCttGetData()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(105, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardCttGetDesc()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(104, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardCttGetEncodingType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofQrCardCttGetMimeType()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public boolean Mtc_CprofQrCardGetBusinessFlag()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            mRemote.transact(100, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardLoadAll()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofQrCardLoadFlag()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofQrCardLoadPcc(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofQrCardLoadPccIcon(String s, int i, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeString(s1);
            mRemote.transact(19, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccAddrGetAddr(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(123, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccAddrGetLabel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(122, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccAddrGetPref(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(125, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccAddrGetType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(124, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccBirthGetCalType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(148, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccBirthGetDate(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(145, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccBirthGetNonGregDate(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(146, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccBirthGetPlace(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(147, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccCareerGetDuty(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(150, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccCareerGetEmployer(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(149, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccCommAddrGetTelId(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(139, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccCommAddrGetTelSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(138, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccCommAddrGetUriId(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(133, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccCommAddrGetUriSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(132, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccGetAddrId(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(121, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccGetAddrSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(120, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccGetMediaId(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(127, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccGetMediaSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(126, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccGetNameId(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(112, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccGetNameSize(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofQrCardPccGetPccType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(110, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccGetUrl(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(107, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccGetVcard(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public boolean Mtc_CprofQrCardPccHasVcard(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(108, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccIconClrData(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(157, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccIconExportFile(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(158, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccIconGetData(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(155, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccIconGetDesc(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(154, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccIconGetEncodingType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(153, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccIconGetMimeType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(152, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccIconGetUri(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
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

        public boolean Mtc_CprofQrCardPccIconHasData(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(156, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccMediaGetLabel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(128, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccMediaGetMediaUrl(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(129, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccMediaGetPref(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(131, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccMediaGetType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(130, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccNameGetDispName(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(116, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccNameGetFamily(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(115, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccNameGetFirst(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(117, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccNameGetGiven(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(113, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccNameGetMiddle(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public int Mtc_CprofQrCardPccNameGetPref(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
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

        public int Mtc_CprofQrCardPccNameGetType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(118, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccTelGetLabel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(140, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccTelGetPref(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(144, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccTelGetTel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(141, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccTelGetTelType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(142, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccTelGetXuiType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(143, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccUriGetLabel(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(134, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccUriGetPref(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(137, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardPccUriGetType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(136, parcel, parcel1, 0);
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

        public String Mtc_CprofQrCardPccUriGetUri(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            parcel.writeInt(i);
            mRemote.transact(135, parcel, parcel1, 0);
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

        public int Mtc_CprofQrCardSetBusinessFlag(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
            if(flag)
                i = 1;
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

        public int Mtc_CprofQrCardUploadFlag()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.juphoon.service.cprof.IRcsCprofService");
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "com.juphoon.service.cprof.IRcsCprofService";
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int Mtc_CprofPccAddAddr()
        throws RemoteException;

    public abstract int Mtc_CprofPccAddName()
        throws RemoteException;

    public abstract String Mtc_CprofPccAddrGetAddr(int i)
        throws RemoteException;

    public abstract String Mtc_CprofPccAddrGetLabel(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccAddrGetPref(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccAddrGetType(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccAddrSetAddr(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccAddrSetLabel(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccAddrSetPref(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofPccAddrSetType(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofPccBirthGetCalType()
        throws RemoteException;

    public abstract String Mtc_CprofPccBirthGetDate()
        throws RemoteException;

    public abstract String Mtc_CprofPccBirthGetNonGregDate()
        throws RemoteException;

    public abstract String Mtc_CprofPccBirthGetPlace()
        throws RemoteException;

    public abstract int Mtc_CprofPccBirthSetCalType(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccBirthSetDate(String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccBirthSetNonGregDate(String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccBirthSetPlace(String s)
        throws RemoteException;

    public abstract String Mtc_CprofPccCareerGetDuty()
        throws RemoteException;

    public abstract String Mtc_CprofPccCareerGetEmployer()
        throws RemoteException;

    public abstract int Mtc_CprofPccCareerSetDuty(String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccCareerSetEmployer(String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccCommAddrAddTel()
        throws RemoteException;

    public abstract int Mtc_CprofPccCommAddrAddUri()
        throws RemoteException;

    public abstract int Mtc_CprofPccCommAddrGetTelId(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccCommAddrGetTelSize()
        throws RemoteException;

    public abstract int Mtc_CprofPccCommAddrGetUriId(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccCommAddrGetUriSize()
        throws RemoteException;

    public abstract int Mtc_CprofPccCommAddrRmvTel(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccCommAddrRmvUri(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccGetAddrId(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccGetAddrSize()
        throws RemoteException;

    public abstract int Mtc_CprofPccGetNameId(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccGetNameSize()
        throws RemoteException;

    public abstract int Mtc_CprofPccGetPccType()
        throws RemoteException;

    public abstract int Mtc_CprofPccIconExportFile(String s)
        throws RemoteException;

    public abstract String Mtc_CprofPccIconGetData()
        throws RemoteException;

    public abstract String Mtc_CprofPccIconGetDesc()
        throws RemoteException;

    public abstract int Mtc_CprofPccIconGetEncodingType()
        throws RemoteException;

    public abstract int Mtc_CprofPccIconGetMimeType()
        throws RemoteException;

    public abstract int Mtc_CprofPccIconImportFile(String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccIconSetData(String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccIconSetDesc(String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccIconSetEncodingType(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccIconSetMimeType(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccLoadAddr()
        throws RemoteException;

    public abstract int Mtc_CprofPccLoadAll()
        throws RemoteException;

    public abstract int Mtc_CprofPccLoadBirth()
        throws RemoteException;

    public abstract int Mtc_CprofPccLoadCareer()
        throws RemoteException;

    public abstract int Mtc_CprofPccLoadCommAddr()
        throws RemoteException;

    public abstract int Mtc_CprofPccLoadIcon(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccLoadName()
        throws RemoteException;

    public abstract String Mtc_CprofPccNameGetDispName(int i)
        throws RemoteException;

    public abstract String Mtc_CprofPccNameGetFamily(int i)
        throws RemoteException;

    public abstract String Mtc_CprofPccNameGetFirst(int i)
        throws RemoteException;

    public abstract String Mtc_CprofPccNameGetGiven(int i)
        throws RemoteException;

    public abstract String Mtc_CprofPccNameGetMiddle(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccNameGetPref(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccNameGetType(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccNameSetDispName(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccNameSetFamily(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccNameSetFirst(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccNameSetGiven(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccNameSetMiddle(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccNameSetPref(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofPccNameSetType(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofPccRmvAddr(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccRmvName(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccSetPccType(int i)
        throws RemoteException;

    public abstract String Mtc_CprofPccTelGetLabel(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccTelGetPref(int i)
        throws RemoteException;

    public abstract String Mtc_CprofPccTelGetTel(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccTelGetTelType(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccTelGetXuiType(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccTelSetLabel(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccTelSetPref(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofPccTelSetTel(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccTelSetTelType(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofPccTelSetXuiType(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofPccUploadAddr()
        throws RemoteException;

    public abstract int Mtc_CprofPccUploadAll()
        throws RemoteException;

    public abstract int Mtc_CprofPccUploadBirth()
        throws RemoteException;

    public abstract int Mtc_CprofPccUploadCareer()
        throws RemoteException;

    public abstract int Mtc_CprofPccUploadCommAddr()
        throws RemoteException;

    public abstract int Mtc_CprofPccUploadIcon()
        throws RemoteException;

    public abstract int Mtc_CprofPccUploadName()
        throws RemoteException;

    public abstract String Mtc_CprofPccUriGetLabel(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccUriGetPref(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccUriGetType(int i)
        throws RemoteException;

    public abstract String Mtc_CprofPccUriGetUri(int i)
        throws RemoteException;

    public abstract int Mtc_CprofPccUriSetLabel(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofPccUriSetPref(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofPccUriSetType(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofPccUriSetUri(int i, String s)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardCttExportFile(String s)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardCttGetData()
        throws RemoteException;

    public abstract String Mtc_CprofQrCardCttGetDesc()
        throws RemoteException;

    public abstract int Mtc_CprofQrCardCttGetEncodingType()
        throws RemoteException;

    public abstract int Mtc_CprofQrCardCttGetMimeType()
        throws RemoteException;

    public abstract boolean Mtc_CprofQrCardGetBusinessFlag()
        throws RemoteException;

    public abstract int Mtc_CprofQrCardLoadAll()
        throws RemoteException;

    public abstract int Mtc_CprofQrCardLoadFlag()
        throws RemoteException;

    public abstract int Mtc_CprofQrCardLoadPcc(String s)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardLoadPccIcon(String s, int i, String s1)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccAddrGetAddr(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccAddrGetLabel(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccAddrGetPref(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccAddrGetType(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccBirthGetCalType(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccBirthGetDate(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccBirthGetNonGregDate(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccBirthGetPlace(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccCareerGetDuty(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccCareerGetEmployer(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccCommAddrGetTelId(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccCommAddrGetTelSize(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccCommAddrGetUriId(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccCommAddrGetUriSize(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccGetAddrId(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccGetAddrSize(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccGetMediaId(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccGetMediaSize(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccGetNameId(int i, int j)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccGetNameSize(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccGetPccType(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccGetUrl(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccGetVcard(int i)
        throws RemoteException;

    public abstract boolean Mtc_CprofQrCardPccHasVcard(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccIconClrData(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccIconExportFile(int i, String s)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccIconGetData(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccIconGetDesc(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccIconGetEncodingType(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccIconGetMimeType(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccIconGetUri(int i)
        throws RemoteException;

    public abstract boolean Mtc_CprofQrCardPccIconHasData(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccMediaGetLabel(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccMediaGetMediaUrl(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccMediaGetPref(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccMediaGetType(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccNameGetDispName(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccNameGetFamily(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccNameGetFirst(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccNameGetGiven(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccNameGetMiddle(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccNameGetPref(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccNameGetType(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccTelGetLabel(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccTelGetPref(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccTelGetTel(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccTelGetTelType(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccTelGetXuiType(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccUriGetLabel(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccUriGetPref(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardPccUriGetType(int i)
        throws RemoteException;

    public abstract String Mtc_CprofQrCardPccUriGetUri(int i)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardSetBusinessFlag(boolean flag)
        throws RemoteException;

    public abstract int Mtc_CprofQrCardUploadFlag()
        throws RemoteException;
}
