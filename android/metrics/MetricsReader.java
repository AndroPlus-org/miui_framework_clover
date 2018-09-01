// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.metrics;

import android.util.EventLog;
import com.android.internal.logging.MetricsLogger;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

// Referenced classes of package android.metrics:
//            LogMaker

public class MetricsReader
{
    public static class Event
    {

        public Object getData()
        {
            return mData;
        }

        public int getProcessId()
        {
            return mPid;
        }

        public long getTimeMillis()
        {
            return mTimeMillis;
        }

        public int getUid()
        {
            return mUid;
        }

        public void setData(Object obj)
        {
            mData = obj;
        }

        Object mData;
        int mPid;
        long mTimeMillis;
        int mUid;

        public Event(long l, int i, int j, Object obj)
        {
            mTimeMillis = l;
            mPid = i;
            mUid = j;
            mData = obj;
        }

        Event(android.util.EventLog.Event event)
        {
            mTimeMillis = TimeUnit.MILLISECONDS.convert(event.getTimeNanos(), TimeUnit.NANOSECONDS);
            mPid = event.getProcessId();
            mUid = event.getUid();
            mData = event.getData();
        }
    }

    public static class LogReader
    {

        public void readEvents(int ai[], long l, Collection collection)
            throws IOException
        {
            ArrayList arraylist = new ArrayList();
            EventLog.readEventsOnWrapping(ai, TimeUnit.NANOSECONDS.convert(l, TimeUnit.MILLISECONDS), arraylist);
            for(ai = arraylist.iterator(); ai.hasNext(); collection.add(new Event((android.util.EventLog.Event)ai.next())));
        }

        public void writeCheckpoint(int i)
        {
            (new MetricsLogger()).action(920, i);
        }

        public LogReader()
        {
        }
    }


    public MetricsReader()
    {
        mPendingQueue = new LinkedList();
        mSeenQueue = new LinkedList();
        mReader = new LogReader();
        mCheckpointTag = -1;
    }

    public void checkpoint()
    {
        mCheckpointTag = (int)(System.currentTimeMillis() % 0x7fffffffL);
        mReader.writeCheckpoint(mCheckpointTag);
        mPendingQueue.clear();
        mSeenQueue.clear();
    }

    public boolean hasNext()
    {
        return mPendingQueue.isEmpty() ^ true;
    }

    public LogMaker next()
    {
        LogMaker logmaker = (LogMaker)mPendingQueue.poll();
        if(logmaker != null)
            mSeenQueue.offer(logmaker);
        return logmaker;
    }

    public void read(long l)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator;
        try
        {
            mReader.readEvents(LOGTAGS, l, arraylist);
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        mPendingQueue.clear();
        mSeenQueue.clear();
        iterator = arraylist.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Event event = (Event)iterator.next();
            l = event.getTimeMillis();
            Object obj1 = event.getData();
            Object obj;
            if(obj1 instanceof Object[])
            {
                obj = ((Object) ((Object[])obj1));
            } else
            {
                obj = ((Object) (new Object[1]));
                obj[0] = obj1;
            }
            obj = (new LogMaker(((Object []) (obj)))).setTimestamp(l).setUid(event.getUid()).setProcessId(event.getProcessId());
            if(((LogMaker) (obj)).getCategory() == 920)
            {
                if(((LogMaker) (obj)).getSubtype() == mCheckpointTag)
                    mPendingQueue.clear();
            } else
            {
                mPendingQueue.offer(obj);
            }
        } while(true);
    }

    public void reset()
    {
        mSeenQueue.addAll(mPendingQueue);
        mPendingQueue.clear();
        mCheckpointTag = -1;
        Queue queue = mPendingQueue;
        mPendingQueue = mSeenQueue;
        mSeenQueue = queue;
    }

    public void setLogReader(LogReader logreader)
    {
        mReader = logreader;
    }

    private int LOGTAGS[] = {
        0x80004
    };
    private int mCheckpointTag;
    private Queue mPendingQueue;
    private LogReader mReader;
    private Queue mSeenQueue;
}
