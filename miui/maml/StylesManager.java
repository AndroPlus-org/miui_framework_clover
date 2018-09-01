// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import java.util.HashMap;
import miui.maml.util.Utils;
import org.w3c.dom.*;

public class StylesManager
{
    public class Style
    {

        public String getAttr(String s)
        {
            String s1 = (String)mAttrs.get(s);
            if(s1 != null)
                return s1;
            if(base != null)
                return base.getAttr(s);
            else
                return null;
        }

        public static final String TAG = "Style";
        private Style base;
        private HashMap mAttrs;
        public String name;
        final StylesManager this$0;

        public Style(Element element)
        {
            this$0 = StylesManager.this;
            super();
            mAttrs = new HashMap();
            NamedNodeMap namednodemap = element.getAttributes();
            int i = 0;
            while(i < namednodemap.getLength()) 
            {
                Object obj = namednodemap.item(i);
                element = ((Node) (obj)).getNodeName();
                obj = ((Node) (obj)).getNodeValue();
                if(element.equals("name"))
                    name = ((String) (obj));
                else
                if(element.equals("base"))
                    base = (Style)StylesManager._2D_get0(StylesManager.this).get(obj);
                else
                    mAttrs.put(element, obj);
                i++;
            }
        }
    }


    static HashMap _2D_get0(StylesManager stylesmanager)
    {
        return stylesmanager.mStyles;
    }

    public StylesManager(Element element)
    {
        mStyles = new HashMap();
        Utils.traverseXmlElementChildren(element, "Style", new miui.maml.util.Utils.XmlTraverseListener() {

            public void onChild(Element element1)
            {
                element1 = new Style(element1);
                StylesManager._2D_get0(StylesManager.this).put(((Style) (element1)).name, element1);
            }

            final StylesManager this$0;

            
            {
                this$0 = StylesManager.this;
                super();
            }
        }
);
    }

    public Style getStyle(String s)
    {
        return (Style)mStyles.get(s);
    }

    private HashMap mStyles;
}
