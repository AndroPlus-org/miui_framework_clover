// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import android.filterfw.format.ObjectFormat;
import android.graphics.Bitmap;
import java.nio.ByteBuffer;

// Referenced classes of package android.filterfw.core:
//            Frame, FrameFormat, MutableFrameFormat, FrameManager

public class SimpleFrame extends Frame
{

    SimpleFrame(FrameFormat frameformat, FrameManager framemanager)
    {
        super(frameformat, framemanager);
        initWithFormat(frameformat);
        setReusable(false);
    }

    private void initWithFormat(FrameFormat frameformat)
    {
        int i = frameformat.getLength();
        frameformat.getBaseType();
        JVM INSTR tableswitch 2 6: default 44
    //                   2 50
    //                   3 60
    //                   4 70
    //                   5 80
    //                   6 90;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        mObject = null;
_L8:
        return;
_L2:
        mObject = new byte[i];
        continue; /* Loop/switch isn't completed */
_L3:
        mObject = new short[i];
        continue; /* Loop/switch isn't completed */
_L4:
        mObject = new int[i];
        continue; /* Loop/switch isn't completed */
_L5:
        mObject = new float[i];
        continue; /* Loop/switch isn't completed */
_L6:
        mObject = new double[i];
        if(true) goto _L8; else goto _L7
_L7:
    }

    private void setFormatObjectClass(Class class1)
    {
        MutableFrameFormat mutableframeformat = getFormat().mutableCopy();
        mutableframeformat.setObjectClass(class1);
        setFormat(mutableframeformat);
    }

    static SimpleFrame wrapObject(Object obj, FrameManager framemanager)
    {
        framemanager = new SimpleFrame(ObjectFormat.fromObject(obj, 1), framemanager);
        framemanager.setObjectValue(obj);
        return framemanager;
    }

    public Bitmap getBitmap()
    {
        Bitmap bitmap;
        if(mObject instanceof Bitmap)
            bitmap = (Bitmap)mObject;
        else
            bitmap = null;
        return bitmap;
    }

    public ByteBuffer getData()
    {
        ByteBuffer bytebuffer;
        if(mObject instanceof ByteBuffer)
            bytebuffer = (ByteBuffer)mObject;
        else
            bytebuffer = null;
        return bytebuffer;
    }

    public float[] getFloats()
    {
        float af[];
        if(mObject instanceof float[])
            af = (float[])mObject;
        else
            af = null;
        return af;
    }

    public int[] getInts()
    {
        int ai[];
        if(mObject instanceof int[])
            ai = (int[])mObject;
        else
            ai = null;
        return ai;
    }

    public Object getObjectValue()
    {
        return mObject;
    }

    protected boolean hasNativeAllocation()
    {
        return false;
    }

    protected void releaseNativeAllocation()
    {
    }

    public void setBitmap(Bitmap bitmap)
    {
        assertFrameMutable();
        setGenericObjectValue(bitmap);
    }

    public void setData(ByteBuffer bytebuffer, int i, int j)
    {
        assertFrameMutable();
        setGenericObjectValue(ByteBuffer.wrap(bytebuffer.array(), i, j));
    }

    public void setFloats(float af[])
    {
        assertFrameMutable();
        setGenericObjectValue(af);
    }

    protected void setGenericObjectValue(Object obj)
    {
        FrameFormat frameformat = getFormat();
        if(frameformat.getObjectClass() == null)
            setFormatObjectClass(obj.getClass());
        else
        if(!frameformat.getObjectClass().isAssignableFrom(obj.getClass()))
            throw new RuntimeException((new StringBuilder()).append("Attempting to set object value of type '").append(obj.getClass()).append("' on ").append("SimpleFrame of type '").append(frameformat.getObjectClass()).append("'!").toString());
        mObject = obj;
    }

    public void setInts(int ai[])
    {
        assertFrameMutable();
        setGenericObjectValue(ai);
    }

    public String toString()
    {
        return (new StringBuilder()).append("SimpleFrame (").append(getFormat()).append(")").toString();
    }

    private Object mObject;
}
