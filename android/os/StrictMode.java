// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.animation.ValueAnimator;
import android.app.*;
import android.content.Intent;
import android.net.Uri;
import android.util.*;
import android.view.IWindowManager;
import com.android.internal.os.RuntimeInit;
import com.android.internal.util.FastPrintWriter;
import com.android.internal.util.HexDump;
import dalvik.system.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.os:
//            Process, SystemProperties, Build, SystemClock, 
//            FileUriExposedException, RemoteException, DeadObjectException, Parcel, 
//            Binder, Looper, MessageQueue, ServiceManager, 
//            INetworkManagementService, Handler, NetworkOnMainThreadException, Parcelable

public final class StrictMode
{
    private static class AndroidBlockGuardPolicy
        implements dalvik.system.BlockGuard.Policy
    {

        public int getPolicyMask()
        {
            return mPolicyMask;
        }

        void handleViolation(ViolationInfo violationinfo)
        {
            int i;
            int k;
            while(violationinfo == null || violationinfo.crashInfo == null || violationinfo.crashInfo.stackTrace == null) 
            {
                Log.wtf("StrictMode", "unexpected null stacktrace");
                return;
            }
            if(StrictMode._2D_get1())
                Log.d("StrictMode", (new StringBuilder()).append("handleViolation; policy=").append(violationinfo.policy).toString());
            if((violationinfo.policy & 0x400000) != 0)
            {
                ArrayList arraylist = (ArrayList)StrictMode._2D_get2().get();
                ArrayList arraylist1 = arraylist;
                if(arraylist == null)
                {
                    arraylist1 = new ArrayList(1);
                    StrictMode._2D_get2().set(arraylist1);
                }
                for(Iterator iterator = arraylist1.iterator(); iterator.hasNext();)
                {
                    ViolationInfo violationinfo1 = (ViolationInfo)iterator.next();
                    if(violationinfo.crashInfo.stackTrace.equals(violationinfo1.crashInfo.stackTrace))
                        return;
                }

                arraylist1.add(violationinfo);
                return;
            }
            Integer integer = Integer.valueOf(violationinfo.hashCode());
            long l = 0L;
            long l1;
            int j;
            boolean flag;
            if(mLastViolationTime != null)
            {
                Long long1 = (Long)mLastViolationTime.get(integer);
                if(long1 != null)
                    l = long1.longValue();
            } else
            {
                mLastViolationTime = new ArrayMap(1);
            }
            l1 = SystemClock.uptimeMillis();
            mLastViolationTime.put(integer, Long.valueOf(l1));
            if(l == 0L)
                l1 = 0x7fffffffffffffffL;
            else
                l1 -= l;
            if((violationinfo.policy & 0x10000) != 0 && StrictMode._2D_get5() != null)
                StrictMode._2D_get5().onViolation(violationinfo.crashInfo.stackTrace);
            if((violationinfo.policy & 0x10000) != 0 && l1 > 1000L)
                if(violationinfo.durationMillis != -1)
                    Log.d("StrictMode", (new StringBuilder()).append("StrictMode policy violation; ~duration=").append(violationinfo.durationMillis).append(" ms: ").append(violationinfo.crashInfo.stackTrace).toString());
                else
                    Log.d("StrictMode", (new StringBuilder()).append("StrictMode policy violation: ").append(violationinfo.crashInfo.stackTrace).toString());
            i = 0;
            j = i;
            if((violationinfo.policy & 0x20000) != 0)
            {
                j = i;
                if(l1 > 30000L)
                    j = 0x20000;
            }
            i = j;
            if((violationinfo.policy & 0x200000) != 0)
            {
                i = j;
                if(l == 0L)
                    i = j | 0x200000;
            }
            if(i == 0)
                break MISSING_BLOCK_LABEL_540;
            k = i | StrictMode._2D_wrap1(violationinfo.crashInfo.exceptionMessage);
            i = StrictMode.getThreadPolicyMask();
            if((violationinfo.policy & 0x1770000) == 0x200000)
                flag = true;
            else
                flag = false;
            if(flag)
            {
                StrictMode._2D_wrap2(k, violationinfo);
                return;
            }
            StrictMode._2D_wrap4(0);
            ActivityManager.getService().handleApplicationStrictModeViolation(RuntimeInit.getApplicationObject(), k, violationinfo);
            StrictMode._2D_wrap4(i);
_L2:
            if((violationinfo.policy & 0x40000) != 0)
                StrictMode._2D_wrap3(violationinfo);
            return;
            RemoteException remoteexception;
            remoteexception;
            boolean flag1 = remoteexception instanceof DeadObjectException;
            if(!flag1)
                break; /* Loop/switch isn't completed */
_L3:
            StrictMode._2D_wrap4(i);
            if(true) goto _L2; else goto _L1
_L1:
            Log.e("StrictMode", "RemoteException trying to handle StrictMode violation", remoteexception);
              goto _L3
            violationinfo;
            StrictMode._2D_wrap4(i);
            throw violationinfo;
        }

        void handleViolationWithTimingAttempt(final ViolationInfo windowManager)
        {
            if(Looper.myLooper() == null || (windowManager.policy & 0x1770000) == 0x40000)
            {
                windowManager.durationMillis = -1;
                handleViolation(windowManager);
                return;
            }
            ArrayList arraylist = (ArrayList)StrictMode._2D_get9().get();
            if(arraylist.size() >= 10)
                return;
            arraylist.add(windowManager);
            if(arraylist.size() > 1)
                return;
            if((windowManager.policy & 0x100000) != 0)
                windowManager = (IWindowManager)StrictMode._2D_get7().get();
            else
                windowManager = null;
            if(windowManager != null)
                try
                {
                    windowManager.showStrictModeViolation(true);
                }
                catch(RemoteException remoteexception) { }
            ((Handler)StrictMode._2D_get8().get()).postAtFrontOfQueue(arraylist. new Runnable() {

                public void run()
                {
                    long l = SystemClock.uptimeMillis();
                    int i;
                    if(windowManager != null)
                        try
                        {
                            windowManager.showStrictModeViolation(false);
                        }
                        catch(RemoteException remoteexception) { }
                    for(i = 0; i < records.size(); i++)
                    {
                        ViolationInfo violationinfo = (ViolationInfo)records.get(i);
                        violationinfo.violationNumThisLoop = i + 1;
                        violationinfo.durationMillis = (int)(l - violationinfo.violationUptimeMillis);
                        handleViolation(violationinfo);
                    }

                    records.clear();
                }

                final AndroidBlockGuardPolicy this$1;
                final ArrayList val$records;
                final IWindowManager val$windowManager;

            
            {
                this$1 = final_androidblockguardpolicy;
                windowManager = iwindowmanager;
                records = ArrayList.this;
                super();
            }
            }
);
        }

        void onCustomSlowCall(String s)
        {
            if((mPolicyMask & 8) == 0)
                return;
            if(StrictMode._2D_wrap0())
            {
                return;
            } else
            {
                s = new StrictModeCustomViolation(mPolicyMask, s);
                s.fillInStackTrace();
                startHandlingViolationException(s);
                return;
            }
        }

        public void onNetwork()
        {
            if((mPolicyMask & 4) == 0)
                return;
            if((mPolicyMask & 0x1000000) != 0)
                throw new NetworkOnMainThreadException();
            if(StrictMode._2D_wrap0())
            {
                return;
            } else
            {
                StrictModeNetworkViolation strictmodenetworkviolation = new StrictModeNetworkViolation(mPolicyMask);
                strictmodenetworkviolation.fillInStackTrace();
                startHandlingViolationException(strictmodenetworkviolation);
                return;
            }
        }

        public void onReadFromDisk()
        {
            if((mPolicyMask & 2) == 0)
                return;
            if(StrictMode._2D_wrap0())
            {
                return;
            } else
            {
                StrictModeDiskReadViolation strictmodediskreadviolation = new StrictModeDiskReadViolation(mPolicyMask);
                strictmodediskreadviolation.fillInStackTrace();
                startHandlingViolationException(strictmodediskreadviolation);
                return;
            }
        }

        void onResourceMismatch(Object obj)
        {
            if((mPolicyMask & 0x10) == 0)
                return;
            if(StrictMode._2D_wrap0())
            {
                return;
            } else
            {
                obj = new StrictModeResourceMismatchViolation(mPolicyMask, obj);
                ((dalvik.system.BlockGuard.BlockGuardPolicyException) (obj)).fillInStackTrace();
                startHandlingViolationException(((dalvik.system.BlockGuard.BlockGuardPolicyException) (obj)));
                return;
            }
        }

        public void onUnbufferedIO()
        {
            if((mPolicyMask & 0x20) == 0)
                return;
            if(StrictMode._2D_wrap0())
            {
                return;
            } else
            {
                StrictModeUnbufferedIOViolation strictmodeunbufferedioviolation = new StrictModeUnbufferedIOViolation(mPolicyMask);
                strictmodeunbufferedioviolation.fillInStackTrace();
                startHandlingViolationException(strictmodeunbufferedioviolation);
                return;
            }
        }

        public void onWriteToDisk()
        {
            if((mPolicyMask & 1) == 0)
                return;
            if(StrictMode._2D_wrap0())
            {
                return;
            } else
            {
                StrictModeDiskWriteViolation strictmodediskwriteviolation = new StrictModeDiskWriteViolation(mPolicyMask);
                strictmodediskwriteviolation.fillInStackTrace();
                startHandlingViolationException(strictmodediskwriteviolation);
                return;
            }
        }

        public void setPolicyMask(int i)
        {
            mPolicyMask = i;
        }

        void startHandlingViolationException(dalvik.system.BlockGuard.BlockGuardPolicyException blockguardpolicyexception)
        {
            blockguardpolicyexception = new ViolationInfo(blockguardpolicyexception, blockguardpolicyexception.getPolicy());
            blockguardpolicyexception.violationUptimeMillis = SystemClock.uptimeMillis();
            handleViolationWithTimingAttempt(blockguardpolicyexception);
        }

        public String toString()
        {
            return (new StringBuilder()).append("AndroidBlockGuardPolicy; mPolicyMask=").append(mPolicyMask).toString();
        }

        private ArrayMap mLastViolationTime;
        private int mPolicyMask;

        public AndroidBlockGuardPolicy(int i)
        {
            mPolicyMask = i;
        }
    }

    private static class AndroidCloseGuardReporter
        implements dalvik.system.CloseGuard.Reporter
    {

        public void report(String s, Throwable throwable)
        {
            StrictMode.onVmPolicyViolation(s, throwable);
        }

        private AndroidCloseGuardReporter()
        {
        }

        AndroidCloseGuardReporter(AndroidCloseGuardReporter androidcloseguardreporter)
        {
            this();
        }
    }

    private static class InstanceCountViolation extends Throwable
    {

        private static final StackTraceElement FAKE_STACK[] = {
            new StackTraceElement("android.os.StrictMode", "setClassInstanceLimit", "StrictMode.java", 1)
        };
        final Class mClass;
        final long mInstances;
        final int mLimit;


        public InstanceCountViolation(Class class1, long l, int i)
        {
            super((new StringBuilder()).append(class1.toString()).append("; instances=").append(l).append("; limit=").append(i).toString());
            setStackTrace(FAKE_STACK);
            mClass = class1;
            mInstances = l;
            mLimit = i;
        }
    }

    private static final class InstanceTracker
    {

        public static int getInstanceCount(Class class1)
        {
            HashMap hashmap = sInstanceCounts;
            hashmap;
            JVM INSTR monitorenter ;
            class1 = (Integer)sInstanceCounts.get(class1);
            if(class1 == null)
                break MISSING_BLOCK_LABEL_30;
            int i = class1.intValue();
_L1:
            hashmap;
            JVM INSTR monitorexit ;
            return i;
            i = 0;
              goto _L1
            class1;
            throw class1;
        }

        protected void finalize()
            throws Throwable
        {
            HashMap hashmap = sInstanceCounts;
            hashmap;
            JVM INSTR monitorenter ;
            Integer integer = (Integer)sInstanceCounts.get(mKlass);
            if(integer == null)
                break MISSING_BLOCK_LABEL_50;
            int i = integer.intValue() - 1;
            if(i <= 0)
                break MISSING_BLOCK_LABEL_57;
            sInstanceCounts.put(mKlass, Integer.valueOf(i));
_L1:
            hashmap;
            JVM INSTR monitorexit ;
            super.finalize();
            return;
            sInstanceCounts.remove(mKlass);
              goto _L1
            Exception exception1;
            exception1;
            hashmap;
            JVM INSTR monitorexit ;
            throw exception1;
            Exception exception;
            exception;
            super.finalize();
            throw exception;
        }

        private static final HashMap sInstanceCounts = new HashMap();
        private final Class mKlass;


        public InstanceTracker(Object obj)
        {
            mKlass = obj.getClass();
            obj = sInstanceCounts;
            obj;
            JVM INSTR monitorenter ;
            Integer integer = (Integer)sInstanceCounts.get(mKlass);
            if(integer == null)
                break MISSING_BLOCK_LABEL_61;
            int i = integer.intValue() + 1;
_L1:
            sInstanceCounts.put(mKlass, Integer.valueOf(i));
            obj;
            JVM INSTR monitorexit ;
            return;
            i = 1;
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }
    }

    private static class LogStackTrace extends Exception
    {

        private LogStackTrace()
        {
        }

        LogStackTrace(LogStackTrace logstacktrace)
        {
            this();
        }
    }

    public static class Span
    {

        static String _2D_get0(Span span)
        {
            return span.mName;
        }

        static Span _2D_get1(Span span)
        {
            return span.mNext;
        }

        static long _2D_set0(Span span, long l)
        {
            span.mCreateMillis = l;
            return l;
        }

        static String _2D_set1(Span span, String s)
        {
            span.mName = s;
            return s;
        }

        static Span _2D_set2(Span span, Span span1)
        {
            span.mNext = span1;
            return span1;
        }

        static Span _2D_set3(Span span, Span span1)
        {
            span.mPrev = span1;
            return span1;
        }

        public void finish()
        {
            ThreadSpanState threadspanstate = mContainerState;
            threadspanstate;
            JVM INSTR monitorenter ;
            String s = mName;
            if(s != null)
                break MISSING_BLOCK_LABEL_19;
            threadspanstate;
            JVM INSTR monitorexit ;
            return;
            if(mPrev != null)
                mPrev.mNext = mNext;
            if(mNext != null)
                mNext.mPrev = mPrev;
            if(threadspanstate.mActiveHead == this)
                threadspanstate.mActiveHead = mNext;
            threadspanstate.mActiveSize = threadspanstate.mActiveSize - 1;
            if(StrictMode._2D_get1())
            {
                StringBuilder stringbuilder = JVM INSTR new #58  <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("StrictMode", stringbuilder.append("Span finished=").append(mName).append("; size=").append(threadspanstate.mActiveSize).toString());
            }
            mCreateMillis = -1L;
            mName = null;
            mPrev = null;
            mNext = null;
            if(threadspanstate.mFreeListSize < 5)
            {
                mNext = threadspanstate.mFreeListHead;
                threadspanstate.mFreeListHead = this;
                threadspanstate.mFreeListSize = threadspanstate.mFreeListSize + 1;
            }
            threadspanstate;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private final ThreadSpanState mContainerState;
        private long mCreateMillis;
        private String mName;
        private Span mNext;
        private Span mPrev;

        protected Span()
        {
            mContainerState = null;
        }

        Span(ThreadSpanState threadspanstate)
        {
            mContainerState = threadspanstate;
        }
    }

    private static class StrictModeCustomViolation extends StrictModeViolation
    {

        public StrictModeCustomViolation(int i, String s)
        {
            super(i, 8, s);
        }
    }

    private static class StrictModeDiskReadViolation extends StrictModeViolation
    {

        public StrictModeDiskReadViolation(int i)
        {
            super(i, 2, null);
        }
    }

    private static class StrictModeDiskWriteViolation extends StrictModeViolation
    {

        public StrictModeDiskWriteViolation(int i)
        {
            super(i, 1, null);
        }
    }

    public static class StrictModeNetworkViolation extends StrictModeViolation
    {

        public StrictModeNetworkViolation(int i)
        {
            super(i, 4, null);
        }
    }

    private static class StrictModeResourceMismatchViolation extends StrictModeViolation
    {

        public StrictModeResourceMismatchViolation(int i, Object obj)
        {
            String s = null;
            if(obj != null)
                s = obj.toString();
            super(i, 16, s);
        }
    }

    private static class StrictModeUnbufferedIOViolation extends StrictModeViolation
    {

        public StrictModeUnbufferedIOViolation(int i)
        {
            super(i, 32, null);
        }
    }

    public static class StrictModeViolation extends dalvik.system.BlockGuard.BlockGuardPolicyException
    {

        public StrictModeViolation(int i, int j, String s)
        {
            super(i, j, s);
        }
    }

    public static final class ThreadPolicy
    {

        public String toString()
        {
            return (new StringBuilder()).append("[StrictMode.ThreadPolicy; mask=").append(mask).append("]").toString();
        }

        public static final ThreadPolicy LAX = new ThreadPolicy(0);
        final int mask;


        private ThreadPolicy(int i)
        {
            mask = i;
        }

        ThreadPolicy(int i, ThreadPolicy threadpolicy)
        {
            this(i);
        }
    }

    public static final class ThreadPolicy.Builder
    {

        private ThreadPolicy.Builder disable(int i)
        {
            mMask = mMask & i;
            return this;
        }

        private ThreadPolicy.Builder enable(int i)
        {
            mMask = mMask | i;
            return this;
        }

        public ThreadPolicy build()
        {
            if(mMask != 0 && (mMask & 0x270000) == 0)
                penaltyLog();
            return new ThreadPolicy(mMask, null);
        }

        public ThreadPolicy.Builder detectAll()
        {
            detectDiskReads();
            detectDiskWrites();
            detectNetwork();
            int i = VMRuntime.getRuntime().getTargetSdkVersion();
            if(i >= 11)
                detectCustomSlowCalls();
            if(i >= 23)
                detectResourceMismatches();
            if(i >= 26)
                detectUnbufferedIo();
            return this;
        }

        public ThreadPolicy.Builder detectCustomSlowCalls()
        {
            return enable(8);
        }

        public ThreadPolicy.Builder detectDiskReads()
        {
            return enable(2);
        }

        public ThreadPolicy.Builder detectDiskWrites()
        {
            return enable(1);
        }

        public ThreadPolicy.Builder detectNetwork()
        {
            return enable(4);
        }

        public ThreadPolicy.Builder detectResourceMismatches()
        {
            return enable(16);
        }

        public ThreadPolicy.Builder detectUnbufferedIo()
        {
            return enable(32);
        }

        public ThreadPolicy.Builder penaltyDeath()
        {
            return enable(0x40000);
        }

        public ThreadPolicy.Builder penaltyDeathOnNetwork()
        {
            return enable(0x1000000);
        }

        public ThreadPolicy.Builder penaltyDialog()
        {
            return enable(0x20000);
        }

        public ThreadPolicy.Builder penaltyDropBox()
        {
            return enable(0x200000);
        }

        public ThreadPolicy.Builder penaltyFlashScreen()
        {
            return enable(0x100000);
        }

        public ThreadPolicy.Builder penaltyLog()
        {
            return enable(0x10000);
        }

        public ThreadPolicy.Builder permitAll()
        {
            return disable(63);
        }

        public ThreadPolicy.Builder permitCustomSlowCalls()
        {
            return disable(8);
        }

        public ThreadPolicy.Builder permitDiskReads()
        {
            return disable(2);
        }

        public ThreadPolicy.Builder permitDiskWrites()
        {
            return disable(1);
        }

        public ThreadPolicy.Builder permitNetwork()
        {
            return disable(4);
        }

        public ThreadPolicy.Builder permitResourceMismatches()
        {
            return disable(16);
        }

        public ThreadPolicy.Builder permitUnbufferedIo()
        {
            return disable(32);
        }

        private int mMask;

        public ThreadPolicy.Builder()
        {
            mMask = 0;
            mMask = 0;
        }

        public ThreadPolicy.Builder(ThreadPolicy threadpolicy)
        {
            mMask = 0;
            mMask = threadpolicy.mask;
        }
    }

    private static class ThreadSpanState
    {

        public Span mActiveHead;
        public int mActiveSize;
        public Span mFreeListHead;
        public int mFreeListSize;

        private ThreadSpanState()
        {
        }

        ThreadSpanState(ThreadSpanState threadspanstate)
        {
            this();
        }
    }

    public static class ViolationInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void dump(Printer printer, String s)
        {
            int i = 0;
            if(crashInfo != null)
                crashInfo.dump(printer, s);
            printer.println((new StringBuilder()).append(s).append("policy: ").append(policy).toString());
            if(durationMillis != -1)
                printer.println((new StringBuilder()).append(s).append("durationMillis: ").append(durationMillis).toString());
            if(numInstances != -1L)
                printer.println((new StringBuilder()).append(s).append("numInstances: ").append(numInstances).toString());
            if(violationNumThisLoop != 0)
                printer.println((new StringBuilder()).append(s).append("violationNumThisLoop: ").append(violationNumThisLoop).toString());
            if(numAnimationsRunning != 0)
                printer.println((new StringBuilder()).append(s).append("numAnimationsRunning: ").append(numAnimationsRunning).toString());
            printer.println((new StringBuilder()).append(s).append("violationUptimeMillis: ").append(violationUptimeMillis).toString());
            if(broadcastIntentAction != null)
                printer.println((new StringBuilder()).append(s).append("broadcastIntentAction: ").append(broadcastIntentAction).toString());
            if(tags != null)
            {
                String as[] = tags;
                int j = as.length;
                for(int k = 0; i < j; k++)
                {
                    String s1 = as[i];
                    printer.println((new StringBuilder()).append(s).append("tag[").append(k).append("]: ").append(s1).toString());
                    i++;
                }

            }
        }

        public int hashCode()
        {
            boolean flag = false;
            int i = 17;
            if(crashInfo != null)
                i = crashInfo.stackTrace.hashCode() + 629;
            int j = i;
            if(numAnimationsRunning != 0)
                j = i * 37;
            i = j;
            if(broadcastIntentAction != null)
                i = j * 37 + broadcastIntentAction.hashCode();
            int l = i;
            if(tags != null)
            {
                String as[] = tags;
                int i1 = as.length;
                int k = ((flag) ? 1 : 0);
                do
                {
                    l = i;
                    if(k >= i1)
                        break;
                    i = i * 37 + as[k].hashCode();
                    k++;
                } while(true);
            }
            return l;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(message);
            if(crashInfo != null)
            {
                parcel.writeInt(1);
                crashInfo.writeToParcel(parcel, i);
            } else
            {
                parcel.writeInt(0);
            }
            parcel.dataPosition();
            parcel.writeInt(policy);
            parcel.writeInt(durationMillis);
            parcel.writeInt(violationNumThisLoop);
            parcel.writeInt(numAnimationsRunning);
            parcel.writeLong(violationUptimeMillis);
            parcel.writeLong(numInstances);
            parcel.writeString(broadcastIntentAction);
            parcel.writeStringArray(tags);
            parcel.dataPosition();
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

            public ViolationInfo createFromParcel(Parcel parcel)
            {
                return new ViolationInfo(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ViolationInfo[] newArray(int i)
            {
                return new ViolationInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public String broadcastIntentAction;
        public final android.app.ApplicationErrorReport.CrashInfo crashInfo;
        public int durationMillis;
        public final String message;
        public int numAnimationsRunning;
        public long numInstances;
        public final int policy;
        public String tags[];
        public int violationNumThisLoop;
        public long violationUptimeMillis;


        public ViolationInfo()
        {
            durationMillis = -1;
            numAnimationsRunning = 0;
            numInstances = -1L;
            message = null;
            crashInfo = null;
            policy = 0;
        }

        public ViolationInfo(Parcel parcel)
        {
            this(parcel, false);
        }

        public ViolationInfo(Parcel parcel, boolean flag)
        {
            durationMillis = -1;
            numAnimationsRunning = 0;
            numInstances = -1L;
            message = parcel.readString();
            int i;
            if(parcel.readInt() != 0)
                crashInfo = new android.app.ApplicationErrorReport.CrashInfo(parcel);
            else
                crashInfo = null;
            i = parcel.readInt();
            if(flag)
                policy = 0xffbfffff & i;
            else
                policy = i;
            durationMillis = parcel.readInt();
            violationNumThisLoop = parcel.readInt();
            numAnimationsRunning = parcel.readInt();
            violationUptimeMillis = parcel.readLong();
            numInstances = parcel.readLong();
            broadcastIntentAction = parcel.readString();
            tags = parcel.readStringArray();
        }

        public ViolationInfo(String s, Throwable throwable, int i)
        {
            ThreadSpanState threadspanstate;
            durationMillis = -1;
            numAnimationsRunning = 0;
            numInstances = -1L;
            message = s;
            crashInfo = new android.app.ApplicationErrorReport.CrashInfo(throwable);
            violationUptimeMillis = SystemClock.uptimeMillis();
            policy = i;
            numAnimationsRunning = ValueAnimator.getCurrentAnimationsCount();
            s = ActivityThread.getIntentBeingBroadcast();
            if(s != null)
                broadcastIntentAction = s.getAction();
            threadspanstate = (ThreadSpanState)StrictMode._2D_get6().get();
            if(throwable instanceof InstanceCountViolation)
                numInstances = ((InstanceCountViolation)throwable).mInstances;
            threadspanstate;
            JVM INSTR monitorenter ;
            int j = threadspanstate.mActiveSize;
            i = j;
            if(j > 20)
                i = 20;
            if(i == 0) goto _L2; else goto _L1
_L1:
            tags = new String[i];
            s = threadspanstate.mActiveHead;
            j = 0;
_L3:
            if(s == null || j >= i)
                break; /* Loop/switch isn't completed */
            tags[j] = Span._2D_get0(s);
            j++;
            s = Span._2D_get1(s);
            if(true) goto _L3; else goto _L2
_L2:
            threadspanstate;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        public ViolationInfo(Throwable throwable, int i)
        {
            this(null, throwable, i);
        }
    }

    public static interface ViolationListener
    {

        public abstract void onViolation(String s);
    }

    public static final class VmPolicy
    {

        public String toString()
        {
            return (new StringBuilder()).append("[StrictMode.VmPolicy; mask=").append(mask).append("]").toString();
        }

        public static final VmPolicy LAX = new VmPolicy(0, StrictMode._2D_get0());
        final HashMap classInstanceLimit;
        final int mask;


        private VmPolicy(int i, HashMap hashmap)
        {
            if(hashmap == null)
            {
                throw new NullPointerException("classInstanceLimit == null");
            } else
            {
                mask = i;
                classInstanceLimit = hashmap;
                return;
            }
        }

        VmPolicy(int i, HashMap hashmap, VmPolicy vmpolicy)
        {
            this(i, hashmap);
        }
    }

    public static final class VmPolicy.Builder
    {

        static VmPolicy.Builder _2D_wrap0(VmPolicy.Builder builder, int i)
        {
            return builder.enable(i);
        }

        private VmPolicy.Builder enable(int i)
        {
            mMask = mMask | i;
            return this;
        }

        public VmPolicy build()
        {
            if(mMask != 0 && (mMask & 0x270000) == 0)
                penaltyLog();
            int i = mMask;
            HashMap hashmap;
            if(mClassInstanceLimit != null)
                hashmap = mClassInstanceLimit;
            else
                hashmap = StrictMode._2D_get0();
            return new VmPolicy(i, hashmap, null);
        }

        public VmPolicy.Builder detectActivityLeaks()
        {
            return enable(1024);
        }

        public VmPolicy.Builder detectAll()
        {
            detectLeakedSqlLiteObjects();
            int i = VMRuntime.getRuntime().getTargetSdkVersion();
            if(i >= 11)
            {
                detectActivityLeaks();
                detectLeakedClosableObjects();
            }
            if(i >= 16)
                detectLeakedRegistrationObjects();
            if(i >= 18)
                detectFileUriExposure();
            if(i >= 23 && SystemProperties.getBoolean("persist.sys.strictmode.clear", false))
                detectCleartextNetwork();
            if(i >= 26)
            {
                detectContentUriWithoutPermission();
                detectUntaggedSockets();
            }
            return this;
        }

        public VmPolicy.Builder detectCleartextNetwork()
        {
            return enable(16384);
        }

        public VmPolicy.Builder detectContentUriWithoutPermission()
        {
            return enable(32768);
        }

        public VmPolicy.Builder detectFileUriExposure()
        {
            return enable(8192);
        }

        public VmPolicy.Builder detectLeakedClosableObjects()
        {
            return enable(512);
        }

        public VmPolicy.Builder detectLeakedRegistrationObjects()
        {
            return enable(4096);
        }

        public VmPolicy.Builder detectLeakedSqlLiteObjects()
        {
            return enable(256);
        }

        public VmPolicy.Builder detectUntaggedSockets()
        {
            return enable(0x80000000);
        }

        VmPolicy.Builder disable(int i)
        {
            mMask = mMask & i;
            return this;
        }

        public VmPolicy.Builder penaltyDeath()
        {
            return enable(0x40000);
        }

        public VmPolicy.Builder penaltyDeathOnCleartextNetwork()
        {
            return enable(0x2000000);
        }

        public VmPolicy.Builder penaltyDeathOnFileUriExposure()
        {
            return enable(0x4000000);
        }

        public VmPolicy.Builder penaltyDropBox()
        {
            return enable(0x200000);
        }

        public VmPolicy.Builder penaltyLog()
        {
            return enable(0x10000);
        }

        public VmPolicy.Builder setClassInstanceLimit(Class class1, int i)
        {
            if(class1 == null)
                throw new NullPointerException("klass == null");
            if(!mClassInstanceLimitNeedCow) goto _L2; else goto _L1
_L1:
            if(mClassInstanceLimit.containsKey(class1) && ((Integer)mClassInstanceLimit.get(class1)).intValue() == i)
                return this;
            mClassInstanceLimitNeedCow = false;
            mClassInstanceLimit = (HashMap)mClassInstanceLimit.clone();
_L4:
            mMask = mMask | 0x800;
            mClassInstanceLimit.put(class1, Integer.valueOf(i));
            return this;
_L2:
            if(mClassInstanceLimit == null)
                mClassInstanceLimit = new HashMap();
            if(true) goto _L4; else goto _L3
_L3:
        }

        private HashMap mClassInstanceLimit;
        private boolean mClassInstanceLimitNeedCow;
        private int mMask;

        public VmPolicy.Builder()
        {
            mClassInstanceLimitNeedCow = false;
            mMask = 0;
        }

        public VmPolicy.Builder(VmPolicy vmpolicy)
        {
            mClassInstanceLimitNeedCow = false;
            mMask = vmpolicy.mask;
            mClassInstanceLimitNeedCow = true;
            mClassInstanceLimit = vmpolicy.classInstanceLimit;
        }
    }


    static HashMap _2D_get0()
    {
        return EMPTY_CLASS_LIMIT_MAP;
    }

    static boolean _2D_get1()
    {
        return LOG_V;
    }

    static ThreadLocal _2D_get2()
    {
        return gatheredViolations;
    }

    static AtomicInteger _2D_get3()
    {
        return sDropboxCallsInFlight;
    }

    static long _2D_get4()
    {
        return sLastInstanceCountCheckMillis;
    }

    static ViolationListener _2D_get5()
    {
        return sListener;
    }

    static ThreadLocal _2D_get6()
    {
        return sThisThreadSpanState;
    }

    static Singleton _2D_get7()
    {
        return sWindowManager;
    }

    static ThreadLocal _2D_get8()
    {
        return threadHandler;
    }

    static ThreadLocal _2D_get9()
    {
        return violationsBeingTimed;
    }

    static long _2D_set0(long l)
    {
        sLastInstanceCountCheckMillis = l;
        return l;
    }

    static boolean _2D_wrap0()
    {
        return tooManyViolationsThisLoop();
    }

    static int _2D_wrap1(String s)
    {
        return parseViolationFromMessage(s);
    }

    static void _2D_wrap2(int i, ViolationInfo violationinfo)
    {
        dropboxViolationAsync(i, violationinfo);
    }

    static void _2D_wrap3(ViolationInfo violationinfo)
    {
        executeDeathPenalty(violationinfo);
    }

    static void _2D_wrap4(int i)
    {
        setThreadPolicyMask(i);
    }

    private StrictMode()
    {
    }

    public static ThreadPolicy allowThreadDiskReads()
    {
        int i = getThreadPolicyMask();
        int j = i & -3;
        if(j != i)
            setThreadPolicyMask(j);
        return new ThreadPolicy(i, null);
    }

    public static ThreadPolicy allowThreadDiskWrites()
    {
        int i = getThreadPolicyMask();
        int j = i & -4;
        if(j != i)
            setThreadPolicyMask(j);
        return new ThreadPolicy(i, null);
    }

    private static boolean amTheSystemServerProcess()
    {
        if(Process.myUid() != 1000)
            return false;
        Throwable throwable = new Throwable();
        throwable.fillInStackTrace();
        StackTraceElement astacktraceelement[] = throwable.getStackTrace();
        int i = astacktraceelement.length;
        for(int j = 0; j < i; j++)
        {
            String s = astacktraceelement[j].getClassName();
            if(s != null && s.startsWith("com.android.server."))
                return true;
        }

        return false;
    }

    static void clearGatheredViolations()
    {
        gatheredViolations.set(null);
    }

    public static void conditionallyCheckInstanceCounts()
    {
        VmPolicy vmpolicy = getVmPolicy();
        int i = vmpolicy.classInstanceLimit.size();
        if(i == 0)
            return;
        System.gc();
        System.runFinalization();
        System.gc();
        Class aclass[] = (Class[])vmpolicy.classInstanceLimit.keySet().toArray(new Class[i]);
        long al[] = VMDebug.countInstancesOfClasses(aclass, false);
        for(int j = 0; j < aclass.length; j++)
        {
            Object obj = aclass[j];
            int k = ((Integer)vmpolicy.classInstanceLimit.get(obj)).intValue();
            long l = al[j];
            if(l > (long)k)
            {
                obj = new InstanceCountViolation(((Class) (obj)), l, k);
                onVmPolicyViolation(((Throwable) (obj)).getMessage(), ((Throwable) (obj)));
            }
        }

    }

    public static boolean conditionallyEnableDebugLogging()
    {
        boolean flag;
        boolean flag1;
        if(SystemProperties.getBoolean("persist.sys.strictmode.visual", false))
            flag = amTheSystemServerProcess() ^ true;
        else
            flag = false;
        flag1 = SystemProperties.getBoolean("persist.sys.strictmode.disable", false);
        if(!flag && (Build.IS_USER || flag1))
        {
            setCloseGuardEnabled(false);
            return false;
        }
        boolean flag2 = flag;
        if(Build.IS_ENG)
            flag2 = true;
        int i = 7;
        if(!Build.IS_USER)
            i = 0x200007;
        int j = i;
        if(flag2)
            j = i | 0x100000;
        setThreadPolicyMask(j);
        if(Build.IS_USER)
        {
            setCloseGuardEnabled(false);
        } else
        {
            VmPolicy.Builder builder = (new VmPolicy.Builder()).detectAll();
            VmPolicy.Builder builder1 = builder;
            if(!Build.IS_ENG)
                builder1 = builder.disable(1024);
            builder1 = builder1.penaltyDropBox();
            if(Build.IS_ENG)
                builder1.penaltyLog();
            if(Process.myUid() < 10000)
                VmPolicy.Builder._2D_wrap0(builder1, 0x80000000);
            else
                builder1.disable(0x80000000);
            setVmPolicy(builder1.build());
            setCloseGuardEnabled(vmClosableObjectLeaksEnabled());
        }
        return true;
    }

    public static void decrementExpectedActivityCount(Class class1)
    {
        if(class1 == null)
            return;
        android/os/StrictMode;
        JVM INSTR monitorenter ;
        int i = sVmPolicy.mask;
        if((i & 0x400) != 0)
            break MISSING_BLOCK_LABEL_27;
        android/os/StrictMode;
        JVM INSTR monitorexit ;
        return;
        Integer integer = (Integer)sExpectedActivityInstanceCount.get(class1);
        if(integer == null) goto _L2; else goto _L1
_L1:
        if(integer.intValue() != 0) goto _L3; else goto _L2
_L2:
        i = 0;
_L9:
        if(i != 0) goto _L5; else goto _L4
_L4:
        sExpectedActivityInstanceCount.remove(class1);
_L7:
        i++;
        android/os/StrictMode;
        JVM INSTR monitorexit ;
        if(InstanceTracker.getInstanceCount(class1) <= i)
            return;
        break; /* Loop/switch isn't completed */
_L3:
        i = integer.intValue() - 1;
        continue; /* Loop/switch isn't completed */
_L5:
        sExpectedActivityInstanceCount.put(class1, Integer.valueOf(i));
        if(true) goto _L7; else goto _L6
        class1;
        throw class1;
_L6:
        System.gc();
        System.runFinalization();
        System.gc();
        long l = VMDebug.countInstancesOfClass(class1, false);
        if(l > (long)i)
        {
            class1 = new InstanceCountViolation(class1, l, i);
            onVmPolicyViolation(class1.getMessage(), class1);
        }
        return;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public static void disableDeathOnFileUriExposure()
    {
        sVmPolicyMask &= 0xfbffdfff;
    }

    private static void dropboxViolationAsync(int i, ViolationInfo violationinfo)
    {
        int j = sDropboxCallsInFlight.incrementAndGet();
        if(j > 20)
        {
            sDropboxCallsInFlight.decrementAndGet();
            return;
        }
        if(LOG_V)
            Log.d("StrictMode", (new StringBuilder()).append("Dropboxing async; in-flight=").append(j).toString());
        (new Thread("callActivityManagerForStrictModeDropbox", i, violationinfo) {

            public void run()
            {
                Process.setThreadPriority(10);
                IActivityManager iactivitymanager = ActivityManager.getService();
                if(iactivitymanager != null) goto _L2; else goto _L1
_L1:
                Log.d("StrictMode", "No activity manager; failed to Dropbox violation.");
_L4:
                int k = StrictMode._2D_get3().decrementAndGet();
                if(StrictMode._2D_get1())
                    Log.d("StrictMode", (new StringBuilder()).append("Dropbox complete; in-flight=").append(k).toString());
                return;
_L2:
                try
                {
                    iactivitymanager.handleApplicationStrictModeViolation(RuntimeInit.getApplicationObject(), violationMaskSubset, info);
                }
                catch(RemoteException remoteexception)
                {
                    if(!(remoteexception instanceof DeadObjectException))
                        Log.e("StrictMode", "RemoteException handling StrictMode violation", remoteexception);
                }
                if(true) goto _L4; else goto _L3
_L3:
            }

            final ViolationInfo val$info;
            final int val$violationMaskSubset;

            
            {
                violationMaskSubset = i;
                info = violationinfo;
                super(s);
            }
        }
).start();
    }

    public static void enableDeathOnFileUriExposure()
    {
        sVmPolicyMask |= 0x4002000;
    }

    public static void enableDeathOnNetwork()
    {
        setThreadPolicyMask(getThreadPolicyMask() | 4 | 0x1000000);
    }

    public static void enableDefaults()
    {
        setThreadPolicy((new ThreadPolicy.Builder()).detectAll().penaltyLog().build());
        setVmPolicy((new VmPolicy.Builder()).detectAll().penaltyLog().build());
    }

    public static Span enterCriticalSpan(String s)
    {
        if(Build.IS_USER)
            return NO_OP_SPAN;
        if(s == null || s.isEmpty())
            throw new IllegalArgumentException("name must be non-null and non-empty");
        ThreadSpanState threadspanstate = (ThreadSpanState)sThisThreadSpanState.get();
        threadspanstate;
        JVM INSTR monitorenter ;
        Span span;
        if(threadspanstate.mFreeListHead == null)
            break MISSING_BLOCK_LABEL_185;
        span = threadspanstate.mFreeListHead;
        threadspanstate.mFreeListHead = Span._2D_get1(span);
        threadspanstate.mFreeListSize = threadspanstate.mFreeListSize - 1;
_L1:
        Span._2D_set1(span, s);
        Span._2D_set0(span, SystemClock.uptimeMillis());
        Span._2D_set2(span, threadspanstate.mActiveHead);
        Span._2D_set3(span, null);
        threadspanstate.mActiveHead = span;
        threadspanstate.mActiveSize = threadspanstate.mActiveSize + 1;
        if(Span._2D_get1(span) != null)
            Span._2D_set3(Span._2D_get1(span), span);
        if(LOG_V)
        {
            StringBuilder stringbuilder = JVM INSTR new #503 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("StrictMode", stringbuilder.append("Span enter=").append(s).append("; size=").append(threadspanstate.mActiveSize).toString());
        }
        threadspanstate;
        JVM INSTR monitorexit ;
        return span;
        span = new Span(threadspanstate);
          goto _L1
        s;
        throw s;
    }

    private static void executeDeathPenalty(ViolationInfo violationinfo)
    {
        int i = parseViolationFromMessage(violationinfo.crashInfo.exceptionMessage);
        throw new StrictModeViolation(violationinfo.policy, i, null);
    }

    public static ThreadPolicy getThreadPolicy()
    {
        return new ThreadPolicy(getThreadPolicyMask(), null);
    }

    public static int getThreadPolicyMask()
    {
        return BlockGuard.getThreadPolicy().getPolicyMask();
    }

    public static VmPolicy getVmPolicy()
    {
        android/os/StrictMode;
        JVM INSTR monitorenter ;
        VmPolicy vmpolicy = sVmPolicy;
        android/os/StrictMode;
        JVM INSTR monitorexit ;
        return vmpolicy;
        Exception exception;
        exception;
        throw exception;
    }

    static boolean hasGatheredViolations()
    {
        boolean flag;
        if(gatheredViolations.get() != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static void incrementExpectedActivityCount(Class class1)
    {
        if(class1 == null)
            return;
        android/os/StrictMode;
        JVM INSTR monitorenter ;
        int i = sVmPolicy.mask;
        if((i & 0x400) != 0)
            break MISSING_BLOCK_LABEL_27;
        android/os/StrictMode;
        JVM INSTR monitorexit ;
        return;
        Integer integer = (Integer)sExpectedActivityInstanceCount.get(class1);
        if(integer != null) goto _L2; else goto _L1
_L1:
        i = 1;
_L4:
        sExpectedActivityInstanceCount.put(class1, Integer.valueOf(i));
        android/os/StrictMode;
        JVM INSTR monitorexit ;
        return;
_L2:
        i = integer.intValue();
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        class1;
        throw class1;
    }

    public static void noteDiskRead()
    {
        dalvik.system.BlockGuard.Policy policy = BlockGuard.getThreadPolicy();
        if(!(policy instanceof AndroidBlockGuardPolicy))
        {
            return;
        } else
        {
            ((AndroidBlockGuardPolicy)policy).onReadFromDisk();
            return;
        }
    }

    public static void noteDiskWrite()
    {
        dalvik.system.BlockGuard.Policy policy = BlockGuard.getThreadPolicy();
        if(!(policy instanceof AndroidBlockGuardPolicy))
        {
            return;
        } else
        {
            ((AndroidBlockGuardPolicy)policy).onWriteToDisk();
            return;
        }
    }

    public static void noteResourceMismatch(Object obj)
    {
        dalvik.system.BlockGuard.Policy policy = BlockGuard.getThreadPolicy();
        if(!(policy instanceof AndroidBlockGuardPolicy))
        {
            return;
        } else
        {
            ((AndroidBlockGuardPolicy)policy).onResourceMismatch(obj);
            return;
        }
    }

    public static void noteSlowCall(String s)
    {
        dalvik.system.BlockGuard.Policy policy = BlockGuard.getThreadPolicy();
        if(!(policy instanceof AndroidBlockGuardPolicy))
        {
            return;
        } else
        {
            ((AndroidBlockGuardPolicy)policy).onCustomSlowCall(s);
            return;
        }
    }

    public static void noteUnbufferedIO()
    {
        dalvik.system.BlockGuard.Policy policy = BlockGuard.getThreadPolicy();
        if(!(policy instanceof AndroidBlockGuardPolicy))
        {
            return;
        } else
        {
            ((AndroidBlockGuardPolicy)policy).onUnbufferedIO();
            return;
        }
    }

    private static void onBinderStrictModePolicyChange(int i)
    {
        setBlockGuardPolicy(i);
    }

    public static void onCleartextNetworkDetected(byte abyte0[])
    {
        Object obj = null;
        byte abyte1[] = ((byte []) (obj));
        int i;
        String s;
        boolean flag;
        if(abyte0 != null)
            if(abyte0.length >= 20 && (abyte0[0] & 0xf0) == 64)
            {
                abyte1 = new byte[4];
                System.arraycopy(abyte0, 16, abyte1, 0, 4);
            } else
            {
                abyte1 = ((byte []) (obj));
                if(abyte0.length >= 40)
                {
                    abyte1 = ((byte []) (obj));
                    if((abyte0[0] & 0xf0) == 96)
                    {
                        abyte1 = new byte[16];
                        System.arraycopy(abyte0, 24, abyte1, 0, 16);
                    }
                }
            }
        i = Process.myUid();
        s = (new StringBuilder()).append("Detected cleartext network traffic from UID ").append(i).toString();
        obj = s;
        if(abyte1 != null)
            try
            {
                obj = JVM INSTR new #503 <Class StringBuilder>;
                ((StringBuilder) (obj)).StringBuilder();
                obj = ((StringBuilder) (obj)).append("Detected cleartext network traffic from UID ").append(i).append(" to ").append(InetAddress.getByAddress(abyte1)).toString();
            }
            catch(UnknownHostException unknownhostexception)
            {
                obj = s;
            }
        if((sVmPolicyMask & 0x2000000) != 0)
            flag = true;
        else
            flag = false;
        onVmPolicyViolation(HexDump.dumpHexString(abyte0).trim(), new Throwable(((String) (obj))), flag);
    }

    public static void onContentUriWithoutPermission(Uri uri, String s)
    {
        onVmPolicyViolation(null, new Throwable((new StringBuilder()).append(uri).append(" exposed beyond app through ").append(s).append(" without permission grant flags; did you forget").append(" FLAG_GRANT_READ_URI_PERMISSION?").toString()));
    }

    public static void onFileUriExposed(Uri uri, String s)
    {
        uri = (new StringBuilder()).append(uri).append(" exposed beyond app through ").append(s).toString();
        if((sVmPolicyMask & 0x4000000) != 0)
        {
            throw new FileUriExposedException(uri);
        } else
        {
            onVmPolicyViolation(null, new Throwable(uri));
            return;
        }
    }

    public static void onIntentReceiverLeaked(Throwable throwable)
    {
        onVmPolicyViolation(null, throwable);
    }

    public static void onServiceConnectionLeaked(Throwable throwable)
    {
        onVmPolicyViolation(null, throwable);
    }

    public static void onSqliteObjectLeaked(String s, Throwable throwable)
    {
        onVmPolicyViolation(s, throwable);
    }

    public static void onUntaggedSocket()
    {
        onVmPolicyViolation(null, new Throwable("Untagged socket detected; use TrafficStats.setThreadSocketTag() to track all network usage"));
    }

    public static void onVmPolicyViolation(String s, Throwable throwable)
    {
        onVmPolicyViolation(s, throwable, false);
    }

    public static void onVmPolicyViolation(String s, Throwable throwable, boolean flag)
    {
        int i;
        int j;
        ViolationInfo violationinfo;
        long l1;
        HashMap hashmap;
        Integer integer;
        long l;
        long l2;
        if((sVmPolicyMask & 0x200000) != 0)
            i = 1;
        else
            i = 0;
        if((sVmPolicyMask & 0x40000) != 0)
            flag = true;
        if((sVmPolicyMask & 0x10000) != 0)
            j = 1;
        else
            j = 0;
        violationinfo = new ViolationInfo(s, throwable, sVmPolicyMask);
        violationinfo.numAnimationsRunning = 0;
        violationinfo.tags = null;
        violationinfo.broadcastIntentAction = null;
        integer = Integer.valueOf(violationinfo.hashCode());
        l = SystemClock.uptimeMillis();
        l1 = 0L;
        l2 = 0x7fffffffffffffffL;
        hashmap = sLastVmViolationTime;
        hashmap;
        JVM INSTR monitorenter ;
        if(!sLastVmViolationTime.containsKey(integer))
            break MISSING_BLOCK_LABEL_129;
        l1 = ((Long)sLastVmViolationTime.get(integer)).longValue();
        l2 = l - l1;
        if(l2 <= 1000L)
            break MISSING_BLOCK_LABEL_152;
        sLastVmViolationTime.put(integer, Long.valueOf(l));
        hashmap;
        JVM INSTR monitorexit ;
        if(j != 0 && sListener != null)
            sListener.onViolation(throwable.toString());
        if(j != 0 && l2 > 1000L)
            Log.e("StrictMode", s, throwable);
        j = 0x200000 | sVmPolicyMask & 0x8000ff00;
        if(i && flag ^ true)
        {
            dropboxViolationAsync(j, violationinfo);
            return;
        }
        break MISSING_BLOCK_LABEL_251;
        s;
        throw s;
        if(!i || l1 != 0L)
            break MISSING_BLOCK_LABEL_289;
        i = getThreadPolicyMask();
        setThreadPolicyMask(0);
        ActivityManager.getService().handleApplicationStrictModeViolation(RuntimeInit.getApplicationObject(), j, violationinfo);
        setThreadPolicyMask(i);
_L2:
        if(flag)
        {
            System.err.println("StrictMode VmPolicy violation with POLICY_DEATH; shutting down.");
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
        return;
        s;
        boolean flag1 = s instanceof DeadObjectException;
        if(!flag1)
            break; /* Loop/switch isn't completed */
_L3:
        setThreadPolicyMask(i);
        if(true) goto _L2; else goto _L1
_L1:
        Log.e("StrictMode", "RemoteException trying to handle StrictMode violation", s);
          goto _L3
        s;
        setThreadPolicyMask(i);
        throw s;
    }

    public static void onWebViewMethodCalledOnWrongThread(Throwable throwable)
    {
        onVmPolicyViolation(null, throwable);
    }

    private static int parsePolicyFromMessage(String s)
    {
        if(s == null || s.startsWith("policy=") ^ true)
            return 0;
        int i = s.indexOf(' ');
        if(i == -1)
            return 0;
        s = s.substring(7, i);
        try
        {
            i = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return 0;
        }
        return i;
    }

    private static int parseViolationFromMessage(String s)
    {
        if(s == null)
            return 0;
        int i = s.indexOf("violation=");
        if(i == -1)
            return 0;
        int j = i + "violation=".length();
        int k = s.indexOf(' ', j);
        i = k;
        if(k == -1)
            i = s.length();
        s = s.substring(j, i);
        try
        {
            i = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return 0;
        }
        return i;
    }

    static void readAndHandleBinderCallViolations(Parcel parcel)
    {
        StringWriter stringwriter = new StringWriter();
        stringwriter.append("# via Binder call with stack:\n");
        Object obj = new FastPrintWriter(stringwriter, false, 256);
        (new LogStackTrace(null)).printStackTrace(((PrintWriter) (obj)));
        ((PrintWriter) (obj)).flush();
        obj = stringwriter.toString();
        boolean flag;
        int i;
        if((0x400000 & getThreadPolicyMask()) != 0)
            flag = true;
        else
            flag = false;
        i = parcel.readInt();
        for(int j = 0; j < i; j++)
        {
            ViolationInfo violationinfo = new ViolationInfo(parcel, flag ^ true);
            violationinfo.crashInfo.appendStackTrace(((String) (obj)));
            dalvik.system.BlockGuard.Policy policy = BlockGuard.getThreadPolicy();
            if(policy instanceof AndroidBlockGuardPolicy)
                ((AndroidBlockGuardPolicy)policy).handleViolationWithTimingAttempt(violationinfo);
        }

    }

    private static void setBlockGuardPolicy(int i)
    {
        if(i == 0)
        {
            BlockGuard.setThreadPolicy(BlockGuard.LAX_POLICY);
            return;
        }
        Object obj = BlockGuard.getThreadPolicy();
        if(obj instanceof AndroidBlockGuardPolicy)
        {
            obj = (AndroidBlockGuardPolicy)obj;
        } else
        {
            obj = (AndroidBlockGuardPolicy)threadAndroidPolicy.get();
            BlockGuard.setThreadPolicy(((dalvik.system.BlockGuard.Policy) (obj)));
        }
        ((AndroidBlockGuardPolicy) (obj)).setPolicyMask(i);
    }

    private static void setCloseGuardEnabled(boolean flag)
    {
        if(!(CloseGuard.getReporter() instanceof AndroidCloseGuardReporter))
            CloseGuard.setReporter(new AndroidCloseGuardReporter(null));
        CloseGuard.setEnabled(flag);
    }

    public static void setThreadPolicy(ThreadPolicy threadpolicy)
    {
        setThreadPolicyMask(threadpolicy.mask);
    }

    private static void setThreadPolicyMask(int i)
    {
        setBlockGuardPolicy(i);
        Binder.setThreadStrictModePolicy(i);
    }

    public static void setViolationListener(ViolationListener violationlistener)
    {
        sListener = violationlistener;
    }

    public static void setVmPolicy(VmPolicy vmpolicy)
    {
        android/os/StrictMode;
        JVM INSTR monitorenter ;
        Object obj;
        sVmPolicy = vmpolicy;
        sVmPolicyMask = vmpolicy.mask;
        setCloseGuardEnabled(vmClosableObjectLeaksEnabled());
        obj = Looper.getMainLooper();
        if(obj == null) goto _L2; else goto _L1
_L1:
        obj = ((Looper) (obj)).mQueue;
        if(vmpolicy.classInstanceLimit.size() != 0 && (sVmPolicyMask & 0x6250000) != 0) goto _L4; else goto _L3
_L3:
        ((MessageQueue) (obj)).removeIdleHandler(sProcessIdleHandler);
        sIsIdlerRegistered = false;
_L2:
        byte byte0 = 0;
        if((sVmPolicyMask & 0x4000) != 0)
            if((sVmPolicyMask & 0x40000) != 0 || (sVmPolicyMask & 0x2000000) != 0)
                byte0 = 2;
            else
                byte0 = 1;
        vmpolicy = INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management"));
        if(vmpolicy == null)
            break MISSING_BLOCK_LABEL_154;
        try
        {
            vmpolicy.setUidCleartextNetworkPolicy(Process.myUid(), byte0);
        }
        // Misplaced declaration of an exception variable
        catch(VmPolicy vmpolicy) { }
        android/os/StrictMode;
        JVM INSTR monitorexit ;
        return;
_L4:
        if(sIsIdlerRegistered) goto _L2; else goto _L5
_L5:
        ((MessageQueue) (obj)).addIdleHandler(sProcessIdleHandler);
        sIsIdlerRegistered = true;
          goto _L2
        vmpolicy;
        throw vmpolicy;
        if(byte0 == 0)
            break MISSING_BLOCK_LABEL_119;
        Log.w("StrictMode", "Dropping requested network policy due to missing service!");
        break MISSING_BLOCK_LABEL_119;
    }

    private static boolean tooManyViolationsThisLoop()
    {
        boolean flag;
        if(((ArrayList)violationsBeingTimed.get()).size() >= 10)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static Object trackActivity(Object obj)
    {
        return new InstanceTracker(obj);
    }

    public static boolean vmCleartextNetworkEnabled()
    {
        boolean flag = false;
        if((sVmPolicyMask & 0x4000) != 0)
            flag = true;
        return flag;
    }

    public static boolean vmClosableObjectLeaksEnabled()
    {
        boolean flag = false;
        if((sVmPolicyMask & 0x200) != 0)
            flag = true;
        return flag;
    }

    public static boolean vmContentUriWithoutPermissionEnabled()
    {
        boolean flag = false;
        if((sVmPolicyMask & 0x8000) != 0)
            flag = true;
        return flag;
    }

    public static boolean vmFileUriExposureEnabled()
    {
        boolean flag = false;
        if((sVmPolicyMask & 0x2000) != 0)
            flag = true;
        return flag;
    }

    public static boolean vmRegistrationLeaksEnabled()
    {
        boolean flag = false;
        if((sVmPolicyMask & 0x1000) != 0)
            flag = true;
        return flag;
    }

    public static boolean vmSqliteObjectLeaksEnabled()
    {
        boolean flag = false;
        if((sVmPolicyMask & 0x100) != 0)
            flag = true;
        return flag;
    }

    public static boolean vmUntaggedSocketEnabled()
    {
        boolean flag = false;
        if((sVmPolicyMask & 0x80000000) != 0)
            flag = true;
        return flag;
    }

    static void writeGatheredViolationsToParcel(Parcel parcel)
    {
        ArrayList arraylist = (ArrayList)gatheredViolations.get();
        if(arraylist == null)
        {
            parcel.writeInt(0);
        } else
        {
            int i = Math.min(arraylist.size(), 3);
            parcel.writeInt(i);
            int j = 0;
            while(j < i) 
            {
                ((ViolationInfo)arraylist.get(j)).writeToParcel(parcel, 0);
                j++;
            }
        }
        gatheredViolations.set(null);
    }

    private static final int ALL_THREAD_DETECT_BITS = 63;
    private static final int ALL_VM_DETECT_BITS = 0x8000ff00;
    private static final String CLEARTEXT_PROPERTY = "persist.sys.strictmode.clear";
    public static final int DETECT_CUSTOM = 8;
    public static final int DETECT_DISK_READ = 2;
    public static final int DETECT_DISK_WRITE = 1;
    public static final int DETECT_NETWORK = 4;
    public static final int DETECT_RESOURCE_MISMATCH = 16;
    public static final int DETECT_UNBUFFERED_IO = 32;
    public static final int DETECT_VM_ACTIVITY_LEAKS = 1024;
    private static final int DETECT_VM_CLEARTEXT_NETWORK = 16384;
    public static final int DETECT_VM_CLOSABLE_LEAKS = 512;
    private static final int DETECT_VM_CONTENT_URI_WITHOUT_PERMISSION = 32768;
    public static final int DETECT_VM_CURSOR_LEAKS = 256;
    private static final int DETECT_VM_FILE_URI_EXPOSURE = 8192;
    private static final int DETECT_VM_INSTANCE_LEAKS = 2048;
    public static final int DETECT_VM_REGISTRATION_LEAKS = 4096;
    private static final int DETECT_VM_UNTAGGED_SOCKET = 0x80000000;
    public static final String DISABLE_PROPERTY = "persist.sys.strictmode.disable";
    private static final HashMap EMPTY_CLASS_LIMIT_MAP = new HashMap();
    private static final boolean LOG_V = Log.isLoggable("StrictMode", 2);
    private static final int MAX_OFFENSES_PER_LOOP = 10;
    private static final int MAX_SPAN_TAGS = 20;
    private static final long MIN_DIALOG_INTERVAL_MS = 30000L;
    private static final long MIN_LOG_INTERVAL_MS = 1000L;
    public static final int NETWORK_POLICY_ACCEPT = 0;
    public static final int NETWORK_POLICY_LOG = 1;
    public static final int NETWORK_POLICY_REJECT = 2;
    private static final Span NO_OP_SPAN = new Span() {

        public void finish()
        {
        }

    }
;
    public static final int PENALTY_DEATH = 0x40000;
    public static final int PENALTY_DEATH_ON_CLEARTEXT_NETWORK = 0x2000000;
    public static final int PENALTY_DEATH_ON_FILE_URI_EXPOSURE = 0x4000000;
    public static final int PENALTY_DEATH_ON_NETWORK = 0x1000000;
    public static final int PENALTY_DIALOG = 0x20000;
    public static final int PENALTY_DROPBOX = 0x200000;
    public static final int PENALTY_FLASH = 0x100000;
    public static final int PENALTY_GATHER = 0x400000;
    public static final int PENALTY_LOG = 0x10000;
    private static final String TAG = "StrictMode";
    private static final int THREAD_PENALTY_MASK = 0x1770000;
    public static final String VISUAL_PROPERTY = "persist.sys.strictmode.visual";
    private static final int VM_PENALTY_MASK = 0x6250000;
    private static final ThreadLocal gatheredViolations = new ThreadLocal() {

        protected volatile Object initialValue()
        {
            return initialValue();
        }

        protected ArrayList initialValue()
        {
            return null;
        }

    }
;
    private static final AtomicInteger sDropboxCallsInFlight = new AtomicInteger(0);
    private static final HashMap sExpectedActivityInstanceCount = new HashMap();
    private static boolean sIsIdlerRegistered = false;
    private static long sLastInstanceCountCheckMillis = 0L;
    private static final HashMap sLastVmViolationTime = new HashMap();
    private static volatile ViolationListener sListener;
    private static final MessageQueue.IdleHandler sProcessIdleHandler = new MessageQueue.IdleHandler() {

        public boolean queueIdle()
        {
            long l = SystemClock.uptimeMillis();
            if(l - StrictMode._2D_get4() > 30000L)
            {
                StrictMode._2D_set0(l);
                StrictMode.conditionallyCheckInstanceCounts();
            }
            return true;
        }

    }
;
    private static final ThreadLocal sThisThreadSpanState = new ThreadLocal() {

        protected ThreadSpanState initialValue()
        {
            return new ThreadSpanState(null);
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;
    private static volatile VmPolicy sVmPolicy;
    private static volatile int sVmPolicyMask = 0;
    private static Singleton sWindowManager = new Singleton() {

        protected IWindowManager create()
        {
            return android.view.IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        }

        protected volatile Object create()
        {
            return create();
        }

    }
;
    private static final ThreadLocal threadAndroidPolicy = new ThreadLocal() {

        protected AndroidBlockGuardPolicy initialValue()
        {
            return new AndroidBlockGuardPolicy(0);
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;
    private static final ThreadLocal threadHandler = new ThreadLocal() {

        protected Handler initialValue()
        {
            return new Handler();
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

    }
;
    private static final ThreadLocal violationsBeingTimed = new ThreadLocal() {

        protected volatile Object initialValue()
        {
            return initialValue();
        }

        protected ArrayList initialValue()
        {
            return new ArrayList();
        }

    }
;

    static 
    {
        sVmPolicy = VmPolicy.LAX;
    }
}
