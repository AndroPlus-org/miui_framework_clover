// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.app.MobileDataUtils;
import android.content.*;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.*;

public final class NotifierManager
{
    public static abstract class BaseNotifier
    {

        private final void checkListeners()
        {
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            checkListenersLocked();
            arraylist;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private final void checkListenersLocked()
        {
            mActiveCount = 0;
            int i = mListeners.size() - 1;
            while(i >= 0) 
            {
                Listener listener = (Listener)mListeners.get(i);
                if(listener.ref.get() == null)
                    mListeners.remove(i);
                else
                if(!Listener._2D_get0(listener))
                    mActiveCount = mActiveCount + 1;
                i--;
            }
            mRefCount = mListeners.size();
        }

        private final Listener findListenerLocked(OnNotifyListener onnotifylistener)
        {
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext();)
            {
                Listener listener = (Listener)iterator.next();
                if(listener.ref.get() == onnotifylistener)
                    return listener;
            }

            return null;
        }

        public final void addListener(OnNotifyListener onnotifylistener)
        {
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            if(findListenerLocked(onnotifylistener) == null)
            {
                ArrayList arraylist1 = mListeners;
                Listener listener = JVM INSTR new #9   <Class NotifierManager$BaseNotifier$Listener>;
                listener.Listener(onnotifylistener);
                arraylist1.add(listener);
                checkListenersLocked();
            }
            arraylist;
            JVM INSTR monitorexit ;
            return;
            onnotifylistener;
            throw onnotifylistener;
        }

        public void finish()
        {
            unregister();
        }

        public final int getActiveCount()
        {
            checkListeners();
            return mActiveCount;
        }

        public final int getRef()
        {
            checkListeners();
            return mRefCount;
        }

        public void init()
        {
            register();
        }

        protected void onNotify(Context context, Intent intent, Object obj)
        {
            checkListeners();
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            for(Iterator iterator = mListeners.iterator(); iterator.hasNext(); ((Listener)iterator.next()).onNotify(context, intent, obj));
            break MISSING_BLOCK_LABEL_59;
            context;
            throw context;
            arraylist;
            JVM INSTR monitorexit ;
        }

        protected abstract void onRegister();

        protected abstract void onUnregister();

        public void pause()
        {
            unregister();
        }

        public final int pauseListener(OnNotifyListener onnotifylistener)
        {
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            onnotifylistener = findListenerLocked(onnotifylistener);
            if(onnotifylistener != null)
                break MISSING_BLOCK_LABEL_34;
            int i;
            Log.w("NotifierManager", "pauseListener, listener not exist");
            i = mActiveCount;
            arraylist;
            JVM INSTR monitorexit ;
            return i;
            onnotifylistener.pause();
            checkListenersLocked();
            i = mActiveCount;
            arraylist;
            JVM INSTR monitorexit ;
            return i;
            onnotifylistener;
            throw onnotifylistener;
        }

        protected void register()
        {
            if(mRegistered)
                return;
            onRegister();
            mRegistered = true;
            if(NotifierManager._2D_get0())
                Log.i("NotifierManager", (new StringBuilder()).append("onRegister: ").append(toString()).toString());
        }

        public final void removeListener(OnNotifyListener onnotifylistener)
        {
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            onnotifylistener = findListenerLocked(onnotifylistener);
            if(onnotifylistener == null)
                break MISSING_BLOCK_LABEL_30;
            mListeners.remove(onnotifylistener);
            checkListenersLocked();
            arraylist;
            JVM INSTR monitorexit ;
            return;
            onnotifylistener;
            throw onnotifylistener;
        }

        public void resume()
        {
            register();
        }

        public final int resumeListener(OnNotifyListener onnotifylistener)
        {
            ArrayList arraylist = mListeners;
            arraylist;
            JVM INSTR monitorenter ;
            onnotifylistener = findListenerLocked(onnotifylistener);
            if(onnotifylistener != null)
                break MISSING_BLOCK_LABEL_34;
            int i;
            Log.w("NotifierManager", "resumeListener, listener not exist");
            i = mActiveCount;
            arraylist;
            JVM INSTR monitorexit ;
            return i;
            onnotifylistener.resume();
            checkListenersLocked();
            i = mActiveCount;
            arraylist;
            JVM INSTR monitorexit ;
            return i;
            onnotifylistener;
            throw onnotifylistener;
        }

        protected void unregister()
        {
            if(!mRegistered)
                return;
            try
            {
                onUnregister();
            }
            catch(IllegalArgumentException illegalargumentexception)
            {
                Log.w("NotifierManager", illegalargumentexception.toString());
            }
            mRegistered = false;
            if(NotifierManager._2D_get0())
                Log.i("NotifierManager", (new StringBuilder()).append("onUnregister: ").append(toString()).toString());
        }

        private int mActiveCount;
        protected Context mContext;
        private ArrayList mListeners;
        private int mRefCount;
        private boolean mRegistered;

        public BaseNotifier(Context context)
        {
            mListeners = new ArrayList();
            mContext = context;
        }
    }

    private static class BaseNotifier.Listener
    {

        static boolean _2D_get0(BaseNotifier.Listener listener)
        {
            return listener.paused;
        }

        public void onNotify(Context context1, Intent intent1, Object obj1)
        {
            if(!paused) goto _L2; else goto _L1
_L1:
            pendingNotify = true;
            context = context1;
            intent = intent1;
            obj = obj1;
_L4:
            return;
_L2:
            OnNotifyListener onnotifylistener = (OnNotifyListener)ref.get();
            if(onnotifylistener != null)
                onnotifylistener.onNotify(context1, intent1, obj1);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void pause()
        {
            paused = true;
        }

        public void resume()
        {
            paused = false;
            if(pendingNotify)
            {
                OnNotifyListener onnotifylistener = (OnNotifyListener)ref.get();
                if(onnotifylistener != null)
                {
                    onnotifylistener.onNotify(context, intent, obj);
                    pendingNotify = false;
                    context = null;
                    intent = null;
                    obj = null;
                }
            }
        }

        public Context context;
        public Intent intent;
        public Object obj;
        private boolean paused;
        private boolean pendingNotify;
        public WeakReference ref;

        public BaseNotifier.Listener(OnNotifyListener onnotifylistener)
        {
            ref = new WeakReference(onnotifylistener);
        }
    }

    public static class BroadcastNotifier extends BaseNotifier
    {

        protected IntentFilter createIntentFilter()
        {
            IntentFilter intentfilter = null;
            String s = getIntentAction();
            if(s != null)
                intentfilter = new IntentFilter(s);
            return intentfilter;
        }

        protected String getIntentAction()
        {
            return mAction;
        }

        protected void onRegister()
        {
            if(mIntentFilter == null)
                mIntentFilter = createIntentFilter();
            if(mIntentFilter == null)
            {
                Log.e("NotifierManager", "onRegister: mIntentFilter is null");
                return;
            }
            Intent intent = mContext.registerReceiver(mIntentReceiver, mIntentFilter);
            if(intent != null)
                onNotify(mContext, intent, null);
        }

        protected void onUnregister()
        {
            mContext.unregisterReceiver(mIntentReceiver);
_L2:
            return;
            IllegalArgumentException illegalargumentexception;
            illegalargumentexception;
            if(true) goto _L2; else goto _L1
_L1:
        }

        private String mAction;
        private IntentFilter mIntentFilter;
        private final BroadcastReceiver mIntentReceiver;

        public BroadcastNotifier(Context context)
        {
            super(context);
            mIntentReceiver = new _cls1();
        }

        public BroadcastNotifier(Context context, String s)
        {
            super(context);
            mIntentReceiver = new _cls1();
            mAction = s;
        }
    }

    public static class ContentChangeNotifier extends BaseNotifier
    {

        protected void onRegister()
        {
            mContext.getContentResolver().registerContentObserver(mUri, false, mObserver);
            onNotify(null, null, Boolean.valueOf(true));
        }

        protected void onUnregister()
        {
            mContext.getContentResolver().unregisterContentObserver(mObserver);
        }

        protected final ContentObserver mObserver = new _cls1(null);
        private Uri mUri;

        public ContentChangeNotifier(Context context, Uri uri)
        {
            super(context);
            mUri = uri;
        }
    }

    public static class MobileDataNotifier extends ContentChangeNotifier
    {

        protected void onRegister()
        {
            mMobileDataUtils.registerContentObserver(mContext, mObserver);
            onNotify(null, null, Boolean.valueOf(true));
        }

        private MobileDataUtils mMobileDataUtils;

        public MobileDataNotifier(Context context)
        {
            super(context, null);
            mMobileDataUtils = MobileDataUtils.getInstance();
        }
    }

    public static class MultiBroadcastNotifier extends BroadcastNotifier
    {

        protected IntentFilter createIntentFilter()
        {
            IntentFilter intentfilter = new IntentFilter();
            String as[] = mIntents;
            int i = 0;
            for(int j = as.length; i < j; i++)
                intentfilter.addAction(as[i]);

            return intentfilter;
        }

        private String mIntents[];

        public transient MultiBroadcastNotifier(Context context, String as[])
        {
            super(context);
            mIntents = as;
        }
    }

    public static interface OnNotifyListener
    {

        public abstract void onNotify(Context context, Intent intent, Object obj);
    }


    static boolean _2D_get0()
    {
        return DBG;
    }

    private NotifierManager(Context context)
    {
        mNotifiers = new HashMap();
        mContext = context;
    }

    private static BaseNotifier createNotifier(String s, Context context)
    {
        if(DBG)
            Log.i("NotifierManager", (new StringBuilder()).append("createNotifier:").append(s).toString());
        if(TYPE_MOBILE_DATA.equals(s))
            return new MobileDataNotifier(context);
        if(TYPE_WIFI_STATE.equals(s))
            return new MultiBroadcastNotifier(context, new String[] {
                "android.net.wifi.WIFI_STATE_CHANGED", "android.net.wifi.SCAN_RESULTS", "android.net.wifi.STATE_CHANGE"
            });
        if(TYPE_TIME_CHANGED.equals(s))
            return new MultiBroadcastNotifier(context, new String[] {
                "android.intent.action.TIMEZONE_CHANGED", "android.intent.action.TIME_SET"
            });
        else
            return new BroadcastNotifier(context, s);
    }

    public static NotifierManager getInstance(Context context)
    {
        miui/maml/NotifierManager;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            NotifierManager notifiermanager = JVM INSTR new #2   <Class NotifierManager>;
            notifiermanager.NotifierManager(context);
            sInstance = notifiermanager;
        }
        context = sInstance;
        miui/maml/NotifierManager;
        JVM INSTR monitorexit ;
        return context;
        context;
        throw context;
    }

    private BaseNotifier safeGet(String s)
    {
        HashMap hashmap = mNotifiers;
        hashmap;
        JVM INSTR monitorenter ;
        s = (BaseNotifier)mNotifiers.get(s);
        hashmap;
        JVM INSTR monitorexit ;
        return s;
        s;
        throw s;
    }

    public void acquireNotifier(String s, OnNotifyListener onnotifylistener)
    {
        if(DBG)
            Log.i("NotifierManager", (new StringBuilder()).append("acquireNotifier:").append(s).append("  ").append(onnotifylistener.toString()).toString());
        HashMap hashmap = mNotifiers;
        hashmap;
        JVM INSTR monitorenter ;
        BaseNotifier basenotifier = (BaseNotifier)mNotifiers.get(s);
        BaseNotifier basenotifier1;
        basenotifier1 = basenotifier;
        if(basenotifier != null)
            break MISSING_BLOCK_LABEL_106;
        basenotifier1 = createNotifier(s, mContext);
        if(basenotifier1 != null)
            break MISSING_BLOCK_LABEL_90;
        hashmap;
        JVM INSTR monitorexit ;
        return;
        basenotifier1.init();
        mNotifiers.put(s, basenotifier1);
        hashmap;
        JVM INSTR monitorexit ;
        basenotifier1.addListener(onnotifylistener);
        return;
        s;
        throw s;
    }

    public void pause(String s, OnNotifyListener onnotifylistener)
    {
        s = safeGet(s);
        if(s == null)
            return;
        if(s.pauseListener(onnotifylistener) == 0)
            s.pause();
    }

    public void releaseNotifier(String s, OnNotifyListener onnotifylistener)
    {
        if(DBG)
            Log.i("NotifierManager", (new StringBuilder()).append("releaseNotifier:").append(s).append("  ").append(onnotifylistener.toString()).toString());
        HashMap hashmap = mNotifiers;
        hashmap;
        JVM INSTR monitorenter ;
        BaseNotifier basenotifier = (BaseNotifier)mNotifiers.get(s);
        if(basenotifier != null)
            break MISSING_BLOCK_LABEL_71;
        hashmap;
        JVM INSTR monitorexit ;
        return;
        basenotifier.removeListener(onnotifylistener);
        if(basenotifier.getRef() == 0)
        {
            basenotifier.finish();
            mNotifiers.remove(s);
        }
        hashmap;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void resume(String s, OnNotifyListener onnotifylistener)
    {
        this;
        JVM INSTR monitorenter ;
        s = safeGet(s);
        if(s != null)
            break MISSING_BLOCK_LABEL_15;
        this;
        JVM INSTR monitorexit ;
        return;
        if(s.resumeListener(onnotifylistener) == 1)
            s.resume();
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    private static boolean DBG = false;
    private static final String LOG_TAG = "NotifierManager";
    public static String TYPE_MOBILE_DATA = "MobileData";
    public static String TYPE_TIME_CHANGED = "TimeChanged";
    public static String TYPE_WIFI_STATE = "WifiState";
    private static NotifierManager sInstance;
    private Context mContext;
    private HashMap mNotifiers;

    static 
    {
        DBG = true;
    }

    // Unreferenced inner class miui/maml/NotifierManager$BroadcastNotifier$1

/* anonymous class */
    class BroadcastNotifier._cls1 extends BroadcastReceiver
    {

        public void onReceive(Context context, Intent intent)
        {
            if(NotifierManager._2D_get0())
                Log.i("NotifierManager", (new StringBuilder()).append("onNotify: ").append(toString()).toString());
            onNotify(context, intent, null);
        }

        final BroadcastNotifier this$1;

            
            {
                this$1 = BroadcastNotifier.this;
                super();
            }
    }


    // Unreferenced inner class miui/maml/NotifierManager$ContentChangeNotifier$1

/* anonymous class */
    class ContentChangeNotifier._cls1 extends ContentObserver
    {

        public void onChange(boolean flag)
        {
            if(NotifierManager._2D_get0())
                Log.i("NotifierManager", (new StringBuilder()).append("onNotify: ").append(toString()).toString());
            onNotify(null, null, Boolean.valueOf(flag));
        }

        final ContentChangeNotifier this$1;

            
            {
                this$1 = ContentChangeNotifier.this;
                super(handler);
            }
    }

}
