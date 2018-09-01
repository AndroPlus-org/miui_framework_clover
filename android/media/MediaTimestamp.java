// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


public final class MediaTimestamp
{

    MediaTimestamp()
    {
        mediaTimeUs = 0L;
        nanoTime = 0L;
        clockRate = 1.0F;
    }

    MediaTimestamp(long l, long l1, float f)
    {
        mediaTimeUs = l;
        nanoTime = l1;
        clockRate = f;
    }

    public long getAnchorMediaTimeUs()
    {
        return mediaTimeUs;
    }

    public long getAnchorSytemNanoTime()
    {
        return nanoTime;
    }

    public float getMediaClockRate()
    {
        return clockRate;
    }

    public final float clockRate;
    public final long mediaTimeUs;
    public final long nanoTime;
}
