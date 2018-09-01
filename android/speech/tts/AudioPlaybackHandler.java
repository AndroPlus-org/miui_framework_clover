// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package android.speech.tts:
//            PlaybackQueueItem

class AudioPlaybackHandler
{
    private final class MessageLoop
        implements Runnable
    {

        public void run()
        {
            do
            {
                PlaybackQueueItem playbackqueueitem;
                try
                {
                    playbackqueueitem = (PlaybackQueueItem)AudioPlaybackHandler._2D_get0(AudioPlaybackHandler.this).take();
                }
                catch(InterruptedException interruptedexception)
                {
                    return;
                }
                AudioPlaybackHandler._2D_set0(AudioPlaybackHandler.this, playbackqueueitem);
                playbackqueueitem.run();
                AudioPlaybackHandler._2D_set0(AudioPlaybackHandler.this, null);
            } while(true);
        }

        final AudioPlaybackHandler this$0;

        private MessageLoop()
        {
            this$0 = AudioPlaybackHandler.this;
            super();
        }

        MessageLoop(MessageLoop messageloop)
        {
            this();
        }
    }


    static LinkedBlockingQueue _2D_get0(AudioPlaybackHandler audioplaybackhandler)
    {
        return audioplaybackhandler.mQueue;
    }

    static PlaybackQueueItem _2D_set0(AudioPlaybackHandler audioplaybackhandler, PlaybackQueueItem playbackqueueitem)
    {
        audioplaybackhandler.mCurrentWorkItem = playbackqueueitem;
        return playbackqueueitem;
    }

    AudioPlaybackHandler()
    {
        mCurrentWorkItem = null;
    }

    private void removeAllMessages()
    {
        mQueue.clear();
    }

    private void removeWorkItemsFor(Object obj)
    {
        Iterator iterator = mQueue.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            if(((PlaybackQueueItem)iterator.next()).getCallerIdentity() == obj)
                iterator.remove();
        } while(true);
    }

    private void stop(PlaybackQueueItem playbackqueueitem)
    {
        if(playbackqueueitem == null)
        {
            return;
        } else
        {
            playbackqueueitem.stop(-2);
            return;
        }
    }

    public void enqueue(PlaybackQueueItem playbackqueueitem)
    {
        mQueue.put(playbackqueueitem);
_L2:
        return;
        playbackqueueitem;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean isSpeaking()
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mQueue.peek() == null)
            if(mCurrentWorkItem != null)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public void quit()
    {
        removeAllMessages();
        stop(mCurrentWorkItem);
        mHandlerThread.interrupt();
    }

    public void start()
    {
        mHandlerThread.start();
    }

    public void stop()
    {
        removeAllMessages();
        stop(mCurrentWorkItem);
    }

    public void stopForApp(Object obj)
    {
        removeWorkItemsFor(obj);
        PlaybackQueueItem playbackqueueitem = mCurrentWorkItem;
        if(playbackqueueitem != null && playbackqueueitem.getCallerIdentity() == obj)
            stop(playbackqueueitem);
    }

    private static final boolean DBG = false;
    private static final String TAG = "TTS.AudioPlaybackHandler";
    private volatile PlaybackQueueItem mCurrentWorkItem;
    private final Thread mHandlerThread = new Thread(new MessageLoop(null), "TTS.AudioPlaybackThread");
    private final LinkedBlockingQueue mQueue = new LinkedBlockingQueue();
}
