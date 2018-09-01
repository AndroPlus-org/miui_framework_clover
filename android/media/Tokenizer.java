// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.util.Log;

// Referenced classes of package android.media:
//            WebVttParser

class Tokenizer
{
    class DataTokenizer
        implements TokenizerPhase
    {

        private boolean replaceEscape(String s, String s1, int i)
        {
            if(Tokenizer._2D_get2(Tokenizer.this).startsWith(s, i))
            {
                mData.append(Tokenizer._2D_get2(Tokenizer.this).substring(Tokenizer._2D_get1(Tokenizer.this), i));
                mData.append(s1);
                Tokenizer._2D_set0(Tokenizer.this, s.length() + i);
                Tokenizer._2D_get1(Tokenizer.this);
                return true;
            } else
            {
                return false;
            }
        }

        public TokenizerPhase start()
        {
            mData = new StringBuilder();
            return this;
        }

        public void tokenize()
        {
            int i;
            int j;
            i = Tokenizer._2D_get2(Tokenizer.this).length();
            j = Tokenizer._2D_get1(Tokenizer.this);
_L2:
            int k;
            k = i;
            if(j >= Tokenizer._2D_get2(Tokenizer.this).length())
                break MISSING_BLOCK_LABEL_165;
            if(Tokenizer._2D_get2(Tokenizer.this).charAt(j) != '&')
                break; /* Loop/switch isn't completed */
            if(!replaceEscape("&amp;", "&", j) && !replaceEscape("&lt;", "<", j) && !replaceEscape("&gt;", ">", j) && !replaceEscape("&lrm;", "\u200E", j) && !replaceEscape("&rlm;", "\u200F", j))
                replaceEscape("&nbsp;", "\240", j);
_L4:
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            if(Tokenizer._2D_get2(Tokenizer.this).charAt(j) != '<') goto _L4; else goto _L3
_L3:
            Tokenizer._2D_set1(Tokenizer.this, Tokenizer._2D_get4(Tokenizer.this).start());
            k = j;
            mData.append(Tokenizer._2D_get2(Tokenizer.this).substring(Tokenizer._2D_get1(Tokenizer.this), k));
            Tokenizer._2D_get3(Tokenizer.this).onData(mData.toString());
            mData.delete(0, mData.length());
            Tokenizer._2D_set0(Tokenizer.this, k);
            return;
        }

        private StringBuilder mData;
        final Tokenizer this$0;

        DataTokenizer()
        {
            this$0 = Tokenizer.this;
            super();
        }
    }

    static interface OnTokenListener
    {

        public abstract void onData(String s);

        public abstract void onEnd(String s);

        public abstract void onLineEnd();

        public abstract void onStart(String s, String as[], String s1);

        public abstract void onTimeStamp(long l);
    }

    class TagTokenizer
        implements TokenizerPhase
    {

        private void yield_tag()
        {
            if(mName.startsWith("/"))
                Tokenizer._2D_get3(Tokenizer.this).onEnd(mName.substring(1));
            else
            if(mName.length() > 0 && Character.isDigit(mName.charAt(0)))
            {
                try
                {
                    long l = WebVttParser.parseTimestampMs(mName);
                    Tokenizer._2D_get3(Tokenizer.this).onTimeStamp(l);
                }
                catch(NumberFormatException numberformatexception)
                {
                    Log.d("Tokenizer", (new StringBuilder()).append("invalid timestamp tag: <").append(mName).append(">").toString());
                }
            } else
            {
                mAnnotation = mAnnotation.replaceAll("\\s+", " ");
                if(mAnnotation.startsWith(" "))
                    mAnnotation = mAnnotation.substring(1);
                if(mAnnotation.endsWith(" "))
                    mAnnotation = mAnnotation.substring(0, mAnnotation.length() - 1);
                String as[] = null;
                int i = mName.indexOf('.');
                if(i >= 0)
                {
                    as = mName.substring(i + 1).split("\\.");
                    mName = mName.substring(0, i);
                }
                Tokenizer._2D_get3(Tokenizer.this).onStart(mName, as, mAnnotation);
            }
        }

        public TokenizerPhase start()
        {
            mAnnotation = "";
            mName = "";
            mAtAnnotation = false;
            return this;
        }

        public void tokenize()
        {
            if(!mAtAnnotation)
            {
                Tokenizer tokenizer = Tokenizer.this;
                Tokenizer._2D_set0(tokenizer, Tokenizer._2D_get1(tokenizer) + 1);
            }
            if(Tokenizer._2D_get1(Tokenizer.this) < Tokenizer._2D_get2(Tokenizer.this).length())
            {
                Object obj;
                String s;
                Tokenizer tokenizer1;
                if(mAtAnnotation || Tokenizer._2D_get2(Tokenizer.this).charAt(Tokenizer._2D_get1(Tokenizer.this)) == '/')
                    obj = Tokenizer._2D_get2(Tokenizer.this).substring(Tokenizer._2D_get1(Tokenizer.this)).split(">");
                else
                    obj = Tokenizer._2D_get2(Tokenizer.this).substring(Tokenizer._2D_get1(Tokenizer.this)).split("[\t\f >]");
                s = Tokenizer._2D_get2(Tokenizer.this).substring(Tokenizer._2D_get1(Tokenizer.this), Tokenizer._2D_get1(Tokenizer.this) + obj[0].length());
                tokenizer1 = Tokenizer.this;
                Tokenizer._2D_set0(tokenizer1, Tokenizer._2D_get1(tokenizer1) + obj[0].length());
                if(mAtAnnotation)
                    mAnnotation = (new StringBuilder()).append(mAnnotation).append(" ").append(s).toString();
                else
                    mName = s;
            }
            mAtAnnotation = true;
            if(Tokenizer._2D_get1(Tokenizer.this) < Tokenizer._2D_get2(Tokenizer.this).length() && Tokenizer._2D_get2(Tokenizer.this).charAt(Tokenizer._2D_get1(Tokenizer.this)) == '>')
            {
                yield_tag();
                Tokenizer._2D_set1(Tokenizer.this, Tokenizer._2D_get0(Tokenizer.this).start());
                obj = Tokenizer.this;
                Tokenizer._2D_set0(((Tokenizer) (obj)), Tokenizer._2D_get1(((Tokenizer) (obj))) + 1);
            }
        }

        private String mAnnotation;
        private boolean mAtAnnotation;
        private String mName;
        final Tokenizer this$0;

        TagTokenizer()
        {
            this$0 = Tokenizer.this;
            super();
        }
    }

    static interface TokenizerPhase
    {

        public abstract TokenizerPhase start();

        public abstract void tokenize();
    }


    static TokenizerPhase _2D_get0(Tokenizer tokenizer)
    {
        return tokenizer.mDataTokenizer;
    }

    static int _2D_get1(Tokenizer tokenizer)
    {
        return tokenizer.mHandledLen;
    }

    static String _2D_get2(Tokenizer tokenizer)
    {
        return tokenizer.mLine;
    }

    static OnTokenListener _2D_get3(Tokenizer tokenizer)
    {
        return tokenizer.mListener;
    }

    static TokenizerPhase _2D_get4(Tokenizer tokenizer)
    {
        return tokenizer.mTagTokenizer;
    }

    static int _2D_set0(Tokenizer tokenizer, int i)
    {
        tokenizer.mHandledLen = i;
        return i;
    }

    static TokenizerPhase _2D_set1(Tokenizer tokenizer, TokenizerPhase tokenizerphase)
    {
        tokenizer.mPhase = tokenizerphase;
        return tokenizerphase;
    }

    Tokenizer(OnTokenListener ontokenlistener)
    {
        mDataTokenizer = new DataTokenizer();
        mTagTokenizer = new TagTokenizer();
        reset();
        mListener = ontokenlistener;
    }

    void reset()
    {
        mPhase = mDataTokenizer.start();
    }

    void tokenize(String s)
    {
        mHandledLen = 0;
        for(mLine = s; mHandledLen < mLine.length(); mPhase.tokenize());
        if(!(mPhase instanceof TagTokenizer))
            mListener.onLineEnd();
    }

    private static final String TAG = "Tokenizer";
    private TokenizerPhase mDataTokenizer;
    private int mHandledLen;
    private String mLine;
    private OnTokenListener mListener;
    private TokenizerPhase mPhase;
    private TokenizerPhase mTagTokenizer;
}
