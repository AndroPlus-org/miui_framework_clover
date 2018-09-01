// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;


// Referenced classes of package android.net.lowpan:
//            LowpanException

public class WrongStateException extends LowpanException
{

    public WrongStateException()
    {
    }

    protected WrongStateException(Exception exception)
    {
        super(exception);
    }

    public WrongStateException(String s)
    {
        super(s);
    }

    public WrongStateException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
