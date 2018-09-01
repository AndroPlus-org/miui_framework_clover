// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


// Referenced classes of package android.media:
//            MediaCasStateException

public class MediaCasException extends Exception
{
    public static final class DeniedByServerException extends MediaCasException
    {

        public DeniedByServerException(String s)
        {
            super(s, null);
        }
    }

    public static final class NotProvisionedException extends MediaCasException
    {

        public NotProvisionedException(String s)
        {
            super(s, null);
        }
    }

    public static final class ResourceBusyException extends MediaCasException
    {

        public ResourceBusyException(String s)
        {
            super(s, null);
        }
    }

    public static final class UnsupportedCasException extends MediaCasException
    {

        public UnsupportedCasException(String s)
        {
            super(s, null);
        }
    }


    private MediaCasException(String s)
    {
        super(s);
    }

    MediaCasException(String s, MediaCasException mediacasexception)
    {
        this(s);
    }

    static void throwExceptionIfNeeded(int i)
        throws MediaCasException
    {
        if(i == 0)
            return;
        if(i == 7)
            throw new NotProvisionedException(null);
        if(i == 8)
            throw new ResourceBusyException(null);
        if(i == 11)
        {
            throw new DeniedByServerException(null);
        } else
        {
            MediaCasStateException.throwExceptionIfNeeded(i);
            return;
        }
    }
}
