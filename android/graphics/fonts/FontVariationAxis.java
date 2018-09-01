// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics.fonts;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FontVariationAxis
{

    public FontVariationAxis(String s, float f)
    {
        if(!isValidTag(s))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal tag pattern: ").append(s).toString());
        } else
        {
            mTag = makeTag(s);
            mTagString = s;
            mStyleValue = f;
            return;
        }
    }

    public static FontVariationAxis[] fromFontVariationSettings(String s)
    {
        if(s == null || s.isEmpty())
            return null;
        ArrayList arraylist = new ArrayList();
        int i = s.length();
        int j = 0;
        while(j < i) 
        {
            char c = s.charAt(j);
            if(!Character.isWhitespace(c))
            {
                while(c != '\'' && c != '"' || i < j + 6 || s.charAt(j + 5) != c) 
                    throw new IllegalArgumentException((new StringBuilder()).append("Tag should be wrapped with double or single quote: ").append(s).toString());
                String s1 = s.substring(j + 1, j + 5);
                int k = j + 6;
                int l = s.indexOf(',', k);
                j = l;
                if(l == -1)
                    j = i;
                float f;
                try
                {
                    f = Float.parseFloat(s.substring(k, j));
                }
                // Misplaced declaration of an exception variable
                catch(String s)
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("Failed to parse float string: ").append(s.getMessage()).toString());
                }
                arraylist.add(new FontVariationAxis(s1, f));
            }
            j++;
        }
        if(arraylist.isEmpty())
            return null;
        else
            return (FontVariationAxis[])arraylist.toArray(new FontVariationAxis[0]);
    }

    private static boolean isValidTag(String s)
    {
        boolean flag;
        if(s != null)
            flag = TAG_PATTERN.matcher(s).matches();
        else
            flag = false;
        return flag;
    }

    private static boolean isValidValueFormat(String s)
    {
        boolean flag;
        if(s != null)
            flag = STYLE_VALUE_PATTERN.matcher(s).matches();
        else
            flag = false;
        return flag;
    }

    public static int makeTag(String s)
    {
        return s.charAt(0) << 24 | s.charAt(1) << 16 | s.charAt(2) << 8 | s.charAt(3);
    }

    public static String toFontVariationSettings(FontVariationAxis afontvariationaxis[])
    {
        if(afontvariationaxis == null || afontvariationaxis.length == 0)
            return "";
        else
            return TextUtils.join(",", afontvariationaxis);
    }

    public int getOpenTypeTagValue()
    {
        return mTag;
    }

    public float getStyleValue()
    {
        return mStyleValue;
    }

    public String getTag()
    {
        return mTagString;
    }

    public String toString()
    {
        return (new StringBuilder()).append("'").append(mTagString).append("' ").append(Float.toString(mStyleValue)).toString();
    }

    private static final Pattern STYLE_VALUE_PATTERN = Pattern.compile("-?(([0-9]+(\\.[0-9]+)?)|(\\.[0-9]+))");
    private static final Pattern TAG_PATTERN = Pattern.compile("[ -~]{4}");
    private final float mStyleValue;
    private final int mTag;
    private final String mTagString;

}
