// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import java.io.*;
import miui.os.Environment;
import miui.os.FileUtils;

class MediaScannerInjector
{

    static Context _2D_get0()
    {
        return sContext;
    }

    static String _2D_get1()
    {
        return sProcessName;
    }

    static Handler _2D_set0(Handler handler)
    {
        sHandler = handler;
        return handler;
    }

    MediaScannerInjector()
    {
    }

    private static void copyFile(String s, String s1, int i)
    {
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = obj;
        obj6 = obj2;
        Object obj7 = JVM INSTR new #79  <Class FileInputStream>;
        obj5 = obj;
        obj6 = obj2;
        ((FileInputStream) (obj7)).FileInputStream(s);
        s = JVM INSTR new #84  <Class FileOutputStream>;
        s.FileOutputStream(s1);
        s.write(170);
        int j = 0;
        s1 = new byte[4096];
_L5:
        int k = ((FileInputStream) (obj7)).read(s1);
        if(k <= 0) goto _L2; else goto _L1
_L1:
        j += k;
        if(j < i) goto _L4; else goto _L3
_L3:
        s.write(s1, 0, k - (j - i));
_L2:
        if(obj7 == null)
            break MISSING_BLOCK_LABEL_113;
        ((FileInputStream) (obj7)).close();
        if(s == null)
            break MISSING_BLOCK_LABEL_121;
        s.close();
_L6:
        return;
_L4:
        s.write(s1, 0, k);
          goto _L5
        s1;
        obj5 = obj7;
        obj7 = s;
        s = ((String) (obj5));
_L9:
        obj5 = s;
        obj6 = obj7;
        Log.e("MediaScannerInjector", "IOException ", s1);
        if(s == null)
            break MISSING_BLOCK_LABEL_168;
        s.close();
        if(obj7 != null)
            try
            {
                ((FileOutputStream) (obj7)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                Log.e("MediaScannerInjector", "IOException", s);
            }
          goto _L6
        s;
        Log.e("MediaScannerInjector", "IOException", s);
          goto _L6
        s;
_L8:
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_218;
        ((FileInputStream) (obj5)).close();
        if(obj6 != null)
            try
            {
                ((FileOutputStream) (obj6)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s1)
            {
                Log.e("MediaScannerInjector", "IOException", s1);
            }
        throw s;
        s;
        obj5 = obj7;
        obj6 = obj3;
        continue; /* Loop/switch isn't completed */
        s1;
        obj5 = obj7;
        obj6 = s;
        s = s1;
        if(true) goto _L8; else goto _L7
_L7:
        s1;
        s = obj1;
        obj7 = obj4;
          goto _L9
        s1;
        s = ((String) (obj7));
        obj7 = obj4;
          goto _L9
    }

    private static void initDebugDirectory()
    {
        File file = new File(DEBUG_LOG_PATH);
        if(!file.exists())
        {
            if(!FileUtils.mkdirs(file, -1, -1, -1))
            {
                Log.e("MediaScannerInjector", (new StringBuilder()).append("mkdir ").append(DEBUG_LOG_PATH).append(" failed").toString());
                return;
            }
            FileUtils.addNoMedia(DEBUG_LOG_PATH);
        }
    }

    public static void initMediaFileCapture(String s)
    {
        android/media/MediaScannerInjector;
        JVM INSTR monitorenter ;
        sProcessName = s;
        if(sHandlerThread == null && sHandler == null)
        {
            s = JVM INSTR new #6   <Class MediaScannerInjector$1>;
            s._cls1("MediaScannerInjector");
            sHandlerThread = s;
            sHandlerThread.start();
        }
        android/media/MediaScannerInjector;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    static boolean keepMimeType(String s, String s1)
    {
        boolean flag;
        if(s1 != null)
            flag = s1.startsWith("audio");
        else
            flag = true;
        if("video/mp2p".equals(s) && flag)
            return true;
        return "video/x-matroska".equals(s) && flag;
    }

    public static void processFileBegin(String s, Context context)
    {
        if(sHandler != null)
        {
            s = sHandler.obtainMessage(1, s);
            sHandler.sendMessageDelayed(s, 60000L);
        }
        sContext = context;
    }

    public static void processFileEnd()
    {
        if(sHandler != null)
            sHandler.removeMessages(1);
        sContext = null;
    }

    private static final String DEBUG_LOG_PATH = (new StringBuilder()).append(Environment.getExternalStorageMiuiDirectory().getAbsolutePath()).append("/debug_log/common/android.process.media/").toString();
    private static final int MEDIA_META_APPROXIMATION = 0x400000;
    private static final int MSG_PROCESSFILE_TIMEOUT = 1;
    private static final int PROCESSFILE_TIMEOUT = 60000;
    private static final String SECURITY_CENTER = "com.miui.securitycenter";
    private static final String TAG = "MediaScannerInjector";
    private static Context sContext;
    private static Handler sHandler;
    private static HandlerThread sHandlerThread;
    private static String sProcessName;


    // Unreferenced inner class android/media/MediaScannerInjector$1

/* anonymous class */
    static final class _cls1 extends HandlerThread
    {

        protected void onLooperPrepared()
        {
            MediaScannerInjector._2D_set0(new Handler(getLooper()) {

                public void handleMessage(Message message)
                {
                    Log.e("MediaScannerInjector", (new StringBuilder()).append("handleMessage what: ").append(message.what).toString());
                    if(message.what == 1)
                    {
                        message = (String)message.obj;
                        Log.e("MediaScannerInjector", (new StringBuilder()).append("ProcessFile timeout, path: ").append(message).toString());
                        Intent intent = new Intent("miui.intent.ACTION_MEDIASCANNER_TIMEOUT");
                        intent.putExtra("miui.intent.extra.EXTRA_FILE_PATH", message);
                        intent.putExtra("miui.intent.extra.EXTRA_PROCESS_NAME", MediaScannerInjector._2D_get1());
                        intent.setPackage("com.miui.securitycenter");
                        MediaScannerInjector._2D_get0().sendBroadcast(intent);
                    }
                }

                final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super(looper);
            }
            }
);
        }

    }

}
