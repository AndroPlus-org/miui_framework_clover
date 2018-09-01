// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.res;

import android.graphics.Rect;
import android.os.*;
import android.text.TextUtils;
import com.android.internal.util.XmlUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import org.xmlpull.v1.*;

// Referenced classes of package android.content.res:
//            MiuiConfiguration

public final class Configuration
    implements Parcelable, Comparable
{

    public Configuration()
    {
        extraConfig = new MiuiConfiguration();
        unset();
    }

    public Configuration(Configuration configuration)
    {
        extraConfig = new MiuiConfiguration();
        setTo(configuration);
    }

    private Configuration(Parcel parcel)
    {
        extraConfig = new MiuiConfiguration();
        readFromParcel(parcel);
    }

    Configuration(Parcel parcel, Configuration configuration)
    {
        this(parcel);
    }

    public static String configurationDiffToString(int i)
    {
        ArrayList arraylist = new ArrayList();
        if((i & 1) != 0)
            arraylist.add("CONFIG_MCC");
        if((i & 2) != 0)
            arraylist.add("CONFIG_MNC");
        if((i & 4) != 0)
            arraylist.add("CONFIG_LOCALE");
        if((i & 8) != 0)
            arraylist.add("CONFIG_TOUCHSCREEN");
        if((i & 0x10) != 0)
            arraylist.add("CONFIG_KEYBOARD");
        if((i & 0x20) != 0)
            arraylist.add("CONFIG_KEYBOARD_HIDDEN");
        if((i & 0x40) != 0)
            arraylist.add("CONFIG_NAVIGATION");
        if((i & 0x80) != 0)
            arraylist.add("CONFIG_ORIENTATION");
        if((i & 0x100) != 0)
            arraylist.add("CONFIG_SCREEN_LAYOUT");
        if((i & 0x4000) != 0)
            arraylist.add("CONFIG_COLOR_MODE");
        if((i & 0x200) != 0)
            arraylist.add("CONFIG_UI_MODE");
        if((i & 0x400) != 0)
            arraylist.add("CONFIG_SCREEN_SIZE");
        if((i & 0x800) != 0)
            arraylist.add("CONFIG_SMALLEST_SCREEN_SIZE");
        if((i & 0x2000) != 0)
            arraylist.add("CONFIG_LAYOUT_DIRECTION");
        if((0x40000000 & i) != 0)
            arraylist.add("CONFIG_FONT_SCALE");
        if((0x80000000 & i) != 0)
            arraylist.add("CONFIG_ASSETS_PATHS");
        StringBuilder stringbuilder = new StringBuilder("{");
        i = 0;
        for(int j = arraylist.size(); i < j; i++)
        {
            stringbuilder.append((String)arraylist.get(i));
            if(i != j - 1)
                stringbuilder.append(", ");
        }

        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    private void fixUpLocaleList()
    {
        if(locale == null && mLocaleList.isEmpty() ^ true || locale != null && locale.equals(mLocaleList.get(0)) ^ true)
        {
            LocaleList localelist;
            if(locale == null)
                localelist = LocaleList.getEmptyLocaleList();
            else
                localelist = new LocaleList(new Locale[] {
                    locale
                });
            mLocaleList = localelist;
        }
    }

    public static Configuration generateDelta(Configuration configuration, Configuration configuration1)
    {
        Configuration configuration2 = new Configuration();
        if(configuration.fontScale != configuration1.fontScale)
            configuration2.fontScale = configuration1.fontScale;
        if(configuration.mcc != configuration1.mcc)
            configuration2.mcc = configuration1.mcc;
        if(configuration.mnc != configuration1.mnc)
            configuration2.mnc = configuration1.mnc;
        configuration.fixUpLocaleList();
        configuration1.fixUpLocaleList();
        if(!configuration.mLocaleList.equals(configuration1.mLocaleList))
        {
            configuration2.mLocaleList = configuration1.mLocaleList;
            configuration2.locale = configuration1.locale;
        }
        if(configuration.touchscreen != configuration1.touchscreen)
            configuration2.touchscreen = configuration1.touchscreen;
        if(configuration.keyboard != configuration1.keyboard)
            configuration2.keyboard = configuration1.keyboard;
        if(configuration.keyboardHidden != configuration1.keyboardHidden)
            configuration2.keyboardHidden = configuration1.keyboardHidden;
        if(configuration.navigation != configuration1.navigation)
            configuration2.navigation = configuration1.navigation;
        if(configuration.navigationHidden != configuration1.navigationHidden)
            configuration2.navigationHidden = configuration1.navigationHidden;
        if(configuration.orientation != configuration1.orientation)
            configuration2.orientation = configuration1.orientation;
        if((configuration.screenLayout & 0xf) != (configuration1.screenLayout & 0xf))
            configuration2.screenLayout = configuration2.screenLayout | configuration1.screenLayout & 0xf;
        if((configuration.screenLayout & 0xc0) != (configuration1.screenLayout & 0xc0))
            configuration2.screenLayout = configuration2.screenLayout | configuration1.screenLayout & 0xc0;
        if((configuration.screenLayout & 0x30) != (configuration1.screenLayout & 0x30))
            configuration2.screenLayout = configuration2.screenLayout | configuration1.screenLayout & 0x30;
        if((configuration.screenLayout & 0x300) != (configuration1.screenLayout & 0x300))
            configuration2.screenLayout = configuration2.screenLayout | configuration1.screenLayout & 0x300;
        if((configuration.colorMode & 3) != (configuration1.colorMode & 3))
            configuration2.colorMode = configuration2.colorMode | configuration1.colorMode & 3;
        if((configuration.colorMode & 0xc) != (configuration1.colorMode & 0xc))
            configuration2.colorMode = configuration2.colorMode | configuration1.colorMode & 0xc;
        if((configuration.uiMode & 0xf) != (configuration1.uiMode & 0xf))
            configuration2.uiMode = configuration2.uiMode | configuration1.uiMode & 0xf;
        if((configuration.uiMode & 0x30) != (configuration1.uiMode & 0x30))
            configuration2.uiMode = configuration2.uiMode | configuration1.uiMode & 0x30;
        if(configuration.screenWidthDp != configuration1.screenWidthDp)
            configuration2.screenWidthDp = configuration1.screenWidthDp;
        if(configuration.screenHeightDp != configuration1.screenHeightDp)
            configuration2.screenHeightDp = configuration1.screenHeightDp;
        if(configuration.smallestScreenWidthDp != configuration1.smallestScreenWidthDp)
            configuration2.smallestScreenWidthDp = configuration1.smallestScreenWidthDp;
        if(configuration.densityDpi != configuration1.densityDpi)
            configuration2.densityDpi = configuration1.densityDpi;
        if(configuration.assetsSeq != configuration1.assetsSeq)
            configuration2.assetsSeq = configuration1.assetsSeq;
        if(configuration.extraConfig.compareTo(configuration1.extraConfig) != 0)
            configuration2.extraConfig.setTo(configuration1.extraConfig);
        return configuration2;
    }

    private static int getScreenLayoutNoDirection(int i)
    {
        return i & 0xffffff3f;
    }

    public static String localesToResourceQualifier(LocaleList localelist)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = 0;
        while(i < localelist.size()) 
        {
            Locale locale1 = localelist.get(i);
            int j = locale1.getLanguage().length();
            if(j != 0)
            {
                int k = locale1.getScript().length();
                int l = locale1.getCountry().length();
                int i1 = locale1.getVariant().length();
                if(stringbuilder.length() != 0)
                    stringbuilder.append(",");
                if(j == 2 && k == 0 && (l == 0 || l == 2) && i1 == 0)
                {
                    stringbuilder.append(locale1.getLanguage());
                    if(l == 2)
                        stringbuilder.append("-r").append(locale1.getCountry());
                } else
                {
                    stringbuilder.append("b+");
                    stringbuilder.append(locale1.getLanguage());
                    if(k != 0)
                    {
                        stringbuilder.append("+");
                        stringbuilder.append(locale1.getScript());
                    }
                    if(l != 0)
                    {
                        stringbuilder.append("+");
                        stringbuilder.append(locale1.getCountry());
                    }
                    if(i1 != 0)
                    {
                        stringbuilder.append("+");
                        stringbuilder.append(locale1.getVariant());
                    }
                }
            }
            i++;
        }
        return stringbuilder.toString();
    }

    public static boolean needNewResources(int i, int j)
    {
        boolean flag;
        if((i & (0x80000000 | j | 0x40000000)) == 0)
            flag = MiuiConfiguration.needNewResources(i);
        else
            flag = true;
        return flag;
    }

    public static void readXmlAttrs(XmlPullParser xmlpullparser, Configuration configuration)
        throws XmlPullParserException, IOException
    {
        configuration.fontScale = Float.intBitsToFloat(XmlUtils.readIntAttribute(xmlpullparser, "fs", 0));
        configuration.mcc = XmlUtils.readIntAttribute(xmlpullparser, "mcc", 0);
        configuration.mnc = XmlUtils.readIntAttribute(xmlpullparser, "mnc", 0);
        configuration.mLocaleList = LocaleList.forLanguageTags(XmlUtils.readStringAttribute(xmlpullparser, "locales"));
        configuration.locale = configuration.mLocaleList.get(0);
        configuration.touchscreen = XmlUtils.readIntAttribute(xmlpullparser, "touch", 0);
        configuration.keyboard = XmlUtils.readIntAttribute(xmlpullparser, "key", 0);
        configuration.keyboardHidden = XmlUtils.readIntAttribute(xmlpullparser, "keyHid", 0);
        configuration.hardKeyboardHidden = XmlUtils.readIntAttribute(xmlpullparser, "hardKeyHid", 0);
        configuration.navigation = XmlUtils.readIntAttribute(xmlpullparser, "nav", 0);
        configuration.navigationHidden = XmlUtils.readIntAttribute(xmlpullparser, "navHid", 0);
        configuration.orientation = XmlUtils.readIntAttribute(xmlpullparser, "ori", 0);
        configuration.screenLayout = XmlUtils.readIntAttribute(xmlpullparser, "scrLay", 0);
        configuration.colorMode = XmlUtils.readIntAttribute(xmlpullparser, "clrMod", 0);
        configuration.uiMode = XmlUtils.readIntAttribute(xmlpullparser, "ui", 0);
        configuration.screenWidthDp = XmlUtils.readIntAttribute(xmlpullparser, "width", 0);
        configuration.screenHeightDp = XmlUtils.readIntAttribute(xmlpullparser, "height", 0);
        configuration.smallestScreenWidthDp = XmlUtils.readIntAttribute(xmlpullparser, "sw", 0);
        configuration.densityDpi = XmlUtils.readIntAttribute(xmlpullparser, "density", 0);
        configuration.appBounds = Rect.unflattenFromString(XmlUtils.readStringAttribute(xmlpullparser, "app_bounds"));
    }

    public static int reduceScreenLayout(int i, int j, int k)
    {
        if(j >= 470) goto _L2; else goto _L1
_L1:
        int l;
        boolean flag;
        l = 1;
        j = 0;
        flag = false;
_L5:
        k = i;
        if(j == 0)
            k = i & 0xffffffcf | 0x10;
        i = k;
        if(flag)
            i = k | 0x10000000;
        j = i;
        if(l < (i & 0xf))
            j = i & 0xfffffff0 | l;
        return j;
_L2:
        if(j < 960 || k < 720) goto _L4; else goto _L3
_L3:
        l = 4;
_L6:
        if(k > 321 || j > 570)
            flag = true;
        else
            flag = false;
        if((j * 3) / 5 >= k - 1)
            j = 1;
        else
            j = 0;
        if(true) goto _L5; else goto _L4
_L4:
        if(j >= 640 && k >= 480)
            l = 3;
        else
            l = 2;
          goto _L6
    }

    public static int resetScreenLayout(int i)
    {
        return 0xefffffc0 & i | 0x24;
    }

    public static String resourceQualifierString(Configuration configuration)
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        if(configuration.mcc != 0)
        {
            arraylist.add((new StringBuilder()).append("mcc").append(configuration.mcc).toString());
            if(configuration.mnc != 0)
                arraylist.add((new StringBuilder()).append("mnc").append(configuration.mnc).toString());
        }
        if(!configuration.mLocaleList.isEmpty())
        {
            String s = localesToResourceQualifier(configuration.mLocaleList);
            if(!s.isEmpty())
                arraylist.add(s);
        }
        configuration.screenLayout & 0xc0;
        JVM INSTR lookupswitch 2: default 140
    //                   64: 851
    //                   128: 862;
           goto _L1 _L2 _L3
_L1:
        if(configuration.smallestScreenWidthDp != 0)
            arraylist.add((new StringBuilder()).append("sw").append(configuration.smallestScreenWidthDp).append("dp").toString());
        if(configuration.screenWidthDp != 0)
            arraylist.add((new StringBuilder()).append("w").append(configuration.screenWidthDp).append("dp").toString());
        if(configuration.screenHeightDp != 0)
            arraylist.add((new StringBuilder()).append("h").append(configuration.screenHeightDp).append("dp").toString());
        configuration.screenLayout & 0xf;
        JVM INSTR tableswitch 1 4: default 300
    //                   1 873
    //                   2 884
    //                   3 895
    //                   4 906;
           goto _L4 _L5 _L6 _L7 _L8
_L4:
        configuration.screenLayout & 0x30;
        JVM INSTR lookupswitch 2: default 332
    //                   16: 928
    //                   32: 917;
           goto _L9 _L10 _L11
_L9:
        configuration.screenLayout & 0x300;
        JVM INSTR lookupswitch 2: default 368
    //                   256: 950
    //                   512: 939;
           goto _L12 _L13 _L14
_L12:
        configuration.colorMode & 0xc;
        JVM INSTR lookupswitch 2: default 400
    //                   4: 972
    //                   8: 961;
           goto _L15 _L16 _L17
_L15:
        configuration.colorMode & 3;
        JVM INSTR tableswitch 1 2: default 428
    //                   1 994
    //                   2 983;
           goto _L18 _L19 _L20
_L18:
        configuration.orientation;
        JVM INSTR tableswitch 1 2: default 456
    //                   1 1016
    //                   2 1005;
           goto _L21 _L22 _L23
_L21:
        configuration.uiMode & 0xf;
        JVM INSTR tableswitch 2 7: default 500
    //                   2 1038
    //                   3 1060
    //                   4 1049
    //                   5 1027
    //                   6 1071
    //                   7 1082;
           goto _L24 _L25 _L26 _L27 _L28 _L29 _L30
_L24:
        configuration.uiMode & 0x30;
        JVM INSTR lookupswitch 2: default 532
    //                   16: 1104
    //                   32: 1093;
           goto _L31 _L32 _L33
_L31:
        configuration.densityDpi;
        JVM INSTR lookupswitch 10: default 628
    //                   0: 656
    //                   120: 1115
    //                   160: 1126
    //                   213: 1137
    //                   240: 1148
    //                   320: 1159
    //                   480: 1170
    //                   640: 1181
    //                   65534: 1192
    //                   65535: 1203;
           goto _L34 _L35 _L36 _L37 _L38 _L39 _L40 _L41 _L42 _L43 _L44
_L34:
        arraylist.add((new StringBuilder()).append(configuration.densityDpi).append("dpi").toString());
_L35:
        configuration.touchscreen;
        JVM INSTR tableswitch 1 3: default 688
    //                   1 1214
    //                   2 688
    //                   3 1225;
           goto _L45 _L46 _L45 _L47
_L45:
        configuration.keyboardHidden;
        JVM INSTR tableswitch 1 3: default 720
    //                   1 1236
    //                   2 1247
    //                   3 1258;
           goto _L48 _L49 _L50 _L51
_L48:
        configuration.keyboard;
        JVM INSTR tableswitch 1 3: default 752
    //                   1 1269
    //                   2 1280
    //                   3 1291;
           goto _L52 _L53 _L54 _L55
_L52:
        configuration.navigationHidden;
        JVM INSTR tableswitch 1 2: default 780
    //                   1 1302
    //                   2 1313;
           goto _L56 _L57 _L58
_L56:
        configuration.navigation;
        JVM INSTR tableswitch 1 4: default 816
    //                   1 1324
    //                   2 1335
    //                   3 1346
    //                   4 1357;
           goto _L59 _L60 _L61 _L62 _L63
_L59:
        arraylist.add((new StringBuilder()).append("v").append(android.os.Build.VERSION.RESOURCES_SDK_INT).toString());
        return TextUtils.join("-", arraylist);
_L2:
        arraylist.add("ldltr");
          goto _L1
_L3:
        arraylist.add("ldrtl");
          goto _L1
_L5:
        arraylist.add("small");
          goto _L4
_L6:
        arraylist.add("normal");
          goto _L4
_L7:
        arraylist.add("large");
          goto _L4
_L8:
        arraylist.add("xlarge");
          goto _L4
_L11:
        arraylist.add("long");
          goto _L9
_L10:
        arraylist.add("notlong");
          goto _L9
_L14:
        arraylist.add("round");
          goto _L12
_L13:
        arraylist.add("notround");
          goto _L12
_L17:
        arraylist.add("highdr");
          goto _L15
_L16:
        arraylist.add("lowdr");
          goto _L15
_L20:
        arraylist.add("widecg");
          goto _L18
_L19:
        arraylist.add("nowidecg");
          goto _L18
_L23:
        arraylist.add("land");
          goto _L21
_L22:
        arraylist.add("port");
          goto _L21
_L28:
        arraylist.add("appliance");
          goto _L24
_L25:
        arraylist.add("desk");
          goto _L24
_L27:
        arraylist.add("television");
          goto _L24
_L26:
        arraylist.add("car");
          goto _L24
_L29:
        arraylist.add("watch");
          goto _L24
_L30:
        arraylist.add("vrheadset");
          goto _L24
_L33:
        arraylist.add("night");
          goto _L31
_L32:
        arraylist.add("notnight");
          goto _L31
_L36:
        arraylist.add("ldpi");
          goto _L35
_L37:
        arraylist.add("mdpi");
          goto _L35
_L38:
        arraylist.add("tvdpi");
          goto _L35
_L39:
        arraylist.add("hdpi");
          goto _L35
_L40:
        arraylist.add("xhdpi");
          goto _L35
_L41:
        arraylist.add("xxhdpi");
          goto _L35
_L42:
        arraylist.add("xxxhdpi");
          goto _L35
_L43:
        arraylist.add("anydpi");
          goto _L35
_L44:
        arraylist.add("nodpi");
          goto _L34
_L46:
        arraylist.add("notouch");
          goto _L45
_L47:
        arraylist.add("finger");
          goto _L45
_L49:
        arraylist.add("keysexposed");
          goto _L48
_L50:
        arraylist.add("keyshidden");
          goto _L48
_L51:
        arraylist.add("keyssoft");
          goto _L48
_L53:
        arraylist.add("nokeys");
          goto _L52
_L54:
        arraylist.add("qwerty");
          goto _L52
_L55:
        arraylist.add("12key");
          goto _L52
_L57:
        arraylist.add("navexposed");
          goto _L56
_L58:
        arraylist.add("navhidden");
          goto _L56
_L60:
        arraylist.add("nonav");
          goto _L59
_L61:
        arraylist.add("dpad");
          goto _L59
_L62:
        arraylist.add("trackball");
          goto _L59
_L63:
        arraylist.add("wheel");
          goto _L59
    }

    public static void writeXmlAttrs(XmlSerializer xmlserializer, Configuration configuration)
        throws IOException
    {
        XmlUtils.writeIntAttribute(xmlserializer, "fs", Float.floatToIntBits(configuration.fontScale));
        if(configuration.mcc != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "mcc", configuration.mcc);
        if(configuration.mnc != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "mnc", configuration.mnc);
        configuration.fixUpLocaleList();
        if(!configuration.mLocaleList.isEmpty())
            XmlUtils.writeStringAttribute(xmlserializer, "locales", configuration.mLocaleList.toLanguageTags());
        if(configuration.touchscreen != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "touch", configuration.touchscreen);
        if(configuration.keyboard != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "key", configuration.keyboard);
        if(configuration.keyboardHidden != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "keyHid", configuration.keyboardHidden);
        if(configuration.hardKeyboardHidden != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "hardKeyHid", configuration.hardKeyboardHidden);
        if(configuration.navigation != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "nav", configuration.navigation);
        if(configuration.navigationHidden != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "navHid", configuration.navigationHidden);
        if(configuration.orientation != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "ori", configuration.orientation);
        if(configuration.screenLayout != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "scrLay", configuration.screenLayout);
        if(configuration.colorMode != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "clrMod", configuration.colorMode);
        if(configuration.uiMode != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "ui", configuration.uiMode);
        if(configuration.screenWidthDp != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "width", configuration.screenWidthDp);
        if(configuration.screenHeightDp != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "height", configuration.screenHeightDp);
        if(configuration.smallestScreenWidthDp != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "sw", configuration.smallestScreenWidthDp);
        if(configuration.densityDpi != 0)
            XmlUtils.writeIntAttribute(xmlserializer, "density", configuration.densityDpi);
        if(configuration.appBounds != null)
            XmlUtils.writeStringAttribute(xmlserializer, "app_bounds", configuration.appBounds.flattenToString());
    }

    public void clearLocales()
    {
        mLocaleList = LocaleList.getEmptyLocaleList();
        locale = null;
    }

    public int compareTo(Configuration configuration)
    {
        float f = fontScale;
        float f1 = configuration.fontScale;
        if(f < f1)
            return -1;
        if(f > f1)
            return 1;
        int i = mcc - configuration.mcc;
        if(i != 0)
            return i;
        i = mnc - configuration.mnc;
        if(i != 0)
            return i;
        fixUpLocaleList();
        configuration.fixUpLocaleList();
        if(mLocaleList.isEmpty())
        {
            if(!configuration.mLocaleList.isEmpty())
                return 1;
        } else
        {
            if(configuration.mLocaleList.isEmpty())
                return -1;
            int j = Math.min(mLocaleList.size(), configuration.mLocaleList.size());
            for(i = 0; i < j; i++)
            {
                Locale locale1 = mLocaleList.get(i);
                Locale locale2 = configuration.mLocaleList.get(i);
                int l = locale1.getLanguage().compareTo(locale2.getLanguage());
                if(l != 0)
                    return l;
                l = locale1.getCountry().compareTo(locale2.getCountry());
                if(l != 0)
                    return l;
                l = locale1.getVariant().compareTo(locale2.getVariant());
                if(l != 0)
                    return l;
                l = locale1.toLanguageTag().compareTo(locale2.toLanguageTag());
                if(l != 0)
                    return l;
            }

            i = mLocaleList.size() - configuration.mLocaleList.size();
            if(i != 0)
                return i;
        }
        i = touchscreen - configuration.touchscreen;
        if(i != 0)
            return i;
        i = keyboard - configuration.keyboard;
        if(i != 0)
            return i;
        i = keyboardHidden - configuration.keyboardHidden;
        if(i != 0)
            return i;
        i = hardKeyboardHidden - configuration.hardKeyboardHidden;
        if(i != 0)
            return i;
        i = navigation - configuration.navigation;
        if(i != 0)
            return i;
        i = navigationHidden - configuration.navigationHidden;
        if(i != 0)
            return i;
        i = orientation - configuration.orientation;
        if(i != 0)
            return i;
        i = colorMode - configuration.colorMode;
        if(i != 0)
            return i;
        i = screenLayout - configuration.screenLayout;
        if(i != 0)
            return i;
        i = uiMode - configuration.uiMode;
        if(i != 0)
            return i;
        i = screenWidthDp - configuration.screenWidthDp;
        if(i != 0)
            return i;
        i = screenHeightDp - configuration.screenHeightDp;
        if(i != 0)
            return i;
        i = smallestScreenWidthDp - configuration.smallestScreenWidthDp;
        if(i != 0)
            return i;
        i = densityDpi - configuration.densityDpi;
        if(i != 0)
            return i;
        int k = assetsSeq - configuration.assetsSeq;
        if(k != 0)
            return k;
        if(appBounds == null && configuration.appBounds != null)
            return 1;
        if(appBounds != null && configuration.appBounds == null)
            return -1;
        i = k;
        if(appBounds != null)
        {
            i = k;
            if(configuration.appBounds != null)
            {
                i = appBounds.left - configuration.appBounds.left;
                if(i != 0)
                    return i;
                i = appBounds.top - configuration.appBounds.top;
                if(i != 0)
                    return i;
                i = appBounds.right - configuration.appBounds.right;
                if(i != 0)
                    return i;
                k = appBounds.bottom - configuration.appBounds.bottom;
                i = k;
                if(k != 0)
                    return k;
            }
        }
        k = i;
        if(i == 0)
            k = extraConfig.compareTo(configuration.extraConfig);
        return k;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Configuration)obj);
    }

    public int describeContents()
    {
        return 0;
    }

    public int diff(Configuration configuration)
    {
        return diff(configuration, false, false);
    }

    public int diff(Configuration configuration, boolean flag, boolean flag1)
    {
        int j;
label0:
        {
            boolean flag2 = false;
            if(!flag)
            {
                j = ((flag2) ? 1 : 0);
                if(configuration.fontScale <= 0.0F)
                    break label0;
            }
            j = ((flag2) ? 1 : 0);
            if(fontScale != configuration.fontScale)
                j = 0x40000000;
        }
        int i;
label1:
        {
            if(!flag)
            {
                i = j;
                if(configuration.mcc == 0)
                    break label1;
            }
            i = j;
            if(mcc != configuration.mcc)
                i = j | 1;
        }
label2:
        {
            if(!flag)
            {
                j = i;
                if(configuration.mnc == 0)
                    break label2;
            }
            j = i;
            if(mnc != configuration.mnc)
                j = i | 2;
        }
label3:
        {
            fixUpLocaleList();
            configuration.fixUpLocaleList();
            if(!flag)
            {
                i = j;
                if(!(configuration.mLocaleList.isEmpty() ^ true))
                    break label3;
            }
            i = j;
            if(mLocaleList.equals(configuration.mLocaleList) ^ true)
                i = j | 4 | 0x2000;
        }
label4:
        {
            int k = configuration.screenLayout & 0xc0;
            if(!flag)
            {
                j = i;
                if(k == 0)
                    break label4;
            }
            j = i;
            if(k != (screenLayout & 0xc0))
                j = i | 0x2000;
        }
        int l;
label5:
        {
            if(!flag)
            {
                l = j;
                if(configuration.touchscreen == 0)
                    break label5;
            }
            l = j;
            if(touchscreen != configuration.touchscreen)
                l = j | 8;
        }
label6:
        {
            if(!flag)
            {
                i = l;
                if(configuration.keyboard == 0)
                    break label6;
            }
            i = l;
            if(keyboard != configuration.keyboard)
                i = l | 0x10;
        }
label7:
        {
            if(!flag)
            {
                j = i;
                if(configuration.keyboardHidden == 0)
                    break label7;
            }
            j = i;
            if(keyboardHidden != configuration.keyboardHidden)
                j = i | 0x20;
        }
label8:
        {
            if(!flag)
            {
                i = j;
                if(configuration.hardKeyboardHidden == 0)
                    break label8;
            }
            i = j;
            if(hardKeyboardHidden != configuration.hardKeyboardHidden)
                i = j | 0x20;
        }
label9:
        {
            if(!flag)
            {
                j = i;
                if(configuration.navigation == 0)
                    break label9;
            }
            j = i;
            if(navigation != configuration.navigation)
                j = i | 0x40;
        }
label10:
        {
            if(!flag)
            {
                l = j;
                if(configuration.navigationHidden == 0)
                    break label10;
            }
            l = j;
            if(navigationHidden != configuration.navigationHidden)
                l = j | 0x20;
        }
label11:
        {
            if(!flag)
            {
                i = l;
                if(configuration.orientation == 0)
                    break label11;
            }
            i = l;
            if(orientation != configuration.orientation)
                i = l | 0x80;
        }
label12:
        {
            if(!flag)
            {
                j = i;
                if(getScreenLayoutNoDirection(configuration.screenLayout) == 0)
                    break label12;
            }
            j = i;
            if(getScreenLayoutNoDirection(screenLayout) != getScreenLayoutNoDirection(configuration.screenLayout))
                j = i | 0x100;
        }
label13:
        {
            if(!flag)
            {
                i = j;
                if((configuration.colorMode & 0xc) == 0)
                    break label13;
            }
            i = j;
            if((colorMode & 0xc) != (configuration.colorMode & 0xc))
                i = j | 0x4000;
        }
label14:
        {
            if(!flag)
            {
                j = i;
                if((configuration.colorMode & 3) == 0)
                    break label14;
            }
            j = i;
            if((colorMode & 3) != (configuration.colorMode & 3))
                j = i | 0x4000;
        }
label15:
        {
            if(!flag)
            {
                i = j;
                if(configuration.uiMode == 0)
                    break label15;
            }
            i = j;
            if(uiMode != configuration.uiMode)
                i = j | 0x200;
        }
label16:
        {
            if(!flag)
            {
                j = i;
                if(configuration.screenWidthDp == 0)
                    break label16;
            }
            j = i;
            if(screenWidthDp != configuration.screenWidthDp)
                j = i | 0x400;
        }
label17:
        {
            if(!flag)
            {
                i = j;
                if(configuration.screenHeightDp == 0)
                    break label17;
            }
            i = j;
            if(screenHeightDp != configuration.screenHeightDp)
                i = j | 0x400;
        }
label18:
        {
            if(!flag)
            {
                l = i;
                if(configuration.smallestScreenWidthDp == 0)
                    break label18;
            }
            l = i;
            if(smallestScreenWidthDp != configuration.smallestScreenWidthDp)
                l = i | 0x800;
        }
label19:
        {
            if(!flag)
            {
                j = l;
                if(configuration.densityDpi == 0)
                    break label19;
            }
            j = l;
            if(densityDpi != configuration.densityDpi)
                j = l | 0x1000;
        }
label20:
        {
            if(!flag)
            {
                i = j;
                if(configuration.assetsSeq == 0)
                    break label20;
            }
            i = j;
            if(assetsSeq != configuration.assetsSeq)
                i = j | 0x80000000;
        }
label21:
        {
            if(!flag)
            {
                j = i;
                if(configuration.appBounds == null)
                    break label21;
            }
            j = i;
            if(appBounds == configuration.appBounds)
                break label21;
            if(appBounds != null && (flag1 || !(appBounds.equals(configuration.appBounds) ^ true)))
            {
                j = i;
                if(!flag1)
                    break label21;
                if(appBounds.width() == configuration.appBounds.width())
                {
                    j = i;
                    if(appBounds.height() == configuration.appBounds.height())
                        break label21;
                }
            }
            j = i | 0x400;
        }
        return j | extraConfig.diff(configuration.extraConfig);
    }

    public int diffPublicOnly(Configuration configuration)
    {
        return diff(configuration, false, true);
    }

    public boolean equals(Configuration configuration)
    {
        boolean flag = true;
        if(configuration == null)
            return false;
        if(configuration == this)
            return true;
        if(compareTo(configuration) != 0)
            flag = false;
        return flag;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        try
        {
            flag = equals((Configuration)obj);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return false;
        }
        return flag;
    }

    public int getLayoutDirection()
    {
        int i;
        if((screenLayout & 0xc0) == 128)
            i = 1;
        else
            i = 0;
        return i;
    }

    public LocaleList getLocales()
    {
        fixUpLocaleList();
        return mLocaleList;
    }

    public int hashCode()
    {
        return (((((((((((((((((((Float.floatToIntBits(fontScale) + 527) * 31 + mcc) * 31 + mnc) * 31 + mLocaleList.hashCode()) * 31 + touchscreen) * 31 + keyboard) * 31 + keyboardHidden) * 31 + hardKeyboardHidden) * 31 + navigation) * 31 + navigationHidden) * 31 + orientation) * 31 + screenLayout) * 31 + colorMode) * 31 + uiMode) * 31 + screenWidthDp) * 31 + screenHeightDp) * 31 + smallestScreenWidthDp) * 31 + densityDpi) * 31 + assetsSeq) * 31 + extraConfig.hashCode();
    }

    public boolean isLayoutSizeAtLeast(int i)
    {
        boolean flag = false;
        int j = screenLayout & 0xf;
        if(j == 0)
            return false;
        if(j >= i)
            flag = true;
        return flag;
    }

    public boolean isOtherSeqNewer(Configuration configuration)
    {
        boolean flag = true;
        if(configuration == null)
            return false;
        if(configuration.seq == 0)
            return true;
        if(seq == 0)
            return true;
        int i = configuration.seq - seq;
        if(i > 0x10000)
            return false;
        if(i <= 0)
            flag = false;
        return flag;
    }

    public boolean isScreenHdr()
    {
        boolean flag;
        if((colorMode & 0xc) == 8)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isScreenRound()
    {
        boolean flag;
        if((screenLayout & 0x300) == 512)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isScreenWideColorGamut()
    {
        boolean flag;
        if((colorMode & 3) == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void makeDefault()
    {
        setToDefaults();
    }

    public void readFromParcel(Parcel parcel)
    {
        boolean flag = true;
        fontScale = parcel.readFloat();
        mcc = parcel.readInt();
        mnc = parcel.readInt();
        int i = parcel.readInt();
        Locale alocale[] = new Locale[i];
        for(int j = 0; j < i; j++)
            alocale[j] = Locale.forLanguageTag(parcel.readString());

        mLocaleList = new LocaleList(alocale);
        locale = mLocaleList.get(0);
        if(parcel.readInt() != 1)
            flag = false;
        userSetLocale = flag;
        touchscreen = parcel.readInt();
        keyboard = parcel.readInt();
        keyboardHidden = parcel.readInt();
        hardKeyboardHidden = parcel.readInt();
        navigation = parcel.readInt();
        navigationHidden = parcel.readInt();
        orientation = parcel.readInt();
        screenLayout = parcel.readInt();
        colorMode = parcel.readInt();
        uiMode = parcel.readInt();
        screenWidthDp = parcel.readInt();
        screenHeightDp = parcel.readInt();
        smallestScreenWidthDp = parcel.readInt();
        densityDpi = parcel.readInt();
        compatScreenWidthDp = parcel.readInt();
        compatScreenHeightDp = parcel.readInt();
        compatSmallestScreenWidthDp = parcel.readInt();
        appBounds = (Rect)parcel.readValue(null);
        assetsSeq = parcel.readInt();
        seq = parcel.readInt();
        extraConfig.readFromParcel(parcel);
    }

    public void setAppBounds(int i, int j, int k, int l)
    {
        if(appBounds == null)
            appBounds = new Rect();
        appBounds.set(i, j, k, l);
    }

    public void setAppBounds(Rect rect)
    {
        if(rect == null)
        {
            appBounds = null;
            return;
        } else
        {
            setAppBounds(rect.left, rect.top, rect.right, rect.bottom);
            return;
        }
    }

    public void setLayoutDirection(Locale locale1)
    {
        int i = TextUtils.getLayoutDirectionFromLocale(locale1);
        screenLayout = screenLayout & 0xffffff3f | i + 1 << 6;
    }

    public void setLocale(Locale locale1)
    {
        if(locale1 == null)
            locale1 = LocaleList.getEmptyLocaleList();
        else
            locale1 = new LocaleList(new Locale[] {
                locale1
            });
        setLocales(locale1);
    }

    public void setLocales(LocaleList localelist)
    {
        LocaleList localelist1 = localelist;
        if(localelist == null)
            localelist1 = LocaleList.getEmptyLocaleList();
        mLocaleList = localelist1;
        locale = mLocaleList.get(0);
        setLayoutDirection(locale);
    }

    public void setTo(Configuration configuration)
    {
        Locale locale1 = null;
        fontScale = configuration.fontScale;
        mcc = configuration.mcc;
        mnc = configuration.mnc;
        if(configuration.locale != null)
            locale1 = (Locale)configuration.locale.clone();
        locale = locale1;
        configuration.fixUpLocaleList();
        mLocaleList = configuration.mLocaleList;
        userSetLocale = configuration.userSetLocale;
        touchscreen = configuration.touchscreen;
        keyboard = configuration.keyboard;
        keyboardHidden = configuration.keyboardHidden;
        hardKeyboardHidden = configuration.hardKeyboardHidden;
        navigation = configuration.navigation;
        navigationHidden = configuration.navigationHidden;
        orientation = configuration.orientation;
        screenLayout = configuration.screenLayout;
        colorMode = configuration.colorMode;
        uiMode = configuration.uiMode;
        screenWidthDp = configuration.screenWidthDp;
        screenHeightDp = configuration.screenHeightDp;
        smallestScreenWidthDp = configuration.smallestScreenWidthDp;
        densityDpi = configuration.densityDpi;
        compatScreenWidthDp = configuration.compatScreenWidthDp;
        compatScreenHeightDp = configuration.compatScreenHeightDp;
        compatSmallestScreenWidthDp = configuration.compatSmallestScreenWidthDp;
        setAppBounds(configuration.appBounds);
        assetsSeq = configuration.assetsSeq;
        seq = configuration.seq;
        extraConfig.setTo(configuration.extraConfig);
    }

    public void setToDefaults()
    {
        fontScale = 1.0F;
        mnc = 0;
        mcc = 0;
        mLocaleList = LocaleList.getEmptyLocaleList();
        locale = null;
        userSetLocale = false;
        touchscreen = 0;
        keyboard = 0;
        keyboardHidden = 0;
        hardKeyboardHidden = 0;
        navigation = 0;
        navigationHidden = 0;
        orientation = 0;
        screenLayout = 0;
        colorMode = 0;
        uiMode = 0;
        compatScreenWidthDp = 0;
        screenWidthDp = 0;
        compatScreenHeightDp = 0;
        screenHeightDp = 0;
        compatSmallestScreenWidthDp = 0;
        smallestScreenWidthDp = 0;
        densityDpi = 0;
        assetsSeq = 0;
        appBounds = null;
        seq = 0;
        extraConfig.setToDefaults();
    }

    public String toString()
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder(128);
        stringbuilder.append("{");
        stringbuilder.append(fontScale);
        stringbuilder.append(" ");
        int i;
        if(mcc != 0)
        {
            stringbuilder.append(mcc);
            stringbuilder.append("mcc");
        } else
        {
            stringbuilder.append("?mcc");
        }
        if(mnc != 0)
        {
            stringbuilder.append(mnc);
            stringbuilder.append("mnc");
        } else
        {
            stringbuilder.append("?mnc");
        }
        fixUpLocaleList();
        if(!mLocaleList.isEmpty())
        {
            stringbuilder.append(" ");
            stringbuilder.append(mLocaleList);
        } else
        {
            stringbuilder.append(" ?localeList");
        }
        i = screenLayout & 0xc0;
        i;
        JVM INSTR lookupswitch 3: default 156
    //                   0: 1150
    //                   64: 1161
    //                   128: 1172;
           goto _L1 _L2 _L3 _L4
_L1:
        stringbuilder.append(" layoutDir=");
        stringbuilder.append(i >> 6);
_L69:
        if(smallestScreenWidthDp != 0)
        {
            stringbuilder.append(" sw");
            stringbuilder.append(smallestScreenWidthDp);
            stringbuilder.append("dp");
        } else
        {
            stringbuilder.append(" ?swdp");
        }
        if(screenWidthDp != 0)
        {
            stringbuilder.append(" w");
            stringbuilder.append(screenWidthDp);
            stringbuilder.append("dp");
        } else
        {
            stringbuilder.append(" ?wdp");
        }
        if(screenHeightDp != 0)
        {
            stringbuilder.append(" h");
            stringbuilder.append(screenHeightDp);
            stringbuilder.append("dp");
        } else
        {
            stringbuilder.append(" ?hdp");
        }
        if(densityDpi != 0)
        {
            stringbuilder.append(" ");
            stringbuilder.append(densityDpi);
            stringbuilder.append("dpi");
        } else
        {
            stringbuilder.append(" ?density");
        }
        screenLayout & 0xf;
        JVM INSTR tableswitch 0 4: default 344
    //                   0 1227
    //                   1 1238
    //                   2 1249
    //                   3 1260
    //                   4 1271;
           goto _L5 _L6 _L7 _L8 _L9 _L10
_L5:
        stringbuilder.append(" layoutSize=");
        stringbuilder.append(screenLayout & 0xf);
_L70:
        screenLayout & 0x30;
        JVM INSTR lookupswitch 3: default 404
    //                   0: 1282
    //                   16: 424
    //                   32: 1293;
           goto _L11 _L12 _L13 _L14
_L11:
        stringbuilder.append(" layoutLong=");
        stringbuilder.append(screenLayout & 0x30);
_L13:
        colorMode & 0xc;
        JVM INSTR lookupswitch 3: default 464
    //                   0: 1304
    //                   4: 484
    //                   8: 1315;
           goto _L15 _L16 _L17 _L18
_L15:
        stringbuilder.append(" dynamicRange=");
        stringbuilder.append(colorMode & 0xc);
_L17:
        colorMode & 3;
        JVM INSTR tableswitch 0 2: default 516
    //                   0 1326
    //                   1 535
    //                   2 1337;
           goto _L19 _L20 _L21 _L22
_L19:
        stringbuilder.append(" wideColorGamut=");
        stringbuilder.append(colorMode & 3);
_L21:
        orientation;
        JVM INSTR tableswitch 0 2: default 564
    //                   0 1348
    //                   1 1370
    //                   2 1359;
           goto _L23 _L24 _L25 _L26
_L23:
        stringbuilder.append(" orien=");
        stringbuilder.append(orientation);
_L71:
        uiMode & 0xf;
        JVM INSTR tableswitch 0 7: default 636
    //                   0 1381
    //                   1 656
    //                   2 1392
    //                   3 1403
    //                   4 1414
    //                   5 1425
    //                   6 1436
    //                   7 1447;
           goto _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35
_L27:
        stringbuilder.append(" uimode=");
        stringbuilder.append(uiMode & 0xf);
_L29:
        uiMode & 0x30;
        JVM INSTR lookupswitch 3: default 696
    //                   0: 1458
    //                   16: 716
    //                   32: 1469;
           goto _L36 _L37 _L38 _L39
_L36:
        stringbuilder.append(" night=");
        stringbuilder.append(uiMode & 0x30);
_L38:
        touchscreen;
        JVM INSTR tableswitch 0 3: default 752
    //                   0 1480
    //                   1 1491
    //                   2 1502
    //                   3 1513;
           goto _L40 _L41 _L42 _L43 _L44
_L40:
        stringbuilder.append(" touch=");
        stringbuilder.append(touchscreen);
_L72:
        keyboard;
        JVM INSTR tableswitch 0 3: default 804
    //                   0 1524
    //                   1 1535
    //                   2 1546
    //                   3 1557;
           goto _L45 _L46 _L47 _L48 _L49
_L45:
        stringbuilder.append(" keys=");
        stringbuilder.append(keyboard);
_L73:
        keyboardHidden;
        JVM INSTR tableswitch 0 3: default 856
    //                   0 1568
    //                   1 1579
    //                   2 1590
    //                   3 1601;
           goto _L50 _L51 _L52 _L53 _L54
_L50:
        stringbuilder.append("/");
        stringbuilder.append(keyboardHidden);
_L74:
        hardKeyboardHidden;
        JVM INSTR tableswitch 0 2: default 904
    //                   0 1612
    //                   1 1623
    //                   2 1634;
           goto _L55 _L56 _L57 _L58
_L55:
        stringbuilder.append("/");
        stringbuilder.append(hardKeyboardHidden);
_L75:
        navigation;
        JVM INSTR tableswitch 0 4: default 960
    //                   0 1645
    //                   1 1656
    //                   2 1667
    //                   3 1678
    //                   4 1689;
           goto _L59 _L60 _L61 _L62 _L63 _L64
_L59:
        stringbuilder.append(" nav=");
        stringbuilder.append(navigation);
_L76:
        navigationHidden;
        JVM INSTR tableswitch 0 2: default 1008
    //                   0 1700
    //                   1 1711
    //                   2 1722;
           goto _L65 _L66 _L67 _L68
_L65:
        stringbuilder.append("/");
        stringbuilder.append(navigationHidden);
_L77:
        if(appBounds != null)
        {
            stringbuilder.append(" appBounds=");
            stringbuilder.append(appBounds);
        }
        if(assetsSeq != 0)
            stringbuilder.append(" as.").append(assetsSeq);
        if(seq != 0)
            stringbuilder.append(" s.").append(seq);
        stringbuilder.append(extraConfig.toString());
        stringbuilder.append('}');
        return stringbuilder.toString();
_L2:
        stringbuilder.append(" ?layoutDir");
          goto _L69
_L3:
        stringbuilder.append(" ldltr");
          goto _L69
_L4:
        stringbuilder.append(" ldrtl");
          goto _L69
_L6:
        stringbuilder.append(" ?lsize");
          goto _L70
_L7:
        stringbuilder.append(" smll");
          goto _L70
_L8:
        stringbuilder.append(" nrml");
          goto _L70
_L9:
        stringbuilder.append(" lrg");
          goto _L70
_L10:
        stringbuilder.append(" xlrg");
          goto _L70
_L12:
        stringbuilder.append(" ?long");
          goto _L13
_L14:
        stringbuilder.append(" long");
          goto _L13
_L16:
        stringbuilder.append(" ?ldr");
          goto _L17
_L18:
        stringbuilder.append(" hdr");
          goto _L17
_L20:
        stringbuilder.append(" ?wideColorGamut");
          goto _L21
_L22:
        stringbuilder.append(" widecg");
          goto _L21
_L24:
        stringbuilder.append(" ?orien");
          goto _L71
_L26:
        stringbuilder.append(" land");
          goto _L71
_L25:
        stringbuilder.append(" port");
          goto _L71
_L28:
        stringbuilder.append(" ?uimode");
          goto _L29
_L30:
        stringbuilder.append(" desk");
          goto _L29
_L31:
        stringbuilder.append(" car");
          goto _L29
_L32:
        stringbuilder.append(" television");
          goto _L29
_L33:
        stringbuilder.append(" appliance");
          goto _L29
_L34:
        stringbuilder.append(" watch");
          goto _L29
_L35:
        stringbuilder.append(" vrheadset");
          goto _L29
_L37:
        stringbuilder.append(" ?night");
          goto _L38
_L39:
        stringbuilder.append(" night");
          goto _L38
_L41:
        stringbuilder.append(" ?touch");
          goto _L72
_L42:
        stringbuilder.append(" -touch");
          goto _L72
_L43:
        stringbuilder.append(" stylus");
          goto _L72
_L44:
        stringbuilder.append(" finger");
          goto _L72
_L46:
        stringbuilder.append(" ?keyb");
          goto _L73
_L47:
        stringbuilder.append(" -keyb");
          goto _L73
_L48:
        stringbuilder.append(" qwerty");
          goto _L73
_L49:
        stringbuilder.append(" 12key");
          goto _L73
_L51:
        stringbuilder.append("/?");
          goto _L74
_L52:
        stringbuilder.append("/v");
          goto _L74
_L53:
        stringbuilder.append("/h");
          goto _L74
_L54:
        stringbuilder.append("/s");
          goto _L74
_L56:
        stringbuilder.append("/?");
          goto _L75
_L57:
        stringbuilder.append("/v");
          goto _L75
_L58:
        stringbuilder.append("/h");
          goto _L75
_L60:
        stringbuilder.append(" ?nav");
          goto _L76
_L61:
        stringbuilder.append(" -nav");
          goto _L76
_L62:
        stringbuilder.append(" dpad");
          goto _L76
_L63:
        stringbuilder.append(" tball");
          goto _L76
_L64:
        stringbuilder.append(" wheel");
          goto _L76
_L66:
        stringbuilder.append("/?");
          goto _L77
_L67:
        stringbuilder.append("/v");
          goto _L77
_L68:
        stringbuilder.append("/h");
          goto _L77
    }

    public void unset()
    {
        setToDefaults();
        fontScale = 0.0F;
    }

    public int updateFrom(Configuration configuration)
    {
        int k;
label0:
        {
            int i = 0;
            k = i;
            if(configuration.fontScale > 0.0F)
            {
                k = i;
                if(fontScale != configuration.fontScale)
                {
                    k = 0x40000000;
                    fontScale = configuration.fontScale;
                }
            }
            int l = k;
            if(configuration.mcc != 0)
            {
                l = k;
                if(mcc != configuration.mcc)
                {
                    l = k | 1;
                    mcc = configuration.mcc;
                }
            }
            i = l;
            if(configuration.mnc != 0)
            {
                i = l;
                if(mnc != configuration.mnc)
                {
                    i = l | 2;
                    mnc = configuration.mnc;
                }
            }
            fixUpLocaleList();
            configuration.fixUpLocaleList();
            k = i;
            if(!configuration.mLocaleList.isEmpty())
            {
                k = i;
                if(mLocaleList.equals(configuration.mLocaleList) ^ true)
                {
                    i |= 4;
                    mLocaleList = configuration.mLocaleList;
                    k = i;
                    if(!configuration.locale.equals(locale))
                    {
                        locale = (Locale)configuration.locale.clone();
                        k = i | 0x2000;
                        setLayoutDirection(locale);
                    }
                }
            }
            l = configuration.screenLayout & 0xc0;
            i = k;
            if(l != 0)
            {
                i = k;
                if(l != (screenLayout & 0xc0))
                {
                    screenLayout = screenLayout & 0xffffff3f | l;
                    i = k | 0x2000;
                }
            }
            k = i;
            if(!configuration.userSetLocale)
                break label0;
            if(userSetLocale)
            {
                k = i;
                if((i & 4) == 0)
                    break label0;
            }
            k = i | 4;
            userSetLocale = true;
        }
        int j = k;
        if(configuration.touchscreen != 0)
        {
            j = k;
            if(touchscreen != configuration.touchscreen)
            {
                j = k | 8;
                touchscreen = configuration.touchscreen;
            }
        }
        k = j;
        if(configuration.keyboard != 0)
        {
            k = j;
            if(keyboard != configuration.keyboard)
            {
                k = j | 0x10;
                keyboard = configuration.keyboard;
            }
        }
        j = k;
        if(configuration.keyboardHidden != 0)
        {
            j = k;
            if(keyboardHidden != configuration.keyboardHidden)
            {
                j = k | 0x20;
                keyboardHidden = configuration.keyboardHidden;
            }
        }
        k = j;
        if(configuration.hardKeyboardHidden != 0)
        {
            k = j;
            if(hardKeyboardHidden != configuration.hardKeyboardHidden)
            {
                k = j | 0x20;
                hardKeyboardHidden = configuration.hardKeyboardHidden;
            }
        }
        j = k;
        if(configuration.navigation != 0)
        {
            j = k;
            if(navigation != configuration.navigation)
            {
                j = k | 0x40;
                navigation = configuration.navigation;
            }
        }
        k = j;
        if(configuration.navigationHidden != 0)
        {
            k = j;
            if(navigationHidden != configuration.navigationHidden)
            {
                k = j | 0x20;
                navigationHidden = configuration.navigationHidden;
            }
        }
        j = k;
        if(configuration.orientation != 0)
        {
            j = k;
            if(orientation != configuration.orientation)
            {
                j = k | 0x80;
                orientation = configuration.orientation;
            }
        }
        k = j;
        if((configuration.screenLayout & 0xf) != 0)
        {
            k = j;
            if((configuration.screenLayout & 0xf) != (screenLayout & 0xf))
            {
                k = j | 0x100;
                screenLayout = screenLayout & 0xfffffff0 | configuration.screenLayout & 0xf;
            }
        }
        j = k;
        if((configuration.screenLayout & 0x30) != 0)
        {
            j = k;
            if((configuration.screenLayout & 0x30) != (screenLayout & 0x30))
            {
                j = k | 0x100;
                screenLayout = screenLayout & 0xffffffcf | configuration.screenLayout & 0x30;
            }
        }
        k = j;
        if((configuration.screenLayout & 0x300) != 0)
        {
            k = j;
            if((configuration.screenLayout & 0x300) != (screenLayout & 0x300))
            {
                k = j | 0x100;
                screenLayout = screenLayout & 0xfffffcff | configuration.screenLayout & 0x300;
            }
        }
        j = k;
        if((configuration.screenLayout & 0x10000000) != (screenLayout & 0x10000000))
        {
            j = k;
            if(configuration.screenLayout != 0)
            {
                j = k | 0x100;
                screenLayout = screenLayout & 0xefffffff | configuration.screenLayout & 0x10000000;
            }
        }
        k = j;
        if((configuration.colorMode & 3) != 0)
        {
            k = j;
            if((configuration.colorMode & 3) != (colorMode & 3))
            {
                k = j | 0x4000;
                colorMode = colorMode & -4 | configuration.colorMode & 3;
            }
        }
        j = k;
        if((configuration.colorMode & 0xc) != 0)
        {
            j = k;
            if((configuration.colorMode & 0xc) != (colorMode & 0xc))
            {
                j = k | 0x4000;
                colorMode = colorMode & 0xfffffff3 | configuration.colorMode & 0xc;
            }
        }
        k = j;
        if(configuration.uiMode != 0)
        {
            k = j;
            if(uiMode != configuration.uiMode)
            {
                j |= 0x200;
                if((configuration.uiMode & 0xf) != 0)
                    uiMode = uiMode & 0xfffffff0 | configuration.uiMode & 0xf;
                k = j;
                if((configuration.uiMode & 0x30) != 0)
                {
                    uiMode = uiMode & 0xffffffcf | configuration.uiMode & 0x30;
                    k = j;
                }
            }
        }
        j = k;
        if(configuration.screenWidthDp != 0)
        {
            j = k;
            if(screenWidthDp != configuration.screenWidthDp)
            {
                j = k | 0x400;
                screenWidthDp = configuration.screenWidthDp;
            }
        }
        k = j;
        if(configuration.screenHeightDp != 0)
        {
            k = j;
            if(screenHeightDp != configuration.screenHeightDp)
            {
                k = j | 0x400;
                screenHeightDp = configuration.screenHeightDp;
            }
        }
        j = k;
        if(configuration.smallestScreenWidthDp != 0)
        {
            j = k;
            if(smallestScreenWidthDp != configuration.smallestScreenWidthDp)
            {
                j = k | 0x800;
                smallestScreenWidthDp = configuration.smallestScreenWidthDp;
            }
        }
        k = j;
        if(configuration.densityDpi != 0)
        {
            k = j;
            if(densityDpi != configuration.densityDpi)
            {
                k = j | 0x1000;
                densityDpi = configuration.densityDpi;
            }
        }
        if(configuration.compatScreenWidthDp != 0)
            compatScreenWidthDp = configuration.compatScreenWidthDp;
        if(configuration.compatScreenHeightDp != 0)
            compatScreenHeightDp = configuration.compatScreenHeightDp;
        if(configuration.compatSmallestScreenWidthDp != 0)
            compatSmallestScreenWidthDp = configuration.compatSmallestScreenWidthDp;
        j = k;
        if(configuration.appBounds != null)
        {
            j = k;
            if(configuration.appBounds.equals(appBounds) ^ true)
            {
                j = k | 0x400;
                setAppBounds(configuration.appBounds);
            }
        }
        k = j;
        if(configuration.assetsSeq != 0)
        {
            k = j;
            if(configuration.assetsSeq != assetsSeq)
            {
                k = j | 0x80000000;
                assetsSeq = configuration.assetsSeq;
            }
        }
        if(configuration.seq != 0)
            seq = configuration.seq;
        return k | extraConfig.updateFrom(configuration.extraConfig);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeFloat(fontScale);
        parcel.writeInt(mcc);
        parcel.writeInt(mnc);
        fixUpLocaleList();
        int j = mLocaleList.size();
        parcel.writeInt(j);
        for(int k = 0; k < j; k++)
            parcel.writeString(mLocaleList.get(k).toLanguageTag());

        if(userSetLocale)
            parcel.writeInt(1);
        else
            parcel.writeInt(0);
        parcel.writeInt(touchscreen);
        parcel.writeInt(keyboard);
        parcel.writeInt(keyboardHidden);
        parcel.writeInt(hardKeyboardHidden);
        parcel.writeInt(navigation);
        parcel.writeInt(navigationHidden);
        parcel.writeInt(orientation);
        parcel.writeInt(screenLayout);
        parcel.writeInt(colorMode);
        parcel.writeInt(uiMode);
        parcel.writeInt(screenWidthDp);
        parcel.writeInt(screenHeightDp);
        parcel.writeInt(smallestScreenWidthDp);
        parcel.writeInt(densityDpi);
        parcel.writeInt(compatScreenWidthDp);
        parcel.writeInt(compatScreenHeightDp);
        parcel.writeInt(compatSmallestScreenWidthDp);
        parcel.writeValue(appBounds);
        parcel.writeInt(assetsSeq);
        parcel.writeInt(seq);
        extraConfig.writeToParcel(parcel, i);
    }

    public static final int ASSETS_SEQ_UNDEFINED = 0;
    public static final int COLOR_MODE_HDR_MASK = 12;
    public static final int COLOR_MODE_HDR_NO = 4;
    public static final int COLOR_MODE_HDR_SHIFT = 2;
    public static final int COLOR_MODE_HDR_UNDEFINED = 0;
    public static final int COLOR_MODE_HDR_YES = 8;
    public static final int COLOR_MODE_UNDEFINED = 0;
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT_MASK = 3;
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT_NO = 1;
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT_UNDEFINED = 0;
    public static final int COLOR_MODE_WIDE_COLOR_GAMUT_YES = 2;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Configuration createFromParcel(Parcel parcel)
        {
            return new Configuration(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Configuration[] newArray(int i)
        {
            return new Configuration[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DENSITY_DPI_ANY = 65534;
    public static final int DENSITY_DPI_NONE = 65535;
    public static final int DENSITY_DPI_UNDEFINED = 0;
    public static final Configuration EMPTY = new Configuration();
    public static final int HARDKEYBOARDHIDDEN_NO = 1;
    public static final int HARDKEYBOARDHIDDEN_UNDEFINED = 0;
    public static final int HARDKEYBOARDHIDDEN_YES = 2;
    public static final int KEYBOARDHIDDEN_NO = 1;
    public static final int KEYBOARDHIDDEN_SOFT = 3;
    public static final int KEYBOARDHIDDEN_UNDEFINED = 0;
    public static final int KEYBOARDHIDDEN_YES = 2;
    public static final int KEYBOARD_12KEY = 3;
    public static final int KEYBOARD_NOKEYS = 1;
    public static final int KEYBOARD_QWERTY = 2;
    public static final int KEYBOARD_UNDEFINED = 0;
    public static final int MNC_ZERO = 65535;
    public static final int NATIVE_CONFIG_COLOR_MODE = 0x10000;
    public static final int NATIVE_CONFIG_DENSITY = 256;
    public static final int NATIVE_CONFIG_KEYBOARD = 16;
    public static final int NATIVE_CONFIG_KEYBOARD_HIDDEN = 32;
    public static final int NATIVE_CONFIG_LAYOUTDIR = 16384;
    public static final int NATIVE_CONFIG_LOCALE = 4;
    public static final int NATIVE_CONFIG_MCC = 1;
    public static final int NATIVE_CONFIG_MNC = 2;
    public static final int NATIVE_CONFIG_NAVIGATION = 64;
    public static final int NATIVE_CONFIG_ORIENTATION = 128;
    public static final int NATIVE_CONFIG_SCREEN_LAYOUT = 2048;
    public static final int NATIVE_CONFIG_SCREEN_SIZE = 512;
    public static final int NATIVE_CONFIG_SMALLEST_SCREEN_SIZE = 8192;
    public static final int NATIVE_CONFIG_TOUCHSCREEN = 8;
    public static final int NATIVE_CONFIG_UI_MODE = 4096;
    public static final int NATIVE_CONFIG_VERSION = 1024;
    public static final int NAVIGATIONHIDDEN_NO = 1;
    public static final int NAVIGATIONHIDDEN_UNDEFINED = 0;
    public static final int NAVIGATIONHIDDEN_YES = 2;
    public static final int NAVIGATION_DPAD = 2;
    public static final int NAVIGATION_NONAV = 1;
    public static final int NAVIGATION_TRACKBALL = 3;
    public static final int NAVIGATION_UNDEFINED = 0;
    public static final int NAVIGATION_WHEEL = 4;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int ORIENTATION_PORTRAIT = 1;
    public static final int ORIENTATION_SQUARE = 3;
    public static final int ORIENTATION_UNDEFINED = 0;
    public static final int SCREENLAYOUT_COMPAT_NEEDED = 0x10000000;
    public static final int SCREENLAYOUT_LAYOUTDIR_LTR = 64;
    public static final int SCREENLAYOUT_LAYOUTDIR_MASK = 192;
    public static final int SCREENLAYOUT_LAYOUTDIR_RTL = 128;
    public static final int SCREENLAYOUT_LAYOUTDIR_SHIFT = 6;
    public static final int SCREENLAYOUT_LAYOUTDIR_UNDEFINED = 0;
    public static final int SCREENLAYOUT_LONG_MASK = 48;
    public static final int SCREENLAYOUT_LONG_NO = 16;
    public static final int SCREENLAYOUT_LONG_UNDEFINED = 0;
    public static final int SCREENLAYOUT_LONG_YES = 32;
    public static final int SCREENLAYOUT_ROUND_MASK = 768;
    public static final int SCREENLAYOUT_ROUND_NO = 256;
    public static final int SCREENLAYOUT_ROUND_SHIFT = 8;
    public static final int SCREENLAYOUT_ROUND_UNDEFINED = 0;
    public static final int SCREENLAYOUT_ROUND_YES = 512;
    public static final int SCREENLAYOUT_SIZE_LARGE = 3;
    public static final int SCREENLAYOUT_SIZE_MASK = 15;
    public static final int SCREENLAYOUT_SIZE_NORMAL = 2;
    public static final int SCREENLAYOUT_SIZE_SMALL = 1;
    public static final int SCREENLAYOUT_SIZE_UNDEFINED = 0;
    public static final int SCREENLAYOUT_SIZE_XLARGE = 4;
    public static final int SCREENLAYOUT_UNDEFINED = 0;
    public static final int SCREEN_HEIGHT_DP_UNDEFINED = 0;
    public static final int SCREEN_WIDTH_DP_UNDEFINED = 0;
    public static final int SMALLEST_SCREEN_WIDTH_DP_UNDEFINED = 0;
    public static final int TOUCHSCREEN_FINGER = 3;
    public static final int TOUCHSCREEN_NOTOUCH = 1;
    public static final int TOUCHSCREEN_STYLUS = 2;
    public static final int TOUCHSCREEN_UNDEFINED = 0;
    public static final int UI_MODE_NIGHT_MASK = 48;
    public static final int UI_MODE_NIGHT_NO = 16;
    public static final int UI_MODE_NIGHT_UNDEFINED = 0;
    public static final int UI_MODE_NIGHT_YES = 32;
    public static final int UI_MODE_TYPE_APPLIANCE = 5;
    public static final int UI_MODE_TYPE_CAR = 3;
    public static final int UI_MODE_TYPE_DESK = 2;
    public static final int UI_MODE_TYPE_MASK = 15;
    public static final int UI_MODE_TYPE_NORMAL = 1;
    public static final int UI_MODE_TYPE_TELEVISION = 4;
    public static final int UI_MODE_TYPE_UNDEFINED = 0;
    public static final int UI_MODE_TYPE_VR_HEADSET = 7;
    public static final int UI_MODE_TYPE_WATCH = 6;
    private static final String XML_ATTR_APP_BOUNDS = "app_bounds";
    private static final String XML_ATTR_COLOR_MODE = "clrMod";
    private static final String XML_ATTR_DENSITY = "density";
    private static final String XML_ATTR_FONT_SCALE = "fs";
    private static final String XML_ATTR_HARD_KEYBOARD_HIDDEN = "hardKeyHid";
    private static final String XML_ATTR_KEYBOARD = "key";
    private static final String XML_ATTR_KEYBOARD_HIDDEN = "keyHid";
    private static final String XML_ATTR_LOCALES = "locales";
    private static final String XML_ATTR_MCC = "mcc";
    private static final String XML_ATTR_MNC = "mnc";
    private static final String XML_ATTR_NAVIGATION = "nav";
    private static final String XML_ATTR_NAVIGATION_HIDDEN = "navHid";
    private static final String XML_ATTR_ORIENTATION = "ori";
    private static final String XML_ATTR_ROTATION = "rot";
    private static final String XML_ATTR_SCREEN_HEIGHT = "height";
    private static final String XML_ATTR_SCREEN_LAYOUT = "scrLay";
    private static final String XML_ATTR_SCREEN_WIDTH = "width";
    private static final String XML_ATTR_SMALLEST_WIDTH = "sw";
    private static final String XML_ATTR_TOUCHSCREEN = "touch";
    private static final String XML_ATTR_UI_MODE = "ui";
    public Rect appBounds;
    public int assetsSeq;
    public int colorMode;
    public int compatScreenHeightDp;
    public int compatScreenWidthDp;
    public int compatSmallestScreenWidthDp;
    public int densityDpi;
    public MiuiConfiguration extraConfig;
    public float fontScale;
    public int hardKeyboardHidden;
    public int keyboard;
    public int keyboardHidden;
    public Locale locale;
    private LocaleList mLocaleList;
    public int mcc;
    public int mnc;
    public int navigation;
    public int navigationHidden;
    public int orientation;
    public int screenHeightDp;
    public int screenLayout;
    public int screenWidthDp;
    public int seq;
    public int smallestScreenWidthDp;
    public int touchscreen;
    public int uiMode;
    public boolean userSetLocale;

}
