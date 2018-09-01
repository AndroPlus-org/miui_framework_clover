// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.content.Context;
import android.content.Intent;
import android.content.pm.*;
import android.content.res.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.speech.tts:
//            TtsEnginesInjector

public class TtsEngines
{
    private static class EngineInfoComparator
        implements Comparator
    {

        public int compare(TextToSpeech.EngineInfo engineinfo, TextToSpeech.EngineInfo engineinfo1)
        {
            if(engineinfo.system && engineinfo1.system ^ true)
                return -1;
            if(engineinfo1.system && engineinfo.system ^ true)
                return 1;
            else
                return engineinfo1.priority - engineinfo.priority;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((TextToSpeech.EngineInfo)obj, (TextToSpeech.EngineInfo)obj1);
        }

        static EngineInfoComparator INSTANCE = new EngineInfoComparator();


        private EngineInfoComparator()
        {
        }
    }


    public TtsEngines(Context context)
    {
        mContext = context;
    }

    private TextToSpeech.EngineInfo getEngineInfo(ResolveInfo resolveinfo, PackageManager packagemanager)
    {
        ServiceInfo serviceinfo = resolveinfo.serviceInfo;
        if(serviceinfo != null)
        {
            TextToSpeech.EngineInfo engineinfo = new TextToSpeech.EngineInfo();
            engineinfo.name = serviceinfo.packageName;
            packagemanager = serviceinfo.loadLabel(packagemanager);
            if(TextUtils.isEmpty(packagemanager))
                packagemanager = engineinfo.name;
            else
                packagemanager = packagemanager.toString();
            engineinfo.label = packagemanager;
            engineinfo.icon = serviceinfo.getIconResource();
            engineinfo.priority = resolveinfo.priority;
            engineinfo.system = isSystemEngine(serviceinfo);
            return engineinfo;
        } else
        {
            return null;
        }
    }

    private boolean isSystemEngine(ServiceInfo serviceinfo)
    {
        boolean flag = false;
        serviceinfo = serviceinfo.applicationInfo;
        boolean flag1 = flag;
        if(serviceinfo != null)
        {
            flag1 = flag;
            if((((ApplicationInfo) (serviceinfo)).flags & 1) != 0)
                flag1 = true;
        }
        return flag1;
    }

    public static Locale normalizeTTSLocale(Locale locale)
    {
        String s = locale.getLanguage();
        String s1 = s;
        if(!TextUtils.isEmpty(s))
        {
            String s2 = (String)sNormalizeLanguage.get(s);
            s1 = s;
            if(s2 != null)
                s1 = s2;
        }
        String s3 = locale.getCountry();
        s = s3;
        if(!TextUtils.isEmpty(s3))
        {
            String s4 = (String)sNormalizeCountry.get(s3);
            s = s3;
            if(s4 != null)
                s = s4;
        }
        return new Locale(s1, s, locale.getVariant());
    }

    private static String parseEnginePrefFromList(String s, String s1)
    {
        if(TextUtils.isEmpty(s))
            return null;
        String as[] = s.split(",");
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            s = as[j];
            int k = s.indexOf(':');
            if(k > 0 && s1.equals(s.substring(0, k)))
                return s.substring(k + 1);
        }

        return null;
    }

    private String settingsActivityFromServiceInfo(ServiceInfo serviceinfo, PackageManager packagemanager)
    {
        XmlResourceParser xmlresourceparser;
        XmlResourceParser xmlresourceparser1;
        XmlResourceParser xmlresourceparser2;
        XmlResourceParser xmlresourceparser3;
        xmlresourceparser = null;
        xmlresourceparser1 = null;
        xmlresourceparser2 = null;
        xmlresourceparser3 = null;
        Object obj = serviceinfo.loadXmlMetaData(packagemanager, "android.speech.tts");
        if(obj != null)
            break MISSING_BLOCK_LABEL_111;
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        packagemanager = JVM INSTR new #207 <Class StringBuilder>;
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        packagemanager.StringBuilder();
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        Log.w("TtsEngines", packagemanager.append("No meta-data found for :").append(serviceinfo).toString());
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        return null;
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        packagemanager = packagemanager.getResourcesForApplication(serviceinfo.applicationInfo);
_L2:
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        int i = ((XmlResourceParser) (obj)).next();
        if(i == 1)
            break MISSING_BLOCK_LABEL_387;
        if(i != 2) goto _L2; else goto _L1
_L1:
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        if("tts-engine".equals(((XmlResourceParser) (obj)).getName()))
            break MISSING_BLOCK_LABEL_302;
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        packagemanager = JVM INSTR new #207 <Class StringBuilder>;
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        packagemanager.StringBuilder();
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        Log.w("TtsEngines", packagemanager.append("Package ").append(serviceinfo).append(" uses unknown tag :").append(((XmlResourceParser) (obj)).getName()).toString());
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        return null;
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        TypedArray typedarray = packagemanager.obtainAttributes(Xml.asAttributeSet(((org.xmlpull.v1.XmlPullParser) (obj))), com.android.internal.R.styleable.TextToSpeechEngine);
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        packagemanager = typedarray.getString(0);
        xmlresourceparser3 = ((XmlResourceParser) (obj));
        xmlresourceparser = ((XmlResourceParser) (obj));
        xmlresourceparser1 = ((XmlResourceParser) (obj));
        xmlresourceparser2 = ((XmlResourceParser) (obj));
        typedarray.recycle();
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        return packagemanager;
        if(obj != null)
            ((XmlResourceParser) (obj)).close();
        return null;
        packagemanager;
        xmlresourceparser2 = xmlresourceparser3;
        obj = JVM INSTR new #207 <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser3;
        ((StringBuilder) (obj)).StringBuilder();
        xmlresourceparser2 = xmlresourceparser3;
        Log.w("TtsEngines", ((StringBuilder) (obj)).append("Error parsing metadata for ").append(serviceinfo).append(":").append(packagemanager).toString());
        if(xmlresourceparser3 != null)
            xmlresourceparser3.close();
        return null;
        XmlPullParserException xmlpullparserexception;
        xmlpullparserexception;
        xmlresourceparser2 = xmlresourceparser;
        packagemanager = JVM INSTR new #207 <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser;
        packagemanager.StringBuilder();
        xmlresourceparser2 = xmlresourceparser;
        Log.w("TtsEngines", packagemanager.append("Error parsing metadata for ").append(serviceinfo).append(":").append(xmlpullparserexception).toString());
        if(xmlresourceparser != null)
            xmlresourceparser.close();
        return null;
        packagemanager;
        xmlresourceparser2 = xmlresourceparser1;
        packagemanager = JVM INSTR new #207 <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser1;
        packagemanager.StringBuilder();
        xmlresourceparser2 = xmlresourceparser1;
        Log.w("TtsEngines", packagemanager.append("Could not load resources for : ").append(serviceinfo).toString());
        if(xmlresourceparser1 != null)
            xmlresourceparser1.close();
        return null;
        serviceinfo;
        if(xmlresourceparser2 != null)
            xmlresourceparser2.close();
        throw serviceinfo;
    }

    public static String[] toOldLocaleStringFormat(Locale locale)
    {
        String as[] = new String[3];
        as[0] = "";
        as[1] = "";
        as[2] = "";
        try
        {
            as[0] = locale.getISO3Language();
            as[1] = locale.getISO3Country();
            as[2] = locale.getVariant();
        }
        // Misplaced declaration of an exception variable
        catch(Locale locale)
        {
            return (new String[] {
                "eng", "USA", ""
            });
        }
        return as;
    }

    private String updateValueInCommaSeparatedList(String s, String s1, String s2)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(!TextUtils.isEmpty(s)) goto _L2; else goto _L1
_L1:
        stringbuilder.append(s1).append(':').append(s2);
_L4:
        return stringbuilder.toString();
_L2:
        s = s.split(",");
        boolean flag = true;
        boolean flag1 = false;
        int i = s.length;
        int j = 0;
        while(j < i) 
        {
            String s3 = s[j];
            int k = s3.indexOf(':');
            boolean flag2 = flag;
            boolean flag3 = flag1;
            if(k > 0)
                if(s1.equals(s3.substring(0, k)))
                {
                    if(flag)
                        flag = false;
                    else
                        stringbuilder.append(',');
                    flag3 = true;
                    stringbuilder.append(s1).append(':').append(s2);
                    flag2 = flag;
                } else
                {
                    if(flag)
                        flag = false;
                    else
                        stringbuilder.append(',');
                    stringbuilder.append(s3);
                    flag2 = flag;
                    flag3 = flag1;
                }
            j++;
            flag = flag2;
            flag1 = flag3;
        }
        if(!flag1)
        {
            stringbuilder.append(',');
            stringbuilder.append(s1).append(':').append(s2);
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public String getDefaultEngine()
    {
        String s = android.provider.Settings.Secure.getString(mContext.getContentResolver(), "tts_default_synth");
        if(!isEngineInstalled(s))
            s = getHighestRankedEngineName();
        return s;
    }

    public TextToSpeech.EngineInfo getEngineInfo(String s)
    {
        PackageManager packagemanager = mContext.getPackageManager();
        Intent intent = new Intent("android.intent.action.TTS_SERVICE");
        intent.setPackage(s);
        s = packagemanager.queryIntentServices(intent, 0x10000);
        if(s != null && s.size() == 1)
            return getEngineInfo((ResolveInfo)s.get(0), packagemanager);
        else
            return null;
    }

    public List getEngines()
    {
        PackageManager packagemanager = mContext.getPackageManager();
        Object obj = packagemanager.queryIntentServices(new Intent("android.intent.action.TTS_SERVICE"), 0x10000);
        if(obj == null)
            return Collections.emptyList();
        ArrayList arraylist = new ArrayList(((List) (obj)).size());
        obj = ((Iterable) (obj)).iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            TextToSpeech.EngineInfo engineinfo = getEngineInfo((ResolveInfo)((Iterator) (obj)).next(), packagemanager);
            if(engineinfo != null)
                arraylist.add(engineinfo);
        } while(true);
        Collections.sort(arraylist, EngineInfoComparator.INSTANCE);
        return arraylist;
    }

    public String getHighestRankedEngineName()
    {
        Object obj = TtsEnginesInjector.getRecommendEngineForLocale(this, Locale.getDefault());
        if(obj != null)
            return ((String) (obj));
        obj = getEngines();
        if(((List) (obj)).size() > 0 && ((TextToSpeech.EngineInfo)((List) (obj)).get(0)).system)
            return ((TextToSpeech.EngineInfo)((List) (obj)).get(0)).name;
        else
            return null;
    }

    public Locale getLocalePrefForEngine(String s)
    {
        return getLocalePrefForEngine(s, android.provider.Settings.Secure.getString(mContext.getContentResolver(), "tts_default_locale"));
    }

    public Locale getLocalePrefForEngine(String s, String s1)
    {
        String s2 = parseEnginePrefFromList(s1, s);
        if(TextUtils.isEmpty(s2))
            return Locale.getDefault();
        s1 = parseLocaleString(s2);
        s = s1;
        if(s1 == null)
        {
            Log.w("TtsEngines", (new StringBuilder()).append("Failed to parse locale ").append(s2).append(", returning en_US instead").toString());
            s = Locale.US;
        }
        return s;
    }

    public Intent getSettingsIntent(String s)
    {
        PackageManager packagemanager = mContext.getPackageManager();
        Object obj = new Intent("android.intent.action.TTS_SERVICE");
        ((Intent) (obj)).setPackage(s);
        obj = packagemanager.queryIntentServices(((Intent) (obj)), 0x10080);
        if(obj != null && ((List) (obj)).size() == 1)
        {
            obj = ((ResolveInfo)((List) (obj)).get(0)).serviceInfo;
            if(obj != null)
            {
                obj = settingsActivityFromServiceInfo(((ServiceInfo) (obj)), packagemanager);
                if(obj != null)
                {
                    Intent intent = new Intent();
                    intent.setClassName(s, ((String) (obj)));
                    return intent;
                }
            }
        }
        return null;
    }

    public boolean isEngineInstalled(String s)
    {
        boolean flag = false;
        if(s == null)
            return false;
        if(getEngineInfo(s) != null)
            flag = true;
        return flag;
    }

    public boolean isLocaleSetToDefaultForEngine(String s)
    {
        return TextUtils.isEmpty(parseEnginePrefFromList(android.provider.Settings.Secure.getString(mContext.getContentResolver(), "tts_default_locale"), s));
    }

    public Locale parseLocaleString(String s)
    {
        String s1 = "";
        String s2 = "";
        String s3 = "";
        Object obj = s2;
        String s4 = s3;
        if(!TextUtils.isEmpty(s))
        {
            String as[] = s.split("[-_]");
            String s5 = as[0].toLowerCase();
            if(as.length == 0)
            {
                Log.w("TtsEngines", (new StringBuilder()).append("Failed to convert ").append(s).append(" to a valid Locale object. Only").append(" separators").toString());
                return null;
            }
            if(as.length > 3)
            {
                Log.w("TtsEngines", (new StringBuilder()).append("Failed to convert ").append(s).append(" to a valid Locale object. Too").append(" many separators").toString());
                return null;
            }
            if(as.length >= 2)
                s2 = as[1].toUpperCase();
            obj = s2;
            s1 = s5;
            s4 = s3;
            if(as.length >= 3)
            {
                s4 = as[2];
                s1 = s5;
                obj = s2;
            }
        }
        s2 = (String)sNormalizeLanguage.get(s1);
        if(s2 != null)
            s1 = s2;
        s2 = (String)sNormalizeCountry.get(obj);
        if(s2 != null)
            obj = s2;
        obj = new Locale(s1, ((String) (obj)), s4);
        try
        {
            ((Locale) (obj)).getISO3Language();
            ((Locale) (obj)).getISO3Country();
        }
        catch(MissingResourceException missingresourceexception)
        {
            Log.w("TtsEngines", (new StringBuilder()).append("Failed to convert ").append(s).append(" to a valid Locale object.").toString());
            return null;
        }
        return ((Locale) (obj));
    }

    public void updateLocalePrefForEngine(String s, Locale locale)
    {
        this;
        JVM INSTR monitorenter ;
        String s1 = android.provider.Settings.Secure.getString(mContext.getContentResolver(), "tts_default_locale");
        if(locale == null)
            break MISSING_BLOCK_LABEL_54;
        locale = locale.toString();
_L1:
        s = updateValueInCommaSeparatedList(s1, s, locale);
        android.provider.Settings.Secure.putString(mContext.getContentResolver(), "tts_default_locale", s.toString());
        this;
        JVM INSTR monitorexit ;
        return;
        locale = "";
          goto _L1
        s;
        throw s;
    }

    private static final boolean DBG = false;
    private static final String LOCALE_DELIMITER_NEW = "_";
    private static final String LOCALE_DELIMITER_OLD = "-";
    private static final String TAG = "TtsEngines";
    private static final String XML_TAG_NAME = "tts-engine";
    private static final Map sNormalizeCountry;
    private static final Map sNormalizeLanguage;
    private final Context mContext;

    static 
    {
        boolean flag = false;
        HashMap hashmap = new HashMap();
        String as[] = Locale.getISOLanguages();
        int i = as.length;
        int k = 0;
        while(k < i) 
        {
            String s = as[k];
            int j;
            MissingResourceException missingresourceexception;
            Locale locale1;
            try
            {
                Locale locale = JVM INSTR new #40  <Class Locale>;
                locale.Locale(s);
                hashmap.put(locale.getISO3Language(), s);
            }
            catch(MissingResourceException missingresourceexception1) { }
            k++;
        }
        sNormalizeLanguage = Collections.unmodifiableMap(hashmap);
        hashmap = new HashMap();
        as = Locale.getISOCountries();
        j = as.length;
        k = ((flag) ? 1 : 0);
        while(k < j) 
        {
            s = as[k];
            try
            {
                locale1 = JVM INSTR new #40  <Class Locale>;
                locale1.Locale("", s);
                hashmap.put(locale1.getISO3Country(), s);
            }
            // Misplaced declaration of an exception variable
            catch(MissingResourceException missingresourceexception) { }
            k++;
        }
        sNormalizeCountry = Collections.unmodifiableMap(hashmap);
    }
}
