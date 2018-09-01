// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.text;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
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
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.*;

// Referenced classes of package android.text:
//            SpannableStringBuilder, Editable, Spanned, Spannable, 
//            TextUtils

class HtmlToSpannedConverter
    implements ContentHandler
{
    private static class Alignment
    {

        static Layout.Alignment _2D_get0(Alignment alignment)
        {
            return alignment.mAlignment;
        }

        private Layout.Alignment mAlignment;

        public Alignment(Layout.Alignment alignment)
        {
            mAlignment = alignment;
        }
    }

    private static class Background
    {

        static int _2D_get0(Background background)
        {
            return background.mBackgroundColor;
        }

        private int mBackgroundColor;

        public Background(int i)
        {
            mBackgroundColor = i;
        }
    }

    private static class Big
    {

        private Big()
        {
        }

        Big(Big big)
        {
            this();
        }
    }

    private static class Blockquote
    {

        private Blockquote()
        {
        }

        Blockquote(Blockquote blockquote)
        {
            this();
        }
    }

    private static class Bold
    {

        private Bold()
        {
        }

        Bold(Bold bold)
        {
            this();
        }
    }

    private static class Bullet
    {

        private Bullet()
        {
        }

        Bullet(Bullet bullet)
        {
            this();
        }
    }

    private static class Font
    {

        public String mFace;

        public Font(String s)
        {
            mFace = s;
        }
    }

    private static class Foreground
    {

        static int _2D_get0(Foreground foreground)
        {
            return foreground.mForegroundColor;
        }

        private int mForegroundColor;

        public Foreground(int i)
        {
            mForegroundColor = i;
        }
    }

    private static class Heading
    {

        static int _2D_get0(Heading heading)
        {
            return heading.mLevel;
        }

        private int mLevel;

        public Heading(int i)
        {
            mLevel = i;
        }
    }

    private static class Href
    {

        public String mHref;

        public Href(String s)
        {
            mHref = s;
        }
    }

    private static class Italic
    {

        private Italic()
        {
        }

        Italic(Italic italic)
        {
            this();
        }
    }

    private static class Monospace
    {

        private Monospace()
        {
        }

        Monospace(Monospace monospace)
        {
            this();
        }
    }

    private static class Newline
    {

        static int _2D_get0(Newline newline)
        {
            return newline.mNumNewlines;
        }

        private int mNumNewlines;

        public Newline(int i)
        {
            mNumNewlines = i;
        }
    }

    private static class Small
    {

        private Small()
        {
        }

        Small(Small small)
        {
            this();
        }
    }

    private static class Strikethrough
    {

        private Strikethrough()
        {
        }

        Strikethrough(Strikethrough strikethrough)
        {
            this();
        }
    }

    private static class Sub
    {

        private Sub()
        {
        }

        Sub(Sub sub)
        {
            this();
        }
    }

    private static class Super
    {

        private Super()
        {
        }

        Super(Super super1)
        {
            this();
        }
    }

    private static class Underline
    {

        private Underline()
        {
        }

        Underline(Underline underline)
        {
            this();
        }
    }


    public HtmlToSpannedConverter(String s, Html.ImageGetter imagegetter, Html.TagHandler taghandler, Parser parser, int i)
    {
        mSource = s;
        mSpannableStringBuilder = new SpannableStringBuilder();
        mImageGetter = imagegetter;
        mTagHandler = taghandler;
        mReader = parser;
        mFlags = i;
    }

    private static void appendNewlines(Editable editable, int i)
    {
        int j = editable.length();
        if(j == 0)
            return;
        int k = 0;
        for(j--; j >= 0 && editable.charAt(j) == '\n'; j--)
            k++;

        for(; k < i; k++)
            editable.append("\n");

    }

    private static void end(Editable editable, Class class1, Object obj)
    {
        editable.length();
        class1 = ((Class) (getLast(editable, class1)));
        if(class1 != null)
            setSpanFromMark(editable, class1, new Object[] {
                obj
            });
    }

    private static void endA(Editable editable)
    {
        Href href = (Href)getLast(editable, android/text/HtmlToSpannedConverter$Href);
        if(href != null && href.mHref != null)
            setSpanFromMark(editable, href, new Object[] {
                new URLSpan(href.mHref)
            });
    }

    private static void endBlockElement(Editable editable)
    {
        Object obj = (Newline)getLast(editable, android/text/HtmlToSpannedConverter$Newline);
        if(obj != null)
        {
            appendNewlines(editable, Newline._2D_get0(((Newline) (obj))));
            editable.removeSpan(obj);
        }
        obj = (Alignment)getLast(editable, android/text/HtmlToSpannedConverter$Alignment);
        if(obj != null)
            setSpanFromMark(editable, obj, new Object[] {
                new android.text.style.AlignmentSpan.Standard(Alignment._2D_get0(((Alignment) (obj))))
            });
    }

    private static void endBlockquote(Editable editable)
    {
        endBlockElement(editable);
        end(editable, android/text/HtmlToSpannedConverter$Blockquote, new QuoteSpan());
    }

    private static void endCssStyle(Editable editable)
    {
        Object obj = (Strikethrough)getLast(editable, android/text/HtmlToSpannedConverter$Strikethrough);
        if(obj != null)
            setSpanFromMark(editable, obj, new Object[] {
                new StrikethroughSpan()
            });
        obj = (Background)getLast(editable, android/text/HtmlToSpannedConverter$Background);
        if(obj != null)
            setSpanFromMark(editable, obj, new Object[] {
                new BackgroundColorSpan(Background._2D_get0(((Background) (obj))))
            });
        obj = (Foreground)getLast(editable, android/text/HtmlToSpannedConverter$Foreground);
        if(obj != null)
            setSpanFromMark(editable, obj, new Object[] {
                new ForegroundColorSpan(Foreground._2D_get0(((Foreground) (obj))))
            });
    }

    private static void endFont(Editable editable)
    {
        Object obj = (Font)getLast(editable, android/text/HtmlToSpannedConverter$Font);
        if(obj != null)
            setSpanFromMark(editable, obj, new Object[] {
                new TypefaceSpan(((Font) (obj)).mFace)
            });
        obj = (Foreground)getLast(editable, android/text/HtmlToSpannedConverter$Foreground);
        if(obj != null)
            setSpanFromMark(editable, obj, new Object[] {
                new ForegroundColorSpan(Foreground._2D_get0(((Foreground) (obj))))
            });
    }

    private static void endHeading(Editable editable)
    {
        Heading heading = (Heading)getLast(editable, android/text/HtmlToSpannedConverter$Heading);
        if(heading != null)
            setSpanFromMark(editable, heading, new Object[] {
                new RelativeSizeSpan(HEADING_SIZES[Heading._2D_get0(heading)]), new StyleSpan(1)
            });
        endBlockElement(editable);
    }

    private static void endLi(Editable editable)
    {
        endCssStyle(editable);
        endBlockElement(editable);
        end(editable, android/text/HtmlToSpannedConverter$Bullet, new BulletSpan());
    }

    private static Pattern getBackgroundColorPattern()
    {
        if(sBackgroundColorPattern == null)
            sBackgroundColorPattern = Pattern.compile("(?:\\s+|\\A)background(?:-color)?\\s*:\\s*(\\S*)\\b");
        return sBackgroundColorPattern;
    }

    private static Pattern getForegroundColorPattern()
    {
        if(sForegroundColorPattern == null)
            sForegroundColorPattern = Pattern.compile("(?:\\s+|\\A)color\\s*:\\s*(\\S*)\\b");
        return sForegroundColorPattern;
    }

    private int getHtmlColor(String s)
    {
        if((mFlags & 0x100) == 256)
        {
            Integer integer = (Integer)sColorMap.get(s.toLowerCase(Locale.US));
            if(integer != null)
                return integer.intValue();
        }
        return Color.getHtmlColor(s);
    }

    private static Object getLast(Spanned spanned, Class class1)
    {
        spanned = ((Spanned) (spanned.getSpans(0, spanned.length(), class1)));
        if(spanned.length == 0)
            return null;
        else
            return spanned[spanned.length - 1];
    }

    private int getMargin(int i)
    {
        return (mFlags & i) == 0 ? 2 : 1;
    }

    private int getMarginBlockquote()
    {
        return getMargin(32);
    }

    private int getMarginDiv()
    {
        return getMargin(16);
    }

    private int getMarginHeading()
    {
        return getMargin(2);
    }

    private int getMarginList()
    {
        return getMargin(8);
    }

    private int getMarginListItem()
    {
        return getMargin(4);
    }

    private int getMarginParagraph()
    {
        return getMargin(1);
    }

    private static Pattern getTextAlignPattern()
    {
        if(sTextAlignPattern == null)
            sTextAlignPattern = Pattern.compile("(?:\\s+|\\A)text-align\\s*:\\s*(\\S*)\\b");
        return sTextAlignPattern;
    }

    private static Pattern getTextDecorationPattern()
    {
        if(sTextDecorationPattern == null)
            sTextDecorationPattern = Pattern.compile("(?:\\s+|\\A)text-decoration\\s*:\\s*(\\S*)\\b");
        return sTextDecorationPattern;
    }

    private static void handleBr(Editable editable)
    {
        editable.append('\n');
    }

    private void handleEndTag(String s)
    {
        if(!s.equalsIgnoreCase("br")) goto _L2; else goto _L1
_L1:
        handleBr(mSpannableStringBuilder);
_L4:
        return;
_L2:
        if(s.equalsIgnoreCase("p"))
        {
            endCssStyle(mSpannableStringBuilder);
            endBlockElement(mSpannableStringBuilder);
        } else
        if(s.equalsIgnoreCase("ul"))
            endBlockElement(mSpannableStringBuilder);
        else
        if(s.equalsIgnoreCase("li"))
            endLi(mSpannableStringBuilder);
        else
        if(s.equalsIgnoreCase("div"))
            endBlockElement(mSpannableStringBuilder);
        else
        if(s.equalsIgnoreCase("span"))
            endCssStyle(mSpannableStringBuilder);
        else
        if(s.equalsIgnoreCase("strong"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Bold, new StyleSpan(1));
        else
        if(s.equalsIgnoreCase("b"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Bold, new StyleSpan(1));
        else
        if(s.equalsIgnoreCase("em"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Italic, new StyleSpan(2));
        else
        if(s.equalsIgnoreCase("cite"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Italic, new StyleSpan(2));
        else
        if(s.equalsIgnoreCase("dfn"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Italic, new StyleSpan(2));
        else
        if(s.equalsIgnoreCase("i"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Italic, new StyleSpan(2));
        else
        if(s.equalsIgnoreCase("big"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Big, new RelativeSizeSpan(1.25F));
        else
        if(s.equalsIgnoreCase("small"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Small, new RelativeSizeSpan(0.8F));
        else
        if(s.equalsIgnoreCase("font"))
            endFont(mSpannableStringBuilder);
        else
        if(s.equalsIgnoreCase("blockquote"))
            endBlockquote(mSpannableStringBuilder);
        else
        if(s.equalsIgnoreCase("tt"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Monospace, new TypefaceSpan("monospace"));
        else
        if(s.equalsIgnoreCase("a"))
            endA(mSpannableStringBuilder);
        else
        if(s.equalsIgnoreCase("u"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Underline, new UnderlineSpan());
        else
        if(s.equalsIgnoreCase("del"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Strikethrough, new StrikethroughSpan());
        else
        if(s.equalsIgnoreCase("s"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Strikethrough, new StrikethroughSpan());
        else
        if(s.equalsIgnoreCase("strike"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Strikethrough, new StrikethroughSpan());
        else
        if(s.equalsIgnoreCase("sup"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Super, new SuperscriptSpan());
        else
        if(s.equalsIgnoreCase("sub"))
            end(mSpannableStringBuilder, android/text/HtmlToSpannedConverter$Sub, new SubscriptSpan());
        else
        if(s.length() == 2 && Character.toLowerCase(s.charAt(0)) == 'h' && s.charAt(1) >= '1' && s.charAt(1) <= '6')
            endHeading(mSpannableStringBuilder);
        else
        if(mTagHandler != null)
            mTagHandler.handleTag(false, s, mSpannableStringBuilder, mReader);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void handleStartTag(String s, Attributes attributes)
    {
        if(!s.equalsIgnoreCase("br")) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if(s.equalsIgnoreCase("p"))
        {
            startBlockElement(mSpannableStringBuilder, attributes, getMarginParagraph());
            startCssStyle(mSpannableStringBuilder, attributes);
        } else
        if(s.equalsIgnoreCase("ul"))
            startBlockElement(mSpannableStringBuilder, attributes, getMarginList());
        else
        if(s.equalsIgnoreCase("li"))
            startLi(mSpannableStringBuilder, attributes);
        else
        if(s.equalsIgnoreCase("div"))
            startBlockElement(mSpannableStringBuilder, attributes, getMarginDiv());
        else
        if(s.equalsIgnoreCase("span"))
            startCssStyle(mSpannableStringBuilder, attributes);
        else
        if(s.equalsIgnoreCase("strong"))
            start(mSpannableStringBuilder, new Bold(null));
        else
        if(s.equalsIgnoreCase("b"))
            start(mSpannableStringBuilder, new Bold(null));
        else
        if(s.equalsIgnoreCase("em"))
            start(mSpannableStringBuilder, new Italic(null));
        else
        if(s.equalsIgnoreCase("cite"))
            start(mSpannableStringBuilder, new Italic(null));
        else
        if(s.equalsIgnoreCase("dfn"))
            start(mSpannableStringBuilder, new Italic(null));
        else
        if(s.equalsIgnoreCase("i"))
            start(mSpannableStringBuilder, new Italic(null));
        else
        if(s.equalsIgnoreCase("big"))
            start(mSpannableStringBuilder, new Big(null));
        else
        if(s.equalsIgnoreCase("small"))
            start(mSpannableStringBuilder, new Small(null));
        else
        if(s.equalsIgnoreCase("font"))
            startFont(mSpannableStringBuilder, attributes);
        else
        if(s.equalsIgnoreCase("blockquote"))
            startBlockquote(mSpannableStringBuilder, attributes);
        else
        if(s.equalsIgnoreCase("tt"))
            start(mSpannableStringBuilder, new Monospace(null));
        else
        if(s.equalsIgnoreCase("a"))
            startA(mSpannableStringBuilder, attributes);
        else
        if(s.equalsIgnoreCase("u"))
            start(mSpannableStringBuilder, new Underline(null));
        else
        if(s.equalsIgnoreCase("del"))
            start(mSpannableStringBuilder, new Strikethrough(null));
        else
        if(s.equalsIgnoreCase("s"))
            start(mSpannableStringBuilder, new Strikethrough(null));
        else
        if(s.equalsIgnoreCase("strike"))
            start(mSpannableStringBuilder, new Strikethrough(null));
        else
        if(s.equalsIgnoreCase("sup"))
            start(mSpannableStringBuilder, new Super(null));
        else
        if(s.equalsIgnoreCase("sub"))
            start(mSpannableStringBuilder, new Sub(null));
        else
        if(s.length() == 2 && Character.toLowerCase(s.charAt(0)) == 'h' && s.charAt(1) >= '1' && s.charAt(1) <= '6')
            startHeading(mSpannableStringBuilder, attributes, s.charAt(1) - 49);
        else
        if(s.equalsIgnoreCase("img"))
            startImg(mSpannableStringBuilder, attributes, mImageGetter);
        else
        if(mTagHandler != null)
            mTagHandler.handleTag(true, s, mSpannableStringBuilder, mReader);
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static transient void setSpanFromMark(Spannable spannable, Object obj, Object aobj[])
    {
        int i = spannable.getSpanStart(obj);
        spannable.removeSpan(obj);
        int j = spannable.length();
        if(i != j)
        {
            int k = 0;
            for(int l = aobj.length; k < l; k++)
                spannable.setSpan(aobj[k], i, j, 33);

        }
    }

    private static void start(Editable editable, Object obj)
    {
        int i = editable.length();
        editable.setSpan(obj, i, i, 17);
    }

    private static void startA(Editable editable, Attributes attributes)
    {
        start(editable, new Href(attributes.getValue("", "href")));
    }

    private static void startBlockElement(Editable editable, Attributes attributes, int i)
    {
        editable.length();
        if(i > 0)
        {
            appendNewlines(editable, i);
            start(editable, new Newline(i));
        }
        attributes = attributes.getValue("", "style");
        if(attributes == null) goto _L2; else goto _L1
_L1:
        attributes = getTextAlignPattern().matcher(attributes);
        if(!attributes.find()) goto _L2; else goto _L3
_L3:
        attributes = attributes.group(1);
        if(!attributes.equalsIgnoreCase("start")) goto _L5; else goto _L4
_L4:
        start(editable, new Alignment(Layout.Alignment.ALIGN_NORMAL));
_L2:
        return;
_L5:
        if(attributes.equalsIgnoreCase("center"))
            start(editable, new Alignment(Layout.Alignment.ALIGN_CENTER));
        else
        if(attributes.equalsIgnoreCase("end"))
            start(editable, new Alignment(Layout.Alignment.ALIGN_OPPOSITE));
        if(true) goto _L2; else goto _L6
_L6:
    }

    private void startBlockquote(Editable editable, Attributes attributes)
    {
        startBlockElement(editable, attributes, getMarginBlockquote());
        start(editable, new Blockquote(null));
    }

    private void startCssStyle(Editable editable, Attributes attributes)
    {
        attributes = attributes.getValue("", "style");
        if(attributes != null)
        {
            Matcher matcher = getForegroundColorPattern().matcher(attributes);
            if(matcher.find())
            {
                int i = getHtmlColor(matcher.group(1));
                if(i != -1)
                    start(editable, new Foreground(i | 0xff000000));
            }
            matcher = getBackgroundColorPattern().matcher(attributes);
            if(matcher.find())
            {
                int j = getHtmlColor(matcher.group(1));
                if(j != -1)
                    start(editable, new Background(j | 0xff000000));
            }
            attributes = getTextDecorationPattern().matcher(attributes);
            if(attributes.find() && attributes.group(1).equalsIgnoreCase("line-through"))
                start(editable, new Strikethrough(null));
        }
    }

    private void startFont(Editable editable, Attributes attributes)
    {
        String s = attributes.getValue("", "color");
        attributes = attributes.getValue("", "face");
        if(!TextUtils.isEmpty(s))
        {
            int i = getHtmlColor(s);
            if(i != -1)
                start(editable, new Foreground(0xff000000 | i));
        }
        if(!TextUtils.isEmpty(attributes))
            start(editable, new Font(attributes));
    }

    private void startHeading(Editable editable, Attributes attributes, int i)
    {
        startBlockElement(editable, attributes, getMarginHeading());
        start(editable, new Heading(i));
    }

    private static void startImg(Editable editable, Attributes attributes, Html.ImageGetter imagegetter)
    {
        String s = attributes.getValue("", "src");
        attributes = null;
        if(imagegetter != null)
            attributes = imagegetter.getDrawable(s);
        imagegetter = attributes;
        if(attributes == null)
        {
            imagegetter = Resources.getSystem().getDrawable(0x108088d);
            imagegetter.setBounds(0, 0, imagegetter.getIntrinsicWidth(), imagegetter.getIntrinsicHeight());
        }
        int i = editable.length();
        editable.append("\uFFFC");
        editable.setSpan(new ImageSpan(imagegetter, s), i, editable.length(), 33);
    }

    private void startLi(Editable editable, Attributes attributes)
    {
        startBlockElement(editable, attributes, getMarginListItem());
        start(editable, new Bullet(null));
        startCssStyle(editable, attributes);
    }

    public void characters(char ac[], int i, int j)
        throws SAXException
    {
        StringBuilder stringbuilder = new StringBuilder();
        int k = 0;
        while(k < j) 
        {
            char c = ac[k + i];
            if(c == ' ' || c == '\n')
            {
                int l = stringbuilder.length();
                if(l == 0)
                {
                    l = mSpannableStringBuilder.length();
                    if(l == 0)
                        l = 10;
                    else
                        l = mSpannableStringBuilder.charAt(l - 1);
                } else
                {
                    l = stringbuilder.charAt(l - 1);
                }
                if(l != 32 && l != 10)
                    stringbuilder.append(' ');
            } else
            {
                stringbuilder.append(c);
            }
            k++;
        }
        mSpannableStringBuilder.append(stringbuilder);
    }

    public Spanned convert()
    {
        mReader.setContentHandler(this);
        Object obj;
        int i;
        try
        {
            XMLReader xmlreader = mReader;
            InputSource inputsource = JVM INSTR new #651 <Class InputSource>;
            StringReader stringreader = JVM INSTR new #653 <Class StringReader>;
            stringreader.StringReader(mSource);
            inputsource.InputSource(stringreader);
            xmlreader.parse(inputsource);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException(((Throwable) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            throw new RuntimeException(((Throwable) (obj)));
        }
        obj = ((Object) (mSpannableStringBuilder.getSpans(0, mSpannableStringBuilder.length(), android/text/style/ParagraphStyle)));
        i = 0;
        while(i < obj.length) 
        {
            int j = mSpannableStringBuilder.getSpanStart(obj[i]);
            int k = mSpannableStringBuilder.getSpanEnd(obj[i]);
            int l = k;
            if(k - 2 >= 0)
            {
                l = k;
                if(mSpannableStringBuilder.charAt(k - 1) == '\n')
                {
                    l = k;
                    if(mSpannableStringBuilder.charAt(k - 2) == '\n')
                        l = k - 1;
                }
            }
            if(l == j)
                mSpannableStringBuilder.removeSpan(obj[i]);
            else
                mSpannableStringBuilder.setSpan(obj[i], j, l, 51);
            i++;
        }
        return mSpannableStringBuilder;
    }

    public void endDocument()
        throws SAXException
    {
    }

    public void endElement(String s, String s1, String s2)
        throws SAXException
    {
        handleEndTag(s1);
    }

    public void endPrefixMapping(String s)
        throws SAXException
    {
    }

    public void ignorableWhitespace(char ac[], int i, int j)
        throws SAXException
    {
    }

    public void processingInstruction(String s, String s1)
        throws SAXException
    {
    }

    public void setDocumentLocator(Locator locator)
    {
    }

    public void skippedEntity(String s)
        throws SAXException
    {
    }

    public void startDocument()
        throws SAXException
    {
    }

    public void startElement(String s, String s1, String s2, Attributes attributes)
        throws SAXException
    {
        handleStartTag(s1, attributes);
    }

    public void startPrefixMapping(String s, String s1)
        throws SAXException
    {
    }

    private static final float HEADING_SIZES[] = {
        1.5F, 1.4F, 1.3F, 1.2F, 1.1F, 1.0F
    };
    private static Pattern sBackgroundColorPattern;
    private static final Map sColorMap;
    private static Pattern sForegroundColorPattern;
    private static Pattern sTextAlignPattern;
    private static Pattern sTextDecorationPattern;
    private int mFlags;
    private Html.ImageGetter mImageGetter;
    private XMLReader mReader;
    private String mSource;
    private SpannableStringBuilder mSpannableStringBuilder;
    private Html.TagHandler mTagHandler;

    static 
    {
        sColorMap = new HashMap();
        sColorMap.put("darkgray", Integer.valueOf(0xffa9a9a9));
        sColorMap.put("gray", Integer.valueOf(0xff808080));
        sColorMap.put("lightgray", Integer.valueOf(0xffd3d3d3));
        sColorMap.put("darkgrey", Integer.valueOf(0xffa9a9a9));
        sColorMap.put("grey", Integer.valueOf(0xff808080));
        sColorMap.put("lightgrey", Integer.valueOf(0xffd3d3d3));
        sColorMap.put("green", Integer.valueOf(0xff008000));
    }
}
