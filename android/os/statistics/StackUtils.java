// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import android.text.TextUtils;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;

class StackUtils
{

    StackUtils()
    {
    }

    public static String[] getStackTrace(JSONArray jsonarray)
    {
        if(jsonarray == null)
            return new String[0];
        String as[] = new String[jsonarray.length()];
        int i = 0;
        while(i < as.length) 
        {
            try
            {
                as[i] = jsonarray.getString(i);
            }
            catch(JSONException jsonexception)
            {
                as[i] = "";
            }
            i++;
        }
        return as;
    }

    public static String[] getStackTrace(StackTraceElement astacktraceelement[], Class aclass[], String as[])
    {
        int i;
        int j;
        int l;
        ArrayList arraylist;
        int i1;
        int j1;
        i = 0;
        j = 0;
        boolean flag = false;
        l = 0;
        arraylist = new ArrayList(32);
        i1 = ((flag) ? 1 : 0);
        j1 = i;
        if(as == null)
            break MISSING_BLOCK_LABEL_144;
        i1 = ((flag) ? 1 : 0);
        j1 = i;
        if(as.length <= 0)
            break MISSING_BLOCK_LABEL_144;
        i = 0;
_L2:
        Object obj;
        i1 = l;
        j1 = j;
        if(i >= as.length)
            break MISSING_BLOCK_LABEL_144;
        obj = as[i];
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            break; /* Loop/switch isn't completed */
        i1 = l;
_L4:
        i++;
        l = i1;
        if(true) goto _L2; else goto _L1
_L1:
        int k;
        arraylist.add(obj);
        l++;
        k = j + ((String) (obj)).length();
        i1 = l;
        j1 = k;
        if(l >= 32)
            break MISSING_BLOCK_LABEL_144;
        i1 = l;
        j = k;
        if(k < 1024) goto _L4; else goto _L3
_L3:
        j1 = k;
        for(i1 = l; i1 >= 32 || j1 >= 1024 || astacktraceelement == null || astacktraceelement.length == 0 || aclass == null || aclass.length == 0;)
            return (String[])arraylist.toArray(new String[arraylist.size()]);

        if(topClassMethodSimpleNames != null) goto _L6; else goto _L5
_L5:
        android/os/statistics/StackUtils;
        JVM INSTR monitorenter ;
        if(topClassMethodSimpleNames != null) goto _L8; else goto _L7
_L7:
        Object obj1;
        Object aobj[];
        obj1 = JVM INSTR new #242 <Class HashMap>;
        ((HashMap) (obj1)).HashMap();
        aobj = suggestedStackTopMethodFullNames;
        j = 0;
        l = aobj.length;
_L10:
        if(j >= l)
            break; /* Loop/switch isn't completed */
        as = aobj[j];
        Object obj2;
        Class class1;
        i = as.lastIndexOf(".");
        obj2 = as.substring(i + 1);
        class1 = Class.forName(as.substring(0, i));
        obj = (ArrayList)((HashMap) (obj1)).get(class1);
        as = ((String []) (obj));
        if(obj != null)
            break MISSING_BLOCK_LABEL_313;
        as = JVM INSTR new #218 <Class ArrayList>;
        as.ArrayList();
        ((HashMap) (obj1)).put(class1, as);
        as.add(obj2);
_L38:
        j++;
        if(true) goto _L10; else goto _L9
_L9:
        Class aclass1[];
        aclass1 = (Class[])((HashMap) (obj1)).keySet().toArray(new Class[((HashMap) (obj1)).size()]);
        aobj = new ArrayList[aclass1.length];
        j = 0;
        obj2 = ((HashMap) (obj1)).keySet().iterator();
_L11:
        if(!((Iterator) (obj2)).hasNext())
            break MISSING_BLOCK_LABEL_419;
        as = (Class)((Iterator) (obj2)).next();
        aclass1[j] = as;
        aobj[j] = (ArrayList)((HashMap) (obj1)).get(as);
        j++;
          goto _L11
        topClasses = aclass1;
        topClassMethodSimpleNames = ((ArrayList []) (aobj));
_L8:
        android/os/statistics/StackUtils;
        JVM INSTR monitorexit ;
_L6:
        l = 0;
        i = 0;
        j = i;
        if(aclass == null) goto _L13; else goto _L12
_L12:
        if(aclass.length < 1 || aclass[0] != java/lang/Thread) goto _L15; else goto _L14
_L14:
        j = 1;
_L13:
        if(j == 0) goto _L17; else goto _L16
_L16:
        j = astacktraceelement.length - 1;
_L36:
        l = j;
        if(j <= 0) goto _L17; else goto _L18
_L18:
        as = aclass[j];
        if(as == null) goto _L20; else goto _L19
_L19:
        k = -1;
        l = 0;
_L35:
        i = k;
        if(l >= topClasses.length) goto _L22; else goto _L21
_L21:
        if(topClasses[l] != as) goto _L24; else goto _L23
_L23:
        i = l;
_L22:
        if(i < 0) goto _L20; else goto _L25
_L25:
        as = astacktraceelement[j];
        if(!topClassMethodSimpleNames[i].contains(as.getMethodName())) goto _L20; else goto _L26
_L26:
        l = j;
_L17:
        aclass1 = null;
        if(sStackTraceStringBuilderBusy.compareAndSet(false, true))
            as = sStackTraceStringBuilder;
        else
            as = new StringBuilder(256);
        i = 0;
        j = l;
        l = j1;
        j1 = i;
        i = i1;
        if(j >= astacktraceelement.length) goto _L28; else goto _L27
_L27:
        as.setLength(0);
        obj1 = aclass[j];
        if(obj1 != null) goto _L30; else goto _L29
_L29:
        i1 = j1;
_L37:
        j++;
        j1 = i1;
        break MISSING_BLOCK_LABEL_584;
        astacktraceelement;
        throw astacktraceelement;
_L15:
        j = i;
        if(aclass.length < 4) goto _L13; else goto _L31
_L31:
        j = i;
        if(aclass[0] != java/lang/Object) goto _L13; else goto _L32
_L32:
        if(aclass[1] != java/lang/Thread && aclass[2] != java/lang/Thread) goto _L34; else goto _L33
_L33:
        j = 1;
        break; /* Loop/switch isn't completed */
_L34:
        j = i;
        if(aclass[3] != java/lang/Thread) goto _L13; else goto _L33
_L24:
        l++;
          goto _L35
_L20:
        j--;
          goto _L36
_L30:
        StackTraceElement stacktraceelement = astacktraceelement[j];
        if(aclass1 == obj1)
            as.append('-').append('.');
        else
            as.append(stacktraceelement.getClassName()).append('.');
        as.append(stacktraceelement.getMethodName());
        i1 = j1;
        if(j1 == 0)
        {
            i1 = 1;
            int k1;
            if(stacktraceelement.isNativeMethod())
                as.append("(Native)");
            else
            if(stacktraceelement.getFileName() != null)
            {
                as.append('(');
                if(aclass1 == obj1)
                    as.append('-');
                else
                    as.append(stacktraceelement.getFileName());
                as.append(':').append(stacktraceelement.getLineNumber()).append(')');
            } else
            {
                as.append("(None)");
            }
        }
        aclass1 = ((Class []) (obj1));
        arraylist.add(as.toString());
        i++;
        k1 = l + as.length();
        if(i >= 32)
            break; /* Loop/switch isn't completed */
        l = k1;
        if(k1 < 1024) goto _L37; else goto _L28
_L28:
        if(as == sStackTraceStringBuilder)
            sStackTraceStringBuilderBusy.set(false);
        return (String[])arraylist.toArray(new String[arraylist.size()]);
        as;
          goto _L38
    }

    private static final int MAX_STACK_DEPTH = 32;
    private static final int MAX_STACK_LENGH = 1024;
    public static final JSONArray emptyJsonedStack = new JSONArray();
    public static final String emptyStack[] = new String[0];
    private static final StringBuilder sStackTraceStringBuilder = new StringBuilder(256);
    private static final AtomicBoolean sStackTraceStringBuilderBusy = new AtomicBoolean(false);
    private static final String suggestedStackTopMethodFullNames[] = {
        "java.lang.Thread.sleep", "java.lang.Thread.join", "java.util.concurrent.Semaphore.acquire", "java.util.concurrent.Semaphore.acquireUninterruptibly", "java.util.concurrent.Semaphore.tryAcquire", "java.util.concurrent.Semaphore.release", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer.acquire", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer.acquireInterruptibly", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer.tryAcquireNanos", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer.release", 
        "java.util.concurrent.locks.AbstractQueuedLongSynchronizer.acquireShared", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer.acquireSharedInterruptibly", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer.tryAcquireSharedNanos", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer.releaseShared", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer$ConditionObject.signal", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer$ConditionObject.signalAll", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer$ConditionObject.await", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer$ConditionObject.awaitNanos", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer$ConditionObject.awaitUntil", "java.util.concurrent.locks.AbstractQueuedLongSynchronizer$ConditionObject.awaitUninterruptibly", 
        "java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire", "java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireInterruptibly", "java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireNanos", "java.util.concurrent.locks.AbstractQueuedSynchronizer.release", "java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireShared", "java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireSharedInterruptibly", "java.util.concurrent.locks.AbstractQueuedSynchronizer.tryAcquireSharedNanos", "java.util.concurrent.locks.AbstractQueuedSynchronizer.releaseShared", "java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.signal", "java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.signalAll", 
        "java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await", "java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitNanos", "java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitUntil", "java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.awaitUninterruptibly", "java.util.concurrent.locks.ReentrantLock.lock", "java.util.concurrent.locks.ReentrantLock.lockInterruptibly", "java.util.concurrent.locks.ReentrantLock.tryLock", "java.util.concurrent.locks.ReentrantLock.unlock", "java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock.lock", "java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock.lockInterruptibly", 
        "java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock.tryLock", "java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock.unlock", "java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock.lock", "java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock.lockInterruptibly", "java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock.tryLock", "java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock.unlock", "java.util.concurrent.locks.StampedLock$ReadLockView.lock", "java.util.concurrent.locks.StampedLock$ReadLockView.lockInterruptibly", "java.util.concurrent.locks.StampedLock$ReadLockView.tryLock", "java.util.concurrent.locks.StampedLock$ReadLockView.unlock", 
        "java.util.concurrent.locks.StampedLock$WriteLockView.lock", "java.util.concurrent.locks.StampedLock$WriteLockView.lockInterruptibly", "java.util.concurrent.locks.StampedLock$WriteLockView.tryLock", "java.util.concurrent.locks.StampedLock$WriteLockView.unlock", "java.util.concurrent.locks.StampedLock.writeLock", "java.util.concurrent.locks.StampedLock.tryWriteLock", "java.util.concurrent.locks.StampedLock.writeLockInterruptibly", "java.util.concurrent.locks.StampedLock.readLock", "java.util.concurrent.locks.StampedLock.tryReadLock", "java.util.concurrent.locks.StampedLock.readLockInterruptibly", 
        "java.util.concurrent.locks.StampedLock.unlockWrite", "java.util.concurrent.locks.StampedLock.unlockRead", "java.util.concurrent.locks.StampedLock.unlock", "java.util.concurrent.locks.StampedLock.tryConvertToReadLock", "java.util.concurrent.locks.StampedLock.tryConvertToWriteLock", "java.util.concurrent.locks.StampedLock.tryConvertToOptimisticRead", "java.util.concurrent.locks.StampedLock.tryUnlockWrite", "java.util.concurrent.locks.StampedLock.tryUnlockRead", "java.util.concurrent.locks.StampedLock.unstampedUnlockRead", "java.util.concurrent.locks.LockSupport.park", 
        "java.util.concurrent.locks.LockSupport.parkNanos", "java.util.concurrent.locks.LockSupport.parkUntil", "java.util.concurrent.locks.LockSupport.unpark"
    };
    private static volatile ArrayList topClassMethodSimpleNames[];
    private static volatile Class topClasses[];

}
