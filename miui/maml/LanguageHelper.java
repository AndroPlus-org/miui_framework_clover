// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.os.MemoryFile;
import android.util.Log;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import miui.maml.data.Variables;
import miui.maml.util.Utils;
import org.w3c.dom.*;

// Referenced classes of package miui.maml:
//            ResourceManager

public class LanguageHelper
{

    public LanguageHelper()
    {
    }

    public static boolean load(Locale locale, ResourceManager resourcemanager, Variables variables)
    {
        MemoryFile memoryfile = null;
        if(locale != null)
        {
            MemoryFile memoryfile1 = resourcemanager.getFile(Utils.addFileNameSuffix("strings/strings.xml", locale.toString()));
            memoryfile = memoryfile1;
            if(memoryfile1 == null)
                memoryfile = resourcemanager.getFile(Utils.addFileNameSuffix("strings/strings.xml", locale.getLanguage()));
        }
        locale = memoryfile;
        if(memoryfile == null)
        {
            resourcemanager = resourcemanager.getFile("strings/strings.xml");
            locale = resourcemanager;
            if(resourcemanager == null)
            {
                Log.w("LanguageHelper", "no available string resources to load.");
                return false;
            }
        }
        resourcemanager = DocumentBuilderFactory.newInstance();
        resourcemanager = resourcemanager.newDocumentBuilder().parse(locale.getInputStream());
        locale.close();
        return setVariables(resourcemanager, variables);
        resourcemanager;
        Log.e("LanguageHelper", resourcemanager.getMessage());
        locale.close();
        return false;
        resourcemanager;
        locale.close();
        throw resourcemanager;
    }

    private static boolean setVariables(Document document, Variables variables)
    {
        boolean flag = true;
        NodeList nodelist = document.getElementsByTagName("resources");
        NodeList nodelist1 = nodelist;
        if(nodelist.getLength() <= 0)
        {
            nodelist1 = document.getElementsByTagName("strings");
            if(nodelist1.getLength() <= 0)
                return false;
            flag = false;
        }
        nodelist1 = ((Element)nodelist1.item(0)).getElementsByTagName("string");
        int i = 0;
        while(i < nodelist1.getLength()) 
        {
            document = (Element)nodelist1.item(i);
            String s = document.getAttribute("name");
            if(flag)
                document = document.getTextContent();
            else
                document = document.getAttribute("value");
            variables.put(s, document.replaceAll("\\\\", ""));
            i++;
        }
        return true;
    }

    private static final String COMPATIBLE_STRING_ROOT_TAG = "strings";
    private static final String DEFAULT_STRING_FILE_PATH = "strings/strings.xml";
    private static final String LOG_TAG = "LanguageHelper";
    private static final String STRING_FILE_PATH = "strings/strings.xml";
    private static final String STRING_ROOT_TAG = "resources";
    private static final String STRING_TAG = "string";
}
