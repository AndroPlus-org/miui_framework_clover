// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


public class MediaCasStateException extends IllegalStateException
{

    private MediaCasStateException(int i, String s, String s1)
    {
        super(s);
        mErrorCode = i;
        mDiagnosticInfo = s1;
    }

    static void throwExceptionIfNeeded(int i)
    {
        throwExceptionIfNeeded(i, null);
    }

    static void throwExceptionIfNeeded(int i, String s)
    {
        if(i == 0)
            return;
        if(i == 6)
            throw new IllegalArgumentException();
        i;
        JVM INSTR tableswitch 1 14: default 92
    //                   1 131
    //                   2 137
    //                   3 143
    //                   4 149
    //                   5 155
    //                   6 92
    //                   7 92
    //                   8 92
    //                   9 161
    //                   10 167
    //                   11 92
    //                   12 173
    //                   13 179
    //                   14 125;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L1 _L1 _L1 _L7 _L8 _L1 _L9 _L10 _L11
_L1:
        String s1 = "Unknown CAS state exception";
_L13:
        throw new MediaCasStateException(i, s, String.format("%s (err=%d)", new Object[] {
            s1, Integer.valueOf(i)
        }));
_L11:
        s1 = "General CAS error";
        continue; /* Loop/switch isn't completed */
_L2:
        s1 = "No license";
        continue; /* Loop/switch isn't completed */
_L3:
        s1 = "License expired";
        continue; /* Loop/switch isn't completed */
_L4:
        s1 = "Session not opened";
        continue; /* Loop/switch isn't completed */
_L5:
        s1 = "Unsupported scheme or data format";
        continue; /* Loop/switch isn't completed */
_L6:
        s1 = "Invalid CAS state";
        continue; /* Loop/switch isn't completed */
_L7:
        s1 = "Insufficient output protection";
        continue; /* Loop/switch isn't completed */
_L8:
        s1 = "Tamper detected";
        continue; /* Loop/switch isn't completed */
_L9:
        s1 = "Not initialized";
        continue; /* Loop/switch isn't completed */
_L10:
        s1 = "Decrypt error";
        if(true) goto _L13; else goto _L12
_L12:
    }

    public String getDiagnosticInfo()
    {
        return mDiagnosticInfo;
    }

    public int getErrorCode()
    {
        return mErrorCode;
    }

    private final String mDiagnosticInfo;
    private final int mErrorCode;
}
