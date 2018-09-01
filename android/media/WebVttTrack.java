// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.*;

// Referenced classes of package android.media:
//            SubtitleTrack, WebVttCueListener, WebVttParser, UnstyledTextExtractor, 
//            Tokenizer, TextTrackCue, TextTrackRegion, TextTrackCueSpan, 
//            MediaTimeProvider, WebVttRenderingWidget, MediaFormat

class WebVttTrack extends SubtitleTrack
    implements WebVttCueListener
{

    WebVttTrack(WebVttRenderingWidget webvttrenderingwidget, MediaFormat mediaformat)
    {
        super(mediaformat);
        mTokenizer = new Tokenizer(mExtractor);
        mRenderingWidget = webvttrenderingwidget;
    }

    public volatile SubtitleTrack.RenderingWidget getRenderingWidget()
    {
        return getRenderingWidget();
    }

    public WebVttRenderingWidget getRenderingWidget()
    {
        return mRenderingWidget;
    }

    public void onCueParsed(TextTrackCue texttrackcue)
    {
        WebVttParser webvttparser = mParser;
        webvttparser;
        JVM INSTR monitorenter ;
        Object aobj[];
        if(texttrackcue.mRegionId.length() != 0)
            texttrackcue.mRegion = (TextTrackRegion)mRegions.get(texttrackcue.mRegionId);
        if(DEBUG)
        {
            StringBuilder stringbuilder = JVM INSTR new #101 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.v("WebVttTrack", stringbuilder.append("adding cue ").append(texttrackcue).toString());
        }
        mTokenizer.reset();
        aobj = texttrackcue.mStrings;
        int i = 0;
        int j = aobj.length;
_L2:
        Object obj;
        if(i >= j)
            break; /* Loop/switch isn't completed */
        obj = aobj[i];
        mTokenizer.tokenize(((String) (obj)));
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        TextTrackCueSpan atexttrackcuespan[][];
        int k;
        texttrackcue.mLines = mExtractor.getText();
        if(DEBUG)
        {
            obj = JVM INSTR new #101 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            Log.v("WebVttTrack", texttrackcue.appendLinesToBuilder(texttrackcue.appendStringsToBuilder(((StringBuilder) (obj))).append(" simplified to: ")).toString());
        }
        atexttrackcuespan = texttrackcue.mLines;
        k = atexttrackcuespan.length;
        i = 0;
_L7:
        if(i >= k)
            break; /* Loop/switch isn't completed */
        aobj = atexttrackcuespan[i];
        j = 0;
        int l = aobj.length;
_L5:
        if(j >= l) goto _L4; else goto _L3
_L3:
        obj = aobj[j];
        if(((TextTrackCueSpan) (obj)).mTimestampMs > texttrackcue.mStartTimeMs && ((TextTrackCueSpan) (obj)).mTimestampMs < texttrackcue.mEndTimeMs && mTimestamps.contains(Long.valueOf(((TextTrackCueSpan) (obj)).mTimestampMs)) ^ true)
            mTimestamps.add(Long.valueOf(((TextTrackCueSpan) (obj)).mTimestampMs));
        j++;
          goto _L5
_L4:
        i++;
        if(true) goto _L7; else goto _L6
_L6:
        if(mTimestamps.size() <= 0)
            break MISSING_BLOCK_LABEL_380;
        texttrackcue.mInnerTimesMs = new long[mTimestamps.size()];
        i = 0;
_L9:
        if(i >= mTimestamps.size())
            break; /* Loop/switch isn't completed */
        texttrackcue.mInnerTimesMs[i] = ((Long)mTimestamps.get(i)).longValue();
        i++;
        if(true) goto _L9; else goto _L8
_L8:
        mTimestamps.clear();
_L10:
        texttrackcue.mRunID = mCurrentRunID.longValue();
        webvttparser;
        JVM INSTR monitorexit ;
        addCue(texttrackcue);
        return;
        texttrackcue.mInnerTimesMs = null;
          goto _L10
        texttrackcue;
        throw texttrackcue;
    }

    public void onData(byte abyte0[], boolean flag, long l)
    {
        Object obj;
        obj = JVM INSTR new #79  <Class String>;
        ((String) (obj)).String(abyte0, "UTF-8");
        abyte0 = mParser;
        abyte0;
        JVM INSTR monitorenter ;
        if(mCurrentRunID != null && l != mCurrentRunID.longValue())
        {
            IllegalStateException illegalstateexception = JVM INSTR new #211 <Class IllegalStateException>;
            obj = JVM INSTR new #101 <Class StringBuilder>;
            ((StringBuilder) (obj)).StringBuilder();
            illegalstateexception.IllegalStateException(((StringBuilder) (obj)).append("Run #").append(mCurrentRunID).append(" in progress.  Cannot process run #").append(l).toString());
            throw illegalstateexception;
        }
          goto _L1
        obj;
        abyte0;
        JVM INSTR monitorexit ;
        throw obj;
        abyte0;
        Log.w("WebVttTrack", (new StringBuilder()).append("subtitle data is not UTF-8 encoded: ").append(abyte0).toString());
_L2:
        return;
_L1:
        mCurrentRunID = Long.valueOf(l);
        mParser.parse(((String) (obj)));
        if(!flag)
            break MISSING_BLOCK_LABEL_169;
        finishedRun(l);
        mParser.eos();
        mRegions.clear();
        mCurrentRunID = null;
        abyte0;
        JVM INSTR monitorexit ;
          goto _L2
    }

    public void onRegionParsed(TextTrackRegion texttrackregion)
    {
        WebVttParser webvttparser = mParser;
        webvttparser;
        JVM INSTR monitorenter ;
        mRegions.put(texttrackregion.mId, texttrackregion);
        webvttparser;
        JVM INSTR monitorexit ;
        return;
        texttrackregion;
        throw texttrackregion;
    }

    public void updateView(Vector vector)
    {
        if(!mVisible)
            return;
        if(DEBUG && mTimeProvider != null)
            try
            {
                StringBuilder stringbuilder = JVM INSTR new #101 <Class StringBuilder>;
                stringbuilder.StringBuilder();
                Log.d("WebVttTrack", stringbuilder.append("at ").append(mTimeProvider.getCurrentTimeUs(false, true) / 1000L).append(" ms the active cues are:").toString());
            }
            catch(IllegalStateException illegalstateexception)
            {
                Log.d("WebVttTrack", "at (illegal state) the active cues are:");
            }
        if(mRenderingWidget != null)
            mRenderingWidget.setActiveCues(vector);
    }

    private static final String TAG = "WebVttTrack";
    private Long mCurrentRunID;
    private final UnstyledTextExtractor mExtractor = new UnstyledTextExtractor();
    private final WebVttParser mParser = new WebVttParser(this);
    private final Map mRegions = new HashMap();
    private final WebVttRenderingWidget mRenderingWidget;
    private final Vector mTimestamps = new Vector();
    private final Tokenizer mTokenizer;
}
