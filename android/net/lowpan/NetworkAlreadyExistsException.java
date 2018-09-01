// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;


// Referenced classes of package android.net.lowpan:
//            LowpanException

public class NetworkAlreadyExistsException extends LowpanException
{

    public NetworkAlreadyExistsException()
    {
    }

    public NetworkAlreadyExistsException(Exception exception)
    {
        super(exception);
    }

    public NetworkAlreadyExistsException(String s)
    {
        super(s, null);
    }

    public NetworkAlreadyExistsException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
