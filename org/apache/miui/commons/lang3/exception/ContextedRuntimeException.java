// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3.exception;

import java.util.List;
import java.util.Set;

// Referenced classes of package org.apache.miui.commons.lang3.exception:
//            ExceptionContext, DefaultExceptionContext

public class ContextedRuntimeException extends RuntimeException
    implements ExceptionContext
{

    public ContextedRuntimeException()
    {
        exceptionContext = new DefaultExceptionContext();
    }

    public ContextedRuntimeException(String s)
    {
        super(s);
        exceptionContext = new DefaultExceptionContext();
    }

    public ContextedRuntimeException(String s, Throwable throwable)
    {
        super(s, throwable);
        exceptionContext = new DefaultExceptionContext();
    }

    public ContextedRuntimeException(String s, Throwable throwable, ExceptionContext exceptioncontext)
    {
        super(s, throwable);
        s = exceptioncontext;
        if(exceptioncontext == null)
            s = new DefaultExceptionContext();
        exceptionContext = s;
    }

    public ContextedRuntimeException(Throwable throwable)
    {
        super(throwable);
        exceptionContext = new DefaultExceptionContext();
    }

    public ContextedRuntimeException addContextValue(String s, Object obj)
    {
        exceptionContext.addContextValue(s, obj);
        return this;
    }

    public volatile ExceptionContext addContextValue(String s, Object obj)
    {
        return addContextValue(s, obj);
    }

    public List getContextEntries()
    {
        return exceptionContext.getContextEntries();
    }

    public Set getContextLabels()
    {
        return exceptionContext.getContextLabels();
    }

    public List getContextValues(String s)
    {
        return exceptionContext.getContextValues(s);
    }

    public Object getFirstContextValue(String s)
    {
        return exceptionContext.getFirstContextValue(s);
    }

    public String getFormattedExceptionMessage(String s)
    {
        return exceptionContext.getFormattedExceptionMessage(s);
    }

    public String getMessage()
    {
        return getFormattedExceptionMessage(super.getMessage());
    }

    public String getRawMessage()
    {
        return super.getMessage();
    }

    public ContextedRuntimeException setContextValue(String s, Object obj)
    {
        exceptionContext.setContextValue(s, obj);
        return this;
    }

    public volatile ExceptionContext setContextValue(String s, Object obj)
    {
        return setContextValue(s, obj);
    }

    private static final long serialVersionUID = 0x132dd72L;
    private final ExceptionContext exceptionContext;
}
