// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.sax;

import java.util.ArrayList;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

// Referenced classes of package android.sax:
//            BadXmlException, Children, EndElementListener, EndTextElementListener, 
//            StartElementListener, ElementListener, TextElementListener

public class Element
{

    Element(Element element, String s, String s1, int i)
    {
        parent = element;
        uri = s;
        localName = s1;
        depth = i;
    }

    static String toString(String s, String s1)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("'");
        if(!s.equals(""))
            s1 = (new StringBuilder()).append(s).append(":").append(s1).toString();
        return stringbuilder.append(s1).append("'").toString();
    }

    void checkRequiredChildren(Locator locator)
        throws SAXParseException
    {
        ArrayList arraylist = requiredChilden;
        if(arraylist != null)
        {
            for(int i = arraylist.size() - 1; i >= 0; i--)
            {
                Element element = (Element)arraylist.get(i);
                if(!element.visited)
                    throw new BadXmlException((new StringBuilder()).append("Element named ").append(this).append(" is missing required").append(" child element named ").append(element).append(".").toString(), locator);
            }

        }
    }

    public Element getChild(String s)
    {
        return getChild("", s);
    }

    public Element getChild(String s, String s1)
    {
        if(endTextElementListener != null)
            throw new IllegalStateException("This element already has an end text element listener. It cannot have children.");
        if(children == null)
            children = new Children();
        return children.getOrCreate(this, s, s1);
    }

    public Element requireChild(String s)
    {
        return requireChild("", s);
    }

    public Element requireChild(String s, String s1)
    {
        s = getChild(s, s1);
        if(requiredChilden != null) goto _L2; else goto _L1
_L1:
        requiredChilden = new ArrayList();
        requiredChilden.add(s);
_L4:
        return s;
_L2:
        if(!requiredChilden.contains(s))
            requiredChilden.add(s);
        if(true) goto _L4; else goto _L3
_L3:
    }

    void resetRequiredChildren()
    {
        ArrayList arraylist = requiredChilden;
        if(arraylist != null)
        {
            for(int i = arraylist.size() - 1; i >= 0; i--)
                ((Element)arraylist.get(i)).visited = false;

        }
    }

    public void setElementListener(ElementListener elementlistener)
    {
        setStartElementListener(elementlistener);
        setEndElementListener(elementlistener);
    }

    public void setEndElementListener(EndElementListener endelementlistener)
    {
        if(endElementListener != null)
        {
            throw new IllegalStateException("End element listener has already been set.");
        } else
        {
            endElementListener = endelementlistener;
            return;
        }
    }

    public void setEndTextElementListener(EndTextElementListener endtextelementlistener)
    {
        if(endTextElementListener != null)
            throw new IllegalStateException("End text element listener has already been set.");
        if(children != null)
        {
            throw new IllegalStateException("This element already has children. It cannot have an end text element listener.");
        } else
        {
            endTextElementListener = endtextelementlistener;
            return;
        }
    }

    public void setStartElementListener(StartElementListener startelementlistener)
    {
        if(startElementListener != null)
        {
            throw new IllegalStateException("Start element listener has already been set.");
        } else
        {
            startElementListener = startelementlistener;
            return;
        }
    }

    public void setTextElementListener(TextElementListener textelementlistener)
    {
        setStartElementListener(textelementlistener);
        setEndTextElementListener(textelementlistener);
    }

    public String toString()
    {
        return toString(uri, localName);
    }

    Children children;
    final int depth;
    EndElementListener endElementListener;
    EndTextElementListener endTextElementListener;
    final String localName;
    final Element parent;
    ArrayList requiredChilden;
    StartElementListener startElementListener;
    final String uri;
    boolean visited;
}
