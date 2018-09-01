// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.media:
//            TtmlNode

final class TtmlUtils
{

    private TtmlUtils()
    {
    }

    public static String applyDefaultSpacePolicy(String s)
    {
        return applySpacePolicy(s, true);
    }

    public static String applySpacePolicy(String s, boolean flag)
    {
        s = s.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n");
        if(flag)
            s = s.replaceAll("\n", " ");
        return s.replaceAll("[ \t\\x0B\f\r]+", " ");
    }

    public static String extractText(TtmlNode ttmlnode, long l, long l1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        extractText(ttmlnode, l, l1, stringbuilder, false);
        return stringbuilder.toString().replaceAll("\n$", "");
    }

    private static void extractText(TtmlNode ttmlnode, long l, long l1, StringBuilder stringbuilder, boolean flag)
    {
        if(!ttmlnode.mName.equals("#pcdata") || !flag) goto _L2; else goto _L1
_L1:
        stringbuilder.append(ttmlnode.mText);
_L4:
        return;
_L2:
        if(ttmlnode.mName.equals("br") && flag)
            stringbuilder.append("\n");
        else
        if(!ttmlnode.mName.equals("metadata") && ttmlnode.isActive(l, l1))
        {
            boolean flag1 = ttmlnode.mName.equals("p");
            int i = stringbuilder.length();
            int j = 0;
            while(j < ttmlnode.mChildren.size()) 
            {
                TtmlNode ttmlnode1 = (TtmlNode)ttmlnode.mChildren.get(j);
                boolean flag2;
                if(!flag1)
                    flag2 = flag;
                else
                    flag2 = true;
                extractText(ttmlnode1, l, l1, stringbuilder, flag2);
                j++;
            }
            if(flag1 && i != stringbuilder.length())
                stringbuilder.append("\n");
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String extractTtmlFragment(TtmlNode ttmlnode, long l, long l1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        extractTtmlFragment(ttmlnode, l, l1, stringbuilder);
        return stringbuilder.toString();
    }

    private static void extractTtmlFragment(TtmlNode ttmlnode, long l, long l1, StringBuilder stringbuilder)
    {
        if(!ttmlnode.mName.equals("#pcdata")) goto _L2; else goto _L1
_L1:
        stringbuilder.append(ttmlnode.mText);
_L4:
        return;
_L2:
        if(ttmlnode.mName.equals("br"))
            stringbuilder.append("<br/>");
        else
        if(ttmlnode.isActive(l, l1))
        {
            stringbuilder.append("<");
            stringbuilder.append(ttmlnode.mName);
            stringbuilder.append(ttmlnode.mAttributes);
            stringbuilder.append(">");
            for(int i = 0; i < ttmlnode.mChildren.size(); i++)
                extractTtmlFragment((TtmlNode)ttmlnode.mChildren.get(i), l, l1, stringbuilder);

            stringbuilder.append("</");
            stringbuilder.append(ttmlnode.mName);
            stringbuilder.append(">");
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static long parseTimeExpression(String s, int i, int j, int k)
        throws NumberFormatException
    {
        Matcher matcher;
        matcher = CLOCK_TIME.matcher(s);
        if(matcher.matches())
        {
            double d = Long.parseLong(matcher.group(1)) * 3600L;
            double d1 = Long.parseLong(matcher.group(2)) * 60L;
            double d2 = Long.parseLong(matcher.group(3));
            s = matcher.group(4);
            double d3;
            double d5;
            double d7;
            if(s != null)
                d3 = Double.parseDouble(s);
            else
                d3 = 0.0D;
            s = matcher.group(5);
            if(s != null)
                d5 = (double)Long.parseLong(s) / (double)i;
            else
                d5 = 0.0D;
            s = matcher.group(6);
            if(s != null)
                d7 = (double)Long.parseLong(s) / (double)j / (double)i;
            else
                d7 = 0.0D;
            return (long)(1000D * (d + d1 + d2 + d3 + d5 + d7));
        }
        matcher = OFFSET_TIME.matcher(s);
        if(!matcher.matches()) goto _L2; else goto _L1
_L1:
        double d6;
        d6 = Double.parseDouble(matcher.group(1));
        s = matcher.group(2);
        if(!s.equals("h")) goto _L4; else goto _L3
_L3:
        double d4 = d6 * 3600000000D;
_L5:
        return (long)d4;
_L4:
        if(s.equals("m"))
            d4 = d6 * 60000000D;
        else
        if(s.equals("s"))
            d4 = d6 * 1000000D;
        else
        if(s.equals("ms"))
            d4 = d6 * 1000D;
        else
        if(s.equals("f"))
        {
            d4 = (d6 / (double)i) * 1000000D;
        } else
        {
            d4 = d6;
            if(s.equals("t"))
                d4 = (d6 / (double)k) * 1000000D;
        }
        if(true) goto _L5; else goto _L2
_L2:
        throw new NumberFormatException((new StringBuilder()).append("Malformed time expression : ").append(s).toString());
    }

    public static final String ATTR_BEGIN = "begin";
    public static final String ATTR_DURATION = "dur";
    public static final String ATTR_END = "end";
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    public static final long INVALID_TIMESTAMP = 0x7fffffffffffffffL;
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    public static final String PCDATA = "#pcdata";
    public static final String TAG_BODY = "body";
    public static final String TAG_BR = "br";
    public static final String TAG_DIV = "div";
    public static final String TAG_HEAD = "head";
    public static final String TAG_LAYOUT = "layout";
    public static final String TAG_METADATA = "metadata";
    public static final String TAG_P = "p";
    public static final String TAG_REGION = "region";
    public static final String TAG_SMPTE_DATA = "smpte:data";
    public static final String TAG_SMPTE_IMAGE = "smpte:image";
    public static final String TAG_SMPTE_INFORMATION = "smpte:information";
    public static final String TAG_SPAN = "span";
    public static final String TAG_STYLE = "style";
    public static final String TAG_STYLING = "styling";
    public static final String TAG_TT = "tt";

}
