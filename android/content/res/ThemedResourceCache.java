// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.util.ArrayMap;
import android.util.LongSparseArray;
import java.lang.ref.WeakReference;

abstract class ThemedResourceCache
{

    ThemedResourceCache()
    {
    }

    private LongSparseArray getThemedLocked(Resources.Theme theme, boolean flag)
    {
label0:
        {
            if(theme == null)
            {
                if(mNullThemedEntries == null && flag)
                    mNullThemedEntries = new LongSparseArray(1);
                return mNullThemedEntries;
            }
            if(mThemedEntries == null)
            {
                if(!flag)
                    break label0;
                mThemedEntries = new ArrayMap(1);
            }
            Resources.ThemeKey themekey = theme.getKey();
            LongSparseArray longsparsearray = (LongSparseArray)mThemedEntries.get(themekey);
            theme = longsparsearray;
            if(longsparsearray == null)
            {
                theme = longsparsearray;
                if(flag)
                {
                    theme = new LongSparseArray(1);
                    Resources.ThemeKey themekey1 = themekey.clone();
                    mThemedEntries.put(themekey1, theme);
                }
            }
            return theme;
        }
        return null;
    }

    private LongSparseArray getUnthemedLocked(boolean flag)
    {
        if(mUnthemedEntries == null && flag)
            mUnthemedEntries = new LongSparseArray(1);
        return mUnthemedEntries;
    }

    private boolean prune(int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j;
        if(mThemedEntries == null)
            break MISSING_BLOCK_LABEL_57;
        j = mThemedEntries.size() - 1;
_L3:
        if(j < 0) goto _L2; else goto _L1
_L1:
        if(pruneEntriesLocked((LongSparseArray)mThemedEntries.valueAt(j), i))
            mThemedEntries.removeAt(j);
        j--;
          goto _L3
_L2:
        pruneEntriesLocked(mNullThemedEntries, i);
        pruneEntriesLocked(mUnthemedEntries, i);
        if(mThemedEntries != null || mNullThemedEntries != null) goto _L5; else goto _L4
_L4:
        LongSparseArray longsparsearray = mUnthemedEntries;
        boolean flag;
        if(longsparsearray == null)
            flag = true;
        else
            flag = false;
_L7:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L5:
        flag = false;
        if(true) goto _L7; else goto _L6
_L6:
        Exception exception;
        exception;
        throw exception;
    }

    private boolean pruneEntriesLocked(LongSparseArray longsparsearray, int i)
    {
        boolean flag = true;
        if(longsparsearray == null)
            return true;
        for(int j = longsparsearray.size() - 1; j >= 0; j--)
        {
            WeakReference weakreference = (WeakReference)longsparsearray.valueAt(j);
            if(weakreference == null || pruneEntryLocked(weakreference.get(), i))
                longsparsearray.removeAt(j);
        }

        if(longsparsearray.size() != 0)
            flag = false;
        return flag;
    }

    private boolean pruneEntryLocked(Object obj, int i)
    {
        boolean flag = false;
        if(obj != null)
        {
            if(i != 0)
                flag = shouldInvalidateEntry(obj, i);
        } else
        {
            flag = true;
        }
        return flag;
    }

    public Object get(long l, Resources.Theme theme)
    {
        this;
        JVM INSTR monitorenter ;
        theme = getThemedLocked(theme, false);
        if(theme == null)
            break MISSING_BLOCK_LABEL_35;
        theme = (WeakReference)theme.get(l);
        if(theme == null)
            break MISSING_BLOCK_LABEL_35;
        theme = ((Resources.Theme) (theme.get()));
        this;
        JVM INSTR monitorexit ;
        return theme;
        theme = getUnthemedLocked(false);
        if(theme == null)
            break MISSING_BLOCK_LABEL_67;
        theme = (WeakReference)theme.get(l);
        if(theme == null)
            break MISSING_BLOCK_LABEL_67;
        theme = ((Resources.Theme) (theme.get()));
        this;
        JVM INSTR monitorexit ;
        return theme;
        this;
        JVM INSTR monitorexit ;
        return null;
        theme;
        throw theme;
    }

    public void onConfigurationChange(int i)
    {
        prune(i);
    }

    public void put(long l, Resources.Theme theme, Object obj)
    {
        put(l, theme, obj, true);
    }

    public void put(long l, Resources.Theme theme, Object obj, boolean flag)
    {
        if(obj == null)
            return;
        this;
        JVM INSTR monitorenter ;
        if(flag) goto _L2; else goto _L1
_L1:
        theme = getUnthemedLocked(true);
_L4:
        if(theme == null)
            break MISSING_BLOCK_LABEL_42;
        WeakReference weakreference = JVM INSTR new #78  <Class WeakReference>;
        weakreference.WeakReference(obj);
        theme.put(l, weakreference);
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        theme = getThemedLocked(theme, true);
        if(true) goto _L4; else goto _L3
_L3:
        theme;
        throw theme;
    }

    protected abstract boolean shouldInvalidateEntry(Object obj, int i);

    private LongSparseArray mNullThemedEntries;
    private ArrayMap mThemedEntries;
    private LongSparseArray mUnthemedEntries;
}
