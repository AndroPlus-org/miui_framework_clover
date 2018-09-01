// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom.Logging;

import android.content.Context;
import android.os.*;
import android.telecom.Log;
import android.util.Base64;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package android.telecom.Logging:
//            Session

public class SessionManager
{
    public static interface ICurrentThreadId
    {

        public abstract int get();
    }

    private static interface ISessionCleanupTimeoutMs
    {

        public abstract long get();
    }

    public static interface ISessionIdQueryHandler
    {

        public abstract String getSessionId();
    }

    public static interface ISessionListener
    {

        public abstract void sessionComplete(String s, long l);
    }


    static int _2D_android_telecom_Logging_SessionManager_2D_mthref_2D_0()
    {
        return Process.myTid();
    }

    public SessionManager()
    {
        sCodeEntryCounter = 0;
        mSessionMapper = new ConcurrentHashMap(100);
        mCleanStaleSessions = new _.Lambda.OwO3BlCgqcOx28O1BaOAPVPor24._cls2(this);
        mSessionCleanupHandler = new Handler(Looper.getMainLooper());
        mCurrentThreadId = _.Lambda.OwO3BlCgqcOx28O1BaOAPVPor24.$INST$0;
        mSessionCleanupTimeoutMs = new _.Lambda.OwO3BlCgqcOx28O1BaOAPVPor24._cls1(this);
        mSessionListeners = new ArrayList();
    }

    private Session createSubsession(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        Session session;
        int i = getCallingThreadId();
        session = (Session)mSessionMapper.get(Integer.valueOf(i));
        if(session != null)
            break MISSING_BLOCK_LABEL_41;
        Log.d("Logging", "Log.createSubsession was called with no session active.", new Object[0]);
        this;
        JVM INSTR monitorexit ;
        return null;
        Session session1;
        session1 = JVM INSTR new #124 <Class Session>;
        session1.Session(session.getNextChildId(), session.getShortMethodName(), System.currentTimeMillis(), flag, null);
        session.addChild(session1);
        session1.setParentSession(session);
        if(flag)
            break MISSING_BLOCK_LABEL_119;
        StringBuilder stringbuilder = JVM INSTR new #157 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.v("Logging", stringbuilder.append("CREATE_SUBSESSION ").append(session1.toString()).toString(), new Object[0]);
_L1:
        this;
        JVM INSTR monitorexit ;
        return session1;
        Log.v("Logging", "CREATE_SUBSESSION (Invisible subsession)", new Object[0]);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private void endParentSessions(Session session)
    {
        Session session1;
        if(!session.isSessionCompleted() || session.getChildSessions().size() != 0)
            return;
        session1 = session.getParentSession();
        if(session1 == null) goto _L2; else goto _L1
_L1:
        session.setParentSession(null);
        session1.removeChild(session);
        if(session1.isExternal())
        {
            long l = System.currentTimeMillis();
            long l2 = session.getExecutionStartTimeMilliseconds();
            notifySessionCompleteListeners(session.getShortMethodName(), l - l2);
        }
        endParentSessions(session1);
_L4:
        return;
_L2:
        long l1 = System.currentTimeMillis() - session.getExecutionStartTimeMilliseconds();
        Log.d("Logging", (new StringBuilder()).append("END_SESSION (dur: ").append(l1).append(" ms): ").append(session.toString()).toString(), new Object[0]);
        if(!session.isExternal())
            notifySessionCompleteListeners(session.getShortMethodName(), l1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private String getBase64Encoding(int i)
    {
        return Base64.encodeToString(Arrays.copyOfRange(ByteBuffer.allocate(4).putInt(i).array(), 2, 4), 3);
    }

    private int getCallingThreadId()
    {
        return mCurrentThreadId.get();
    }

    private long getCleanupTimeout(Context context)
    {
        return android.provider.Settings.Secure.getLong(context.getContentResolver(), "telecom.stale_session_cleanup_timeout_millis", 30000L);
    }

    private String getNextSessionID()
    {
        this;
        JVM INSTR monitorenter ;
        Integer integer;
        int i = sCodeEntryCounter;
        sCodeEntryCounter = i + 1;
        integer = Integer.valueOf(i);
        Object obj = integer;
        if((long)integer.intValue() >= 0x40000L)
        {
            restartSessionCounter();
            int j = sCodeEntryCounter;
            sCodeEntryCounter = j + 1;
            obj = Integer.valueOf(j);
        }
        obj = getBase64Encoding(((Integer) (obj)).intValue());
        this;
        JVM INSTR monitorexit ;
        return ((String) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    private long getSessionCleanupTimeoutMs()
    {
        return mSessionCleanupTimeoutMs.get();
    }

    private void notifySessionCompleteListeners(String s, long l)
    {
        for(Iterator iterator = mSessionListeners.iterator(); iterator.hasNext(); ((ISessionListener)iterator.next()).sessionComplete(s, l));
    }

    private void resetStaleSessionTimer()
    {
        this;
        JVM INSTR monitorenter ;
        mSessionCleanupHandler.removeCallbacksAndMessages(null);
        if(mCleanStaleSessions != null)
            mSessionCleanupHandler.postDelayed(mCleanStaleSessions, getSessionCleanupTimeoutMs());
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void restartSessionCounter()
    {
        this;
        JVM INSTR monitorenter ;
        sCodeEntryCounter = 0;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void cancelSubsession(Session session)
    {
        this;
        JVM INSTR monitorenter ;
        if(session == null)
            return;
        session.markSessionCompleted(-1L);
        endParentSessions(session);
        this;
        JVM INSTR monitorexit ;
        return;
        session;
        throw session;
    }

    public void cleanupStaleSessions(long l)
    {
        this;
        JVM INSTR monitorenter ;
        String s;
        boolean flag;
        s = "Stale Sessions Cleaned:\n";
        flag = false;
        long l1;
        Iterator iterator;
        l1 = System.currentTimeMillis();
        iterator = mSessionMapper.entrySet().iterator();
_L1:
        Session session;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_118;
            session = (Session)((java.util.Map.Entry)iterator.next()).getValue();
        } while(l1 - session.getExecutionStartTimeMilliseconds() <= l);
        iterator.remove();
        StringBuilder stringbuilder = JVM INSTR new #157 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        s = stringbuilder.append(s).append(session.printFullSessionTree()).append("\n").toString();
        flag = true;
          goto _L1
        if(!flag)
            break MISSING_BLOCK_LABEL_136;
        Log.w("Logging", s, new Object[0]);
_L2:
        this;
        JVM INSTR monitorexit ;
        return;
        Log.v("Logging", "No stale logging sessions needed to be cleaned...", new Object[0]);
          goto _L2
        Exception exception;
        exception;
        throw exception;
    }

    public void continueSession(Session session, String s)
    {
        this;
        JVM INSTR monitorenter ;
        if(session == null)
            return;
        resetStaleSessionTimer();
        session.setShortMethodName(s);
        session.setExecutionStartTimeMs(System.currentTimeMillis());
        if(session.getParentSession() != null)
            break MISSING_BLOCK_LABEL_66;
        session = JVM INSTR new #157 <Class StringBuilder>;
        session.StringBuilder();
        Log.i("Logging", session.append("Log.continueSession was called with no session active for method ").append(s).toString(), new Object[0]);
        this;
        JVM INSTR monitorexit ;
        return;
        mSessionMapper.put(Integer.valueOf(getCallingThreadId()), session);
        if(session.isStartedFromActiveSession())
            break MISSING_BLOCK_LABEL_104;
        Log.v("Logging", "CONTINUE_SUBSESSION", new Object[0]);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        session = JVM INSTR new #157 <Class StringBuilder>;
        session.StringBuilder();
        Log.v("Logging", session.append("CONTINUE_SUBSESSION (Invisible Subsession) with Method ").append(s).toString(), new Object[0]);
          goto _L1
        session;
        throw session;
    }

    public Session createSubsession()
    {
        return createSubsession(false);
    }

    public void endSession()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        Session session;
        i = getCallingThreadId();
        session = (Session)mSessionMapper.get(Integer.valueOf(i));
        if(session != null)
            break MISSING_BLOCK_LABEL_41;
        Log.w("Logging", "Log.endSession was called with no session active.", new Object[0]);
        this;
        JVM INSTR monitorexit ;
        return;
        session.markSessionCompleted(System.currentTimeMillis());
        if(session.isStartedFromActiveSession())
            break MISSING_BLOCK_LABEL_153;
        StringBuilder stringbuilder = JVM INSTR new #157 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.v("Logging", stringbuilder.append("END_SUBSESSION (dur: ").append(session.getLocalExecutionTime()).append(" mS)").toString(), new Object[0]);
_L1:
        Session session1;
        session1 = session.getParentSession();
        mSessionMapper.remove(Integer.valueOf(i));
        endParentSessions(session);
        if(session1 == null)
            break MISSING_BLOCK_LABEL_150;
        if(session1.isSessionCompleted() ^ true && session.isStartedFromActiveSession())
            mSessionMapper.put(Integer.valueOf(i), session1);
        this;
        JVM INSTR monitorexit ;
        return;
        StringBuilder stringbuilder1 = JVM INSTR new #157 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Log.v("Logging", stringbuilder1.append("END_SUBSESSION (Invisible Subsession) (dur: ").append(session.getLocalExecutionTime()).append(" ms)").toString(), new Object[0]);
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public Session.Info getExternalSession()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        int i = getCallingThreadId();
        obj = (Session)mSessionMapper.get(Integer.valueOf(i));
        if(obj != null)
            break MISSING_BLOCK_LABEL_42;
        Log.d("Logging", "Log.getExternalSession was called with no session active.", new Object[0]);
        this;
        JVM INSTR monitorexit ;
        return null;
        obj = ((Session) (obj)).getInfo();
        this;
        JVM INSTR monitorexit ;
        return ((Session.Info) (obj));
        Exception exception;
        exception;
        throw exception;
    }

    public String getSessionId()
    {
        Object obj = (Session)mSessionMapper.get(Integer.valueOf(getCallingThreadId()));
        if(obj != null)
            obj = ((Session) (obj)).toString();
        else
            obj = "";
        return ((String) (obj));
    }

    void lambda$_2D_android_telecom_Logging_SessionManager_1888()
    {
        cleanupStaleSessions(getSessionCleanupTimeoutMs());
    }

    long lambda$_2D_android_telecom_Logging_SessionManager_2450()
    {
        if(mContext == null)
            return 30000L;
        else
            return getCleanupTimeout(mContext);
    }

    public void registerSessionListener(ISessionListener isessionlistener)
    {
        this;
        JVM INSTR monitorenter ;
        if(isessionlistener == null)
            break MISSING_BLOCK_LABEL_17;
        mSessionListeners.add(isessionlistener);
        this;
        JVM INSTR monitorexit ;
        return;
        isessionlistener;
        throw isessionlistener;
    }

    public void setContext(Context context)
    {
        mContext = context;
    }

    public void startExternalSession(Session.Info info, String s)
    {
        this;
        JVM INSTR monitorenter ;
        if(info == null)
            return;
        int i;
        i = getCallingThreadId();
        if((Session)mSessionMapper.get(Integer.valueOf(i)) == null)
            break MISSING_BLOCK_LABEL_46;
        Log.w("Logging", "trying to start an external session with a session already active.", new Object[0]);
        this;
        JVM INSTR monitorexit ;
        return;
        Log.d("Logging", "START_EXTERNAL_SESSION", new Object[0]);
        Session session = JVM INSTR new #124 <Class Session>;
        StringBuilder stringbuilder = JVM INSTR new #157 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        session.Session(stringbuilder.append("E-").append(info.sessionId).toString(), info.methodPath, System.currentTimeMillis(), false, null);
        session.setIsExternal(true);
        session.markSessionCompleted(-1L);
        mSessionMapper.put(Integer.valueOf(i), session);
        continueSession(createSubsession(), s);
        this;
        JVM INSTR monitorexit ;
        return;
        info;
        throw info;
    }

    public void startSession(Session.Info info, String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        if(info != null)
            break MISSING_BLOCK_LABEL_15;
        startSession(s, s1);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        startExternalSession(info, s);
          goto _L1
        info;
        throw info;
    }

    public void startSession(String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        resetStaleSessionTimer();
        i = getCallingThreadId();
        if((Session)mSessionMapper.get(Integer.valueOf(i)) == null)
            break MISSING_BLOCK_LABEL_41;
        continueSession(createSubsession(true), s);
        this;
        JVM INSTR monitorexit ;
        return;
        Log.d("Logging", "START_SESSION", new Object[0]);
        Session session = JVM INSTR new #124 <Class Session>;
        session.Session(getNextSessionID(), s, System.currentTimeMillis(), false, s1);
        mSessionMapper.put(Integer.valueOf(i), session);
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    private static final long DEFAULT_SESSION_TIMEOUT_MS = 30000L;
    private static final String LOGGING_TAG = "Logging";
    private static final long SESSION_ID_ROLLOVER_THRESHOLD = 0x40000L;
    private static final String TIMEOUTS_PREFIX = "telecom.";
    public Runnable mCleanStaleSessions;
    private Context mContext;
    public ICurrentThreadId mCurrentThreadId;
    private Handler mSessionCleanupHandler;
    private ISessionCleanupTimeoutMs mSessionCleanupTimeoutMs;
    private List mSessionListeners;
    public ConcurrentHashMap mSessionMapper;
    private int sCodeEntryCounter;
}
