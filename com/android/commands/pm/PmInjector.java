// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.commands.pm;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import java.io.File;
import java.util.Iterator;
import miui.securityspace.XSpaceUserHandle;

// Referenced classes of package com.android.commands.pm:
//            IActivityManagerCompat

public final class PmInjector
{
    public static class InstallObserver extends android.os.IMessenger.Stub
    {

        public void send(Message message)
            throws RemoteException
        {
            this;
            JVM INSTR monitorenter ;
            finished = true;
            result = message.what;
            message = message.getData();
            if(message == null)
                break MISSING_BLOCK_LABEL_34;
            msg = message.getString("msg");
            notifyAll();
            this;
            JVM INSTR monitorexit ;
            return;
            message;
            throw message;
        }

        boolean finished;
        String msg;
        int result;

        public InstallObserver()
        {
        }
    }


    public PmInjector()
    {
    }

    public static int getDefaultUserId()
    {
        int i;
        Iterator iterator = android.os.IUserManager.Stub.asInterface(ServiceManager.getService("user")).getUsers(false).iterator();
        do
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_51;
        while(!XSpaceUserHandle.isXSpaceUser((UserInfo)iterator.next()));
        i = ActivityManager.getCurrentUser();
        return i;
        RemoteException remoteexception;
        remoteexception;
        return -1;
    }

    public static int installVerify(String s)
    {
        InstallObserver installobserver;
        Uri uri = Uri.fromFile(new File(s));
        s = new Intent("android.intent.action.VIEW");
        s.setClassName("com.miui.securitycenter", "com.miui.permcenter.install.AdbInstallActivity");
        s.setDataAndType(uri, "application/vnd.android.package-archive");
        installobserver = new InstallObserver();
        s.putExtra("observer", installobserver);
        s.addFlags(0x18000000);
        int i;
        try
        {
            i = IActivityManagerCompat.startActivity(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            s.printStackTrace();
            Log.e("Pm", "start PackageInstallerActivity RemoteException");
            return -1;
        }
        if(i == 0)
            break MISSING_BLOCK_LABEL_120;
        s = JVM INSTR new #130 <Class StringBuilder>;
        s.StringBuilder();
        Log.e("Pm", s.append("start PackageInstallerActivity failed [").append(i).append("]").toString());
        return -1;
        installobserver;
        JVM INSTR monitorenter ;
_L2:
        boolean flag = installobserver.finished;
        if(flag)
            break; /* Loop/switch isn't completed */
        try
        {
            installobserver.wait(60000L);
            installobserver.finished = true;
        }
        // Misplaced declaration of an exception variable
        catch(String s) { }
        if(true) goto _L2; else goto _L1
_L1:
        int j = installobserver.result;
        if(j != -1)
            break MISSING_BLOCK_LABEL_166;
        installobserver;
        JVM INSTR monitorexit ;
        return 2;
        String s1 = installobserver.msg;
        s = s1;
        if(s1 == null)
            s = "Failure [INSTALL_CANCELED_BY_USER]";
        StringBuilder stringbuilder = JVM INSTR new #130 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("Pm", stringbuilder.append("install msg : ").append(s).toString());
        flag = s.contains("Invalid apk");
        if(flag)
            return 3;
        installobserver;
        JVM INSTR monitorexit ;
        return -1;
        s;
        throw s;
    }

    public static String statusToString(int i)
    {
        String s;
        String s1;
        s = "";
        s1 = s;
        i;
        JVM INSTR tableswitch -1 3: default 40
    //                   -1 44
    //                   0 42
    //                   1 42
    //                   2 50
    //                   3 56;
           goto _L1 _L2 _L3 _L3 _L4 _L5
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        s1 = s;
_L7:
        return s1;
_L2:
        s1 = "Install canceled by user";
        continue; /* Loop/switch isn't completed */
_L4:
        s1 = "Sucess";
        continue; /* Loop/switch isn't completed */
_L5:
        s1 = "Invalid apk";
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static final String PM = "Pm";
    public static final int STATUS_INVALID_APK = 3;
    public static final int STATUS_REJECT = -1;
    public static final int STATUS_SUCESS = 2;
}
