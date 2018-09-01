// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.elements;

import android.text.TextUtils;
import miui.maml.ScreenElementRoot;
import miui.maml.data.IndexedVariable;
import miui.maml.util.Utils;
import org.w3c.dom.Element;

// Referenced classes of package miui.maml.elements:
//            ElementGroup, ScreenElement

public class ScreenElementArray extends ElementGroup
{

    static ScreenElement _2D_wrap0(ScreenElementArray screenelementarray, Element element)
    {
        return screenelementarray.ElementGroup.onCreateChild(element);
    }

    public ScreenElementArray(Element element, final ScreenElementRoot _root)
    {
        super(element, _root);
        final int count = Utils.getAttrAsInt(element, "count", 0);
        String s = element.getAttribute("indexName");
        String s1 = s;
        if(TextUtils.isEmpty(s))
            s1 = "__i";
        Utils.traverseXmlElementChildren(element, null, new miui.maml.util.Utils.XmlTraverseListener() {

            public void onChild(Element element1)
            {
                ElementGroup elementgroup = null;
                for(int i = 0; i < count;)
                {
                    ScreenElement screenelement = ScreenElementArray._2D_wrap0(ScreenElementArray.this, element1);
                    ElementGroup elementgroup1 = elementgroup;
                    if(screenelement != null)
                    {
                        elementgroup1 = elementgroup;
                        if(elementgroup == null)
                        {
                            elementgroup1 = ElementGroup.createArrayGroup(_root, indexVar);
                            elementgroup1.setName(screenelement.getName());
                            addElement(elementgroup1);
                        }
                        elementgroup1.addElement(screenelement);
                    }
                    i++;
                    elementgroup = elementgroup1;
                }

            }

            final ScreenElementArray this$0;
            final ScreenElementRoot val$_root;
            final int val$count;
            final IndexedVariable val$indexVar;

            
            {
                this$0 = ScreenElementArray.this;
                count = i;
                _root = screenelementroot;
                indexVar = indexedvariable;
                super();
            }
        }
);
    }

    protected ScreenElement onCreateChild(Element element)
    {
        return null;
    }

    private static final String DEF_INDEX_VAR_NAME = "__i";
    public static final String TAG_NAME = "Array";
}
