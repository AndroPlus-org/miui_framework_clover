// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.net.*;
import java.util.List;

// Referenced classes of package android.os:
//            IInterface, RemoteException, IBinder, INetworkActivityListener, 
//            Binder, Parcel

public interface INetworkManagementService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements INetworkManagementService
    {

        public static INetworkManagementService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.os.INetworkManagementService");
            if(iinterface != null && (iinterface instanceof INetworkManagementService))
                return (INetworkManagementService)iinterface;
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
                parcel1.writeString("android.os.INetworkManagementService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.os.INetworkManagementService");
                registerObserver(android.net.INetworkManagementEventObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.os.INetworkManagementService");
                unregisterObserver(android.net.INetworkManagementEventObserver.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = getNetdService();
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = listInterfaces();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = getInterfaceConfig(parcel.readString());
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
                parcel.enforceInterface("android.os.INetworkManagementService");
                String s = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (InterfaceConfiguration)InterfaceConfiguration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setInterfaceConfig(s, parcel);
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.os.INetworkManagementService");
                clearInterfaceAddresses(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setInterfaceDown(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setInterfaceUp(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.os.INetworkManagementService");
                String s1 = parcel.readString();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                setInterfaceIpv6PrivacyExtensions(s1, flag);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.os.INetworkManagementService");
                disableIpv6(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.os.INetworkManagementService");
                enableIpv6(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setIPv6AddrGenMode(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.os.INetworkManagementService");
                String s2 = parcel.readString();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                setInterfaceIpv6NdOffload(s2, flag1);
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.os.INetworkManagementService");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (RouteInfo)RouteInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                addRoute(i, parcel);
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.os.INetworkManagementService");
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (RouteInfo)RouteInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                removeRoute(i, parcel);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setMtu(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.os.INetworkManagementService");
                shutdown();
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag2 = getIpForwardingEnabled();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setIpForwardingEnabled(flag3);
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.os.INetworkManagementService");
                startTethering(parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.os.INetworkManagementService");
                stopTethering();
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag4 = isTetheringStarted();
                parcel1.writeNoException();
                if(flag4)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.os.INetworkManagementService");
                tetherInterface(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.os.INetworkManagementService");
                untetherInterface(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = listTetheredInterfaces();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.os.INetworkManagementService");
                Network network;
                if(parcel.readInt() != 0)
                    network = (Network)Network.CREATOR.createFromParcel(parcel);
                else
                    network = null;
                setDnsForwarders(network, parcel.createStringArray());
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = getDnsForwarders();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.os.INetworkManagementService");
                startInterfaceForwarding(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.os.INetworkManagementService");
                stopInterfaceForwarding(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.os.INetworkManagementService");
                enableNat(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.os.INetworkManagementService");
                disableNat(parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.os.INetworkManagementService");
                registerTetheringStatsProvider(android.net.ITetheringStatsProvider.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.os.INetworkManagementService");
                unregisterTetheringStatsProvider(android.net.ITetheringStatsProvider.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("android.os.INetworkManagementService");
                tetherLimitReached(android.net.ITetheringStatsProvider.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = listTtys();
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 37: // '%'
                parcel.enforceInterface("android.os.INetworkManagementService");
                attachPppd(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("android.os.INetworkManagementService");
                detachPppd(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = getNetworkStatsSummaryDev();
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

            case 40: // '('
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = getNetworkStatsSummaryXt();
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

            case 41: // ')'
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = getNetworkStatsDetail();
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
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = getNetworkStatsUidDetail(parcel.readInt());
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
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = getNetworkStatsTethering(parcel.readInt());
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

            case 44: // ','
                parcel.enforceInterface("android.os.INetworkManagementService");
                setInterfaceQuota(parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 45: // '-'
                parcel.enforceInterface("android.os.INetworkManagementService");
                removeInterfaceQuota(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 46: // '.'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setInterfaceAlert(parcel.readString(), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 47: // '/'
                parcel.enforceInterface("android.os.INetworkManagementService");
                removeInterfaceAlert(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 48: // '0'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setGlobalAlert(parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("android.os.INetworkManagementService");
                i = parcel.readInt();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                setUidMeteredNetworkBlacklist(i, flag5);
                parcel1.writeNoException();
                return true;

            case 50: // '2'
                parcel.enforceInterface("android.os.INetworkManagementService");
                i = parcel.readInt();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                setUidMeteredNetworkWhitelist(i, flag6);
                parcel1.writeNoException();
                return true;

            case 51: // '3'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                flag7 = setDataSaverModeEnabled(flag7);
                parcel1.writeNoException();
                if(flag7)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 52: // '4'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setUidCleartextNetworkPolicy(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 53: // '5'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag8 = isBandwidthControlEnabled();
                parcel1.writeNoException();
                if(flag8)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 54: // '6'
                parcel.enforceInterface("android.os.INetworkManagementService");
                addIdleTimer(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 55: // '7'
                parcel.enforceInterface("android.os.INetworkManagementService");
                removeIdleTimer(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 56: // '8'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setDnsConfigurationForNetwork(parcel.readInt(), parcel.createStringArray(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 57: // '9'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag9;
                if(parcel.readInt() != 0)
                    flag9 = true;
                else
                    flag9 = false;
                setFirewallEnabled(flag9);
                parcel1.writeNoException();
                return true;

            case 58: // ':'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag10 = isFirewallEnabled();
                parcel1.writeNoException();
                if(flag10)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 59: // ';'
                parcel.enforceInterface("android.os.INetworkManagementService");
                String s3 = parcel.readString();
                boolean flag11;
                if(parcel.readInt() != 0)
                    flag11 = true;
                else
                    flag11 = false;
                setFirewallInterfaceRule(s3, flag11);
                parcel1.writeNoException();
                return true;

            case 60: // '<'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setFirewallUidRule(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 61: // '='
                parcel.enforceInterface("android.os.INetworkManagementService");
                setFirewallUidRules(parcel.readInt(), parcel.createIntArray(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 62: // '>'
                parcel.enforceInterface("android.os.INetworkManagementService");
                i = parcel.readInt();
                boolean flag12;
                if(parcel.readInt() != 0)
                    flag12 = true;
                else
                    flag12 = false;
                setFirewallChainEnabled(i, flag12);
                parcel1.writeNoException();
                return true;

            case 63: // '?'
                parcel.enforceInterface("android.os.INetworkManagementService");
                addVpnUidRanges(parcel.readInt(), (UidRange[])parcel.createTypedArray(UidRange.CREATOR));
                parcel1.writeNoException();
                return true;

            case 64: // '@'
                parcel.enforceInterface("android.os.INetworkManagementService");
                removeVpnUidRanges(parcel.readInt(), (UidRange[])parcel.createTypedArray(UidRange.CREATOR));
                parcel1.writeNoException();
                return true;

            case 65: // 'A'
                parcel.enforceInterface("android.os.INetworkManagementService");
                startClatd(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("android.os.INetworkManagementService");
                stopClatd(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 67: // 'C'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag13 = isClatdStarted(parcel.readString());
                parcel1.writeNoException();
                if(flag13)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 68: // 'D'
                parcel.enforceInterface("android.os.INetworkManagementService");
                registerNetworkActivityListener(INetworkActivityListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 69: // 'E'
                parcel.enforceInterface("android.os.INetworkManagementService");
                unregisterNetworkActivityListener(INetworkActivityListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 70: // 'F'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag14 = isNetworkActive();
                parcel1.writeNoException();
                if(flag14)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 71: // 'G'
                parcel.enforceInterface("android.os.INetworkManagementService");
                createPhysicalNetwork(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 72: // 'H'
                parcel.enforceInterface("android.os.INetworkManagementService");
                i = parcel.readInt();
                boolean flag15;
                boolean flag18;
                if(parcel.readInt() != 0)
                    flag15 = true;
                else
                    flag15 = false;
                if(parcel.readInt() != 0)
                    flag18 = true;
                else
                    flag18 = false;
                createVirtualNetwork(i, flag15, flag18);
                parcel1.writeNoException();
                return true;

            case 73: // 'I'
                parcel.enforceInterface("android.os.INetworkManagementService");
                removeNetwork(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 74: // 'J'
                parcel.enforceInterface("android.os.INetworkManagementService");
                addInterfaceToNetwork(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 75: // 'K'
                parcel.enforceInterface("android.os.INetworkManagementService");
                removeInterfaceFromNetwork(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 76: // 'L'
                parcel.enforceInterface("android.os.INetworkManagementService");
                i = parcel.readInt();
                RouteInfo routeinfo;
                if(parcel.readInt() != 0)
                    routeinfo = (RouteInfo)RouteInfo.CREATOR.createFromParcel(parcel);
                else
                    routeinfo = null;
                addLegacyRouteForNetId(i, routeinfo, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 77: // 'M'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setDefaultNetId(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 78: // 'N'
                parcel.enforceInterface("android.os.INetworkManagementService");
                clearDefaultNetId();
                parcel1.writeNoException();
                return true;

            case 79: // 'O'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setNetworkPermission(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 80: // 'P'
                parcel.enforceInterface("android.os.INetworkManagementService");
                setPermission(parcel.readString(), parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 81: // 'Q'
                parcel.enforceInterface("android.os.INetworkManagementService");
                clearPermission(parcel.createIntArray());
                parcel1.writeNoException();
                return true;

            case 82: // 'R'
                parcel.enforceInterface("android.os.INetworkManagementService");
                allowProtect(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 83: // 'S'
                parcel.enforceInterface("android.os.INetworkManagementService");
                denyProtect(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 84: // 'T'
                parcel.enforceInterface("android.os.INetworkManagementService");
                addInterfaceToLocalNetwork(parcel.readString(), parcel.createTypedArrayList(RouteInfo.CREATOR));
                parcel1.writeNoException();
                return true;

            case 85: // 'U'
                parcel.enforceInterface("android.os.INetworkManagementService");
                removeInterfaceFromLocalNetwork(parcel.readString());
                parcel1.writeNoException();
                return true;

            case 86: // 'V'
                parcel.enforceInterface("android.os.INetworkManagementService");
                i = removeRoutesFromLocalNetwork(parcel.createTypedArrayList(RouteInfo.CREATOR));
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 87: // 'W'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag16;
                if(parcel.readInt() != 0)
                    flag16 = true;
                else
                    flag16 = false;
                setAllowOnlyVpnForUids(flag16, (UidRange[])parcel.createTypedArray(UidRange.CREATOR));
                parcel1.writeNoException();
                return true;

            case 88: // 'X'
                parcel.enforceInterface("android.os.INetworkManagementService");
                boolean flag17 = isNetworkRestricted(parcel.readInt());
                parcel1.writeNoException();
                if(flag17)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 89: // 'Y'
                parcel.enforceInterface("android.os.INetworkManagementService");
                parcel = getMiuiNetworkManager();
                parcel1.writeNoException();
                parcel1.writeStrongBinder(parcel);
                return true;
            }
        }

        private static final String DESCRIPTOR = "android.os.INetworkManagementService";
        static final int TRANSACTION_addIdleTimer = 54;
        static final int TRANSACTION_addInterfaceToLocalNetwork = 84;
        static final int TRANSACTION_addInterfaceToNetwork = 74;
        static final int TRANSACTION_addLegacyRouteForNetId = 76;
        static final int TRANSACTION_addRoute = 15;
        static final int TRANSACTION_addVpnUidRanges = 63;
        static final int TRANSACTION_allowProtect = 82;
        static final int TRANSACTION_attachPppd = 37;
        static final int TRANSACTION_clearDefaultNetId = 78;
        static final int TRANSACTION_clearInterfaceAddresses = 7;
        static final int TRANSACTION_clearPermission = 81;
        static final int TRANSACTION_createPhysicalNetwork = 71;
        static final int TRANSACTION_createVirtualNetwork = 72;
        static final int TRANSACTION_denyProtect = 83;
        static final int TRANSACTION_detachPppd = 38;
        static final int TRANSACTION_disableIpv6 = 11;
        static final int TRANSACTION_disableNat = 32;
        static final int TRANSACTION_enableIpv6 = 12;
        static final int TRANSACTION_enableNat = 31;
        static final int TRANSACTION_getDnsForwarders = 28;
        static final int TRANSACTION_getInterfaceConfig = 5;
        static final int TRANSACTION_getIpForwardingEnabled = 19;
        static final int TRANSACTION_getMiuiNetworkManager = 89;
        static final int TRANSACTION_getNetdService = 3;
        static final int TRANSACTION_getNetworkStatsDetail = 41;
        static final int TRANSACTION_getNetworkStatsSummaryDev = 39;
        static final int TRANSACTION_getNetworkStatsSummaryXt = 40;
        static final int TRANSACTION_getNetworkStatsTethering = 43;
        static final int TRANSACTION_getNetworkStatsUidDetail = 42;
        static final int TRANSACTION_isBandwidthControlEnabled = 53;
        static final int TRANSACTION_isClatdStarted = 67;
        static final int TRANSACTION_isFirewallEnabled = 58;
        static final int TRANSACTION_isNetworkActive = 70;
        static final int TRANSACTION_isNetworkRestricted = 88;
        static final int TRANSACTION_isTetheringStarted = 23;
        static final int TRANSACTION_listInterfaces = 4;
        static final int TRANSACTION_listTetheredInterfaces = 26;
        static final int TRANSACTION_listTtys = 36;
        static final int TRANSACTION_registerNetworkActivityListener = 68;
        static final int TRANSACTION_registerObserver = 1;
        static final int TRANSACTION_registerTetheringStatsProvider = 33;
        static final int TRANSACTION_removeIdleTimer = 55;
        static final int TRANSACTION_removeInterfaceAlert = 47;
        static final int TRANSACTION_removeInterfaceFromLocalNetwork = 85;
        static final int TRANSACTION_removeInterfaceFromNetwork = 75;
        static final int TRANSACTION_removeInterfaceQuota = 45;
        static final int TRANSACTION_removeNetwork = 73;
        static final int TRANSACTION_removeRoute = 16;
        static final int TRANSACTION_removeRoutesFromLocalNetwork = 86;
        static final int TRANSACTION_removeVpnUidRanges = 64;
        static final int TRANSACTION_setAllowOnlyVpnForUids = 87;
        static final int TRANSACTION_setDataSaverModeEnabled = 51;
        static final int TRANSACTION_setDefaultNetId = 77;
        static final int TRANSACTION_setDnsConfigurationForNetwork = 56;
        static final int TRANSACTION_setDnsForwarders = 27;
        static final int TRANSACTION_setFirewallChainEnabled = 62;
        static final int TRANSACTION_setFirewallEnabled = 57;
        static final int TRANSACTION_setFirewallInterfaceRule = 59;
        static final int TRANSACTION_setFirewallUidRule = 60;
        static final int TRANSACTION_setFirewallUidRules = 61;
        static final int TRANSACTION_setGlobalAlert = 48;
        static final int TRANSACTION_setIPv6AddrGenMode = 13;
        static final int TRANSACTION_setInterfaceAlert = 46;
        static final int TRANSACTION_setInterfaceConfig = 6;
        static final int TRANSACTION_setInterfaceDown = 8;
        static final int TRANSACTION_setInterfaceIpv6NdOffload = 14;
        static final int TRANSACTION_setInterfaceIpv6PrivacyExtensions = 10;
        static final int TRANSACTION_setInterfaceQuota = 44;
        static final int TRANSACTION_setInterfaceUp = 9;
        static final int TRANSACTION_setIpForwardingEnabled = 20;
        static final int TRANSACTION_setMtu = 17;
        static final int TRANSACTION_setNetworkPermission = 79;
        static final int TRANSACTION_setPermission = 80;
        static final int TRANSACTION_setUidCleartextNetworkPolicy = 52;
        static final int TRANSACTION_setUidMeteredNetworkBlacklist = 49;
        static final int TRANSACTION_setUidMeteredNetworkWhitelist = 50;
        static final int TRANSACTION_shutdown = 18;
        static final int TRANSACTION_startClatd = 65;
        static final int TRANSACTION_startInterfaceForwarding = 29;
        static final int TRANSACTION_startTethering = 21;
        static final int TRANSACTION_stopClatd = 66;
        static final int TRANSACTION_stopInterfaceForwarding = 30;
        static final int TRANSACTION_stopTethering = 22;
        static final int TRANSACTION_tetherInterface = 24;
        static final int TRANSACTION_tetherLimitReached = 35;
        static final int TRANSACTION_unregisterNetworkActivityListener = 69;
        static final int TRANSACTION_unregisterObserver = 2;
        static final int TRANSACTION_unregisterTetheringStatsProvider = 34;
        static final int TRANSACTION_untetherInterface = 25;

        public Stub()
        {
            attachInterface(this, "android.os.INetworkManagementService");
        }
    }

    private static class Stub.Proxy
        implements INetworkManagementService
    {

        public void addIdleTimer(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(54, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void addInterfaceToLocalNetwork(String s, List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeTypedList(list);
            mRemote.transact(84, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void addInterfaceToNetwork(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(74, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void addLegacyRouteForNetId(int i, RouteInfo routeinfo, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            if(routeinfo == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            routeinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(j);
            mRemote.transact(76, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            routeinfo;
            parcel1.recycle();
            parcel.recycle();
            throw routeinfo;
        }

        public void addRoute(int i, RouteInfo routeinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            if(routeinfo == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            routeinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            routeinfo;
            parcel1.recycle();
            parcel.recycle();
            throw routeinfo;
        }

        public void addVpnUidRanges(int i, UidRange auidrange[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            parcel.writeTypedArray(auidrange, 0);
            mRemote.transact(63, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            auidrange;
            parcel1.recycle();
            parcel.recycle();
            throw auidrange;
        }

        public void allowProtect(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            mRemote.transact(82, parcel, parcel1, 0);
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

        public void attachPppd(String s, String s1, String s2, String s3, String s4)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeString(s4);
            mRemote.transact(37, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearDefaultNetId()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            mRemote.transact(78, parcel, parcel1, 0);
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

        public void clearInterfaceAddresses(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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

        public void clearPermission(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeIntArray(ai);
            mRemote.transact(81, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public void createPhysicalNetwork(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(71, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void createVirtualNetwork(int i, boolean flag, boolean flag1)
            throws RemoteException
        {
            boolean flag2;
            Parcel parcel;
            Parcel parcel1;
            flag2 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
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

        public void denyProtect(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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

        public void detachPppd(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
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

        public void disableIpv6(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void disableNat(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeString(s1);
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

        public void enableIpv6(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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

        public void enableNat(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeString(s1);
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

        public String[] getDnsForwarders()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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

        public InterfaceConfiguration getInterfaceConfig(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (InterfaceConfiguration)InterfaceConfiguration.CREATOR.createFromParcel(parcel1);
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

        public String getInterfaceDescriptor()
        {
            return "android.os.INetworkManagementService";
        }

        public boolean getIpForwardingEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public IBinder getMiuiNetworkManager()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IBinder ibinder;
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            mRemote.transact(89, parcel, parcel1, 0);
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

        public INetd getNetdService()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            INetd inetd;
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            inetd = android.net.INetd.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return inetd;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public NetworkStats getNetworkStatsDetail()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            mRemote.transact(41, parcel, parcel1, 0);
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

        public NetworkStats getNetworkStatsSummaryDev()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            mRemote.transact(39, parcel, parcel1, 0);
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

        public NetworkStats getNetworkStatsSummaryXt()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            mRemote.transact(40, parcel, parcel1, 0);
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

        public NetworkStats getNetworkStatsTethering(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            mRemote.transact(43, parcel, parcel1, 0);
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

        public NetworkStats getNetworkStatsUidDetail(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            mRemote.transact(42, parcel, parcel1, 0);
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

        public boolean isBandwidthControlEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            mRemote.transact(53, parcel, parcel1, 0);
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

        public boolean isClatdStarted(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean isFirewallEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isNetworkActive()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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

        public boolean isNetworkRestricted(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
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
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isTetheringStarted()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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

        public String[] listInterfaces()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            mRemote.transact(4, parcel, parcel1, 0);
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

        public String[] listTetheredInterfaces()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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

        public String[] listTtys()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            String as[];
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            mRemote.transact(36, parcel, parcel1, 0);
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

        public void registerNetworkActivityListener(INetworkActivityListener inetworkactivitylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(inetworkactivitylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = inetworkactivitylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(68, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inetworkactivitylistener;
            parcel1.recycle();
            parcel.recycle();
            throw inetworkactivitylistener;
        }

        public void registerObserver(INetworkManagementEventObserver inetworkmanagementeventobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(inetworkmanagementeventobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = inetworkmanagementeventobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inetworkmanagementeventobserver;
            parcel1.recycle();
            parcel.recycle();
            throw inetworkmanagementeventobserver;
        }

        public void registerTetheringStatsProvider(ITetheringStatsProvider itetheringstatsprovider, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(itetheringstatsprovider == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = itetheringstatsprovider.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            itetheringstatsprovider;
            parcel1.recycle();
            parcel.recycle();
            throw itetheringstatsprovider;
        }

        public void removeIdleTimer(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            mRemote.transact(55, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeInterfaceAlert(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            mRemote.transact(47, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeInterfaceFromLocalNetwork(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            mRemote.transact(85, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeInterfaceFromNetwork(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(75, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeInterfaceQuota(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void removeNetwork(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            mRemote.transact(73, parcel, parcel1, 0);
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

        public void removeRoute(int i, RouteInfo routeinfo)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            if(routeinfo == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            routeinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            routeinfo;
            parcel1.recycle();
            parcel.recycle();
            throw routeinfo;
        }

        public int removeRoutesFromLocalNetwork(List list)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeTypedList(list);
            mRemote.transact(86, parcel, parcel1, 0);
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

        public void removeVpnUidRanges(int i, UidRange auidrange[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            parcel.writeTypedArray(auidrange, 0);
            mRemote.transact(64, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            auidrange;
            parcel1.recycle();
            parcel.recycle();
            throw auidrange;
        }

        public void setAllowOnlyVpnForUids(boolean flag, UidRange auidrange[])
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeTypedArray(auidrange, 0);
            mRemote.transact(87, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            auidrange;
            parcel1.recycle();
            parcel.recycle();
            throw auidrange;
        }

        public boolean setDataSaverModeEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(51, parcel, parcel1, 0);
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

        public void setDefaultNetId(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            mRemote.transact(77, parcel, parcel1, 0);
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

        public void setDnsConfigurationForNetwork(int i, String as[], String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            parcel.writeStringArray(as);
            parcel.writeString(s);
            mRemote.transact(56, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void setDnsForwarders(Network network, String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(network == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            network.writeToParcel(parcel, 0);
_L1:
            parcel.writeStringArray(as);
            mRemote.transact(27, parcel, parcel1, 0);
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

        public void setFirewallChainEnabled(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
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

        public void setFirewallEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(57, parcel, parcel1, 0);
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

        public void setFirewallInterfaceRule(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(59, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setFirewallUidRule(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
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

        public void setFirewallUidRules(int i, int ai[], int ai1[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            parcel.writeIntArray(ai);
            parcel.writeIntArray(ai1);
            mRemote.transact(61, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public void setGlobalAlert(long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeLong(l);
            mRemote.transact(48, parcel, parcel1, 0);
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

        public void setIPv6AddrGenMode(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public void setInterfaceAlert(String s, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeLong(l);
            mRemote.transact(46, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setInterfaceConfig(String s, InterfaceConfiguration interfaceconfiguration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            if(interfaceconfiguration == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            interfaceconfiguration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, parcel1, 0);
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

        public void setInterfaceDown(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setInterfaceIpv6NdOffload(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setInterfaceIpv6PrivacyExtensions(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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

        public void setInterfaceQuota(String s, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeLong(l);
            mRemote.transact(44, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setInterfaceUp(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setIpForwardingEnabled(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
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

        public void setMtu(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public void setNetworkPermission(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(79, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setPermission(String s, int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeIntArray(ai);
            mRemote.transact(80, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setUidCleartextNetworkPolicy(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(52, parcel, parcel1, 0);
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

        public void setUidMeteredNetworkBlacklist(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(49, parcel, parcel1, 0);
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

        public void setUidMeteredNetworkWhitelist(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(50, parcel, parcel1, 0);
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

        public void shutdown()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            mRemote.transact(18, parcel, parcel1, 0);
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

        public void startClatd(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
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

        public void startInterfaceForwarding(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeString(s1);
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

        public void startTethering(String as[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeStringArray(as);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            as;
            parcel1.recycle();
            parcel.recycle();
            throw as;
        }

        public void stopClatd(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            mRemote.transact(66, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void stopInterfaceForwarding(String s, String s1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
            parcel.writeString(s1);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void stopTethering()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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

        public void tetherInterface(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            parcel.writeString(s);
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

        public void tetherLimitReached(ITetheringStatsProvider itetheringstatsprovider)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(itetheringstatsprovider == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = itetheringstatsprovider.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(35, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            itetheringstatsprovider;
            parcel1.recycle();
            parcel.recycle();
            throw itetheringstatsprovider;
        }

        public void unregisterNetworkActivityListener(INetworkActivityListener inetworkactivitylistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(inetworkactivitylistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = inetworkactivitylistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(69, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inetworkactivitylistener;
            parcel1.recycle();
            parcel.recycle();
            throw inetworkactivitylistener;
        }

        public void unregisterObserver(INetworkManagementEventObserver inetworkmanagementeventobserver)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(inetworkmanagementeventobserver == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = inetworkmanagementeventobserver.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            inetworkmanagementeventobserver;
            parcel1.recycle();
            parcel.recycle();
            throw inetworkmanagementeventobserver;
        }

        public void unregisterTetheringStatsProvider(ITetheringStatsProvider itetheringstatsprovider)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
            if(itetheringstatsprovider == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = itetheringstatsprovider.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            itetheringstatsprovider;
            parcel1.recycle();
            parcel.recycle();
            throw itetheringstatsprovider;
        }

        public void untetherInterface(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.os.INetworkManagementService");
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

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addIdleTimer(String s, int i, int j)
        throws RemoteException;

    public abstract void addInterfaceToLocalNetwork(String s, List list)
        throws RemoteException;

    public abstract void addInterfaceToNetwork(String s, int i)
        throws RemoteException;

    public abstract void addLegacyRouteForNetId(int i, RouteInfo routeinfo, int j)
        throws RemoteException;

    public abstract void addRoute(int i, RouteInfo routeinfo)
        throws RemoteException;

    public abstract void addVpnUidRanges(int i, UidRange auidrange[])
        throws RemoteException;

    public abstract void allowProtect(int i)
        throws RemoteException;

    public abstract void attachPppd(String s, String s1, String s2, String s3, String s4)
        throws RemoteException;

    public abstract void clearDefaultNetId()
        throws RemoteException;

    public abstract void clearInterfaceAddresses(String s)
        throws RemoteException;

    public abstract void clearPermission(int ai[])
        throws RemoteException;

    public abstract void createPhysicalNetwork(int i, String s)
        throws RemoteException;

    public abstract void createVirtualNetwork(int i, boolean flag, boolean flag1)
        throws RemoteException;

    public abstract void denyProtect(int i)
        throws RemoteException;

    public abstract void detachPppd(String s)
        throws RemoteException;

    public abstract void disableIpv6(String s)
        throws RemoteException;

    public abstract void disableNat(String s, String s1)
        throws RemoteException;

    public abstract void enableIpv6(String s)
        throws RemoteException;

    public abstract void enableNat(String s, String s1)
        throws RemoteException;

    public abstract String[] getDnsForwarders()
        throws RemoteException;

    public abstract InterfaceConfiguration getInterfaceConfig(String s)
        throws RemoteException;

    public abstract boolean getIpForwardingEnabled()
        throws RemoteException;

    public abstract IBinder getMiuiNetworkManager()
        throws RemoteException;

    public abstract INetd getNetdService()
        throws RemoteException;

    public abstract NetworkStats getNetworkStatsDetail()
        throws RemoteException;

    public abstract NetworkStats getNetworkStatsSummaryDev()
        throws RemoteException;

    public abstract NetworkStats getNetworkStatsSummaryXt()
        throws RemoteException;

    public abstract NetworkStats getNetworkStatsTethering(int i)
        throws RemoteException;

    public abstract NetworkStats getNetworkStatsUidDetail(int i)
        throws RemoteException;

    public abstract boolean isBandwidthControlEnabled()
        throws RemoteException;

    public abstract boolean isClatdStarted(String s)
        throws RemoteException;

    public abstract boolean isFirewallEnabled()
        throws RemoteException;

    public abstract boolean isNetworkActive()
        throws RemoteException;

    public abstract boolean isNetworkRestricted(int i)
        throws RemoteException;

    public abstract boolean isTetheringStarted()
        throws RemoteException;

    public abstract String[] listInterfaces()
        throws RemoteException;

    public abstract String[] listTetheredInterfaces()
        throws RemoteException;

    public abstract String[] listTtys()
        throws RemoteException;

    public abstract void registerNetworkActivityListener(INetworkActivityListener inetworkactivitylistener)
        throws RemoteException;

    public abstract void registerObserver(INetworkManagementEventObserver inetworkmanagementeventobserver)
        throws RemoteException;

    public abstract void registerTetheringStatsProvider(ITetheringStatsProvider itetheringstatsprovider, String s)
        throws RemoteException;

    public abstract void removeIdleTimer(String s)
        throws RemoteException;

    public abstract void removeInterfaceAlert(String s)
        throws RemoteException;

    public abstract void removeInterfaceFromLocalNetwork(String s)
        throws RemoteException;

    public abstract void removeInterfaceFromNetwork(String s, int i)
        throws RemoteException;

    public abstract void removeInterfaceQuota(String s)
        throws RemoteException;

    public abstract void removeNetwork(int i)
        throws RemoteException;

    public abstract void removeRoute(int i, RouteInfo routeinfo)
        throws RemoteException;

    public abstract int removeRoutesFromLocalNetwork(List list)
        throws RemoteException;

    public abstract void removeVpnUidRanges(int i, UidRange auidrange[])
        throws RemoteException;

    public abstract void setAllowOnlyVpnForUids(boolean flag, UidRange auidrange[])
        throws RemoteException;

    public abstract boolean setDataSaverModeEnabled(boolean flag)
        throws RemoteException;

    public abstract void setDefaultNetId(int i)
        throws RemoteException;

    public abstract void setDnsConfigurationForNetwork(int i, String as[], String s)
        throws RemoteException;

    public abstract void setDnsForwarders(Network network, String as[])
        throws RemoteException;

    public abstract void setFirewallChainEnabled(int i, boolean flag)
        throws RemoteException;

    public abstract void setFirewallEnabled(boolean flag)
        throws RemoteException;

    public abstract void setFirewallInterfaceRule(String s, boolean flag)
        throws RemoteException;

    public abstract void setFirewallUidRule(int i, int j, int k)
        throws RemoteException;

    public abstract void setFirewallUidRules(int i, int ai[], int ai1[])
        throws RemoteException;

    public abstract void setGlobalAlert(long l)
        throws RemoteException;

    public abstract void setIPv6AddrGenMode(String s, int i)
        throws RemoteException;

    public abstract void setInterfaceAlert(String s, long l)
        throws RemoteException;

    public abstract void setInterfaceConfig(String s, InterfaceConfiguration interfaceconfiguration)
        throws RemoteException;

    public abstract void setInterfaceDown(String s)
        throws RemoteException;

    public abstract void setInterfaceIpv6NdOffload(String s, boolean flag)
        throws RemoteException;

    public abstract void setInterfaceIpv6PrivacyExtensions(String s, boolean flag)
        throws RemoteException;

    public abstract void setInterfaceQuota(String s, long l)
        throws RemoteException;

    public abstract void setInterfaceUp(String s)
        throws RemoteException;

    public abstract void setIpForwardingEnabled(boolean flag)
        throws RemoteException;

    public abstract void setMtu(String s, int i)
        throws RemoteException;

    public abstract void setNetworkPermission(int i, String s)
        throws RemoteException;

    public abstract void setPermission(String s, int ai[])
        throws RemoteException;

    public abstract void setUidCleartextNetworkPolicy(int i, int j)
        throws RemoteException;

    public abstract void setUidMeteredNetworkBlacklist(int i, boolean flag)
        throws RemoteException;

    public abstract void setUidMeteredNetworkWhitelist(int i, boolean flag)
        throws RemoteException;

    public abstract void shutdown()
        throws RemoteException;

    public abstract void startClatd(String s)
        throws RemoteException;

    public abstract void startInterfaceForwarding(String s, String s1)
        throws RemoteException;

    public abstract void startTethering(String as[])
        throws RemoteException;

    public abstract void stopClatd(String s)
        throws RemoteException;

    public abstract void stopInterfaceForwarding(String s, String s1)
        throws RemoteException;

    public abstract void stopTethering()
        throws RemoteException;

    public abstract void tetherInterface(String s)
        throws RemoteException;

    public abstract void tetherLimitReached(ITetheringStatsProvider itetheringstatsprovider)
        throws RemoteException;

    public abstract void unregisterNetworkActivityListener(INetworkActivityListener inetworkactivitylistener)
        throws RemoteException;

    public abstract void unregisterObserver(INetworkManagementEventObserver inetworkmanagementeventobserver)
        throws RemoteException;

    public abstract void unregisterTetheringStatsProvider(ITetheringStatsProvider itetheringstatsprovider)
        throws RemoteException;

    public abstract void untetherInterface(String s)
        throws RemoteException;
}
