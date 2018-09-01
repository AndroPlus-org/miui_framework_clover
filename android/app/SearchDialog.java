// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.*;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

// Referenced classes of package android.app:
//            Dialog, SearchableInfo, SearchManager

public class SearchDialog extends Dialog
{
    public static class SearchBar extends LinearLayout
    {

        public ActionMode startActionModeForChild(View view, android.view.ActionMode.Callback callback, int i)
        {
            if(i != 0)
                return super.startActionModeForChild(view, callback, i);
            else
                return null;
        }

        public SearchBar(Context context)
        {
            super(context);
        }

        public SearchBar(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }


    static boolean _2D_wrap0(SearchDialog searchdialog)
    {
        return searchdialog.onClosePressed();
    }

    public SearchDialog(Context context, SearchManager searchmanager)
    {
        super(context, resolveDialogTheme(context));
        mConfChangeListener = new BroadcastReceiver() {

            public void onReceive(Context context1, Intent intent)
            {
                if(intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED"))
                    onConfigurationChanged();
            }

            final SearchDialog this$0;

            
            {
                this$0 = SearchDialog.this;
                super();
            }
        }
;
        mVoiceWebSearchIntent.addFlags(0x10000000);
        mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        mVoiceAppSearchIntent.addFlags(0x10000000);
    }

    private void createContentView()
    {
        setContentView(0x10900ea);
        mSearchView = (SearchView)findViewById(0x10203cf);
        mSearchView.setIconified(false);
        mSearchView.setOnCloseListener(mOnCloseListener);
        mSearchView.setOnQueryTextListener(mOnQueryChangeListener);
        mSearchView.setOnSuggestionListener(mOnSuggestionSelectionListener);
        mSearchView.onActionViewExpanded();
        mCloseSearch = findViewById(0x1020027);
        mCloseSearch.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                dismiss();
            }

            final SearchDialog this$0;

            
            {
                this$0 = SearchDialog.this;
                super();
            }
        }
);
        mBadgeLabel = (TextView)mSearchView.findViewById(0x10203c6);
        mSearchAutoComplete = (AutoCompleteTextView)mSearchView.findViewById(0x10203ce);
        mAppIcon = (ImageView)findViewById(0x10203c5);
        mSearchPlate = mSearchView.findViewById(0x10203cd);
        mWorkingSpinner = getContext().getDrawable(0x1080737);
        setWorking(false);
        mBadgeLabel.setVisibility(8);
        mSearchAutoCompleteImeOptions = mSearchAutoComplete.getImeOptions();
    }

    private Intent createIntent(String s, Uri uri, String s1, String s2, int i, String s3)
    {
        s = new Intent(s);
        s.addFlags(0x10000000);
        if(uri != null)
            s.setData(uri);
        s.putExtra("user_query", mUserQuery);
        if(s2 != null)
            s.putExtra("query", s2);
        if(s1 != null)
            s.putExtra("intent_extra_data_key", s1);
        if(mAppSearchData != null)
            s.putExtra("app_data", mAppSearchData);
        if(i != 0)
        {
            s.putExtra("action_key", i);
            s.putExtra("action_msg", s3);
        }
        s.setComponent(mSearchable.getSearchActivity());
        return s;
    }

    private boolean doShow(String s, boolean flag, ComponentName componentname, Bundle bundle)
    {
        if(!show(componentname, bundle))
            return false;
        setUserQuery(s);
        if(flag)
            mSearchAutoComplete.selectAll();
        return true;
    }

    private boolean isEmpty(AutoCompleteTextView autocompletetextview)
    {
        boolean flag = false;
        if(TextUtils.getTrimmedLength(autocompletetextview.getText()) == 0)
            flag = true;
        return flag;
    }

    static boolean isLandscapeMode(Context context)
    {
        boolean flag;
        if(context.getResources().getConfiguration().orientation == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean isOutOfBounds(View view, MotionEvent motionevent)
    {
        boolean flag;
        int i;
        int j;
        int k;
        boolean flag1;
        flag = true;
        i = (int)motionevent.getX();
        j = (int)motionevent.getY();
        k = ViewConfiguration.get(mContext).getScaledWindowTouchSlop();
        flag1 = flag;
        if(i < -k) goto _L2; else goto _L1
_L1:
        if(j >= -k) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i <= view.getWidth() + k)
        {
            flag1 = flag;
            if(j <= view.getHeight() + k)
                flag1 = false;
        }
        if(true) goto _L2; else goto _L5
_L5:
    }

    private void launchIntent(Intent intent)
    {
        if(intent == null)
            return;
        Log.d("SearchDialog", (new StringBuilder()).append("launching ").append(intent).toString());
        getContext().startActivity(intent);
        dismiss();
_L1:
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        Log.e("SearchDialog", (new StringBuilder()).append("Failed launch activity: ").append(intent).toString(), runtimeexception);
          goto _L1
    }

    private boolean onClosePressed()
    {
        if(isEmpty(mSearchAutoComplete))
        {
            dismiss();
            return true;
        } else
        {
            return false;
        }
    }

    static int resolveDialogTheme(Context context)
    {
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x11100ae, typedvalue, true);
        return typedvalue.resourceId;
    }

    private void setUserQuery(String s)
    {
        String s1 = s;
        if(s == null)
            s1 = "";
        mUserQuery = s1;
        mSearchAutoComplete.setText(s1);
        mSearchAutoComplete.setSelection(s1.length());
    }

    private boolean show(ComponentName componentname, Bundle bundle)
    {
        mSearchable = ((SearchManager)mContext.getSystemService("search")).getSearchableInfo(componentname);
        if(mSearchable == null)
            return false;
        mLaunchComponent = componentname;
        mAppSearchData = bundle;
        mActivityContext = mSearchable.getActivityContext(getContext());
        if(!isShowing())
        {
            createContentView();
            mSearchView.setSearchableInfo(mSearchable);
            mSearchView.setAppSearchData(mAppSearchData);
            show();
        }
        updateUI();
        return true;
    }

    private void updateSearchAppIcon()
    {
        PackageManager packagemanager = getContext().getPackageManager();
        Drawable drawable;
        try
        {
            drawable = packagemanager.getApplicationIcon(packagemanager.getActivityInfo(mLaunchComponent, 0).applicationInfo);
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception = packagemanager.getDefaultActivityIcon();
            Log.w("SearchDialog", (new StringBuilder()).append(mLaunchComponent).append(" not found, using generic app icon").toString());
        }
        mAppIcon.setImageDrawable(drawable);
        mAppIcon.setVisibility(0);
        mSearchPlate.setPadding(7, mSearchPlate.getPaddingTop(), mSearchPlate.getPaddingRight(), mSearchPlate.getPaddingBottom());
    }

    private void updateSearchAutoComplete()
    {
        mSearchAutoComplete.setDropDownDismissedOnCompletion(false);
        mSearchAutoComplete.setForceIgnoreOutsideTouch(false);
    }

    private void updateSearchBadge()
    {
        byte byte0;
        Object obj;
        Object obj1;
        byte0 = 8;
        obj = null;
        obj1 = null;
        if(!mSearchable.useBadgeIcon()) goto _L2; else goto _L1
_L1:
        Drawable drawable;
        drawable = mActivityContext.getDrawable(mSearchable.getIconId());
        byte0 = 0;
_L4:
        mBadgeLabel.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        mBadgeLabel.setText(((CharSequence) (obj1)));
        mBadgeLabel.setVisibility(byte0);
        return;
_L2:
        drawable = obj;
        if(mSearchable.useBadgeLabel())
        {
            obj1 = mActivityContext.getResources().getText(mSearchable.getLabelId()).toString();
            byte0 = 0;
            drawable = obj;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private void updateUI()
    {
        if(mSearchable != null)
        {
            mDecor.setVisibility(0);
            updateSearchAutoComplete();
            updateSearchAppIcon();
            updateSearchBadge();
            int i = mSearchable.getInputType();
            int j = i;
            if((i & 0xf) == 1)
            {
                i &= 0xfffeffff;
                j = i;
                if(mSearchable.getSuggestAuthority() != null)
                    j = i | 0x10000;
            }
            mSearchAutoComplete.setInputType(j);
            mSearchAutoCompleteImeOptions = mSearchable.getImeOptions();
            mSearchAutoComplete.setImeOptions(mSearchAutoCompleteImeOptions);
            if(mSearchable.getVoiceSearchEnabled())
                mSearchAutoComplete.setPrivateImeOptions("nm");
            else
                mSearchAutoComplete.setPrivateImeOptions(null);
        }
    }

    public void hide()
    {
        if(!isShowing())
            return;
        InputMethodManager inputmethodmanager = (InputMethodManager)getContext().getSystemService(android/view/inputmethod/InputMethodManager);
        if(inputmethodmanager != null)
            inputmethodmanager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        super.hide();
    }

    public void launchQuerySearch()
    {
        launchQuerySearch(0, null);
    }

    protected void launchQuerySearch(int i, String s)
    {
        launchIntent(createIntent("android.intent.action.SEARCH", null, null, mSearchAutoComplete.getText().toString(), i, s));
    }

    public void onBackPressed()
    {
        InputMethodManager inputmethodmanager = (InputMethodManager)getContext().getSystemService(android/view/inputmethod/InputMethodManager);
        if(inputmethodmanager != null && inputmethodmanager.isFullscreenMode() && inputmethodmanager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0))
        {
            return;
        } else
        {
            cancel();
            return;
        }
    }

    public void onConfigurationChanged()
    {
        if(mSearchable != null && isShowing())
        {
            updateSearchAppIcon();
            updateSearchBadge();
            if(isLandscapeMode(getContext()))
                mSearchAutoComplete.ensureImeVisible(true);
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = getWindow();
        android.view.WindowManager.LayoutParams layoutparams = bundle.getAttributes();
        layoutparams.width = -1;
        layoutparams.height = -1;
        layoutparams.gravity = 55;
        layoutparams.softInputMode = 16;
        bundle.setAttributes(layoutparams);
        setCanceledOnTouchOutside(true);
    }

    public void onRestoreInstanceState(Bundle bundle)
    {
        if(bundle == null)
            return;
        ComponentName componentname = (ComponentName)bundle.getParcelable("comp");
        Bundle bundle1 = bundle.getBundle("data");
        if(!doShow(bundle.getString("uQry"), false, componentname, bundle1))
            return;
        else
            return;
    }

    public Bundle onSaveInstanceState()
    {
        if(!isShowing())
        {
            return null;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putParcelable("comp", mLaunchComponent);
            bundle.putBundle("data", mAppSearchData);
            bundle.putString("uQry", mUserQuery);
            return bundle;
        }
    }

    public void onStart()
    {
        super.onStart();
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        getContext().registerReceiver(mConfChangeListener, intentfilter);
    }

    public void onStop()
    {
        super.onStop();
        getContext().unregisterReceiver(mConfChangeListener);
        mLaunchComponent = null;
        mAppSearchData = null;
        mSearchable = null;
        mUserQuery = null;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(!mSearchAutoComplete.isPopupShowing() && isOutOfBounds(mSearchPlate, motionevent))
        {
            cancel();
            return true;
        } else
        {
            return super.onTouchEvent(motionevent);
        }
    }

    public void setListSelection(int i)
    {
        mSearchAutoComplete.setListSelection(i);
    }

    public void setWorking(boolean flag)
    {
        Drawable drawable = mWorkingSpinner;
        char c;
        if(flag)
            c = '\377';
        else
            c = '\0';
        drawable.setAlpha(c);
        mWorkingSpinner.setVisible(flag, false);
        mWorkingSpinner.invalidateSelf();
    }

    public boolean show(String s, boolean flag, ComponentName componentname, Bundle bundle)
    {
        flag = doShow(s, flag, componentname, bundle);
        if(flag)
            mSearchAutoComplete.showDropDownAfterLayout();
        return flag;
    }

    private static final boolean DBG = false;
    private static final String IME_OPTION_NO_MICROPHONE = "nm";
    private static final String INSTANCE_KEY_APPDATA = "data";
    private static final String INSTANCE_KEY_COMPONENT = "comp";
    private static final String INSTANCE_KEY_USER_QUERY = "uQry";
    private static final String LOG_TAG = "SearchDialog";
    private static final int SEARCH_PLATE_LEFT_PADDING_NON_GLOBAL = 7;
    private Context mActivityContext;
    private ImageView mAppIcon;
    private Bundle mAppSearchData;
    private TextView mBadgeLabel;
    private View mCloseSearch;
    private BroadcastReceiver mConfChangeListener;
    private ComponentName mLaunchComponent;
    private final android.widget.SearchView.OnCloseListener mOnCloseListener = new android.widget.SearchView.OnCloseListener() {

        public boolean onClose()
        {
            return SearchDialog._2D_wrap0(SearchDialog.this);
        }

        final SearchDialog this$0;

            
            {
                this$0 = SearchDialog.this;
                super();
            }
    }
;
    private final android.widget.SearchView.OnQueryTextListener mOnQueryChangeListener = new android.widget.SearchView.OnQueryTextListener() {

        public boolean onQueryTextChange(String s)
        {
            return false;
        }

        public boolean onQueryTextSubmit(String s)
        {
            dismiss();
            return false;
        }

        final SearchDialog this$0;

            
            {
                this$0 = SearchDialog.this;
                super();
            }
    }
;
    private final android.widget.SearchView.OnSuggestionListener mOnSuggestionSelectionListener = new android.widget.SearchView.OnSuggestionListener() {

        public boolean onSuggestionClick(int i)
        {
            dismiss();
            return false;
        }

        public boolean onSuggestionSelect(int i)
        {
            return false;
        }

        final SearchDialog this$0;

            
            {
                this$0 = SearchDialog.this;
                super();
            }
    }
;
    private AutoCompleteTextView mSearchAutoComplete;
    private int mSearchAutoCompleteImeOptions;
    private View mSearchPlate;
    private SearchView mSearchView;
    private SearchableInfo mSearchable;
    private String mUserQuery;
    private final Intent mVoiceAppSearchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
    private final Intent mVoiceWebSearchIntent = new Intent("android.speech.action.WEB_SEARCH");
    private Drawable mWorkingSpinner;
}
