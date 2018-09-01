// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.telephony;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.telephony.Rlog;
import java.util.*;
import miui.os.SystemProperties;
import miui.util.AppConstants;

public class PhoneDebug
{
    public static interface Listener
    {

        public abstract void onDebugChanged();
    }


    static List _2D_get0()
    {
        return sListeners;
    }

    static void _2D_wrap0()
    {
        register();
    }

    private PhoneDebug()
    {
    }

    public static Listener addListener(Listener listener)
    {
        if(sListeners == null)
            sListeners = new ArrayList(1);
        if(listener != null && sListeners.contains(listener) ^ true)
        {
            sListeners.add(listener);
            listener.onDebugChanged();
        }
        return listener;
    }

    private static void register()
    {
        boolean flag = true;
        ContentResolver contentresolver = AppConstants.getCurrentApplication().getContentResolver();
        if(android.provider.Settings.System.getInt(contentresolver, "phone_debug_flag", 0) != 1)
            flag = SystemProperties.getBoolean("debug.miui.phone", false);
        VDBG = flag;
        android.net.Uri uri = android.provider.Settings.System.getUriFor("phone_debug_flag");
        ContentObserver contentobserver = JVM INSTR new #8   <Class PhoneDebug$2>;
        contentobserver._cls2(null, contentresolver);
        contentresolver.registerContentObserver(uri, false, contentobserver);
_L1:
        return;
        Exception exception;
        exception;
        Rlog.w("PhoneDebug", (new StringBuilder()).append("register").append(exception).toString());
          goto _L1
    }

    private static void registerDelay(int i)
    {
        if(VDBG)
            Rlog.w("PhoneDebug", "registerDelay");
        (new Thread(new Runnable(i) {

            public void run()
            {
                Thread.sleep(time);
                PhoneDebug._2D_wrap0();
_L1:
                return;
                Exception exception;
                exception;
                Rlog.w("PhoneDebug", (new StringBuilder()).append("registerDelay").append(exception).toString());
                  goto _L1
            }

            final int val$time;

            
            {
                time = i;
                super();
            }
        }
)).start();
    }

    public static void removeListener(Listener listener)
    {
        if(sListeners != null && listener != null)
        {
            sListeners.remove(listener);
            if(sListeners.isEmpty())
                sListeners = null;
        }
    }

    public static final String PHONE_DEBUG_FLAG = "phone_debug_flag";
    public static boolean VDBG;
    private static List sListeners;

    static 
    {
        boolean flag;
        flag = true;
        VDBG = false;
        sListeners = null;
        Application application = AppConstants.getCurrentApplication();
        if(!"android".equals(application.getOpPackageName()))
            break MISSING_BLOCK_LABEL_57;
        registerDelay(60000);
        if(android.provider.Settings.System.getInt(application.getContentResolver(), "phone_debug_flag", 0) != 1)
            flag = SystemProperties.getBoolean("debug.miui.phone", false);
        VDBG = flag;
_L1:
        return;
        try
        {
            register();
        }
        catch(Exception exception)
        {
            Rlog.w("PhoneDebug", (new StringBuilder()).append("init").append(exception).toString());
        }
          goto _L1
    }

    // Unreferenced inner class miui/telephony/PhoneDebug$2

/* anonymous class */
    static final class _cls2 extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            flag = true;
            if(android.provider.Settings.System.getInt(cr, "phone_debug_flag", 0) != 1)
                flag = SystemProperties.getBoolean("debug.miui.phone", false);
            PhoneDebug.VDBG = flag;
            if(PhoneDebug.VDBG)
                Rlog.w("PhoneDebug", (new StringBuilder()).append("onChange VDBG=").append(PhoneDebug.VDBG).toString());
            if(PhoneDebug._2D_get0() != null)
            {
                for(Iterator iterator = PhoneDebug._2D_get0().iterator(); iterator.hasNext(); ((Listener)iterator.next()).onDebugChanged());
            }
        }

        final ContentResolver val$cr;

            
            {
                cr = contentresolver;
                super(handler);
            }
    }

}
