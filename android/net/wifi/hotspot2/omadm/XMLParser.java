// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.hotspot2.omadm;

import android.text.TextUtils;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

// Referenced classes of package android.net.wifi.hotspot2.omadm:
//            XMLNode

public class XMLParser extends DefaultHandler
{

    public XMLParser()
    {
        mRoot = null;
        mCurrent = null;
    }

    public void characters(char ac[], int i, int j)
        throws SAXException
    {
        mCurrent.addText(new String(ac, i, j));
    }

    public void endElement(String s, String s1, String s2)
        throws SAXException
    {
        if(!s2.equals(mCurrent.getTag()))
        {
            throw new SAXException((new StringBuilder()).append("End tag '").append(s2).append("' doesn't match current node: ").append(mCurrent).toString());
        } else
        {
            mCurrent.close();
            mCurrent = mCurrent.getParent();
            return;
        }
    }

    public XMLNode parse(String s)
        throws IOException, SAXException
    {
        if(TextUtils.isEmpty(s))
            throw new IOException("XML string not provided");
        mRoot = null;
        mCurrent = null;
        try
        {
            SAXParser saxparser = SAXParserFactory.newInstance().newSAXParser();
            InputSource inputsource = JVM INSTR new #94  <Class InputSource>;
            StringReader stringreader = JVM INSTR new #96  <Class StringReader>;
            stringreader.StringReader(s);
            inputsource.InputSource(stringreader);
            saxparser.parse(inputsource, this);
            s = mRoot;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new SAXException(s);
        }
        return s;
    }

    public void startElement(String s, String s1, String s2, Attributes attributes)
        throws SAXException
    {
        s = mCurrent;
        mCurrent = new XMLNode(s, s2);
        if(mRoot == null)
        {
            mRoot = mCurrent;
        } else
        {
            if(s == null)
                throw new SAXException("More than one root nodes");
            s.addChild(mCurrent);
        }
    }

    private XMLNode mCurrent;
    private XMLNode mRoot;
}
