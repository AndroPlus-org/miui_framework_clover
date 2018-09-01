// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import java.util.*;

// Referenced classes of package android.preference:
//            Preference

public abstract class PreferenceGroup extends Preference
    implements GenericInflater.Parent
{

    public PreferenceGroup(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mOrderingAsAdded = true;
        mCurrentPreferenceOrder = 0;
        mAttachedToActivity = false;
        mPreferenceList = new ArrayList();
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.PreferenceGroup, i, j);
        mOrderingAsAdded = context.getBoolean(0, mOrderingAsAdded);
        context.recycle();
    }

    private boolean removePreferenceInt(Preference preference)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        preference.onPrepareForRemoval();
        if(preference.getParent() == this)
            preference.assignParent(null);
        flag = mPreferenceList.remove(preference);
        this;
        JVM INSTR monitorexit ;
        return flag;
        preference;
        throw preference;
    }

    public void addItemFromInflater(Preference preference)
    {
        addPreference(preference);
    }

    public volatile void addItemFromInflater(Object obj)
    {
        addItemFromInflater((Preference)obj);
    }

    public boolean addPreference(Preference preference)
    {
        if(mPreferenceList.contains(preference))
            return true;
        if(preference.getOrder() == 0x7fffffff)
        {
            if(mOrderingAsAdded)
            {
                int i = mCurrentPreferenceOrder;
                mCurrentPreferenceOrder = i + 1;
                preference.setOrder(i);
            }
            if(preference instanceof PreferenceGroup)
                ((PreferenceGroup)preference).setOrderingAsAdded(mOrderingAsAdded);
        }
        if(!onPrepareAddPreference(preference))
            return false;
        this;
        JVM INSTR monitorenter ;
        int k = Collections.binarySearch(mPreferenceList, preference);
        int j;
        j = k;
        if(k < 0)
            j = k * -1 - 1;
        mPreferenceList.add(j, preference);
        this;
        JVM INSTR monitorexit ;
        preference.onAttachedToHierarchy(getPreferenceManager());
        preference.assignParent(this);
        if(mAttachedToActivity)
            preference.onAttachedToActivity();
        notifyHierarchyChanged();
        return true;
        preference;
        throw preference;
    }

    protected void dispatchRestoreInstanceState(Bundle bundle)
    {
        super.dispatchRestoreInstanceState(bundle);
        int i = getPreferenceCount();
        for(int j = 0; j < i; j++)
            getPreference(j).dispatchRestoreInstanceState(bundle);

    }

    protected void dispatchSaveInstanceState(Bundle bundle)
    {
        super.dispatchSaveInstanceState(bundle);
        int i = getPreferenceCount();
        for(int j = 0; j < i; j++)
            getPreference(j).dispatchSaveInstanceState(bundle);

    }

    public Preference findPreference(CharSequence charsequence)
    {
        if(TextUtils.equals(getKey(), charsequence))
            return this;
        int i = getPreferenceCount();
        for(int j = 0; j < i; j++)
        {
            Preference preference = getPreference(j);
            Object obj = preference.getKey();
            if(obj != null && ((String) (obj)).equals(charsequence))
                return preference;
            if(!(preference instanceof PreferenceGroup))
                continue;
            obj = ((PreferenceGroup)preference).findPreference(charsequence);
            if(obj != null)
                return ((Preference) (obj));
        }

        return null;
    }

    public Preference getPreference(int i)
    {
        return (Preference)mPreferenceList.get(i);
    }

    public int getPreferenceCount()
    {
        return mPreferenceList.size();
    }

    protected boolean isOnSameScreenAsChildren()
    {
        return true;
    }

    public boolean isOrderingAsAdded()
    {
        return mOrderingAsAdded;
    }

    public void notifyDependencyChange(boolean flag)
    {
        super.notifyDependencyChange(flag);
        int i = getPreferenceCount();
        for(int j = 0; j < i; j++)
            getPreference(j).onParentChanged(this, flag);

    }

    protected void onAttachedToActivity()
    {
        super.onAttachedToActivity();
        mAttachedToActivity = true;
        int i = getPreferenceCount();
        for(int j = 0; j < i; j++)
            getPreference(j).onAttachedToActivity();

    }

    protected boolean onPrepareAddPreference(Preference preference)
    {
        preference.onParentChanged(this, shouldDisableDependents());
        return true;
    }

    protected void onPrepareForRemoval()
    {
        super.onPrepareForRemoval();
        mAttachedToActivity = false;
    }

    public void removeAll()
    {
        this;
        JVM INSTR monitorenter ;
        List list;
        int i;
        list = mPreferenceList;
        i = list.size() - 1;
_L2:
        if(i < 0)
            break; /* Loop/switch isn't completed */
        removePreferenceInt((Preference)list.get(0));
        i--;
        if(true) goto _L2; else goto _L1
_L1:
        notifyHierarchyChanged();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean removePreference(Preference preference)
    {
        boolean flag = removePreferenceInt(preference);
        notifyHierarchyChanged();
        return flag;
    }

    public void setOrderingAsAdded(boolean flag)
    {
        mOrderingAsAdded = flag;
    }

    void sortPreferences()
    {
        this;
        JVM INSTR monitorenter ;
        Collections.sort(mPreferenceList);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean mAttachedToActivity;
    private int mCurrentPreferenceOrder;
    private boolean mOrderingAsAdded;
    private List mPreferenceList;
}
