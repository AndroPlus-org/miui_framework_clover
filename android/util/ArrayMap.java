// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.util.*;
import libcore.util.EmptyArray;

// Referenced classes of package android.util:
//            ContainerHelpers, Log, MapCollections

public final class ArrayMap
    implements Map
{

    public ArrayMap()
    {
        this(0, false);
    }

    public ArrayMap(int i)
    {
        this(i, false);
    }

    public ArrayMap(int i, boolean flag)
    {
        mIdentityHashCode = flag;
        if(i < 0)
        {
            mHashes = EMPTY_IMMUTABLE_INTS;
            mArray = EmptyArray.OBJECT;
        } else
        if(i == 0)
        {
            mHashes = EmptyArray.INT;
            mArray = EmptyArray.OBJECT;
        } else
        {
            allocArrays(i);
        }
        mSize = 0;
    }

    public ArrayMap(ArrayMap arraymap)
    {
        this();
        if(arraymap != null)
            putAll(arraymap);
    }

    private void allocArrays(int i)
    {
        if(mHashes == EMPTY_IMMUTABLE_INTS)
            throw new UnsupportedOperationException("ArrayMap is immutable");
        if(i != 8) goto _L2; else goto _L1
_L1:
        Object obj = android/util/ArrayMap;
        android/util/ArrayMap;
        JVM INSTR monitorenter ;
        if(mTwiceBaseCache == null)
            break MISSING_BLOCK_LABEL_86;
        obj = ((Object) (mTwiceBaseCache));
        mArray = ((Object []) (obj));
        mTwiceBaseCache = (Object[])obj[0];
        mHashes = (int[])obj[1];
        obj[1] = null;
        obj[0] = null;
        mTwiceBaseCacheSize--;
        android/util/ArrayMap;
        JVM INSTR monitorexit ;
        return;
_L6:
        obj;
        JVM INSTR monitorexit ;
_L4:
        mHashes = new int[i];
        mArray = new Object[i << 1];
        return;
        obj;
        throw obj;
_L2:
        if(i != 4) goto _L4; else goto _L3
_L3:
        obj = android/util/ArrayMap;
        android/util/ArrayMap;
        JVM INSTR monitorenter ;
        if(mBaseCache == null) goto _L6; else goto _L5
_L5:
        Object aobj[];
        aobj = mBaseCache;
        mArray = aobj;
        mBaseCache = (Object[])aobj[0];
        mHashes = (int[])aobj[1];
        aobj[1] = null;
        aobj[0] = null;
        mBaseCacheSize--;
        android/util/ArrayMap;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static int binarySearchHashes(int ai[], int i, int j)
    {
        try
        {
            i = ContainerHelpers.binarySearch(ai, i, j);
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw new ConcurrentModificationException();
        }
        return i;
    }

    private static void freeArrays(int ai[], Object aobj[], int i)
    {
        if(ai.length != 8) goto _L2; else goto _L1
_L1:
        /*<invalid signature>*/java.lang.Object local = android/util/ArrayMap;
        android/util/ArrayMap;
        JVM INSTR monitorenter ;
        /*<invalid signature>*/java.lang.Object local1 = local;
        if(mTwiceBaseCacheSize >= 10)
            break MISSING_BLOCK_LABEL_70;
        aobj[0] = ((Object) (mTwiceBaseCache));
        aobj[1] = ai;
        for(i = (i << 1) - 1; i >= 2; i--)
            aobj[i] = null;

        mTwiceBaseCache = aobj;
        mTwiceBaseCacheSize++;
        local1 = local;
_L6:
        local1;
        JVM INSTR monitorexit ;
_L4:
        return;
        ai;
        throw ai;
_L2:
        if(ai.length != 4) goto _L4; else goto _L3
_L3:
        local = android/util/ArrayMap;
        android/util/ArrayMap;
        JVM INSTR monitorenter ;
        local1 = local;
        if(mBaseCacheSize >= 10)
            continue; /* Loop/switch isn't completed */
        aobj[0] = ((Object) (mBaseCache));
        aobj[1] = ai;
        for(i = (i << 1) - 1; i >= 2; i--)
            aobj[i] = null;

        mBaseCache = aobj;
        mBaseCacheSize++;
        local1 = local;
        if(true) goto _L6; else goto _L5
_L5:
        ai;
        throw ai;
    }

    private MapCollections getCollection()
    {
        if(mCollections == null)
            mCollections = new MapCollections() {

                protected void colClear()
                {
                    clear();
                }

                protected Object colGetEntry(int i, int j)
                {
                    return mArray[(i << 1) + j];
                }

                protected Map colGetMap()
                {
                    return ArrayMap.this;
                }

                protected int colGetSize()
                {
                    return mSize;
                }

                protected int colIndexOfKey(Object obj)
                {
                    return indexOfKey(obj);
                }

                protected int colIndexOfValue(Object obj)
                {
                    return indexOfValue(obj);
                }

                protected void colPut(Object obj, Object obj1)
                {
                    put(obj, obj1);
                }

                protected void colRemoveAt(int i)
                {
                    removeAt(i);
                }

                protected Object colSetValue(int i, Object obj)
                {
                    return setValueAt(i, obj);
                }

                final ArrayMap this$0;

            
            {
                this$0 = ArrayMap.this;
                super();
            }
            }
;
        return mCollections;
    }

    public void append(Object obj, Object obj1)
    {
        int i = mSize;
        int j;
        if(obj == null)
            j = 0;
        else
        if(mIdentityHashCode)
            j = System.identityHashCode(obj);
        else
            j = obj.hashCode();
        if(i >= mHashes.length)
            throw new IllegalStateException("Array is full");
        if(i > 0 && mHashes[i - 1] > j)
        {
            RuntimeException runtimeexception = new RuntimeException("here");
            runtimeexception.fillInStackTrace();
            Log.w("ArrayMap", (new StringBuilder()).append("New hash ").append(j).append(" is before end of array hash ").append(mHashes[i - 1]).append(" at index ").append(i).append(" key ").append(obj).toString(), runtimeexception);
            put(obj, obj1);
            return;
        } else
        {
            mSize = i + 1;
            mHashes[i] = j;
            j = i << 1;
            mArray[j] = obj;
            mArray[j + 1] = obj1;
            return;
        }
    }

    public void clear()
    {
        if(mSize > 0)
        {
            int ai[] = mHashes;
            Object aobj[] = mArray;
            int i = mSize;
            mHashes = EmptyArray.INT;
            mArray = EmptyArray.OBJECT;
            mSize = 0;
            freeArrays(ai, aobj, i);
        }
        if(mSize > 0)
            throw new ConcurrentModificationException();
        else
            return;
    }

    public boolean containsAll(Collection collection)
    {
        return MapCollections.containsAllHelper(this, collection);
    }

    public boolean containsKey(Object obj)
    {
        boolean flag = false;
        if(indexOfKey(obj) >= 0)
            flag = true;
        return flag;
    }

    public boolean containsValue(Object obj)
    {
        boolean flag = false;
        if(indexOfValue(obj) >= 0)
            flag = true;
        return flag;
    }

    public void ensureCapacity(int i)
    {
        int j = mSize;
        if(mHashes.length < i)
        {
            int ai[] = mHashes;
            Object aobj[] = mArray;
            allocArrays(i);
            if(mSize > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, j);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, j << 1);
            }
            freeArrays(ai, aobj, j);
        }
        if(mSize != j)
            throw new ConcurrentModificationException();
        else
            return;
    }

    public Set entrySet()
    {
        return getCollection().getEntrySet();
    }

    public boolean equals(Object obj)
    {
        int i;
        if(this == obj)
            return true;
        if(!(obj instanceof Map))
            break MISSING_BLOCK_LABEL_120;
        obj = (Map)obj;
        if(size() != ((Map) (obj)).size())
            return false;
        i = 0;
_L2:
        Object obj1;
        Object obj2;
        Object obj3;
        if(i >= mSize)
            break; /* Loop/switch isn't completed */
        obj1 = keyAt(i);
        obj2 = valueAt(i);
        obj3 = ((Map) (obj)).get(obj1);
        if(obj2 != null)
            break MISSING_BLOCK_LABEL_90;
        if(obj3 != null)
            break MISSING_BLOCK_LABEL_88;
        if(!(((Map) (obj)).containsKey(obj1) ^ true))
            break MISSING_BLOCK_LABEL_106;
        return false;
        boolean flag;
        try
        {
            flag = obj2.equals(obj3);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        if(!flag)
            return false;
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return true;
        return false;
    }

    public void erase()
    {
        if(mSize > 0)
        {
            int i = mSize;
            Object aobj[] = mArray;
            for(int j = 0; j < i << 1; j++)
                aobj[j] = null;

            mSize = 0;
        }
    }

    public Object get(Object obj)
    {
        int i = indexOfKey(obj);
        if(i >= 0)
            obj = mArray[(i << 1) + 1];
        else
            obj = null;
        return obj;
    }

    public int hashCode()
    {
        int ai[] = mHashes;
        Object aobj[] = mArray;
        int i = 0;
        int j = 0;
        int k = 1;
        int l = mSize;
        while(j < l) 
        {
            Object obj = aobj[k];
            int i1 = ai[j];
            int j1;
            if(obj == null)
                j1 = 0;
            else
                j1 = obj.hashCode();
            i += j1 ^ i1;
            j++;
            k += 2;
        }
        return i;
    }

    int indexOf(Object obj, int i)
    {
        int j = mSize;
        if(j == 0)
            return -1;
        int k = binarySearchHashes(mHashes, j, i);
        if(k < 0)
            return k;
        if(obj.equals(mArray[k << 1]))
            return k;
        int l;
        for(l = k + 1; l < j && mHashes[l] == i; l++)
            if(obj.equals(mArray[l << 1]))
                return l;

        for(k--; k >= 0 && mHashes[k] == i; k--)
            if(obj.equals(mArray[k << 1]))
                return k;

        return l;
    }

    public int indexOfKey(Object obj)
    {
        int i;
        if(obj == null)
        {
            i = indexOfNull();
        } else
        {
            if(mIdentityHashCode)
                i = System.identityHashCode(obj);
            else
                i = obj.hashCode();
            i = indexOf(obj, i);
        }
        return i;
    }

    int indexOfNull()
    {
        int i = mSize;
        if(i == 0)
            return -1;
        int j = binarySearchHashes(mHashes, i, 0);
        if(j < 0)
            return j;
        if(mArray[j << 1] == null)
            return j;
        int k;
        for(k = j + 1; k < i && mHashes[k] == 0; k++)
            if(mArray[k << 1] == null)
                return k;

        for(j--; j >= 0 && mHashes[j] == 0; j--)
            if(mArray[j << 1] == null)
                return j;

        return k;
    }

    int indexOfValue(Object obj)
    {
        int i = mSize * 2;
        Object aobj[] = mArray;
        if(obj == null)
        {
            for(int j = 1; j < i; j += 2)
                if(aobj[j] == null)
                    return j >> 1;

        } else
        {
            for(int k = 1; k < i; k += 2)
                if(obj.equals(aobj[k]))
                    return k >> 1;

        }
        return -1;
    }

    public boolean isEmpty()
    {
        boolean flag = false;
        if(mSize <= 0)
            flag = true;
        return flag;
    }

    public Object keyAt(int i)
    {
        return mArray[i << 1];
    }

    public Set keySet()
    {
        return getCollection().getKeySet();
    }

    public Object put(Object obj, Object obj1)
    {
        int i = mSize;
        int j;
        int k;
        if(obj == null)
        {
            j = 0;
            k = indexOfNull();
        } else
        {
            int i1;
            if(mIdentityHashCode)
                k = System.identityHashCode(obj);
            else
                k = obj.hashCode();
            i1 = indexOf(obj, k);
            j = k;
            k = i1;
        }
        if(k >= 0)
        {
            k = (k << 1) + 1;
            obj = mArray[k];
            mArray[k] = obj1;
            return obj;
        }
        int j1 = k;
        if(i >= mHashes.length)
        {
            int l;
            int ai[];
            Object aobj[];
            if(i >= 8)
                l = i + (i >> 1);
            else
            if(i >= 4)
                l = 8;
            else
                l = 4;
            ai = mHashes;
            aobj = mArray;
            allocArrays(l);
            if(i != mSize)
                throw new ConcurrentModificationException();
            if(mHashes.length > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, ai.length);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, aobj.length);
            }
            freeArrays(ai, aobj, i);
        }
        if(j1 < i)
        {
            System.arraycopy(mHashes, j1, mHashes, j1 + 1, i - j1);
            System.arraycopy(((Object) (mArray)), j1 << 1, ((Object) (mArray)), j1 + 1 << 1, mSize - j1 << 1);
        }
        if(i != mSize || j1 >= mHashes.length)
        {
            throw new ConcurrentModificationException();
        } else
        {
            mHashes[j1] = j;
            mArray[j1 << 1] = obj;
            mArray[(j1 << 1) + 1] = obj1;
            mSize = mSize + 1;
            return null;
        }
    }

    public void putAll(ArrayMap arraymap)
    {
        int i = arraymap.mSize;
        ensureCapacity(mSize + i);
        if(mSize == 0)
        {
            if(i > 0)
            {
                System.arraycopy(arraymap.mHashes, 0, mHashes, 0, i);
                System.arraycopy(((Object) (arraymap.mArray)), 0, ((Object) (mArray)), 0, i << 1);
                mSize = i;
            }
        } else
        {
            int j = 0;
            while(j < i) 
            {
                put(arraymap.keyAt(j), arraymap.valueAt(j));
                j++;
            }
        }
    }

    public void putAll(Map map)
    {
        ensureCapacity(mSize + map.size());
        java.util.Map.Entry entry;
        for(map = map.entrySet().iterator(); map.hasNext(); put(entry.getKey(), entry.getValue()))
            entry = (java.util.Map.Entry)map.next();

    }

    public Object remove(Object obj)
    {
        int i = indexOfKey(obj);
        if(i >= 0)
            return removeAt(i);
        else
            return null;
    }

    public boolean removeAll(Collection collection)
    {
        return MapCollections.removeAllHelper(this, collection);
    }

    public Object removeAt(int i)
    {
        Object obj = mArray[(i << 1) + 1];
        int j = mSize;
        int k;
        if(j <= 1)
        {
            freeArrays(mHashes, mArray, j);
            mHashes = EmptyArray.INT;
            mArray = EmptyArray.OBJECT;
            k = 0;
        } else
        {
            int l = j - 1;
            if(mHashes.length > 8 && mSize < mHashes.length / 3)
            {
                int ai[];
                Object aobj[];
                if(j > 8)
                    k = j + (j >> 1);
                else
                    k = 8;
                ai = mHashes;
                aobj = mArray;
                allocArrays(k);
                if(j != mSize)
                    throw new ConcurrentModificationException();
                if(i > 0)
                {
                    System.arraycopy(ai, 0, mHashes, 0, i);
                    System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, i << 1);
                }
                k = l;
                if(i < l)
                {
                    System.arraycopy(ai, i + 1, mHashes, i, l - i);
                    System.arraycopy(((Object) (aobj)), i + 1 << 1, ((Object) (mArray)), i << 1, l - i << 1);
                    k = l;
                }
            } else
            {
                if(i < l)
                {
                    System.arraycopy(mHashes, i + 1, mHashes, i, l - i);
                    System.arraycopy(((Object) (mArray)), i + 1 << 1, ((Object) (mArray)), i << 1, l - i << 1);
                }
                mArray[l << 1] = null;
                mArray[(l << 1) + 1] = null;
                k = l;
            }
        }
        if(j != mSize)
        {
            throw new ConcurrentModificationException();
        } else
        {
            mSize = k;
            return obj;
        }
    }

    public boolean retainAll(Collection collection)
    {
        return MapCollections.retainAllHelper(this, collection);
    }

    public Object setValueAt(int i, Object obj)
    {
        i = (i << 1) + 1;
        Object obj1 = mArray[i];
        mArray[i] = obj;
        return obj1;
    }

    public int size()
    {
        return mSize;
    }

    public String toString()
    {
        if(isEmpty())
            return "{}";
        StringBuilder stringbuilder = new StringBuilder(mSize * 28);
        stringbuilder.append('{');
        int i = 0;
        while(i < mSize) 
        {
            if(i > 0)
                stringbuilder.append(", ");
            Object obj = keyAt(i);
            if(obj != this)
                stringbuilder.append(obj);
            else
                stringbuilder.append("(this Map)");
            stringbuilder.append('=');
            obj = valueAt(i);
            if(obj != this)
                stringbuilder.append(obj);
            else
                stringbuilder.append("(this Map)");
            i++;
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void validate()
    {
        int i;
        int j;
        int k;
        int l;
        i = mSize;
        if(i <= 1)
            return;
        j = mHashes[0];
        k = 0;
        l = 1;
_L2:
        int i1;
        int j1;
        if(l >= i)
            break MISSING_BLOCK_LABEL_191;
        i1 = mHashes[l];
        if(i1 == j)
            break; /* Loop/switch isn't completed */
        j1 = l;
_L4:
        l++;
        j = i1;
        k = j1;
        if(true) goto _L2; else goto _L1
_L1:
        Object obj;
        int k1;
        obj = mArray[l << 1];
        k1 = l - 1;
_L5:
        i1 = j;
        j1 = k;
        if(k1 < k) goto _L4; else goto _L3
_L3:
        Object obj1 = mArray[k1 << 1];
        if(obj == obj1)
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate key in ArrayMap: ").append(obj).toString());
        if(obj != null && obj1 != null && obj.equals(obj1))
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate key in ArrayMap: ").append(obj).toString());
        k1--;
          goto _L5
          goto _L4
    }

    public Object valueAt(int i)
    {
        return mArray[(i << 1) + 1];
    }

    public Collection values()
    {
        return getCollection().getValues();
    }

    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
    private static final boolean DEBUG = false;
    public static final ArrayMap EMPTY = new ArrayMap(-1);
    static final int EMPTY_IMMUTABLE_INTS[] = new int[0];
    private static final String TAG = "ArrayMap";
    static Object mBaseCache[];
    static int mBaseCacheSize;
    static Object mTwiceBaseCache[];
    static int mTwiceBaseCacheSize;
    Object mArray[];
    MapCollections mCollections;
    int mHashes[];
    final boolean mIdentityHashCode;
    int mSize;

}
