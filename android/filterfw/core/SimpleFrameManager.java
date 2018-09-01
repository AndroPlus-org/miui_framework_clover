// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            FrameManager, FrameFormat, SimpleFrame, NativeFrame, 
//            GLFrame, VertexFrame, Frame

public class SimpleFrameManager extends FrameManager
{

    public SimpleFrameManager()
    {
    }

    private Frame createNewFrame(FrameFormat frameformat)
    {
        frameformat.getTarget();
        JVM INSTR tableswitch 1 4: default 36
    //                   1 74
    //                   2 86
    //                   3 99
    //                   4 120;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new RuntimeException((new StringBuilder()).append("Unsupported frame target type: ").append(FrameFormat.targetToString(frameformat.getTarget())).append("!").toString());
_L2:
        frameformat = new SimpleFrame(frameformat, this);
_L7:
        return frameformat;
_L3:
        frameformat = new NativeFrame(frameformat, this);
        continue; /* Loop/switch isn't completed */
_L4:
        frameformat = new GLFrame(frameformat, this);
        frameformat.init(getGLEnvironment());
        continue; /* Loop/switch isn't completed */
_L5:
        frameformat = new VertexFrame(frameformat, this);
        if(true) goto _L7; else goto _L6
_L6:
    }

    public Frame newBoundFrame(FrameFormat frameformat, int i, long l)
    {
        switch(frameformat.getTarget())
        {
        default:
            throw new RuntimeException((new StringBuilder()).append("Attached frames are not supported for target type: ").append(FrameFormat.targetToString(frameformat.getTarget())).append("!").toString());

        case 3: // '\003'
            frameformat = new GLFrame(frameformat, this, i, l);
            break;
        }
        frameformat.init(getGLEnvironment());
        return frameformat;
    }

    public Frame newFrame(FrameFormat frameformat)
    {
        return createNewFrame(frameformat);
    }

    public Frame releaseFrame(Frame frame)
    {
        int i = frame.decRefCount();
        if(i == 0 && frame.hasNativeAllocation())
        {
            frame.releaseNativeAllocation();
            return null;
        }
        if(i < 0)
            throw new RuntimeException("Frame reference count dropped below 0!");
        else
            return frame;
    }

    public Frame retainFrame(Frame frame)
    {
        frame.incRefCount();
        return frame;
    }
}
