// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;


// Referenced classes of package android.os:
//            RemoteException

public class DeadObjectException extends RemoteException
{

    public DeadObjectException()
    {
    }

    public DeadObjectException(String s)
    {
        super(s);
    }
}
