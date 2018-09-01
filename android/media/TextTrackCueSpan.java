// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


class TextTrackCueSpan
{

    TextTrackCueSpan(String s, long l)
    {
        mTimestampMs = l;
        mText = s;
        boolean flag;
        if(mTimestampMs < 0L)
            flag = true;
        else
            flag = false;
        mEnabled = flag;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(!(obj instanceof TextTrackCueSpan))
            return false;
        obj = (TextTrackCueSpan)obj;
        if(mTimestampMs == ((TextTrackCueSpan) (obj)).mTimestampMs)
            flag = mText.equals(((TextTrackCueSpan) (obj)).mText);
        return flag;
    }

    boolean mEnabled;
    String mText;
    long mTimestampMs;
}
