// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.data;

import android.graphics.Bitmap;
import java.util.HashMap;

public class ContextVariables
{

    public ContextVariables()
    {
        mMap = new HashMap();
    }

    public void clear()
    {
        mMap.clear();
    }

    public Bitmap getBmp(String s)
    {
        s = ((String) (mMap.get(s)));
        if(s == null)
            return null;
        if(!(s instanceof Bitmap))
            return null;
        else
            return (Bitmap)s;
    }

    public Double getDouble(String s)
    {
        s = ((String) (mMap.get(s)));
        if(s == null)
            return null;
        if(!(s instanceof Double))
            return null;
        else
            return (Double)s;
    }

    public Integer getInt(String s)
    {
        s = ((String) (mMap.get(s)));
        if(s == null)
            return null;
        if(!(s instanceof Integer))
            return null;
        else
            return (Integer)s;
    }

    public Long getLong(String s)
    {
        s = ((String) (mMap.get(s)));
        if(s == null)
            return null;
        if(!(s instanceof Long))
            return null;
        else
            return (Long)s;
    }

    public String getString(String s)
    {
        s = ((String) (mMap.get(s)));
        if(s == null)
            return null;
        if(!(s instanceof String))
            return String.valueOf(s);
        else
            return (String)s;
    }

    public Object getVar(String s)
    {
        return mMap.get(s);
    }

    public void setVar(String s, Object obj)
    {
        mMap.put(s, obj);
    }

    private HashMap mMap;
}
