// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.io.Closeable;
import java.io.IOException;

public abstract class MediaDataSource
    implements Closeable
{

    public MediaDataSource()
    {
    }

    public abstract long getSize()
        throws IOException;

    public abstract int readAt(long l, byte abyte0[], int i, int j)
        throws IOException;
}
