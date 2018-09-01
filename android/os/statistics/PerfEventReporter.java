// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.app.ActivityThreadInjector;
import android.os.*;
import android.system.OsConstants;
import android.util.Slog;
import com.android.internal.app.IPerfShielder;
import com.miui.daemon.performance.PerfShielderManager;
import java.io.FileDescriptor;
import java.io.IOException;
import org.json.JSONObject;

// Referenced classes of package android.os.statistics:
//            PerfSupervisionSettings, PerfEvent, FastPerfEventList, MicroscopicEvent, 
//            PerfEventFilter, OsUtils, SystemServerLockStatistics, LooperOnce, 
//            PerfEventSocket, PerfSuperviser

public class PerfEventReporter
{
    private static class PerfEventCompactThread extends Thread
    {

        private void fetchPerfEventsFromBuffer()
        {
            PerfEvent aperfevent[] = mPerfEventFetchingBuffer;
            PerfEvent aperfevent1[] = aperfevent;
            if(aperfevent == null)
            {
                mPerfEventFetchingBuffer = PerfEventReporter._2D_wrap0();
                aperfevent1 = mPerfEventFetchingBuffer;
            }
            int i = PerfEventReporter._2D_wrap1(aperfevent1);
            if(i == 0)
                return;
            FastPerfEventList fastperfeventlist = mRootEvents;
            FastPerfEventList fastperfeventlist1 = mSuspectedEvents;
            int j = 0;
            while(j < i) 
            {
                Object obj = aperfevent1[j];
                aperfevent1[j] = null;
                if(((PerfEvent) (obj)).isConcerned())
                {
                    ((PerfEvent) (obj)).fillInCurrentProcessId();
                    if(obj instanceof MicroscopicEvent)
                    {
                        obj = (MicroscopicEvent)obj;
                        if(((MicroscopicEvent) (obj)).isRootEvent())
                            fastperfeventlist.add(((PerfEvent) (obj)));
                        else
                            fastperfeventlist1.add(((PerfEvent) (obj)));
                    } else
                    {
                        fastperfeventlist1.add(((PerfEvent) (obj)));
                    }
                }
                j++;
            }
        }

        private long getEarliestBeginUptimeMillis(FastPerfEventList fastperfeventlist)
        {
            long l = 0x7fffffffffffffffL;
            for(int i = 0; i < fastperfeventlist.size;)
            {
                MicroscopicEvent microscopicevent = ((MicroscopicEvent[])fastperfeventlist.events)[i];
                long l1 = l;
                if(microscopicevent != null)
                {
                    l1 = l;
                    if(l > microscopicevent.beginUptimeMillis)
                        l1 = microscopicevent.beginUptimeMillis;
                }
                i++;
                l = l1;
            }

            return l;
        }

        public static PerfEventCompactThread getInstance()
        {
            return sInstance;
        }

        private void loopOnce()
        {
            OsUtils.setThreadPriorityUnconditonally(Process.myTid(), -10);
            do
            {
                char c;
                long l;
                if(mRootEvents.size > 0 || mSuspectedEvents.size > 0)
                    c = '\u01F4';
                else
                if(perfEventFilter.hasPendingPerfEvents())
                    c = '\u03E8';
                else
                    c = '\uFFFF';
                PerfEventReporter._2D_wrap3(c);
                l = SystemClock.uptimeMillis();
                if(l - latestFilterUptimeMillis < 1000L)
                {
                    fetchPerfEventsFromBuffer();
                } else
                {
                    latestFilterUptimeMillis = l;
                    long l1 = PerfEventReporter._2D_wrap2();
                    fetchPerfEventsFromBuffer();
                    l = Math.min(l, Math.min(l1, getEarliestBeginUptimeMillis(mRootEvents)));
                    if(ActivityThreadInjector.isSystemThread())
                        SystemServerLockStatistics.report(mSuspectedEvents, l);
                    perfEventFilter.filter(mRootEvents, mSuspectedEvents, l, mCompletedEvents);
                    if(!mCompletedEvents.isEmpty())
                    {
                        obtainPerfEventSocketFd();
                        int i = 0;
                        while(i < mCompletedEvents.size) 
                        {
                            PerfEvent perfevent = mCompletedEvents.events[i];
                            if(!(perfevent instanceof LooperOnce))
                            {
                                perfevent.fillInCurrentProcessInfo();
                                mSendingParcel.setDataPosition(0);
                                mSendingParcel.setDataSize(0);
                                mSendingParcel.writeInt(perfevent.eventType);
                                perfevent.writeToParcel(mSendingParcel, 0);
                                boolean flag = sendPerfEventParcel(mSendingParcel);
                                if(PerfSupervisionSettings.isInTestMode())
                                    if(flag)
                                    {
                                        if((perfevent instanceof MicroscopicEvent) && ((MicroscopicEvent)perfevent).isRootEvent())
                                            Slog.d("MiuiPerfSuperviser", (new StringBuilder()).append("Succeed to send by PerfEventReporter: ").append(perfevent.toJson().toString()).toString());
                                    } else
                                    {
                                        Slog.e("MiuiPerfSuperviser", (new StringBuilder()).append("Fail to send by PerfEventReporter: ").append(perfevent.toJson().toString()).toString());
                                    }
                                AnrMonitor.checkPerfEvent(perfevent);
                            }
                            i++;
                        }
                    }
                    char c1;
                    if(ActivityThreadInjector.isSystemThread())
                        c1 = '\u0100';
                    else
                        c1 = '@';
                    mRootEvents.clear();
                    mRootEvents.trimTo(c1);
                    mSuspectedEvents.clear();
                    mSuspectedEvents.trimTo(c1);
                    mCompletedEvents.clear();
                    mCompletedEvents.trimTo(c1);
                    System.gc();
                    return;
                }
            } while(true);
        }

        private void obtainPerfEventSocketFd()
        {
            if(mPerfEventSocketFd != null) goto _L2; else goto _L1
_L1:
            IPerfShielder iperfshielder;
            iperfshielder = PerfShielderManager.getService();
            if(iperfshielder == null)
                return;
            mPerfEventSocketFd = iperfshielder.getPerfEventSocketFd();
              goto _L3
_L2:
            return;
            RemoteException remoteexception;
            remoteexception;
            if(!PerfSupervisionSettings.isInTestMode()) goto _L3; else goto _L4
_L4:
            Slog.e("MiuiPerfSuperviser", "Failed to get perf event socket fd", remoteexception);
_L3:
            if(mPerfEventSocketFd == null || mPerfEventSocketFd.getFileDescriptor() == null || mPerfEventSocketFd.getFileDescriptor().valid() ^ true)
                mPerfEventSocketFd = null;
              goto _L2
        }

        private boolean sendPerfEventParcel(Parcel parcel)
        {
            int i;
            if(mPerfEventSocketFd == null)
                break MISSING_BLOCK_LABEL_117;
            i = 0;
_L3:
            int j;
            if(i >= 6)
                break MISSING_BLOCK_LABEL_117;
            j = PerfEventSocket.sendPerfEvent(mPerfEventSocketFd.getFileDescriptor().getInt$(), parcel, 4096);
            if(j >= 0)
                return true;
            j = -j;
            if(PerfSupervisionSettings.isInTestMode())
                Slog.e("MiuiPerfSuperviser", (new StringBuilder()).append("failed to send perf event to perf event socket, errno: ").append(j).toString());
            if(j != OsConstants.EAGAIN && j != OsConstants.EINTR) goto _L2; else goto _L1
_L1:
            try
            {
                Thread.sleep(i / 2 + 1);
            }
            catch(InterruptedException interruptedexception) { }
            i++;
            if(true) goto _L3; else goto _L2
_L2:
            if(j != OsConstants.EMSGSIZE && j != OsConstants.ENOMEM)
            {
                try
                {
                    mPerfEventSocketFd.close();
                }
                // Misplaced declaration of an exception variable
                catch(Parcel parcel)
                {
                    parcel.printStackTrace();
                }
                mPerfEventSocketFd = null;
            }
            return false;
        }

        public void run()
        {
            PerfSuperviser.setThreadPerfSupervisionOn(false);
            mSendingParcel = Parcel.obtain();
            mSendingParcel.setDataCapacity(4096);
            latestFilterUptimeMillis = SystemClock.uptimeMillis();
            do
                loopOnce();
            while(true);
        }

        private static final PerfEventCompactThread sInstance = new PerfEventCompactThread();
        private long latestFilterUptimeMillis;
        private final FastPerfEventList mCompletedEvents = new FastPerfEventList(android/os/statistics/PerfEvent, 32);
        private PerfEvent mPerfEventFetchingBuffer[];
        private ParcelFileDescriptor mPerfEventSocketFd;
        private final FastPerfEventList mRootEvents = new FastPerfEventList(android/os/statistics/MicroscopicEvent, 32);
        private Parcel mSendingParcel;
        private final FastPerfEventList mSuspectedEvents = new FastPerfEventList(android/os/statistics/PerfEvent, 32);
        private final PerfEventFilter perfEventFilter = new PerfEventFilter(true);


        private PerfEventCompactThread()
        {
            super("Binder:perf-event-compact");
        }
    }


    static PerfEvent[] _2D_wrap0()
    {
        return createPerfEventFetchingBuffer();
    }

    static int _2D_wrap1(PerfEvent aperfevent[])
    {
        return fetchPerfEvents(aperfevent);
    }

    static long _2D_wrap2()
    {
        return getEarliestBeginUptimeMillisOfExecutingRootEvents();
    }

    static void _2D_wrap3(int i)
    {
        waitForPerfEventArrived(i);
    }

    public PerfEventReporter()
    {
    }

    private static native PerfEvent[] createPerfEventFetchingBuffer();

    private static native int fetchPerfEvents(PerfEvent aperfevent[]);

    private static native long getEarliestBeginUptimeMillisOfExecutingRootEvents();

    private static native void nativeReport(int i, PerfEvent perfevent);

    public static void report(PerfEvent perfevent)
    {
        if(!PerfSupervisionSettings.isSupervisionOn())
        {
            return;
        } else
        {
            nativeReport(perfevent.eventType, perfevent);
            return;
        }
    }

    static void start()
    {
        PerfEventCompactThread.getInstance().start();
    }

    private static native void waitForPerfEventArrived(int i);

    private static final int APP_PERF_EVENT_ARRAY_CAPACITY = 64;
    private static final boolean DEBUGGING = false;
    private static final int INITIAL_PERF_EVENT_ARRAY_CAPACITY = 32;
    private static final int MAX_RETRY_WRITE_COUNT = 6;
    private static final int SYSTEM_PERF_EVENT_ARRAY_CAPACITY = 256;
}
