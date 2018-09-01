// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.exception;

import java.util.List;
import java.util.Set;

public interface ExceptionContext
{

    public abstract ExceptionContext addContextValue(String s, Object obj);

    public abstract List getContextEntries();

    public abstract Set getContextLabels();

    public abstract List getContextValues(String s);

    public abstract Object getFirstContextValue(String s);

    public abstract String getFormattedExceptionMessage(String s);

    public abstract ExceptionContext setContextValue(String s, Object obj);
}
