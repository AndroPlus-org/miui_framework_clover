// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.graphics;

import android.graphics.fonts.FontVariationAxis;
import android.text.FontConfig;
import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class FontListParser
{

    public FontListParser()
    {
    }

    public static FontConfig parse(InputStream inputstream)
        throws XmlPullParserException, IOException
    {
        Object obj;
        obj = Xml.newPullParser();
        ((XmlPullParser) (obj)).setInput(inputstream, null);
        ((XmlPullParser) (obj)).nextTag();
        obj = readFamilies(((XmlPullParser) (obj)));
        inputstream.close();
        return ((FontConfig) (obj));
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
    }

    private static android.text.FontConfig.Alias readAlias(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        String s = xmlpullparser.getAttributeValue(null, "name");
        String s1 = xmlpullparser.getAttributeValue(null, "to");
        String s2 = xmlpullparser.getAttributeValue(null, "weight");
        int i;
        if(s2 == null)
            i = 400;
        else
            i = Integer.parseInt(s2);
        skip(xmlpullparser);
        return new android.text.FontConfig.Alias(s, s1, i);
    }

    private static FontVariationAxis readAxis(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        String s = xmlpullparser.getAttributeValue(null, "tag");
        String s1 = xmlpullparser.getAttributeValue(null, "stylevalue");
        skip(xmlpullparser);
        return new FontVariationAxis(s, Float.parseFloat(s1));
    }

    private static FontConfig readFamilies(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        ArrayList arraylist = new ArrayList();
        ArrayList arraylist1 = new ArrayList();
        xmlpullparser.require(2, null, "familyset");
        do
        {
            if(xmlpullparser.next() == 3)
                break;
            if(xmlpullparser.getEventType() == 2)
            {
                String s = xmlpullparser.getName();
                if(s.equals("family"))
                    arraylist.add(readFamily(xmlpullparser));
                else
                if(s.equals("alias"))
                    arraylist1.add(readAlias(xmlpullparser));
                else
                    skip(xmlpullparser);
            }
        } while(true);
        return new FontConfig((android.text.FontConfig.Family[])arraylist.toArray(new android.text.FontConfig.Family[arraylist.size()]), (android.text.FontConfig.Alias[])arraylist1.toArray(new android.text.FontConfig.Alias[arraylist1.size()]));
    }

    private static android.text.FontConfig.Family readFamily(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        String s;
        String s1;
        String s2;
        ArrayList arraylist;
        boolean flag;
        int i;
        s = xmlpullparser.getAttributeValue(null, "name");
        s1 = xmlpullparser.getAttributeValue(null, "lang");
        s2 = xmlpullparser.getAttributeValue(null, "variant");
        arraylist = new ArrayList();
        do
        {
            if(xmlpullparser.next() == 3)
                break;
            if(xmlpullparser.getEventType() == 2)
                if(xmlpullparser.getName().equals("font"))
                    arraylist.add(readFont(xmlpullparser));
                else
                    skip(xmlpullparser);
        } while(true);
        flag = false;
        i = ((flag) ? 1 : 0);
        if(s2 == null) goto _L2; else goto _L1
_L1:
        if(!s2.equals("compact")) goto _L4; else goto _L3
_L3:
        i = 1;
_L2:
        return new android.text.FontConfig.Family(s, (android.text.FontConfig.Font[])arraylist.toArray(new android.text.FontConfig.Font[arraylist.size()]), s1, i);
_L4:
        i = ((flag) ? 1 : 0);
        if(s2.equals("elegant"))
            i = 2;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private static android.text.FontConfig.Font readFont(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        Object obj = xmlpullparser.getAttributeValue(null, "index");
        int i;
        Object obj1;
        int j;
        boolean flag;
        if(obj == null)
            i = 0;
        else
            i = Integer.parseInt(((String) (obj)));
        obj = new ArrayList();
        obj1 = xmlpullparser.getAttributeValue(null, "weight");
        if(obj1 == null)
            j = 400;
        else
            j = Integer.parseInt(((String) (obj1)));
        flag = "italic".equals(xmlpullparser.getAttributeValue(null, "style"));
        obj1 = new StringBuilder();
        do
        {
            if(xmlpullparser.next() == 3)
                break;
            if(xmlpullparser.getEventType() == 4)
                ((StringBuilder) (obj1)).append(xmlpullparser.getText());
            if(xmlpullparser.getEventType() == 2)
                if(xmlpullparser.getName().equals("axis"))
                    ((List) (obj)).add(readAxis(xmlpullparser));
                else
                    skip(xmlpullparser);
        } while(true);
        return new android.text.FontConfig.Font(FILENAME_WHITESPACE_PATTERN.matcher(((CharSequence) (obj1))).replaceAll(""), i, (FontVariationAxis[])((List) (obj)).toArray(new FontVariationAxis[((List) (obj)).size()]), j, flag);
    }

    private static void skip(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i = 1;
        do
        {
            if(i <= 0)
                break;
            switch(xmlpullparser.next())
            {
            case 2: // '\002'
                i++;
                break;

            case 3: // '\003'
                i--;
                break;
            }
        } while(true);
    }

    private static final Pattern FILENAME_WHITESPACE_PATTERN = Pattern.compile("^[ \\n\\r\\t]+|[ \\n\\r\\t]+$");

}
