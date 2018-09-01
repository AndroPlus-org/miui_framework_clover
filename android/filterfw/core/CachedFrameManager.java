// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterfw.core;

import java.util.*;

// Referenced classes of package android.filterfw.core:
//            SimpleFrameManager, Frame, FrameFormat

public class CachedFrameManager extends SimpleFrameManager
{

    public CachedFrameManager()
    {
        mStorageCapacity = 0x1800000;
        mStorageSize = 0;
        mTimeStamp = 0;
        mAvailableFrames = new TreeMap();
    }

    private void dropOldestFrame()
    {
        int i = ((Integer)mAvailableFrames.firstKey()).intValue();
        Frame frame = (Frame)mAvailableFrames.get(Integer.valueOf(i));
        mStorageSize = mStorageSize - frame.getFormat().getSize();
        frame.releaseNativeAllocation();
        mAvailableFrames.remove(Integer.valueOf(i));
    }

    private Frame findAvailableFrame(FrameFormat frameformat, int i, long l)
    {
        SortedMap sortedmap = mAvailableFrames;
        sortedmap;
        JVM INSTR monitorenter ;
        Iterator iterator = mAvailableFrames.entrySet().iterator();
_L2:
        java.util.Map.Entry entry;
        Frame frame;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_148;
            entry = (java.util.Map.Entry)iterator.next();
            frame = (Frame)entry.getValue();
        } while(!frame.getFormat().isReplaceableBy(frameformat) || i != frame.getBindingType());
        if(i == 0)
            break; /* Loop/switch isn't completed */
        if(l != frame.getBindingId()) goto _L2; else goto _L1
_L1:
        super.retainFrame(frame);
        mAvailableFrames.remove(entry.getKey());
        frame.onFrameFetch();
        frame.reset(frameformat);
        mStorageSize = mStorageSize - frameformat.getSize();
        sortedmap;
        JVM INSTR monitorexit ;
        return frame;
        sortedmap;
        JVM INSTR monitorexit ;
        return null;
        frameformat;
        throw frameformat;
    }

    private boolean storeFrame(Frame frame)
    {
        SortedMap sortedmap = mAvailableFrames;
        sortedmap;
        JVM INSTR monitorenter ;
        int i;
        int j;
        i = frame.getFormat().getSize();
        j = mStorageCapacity;
        if(i <= j)
            break MISSING_BLOCK_LABEL_31;
        sortedmap;
        JVM INSTR monitorexit ;
        return false;
        for(j = mStorageSize + i; j > mStorageCapacity; j = mStorageSize + i)
            dropOldestFrame();

        frame.onFrameStore();
        mStorageSize = j;
        mAvailableFrames.put(Integer.valueOf(mTimeStamp), frame);
        mTimeStamp = mTimeStamp + 1;
        sortedmap;
        JVM INSTR monitorexit ;
        return true;
        frame;
        throw frame;
    }

    public void clearCache()
    {
        for(Iterator iterator = mAvailableFrames.values().iterator(); iterator.hasNext(); ((Frame)iterator.next()).releaseNativeAllocation());
        mAvailableFrames.clear();
    }

    public Frame newBoundFrame(FrameFormat frameformat, int i, long l)
    {
        Frame frame = findAvailableFrame(frameformat, i, l);
        Frame frame1 = frame;
        if(frame == null)
            frame1 = super.newBoundFrame(frameformat, i, l);
        frame1.setTimestamp(-2L);
        return frame1;
    }

    public Frame newFrame(FrameFormat frameformat)
    {
        Frame frame = findAvailableFrame(frameformat, 0, 0L);
        Frame frame1 = frame;
        if(frame == null)
            frame1 = super.newFrame(frameformat);
        frame1.setTimestamp(-2L);
        return frame1;
    }

    public Frame releaseFrame(Frame frame)
    {
        if(frame.isReusable())
        {
            int i = frame.decRefCount();
            if(i == 0 && frame.hasNativeAllocation())
            {
                if(!storeFrame(frame))
                    frame.releaseNativeAllocation();
                return null;
            }
            if(i < 0)
                throw new RuntimeException("Frame reference count dropped below 0!");
        } else
        {
            super.releaseFrame(frame);
        }
        return frame;
    }

    public Frame retainFrame(Frame frame)
    {
        return super.retainFrame(frame);
    }

    public void tearDown()
    {
        clearCache();
    }

    private SortedMap mAvailableFrames;
    private int mStorageCapacity;
    private int mStorageSize;
    private int mTimeStamp;
}
