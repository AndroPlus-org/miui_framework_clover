// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;


public abstract class UtteranceProgressListener
{

    public UtteranceProgressListener()
    {
    }

    static UtteranceProgressListener from(TextToSpeech.OnUtteranceCompletedListener onutterancecompletedlistener)
    {
        return new UtteranceProgressListener(onutterancecompletedlistener) {

            public void onDone(String s)
            {
                this;
                JVM INSTR monitorenter ;
                listener.onUtteranceCompleted(s);
                this;
                JVM INSTR monitorexit ;
                return;
                s;
                throw s;
            }

            public void onError(String s)
            {
                listener.onUtteranceCompleted(s);
            }

            public void onStart(String s)
            {
            }

            public void onStop(String s, boolean flag)
            {
                listener.onUtteranceCompleted(s);
            }

            final TextToSpeech.OnUtteranceCompletedListener val$listener;

            
            {
                listener = onutterancecompletedlistener;
                super();
            }
        }
;
    }

    public void onAudioAvailable(String s, byte abyte0[])
    {
    }

    public void onBeginSynthesis(String s, int i, int j, int k)
    {
    }

    public abstract void onDone(String s);

    public abstract void onError(String s);

    public void onError(String s, int i)
    {
        onError(s);
    }

    public void onRangeStart(String s, int i, int j, int k)
    {
        onUtteranceRangeStart(s, i, j);
    }

    public abstract void onStart(String s);

    public void onStop(String s, boolean flag)
    {
    }

    public void onUtteranceRangeStart(String s, int i, int j)
    {
    }
}
