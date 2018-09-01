// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.lowpan;


// Referenced classes of package android.net.lowpan:
//            JoinFailedException

public class JoinFailedAtScanException extends JoinFailedException
{

    public JoinFailedAtScanException()
    {
    }

    public JoinFailedAtScanException(Exception exception)
    {
        super(exception);
    }

    public JoinFailedAtScanException(String s)
    {
        super(s);
    }

    public JoinFailedAtScanException(String s, Throwable throwable)
    {
        super(s, throwable);
    }
}
