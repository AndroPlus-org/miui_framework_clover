// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.logging;

import android.util.Log;
import com.android.internal.util.FastPrintWriter;
import dalvik.system.DalvikLogHandler;
import dalvik.system.DalvikLogging;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.*;

public class AndroidHandler extends Handler
    implements DalvikLogHandler
{

    public AndroidHandler()
    {
        setFormatter(THE_FORMATTER);
    }

    static int getAndroidLevel(Level level)
    {
        int i = level.intValue();
        if(i >= 1000)
            return 6;
        if(i >= 900)
            return 5;
        return i < 800 ? 3 : 4;
    }

    public void close()
    {
    }

    public void flush()
    {
    }

    public void publish(LogRecord logrecord)
    {
        int i;
        String s;
        i = getAndroidLevel(logrecord.getLevel());
        s = DalvikLogging.loggerNameToTag(logrecord.getLoggerName());
        if(!Log.isLoggable(s, i))
            return;
        Log.println(i, s, getFormatter().format(logrecord));
_L1:
        return;
        logrecord;
        Log.e("AndroidHandler", "Error logging message.", logrecord);
          goto _L1
    }

    public void publish(Logger logger, String s, Level level, String s1)
    {
        int i;
        i = getAndroidLevel(level);
        if(!Log.isLoggable(s, i))
            return;
        Log.println(i, s, s1);
_L1:
        return;
        logger;
        Log.e("AndroidHandler", "Error logging message.", logger);
          goto _L1
    }

    private static final Formatter THE_FORMATTER = new Formatter() {

        public String format(LogRecord logrecord)
        {
            Throwable throwable = logrecord.getThrown();
            if(throwable != null)
            {
                StringWriter stringwriter = new StringWriter();
                FastPrintWriter fastprintwriter = new FastPrintWriter(stringwriter, false, 256);
                stringwriter.write(logrecord.getMessage());
                stringwriter.write("\n");
                throwable.printStackTrace(fastprintwriter);
                fastprintwriter.flush();
                return stringwriter.toString();
            } else
            {
                return logrecord.getMessage();
            }
        }

    }
;

}
