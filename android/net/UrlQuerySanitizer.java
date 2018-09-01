// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import java.util.*;

public class UrlQuerySanitizer
{
    public static class IllegalCharacterValueSanitizer
        implements ValueSanitizer
    {

        private boolean characterIsLegal(char c)
        {
            boolean flag = true;
            boolean flag1 = true;
            boolean flag2 = true;
            boolean flag3 = true;
            boolean flag4 = true;
            boolean flag5 = true;
            boolean flag6 = true;
            boolean flag7 = true;
            boolean flag8 = true;
            boolean flag9 = true;
            switch(c)
            {
            default:
                if((c < ' ' || c >= '\177') && (c < '\200' || (mFlags & 4) == 0))
                    flag9 = false;
                return flag9;

            case 32: // ' '
                if((mFlags & 1) != 0)
                    flag9 = flag;
                else
                    flag9 = false;
                return flag9;

            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
                if((mFlags & 2) != 0)
                    flag9 = flag1;
                else
                    flag9 = false;
                return flag9;

            case 34: // '"'
                if((mFlags & 8) != 0)
                    flag9 = flag2;
                else
                    flag9 = false;
                return flag9;

            case 39: // '\''
                if((mFlags & 0x10) != 0)
                    flag9 = flag3;
                else
                    flag9 = false;
                return flag9;

            case 60: // '<'
                if((mFlags & 0x20) != 0)
                    flag9 = flag4;
                else
                    flag9 = false;
                return flag9;

            case 62: // '>'
                if((mFlags & 0x40) != 0)
                    flag9 = flag5;
                else
                    flag9 = false;
                return flag9;

            case 38: // '&'
                if((mFlags & 0x80) != 0)
                    flag9 = flag6;
                else
                    flag9 = false;
                return flag9;

            case 37: // '%'
                if((mFlags & 0x100) != 0)
                    flag9 = flag7;
                else
                    flag9 = false;
                return flag9;

            case 0: // '\0'
                if((mFlags & 0x200) != 0)
                    flag9 = flag8;
                else
                    flag9 = false;
                return flag9;
            }
        }

        private boolean isWhitespace(char c)
        {
            switch(c)
            {
            default:
                return false;

            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 32: // ' '
                return true;
            }
        }

        private String trimWhitespace(String s)
        {
            int i = 0;
            int j = s.length() - 1;
            int k = j;
            int l;
            do
            {
                l = k;
                if(i > j)
                    break;
                l = k;
                if(!isWhitespace(s.charAt(i)))
                    break;
                i++;
            } while(true);
            for(; l >= i && isWhitespace(s.charAt(l)); l--);
            if(i == 0 && l == j)
                return s;
            else
                return s.substring(i, l + 1);
        }

        public String sanitize(String s)
        {
            if(s == null)
                return null;
            int i = s.length();
            if((mFlags & 0x400) != 0 && i >= MIN_SCRIPT_PREFIX_LENGTH)
            {
                String s1 = s.toLowerCase(Locale.ROOT);
                if(s1.startsWith("javascript:") || s1.startsWith("vbscript:"))
                    return "";
            }
            String s2 = s;
            if((mFlags & 3) == 0)
            {
                s2 = trimWhitespace(s);
                i = s2.length();
            }
            s = new StringBuilder(i);
            int j = 0;
            while(j < i) 
            {
                char c = s2.charAt(j);
                char c1 = c;
                if(!characterIsLegal(c))
                    if((mFlags & 1) != 0)
                    {
                        byte byte0 = 32;
                        c1 = byte0;
                    } else
                    {
                        byte byte1 = 95;
                        c1 = byte1;
                    }
                s.append(c1);
                j++;
            }
            return s.toString();
        }

        public static final int ALL_BUT_NUL_AND_ANGLE_BRACKETS_LEGAL = 1439;
        public static final int ALL_BUT_NUL_LEGAL = 1535;
        public static final int ALL_BUT_WHITESPACE_LEGAL = 1532;
        public static final int ALL_ILLEGAL = 0;
        public static final int ALL_OK = 2047;
        public static final int ALL_WHITESPACE_OK = 3;
        public static final int AMP_AND_SPACE_LEGAL = 129;
        public static final int AMP_LEGAL = 128;
        public static final int AMP_OK = 128;
        public static final int DQUOTE_OK = 8;
        public static final int GT_OK = 64;
        private static final String JAVASCRIPT_PREFIX = "javascript:";
        public static final int LT_OK = 32;
        private static final int MIN_SCRIPT_PREFIX_LENGTH = Math.min("javascript:".length(), "vbscript:".length());
        public static final int NON_7_BIT_ASCII_OK = 4;
        public static final int NUL_OK = 512;
        public static final int OTHER_WHITESPACE_OK = 2;
        public static final int PCT_OK = 256;
        public static final int SCRIPT_URL_OK = 1024;
        public static final int SPACE_LEGAL = 1;
        public static final int SPACE_OK = 1;
        public static final int SQUOTE_OK = 16;
        public static final int URL_AND_SPACE_LEGAL = 405;
        public static final int URL_LEGAL = 404;
        private static final String VBSCRIPT_PREFIX = "vbscript:";
        private int mFlags;


        public IllegalCharacterValueSanitizer(int i)
        {
            mFlags = i;
        }
    }

    public class ParameterValuePair
    {

        public String mParameter;
        public String mValue;
        final UrlQuerySanitizer this$0;

        public ParameterValuePair(String s, String s1)
        {
            this$0 = UrlQuerySanitizer.this;
            super();
            mParameter = s;
            mValue = s1;
        }
    }

    public static interface ValueSanitizer
    {

        public abstract String sanitize(String s);
    }


    public UrlQuerySanitizer()
    {
        mSanitizers = new HashMap();
        mEntries = new HashMap();
        mEntriesList = new ArrayList();
        mUnregisteredParameterValueSanitizer = getAllIllegal();
    }

    public UrlQuerySanitizer(String s)
    {
        mSanitizers = new HashMap();
        mEntries = new HashMap();
        mEntriesList = new ArrayList();
        mUnregisteredParameterValueSanitizer = getAllIllegal();
        setAllowUnregisteredParamaters(true);
        parseUrl(s);
    }

    public static final ValueSanitizer getAllButNulAndAngleBracketsLegal()
    {
        return sAllButNulAndAngleBracketsLegal;
    }

    public static final ValueSanitizer getAllButNulLegal()
    {
        return sAllButNulLegal;
    }

    public static final ValueSanitizer getAllButWhitespaceLegal()
    {
        return sAllButWhitespaceLegal;
    }

    public static final ValueSanitizer getAllIllegal()
    {
        return sAllIllegal;
    }

    public static final ValueSanitizer getAmpAndSpaceLegal()
    {
        return sAmpAndSpaceLegal;
    }

    public static final ValueSanitizer getAmpLegal()
    {
        return sAmpLegal;
    }

    public static final ValueSanitizer getSpaceLegal()
    {
        return sSpaceLegal;
    }

    public static final ValueSanitizer getUrlAndSpaceLegal()
    {
        return sUrlAndSpaceLegal;
    }

    public static final ValueSanitizer getUrlLegal()
    {
        return sURLLegal;
    }

    protected void addSanitizedEntry(String s, String s1)
    {
        mEntriesList.add(new ParameterValuePair(s, s1));
        if(mPreferFirstRepeatedParameter && mEntries.containsKey(s))
        {
            return;
        } else
        {
            mEntries.put(s, s1);
            return;
        }
    }

    protected void clear()
    {
        mEntries.clear();
        mEntriesList.clear();
    }

    protected int decodeHexDigit(char c)
    {
        if(c >= '0' && c <= '9')
            return c - 48;
        if(c >= 'A' && c <= 'F')
            return (c - 65) + 10;
        if(c >= 'a' && c <= 'f')
            return (c - 97) + 10;
        else
            return -1;
    }

    public boolean getAllowUnregisteredParamaters()
    {
        return mAllowUnregisteredParamaters;
    }

    public ValueSanitizer getEffectiveValueSanitizer(String s)
    {
        ValueSanitizer valuesanitizer = getValueSanitizer(s);
        s = valuesanitizer;
        if(valuesanitizer == null)
        {
            s = valuesanitizer;
            if(mAllowUnregisteredParamaters)
                s = getUnregisteredParameterValueSanitizer();
        }
        return s;
    }

    public List getParameterList()
    {
        return mEntriesList;
    }

    public Set getParameterSet()
    {
        return mEntries.keySet();
    }

    public boolean getPreferFirstRepeatedParameter()
    {
        return mPreferFirstRepeatedParameter;
    }

    public ValueSanitizer getUnregisteredParameterValueSanitizer()
    {
        return mUnregisteredParameterValueSanitizer;
    }

    public String getValue(String s)
    {
        return (String)mEntries.get(s);
    }

    public ValueSanitizer getValueSanitizer(String s)
    {
        return (ValueSanitizer)mSanitizers.get(s);
    }

    public boolean hasParameter(String s)
    {
        return mEntries.containsKey(s);
    }

    protected boolean isHexDigit(char c)
    {
        boolean flag = false;
        if(decodeHexDigit(c) >= 0)
            flag = true;
        return flag;
    }

    protected void parseEntry(String s, String s1)
    {
        s = unescape(s);
        ValueSanitizer valuesanitizer = getEffectiveValueSanitizer(s);
        if(valuesanitizer == null)
        {
            return;
        } else
        {
            addSanitizedEntry(s, valuesanitizer.sanitize(unescape(s1)));
            return;
        }
    }

    public void parseQuery(String s)
    {
        clear();
        s = new StringTokenizer(s, "&");
        do
        {
            if(!s.hasMoreElements())
                break;
            String s1 = s.nextToken();
            if(s1.length() > 0)
            {
                int i = s1.indexOf('=');
                if(i < 0)
                    parseEntry(s1, "");
                else
                    parseEntry(s1.substring(0, i), s1.substring(i + 1));
            }
        } while(true);
    }

    public void parseUrl(String s)
    {
        int i = s.indexOf('?');
        if(i >= 0)
            s = s.substring(i + 1);
        else
            s = "";
        parseQuery(s);
    }

    public void registerParameter(String s, ValueSanitizer valuesanitizer)
    {
        if(valuesanitizer == null)
            mSanitizers.remove(s);
        mSanitizers.put(s, valuesanitizer);
    }

    public void registerParameters(String as[], ValueSanitizer valuesanitizer)
    {
        int i = as.length;
        for(int j = 0; j < i; j++)
            mSanitizers.put(as[j], valuesanitizer);

    }

    public void setAllowUnregisteredParamaters(boolean flag)
    {
        mAllowUnregisteredParamaters = flag;
    }

    public void setPreferFirstRepeatedParameter(boolean flag)
    {
        mPreferFirstRepeatedParameter = flag;
    }

    public void setUnregisteredParameterValueSanitizer(ValueSanitizer valuesanitizer)
    {
        mUnregisteredParameterValueSanitizer = valuesanitizer;
    }

    public String unescape(String s)
    {
        int i = s.indexOf('%');
        int l = i;
        if(i < 0)
        {
            int j = s.indexOf('+');
            l = j;
            if(j < 0)
                return s;
        }
        int i1 = s.length();
        StringBuilder stringbuilder = new StringBuilder(i1);
        stringbuilder.append(s.substring(0, l));
        while(l < i1) 
        {
            char c = s.charAt(l);
            int k;
            char c2;
            if(c == '+')
            {
                c = ' ';
                k = l;
                c2 = c;
            } else
            {
                c2 = c;
                k = l;
                if(c == '%')
                {
                    c2 = c;
                    k = l;
                    if(l + 2 < i1)
                    {
                        char c3 = s.charAt(l + 1);
                        char c4 = s.charAt(l + 2);
                        c2 = c;
                        k = l;
                        if(isHexDigit(c3))
                        {
                            c2 = c;
                            k = l;
                            if(isHexDigit(c4))
                            {
                                char c1 = (char)(decodeHexDigit(c3) * 16 + decodeHexDigit(c4));
                                k = l + 2;
                                c2 = c1;
                            }
                        }
                    }
                }
            }
            stringbuilder.append(c2);
            l = k + 1;
        }
        return stringbuilder.toString();
    }

    private static final ValueSanitizer sAllButNulAndAngleBracketsLegal = new IllegalCharacterValueSanitizer(1439);
    private static final ValueSanitizer sAllButNulLegal = new IllegalCharacterValueSanitizer(1535);
    private static final ValueSanitizer sAllButWhitespaceLegal = new IllegalCharacterValueSanitizer(1532);
    private static final ValueSanitizer sAllIllegal = new IllegalCharacterValueSanitizer(0);
    private static final ValueSanitizer sAmpAndSpaceLegal = new IllegalCharacterValueSanitizer(129);
    private static final ValueSanitizer sAmpLegal = new IllegalCharacterValueSanitizer(128);
    private static final ValueSanitizer sSpaceLegal = new IllegalCharacterValueSanitizer(1);
    private static final ValueSanitizer sURLLegal = new IllegalCharacterValueSanitizer(404);
    private static final ValueSanitizer sUrlAndSpaceLegal = new IllegalCharacterValueSanitizer(405);
    private boolean mAllowUnregisteredParamaters;
    private final HashMap mEntries;
    private final ArrayList mEntriesList;
    private boolean mPreferFirstRepeatedParameter;
    private final HashMap mSanitizers;
    private ValueSanitizer mUnregisteredParameterValueSanitizer;

}
