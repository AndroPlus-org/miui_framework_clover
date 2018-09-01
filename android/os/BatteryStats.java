// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.telephony.SignalStrength;
import android.text.format.DateFormat;
import android.util.*;
import com.android.internal.os.*;
import java.io.PrintWriter;
import java.util.*;

// Referenced classes of package android.os:
//            Parcelable, UserHandle, SystemClock, Parcel, 
//            ParcelFormatException

public abstract class BatteryStats
    implements Parcelable
{
    public static final class BitDescription
    {

        public final int mask;
        public final String name;
        public final int shift;
        public final String shortName;
        public final String shortValues[];
        public final String values[];

        public BitDescription(int i, int j, String s, String s1, String as[], String as1[])
        {
            mask = i;
            shift = j;
            name = s;
            shortName = s1;
            values = as;
            shortValues = as1;
        }

        public BitDescription(int i, String s, String s1)
        {
            mask = i;
            shift = -1;
            name = s;
            shortName = s1;
            values = null;
            shortValues = null;
        }
    }

    public static abstract class ControllerActivityCounter
    {

        public abstract LongCounter getIdleTimeCounter();

        public abstract LongCounter getPowerCounter();

        public abstract LongCounter getRxTimeCounter();

        public abstract LongCounter[] getTxTimeCounters();

        public ControllerActivityCounter()
        {
        }
    }

    public static abstract class Counter
    {

        public abstract int getCountLocked(int i);

        public abstract void logState(Printer printer, String s);

        public Counter()
        {
        }
    }

    public static final class DailyItem
    {

        public LevelStepTracker mChargeSteps;
        public LevelStepTracker mDischargeSteps;
        public long mEndTime;
        public ArrayList mPackageChanges;
        public long mStartTime;

        public DailyItem()
        {
        }
    }

    public static final class HistoryEventTracker
    {

        public HashMap getStateForEvent(int i)
        {
            return mActiveEvents[i];
        }

        public void removeEvents(int i)
        {
            mActiveEvents[i & 0xffff3fff] = null;
        }

        public boolean updateState(int i, String s, int j, int k)
        {
            if((0x8000 & i) == 0) goto _L2; else goto _L1
_L1:
            i &= 0xffff3fff;
            Object obj = mActiveEvents[i];
            HashMap hashmap = ((HashMap) (obj));
            if(obj == null)
            {
                hashmap = new HashMap();
                mActiveEvents[i] = hashmap;
            }
            SparseIntArray sparseintarray1 = (SparseIntArray)hashmap.get(s);
            obj = sparseintarray1;
            if(sparseintarray1 == null)
            {
                obj = new SparseIntArray();
                hashmap.put(s, obj);
            }
            if(((SparseIntArray) (obj)).indexOfKey(j) >= 0)
                return false;
            ((SparseIntArray) (obj)).put(j, k);
_L4:
            return true;
_L2:
            if((i & 0x4000) != 0)
            {
                HashMap hashmap1 = mActiveEvents[i & 0xffff3fff];
                if(hashmap1 == null)
                    return false;
                SparseIntArray sparseintarray = (SparseIntArray)hashmap1.get(s);
                if(sparseintarray == null)
                    return false;
                i = sparseintarray.indexOfKey(j);
                if(i < 0)
                    return false;
                sparseintarray.removeAt(i);
                if(sparseintarray.size() <= 0)
                    hashmap1.remove(s);
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        private final HashMap mActiveEvents[] = new HashMap[22];

        public HistoryEventTracker()
        {
        }
    }

    public static final class HistoryItem
        implements Parcelable
    {

        private void setToCommon(HistoryItem historyitem)
        {
            batteryLevel = historyitem.batteryLevel;
            batteryStatus = historyitem.batteryStatus;
            batteryHealth = historyitem.batteryHealth;
            batteryPlugType = historyitem.batteryPlugType;
            batteryTemperature = historyitem.batteryTemperature;
            batteryVoltage = historyitem.batteryVoltage;
            batteryChargeUAh = historyitem.batteryChargeUAh;
            states = historyitem.states;
            states2 = historyitem.states2;
            if(historyitem.wakelockTag != null)
            {
                wakelockTag = localWakelockTag;
                wakelockTag.setTo(historyitem.wakelockTag);
            } else
            {
                wakelockTag = null;
            }
            if(historyitem.wakeReasonTag != null)
            {
                wakeReasonTag = localWakeReasonTag;
                wakeReasonTag.setTo(historyitem.wakeReasonTag);
            } else
            {
                wakeReasonTag = null;
            }
            eventCode = historyitem.eventCode;
            if(historyitem.eventTag != null)
            {
                eventTag = localEventTag;
                eventTag.setTo(historyitem.eventTag);
            } else
            {
                eventTag = null;
            }
            currentTime = historyitem.currentTime;
        }

        public void clear()
        {
            time = 0L;
            cmd = (byte)-1;
            batteryLevel = (byte)0;
            batteryStatus = (byte)0;
            batteryHealth = (byte)0;
            batteryPlugType = (byte)0;
            batteryTemperature = (short)0;
            batteryVoltage = (char)0;
            batteryChargeUAh = 0;
            states = 0;
            states2 = 0;
            wakelockTag = null;
            wakeReasonTag = null;
            eventCode = 0;
            eventTag = null;
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean isDeltaData()
        {
            boolean flag = false;
            if(cmd == 0)
                flag = true;
            return flag;
        }

        public void readFromParcel(Parcel parcel)
        {
            int i = parcel.dataPosition();
            int j = parcel.readInt();
            cmd = (byte)(j & 0xff);
            batteryLevel = (byte)(j >> 8 & 0xff);
            batteryStatus = (byte)(j >> 16 & 0xf);
            batteryHealth = (byte)(j >> 20 & 0xf);
            batteryPlugType = (byte)(j >> 24 & 0xf);
            int k = parcel.readInt();
            batteryTemperature = (short)(k & 0xffff);
            batteryVoltage = (char)(k >> 16 & 0xffff);
            batteryChargeUAh = parcel.readInt();
            states = parcel.readInt();
            states2 = parcel.readInt();
            if((0x10000000 & j) != 0)
            {
                wakelockTag = localWakelockTag;
                wakelockTag.readFromParcel(parcel);
            } else
            {
                wakelockTag = null;
            }
            if((0x20000000 & j) != 0)
            {
                wakeReasonTag = localWakeReasonTag;
                wakeReasonTag.readFromParcel(parcel);
            } else
            {
                wakeReasonTag = null;
            }
            if((0x40000000 & j) != 0)
            {
                eventCode = parcel.readInt();
                eventTag = localEventTag;
                eventTag.readFromParcel(parcel);
            } else
            {
                eventCode = 0;
                eventTag = null;
            }
            if(cmd == 5 || cmd == 7)
                currentTime = parcel.readLong();
            else
                currentTime = 0L;
            numReadInts = numReadInts + (parcel.dataPosition() - i) / 4;
        }

        public boolean same(HistoryItem historyitem)
        {
            if(!sameNonEvent(historyitem) || eventCode != historyitem.eventCode)
                return false;
            if(wakelockTag != historyitem.wakelockTag)
            {
                if(wakelockTag == null || historyitem.wakelockTag == null)
                    return false;
                if(!wakelockTag.equals(historyitem.wakelockTag))
                    return false;
            }
            if(wakeReasonTag != historyitem.wakeReasonTag)
            {
                if(wakeReasonTag == null || historyitem.wakeReasonTag == null)
                    return false;
                if(!wakeReasonTag.equals(historyitem.wakeReasonTag))
                    return false;
            }
            if(eventTag != historyitem.eventTag)
            {
                if(eventTag == null || historyitem.eventTag == null)
                    return false;
                if(!eventTag.equals(historyitem.eventTag))
                    return false;
            }
            return true;
        }

        public boolean sameNonEvent(HistoryItem historyitem)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(batteryLevel == historyitem.batteryLevel)
            {
                flag1 = flag;
                if(batteryStatus == historyitem.batteryStatus)
                {
                    flag1 = flag;
                    if(batteryHealth == historyitem.batteryHealth)
                    {
                        flag1 = flag;
                        if(batteryPlugType == historyitem.batteryPlugType)
                        {
                            flag1 = flag;
                            if(batteryTemperature == historyitem.batteryTemperature)
                            {
                                flag1 = flag;
                                if(batteryVoltage == historyitem.batteryVoltage)
                                {
                                    flag1 = flag;
                                    if(batteryChargeUAh == historyitem.batteryChargeUAh)
                                    {
                                        flag1 = flag;
                                        if(states == historyitem.states)
                                        {
                                            flag1 = flag;
                                            if(states2 == historyitem.states2)
                                            {
                                                flag1 = flag;
                                                if(currentTime == historyitem.currentTime)
                                                    flag1 = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return flag1;
        }

        public void setTo(long l, byte byte0, HistoryItem historyitem)
        {
            time = l;
            cmd = byte0;
            setToCommon(historyitem);
        }

        public void setTo(HistoryItem historyitem)
        {
            time = historyitem.time;
            cmd = historyitem.cmd;
            setToCommon(historyitem);
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            int j = 0;
            parcel.writeLong(time);
            byte byte0 = cmd;
            byte byte1 = batteryLevel;
            byte byte2 = batteryStatus;
            byte byte3 = batteryHealth;
            byte byte4 = batteryPlugType;
            int k;
            int l;
            if(wakelockTag != null)
                k = 0x10000000;
            else
                k = 0;
            if(wakeReasonTag != null)
                l = 0x20000000;
            else
                l = 0;
            if(eventCode != 0)
                j = 0x40000000;
            parcel.writeInt(l | (byte4 << 24 & 0xf000000 | (byte0 & 0xff | byte1 << 8 & 0xff00 | byte2 << 16 & 0xf0000 | byte3 << 20 & 0xf00000) | k) | j);
            parcel.writeInt(batteryTemperature & 0xffff | batteryVoltage << 16 & 0xffff0000);
            parcel.writeInt(batteryChargeUAh);
            parcel.writeInt(states);
            parcel.writeInt(states2);
            if(wakelockTag != null)
                wakelockTag.writeToParcel(parcel, i);
            if(wakeReasonTag != null)
                wakeReasonTag.writeToParcel(parcel, i);
            if(eventCode != 0)
            {
                parcel.writeInt(eventCode);
                eventTag.writeToParcel(parcel, i);
            }
            if(cmd == 5 || cmd == 7)
                parcel.writeLong(currentTime);
        }

        public static final byte CMD_CURRENT_TIME = 5;
        public static final byte CMD_NULL = -1;
        public static final byte CMD_OVERFLOW = 6;
        public static final byte CMD_RESET = 7;
        public static final byte CMD_SHUTDOWN = 8;
        public static final byte CMD_START = 4;
        public static final byte CMD_UPDATE = 0;
        public static final int EVENT_ACTIVE = 10;
        public static final int EVENT_ALARM = 13;
        public static final int EVENT_ALARM_FINISH = 16397;
        public static final int EVENT_ALARM_START = 32781;
        public static final int EVENT_COLLECT_EXTERNAL_STATS = 14;
        public static final int EVENT_CONNECTIVITY_CHANGED = 9;
        public static final int EVENT_COUNT = 22;
        public static final int EVENT_FLAG_FINISH = 16384;
        public static final int EVENT_FLAG_START = 32768;
        public static final int EVENT_FOREGROUND = 2;
        public static final int EVENT_FOREGROUND_FINISH = 16386;
        public static final int EVENT_FOREGROUND_START = 32770;
        public static final int EVENT_JOB = 6;
        public static final int EVENT_JOB_FINISH = 16390;
        public static final int EVENT_JOB_START = 32774;
        public static final int EVENT_LONG_WAKE_LOCK = 20;
        public static final int EVENT_LONG_WAKE_LOCK_FINISH = 16404;
        public static final int EVENT_LONG_WAKE_LOCK_START = 32788;
        public static final int EVENT_NONE = 0;
        public static final int EVENT_PACKAGE_ACTIVE = 16;
        public static final int EVENT_PACKAGE_INACTIVE = 15;
        public static final int EVENT_PACKAGE_INSTALLED = 11;
        public static final int EVENT_PACKAGE_UNINSTALLED = 12;
        public static final int EVENT_PROC = 1;
        public static final int EVENT_PROC_FINISH = 16385;
        public static final int EVENT_PROC_START = 32769;
        public static final int EVENT_SCREEN_WAKE_UP = 18;
        public static final int EVENT_SYNC = 4;
        public static final int EVENT_SYNC_FINISH = 16388;
        public static final int EVENT_SYNC_START = 32772;
        public static final int EVENT_TEMP_WHITELIST = 17;
        public static final int EVENT_TEMP_WHITELIST_FINISH = 16401;
        public static final int EVENT_TEMP_WHITELIST_START = 32785;
        public static final int EVENT_TOP = 3;
        public static final int EVENT_TOP_FINISH = 16387;
        public static final int EVENT_TOP_START = 32771;
        public static final int EVENT_TYPE_MASK = -49153;
        public static final int EVENT_USER_FOREGROUND = 8;
        public static final int EVENT_USER_FOREGROUND_FINISH = 16392;
        public static final int EVENT_USER_FOREGROUND_START = 32776;
        public static final int EVENT_USER_RUNNING = 7;
        public static final int EVENT_USER_RUNNING_FINISH = 16391;
        public static final int EVENT_USER_RUNNING_START = 32775;
        public static final int EVENT_WAKEUP_AP = 19;
        public static final int EVENT_WAKE_LOCK = 5;
        public static final int EVENT_WAKE_LOCK_FINISH = 16389;
        public static final int EVENT_WAKE_LOCK_START = 32773;
        public static final int MOST_INTERESTING_STATES = 0x1c0000;
        public static final int MOST_INTERESTING_STATES2 = 0x97c00000;
        public static final int SETTLE_TO_ZERO_STATES = 0xffe30000;
        public static final int SETTLE_TO_ZERO_STATES2 = 0x683f0000;
        public static final int STATE2_BLUETOOTH_ON_FLAG = 0x400000;
        public static final int STATE2_BLUETOOTH_SCAN_FLAG = 0x100000;
        public static final int STATE2_CAMERA_FLAG = 0x200000;
        public static final int STATE2_CHARGING_FLAG = 0x1000000;
        public static final int STATE2_DEVICE_IDLE_MASK = 0x6000000;
        public static final int STATE2_DEVICE_IDLE_SHIFT = 25;
        public static final int STATE2_FLASHLIGHT_FLAG = 0x8000000;
        public static final int STATE2_PHONE_IN_CALL_FLAG = 0x800000;
        public static final int STATE2_POWER_SAVE_FLAG = 0x80000000;
        public static final int STATE2_VIDEO_ON_FLAG = 0x40000000;
        public static final int STATE2_WIFI_ON_FLAG = 0x10000000;
        public static final int STATE2_WIFI_RUNNING_FLAG = 0x20000000;
        public static final int STATE2_WIFI_SIGNAL_STRENGTH_MASK = 112;
        public static final int STATE2_WIFI_SIGNAL_STRENGTH_SHIFT = 4;
        public static final int STATE2_WIFI_SUPPL_STATE_MASK = 15;
        public static final int STATE2_WIFI_SUPPL_STATE_SHIFT = 0;
        public static final int STATE_AUDIO_ON_FLAG = 0x400000;
        public static final int STATE_BATTERY_PLUGGED_FLAG = 0x80000;
        public static final int STATE_BRIGHTNESS_MASK = 7;
        public static final int STATE_BRIGHTNESS_SHIFT = 0;
        public static final int STATE_CPU_RUNNING_FLAG = 0x80000000;
        public static final int STATE_DATA_CONNECTION_MASK = 15872;
        public static final int STATE_DATA_CONNECTION_SHIFT = 9;
        public static final int STATE_GPS_ON_FLAG = 0x20000000;
        public static final int STATE_MOBILE_RADIO_ACTIVE_FLAG = 0x2000000;
        public static final int STATE_PHONE_SCANNING_FLAG = 0x200000;
        public static final int STATE_PHONE_SIGNAL_STRENGTH_MASK = 56;
        public static final int STATE_PHONE_SIGNAL_STRENGTH_SHIFT = 3;
        public static final int STATE_PHONE_STATE_MASK = 448;
        public static final int STATE_PHONE_STATE_SHIFT = 6;
        private static final int STATE_RESERVED_0 = 0x1000000;
        public static final int STATE_SCREEN_DOZE_FLAG = 0x40000;
        public static final int STATE_SCREEN_ON_FLAG = 0x100000;
        public static final int STATE_SENSOR_ON_FLAG = 0x800000;
        public static final int STATE_WAKE_LOCK_FLAG = 0x40000000;
        public static final int STATE_WIFI_FULL_LOCK_FLAG = 0x10000000;
        public static final int STATE_WIFI_MULTICAST_ON_FLAG = 0x10000;
        public static final int STATE_WIFI_RADIO_ACTIVE_FLAG = 0x4000000;
        public static final int STATE_WIFI_SCAN_FLAG = 0x8000000;
        public int batteryChargeUAh;
        public byte batteryHealth;
        public byte batteryLevel;
        public byte batteryPlugType;
        public byte batteryStatus;
        public short batteryTemperature;
        public char batteryVoltage;
        public byte cmd;
        public long currentTime;
        public int eventCode;
        public HistoryTag eventTag;
        public final HistoryTag localEventTag;
        public final HistoryTag localWakeReasonTag;
        public final HistoryTag localWakelockTag;
        public HistoryItem next;
        public int numReadInts;
        public int states;
        public int states2;
        public HistoryStepDetails stepDetails;
        public long time;
        public HistoryTag wakeReasonTag;
        public HistoryTag wakelockTag;

        public HistoryItem()
        {
            cmd = (byte)-1;
            localWakelockTag = new HistoryTag();
            localWakeReasonTag = new HistoryTag();
            localEventTag = new HistoryTag();
        }

        public HistoryItem(long l, Parcel parcel)
        {
            cmd = (byte)-1;
            localWakelockTag = new HistoryTag();
            localWakeReasonTag = new HistoryTag();
            localEventTag = new HistoryTag();
            time = l;
            numReadInts = 2;
            readFromParcel(parcel);
        }
    }

    public static class HistoryPrinter
    {

        private void printStepCpuUidCheckinDetails(PrintWriter printwriter, int i, int j, int k)
        {
            printwriter.print('/');
            printwriter.print(i);
            printwriter.print(":");
            printwriter.print(j);
            printwriter.print(":");
            printwriter.print(k);
        }

        private void printStepCpuUidDetails(PrintWriter printwriter, int i, int j, int k)
        {
            UserHandle.formatUid(printwriter, i);
            printwriter.print("=");
            printwriter.print(j);
            printwriter.print("u+");
            printwriter.print(k);
            printwriter.print("s");
        }

        public void printNextItem(PrintWriter printwriter, HistoryItem historyitem, long l, boolean flag, boolean flag1)
        {
            if(!flag)
            {
                printwriter.print("  ");
                TimeUtils.formatDuration(historyitem.time - l, printwriter, 19);
                printwriter.print(" (");
                printwriter.print(historyitem.numReadInts);
                printwriter.print(") ");
            } else
            {
                printwriter.print(9);
                printwriter.print(',');
                printwriter.print("h");
                printwriter.print(',');
                if(lastTime < 0L)
                    printwriter.print(historyitem.time - l);
                else
                    printwriter.print(historyitem.time - lastTime);
                lastTime = historyitem.time;
            }
            if(historyitem.cmd == 4)
            {
                if(flag)
                    printwriter.print(":");
                printwriter.println("START");
                reset();
            } else
            if(historyitem.cmd == 5 || historyitem.cmd == 7)
            {
                if(flag)
                    printwriter.print(":");
                if(historyitem.cmd == 7)
                {
                    printwriter.print("RESET:");
                    reset();
                }
                printwriter.print("TIME:");
                if(flag)
                {
                    printwriter.println(historyitem.currentTime);
                } else
                {
                    printwriter.print(" ");
                    printwriter.println(DateFormat.format("yyyy-MM-dd-HH-mm-ss", historyitem.currentTime).toString());
                }
            } else
            if(historyitem.cmd == 8)
            {
                if(flag)
                    printwriter.print(":");
                printwriter.println("SHUTDOWN");
            } else
            {
label0:
                {
                    if(historyitem.cmd != 6)
                        break label0;
                    if(flag)
                        printwriter.print(":");
                    printwriter.println("*OVERFLOW*");
                }
            }
_L26:
            return;
            Object obj;
            if(!flag)
            {
                if(historyitem.batteryLevel < 10)
                    printwriter.print("00");
                else
                if(historyitem.batteryLevel < 100)
                    printwriter.print("0");
                printwriter.print(historyitem.batteryLevel);
                if(flag1)
                {
                    printwriter.print(" ");
                    int i;
                    int k;
                    if(historyitem.states >= 0)
                        if(historyitem.states < 16)
                            printwriter.print("0000000");
                        else
                        if(historyitem.states < 256)
                            printwriter.print("000000");
                        else
                        if(historyitem.states < 4096)
                            printwriter.print("00000");
                        else
                        if(historyitem.states < 0x10000)
                            printwriter.print("0000");
                        else
                        if(historyitem.states < 0x100000)
                            printwriter.print("000");
                        else
                        if(historyitem.states < 0x1000000)
                            printwriter.print("00");
                        else
                        if(historyitem.states < 0x10000000)
                            printwriter.print("0");
                    printwriter.print(Integer.toHexString(historyitem.states));
                }
            } else
            if(oldLevel != historyitem.batteryLevel)
            {
                oldLevel = historyitem.batteryLevel;
                printwriter.print(",Bl=");
                printwriter.print(historyitem.batteryLevel);
            }
            if(oldStatus == historyitem.batteryStatus) goto _L2; else goto _L1
_L1:
            oldStatus = historyitem.batteryStatus;
            if(flag)
                obj = ",Bs=";
            else
                obj = " status=";
            printwriter.print(((String) (obj)));
            oldStatus;
            JVM INSTR tableswitch 1 5: default 440
        //                       1 1613
        //                       2 1640
        //                       3 1667
        //                       4 1694
        //                       5 1721;
               goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
            printwriter.print(oldStatus);
_L2:
            if(oldHealth == historyitem.batteryHealth) goto _L10; else goto _L9
_L9:
            oldHealth = historyitem.batteryHealth;
            if(flag)
                obj = ",Bh=";
            else
                obj = " health=";
            printwriter.print(((String) (obj)));
            oldHealth;
            JVM INSTR tableswitch 1 7: default 528
        //                       1 1756
        //                       2 1783
        //                       3 1810
        //                       4 1836
        //                       5 1863
        //                       6 1890
        //                       7 1917;
               goto _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18
_L11:
            printwriter.print(oldHealth);
_L10:
            if(oldPlug == historyitem.batteryPlugType) goto _L20; else goto _L19
_L19:
            oldPlug = historyitem.batteryPlugType;
            if(flag)
                obj = ",Bp=";
            else
                obj = " plug=";
            printwriter.print(((String) (obj)));
            oldPlug;
            JVM INSTR tableswitch 0 4: default 608
        //                       0 1952
        //                       1 1979
        //                       2 2006
        //                       3 608
        //                       4 2033;
               goto _L21 _L22 _L23 _L24 _L21 _L25
_L25:
            break MISSING_BLOCK_LABEL_2033;
_L21:
            printwriter.print(oldPlug);
_L20:
            if(oldTemp != historyitem.batteryTemperature)
            {
                oldTemp = historyitem.batteryTemperature;
                if(flag)
                    obj = ",Bt=";
                else
                    obj = " temp=";
                printwriter.print(((String) (obj)));
                printwriter.print(oldTemp);
            }
            if(oldVolt != historyitem.batteryVoltage)
            {
                oldVolt = historyitem.batteryVoltage;
                if(flag)
                    obj = ",Bv=";
                else
                    obj = " volt=";
                printwriter.print(((String) (obj)));
                printwriter.print(oldVolt);
            }
            i = historyitem.batteryChargeUAh / 1000;
            if(oldChargeMAh != i)
            {
                oldChargeMAh = i;
                if(flag)
                    obj = ",Bcc=";
                else
                    obj = " charge=";
                printwriter.print(((String) (obj)));
                printwriter.print(oldChargeMAh);
            }
            BatteryStats.printBitDescriptions(printwriter, oldState, historyitem.states, historyitem.wakelockTag, BatteryStats.HISTORY_STATE_DESCRIPTIONS, flag ^ true);
            BatteryStats.printBitDescriptions(printwriter, oldState2, historyitem.states2, null, BatteryStats.HISTORY_STATE2_DESCRIPTIONS, flag ^ true);
            if(historyitem.wakeReasonTag != null)
                if(flag)
                {
                    printwriter.print(",wr=");
                    printwriter.print(historyitem.wakeReasonTag.poolIdx);
                } else
                {
                    printwriter.print(" wake_reason=");
                    printwriter.print(historyitem.wakeReasonTag.uid);
                    printwriter.print(":\"");
                    printwriter.print(historyitem.wakeReasonTag.string);
                    printwriter.print("\"");
                }
            if(historyitem.eventCode != 0)
            {
                int j;
                if(flag)
                    obj = ",";
                else
                    obj = " ";
                printwriter.print(((String) (obj)));
                if((historyitem.eventCode & 0x8000) != 0)
                    printwriter.print("+");
                else
                if((historyitem.eventCode & 0x4000) != 0)
                    printwriter.print("-");
                if(flag)
                    obj = BatteryStats.HISTORY_EVENT_CHECKIN_NAMES;
                else
                    obj = BatteryStats.HISTORY_EVENT_NAMES;
                j = historyitem.eventCode & 0xffff3fff;
                if(j >= 0 && j < obj.length)
                {
                    printwriter.print(obj[j]);
                } else
                {
                    String s;
                    if(flag)
                        s = "Ev";
                    else
                        s = "event";
                    printwriter.print(s);
                    printwriter.print(j);
                }
                printwriter.print("=");
                if(flag)
                {
                    printwriter.print(historyitem.eventTag.poolIdx);
                } else
                {
                    printwriter.append(BatteryStats.HISTORY_EVENT_INT_FORMATTERS[j].applyAsString(historyitem.eventTag.uid));
                    printwriter.print(":\"");
                    printwriter.print(historyitem.eventTag.string);
                    printwriter.print("\"");
                }
            }
            printwriter.println();
            if(historyitem.stepDetails != null)
                if(!flag)
                {
                    printwriter.print("                 Details: cpu=");
                    printwriter.print(historyitem.stepDetails.userTime);
                    printwriter.print("u+");
                    printwriter.print(historyitem.stepDetails.systemTime);
                    printwriter.print("s");
                    if(historyitem.stepDetails.appCpuUid1 >= 0)
                    {
                        printwriter.print(" (");
                        printStepCpuUidDetails(printwriter, historyitem.stepDetails.appCpuUid1, historyitem.stepDetails.appCpuUTime1, historyitem.stepDetails.appCpuSTime1);
                        if(historyitem.stepDetails.appCpuUid2 >= 0)
                        {
                            printwriter.print(", ");
                            printStepCpuUidDetails(printwriter, historyitem.stepDetails.appCpuUid2, historyitem.stepDetails.appCpuUTime2, historyitem.stepDetails.appCpuSTime2);
                        }
                        if(historyitem.stepDetails.appCpuUid3 >= 0)
                        {
                            printwriter.print(", ");
                            printStepCpuUidDetails(printwriter, historyitem.stepDetails.appCpuUid3, historyitem.stepDetails.appCpuUTime3, historyitem.stepDetails.appCpuSTime3);
                        }
                        printwriter.print(')');
                    }
                    printwriter.println();
                    printwriter.print("                          /proc/stat=");
                    printwriter.print(historyitem.stepDetails.statUserTime);
                    printwriter.print(" usr, ");
                    printwriter.print(historyitem.stepDetails.statSystemTime);
                    printwriter.print(" sys, ");
                    printwriter.print(historyitem.stepDetails.statIOWaitTime);
                    printwriter.print(" io, ");
                    printwriter.print(historyitem.stepDetails.statIrqTime);
                    printwriter.print(" irq, ");
                    printwriter.print(historyitem.stepDetails.statSoftIrqTime);
                    printwriter.print(" sirq, ");
                    printwriter.print(historyitem.stepDetails.statIdlTime);
                    printwriter.print(" idle");
                    k = historyitem.stepDetails.statUserTime + historyitem.stepDetails.statSystemTime + historyitem.stepDetails.statIOWaitTime + historyitem.stepDetails.statIrqTime + historyitem.stepDetails.statSoftIrqTime;
                    j = k + historyitem.stepDetails.statIdlTime;
                    if(j > 0)
                    {
                        printwriter.print(" (");
                        printwriter.print(String.format("%.1f%%", new Object[] {
                            Float.valueOf(((float)k / (float)j) * 100F)
                        }));
                        printwriter.print(" of ");
                        obj = new StringBuilder(64);
                        BatteryStats.formatTimeMsNoSpace(((StringBuilder) (obj)), j * 10);
                        printwriter.print(obj);
                        printwriter.print(")");
                    }
                    printwriter.print(", PlatformIdleStat ");
                    printwriter.print(historyitem.stepDetails.statPlatformIdleState);
                    printwriter.println();
                    printwriter.print(", SubsystemPowerState ");
                    printwriter.print(historyitem.stepDetails.statSubsystemPowerState);
                    printwriter.println();
                } else
                {
                    printwriter.print(9);
                    printwriter.print(',');
                    printwriter.print("h");
                    printwriter.print(",0,Dcpu=");
                    printwriter.print(historyitem.stepDetails.userTime);
                    printwriter.print(":");
                    printwriter.print(historyitem.stepDetails.systemTime);
                    if(historyitem.stepDetails.appCpuUid1 >= 0)
                    {
                        printStepCpuUidCheckinDetails(printwriter, historyitem.stepDetails.appCpuUid1, historyitem.stepDetails.appCpuUTime1, historyitem.stepDetails.appCpuSTime1);
                        if(historyitem.stepDetails.appCpuUid2 >= 0)
                            printStepCpuUidCheckinDetails(printwriter, historyitem.stepDetails.appCpuUid2, historyitem.stepDetails.appCpuUTime2, historyitem.stepDetails.appCpuSTime2);
                        if(historyitem.stepDetails.appCpuUid3 >= 0)
                            printStepCpuUidCheckinDetails(printwriter, historyitem.stepDetails.appCpuUid3, historyitem.stepDetails.appCpuUTime3, historyitem.stepDetails.appCpuSTime3);
                    }
                    printwriter.println();
                    printwriter.print(9);
                    printwriter.print(',');
                    printwriter.print("h");
                    printwriter.print(",0,Dpst=");
                    printwriter.print(historyitem.stepDetails.statUserTime);
                    printwriter.print(',');
                    printwriter.print(historyitem.stepDetails.statSystemTime);
                    printwriter.print(',');
                    printwriter.print(historyitem.stepDetails.statIOWaitTime);
                    printwriter.print(',');
                    printwriter.print(historyitem.stepDetails.statIrqTime);
                    printwriter.print(',');
                    printwriter.print(historyitem.stepDetails.statSoftIrqTime);
                    printwriter.print(',');
                    printwriter.print(historyitem.stepDetails.statIdlTime);
                    printwriter.print(',');
                    if(historyitem.stepDetails.statPlatformIdleState != null)
                    {
                        printwriter.print(historyitem.stepDetails.statPlatformIdleState);
                        if(historyitem.stepDetails.statSubsystemPowerState != null)
                            printwriter.print(',');
                    }
                    if(historyitem.stepDetails.statSubsystemPowerState != null)
                        printwriter.print(historyitem.stepDetails.statSubsystemPowerState);
                    printwriter.println();
                }
            oldState = historyitem.states;
            oldState2 = historyitem.states2;
              goto _L26
_L4:
            if(flag)
                obj = "?";
            else
                obj = "unknown";
            printwriter.print(((String) (obj)));
              goto _L2
_L5:
            if(flag)
                obj = "c";
            else
                obj = "charging";
            printwriter.print(((String) (obj)));
              goto _L2
_L6:
            if(flag)
                obj = "d";
            else
                obj = "discharging";
            printwriter.print(((String) (obj)));
              goto _L2
_L7:
            if(flag)
                obj = "n";
            else
                obj = "not-charging";
            printwriter.print(((String) (obj)));
              goto _L2
_L8:
            if(flag)
                obj = "f";
            else
                obj = "full";
            printwriter.print(((String) (obj)));
              goto _L2
_L12:
            if(flag)
                obj = "?";
            else
                obj = "unknown";
            printwriter.print(((String) (obj)));
              goto _L10
_L13:
            if(flag)
                obj = "g";
            else
                obj = "good";
            printwriter.print(((String) (obj)));
              goto _L10
_L14:
            if(flag)
                obj = "h";
            else
                obj = "overheat";
            printwriter.print(((String) (obj)));
              goto _L10
_L15:
            if(flag)
                obj = "d";
            else
                obj = "dead";
            printwriter.print(((String) (obj)));
              goto _L10
_L16:
            if(flag)
                obj = "v";
            else
                obj = "over-voltage";
            printwriter.print(((String) (obj)));
              goto _L10
_L17:
            if(flag)
                obj = "f";
            else
                obj = "failure";
            printwriter.print(((String) (obj)));
              goto _L10
_L18:
            if(flag)
                obj = "c";
            else
                obj = "cold";
            printwriter.print(((String) (obj)));
              goto _L10
_L22:
            if(flag)
                obj = "n";
            else
                obj = "none";
            printwriter.print(((String) (obj)));
              goto _L20
_L23:
            if(flag)
                obj = "a";
            else
                obj = "ac";
            printwriter.print(((String) (obj)));
              goto _L20
_L24:
            if(flag)
                obj = "u";
            else
                obj = "usb";
            printwriter.print(((String) (obj)));
              goto _L20
            if(flag)
                obj = "w";
            else
                obj = "wireless";
            printwriter.print(((String) (obj)));
              goto _L20
        }

        void reset()
        {
            oldState2 = 0;
            oldState = 0;
            oldLevel = -1;
            oldStatus = -1;
            oldHealth = -1;
            oldPlug = -1;
            oldTemp = -1;
            oldVolt = -1;
            oldChargeMAh = -1;
        }

        long lastTime;
        int oldChargeMAh;
        int oldHealth;
        int oldLevel;
        int oldPlug;
        int oldState;
        int oldState2;
        int oldStatus;
        int oldTemp;
        int oldVolt;

        public HistoryPrinter()
        {
            oldState = 0;
            oldState2 = 0;
            oldLevel = -1;
            oldStatus = -1;
            oldHealth = -1;
            oldPlug = -1;
            oldTemp = -1;
            oldVolt = -1;
            oldChargeMAh = -1;
            lastTime = -1L;
        }
    }

    public static final class HistoryStepDetails
    {

        public void clear()
        {
            systemTime = 0;
            userTime = 0;
            appCpuUid3 = -1;
            appCpuUid2 = -1;
            appCpuUid1 = -1;
            appCpuSTime3 = 0;
            appCpuUTime3 = 0;
            appCpuSTime2 = 0;
            appCpuUTime2 = 0;
            appCpuSTime1 = 0;
            appCpuUTime1 = 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            userTime = parcel.readInt();
            systemTime = parcel.readInt();
            appCpuUid1 = parcel.readInt();
            appCpuUTime1 = parcel.readInt();
            appCpuSTime1 = parcel.readInt();
            appCpuUid2 = parcel.readInt();
            appCpuUTime2 = parcel.readInt();
            appCpuSTime2 = parcel.readInt();
            appCpuUid3 = parcel.readInt();
            appCpuUTime3 = parcel.readInt();
            appCpuSTime3 = parcel.readInt();
            statUserTime = parcel.readInt();
            statSystemTime = parcel.readInt();
            statIOWaitTime = parcel.readInt();
            statIrqTime = parcel.readInt();
            statSoftIrqTime = parcel.readInt();
            statIdlTime = parcel.readInt();
            statPlatformIdleState = parcel.readString();
            statSubsystemPowerState = parcel.readString();
        }

        public void writeToParcel(Parcel parcel)
        {
            parcel.writeInt(userTime);
            parcel.writeInt(systemTime);
            parcel.writeInt(appCpuUid1);
            parcel.writeInt(appCpuUTime1);
            parcel.writeInt(appCpuSTime1);
            parcel.writeInt(appCpuUid2);
            parcel.writeInt(appCpuUTime2);
            parcel.writeInt(appCpuSTime2);
            parcel.writeInt(appCpuUid3);
            parcel.writeInt(appCpuUTime3);
            parcel.writeInt(appCpuSTime3);
            parcel.writeInt(statUserTime);
            parcel.writeInt(statSystemTime);
            parcel.writeInt(statIOWaitTime);
            parcel.writeInt(statIrqTime);
            parcel.writeInt(statSoftIrqTime);
            parcel.writeInt(statIdlTime);
            parcel.writeString(statPlatformIdleState);
            parcel.writeString(statSubsystemPowerState);
        }

        public int appCpuSTime1;
        public int appCpuSTime2;
        public int appCpuSTime3;
        public int appCpuUTime1;
        public int appCpuUTime2;
        public int appCpuUTime3;
        public int appCpuUid1;
        public int appCpuUid2;
        public int appCpuUid3;
        public int statIOWaitTime;
        public int statIdlTime;
        public int statIrqTime;
        public String statPlatformIdleState;
        public int statSoftIrqTime;
        public String statSubsystemPowerState;
        public int statSystemTime;
        public int statUserTime;
        public int systemTime;
        public int userTime;

        public HistoryStepDetails()
        {
            clear();
        }
    }

    public static final class HistoryTag
    {

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (HistoryTag)obj;
            if(uid != ((HistoryTag) (obj)).uid)
                return false;
            return string.equals(((HistoryTag) (obj)).string);
        }

        public int hashCode()
        {
            return string.hashCode() * 31 + uid;
        }

        public void readFromParcel(Parcel parcel)
        {
            string = parcel.readString();
            uid = parcel.readInt();
            poolIdx = -1;
        }

        public void setTo(HistoryTag historytag)
        {
            string = historytag.string;
            uid = historytag.uid;
            poolIdx = historytag.poolIdx;
        }

        public void setTo(String s, int i)
        {
            string = s;
            uid = i;
            poolIdx = -1;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(string);
            parcel.writeInt(uid);
        }

        public int poolIdx;
        public String string;
        public int uid;

        public HistoryTag()
        {
        }
    }

    public static interface IntToString
    {

        public abstract String applyAsString(int i);
    }

    public static final class LevelStepTracker
    {

        private void appendHex(long l, int i, StringBuilder stringbuilder)
        {
            boolean flag = false;
            do
            {
                if(i < 0)
                    break;
                int j = (int)(l >> i & 15L);
                int k = i - 4;
                if(!flag)
                {
                    i = k;
                    if(j == 0)
                        continue;
                }
                flag = true;
                if(j >= 0 && j <= 9)
                {
                    stringbuilder.append((char)(j + 48));
                    i = k;
                } else
                {
                    stringbuilder.append((char)((j + 97) - 10));
                    i = k;
                }
            } while(true);
        }

        public void addLevelSteps(int i, long l, long l1)
        {
            int j = mNumStepDurations;
            long l2 = mLastStepTime;
            int k = j;
            if(l2 >= 0L)
            {
                k = j;
                if(i > 0)
                {
                    long al[] = mStepDurations;
                    l2 = l1 - l2;
                    for(k = 0; k < i;)
                    {
                        System.arraycopy(al, 0, al, 1, al.length - 1);
                        long l3 = l2 / (long)(i - k);
                        long l4 = l2 - l3;
                        l2 = l3;
                        if(l3 > 0xffffffffffL)
                            l2 = 0xffffffffffL;
                        al[0] = l2 | l;
                        k++;
                        l2 = l4;
                    }

                    i = j + i;
                    k = i;
                    if(i > al.length)
                        k = al.length;
                }
            }
            mNumStepDurations = k;
            mLastStepTime = l1;
        }

        public void clearTime()
        {
            mLastStepTime = -1L;
        }

        public long computeTimeEstimate(long l, long l1, int ai[])
        {
            long al[] = mStepDurations;
            int i = mNumStepDurations;
            if(i <= 0)
                return -1L;
            long l2 = 0L;
            int j = 0;
            for(int k = 0; k < i;)
            {
                long l3 = al[k];
                int i1 = j;
                long l4 = l2;
                if(((al[k] & 0xff00000000000000L) >> 56 & l) == 0L)
                {
                    i1 = j;
                    l4 = l2;
                    if(((l3 & 0xff000000000000L) >> 48 & l) == l1)
                    {
                        i1 = j + 1;
                        l4 = l2 + (al[k] & 0xffffffffffL);
                    }
                }
                k++;
                j = i1;
                l2 = l4;
            }

            if(j <= 0)
                return -1L;
            if(ai != null)
                ai[0] = j;
            return (l2 / (long)j) * 100L;
        }

        public long computeTimePerLevel()
        {
            long al[] = mStepDurations;
            int i = mNumStepDurations;
            if(i <= 0)
                return -1L;
            long l = 0L;
            for(int j = 0; j < i; j++)
                l += al[j] & 0xffffffffffL;

            return l / (long)i;
        }

        public void decodeEntryAt(int i, String s)
        {
            int j = s.length();
            int k = 0;
            long l = 0L;
            do
            {
                if(k < j)
                {
                    char c = s.charAt(k);
                    if(c != '-')
                    {
                        k++;
                        switch(c)
                        {
                        case 68: // 'D'
                            l |= 0x200000000000000L;
                            break;

                        case 102: // 'f'
                            l |= 0L;
                            break;

                        case 111: // 'o'
                            l |= 0x1000000000000L;
                            break;

                        case 100: // 'd'
                            l |= 0x2000000000000L;
                            break;

                        case 122: // 'z'
                            l |= 0x3000000000000L;
                            break;

                        case 112: // 'p'
                            l |= 0x4000000000000L;
                            break;

                        case 105: // 'i'
                            l |= 0x8000000000000L;
                            break;

                        case 70: // 'F'
                            l |= 0L;
                            break;

                        case 79: // 'O'
                            l |= 0x100000000000000L;
                            break;

                        case 90: // 'Z'
                            l |= 0x300000000000000L;
                            break;

                        case 80: // 'P'
                            l |= 0x400000000000000L;
                            break;

                        case 73: // 'I'
                            l |= 0x800000000000000L;
                            break;
                        }
                        continue;
                    }
                }
                k++;
                long l1 = 0L;
                do
                {
                    if(k >= j)
                        break;
                    char c1 = s.charAt(k);
                    if(c1 == '-')
                        break;
                    int i1 = k + 1;
                    long l2 = l1 << 4;
                    if(c1 >= '0' && c1 <= '9')
                    {
                        l1 = l2 + (long)(c1 - 48);
                        k = i1;
                    } else
                    if(c1 >= 'a' && c1 <= 'f')
                    {
                        l1 = l2 + (long)((c1 - 97) + 10);
                        k = i1;
                    } else
                    {
                        k = i1;
                        l1 = l2;
                        if(c1 >= 'A')
                        {
                            k = i1;
                            l1 = l2;
                            if(c1 <= 'F')
                            {
                                l1 = l2 + (long)((c1 - 65) + 10);
                                k = i1;
                            }
                        }
                    }
                } while(true);
                k++;
                long l3 = 0L;
                do
                {
                    if(k >= j)
                        break;
                    char c2 = s.charAt(k);
                    if(c2 == '-')
                        break;
                    int j1 = k + 1;
                    long l4 = l3 << 4;
                    if(c2 >= '0' && c2 <= '9')
                    {
                        l3 = l4 + (long)(c2 - 48);
                        k = j1;
                    } else
                    if(c2 >= 'a' && c2 <= 'f')
                    {
                        l3 = l4 + (long)((c2 - 97) + 10);
                        k = j1;
                    } else
                    {
                        l3 = l4;
                        k = j1;
                        if(c2 >= 'A')
                        {
                            l3 = l4;
                            k = j1;
                            if(c2 <= 'F')
                            {
                                l3 = l4 + (long)((c2 - 65) + 10);
                                k = j1;
                            }
                        }
                    }
                } while(true);
                mStepDurations[i] = 0xffffffffffL & l3 | (l | l1 << 40 & 0xff0000000000L);
                break;
            } while(true);
        }

        public void encodeEntryAt(int i, StringBuilder stringbuilder)
        {
            long l;
            int j;
            int k;
            l = mStepDurations[i];
            j = (int)((0xff0000000000L & l) >> 40);
            i = (int)((0xff000000000000L & l) >> 48);
            k = (int)((0xff00000000000000L & l) >> 56);
            (i & 3) + 1;
            JVM INSTR tableswitch 1 4: default 76
        //                       1 205
        //                       2 215
        //                       3 225
        //                       4 235;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            if((i & 4) != 0)
                stringbuilder.append('p');
            if((i & 8) != 0)
                stringbuilder.append('i');
            (k & 3) + 1;
            JVM INSTR tableswitch 1 4: default 140
        //                       1 245
        //                       2 255
        //                       3 265
        //                       4 275;
               goto _L6 _L7 _L8 _L9 _L10
_L6:
            break; /* Loop/switch isn't completed */
_L10:
            break MISSING_BLOCK_LABEL_275;
_L11:
            if((k & 4) != 0)
                stringbuilder.append('P');
            if((k & 8) != 0)
                stringbuilder.append('I');
            stringbuilder.append('-');
            appendHex(j, 4, stringbuilder);
            stringbuilder.append('-');
            appendHex(l & 0xffffffffffL, 36, stringbuilder);
            return;
_L2:
            stringbuilder.append('f');
              goto _L1
_L3:
            stringbuilder.append('o');
              goto _L1
_L4:
            stringbuilder.append('d');
              goto _L1
_L5:
            stringbuilder.append('z');
              goto _L1
_L7:
            stringbuilder.append('F');
              goto _L11
_L8:
            stringbuilder.append('O');
              goto _L11
_L9:
            stringbuilder.append('D');
              goto _L11
            stringbuilder.append('Z');
              goto _L11
        }

        public long getDurationAt(int i)
        {
            return mStepDurations[i] & 0xffffffffffL;
        }

        public int getInitModeAt(int i)
        {
            return (int)((mStepDurations[i] & 0xff000000000000L) >> 48);
        }

        public int getLevelAt(int i)
        {
            return (int)((mStepDurations[i] & 0xff0000000000L) >> 40);
        }

        public int getModModeAt(int i)
        {
            return (int)((mStepDurations[i] & 0xff00000000000000L) >> 56);
        }

        public void init()
        {
            mLastStepTime = -1L;
            mNumStepDurations = 0;
        }

        public void readFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            if(i > mStepDurations.length)
                throw new ParcelFormatException((new StringBuilder()).append("more step durations than available: ").append(i).toString());
            mNumStepDurations = i;
            for(int j = 0; j < i; j++)
                mStepDurations[j] = parcel.readLong();

        }

        public void writeToParcel(Parcel parcel)
        {
            int i = mNumStepDurations;
            parcel.writeInt(i);
            for(int j = 0; j < i; j++)
                parcel.writeLong(mStepDurations[j]);

        }

        public long mLastStepTime;
        public int mNumStepDurations;
        public final long mStepDurations[];

        public LevelStepTracker(int i)
        {
            mLastStepTime = -1L;
            mStepDurations = new long[i];
        }

        public LevelStepTracker(int i, long al[])
        {
            mLastStepTime = -1L;
            mNumStepDurations = i;
            mStepDurations = new long[i];
            System.arraycopy(al, 0, mStepDurations, 0, i);
        }
    }

    public static abstract class LongCounter
    {

        public abstract long getCountLocked(int i);

        public abstract void logState(Printer printer, String s);

        public LongCounter()
        {
        }
    }

    public static abstract class LongCounterArray
    {

        public abstract long[] getCountsLocked(int i);

        public abstract void logState(Printer printer, String s);

        public LongCounterArray()
        {
        }
    }

    public static final class PackageChange
    {

        public String mPackageName;
        public boolean mUpdate;
        public int mVersionCode;

        public PackageChange()
        {
        }
    }

    public static abstract class Timer
    {

        public abstract int getCountLocked(int i);

        public long getCurrentDurationMsLocked(long l)
        {
            return -1L;
        }

        public long getMaxDurationMsLocked(long l)
        {
            return -1L;
        }

        public Timer getSubTimer()
        {
            return null;
        }

        public abstract long getTimeSinceMarkLocked(long l);

        public long getTotalDurationMsLocked(long l)
        {
            return -1L;
        }

        public abstract long getTotalTimeLocked(long l, int i);

        public boolean isRunningLocked()
        {
            return false;
        }

        public abstract void logState(Printer printer, String s);

        public Timer()
        {
        }
    }

    static final class TimerEntry
    {

        final int mId;
        final String mName;
        final long mTime;
        final Timer mTimer;

        TimerEntry(String s, int i, Timer timer, long l)
        {
            mName = s;
            mId = i;
            mTimer = timer;
            mTime = l;
        }
    }

    public static abstract class Uid
    {

        public abstract Timer getAggregatedPartialWakelockTimer();

        public abstract Timer getAudioTurnedOnTimer();

        public abstract ControllerActivityCounter getBluetoothControllerActivity();

        public abstract Timer getBluetoothScanBackgroundTimer();

        public abstract Counter getBluetoothScanResultBgCounter();

        public abstract Counter getBluetoothScanResultCounter();

        public abstract Timer getBluetoothScanTimer();

        public abstract Timer getBluetoothUnoptimizedScanBackgroundTimer();

        public abstract Timer getBluetoothUnoptimizedScanTimer();

        public abstract Timer getCameraTurnedOnTimer();

        public abstract long[] getCpuFreqTimes(int i);

        public abstract Timer getFlashlightTurnedOnTimer();

        public abstract Timer getForegroundActivityTimer();

        public abstract Timer getForegroundServiceTimer();

        public abstract long getFullWifiLockTime(long l, int i);

        public abstract ArrayMap getJobCompletionStats();

        public abstract ArrayMap getJobStats();

        public abstract int getMobileRadioActiveCount(int i);

        public abstract long getMobileRadioActiveTime(int i);

        public abstract long getMobileRadioApWakeupCount(int i);

        public abstract ControllerActivityCounter getModemControllerActivity();

        public abstract long getNetworkActivityBytes(int i, int j);

        public abstract long getNetworkActivityPackets(int i, int j);

        public abstract ArrayMap getPackageStats();

        public abstract SparseArray getPidStats();

        public abstract long getProcessStateTime(int i, long l, int j);

        public abstract Timer getProcessStateTimer(int i);

        public abstract ArrayMap getProcessStats();

        public abstract long[] getScreenOffCpuFreqTimes(int i);

        public abstract SparseArray getSensorStats();

        public abstract ArrayMap getSyncStats();

        public abstract long getSystemCpuTimeUs(int i);

        public abstract long getTimeAtCpuSpeed(int i, int j, int k);

        public abstract int getUid();

        public abstract int getUserActivityCount(int i, int j);

        public abstract long getUserCpuTimeUs(int i);

        public abstract Timer getVibratorOnTimer();

        public abstract Timer getVideoTurnedOnTimer();

        public abstract ArrayMap getWakelockStats();

        public abstract int getWifiBatchedScanCount(int i, int j);

        public abstract long getWifiBatchedScanTime(int i, long l, int j);

        public abstract ControllerActivityCounter getWifiControllerActivity();

        public abstract long getWifiMulticastTime(long l, int i);

        public abstract long getWifiRadioApWakeupCount(int i);

        public abstract long getWifiRunningTime(long l, int i);

        public abstract long getWifiScanActualTime(long l);

        public abstract int getWifiScanBackgroundCount(int i);

        public abstract long getWifiScanBackgroundTime(long l);

        public abstract int getWifiScanCount(int i);

        public abstract long getWifiScanTime(long l, int i);

        public abstract boolean hasNetworkActivity();

        public abstract boolean hasUserActivity();

        public abstract void noteActivityPausedLocked(long l);

        public abstract void noteActivityResumedLocked(long l);

        public abstract void noteFullWifiLockAcquiredLocked(long l);

        public abstract void noteFullWifiLockReleasedLocked(long l);

        public abstract void noteUserActivityLocked(int i);

        public abstract void noteWifiBatchedScanStartedLocked(int i, long l);

        public abstract void noteWifiBatchedScanStoppedLocked(long l);

        public abstract void noteWifiMulticastDisabledLocked(long l);

        public abstract void noteWifiMulticastEnabledLocked(long l);

        public abstract void noteWifiRunningLocked(long l);

        public abstract void noteWifiScanStartedLocked(long l);

        public abstract void noteWifiScanStoppedLocked(long l);

        public abstract void noteWifiStoppedLocked(long l);

        public static final int NUM_PROCESS_STATE = 6;
        public static final int NUM_USER_ACTIVITY_TYPES = 4;
        public static final int NUM_WIFI_BATCHED_SCAN_BINS = 5;
        public static final int PROCESS_STATE_BACKGROUND = 4;
        public static final int PROCESS_STATE_CACHED = 5;
        public static final int PROCESS_STATE_FOREGROUND = 3;
        public static final int PROCESS_STATE_FOREGROUND_SERVICE = 1;
        static final String PROCESS_STATE_NAMES[] = {
            "Top", "Fg Service", "Top Sleeping", "Foreground", "Background", "Cached"
        };
        public static final int PROCESS_STATE_TOP = 0;
        public static final int PROCESS_STATE_TOP_SLEEPING = 2;
        static final String USER_ACTIVITY_TYPES[] = {
            "other", "button", "touch", "accessibility"
        };


        public Uid()
        {
        }
    }

    public class Uid.Pid
    {

        public int mWakeNesting;
        public long mWakeStartMs;
        public long mWakeSumMs;
        final Uid this$1;

        public Uid.Pid()
        {
            this$1 = Uid.this;
            super();
        }
    }

    public static abstract class Uid.Pkg
    {

        public abstract ArrayMap getServiceStats();

        public abstract ArrayMap getWakeupAlarmStats();

        public Uid.Pkg()
        {
        }
    }

    public static abstract class Uid.Pkg.Serv
    {

        public abstract int getLaunches(int i);

        public abstract long getStartTime(long l, int i);

        public abstract int getStarts(int i);

        public Uid.Pkg.Serv()
        {
        }
    }

    public static abstract class Uid.Proc
    {

        public abstract int countExcessivePowers();

        public abstract ExcessivePower getExcessivePower(int i);

        public abstract long getForegroundTime(int i);

        public abstract int getNumAnrs(int i);

        public abstract int getNumCrashes(int i);

        public abstract int getStarts(int i);

        public abstract long getSystemTime(int i);

        public abstract long getUserTime(int i);

        public abstract boolean isActive();

        public Uid.Proc()
        {
        }
    }

    public static class Uid.Proc.ExcessivePower
    {

        public static final int TYPE_CPU = 2;
        public static final int TYPE_WAKE = 1;
        public long overTime;
        public int type;
        public long usedTime;

        public Uid.Proc.ExcessivePower()
        {
        }
    }

    public static abstract class Uid.Sensor
    {

        public abstract int getHandle();

        public abstract Timer getSensorBackgroundTime();

        public abstract Timer getSensorTime();

        public static final int GPS = -10000;

        public Uid.Sensor()
        {
        }
    }

    public static abstract class Uid.Wakelock
    {

        public abstract Timer getWakeTime(int i);

        public Uid.Wakelock()
        {
        }
    }


    static String _2D_android_os_BatteryStats_2D_mthref_2D_0(int i)
    {
        return UserHandle.formatUid(i);
    }

    static String _2D_android_os_BatteryStats_2D_mthref_2D_1(int i)
    {
        return Integer.toString(i);
    }

    private static int[] _2D_getcom_2D_android_2D_internal_2D_os_2D_BatterySipper$DrainTypeSwitchesValues()
    {
        if(_2D_com_2D_android_2D_internal_2D_os_2D_BatterySipper$DrainTypeSwitchesValues != null)
            return _2D_com_2D_android_2D_internal_2D_os_2D_BatterySipper$DrainTypeSwitchesValues;
        int ai[] = new int[com.android.internal.os.BatterySipper.DrainType.values().length];
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.APP.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror12) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.BLUETOOTH.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror11) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.CAMERA.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror10) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.CELL.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror9) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.FLASHLIGHT.ordinal()] = 5;
        }
        catch(NoSuchFieldError nosuchfielderror8) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.IDLE.ordinal()] = 6;
        }
        catch(NoSuchFieldError nosuchfielderror7) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.MEMORY.ordinal()] = 13;
        }
        catch(NoSuchFieldError nosuchfielderror6) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.OVERCOUNTED.ordinal()] = 7;
        }
        catch(NoSuchFieldError nosuchfielderror5) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.PHONE.ordinal()] = 8;
        }
        catch(NoSuchFieldError nosuchfielderror4) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.SCREEN.ordinal()] = 9;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.UNACCOUNTED.ordinal()] = 10;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.USER.ordinal()] = 11;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            ai[com.android.internal.os.BatterySipper.DrainType.WIFI.ordinal()] = 12;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        _2D_com_2D_android_2D_internal_2D_os_2D_BatterySipper$DrainTypeSwitchesValues = ai;
        return ai;
    }

    public BatteryStats()
    {
        mFormatter = new Formatter(mFormatBuilder);
    }

    private static long computeWakeLock(Timer timer, long l, int i)
    {
        if(timer != null)
            return (500L + timer.getTotalTimeLocked(l, i)) / 1000L;
        else
            return 0L;
    }

    private static boolean controllerActivityHasData(ControllerActivityCounter controlleractivitycounter, int i)
    {
        if(controlleractivitycounter == null)
            return false;
        while(controlleractivitycounter.getIdleTimeCounter().getCountLocked(i) != 0L || controlleractivitycounter.getRxTimeCounter().getCountLocked(i) != 0L || controlleractivitycounter.getPowerCounter().getCountLocked(i) != 0L) 
            return true;
        controlleractivitycounter = controlleractivitycounter.getTxTimeCounters();
        int j = controlleractivitycounter.length;
        for(int k = 0; k < j; k++)
            if(controlleractivitycounter[k].getCountLocked(i) != 0L)
                return true;

        return false;
    }

    private static final void dumpControllerActivityLine(PrintWriter printwriter, int i, String s, String s1, ControllerActivityCounter controlleractivitycounter, int j)
    {
        if(!controllerActivityHasData(controlleractivitycounter, j))
            return;
        dumpLineHeader(printwriter, i, s, s1);
        printwriter.print(",");
        printwriter.print(controlleractivitycounter.getIdleTimeCounter().getCountLocked(j));
        printwriter.print(",");
        printwriter.print(controlleractivitycounter.getRxTimeCounter().getCountLocked(j));
        printwriter.print(",");
        printwriter.print(controlleractivitycounter.getPowerCounter().getCountLocked(j) / 0x36ee80L);
        s1 = controlleractivitycounter.getTxTimeCounters();
        i = 0;
        for(int k = s1.length; i < k; i++)
        {
            s = s1[i];
            printwriter.print(",");
            printwriter.print(s.getCountLocked(j));
        }

        printwriter.println();
    }

    private void dumpDailyLevelStepSummary(PrintWriter printwriter, String s, String s1, LevelStepTracker levelsteptracker, StringBuilder stringbuilder, int ai[])
    {
        if(levelsteptracker == null)
            return;
        long l = levelsteptracker.computeTimeEstimate(0L, 0L, ai);
        if(l >= 0L)
        {
            printwriter.print(s);
            printwriter.print(s1);
            printwriter.print(" total time: ");
            stringbuilder.setLength(0);
            formatTimeMs(stringbuilder, l);
            printwriter.print(stringbuilder);
            printwriter.print(" (from ");
            printwriter.print(ai[0]);
            printwriter.println(" steps)");
        }
        for(int i = 0; i < STEP_LEVEL_MODES_OF_INTEREST.length; i++)
        {
            long l1 = levelsteptracker.computeTimeEstimate(STEP_LEVEL_MODES_OF_INTEREST[i], STEP_LEVEL_MODE_VALUES[i], ai);
            if(l1 > 0L)
            {
                printwriter.print(s);
                printwriter.print(s1);
                printwriter.print(" ");
                printwriter.print(STEP_LEVEL_MODE_LABELS[i]);
                printwriter.print(" time: ");
                stringbuilder.setLength(0);
                formatTimeMs(stringbuilder, l1);
                printwriter.print(stringbuilder);
                printwriter.print(" (from ");
                printwriter.print(ai[0]);
                printwriter.println(" steps)");
            }
        }

    }

    private void dumpDailyPackageChanges(PrintWriter printwriter, String s, ArrayList arraylist)
    {
        if(arraylist == null)
            return;
        printwriter.print(s);
        printwriter.println("Package changes:");
        int i = 0;
        while(i < arraylist.size()) 
        {
            PackageChange packagechange = (PackageChange)arraylist.get(i);
            if(packagechange.mUpdate)
            {
                printwriter.print(s);
                printwriter.print("  Update ");
                printwriter.print(packagechange.mPackageName);
                printwriter.print(" vers=");
                printwriter.println(packagechange.mVersionCode);
            } else
            {
                printwriter.print(s);
                printwriter.print("  Uninstall ");
                printwriter.println(packagechange.mPackageName);
            }
            i++;
        }
    }

    private static boolean dumpDurationSteps(PrintWriter printwriter, String s, String s1, LevelStepTracker levelsteptracker, boolean flag)
    {
        int i;
        String as[];
        int j;
        if(levelsteptracker == null)
            return false;
        i = levelsteptracker.mNumStepDurations;
        if(i <= 0)
            return false;
        if(!flag)
            printwriter.println(s1);
        as = new String[5];
        j = 0;
_L21:
        long l;
        int k;
        long l1;
        long l2;
        if(j >= i)
            break MISSING_BLOCK_LABEL_645;
        l = levelsteptracker.getDurationAt(j);
        k = levelsteptracker.getLevelAt(j);
        l1 = levelsteptracker.getInitModeAt(j);
        l2 = levelsteptracker.getModModeAt(j);
        if(!flag) goto _L2; else goto _L1
_L1:
        as[0] = Long.toString(l);
        as[1] = Integer.toString(k);
        if((3L & l2) != 0L) goto _L4; else goto _L3
_L3:
        (int)(3L & l1) + 1;
        JVM INSTR tableswitch 1 4: default 152
    //                   1 242
    //                   2 252
    //                   3 262
    //                   4 272;
           goto _L5 _L6 _L7 _L8 _L9
_L5:
        as[2] = "?";
_L10:
        String s2;
        if((4L & l2) == 0L)
        {
            if((4L & l1) != 0L)
                s2 = "p+";
            else
                s2 = "p-";
            as[3] = s2;
        } else
        {
            as[3] = "";
        }
        if((8L & l2) == 0L)
        {
            if((8L & l1) != 0L)
                s2 = "i+";
            else
                s2 = "i-";
            as[4] = s2;
        } else
        {
            as[4] = "";
        }
        dumpLine(printwriter, 0, "i", s1, as);
_L18:
        j++;
        continue; /* Loop/switch isn't completed */
_L6:
        as[2] = "s-";
          goto _L10
_L7:
        as[2] = "s+";
          goto _L10
_L8:
        as[2] = "sd";
          goto _L10
_L9:
        as[2] = "sds";
          goto _L10
_L4:
        as[2] = "";
          goto _L10
_L2:
        boolean flag2;
        printwriter.print(s);
        printwriter.print("#");
        printwriter.print(j);
        printwriter.print(": ");
        TimeUtils.formatDuration(l, printwriter);
        printwriter.print(" to ");
        printwriter.print(k);
        flag2 = false;
        if((3L & l2) != 0L) goto _L12; else goto _L11
_L11:
        printwriter.print(" (");
        (int)(3L & l1) + 1;
        JVM INSTR tableswitch 1 4: default 432
    //                   1 573
    //                   2 583
    //                   3 593
    //                   4 603;
           goto _L13 _L14 _L15 _L16 _L17
_L17:
        break MISSING_BLOCK_LABEL_603;
_L13:
        printwriter.print("screen-?");
_L19:
        flag2 = true;
_L12:
        boolean flag1 = flag2;
        String s3;
        if((4L & l2) == 0L)
        {
            if(flag2)
                s3 = ", ";
            else
                s3 = " (";
            printwriter.print(s3);
            if((4L & l1) != 0L)
                s3 = "power-save-on";
            else
                s3 = "power-save-off";
            printwriter.print(s3);
            flag1 = true;
        }
        flag2 = flag1;
        if((8L & l2) == 0L)
        {
            if(flag1)
                s3 = ", ";
            else
                s3 = " (";
            printwriter.print(s3);
            if((8L & l1) != 0L)
                s3 = "device-idle-on";
            else
                s3 = "device-idle-off";
            printwriter.print(s3);
            flag2 = true;
        }
        if(flag2)
            printwriter.print(")");
        printwriter.println();
          goto _L18
_L14:
        printwriter.print("screen-off");
          goto _L19
_L15:
        printwriter.print("screen-on");
          goto _L19
_L16:
        printwriter.print("screen-doze");
          goto _L19
        printwriter.print("screen-doze-suspend");
          goto _L19
        return true;
        if(true) goto _L21; else goto _L20
_L20:
    }

    private void dumpHistoryLocked(PrintWriter printwriter, int i, long l, boolean flag)
    {
        HistoryPrinter historyprinter;
        HistoryItem historyitem;
        long l1;
        long l2;
        int j;
        HistoryTag historytag;
        historyprinter = new HistoryPrinter();
        historyitem = new HistoryItem();
        l1 = -1L;
        l2 = -1L;
        j = 0;
        historytag = null;
_L2:
        long l3;
        long l4;
        int k;
        HistoryTag historytag1;
        if(!getNextHistoryLocked(historyitem))
            break; /* Loop/switch isn't completed */
        l3 = historyitem.time;
        l4 = l2;
        if(l2 < 0L)
            l4 = l3;
        l2 = l4;
        l1 = l3;
        if(historyitem.time < l)
            continue; /* Loop/switch isn't completed */
        k = j;
        historytag1 = historytag;
        if(l < 0L)
            break MISSING_BLOCK_LABEL_554;
        k = j;
        historytag1 = historytag;
        if(!(j ^ true))
            break MISSING_BLOCK_LABEL_554;
        break MISSING_BLOCK_LABEL_112;
        int i1;
        boolean flag1;
        if(historyitem.cmd == 5 || historyitem.cmd == 7 || historyitem.cmd == 4 || historyitem.cmd == 8)
        {
            j = 1;
            if((i & 0x20) != 0)
                flag1 = true;
            else
                flag1 = false;
            historyprinter.printNextItem(printwriter, historyitem, l4, flag, flag1);
            historyitem.cmd = (byte)0;
        } else
        if(historyitem.currentTime != 0L)
        {
            j = 1;
            k = historyitem.cmd;
            historyitem.cmd = (byte)5;
            if((i & 0x20) != 0)
                flag1 = true;
            else
                flag1 = false;
            historyprinter.printNextItem(printwriter, historyitem, l4, flag, flag1);
            historyitem.cmd = (byte)k;
        }
        k = j;
        historytag1 = historytag;
        if(true)
            break MISSING_BLOCK_LABEL_554;
        if(historyitem.cmd != 0)
        {
            if((i & 0x20) != 0)
                flag1 = true;
            else
                flag1 = false;
            historyprinter.printNextItem(printwriter, historyitem, l4, flag, flag1);
            historyitem.cmd = (byte)0;
        }
        i1 = historyitem.eventCode;
        historytag1 = historyitem.eventTag;
        historyitem.eventTag = new HistoryTag();
        k = 0;
        while(k < 22) 
        {
            Object obj = historytag.getStateForEvent(k);
            if(obj != null)
            {
                obj = ((HashMap) (obj)).entrySet().iterator();
                while(((Iterator) (obj)).hasNext()) 
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
                    SparseIntArray sparseintarray = (SparseIntArray)entry.getValue();
                    int j1 = 0;
                    while(j1 < sparseintarray.size()) 
                    {
                        historyitem.eventCode = k;
                        historyitem.eventTag.string = (String)entry.getKey();
                        historyitem.eventTag.uid = sparseintarray.keyAt(j1);
                        historyitem.eventTag.poolIdx = sparseintarray.valueAt(j1);
                        boolean flag2;
                        if((i & 0x20) != 0)
                            flag2 = true;
                        else
                            flag2 = false;
                        historyprinter.printNextItem(printwriter, historyitem, l4, flag, flag2);
                        historyitem.wakeReasonTag = null;
                        historyitem.wakelockTag = null;
                        j1++;
                    }
                }
            }
            k++;
        }
        break MISSING_BLOCK_LABEL_533;
        historyitem.eventCode = i1;
        historyitem.eventTag = historytag1;
        historytag1 = null;
        k = j;
        boolean flag3;
        if((i & 0x20) != 0)
            flag3 = true;
        else
            flag3 = false;
        historyprinter.printNextItem(printwriter, historyitem, l4, flag, flag3);
        l2 = l4;
        l1 = l3;
        j = k;
        historytag = historytag1;
        if(true) goto _L2; else goto _L1
_L1:
        if(l >= 0L)
        {
            commitCurrentHistoryBatchLocked();
            String s;
            if(flag)
                s = "NEXT: ";
            else
                s = "  NEXT: ";
            printwriter.print(s);
            printwriter.println(1L + l1);
        }
        return;
    }

    private static final transient void dumpLine(PrintWriter printwriter, int i, String s, String s1, Object aobj[])
    {
        dumpLineHeader(printwriter, i, s, s1);
        i = 0;
        for(int j = aobj.length; i < j; i++)
        {
            s = ((String) (aobj[i]));
            printwriter.print(',');
            printwriter.print(s);
        }

        printwriter.println();
    }

    private static final void dumpLineHeader(PrintWriter printwriter, int i, String s, String s1)
    {
        printwriter.print(9);
        printwriter.print(',');
        printwriter.print(i);
        printwriter.print(',');
        printwriter.print(s);
        printwriter.print(',');
        printwriter.print(s1);
    }

    private static boolean dumpTimeEstimate(PrintWriter printwriter, String s, String s1, String s2, long l)
    {
        if(l < 0L)
        {
            return false;
        } else
        {
            printwriter.print(s);
            printwriter.print(s1);
            printwriter.print(s2);
            s = new StringBuilder(64);
            formatTimeMs(s, l);
            printwriter.print(s);
            printwriter.println();
            return true;
        }
    }

    private static final void dumpTimer(PrintWriter printwriter, int i, String s, String s1, Timer timer, long l, int j)
    {
        if(timer != null)
        {
            l = (timer.getTotalTimeLocked(l, j) + 500L) / 1000L;
            j = timer.getCountLocked(j);
            if(l != 0L)
                dumpLine(printwriter, i, s, s1, new Object[] {
                    Long.valueOf(l), Integer.valueOf(j)
                });
        }
    }

    public static final void formatTimeMs(StringBuilder stringbuilder, long l)
    {
        long l1 = l / 1000L;
        formatTimeRaw(stringbuilder, l1);
        stringbuilder.append(l - 1000L * l1);
        stringbuilder.append("ms ");
    }

    public static final void formatTimeMsNoSpace(StringBuilder stringbuilder, long l)
    {
        long l1 = l / 1000L;
        formatTimeRaw(stringbuilder, l1);
        stringbuilder.append(l - 1000L * l1);
        stringbuilder.append("ms");
    }

    private static final void formatTimeRaw(StringBuilder stringbuilder, long l)
    {
        long l1 = l / 0x15180L;
        if(l1 != 0L)
        {
            stringbuilder.append(l1);
            stringbuilder.append("d ");
        }
        long l2 = 60L * l1 * 60L * 24L;
        l1 = (l - l2) / 3600L;
        if(l1 != 0L || l2 != 0L)
        {
            stringbuilder.append(l1);
            stringbuilder.append("h ");
        }
        l2 += 60L * l1 * 60L;
        l1 = (l - l2) / 60L;
        if(l1 != 0L || l2 != 0L)
        {
            stringbuilder.append(l1);
            stringbuilder.append("m ");
        }
        l1 = l2 + 60L * l1;
        if(l != 0L || l1 != 0L)
        {
            stringbuilder.append(l - l1);
            stringbuilder.append("s ");
        }
    }

    static void printBitDescriptions(PrintWriter printwriter, int i, int j, HistoryTag historytag, BitDescription abitdescription[], boolean flag)
    {
        int k = i ^ j;
        if(k == 0)
            return;
        boolean flag1 = false;
        i = 0;
        while(i < abitdescription.length) 
        {
            BitDescription bitdescription = abitdescription[i];
            int l = ((flag1) ? 1 : 0);
            if((bitdescription.mask & k) != 0)
            {
                String s;
                if(flag)
                    s = " ";
                else
                    s = ",";
                printwriter.print(s);
                if(bitdescription.shift < 0)
                {
                    if((bitdescription.mask & j) != 0)
                        s = "+";
                    else
                        s = "-";
                    printwriter.print(s);
                    if(flag)
                        s = bitdescription.name;
                    else
                        s = bitdescription.shortName;
                    printwriter.print(s);
                    l = ((flag1) ? 1 : 0);
                    if(bitdescription.mask == 0x40000000)
                    {
                        l = ((flag1) ? 1 : 0);
                        if(historytag != null)
                        {
                            l = 1;
                            printwriter.print("=");
                            if(flag)
                            {
                                UserHandle.formatUid(printwriter, historytag.uid);
                                printwriter.print(":\"");
                                printwriter.print(historytag.string);
                                printwriter.print("\"");
                            } else
                            {
                                printwriter.print(historytag.poolIdx);
                            }
                        }
                    }
                } else
                {
                    String s1;
                    if(flag)
                        s1 = bitdescription.name;
                    else
                        s1 = bitdescription.shortName;
                    printwriter.print(s1);
                    printwriter.print("=");
                    l = (bitdescription.mask & j) >> bitdescription.shift;
                    if(bitdescription.values != null && l >= 0 && l < bitdescription.values.length)
                    {
                        if(flag)
                            s1 = bitdescription.values[l];
                        else
                            s1 = bitdescription.shortValues[l];
                        printwriter.print(s1);
                        l = ((flag1) ? 1 : 0);
                    } else
                    {
                        printwriter.print(l);
                        l = ((flag1) ? 1 : 0);
                    }
                }
            }
            i++;
            flag1 = l;
        }
        if(!flag1 && historytag != null)
        {
            if(flag)
                abitdescription = " wake_lock=";
            else
                abitdescription = ",w=";
            printwriter.print(abitdescription);
            if(flag)
            {
                UserHandle.formatUid(printwriter, historytag.uid);
                printwriter.print(":\"");
                printwriter.print(historytag.string);
                printwriter.print("\"");
            } else
            {
                printwriter.print(historytag.poolIdx);
            }
        }
    }

    private final void printControllerActivity(PrintWriter printwriter, StringBuilder stringbuilder, String s, String s1, ControllerActivityCounter controlleractivitycounter, int i)
    {
        long l = controlleractivitycounter.getIdleTimeCounter().getCountLocked(i);
        long l1 = controlleractivitycounter.getRxTimeCounter().getCountLocked(i);
        long l2 = controlleractivitycounter.getPowerCounter().getCountLocked(i);
        long l3 = computeBatteryRealtime(SystemClock.elapsedRealtime() * 1000L, i) / 1000L;
        long l4 = 0L;
        Object aobj[] = controlleractivitycounter.getTxTimeCounters();
        int j = 0;
        for(int i1 = aobj.length; j < i1; j++)
            l4 += aobj[j].getCountLocked(i);

        l4 = l3 - (l + l1 + l4);
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("     ");
        stringbuilder.append(s1);
        stringbuilder.append(" Sleep time:  ");
        formatTimeMs(stringbuilder, l4);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l4, l3));
        stringbuilder.append(")");
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("     ");
        stringbuilder.append(s1);
        stringbuilder.append(" Idle time:   ");
        formatTimeMs(stringbuilder, l);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l, l3));
        stringbuilder.append(")");
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("     ");
        stringbuilder.append(s1);
        stringbuilder.append(" Rx time:     ");
        formatTimeMs(stringbuilder, l1);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l1, l3));
        stringbuilder.append(")");
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("     ");
        stringbuilder.append(s1);
        stringbuilder.append(" Tx time:     ");
        int j1;
        if(s1.equals("Cellular"))
        {
            aobj = new String[5];
            aobj[0] = "   less than 0dBm: ";
            aobj[1] = "   0dBm to 8dBm: ";
            aobj[2] = "   8dBm to 15dBm: ";
            aobj[3] = "   15dBm to 20dBm: ";
            aobj[4] = "   above 20dBm: ";
        } else
        {
            aobj = new String[5];
            aobj[0] = "[0]";
            aobj[1] = "[1]";
            aobj[2] = "[2]";
            aobj[3] = "[3]";
            aobj[4] = "[4]";
        }
        j1 = Math.min(controlleractivitycounter.getTxTimeCounters().length, aobj.length);
        if(j1 > 1)
        {
            printwriter.println(stringbuilder.toString());
            for(int k = 0; k < j1; k++)
            {
                long l5 = controlleractivitycounter.getTxTimeCounters()[k].getCountLocked(i);
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("    ");
                stringbuilder.append(aobj[k]);
                stringbuilder.append(" ");
                formatTimeMs(stringbuilder, l5);
                stringbuilder.append("(");
                stringbuilder.append(formatRatioLocked(l5, l3));
                stringbuilder.append(")");
                printwriter.println(stringbuilder.toString());
            }

        } else
        {
            long l6 = controlleractivitycounter.getTxTimeCounters()[0].getCountLocked(i);
            formatTimeMs(stringbuilder, l6);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l6, l3));
            stringbuilder.append(")");
            printwriter.println(stringbuilder.toString());
        }
        if(l2 > 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("     ");
            stringbuilder.append(s1);
            stringbuilder.append(" Battery drain: ").append(BatteryStatsHelper.makemAh((double)l2 / 3600000D));
            stringbuilder.append("mAh");
            printwriter.println(stringbuilder.toString());
        }
    }

    private final void printControllerActivityIfInteresting(PrintWriter printwriter, StringBuilder stringbuilder, String s, String s1, ControllerActivityCounter controlleractivitycounter, int i)
    {
        if(controllerActivityHasData(controlleractivitycounter, i))
            printControllerActivity(printwriter, stringbuilder, s, s1, controlleractivitycounter, i);
    }

    private void printSizeValue(PrintWriter printwriter, long l)
    {
        float f = l;
        String s = "";
        float f1 = f;
        if(f >= 10240F)
        {
            s = "KB";
            f1 = f / 1024F;
        }
        float f2 = f1;
        if(f1 >= 10240F)
        {
            s = "MB";
            f2 = f1 / 1024F;
        }
        f = f2;
        if(f2 >= 10240F)
        {
            s = "GB";
            f = f2 / 1024F;
        }
        f1 = f;
        if(f >= 10240F)
        {
            s = "TB";
            f1 = f / 1024F;
        }
        f = f1;
        if(f1 >= 10240F)
        {
            s = "PB";
            f = f1 / 1024F;
        }
        printwriter.print((int)f);
        printwriter.print(s);
    }

    private static final boolean printTimer(PrintWriter printwriter, StringBuilder stringbuilder, Timer timer, long l, int i, String s, String s1)
    {
        if(timer != null)
        {
            long l1 = (timer.getTotalTimeLocked(l, i) + 500L) / 1000L;
            i = timer.getCountLocked(i);
            if(l1 != 0L)
            {
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("    ");
                stringbuilder.append(s1);
                stringbuilder.append(": ");
                formatTimeMs(stringbuilder, l1);
                stringbuilder.append("realtime (");
                stringbuilder.append(i);
                stringbuilder.append(" times)");
                l1 = timer.getMaxDurationMsLocked(l / 1000L);
                if(l1 >= 0L)
                {
                    stringbuilder.append(" max=");
                    stringbuilder.append(l1);
                }
                if(timer.isRunningLocked())
                {
                    l = timer.getCurrentDurationMsLocked(l / 1000L);
                    if(l >= 0L)
                    {
                        stringbuilder.append(" (running for ");
                        stringbuilder.append(l);
                        stringbuilder.append("ms)");
                    } else
                    {
                        stringbuilder.append(" (running)");
                    }
                }
                printwriter.println(stringbuilder.toString());
                return true;
            }
        }
        return false;
    }

    private static final String printWakeLock(StringBuilder stringbuilder, Timer timer, long l, String s, int i, String s1)
    {
        if(timer != null)
        {
            long l1 = computeWakeLock(timer, l, i);
            i = timer.getCountLocked(i);
            if(l1 != 0L)
            {
                stringbuilder.append(s1);
                formatTimeMs(stringbuilder, l1);
                if(s != null)
                {
                    stringbuilder.append(s);
                    stringbuilder.append(' ');
                }
                stringbuilder.append('(');
                stringbuilder.append(i);
                stringbuilder.append(" times)");
                long l2 = timer.getMaxDurationMsLocked(l / 1000L);
                if(l2 >= 0L)
                {
                    stringbuilder.append(" max=");
                    stringbuilder.append(l2);
                }
                l2 = timer.getTotalDurationMsLocked(l / 1000L);
                if(l2 > l1)
                {
                    stringbuilder.append(" actual=");
                    stringbuilder.append(l2);
                }
                if(timer.isRunningLocked())
                {
                    l = timer.getCurrentDurationMsLocked(l / 1000L);
                    if(l >= 0L)
                    {
                        stringbuilder.append(" (running for ");
                        stringbuilder.append(l);
                        stringbuilder.append("ms)");
                    } else
                    {
                        stringbuilder.append(" (running)");
                    }
                }
                return ", ";
            }
        }
        return s1;
    }

    private static final String printWakeLockCheckin(StringBuilder stringbuilder, Timer timer, long l, String s, int i, String s1)
    {
        long l1 = 0L;
        int j = 0;
        long l2 = 0L;
        long l3 = 0L;
        long l4 = 0L;
        if(timer != null)
        {
            l1 = timer.getTotalTimeLocked(l, i);
            j = timer.getCountLocked(i);
            l3 = timer.getCurrentDurationMsLocked(l / 1000L);
            l2 = timer.getMaxDurationMsLocked(l / 1000L);
            l4 = timer.getTotalDurationMsLocked(l / 1000L);
        }
        stringbuilder.append(s1);
        stringbuilder.append((500L + l1) / 1000L);
        stringbuilder.append(',');
        if(s != null)
            timer = (new StringBuilder()).append(s).append(",").toString();
        else
            timer = "";
        stringbuilder.append(timer);
        stringbuilder.append(j);
        stringbuilder.append(',');
        stringbuilder.append(l3);
        stringbuilder.append(',');
        stringbuilder.append(l2);
        if(s != null)
        {
            stringbuilder.append(',');
            stringbuilder.append(l4);
        }
        return ",";
    }

    private void printmAh(PrintWriter printwriter, double d)
    {
        printwriter.print(BatteryStatsHelper.makemAh(d));
    }

    private void printmAh(StringBuilder stringbuilder, double d)
    {
        stringbuilder.append(BatteryStatsHelper.makemAh(d));
    }

    public abstract void commitCurrentHistoryBatchLocked();

    public abstract long computeBatteryRealtime(long l, int i);

    public abstract long computeBatteryScreenOffRealtime(long l, int i);

    public abstract long computeBatteryScreenOffUptime(long l, int i);

    public abstract long computeBatteryTimeRemaining(long l);

    public abstract long computeBatteryUptime(long l, int i);

    public abstract long computeChargeTimeRemaining(long l);

    public abstract long computeRealtime(long l, int i);

    public abstract long computeUptime(long l, int i);

    public final void dumpCheckinLocked(Context context, PrintWriter printwriter, int i, int j)
    {
        dumpCheckinLocked(context, printwriter, i, j, BatteryStatsHelper.checkWifiOnly(context));
    }

    public final void dumpCheckinLocked(Context context, PrintWriter printwriter, int i, int j, boolean flag)
    {
        long l2;
        long l3;
        long l4;
        StringBuilder stringbuilder;
        SparseArray sparsearray;
        int j3;
        String s;
        Object obj;
        int i9;
        long l = SystemClock.uptimeMillis() * 1000L;
        l2 = SystemClock.elapsedRealtime() * 1000L;
        l3 = (500L + l2) / 1000L;
        l4 = getBatteryUptime(l);
        long l5 = computeBatteryUptime(l, i);
        long l15 = computeBatteryRealtime(l2, i);
        long l17 = computeBatteryScreenOffUptime(l, i);
        long l28 = computeBatteryScreenOffRealtime(l2, i);
        long l30 = computeRealtime(l2, i);
        long l34 = computeUptime(l, i);
        long l36 = getScreenOnTime(l2, i);
        long l38 = getScreenDozeTime(l2, i);
        long l40 = getInteractiveTime(l2, i);
        long l42 = getPowerSaveModeEnabledTime(l2, i);
        long l44 = getDeviceIdleModeTime(1, l2, i);
        long l46 = getDeviceIdleModeTime(2, l2, i);
        long l48 = getDeviceIdlingTime(1, l2, i);
        long l50 = getDeviceIdlingTime(2, l2, i);
        int k = getNumConnectivityChange(i);
        l = getPhoneOnTime(l2, i);
        long l52 = getMahDischarge(i);
        long l54 = getMahDischargeScreenOff(i);
        long l56 = getMahDischargeScreenDoze(i);
        stringbuilder = new StringBuilder(128);
        sparsearray = getUidStats();
        j3 = sparsearray.size();
        s = STAT_NAMES[i];
        if(i == 0)
            obj = Integer.valueOf(getStartCount());
        else
            obj = "N/A";
        dumpLine(printwriter, 0, s, "bt", new Object[] {
            obj, Long.valueOf(l15 / 1000L), Long.valueOf(l5 / 1000L), Long.valueOf(l30 / 1000L), Long.valueOf(l34 / 1000L), Long.valueOf(getStartClockTime()), Long.valueOf(l28 / 1000L), Long.valueOf(l17 / 1000L), Integer.valueOf(getEstimatedBatteryCapacity()), Integer.valueOf(getMinLearnedBatteryCapacity()), 
            Integer.valueOf(getMaxLearnedBatteryCapacity()), Long.valueOf(l38 / 1000L)
        });
        l5 = 0L;
        l17 = 0L;
        for(int k3 = 0; k3 < j3;)
        {
            obj = ((Uid)sparsearray.valueAt(k3)).getWakelockStats();
            int k8 = ((ArrayMap) (obj)).size() - 1;
            l28 = l5;
            while(k8 >= 0) 
            {
                Uid.Wakelock wakelock = (Uid.Wakelock)((ArrayMap) (obj)).valueAt(k8);
                Timer timer3 = wakelock.getWakeTime(1);
                l5 = l28;
                if(timer3 != null)
                    l5 = l28 + timer3.getTotalTimeLocked(l2, i);
                timer3 = wakelock.getWakeTime(0);
                l30 = l17;
                if(timer3 != null)
                    l30 = l17 + timer3.getTotalTimeLocked(l2, i);
                k8--;
                l28 = l5;
                l17 = l30;
            }
            k3++;
            l5 = l28;
        }

        dumpLine(printwriter, 0, s, "gn", new Object[] {
            Long.valueOf(getNetworkActivityBytes(0, i)), Long.valueOf(getNetworkActivityBytes(1, i)), Long.valueOf(getNetworkActivityBytes(2, i)), Long.valueOf(getNetworkActivityBytes(3, i)), Long.valueOf(getNetworkActivityPackets(0, i)), Long.valueOf(getNetworkActivityPackets(1, i)), Long.valueOf(getNetworkActivityPackets(2, i)), Long.valueOf(getNetworkActivityPackets(3, i)), Long.valueOf(getNetworkActivityBytes(4, i)), Long.valueOf(getNetworkActivityBytes(5, i))
        });
        dumpControllerActivityLine(printwriter, 0, s, "gmcd", getModemControllerActivity(), i);
        l28 = getWifiOnTime(l2, i);
        l30 = getGlobalWifiRunningTime(l2, i);
        dumpLine(printwriter, 0, s, "gwfl", new Object[] {
            Long.valueOf(l28 / 1000L), Long.valueOf(l30 / 1000L), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)
        });
        dumpControllerActivityLine(printwriter, 0, s, "gwfcd", getWifiControllerActivity(), i);
        dumpControllerActivityLine(printwriter, 0, s, "gble", getBluetoothControllerActivity(), i);
        dumpLine(printwriter, 0, s, "m", new Object[] {
            Long.valueOf(l36 / 1000L), Long.valueOf(l / 1000L), Long.valueOf(l5 / 1000L), Long.valueOf(l17 / 1000L), Long.valueOf(getMobileRadioActiveTime(l2, i) / 1000L), Long.valueOf(getMobileRadioActiveAdjustedTime(i) / 1000L), Long.valueOf(l40 / 1000L), Long.valueOf(l42 / 1000L), Integer.valueOf(k), Long.valueOf(l46 / 1000L), 
            Integer.valueOf(getDeviceIdleModeCount(2, i)), Long.valueOf(l50 / 1000L), Integer.valueOf(getDeviceIdlingCount(2, i)), Integer.valueOf(getMobileRadioActiveCount(i)), Long.valueOf(getMobileRadioActiveUnknownTime(i) / 1000L), Long.valueOf(l44 / 1000L), Integer.valueOf(getDeviceIdleModeCount(1, i)), Long.valueOf(l48 / 1000L), Integer.valueOf(getDeviceIdlingCount(1, i)), Long.valueOf(getLongestDeviceIdleModeTime(1)), 
            Long.valueOf(getLongestDeviceIdleModeTime(2))
        });
        Object aobj[] = new Object[5];
        for(int i4 = 0; i4 < 5; i4++)
            aobj[i4] = Long.valueOf(getScreenBrightnessTime(i4, l2, i) / 1000L);

        dumpLine(printwriter, 0, s, "br", aobj);
        aobj = new Object[6];
        for(int j4 = 0; j4 < 6; j4++)
            aobj[j4] = Long.valueOf(getPhoneSignalStrengthTime(j4, l2, i) / 1000L);

        dumpLine(printwriter, 0, s, "sgt", aobj);
        dumpLine(printwriter, 0, s, "sst", new Object[] {
            Long.valueOf(getPhoneSignalScanningTime(l2, i) / 1000L)
        });
        for(int k4 = 0; k4 < 6; k4++)
            aobj[k4] = Integer.valueOf(getPhoneSignalStrengthCount(k4, i));

        dumpLine(printwriter, 0, s, "sgc", aobj);
        aobj = new Object[17];
        for(int i5 = 0; i5 < 17; i5++)
            aobj[i5] = Long.valueOf(getPhoneDataConnectionTime(i5, l2, i) / 1000L);

        dumpLine(printwriter, 0, s, "dct", aobj);
        for(int j5 = 0; j5 < 17; j5++)
            aobj[j5] = Integer.valueOf(getPhoneDataConnectionCount(j5, i));

        dumpLine(printwriter, 0, s, "dcc", aobj);
        aobj = new Object[8];
        for(int k5 = 0; k5 < 8; k5++)
            aobj[k5] = Long.valueOf(getWifiStateTime(k5, l2, i) / 1000L);

        dumpLine(printwriter, 0, s, "wst", aobj);
        for(int i6 = 0; i6 < 8; i6++)
            aobj[i6] = Integer.valueOf(getWifiStateCount(i6, i));

        dumpLine(printwriter, 0, s, "wsc", aobj);
        aobj = new Object[13];
        for(int j6 = 0; j6 < 13; j6++)
            aobj[j6] = Long.valueOf(getWifiSupplStateTime(j6, l2, i) / 1000L);

        dumpLine(printwriter, 0, s, "wsst", aobj);
        for(int k6 = 0; k6 < 13; k6++)
            aobj[k6] = Integer.valueOf(getWifiSupplStateCount(k6, i));

        dumpLine(printwriter, 0, s, "wssc", aobj);
        aobj = new Object[5];
        for(int i7 = 0; i7 < 5; i7++)
            aobj[i7] = Long.valueOf(getWifiSignalStrengthTime(i7, l2, i) / 1000L);

        dumpLine(printwriter, 0, s, "wsgt", aobj);
        for(int j7 = 0; j7 < 5; j7++)
            aobj[j7] = Integer.valueOf(getWifiSignalStrengthCount(j7, i));

        dumpLine(printwriter, 0, s, "wsgc", aobj);
        if(i == 2)
            dumpLine(printwriter, 0, s, "lv", new Object[] {
                Integer.valueOf(getDischargeStartLevel()), Integer.valueOf(getDischargeCurrentLevel())
            });
        if(i == 2)
            dumpLine(printwriter, 0, s, "dc", new Object[] {
                Integer.valueOf(getDischargeStartLevel() - getDischargeCurrentLevel()), Integer.valueOf(getDischargeStartLevel() - getDischargeCurrentLevel()), Integer.valueOf(getDischargeAmountScreenOn()), Integer.valueOf(getDischargeAmountScreenOff()), Long.valueOf(l52 / 1000L), Long.valueOf(l54 / 1000L), Integer.valueOf(getDischargeAmountScreenDoze()), Long.valueOf(l56 / 1000L)
            });
        else
            dumpLine(printwriter, 0, s, "dc", new Object[] {
                Integer.valueOf(getLowDischargeAmountSinceCharge()), Integer.valueOf(getHighDischargeAmountSinceCharge()), Integer.valueOf(getDischargeAmountScreenOnSinceCharge()), Integer.valueOf(getDischargeAmountScreenOffSinceCharge()), Long.valueOf(l52 / 1000L), Long.valueOf(l54 / 1000L), Integer.valueOf(getDischargeAmountScreenDozeSinceCharge()), Long.valueOf(l56 / 1000L)
            });
        if(j < 0)
        {
            aobj = getKernelWakelockStats();
            if(((Map) (aobj)).size() > 0)
            {
                java.util.Map.Entry entry;
                for(aobj = ((Map) (aobj)).entrySet().iterator(); ((Iterator) (aobj)).hasNext(); dumpLine(printwriter, 0, s, "kwl", new Object[] {
    (new StringBuilder()).append("\"").append((String)entry.getKey()).append("\"").toString(), stringbuilder.toString()
}))
                {
                    entry = (java.util.Map.Entry)((Iterator) (aobj)).next();
                    stringbuilder.setLength(0);
                    printWakeLockCheckin(stringbuilder, (Timer)entry.getValue(), l2, null, i, "");
                }

            }
            aobj = getWakeupReasonStats();
            if(((Map) (aobj)).size() > 0)
            {
                long l18;
                int k7;
                for(Iterator iterator1 = ((Map) (aobj)).entrySet().iterator(); iterator1.hasNext(); dumpLine(printwriter, 0, s, "wr", new Object[] {
    (new StringBuilder()).append("\"").append((String)((java.util.Map.Entry) (aobj)).getKey()).append("\"").toString(), Long.valueOf((500L + l18) / 1000L), Integer.valueOf(k7)
}))
                {
                    aobj = (java.util.Map.Entry)iterator1.next();
                    l18 = ((Timer)((java.util.Map.Entry) (aobj)).getValue()).getTotalTimeLocked(l2, i);
                    k7 = ((Timer)((java.util.Map.Entry) (aobj)).getValue()).getCountLocked(i);
                }

            }
        }
        Object obj3 = getRpmStats();
        aobj = getScreenOffRpmStats();
        if(((Map) (obj3)).size() > 0)
        {
            long l6;
            int i8;
            for(Iterator iterator = ((Map) (obj3)).entrySet().iterator(); iterator.hasNext(); dumpLine(printwriter, 0, s, "rpm", new Object[] {
    (new StringBuilder()).append("\"").append((String)((java.util.Map.Entry) (obj3)).getKey()).append("\"").toString(), Long.valueOf(l6), Integer.valueOf(i8)
}))
            {
                obj3 = (java.util.Map.Entry)iterator.next();
                stringbuilder.setLength(0);
                Timer timer4 = (Timer)((java.util.Map.Entry) (obj3)).getValue();
                l6 = (timer4.getTotalTimeLocked(l2, i) + 500L) / 1000L;
                i8 = timer4.getCountLocked(i);
                timer4 = (Timer)((Map) (aobj)).get(((java.util.Map.Entry) (obj3)).getKey());
                long l19;
                if(timer4 != null)
                    l19 = (timer4.getTotalTimeLocked(l2, i) + 500L) / 1000L;
                if(timer4 != null)
                    timer4.getCountLocked(i);
            }

        }
        context = new BatteryStatsHelper(context, false, flag);
        context.create(this);
        context.refreshStats(i, -1);
        aobj = context.getUsageList();
        if(aobj == null || ((List) (aobj)).size() <= 0)
            break MISSING_BLOCK_LABEL_2880;
        dumpLine(printwriter, 0, s, "pws", new Object[] {
            BatteryStatsHelper.makemAh(context.getPowerProfile().getBatteryCapacity()), BatteryStatsHelper.makemAh(context.getComputedPower()), BatteryStatsHelper.makemAh(context.getMinDrainedPower()), BatteryStatsHelper.makemAh(context.getMaxDrainedPower())
        });
        i9 = 0;
_L14:
        int j8;
        BatterySipper batterysipper;
        if(i9 >= ((List) (aobj)).size())
            break MISSING_BLOCK_LABEL_2880;
        batterysipper = (BatterySipper)((List) (aobj)).get(i9);
        j8 = 0;
        _2D_getcom_2D_android_2D_internal_2D_os_2D_BatterySipper$DrainTypeSwitchesValues()[batterysipper.drainType.ordinal()];
        JVM INSTR tableswitch 1 12: default 2684
    //                   1 2818
    //                   2 2797
    //                   3 2867
    //                   4 2776
    //                   5 2811
    //                   6 2769
    //                   7 2860
    //                   8 2783
    //                   9 2804
    //                   10 2853
    //                   11 2835
    //                   12 2790;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
        context = "???";
_L15:
        String s1 = BatteryStatsHelper.makemAh(batterysipper.totalPowerMah);
        int i1;
        if(batterysipper.shouldHide)
            i1 = 1;
        else
            i1 = 0;
        dumpLine(printwriter, j8, s, "pwi", new Object[] {
            context, s1, Integer.valueOf(i1), BatteryStatsHelper.makemAh(batterysipper.screenPowerMah), BatteryStatsHelper.makemAh(batterysipper.proportionalSmearMah)
        });
        i9++;
        if(true) goto _L14; else goto _L7
_L7:
        context = "idle";
          goto _L15
_L5:
        context = "cell";
          goto _L15
_L9:
        context = "phone";
          goto _L15
_L13:
        context = "wifi";
          goto _L15
_L3:
        context = "blue";
          goto _L15
_L10:
        context = "scrn";
          goto _L15
_L6:
        context = "flashlight";
          goto _L15
_L2:
        j8 = batterysipper.uidObj.getUid();
        context = "uid";
          goto _L15
_L12:
        j8 = UserHandle.getUid(batterysipper.userId, 0);
        context = "user";
          goto _L15
_L11:
        context = "unacc";
          goto _L15
_L8:
        context = "over";
          goto _L15
_L4:
        context = "camera";
          goto _L15
        long al1[];
        al1 = getCpuFreqs();
        if(al1 != null)
        {
            stringbuilder.setLength(0);
            j8 = 0;
            while(j8 < al1.length) 
            {
                if(j8 == 0)
                    context = "";
                else
                    context = ",";
                stringbuilder.append(context).append(al1[j8]);
                j8++;
            }
            dumpLine(printwriter, 0, s, "gcf", new Object[] {
                stringbuilder.toString()
            });
        }
        j8 = 0;
_L17:
        int i10;
        if(j8 >= j3)
            break MISSING_BLOCK_LABEL_6344;
        i10 = sparsearray.keyAt(j8);
        if(j < 0 || i10 == j)
            break; /* Loop/switch isn't completed */
_L18:
        j8++;
        if(true) goto _L17; else goto _L16
_L16:
        long l1;
        long l7;
        long l16;
        long l20;
        long l29;
        long l31;
        long l35;
        long l37;
        long l39;
        long l41;
        long l43;
        long l45;
        long l47;
        long l49;
        long l51;
        long l53;
        long l55;
        long l57;
        Uid uid;
        long l58;
        long l59;
        long l60;
        uid = (Uid)sparsearray.valueAt(j8);
        l35 = uid.getNetworkActivityBytes(0, i);
        l1 = uid.getNetworkActivityBytes(1, i);
        l55 = uid.getNetworkActivityBytes(2, i);
        l58 = uid.getNetworkActivityBytes(3, i);
        l43 = uid.getNetworkActivityPackets(0, i);
        l45 = uid.getNetworkActivityPackets(1, i);
        l39 = uid.getMobileRadioActiveTime(i);
        i9 = uid.getMobileRadioActiveCount(i);
        l59 = uid.getMobileRadioApWakeupCount(i);
        l57 = uid.getNetworkActivityPackets(2, i);
        l49 = uid.getNetworkActivityPackets(3, i);
        l20 = uid.getWifiRadioApWakeupCount(i);
        l16 = uid.getNetworkActivityBytes(4, i);
        l41 = uid.getNetworkActivityBytes(5, i);
        l60 = uid.getNetworkActivityBytes(6, i);
        l29 = uid.getNetworkActivityBytes(7, i);
        l53 = uid.getNetworkActivityBytes(8, i);
        l7 = uid.getNetworkActivityBytes(9, i);
        l37 = uid.getNetworkActivityPackets(6, i);
        l31 = uid.getNetworkActivityPackets(7, i);
        l51 = uid.getNetworkActivityPackets(8, i);
        l47 = uid.getNetworkActivityPackets(9, i);
        break MISSING_BLOCK_LABEL_3217;
        int j1;
        Timer timer;
        int j10;
        if(l35 > 0L || l1 > 0L || l55 > 0L || l58 > 0L || l43 > 0L || l45 > 0L || l57 > 0L || l49 > 0L || l39 > 0L || i9 > 0 || l16 > 0L || l41 > 0L || l59 > 0L || l20 > 0L || l60 > 0L || l29 > 0L || l53 > 0L || l7 > 0L || l37 > 0L || l31 > 0L || l51 > 0L || l47 > 0L)
            dumpLine(printwriter, i10, s, "nt", new Object[] {
                Long.valueOf(l35), Long.valueOf(l1), Long.valueOf(l55), Long.valueOf(l58), Long.valueOf(l43), Long.valueOf(l45), Long.valueOf(l57), Long.valueOf(l49), Long.valueOf(l39), Integer.valueOf(i9), 
                Long.valueOf(l16), Long.valueOf(l41), Long.valueOf(l59), Long.valueOf(l20), Long.valueOf(l60), Long.valueOf(l29), Long.valueOf(l53), Long.valueOf(l7), Long.valueOf(l37), Long.valueOf(l31), 
                Long.valueOf(l51), Long.valueOf(l47)
            });
        dumpControllerActivityLine(printwriter, i10, s, "mcd", uid.getModemControllerActivity(), i);
        l29 = uid.getFullWifiLockTime(l2, i);
        l7 = uid.getWifiScanTime(l2, i);
        j1 = uid.getWifiScanCount(i);
        i9 = uid.getWifiScanBackgroundCount(i);
        l1 = (uid.getWifiScanActualTime(l2) + 500L) / 1000L;
        l20 = (uid.getWifiScanBackgroundTime(l2) + 500L) / 1000L;
        l31 = uid.getWifiRunningTime(l2, i);
        break MISSING_BLOCK_LABEL_3536;
        if(l29 != 0L || l7 != 0L || j1 != 0 || i9 != 0 || l1 != 0L || l20 != 0L || l31 != 0L)
            dumpLine(printwriter, i10, s, "wfl", new Object[] {
                Long.valueOf(l29), Long.valueOf(l7), Long.valueOf(l31), Integer.valueOf(j1), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(i9), Long.valueOf(l1), Long.valueOf(l20)
            });
        dumpControllerActivityLine(printwriter, i10, s, "wfcd", uid.getWifiControllerActivity(), i);
        context = uid.getBluetoothScanTimer();
        if(context != null)
        {
            l47 = (context.getTotalTimeLocked(l2, i) + 500L) / 1000L;
            if(l47 != 0L)
            {
                j10 = context.getCountLocked(i);
                timer = uid.getBluetoothScanBackgroundTimer();
                int i12;
                if(timer != null)
                    i9 = timer.getCountLocked(i);
                else
                    i9 = 0;
                l57 = context.getTotalDurationMsLocked(l3);
                if(timer != null)
                    l20 = timer.getTotalDurationMsLocked(l3);
                else
                    l20 = 0L;
                if(uid.getBluetoothScanResultCounter() != null)
                    j1 = uid.getBluetoothScanResultCounter().getCountLocked(i);
                else
                    j1 = 0;
                if(uid.getBluetoothScanResultBgCounter() != null)
                    i12 = uid.getBluetoothScanResultBgCounter().getCountLocked(i);
                else
                    i12 = 0;
                context = uid.getBluetoothUnoptimizedScanTimer();
                if(context != null)
                    l7 = context.getTotalDurationMsLocked(l3);
                else
                    l7 = 0L;
                if(context != null)
                    l31 = context.getMaxDurationMsLocked(l3);
                else
                    l31 = 0L;
                context = uid.getBluetoothUnoptimizedScanBackgroundTimer();
                if(context != null)
                    l29 = context.getTotalDurationMsLocked(l3);
                else
                    l29 = 0L;
                if(context != null)
                    l1 = context.getMaxDurationMsLocked(l3);
                else
                    l1 = 0L;
                dumpLine(printwriter, i10, s, "blem", new Object[] {
                    Long.valueOf(l47), Integer.valueOf(j10), Integer.valueOf(i9), Long.valueOf(l57), Long.valueOf(l20), Integer.valueOf(j1), Integer.valueOf(i12), Long.valueOf(l7), Long.valueOf(l29), Long.valueOf(l31), 
                    Long.valueOf(l1)
                });
            }
        }
        dumpControllerActivityLine(printwriter, i10, s, "ble", uid.getBluetoothControllerActivity(), i);
        if(!uid.hasUserActivity())
            break MISSING_BLOCK_LABEL_4267;
        context = ((Context) (new Object[4]));
        j1 = 0;
        for(i9 = 0; i9 < 4; i9++)
        {
            i12 = uid.getUserActivityCount(i9, i);
            context[i9] = Integer.valueOf(i12);
            if(i12 != 0)
                j1 = 1;
        }

        break MISSING_BLOCK_LABEL_4250;
        if(j1 != 0)
            dumpLine(printwriter, i10, s, "ua", context);
        if(uid.getAggregatedPartialWakelockTimer() != null)
        {
            context = uid.getAggregatedPartialWakelockTimer();
            long l8 = context.getTotalDurationMsLocked(l3);
            context = context.getSubTimer();
            long l21;
            Object obj1;
            int j9;
            ArrayMap arraymap1;
            String s2;
            if(context != null)
                l21 = context.getTotalDurationMsLocked(l3);
            else
                l21 = 0L;
            dumpLine(printwriter, i10, s, "awl", new Object[] {
                Long.valueOf(l8), Long.valueOf(l21)
            });
        }
        arraymap1 = uid.getWakelockStats();
        j9 = arraymap1.size() - 1;
        while(j9 >= 0) 
        {
            obj1 = (Uid.Wakelock)arraymap1.valueAt(j9);
            stringbuilder.setLength(0);
            s2 = printWakeLockCheckin(stringbuilder, ((Uid.Wakelock) (obj1)).getWakeTime(1), l2, "f", i, "");
            context = ((Uid.Wakelock) (obj1)).getWakeTime(0);
            s2 = printWakeLockCheckin(stringbuilder, context, l2, "p", i, s2);
            if(context != null)
                context = context.getSubTimer();
            else
                context = null;
            context = printWakeLockCheckin(stringbuilder, context, l2, "bp", i, s2);
            printWakeLockCheckin(stringbuilder, ((Uid.Wakelock) (obj1)).getWakeTime(2), l2, "w", i, context);
            if(stringbuilder.length() <= 0)
                continue;
            obj1 = (String)arraymap1.keyAt(j9);
            context = ((Context) (obj1));
            if(((String) (obj1)).indexOf(',') >= 0)
                context = ((String) (obj1)).replace(',', '_');
            obj1 = context;
            if(context.indexOf('\n') >= 0)
                obj1 = context.replace('\n', '_');
            context = ((Context) (obj1));
            if(((String) (obj1)).indexOf('\r') >= 0)
                context = ((String) (obj1)).replace('\r', '_');
            dumpLine(printwriter, i10, s, "wl", new Object[] {
                context, stringbuilder.toString()
            });
            j9--;
        }
        context = uid.getSyncStats();
        int k9 = context.size() - 1;
        while(k9 >= 0) 
        {
            Timer timer1 = (Timer)context.valueAt(k9);
            long l9 = (timer1.getTotalTimeLocked(l2, i) + 500L) / 1000L;
            int j12 = timer1.getCountLocked(i);
            timer1 = timer1.getSubTimer();
            long l22;
            int k1;
            if(timer1 != null)
                l22 = timer1.getTotalDurationMsLocked(l3);
            else
                l22 = -1L;
            if(timer1 != null)
                k1 = timer1.getCountLocked(i);
            else
                k1 = -1;
            if(l9 != 0L)
                dumpLine(printwriter, i10, s, "sy", new Object[] {
                    (new StringBuilder()).append("\"").append((String)context.keyAt(k9)).append("\"").toString(), Long.valueOf(l9), Integer.valueOf(j12), Long.valueOf(l22), Integer.valueOf(k1)
                });
            k9--;
        }
        context = uid.getJobStats();
        k9 = context.size() - 1;
        while(k9 >= 0) 
        {
            Timer timer2 = (Timer)context.valueAt(k9);
            long l10 = (timer2.getTotalTimeLocked(l2, i) + 500L) / 1000L;
            int k12 = timer2.getCountLocked(i);
            timer2 = timer2.getSubTimer();
            long l23;
            int i2;
            if(timer2 != null)
                l23 = timer2.getTotalDurationMsLocked(l3);
            else
                l23 = -1L;
            if(timer2 != null)
                i2 = timer2.getCountLocked(i);
            else
                i2 = -1;
            if(l10 != 0L)
                dumpLine(printwriter, i10, s, "jb", new Object[] {
                    (new StringBuilder()).append("\"").append((String)context.keyAt(k9)).append("\"").toString(), Long.valueOf(l10), Integer.valueOf(k12), Long.valueOf(l23), Integer.valueOf(i2)
                });
            k9--;
        }
        Object obj2 = uid.getJobCompletionStats();
        for(k9 = ((ArrayMap) (obj2)).size() - 1; k9 >= 0; k9--)
        {
            context = (SparseIntArray)((ArrayMap) (obj2)).valueAt(k9);
            if(context != null)
                dumpLine(printwriter, i10, s, "jbc", new Object[] {
                    (new StringBuilder()).append("\"").append((String)((ArrayMap) (obj2)).keyAt(k9)).append("\"").toString(), Integer.valueOf(context.get(0, 0)), Integer.valueOf(context.get(1, 0)), Integer.valueOf(context.get(2, 0)), Integer.valueOf(context.get(3, 0)), Integer.valueOf(context.get(4, 0))
                });
        }

        dumpTimer(printwriter, i10, s, "fla", uid.getFlashlightTurnedOnTimer(), l2, i);
        dumpTimer(printwriter, i10, s, "cam", uid.getCameraTurnedOnTimer(), l2, i);
        dumpTimer(printwriter, i10, s, "vid", uid.getVideoTurnedOnTimer(), l2, i);
        dumpTimer(printwriter, i10, s, "aud", uid.getAudioTurnedOnTimer(), l2, i);
        obj2 = uid.getSensorStats();
        int i13 = ((SparseArray) (obj2)).size();
        k9 = 0;
        while(k9 < i13) 
        {
            Object obj4 = (Uid.Sensor)((SparseArray) (obj2)).valueAt(k9);
            int k10 = ((SparseArray) (obj2)).keyAt(k9);
            context = ((Uid.Sensor) (obj4)).getSensorTime();
            if(context == null)
                continue;
            long l11 = (context.getTotalTimeLocked(l2, i) + 500L) / 1000L;
            if(l11 != 0L)
            {
                int j14 = context.getCountLocked(i);
                obj4 = ((Uid.Sensor) (obj4)).getSensorBackgroundTime();
                long l24;
                long l32;
                int j2;
                if(obj4 != null)
                    j2 = ((Timer) (obj4)).getCountLocked(i);
                else
                    j2 = 0;
                l32 = context.getTotalDurationMsLocked(l3);
                if(obj4 != null)
                    l24 = ((Timer) (obj4)).getTotalDurationMsLocked(l3);
                else
                    l24 = 0L;
                dumpLine(printwriter, i10, s, "sr", new Object[] {
                    Integer.valueOf(k10), Long.valueOf(l11), Integer.valueOf(j14), Integer.valueOf(j2), Long.valueOf(l32), Long.valueOf(l24)
                });
            }
            k9++;
        }
        dumpTimer(printwriter, i10, s, "vib", uid.getVibratorOnTimer(), l2, i);
        dumpTimer(printwriter, i10, s, "fg", uid.getForegroundActivityTimer(), l2, i);
        dumpTimer(printwriter, i10, s, "fgs", uid.getForegroundServiceTimer(), l2, i);
        context = ((Context) (new Object[6]));
        long l25 = 0L;
        for(k9 = 0; k9 < 6; k9++)
        {
            long l12 = uid.getProcessStateTime(k9, l2, i);
            l25 += l12;
            context[k9] = Long.valueOf((500L + l12) / 1000L);
        }

        if(l25 > 0L)
            dumpLine(printwriter, i10, s, "st", context);
        l25 = uid.getUserCpuTimeUs(i);
        long l13 = uid.getSystemCpuTimeUs(i);
        if(l25 > 0L || l13 > 0L)
            dumpLine(printwriter, i10, s, "cpu", new Object[] {
                Long.valueOf(l25 / 1000L), Long.valueOf(l13 / 1000L), Integer.valueOf(0)
            });
        if(al1 != null)
        {
            long al[] = uid.getCpuFreqTimes(i);
            if(al != null && al.length == al1.length)
            {
                stringbuilder.setLength(0);
                k9 = 0;
                while(k9 < al.length) 
                {
                    if(k9 == 0)
                        context = "";
                    else
                        context = ",";
                    stringbuilder.append(context).append(al[k9]);
                    k9++;
                }
                context = uid.getScreenOffCpuFreqTimes(i);
                if(context != null)
                    for(k9 = 0; k9 < context.length; k9++)
                        stringbuilder.append(",").append(context[k9]);

                else
                    for(k9 = 0; k9 < al.length; k9++)
                        stringbuilder.append(",0");

                dumpLine(printwriter, i10, s, "ctf", new Object[] {
                    "A", Integer.valueOf(al.length), stringbuilder.toString()
                });
            }
        }
        context = uid.getProcessStats();
        k9 = context.size() - 1;
        do
        {
            if(k9 < 0)
                break;
            Uid.Proc proc = (Uid.Proc)context.valueAt(k9);
            long l26 = proc.getUserTime(i);
            long l14 = proc.getSystemTime(i);
            long l33 = proc.getForegroundTime(i);
            int j13 = proc.getStarts(i);
            int i11 = proc.getNumCrashes(i);
            int k2 = proc.getNumAnrs(i);
            if(l26 != 0L || l14 != 0L || l33 != 0L || j13 != 0 || k2 != 0 || i11 != 0)
                dumpLine(printwriter, i10, s, "pr", new Object[] {
                    (new StringBuilder()).append("\"").append((String)context.keyAt(k9)).append("\"").toString(), Long.valueOf(l26), Long.valueOf(l14), Long.valueOf(l33), Integer.valueOf(j13), Integer.valueOf(k2), Integer.valueOf(i11)
                });
            k9--;
        } while(true);
        context = uid.getPackageStats();
        k9 = context.size() - 1;
        while(k9 >= 0) 
        {
            Uid.Pkg pkg = (Uid.Pkg)context.valueAt(k9);
            int i3 = 0;
            ArrayMap arraymap = pkg.getWakeupAlarmStats();
            for(int k13 = arraymap.size() - 1; k13 >= 0; k13--)
            {
                int j11 = ((Counter)arraymap.valueAt(k13)).getCountLocked(i);
                i3 += j11;
                dumpLine(printwriter, i10, s, "wua", new Object[] {
                    ((String)arraymap.keyAt(k13)).replace(',', '_'), Integer.valueOf(j11)
                });
            }

            arraymap = pkg.getServiceStats();
            int i14 = arraymap.size() - 1;
            do
            {
                if(i14 < 0)
                    break;
                Uid.Pkg.Serv serv = (Uid.Pkg.Serv)arraymap.valueAt(i14);
                long l27 = serv.getStartTime(l4, i);
                int k14 = serv.getStarts(i);
                int k11 = serv.getLaunches(i);
                if(l27 != 0L || k14 != 0 || k11 != 0)
                    dumpLine(printwriter, i10, s, "apk", new Object[] {
                        Integer.valueOf(i3), context.keyAt(k9), arraymap.keyAt(i14), Long.valueOf(l27 / 1000L), Integer.valueOf(k14), Integer.valueOf(k11)
                    });
                i14--;
            } while(true);
            k9--;
        }
          goto _L18
          goto _L18
    }

    public void dumpCheckinLocked(Context context, PrintWriter printwriter, List list, int i, long l)
    {
        boolean flag;
        prepareForDumpLocked();
        dumpLine(printwriter, 0, "i", "vers", new Object[] {
            "27", Integer.valueOf(getParcelVersion()), getStartPlatformVersion(), getEndPlatformVersion()
        });
        getHistoryBaseTime();
        SystemClock.elapsedRealtime();
        int j;
        if((i & 0xe) != 0)
            flag = true;
        else
            flag = false;
        if((i & 0x10) == 0 && (i & 8) == 0 || !startIteratingHistoryLocked())
            break MISSING_BLOCK_LABEL_222;
        j = 0;
        if(j >= getHistoryStringPoolSize())
            break; /* Loop/switch isn't completed */
        printwriter.print(9);
        printwriter.print(',');
        printwriter.print("hsp");
        printwriter.print(',');
        printwriter.print(j);
        printwriter.print(",");
        printwriter.print(getHistoryTagPoolUid(j));
        printwriter.print(",\"");
        printwriter.print(getHistoryTagPoolString(j).replace("\\", "\\\\").replace("\"", "\\\""));
        printwriter.print("\"");
        printwriter.println();
        j++;
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_94;
_L1:
        dumpHistoryLocked(printwriter, i, l, true);
        finishIteratingHistoryLocked();
        if(flag && (i & 6) == 0)
            return;
        break MISSING_BLOCK_LABEL_243;
        context;
        finishIteratingHistoryLocked();
        throw context;
        if(list != null)
        {
            SparseArray sparsearray = new SparseArray();
            for(int k = 0; k < list.size(); k++)
            {
                ApplicationInfo applicationinfo = (ApplicationInfo)list.get(k);
                Pair pair = (Pair)sparsearray.get(UserHandle.getAppId(applicationinfo.uid));
                Pair pair1 = pair;
                if(pair == null)
                {
                    pair1 = new Pair(new ArrayList(), new MutableBoolean(false));
                    sparsearray.put(UserHandle.getAppId(applicationinfo.uid), pair1);
                }
                ((ArrayList)pair1.first).add(applicationinfo.packageName);
            }

            list = getUidStats();
            int j1 = list.size();
            String as[] = new String[2];
            for(int i1 = 0; i1 < j1; i1++)
            {
                int k1 = UserHandle.getAppId(list.keyAt(i1));
                Pair pair2 = (Pair)sparsearray.get(k1);
                if(pair2 == null || !(((MutableBoolean)pair2.second).value ^ true))
                    continue;
                ((MutableBoolean)pair2.second).value = true;
                for(int l1 = 0; l1 < ((ArrayList)pair2.first).size(); l1++)
                {
                    as[0] = Integer.toString(k1);
                    as[1] = (String)((ArrayList)pair2.first).get(l1);
                    dumpLine(printwriter, 0, "i", "uid", as);
                }

            }

        }
        if(!flag || (i & 2) != 0)
        {
            dumpDurationSteps(printwriter, "", "dsd", getDischargeLevelStepTracker(), true);
            list = new String[1];
            l = computeBatteryTimeRemaining(SystemClock.elapsedRealtime() * 1000L);
            if(l >= 0L)
            {
                list[0] = Long.toString(l);
                dumpLine(printwriter, 0, "i", "dtr", list);
            }
            dumpDurationSteps(printwriter, "", "csd", getChargeLevelStepTracker(), true);
            l = computeChargeTimeRemaining(SystemClock.elapsedRealtime() * 1000L);
            if(l >= 0L)
            {
                list[0] = Long.toString(l);
                dumpLine(printwriter, 0, "i", "ctr", list);
            }
            boolean flag1;
            if((i & 0x40) != 0)
                flag1 = true;
            else
                flag1 = false;
            dumpCheckinLocked(context, printwriter, 0, -1, flag1);
        }
        return;
    }

    public void dumpLocked(Context context, PrintWriter printwriter, int i, int j, long l)
    {
        boolean flag;
        boolean flag1;
        prepareForDumpLocked();
        long l1;
        long l3;
        HistoryItem historyitem;
        HistoryPrinter historyprinter;
        if((i & 0xe) != 0)
            flag = true;
        else
            flag = false;
        if((i & 8) == 0 && !(flag ^ true))
            break MISSING_BLOCK_LABEL_283;
        l1 = getHistoryTotalSize();
        l3 = getHistoryUsedSize();
        if(!startIteratingHistoryLocked())
            break MISSING_BLOCK_LABEL_153;
        printwriter.print("Battery History (");
        printwriter.print((100L * l3) / l1);
        printwriter.print("% used, ");
        printSizeValue(printwriter, l3);
        printwriter.print(" used of ");
        printSizeValue(printwriter, l1);
        printwriter.print(", ");
        printwriter.print(getHistoryStringPoolSize());
        printwriter.print(" strings using ");
        printSizeValue(printwriter, getHistoryStringPoolBytes());
        printwriter.println("):");
        dumpHistoryLocked(printwriter, i, l, false);
        printwriter.println();
        finishIteratingHistoryLocked();
        if(!startIteratingOldHistoryLocked())
            break MISSING_BLOCK_LABEL_283;
        historyitem = JVM INSTR new #25  <Class BatteryStats$HistoryItem>;
        historyitem.HistoryItem();
        printwriter.println("Old battery History:");
        historyprinter = JVM INSTR new #28  <Class BatteryStats$HistoryPrinter>;
        historyprinter.HistoryPrinter();
        l1 = -1L;
_L1:
        if(!getNextOldHistoryLocked(historyitem))
            break MISSING_BLOCK_LABEL_275;
        l = l1;
        if(l1 >= 0L)
            break MISSING_BLOCK_LABEL_219;
        l = historyitem.time;
        if((i & 0x20) != 0)
            flag1 = true;
        else
            flag1 = false;
        historyprinter.printNextItem(printwriter, historyitem, l, false, flag1);
        l1 = l;
          goto _L1
        context;
        finishIteratingOldHistoryLocked();
        throw context;
        context;
        finishIteratingHistoryLocked();
        throw context;
        printwriter.println();
        finishIteratingOldHistoryLocked();
        StringBuilder stringbuilder;
        int ai[];
        Object obj;
        LevelStepTracker levelsteptracker1;
        ArrayList arraylist;
        if(flag && (i & 6) == 0)
            return;
        if(!flag)
        {
            SparseArray sparsearray1 = getUidStats();
            int k = sparsearray1.size();
            boolean flag3 = false;
            long l4 = SystemClock.elapsedRealtime();
            for(int k1 = 0; k1 < k;)
            {
                SparseArray sparsearray = ((Uid)sparsearray1.valueAt(k1)).getPidStats();
                boolean flag4 = flag3;
                if(sparsearray != null)
                {
                    int i2 = 0;
                    do
                    {
                        flag4 = flag3;
                        if(i2 >= sparsearray.size())
                            break;
                        Uid.Pid pid = (Uid.Pid)sparsearray.valueAt(i2);
                        flag4 = flag3;
                        if(!flag3)
                        {
                            printwriter.println("Per-PID Stats:");
                            flag4 = true;
                        }
                        long l2 = pid.mWakeSumMs;
                        if(pid.mWakeNesting > 0)
                            l = l4 - pid.mWakeStartMs;
                        else
                            l = 0L;
                        printwriter.print("  PID ");
                        printwriter.print(sparsearray.keyAt(i2));
                        printwriter.print(" wake time: ");
                        TimeUtils.formatDuration(l2 + l, printwriter);
                        printwriter.println("");
                        i2++;
                        flag3 = flag4;
                    } while(true);
                }
                k1++;
                flag3 = flag4;
            }

            if(flag3)
                printwriter.println();
        }
        if(!flag || (i & 2) != 0)
        {
            if(dumpDurationSteps(printwriter, "  ", "Discharge step durations:", getDischargeLevelStepTracker(), false))
            {
                l = computeBatteryTimeRemaining(SystemClock.elapsedRealtime() * 1000L);
                if(l >= 0L)
                {
                    printwriter.print("  Estimated discharge time remaining: ");
                    TimeUtils.formatDuration(l / 1000L, printwriter);
                    printwriter.println();
                }
                LevelStepTracker levelsteptracker = getDischargeLevelStepTracker();
                for(int i1 = 0; i1 < STEP_LEVEL_MODES_OF_INTEREST.length; i1++)
                    dumpTimeEstimate(printwriter, "  Estimated ", STEP_LEVEL_MODE_LABELS[i1], " time: ", levelsteptracker.computeTimeEstimate(STEP_LEVEL_MODES_OF_INTEREST[i1], STEP_LEVEL_MODE_VALUES[i1], null));

                printwriter.println();
            }
            if(dumpDurationSteps(printwriter, "  ", "Charge step durations:", getChargeLevelStepTracker(), false))
            {
                l = computeChargeTimeRemaining(SystemClock.elapsedRealtime() * 1000L);
                if(l >= 0L)
                {
                    printwriter.print("  Estimated charge time remaining: ");
                    TimeUtils.formatDuration(l / 1000L, printwriter);
                    printwriter.println();
                }
                printwriter.println();
            }
        }
        if(flag && (i & 6) == 0)
            break MISSING_BLOCK_LABEL_1235;
        printwriter.println("Daily stats:");
        printwriter.print("  Current start time: ");
        printwriter.println(DateFormat.format("yyyy-MM-dd-HH-mm-ss", getCurrentDailyStartTime()).toString());
        printwriter.print("  Next min deadline: ");
        printwriter.println(DateFormat.format("yyyy-MM-dd-HH-mm-ss", getNextMinDailyDeadline()).toString());
        printwriter.print("  Next max deadline: ");
        printwriter.println(DateFormat.format("yyyy-MM-dd-HH-mm-ss", getNextMaxDailyDeadline()).toString());
        stringbuilder = new StringBuilder(64);
        ai = new int[1];
        levelsteptracker1 = getDailyDischargeLevelStepTracker();
        obj = getDailyChargeLevelStepTracker();
        arraylist = getDailyPackageChanges();
        break MISSING_BLOCK_LABEL_834;
        int j1;
        if(levelsteptracker1.mNumStepDurations > 0 || ((LevelStepTracker) (obj)).mNumStepDurations > 0 || arraylist != null)
            if((i & 4) != 0 || flag ^ true)
            {
                if(dumpDurationSteps(printwriter, "    ", "  Current daily discharge step durations:", levelsteptracker1, false))
                    dumpDailyLevelStepSummary(printwriter, "      ", "Discharge", levelsteptracker1, stringbuilder, ai);
                if(dumpDurationSteps(printwriter, "    ", "  Current daily charge step durations:", ((LevelStepTracker) (obj)), false))
                    dumpDailyLevelStepSummary(printwriter, "      ", "Charge", ((LevelStepTracker) (obj)), stringbuilder, ai);
                dumpDailyPackageChanges(printwriter, "    ", arraylist);
            } else
            {
                printwriter.println("  Current daily steps:");
                dumpDailyLevelStepSummary(printwriter, "    ", "Discharge", levelsteptracker1, stringbuilder, ai);
                dumpDailyLevelStepSummary(printwriter, "    ", "Charge", ((LevelStepTracker) (obj)), stringbuilder, ai);
            }
        j1 = 0;
        do
        {
            obj = getDailyItemLocked(j1);
            if(obj == null)
                break;
            j1++;
            if((i & 4) != 0)
                printwriter.println();
            printwriter.print("  Daily from ");
            printwriter.print(DateFormat.format("yyyy-MM-dd-HH-mm-ss", ((DailyItem) (obj)).mStartTime).toString());
            printwriter.print(" to ");
            printwriter.print(DateFormat.format("yyyy-MM-dd-HH-mm-ss", ((DailyItem) (obj)).mEndTime).toString());
            printwriter.println(":");
            if((i & 4) != 0 || flag ^ true)
            {
                if(dumpDurationSteps(printwriter, "      ", "    Discharge step durations:", ((DailyItem) (obj)).mDischargeSteps, false))
                    dumpDailyLevelStepSummary(printwriter, "        ", "Discharge", ((DailyItem) (obj)).mDischargeSteps, stringbuilder, ai);
                if(dumpDurationSteps(printwriter, "      ", "    Charge step durations:", ((DailyItem) (obj)).mChargeSteps, false))
                    dumpDailyLevelStepSummary(printwriter, "        ", "Charge", ((DailyItem) (obj)).mChargeSteps, stringbuilder, ai);
                dumpDailyPackageChanges(printwriter, "    ", ((DailyItem) (obj)).mPackageChanges);
            } else
            {
                dumpDailyLevelStepSummary(printwriter, "    ", "Discharge", ((DailyItem) (obj)).mDischargeSteps, stringbuilder, ai);
                dumpDailyLevelStepSummary(printwriter, "    ", "Charge", ((DailyItem) (obj)).mChargeSteps, stringbuilder, ai);
            }
        } while(true);
        break MISSING_BLOCK_LABEL_1231;
        printwriter.println();
        if(!flag || (i & 2) != 0)
        {
            printwriter.println("Statistics since last charge:");
            printwriter.println((new StringBuilder()).append("  System starts: ").append(getStartCount()).append(", currently on battery: ").append(getIsOnBattery()).toString());
            boolean flag2;
            if((i & 0x40) != 0)
                flag2 = true;
            else
                flag2 = false;
            dumpLocked(context, printwriter, "", 0, j, flag2);
            printwriter.println();
        }
        return;
    }

    public final void dumpLocked(Context context, PrintWriter printwriter, String s, int i, int j)
    {
        dumpLocked(context, printwriter, s, i, j, BatteryStatsHelper.checkWifiOnly(context));
    }

    public final void dumpLocked(Context context, PrintWriter printwriter, String s, int i, int j, boolean flag)
    {
        long l4;
        long l5;
        long l6;
        long l12;
        long l36;
        StringBuilder stringbuilder;
        SparseArray sparsearray;
        int k;
        Object obj;
        BatteryStatsHelper batterystatshelper;
        Object obj3;
        long l = SystemClock.uptimeMillis() * 1000L;
        l4 = SystemClock.elapsedRealtime() * 1000L;
        l5 = (500L + l4) / 1000L;
        l6 = getBatteryUptime(l);
        long l7 = computeBatteryUptime(l, i);
        l12 = computeBatteryRealtime(l4, i);
        long l13 = computeRealtime(l4, i);
        long l20 = computeUptime(l, i);
        long l29 = computeBatteryScreenOffUptime(l, i);
        long l31 = computeBatteryScreenOffRealtime(l4, i);
        long l34 = computeBatteryTimeRemaining(l4);
        l36 = computeChargeTimeRemaining(l4);
        l = getScreenDozeTime(l4, i);
        stringbuilder = new StringBuilder(128);
        sparsearray = getUidStats();
        k = sparsearray.size();
        int i1 = getEstimatedBatteryCapacity();
        if(i1 > 0)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Estimated battery capacity: ");
            stringbuilder.append(BatteryStatsHelper.makemAh(i1));
            stringbuilder.append(" mAh");
            printwriter.println(stringbuilder.toString());
        }
        i1 = getMinLearnedBatteryCapacity();
        if(i1 > 0)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Min learned battery capacity: ");
            stringbuilder.append(BatteryStatsHelper.makemAh(i1 / 1000));
            stringbuilder.append(" mAh");
            printwriter.println(stringbuilder.toString());
        }
        i1 = getMaxLearnedBatteryCapacity();
        if(i1 > 0)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Max learned battery capacity: ");
            stringbuilder.append(BatteryStatsHelper.makemAh(i1 / 1000));
            stringbuilder.append(" mAh");
            printwriter.println(stringbuilder.toString());
        }
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  Time on battery: ");
        formatTimeMs(stringbuilder, l12 / 1000L);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l12, l13));
        stringbuilder.append(") realtime, ");
        formatTimeMs(stringbuilder, l7 / 1000L);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l7, l12));
        stringbuilder.append(") uptime");
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  Time on battery screen off: ");
        formatTimeMs(stringbuilder, l31 / 1000L);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l31, l12));
        stringbuilder.append(") realtime, ");
        formatTimeMs(stringbuilder, l29 / 1000L);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l29, l12));
        stringbuilder.append(") uptime");
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  Time on battery screen doze: ");
        formatTimeMs(stringbuilder, l / 1000L);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l, l12));
        stringbuilder.append(")");
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  Total run time: ");
        formatTimeMs(stringbuilder, l13 / 1000L);
        stringbuilder.append("realtime, ");
        formatTimeMs(stringbuilder, l20 / 1000L);
        stringbuilder.append("uptime");
        printwriter.println(stringbuilder.toString());
        if(l34 >= 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Battery time remaining: ");
            formatTimeMs(stringbuilder, l34 / 1000L);
            printwriter.println(stringbuilder.toString());
        }
        if(l36 >= 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Charge time remaining: ");
            formatTimeMs(stringbuilder, l36 / 1000L);
            printwriter.println(stringbuilder.toString());
        }
        l20 = getMahDischarge(i);
        if(l20 >= 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Discharge: ");
            stringbuilder.append(BatteryStatsHelper.makemAh((double)l20 / 1000D));
            stringbuilder.append(" mAh");
            printwriter.println(stringbuilder.toString());
        }
        l7 = getMahDischargeScreenOff(i);
        if(l7 >= 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Screen off discharge: ");
            stringbuilder.append(BatteryStatsHelper.makemAh((double)l7 / 1000D));
            stringbuilder.append(" mAh");
            printwriter.println(stringbuilder.toString());
        }
        l13 = getMahDischargeScreenDoze(i);
        if(l13 >= 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Screen doze discharge: ");
            stringbuilder.append(BatteryStatsHelper.makemAh((double)l13 / 1000D));
            stringbuilder.append(" mAh");
            printwriter.println(stringbuilder.toString());
        }
        l20 = l20 - l7 - l13;
        if(l20 >= 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Screen on discharge: ");
            stringbuilder.append(BatteryStatsHelper.makemAh((double)l20 / 1000D));
            stringbuilder.append(" mAh");
            printwriter.println(stringbuilder.toString());
        }
        printwriter.print("  Start clock time: ");
        printwriter.println(DateFormat.format("yyyy-MM-dd-HH-mm-ss", getStartClockTime()).toString());
        l34 = getScreenOnTime(l4, i);
        l29 = getInteractiveTime(l4, i);
        l20 = getPowerSaveModeEnabledTime(l4, i);
        l36 = getDeviceIdleModeTime(1, l4, i);
        l = getDeviceIdleModeTime(2, l4, i);
        l31 = getDeviceIdlingTime(1, l4, i);
        l7 = getDeviceIdlingTime(2, l4, i);
        l13 = getPhoneOnTime(l4, i);
        getGlobalWifiRunningTime(l4, i);
        getWifiOnTime(l4, i);
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  Screen on: ");
        formatTimeMs(stringbuilder, l34 / 1000L);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l34, l12));
        stringbuilder.append(") ");
        stringbuilder.append(getScreenOnCount(i));
        stringbuilder.append("x, Interactive: ");
        formatTimeMs(stringbuilder, l29 / 1000L);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l29, l12));
        stringbuilder.append(")");
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  Screen brightnesses:");
        boolean flag1 = false;
        i1 = 0;
        while(i1 < 5) 
        {
            l29 = getScreenBrightnessTime(i1, l4, i);
            if(l29 != 0L)
            {
                stringbuilder.append("\n    ");
                stringbuilder.append(s);
                flag1 = true;
                stringbuilder.append(SCREEN_BRIGHTNESS_NAMES[i1]);
                stringbuilder.append(" ");
                formatTimeMs(stringbuilder, l29 / 1000L);
                stringbuilder.append("(");
                stringbuilder.append(formatRatioLocked(l29, l34));
                stringbuilder.append(")");
            }
            i1++;
        }
        if(!flag1)
            stringbuilder.append(" (no activity)");
        printwriter.println(stringbuilder.toString());
        if(l20 != 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Power save mode enabled: ");
            formatTimeMs(stringbuilder, l20 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l20, l12));
            stringbuilder.append(")");
            printwriter.println(stringbuilder.toString());
        }
        if(l31 != 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Device light idling: ");
            formatTimeMs(stringbuilder, l31 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l31, l12));
            stringbuilder.append(") ");
            stringbuilder.append(getDeviceIdlingCount(1, i));
            stringbuilder.append("x");
            printwriter.println(stringbuilder.toString());
        }
        if(l36 != 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Idle mode light time: ");
            formatTimeMs(stringbuilder, l36 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l36, l12));
            stringbuilder.append(") ");
            stringbuilder.append(getDeviceIdleModeCount(1, i));
            stringbuilder.append("x");
            stringbuilder.append(" -- longest ");
            formatTimeMs(stringbuilder, getLongestDeviceIdleModeTime(1));
            printwriter.println(stringbuilder.toString());
        }
        if(l7 != 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Device full idling: ");
            formatTimeMs(stringbuilder, l7 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l7, l12));
            stringbuilder.append(") ");
            stringbuilder.append(getDeviceIdlingCount(2, i));
            stringbuilder.append("x");
            printwriter.println(stringbuilder.toString());
        }
        if(l != 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Idle mode full time: ");
            formatTimeMs(stringbuilder, l / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l, l12));
            stringbuilder.append(") ");
            stringbuilder.append(getDeviceIdleModeCount(2, i));
            stringbuilder.append("x");
            stringbuilder.append(" -- longest ");
            formatTimeMs(stringbuilder, getLongestDeviceIdleModeTime(2));
            printwriter.println(stringbuilder.toString());
        }
        if(l13 != 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Active phone call: ");
            formatTimeMs(stringbuilder, l13 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l13, l12));
            stringbuilder.append(") ");
            stringbuilder.append(getPhoneOnCount(i));
            stringbuilder.append("x");
        }
        i1 = getNumConnectivityChange(i);
        if(i1 != 0)
        {
            printwriter.print(s);
            printwriter.print("  Connectivity changes: ");
            printwriter.println(i1);
        }
        l20 = 0L;
        l13 = 0L;
        obj = new ArrayList();
        for(i1 = 0; i1 < k;)
        {
            Uid uid = (Uid)sparsearray.valueAt(i1);
            ArrayMap arraymap1 = uid.getWakelockStats();
            int i4 = arraymap1.size() - 1;
            l = l20;
            while(i4 >= 0) 
            {
                Uid.Wakelock wakelock1 = (Uid.Wakelock)arraymap1.valueAt(i4);
                Timer timer4 = wakelock1.getWakeTime(1);
                l20 = l;
                if(timer4 != null)
                    l20 = l + timer4.getTotalTimeLocked(l4, i);
                timer4 = wakelock1.getWakeTime(0);
                l7 = l13;
                if(timer4 != null)
                {
                    l = timer4.getTotalTimeLocked(l4, i);
                    l7 = l13;
                    if(l > 0L)
                    {
                        if(j < 0)
                            ((ArrayList) (obj)).add(new TimerEntry((String)arraymap1.keyAt(i4), uid.getUid(), timer4, l));
                        l7 = l13 + l;
                    }
                }
                i4--;
                l = l20;
                l13 = l7;
            }
            i1++;
            l20 = l;
        }

        long l37 = getNetworkActivityBytes(0, i);
        long l39 = getNetworkActivityBytes(1, i);
        long l41 = getNetworkActivityBytes(2, i);
        l31 = getNetworkActivityBytes(3, i);
        long l43 = getNetworkActivityPackets(0, i);
        long l45 = getNetworkActivityPackets(1, i);
        l29 = getNetworkActivityPackets(2, i);
        l34 = getNetworkActivityPackets(3, i);
        l = getNetworkActivityBytes(4, i);
        l7 = getNetworkActivityBytes(5, i);
        if(l20 != 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Total full wakelock time: ");
            formatTimeMsNoSpace(stringbuilder, (500L + l20) / 1000L);
            printwriter.println(stringbuilder.toString());
        }
        if(l13 != 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("  Total partial wakelock time: ");
            formatTimeMsNoSpace(stringbuilder, (500L + l13) / 1000L);
            printwriter.println(stringbuilder.toString());
        }
        printwriter.println("");
        printwriter.print(s);
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  CONNECTIVITY POWER SUMMARY START");
        printwriter.println(stringbuilder.toString());
        printwriter.print(s);
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  Logging duration for connectivity statistics: ");
        formatTimeMs(stringbuilder, l12 / 1000L);
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  Cellular Statistics:");
        printwriter.println(stringbuilder.toString());
        printwriter.print(s);
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("     Cellular kernel active time: ");
        l36 = getMobileRadioActiveTime(l4, i);
        formatTimeMs(stringbuilder, l36 / 1000L);
        stringbuilder.append("(");
        stringbuilder.append(formatRatioLocked(l36, l12));
        stringbuilder.append(")");
        printwriter.println(stringbuilder.toString());
        printwriter.print("     Cellular data received: ");
        printwriter.println(formatBytesLocked(l37));
        printwriter.print("     Cellular data sent: ");
        printwriter.println(formatBytesLocked(l39));
        printwriter.print("     Cellular packets received: ");
        printwriter.println(l43);
        printwriter.print("     Cellular packets sent: ");
        printwriter.println(l45);
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("     Cellular Radio Access Technology:");
        i4 = 0;
        i1 = 0;
        while(i1 < 17) 
        {
            l20 = getPhoneDataConnectionTime(i1, l4, i);
            if(l20 != 0L)
            {
                stringbuilder.append("\n       ");
                stringbuilder.append(s);
                i4 = 1;
                stringbuilder.append(DATA_CONNECTION_NAMES[i1]);
                stringbuilder.append(" ");
                formatTimeMs(stringbuilder, l20 / 1000L);
                stringbuilder.append("(");
                stringbuilder.append(formatRatioLocked(l20, l12));
                stringbuilder.append(") ");
            }
            i1++;
        }
        if(!i4)
            stringbuilder.append(" (no activity)");
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("     Cellular Rx signal strength (RSRP):");
        String as[] = new String[5];
        as[0] = "very poor (less than -128dBm): ";
        as[1] = "poor (-128dBm to -118dBm): ";
        as[2] = "moderate (-118dBm to -108dBm): ";
        as[3] = "good (-108dBm to -98dBm): ";
        as[4] = "great (greater than -98dBm): ";
        i4 = 0;
        int i5 = Math.min(6, as.length);
        i1 = 0;
        while(i1 < i5) 
        {
            l20 = getPhoneSignalStrengthTime(i1, l4, i);
            if(l20 != 0L)
            {
                stringbuilder.append("\n       ");
                stringbuilder.append(s);
                i4 = 1;
                stringbuilder.append(as[i1]);
                stringbuilder.append(" ");
                formatTimeMs(stringbuilder, l20 / 1000L);
                stringbuilder.append("(");
                stringbuilder.append(formatRatioLocked(l20, l12));
                stringbuilder.append(") ");
            }
            i1++;
        }
        if(!i4)
            stringbuilder.append(" (no activity)");
        printwriter.println(stringbuilder.toString());
        printControllerActivity(printwriter, stringbuilder, s, "Cellular", getModemControllerActivity(), i);
        printwriter.print(s);
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  Wifi Statistics:");
        printwriter.println(stringbuilder.toString());
        printwriter.print("     Wifi data received: ");
        printwriter.println(formatBytesLocked(l41));
        printwriter.print("     Wifi data sent: ");
        printwriter.println(formatBytesLocked(l31));
        printwriter.print("     Wifi packets received: ");
        printwriter.println(l29);
        printwriter.print("     Wifi packets sent: ");
        printwriter.println(l34);
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("     Wifi states:");
        i4 = 0;
        i1 = 0;
        while(i1 < 8) 
        {
            l20 = getWifiStateTime(i1, l4, i);
            if(l20 != 0L)
            {
                stringbuilder.append("\n       ");
                i4 = 1;
                stringbuilder.append(WIFI_STATE_NAMES[i1]);
                stringbuilder.append(" ");
                formatTimeMs(stringbuilder, l20 / 1000L);
                stringbuilder.append("(");
                stringbuilder.append(formatRatioLocked(l20, l12));
                stringbuilder.append(") ");
            }
            i1++;
        }
        if(!i4)
            stringbuilder.append(" (no activity)");
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("     Wifi supplicant states:");
        i4 = 0;
        i1 = 0;
        while(i1 < 13) 
        {
            l20 = getWifiSupplStateTime(i1, l4, i);
            if(l20 != 0L)
            {
                stringbuilder.append("\n       ");
                i4 = 1;
                stringbuilder.append(WIFI_SUPPL_STATE_NAMES[i1]);
                stringbuilder.append(" ");
                formatTimeMs(stringbuilder, l20 / 1000L);
                stringbuilder.append("(");
                stringbuilder.append(formatRatioLocked(l20, l12));
                stringbuilder.append(") ");
            }
            i1++;
        }
        if(!i4)
            stringbuilder.append(" (no activity)");
        printwriter.println(stringbuilder.toString());
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("     Wifi Rx signal strength (RSSI):");
        as = new String[5];
        as[0] = "very poor (less than -88.75dBm): ";
        as[1] = "poor (-88.75 to -77.5dBm): ";
        as[2] = "moderate (-77.5dBm to -66.25dBm): ";
        as[3] = "good (-66.25dBm to -55dBm): ";
        as[4] = "great (greater than -55dBm): ";
        i4 = 0;
        i5 = Math.min(5, as.length);
        i1 = 0;
        while(i1 < i5) 
        {
            l20 = getWifiSignalStrengthTime(i1, l4, i);
            if(l20 != 0L)
            {
                stringbuilder.append("\n    ");
                stringbuilder.append(s);
                i4 = 1;
                stringbuilder.append("     ");
                stringbuilder.append(as[i1]);
                formatTimeMs(stringbuilder, l20 / 1000L);
                stringbuilder.append("(");
                stringbuilder.append(formatRatioLocked(l20, l12));
                stringbuilder.append(") ");
            }
            i1++;
        }
        if(!i4)
            stringbuilder.append(" (no activity)");
        printwriter.println(stringbuilder.toString());
        printControllerActivity(printwriter, stringbuilder, s, "WiFi", getWifiControllerActivity(), i);
        printwriter.print(s);
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  CONNECTIVITY POWER SUMMARY END");
        printwriter.println(stringbuilder.toString());
        printwriter.println("");
        printwriter.print(s);
        printwriter.print("  Bluetooth total received: ");
        printwriter.print(formatBytesLocked(l));
        printwriter.print(", sent: ");
        printwriter.println(formatBytesLocked(l7));
        l20 = getBluetoothScanTime(l4, i) / 1000L;
        stringbuilder.setLength(0);
        stringbuilder.append(s);
        stringbuilder.append("  Bluetooth scan time: ");
        formatTimeMs(stringbuilder, l20);
        printwriter.println(stringbuilder.toString());
        printControllerActivity(printwriter, stringbuilder, s, "Bluetooth", getBluetoothControllerActivity(), i);
        printwriter.println();
        if(i == 2)
        {
            int j1;
            if(getIsOnBattery())
            {
                printwriter.print(s);
                printwriter.println("  Device is currently unplugged");
                printwriter.print(s);
                printwriter.print("    Discharge cycle start level: ");
                printwriter.println(getDischargeStartLevel());
                printwriter.print(s);
                printwriter.print("    Discharge cycle current level: ");
                printwriter.println(getDischargeCurrentLevel());
            } else
            {
                printwriter.print(s);
                printwriter.println("  Device is currently plugged into power");
                printwriter.print(s);
                printwriter.print("    Last discharge cycle start level: ");
                printwriter.println(getDischargeStartLevel());
                printwriter.print(s);
                printwriter.print("    Last discharge cycle end level: ");
                printwriter.println(getDischargeCurrentLevel());
            }
            printwriter.print(s);
            printwriter.print("    Amount discharged while screen on: ");
            printwriter.println(getDischargeAmountScreenOn());
            printwriter.print(s);
            printwriter.print("    Amount discharged while screen off: ");
            printwriter.println(getDischargeAmountScreenOff());
            printwriter.print(s);
            printwriter.print("    Amount discharged while screen doze: ");
            printwriter.println(getDischargeAmountScreenDoze());
            printwriter.println(" ");
        } else
        {
            printwriter.print(s);
            printwriter.println("  Device battery use since last full charge");
            printwriter.print(s);
            printwriter.print("    Amount discharged (lower bound): ");
            printwriter.println(getLowDischargeAmountSinceCharge());
            printwriter.print(s);
            printwriter.print("    Amount discharged (upper bound): ");
            printwriter.println(getHighDischargeAmountSinceCharge());
            printwriter.print(s);
            printwriter.print("    Amount discharged while screen on: ");
            printwriter.println(getDischargeAmountScreenOnSinceCharge());
            printwriter.print(s);
            printwriter.print("    Amount discharged while screen off: ");
            printwriter.println(getDischargeAmountScreenOffSinceCharge());
            printwriter.print(s);
            printwriter.print("    Amount discharged while screen doze: ");
            printwriter.println(getDischargeAmountScreenDozeSinceCharge());
            printwriter.println();
        }
        batterystatshelper = new BatteryStatsHelper(context, false, flag);
        batterystatshelper.create(this);
        batterystatshelper.refreshStats(i, -1);
        context = batterystatshelper.getUsageList();
        if(context == null || context.size() <= 0)
            break MISSING_BLOCK_LABEL_5252;
        printwriter.print(s);
        printwriter.println("  Estimated power use (mAh):");
        printwriter.print(s);
        printwriter.print("    Capacity: ");
        printmAh(printwriter, batterystatshelper.getPowerProfile().getBatteryCapacity());
        printwriter.print(", Computed drain: ");
        printmAh(printwriter, batterystatshelper.getComputedPower());
        printwriter.print(", actual drain: ");
        printmAh(printwriter, batterystatshelper.getMinDrainedPower());
        if(batterystatshelper.getMinDrainedPower() != batterystatshelper.getMaxDrainedPower())
        {
            printwriter.print("-");
            printmAh(printwriter, batterystatshelper.getMaxDrainedPower());
        }
        printwriter.println();
        j1 = 0;
        if(j1 >= context.size())
            break MISSING_BLOCK_LABEL_5248;
        obj3 = (BatterySipper)context.get(j1);
        printwriter.print(s);
        _2D_getcom_2D_android_2D_internal_2D_os_2D_BatterySipper$DrainTypeSwitchesValues()[((BatterySipper) (obj3)).drainType.ordinal()];
        JVM INSTR tableswitch 1 12: default 4480
    //                   1 5163
    //                   2 5133
    //                   3 5238
    //                   4 5103
    //                   5 5153
    //                   6 5093
    //                   7 5228
    //                   8 5113
    //                   9 5143
    //                   10 5218
    //                   11 5192
    //                   12 5123;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L4:
        break MISSING_BLOCK_LABEL_5238;
_L7:
        break; /* Loop/switch isn't completed */
_L1:
        printwriter.print("    ???: ");
_L17:
        printmAh(printwriter, ((BatterySipper) (obj3)).totalPowerMah);
        if(((BatterySipper) (obj3)).usagePowerMah != ((BatterySipper) (obj3)).totalPowerMah)
        {
            printwriter.print(" (");
            if(((BatterySipper) (obj3)).usagePowerMah != 0.0D)
            {
                printwriter.print(" usage=");
                printmAh(printwriter, ((BatterySipper) (obj3)).usagePowerMah);
            }
            if(((BatterySipper) (obj3)).cpuPowerMah != 0.0D)
            {
                printwriter.print(" cpu=");
                printmAh(printwriter, ((BatterySipper) (obj3)).cpuPowerMah);
            }
            if(((BatterySipper) (obj3)).wakeLockPowerMah != 0.0D)
            {
                printwriter.print(" wake=");
                printmAh(printwriter, ((BatterySipper) (obj3)).wakeLockPowerMah);
            }
            if(((BatterySipper) (obj3)).mobileRadioPowerMah != 0.0D)
            {
                printwriter.print(" radio=");
                printmAh(printwriter, ((BatterySipper) (obj3)).mobileRadioPowerMah);
            }
            if(((BatterySipper) (obj3)).wifiPowerMah != 0.0D)
            {
                printwriter.print(" wifi=");
                printmAh(printwriter, ((BatterySipper) (obj3)).wifiPowerMah);
            }
            if(((BatterySipper) (obj3)).bluetoothPowerMah != 0.0D)
            {
                printwriter.print(" bt=");
                printmAh(printwriter, ((BatterySipper) (obj3)).bluetoothPowerMah);
            }
            if(((BatterySipper) (obj3)).gpsPowerMah != 0.0D)
            {
                printwriter.print(" gps=");
                printmAh(printwriter, ((BatterySipper) (obj3)).gpsPowerMah);
            }
            if(((BatterySipper) (obj3)).sensorPowerMah != 0.0D)
            {
                printwriter.print(" sensor=");
                printmAh(printwriter, ((BatterySipper) (obj3)).sensorPowerMah);
            }
            if(((BatterySipper) (obj3)).cameraPowerMah != 0.0D)
            {
                printwriter.print(" camera=");
                printmAh(printwriter, ((BatterySipper) (obj3)).cameraPowerMah);
            }
            if(((BatterySipper) (obj3)).flashlightPowerMah != 0.0D)
            {
                printwriter.print(" flash=");
                printmAh(printwriter, ((BatterySipper) (obj3)).flashlightPowerMah);
            }
            printwriter.print(" )");
        }
        if(((BatterySipper) (obj3)).totalSmearedPowerMah != ((BatterySipper) (obj3)).totalPowerMah)
        {
            printwriter.print(" Including smearing: ");
            printmAh(printwriter, ((BatterySipper) (obj3)).totalSmearedPowerMah);
            printwriter.print(" (");
            if(((BatterySipper) (obj3)).screenPowerMah != 0.0D)
            {
                printwriter.print(" screen=");
                printmAh(printwriter, ((BatterySipper) (obj3)).screenPowerMah);
            }
            if(((BatterySipper) (obj3)).proportionalSmearMah != 0.0D)
            {
                printwriter.print(" proportional=");
                printmAh(printwriter, ((BatterySipper) (obj3)).proportionalSmearMah);
            }
            printwriter.print(" )");
        }
        if(((BatterySipper) (obj3)).shouldHide)
            printwriter.print(" Excluded from smearing");
        printwriter.println();
        j1++;
        if(true) goto _L15; else goto _L14
_L15:
        break MISSING_BLOCK_LABEL_4378;
_L14:
        printwriter.print("    Idle: ");
        continue; /* Loop/switch isn't completed */
_L5:
        printwriter.print("    Cell standby: ");
        continue; /* Loop/switch isn't completed */
_L9:
        printwriter.print("    Phone calls: ");
        continue; /* Loop/switch isn't completed */
_L13:
        printwriter.print("    Wifi: ");
        continue; /* Loop/switch isn't completed */
_L3:
        printwriter.print("    Bluetooth: ");
        continue; /* Loop/switch isn't completed */
_L10:
        printwriter.print("    Screen: ");
        continue; /* Loop/switch isn't completed */
_L6:
        printwriter.print("    Flashlight: ");
        continue; /* Loop/switch isn't completed */
_L2:
        printwriter.print("    Uid ");
        UserHandle.formatUid(printwriter, ((BatterySipper) (obj3)).uidObj.getUid());
        printwriter.print(": ");
        continue; /* Loop/switch isn't completed */
_L12:
        printwriter.print("    User ");
        printwriter.print(((BatterySipper) (obj3)).userId);
        printwriter.print(": ");
        continue; /* Loop/switch isn't completed */
_L11:
        printwriter.print("    Unaccounted: ");
        continue; /* Loop/switch isn't completed */
_L8:
        printwriter.print("    Over-counted: ");
        continue; /* Loop/switch isn't completed */
        printwriter.print("    Camera: ");
        if(true) goto _L17; else goto _L16
_L16:
        printwriter.println();
        int j8;
        context = batterystatshelper.getMobilemsppList();
        if(context != null && context.size() > 0)
        {
            printwriter.print(s);
            printwriter.println("  Per-app mobile ms per packet:");
            long l21 = 0L;
            for(int k1 = 0; k1 < context.size(); k1++)
            {
                BatterySipper batterysipper = (BatterySipper)context.get(k1);
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("    Uid ");
                UserHandle.formatUid(stringbuilder, batterysipper.uidObj.getUid());
                stringbuilder.append(": ");
                stringbuilder.append(BatteryStatsHelper.makemAh(batterysipper.mobilemspp));
                stringbuilder.append(" (");
                stringbuilder.append(batterysipper.mobileRxPackets + batterysipper.mobileTxPackets);
                stringbuilder.append(" packets over ");
                formatTimeMsNoSpace(stringbuilder, batterysipper.mobileActive);
                stringbuilder.append(") ");
                stringbuilder.append(batterysipper.mobileActiveCount);
                stringbuilder.append("x");
                printwriter.println(stringbuilder.toString());
                l21 += batterysipper.mobileActive;
            }

            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    TOTAL TIME: ");
            formatTimeMs(stringbuilder, l21);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l21, l12));
            stringbuilder.append(")");
            printwriter.println(stringbuilder.toString());
            printwriter.println();
        }
        context = new Comparator() {

            public int compare(TimerEntry timerentry1, TimerEntry timerentry2)
            {
                long l53 = timerentry1.mTime;
                long l54 = timerentry2.mTime;
                if(l53 < l54)
                    return 1;
                return l53 <= l54 ? 0 : -1;
            }

            public volatile int compare(Object obj4, Object obj5)
            {
                return compare((TimerEntry)obj4, (TimerEntry)obj5);
            }

            final BatteryStats this$0;

            
            {
                this$0 = BatteryStats.this;
                super();
            }
        }
;
        if(j < 0)
        {
            obj3 = getKernelWakelockStats();
            if(((Map) (obj3)).size() > 0)
            {
                ArrayList arraylist = new ArrayList();
                Iterator iterator = ((Map) (obj3)).entrySet().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    obj3 = (java.util.Map.Entry)iterator.next();
                    Timer timer5 = (Timer)((java.util.Map.Entry) (obj3)).getValue();
                    long l22 = computeWakeLock(timer5, l4, i);
                    if(l22 > 0L)
                        arraylist.add(new TimerEntry((String)((java.util.Map.Entry) (obj3)).getKey(), 0, timer5, l22));
                } while(true);
                if(arraylist.size() > 0)
                {
                    Collections.sort(arraylist, context);
                    printwriter.print(s);
                    printwriter.println("  All kernel wake locks:");
                    for(int i2 = 0; i2 < arraylist.size(); i2++)
                    {
                        obj3 = (TimerEntry)arraylist.get(i2);
                        stringbuilder.setLength(0);
                        stringbuilder.append(s);
                        stringbuilder.append("  Kernel Wake lock ");
                        stringbuilder.append(((TimerEntry) (obj3)).mName);
                        if(!printWakeLock(stringbuilder, ((TimerEntry) (obj3)).mTimer, l4, null, i, ": ").equals(": "))
                        {
                            stringbuilder.append(" realtime");
                            printwriter.println(stringbuilder.toString());
                        }
                    }

                    printwriter.println();
                }
            }
            if(((ArrayList) (obj)).size() > 0)
            {
                Collections.sort(((List) (obj)), context);
                printwriter.print(s);
                printwriter.println("  All partial wake locks:");
                for(int j2 = 0; j2 < ((ArrayList) (obj)).size(); j2++)
                {
                    TimerEntry timerentry = (TimerEntry)((ArrayList) (obj)).get(j2);
                    stringbuilder.setLength(0);
                    stringbuilder.append("  Wake lock ");
                    UserHandle.formatUid(stringbuilder, timerentry.mId);
                    stringbuilder.append(" ");
                    stringbuilder.append(timerentry.mName);
                    printWakeLock(stringbuilder, timerentry.mTimer, l4, null, i, ": ");
                    stringbuilder.append(" realtime");
                    printwriter.println(stringbuilder.toString());
                }

                ((ArrayList) (obj)).clear();
                printwriter.println();
            }
            Object obj1 = getWakeupReasonStats();
            if(((Map) (obj1)).size() > 0)
            {
                printwriter.print(s);
                printwriter.println("  All wakeup reasons:");
                obj = new ArrayList();
                for(Iterator iterator1 = ((Map) (obj1)).entrySet().iterator(); iterator1.hasNext(); ((ArrayList) (obj)).add(new TimerEntry((String)((java.util.Map.Entry) (obj1)).getKey(), 0, ((Timer) (obj3)), ((Timer) (obj3)).getCountLocked(i))))
                {
                    obj1 = (java.util.Map.Entry)iterator1.next();
                    obj3 = (Timer)((java.util.Map.Entry) (obj1)).getValue();
                }

                Collections.sort(((List) (obj)), context);
                for(int k2 = 0; k2 < ((ArrayList) (obj)).size(); k2++)
                {
                    context = (TimerEntry)((ArrayList) (obj)).get(k2);
                    stringbuilder.setLength(0);
                    stringbuilder.append(s);
                    stringbuilder.append("  Wakeup reason ");
                    stringbuilder.append(((TimerEntry) (context)).mName);
                    printWakeLock(stringbuilder, ((TimerEntry) (context)).mTimer, l4, null, i, ": ");
                    stringbuilder.append(" realtime");
                    printwriter.println(stringbuilder.toString());
                }

                printwriter.println();
            }
        }
        context = getKernelMemoryStats();
        if(context.size() > 0)
        {
            printwriter.println("  Memory Stats");
            for(int i3 = 0; i3 < context.size(); i3++)
            {
                stringbuilder.setLength(0);
                stringbuilder.append("  Bandwidth ");
                stringbuilder.append(context.keyAt(i3));
                stringbuilder.append(" Time ");
                stringbuilder.append(((Timer)context.valueAt(i3)).getTotalTimeLocked(l4, i));
                printwriter.println(stringbuilder.toString());
            }

            printwriter.println();
        }
        context = getRpmStats();
        if(context.size() > 0)
        {
            printwriter.print(s);
            printwriter.println("  Resource Power Manager Stats");
            if(context.size() > 0)
            {
                String s1;
                for(obj = context.entrySet().iterator(); ((Iterator) (obj)).hasNext(); printTimer(printwriter, stringbuilder, (Timer)context.getValue(), l4, i, s, s1))
                {
                    context = (java.util.Map.Entry)((Iterator) (obj)).next();
                    s1 = (String)context.getKey();
                }

            }
            printwriter.println();
        }
        context = getCpuFreqs();
        if(context != null)
        {
            stringbuilder.setLength(0);
            stringbuilder.append("  CPU freqs:");
            for(int j3 = 0; j3 < context.length; j3++)
                stringbuilder.append(" ").append(context[j3]);

            printwriter.println(stringbuilder.toString());
            printwriter.println();
        }
        j8 = 0;
_L19:
        int k3;
        if(j8 >= k)
            break MISSING_BLOCK_LABEL_11938;
        k3 = sparsearray.keyAt(j8);
        if(j < 0 || k3 == j || k3 == 1000)
            break; /* Loop/switch isn't completed */
_L22:
        j8++;
        if(true) goto _L19; else goto _L18
_L18:
        long l1;
        long l8;
        long l14;
        long l23;
        long l30;
        long l32;
        long l35;
        int j4;
        long l38;
        long l40;
        long l42;
        long l44;
        long l46;
        int j5;
        long l47;
        long l48;
        long l49;
        long l50;
        int k8;
        long l51;
        long l52;
        obj = (Uid)sparsearray.valueAt(j8);
        printwriter.print(s);
        printwriter.print("  ");
        UserHandle.formatUid(printwriter, k3);
        printwriter.println(":");
        j4 = 0;
        l47 = ((Uid) (obj)).getNetworkActivityBytes(0, i);
        l14 = ((Uid) (obj)).getNetworkActivityBytes(1, i);
        l46 = ((Uid) (obj)).getNetworkActivityBytes(2, i);
        l30 = ((Uid) (obj)).getNetworkActivityBytes(3, i);
        l48 = ((Uid) (obj)).getNetworkActivityBytes(4, i);
        l8 = ((Uid) (obj)).getNetworkActivityBytes(5, i);
        l23 = ((Uid) (obj)).getNetworkActivityPackets(0, i);
        l49 = ((Uid) (obj)).getNetworkActivityPackets(1, i);
        l44 = ((Uid) (obj)).getNetworkActivityPackets(2, i);
        l32 = ((Uid) (obj)).getNetworkActivityPackets(3, i);
        l50 = ((Uid) (obj)).getMobileRadioActiveTime(i);
        k8 = ((Uid) (obj)).getMobileRadioActiveCount(i);
        l1 = ((Uid) (obj)).getFullWifiLockTime(l4, i);
        l35 = ((Uid) (obj)).getWifiScanTime(l4, i);
        j5 = ((Uid) (obj)).getWifiScanCount(i);
        k3 = ((Uid) (obj)).getWifiScanBackgroundCount(i);
        l51 = ((Uid) (obj)).getWifiScanActualTime(l4);
        l38 = ((Uid) (obj)).getWifiScanBackgroundTime(l4);
        l42 = ((Uid) (obj)).getWifiRunningTime(l4, i);
        l40 = ((Uid) (obj)).getMobileRadioApWakeupCount(i);
        l52 = ((Uid) (obj)).getWifiRadioApWakeupCount(i);
        break MISSING_BLOCK_LABEL_6774;
        Timer timer;
        Timer timer6;
        int i10;
        if(l47 > 0L || l14 > 0L || l23 > 0L || l49 > 0L)
        {
            printwriter.print(s);
            printwriter.print("    Mobile network: ");
            printwriter.print(formatBytesLocked(l47));
            printwriter.print(" received, ");
            printwriter.print(formatBytesLocked(l14));
            printwriter.print(" sent (packets ");
            printwriter.print(l23);
            printwriter.print(" received, ");
            printwriter.print(l49);
            printwriter.println(" sent)");
        }
        if(l50 > 0L || k8 > 0)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    Mobile radio active: ");
            formatTimeMs(stringbuilder, l50 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l50, l36));
            stringbuilder.append(") ");
            stringbuilder.append(k8);
            stringbuilder.append("x");
            l14 = l23 + l49;
            l23 = l14;
            if(l14 == 0L)
                l23 = 1L;
            stringbuilder.append(" @ ");
            stringbuilder.append(BatteryStatsHelper.makemAh((double)(l50 / 1000L) / (double)l23));
            stringbuilder.append(" mspp");
            printwriter.println(stringbuilder.toString());
        }
        if(l40 > 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    Mobile radio AP wakeups: ");
            stringbuilder.append(l40);
            printwriter.println(stringbuilder.toString());
        }
        printControllerActivityIfInteresting(printwriter, stringbuilder, (new StringBuilder()).append(s).append("  ").toString(), "Modem", ((Uid) (obj)).getModemControllerActivity(), i);
        break MISSING_BLOCK_LABEL_7105;
        if(l46 > 0L || l30 > 0L || l44 > 0L || l32 > 0L)
        {
            printwriter.print(s);
            printwriter.print("    Wi-Fi network: ");
            printwriter.print(formatBytesLocked(l46));
            printwriter.print(" received, ");
            printwriter.print(formatBytesLocked(l30));
            printwriter.print(" sent (packets ");
            printwriter.print(l44);
            printwriter.print(" received, ");
            printwriter.print(l32);
            printwriter.println(" sent)");
        }
        if(l1 != 0L || l35 != 0L || j5 != 0 || k3 != 0 || l51 != 0L || l38 != 0L || l42 != 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    Wifi Running: ");
            formatTimeMs(stringbuilder, l42 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l42, l12));
            stringbuilder.append(")\n");
            stringbuilder.append(s);
            stringbuilder.append("    Full Wifi Lock: ");
            formatTimeMs(stringbuilder, l1 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l1, l12));
            stringbuilder.append(")\n");
            stringbuilder.append(s);
            stringbuilder.append("    Wifi Scan (blamed): ");
            formatTimeMs(stringbuilder, l35 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l35, l12));
            stringbuilder.append(") ");
            stringbuilder.append(j5);
            stringbuilder.append("x\n");
            stringbuilder.append(s);
            stringbuilder.append("    Wifi Scan (actual): ");
            formatTimeMs(stringbuilder, l51 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l51, computeBatteryRealtime(l4, 0)));
            stringbuilder.append(") ");
            stringbuilder.append(j5);
            stringbuilder.append("x\n");
            stringbuilder.append(s);
            stringbuilder.append("    Background Wifi Scan: ");
            formatTimeMs(stringbuilder, l38 / 1000L);
            stringbuilder.append("(");
            stringbuilder.append(formatRatioLocked(l38, computeBatteryRealtime(l4, 0)));
            stringbuilder.append(") ");
            stringbuilder.append(k3);
            stringbuilder.append("x");
            printwriter.println(stringbuilder.toString());
        }
        if(l52 > 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    WiFi AP wakeups: ");
            stringbuilder.append(l52);
            printwriter.println(stringbuilder.toString());
        }
        printControllerActivityIfInteresting(printwriter, stringbuilder, (new StringBuilder()).append(s).append("  ").toString(), "WiFi", ((Uid) (obj)).getWifiControllerActivity(), i);
        if(l48 > 0L || l8 > 0L)
        {
            printwriter.print(s);
            printwriter.print("    Bluetooth network: ");
            printwriter.print(formatBytesLocked(l48));
            printwriter.print(" received, ");
            printwriter.print(formatBytesLocked(l8));
            printwriter.println(" sent");
        }
        timer6 = ((Uid) (obj)).getBluetoothScanTimer();
        k3 = j4;
        if(timer6 != null)
        {
            l32 = (timer6.getTotalTimeLocked(l4, i) + 500L) / 1000L;
            k3 = j4;
            if(l32 != 0L)
            {
                k8 = timer6.getCountLocked(i);
                obj3 = ((Uid) (obj)).getBluetoothScanBackgroundTimer();
                long l2;
                if(obj3 != null)
                    k3 = ((Timer) (obj3)).getCountLocked(i);
                else
                    k3 = 0;
                l30 = timer6.getTotalDurationMsLocked(l5);
                if(obj3 != null)
                    l23 = ((Timer) (obj3)).getTotalDurationMsLocked(l5);
                else
                    l23 = 0L;
                if(((Uid) (obj)).getBluetoothScanResultCounter() != null)
                    j4 = ((Uid) (obj)).getBluetoothScanResultCounter().getCountLocked(i);
                else
                    j4 = 0;
                if(((Uid) (obj)).getBluetoothScanResultBgCounter() != null)
                    j5 = ((Uid) (obj)).getBluetoothScanResultBgCounter().getCountLocked(i);
                else
                    j5 = 0;
                timer = ((Uid) (obj)).getBluetoothUnoptimizedScanTimer();
                if(timer != null)
                    l14 = timer.getTotalDurationMsLocked(l5);
                else
                    l14 = 0L;
                if(timer != null)
                    l8 = timer.getMaxDurationMsLocked(l5);
                else
                    l8 = 0L;
                context = ((Uid) (obj)).getBluetoothUnoptimizedScanBackgroundTimer();
                if(context != null)
                    l2 = context.getTotalDurationMsLocked(l5);
                else
                    l2 = 0L;
                if(context != null)
                    l35 = context.getMaxDurationMsLocked(l5);
                else
                    l35 = 0L;
                stringbuilder.setLength(0);
                if(l30 != l32)
                {
                    stringbuilder.append(s);
                    stringbuilder.append("    Bluetooth Scan (total blamed realtime): ");
                    formatTimeMs(stringbuilder, l32);
                    stringbuilder.append(" (");
                    stringbuilder.append(k8);
                    stringbuilder.append(" times)");
                    if(timer6.isRunningLocked())
                        stringbuilder.append(" (currently running)");
                    stringbuilder.append("\n");
                }
                stringbuilder.append(s);
                stringbuilder.append("    Bluetooth Scan (total actual realtime): ");
                formatTimeMs(stringbuilder, l30);
                stringbuilder.append(" (");
                stringbuilder.append(k8);
                stringbuilder.append(" times)");
                if(timer6.isRunningLocked())
                    stringbuilder.append(" (currently running)");
                stringbuilder.append("\n");
                if(l23 > 0L || k3 > 0)
                {
                    stringbuilder.append(s);
                    stringbuilder.append("    Bluetooth Scan (background realtime): ");
                    formatTimeMs(stringbuilder, l23);
                    stringbuilder.append(" (");
                    stringbuilder.append(k3);
                    stringbuilder.append(" times)");
                    if(obj3 != null && ((Timer) (obj3)).isRunningLocked())
                        stringbuilder.append(" (currently running in background)");
                    stringbuilder.append("\n");
                }
                stringbuilder.append(s);
                stringbuilder.append("    Bluetooth Scan Results: ");
                stringbuilder.append(j4);
                stringbuilder.append(" (");
                stringbuilder.append(j5);
                stringbuilder.append(" in background)");
                if(l14 > 0L || l2 > 0L)
                {
                    stringbuilder.append("\n");
                    stringbuilder.append(s);
                    stringbuilder.append("    Unoptimized Bluetooth Scan (realtime): ");
                    formatTimeMs(stringbuilder, l14);
                    stringbuilder.append(" (max ");
                    formatTimeMs(stringbuilder, l8);
                    stringbuilder.append(")");
                    if(timer != null && timer.isRunningLocked())
                        stringbuilder.append(" (currently running unoptimized)");
                    if(context != null && l2 > 0L)
                    {
                        stringbuilder.append("\n");
                        stringbuilder.append(s);
                        stringbuilder.append("    Unoptimized Bluetooth Scan (background realtime): ");
                        formatTimeMs(stringbuilder, l2);
                        stringbuilder.append(" (max ");
                        formatTimeMs(stringbuilder, l35);
                        stringbuilder.append(")");
                        if(context.isRunningLocked())
                            stringbuilder.append(" (currently running unoptimized in background)");
                    }
                }
                printwriter.println(stringbuilder.toString());
                k3 = 1;
            }
        }
        if(!((Uid) (obj)).hasUserActivity())
            break MISSING_BLOCK_LABEL_8666;
        j4 = 0;
        j5 = 0;
        while(j5 < 4) 
        {
            i10 = ((Uid) (obj)).getUserActivityCount(j5, i);
            k8 = j4;
            if(i10 != 0)
            {
                if(j4 == 0)
                {
                    stringbuilder.setLength(0);
                    stringbuilder.append("    User activity: ");
                    j4 = 1;
                } else
                {
                    stringbuilder.append(", ");
                }
                stringbuilder.append(i10);
                stringbuilder.append(" ");
                stringbuilder.append(Uid.USER_ACTIVITY_TYPES[j5]);
                k8 = j4;
            }
            j5++;
            j4 = k8;
        }
        break MISSING_BLOCK_LABEL_8652;
        if(j4 != 0)
            printwriter.println(stringbuilder.toString());
        obj3 = ((Uid) (obj)).getWakelockStats();
        l35 = 0L;
        l8 = 0L;
        l14 = 0L;
        long l3 = 0L;
        int k5 = 0;
        j4 = ((ArrayMap) (obj3)).size() - 1;
        while(j4 >= 0) 
        {
            Uid.Wakelock wakelock = (Uid.Wakelock)((ArrayMap) (obj3)).valueAt(j4);
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    Wake lock ");
            stringbuilder.append((String)((ArrayMap) (obj3)).keyAt(j4));
            String s2 = printWakeLock(stringbuilder, wakelock.getWakeTime(1), l4, "full", i, ": ");
            context = wakelock.getWakeTime(0);
            s2 = printWakeLock(stringbuilder, context, l4, "partial", i, s2);
            if(context != null)
                context = context.getSubTimer();
            else
                context = null;
            context = printWakeLock(stringbuilder, context, l4, "background partial", i, s2);
            context = printWakeLock(stringbuilder, wakelock.getWakeTime(2), l4, "window", i, context);
            printWakeLock(stringbuilder, wakelock.getWakeTime(18), l4, "draw", i, context);
            stringbuilder.append(" realtime");
            printwriter.println(stringbuilder.toString());
            k3 = 1;
            k5++;
            l35 += computeWakeLock(wakelock.getWakeTime(1), l4, i);
            l8 += computeWakeLock(wakelock.getWakeTime(0), l4, i);
            l14 += computeWakeLock(wakelock.getWakeTime(2), l4, i);
            l3 += computeWakeLock(wakelock.getWakeTime(18), l4, i);
            j4--;
        }
        if(k5 > 1)
        {
            long l33 = 0L;
            l23 = 0L;
            if(((Uid) (obj)).getAggregatedPartialWakelockTimer() != null)
            {
                context = ((Uid) (obj)).getAggregatedPartialWakelockTimer();
                l33 = context.getTotalDurationMsLocked(l5);
                context = context.getSubTimer();
                boolean flag2;
                Timer timer1;
                boolean flag3;
                int i6;
                if(context != null)
                    l23 = context.getTotalDurationMsLocked(l5);
                else
                    l23 = 0L;
            }
            break MISSING_BLOCK_LABEL_9024;
        }
        context = ((Uid) (obj)).getSyncStats();
        flag2 = context.size() - 1;
        while(flag2 >= 0) 
        {
            timer1 = (Timer)context.valueAt(flag2);
            l14 = (timer1.getTotalTimeLocked(l4, i) + 500L) / 1000L;
            i6 = timer1.getCountLocked(i);
            timer1 = timer1.getSubTimer();
            if(timer1 != null)
                l23 = timer1.getTotalDurationMsLocked(l5);
            else
                l23 = -1L;
            if(timer1 != null)
                k3 = timer1.getCountLocked(i);
            else
                k3 = -1;
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    Sync ");
            stringbuilder.append((String)context.keyAt(flag2));
            stringbuilder.append(": ");
            if(l14 != 0L)
            {
                formatTimeMs(stringbuilder, l14);
                stringbuilder.append("realtime (");
                stringbuilder.append(i6);
                stringbuilder.append(" times)");
                if(l23 > 0L)
                {
                    stringbuilder.append(", ");
                    formatTimeMs(stringbuilder, l23);
                    stringbuilder.append("background (");
                    stringbuilder.append(k3);
                    stringbuilder.append(" times)");
                }
            } else
            {
                stringbuilder.append("(not used)");
            }
            printwriter.println(stringbuilder.toString());
            k3 = 1;
            flag2--;
        }
        break MISSING_BLOCK_LABEL_9622;
        if(l33 != 0L || l23 != 0L || l35 != 0L || l8 != 0L || l14 != 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    TOTAL wake: ");
            flag2 = false;
            if(l35 != 0L)
            {
                flag2 = true;
                formatTimeMs(stringbuilder, l35);
                stringbuilder.append("full");
            }
            flag3 = flag2;
            if(l8 != 0L)
            {
                if(flag2)
                    stringbuilder.append(", ");
                flag3 = true;
                formatTimeMs(stringbuilder, l8);
                stringbuilder.append("blamed partial");
            }
            flag2 = flag3;
            if(l33 != 0L)
            {
                if(flag3)
                    stringbuilder.append(", ");
                flag2 = true;
                formatTimeMs(stringbuilder, l33);
                stringbuilder.append("actual partial");
            }
            flag3 = flag2;
            if(l23 != 0L)
            {
                if(flag2)
                    stringbuilder.append(", ");
                flag3 = true;
                formatTimeMs(stringbuilder, l23);
                stringbuilder.append("actual background partial");
            }
            flag2 = flag3;
            if(l14 != 0L)
            {
                if(flag3)
                    stringbuilder.append(", ");
                flag2 = true;
                formatTimeMs(stringbuilder, l14);
                stringbuilder.append("window");
            }
            if(l3 != 0L)
            {
                if(flag2)
                    stringbuilder.append(",");
                formatTimeMs(stringbuilder, l3);
                stringbuilder.append("draw");
            }
            stringbuilder.append(" realtime");
            printwriter.println(stringbuilder.toString());
        }
        break MISSING_BLOCK_LABEL_9320;
        context = ((Uid) (obj)).getJobStats();
        flag2 = context.size() - 1;
        while(flag2 >= 0) 
        {
            Timer timer2 = (Timer)context.valueAt(flag2);
            long l15 = (timer2.getTotalTimeLocked(l4, i) + 500L) / 1000L;
            int j6 = timer2.getCountLocked(i);
            timer2 = timer2.getSubTimer();
            long l24;
            if(timer2 != null)
                l24 = timer2.getTotalDurationMsLocked(l5);
            else
                l24 = -1L;
            if(timer2 != null)
                k3 = timer2.getCountLocked(i);
            else
                k3 = -1;
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    Job ");
            stringbuilder.append((String)context.keyAt(flag2));
            stringbuilder.append(": ");
            if(l15 != 0L)
            {
                formatTimeMs(stringbuilder, l15);
                stringbuilder.append("realtime (");
                stringbuilder.append(j6);
                stringbuilder.append(" times)");
                if(l24 > 0L)
                {
                    stringbuilder.append(", ");
                    formatTimeMs(stringbuilder, l24);
                    stringbuilder.append("background (");
                    stringbuilder.append(k3);
                    stringbuilder.append(" times)");
                }
            } else
            {
                stringbuilder.append("(not used)");
            }
            printwriter.println(stringbuilder.toString());
            k3 = 1;
            flag2--;
        }
        context = ((Uid) (obj)).getJobCompletionStats();
        for(flag2 = context.size() - 1; flag2 >= 0; flag2--)
        {
            SparseIntArray sparseintarray = (SparseIntArray)context.valueAt(flag2);
            if(sparseintarray == null)
                continue;
            printwriter.print(s);
            printwriter.print("    Job Completions ");
            printwriter.print((String)context.keyAt(flag2));
            printwriter.print(":");
            for(int k6 = 0; k6 < sparseintarray.size(); k6++)
            {
                printwriter.print(" ");
                printwriter.print(JobParameters.getReasonName(sparseintarray.keyAt(k6)));
                printwriter.print("(");
                printwriter.print(sparseintarray.valueAt(k6));
                printwriter.print("x)");
            }

            printwriter.println();
        }

        flag2 = k3 | printTimer(printwriter, stringbuilder, ((Uid) (obj)).getFlashlightTurnedOnTimer(), l4, i, s, "Flashlight") | printTimer(printwriter, stringbuilder, ((Uid) (obj)).getCameraTurnedOnTimer(), l4, i, s, "Camera") | printTimer(printwriter, stringbuilder, ((Uid) (obj)).getVideoTurnedOnTimer(), l4, i, s, "Video") | printTimer(printwriter, stringbuilder, ((Uid) (obj)).getAudioTurnedOnTimer(), l4, i, s, "Audio");
        context = ((Uid) (obj)).getSensorStats();
        int i7 = context.size();
        k3 = 0;
        while(k3 < i7) 
        {
            obj3 = (Uid.Sensor)context.valueAt(k3);
            context.keyAt(k3);
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    Sensor ");
            flag2 = ((Uid.Sensor) (obj3)).getHandle();
            Timer timer3;
            if(flag2 == -10000)
                stringbuilder.append("GPS");
            else
                stringbuilder.append(flag2);
            stringbuilder.append(": ");
            timer3 = ((Uid.Sensor) (obj3)).getSensorTime();
            if(timer3 != null)
            {
                long l9 = (timer3.getTotalTimeLocked(l4, i) + 500L) / 1000L;
                int i9 = timer3.getCountLocked(i);
                obj3 = ((Uid.Sensor) (obj3)).getSensorBackgroundTime();
                long l16;
                long l25;
                if(obj3 != null)
                    flag2 = ((Timer) (obj3)).getCountLocked(i);
                else
                    flag2 = false;
                l16 = timer3.getTotalDurationMsLocked(l5);
                if(obj3 != null)
                    l25 = ((Timer) (obj3)).getTotalDurationMsLocked(l5);
                else
                    l25 = 0L;
                if(l9 != 0L)
                {
                    if(l16 != l9)
                    {
                        formatTimeMs(stringbuilder, l9);
                        stringbuilder.append("blamed realtime, ");
                    }
                    formatTimeMs(stringbuilder, l16);
                    stringbuilder.append("realtime (");
                    stringbuilder.append(i9);
                    stringbuilder.append(" times)");
                    if(l25 != 0L || flag2 > 0)
                    {
                        stringbuilder.append(", ");
                        formatTimeMs(stringbuilder, l25);
                        stringbuilder.append("background (");
                        stringbuilder.append(flag2);
                        stringbuilder.append(" times)");
                    }
                } else
                {
                    stringbuilder.append("(not used)");
                }
            } else
            {
                stringbuilder.append("(not used)");
            }
            printwriter.println(stringbuilder.toString());
            flag2 = true;
            k3++;
        }
        k3 = flag2 | printTimer(printwriter, stringbuilder, ((Uid) (obj)).getVibratorOnTimer(), l4, i, s, "Vibrator") | printTimer(printwriter, stringbuilder, ((Uid) (obj)).getForegroundActivityTimer(), l4, i, s, "Foreground activities") | printTimer(printwriter, stringbuilder, ((Uid) (obj)).getForegroundServiceTimer(), l4, i, s, "Foreground services");
        long l26 = 0L;
        for(flag2 = false; flag2 < 6;)
        {
            long l10 = ((Uid) (obj)).getProcessStateTime(flag2, l4, i);
            long l17 = l26;
            if(l10 > 0L)
            {
                l17 = l26 + l10;
                stringbuilder.setLength(0);
                stringbuilder.append(s);
                stringbuilder.append("    ");
                stringbuilder.append(Uid.PROCESS_STATE_NAMES[flag2]);
                stringbuilder.append(" for: ");
                formatTimeMs(stringbuilder, (500L + l10) / 1000L);
                printwriter.println(stringbuilder.toString());
                k3 = 1;
            }
            flag2++;
            l26 = l17;
        }

        if(l26 > 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    Total running: ");
            formatTimeMs(stringbuilder, (500L + l26) / 1000L);
            printwriter.println(stringbuilder.toString());
        }
        long l18 = ((Uid) (obj)).getUserCpuTimeUs(i);
        l26 = ((Uid) (obj)).getSystemCpuTimeUs(i);
        if(l18 > 0L || l26 > 0L)
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    Total cpu time: u=");
            formatTimeMs(stringbuilder, l18 / 1000L);
            stringbuilder.append("s=");
            formatTimeMs(stringbuilder, l26 / 1000L);
            printwriter.println(stringbuilder.toString());
        }
        context = ((Uid) (obj)).getCpuFreqTimes(i);
        if(context != null)
        {
            stringbuilder.setLength(0);
            stringbuilder.append("    Total cpu time per freq:");
            for(flag2 = false; flag2 < context.length; flag2++)
                stringbuilder.append(" ").append(context[flag2]);

            printwriter.println(stringbuilder.toString());
        }
        context = ((Uid) (obj)).getScreenOffCpuFreqTimes(i);
        if(context != null)
        {
            stringbuilder.setLength(0);
            stringbuilder.append("    Total screen-off cpu time per freq:");
            for(flag2 = false; flag2 < context.length; flag2++)
                stringbuilder.append(" ").append(context[flag2]);

            printwriter.println(stringbuilder.toString());
        }
        obj3 = ((Uid) (obj)).getProcessStats();
        flag2 = ((ArrayMap) (obj3)).size() - 1;
_L20:
        if(flag2 < 0)
            break MISSING_BLOCK_LABEL_11514;
        Uid.Proc proc = (Uid.Proc)((ArrayMap) (obj3)).valueAt(flag2);
        long l27 = proc.getUserTime(i);
        long l19 = proc.getSystemTime(i);
        long l11 = proc.getForegroundTime(i);
        int j9 = proc.getStarts(i);
        int j10 = proc.getNumCrashes(i);
        int i11 = proc.getNumAnrs(i);
        int j7;
        if(i == 0)
            j7 = proc.countExcessivePowers();
        else
            j7 = 0;
        while(l27 != 0L || l19 != 0L || l11 != 0L || j9 != 0 || j7 != 0 || j10 != 0 || i11 != 0) 
        {
            stringbuilder.setLength(0);
            stringbuilder.append(s);
            stringbuilder.append("    Proc ");
            stringbuilder.append((String)((ArrayMap) (obj3)).keyAt(flag2));
            stringbuilder.append(":\n");
            stringbuilder.append(s);
            stringbuilder.append("      CPU: ");
            formatTimeMs(stringbuilder, l27);
            stringbuilder.append("usr + ");
            formatTimeMs(stringbuilder, l19);
            stringbuilder.append("krn ; ");
            formatTimeMs(stringbuilder, l11);
            stringbuilder.append("fg");
            if(j9 != 0 || j10 != 0 || i11 != 0)
            {
                stringbuilder.append("\n");
                stringbuilder.append(s);
                stringbuilder.append("      ");
                k3 = 0;
                if(j9 != 0)
                {
                    k3 = 1;
                    stringbuilder.append(j9);
                    stringbuilder.append(" starts");
                }
                j9 = k3;
                if(j10 != 0)
                {
                    if(k3 != 0)
                        stringbuilder.append(", ");
                    j9 = 1;
                    stringbuilder.append(j10);
                    stringbuilder.append(" crashes");
                }
                if(i11 != 0)
                {
                    if(j9 != 0)
                        stringbuilder.append(", ");
                    stringbuilder.append(i11);
                    stringbuilder.append(" anrs");
                }
            }
            printwriter.println(stringbuilder.toString());
            k3 = 0;
            while(k3 < j7) 
            {
                context = proc.getExcessivePower(k3);
                if(context != null)
                {
                    printwriter.print(s);
                    printwriter.print("      * Killed for ");
                    if(((Uid.Proc.ExcessivePower) (context)).type == 2)
                        printwriter.print("cpu");
                    else
                        printwriter.print("unknown");
                    printwriter.print(" use: ");
                    TimeUtils.formatDuration(((Uid.Proc.ExcessivePower) (context)).usedTime, printwriter);
                    printwriter.print(" over ");
                    TimeUtils.formatDuration(((Uid.Proc.ExcessivePower) (context)).overTime, printwriter);
                    if(((Uid.Proc.ExcessivePower) (context)).overTime != 0L)
                    {
                        printwriter.print(" (");
                        printwriter.print((((Uid.Proc.ExcessivePower) (context)).usedTime * 100L) / ((Uid.Proc.ExcessivePower) (context)).overTime);
                        printwriter.println("%)");
                    }
                }
                k3++;
            }
            break MISSING_BLOCK_LABEL_11508;
        }
_L21:
        flag2--;
          goto _L20
        k3 = 1;
          goto _L21
        context = ((Uid) (obj)).getPackageStats();
        for(int k4 = context.size() - 1; k4 >= 0; k4--)
        {
            printwriter.print(s);
            printwriter.print("    Apk ");
            printwriter.print((String)context.keyAt(k4));
            printwriter.println(":");
            k3 = 0;
            Object obj2 = (Uid.Pkg)context.valueAt(k4);
            ArrayMap arraymap = ((Uid.Pkg) (obj2)).getWakeupAlarmStats();
            for(int k7 = arraymap.size() - 1; k7 >= 0; k7--)
            {
                printwriter.print(s);
                printwriter.print("      Wakeup alarm ");
                printwriter.print((String)arraymap.keyAt(k7));
                printwriter.print(": ");
                printwriter.print(((Counter)arraymap.valueAt(k7)).getCountLocked(i));
                printwriter.println(" times");
                k3 = 1;
            }

            obj2 = ((Uid.Pkg) (obj2)).getServiceStats();
            int i8 = ((ArrayMap) (obj2)).size() - 1;
            do
            {
                if(i8 < 0)
                    break;
                Uid.Pkg.Serv serv = (Uid.Pkg.Serv)((ArrayMap) (obj2)).valueAt(i8);
                long l28 = serv.getStartTime(l6, i);
                int k9 = serv.getStarts(i);
                int k10 = serv.getLaunches(i);
                if(l28 != 0L || k9 != 0 || k10 != 0)
                {
                    stringbuilder.setLength(0);
                    stringbuilder.append(s);
                    stringbuilder.append("      Service ");
                    stringbuilder.append((String)((ArrayMap) (obj2)).keyAt(i8));
                    stringbuilder.append(":\n");
                    stringbuilder.append(s);
                    stringbuilder.append("        Created for: ");
                    formatTimeMs(stringbuilder, l28 / 1000L);
                    stringbuilder.append("uptime\n");
                    stringbuilder.append(s);
                    stringbuilder.append("        Starts: ");
                    stringbuilder.append(k9);
                    stringbuilder.append(", launches: ");
                    stringbuilder.append(k10);
                    printwriter.println(stringbuilder.toString());
                    k3 = 1;
                }
                i8--;
            } while(true);
            if(k3 == 0)
            {
                printwriter.print(s);
                printwriter.println("      (nothing executed)");
            }
            k3 = 1;
        }

        if(k3 == 0)
        {
            printwriter.print(s);
            printwriter.println("    (nothing executed)");
        }
          goto _L22
    }

    public abstract void finishIteratingHistoryLocked();

    public abstract void finishIteratingOldHistoryLocked();

    final String formatBytesLocked(long l)
    {
        mFormatBuilder.setLength(0);
        if(l < 1024L)
            return (new StringBuilder()).append(l).append("B").toString();
        if(l < 0x100000L)
        {
            mFormatter.format("%.2fKB", new Object[] {
                Double.valueOf((double)l / 1024D)
            });
            return mFormatBuilder.toString();
        }
        if(l < 0x40000000L)
        {
            mFormatter.format("%.2fMB", new Object[] {
                Double.valueOf((double)l / 1048576D)
            });
            return mFormatBuilder.toString();
        } else
        {
            mFormatter.format("%.2fGB", new Object[] {
                Double.valueOf((double)l / 1073741824D)
            });
            return mFormatBuilder.toString();
        }
    }

    public final String formatRatioLocked(long l, long l1)
    {
        if(l1 == 0L)
        {
            return "--%";
        } else
        {
            float f = (float)l / (float)l1;
            mFormatBuilder.setLength(0);
            mFormatter.format("%.1f%%", new Object[] {
                Float.valueOf(f * 100F)
            });
            return mFormatBuilder.toString();
        }
    }

    public abstract long getBatteryRealtime(long l);

    public abstract long getBatteryUptime(long l);

    public abstract ControllerActivityCounter getBluetoothControllerActivity();

    public abstract long getBluetoothScanTime(long l, int i);

    public abstract long getCameraOnTime(long l, int i);

    public abstract LevelStepTracker getChargeLevelStepTracker();

    public abstract long[] getCpuFreqs();

    public abstract long getCurrentDailyStartTime();

    public abstract LevelStepTracker getDailyChargeLevelStepTracker();

    public abstract LevelStepTracker getDailyDischargeLevelStepTracker();

    public abstract DailyItem getDailyItemLocked(int i);

    public abstract ArrayList getDailyPackageChanges();

    public abstract int getDeviceIdleModeCount(int i, int j);

    public abstract long getDeviceIdleModeTime(int i, long l, int j);

    public abstract int getDeviceIdlingCount(int i, int j);

    public abstract long getDeviceIdlingTime(int i, long l, int j);

    public abstract int getDischargeAmount(int i);

    public abstract int getDischargeAmountScreenDoze();

    public abstract int getDischargeAmountScreenDozeSinceCharge();

    public abstract int getDischargeAmountScreenOff();

    public abstract int getDischargeAmountScreenOffSinceCharge();

    public abstract int getDischargeAmountScreenOn();

    public abstract int getDischargeAmountScreenOnSinceCharge();

    public abstract int getDischargeCurrentLevel();

    public abstract LevelStepTracker getDischargeLevelStepTracker();

    public abstract int getDischargeStartLevel();

    public abstract String getEndPlatformVersion();

    public abstract int getEstimatedBatteryCapacity();

    public abstract long getFlashlightOnCount(int i);

    public abstract long getFlashlightOnTime(long l, int i);

    public abstract long getGlobalWifiRunningTime(long l, int i);

    public abstract int getHighDischargeAmountSinceCharge();

    public abstract long getHistoryBaseTime();

    public abstract int getHistoryStringPoolBytes();

    public abstract int getHistoryStringPoolSize();

    public abstract String getHistoryTagPoolString(int i);

    public abstract int getHistoryTagPoolUid(int i);

    public abstract int getHistoryTotalSize();

    public abstract int getHistoryUsedSize();

    public abstract long getInteractiveTime(long l, int i);

    public abstract boolean getIsOnBattery();

    public abstract LongSparseArray getKernelMemoryStats();

    public abstract Map getKernelWakelockStats();

    public abstract long getLongestDeviceIdleModeTime(int i);

    public abstract int getLowDischargeAmountSinceCharge();

    public abstract long getMahDischarge(int i);

    public abstract long getMahDischargeScreenDoze(int i);

    public abstract long getMahDischargeScreenOff(int i);

    public abstract int getMaxLearnedBatteryCapacity();

    public abstract int getMinLearnedBatteryCapacity();

    public abstract long getMobileRadioActiveAdjustedTime(int i);

    public abstract int getMobileRadioActiveCount(int i);

    public abstract long getMobileRadioActiveTime(long l, int i);

    public abstract int getMobileRadioActiveUnknownCount(int i);

    public abstract long getMobileRadioActiveUnknownTime(int i);

    public abstract ControllerActivityCounter getModemControllerActivity();

    public abstract long getNetworkActivityBytes(int i, int j);

    public abstract long getNetworkActivityPackets(int i, int j);

    public abstract boolean getNextHistoryLocked(HistoryItem historyitem);

    public abstract long getNextMaxDailyDeadline();

    public abstract long getNextMinDailyDeadline();

    public abstract boolean getNextOldHistoryLocked(HistoryItem historyitem);

    public abstract int getNumConnectivityChange(int i);

    public abstract int getParcelVersion();

    public abstract int getPhoneDataConnectionCount(int i, int j);

    public abstract long getPhoneDataConnectionTime(int i, long l, int j);

    public abstract int getPhoneOnCount(int i);

    public abstract long getPhoneOnTime(long l, int i);

    public abstract long getPhoneSignalScanningTime(long l, int i);

    public abstract int getPhoneSignalStrengthCount(int i, int j);

    public abstract long getPhoneSignalStrengthTime(int i, long l, int j);

    public abstract int getPowerSaveModeEnabledCount(int i);

    public abstract long getPowerSaveModeEnabledTime(long l, int i);

    public abstract Map getRpmStats();

    public abstract long getScreenBrightnessTime(int i, long l, int j);

    public abstract int getScreenDozeCount(int i);

    public abstract long getScreenDozeTime(long l, int i);

    public abstract Map getScreenOffRpmStats();

    public abstract int getScreenOnCount(int i);

    public abstract long getScreenOnTime(long l, int i);

    public abstract long getStartClockTime();

    public abstract int getStartCount();

    public abstract String getStartPlatformVersion();

    public abstract SparseArray getUidStats();

    public abstract Map getWakeupReasonStats();

    public abstract ControllerActivityCounter getWifiControllerActivity();

    public abstract long getWifiOnTime(long l, int i);

    public abstract int getWifiSignalStrengthCount(int i, int j);

    public abstract long getWifiSignalStrengthTime(int i, long l, int j);

    public abstract int getWifiStateCount(int i, int j);

    public abstract long getWifiStateTime(int i, long l, int j);

    public abstract int getWifiSupplStateCount(int i, int j);

    public abstract long getWifiSupplStateTime(int i, long l, int j);

    public abstract boolean hasBluetoothActivityReporting();

    public abstract boolean hasModemActivityReporting();

    public abstract boolean hasWifiActivityReporting();

    public void prepareForDumpLocked()
    {
    }

    public abstract boolean startIteratingHistoryLocked();

    public abstract boolean startIteratingOldHistoryLocked();

    public abstract void writeToParcelWithoutUids(Parcel parcel, int i);

    private static final int _2D_com_2D_android_2D_internal_2D_os_2D_BatterySipper$DrainTypeSwitchesValues[];
    private static final String AGGREGATED_WAKELOCK_DATA = "awl";
    public static final int AGGREGATED_WAKE_TYPE_PARTIAL = 20;
    private static final String APK_DATA = "apk";
    private static final String AUDIO_DATA = "aud";
    public static final int AUDIO_TURNED_ON = 15;
    private static final String BATTERY_DATA = "bt";
    private static final String BATTERY_DISCHARGE_DATA = "dc";
    private static final String BATTERY_LEVEL_DATA = "lv";
    private static final int BATTERY_STATS_CHECKIN_VERSION = 9;
    private static final String BLUETOOTH_CONTROLLER_DATA = "ble";
    private static final String BLUETOOTH_MISC_DATA = "blem";
    public static final int BLUETOOTH_SCAN_ON = 19;
    public static final int BLUETOOTH_UNOPTIMIZED_SCAN_ON = 21;
    private static final long BYTES_PER_GB = 0x40000000L;
    private static final long BYTES_PER_KB = 1024L;
    private static final long BYTES_PER_MB = 0x100000L;
    private static final String CAMERA_DATA = "cam";
    public static final int CAMERA_TURNED_ON = 17;
    private static final String CHARGE_STEP_DATA = "csd";
    private static final String CHARGE_TIME_REMAIN_DATA = "ctr";
    static final String CHECKIN_VERSION = "27";
    private static final String CPU_DATA = "cpu";
    private static final String CPU_TIMES_AT_FREQ_DATA = "ctf";
    public static final int DATA_CONNECTION_1xRTT = 7;
    public static final int DATA_CONNECTION_CDMA = 4;
    private static final String DATA_CONNECTION_COUNT_DATA = "dcc";
    public static final int DATA_CONNECTION_EDGE = 2;
    public static final int DATA_CONNECTION_EHRPD = 14;
    public static final int DATA_CONNECTION_EVDO_0 = 5;
    public static final int DATA_CONNECTION_EVDO_A = 6;
    public static final int DATA_CONNECTION_EVDO_B = 12;
    public static final int DATA_CONNECTION_GPRS = 1;
    public static final int DATA_CONNECTION_HSDPA = 8;
    public static final int DATA_CONNECTION_HSPA = 10;
    public static final int DATA_CONNECTION_HSPAP = 15;
    public static final int DATA_CONNECTION_HSUPA = 9;
    public static final int DATA_CONNECTION_IDEN = 11;
    public static final int DATA_CONNECTION_LTE = 13;
    static final String DATA_CONNECTION_NAMES[] = {
        "none", "gprs", "edge", "umts", "cdma", "evdo_0", "evdo_A", "1xrtt", "hsdpa", "hsupa", 
        "hspa", "iden", "evdo_b", "lte", "ehrpd", "hspap", "other"
    };
    public static final int DATA_CONNECTION_NONE = 0;
    public static final int DATA_CONNECTION_OTHER = 16;
    private static final String DATA_CONNECTION_TIME_DATA = "dct";
    public static final int DATA_CONNECTION_UMTS = 3;
    public static final int DEVICE_IDLE_MODE_DEEP = 2;
    public static final int DEVICE_IDLE_MODE_LIGHT = 1;
    public static final int DEVICE_IDLE_MODE_OFF = 0;
    private static final String DISCHARGE_STEP_DATA = "dsd";
    private static final String DISCHARGE_TIME_REMAIN_DATA = "dtr";
    public static final int DUMP_CHARGED_ONLY = 2;
    public static final int DUMP_DAILY_ONLY = 4;
    public static final int DUMP_DEVICE_WIFI_ONLY = 64;
    public static final int DUMP_HISTORY_ONLY = 8;
    public static final int DUMP_INCLUDE_HISTORY = 16;
    public static final int DUMP_VERBOSE = 32;
    private static final String FLASHLIGHT_DATA = "fla";
    public static final int FLASHLIGHT_TURNED_ON = 16;
    public static final int FOREGROUND_ACTIVITY = 10;
    private static final String FOREGROUND_ACTIVITY_DATA = "fg";
    public static final int FOREGROUND_SERVICE = 22;
    private static final String FOREGROUND_SERVICE_DATA = "fgs";
    public static final int FULL_WIFI_LOCK = 5;
    private static final String GLOBAL_BLUETOOTH_CONTROLLER_DATA = "gble";
    private static final String GLOBAL_CPU_FREQ_DATA = "gcf";
    private static final String GLOBAL_MODEM_CONTROLLER_DATA = "gmcd";
    private static final String GLOBAL_NETWORK_DATA = "gn";
    private static final String GLOBAL_WIFI_CONTROLLER_DATA = "gwfcd";
    private static final String GLOBAL_WIFI_DATA = "gwfl";
    private static final String HISTORY_DATA = "h";
    public static final String HISTORY_EVENT_CHECKIN_NAMES[] = {
        "Enl", "Epr", "Efg", "Etp", "Esy", "Ewl", "Ejb", "Eur", "Euf", "Ecn", 
        "Eac", "Epi", "Epu", "Eal", "Est", "Eai", "Eaa", "Etw", "Esw", "Ewa", 
        "Elw", "Eec"
    };
    public static final IntToString HISTORY_EVENT_INT_FORMATTERS[];
    public static final String HISTORY_EVENT_NAMES[] = {
        "null", "proc", "fg", "top", "sync", "wake_lock_in", "job", "user", "userfg", "conn", 
        "active", "pkginst", "pkgunin", "alarm", "stats", "pkginactive", "pkgactive", "tmpwhitelist", "screenwake", "wakeupap", 
        "longwake", "est_capacity"
    };
    public static final BitDescription HISTORY_STATE2_DESCRIPTIONS[] = {
        new BitDescription(0x80000000, "power_save", "ps"), new BitDescription(0x40000000, "video", "v"), new BitDescription(0x20000000, "wifi_running", "Ww"), new BitDescription(0x10000000, "wifi", "W"), new BitDescription(0x8000000, "flashlight", "fl"), new BitDescription(0x6000000, 25, "device_idle", "di", new String[] {
            "off", "light", "full", "???"
        }, new String[] {
            "off", "light", "full", "???"
        }), new BitDescription(0x1000000, "charging", "ch"), new BitDescription(0x800000, "phone_in_call", "Pcl"), new BitDescription(0x400000, "bluetooth", "b"), new BitDescription(112, 4, "wifi_signal_strength", "Wss", new String[] {
            "0", "1", "2", "3", "4"
        }, new String[] {
            "0", "1", "2", "3", "4"
        }), 
        new BitDescription(15, 0, "wifi_suppl", "Wsp", WIFI_SUPPL_STATE_NAMES, WIFI_SUPPL_STATE_SHORT_NAMES), new BitDescription(0x200000, "camera", "ca"), new BitDescription(0x100000, "ble_scan", "bles")
    };
    public static final BitDescription HISTORY_STATE_DESCRIPTIONS[];
    private static final String HISTORY_STRING_POOL = "hsp";
    public static final int JOB = 14;
    private static final String JOB_COMPLETION_DATA = "jbc";
    private static final String JOB_DATA = "jb";
    private static final String KERNEL_WAKELOCK_DATA = "kwl";
    private static final boolean LOCAL_LOGV = false;
    public static final int MAX_TRACKED_SCREEN_STATE = 4;
    private static final String MISC_DATA = "m";
    private static final String MODEM_CONTROLLER_DATA = "mcd";
    public static final int NETWORK_BT_RX_DATA = 4;
    public static final int NETWORK_BT_TX_DATA = 5;
    private static final String NETWORK_DATA = "nt";
    public static final int NETWORK_MOBILE_BG_RX_DATA = 6;
    public static final int NETWORK_MOBILE_BG_TX_DATA = 7;
    public static final int NETWORK_MOBILE_RX_DATA = 0;
    public static final int NETWORK_MOBILE_TX_DATA = 1;
    public static final int NETWORK_WIFI_BG_RX_DATA = 8;
    public static final int NETWORK_WIFI_BG_TX_DATA = 9;
    public static final int NETWORK_WIFI_RX_DATA = 2;
    public static final int NETWORK_WIFI_TX_DATA = 3;
    public static final int NUM_DATA_CONNECTION_TYPES = 17;
    public static final int NUM_NETWORK_ACTIVITY_TYPES = 10;
    public static final int NUM_SCREEN_BRIGHTNESS_BINS = 5;
    public static final int NUM_WIFI_SIGNAL_STRENGTH_BINS = 5;
    public static final int NUM_WIFI_STATES = 8;
    public static final int NUM_WIFI_SUPPL_STATES = 13;
    private static final String POWER_USE_ITEM_DATA = "pwi";
    private static final String POWER_USE_SUMMARY_DATA = "pws";
    private static final String PROCESS_DATA = "pr";
    public static final int PROCESS_STATE = 12;
    private static final String RESOURCE_POWER_MANAGER_DATA = "rpm";
    public static final String RESULT_RECEIVER_CONTROLLER_KEY = "controller_activity";
    public static final int SCREEN_BRIGHTNESS_BRIGHT = 4;
    public static final int SCREEN_BRIGHTNESS_DARK = 0;
    private static final String SCREEN_BRIGHTNESS_DATA = "br";
    public static final int SCREEN_BRIGHTNESS_DIM = 1;
    public static final int SCREEN_BRIGHTNESS_LIGHT = 3;
    public static final int SCREEN_BRIGHTNESS_MEDIUM = 2;
    static final String SCREEN_BRIGHTNESS_NAMES[] = {
        "dark", "dim", "medium", "light", "bright"
    };
    static final String SCREEN_BRIGHTNESS_SHORT_NAMES[] = {
        "0", "1", "2", "3", "4"
    };
    protected static final boolean SCREEN_OFF_RPM_STATS_ENABLED = false;
    public static final int SENSOR = 3;
    private static final String SENSOR_DATA = "sr";
    public static final String SERVICE_NAME = "batterystats";
    private static final String SIGNAL_SCANNING_TIME_DATA = "sst";
    private static final String SIGNAL_STRENGTH_COUNT_DATA = "sgc";
    private static final String SIGNAL_STRENGTH_TIME_DATA = "sgt";
    private static final String STATE_TIME_DATA = "st";
    public static final int STATS_CURRENT = 1;
    public static final int STATS_SINCE_CHARGED = 0;
    public static final int STATS_SINCE_UNPLUGGED = 2;
    private static final String STAT_NAMES[] = {
        "l", "c", "u"
    };
    public static final long STEP_LEVEL_INITIAL_MODE_MASK = 0xff000000000000L;
    public static final int STEP_LEVEL_INITIAL_MODE_SHIFT = 48;
    public static final long STEP_LEVEL_LEVEL_MASK = 0xff0000000000L;
    public static final int STEP_LEVEL_LEVEL_SHIFT = 40;
    public static final int STEP_LEVEL_MODES_OF_INTEREST[] = {
        7, 15, 11, 7, 7, 7, 7, 7, 15, 11
    };
    public static final int STEP_LEVEL_MODE_DEVICE_IDLE = 8;
    public static final String STEP_LEVEL_MODE_LABELS[] = {
        "screen off", "screen off power save", "screen off device idle", "screen on", "screen on power save", "screen doze", "screen doze power save", "screen doze-suspend", "screen doze-suspend power save", "screen doze-suspend device idle"
    };
    public static final int STEP_LEVEL_MODE_POWER_SAVE = 4;
    public static final int STEP_LEVEL_MODE_SCREEN_STATE = 3;
    public static final int STEP_LEVEL_MODE_VALUES[] = {
        0, 4, 8, 1, 5, 2, 6, 3, 7, 11
    };
    public static final long STEP_LEVEL_MODIFIED_MODE_MASK = 0xff00000000000000L;
    public static final int STEP_LEVEL_MODIFIED_MODE_SHIFT = 56;
    public static final long STEP_LEVEL_TIME_MASK = 0xffffffffffL;
    public static final int SYNC = 13;
    private static final String SYNC_DATA = "sy";
    private static final String TAG = "BatteryStats";
    private static final String UID_DATA = "uid";
    private static final String USER_ACTIVITY_DATA = "ua";
    private static final String VERSION_DATA = "vers";
    private static final String VIBRATOR_DATA = "vib";
    public static final int VIBRATOR_ON = 9;
    private static final String VIDEO_DATA = "vid";
    public static final int VIDEO_TURNED_ON = 8;
    private static final String WAKELOCK_DATA = "wl";
    private static final String WAKEUP_ALARM_DATA = "wua";
    private static final String WAKEUP_REASON_DATA = "wr";
    public static final int WAKE_TYPE_DRAW = 18;
    public static final int WAKE_TYPE_FULL = 1;
    public static final int WAKE_TYPE_PARTIAL = 0;
    public static final int WAKE_TYPE_WINDOW = 2;
    public static final int WIFI_BATCHED_SCAN = 11;
    private static final String WIFI_CONTROLLER_DATA = "wfcd";
    private static final String WIFI_DATA = "wfl";
    public static final int WIFI_MULTICAST_ENABLED = 7;
    public static final int WIFI_RUNNING = 4;
    public static final int WIFI_SCAN = 6;
    private static final String WIFI_SIGNAL_STRENGTH_COUNT_DATA = "wsgc";
    private static final String WIFI_SIGNAL_STRENGTH_TIME_DATA = "wsgt";
    private static final String WIFI_STATE_COUNT_DATA = "wsc";
    static final String WIFI_STATE_NAMES[] = {
        "off", "scanning", "no_net", "disconn", "sta", "p2p", "sta_p2p", "soft_ap"
    };
    public static final int WIFI_STATE_OFF = 0;
    public static final int WIFI_STATE_OFF_SCANNING = 1;
    public static final int WIFI_STATE_ON_CONNECTED_P2P = 5;
    public static final int WIFI_STATE_ON_CONNECTED_STA = 4;
    public static final int WIFI_STATE_ON_CONNECTED_STA_P2P = 6;
    public static final int WIFI_STATE_ON_DISCONNECTED = 3;
    public static final int WIFI_STATE_ON_NO_NETWORKS = 2;
    public static final int WIFI_STATE_SOFT_AP = 7;
    private static final String WIFI_STATE_TIME_DATA = "wst";
    public static final int WIFI_SUPPL_STATE_ASSOCIATED = 7;
    public static final int WIFI_SUPPL_STATE_ASSOCIATING = 6;
    public static final int WIFI_SUPPL_STATE_AUTHENTICATING = 5;
    public static final int WIFI_SUPPL_STATE_COMPLETED = 10;
    private static final String WIFI_SUPPL_STATE_COUNT_DATA = "wssc";
    public static final int WIFI_SUPPL_STATE_DISCONNECTED = 1;
    public static final int WIFI_SUPPL_STATE_DORMANT = 11;
    public static final int WIFI_SUPPL_STATE_FOUR_WAY_HANDSHAKE = 8;
    public static final int WIFI_SUPPL_STATE_GROUP_HANDSHAKE = 9;
    public static final int WIFI_SUPPL_STATE_INACTIVE = 3;
    public static final int WIFI_SUPPL_STATE_INTERFACE_DISABLED = 2;
    public static final int WIFI_SUPPL_STATE_INVALID = 0;
    static final String WIFI_SUPPL_STATE_NAMES[] = {
        "invalid", "disconn", "disabled", "inactive", "scanning", "authenticating", "associating", "associated", "4-way-handshake", "group-handshake", 
        "completed", "dormant", "uninit"
    };
    public static final int WIFI_SUPPL_STATE_SCANNING = 4;
    static final String WIFI_SUPPL_STATE_SHORT_NAMES[] = {
        "inv", "dsc", "dis", "inact", "scan", "auth", "ascing", "asced", "4-way", "group", 
        "compl", "dorm", "uninit"
    };
    private static final String WIFI_SUPPL_STATE_TIME_DATA = "wsst";
    public static final int WIFI_SUPPL_STATE_UNINITIALIZED = 12;
    private static final IntToString sIntToString;
    private static final IntToString sUidToString;
    private final String UID_TIMES_TYPE_ALL = "A";
    private final StringBuilder mFormatBuilder = new StringBuilder(32);
    private final Formatter mFormatter;

    static 
    {
        HISTORY_STATE_DESCRIPTIONS = (new BitDescription[] {
            new BitDescription(0x80000000, "running", "r"), new BitDescription(0x40000000, "wake_lock", "w"), new BitDescription(0x800000, "sensor", "s"), new BitDescription(0x20000000, "gps", "g"), new BitDescription(0x10000000, "wifi_full_lock", "Wl"), new BitDescription(0x8000000, "wifi_scan", "Ws"), new BitDescription(0x10000, "wifi_multicast", "Wm"), new BitDescription(0x4000000, "wifi_radio", "Wr"), new BitDescription(0x2000000, "mobile_radio", "Pr"), new BitDescription(0x200000, "phone_scanning", "Psc"), 
            new BitDescription(0x400000, "audio", "a"), new BitDescription(0x100000, "screen", "S"), new BitDescription(0x80000, "plugged", "BP"), new BitDescription(0x40000, "screen_doze", "Sd"), new BitDescription(15872, 9, "data_conn", "Pcn", DATA_CONNECTION_NAMES, DATA_CONNECTION_NAMES), new BitDescription(448, 6, "phone_state", "Pst", new String[] {
                "in", "out", "emergency", "off"
            }, new String[] {
                "in", "out", "em", "off"
            }), new BitDescription(56, 3, "phone_signal_strength", "Pss", SignalStrength.SIGNAL_STRENGTH_NAMES, new String[] {
                "0", "1", "2", "3", "4", "5"
            }), new BitDescription(7, 0, "brightness", "Sb", SCREEN_BRIGHTNESS_NAMES, SCREEN_BRIGHTNESS_SHORT_NAMES)
        });
        sUidToString = _.Lambda._dncxFEc2F2bgG2fsIoC6FC6WNE.$INST$0;
        sIntToString = _.Lambda._dncxFEc2F2bgG2fsIoC6FC6WNE.$INST$1;
        HISTORY_EVENT_INT_FORMATTERS = (new IntToString[] {
            sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, 
            sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, sUidToString, 
            sUidToString, sIntToString
        });
    }
}
