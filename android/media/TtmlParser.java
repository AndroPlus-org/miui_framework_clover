// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import org.xmlpull.v1.*;

// Referenced classes of package android.media:
//            TtmlUtils, TtmlNode, TtmlNodeListener

class TtmlParser
{

    public TtmlParser(TtmlNodeListener ttmlnodelistener)
    {
        mListener = ttmlnodelistener;
    }

    private void extractAttribute(XmlPullParser xmlpullparser, int i, StringBuilder stringbuilder)
    {
        stringbuilder.append(" ");
        stringbuilder.append(xmlpullparser.getAttributeName(i));
        stringbuilder.append("=\"");
        stringbuilder.append(xmlpullparser.getAttributeValue(i));
        stringbuilder.append("\"");
    }

    private boolean isEndOfDoc()
        throws XmlPullParserException
    {
        boolean flag = true;
        if(mParser.getEventType() != 1)
            flag = false;
        return flag;
    }

    private static boolean isSupportedTag(String s)
    {
        return s.equals("tt") || s.equals("head") || s.equals("body") || s.equals("div") || s.equals("p") || s.equals("span") || s.equals("br") || s.equals("style") || s.equals("styling") || s.equals("layout") || s.equals("region") || s.equals("metadata") || s.equals("smpte:image") || s.equals("smpte:data") || s.equals("smpte:information");
    }

    private void loadParser(String s)
        throws XmlPullParserException
    {
        XmlPullParserFactory xmlpullparserfactory = XmlPullParserFactory.newInstance();
        xmlpullparserfactory.setNamespaceAware(false);
        mParser = xmlpullparserfactory.newPullParser();
        s = new StringReader(s);
        mParser.setInput(s);
    }

    private TtmlNode parseNode(TtmlNode ttmlnode)
        throws XmlPullParserException, IOException
    {
        if(mParser.getEventType() != 2)
            return null;
        StringBuilder stringbuilder = new StringBuilder();
        long l = 0L;
        long l1 = 0x7fffffffffffffffL;
        long l2 = 0L;
        int i = 0;
        while(i < mParser.getAttributeCount()) 
        {
            String s = mParser.getAttributeName(i);
            String s1 = mParser.getAttributeValue(i);
            s = s.replaceFirst("^.*:", "");
            if(s.equals("begin"))
                l = TtmlUtils.parseTimeExpression(s1, 30, 1, 1);
            else
            if(s.equals("end"))
                l1 = TtmlUtils.parseTimeExpression(s1, 30, 1, 1);
            else
            if(s.equals("dur"))
                l2 = TtmlUtils.parseTimeExpression(s1, 30, 1, 1);
            else
                extractAttribute(mParser, i, stringbuilder);
            i++;
        }
        long l3 = l;
        long l4 = l1;
        if(ttmlnode != null)
        {
            l += ttmlnode.mStartTimeMs;
            l3 = l;
            l4 = l1;
            if(l1 != 0x7fffffffffffffffL)
            {
                l4 = l1 + ttmlnode.mStartTimeMs;
                l3 = l;
            }
        }
        l1 = l4;
        if(l2 > 0L)
        {
            if(l4 != 0x7fffffffffffffffL)
                Log.e("TtmlParser", "'dur' and 'end' attributes are defined at the same time.'end' value is ignored.");
            l1 = l3 + l2;
        }
        l = l1;
        if(ttmlnode != null)
        {
            l = l1;
            if(l1 == 0x7fffffffffffffffL)
            {
                l = l1;
                if(ttmlnode.mEndTimeMs != 0x7fffffffffffffffL)
                {
                    l = l1;
                    if(l1 > ttmlnode.mEndTimeMs)
                        l = ttmlnode.mEndTimeMs;
                }
            }
        }
        return new TtmlNode(mParser.getName(), stringbuilder.toString(), null, l3, l, ttmlnode, mCurrentRunId);
    }

    private void parseTtml()
        throws XmlPullParserException, IOException
    {
        LinkedList linkedlist = new LinkedList();
        int i = 0;
        boolean flag = true;
        while(!isEndOfDoc()) 
        {
            int j = mParser.getEventType();
            TtmlNode ttmlnode = (TtmlNode)linkedlist.peekLast();
            int k;
            boolean flag1;
            if(flag)
            {
                if(j == 2)
                {
                    if(!isSupportedTag(mParser.getName()))
                    {
                        Log.w("TtmlParser", (new StringBuilder()).append("Unsupported tag ").append(mParser.getName()).append(" is ignored.").toString());
                        k = i + 1;
                        flag1 = false;
                    } else
                    {
                        TtmlNode ttmlnode1 = parseNode(ttmlnode);
                        linkedlist.addLast(ttmlnode1);
                        flag1 = flag;
                        k = i;
                        if(ttmlnode != null)
                        {
                            ttmlnode.mChildren.add(ttmlnode1);
                            flag1 = flag;
                            k = i;
                        }
                    }
                } else
                if(j == 4)
                {
                    String s = TtmlUtils.applyDefaultSpacePolicy(mParser.getText());
                    flag1 = flag;
                    k = i;
                    if(!TextUtils.isEmpty(s))
                    {
                        ttmlnode.mChildren.add(new TtmlNode("#pcdata", "", s, 0L, 0x7fffffffffffffffL, ttmlnode, mCurrentRunId));
                        flag1 = flag;
                        k = i;
                    }
                } else
                {
                    flag1 = flag;
                    k = i;
                    if(j == 3)
                    {
                        if(mParser.getName().equals("p"))
                            mListener.onTtmlNodeParsed((TtmlNode)linkedlist.getLast());
                        else
                        if(mParser.getName().equals("tt"))
                            mListener.onRootNodeParsed((TtmlNode)linkedlist.getLast());
                        linkedlist.removeLast();
                        flag1 = flag;
                        k = i;
                    }
                }
            } else
            if(j == 2)
            {
                k = i + 1;
                flag1 = flag;
            } else
            {
                flag1 = flag;
                k = i;
                if(j == 3)
                {
                    i--;
                    flag1 = flag;
                    k = i;
                    if(i == 0)
                    {
                        flag1 = true;
                        k = i;
                    }
                }
            }
            mParser.next();
            flag = flag1;
            i = k;
        }
    }

    public void parse(String s, long l)
        throws XmlPullParserException, IOException
    {
        mParser = null;
        mCurrentRunId = l;
        loadParser(s);
        parseTtml();
    }

    private static final int DEFAULT_FRAMERATE = 30;
    private static final int DEFAULT_SUBFRAMERATE = 1;
    private static final int DEFAULT_TICKRATE = 1;
    static final String TAG = "TtmlParser";
    private long mCurrentRunId;
    private final TtmlNodeListener mListener;
    private XmlPullParser mParser;
}
