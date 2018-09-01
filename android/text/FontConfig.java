// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;

public final class FontConfig
{
    public static final class Alias
    {

        public String getName()
        {
            return mName;
        }

        public String getToName()
        {
            return mToName;
        }

        public int getWeight()
        {
            return mWeight;
        }

        private final String mName;
        private final String mToName;
        private final int mWeight;

        public Alias(String s, String s1, int i)
        {
            mName = s;
            mToName = s1;
            mWeight = i;
        }
    }

    public static final class Family
    {

        public Font[] getFonts()
        {
            return mFonts;
        }

        public String getLanguage()
        {
            return mLanguage;
        }

        public String getName()
        {
            return mName;
        }

        public int getVariant()
        {
            return mVariant;
        }

        public static final int VARIANT_COMPACT = 1;
        public static final int VARIANT_DEFAULT = 0;
        public static final int VARIANT_ELEGANT = 2;
        private final Font mFonts[];
        private final String mLanguage;
        private final String mName;
        private final int mVariant;

        public Family(String s, Font afont[], String s1, int i)
        {
            mName = s;
            mFonts = afont;
            mLanguage = s1;
            mVariant = i;
        }
    }

    public static final class Font
    {

        public FontVariationAxis[] getAxes()
        {
            return mAxes;
        }

        public String getFontName()
        {
            return mFontName;
        }

        public int getTtcIndex()
        {
            return mTtcIndex;
        }

        public Uri getUri()
        {
            return mUri;
        }

        public int getWeight()
        {
            return mWeight;
        }

        public boolean isItalic()
        {
            return mIsItalic;
        }

        public void setUri(Uri uri)
        {
            mUri = uri;
        }

        private final FontVariationAxis mAxes[];
        private final String mFontName;
        private final boolean mIsItalic;
        private final int mTtcIndex;
        private Uri mUri;
        private final int mWeight;

        public Font(String s, int i, FontVariationAxis afontvariationaxis[], int j, boolean flag)
        {
            mFontName = s;
            mTtcIndex = i;
            mAxes = afontvariationaxis;
            mWeight = j;
            mIsItalic = flag;
        }
    }


    public FontConfig(Family afamily[], Alias aalias[])
    {
        mFamilies = afamily;
        mAliases = aalias;
    }

    public Alias[] getAliases()
    {
        return mAliases;
    }

    public Family[] getFamilies()
    {
        return mFamilies;
    }

    private final Alias mAliases[];
    private final Family mFamilies[];
}
