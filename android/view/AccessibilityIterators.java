// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.content.res.Configuration;
import android.os.LocaleList;
import java.text.BreakIterator;
import java.util.Locale;

// Referenced classes of package android.view:
//            ViewRootImpl

public final class AccessibilityIterators
{
    public static abstract class AbstractTextSegmentIterator
        implements TextSegmentIterator
    {

        protected int[] getRange(int i, int j)
        {
            while(i < 0 || j < 0 || i == j) 
                return null;
            mSegment[0] = i;
            mSegment[1] = j;
            return mSegment;
        }

        public void initialize(String s)
        {
            mText = s;
        }

        private final int mSegment[] = new int[2];
        protected String mText;

        public AbstractTextSegmentIterator()
        {
        }
    }

    static class CharacterTextSegmentIterator extends AbstractTextSegmentIterator
        implements ViewRootImpl.ConfigChangedCallback
    {

        public static CharacterTextSegmentIterator getInstance(Locale locale)
        {
            if(sInstance == null)
                sInstance = new CharacterTextSegmentIterator(locale);
            return sInstance;
        }

        public int[] following(int i)
        {
            int j = mText.length();
            if(j <= 0)
                return null;
            if(i >= j)
                return null;
            j = i;
            if(i < 0)
                j = 0;
            while(!mImpl.isBoundary(j)) 
            {
                i = mImpl.following(j);
                j = i;
                if(i == -1)
                    return null;
            }
            i = mImpl.following(j);
            if(i == -1)
                return null;
            else
                return getRange(j, i);
        }

        public void initialize(String s)
        {
            super.initialize(s);
            mImpl.setText(s);
        }

        public void onConfigurationChanged(Configuration configuration)
        {
            configuration = configuration.getLocales().get(0);
            if(!mLocale.equals(configuration))
            {
                mLocale = configuration;
                onLocaleChanged(configuration);
            }
        }

        protected void onLocaleChanged(Locale locale)
        {
            mImpl = BreakIterator.getCharacterInstance(locale);
        }

        public int[] preceding(int i)
        {
            int j = mText.length();
            if(j <= 0)
                return null;
            if(i <= 0)
                return null;
            int k = i;
            if(i > j)
                k = j;
            while(!mImpl.isBoundary(k)) 
            {
                i = mImpl.preceding(k);
                k = i;
                if(i == -1)
                    return null;
            }
            i = mImpl.preceding(k);
            if(i == -1)
                return null;
            else
                return getRange(i, k);
        }

        private static CharacterTextSegmentIterator sInstance;
        protected BreakIterator mImpl;
        private Locale mLocale;

        private CharacterTextSegmentIterator(Locale locale)
        {
            mLocale = locale;
            onLocaleChanged(locale);
            ViewRootImpl.addConfigCallback(this);
        }

        CharacterTextSegmentIterator(Locale locale, CharacterTextSegmentIterator charactertextsegmentiterator)
        {
            this(locale);
        }
    }

    static class ParagraphTextSegmentIterator extends AbstractTextSegmentIterator
    {

        public static ParagraphTextSegmentIterator getInstance()
        {
            if(sInstance == null)
                sInstance = new ParagraphTextSegmentIterator();
            return sInstance;
        }

        private boolean isEndBoundary(int i)
        {
            boolean flag = true;
            boolean flag1;
            if(i > 0 && mText.charAt(i - 1) != '\n')
            {
                flag1 = flag;
                if(i != mText.length())
                    if(mText.charAt(i) == '\n')
                        flag1 = flag;
                    else
                        flag1 = false;
            } else
            {
                flag1 = false;
            }
            return flag1;
        }

        private boolean isStartBoundary(int i)
        {
            boolean flag = true;
            boolean flag1;
            if(mText.charAt(i) != '\n')
            {
                flag1 = flag;
                if(i != 0)
                    if(mText.charAt(i - 1) == '\n')
                        flag1 = flag;
                    else
                        flag1 = false;
            } else
            {
                flag1 = false;
            }
            return flag1;
        }

        public int[] following(int i)
        {
            int j = mText.length();
            if(j <= 0)
                return null;
            if(i >= j)
                return null;
            int k = i;
            if(i < 0)
                k = 0;
            for(; k < j && mText.charAt(k) == '\n' && isStartBoundary(k) ^ true; k++);
            if(k >= j)
                return null;
            for(i = k + 1; i < j && isEndBoundary(i) ^ true; i++);
            return getRange(k, i);
        }

        public int[] preceding(int i)
        {
            int j = mText.length();
            if(j <= 0)
                return null;
            if(i <= 0)
                return null;
            int k = i;
            if(i > j)
                k = j;
            for(; k > 0 && mText.charAt(k - 1) == '\n' && isEndBoundary(k) ^ true; k--);
            if(k <= 0)
                return null;
            for(i = k - 1; i > 0 && isStartBoundary(i) ^ true; i--);
            return getRange(i, k);
        }

        private static ParagraphTextSegmentIterator sInstance;

        ParagraphTextSegmentIterator()
        {
        }
    }

    public static interface TextSegmentIterator
    {

        public abstract int[] following(int i);

        public abstract int[] preceding(int i);
    }

    static class WordTextSegmentIterator extends CharacterTextSegmentIterator
    {

        public static WordTextSegmentIterator getInstance(Locale locale)
        {
            if(sInstance == null)
                sInstance = new WordTextSegmentIterator(locale);
            return sInstance;
        }

        private boolean isEndBoundary(int i)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(i > 0)
            {
                flag1 = flag;
                if(isLetterOrDigit(i - 1))
                    if(i != mText.length())
                        flag1 = isLetterOrDigit(i) ^ true;
                    else
                        flag1 = true;
            }
            return flag1;
        }

        private boolean isLetterOrDigit(int i)
        {
            if(i >= 0 && i < mText.length())
                return Character.isLetterOrDigit(mText.codePointAt(i));
            else
                return false;
        }

        private boolean isStartBoundary(int i)
        {
            boolean flag = false;
            if(isLetterOrDigit(i))
                if(i != 0)
                    flag = isLetterOrDigit(i - 1) ^ true;
                else
                    flag = true;
            return flag;
        }

        public int[] following(int i)
        {
            if(mText.length() <= 0)
                return null;
            if(i >= mText.length())
                return null;
            int j = i;
            if(i < 0)
                j = 0;
            while(!isLetterOrDigit(j) && isStartBoundary(j) ^ true) 
            {
                i = mImpl.following(j);
                j = i;
                if(i == -1)
                    return null;
            }
            i = mImpl.following(j);
            if(i == -1 || isEndBoundary(i) ^ true)
                return null;
            else
                return getRange(j, i);
        }

        protected void onLocaleChanged(Locale locale)
        {
            mImpl = BreakIterator.getWordInstance(locale);
        }

        public int[] preceding(int i)
        {
            int j = mText.length();
            if(j <= 0)
                return null;
            if(i <= 0)
                return null;
            int k = i;
            if(i > j)
                k = j;
            while(k > 0 && isLetterOrDigit(k - 1) ^ true && isEndBoundary(k) ^ true) 
            {
                i = mImpl.preceding(k);
                k = i;
                if(i == -1)
                    return null;
            }
            i = mImpl.preceding(k);
            if(i == -1 || isStartBoundary(i) ^ true)
                return null;
            else
                return getRange(i, k);
        }

        private static WordTextSegmentIterator sInstance;

        private WordTextSegmentIterator(Locale locale)
        {
            super(locale, null);
        }
    }


    public AccessibilityIterators()
    {
    }
}
