// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.accessibilityservice.IAccessibilityServiceConnection;
import android.os.*;
import android.util.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package android.view.accessibility:
//            AccessibilityCache, AccessibilityNodeInfo, AccessibilityWindowInfo, AccessibilityEvent

public final class AccessibilityInteractionClient extends IAccessibilityInteractionConnectionCallback.Stub
{

    private AccessibilityInteractionClient()
    {
        mInteractionId = -1;
    }

    private void checkFindAccessibilityNodeInfoResultIntegrity(List list)
    {
        if(list.size() == 0)
            return;
        AccessibilityNodeInfo accessibilitynodeinfo = (AccessibilityNodeInfo)list.get(0);
        int i = list.size();
        int j = 1;
label0:
        do
        {
            if(j < i)
            {
                int k = j;
                do
                {
label1:
                    {
                        AccessibilityNodeInfo accessibilitynodeinfo2 = accessibilitynodeinfo;
                        if(k < i)
                        {
                            accessibilitynodeinfo2 = (AccessibilityNodeInfo)list.get(k);
                            if(accessibilitynodeinfo.getParentNodeId() != accessibilitynodeinfo2.getSourceNodeId())
                                break label1;
                        }
                        j++;
                        accessibilitynodeinfo = accessibilitynodeinfo2;
                        continue label0;
                    }
                    k++;
                } while(true);
            }
            if(accessibilitynodeinfo == null)
                Log.e("AccessibilityInteractionClient", "No root.");
            HashSet hashset = new HashSet();
            LinkedList linkedlist = new LinkedList();
            linkedlist.add(accessibilitynodeinfo);
            while(!linkedlist.isEmpty()) 
            {
                AccessibilityNodeInfo accessibilitynodeinfo1 = (AccessibilityNodeInfo)linkedlist.poll();
                if(!hashset.add(accessibilitynodeinfo1))
                {
                    Log.e("AccessibilityInteractionClient", "Duplicate node.");
                    return;
                }
                int i1 = accessibilitynodeinfo1.getChildCount();
                j = 0;
                while(j < i1) 
                {
                    long l1 = accessibilitynodeinfo1.getChildId(j);
                    for(int l = 0; l < i; l++)
                    {
                        AccessibilityNodeInfo accessibilitynodeinfo3 = (AccessibilityNodeInfo)list.get(l);
                        if(accessibilitynodeinfo3.getSourceNodeId() == l1)
                            linkedlist.add(accessibilitynodeinfo3);
                    }

                    j++;
                }
            }
            j = list.size() - hashset.size();
            if(j > 0)
                Log.e("AccessibilityInteractionClient", (new StringBuilder()).append(j).append(" Disconnected nodes.").toString());
            return;
        } while(true);
    }

    private void clearResultLocked()
    {
        mInteractionId = -1;
        mFindAccessibilityNodeInfoResult = null;
        mFindAccessibilityNodeInfosResult = null;
        mPerformAccessibilityActionResult = false;
    }

    private void finalizeAndCacheAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo, int i)
    {
        if(accessibilitynodeinfo != null)
        {
            accessibilitynodeinfo.setConnectionId(i);
            accessibilitynodeinfo.setSealed(true);
            sAccessibilityCache.add(accessibilitynodeinfo);
        }
    }

    private void finalizeAndCacheAccessibilityNodeInfos(List list, int i)
    {
        if(list != null)
        {
            int j = list.size();
            for(int k = 0; k < j; k++)
                finalizeAndCacheAccessibilityNodeInfo((AccessibilityNodeInfo)list.get(k), i);

        }
    }

    private AccessibilityNodeInfo getFindAccessibilityNodeInfoResultAndClear(int i)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        AccessibilityNodeInfo accessibilitynodeinfo;
        if(!waitForResultTimedLocked(i))
            break MISSING_BLOCK_LABEL_28;
        accessibilitynodeinfo = mFindAccessibilityNodeInfoResult;
_L1:
        clearResultLocked();
        obj;
        JVM INSTR monitorexit ;
        return accessibilitynodeinfo;
        accessibilitynodeinfo = null;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private List getFindAccessibilityNodeInfosResultAndClear(int i)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        List list;
        if(!waitForResultTimedLocked(i))
            break MISSING_BLOCK_LABEL_39;
        list = mFindAccessibilityNodeInfosResult;
_L1:
        clearResultLocked();
        if(Build.IS_DEBUGGABLE)
            checkFindAccessibilityNodeInfoResultIntegrity(list);
        obj;
        JVM INSTR monitorexit ;
        return list;
        list = Collections.emptyList();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public static AccessibilityInteractionClient getInstance()
    {
        return getInstanceForThread(Thread.currentThread().getId());
    }

    public static AccessibilityInteractionClient getInstanceForThread(long l)
    {
        Object obj = sStaticLock;
        obj;
        JVM INSTR monitorenter ;
        AccessibilityInteractionClient accessibilityinteractionclient = (AccessibilityInteractionClient)sClients.get(l);
        AccessibilityInteractionClient accessibilityinteractionclient1;
        accessibilityinteractionclient1 = accessibilityinteractionclient;
        if(accessibilityinteractionclient != null)
            break MISSING_BLOCK_LABEL_43;
        accessibilityinteractionclient1 = JVM INSTR new #2   <Class AccessibilityInteractionClient>;
        accessibilityinteractionclient1.AccessibilityInteractionClient();
        sClients.put(l, accessibilityinteractionclient1);
        obj;
        JVM INSTR monitorexit ;
        return accessibilityinteractionclient1;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean getPerformAccessibilityActionResultAndClear(int i)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag;
        if(!waitForResultTimedLocked(i))
            break MISSING_BLOCK_LABEL_28;
        flag = mPerformAccessibilityActionResult;
_L1:
        clearResultLocked();
        obj;
        JVM INSTR monitorexit ;
        return flag;
        flag = false;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    private Message getSameProcessMessageAndClear()
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        Message message;
        message = mSameThreadMessage;
        mSameThreadMessage = null;
        obj;
        JVM INSTR monitorexit ;
        return message;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean waitForResultTimedLocked(int i)
    {
        long l = SystemClock.uptimeMillis();
_L2:
        Message message = getSameProcessMessageAndClear();
        if(message == null)
            break MISSING_BLOCK_LABEL_25;
        message.getTarget().handleMessage(message);
        if(mInteractionId == i)
            return true;
        if(mInteractionId > i)
            return false;
        long l1 = 5000L - (SystemClock.uptimeMillis() - l);
        if(l1 <= 0L)
            return false;
        try
        {
            mInstanceLock.wait(l1);
        }
        catch(InterruptedException interruptedexception) { }
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void addConnection(int i, IAccessibilityServiceConnection iaccessibilityserviceconnection)
    {
        SparseArray sparsearray = sConnectionCache;
        sparsearray;
        JVM INSTR monitorenter ;
        sConnectionCache.put(i, iaccessibilityserviceconnection);
        sparsearray;
        JVM INSTR monitorexit ;
        return;
        iaccessibilityserviceconnection;
        throw iaccessibilityserviceconnection;
    }

    public void clearCache()
    {
        sAccessibilityCache.clear();
    }

    public AccessibilityNodeInfo findAccessibilityNodeInfoByAccessibilityId(int i, int j, long l, boolean flag, int k, Bundle bundle)
    {
        if((k & 2) != 0 && (k & 1) == 0)
            throw new IllegalArgumentException("FLAG_PREFETCH_SIBLINGS requires FLAG_PREFETCH_PREDECESSORS");
        IAccessibilityServiceConnection iaccessibilityserviceconnection = getConnection(i);
        if(iaccessibilityserviceconnection == null)
            break MISSING_BLOCK_LABEL_202;
        if(flag)
            break MISSING_BLOCK_LABEL_60;
        AccessibilityNodeInfo accessibilitynodeinfo = sAccessibilityCache.getNode(j, l);
        if(accessibilitynodeinfo != null)
            return accessibilitynodeinfo;
        int i1;
        i1 = mInteractionIdCounter.getAndIncrement();
        long l1 = Binder.clearCallingIdentity();
        flag = iaccessibilityserviceconnection.findAccessibilityNodeInfoByAccessibilityId(j, l, i1, this, k, Thread.currentThread().getId(), bundle);
        Binder.restoreCallingIdentity(l1);
        if(!flag)
            break MISSING_BLOCK_LABEL_202;
        bundle = getFindAccessibilityNodeInfosResultAndClear(i1);
        finalizeAndCacheAccessibilityNodeInfos(bundle, i);
        if(bundle == null)
            break MISSING_BLOCK_LABEL_202;
        if(!(bundle.isEmpty() ^ true))
            break MISSING_BLOCK_LABEL_202;
        i = 1;
_L2:
        if(i >= bundle.size())
            break; /* Loop/switch isn't completed */
        ((AccessibilityNodeInfo)bundle.get(i)).recycle();
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        bundle = (AccessibilityNodeInfo)bundle.get(0);
        return bundle;
        bundle;
        Log.e("AccessibilityInteractionClient", "Error while calling remote findAccessibilityNodeInfoByAccessibilityId", bundle);
        return null;
    }

    public List findAccessibilityNodeInfosByText(int i, int j, long l, String s)
    {
        IAccessibilityServiceConnection iaccessibilityserviceconnection = getConnection(i);
        if(iaccessibilityserviceconnection == null)
            break MISSING_BLOCK_LABEL_94;
        int k;
        boolean flag;
        k = mInteractionIdCounter.getAndIncrement();
        long l1 = Binder.clearCallingIdentity();
        flag = iaccessibilityserviceconnection.findAccessibilityNodeInfosByText(j, l, s, k, this, Thread.currentThread().getId());
        Binder.restoreCallingIdentity(l1);
        if(!flag)
            break MISSING_BLOCK_LABEL_94;
        s = getFindAccessibilityNodeInfosResultAndClear(k);
        if(s == null)
            break MISSING_BLOCK_LABEL_94;
        finalizeAndCacheAccessibilityNodeInfos(s, i);
        return s;
        s;
        Log.w("AccessibilityInteractionClient", "Error while calling remote findAccessibilityNodeInfosByViewText", s);
        return Collections.emptyList();
    }

    public List findAccessibilityNodeInfosByViewId(int i, int j, long l, String s)
    {
        IAccessibilityServiceConnection iaccessibilityserviceconnection = getConnection(i);
        if(iaccessibilityserviceconnection == null)
            break MISSING_BLOCK_LABEL_94;
        int k;
        boolean flag;
        k = mInteractionIdCounter.getAndIncrement();
        long l1 = Binder.clearCallingIdentity();
        flag = iaccessibilityserviceconnection.findAccessibilityNodeInfosByViewId(j, l, s, k, this, Thread.currentThread().getId());
        Binder.restoreCallingIdentity(l1);
        if(!flag)
            break MISSING_BLOCK_LABEL_94;
        s = getFindAccessibilityNodeInfosResultAndClear(k);
        if(s == null)
            break MISSING_BLOCK_LABEL_94;
        finalizeAndCacheAccessibilityNodeInfos(s, i);
        return s;
        s;
        Log.w("AccessibilityInteractionClient", "Error while calling remote findAccessibilityNodeInfoByViewIdInActiveWindow", s);
        return Collections.emptyList();
    }

    public AccessibilityNodeInfo findFocus(int i, int j, long l, int k)
    {
        Object obj = getConnection(i);
        if(obj == null)
            break MISSING_BLOCK_LABEL_89;
        int i1;
        boolean flag;
        i1 = mInteractionIdCounter.getAndIncrement();
        long l1 = Binder.clearCallingIdentity();
        flag = ((IAccessibilityServiceConnection) (obj)).findFocus(j, l, k, i1, this, Thread.currentThread().getId());
        Binder.restoreCallingIdentity(l1);
        if(!flag)
            break MISSING_BLOCK_LABEL_89;
        obj = getFindAccessibilityNodeInfoResultAndClear(i1);
        finalizeAndCacheAccessibilityNodeInfo(((AccessibilityNodeInfo) (obj)), i);
        return ((AccessibilityNodeInfo) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.w("AccessibilityInteractionClient", "Error while calling remote findFocus", remoteexception);
        return null;
    }

    public AccessibilityNodeInfo focusSearch(int i, int j, long l, int k)
    {
        Object obj = getConnection(i);
        if(obj == null)
            break MISSING_BLOCK_LABEL_89;
        int i1;
        boolean flag;
        i1 = mInteractionIdCounter.getAndIncrement();
        long l1 = Binder.clearCallingIdentity();
        flag = ((IAccessibilityServiceConnection) (obj)).focusSearch(j, l, k, i1, this, Thread.currentThread().getId());
        Binder.restoreCallingIdentity(l1);
        if(!flag)
            break MISSING_BLOCK_LABEL_89;
        obj = getFindAccessibilityNodeInfoResultAndClear(i1);
        finalizeAndCacheAccessibilityNodeInfo(((AccessibilityNodeInfo) (obj)), i);
        return ((AccessibilityNodeInfo) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.w("AccessibilityInteractionClient", "Error while calling remote accessibilityFocusSearch", remoteexception);
        return null;
    }

    public IAccessibilityServiceConnection getConnection(int i)
    {
        SparseArray sparsearray = sConnectionCache;
        sparsearray;
        JVM INSTR monitorenter ;
        IAccessibilityServiceConnection iaccessibilityserviceconnection = (IAccessibilityServiceConnection)sConnectionCache.get(i);
        sparsearray;
        JVM INSTR monitorexit ;
        return iaccessibilityserviceconnection;
        Exception exception;
        exception;
        throw exception;
    }

    public AccessibilityNodeInfo getRootInActiveWindow(int i)
    {
        return findAccessibilityNodeInfoByAccessibilityId(i, 0x7fffffff, AccessibilityNodeInfo.ROOT_NODE_ID, false, 4, null);
    }

    public AccessibilityWindowInfo getWindow(int i, int j)
    {
        Object obj = getConnection(i);
        if(obj == null)
            break MISSING_BLOCK_LABEL_69;
        AccessibilityWindowInfo accessibilitywindowinfo = sAccessibilityCache.getWindow(j);
        if(accessibilitywindowinfo != null)
            return accessibilitywindowinfo;
        long l = Binder.clearCallingIdentity();
        obj = ((IAccessibilityServiceConnection) (obj)).getWindow(j);
        Binder.restoreCallingIdentity(l);
        if(obj == null)
            break MISSING_BLOCK_LABEL_69;
        sAccessibilityCache.addWindow(((AccessibilityWindowInfo) (obj)));
        return ((AccessibilityWindowInfo) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("AccessibilityInteractionClient", "Error while calling remote getWindow", remoteexception);
        return null;
    }

    public List getWindows(int i)
    {
        Object obj = getConnection(i);
        if(obj == null)
            break MISSING_BLOCK_LABEL_64;
        List list = sAccessibilityCache.getWindows();
        if(list != null)
            return list;
        long l = Binder.clearCallingIdentity();
        obj = ((IAccessibilityServiceConnection) (obj)).getWindows();
        Binder.restoreCallingIdentity(l);
        if(obj == null)
            break MISSING_BLOCK_LABEL_64;
        sAccessibilityCache.setWindows(((List) (obj)));
        return ((List) (obj));
        RemoteException remoteexception;
        remoteexception;
        Log.e("AccessibilityInteractionClient", "Error while calling remote getWindows", remoteexception);
        return Collections.emptyList();
    }

    public void onAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        sAccessibilityCache.onAccessibilityEvent(accessibilityevent);
    }

    public boolean performAccessibilityAction(int i, int j, long l, int k, Bundle bundle)
    {
        IAccessibilityServiceConnection iaccessibilityserviceconnection = getConnection(i);
        if(iaccessibilityserviceconnection == null)
            break MISSING_BLOCK_LABEL_81;
        boolean flag;
        i = mInteractionIdCounter.getAndIncrement();
        long l1 = Binder.clearCallingIdentity();
        flag = iaccessibilityserviceconnection.performAccessibilityAction(j, l, k, bundle, i, this, Thread.currentThread().getId());
        Binder.restoreCallingIdentity(l1);
        if(!flag)
            break MISSING_BLOCK_LABEL_81;
        flag = getPerformAccessibilityActionResultAndClear(i);
        return flag;
        bundle;
        Log.w("AccessibilityInteractionClient", "Error while calling remote performAccessibilityAction", bundle);
        return false;
    }

    public void removeConnection(int i)
    {
        SparseArray sparsearray = sConnectionCache;
        sparsearray;
        JVM INSTR monitorenter ;
        sConnectionCache.remove(i);
        sparsearray;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setFindAccessibilityNodeInfoResult(AccessibilityNodeInfo accessibilitynodeinfo, int i)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        if(i > mInteractionId)
        {
            mFindAccessibilityNodeInfoResult = accessibilitynodeinfo;
            mInteractionId = i;
        }
        mInstanceLock.notifyAll();
        obj;
        JVM INSTR monitorexit ;
        return;
        accessibilitynodeinfo;
        throw accessibilitynodeinfo;
    }

    public void setFindAccessibilityNodeInfosResult(List list, int i)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        if(i <= mInteractionId) goto _L2; else goto _L1
_L1:
        if(list == null)
            break MISSING_BLOCK_LABEL_87;
        boolean flag;
        if(Binder.getCallingPid() != Process.myPid())
            flag = true;
        else
            flag = false;
        if(flag) goto _L4; else goto _L3
_L3:
        ArrayList arraylist = JVM INSTR new #411 <Class ArrayList>;
        arraylist.ArrayList(list);
        mFindAccessibilityNodeInfosResult = arraylist;
_L5:
        mInteractionId = i;
_L2:
        mInstanceLock.notifyAll();
        obj;
        JVM INSTR monitorexit ;
        return;
_L4:
        mFindAccessibilityNodeInfosResult = list;
          goto _L5
        list;
        throw list;
        mFindAccessibilityNodeInfosResult = Collections.emptyList();
          goto _L5
    }

    public void setPerformAccessibilityActionResult(boolean flag, int i)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        if(i > mInteractionId)
        {
            mPerformAccessibilityActionResult = flag;
            mInteractionId = i;
        }
        mInstanceLock.notifyAll();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setSameThreadMessage(Message message)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        mSameThreadMessage = message;
        mInstanceLock.notifyAll();
        obj;
        JVM INSTR monitorexit ;
        return;
        message;
        throw message;
    }

    private static final boolean CHECK_INTEGRITY = true;
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "AccessibilityInteractionClient";
    public static final int NO_ID = -1;
    private static final long TIMEOUT_INTERACTION_MILLIS = 5000L;
    private static final AccessibilityCache sAccessibilityCache = new AccessibilityCache(new AccessibilityCache.AccessibilityNodeRefresher());
    private static final LongSparseArray sClients = new LongSparseArray();
    private static final SparseArray sConnectionCache = new SparseArray();
    private static final Object sStaticLock = new Object();
    private AccessibilityNodeInfo mFindAccessibilityNodeInfoResult;
    private List mFindAccessibilityNodeInfosResult;
    private final Object mInstanceLock = new Object();
    private volatile int mInteractionId;
    private final AtomicInteger mInteractionIdCounter = new AtomicInteger();
    private boolean mPerformAccessibilityActionResult;
    private Message mSameThreadMessage;

}
