// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.midi;

import android.media.midi.MidiReceiver;
import java.io.IOException;

// Referenced classes of package com.android.internal.midi:
//            EventScheduler

public class MidiEventScheduler extends EventScheduler
{
    public static class MidiEvent extends EventScheduler.SchedulableEvent
    {

        public String toString()
        {
            String s = "Event: ";
            for(int i = 0; i < count; i++)
                s = (new StringBuilder()).append(s).append(data[i]).append(", ").toString();

            return s;
        }

        public int count;
        public byte data[];

        private MidiEvent(int i)
        {
            super(0L);
            count = 0;
            data = new byte[i];
        }

        MidiEvent(int i, MidiEvent midievent)
        {
            this(i);
        }

        private MidiEvent(byte abyte0[], int i, int j, long l)
        {
            super(l);
            count = 0;
            data = new byte[j];
            System.arraycopy(abyte0, i, data, 0, j);
            count = j;
        }

        MidiEvent(byte abyte0[], int i, int j, long l, MidiEvent midievent)
        {
            this(abyte0, i, j, l);
        }
    }

    private class SchedulingReceiver extends MidiReceiver
    {

        public void onFlush()
        {
            flush();
        }

        public void onSend(byte abyte0[], int i, int j, long l)
            throws IOException
        {
            abyte0 = MidiEventScheduler._2D_wrap0(MidiEventScheduler.this, abyte0, i, j, l);
            if(abyte0 != null)
                add(abyte0);
        }

        final MidiEventScheduler this$0;

        private SchedulingReceiver()
        {
            this$0 = MidiEventScheduler.this;
            super();
        }

        SchedulingReceiver(SchedulingReceiver schedulingreceiver)
        {
            this();
        }
    }


    static MidiEvent _2D_wrap0(MidiEventScheduler midieventscheduler, byte abyte0[], int i, int j, long l)
    {
        return midieventscheduler.createScheduledEvent(abyte0, i, j, l);
    }

    public MidiEventScheduler()
    {
        mReceiver = new SchedulingReceiver(null);
    }

    private MidiEvent createScheduledEvent(byte abyte0[], int i, int j, long l)
    {
        MidiEvent midievent;
        if(j > 16)
        {
            midievent = new MidiEvent(abyte0, i, j, l, null);
        } else
        {
            MidiEvent midievent1 = (MidiEvent)removeEventfromPool();
            midievent = midievent1;
            if(midievent1 == null)
                midievent = new MidiEvent(16, null);
            System.arraycopy(abyte0, i, midievent.data, 0, j);
            midievent.count = j;
            midievent.setTimestamp(l);
        }
        return midievent;
    }

    public void addEventToPool(EventScheduler.SchedulableEvent schedulableevent)
    {
        if((schedulableevent instanceof MidiEvent) && ((MidiEvent)schedulableevent).data.length == 16)
            super.addEventToPool(schedulableevent);
    }

    public MidiReceiver getReceiver()
    {
        return mReceiver;
    }

    private static final int POOL_EVENT_SIZE = 16;
    private static final String TAG = "MidiEventScheduler";
    private MidiReceiver mReceiver;
}
