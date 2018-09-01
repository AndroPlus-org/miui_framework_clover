// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.http.params;


public interface HttpParams
{

    public abstract HttpParams copy();

    public abstract boolean getBooleanParameter(String s, boolean flag);

    public abstract double getDoubleParameter(String s, double d);

    public abstract int getIntParameter(String s, int i);

    public abstract long getLongParameter(String s, long l);

    public abstract Object getParameter(String s);

    public abstract boolean isParameterFalse(String s);

    public abstract boolean isParameterTrue(String s);

    public abstract boolean removeParameter(String s);

    public abstract HttpParams setBooleanParameter(String s, boolean flag);

    public abstract HttpParams setDoubleParameter(String s, double d);

    public abstract HttpParams setIntParameter(String s, int i);

    public abstract HttpParams setLongParameter(String s, long l);

    public abstract HttpParams setParameter(String s, Object obj);
}
