// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.statistics;

import java.lang.reflect.Array;
import java.util.Arrays;

// Referenced classes of package android.os.statistics:
//            PerfEvent

public class FastPerfEventList
{

    public FastPerfEventList(Class class1, int i)
    {
        events = (PerfEvent[])Array.newInstance(class1, i);
    }

    public void add(int i, PerfEvent perfevent)
    {
        if(size == events.length)
            ensureCapacity(size + 1);
        if(i < size)
            System.arraycopy(events, i, events, i + 1, size - i);
        events[i] = perfevent;
        size = size + 1;
    }

    public void add(PerfEvent perfevent)
    {
        if(size == events.length)
            ensureCapacity(size + 1);
        PerfEvent aperfevent[] = events;
        int i = size;
        size = i + 1;
        aperfevent[i] = perfevent;
    }

    public void addAll(FastPerfEventList fastperfeventlist)
    {
        int i = fastperfeventlist.size;
        ensureCapacity(size + i);
        System.arraycopy(fastperfeventlist.events, 0, events, size, i);
        size = size + i;
    }

    public void clear()
    {
        for(int i = 0; i < size; i++)
            events[i] = null;

        size = 0;
    }

    public void compact()
    {
        int i = 0;
        int j = 0;
label0:
        do
        {
            if(i < size && j < size)
            {
                if(events[i] != null)
                {
                    i++;
                    continue;
                }
                int k = j;
                if(j <= i)
                    k = i + 1;
                PerfEvent perfevent = null;
                do
                {
label1:
                    {
                        if(k < size)
                        {
                            perfevent = events[k];
                            if(perfevent == null)
                                break label1;
                        }
                        j = k;
                        if(k < size)
                        {
                            events[i] = perfevent;
                            events[k] = null;
                            j = k + 1;
                            i++;
                        }
                        continue label0;
                    }
                    k++;
                } while(true);
            }
            if(i == size || events[i] == null)
                size = i;
            else
                size = i + 1;
            return;
        } while(true);
    }

    public void ensureCapacity(int i)
    {
        int j = events.length;
        if(i > j)
        {
            int k = j + (j >> 1);
            j = k;
            if(k < i)
                j = i;
            events = (PerfEvent[])Arrays.copyOf(events, j);
        }
    }

    public PerfEvent get(int i)
    {
        return events[i];
    }

    public boolean isEmpty()
    {
        boolean flag = false;
        if(size == 0)
            flag = true;
        return flag;
    }

    public void trimTo(int i)
    {
        int j = i;
        if(i <= size + (size >> 1))
            j = size + (size >> 1);
        if(j >= events.length)
        {
            return;
        } else
        {
            events = (PerfEvent[])Arrays.copyOf(events, j);
            return;
        }
    }

    public PerfEvent events[];
    public int size;
}
