// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public abstract class SimpleImageFilter extends Filter
{

    public SimpleImageFilter(String s, String s1)
    {
        super(s);
        mCurrentTarget = 0;
        mParameterName = s1;
    }

    protected abstract Program getNativeProgram(FilterContext filtercontext);

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    protected abstract Program getShaderProgram(FilterContext filtercontext);

    public void process(FilterContext filtercontext)
    {
        Frame frame = pullInput("image");
        FrameFormat frameformat = frame.getFormat();
        Frame frame1 = filtercontext.getFrameManager().newFrame(frameformat);
        updateProgramWithTarget(frameformat.getTarget(), filtercontext);
        mProgram.process(frame, frame1);
        pushOutput("image", frame1);
        frame1.release();
    }

    public void setupPorts()
    {
        if(mParameterName != null)
            try
            {
                java.lang.reflect.Field field = android/filterpacks/imageproc/SimpleImageFilter.getDeclaredField("mProgram");
                addProgramPort(mParameterName, mParameterName, field, Float.TYPE, false);
            }
            catch(NoSuchFieldException nosuchfieldexception)
            {
                throw new RuntimeException("Internal Error: mProgram field not found!");
            }
        addMaskedInputPort("image", ImageFormat.create(3));
        addOutputBasedOnInput("image", "image");
    }

    protected void updateProgramWithTarget(int i, FilterContext filtercontext)
    {
        if(i == mCurrentTarget)
            break MISSING_BLOCK_LABEL_114;
        i;
        JVM INSTR tableswitch 2 3: default 32
    //                   2 76
    //                   3 88;
           goto _L1 _L2 _L3
_L1:
        mProgram = null;
_L5:
        if(mProgram == null)
            throw new RuntimeException((new StringBuilder()).append("Could not create a program for image filter ").append(this).append("!").toString());
        break; /* Loop/switch isn't completed */
_L2:
        mProgram = getNativeProgram(filtercontext);
        continue; /* Loop/switch isn't completed */
_L3:
        mProgram = getShaderProgram(filtercontext);
        if(true) goto _L5; else goto _L4
_L4:
        initProgramInputs(mProgram, filtercontext);
        mCurrentTarget = i;
    }

    protected int mCurrentTarget;
    protected String mParameterName;
    protected Program mProgram;
}
