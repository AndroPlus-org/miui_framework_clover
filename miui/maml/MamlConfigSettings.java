// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.maml;

import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.*;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipFile;
import javax.xml.parsers.*;
import miui.app.ActionBar;
import miui.app.constants.ResourceBrowserConstants;
import miui.content.res.ThemeResources;
import miui.content.res.ThemeResourcesSystem;
import miui.maml.util.ConfigFile;
import miui.maml.util.Task;
import miui.maml.util.Utils;
import miui.preference.PreferenceActivity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

// Referenced classes of package miui.maml:
//            ThirdAppPicker

public class MamlConfigSettings extends PreferenceActivity
    implements android.preference.Preference.OnPreferenceChangeListener, android.preference.Preference.OnPreferenceClickListener
{
    private class AppPickerItem extends PickerItem
    {

        public boolean OnValueChange(Object obj)
        {
            obj = (Task)obj;
            MamlConfigSettings._2D_get0(MamlConfigSettings.this).getConfigFile().putTask(((Task) (obj)));
            if(obj != null && ((Task) (obj)).name != null)
                obj = ((Task) (obj)).name;
            else
                obj = "";
            setValuePreview(((String) (obj)));
            return true;
        }

        public boolean onActivityResult(int i, Intent intent)
        {
            if(i != -1)
                return false;
            Task task = new Task();
            task.id = mId;
            if(intent != null)
            {
                task.name = intent.getStringExtra("name");
                task.packageName = intent.getComponent().getPackageName();
                task.className = intent.getComponent().getClassName();
                task.action = "android.intent.action.MAIN";
                Log.i("AppPickerItem", (new StringBuilder()).append("selected component: ").append(task.packageName).append(" ").append(task.className).toString());
                intent = task;
            } else
            {
                intent = mDefaultTask;
            }
            return OnValueChange(intent);
        }

        protected void onBuild(Element element)
        {
            super.onBuild(element);
            mDefaultTask = Task.load(element);
        }

        public boolean onClick()
        {
            Intent intent = new Intent(MamlConfigSettings.this, miui/maml/ThirdAppPicker);
            startActivityForResult(intent, mRequestCode);
            return true;
        }

        public void updateValue()
        {
            Task task = MamlConfigSettings._2D_get0(MamlConfigSettings.this).getConfigFile().getTask(mId);
            if(task != null)
                setValuePreview(task.name);
            else
                OnValueChange(mDefaultTask);
        }

        private Task mDefaultTask;
        final MamlConfigSettings this$0;

        private AppPickerItem()
        {
            this$0 = MamlConfigSettings.this;
            super(null);
        }

        AppPickerItem(AppPickerItem apppickeritem)
        {
            this();
        }
    }

    private class CheckboxItem extends VariableItem
    {

        public boolean OnValueChange(Object obj)
        {
            boolean flag = false;
            ConfigFile configfile;
            String s;
            if(obj instanceof Boolean)
                flag = ((Boolean)obj).booleanValue();
            else
            if(obj instanceof String)
                flag = "1".equals(obj);
            configfile = MamlConfigSettings._2D_get0(MamlConfigSettings.this).getConfigFile();
            s = mId;
            if(flag)
                obj = "1";
            else
                obj = "0";
            configfile.putNumber(s, ((String) (obj)));
            return true;
        }

        protected Preference createPreference(Context context)
        {
            return new CheckBoxPreference(context);
        }

        protected void setValue(String s)
        {
            ((CheckBoxPreference)mPreference).setChecked("1".equals(s));
        }

        final MamlConfigSettings this$0;

        private CheckboxItem()
        {
            this$0 = MamlConfigSettings.this;
            super(null);
        }

        CheckboxItem(CheckboxItem checkboxitem)
        {
            this();
        }
    }

    private static class ConfigFileHelper
    {

        public void close()
        {
            if(mZipFile == null)
                break MISSING_BLOCK_LABEL_14;
            mZipFile.close();
_L1:
            return;
            IOException ioexception;
            ioexception;
            ioexception.printStackTrace();
              goto _L1
        }

        public boolean containsFile(String s)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(mZipFile != null)
            {
                flag1 = flag;
                if(mZipFile.getEntry(s) != null)
                    flag1 = true;
            }
            return flag1;
        }

        public ConfigFile getConfigFile()
        {
            return mConfigFile;
        }

        public InputStream getConfigFileStream(Locale locale)
            throws IOException
        {
            if(locale != null)
            {
                InputStream inputstream = getFileStream(Utils.addFileNameSuffix("config.xml", locale.toString()));
                if(inputstream != null)
                    return inputstream;
                locale = getFileStream(Utils.addFileNameSuffix("config.xml", locale.getLanguage()));
                if(locale != null)
                    return locale;
            }
            return getFileStream("config.xml");
        }

        public String getConfigPath()
        {
            return (new StringBuilder()).append(mPath).append(".config").toString();
        }

        protected InputStream getFileStream(String s)
            throws IOException
        {
            if(mZipFile == null)
                return null;
            s = mZipFile.getEntry(s);
            if(s == null)
                return null;
            else
                return mZipFile.getInputStream(s);
        }

        public void save()
        {
            mConfigFile.save(mAppContext);
        }

        protected Context mAppContext;
        protected ConfigFile mConfigFile;
        private String mPath;
        private ZipFile mZipFile;

        public ConfigFileHelper(String s, Context context)
        {
            mPath = s;
            mAppContext = context;
            if(mPath != null)
                try
                {
                    s = JVM INSTR new #28  <Class ZipFile>;
                    s.ZipFile(mPath);
                    mZipFile = s;
                }
                // Misplaced declaration of an exception variable
                catch(String s)
                {
                    s.printStackTrace();
                }
            mConfigFile = new ConfigFile();
            mConfigFile.load(getConfigPath());
        }
    }

    private class ImagePickerItem extends PickerItem
    {

        public boolean OnValueChange(Object obj)
        {
            obj = (Uri)obj;
            if(obj == null)
                obj = null;
            else
                obj = ((Uri) (obj)).toString();
            MamlConfigSettings._2D_get0(MamlConfigSettings.this).getConfigFile().putString(mId, ((String) (obj)));
            setValuePreview(((String) (obj)));
            return true;
        }

        public boolean onActivityResult(int i, Intent intent)
        {
            boolean flag;
            if(i == -1)
                flag = OnValueChange(intent.getData());
            else
                flag = false;
            return flag;
        }

        public boolean onClick()
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction("android.intent.action.PICK");
            startActivityForResult(Intent.createChooser(intent, null), mRequestCode);
            return true;
        }

        public void updateValue()
        {
            setValuePreview(MamlConfigSettings._2D_get0(MamlConfigSettings.this).getConfigFile().getVariable(mId));
        }

        final MamlConfigSettings this$0;

        private ImagePickerItem()
        {
            this$0 = MamlConfigSettings.this;
            super(null);
        }

        ImagePickerItem(ImagePickerItem imagepickeritem)
        {
            this();
        }
    }

    private abstract class Item
    {

        public abstract boolean OnValueChange(Object obj);

        public final boolean build(PreferenceCategory preferencecategory, Element element)
        {
            mId = element.getAttribute("id");
            mTitle = element.getAttribute("text");
            mSummary = element.getAttribute("summary");
            mDefaultValue = element.getAttribute("default");
            mPreference = createPreference(MamlConfigSettings.this);
            if(mPreference == null)
            {
                return false;
            } else
            {
                preferencecategory.addPreference(mPreference);
                mPreference.setTitle(mTitle);
                mPreference.setSummary(mSummary);
                mPreference.setKey(mId);
                mPreference.setOnPreferenceChangeListener(MamlConfigSettings.this);
                mPreference.setPersistent(false);
                onBuild(element);
                updateValue();
                return true;
            }
        }

        protected abstract Preference createPreference(Context context);

        protected void onBuild(Element element)
        {
        }

        public boolean onClick()
        {
            return false;
        }

        protected void setValuePreview(String s)
        {
            StringBuilder stringbuilder = new StringBuilder();
            String s1 = s;
            if(s == null)
                s1 = "";
            stringbuilder.append(s1);
            if(!TextUtils.isEmpty(mSummary))
                stringbuilder.append("\n").append(mSummary);
            mPreference.setSummary(stringbuilder.toString());
        }

        public abstract void updateValue();

        protected String mDefaultValue;
        protected String mId;
        protected Preference mPreference;
        protected String mSummary;
        protected String mTitle;
        final MamlConfigSettings this$0;

        private Item()
        {
            this$0 = MamlConfigSettings.this;
            super();
        }

        Item(Item item)
        {
            this();
        }
    }

    private static class LockscreenConfigFileHelper extends ConfigFileHelper
    {

        public boolean containsFile(String s)
        {
            return ThemeResources.getSystem().containsAwesomeLockscreenEntry(s);
        }

        public String getConfigPath()
        {
            return "/data/system/theme/config.config";
        }

        protected InputStream getFileStream(String s)
        {
            return ThemeResources.getSystem().getAwesomeLockscreenFileStream(s, null);
        }

        public void save()
        {
            super.save();
            String s = (new StringBuilder()).append(ResourceBrowserConstants.MAML_CONFIG_PATH).append("lockstyle").append("/").append(mId).append(".config").toString();
            mConfigFile.save(s, mAppContext);
        }

        private String mId;

        public LockscreenConfigFileHelper(String s, Context context)
        {
            super(null, context);
            mId = s;
        }
    }

    private class NumberChoiceItem extends ValueChoiceItem
    {

        public boolean OnValueChange(Object obj)
        {
            super.OnValueChange(obj);
            MamlConfigSettings._2D_get0(MamlConfigSettings.this).getConfigFile().putNumber(mId, (String)obj);
            return true;
        }

        final MamlConfigSettings this$0;

        private NumberChoiceItem()
        {
            this$0 = MamlConfigSettings.this;
            super(null);
        }

        NumberChoiceItem(NumberChoiceItem numberchoiceitem)
        {
            this();
        }
    }

    private class NumberInputItem extends VariableItem
    {

        private String getValueString(String s)
        {
            double d = 0.0D;
            double d1;
            try
            {
                d1 = Double.parseDouble(s);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                d1 = d;
            }
            return Utils.doubleToString(d1);
        }

        public boolean OnValueChange(Object obj)
        {
            double d;
            try
            {
                d = Double.parseDouble((String)obj);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                return false;
            }
            obj = Utils.doubleToString(d);
            MamlConfigSettings._2D_get0(MamlConfigSettings.this).getConfigFile().putNumber(mId, ((String) (obj)));
            setValuePreview(((String) (obj)));
            return true;
        }

        protected Preference createPreference(Context context)
        {
            return new EditTextPreference(context);
        }

        protected void onBuild(Element element)
        {
            super.onBuild(element);
            ((EditTextPreference)mPreference).setDialogTitle(mTitle);
        }

        protected void setValue(String s)
        {
            s = getValueString(s);
            ((EditTextPreference)mPreference).setText(s);
            setValuePreview(s);
        }

        final MamlConfigSettings this$0;

        private NumberInputItem()
        {
            this$0 = MamlConfigSettings.this;
            super(null);
        }

        NumberInputItem(NumberInputItem numberinputitem)
        {
            this();
        }
    }

    private abstract class PickerItem extends Item
    {

        protected Preference createPreference(Context context)
        {
            return new Preference(context);
        }

        public abstract boolean onActivityResult(int i, Intent intent);

        protected void onBuild(Element element)
        {
            mPreference.setOnPreferenceClickListener(MamlConfigSettings.this);
            mRequestCode = MamlConfigSettings._2D_wrap0(MamlConfigSettings.this);
            MamlConfigSettings._2D_wrap3(MamlConfigSettings.this, mRequestCode, this);
        }

        protected int mRequestCode;
        final MamlConfigSettings this$0;

        private PickerItem()
        {
            this$0 = MamlConfigSettings.this;
            super(null);
        }

        PickerItem(PickerItem pickeritem)
        {
            this();
        }
    }

    private class StringChoiceItem extends ValueChoiceItem
    {

        public boolean OnValueChange(Object obj)
        {
            super.OnValueChange(obj);
            MamlConfigSettings._2D_get0(MamlConfigSettings.this).getConfigFile().putString(mId, (String)obj);
            return true;
        }

        final MamlConfigSettings this$0;

        private StringChoiceItem()
        {
            this$0 = MamlConfigSettings.this;
            super(null);
        }

        StringChoiceItem(StringChoiceItem stringchoiceitem)
        {
            this();
        }
    }

    private class StringInputItem extends VariableItem
    {

        public boolean OnValueChange(Object obj)
        {
            obj = (String)obj;
            MamlConfigSettings._2D_get0(MamlConfigSettings.this).getConfigFile().putString(mId, ((String) (obj)));
            setValuePreview(((String) (obj)));
            return true;
        }

        protected Preference createPreference(Context context)
        {
            return new EditTextPreference(context);
        }

        protected void onBuild(Element element)
        {
            super.onBuild(element);
            ((EditTextPreference)mPreference).setDialogTitle(mTitle);
        }

        protected void setValue(String s)
        {
            ((EditTextPreference)mPreference).setText(s);
            setValuePreview(s);
        }

        final MamlConfigSettings this$0;

        private StringInputItem()
        {
            this$0 = MamlConfigSettings.this;
            super(null);
        }

        StringInputItem(StringInputItem stringinputitem)
        {
            this();
        }
    }

    private abstract class ValueChoiceItem extends VariableItem
    {

        public boolean OnValueChange(Object obj)
        {
            obj = (String)obj;
            ListPreference listpreference = (ListPreference)mPreference;
            int i = listpreference.findIndexOfValue(((String) (obj)));
            if(i != -1)
            {
                setValuePreview(listpreference.getEntries()[i].toString());
                return true;
            } else
            {
                return false;
            }
        }

        protected Preference createPreference(Context context)
        {
            return new ListPreference(context);
        }

        protected void onBuild(Element element)
        {
            mItemsText.clear();
            mItemsValue.clear();
            Utils.traverseXmlElementChildren(element, "Item", new miui.maml.util.Utils.XmlTraverseListener() {

                public void onChild(Element element)
                {
                    mItemsText.add(element.getAttribute("text"));
                    mItemsValue.add(element.getAttribute("value"));
                }

                final ValueChoiceItem this$1;

            
            {
                this$1 = ValueChoiceItem.this;
                super();
            }
            }
);
            element = (ListPreference)mPreference;
            element.setEntries((CharSequence[])mItemsText.toArray(new String[mItemsText.size()]));
            element.setEntryValues((CharSequence[])mItemsValue.toArray(new String[mItemsValue.size()]));
            element.setDialogTitle(mTitle);
        }

        protected void setValue(String s)
        {
            ListPreference listpreference = (ListPreference)mPreference;
            int i = listpreference.findIndexOfValue(s);
            if(i != -1)
            {
                listpreference.setValueIndex(i);
                setValuePreview(listpreference.getEntries()[i].toString());
            }
        }

        protected ArrayList mItemsText;
        protected ArrayList mItemsValue;
        final MamlConfigSettings this$0;

        private ValueChoiceItem()
        {
            this$0 = MamlConfigSettings.this;
            super(null);
            mItemsText = new ArrayList();
            mItemsValue = new ArrayList();
        }

        ValueChoiceItem(ValueChoiceItem valuechoiceitem)
        {
            this();
        }
    }

    private abstract class VariableItem extends Item
    {

        protected abstract void setValue(String s);

        public void updateValue()
        {
            String s = MamlConfigSettings._2D_get0(MamlConfigSettings.this).getConfigFile().getVariable(mId);
            if(s != null)
            {
                setValue(s);
            } else
            {
                setValue(mDefaultValue);
                OnValueChange(mDefaultValue);
            }
        }

        final MamlConfigSettings this$0;

        private VariableItem()
        {
            this$0 = MamlConfigSettings.this;
            super(null);
        }

        VariableItem(VariableItem variableitem)
        {
            this();
        }
    }


    static ConfigFileHelper _2D_get0(MamlConfigSettings mamlconfigsettings)
    {
        return mamlconfigsettings.mConfigFileHelper;
    }

    static HashMap _2D_get1(MamlConfigSettings mamlconfigsettings)
    {
        return mamlconfigsettings.mPreferenceMap;
    }

    static int _2D_wrap0(MamlConfigSettings mamlconfigsettings)
    {
        return mamlconfigsettings.getNextRequestCode();
    }

    static Item _2D_wrap1(MamlConfigSettings mamlconfigsettings, String s)
    {
        return mamlconfigsettings.createItem(s);
    }

    static void _2D_wrap2(MamlConfigSettings mamlconfigsettings, PreferenceScreen preferencescreen, Element element)
    {
        mamlconfigsettings.createGroup(preferencescreen, element);
    }

    static void _2D_wrap3(MamlConfigSettings mamlconfigsettings, int i, Object obj)
    {
        mamlconfigsettings.putRequestCodeObj(i, obj);
    }

    public MamlConfigSettings()
    {
        mPreferenceMap = new HashMap();
        mNextRequestCode = 100;
        mRequestCodeObjMap = new HashMap();
    }

    public static boolean containsConfig(String s)
    {
        return containsConfig(s, null);
    }

    public static boolean containsConfig(String s, String s1)
    {
        boolean flag;
        Object obj;
        Object obj1;
        Object obj2;
        flag = false;
        if(s == null)
            return false;
        obj = null;
        obj1 = null;
        obj2 = obj;
        ZipFile zipfile = JVM INSTR new #134 <Class ZipFile>;
        obj2 = obj;
        zipfile.ZipFile(s);
        obj2 = JVM INSTR new #139 <Class StringBuilder>;
        ((StringBuilder) (obj2)).StringBuilder();
        s = s1;
        if(s1 == null)
            s = "";
        s = zipfile.getEntry(((StringBuilder) (obj2)).append(s).append("config.xml").toString());
        if(s != null)
            flag = true;
        if(zipfile != null)
            try
            {
                zipfile.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        return flag;
        s1;
        s = obj1;
_L4:
        obj2 = s;
        s1.printStackTrace();
        if(s != null)
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        return false;
        s;
_L2:
        if(obj2 != null)
            try
            {
                ((ZipFile) (obj2)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String s1) { }
        throw s;
        s;
        obj2 = zipfile;
        if(true) goto _L2; else goto _L1
_L1:
        s1;
        s = zipfile;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static boolean containsLockstyleConfig()
    {
        return ThemeResources.getSystem().containsAwesomeLockscreenEntry("config.xml");
    }

    private static ConfigFileHelper createConfigFileHelper(Context context, String s, String s1, String s2)
    {
        if("lockstyle".equals(s))
        {
            s1 = s2;
            if(TextUtils.isEmpty(s2))
                s1 = getComponentId(context, s);
            return new LockscreenConfigFileHelper(s1, context.getApplicationContext());
        } else
        {
            return new ConfigFileHelper(s1, context.getApplicationContext());
        }
    }

    private void createGroup(PreferenceScreen preferencescreen, Element element)
    {
        if(element == null)
        {
            return;
        } else
        {
            final PreferenceCategory category = new PreferenceCategory(this);
            preferencescreen.addPreference(category);
            category.setTitle(element.getAttribute("text"));
            category.setSummary(element.getAttribute("summary"));
            Utils.traverseXmlElementChildren(element, null, new miui.maml.util.Utils.XmlTraverseListener() {

                public void onChild(Element element1)
                {
                    Item item = MamlConfigSettings._2D_wrap1(MamlConfigSettings.this, element1.getNodeName());
                    if(item != null && item.build(category, element1))
                        MamlConfigSettings._2D_get1(MamlConfigSettings.this).put(item.mId, item);
                }

                final MamlConfigSettings this$0;
                final PreferenceCategory val$category;

            
            {
                this$0 = MamlConfigSettings.this;
                category = preferencecategory;
                super();
            }
            }
);
            return;
        }
    }

    private Item createItem(String s)
    {
        if("StringInput".equals(s))
            return new StringInputItem(null);
        if("CheckBox".equals(s))
            return new CheckboxItem(null);
        if("NumberInput".equals(s))
            return new NumberInputItem(null);
        if("StringChoice".equals(s))
            return new StringChoiceItem(null);
        if("NumberChoice".equals(s))
            return new NumberChoiceItem(null);
        if("AppPicker".equals(s))
            return new AppPickerItem(null);
        if("ImagePicker".equals(s))
            return new ImagePickerItem(null);
        else
            return null;
    }

    private void createPreferenceScreen()
    {
        PreferenceScreen preferencescreen;
        Object obj;
        Element element;
        miui.maml.util.Utils.XmlTraverseListener xmltraverselistener;
        Object obj1;
        Object obj2;
        InputStream inputstream;
        InputStream inputstream1;
        InputStream inputstream2;
        InputStream inputstream3;
        InputStream inputstream4;
        Object obj4;
        preferencescreen = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(preferencescreen);
        obj = DocumentBuilderFactory.newInstance();
        element = null;
        xmltraverselistener = null;
        obj1 = null;
        obj2 = null;
        inputstream = null;
        inputstream1 = inputstream;
        inputstream2 = element;
        inputstream3 = xmltraverselistener;
        inputstream4 = obj1;
        obj4 = obj2;
        obj = ((DocumentBuilderFactory) (obj)).newDocumentBuilder();
        inputstream1 = inputstream;
        inputstream2 = element;
        inputstream3 = xmltraverselistener;
        inputstream4 = obj1;
        obj4 = obj2;
        inputstream = mConfigFileHelper.getConfigFileStream(getResources().getConfiguration().locale);
        if(inputstream != null)
            break MISSING_BLOCK_LABEL_115;
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_109;
        inputstream.close();
_L2:
        return;
        obj4;
        if(true) goto _L2; else goto _L1
_L1:
        inputstream1 = inputstream;
        inputstream2 = inputstream;
        inputstream3 = inputstream;
        inputstream4 = inputstream;
        obj4 = inputstream;
        element = ((DocumentBuilder) (obj)).parse(inputstream).getDocumentElement();
        if(element != null)
            break MISSING_BLOCK_LABEL_167;
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_161;
        inputstream.close();
_L4:
        return;
        obj4;
        if(true) goto _L4; else goto _L3
_L3:
        inputstream1 = inputstream;
        inputstream2 = inputstream;
        inputstream3 = inputstream;
        inputstream4 = inputstream;
        obj4 = inputstream;
        boolean flag = element.getNodeName().equals("Config");
        if(flag)
            break MISSING_BLOCK_LABEL_221;
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_215;
        inputstream.close();
_L6:
        return;
        obj4;
        if(true) goto _L6; else goto _L5
_L5:
        inputstream1 = inputstream;
        inputstream2 = inputstream;
        inputstream3 = inputstream;
        inputstream4 = inputstream;
        obj4 = inputstream;
        xmltraverselistener = JVM INSTR new #10  <Class MamlConfigSettings$1>;
        inputstream1 = inputstream;
        inputstream2 = inputstream;
        inputstream3 = inputstream;
        inputstream4 = inputstream;
        obj4 = inputstream;
        xmltraverselistener.this. _cls1();
        inputstream1 = inputstream;
        inputstream2 = inputstream;
        inputstream3 = inputstream;
        inputstream4 = inputstream;
        obj4 = inputstream;
        Utils.traverseXmlElementChildren(element, "Group", xmltraverselistener);
        if(inputstream == null)
            break MISSING_BLOCK_LABEL_312;
        inputstream.close();
_L7:
        return;
        obj4;
          goto _L7
        Object obj3;
        obj3;
        obj4 = inputstream1;
        ((Exception) (obj3)).printStackTrace();
        if(inputstream1 != null)
            try
            {
                inputstream1.close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4) { }
          goto _L7
        obj3;
        obj4 = inputstream2;
        ((IOException) (obj3)).printStackTrace();
        if(inputstream2 != null)
            try
            {
                inputstream2.close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4) { }
          goto _L7
        obj3;
        obj4 = inputstream3;
        ((SAXException) (obj3)).printStackTrace();
        if(inputstream3 != null)
            try
            {
                inputstream3.close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4) { }
          goto _L7
        obj3;
        obj4 = inputstream4;
        ((ParserConfigurationException) (obj3)).printStackTrace();
        if(inputstream4 != null)
            try
            {
                inputstream4.close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj4) { }
          goto _L7
        obj3;
        if(obj4 != null)
            try
            {
                ((InputStream) (obj4)).close();
            }
            catch(IOException ioexception) { }
        throw obj3;
    }

    public static String getComponentId(Context context, String s)
    {
        context = context.getContentResolver().query(Uri.parse((new StringBuilder()).append("content://com.android.thememanager.provider/").append(s).toString()), new String[] {
            "local_id"
        }, null, null, null);
        if(context != null)
        {
            context.moveToFirst();
            if(context.getCount() != 0)
                return context.getString(0);
        }
        return null;
    }

    private int getNextRequestCode()
    {
        int i = mNextRequestCode;
        mNextRequestCode = i + 1;
        return i;
    }

    private Object getRequestCodeObj(int i)
    {
        return mRequestCodeObjMap.get(Integer.valueOf(i));
    }

    private void putRequestCodeObj(int i, Object obj)
    {
        mRequestCodeObjMap.put(Integer.valueOf(i), obj);
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        Object obj = getRequestCodeObj(i);
        if(obj != null && (obj instanceof PickerItem) && ((PickerItem)obj).onActivityResult(j, intent))
            mConfigFileHelper.save();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mConfigFileHelper = createConfigFileHelper(this, getIntent().getStringExtra("maml_code"), getIntent().getStringExtra("maml_path"), getIntent().getStringExtra("maml_id"));
        if(!mConfigFileHelper.containsFile("config.xml"))
        {
            finish();
            return;
        } else
        {
            setContentView(0x11030006);
            getActionBar().setTitle(0x110800b4);
            getActionBar().setHomeButtonEnabled(true);
            createPreferenceScreen();
            return;
        }
    }

    public void onDestroy()
    {
        mConfigFileHelper.save();
        mConfigFileHelper.close();
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if(menuitem.getItemId() == 0x102002c)
            finish();
        return super.onOptionsItemSelected(menuitem);
    }

    public void onPause()
    {
        super.onPause();
        mConfigFileHelper.save();
    }

    public boolean onPreferenceChange(Preference preference, Object obj)
    {
        preference = preference.getKey();
        preference = (Item)mPreferenceMap.get(preference);
        if(preference == null)
            return false;
        boolean flag = preference.OnValueChange(obj);
        if(flag)
            mConfigFileHelper.save();
        return flag;
    }

    public boolean onPreferenceClick(Preference preference)
    {
        preference = preference.getKey();
        preference = (Item)mPreferenceMap.get(preference);
        boolean flag;
        if(preference != null)
            flag = preference.onClick();
        else
            flag = false;
        return flag;
    }

    private static final String CONFIG_NAME = "config.xml";
    public static final String EXTRA_MAML_CODE = "maml_code";
    public static final String EXTRA_MAML_ID = "maml_id";
    public static final String EXTRA_MAML_PATH = "maml_path";
    private static final String LOG_TAG = "MamlConfigSettings";
    private static final String TAG_ROOT = "Config";
    private ConfigFileHelper mConfigFileHelper;
    private int mNextRequestCode;
    private HashMap mPreferenceMap;
    private HashMap mRequestCodeObjMap;

    // Unreferenced inner class miui/maml/MamlConfigSettings$1

/* anonymous class */
    class _cls1
        implements miui.maml.util.Utils.XmlTraverseListener
    {

        public void onChild(Element element)
        {
            MamlConfigSettings._2D_wrap2(MamlConfigSettings.this, rootScreen, element);
        }

        final MamlConfigSettings this$0;
        final PreferenceScreen val$rootScreen;

            
            {
                this$0 = MamlConfigSettings.this;
                rootScreen = preferencescreen;
                super();
            }
    }

}
