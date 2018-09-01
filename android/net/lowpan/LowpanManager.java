// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;

import android.content.Context;
import android.os.*;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.net.lowpan:
//            LowpanInterface, ILowpanInterface, ILowpanManager, LowpanException, 
//            ILowpanManagerListener

public class LowpanManager
{
    public static abstract class Callback
    {

        public void onInterfaceAdded(LowpanInterface lowpaninterface)
        {
        }

        public void onInterfaceRemoved(LowpanInterface lowpaninterface)
        {
        }

        public Callback()
        {
        }
    }


    static Map _2D_get0(LowpanManager lowpanmanager)
    {
        return lowpanmanager.mInterfaceCache;
    }

    static Looper _2D_get1(LowpanManager lowpanmanager)
    {
        return lowpanmanager.mLooper;
    }

    public LowpanManager(Context context, ILowpanManager ilowpanmanager, Looper looper)
    {
        mListenerMap = new HashMap();
        mInterfaceCache = new HashMap();
        mBinderCache = new WeakHashMap();
        mContext = context;
        mService = ilowpanmanager;
        mLooper = looper;
    }

    LowpanManager(ILowpanManager ilowpanmanager)
    {
        mListenerMap = new HashMap();
        mInterfaceCache = new HashMap();
        mBinderCache = new WeakHashMap();
        mService = ilowpanmanager;
        mContext = null;
        mLooper = null;
    }

    public static LowpanManager from(Context context)
    {
        return (LowpanManager)context.getSystemService("lowpan");
    }

    public static LowpanManager getManager()
    {
        IBinder ibinder = ServiceManager.getService("lowpan");
        if(ibinder != null)
            return new LowpanManager(ILowpanManager.Stub.asInterface(ibinder));
        else
            return null;
    }

    public LowpanInterface getInterface()
    {
        String as[] = getInterfaceList();
        if(as.length > 0)
            return getInterface(as[0]);
        else
            return null;
    }

    public LowpanInterface getInterface(ILowpanInterface ilowpaninterface)
    {
        Map map = mBinderCache;
        map;
        JVM INSTR monitorenter ;
        if(!mBinderCache.containsKey(ilowpaninterface)) goto _L2; else goto _L1
_L1:
        LowpanInterface lowpaninterface = (LowpanInterface)((WeakReference)mBinderCache.get(ilowpaninterface)).get();
_L5:
        if(lowpaninterface != null)
            break MISSING_BLOCK_LABEL_156;
        String s;
        s = ilowpaninterface.getName();
        lowpaninterface = JVM INSTR new #119 <Class LowpanInterface>;
        lowpaninterface.LowpanInterface(mContext, ilowpaninterface, mLooper);
        Object obj = mInterfaceCache;
        obj;
        JVM INSTR monitorenter ;
        mInterfaceCache.put(lowpaninterface.getName(), lowpaninterface);
        obj;
        JVM INSTR monitorexit ;
        Object obj1 = mBinderCache;
        obj = JVM INSTR new #114 <Class WeakReference>;
        ((WeakReference) (obj)).WeakReference(lowpaninterface);
        ((Map) (obj1)).put(ilowpaninterface, obj);
        obj = ilowpaninterface.asBinder();
        obj1 = JVM INSTR new #6   <Class LowpanManager$1>;
        ((_cls1) (obj1)).this. _cls1();
        ((IBinder) (obj)).linkToDeath(((android.os.IBinder.DeathRecipient) (obj1)), 0);
        map;
        JVM INSTR monitorexit ;
        return lowpaninterface;
        ilowpaninterface;
        obj;
        JVM INSTR monitorexit ;
        throw ilowpaninterface;
        ilowpaninterface;
_L3:
        map;
        JVM INSTR monitorexit ;
        throw ilowpaninterface;
        ilowpaninterface;
        throw ilowpaninterface.rethrowAsRuntimeException();
        ilowpaninterface;
        if(true) goto _L3; else goto _L2
_L2:
        lowpaninterface = null;
        if(true) goto _L5; else goto _L4
_L4:
    }

    public LowpanInterface getInterface(String s)
    {
        Object obj = null;
        Map map = mInterfaceCache;
        map;
        JVM INSTR monitorenter ;
        if(!mInterfaceCache.containsKey(s)) goto _L2; else goto _L1
_L1:
        s = (LowpanInterface)mInterfaceCache.get(s);
_L4:
        map;
        JVM INSTR monitorexit ;
        return s;
_L2:
        ILowpanInterface ilowpaninterface = mService.getInterface(s);
        s = obj;
        if(ilowpaninterface == null) goto _L4; else goto _L3
_L3:
        s = getInterface(ilowpaninterface);
          goto _L4
        s;
        map;
        JVM INSTR monitorexit ;
        throw s;
        s;
        throw s.rethrowFromSystemServer();
    }

    public String[] getInterfaceList()
    {
        String as[];
        try
        {
            as = mService.getInterfaceList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return as;
    }

    public void registerCallback(Callback callback)
        throws LowpanException
    {
        registerCallback(callback, null);
    }

    public void registerCallback(final Callback cb, final Handler handler)
        throws LowpanException
    {
        ILowpanManagerListener.Stub stub = new ILowpanManagerListener.Stub() {

            void lambda$_2D_android_net_lowpan_LowpanManager$2_8833(ILowpanInterface ilowpaninterface, Callback callback)
            {
                ilowpaninterface = getInterface(ilowpaninterface);
                if(ilowpaninterface != null)
                    callback.onInterfaceAdded(ilowpaninterface);
            }

            void lambda$_2D_android_net_lowpan_LowpanManager$2_9391(ILowpanInterface ilowpaninterface, Callback callback)
            {
                ilowpaninterface = getInterface(ilowpaninterface);
                if(ilowpaninterface != null)
                    callback.onInterfaceRemoved(ilowpaninterface);
            }

            public void onInterfaceAdded(ILowpanInterface ilowpaninterface)
            {
                ilowpaninterface = new _.Lambda.fU5N8X3bFktKBQFPK6v4czv7e_Y((byte)0, this, ilowpaninterface, cb);
                mHandler.post(ilowpaninterface);
            }

            public void onInterfaceRemoved(ILowpanInterface ilowpaninterface)
            {
                ilowpaninterface = new _.Lambda.fU5N8X3bFktKBQFPK6v4czv7e_Y((byte)1, this, ilowpaninterface, cb);
                mHandler.post(ilowpaninterface);
            }

            private Handler mHandler;
            final LowpanManager this$0;
            final Callback val$cb;
            final Handler val$handler;

            
            {
                this$0 = LowpanManager.this;
                handler = handler1;
                cb = callback;
                super();
                if(handler != null)
                    mHandler = handler;
                else
                if(LowpanManager._2D_get1(LowpanManager.this) != null)
                    mHandler = new Handler(LowpanManager._2D_get1(LowpanManager.this));
                else
                    mHandler = new Handler();
            }
        }
;
        try
        {
            mService.addListener(stub);
        }
        // Misplaced declaration of an exception variable
        catch(final Callback cb)
        {
            throw cb.rethrowFromSystemServer();
        }
        handler = mListenerMap;
        handler;
        JVM INSTR monitorenter ;
        mListenerMap.put(Integer.valueOf(System.identityHashCode(cb)), stub);
        handler;
        JVM INSTR monitorexit ;
        return;
        cb;
        throw cb;
    }

    public void unregisterCallback(Callback callback)
    {
        Integer integer = Integer.valueOf(System.identityHashCode(callback));
        callback = mListenerMap;
        callback;
        JVM INSTR monitorenter ;
        ILowpanManagerListener ilowpanmanagerlistener;
        ilowpanmanagerlistener = (ILowpanManagerListener)mListenerMap.get(integer);
        mListenerMap.remove(integer);
        callback;
        JVM INSTR monitorexit ;
        Exception exception;
        if(ilowpanmanagerlistener != null)
            try
            {
                mService.removeListener(ilowpanmanagerlistener);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Callback callback)
            {
                throw callback.rethrowFromSystemServer();
            }
        else
            throw new RuntimeException("Attempt to unregister an unknown callback");
        exception;
        throw exception;
    }

    private static final String TAG = android/net/lowpan/LowpanManager.getSimpleName();
    private final Map mBinderCache;
    private final Context mContext;
    private final Map mInterfaceCache;
    private final Map mListenerMap;
    private final Looper mLooper;
    private final ILowpanManager mService;


    // Unreferenced inner class android/net/lowpan/LowpanManager$1

/* anonymous class */
    class _cls1
        implements android.os.IBinder.DeathRecipient
    {

        public void binderDied()
        {
            Map map = LowpanManager._2D_get0(LowpanManager.this);
            map;
            JVM INSTR monitorenter ;
            LowpanInterface lowpaninterface = (LowpanInterface)LowpanManager._2D_get0(LowpanManager.this).get(ifaceName);
            if(lowpaninterface == null)
                break MISSING_BLOCK_LABEL_62;
            if(lowpaninterface.getService() == ifaceService)
                LowpanManager._2D_get0(LowpanManager.this).remove(ifaceName);
            map;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        final LowpanManager this$0;
        final String val$ifaceName;
        final ILowpanInterface val$ifaceService;

            
            {
                this$0 = LowpanManager.this;
                ifaceName = s;
                ifaceService = ilowpaninterface;
                super();
            }
    }

}
