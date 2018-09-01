// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi;

import android.content.pm.ParceledListSlice;
import android.net.DhcpInfo;
import android.net.Network;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.*;
import java.util.List;

// Referenced classes of package android.net.wifi:
//            WifiConfiguration, WifiInfo, WifiConnectionStatistics, ScanResult, 
//            WifiActivityEnergyInfo, ScanSettings, WifiDevice

public interface IWifiManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IWifiManager
    {

        public static IWifiManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.wifi.IWifiManager");
            if(iinterface != null && (iinterface instanceof IWifiManager))
                return (IWifiManager)iinterface;
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
            boolean flag23;
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.net.wifi.IWifiManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                i = getSupportedFeatures();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = reportActivityInfo();
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
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                if(parcel.readInt() != 0)
                    parcel = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestActivityInfo(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getConfiguredNetworks();
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

            case 5: // '\005'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getPrivilegedConfiguredNetworks();
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

            case 6: // '\006'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                if(parcel.readInt() != 0)
                    parcel = (ScanResult)ScanResult.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getMatchingWifiConfig(parcel);
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

            case 7: // '\007'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                if(parcel.readInt() != 0)
                    parcel = (ScanResult)ScanResult.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getMatchingOsuProviders(parcel);
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                if(parcel.readInt() != 0)
                    parcel = (WifiConfiguration)WifiConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = addOrUpdateNetwork(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel = (PasspointConfiguration)PasspointConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = addOrUpdatePasspointConfiguration(parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag1 = removePasspointConfiguration(parcel.readString());
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getPasspointConfigurations();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                queryPasspointIcon(parcel.readLong(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                i = matchProviderWithCurrentNetwork(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                long l = parcel.readLong();
                boolean flag2;
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                deauthenticateNetwork(l, flag2);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag3 = removeNetwork(parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                i = parcel.readInt();
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                flag4 = enableNetwork(i, flag4);
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag5 = disableNetwork(parcel.readInt());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                ScanSettings scansettings;
                WorkSource worksource;
                if(parcel.readInt() != 0)
                    scansettings = (ScanSettings)ScanSettings.CREATOR.createFromParcel(parcel);
                else
                    scansettings = null;
                if(parcel.readInt() != 0)
                    worksource = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource = null;
                startScan(scansettings, worksource, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getScanResults(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                disconnect();
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                reconnect();
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                reassociate();
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getConnectionInfo(parcel.readString());
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
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                String s = parcel.readString();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                flag6 = setWifiEnabled(s, flag6);
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                i = getWifiEnabledState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                String s1 = parcel.readString();
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                setCountryCode(s1, flag7);
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getCountryCode();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag8 = isDualBandSupported();
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag9 = saveConfiguration();
                parcel1.writeNoException();
                if(flag9)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getDhcpInfo();
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

            case 31: // '\037'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag10 = isScanAlwaysAvailable();
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                IBinder ibinder1 = parcel.readStrongBinder();
                i = parcel.readInt();
                String s2 = parcel.readString();
                boolean flag11;
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag11 = acquireWifiLock(ibinder1, i, s2, parcel);
                parcel1.writeNoException();
                if(flag11)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                IBinder ibinder = parcel.readStrongBinder();
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                updateWifiLockWorkSource(ibinder, parcel);
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag12 = releaseWifiLock(parcel.readStrongBinder());
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                initializeMulticastFiltering();
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag13 = isMulticastEnabled();
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                acquireMulticastLock(parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                releaseMulticastLock();
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag14;
                WifiConfiguration wificonfiguration;
                if(parcel.readInt() != 0)
                    wificonfiguration = (WifiConfiguration)WifiConfiguration.CREATOR.createFromParcel(parcel);
                else
                    wificonfiguration = null;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                setWifiApEnabled(wificonfiguration, flag14);
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                updateInterfaceIpState(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag15;
                if(parcel.readInt() != 0)
                    parcel = (WifiConfiguration)WifiConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag15 = startSoftAp(parcel);
                parcel1.writeNoException();
                if(flag15)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 42: // '*'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag16 = stopSoftAp();
                parcel1.writeNoException();
                if(flag16)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 43: // '+'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                Messenger messenger;
                if(parcel.readInt() != 0)
                    messenger = (Messenger)Messenger.CREATOR.createFromParcel(parcel);
                else
                    messenger = null;
                i = startLocalOnlyHotspot(messenger, parcel.readStrongBinder(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 44: // ','
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                stopLocalOnlyHotspot();
                parcel1.writeNoException();
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                Messenger messenger1;
                if(parcel.readInt() != 0)
                    messenger1 = (Messenger)Messenger.CREATOR.createFromParcel(parcel);
                else
                    messenger1 = null;
                startWatchLocalOnlyHotspot(messenger1, parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                stopWatchLocalOnlyHotspot();
                parcel1.writeNoException();
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                i = getWifiApEnabledState();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getWifiApConfiguration();
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

            case 49: // '1'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                if(parcel.readInt() != 0)
                    parcel = (WifiConfiguration)WifiConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setWifiApConfiguration(parcel);
                parcel1.writeNoException();
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getWifiServiceMessenger();
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

            case 51: // '3'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                String s3 = parcel.readString();
                boolean flag17;
                if(parcel.readInt() != 0)
                    flag17 = true;
                else
                    flag17 = false;
                enableTdls(s3, flag17);
                parcel1.writeNoException();
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                String s4 = parcel.readString();
                boolean flag18;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                enableTdlsWithMacAddress(s4, flag18);
                parcel1.writeNoException();
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getCurrentNetworkWpsNfcConfigurationToken();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                enableVerboseLogging(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                i = getVerboseLoggingLevel();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                enableAggressiveHandover(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                i = getAggressiveHandover();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                setAllowScansWithTraffic(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 59: // ';'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                i = getAllowScansWithTraffic();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 60: // '<'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag19;
                if(parcel.readInt() != 0)
                    flag19 = true;
                else
                    flag19 = false;
                flag19 = setEnableAutoJoinWhenAssociated(flag19);
                parcel1.writeNoException();
                if(flag19)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 61: // '='
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag20 = getEnableAutoJoinWhenAssociated();
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag21;
                if(parcel.readInt() != 0)
                    flag21 = true;
                else
                    flag21 = false;
                enableWifiConnectivityManager(flag21);
                parcel1.writeNoException();
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getConnectionStatistics();
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

            case 64: // '@'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                disableEphemeralNetwork(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                factoryReset();
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getCurrentNetwork();
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
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = retrieveBackupData();
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                restoreBackupData(parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 69: // 'E'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                restoreSupplicantBackupData(parcel.createByteArray(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 70: // 'F'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                boolean flag22 = getWifiStaSapConcurrency();
                parcel1.writeNoException();
                if(flag22)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 71: // 'G'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                parcel = getConnectedStations();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 72: // 'H'
                parcel.enforceInterface("android.net.wifi.IWifiManager");
                flag23 = isExtendingNetworkCoverage();
                parcel1.writeNoException();
                break;
            }
            if(flag23)
                i = 1;
            else
                i = 0;
            parcel1.writeInt(i);
            return true;
        }

        private static final String DESCRIPTOR = "android.net.wifi.IWifiManager";
        static final int TRANSACTION_acquireMulticastLock = 37;
        static final int TRANSACTION_acquireWifiLock = 32;
        static final int TRANSACTION_addOrUpdateNetwork = 8;
        static final int TRANSACTION_addOrUpdatePasspointConfiguration = 9;
        static final int TRANSACTION_deauthenticateNetwork = 14;
        static final int TRANSACTION_disableEphemeralNetwork = 64;
        static final int TRANSACTION_disableNetwork = 17;
        static final int TRANSACTION_disconnect = 20;
        static final int TRANSACTION_enableAggressiveHandover = 56;
        static final int TRANSACTION_enableNetwork = 16;
        static final int TRANSACTION_enableTdls = 51;
        static final int TRANSACTION_enableTdlsWithMacAddress = 52;
        static final int TRANSACTION_enableVerboseLogging = 54;
        static final int TRANSACTION_enableWifiConnectivityManager = 62;
        static final int TRANSACTION_factoryReset = 65;
        static final int TRANSACTION_getAggressiveHandover = 57;
        static final int TRANSACTION_getAllowScansWithTraffic = 59;
        static final int TRANSACTION_getConfiguredNetworks = 4;
        static final int TRANSACTION_getConnectedStations = 71;
        static final int TRANSACTION_getConnectionInfo = 23;
        static final int TRANSACTION_getConnectionStatistics = 63;
        static final int TRANSACTION_getCountryCode = 27;
        static final int TRANSACTION_getCurrentNetwork = 66;
        static final int TRANSACTION_getCurrentNetworkWpsNfcConfigurationToken = 53;
        static final int TRANSACTION_getDhcpInfo = 30;
        static final int TRANSACTION_getEnableAutoJoinWhenAssociated = 61;
        static final int TRANSACTION_getMatchingOsuProviders = 7;
        static final int TRANSACTION_getMatchingWifiConfig = 6;
        static final int TRANSACTION_getPasspointConfigurations = 11;
        static final int TRANSACTION_getPrivilegedConfiguredNetworks = 5;
        static final int TRANSACTION_getScanResults = 19;
        static final int TRANSACTION_getSupportedFeatures = 1;
        static final int TRANSACTION_getVerboseLoggingLevel = 55;
        static final int TRANSACTION_getWifiApConfiguration = 48;
        static final int TRANSACTION_getWifiApEnabledState = 47;
        static final int TRANSACTION_getWifiEnabledState = 25;
        static final int TRANSACTION_getWifiServiceMessenger = 50;
        static final int TRANSACTION_getWifiStaSapConcurrency = 70;
        static final int TRANSACTION_initializeMulticastFiltering = 35;
        static final int TRANSACTION_isDualBandSupported = 28;
        static final int TRANSACTION_isExtendingNetworkCoverage = 72;
        static final int TRANSACTION_isMulticastEnabled = 36;
        static final int TRANSACTION_isScanAlwaysAvailable = 31;
        static final int TRANSACTION_matchProviderWithCurrentNetwork = 13;
        static final int TRANSACTION_queryPasspointIcon = 12;
        static final int TRANSACTION_reassociate = 22;
        static final int TRANSACTION_reconnect = 21;
        static final int TRANSACTION_releaseMulticastLock = 38;
        static final int TRANSACTION_releaseWifiLock = 34;
        static final int TRANSACTION_removeNetwork = 15;
        static final int TRANSACTION_removePasspointConfiguration = 10;
        static final int TRANSACTION_reportActivityInfo = 2;
        static final int TRANSACTION_requestActivityInfo = 3;
        static final int TRANSACTION_restoreBackupData = 68;
        static final int TRANSACTION_restoreSupplicantBackupData = 69;
        static final int TRANSACTION_retrieveBackupData = 67;
        static final int TRANSACTION_saveConfiguration = 29;
        static final int TRANSACTION_setAllowScansWithTraffic = 58;
        static final int TRANSACTION_setCountryCode = 26;
        static final int TRANSACTION_setEnableAutoJoinWhenAssociated = 60;
        static final int TRANSACTION_setWifiApConfiguration = 49;
        static final int TRANSACTION_setWifiApEnabled = 39;
        static final int TRANSACTION_setWifiEnabled = 24;
        static final int TRANSACTION_startLocalOnlyHotspot = 43;
        static final int TRANSACTION_startScan = 18;
        static final int TRANSACTION_startSoftAp = 41;
        static final int TRANSACTION_startWatchLocalOnlyHotspot = 45;
        static final int TRANSACTION_stopLocalOnlyHotspot = 44;
        static final int TRANSACTION_stopSoftAp = 42;
        static final int TRANSACTION_stopWatchLocalOnlyHotspot = 46;
        static final int TRANSACTION_updateInterfaceIpState = 40;
        static final int TRANSACTION_updateWifiLockWorkSource = 33;

        public Stub()
        {
            attachInterface(this, "android.net.wifi.IWifiManager");
        }
    }

    private static class Stub.Proxy
        implements IWifiManager
    {

        public void acquireMulticastLock(IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean acquireWifiLock(IBinder ibinder, int i, String s, WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            parcel.writeString(s);
            if(worksource == null)
                break MISSING_BLOCK_LABEL_102;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
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
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public int addOrUpdateNetwork(WifiConfiguration wificonfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(wificonfiguration == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            wificonfiguration.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            wificonfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw wificonfiguration;
        }

        public boolean addOrUpdatePasspointConfiguration(PasspointConfiguration passpointconfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(passpointconfiguration == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            passpointconfiguration.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            passpointconfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw passpointconfiguration;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void deauthenticateNetwork(long l, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeLong(l);
            if(flag)
                i = 1;
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

        public void disableEphemeralNetwork(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeString(s);
            mRemote.transact(64, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean disableNetwork(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeInt(i);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void disconnect()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(20, parcel, parcel1, 0);
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

        public void enableAggressiveHandover(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeInt(i);
            mRemote.transact(56, parcel, parcel1, 0);
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

        public boolean enableNetwork(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
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

        public void enableTdls(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
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

        public void enableTdlsWithMacAddress(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(52, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void enableVerboseLogging(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeInt(i);
            mRemote.transact(54, parcel, parcel1, 0);
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

        public void enableWifiConnectivityManager(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(flag)
                i = 1;
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

        public void factoryReset()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(65, parcel, parcel1, 0);
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

        public int getAggressiveHandover()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public int getAllowScansWithTraffic()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public ParceledListSlice getConfiguredNetworks()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(4, parcel, parcel1, 0);
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

        public List getConnectedStations()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(71, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(WifiDevice.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public WifiInfo getConnectionInfo(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeString(s);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (WifiInfo)WifiInfo.CREATOR.createFromParcel(parcel1);
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

        public WifiConnectionStatistics getConnectionStatistics()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(63, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            WifiConnectionStatistics wificonnectionstatistics = (WifiConnectionStatistics)WifiConnectionStatistics.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return wificonnectionstatistics;
_L2:
            wificonnectionstatistics = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getCountryCode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public Network getCurrentNetwork()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(66, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Network network = (Network)Network.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return network;
_L2:
            network = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getCurrentNetworkWpsNfcConfigurationToken()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public DhcpInfo getDhcpInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            DhcpInfo dhcpinfo = (DhcpInfo)DhcpInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return dhcpinfo;
_L2:
            dhcpinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getEnableAutoJoinWhenAssociated()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(61, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "android.net.wifi.IWifiManager";
        }

        public List getMatchingOsuProviders(ScanResult scanresult)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(scanresult == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            scanresult.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            scanresult = parcel1.createTypedArrayList(OsuProvider.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return scanresult;
            parcel.writeInt(0);
              goto _L1
            scanresult;
            parcel1.recycle();
            parcel.recycle();
            throw scanresult;
        }

        public WifiConfiguration getMatchingWifiConfig(ScanResult scanresult)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(scanresult == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            scanresult.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            scanresult = (WifiConfiguration)WifiConfiguration.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return scanresult;
_L2:
            parcel.writeInt(0);
              goto _L3
            scanresult;
            parcel1.recycle();
            parcel.recycle();
            throw scanresult;
            scanresult = null;
              goto _L4
        }

        public List getPasspointConfigurations()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(PasspointConfiguration.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ParceledListSlice getPrivilegedConfiguredNetworks()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(5, parcel, parcel1, 0);
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

        public List getScanResults(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeString(s);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            s = parcel1.createTypedArrayList(ScanResult.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return s;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int getSupportedFeatures()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public int getVerboseLoggingLevel()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public WifiConfiguration getWifiApConfiguration()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(48, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            WifiConfiguration wificonfiguration = (WifiConfiguration)WifiConfiguration.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return wificonfiguration;
_L2:
            wificonfiguration = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int getWifiApEnabledState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public int getWifiEnabledState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public Messenger getWifiServiceMessenger()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            Messenger messenger = (Messenger)Messenger.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return messenger;
_L2:
            messenger = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getWifiStaSapConcurrency()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public void initializeMulticastFiltering()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public boolean isDualBandSupported()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isExtendingNetworkCoverage()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(72, parcel, parcel1, 0);
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

        public boolean isMulticastEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(36, parcel, parcel1, 0);
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

        public boolean isScanAlwaysAvailable()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int matchProviderWithCurrentNetwork(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public void queryPasspointIcon(long l, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeLong(l);
            parcel.writeString(s);
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

        public void reassociate()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public void reconnect()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(21, parcel, parcel1, 0);
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

        public void releaseMulticastLock()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(38, parcel, parcel1, 0);
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

        public boolean releaseWifiLock(IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeStrongBinder(ibinder);
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
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public boolean removeNetwork(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeInt(i);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean removePasspointConfiguration(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public WifiActivityEnergyInfo reportActivityInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            WifiActivityEnergyInfo wifiactivityenergyinfo = (WifiActivityEnergyInfo)WifiActivityEnergyInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return wifiactivityenergyinfo;
_L2:
            wifiactivityenergyinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void requestActivityInfo(ResultReceiver resultreceiver)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            resultreceiver;
            parcel.recycle();
            throw resultreceiver;
        }

        public void restoreBackupData(byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeByteArray(abyte0);
            mRemote.transact(68, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void restoreSupplicantBackupData(byte abyte0[], byte abyte1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeByteArray(abyte0);
            parcel.writeByteArray(abyte1);
            mRemote.transact(69, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public byte[] retrieveBackupData()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(67, parcel, parcel1, 0);
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

        public boolean saveConfiguration()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(29, parcel, parcel1, 0);
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

        public void setAllowScansWithTraffic(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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

        public void setCountryCode(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
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

        public boolean setEnableAutoJoinWhenAssociated(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(60, parcel, parcel1, 0);
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

        public void setWifiApConfiguration(WifiConfiguration wificonfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(wificonfiguration == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            wificonfiguration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(49, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            wificonfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw wificonfiguration;
        }

        public void setWifiApEnabled(WifiConfiguration wificonfiguration, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(wificonfiguration == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            wificonfiguration.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(39, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            wificonfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw wificonfiguration;
        }

        public boolean setWifiEnabled(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
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

        public int startLocalOnlyHotspot(Messenger messenger, IBinder ibinder, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(messenger == null)
                break MISSING_BLOCK_LABEL_88;
            parcel.writeInt(1);
            messenger.writeToParcel(parcel, 0);
_L1:
            int i;
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            messenger;
            parcel1.recycle();
            parcel.recycle();
            throw messenger;
        }

        public void startScan(ScanSettings scansettings, WorkSource worksource, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(scansettings == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            scansettings.writeToParcel(parcel, 0);
_L3:
            if(worksource == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L4:
            parcel.writeString(s);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            scansettings;
            parcel1.recycle();
            parcel.recycle();
            throw scansettings;
            parcel.writeInt(0);
              goto _L4
        }

        public boolean startSoftAp(WifiConfiguration wificonfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(wificonfiguration == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            wificonfiguration.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(41, parcel, parcel1, 0);
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
            wificonfiguration;
            parcel1.recycle();
            parcel.recycle();
            throw wificonfiguration;
        }

        public void startWatchLocalOnlyHotspot(Messenger messenger, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            if(messenger == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            messenger.writeToParcel(parcel, 0);
_L1:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            messenger;
            parcel1.recycle();
            parcel.recycle();
            throw messenger;
        }

        public void stopLocalOnlyHotspot()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(44, parcel, parcel1, 0);
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

        public boolean stopSoftAp()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void stopWatchLocalOnlyHotspot()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            mRemote.transact(46, parcel, parcel1, 0);
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

        public void updateInterfaceIpState(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(40, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void updateWifiLockWorkSource(IBinder ibinder, WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.wifi.IWifiManager");
            parcel.writeStrongBinder(ibinder);
            if(worksource == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void acquireMulticastLock(IBinder ibinder, String s)
        throws RemoteException;

    public abstract boolean acquireWifiLock(IBinder ibinder, int i, String s, WorkSource worksource)
        throws RemoteException;

    public abstract int addOrUpdateNetwork(WifiConfiguration wificonfiguration)
        throws RemoteException;

    public abstract boolean addOrUpdatePasspointConfiguration(PasspointConfiguration passpointconfiguration)
        throws RemoteException;

    public abstract void deauthenticateNetwork(long l, boolean flag)
        throws RemoteException;

    public abstract void disableEphemeralNetwork(String s)
        throws RemoteException;

    public abstract boolean disableNetwork(int i)
        throws RemoteException;

    public abstract void disconnect()
        throws RemoteException;

    public abstract void enableAggressiveHandover(int i)
        throws RemoteException;

    public abstract boolean enableNetwork(int i, boolean flag)
        throws RemoteException;

    public abstract void enableTdls(String s, boolean flag)
        throws RemoteException;

    public abstract void enableTdlsWithMacAddress(String s, boolean flag)
        throws RemoteException;

    public abstract void enableVerboseLogging(int i)
        throws RemoteException;

    public abstract void enableWifiConnectivityManager(boolean flag)
        throws RemoteException;

    public abstract void factoryReset()
        throws RemoteException;

    public abstract int getAggressiveHandover()
        throws RemoteException;

    public abstract int getAllowScansWithTraffic()
        throws RemoteException;

    public abstract ParceledListSlice getConfiguredNetworks()
        throws RemoteException;

    public abstract List getConnectedStations()
        throws RemoteException;

    public abstract WifiInfo getConnectionInfo(String s)
        throws RemoteException;

    public abstract WifiConnectionStatistics getConnectionStatistics()
        throws RemoteException;

    public abstract String getCountryCode()
        throws RemoteException;

    public abstract Network getCurrentNetwork()
        throws RemoteException;

    public abstract String getCurrentNetworkWpsNfcConfigurationToken()
        throws RemoteException;

    public abstract DhcpInfo getDhcpInfo()
        throws RemoteException;

    public abstract boolean getEnableAutoJoinWhenAssociated()
        throws RemoteException;

    public abstract List getMatchingOsuProviders(ScanResult scanresult)
        throws RemoteException;

    public abstract WifiConfiguration getMatchingWifiConfig(ScanResult scanresult)
        throws RemoteException;

    public abstract List getPasspointConfigurations()
        throws RemoteException;

    public abstract ParceledListSlice getPrivilegedConfiguredNetworks()
        throws RemoteException;

    public abstract List getScanResults(String s)
        throws RemoteException;

    public abstract int getSupportedFeatures()
        throws RemoteException;

    public abstract int getVerboseLoggingLevel()
        throws RemoteException;

    public abstract WifiConfiguration getWifiApConfiguration()
        throws RemoteException;

    public abstract int getWifiApEnabledState()
        throws RemoteException;

    public abstract int getWifiEnabledState()
        throws RemoteException;

    public abstract Messenger getWifiServiceMessenger()
        throws RemoteException;

    public abstract boolean getWifiStaSapConcurrency()
        throws RemoteException;

    public abstract void initializeMulticastFiltering()
        throws RemoteException;

    public abstract boolean isDualBandSupported()
        throws RemoteException;

    public abstract boolean isExtendingNetworkCoverage()
        throws RemoteException;

    public abstract boolean isMulticastEnabled()
        throws RemoteException;

    public abstract boolean isScanAlwaysAvailable()
        throws RemoteException;

    public abstract int matchProviderWithCurrentNetwork(String s)
        throws RemoteException;

    public abstract void queryPasspointIcon(long l, String s)
        throws RemoteException;

    public abstract void reassociate()
        throws RemoteException;

    public abstract void reconnect()
        throws RemoteException;

    public abstract void releaseMulticastLock()
        throws RemoteException;

    public abstract boolean releaseWifiLock(IBinder ibinder)
        throws RemoteException;

    public abstract boolean removeNetwork(int i)
        throws RemoteException;

    public abstract boolean removePasspointConfiguration(String s)
        throws RemoteException;

    public abstract WifiActivityEnergyInfo reportActivityInfo()
        throws RemoteException;

    public abstract void requestActivityInfo(ResultReceiver resultreceiver)
        throws RemoteException;

    public abstract void restoreBackupData(byte abyte0[])
        throws RemoteException;

    public abstract void restoreSupplicantBackupData(byte abyte0[], byte abyte1[])
        throws RemoteException;

    public abstract byte[] retrieveBackupData()
        throws RemoteException;

    public abstract boolean saveConfiguration()
        throws RemoteException;

    public abstract void setAllowScansWithTraffic(int i)
        throws RemoteException;

    public abstract void setCountryCode(String s, boolean flag)
        throws RemoteException;

    public abstract boolean setEnableAutoJoinWhenAssociated(boolean flag)
        throws RemoteException;

    public abstract void setWifiApConfiguration(WifiConfiguration wificonfiguration)
        throws RemoteException;

    public abstract void setWifiApEnabled(WifiConfiguration wificonfiguration, boolean flag)
        throws RemoteException;

    public abstract boolean setWifiEnabled(String s, boolean flag)
        throws RemoteException;

    public abstract int startLocalOnlyHotspot(Messenger messenger, IBinder ibinder, String s)
        throws RemoteException;

    public abstract void startScan(ScanSettings scansettings, WorkSource worksource, String s)
        throws RemoteException;

    public abstract boolean startSoftAp(WifiConfiguration wificonfiguration)
        throws RemoteException;

    public abstract void startWatchLocalOnlyHotspot(Messenger messenger, IBinder ibinder)
        throws RemoteException;

    public abstract void stopLocalOnlyHotspot()
        throws RemoteException;

    public abstract boolean stopSoftAp()
        throws RemoteException;

    public abstract void stopWatchLocalOnlyHotspot()
        throws RemoteException;

    public abstract void updateInterfaceIpState(String s, int i)
        throws RemoteException;

    public abstract void updateWifiLockWorkSource(IBinder ibinder, WorkSource worksource)
        throws RemoteException;
}
