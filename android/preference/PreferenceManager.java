// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.app.Activity;
import android.content.*;
import android.content.pm.*;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.preference:
//            PreferenceScreen, PreferenceInflater, PreferenceFragment, PreferenceDataStore, 
//            Preference

public class PreferenceManager
{
    public static interface OnActivityDestroyListener
    {

        public abstract void onActivityDestroy();
    }

    public static interface OnActivityResultListener
    {

        public abstract boolean onActivityResult(int i, int j, Intent intent);
    }

    public static interface OnActivityStopListener
    {

        public abstract void onActivityStop();
    }

    public static interface OnPreferenceTreeClickListener
    {

        public abstract boolean onPreferenceTreeClick(PreferenceScreen preferencescreen, Preference preference);
    }


    public PreferenceManager(Activity activity, int i)
    {
        mNextId = 0L;
        mStorage = 0;
        mActivity = activity;
        mNextRequestCode = i;
        init(activity);
    }

    PreferenceManager(Context context)
    {
        mNextId = 0L;
        mStorage = 0;
        init(context);
    }

    private void dismissAllScreens()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = mPreferencesScreens;
        if(obj != null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        obj = JVM INSTR new #89  <Class ArrayList>;
        ((ArrayList) (obj)).ArrayList(mPreferencesScreens);
        mPreferencesScreens.clear();
        this;
        JVM INSTR monitorexit ;
        for(int i = ((ArrayList) (obj)).size() - 1; i >= 0; i--)
            ((DialogInterface)((ArrayList) (obj)).get(i)).dismiss();

        break MISSING_BLOCK_LABEL_72;
        Exception exception;
        exception;
        throw exception;
    }

    public static SharedPreferences getDefaultSharedPreferences(Context context)
    {
        return context.getSharedPreferences(getDefaultSharedPreferencesName(context), getDefaultSharedPreferencesMode());
    }

    private static int getDefaultSharedPreferencesMode()
    {
        return 0;
    }

    public static String getDefaultSharedPreferencesName(Context context)
    {
        return (new StringBuilder()).append(context.getPackageName()).append("_preferences").toString();
    }

    private void init(Context context)
    {
        mContext = context;
        setSharedPreferencesName(getDefaultSharedPreferencesName(context));
    }

    private List queryIntentActivities(Intent intent)
    {
        return mContext.getPackageManager().queryIntentActivities(intent, 128);
    }

    public static void setDefaultValues(Context context, int i, boolean flag)
    {
        setDefaultValues(context, getDefaultSharedPreferencesName(context), getDefaultSharedPreferencesMode(), i, flag);
    }

    public static void setDefaultValues(Context context, String s, int i, int j, boolean flag)
    {
        SharedPreferences sharedpreferences = context.getSharedPreferences("_has_set_default_values", 0);
        if(!flag && !(sharedpreferences.getBoolean("_has_set_default_values", false) ^ true))
            break MISSING_BLOCK_LABEL_82;
        PreferenceManager preferencemanager = new PreferenceManager(context);
        preferencemanager.setSharedPreferencesName(s);
        preferencemanager.setSharedPreferencesMode(i);
        preferencemanager.inflateFromResource(context, j, null);
        s = sharedpreferences.edit().putBoolean("_has_set_default_values", true);
        s.apply();
_L1:
        return;
        context;
        s.commit();
          goto _L1
    }

    private void setNoCommit(boolean flag)
    {
        if(!flag && mEditor != null)
            try
            {
                mEditor.apply();
            }
            catch(AbstractMethodError abstractmethoderror)
            {
                mEditor.commit();
            }
        mNoCommit = flag;
    }

    void addPreferencesScreen(DialogInterface dialoginterface)
    {
        this;
        JVM INSTR monitorenter ;
        if(mPreferencesScreens == null)
        {
            ArrayList arraylist = JVM INSTR new #89  <Class ArrayList>;
            arraylist.ArrayList();
            mPreferencesScreens = arraylist;
        }
        mPreferencesScreens.add(dialoginterface);
        this;
        JVM INSTR monitorexit ;
        return;
        dialoginterface;
        throw dialoginterface;
    }

    public PreferenceScreen createPreferenceScreen(Context context)
    {
        context = new PreferenceScreen(context, null);
        context.onAttachedToHierarchy(this);
        return context;
    }

    void dispatchActivityDestroy()
    {
        ArrayList arraylist = null;
        this;
        JVM INSTR monitorenter ;
        if(mActivityDestroyListeners != null)
            arraylist = new ArrayList(mActivityDestroyListeners);
        this;
        JVM INSTR monitorexit ;
        if(arraylist != null)
        {
            int i = arraylist.size();
            for(int j = 0; j < i; j++)
                ((OnActivityDestroyListener)arraylist.get(j)).onActivityDestroy();

        }
        break MISSING_BLOCK_LABEL_69;
        Exception exception;
        exception;
        throw exception;
        dismissAllScreens();
        return;
    }

    void dispatchActivityResult(int i, int j, Intent intent)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = mActivityResultListeners;
        if(obj != null)
            break MISSING_BLOCK_LABEL_16;
        this;
        JVM INSTR monitorexit ;
        return;
        obj = new ArrayList(mActivityResultListeners);
        this;
        JVM INSTR monitorexit ;
        int k;
        int l;
        k = ((List) (obj)).size();
        l = 0;
_L2:
        if(l >= k || ((OnActivityResultListener)((List) (obj)).get(l)).onActivityResult(i, j, intent))
            return;
        break MISSING_BLOCK_LABEL_79;
        intent;
        throw intent;
        l++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    void dispatchActivityStop()
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = mActivityStopListeners;
        if(obj != null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        obj = new ArrayList(mActivityStopListeners);
        this;
        JVM INSTR monitorexit ;
        int i = ((List) (obj)).size();
        for(int j = 0; j < i; j++)
            ((OnActivityStopListener)((List) (obj)).get(j)).onActivityStop();

        break MISSING_BLOCK_LABEL_68;
        Exception exception;
        exception;
        throw exception;
    }

    void dispatchNewIntent(Intent intent)
    {
        dismissAllScreens();
    }

    public Preference findPreference(CharSequence charsequence)
    {
        if(mPreferenceScreen == null)
            return null;
        else
            return mPreferenceScreen.findPreference(charsequence);
    }

    Activity getActivity()
    {
        return mActivity;
    }

    Context getContext()
    {
        return mContext;
    }

    android.content.SharedPreferences.Editor getEditor()
    {
        if(mPreferenceDataStore != null)
            return null;
        if(mNoCommit)
        {
            if(mEditor == null)
                mEditor = getSharedPreferences().edit();
            return mEditor;
        } else
        {
            return getSharedPreferences().edit();
        }
    }

    PreferenceFragment getFragment()
    {
        return mFragment;
    }

    long getNextId()
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        l = mNextId;
        mNextId = 1L + l;
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    int getNextRequestCode()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = mNextRequestCode;
        mNextRequestCode = i + 1;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    OnPreferenceTreeClickListener getOnPreferenceTreeClickListener()
    {
        return mOnPreferenceTreeClickListener;
    }

    public PreferenceDataStore getPreferenceDataStore()
    {
        return mPreferenceDataStore;
    }

    PreferenceScreen getPreferenceScreen()
    {
        return mPreferenceScreen;
    }

    public SharedPreferences getSharedPreferences()
    {
        if(mPreferenceDataStore != null)
            return null;
        if(mSharedPreferences != null) goto _L2; else goto _L1
_L1:
        mStorage;
        JVM INSTR tableswitch 1 2: default 44
    //                   1 70
    //                   2 81;
           goto _L3 _L4 _L5
_L3:
        Context context = mContext;
_L7:
        mSharedPreferences = context.getSharedPreferences(mSharedPreferencesName, mSharedPreferencesMode);
_L2:
        return mSharedPreferences;
_L4:
        context = mContext.createDeviceProtectedStorageContext();
        continue; /* Loop/switch isn't completed */
_L5:
        context = mContext.createCredentialProtectedStorageContext();
        if(true) goto _L7; else goto _L6
_L6:
    }

    public int getSharedPreferencesMode()
    {
        return mSharedPreferencesMode;
    }

    public String getSharedPreferencesName()
    {
        return mSharedPreferencesName;
    }

    PreferenceScreen inflateFromIntent(Intent intent, PreferenceScreen preferencescreen)
    {
        List list;
        HashSet hashset;
        int i;
        list = queryIntentActivities(intent);
        hashset = new HashSet();
        i = list.size() - 1;
_L2:
        Object obj;
        if(i < 0)
            break MISSING_BLOCK_LABEL_252;
        obj = ((ResolveInfo)list.get(i)).activityInfo;
        Bundle bundle = ((ActivityInfo) (obj)).metaData;
        intent = preferencescreen;
        if(bundle != null)
        {
            if(!(bundle.containsKey("android.preference") ^ true))
                break; /* Loop/switch isn't completed */
            intent = preferencescreen;
        }
_L4:
        i--;
        preferencescreen = intent;
        if(true) goto _L2; else goto _L1
_L1:
        Object obj1;
        obj1 = (new StringBuilder()).append(((ActivityInfo) (obj)).packageName).append(":").append(((ActivityInfo) (obj)).metaData.getInt("android.preference")).toString();
        intent = preferencescreen;
        if(hashset.contains(obj1)) goto _L4; else goto _L3
_L3:
        hashset.add(obj1);
        obj1 = mContext.createPackageContext(((ActivityInfo) (obj)).packageName, 0);
        intent = new PreferenceInflater(((Context) (obj1)), this);
        obj = ((ActivityInfo) (obj)).loadXmlMetaData(((Context) (obj1)).getPackageManager(), "android.preference");
        intent = (PreferenceScreen)intent.inflate(((org.xmlpull.v1.XmlPullParser) (obj)), preferencescreen, true);
        ((XmlResourceParser) (obj)).close();
          goto _L4
        intent;
        Log.w("PreferenceManager", (new StringBuilder()).append("Could not create context for ").append(((ActivityInfo) (obj)).packageName).append(": ").append(Log.getStackTraceString(intent)).toString());
        intent = preferencescreen;
          goto _L4
        preferencescreen.onAttachedToHierarchy(this);
        return preferencescreen;
    }

    public PreferenceScreen inflateFromResource(Context context, int i, PreferenceScreen preferencescreen)
    {
        setNoCommit(true);
        context = (PreferenceScreen)(new PreferenceInflater(context, this)).inflate(i, preferencescreen, true);
        context.onAttachedToHierarchy(this);
        setNoCommit(false);
        return context;
    }

    public boolean isStorageCredentialProtected()
    {
        boolean flag;
        if(mStorage == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isStorageDefault()
    {
        boolean flag = false;
        if(mStorage == 0)
            flag = true;
        return flag;
    }

    public boolean isStorageDeviceProtected()
    {
        boolean flag = true;
        if(mStorage != 1)
            flag = false;
        return flag;
    }

    void registerOnActivityDestroyListener(OnActivityDestroyListener onactivitydestroylistener)
    {
        this;
        JVM INSTR monitorenter ;
        if(mActivityDestroyListeners == null)
        {
            ArrayList arraylist = JVM INSTR new #89  <Class ArrayList>;
            arraylist.ArrayList();
            mActivityDestroyListeners = arraylist;
        }
        if(!mActivityDestroyListeners.contains(onactivitydestroylistener))
            mActivityDestroyListeners.add(onactivitydestroylistener);
        this;
        JVM INSTR monitorexit ;
        return;
        onactivitydestroylistener;
        throw onactivitydestroylistener;
    }

    void registerOnActivityResultListener(OnActivityResultListener onactivityresultlistener)
    {
        this;
        JVM INSTR monitorenter ;
        if(mActivityResultListeners == null)
        {
            ArrayList arraylist = JVM INSTR new #89  <Class ArrayList>;
            arraylist.ArrayList();
            mActivityResultListeners = arraylist;
        }
        if(!mActivityResultListeners.contains(onactivityresultlistener))
            mActivityResultListeners.add(onactivityresultlistener);
        this;
        JVM INSTR monitorexit ;
        return;
        onactivityresultlistener;
        throw onactivityresultlistener;
    }

    public void registerOnActivityStopListener(OnActivityStopListener onactivitystoplistener)
    {
        this;
        JVM INSTR monitorenter ;
        if(mActivityStopListeners == null)
        {
            ArrayList arraylist = JVM INSTR new #89  <Class ArrayList>;
            arraylist.ArrayList();
            mActivityStopListeners = arraylist;
        }
        if(!mActivityStopListeners.contains(onactivitystoplistener))
            mActivityStopListeners.add(onactivitystoplistener);
        this;
        JVM INSTR monitorexit ;
        return;
        onactivitystoplistener;
        throw onactivitystoplistener;
    }

    void removePreferencesScreen(DialogInterface dialoginterface)
    {
        this;
        JVM INSTR monitorenter ;
        List list = mPreferencesScreens;
        if(list != null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        mPreferencesScreens.remove(dialoginterface);
        this;
        JVM INSTR monitorexit ;
        return;
        dialoginterface;
        throw dialoginterface;
    }

    void setFragment(PreferenceFragment preferencefragment)
    {
        mFragment = preferencefragment;
    }

    void setOnPreferenceTreeClickListener(OnPreferenceTreeClickListener onpreferencetreeclicklistener)
    {
        mOnPreferenceTreeClickListener = onpreferencetreeclicklistener;
    }

    public void setPreferenceDataStore(PreferenceDataStore preferencedatastore)
    {
        mPreferenceDataStore = preferencedatastore;
    }

    boolean setPreferences(PreferenceScreen preferencescreen)
    {
        if(preferencescreen != mPreferenceScreen)
        {
            mPreferenceScreen = preferencescreen;
            return true;
        } else
        {
            return false;
        }
    }

    public void setSharedPreferencesMode(int i)
    {
        mSharedPreferencesMode = i;
        mSharedPreferences = null;
    }

    public void setSharedPreferencesName(String s)
    {
        mSharedPreferencesName = s;
        mSharedPreferences = null;
    }

    public void setStorageCredentialProtected()
    {
        mStorage = 2;
        mSharedPreferences = null;
    }

    public void setStorageDefault()
    {
        mStorage = 0;
        mSharedPreferences = null;
    }

    public void setStorageDeviceProtected()
    {
        mStorage = 1;
        mSharedPreferences = null;
    }

    boolean shouldCommit()
    {
        return mNoCommit ^ true;
    }

    void unregisterOnActivityDestroyListener(OnActivityDestroyListener onactivitydestroylistener)
    {
        this;
        JVM INSTR monitorenter ;
        if(mActivityDestroyListeners != null)
            mActivityDestroyListeners.remove(onactivitydestroylistener);
        this;
        JVM INSTR monitorexit ;
        return;
        onactivitydestroylistener;
        throw onactivitydestroylistener;
    }

    void unregisterOnActivityResultListener(OnActivityResultListener onactivityresultlistener)
    {
        this;
        JVM INSTR monitorenter ;
        if(mActivityResultListeners != null)
            mActivityResultListeners.remove(onactivityresultlistener);
        this;
        JVM INSTR monitorexit ;
        return;
        onactivityresultlistener;
        throw onactivityresultlistener;
    }

    public void unregisterOnActivityStopListener(OnActivityStopListener onactivitystoplistener)
    {
        this;
        JVM INSTR monitorenter ;
        if(mActivityStopListeners != null)
            mActivityStopListeners.remove(onactivitystoplistener);
        this;
        JVM INSTR monitorexit ;
        return;
        onactivitystoplistener;
        throw onactivitystoplistener;
    }

    public static final String KEY_HAS_SET_DEFAULT_VALUES = "_has_set_default_values";
    public static final String METADATA_KEY_PREFERENCES = "android.preference";
    private static final int STORAGE_CREDENTIAL_PROTECTED = 2;
    private static final int STORAGE_DEFAULT = 0;
    private static final int STORAGE_DEVICE_PROTECTED = 1;
    private static final String TAG = "PreferenceManager";
    private Activity mActivity;
    private List mActivityDestroyListeners;
    private List mActivityResultListeners;
    private List mActivityStopListeners;
    private Context mContext;
    private android.content.SharedPreferences.Editor mEditor;
    private PreferenceFragment mFragment;
    private long mNextId;
    private int mNextRequestCode;
    private boolean mNoCommit;
    private OnPreferenceTreeClickListener mOnPreferenceTreeClickListener;
    private PreferenceDataStore mPreferenceDataStore;
    private PreferenceScreen mPreferenceScreen;
    private List mPreferencesScreens;
    private SharedPreferences mSharedPreferences;
    private int mSharedPreferencesMode;
    private String mSharedPreferencesName;
    private int mStorage;
}
