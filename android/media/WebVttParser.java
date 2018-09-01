// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.Log;
import java.util.Vector;

// Referenced classes of package android.media:
//            TextTrackCue, WebVttCueListener, TextTrackRegion

class WebVttParser
{
    static interface Phase
    {

        public abstract void parse(String s);
    }


    static TextTrackCue _2D_get0(WebVttParser webvttparser)
    {
        return webvttparser.mCue;
    }

    static Vector _2D_get1(WebVttParser webvttparser)
    {
        return webvttparser.mCueTexts;
    }

    static WebVttCueListener _2D_get2(WebVttParser webvttparser)
    {
        return webvttparser.mListener;
    }

    static Phase _2D_get3(WebVttParser webvttparser)
    {
        return webvttparser.mParseCueId;
    }

    static Phase _2D_get4(WebVttParser webvttparser)
    {
        return webvttparser.mParseCueText;
    }

    static Phase _2D_get5(WebVttParser webvttparser)
    {
        return webvttparser.mParseCueTime;
    }

    static Phase _2D_get6(WebVttParser webvttparser)
    {
        return webvttparser.mParseHeader;
    }

    static Phase _2D_get7(WebVttParser webvttparser)
    {
        return webvttparser.mPhase;
    }

    static Phase _2D_get8(WebVttParser webvttparser)
    {
        return webvttparser.mSkipRest;
    }

    static TextTrackCue _2D_set0(WebVttParser webvttparser, TextTrackCue texttrackcue)
    {
        webvttparser.mCue = texttrackcue;
        return texttrackcue;
    }

    static Phase _2D_set1(WebVttParser webvttparser, Phase phase)
    {
        webvttparser.mPhase = phase;
        return phase;
    }

    static void _2D_wrap0(WebVttParser webvttparser, String s, String s1)
    {
        webvttparser.log_warning(s, s1);
    }

    static void _2D_wrap1(WebVttParser webvttparser, String s, String s1, String s2, String s3, String s4)
    {
        webvttparser.log_warning(s, s1, s2, s3, s4);
    }

    static void _2D_wrap2(WebVttParser webvttparser, String s, String s1, String s2, String s3)
    {
        webvttparser.log_warning(s, s1, s2, s3);
    }

    WebVttParser(WebVttCueListener webvttcuelistener)
    {
        mPhase = mParseStart;
        mBuffer = "";
        mListener = webvttcuelistener;
        mCueTexts = new Vector();
    }

    private void log_warning(String s, String s1)
    {
        Log.w(getClass().getName(), (new StringBuilder()).append(s).append(" ('").append(s1).append("')").toString());
    }

    private void log_warning(String s, String s1, String s2, String s3)
    {
        Log.w(getClass().getName(), (new StringBuilder()).append(s).append(" '").append(s1).append("' ").append(s2).append(" ('").append(s3).append("')").toString());
    }

    private void log_warning(String s, String s1, String s2, String s3, String s4)
    {
        Log.w(getClass().getName(), (new StringBuilder()).append(s).append(" '").append(s1).append("' ").append(s2).append(" ('").append(s4).append("' ").append(s3).append(")").toString());
    }

    public static float parseFloatPercentage(String s)
        throws NumberFormatException
    {
        float f;
        if(!s.endsWith("%"))
            throw new NumberFormatException("does not end in %");
        s = s.substring(0, s.length() - 1);
        if(s.matches(".*[^0-9.].*"))
            throw new NumberFormatException("contains an invalid character");
        try
        {
            f = Float.parseFloat(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new NumberFormatException("is not a number");
        }
        if(f >= 0.0F && f <= 100F)
            break MISSING_BLOCK_LABEL_91;
        s = JVM INSTR new #153 <Class NumberFormatException>;
        s.NumberFormatException("is out of range");
        throw s;
        return f;
    }

    public static int parseIntPercentage(String s)
        throws NumberFormatException
    {
        int i;
        if(!s.endsWith("%"))
            throw new NumberFormatException("does not end in %");
        s = s.substring(0, s.length() - 1);
        if(s.matches(".*[^0-9].*"))
            throw new NumberFormatException("contains an invalid character");
        try
        {
            i = Integer.parseInt(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new NumberFormatException("is not a number");
        }
        if(i >= 0 && i <= 100)
            break MISSING_BLOCK_LABEL_88;
        s = JVM INSTR new #153 <Class NumberFormatException>;
        s.NumberFormatException("is out of range");
        throw s;
        return i;
    }

    public static long parseTimestampMs(String s)
        throws NumberFormatException
    {
        int i = 0;
        if(!s.matches("(\\d+:)?[0-5]\\d:[0-5]\\d\\.\\d{3}"))
            throw new NumberFormatException("has invalid format");
        String as[] = s.split("\\.", 2);
        long l = 0L;
        s = as[0].split(":");
        for(int j = s.length; i < j; i++)
            l = 60L * l + Long.parseLong(s[i]);

        return 1000L * l + Long.parseLong(as[1]);
    }

    public static String timeToString(long l)
    {
        return String.format("%d:%02d:%02d.%03d", new Object[] {
            Long.valueOf(l / 0x36ee80L), Long.valueOf((l / 60000L) % 60L), Long.valueOf((l / 1000L) % 60L), Long.valueOf(l % 1000L)
        });
    }

    public void eos()
    {
        if(mBuffer.endsWith("\r"))
            mBuffer = mBuffer.substring(0, mBuffer.length() - 1);
        mPhase.parse(mBuffer);
        mBuffer = "";
        yieldCue();
        mPhase = mParseStart;
    }

    public void parse(String s)
    {
        boolean flag = false;
        mBuffer = (new StringBuilder()).append(mBuffer).append(s.replace("\0", "\uFFFD")).toString().replace("\r\n", "\n");
        if(mBuffer.endsWith("\r"))
        {
            flag = true;
            mBuffer = mBuffer.substring(0, mBuffer.length() - 1);
        }
        s = mBuffer.split("[\r\n]");
        for(int i = 0; i < s.length - 1; i++)
            mPhase.parse(s[i]);

        mBuffer = s[s.length - 1];
        if(flag)
            mBuffer = (new StringBuilder()).append(mBuffer).append("\r").toString();
    }

    public void yieldCue()
    {
        if(mCue != null && mCueTexts.size() > 0)
        {
            mCue.mStrings = new String[mCueTexts.size()];
            mCueTexts.toArray(mCue.mStrings);
            mCueTexts.clear();
            mListener.onCueParsed(mCue);
        }
        mCue = null;
    }

    private static final String TAG = "WebVttParser";
    private String mBuffer;
    private TextTrackCue mCue;
    private Vector mCueTexts;
    private WebVttCueListener mListener;
    private final Phase mParseCueId = new Phase() {

        public void parse(String s)
        {
            if(s.length() == 0)
                return;
            if(!_2D_assertionsDisabled && WebVttParser._2D_get0(WebVttParser.this) != null)
                throw new AssertionError();
            if(s.equals("NOTE") || s.startsWith("NOTE "))
                WebVttParser._2D_set1(WebVttParser.this, WebVttParser._2D_get4(WebVttParser.this));
            WebVttParser._2D_set0(WebVttParser.this, new TextTrackCue());
            WebVttParser._2D_get1(WebVttParser.this).clear();
            WebVttParser._2D_set1(WebVttParser.this, WebVttParser._2D_get5(WebVttParser.this));
            if(s.contains("-->"))
                WebVttParser._2D_get7(WebVttParser.this).parse(s);
            else
                WebVttParser._2D_get0(WebVttParser.this).mId = s;
        }

        static final boolean _2D_assertionsDisabled = android/media/WebVttParser$4.desiredAssertionStatus() ^ true;
        final WebVttParser this$0;


            
            {
                this$0 = WebVttParser.this;
                super();
            }
    }
;
    private final Phase mParseCueText = new Phase() {

        public void parse(String s)
        {
            if(s.length() == 0)
            {
                yieldCue();
                WebVttParser._2D_set1(WebVttParser.this, WebVttParser._2D_get3(WebVttParser.this));
                return;
            }
            if(WebVttParser._2D_get0(WebVttParser.this) != null)
                WebVttParser._2D_get1(WebVttParser.this).add(s);
        }

        final WebVttParser this$0;

            
            {
                this$0 = WebVttParser.this;
                super();
            }
    }
;
    private final Phase mParseCueTime = new Phase() {

        public void parse(String s)
        {
            String s1;
            int k;
            int i = s.indexOf("-->");
            if(i < 0)
            {
                WebVttParser._2D_set0(WebVttParser.this, null);
                WebVttParser._2D_set1(WebVttParser.this, WebVttParser._2D_get3(WebVttParser.this));
                return;
            }
            s1 = s.substring(0, i).trim();
            Object obj = s.substring(i + 3).replaceFirst("^\\s+", "").replaceFirst("\\s+", " ");
            i = ((String) (obj)).indexOf(' ');
            int j;
            if(i > 0)
                s = ((String) (obj)).substring(0, i);
            else
                s = ((String) (obj));
            if(i > 0)
                obj = ((String) (obj)).substring(i + 1);
            else
                obj = "";
            WebVttParser._2D_get0(WebVttParser.this).mStartTimeMs = WebVttParser.parseTimestampMs(s1);
            WebVttParser._2D_get0(WebVttParser.this).mEndTimeMs = WebVttParser.parseTimestampMs(s);
            obj = ((String) (obj)).split(" +");
            i = 0;
            j = obj.length;
            if(i >= j)
                break MISSING_BLOCK_LABEL_703;
            s1 = obj[i];
            k = s1.indexOf(':');
            if(k > 0 && k != s1.length() - 1)
                break; /* Loop/switch isn't completed */
_L4:
            i++;
            if(true) goto _L2; else goto _L1
_L2:
            break MISSING_BLOCK_LABEL_147;
_L1:
            s = s1.substring(0, k);
            s1 = s1.substring(k + 1);
            if(s.equals("region"))
            {
                WebVttParser._2D_get0(WebVttParser.this).mRegionId = s1;
            } else
            {
label0:
                {
                    if(!s.equals("vertical"))
                        break label0;
                    if(s1.equals("rl"))
                        WebVttParser._2D_get0(WebVttParser.this).mWritingDirection = 101;
                    else
                    if(s1.equals("lr"))
                        WebVttParser._2D_get0(WebVttParser.this).mWritingDirection = 102;
                    else
                        WebVttParser._2D_wrap2(WebVttParser.this, "cue setting", s, "has invalid value", s1);
                }
            }
            continue; /* Loop/switch isn't completed */
            if(!s.equals("line"))
                break MISSING_BLOCK_LABEL_465;
            NumberFormatException numberformatexception;
            if(!_2D_assertionsDisabled && s1.indexOf(' ') >= 0)
            {
                AssertionError assertionerror = JVM INSTR new #138 <Class AssertionError>;
                assertionerror.AssertionError();
                throw assertionerror;
            }
label1:
            {
                if(!s1.endsWith("%"))
                    break label1;
                WebVttParser._2D_get0(WebVttParser.this).mSnapToLines = false;
                WebVttParser._2D_get0(WebVttParser.this).mLinePosition = Integer.valueOf(WebVttParser.parseIntPercentage(s1));
            }
            continue; /* Loop/switch isn't completed */
label2:
            {
                if(!s1.matches(".*[^0-9].*"))
                    break label2;
                WebVttParser._2D_wrap2(WebVttParser.this, "cue setting", s, "contains an invalid character", s1);
            }
            continue; /* Loop/switch isn't completed */
            try
            {
                WebVttParser._2D_get0(WebVttParser.this).mSnapToLines = true;
                WebVttParser._2D_get0(WebVttParser.this).mLinePosition = Integer.valueOf(Integer.parseInt(s1));
            }
            // Misplaced declaration of an exception variable
            catch(NumberFormatException numberformatexception)
            {
                WebVttParser._2D_wrap2(WebVttParser.this, "cue setting", s, "is not numeric or percentage", s1);
            }
            continue; /* Loop/switch isn't completed */
            if(s.equals("position"))
                try
                {
                    WebVttParser._2D_get0(WebVttParser.this).mTextPosition = WebVttParser.parseIntPercentage(s1);
                }
                catch(NumberFormatException numberformatexception1)
                {
                    WebVttParser._2D_wrap2(WebVttParser.this, "cue setting", s, "is not numeric or percentage", s1);
                }
            else
            if(s.equals("size"))
                try
                {
                    WebVttParser._2D_get0(WebVttParser.this).mSize = WebVttParser.parseIntPercentage(s1);
                }
                catch(NumberFormatException numberformatexception2)
                {
                    WebVttParser._2D_wrap2(WebVttParser.this, "cue setting", s, "is not numeric or percentage", s1);
                }
            else
            if(s.equals("align"))
                if(s1.equals("start"))
                    WebVttParser._2D_get0(WebVttParser.this).mAlignment = 201;
                else
                if(s1.equals("middle"))
                    WebVttParser._2D_get0(WebVttParser.this).mAlignment = 200;
                else
                if(s1.equals("end"))
                    WebVttParser._2D_get0(WebVttParser.this).mAlignment = 202;
                else
                if(s1.equals("left"))
                    WebVttParser._2D_get0(WebVttParser.this).mAlignment = 203;
                else
                if(s1.equals("right"))
                    WebVttParser._2D_get0(WebVttParser.this).mAlignment = 204;
                else
                    WebVttParser._2D_wrap2(WebVttParser.this, "cue setting", s, "has invalid value", s1);
            if(true) goto _L4; else goto _L3
_L3:
            WebVttParser._2D_set1(WebVttParser.this, WebVttParser._2D_get4(WebVttParser.this));
            return;
            if(WebVttParser._2D_get0(WebVttParser.this).mLinePosition != null || WebVttParser._2D_get0(WebVttParser.this).mSize != 100 || WebVttParser._2D_get0(WebVttParser.this).mWritingDirection != 100)
                WebVttParser._2D_get0(WebVttParser.this).mRegionId = "";
              goto _L3
        }

        static final boolean _2D_assertionsDisabled = android/media/WebVttParser$5.desiredAssertionStatus() ^ true;
        final WebVttParser this$0;


            
            {
                this$0 = WebVttParser.this;
                super();
            }
    }
;
    private final Phase mParseHeader = new Phase() {

        public void parse(String s)
        {
            if(s.length() != 0) goto _L2; else goto _L1
_L1:
            WebVttParser._2D_set1(WebVttParser.this, WebVttParser._2D_get3(WebVttParser.this));
_L4:
            return;
_L2:
            if(s.contains("-->"))
            {
                WebVttParser._2D_set1(WebVttParser.this, WebVttParser._2D_get5(WebVttParser.this));
                WebVttParser._2D_get7(WebVttParser.this).parse(s);
            } else
            {
                int i = s.indexOf(':');
                if(i <= 0 || i >= s.length() - 1)
                    WebVttParser._2D_wrap0(WebVttParser.this, "meta data header has invalid format", s);
                String s1 = s.substring(0, i);
                s = s.substring(i + 1);
                if(s1.equals("Region"))
                {
                    s = parseRegion(s);
                    WebVttParser._2D_get2(WebVttParser.this).onRegionParsed(s);
                }
            }
            if(true) goto _L4; else goto _L3
_L3:
        }

        TextTrackRegion parseRegion(String s)
        {
            TextTrackRegion texttrackregion;
            int i;
            int j;
            texttrackregion = new TextTrackRegion();
            s = s.split(" +");
            i = s.length;
            j = 0;
_L2:
            Object obj;
            int k;
            if(j >= i)
                break MISSING_BLOCK_LABEL_471;
            obj = s[j];
            k = ((String) (obj)).indexOf('=');
            if(k > 0 && k != ((String) (obj)).length() - 1)
                break; /* Loop/switch isn't completed */
_L3:
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            String s1;
            s1 = ((String) (obj)).substring(0, k);
            obj = ((String) (obj)).substring(k + 1);
            if(s1.equals("id"))
                texttrackregion.mId = ((String) (obj));
            else
            if(s1.equals("width"))
                try
                {
                    texttrackregion.mWidth = WebVttParser.parseFloatPercentage(((String) (obj)));
                }
                catch(NumberFormatException numberformatexception)
                {
                    WebVttParser._2D_wrap1(WebVttParser.this, "region setting", s1, "has invalid value", numberformatexception.getMessage(), ((String) (obj)));
                }
            else
            if(s1.equals("lines"))
            {
                if(((String) (obj)).matches(".*[^0-9].*"))
                    WebVttParser._2D_wrap2(WebVttParser.this, "lines", s1, "contains an invalid character", ((String) (obj)));
                else
                    try
                    {
                        texttrackregion.mLines = Integer.parseInt(((String) (obj)));
                        if(!_2D_assertionsDisabled && texttrackregion.mLines < 0)
                        {
                            AssertionError assertionerror = JVM INSTR new #161 <Class AssertionError>;
                            assertionerror.AssertionError();
                            throw assertionerror;
                        }
                    }
                    catch(NumberFormatException numberformatexception1)
                    {
                        WebVttParser._2D_wrap2(WebVttParser.this, "region setting", s1, "is not numeric", ((String) (obj)));
                    }
            } else
            {
label0:
                {
                    if(!s1.equals("regionanchor") && !s1.equals("viewportanchor"))
                        break MISSING_BLOCK_LABEL_423;
                    k = ((String) (obj)).indexOf(",");
                    if(k >= 0)
                        break label0;
                    WebVttParser._2D_wrap2(WebVttParser.this, "region setting", s1, "contains no comma", ((String) (obj)));
                }
            }
              goto _L3
            String s2;
            s2 = ((String) (obj)).substring(0, k);
            obj = ((String) (obj)).substring(k + 1);
            float f = WebVttParser.parseFloatPercentage(s2);
            float f1 = WebVttParser.parseFloatPercentage(((String) (obj)));
            NumberFormatException numberformatexception2;
            if(s1.charAt(0) == 'r')
            {
                texttrackregion.mAnchorPointX = f;
                texttrackregion.mAnchorPointY = f1;
            } else
            {
                texttrackregion.mViewportAnchorPointX = f;
                texttrackregion.mViewportAnchorPointY = f1;
            }
              goto _L3
            obj;
            WebVttParser._2D_wrap1(WebVttParser.this, "region setting", s1, "has invalid x component", ((NumberFormatException) (obj)).getMessage(), s2);
              goto _L3
            numberformatexception2;
            WebVttParser._2D_wrap1(WebVttParser.this, "region setting", s1, "has invalid y component", numberformatexception2.getMessage(), ((String) (obj)));
              goto _L3
            if(s1.equals("scroll"))
                if(((String) (obj)).equals("up"))
                    texttrackregion.mScrollValue = 301;
                else
                    WebVttParser._2D_wrap2(WebVttParser.this, "region setting", s1, "has invalid value", ((String) (obj)));
              goto _L3
            return texttrackregion;
        }

        static final boolean _2D_assertionsDisabled = android/media/WebVttParser$3.desiredAssertionStatus() ^ true;
        final WebVttParser this$0;


            
            {
                this$0 = WebVttParser.this;
                super();
            }
    }
;
    private final Phase mParseStart = new Phase() {

        public void parse(String s)
        {
            String s1 = s;
            if(s.startsWith("\uFEFF"))
                s1 = s.substring(1);
            if(!s1.equals("WEBVTT") && s1.startsWith("WEBVTT ") ^ true && s1.startsWith("WEBVTT\t") ^ true)
            {
                WebVttParser._2D_wrap0(WebVttParser.this, "Not a WEBVTT header", s1);
                WebVttParser._2D_set1(WebVttParser.this, WebVttParser._2D_get8(WebVttParser.this));
            } else
            {
                WebVttParser._2D_set1(WebVttParser.this, WebVttParser._2D_get6(WebVttParser.this));
            }
        }

        final WebVttParser this$0;

            
            {
                this$0 = WebVttParser.this;
                super();
            }
    }
;
    private Phase mPhase;
    private final Phase mSkipRest = new Phase() {

        public void parse(String s)
        {
        }

        final WebVttParser this$0;

            
            {
                this$0 = WebVttParser.this;
                super();
            }
    }
;
}
