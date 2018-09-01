// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.*;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.*;
import android.util.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class SearchableInfo
    implements Parcelable
{
    public static class ActionKeyInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public int getKeyCode()
        {
            return mKeyCode;
        }

        public String getQueryActionMsg()
        {
            return mQueryActionMsg;
        }

        public String getSuggestActionMsg()
        {
            return mSuggestActionMsg;
        }

        public String getSuggestActionMsgColumn()
        {
            return mSuggestActionMsgColumn;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeInt(mKeyCode);
            parcel.writeString(mQueryActionMsg);
            parcel.writeString(mSuggestActionMsg);
            parcel.writeString(mSuggestActionMsgColumn);
        }

        private final int mKeyCode;
        private final String mQueryActionMsg;
        private final String mSuggestActionMsg;
        private final String mSuggestActionMsgColumn;

        ActionKeyInfo(Context context, AttributeSet attributeset)
        {
            context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.SearchableActionKey);
            mKeyCode = context.getInt(0, 0);
            mQueryActionMsg = context.getString(1);
            mSuggestActionMsg = context.getString(2);
            mSuggestActionMsgColumn = context.getString(3);
            context.recycle();
            if(mKeyCode == 0)
                throw new IllegalArgumentException("No keycode.");
            if(mQueryActionMsg == null && mSuggestActionMsg == null && mSuggestActionMsgColumn == null)
                throw new IllegalArgumentException("No message information.");
            else
                return;
        }

        private ActionKeyInfo(Parcel parcel)
        {
            mKeyCode = parcel.readInt();
            mQueryActionMsg = parcel.readString();
            mSuggestActionMsg = parcel.readString();
            mSuggestActionMsgColumn = parcel.readString();
        }

        ActionKeyInfo(Parcel parcel, ActionKeyInfo actionkeyinfo)
        {
            this(parcel);
        }
    }


    private SearchableInfo(Context context, AttributeSet attributeset, ComponentName componentname)
    {
        mActionKeys = null;
        mSearchActivity = componentname;
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Searchable);
        mSearchMode = attributeset.getInt(3, 0);
        mLabelId = attributeset.getResourceId(0, 0);
        mHintId = attributeset.getResourceId(2, 0);
        mIconId = attributeset.getResourceId(1, 0);
        mSearchButtonText = attributeset.getResourceId(9, 0);
        mSearchInputType = attributeset.getInt(10, 1);
        mSearchImeOptions = attributeset.getInt(16, 2);
        mIncludeInGlobalSearch = attributeset.getBoolean(18, false);
        mQueryAfterZeroResults = attributeset.getBoolean(19, false);
        mAutoUrlDetect = attributeset.getBoolean(21, false);
        mSettingsDescriptionId = attributeset.getResourceId(20, 0);
        mSuggestAuthority = attributeset.getString(4);
        mSuggestPath = attributeset.getString(5);
        mSuggestSelection = attributeset.getString(6);
        mSuggestIntentAction = attributeset.getString(7);
        mSuggestIntentData = attributeset.getString(8);
        mSuggestThreshold = attributeset.getInt(17, 0);
        mVoiceSearchMode = attributeset.getInt(11, 0);
        mVoiceLanguageModeId = attributeset.getResourceId(12, 0);
        mVoicePromptTextId = attributeset.getResourceId(13, 0);
        mVoiceLanguageId = attributeset.getResourceId(14, 0);
        mVoiceMaxResults = attributeset.getInt(15, 0);
        attributeset.recycle();
        componentname = null;
        attributeset = componentname;
        if(mSuggestAuthority != null)
        {
            context = context.getPackageManager().resolveContentProvider(mSuggestAuthority, 0x10000000);
            attributeset = componentname;
            if(context != null)
                attributeset = ((ProviderInfo) (context)).packageName;
        }
        mSuggestProviderPackage = attributeset;
        if(mLabelId == 0)
            throw new IllegalArgumentException("Search label must be a resource reference.");
        else
            return;
    }

    SearchableInfo(Parcel parcel)
    {
        boolean flag = true;
        super();
        mActionKeys = null;
        mLabelId = parcel.readInt();
        mSearchActivity = ComponentName.readFromParcel(parcel);
        mHintId = parcel.readInt();
        mSearchMode = parcel.readInt();
        mIconId = parcel.readInt();
        mSearchButtonText = parcel.readInt();
        mSearchInputType = parcel.readInt();
        mSearchImeOptions = parcel.readInt();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIncludeInGlobalSearch = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mQueryAfterZeroResults = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mAutoUrlDetect = flag1;
        mSettingsDescriptionId = parcel.readInt();
        mSuggestAuthority = parcel.readString();
        mSuggestPath = parcel.readString();
        mSuggestSelection = parcel.readString();
        mSuggestIntentAction = parcel.readString();
        mSuggestIntentData = parcel.readString();
        mSuggestThreshold = parcel.readInt();
        for(int i = parcel.readInt(); i > 0; i--)
            addActionKey(new ActionKeyInfo(parcel, null));

        mSuggestProviderPackage = parcel.readString();
        mVoiceSearchMode = parcel.readInt();
        mVoiceLanguageModeId = parcel.readInt();
        mVoicePromptTextId = parcel.readInt();
        mVoiceLanguageId = parcel.readInt();
        mVoiceMaxResults = parcel.readInt();
    }

    private void addActionKey(ActionKeyInfo actionkeyinfo)
    {
        if(mActionKeys == null)
            mActionKeys = new HashMap();
        mActionKeys.put(Integer.valueOf(actionkeyinfo.getKeyCode()), actionkeyinfo);
    }

    private static Context createActivityContext(Context context, ComponentName componentname)
    {
        Object obj = null;
        try
        {
            context = context.createPackageContext(componentname.getPackageName(), 0);
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("SearchableInfo", (new StringBuilder()).append("Package not found ").append(componentname.getPackageName()).toString());
            context = obj;
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("SearchableInfo", (new StringBuilder()).append("Can't make context for ").append(componentname.getPackageName()).toString(), context);
            context = obj;
        }
        return context;
    }

    public static SearchableInfo getActivityMetaData(Context context, ActivityInfo activityinfo, int i)
    {
        Object obj;
        try
        {
            obj = JVM INSTR new #267 <Class UserHandle>;
            ((UserHandle) (obj)).UserHandle(i);
            obj = context.createPackageContextAsUser("system", 0, ((UserHandle) (obj)));
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("SearchableInfo", (new StringBuilder()).append("Couldn't create package context for user ").append(i).toString());
            return null;
        }
        context = activityinfo.loadXmlMetaData(((Context) (obj)).getPackageManager(), "android.app.searchable");
        if(context == null)
        {
            return null;
        } else
        {
            activityinfo = getActivityMetaData(((Context) (obj)), ((XmlPullParser) (context)), new ComponentName(activityinfo.packageName, activityinfo.name));
            context.close();
            return activityinfo;
        }
    }

    private static SearchableInfo getActivityMetaData(Context context, XmlPullParser xmlpullparser, ComponentName componentname)
    {
        Context context1;
        context1 = createActivityContext(context, componentname);
        if(context1 == null)
            return null;
        int i = xmlpullparser.next();
        context = null;
_L7:
        if(i == 1)
            break; /* Loop/switch isn't completed */
        if(i != 2) goto _L2; else goto _L1
_L1:
        if(!xmlpullparser.getName().equals("searchable")) goto _L4; else goto _L3
_L3:
        AttributeSet attributeset = Xml.asAttributeSet(xmlpullparser);
        if(attributeset == null) goto _L2; else goto _L5
_L5:
        context = JVM INSTR new #2   <Class SearchableInfo>;
        context.SearchableInfo(context1, attributeset, componentname);
_L2:
        i = xmlpullparser.next();
        if(true) goto _L7; else goto _L6
        context;
        xmlpullparser = JVM INSTR new #242 <Class StringBuilder>;
        xmlpullparser.StringBuilder();
        Log.w("SearchableInfo", xmlpullparser.append("Invalid searchable metadata for ").append(componentname.flattenToShortString()).append(": ").append(context.getMessage()).toString());
        return null;
_L4:
        if(!xmlpullparser.getName().equals("actionkey")) goto _L2; else goto _L8
_L8:
        if(context == null)
            return null;
        AttributeSet attributeset1 = Xml.asAttributeSet(xmlpullparser);
        if(attributeset1 == null) goto _L2; else goto _L9
_L9:
        ActionKeyInfo actionkeyinfo = JVM INSTR new #10  <Class SearchableInfo$ActionKeyInfo>;
        actionkeyinfo.ActionKeyInfo(context1, attributeset1);
        context.addActionKey(actionkeyinfo);
          goto _L2
        xmlpullparser;
        context = JVM INSTR new #242 <Class StringBuilder>;
        context.StringBuilder();
        Log.w("SearchableInfo", context.append("Invalid action key for ").append(componentname.flattenToShortString()).append(": ").append(xmlpullparser.getMessage()).toString());
        return null;
        context;
_L13:
        Log.w("SearchableInfo", (new StringBuilder()).append("Reading searchable metadata for ").append(componentname.flattenToShortString()).toString(), context);
        return null;
        context;
_L11:
        Log.w("SearchableInfo", (new StringBuilder()).append("Reading searchable metadata for ").append(componentname.flattenToShortString()).toString(), context);
        return null;
_L6:
        return context;
        context;
        if(true) goto _L11; else goto _L10
_L10:
        context;
        if(true) goto _L13; else goto _L12
_L12:
    }

    public boolean autoUrlDetect()
    {
        return mAutoUrlDetect;
    }

    public int describeContents()
    {
        return 0;
    }

    public ActionKeyInfo findActionKey(int i)
    {
        if(mActionKeys == null)
            return null;
        else
            return (ActionKeyInfo)mActionKeys.get(Integer.valueOf(i));
    }

    public Context getActivityContext(Context context)
    {
        return createActivityContext(context, mSearchActivity);
    }

    public int getHintId()
    {
        return mHintId;
    }

    public int getIconId()
    {
        return mIconId;
    }

    public int getImeOptions()
    {
        return mSearchImeOptions;
    }

    public int getInputType()
    {
        return mSearchInputType;
    }

    public int getLabelId()
    {
        return mLabelId;
    }

    public Context getProviderContext(Context context, Context context1)
    {
        Object obj = null;
        if(mSearchActivity.getPackageName().equals(mSuggestProviderPackage))
            return context1;
        context1 = obj;
        if(mSuggestProviderPackage != null)
            try
            {
                context1 = context.createPackageContext(mSuggestProviderPackage, 0);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                context1 = obj;
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                context1 = obj;
            }
        return context1;
    }

    public ComponentName getSearchActivity()
    {
        return mSearchActivity;
    }

    public int getSearchButtonText()
    {
        return mSearchButtonText;
    }

    public int getSettingsDescriptionId()
    {
        return mSettingsDescriptionId;
    }

    public String getSuggestAuthority()
    {
        return mSuggestAuthority;
    }

    public String getSuggestIntentAction()
    {
        return mSuggestIntentAction;
    }

    public String getSuggestIntentData()
    {
        return mSuggestIntentData;
    }

    public String getSuggestPackage()
    {
        return mSuggestProviderPackage;
    }

    public String getSuggestPath()
    {
        return mSuggestPath;
    }

    public String getSuggestSelection()
    {
        return mSuggestSelection;
    }

    public int getSuggestThreshold()
    {
        return mSuggestThreshold;
    }

    public int getVoiceLanguageId()
    {
        return mVoiceLanguageId;
    }

    public int getVoiceLanguageModeId()
    {
        return mVoiceLanguageModeId;
    }

    public int getVoiceMaxResults()
    {
        return mVoiceMaxResults;
    }

    public int getVoicePromptTextId()
    {
        return mVoicePromptTextId;
    }

    public boolean getVoiceSearchEnabled()
    {
        boolean flag = false;
        if((mVoiceSearchMode & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean getVoiceSearchLaunchRecognizer()
    {
        boolean flag = false;
        if((mVoiceSearchMode & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean getVoiceSearchLaunchWebSearch()
    {
        boolean flag = false;
        if((mVoiceSearchMode & 2) != 0)
            flag = true;
        return flag;
    }

    public boolean queryAfterZeroResults()
    {
        return mQueryAfterZeroResults;
    }

    public boolean shouldIncludeInGlobalSearch()
    {
        return mIncludeInGlobalSearch;
    }

    public boolean shouldRewriteQueryFromData()
    {
        boolean flag = false;
        if((mSearchMode & 0x10) != 0)
            flag = true;
        return flag;
    }

    public boolean shouldRewriteQueryFromText()
    {
        boolean flag = false;
        if((mSearchMode & 0x20) != 0)
            flag = true;
        return flag;
    }

    public boolean useBadgeIcon()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if((mSearchMode & 8) != 0)
        {
            flag1 = flag;
            if(mIconId != 0)
                flag1 = true;
        }
        return flag1;
    }

    public boolean useBadgeLabel()
    {
        boolean flag = false;
        if((mSearchMode & 4) != 0)
            flag = true;
        return flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mLabelId);
        mSearchActivity.writeToParcel(parcel, i);
        parcel.writeInt(mHintId);
        parcel.writeInt(mSearchMode);
        parcel.writeInt(mIconId);
        parcel.writeInt(mSearchButtonText);
        parcel.writeInt(mSearchInputType);
        parcel.writeInt(mSearchImeOptions);
        int j;
        if(mIncludeInGlobalSearch)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(mQueryAfterZeroResults)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(mAutoUrlDetect)
            j = ((flag) ? 1 : 0);
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(mSettingsDescriptionId);
        parcel.writeString(mSuggestAuthority);
        parcel.writeString(mSuggestPath);
        parcel.writeString(mSuggestSelection);
        parcel.writeString(mSuggestIntentAction);
        parcel.writeString(mSuggestIntentData);
        parcel.writeInt(mSuggestThreshold);
        if(mActionKeys == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(mActionKeys.size());
            Iterator iterator = mActionKeys.values().iterator();
            while(iterator.hasNext()) 
                ((ActionKeyInfo)iterator.next()).writeToParcel(parcel, i);
        }
        parcel.writeString(mSuggestProviderPackage);
        parcel.writeInt(mVoiceSearchMode);
        parcel.writeInt(mVoiceLanguageModeId);
        parcel.writeInt(mVoicePromptTextId);
        parcel.writeInt(mVoiceLanguageId);
        parcel.writeInt(mVoiceMaxResults);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SearchableInfo createFromParcel(Parcel parcel)
        {
            return new SearchableInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SearchableInfo[] newArray(int i)
        {
            return new SearchableInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final boolean DBG = false;
    private static final String LOG_TAG = "SearchableInfo";
    private static final String MD_LABEL_SEARCHABLE = "android.app.searchable";
    private static final String MD_XML_ELEMENT_SEARCHABLE = "searchable";
    private static final String MD_XML_ELEMENT_SEARCHABLE_ACTION_KEY = "actionkey";
    private static final int SEARCH_MODE_BADGE_ICON = 8;
    private static final int SEARCH_MODE_BADGE_LABEL = 4;
    private static final int SEARCH_MODE_QUERY_REWRITE_FROM_DATA = 16;
    private static final int SEARCH_MODE_QUERY_REWRITE_FROM_TEXT = 32;
    private static final int VOICE_SEARCH_LAUNCH_RECOGNIZER = 4;
    private static final int VOICE_SEARCH_LAUNCH_WEB_SEARCH = 2;
    private static final int VOICE_SEARCH_SHOW_BUTTON = 1;
    private HashMap mActionKeys;
    private final boolean mAutoUrlDetect;
    private final int mHintId;
    private final int mIconId;
    private final boolean mIncludeInGlobalSearch;
    private final int mLabelId;
    private final boolean mQueryAfterZeroResults;
    private final ComponentName mSearchActivity;
    private final int mSearchButtonText;
    private final int mSearchImeOptions;
    private final int mSearchInputType;
    private final int mSearchMode;
    private final int mSettingsDescriptionId;
    private final String mSuggestAuthority;
    private final String mSuggestIntentAction;
    private final String mSuggestIntentData;
    private final String mSuggestPath;
    private final String mSuggestProviderPackage;
    private final String mSuggestSelection;
    private final int mSuggestThreshold;
    private final int mVoiceLanguageId;
    private final int mVoiceLanguageModeId;
    private final int mVoiceMaxResults;
    private final int mVoicePromptTextId;
    private final int mVoiceSearchMode;

}
