// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.*;
import java.io.*;
import java.net.ProtocolException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.xmlpull.v1.*;

// Referenced classes of package com.android.internal.util:
//            FastXmlSerializer

public class XmlUtils
{
    public static interface ReadMapCallback
    {

        public abstract Object readThisUnknownObjectXml(XmlPullParser xmlpullparser, String s)
            throws XmlPullParserException, IOException;
    }

    public static interface WriteMapCallback
    {

        public abstract void writeUnknownObject(Object obj, String s, XmlSerializer xmlserializer)
            throws XmlPullParserException, IOException;
    }


    public XmlUtils()
    {
    }

    public static final void beginDocument(XmlPullParser xmlpullparser, String s)
        throws XmlPullParserException, IOException
    {
        int i;
        do
            i = xmlpullparser.next();
        while(i != 2 && i != 1);
        if(i != 2)
            throw new XmlPullParserException("No start tag found");
        if(!xmlpullparser.getName().equals(s))
            throw new XmlPullParserException((new StringBuilder()).append("Unexpected start tag: found ").append(xmlpullparser.getName()).append(", expected ").append(s).toString());
        else
            return;
    }

    public static final boolean convertValueToBoolean(CharSequence charsequence, boolean flag)
    {
label0:
        {
            boolean flag1 = false;
            if(charsequence == null)
                return flag;
            if(!charsequence.equals("1") && !charsequence.equals("true"))
            {
                flag = flag1;
                if(!charsequence.equals("TRUE"))
                    break label0;
            }
            flag = true;
        }
        return flag;
    }

    public static final int convertValueToInt(CharSequence charsequence, int i)
    {
        byte byte0;
        int j;
        int k;
        if(charsequence == null)
            return i;
        charsequence = charsequence.toString();
        byte0 = 1;
        j = 0;
        k = charsequence.length();
        i = 10;
        if('-' == charsequence.charAt(0))
        {
            byte0 = -1;
            j = 1;
        }
        if('0' != charsequence.charAt(j)) goto _L2; else goto _L1
_L1:
        if(j == k - 1)
            return 0;
        i = charsequence.charAt(j + 1);
        if(120 == i || 88 == i)
        {
            k = j + 2;
            i = 16;
        } else
        {
            k = j + 1;
            i = 8;
        }
_L4:
        return Integer.parseInt(charsequence.substring(k), i) * byte0;
_L2:
        k = j;
        if('#' == charsequence.charAt(j))
        {
            k = j + 1;
            i = 16;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final int convertValueToList(CharSequence charsequence, String as[], int i)
    {
        if(charsequence != null)
        {
            for(int j = 0; j < as.length; j++)
                if(charsequence.equals(as[j]))
                    return j;

        }
        return i;
    }

    public static int convertValueToUnsignedInt(String s, int i)
    {
        if(s == null)
            return i;
        else
            return parseUnsignedIntAttribute(s);
    }

    public static final void nextElement(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i;
        do
            i = xmlpullparser.next();
        while(i != 2 && i != 1);
    }

    public static boolean nextElementWithin(XmlPullParser xmlpullparser, int i)
        throws IOException, XmlPullParserException
    {
        int j;
        do
        {
            j = xmlpullparser.next();
            if(j == 1 || j == 3 && xmlpullparser.getDepth() == i)
                return false;
        } while(j != 2 || xmlpullparser.getDepth() != i + 1);
        return true;
    }

    public static int parseUnsignedIntAttribute(CharSequence charsequence)
    {
        byte byte0;
        int i;
        char c;
        charsequence = charsequence.toString();
        byte0 = 0;
        i = charsequence.length();
        c = '\n';
        if('0' != charsequence.charAt(0)) goto _L2; else goto _L1
_L1:
        if(i - 1 == 0)
            return 0;
        c = charsequence.charAt(1);
        if('x' == c || 'X' == c)
        {
            byte0 = 2;
            c = '\020';
        } else
        {
            byte0 = 1;
            c = '\b';
        }
_L4:
        return (int)Long.parseLong(charsequence.substring(byte0), c);
_L2:
        if('#' == charsequence.charAt(0))
        {
            byte0 = 1;
            c = '\020';
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static Bitmap readBitmapAttribute(XmlPullParser xmlpullparser, String s)
    {
        xmlpullparser = readByteArrayAttribute(xmlpullparser, s);
        if(xmlpullparser != null)
            return BitmapFactory.decodeByteArray(xmlpullparser, 0, xmlpullparser.length);
        else
            return null;
    }

    public static boolean readBooleanAttribute(XmlPullParser xmlpullparser, String s)
    {
        return Boolean.parseBoolean(xmlpullparser.getAttributeValue(null, s));
    }

    public static boolean readBooleanAttribute(XmlPullParser xmlpullparser, String s, boolean flag)
    {
        xmlpullparser = xmlpullparser.getAttributeValue(null, s);
        if(xmlpullparser == null)
            return flag;
        else
            return Boolean.parseBoolean(xmlpullparser);
    }

    public static byte[] readByteArrayAttribute(XmlPullParser xmlpullparser, String s)
    {
        xmlpullparser = xmlpullparser.getAttributeValue(null, s);
        if(xmlpullparser != null)
            return Base64.decode(xmlpullparser, 0);
        else
            return null;
    }

    public static float readFloatAttribute(XmlPullParser xmlpullparser, String s)
        throws IOException
    {
        String s1 = xmlpullparser.getAttributeValue(null, s);
        float f;
        try
        {
            f = Float.parseFloat(s1);
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new ProtocolException((new StringBuilder()).append("problem parsing ").append(s).append("=").append(s1).append(" as long").toString());
        }
        return f;
    }

    public static int readIntAttribute(XmlPullParser xmlpullparser, String s)
        throws IOException
    {
        String s1 = xmlpullparser.getAttributeValue(null, s);
        int i;
        try
        {
            i = Integer.parseInt(s1);
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new ProtocolException((new StringBuilder()).append("problem parsing ").append(s).append("=").append(s1).append(" as int").toString());
        }
        return i;
    }

    public static int readIntAttribute(XmlPullParser xmlpullparser, String s, int i)
    {
        xmlpullparser = xmlpullparser.getAttributeValue(null, s);
        if(TextUtils.isEmpty(xmlpullparser))
            return i;
        int j;
        try
        {
            j = Integer.parseInt(xmlpullparser);
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            return i;
        }
        return j;
    }

    public static final ArrayList readListXml(InputStream inputstream)
        throws XmlPullParserException, IOException
    {
        XmlPullParser xmlpullparser = Xml.newPullParser();
        xmlpullparser.setInput(inputstream, StandardCharsets.UTF_8.name());
        return (ArrayList)readValueXml(xmlpullparser, new String[1]);
    }

    public static long readLongAttribute(XmlPullParser xmlpullparser, String s)
        throws IOException
    {
        String s1 = xmlpullparser.getAttributeValue(null, s);
        long l;
        try
        {
            l = Long.parseLong(s1);
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new ProtocolException((new StringBuilder()).append("problem parsing ").append(s).append("=").append(s1).append(" as long").toString());
        }
        return l;
    }

    public static long readLongAttribute(XmlPullParser xmlpullparser, String s, long l)
    {
        xmlpullparser = xmlpullparser.getAttributeValue(null, s);
        if(TextUtils.isEmpty(xmlpullparser))
            return l;
        long l1;
        try
        {
            l1 = Long.parseLong(xmlpullparser);
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            return l;
        }
        return l1;
    }

    public static final HashMap readMapXml(InputStream inputstream)
        throws XmlPullParserException, IOException
    {
        XmlPullParser xmlpullparser = Xml.newPullParser();
        xmlpullparser.setInput(inputstream, StandardCharsets.UTF_8.name());
        return (HashMap)readValueXml(xmlpullparser, new String[1]);
    }

    public static final HashSet readSetXml(InputStream inputstream)
        throws XmlPullParserException, IOException
    {
        XmlPullParser xmlpullparser = Xml.newPullParser();
        xmlpullparser.setInput(inputstream, null);
        return (HashSet)readValueXml(xmlpullparser, new String[1]);
    }

    public static String readStringAttribute(XmlPullParser xmlpullparser, String s)
    {
        return xmlpullparser.getAttributeValue(null, s);
    }

    public static final ArrayMap readThisArrayMapXml(XmlPullParser xmlpullparser, String s, String as[], ReadMapCallback readmapcallback)
        throws XmlPullParserException, IOException
    {
        ArrayMap arraymap = new ArrayMap();
        int i = xmlpullparser.getEventType();
        do
        {
            int j;
            if(i == 2)
            {
                Object obj = readThisValueXml(xmlpullparser, as, readmapcallback, true);
                arraymap.put(as[0], obj);
            } else
            if(i == 3)
                if(xmlpullparser.getName().equals(s))
                    return arraymap;
                else
                    throw new XmlPullParserException((new StringBuilder()).append("Expected ").append(s).append(" end tag at: ").append(xmlpullparser.getName()).toString());
            j = xmlpullparser.next();
            i = j;
        } while(j != 1);
        throw new XmlPullParserException((new StringBuilder()).append("Document ended before ").append(s).append(" end tag").toString());
    }

    public static final boolean[] readThisBooleanArrayXml(XmlPullParser xmlpullparser, String s, String as[])
        throws XmlPullParserException, IOException
    {
        int i;
        int j;
        int k;
        int l;
        try
        {
            i = Integer.parseInt(xmlpullparser.getAttributeValue(null, "num"));
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need num attribute in string-array");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in num attribute in string-array");
        }
        xmlpullparser.next();
        as = new boolean[i];
        i = 0;
        j = xmlpullparser.getEventType();
        if(j != 2)
            break MISSING_BLOCK_LABEL_208;
        if(!xmlpullparser.getName().equals("item"))
            break; /* Loop/switch isn't completed */
        try
        {
            as[i] = Boolean.parseBoolean(xmlpullparser.getAttributeValue(null, "value"));
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need value attribute in item");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in value attribute in item");
        }
        k = i;
_L4:
        l = xmlpullparser.next();
        j = l;
        i = k;
        if(l == 1)
            throw new XmlPullParserException((new StringBuilder()).append("Document ended before ").append(s).append(" end tag").toString());
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_34;
_L1:
        throw new XmlPullParserException((new StringBuilder()).append("Expected item tag at: ").append(xmlpullparser.getName()).toString());
        k = i;
        if(j == 3)
        {
            if(xmlpullparser.getName().equals(s))
                return as;
            if(xmlpullparser.getName().equals("item"))
                k = i + 1;
            else
                throw new XmlPullParserException((new StringBuilder()).append("Expected ").append(s).append(" end tag at: ").append(xmlpullparser.getName()).toString());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final byte[] readThisByteArrayXml(XmlPullParser xmlpullparser, String s, String as[])
        throws XmlPullParserException, IOException
    {
        int i;
        byte abyte0[];
        int j;
        try
        {
            i = Integer.parseInt(xmlpullparser.getAttributeValue(null, "num"));
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need num attribute in byte-array");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in num attribute in byte-array");
        }
        abyte0 = new byte[i];
        j = xmlpullparser.getEventType();
        int l;
        do
        {
            if(j == 4)
            {
                if(i > 0)
                {
                    as = xmlpullparser.getText();
                    if(as == null || as.length() != i * 2)
                        throw new XmlPullParserException((new StringBuilder()).append("Invalid value found in byte-array: ").append(as).toString());
                    j = 0;
                    while(j < i) 
                    {
                        int k = as.charAt(j * 2);
                        int i1 = as.charAt(j * 2 + 1);
                        if(k > 97)
                            k = (k - 97) + 10;
                        else
                            k -= 48;
                        if(i1 > 97)
                            i1 = (i1 - 97) + 10;
                        else
                            i1 -= 48;
                        abyte0[j] = (byte)((k & 0xf) << 4 | i1 & 0xf);
                        j++;
                    }
                }
            } else
            if(j == 3)
                if(xmlpullparser.getName().equals(s))
                    return abyte0;
                else
                    throw new XmlPullParserException((new StringBuilder()).append("Expected ").append(s).append(" end tag at: ").append(xmlpullparser.getName()).toString());
            l = xmlpullparser.next();
            j = l;
        } while(l != 1);
        throw new XmlPullParserException((new StringBuilder()).append("Document ended before ").append(s).append(" end tag").toString());
    }

    public static final double[] readThisDoubleArrayXml(XmlPullParser xmlpullparser, String s, String as[])
        throws XmlPullParserException, IOException
    {
        int i;
        int j;
        int k;
        int l;
        try
        {
            i = Integer.parseInt(xmlpullparser.getAttributeValue(null, "num"));
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need num attribute in double-array");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in num attribute in double-array");
        }
        xmlpullparser.next();
        as = new double[i];
        i = 0;
        j = xmlpullparser.getEventType();
        if(j != 2)
            break MISSING_BLOCK_LABEL_208;
        if(!xmlpullparser.getName().equals("item"))
            break; /* Loop/switch isn't completed */
        try
        {
            as[i] = Double.parseDouble(xmlpullparser.getAttributeValue(null, "value"));
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need value attribute in item");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in value attribute in item");
        }
        k = i;
_L4:
        l = xmlpullparser.next();
        j = l;
        i = k;
        if(l == 1)
            throw new XmlPullParserException((new StringBuilder()).append("Document ended before ").append(s).append(" end tag").toString());
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_34;
_L1:
        throw new XmlPullParserException((new StringBuilder()).append("Expected item tag at: ").append(xmlpullparser.getName()).toString());
        k = i;
        if(j == 3)
        {
            if(xmlpullparser.getName().equals(s))
                return as;
            if(xmlpullparser.getName().equals("item"))
                k = i + 1;
            else
                throw new XmlPullParserException((new StringBuilder()).append("Expected ").append(s).append(" end tag at: ").append(xmlpullparser.getName()).toString());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final int[] readThisIntArrayXml(XmlPullParser xmlpullparser, String s, String as[])
        throws XmlPullParserException, IOException
    {
        int i;
        int j;
        int k;
        int l;
        try
        {
            i = Integer.parseInt(xmlpullparser.getAttributeValue(null, "num"));
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need num attribute in int-array");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in num attribute in int-array");
        }
        xmlpullparser.next();
        as = new int[i];
        i = 0;
        j = xmlpullparser.getEventType();
        if(j != 2)
            break MISSING_BLOCK_LABEL_208;
        if(!xmlpullparser.getName().equals("item"))
            break; /* Loop/switch isn't completed */
        try
        {
            as[i] = Integer.parseInt(xmlpullparser.getAttributeValue(null, "value"));
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need value attribute in item");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in value attribute in item");
        }
        k = i;
_L4:
        l = xmlpullparser.next();
        j = l;
        i = k;
        if(l == 1)
            throw new XmlPullParserException((new StringBuilder()).append("Document ended before ").append(s).append(" end tag").toString());
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_34;
_L1:
        throw new XmlPullParserException((new StringBuilder()).append("Expected item tag at: ").append(xmlpullparser.getName()).toString());
        k = i;
        if(j == 3)
        {
            if(xmlpullparser.getName().equals(s))
                return as;
            if(xmlpullparser.getName().equals("item"))
                k = i + 1;
            else
                throw new XmlPullParserException((new StringBuilder()).append("Expected ").append(s).append(" end tag at: ").append(xmlpullparser.getName()).toString());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final ArrayList readThisListXml(XmlPullParser xmlpullparser, String s, String as[])
        throws XmlPullParserException, IOException
    {
        return readThisListXml(xmlpullparser, s, as, null, false);
    }

    private static final ArrayList readThisListXml(XmlPullParser xmlpullparser, String s, String as[], ReadMapCallback readmapcallback, boolean flag)
        throws XmlPullParserException, IOException
    {
        ArrayList arraylist = new ArrayList();
        int i = xmlpullparser.getEventType();
        do
        {
            int j;
            if(i == 2)
                arraylist.add(readThisValueXml(xmlpullparser, as, readmapcallback, flag));
            else
            if(i == 3)
                if(xmlpullparser.getName().equals(s))
                    return arraylist;
                else
                    throw new XmlPullParserException((new StringBuilder()).append("Expected ").append(s).append(" end tag at: ").append(xmlpullparser.getName()).toString());
            j = xmlpullparser.next();
            i = j;
        } while(j != 1);
        throw new XmlPullParserException((new StringBuilder()).append("Document ended before ").append(s).append(" end tag").toString());
    }

    public static final long[] readThisLongArrayXml(XmlPullParser xmlpullparser, String s, String as[])
        throws XmlPullParserException, IOException
    {
        int i;
        int j;
        int k;
        int l;
        try
        {
            i = Integer.parseInt(xmlpullparser.getAttributeValue(null, "num"));
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need num attribute in long-array");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in num attribute in long-array");
        }
        xmlpullparser.next();
        as = new long[i];
        i = 0;
        j = xmlpullparser.getEventType();
        if(j != 2)
            break MISSING_BLOCK_LABEL_208;
        if(!xmlpullparser.getName().equals("item"))
            break; /* Loop/switch isn't completed */
        try
        {
            as[i] = Long.parseLong(xmlpullparser.getAttributeValue(null, "value"));
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need value attribute in item");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in value attribute in item");
        }
        k = i;
_L4:
        l = xmlpullparser.next();
        j = l;
        i = k;
        if(l == 1)
            throw new XmlPullParserException((new StringBuilder()).append("Document ended before ").append(s).append(" end tag").toString());
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_34;
_L1:
        throw new XmlPullParserException((new StringBuilder()).append("Expected item tag at: ").append(xmlpullparser.getName()).toString());
        k = i;
        if(j == 3)
        {
            if(xmlpullparser.getName().equals(s))
                return as;
            if(xmlpullparser.getName().equals("item"))
                k = i + 1;
            else
                throw new XmlPullParserException((new StringBuilder()).append("Expected ").append(s).append(" end tag at: ").append(xmlpullparser.getName()).toString());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static final HashMap readThisMapXml(XmlPullParser xmlpullparser, String s, String as[])
        throws XmlPullParserException, IOException
    {
        return readThisMapXml(xmlpullparser, s, as, null);
    }

    public static final HashMap readThisMapXml(XmlPullParser xmlpullparser, String s, String as[], ReadMapCallback readmapcallback)
        throws XmlPullParserException, IOException
    {
        HashMap hashmap = new HashMap();
        int i = xmlpullparser.getEventType();
        do
        {
            int j;
            if(i == 2)
            {
                Object obj = readThisValueXml(xmlpullparser, as, readmapcallback, false);
                hashmap.put(as[0], obj);
            } else
            if(i == 3)
                if(xmlpullparser.getName().equals(s))
                    return hashmap;
                else
                    throw new XmlPullParserException((new StringBuilder()).append("Expected ").append(s).append(" end tag at: ").append(xmlpullparser.getName()).toString());
            j = xmlpullparser.next();
            i = j;
        } while(j != 1);
        throw new XmlPullParserException((new StringBuilder()).append("Document ended before ").append(s).append(" end tag").toString());
    }

    private static final Object readThisPrimitiveValueXml(XmlPullParser xmlpullparser, String s)
        throws XmlPullParserException, IOException
    {
label0:
        {
            try
            {
                if(s.equals("int"))
                    return Integer.valueOf(Integer.parseInt(xmlpullparser.getAttributeValue(null, "value")));
                if(s.equals("long"))
                    return Long.valueOf(xmlpullparser.getAttributeValue(null, "value"));
                if(s.equals("float"))
                    return new Float(xmlpullparser.getAttributeValue(null, "value"));
                if(s.equals("double"))
                    return new Double(xmlpullparser.getAttributeValue(null, "value"));
                if(!s.equals("boolean"))
                    break label0;
                xmlpullparser = Boolean.valueOf(xmlpullparser.getAttributeValue(null, "value"));
            }
            // Misplaced declaration of an exception variable
            catch(XmlPullParser xmlpullparser)
            {
                throw new XmlPullParserException((new StringBuilder()).append("Need value attribute in <").append(s).append(">").toString());
            }
            // Misplaced declaration of an exception variable
            catch(XmlPullParser xmlpullparser)
            {
                throw new XmlPullParserException((new StringBuilder()).append("Not a number in value attribute in <").append(s).append(">").toString());
            }
            return xmlpullparser;
        }
        return null;
    }

    public static final HashSet readThisSetXml(XmlPullParser xmlpullparser, String s, String as[])
        throws XmlPullParserException, IOException
    {
        return readThisSetXml(xmlpullparser, s, as, null, false);
    }

    private static final HashSet readThisSetXml(XmlPullParser xmlpullparser, String s, String as[], ReadMapCallback readmapcallback, boolean flag)
        throws XmlPullParserException, IOException
    {
        HashSet hashset = new HashSet();
        int i = xmlpullparser.getEventType();
        do
        {
            int j;
            if(i == 2)
                hashset.add(readThisValueXml(xmlpullparser, as, readmapcallback, flag));
            else
            if(i == 3)
                if(xmlpullparser.getName().equals(s))
                    return hashset;
                else
                    throw new XmlPullParserException((new StringBuilder()).append("Expected ").append(s).append(" end tag at: ").append(xmlpullparser.getName()).toString());
            j = xmlpullparser.next();
            i = j;
        } while(j != 1);
        throw new XmlPullParserException((new StringBuilder()).append("Document ended before ").append(s).append(" end tag").toString());
    }

    public static final String[] readThisStringArrayXml(XmlPullParser xmlpullparser, String s, String as[])
        throws XmlPullParserException, IOException
    {
        int i;
        int j;
        int k;
        int l;
        try
        {
            i = Integer.parseInt(xmlpullparser.getAttributeValue(null, "num"));
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need num attribute in string-array");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in num attribute in string-array");
        }
        xmlpullparser.next();
        as = new String[i];
        i = 0;
        j = xmlpullparser.getEventType();
        if(j != 2)
            break MISSING_BLOCK_LABEL_206;
        if(!xmlpullparser.getName().equals("item"))
            break; /* Loop/switch isn't completed */
        try
        {
            as[i] = xmlpullparser.getAttributeValue(null, "value");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Need value attribute in item");
        }
        // Misplaced declaration of an exception variable
        catch(XmlPullParser xmlpullparser)
        {
            throw new XmlPullParserException("Not a number in value attribute in item");
        }
        k = i;
_L4:
        l = xmlpullparser.next();
        j = l;
        i = k;
        if(l == 1)
            throw new XmlPullParserException((new StringBuilder()).append("Document ended before ").append(s).append(" end tag").toString());
        if(true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_35;
_L1:
        throw new XmlPullParserException((new StringBuilder()).append("Expected item tag at: ").append(xmlpullparser.getName()).toString());
        k = i;
        if(j == 3)
        {
            if(xmlpullparser.getName().equals(s))
                return as;
            if(xmlpullparser.getName().equals("item"))
                k = i + 1;
            else
                throw new XmlPullParserException((new StringBuilder()).append("Expected ").append(s).append(" end tag at: ").append(xmlpullparser.getName()).toString());
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final Object readThisValueXml(XmlPullParser xmlpullparser, String as[], ReadMapCallback readmapcallback, boolean flag)
        throws XmlPullParserException, IOException
    {
        String s = xmlpullparser.getAttributeValue(null, "name");
        String s1 = xmlpullparser.getName();
        Object obj;
        if(s1.equals("null"))
        {
            obj = null;
        } else
        {
            if(s1.equals("string"))
            {
                readmapcallback = "";
label0:
                do
                    do
                    {
                        i = xmlpullparser.next();
                        if(i != 1)
                        {
                            if(i == 3)
                                if(xmlpullparser.getName().equals("string"))
                                {
                                    as[0] = s;
                                    return readmapcallback;
                                } else
                                {
                                    throw new XmlPullParserException((new StringBuilder()).append("Unexpected end tag in <string>: ").append(xmlpullparser.getName()).toString());
                                }
                            if(i != 4)
                                continue label0;
                            readmapcallback = (new StringBuilder()).append(readmapcallback).append(xmlpullparser.getText()).toString();
                        } else
                        {
                            throw new XmlPullParserException("Unexpected end of document in <string>");
                        }
                    } while(true);
                while(i != 2);
                throw new XmlPullParserException((new StringBuilder()).append("Unexpected start tag in <string>: ").append(xmlpullparser.getName()).toString());
            }
            Object obj1 = readThisPrimitiveValueXml(xmlpullparser, s1);
            obj = obj1;
            if(obj1 == null)
            {
                if(s1.equals("byte-array"))
                {
                    xmlpullparser = readThisByteArrayXml(xmlpullparser, "byte-array", as);
                    as[0] = s;
                    return xmlpullparser;
                }
                if(s1.equals("int-array"))
                {
                    xmlpullparser = readThisIntArrayXml(xmlpullparser, "int-array", as);
                    as[0] = s;
                    return xmlpullparser;
                }
                if(s1.equals("long-array"))
                {
                    xmlpullparser = readThisLongArrayXml(xmlpullparser, "long-array", as);
                    as[0] = s;
                    return xmlpullparser;
                }
                if(s1.equals("double-array"))
                {
                    xmlpullparser = readThisDoubleArrayXml(xmlpullparser, "double-array", as);
                    as[0] = s;
                    return xmlpullparser;
                }
                if(s1.equals("string-array"))
                {
                    xmlpullparser = readThisStringArrayXml(xmlpullparser, "string-array", as);
                    as[0] = s;
                    return xmlpullparser;
                }
                if(s1.equals("boolean-array"))
                {
                    xmlpullparser = readThisBooleanArrayXml(xmlpullparser, "boolean-array", as);
                    as[0] = s;
                    return xmlpullparser;
                }
                if(s1.equals("map"))
                {
                    xmlpullparser.next();
                    if(flag)
                        xmlpullparser = readThisArrayMapXml(xmlpullparser, "map", as, readmapcallback);
                    else
                        xmlpullparser = readThisMapXml(xmlpullparser, "map", as, readmapcallback);
                    as[0] = s;
                    return xmlpullparser;
                }
                if(s1.equals("list"))
                {
                    xmlpullparser.next();
                    xmlpullparser = readThisListXml(xmlpullparser, "list", as, readmapcallback, flag);
                    as[0] = s;
                    return xmlpullparser;
                }
                if(s1.equals("set"))
                {
                    xmlpullparser.next();
                    xmlpullparser = readThisSetXml(xmlpullparser, "set", as, readmapcallback, flag);
                    as[0] = s;
                    return xmlpullparser;
                }
                if(readmapcallback != null)
                {
                    xmlpullparser = ((XmlPullParser) (readmapcallback.readThisUnknownObjectXml(xmlpullparser, s1)));
                    as[0] = s;
                    return xmlpullparser;
                } else
                {
                    throw new XmlPullParserException((new StringBuilder()).append("Unknown tag: ").append(s1).toString());
                }
            }
        }
        do
        {
            int i = xmlpullparser.next();
            if(i != 1)
            {
                if(i == 3)
                    if(xmlpullparser.getName().equals(s1))
                    {
                        as[0] = s;
                        return obj;
                    } else
                    {
                        throw new XmlPullParserException((new StringBuilder()).append("Unexpected end tag in <").append(s1).append(">: ").append(xmlpullparser.getName()).toString());
                    }
                if(i == 4)
                    throw new XmlPullParserException((new StringBuilder()).append("Unexpected text in <").append(s1).append(">: ").append(xmlpullparser.getName()).toString());
                if(i == 2)
                    throw new XmlPullParserException((new StringBuilder()).append("Unexpected start tag in <").append(s1).append(">: ").append(xmlpullparser.getName()).toString());
            } else
            {
                throw new XmlPullParserException((new StringBuilder()).append("Unexpected end of document in <").append(s1).append(">").toString());
            }
        } while(true);
    }

    public static Uri readUriAttribute(XmlPullParser xmlpullparser, String s)
    {
        Object obj = null;
        s = xmlpullparser.getAttributeValue(null, s);
        xmlpullparser = obj;
        if(s != null)
            xmlpullparser = Uri.parse(s);
        return xmlpullparser;
    }

    public static final Object readValueXml(XmlPullParser xmlpullparser, String as[])
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getEventType();
        int j;
        do
        {
            if(i == 2)
                return readThisValueXml(xmlpullparser, as, null, false);
            if(i == 3)
                throw new XmlPullParserException((new StringBuilder()).append("Unexpected end tag at: ").append(xmlpullparser.getName()).toString());
            if(i == 4)
                throw new XmlPullParserException((new StringBuilder()).append("Unexpected text: ").append(xmlpullparser.getText()).toString());
            j = xmlpullparser.next();
            i = j;
        } while(j != 1);
        throw new XmlPullParserException("Unexpected end of document");
    }

    public static void skipCurrentTag(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        int i = xmlpullparser.getDepth();
        int j;
        do
            j = xmlpullparser.next();
        while(j != 1 && (j != 3 || xmlpullparser.getDepth() > i));
    }

    public static void writeBitmapAttribute(XmlSerializer xmlserializer, String s, Bitmap bitmap)
        throws IOException
    {
        if(bitmap != null)
        {
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 90, bytearrayoutputstream);
            writeByteArrayAttribute(xmlserializer, s, bytearrayoutputstream.toByteArray());
        }
    }

    public static final void writeBooleanArrayXml(boolean aflag[], String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        if(aflag == null)
        {
            xmlserializer.startTag(null, "null");
            xmlserializer.endTag(null, "null");
            return;
        }
        xmlserializer.startTag(null, "boolean-array");
        if(s != null)
            xmlserializer.attribute(null, "name", s);
        int i = aflag.length;
        xmlserializer.attribute(null, "num", Integer.toString(i));
        for(int j = 0; j < i; j++)
        {
            xmlserializer.startTag(null, "item");
            xmlserializer.attribute(null, "value", Boolean.toString(aflag[j]));
            xmlserializer.endTag(null, "item");
        }

        xmlserializer.endTag(null, "boolean-array");
    }

    public static void writeBooleanAttribute(XmlSerializer xmlserializer, String s, boolean flag)
        throws IOException
    {
        xmlserializer.attribute(null, s, Boolean.toString(flag));
    }

    public static void writeByteArrayAttribute(XmlSerializer xmlserializer, String s, byte abyte0[])
        throws IOException
    {
        if(abyte0 != null)
            xmlserializer.attribute(null, s, Base64.encodeToString(abyte0, 0));
    }

    public static final void writeByteArrayXml(byte abyte0[], String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        if(abyte0 == null)
        {
            xmlserializer.startTag(null, "null");
            xmlserializer.endTag(null, "null");
            return;
        }
        xmlserializer.startTag(null, "byte-array");
        if(s != null)
            xmlserializer.attribute(null, "name", s);
        int i = abyte0.length;
        xmlserializer.attribute(null, "num", Integer.toString(i));
        s = new StringBuilder(abyte0.length * 2);
        int j = 0;
        while(j < i) 
        {
            byte byte0 = abyte0[j];
            int k = byte0 >> 4 & 0xf;
            if(k >= 10)
                k = (k + 97) - 10;
            else
                k += 48;
            s.append((char)k);
            k = byte0 & 0xf;
            if(k >= 10)
                k = (k + 97) - 10;
            else
                k += 48;
            s.append((char)k);
            j++;
        }
        xmlserializer.text(s.toString());
        xmlserializer.endTag(null, "byte-array");
    }

    public static final void writeDoubleArrayXml(double ad[], String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        if(ad == null)
        {
            xmlserializer.startTag(null, "null");
            xmlserializer.endTag(null, "null");
            return;
        }
        xmlserializer.startTag(null, "double-array");
        if(s != null)
            xmlserializer.attribute(null, "name", s);
        int i = ad.length;
        xmlserializer.attribute(null, "num", Integer.toString(i));
        for(int j = 0; j < i; j++)
        {
            xmlserializer.startTag(null, "item");
            xmlserializer.attribute(null, "value", Double.toString(ad[j]));
            xmlserializer.endTag(null, "item");
        }

        xmlserializer.endTag(null, "double-array");
    }

    public static void writeFloatAttribute(XmlSerializer xmlserializer, String s, float f)
        throws IOException
    {
        xmlserializer.attribute(null, s, Float.toString(f));
    }

    public static final void writeIntArrayXml(int ai[], String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        if(ai == null)
        {
            xmlserializer.startTag(null, "null");
            xmlserializer.endTag(null, "null");
            return;
        }
        xmlserializer.startTag(null, "int-array");
        if(s != null)
            xmlserializer.attribute(null, "name", s);
        int i = ai.length;
        xmlserializer.attribute(null, "num", Integer.toString(i));
        for(int j = 0; j < i; j++)
        {
            xmlserializer.startTag(null, "item");
            xmlserializer.attribute(null, "value", Integer.toString(ai[j]));
            xmlserializer.endTag(null, "item");
        }

        xmlserializer.endTag(null, "int-array");
    }

    public static void writeIntAttribute(XmlSerializer xmlserializer, String s, int i)
        throws IOException
    {
        xmlserializer.attribute(null, s, Integer.toString(i));
    }

    public static final void writeListXml(List list, OutputStream outputstream)
        throws XmlPullParserException, IOException
    {
        XmlSerializer xmlserializer = Xml.newSerializer();
        xmlserializer.setOutput(outputstream, StandardCharsets.UTF_8.name());
        xmlserializer.startDocument(null, Boolean.valueOf(true));
        xmlserializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        writeListXml(list, null, xmlserializer);
        xmlserializer.endDocument();
    }

    public static final void writeListXml(List list, String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        if(list == null)
        {
            xmlserializer.startTag(null, "null");
            xmlserializer.endTag(null, "null");
            return;
        }
        xmlserializer.startTag(null, "list");
        if(s != null)
            xmlserializer.attribute(null, "name", s);
        int i = list.size();
        for(int j = 0; j < i; j++)
            writeValueXml(list.get(j), null, xmlserializer);

        xmlserializer.endTag(null, "list");
    }

    public static final void writeLongArrayXml(long al[], String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        if(al == null)
        {
            xmlserializer.startTag(null, "null");
            xmlserializer.endTag(null, "null");
            return;
        }
        xmlserializer.startTag(null, "long-array");
        if(s != null)
            xmlserializer.attribute(null, "name", s);
        int i = al.length;
        xmlserializer.attribute(null, "num", Integer.toString(i));
        for(int j = 0; j < i; j++)
        {
            xmlserializer.startTag(null, "item");
            xmlserializer.attribute(null, "value", Long.toString(al[j]));
            xmlserializer.endTag(null, "item");
        }

        xmlserializer.endTag(null, "long-array");
    }

    public static void writeLongAttribute(XmlSerializer xmlserializer, String s, long l)
        throws IOException
    {
        xmlserializer.attribute(null, s, Long.toString(l));
    }

    public static final void writeMapXml(Map map, OutputStream outputstream)
        throws XmlPullParserException, IOException
    {
        FastXmlSerializer fastxmlserializer = new FastXmlSerializer();
        fastxmlserializer.setOutput(outputstream, StandardCharsets.UTF_8.name());
        fastxmlserializer.startDocument(null, Boolean.valueOf(true));
        fastxmlserializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        writeMapXml(map, ((String) (null)), ((XmlSerializer) (fastxmlserializer)));
        fastxmlserializer.endDocument();
    }

    public static final void writeMapXml(Map map, String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        writeMapXml(map, s, xmlserializer, null);
    }

    public static final void writeMapXml(Map map, String s, XmlSerializer xmlserializer, WriteMapCallback writemapcallback)
        throws XmlPullParserException, IOException
    {
        if(map == null)
        {
            xmlserializer.startTag(null, "null");
            xmlserializer.endTag(null, "null");
            return;
        }
        xmlserializer.startTag(null, "map");
        if(s != null)
            xmlserializer.attribute(null, "name", s);
        writeMapXml(map, xmlserializer, writemapcallback);
        xmlserializer.endTag(null, "map");
    }

    public static final void writeMapXml(Map map, XmlSerializer xmlserializer, WriteMapCallback writemapcallback)
        throws XmlPullParserException, IOException
    {
        if(map == null)
            return;
        java.util.Map.Entry entry;
        for(map = map.entrySet().iterator(); map.hasNext(); writeValueXml(entry.getValue(), (String)entry.getKey(), xmlserializer, writemapcallback))
            entry = (java.util.Map.Entry)map.next();

    }

    public static final void writeSetXml(Set set, String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        if(set == null)
        {
            xmlserializer.startTag(null, "null");
            xmlserializer.endTag(null, "null");
            return;
        }
        xmlserializer.startTag(null, "set");
        if(s != null)
            xmlserializer.attribute(null, "name", s);
        for(set = set.iterator(); set.hasNext(); writeValueXml(set.next(), null, xmlserializer));
        xmlserializer.endTag(null, "set");
    }

    public static final void writeStringArrayXml(String as[], String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        if(as == null)
        {
            xmlserializer.startTag(null, "null");
            xmlserializer.endTag(null, "null");
            return;
        }
        xmlserializer.startTag(null, "string-array");
        if(s != null)
            xmlserializer.attribute(null, "name", s);
        int i = as.length;
        xmlserializer.attribute(null, "num", Integer.toString(i));
        for(int j = 0; j < i; j++)
        {
            xmlserializer.startTag(null, "item");
            xmlserializer.attribute(null, "value", as[j]);
            xmlserializer.endTag(null, "item");
        }

        xmlserializer.endTag(null, "string-array");
    }

    public static void writeStringAttribute(XmlSerializer xmlserializer, String s, CharSequence charsequence)
        throws IOException
    {
        if(charsequence != null)
            xmlserializer.attribute(null, s, charsequence.toString());
    }

    public static void writeUriAttribute(XmlSerializer xmlserializer, String s, Uri uri)
        throws IOException
    {
        if(uri != null)
            xmlserializer.attribute(null, s, uri.toString());
    }

    public static final void writeValueXml(Object obj, String s, XmlSerializer xmlserializer)
        throws XmlPullParserException, IOException
    {
        writeValueXml(obj, s, xmlserializer, null);
    }

    private static final void writeValueXml(Object obj, String s, XmlSerializer xmlserializer, WriteMapCallback writemapcallback)
        throws XmlPullParserException, IOException
    {
        if(obj == null)
        {
            xmlserializer.startTag(null, "null");
            if(s != null)
                xmlserializer.attribute(null, "name", s);
            xmlserializer.endTag(null, "null");
            return;
        }
        if(obj instanceof String)
        {
            xmlserializer.startTag(null, "string");
            if(s != null)
                xmlserializer.attribute(null, "name", s);
            xmlserializer.text(obj.toString());
            xmlserializer.endTag(null, "string");
            return;
        }
        if(!(obj instanceof Integer)) goto _L2; else goto _L1
_L1:
        writemapcallback = "int";
_L4:
        xmlserializer.startTag(null, writemapcallback);
        if(s != null)
            xmlserializer.attribute(null, "name", s);
        xmlserializer.attribute(null, "value", obj.toString());
        xmlserializer.endTag(null, writemapcallback);
        return;
_L2:
        if(obj instanceof Long)
        {
            writemapcallback = "long";
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof Float)
        {
            writemapcallback = "float";
            continue; /* Loop/switch isn't completed */
        }
        if(obj instanceof Double)
        {
            writemapcallback = "double";
            continue; /* Loop/switch isn't completed */
        }
        if(!(obj instanceof Boolean))
            break; /* Loop/switch isn't completed */
        writemapcallback = "boolean";
        if(true) goto _L4; else goto _L3
_L3:
        if(obj instanceof byte[])
        {
            writeByteArrayXml((byte[])obj, s, xmlserializer);
            return;
        }
        if(obj instanceof int[])
        {
            writeIntArrayXml((int[])obj, s, xmlserializer);
            return;
        }
        if(obj instanceof long[])
        {
            writeLongArrayXml((long[])obj, s, xmlserializer);
            return;
        }
        if(obj instanceof double[])
        {
            writeDoubleArrayXml((double[])obj, s, xmlserializer);
            return;
        }
        if(obj instanceof String[])
        {
            writeStringArrayXml((String[])obj, s, xmlserializer);
            return;
        }
        if(obj instanceof boolean[])
        {
            writeBooleanArrayXml((boolean[])obj, s, xmlserializer);
            return;
        }
        if(obj instanceof Map)
        {
            writeMapXml((Map)obj, s, xmlserializer);
            return;
        }
        if(obj instanceof List)
        {
            writeListXml((List)obj, s, xmlserializer);
            return;
        }
        if(obj instanceof Set)
        {
            writeSetXml((Set)obj, s, xmlserializer);
            return;
        }
        if(obj instanceof CharSequence)
        {
            xmlserializer.startTag(null, "string");
            if(s != null)
                xmlserializer.attribute(null, "name", s);
            xmlserializer.text(obj.toString());
            xmlserializer.endTag(null, "string");
            return;
        }
        if(writemapcallback != null)
        {
            writemapcallback.writeUnknownObject(obj, s, xmlserializer);
            return;
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("writeValueXml: unable to write value ").append(obj).toString());
        }
    }

    private static final String STRING_ARRAY_SEPARATOR = ":";
}
