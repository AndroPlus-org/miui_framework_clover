// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.soundtrigger;

import android.content.Intent;
import android.content.pm.*;
import android.content.res.*;
import android.text.TextUtils;
import android.util.*;
import java.io.IOException;
import java.util.*;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.hardware.soundtrigger:
//            KeyphraseMetadata

public class KeyphraseEnrollmentInfo
{

    public KeyphraseEnrollmentInfo(PackageManager packagemanager)
    {
        Object obj;
        LinkedList linkedlist;
        obj = packagemanager.queryIntentActivities(new Intent("com.android.intent.action.MANAGE_VOICE_KEYPHRASES"), 0x10000);
        if(obj == null || ((List) (obj)).isEmpty())
        {
            mParseError = "No enrollment applications found";
            mKeyphrasePackageMap = Collections.emptyMap();
            mKeyphrases = null;
            return;
        }
        linkedlist = new LinkedList();
        mKeyphrasePackageMap = new HashMap();
        obj = ((Iterable) (obj)).iterator();
_L2:
        Object obj1;
        if(!((Iterator) (obj)).hasNext())
            break; /* Loop/switch isn't completed */
        obj1 = (ResolveInfo)((Iterator) (obj)).next();
        Object obj2;
        obj2 = packagemanager.getApplicationInfo(((ResolveInfo) (obj1)).activityInfo.packageName, 128);
        if((((ApplicationInfo) (obj2)).privateFlags & 8) == 0)
        {
            StringBuilder stringbuilder = JVM INSTR new #112 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Slog.w("KeyphraseEnrollmentInfo", stringbuilder.append(((ApplicationInfo) (obj2)).packageName).append("is not a privileged system app").toString());
            continue; /* Loop/switch isn't completed */
        }
        if(!"android.permission.MANAGE_VOICE_KEYPHRASES".equals(((ApplicationInfo) (obj2)).permission))
        {
            StringBuilder stringbuilder1 = JVM INSTR new #112 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Slog.w("KeyphraseEnrollmentInfo", stringbuilder1.append(((ApplicationInfo) (obj2)).packageName).append(" does not require MANAGE_VOICE_KEYPHRASES").toString());
            continue; /* Loop/switch isn't completed */
        }
        KeyphraseMetadata keyphrasemetadata = getKeyphraseMetadataFromApplicationInfo(packagemanager, ((ApplicationInfo) (obj2)), linkedlist);
        if(keyphrasemetadata != null)
            try
            {
                mKeyphrasePackageMap.put(keyphrasemetadata, ((ApplicationInfo) (obj2)).packageName);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj2)
            {
                obj1 = (new StringBuilder()).append("error parsing voice enrollment meta-data for ").append(((ResolveInfo) (obj1)).activityInfo.packageName).toString();
                linkedlist.add((new StringBuilder()).append(((String) (obj1))).append(": ").append(obj2).toString());
                Slog.w("KeyphraseEnrollmentInfo", ((String) (obj1)), ((Throwable) (obj2)));
            }
        if(true) goto _L2; else goto _L1
_L1:
        if(mKeyphrasePackageMap.isEmpty())
        {
            linkedlist.add("No suitable enrollment application found");
            Slog.w("KeyphraseEnrollmentInfo", "No suitable enrollment application found");
            mKeyphrases = null;
        } else
        {
            mKeyphrases = (KeyphraseMetadata[])mKeyphrasePackageMap.keySet().toArray(new KeyphraseMetadata[mKeyphrasePackageMap.size()]);
        }
        if(!linkedlist.isEmpty())
            mParseError = TextUtils.join("\n", linkedlist);
        return;
    }

    private KeyphraseMetadata getKeyphraseFromTypedArray(TypedArray typedarray, String s, List list)
    {
        int i;
        String s1;
        String s2;
        ArraySet arrayset;
        i = typedarray.getInt(0, -1);
        if(i <= 0)
        {
            typedarray = (new StringBuilder()).append("No valid searchKeyphraseId specified in meta-data for ").append(s).toString();
            list.add(typedarray);
            Slog.w("KeyphraseEnrollmentInfo", typedarray);
            return null;
        }
        s1 = typedarray.getString(1);
        if(s1 == null)
        {
            typedarray = (new StringBuilder()).append("No valid searchKeyphrase specified in meta-data for ").append(s).toString();
            list.add(typedarray);
            Slog.w("KeyphraseEnrollmentInfo", typedarray);
            return null;
        }
        s2 = typedarray.getString(2);
        if(s2 == null)
        {
            typedarray = (new StringBuilder()).append("No valid searchKeyphraseSupportedLocales specified in meta-data for ").append(s).toString();
            list.add(typedarray);
            Slog.w("KeyphraseEnrollmentInfo", typedarray);
            return null;
        }
        arrayset = new ArraySet();
        if(TextUtils.isEmpty(s2)) goto _L2; else goto _L1
_L1:
        String as[];
        int j;
        try
        {
            as = s2.split(",");
        }
        // Misplaced declaration of an exception variable
        catch(TypedArray typedarray)
        {
            typedarray = (new StringBuilder()).append("Error reading searchKeyphraseSupportedLocales from meta-data for ").append(s).toString();
            list.add(typedarray);
            Slog.w("KeyphraseEnrollmentInfo", typedarray);
            return null;
        }
        j = 0;
        if(j >= as.length)
            break; /* Loop/switch isn't completed */
        arrayset.add(Locale.forLanguageTag(as[j]));
        j++;
        if(true) goto _L3; else goto _L2
_L3:
        break MISSING_BLOCK_LABEL_177;
_L2:
        int k = typedarray.getInt(3, -1);
        if(k < 0)
        {
            typedarray = (new StringBuilder()).append("No valid searchKeyphraseRecognitionFlags specified in meta-data for ").append(s).toString();
            list.add(typedarray);
            Slog.w("KeyphraseEnrollmentInfo", typedarray);
            return null;
        } else
        {
            return new KeyphraseMetadata(i, s1, arrayset, k);
        }
    }

    private KeyphraseMetadata getKeyphraseMetadataFromApplicationInfo(PackageManager packagemanager, ApplicationInfo applicationinfo, List list)
    {
        XmlResourceParser xmlresourceparser;
        XmlResourceParser xmlresourceparser1;
        XmlResourceParser xmlresourceparser2;
        XmlResourceParser xmlresourceparser3;
        String s;
        Object obj;
        Object obj1;
        Object obj2;
        PackageManager packagemanager1;
        PackageManager packagemanager2;
        PackageManager packagemanager3;
        xmlresourceparser = null;
        xmlresourceparser1 = null;
        xmlresourceparser2 = null;
        xmlresourceparser3 = null;
        s = applicationinfo.packageName;
        obj = null;
        obj1 = null;
        obj2 = null;
        packagemanager1 = obj2;
        packagemanager2 = obj;
        packagemanager3 = obj1;
        Object obj3 = applicationinfo.loadXmlMetaData(packagemanager, "android.voice_enrollment");
        if(obj3 != null)
            break MISSING_BLOCK_LABEL_245;
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        packagemanager = JVM INSTR new #112 <Class StringBuilder>;
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        packagemanager.StringBuilder();
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        packagemanager = packagemanager.append("No android.voice_enrollment meta-data for ").append(s).toString();
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        list.add(packagemanager);
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        Slog.w("KeyphraseEnrollmentInfo", packagemanager);
        if(obj3 != null)
            ((XmlResourceParser) (obj3)).close();
        return null;
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        packagemanager = packagemanager.getResourcesForApplication(applicationinfo);
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        applicationinfo = Xml.asAttributeSet(((org.xmlpull.v1.XmlPullParser) (obj3)));
_L2:
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        int i = ((XmlResourceParser) (obj3)).next();
        if(i != 1 && i != 2) goto _L2; else goto _L1
_L1:
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        if("voice-enrollment-application".equals(((XmlResourceParser) (obj3)).getName()))
            break MISSING_BLOCK_LABEL_599;
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        packagemanager = JVM INSTR new #112 <Class StringBuilder>;
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        packagemanager.StringBuilder();
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        packagemanager = packagemanager.append("Meta-data does not start with voice-enrollment-application tag for ").append(s).toString();
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        list.add(packagemanager);
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        Slog.w("KeyphraseEnrollmentInfo", packagemanager);
        if(obj3 != null)
            ((XmlResourceParser) (obj3)).close();
        return null;
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        applicationinfo = packagemanager.obtainAttributes(applicationinfo, com.android.internal.R.styleable.VoiceEnrollmentApplication);
        packagemanager1 = obj2;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = obj;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = obj1;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        packagemanager = getKeyphraseFromTypedArray(applicationinfo, s, list);
        packagemanager1 = packagemanager;
        xmlresourceparser3 = ((XmlResourceParser) (obj3));
        packagemanager2 = packagemanager;
        xmlresourceparser = ((XmlResourceParser) (obj3));
        packagemanager3 = packagemanager;
        xmlresourceparser1 = ((XmlResourceParser) (obj3));
        xmlresourceparser2 = ((XmlResourceParser) (obj3));
        applicationinfo.recycle();
        applicationinfo = packagemanager;
        if(obj3 != null)
        {
            ((XmlResourceParser) (obj3)).close();
            applicationinfo = packagemanager;
        }
_L4:
        return applicationinfo;
        packagemanager;
        xmlresourceparser2 = xmlresourceparser3;
        applicationinfo = JVM INSTR new #112 <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser3;
        applicationinfo.StringBuilder();
        xmlresourceparser2 = xmlresourceparser3;
        applicationinfo = applicationinfo.append("Error parsing keyphrase enrollment meta-data for ").append(s).toString();
        xmlresourceparser2 = xmlresourceparser3;
        obj3 = JVM INSTR new #112 <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser3;
        ((StringBuilder) (obj3)).StringBuilder();
        xmlresourceparser2 = xmlresourceparser3;
        list.add(((StringBuilder) (obj3)).append(applicationinfo).append(": ").append(packagemanager).toString());
        xmlresourceparser2 = xmlresourceparser3;
        Slog.w("KeyphraseEnrollmentInfo", applicationinfo, packagemanager);
        applicationinfo = packagemanager1;
        if(xmlresourceparser3 != null)
        {
            xmlresourceparser3.close();
            applicationinfo = packagemanager1;
        }
        continue; /* Loop/switch isn't completed */
        packagemanager;
        xmlresourceparser2 = xmlresourceparser;
        applicationinfo = JVM INSTR new #112 <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser;
        applicationinfo.StringBuilder();
        xmlresourceparser2 = xmlresourceparser;
        obj3 = applicationinfo.append("Error parsing keyphrase enrollment meta-data for ").append(s).toString();
        xmlresourceparser2 = xmlresourceparser;
        applicationinfo = JVM INSTR new #112 <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser;
        applicationinfo.StringBuilder();
        xmlresourceparser2 = xmlresourceparser;
        list.add(applicationinfo.append(((String) (obj3))).append(": ").append(packagemanager).toString());
        xmlresourceparser2 = xmlresourceparser;
        Slog.w("KeyphraseEnrollmentInfo", ((String) (obj3)), packagemanager);
        applicationinfo = packagemanager2;
        if(xmlresourceparser != null)
        {
            xmlresourceparser.close();
            applicationinfo = packagemanager2;
        }
        continue; /* Loop/switch isn't completed */
        packagemanager;
        xmlresourceparser2 = xmlresourceparser1;
        applicationinfo = JVM INSTR new #112 <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser1;
        applicationinfo.StringBuilder();
        xmlresourceparser2 = xmlresourceparser1;
        applicationinfo = applicationinfo.append("Error parsing keyphrase enrollment meta-data for ").append(s).toString();
        xmlresourceparser2 = xmlresourceparser1;
        obj3 = JVM INSTR new #112 <Class StringBuilder>;
        xmlresourceparser2 = xmlresourceparser1;
        ((StringBuilder) (obj3)).StringBuilder();
        xmlresourceparser2 = xmlresourceparser1;
        list.add(((StringBuilder) (obj3)).append(applicationinfo).append(": ").append(packagemanager).toString());
        xmlresourceparser2 = xmlresourceparser1;
        Slog.w("KeyphraseEnrollmentInfo", applicationinfo, packagemanager);
        applicationinfo = packagemanager3;
        if(xmlresourceparser1 != null)
        {
            xmlresourceparser1.close();
            applicationinfo = packagemanager3;
        }
        if(true) goto _L4; else goto _L3
_L3:
        packagemanager;
        if(xmlresourceparser2 != null)
            xmlresourceparser2.close();
        throw packagemanager;
    }

    public KeyphraseMetadata getKeyphraseMetadata(String s, Locale locale)
    {
        int i = 0;
        if(mKeyphrases != null && mKeyphrases.length > 0)
        {
            KeyphraseMetadata akeyphrasemetadata[] = mKeyphrases;
            for(int j = akeyphrasemetadata.length; i < j; i++)
            {
                KeyphraseMetadata keyphrasemetadata = akeyphrasemetadata[i];
                if(keyphrasemetadata.supportsPhrase(s) && keyphrasemetadata.supportsLocale(locale))
                    return keyphrasemetadata;
            }

        }
        Slog.w("KeyphraseEnrollmentInfo", (new StringBuilder()).append("No enrollment application supports the given keyphrase/locale: '").append(s).append("'/").append(locale).toString());
        return null;
    }

    public Intent getManageKeyphraseIntent(int i, String s, Locale locale)
    {
        if(mKeyphrasePackageMap == null || mKeyphrasePackageMap.isEmpty())
        {
            Slog.w("KeyphraseEnrollmentInfo", "No enrollment application exists");
            return null;
        }
        KeyphraseMetadata keyphrasemetadata = getKeyphraseMetadata(s, locale);
        if(keyphrasemetadata != null)
            return (new Intent("com.android.intent.action.MANAGE_VOICE_KEYPHRASES")).setPackage((String)mKeyphrasePackageMap.get(keyphrasemetadata)).putExtra("com.android.intent.extra.VOICE_KEYPHRASE_HINT_TEXT", s).putExtra("com.android.intent.extra.VOICE_KEYPHRASE_LOCALE", locale.toLanguageTag()).putExtra("com.android.intent.extra.VOICE_KEYPHRASE_ACTION", i);
        else
            return null;
    }

    public String getParseError()
    {
        return mParseError;
    }

    public KeyphraseMetadata[] listKeyphraseMetadata()
    {
        return mKeyphrases;
    }

    public String toString()
    {
        return (new StringBuilder()).append("KeyphraseEnrollmentInfo [Keyphrases=").append(mKeyphrasePackageMap.toString()).append(", ParseError=").append(mParseError).append("]").toString();
    }

    public static final String ACTION_MANAGE_VOICE_KEYPHRASES = "com.android.intent.action.MANAGE_VOICE_KEYPHRASES";
    public static final String EXTRA_VOICE_KEYPHRASE_ACTION = "com.android.intent.extra.VOICE_KEYPHRASE_ACTION";
    public static final String EXTRA_VOICE_KEYPHRASE_HINT_TEXT = "com.android.intent.extra.VOICE_KEYPHRASE_HINT_TEXT";
    public static final String EXTRA_VOICE_KEYPHRASE_LOCALE = "com.android.intent.extra.VOICE_KEYPHRASE_LOCALE";
    private static final String TAG = "KeyphraseEnrollmentInfo";
    private static final String VOICE_KEYPHRASE_META_DATA = "android.voice_enrollment";
    private final Map mKeyphrasePackageMap;
    private final KeyphraseMetadata mKeyphrases[];
    private String mParseError;
}
