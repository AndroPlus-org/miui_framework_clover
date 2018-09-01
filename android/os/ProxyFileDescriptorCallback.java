// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os;

import android.system.ErrnoException;
import android.system.OsConstants;

public abstract class ProxyFileDescriptorCallback
{

    public ProxyFileDescriptorCallback()
    {
    }

    public void onFsync()
        throws ErrnoException
    {
        throw new ErrnoException("onFsync", OsConstants.EINVAL);
    }

    public long onGetSize()
        throws ErrnoException
    {
        throw new ErrnoException("onGetSize", OsConstants.EBADF);
    }

    public int onRead(long l, int i, byte abyte0[])
        throws ErrnoException
    {
        throw new ErrnoException("onRead", OsConstants.EBADF);
    }

    public abstract void onRelease();

    public int onWrite(long l, int i, byte abyte0[])
        throws ErrnoException
    {
        throw new ErrnoException("onWrite", OsConstants.EBADF);
    }
}
