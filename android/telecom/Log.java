// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.telecom.Logging.EventManager;
import android.telecom.Logging.Session;
import android.telecom.Logging.SessionManager;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Slog;
import com.android.internal.util.IndentingPrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.IllegalFormatException;
import java.util.Locale;
import miui.telephony.PhoneDebug;

public class Log
{

    static String _2D_android_telecom_Log_2D_mthref_2D_0()
    {
        return getSessionId();
    }

    static MessageDigest _2D_set0(MessageDigest messagedigest)
    {
        sMessageDigest = messagedigest;
        return messagedigest;
    }

    private Log()
    {
    }

    public static void addEvent(android.telecom.Logging.EventManager.Loggable loggable, String s)
    {
        getEventManager().event(loggable, s, null);
    }

    public static void addEvent(android.telecom.Logging.EventManager.Loggable loggable, String s, Object obj)
    {
        getEventManager().event(loggable, s, obj);
    }

    public static transient void addEvent(android.telecom.Logging.EventManager.Loggable loggable, String s, String s1, Object aobj[])
    {
        getEventManager().event(loggable, s, s1, aobj);
    }

    public static void addRequestResponsePair(android.telecom.Logging.EventManager.TimedEventPair timedeventpair)
    {
        getEventManager().addRequestResponsePair(timedeventpair);
    }

    private static transient String buildMessage(String s, String s1, Object aobj[])
    {
        String s2 = getSessionId();
        int j;
        if(TextUtils.isEmpty(s2))
            s2 = "";
        else
            s2 = (new StringBuilder()).append(": ").append(s2).toString();
        if(aobj == null) goto _L2; else goto _L1
_L1:
        j = aobj.length;
        if(j != 0) goto _L3; else goto _L2
_L2:
        return String.format(Locale.US, "%s: %s%s", new Object[] {
            s, s1, s2
        });
_L3:
        String s3 = String.format(Locale.US, s1, aobj);
        s1 = s3;
        continue; /* Loop/switch isn't completed */
        IllegalFormatException illegalformatexception;
        illegalformatexception;
        e(TAG, illegalformatexception, "Log: IllegalFormatException: formatString='%s' numArgs=%d", new Object[] {
            s1, Integer.valueOf(aobj.length)
        });
        s1 = (new StringBuilder()).append(s1).append(" (An error occurred while formatting the message.)").toString();
        if(true) goto _L2; else goto _L4
_L4:
    }

    public static void cancelSubsession(Session session)
    {
        getSessionManager().cancelSubsession(session);
    }

    public static void continueSession(Session session, String s)
    {
        getSessionManager().continueSession(session, s);
    }

    public static Session createSubsession()
    {
        return getSessionManager().createSubsession();
    }

    public static transient void d(Object obj, String s, Object aobj[])
    {
        if(!sIsUserExtendedLoggingEnabled) goto _L2; else goto _L1
_L1:
        maybeDisableLogging();
        Slog.i(TAG, buildMessage(getPrefixFromObject(obj), s, aobj));
_L4:
        return;
_L2:
        if(DEBUG)
            Slog.d(TAG, buildMessage(getPrefixFromObject(obj), s, aobj));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static transient void d(String s, String s1, Object aobj[])
    {
        if(!sIsUserExtendedLoggingEnabled) goto _L2; else goto _L1
_L1:
        maybeDisableLogging();
        Slog.i(TAG, buildMessage(s, s1, aobj));
_L4:
        return;
_L2:
        if(DEBUG)
            Slog.d(TAG, buildMessage(s, s1, aobj));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static void dumpEvents(IndentingPrintWriter indentingprintwriter)
    {
        Object obj = sSingletonSync;
        obj;
        JVM INSTR monitorenter ;
        if(sEventManager == null)
            break MISSING_BLOCK_LABEL_22;
        getEventManager().dumpEvents(indentingprintwriter);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        indentingprintwriter.println("No Historical Events Logged.");
          goto _L1
        indentingprintwriter;
        throw indentingprintwriter;
    }

    public static void dumpEventsTimeline(IndentingPrintWriter indentingprintwriter)
    {
        Object obj = sSingletonSync;
        obj;
        JVM INSTR monitorenter ;
        if(sEventManager == null)
            break MISSING_BLOCK_LABEL_22;
        getEventManager().dumpEventsTimeline(indentingprintwriter);
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
        indentingprintwriter.println("No Historical Events Logged.");
          goto _L1
        indentingprintwriter;
        throw indentingprintwriter;
    }

    public static transient void e(Object obj, Throwable throwable, String s, Object aobj[])
    {
        if(ERROR)
            Slog.e(TAG, buildMessage(getPrefixFromObject(obj), s, aobj), throwable);
    }

    public static transient void e(String s, Throwable throwable, String s1, Object aobj[])
    {
        if(ERROR)
            Slog.e(TAG, buildMessage(s, s1, aobj), throwable);
    }

    private static String encodeHex(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer(abyte0.length * 2);
        for(int j = 0; j < abyte0.length; j++)
        {
            int k = abyte0[j] & 0xff;
            if(k < 16)
                stringbuffer.append("0");
            stringbuffer.append(Integer.toString(k, 16));
        }

        return stringbuffer.toString();
    }

    public static void endSession()
    {
        getSessionManager().endSession();
    }

    private static EventManager getEventManager()
    {
        if(sEventManager != null) goto _L2; else goto _L1
_L1:
        Object obj = sSingletonSync;
        obj;
        JVM INSTR monitorenter ;
        EventManager eventmanager;
        if(sEventManager != null)
            break MISSING_BLOCK_LABEL_41;
        eventmanager = JVM INSTR new #97  <Class EventManager>;
        eventmanager.EventManager(_.Lambda.afyb_ODGzn3xMew6fjs8ANSIdVo.$INST$0);
        sEventManager = eventmanager;
        eventmanager = sEventManager;
        obj;
        JVM INSTR monitorexit ;
        return eventmanager;
        obj;
        JVM INSTR monitorexit ;
_L2:
        return sEventManager;
        Exception exception;
        exception;
        throw exception;
    }

    public static android.telecom.Logging.Session.Info getExternalSession()
    {
        return getSessionManager().getExternalSession();
    }

    private static String getPrefixFromObject(Object obj)
    {
        if(obj == null)
            obj = "<null>";
        else
            obj = obj.getClass().getSimpleName();
        return ((String) (obj));
    }

    public static String getSessionId()
    {
        Object obj = sSingletonSync;
        obj;
        JVM INSTR monitorenter ;
        String s;
        if(sSessionManager == null)
            break MISSING_BLOCK_LABEL_23;
        s = getSessionManager().getSessionId();
        return s;
        obj;
        JVM INSTR monitorexit ;
        return "";
        Exception exception;
        exception;
        throw exception;
    }

    public static SessionManager getSessionManager()
    {
        if(sSessionManager != null) goto _L2; else goto _L1
_L1:
        Object obj = sSingletonSync;
        obj;
        JVM INSTR monitorenter ;
        SessionManager sessionmanager;
        if(sSessionManager != null)
            break MISSING_BLOCK_LABEL_38;
        sessionmanager = JVM INSTR new #168 <Class SessionManager>;
        sessionmanager.SessionManager();
        sSessionManager = sessionmanager;
        sessionmanager = sSessionManager;
        obj;
        JVM INSTR monitorexit ;
        return sessionmanager;
        obj;
        JVM INSTR monitorexit ;
_L2:
        return sSessionManager;
        Exception exception;
        exception;
        throw exception;
    }

    public static transient void i(Object obj, String s, Object aobj[])
    {
        if(INFO)
            Slog.i(TAG, buildMessage(getPrefixFromObject(obj), s, aobj));
    }

    public static transient void i(String s, String s1, Object aobj[])
    {
        if(INFO)
            Slog.i(TAG, buildMessage(s, s1, aobj));
    }

    public static void initMd5Sum()
    {
        (new AsyncTask() {

            public volatile Object doInBackground(Object aobj[])
            {
                return doInBackground((Void[])aobj);
            }

            public transient Void doInBackground(Void avoid[])
            {
                try
                {
                    avoid = MessageDigest.getInstance("SHA-1");
                }
                // Misplaced declaration of an exception variable
                catch(Void avoid[])
                {
                    avoid = null;
                }
                Log._2D_set0(avoid);
                return null;
            }

        }
).execute(new Void[0]);
    }

    public static boolean isLoggable(int j)
    {
        boolean flag;
        if(!FORCE_LOGGING)
            flag = android.util.Log.isLoggable(TAG, j);
        else
            flag = true;
        return flag;
    }

    private static void maybeDisableLogging()
    {
        if(!sIsUserExtendedLoggingEnabled)
            return;
        if(sUserExtendedLoggingStopTime < System.currentTimeMillis())
        {
            sUserExtendedLoggingStopTime = 0L;
            sIsUserExtendedLoggingEnabled = false;
        }
    }

    public static String pii(Object obj)
    {
        if(obj == null || android.util.Log.isLoggable(TAG, 2))
            return String.valueOf(obj);
        else
            return (new StringBuilder()).append("[").append(secureHash(String.valueOf(obj).getBytes())).append("]").toString();
    }

    public static String piiHandle(Object obj)
    {
        if(obj == null || android.util.Log.isLoggable(TAG, 2))
            return String.valueOf(obj);
        StringBuilder stringbuilder = new StringBuilder();
        if(obj instanceof Uri)
        {
            Object obj1 = (Uri)obj;
            String s = ((Uri) (obj1)).getScheme();
            if(!TextUtils.isEmpty(s))
                stringbuilder.append(s).append(":");
            obj1 = ((Uri) (obj1)).getSchemeSpecificPart();
            if("tel".equals(s))
            {
                int j = 0;
                while(j < ((String) (obj1)).length()) 
                {
                    char c = ((String) (obj1)).charAt(j);
                    if(PhoneNumberUtils.isDialable(c))
                        obj = "*";
                    else
                        obj = Character.valueOf(c);
                    stringbuilder.append(obj);
                    j++;
                }
            } else
            if("sip".equals(s))
            {
                for(int k = 0; k < ((String) (obj1)).length(); k++)
                {
                    char c2 = ((String) (obj1)).charAt(k);
                    char c1 = c2;
                    if(c2 != '@')
                    {
                        c1 = c2;
                        if(c2 != '.')
                        {
                            byte byte0 = 42;
                            c1 = byte0;
                        }
                    }
                    stringbuilder.append(c1);
                }

            } else
            {
                stringbuilder.append(pii(obj));
            }
        }
        return stringbuilder.toString();
    }

    public static void registerEventListener(android.telecom.Logging.EventManager.EventListener eventlistener)
    {
        getEventManager().registerEventListener(eventlistener);
    }

    public static void registerSessionListener(android.telecom.Logging.SessionManager.ISessionListener isessionlistener)
    {
        getSessionManager().registerSessionListener(isessionlistener);
    }

    private static String secureHash(byte abyte0[])
    {
        if(USER_BUILD)
            return "****";
        if(sMessageDigest != null)
        {
            sMessageDigest.reset();
            sMessageDigest.update(abyte0);
            return encodeHex(sMessageDigest.digest());
        } else
        {
            return "Uninitialized SHA1";
        }
    }

    public static void setIsExtendedLoggingEnabled(boolean flag)
    {
        if(sIsUserExtendedLoggingEnabled == flag)
            return;
        if(sEventManager != null)
        {
            EventManager eventmanager = sEventManager;
            byte byte0;
            if(flag)
                byte0 = 20;
            else
                byte0 = 10;
            eventmanager.changeEventCacheSize(byte0);
        }
        sIsUserExtendedLoggingEnabled = flag;
        if(sIsUserExtendedLoggingEnabled)
            sUserExtendedLoggingStopTime = System.currentTimeMillis() + 0x1b7740L;
        else
            sUserExtendedLoggingStopTime = 0L;
    }

    public static void setSessionContext(Context context)
    {
        getSessionManager().setContext(context);
    }

    public static void setTag(String s)
    {
        TAG = s;
        DEBUG = isLoggable(3);
        INFO = isLoggable(4);
        VERBOSE = isLoggable(2);
        WARN = isLoggable(5);
        ERROR = isLoggable(6);
    }

    public static void startSession(android.telecom.Logging.Session.Info info, String s)
    {
        getSessionManager().startSession(info, s, null);
    }

    public static void startSession(android.telecom.Logging.Session.Info info, String s, String s1)
    {
        getSessionManager().startSession(info, s, s1);
    }

    public static void startSession(String s)
    {
        getSessionManager().startSession(s, null);
    }

    public static void startSession(String s, String s1)
    {
        getSessionManager().startSession(s, s1);
    }

    public static transient void v(Object obj, String s, Object aobj[])
    {
        if(!sIsUserExtendedLoggingEnabled) goto _L2; else goto _L1
_L1:
        maybeDisableLogging();
        Slog.i(TAG, buildMessage(getPrefixFromObject(obj), s, aobj));
_L4:
        return;
_L2:
        if(VERBOSE)
            Slog.v(TAG, buildMessage(getPrefixFromObject(obj), s, aobj));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static transient void v(String s, String s1, Object aobj[])
    {
        if(!sIsUserExtendedLoggingEnabled) goto _L2; else goto _L1
_L1:
        maybeDisableLogging();
        Slog.i(TAG, buildMessage(s, s1, aobj));
_L4:
        return;
_L2:
        if(VERBOSE)
            Slog.v(TAG, buildMessage(s, s1, aobj));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static transient void w(Object obj, String s, Object aobj[])
    {
        if(WARN)
            Slog.w(TAG, buildMessage(getPrefixFromObject(obj), s, aobj));
    }

    public static transient void w(String s, String s1, Object aobj[])
    {
        if(WARN)
            Slog.w(TAG, buildMessage(s, s1, aobj));
    }

    public static transient void wtf(Object obj, String s, Object aobj[])
    {
        obj = buildMessage(getPrefixFromObject(obj), s, aobj);
        Slog.wtf(TAG, ((String) (obj)), new IllegalStateException(((String) (obj))));
    }

    public static transient void wtf(Object obj, Throwable throwable, String s, Object aobj[])
    {
        Slog.wtf(TAG, buildMessage(getPrefixFromObject(obj), s, aobj), throwable);
    }

    public static transient void wtf(String s, String s1, Object aobj[])
    {
        s = buildMessage(s, s1, aobj);
        Slog.wtf(TAG, s, new IllegalStateException(s));
    }

    public static transient void wtf(String s, Throwable throwable, String s1, Object aobj[])
    {
        Slog.wtf(TAG, buildMessage(s, s1, aobj), throwable);
    }

    public static boolean DEBUG = false;
    public static boolean ERROR = false;
    private static final int EVENTS_TO_CACHE = 10;
    private static final int EVENTS_TO_CACHE_DEBUG = 20;
    private static final long EXTENDED_LOGGING_DURATION_MILLIS = 0x1b7740L;
    private static final boolean FORCE_LOGGING;
    public static boolean INFO = isLoggable(4);
    public static String TAG = "TelecomFramework";
    private static final boolean USER_BUILD;
    public static boolean VERBOSE;
    public static boolean WARN = isLoggable(5);
    private static EventManager sEventManager;
    private static boolean sIsUserExtendedLoggingEnabled = false;
    private static MessageDigest sMessageDigest;
    private static SessionManager sSessionManager;
    private static final Object sSingletonSync = new Object();
    private static long sUserExtendedLoggingStopTime = 0L;

    static 
    {
        boolean flag = true;
        boolean flag1;
        if(!isLoggable(3))
            flag1 = PhoneDebug.VDBG;
        else
            flag1 = true;
        DEBUG = flag1;
        flag1 = flag;
        if(!isLoggable(2))
            flag1 = PhoneDebug.VDBG;
        VERBOSE = flag1;
        ERROR = isLoggable(6);
        FORCE_LOGGING = PhoneDebug.VDBG;
        USER_BUILD = Build.IS_USER;
    }
}
