// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.app.IApplicationThread;
import android.app.IServiceConnection;
import android.content.*;
import android.content.pm.ParceledListSlice;
import android.content.pm.StringParceledListSlice;
import android.graphics.Bitmap;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import java.util.List;

// Referenced classes of package android.app.admin:
//            SystemUpdateInfo, SystemUpdatePolicy, PasswordMetrics, NetworkEvent

public interface IDevicePolicyManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IDevicePolicyManager
    {

        public static IDevicePolicyManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.app.admin.IDevicePolicyManager");
            if(iinterface != null && (iinterface instanceof IDevicePolicyManager))
                return (IDevicePolicyManager)iinterface;
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
                parcel1.writeString("android.app.admin.IDevicePolicyManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname;
                boolean flag;
                if(parcel.readInt() != 0)
                    componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setPasswordQuality(componentname, i, flag);
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname1;
                boolean flag1;
                if(parcel.readInt() != 0)
                    componentname1 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname1 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                i = getPasswordQuality(componentname1, i, flag1);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname2;
                boolean flag2;
                if(parcel.readInt() != 0)
                    componentname2 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname2 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                setPasswordMinimumLength(componentname2, i, flag2);
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname3;
                boolean flag3;
                if(parcel.readInt() != 0)
                    componentname3 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname3 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                i = getPasswordMinimumLength(componentname3, i, flag3);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname4;
                boolean flag4;
                if(parcel.readInt() != 0)
                    componentname4 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname4 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setPasswordMinimumUpperCase(componentname4, i, flag4);
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname5;
                boolean flag5;
                if(parcel.readInt() != 0)
                    componentname5 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname5 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                i = getPasswordMinimumUpperCase(componentname5, i, flag5);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname6;
                boolean flag6;
                if(parcel.readInt() != 0)
                    componentname6 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname6 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                setPasswordMinimumLowerCase(componentname6, i, flag6);
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname7;
                boolean flag7;
                if(parcel.readInt() != 0)
                    componentname7 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname7 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                i = getPasswordMinimumLowerCase(componentname7, i, flag7);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname8;
                boolean flag8;
                if(parcel.readInt() != 0)
                    componentname8 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname8 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                setPasswordMinimumLetters(componentname8, i, flag8);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname9;
                boolean flag9;
                if(parcel.readInt() != 0)
                    componentname9 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname9 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                i = getPasswordMinimumLetters(componentname9, i, flag9);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname10;
                boolean flag10;
                if(parcel.readInt() != 0)
                    componentname10 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname10 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag10 = true;
                else
                    flag10 = false;
                setPasswordMinimumNumeric(componentname10, i, flag10);
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname11;
                boolean flag11;
                if(parcel.readInt() != 0)
                    componentname11 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname11 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag11 = true;
                else
                    flag11 = false;
                i = getPasswordMinimumNumeric(componentname11, i, flag11);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname12;
                boolean flag12;
                if(parcel.readInt() != 0)
                    componentname12 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname12 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag12 = true;
                else
                    flag12 = false;
                setPasswordMinimumSymbols(componentname12, i, flag12);
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname13;
                boolean flag13;
                if(parcel.readInt() != 0)
                    componentname13 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname13 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag13 = true;
                else
                    flag13 = false;
                i = getPasswordMinimumSymbols(componentname13, i, flag13);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname14;
                boolean flag14;
                if(parcel.readInt() != 0)
                    componentname14 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname14 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                setPasswordMinimumNonLetter(componentname14, i, flag14);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname15;
                boolean flag15;
                if(parcel.readInt() != 0)
                    componentname15 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname15 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                i = getPasswordMinimumNonLetter(componentname15, i, flag15);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname16;
                boolean flag16;
                if(parcel.readInt() != 0)
                    componentname16 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname16 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                setPasswordHistoryLength(componentname16, i, flag16);
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname17;
                boolean flag17;
                if(parcel.readInt() != 0)
                    componentname17 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname17 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                i = getPasswordHistoryLength(componentname17, i, flag17);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname18;
                boolean flag18;
                long l;
                if(parcel.readInt() != 0)
                    componentname18 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname18 = null;
                l = parcel.readLong();
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                setPasswordExpirationTimeout(componentname18, l, flag18);
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname19;
                boolean flag19;
                long l1;
                if(parcel.readInt() != 0)
                    componentname19 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname19 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag19 = true;
                else
                    flag19 = false;
                l1 = getPasswordExpirationTimeout(componentname19, i, flag19);
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname20;
                boolean flag20;
                long l2;
                if(parcel.readInt() != 0)
                    componentname20 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname20 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag20 = true;
                else
                    flag20 = false;
                l2 = getPasswordExpiration(componentname20, i, flag20);
                parcel1.writeNoException();
                parcel1.writeLong(l2);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                i = parcel.readInt();
                boolean flag21;
                if(parcel.readInt() != 0)
                    flag21 = true;
                else
                    flag21 = false;
                flag21 = isActivePasswordSufficient(i, flag21);
                parcel1.writeNoException();
                if(flag21)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag22 = isProfileActivePasswordSufficientForParent(parcel.readInt());
                parcel1.writeNoException();
                if(flag22)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                i = parcel.readInt();
                boolean flag23;
                if(parcel.readInt() != 0)
                    flag23 = true;
                else
                    flag23 = false;
                i = getCurrentFailedPasswordAttempts(i, flag23);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                i = parcel.readInt();
                boolean flag24;
                if(parcel.readInt() != 0)
                    flag24 = true;
                else
                    flag24 = false;
                i = getProfileWithMinimumFailedPasswordsForWipe(i, flag24);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname21;
                boolean flag25;
                if(parcel.readInt() != 0)
                    componentname21 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname21 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag25 = true;
                else
                    flag25 = false;
                setMaximumFailedPasswordsForWipe(componentname21, i, flag25);
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname22;
                boolean flag26;
                if(parcel.readInt() != 0)
                    componentname22 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname22 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag26 = true;
                else
                    flag26 = false;
                i = getMaximumFailedPasswordsForWipe(componentname22, i, flag26);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag27 = resetPassword(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag27)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname23;
                boolean flag28;
                long l3;
                if(parcel.readInt() != 0)
                    componentname23 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname23 = null;
                l3 = parcel.readLong();
                if(parcel.readInt() != 0)
                    flag28 = true;
                else
                    flag28 = false;
                setMaximumTimeToLock(componentname23, l3, flag28);
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname24;
                boolean flag29;
                long l4;
                if(parcel.readInt() != 0)
                    componentname24 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname24 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag29 = true;
                else
                    flag29 = false;
                l4 = getMaximumTimeToLock(componentname24, i, flag29);
                parcel1.writeNoException();
                parcel1.writeLong(l4);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                long l5 = getMaximumTimeToLockForUserAndProfiles(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeLong(l5);
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname25;
                boolean flag30;
                long l6;
                if(parcel.readInt() != 0)
                    componentname25 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname25 = null;
                l6 = parcel.readLong();
                if(parcel.readInt() != 0)
                    flag30 = true;
                else
                    flag30 = false;
                setRequiredStrongAuthTimeout(componentname25, l6, flag30);
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname26;
                boolean flag31;
                long l7;
                if(parcel.readInt() != 0)
                    componentname26 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname26 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag31 = true;
                else
                    flag31 = false;
                l7 = getRequiredStrongAuthTimeout(componentname26, i, flag31);
                parcel1.writeNoException();
                parcel1.writeLong(l7);
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                i = parcel.readInt();
                boolean flag32;
                if(parcel.readInt() != 0)
                    flag32 = true;
                else
                    flag32 = false;
                lockNow(i, flag32);
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                wipeData(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname27;
                if(parcel.readInt() != 0)
                    componentname27 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname27 = null;
                parcel = setGlobalProxy(componentname27, parcel.readString(), parcel.readString());
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
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getGlobalProxyAdmin(parcel.readInt());
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

            case 38: // '&'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname28;
                if(parcel.readInt() != 0)
                    componentname28 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname28 = null;
                if(parcel.readInt() != 0)
                    parcel = (ProxyInfo)ProxyInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setRecommendedGlobalProxy(componentname28, parcel);
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname29;
                boolean flag33;
                if(parcel.readInt() != 0)
                    componentname29 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname29 = null;
                if(parcel.readInt() != 0)
                    flag33 = true;
                else
                    flag33 = false;
                i = setStorageEncryption(componentname29, flag33);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 40: // '('
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname30;
                boolean flag34;
                if(parcel.readInt() != 0)
                    componentname30 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname30 = null;
                flag34 = getStorageEncryption(componentname30, parcel.readInt());
                parcel1.writeNoException();
                if(flag34)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                i = getStorageEncryptionStatus(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag35;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag35 = requestBugreport(parcel);
                parcel1.writeNoException();
                if(flag35)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname31;
                boolean flag36;
                if(parcel.readInt() != 0)
                    componentname31 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname31 = null;
                if(parcel.readInt() != 0)
                    flag36 = true;
                else
                    flag36 = false;
                setCameraDisabled(componentname31, flag36);
                parcel1.writeNoException();
                return true;

            case 44: // ','
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname32;
                boolean flag37;
                if(parcel.readInt() != 0)
                    componentname32 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname32 = null;
                flag37 = getCameraDisabled(componentname32, parcel.readInt());
                parcel1.writeNoException();
                if(flag37)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname33;
                boolean flag38;
                if(parcel.readInt() != 0)
                    componentname33 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname33 = null;
                if(parcel.readInt() != 0)
                    flag38 = true;
                else
                    flag38 = false;
                setScreenCaptureDisabled(componentname33, flag38);
                parcel1.writeNoException();
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname34;
                boolean flag39;
                if(parcel.readInt() != 0)
                    componentname34 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname34 = null;
                flag39 = getScreenCaptureDisabled(componentname34, parcel.readInt());
                parcel1.writeNoException();
                if(flag39)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname35;
                boolean flag40;
                if(parcel.readInt() != 0)
                    componentname35 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname35 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag40 = true;
                else
                    flag40 = false;
                setKeyguardDisabledFeatures(componentname35, i, flag40);
                parcel1.writeNoException();
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname36;
                boolean flag41;
                if(parcel.readInt() != 0)
                    componentname36 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname36 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag41 = true;
                else
                    flag41 = false;
                i = getKeyguardDisabledFeatures(componentname36, i, flag41);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname37;
                boolean flag42;
                if(parcel.readInt() != 0)
                    componentname37 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname37 = null;
                if(parcel.readInt() != 0)
                    flag42 = true;
                else
                    flag42 = false;
                setActiveAdmin(componentname37, flag42, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname38;
                boolean flag43;
                if(parcel.readInt() != 0)
                    componentname38 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname38 = null;
                flag43 = isAdminActive(componentname38, parcel.readInt());
                parcel1.writeNoException();
                if(flag43)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getActiveAdmins(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag44 = packageHasActiveAdmins(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag44)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname39;
                RemoteCallback remotecallback;
                if(parcel.readInt() != 0)
                    componentname39 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname39 = null;
                if(parcel.readInt() != 0)
                    remotecallback = (RemoteCallback)RemoteCallback.CREATOR.createFromParcel(parcel);
                else
                    remotecallback = null;
                getRemoveWarning(componentname39, remotecallback, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname40;
                if(parcel.readInt() != 0)
                    componentname40 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname40 = null;
                removeActiveAdmin(componentname40, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname41;
                if(parcel.readInt() != 0)
                    componentname41 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname41 = null;
                forceRemoveActiveAdmin(componentname41, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname42;
                boolean flag45;
                if(parcel.readInt() != 0)
                    componentname42 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname42 = null;
                flag45 = hasGrantedPolicy(componentname42, parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag45)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                PasswordMetrics passwordmetrics;
                if(parcel.readInt() != 0)
                    passwordmetrics = (PasswordMetrics)PasswordMetrics.CREATOR.createFromParcel(parcel);
                else
                    passwordmetrics = null;
                setActivePasswordState(passwordmetrics, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                reportPasswordChanged(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 59: // ';'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                reportFailedPasswordAttempt(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 60: // '<'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                reportSuccessfulPasswordAttempt(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 61: // '='
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                reportFailedFingerprintAttempt(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                reportSuccessfulFingerprintAttempt(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                reportKeyguardDismissed(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 64: // '@'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                reportKeyguardSecured(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname43;
                boolean flag46;
                if(parcel.readInt() != 0)
                    componentname43 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname43 = null;
                flag46 = setDeviceOwner(componentname43, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag46)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag47;
                if(parcel.readInt() != 0)
                    flag47 = true;
                else
                    flag47 = false;
                parcel = getDeviceOwnerComponent(flag47);
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

            case 67: // 'C'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag48 = hasDeviceOwner();
                parcel1.writeNoException();
                if(flag48)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getDeviceOwnerName();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 69: // 'E'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                clearDeviceOwner(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 70: // 'F'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                i = getDeviceOwnerUserId();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 71: // 'G'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname44;
                boolean flag49;
                if(parcel.readInt() != 0)
                    componentname44 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname44 = null;
                flag49 = setProfileOwner(componentname44, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag49)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 72: // 'H'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getProfileOwner(parcel.readInt());
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

            case 73: // 'I'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getProfileOwnerName(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 74: // 'J'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setProfileEnabled(parcel);
                parcel1.writeNoException();
                return true;

            case 75: // 'K'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname45;
                if(parcel.readInt() != 0)
                    componentname45 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname45 = null;
                setProfileName(componentname45, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 76: // 'L'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                clearProfileOwner(parcel);
                parcel1.writeNoException();
                return true;

            case 77: // 'M'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag50 = hasUserSetupCompleted();
                parcel1.writeNoException();
                if(flag50)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 78: // 'N'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname46;
                if(parcel.readInt() != 0)
                    componentname46 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname46 = null;
                if(parcel.readInt() != 0)
                    parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setDeviceOwnerLockScreenInfo(componentname46, parcel);
                parcel1.writeNoException();
                return true;

            case 79: // 'O'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getDeviceOwnerLockScreenInfo();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    TextUtils.writeToParcel(parcel, parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 80: // 'P'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname47;
                boolean flag51;
                String as[];
                String s9;
                if(parcel.readInt() != 0)
                    componentname47 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname47 = null;
                s9 = parcel.readString();
                as = parcel.createStringArray();
                if(parcel.readInt() != 0)
                    flag51 = true;
                else
                    flag51 = false;
                parcel = setPackagesSuspended(componentname47, s9, as, flag51);
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 81: // 'Q'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname48;
                boolean flag52;
                if(parcel.readInt() != 0)
                    componentname48 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname48 = null;
                flag52 = isPackageSuspended(componentname48, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag52)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 82: // 'R'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname49;
                boolean flag53;
                if(parcel.readInt() != 0)
                    componentname49 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname49 = null;
                flag53 = installCaCert(componentname49, parcel.readString(), parcel.createByteArray());
                parcel1.writeNoException();
                if(flag53)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 83: // 'S'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname50;
                if(parcel.readInt() != 0)
                    componentname50 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname50 = null;
                uninstallCaCerts(componentname50, parcel.readString(), parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 84: // 'T'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname51;
                if(parcel.readInt() != 0)
                    componentname51 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname51 = null;
                enforceCanManageCaCerts(componentname51, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 85: // 'U'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                String s = parcel.readString();
                i = parcel.readInt();
                boolean flag54;
                if(parcel.readInt() != 0)
                    flag54 = true;
                else
                    flag54 = false;
                flag54 = approveCaCert(s, i, flag54);
                parcel1.writeNoException();
                if(flag54)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 86: // 'V'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag55 = isCaCertApproved(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag55)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 87: // 'W'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname52;
                boolean flag56;
                byte abyte0[];
                String s10;
                byte abyte1[];
                byte abyte2[];
                String s15;
                if(parcel.readInt() != 0)
                    componentname52 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname52 = null;
                s10 = parcel.readString();
                abyte0 = parcel.createByteArray();
                abyte1 = parcel.createByteArray();
                abyte2 = parcel.createByteArray();
                s15 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag56 = true;
                else
                    flag56 = false;
                flag56 = installKeyPair(componentname52, s10, abyte0, abyte1, abyte2, s15, flag56);
                parcel1.writeNoException();
                if(flag56)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 88: // 'X'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname53;
                boolean flag57;
                if(parcel.readInt() != 0)
                    componentname53 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname53 = null;
                flag57 = removeKeyPair(componentname53, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag57)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 89: // 'Y'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                i = parcel.readInt();
                Uri uri;
                if(parcel.readInt() != 0)
                    uri = (Uri)Uri.CREATOR.createFromParcel(parcel);
                else
                    uri = null;
                choosePrivateKeyAlias(i, uri, parcel.readString(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 90: // 'Z'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname54;
                if(parcel.readInt() != 0)
                    componentname54 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname54 = null;
                setDelegatedScopes(componentname54, parcel.readString(), parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 91: // '['
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname55;
                if(parcel.readInt() != 0)
                    componentname55 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname55 = null;
                parcel = getDelegatedScopes(componentname55, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 92: // '\\'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname56;
                if(parcel.readInt() != 0)
                    componentname56 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname56 = null;
                parcel = getDelegatePackages(componentname56, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 93: // ']'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname57;
                if(parcel.readInt() != 0)
                    componentname57 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname57 = null;
                setCertInstallerPackage(componentname57, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 94: // '^'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getCertInstallerPackage(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 95: // '_'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname58;
                boolean flag58;
                String s2;
                if(parcel.readInt() != 0)
                    componentname58 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname58 = null;
                s2 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag58 = true;
                else
                    flag58 = false;
                flag58 = setAlwaysOnVpnPackage(componentname58, s2, flag58);
                parcel1.writeNoException();
                if(flag58)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 96: // '`'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getAlwaysOnVpnPackage(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 97: // 'a'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname59;
                IntentFilter intentfilter;
                if(parcel.readInt() != 0)
                    componentname59 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname59 = null;
                if(parcel.readInt() != 0)
                    intentfilter = (IntentFilter)IntentFilter.CREATOR.createFromParcel(parcel);
                else
                    intentfilter = null;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addPersistentPreferredActivity(componentname59, intentfilter, parcel);
                parcel1.writeNoException();
                return true;

            case 98: // 'b'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname60;
                if(parcel.readInt() != 0)
                    componentname60 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname60 = null;
                clearPackagePersistentPreferredActivities(componentname60, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 99: // 'c'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname61;
                String s3;
                String s11;
                if(parcel.readInt() != 0)
                    componentname61 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname61 = null;
                s11 = parcel.readString();
                s3 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setApplicationRestrictions(componentname61, s11, s3, parcel);
                parcel1.writeNoException();
                return true;

            case 100: // 'd'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname62;
                if(parcel.readInt() != 0)
                    componentname62 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname62 = null;
                parcel = getApplicationRestrictions(componentname62, parcel.readString(), parcel.readString());
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
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname63;
                boolean flag59;
                if(parcel.readInt() != 0)
                    componentname63 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname63 = null;
                flag59 = setApplicationRestrictionsManagingPackage(componentname63, parcel.readString());
                parcel1.writeNoException();
                if(flag59)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 102: // 'f'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getApplicationRestrictionsManagingPackage(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 103: // 'g'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag60 = isCallerApplicationRestrictionsManagingPackage(parcel.readString());
                parcel1.writeNoException();
                if(flag60)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 104: // 'h'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname64;
                if(parcel.readInt() != 0)
                    componentname64 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname64 = null;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setRestrictionsProvider(componentname64, parcel);
                parcel1.writeNoException();
                return true;

            case 105: // 'i'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getRestrictionsProvider(parcel.readInt());
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

            case 106: // 'j'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname65;
                boolean flag61;
                String s4;
                if(parcel.readInt() != 0)
                    componentname65 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname65 = null;
                s4 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag61 = true;
                else
                    flag61 = false;
                setUserRestriction(componentname65, s4, flag61);
                parcel1.writeNoException();
                return true;

            case 107: // 'k'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getUserRestrictions(parcel);
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

            case 108: // 'l'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname66;
                IntentFilter intentfilter1;
                if(parcel.readInt() != 0)
                    componentname66 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname66 = null;
                if(parcel.readInt() != 0)
                    intentfilter1 = (IntentFilter)IntentFilter.CREATOR.createFromParcel(parcel);
                else
                    intentfilter1 = null;
                addCrossProfileIntentFilter(componentname66, intentfilter1, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 109: // 'm'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                clearCrossProfileIntentFilters(parcel);
                parcel1.writeNoException();
                return true;

            case 110: // 'n'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname67;
                boolean flag62;
                if(parcel.readInt() != 0)
                    componentname67 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname67 = null;
                flag62 = setPermittedAccessibilityServices(componentname67, parcel.readArrayList(getClass().getClassLoader()));
                parcel1.writeNoException();
                if(flag62)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 111: // 'o'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPermittedAccessibilityServices(parcel);
                parcel1.writeNoException();
                parcel1.writeList(parcel);
                return true;

            case 112: // 'p'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getPermittedAccessibilityServicesForUser(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeList(parcel);
                return true;

            case 113: // 'q'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname68;
                boolean flag63;
                if(parcel.readInt() != 0)
                    componentname68 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname68 = null;
                flag63 = isAccessibilityServicePermittedByAdmin(componentname68, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag63)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 114: // 'r'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname69;
                boolean flag64;
                if(parcel.readInt() != 0)
                    componentname69 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname69 = null;
                flag64 = setPermittedInputMethods(componentname69, parcel.readArrayList(getClass().getClassLoader()));
                parcel1.writeNoException();
                if(flag64)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 115: // 's'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPermittedInputMethods(parcel);
                parcel1.writeNoException();
                parcel1.writeList(parcel);
                return true;

            case 116: // 't'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getPermittedInputMethodsForCurrentUser();
                parcel1.writeNoException();
                parcel1.writeList(parcel);
                return true;

            case 117: // 'u'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname70;
                boolean flag65;
                if(parcel.readInt() != 0)
                    componentname70 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname70 = null;
                flag65 = isInputMethodPermittedByAdmin(componentname70, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag65)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 118: // 'v'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname71;
                boolean flag66;
                if(parcel.readInt() != 0)
                    componentname71 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname71 = null;
                flag66 = setPermittedCrossProfileNotificationListeners(componentname71, parcel.createStringArrayList());
                parcel1.writeNoException();
                if(flag66)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 119: // 'w'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPermittedCrossProfileNotificationListeners(parcel);
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 120: // 'x'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag67 = isNotificationListenerServicePermitted(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag67)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 121: // 'y'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = createAdminSupportIntent(parcel.readString());
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

            case 122: // 'z'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname72;
                boolean flag68;
                String s5;
                String s12;
                if(parcel.readInt() != 0)
                    componentname72 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname72 = null;
                s5 = parcel.readString();
                s12 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag68 = true;
                else
                    flag68 = false;
                flag68 = setApplicationHidden(componentname72, s5, s12, flag68);
                parcel1.writeNoException();
                if(flag68)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 123: // '{'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname73;
                boolean flag69;
                if(parcel.readInt() != 0)
                    componentname73 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname73 = null;
                flag69 = isApplicationHidden(componentname73, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag69)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 124: // '|'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname74;
                ComponentName componentname119;
                PersistableBundle persistablebundle;
                String s14;
                if(parcel.readInt() != 0)
                    componentname74 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname74 = null;
                s14 = parcel.readString();
                if(parcel.readInt() != 0)
                    componentname119 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname119 = null;
                if(parcel.readInt() != 0)
                    persistablebundle = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(parcel);
                else
                    persistablebundle = null;
                parcel = createAndManageUser(componentname74, s14, componentname119, persistablebundle, parcel.readInt());
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

            case 125: // '}'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname75;
                boolean flag70;
                if(parcel.readInt() != 0)
                    componentname75 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname75 = null;
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag70 = removeUser(componentname75, parcel);
                parcel1.writeNoException();
                if(flag70)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 126: // '~'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname76;
                boolean flag71;
                if(parcel.readInt() != 0)
                    componentname76 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname76 = null;
                if(parcel.readInt() != 0)
                    parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag71 = switchUser(componentname76, parcel);
                parcel1.writeNoException();
                if(flag71)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 127: // '\177'
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname77;
                if(parcel.readInt() != 0)
                    componentname77 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname77 = null;
                enableSystemApp(componentname77, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 128: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname78;
                String s6;
                if(parcel.readInt() != 0)
                    componentname78 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname78 = null;
                s6 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = enableSystemAppWithIntent(componentname78, s6, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 129: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname79;
                boolean flag72;
                String s7;
                if(parcel.readInt() != 0)
                    componentname79 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname79 = null;
                s7 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag72 = true;
                else
                    flag72 = false;
                setAccountManagementDisabled(componentname79, s7, flag72);
                parcel1.writeNoException();
                return true;

            case 130: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getAccountTypesWithManagementDisabled();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 131: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getAccountTypesWithManagementDisabledAsUser(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 132: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname80;
                if(parcel.readInt() != 0)
                    componentname80 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname80 = null;
                setLockTaskPackages(componentname80, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 133: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getLockTaskPackages(parcel);
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 134: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag73 = isLockTaskPermitted(parcel.readString());
                parcel1.writeNoException();
                if(flag73)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 135: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname81;
                if(parcel.readInt() != 0)
                    componentname81 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname81 = null;
                setGlobalSetting(componentname81, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 136: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname82;
                if(parcel.readInt() != 0)
                    componentname82 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname82 = null;
                setSecureSetting(componentname82, parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 137: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname83;
                boolean flag74;
                if(parcel.readInt() != 0)
                    componentname83 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname83 = null;
                if(parcel.readInt() != 0)
                    flag74 = true;
                else
                    flag74 = false;
                setMasterVolumeMuted(componentname83, flag74);
                parcel1.writeNoException();
                return true;

            case 138: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag75;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag75 = isMasterVolumeMuted(parcel);
                parcel1.writeNoException();
                if(flag75)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 139: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag76;
                if(parcel.readInt() != 0)
                    flag76 = true;
                else
                    flag76 = false;
                notifyLockTaskModeChanged(flag76, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 140: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname84;
                boolean flag77;
                String s8;
                String s13;
                if(parcel.readInt() != 0)
                    componentname84 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname84 = null;
                s13 = parcel.readString();
                s8 = parcel.readString();
                if(parcel.readInt() != 0)
                    flag77 = true;
                else
                    flag77 = false;
                setUninstallBlocked(componentname84, s13, s8, flag77);
                parcel1.writeNoException();
                return true;

            case 141: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname85;
                boolean flag78;
                if(parcel.readInt() != 0)
                    componentname85 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname85 = null;
                flag78 = isUninstallBlocked(componentname85, parcel.readString());
                parcel1.writeNoException();
                if(flag78)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 142: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname86;
                boolean flag79;
                if(parcel.readInt() != 0)
                    componentname86 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname86 = null;
                if(parcel.readInt() != 0)
                    flag79 = true;
                else
                    flag79 = false;
                setCrossProfileCallerIdDisabled(componentname86, flag79);
                parcel1.writeNoException();
                return true;

            case 143: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag80;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag80 = getCrossProfileCallerIdDisabled(parcel);
                parcel1.writeNoException();
                if(flag80)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 144: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag81 = getCrossProfileCallerIdDisabledForUser(parcel.readInt());
                parcel1.writeNoException();
                if(flag81)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 145: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname87;
                boolean flag82;
                if(parcel.readInt() != 0)
                    componentname87 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname87 = null;
                if(parcel.readInt() != 0)
                    flag82 = true;
                else
                    flag82 = false;
                setCrossProfileContactsSearchDisabled(componentname87, flag82);
                parcel1.writeNoException();
                return true;

            case 146: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag83;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag83 = getCrossProfileContactsSearchDisabled(parcel);
                parcel1.writeNoException();
                if(flag83)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 147: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag84 = getCrossProfileContactsSearchDisabledForUser(parcel.readInt());
                parcel1.writeNoException();
                if(flag84)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 148: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                String s1 = parcel.readString();
                long l12 = parcel.readLong();
                boolean flag85;
                long l8;
                if(parcel.readInt() != 0)
                    flag85 = true;
                else
                    flag85 = false;
                l8 = parcel.readLong();
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startManagedQuickContact(s1, l12, flag85, l8, parcel);
                parcel1.writeNoException();
                return true;

            case 149: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname88;
                boolean flag86;
                if(parcel.readInt() != 0)
                    componentname88 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname88 = null;
                if(parcel.readInt() != 0)
                    flag86 = true;
                else
                    flag86 = false;
                setBluetoothContactSharingDisabled(componentname88, flag86);
                parcel1.writeNoException();
                return true;

            case 150: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag87;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag87 = getBluetoothContactSharingDisabled(parcel);
                parcel1.writeNoException();
                if(flag87)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 151: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag88 = getBluetoothContactSharingDisabledForUser(parcel.readInt());
                parcel1.writeNoException();
                if(flag88)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 152: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname89;
                boolean flag89;
                ComponentName componentname120;
                PersistableBundle persistablebundle1;
                if(parcel.readInt() != 0)
                    componentname89 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname89 = null;
                if(parcel.readInt() != 0)
                    componentname120 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname120 = null;
                if(parcel.readInt() != 0)
                    persistablebundle1 = (PersistableBundle)PersistableBundle.CREATOR.createFromParcel(parcel);
                else
                    persistablebundle1 = null;
                if(parcel.readInt() != 0)
                    flag89 = true;
                else
                    flag89 = false;
                setTrustAgentConfiguration(componentname89, componentname120, persistablebundle1, flag89);
                parcel1.writeNoException();
                return true;

            case 153: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname90;
                boolean flag90;
                ComponentName componentname121;
                if(parcel.readInt() != 0)
                    componentname90 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname90 = null;
                if(parcel.readInt() != 0)
                    componentname121 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname121 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag90 = true;
                else
                    flag90 = false;
                parcel = getTrustAgentConfiguration(componentname90, componentname121, i, flag90);
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 154: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname91;
                boolean flag91;
                if(parcel.readInt() != 0)
                    componentname91 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname91 = null;
                flag91 = addCrossProfileWidgetProvider(componentname91, parcel.readString());
                parcel1.writeNoException();
                if(flag91)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 155: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname92;
                boolean flag92;
                if(parcel.readInt() != 0)
                    componentname92 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname92 = null;
                flag92 = removeCrossProfileWidgetProvider(componentname92, parcel.readString());
                parcel1.writeNoException();
                if(flag92)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 156: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getCrossProfileWidgetProviders(parcel);
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 157: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname93;
                boolean flag93;
                if(parcel.readInt() != 0)
                    componentname93 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname93 = null;
                if(parcel.readInt() != 0)
                    flag93 = true;
                else
                    flag93 = false;
                setAutoTimeRequired(componentname93, flag93);
                parcel1.writeNoException();
                return true;

            case 158: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag94 = getAutoTimeRequired();
                parcel1.writeNoException();
                if(flag94)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 159: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname94;
                boolean flag95;
                if(parcel.readInt() != 0)
                    componentname94 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname94 = null;
                if(parcel.readInt() != 0)
                    flag95 = true;
                else
                    flag95 = false;
                setForceEphemeralUsers(componentname94, flag95);
                parcel1.writeNoException();
                return true;

            case 160: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag96;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag96 = getForceEphemeralUsers(parcel);
                parcel1.writeNoException();
                if(flag96)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 161: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname95;
                boolean flag97;
                if(parcel.readInt() != 0)
                    componentname95 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname95 = null;
                flag97 = isRemovingAdmin(componentname95, parcel.readInt());
                parcel1.writeNoException();
                if(flag97)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 162: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname96;
                if(parcel.readInt() != 0)
                    componentname96 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname96 = null;
                if(parcel.readInt() != 0)
                    parcel = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setUserIcon(componentname96, parcel);
                parcel1.writeNoException();
                return true;

            case 163: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname97;
                if(parcel.readInt() != 0)
                    componentname97 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname97 = null;
                if(parcel.readInt() != 0)
                    parcel = (SystemUpdatePolicy)SystemUpdatePolicy.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setSystemUpdatePolicy(componentname97, parcel);
                parcel1.writeNoException();
                return true;

            case 164: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getSystemUpdatePolicy();
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

            case 165: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname98;
                boolean flag98;
                if(parcel.readInt() != 0)
                    componentname98 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname98 = null;
                if(parcel.readInt() != 0)
                    flag98 = true;
                else
                    flag98 = false;
                flag98 = setKeyguardDisabled(componentname98, flag98);
                parcel1.writeNoException();
                if(flag98)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 166: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname99;
                boolean flag99;
                if(parcel.readInt() != 0)
                    componentname99 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname99 = null;
                if(parcel.readInt() != 0)
                    flag99 = true;
                else
                    flag99 = false;
                flag99 = setStatusBarDisabled(componentname99, flag99);
                parcel1.writeNoException();
                if(flag99)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 167: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag100 = getDoNotAskCredentialsOnBoot();
                parcel1.writeNoException();
                if(flag100)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 168: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (SystemUpdateInfo)SystemUpdateInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyPendingSystemUpdate(parcel);
                parcel1.writeNoException();
                return true;

            case 169: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getPendingSystemUpdate(parcel);
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

            case 170: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname100;
                if(parcel.readInt() != 0)
                    componentname100 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname100 = null;
                setPermissionPolicy(componentname100, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 171: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getPermissionPolicy(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 172: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname101;
                boolean flag101;
                if(parcel.readInt() != 0)
                    componentname101 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname101 = null;
                flag101 = setPermissionGrantState(componentname101, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag101)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 173: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname102;
                if(parcel.readInt() != 0)
                    componentname102 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname102 = null;
                i = getPermissionGrantState(componentname102, parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 174: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag102 = isProvisioningAllowed(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag102)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 175: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                i = checkProvisioningPreCondition(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 176: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname103;
                if(parcel.readInt() != 0)
                    componentname103 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname103 = null;
                setKeepUninstalledPackages(componentname103, parcel.readString(), parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 177: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname104;
                if(parcel.readInt() != 0)
                    componentname104 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname104 = null;
                parcel = getKeepUninstalledPackages(componentname104, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 178: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag103;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag103 = isManagedProfile(parcel);
                parcel1.writeNoException();
                if(flag103)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 179: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag104;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag104 = isSystemOnlyUser(parcel);
                parcel1.writeNoException();
                if(flag104)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 180: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getWifiMacAddress(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 181: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                reboot(parcel);
                parcel1.writeNoException();
                return true;

            case 182: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname105;
                if(parcel.readInt() != 0)
                    componentname105 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname105 = null;
                if(parcel.readInt() != 0)
                    parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setShortSupportMessage(componentname105, parcel);
                parcel1.writeNoException();
                return true;

            case 183: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getShortSupportMessage(parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    TextUtils.writeToParcel(parcel, parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 184: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname106;
                if(parcel.readInt() != 0)
                    componentname106 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname106 = null;
                if(parcel.readInt() != 0)
                    parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setLongSupportMessage(componentname106, parcel);
                parcel1.writeNoException();
                return true;

            case 185: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getLongSupportMessage(parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    TextUtils.writeToParcel(parcel, parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 186: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname107;
                if(parcel.readInt() != 0)
                    componentname107 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname107 = null;
                parcel = getShortSupportMessageForUser(componentname107, parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    TextUtils.writeToParcel(parcel, parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 187: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname108;
                if(parcel.readInt() != 0)
                    componentname108 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname108 = null;
                parcel = getLongSupportMessageForUser(componentname108, parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    TextUtils.writeToParcel(parcel, parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 188: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag105 = isSeparateProfileChallengeAllowed(parcel.readInt());
                parcel1.writeNoException();
                if(flag105)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 189: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname109;
                if(parcel.readInt() != 0)
                    componentname109 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname109 = null;
                setOrganizationColor(componentname109, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 190: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                setOrganizationColorForUser(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 191: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getOrganizationColor(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 192: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                i = getOrganizationColorForUser(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 193: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname110;
                if(parcel.readInt() != 0)
                    componentname110 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname110 = null;
                if(parcel.readInt() != 0)
                    parcel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setOrganizationName(componentname110, parcel);
                parcel1.writeNoException();
                return true;

            case 194: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getOrganizationName(parcel);
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    TextUtils.writeToParcel(parcel, parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 195: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getDeviceOwnerOrganizationName();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    TextUtils.writeToParcel(parcel, parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 196: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                parcel = getOrganizationNameForUser(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    TextUtils.writeToParcel(parcel, parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 197: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                i = getUserProvisioningState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 198: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                setUserProvisioningState(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 199: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname111;
                if(parcel.readInt() != 0)
                    componentname111 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname111 = null;
                setAffiliationIds(componentname111, parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 200: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getAffiliationIds(parcel);
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 201: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag106 = isAffiliatedUser();
                parcel1.writeNoException();
                if(flag106)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 202: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname112;
                boolean flag107;
                if(parcel.readInt() != 0)
                    componentname112 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname112 = null;
                if(parcel.readInt() != 0)
                    flag107 = true;
                else
                    flag107 = false;
                setSecurityLoggingEnabled(componentname112, flag107);
                parcel1.writeNoException();
                return true;

            case 203: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag108;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag108 = isSecurityLoggingEnabled(parcel);
                parcel1.writeNoException();
                if(flag108)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 204: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = retrieveSecurityLogs(parcel);
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

            case 205: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = retrievePreRebootSecurityLogs(parcel);
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

            case 206: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag109 = isUninstallInQueue(parcel.readString());
                parcel1.writeNoException();
                if(flag109)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 207: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                uninstallPackageWithActiveAdmins(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 208: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag110 = isDeviceProvisioned();
                parcel1.writeNoException();
                if(flag110)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 209: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag111 = isDeviceProvisioningConfigApplied();
                parcel1.writeNoException();
                if(flag111)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 210: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                setDeviceProvisioningConfigApplied();
                parcel1.writeNoException();
                return true;

            case 211: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                forceUpdateUserSetupComplete();
                parcel1.writeNoException();
                return true;

            case 212: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname113;
                boolean flag112;
                if(parcel.readInt() != 0)
                    componentname113 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname113 = null;
                if(parcel.readInt() != 0)
                    flag112 = true;
                else
                    flag112 = false;
                setBackupServiceEnabled(componentname113, flag112);
                parcel1.writeNoException();
                return true;

            case 213: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag113;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag113 = isBackupServiceEnabled(parcel);
                parcel1.writeNoException();
                if(flag113)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 214: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname114;
                boolean flag114;
                if(parcel.readInt() != 0)
                    componentname114 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname114 = null;
                if(parcel.readInt() != 0)
                    flag114 = true;
                else
                    flag114 = false;
                setNetworkLoggingEnabled(componentname114, flag114);
                parcel1.writeNoException();
                return true;

            case 215: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag115;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag115 = isNetworkLoggingEnabled(parcel);
                parcel1.writeNoException();
                if(flag115)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 216: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname115;
                if(parcel.readInt() != 0)
                    componentname115 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname115 = null;
                parcel = retrieveNetworkLogs(componentname115, parcel.readLong());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 217: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname116;
                boolean flag116;
                Intent intent;
                IApplicationThread iapplicationthread;
                IBinder ibinder;
                if(parcel.readInt() != 0)
                    componentname116 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname116 = null;
                iapplicationthread = android.app.IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                ibinder = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent = null;
                flag116 = bindDeviceAdminServiceAsUser(componentname116, iapplicationthread, ibinder, intent, android.app.IServiceConnection.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag116)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 218: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getBindDeviceAdminTargetUsers(parcel);
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 219: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                long l9 = getLastSecurityLogRetrievalTime();
                parcel1.writeNoException();
                parcel1.writeLong(l9);
                return true;

            case 220: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                long l10 = getLastBugReportRequestTime();
                parcel1.writeNoException();
                parcel1.writeLong(l10);
                return true;

            case 221: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                long l11 = getLastNetworkLogRetrievalTime();
                parcel1.writeNoException();
                parcel1.writeLong(l11);
                return true;

            case 222: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname117;
                boolean flag117;
                if(parcel.readInt() != 0)
                    componentname117 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname117 = null;
                flag117 = setResetPasswordToken(componentname117, parcel.createByteArray());
                parcel1.writeNoException();
                if(flag117)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 223: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag118;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag118 = clearResetPasswordToken(parcel);
                parcel1.writeNoException();
                if(flag118)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 224: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag119;
                if(parcel.readInt() != 0)
                    parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag119 = isResetPasswordTokenActive(parcel);
                parcel1.writeNoException();
                if(flag119)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 225: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                ComponentName componentname118;
                boolean flag120;
                if(parcel.readInt() != 0)
                    componentname118 = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
                else
                    componentname118 = null;
                flag120 = resetPasswordWithToken(componentname118, parcel.readString(), parcel.createByteArray(), parcel.readInt());
                parcel1.writeNoException();
                if(flag120)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 226: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                boolean flag121 = isCurrentInputMethodSetByOwner();
                parcel1.writeNoException();
                if(flag121)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 227: 
                parcel.enforceInterface("android.app.admin.IDevicePolicyManager");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            parcel = getOwnerInstalledCaCerts(parcel);
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
        }

        private static final String DESCRIPTOR = "android.app.admin.IDevicePolicyManager";
        static final int TRANSACTION_addCrossProfileIntentFilter = 108;
        static final int TRANSACTION_addCrossProfileWidgetProvider = 154;
        static final int TRANSACTION_addPersistentPreferredActivity = 97;
        static final int TRANSACTION_approveCaCert = 85;
        static final int TRANSACTION_bindDeviceAdminServiceAsUser = 217;
        static final int TRANSACTION_checkProvisioningPreCondition = 175;
        static final int TRANSACTION_choosePrivateKeyAlias = 89;
        static final int TRANSACTION_clearCrossProfileIntentFilters = 109;
        static final int TRANSACTION_clearDeviceOwner = 69;
        static final int TRANSACTION_clearPackagePersistentPreferredActivities = 98;
        static final int TRANSACTION_clearProfileOwner = 76;
        static final int TRANSACTION_clearResetPasswordToken = 223;
        static final int TRANSACTION_createAdminSupportIntent = 121;
        static final int TRANSACTION_createAndManageUser = 124;
        static final int TRANSACTION_enableSystemApp = 127;
        static final int TRANSACTION_enableSystemAppWithIntent = 128;
        static final int TRANSACTION_enforceCanManageCaCerts = 84;
        static final int TRANSACTION_forceRemoveActiveAdmin = 55;
        static final int TRANSACTION_forceUpdateUserSetupComplete = 211;
        static final int TRANSACTION_getAccountTypesWithManagementDisabled = 130;
        static final int TRANSACTION_getAccountTypesWithManagementDisabledAsUser = 131;
        static final int TRANSACTION_getActiveAdmins = 51;
        static final int TRANSACTION_getAffiliationIds = 200;
        static final int TRANSACTION_getAlwaysOnVpnPackage = 96;
        static final int TRANSACTION_getApplicationRestrictions = 100;
        static final int TRANSACTION_getApplicationRestrictionsManagingPackage = 102;
        static final int TRANSACTION_getAutoTimeRequired = 158;
        static final int TRANSACTION_getBindDeviceAdminTargetUsers = 218;
        static final int TRANSACTION_getBluetoothContactSharingDisabled = 150;
        static final int TRANSACTION_getBluetoothContactSharingDisabledForUser = 151;
        static final int TRANSACTION_getCameraDisabled = 44;
        static final int TRANSACTION_getCertInstallerPackage = 94;
        static final int TRANSACTION_getCrossProfileCallerIdDisabled = 143;
        static final int TRANSACTION_getCrossProfileCallerIdDisabledForUser = 144;
        static final int TRANSACTION_getCrossProfileContactsSearchDisabled = 146;
        static final int TRANSACTION_getCrossProfileContactsSearchDisabledForUser = 147;
        static final int TRANSACTION_getCrossProfileWidgetProviders = 156;
        static final int TRANSACTION_getCurrentFailedPasswordAttempts = 24;
        static final int TRANSACTION_getDelegatePackages = 92;
        static final int TRANSACTION_getDelegatedScopes = 91;
        static final int TRANSACTION_getDeviceOwnerComponent = 66;
        static final int TRANSACTION_getDeviceOwnerLockScreenInfo = 79;
        static final int TRANSACTION_getDeviceOwnerName = 68;
        static final int TRANSACTION_getDeviceOwnerOrganizationName = 195;
        static final int TRANSACTION_getDeviceOwnerUserId = 70;
        static final int TRANSACTION_getDoNotAskCredentialsOnBoot = 167;
        static final int TRANSACTION_getForceEphemeralUsers = 160;
        static final int TRANSACTION_getGlobalProxyAdmin = 37;
        static final int TRANSACTION_getKeepUninstalledPackages = 177;
        static final int TRANSACTION_getKeyguardDisabledFeatures = 48;
        static final int TRANSACTION_getLastBugReportRequestTime = 220;
        static final int TRANSACTION_getLastNetworkLogRetrievalTime = 221;
        static final int TRANSACTION_getLastSecurityLogRetrievalTime = 219;
        static final int TRANSACTION_getLockTaskPackages = 133;
        static final int TRANSACTION_getLongSupportMessage = 185;
        static final int TRANSACTION_getLongSupportMessageForUser = 187;
        static final int TRANSACTION_getMaximumFailedPasswordsForWipe = 27;
        static final int TRANSACTION_getMaximumTimeToLock = 30;
        static final int TRANSACTION_getMaximumTimeToLockForUserAndProfiles = 31;
        static final int TRANSACTION_getOrganizationColor = 191;
        static final int TRANSACTION_getOrganizationColorForUser = 192;
        static final int TRANSACTION_getOrganizationName = 194;
        static final int TRANSACTION_getOrganizationNameForUser = 196;
        static final int TRANSACTION_getOwnerInstalledCaCerts = 227;
        static final int TRANSACTION_getPasswordExpiration = 21;
        static final int TRANSACTION_getPasswordExpirationTimeout = 20;
        static final int TRANSACTION_getPasswordHistoryLength = 18;
        static final int TRANSACTION_getPasswordMinimumLength = 4;
        static final int TRANSACTION_getPasswordMinimumLetters = 10;
        static final int TRANSACTION_getPasswordMinimumLowerCase = 8;
        static final int TRANSACTION_getPasswordMinimumNonLetter = 16;
        static final int TRANSACTION_getPasswordMinimumNumeric = 12;
        static final int TRANSACTION_getPasswordMinimumSymbols = 14;
        static final int TRANSACTION_getPasswordMinimumUpperCase = 6;
        static final int TRANSACTION_getPasswordQuality = 2;
        static final int TRANSACTION_getPendingSystemUpdate = 169;
        static final int TRANSACTION_getPermissionGrantState = 173;
        static final int TRANSACTION_getPermissionPolicy = 171;
        static final int TRANSACTION_getPermittedAccessibilityServices = 111;
        static final int TRANSACTION_getPermittedAccessibilityServicesForUser = 112;
        static final int TRANSACTION_getPermittedCrossProfileNotificationListeners = 119;
        static final int TRANSACTION_getPermittedInputMethods = 115;
        static final int TRANSACTION_getPermittedInputMethodsForCurrentUser = 116;
        static final int TRANSACTION_getProfileOwner = 72;
        static final int TRANSACTION_getProfileOwnerName = 73;
        static final int TRANSACTION_getProfileWithMinimumFailedPasswordsForWipe = 25;
        static final int TRANSACTION_getRemoveWarning = 53;
        static final int TRANSACTION_getRequiredStrongAuthTimeout = 33;
        static final int TRANSACTION_getRestrictionsProvider = 105;
        static final int TRANSACTION_getScreenCaptureDisabled = 46;
        static final int TRANSACTION_getShortSupportMessage = 183;
        static final int TRANSACTION_getShortSupportMessageForUser = 186;
        static final int TRANSACTION_getStorageEncryption = 40;
        static final int TRANSACTION_getStorageEncryptionStatus = 41;
        static final int TRANSACTION_getSystemUpdatePolicy = 164;
        static final int TRANSACTION_getTrustAgentConfiguration = 153;
        static final int TRANSACTION_getUserProvisioningState = 197;
        static final int TRANSACTION_getUserRestrictions = 107;
        static final int TRANSACTION_getWifiMacAddress = 180;
        static final int TRANSACTION_hasDeviceOwner = 67;
        static final int TRANSACTION_hasGrantedPolicy = 56;
        static final int TRANSACTION_hasUserSetupCompleted = 77;
        static final int TRANSACTION_installCaCert = 82;
        static final int TRANSACTION_installKeyPair = 87;
        static final int TRANSACTION_isAccessibilityServicePermittedByAdmin = 113;
        static final int TRANSACTION_isActivePasswordSufficient = 22;
        static final int TRANSACTION_isAdminActive = 50;
        static final int TRANSACTION_isAffiliatedUser = 201;
        static final int TRANSACTION_isApplicationHidden = 123;
        static final int TRANSACTION_isBackupServiceEnabled = 213;
        static final int TRANSACTION_isCaCertApproved = 86;
        static final int TRANSACTION_isCallerApplicationRestrictionsManagingPackage = 103;
        static final int TRANSACTION_isCurrentInputMethodSetByOwner = 226;
        static final int TRANSACTION_isDeviceProvisioned = 208;
        static final int TRANSACTION_isDeviceProvisioningConfigApplied = 209;
        static final int TRANSACTION_isInputMethodPermittedByAdmin = 117;
        static final int TRANSACTION_isLockTaskPermitted = 134;
        static final int TRANSACTION_isManagedProfile = 178;
        static final int TRANSACTION_isMasterVolumeMuted = 138;
        static final int TRANSACTION_isNetworkLoggingEnabled = 215;
        static final int TRANSACTION_isNotificationListenerServicePermitted = 120;
        static final int TRANSACTION_isPackageSuspended = 81;
        static final int TRANSACTION_isProfileActivePasswordSufficientForParent = 23;
        static final int TRANSACTION_isProvisioningAllowed = 174;
        static final int TRANSACTION_isRemovingAdmin = 161;
        static final int TRANSACTION_isResetPasswordTokenActive = 224;
        static final int TRANSACTION_isSecurityLoggingEnabled = 203;
        static final int TRANSACTION_isSeparateProfileChallengeAllowed = 188;
        static final int TRANSACTION_isSystemOnlyUser = 179;
        static final int TRANSACTION_isUninstallBlocked = 141;
        static final int TRANSACTION_isUninstallInQueue = 206;
        static final int TRANSACTION_lockNow = 34;
        static final int TRANSACTION_notifyLockTaskModeChanged = 139;
        static final int TRANSACTION_notifyPendingSystemUpdate = 168;
        static final int TRANSACTION_packageHasActiveAdmins = 52;
        static final int TRANSACTION_reboot = 181;
        static final int TRANSACTION_removeActiveAdmin = 54;
        static final int TRANSACTION_removeCrossProfileWidgetProvider = 155;
        static final int TRANSACTION_removeKeyPair = 88;
        static final int TRANSACTION_removeUser = 125;
        static final int TRANSACTION_reportFailedFingerprintAttempt = 61;
        static final int TRANSACTION_reportFailedPasswordAttempt = 59;
        static final int TRANSACTION_reportKeyguardDismissed = 63;
        static final int TRANSACTION_reportKeyguardSecured = 64;
        static final int TRANSACTION_reportPasswordChanged = 58;
        static final int TRANSACTION_reportSuccessfulFingerprintAttempt = 62;
        static final int TRANSACTION_reportSuccessfulPasswordAttempt = 60;
        static final int TRANSACTION_requestBugreport = 42;
        static final int TRANSACTION_resetPassword = 28;
        static final int TRANSACTION_resetPasswordWithToken = 225;
        static final int TRANSACTION_retrieveNetworkLogs = 216;
        static final int TRANSACTION_retrievePreRebootSecurityLogs = 205;
        static final int TRANSACTION_retrieveSecurityLogs = 204;
        static final int TRANSACTION_setAccountManagementDisabled = 129;
        static final int TRANSACTION_setActiveAdmin = 49;
        static final int TRANSACTION_setActivePasswordState = 57;
        static final int TRANSACTION_setAffiliationIds = 199;
        static final int TRANSACTION_setAlwaysOnVpnPackage = 95;
        static final int TRANSACTION_setApplicationHidden = 122;
        static final int TRANSACTION_setApplicationRestrictions = 99;
        static final int TRANSACTION_setApplicationRestrictionsManagingPackage = 101;
        static final int TRANSACTION_setAutoTimeRequired = 157;
        static final int TRANSACTION_setBackupServiceEnabled = 212;
        static final int TRANSACTION_setBluetoothContactSharingDisabled = 149;
        static final int TRANSACTION_setCameraDisabled = 43;
        static final int TRANSACTION_setCertInstallerPackage = 93;
        static final int TRANSACTION_setCrossProfileCallerIdDisabled = 142;
        static final int TRANSACTION_setCrossProfileContactsSearchDisabled = 145;
        static final int TRANSACTION_setDelegatedScopes = 90;
        static final int TRANSACTION_setDeviceOwner = 65;
        static final int TRANSACTION_setDeviceOwnerLockScreenInfo = 78;
        static final int TRANSACTION_setDeviceProvisioningConfigApplied = 210;
        static final int TRANSACTION_setForceEphemeralUsers = 159;
        static final int TRANSACTION_setGlobalProxy = 36;
        static final int TRANSACTION_setGlobalSetting = 135;
        static final int TRANSACTION_setKeepUninstalledPackages = 176;
        static final int TRANSACTION_setKeyguardDisabled = 165;
        static final int TRANSACTION_setKeyguardDisabledFeatures = 47;
        static final int TRANSACTION_setLockTaskPackages = 132;
        static final int TRANSACTION_setLongSupportMessage = 184;
        static final int TRANSACTION_setMasterVolumeMuted = 137;
        static final int TRANSACTION_setMaximumFailedPasswordsForWipe = 26;
        static final int TRANSACTION_setMaximumTimeToLock = 29;
        static final int TRANSACTION_setNetworkLoggingEnabled = 214;
        static final int TRANSACTION_setOrganizationColor = 189;
        static final int TRANSACTION_setOrganizationColorForUser = 190;
        static final int TRANSACTION_setOrganizationName = 193;
        static final int TRANSACTION_setPackagesSuspended = 80;
        static final int TRANSACTION_setPasswordExpirationTimeout = 19;
        static final int TRANSACTION_setPasswordHistoryLength = 17;
        static final int TRANSACTION_setPasswordMinimumLength = 3;
        static final int TRANSACTION_setPasswordMinimumLetters = 9;
        static final int TRANSACTION_setPasswordMinimumLowerCase = 7;
        static final int TRANSACTION_setPasswordMinimumNonLetter = 15;
        static final int TRANSACTION_setPasswordMinimumNumeric = 11;
        static final int TRANSACTION_setPasswordMinimumSymbols = 13;
        static final int TRANSACTION_setPasswordMinimumUpperCase = 5;
        static final int TRANSACTION_setPasswordQuality = 1;
        static final int TRANSACTION_setPermissionGrantState = 172;
        static final int TRANSACTION_setPermissionPolicy = 170;
        static final int TRANSACTION_setPermittedAccessibilityServices = 110;
        static final int TRANSACTION_setPermittedCrossProfileNotificationListeners = 118;
        static final int TRANSACTION_setPermittedInputMethods = 114;
        static final int TRANSACTION_setProfileEnabled = 74;
        static final int TRANSACTION_setProfileName = 75;
        static final int TRANSACTION_setProfileOwner = 71;
        static final int TRANSACTION_setRecommendedGlobalProxy = 38;
        static final int TRANSACTION_setRequiredStrongAuthTimeout = 32;
        static final int TRANSACTION_setResetPasswordToken = 222;
        static final int TRANSACTION_setRestrictionsProvider = 104;
        static final int TRANSACTION_setScreenCaptureDisabled = 45;
        static final int TRANSACTION_setSecureSetting = 136;
        static final int TRANSACTION_setSecurityLoggingEnabled = 202;
        static final int TRANSACTION_setShortSupportMessage = 182;
        static final int TRANSACTION_setStatusBarDisabled = 166;
        static final int TRANSACTION_setStorageEncryption = 39;
        static final int TRANSACTION_setSystemUpdatePolicy = 163;
        static final int TRANSACTION_setTrustAgentConfiguration = 152;
        static final int TRANSACTION_setUninstallBlocked = 140;
        static final int TRANSACTION_setUserIcon = 162;
        static final int TRANSACTION_setUserProvisioningState = 198;
        static final int TRANSACTION_setUserRestriction = 106;
        static final int TRANSACTION_startManagedQuickContact = 148;
        static final int TRANSACTION_switchUser = 126;
        static final int TRANSACTION_uninstallCaCerts = 83;
        static final int TRANSACTION_uninstallPackageWithActiveAdmins = 207;
        static final int TRANSACTION_wipeData = 35;

        public Stub()
        {
            attachInterface(this, "android.app.admin.IDevicePolicyManager");
        }
    }

    private static class Stub.Proxy
        implements IDevicePolicyManager
    {

        public void addCrossProfileIntentFilter(ComponentName componentname, IntentFilter intentfilter, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(intentfilter == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            intentfilter.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(108, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean addCrossProfileWidgetProvider(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(154, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void addPersistentPreferredActivity(ComponentName componentname, IntentFilter intentfilter, ComponentName componentname1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L5:
            if(intentfilter == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            intentfilter.writeToParcel(parcel, 0);
_L6:
            if(componentname1 == null)
                break MISSING_BLOCK_LABEL_132;
            parcel.writeInt(1);
            componentname1.writeToParcel(parcel, 0);
_L7:
            mRemote.transact(97, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public boolean approveCaCert(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(85, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean bindDeviceAdminServiceAsUser(ComponentName componentname, IApplicationThread iapplicationthread, IBinder ibinder, Intent intent, IServiceConnection iserviceconnection, int i, int j)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            Parcel parcel1;
            obj = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L5:
            if(iapplicationthread == null) goto _L4; else goto _L3
_L3:
            componentname = iapplicationthread.asBinder();
_L6:
            parcel.writeStrongBinder(componentname);
            parcel.writeStrongBinder(ibinder);
            if(intent == null)
                break MISSING_BLOCK_LABEL_193;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L7:
            componentname = obj;
            if(iserviceconnection == null)
                break MISSING_BLOCK_LABEL_95;
            componentname = iserviceconnection.asBinder();
            parcel.writeStrongBinder(componentname);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(217, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
              goto _L5
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
_L4:
            componentname = null;
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public int checkProvisioningPreCondition(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(175, parcel, parcel1, 0);
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

        public void choosePrivateKeyAlias(int i, Uri uri, String s, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            if(uri == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            uri.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(89, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            uri;
            parcel1.recycle();
            parcel.recycle();
            throw uri;
        }

        public void clearCrossProfileIntentFilters(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(109, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void clearDeviceOwner(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            mRemote.transact(69, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearPackagePersistentPreferredActivities(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(98, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void clearProfileOwner(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(76, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean clearResetPasswordToken(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(223, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public Intent createAdminSupportIntent(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            mRemote.transact(121, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (Intent)Intent.CREATOR.createFromParcel(parcel1);
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

        public UserHandle createAndManageUser(ComponentName componentname, String s, ComponentName componentname1, PersistableBundle persistablebundle, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L7:
            parcel.writeString(s);
            if(componentname1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            componentname1.writeToParcel(parcel, 0);
_L8:
            if(persistablebundle == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            persistablebundle.writeToParcel(parcel, 0);
_L9:
            parcel.writeInt(i);
            mRemote.transact(124, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_179;
            componentname = (UserHandle)UserHandle.CREATOR.createFromParcel(parcel1);
_L10:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L7
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            componentname = null;
              goto _L10
        }

        public void enableSystemApp(ComponentName componentname, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(127, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int enableSystemAppWithIntent(ComponentName componentname, String s, Intent intent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(intent == null)
                break MISSING_BLOCK_LABEL_122;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(128, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void enforceCanManageCaCerts(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(84, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void forceRemoveActiveAdmin(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(55, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void forceUpdateUserSetupComplete()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(211, parcel, parcel1, 0);
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

        public String[] getAccountTypesWithManagementDisabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(130, parcel, parcel1, 0);
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

        public String[] getAccountTypesWithManagementDisabledAsUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(131, parcel, parcel1, 0);
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

        public List getActiveAdmins(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(ComponentName.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getAffiliationIds(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(200, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public String getAlwaysOnVpnPackage(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(96, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public Bundle getApplicationRestrictions(ComponentName componentname, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(100, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            componentname = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public String getApplicationRestrictionsManagingPackage(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(102, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean getAutoTimeRequired()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(158, parcel, parcel1, 0);
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

        public List getBindDeviceAdminTargetUsers(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(218, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createTypedArrayList(UserHandle.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean getBluetoothContactSharingDisabled(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(150, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean getBluetoothContactSharingDisabledForUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(151, parcel, parcel1, 0);
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

        public boolean getCameraDisabled(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(44, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public String getCertInstallerPackage(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(94, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean getCrossProfileCallerIdDisabled(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(143, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean getCrossProfileCallerIdDisabledForUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(144, parcel, parcel1, 0);
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

        public boolean getCrossProfileContactsSearchDisabled(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(146, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean getCrossProfileContactsSearchDisabledForUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(147, parcel, parcel1, 0);
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

        public List getCrossProfileWidgetProviders(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(156, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getCurrentFailedPasswordAttempts(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
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

        public List getDelegatePackages(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(92, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public List getDelegatedScopes(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(91, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public ComponentName getDeviceOwnerComponent(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(66, parcel, parcel1, 0);
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

        public CharSequence getDeviceOwnerLockScreenInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(79, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            CharSequence charsequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return charsequence;
_L2:
            charsequence = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getDeviceOwnerName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
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

        public CharSequence getDeviceOwnerOrganizationName()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(195, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            CharSequence charsequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return charsequence;
_L2:
            charsequence = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getDeviceOwnerUserId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
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

        public boolean getDoNotAskCredentialsOnBoot()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(167, parcel, parcel1, 0);
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

        public boolean getForceEphemeralUsers(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(160, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public ComponentName getGlobalProxyAdmin(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(37, parcel, parcel1, 0);
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
            return "android.app.admin.IDevicePolicyManager";
        }

        public List getKeepUninstalledPackages(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(177, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getKeyguardDisabledFeatures(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public long getLastBugReportRequestTime()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(220, parcel, parcel1, 0);
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

        public long getLastNetworkLogRetrievalTime()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(221, parcel, parcel1, 0);
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

        public long getLastSecurityLogRetrievalTime()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(219, parcel, parcel1, 0);
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

        public String[] getLockTaskPackages(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(133, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public CharSequence getLongSupportMessage(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(185, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_98;
            componentname = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public CharSequence getLongSupportMessageForUser(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            mRemote.transact(187, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_110;
            componentname = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public int getMaximumFailedPasswordsForWipe(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public long getMaximumTimeToLock(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_98;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            long l;
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public long getMaximumTimeToLockForUserAndProfiles(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
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

        public int getOrganizationColor(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(191, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getOrganizationColorForUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(192, parcel, parcel1, 0);
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

        public CharSequence getOrganizationName(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(194, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_98;
            componentname = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public CharSequence getOrganizationNameForUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(196, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            CharSequence charsequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return charsequence;
_L2:
            charsequence = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public StringParceledListSlice getOwnerInstalledCaCerts(UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(userhandle == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(227, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_98;
            userhandle = (StringParceledListSlice)StringParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return userhandle;
_L2:
            parcel.writeInt(0);
              goto _L3
            userhandle;
            parcel1.recycle();
            parcel.recycle();
            throw userhandle;
            userhandle = null;
              goto _L4
        }

        public long getPasswordExpiration(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_98;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            long l;
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public long getPasswordExpirationTimeout(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_98;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            long l;
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getPasswordHistoryLength(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getPasswordMinimumLength(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getPasswordMinimumLetters(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getPasswordMinimumLowerCase(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getPasswordMinimumNonLetter(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getPasswordMinimumNumeric(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getPasswordMinimumSymbols(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getPasswordMinimumUpperCase(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getPasswordQuality(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public SystemUpdateInfo getPendingSystemUpdate(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(169, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_98;
            componentname = (SystemUpdateInfo)SystemUpdateInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public int getPermissionGrantState(ComponentName componentname, String s, String s1, String s2)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            mRemote.transact(173, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getPermissionPolicy(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(171, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public List getPermittedAccessibilityServices(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(111, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.readArrayList(getClass().getClassLoader());
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public List getPermittedAccessibilityServicesForUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(112, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.readArrayList(getClass().getClassLoader());
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getPermittedCrossProfileNotificationListeners(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(119, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createStringArrayList();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public List getPermittedInputMethods(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_70;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(115, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.readArrayList(getClass().getClassLoader());
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public List getPermittedInputMethodsForCurrentUser()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(116, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.readArrayList(getClass().getClassLoader());
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ComponentName getProfileOwner(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(72, parcel, parcel1, 0);
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

        public String getProfileOwnerName(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
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

        public int getProfileWithMinimumFailedPasswordsForWipe(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
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

        public void getRemoveWarning(ComponentName componentname, RemoteCallback remotecallback, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(remotecallback == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            remotecallback.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            mRemote.transact(53, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public long getRequiredStrongAuthTimeout(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_98;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            long l;
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public ComponentName getRestrictionsProvider(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(105, parcel, parcel1, 0);
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

        public boolean getScreenCaptureDisabled(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public CharSequence getShortSupportMessage(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(183, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_98;
            componentname = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public CharSequence getShortSupportMessageForUser(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            mRemote.transact(186, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_110;
            componentname = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public boolean getStorageEncryption(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
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
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int getStorageEncryptionStatus(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public SystemUpdatePolicy getSystemUpdatePolicy()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(164, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            SystemUpdatePolicy systemupdatepolicy = (SystemUpdatePolicy)SystemUpdatePolicy.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return systemupdatepolicy;
_L2:
            systemupdatepolicy = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getTrustAgentConfiguration(ComponentName componentname, ComponentName componentname1, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(componentname1 == null)
                break MISSING_BLOCK_LABEL_140;
            parcel.writeInt(1);
            componentname1.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(153, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createTypedArrayList(PersistableBundle.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public int getUserProvisioningState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(197, parcel, parcel1, 0);
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

        public Bundle getUserRestrictions(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(107, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            componentname = (Bundle)Bundle.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public String getWifiMacAddress(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_64;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(180, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean hasDeviceOwner()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(67, parcel, parcel1, 0);
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

        public boolean hasGrantedPolicy(ComponentName componentname, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeInt(j);
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
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean hasUserSetupCompleted()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
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

        public boolean installCaCert(ComponentName componentname, String s, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            mRemote.transact(82, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean installKeyPair(ComponentName componentname, String s, byte abyte0[], byte abyte1[], byte abyte2[], String s1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_132;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            parcel.writeByteArray(abyte2);
            parcel.writeString(s1);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(87, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isAccessibilityServicePermittedByAdmin(ComponentName componentname, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(113, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isActivePasswordSufficient(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public boolean isAdminActive(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(50, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isAffiliatedUser()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(201, parcel, parcel1, 0);
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

        public boolean isApplicationHidden(ComponentName componentname, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(123, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isBackupServiceEnabled(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(213, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isCaCertApproved(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isCallerApplicationRestrictionsManagingPackage(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            mRemote.transact(103, parcel, parcel1, 0);
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

        public boolean isCurrentInputMethodSetByOwner()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(226, parcel, parcel1, 0);
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

        public boolean isDeviceProvisioned()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(208, parcel, parcel1, 0);
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

        public boolean isDeviceProvisioningConfigApplied()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(209, parcel, parcel1, 0);
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

        public boolean isInputMethodPermittedByAdmin(ComponentName componentname, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(117, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isLockTaskPermitted(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isManagedProfile(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(178, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isMasterVolumeMuted(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(138, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isNetworkLoggingEnabled(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(215, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isNotificationListenerServicePermitted(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isPackageSuspended(ComponentName componentname, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(81, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isProfileActivePasswordSufficientForParent(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
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

        public boolean isProvisioningAllowed(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(174, parcel, parcel1, 0);
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

        public boolean isRemovingAdmin(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_82;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(161, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isResetPasswordTokenActive(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(224, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isSecurityLoggingEnabled(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(203, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isSeparateProfileChallengeAllowed(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(188, parcel, parcel1, 0);
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

        public boolean isSystemOnlyUser(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_74;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(179, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isUninstallBlocked(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(141, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean isUninstallInQueue(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            mRemote.transact(206, parcel, parcel1, 0);
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

        public void lockNow(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
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

        public void notifyLockTaskModeChanged(boolean flag, String s, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(139, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void notifyPendingSystemUpdate(SystemUpdateInfo systemupdateinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(systemupdateinfo == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            systemupdateinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(168, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            systemupdateinfo;
            parcel1.recycle();
            parcel.recycle();
            throw systemupdateinfo;
        }

        public boolean packageHasActiveAdmins(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(52, parcel, parcel1, 0);
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

        public void reboot(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_58;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(181, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void removeActiveAdmin(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(54, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean removeCrossProfileWidgetProvider(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(155, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean removeKeyPair(ComponentName componentname, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(88, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean removeUser(ComponentName componentname, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_113;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(125, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void reportFailedFingerprintAttempt(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(61, parcel, parcel1, 0);
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

        public void reportFailedPasswordAttempt(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
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

        public void reportKeyguardDismissed(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(63, parcel, parcel1, 0);
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

        public void reportKeyguardSecured(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(64, parcel, parcel1, 0);
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

        public void reportPasswordChanged(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
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

        public void reportSuccessfulFingerprintAttempt(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(62, parcel, parcel1, 0);
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

        public void reportSuccessfulPasswordAttempt(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            mRemote.transact(60, parcel, parcel1, 0);
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

        public boolean requestBugreport(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean resetPassword(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public boolean resetPasswordWithToken(ComponentName componentname, String s, byte abyte0[], int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_104;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeByteArray(abyte0);
            parcel.writeInt(i);
            mRemote.transact(225, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public List retrieveNetworkLogs(ComponentName componentname, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeLong(l);
            mRemote.transact(216, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createTypedArrayList(NetworkEvent.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public ParceledListSlice retrievePreRebootSecurityLogs(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(205, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_98;
            componentname = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public ParceledListSlice retrieveSecurityLogs(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(204, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_98;
            componentname = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public void setAccountManagementDisabled(ComponentName componentname, String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(129, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setActiveAdmin(ComponentName componentname, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                j = 0;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(49, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setActivePasswordState(PasswordMetrics passwordmetrics, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(passwordmetrics == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            passwordmetrics.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(57, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            passwordmetrics;
            parcel1.recycle();
            parcel.recycle();
            throw passwordmetrics;
        }

        public void setAffiliationIds(ComponentName componentname, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringList(list);
            mRemote.transact(199, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setAlwaysOnVpnPackage(ComponentName componentname, String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_102;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(95, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setApplicationHidden(ComponentName componentname, String s, String s1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeString(s1);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(122, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setApplicationRestrictions(ComponentName componentname, String s, String s1, Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeString(s1);
            if(bundle == null)
                break MISSING_BLOCK_LABEL_120;
            parcel.writeInt(1);
            bundle.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(99, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean setApplicationRestrictionsManagingPackage(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_83;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeString(s);
            mRemote.transact(101, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setAutoTimeRequired(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(157, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setBackupServiceEnabled(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(212, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setBluetoothContactSharingDisabled(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setCameraDisabled(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setCertInstallerPackage(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(93, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setCrossProfileCallerIdDisabled(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(142, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setCrossProfileContactsSearchDisabled(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(145, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setDelegatedScopes(ComponentName componentname, String s, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeStringList(list);
            mRemote.transact(90, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setDeviceOwner(ComponentName componentname, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(65, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setDeviceOwnerLockScreenInfo(ComponentName componentname, CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L4:
            mRemote.transact(78, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void setDeviceProvisioningConfigApplied()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            mRemote.transact(210, parcel, parcel1, 0);
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

        public void setForceEphemeralUsers(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(159, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public ComponentName setGlobalProxy(ComponentName componentname, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_124;
            componentname = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return componentname;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            componentname = null;
              goto _L4
        }

        public void setGlobalSetting(ComponentName componentname, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(135, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setKeepUninstalledPackages(ComponentName componentname, String s, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeStringList(list);
            mRemote.transact(176, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setKeyguardDisabled(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(165, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setKeyguardDisabledFeatures(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setLockTaskPackages(ComponentName componentname, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            mRemote.transact(132, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setLongSupportMessage(ComponentName componentname, CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_97;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L4:
            mRemote.transact(184, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void setMasterVolumeMuted(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(137, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setMaximumFailedPasswordsForWipe(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setMaximumTimeToLock(ComponentName componentname, long l, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeLong(l);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setNetworkLoggingEnabled(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(214, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setOrganizationColor(ComponentName componentname, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_67;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(189, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setOrganizationColorForUser(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(190, parcel, parcel1, 0);
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

        public void setOrganizationName(ComponentName componentname, CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_97;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L4:
            mRemote.transact(193, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public String[] setPackagesSuspended(ComponentName componentname, String s, String as[], boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_101;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeStringArray(as);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(80, parcel, parcel1, 0);
            parcel1.readException();
            componentname = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return componentname;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPasswordExpirationTimeout(ComponentName componentname, long l, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeLong(l);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPasswordHistoryLength(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPasswordMinimumLength(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPasswordMinimumLetters(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPasswordMinimumLowerCase(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPasswordMinimumNonLetter(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPasswordMinimumNumeric(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPasswordMinimumSymbols(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_89;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPasswordMinimumUpperCase(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPasswordQuality(ComponentName componentname, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setPermissionGrantState(ComponentName componentname, String s, String s1, String s2, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_111;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeInt(i);
            mRemote.transact(172, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setPermissionPolicy(ComponentName componentname, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(170, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setPermittedAccessibilityServices(ComponentName componentname, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_83;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeList(list);
            mRemote.transact(110, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setPermittedCrossProfileNotificationListeners(ComponentName componentname, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_83;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeStringList(list);
            mRemote.transact(118, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setPermittedInputMethods(ComponentName componentname, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_83;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeList(list);
            mRemote.transact(114, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setProfileEnabled(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(74, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setProfileName(ComponentName componentname, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(75, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setProfileOwner(ComponentName componentname, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(71, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setRecommendedGlobalProxy(ComponentName componentname, ProxyInfo proxyinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(proxyinfo == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            proxyinfo.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void setRequiredStrongAuthTimeout(ComponentName componentname, long l, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeLong(l);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public boolean setResetPasswordToken(ComponentName componentname, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_84;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeByteArray(abyte0);
            mRemote.transact(222, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setRestrictionsProvider(ComponentName componentname, ComponentName componentname1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(componentname1 == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            componentname1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(104, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void setScreenCaptureDisabled(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setSecureSetting(ComponentName componentname, String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(136, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setSecurityLoggingEnabled(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(202, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setShortSupportMessage(ComponentName componentname, CharSequence charsequence)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(charsequence == null)
                break MISSING_BLOCK_LABEL_97;
            parcel.writeInt(1);
            TextUtils.writeToParcel(charsequence, parcel, 0);
_L4:
            mRemote.transact(182, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean setStatusBarDisabled(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(166, parcel, parcel1, 0);
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
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public int setStorageEncryption(ComponentName componentname, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_86;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setSystemUpdatePolicy(ComponentName componentname, SystemUpdatePolicy systemupdatepolicy)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(systemupdatepolicy == null)
                break MISSING_BLOCK_LABEL_97;
            parcel.writeInt(1);
            systemupdatepolicy.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(163, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void setTrustAgentConfiguration(ComponentName componentname, ComponentName componentname1, PersistableBundle persistablebundle, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L5:
            if(componentname1 == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            componentname1.writeToParcel(parcel, 0);
_L6:
            if(persistablebundle == null)
                break MISSING_BLOCK_LABEL_148;
            parcel.writeInt(1);
            persistablebundle.writeToParcel(parcel, 0);
_L7:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(152, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void setUninstallBlocked(ComponentName componentname, String s, String s1, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_95;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeString(s1);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(140, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void setUserIcon(ComponentName componentname, Bitmap bitmap)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(bitmap == null)
                break MISSING_BLOCK_LABEL_97;
            parcel.writeInt(1);
            bitmap.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(162, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void setUserProvisioningState(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(198, parcel, parcel1, 0);
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

        public void setUserRestriction(ComponentName componentname, String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_87;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(106, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void startManagedQuickContact(String s, long l, boolean flag, long l1, Intent intent)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            parcel.writeLong(l);
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            parcel.writeLong(l1);
            if(intent == null)
                break MISSING_BLOCK_LABEL_110;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(148, parcel, parcel1, 0);
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

        public boolean switchUser(ComponentName componentname, UserHandle userhandle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L3:
            if(userhandle == null)
                break MISSING_BLOCK_LABEL_113;
            parcel.writeInt(1);
            userhandle.writeToParcel(parcel, 0);
_L4:
            int i;
            mRemote.transact(126, parcel, parcel1, 0);
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
_L2:
            parcel.writeInt(0);
              goto _L3
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
            parcel.writeInt(0);
              goto _L4
        }

        public void uninstallCaCerts(ComponentName componentname, String s, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeStringArray(as);
            mRemote.transact(83, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        public void uninstallPackageWithActiveAdmins(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
            parcel.writeString(s);
            mRemote.transact(207, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void wipeData(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.app.admin.IDevicePolicyManager");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addCrossProfileIntentFilter(ComponentName componentname, IntentFilter intentfilter, int i)
        throws RemoteException;

    public abstract boolean addCrossProfileWidgetProvider(ComponentName componentname, String s)
        throws RemoteException;

    public abstract void addPersistentPreferredActivity(ComponentName componentname, IntentFilter intentfilter, ComponentName componentname1)
        throws RemoteException;

    public abstract boolean approveCaCert(String s, int i, boolean flag)
        throws RemoteException;

    public abstract boolean bindDeviceAdminServiceAsUser(ComponentName componentname, IApplicationThread iapplicationthread, IBinder ibinder, Intent intent, IServiceConnection iserviceconnection, int i, int j)
        throws RemoteException;

    public abstract int checkProvisioningPreCondition(String s, String s1)
        throws RemoteException;

    public abstract void choosePrivateKeyAlias(int i, Uri uri, String s, IBinder ibinder)
        throws RemoteException;

    public abstract void clearCrossProfileIntentFilters(ComponentName componentname)
        throws RemoteException;

    public abstract void clearDeviceOwner(String s)
        throws RemoteException;

    public abstract void clearPackagePersistentPreferredActivities(ComponentName componentname, String s)
        throws RemoteException;

    public abstract void clearProfileOwner(ComponentName componentname)
        throws RemoteException;

    public abstract boolean clearResetPasswordToken(ComponentName componentname)
        throws RemoteException;

    public abstract Intent createAdminSupportIntent(String s)
        throws RemoteException;

    public abstract UserHandle createAndManageUser(ComponentName componentname, String s, ComponentName componentname1, PersistableBundle persistablebundle, int i)
        throws RemoteException;

    public abstract void enableSystemApp(ComponentName componentname, String s, String s1)
        throws RemoteException;

    public abstract int enableSystemAppWithIntent(ComponentName componentname, String s, Intent intent)
        throws RemoteException;

    public abstract void enforceCanManageCaCerts(ComponentName componentname, String s)
        throws RemoteException;

    public abstract void forceRemoveActiveAdmin(ComponentName componentname, int i)
        throws RemoteException;

    public abstract void forceUpdateUserSetupComplete()
        throws RemoteException;

    public abstract String[] getAccountTypesWithManagementDisabled()
        throws RemoteException;

    public abstract String[] getAccountTypesWithManagementDisabledAsUser(int i)
        throws RemoteException;

    public abstract List getActiveAdmins(int i)
        throws RemoteException;

    public abstract List getAffiliationIds(ComponentName componentname)
        throws RemoteException;

    public abstract String getAlwaysOnVpnPackage(ComponentName componentname)
        throws RemoteException;

    public abstract Bundle getApplicationRestrictions(ComponentName componentname, String s, String s1)
        throws RemoteException;

    public abstract String getApplicationRestrictionsManagingPackage(ComponentName componentname)
        throws RemoteException;

    public abstract boolean getAutoTimeRequired()
        throws RemoteException;

    public abstract List getBindDeviceAdminTargetUsers(ComponentName componentname)
        throws RemoteException;

    public abstract boolean getBluetoothContactSharingDisabled(ComponentName componentname)
        throws RemoteException;

    public abstract boolean getBluetoothContactSharingDisabledForUser(int i)
        throws RemoteException;

    public abstract boolean getCameraDisabled(ComponentName componentname, int i)
        throws RemoteException;

    public abstract String getCertInstallerPackage(ComponentName componentname)
        throws RemoteException;

    public abstract boolean getCrossProfileCallerIdDisabled(ComponentName componentname)
        throws RemoteException;

    public abstract boolean getCrossProfileCallerIdDisabledForUser(int i)
        throws RemoteException;

    public abstract boolean getCrossProfileContactsSearchDisabled(ComponentName componentname)
        throws RemoteException;

    public abstract boolean getCrossProfileContactsSearchDisabledForUser(int i)
        throws RemoteException;

    public abstract List getCrossProfileWidgetProviders(ComponentName componentname)
        throws RemoteException;

    public abstract int getCurrentFailedPasswordAttempts(int i, boolean flag)
        throws RemoteException;

    public abstract List getDelegatePackages(ComponentName componentname, String s)
        throws RemoteException;

    public abstract List getDelegatedScopes(ComponentName componentname, String s)
        throws RemoteException;

    public abstract ComponentName getDeviceOwnerComponent(boolean flag)
        throws RemoteException;

    public abstract CharSequence getDeviceOwnerLockScreenInfo()
        throws RemoteException;

    public abstract String getDeviceOwnerName()
        throws RemoteException;

    public abstract CharSequence getDeviceOwnerOrganizationName()
        throws RemoteException;

    public abstract int getDeviceOwnerUserId()
        throws RemoteException;

    public abstract boolean getDoNotAskCredentialsOnBoot()
        throws RemoteException;

    public abstract boolean getForceEphemeralUsers(ComponentName componentname)
        throws RemoteException;

    public abstract ComponentName getGlobalProxyAdmin(int i)
        throws RemoteException;

    public abstract List getKeepUninstalledPackages(ComponentName componentname, String s)
        throws RemoteException;

    public abstract int getKeyguardDisabledFeatures(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract long getLastBugReportRequestTime()
        throws RemoteException;

    public abstract long getLastNetworkLogRetrievalTime()
        throws RemoteException;

    public abstract long getLastSecurityLogRetrievalTime()
        throws RemoteException;

    public abstract String[] getLockTaskPackages(ComponentName componentname)
        throws RemoteException;

    public abstract CharSequence getLongSupportMessage(ComponentName componentname)
        throws RemoteException;

    public abstract CharSequence getLongSupportMessageForUser(ComponentName componentname, int i)
        throws RemoteException;

    public abstract int getMaximumFailedPasswordsForWipe(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract long getMaximumTimeToLock(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract long getMaximumTimeToLockForUserAndProfiles(int i)
        throws RemoteException;

    public abstract int getOrganizationColor(ComponentName componentname)
        throws RemoteException;

    public abstract int getOrganizationColorForUser(int i)
        throws RemoteException;

    public abstract CharSequence getOrganizationName(ComponentName componentname)
        throws RemoteException;

    public abstract CharSequence getOrganizationNameForUser(int i)
        throws RemoteException;

    public abstract StringParceledListSlice getOwnerInstalledCaCerts(UserHandle userhandle)
        throws RemoteException;

    public abstract long getPasswordExpiration(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract long getPasswordExpirationTimeout(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract int getPasswordHistoryLength(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract int getPasswordMinimumLength(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract int getPasswordMinimumLetters(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract int getPasswordMinimumLowerCase(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract int getPasswordMinimumNonLetter(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract int getPasswordMinimumNumeric(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract int getPasswordMinimumSymbols(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract int getPasswordMinimumUpperCase(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract int getPasswordQuality(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract SystemUpdateInfo getPendingSystemUpdate(ComponentName componentname)
        throws RemoteException;

    public abstract int getPermissionGrantState(ComponentName componentname, String s, String s1, String s2)
        throws RemoteException;

    public abstract int getPermissionPolicy(ComponentName componentname)
        throws RemoteException;

    public abstract List getPermittedAccessibilityServices(ComponentName componentname)
        throws RemoteException;

    public abstract List getPermittedAccessibilityServicesForUser(int i)
        throws RemoteException;

    public abstract List getPermittedCrossProfileNotificationListeners(ComponentName componentname)
        throws RemoteException;

    public abstract List getPermittedInputMethods(ComponentName componentname)
        throws RemoteException;

    public abstract List getPermittedInputMethodsForCurrentUser()
        throws RemoteException;

    public abstract ComponentName getProfileOwner(int i)
        throws RemoteException;

    public abstract String getProfileOwnerName(int i)
        throws RemoteException;

    public abstract int getProfileWithMinimumFailedPasswordsForWipe(int i, boolean flag)
        throws RemoteException;

    public abstract void getRemoveWarning(ComponentName componentname, RemoteCallback remotecallback, int i)
        throws RemoteException;

    public abstract long getRequiredStrongAuthTimeout(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract ComponentName getRestrictionsProvider(int i)
        throws RemoteException;

    public abstract boolean getScreenCaptureDisabled(ComponentName componentname, int i)
        throws RemoteException;

    public abstract CharSequence getShortSupportMessage(ComponentName componentname)
        throws RemoteException;

    public abstract CharSequence getShortSupportMessageForUser(ComponentName componentname, int i)
        throws RemoteException;

    public abstract boolean getStorageEncryption(ComponentName componentname, int i)
        throws RemoteException;

    public abstract int getStorageEncryptionStatus(String s, int i)
        throws RemoteException;

    public abstract SystemUpdatePolicy getSystemUpdatePolicy()
        throws RemoteException;

    public abstract List getTrustAgentConfiguration(ComponentName componentname, ComponentName componentname1, int i, boolean flag)
        throws RemoteException;

    public abstract int getUserProvisioningState()
        throws RemoteException;

    public abstract Bundle getUserRestrictions(ComponentName componentname)
        throws RemoteException;

    public abstract String getWifiMacAddress(ComponentName componentname)
        throws RemoteException;

    public abstract boolean hasDeviceOwner()
        throws RemoteException;

    public abstract boolean hasGrantedPolicy(ComponentName componentname, int i, int j)
        throws RemoteException;

    public abstract boolean hasUserSetupCompleted()
        throws RemoteException;

    public abstract boolean installCaCert(ComponentName componentname, String s, byte abyte0[])
        throws RemoteException;

    public abstract boolean installKeyPair(ComponentName componentname, String s, byte abyte0[], byte abyte1[], byte abyte2[], String s1, boolean flag)
        throws RemoteException;

    public abstract boolean isAccessibilityServicePermittedByAdmin(ComponentName componentname, String s, int i)
        throws RemoteException;

    public abstract boolean isActivePasswordSufficient(int i, boolean flag)
        throws RemoteException;

    public abstract boolean isAdminActive(ComponentName componentname, int i)
        throws RemoteException;

    public abstract boolean isAffiliatedUser()
        throws RemoteException;

    public abstract boolean isApplicationHidden(ComponentName componentname, String s, String s1)
        throws RemoteException;

    public abstract boolean isBackupServiceEnabled(ComponentName componentname)
        throws RemoteException;

    public abstract boolean isCaCertApproved(String s, int i)
        throws RemoteException;

    public abstract boolean isCallerApplicationRestrictionsManagingPackage(String s)
        throws RemoteException;

    public abstract boolean isCurrentInputMethodSetByOwner()
        throws RemoteException;

    public abstract boolean isDeviceProvisioned()
        throws RemoteException;

    public abstract boolean isDeviceProvisioningConfigApplied()
        throws RemoteException;

    public abstract boolean isInputMethodPermittedByAdmin(ComponentName componentname, String s, int i)
        throws RemoteException;

    public abstract boolean isLockTaskPermitted(String s)
        throws RemoteException;

    public abstract boolean isManagedProfile(ComponentName componentname)
        throws RemoteException;

    public abstract boolean isMasterVolumeMuted(ComponentName componentname)
        throws RemoteException;

    public abstract boolean isNetworkLoggingEnabled(ComponentName componentname)
        throws RemoteException;

    public abstract boolean isNotificationListenerServicePermitted(String s, int i)
        throws RemoteException;

    public abstract boolean isPackageSuspended(ComponentName componentname, String s, String s1)
        throws RemoteException;

    public abstract boolean isProfileActivePasswordSufficientForParent(int i)
        throws RemoteException;

    public abstract boolean isProvisioningAllowed(String s, String s1)
        throws RemoteException;

    public abstract boolean isRemovingAdmin(ComponentName componentname, int i)
        throws RemoteException;

    public abstract boolean isResetPasswordTokenActive(ComponentName componentname)
        throws RemoteException;

    public abstract boolean isSecurityLoggingEnabled(ComponentName componentname)
        throws RemoteException;

    public abstract boolean isSeparateProfileChallengeAllowed(int i)
        throws RemoteException;

    public abstract boolean isSystemOnlyUser(ComponentName componentname)
        throws RemoteException;

    public abstract boolean isUninstallBlocked(ComponentName componentname, String s)
        throws RemoteException;

    public abstract boolean isUninstallInQueue(String s)
        throws RemoteException;

    public abstract void lockNow(int i, boolean flag)
        throws RemoteException;

    public abstract void notifyLockTaskModeChanged(boolean flag, String s, int i)
        throws RemoteException;

    public abstract void notifyPendingSystemUpdate(SystemUpdateInfo systemupdateinfo)
        throws RemoteException;

    public abstract boolean packageHasActiveAdmins(String s, int i)
        throws RemoteException;

    public abstract void reboot(ComponentName componentname)
        throws RemoteException;

    public abstract void removeActiveAdmin(ComponentName componentname, int i)
        throws RemoteException;

    public abstract boolean removeCrossProfileWidgetProvider(ComponentName componentname, String s)
        throws RemoteException;

    public abstract boolean removeKeyPair(ComponentName componentname, String s, String s1)
        throws RemoteException;

    public abstract boolean removeUser(ComponentName componentname, UserHandle userhandle)
        throws RemoteException;

    public abstract void reportFailedFingerprintAttempt(int i)
        throws RemoteException;

    public abstract void reportFailedPasswordAttempt(int i)
        throws RemoteException;

    public abstract void reportKeyguardDismissed(int i)
        throws RemoteException;

    public abstract void reportKeyguardSecured(int i)
        throws RemoteException;

    public abstract void reportPasswordChanged(int i)
        throws RemoteException;

    public abstract void reportSuccessfulFingerprintAttempt(int i)
        throws RemoteException;

    public abstract void reportSuccessfulPasswordAttempt(int i)
        throws RemoteException;

    public abstract boolean requestBugreport(ComponentName componentname)
        throws RemoteException;

    public abstract boolean resetPassword(String s, int i)
        throws RemoteException;

    public abstract boolean resetPasswordWithToken(ComponentName componentname, String s, byte abyte0[], int i)
        throws RemoteException;

    public abstract List retrieveNetworkLogs(ComponentName componentname, long l)
        throws RemoteException;

    public abstract ParceledListSlice retrievePreRebootSecurityLogs(ComponentName componentname)
        throws RemoteException;

    public abstract ParceledListSlice retrieveSecurityLogs(ComponentName componentname)
        throws RemoteException;

    public abstract void setAccountManagementDisabled(ComponentName componentname, String s, boolean flag)
        throws RemoteException;

    public abstract void setActiveAdmin(ComponentName componentname, boolean flag, int i)
        throws RemoteException;

    public abstract void setActivePasswordState(PasswordMetrics passwordmetrics, int i)
        throws RemoteException;

    public abstract void setAffiliationIds(ComponentName componentname, List list)
        throws RemoteException;

    public abstract boolean setAlwaysOnVpnPackage(ComponentName componentname, String s, boolean flag)
        throws RemoteException;

    public abstract boolean setApplicationHidden(ComponentName componentname, String s, String s1, boolean flag)
        throws RemoteException;

    public abstract void setApplicationRestrictions(ComponentName componentname, String s, String s1, Bundle bundle)
        throws RemoteException;

    public abstract boolean setApplicationRestrictionsManagingPackage(ComponentName componentname, String s)
        throws RemoteException;

    public abstract void setAutoTimeRequired(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setBackupServiceEnabled(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setBluetoothContactSharingDisabled(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setCameraDisabled(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setCertInstallerPackage(ComponentName componentname, String s)
        throws RemoteException;

    public abstract void setCrossProfileCallerIdDisabled(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setCrossProfileContactsSearchDisabled(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setDelegatedScopes(ComponentName componentname, String s, List list)
        throws RemoteException;

    public abstract boolean setDeviceOwner(ComponentName componentname, String s, int i)
        throws RemoteException;

    public abstract void setDeviceOwnerLockScreenInfo(ComponentName componentname, CharSequence charsequence)
        throws RemoteException;

    public abstract void setDeviceProvisioningConfigApplied()
        throws RemoteException;

    public abstract void setForceEphemeralUsers(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract ComponentName setGlobalProxy(ComponentName componentname, String s, String s1)
        throws RemoteException;

    public abstract void setGlobalSetting(ComponentName componentname, String s, String s1)
        throws RemoteException;

    public abstract void setKeepUninstalledPackages(ComponentName componentname, String s, List list)
        throws RemoteException;

    public abstract boolean setKeyguardDisabled(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setKeyguardDisabledFeatures(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setLockTaskPackages(ComponentName componentname, String as[])
        throws RemoteException;

    public abstract void setLongSupportMessage(ComponentName componentname, CharSequence charsequence)
        throws RemoteException;

    public abstract void setMasterVolumeMuted(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setMaximumFailedPasswordsForWipe(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setMaximumTimeToLock(ComponentName componentname, long l, boolean flag)
        throws RemoteException;

    public abstract void setNetworkLoggingEnabled(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setOrganizationColor(ComponentName componentname, int i)
        throws RemoteException;

    public abstract void setOrganizationColorForUser(int i, int j)
        throws RemoteException;

    public abstract void setOrganizationName(ComponentName componentname, CharSequence charsequence)
        throws RemoteException;

    public abstract String[] setPackagesSuspended(ComponentName componentname, String s, String as[], boolean flag)
        throws RemoteException;

    public abstract void setPasswordExpirationTimeout(ComponentName componentname, long l, boolean flag)
        throws RemoteException;

    public abstract void setPasswordHistoryLength(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setPasswordMinimumLength(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setPasswordMinimumLetters(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setPasswordMinimumLowerCase(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setPasswordMinimumNonLetter(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setPasswordMinimumNumeric(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setPasswordMinimumSymbols(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setPasswordMinimumUpperCase(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract void setPasswordQuality(ComponentName componentname, int i, boolean flag)
        throws RemoteException;

    public abstract boolean setPermissionGrantState(ComponentName componentname, String s, String s1, String s2, int i)
        throws RemoteException;

    public abstract void setPermissionPolicy(ComponentName componentname, String s, int i)
        throws RemoteException;

    public abstract boolean setPermittedAccessibilityServices(ComponentName componentname, List list)
        throws RemoteException;

    public abstract boolean setPermittedCrossProfileNotificationListeners(ComponentName componentname, List list)
        throws RemoteException;

    public abstract boolean setPermittedInputMethods(ComponentName componentname, List list)
        throws RemoteException;

    public abstract void setProfileEnabled(ComponentName componentname)
        throws RemoteException;

    public abstract void setProfileName(ComponentName componentname, String s)
        throws RemoteException;

    public abstract boolean setProfileOwner(ComponentName componentname, String s, int i)
        throws RemoteException;

    public abstract void setRecommendedGlobalProxy(ComponentName componentname, ProxyInfo proxyinfo)
        throws RemoteException;

    public abstract void setRequiredStrongAuthTimeout(ComponentName componentname, long l, boolean flag)
        throws RemoteException;

    public abstract boolean setResetPasswordToken(ComponentName componentname, byte abyte0[])
        throws RemoteException;

    public abstract void setRestrictionsProvider(ComponentName componentname, ComponentName componentname1)
        throws RemoteException;

    public abstract void setScreenCaptureDisabled(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setSecureSetting(ComponentName componentname, String s, String s1)
        throws RemoteException;

    public abstract void setSecurityLoggingEnabled(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setShortSupportMessage(ComponentName componentname, CharSequence charsequence)
        throws RemoteException;

    public abstract boolean setStatusBarDisabled(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract int setStorageEncryption(ComponentName componentname, boolean flag)
        throws RemoteException;

    public abstract void setSystemUpdatePolicy(ComponentName componentname, SystemUpdatePolicy systemupdatepolicy)
        throws RemoteException;

    public abstract void setTrustAgentConfiguration(ComponentName componentname, ComponentName componentname1, PersistableBundle persistablebundle, boolean flag)
        throws RemoteException;

    public abstract void setUninstallBlocked(ComponentName componentname, String s, String s1, boolean flag)
        throws RemoteException;

    public abstract void setUserIcon(ComponentName componentname, Bitmap bitmap)
        throws RemoteException;

    public abstract void setUserProvisioningState(int i, int j)
        throws RemoteException;

    public abstract void setUserRestriction(ComponentName componentname, String s, boolean flag)
        throws RemoteException;

    public abstract void startManagedQuickContact(String s, long l, boolean flag, long l1, Intent intent)
        throws RemoteException;

    public abstract boolean switchUser(ComponentName componentname, UserHandle userhandle)
        throws RemoteException;

    public abstract void uninstallCaCerts(ComponentName componentname, String s, String as[])
        throws RemoteException;

    public abstract void uninstallPackageWithActiveAdmins(String s)
        throws RemoteException;

    public abstract void wipeData(int i)
        throws RemoteException;
}
