// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.hotspot2.omadm;

import android.text.TextUtils;
import java.util.*;

public class XMLNode
{

    public XMLNode(XMLNode xmlnode, String s)
    {
        mTag = s;
        mParent = xmlnode;
        mTextBuilder = new StringBuilder();
        mText = null;
    }

    public void addChild(XMLNode xmlnode)
    {
        mChildren.add(xmlnode);
    }

    public void addText(String s)
    {
        mTextBuilder.append(s);
    }

    public void close()
    {
        mText = mTextBuilder.toString().trim();
        mTextBuilder = null;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(!(obj instanceof XMLNode))
            return false;
        obj = (XMLNode)obj;
        boolean flag1 = flag;
        if(TextUtils.equals(mTag, ((XMLNode) (obj)).mTag))
        {
            flag1 = flag;
            if(TextUtils.equals(mText, ((XMLNode) (obj)).mText))
                flag1 = mChildren.equals(((XMLNode) (obj)).mChildren);
        }
        return flag1;
    }

    public List getChildren()
    {
        return mChildren;
    }

    public XMLNode getParent()
    {
        return mParent;
    }

    public String getTag()
    {
        return mTag;
    }

    public String getText()
    {
        return mText;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mTag, mText, mChildren
        });
    }

    private final List mChildren = new ArrayList();
    private final XMLNode mParent;
    private final String mTag;
    private String mText;
    private StringBuilder mTextBuilder;
}
