// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityThread;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.*;
import android.util.*;
import android.view.IWindow;
import android.view.View;
import com.android.internal.util.IntPair;
import java.util.*;
import miui.os.SystemProperties;

// Referenced classes of package android.view.accessibility:
//            IAccessibilityManager, AccessibilityRequestPreparer, AccessibilityEvent, IAccessibilityInteractionConnection, 
//            IAccessibilityManagerClient

public final class AccessibilityManager
{
    public static interface AccessibilityServicesStateChangeListener
    {

        public abstract void onAccessibilityServicesStateChanged(AccessibilityManager accessibilitymanager);
    }

    public static interface AccessibilityStateChangeListener
    {

        public abstract void onAccessibilityStateChanged(boolean flag);
    }

    public static interface HighTextContrastChangeListener
    {

        public abstract void onHighTextContrastStateChanged(boolean flag);
    }

    private final class MyCallback
        implements android.os.Handler.Callback
    {

        public boolean handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 26;
               goto _L1 _L2
_L1:
            return true;
_L2:
            int i = message.arg1;
            Object obj = AccessibilityManager._2D_get0(AccessibilityManager.this);
            obj;
            JVM INSTR monitorenter ;
            AccessibilityManager._2D_wrap0(AccessibilityManager.this, i);
            obj;
            JVM INSTR monitorexit ;
            if(true) goto _L1; else goto _L3
_L3:
            message;
            throw message;
        }

        public static final int MSG_SET_STATE = 1;
        final AccessibilityManager this$0;

        private MyCallback()
        {
            this$0 = AccessibilityManager.this;
            super();
        }

        MyCallback(MyCallback mycallback)
        {
            this();
        }
    }

    public static interface TouchExplorationStateChangeListener
    {

        public abstract void onTouchExplorationStateChanged(boolean flag);
    }


    static Object _2D_get0(AccessibilityManager accessibilitymanager)
    {
        return accessibilitymanager.mLock;
    }

    static ArrayMap _2D_get1(AccessibilityManager accessibilitymanager)
    {
        return accessibilitymanager.mServicesStateChangeListeners;
    }

    static void _2D_wrap0(AccessibilityManager accessibilitymanager, int i)
    {
        accessibilitymanager.setStateLocked(i);
    }

    public AccessibilityManager(Context context, IAccessibilityManager iaccessibilitymanager, int i)
    {
        mLock = new Object();
        mRelevantEventTypes = -1;
        mAccessibilityStateChangeListeners = new ArrayMap();
        mTouchExplorationStateChangeListeners = new ArrayMap();
        mHighTextContrastStateChangeListeners = new ArrayMap();
        mServicesStateChangeListeners = new ArrayMap();
        mCurrentBoundServices = new ArrayList();
        mClient = new IAccessibilityManagerClient.Stub() {

            void lambda$_2D_android_view_accessibility_AccessibilityManager$1_9450(AccessibilityServicesStateChangeListener accessibilityservicesstatechangelistener)
            {
                accessibilityservicesstatechangelistener.onAccessibilityServicesStateChanged(AccessibilityManager.this);
            }

            public void notifyServicesStateChanged()
            {
                Object obj = AccessibilityManager._2D_get0(AccessibilityManager.this);
                obj;
                JVM INSTR monitorenter ;
                boolean flag = AccessibilityManager._2D_get1(AccessibilityManager.this).isEmpty();
                if(!flag)
                    break MISSING_BLOCK_LABEL_28;
                obj;
                JVM INSTR monitorexit ;
                return;
                ArrayMap arraymap = new ArrayMap(AccessibilityManager._2D_get1(AccessibilityManager.this));
                obj;
                JVM INSTR monitorexit ;
                int j = arraymap.size();
                for(int k = 0; k < j; k++)
                {
                    obj = (AccessibilityServicesStateChangeListener)AccessibilityManager._2D_get1(AccessibilityManager.this).keyAt(k);
                    ((Handler)AccessibilityManager._2D_get1(AccessibilityManager.this).valueAt(k)).post(new _.Lambda.T3m_l9_RA18vCOcakSWp1lZCy5g(this, obj));
                }

                break MISSING_BLOCK_LABEL_116;
                Exception exception;
                exception;
                throw exception;
            }

            public void setRelevantEventTypes(int j)
            {
                mRelevantEventTypes = j;
            }

            public void setState(int j)
            {
                mHandler.obtainMessage(1, j, 0).sendToTarget();
            }

            final AccessibilityManager this$0;

            
            {
                this$0 = AccessibilityManager.this;
                super();
            }
        }
;
        mCallback = new MyCallback(null);
        mHandler = new Handler(context.getMainLooper(), mCallback);
        mUserId = i;
        mPackageName = context.getPackageName();
        mOptimizeEnabled = SystemProperties.getBoolean("persist.sys.opt_accessibility", false);
        context = ((Context) (mLock));
        context;
        JVM INSTR monitorenter ;
        tryConnectToServiceLocked(iaccessibilitymanager);
        context;
        JVM INSTR monitorexit ;
        return;
        iaccessibilitymanager;
        throw iaccessibilitymanager;
    }

    public AccessibilityManager(Handler handler, IAccessibilityManager iaccessibilitymanager, int i)
    {
        mLock = new Object();
        mRelevantEventTypes = -1;
        mAccessibilityStateChangeListeners = new ArrayMap();
        mTouchExplorationStateChangeListeners = new ArrayMap();
        mHighTextContrastStateChangeListeners = new ArrayMap();
        mServicesStateChangeListeners = new ArrayMap();
        mCurrentBoundServices = new ArrayList();
        mClient = new _cls1();
        mCallback = new MyCallback(null);
        mHandler = handler;
        mUserId = i;
        mPackageName = null;
        mOptimizeEnabled = SystemProperties.getBoolean("persist.sys.opt_accessibility", false);
        handler = ((Handler) (mLock));
        handler;
        JVM INSTR monitorenter ;
        tryConnectToServiceLocked(iaccessibilitymanager);
        handler;
        JVM INSTR monitorexit ;
        return;
        iaccessibilitymanager;
        throw iaccessibilitymanager;
    }

    public static AccessibilityManager getInstance(Context context)
    {
        Object obj = sInstanceSync;
        obj;
        JVM INSTR monitorenter ;
        if(sInstance != null) goto _L2; else goto _L1
_L1:
        if(Binder.getCallingUid() != 1000 && context.checkCallingOrSelfPermission("android.permission.INTERACT_ACROSS_USERS") != 0) goto _L4; else goto _L3
_L3:
        int i = -2;
_L6:
        AccessibilityManager accessibilitymanager = JVM INSTR new #2   <Class AccessibilityManager>;
        accessibilitymanager.AccessibilityManager(context, null, i);
        sInstance = accessibilitymanager;
_L2:
        obj;
        JVM INSTR monitorexit ;
        return sInstance;
_L4:
        if(context.checkCallingOrSelfPermission("android.permission.INTERACT_ACROSS_USERS_FULL") == 0)
            break; /* Loop/switch isn't completed */
        i = UserHandle.myUserId();
        if(true) goto _L6; else goto _L5
_L5:
        if(true) goto _L3; else goto _L7
_L7:
        context;
        throw context;
    }

    private IAccessibilityManager getServiceLocked()
    {
        if(mService == null)
            tryConnectToServiceLocked(null);
        return mService;
    }

    public static boolean isAccessibilityButtonSupported()
    {
        return Resources.getSystem().getBoolean(0x1120099);
    }

    private boolean isInterestedPackageLocked()
    {
        if(mPackageName == null || ActivityThread.isSystem())
            return true;
        for(Iterator iterator = mCurrentBoundServices.iterator(); iterator.hasNext();)
        {
            String as[] = ((AccessibilityServiceInfo)iterator.next()).packageNames;
            if(as == null)
                return true;
            int i = as.length;
            int j = 0;
            while(j < i) 
            {
                if(as[j].equals(mPackageName))
                    return true;
                j++;
            }
        }

        return false;
    }

    static void lambda$_2D_android_view_accessibility_AccessibilityManager_42657(AccessibilityStateChangeListener accessibilitystatechangelistener, boolean flag)
    {
        accessibilitystatechangelistener.onAccessibilityStateChanged(flag);
    }

    static void lambda$_2D_android_view_accessibility_AccessibilityManager_43616(TouchExplorationStateChangeListener touchexplorationstatechangelistener, boolean flag)
    {
        touchexplorationstatechangelistener.onTouchExplorationStateChanged(flag);
    }

    static void lambda$_2D_android_view_accessibility_AccessibilityManager_44579(HighTextContrastChangeListener hightextcontrastchangelistener, boolean flag)
    {
        hightextcontrastchangelistener.onHighTextContrastStateChanged(flag);
    }

    private void notifyAccessibilityStateChanged()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mAccessibilityStateChangeListeners.isEmpty();
        if(!flag)
            break MISSING_BLOCK_LABEL_22;
        obj;
        JVM INSTR monitorexit ;
        return;
        ArrayMap arraymap;
        flag = mIsEnabled;
        arraymap = new ArrayMap(mAccessibilityStateChangeListeners);
        obj;
        JVM INSTR monitorexit ;
        int i = arraymap.size();
        for(int j = 0; j < i; j++)
        {
            obj = (AccessibilityStateChangeListener)mAccessibilityStateChangeListeners.keyAt(j);
            ((Handler)mAccessibilityStateChangeListeners.valueAt(j)).post(new _.Lambda.T3m_l9_RA18vCOcakSWp1lZCy5g._cls1((byte)0, flag, obj));
        }

        break MISSING_BLOCK_LABEL_107;
        Exception exception;
        exception;
        throw exception;
    }

    private void notifyHighTextContrastStateChanged()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mHighTextContrastStateChangeListeners.isEmpty();
        if(!flag)
            break MISSING_BLOCK_LABEL_22;
        obj;
        JVM INSTR monitorexit ;
        return;
        ArrayMap arraymap;
        flag = mIsHighTextContrastEnabled;
        arraymap = new ArrayMap(mHighTextContrastStateChangeListeners);
        obj;
        JVM INSTR monitorexit ;
        int i = arraymap.size();
        for(int j = 0; j < i; j++)
        {
            obj = (HighTextContrastChangeListener)mHighTextContrastStateChangeListeners.keyAt(j);
            ((Handler)mHighTextContrastStateChangeListeners.valueAt(j)).post(new _.Lambda.T3m_l9_RA18vCOcakSWp1lZCy5g._cls1((byte)1, flag, obj));
        }

        break MISSING_BLOCK_LABEL_107;
        Exception exception;
        exception;
        throw exception;
    }

    private void notifyTouchExplorationStateChanged()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mTouchExplorationStateChangeListeners.isEmpty();
        if(!flag)
            break MISSING_BLOCK_LABEL_22;
        obj;
        JVM INSTR monitorexit ;
        return;
        ArrayMap arraymap;
        flag = mIsTouchExplorationEnabled;
        arraymap = new ArrayMap(mTouchExplorationStateChangeListeners);
        obj;
        JVM INSTR monitorexit ;
        int i = arraymap.size();
        for(int j = 0; j < i; j++)
        {
            obj = (TouchExplorationStateChangeListener)mTouchExplorationStateChangeListeners.keyAt(j);
            ((Handler)mTouchExplorationStateChangeListeners.valueAt(j)).post(new _.Lambda.T3m_l9_RA18vCOcakSWp1lZCy5g._cls1((byte)2, flag, obj));
        }

        break MISSING_BLOCK_LABEL_107;
        Exception exception;
        exception;
        throw exception;
    }

    private void setStateLocked(int i)
    {
        setStateLocked(i, false);
    }

    private void setStateLocked(int i, boolean flag)
    {
        boolean flag1 = false;
        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        boolean flag6;
        boolean flag7;
        boolean flag8;
        boolean flag9;
        if((i & 1) != 0)
            flag2 = true;
        else
            flag2 = false;
        if((i & 2) != 0)
            flag3 = true;
        else
            flag3 = false;
        if((i & 4) != 0)
            flag4 = true;
        else
            flag4 = false;
        if((i & 8) != 0)
            flag5 = true;
        else
            flag5 = false;
        if((i & 0x10) != 0)
            flag6 = true;
        else
            flag6 = false;
        flag7 = mIsEnabled;
        flag8 = mIsTouchExplorationEnabled;
        flag9 = mIsHighTextContrastEnabled;
        mIsEnabled = flag2;
        mIsTouchExplorationEnabled = flag3;
        mIsHighTextContrastEnabled = flag4;
        mIsUiAutomationEnabled = flag6;
        if(flag7 != flag2)
            notifyAccessibilityStateChanged();
        if(flag8 != flag3)
            notifyTouchExplorationStateChanged();
        if(flag9 != flag4)
            notifyHighTextContrastStateChanged();
        if(mIsEnabled && (flag5 || flag))
        {
            List list = getEnabledAccessibilityServiceList(-1);
            mCurrentBoundServices.clear();
            if(list != null)
                mCurrentBoundServices.addAll(list);
            mIsInterestedPackage = isInterestedPackageLocked();
        }
        if(mOptimizeEnabled)
        {
            flag = flag1;
            if(mIsEnabled)
                if(!mIsUiAutomationEnabled)
                    flag = mIsInterestedPackage;
                else
                    flag = true;
        } else
        {
            flag = mIsEnabled;
        }
        mIsFinalEnabled = flag;
        Log.d("AccessibilityManager", (new StringBuilder()).append("AccessibilityManager status: mPackageName = ").append(mPackageName).append(", mOptimizeEnabled = ").append(mOptimizeEnabled).append(", mIsEnabled = ").append(mIsEnabled).append(", mIsUiAutomationEnabled = ").append(mIsUiAutomationEnabled).append(", mIsInterestedPackage =").append(mIsInterestedPackage).toString());
    }

    private void tryConnectToServiceLocked(IAccessibilityManager iaccessibilitymanager)
    {
        IAccessibilityManager iaccessibilitymanager1;
        iaccessibilitymanager1 = iaccessibilitymanager;
        if(iaccessibilitymanager == null)
        {
            iaccessibilitymanager = ServiceManager.getService("accessibility");
            if(iaccessibilitymanager == null)
                return;
            iaccessibilitymanager1 = IAccessibilityManager.Stub.asInterface(iaccessibilitymanager);
        }
        long l = iaccessibilitymanager1.addClient(mClient, mUserId);
        mRelevantEventTypes = IntPair.second(l);
        mService = iaccessibilitymanager1;
        setStateLocked(IntPair.first(l), true);
_L1:
        return;
        iaccessibilitymanager;
        Log.e("AccessibilityManager", "AccessibilityManagerService is dead", iaccessibilitymanager);
          goto _L1
    }

    public int addAccessibilityInteractionConnection(IWindow iwindow, IAccessibilityInteractionConnection iaccessibilityinteractionconnection)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_22;
        obj;
        JVM INSTR monitorexit ;
        return -1;
        int i = mUserId;
        obj;
        JVM INSTR monitorexit ;
        try
        {
            i = iaccessibilitymanager.addAccessibilityInteractionConnection(iwindow, iaccessibilityinteractionconnection, i);
        }
        // Misplaced declaration of an exception variable
        catch(IWindow iwindow)
        {
            Log.e("AccessibilityManager", "Error while adding an accessibility interaction connection. ", iwindow);
            return -1;
        }
        return i;
        iwindow;
        throw iwindow;
    }

    public void addAccessibilityRequestPreparer(AccessibilityRequestPreparer accessibilityrequestpreparer)
    {
        if(mRequestPreparerLists == null)
            mRequestPreparerLists = new SparseArray(1);
        int i = accessibilityrequestpreparer.getView().getAccessibilityViewId();
        List list = (List)mRequestPreparerLists.get(i);
        Object obj = list;
        if(list == null)
        {
            obj = new ArrayList(1);
            mRequestPreparerLists.put(i, obj);
        }
        ((List) (obj)).add(accessibilityrequestpreparer);
    }

    public void addAccessibilityServicesStateChangeListener(AccessibilityServicesStateChangeListener accessibilityservicesstatechangelistener, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = mServicesStateChangeListeners;
        Handler handler1;
        handler1 = handler;
        if(handler != null)
            break MISSING_BLOCK_LABEL_26;
        handler1 = mHandler;
        arraymap.put(accessibilityservicesstatechangelistener, handler1);
        obj;
        JVM INSTR monitorexit ;
        return;
        accessibilityservicesstatechangelistener;
        throw accessibilityservicesstatechangelistener;
    }

    public void addAccessibilityStateChangeListener(AccessibilityStateChangeListener accessibilitystatechangelistener, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = mAccessibilityStateChangeListeners;
        Handler handler1;
        handler1 = handler;
        if(handler != null)
            break MISSING_BLOCK_LABEL_26;
        handler1 = mHandler;
        arraymap.put(accessibilitystatechangelistener, handler1);
        obj;
        JVM INSTR monitorexit ;
        return;
        accessibilitystatechangelistener;
        throw accessibilitystatechangelistener;
    }

    public boolean addAccessibilityStateChangeListener(AccessibilityStateChangeListener accessibilitystatechangelistener)
    {
        addAccessibilityStateChangeListener(accessibilitystatechangelistener, null);
        return true;
    }

    public void addHighTextContrastStateChangeListener(HighTextContrastChangeListener hightextcontrastchangelistener, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = mHighTextContrastStateChangeListeners;
        Handler handler1;
        handler1 = handler;
        if(handler != null)
            break MISSING_BLOCK_LABEL_26;
        handler1 = mHandler;
        arraymap.put(hightextcontrastchangelistener, handler1);
        obj;
        JVM INSTR monitorexit ;
        return;
        hightextcontrastchangelistener;
        throw hightextcontrastchangelistener;
    }

    public void addTouchExplorationStateChangeListener(TouchExplorationStateChangeListener touchexplorationstatechangelistener, Handler handler)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        ArrayMap arraymap = mTouchExplorationStateChangeListeners;
        Handler handler1;
        handler1 = handler;
        if(handler != null)
            break MISSING_BLOCK_LABEL_26;
        handler1 = mHandler;
        arraymap.put(touchexplorationstatechangelistener, handler1);
        obj;
        JVM INSTR monitorexit ;
        return;
        touchexplorationstatechangelistener;
        throw touchexplorationstatechangelistener;
    }

    public boolean addTouchExplorationStateChangeListener(TouchExplorationStateChangeListener touchexplorationstatechangelistener)
    {
        addTouchExplorationStateChangeListener(touchexplorationstatechangelistener, null);
        return true;
    }

    public List getAccessibilityServiceList()
    {
        List list = getInstalledAccessibilityServiceList();
        ArrayList arraylist = new ArrayList();
        int i = list.size();
        for(int j = 0; j < i; j++)
            arraylist.add(((AccessibilityServiceInfo)list.get(j)).getResolveInfo().serviceInfo);

        return Collections.unmodifiableList(arraylist);
    }

    public android.os.Handler.Callback getCallback()
    {
        return mCallback;
    }

    public IAccessibilityManagerClient getClient()
    {
        return mClient;
    }

    public List getEnabledAccessibilityServiceList(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = getServiceLocked();
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_24;
        obj1 = Collections.emptyList();
        obj;
        JVM INSTR monitorexit ;
        return ((List) (obj1));
        int j = mUserId;
        obj;
        JVM INSTR monitorexit ;
        obj = null;
        obj1 = ((IAccessibilityManager) (obj1)).getEnabledAccessibilityServiceList(i, j);
        obj = obj1;
_L1:
        Object obj2;
        if(obj != null)
            return Collections.unmodifiableList(((List) (obj)));
        else
            return Collections.emptyList();
        obj2;
        throw obj2;
        obj2;
        Log.e("AccessibilityManager", "Error while obtaining the installed AccessibilityServices. ", ((Throwable) (obj2)));
          goto _L1
    }

    public List getInstalledAccessibilityServiceList()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = getServiceLocked();
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_24;
        obj1 = Collections.emptyList();
        obj;
        JVM INSTR monitorexit ;
        return ((List) (obj1));
        int i = mUserId;
        obj;
        JVM INSTR monitorexit ;
        obj = null;
        obj1 = ((IAccessibilityManager) (obj1)).getInstalledAccessibilityServiceList(i);
        obj = obj1;
_L1:
        Object obj2;
        if(obj != null)
            return Collections.unmodifiableList(((List) (obj)));
        else
            return Collections.emptyList();
        obj2;
        throw obj2;
        obj2;
        Log.e("AccessibilityManager", "Error while obtaining the installed AccessibilityServices. ", ((Throwable) (obj2)));
          goto _L1
    }

    public AccessibilityServiceInfo getInstalledServiceInfoWithComponentName(ComponentName componentname)
    {
        List list = getInstalledAccessibilityServiceList();
        if(list == null || componentname == null)
            return null;
        for(int i = 0; i < list.size(); i++)
            if(componentname.equals(((AccessibilityServiceInfo)list.get(i)).getComponentName()))
                return (AccessibilityServiceInfo)list.get(i);

        return null;
    }

    public List getRequestPreparersForAccessibilityId(int i)
    {
        if(mRequestPreparerLists == null)
            return null;
        else
            return (List)mRequestPreparerLists.get(i);
    }

    public void interrupt()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = getServiceLocked();
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        if(mIsEnabled)
            break MISSING_BLOCK_LABEL_65;
        if(Looper.myLooper() == Looper.getMainLooper())
        {
            obj1 = JVM INSTR new #511 <Class IllegalStateException>;
            ((IllegalStateException) (obj1)).IllegalStateException("Accessibility off. Did you forget to check that?");
            throw obj1;
        }
        break MISSING_BLOCK_LABEL_53;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        Log.e("AccessibilityManager", "Interrupt called with accessibility disabled");
        obj;
        JVM INSTR monitorexit ;
        return;
        int i = mUserId;
        obj;
        JVM INSTR monitorexit ;
        ((IAccessibilityManager) (obj1)).interrupt(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("AccessibilityManager", "Error while requesting interrupt from all services. ", remoteexception);
          goto _L1
    }

    public boolean isAccessibilityVolumeStreamActive()
    {
        List list = getEnabledAccessibilityServiceList(-1);
        for(int i = 0; i < list.size(); i++)
            if((((AccessibilityServiceInfo)list.get(i)).flags & 0x80) != 0)
                return true;

        return false;
    }

    public boolean isEnabled()
    {
        return isFinalEnabled();
    }

    public boolean isFinalEnabled()
    {
        if(mService != null) goto _L2; else goto _L1
_L1:
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_27;
        obj;
        JVM INSTR monitorexit ;
        return false;
        obj;
        JVM INSTR monitorexit ;
_L2:
        return mIsFinalEnabled;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isHighTextContrastEnabled()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_20;
        obj;
        JVM INSTR monitorexit ;
        return false;
        boolean flag = mIsHighTextContrastEnabled;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isTouchExplorationEnabled()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_20;
        obj;
        JVM INSTR monitorexit ;
        return false;
        boolean flag = mIsTouchExplorationEnabled;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void notifyAccessibilityButtonClicked()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
        iaccessibilitymanager.notifyAccessibilityButtonClicked();
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        Log.e("AccessibilityManager", "Error while dispatching accessibility button click", remoteexception);
          goto _L1
    }

    public void notifyAccessibilityButtonVisibilityChanged(boolean flag)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
        iaccessibilitymanager.notifyAccessibilityButtonVisibilityChanged(flag);
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        Log.e("AccessibilityManager", "Error while dispatching accessibility button visibility change", remoteexception);
          goto _L1
    }

    public void performAccessibilityShortcut()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
        iaccessibilitymanager.performAccessibilityShortcut();
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        Log.e("AccessibilityManager", "Error performing accessibility shortcut. ", remoteexception);
          goto _L1
    }

    public void removeAccessibilityInteractionConnection(IWindow iwindow)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
        iaccessibilitymanager.removeAccessibilityInteractionConnection(iwindow);
_L1:
        return;
        iwindow;
        throw iwindow;
        iwindow;
        Log.e("AccessibilityManager", "Error while removing an accessibility interaction connection. ", iwindow);
          goto _L1
    }

    public void removeAccessibilityRequestPreparer(AccessibilityRequestPreparer accessibilityrequestpreparer)
    {
        if(mRequestPreparerLists == null)
            return;
        int i = accessibilityrequestpreparer.getView().getAccessibilityViewId();
        List list = (List)mRequestPreparerLists.get(i);
        if(list != null)
        {
            list.remove(accessibilityrequestpreparer);
            if(list.isEmpty())
                mRequestPreparerLists.remove(i);
        }
    }

    public void removeAccessibilityServicesStateChangeListener(AccessibilityServicesStateChangeListener accessibilityservicesstatechangelistener)
    {
        mServicesStateChangeListeners.remove(accessibilityservicesstatechangelistener);
    }

    public boolean removeAccessibilityStateChangeListener(AccessibilityStateChangeListener accessibilitystatechangelistener)
    {
        boolean flag = false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        i = mAccessibilityStateChangeListeners.indexOfKey(accessibilitystatechangelistener);
        mAccessibilityStateChangeListeners.remove(accessibilitystatechangelistener);
        if(i >= 0)
            flag = true;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        accessibilitystatechangelistener;
        throw accessibilitystatechangelistener;
    }

    public void removeHighTextContrastStateChangeListener(HighTextContrastChangeListener hightextcontrastchangelistener)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mHighTextContrastStateChangeListeners.remove(hightextcontrastchangelistener);
        obj;
        JVM INSTR monitorexit ;
        return;
        hightextcontrastchangelistener;
        throw hightextcontrastchangelistener;
    }

    public boolean removeTouchExplorationStateChangeListener(TouchExplorationStateChangeListener touchexplorationstatechangelistener)
    {
        boolean flag = false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        i = mTouchExplorationStateChangeListeners.indexOfKey(touchexplorationstatechangelistener);
        mTouchExplorationStateChangeListeners.remove(touchexplorationstatechangelistener);
        if(i >= 0)
            flag = true;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        touchexplorationstatechangelistener;
        throw touchexplorationstatechangelistener;
    }

    public void sendAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        if(mIsEnabled)
            break MISSING_BLOCK_LABEL_65;
        if(Looper.myLooper() == Looper.getMainLooper())
        {
            accessibilityevent = JVM INSTR new #511 <Class IllegalStateException>;
            accessibilityevent.IllegalStateException("Accessibility off. Did you forget to check that?");
            throw accessibilityevent;
        }
        break MISSING_BLOCK_LABEL_53;
        accessibilityevent;
        obj;
        JVM INSTR monitorexit ;
        throw accessibilityevent;
        Log.e("AccessibilityManager", "AccessibilityEvent sent with accessibility disabled");
        obj;
        JVM INSTR monitorexit ;
        return;
        int i;
        int j;
        i = accessibilityevent.getEventType();
        j = mRelevantEventTypes;
        if((i & j) != 0)
            break MISSING_BLOCK_LABEL_88;
        obj;
        JVM INSTR monitorexit ;
        return;
        boolean flag = mIsFinalEnabled;
        if(flag)
            break MISSING_BLOCK_LABEL_102;
        obj;
        JVM INSTR monitorexit ;
        return;
        j = mUserId;
        obj;
        JVM INSTR monitorexit ;
        accessibilityevent.setEventTime(SystemClock.uptimeMillis());
        long l = Binder.clearCallingIdentity();
        iaccessibilitymanager.sendAccessibilityEvent(accessibilityevent, j);
        Binder.restoreCallingIdentity(l);
        accessibilityevent.recycle();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        StringBuilder stringbuilder = JVM INSTR new #328 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e("AccessibilityManager", stringbuilder.append("Error during sending ").append(accessibilityevent).append(" ").toString(), remoteexception);
        accessibilityevent.recycle();
        if(true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        accessibilityevent.recycle();
        throw exception;
    }

    public boolean sendFingerprintGesture(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_20;
        obj;
        JVM INSTR monitorexit ;
        return false;
        obj;
        JVM INSTR monitorexit ;
        Exception exception;
        boolean flag;
        try
        {
            flag = iaccessibilitymanager.sendFingerprintGesture(i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return flag;
        exception;
        throw exception;
    }

    public void setPictureInPictureActionReplacingConnection(IAccessibilityInteractionConnection iaccessibilityinteractionconnection)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        IAccessibilityManager iaccessibilitymanager = getServiceLocked();
        if(iaccessibilitymanager != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        obj;
        JVM INSTR monitorexit ;
        iaccessibilitymanager.setPictureInPictureActionReplacingConnection(iaccessibilityinteractionconnection);
_L1:
        return;
        iaccessibilityinteractionconnection;
        throw iaccessibilityinteractionconnection;
        iaccessibilityinteractionconnection;
        Log.e("AccessibilityManager", "Error setting picture in picture action replacement", iaccessibilityinteractionconnection);
          goto _L1
    }

    public static final String ACTION_CHOOSE_ACCESSIBILITY_BUTTON = "com.android.internal.intent.action.CHOOSE_ACCESSIBILITY_BUTTON";
    public static final int AUTOCLICK_DELAY_DEFAULT = 600;
    public static final int DALTONIZER_CORRECT_DEUTERANOMALY = 12;
    public static final int DALTONIZER_DISABLED = -1;
    public static final int DALTONIZER_SIMULATE_MONOCHROMACY = 0;
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "AccessibilityManager";
    public static final int STATE_FLAG_ACCESSIBILITY_ENABLED = 1;
    public static final int STATE_FLAG_BOUND_SERVICES_CHANGED = 8;
    public static final int STATE_FLAG_HIGH_TEXT_CONTRAST_ENABLED = 4;
    public static final int STATE_FLAG_TOUCH_EXPLORATION_ENABLED = 2;
    public static final int STATE_FLAG_UIAUTOMATION_ENABLED = 16;
    private static AccessibilityManager sInstance;
    static final Object sInstanceSync = new Object();
    private final ArrayMap mAccessibilityStateChangeListeners;
    final android.os.Handler.Callback mCallback;
    private final IAccessibilityManagerClient.Stub mClient;
    private final List mCurrentBoundServices;
    final Handler mHandler;
    private final ArrayMap mHighTextContrastStateChangeListeners;
    boolean mIsEnabled;
    boolean mIsFinalEnabled;
    boolean mIsHighTextContrastEnabled;
    boolean mIsInterestedPackage;
    boolean mIsTouchExplorationEnabled;
    boolean mIsUiAutomationEnabled;
    private final Object mLock;
    boolean mOptimizeEnabled;
    final String mPackageName;
    int mRelevantEventTypes;
    private SparseArray mRequestPreparerLists;
    private IAccessibilityManager mService;
    private final ArrayMap mServicesStateChangeListeners;
    private final ArrayMap mTouchExplorationStateChangeListeners;
    final int mUserId;

}
