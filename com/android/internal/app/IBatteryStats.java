// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.app;

import android.bluetooth.BluetoothActivityEnergyInfo;
import android.net.wifi.WifiActivityEnergyInfo;
import android.os.*;
import android.os.health.HealthStatsParceler;
import android.telephony.ModemActivityInfo;
import android.telephony.SignalStrength;

public interface IBatteryStats
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IBatteryStats
    {

        public static IBatteryStats asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("com.android.internal.app.IBatteryStats");
            if(iinterface != null && (iinterface instanceof IBatteryStats))
                return (IBatteryStats)iinterface;
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
                parcel1.writeString("com.android.internal.app.IBatteryStats");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStartSensor(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 2: // '\002'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStopSensor(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 3: // '\003'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStartVideo(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStopVideo(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStartAudio(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStopAudio(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteResetVideo();
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteResetAudio();
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteFlashlightOn(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteFlashlightOff(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStartCamera(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStopCamera(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteResetCamera();
                parcel1.writeNoException();
                return true;

            case 14: // '\016'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteResetFlashlight();
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                parcel = getStatistics();
                parcel1.writeNoException();
                parcel1.writeByteArray(parcel);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                parcel = getStatisticsStream();
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

            case 17: // '\021'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                boolean flag = isCharging();
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 18: // '\022'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                long l = computeBatteryTimeRemaining();
                parcel1.writeNoException();
                parcel1.writeLong(l);
                return true;

            case 19: // '\023'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                long l1 = computeChargeTimeRemaining();
                parcel1.writeNoException();
                parcel1.writeLong(l1);
                return true;

            case 20: // '\024'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteEvent(parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteSyncStart(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteSyncFinish(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteJobStart(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteJobFinish(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                i = parcel.readInt();
                int k = parcel.readInt();
                String s = parcel.readString();
                String s1 = parcel.readString();
                j = parcel.readInt();
                boolean flag1;
                if(parcel.readInt() != 0)
                    flag1 = true;
                else
                    flag1 = false;
                noteStartWakelock(i, k, s, s1, j, flag1);
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStopWakelock(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                boolean flag2;
                WorkSource worksource;
                String s2;
                String s3;
                if(parcel.readInt() != 0)
                    worksource = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource = null;
                i = parcel.readInt();
                s3 = parcel.readString();
                s2 = parcel.readString();
                j = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag2 = true;
                else
                    flag2 = false;
                noteStartWakelockFromSource(worksource, i, s3, s2, j, flag2);
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                boolean flag3;
                int i1;
                WorkSource worksource1;
                WorkSource worksource8;
                String s4;
                String s5;
                int j1;
                String s6;
                String s7;
                if(parcel.readInt() != 0)
                    worksource1 = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource1 = null;
                i1 = parcel.readInt();
                s5 = parcel.readString();
                s4 = parcel.readString();
                j1 = parcel.readInt();
                if(parcel.readInt() != 0)
                    worksource8 = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource8 = null;
                j = parcel.readInt();
                s6 = parcel.readString();
                s7 = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                noteChangeWakelockFromSource(worksource1, i1, s5, s4, j1, worksource8, j, s6, s7, i, flag3);
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                WorkSource worksource2;
                if(parcel.readInt() != 0)
                    worksource2 = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource2 = null;
                noteStopWakelockFromSource(worksource2, parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteLongPartialWakelockStart(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteLongPartialWakelockFinish(parcel.readString(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteVibratorOn(parcel.readInt(), parcel.readLong());
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteVibratorOff(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStartGps(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 35: // '#'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteStopGps(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 36: // '$'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteScreenState(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 37: // '%'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteScreenBrightness(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 38: // '&'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteUserActivity(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 39: // '\''
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteWakeUp(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 40: // '('
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                noteInteractive(flag4);
                parcel1.writeNoException();
                return true;

            case 41: // ')'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteConnectivityChanged(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 42: // '*'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteMobileRadioPowerState(parcel.readInt(), parcel.readLong(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 43: // '+'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                notePhoneOn();
                parcel1.writeNoException();
                return true;

            case 44: // ','
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                notePhoneOff();
                parcel1.writeNoException();
                return true;

            case 45: // '-'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (SignalStrength)SignalStrength.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notePhoneSignalStrength(parcel);
                parcel1.writeNoException();
                return true;

            case 46: // '.'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                i = parcel.readInt();
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                notePhoneDataConnectionState(i, flag5);
                parcel1.writeNoException();
                return true;

            case 47: // '/'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                notePhoneState(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 48: // '0'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteWifiOn();
                parcel1.writeNoException();
                return true;

            case 49: // '1'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteWifiOff();
                parcel1.writeNoException();
                return true;

            case 50: // '2'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteWifiRunning(parcel);
                parcel1.writeNoException();
                return true;

            case 51: // '3'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                WorkSource worksource3;
                if(parcel.readInt() != 0)
                    worksource3 = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource3 = null;
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteWifiRunningChanged(worksource3, parcel);
                parcel1.writeNoException();
                return true;

            case 52: // '4'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteWifiStopped(parcel);
                parcel1.writeNoException();
                return true;

            case 53: // '5'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteWifiState(parcel.readInt(), parcel.readString());
                parcel1.writeNoException();
                return true;

            case 54: // '6'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                i = parcel.readInt();
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                noteWifiSupplicantStateChanged(i, flag6);
                parcel1.writeNoException();
                return true;

            case 55: // '7'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteWifiRssiChanged(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 56: // '8'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteFullWifiLockAcquired(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 57: // '9'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteFullWifiLockReleased(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 58: // ':'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteWifiScanStarted(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 59: // ';'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteWifiScanStopped(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 60: // '<'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteWifiMulticastEnabled(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 61: // '='
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteWifiMulticastDisabled(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 62: // '>'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteFullWifiLockAcquiredFromSource(parcel);
                parcel1.writeNoException();
                return true;

            case 63: // '?'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteFullWifiLockReleasedFromSource(parcel);
                parcel1.writeNoException();
                return true;

            case 64: // '@'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteWifiScanStartedFromSource(parcel);
                parcel1.writeNoException();
                return true;

            case 65: // 'A'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteWifiScanStoppedFromSource(parcel);
                parcel1.writeNoException();
                return true;

            case 66: // 'B'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                WorkSource worksource4;
                if(parcel.readInt() != 0)
                    worksource4 = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource4 = null;
                noteWifiBatchedScanStartedFromSource(worksource4, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 67: // 'C'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteWifiBatchedScanStoppedFromSource(parcel);
                parcel1.writeNoException();
                return true;

            case 68: // 'D'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteWifiMulticastEnabledFromSource(parcel);
                parcel1.writeNoException();
                return true;

            case 69: // 'E'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteWifiMulticastDisabledFromSource(parcel);
                parcel1.writeNoException();
                return true;

            case 70: // 'F'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteWifiRadioPowerState(parcel.readInt(), parcel.readLong(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 71: // 'G'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteNetworkInterfaceType(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 72: // 'H'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteNetworkStatsEnabled();
                parcel1.writeNoException();
                return true;

            case 73: // 'I'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteDeviceIdleMode(parcel.readInt(), parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 74: // 'J'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                setBatteryState(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 75: // 'K'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                long l2 = getAwakeTimeBattery();
                parcel1.writeNoException();
                parcel1.writeLong(l2);
                return true;

            case 76: // 'L'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                long l3 = getAwakeTimePlugged();
                parcel1.writeNoException();
                parcel1.writeLong(l3);
                return true;

            case 77: // 'M'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                boolean flag7;
                WorkSource worksource5;
                if(parcel.readInt() != 0)
                    worksource5 = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource5 = null;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                noteBleScanStarted(worksource5, flag7);
                parcel1.writeNoException();
                return true;

            case 78: // 'N'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                boolean flag8;
                WorkSource worksource6;
                if(parcel.readInt() != 0)
                    worksource6 = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource6 = null;
                if(parcel.readInt() != 0)
                    flag8 = true;
                else
                    flag8 = false;
                noteBleScanStopped(worksource6, flag8);
                parcel1.writeNoException();
                return true;

            case 79: // 'O'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                noteResetBleScan();
                parcel1.writeNoException();
                return true;

            case 80: // 'P'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                WorkSource worksource7;
                if(parcel.readInt() != 0)
                    worksource7 = (WorkSource)WorkSource.CREATOR.createFromParcel(parcel);
                else
                    worksource7 = null;
                noteBleScanResults(worksource7, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 81: // 'Q'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                parcel = takeUidSnapshot(parcel.readInt());
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

            case 82: // 'R'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                parcel = takeUidSnapshots(parcel.createIntArray());
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 83: // 'S'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (BluetoothActivityEnergyInfo)BluetoothActivityEnergyInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteBluetoothControllerActivity(parcel);
                return true;

            case 84: // 'T'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                if(parcel.readInt() != 0)
                    parcel = (ModemActivityInfo)ModemActivityInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                noteModemControllerActivity(parcel);
                return true;

            case 85: // 'U'
                parcel.enforceInterface("com.android.internal.app.IBatteryStats");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (WifiActivityEnergyInfo)WifiActivityEnergyInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            noteWifiControllerActivity(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "com.android.internal.app.IBatteryStats";
        static final int TRANSACTION_computeBatteryTimeRemaining = 18;
        static final int TRANSACTION_computeChargeTimeRemaining = 19;
        static final int TRANSACTION_getAwakeTimeBattery = 75;
        static final int TRANSACTION_getAwakeTimePlugged = 76;
        static final int TRANSACTION_getStatistics = 15;
        static final int TRANSACTION_getStatisticsStream = 16;
        static final int TRANSACTION_isCharging = 17;
        static final int TRANSACTION_noteBleScanResults = 80;
        static final int TRANSACTION_noteBleScanStarted = 77;
        static final int TRANSACTION_noteBleScanStopped = 78;
        static final int TRANSACTION_noteBluetoothControllerActivity = 83;
        static final int TRANSACTION_noteChangeWakelockFromSource = 28;
        static final int TRANSACTION_noteConnectivityChanged = 41;
        static final int TRANSACTION_noteDeviceIdleMode = 73;
        static final int TRANSACTION_noteEvent = 20;
        static final int TRANSACTION_noteFlashlightOff = 10;
        static final int TRANSACTION_noteFlashlightOn = 9;
        static final int TRANSACTION_noteFullWifiLockAcquired = 56;
        static final int TRANSACTION_noteFullWifiLockAcquiredFromSource = 62;
        static final int TRANSACTION_noteFullWifiLockReleased = 57;
        static final int TRANSACTION_noteFullWifiLockReleasedFromSource = 63;
        static final int TRANSACTION_noteInteractive = 40;
        static final int TRANSACTION_noteJobFinish = 24;
        static final int TRANSACTION_noteJobStart = 23;
        static final int TRANSACTION_noteLongPartialWakelockFinish = 31;
        static final int TRANSACTION_noteLongPartialWakelockStart = 30;
        static final int TRANSACTION_noteMobileRadioPowerState = 42;
        static final int TRANSACTION_noteModemControllerActivity = 84;
        static final int TRANSACTION_noteNetworkInterfaceType = 71;
        static final int TRANSACTION_noteNetworkStatsEnabled = 72;
        static final int TRANSACTION_notePhoneDataConnectionState = 46;
        static final int TRANSACTION_notePhoneOff = 44;
        static final int TRANSACTION_notePhoneOn = 43;
        static final int TRANSACTION_notePhoneSignalStrength = 45;
        static final int TRANSACTION_notePhoneState = 47;
        static final int TRANSACTION_noteResetAudio = 8;
        static final int TRANSACTION_noteResetBleScan = 79;
        static final int TRANSACTION_noteResetCamera = 13;
        static final int TRANSACTION_noteResetFlashlight = 14;
        static final int TRANSACTION_noteResetVideo = 7;
        static final int TRANSACTION_noteScreenBrightness = 37;
        static final int TRANSACTION_noteScreenState = 36;
        static final int TRANSACTION_noteStartAudio = 5;
        static final int TRANSACTION_noteStartCamera = 11;
        static final int TRANSACTION_noteStartGps = 34;
        static final int TRANSACTION_noteStartSensor = 1;
        static final int TRANSACTION_noteStartVideo = 3;
        static final int TRANSACTION_noteStartWakelock = 25;
        static final int TRANSACTION_noteStartWakelockFromSource = 27;
        static final int TRANSACTION_noteStopAudio = 6;
        static final int TRANSACTION_noteStopCamera = 12;
        static final int TRANSACTION_noteStopGps = 35;
        static final int TRANSACTION_noteStopSensor = 2;
        static final int TRANSACTION_noteStopVideo = 4;
        static final int TRANSACTION_noteStopWakelock = 26;
        static final int TRANSACTION_noteStopWakelockFromSource = 29;
        static final int TRANSACTION_noteSyncFinish = 22;
        static final int TRANSACTION_noteSyncStart = 21;
        static final int TRANSACTION_noteUserActivity = 38;
        static final int TRANSACTION_noteVibratorOff = 33;
        static final int TRANSACTION_noteVibratorOn = 32;
        static final int TRANSACTION_noteWakeUp = 39;
        static final int TRANSACTION_noteWifiBatchedScanStartedFromSource = 66;
        static final int TRANSACTION_noteWifiBatchedScanStoppedFromSource = 67;
        static final int TRANSACTION_noteWifiControllerActivity = 85;
        static final int TRANSACTION_noteWifiMulticastDisabled = 61;
        static final int TRANSACTION_noteWifiMulticastDisabledFromSource = 69;
        static final int TRANSACTION_noteWifiMulticastEnabled = 60;
        static final int TRANSACTION_noteWifiMulticastEnabledFromSource = 68;
        static final int TRANSACTION_noteWifiOff = 49;
        static final int TRANSACTION_noteWifiOn = 48;
        static final int TRANSACTION_noteWifiRadioPowerState = 70;
        static final int TRANSACTION_noteWifiRssiChanged = 55;
        static final int TRANSACTION_noteWifiRunning = 50;
        static final int TRANSACTION_noteWifiRunningChanged = 51;
        static final int TRANSACTION_noteWifiScanStarted = 58;
        static final int TRANSACTION_noteWifiScanStartedFromSource = 64;
        static final int TRANSACTION_noteWifiScanStopped = 59;
        static final int TRANSACTION_noteWifiScanStoppedFromSource = 65;
        static final int TRANSACTION_noteWifiState = 53;
        static final int TRANSACTION_noteWifiStopped = 52;
        static final int TRANSACTION_noteWifiSupplicantStateChanged = 54;
        static final int TRANSACTION_setBatteryState = 74;
        static final int TRANSACTION_takeUidSnapshot = 81;
        static final int TRANSACTION_takeUidSnapshots = 82;

        public Stub()
        {
            attachInterface(this, "com.android.internal.app.IBatteryStats");
        }
    }

    private static class Stub.Proxy
        implements IBatteryStats
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public long computeBatteryTimeRemaining()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            mRemote.transact(18, parcel, parcel1, 0);
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

        public long computeChargeTimeRemaining()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            mRemote.transact(19, parcel, parcel1, 0);
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

        public long getAwakeTimeBattery()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            mRemote.transact(75, parcel, parcel1, 0);
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

        public long getAwakeTimePlugged()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            long l;
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            mRemote.transact(76, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "com.android.internal.app.IBatteryStats";
        }

        public byte[] getStatistics()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            byte abyte0[];
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            mRemote.transact(15, parcel, parcel1, 0);
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

        public ParcelFileDescriptor getStatisticsStream()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            ParcelFileDescriptor parcelfiledescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return parcelfiledescriptor;
_L2:
            parcelfiledescriptor = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isCharging()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteBleScanResults(WorkSource worksource, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(80, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteBleScanStarted(WorkSource worksource, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(77, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteBleScanStopped(WorkSource worksource, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(78, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteBluetoothControllerActivity(BluetoothActivityEnergyInfo bluetoothactivityenergyinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(bluetoothactivityenergyinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            bluetoothactivityenergyinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(83, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            bluetoothactivityenergyinfo;
            parcel.recycle();
            throw bluetoothactivityenergyinfo;
        }

        public void noteChangeWakelockFromSource(WorkSource worksource, int i, String s, String s1, int j, WorkSource worksource1, int k, 
                String s2, String s3, int l, boolean flag)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L3:
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(j);
            if(worksource1 == null)
                break MISSING_BLOCK_LABEL_175;
            parcel.writeInt(1);
            worksource1.writeToParcel(parcel, 0);
_L4:
            parcel.writeInt(k);
            parcel.writeString(s2);
            parcel.writeString(s3);
            parcel.writeInt(l);
            if(flag)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
            parcel.writeInt(0);
              goto _L4
        }

        public void noteConnectivityChanged(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(41, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void noteDeviceIdleMode(int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(73, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void noteEvent(int i, String s, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeInt(j);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void noteFlashlightOff(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
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

        public void noteFlashlightOn(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
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

        public void noteFullWifiLockAcquired(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteFullWifiLockAcquiredFromSource(WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(62, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteFullWifiLockReleased(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteFullWifiLockReleasedFromSource(WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(63, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteInteractive(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(40, parcel, parcel1, 0);
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

        public void noteJobFinish(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public void noteJobStart(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void noteLongPartialWakelockFinish(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
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

        public void noteLongPartialWakelockStart(String s, String s1, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(i);
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

        public void noteMobileRadioPowerState(int i, long l, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeInt(j);
            mRemote.transact(42, parcel, parcel1, 0);
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

        public void noteModemControllerActivity(ModemActivityInfo modemactivityinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(modemactivityinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            modemactivityinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(84, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            modemactivityinfo;
            parcel.recycle();
            throw modemactivityinfo;
        }

        public void noteNetworkInterfaceType(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeString(s);
            parcel.writeInt(i);
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

        public void noteNetworkStatsEnabled()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void notePhoneDataConnectionState(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
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

        public void notePhoneOff()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void notePhoneOn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            mRemote.transact(43, parcel, parcel1, 0);
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

        public void notePhoneSignalStrength(SignalStrength signalstrength)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(signalstrength == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            signalstrength.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(45, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            signalstrength;
            parcel1.recycle();
            parcel.recycle();
            throw signalstrength;
        }

        public void notePhoneState(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
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

        public void noteResetAudio()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            mRemote.transact(8, parcel, parcel1, 0);
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

        public void noteResetBleScan()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            mRemote.transact(79, parcel, parcel1, 0);
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

        public void noteResetCamera()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            mRemote.transact(13, parcel, parcel1, 0);
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

        public void noteResetFlashlight()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteResetVideo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteScreenBrightness(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteScreenState(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
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

        public void noteStartAudio(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
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

        public void noteStartCamera(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
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

        public void noteStartGps(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteStartSensor(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(1, parcel, parcel1, 0);
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

        public void noteStartVideo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public void noteStartWakelock(int i, int j, String s, String s1, int k, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(k);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
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

        public void noteStartWakelockFromSource(WorkSource worksource, int i, String s, String s1, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = true;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_110;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(j);
            if(flag)
                i = ((flag1) ? 1 : 0);
            else
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteStopAudio(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteStopCamera(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
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

        public void noteStopGps(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteStopSensor(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(2, parcel, parcel1, 0);
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

        public void noteStopVideo(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
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

        public void noteStopWakelock(int i, int j, String s, String s1, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(k);
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

        public void noteStopWakelockFromSource(WorkSource worksource, int i, String s, String s1, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_93;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            parcel.writeString(s);
            parcel.writeString(s1);
            parcel.writeInt(j);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteSyncFinish(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void noteSyncStart(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void noteUserActivity(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeInt(j);
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

        public void noteVibratorOff(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteVibratorOn(int i, long l)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeLong(l);
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

        public void noteWakeUp(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeString(s);
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

        public void noteWifiBatchedScanStartedFromSource(WorkSource worksource, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(66, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteWifiBatchedScanStoppedFromSource(WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(67, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteWifiControllerActivity(WifiActivityEnergyInfo wifiactivityenergyinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(wifiactivityenergyinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            wifiactivityenergyinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(85, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            wifiactivityenergyinfo;
            parcel.recycle();
            throw wifiactivityenergyinfo;
        }

        public void noteWifiMulticastDisabled(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteWifiMulticastDisabledFromSource(WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(69, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteWifiMulticastEnabled(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteWifiMulticastEnabledFromSource(WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(68, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteWifiOff()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteWifiOn()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteWifiRadioPowerState(int i, long l, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeLong(l);
            parcel.writeInt(j);
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

        public void noteWifiRssiChanged(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            mRemote.transact(55, parcel, parcel1, 0);
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

        public void noteWifiRunning(WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(50, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteWifiRunningChanged(WorkSource worksource, WorkSource worksource1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L3:
            if(worksource1 == null)
                break MISSING_BLOCK_LABEL_96;
            parcel.writeInt(1);
            worksource1.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(51, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
            parcel.writeInt(0);
              goto _L4
        }

        public void noteWifiScanStarted(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteWifiScanStartedFromSource(WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(64, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteWifiScanStopped(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
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

        public void noteWifiScanStoppedFromSource(WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(65, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteWifiState(int i, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeString(s);
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

        public void noteWifiStopped(WorkSource worksource)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            if(worksource == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            worksource.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(52, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            worksource;
            parcel1.recycle();
            parcel.recycle();
            throw worksource;
        }

        public void noteWifiSupplicantStateChanged(int i, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
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

        public void setBatteryState(int i, int j, int k, int l, int i1, int j1, int k1, 
                int l1)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeInt(l);
            parcel.writeInt(i1);
            parcel.writeInt(j1);
            parcel.writeInt(k1);
            parcel.writeInt(l1);
            mRemote.transact(74, parcel, parcel1, 0);
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

        public HealthStatsParceler takeUidSnapshot(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeInt(i);
            mRemote.transact(81, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            HealthStatsParceler healthstatsparceler = (HealthStatsParceler)HealthStatsParceler.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return healthstatsparceler;
_L2:
            healthstatsparceler = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public HealthStatsParceler[] takeUidSnapshots(int ai[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("com.android.internal.app.IBatteryStats");
            parcel.writeIntArray(ai);
            mRemote.transact(82, parcel, parcel1, 0);
            parcel1.readException();
            ai = (HealthStatsParceler[])parcel1.createTypedArray(HealthStatsParceler.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return ai;
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract long computeBatteryTimeRemaining()
        throws RemoteException;

    public abstract long computeChargeTimeRemaining()
        throws RemoteException;

    public abstract long getAwakeTimeBattery()
        throws RemoteException;

    public abstract long getAwakeTimePlugged()
        throws RemoteException;

    public abstract byte[] getStatistics()
        throws RemoteException;

    public abstract ParcelFileDescriptor getStatisticsStream()
        throws RemoteException;

    public abstract boolean isCharging()
        throws RemoteException;

    public abstract void noteBleScanResults(WorkSource worksource, int i)
        throws RemoteException;

    public abstract void noteBleScanStarted(WorkSource worksource, boolean flag)
        throws RemoteException;

    public abstract void noteBleScanStopped(WorkSource worksource, boolean flag)
        throws RemoteException;

    public abstract void noteBluetoothControllerActivity(BluetoothActivityEnergyInfo bluetoothactivityenergyinfo)
        throws RemoteException;

    public abstract void noteChangeWakelockFromSource(WorkSource worksource, int i, String s, String s1, int j, WorkSource worksource1, int k, 
            String s2, String s3, int l, boolean flag)
        throws RemoteException;

    public abstract void noteConnectivityChanged(int i, String s)
        throws RemoteException;

    public abstract void noteDeviceIdleMode(int i, String s, int j)
        throws RemoteException;

    public abstract void noteEvent(int i, String s, int j)
        throws RemoteException;

    public abstract void noteFlashlightOff(int i)
        throws RemoteException;

    public abstract void noteFlashlightOn(int i)
        throws RemoteException;

    public abstract void noteFullWifiLockAcquired(int i)
        throws RemoteException;

    public abstract void noteFullWifiLockAcquiredFromSource(WorkSource worksource)
        throws RemoteException;

    public abstract void noteFullWifiLockReleased(int i)
        throws RemoteException;

    public abstract void noteFullWifiLockReleasedFromSource(WorkSource worksource)
        throws RemoteException;

    public abstract void noteInteractive(boolean flag)
        throws RemoteException;

    public abstract void noteJobFinish(String s, int i, int j)
        throws RemoteException;

    public abstract void noteJobStart(String s, int i)
        throws RemoteException;

    public abstract void noteLongPartialWakelockFinish(String s, String s1, int i)
        throws RemoteException;

    public abstract void noteLongPartialWakelockStart(String s, String s1, int i)
        throws RemoteException;

    public abstract void noteMobileRadioPowerState(int i, long l, int j)
        throws RemoteException;

    public abstract void noteModemControllerActivity(ModemActivityInfo modemactivityinfo)
        throws RemoteException;

    public abstract void noteNetworkInterfaceType(String s, int i)
        throws RemoteException;

    public abstract void noteNetworkStatsEnabled()
        throws RemoteException;

    public abstract void notePhoneDataConnectionState(int i, boolean flag)
        throws RemoteException;

    public abstract void notePhoneOff()
        throws RemoteException;

    public abstract void notePhoneOn()
        throws RemoteException;

    public abstract void notePhoneSignalStrength(SignalStrength signalstrength)
        throws RemoteException;

    public abstract void notePhoneState(int i)
        throws RemoteException;

    public abstract void noteResetAudio()
        throws RemoteException;

    public abstract void noteResetBleScan()
        throws RemoteException;

    public abstract void noteResetCamera()
        throws RemoteException;

    public abstract void noteResetFlashlight()
        throws RemoteException;

    public abstract void noteResetVideo()
        throws RemoteException;

    public abstract void noteScreenBrightness(int i)
        throws RemoteException;

    public abstract void noteScreenState(int i)
        throws RemoteException;

    public abstract void noteStartAudio(int i)
        throws RemoteException;

    public abstract void noteStartCamera(int i)
        throws RemoteException;

    public abstract void noteStartGps(int i)
        throws RemoteException;

    public abstract void noteStartSensor(int i, int j)
        throws RemoteException;

    public abstract void noteStartVideo(int i)
        throws RemoteException;

    public abstract void noteStartWakelock(int i, int j, String s, String s1, int k, boolean flag)
        throws RemoteException;

    public abstract void noteStartWakelockFromSource(WorkSource worksource, int i, String s, String s1, int j, boolean flag)
        throws RemoteException;

    public abstract void noteStopAudio(int i)
        throws RemoteException;

    public abstract void noteStopCamera(int i)
        throws RemoteException;

    public abstract void noteStopGps(int i)
        throws RemoteException;

    public abstract void noteStopSensor(int i, int j)
        throws RemoteException;

    public abstract void noteStopVideo(int i)
        throws RemoteException;

    public abstract void noteStopWakelock(int i, int j, String s, String s1, int k)
        throws RemoteException;

    public abstract void noteStopWakelockFromSource(WorkSource worksource, int i, String s, String s1, int j)
        throws RemoteException;

    public abstract void noteSyncFinish(String s, int i)
        throws RemoteException;

    public abstract void noteSyncStart(String s, int i)
        throws RemoteException;

    public abstract void noteUserActivity(int i, int j)
        throws RemoteException;

    public abstract void noteVibratorOff(int i)
        throws RemoteException;

    public abstract void noteVibratorOn(int i, long l)
        throws RemoteException;

    public abstract void noteWakeUp(String s, int i)
        throws RemoteException;

    public abstract void noteWifiBatchedScanStartedFromSource(WorkSource worksource, int i)
        throws RemoteException;

    public abstract void noteWifiBatchedScanStoppedFromSource(WorkSource worksource)
        throws RemoteException;

    public abstract void noteWifiControllerActivity(WifiActivityEnergyInfo wifiactivityenergyinfo)
        throws RemoteException;

    public abstract void noteWifiMulticastDisabled(int i)
        throws RemoteException;

    public abstract void noteWifiMulticastDisabledFromSource(WorkSource worksource)
        throws RemoteException;

    public abstract void noteWifiMulticastEnabled(int i)
        throws RemoteException;

    public abstract void noteWifiMulticastEnabledFromSource(WorkSource worksource)
        throws RemoteException;

    public abstract void noteWifiOff()
        throws RemoteException;

    public abstract void noteWifiOn()
        throws RemoteException;

    public abstract void noteWifiRadioPowerState(int i, long l, int j)
        throws RemoteException;

    public abstract void noteWifiRssiChanged(int i)
        throws RemoteException;

    public abstract void noteWifiRunning(WorkSource worksource)
        throws RemoteException;

    public abstract void noteWifiRunningChanged(WorkSource worksource, WorkSource worksource1)
        throws RemoteException;

    public abstract void noteWifiScanStarted(int i)
        throws RemoteException;

    public abstract void noteWifiScanStartedFromSource(WorkSource worksource)
        throws RemoteException;

    public abstract void noteWifiScanStopped(int i)
        throws RemoteException;

    public abstract void noteWifiScanStoppedFromSource(WorkSource worksource)
        throws RemoteException;

    public abstract void noteWifiState(int i, String s)
        throws RemoteException;

    public abstract void noteWifiStopped(WorkSource worksource)
        throws RemoteException;

    public abstract void noteWifiSupplicantStateChanged(int i, boolean flag)
        throws RemoteException;

    public abstract void setBatteryState(int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
        throws RemoteException;

    public abstract HealthStatsParceler takeUidSnapshot(int i)
        throws RemoteException;

    public abstract HealthStatsParceler[] takeUidSnapshots(int ai[])
        throws RemoteException;
}
