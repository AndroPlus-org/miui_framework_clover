// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;


class TtmlCue extends SubtitleTrack.Cue
{

    public TtmlCue(long l, long l1, String s, String s1)
    {
        mStartTimeMs = l;
        mEndTimeMs = l1;
        mText = s;
        mTtmlFragment = s1;
    }

    public String mText;
    public String mTtmlFragment;
}
