// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.ActivityManager;
import android.content.*;
import android.content.pm.*;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import com.android.internal.content.PackageMonitor;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.xmlpull.v1.*;

public class ActivityChooserModel extends DataSetObservable
{
    public static interface ActivityChooserModelClient
    {

        public abstract void setActivityChooserModel(ActivityChooserModel activitychoosermodel);
    }

    public final class ActivityResolveInfo
        implements Comparable
    {

        public int compareTo(ActivityResolveInfo activityresolveinfo)
        {
            return Float.floatToIntBits(activityresolveinfo.weight) - Float.floatToIntBits(weight);
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((ActivityResolveInfo)obj);
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (ActivityResolveInfo)obj;
            return Float.floatToIntBits(weight) == Float.floatToIntBits(((ActivityResolveInfo) (obj)).weight);
        }

        public int hashCode()
        {
            return Float.floatToIntBits(weight) + 31;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("[");
            stringbuilder.append("resolveInfo:").append(resolveInfo.toString());
            stringbuilder.append("; weight:").append(new BigDecimal(weight));
            stringbuilder.append("]");
            return stringbuilder.toString();
        }

        public final ResolveInfo resolveInfo;
        final ActivityChooserModel this$0;
        public float weight;

        public ActivityResolveInfo(ResolveInfo resolveinfo)
        {
            this$0 = ActivityChooserModel.this;
            super();
            resolveInfo = resolveinfo;
        }
    }

    public static interface ActivitySorter
    {

        public abstract void sort(Intent intent, List list, List list1);
    }

    private final class DataModelPackageMonitor extends PackageMonitor
    {

        public void onSomePackagesChanged()
        {
            ActivityChooserModel._2D_set1(ActivityChooserModel.this, true);
        }

        final ActivityChooserModel this$0;

        private DataModelPackageMonitor()
        {
            this$0 = ActivityChooserModel.this;
            super();
        }

        DataModelPackageMonitor(DataModelPackageMonitor datamodelpackagemonitor)
        {
            this();
        }
    }

    private final class DefaultSorter
        implements ActivitySorter
    {

        public void sort(Intent intent, List list, List list1)
        {
            intent = mPackageNameToActivityMap;
            intent.clear();
            int i = list.size();
            for(int j = 0; j < i; j++)
            {
                ActivityResolveInfo activityresolveinfo = (ActivityResolveInfo)list.get(j);
                activityresolveinfo.weight = 0.0F;
                intent.put(new ComponentName(activityresolveinfo.resolveInfo.activityInfo.packageName, activityresolveinfo.resolveInfo.activityInfo.name), activityresolveinfo);
            }

            int k = list1.size();
            float f = 1.0F;
            for(k--; k >= 0;)
            {
                HistoricalRecord historicalrecord = (HistoricalRecord)list1.get(k);
                ActivityResolveInfo activityresolveinfo1 = (ActivityResolveInfo)intent.get(historicalrecord.activity);
                float f1 = f;
                if(activityresolveinfo1 != null)
                {
                    activityresolveinfo1.weight = activityresolveinfo1.weight + historicalrecord.weight * f;
                    f1 = f * 0.95F;
                }
                k--;
                f = f1;
            }

            Collections.sort(list);
        }

        private static final float WEIGHT_DECAY_COEFFICIENT = 0.95F;
        private final Map mPackageNameToActivityMap;
        final ActivityChooserModel this$0;

        private DefaultSorter()
        {
            this$0 = ActivityChooserModel.this;
            super();
            mPackageNameToActivityMap = new HashMap();
        }

        DefaultSorter(DefaultSorter defaultsorter)
        {
            this();
        }
    }

    public static final class HistoricalRecord
    {

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null)
                return false;
            if(getClass() != obj.getClass())
                return false;
            obj = (HistoricalRecord)obj;
            if(activity == null)
            {
                if(((HistoricalRecord) (obj)).activity != null)
                    return false;
            } else
            if(!activity.equals(((HistoricalRecord) (obj)).activity))
                return false;
            if(time != ((HistoricalRecord) (obj)).time)
                return false;
            return Float.floatToIntBits(weight) == Float.floatToIntBits(((HistoricalRecord) (obj)).weight);
        }

        public int hashCode()
        {
            int i;
            if(activity == null)
                i = 0;
            else
                i = activity.hashCode();
            return ((i + 31) * 31 + (int)(time ^ time >>> 32)) * 31 + Float.floatToIntBits(weight);
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("[");
            stringbuilder.append("; activity:").append(activity);
            stringbuilder.append("; time:").append(time);
            stringbuilder.append("; weight:").append(new BigDecimal(weight));
            stringbuilder.append("]");
            return stringbuilder.toString();
        }

        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(ComponentName componentname, long l, float f)
        {
            activity = componentname;
            time = l;
            weight = f;
        }

        public HistoricalRecord(String s, long l, float f)
        {
            this(ComponentName.unflattenFromString(s), l, f);
        }
    }

    public static interface OnChooseActivityListener
    {

        public abstract boolean onChooseActivity(ActivityChooserModel activitychoosermodel, Intent intent);
    }

    private final class PersistHistoryAsyncTask extends AsyncTask
    {

        public volatile Object doInBackground(Object aobj[])
        {
            return doInBackground(aobj);
        }

        public transient Void doInBackground(Object aobj[])
        {
            XmlSerializer xmlserializer;
            List list = (List)aobj[0];
            Object obj1 = (String)aobj[1];
            int i;
            int j;
            try
            {
                aobj = ActivityChooserModel._2D_get1(ActivityChooserModel.this).openFileOutput(((String) (obj1)), 0);
            }
            // Misplaced declaration of an exception variable
            catch(Object aobj[])
            {
                Log.e(ActivityChooserModel._2D_get0(), (new StringBuilder()).append("Error writing historical recrod file: ").append(((String) (obj1))).toString(), ((Throwable) (aobj)));
                return null;
            }
            xmlserializer = Xml.newSerializer();
            xmlserializer.setOutput(((java.io.OutputStream) (aobj)), null);
            xmlserializer.startDocument(StandardCharsets.UTF_8.name(), Boolean.valueOf(true));
            xmlserializer.startTag(null, "historical-records");
            i = list.size();
            j = 0;
_L2:
            if(j >= i)
                break; /* Loop/switch isn't completed */
            obj1 = (HistoricalRecord)list.remove(0);
            xmlserializer.startTag(null, "historical-record");
            xmlserializer.attribute(null, "activity", ((HistoricalRecord) (obj1)).activity.flattenToString());
            xmlserializer.attribute(null, "time", String.valueOf(((HistoricalRecord) (obj1)).time));
            xmlserializer.attribute(null, "weight", String.valueOf(((HistoricalRecord) (obj1)).weight));
            xmlserializer.endTag(null, "historical-record");
            j++;
            if(true) goto _L2; else goto _L1
_L1:
            xmlserializer.endTag(null, "historical-records");
            xmlserializer.endDocument();
            ActivityChooserModel._2D_set0(ActivityChooserModel.this, true);
            if(aobj != null)
                try
                {
                    ((FileOutputStream) (aobj)).close();
                }
                // Misplaced declaration of an exception variable
                catch(Object aobj[]) { }
_L4:
            return null;
            IOException ioexception;
            ioexception;
            String s1 = ActivityChooserModel._2D_get0();
            StringBuilder stringbuilder = JVM INSTR new #138 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.e(s1, stringbuilder.append("Error writing historical recrod file: ").append(ActivityChooserModel._2D_get2(ActivityChooserModel.this)).toString(), ioexception);
            ActivityChooserModel._2D_set0(ActivityChooserModel.this, true);
            if(aobj != null)
                try
                {
                    ((FileOutputStream) (aobj)).close();
                }
                // Misplaced declaration of an exception variable
                catch(Object aobj[]) { }
            continue; /* Loop/switch isn't completed */
            Object obj;
            obj;
            String s2 = ActivityChooserModel._2D_get0();
            StringBuilder stringbuilder1 = JVM INSTR new #138 <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            Log.e(s2, stringbuilder1.append("Error writing historical recrod file: ").append(ActivityChooserModel._2D_get2(ActivityChooserModel.this)).toString(), ((Throwable) (obj)));
            ActivityChooserModel._2D_set0(ActivityChooserModel.this, true);
            if(aobj != null)
                try
                {
                    ((FileOutputStream) (aobj)).close();
                }
                // Misplaced declaration of an exception variable
                catch(Object aobj[]) { }
            continue; /* Loop/switch isn't completed */
            IllegalArgumentException illegalargumentexception;
            illegalargumentexception;
            String s = ActivityChooserModel._2D_get0();
            StringBuilder stringbuilder2 = JVM INSTR new #138 <Class StringBuilder>;
            stringbuilder2.StringBuilder();
            Log.e(s, stringbuilder2.append("Error writing historical recrod file: ").append(ActivityChooserModel._2D_get2(ActivityChooserModel.this)).toString(), illegalargumentexception);
            ActivityChooserModel._2D_set0(ActivityChooserModel.this, true);
            if(aobj != null)
                try
                {
                    ((FileOutputStream) (aobj)).close();
                }
                // Misplaced declaration of an exception variable
                catch(Object aobj[]) { }
            if(true) goto _L4; else goto _L3
_L3:
            s;
            ActivityChooserModel._2D_set0(ActivityChooserModel.this, true);
            if(aobj != null)
                try
                {
                    ((FileOutputStream) (aobj)).close();
                }
                // Misplaced declaration of an exception variable
                catch(Object aobj[]) { }
            throw s;
        }

        final ActivityChooserModel this$0;

        private PersistHistoryAsyncTask()
        {
            this$0 = ActivityChooserModel.this;
            super();
        }

        PersistHistoryAsyncTask(PersistHistoryAsyncTask persisthistoryasynctask)
        {
            this();
        }
    }


    static String _2D_get0()
    {
        return LOG_TAG;
    }

    static Context _2D_get1(ActivityChooserModel activitychoosermodel)
    {
        return activitychoosermodel.mContext;
    }

    static String _2D_get2(ActivityChooserModel activitychoosermodel)
    {
        return activitychoosermodel.mHistoryFileName;
    }

    static boolean _2D_set0(ActivityChooserModel activitychoosermodel, boolean flag)
    {
        activitychoosermodel.mCanReadHistoricalData = flag;
        return flag;
    }

    static boolean _2D_set1(ActivityChooserModel activitychoosermodel, boolean flag)
    {
        activitychoosermodel.mReloadActivities = flag;
        return flag;
    }

    private ActivityChooserModel(Context context, String s)
    {
        mActivitySorter = new DefaultSorter(null);
        mHistoryMaxSize = 50;
        mCanReadHistoricalData = true;
        mReadShareHistoryCalled = false;
        mHistoricalRecordsChanged = true;
        mReloadActivities = false;
        mContext = context.getApplicationContext();
        if(!TextUtils.isEmpty(s) && s.endsWith(".xml") ^ true)
            mHistoryFileName = (new StringBuilder()).append(s).append(".xml").toString();
        else
            mHistoryFileName = s;
        mPackageMonitor.register(mContext, null, true);
    }

    private boolean addHisoricalRecord(HistoricalRecord historicalrecord)
    {
        boolean flag = mHistoricalRecords.add(historicalrecord);
        if(flag)
        {
            mHistoricalRecordsChanged = true;
            pruneExcessiveHistoricalRecordsIfNeeded();
            persistHistoricalDataIfNeeded();
            sortActivitiesIfNeeded();
            notifyChanged();
        }
        return flag;
    }

    private void ensureConsistentState()
    {
        boolean flag = loadActivitiesIfNeeded();
        boolean flag1 = readHistoricalDataIfNeeded();
        pruneExcessiveHistoricalRecordsIfNeeded();
        if(flag | flag1)
        {
            sortActivitiesIfNeeded();
            notifyChanged();
        }
    }

    public static ActivityChooserModel get(Context context, String s)
    {
        Object obj = sRegistryLock;
        obj;
        JVM INSTR monitorenter ;
        ActivityChooserModel activitychoosermodel = (ActivityChooserModel)sDataModelRegistry.get(s);
        ActivityChooserModel activitychoosermodel1;
        activitychoosermodel1 = activitychoosermodel;
        if(activitychoosermodel != null)
            break MISSING_BLOCK_LABEL_50;
        activitychoosermodel1 = JVM INSTR new #2   <Class ActivityChooserModel>;
        activitychoosermodel1.ActivityChooserModel(context, s);
        sDataModelRegistry.put(s, activitychoosermodel1);
        obj;
        JVM INSTR monitorexit ;
        return activitychoosermodel1;
        context;
        throw context;
    }

    private boolean loadActivitiesIfNeeded()
    {
        if(mReloadActivities && mIntent != null)
        {
            mReloadActivities = false;
            mActivities.clear();
            List list = mContext.getPackageManager().queryIntentActivities(mIntent, 0);
            int i = list.size();
            for(int j = 0; j < i; j++)
            {
                ResolveInfo resolveinfo = (ResolveInfo)list.get(j);
                ActivityInfo activityinfo = resolveinfo.activityInfo;
                if(ActivityManager.checkComponentPermission(activityinfo.permission, Process.myUid(), activityinfo.applicationInfo.uid, activityinfo.exported) == 0)
                    mActivities.add(new ActivityResolveInfo(resolveinfo));
            }

            return true;
        } else
        {
            return false;
        }
    }

    private void persistHistoricalDataIfNeeded()
    {
        if(!mReadShareHistoryCalled)
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        if(!mHistoricalRecordsChanged)
            return;
        mHistoricalRecordsChanged = false;
        if(!TextUtils.isEmpty(mHistoryFileName))
            (new PersistHistoryAsyncTask(null)).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Object[] {
                new ArrayList(mHistoricalRecords), mHistoryFileName
            });
    }

    private void pruneExcessiveHistoricalRecordsIfNeeded()
    {
        int i = mHistoricalRecords.size() - mHistoryMaxSize;
        if(i <= 0)
            return;
        mHistoricalRecordsChanged = true;
        for(int j = 0; j < i; j++)
        {
            HistoricalRecord historicalrecord = (HistoricalRecord)mHistoricalRecords.remove(0);
        }

    }

    private boolean readHistoricalDataIfNeeded()
    {
        if(mCanReadHistoricalData && mHistoricalRecordsChanged && TextUtils.isEmpty(mHistoryFileName) ^ true)
        {
            mCanReadHistoricalData = false;
            mReadShareHistoryCalled = true;
            readHistoricalDataImpl();
            return true;
        } else
        {
            return false;
        }
    }

    private void readHistoricalDataImpl()
    {
        Object obj;
        XmlPullParser xmlpullparser;
        int i;
        try
        {
            obj = mContext.openFileInput(mHistoryFileName);
        }
        // Misplaced declaration of an exception variable
        catch(Object obj)
        {
            return;
        }
        xmlpullparser = Xml.newPullParser();
        xmlpullparser.setInput(((java.io.InputStream) (obj)), StandardCharsets.UTF_8.name());
        i = 0;
_L1:
        if(i == 1 || i == 2)
            break MISSING_BLOCK_LABEL_53;
        i = xmlpullparser.next();
          goto _L1
        if(!"historical-records".equals(xmlpullparser.getName()))
        {
            XmlPullParserException xmlpullparserexception = JVM INSTR new #324 <Class XmlPullParserException>;
            xmlpullparserexception.XmlPullParserException("Share records file does not start with historical-records tag.");
            throw xmlpullparserexception;
        }
          goto _L2
        XmlPullParserException xmlpullparserexception2;
        xmlpullparserexception2;
        String s = LOG_TAG;
        StringBuilder stringbuilder1 = JVM INSTR new #177 <Class StringBuilder>;
        stringbuilder1.StringBuilder();
        Log.e(s, stringbuilder1.append("Error reading historical recrod file: ").append(mHistoryFileName).toString(), xmlpullparserexception2);
        if(obj == null)
            break MISSING_BLOCK_LABEL_134;
        ((FileInputStream) (obj)).close();
_L6:
        return;
_L2:
        Object obj2;
        obj2 = mHistoricalRecords;
        ((List) (obj2)).clear();
_L4:
        i = xmlpullparser.next();
        if(i == 1)
        {
            if(obj != null)
                try
                {
                    ((FileInputStream) (obj)).close();
                }
                // Misplaced declaration of an exception variable
                catch(Object obj) { }
            continue; /* Loop/switch isn't completed */
        }
        if(i == 3 || i == 4) goto _L4; else goto _L3
_L3:
        if(!"historical-record".equals(xmlpullparser.getName()))
        {
            XmlPullParserException xmlpullparserexception1 = JVM INSTR new #324 <Class XmlPullParserException>;
            xmlpullparserexception1.XmlPullParserException("Share records file not well-formed.");
            throw xmlpullparserexception1;
        }
        break MISSING_BLOCK_LABEL_273;
        Object obj1;
        obj1;
        obj2 = LOG_TAG;
        StringBuilder stringbuilder = JVM INSTR new #177 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.e(((String) (obj2)), stringbuilder.append("Error reading historical recrod file: ").append(mHistoryFileName).toString(), ((Throwable) (obj1)));
        if(obj != null)
            try
            {
                ((FileInputStream) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj) { }
        continue; /* Loop/switch isn't completed */
        String s1 = xmlpullparser.getAttributeValue(null, "activity");
        long l = Long.parseLong(xmlpullparser.getAttributeValue(null, "time"));
        float f = Float.parseFloat(xmlpullparser.getAttributeValue(null, "weight"));
        HistoricalRecord historicalrecord = JVM INSTR new #21  <Class ActivityChooserModel$HistoricalRecord>;
        historicalrecord.HistoricalRecord(s1, l, f);
        ((List) (obj2)).add(historicalrecord);
          goto _L4
        historicalrecord;
        IOException ioexception;
        if(obj != null)
            try
            {
                ((FileInputStream) (obj)).close();
            }
            catch(IOException ioexception1) { }
        throw historicalrecord;
        ioexception;
        if(true) goto _L6; else goto _L5
_L5:
    }

    private boolean sortActivitiesIfNeeded()
    {
        if(mActivitySorter != null && mIntent != null && mActivities.isEmpty() ^ true && mHistoricalRecords.isEmpty() ^ true)
        {
            mActivitySorter.sort(mIntent, mActivities, Collections.unmodifiableList(mHistoricalRecords));
            return true;
        } else
        {
            return false;
        }
    }

    public Intent chooseActivity(int i)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = mIntent;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_20;
        obj;
        JVM INSTR monitorexit ;
        return null;
        Object obj2;
        boolean flag;
        ensureConsistentState();
        obj2 = (ActivityResolveInfo)mActivities.get(i);
        obj1 = JVM INSTR new #417 <Class ComponentName>;
        ((ComponentName) (obj1)).ComponentName(((ActivityResolveInfo) (obj2)).resolveInfo.activityInfo.packageName, ((ActivityResolveInfo) (obj2)).resolveInfo.activityInfo.name);
        obj2 = JVM INSTR new #431 <Class Intent>;
        ((Intent) (obj2)).Intent(mIntent);
        ((Intent) (obj2)).setComponent(((ComponentName) (obj1)));
        if(mActivityChoserModelPolicy == null)
            break MISSING_BLOCK_LABEL_132;
        Intent intent = JVM INSTR new #431 <Class Intent>;
        intent.Intent(((Intent) (obj2)));
        flag = mActivityChoserModelPolicy.onChooseActivity(this, intent);
        if(!flag)
            break MISSING_BLOCK_LABEL_132;
        obj;
        JVM INSTR monitorexit ;
        return null;
        HistoricalRecord historicalrecord = JVM INSTR new #21  <Class ActivityChooserModel$HistoricalRecord>;
        historicalrecord.HistoricalRecord(((ComponentName) (obj1)), System.currentTimeMillis(), 1.0F);
        addHisoricalRecord(historicalrecord);
        obj;
        JVM INSTR monitorexit ;
        return ((Intent) (obj2));
        Exception exception;
        exception;
        throw exception;
    }

    protected void finalize()
        throws Throwable
    {
        super.finalize();
        mPackageMonitor.unregister();
    }

    public ResolveInfo getActivity(int i)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        ResolveInfo resolveinfo;
        ensureConsistentState();
        resolveinfo = ((ActivityResolveInfo)mActivities.get(i)).resolveInfo;
        obj;
        JVM INSTR monitorexit ;
        return resolveinfo;
        Exception exception;
        exception;
        throw exception;
    }

    public int getActivityCount()
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        ensureConsistentState();
        i = mActivities.size();
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getActivityIndex(ResolveInfo resolveinfo)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        List list;
        int i;
        ensureConsistentState();
        list = mActivities;
        i = list.size();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ResolveInfo resolveinfo1 = ((ActivityResolveInfo)list.get(j)).resolveInfo;
        if(resolveinfo1 != resolveinfo)
            break MISSING_BLOCK_LABEL_61;
        obj;
        JVM INSTR monitorexit ;
        return j;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return -1;
        resolveinfo;
        throw resolveinfo;
    }

    public ResolveInfo getDefaultActivity()
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        ResolveInfo resolveinfo;
        ensureConsistentState();
        if(mActivities.isEmpty())
            break MISSING_BLOCK_LABEL_44;
        resolveinfo = ((ActivityResolveInfo)mActivities.get(0)).resolveInfo;
        return resolveinfo;
        obj;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
    }

    public int getHistoryMaxSize()
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mHistoryMaxSize;
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getHistorySize()
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        ensureConsistentState();
        i = mHistoricalRecords.size();
        obj;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public Intent getIntent()
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        Intent intent = mIntent;
        obj;
        JVM INSTR monitorexit ;
        return intent;
        Exception exception;
        exception;
        throw exception;
    }

    public void setActivitySorter(ActivitySorter activitysorter)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        ActivitySorter activitysorter1 = mActivitySorter;
        if(activitysorter1 != activitysorter)
            break MISSING_BLOCK_LABEL_20;
        obj;
        JVM INSTR monitorexit ;
        return;
        mActivitySorter = activitysorter;
        if(sortActivitiesIfNeeded())
            notifyChanged();
        obj;
        JVM INSTR monitorexit ;
        return;
        activitysorter;
        throw activitysorter;
    }

    public void setDefaultActivity(int i)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        Object obj1;
        ActivityResolveInfo activityresolveinfo;
        ensureConsistentState();
        obj1 = (ActivityResolveInfo)mActivities.get(i);
        activityresolveinfo = (ActivityResolveInfo)mActivities.get(0);
        if(activityresolveinfo == null)
            break MISSING_BLOCK_LABEL_115;
        float f = (activityresolveinfo.weight - ((ActivityResolveInfo) (obj1)).weight) + 5F;
_L1:
        ComponentName componentname = JVM INSTR new #417 <Class ComponentName>;
        componentname.ComponentName(((ActivityResolveInfo) (obj1)).resolveInfo.activityInfo.packageName, ((ActivityResolveInfo) (obj1)).resolveInfo.activityInfo.name);
        obj1 = JVM INSTR new #21  <Class ActivityChooserModel$HistoricalRecord>;
        ((HistoricalRecord) (obj1)).HistoricalRecord(componentname, System.currentTimeMillis(), f);
        addHisoricalRecord(((HistoricalRecord) (obj1)));
        obj;
        JVM INSTR monitorexit ;
        return;
        f = 1.0F;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void setHistoryMaxSize(int i)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        int j = mHistoryMaxSize;
        if(j != i)
            break MISSING_BLOCK_LABEL_20;
        obj;
        JVM INSTR monitorexit ;
        return;
        mHistoryMaxSize = i;
        pruneExcessiveHistoricalRecordsIfNeeded();
        if(sortActivitiesIfNeeded())
            notifyChanged();
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setIntent(Intent intent)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        Intent intent1 = mIntent;
        if(intent1 != intent)
            break MISSING_BLOCK_LABEL_20;
        obj;
        JVM INSTR monitorexit ;
        return;
        mIntent = intent;
        mReloadActivities = true;
        ensureConsistentState();
        obj;
        JVM INSTR monitorexit ;
        return;
        intent;
        throw intent;
    }

    public void setOnChooseActivityListener(OnChooseActivityListener onchooseactivitylistener)
    {
        Object obj = mInstanceLock;
        obj;
        JVM INSTR monitorenter ;
        mActivityChoserModelPolicy = onchooseactivitylistener;
        obj;
        JVM INSTR monitorexit ;
        return;
        onchooseactivitylistener;
        throw onchooseactivitylistener;
    }

    private static final String ATTRIBUTE_ACTIVITY = "activity";
    private static final String ATTRIBUTE_TIME = "time";
    private static final String ATTRIBUTE_WEIGHT = "weight";
    private static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1F;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    private static final String LOG_TAG = android/widget/ActivityChooserModel.getSimpleName();
    private static final String TAG_HISTORICAL_RECORD = "historical-record";
    private static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private static final Map sDataModelRegistry = new HashMap();
    private static final Object sRegistryLock = new Object();
    private final List mActivities = new ArrayList();
    private OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivitySorter mActivitySorter;
    private boolean mCanReadHistoricalData;
    private final Context mContext;
    private final List mHistoricalRecords = new ArrayList();
    private boolean mHistoricalRecordsChanged;
    private final String mHistoryFileName;
    private int mHistoryMaxSize;
    private final Object mInstanceLock = new Object();
    private Intent mIntent;
    private final PackageMonitor mPackageMonitor = new DataModelPackageMonitor(null);
    private boolean mReadShareHistoryCalled;
    private boolean mReloadActivities;

}
