// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import java.util.Locale;

// Referenced classes of package android.text:
//            TextDirectionHeuristics, TextUtils, TextDirectionHeuristic, SpannableStringBuilder, 
//            Emoji

public final class BidiFormatter
{
    public static final class Builder
    {

        private void initialize(boolean flag)
        {
            mIsRtlContext = flag;
            mTextDirectionHeuristic = BidiFormatter._2D_get0();
            mFlags = 2;
        }

        public BidiFormatter build()
        {
            if(mFlags == 2 && mTextDirectionHeuristic == BidiFormatter._2D_get0())
                return BidiFormatter._2D_wrap0(mIsRtlContext);
            else
                return new BidiFormatter(mIsRtlContext, mFlags, mTextDirectionHeuristic, null);
        }

        public Builder setTextDirectionHeuristic(TextDirectionHeuristic textdirectionheuristic)
        {
            mTextDirectionHeuristic = textdirectionheuristic;
            return this;
        }

        public Builder stereoReset(boolean flag)
        {
            if(flag)
                mFlags = mFlags | 2;
            else
                mFlags = mFlags & -3;
            return this;
        }

        private int mFlags;
        private boolean mIsRtlContext;
        private TextDirectionHeuristic mTextDirectionHeuristic;

        public Builder()
        {
            initialize(BidiFormatter._2D_wrap1(Locale.getDefault()));
        }

        public Builder(Locale locale)
        {
            initialize(BidiFormatter._2D_wrap1(locale));
        }

        public Builder(boolean flag)
        {
            initialize(flag);
        }
    }

    private static class DirectionalityEstimator
    {

        private static byte getCachedDirectionality(char c)
        {
            byte byte0;
            if(c < '\u0700')
            {
                c = DIR_TYPE_CACHE[c];
                byte0 = c;
            } else
            {
                c = getDirectionality(c);
                byte0 = c;
            }
            return byte0;
        }

        private static byte getDirectionality(int i)
        {
            if(Emoji.isNewEmoji(i))
                return 13;
            else
                return Character.getDirectionality(i);
        }

        private byte skipEntityBackward()
        {
            int i = charIndex;
            do
            {
                if(charIndex <= 0)
                    break;
                CharSequence charsequence = text;
                int j = charIndex - 1;
                charIndex = j;
                lastChar = charsequence.charAt(j);
                if(lastChar == '&')
                    return 12;
            } while(lastChar != ';');
            charIndex = i;
            lastChar = (char)59;
            return 13;
        }

        private byte skipEntityForward()
        {
            int i;
            do
            {
                if(charIndex >= length)
                    break;
                CharSequence charsequence = text;
                i = charIndex;
                charIndex = i + 1;
                i = charsequence.charAt(i);
                lastChar = (char)i;
            } while(i != 59);
            return 12;
        }

        private byte skipTagBackward()
        {
            int i = charIndex;
            do
            {
                do
                {
                    if(charIndex > 0)
                    {
                        CharSequence charsequence = text;
                        int j = charIndex - 1;
                        charIndex = j;
                        lastChar = charsequence.charAt(j);
                        if(lastChar == '<')
                            return 12;
                        if(lastChar != '>')
                            continue;
                    }
                    charIndex = i;
                    lastChar = (char)62;
                    return 13;
                } while(lastChar != '"' && lastChar != '\'');
                char c = lastChar;
                int k;
                do
                {
                    if(charIndex <= 0)
                        break;
                    CharSequence charsequence1 = text;
                    k = charIndex - 1;
                    charIndex = k;
                    k = charsequence1.charAt(k);
                    lastChar = (char)k;
                } while(k != c);
            } while(true);
        }

        private byte skipTagForward()
        {
            int i = charIndex;
            while(charIndex < length) 
            {
                CharSequence charsequence = text;
                int j = charIndex;
                charIndex = j + 1;
                lastChar = charsequence.charAt(j);
                if(lastChar == '>')
                    return 12;
                if(lastChar != '"' && lastChar != '\'')
                    break;
                j = lastChar;
                int k;
                do
                {
                    if(charIndex >= length)
                        break;
                    CharSequence charsequence1 = text;
                    k = charIndex;
                    charIndex = k + 1;
                    k = charsequence1.charAt(k);
                    lastChar = (char)k;
                } while(k != j);
            }
            charIndex = i;
            lastChar = (char)60;
            return 13;
        }

        byte dirTypeBackward()
        {
            byte byte0;
            byte byte2;
            lastChar = text.charAt(charIndex - 1);
            if(Character.isLowSurrogate(lastChar))
            {
                int i = Character.codePointBefore(text, charIndex);
                charIndex = charIndex - Character.charCount(i);
                return getDirectionality(i);
            }
            charIndex = charIndex - 1;
            byte0 = getCachedDirectionality(lastChar);
            byte2 = byte0;
            if(!isHtml) goto _L2; else goto _L1
_L1:
            if(lastChar != '>') goto _L4; else goto _L3
_L3:
            byte0 = skipTagBackward();
            byte2 = byte0;
_L2:
            return byte2;
_L4:
            byte2 = byte0;
            if(lastChar == ';')
            {
                byte byte1 = skipEntityBackward();
                byte2 = byte1;
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        byte dirTypeForward()
        {
            byte byte0;
            byte byte2;
            lastChar = text.charAt(charIndex);
            if(Character.isHighSurrogate(lastChar))
            {
                int i = Character.codePointAt(text, charIndex);
                charIndex = charIndex + Character.charCount(i);
                return getDirectionality(i);
            }
            charIndex = charIndex + 1;
            byte0 = getCachedDirectionality(lastChar);
            byte2 = byte0;
            if(!isHtml) goto _L2; else goto _L1
_L1:
            if(lastChar != '<') goto _L4; else goto _L3
_L3:
            byte0 = skipTagForward();
            byte2 = byte0;
_L2:
            return byte2;
_L4:
            byte2 = byte0;
            if(lastChar == '&')
            {
                byte byte1 = skipEntityForward();
                byte2 = byte1;
            }
            if(true) goto _L2; else goto _L5
_L5:
        }

        int getEntryDir()
        {
            charIndex = 0;
            int i = 0;
            byte byte0 = 0;
            int j = 0;
            do
                if(charIndex < length && j == 0)
                {
                    switch(dirTypeForward())
                    {
                    case 3: // '\003'
                    case 4: // '\004'
                    case 5: // '\005'
                    case 6: // '\006'
                    case 7: // '\007'
                    case 8: // '\b'
                    case 10: // '\n'
                    case 11: // '\013'
                    case 12: // '\f'
                    case 13: // '\r'
                    default:
                        j = i;
                        break;

                    case 14: // '\016'
                    case 15: // '\017'
                        i++;
                        byte0 = -1;
                        break;

                    case 16: // '\020'
                    case 17: // '\021'
                        i++;
                        byte0 = 1;
                        break;

                    case 18: // '\022'
                        i--;
                        byte0 = 0;
                        break;

                    case 0: // '\0'
                        if(i == 0)
                            return -1;
                        j = i;
                        break;

                    case 1: // '\001'
                    case 2: // '\002'
                        if(i == 0)
                            return 1;
                        j = i;
                        break;

                    case 9: // '\t'
                        break;
                    }
                } else
                {
                    if(j == 0)
                        return 0;
                    if(byte0 != 0)
                        return byte0;
                    do
                        if(charIndex > 0)
                            switch(dirTypeBackward())
                            {
                            case 14: // '\016'
                            case 15: // '\017'
                                if(j == i)
                                    return -1;
                                i--;
                                break;

                            case 16: // '\020'
                            case 17: // '\021'
                                if(j == i)
                                    return 1;
                                i--;
                                break;

                            case 18: // '\022'
                                i++;
                                break;
                            }
                        else
                            return 0;
                    while(true);
                }
            while(true);
        }

        int getExitDir()
        {
            charIndex = length;
            int i = 0;
            int j = 0;
            do
            {
                if(charIndex <= 0)
                    break;
                switch(dirTypeBackward())
                {
                case 3: // '\003'
                case 4: // '\004'
                case 5: // '\005'
                case 6: // '\006'
                case 7: // '\007'
                case 8: // '\b'
                case 10: // '\n'
                case 11: // '\013'
                case 12: // '\f'
                case 13: // '\r'
                default:
                    if(j == 0)
                        j = i;
                    break;

                case 0: // '\0'
                    if(i == 0)
                        return -1;
                    if(j == 0)
                        j = i;
                    break;

                case 14: // '\016'
                case 15: // '\017'
                    if(j == i)
                        return -1;
                    i--;
                    break;

                case 1: // '\001'
                case 2: // '\002'
                    if(i == 0)
                        return 1;
                    if(j == 0)
                        j = i;
                    break;

                case 16: // '\020'
                case 17: // '\021'
                    if(j == i)
                        return 1;
                    i--;
                    break;

                case 18: // '\022'
                    i++;
                    break;

                case 9: // '\t'
                    break;
                }
            } while(true);
            return 0;
        }

        private static final byte DIR_TYPE_CACHE[];
        private static final int DIR_TYPE_CACHE_SIZE = 1792;
        private int charIndex;
        private final boolean isHtml;
        private char lastChar;
        private final int length;
        private final CharSequence text;

        static 
        {
            DIR_TYPE_CACHE = new byte[1792];
            for(int i = 0; i < 1792; i++)
                DIR_TYPE_CACHE[i] = Character.getDirectionality(i);

        }

        DirectionalityEstimator(CharSequence charsequence, boolean flag)
        {
            text = charsequence;
            isHtml = flag;
            length = charsequence.length();
        }
    }


    static TextDirectionHeuristic _2D_get0()
    {
        return DEFAULT_TEXT_DIRECTION_HEURISTIC;
    }

    static BidiFormatter _2D_wrap0(boolean flag)
    {
        return getDefaultInstanceFromContext(flag);
    }

    static boolean _2D_wrap1(Locale locale)
    {
        return isRtlLocale(locale);
    }

    private BidiFormatter(boolean flag, int i, TextDirectionHeuristic textdirectionheuristic)
    {
        mIsRtlContext = flag;
        mFlags = i;
        mDefaultTextDirectionHeuristic = textdirectionheuristic;
    }

    BidiFormatter(boolean flag, int i, TextDirectionHeuristic textdirectionheuristic, BidiFormatter bidiformatter)
    {
        this(flag, i, textdirectionheuristic);
    }

    private static BidiFormatter getDefaultInstanceFromContext(boolean flag)
    {
        BidiFormatter bidiformatter;
        if(flag)
            bidiformatter = DEFAULT_RTL_INSTANCE;
        else
            bidiformatter = DEFAULT_LTR_INSTANCE;
        return bidiformatter;
    }

    private static int getEntryDir(CharSequence charsequence)
    {
        return (new DirectionalityEstimator(charsequence, false)).getEntryDir();
    }

    private static int getExitDir(CharSequence charsequence)
    {
        return (new DirectionalityEstimator(charsequence, false)).getExitDir();
    }

    public static BidiFormatter getInstance()
    {
        return getDefaultInstanceFromContext(isRtlLocale(Locale.getDefault()));
    }

    public static BidiFormatter getInstance(Locale locale)
    {
        return getDefaultInstanceFromContext(isRtlLocale(locale));
    }

    public static BidiFormatter getInstance(boolean flag)
    {
        return getDefaultInstanceFromContext(flag);
    }

    private static boolean isRtlLocale(Locale locale)
    {
        boolean flag = true;
        if(TextUtils.getLayoutDirectionFromLocale(locale) != 1)
            flag = false;
        return flag;
    }

    public boolean getStereoReset()
    {
        boolean flag = false;
        if((mFlags & 2) != 0)
            flag = true;
        return flag;
    }

    public boolean isRtl(CharSequence charsequence)
    {
        return mDefaultTextDirectionHeuristic.isRtl(charsequence, 0, charsequence.length());
    }

    public boolean isRtl(String s)
    {
        return isRtl(((CharSequence) (s)));
    }

    public boolean isRtlContext()
    {
        return mIsRtlContext;
    }

    public String markAfter(CharSequence charsequence, TextDirectionHeuristic textdirectionheuristic)
    {
        boolean flag = textdirectionheuristic.isRtl(charsequence, 0, charsequence.length());
        if(!mIsRtlContext && (flag || getExitDir(charsequence) == 1))
            return LRM_STRING;
        if(mIsRtlContext && (!flag || getExitDir(charsequence) == -1))
            return RLM_STRING;
        else
            return "";
    }

    public String markBefore(CharSequence charsequence, TextDirectionHeuristic textdirectionheuristic)
    {
        boolean flag = textdirectionheuristic.isRtl(charsequence, 0, charsequence.length());
        if(!mIsRtlContext && (flag || getEntryDir(charsequence) == 1))
            return LRM_STRING;
        if(mIsRtlContext && (!flag || getEntryDir(charsequence) == -1))
            return RLM_STRING;
        else
            return "";
    }

    public CharSequence unicodeWrap(CharSequence charsequence)
    {
        return unicodeWrap(charsequence, mDefaultTextDirectionHeuristic, true);
    }

    public CharSequence unicodeWrap(CharSequence charsequence, TextDirectionHeuristic textdirectionheuristic)
    {
        return unicodeWrap(charsequence, textdirectionheuristic, true);
    }

    public CharSequence unicodeWrap(CharSequence charsequence, TextDirectionHeuristic textdirectionheuristic, boolean flag)
    {
        if(charsequence == null)
            return null;
        boolean flag1 = textdirectionheuristic.isRtl(charsequence, 0, charsequence.length());
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
        if(getStereoReset() && flag)
        {
            char c;
            if(flag1)
                textdirectionheuristic = TextDirectionHeuristics.RTL;
            else
                textdirectionheuristic = TextDirectionHeuristics.LTR;
            spannablestringbuilder.append(markBefore(charsequence, textdirectionheuristic));
        }
        if(flag1 != mIsRtlContext)
        {
            char c2;
            if(flag1)
            {
                c = '\u202B';
                c2 = c;
            } else
            {
                char c1 = '\u202A';
                c2 = c1;
            }
            spannablestringbuilder.append(c2);
            spannablestringbuilder.append(charsequence);
            spannablestringbuilder.append('\u202C');
        } else
        {
            spannablestringbuilder.append(charsequence);
        }
        if(flag)
        {
            if(flag1)
                textdirectionheuristic = TextDirectionHeuristics.RTL;
            else
                textdirectionheuristic = TextDirectionHeuristics.LTR;
            spannablestringbuilder.append(markAfter(charsequence, textdirectionheuristic));
        }
        return spannablestringbuilder;
    }

    public CharSequence unicodeWrap(CharSequence charsequence, boolean flag)
    {
        return unicodeWrap(charsequence, mDefaultTextDirectionHeuristic, flag);
    }

    public String unicodeWrap(String s)
    {
        return unicodeWrap(s, mDefaultTextDirectionHeuristic, true);
    }

    public String unicodeWrap(String s, TextDirectionHeuristic textdirectionheuristic)
    {
        return unicodeWrap(s, textdirectionheuristic, true);
    }

    public String unicodeWrap(String s, TextDirectionHeuristic textdirectionheuristic, boolean flag)
    {
        if(s == null)
            return null;
        else
            return unicodeWrap(((CharSequence) (s)), textdirectionheuristic, flag).toString();
    }

    public String unicodeWrap(String s, boolean flag)
    {
        return unicodeWrap(s, mDefaultTextDirectionHeuristic, flag);
    }

    private static final int DEFAULT_FLAGS = 2;
    private static final BidiFormatter DEFAULT_LTR_INSTANCE;
    private static final BidiFormatter DEFAULT_RTL_INSTANCE;
    private static TextDirectionHeuristic DEFAULT_TEXT_DIRECTION_HEURISTIC;
    private static final int DIR_LTR = -1;
    private static final int DIR_RTL = 1;
    private static final int DIR_UNKNOWN = 0;
    private static final String EMPTY_STRING = "";
    private static final int FLAG_STEREO_RESET = 2;
    private static final char LRE = 8234;
    private static final char LRM = 8206;
    private static final String LRM_STRING = Character.toString('\u200E');
    private static final char PDF = 8236;
    private static final char RLE = 8235;
    private static final char RLM = 8207;
    private static final String RLM_STRING = Character.toString('\u200F');
    private final TextDirectionHeuristic mDefaultTextDirectionHeuristic;
    private final int mFlags;
    private final boolean mIsRtlContext;

    static 
    {
        DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, DEFAULT_TEXT_DIRECTION_HEURISTIC);
    }
}
