// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.midi;

import android.media.midi.MidiReceiver;
import android.media.midi.MidiSender;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public final class MidiDispatcher extends MidiReceiver
{
    public static interface MidiReceiverFailureHandler
    {

        public abstract void onReceiverFailure(MidiReceiver midireceiver, IOException ioexception);
    }


    static CopyOnWriteArrayList _2D_get0(MidiDispatcher mididispatcher)
    {
        return mididispatcher.mReceivers;
    }

    public MidiDispatcher()
    {
        this(null);
    }

    public MidiDispatcher(MidiReceiverFailureHandler midireceiverfailurehandler)
    {
        mReceivers = new CopyOnWriteArrayList();
        mSender = new MidiSender() {

            public void onConnect(MidiReceiver midireceiver)
            {
                MidiDispatcher._2D_get0(MidiDispatcher.this).add(midireceiver);
            }

            public void onDisconnect(MidiReceiver midireceiver)
            {
                MidiDispatcher._2D_get0(MidiDispatcher.this).remove(midireceiver);
            }

            final MidiDispatcher this$0;

            
            {
                this$0 = MidiDispatcher.this;
                super();
            }
        }
;
        mFailureHandler = midireceiverfailurehandler;
    }

    public int getReceiverCount()
    {
        return mReceivers.size();
    }

    public MidiSender getSender()
    {
        return mSender;
    }

    public void onFlush()
        throws IOException
    {
        Iterator iterator = mReceivers.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            MidiReceiver midireceiver = (MidiReceiver)iterator.next();
            try
            {
                midireceiver.flush();
            }
            catch(IOException ioexception)
            {
                mReceivers.remove(midireceiver);
                if(mFailureHandler != null)
                    mFailureHandler.onReceiverFailure(midireceiver, ioexception);
            }
        } while(true);
    }

    public void onSend(byte abyte0[], int i, int j, long l)
        throws IOException
    {
        Iterator iterator = mReceivers.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            MidiReceiver midireceiver = (MidiReceiver)iterator.next();
            try
            {
                midireceiver.send(abyte0, i, j, l);
            }
            catch(IOException ioexception)
            {
                mReceivers.remove(midireceiver);
                if(mFailureHandler != null)
                    mFailureHandler.onReceiverFailure(midireceiver, ioexception);
            }
        } while(true);
    }

    private final MidiReceiverFailureHandler mFailureHandler;
    private final CopyOnWriteArrayList mReceivers;
    private final MidiSender mSender;
}
