// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.sax;

import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

class BadXmlException extends SAXParseException
{

    public BadXmlException(String s, Locator locator)
    {
        super(s, locator);
    }

    public String getMessage()
    {
        return (new StringBuilder()).append("Line ").append(getLineNumber()).append(": ").append(super.getMessage()).toString();
    }
}
