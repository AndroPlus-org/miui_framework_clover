// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.util.Vector;

// Referenced classes of package android.media:
//            TextTrackCueSpan

class UnstyledTextExtractor
    implements Tokenizer.OnTokenListener
{

    UnstyledTextExtractor()
    {
        mLine = new StringBuilder();
        mLines = new Vector();
        mCurrentLine = new Vector();
        init();
    }

    private void init()
    {
        mLine.delete(0, mLine.length());
        mLines.clear();
        mCurrentLine.clear();
        mLastTimestamp = -1L;
    }

    public TextTrackCueSpan[][] getText()
    {
        if(mLine.length() > 0 || mCurrentLine.size() > 0)
            onLineEnd();
        TextTrackCueSpan atexttrackcuespan[][] = new TextTrackCueSpan[mLines.size()][];
        mLines.toArray(atexttrackcuespan);
        init();
        return atexttrackcuespan;
    }

    public void onData(String s)
    {
        mLine.append(s);
    }

    public void onEnd(String s)
    {
    }

    public void onLineEnd()
    {
        if(mLine.length() > 0)
        {
            mCurrentLine.add(new TextTrackCueSpan(mLine.toString(), mLastTimestamp));
            mLine.delete(0, mLine.length());
        }
        TextTrackCueSpan atexttrackcuespan[] = new TextTrackCueSpan[mCurrentLine.size()];
        mCurrentLine.toArray(atexttrackcuespan);
        mCurrentLine.clear();
        mLines.add(atexttrackcuespan);
    }

    public void onStart(String s, String as[], String s1)
    {
    }

    public void onTimeStamp(long l)
    {
        if(mLine.length() > 0 && l != mLastTimestamp)
        {
            mCurrentLine.add(new TextTrackCueSpan(mLine.toString(), mLastTimestamp));
            mLine.delete(0, mLine.length());
        }
        mLastTimestamp = l;
    }

    Vector mCurrentLine;
    long mLastTimestamp;
    StringBuilder mLine;
    Vector mLines;
}
