// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.lang.reflect.Field;

// Referenced classes of package android.filterfw.core:
//            InputPort, Frame, Filter, FilterContext

public class FieldPort extends InputPort
{

    public FieldPort(Filter filter, String s, Field field, boolean flag)
    {
        super(filter, s);
        mValueWaiting = false;
        mField = field;
        mHasFrame = flag;
    }

    public boolean acceptsFrame()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mValueWaiting;
        this;
        JVM INSTR monitorexit ;
        return flag ^ true;
        Exception exception;
        exception;
        throw exception;
    }

    public void clear()
    {
    }

    public Object getTarget()
    {
        Object obj;
        try
        {
            obj = mField.get(mFilter);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            return null;
        }
        return obj;
    }

    public boolean hasFrame()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mHasFrame;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public Frame pullFrame()
    {
        this;
        JVM INSTR monitorenter ;
        RuntimeException runtimeexception = JVM INSTR new #46  <Class RuntimeException>;
        StringBuilder stringbuilder = JVM INSTR new #48  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        runtimeexception.RuntimeException(stringbuilder.append("Cannot pull frame on ").append(this).append("!").toString());
        throw runtimeexception;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void pushFrame(Frame frame)
    {
        setFieldFrame(frame, false);
    }

    protected void setFieldFrame(Frame frame, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        assertPortIsOpen();
        checkFrameType(frame, flag);
        frame = ((Frame) (frame.getObjectValue()));
        if(frame != null) goto _L2; else goto _L1
_L1:
        if(mValue == null) goto _L2; else goto _L3
_L3:
        mValue = frame;
        mValueWaiting = true;
_L4:
        mHasFrame = true;
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        flag = frame.equals(mValue);
        if(!(flag ^ true)) goto _L4; else goto _L3
        frame;
        throw frame;
    }

    public void setFrame(Frame frame)
    {
        setFieldFrame(frame, true);
    }

    public String toString()
    {
        return (new StringBuilder()).append("field ").append(super.toString()).toString();
    }

    public void transfer(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mValueWaiting;
        if(!flag)
            break MISSING_BLOCK_LABEL_47;
        mField.set(mFilter, mValue);
        mValueWaiting = false;
        if(filtercontext == null)
            break MISSING_BLOCK_LABEL_47;
        mFilter.notifyFieldPortValueUpdated(mName, filtercontext);
        this;
        JVM INSTR monitorexit ;
        return;
        filtercontext;
        filtercontext = JVM INSTR new #46  <Class RuntimeException>;
        StringBuilder stringbuilder = JVM INSTR new #48  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        filtercontext.RuntimeException(stringbuilder.append("Access to field '").append(mField.getName()).append("' was denied!").toString());
        throw filtercontext;
        filtercontext;
        this;
        JVM INSTR monitorexit ;
        throw filtercontext;
    }

    protected Field mField;
    protected boolean mHasFrame;
    protected Object mValue;
    protected boolean mValueWaiting;
}
