// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.os.DeadSystemException;
import com.android.internal.os.RuntimeInit;
import com.android.internal.util.FastPrintWriter;
import com.android.internal.util.LineBreakBufferedWriter;
import java.io.*;
import java.net.UnknownHostException;

public final class Log
{
    private static class ImmediateLogWriter extends Writer
    {

        public void close()
        {
        }

        public void flush()
        {
        }

        public int getWritten()
        {
            return written;
        }

        public void write(char ac[], int j, int k)
        {
            written = written + Log.println_native(bufID, priority, tag, new String(ac, j, k));
        }

        private int bufID;
        private int priority;
        private String tag;
        private int written;

        public ImmediateLogWriter(int j, int k, String s)
        {
            written = 0;
            bufID = j;
            priority = k;
            tag = s;
        }
    }

    static class PreloadHolder
    {

        public static final int LOGGER_ENTRY_MAX_PAYLOAD = Log._2D_wrap0();


        PreloadHolder()
        {
        }
    }

    public static class TerribleFailure extends Exception
    {

        TerribleFailure(String s, Throwable throwable)
        {
            super(s, throwable);
        }
    }

    public static interface TerribleFailureHandler
    {

        public abstract void onTerribleFailure(String s, TerribleFailure terriblefailure, boolean flag);
    }


    static int _2D_wrap0()
    {
        return logger_entry_max_payload_native();
    }

    private Log()
    {
    }

    public static int d(String s, String s1)
    {
        return println_native(0, 3, s, s1);
    }

    public static int d(String s, String s1, Throwable throwable)
    {
        return printlns(0, 3, s, s1, throwable);
    }

    public static int e(String s, String s1)
    {
        return println_native(0, 6, s, s1);
    }

    public static int e(String s, String s1, Throwable throwable)
    {
        return printlns(0, 6, s, s1, throwable);
    }

    public static String getStackTraceString(Throwable throwable)
    {
        if(throwable == null)
            return "";
        for(Throwable throwable1 = throwable; throwable1 != null; throwable1 = throwable1.getCause())
            if(throwable1 instanceof UnknownHostException)
                return "";

        StringWriter stringwriter = new StringWriter();
        FastPrintWriter fastprintwriter = new FastPrintWriter(stringwriter, false, 256);
        throwable.printStackTrace(fastprintwriter);
        fastprintwriter.flush();
        return stringwriter.toString();
    }

    public static int i(String s, String s1)
    {
        return println_native(0, 4, s, s1);
    }

    public static int i(String s, String s1, Throwable throwable)
    {
        return printlns(0, 4, s, s1, throwable);
    }

    public static native boolean isLoggable(String s, int j);

    private static native int logger_entry_max_payload_native();

    public static int println(int j, String s, String s1)
    {
        return println_native(0, j, s, s1);
    }

    public static native int println_native(int j, int k, String s, String s1);

    public static int printlns(int j, int k, String s, String s1, Throwable throwable)
    {
        LineBreakBufferedWriter linebreakbufferedwriter;
        ImmediateLogWriter immediatelogwriter = new ImmediateLogWriter(j, k, s);
        k = PreloadHolder.LOGGER_ENTRY_MAX_PAYLOAD;
        if(s != null)
            j = s.length();
        else
            j = 0;
        linebreakbufferedwriter = new LineBreakBufferedWriter(immediatelogwriter, Math.max(k - 2 - j - 32, 100));
        linebreakbufferedwriter.println(s1);
        if(throwable == null) goto _L2; else goto _L1
_L1:
        s = throwable;
_L7:
        if(s != null && !(s instanceof UnknownHostException)) goto _L4; else goto _L3
_L3:
        if(s == null)
            throwable.printStackTrace(linebreakbufferedwriter);
_L2:
        linebreakbufferedwriter.flush();
        return immediatelogwriter.getWritten();
_L4:
        if(!(s instanceof DeadSystemException))
            break; /* Loop/switch isn't completed */
        linebreakbufferedwriter.println("DeadSystemException: The system died; earlier logs will point to the root cause");
        if(true) goto _L3; else goto _L5
_L5:
        s = s.getCause();
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static TerribleFailureHandler setWtfHandler(TerribleFailureHandler terriblefailurehandler)
    {
        if(terriblefailurehandler == null)
        {
            throw new NullPointerException("handler == null");
        } else
        {
            TerribleFailureHandler terriblefailurehandler1 = sWtfHandler;
            sWtfHandler = terriblefailurehandler;
            return terriblefailurehandler1;
        }
    }

    public static int v(String s, String s1)
    {
        return println_native(0, 2, s, s1);
    }

    public static int v(String s, String s1, Throwable throwable)
    {
        return printlns(0, 2, s, s1, throwable);
    }

    public static int w(String s, String s1)
    {
        return println_native(0, 5, s, s1);
    }

    public static int w(String s, String s1, Throwable throwable)
    {
        return printlns(0, 5, s, s1, throwable);
    }

    public static int w(String s, Throwable throwable)
    {
        return printlns(0, 5, s, "", throwable);
    }

    public static int ws(String s, String s1)
    {
        return println_native(5, 2, s, s1);
    }

    static int wtf(int j, String s, String s1, Throwable throwable, boolean flag, boolean flag1)
    {
        TerribleFailure terriblefailure = new TerribleFailure(s1, throwable);
        if(flag)
            throwable = terriblefailure;
        j = printlns(j, 6, s, s1, throwable);
        sWtfHandler.onTerribleFailure(s, terriblefailure, flag1);
        return j;
    }

    public static int wtf(String s, String s1)
    {
        return wtf(0, s, s1, null, false, false);
    }

    public static int wtf(String s, String s1, Throwable throwable)
    {
        return wtf(0, s, s1, throwable, false, false);
    }

    public static int wtf(String s, Throwable throwable)
    {
        return wtf(0, s, throwable.getMessage(), throwable, false, false);
    }

    static void wtfQuiet(int j, String s, String s1, boolean flag)
    {
        s1 = new TerribleFailure(s1, null);
        sWtfHandler.onTerribleFailure(s, s1, flag);
    }

    public static int wtfStack(String s, String s1)
    {
        return wtf(0, s, s1, null, true, false);
    }

    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int LOG_ID_CRASH = 4;
    public static final int LOG_ID_EVENTS = 2;
    public static final int LOG_ID_MAIN = 0;
    public static final int LOG_ID_RADIO = 1;
    public static final int LOG_ID_SYSTEM = 3;
    public static final int LOG_ID_WSEVENTS = 5;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static TerribleFailureHandler sWtfHandler = new TerribleFailureHandler() {

        public void onTerribleFailure(String s, TerribleFailure terriblefailure, boolean flag)
        {
            RuntimeInit.wtf(s, terriblefailure, flag);
        }

    }
;

}
