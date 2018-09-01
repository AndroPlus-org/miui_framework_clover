// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class UEventObserver
{
    public static final class UEvent
    {

        public String get(String s)
        {
            return (String)mMap.get(s);
        }

        public String get(String s, String s1)
        {
            s = (String)mMap.get(s);
            if(s != null)
                s1 = s;
            return s1;
        }

        public String toString()
        {
            return mMap.toString();
        }

        private final HashMap mMap = new HashMap();

        public UEvent(String s)
        {
            int i = 0;
            int j = s.length();
            do
            {
                int k;
                int l;
label0:
                {
                    if(i < j)
                    {
                        k = s.indexOf('=', i);
                        l = s.indexOf('\0', i);
                        if(l >= 0)
                            break label0;
                    }
                    return;
                }
                if(k > i && k < l)
                    mMap.put(s.substring(i, k), s.substring(k + 1, l));
                i = l + 1;
            } while(true);
        }
    }

    private static final class UEventThread extends Thread
    {

        private void sendEvent(String s)
        {
            ArrayList arraylist = mKeysAndObservers;
            arraylist;
            JVM INSTR monitorenter ;
            int i = mKeysAndObservers.size();
            int k = 0;
_L3:
            if(k >= i) goto _L2; else goto _L1
_L1:
            if(s.contains((String)mKeysAndObservers.get(k)))
            {
                UEventObserver ueventobserver = (UEventObserver)mKeysAndObservers.get(k + 1);
                mTempObserversToSignal.add(ueventobserver);
            }
            k += 2;
              goto _L3
_L2:
            if(mTempObserversToSignal.isEmpty())
                break MISSING_BLOCK_LABEL_147;
            s = new UEvent(s);
            int j = mTempObserversToSignal.size();
            for(int l = 0; l < j; l++)
                ((UEventObserver)mTempObserversToSignal.get(l)).onUEvent(s);

            break MISSING_BLOCK_LABEL_140;
            s;
            throw s;
            mTempObserversToSignal.clear();
        }

        public void addObserver(String s, UEventObserver ueventobserver)
        {
            ArrayList arraylist = mKeysAndObservers;
            arraylist;
            JVM INSTR monitorenter ;
            mKeysAndObservers.add(s);
            mKeysAndObservers.add(ueventobserver);
            UEventObserver._2D_wrap1(s);
            arraylist;
            JVM INSTR monitorexit ;
            return;
            s;
            throw s;
        }

        public void removeObserver(UEventObserver ueventobserver)
        {
            ArrayList arraylist = mKeysAndObservers;
            arraylist;
            JVM INSTR monitorenter ;
            int i = 0;
_L2:
            if(i >= mKeysAndObservers.size())
                break; /* Loop/switch isn't completed */
            if(mKeysAndObservers.get(i + 1) == ueventobserver)
            {
                mKeysAndObservers.remove(i + 1);
                UEventObserver._2D_wrap2((String)mKeysAndObservers.remove(i));
                continue; /* Loop/switch isn't completed */
            }
            break MISSING_BLOCK_LABEL_67;
            ueventobserver;
            throw ueventobserver;
            i += 2;
            if(true) goto _L2; else goto _L1
_L1:
        }

        public void run()
        {
            UEventObserver._2D_wrap3();
            do
            {
                String s;
                do
                    s = UEventObserver._2D_wrap0();
                while(s == null);
                sendEvent(s);
            } while(true);
        }

        private final ArrayList mKeysAndObservers = new ArrayList();
        private final ArrayList mTempObserversToSignal = new ArrayList();

        public UEventThread()
        {
            super("UEventObserver");
        }
    }


    static String _2D_wrap0()
    {
        return nativeWaitForNextEvent();
    }

    static void _2D_wrap1(String s)
    {
        nativeAddMatch(s);
    }

    static void _2D_wrap2(String s)
    {
        nativeRemoveMatch(s);
    }

    static void _2D_wrap3()
    {
        nativeSetup();
    }

    public UEventObserver()
    {
    }

    private static UEventThread getThread()
    {
        android/os/UEventObserver;
        JVM INSTR monitorenter ;
        UEventThread ueventthread1;
        if(sThread == null)
        {
            UEventThread ueventthread = JVM INSTR new #9   <Class UEventObserver$UEventThread>;
            ueventthread.UEventThread();
            sThread = ueventthread;
            sThread.start();
        }
        ueventthread1 = sThread;
        android/os/UEventObserver;
        JVM INSTR monitorexit ;
        return ueventthread1;
        Exception exception;
        exception;
        throw exception;
    }

    private static native void nativeAddMatch(String s);

    private static native void nativeRemoveMatch(String s);

    private static native void nativeSetup();

    private static native String nativeWaitForNextEvent();

    private static UEventThread peekThread()
    {
        android/os/UEventObserver;
        JVM INSTR monitorenter ;
        UEventThread ueventthread = sThread;
        android/os/UEventObserver;
        JVM INSTR monitorexit ;
        return ueventthread;
        Exception exception;
        exception;
        throw exception;
    }

    protected void finalize()
        throws Throwable
    {
        stopObserving();
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public abstract void onUEvent(UEvent uevent);

    public final void startObserving(String s)
    {
        if(s == null || s.isEmpty())
        {
            throw new IllegalArgumentException("match substring must be non-empty");
        } else
        {
            getThread().addObserver(s, this);
            return;
        }
    }

    public final void stopObserving()
    {
        UEventThread ueventthread = getThread();
        if(ueventthread != null)
            ueventthread.removeObserver(this);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "UEventObserver";
    private static UEventThread sThread;
}
