// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.util.Arrays;

// Referenced classes of package android.media:
//            TextTrackCueSpan, WebVttParser, TextTrackRegion

class TextTrackCue extends SubtitleTrack.Cue
{

    TextTrackCue()
    {
        mId = "";
        mPauseOnExit = false;
        mWritingDirection = 100;
        mRegionId = "";
        mSnapToLines = true;
        mLinePosition = null;
        mTextPosition = 50;
        mSize = 100;
        mAlignment = 200;
        mLines = null;
        mRegion = null;
    }

    public StringBuilder appendLinesToBuilder(StringBuilder stringbuilder)
    {
        if(mLines == null)
        {
            stringbuilder.append("null");
        } else
        {
            stringbuilder.append("[");
            boolean flag = true;
            TextTrackCueSpan atexttrackcuespan[][] = mLines;
            int j = atexttrackcuespan.length;
            int k = 0;
            while(k < j) 
            {
                TextTrackCueSpan atexttrackcuespan1[] = atexttrackcuespan[k];
                if(!flag)
                    stringbuilder.append(", ");
                if(atexttrackcuespan1 == null)
                {
                    stringbuilder.append("null");
                } else
                {
                    stringbuilder.append("\"");
                    boolean flag1 = true;
                    long l = -1L;
                    int i = 0;
                    for(int i1 = atexttrackcuespan1.length; i < i1;)
                    {
                        TextTrackCueSpan texttrackcuespan = atexttrackcuespan1[i];
                        if(!flag1)
                            stringbuilder.append(" ");
                        long l1 = l;
                        if(texttrackcuespan.mTimestampMs != l)
                        {
                            stringbuilder.append("<").append(WebVttParser.timeToString(texttrackcuespan.mTimestampMs)).append(">");
                            l1 = texttrackcuespan.mTimestampMs;
                        }
                        stringbuilder.append(texttrackcuespan.mText);
                        flag1 = false;
                        i++;
                        l = l1;
                    }

                    stringbuilder.append("\"");
                }
                flag = false;
                k++;
            }
            stringbuilder.append("]");
        }
        return stringbuilder;
    }

    public StringBuilder appendStringsToBuilder(StringBuilder stringbuilder)
    {
        if(mStrings == null)
        {
            stringbuilder.append("null");
        } else
        {
            stringbuilder.append("[");
            boolean flag = true;
            String as[] = mStrings;
            int i = 0;
            int j = as.length;
            while(i < j) 
            {
                String s = as[i];
                if(!flag)
                    stringbuilder.append(", ");
                if(s == null)
                {
                    stringbuilder.append("null");
                } else
                {
                    stringbuilder.append("\"");
                    stringbuilder.append(s);
                    stringbuilder.append("\"");
                }
                flag = false;
                i++;
            }
            stringbuilder.append("]");
        }
        return stringbuilder;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof TextTrackCue))
            return false;
        if(this == obj)
            return true;
        obj = (TextTrackCue)obj;
        boolean flag;
        if(mId.equals(((TextTrackCue) (obj)).mId) && mPauseOnExit == ((TextTrackCue) (obj)).mPauseOnExit && mWritingDirection == ((TextTrackCue) (obj)).mWritingDirection && mRegionId.equals(((TextTrackCue) (obj)).mRegionId) && mSnapToLines == ((TextTrackCue) (obj)).mSnapToLines && mAutoLinePosition == ((TextTrackCue) (obj)).mAutoLinePosition && (mAutoLinePosition || mLinePosition != null && mLinePosition.equals(((TextTrackCue) (obj)).mLinePosition) || mLinePosition == null && ((TextTrackCue) (obj)).mLinePosition == null) && mTextPosition == ((TextTrackCue) (obj)).mTextPosition && mSize == ((TextTrackCue) (obj)).mSize && mAlignment == ((TextTrackCue) (obj)).mAlignment)
        {
            if(mLines.length == ((TextTrackCue) (obj)).mLines.length)
                flag = true;
            else
                flag = false;
        } else
        {
            flag = false;
        }
        if(flag)
        {
            int i = 0;
            do
            {
                boolean flag1;
                try
                {
                    if(i >= mLines.length)
                        break;
                    flag1 = Arrays.equals(mLines[i], ((TextTrackCue) (obj)).mLines[i]);
                }
                // Misplaced declaration of an exception variable
                catch(Object obj)
                {
                    return false;
                }
                if(!flag1)
                    return false;
                i++;
            } while(true);
        }
        return flag;
    }

    public int hashCode()
    {
        return toString().hashCode();
    }

    public void onTime(long l)
    {
        TextTrackCueSpan atexttrackcuespan[][] = mLines;
        int i = atexttrackcuespan.length;
        for(int j = 0; j < i; j++)
        {
            TextTrackCueSpan atexttrackcuespan1[] = atexttrackcuespan[j];
            int k = atexttrackcuespan1.length;
            int i1 = 0;
            while(i1 < k) 
            {
                TextTrackCueSpan texttrackcuespan = atexttrackcuespan1[i1];
                boolean flag;
                if(l >= texttrackcuespan.mTimestampMs)
                    flag = true;
                else
                    flag = false;
                texttrackcuespan.mEnabled = flag;
                i1++;
            }
        }

    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        StringBuilder stringbuilder1 = stringbuilder.append(WebVttParser.timeToString(mStartTimeMs)).append(" --> ").append(WebVttParser.timeToString(mEndTimeMs)).append(" {id:\"").append(mId).append("\", pauseOnExit:").append(mPauseOnExit).append(", direction:");
        Object obj;
        if(mWritingDirection == 100)
            obj = "horizontal";
        else
        if(mWritingDirection == 102)
            obj = "vertical_lr";
        else
        if(mWritingDirection == 101)
            obj = "vertical_rl";
        else
            obj = "INVALID";
        stringbuilder1 = stringbuilder1.append(((String) (obj))).append(", regionId:\"").append(mRegionId).append("\", snapToLines:").append(mSnapToLines).append(", linePosition:");
        if(mAutoLinePosition)
            obj = "auto";
        else
            obj = mLinePosition;
        stringbuilder1 = stringbuilder1.append(obj).append(", textPosition:").append(mTextPosition).append(", size:").append(mSize).append(", alignment:");
        if(mAlignment == 202)
            obj = "end";
        else
        if(mAlignment == 203)
            obj = "left";
        else
        if(mAlignment == 200)
            obj = "middle";
        else
        if(mAlignment == 204)
            obj = "right";
        else
        if(mAlignment == 201)
            obj = "start";
        else
            obj = "INVALID";
        stringbuilder1.append(((String) (obj))).append(", text:");
        appendStringsToBuilder(stringbuilder).append("}");
        return stringbuilder.toString();
    }

    static final int ALIGNMENT_END = 202;
    static final int ALIGNMENT_LEFT = 203;
    static final int ALIGNMENT_MIDDLE = 200;
    static final int ALIGNMENT_RIGHT = 204;
    static final int ALIGNMENT_START = 201;
    private static final String TAG = "TTCue";
    static final int WRITING_DIRECTION_HORIZONTAL = 100;
    static final int WRITING_DIRECTION_VERTICAL_LR = 102;
    static final int WRITING_DIRECTION_VERTICAL_RL = 101;
    int mAlignment;
    boolean mAutoLinePosition;
    String mId;
    Integer mLinePosition;
    TextTrackCueSpan mLines[][];
    boolean mPauseOnExit;
    TextTrackRegion mRegion;
    String mRegionId;
    int mSize;
    boolean mSnapToLines;
    String mStrings[];
    int mTextPosition;
    int mWritingDirection;
}
