// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache
{

    public LruCache(int i)
    {
        if(i <= 0)
        {
            throw new IllegalArgumentException("maxSize <= 0");
        } else
        {
            maxSize = i;
            map = new LinkedHashMap(0, 0.75F, true);
            return;
        }
    }

    private int safeSizeOf(Object obj, Object obj1)
    {
        int i = sizeOf(obj, obj1);
        if(i < 0)
            throw new IllegalStateException((new StringBuilder()).append("Negative size: ").append(obj).append("=").append(obj1).toString());
        else
            return i;
    }

    protected Object create(Object obj)
    {
        return null;
    }

    public final int createCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = createCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    protected void entryRemoved(boolean flag, Object obj, Object obj1, Object obj2)
    {
    }

    public final void evictAll()
    {
        trimToSize(-1);
    }

    public final int evictionCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = evictionCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final Object get(Object obj)
    {
        if(obj == null)
            throw new NullPointerException("key == null");
        this;
        JVM INSTR monitorenter ;
        Object obj1 = map.get(obj);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_43;
        hitCount = hitCount + 1;
        this;
        JVM INSTR monitorexit ;
        return obj1;
        missCount = missCount + 1;
        this;
        JVM INSTR monitorexit ;
        obj1 = create(obj);
        if(obj1 == null)
            return null;
        break MISSING_BLOCK_LABEL_72;
        obj;
        throw obj;
        this;
        JVM INSTR monitorenter ;
        Object obj2;
        createCount = createCount + 1;
        obj2 = map.put(obj, obj1);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_124;
        map.put(obj, obj2);
_L1:
        this;
        JVM INSTR monitorexit ;
        if(obj2 != null)
        {
            entryRemoved(false, obj, obj1, obj2);
            return obj2;
        } else
        {
            trimToSize(maxSize);
            return obj1;
        }
        size = size + safeSizeOf(obj, obj1);
          goto _L1
        obj;
        throw obj;
    }

    public final int hitCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = hitCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final int maxSize()
    {
        this;
        JVM INSTR monitorenter ;
        int i = maxSize;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final int missCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = missCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final Object put(Object obj, Object obj1)
    {
        if(obj == null || obj1 == null)
            throw new NullPointerException("key == null || value == null");
        this;
        JVM INSTR monitorenter ;
        Object obj2;
        putCount = putCount + 1;
        size = size + safeSizeOf(obj, obj1);
        obj2 = map.put(obj, obj1);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_74;
        size = size - safeSizeOf(obj, obj2);
        this;
        JVM INSTR monitorexit ;
        if(obj2 != null)
            entryRemoved(false, obj, obj2, obj1);
        trimToSize(maxSize);
        return obj2;
        obj;
        throw obj;
    }

    public final int putCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = putCount;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public final Object remove(Object obj)
    {
        if(obj == null)
            throw new NullPointerException("key == null");
        this;
        JVM INSTR monitorenter ;
        Object obj1 = map.remove(obj);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_44;
        size = size - safeSizeOf(obj, obj1);
        this;
        JVM INSTR monitorexit ;
        if(obj1 != null)
            entryRemoved(false, obj, obj1, null);
        return obj1;
        obj;
        throw obj;
    }

    public void resize(int i)
    {
        if(i <= 0)
            throw new IllegalArgumentException("maxSize <= 0");
        this;
        JVM INSTR monitorenter ;
        maxSize = i;
        this;
        JVM INSTR monitorexit ;
        trimToSize(i);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final int size()
    {
        this;
        JVM INSTR monitorenter ;
        int i = size;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    protected int sizeOf(Object obj, Object obj1)
    {
        return 1;
    }

    public final Map snapshot()
    {
        this;
        JVM INSTR monitorenter ;
        LinkedHashMap linkedhashmap = new LinkedHashMap(map);
        this;
        JVM INSTR monitorexit ;
        return linkedhashmap;
        Exception exception;
        exception;
        throw exception;
    }

    public final String toString()
    {
        this;
        JVM INSTR monitorenter ;
        int i = hitCount + missCount;
        if(i == 0)
            break MISSING_BLOCK_LABEL_77;
        i = (hitCount * 100) / i;
_L1:
        String s = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[] {
            Integer.valueOf(maxSize), Integer.valueOf(hitCount), Integer.valueOf(missCount), Integer.valueOf(i)
        });
        this;
        JVM INSTR monitorexit ;
        return s;
        i = 0;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void trimToSize(int i)
    {
_L5:
        this;
        JVM INSTR monitorenter ;
        if(size < 0 || map.isEmpty() && size != 0)
        {
            IllegalStateException illegalstateexception = JVM INSTR new #46  <Class IllegalStateException>;
            StringBuilder stringbuilder = JVM INSTR new #48  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            illegalstateexception.IllegalStateException(stringbuilder.append(getClass().getName()).append(".sizeOf() is reporting inconsistent results!").toString());
            throw illegalstateexception;
        }
        break MISSING_BLOCK_LABEL_68;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        int j = size;
        if(j > i) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        Object obj;
        return;
_L2:
        if((obj = map.eldest()) == null) goto _L1; else goto _L3
_L3:
        Object obj1;
        obj1 = ((java.util.Map.Entry) (obj)).getKey();
        obj = ((java.util.Map.Entry) (obj)).getValue();
        map.remove(obj1);
        size = size - safeSizeOf(obj1, obj);
        evictionCount = evictionCount + 1;
        this;
        JVM INSTR monitorexit ;
        entryRemoved(true, obj1, obj, null);
        if(true) goto _L5; else goto _L4
_L4:
    }

    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;
}
