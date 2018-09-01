// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony;

import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Rlog
{

    private Rlog()
    {
    }

    public static int d(String s, String s1)
    {
        return Log.println_native(1, 3, s, s1);
    }

    public static int d(String s, String s1, Throwable throwable)
    {
        return Log.println_native(1, 3, s, (new StringBuilder()).append(s1).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public static int e(String s, String s1)
    {
        return Log.println_native(1, 6, s, s1);
    }

    public static int e(String s, String s1, Throwable throwable)
    {
        return Log.println_native(1, 6, s, (new StringBuilder()).append(s1).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public static int i(String s, String s1)
    {
        return Log.println_native(1, 4, s, s1);
    }

    public static int i(String s, String s1, Throwable throwable)
    {
        return Log.println_native(1, 4, s, (new StringBuilder()).append(s1).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public static boolean isLoggable(String s, int j)
    {
        return Log.isLoggable(s, j);
    }

    public static String pii(String s, Object obj)
    {
        String s1 = String.valueOf(obj);
        if(obj == null || TextUtils.isEmpty(s1) || isLoggable(s, 2))
            return s1;
        else
            return (new StringBuilder()).append("[").append(secureHash(s1.getBytes())).append("]").toString();
    }

    public static String pii(boolean flag, Object obj)
    {
        String s = String.valueOf(obj);
        if(obj == null || TextUtils.isEmpty(s) || flag)
            return s;
        else
            return (new StringBuilder()).append("[").append(secureHash(s.getBytes())).append("]").toString();
    }

    public static int println(int j, String s, String s1)
    {
        return Log.println_native(1, j, s, s1);
    }

    private static String secureHash(byte abyte0[])
    {
        if(USER_BUILD)
            return "****";
        MessageDigest messagedigest;
        try
        {
            messagedigest = MessageDigest.getInstance("SHA-1");
        }
        // Misplaced declaration of an exception variable
        catch(byte abyte0[])
        {
            return "####";
        }
        return Base64.encodeToString(messagedigest.digest(abyte0), 11);
    }

    public static int v(String s, String s1)
    {
        return Log.println_native(1, 2, s, s1);
    }

    public static int v(String s, String s1, Throwable throwable)
    {
        return Log.println_native(1, 2, s, (new StringBuilder()).append(s1).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public static int w(String s, String s1)
    {
        return Log.println_native(1, 5, s, s1);
    }

    public static int w(String s, String s1, Throwable throwable)
    {
        return Log.println_native(1, 5, s, (new StringBuilder()).append(s1).append('\n').append(Log.getStackTraceString(throwable)).toString());
    }

    public static int w(String s, Throwable throwable)
    {
        return Log.println_native(1, 5, s, Log.getStackTraceString(throwable));
    }

    private static final boolean USER_BUILD;

    static 
    {
        USER_BUILD = Build.IS_USER;
    }
}
