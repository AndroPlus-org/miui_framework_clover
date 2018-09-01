// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.filterpacks.videosink;


public class MediaRecorderStopException extends RuntimeException
{

    public MediaRecorderStopException()
    {
    }

    public MediaRecorderStopException(String s)
    {
        super(s);
    }

    public MediaRecorderStopException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public MediaRecorderStopException(Throwable throwable)
    {
        super(throwable);
    }

    private static final String TAG = "MediaRecorderStopException";
}
