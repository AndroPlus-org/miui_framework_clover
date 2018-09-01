// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.xiaomi.joyose;

import android.os.*;

// Referenced classes of package com.xiaomi.joyose:
//            IJoyoseInterface, IGameEngineCallback

public class JoyoseManager
{
    static class JoyoseManagerDeath
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            JoyoseManager._2D_set0(null);
            if(mToken != null)
                mToken.asBinder().unlinkToDeath(this, 0);
        }

        private IJoyoseInterface mToken;

        JoyoseManagerDeath(IJoyoseInterface ijoyoseinterface)
        {
            mToken = ijoyoseinterface;
        }
    }


    static IJoyoseInterface _2D_set0(IJoyoseInterface ijoyoseinterface)
    {
        js = ijoyoseinterface;
        return ijoyoseinterface;
    }

    public JoyoseManager()
    {
    }

    private static void checkService()
    {
        getService();
    }

    private static IJoyoseInterface getService()
    {
        if(js == null)
        {
            js = IJoyoseInterface.Stub.asInterface(ServiceManager.getService("xiaomi.joyose"));
            try
            {
                if(js != null)
                {
                    JoyoseManagerDeath joyosemanagerdeath = JVM INSTR new #6   <Class JoyoseManager$JoyoseManagerDeath>;
                    joyosemanagerdeath.JoyoseManagerDeath(js);
                    js.asBinder().linkToDeath(joyosemanagerdeath, 0);
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                js = null;
            }
        }
        return js;
    }

    public static void handleGameBoosterForOneway(int i, String s)
    {
        checkService();
        if(js == null)
            break MISSING_BLOCK_LABEL_19;
        js.handleGameBoosterForOneway(i, s);
_L1:
        return;
        s;
        s.printStackTrace();
          goto _L1
    }

    public static String handleGameBoosterForSync(int i, String s)
    {
        checkService();
        Object obj = null;
        String s1 = obj;
        if(js != null)
            try
            {
                s1 = js.handleGameBoosterForSync(i, s);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                s1 = obj;
            }
        return s1;
    }

    public static void registerGameEngineListener(int i, IGameEngineCallback igameenginecallback)
    {
        checkService();
        if(js == null)
            break MISSING_BLOCK_LABEL_19;
        js.registerGameEngineListener(i, igameenginecallback);
_L1:
        return;
        igameenginecallback;
        igameenginecallback.printStackTrace();
          goto _L1
    }

    public static void unRegisterGameEngineListener(IGameEngineCallback igameenginecallback)
    {
        checkService();
        if(js == null)
            break MISSING_BLOCK_LABEL_18;
        js.unRegisterGameEngineListener(igameenginecallback);
_L1:
        return;
        igameenginecallback;
        igameenginecallback.printStackTrace();
          goto _L1
    }

    public static final String SERVICE_NAME = "xiaomi.joyose";
    private static IJoyoseInterface js = null;

}
