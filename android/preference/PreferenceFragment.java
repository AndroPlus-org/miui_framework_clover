// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.*;
import android.text.TextUtils;
import android.view.*;
import android.widget.ListView;
import android.widget.TextView;

// Referenced classes of package android.preference:
//            PreferenceScreen, PreferenceManager, Preference

public abstract class PreferenceFragment extends Fragment
    implements PreferenceManager.OnPreferenceTreeClickListener
{
    public static interface OnPreferenceStartFragmentCallback
    {

        public abstract boolean onPreferenceStartFragment(PreferenceFragment preferencefragment, Preference preference);
    }


    static ListView _2D_get0(PreferenceFragment preferencefragment)
    {
        return preferencefragment.mList;
    }

    static void _2D_wrap0(PreferenceFragment preferencefragment)
    {
        preferencefragment.bindPreferences();
    }

    public PreferenceFragment()
    {
        mLayoutResId = 0x10900ca;
        mHandler = new Handler() {

            public void handleMessage(Message message)
            {
                message.what;
                JVM INSTR tableswitch 1 1: default 24
            //                           1 25;
                   goto _L1 _L2
_L1:
                return;
_L2:
                PreferenceFragment._2D_wrap0(PreferenceFragment.this);
                if(true) goto _L1; else goto _L3
_L3:
            }

            final PreferenceFragment this$0;

            
            {
                this$0 = PreferenceFragment.this;
                super();
            }
        }
;
        mListOnKeyListener = new android.view.View.OnKeyListener() {

            public boolean onKey(View view, int i, KeyEvent keyevent)
            {
                view = ((View) (PreferenceFragment._2D_get0(PreferenceFragment.this).getSelectedItem()));
                if(view instanceof Preference)
                {
                    View view1 = PreferenceFragment._2D_get0(PreferenceFragment.this).getSelectedView();
                    return ((Preference)view).onKey(view1, i, keyevent);
                } else
                {
                    return false;
                }
            }

            final PreferenceFragment this$0;

            
            {
                this$0 = PreferenceFragment.this;
                super();
            }
        }
;
    }

    private void bindPreferences()
    {
        PreferenceScreen preferencescreen = getPreferenceScreen();
        if(preferencescreen != null)
        {
            View view = getView();
            if(view != null)
            {
                View view1 = view.findViewById(0x1020016);
                if(view1 instanceof TextView)
                {
                    CharSequence charsequence = preferencescreen.getTitle();
                    if(TextUtils.isEmpty(charsequence))
                    {
                        view1.setVisibility(8);
                    } else
                    {
                        ((TextView)view1).setText(charsequence);
                        view1.setVisibility(0);
                    }
                }
            }
            preferencescreen.bind(getListView());
        }
        onBindPreferences();
    }

    private void ensureList()
    {
        if(mList != null)
            return;
        View view = getView();
        if(view == null)
            throw new IllegalStateException("Content view not yet created");
        view = view.findViewById(0x102000a);
        if(!(view instanceof ListView))
            throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
        mList = (ListView)view;
        if(mList == null)
        {
            throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
        } else
        {
            mList.setOnKeyListener(mListOnKeyListener);
            mHandler.post(mRequestFocus);
            return;
        }
    }

    private void postBindPreferences()
    {
        if(mHandler.hasMessages(1))
        {
            return;
        } else
        {
            mHandler.obtainMessage(1).sendToTarget();
            return;
        }
    }

    private void requirePreferenceManager()
    {
        if(mPreferenceManager == null)
            throw new RuntimeException("This should be called after super.onCreate.");
        else
            return;
    }

    public void addPreferencesFromIntent(Intent intent)
    {
        requirePreferenceManager();
        setPreferenceScreen(mPreferenceManager.inflateFromIntent(intent, getPreferenceScreen()));
    }

    public void addPreferencesFromResource(int i)
    {
        requirePreferenceManager();
        setPreferenceScreen(mPreferenceManager.inflateFromResource(getActivity(), i, getPreferenceScreen()));
    }

    public Preference findPreference(CharSequence charsequence)
    {
        if(mPreferenceManager == null)
            return null;
        else
            return mPreferenceManager.findPreference(charsequence);
    }

    public ListView getListView()
    {
        ensureList();
        return mList;
    }

    public PreferenceManager getPreferenceManager()
    {
        return mPreferenceManager;
    }

    public PreferenceScreen getPreferenceScreen()
    {
        return mPreferenceManager.getPreferenceScreen();
    }

    public boolean hasListView()
    {
        if(mList != null)
            return true;
        View view = getView();
        if(view == null)
            return false;
        view = view.findViewById(0x102000a);
        if(!(view instanceof ListView))
            return false;
        mList = (ListView)view;
        return mList != null;
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        if(mHavePrefs)
            bindPreferences();
        mInitDone = true;
        if(bundle != null)
        {
            bundle = bundle.getBundle("android:preferences");
            if(bundle != null)
            {
                PreferenceScreen preferencescreen = getPreferenceScreen();
                if(preferencescreen != null)
                    preferencescreen.restoreHierarchyState(bundle);
            }
        }
    }

    public void onActivityResult(int i, int j, Intent intent)
    {
        super.onActivityResult(i, j, intent);
        mPreferenceManager.dispatchActivityResult(i, j, intent);
    }

    protected void onBindPreferences()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mPreferenceManager = new PreferenceManager(getActivity(), 100);
        mPreferenceManager.setFragment(this);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        bundle = getActivity().obtainStyledAttributes(null, com.android.internal.R.styleable.PreferenceFragment, 0x1010506, 0);
        mLayoutResId = bundle.getResourceId(0, mLayoutResId);
        bundle.recycle();
        return layoutinflater.inflate(mLayoutResId, viewgroup, false);
    }

    public void onDestroy()
    {
        super.onDestroy();
        mPreferenceManager.dispatchActivityDestroy();
    }

    public void onDestroyView()
    {
        if(mList != null)
            mList.setOnKeyListener(null);
        mList = null;
        mHandler.removeCallbacks(mRequestFocus);
        mHandler.removeMessages(1);
        super.onDestroyView();
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferencescreen, Preference preference)
    {
        if(preference.getFragment() != null && (getActivity() instanceof OnPreferenceStartFragmentCallback))
            return ((OnPreferenceStartFragmentCallback)getActivity()).onPreferenceStartFragment(this, preference);
        else
            return false;
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        PreferenceScreen preferencescreen = getPreferenceScreen();
        if(preferencescreen != null)
        {
            Bundle bundle1 = new Bundle();
            preferencescreen.saveHierarchyState(bundle1);
            bundle.putBundle("android:preferences", bundle1);
        }
    }

    public void onStart()
    {
        super.onStart();
        mPreferenceManager.setOnPreferenceTreeClickListener(this);
    }

    public void onStop()
    {
        super.onStop();
        mPreferenceManager.dispatchActivityStop();
        mPreferenceManager.setOnPreferenceTreeClickListener(null);
    }

    protected void onUnbindPreferences()
    {
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        bundle = getActivity().obtainStyledAttributes(null, com.android.internal.R.styleable.PreferenceFragment, 0x1010506, 0);
        view = (ListView)view.findViewById(0x102000a);
        if(view != null && bundle.hasValueOrEmpty(1))
            view.setDivider(bundle.getDrawable(1));
        bundle.recycle();
    }

    public void setPreferenceScreen(PreferenceScreen preferencescreen)
    {
        if(mPreferenceManager.setPreferences(preferencescreen) && preferencescreen != null)
        {
            onUnbindPreferences();
            mHavePrefs = true;
            if(mInitDone)
                postBindPreferences();
        }
    }

    private static final int FIRST_REQUEST_CODE = 100;
    private static final int MSG_BIND_PREFERENCES = 1;
    private static final String PREFERENCES_TAG = "android:preferences";
    private Handler mHandler;
    private boolean mHavePrefs;
    private boolean mInitDone;
    private int mLayoutResId;
    private ListView mList;
    private android.view.View.OnKeyListener mListOnKeyListener;
    private PreferenceManager mPreferenceManager;
    private final Runnable mRequestFocus = new Runnable() {

        public void run()
        {
            PreferenceFragment._2D_get0(PreferenceFragment.this).focusableViewAvailable(PreferenceFragment._2D_get0(PreferenceFragment.this));
        }

        final PreferenceFragment this$0;

            
            {
                this$0 = PreferenceFragment.this;
                super();
            }
    }
;
}
