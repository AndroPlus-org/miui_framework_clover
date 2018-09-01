// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.util.Arrays;

// Referenced classes of package android.filterfw.core:
//            FrameFormat, KeyValueMap

public class MutableFrameFormat extends FrameFormat
{

    public MutableFrameFormat()
    {
    }

    public MutableFrameFormat(int i, int j)
    {
        super(i, j);
    }

    public void setBaseType(int i)
    {
        mBaseType = i;
        mBytesPerSample = bytesPerSampleOf(i);
    }

    public void setBytesPerSample(int i)
    {
        mBytesPerSample = i;
        mSize = -1;
    }

    public void setDimensionCount(int i)
    {
        mDimensions = new int[i];
    }

    public void setDimensions(int i)
    {
        mDimensions = (new int[] {
            i
        });
        mSize = -1;
    }

    public void setDimensions(int i, int j)
    {
        mDimensions = (new int[] {
            i, j
        });
        mSize = -1;
    }

    public void setDimensions(int i, int j, int k)
    {
        mDimensions = (new int[] {
            i, j, k
        });
        mSize = -1;
    }

    public void setDimensions(int ai[])
    {
        Object obj = null;
        if(ai == null)
            ai = obj;
        else
            ai = Arrays.copyOf(ai, ai.length);
        mDimensions = ai;
        mSize = -1;
    }

    public void setMetaValue(String s, Object obj)
    {
        if(mMetaData == null)
            mMetaData = new KeyValueMap();
        mMetaData.put(s, obj);
    }

    public void setObjectClass(Class class1)
    {
        mObjectClass = class1;
    }

    public void setTarget(int i)
    {
        mTarget = i;
    }
}
