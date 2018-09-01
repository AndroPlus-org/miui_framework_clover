// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.accounts;


// Referenced classes of package android.accounts:
//            AccountsException

public class OperationCanceledException extends AccountsException
{

    public OperationCanceledException()
    {
    }

    public OperationCanceledException(String s)
    {
        super(s);
    }

    public OperationCanceledException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public OperationCanceledException(Throwable throwable)
    {
        super(throwable);
    }
}
