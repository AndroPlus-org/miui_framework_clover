// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;


// Referenced classes of package android.speech.tts:
//            SynthesisCallback

abstract class AbstractSynthesisCallback
    implements SynthesisCallback
{

    AbstractSynthesisCallback(boolean flag)
    {
        mClientIsUsingV2 = flag;
    }

    int errorCodeOnStop()
    {
        byte byte0;
        if(mClientIsUsingV2)
            byte0 = -2;
        else
            byte0 = -1;
        return byte0;
    }

    abstract void stop();

    protected final boolean mClientIsUsingV2;
}
