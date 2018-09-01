// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.util.EventLog;

public class SecurityLogTags
{

    private SecurityLogTags()
    {
    }

    public static void writeSecurityAdbShellCommand(String s)
    {
        EventLog.writeEvent(0x33452, s);
    }

    public static void writeSecurityAdbShellInteractive()
    {
        EventLog.writeEvent(0x33451, new Object[0]);
    }

    public static void writeSecurityAdbSyncRecv(String s)
    {
        EventLog.writeEvent(0x33453, s);
    }

    public static void writeSecurityAdbSyncSend(String s)
    {
        EventLog.writeEvent(0x33454, s);
    }

    public static void writeSecurityAppProcessStart(String s, long l, int i, int j, String s1, String s2)
    {
        EventLog.writeEvent(0x33455, new Object[] {
            s, Long.valueOf(l), Integer.valueOf(i), Integer.valueOf(j), s1, s2
        });
    }

    public static void writeSecurityKeyguardDismissAuthAttempt(int i, int j)
    {
        EventLog.writeEvent(0x33457, new Object[] {
            Integer.valueOf(i), Integer.valueOf(j)
        });
    }

    public static void writeSecurityKeyguardDismissed()
    {
        EventLog.writeEvent(0x33456, new Object[0]);
    }

    public static void writeSecurityKeyguardSecured()
    {
        EventLog.writeEvent(0x33458, new Object[0]);
    }

    public static final int SECURITY_ADB_SHELL_COMMAND = 0x33452;
    public static final int SECURITY_ADB_SHELL_INTERACTIVE = 0x33451;
    public static final int SECURITY_ADB_SYNC_RECV = 0x33453;
    public static final int SECURITY_ADB_SYNC_SEND = 0x33454;
    public static final int SECURITY_APP_PROCESS_START = 0x33455;
    public static final int SECURITY_KEYGUARD_DISMISSED = 0x33456;
    public static final int SECURITY_KEYGUARD_DISMISS_AUTH_ATTEMPT = 0x33457;
    public static final int SECURITY_KEYGUARD_SECURED = 0x33458;
}
