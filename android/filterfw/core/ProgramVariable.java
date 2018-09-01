// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            Program

public class ProgramVariable
{

    public ProgramVariable(Program program, String s)
    {
        mProgram = program;
        mVarName = s;
    }

    public Program getProgram()
    {
        return mProgram;
    }

    public Object getValue()
    {
        if(mProgram == null)
            throw new RuntimeException((new StringBuilder()).append("Attempting to get program variable '").append(mVarName).append("' but the program is null!").toString());
        else
            return mProgram.getHostValue(mVarName);
    }

    public String getVariableName()
    {
        return mVarName;
    }

    public void setValue(Object obj)
    {
        if(mProgram == null)
        {
            throw new RuntimeException((new StringBuilder()).append("Attempting to set program variable '").append(mVarName).append("' but the program is null!").toString());
        } else
        {
            mProgram.setHostValue(mVarName, obj);
            return;
        }
    }

    private Program mProgram;
    private String mVarName;
}
