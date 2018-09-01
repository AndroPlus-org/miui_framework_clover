// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.*;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.util.CharSequences;
import java.util.*;

// Referenced classes of package android.preference:
//            PreferenceManager, PreferenceDataStore, PreferenceGroup, PreferenceScreen

public class Preference
    implements Comparable
{
    public static class BaseSavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public BaseSavedState createFromParcel(Parcel parcel)
            {
                return new BaseSavedState(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public BaseSavedState[] newArray(int i)
            {
                return new BaseSavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;


        public BaseSavedState(Parcel parcel)
        {
            super(parcel);
        }

        public BaseSavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }

    static interface OnPreferenceChangeInternalListener
    {

        public abstract void onPreferenceChange(Preference preference);

        public abstract void onPreferenceHierarchyChange(Preference preference);
    }

    public static interface OnPreferenceChangeListener
    {

        public abstract boolean onPreferenceChange(Preference preference, Object obj);
    }

    public static interface OnPreferenceClickListener
    {

        public abstract boolean onPreferenceClick(Preference preference);
    }


    public Preference(Context context)
    {
        this(context, null);
    }

    public Preference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101008e);
    }

    public Preference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public Preference(Context context, AttributeSet attributeset, int i, int j)
    {
        mOrder = 0x7fffffff;
        mEnabled = true;
        mSelectable = true;
        mPersistent = true;
        mDependencyMet = true;
        mParentDependencyMet = true;
        mRecycleEnabled = true;
        mSingleLineTitle = true;
        mShouldDisableView = true;
        mLayoutResId = 0x10900b7;
        mContext = context;
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.Preference, i, j);
        i = context.getIndexCount() - 1;
_L20:
        if(i < 0)
            break MISSING_BLOCK_LABEL_455;
        j = context.getIndex(i);
        j;
        JVM INSTR tableswitch 0 16: default 176
    //                   0 182
    //                   1 338
    //                   2 310
    //                   3 276
    //                   4 209
    //                   5 324
    //                   6 196
    //                   7 233
    //                   8 246
    //                   9 293
    //                   10 355
    //                   11 368
    //                   12 382
    //                   13 263
    //                   14 399
    //                   15 416
    //                   16 438;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18
_L18:
        break MISSING_BLOCK_LABEL_438;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L21:
        i--;
        if(true) goto _L20; else goto _L19
_L19:
        mIconResId = context.getResourceId(j, 0);
          goto _L21
_L8:
        mKey = context.getString(j);
          goto _L21
_L6:
        mTitleRes = context.getResourceId(j, 0);
        mTitle = context.getText(j);
          goto _L21
_L9:
        mSummary = context.getText(j);
          goto _L21
_L10:
        mOrder = context.getInt(j, mOrder);
          goto _L21
_L15:
        mFragment = context.getString(j);
          goto _L21
_L5:
        mLayoutResId = context.getResourceId(j, mLayoutResId);
          goto _L21
_L11:
        mWidgetLayoutResId = context.getResourceId(j, mWidgetLayoutResId);
          goto _L21
_L4:
        mEnabled = context.getBoolean(j, true);
          goto _L21
_L7:
        mSelectable = context.getBoolean(j, true);
          goto _L21
_L3:
        mPersistent = context.getBoolean(j, mPersistent);
          goto _L21
_L12:
        mDependencyKey = context.getString(j);
          goto _L21
_L13:
        mDefaultValue = onGetDefaultValue(context, j);
          goto _L21
_L14:
        mShouldDisableView = context.getBoolean(j, mShouldDisableView);
          goto _L21
_L16:
        mRecycleEnabled = context.getBoolean(j, mRecycleEnabled);
          goto _L21
_L17:
        mSingleLineTitle = context.getBoolean(j, mSingleLineTitle);
        mHasSingleLineTitleAttr = true;
          goto _L21
        mIconSpaceReserved = context.getBoolean(j, mIconSpaceReserved);
          goto _L21
        context.recycle();
        return;
    }

    private void dispatchSetInitialValue()
    {
        if(getPreferenceDataStore() != null)
        {
            onSetInitialValue(true, mDefaultValue);
            return;
        }
        if(!shouldPersist() || getSharedPreferences().contains(mKey) ^ true)
        {
            if(mDefaultValue != null)
                onSetInitialValue(false, mDefaultValue);
        } else
        {
            onSetInitialValue(true, null);
        }
    }

    private void registerDependency()
    {
        if(TextUtils.isEmpty(mDependencyKey))
            return;
        Preference preference = findPreferenceInHierarchy(mDependencyKey);
        if(preference != null)
        {
            preference.registerDependent(this);
            return;
        } else
        {
            throw new IllegalStateException((new StringBuilder()).append("Dependency \"").append(mDependencyKey).append("\" not found for preference \"").append(mKey).append("\" (title: \"").append(mTitle).append("\"").toString());
        }
    }

    private void registerDependent(Preference preference)
    {
        if(mDependents == null)
            mDependents = new ArrayList();
        mDependents.add(preference);
        preference.onDependencyChanged(this, shouldDisableDependents());
    }

    private void setEnabledStateOnViews(View view, boolean flag)
    {
        view.setEnabled(flag);
        if(view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            for(int i = view.getChildCount() - 1; i >= 0; i--)
                setEnabledStateOnViews(view.getChildAt(i), flag);

        }
    }

    private void tryCommit(android.content.SharedPreferences.Editor editor)
    {
        if(!mPreferenceManager.shouldCommit())
            break MISSING_BLOCK_LABEL_16;
        editor.apply();
_L1:
        return;
        AbstractMethodError abstractmethoderror;
        abstractmethoderror;
        editor.commit();
          goto _L1
    }

    private void unregisterDependency()
    {
        if(mDependencyKey != null)
        {
            Preference preference = findPreferenceInHierarchy(mDependencyKey);
            if(preference != null)
                preference.unregisterDependent(this);
        }
    }

    private void unregisterDependent(Preference preference)
    {
        if(mDependents != null)
            mDependents.remove(preference);
    }

    void assignParent(PreferenceGroup preferencegroup)
    {
        mParentGroup = preferencegroup;
    }

    protected boolean callChangeListener(Object obj)
    {
        boolean flag;
        if(mOnChangeListener != null)
            flag = mOnChangeListener.onPreferenceChange(this, obj);
        else
            flag = true;
        return flag;
    }

    public int compareTo(Preference preference)
    {
        if(mOrder != preference.mOrder)
            return mOrder - preference.mOrder;
        if(mTitle == preference.mTitle)
            return 0;
        if(mTitle == null)
            return 1;
        if(preference.mTitle == null)
            return -1;
        else
            return CharSequences.compareToIgnoreCase(mTitle, preference.mTitle);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Preference)obj);
    }

    void dispatchRestoreInstanceState(Bundle bundle)
    {
        if(hasKey())
        {
            bundle = bundle.getParcelable(mKey);
            if(bundle != null)
            {
                mBaseMethodCalled = false;
                onRestoreInstanceState(bundle);
                if(!mBaseMethodCalled)
                    throw new IllegalStateException("Derived class did not call super.onRestoreInstanceState()");
            }
        }
    }

    void dispatchSaveInstanceState(Bundle bundle)
    {
        if(hasKey())
        {
            mBaseMethodCalled = false;
            Parcelable parcelable = onSaveInstanceState();
            if(!mBaseMethodCalled)
                throw new IllegalStateException("Derived class did not call super.onSaveInstanceState()");
            if(parcelable != null)
                bundle.putParcelable(mKey, parcelable);
        }
    }

    protected Preference findPreferenceInHierarchy(String s)
    {
        if(TextUtils.isEmpty(s) || mPreferenceManager == null)
            return null;
        else
            return mPreferenceManager.findPreference(s);
    }

    public Context getContext()
    {
        return mContext;
    }

    public String getDependency()
    {
        return mDependencyKey;
    }

    public android.content.SharedPreferences.Editor getEditor()
    {
        if(mPreferenceManager == null || getPreferenceDataStore() != null)
            return null;
        else
            return mPreferenceManager.getEditor();
    }

    public Bundle getExtras()
    {
        if(mExtras == null)
            mExtras = new Bundle();
        return mExtras;
    }

    StringBuilder getFilterableStringBuilder()
    {
        StringBuilder stringbuilder = new StringBuilder();
        CharSequence charsequence = getTitle();
        if(!TextUtils.isEmpty(charsequence))
            stringbuilder.append(charsequence).append(' ');
        charsequence = getSummary();
        if(!TextUtils.isEmpty(charsequence))
            stringbuilder.append(charsequence).append(' ');
        if(stringbuilder.length() > 0)
            stringbuilder.setLength(stringbuilder.length() - 1);
        return stringbuilder;
    }

    public String getFragment()
    {
        return mFragment;
    }

    public Drawable getIcon()
    {
        if(mIcon == null && mIconResId != 0)
            mIcon = getContext().getDrawable(mIconResId);
        return mIcon;
    }

    long getId()
    {
        return mId;
    }

    public Intent getIntent()
    {
        return mIntent;
    }

    public String getKey()
    {
        return mKey;
    }

    public int getLayoutResource()
    {
        return mLayoutResId;
    }

    public OnPreferenceChangeListener getOnPreferenceChangeListener()
    {
        return mOnChangeListener;
    }

    public OnPreferenceClickListener getOnPreferenceClickListener()
    {
        return mOnClickListener;
    }

    public int getOrder()
    {
        return mOrder;
    }

    public PreferenceGroup getParent()
    {
        return mParentGroup;
    }

    protected boolean getPersistedBoolean(boolean flag)
    {
        if(!shouldPersist())
            return flag;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
            return preferencedatastore.getBoolean(mKey, flag);
        else
            return mPreferenceManager.getSharedPreferences().getBoolean(mKey, flag);
    }

    protected float getPersistedFloat(float f)
    {
        if(!shouldPersist())
            return f;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
            return preferencedatastore.getFloat(mKey, f);
        else
            return mPreferenceManager.getSharedPreferences().getFloat(mKey, f);
    }

    protected int getPersistedInt(int i)
    {
        if(!shouldPersist())
            return i;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
            return preferencedatastore.getInt(mKey, i);
        else
            return mPreferenceManager.getSharedPreferences().getInt(mKey, i);
    }

    protected long getPersistedLong(long l)
    {
        if(!shouldPersist())
            return l;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
            return preferencedatastore.getLong(mKey, l);
        else
            return mPreferenceManager.getSharedPreferences().getLong(mKey, l);
    }

    protected String getPersistedString(String s)
    {
        if(!shouldPersist())
            return s;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
            return preferencedatastore.getString(mKey, s);
        else
            return mPreferenceManager.getSharedPreferences().getString(mKey, s);
    }

    public Set getPersistedStringSet(Set set)
    {
        if(!shouldPersist())
            return set;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
            return preferencedatastore.getStringSet(mKey, set);
        else
            return mPreferenceManager.getSharedPreferences().getStringSet(mKey, set);
    }

    public PreferenceDataStore getPreferenceDataStore()
    {
        if(mPreferenceDataStore != null)
            return mPreferenceDataStore;
        if(mPreferenceManager != null)
            return mPreferenceManager.getPreferenceDataStore();
        else
            return null;
    }

    public PreferenceManager getPreferenceManager()
    {
        return mPreferenceManager;
    }

    public SharedPreferences getSharedPreferences()
    {
        if(mPreferenceManager == null || getPreferenceDataStore() != null)
            return null;
        else
            return mPreferenceManager.getSharedPreferences();
    }

    public boolean getShouldDisableView()
    {
        return mShouldDisableView;
    }

    public CharSequence getSummary()
    {
        return mSummary;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public int getTitleRes()
    {
        return mTitleRes;
    }

    public View getView(View view, ViewGroup viewgroup)
    {
        View view1 = view;
        if(view == null)
            view1 = onCreateView(viewgroup);
        onBindView(view1);
        return view1;
    }

    public int getWidgetLayoutResource()
    {
        return mWidgetLayoutResId;
    }

    public boolean hasKey()
    {
        return TextUtils.isEmpty(mKey) ^ true;
    }

    public boolean isEnabled()
    {
        boolean flag;
        if(mEnabled && mDependencyMet)
            flag = mParentDependencyMet;
        else
            flag = false;
        return flag;
    }

    public boolean isIconSpaceReserved()
    {
        return mIconSpaceReserved;
    }

    public boolean isPersistent()
    {
        return mPersistent;
    }

    public boolean isRecycleEnabled()
    {
        return mRecycleEnabled;
    }

    public boolean isSelectable()
    {
        return mSelectable;
    }

    public boolean isSingleLineTitle()
    {
        return mSingleLineTitle;
    }

    protected void notifyChanged()
    {
        if(mListener != null)
            mListener.onPreferenceChange(this);
    }

    public void notifyDependencyChange(boolean flag)
    {
        List list = mDependents;
        if(list == null)
            return;
        int i = list.size();
        for(int j = 0; j < i; j++)
            ((Preference)list.get(j)).onDependencyChanged(this, flag);

    }

    protected void notifyHierarchyChanged()
    {
        if(mListener != null)
            mListener.onPreferenceHierarchyChange(this);
    }

    protected void onAttachedToActivity()
    {
        registerDependency();
    }

    protected void onAttachedToHierarchy(PreferenceManager preferencemanager)
    {
        mPreferenceManager = preferencemanager;
        mId = preferencemanager.getNextId();
        dispatchSetInitialValue();
    }

    protected void onBindView(View view)
    {
        byte byte0 = 4;
        Object obj = (TextView)view.findViewById(0x1020016);
        if(obj != null)
        {
            CharSequence charsequence = getTitle();
            if(!TextUtils.isEmpty(charsequence))
            {
                ((TextView) (obj)).setText(charsequence);
                ((TextView) (obj)).setVisibility(0);
                if(mHasSingleLineTitleAttr)
                    ((TextView) (obj)).setSingleLine(mSingleLineTitle);
            } else
            {
                ((TextView) (obj)).setVisibility(8);
            }
        }
        obj = (TextView)view.findViewById(0x1020010);
        if(obj != null)
        {
            charsequence = getSummary();
            if(!TextUtils.isEmpty(charsequence))
            {
                ((TextView) (obj)).setText(charsequence);
                ((TextView) (obj)).setVisibility(0);
            } else
            {
                ((TextView) (obj)).setVisibility(8);
            }
        }
        obj = (ImageView)view.findViewById(0x1020006);
        if(obj != null)
        {
            if(mIconResId != 0 || mIcon != null)
            {
                if(mIcon == null)
                    mIcon = getContext().getDrawable(mIconResId);
                if(mIcon != null)
                    ((ImageView) (obj)).setImageDrawable(mIcon);
            }
            if(mIcon != null)
            {
                ((ImageView) (obj)).setVisibility(0);
            } else
            {
                byte byte1;
                if(mIconSpaceReserved)
                    byte1 = 4;
                else
                    byte1 = 8;
                ((ImageView) (obj)).setVisibility(byte1);
            }
        }
        obj = view.findViewById(0x102003e);
        if(obj != null)
            if(mIcon != null)
            {
                ((View) (obj)).setVisibility(0);
            } else
            {
                byte byte2;
                if(mIconSpaceReserved)
                    byte2 = byte0;
                else
                    byte2 = 8;
                ((View) (obj)).setVisibility(byte2);
            }
        if(mShouldDisableView)
            setEnabledStateOnViews(view, isEnabled());
    }

    protected void onClick()
    {
    }

    protected View onCreateView(ViewGroup viewgroup)
    {
        LayoutInflater layoutinflater = (LayoutInflater)mContext.getSystemService("layout_inflater");
        View view = layoutinflater.inflate(mLayoutResId, viewgroup, false);
        viewgroup = (ViewGroup)view.findViewById(0x1020018);
        if(viewgroup != null)
            if(mWidgetLayoutResId != 0)
                layoutinflater.inflate(mWidgetLayoutResId, viewgroup);
            else
                viewgroup.setVisibility(8);
        return view;
    }

    public void onDependencyChanged(Preference preference, boolean flag)
    {
        if(mDependencyMet == flag)
        {
            mDependencyMet = flag ^ true;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    protected Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return null;
    }

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        return false;
    }

    public void onParentChanged(Preference preference, boolean flag)
    {
        if(mParentDependencyMet == flag)
        {
            mParentDependencyMet = flag ^ true;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    protected void onPrepareForRemoval()
    {
        unregisterDependency();
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        mBaseMethodCalled = true;
        if(parcelable != BaseSavedState.EMPTY_STATE && parcelable != null)
            throw new IllegalArgumentException("Wrong state class -- expecting Preference State");
        else
            return;
    }

    protected Parcelable onSaveInstanceState()
    {
        mBaseMethodCalled = true;
        return BaseSavedState.EMPTY_STATE;
    }

    protected void onSetInitialValue(boolean flag, Object obj)
    {
    }

    public Bundle peekExtras()
    {
        return mExtras;
    }

    public void performClick(PreferenceScreen preferencescreen)
    {
        if(!isEnabled())
            return;
        onClick();
        if(mOnClickListener != null && mOnClickListener.onPreferenceClick(this))
            return;
        Object obj = getPreferenceManager();
        if(obj != null)
        {
            obj = ((PreferenceManager) (obj)).getOnPreferenceTreeClickListener();
            if(preferencescreen != null && obj != null && ((PreferenceManager.OnPreferenceTreeClickListener) (obj)).onPreferenceTreeClick(preferencescreen, this))
                return;
        }
        if(mIntent != null)
            getContext().startActivity(mIntent);
    }

    protected boolean persistBoolean(boolean flag)
    {
        if(!shouldPersist())
            return false;
        if(flag == getPersistedBoolean(flag ^ true))
            return true;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
        {
            preferencedatastore.putBoolean(mKey, flag);
        } else
        {
            android.content.SharedPreferences.Editor editor = mPreferenceManager.getEditor();
            editor.putBoolean(mKey, flag);
            tryCommit(editor);
        }
        return true;
    }

    protected boolean persistFloat(float f)
    {
        if(!shouldPersist())
            return false;
        if(f == getPersistedFloat((0.0F / 0.0F)))
            return true;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
        {
            preferencedatastore.putFloat(mKey, f);
        } else
        {
            android.content.SharedPreferences.Editor editor = mPreferenceManager.getEditor();
            editor.putFloat(mKey, f);
            tryCommit(editor);
        }
        return true;
    }

    protected boolean persistInt(int i)
    {
        if(!shouldPersist())
            return false;
        if(i == getPersistedInt(i))
            return true;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
        {
            preferencedatastore.putInt(mKey, i);
        } else
        {
            android.content.SharedPreferences.Editor editor = mPreferenceManager.getEditor();
            editor.putInt(mKey, i);
            tryCommit(editor);
        }
        return true;
    }

    protected boolean persistLong(long l)
    {
        if(!shouldPersist())
            return false;
        if(l == getPersistedLong(l))
            return true;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
        {
            preferencedatastore.putLong(mKey, l);
        } else
        {
            android.content.SharedPreferences.Editor editor = mPreferenceManager.getEditor();
            editor.putLong(mKey, l);
            tryCommit(editor);
        }
        return true;
    }

    protected boolean persistString(String s)
    {
        if(!shouldPersist())
            return false;
        if(TextUtils.equals(s, getPersistedString(null)))
            return true;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
        {
            preferencedatastore.putString(mKey, s);
        } else
        {
            android.content.SharedPreferences.Editor editor = mPreferenceManager.getEditor();
            editor.putString(mKey, s);
            tryCommit(editor);
        }
        return true;
    }

    public boolean persistStringSet(Set set)
    {
        if(!shouldPersist())
            return false;
        if(set.equals(getPersistedStringSet(null)))
            return true;
        PreferenceDataStore preferencedatastore = getPreferenceDataStore();
        if(preferencedatastore != null)
        {
            preferencedatastore.putStringSet(mKey, set);
        } else
        {
            android.content.SharedPreferences.Editor editor = mPreferenceManager.getEditor();
            editor.putStringSet(mKey, set);
            tryCommit(editor);
        }
        return true;
    }

    void requireKey()
    {
        if(mKey == null)
        {
            throw new IllegalStateException("Preference does not have a key assigned.");
        } else
        {
            mRequiresKey = true;
            return;
        }
    }

    public void restoreHierarchyState(Bundle bundle)
    {
        dispatchRestoreInstanceState(bundle);
    }

    public void saveHierarchyState(Bundle bundle)
    {
        dispatchSaveInstanceState(bundle);
    }

    public void setDefaultValue(Object obj)
    {
        mDefaultValue = obj;
    }

    public void setDependency(String s)
    {
        unregisterDependency();
        mDependencyKey = s;
        registerDependency();
    }

    public void setEnabled(boolean flag)
    {
        if(mEnabled != flag)
        {
            mEnabled = flag;
            notifyDependencyChange(shouldDisableDependents());
            notifyChanged();
        }
    }

    public void setFragment(String s)
    {
        mFragment = s;
    }

    public void setIcon(int i)
    {
        if(mIconResId != i)
        {
            mIconResId = i;
            setIcon(mContext.getDrawable(i));
        }
    }

    public void setIcon(Drawable drawable)
    {
        if(drawable == null && mIcon != null || drawable != null && mIcon != drawable)
        {
            mIcon = drawable;
            notifyChanged();
        }
    }

    public void setIconSpaceReserved(boolean flag)
    {
        mIconSpaceReserved = flag;
        notifyChanged();
    }

    public void setIntent(Intent intent)
    {
        mIntent = intent;
    }

    public void setKey(String s)
    {
        mKey = s;
        if(mRequiresKey && hasKey() ^ true)
            requireKey();
    }

    public void setLayoutResource(int i)
    {
        if(i != mLayoutResId)
            mRecycleEnabled = false;
        mLayoutResId = i;
    }

    final void setOnPreferenceChangeInternalListener(OnPreferenceChangeInternalListener onpreferencechangeinternallistener)
    {
        mListener = onpreferencechangeinternallistener;
    }

    public void setOnPreferenceChangeListener(OnPreferenceChangeListener onpreferencechangelistener)
    {
        mOnChangeListener = onpreferencechangelistener;
    }

    public void setOnPreferenceClickListener(OnPreferenceClickListener onpreferenceclicklistener)
    {
        mOnClickListener = onpreferenceclicklistener;
    }

    public void setOrder(int i)
    {
        if(i != mOrder)
        {
            mOrder = i;
            notifyHierarchyChanged();
        }
    }

    public void setPersistent(boolean flag)
    {
        mPersistent = flag;
    }

    public void setPreferenceDataStore(PreferenceDataStore preferencedatastore)
    {
        mPreferenceDataStore = preferencedatastore;
    }

    public void setRecycleEnabled(boolean flag)
    {
        mRecycleEnabled = flag;
        notifyChanged();
    }

    public void setSelectable(boolean flag)
    {
        if(mSelectable != flag)
        {
            mSelectable = flag;
            notifyChanged();
        }
    }

    public void setShouldDisableView(boolean flag)
    {
        mShouldDisableView = flag;
        notifyChanged();
    }

    public void setSingleLineTitle(boolean flag)
    {
        mHasSingleLineTitleAttr = true;
        mSingleLineTitle = flag;
        notifyChanged();
    }

    public void setSummary(int i)
    {
        setSummary(((CharSequence) (mContext.getString(i))));
    }

    public void setSummary(CharSequence charsequence)
    {
        if(charsequence == null && mSummary != null || charsequence != null && charsequence.equals(mSummary) ^ true)
        {
            mSummary = charsequence;
            notifyChanged();
        }
    }

    public void setTitle(int i)
    {
        setTitle(((CharSequence) (mContext.getString(i))));
        mTitleRes = i;
    }

    public void setTitle(CharSequence charsequence)
    {
        if(charsequence == null && mTitle != null || charsequence != null && charsequence.equals(mTitle) ^ true)
        {
            mTitleRes = 0;
            mTitle = charsequence;
            notifyChanged();
        }
    }

    public void setWidgetLayoutResource(int i)
    {
        if(i != mWidgetLayoutResId)
            mRecycleEnabled = false;
        mWidgetLayoutResId = i;
    }

    public boolean shouldCommit()
    {
        if(mPreferenceManager == null)
            return false;
        else
            return mPreferenceManager.shouldCommit();
    }

    public boolean shouldDisableDependents()
    {
        return isEnabled() ^ true;
    }

    protected boolean shouldPersist()
    {
        boolean flag;
        if(mPreferenceManager != null && isPersistent())
            flag = hasKey();
        else
            flag = false;
        return flag;
    }

    public String toString()
    {
        return getFilterableStringBuilder().toString();
    }

    public static final int DEFAULT_ORDER = 0x7fffffff;
    private boolean mBaseMethodCalled;
    private Context mContext;
    private Object mDefaultValue;
    private String mDependencyKey;
    private boolean mDependencyMet;
    private List mDependents;
    private boolean mEnabled;
    private Bundle mExtras;
    private String mFragment;
    private boolean mHasSingleLineTitleAttr;
    private Drawable mIcon;
    private int mIconResId;
    private boolean mIconSpaceReserved;
    private long mId;
    private Intent mIntent;
    private String mKey;
    private int mLayoutResId;
    private OnPreferenceChangeInternalListener mListener;
    private OnPreferenceChangeListener mOnChangeListener;
    private OnPreferenceClickListener mOnClickListener;
    private int mOrder;
    private boolean mParentDependencyMet;
    private PreferenceGroup mParentGroup;
    private boolean mPersistent;
    private PreferenceDataStore mPreferenceDataStore;
    private PreferenceManager mPreferenceManager;
    private boolean mRecycleEnabled;
    private boolean mRequiresKey;
    private boolean mSelectable;
    private boolean mShouldDisableView;
    private boolean mSingleLineTitle;
    private CharSequence mSummary;
    private CharSequence mTitle;
    private int mTitleRes;
    private int mWidgetLayoutResId;
}
