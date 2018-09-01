// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.Slog;
import java.io.*;

public class SELinux
{

    public SELinux()
    {
    }

    public static final native boolean checkSELinuxAccess(String s, String s1, String s2, String s3);

    public static final native String getContext();

    public static final native String getFileContext(String s);

    public static final native String getPeerContext(FileDescriptor filedescriptor);

    public static final native String getPidContext(int i);

    public static final native boolean isSELinuxEnabled();

    public static final native boolean isSELinuxEnforced();

    private static native boolean native_restorecon(String s, int i);

    public static boolean restorecon(File file)
        throws NullPointerException
    {
        boolean flag;
        try
        {
            flag = native_restorecon(file.getCanonicalPath(), 0);
        }
        catch(IOException ioexception)
        {
            Slog.e("SELinux", (new StringBuilder()).append("Error getting canonical path. Restorecon failed for ").append(file.getPath()).toString(), ioexception);
            return false;
        }
        return flag;
    }

    public static boolean restorecon(String s)
        throws NullPointerException
    {
        if(s == null)
            throw new NullPointerException();
        else
            return native_restorecon(s, 0);
    }

    public static boolean restoreconRecursive(File file)
    {
        boolean flag;
        try
        {
            flag = native_restorecon(file.getCanonicalPath(), 4);
        }
        catch(IOException ioexception)
        {
            Slog.e("SELinux", (new StringBuilder()).append("Error getting canonical path. Restorecon failed for ").append(file.getPath()).toString(), ioexception);
            return false;
        }
        return flag;
    }

    public static final native boolean setFSCreateContext(String s);

    public static final native boolean setFileContext(String s, String s1);

    private static final int SELINUX_ANDROID_RESTORECON_DATADATA = 16;
    private static final int SELINUX_ANDROID_RESTORECON_FORCE = 8;
    private static final int SELINUX_ANDROID_RESTORECON_NOCHANGE = 1;
    private static final int SELINUX_ANDROID_RESTORECON_RECURSE = 4;
    private static final int SELINUX_ANDROID_RESTORECON_VERBOSE = 2;
    private static final String TAG = "SELinux";
}
