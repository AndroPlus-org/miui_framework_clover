// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import java.util.Set;

public interface PreferenceDataStore
{

    public boolean getBoolean(String s, boolean flag)
    {
        return flag;
    }

    public float getFloat(String s, float f)
    {
        return f;
    }

    public int getInt(String s, int i)
    {
        return i;
    }

    public long getLong(String s, long l)
    {
        return l;
    }

    public String getString(String s, String s1)
    {
        return s1;
    }

    public Set getStringSet(String s, Set set)
    {
        return set;
    }

    public void putBoolean(String s, boolean flag)
    {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putFloat(String s, float f)
    {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putInt(String s, int i)
    {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putLong(String s, long l)
    {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putString(String s, String s1)
    {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putStringSet(String s, Set set)
    {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }
}
