// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import android.util.ArraySet;
import java.util.*;

// Referenced classes of package android.security.net.config:
//            Pin

public final class PinSet
{

    public PinSet(Set set, long l)
    {
        if(set == null)
        {
            throw new NullPointerException("pins must not be null");
        } else
        {
            pins = set;
            expirationTime = l;
            return;
        }
    }

    Set getPinAlgorithms()
    {
        ArraySet arrayset = new ArraySet();
        for(Iterator iterator = pins.iterator(); iterator.hasNext(); arrayset.add(((Pin)iterator.next()).digestAlgorithm));
        return arrayset;
    }

    public static final PinSet EMPTY_PINSET = new PinSet(Collections.emptySet(), 0x7fffffffffffffffL);
    public final long expirationTime;
    public final Set pins;

}
