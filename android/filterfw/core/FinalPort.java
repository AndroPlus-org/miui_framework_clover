// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.lang.reflect.Field;

// Referenced classes of package android.filterfw.core:
//            FieldPort, Filter, Frame

public class FinalPort extends FieldPort
{

    public FinalPort(Filter filter, String s, Field field, boolean flag)
    {
        super(filter, s, field, flag);
    }

    protected void setFieldFrame(Frame frame, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        assertPortIsOpen();
        checkFrameType(frame, flag);
        if(mFilter.getStatus() != 0)
        {
            frame = JVM INSTR new #30  <Class RuntimeException>;
            StringBuilder stringbuilder = JVM INSTR new #32  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            frame.RuntimeException(stringbuilder.append("Attempting to modify ").append(this).append("!").toString());
            throw frame;
        }
        break MISSING_BLOCK_LABEL_63;
        frame;
        this;
        JVM INSTR monitorexit ;
        throw frame;
        super.setFieldFrame(frame, flag);
        super.transfer(null);
        this;
        JVM INSTR monitorexit ;
    }

    public String toString()
    {
        return (new StringBuilder()).append("final ").append(super.toString()).toString();
    }
}
