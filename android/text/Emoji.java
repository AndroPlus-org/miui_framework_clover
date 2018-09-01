// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.icu.lang.UCharacter;

public class Emoji
{

    public Emoji()
    {
    }

    public static boolean isEmoji(int i)
    {
        boolean flag;
        if(!isNewEmoji(i))
            flag = UCharacter.hasBinaryProperty(i, 57);
        else
            flag = true;
        return flag;
    }

    public static boolean isEmojiModifier(int i)
    {
        return UCharacter.hasBinaryProperty(i, 59);
    }

    public static boolean isEmojiModifierBase(int i)
    {
        if(i == 0x1f91d || i == 0x1f93c)
            return true;
        while(i == 0x1f91f || 0x1f931 <= i && i <= 0x1f932 || 0x1f9d1 <= i && i <= 0x1f9dd) 
            return true;
        return UCharacter.hasBinaryProperty(i, 60);
    }

    public static boolean isKeycapBase(int i)
    {
        boolean flag = true;
        if(48 > i || i > 57) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = flag;
        if(i != 35)
        {
            flag1 = flag;
            if(i != 42)
                flag1 = false;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isNewEmoji(int i)
    {
        boolean flag;
        flag = true;
        if(i < 0x1f6f7 || i > 0x1f9e6)
            return false;
        if(0x1f6f7 > i || i > 0x1f6f8) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag;
_L4:
        return flag1;
_L2:
        flag1 = flag;
        if(i == 0x1f91f)
            continue; /* Loop/switch isn't completed */
        if(0x1f928 <= i)
        {
            flag1 = flag;
            if(i <= 0x1f92f)
                continue; /* Loop/switch isn't completed */
        }
        if(0x1f931 <= i)
        {
            flag1 = flag;
            if(i <= 0x1f932)
                continue; /* Loop/switch isn't completed */
        }
        flag1 = flag;
        if(i == 0x1f94c)
            continue; /* Loop/switch isn't completed */
        if(0x1f95f <= i)
        {
            flag1 = flag;
            if(i <= 0x1f96b)
                continue; /* Loop/switch isn't completed */
        }
        if(0x1f992 <= i)
        {
            flag1 = flag;
            if(i <= 0x1f997)
                continue; /* Loop/switch isn't completed */
        }
        if(0x1f9d0 <= i)
        {
            flag1 = flag;
            if(i <= 0x1f9e6)
                continue; /* Loop/switch isn't completed */
        }
        flag1 = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean isRegionalIndicatorSymbol(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(0x1f1e6 <= i)
        {
            flag1 = flag;
            if(i <= 0x1f1ff)
                flag1 = true;
        }
        return flag1;
    }

    public static boolean isTagSpecChar(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(0xe0020 <= i)
        {
            flag1 = flag;
            if(i <= 0xe007e)
                flag1 = true;
        }
        return flag1;
    }

    public static int CANCEL_TAG = 0xe007f;
    public static int COMBINING_ENCLOSING_KEYCAP = 8419;
    public static int VARIATION_SELECTOR_16 = 65039;
    public static int ZERO_WIDTH_JOINER = 8205;

}
