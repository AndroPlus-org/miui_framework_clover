// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.transition;

import android.util.ArrayMap;
import android.view.View;
import java.util.*;

public class TransitionValues
{

    public TransitionValues()
    {
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof TransitionValues) && view == ((TransitionValues)obj).view && values.equals(((TransitionValues)obj).values);
    }

    public int hashCode()
    {
        return view.hashCode() * 31 + values.hashCode();
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("TransitionValues@").append(Integer.toHexString(hashCode())).append(":\n").toString();
        s = (new StringBuilder()).append(s).append("    view = ").append(view).append("\n").toString();
        s = (new StringBuilder()).append(s).append("    values:").toString();
        for(Iterator iterator = values.keySet().iterator(); iterator.hasNext();)
        {
            String s1 = (String)iterator.next();
            s = (new StringBuilder()).append(s).append("    ").append(s1).append(": ").append(values.get(s1)).append("\n").toString();
        }

        return s;
    }

    final ArrayList targetedTransitions = new ArrayList();
    public final Map values = new ArrayMap();
    public View view;
}
