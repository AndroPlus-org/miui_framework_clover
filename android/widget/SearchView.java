// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.*;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.ImageSpan;
import android.util.*;
import android.view.*;
import android.view.inputmethod.*;
import java.util.WeakHashMap;

// Referenced classes of package android.widget:
//            LinearLayout, ImageView, SuggestionsAdapter, CursorAdapter, 
//            AutoCompleteTextView, TextView, AdapterView

public class SearchView extends LinearLayout
    implements CollapsibleActionView
{
    public static interface OnCloseListener
    {

        public abstract boolean onClose();
    }

    public static interface OnQueryTextListener
    {

        public abstract boolean onQueryTextChange(String s);

        public abstract boolean onQueryTextSubmit(String s);
    }

    public static interface OnSuggestionListener
    {

        public abstract boolean onSuggestionClick(int i);

        public abstract boolean onSuggestionSelect(int i);
    }

    static class SavedState extends android.view.View.BaseSavedState
    {

        public String toString()
        {
            return (new StringBuilder()).append("SearchView.SavedState{").append(Integer.toHexString(System.identityHashCode(this))).append(" isIconified=").append(isIconified).append("}").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(isIconified));
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SavedState createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        boolean isIconified;


        public SavedState(Parcel parcel)
        {
            super(parcel);
            isIconified = ((Boolean)parcel.readValue(null)).booleanValue();
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    public static class SearchAutoComplete extends AutoCompleteTextView
    {

        static boolean _2D_wrap0(SearchAutoComplete searchautocomplete)
        {
            return searchautocomplete.isEmpty();
        }

        static void _2D_wrap1(SearchAutoComplete searchautocomplete, boolean flag)
        {
            searchautocomplete.setImeVisibility(flag);
        }

        private int getSearchViewTextMinWidthDp()
        {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int j = configuration.screenHeightDp;
            int k = configuration.orientation;
            if(i >= 960 && j >= 720 && k == 2)
                return 256;
            return i < 600 && (i < 640 || j < 480) ? 160 : 192;
        }

        private boolean isEmpty()
        {
            boolean flag = false;
            if(TextUtils.getTrimmedLength(getText()) == 0)
                flag = true;
            return flag;
        }

        private void setImeVisibility(boolean flag)
        {
            InputMethodManager inputmethodmanager = (InputMethodManager)getContext().getSystemService(android/view/inputmethod/InputMethodManager);
            if(!flag)
            {
                mHasPendingShowSoftInputRequest = false;
                removeCallbacks(mRunShowSoftInputIfNecessary);
                inputmethodmanager.hideSoftInputFromWindow(getWindowToken(), 0);
                return;
            }
            if(inputmethodmanager.isActive(this))
            {
                mHasPendingShowSoftInputRequest = false;
                removeCallbacks(mRunShowSoftInputIfNecessary);
                inputmethodmanager.showSoftInput(this, 0);
                return;
            } else
            {
                mHasPendingShowSoftInputRequest = true;
                return;
            }
        }

        private void showSoftInputIfNecessary()
        {
            if(mHasPendingShowSoftInputRequest)
            {
                ((InputMethodManager)getContext().getSystemService(android/view/inputmethod/InputMethodManager)).showSoftInput(this, 0);
                mHasPendingShowSoftInputRequest = false;
            }
        }

        public boolean enoughToFilter()
        {
            boolean flag;
            if(mThreshold > 0)
                flag = super.enoughToFilter();
            else
                flag = true;
            return flag;
        }

        void lambda$_2D_android_widget_SearchView$SearchAutoComplete_74041()
        {
            showSoftInputIfNecessary();
        }

        public InputConnection onCreateInputConnection(EditorInfo editorinfo)
        {
            editorinfo = super.onCreateInputConnection(editorinfo);
            if(mHasPendingShowSoftInputRequest)
            {
                removeCallbacks(mRunShowSoftInputIfNecessary);
                post(mRunShowSoftInputIfNecessary);
            }
            return editorinfo;
        }

        protected void onFinishInflate()
        {
            super.onFinishInflate();
            android.util.DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
            setMinWidth((int)TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), displaymetrics));
        }

        protected void onFocusChanged(boolean flag, int i, Rect rect)
        {
            super.onFocusChanged(flag, i, rect);
            mSearchView.onTextFocusChanged();
        }

        public boolean onKeyPreIme(int i, KeyEvent keyevent)
        {
            if(i == 4)
            {
                if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
                {
                    android.view.KeyEvent.DispatcherState dispatcherstate = getKeyDispatcherState();
                    if(dispatcherstate != null)
                        dispatcherstate.startTracking(keyevent, this);
                    return true;
                }
                if(keyevent.getAction() == 1)
                {
                    android.view.KeyEvent.DispatcherState dispatcherstate1 = getKeyDispatcherState();
                    if(dispatcherstate1 != null)
                        dispatcherstate1.handleUpEvent(keyevent);
                    if(keyevent.isTracking() && keyevent.isCanceled() ^ true)
                    {
                        mSearchView.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyevent);
        }

        public void onWindowFocusChanged(boolean flag)
        {
            super.onWindowFocusChanged(flag);
            if(flag && mSearchView.hasFocus() && getVisibility() == 0)
            {
                mHasPendingShowSoftInputRequest = true;
                if(SearchView.isLandscapeMode(getContext()))
                    ensureImeVisible(true);
            }
        }

        public void performCompletion()
        {
        }

        protected void replaceText(CharSequence charsequence)
        {
        }

        void setSearchView(SearchView searchview)
        {
            mSearchView = searchview;
        }

        public void setThreshold(int i)
        {
            super.setThreshold(i);
            mThreshold = i;
        }

        private boolean mHasPendingShowSoftInputRequest;
        final Runnable mRunShowSoftInputIfNecessary;
        private SearchView mSearchView;
        private int mThreshold;

        public SearchAutoComplete(Context context)
        {
            super(context);
            mRunShowSoftInputIfNecessary = new _.Lambda._cls2f4l12BcqlVIiuw8w0ONZMWiEpk((byte)2, this);
            mThreshold = getThreshold();
        }

        public SearchAutoComplete(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            mRunShowSoftInputIfNecessary = new _.Lambda._cls2f4l12BcqlVIiuw8w0ONZMWiEpk((byte)3, this);
            mThreshold = getThreshold();
        }

        public SearchAutoComplete(Context context, AttributeSet attributeset, int i)
        {
            super(context, attributeset, i);
            mRunShowSoftInputIfNecessary = new _.Lambda._cls2f4l12BcqlVIiuw8w0ONZMWiEpk((byte)4, this);
            mThreshold = getThreshold();
        }

        public SearchAutoComplete(Context context, AttributeSet attributeset, int i, int j)
        {
            super(context, attributeset, i, j);
            mRunShowSoftInputIfNecessary = new _.Lambda._cls2f4l12BcqlVIiuw8w0ONZMWiEpk((byte)5, this);
            mThreshold = getThreshold();
        }
    }

    private static class UpdatableTouchDelegate extends TouchDelegate
    {

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            int i;
            int j;
            boolean flag;
            boolean flag1;
            boolean flag2;
            i = (int)motionevent.getX();
            j = (int)motionevent.getY();
            flag = false;
            flag1 = true;
            flag2 = false;
            motionevent.getAction();
            JVM INSTR tableswitch 0 3: default 56
        //                       0 121
        //                       1 152
        //                       2 152
        //                       3 201;
               goto _L1 _L2 _L3 _L3 _L4
_L4:
            break MISSING_BLOCK_LABEL_201;
_L1:
            boolean flag3 = flag1;
_L5:
            if(flag)
            {
                boolean flag4;
                if(flag3 && mActualBounds.contains(i, j) ^ true)
                    motionevent.setLocation(mDelegateView.getWidth() / 2, mDelegateView.getHeight() / 2);
                else
                    motionevent.setLocation(i - mActualBounds.left, j - mActualBounds.top);
                flag2 = mDelegateView.dispatchTouchEvent(motionevent);
            }
            return flag2;
_L2:
            flag3 = flag1;
            if(mTargetBounds.contains(i, j))
            {
                mDelegateTargeted = true;
                flag = true;
                flag3 = flag1;
            }
              goto _L5
_L3:
            flag4 = mDelegateTargeted;
            flag3 = flag1;
            flag = flag4;
            if(flag4)
            {
                flag3 = flag1;
                flag = flag4;
                if(!mSlopBounds.contains(i, j))
                {
                    flag3 = false;
                    flag = flag4;
                }
            }
              goto _L5
            flag = mDelegateTargeted;
            mDelegateTargeted = false;
            flag3 = flag1;
              goto _L5
        }

        public void setBounds(Rect rect, Rect rect1)
        {
            mTargetBounds.set(rect);
            mSlopBounds.set(rect);
            mSlopBounds.inset(-mSlop, -mSlop);
            mActualBounds.set(rect1);
        }

        private final Rect mActualBounds = new Rect();
        private boolean mDelegateTargeted;
        private final View mDelegateView;
        private final int mSlop;
        private final Rect mSlopBounds = new Rect();
        private final Rect mTargetBounds = new Rect();

        public UpdatableTouchDelegate(Rect rect, Rect rect1, View view)
        {
            super(rect, view);
            mSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            setBounds(rect, rect1);
            mDelegateView = view;
        }
    }


    static ImageView _2D_get0(SearchView searchview)
    {
        return searchview.mCloseButton;
    }

    static ImageView _2D_get1(SearchView searchview)
    {
        return searchview.mGoButton;
    }

    static android.view.View.OnFocusChangeListener _2D_get2(SearchView searchview)
    {
        return searchview.mOnQueryTextFocusChangeListener;
    }

    static ImageView _2D_get3(SearchView searchview)
    {
        return searchview.mSearchButton;
    }

    static SearchAutoComplete _2D_get4(SearchView searchview)
    {
        return searchview.mSearchSrcTextView;
    }

    static SearchableInfo _2D_get5(SearchView searchview)
    {
        return searchview.mSearchable;
    }

    static CursorAdapter _2D_get6(SearchView searchview)
    {
        return searchview.mSuggestionsAdapter;
    }

    static ImageView _2D_get7(SearchView searchview)
    {
        return searchview.mVoiceButton;
    }

    static boolean _2D_wrap0(SearchView searchview, int i, int j, String s)
    {
        return searchview.onItemClicked(i, j, s);
    }

    static boolean _2D_wrap1(SearchView searchview, int i)
    {
        return searchview.onItemSelected(i);
    }

    static void _2D_wrap10(SearchView searchview)
    {
        searchview.onVoiceClicked();
    }

    static void _2D_wrap11(SearchView searchview)
    {
        searchview.updateFocusedState();
    }

    static boolean _2D_wrap2(SearchView searchview, View view, int i, KeyEvent keyevent)
    {
        return searchview.onSuggestionsKey(view, i, keyevent);
    }

    static void _2D_wrap3(SearchView searchview)
    {
        searchview.adjustDropDownSizeAndPosition();
    }

    static void _2D_wrap4(SearchView searchview)
    {
        searchview.forceSuggestionQuery();
    }

    static void _2D_wrap5(SearchView searchview, int i, String s, String s1)
    {
        searchview.launchQuerySearch(i, s, s1);
    }

    static void _2D_wrap6(SearchView searchview)
    {
        searchview.onCloseClicked();
    }

    static void _2D_wrap7(SearchView searchview)
    {
        searchview.onSearchClicked();
    }

    static void _2D_wrap8(SearchView searchview)
    {
        searchview.onSubmitQuery();
    }

    static void _2D_wrap9(SearchView searchview, CharSequence charsequence)
    {
        searchview.onTextChanged(charsequence);
    }

    public SearchView(Context context)
    {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010480);
    }

    public SearchView(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public SearchView(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mSearchSrcTextViewBounds = new Rect();
        mSearchSrtTextViewBoundsExpanded = new Rect();
        mTemp = new int[2];
        mTemp2 = new int[2];
        mOutsideDrawablesCache = new WeakHashMap();
        mOnClickListener = new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(view != SearchView._2D_get3(SearchView.this)) goto _L2; else goto _L1
_L1:
                SearchView._2D_wrap7(SearchView.this);
_L4:
                return;
_L2:
                if(view == SearchView._2D_get0(SearchView.this))
                    SearchView._2D_wrap6(SearchView.this);
                else
                if(view == SearchView._2D_get1(SearchView.this))
                    SearchView._2D_wrap8(SearchView.this);
                else
                if(view == SearchView._2D_get7(SearchView.this))
                    SearchView._2D_wrap10(SearchView.this);
                else
                if(view == SearchView._2D_get4(SearchView.this))
                    SearchView._2D_wrap4(SearchView.this);
                if(true) goto _L4; else goto _L3
_L3:
            }

            final SearchView this$0;

            
            {
                this$0 = SearchView.this;
                super();
            }
        }
;
        mOnEditorActionListener = new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView textview, int k, KeyEvent keyevent)
            {
                SearchView._2D_wrap8(SearchView.this);
                return true;
            }

            final SearchView this$0;

            
            {
                this$0 = SearchView.this;
                super();
            }
        }
;
        mOnItemClickListener = new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int k, long l)
            {
                SearchView._2D_wrap0(SearchView.this, k, 0, null);
            }

            final SearchView this$0;

            
            {
                this$0 = SearchView.this;
                super();
            }
        }
;
        mOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view, int k, long l)
            {
                SearchView._2D_wrap1(SearchView.this, k);
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            final SearchView this$0;

            
            {
                this$0 = SearchView.this;
                super();
            }
        }
;
        attributeset = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.SearchView, i, j);
        ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(attributeset.getResourceId(0, 0x10900ec), this, true);
        mSearchSrcTextView = (SearchAutoComplete)findViewById(0x10203ce);
        mSearchSrcTextView.setSearchView(this);
        mSearchEditFrame = findViewById(0x10203ca);
        mSearchPlate = findViewById(0x10203cd);
        mSubmitArea = findViewById(0x1020426);
        mSearchButton = (ImageView)findViewById(0x10203c8);
        mGoButton = (ImageView)findViewById(0x10203cb);
        mCloseButton = (ImageView)findViewById(0x10203c9);
        mVoiceButton = (ImageView)findViewById(0x10203d0);
        mCollapsedIcon = (ImageView)findViewById(0x10203cc);
        mSearchPlate.setBackground(attributeset.getDrawable(12));
        mSubmitArea.setBackground(attributeset.getDrawable(13));
        mSearchButton.setImageDrawable(attributeset.getDrawable(8));
        mGoButton.setImageDrawable(attributeset.getDrawable(7));
        mCloseButton.setImageDrawable(attributeset.getDrawable(6));
        mVoiceButton.setImageDrawable(attributeset.getDrawable(9));
        mCollapsedIcon.setImageDrawable(attributeset.getDrawable(8));
        if(attributeset.hasValueOrEmpty(14))
            mSearchHintIcon = attributeset.getDrawable(14);
        else
            mSearchHintIcon = attributeset.getDrawable(8);
        mSuggestionRowLayout = attributeset.getResourceId(11, 0x10900eb);
        mSuggestionCommitIconResId = attributeset.getResourceId(10, 0);
        mSearchButton.setOnClickListener(mOnClickListener);
        mCloseButton.setOnClickListener(mOnClickListener);
        mGoButton.setOnClickListener(mOnClickListener);
        mVoiceButton.setOnClickListener(mOnClickListener);
        mSearchSrcTextView.setOnClickListener(mOnClickListener);
        mSearchSrcTextView.addTextChangedListener(mTextWatcher);
        mSearchSrcTextView.setOnEditorActionListener(mOnEditorActionListener);
        mSearchSrcTextView.setOnItemClickListener(mOnItemClickListener);
        mSearchSrcTextView.setOnItemSelectedListener(mOnItemSelectedListener);
        mSearchSrcTextView.setOnKeyListener(mTextKeyListener);
        mSearchSrcTextView.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            public void onFocusChange(View view, boolean flag)
            {
                if(SearchView._2D_get2(SearchView.this) != null)
                    SearchView._2D_get2(SearchView.this).onFocusChange(SearchView.this, flag);
            }

            final SearchView this$0;

            
            {
                this$0 = SearchView.this;
                super();
            }
        }
);
        setIconifiedByDefault(attributeset.getBoolean(4, true));
        i = attributeset.getDimensionPixelSize(1, -1);
        if(i != -1)
            setMaxWidth(i);
        mDefaultQueryHint = attributeset.getText(15);
        mQueryHint = attributeset.getText(5);
        i = attributeset.getInt(3, -1);
        if(i != -1)
            setImeOptions(i);
        i = attributeset.getInt(2, -1);
        if(i != -1)
            setInputType(i);
        if(getFocusable() == 16)
            setFocusable(1);
        attributeset.recycle();
        mVoiceWebSearchIntent = new Intent("android.speech.action.WEB_SEARCH");
        mVoiceWebSearchIntent.addFlags(0x10000000);
        mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        mVoiceAppSearchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        mVoiceAppSearchIntent.addFlags(0x10000000);
        mDropDownAnchor = findViewById(mSearchSrcTextView.getDropDownAnchor());
        if(mDropDownAnchor != null)
            mDropDownAnchor.addOnLayoutChangeListener(new android.view.View.OnLayoutChangeListener() {

                public void onLayoutChange(View view, int k, int l, int i1, int j1, int k1, int l1, 
                        int i2, int j2)
                {
                    SearchView._2D_wrap3(SearchView.this);
                }

                final SearchView this$0;

            
            {
                this$0 = SearchView.this;
                super();
            }
            }
);
        updateViewsVisibility(mIconifiedByDefault);
        updateQueryHint();
    }

    private void adjustDropDownSizeAndPosition()
    {
        if(mDropDownAnchor.getWidth() > 1)
        {
            Resources resources = getContext().getResources();
            int i = mSearchPlate.getPaddingLeft();
            Rect rect = new Rect();
            boolean flag = isLayoutRtl();
            int j;
            int k;
            int l;
            int i1;
            if(mIconifiedByDefault)
                j = resources.getDimensionPixelSize(0x105007d) + resources.getDimensionPixelSize(0x105007e);
            else
                j = 0;
            mSearchSrcTextView.getDropDownBackground().getPadding(rect);
            if(flag)
                k = -rect.left;
            else
                k = i - (rect.left + j);
            mSearchSrcTextView.setDropDownHorizontalOffset(k);
            l = mDropDownAnchor.getWidth();
            k = rect.left;
            i1 = rect.right;
            mSearchSrcTextView.setDropDownWidth((l + k + i1 + j) - i);
        }
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

    private Intent createIntentFromSuggestion(Cursor cursor, int i, String s)
    {
        String s1;
        Object obj;
        Object obj1;
        String s2;
        try
        {
            s1 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_action");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            try
            {
                i = cursor.getPosition();
            }
            // Misplaced declaration of an exception variable
            catch(Cursor cursor)
            {
                i = -1;
            }
            Log.w("SearchView", (new StringBuilder()).append("Search suggestions cursor at row ").append(i).append(" returned exception.").toString(), s);
            return null;
        }
        obj = s1;
        if(s1 != null)
            break MISSING_BLOCK_LABEL_27;
        obj = mSearchable.getSuggestIntentAction();
        s1 = ((String) (obj));
        if(obj == null)
            s1 = "android.intent.action.SEARCH";
        obj1 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data");
        obj = obj1;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_68;
        obj = mSearchable.getSuggestIntentData();
        obj1 = obj;
        if(obj == null)
            break MISSING_BLOCK_LABEL_131;
        s2 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_data_id");
        obj1 = obj;
        if(s2 == null)
            break MISSING_BLOCK_LABEL_131;
        obj1 = JVM INSTR new #586 <Class StringBuilder>;
        ((StringBuilder) (obj1)).StringBuilder();
        obj1 = ((StringBuilder) (obj1)).append(((String) (obj))).append("/").append(Uri.encode(s2)).toString();
        if(obj1 != null) goto _L2; else goto _L1
_L1:
        obj = null;
_L4:
        obj1 = SuggestionsAdapter.getColumnString(cursor, "suggest_intent_query");
        return createIntent(s1, ((Uri) (obj)), SuggestionsAdapter.getColumnString(cursor, "suggest_intent_extra_data"), ((String) (obj1)), i, s);
_L2:
        obj = Uri.parse(((String) (obj1)));
        if(true) goto _L4; else goto _L3
_L3:
    }

    private Intent createVoiceAppSearchIntent(Intent intent, SearchableInfo searchableinfo)
    {
        ComponentName componentname = searchableinfo.getSearchActivity();
        Object obj = new Intent("android.intent.action.SEARCH");
        ((Intent) (obj)).setComponent(componentname);
        PendingIntent pendingintent = PendingIntent.getActivity(getContext(), 0, ((Intent) (obj)), 0x40000000);
        Bundle bundle = new Bundle();
        if(mAppSearchData != null)
            bundle.putParcelable("app_data", mAppSearchData);
        Intent intent1 = new Intent(intent);
        intent = "free_form";
        obj = null;
        String s = null;
        int i = 1;
        Resources resources = getResources();
        if(searchableinfo.getVoiceLanguageModeId() != 0)
            intent = resources.getString(searchableinfo.getVoiceLanguageModeId());
        if(searchableinfo.getVoicePromptTextId() != 0)
            obj = resources.getString(searchableinfo.getVoicePromptTextId());
        if(searchableinfo.getVoiceLanguageId() != 0)
            s = resources.getString(searchableinfo.getVoiceLanguageId());
        if(searchableinfo.getVoiceMaxResults() != 0)
            i = searchableinfo.getVoiceMaxResults();
        intent1.putExtra("android.speech.extra.LANGUAGE_MODEL", intent);
        intent1.putExtra("android.speech.extra.PROMPT", ((String) (obj)));
        intent1.putExtra("android.speech.extra.LANGUAGE", s);
        intent1.putExtra("android.speech.extra.MAX_RESULTS", i);
        if(componentname == null)
            intent = null;
        else
            intent = componentname.flattenToShortString();
        intent1.putExtra("calling_package", intent);
        intent1.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", pendingintent);
        intent1.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent1;
    }

    private Intent createVoiceWebSearchIntent(Intent intent, SearchableInfo searchableinfo)
    {
        Object obj = null;
        Intent intent1 = new Intent(intent);
        intent = searchableinfo.getSearchActivity();
        if(intent == null)
            intent = obj;
        else
            intent = intent.flattenToShortString();
        intent1.putExtra("calling_package", intent);
        return intent1;
    }

    private void dismissSuggestions()
    {
        mSearchSrcTextView.dismissDropDown();
    }

    private void forceSuggestionQuery()
    {
        mSearchSrcTextView.doBeforeTextChanged();
        mSearchSrcTextView.doAfterTextChanged();
    }

    private static String getActionKeyMessage(Cursor cursor, android.app.SearchableInfo.ActionKeyInfo actionkeyinfo)
    {
        String s = null;
        String s1 = actionkeyinfo.getSuggestActionMsgColumn();
        if(s1 != null)
            s = SuggestionsAdapter.getColumnString(cursor, s1);
        cursor = s;
        if(s == null)
            cursor = actionkeyinfo.getSuggestActionMsg();
        return cursor;
    }

    private void getChildBoundsWithinSearchView(View view, Rect rect)
    {
        view.getLocationInWindow(mTemp);
        getLocationInWindow(mTemp2);
        int i = mTemp[1] - mTemp2[1];
        int j = mTemp[0] - mTemp2[0];
        rect.set(j, i, view.getWidth() + j, view.getHeight() + i);
    }

    private CharSequence getDecoratedHint(CharSequence charsequence)
    {
        if(!mIconifiedByDefault || mSearchHintIcon == null)
        {
            return charsequence;
        } else
        {
            int i = (int)((double)mSearchSrcTextView.getTextSize() * 1.25D);
            mSearchHintIcon.setBounds(0, 0, i, i);
            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder("   ");
            spannablestringbuilder.setSpan(new ImageSpan(mSearchHintIcon), 1, 2, 33);
            spannablestringbuilder.append(charsequence);
            return spannablestringbuilder;
        }
    }

    private int getPreferredHeight()
    {
        return getContext().getResources().getDimensionPixelSize(0x105016b);
    }

    private int getPreferredWidth()
    {
        return getContext().getResources().getDimensionPixelSize(0x105016c);
    }

    private boolean hasVoiceSearch()
    {
        boolean flag = false;
        if(mSearchable == null || !mSearchable.getVoiceSearchEnabled()) goto _L2; else goto _L1
_L1:
        Intent intent = null;
        if(!mSearchable.getVoiceSearchLaunchWebSearch()) goto _L4; else goto _L3
_L3:
        intent = mVoiceWebSearchIntent;
_L5:
        if(intent != null)
        {
            if(getContext().getPackageManager().resolveActivity(intent, 0x10000) != null)
                flag = true;
            return flag;
        }
        break; /* Loop/switch isn't completed */
_L4:
        if(mSearchable.getVoiceSearchLaunchRecognizer())
            intent = mVoiceAppSearchIntent;
        if(true) goto _L5; else goto _L2
_L2:
        return false;
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

    private boolean isSubmitAreaEnabled()
    {
        boolean flag;
        if(mSubmitButtonEnabled || mVoiceButtonEnabled)
            flag = isIconified() ^ true;
        else
            flag = false;
        return flag;
    }

    private void launchIntent(Intent intent)
    {
        if(intent == null)
            return;
        getContext().startActivity(intent);
_L1:
        return;
        RuntimeException runtimeexception;
        runtimeexception;
        Log.e("SearchView", (new StringBuilder()).append("Failed launch activity: ").append(intent).toString(), runtimeexception);
          goto _L1
    }

    private void launchQuerySearch(int i, String s, String s1)
    {
        s = createIntent("android.intent.action.SEARCH", null, null, s1, i, s);
        getContext().startActivity(s);
    }

    private boolean launchSuggestion(int i, int j, String s)
    {
        Cursor cursor = mSuggestionsAdapter.getCursor();
        if(cursor != null && cursor.moveToPosition(i))
        {
            launchIntent(createIntentFromSuggestion(cursor, j, s));
            return true;
        } else
        {
            return false;
        }
    }

    private void onCloseClicked()
    {
        if(TextUtils.isEmpty(mSearchSrcTextView.getText()))
        {
            if(mIconifiedByDefault && (mOnCloseListener == null || mOnCloseListener.onClose() ^ true))
            {
                clearFocus();
                updateViewsVisibility(true);
            }
        } else
        {
            mSearchSrcTextView.setText("");
            mSearchSrcTextView.requestFocus();
            SearchAutoComplete._2D_wrap1(mSearchSrcTextView, true);
        }
    }

    private boolean onItemClicked(int i, int j, String s)
    {
        if(mOnSuggestionListener == null || mOnSuggestionListener.onSuggestionClick(i) ^ true)
        {
            launchSuggestion(i, 0, null);
            SearchAutoComplete._2D_wrap1(mSearchSrcTextView, false);
            dismissSuggestions();
            return true;
        } else
        {
            return false;
        }
    }

    private boolean onItemSelected(int i)
    {
        if(mOnSuggestionListener == null || mOnSuggestionListener.onSuggestionSelect(i) ^ true)
        {
            rewriteQueryFromSuggestion(i);
            return true;
        } else
        {
            return false;
        }
    }

    private void onSearchClicked()
    {
        updateViewsVisibility(false);
        mSearchSrcTextView.requestFocus();
        SearchAutoComplete._2D_wrap1(mSearchSrcTextView, true);
        if(mOnSearchClickListener != null)
            mOnSearchClickListener.onClick(this);
    }

    private void onSubmitQuery()
    {
        Editable editable = mSearchSrcTextView.getText();
        if(editable != null && TextUtils.getTrimmedLength(editable) > 0 && (mOnQueryChangeListener == null || mOnQueryChangeListener.onQueryTextSubmit(editable.toString()) ^ true))
        {
            if(mSearchable != null)
                launchQuerySearch(0, null, editable.toString());
            SearchAutoComplete._2D_wrap1(mSearchSrcTextView, false);
            dismissSuggestions();
        }
    }

    private boolean onSuggestionsKey(View view, int i, KeyEvent keyevent)
    {
        if(mSearchable == null)
            return false;
        if(mSuggestionsAdapter == null)
            return false;
        if(keyevent.getAction() == 0 && keyevent.hasNoModifiers())
        {
            while(i == 66 || i == 84 || i == 61) 
                return onItemClicked(mSearchSrcTextView.getListSelection(), 0, null);
            if(i == 21 || i == 22)
            {
                if(i == 21)
                    i = 0;
                else
                    i = mSearchSrcTextView.length();
                mSearchSrcTextView.setSelection(i);
                mSearchSrcTextView.setListSelection(0);
                mSearchSrcTextView.clearListSelection();
                mSearchSrcTextView.ensureImeVisible(true);
                return true;
            }
            if(i == 19 && mSearchSrcTextView.getListSelection() == 0)
                return false;
            view = mSearchable.findActionKey(i);
            if(view != null && (view.getSuggestActionMsg() != null || view.getSuggestActionMsgColumn() != null))
            {
                int j = mSearchSrcTextView.getListSelection();
                if(j != -1)
                {
                    keyevent = mSuggestionsAdapter.getCursor();
                    if(keyevent.moveToPosition(j))
                    {
                        view = getActionKeyMessage(keyevent, view);
                        if(view != null && view.length() > 0)
                            return onItemClicked(j, i, view);
                    }
                }
            }
        }
        return false;
    }

    private void onTextChanged(CharSequence charsequence)
    {
        Editable editable = mSearchSrcTextView.getText();
        mUserQuery = editable;
        boolean flag = TextUtils.isEmpty(editable) ^ true;
        updateSubmitButton(flag);
        updateVoiceButton(flag ^ true);
        updateCloseButton();
        updateSubmitArea();
        if(mOnQueryChangeListener != null && TextUtils.equals(charsequence, mOldQueryText) ^ true)
            mOnQueryChangeListener.onQueryTextChange(charsequence.toString());
        mOldQueryText = charsequence.toString();
    }

    private void onVoiceClicked()
    {
        Object obj;
        if(mSearchable == null)
            return;
        obj = mSearchable;
        if(!((SearchableInfo) (obj)).getVoiceSearchLaunchWebSearch()) goto _L2; else goto _L1
_L1:
        obj = createVoiceWebSearchIntent(mVoiceWebSearchIntent, ((SearchableInfo) (obj)));
        getContext().startActivity(((Intent) (obj)));
_L4:
        return;
_L2:
        if(!((SearchableInfo) (obj)).getVoiceSearchLaunchRecognizer()) goto _L4; else goto _L3
_L3:
        obj = createVoiceAppSearchIntent(mVoiceAppSearchIntent, ((SearchableInfo) (obj)));
        getContext().startActivity(((Intent) (obj)));
          goto _L4
        ActivityNotFoundException activitynotfoundexception;
        activitynotfoundexception;
        Log.w("SearchView", "Could not find voice search activity");
          goto _L4
    }

    private void postUpdateFocusedState()
    {
        post(mUpdateDrawableStateRunnable);
    }

    private void rewriteQueryFromSuggestion(int i)
    {
        Editable editable = mSearchSrcTextView.getText();
        Object obj = mSuggestionsAdapter.getCursor();
        if(obj == null)
            return;
        if(((Cursor) (obj)).moveToPosition(i))
        {
            obj = mSuggestionsAdapter.convertToString(((Cursor) (obj)));
            if(obj != null)
                setQuery(((CharSequence) (obj)));
            else
                setQuery(editable);
        } else
        {
            setQuery(editable);
        }
    }

    private void setQuery(CharSequence charsequence)
    {
        mSearchSrcTextView.setText(charsequence, true);
        SearchAutoComplete searchautocomplete = mSearchSrcTextView;
        int i;
        if(TextUtils.isEmpty(charsequence))
            i = 0;
        else
            i = charsequence.length();
        searchautocomplete.setSelection(i);
    }

    private void updateCloseButton()
    {
        boolean flag = TextUtils.isEmpty(mSearchSrcTextView.getText()) ^ true;
        int i;
        ImageView imageview;
        Drawable drawable;
        if(!flag)
        {
            if(mIconifiedByDefault)
                i = mExpandedInActionView ^ true;
            else
                i = 0;
        } else
        {
            i = 1;
        }
        imageview = mCloseButton;
        if(i != 0)
            i = 0;
        else
            i = 8;
        imageview.setVisibility(i);
        drawable = mCloseButton.getDrawable();
        if(drawable != null)
        {
            int ai[];
            if(flag)
                ai = ENABLED_STATE_SET;
            else
                ai = EMPTY_STATE_SET;
            drawable.setState(ai);
        }
    }

    private void updateFocusedState()
    {
        int ai[];
        Drawable drawable;
        if(mSearchSrcTextView.hasFocus())
            ai = FOCUSED_STATE_SET;
        else
            ai = EMPTY_STATE_SET;
        drawable = mSearchPlate.getBackground();
        if(drawable != null)
            drawable.setState(ai);
        drawable = mSubmitArea.getBackground();
        if(drawable != null)
            drawable.setState(ai);
        invalidate();
    }

    private void updateQueryHint()
    {
        CharSequence charsequence = getQueryHint();
        SearchAutoComplete searchautocomplete = mSearchSrcTextView;
        Object obj = charsequence;
        if(charsequence == null)
            obj = "";
        searchautocomplete.setHint(getDecoratedHint(((CharSequence) (obj))));
    }

    private void updateSearchAutoComplete()
    {
        boolean flag = true;
        mSearchSrcTextView.setDropDownAnimationStyle(0);
        mSearchSrcTextView.setThreshold(mSearchable.getSuggestThreshold());
        mSearchSrcTextView.setImeOptions(mSearchable.getImeOptions());
        int i = mSearchable.getInputType();
        int j = i;
        if((i & 0xf) == 1)
        {
            i &= 0xfffeffff;
            j = i;
            if(mSearchable.getSuggestAuthority() != null)
                j = i | 0x10000 | 0x80000;
        }
        mSearchSrcTextView.setInputType(j);
        if(mSuggestionsAdapter != null)
            mSuggestionsAdapter.changeCursor(null);
        if(mSearchable.getSuggestAuthority() != null)
        {
            mSuggestionsAdapter = new SuggestionsAdapter(getContext(), this, mSearchable, mOutsideDrawablesCache);
            mSearchSrcTextView.setAdapter(mSuggestionsAdapter);
            SuggestionsAdapter suggestionsadapter = (SuggestionsAdapter)mSuggestionsAdapter;
            byte byte0 = flag;
            if(mQueryRefinement)
                byte0 = 2;
            suggestionsadapter.setQueryRefinement(byte0);
        }
    }

    private void updateSubmitArea()
    {
        byte byte1;
label0:
        {
            byte byte0 = 8;
            byte1 = byte0;
            if(!isSubmitAreaEnabled())
                break label0;
            if(mGoButton.getVisibility() != 0)
            {
                byte1 = byte0;
                if(mVoiceButton.getVisibility() != 0)
                    break label0;
            }
            byte1 = 0;
        }
        mSubmitArea.setVisibility(byte1);
    }

    private void updateSubmitButton(boolean flag)
    {
        byte byte1;
label0:
        {
            byte byte0 = 8;
            byte1 = byte0;
            if(!mSubmitButtonEnabled)
                break label0;
            byte1 = byte0;
            if(!isSubmitAreaEnabled())
                break label0;
            byte1 = byte0;
            if(!hasFocus())
                break label0;
            if(!flag)
            {
                byte1 = byte0;
                if(!(mVoiceButtonEnabled ^ true))
                    break label0;
            }
            byte1 = 0;
        }
        mGoButton.setVisibility(byte1);
    }

    private void updateViewsVisibility(boolean flag)
    {
        mIconified = flag;
        int i;
        boolean flag1;
        View view;
        if(flag)
            i = 0;
        else
            i = 8;
        flag1 = TextUtils.isEmpty(mSearchSrcTextView.getText()) ^ true;
        mSearchButton.setVisibility(i);
        updateSubmitButton(flag1);
        view = mSearchEditFrame;
        if(flag)
            i = 8;
        else
            i = 0;
        view.setVisibility(i);
        if(mCollapsedIcon.getDrawable() == null || mIconifiedByDefault)
            i = 8;
        else
            i = 0;
        mCollapsedIcon.setVisibility(i);
        updateCloseButton();
        updateVoiceButton(flag1 ^ true);
        updateSubmitArea();
    }

    private void updateVoiceButton(boolean flag)
    {
        byte byte0 = 8;
        byte byte1 = byte0;
        if(mVoiceButtonEnabled)
        {
            byte1 = byte0;
            if(isIconified() ^ true)
            {
                byte1 = byte0;
                if(flag)
                {
                    byte1 = 0;
                    mGoButton.setVisibility(8);
                }
            }
        }
        mVoiceButton.setVisibility(byte1);
    }

    public void clearFocus()
    {
        mClearingFocus = true;
        super.clearFocus();
        mSearchSrcTextView.clearFocus();
        SearchAutoComplete._2D_wrap1(mSearchSrcTextView, false);
        mClearingFocus = false;
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/SearchView.getName();
    }

    public int getImeOptions()
    {
        return mSearchSrcTextView.getImeOptions();
    }

    public int getInputType()
    {
        return mSearchSrcTextView.getInputType();
    }

    public int getMaxWidth()
    {
        return mMaxWidth;
    }

    public CharSequence getQuery()
    {
        return mSearchSrcTextView.getText();
    }

    public CharSequence getQueryHint()
    {
        CharSequence charsequence;
        if(mQueryHint != null)
            charsequence = mQueryHint;
        else
        if(mSearchable != null && mSearchable.getHintId() != 0)
            charsequence = getContext().getText(mSearchable.getHintId());
        else
            charsequence = mDefaultQueryHint;
        return charsequence;
    }

    int getSuggestionCommitIconResId()
    {
        return mSuggestionCommitIconResId;
    }

    int getSuggestionRowLayout()
    {
        return mSuggestionRowLayout;
    }

    public CursorAdapter getSuggestionsAdapter()
    {
        return mSuggestionsAdapter;
    }

    public boolean isIconfiedByDefault()
    {
        return mIconifiedByDefault;
    }

    public boolean isIconified()
    {
        return mIconified;
    }

    public boolean isQueryRefinementEnabled()
    {
        return mQueryRefinement;
    }

    public boolean isSubmitButtonEnabled()
    {
        return mSubmitButtonEnabled;
    }

    public void onActionViewCollapsed()
    {
        setQuery("", false);
        clearFocus();
        updateViewsVisibility(true);
        mSearchSrcTextView.setImeOptions(mCollapsedImeOptions);
        mExpandedInActionView = false;
    }

    public void onActionViewExpanded()
    {
        if(mExpandedInActionView)
        {
            return;
        } else
        {
            mExpandedInActionView = true;
            mCollapsedImeOptions = mSearchSrcTextView.getImeOptions();
            mSearchSrcTextView.setImeOptions(mCollapsedImeOptions | 0x2000000);
            mSearchSrcTextView.setText("");
            setIconified(false);
            return;
        }
    }

    protected void onDetachedFromWindow()
    {
        removeCallbacks(mUpdateDrawableStateRunnable);
        post(mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(mSearchable == null)
            return false;
        android.app.SearchableInfo.ActionKeyInfo actionkeyinfo = mSearchable.findActionKey(i);
        if(actionkeyinfo != null && actionkeyinfo.getQueryActionMsg() != null)
        {
            launchQuerySearch(i, actionkeyinfo.getQueryActionMsg(), mSearchSrcTextView.getText().toString());
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if(flag)
        {
            getChildBoundsWithinSearchView(mSearchSrcTextView, mSearchSrcTextViewBounds);
            mSearchSrtTextViewBoundsExpanded.set(mSearchSrcTextViewBounds.left, 0, mSearchSrcTextViewBounds.right, l - j);
            if(mTouchDelegate == null)
            {
                mTouchDelegate = new UpdatableTouchDelegate(mSearchSrtTextViewBoundsExpanded, mSearchSrcTextViewBounds, mSearchSrcTextView);
                setTouchDelegate(mTouchDelegate);
            } else
            {
                mTouchDelegate.setBounds(mSearchSrtTextViewBoundsExpanded, mSearchSrcTextViewBounds);
            }
        }
    }

    protected void onMeasure(int i, int j)
    {
        int k;
        int l;
        if(isIconified())
        {
            super.onMeasure(i, j);
            return;
        }
        k = android.view.View.MeasureSpec.getMode(i);
        l = android.view.View.MeasureSpec.getSize(i);
        k;
        JVM INSTR lookupswitch 3: default 60
    //                   -2147483648: 123
    //                   0: 179
    //                   1073741824: 156;
           goto _L1 _L2 _L3 _L4
_L1:
        i = l;
_L8:
        l = android.view.View.MeasureSpec.getMode(j);
        j = android.view.View.MeasureSpec.getSize(j);
        l;
        JVM INSTR lookupswitch 2: default 104
    //                   -2147483648: 202
    //                   0: 214;
           goto _L5 _L6 _L7
_L5:
        break; /* Loop/switch isn't completed */
_L7:
        break MISSING_BLOCK_LABEL_214;
_L9:
        super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000), android.view.View.MeasureSpec.makeMeasureSpec(j, 0x40000000));
        return;
_L2:
        if(mMaxWidth > 0)
            i = Math.min(mMaxWidth, l);
        else
            i = Math.min(getPreferredWidth(), l);
          goto _L8
_L4:
        i = l;
        if(mMaxWidth > 0)
            i = Math.min(mMaxWidth, l);
          goto _L8
_L3:
        if(mMaxWidth > 0)
            i = mMaxWidth;
        else
            i = getPreferredWidth();
          goto _L8
_L6:
        j = Math.min(getPreferredHeight(), j);
          goto _L9
        j = getPreferredHeight();
          goto _L9
    }

    void onQueryRefine(CharSequence charsequence)
    {
        setQuery(charsequence);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        updateViewsVisibility(((SavedState) (parcelable)).isIconified);
        requestLayout();
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.isIconified = isIconified();
        return savedstate;
    }

    void onTextFocusChanged()
    {
        updateViewsVisibility(isIconified());
        postUpdateFocusedState();
        if(mSearchSrcTextView.hasFocus())
            forceSuggestionQuery();
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        postUpdateFocusedState();
    }

    public boolean requestFocus(int i, Rect rect)
    {
        if(mClearingFocus)
            return false;
        if(!isFocusable())
            return false;
        if(!isIconified())
        {
            boolean flag = mSearchSrcTextView.requestFocus(i, rect);
            if(flag)
                updateViewsVisibility(false);
            return flag;
        } else
        {
            return super.requestFocus(i, rect);
        }
    }

    public void setAppSearchData(Bundle bundle)
    {
        mAppSearchData = bundle;
    }

    public void setIconified(boolean flag)
    {
        if(flag)
            onCloseClicked();
        else
            onSearchClicked();
    }

    public void setIconifiedByDefault(boolean flag)
    {
        if(mIconifiedByDefault == flag)
        {
            return;
        } else
        {
            mIconifiedByDefault = flag;
            updateViewsVisibility(flag);
            updateQueryHint();
            return;
        }
    }

    public void setImeOptions(int i)
    {
        mSearchSrcTextView.setImeOptions(i);
    }

    public void setInputType(int i)
    {
        mSearchSrcTextView.setInputType(i);
    }

    public void setMaxWidth(int i)
    {
        mMaxWidth = i;
        requestLayout();
    }

    public void setOnCloseListener(OnCloseListener oncloselistener)
    {
        mOnCloseListener = oncloselistener;
    }

    public void setOnQueryTextFocusChangeListener(android.view.View.OnFocusChangeListener onfocuschangelistener)
    {
        mOnQueryTextFocusChangeListener = onfocuschangelistener;
    }

    public void setOnQueryTextListener(OnQueryTextListener onquerytextlistener)
    {
        mOnQueryChangeListener = onquerytextlistener;
    }

    public void setOnSearchClickListener(android.view.View.OnClickListener onclicklistener)
    {
        mOnSearchClickListener = onclicklistener;
    }

    public void setOnSuggestionListener(OnSuggestionListener onsuggestionlistener)
    {
        mOnSuggestionListener = onsuggestionlistener;
    }

    public void setQuery(CharSequence charsequence, boolean flag)
    {
        mSearchSrcTextView.setText(charsequence);
        if(charsequence != null)
        {
            mSearchSrcTextView.setSelection(mSearchSrcTextView.length());
            mUserQuery = charsequence;
        }
        if(flag && TextUtils.isEmpty(charsequence) ^ true)
            onSubmitQuery();
    }

    public void setQueryHint(CharSequence charsequence)
    {
        mQueryHint = charsequence;
        updateQueryHint();
    }

    public void setQueryRefinementEnabled(boolean flag)
    {
        mQueryRefinement = flag;
        if(mSuggestionsAdapter instanceof SuggestionsAdapter)
        {
            SuggestionsAdapter suggestionsadapter = (SuggestionsAdapter)mSuggestionsAdapter;
            byte byte0;
            if(flag)
                byte0 = 2;
            else
                byte0 = 1;
            suggestionsadapter.setQueryRefinement(byte0);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableinfo)
    {
        mSearchable = searchableinfo;
        if(mSearchable != null)
        {
            updateSearchAutoComplete();
            updateQueryHint();
        }
        mVoiceButtonEnabled = hasVoiceSearch();
        if(mVoiceButtonEnabled)
            mSearchSrcTextView.setPrivateImeOptions("nm");
        updateViewsVisibility(isIconified());
    }

    public void setSubmitButtonEnabled(boolean flag)
    {
        mSubmitButtonEnabled = flag;
        updateViewsVisibility(isIconified());
    }

    public void setSuggestionsAdapter(CursorAdapter cursoradapter)
    {
        mSuggestionsAdapter = cursoradapter;
        mSearchSrcTextView.setAdapter(mSuggestionsAdapter);
    }

    private static final boolean DBG = false;
    private static final String IME_OPTION_NO_MICROPHONE = "nm";
    private static final String LOG_TAG = "SearchView";
    private Bundle mAppSearchData;
    private boolean mClearingFocus;
    private final ImageView mCloseButton;
    private final ImageView mCollapsedIcon;
    private int mCollapsedImeOptions;
    private final CharSequence mDefaultQueryHint;
    private final View mDropDownAnchor;
    private boolean mExpandedInActionView;
    private final ImageView mGoButton;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    private int mMaxWidth;
    private CharSequence mOldQueryText;
    private final android.view.View.OnClickListener mOnClickListener;
    private OnCloseListener mOnCloseListener;
    private final TextView.OnEditorActionListener mOnEditorActionListener;
    private final AdapterView.OnItemClickListener mOnItemClickListener;
    private final AdapterView.OnItemSelectedListener mOnItemSelectedListener;
    private OnQueryTextListener mOnQueryChangeListener;
    private android.view.View.OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private android.view.View.OnClickListener mOnSearchClickListener;
    private OnSuggestionListener mOnSuggestionListener;
    private final WeakHashMap mOutsideDrawablesCache;
    private CharSequence mQueryHint;
    private boolean mQueryRefinement;
    private Runnable mReleaseCursorRunnable = new Runnable() {

        public void run()
        {
            if(SearchView._2D_get6(SearchView.this) != null && (SearchView._2D_get6(SearchView.this) instanceof SuggestionsAdapter))
                SearchView._2D_get6(SearchView.this).changeCursor(null);
        }

        final SearchView this$0;

            
            {
                this$0 = SearchView.this;
                super();
            }
    }
;
    private final ImageView mSearchButton;
    private final View mSearchEditFrame;
    private final Drawable mSearchHintIcon;
    private final View mSearchPlate;
    private final SearchAutoComplete mSearchSrcTextView;
    private Rect mSearchSrcTextViewBounds;
    private Rect mSearchSrtTextViewBoundsExpanded;
    private SearchableInfo mSearchable;
    private final View mSubmitArea;
    private boolean mSubmitButtonEnabled;
    private final int mSuggestionCommitIconResId;
    private final int mSuggestionRowLayout;
    private CursorAdapter mSuggestionsAdapter;
    private int mTemp[];
    private int mTemp2[];
    android.view.View.OnKeyListener mTextKeyListener = new android.view.View.OnKeyListener() {

        public boolean onKey(View view, int k, KeyEvent keyevent)
        {
            if(SearchView._2D_get5(SearchView.this) == null)
                return false;
            if(SearchView._2D_get4(SearchView.this).isPopupShowing() && SearchView._2D_get4(SearchView.this).getListSelection() != -1)
                return SearchView._2D_wrap2(SearchView.this, view, k, keyevent);
            if(!SearchAutoComplete._2D_wrap0(SearchView._2D_get4(SearchView.this)) && keyevent.hasNoModifiers())
            {
                if(keyevent.getAction() == 1 && k == 66)
                {
                    view.cancelLongPress();
                    SearchView._2D_wrap5(SearchView.this, 0, null, SearchView._2D_get4(SearchView.this).getText().toString());
                    return true;
                }
                if(keyevent.getAction() == 0)
                {
                    view = SearchView._2D_get5(SearchView.this).findActionKey(k);
                    if(view != null && view.getQueryActionMsg() != null)
                    {
                        SearchView._2D_wrap5(SearchView.this, k, view.getQueryActionMsg(), SearchView._2D_get4(SearchView.this).getText().toString());
                        return true;
                    }
                }
            }
            return false;
        }

        final SearchView this$0;

            
            {
                this$0 = SearchView.this;
                super();
            }
    }
;
    private TextWatcher mTextWatcher = new TextWatcher() {

        public void afterTextChanged(Editable editable)
        {
        }

        public void beforeTextChanged(CharSequence charsequence, int k, int l, int i1)
        {
        }

        public void onTextChanged(CharSequence charsequence, int k, int l, int i1)
        {
            SearchView._2D_wrap9(SearchView.this, charsequence);
        }

        final SearchView this$0;

            
            {
                this$0 = SearchView.this;
                super();
            }
    }
;
    private UpdatableTouchDelegate mTouchDelegate;
    private Runnable mUpdateDrawableStateRunnable = new Runnable() {

        public void run()
        {
            SearchView._2D_wrap11(SearchView.this);
        }

        final SearchView this$0;

            
            {
                this$0 = SearchView.this;
                super();
            }
    }
;
    private CharSequence mUserQuery;
    private final Intent mVoiceAppSearchIntent;
    private final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    private final Intent mVoiceWebSearchIntent;
}
