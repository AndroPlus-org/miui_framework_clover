// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.lang.reflect.Array;
import java.util.*;
import libcore.util.EmptyArray;

// Referenced classes of package android.util:
//            Slog, ContainerHelpers, MapCollections

public final class ArraySet
    implements Collection, Set
{

    public ArraySet()
    {
        this(0, false);
    }

    public ArraySet(int i)
    {
        this(i, false);
    }

    public ArraySet(int i, boolean flag)
    {
        mIdentityHashCode = flag;
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

    public ArraySet(ArraySet arrayset)
    {
        this();
        if(arrayset != null)
            addAll(arrayset);
    }

    public ArraySet(Collection collection)
    {
        this();
        if(collection != null)
            addAll(collection);
    }

    private void allocArrays(int i)
    {
        if(i != 8) goto _L2; else goto _L1
_L1:
        /*<invalid signature>*/java.lang.Object local = android/util/ArraySet;
        android/util/ArraySet;
        JVM INSTR monitorenter ;
        Object obj = local;
        if(sTwiceBaseCache == null)
            break MISSING_BLOCK_LABEL_123;
        obj = ((Object) (sTwiceBaseCache));
        mArray = ((Object []) (obj));
        sTwiceBaseCache = (Object[])obj[0];
        mHashes = (int[])obj[1];
        obj[1] = null;
        obj[0] = null;
        sTwiceBaseCacheSize--;
        android/util/ArraySet;
        JVM INSTR monitorexit ;
        return;
        ClassCastException classcastexception;
        classcastexception;
        StringBuilder stringbuilder = JVM INSTR new #86  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Slog.wtf("ArraySet", stringbuilder.append("Found corrupt ArraySet cache: [0]=").append(obj[0]).append(" [1]=").append(obj[1]).toString());
        sTwiceBaseCache = null;
        sTwiceBaseCacheSize = 0;
        obj = local;
_L6:
        obj;
        JVM INSTR monitorexit ;
_L4:
        mHashes = new int[i];
        mArray = new Object[i];
        return;
        obj;
        throw obj;
_L2:
        if(i != 4) goto _L4; else goto _L3
_L3:
        local = android/util/ArraySet;
        android/util/ArraySet;
        JVM INSTR monitorenter ;
        obj = local;
        if(sBaseCache == null)
            continue; /* Loop/switch isn't completed */
        obj = ((Object) (sBaseCache));
        mArray = ((Object []) (obj));
        sBaseCache = (Object[])obj[0];
        mHashes = (int[])obj[1];
        obj[1] = null;
        obj[0] = null;
        sBaseCacheSize--;
        android/util/ArraySet;
        JVM INSTR monitorexit ;
        return;
        stringbuilder;
        StringBuilder stringbuilder1 = JVM INSTR new #86  <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Slog.wtf("ArraySet", stringbuilder1.append("Found corrupt ArraySet cache: [0]=").append(obj[0]).append(" [1]=").append(obj[1]).toString());
        sBaseCache = null;
        sBaseCacheSize = 0;
        obj = local;
        if(true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    private static void freeArrays(int ai[], Object aobj[], int i)
    {
        if(ai.length != 8) goto _L2; else goto _L1
_L1:
        /*<invalid signature>*/java.lang.Object local = android/util/ArraySet;
        android/util/ArraySet;
        JVM INSTR monitorenter ;
        /*<invalid signature>*/java.lang.Object local1 = local;
        if(sTwiceBaseCacheSize >= 10)
            break MISSING_BLOCK_LABEL_67;
        aobj[0] = ((Object) (sTwiceBaseCache));
        aobj[1] = ai;
        for(i--; i >= 2; i--)
            aobj[i] = null;

        sTwiceBaseCache = aobj;
        sTwiceBaseCacheSize++;
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
        local = android/util/ArraySet;
        android/util/ArraySet;
        JVM INSTR monitorenter ;
        local1 = local;
        if(sBaseCacheSize >= 10)
            continue; /* Loop/switch isn't completed */
        aobj[0] = ((Object) (sBaseCache));
        aobj[1] = ai;
        for(i--; i >= 2; i--)
            aobj[i] = null;

        sBaseCache = aobj;
        sBaseCacheSize++;
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
                    return mArray[i];
                }

                protected Map colGetMap()
                {
                    throw new UnsupportedOperationException("not a map");
                }

                protected int colGetSize()
                {
                    return mSize;
                }

                protected int colIndexOfKey(Object obj)
                {
                    return indexOf(obj);
                }

                protected int colIndexOfValue(Object obj)
                {
                    return indexOf(obj);
                }

                protected void colPut(Object obj, Object obj1)
                {
                    add(obj);
                }

                protected void colRemoveAt(int i)
                {
                    removeAt(i);
                }

                protected Object colSetValue(int i, Object obj)
                {
                    throw new UnsupportedOperationException("not a map");
                }

                final ArraySet this$0;

            
            {
                this$0 = ArraySet.this;
                super();
            }
            }
;
        return mCollections;
    }

    private int indexOf(Object obj, int i)
    {
        int j = mSize;
        if(j == 0)
            return -1;
        int l = ContainerHelpers.binarySearch(mHashes, j, i);
        if(l < 0)
            return l;
        if(obj.equals(mArray[l]))
            return l;
        int i1;
        for(i1 = l + 1; i1 < j && mHashes[i1] == i; i1++)
            if(obj.equals(mArray[i1]))
                return i1;

        for(int k = l - 1; k >= 0 && mHashes[k] == i; k--)
            if(obj.equals(mArray[k]))
                return k;

        return i1;
    }

    private int indexOfNull()
    {
        int i = mSize;
        if(i == 0)
            return -1;
        int k = ContainerHelpers.binarySearch(mHashes, i, 0);
        if(k < 0)
            return k;
        if(mArray[k] == null)
            return k;
        int l;
        for(l = k + 1; l < i && mHashes[l] == 0; l++)
            if(mArray[l] == null)
                return l;

        for(int j = k - 1; j >= 0 && mHashes[j] == 0; j--)
            if(mArray[j] == null)
                return j;

        return l;
    }

    public boolean add(Object obj)
    {
        int i;
        int j;
        if(obj == null)
        {
            i = 0;
            j = indexOfNull();
        } else
        {
            int l;
            if(mIdentityHashCode)
                j = System.identityHashCode(obj);
            else
                j = obj.hashCode();
            l = indexOf(obj, j);
            i = j;
            j = l;
        }
        if(j >= 0)
            return false;
        int i1 = j;
        if(mSize >= mHashes.length)
        {
            int k;
            int ai[];
            Object aobj[];
            if(mSize >= 8)
                k = mSize + (mSize >> 1);
            else
            if(mSize >= 4)
                k = 8;
            else
                k = 4;
            ai = mHashes;
            aobj = mArray;
            allocArrays(k);
            if(mHashes.length > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, ai.length);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, aobj.length);
            }
            freeArrays(ai, aobj, mSize);
        }
        if(i1 < mSize)
        {
            System.arraycopy(mHashes, i1, mHashes, i1 + 1, mSize - i1);
            System.arraycopy(((Object) (mArray)), i1, ((Object) (mArray)), i1 + 1, mSize - i1);
        }
        mHashes[i1] = i;
        mArray[i1] = obj;
        mSize = mSize + 1;
        return true;
    }

    public void addAll(ArraySet arrayset)
    {
        int i = arrayset.mSize;
        ensureCapacity(mSize + i);
        if(mSize == 0)
        {
            if(i > 0)
            {
                System.arraycopy(arrayset.mHashes, 0, mHashes, 0, i);
                System.arraycopy(((Object) (arrayset.mArray)), 0, ((Object) (mArray)), 0, i);
                mSize = i;
            }
        } else
        {
            int j = 0;
            while(j < i) 
            {
                add(arrayset.valueAt(j));
                j++;
            }
        }
    }

    public boolean addAll(Collection collection)
    {
        ensureCapacity(mSize + collection.size());
        boolean flag = false;
        for(collection = collection.iterator(); collection.hasNext();)
            flag |= add(collection.next());

        return flag;
    }

    public void append(Object obj)
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
            add(obj);
            return;
        } else
        {
            mSize = i + 1;
            mHashes[i] = j;
            mArray[i] = obj;
            return;
        }
    }

    public void clear()
    {
        if(mSize != 0)
        {
            freeArrays(mHashes, mArray, mSize);
            mHashes = EmptyArray.INT;
            mArray = EmptyArray.OBJECT;
            mSize = 0;
        }
    }

    public boolean contains(Object obj)
    {
        boolean flag = false;
        if(indexOf(obj) >= 0)
            flag = true;
        return flag;
    }

    public boolean containsAll(Collection collection)
    {
        for(collection = collection.iterator(); collection.hasNext();)
            if(!contains(collection.next()))
                return false;

        return true;
    }

    public void ensureCapacity(int i)
    {
        if(mHashes.length < i)
        {
            int ai[] = mHashes;
            Object aobj[] = mArray;
            allocArrays(i);
            if(mSize > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, mSize);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, mSize);
            }
            freeArrays(ai, aobj, mSize);
        }
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj instanceof Set)
        {
            obj = (Set)obj;
            if(size() != ((Set) (obj)).size())
                return false;
            int i = 0;
            do
            {
                boolean flag;
                try
                {
                    if(i >= mSize)
                        break;
                    flag = ((Set) (obj)).contains(valueAt(i));
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
            } while(true);
            return true;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        int ai[] = mHashes;
        int i = 0;
        int j = 0;
        for(int k = mSize; j < k; j++)
            i += ai[j];

        return i;
    }

    public int indexOf(Object obj)
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

    public boolean isEmpty()
    {
        boolean flag = false;
        if(mSize <= 0)
            flag = true;
        return flag;
    }

    public Iterator iterator()
    {
        return getCollection().getKeySet().iterator();
    }

    public boolean remove(Object obj)
    {
        int i = indexOf(obj);
        if(i >= 0)
        {
            removeAt(i);
            return true;
        } else
        {
            return false;
        }
    }

    public boolean removeAll(ArraySet arrayset)
    {
        int i = arrayset.mSize;
        int j = mSize;
        for(int k = 0; k < i; k++)
            remove(arrayset.valueAt(k));

        boolean flag;
        if(j != mSize)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean removeAll(Collection collection)
    {
        boolean flag = false;
        for(collection = collection.iterator(); collection.hasNext();)
            flag |= remove(collection.next());

        return flag;
    }

    public Object removeAt(int i)
    {
        Object obj = mArray[i];
        if(mSize > 1) goto _L2; else goto _L1
_L1:
        freeArrays(mHashes, mArray, mSize);
        mHashes = EmptyArray.INT;
        mArray = EmptyArray.OBJECT;
        mSize = 0;
_L4:
        return obj;
_L2:
        if(mHashes.length > 8 && mSize < mHashes.length / 3)
        {
            int j;
            int ai[];
            Object aobj[];
            if(mSize > 8)
                j = mSize + (mSize >> 1);
            else
                j = 8;
            ai = mHashes;
            aobj = mArray;
            allocArrays(j);
            mSize = mSize - 1;
            if(i > 0)
            {
                System.arraycopy(ai, 0, mHashes, 0, i);
                System.arraycopy(((Object) (aobj)), 0, ((Object) (mArray)), 0, i);
            }
            if(i < mSize)
            {
                System.arraycopy(ai, i + 1, mHashes, i, mSize - i);
                System.arraycopy(((Object) (aobj)), i + 1, ((Object) (mArray)), i, mSize - i);
            }
        } else
        {
            mSize = mSize - 1;
            if(i < mSize)
            {
                System.arraycopy(mHashes, i + 1, mHashes, i, mSize - i);
                System.arraycopy(((Object) (mArray)), i + 1, ((Object) (mArray)), i, mSize - i);
            }
            mArray[mSize] = null;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public boolean retainAll(Collection collection)
    {
        boolean flag = false;
        for(int i = mSize - 1; i >= 0; i--)
            if(!collection.contains(mArray[i]))
            {
                removeAt(i);
                flag = true;
            }

        return flag;
    }

    public int size()
    {
        return mSize;
    }

    public Object[] toArray()
    {
        Object aobj[] = new Object[mSize];
        System.arraycopy(((Object) (mArray)), 0, ((Object) (aobj)), 0, mSize);
        return aobj;
    }

    public Object[] toArray(Object aobj[])
    {
        Object aobj1[] = aobj;
        if(aobj.length < mSize)
            aobj1 = (Object[])Array.newInstance(((Object) (aobj)).getClass().getComponentType(), mSize);
        System.arraycopy(((Object) (mArray)), 0, ((Object) (aobj1)), 0, mSize);
        if(aobj1.length > mSize)
            aobj1[mSize] = null;
        return aobj1;
    }

    public String toString()
    {
        if(isEmpty())
            return "{}";
        StringBuilder stringbuilder = new StringBuilder(mSize * 14);
        stringbuilder.append('{');
        int i = 0;
        while(i < mSize) 
        {
            if(i > 0)
                stringbuilder.append(", ");
            Object obj = valueAt(i);
            if(obj != this)
                stringbuilder.append(obj);
            else
                stringbuilder.append("(this Set)");
            i++;
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public Object valueAt(int i)
    {
        return mArray[i];
    }

    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArraySet";
    static Object sBaseCache[];
    static int sBaseCacheSize;
    static Object sTwiceBaseCache[];
    static int sTwiceBaseCacheSize;
    Object mArray[];
    MapCollections mCollections;
    int mHashes[];
    final boolean mIdentityHashCode;
    int mSize;
}
