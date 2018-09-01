// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.*;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;

// Referenced classes of package android.app:
//            SearchDialog, ActivityManager, IActivityManager, ISearchManager, 
//            SearchableInfo, UiModeManager

public class SearchManager
    implements android.content.DialogInterface.OnDismissListener, android.content.DialogInterface.OnCancelListener
{
    public static interface OnCancelListener
    {

        public abstract void onCancel();
    }

    public static interface OnDismissListener
    {

        public abstract void onDismiss();
    }


    SearchManager(Context context, Handler handler)
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        mDismissListener = null;
        mCancelListener = null;
        mContext = context;
        mHandler = handler;
    }

    private void ensureSearchDialog()
    {
        if(mSearchDialog == null)
        {
            mSearchDialog = new SearchDialog(mContext, this);
            mSearchDialog.setOnCancelListener(this);
            mSearchDialog.setOnDismissListener(this);
        }
    }

    public Intent getAssistIntent(boolean flag)
    {
        Intent intent;
        Bundle bundle;
        try
        {
            intent = JVM INSTR new #252 <Class Intent>;
            intent.Intent("android.intent.action.ASSIST");
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(!flag)
            break MISSING_BLOCK_LABEL_34;
        bundle = ActivityManager.getService().getAssistContextExtras(0);
        if(bundle == null)
            break MISSING_BLOCK_LABEL_34;
        intent.replaceExtras(bundle);
        return intent;
    }

    public List getGlobalSearchActivities()
    {
        List list;
        try
        {
            list = mService.getGlobalSearchActivities();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public ComponentName getGlobalSearchActivity()
    {
        ComponentName componentname;
        try
        {
            componentname = mService.getGlobalSearchActivity();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return componentname;
    }

    public SearchableInfo getSearchableInfo(ComponentName componentname)
    {
        try
        {
            componentname = mService.getSearchableInfo(componentname);
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
        return componentname;
    }

    public List getSearchablesInGlobalSearch()
    {
        List list;
        try
        {
            list = mService.getSearchablesInGlobalSearch();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public Cursor getSuggestions(SearchableInfo searchableinfo, String s)
    {
        return getSuggestions(searchableinfo, s, -1);
    }

    public Cursor getSuggestions(SearchableInfo searchableinfo, String s, int i)
    {
        if(searchableinfo == null)
            return null;
        Object obj = searchableinfo.getSuggestAuthority();
        if(obj == null)
            return null;
        obj = (new android.net.Uri.Builder()).scheme("content").authority(((String) (obj))).query("").fragment("");
        String s1 = searchableinfo.getSuggestPath();
        if(s1 != null)
            ((android.net.Uri.Builder) (obj)).appendEncodedPath(s1);
        ((android.net.Uri.Builder) (obj)).appendPath("search_suggest_query");
        s1 = searchableinfo.getSuggestSelection();
        searchableinfo = null;
        if(s1 != null)
        {
            searchableinfo = new String[1];
            searchableinfo[0] = s;
        } else
        {
            ((android.net.Uri.Builder) (obj)).appendPath(s);
        }
        if(i > 0)
            ((android.net.Uri.Builder) (obj)).appendQueryParameter("limit", String.valueOf(i));
        s = ((android.net.Uri.Builder) (obj)).build();
        return mContext.getContentResolver().query(s, null, s1, searchableinfo, null);
    }

    public ComponentName getWebSearchActivity()
    {
        ComponentName componentname;
        try
        {
            componentname = mService.getWebSearchActivity();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return componentname;
    }

    public boolean isVisible()
    {
        boolean flag;
        if(mSearchDialog == null)
            flag = false;
        else
            flag = mSearchDialog.isShowing();
        return flag;
    }

    public void launchAssist(Bundle bundle)
    {
        try
        {
            if(mService == null)
                return;
        }
        // Misplaced declaration of an exception variable
        catch(Bundle bundle)
        {
            throw bundle.rethrowFromSystemServer();
        }
        mService.launchAssist(bundle);
        return;
    }

    public boolean launchLegacyAssist(String s, int i, Bundle bundle)
    {
        if(mService == null)
            return false;
        boolean flag;
        try
        {
            flag = mService.launchLegacyAssist(s, i, bundle);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        if(mCancelListener != null)
            mCancelListener.onCancel();
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        if(mDismissListener != null)
            mDismissListener.onDismiss();
    }

    public void setOnCancelListener(OnCancelListener oncancellistener)
    {
        mCancelListener = oncancellistener;
    }

    public void setOnDismissListener(OnDismissListener ondismisslistener)
    {
        mDismissListener = ondismisslistener;
    }

    void startGlobalSearch(String s, boolean flag, Bundle bundle, Rect rect)
    {
        ComponentName componentname;
        componentname = getGlobalSearchActivity();
        if(componentname == null)
        {
            Log.w("SearchManager", "No global search activity found.");
            return;
        }
        Intent intent = new Intent("android.search.action.GLOBAL_SEARCH");
        intent.addFlags(0x10000000);
        intent.setComponent(componentname);
        if(bundle == null)
            bundle = new Bundle();
        else
            bundle = new Bundle(bundle);
        if(!bundle.containsKey("source"))
            bundle.putString("source", mContext.getPackageName());
        intent.putExtra("app_data", bundle);
        if(!TextUtils.isEmpty(s))
            intent.putExtra("query", s);
        if(flag)
            intent.putExtra("select_query", flag);
        intent.setSourceBounds(rect);
        mContext.startActivity(intent);
_L1:
        return;
        s;
        Log.e("SearchManager", (new StringBuilder()).append("Global search activity not found: ").append(componentname).toString());
          goto _L1
    }

    public void startSearch(String s, boolean flag, ComponentName componentname, Bundle bundle, boolean flag1)
    {
        startSearch(s, flag, componentname, bundle, flag1, null);
    }

    public void startSearch(String s, boolean flag, ComponentName componentname, Bundle bundle, boolean flag1, Rect rect)
    {
        if(flag1)
        {
            startGlobalSearch(s, flag, bundle, rect);
            return;
        }
        if(((UiModeManager)mContext.getSystemService(android/app/UiModeManager)).getCurrentModeType() != 4)
        {
            ensureSearchDialog();
            mSearchDialog.show(s, flag, componentname, bundle);
        }
    }

    public void stopSearch()
    {
        if(mSearchDialog != null)
            mSearchDialog.cancel();
    }

    public void triggerSearch(String s, ComponentName componentname, Bundle bundle)
    {
        if(s == null || TextUtils.getTrimmedLength(s) == 0)
        {
            Log.w("SearchManager", "triggerSearch called with empty query, ignoring.");
            return;
        } else
        {
            startSearch(s, false, componentname, bundle, false);
            mSearchDialog.launchQuerySearch();
            return;
        }
    }

    public static final String ACTION_KEY = "action_key";
    public static final String ACTION_MSG = "action_msg";
    public static final String APP_DATA = "app_data";
    public static final String CONTEXT_IS_VOICE = "android.search.CONTEXT_IS_VOICE";
    public static final String CURSOR_EXTRA_KEY_IN_PROGRESS = "in_progress";
    private static final boolean DBG = false;
    public static final String DISABLE_VOICE_SEARCH = "android.search.DISABLE_VOICE_SEARCH";
    public static final String EXTRA_DATA_KEY = "intent_extra_data_key";
    public static final String EXTRA_NEW_SEARCH = "new_search";
    public static final String EXTRA_SELECT_QUERY = "select_query";
    public static final String EXTRA_WEB_SEARCH_PENDINGINTENT = "web_search_pendingintent";
    public static final int FLAG_QUERY_REFINEMENT = 1;
    public static final String INTENT_ACTION_GLOBAL_SEARCH = "android.search.action.GLOBAL_SEARCH";
    public static final String INTENT_ACTION_SEARCHABLES_CHANGED = "android.search.action.SEARCHABLES_CHANGED";
    public static final String INTENT_ACTION_SEARCH_SETTINGS = "android.search.action.SEARCH_SETTINGS";
    public static final String INTENT_ACTION_SEARCH_SETTINGS_CHANGED = "android.search.action.SETTINGS_CHANGED";
    public static final String INTENT_ACTION_WEB_SEARCH_SETTINGS = "android.search.action.WEB_SEARCH_SETTINGS";
    public static final String INTENT_GLOBAL_SEARCH_ACTIVITY_CHANGED = "android.search.action.GLOBAL_SEARCH_ACTIVITY_CHANGED";
    public static final char MENU_KEY = 115;
    public static final int MENU_KEYCODE = 47;
    public static final String QUERY = "query";
    public static final String SEARCH_MODE = "search_mode";
    public static final String SHORTCUT_MIME_TYPE = "vnd.android.cursor.item/vnd.android.search.suggest";
    public static final String SUGGEST_COLUMN_AUDIO_CHANNEL_CONFIG = "suggest_audio_channel_config";
    public static final String SUGGEST_COLUMN_CONTENT_TYPE = "suggest_content_type";
    public static final String SUGGEST_COLUMN_DURATION = "suggest_duration";
    public static final String SUGGEST_COLUMN_FLAGS = "suggest_flags";
    public static final String SUGGEST_COLUMN_FORMAT = "suggest_format";
    public static final String SUGGEST_COLUMN_ICON_1 = "suggest_icon_1";
    public static final String SUGGEST_COLUMN_ICON_2 = "suggest_icon_2";
    public static final String SUGGEST_COLUMN_INTENT_ACTION = "suggest_intent_action";
    public static final String SUGGEST_COLUMN_INTENT_DATA = "suggest_intent_data";
    public static final String SUGGEST_COLUMN_INTENT_DATA_ID = "suggest_intent_data_id";
    public static final String SUGGEST_COLUMN_INTENT_EXTRA_DATA = "suggest_intent_extra_data";
    public static final String SUGGEST_COLUMN_IS_LIVE = "suggest_is_live";
    public static final String SUGGEST_COLUMN_LAST_ACCESS_HINT = "suggest_last_access_hint";
    public static final String SUGGEST_COLUMN_PRODUCTION_YEAR = "suggest_production_year";
    public static final String SUGGEST_COLUMN_PURCHASE_PRICE = "suggest_purchase_price";
    public static final String SUGGEST_COLUMN_QUERY = "suggest_intent_query";
    public static final String SUGGEST_COLUMN_RATING_SCORE = "suggest_rating_score";
    public static final String SUGGEST_COLUMN_RATING_STYLE = "suggest_rating_style";
    public static final String SUGGEST_COLUMN_RENTAL_PRICE = "suggest_rental_price";
    public static final String SUGGEST_COLUMN_RESULT_CARD_IMAGE = "suggest_result_card_image";
    public static final String SUGGEST_COLUMN_SHORTCUT_ID = "suggest_shortcut_id";
    public static final String SUGGEST_COLUMN_SPINNER_WHILE_REFRESHING = "suggest_spinner_while_refreshing";
    public static final String SUGGEST_COLUMN_TEXT_1 = "suggest_text_1";
    public static final String SUGGEST_COLUMN_TEXT_2 = "suggest_text_2";
    public static final String SUGGEST_COLUMN_TEXT_2_URL = "suggest_text_2_url";
    public static final String SUGGEST_COLUMN_VIDEO_HEIGHT = "suggest_video_height";
    public static final String SUGGEST_COLUMN_VIDEO_WIDTH = "suggest_video_width";
    public static final String SUGGEST_MIME_TYPE = "vnd.android.cursor.dir/vnd.android.search.suggest";
    public static final String SUGGEST_NEVER_MAKE_SHORTCUT = "_-1";
    public static final String SUGGEST_PARAMETER_LIMIT = "limit";
    public static final String SUGGEST_URI_PATH_QUERY = "search_suggest_query";
    public static final String SUGGEST_URI_PATH_SHORTCUT = "search_suggest_shortcut";
    private static final String TAG = "SearchManager";
    public static final String USER_QUERY = "user_query";
    OnCancelListener mCancelListener;
    private final Context mContext;
    OnDismissListener mDismissListener;
    final Handler mHandler;
    private SearchDialog mSearchDialog;
    private final ISearchManager mService = ISearchManager.Stub.asInterface(ServiceManager.getServiceOrThrow("search"));
}
