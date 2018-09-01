// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.text.TextUtils;
import org.w3c.dom.Element;

public class StyleHelper
{

    public StyleHelper()
    {
    }

    public static String getAttr(Element element, String s, miui.maml.StylesManager.Style style)
    {
        element = element.getAttribute(s);
        if(!TextUtils.isEmpty(element))
            return element;
        if(style != null)
            element = style.getAttr(s);
        if(element == null)
            element = "";
        return element;
    }
}
