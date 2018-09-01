// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.imageproc;

import android.filterfw.core.*;
import android.filterfw.format.ImageFormat;

public abstract class ImageCombineFilter extends Filter
{

    public ImageCombineFilter(String s, String as[], String s1, String s2)
    {
        super(s);
        mCurrentTarget = 0;
        mInputNames = as;
        mOutputName = s1;
        mParameterName = s2;
    }

    private void assertAllInputTargetsMatch()
    {
        int i = 0;
        int j = getInputFormat(mInputNames[0]).getTarget();
        String as[] = mInputNames;
        for(int k = as.length; i < k; i++)
            if(j != getInputFormat(as[i]).getTarget())
                throw new RuntimeException((new StringBuilder()).append("Type mismatch of input formats in filter ").append(this).append(". All input frames must have the same target!").toString());

    }

    protected abstract Program getNativeProgram(FilterContext filtercontext);

    public FrameFormat getOutputFormat(String s, FrameFormat frameformat)
    {
        return frameformat;
    }

    protected abstract Program getShaderProgram(FilterContext filtercontext);

    public void process(FilterContext filtercontext)
    {
        Frame aframe[] = new Frame[mInputNames.length];
        String as[] = mInputNames;
        int i = as.length;
        int j = 0;
        for(int k = 0; j < i; k++)
        {
            aframe[k] = pullInput(as[j]);
            j++;
        }

        Frame frame = filtercontext.getFrameManager().newFrame(aframe[0].getFormat());
        updateProgramWithTarget(aframe[0].getFormat().getTarget(), filtercontext);
        mProgram.process(aframe, frame);
        pushOutput(mOutputName, frame);
        frame.release();
    }

    public void setupPorts()
    {
        String as[];
        int i;
        if(mParameterName != null)
            try
            {
                java.lang.reflect.Field field = android/filterpacks/imageproc/ImageCombineFilter.getDeclaredField("mProgram");
                addProgramPort(mParameterName, mParameterName, field, Float.TYPE, false);
            }
            catch(NoSuchFieldException nosuchfieldexception)
            {
                throw new RuntimeException("Internal Error: mProgram field not found!");
            }
        as = mInputNames;
        i = as.length;
        for(int j = 0; j < i; j++)
            addMaskedInputPort(as[j], ImageFormat.create(3));

        addOutputBasedOnInput(mOutputName, mInputNames[0]);
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
    protected String mInputNames[];
    protected String mOutputName;
    protected String mParameterName;
    protected Program mProgram;
}
