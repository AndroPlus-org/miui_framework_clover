// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            Program, NativeFrame, Frame

public class NativeProgram extends Program
{

    public NativeProgram(String s, String s1)
    {
        mHasInitFunction = false;
        mHasTeardownFunction = false;
        mHasSetValueFunction = false;
        mHasGetValueFunction = false;
        mHasResetFunction = false;
        mTornDown = false;
        allocate();
        s = (new StringBuilder()).append("lib").append(s).append(".so").toString();
        if(!openNativeLibrary(s))
            throw new RuntimeException((new StringBuilder()).append("Could not find native library named '").append(s).append("' ").append("required for native program!").toString());
        String s2 = (new StringBuilder()).append(s1).append("_process").toString();
        if(!bindProcessFunction(s2))
            throw new RuntimeException((new StringBuilder()).append("Could not find native program function name ").append(s2).append(" in library ").append(s).append("! ").append("This function is required!").toString());
        mHasInitFunction = bindInitFunction((new StringBuilder()).append(s1).append("_init").toString());
        mHasTeardownFunction = bindTeardownFunction((new StringBuilder()).append(s1).append("_teardown").toString());
        mHasSetValueFunction = bindSetValueFunction((new StringBuilder()).append(s1).append("_setvalue").toString());
        mHasGetValueFunction = bindGetValueFunction((new StringBuilder()).append(s1).append("_getvalue").toString());
        mHasResetFunction = bindResetFunction((new StringBuilder()).append(s1).append("_reset").toString());
        if(mHasInitFunction && callNativeInit() ^ true)
            throw new RuntimeException("Could not initialize NativeProgram!");
        else
            return;
    }

    private native boolean allocate();

    private native boolean bindGetValueFunction(String s);

    private native boolean bindInitFunction(String s);

    private native boolean bindProcessFunction(String s);

    private native boolean bindResetFunction(String s);

    private native boolean bindSetValueFunction(String s);

    private native boolean bindTeardownFunction(String s);

    private native String callNativeGetValue(String s);

    private native boolean callNativeInit();

    private native boolean callNativeProcess(NativeFrame anativeframe[], NativeFrame nativeframe);

    private native boolean callNativeReset();

    private native boolean callNativeSetValue(String s, String s1);

    private native boolean callNativeTeardown();

    private native boolean deallocate();

    private native boolean nativeInit();

    private native boolean openNativeLibrary(String s);

    protected void finalize()
        throws Throwable
    {
        tearDown();
    }

    public Object getHostValue(String s)
    {
        if(mTornDown)
            throw new RuntimeException("NativeProgram already torn down!");
        if(!mHasGetValueFunction)
            throw new RuntimeException("Attempting to get native variable, but native code does not define native getvalue function!");
        else
            return callNativeGetValue(s);
    }

    public void process(Frame aframe[], Frame frame)
    {
        if(mTornDown)
            throw new RuntimeException("NativeProgram already torn down!");
        NativeFrame anativeframe[] = new NativeFrame[aframe.length];
        for(int i = 0; i < aframe.length;)
            if(aframe[i] == null || (aframe[i] instanceof NativeFrame))
            {
                anativeframe[i] = (NativeFrame)aframe[i];
                i++;
            } else
            {
                throw new RuntimeException((new StringBuilder()).append("NativeProgram got non-native frame as input ").append(i).append("!").toString());
            }

        if(frame == null || (frame instanceof NativeFrame))
        {
            if(!callNativeProcess(anativeframe, (NativeFrame)frame))
                throw new RuntimeException("Calling native process() caused error!");
            else
                return;
        } else
        {
            throw new RuntimeException("NativeProgram got non-native output frame!");
        }
    }

    public void reset()
    {
        if(mHasResetFunction && callNativeReset() ^ true)
            throw new RuntimeException("Could not reset NativeProgram!");
        else
            return;
    }

    public void setHostValue(String s, Object obj)
    {
        if(mTornDown)
            throw new RuntimeException("NativeProgram already torn down!");
        if(!mHasSetValueFunction)
            throw new RuntimeException("Attempting to set native variable, but native code does not define native setvalue function!");
        if(!callNativeSetValue(s, obj.toString()))
            throw new RuntimeException((new StringBuilder()).append("Error setting native value for variable '").append(s).append("'!").toString());
        else
            return;
    }

    public void tearDown()
    {
        if(mTornDown)
            return;
        if(mHasTeardownFunction && callNativeTeardown() ^ true)
        {
            throw new RuntimeException("Could not tear down NativeProgram!");
        } else
        {
            deallocate();
            mTornDown = true;
            return;
        }
    }

    private boolean mHasGetValueFunction;
    private boolean mHasInitFunction;
    private boolean mHasResetFunction;
    private boolean mHasSetValueFunction;
    private boolean mHasTeardownFunction;
    private boolean mTornDown;
    private int nativeProgramId;

    static 
    {
        System.loadLibrary("filterfw");
    }
}
