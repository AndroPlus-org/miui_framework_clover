// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;


// Referenced classes of package android.filterfw.core:
//            Frame

public abstract class Program
{

    public Program()
    {
    }

    public abstract Object getHostValue(String s);

    public void process(Frame frame, Frame frame1)
    {
        process(new Frame[] {
            frame
        }, frame1);
    }

    public abstract void process(Frame aframe[], Frame frame);

    public void reset()
    {
    }

    public abstract void setHostValue(String s, Object obj);
}
