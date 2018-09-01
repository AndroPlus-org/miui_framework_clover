// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.text.TextUtils;
import java.util.ArrayList;
import miui.maml.data.IndexedVariable;
import miui.maml.data.Variables;
import org.w3c.dom.*;

public class Utils
{
    public static class GetChildWrapper
    {

        public GetChildWrapper getChild(String s)
        {
            return new GetChildWrapper(Utils.getChild(mEle, s));
        }

        public Element getElement()
        {
            return mEle;
        }

        private Element mEle;

        public GetChildWrapper(Element element)
        {
            mEle = element;
        }
    }

    public static class Point
    {

        public void Offset(Point point)
        {
            x = x + point.x;
            y = y + point.y;
        }

        Point minus(Point point)
        {
            return new Point(x - point.x, y - point.y);
        }

        public double x;
        public double y;

        public Point(double d, double d1)
        {
            x = d;
            y = d1;
        }
    }

    public static interface XmlTraverseListener
    {

        public abstract void onChild(Element element);
    }


    public Utils()
    {
    }

    public static double Dist(Point point, Point point1, boolean flag)
    {
        double d = point.x - point1.x;
        double d1 = point.y - point1.y;
        if(flag)
            return Math.sqrt(d * d + d1 * d1);
        else
            return d * d + d1 * d1;
    }

    public static String addFileNameSuffix(String s, String s1)
    {
        return addFileNameSuffix(s, "_", s1);
    }

    public static String addFileNameSuffix(String s, String s1, String s2)
    {
        int i = s.indexOf('.');
        if(i == -1)
            return s;
        else
            return (new StringBuilder(s.substring(0, i))).append(s1).append(s2).append(s.substring(i)).toString();
    }

    private static boolean arrContains(String as[], String s)
    {
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(TextUtils.equals(as[j], s))
                return true;

        return false;
    }

    public static boolean arrayContains(String as[], String s)
    {
        int i = as.length;
        for(int j = 0; j < i; j++)
            if(equals(as[j], s))
                return true;

        return false;
    }

    public static void asserts(boolean flag)
        throws Exception
    {
        asserts(flag, "assert error");
    }

    public static void asserts(boolean flag, String s)
        throws Exception
    {
        if(!flag)
            throw new Exception(s);
        else
            return;
    }

    public static String doubleToString(double d)
    {
        String s = String.valueOf(d);
        String s1 = s;
        if(s.endsWith(".0"))
            s1 = s.substring(0, s.length() - 2);
        return s1;
    }

    public static boolean equals(Object obj, Object obj1)
    {
        boolean flag;
        if(obj != obj1)
        {
            if(obj == null)
                flag = false;
            else
                flag = obj.equals(obj1);
        } else
        {
            flag = true;
        }
        return flag;
    }

    public static float getAttrAsFloat(Element element, String s, float f)
    {
        float f1;
        try
        {
            f1 = Float.parseFloat(element.getAttribute(s));
        }
        // Misplaced declaration of an exception variable
        catch(Element element)
        {
            return f;
        }
        return f1;
    }

    public static float getAttrAsFloatThrows(Element element, String s)
        throws NumberFormatException
    {
        return Float.parseFloat(element.getAttribute(s));
    }

    public static int getAttrAsInt(Element element, String s, int i)
    {
        int j;
        try
        {
            j = Integer.parseInt(element.getAttribute(s));
        }
        // Misplaced declaration of an exception variable
        catch(Element element)
        {
            return i;
        }
        return j;
    }

    public static int getAttrAsIntThrows(Element element, String s)
        throws NumberFormatException
    {
        return Integer.parseInt(element.getAttribute(s));
    }

    public static long getAttrAsLong(Element element, String s, long l)
    {
        long l1;
        try
        {
            l1 = Long.parseLong(element.getAttribute(s));
        }
        // Misplaced declaration of an exception variable
        catch(Element element)
        {
            return l;
        }
        return l1;
    }

    public static long getAttrAsLongThrows(Element element, String s)
        throws NumberFormatException
    {
        return Long.parseLong(element.getAttribute(s));
    }

    public static Element getChild(Element element, String s)
    {
        if(element == null)
            return null;
        NodeList nodelist = element.getChildNodes();
        for(int i = 0; i < nodelist.getLength(); i++)
        {
            element = nodelist.item(i);
            if(element.getNodeType() == 1 && element.getNodeName().equalsIgnoreCase(s))
                return (Element)element;
        }

        return null;
    }

    public static android.graphics.PorterDuff.Mode getPorterDuffMode(int i)
    {
        boolean flag = false;
        android.graphics.PorterDuff.Mode amode[] = (android.graphics.PorterDuff.Mode[])Enum.getSharedConstants(android/graphics/PorterDuff$Mode);
        int j;
        android.graphics.PorterDuff.Mode mode;
        int k;
        if(i < 0)
        {
            j = 0;
        } else
        {
            j = i;
            if(i >= amode.length)
                j = amode.length - 1;
        }
        mode = android.graphics.PorterDuff.Mode.SRC_OVER;
        k = amode.length;
        i = ((flag) ? 1 : 0);
        do
        {
label0:
            {
                android.graphics.PorterDuff.Mode mode1 = mode;
                if(i < k)
                {
                    mode1 = amode[i];
                    if(mode1.ordinal() != j)
                        break label0;
                }
                return mode1;
            }
            i++;
        } while(true);
    }

    public static android.graphics.PorterDuff.Mode getPorterDuffMode(String s)
    {
        if(TextUtils.isEmpty(s))
            return android.graphics.PorterDuff.Mode.SRC_OVER;
        android.graphics.PorterDuff.Mode mode = android.graphics.PorterDuff.Mode.SRC_OVER;
        android.graphics.PorterDuff.Mode amode[] = android.graphics.PorterDuff.Mode.values();
        int i = 0;
        int j = amode.length;
        do
        {
label0:
            {
                android.graphics.PorterDuff.Mode mode1 = mode;
                if(i < j)
                {
                    mode1 = amode[i];
                    if(!s.equalsIgnoreCase(mode1.name()))
                        break label0;
                }
                return mode1;
            }
            i++;
        } while(true);
    }

    public static double getVariableNumber(String s, Variables variables)
    {
        return (new IndexedVariable(s, variables, true)).getDouble();
    }

    public static boolean isProtectedIntent(String s)
    {
        boolean flag;
        if(s == null)
            flag = false;
        else
            flag = INTENT_BLACK_LIST.contains(s.trim());
        return flag;
    }

    public static int mixAlpha(int i, int j)
    {
        if(i < 255) goto _L2; else goto _L1
_L1:
        i = j;
_L4:
        return Math.min(255, Math.max(0, i));
_L2:
        if(j < 255)
            i = Math.round((float)(i * j) / 255F);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String numberToString(Number number)
    {
        String s = String.valueOf(number);
        number = s;
        if(s.endsWith(".0"))
            number = s.substring(0, s.length() - 2);
        return number;
    }

    public static double parseDouble(String s)
    {
        String s1 = s;
        if(s.startsWith("+"))
        {
            s1 = s;
            if(s.length() > 1)
                s1 = s.substring(1);
        }
        return Double.parseDouble(s1);
    }

    public static Point pointProjectionOnSegment(Point point, Point point1, Point point2, boolean flag)
    {
        Point point3;
        double d;
        point3 = point1.minus(point);
        point2 = point2.minus(point);
        d = (point3.x * point2.x + point3.y * point2.y) / Dist(point, point1, false);
        if(d >= 0.0D && d <= 1.0D) goto _L2; else goto _L1
_L1:
        if(flag) goto _L4; else goto _L3
_L3:
        point = null;
_L5:
        return point;
_L4:
        if(d >= 0.0D)
            point = point1;
        if(true) goto _L5; else goto _L2
_L2:
        point3.x = point3.x * d;
        point3.y = point3.y * d;
        point3.Offset(point);
        return point3;
    }

    public static void putVariableNumber(String s, Variables variables, double d)
    {
        variables.put(s, d);
    }

    public static void putVariableNumber(String s, Variables variables, Double double1)
    {
        variables.put(s, double1.doubleValue());
    }

    public static void putVariableString(String s, Variables variables, String s1)
    {
        variables.put(s, s1);
    }

    public static double stringToDouble(String s, double d)
    {
        if(s == null)
            return d;
        double d1;
        try
        {
            d1 = Double.parseDouble(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return d;
        }
        return d1;
    }

    public static void traverseXmlElementChildren(Element element, String s, XmlTraverseListener xmltraverselistener)
    {
        element = element.getChildNodes();
        for(int i = 0; i < element.getLength(); i++)
        {
            Node node = element.item(i);
            if(node.getNodeType() == 1 && (s == null || TextUtils.equals(node.getNodeName(), s)))
                xmltraverselistener.onChild((Element)node);
        }

    }

    public static void traverseXmlElementChildrenTags(Element element, String as[], XmlTraverseListener xmltraverselistener)
    {
        element = element.getChildNodes();
        for(int i = 0; i < element.getLength(); i++)
        {
            Node node = element.item(i);
            String s = node.getNodeName();
            if(node.getNodeType() == 1 && (as == null || arrContains(as, s)))
                xmltraverselistener.onChild((Element)node);
        }

    }

    public byte[] splitByteArray(String s)
    {
        return splitByteArray(s, 10);
    }

    public byte[] splitByteArray(String s, int i)
    {
        if(TextUtils.isEmpty(s))
            return null;
        s = s.split(",");
        int j = s.length;
        byte abyte0[] = new byte[j];
        int k = 0;
        while(k < j) 
        {
            try
            {
                abyte0[k] = Byte.parseByte(s[k], i);
            }
            catch(NumberFormatException numberformatexception) { }
            k++;
        }
        return abyte0;
    }

    public double[] splitDoubleArray(String s)
    {
        if(TextUtils.isEmpty(s))
            return null;
        String as[] = s.split(",");
        int i = as.length;
        double ad[] = new double[i];
        int j = 0;
        while(j < i) 
        {
            try
            {
                ad[j] = Double.parseDouble(as[j]);
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
            j++;
        }
        return ad;
    }

    public int[] splitIntArray(String s)
    {
        return splitIntArray(s, 10);
    }

    public int[] splitIntArray(String s, int i)
    {
        if(TextUtils.isEmpty(s))
            return null;
        String as[] = s.split(",");
        int j = as.length;
        int ai[] = new int[j];
        int k = 0;
        while(k < j) 
        {
            try
            {
                ai[k] = Integer.parseInt(as[k], i);
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
            k++;
        }
        return ai;
    }

    public String[] splitStringArray(String s, String s1)
    {
        if(TextUtils.isEmpty(s))
            return null;
        else
            return s.split(s1);
    }

    private static ArrayList INTENT_BLACK_LIST;

    static 
    {
        INTENT_BLACK_LIST = new ArrayList();
        INTENT_BLACK_LIST.add("android.intent.action.AIRPLANE_MODE");
        INTENT_BLACK_LIST.add("android.intent.action.BATTERY_CHANGED");
        INTENT_BLACK_LIST.add("android.intent.action.BATTERY_LOW");
        INTENT_BLACK_LIST.add("android.intent.action.BATTERY_OKAY");
        INTENT_BLACK_LIST.add("android.intent.action.BOOT_COMPLETED");
        INTENT_BLACK_LIST.add("android.intent.action.CONFIGURATION_CHANGED");
        INTENT_BLACK_LIST.add("android.intent.action.DEVICE_STORAGE_LOW");
        INTENT_BLACK_LIST.add("android.intent.action.DEVICE_STORAGE_OK");
        INTENT_BLACK_LIST.add("android.intent.action.DREAMING_STARTED");
        INTENT_BLACK_LIST.add("android.intent.action.DREAMING_STOPPED");
        INTENT_BLACK_LIST.add("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
        INTENT_BLACK_LIST.add("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
        INTENT_BLACK_LIST.add("android.intent.action.LOCALE_CHANGED");
        INTENT_BLACK_LIST.add("android.intent.action.MY_PACKAGE_REPLACED");
        INTENT_BLACK_LIST.add("android.intent.action.NEW_OUTGOING_CALL");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_ADDED");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_CHANGED");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_DATA_CLEARED");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_FIRST_LAUNCH");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_FULLY_REMOVED");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_INSTALL");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_NEEDS_VERIFICATION");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_REMOVED");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_REPLACED");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_RESTARTED");
        INTENT_BLACK_LIST.add("android.intent.action.PACKAGE_VERIFIED");
        INTENT_BLACK_LIST.add("android.intent.action.ACTION_POWER_CONNECTED");
        INTENT_BLACK_LIST.add("android.intent.action.ACTION_POWER_DISCONNECTED");
        INTENT_BLACK_LIST.add("android.intent.action.REBOOT");
        INTENT_BLACK_LIST.add("android.intent.action.SCREEN_OFF");
        INTENT_BLACK_LIST.add("android.intent.action.SCREEN_ON");
        INTENT_BLACK_LIST.add("android.intent.action.ACTION_SHUTDOWN");
        INTENT_BLACK_LIST.add("android.intent.action.TIMEZONE_CHANGED");
        INTENT_BLACK_LIST.add("android.intent.action.TIME_TICK");
        INTENT_BLACK_LIST.add("android.intent.action.UID_REMOVED");
        INTENT_BLACK_LIST.add("android.intent.action.USER_PRESENT");
    }
}
