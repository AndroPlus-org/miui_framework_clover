// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom.Logging;

import android.os.Parcel;
import android.os.Parcelable;
import android.telecom.Log;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class Session
{
    public static class Info
        implements Parcelable
    {

        public static Info getInfo(Session session)
        {
            String s = Session._2D_wrap1(session);
            boolean flag;
            if(!Log.DEBUG)
                flag = Session._2D_wrap0(session);
            else
                flag = false;
            return new Info(s, session.getFullMethodPath(flag));
        }

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(sessionId);
            parcel.writeString(methodPath);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Info createFromParcel(Parcel parcel)
            {
                return new Info(parcel.readString(), parcel.readString(), null);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Info[] newArray(int i)
            {
                return new Info[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public final String methodPath;
        public final String sessionId;


        private Info(String s, String s1)
        {
            sessionId = s;
            methodPath = s1;
        }

        Info(String s, String s1, Info info)
        {
            this(s, s1);
        }
    }


    static boolean _2D_wrap0(Session session)
    {
        return session.isSessionExternal();
    }

    static String _2D_wrap1(Session session)
    {
        return session.getFullSessionId();
    }

    public Session(String s, String s1, long l, boolean flag, String s2)
    {
        mExecutionEndTimeMs = -1L;
        mIsCompleted = false;
        mIsExternal = false;
        mChildCounter = 0;
        mIsStartedFromActiveSession = false;
        setSessionId(s);
        setShortMethodName(s1);
        mExecutionStartTimeMs = l;
        mParentSession = null;
        mChildSessions = new ArrayList(5);
        mIsStartedFromActiveSession = flag;
        mOwnerInfo = s2;
    }

    private void getFullMethodPath(StringBuilder stringbuilder, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        if(TextUtils.isEmpty(mFullMethodPathCache) || !(flag ^ true))
            break MISSING_BLOCK_LABEL_30;
        stringbuilder.append(mFullMethodPathCache);
        this;
        JVM INSTR monitorexit ;
        return;
        Session session = getParentSession();
        boolean flag1;
        flag1 = false;
        if(session == null)
            break MISSING_BLOCK_LABEL_70;
        flag1 = mShortMethodName.equals(session.mShortMethodName) ^ true;
        session.getFullMethodPath(stringbuilder, flag);
        stringbuilder.append("->");
        if(!isExternal())
            break MISSING_BLOCK_LABEL_141;
        if(!flag) goto _L2; else goto _L1
_L1:
        stringbuilder.append("...");
_L3:
        if(!flag1 || !(flag ^ true))
            break MISSING_BLOCK_LABEL_107;
        mFullMethodPathCache = stringbuilder.toString();
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        stringbuilder.append("(");
        stringbuilder.append(mShortMethodName);
        stringbuilder.append(")");
          goto _L3
        stringbuilder;
        throw stringbuilder;
        stringbuilder.append(mShortMethodName);
          goto _L3
    }

    private String getFullSessionId()
    {
        Session session = mParentSession;
        if(session == null)
            return mSessionId;
        if(Log.VERBOSE)
            return (new StringBuilder()).append(session.getFullSessionId()).append("_").append(mSessionId).toString();
        else
            return session.getFullSessionId();
    }

    private boolean isSessionExternal()
    {
        if(getParentSession() == null)
            return isExternal();
        else
            return getParentSession().isSessionExternal();
    }

    private void printSessionTree(int i, StringBuilder stringbuilder)
    {
        stringbuilder.append(toString());
        Session session;
        for(Iterator iterator = mChildSessions.iterator(); iterator.hasNext(); session.printSessionTree(i + 1, stringbuilder))
        {
            session = (Session)iterator.next();
            stringbuilder.append("\n");
            for(int j = 0; j <= i; j++)
                stringbuilder.append("\t");

        }

    }

    public void addChild(Session session)
    {
        if(session != null)
            mChildSessions.add(session);
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (Session)obj;
        if(mExecutionStartTimeMs != ((Session) (obj)).mExecutionStartTimeMs)
            return false;
        if(mExecutionEndTimeMs != ((Session) (obj)).mExecutionEndTimeMs)
            return false;
        if(mIsCompleted != ((Session) (obj)).mIsCompleted)
            return false;
        if(mChildCounter != ((Session) (obj)).mChildCounter)
            return false;
        if(mIsStartedFromActiveSession != ((Session) (obj)).mIsStartedFromActiveSession)
            return false;
        if(mSessionId == null ? ((Session) (obj)).mSessionId != null : mSessionId.equals(((Session) (obj)).mSessionId) ^ true)
            return false;
        if(mShortMethodName == null ? ((Session) (obj)).mShortMethodName != null : mShortMethodName.equals(((Session) (obj)).mShortMethodName) ^ true)
            return false;
        if(mParentSession == null ? ((Session) (obj)).mParentSession != null : mParentSession.equals(((Session) (obj)).mParentSession) ^ true)
            return false;
        if(mChildSessions == null ? ((Session) (obj)).mChildSessions != null : mChildSessions.equals(((Session) (obj)).mChildSessions) ^ true)
            return false;
        if(mOwnerInfo == null) goto _L2; else goto _L1
_L1:
        flag = mOwnerInfo.equals(((Session) (obj)).mOwnerInfo);
_L4:
        return flag;
_L2:
        if(((Session) (obj)).mOwnerInfo != null)
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public ArrayList getChildSessions()
    {
        return mChildSessions;
    }

    public long getExecutionStartTimeMilliseconds()
    {
        return mExecutionStartTimeMs;
    }

    public String getFullMethodPath(boolean flag)
    {
        StringBuilder stringbuilder = new StringBuilder();
        getFullMethodPath(stringbuilder, flag);
        return stringbuilder.toString();
    }

    public Info getInfo()
    {
        return Info.getInfo(this);
    }

    public long getLocalExecutionTime()
    {
        if(mExecutionEndTimeMs == -1L)
            return -1L;
        else
            return mExecutionEndTimeMs - mExecutionStartTimeMs;
    }

    public String getNextChildId()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = mChildCounter;
        mChildCounter = i + 1;
        this;
        JVM INSTR monitorexit ;
        return String.valueOf(i);
        Exception exception;
        exception;
        throw exception;
    }

    public Session getParentSession()
    {
        return mParentSession;
    }

    public String getSessionId()
    {
        return mSessionId;
    }

    public String getShortMethodName()
    {
        return mShortMethodName;
    }

    public int hashCode()
    {
        int i = 1;
        int j = 0;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        if(mSessionId != null)
            k = mSessionId.hashCode();
        else
            k = 0;
        if(mShortMethodName != null)
            l = mShortMethodName.hashCode();
        else
            l = 0;
        i1 = (int)(mExecutionStartTimeMs ^ mExecutionStartTimeMs >>> 32);
        j1 = (int)(mExecutionEndTimeMs ^ mExecutionEndTimeMs >>> 32);
        if(mParentSession != null)
            k1 = mParentSession.hashCode();
        else
            k1 = 0;
        if(mChildSessions != null)
            l1 = mChildSessions.hashCode();
        else
            l1 = 0;
        if(mIsCompleted)
            i2 = 1;
        else
            i2 = 0;
        j2 = mChildCounter;
        if(!mIsStartedFromActiveSession)
            i = 0;
        if(mOwnerInfo != null)
            j = mOwnerInfo.hashCode();
        return ((((((((k * 31 + l) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + l1) * 31 + i2) * 31 + j2) * 31 + i) * 31 + j;
    }

    public boolean isExternal()
    {
        return mIsExternal;
    }

    public boolean isSessionCompleted()
    {
        return mIsCompleted;
    }

    public boolean isStartedFromActiveSession()
    {
        return mIsStartedFromActiveSession;
    }

    public void markSessionCompleted(long l)
    {
        mExecutionEndTimeMs = l;
        mIsCompleted = true;
    }

    public String printFullSessionTree()
    {
        Session session;
        for(session = this; session.getParentSession() != null; session = session.getParentSession());
        return session.printSessionTree();
    }

    public String printSessionTree()
    {
        StringBuilder stringbuilder = new StringBuilder();
        printSessionTree(0, stringbuilder);
        return stringbuilder.toString();
    }

    public void removeChild(Session session)
    {
        if(session != null)
            mChildSessions.remove(session);
    }

    public void setExecutionStartTimeMs(long l)
    {
        mExecutionStartTimeMs = l;
    }

    public void setIsExternal(boolean flag)
    {
        mIsExternal = flag;
    }

    public void setParentSession(Session session)
    {
        mParentSession = session;
    }

    public void setSessionId(String s)
    {
        if(s == null)
            mSessionId = "?";
        mSessionId = s;
    }

    public void setShortMethodName(String s)
    {
        String s1 = s;
        if(s == null)
            s1 = "";
        mShortMethodName = s1;
    }

    public String toString()
    {
        if(mParentSession != null && mIsStartedFromActiveSession)
            return mParentSession.toString();
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(getFullMethodPath(false));
        if(mOwnerInfo != null && mOwnerInfo.isEmpty() ^ true)
        {
            stringbuilder.append("(InCall package: ");
            stringbuilder.append(mOwnerInfo);
            stringbuilder.append(")");
        }
        return (new StringBuilder()).append(stringbuilder.toString()).append("@").append(getFullSessionId()).toString();
    }

    public static final String CONTINUE_SUBSESSION = "CONTINUE_SUBSESSION";
    public static final String CREATE_SUBSESSION = "CREATE_SUBSESSION";
    public static final String END_SESSION = "END_SESSION";
    public static final String END_SUBSESSION = "END_SUBSESSION";
    public static final String EXTERNAL_INDICATOR = "E-";
    public static final String SESSION_SEPARATION_CHAR_CHILD = "_";
    public static final String START_EXTERNAL_SESSION = "START_EXTERNAL_SESSION";
    public static final String START_SESSION = "START_SESSION";
    public static final String SUBSESSION_SEPARATION_CHAR = "->";
    public static final String TRUNCATE_STRING = "...";
    public static final int UNDEFINED = -1;
    private int mChildCounter;
    private ArrayList mChildSessions;
    private long mExecutionEndTimeMs;
    private long mExecutionStartTimeMs;
    private String mFullMethodPathCache;
    private boolean mIsCompleted;
    private boolean mIsExternal;
    private boolean mIsStartedFromActiveSession;
    private String mOwnerInfo;
    private Session mParentSession;
    private String mSessionId;
    private String mShortMethodName;
}
