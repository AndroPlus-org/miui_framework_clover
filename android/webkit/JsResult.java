// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.webkit;


public class JsResult
{
    public static interface ResultReceiver
    {

        public abstract void onJsResultComplete(JsResult jsresult);
    }


    public JsResult(ResultReceiver resultreceiver)
    {
        mReceiver = resultreceiver;
    }

    private final void wakeUp()
    {
        mReceiver.onJsResultComplete(this);
    }

    public final void cancel()
    {
        mResult = false;
        wakeUp();
    }

    public final void confirm()
    {
        mResult = true;
        wakeUp();
    }

    public final boolean getResult()
    {
        return mResult;
    }

    private final ResultReceiver mReceiver;
    private boolean mResult;
}
