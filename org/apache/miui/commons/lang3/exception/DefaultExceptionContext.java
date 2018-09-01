// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.exception;

import java.io.Serializable;
import java.util.*;
import org.apache.miui.commons.lang3.StringUtils;
import org.apache.miui.commons.lang3.tuple.ImmutablePair;
import org.apache.miui.commons.lang3.tuple.Pair;

// Referenced classes of package org.apache.miui.commons.lang3.exception:
//            ExceptionContext, ExceptionUtils

public class DefaultExceptionContext
    implements ExceptionContext, Serializable
{

    public DefaultExceptionContext()
    {
    }

    public DefaultExceptionContext addContextValue(String s, Object obj)
    {
        contextValues.add(new ImmutablePair(s, obj));
        return this;
    }

    public volatile ExceptionContext addContextValue(String s, Object obj)
    {
        return addContextValue(s, obj);
    }

    public List getContextEntries()
    {
        return contextValues;
    }

    public Set getContextLabels()
    {
        HashSet hashset = new HashSet();
        for(Iterator iterator = contextValues.iterator(); iterator.hasNext(); hashset.add((String)((Pair)iterator.next()).getKey()));
        return hashset;
    }

    public List getContextValues(String s)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = contextValues.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Pair pair = (Pair)iterator.next();
            if(StringUtils.equals(s, (CharSequence)pair.getKey()))
                arraylist.add(pair.getValue());
        } while(true);
        return arraylist;
    }

    public Object getFirstContextValue(String s)
    {
        for(Iterator iterator = contextValues.iterator(); iterator.hasNext();)
        {
            Pair pair = (Pair)iterator.next();
            if(StringUtils.equals(s, (CharSequence)pair.getKey()))
                return pair.getValue();
        }

        return null;
    }

    public String getFormattedExceptionMessage(String s)
    {
        StringBuilder stringbuilder = new StringBuilder(256);
        if(s != null)
            stringbuilder.append(s);
        if(contextValues.size() > 0)
        {
            if(stringbuilder.length() > 0)
                stringbuilder.append('\n');
            stringbuilder.append("Exception Context:\n");
            int i = 0;
            Iterator iterator = contextValues.iterator();
            while(iterator.hasNext()) 
            {
                s = (Pair)iterator.next();
                stringbuilder.append("\t[");
                i++;
                stringbuilder.append(i);
                stringbuilder.append(':');
                stringbuilder.append((String)s.getKey());
                stringbuilder.append("=");
                s = ((String) (s.getValue()));
                if(s == null)
                {
                    stringbuilder.append("null");
                } else
                {
                    try
                    {
                        s = s.toString();
                    }
                    // Misplaced declaration of an exception variable
                    catch(String s)
                    {
                        s = (new StringBuilder()).append("Exception thrown on toString(): ").append(ExceptionUtils.getStackTrace(s)).toString();
                    }
                    stringbuilder.append(s);
                }
                stringbuilder.append("]\n");
            }
            stringbuilder.append("---------------------------------");
        }
        return stringbuilder.toString();
    }

    public DefaultExceptionContext setContextValue(String s, Object obj)
    {
        Iterator iterator = contextValues.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(StringUtils.equals(s, (CharSequence)((Pair)iterator.next()).getKey()))
                iterator.remove();
        } while(true);
        addContextValue(s, obj);
        return this;
    }

    public volatile ExceptionContext setContextValue(String s, Object obj)
    {
        return setContextValue(s, obj);
    }

    private static final long serialVersionUID = 0x132dd72L;
    private final List contextValues = new ArrayList();
}
