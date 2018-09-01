// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import android.os.SystemProperties;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// Referenced classes of package android.util:
//            Log

public class BoostFramework
{
    public class Draw
    {

        public static final int EVENT_TYPE_V1 = 1;
        final BoostFramework this$0;

        public Draw()
        {
            this$0 = BoostFramework.this;
            super();
        }
    }

    public class Launch
    {

        public static final int BOOST_V1 = 1;
        public static final int BOOST_V2 = 2;
        public static final int BOOST_V3 = 3;
        public static final int TYPE_SERVICE_START = 100;
        final BoostFramework this$0;

        public Launch()
        {
            this$0 = BoostFramework.this;
            super();
        }
    }

    public class Scroll
    {

        public static final int HORIZONTAL = 2;
        public static final int PANEL_VIEW = 3;
        public static final int PREFILING = 4;
        public static final int VERTICAL = 1;
        final BoostFramework this$0;

        public Scroll()
        {
            this$0 = BoostFramework.this;
            super();
        }
    }


    public BoostFramework()
    {
        mPerf = null;
        android/util/BoostFramework;
        JVM INSTR monitorenter ;
        boolean flag = mIsLoaded;
        if(flag)
            break MISSING_BLOCK_LABEL_268;
        mPerfClass = Class.forName("com.qualcomm.qti.Performance");
        Class class1 = Integer.TYPE;
        mAcquireFunc = mPerfClass.getMethod("perfLockAcquire", new Class[] {
            class1, [I
        });
        class1 = Integer.TYPE;
        Class class3 = Integer.TYPE;
        Class class5 = Integer.TYPE;
        mPerfHintFunc = mPerfClass.getMethod("perfHint", new Class[] {
            class1, java/lang/String, class3, class5
        });
        mReleaseFunc = mPerfClass.getMethod("perfLockRelease", new Class[0]);
        class1 = Integer.TYPE;
        mReleaseHandlerFunc = mPerfClass.getDeclaredMethod("perfLockReleaseHandler", new Class[] {
            class1
        });
        class1 = Integer.TYPE;
        mIOPStart = mPerfClass.getDeclaredMethod("perfIOPrefetchStart", new Class[] {
            class1, java/lang/String, java/lang/String
        });
        mIOPStop = mPerfClass.getDeclaredMethod("perfIOPrefetchStop", new Class[0]);
        if(mIopv2 == 1)
        {
            Class class4 = Integer.TYPE;
            Class class6 = Integer.TYPE;
            Class class2 = Integer.TYPE;
            mUXEngine_events = mPerfClass.getDeclaredMethod("perfUXEngine_events", new Class[] {
                class4, class6, java/lang/String, class2
            });
            class2 = Integer.TYPE;
            mUXEngine_trigger = mPerfClass.getDeclaredMethod("perfUXEngine_trigger", new Class[] {
                class2
            });
        }
        mIsLoaded = true;
_L1:
        android/util/BoostFramework;
        JVM INSTR monitorexit ;
        if(mPerfClass != null)
            mPerf = mPerfClass.newInstance();
_L2:
        return;
        Object obj;
        obj;
        StringBuilder stringbuilder = JVM INSTR new #152 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("BoostFramework", stringbuilder.append("BoostFramework() : Exception_1 = ").append(obj).toString());
          goto _L1
        obj;
        throw obj;
        obj;
        Log.e("BoostFramework", (new StringBuilder()).append("BoostFramework() : Exception_2 = ").append(obj).toString());
          goto _L2
    }

    public int perfHint(int i, String s)
    {
        return perfHint(i, s, -1, -1);
    }

    public int perfHint(int i, String s, int j)
    {
        return perfHint(i, s, j, -1);
    }

    public int perfHint(int i, String s, int j, int k)
    {
        byte byte0 = -1;
        try
        {
            i = ((Integer)mPerfHintFunc.invoke(mPerf, new Object[] {
                Integer.valueOf(i), s, Integer.valueOf(j), Integer.valueOf(k)
            })).intValue();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("BoostFramework", (new StringBuilder()).append("Exception ").append(s).toString());
            i = byte0;
        }
        return i;
    }

    public int perfIOPrefetchStart(int i, String s, String s1)
    {
        byte byte0 = -1;
        try
        {
            i = ((Integer)mIOPStart.invoke(mPerf, new Object[] {
                Integer.valueOf(i), s, s1
            })).intValue();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("BoostFramework", (new StringBuilder()).append("Exception ").append(s).toString());
            i = byte0;
        }
        return i;
    }

    public int perfIOPrefetchStop()
    {
        int i = -1;
        int j = ((Integer)mIOPStop.invoke(mPerf, new Object[0])).intValue();
        i = j;
_L2:
        return i;
        Exception exception;
        exception;
        Log.e("BoostFramework", (new StringBuilder()).append("Exception ").append(exception).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    public transient int perfLockAcquire(int i, int ai[])
    {
        byte byte0 = -1;
        try
        {
            i = ((Integer)mAcquireFunc.invoke(mPerf, new Object[] {
                Integer.valueOf(i), ai
            })).intValue();
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            Log.e("BoostFramework", (new StringBuilder()).append("Exception ").append(ai).toString());
            i = byte0;
        }
        return i;
    }

    public int perfLockRelease()
    {
        int i = -1;
        int j = ((Integer)mReleaseFunc.invoke(mPerf, new Object[0])).intValue();
        i = j;
_L2:
        return i;
        Exception exception;
        exception;
        Log.e("BoostFramework", (new StringBuilder()).append("Exception ").append(exception).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int perfLockReleaseHandler(int i)
    {
        byte byte0 = -1;
        try
        {
            i = ((Integer)mReleaseHandlerFunc.invoke(mPerf, new Object[] {
                Integer.valueOf(i)
            })).intValue();
        }
        catch(Exception exception)
        {
            Log.e("BoostFramework", (new StringBuilder()).append("Exception ").append(exception).toString());
            i = byte0;
        }
        return i;
    }

    public int perfUXEngine_events(int i, int j, String s, int k)
    {
        byte byte0 = -1;
        if(mIopv2 == 0 || mUXEngine_events == null)
            return -1;
        try
        {
            i = ((Integer)mUXEngine_events.invoke(mPerf, new Object[] {
                Integer.valueOf(i), Integer.valueOf(j), s, Integer.valueOf(k)
            })).intValue();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("BoostFramework", (new StringBuilder()).append("Exception ").append(s).toString());
            i = byte0;
        }
        return i;
    }

    public String perfUXEngine_trigger(int i)
    {
        String s = null;
        if(mIopv2 == 0 || mUXEngine_trigger == null)
            return null;
        String s1 = (String)mUXEngine_trigger.invoke(mPerf, new Object[] {
            Integer.valueOf(i)
        });
        s = s1;
_L2:
        return s;
        Exception exception;
        exception;
        Log.e("BoostFramework", (new StringBuilder()).append("Exception ").append(exception).toString());
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String PERFORMANCE_CLASS = "com.qualcomm.qti.Performance";
    private static final String PERFORMANCE_JAR = "/system/framework/QPerformance.jar";
    private static final String TAG = "BoostFramework";
    public static final int VENDOR_HINT_ACTIVITY_BOOST = 4228;
    public static final int VENDOR_HINT_ANIM_BOOST = 4227;
    public static final int VENDOR_HINT_DRAG_BOOST = 4231;
    public static final int VENDOR_HINT_FIRST_DRAW = 4162;
    public static final int VENDOR_HINT_FIRST_LAUNCH_BOOST = 4225;
    public static final int VENDOR_HINT_MTP_BOOST = 4230;
    public static final int VENDOR_HINT_PACKAGE_INSTALL_BOOST = 4232;
    public static final int VENDOR_HINT_SCROLL_BOOST = 4224;
    public static final int VENDOR_HINT_SUBSEQ_LAUNCH_BOOST = 4226;
    public static final int VENDOR_HINT_TAP_EVENT = 4163;
    public static final int VENDOR_HINT_TOUCH_BOOST = 4229;
    private static Method mAcquireFunc = null;
    private static Constructor mConstructor = null;
    private static Method mIOPStart = null;
    private static Method mIOPStop = null;
    private static int mIopv2 = SystemProperties.getInt("iop.enable_uxe", 0);
    private static boolean mIsLoaded = false;
    private static Class mPerfClass = null;
    private static Method mPerfHintFunc = null;
    private static Method mReleaseFunc = null;
    private static Method mReleaseHandlerFunc = null;
    private static Method mUXEngine_events = null;
    private static Method mUXEngine_trigger = null;
    private Object mPerf;

}
