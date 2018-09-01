// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.util;

import java.io.*;
import org.apache.harmony.xml.ExpatReader;
import org.kxml2.io.KXmlParser;
import org.xml.sax.*;
import org.xmlpull.v1.*;

// Referenced classes of package android.util:
//            AttributeSet, XmlPullAttributes

public class Xml
{
    public static final class Encoding extends Enum
    {

        public static Encoding valueOf(String s)
        {
            return (Encoding)Enum.valueOf(android/util/Xml$Encoding, s);
        }

        public static Encoding[] values()
        {
            return $VALUES;
        }

        private static final Encoding $VALUES[];
        public static final Encoding ISO_8859_1;
        public static final Encoding US_ASCII;
        public static final Encoding UTF_16;
        public static final Encoding UTF_8;
        final String expatName;

        static 
        {
            US_ASCII = new Encoding("US_ASCII", 0, "US-ASCII");
            UTF_8 = new Encoding("UTF_8", 1, "UTF-8");
            UTF_16 = new Encoding("UTF_16", 2, "UTF-16");
            ISO_8859_1 = new Encoding("ISO_8859_1", 3, "ISO-8859-1");
            $VALUES = (new Encoding[] {
                US_ASCII, UTF_8, UTF_16, ISO_8859_1
            });
        }

        private Encoding(String s, int i, String s1)
        {
            super(s, i);
            expatName = s1;
        }
    }

    static class XmlSerializerFactory
    {

        static final String TYPE = "org.kxml2.io.KXmlParser,org.kxml2.io.KXmlSerializer";
        static final XmlPullParserFactory instance;

        static 
        {
            try
            {
                instance = XmlPullParserFactory.newInstance("org.kxml2.io.KXmlParser,org.kxml2.io.KXmlSerializer", null);
            }
            catch(XmlPullParserException xmlpullparserexception)
            {
                throw new AssertionError(xmlpullparserexception);
            }
        }

        XmlSerializerFactory()
        {
        }
    }


    public Xml()
    {
    }

    public static AttributeSet asAttributeSet(XmlPullParser xmlpullparser)
    {
        if(xmlpullparser instanceof AttributeSet)
            xmlpullparser = (AttributeSet)xmlpullparser;
        else
            xmlpullparser = new XmlPullAttributes(xmlpullparser);
        return xmlpullparser;
    }

    public static Encoding findEncodingByName(String s)
        throws UnsupportedEncodingException
    {
        if(s == null)
            return Encoding.UTF_8;
        Encoding aencoding[] = Encoding.values();
        int i = 0;
        for(int j = aencoding.length; i < j; i++)
        {
            Encoding encoding = aencoding[i];
            if(encoding.expatName.equalsIgnoreCase(s))
                return encoding;
        }

        throw new UnsupportedEncodingException(s);
    }

    public static XmlPullParser newPullParser()
    {
        KXmlParser kxmlparser;
        try
        {
            kxmlparser = JVM INSTR new #62  <Class KXmlParser>;
            kxmlparser.KXmlParser();
            kxmlparser.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", true);
            kxmlparser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        }
        catch(XmlPullParserException xmlpullparserexception)
        {
            throw new AssertionError();
        }
        return kxmlparser;
    }

    public static XmlSerializer newSerializer()
    {
        XmlSerializer xmlserializer;
        try
        {
            xmlserializer = XmlSerializerFactory.instance.newSerializer();
        }
        catch(XmlPullParserException xmlpullparserexception)
        {
            throw new AssertionError(xmlpullparserexception);
        }
        return xmlserializer;
    }

    public static void parse(InputStream inputstream, Encoding encoding, ContentHandler contenthandler)
        throws IOException, SAXException
    {
        ExpatReader expatreader = new ExpatReader();
        expatreader.setContentHandler(contenthandler);
        inputstream = new InputSource(inputstream);
        inputstream.setEncoding(encoding.expatName);
        expatreader.parse(inputstream);
    }

    public static void parse(Reader reader, ContentHandler contenthandler)
        throws IOException, SAXException
    {
        ExpatReader expatreader = new ExpatReader();
        expatreader.setContentHandler(contenthandler);
        expatreader.parse(new InputSource(reader));
    }

    public static void parse(String s, ContentHandler contenthandler)
        throws SAXException
    {
        try
        {
            ExpatReader expatreader = JVM INSTR new #95  <Class ExpatReader>;
            expatreader.ExpatReader();
            expatreader.setContentHandler(contenthandler);
            InputSource inputsource = JVM INSTR new #104 <Class InputSource>;
            contenthandler = JVM INSTR new #120 <Class StringReader>;
            contenthandler.StringReader(s);
            inputsource.InputSource(contenthandler);
            expatreader.parse(inputsource);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new AssertionError(s);
        }
    }

    public static String FEATURE_RELAXED = "http://xmlpull.org/v1/doc/features.html#relaxed";

}
