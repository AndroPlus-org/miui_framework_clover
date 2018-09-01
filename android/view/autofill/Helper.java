// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.autofill;

import android.os.Bundle;
import java.util.*;

public final class Helper
{

    private Helper()
    {
        throw new UnsupportedOperationException("contains static members only");
    }

    static StringBuilder append(StringBuilder stringbuilder, Bundle bundle)
    {
        if(bundle == null || sDebug ^ true)
            stringbuilder.append("N/A");
        else
        if(!sVerbose)
        {
            stringbuilder.append("[REDACTED]");
        } else
        {
            Object obj = bundle.keySet();
            stringbuilder.append("[Bundle with ").append(((Set) (obj)).size()).append(" extras:");
            for(Iterator iterator = ((Iterable) (obj)).iterator(); iterator.hasNext(); stringbuilder.append(obj))
            {
                obj = (String)iterator.next();
                Object obj1 = bundle.get(((String) (obj)));
                stringbuilder.append(' ').append(((String) (obj))).append('=');
                obj = obj1;
                if(obj1 instanceof Object[])
                    obj = Arrays.toString((java.util.Objects[])obj1);
            }

            stringbuilder.append(']');
        }
        return stringbuilder;
    }

    public static final String REDACTED = "[REDACTED]";
    public static boolean sDebug = false;
    public static boolean sVerbose = false;

}
