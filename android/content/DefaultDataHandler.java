// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.net.Uri;
import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;
import org.xml.sax.*;

// Referenced classes of package android.content:
//            ContentInsertHandler, ContentResolver, ContentValues

public class DefaultDataHandler
    implements ContentInsertHandler
{

    public DefaultDataHandler()
    {
        mUris = new Stack();
    }

    private Uri insertRow()
    {
        Uri uri = mContentResolver.insert((Uri)mUris.lastElement(), mValues);
        mValues = null;
        return uri;
    }

    private void parseRow(Attributes attributes)
        throws SAXException
    {
label0:
        {
            Object obj = attributes.getValue("uri");
            if(obj != null)
            {
                Uri uri = Uri.parse(((String) (obj)));
                obj = uri;
                if(uri == null)
                    throw new SAXException((new StringBuilder()).append("attribute ").append(attributes.getValue("uri")).append(" parsing failure").toString());
            } else
            {
                if(mUris.size() <= 0)
                    break label0;
                attributes = attributes.getValue("postfix");
                if(attributes != null)
                    obj = Uri.withAppendedPath((Uri)mUris.lastElement(), attributes);
                else
                    obj = (Uri)mUris.lastElement();
            }
            mUris.push(obj);
            return;
        }
        throw new SAXException("attribute parsing failure");
    }

    public void characters(char ac[], int i, int j)
        throws SAXException
    {
    }

    public void endDocument()
        throws SAXException
    {
    }

    public void endElement(String s, String s1, String s2)
        throws SAXException
    {
        if("row".equals(s1))
        {
            if(mUris.empty())
                throw new SAXException("uri mismatch");
            if(mValues != null)
                insertRow();
            mUris.pop();
        }
    }

    public void endPrefixMapping(String s)
        throws SAXException
    {
    }

    public void ignorableWhitespace(char ac[], int i, int j)
        throws SAXException
    {
    }

    public void insert(ContentResolver contentresolver, InputStream inputstream)
        throws IOException, SAXException
    {
        mContentResolver = contentresolver;
        Xml.parse(inputstream, android.util.Xml.Encoding.UTF_8, this);
    }

    public void insert(ContentResolver contentresolver, String s)
        throws SAXException
    {
        mContentResolver = contentresolver;
        Xml.parse(s, this);
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
        if("row".equals(s1))
        {
            if(mValues != null)
            {
                if(mUris.empty())
                    throw new SAXException("uri is empty");
                s = insertRow();
                if(s == null)
                    throw new SAXException((new StringBuilder()).append("insert to uri ").append(((Uri)mUris.lastElement()).toString()).append(" failure").toString());
                mUris.pop();
                mUris.push(s);
                parseRow(attributes);
            } else
            if(attributes.getLength() == 0)
                mUris.push((Uri)mUris.lastElement());
            else
                parseRow(attributes);
        } else
        if("col".equals(s1))
        {
            int i = attributes.getLength();
            if(i != 2)
                throw new SAXException((new StringBuilder()).append("illegal attributes number ").append(i).toString());
            s = attributes.getValue(0);
            s1 = attributes.getValue(1);
            if(s != null && s.length() > 0 && s1 != null && s1.length() > 0)
            {
                if(mValues == null)
                    mValues = new ContentValues();
                mValues.put(s, s1);
            } else
            {
                throw new SAXException("illegal attributes value");
            }
        } else
        if("del".equals(s1))
        {
            s = Uri.parse(attributes.getValue("uri"));
            if(s == null)
                throw new SAXException((new StringBuilder()).append("attribute ").append(attributes.getValue("uri")).append(" parsing failure").toString());
            int k = attributes.getLength() - 2;
            if(k > 0)
            {
                s1 = new String[k];
                for(int j = 0; j < k; j++)
                    s1[j] = attributes.getValue(j + 2);

                mContentResolver.delete(s, attributes.getValue(1), s1);
            } else
            if(k == 0)
                mContentResolver.delete(s, attributes.getValue(1), null);
            else
                mContentResolver.delete(s, null, null);
        } else
        {
            throw new SAXException((new StringBuilder()).append("unknown element: ").append(s1).toString());
        }
    }

    public void startPrefixMapping(String s, String s1)
        throws SAXException
    {
    }

    private static final String ARG = "arg";
    private static final String COL = "col";
    private static final String DEL = "del";
    private static final String POSTFIX = "postfix";
    private static final String ROW = "row";
    private static final String SELECT = "select";
    private static final String URI_STR = "uri";
    private ContentResolver mContentResolver;
    private Stack mUris;
    private ContentValues mValues;
}
