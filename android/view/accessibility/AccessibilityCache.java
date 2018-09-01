// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.accessibility;

import android.os.Build;
import android.util.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.view.accessibility:
//            AccessibilityNodeInfo, AccessibilityWindowInfo, AccessibilityEvent

public final class AccessibilityCache
{
    public static class AccessibilityNodeRefresher
    {

        public boolean refreshNode(AccessibilityNodeInfo accessibilitynodeinfo, boolean flag)
        {
            return accessibilitynodeinfo.refresh(null, flag);
        }

        public AccessibilityNodeRefresher()
        {
        }
    }


    public AccessibilityCache(AccessibilityNodeRefresher accessibilitynoderefresher)
    {
        mAccessibilityFocus = 0x7fffffffL;
        mInputFocus = 0x7fffffffL;
        mAccessibilityNodeRefresher = accessibilitynoderefresher;
    }

    private void clearNodesForWindowLocked(int i)
    {
        LongSparseArray longsparsearray = (LongSparseArray)mNodeCache.get(i);
        if(longsparsearray == null)
            return;
        for(int j = longsparsearray.size() - 1; j >= 0; j--)
        {
            AccessibilityNodeInfo accessibilitynodeinfo = (AccessibilityNodeInfo)longsparsearray.valueAt(j);
            longsparsearray.removeAt(j);
            accessibilitynodeinfo.recycle();
        }

        mNodeCache.remove(i);
    }

    private void clearSubTreeLocked(int i, long l)
    {
        LongSparseArray longsparsearray = (LongSparseArray)mNodeCache.get(i);
        if(longsparsearray != null)
            clearSubTreeRecursiveLocked(longsparsearray, l);
    }

    private void clearSubTreeRecursiveLocked(LongSparseArray longsparsearray, long l)
    {
        AccessibilityNodeInfo accessibilitynodeinfo = (AccessibilityNodeInfo)longsparsearray.get(l);
        if(accessibilitynodeinfo == null)
            return;
        longsparsearray.remove(l);
        int i = accessibilitynodeinfo.getChildCount();
        for(int j = 0; j < i; j++)
            clearSubTreeRecursiveLocked(longsparsearray, accessibilitynodeinfo.getChildId(j));

        accessibilitynodeinfo.recycle();
    }

    private void clearWindowCache()
    {
        for(int i = mWindowCache.size() - 1; i >= 0; i--)
        {
            ((AccessibilityWindowInfo)mWindowCache.valueAt(i)).recycle();
            mWindowCache.removeAt(i);
        }

        mIsAllWindowsCached = false;
    }

    private void refreshCachedNodeLocked(int i, long l)
    {
        Object obj = (LongSparseArray)mNodeCache.get(i);
        if(obj == null)
            return;
        obj = (AccessibilityNodeInfo)((LongSparseArray) (obj)).get(l);
        if(obj == null)
            return;
        if(mAccessibilityNodeRefresher.refreshNode(((AccessibilityNodeInfo) (obj)), true))
        {
            return;
        } else
        {
            clearSubTreeLocked(i, l);
            return;
        }
    }

    public void add(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        Object obj1;
        i = accessibilitynodeinfo.getWindowId();
        obj1 = (LongSparseArray)mNodeCache.get(i);
        LongSparseArray longsparsearray;
        longsparsearray = ((LongSparseArray) (obj1));
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_54;
        longsparsearray = JVM INSTR new #73  <Class LongSparseArray>;
        longsparsearray.LongSparseArray();
        mNodeCache.put(i, longsparsearray);
        long l;
        AccessibilityNodeInfo accessibilitynodeinfo1;
        l = accessibilitynodeinfo.getSourceNodeId();
        accessibilitynodeinfo1 = (AccessibilityNodeInfo)longsparsearray.get(l);
        if(accessibilitynodeinfo1 == null)
            break MISSING_BLOCK_LABEL_179;
        int j;
        obj1 = accessibilitynodeinfo.getChildNodeIds();
        j = accessibilitynodeinfo1.getChildCount();
        int k = 0;
_L3:
        if(k >= j) goto _L2; else goto _L1
_L1:
        if(longsparsearray.get(l) != null)
            break MISSING_BLOCK_LABEL_118;
        clearNodesForWindowLocked(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        long l1 = accessibilitynodeinfo1.getChildId(k);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_142;
        if(((LongArray) (obj1)).indexOf(l1) >= 0)
            continue; /* Loop/switch isn't completed */
        clearSubTreeLocked(i, l1);
        k++;
          goto _L3
_L2:
        long l2 = accessibilitynodeinfo1.getParentNodeId();
        if(accessibilitynodeinfo.getParentNodeId() != l2)
            clearSubTreeLocked(i, l2);
        accessibilitynodeinfo = AccessibilityNodeInfo.obtain(accessibilitynodeinfo);
        longsparsearray.put(l, accessibilitynodeinfo);
        if(accessibilitynodeinfo.isAccessibilityFocused())
            mAccessibilityFocus = l;
        if(accessibilitynodeinfo.isFocused())
            mInputFocus = l;
        obj;
        JVM INSTR monitorexit ;
        return;
        accessibilitynodeinfo;
        throw accessibilitynodeinfo;
    }

    public void addWindow(AccessibilityWindowInfo accessibilitywindowinfo)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        AccessibilityWindowInfo accessibilitywindowinfo1;
        i = accessibilitywindowinfo.getId();
        accessibilitywindowinfo1 = (AccessibilityWindowInfo)mWindowCache.get(i);
        if(accessibilitywindowinfo1 == null)
            break MISSING_BLOCK_LABEL_35;
        accessibilitywindowinfo1.recycle();
        mWindowCache.put(i, AccessibilityWindowInfo.obtain(accessibilitywindowinfo));
        obj;
        JVM INSTR monitorexit ;
        return;
        accessibilitywindowinfo;
        throw accessibilitywindowinfo;
    }

    public void checkIntegrity()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        if(mWindowCache.size() > 0)
            break MISSING_BLOCK_LABEL_32;
        i = mNodeCache.size();
        if(i != 0)
            break MISSING_BLOCK_LABEL_32;
        obj;
        JVM INSTR monitorexit ;
        return;
        Object obj1;
        Object obj2;
        obj1 = null;
        obj2 = null;
        int j = mWindowCache.size();
        i = 0;
_L29:
        if(i >= j)
            break MISSING_BLOCK_LABEL_195;
        Object obj3 = (AccessibilityWindowInfo)mWindowCache.valueAt(i);
        Object obj4 = obj2;
        if(!((AccessibilityWindowInfo) (obj3)).isActive()) goto _L2; else goto _L1
_L1:
        if(obj2 == null) goto _L4; else goto _L3
_L3:
        obj4 = JVM INSTR new #185 <Class StringBuilder>;
        ((StringBuilder) (obj4)).StringBuilder();
        Log.e("AccessibilityCache", ((StringBuilder) (obj4)).append("Duplicate active window:").append(obj3).toString());
        obj4 = obj2;
_L2:
        Object obj5 = obj1;
        if(!((AccessibilityWindowInfo) (obj3)).isFocused())
            break MISSING_BLOCK_LABEL_168;
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_188;
        obj2 = JVM INSTR new #185 <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        Log.e("AccessibilityCache", ((StringBuilder) (obj2)).append("Duplicate focused window:").append(obj3).toString());
        obj5 = obj1;
_L5:
        i++;
        obj2 = obj4;
        obj1 = obj5;
        continue; /* Loop/switch isn't completed */
_L4:
        obj4 = obj3;
          goto _L2
        obj5 = obj3;
          goto _L5
        obj3 = null;
        obj1 = null;
        int k = mNodeCache.size();
        i = 0;
_L7:
        if(i >= k)
            break MISSING_BLOCK_LABEL_736;
        LongSparseArray longsparsearray;
        longsparsearray = (LongSparseArray)mNodeCache.valueAt(i);
        if(longsparsearray.size() > 0)
            break; /* Loop/switch isn't completed */
        obj4 = obj1;
        obj2 = obj3;
_L9:
        i++;
        obj3 = obj2;
        obj1 = obj4;
        if(true) goto _L7; else goto _L6
_L6:
        ArraySet arrayset;
        int l;
        int i1;
        arrayset = JVM INSTR new #210 <Class ArraySet>;
        arrayset.ArraySet();
        l = mNodeCache.keyAt(i);
        i1 = longsparsearray.size();
        j = 0;
_L12:
        obj2 = obj3;
        obj4 = obj1;
        if(j >= i1) goto _L9; else goto _L8
_L8:
        obj2 = (AccessibilityNodeInfo)longsparsearray.valueAt(j);
        if(arrayset.add(obj2)) goto _L11; else goto _L10
_L10:
        obj4 = JVM INSTR new #185 <Class StringBuilder>;
        ((StringBuilder) (obj4)).StringBuilder();
        Log.e("AccessibilityCache", ((StringBuilder) (obj4)).append("Duplicate node: ").append(obj2).append(" in window:").append(l).toString());
        obj5 = obj3;
_L25:
        j++;
        obj3 = obj5;
          goto _L12
_L11:
        obj4 = obj3;
        if(!((AccessibilityNodeInfo) (obj2)).isAccessibilityFocused()) goto _L14; else goto _L13
_L13:
        if(obj3 == null) goto _L16; else goto _L15
_L15:
        obj4 = JVM INSTR new #185 <Class StringBuilder>;
        ((StringBuilder) (obj4)).StringBuilder();
        Log.e("AccessibilityCache", ((StringBuilder) (obj4)).append("Duplicate accessibility focus:").append(obj2).append(" in window:").append(l).toString());
        obj4 = obj3;
_L14:
        obj3 = obj1;
        if(!((AccessibilityNodeInfo) (obj2)).isFocused()) goto _L18; else goto _L17
_L17:
        if(obj1 == null) goto _L20; else goto _L19
_L19:
        obj3 = JVM INSTR new #185 <Class StringBuilder>;
        ((StringBuilder) (obj3)).StringBuilder();
        Log.e("AccessibilityCache", ((StringBuilder) (obj3)).append("Duplicate input focus: ").append(obj2).append(" in window:").append(l).toString());
        obj3 = obj1;
_L18:
        AccessibilityNodeInfo accessibilitynodeinfo = (AccessibilityNodeInfo)longsparsearray.get(((AccessibilityNodeInfo) (obj2)).getParentNodeId());
        if(accessibilitynodeinfo == null) goto _L22; else goto _L21
_L21:
        boolean flag = false;
        int j1 = accessibilitynodeinfo.getChildCount();
        int k1 = 0;
_L27:
        boolean flag1;
        flag1 = flag;
        if(k1 >= j1)
            break MISSING_BLOCK_LABEL_567;
        if((AccessibilityNodeInfo)longsparsearray.get(accessibilitynodeinfo.getChildId(k1)) != obj2)
            break MISSING_BLOCK_LABEL_729;
        flag1 = true;
        if(flag1) goto _L22; else goto _L23
_L23:
        obj1 = JVM INSTR new #185 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        Log.e("AccessibilityCache", ((StringBuilder) (obj1)).append("Invalid parent-child relation between parent: ").append(accessibilitynodeinfo).append(" and child: ").append(obj2).toString());
_L22:
        int l1 = ((AccessibilityNodeInfo) (obj2)).getChildCount();
        k1 = 0;
_L26:
        obj5 = obj4;
        obj1 = obj3;
        if(k1 >= l1) goto _L25; else goto _L24
_L24:
        obj1 = (AccessibilityNodeInfo)longsparsearray.get(((AccessibilityNodeInfo) (obj2)).getChildId(k1));
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_709;
        if((AccessibilityNodeInfo)longsparsearray.get(((AccessibilityNodeInfo) (obj1)).getParentNodeId()) != obj2)
        {
            StringBuilder stringbuilder = JVM INSTR new #185 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e("AccessibilityCache", stringbuilder.append("Invalid child-parent relation between child: ").append(obj2).append(" and parent: ").append(accessibilitynodeinfo).toString());
        }
        k1++;
          goto _L26
_L16:
        obj4 = obj2;
          goto _L14
_L20:
        obj3 = obj2;
          goto _L18
        k1++;
          goto _L27
        return;
        Exception exception;
        exception;
        throw exception;
        if(true) goto _L29; else goto _L28
_L28:
    }

    public void clear()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        clearWindowCache();
        i = mNodeCache.size();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        clearNodesForWindowLocked(mNodeCache.keyAt(j));
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        mAccessibilityFocus = 0x7fffffffL;
        mInputFocus = 0x7fffffffL;
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public AccessibilityNodeInfo getNode(int i, long l)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = (LongSparseArray)mNodeCache.get(i);
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_32;
        obj;
        JVM INSTR monitorexit ;
        return null;
        AccessibilityNodeInfo accessibilitynodeinfo = (AccessibilityNodeInfo)((LongSparseArray) (obj1)).get(l);
        obj1 = accessibilitynodeinfo;
        if(accessibilitynodeinfo == null)
            break MISSING_BLOCK_LABEL_59;
        obj1 = AccessibilityNodeInfo.obtain(accessibilitynodeinfo);
        obj;
        JVM INSTR monitorexit ;
        return ((AccessibilityNodeInfo) (obj1));
        Exception exception;
        exception;
        throw exception;
    }

    public AccessibilityWindowInfo getWindow(int i)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        AccessibilityWindowInfo accessibilitywindowinfo = (AccessibilityWindowInfo)mWindowCache.get(i);
        if(accessibilitywindowinfo == null)
            break MISSING_BLOCK_LABEL_32;
        accessibilitywindowinfo = AccessibilityWindowInfo.obtain(accessibilitywindowinfo);
        obj;
        JVM INSTR monitorexit ;
        return accessibilitywindowinfo;
        obj;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
    }

    public List getWindows()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mIsAllWindowsCached;
        if(flag)
            break MISSING_BLOCK_LABEL_20;
        obj;
        JVM INSTR monitorexit ;
        return null;
        int i = mWindowCache.size();
        if(i <= 0)
            break MISSING_BLOCK_LABEL_150;
        SparseArray sparsearray;
        sparsearray = mTempWindowArray;
        sparsearray.clear();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        AccessibilityWindowInfo accessibilitywindowinfo = (AccessibilityWindowInfo)mWindowCache.valueAt(j);
        sparsearray.put(accessibilitywindowinfo.getLayer(), accessibilitywindowinfo);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        j = sparsearray.size();
        arraylist = JVM INSTR new #253 <Class ArrayList>;
        arraylist.ArrayList(j);
        j--;
_L4:
        if(j < 0)
            break; /* Loop/switch isn't completed */
        arraylist.add(AccessibilityWindowInfo.obtain((AccessibilityWindowInfo)sparsearray.valueAt(j)));
        sparsearray.removeAt(j);
        j--;
        if(true) goto _L4; else goto _L3
_L3:
        return arraylist;
        obj;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
    }

    public void onAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        int i = accessibilityevent.getEventType();
        i;
        JVM INSTR lookupswitch 11: default 112
    //                   1: 256
    //                   4: 256
    //                   8: 210
    //                   16: 256
    //                   32: 344
    //                   2048: 271
    //                   4096: 329
    //                   8192: 256
    //                   32768: 125
    //                   65536: 176
    //                   4194304: 344;
           goto _L1 _L2 _L2 _L3 _L2 _L4 _L5 _L6 _L2 _L7 _L8 _L4
_L1:
        obj;
        JVM INSTR monitorexit ;
        if(CHECK_INTEGRITY)
            checkIntegrity();
        return;
_L7:
        if(mAccessibilityFocus != 0x7fffffffL)
            refreshCachedNodeLocked(accessibilityevent.getWindowId(), mAccessibilityFocus);
        mAccessibilityFocus = accessibilityevent.getSourceNodeId();
        refreshCachedNodeLocked(accessibilityevent.getWindowId(), mAccessibilityFocus);
          goto _L1
        accessibilityevent;
        throw accessibilityevent;
_L8:
        if(mAccessibilityFocus != accessibilityevent.getSourceNodeId()) goto _L1; else goto _L9
_L9:
        refreshCachedNodeLocked(accessibilityevent.getWindowId(), mAccessibilityFocus);
        mAccessibilityFocus = 0x7fffffffL;
          goto _L1
_L3:
        if(mInputFocus != 0x7fffffffL)
            refreshCachedNodeLocked(accessibilityevent.getWindowId(), mInputFocus);
        mInputFocus = accessibilityevent.getSourceNodeId();
        refreshCachedNodeLocked(accessibilityevent.getWindowId(), mInputFocus);
          goto _L1
_L2:
        refreshCachedNodeLocked(accessibilityevent.getWindowId(), accessibilityevent.getSourceNodeId());
          goto _L1
_L5:
        Object obj1 = mLock;
        obj1;
        JVM INSTR monitorenter ;
        long l;
        i = accessibilityevent.getWindowId();
        l = accessibilityevent.getSourceNodeId();
        if((accessibilityevent.getContentChangeTypes() & 1) == 0) goto _L11; else goto _L10
_L10:
        clearSubTreeLocked(i, l);
_L12:
        obj1;
        JVM INSTR monitorexit ;
          goto _L1
_L11:
        refreshCachedNodeLocked(i, l);
          goto _L12
        accessibilityevent;
        obj1;
        JVM INSTR monitorexit ;
        throw accessibilityevent;
_L6:
        clearSubTreeLocked(accessibilityevent.getWindowId(), accessibilityevent.getSourceNodeId());
          goto _L1
_L4:
        clear();
          goto _L1
    }

    public void setWindows(List list)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        clearWindowCache();
        if(list != null)
            break MISSING_BLOCK_LABEL_18;
        obj;
        JVM INSTR monitorexit ;
        return;
        int i = list.size();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        addWindow((AccessibilityWindowInfo)list.get(j));
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        mIsAllWindowsCached = true;
        obj;
        JVM INSTR monitorexit ;
        return;
        list;
        throw list;
    }

    public static final int CACHE_CRITICAL_EVENTS_MASK = 0x41b83d;
    private static final boolean CHECK_INTEGRITY;
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "AccessibilityCache";
    private long mAccessibilityFocus;
    private final AccessibilityNodeRefresher mAccessibilityNodeRefresher;
    private long mInputFocus;
    private boolean mIsAllWindowsCached;
    private final Object mLock = new Object();
    private final SparseArray mNodeCache = new SparseArray();
    private final SparseArray mTempWindowArray = new SparseArray();
    private final SparseArray mWindowCache = new SparseArray();

    static 
    {
        CHECK_INTEGRITY = Build.IS_ENG;
    }
}
