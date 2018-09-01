// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.security;

import android.content.Intent;
import android.content.pm.ParceledListSlice;
import android.graphics.Bitmap;
import android.os.*;
import com.android.internal.app.IWakePathCallback;
import java.util.List;

// Referenced classes of package miui.security:
//            ISecurityCallback

public interface ISecurityManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ISecurityManager
    {

        public static ISecurityManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("miui.security.ISecurityManager");
            if(iinterface != null && (iinterface instanceof ISecurityManager))
                return (ISecurityManager)iinterface;
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
                parcel1.writeString("miui.security.ISecurityManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("miui.security.ISecurityManager");
                killNativePackageProcesses(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("miui.security.ISecurityManager");
                parcel = getPackageNameByPid(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = checkSmsBlocked(parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
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
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag1 = startInterceptSmsBySender(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag2 = stopInterceptSmsBySender();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("miui.security.ISecurityManager");
                addAccessControlPass(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("miui.security.ISecurityManager");
                removeAccessControlPass(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s = parcel.readString();
                boolean flag3;
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag3 = checkAccessControlPass(s, parcel);
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag4 = getApplicationAccessControlEnabled(parcel.readString());
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s1 = parcel.readString();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                setApplicationAccessControlEnabled(s1, flag5);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag6 = getApplicationChildrenControlEnabled(parcel.readString());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s2 = parcel.readString();
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                setApplicationChildrenControlEnabled(s2, flag7);
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("miui.security.ISecurityManager");
                setWakeUpTime(parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("miui.security.ISecurityManager");
                long l = getWakeUpTime(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag8 = putSystemDataStringFile(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("miui.security.ISecurityManager");
                parcel = readSystemDataStringFile(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("miui.security.ISecurityManager");
                i = parcel.readInt();
                ParceledListSlice parceledlistslice;
                if(parcel.readInt() != 0)
                    parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel);
                else
                    parceledlistslice = null;
                pushWakePathData(i, parceledlistslice, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("miui.security.ISecurityManager");
                pushWakePathWhiteList(parcel.createStringArrayList(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                setTrackWakePathCallListLogEnabled(flag9);
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("miui.security.ISecurityManager");
                parcel = getWakePathCallListLog();
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

            case 21: // '\025'
                parcel.enforceInterface("miui.security.ISecurityManager");
                i = getAppPermissionControlOpen(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("miui.security.ISecurityManager");
                setAppPermissionControlOpen(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("miui.security.ISecurityManager");
                registerWakePathCallback(com.android.internal.app.IWakePathCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("miui.security.ISecurityManager");
                removeAccessControlPassAsUser(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag10 = needFinishAccessControl(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("miui.security.ISecurityManager");
                finishAccessControl(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("miui.security.ISecurityManager");
                if(parcel.readInt() != 0)
                    parcel = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = activityResume(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag11;
                if(parcel.readInt() != 0)
                    flag11 = true;
                else
                    flag11 = false;
                setCoreRuntimePermissionEnabled(flag11, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("miui.security.ISecurityManager");
                grantRuntimePermission(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("miui.security.ISecurityManager");
                i = getCurrentUserId();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s8 = parcel.readString();
                boolean flag12;
                Intent intent;
                if(parcel.readInt() != 0)
                    intent = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent = null;
                flag12 = checkAccessControlPassAsUser(s8, intent, parcel.readInt());
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 32: // ' '
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag13 = getApplicationAccessControlEnabledAsUser(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 33: // '!'
                parcel.enforceInterface("miui.security.ISecurityManager");
                removeWakePathData(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s11 = parcel.readString();
                String s9 = parcel.readString();
                boolean flag14;
                Intent intent1;
                if(parcel.readInt() != 0)
                    intent1 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent1 = null;
                flag14 = checkAllowStartActivity(s11, s9, intent1, parcel.readInt());
                parcel1.writeNoException();
                if(flag14)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 35: // '#'
                parcel.enforceInterface("miui.security.ISecurityManager");
                i = getSysAppCracked();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 36: // '$'
                parcel.enforceInterface("miui.security.ISecurityManager");
                grantInstallPermission(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 37: // '%'
                parcel.enforceInterface("miui.security.ISecurityManager");
                pushWakePathConfirmDialogWhiteList(parcel.readInt(), parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("miui.security.ISecurityManager");
                addAccessControlPassForUser(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s3 = parcel.readString();
                boolean flag15;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                setApplicationAccessControlEnabledForUser(s3, flag15, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag16 = isRestrictedAppNet(parcel.readString());
                parcel1.writeNoException();
                if(flag16)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 41: // ')'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag17;
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                flag17 = writeAppHideConfig(flag17);
                parcel1.writeNoException();
                if(flag17)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 42: // '*'
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s4 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (Bitmap)Bitmap.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                saveIcon(s4, parcel);
                parcel1.writeNoException();
                return true;

            case 43: // '+'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag18 = setMiuiFirewallRule(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                if(flag18)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 44: // ','
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag19 = setCurrentNetworkState(parcel.readInt());
                parcel1.writeNoException();
                if(flag19)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 45: // '-'
                parcel.enforceInterface("miui.security.ISecurityManager");
                setIncompatibleAppList(parcel.createStringArrayList());
                parcel1.writeNoException();
                return true;

            case 46: // '.'
                parcel.enforceInterface("miui.security.ISecurityManager");
                parcel = getIncompatibleAppList();
                parcel1.writeNoException();
                parcel1.writeStringList(parcel);
                return true;

            case 47: // '/'
                parcel.enforceInterface("miui.security.ISecurityManager");
                parcel = getWakePathComponents(parcel.readString());
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

            case 48: // '0'
                parcel.enforceInterface("miui.security.ISecurityManager");
                offerGoogleBaseCallBack(ISecurityCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("miui.security.ISecurityManager");
                notifyAppsPreInstalled();
                return true;

            case 50: // '2'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag20 = getApplicationMaskNotificationEnabledAsUser(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 51: // '3'
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s5 = parcel.readString();
                boolean flag21;
                if(parcel.readInt() != 0)
                    flag21 = true;
                else
                    flag21 = false;
                setApplicationMaskNotificationEnabledForUser(s5, flag21, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 52: // '4'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag22 = areNotificationsEnabledForPackage(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag22)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 53: // '5'
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s6 = parcel.readString();
                i = parcel.readInt();
                boolean flag23;
                if(parcel.readInt() != 0)
                    flag23 = true;
                else
                    flag23 = false;
                setNotificationsEnabledForPackage(s6, i, flag23);
                parcel1.writeNoException();
                return true;

            case 54: // '6'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag24 = isAppHide();
                parcel1.writeNoException();
                if(flag24)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 55: // '7'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag25 = isFunctionOpen();
                parcel1.writeNoException();
                if(flag25)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 56: // '8'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag26;
                if(parcel.readInt() != 0)
                    flag26 = true;
                else
                    flag26 = false;
                flag26 = setAppHide(flag26);
                parcel1.writeNoException();
                if(flag26)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 57: // '9'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag27 = isValidDevice();
                parcel1.writeNoException();
                if(flag27)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 58: // ':'
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s10 = parcel.readString();
                boolean flag28;
                Intent intent2;
                if(parcel.readInt() != 0)
                    intent2 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent2 = null;
                flag28 = checkGameBoosterAntimsgPassAsUser(s10, intent2, parcel.readInt());
                parcel1.writeNoException();
                if(flag28)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 59: // ';'
                parcel.enforceInterface("miui.security.ISecurityManager");
                IBinder ibinder = parcel.readStrongBinder();
                i = parcel.readInt();
                boolean flag29;
                if(parcel.readInt() != 0)
                    flag29 = true;
                else
                    flag29 = false;
                setGameBoosterIBinder(ibinder, i, flag29);
                parcel1.writeNoException();
                return true;

            case 60: // '<'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag30 = getGameMode(parcel.readInt());
                parcel1.writeNoException();
                if(flag30)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 61: // '='
                parcel.enforceInterface("miui.security.ISecurityManager");
                setAccessControlPassword(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 62: // '>'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag31 = checkAccessControlPassword(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag31)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 63: // '?'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag32 = haveAccessControlPassword(parcel.readInt());
                parcel1.writeNoException();
                if(flag32)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 64: // '@'
                parcel.enforceInterface("miui.security.ISecurityManager");
                parcel = getAccessControlPasswordType(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 65: // 'A'
                parcel.enforceInterface("miui.security.ISecurityManager");
                String s7 = parcel.readString();
                boolean flag33;
                if(parcel.readInt() != 0)
                    flag33 = true;
                else
                    flag33 = false;
                setAppPrivacyStatus(s7, flag33);
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag34 = isAppPrivacyEnabled(parcel.readString());
                parcel1.writeNoException();
                if(flag34)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 67: // 'C'
                parcel.enforceInterface("miui.security.ISecurityManager");
                boolean flag35;
                Intent intent3;
                if(parcel.readInt() != 0)
                    intent3 = (Intent)Intent.CREATOR.createFromParcel(parcel);
                else
                    intent3 = null;
                flag35 = isAllowStartService(intent3, parcel.readInt());
                parcel1.writeNoException();
                if(flag35)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("miui.security.ISecurityManager");
                parcel = getTopActivity();
                parcel1.writeNoException();
                parcel1.writeStrongBinder(parcel);
                return true;

            case 69: // 'E'
                parcel.enforceInterface("miui.security.ISecurityManager");
                parcel = getAppRunningControlIBinder();
                parcel1.writeNoException();
                parcel1.writeStrongBinder(parcel);
                return true;

            case 70: // 'F'
                parcel.enforceInterface("miui.security.ISecurityManager");
                watchGreenGuardProcess();
                parcel1.writeNoException();
                return true;

            case 71: // 'G'
                parcel.enforceInterface("miui.security.ISecurityManager");
                i = getSecondSpaceId();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;
            }
        }

        private static final String DESCRIPTOR = "miui.security.ISecurityManager";
        static final int TRANSACTION_activityResume = 27;
        static final int TRANSACTION_addAccessControlPass = 6;
        static final int TRANSACTION_addAccessControlPassForUser = 38;
        static final int TRANSACTION_areNotificationsEnabledForPackage = 52;
        static final int TRANSACTION_checkAccessControlPass = 8;
        static final int TRANSACTION_checkAccessControlPassAsUser = 31;
        static final int TRANSACTION_checkAccessControlPassword = 62;
        static final int TRANSACTION_checkAllowStartActivity = 34;
        static final int TRANSACTION_checkGameBoosterAntimsgPassAsUser = 58;
        static final int TRANSACTION_checkSmsBlocked = 3;
        static final int TRANSACTION_finishAccessControl = 26;
        static final int TRANSACTION_getAccessControlPasswordType = 64;
        static final int TRANSACTION_getAppPermissionControlOpen = 21;
        static final int TRANSACTION_getAppRunningControlIBinder = 69;
        static final int TRANSACTION_getApplicationAccessControlEnabled = 9;
        static final int TRANSACTION_getApplicationAccessControlEnabledAsUser = 32;
        static final int TRANSACTION_getApplicationChildrenControlEnabled = 11;
        static final int TRANSACTION_getApplicationMaskNotificationEnabledAsUser = 50;
        static final int TRANSACTION_getCurrentUserId = 30;
        static final int TRANSACTION_getGameMode = 60;
        static final int TRANSACTION_getIncompatibleAppList = 46;
        static final int TRANSACTION_getPackageNameByPid = 2;
        static final int TRANSACTION_getSecondSpaceId = 71;
        static final int TRANSACTION_getSysAppCracked = 35;
        static final int TRANSACTION_getTopActivity = 68;
        static final int TRANSACTION_getWakePathCallListLog = 20;
        static final int TRANSACTION_getWakePathComponents = 47;
        static final int TRANSACTION_getWakeUpTime = 14;
        static final int TRANSACTION_grantInstallPermission = 36;
        static final int TRANSACTION_grantRuntimePermission = 29;
        static final int TRANSACTION_haveAccessControlPassword = 63;
        static final int TRANSACTION_isAllowStartService = 67;
        static final int TRANSACTION_isAppHide = 54;
        static final int TRANSACTION_isAppPrivacyEnabled = 66;
        static final int TRANSACTION_isFunctionOpen = 55;
        static final int TRANSACTION_isRestrictedAppNet = 40;
        static final int TRANSACTION_isValidDevice = 57;
        static final int TRANSACTION_killNativePackageProcesses = 1;
        static final int TRANSACTION_needFinishAccessControl = 25;
        static final int TRANSACTION_notifyAppsPreInstalled = 49;
        static final int TRANSACTION_offerGoogleBaseCallBack = 48;
        static final int TRANSACTION_pushWakePathConfirmDialogWhiteList = 37;
        static final int TRANSACTION_pushWakePathData = 17;
        static final int TRANSACTION_pushWakePathWhiteList = 18;
        static final int TRANSACTION_putSystemDataStringFile = 15;
        static final int TRANSACTION_readSystemDataStringFile = 16;
        static final int TRANSACTION_registerWakePathCallback = 23;
        static final int TRANSACTION_removeAccessControlPass = 7;
        static final int TRANSACTION_removeAccessControlPassAsUser = 24;
        static final int TRANSACTION_removeWakePathData = 33;
        static final int TRANSACTION_saveIcon = 42;
        static final int TRANSACTION_setAccessControlPassword = 61;
        static final int TRANSACTION_setAppHide = 56;
        static final int TRANSACTION_setAppPermissionControlOpen = 22;
        static final int TRANSACTION_setAppPrivacyStatus = 65;
        static final int TRANSACTION_setApplicationAccessControlEnabled = 10;
        static final int TRANSACTION_setApplicationAccessControlEnabledForUser = 39;
        static final int TRANSACTION_setApplicationChildrenControlEnabled = 12;
        static final int TRANSACTION_setApplicationMaskNotificationEnabledForUser = 51;
        static final int TRANSACTION_setCoreRuntimePermissionEnabled = 28;
        static final int TRANSACTION_setCurrentNetworkState = 44;
        static final int TRANSACTION_setGameBoosterIBinder = 59;
        static final int TRANSACTION_setIncompatibleAppList = 45;
        static final int TRANSACTION_setMiuiFirewallRule = 43;
        static final int TRANSACTION_setNotificationsEnabledForPackage = 53;
        static final int TRANSACTION_setTrackWakePathCallListLogEnabled = 19;
        static final int TRANSACTION_setWakeUpTime = 13;
        static final int TRANSACTION_startInterceptSmsBySender = 4;
        static final int TRANSACTION_stopInterceptSmsBySender = 5;
        static final int TRANSACTION_watchGreenGuardProcess = 70;
        static final int TRANSACTION_writeAppHideConfig = 41;

        public Stub()
        {
            attachInterface(this, "miui.security.ISecurityManager");
        }
    }

    private static class Stub.Proxy
        implements ISecurityManager
    {

        public int activityResume(Intent intent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            if(intent == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
        }

        public void addAccessControlPass(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void addAccessControlPassForUser(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean areNotificationsEnabledForPackage(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean checkAccessControlPass(String s, Intent intent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            if(intent == null)
                break MISSING_BLOCK_LABEL_83;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean checkAccessControlPassAsUser(String s, Intent intent, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            if(intent == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean checkAccessControlPassword(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(62, parcel, parcel1, 0);
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

        public boolean checkAllowStartActivity(String s, String s1, Intent intent, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            if(intent == null)
                break MISSING_BLOCK_LABEL_103;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
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
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean checkGameBoosterAntimsgPassAsUser(String s, Intent intent, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            if(intent == null)
                break MISSING_BLOCK_LABEL_94;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(58, parcel, parcel1, 0);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean checkSmsBlocked(Intent intent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            if(intent == null)
                break MISSING_BLOCK_LABEL_80;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            boolean flag;
            if(parcel1.readInt() != 0)
                flag = true;
            else
                flag = false;
            if(parcel1.readInt() != 0)
                intent.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
        }

        public void finishAccessControl(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public String getAccessControlPasswordType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public int getAppPermissionControlOpen(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public IBinder getAppRunningControlIBinder()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IBinder ibinder;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            mRemote.transact(69, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getApplicationAccessControlEnabled(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public boolean getApplicationAccessControlEnabledAsUser(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(32, parcel, parcel1, 0);
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

        public boolean getApplicationChildrenControlEnabled(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public boolean getApplicationMaskNotificationEnabledAsUser(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getCurrentUserId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public boolean getGameMode(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeInt(i);
            mRemote.transact(60, parcel, parcel1, 0);
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

        public List getIncompatibleAppList()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            mRemote.transact(46, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "miui.security.ISecurityManager";
        }

        public String getPackageNameByPid(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeInt(i);
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

        public int getSecondSpaceId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            mRemote.transact(71, parcel, parcel1, 0);
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

        public int getSysAppCracked()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public IBinder getTopActivity()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IBinder ibinder;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            mRemote.transact(68, parcel, parcel1, 0);
            parcel1.readException();
            ibinder = parcel1.readStrongBinder();
            parcel1.recycle();
            parcel.recycle();
            return ibinder;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParceledListSlice getWakePathCallListLog()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParceledListSlice parceledlistslice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parceledlistslice;
_L2:
            parceledlistslice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParceledListSlice getWakePathComponents(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel1);
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

        public long getWakeUpTime(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            l = parcel1.readLong();
            parcel1.recycle();
            parcel.recycle();
            return l;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void grantInstallPermission(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void grantRuntimePermission(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean haveAccessControlPassword(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeInt(i);
            mRemote.transact(63, parcel, parcel1, 0);
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

        public boolean isAllowStartService(Intent intent, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            if(intent == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            intent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
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
            parcel.writeInt(0);
              goto _L1
            intent;
            parcel1.recycle();
            parcel.recycle();
            throw intent;
        }

        public boolean isAppHide()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            mRemote.transact(54, parcel, parcel1, 0);
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

        public boolean isAppPrivacyEnabled(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            mRemote.transact(66, parcel, parcel1, 0);
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

        public boolean isFunctionOpen()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            mRemote.transact(55, parcel, parcel1, 0);
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

        public boolean isRestrictedAppNet(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isValidDevice()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void killNativePackageProcesses(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public boolean needFinishAccessControl(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(25, parcel, parcel1, 0);
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
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void notifyAppsPreInstalled()
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            mRemote.transact(49, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        public void offerGoogleBaseCallBack(ISecurityCallback isecuritycallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            if(isecuritycallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = isecuritycallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            isecuritycallback;
            parcel1.recycle();
            parcel.recycle();
            throw isecuritycallback;
        }

        public void pushWakePathConfirmDialogWhiteList(int i, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeInt(i);
            parcel.writeStringList(list);
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public void pushWakePathData(int i, ParceledListSlice parceledlistslice, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeInt(i);
            if(parceledlistslice == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            parceledlistslice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            parceledlistslice;
            parcel1.recycle();
            parcel.recycle();
            throw parceledlistslice;
        }

        public void pushWakePathWhiteList(List list, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeStringList(list);
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public boolean putSystemDataStringFile(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
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

        public String readSystemDataStringFile(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public void registerWakePathCallback(IWakePathCallback iwakepathcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            if(iwakepathcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iwakepathcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iwakepathcallback;
            parcel1.recycle();
            parcel.recycle();
            throw iwakepathcallback;
        }

        public void removeAccessControlPass(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public void removeAccessControlPassAsUser(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeWakePathData(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public void saveIcon(String s, Bitmap bitmap)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            if(bitmap == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            bitmap.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(42, parcel, parcel1, 0);
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

        public void setAccessControlPassword(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
            mRemote.transact(61, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean setAppHide(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(56, parcel, parcel1, 0);
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

        public void setAppPermissionControlOpen(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public void setAppPrivacyStatus(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(65, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setApplicationAccessControlEnabled(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
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

        public void setApplicationAccessControlEnabledForUser(String s, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setApplicationChildrenControlEnabled(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setApplicationMaskNotificationEnabledForUser(String s, boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setCoreRuntimePermissionEnabled(boolean flag, int i)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            if(flag)
                j = 1;
            parcel.writeInt(j);
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

        public boolean setCurrentNetworkState(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setGameBoosterIBinder(IBinder ibinder, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(59, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setIncompatibleAppList(List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeStringList(list);
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            list;
            parcel1.recycle();
            parcel.recycle();
            throw list;
        }

        public boolean setMiuiFirewallRule(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public void setNotificationsEnabledForPackage(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(53, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setTrackWakePathCallListLogEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public void setWakeUpTime(String s, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeLong(l);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean startInterceptSmsBySender(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean stopInterceptSmsBySender()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
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

        public void watchGreenGuardProcess()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            mRemote.transact(70, parcel, parcel1, 0);
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

        public boolean writeAppHideConfig(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("miui.security.ISecurityManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(41, parcel, parcel1, 0);
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract int activityResume(Intent intent)
        throws RemoteException;

    public abstract void addAccessControlPass(String s)
        throws RemoteException;

    public abstract void addAccessControlPassForUser(String s, int i)
        throws RemoteException;

    public abstract boolean areNotificationsEnabledForPackage(String s, int i)
        throws RemoteException;

    public abstract boolean checkAccessControlPass(String s, Intent intent)
        throws RemoteException;

    public abstract boolean checkAccessControlPassAsUser(String s, Intent intent, int i)
        throws RemoteException;

    public abstract boolean checkAccessControlPassword(String s, String s1, int i)
        throws RemoteException;

    public abstract boolean checkAllowStartActivity(String s, String s1, Intent intent, int i)
        throws RemoteException;

    public abstract boolean checkGameBoosterAntimsgPassAsUser(String s, Intent intent, int i)
        throws RemoteException;

    public abstract boolean checkSmsBlocked(Intent intent)
        throws RemoteException;

    public abstract void finishAccessControl(String s, int i)
        throws RemoteException;

    public abstract String getAccessControlPasswordType(int i)
        throws RemoteException;

    public abstract int getAppPermissionControlOpen(int i)
        throws RemoteException;

    public abstract IBinder getAppRunningControlIBinder()
        throws RemoteException;

    public abstract boolean getApplicationAccessControlEnabled(String s)
        throws RemoteException;

    public abstract boolean getApplicationAccessControlEnabledAsUser(String s, int i)
        throws RemoteException;

    public abstract boolean getApplicationChildrenControlEnabled(String s)
        throws RemoteException;

    public abstract boolean getApplicationMaskNotificationEnabledAsUser(String s, int i)
        throws RemoteException;

    public abstract int getCurrentUserId()
        throws RemoteException;

    public abstract boolean getGameMode(int i)
        throws RemoteException;

    public abstract List getIncompatibleAppList()
        throws RemoteException;

    public abstract String getPackageNameByPid(int i)
        throws RemoteException;

    public abstract int getSecondSpaceId()
        throws RemoteException;

    public abstract int getSysAppCracked()
        throws RemoteException;

    public abstract IBinder getTopActivity()
        throws RemoteException;

    public abstract ParceledListSlice getWakePathCallListLog()
        throws RemoteException;

    public abstract ParceledListSlice getWakePathComponents(String s)
        throws RemoteException;

    public abstract long getWakeUpTime(String s)
        throws RemoteException;

    public abstract void grantInstallPermission(String s, String s1)
        throws RemoteException;

    public abstract void grantRuntimePermission(String s)
        throws RemoteException;

    public abstract boolean haveAccessControlPassword(int i)
        throws RemoteException;

    public abstract boolean isAllowStartService(Intent intent, int i)
        throws RemoteException;

    public abstract boolean isAppHide()
        throws RemoteException;

    public abstract boolean isAppPrivacyEnabled(String s)
        throws RemoteException;

    public abstract boolean isFunctionOpen()
        throws RemoteException;

    public abstract boolean isRestrictedAppNet(String s)
        throws RemoteException;

    public abstract boolean isValidDevice()
        throws RemoteException;

    public abstract void killNativePackageProcesses(int i, String s)
        throws RemoteException;

    public abstract boolean needFinishAccessControl(IBinder ibinder)
        throws RemoteException;

    public abstract void notifyAppsPreInstalled()
        throws RemoteException;

    public abstract void offerGoogleBaseCallBack(ISecurityCallback isecuritycallback)
        throws RemoteException;

    public abstract void pushWakePathConfirmDialogWhiteList(int i, List list)
        throws RemoteException;

    public abstract void pushWakePathData(int i, ParceledListSlice parceledlistslice, int j)
        throws RemoteException;

    public abstract void pushWakePathWhiteList(List list, int i)
        throws RemoteException;

    public abstract boolean putSystemDataStringFile(String s, String s1)
        throws RemoteException;

    public abstract String readSystemDataStringFile(String s)
        throws RemoteException;

    public abstract void registerWakePathCallback(IWakePathCallback iwakepathcallback)
        throws RemoteException;

    public abstract void removeAccessControlPass(String s)
        throws RemoteException;

    public abstract void removeAccessControlPassAsUser(String s, int i)
        throws RemoteException;

    public abstract void removeWakePathData(int i)
        throws RemoteException;

    public abstract void saveIcon(String s, Bitmap bitmap)
        throws RemoteException;

    public abstract void setAccessControlPassword(String s, String s1, int i)
        throws RemoteException;

    public abstract boolean setAppHide(boolean flag)
        throws RemoteException;

    public abstract void setAppPermissionControlOpen(int i)
        throws RemoteException;

    public abstract void setAppPrivacyStatus(String s, boolean flag)
        throws RemoteException;

    public abstract void setApplicationAccessControlEnabled(String s, boolean flag)
        throws RemoteException;

    public abstract void setApplicationAccessControlEnabledForUser(String s, boolean flag, int i)
        throws RemoteException;

    public abstract void setApplicationChildrenControlEnabled(String s, boolean flag)
        throws RemoteException;

    public abstract void setApplicationMaskNotificationEnabledForUser(String s, boolean flag, int i)
        throws RemoteException;

    public abstract void setCoreRuntimePermissionEnabled(boolean flag, int i)
        throws RemoteException;

    public abstract boolean setCurrentNetworkState(int i)
        throws RemoteException;

    public abstract void setGameBoosterIBinder(IBinder ibinder, int i, boolean flag)
        throws RemoteException;

    public abstract void setIncompatibleAppList(List list)
        throws RemoteException;

    public abstract boolean setMiuiFirewallRule(String s, int i, int j)
        throws RemoteException;

    public abstract void setNotificationsEnabledForPackage(String s, int i, boolean flag)
        throws RemoteException;

    public abstract void setTrackWakePathCallListLogEnabled(boolean flag)
        throws RemoteException;

    public abstract void setWakeUpTime(String s, long l)
        throws RemoteException;

    public abstract boolean startInterceptSmsBySender(String s, String s1, int i)
        throws RemoteException;

    public abstract boolean stopInterceptSmsBySender()
        throws RemoteException;

    public abstract void watchGreenGuardProcess()
        throws RemoteException;

    public abstract boolean writeAppHideConfig(boolean flag)
        throws RemoteException;
}
