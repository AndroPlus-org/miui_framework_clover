// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.app.ActivityThread;
import android.app.Application;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ParagraphStyle;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.*;

// Referenced classes of package android.text:
//            Spanned, HtmlToSpannedConverter, TextDirectionHeuristics, TextDirectionHeuristic, 
//            TextUtils, Editable

public class Html
{
    private static class HtmlParser
    {

        static HTMLSchema _2D_get0()
        {
            return schema;
        }

        private static final HTMLSchema schema = new HTMLSchema();


        private HtmlParser()
        {
        }
    }

    public static interface ImageGetter
    {

        public abstract Drawable getDrawable(String s);
    }

    public static interface TagHandler
    {

        public abstract void handleTag(boolean flag, String s, Editable editable, XMLReader xmlreader);
    }


    private Html()
    {
    }

    private static void encodeTextAlignmentByDiv(StringBuilder stringbuilder, Spanned spanned, int i)
    {
        int j = spanned.length();
        int l;
        for(int k = 0; k < j; k = l)
        {
            l = spanned.nextSpanTransition(k, j, android/text/style/ParagraphStyle);
            ParagraphStyle aparagraphstyle[] = (ParagraphStyle[])spanned.getSpans(k, l, android/text/style/ParagraphStyle);
            Object obj = " ";
            boolean flag = false;
            int i1 = 0;
            while(i1 < aparagraphstyle.length) 
            {
                Object obj1 = obj;
                if(aparagraphstyle[i1] instanceof AlignmentSpan)
                {
                    obj1 = ((AlignmentSpan)aparagraphstyle[i1]).getAlignment();
                    flag = true;
                    if(obj1 == Layout.Alignment.ALIGN_CENTER)
                        obj1 = (new StringBuilder()).append("align=\"center\" ").append(((String) (obj))).toString();
                    else
                    if(obj1 == Layout.Alignment.ALIGN_OPPOSITE)
                        obj1 = (new StringBuilder()).append("align=\"right\" ").append(((String) (obj))).toString();
                    else
                        obj1 = (new StringBuilder()).append("align=\"left\" ").append(((String) (obj))).toString();
                }
                i1++;
                obj = obj1;
            }
            if(flag)
                stringbuilder.append("<div ").append(((String) (obj))).append(">");
            withinDiv(stringbuilder, spanned, k, l, i);
            if(flag)
                stringbuilder.append("</div>");
        }

    }

    public static String escapeHtml(CharSequence charsequence)
    {
        StringBuilder stringbuilder = new StringBuilder();
        withinStyle(stringbuilder, charsequence, 0, charsequence.length());
        return stringbuilder.toString();
    }

    public static Spanned fromHtml(String s)
    {
        return fromHtml(s, 0, null, null);
    }

    public static Spanned fromHtml(String s, int i)
    {
        return fromHtml(s, i, null, null);
    }

    public static Spanned fromHtml(String s, int i, ImageGetter imagegetter, TagHandler taghandler)
    {
        Parser parser = new Parser();
        try
        {
            parser.setProperty("http://www.ccil.org/~cowan/tagsoup/properties/schema", HtmlParser._2D_get0());
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new RuntimeException(s);
        }
        return (new HtmlToSpannedConverter(s, imagegetter, taghandler, parser, i)).convert();
    }

    public static Spanned fromHtml(String s, ImageGetter imagegetter, TagHandler taghandler)
    {
        return fromHtml(s, 0, imagegetter, taghandler);
    }

    private static String getTextDirection(Spanned spanned, int i, int j)
    {
        if(TextDirectionHeuristics.FIRSTSTRONG_LTR.isRtl(spanned, i, j - i))
            return " dir=\"rtl\"";
        else
            return " dir=\"ltr\"";
    }

    private static String getTextStyles(Spanned spanned, int i, int j, boolean flag, boolean flag1)
    {
        String s;
        Object obj;
        Object obj1;
        s = null;
        obj = null;
        if(flag)
            s = "margin-top:0; margin-bottom:0;";
        obj1 = obj;
        if(!flag1) goto _L2; else goto _L1
_L1:
        AlignmentSpan aalignmentspan[];
        aalignmentspan = (AlignmentSpan[])spanned.getSpans(i, j, android/text/style/AlignmentSpan);
        i = aalignmentspan.length - 1;
_L10:
        obj1 = obj;
        if(i < 0) goto _L2; else goto _L3
_L3:
        obj1 = aalignmentspan[i];
        if((spanned.getSpanFlags(obj1) & 0x33) != 51) goto _L5; else goto _L4
_L4:
        spanned = ((AlignmentSpan) (obj1)).getAlignment();
        if(spanned != Layout.Alignment.ALIGN_NORMAL) goto _L7; else goto _L6
_L6:
        obj1 = "text-align:start;";
_L2:
        if(s == null && obj1 == null)
            return "";
        break; /* Loop/switch isn't completed */
_L7:
        if(spanned == Layout.Alignment.ALIGN_CENTER)
        {
            obj1 = "text-align:center;";
        } else
        {
            obj1 = obj;
            if(spanned == Layout.Alignment.ALIGN_OPPOSITE)
                obj1 = "text-align:end;";
        }
        if(true) goto _L2; else goto _L8
_L8:
        break; /* Loop/switch isn't completed */
_L5:
        i--;
        if(true) goto _L10; else goto _L9
_L9:
        spanned = new StringBuilder(" style=\"");
        if(s == null || obj1 == null) goto _L12; else goto _L11
_L11:
        spanned.append(s).append(" ").append(((String) (obj1)));
_L14:
        return spanned.append("\"").toString();
_L12:
        if(s != null)
            spanned.append(s);
        else
        if(obj1 != null)
            spanned.append(((String) (obj1)));
        if(true) goto _L14; else goto _L13
_L13:
    }

    public static String toHtml(Spanned spanned)
    {
        return toHtml(spanned, 0);
    }

    public static String toHtml(Spanned spanned, int i)
    {
        StringBuilder stringbuilder = new StringBuilder();
        withinHtml(stringbuilder, spanned, i);
        return stringbuilder.toString();
    }

    private static void withinBlockquote(StringBuilder stringbuilder, Spanned spanned, int i, int j, int k)
    {
        if((k & 1) == 0)
            withinBlockquoteConsecutive(stringbuilder, spanned, i, j);
        else
            withinBlockquoteIndividual(stringbuilder, spanned, i, j);
    }

    private static void withinBlockquoteConsecutive(StringBuilder stringbuilder, Spanned spanned, int i, int j)
    {
        stringbuilder.append("<p").append(getTextDirection(spanned, i, j)).append(">");
        int k = i;
        while(k < j) 
        {
            int i1 = TextUtils.indexOf(spanned, '\n', k, j);
            int j1 = i1;
            if(i1 < 0)
                j1 = j;
            i1 = 0;
            for(; j1 < j && spanned.charAt(j1) == '\n'; j1++)
                i1++;

            withinParagraph(stringbuilder, spanned, k, j1 - i1);
            if(i1 == 1)
            {
                stringbuilder.append("<br>\n");
            } else
            {
                for(int l = 2; l < i1; l++)
                    stringbuilder.append("<br>");

                if(j1 != j)
                {
                    stringbuilder.append("</p>\n");
                    stringbuilder.append("<p").append(getTextDirection(spanned, i, j)).append(">");
                }
            }
            k = j1;
        }
        stringbuilder.append("</p>\n");
    }

    private static void withinBlockquoteIndividual(StringBuilder stringbuilder, Spanned spanned, int i, int j)
    {
        int i1;
        boolean flag = false;
        i1 = i;
        i = ((flag) ? 1 : 0);
_L2:
        int j1;
        if(i1 > j)
            break MISSING_BLOCK_LABEL_355;
        int k = TextUtils.indexOf(spanned, '\n', i1, j);
        j1 = k;
        if(k < 0)
            j1 = j;
        if(j1 != i1)
            break; /* Loop/switch isn't completed */
        k = i;
        if(i != 0)
        {
            k = 0;
            stringbuilder.append("</ul>\n");
        }
        stringbuilder.append("<br>\n");
        i = k;
_L3:
        i1 = j1 + 1;
        if(true) goto _L2; else goto _L1
_L1:
        int l;
        int k1;
        ParagraphStyle aparagraphstyle[];
        int l1;
        k1 = 0;
        aparagraphstyle = (ParagraphStyle[])spanned.getSpans(i1, j1, android/text/style/ParagraphStyle);
        l = 0;
        l1 = aparagraphstyle.length;
_L4:
label0:
        {
            boolean flag1 = k1;
            if(l < l1)
            {
                ParagraphStyle paragraphstyle = aparagraphstyle[l];
                if((spanned.getSpanFlags(paragraphstyle) & 0x33) != 51 || !(paragraphstyle instanceof BulletSpan))
                    break label0;
                flag1 = true;
            }
            k1 = i;
            if(flag1)
            {
                k1 = i;
                if((i ^ 1) != 0)
                {
                    k1 = 1;
                    stringbuilder.append("<ul").append(getTextStyles(spanned, i1, j1, true, false)).append(">\n");
                }
            }
            l = k1;
            if(k1 != 0)
            {
                l = k1;
                if(flag1 ^ true)
                {
                    l = 0;
                    stringbuilder.append("</ul>\n");
                }
            }
            String s;
            if(flag1)
                s = "li";
            else
                s = "p";
            stringbuilder.append("<").append(s).append(getTextDirection(spanned, i1, j1)).append(getTextStyles(spanned, i1, j1, flag1 ^ true, true)).append(">");
            withinParagraph(stringbuilder, spanned, i1, j1);
            stringbuilder.append("</");
            stringbuilder.append(s);
            stringbuilder.append(">\n");
            i = l;
            if(j1 == j)
            {
                i = l;
                if(l != 0)
                {
                    i = 0;
                    stringbuilder.append("</ul>\n");
                }
            }
        }
          goto _L3
        l++;
          goto _L4
          goto _L3
    }

    private static void withinDiv(StringBuilder stringbuilder, Spanned spanned, int i, int j, int k)
    {
        int l;
        for(; i < j; i = l)
        {
            l = spanned.nextSpanTransition(i, j, android/text/style/QuoteSpan);
            QuoteSpan aquotespan[] = (QuoteSpan[])spanned.getSpans(i, l, android/text/style/QuoteSpan);
            int i1 = aquotespan.length;
            for(int j1 = 0; j1 < i1; j1++)
            {
                QuoteSpan quotespan = aquotespan[j1];
                stringbuilder.append("<blockquote>");
            }

            withinBlockquote(stringbuilder, spanned, i, l, k);
            int k1 = aquotespan.length;
            for(i = 0; i < k1; i++)
            {
                QuoteSpan quotespan1 = aquotespan[i];
                stringbuilder.append("</blockquote>\n");
            }

        }

    }

    private static void withinHtml(StringBuilder stringbuilder, Spanned spanned, int i)
    {
        if((i & 1) == 0)
        {
            encodeTextAlignmentByDiv(stringbuilder, spanned, i);
            return;
        } else
        {
            withinDiv(stringbuilder, spanned, 0, spanned.length(), i);
            return;
        }
    }

    private static void withinParagraph(StringBuilder stringbuilder, Spanned spanned, int i, int j)
    {
        int k;
        for(; i < j; i = k)
        {
            k = spanned.nextSpanTransition(i, j, android/text/style/CharacterStyle);
            CharacterStyle acharacterstyle[] = (CharacterStyle[])spanned.getSpans(i, k, android/text/style/CharacterStyle);
            for(int l = 0; l < acharacterstyle.length; l++)
            {
                if(acharacterstyle[l] instanceof StyleSpan)
                {
                    int j1 = ((StyleSpan)acharacterstyle[l]).getStyle();
                    if((j1 & 1) != 0)
                        stringbuilder.append("<b>");
                    if((j1 & 2) != 0)
                        stringbuilder.append("<i>");
                }
                if((acharacterstyle[l] instanceof TypefaceSpan) && "monospace".equals(((TypefaceSpan)acharacterstyle[l]).getFamily()))
                    stringbuilder.append("<tt>");
                if(acharacterstyle[l] instanceof SuperscriptSpan)
                    stringbuilder.append("<sup>");
                if(acharacterstyle[l] instanceof SubscriptSpan)
                    stringbuilder.append("<sub>");
                if(acharacterstyle[l] instanceof UnderlineSpan)
                    stringbuilder.append("<u>");
                if(acharacterstyle[l] instanceof StrikethroughSpan)
                    stringbuilder.append("<span style=\"text-decoration:line-through;\">");
                if(acharacterstyle[l] instanceof URLSpan)
                {
                    stringbuilder.append("<a href=\"");
                    stringbuilder.append(((URLSpan)acharacterstyle[l]).getURL());
                    stringbuilder.append("\">");
                }
                if(acharacterstyle[l] instanceof ImageSpan)
                {
                    stringbuilder.append("<img src=\"");
                    stringbuilder.append(((ImageSpan)acharacterstyle[l]).getSource());
                    stringbuilder.append("\">");
                    i = k;
                }
                if(acharacterstyle[l] instanceof AbsoluteSizeSpan)
                {
                    AbsoluteSizeSpan absolutesizespan = (AbsoluteSizeSpan)acharacterstyle[l];
                    float f = absolutesizespan.getSize();
                    float f1 = f;
                    if(!absolutesizespan.getDip())
                        f1 = f / ActivityThread.currentApplication().getResources().getDisplayMetrics().density;
                    stringbuilder.append(String.format("<span style=\"font-size:%.0fpx\";>", new Object[] {
                        Float.valueOf(f1)
                    }));
                }
                if(acharacterstyle[l] instanceof RelativeSizeSpan)
                    stringbuilder.append(String.format("<span style=\"font-size:%.2fem;\">", new Object[] {
                        Float.valueOf(((RelativeSizeSpan)acharacterstyle[l]).getSizeChange())
                    }));
                if(acharacterstyle[l] instanceof ForegroundColorSpan)
                    stringbuilder.append(String.format("<span style=\"color:#%06X;\">", new Object[] {
                        Integer.valueOf(0xffffff & ((ForegroundColorSpan)acharacterstyle[l]).getForegroundColor())
                    }));
                if(acharacterstyle[l] instanceof BackgroundColorSpan)
                    stringbuilder.append(String.format("<span style=\"background-color:#%06X;\">", new Object[] {
                        Integer.valueOf(0xffffff & ((BackgroundColorSpan)acharacterstyle[l]).getBackgroundColor())
                    }));
            }

            withinStyle(stringbuilder, spanned, i, k);
            for(i = acharacterstyle.length - 1; i >= 0; i--)
            {
                if(acharacterstyle[i] instanceof BackgroundColorSpan)
                    stringbuilder.append("</span>");
                if(acharacterstyle[i] instanceof ForegroundColorSpan)
                    stringbuilder.append("</span>");
                if(acharacterstyle[i] instanceof RelativeSizeSpan)
                    stringbuilder.append("</span>");
                if(acharacterstyle[i] instanceof AbsoluteSizeSpan)
                    stringbuilder.append("</span>");
                if(acharacterstyle[i] instanceof URLSpan)
                    stringbuilder.append("</a>");
                if(acharacterstyle[i] instanceof StrikethroughSpan)
                    stringbuilder.append("</span>");
                if(acharacterstyle[i] instanceof UnderlineSpan)
                    stringbuilder.append("</u>");
                if(acharacterstyle[i] instanceof SubscriptSpan)
                    stringbuilder.append("</sub>");
                if(acharacterstyle[i] instanceof SuperscriptSpan)
                    stringbuilder.append("</sup>");
                if((acharacterstyle[i] instanceof TypefaceSpan) && ((TypefaceSpan)acharacterstyle[i]).getFamily().equals("monospace"))
                    stringbuilder.append("</tt>");
                if(!(acharacterstyle[i] instanceof StyleSpan))
                    continue;
                int i1 = ((StyleSpan)acharacterstyle[i]).getStyle();
                if((i1 & 1) != 0)
                    stringbuilder.append("</b>");
                if((i1 & 2) != 0)
                    stringbuilder.append("</i>");
            }

        }

    }

    private static void withinStyle(StringBuilder stringbuilder, CharSequence charsequence, int i, int j)
    {
        while(i < j) 
        {
            char c = charsequence.charAt(i);
            int k;
            if(c == '<')
            {
                stringbuilder.append("&lt;");
                k = i;
            } else
            if(c == '>')
            {
                stringbuilder.append("&gt;");
                k = i;
            } else
            if(c == '&')
            {
                stringbuilder.append("&amp;");
                k = i;
            } else
            if(c >= '\uD800' && c <= '\uDFFF')
            {
                k = i;
                if(c < '\uDC00')
                {
                    k = i;
                    if(i + 1 < j)
                    {
                        char c1 = charsequence.charAt(i + 1);
                        k = i;
                        if(c1 >= '\uDC00')
                        {
                            k = i;
                            if(c1 <= '\uDFFF')
                            {
                                k = i + 1;
                                stringbuilder.append("&#").append(c - 55296 << 10 | 0x10000 | c1 - 56320).append(";");
                            }
                        }
                    }
                }
            } else
            if(c > '~' || c < ' ')
            {
                stringbuilder.append("&#").append(c).append(";");
                k = i;
            } else
            if(c == ' ')
            {
                for(; i + 1 < j && charsequence.charAt(i + 1) == ' '; i++)
                    stringbuilder.append("&nbsp;");

                stringbuilder.append(' ');
                k = i;
            } else
            {
                stringbuilder.append(c);
                k = i;
            }
            i = k + 1;
        }
    }

    public static final int FROM_HTML_MODE_COMPACT = 63;
    public static final int FROM_HTML_MODE_LEGACY = 0;
    public static final int FROM_HTML_OPTION_USE_CSS_COLORS = 256;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE = 32;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_DIV = 16;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_HEADING = 2;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_LIST = 8;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM = 4;
    public static final int FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH = 1;
    private static final int TO_HTML_PARAGRAPH_FLAG = 1;
    public static final int TO_HTML_PARAGRAPH_LINES_CONSECUTIVE = 0;
    public static final int TO_HTML_PARAGRAPH_LINES_INDIVIDUAL = 1;
}
