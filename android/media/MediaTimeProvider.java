// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


public interface MediaTimeProvider
{
    public static interface OnMediaTimeListener
    {

        public abstract void onSeek(long l);

        public abstract void onStop();

        public abstract void onTimedEvent(long l);
    }


    public abstract void cancelNotifications(OnMediaTimeListener onmediatimelistener);

    public abstract long getCurrentTimeUs(boolean flag, boolean flag1)
        throws IllegalStateException;

    public abstract void notifyAt(long l, OnMediaTimeListener onmediatimelistener);

    public abstract void scheduleUpdate(OnMediaTimeListener onmediatimelistener);

    public static final long NO_TIME = -1L;
}
