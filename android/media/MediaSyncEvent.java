// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


public class MediaSyncEvent
{

    private MediaSyncEvent(int i)
    {
        mAudioSession = 0;
        mType = i;
    }

    public static MediaSyncEvent createEvent(int i)
        throws IllegalArgumentException
    {
        if(!isValidType(i))
            throw new IllegalArgumentException((new StringBuilder()).append(i).append("is not a valid MediaSyncEvent type.").toString());
        else
            return new MediaSyncEvent(i);
    }

    private static boolean isValidType(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 0: // '\0'
        case 1: // '\001'
            return true;
        }
    }

    public int getAudioSessionId()
    {
        return mAudioSession;
    }

    public int getType()
    {
        return mType;
    }

    public MediaSyncEvent setAudioSessionId(int i)
        throws IllegalArgumentException
    {
        if(i > 0)
        {
            mAudioSession = i;
            return this;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append(i).append(" is not a valid session ID.").toString());
        }
    }

    public static final int SYNC_EVENT_NONE = 0;
    public static final int SYNC_EVENT_PRESENTATION_COMPLETE = 1;
    private int mAudioSession;
    private final int mType;
}
