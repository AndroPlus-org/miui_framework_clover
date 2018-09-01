// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.text.TextUtils;
import android.util.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import miui.util.ReflectionUtils;

// Referenced classes of package android.os:
//            ILooperMonitorable, AnrMonitor, Message, SystemClock, 
//            MessageQueue, Parcelable, Parcel, Process

public class BaseLooper
    implements ILooperMonitorable
{
    static class MessageMonitorInfo
        implements Parcelable, Cloneable
    {

        String createMonitorDigest()
        {
            monitorDigest = (new StringBuilder()).append("").append(Process.myTid()).append(getPlanTime()).append(getDispatchTime()).toString();
            return monitorDigest;
        }

        public int describeContents()
        {
            return 0;
        }

        long getDispatchTime()
        {
            return dispatchTime;
        }

        long getFinishTime()
        {
            return finishTime;
        }

        String getMonitorDigest()
        {
            return monitorDigest;
        }

        String getMonitorMessage()
        {
            return monitorMessage;
        }

        long getPlanTime()
        {
            return planTime;
        }

        long getTookTime()
        {
            if(finishTime > 0L)
                return finishTime - planTime;
            else
                return 0L;
        }

        long getTookTimeAfterDispatch()
        {
            if(finishTime > 0L)
                return finishTime - dispatchTime;
            else
                return 0L;
        }

        long getTookTimeBeforeDispatch()
        {
            if(dispatchTime > 0L)
                return dispatchTime - planTime;
            else
                return 0L;
        }

        void initMessageTime(long l)
        {
            long l1 = SystemClock.uptimeMillis();
            planTime = System.currentTimeMillis() + Math.max(l - l1, 0L);
        }

        void reset()
        {
            planTime = 0L;
            dispatchTime = 0L;
            finishTime = 0L;
            monitorDigest = null;
            monitorMessage = null;
        }

        void setMonitorMessage(Message message)
        {
            monitorMessage = BaseLooper.getMessageString(message);
        }

        public String toString()
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append((new StringBuilder()).append(" planTime=").append(planTime).toString());
            stringbuffer.append((new StringBuilder()).append(" dispatchTime=").append(dispatchTime).toString());
            stringbuffer.append((new StringBuilder()).append(" finishTime=").append(finishTime).toString());
            return stringbuffer.toString();
        }

        void updateMessageTimeByState(int i)
        {
            i;
            JVM INSTR tableswitch 1 2: default 24
        //                       1 25
        //                       2 35;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            dispatchTime = System.currentTimeMillis();
            continue; /* Loop/switch isn't completed */
_L3:
            finishTime = System.currentTimeMillis();
            if(true) goto _L1; else goto _L4
_L4:
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeLong(planTime);
            parcel.writeLong(dispatchTime);
            parcel.writeLong(finishTime);
            parcel.writeString(monitorDigest);
            parcel.writeString(monitorMessage);
        }

        public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

            public MessageMonitorInfo createFromParcel(Parcel parcel)
            {
                return new MessageMonitorInfo(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public MessageMonitorInfo[] newArray(int i)
            {
                return new MessageMonitorInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        long dispatchTime;
        long finishTime;
        String monitorDigest;
        String monitorMessage;
        long planTime;


        public MessageMonitorInfo()
        {
        }

        public MessageMonitorInfo(Parcel parcel)
        {
            planTime = parcel.readLong();
            dispatchTime = parcel.readLong();
            finishTime = parcel.readLong();
            monitorDigest = parcel.readString();
            monitorMessage = parcel.readString();
        }
    }


    public BaseLooper()
    {
        mSummaryHistoryNext = 0;
    }

    private void addMessageToHistoryIfNeed(Message message, MessageMonitorInfo messagemonitorinfo)
    {
        if(!AnrMonitor.isLongTimeMsg(messagemonitorinfo))
            return;
        message = ((Message) (mMsgLock));
        message;
        JVM INSTR monitorenter ;
        String s;
        boolean flag;
        s = messagemonitorinfo.getMonitorMessage();
        flag = TextUtils.isEmpty(s);
        if(!flag)
            break MISSING_BLOCK_LABEL_34;
        message;
        JVM INSTR monitorexit ;
        return;
        mMessageHistory[mSummaryHistoryNext] = s;
        mSummaryHistoryEnqueueTime[mSummaryHistoryNext] = messagemonitorinfo.planTime;
        mSummaryHistoryDispatchTime[mSummaryHistoryNext] = messagemonitorinfo.dispatchTime;
        mSummaryHistoryFinishTime[mSummaryHistoryNext] = messagemonitorinfo.finishTime;
        mSummaryHistoryNext = ringAdvance(mSummaryHistoryNext, 1, 50);
        message;
        JVM INSTR monitorexit ;
        return;
        messagemonitorinfo;
        throw messagemonitorinfo;
    }

    private void dumpInternal(PrintWriter printwriter, String s, long l, boolean flag)
    {
        if(isMonitorLooper() && !(AnrMonitor.canMonitorAnr() ^ true)) goto _L2; else goto _L1
_L1:
        if(flag)
            dumpMessageQueue(getQueue(), new PrintWriterPrinter(printwriter), s);
_L7:
        return;
_L2:
        Object obj = sCallbacksLock;
        obj;
        JVM INSTR monitorenter ;
        int i = 0;
        int j = sCallbacks.size();
_L5:
        if(i >= j) goto _L4; else goto _L3
_L3:
        BaseLooper baselooper = (BaseLooper)((WeakReference)sCallbacks.get(i)).get();
        if(baselooper == null)
            continue; /* Loop/switch isn't completed */
        baselooper.dumpLtMessageHistory(printwriter, l);
        if(!flag)
            continue; /* Loop/switch isn't completed */
        MessageQueue messagequeue = getQueue();
        PrintWriterPrinter printwriterprinter = JVM INSTR new #115 <Class PrintWriterPrinter>;
        printwriterprinter.PrintWriterPrinter(printwriter);
        baselooper.dumpMessageQueue(messagequeue, printwriterprinter, s);
        i++;
          goto _L5
_L4:
        obj;
        JVM INSTR monitorexit ;
        if(true) goto _L7; else goto _L6
_L6:
        printwriter;
        throw printwriter;
    }

    public static String getMessageString(Message message)
    {
        String s = "";
        try
        {
            message = message.toString();
        }
        // Misplaced declaration of an exception variable
        catch(Message message)
        {
            Log.e("BaseLooper", (new StringBuilder()).append("getMessageString failed ! ").append(message.getMessage()).toString());
            message = s;
        }
        return message;
    }

    private int ringAdvance(int i, int j, int k)
    {
        i += j;
        if(i < 0)
            return k - 1;
        if(i >= k)
            return 0;
        else
            return i;
    }

    private void updateCallbackIfNeed(boolean flag)
    {
        Object obj = sCallbacksLock;
        obj;
        JVM INSTR monitorenter ;
        byte byte0;
        int i;
        byte0 = -1;
        i = 0;
        int j = sCallbacks.size();
_L10:
        int k = byte0;
        if(i >= j) goto _L2; else goto _L1
_L1:
        if(((WeakReference)sCallbacks.get(i)).get() != this) goto _L4; else goto _L3
_L3:
        k = i;
_L2:
        if(!flag || k != -1) goto _L6; else goto _L5
_L5:
        ArrayList arraylist = sCallbacks;
        WeakReference weakreference = JVM INSTR new #132 <Class WeakReference>;
        weakreference.WeakReference(this);
        arraylist.add(weakreference);
_L8:
        obj;
        JVM INSTR monitorexit ;
        return;
_L4:
        i++;
        continue; /* Loop/switch isn't completed */
_L6:
        if(flag || k == -1) goto _L8; else goto _L7
_L7:
        sCallbacks.remove(k);
          goto _L8
        Exception exception;
        exception;
        throw exception;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public String dumpAll(String s)
    {
        return dumpAll(s, 10000L);
    }

    public String dumpAll(String s, long l)
    {
        long l1 = SystemClock.uptimeMillis();
        StringWriter stringwriter = new StringWriter();
        PrintWriter printwriter = new PrintWriter(stringwriter);
        printwriter.println((new StringBuilder()).append(s).append(" package ").append(AnrMonitor.currentPackageName()).append(" version Code: ").append(AnrMonitor.currentVersionCode()).append(" version Name: ").append(AnrMonitor.currentVersionName()).append(" cur loop is : ").append(toString()).toString());
        dumpInternal(printwriter, s, l, true);
        printwriter.flush();
        Log.d("BaseLooper", (new StringBuilder()).append("dump anr message took ").append(SystemClock.uptimeMillis() - l1).append("ms").toString());
        return stringwriter.toString();
    }

    public void dumpHistoryMessage(Printer printer, long l)
    {
        StringWriter stringwriter = new StringWriter();
        PrintWriter printwriter = new PrintWriter(stringwriter);
        dumpInternal(printwriter, "history message", l, false);
        printwriter.flush();
        printer.println(stringwriter.toString());
    }

    public final void dumpLtMessageHistory(PrintWriter printwriter, long l)
    {
        Object obj = mMsgLock;
        obj;
        JVM INSTR monitorenter ;
        SimpleDateFormat simpledateformat;
        long l1;
        int i;
        simpledateformat = JVM INSTR new #252 <Class SimpleDateFormat>;
        simpledateformat.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
        l1 = System.currentTimeMillis();
        StringBuilder stringbuilder = JVM INSTR new #152 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        stringbuilder = stringbuilder.append("Dump time : ");
        Date date = JVM INSTR new #265 <Class Date>;
        date.Date(l1);
        printwriter.println(stringbuilder.append(simpledateformat.format(date)).toString());
        stringbuilder = JVM INSTR new #152 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        printwriter.println(stringbuilder.append("---------- History of long time messages on ").append(toString()).append("----------").toString());
        i = mSummaryHistoryNext;
        int j;
        int k;
        j = i;
        k = -1;
_L2:
        Object obj1;
        int i1;
        i1 = ringAdvance(j, -1, 50);
        obj1 = mMessageHistory[i1];
        if(!TextUtils.isEmpty(((CharSequence) (obj1))) && l1 - mSummaryHistoryFinishTime[i1] <= l)
            break MISSING_BLOCK_LABEL_182;
        printwriter.println("-------------------------- END --------------------------");
        obj;
        JVM INSTR monitorexit ;
        return;
        k++;
        printwriter.print("#");
        printwriter.print(k);
        printwriter.print(": ");
        printwriter.println(((String) (obj1)));
        long l2 = mSummaryHistoryEnqueueTime[i1];
        long l3 = mSummaryHistoryDispatchTime[i1];
        long l4 = mSummaryHistoryFinishTime[i1];
        printwriter.print("    Total: ");
        TimeUtils.formatDuration(l4, l2, printwriter);
        printwriter.print(" Waiting: ");
        TimeUtils.formatDuration(l3, l2, printwriter);
        printwriter.print(" Processing: ");
        TimeUtils.formatDuration(l4, l3, printwriter);
        printwriter.println();
        printwriter.print("    enq=");
        obj1 = JVM INSTR new #265 <Class Date>;
        ((Date) (obj1)).Date(l2);
        printwriter.print(simpledateformat.format(((Date) (obj1))));
        printwriter.print(" disp=");
        obj1 = JVM INSTR new #265 <Class Date>;
        ((Date) (obj1)).Date(l3);
        printwriter.print(simpledateformat.format(((Date) (obj1))));
        printwriter.print(" fin=");
        obj1 = JVM INSTR new #265 <Class Date>;
        ((Date) (obj1)).Date(l4);
        printwriter.println(simpledateformat.format(((Date) (obj1))));
        j = i1;
        if(i1 != i) goto _L2; else goto _L1
_L1:
        printwriter.println("-------------------------- END --------------------------");
        obj;
        JVM INSTR monitorexit ;
        return;
        printwriter;
        throw printwriter;
    }

    public void dumpMessageQueue(MessageQueue messagequeue, Printer printer, String s)
    {
        printer.println((new StringBuilder()).append("---------- Dump MessageQueue on ").append(toString()).append("----------").toString());
        if(mRunningMessage != null)
            printer.println((new StringBuilder()).append("Running message is ").append(mRunningMessage).toString());
        if(messagequeue != null)
            messagequeue.dump(printer, s, null);
        printer.println("-------------------------- END --------------------------");
    }

    public void enableMonitor(boolean flag)
    {
        mEnableMonitor = flag;
        if(flag && getQueue() != null)
            ReflectionUtils.tryCallMethod(getQueue(), "enableMonitor", java/lang/Void, new Object[0]);
        updateCallbackIfNeed(flag);
    }

    public MessageQueue getQueue()
    {
        return null;
    }

    public boolean isMonitorLooper()
    {
        return mEnableMonitor;
    }

    protected final void updateMessageByState(Message message, MessageMonitorInfo messagemonitorinfo, int i)
    {
        if(!AnrMonitor.canMonitorAnr() || mEnableMonitor ^ true)
            return;
        i;
        JVM INSTR tableswitch 1 2: default 40
    //                   1 41
    //                   2 64;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        messagemonitorinfo.updateMessageTimeByState(i);
        messagemonitorinfo.setMonitorMessage(message);
        mRunningMessage = message;
        AnrMonitor.startMonitor(message, messagemonitorinfo);
        continue; /* Loop/switch isn't completed */
_L3:
        mRunningMessage = null;
        messagemonitorinfo.updateMessageTimeByState(i);
        AnrMonitor.finishMonitor(message, messagemonitorinfo);
        AnrMonitor.checkMsgTime(message, messagemonitorinfo);
        addMessageToHistoryIfNeed(message, messagemonitorinfo);
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static final int MAX_MESSAGE_SUMMARY_HISTORY = 50;
    protected static final int STATE_DISPATCH = 1;
    protected static final int STATE_ENQUEUE = 0;
    protected static final int STATE_FINISH = 2;
    private static final String TAG = "BaseLooper";
    private static final ArrayList sCallbacks = new ArrayList();
    private static final Object sCallbacksLock = new Object();
    private boolean mEnableMonitor;
    private final String mMessageHistory[] = new String[50];
    private final Object mMsgLock = new Object();
    private Message mRunningMessage;
    private final long mSummaryHistoryDispatchTime[] = new long[50];
    private final long mSummaryHistoryEnqueueTime[] = new long[50];
    private final long mSummaryHistoryFinishTime[] = new long[50];
    private int mSummaryHistoryNext;

}
