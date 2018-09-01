// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.os;

import android.os.Process;
import android.system.*;
import android.util.Slog;
import android.util.TimingsTraceLog;
import dalvik.system.VMRuntime;
import java.io.*;
import libcore.io.IoUtils;

// Referenced classes of package com.android.internal.os:
//            Zygote, ZygoteInit, RuntimeInit

public class WrapperInit
{

    private WrapperInit()
    {
    }

    public static void execApplication(String s, String s1, int i, String s2, FileDescriptor filedescriptor, String as[])
    {
        StringBuilder stringbuilder = new StringBuilder(s);
        int j;
        if(VMRuntime.is64BitInstructionSet(s2))
            s = "/system/bin/app_process64";
        else
            s = "/system/bin/app_process32";
        stringbuilder.append(' ');
        stringbuilder.append(s);
        stringbuilder.append(" /system/bin --application");
        if(s1 != null)
            stringbuilder.append(" '--nice-name=").append(s1).append("'");
        stringbuilder.append(" com.android.internal.os.WrapperInit ");
        if(filedescriptor != null)
            j = filedescriptor.getInt$();
        else
            j = 0;
        stringbuilder.append(j);
        stringbuilder.append(' ');
        stringbuilder.append(i);
        Zygote.appendQuotedShellArgs(stringbuilder, as);
        preserveCapabilities();
        Zygote.execShell(stringbuilder.toString());
    }

    public static void main(String args[])
    {
        int i = Integer.parseInt(args[0], 10);
        int j = Integer.parseInt(args[1], 10);
        String args1[];
        if(i != 0)
            try
            {
                FileDescriptor filedescriptor = JVM INSTR new #45  <Class FileDescriptor>;
                filedescriptor.FileDescriptor();
                filedescriptor.setInt$(i);
                DataOutputStream dataoutputstream = JVM INSTR new #87  <Class DataOutputStream>;
                FileOutputStream fileoutputstream = JVM INSTR new #89  <Class FileOutputStream>;
                fileoutputstream.FileOutputStream(filedescriptor);
                dataoutputstream.DataOutputStream(fileoutputstream);
                dataoutputstream.writeInt(Process.myPid());
                dataoutputstream.close();
                IoUtils.closeQuietly(filedescriptor);
            }
            catch(IOException ioexception)
            {
                Slog.d("AndroidRuntime", "Could not write pid of wrapped process to Zygote pipe.", ioexception);
            }
        ZygoteInit.preload(new TimingsTraceLog("WrapperInitTiming", 16384L));
        args1 = new String[args.length - 2];
        System.arraycopy(args, 2, args1, 0, args1.length);
        wrapperInit(j, args1).run();
    }

    private static void preserveCapabilities()
    {
        StructCapUserHeader structcapuserheader = new StructCapUserHeader(OsConstants._LINUX_CAPABILITY_VERSION_3, 0);
        StructCapUserData astructcapuserdata[];
        int i;
        try
        {
            astructcapuserdata = Os.capget(structcapuserheader);
        }
        catch(ErrnoException errnoexception1)
        {
            Slog.e("AndroidRuntime", "RuntimeInit: Failed capget", errnoexception1);
            return;
        }
        if(astructcapuserdata[0].permitted != astructcapuserdata[0].inheritable || astructcapuserdata[1].permitted != astructcapuserdata[1].inheritable)
        {
            astructcapuserdata[0] = new StructCapUserData(astructcapuserdata[0].effective, astructcapuserdata[0].permitted, astructcapuserdata[0].permitted);
            astructcapuserdata[1] = new StructCapUserData(astructcapuserdata[1].effective, astructcapuserdata[1].permitted, astructcapuserdata[1].permitted);
            int j;
            int k;
            try
            {
                Os.capset(structcapuserheader, astructcapuserdata);
            }
            catch(ErrnoException errnoexception2)
            {
                Slog.e("AndroidRuntime", "RuntimeInit: Failed capset", errnoexception2);
                return;
            }
        }
        i = 0;
        while(i < 64) 
        {
            j = OsConstants.CAP_TO_INDEX(i);
            k = OsConstants.CAP_TO_MASK(i);
            if((astructcapuserdata[j].inheritable & k) != 0)
                try
                {
                    Os.prctl(OsConstants.PR_CAP_AMBIENT, OsConstants.PR_CAP_AMBIENT_RAISE, i, 0L, 0L);
                }
                catch(ErrnoException errnoexception)
                {
                    Slog.e("AndroidRuntime", (new StringBuilder()).append("RuntimeInit: Failed to raise ambient capability ").append(i).toString(), errnoexception);
                }
            i++;
        }
    }

    private static Runnable wrapperInit(int i, String as[])
    {
        Object obj = null;
        ClassLoader classloader = obj;
        String as1[] = as;
        if(as != null)
        {
            classloader = obj;
            as1 = as;
            if(as.length > 2)
            {
                classloader = obj;
                as1 = as;
                if(as[0].equals("-cp"))
                {
                    classloader = ZygoteInit.createPathClassLoader(as[1], i);
                    Thread.currentThread().setContextClassLoader(classloader);
                    as1 = new String[as.length - 2];
                    System.arraycopy(as, 2, as1, 0, as.length - 2);
                }
            }
        }
        Zygote.nativePreApplicationInit();
        return RuntimeInit.applicationInit(i, as1, classloader);
    }

    private static final String TAG = "AndroidRuntime";
}
