// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import java.util.ArrayList;
import java.util.List;

public class CallbackRegistry
    implements Cloneable
{
    public static abstract class NotifierCallback
    {

        public abstract void onNotifyCallback(Object obj, Object obj1, int i, Object obj2);

        public NotifierCallback()
        {
        }
    }


    public CallbackRegistry(NotifierCallback notifiercallback)
    {
        mCallbacks = new ArrayList();
        mFirst64Removed = 0L;
        mNotifier = notifiercallback;
    }

    private boolean isRemovedLocked(int i)
    {
        boolean flag = true;
        boolean flag1 = true;
        if(i < 64)
        {
            if((mFirst64Removed & 1L << i) == 0L)
                flag1 = false;
            return flag1;
        }
        if(mRemainderRemoved == null)
            return false;
        int j = i / 64 - 1;
        if(j >= mRemainderRemoved.length)
            return false;
        if((mRemainderRemoved[j] & 1L << i % 64) != 0L)
            flag1 = flag;
        else
            flag1 = false;
        return flag1;
    }

    private void notifyCallbacksLocked(Object obj, int i, Object obj1, int j, int k, long l)
    {
        long l1 = 1L;
        for(; j < k; j++)
        {
            if((l & l1) == 0L)
                mNotifier.onNotifyCallback(mCallbacks.get(j), obj, i, obj1);
            l1 <<= 1;
        }

    }

    private void notifyFirst64Locked(Object obj, int i, Object obj1)
    {
        notifyCallbacksLocked(obj, i, obj1, 0, Math.min(64, mCallbacks.size()), mFirst64Removed);
    }

    private void notifyRecurseLocked(Object obj, int i, Object obj1)
    {
        int j = mCallbacks.size();
        int k;
        if(mRemainderRemoved == null)
            k = -1;
        else
            k = mRemainderRemoved.length - 1;
        notifyRemainderLocked(obj, i, obj1, k);
        notifyCallbacksLocked(obj, i, obj1, (k + 2) * 64, j, 0L);
    }

    private void notifyRemainderLocked(Object obj, int i, Object obj1, int j)
    {
        if(j < 0)
        {
            notifyFirst64Locked(obj, i, obj1);
        } else
        {
            long l = mRemainderRemoved[j];
            int k = (j + 1) * 64;
            int i1 = Math.min(mCallbacks.size(), k + 64);
            notifyRemainderLocked(obj, i, obj1, j - 1);
            notifyCallbacksLocked(obj, i, obj1, k, i1, l);
        }
    }

    private void removeRemovedCallbacks(int i, long l)
    {
        long l1 = 0x8000000000000000L;
        for(int j = (i + 64) - 1; j >= i; j--)
        {
            if((l & l1) != 0L)
                mCallbacks.remove(j);
            l1 >>>= 1;
        }

    }

    private void setRemovalBitLocked(int i)
    {
        if(i >= 64) goto _L2; else goto _L1
_L1:
        mFirst64Removed = mFirst64Removed | 1L << i;
_L4:
        return;
_L2:
        int j;
        j = i / 64 - 1;
        if(mRemainderRemoved != null)
            break; /* Loop/switch isn't completed */
        mRemainderRemoved = new long[mCallbacks.size() / 64];
_L6:
        long al[] = mRemainderRemoved;
        al[j] = al[j] | 1L << i % 64;
        if(true) goto _L4; else goto _L3
_L3:
        if(mRemainderRemoved.length >= j) goto _L6; else goto _L5
_L5:
        long al1[] = new long[mCallbacks.size() / 64];
        System.arraycopy(mRemainderRemoved, 0, al1, 0, mRemainderRemoved.length);
        mRemainderRemoved = al1;
          goto _L6
    }

    public void add(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        int i = mCallbacks.lastIndexOf(obj);
        if(i < 0)
            break MISSING_BLOCK_LABEL_25;
        if(!isRemovedLocked(i))
            break MISSING_BLOCK_LABEL_36;
        mCallbacks.add(obj);
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        if(mNotificationLevel != 0) goto _L2; else goto _L1
_L1:
        mCallbacks.clear();
_L6:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        int i;
        if(mCallbacks.isEmpty())
            continue; /* Loop/switch isn't completed */
        i = mCallbacks.size() - 1;
_L4:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        setRemovalBitLocked(i);
        i--;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public CallbackRegistry clone()
    {
        this;
        JVM INSTR monitorenter ;
        CallbackRegistry callbackregistry = null;
        CallbackRegistry callbackregistry1 = (CallbackRegistry)super.clone();
        callbackregistry = callbackregistry1;
        callbackregistry1.mFirst64Removed = 0L;
        callbackregistry = callbackregistry1;
        callbackregistry1.mRemainderRemoved = null;
        callbackregistry = callbackregistry1;
        callbackregistry1.mNotificationLevel = 0;
        callbackregistry = callbackregistry1;
        ArrayList arraylist = JVM INSTR new #33  <Class ArrayList>;
        callbackregistry = callbackregistry1;
        arraylist.ArrayList();
        callbackregistry = callbackregistry1;
        callbackregistry1.mCallbacks = arraylist;
        callbackregistry = callbackregistry1;
        int i = mCallbacks.size();
        int j = 0;
_L2:
        callbackregistry = callbackregistry1;
        if(j >= i)
            break; /* Loop/switch isn't completed */
        callbackregistry = callbackregistry1;
        if(isRemovedLocked(j))
            break MISSING_BLOCK_LABEL_111;
        callbackregistry = callbackregistry1;
        callbackregistry1.mCallbacks.add(mCallbacks.get(j));
        j++;
        if(true) goto _L2; else goto _L1
        CloneNotSupportedException clonenotsupportedexception;
        clonenotsupportedexception;
        clonenotsupportedexception.printStackTrace();
_L1:
        this;
        JVM INSTR monitorexit ;
        return callbackregistry;
        Exception exception;
        exception;
        throw exception;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public ArrayList copyListeners()
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        int i;
        arraylist = JVM INSTR new #33  <Class ArrayList>;
        arraylist.ArrayList(mCallbacks.size());
        i = mCallbacks.size();
        int j = 0;
_L3:
        if(j >= i) goto _L2; else goto _L1
_L1:
        if(!isRemovedLocked(j))
            arraylist.add(mCallbacks.get(j));
        j++;
          goto _L3
_L2:
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isEmpty()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mCallbacks.isEmpty();
        if(!flag)
            break MISSING_BLOCK_LABEL_20;
        this;
        JVM INSTR monitorexit ;
        return true;
        int i = mNotificationLevel;
        if(i != 0)
            break MISSING_BLOCK_LABEL_33;
        this;
        JVM INSTR monitorexit ;
        return false;
        int j = mCallbacks.size();
        i = 0;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        flag = isRemovedLocked(i);
        if(flag)
            break MISSING_BLOCK_LABEL_64;
        this;
        JVM INSTR monitorexit ;
        return false;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return true;
        Exception exception;
        exception;
        throw exception;
    }

    public void notifyCallbacks(Object obj, int i, Object obj1)
    {
        this;
        JVM INSTR monitorenter ;
        mNotificationLevel = mNotificationLevel + 1;
        notifyRecurseLocked(obj, i, obj1);
        mNotificationLevel = mNotificationLevel - 1;
        if(mNotificationLevel != 0)
            break MISSING_BLOCK_LABEL_118;
        if(mRemainderRemoved == null)
            break MISSING_BLOCK_LABEL_95;
        i = mRemainderRemoved.length - 1;
_L3:
        if(i < 0) goto _L2; else goto _L1
_L1:
        long l = mRemainderRemoved[i];
        if(l == 0L)
            continue; /* Loop/switch isn't completed */
        removeRemovedCallbacks((i + 1) * 64, l);
        mRemainderRemoved[i] = 0L;
        i--;
          goto _L3
_L2:
        if(mFirst64Removed != 0L)
        {
            removeRemovedCallbacks(0, mFirst64Removed);
            mFirst64Removed = 0L;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        obj;
        throw obj;
    }

    public void remove(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        if(mNotificationLevel != 0) goto _L2; else goto _L1
_L1:
        mCallbacks.remove(obj);
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        int i = mCallbacks.lastIndexOf(obj);
        if(i < 0) goto _L4; else goto _L3
_L3:
        setRemovalBitLocked(i);
          goto _L4
        obj;
        throw obj;
    }

    private static final String TAG = "CallbackRegistry";
    private List mCallbacks;
    private long mFirst64Removed;
    private int mNotificationLevel;
    private final NotifierCallback mNotifier;
    private long mRemainderRemoved[];
}
