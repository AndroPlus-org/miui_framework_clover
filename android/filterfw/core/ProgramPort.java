// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.lang.reflect.Field;

// Referenced classes of package android.filterfw.core:
//            FieldPort, Program, Filter, FilterContext

public class ProgramPort extends FieldPort
{

    public ProgramPort(Filter filter, String s, String s1, Field field, boolean flag)
    {
        super(filter, s, field, flag);
        mVarName = s1;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Program ").append(super.toString()).toString();
    }

    public void transfer(FilterContext filtercontext)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mValueWaiting;
        if(!flag)
            break MISSING_BLOCK_LABEL_47;
        filtercontext = ((FilterContext) (mField.get(mFilter)));
        if(filtercontext == null)
            break MISSING_BLOCK_LABEL_47;
        ((Program)filtercontext).setHostValue(mVarName, mValue);
        mValueWaiting = false;
        this;
        JVM INSTR monitorexit ;
        return;
        filtercontext;
        RuntimeException runtimeexception = JVM INSTR new #66  <Class RuntimeException>;
        filtercontext = JVM INSTR new #18  <Class StringBuilder>;
        filtercontext.StringBuilder();
        runtimeexception.RuntimeException(filtercontext.append("Non Program field '").append(mField.getName()).append("' annotated with ProgramParameter!").toString());
        throw runtimeexception;
        filtercontext;
        this;
        JVM INSTR monitorexit ;
        throw filtercontext;
        filtercontext;
        filtercontext = JVM INSTR new #66  <Class RuntimeException>;
        StringBuilder stringbuilder = JVM INSTR new #18  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        filtercontext.RuntimeException(stringbuilder.append("Access to program field '").append(mField.getName()).append("' was denied!").toString());
        throw filtercontext;
    }

    protected String mVarName;
}
