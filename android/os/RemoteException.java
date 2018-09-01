// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.util.AndroidException;

// Referenced classes of package android.os:
//            DeadObjectException, DeadSystemException

public class RemoteException extends AndroidException
{

    public RemoteException()
    {
    }

    public RemoteException(String s)
    {
        super(s);
    }

    public RuntimeException rethrowAsRuntimeException()
    {
        throw new RuntimeException(this);
    }

    public RuntimeException rethrowFromSystemServer()
    {
        if(this instanceof DeadObjectException)
            throw new RuntimeException(new DeadSystemException());
        else
            throw new RuntimeException(this);
    }
}
