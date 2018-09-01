// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.miui.internal.search;

import android.content.*;
import android.database.MatrixCursor;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.util.*;
import org.json.*;

// Referenced classes of package com.miui.internal.search:
//            IndexTree, SearchUtils, TinyIntent, Function, 
//            RankedResult, BackgroundCheckable

public class SettingsTree extends IndexTree
{

    protected SettingsTree(Context context, JSONObject jsonobject, SettingsTree settingstree, boolean flag)
        throws JSONException
    {
        super(settingstree);
        mContext = SearchUtils.getPackageContext(context, jsonobject.optString("package", getPackage()));
        mResource = jsonobject.optString("resource");
        mCategory = jsonobject.optString("category");
        mTitle = null;
        mKeywords = jsonobject.optString("keywords");
        mSummary = jsonobject.optString("summary");
        mIcon = jsonobject.optString("icon");
        mFragment = jsonobject.optString("fragment");
        try
        {
            settingstree = JVM INSTR new #103 <Class TinyIntent>;
            settingstree.TinyIntent(jsonobject.getJSONObject("intent"));
            mIntent = settingstree.toIntent();
        }
        // Misplaced declaration of an exception variable
        catch(SettingsTree settingstree)
        {
            mIntent = null;
        }
        mFeature = jsonobject.optString("feature");
        mIsSecondSpace = jsonobject.optBoolean("second_space", true);
        mIsCheckBox = jsonobject.optBoolean("is_checkbox", false);
        mIsOldman = jsonobject.optBoolean("is_oldman", true);
        settingstree = jsonobject.optJSONArray("son");
        if(settingstree != null && SearchUtils.removeViaSecondSpace(mIsSecondSpace) ^ true && SearchUtils.removeViaFeature(mFeature) ^ true)
        {
            for(int i = 0; i < settingstree.length(); i++)
                addSon(newInstance(context, settingstree.getJSONObject(i), this, flag));

        }
        mStatus = jsonobject.optInt("status", 3);
        mTmp = jsonobject.optBoolean("temporary", false);
        mLocale = null;
    }

    private boolean dispatchInitialize()
    {
        boolean flag;
        if(!SearchUtils.removeViaSecondSpace(mIsSecondSpace) && !SearchUtils.removeViaFeature(mFeature))
            flag = initialize();
        else
            flag = true;
        return flag;
    }

    private double doMatch(String s)
    {
        double d = 0.0D;
        String s1 = getTitle(true).toLowerCase().replace(" ", "");
        s = s.replace(" ", "");
        int i = s.length();
        s = s.toLowerCase();
        if(s1.contains(s))
        {
            double d1 = Math.sqrt(i) / Math.sqrt(s1.length());
            if(s1.startsWith(s))
                d = 2D;
            else
                d = 1.0D;
            return d + d1;
        }
        int j = 0;
label0:
        do
        {
label1:
            {
                if(j < i)
                {
                    char c = s.charAt(j);
                    if(c < 'a' || c > 'z')
                        break label1;
                }
                if(j == i)
                    return 0.0D;
                break label0;
            }
            j++;
        } while(true);
        String s2 = getPinyin();
        if(TextUtils.isEmpty(s2))
            return 0.0D;
        Object obj = new StringBuilder();
        ((StringBuilder) (obj)).append(s2.charAt(0));
        j = 1;
        while(j < s2.length()) 
        {
            if(TextUtils.isEmpty(s))
                return Math.sqrt(d) / Math.sqrt(s1.length());
            char c1 = s2.charAt(j);
            if(c1 >= 'a' && c1 <= 'z')
            {
                ((StringBuilder) (obj)).append(c1);
            } else
            {
                obj = ((StringBuilder) (obj)).toString();
                if(s.contains(((String) (obj)).toLowerCase()))
                {
                    s = s.substring(s.indexOf(((String) (obj)).toLowerCase()) + ((String) (obj)).length());
                    d++;
                } else
                {
                    if(((String) (obj)).toLowerCase().contains(s))
                        return Math.sqrt(d + 1.0D) / Math.sqrt(s1.length());
                    if(s.contains(((String) (obj)).substring(0, 1).toLowerCase()))
                    {
                        s = s.substring(s.indexOf(((String) (obj)).substring(0, 1).toLowerCase()) + 1);
                        d += 0.5D;
                    } else
                    {
                        return Math.sqrt(d) / Math.sqrt(s1.length());
                    }
                }
                obj = new StringBuilder();
                ((StringBuilder) (obj)).append(c1);
            }
            j++;
        }
        obj = ((StringBuilder) (obj)).toString();
        if(s.contains(((String) (obj)).toLowerCase()))
            d++;
        else
        if(s.contains(((String) (obj)).substring(0, 1).toLowerCase()))
            d += 0.5D;
        else
            return 0.0D;
        return Math.sqrt(d) / Math.sqrt(s1.length());
    }

    private boolean isSelected(String s, String as[])
    {
        if(s == null || as == null)
            return true;
        String as1[] = s.split(",");
        int i = as1.length;
        int j = 0;
        int k = 0;
        for(; j < i; j++)
        {
            s = as1[j].split("=");
            String s1 = s[0].trim();
            s = s[1].trim();
            if(s.equals("?"))
            {
                int l = k + 1;
                s = as[k];
                k = l;
            }
            String as2[] = Function.FUNCTIONS;
            int i1 = 0;
            for(int j1 = as2.length; i1 < j1; i1++)
            {
                String s2 = as2[i1];
                if(!s1.contains(s2))
                    continue;
                s2 = getColumnValue(s2);
                if(s2 == null || s.equals(s2) ^ true)
                    return false;
            }

        }

        return true;
    }

    private double match(String s)
    {
        double d = doMatch(s);
        if(Log.isLoggable("SettingsTree", 2))
            Log.v("SettingsTree", (new StringBuilder()).append(getTitle(true)).append(": ").append(d).toString());
        return d;
    }

    protected static SettingsTree newInstance(Context context, JSONObject jsonobject, SettingsTree settingstree)
        throws JSONException
    {
        return newInstance(context, jsonobject, settingstree, true);
    }

    protected static SettingsTree newInstance(Context context, JSONObject jsonobject, SettingsTree settingstree, boolean flag)
        throws JSONException
    {
        Object obj;
        String s;
        Object obj1;
        obj = null;
        s = jsonobject.optString("class");
        String s1 = jsonobject.optString("package");
        obj1 = s;
        if(TextUtils.isEmpty(s))
            if(settingstree != null && settingstree.getClass().equals(com/miui/internal/search/SettingsTree) ^ true)
            {
                obj1 = settingstree.getClass().getName();
            } else
            {
                if(TextUtils.isEmpty(s1) || TextUtils.equals(s1, "com.android.settings"))
                {
                    jsonobject = new SettingsTree(context, jsonobject, settingstree, flag);
                    context = jsonobject;
                    if(flag)
                    {
                        context = jsonobject;
                        if(jsonobject.dispatchInitialize())
                            context = null;
                    }
                    return context;
                }
                obj1 = (new StringBuilder()).append(s1).append(".search.CustomSettingsTree").toString();
            }
        s = s1;
        if(TextUtils.isEmpty(s1))
            s = ((String) (obj1));
        if(!SearchUtils.isPackageExisted(context, s))
        {
            Log.w("SettingsTree", (new StringBuilder()).append("package ").append(s).append(" doesn't exist any more").toString());
            return null;
        }
        if(((String) (obj1)).contains("com.android.settings"))
            s = "com.android.settings";
        try
        {
            obj1 = Class.forName(((String) (obj1)), true, SearchUtils.getPackageContext(context, s).getClassLoader());
            if(!com/miui/internal/search/SettingsTree.isAssignableFrom(((Class) (obj1))))
            {
                context = JVM INSTR new #361 <Class ClassCastException>;
                jsonobject = JVM INSTR new #238 <Class StringBuilder>;
                jsonobject.StringBuilder();
                context.ClassCastException(jsonobject.append("cannot cast ").append(((Class) (obj1)).getName()).append(" to ").append(com/miui/internal/search/SettingsTree.getName()).toString());
                throw context;
            }
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("SettingsTree", (new StringBuilder()).append("drop subtree under ").append(settingstree.getTitle(false)).toString(), context);
            return null;
        }
        obj1 = ((Class) (obj1)).getDeclaredConstructor(new Class[] {
            android/content/Context, org/json/JSONObject, com/miui/internal/search/SettingsTree, Boolean.TYPE
        });
        ((Constructor) (obj1)).setAccessible(true);
        context = (SettingsTree)((Constructor) (obj1)).newInstance(new Object[] {
            context, jsonobject, settingstree, Boolean.valueOf(flag)
        });
        if(!flag)
            break MISSING_BLOCK_LABEL_406;
        flag = context.dispatchInitialize();
        if(flag)
            context = obj;
        return context;
    }

    public static SettingsTree newInstance(Context context, JSONObject jsonobject, boolean flag)
        throws JSONException
    {
        return newInstance(context, jsonobject, null, flag);
    }

    private final void setIntent(Intent intent)
    {
        mIntent = intent;
    }

    private void updateCategoryRelated()
    {
        if(!TextUtils.isEmpty(mCategory))
            mCategoryString = SearchUtils.getString(getPackageContext(mContext), mCategory);
    }

    private void updateLocale()
    {
        if(Objects.equals(Locale.getDefault(), mLocale))
        {
            return;
        } else
        {
            updateResourceRelated();
            updateCategoryRelated();
            mLocale = Locale.getDefault();
            return;
        }
    }

    private void updateResourceRelated()
    {
        if(!TextUtils.isEmpty(mResource))
        {
            mTitle = SearchUtils.getString(getPackageContext(mContext), mResource);
            mResourceKeywords = SearchUtils.getString(getPackageContext(mContext), (new StringBuilder()).append("search_").append(mResource).toString());
        }
    }

    public void commit(StringBuilder stringbuilder)
    {
        if(getParent() == null)
        {
            Object obj = toJSONObject();
            if(obj == null)
                obj = "";
            else
                obj = ((JSONObject) (obj)).toString();
            stringbuilder.append(((String) (obj)));
        }
    }

    public final boolean delete(String s, String as[])
    {
        return isSelected(s, as);
    }

    public final void dispatchOnReceive(Context context, Intent intent)
    {
        onReceive(context, intent);
        if(getSons() != null)
        {
            for(Iterator iterator = getSons().iterator(); iterator.hasNext(); ((SettingsTree)iterator.next()).dispatchOnReceive(context, intent));
        }
    }

    protected String getCategory(boolean flag)
    {
        if(flag)
            updateLocale();
        String s;
        if(flag)
        {
            if(TextUtils.isEmpty(mCategoryString))
                s = getTitle(true);
            else
                s = mCategoryString;
        } else
        {
            s = mCategory.toLowerCase();
        }
        return s;
    }

    public BackgroundCheckable getCheckable()
    {
        return null;
    }

    public String getColumnValue(String s)
    {
        if(s.equals("package"))
            return getPackage();
        if(s.equals("class"))
            return getClass().getName();
        if(s.equals("resource"))
            return mResource;
        if(s.equals("title"))
            return getTitle(true);
        if(s.equals("category"))
            return getCategory(true);
        if(s.equals("category_origin"))
            return mCategory;
        if(s.equals("path"))
            return getPath(true, true);
        if(s.equals("keywords"))
            return mKeywords;
        if(s.equals("summary"))
            return mSummary;
        if(s.equals("icon"))
            return getIcon();
        if(s.equals("fragment"))
            return mFragment;
        if(s.equals("intent"))
            throw new IllegalArgumentException("Use getIntent() instead");
        while(s.equals("feature") || s.equals("second_space")) 
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" was removed once initialized").toString());
        if(s.equals("is_checkbox"))
            return Boolean.toString(mIsCheckBox);
        if(s.equals("is_oldman"))
            return Boolean.toString(mIsOldman);
        if(s.equals("status"))
            return Integer.toString(getStatus());
        if(s.equals("temporary"))
            return Boolean.toString(mTmp);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown column: ").append(s).toString());
    }

    public Object[] getColumnValues(String as[])
    {
        Object aobj[] = new Object[as.length];
        int i = 0;
        while(i < as.length) 
        {
            if(TextUtils.equals(as[i], "intent"))
                try
                {
                    aobj[i] = getIntent();
                }
                catch(Exception exception)
                {
                    Log.e("SettingsTree", (new StringBuilder()).append("exception occurs when visit: ").append(getTitle(true)).toString(), exception);
                    aobj[i] = null;
                }
            else
                aobj[i] = getColumnValue(as[i]);
            i++;
        }
        return aobj;
    }

    public String getIcon()
    {
        if(TextUtils.isEmpty(mIcon))
        {
            String s;
            if(getParent() == null)
                s = "";
            else
                s = getParent().getIcon();
            return s;
        } else
        {
            return mIcon;
        }
    }

    public Intent getIntent()
    {
        Intent intent = null;
        int i = getStatus();
        if((i & 1) == 0)
            return null;
        if((i & 2) == 0)
        {
            if(getParent() != null)
                intent = getParent().getIntent();
            return intent;
        }
        if(mIntent == null) goto _L2; else goto _L1
_L1:
        Intent intent1;
        intent1 = new Intent(mIntent);
        intent1.putExtra(":android:show_fragment_title", getTitle(true));
_L4:
        return intent1;
_L2:
        if(TextUtils.isEmpty(mFragment))
            break; /* Loop/switch isn't completed */
        intent1 = new Intent();
        intent1.setAction("android.intent.action.MAIN");
        intent1.setClassName("com.android.settings", "com.android.settings.SubSettings");
        intent1.putExtra(":settings:show_fragment", mFragment);
        intent1.putExtra(":android:no_headers", true);
        intent1.putExtra(":settings:show_fragment_title", getTitle(true));
        if(true) goto _L4; else goto _L3
_L3:
        if(getParent() != null)
            return getParent().getIntent();
        else
            return null;
    }

    public String getPackage()
    {
        return "com.android.settings";
    }

    protected final Context getPackageContext(Context context)
    {
        return SearchUtils.getPackageContext(context, getPackage());
    }

    public volatile IndexTree getParent()
    {
        return getParent();
    }

    public SettingsTree getParent()
    {
        return (SettingsTree)super.getParent();
    }

    protected String getPath(boolean flag, boolean flag1)
    {
        String s;
        Object obj;
        if(getParent() == null)
            s = "";
        else
            s = getParent().getPath(flag, false);
        obj = s;
        if(!TextUtils.isEmpty(s))
            obj = (new StringBuilder()).append(s).append("/").toString();
        obj = (new StringBuilder()).append(((String) (obj)));
        if(flag1)
            s = getCategory(flag);
        else
            s = getTitle(flag);
        return ((StringBuilder) (obj)).append(s).toString();
    }

    protected String getPinyin()
    {
        String s = null;
        if(!"zh".equals(Locale.getDefault().getLanguage()))
            return null;
        String s1 = getTitle(true);
        if(!s1.equals(mResource.toLowerCase()))
            s = SearchUtils.getPinyin(s1);
        return s;
    }

    public LinkedList getSons()
    {
        return super.getSons();
    }

    protected int getStatus()
    {
        return mStatus;
    }

    protected String getTitle(boolean flag)
    {
        if(flag)
            updateLocale();
        String s;
        if(flag && TextUtils.isEmpty(mTitle) ^ true)
            s = mTitle;
        else
            s = mResource.toLowerCase();
        return s;
    }

    protected final String getUri()
    {
        return (new StringBuilder()).append("content://com.miui.settings/").append(getPath(false, false)).toString();
    }

    public boolean initialize()
    {
        return false;
    }

    public final IndexTree insert(ContentValues contentvalues)
    {
        if(!TextUtils.equals(contentvalues.getAsString("parent"), mResource))
            return null;
        String as[] = new String[contentvalues.keySet().size()];
        contentvalues.keySet().toArray(as);
        JSONObject jsonobject;
        int i;
        int j;
        String s;
        try
        {
            jsonobject = JVM INSTR new #60  <Class JSONObject>;
            jsonobject.JSONObject();
        }
        // Misplaced declaration of an exception variable
        catch(ContentValues contentvalues)
        {
            return null;
        }
        i = 0;
        j = as.length;
_L2:
        if(i >= j)
            break; /* Loop/switch isn't completed */
        s = as[i];
        jsonobject.put(s, contentvalues.get(s));
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        contentvalues = newInstance(mContext, jsonobject, this);
        return contentvalues;
    }

    public void onReceive(Context context, Intent intent)
    {
    }

    public final boolean query(MatrixCursor matrixcursor, String s, String s1, String as[], String s2)
    {
        boolean flag = true;
        if(SearchUtils.isOldmanMode() && mIsOldman ^ true)
            return false;
        boolean flag1 = false;
        int i;
        try
        {
            i = getStatus();
        }
        // Misplaced declaration of an exception variable
        catch(String s2)
        {
            Log.e("SettingsTree", (new StringBuilder()).append("hide because exception occurs when query: ").append(getTitle(true)).toString(), s2);
            i = ((flag1) ? 1 : 0);
        }
        if((i & 1) == 0)
            return false;
        s2 = matrixcursor.getColumnNames();
        if(isSelected(s1, as))
        {
            double d = match(s);
            if((i & 4) == 0 && d > 0.0D)
                ((RankedResult)matrixcursor).addRow(getColumnValues(s2), d);
        }
        if((i & 2) == 0)
            flag = false;
        return flag;
    }

    public void setColumnValue(String s, String s1)
    {
        if(!s.equals("resource")) goto _L2; else goto _L1
_L1:
        mResource = s1;
        updateResourceRelated();
_L6:
        return;
_L2:
        if(s.equals("category"))
        {
            mCategory = s1;
            updateCategoryRelated();
            continue; /* Loop/switch isn't completed */
        }
        while(s.equals("title") || s.equals("path")) 
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" should not be modified, you may modify ").append(s).append(" via resource").toString());
        if(s.equals("keywords"))
        {
            mKeywords = s1;
            continue; /* Loop/switch isn't completed */
        }
        if(s.equals("summary"))
        {
            mSummary = s1;
            continue; /* Loop/switch isn't completed */
        }
        if(s.equals("icon"))
        {
            mIcon = s1;
            continue; /* Loop/switch isn't completed */
        }
        if(s.equals("fragment"))
        {
            mFragment = s1;
            continue; /* Loop/switch isn't completed */
        }
        if(s.equals("intent"))
            throw new IllegalArgumentException("Use setIntent() instead");
        while(s.equals("feature") || s.equals("second_space")) 
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" was removed once initialized").toString());
        if(s.equals("is_checkbox"))
        {
            mIsCheckBox = Boolean.parseBoolean(s1);
            continue; /* Loop/switch isn't completed */
        }
        if(!s.equals("is_oldman")) goto _L4; else goto _L3
_L3:
        mIsOldman = Boolean.parseBoolean(s1);
        if(true) goto _L6; else goto _L5
_L4:
        while(s.equals("package") || s.equals("class") || s.equals("status") || s.equals("temporary")) 
_L5:
            throw new IllegalArgumentException((new StringBuilder()).append(s).append(" cannot be modified").toString());
        if(true) goto _L6; else goto _L5
    }

    public void setColumnValues(String as[], Object aobj[])
    {
        int i = 0;
        while(i < as.length) 
        {
            if(TextUtils.equals(as[i], "intent"))
            {
                if(aobj[i] instanceof Intent)
                    setIntent((Intent)aobj[i]);
            } else
            {
                setColumnValue(as[i], (String)aobj[i]);
            }
            i++;
        }
    }

    public final JSONObject toJSONObject()
    {
        Object obj;
        if(mTmp)
            return null;
        obj = new JSONObject();
        Iterator iterator;
        JSONArray jsonarray;
        if(!getPackage().equals("com.android.settings"))
            ((JSONObject) (obj)).put("package", getPackage());
        if(!getClass().equals(com/miui/internal/search/SettingsTree))
            ((JSONObject) (obj)).put("class", getClass().getName());
        if(!TextUtils.isEmpty(mResource))
            ((JSONObject) (obj)).put("resource", mResource);
        if(!TextUtils.isEmpty(mCategory))
            ((JSONObject) (obj)).put("category", mCategory);
        if(!TextUtils.isEmpty(mKeywords))
            ((JSONObject) (obj)).put("keywords", mKeywords);
        if(!TextUtils.isEmpty(mSummary))
            ((JSONObject) (obj)).put("summary", mSummary);
        if(!TextUtils.isEmpty(mIcon))
            ((JSONObject) (obj)).put("icon", mIcon);
        if(!TextUtils.isEmpty(mFragment))
            ((JSONObject) (obj)).put("fragment", mFragment);
        if(mIntent != null)
        {
            TinyIntent tinyintent = JVM INSTR new #103 <Class TinyIntent>;
            tinyintent.TinyIntent(mIntent);
            ((JSONObject) (obj)).put("intent", tinyintent.toJSONObject());
        }
        if(mIsCheckBox)
            ((JSONObject) (obj)).put("is_checkbox", true);
        if(!mIsOldman)
            ((JSONObject) (obj)).put("is_oldman", false);
        if(getSons() == null)
            break MISSING_BLOCK_LABEL_334;
        jsonarray = JVM INSTR new #154 <Class JSONArray>;
        jsonarray.JSONArray();
        iterator = getSons().iterator();
_L1:
        JSONObject jsonobject;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_326;
        jsonobject = ((SettingsTree)iterator.next()).toJSONObject();
        if(jsonobject != null)
            try
            {
                jsonarray.put(jsonobject);
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                throw new RuntimeException(((Throwable) (obj)));
            }
          goto _L1
        ((JSONObject) (obj)).put("son", jsonarray);
        return ((JSONObject) (obj));
    }

    public final boolean update(ContentValues contentvalues, String s, String as[])
    {
        if(!isSelected(s, as))
            return false;
        s = new String[contentvalues.keySet().size()];
        contentvalues.keySet().toArray(s);
        as = new String[contentvalues.size()];
        for(int i = 0; i < contentvalues.size(); i++)
            as[i] = contentvalues.getAsString(s[i]);

        setColumnValues(s, as);
        return true;
    }

    public static final int DISABLED = 1;
    public static final int ENABLED = 3;
    public static final int FLAG_AVAILABLE = 2;
    public static final int FLAG_IGNORED = 4;
    public static final int FLAG_VISIBLE = 1;
    public static final int INVISIBLE = 0;
    public static final String SETTINGS_PACKAGE = "com.android.settings";
    private static final String TAG = "SettingsTree";
    private String mCategory;
    private String mCategoryString;
    protected final Context mContext;
    private String mFeature;
    private String mFragment;
    private String mIcon;
    private Intent mIntent;
    private boolean mIsCheckBox;
    private boolean mIsOldman;
    private boolean mIsSecondSpace;
    private String mKeywords;
    private Locale mLocale;
    private String mResource;
    private String mResourceKeywords;
    private int mStatus;
    private String mSummary;
    private String mTitle;
    private boolean mTmp;
}
