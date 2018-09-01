// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textclassifier;

import android.content.*;
import android.content.pm.*;
import android.net.Uri;
import android.os.LocaleList;
import android.os.ParcelFileDescriptor;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.WordIterator;
import android.text.style.ClickableSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.util.Preconditions;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.view.textclassifier:
//            TextClassifier, TextClassification, SmartSelection, TextClassifierConstants, 
//            LinksInfo, TextSelection

final class TextClassifierImpl
    implements TextClassifier
{
    private static final class IntentFactory
    {

        public static Intent create(Context context, String s, String s1)
        {
            s = s.trim().toLowerCase(Locale.ENGLISH);
            s1 = s1.trim();
            if(s.equals("email"))
                return (new Intent("android.intent.action.SENDTO")).setData(Uri.parse(String.format("mailto:%s", new Object[] {
                    s1
                })));
            if(s.equals("phone"))
                return (new Intent("android.intent.action.DIAL")).setData(Uri.parse(String.format("tel:%s", new Object[] {
                    s1
                })));
            if(s.equals("address"))
                return (new Intent("android.intent.action.VIEW")).setData(Uri.parse(String.format("geo:0,0?q=%s", new Object[] {
                    s1
                })));
            if(s.equals("url"))
            {
                if(s1.toLowerCase().startsWith("http://"))
                    s = (new StringBuilder()).append("http://").append(s1.substring("http://".length())).toString();
                else
                if(s1.toLowerCase().startsWith("https://"))
                    s = (new StringBuilder()).append("https://").append(s1.substring("https://".length())).toString();
                else
                    s = (new StringBuilder()).append("http://").append(s1).toString();
                return (new Intent("android.intent.action.VIEW", Uri.parse(s))).putExtra("com.android.browser.application_id", context.getPackageName());
            } else
            {
                return null;
            }
        }

        public static String getLabel(Context context, String s)
        {
            s = s.trim().toLowerCase(Locale.ENGLISH);
            if(s.equals("email"))
                return context.getString(0x10401cf);
            if(s.equals("phone"))
                return context.getString(0x10401b0);
            if(s.equals("address"))
                return context.getString(0x1040343);
            if(s.equals("url"))
                return context.getString(0x10400e5);
            else
                return null;
        }

        public static int getLogType(String s)
        {
            s = s.trim().toLowerCase(Locale.ENGLISH);
            if(s.equals("email"))
                return 1;
            if(s.equals("phone"))
                return 2;
            if(s.equals("address"))
                return 3;
            return !s.equals("url") ? 0 : 4;
        }

        private IntentFactory()
        {
        }
    }

    private static final class LinksInfoFactory
    {

        private static List avoidOverlaps(List list, String s)
        {
            Collections.sort(list, Comparator.comparingInt(_.Lambda.Sy__B53nI_asuVbYEz1JE9PRAk8.$INST$0));
            s = new LinkedHashMap();
            int i = list.size();
            for(int j = 0; j < i; j++)
            {
                SpanSpec spanspec = (SpanSpec)list.get(j);
                SpanSpec spanspec2 = (SpanSpec)s.get(Integer.valueOf(SpanSpec._2D_get2(spanspec)));
                if(spanspec2 == null || SpanSpec._2D_get0(spanspec2) < SpanSpec._2D_get0(spanspec))
                    s.put(Integer.valueOf(SpanSpec._2D_get2(spanspec)), spanspec);
            }

            list = new LinkedList();
            s = s.values().iterator();
            do
            {
                if(!s.hasNext())
                    break;
                SpanSpec spanspec3 = (SpanSpec)s.next();
                if(list.isEmpty())
                {
                    list.add(spanspec3);
                } else
                {
                    SpanSpec spanspec1 = (SpanSpec)list.getLast();
                    if(SpanSpec._2D_get2(spanspec3) < SpanSpec._2D_get0(spanspec1))
                    {
                        if(SpanSpec._2D_get0(spanspec3) - SpanSpec._2D_get2(spanspec3) > SpanSpec._2D_get0(spanspec1) - SpanSpec._2D_get2(spanspec1))
                            list.set(list.size() - 1, spanspec3);
                    } else
                    {
                        list.add(spanspec3);
                    }
                }
            } while(true);
            return list;
        }

        public static LinksInfo create(Context context, SmartSelection smartselection, String s, int i)
        {
            WordIterator worditerator = new WordIterator();
            worditerator.setCharSequence(s, 0, s.length());
            ArrayList arraylist = new ArrayList();
            int j = 0;
            do
            {
                int k = worditerator.nextBoundary(j);
                if(k == -1)
                    break;
                if(!TextUtils.isEmpty(s.substring(j, k)))
                {
                    int ai[] = smartselection.suggest(s, j, k);
                    j = ai[0];
                    int l = ai[1];
                    if(j >= 0 && l <= s.length() && j <= l)
                    {
                        SmartSelection.ClassificationResult aclassificationresult[] = smartselection.classifyText(s, j, l, TextClassifierImpl._2D_wrap0(s, j, l));
                        if(aclassificationresult.length > 0)
                        {
                            Object obj = TextClassifierImpl._2D_wrap1(aclassificationresult);
                            if(matches(((String) (obj)), i))
                            {
                                obj = IntentFactory.create(context, ((String) (obj)), s.substring(j, l));
                                if(hasActivityHandler(context, ((Intent) (obj))))
                                    arraylist.add(new SpanSpec(j, l, createSpan(context, ((Intent) (obj)))));
                            }
                        }
                    }
                    j = k;
                }
            } while(true);
            return new LinksInfoImpl(s, avoidOverlaps(arraylist, s));
        }

        private static ClickableSpan createSpan(Context context, Intent intent)
        {
            return new ClickableSpan(context, intent) {

                public void onClick(View view)
                {
                    context.startActivity(intent);
                }

                final Context val$context;
                final Intent val$intent;

            
            {
                context = context1;
                intent = intent1;
                super();
            }
            }
;
        }

        private static boolean hasActivityHandler(Context context, Intent intent)
        {
            boolean flag = false;
            if(intent == null)
                return false;
            context = context.getPackageManager().resolveActivity(intent, 0);
            boolean flag1 = flag;
            if(context != null)
            {
                flag1 = flag;
                if(((ResolveInfo) (context)).activityInfo != null)
                    flag1 = true;
            }
            return flag1;
        }

        static int lambda$_2D_android_view_textclassifier_TextClassifierImpl$LinksInfoFactory_22315(SpanSpec spanspec)
        {
            return SpanSpec._2D_get2(spanspec);
        }

        private static boolean matches(String s, int i)
        {
            s = s.trim().toLowerCase(Locale.ENGLISH);
            if((i & 4) != 0 && "phone".equals(s))
                return true;
            if((i & 2) != 0 && "email".equals(s))
                return true;
            if((i & 8) != 0 && "address".equals(s))
                return true;
            return (i & 1) != 0 && "url".equals(s);
        }

        private LinksInfoFactory()
        {
        }
    }

    private static final class LinksInfoFactory.LinksInfoImpl
        implements LinksInfo
    {

        public boolean apply(CharSequence charsequence)
        {
            boolean flag;
            if(charsequence != null)
                flag = true;
            else
                flag = false;
            Preconditions.checkArgument(flag);
            if((charsequence instanceof Spannable) && mOriginalText.toString().equals(charsequence.toString()))
            {
                Spannable spannable = (Spannable)charsequence;
                int i = mSpans.size();
                for(int j = 0; j < i; j++)
                {
                    charsequence = (LinksInfoFactory.SpanSpec)mSpans.get(j);
                    spannable.setSpan(LinksInfoFactory.SpanSpec._2D_get1(charsequence), LinksInfoFactory.SpanSpec._2D_get2(charsequence), LinksInfoFactory.SpanSpec._2D_get0(charsequence), 0);
                }

                return true;
            } else
            {
                return false;
            }
        }

        private final CharSequence mOriginalText;
        private final List mSpans;

        LinksInfoFactory.LinksInfoImpl(CharSequence charsequence, List list)
        {
            mOriginalText = charsequence;
            mSpans = list;
        }
    }

    private static final class LinksInfoFactory.SpanSpec
    {

        static int _2D_get0(LinksInfoFactory.SpanSpec spanspec)
        {
            return spanspec.mEnd;
        }

        static ClickableSpan _2D_get1(LinksInfoFactory.SpanSpec spanspec)
        {
            return spanspec.mSpan;
        }

        static int _2D_get2(LinksInfoFactory.SpanSpec spanspec)
        {
            return spanspec.mStart;
        }

        private final int mEnd;
        private final ClickableSpan mSpan;
        private final int mStart;

        LinksInfoFactory.SpanSpec(int i, int j, ClickableSpan clickablespan)
        {
            mStart = i;
            mEnd = j;
            mSpan = clickablespan;
        }
    }


    static int _2D_wrap0(CharSequence charsequence, int i, int j)
    {
        return getHintFlags(charsequence, i, j);
    }

    static String _2D_wrap1(SmartSelection.ClassificationResult aclassificationresult[])
    {
        return getHighestScoringType(aclassificationresult);
    }

    TextClassifierImpl(Context context)
    {
        mContext = (Context)Preconditions.checkNotNull(context);
    }

    private static void closeAndLogError(ParcelFileDescriptor parcelfiledescriptor)
    {
        parcelfiledescriptor.close();
_L1:
        return;
        parcelfiledescriptor;
        Log.e("TextClassifierImpl", "Error closing file.", parcelfiledescriptor);
          goto _L1
    }

    private TextClassification createClassificationResult(SmartSelection.ClassificationResult aclassificationresult[], CharSequence charsequence)
    {
        Object obj = null;
        TextClassification.Builder builder = (new TextClassification.Builder()).setText(charsequence.toString());
        int i = aclassificationresult.length;
        for(int j = 0; j < i; j++)
            builder.setEntityType(aclassificationresult[j].mCollection, aclassificationresult[j].mScore);

        String s = getHighestScoringType(aclassificationresult);
        builder.setLogType(IntentFactory.getLogType(s));
        Object obj2 = IntentFactory.create(mContext, s, charsequence.toString());
        if(obj2 != null)
        {
            aclassificationresult = mContext.getPackageManager();
            charsequence = aclassificationresult.resolveActivity(((Intent) (obj2)), 0);
        } else
        {
            aclassificationresult = null;
            charsequence = null;
        }
        if(charsequence != null && ((ResolveInfo) (charsequence)).activityInfo != null)
        {
            builder.setIntent(((Intent) (obj2))).setOnClickListener(TextClassification.createStartActivityOnClickListener(mContext, ((Intent) (obj2))));
            String s1 = ((ResolveInfo) (charsequence)).activityInfo.packageName;
            if("android".equals(s1))
            {
                builder.setLabel(IntentFactory.getLabel(mContext, s));
            } else
            {
                ((Intent) (obj2)).setComponent(new ComponentName(s1, ((ResolveInfo) (charsequence)).activityInfo.name));
                obj2 = ((ResolveInfo) (charsequence)).activityInfo.loadIcon(aclassificationresult);
                Object obj1 = obj2;
                if(obj2 == null)
                    obj1 = charsequence.loadIcon(aclassificationresult);
                builder.setIcon(((android.graphics.drawable.Drawable) (obj1)));
                obj2 = ((ResolveInfo) (charsequence)).activityInfo.loadLabel(aclassificationresult);
                obj1 = obj2;
                if(obj2 == null)
                    obj1 = charsequence.loadLabel(aclassificationresult);
                aclassificationresult = obj;
                if(obj1 != null)
                    aclassificationresult = ((CharSequence) (obj1)).toString();
                builder.setLabel(aclassificationresult);
            }
        }
        return builder.setVersionInfo(getVersionInfo()).build();
    }

    private void destroySmartSelectionIfExistsLocked()
    {
        if(mSmartSelection != null)
        {
            mSmartSelection.close();
            mSmartSelection = null;
        }
    }

    private Locale findBestSupportedLocaleLocked(LocaleList localelist)
    {
        List list;
        ArrayList arraylist;
        if(localelist.isEmpty())
            localelist = LocaleList.getDefault().toLanguageTags();
        else
            localelist = (new StringBuilder()).append(localelist.toLanguageTags()).append(",").append(LocaleList.getDefault().toLanguageTags()).toString();
        list = java.util.Locale.LanguageRange.parse(localelist);
        arraylist = new ArrayList(getFactoryModelFilePathsLocked().keySet());
        localelist = getUpdatedModelLocale();
        if(localelist != null)
            arraylist.add(localelist);
        return Locale.lookup(list, arraylist);
    }

    private Map getFactoryModelFilePathsLocked()
    {
        if(mModelFilePaths == null)
        {
            HashMap hashmap = new HashMap();
            File file = new File("/etc/textclassifier/");
            if(file.exists() && file.isDirectory())
            {
                File afile[] = file.listFiles();
                Pattern pattern = Pattern.compile("textclassifier\\.smartselection\\.(.*)\\.model");
                int i = afile.length;
                for(int j = 0; j < i; j++)
                {
                    File file1 = afile[j];
                    Matcher matcher = pattern.matcher(file1.getName());
                    if(matcher.matches() && file1.isFile())
                        hashmap.put(Locale.forLanguageTag(matcher.group(1)), file1.getAbsolutePath());
                }

            }
            mModelFilePaths = hashmap;
        }
        return mModelFilePaths;
    }

    private ParcelFileDescriptor getFdLocked(Locale locale)
        throws FileNotFoundException
    {
        ParcelFileDescriptor parcelfiledescriptor;
        String s;
        try
        {
            File file = JVM INSTR new #305 <Class File>;
            file.File("/data/misc/textclassifier/textclassifier.smartselection.model");
            parcelfiledescriptor = ParcelFileDescriptor.open(file, 0x10000000);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            parcelfiledescriptor = null;
        }
        s = (String)getFactoryModelFilePathsLocked().get(locale);
        Object obj;
        if(s != null)
            try
            {
                obj = JVM INSTR new #305 <Class File>;
                ((File) (obj)).File(s);
                obj = ParcelFileDescriptor.open(((File) (obj)), 0x10000000);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                obj = null;
            }
        else
            obj = null;
        if(parcelfiledescriptor == null)
            if(obj != null)
                return ((ParcelFileDescriptor) (obj));
            else
                throw new FileNotFoundException(String.format("No model file found for %s", new Object[] {
                    locale
                }));
        int i = parcelfiledescriptor.getFd();
        boolean flag = Objects.equals(locale.getLanguage().trim().toLowerCase(), SmartSelection.getLanguage(i).trim().toLowerCase());
        if(obj == null)
            if(flag)
            {
                return parcelfiledescriptor;
            } else
            {
                closeAndLogError(parcelfiledescriptor);
                throw new FileNotFoundException(String.format("No model file found for %s", new Object[] {
                    locale
                }));
            }
        if(!flag)
        {
            closeAndLogError(parcelfiledescriptor);
            return ((ParcelFileDescriptor) (obj));
        }
        i = SmartSelection.getVersion(i);
        int j = SmartSelection.getVersion(((ParcelFileDescriptor) (obj)).getFd());
        if(i > j)
        {
            closeAndLogError(((ParcelFileDescriptor) (obj)));
            mVersion = i;
            return parcelfiledescriptor;
        } else
        {
            closeAndLogError(parcelfiledescriptor);
            mVersion = j;
            return ((ParcelFileDescriptor) (obj));
        }
    }

    private static String getHighestScoringType(SmartSelection.ClassificationResult aclassificationresult[])
    {
        if(aclassificationresult.length < 1)
            return "";
        String s = aclassificationresult[0].mCollection;
        float f = aclassificationresult[0].mScore;
        int i = aclassificationresult.length;
        for(int j = 1; j < i;)
        {
            float f1 = f;
            if(aclassificationresult[j].mScore > f)
            {
                s = aclassificationresult[j].mCollection;
                f1 = aclassificationresult[j].mScore;
            }
            j++;
            f = f1;
        }

        return s;
    }

    private static int getHintFlags(CharSequence charsequence, int i, int j)
    {
        byte byte0 = 0;
        CharSequence charsequence1 = charsequence.subSequence(i, j);
        if(Patterns.AUTOLINK_EMAIL_ADDRESS.matcher(charsequence1).matches())
            byte0 = 2;
        int k = byte0;
        if(Patterns.AUTOLINK_WEB_URL.matcher(charsequence1).matches())
        {
            k = byte0;
            if(Linkify.sUrlMatchFilter.acceptMatch(charsequence, i, j))
                k = byte0 | 1;
        }
        return k;
    }

    private SmartSelection getSmartSelection(LocaleList localelist)
        throws FileNotFoundException
    {
        Object obj = mSmartSelectionLock;
        obj;
        JVM INSTR monitorenter ;
        LocaleList localelist1;
        localelist1 = localelist;
        if(localelist != null)
            break MISSING_BLOCK_LABEL_17;
        localelist1 = LocaleList.getEmptyLocaleList();
        localelist = findBestSupportedLocaleLocked(localelist1);
        if(localelist != null)
            break MISSING_BLOCK_LABEL_45;
        localelist = JVM INSTR new #360 <Class FileNotFoundException>;
        localelist.FileNotFoundException("No file for null locale");
        throw localelist;
        localelist;
        obj;
        JVM INSTR monitorexit ;
        throw localelist;
        if(mSmartSelection == null || Objects.equals(mLocale, localelist) ^ true)
        {
            destroySmartSelectionIfExistsLocked();
            ParcelFileDescriptor parcelfiledescriptor = getFdLocked(localelist);
            SmartSelection smartselection = JVM INSTR new #235 <Class SmartSelection>;
            smartselection.SmartSelection(parcelfiledescriptor.getFd());
            mSmartSelection = smartselection;
            closeAndLogError(parcelfiledescriptor);
            mLocale = localelist;
        }
        localelist = mSmartSelection;
        obj;
        JVM INSTR monitorexit ;
        return localelist;
    }

    private Locale getUpdatedModelLocale()
    {
        Locale locale;
        try
        {
            Object obj = JVM INSTR new #305 <Class File>;
            ((File) (obj)).File("/data/misc/textclassifier/textclassifier.smartselection.model");
            obj = ParcelFileDescriptor.open(((File) (obj)), 0x10000000);
            locale = Locale.forLanguageTag(SmartSelection.getLanguage(((ParcelFileDescriptor) (obj)).getFd()));
            closeAndLogError(((ParcelFileDescriptor) (obj)));
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            return null;
        }
        return locale;
    }

    private String getVersionInfo()
    {
        Object obj = mSmartSelectionLock;
        obj;
        JVM INSTR monitorenter ;
        String s;
        if(mLocale == null)
            break MISSING_BLOCK_LABEL_49;
        s = String.format("%s_v%d", new Object[] {
            mLocale.toLanguageTag(), Integer.valueOf(mVersion)
        });
        return s;
        obj;
        JVM INSTR monitorexit ;
        return "";
        Exception exception;
        exception;
        throw exception;
    }

    private static void validateInput(CharSequence charsequence, int i, int j)
    {
        boolean flag = true;
        boolean flag1;
        if(charsequence != null)
            flag1 = true;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1);
        if(i >= 0)
            flag1 = true;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1);
        if(j <= charsequence.length())
            flag1 = true;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1);
        if(j > i)
            flag1 = flag;
        else
            flag1 = false;
        Preconditions.checkArgument(flag1);
    }

    public TextClassification classifyText(CharSequence charsequence, int i, int j, LocaleList localelist)
    {
        validateInput(charsequence, i, j);
        Object obj;
        if(charsequence.length() <= 0)
            break MISSING_BLOCK_LABEL_82;
        obj = charsequence.toString();
        SmartSelection.ClassificationResult aclassificationresult[] = getSmartSelection(localelist).classifyText(((String) (obj)), i, j, getHintFlags(((CharSequence) (obj)), i, j));
        if(aclassificationresult.length <= 0)
            break MISSING_BLOCK_LABEL_82;
        obj = createClassificationResult(aclassificationresult, ((String) (obj)).subSequence(i, j));
        return ((TextClassification) (obj));
        Throwable throwable;
        throwable;
        Log.e("TextClassifierImpl", "Error getting assist info.", throwable);
        return TextClassifier.NO_OP.classifyText(charsequence, i, j, localelist);
    }

    public LinksInfo getLinks(CharSequence charsequence, int i, LocaleList localelist)
    {
        boolean flag;
        LinksInfo linksinfo;
        if(charsequence != null)
            flag = true;
        else
            flag = false;
        Preconditions.checkArgument(flag);
        try
        {
            linksinfo = LinksInfoFactory.create(mContext, getSmartSelection(localelist), charsequence.toString(), i);
        }
        catch(Throwable throwable)
        {
            Log.e("TextClassifierImpl", "Error getting links info.", throwable);
            return TextClassifier.NO_OP.getLinks(charsequence, i, localelist);
        }
        return linksinfo;
    }

    public TextClassifierConstants getSettings()
    {
        if(mSettings == null)
            mSettings = TextClassifierConstants.loadFromString(android.provider.Settings.Global.getString(mContext.getContentResolver(), "text_classifier_constants"));
        return mSettings;
    }

    public void logEvent(String s, String s1)
    {
        if("TextClassifierImpl".equals(s))
            mMetricsLogger.count(s1, 1);
    }

    public TextSelection suggestSelection(CharSequence charsequence, int i, int j, LocaleList localelist)
    {
        validateInput(charsequence, i, j);
        SmartSelection smartselection;
        String s;
        int ai[];
        if(charsequence.length() <= 0)
            break MISSING_BLOCK_LABEL_194;
        smartselection = getSmartSelection(localelist);
        s = charsequence.toString();
        ai = smartselection.suggest(s, i, j);
        int k;
        int l;
        k = ai[0];
        l = ai[1];
        if(k > l || k < 0)
            break MISSING_BLOCK_LABEL_185;
        if(l > s.length() || k > i || l < j)
            break MISSING_BLOCK_LABEL_185;
        SmartSelection.ClassificationResult aclassificationresult[];
        TextSelection.Builder builder;
        builder = JVM INSTR new #536 <Class TextSelection$Builder>;
        builder.TextSelection.Builder(k, l);
        aclassificationresult = smartselection.classifyText(s, k, l, getHintFlags(s, k, l));
        l = aclassificationresult.length;
        k = 0;
_L2:
        if(k >= l)
            break; /* Loop/switch isn't completed */
        builder.setEntityType(aclassificationresult[k].mCollection, aclassificationresult[k].mScore);
        k++;
        if(true) goto _L2; else goto _L1
_L1:
        return builder.setLogSource("TextClassifierImpl").setVersionInfo(getVersionInfo()).build();
        Log.d("TextClassifierImpl", "Got bad indices for input text. Ignoring result.");
_L4:
        return TextClassifier.NO_OP.suggestSelection(charsequence, i, j, localelist);
        Throwable throwable;
        throwable;
        Log.e("TextClassifierImpl", "Error suggesting selection for text. No changes to selection suggested.", throwable);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static final String LOG_TAG = "TextClassifierImpl";
    private static final String MODEL_DIR = "/etc/textclassifier/";
    private static final String MODEL_FILE_REGEX = "textclassifier\\.smartselection\\.(.*)\\.model";
    private static final String UPDATED_MODEL_FILE_PATH = "/data/misc/textclassifier/textclassifier.smartselection.model";
    private final Context mContext;
    private Locale mLocale;
    private final MetricsLogger mMetricsLogger = new MetricsLogger();
    private Map mModelFilePaths;
    private TextClassifierConstants mSettings;
    private SmartSelection mSmartSelection;
    private final Object mSmartSelectionLock = new Object();
    private int mVersion;
}
