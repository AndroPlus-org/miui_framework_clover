// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import java.util.concurrent.*;

// Referenced classes of package android.os:
//            ResultReceiver, Handler, Bundle

public class SynchronousResultReceiver extends ResultReceiver
{
    public static class Result
    {

        public Bundle bundle;
        public int resultCode;

        public Result(int i, Bundle bundle1)
        {
            resultCode = i;
            bundle = bundle1;
        }
    }


    public SynchronousResultReceiver()
    {
        super((Handler)null);
        mFuture = new CompletableFuture();
        mName = null;
    }

    public SynchronousResultReceiver(String s)
    {
        super((Handler)null);
        mFuture = new CompletableFuture();
        mName = s;
    }

    public Result awaitResult(long l)
        throws TimeoutException
    {
        long l1 = System.currentTimeMillis();
        long l2 = l;
        do
        {
            if(l2 >= 0L)
            {
                Result result;
                try
                {
                    result = (Result)mFuture.get(l2, TimeUnit.MILLISECONDS);
                }
                catch(ExecutionException executionexception)
                {
                    throw new AssertionError("Error receiving response", executionexception);
                }
                catch(InterruptedException interruptedexception)
                {
                    l2 -= (l1 + l) - System.currentTimeMillis();
                    continue;
                }
                return result;
            }
            throw new TimeoutException();
        } while(true);
    }

    public String getName()
    {
        return mName;
    }

    protected final void onReceiveResult(int i, Bundle bundle)
    {
        super.onReceiveResult(i, bundle);
        mFuture.complete(new Result(i, bundle));
    }

    private final CompletableFuture mFuture;
    private final String mName;
}
