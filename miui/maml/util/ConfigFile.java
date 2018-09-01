// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml.util;

import android.content.Context;
import android.miui.Shell;
import android.text.TextUtils;
import android.util.Log;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import miui.content.res.ThemeNativeUtils;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

// Referenced classes of package miui.maml.util:
//            Utils, Task

public class ConfigFile
{
    public static class Gadget
    {

        public String path;
        public int x;
        public int y;

        public Gadget(String s, int i, int j)
        {
            path = s;
            x = i;
            y = j;
        }
    }

    private static interface OnLoadElementListener
    {

        public abstract void OnLoadElement(Element element);
    }

    public static class Variable
    {

        public String name;
        public String type;
        public String value;

        public Variable()
        {
        }
    }


    static void _2D_wrap0(ConfigFile configfile, String s, String s1, String s2)
    {
        configfile.put(s, s1, s2);
    }

    public ConfigFile()
    {
        mVariables = new HashMap();
        mTasks = new HashMap();
        mGadgets = new ArrayList();
    }

    private void createNewFile(String s)
        throws IOException
    {
        if(!TextUtils.isEmpty(s))
        {
            s = new File(s);
            s.getParentFile().mkdirs();
            s.delete();
            s.createNewFile();
        }
    }

    private void loadGadgets(Element element)
    {
        loadList(element, "Gadgets", "Gadget", new OnLoadElementListener() {

            public void OnLoadElement(Element element1)
            {
                if(element1 != null)
                    putGadget(new Gadget(element1.getAttribute("path"), Utils.getAttrAsInt(element1, "x", 0), Utils.getAttrAsInt(element1, "y", 0)));
            }

            final ConfigFile this$0;

            
            {
                this$0 = ConfigFile.this;
                super();
            }
        }
);
    }

    private void loadList(Element element, String s, String s1, OnLoadElementListener onloadelementlistener)
    {
        element = Utils.getChild(element, s);
        if(element == null)
            return;
        s = element.getChildNodes();
        for(int i = 0; i < s.getLength(); i++)
        {
            element = s.item(i);
            if(element.getNodeType() == 1 && element.getNodeName().equals(s1))
                onloadelementlistener.OnLoadElement((Element)element);
        }

    }

    private void loadTasks(Element element)
    {
        loadList(element, "Tasks", "Intent", new OnLoadElementListener() {

            public void OnLoadElement(Element element1)
            {
                putTask(Task.load(element1));
            }

            final ConfigFile this$0;

            
            {
                this$0 = ConfigFile.this;
                super();
            }
        }
);
    }

    private void loadVariables(Element element)
    {
        loadList(element, "Variables", "Variable", new OnLoadElementListener() {

            public void OnLoadElement(Element element1)
            {
                ConfigFile._2D_wrap0(ConfigFile.this, element1.getAttribute("name"), element1.getAttribute("value"), element1.getAttribute("type"));
            }

            final ConfigFile this$0;

            
            {
                this$0 = ConfigFile.this;
                super();
            }
        }
);
    }

    private void put(String s, String s1, String s2)
    {
        if(TextUtils.isEmpty(s) || TextUtils.isEmpty(s2))
            return;
        if(!"string".equals(s2) && "number".equals(s2) ^ true)
            return;
        Variable variable = (Variable)mVariables.get(s);
        Variable variable1 = variable;
        if(variable == null)
        {
            variable1 = new Variable();
            variable1.name = s;
            mVariables.put(s, variable1);
        }
        variable1.type = s2;
        variable1.value = s1;
    }

    private void writeGadgets(StringBuilder stringbuilder)
    {
        if(mGadgets.size() == 0)
            return;
        writeTag(stringbuilder, "Gadgets", false);
        String s;
        int i;
        int j;
        for(Iterator iterator = mGadgets.iterator(); iterator.hasNext(); writeTag(stringbuilder, "Gadget", new String[] {
    "path", "x", "y"
}, new String[] {
    s, String.valueOf(i), String.valueOf(j)
}, true))
        {
            Gadget gadget = (Gadget)iterator.next();
            s = gadget.path;
            i = gadget.x;
            j = gadget.y;
        }

        writeTag(stringbuilder, "Gadgets", true);
    }

    private static void writeTag(StringBuilder stringbuilder, String s, boolean flag)
    {
        stringbuilder.append("<");
        if(flag)
            stringbuilder.append("/");
        stringbuilder.append(s);
        stringbuilder.append(">\n");
    }

    private static void writeTag(StringBuilder stringbuilder, String s, String as[], String as1[])
    {
        writeTag(stringbuilder, s, as, as1, true);
    }

    private static void writeTag(StringBuilder stringbuilder, String s, String as[], String as1[], boolean flag)
    {
        stringbuilder.append("<");
        stringbuilder.append(s);
        int i = 0;
        while(i < as.length) 
        {
            if(!flag || !TextUtils.isEmpty(as1[i]))
            {
                stringbuilder.append(" ");
                stringbuilder.append(as[i]);
                stringbuilder.append("=\"");
                stringbuilder.append(as1[i]);
                stringbuilder.append("\"");
            }
            i++;
        }
        stringbuilder.append("/>\n");
    }

    private void writeTasks(StringBuilder stringbuilder)
    {
        if(mTasks.size() == 0)
            return;
        writeTag(stringbuilder, "Tasks", false);
        String s = Task.TAG_ID;
        String s1 = Task.TAG_ACTION;
        String s2 = Task.TAG_TYPE;
        String s3 = Task.TAG_CATEGORY;
        String s4 = Task.TAG_PACKAGE;
        String s5 = Task.TAG_CLASS;
        String s6 = Task.TAG_NAME;
        Object obj;
        String s7;
        String s8;
        String s9;
        String s10;
        String s11;
        String s12;
        for(Iterator iterator = mTasks.values().iterator(); iterator.hasNext(); writeTag(stringbuilder, "Intent", new String[] {
    s, s1, s2, s3, s4, s5, s6
}, new String[] {
    s7, s8, s9, s10, s11, s12, obj
}, true))
        {
            obj = (Task)iterator.next();
            s7 = ((Task) (obj)).id;
            s8 = ((Task) (obj)).action;
            s9 = ((Task) (obj)).type;
            s10 = ((Task) (obj)).category;
            s11 = ((Task) (obj)).packageName;
            s12 = ((Task) (obj)).className;
            obj = ((Task) (obj)).name;
        }

        writeTag(stringbuilder, "Tasks", true);
    }

    private void writeVariables(StringBuilder stringbuilder)
    {
        if(mVariables.size() == 0)
            return;
        writeTag(stringbuilder, "Variables", false);
        Object obj;
        String s;
        String s1;
        for(Iterator iterator = mVariables.values().iterator(); iterator.hasNext(); writeTag(stringbuilder, "Variable", new String[] {
    "name", "type", "value"
}, new String[] {
    s, s1, obj
}))
        {
            obj = (Variable)iterator.next();
            s = ((Variable) (obj)).name;
            s1 = ((Variable) (obj)).type;
            obj = ((Variable) (obj)).value;
        }

        writeTag(stringbuilder, "Variables", true);
    }

    public Collection getGadgets()
    {
        return mGadgets;
    }

    public Task getTask(String s)
    {
        return (Task)mTasks.get(s);
    }

    public Collection getTasks()
    {
        return mTasks.values();
    }

    public String getVariable(String s)
    {
        Object obj = null;
        s = (Variable)mVariables.get(s);
        if(s == null)
            s = obj;
        else
            s = ((Variable) (s)).value;
        return s;
    }

    public Collection getVariables()
    {
        return mVariables.values();
    }

    public boolean load(String s)
    {
        DocumentBuilderFactory documentbuilderfactory;
        Object obj;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        mFilePath = s;
        mVariables.clear();
        mTasks.clear();
        documentbuilderfactory = DocumentBuilderFactory.newInstance();
        obj = null;
        obj1 = null;
        obj2 = null;
        obj3 = null;
        obj4 = null;
        obj5 = null;
        obj6 = obj4;
        Object obj10 = JVM INSTR new #358 <Class FileInputStream>;
        obj6 = obj4;
        ((FileInputStream) (obj10)).FileInputStream(s);
        s = documentbuilderfactory.newDocumentBuilder().parse(((InputStream) (obj10))).getDocumentElement();
        if(s == null)
        {
            if(obj10 != null)
                try
                {
                    ((InputStream) (obj10)).close();
                }
                // Misplaced declaration of an exception variable
                catch(String s) { }
            return false;
        }
        boolean flag = s.getNodeName().equals("Config");
        if(!flag)
        {
            if(obj10 != null)
                try
                {
                    ((InputStream) (obj10)).close();
                }
                // Misplaced declaration of an exception variable
                catch(String s) { }
            return false;
        }
        loadVariables(s);
        loadTasks(s);
        loadGadgets(s);
        if(obj10 != null)
            try
            {
                ((InputStream) (obj10)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        return true;
        obj10;
        s = obj5;
_L9:
        obj6 = s;
        ((Exception) (obj10)).printStackTrace();
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
_L1:
        return false;
        obj10;
        s = obj;
_L7:
        obj6 = s;
        ((IOException) (obj10)).printStackTrace();
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
          goto _L1
        obj10;
        s = obj1;
_L6:
        obj6 = s;
        ((SAXException) (obj10)).printStackTrace();
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
          goto _L1
        obj10;
        s = obj2;
_L5:
        obj6 = s;
        ((ParserConfigurationException) (obj10)).printStackTrace();
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
          goto _L1
        s;
        obj10 = obj3;
_L4:
        if(obj10 != null)
            try
            {
                ((InputStream) (obj10)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
          goto _L1
        s;
_L3:
        if(obj6 != null)
            try
            {
                ((InputStream) (obj6)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj10) { }
        throw s;
        s;
        obj6 = obj10;
        if(true) goto _L3; else goto _L2
_L2:
        s;
          goto _L4
        s;
        Object obj7 = obj10;
        obj10 = s;
        s = ((String) (obj7));
          goto _L5
        s;
        Object obj8 = obj10;
        obj10 = s;
        s = ((String) (obj8));
          goto _L6
        IOException ioexception;
        ioexception;
        s = ((String) (obj10));
        obj10 = ioexception;
          goto _L7
        s;
        Object obj9 = obj10;
        obj10 = s;
        s = ((String) (obj9));
        if(true) goto _L9; else goto _L8
_L8:
    }

    public void loadDefaultSettings(Element element)
    {
        if(element == null || element.getNodeName().equals("Config") ^ true)
        {
            return;
        } else
        {
            Utils.traverseXmlElementChildren(element, "Group", new Utils.XmlTraverseListener() {

                public void onChild(Element element1)
                {
                    Utils.traverseXmlElementChildren(element1, null, new Utils.XmlTraverseListener() {

                        public void onChild(Element element)
                        {
                            Object obj;
                            String s;
                            obj = element.getNodeName();
                            s = element.getAttribute("id");
                            if(!"StringInput".equals(obj)) goto _L2; else goto _L1
_L1:
                            putString(s, element.getAttribute("default"));
_L4:
                            return;
_L2:
                            if("CheckBox".equals(obj))
                            {
                                obj = _fld0;
                                if(element.getAttribute("default").equals("1"))
                                    element = "1";
                                else
                                    element = "0";
                                ((ConfigFile) (obj)).putNumber(s, element);
                            } else
                            if("NumberInput".equals(obj))
                            {
                                double d = Utils.getAttrAsFloat(element, "default", 0.0F);
                                putNumber(s, Utils.doubleToString(d));
                            } else
                            if("StringChoice".equals(obj))
                                putString(s, element.getAttribute("default"));
                            else
                            if("NumberChoice".equals(obj))
                            {
                                double d1 = Utils.getAttrAsFloat(element, "default", 0.0F);
                                putNumber(s, Utils.doubleToString(d1));
                            } else
                            if("AppPicker".equals(obj))
                                putTask(Task.load(element));
                            if(true) goto _L4; else goto _L3
_L3:
                        }

                        final _cls4 this$1;

            
            {
                this$1 = _cls4.this;
                super();
            }
                    }
);
                }

                final ConfigFile this$0;

            
            {
                this$0 = ConfigFile.this;
                super();
            }
            }
);
            return;
        }
    }

    public void moveGadget(Gadget gadget, int i)
    {
        if(mGadgets.remove(gadget))
            mGadgets.add(i, gadget);
    }

    public void putGadget(Gadget gadget)
    {
        if(gadget == null)
        {
            return;
        } else
        {
            mGadgets.add(gadget);
            mDirty = true;
            return;
        }
    }

    public void putNumber(String s, double d)
    {
        putNumber(s, Utils.doubleToString(d));
    }

    public void putNumber(String s, String s1)
    {
        put(s, s1, "number");
        mDirty = true;
    }

    public void putString(String s, String s1)
    {
        put(s, s1, "string");
        mDirty = true;
    }

    public void putTask(Task task)
    {
        if(task == null || TextUtils.isEmpty(task.id))
        {
            return;
        } else
        {
            mTasks.put(task.id, task);
            mDirty = true;
            return;
        }
    }

    public void removeGadget(Gadget gadget)
    {
        mGadgets.remove(gadget);
        mDirty = true;
    }

    public boolean save(Context context)
    {
        boolean flag = mDirty;
        mDirty = false;
        if(flag)
            flag = save(mFilePath, context);
        else
            flag = true;
        return flag;
    }

    public boolean save(String s, Context context)
    {
        StringBuilder stringbuilder;
        Object obj;
        Shell.remove(s);
        stringbuilder = null;
        obj = null;
        createNewFile(s);
        context = obj;
_L9:
        stringbuilder = new StringBuilder();
        writeTag(stringbuilder, "Config", false);
        writeVariables(stringbuilder);
        writeTasks(stringbuilder);
        writeGadgets(stringbuilder);
        writeTag(stringbuilder, "Config", true);
        if(!(new File(s)).exists()) goto _L2; else goto _L1
_L1:
        Shell.write(s, stringbuilder.toString());
_L7:
        ThemeNativeUtils.updateFilePermissionWithThemeContext("/data/system/theme/config.config");
        return true;
        s;
        Log.e("ConfigFile", (new StringBuilder()).append("create target file or temp file failed").append(s).toString());
        return false;
        IOException ioexception;
        ioexception;
        File file = context.getExternalFilesDir(null);
        if(android.os.Build.VERSION.SDK_INT < 23) goto _L4; else goto _L3
_L3:
        context = stringbuilder;
        if(file == null)
            break MISSING_BLOCK_LABEL_173;
        context = JVM INSTR new #255 <Class StringBuilder>;
        context.StringBuilder();
        context = context.append(file.getPath()).append(File.separator).append("temp").toString();
_L5:
        createNewFile(context);
        continue; /* Loop/switch isn't completed */
_L4:
        try
        {
            stringbuilder = JVM INSTR new #255 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            context = stringbuilder.append(context.getDir("temp", 0).getPath()).append(File.separator).append("temp").toString();
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("ConfigFile", (new StringBuilder()).append("create target file failed").append(ioexception).toString());
            return false;
        }
        if(true) goto _L5; else goto _L2
_L2:
        if(TextUtils.isEmpty(context) || !(new File(context)).exists())
            break; /* Loop/switch isn't completed */
        Shell.write(context, stringbuilder.toString());
        Shell.move(context, s);
        if(true) goto _L7; else goto _L6
_L6:
        Log.w("ConfigFile", "target file and temp file are not exists");
        return false;
        if(true) goto _L9; else goto _L8
_L8:
    }

    private static final String LOG_TAG = "ConfigFile";
    public static final String TAG_APP_PICKER = "AppPicker";
    public static final String TAG_CHECK_BOX = "CheckBox";
    private static final String TAG_GADGET = "Gadget";
    private static final String TAG_GADGETS = "Gadgets";
    public static final String TAG_GROUP = "Group";
    public static final String TAG_IMAGE_PICKER = "ImagePicker";
    public static final String TAG_NUMBER_CHOICE = "NumberChoice";
    public static final String TAG_NUMBER_INPUT = "NumberInput";
    private static final String TAG_ROOT = "Config";
    public static final String TAG_STRING_CHOICE = "StringChoice";
    public static final String TAG_STRING_INPUT = "StringInput";
    private static final String TAG_TASK = "Intent";
    private static final String TAG_TASKS = "Tasks";
    private static final String TAG_VARIABLE = "Variable";
    private static final String TAG_VARIABLES = "Variables";
    private boolean mDirty;
    private String mFilePath;
    private ArrayList mGadgets;
    private HashMap mTasks;
    private HashMap mVariables;
}
