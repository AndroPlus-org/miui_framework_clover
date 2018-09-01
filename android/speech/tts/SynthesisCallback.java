// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;


public interface SynthesisCallback
{

    public abstract int audioAvailable(byte abyte0[], int i, int j);

    public abstract int done();

    public abstract void error();

    public abstract void error(int i);

    public abstract int getMaxBufferSize();

    public abstract boolean hasFinished();

    public abstract boolean hasStarted();

    public void rangeStart(int i, int j, int k)
    {
    }

    public abstract int start(int i, int j, int k);
}
