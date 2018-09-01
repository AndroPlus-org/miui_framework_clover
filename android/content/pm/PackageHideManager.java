// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.Context;
import android.miui.Shell;
import android.os.Process;
import android.text.TextUtils;
import android.util.*;
import com.android.internal.util.FastXmlSerializer;
import java.io.*;
import java.util.*;
import miui.os.Build;
import miui.util.FeatureParser;
import org.xmlpull.v1.*;

// Referenced classes of package android.content.pm:
//            PackageManager, ApplicationInfo

public class PackageHideManager
{
    static class AppHideConfig
    {

        boolean isFunctionOpen;
        boolean isHide;
        int version;

        AppHideConfig()
        {
        }
    }

    static class AppHideItem
    {

        public String getJoinPath()
        {
            if(pathArray == null)
                return null;
            else
                return TextUtils.join(";", pathArray);
        }

        public static final String PATH_DILIMITER = ";";
        boolean isHide;
        String packageName;
        String pathArray[];

        public transient AppHideItem(String s, boolean flag, String as[])
        {
            packageName = s;
            isHide = flag;
            pathArray = as;
        }
    }


    private PackageHideManager(boolean flag)
    {
        mShouldHideApks = new HashMap();
        init(flag);
    }

    private void clearUserAleadyInstalled(Context context)
    {
        for(Iterator iterator = mShouldHideApks.values().iterator(); iterator.hasNext(); clearUserAleadyInstalled(context, (AppHideItem)iterator.next()));
    }

    private void clearUserAleadyInstalled(Context context, AppHideItem apphideitem)
    {
        int i = 0;
        context = context.getPackageManager();
        int j;
        String s;
        File file;
        try
        {
            context = context.getApplicationInfo(apphideitem.packageName, 0);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            break; /* Loop/switch isn't completed */
        }
        if(context == null) goto _L2; else goto _L1
_L1:
        if(apphideitem.pathArray == null) goto _L2; else goto _L3
_L3:
        apphideitem = apphideitem.pathArray;
        j = apphideitem.length;
_L4:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        s = apphideitem[i];
        if(((ApplicationInfo) (context)).sourceDir != null && ((ApplicationInfo) (context)).sourceDir.equalsIgnoreCase(s) ^ true)
        {
            file = JVM INSTR new #113 <Class File>;
            file.File(s);
            if(file.exists() && file.isFile())
                file.delete();
        }
        i++;
        continue; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L2
_L2:
    }

    public static PackageHideManager getInstance(boolean flag)
    {
        if(sInstance != null) goto _L2; else goto _L1
_L1:
        android/content/pm/PackageHideManager;
        JVM INSTR monitorenter ;
        if(sInstance == null)
        {
            PackageHideManager packagehidemanager = JVM INSTR new #2   <Class PackageHideManager>;
            packagehidemanager.PackageHideManager(flag);
            sInstance = packagehidemanager;
        }
        android/content/pm/PackageHideManager;
        JVM INSTR monitorexit ;
_L2:
        return sInstance;
        Exception exception;
        exception;
        throw exception;
    }

    private void init(boolean flag)
    {
        if(!isValidDevice())
            return;
        mFile = new File("/data/system/app_hide_switch.xml");
        readAppHideConfig();
        if(appHideConfig == null)
        {
            appHideConfig = new AppHideConfig();
            appHideConfig.isFunctionOpen = false;
            appHideConfig.isHide = false;
            appHideConfig.version = 2;
        }
        if(!flag) goto _L2; else goto _L1
_L1:
        appHideConfig.isFunctionOpen = true;
        appHideConfig.isHide = true;
        initHideApks();
        writeAppHideConfig();
_L4:
        return;
_L2:
        if(mFile.exists() && appHideConfig.version != 2)
        {
            appHideConfig.version = 2;
            initHideApks();
            writeAppHideConfig();
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void initHideApks()
    {
        mShouldHideApks.clear();
        String as[] = FeatureParser.getStringArray("hidden_app_packagename_list");
        String as1[] = FeatureParser.getStringArray("hidden_app_path_list");
        if(as != null && as1 != null && as.length == as1.length)
        {
            for(int i = 0; i < as.length; i++)
            {
                String as2[] = as1[i].split(",");
                mShouldHideApks.put(as[i], new AppHideItem(as[i], true, as2));
            }

        }
    }

    private boolean isSystemServer()
    {
        boolean flag;
        if(Process.myUid() == 1000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static boolean isValidDevice()
    {
        boolean flag = false;
        if(FeatureParser.getBoolean("support_app_hiding", false))
            flag = Build.IS_CM_CUSTOMIZATION;
        return flag;
    }

    private boolean moveToNextStartTag(XmlPullParser xmlpullparser)
        throws XmlPullParserException, IOException
    {
        if(xmlpullparser == null)
            return false;
        int i;
        do
            i = xmlpullparser.next();
        while(i != 2 && i != 1);
        return i == 2;
    }

    private void readAppHideConfig()
    {
        if(mFile == null)
            return;
        Slog.d(TAG, "start readAppHideConfig()");
        File file = mFile;
        file;
        JVM INSTR monitorenter ;
        XmlPullParser xmlpullparser;
        Object obj;
        String s;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11;
        Object obj12;
        Object obj13;
        Object obj14;
        xmlpullparser = null;
        obj = null;
        s = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        obj7 = obj5;
        obj8 = xmlpullparser;
        obj9 = obj;
        obj10 = s;
        obj11 = obj1;
        obj12 = obj2;
        obj13 = obj3;
        obj14 = obj4;
        if(!isSystemServer()) goto _L2; else goto _L1
_L1:
        obj7 = obj5;
        obj8 = xmlpullparser;
        obj9 = obj;
        obj10 = s;
        obj11 = obj1;
        obj12 = obj2;
        obj13 = obj3;
        obj14 = obj4;
        FileInputStream fileinputstream = JVM INSTR new #232 <Class FileInputStream>;
        obj7 = obj5;
        obj8 = xmlpullparser;
        obj9 = obj;
        obj10 = s;
        obj11 = obj1;
        obj12 = obj2;
        obj13 = obj3;
        obj14 = obj4;
        fileinputstream.FileInputStream(mFile);
        obj5 = fileinputstream;
_L8:
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        xmlpullparser = Xml.newPullParser();
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        xmlpullparser.setInput(((InputStream) (obj5)), null);
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        moveToNextStartTag(xmlpullparser);
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        if(!xmlpullparser.getName().equals("app-hide"))
            break MISSING_BLOCK_LABEL_579;
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        obj = JVM INSTR new #6   <Class PackageHideManager$AppHideConfig>;
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        ((AppHideConfig) (obj)).AppHideConfig();
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        appHideConfig = ((AppHideConfig) (obj));
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        appHideConfig.isFunctionOpen = Boolean.parseBoolean(xmlpullparser.getAttributeValue(null, "isFunctionOpen"));
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        appHideConfig.version = Integer.parseInt(xmlpullparser.getAttributeValue(null, "version"));
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        appHideConfig.isHide = Boolean.parseBoolean(xmlpullparser.getAttributeValue(null, "isHide"));
_L6:
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        if(!moveToNextStartTag(xmlpullparser)) goto _L4; else goto _L3
_L3:
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        if(!xmlpullparser.getName().equals("item")) goto _L6; else goto _L5
_L5:
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        obj = xmlpullparser.getAttributeValue(null, "package");
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        s = xmlpullparser.getAttributeValue(null, "path");
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        boolean flag = Boolean.parseBoolean(xmlpullparser.getAttributeValue(null, "isHide"));
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        if(TextUtils.isEmpty(((CharSequence) (obj))))
            break MISSING_BLOCK_LABEL_1047;
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        if(!(TextUtils.isEmpty(s) ^ true))
            break MISSING_BLOCK_LABEL_1047;
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        obj1 = mShouldHideApks;
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        obj2 = JVM INSTR new #9   <Class PackageHideManager$AppHideItem>;
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        ((AppHideItem) (obj2)).AppHideItem(((String) (obj)), flag, TextUtils.split(s, ";"));
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        ((Map) (obj1)).put(obj, obj2);
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        obj1 = TAG;
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        obj2 = JVM INSTR new #291 <Class StringBuilder>;
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        ((StringBuilder) (obj2)).StringBuilder();
        obj7 = obj5;
        obj8 = obj5;
        obj9 = obj5;
        obj10 = obj5;
        obj11 = obj5;
        obj12 = obj5;
        obj13 = obj5;
        obj14 = obj5;
        Log.e(((String) (obj1)), ((StringBuilder) (obj2)).append("read item: ").append(((String) (obj))).append(": ").append(s).toString());
          goto _L6
        obj5;
        obj14 = obj7;
        obj11 = TAG;
        obj14 = obj7;
        obj8 = JVM INSTR new #291 <Class StringBuilder>;
        obj14 = obj7;
        ((StringBuilder) (obj8)).StringBuilder();
        obj14 = obj7;
        Slog.w(((String) (obj11)), ((StringBuilder) (obj8)).append("Failed parsing ").append(obj5).toString());
        if(obj7 == null)
            break MISSING_BLOCK_LABEL_1290;
        Object obj6;
        IOException ioexception2;
        NullPointerException nullpointerexception;
        byte abyte0[];
        try
        {
            ((InputStream) (obj7)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj6) { }
        file;
        JVM INSTR monitorexit ;
        return;
_L2:
        obj7 = obj5;
        obj8 = xmlpullparser;
        obj9 = obj;
        obj10 = s;
        obj11 = obj1;
        obj12 = obj2;
        obj13 = obj3;
        obj14 = obj4;
        abyte0 = Shell.readByteArray(mFile.getAbsolutePath());
        if(abyte0 != null)
            break MISSING_BLOCK_LABEL_1342;
        file;
        JVM INSTR monitorexit ;
        return;
        obj7 = obj5;
        obj8 = xmlpullparser;
        obj9 = obj;
        obj10 = s;
        obj11 = obj1;
        obj12 = obj2;
        obj13 = obj3;
        obj14 = obj4;
        obj5 = new ByteArrayInputStream(abyte0);
        continue; /* Loop/switch isn't completed */
_L4:
        if(obj5 == null)
            break MISSING_BLOCK_LABEL_1290;
        try
        {
            ((InputStream) (obj5)).close();
        }
        catch(IOException ioexception) { }
        break MISSING_BLOCK_LABEL_1290;
        obj11;
        obj14 = obj8;
        obj7 = TAG;
        obj14 = obj8;
        ioexception = JVM INSTR new #291 <Class StringBuilder>;
        obj14 = obj8;
        ioexception.StringBuilder();
        obj14 = obj8;
        Slog.w(((String) (obj7)), ioexception.append("Failed parsing ").append(obj11).toString());
        if(obj8 == null)
            break MISSING_BLOCK_LABEL_1290;
        try
        {
            ((InputStream) (obj8)).close();
        }
        catch(IOException ioexception1) { }
        break MISSING_BLOCK_LABEL_1290;
        obj6;
        obj14 = obj9;
        obj7 = TAG;
        obj14 = obj9;
        obj11 = JVM INSTR new #291 <Class StringBuilder>;
        obj14 = obj9;
        ((StringBuilder) (obj11)).StringBuilder();
        obj14 = obj9;
        Slog.w(((String) (obj7)), ((StringBuilder) (obj11)).append("Failed parsing ").append(obj6).toString());
        if(obj9 == null)
            break MISSING_BLOCK_LABEL_1290;
        try
        {
            ((InputStream) (obj9)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj6) { }
        break MISSING_BLOCK_LABEL_1290;
        ioexception2;
        obj14 = obj10;
        obj6 = TAG;
        obj14 = obj10;
        obj11 = JVM INSTR new #291 <Class StringBuilder>;
        obj14 = obj10;
        ((StringBuilder) (obj11)).StringBuilder();
        obj14 = obj10;
        Slog.w(((String) (obj6)), ((StringBuilder) (obj11)).append("Failed parsing ").append(ioexception2).toString());
        if(obj10 == null)
            break MISSING_BLOCK_LABEL_1290;
        try
        {
            ((InputStream) (obj10)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj6) { }
        break MISSING_BLOCK_LABEL_1290;
        obj6;
        obj14 = obj11;
        obj8 = TAG;
        obj14 = obj11;
        ioexception2 = JVM INSTR new #291 <Class StringBuilder>;
        obj14 = obj11;
        ioexception2.StringBuilder();
        obj14 = obj11;
        Slog.w(((String) (obj8)), ioexception2.append("Failed parsing ").append(obj6).toString());
        if(obj11 == null)
            break MISSING_BLOCK_LABEL_1290;
        try
        {
            ((InputStream) (obj11)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj6) { }
        break MISSING_BLOCK_LABEL_1290;
        obj6;
        obj14 = obj12;
        ioexception2 = TAG;
        obj14 = obj12;
        obj11 = JVM INSTR new #291 <Class StringBuilder>;
        obj14 = obj12;
        ((StringBuilder) (obj11)).StringBuilder();
        obj14 = obj12;
        Slog.w(ioexception2, ((StringBuilder) (obj11)).append("Failed parsing ").append(obj6).toString());
        if(obj12 == null)
            break MISSING_BLOCK_LABEL_1290;
        try
        {
            ((InputStream) (obj12)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj6) { }
        break MISSING_BLOCK_LABEL_1290;
        nullpointerexception;
        obj14 = obj13;
        obj6 = TAG;
        obj14 = obj13;
        ioexception2 = JVM INSTR new #291 <Class StringBuilder>;
        obj14 = obj13;
        ioexception2.StringBuilder();
        obj14 = obj13;
        Slog.w(((String) (obj6)), ioexception2.append("Failed parsing ").append(nullpointerexception).toString());
        if(obj13 == null)
            break MISSING_BLOCK_LABEL_1290;
        try
        {
            ((InputStream) (obj13)).close();
        }
        // Misplaced declaration of an exception variable
        catch(Object obj6) { }
        break MISSING_BLOCK_LABEL_1290;
        obj6;
        if(obj14 == null)
            break MISSING_BLOCK_LABEL_1859;
        try
        {
            ((InputStream) (obj14)).close();
        }
        catch(IOException ioexception3) { }
        throw obj6;
        obj6;
        file;
        JVM INSTR monitorexit ;
        throw obj6;
        if(true) goto _L8; else goto _L7
_L7:
    }

    private boolean writeAppHideConfig()
    {
        FastXmlSerializer fastxmlserializer;
        Object obj;
        boolean flag;
        String s;
        Object obj2;
        Object obj3;
        boolean flag1;
        if(mFile == null)
            return false;
        Slog.d(TAG, "start writeAppHideConfig()");
        fastxmlserializer = null;
        obj = null;
        flag = true;
        Object obj4;
        Iterator iterator;
        if(isSystemServer())
            s = mFile.getAbsolutePath();
        else
            s = (new StringBuilder()).append(mFile.getAbsolutePath()).append("_").append(System.currentTimeMillis()).append("_.bak").toString();
        obj2 = obj;
        obj3 = fastxmlserializer;
        if(!isSystemServer()) goto _L2; else goto _L1
_L1:
        obj2 = obj;
        obj3 = fastxmlserializer;
        obj4 = JVM INSTR new #339 <Class FileOutputStream>;
        obj2 = obj;
        obj3 = fastxmlserializer;
        ((FileOutputStream) (obj4)).FileOutputStream(s);
        obj = obj4;
_L12:
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer = JVM INSTR new #342 <Class FastXmlSerializer>;
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.FastXmlSerializer();
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.setOutput(((OutputStream) (obj)), "utf-8");
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.startDocument(null, Boolean.valueOf(true));
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.startTag(null, "app-hide");
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.attribute(null, "isFunctionOpen", Boolean.toString(appHideConfig.isFunctionOpen));
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.attribute(null, "version", Integer.toString(appHideConfig.version));
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.attribute(null, "isHide", Boolean.toString(appHideConfig.isHide));
        obj2 = obj;
        obj3 = obj;
        if(mShouldHideApks == null) goto _L4; else goto _L3
_L3:
        obj2 = obj;
        obj3 = obj;
        iterator = mShouldHideApks.values().iterator();
_L7:
        obj2 = obj;
        obj3 = obj;
        if(!iterator.hasNext()) goto _L4; else goto _L5
_L5:
        obj2 = obj;
        obj3 = obj;
        obj4 = (AppHideItem)iterator.next();
        obj2 = obj;
        obj3 = obj;
        if(TextUtils.isEmpty(((AppHideItem) (obj4)).packageName)) goto _L7; else goto _L6
_L6:
        obj2 = obj;
        obj3 = obj;
        if(TextUtils.isEmpty(((AppHideItem) (obj4)).getJoinPath())) goto _L7; else goto _L8
_L8:
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.startTag(null, "item");
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.attribute(null, "package", ((AppHideItem) (obj4)).packageName);
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.attribute(null, "path", ((AppHideItem) (obj4)).getJoinPath());
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.attribute(null, "isHide", Boolean.toString(((AppHideItem) (obj4)).isHide));
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.endTag(null, "item");
          goto _L7
        obj;
        obj3 = obj2;
        Slog.w(TAG, "Failed to write state, restoring backup.", ((Throwable) (obj)));
        flag = false;
        flag1 = flag;
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_468;
        ((OutputStream) (obj2)).close();
        flag1 = flag;
_L14:
        flag = flag1;
        if(!flag1) goto _L10; else goto _L9
_L9:
        flag = flag1;
        if(!(isSystemServer() ^ true)) goto _L10; else goto _L11
_L11:
        obj3 = mFile;
        obj3;
        JVM INSTR monitorenter ;
        flag = Shell.move(s, mFile.getAbsolutePath());
        obj3;
        JVM INSTR monitorexit ;
_L10:
        return flag;
_L2:
        obj2 = obj;
        obj3 = fastxmlserializer;
        obj = new ByteArrayOutputStream();
          goto _L12
_L4:
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.endTag(null, "app-hide");
        obj2 = obj;
        obj3 = obj;
        fastxmlserializer.endDocument();
        obj2 = obj;
        obj3 = obj;
        if(isSystemServer())
            break MISSING_BLOCK_LABEL_633;
        obj2 = obj;
        obj3 = obj;
        flag = Shell.writeByteArray(s, false, ((ByteArrayOutputStream)obj).toByteArray());
        flag1 = flag;
        if(obj == null) goto _L14; else goto _L13
_L13:
        ((OutputStream) (obj)).close();
        flag1 = flag;
          goto _L14
        Object obj1;
        obj1;
_L15:
        flag1 = false;
          goto _L14
        obj1;
          goto _L15
        obj1;
        if(obj3 != null)
            try
            {
                ((OutputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj3) { }
        throw obj1;
        obj1;
        obj3;
        JVM INSTR monitorexit ;
        throw obj1;
        obj1;
        Shell.remove(s);
        flag = false;
          goto _L10
    }

    public List getIgnoreApkPathList()
    {
        ArrayList arraylist = new ArrayList();
        if(isAppHide())
        {
            Iterator iterator = mShouldHideApks.values().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                AppHideItem apphideitem = (AppHideItem)iterator.next();
                if(apphideitem.isHide && apphideitem.pathArray != null)
                {
                    String as[] = apphideitem.pathArray;
                    int i = 0;
                    int j = as.length;
                    while(i < j) 
                    {
                        arraylist.add(as[i]);
                        i++;
                    }
                }
            } while(true);
        }
        return arraylist;
    }

    public List getIgnoreApkPkgNameList()
    {
        ArrayList arraylist = new ArrayList();
        if(mShouldHideApks != null)
        {
            for(Iterator iterator = mShouldHideApks.keySet().iterator(); iterator.hasNext(); arraylist.add((String)iterator.next()));
        }
        return arraylist;
    }

    public boolean isAppHide()
    {
        return isValidDevice() && appHideConfig != null && appHideConfig.isFunctionOpen && appHideConfig.isHide;
    }

    public boolean isAppHide(String s)
    {
        if(TextUtils.isEmpty(s) || isAppHide() ^ true)
            return false;
        s = (AppHideItem)mShouldHideApks.get(s);
        return s != null && ((AppHideItem) (s)).isHide;
    }

    public boolean isFunctionOpen()
    {
        return isValidDevice() && appHideConfig != null && appHideConfig.isFunctionOpen;
    }

    public boolean setHideApp(Context context, String s, boolean flag)
    {
        if(TextUtils.isEmpty(s) || isValidDevice() ^ true || appHideConfig == null)
            return false;
        s = (AppHideItem)mShouldHideApks.get(s);
        if(s != null && ((AppHideItem) (s)).isHide != flag)
        {
            s.isHide = flag;
            if(!flag)
                clearUserAleadyInstalled(context, s);
            return writeAppHideConfig();
        } else
        {
            return false;
        }
    }

    public boolean setHideApp(Context context, boolean flag)
    {
        while(!isValidDevice() || appHideConfig == null || appHideConfig.isHide == flag) 
            return false;
        appHideConfig.isHide = flag;
        if(!flag)
            clearUserAleadyInstalled(context);
        return writeAppHideConfig();
    }

    private static final String APP_HIDE_SWITCH_FILE = "/data/system/app_hide_switch.xml";
    private static final int APP_HIDE_SWITCH_FILE_VERSION = 2;
    private static String TAG = android/content/pm/PackageHideManager.getName();
    private static AppHideConfig appHideConfig;
    private static File mFile;
    private static volatile PackageHideManager sInstance = null;
    private Map mShouldHideApks;

}
