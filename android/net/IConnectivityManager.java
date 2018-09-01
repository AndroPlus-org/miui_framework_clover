// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.app.PendingIntent;
import android.os.*;
import com.android.internal.net.*;

// Referenced classes of package android.net:
//            LinkProperties, Network, NetworkInfo, NetworkQuotaInfo, 
//            NetworkState, NetworkCapabilities, ProxyInfo, NetworkRequest, 
//            NetworkMisc

public interface IConnectivityManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IConnectivityManager
    {

        public static IConnectivityManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.net.IConnectivityManager");
            if(iinterface != null && (iinterface instanceof IConnectivityManager))
                return (IConnectivityManager)iinterface;
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
                parcel1.writeString("android.net.IConnectivityManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getActiveNetwork();
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

            case 2: // '\002'
                parcel.enforceInterface("android.net.IConnectivityManager");
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                parcel = getActiveNetworkForUid(i, flag);
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
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getActiveNetworkInfo();
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
                parcel.enforceInterface("android.net.IConnectivityManager");
                i = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                parcel = getActiveNetworkInfoForUid(i, flag1);
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
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getNetworkInfo(parcel.readInt());
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
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag2;
                Network network;
                if(parcel.readInt() != 0)
                    network = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    network = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                parcel = getNetworkInfoForUid(network, i, flag2);
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
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getAllNetworkInfo();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getNetworkForType(parcel.readInt());
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

            case 9: // '\t'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getAllNetworks();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getDefaultNetworkCapabilitiesForUser(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag3 = isNetworkSupported(parcel.readInt());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getActiveLinkProperties();
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

            case 13: // '\r'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getLinkPropertiesForType(parcel.readInt());
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
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getLinkProperties(parcel);
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
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getNetworkCapabilities(parcel);
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

            case 16: // '\020'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getAllNetworkState();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getActiveNetworkQuotaInfo();
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

            case 18: // '\022'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag4 = isActiveNetworkMetered();
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag5 = requestRouteToHostAddress(parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                if(flag5)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.net.IConnectivityManager");
                i = tether(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.net.IConnectivityManager");
                i = untether(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.net.IConnectivityManager");
                i = getLastTetherError(parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag6 = isTetheringSupported(parcel.readString());
                parcel1.writeNoException();
                if(flag6)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.net.IConnectivityManager");
                i = parcel.readInt();
                boolean flag7;
                ResultReceiver resultreceiver;
                if(parcel.readInt() != 0)
                    resultreceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
                else
                    resultreceiver = null;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                startTethering(i, resultreceiver, flag7, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.net.IConnectivityManager");
                stopTethering(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getTetherableIfaces();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getTetheredIfaces();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getTetheringErroredIfaces();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getTetheredDhcpRanges();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getTetherableUsbRegexs();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getTetherableWifiRegexs();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getTetherableBluetoothRegexs();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag8;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                i = setUsbTethering(flag8, parcel.readString());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.net.IConnectivityManager");
                reportInetCondition(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag9;
                Network network1;
                if(parcel.readInt() != 0)
                    network1 = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    network1 = null;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                reportNetworkConnectivity(network1, flag9);
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getGlobalProxy();
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
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (ProxyInfo)ProxyInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setGlobalProxy(parcel);
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getProxyForNetwork(parcel);
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

            case 39: // '\''
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag10 = prepareVpn(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 40: // '('
                parcel.enforceInterface("android.net.IConnectivityManager");
                String s = parcel.readString();
                i = parcel.readInt();
                boolean flag11;
                if(parcel.readInt() != 0)
                    flag11 = true;
                else
                    flag11 = false;
                setVpnPackageAuthorization(s, i, flag11);
                parcel1.writeNoException();
                return true;

            case 41: // ')'
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (VpnConfig)VpnConfig.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = establishVpn(parcel);
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
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getVpnConfig(parcel.readInt());
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

            case 43: // '+'
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (VpnProfile)VpnProfile.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startLegacyVpn(parcel);
                parcel1.writeNoException();
                return true;

            case 44: // ','
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getLegacyVpnInfo(parcel.readInt());
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
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getAllVpnInfo();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag12 = updateLockdownVpn();
                parcel1.writeNoException();
                if(flag12)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag13 = isAlwaysOnVpnPackageSupported(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.net.IConnectivityManager");
                i = parcel.readInt();
                String s1 = parcel.readString();
                boolean flag14;
                if(parcel.readInt() != 0)
                    flag14 = true;
                else
                    flag14 = false;
                flag14 = setAlwaysOnVpnPackage(i, s1, flag14);
                parcel1.writeNoException();
                if(flag14)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getAlwaysOnVpnPackage(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.net.IConnectivityManager");
                i = checkMobileProvisioning(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getMobileProvisioningUrl();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag15;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                setProvisioningNotificationVisible(flag15, parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag16;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                setAirplaneMode(flag16);
                parcel1.writeNoException();
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.net.IConnectivityManager");
                Messenger messenger;
                if(parcel.readInt() != 0)
                    messenger = (Messenger)Messenger.CREATOR.createFromParcel(parcel);
                else
                    messenger = null;
                registerNetworkFactory(messenger, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag17;
                if(parcel.readInt() != 0)
                    parcel = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag17 = requestBandwidthUpdate(parcel);
                parcel1.writeNoException();
                if(flag17)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (Messenger)Messenger.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                unregisterNetworkFactory(parcel);
                parcel1.writeNoException();
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.net.IConnectivityManager");
                Messenger messenger1;
                NetworkInfo networkinfo;
                LinkProperties linkproperties;
                NetworkCapabilities networkcapabilities4;
                if(parcel.readInt() != 0)
                    messenger1 = (Messenger)Messenger.CREATOR.createFromParcel(parcel);
                else
                    messenger1 = null;
                if(parcel.readInt() != 0)
                    networkinfo = (NetworkInfo)NetworkInfo.CREATOR.createFromParcel(parcel);
                else
                    networkinfo = null;
                if(parcel.readInt() != 0)
                    linkproperties = (LinkProperties)LinkProperties.CREATOR.createFromParcel(parcel);
                else
                    linkproperties = null;
                if(parcel.readInt() != 0)
                    networkcapabilities4 = (NetworkCapabilities)NetworkCapabilities.CREATOR.createFromParcel(parcel);
                else
                    networkcapabilities4 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (NetworkMisc)NetworkMisc.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = registerNetworkAgent(messenger1, networkinfo, linkproperties, networkcapabilities4, i, parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.net.IConnectivityManager");
                NetworkCapabilities networkcapabilities;
                Messenger messenger2;
                if(parcel.readInt() != 0)
                    networkcapabilities = (NetworkCapabilities)NetworkCapabilities.CREATOR.createFromParcel(parcel);
                else
                    networkcapabilities = null;
                if(parcel.readInt() != 0)
                    messenger2 = (Messenger)Messenger.CREATOR.createFromParcel(parcel);
                else
                    messenger2 = null;
                parcel = requestNetwork(networkcapabilities, messenger2, parcel.readInt(), parcel.readStrongBinder(), parcel.readInt());
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

            case 59: // ';'
                parcel.enforceInterface("android.net.IConnectivityManager");
                NetworkCapabilities networkcapabilities1;
                if(parcel.readInt() != 0)
                    networkcapabilities1 = (NetworkCapabilities)NetworkCapabilities.CREATOR.createFromParcel(parcel);
                else
                    networkcapabilities1 = null;
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = pendingRequestForNetwork(networkcapabilities1, parcel);
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

            case 60: // '<'
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                releasePendingNetworkRequest(parcel);
                parcel1.writeNoException();
                return true;

            case 61: // '='
                parcel.enforceInterface("android.net.IConnectivityManager");
                NetworkCapabilities networkcapabilities2;
                Messenger messenger3;
                if(parcel.readInt() != 0)
                    networkcapabilities2 = (NetworkCapabilities)NetworkCapabilities.CREATOR.createFromParcel(parcel);
                else
                    networkcapabilities2 = null;
                if(parcel.readInt() != 0)
                    messenger3 = (Messenger)Messenger.CREATOR.createFromParcel(parcel);
                else
                    messenger3 = null;
                parcel = listenForNetwork(networkcapabilities2, messenger3, parcel.readStrongBinder());
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

            case 62: // '>'
                parcel.enforceInterface("android.net.IConnectivityManager");
                NetworkCapabilities networkcapabilities3;
                if(parcel.readInt() != 0)
                    networkcapabilities3 = (NetworkCapabilities)NetworkCapabilities.CREATOR.createFromParcel(parcel);
                else
                    networkcapabilities3 = null;
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                pendingListenForNetwork(networkcapabilities3, parcel);
                parcel1.writeNoException();
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (NetworkRequest)NetworkRequest.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                releaseNetworkRequest(parcel);
                parcel1.writeNoException();
                return true;

            case 64: // '@'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag18;
                Network network2;
                boolean flag22;
                if(parcel.readInt() != 0)
                    network2 = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    network2 = null;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                if(parcel.readInt() != 0)
                    flag22 = true;
                else
                    flag22 = false;
                setAcceptUnvalidated(network2, flag18, flag22);
                parcel1.writeNoException();
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setAvoidUnvalidated(parcel);
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                startCaptivePortalApp(parcel);
                parcel1.writeNoException();
                return true;

            case 67: // 'C'
                parcel.enforceInterface("android.net.IConnectivityManager");
                if(parcel.readInt() != 0)
                    parcel = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                i = getMultipathPreference(parcel);
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("android.net.IConnectivityManager");
                i = getRestoreDefaultNetworkDelay(parcel.readInt());
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 69: // 'E'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag19 = addVpnAddress(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag19)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 70: // 'F'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag20 = removeVpnAddress(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag20)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 71: // 'G'
                parcel.enforceInterface("android.net.IConnectivityManager");
                boolean flag21 = setUnderlyingNetworksForVpn((Network[])parcel.createTypedArray(Network.CREATOR));
                parcel1.writeNoException();
                if(flag21)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 72: // 'H'
                parcel.enforceInterface("android.net.IConnectivityManager");
                factoryReset();
                parcel1.writeNoException();
                return true;

            case 73: // 'I'
                parcel.enforceInterface("android.net.IConnectivityManager");
                Network network3;
                Messenger messenger4;
                if(parcel.readInt() != 0)
                    network3 = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    network3 = null;
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    messenger4 = (Messenger)Messenger.CREATOR.createFromParcel(parcel);
                else
                    messenger4 = null;
                startNattKeepalive(network3, i, messenger4, parcel.readStrongBinder(), parcel.readString(), parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 74: // 'J'
                parcel.enforceInterface("android.net.IConnectivityManager");
                Network network4;
                if(parcel.readInt() != 0)
                    network4 = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    network4 = null;
                stopKeepalive(network4, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 75: // 'K'
                parcel.enforceInterface("android.net.IConnectivityManager");
                parcel = getCaptivePortalServerUrl();
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.net.IConnectivityManager";
        static final int TRANSACTION_addVpnAddress = 69;
        static final int TRANSACTION_checkMobileProvisioning = 50;
        static final int TRANSACTION_establishVpn = 41;
        static final int TRANSACTION_factoryReset = 72;
        static final int TRANSACTION_getActiveLinkProperties = 12;
        static final int TRANSACTION_getActiveNetwork = 1;
        static final int TRANSACTION_getActiveNetworkForUid = 2;
        static final int TRANSACTION_getActiveNetworkInfo = 3;
        static final int TRANSACTION_getActiveNetworkInfoForUid = 4;
        static final int TRANSACTION_getActiveNetworkQuotaInfo = 17;
        static final int TRANSACTION_getAllNetworkInfo = 7;
        static final int TRANSACTION_getAllNetworkState = 16;
        static final int TRANSACTION_getAllNetworks = 9;
        static final int TRANSACTION_getAllVpnInfo = 45;
        static final int TRANSACTION_getAlwaysOnVpnPackage = 49;
        static final int TRANSACTION_getCaptivePortalServerUrl = 75;
        static final int TRANSACTION_getDefaultNetworkCapabilitiesForUser = 10;
        static final int TRANSACTION_getGlobalProxy = 36;
        static final int TRANSACTION_getLastTetherError = 22;
        static final int TRANSACTION_getLegacyVpnInfo = 44;
        static final int TRANSACTION_getLinkProperties = 14;
        static final int TRANSACTION_getLinkPropertiesForType = 13;
        static final int TRANSACTION_getMobileProvisioningUrl = 51;
        static final int TRANSACTION_getMultipathPreference = 67;
        static final int TRANSACTION_getNetworkCapabilities = 15;
        static final int TRANSACTION_getNetworkForType = 8;
        static final int TRANSACTION_getNetworkInfo = 5;
        static final int TRANSACTION_getNetworkInfoForUid = 6;
        static final int TRANSACTION_getProxyForNetwork = 38;
        static final int TRANSACTION_getRestoreDefaultNetworkDelay = 68;
        static final int TRANSACTION_getTetherableBluetoothRegexs = 32;
        static final int TRANSACTION_getTetherableIfaces = 26;
        static final int TRANSACTION_getTetherableUsbRegexs = 30;
        static final int TRANSACTION_getTetherableWifiRegexs = 31;
        static final int TRANSACTION_getTetheredDhcpRanges = 29;
        static final int TRANSACTION_getTetheredIfaces = 27;
        static final int TRANSACTION_getTetheringErroredIfaces = 28;
        static final int TRANSACTION_getVpnConfig = 42;
        static final int TRANSACTION_isActiveNetworkMetered = 18;
        static final int TRANSACTION_isAlwaysOnVpnPackageSupported = 47;
        static final int TRANSACTION_isNetworkSupported = 11;
        static final int TRANSACTION_isTetheringSupported = 23;
        static final int TRANSACTION_listenForNetwork = 61;
        static final int TRANSACTION_pendingListenForNetwork = 62;
        static final int TRANSACTION_pendingRequestForNetwork = 59;
        static final int TRANSACTION_prepareVpn = 39;
        static final int TRANSACTION_registerNetworkAgent = 57;
        static final int TRANSACTION_registerNetworkFactory = 54;
        static final int TRANSACTION_releaseNetworkRequest = 63;
        static final int TRANSACTION_releasePendingNetworkRequest = 60;
        static final int TRANSACTION_removeVpnAddress = 70;
        static final int TRANSACTION_reportInetCondition = 34;
        static final int TRANSACTION_reportNetworkConnectivity = 35;
        static final int TRANSACTION_requestBandwidthUpdate = 55;
        static final int TRANSACTION_requestNetwork = 58;
        static final int TRANSACTION_requestRouteToHostAddress = 19;
        static final int TRANSACTION_setAcceptUnvalidated = 64;
        static final int TRANSACTION_setAirplaneMode = 53;
        static final int TRANSACTION_setAlwaysOnVpnPackage = 48;
        static final int TRANSACTION_setAvoidUnvalidated = 65;
        static final int TRANSACTION_setGlobalProxy = 37;
        static final int TRANSACTION_setProvisioningNotificationVisible = 52;
        static final int TRANSACTION_setUnderlyingNetworksForVpn = 71;
        static final int TRANSACTION_setUsbTethering = 33;
        static final int TRANSACTION_setVpnPackageAuthorization = 40;
        static final int TRANSACTION_startCaptivePortalApp = 66;
        static final int TRANSACTION_startLegacyVpn = 43;
        static final int TRANSACTION_startNattKeepalive = 73;
        static final int TRANSACTION_startTethering = 24;
        static final int TRANSACTION_stopKeepalive = 74;
        static final int TRANSACTION_stopTethering = 25;
        static final int TRANSACTION_tether = 20;
        static final int TRANSACTION_unregisterNetworkFactory = 56;
        static final int TRANSACTION_untether = 21;
        static final int TRANSACTION_updateLockdownVpn = 46;

        public Stub()
        {
            attachInterface(this, "android.net.IConnectivityManager");
        }
    }

    private static class Stub.Proxy
        implements IConnectivityManager
    {

        public boolean addVpnAddress(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public int checkMobileProvisioning(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
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

        public ParcelFileDescriptor establishVpn(VpnConfig vpnconfig)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(vpnconfig == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            vpnconfig.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            vpnconfig = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return vpnconfig;
_L2:
            parcel.writeInt(0);
              goto _L3
            vpnconfig;
            parcel1.recycle();
            parcel.recycle();
            throw vpnconfig;
            vpnconfig = null;
              goto _L4
        }

        public void factoryReset()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(72, parcel, parcel1, 0);
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

        public LinkProperties getActiveLinkProperties()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            LinkProperties linkproperties = (LinkProperties)LinkProperties.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return linkproperties;
_L2:
            linkproperties = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Network getActiveNetwork()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(1, parcel, parcel1, 0);
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

        public Network getActiveNetworkForUid(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public NetworkInfo getActiveNetworkInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            NetworkInfo networkinfo = (NetworkInfo)NetworkInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networkinfo;
_L2:
            networkinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public NetworkInfo getActiveNetworkInfoForUid(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            NetworkInfo networkinfo = (NetworkInfo)NetworkInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networkinfo;
_L2:
            networkinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public NetworkQuotaInfo getActiveNetworkQuotaInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            NetworkQuotaInfo networkquotainfo = (NetworkQuotaInfo)NetworkQuotaInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networkquotainfo;
_L2:
            networkquotainfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public NetworkInfo[] getAllNetworkInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            NetworkInfo anetworkinfo[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            anetworkinfo = (NetworkInfo[])parcel1.createTypedArray(NetworkInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return anetworkinfo;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public NetworkState[] getAllNetworkState()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            NetworkState anetworkstate[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            anetworkstate = (NetworkState[])parcel1.createTypedArray(NetworkState.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return anetworkstate;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public Network[] getAllNetworks()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            Network anetwork[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            anetwork = (Network[])parcel1.createTypedArray(Network.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return anetwork;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public VpnInfo[] getAllVpnInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            VpnInfo avpninfo[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            avpninfo = (VpnInfo[])parcel1.createTypedArray(VpnInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return avpninfo;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getAlwaysOnVpnPackage(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            mRemote.transact(49, parcel, parcel1, 0);
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

        public String getCaptivePortalServerUrl()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
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

        public NetworkCapabilities[] getDefaultNetworkCapabilitiesForUser(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            NetworkCapabilities anetworkcapabilities[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            anetworkcapabilities = (NetworkCapabilities[])parcel1.createTypedArray(NetworkCapabilities.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return anetworkcapabilities;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public ProxyInfo getGlobalProxy()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(36, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ProxyInfo proxyinfo = (ProxyInfo)ProxyInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return proxyinfo;
_L2:
            proxyinfo = null;
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
            return "android.net.IConnectivityManager";
        }

        public int getLastTetherError(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeString(s);
            mRemote.transact(22, parcel, parcel1, 0);
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

        public LegacyVpnInfo getLegacyVpnInfo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            mRemote.transact(44, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            LegacyVpnInfo legacyvpninfo = (LegacyVpnInfo)LegacyVpnInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return legacyvpninfo;
_L2:
            legacyvpninfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public LinkProperties getLinkProperties(Network network)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            network = (LinkProperties)LinkProperties.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return network;
_L2:
            parcel.writeInt(0);
              goto _L3
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
            network = null;
              goto _L4
        }

        public LinkProperties getLinkPropertiesForType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            LinkProperties linkproperties = (LinkProperties)LinkProperties.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return linkproperties;
_L2:
            linkproperties = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getMobileProvisioningUrl()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String s;
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(51, parcel, parcel1, 0);
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

        public int getMultipathPreference(Network network)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null)
                break MISSING_BLOCK_LABEL_65;
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(67, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            parcel.writeInt(0);
              goto _L1
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
        }

        public NetworkCapabilities getNetworkCapabilities(Network network)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            network = (NetworkCapabilities)NetworkCapabilities.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return network;
_L2:
            parcel.writeInt(0);
              goto _L3
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
            network = null;
              goto _L4
        }

        public Network getNetworkForType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            mRemote.transact(8, parcel, parcel1, 0);
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

        public NetworkInfo getNetworkInfo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            NetworkInfo networkinfo = (NetworkInfo)NetworkInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return networkinfo;
_L2:
            networkinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public NetworkInfo getNetworkInfoForUid(Network network, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_139;
            network = (NetworkInfo)NetworkInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return network;
_L2:
            parcel.writeInt(0);
              goto _L3
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
            network = null;
              goto _L4
        }

        public ProxyInfo getProxyForNetwork(Network network)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(38, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_97;
            network = (ProxyInfo)ProxyInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return network;
_L2:
            parcel.writeInt(0);
              goto _L3
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
            network = null;
              goto _L4
        }

        public int getRestoreDefaultNetworkDelay(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            mRemote.transact(68, parcel, parcel1, 0);
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

        public String[] getTetherableBluetoothRegexs()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(32, parcel, parcel1, 0);
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

        public String[] getTetherableIfaces()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(26, parcel, parcel1, 0);
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

        public String[] getTetherableUsbRegexs()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(30, parcel, parcel1, 0);
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

        public String[] getTetherableWifiRegexs()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
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

        public String[] getTetheredDhcpRanges()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
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

        public String[] getTetheredIfaces()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(27, parcel, parcel1, 0);
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

        public String[] getTetheringErroredIfaces()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            mRemote.transact(28, parcel, parcel1, 0);
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

        public VpnConfig getVpnConfig(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            mRemote.transact(42, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            VpnConfig vpnconfig = (VpnConfig)VpnConfig.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return vpnconfig;
_L2:
            vpnconfig = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isActiveNetworkMetered()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isAlwaysOnVpnPackageSupported(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(47, parcel, parcel1, 0);
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

        public boolean isNetworkSupported(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
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

        public boolean isTetheringSupported(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public NetworkRequest listenForNetwork(NetworkCapabilities networkcapabilities, Messenger messenger, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(networkcapabilities == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networkcapabilities.writeToParcel(parcel, 0);
_L5:
            if(messenger == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            messenger.writeToParcel(parcel, 0);
_L6:
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(61, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_144;
            networkcapabilities = (NetworkRequest)NetworkRequest.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return networkcapabilities;
_L2:
            parcel.writeInt(0);
              goto _L5
            networkcapabilities;
            parcel1.recycle();
            parcel.recycle();
            throw networkcapabilities;
_L4:
            parcel.writeInt(0);
              goto _L6
            networkcapabilities = null;
              goto _L7
        }

        public void pendingListenForNetwork(NetworkCapabilities networkcapabilities, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(networkcapabilities == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networkcapabilities.writeToParcel(parcel, 0);
_L3:
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(62, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            networkcapabilities;
            parcel1.recycle();
            parcel.recycle();
            throw networkcapabilities;
            parcel.writeInt(0);
              goto _L4
        }

        public NetworkRequest pendingRequestForNetwork(NetworkCapabilities networkcapabilities, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(networkcapabilities == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networkcapabilities.writeToParcel(parcel, 0);
_L5:
            if(pendingintent == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L6:
            mRemote.transact(59, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_127;
            networkcapabilities = (NetworkRequest)NetworkRequest.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return networkcapabilities;
_L2:
            parcel.writeInt(0);
              goto _L5
            networkcapabilities;
            parcel1.recycle();
            parcel.recycle();
            throw networkcapabilities;
_L4:
            parcel.writeInt(0);
              goto _L6
            networkcapabilities = null;
              goto _L7
        }

        public boolean prepareVpn(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public int registerNetworkAgent(Messenger messenger, NetworkInfo networkinfo, LinkProperties linkproperties, NetworkCapabilities networkcapabilities, int i, NetworkMisc networkmisc)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(messenger == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            messenger.writeToParcel(parcel, 0);
_L9:
            if(networkinfo == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            networkinfo.writeToParcel(parcel, 0);
_L10:
            if(linkproperties == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            linkproperties.writeToParcel(parcel, 0);
_L11:
            if(networkcapabilities == null) goto _L8; else goto _L7
_L7:
            parcel.writeInt(1);
            networkcapabilities.writeToParcel(parcel, 0);
_L12:
            parcel.writeInt(i);
            if(networkmisc == null)
                break MISSING_BLOCK_LABEL_204;
            parcel.writeInt(1);
            networkmisc.writeToParcel(parcel, 0);
_L13:
            mRemote.transact(57, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
_L2:
            parcel.writeInt(0);
              goto _L9
            messenger;
            parcel1.recycle();
            parcel.recycle();
            throw messenger;
_L4:
            parcel.writeInt(0);
              goto _L10
_L6:
            parcel.writeInt(0);
              goto _L11
_L8:
            parcel.writeInt(0);
              goto _L12
            parcel.writeInt(0);
              goto _L13
        }

        public void registerNetworkFactory(Messenger messenger, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(messenger == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            messenger.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(54, parcel, parcel1, 0);
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

        public void releaseNetworkRequest(NetworkRequest networkrequest)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(networkrequest == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            networkrequest.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(63, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            networkrequest;
            parcel1.recycle();
            parcel.recycle();
            throw networkrequest;
        }

        public void releasePendingNetworkRequest(PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(60, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            pendingintent;
            parcel1.recycle();
            parcel.recycle();
            throw pendingintent;
        }

        public boolean removeVpnAddress(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void reportInetCondition(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public void reportNetworkConnectivity(Network network, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
        }

        public boolean requestBandwidthUpdate(Network network)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L1:
            int i;
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
            parcel.writeInt(0);
              goto _L1
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
        }

        public NetworkRequest requestNetwork(NetworkCapabilities networkcapabilities, Messenger messenger, int i, IBinder ibinder, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(networkcapabilities == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            networkcapabilities.writeToParcel(parcel, 0);
_L5:
            if(messenger == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            messenger.writeToParcel(parcel, 0);
_L6:
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(j);
            mRemote.transact(58, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_158;
            networkcapabilities = (NetworkRequest)NetworkRequest.CREATOR.createFromParcel(parcel1);
_L7:
            parcel1.recycle();
            parcel.recycle();
            return networkcapabilities;
_L2:
            parcel.writeInt(0);
              goto _L5
            networkcapabilities;
            parcel1.recycle();
            parcel.recycle();
            throw networkcapabilities;
_L4:
            parcel.writeInt(0);
              goto _L6
            networkcapabilities = null;
              goto _L7
        }

        public boolean requestRouteToHostAddress(int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
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
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void setAcceptUnvalidated(Network network, boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null)
                break MISSING_BLOCK_LABEL_99;
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L1:
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            if(flag1)
                i = ((flag2) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(64, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
        }

        public void setAirplaneMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(53, parcel, parcel1, 0);
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

        public boolean setAlwaysOnVpnPackage(int i, String s, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            parcel.writeString(s);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(48, parcel, parcel1, 0);
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

        public void setAvoidUnvalidated(Network network)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(65, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
        }

        public void setGlobalProxy(ProxyInfo proxyinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(proxyinfo == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            proxyinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            proxyinfo;
            parcel1.recycle();
            parcel.recycle();
            throw proxyinfo;
        }

        public void setProvisioningNotificationVisible(boolean flag, int i, String s)
            throws RemoteException
        {
            int j;
            Parcel parcel;
            Parcel parcel1;
            j = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(flag)
                j = 1;
            parcel.writeInt(j);
            parcel.writeInt(i);
            parcel.writeString(s);
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

        public boolean setUnderlyingNetworksForVpn(Network anetwork[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeTypedArray(anetwork, 0);
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
            anetwork;
            parcel1.recycle();
            parcel.recycle();
            throw anetwork;
        }

        public int setUsbTethering(boolean flag, String s)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(flag)
                i = 1;
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

        public void setVpnPackageAuthorization(String s, int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
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

        public void startCaptivePortalApp(Network network)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(66, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
        }

        public void startLegacyVpn(VpnProfile vpnprofile)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(vpnprofile == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            vpnprofile.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(43, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            vpnprofile;
            parcel1.recycle();
            parcel.recycle();
            throw vpnprofile;
        }

        public void startNattKeepalive(Network network, int i, Messenger messenger, IBinder ibinder, String s, int j, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            if(messenger == null)
                break MISSING_BLOCK_LABEL_140;
            parcel.writeInt(1);
            messenger.writeToParcel(parcel, 0);
_L4:
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            parcel.writeInt(j);
            parcel.writeString(s1);
            mRemote.transact(73, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
            parcel.writeInt(0);
              goto _L4
        }

        public void startTethering(int i, ResultReceiver resultreceiver, boolean flag, String s)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
            if(resultreceiver == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            resultreceiver.writeToParcel(parcel, 0);
_L1:
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            resultreceiver;
            parcel1.recycle();
            parcel.recycle();
            throw resultreceiver;
        }

        public void stopKeepalive(Network network, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(network == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(74, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            network;
            parcel1.recycle();
            parcel.recycle();
            throw network;
        }

        public void stopTethering(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeInt(i);
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

        public int tether(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(20, parcel, parcel1, 0);
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

        public void unregisterNetworkFactory(Messenger messenger)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            if(messenger == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            messenger.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(56, parcel, parcel1, 0);
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

        public int untether(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(21, parcel, parcel1, 0);
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

        public boolean updateLockdownVpn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.net.IConnectivityManager");
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


    public abstract boolean addVpnAddress(String s, int i)
        throws RemoteException;

    public abstract int checkMobileProvisioning(int i)
        throws RemoteException;

    public abstract ParcelFileDescriptor establishVpn(VpnConfig vpnconfig)
        throws RemoteException;

    public abstract void factoryReset()
        throws RemoteException;

    public abstract LinkProperties getActiveLinkProperties()
        throws RemoteException;

    public abstract Network getActiveNetwork()
        throws RemoteException;

    public abstract Network getActiveNetworkForUid(int i, boolean flag)
        throws RemoteException;

    public abstract NetworkInfo getActiveNetworkInfo()
        throws RemoteException;

    public abstract NetworkInfo getActiveNetworkInfoForUid(int i, boolean flag)
        throws RemoteException;

    public abstract NetworkQuotaInfo getActiveNetworkQuotaInfo()
        throws RemoteException;

    public abstract NetworkInfo[] getAllNetworkInfo()
        throws RemoteException;

    public abstract NetworkState[] getAllNetworkState()
        throws RemoteException;

    public abstract Network[] getAllNetworks()
        throws RemoteException;

    public abstract VpnInfo[] getAllVpnInfo()
        throws RemoteException;

    public abstract String getAlwaysOnVpnPackage(int i)
        throws RemoteException;

    public abstract String getCaptivePortalServerUrl()
        throws RemoteException;

    public abstract NetworkCapabilities[] getDefaultNetworkCapabilitiesForUser(int i)
        throws RemoteException;

    public abstract ProxyInfo getGlobalProxy()
        throws RemoteException;

    public abstract int getLastTetherError(String s)
        throws RemoteException;

    public abstract LegacyVpnInfo getLegacyVpnInfo(int i)
        throws RemoteException;

    public abstract LinkProperties getLinkProperties(Network network)
        throws RemoteException;

    public abstract LinkProperties getLinkPropertiesForType(int i)
        throws RemoteException;

    public abstract String getMobileProvisioningUrl()
        throws RemoteException;

    public abstract int getMultipathPreference(Network network)
        throws RemoteException;

    public abstract NetworkCapabilities getNetworkCapabilities(Network network)
        throws RemoteException;

    public abstract Network getNetworkForType(int i)
        throws RemoteException;

    public abstract NetworkInfo getNetworkInfo(int i)
        throws RemoteException;

    public abstract NetworkInfo getNetworkInfoForUid(Network network, int i, boolean flag)
        throws RemoteException;

    public abstract ProxyInfo getProxyForNetwork(Network network)
        throws RemoteException;

    public abstract int getRestoreDefaultNetworkDelay(int i)
        throws RemoteException;

    public abstract String[] getTetherableBluetoothRegexs()
        throws RemoteException;

    public abstract String[] getTetherableIfaces()
        throws RemoteException;

    public abstract String[] getTetherableUsbRegexs()
        throws RemoteException;

    public abstract String[] getTetherableWifiRegexs()
        throws RemoteException;

    public abstract String[] getTetheredDhcpRanges()
        throws RemoteException;

    public abstract String[] getTetheredIfaces()
        throws RemoteException;

    public abstract String[] getTetheringErroredIfaces()
        throws RemoteException;

    public abstract VpnConfig getVpnConfig(int i)
        throws RemoteException;

    public abstract boolean isActiveNetworkMetered()
        throws RemoteException;

    public abstract boolean isAlwaysOnVpnPackageSupported(int i, String s)
        throws RemoteException;

    public abstract boolean isNetworkSupported(int i)
        throws RemoteException;

    public abstract boolean isTetheringSupported(String s)
        throws RemoteException;

    public abstract NetworkRequest listenForNetwork(NetworkCapabilities networkcapabilities, Messenger messenger, IBinder ibinder)
        throws RemoteException;

    public abstract void pendingListenForNetwork(NetworkCapabilities networkcapabilities, PendingIntent pendingintent)
        throws RemoteException;

    public abstract NetworkRequest pendingRequestForNetwork(NetworkCapabilities networkcapabilities, PendingIntent pendingintent)
        throws RemoteException;

    public abstract boolean prepareVpn(String s, String s1, int i)
        throws RemoteException;

    public abstract int registerNetworkAgent(Messenger messenger, NetworkInfo networkinfo, LinkProperties linkproperties, NetworkCapabilities networkcapabilities, int i, NetworkMisc networkmisc)
        throws RemoteException;

    public abstract void registerNetworkFactory(Messenger messenger, String s)
        throws RemoteException;

    public abstract void releaseNetworkRequest(NetworkRequest networkrequest)
        throws RemoteException;

    public abstract void releasePendingNetworkRequest(PendingIntent pendingintent)
        throws RemoteException;

    public abstract boolean removeVpnAddress(String s, int i)
        throws RemoteException;

    public abstract void reportInetCondition(int i, int j)
        throws RemoteException;

    public abstract void reportNetworkConnectivity(Network network, boolean flag)
        throws RemoteException;

    public abstract boolean requestBandwidthUpdate(Network network)
        throws RemoteException;

    public abstract NetworkRequest requestNetwork(NetworkCapabilities networkcapabilities, Messenger messenger, int i, IBinder ibinder, int j)
        throws RemoteException;

    public abstract boolean requestRouteToHostAddress(int i, byte abyte0[])
        throws RemoteException;

    public abstract void setAcceptUnvalidated(Network network, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void setAirplaneMode(boolean flag)
        throws RemoteException;

    public abstract boolean setAlwaysOnVpnPackage(int i, String s, boolean flag)
        throws RemoteException;

    public abstract void setAvoidUnvalidated(Network network)
        throws RemoteException;

    public abstract void setGlobalProxy(ProxyInfo proxyinfo)
        throws RemoteException;

    public abstract void setProvisioningNotificationVisible(boolean flag, int i, String s)
        throws RemoteException;

    public abstract boolean setUnderlyingNetworksForVpn(Network anetwork[])
        throws RemoteException;

    public abstract int setUsbTethering(boolean flag, String s)
        throws RemoteException;

    public abstract void setVpnPackageAuthorization(String s, int i, boolean flag)
        throws RemoteException;

    public abstract void startCaptivePortalApp(Network network)
        throws RemoteException;

    public abstract void startLegacyVpn(VpnProfile vpnprofile)
        throws RemoteException;

    public abstract void startNattKeepalive(Network network, int i, Messenger messenger, IBinder ibinder, String s, int j, String s1)
        throws RemoteException;

    public abstract void startTethering(int i, ResultReceiver resultreceiver, boolean flag, String s)
        throws RemoteException;

    public abstract void stopKeepalive(Network network, int i)
        throws RemoteException;

    public abstract void stopTethering(int i, String s)
        throws RemoteException;

    public abstract int tether(String s, String s1)
        throws RemoteException;

    public abstract void unregisterNetworkFactory(Messenger messenger)
        throws RemoteException;

    public abstract int untether(String s, String s1)
        throws RemoteException;

    public abstract boolean updateLockdownVpn()
        throws RemoteException;
}
