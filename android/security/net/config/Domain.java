// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.security.net.config;

import java.util.Locale;

public final class Domain
{

    public Domain(String s, boolean flag)
    {
        if(s == null)
        {
            throw new NullPointerException("Hostname must not be null");
        } else
        {
            hostname = s.toLowerCase(Locale.US);
            subdomainsIncluded = flag;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == this)
            return true;
        if(!(obj instanceof Domain))
            return false;
        obj = (Domain)obj;
        if(((Domain) (obj)).subdomainsIncluded == subdomainsIncluded)
            flag = ((Domain) (obj)).hostname.equals(hostname);
        return flag;
    }

    public int hashCode()
    {
        int i = hostname.hashCode();
        char c;
        if(subdomainsIncluded)
            c = '\u04CF';
        else
            c = '\u04D5';
        return c ^ i;
    }

    public final String hostname;
    public final boolean subdomainsIncluded;
}
