// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.apache.miui.commons.lang3;


// Referenced classes of package org.apache.miui.commons.lang3:
//            StringUtils

public class CharUtils
{

    public CharUtils()
    {
    }

    public static boolean isAscii(char c)
    {
        boolean flag;
        if(c < '\200')
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isAsciiAlpha(char c)
    {
        boolean flag;
        flag = true;
        break MISSING_BLOCK_LABEL_2;
        if((c < 'A' || c > 'Z') && (c < 'a' || c > 'z'))
            flag = false;
        return flag;
    }

    public static boolean isAsciiAlphaLower(char c)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(c >= 'a')
        {
            flag1 = flag;
            if(c <= 'z')
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isAsciiAlphaUpper(char c)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(c >= 'A')
        {
            flag1 = flag;
            if(c <= 'Z')
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isAsciiAlphanumeric(char c)
    {
        boolean flag = true;
        if(c < 'A' || c > 'Z') goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        if(c >= 'a')
        {
            flag1 = flag;
            if(c <= 'z')
                continue; /* Loop/switch isn't completed */
        }
        if(c >= '0')
        {
            flag1 = flag;
            if(c <= '9')
                continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isAsciiControl(char c)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(c >= ' ')
            if(c == '\177')
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isAsciiNumeric(char c)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(c >= '0')
        {
            flag1 = flag;
            if(c <= '9')
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isAsciiPrintable(char c)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(c >= ' ')
        {
            flag1 = flag;
            if(c < '\177')
                flag1 = true;
        }
        return flag1;
    }

    public static char toChar(Character character)
    {
        if(character == null)
            throw new IllegalArgumentException("The Character must not be null");
        else
            return character.charValue();
    }

    public static char toChar(Character character, char c)
    {
        if(character == null)
            return c;
        else
            return character.charValue();
    }

    public static char toChar(String s)
    {
        if(StringUtils.isEmpty(s))
            throw new IllegalArgumentException("The String must not be empty");
        else
            return s.charAt(0);
    }

    public static char toChar(String s, char c)
    {
        if(StringUtils.isEmpty(s))
            return c;
        else
            return s.charAt(0);
    }

    public static Character toCharacterObject(char c)
    {
        return Character.valueOf(c);
    }

    public static Character toCharacterObject(String s)
    {
        if(StringUtils.isEmpty(s))
            return null;
        else
            return Character.valueOf(s.charAt(0));
    }

    public static int toIntValue(char c)
    {
        if(!isAsciiNumeric(c))
            throw new IllegalArgumentException((new StringBuilder()).append("The character ").append(c).append(" is not in the range '0' - '9'").toString());
        else
            return c - 48;
    }

    public static int toIntValue(char c, int i)
    {
        if(!isAsciiNumeric(c))
            return i;
        else
            return c - 48;
    }

    public static int toIntValue(Character character)
    {
        if(character == null)
            throw new IllegalArgumentException("The character must not be null");
        else
            return toIntValue(character.charValue());
    }

    public static int toIntValue(Character character, int i)
    {
        if(character == null)
            return i;
        else
            return toIntValue(character.charValue(), i);
    }

    public static String toString(char c)
    {
        if(c < '\200')
            return CHAR_STRING_ARRAY[c];
        else
            return new String(new char[] {
                c
            });
    }

    public static String toString(Character character)
    {
        if(character == null)
            return null;
        else
            return toString(character.charValue());
    }

    public static String unicodeEscaped(char c)
    {
        if(c < '\020')
            return (new StringBuilder()).append("\\u000").append(Integer.toHexString(c)).toString();
        if(c < '\u0100')
            return (new StringBuilder()).append("\\u00").append(Integer.toHexString(c)).toString();
        if(c < '\u1000')
            return (new StringBuilder()).append("\\u0").append(Integer.toHexString(c)).toString();
        else
            return (new StringBuilder()).append("\\u").append(Integer.toHexString(c)).toString();
    }

    public static String unicodeEscaped(Character character)
    {
        if(character == null)
            return null;
        else
            return unicodeEscaped(character.charValue());
    }

    private static final String CHAR_STRING_ARRAY[];
    public static final char CR = 13;
    public static final char LF = 10;

    static 
    {
        CHAR_STRING_ARRAY = new String[128];
        char c = '\0';
        for(int i = c; i < CHAR_STRING_ARRAY.length; i = c)
        {
            CHAR_STRING_ARRAY[i] = String.valueOf(i);
            c = (char)(i + 1);
        }

    }
}
