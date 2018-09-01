// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package android.preference:
//            PreferenceGroup, Preference

public class PreferenceCategory extends PreferenceGroup
{

    public PreferenceCategory(Context context)
    {
        this(context, null);
    }

    public PreferenceCategory(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x101008c);
    }

    public PreferenceCategory(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public PreferenceCategory(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    public boolean isEnabled()
    {
        return false;
    }

    protected boolean onPrepareAddPreference(Preference preference)
    {
        if(preference instanceof PreferenceCategory)
            throw new IllegalArgumentException("Cannot add a PreferenceCategory directly to a PreferenceCategory");
        else
            return super.onPrepareAddPreference(preference);
    }

    public boolean shouldDisableDependents()
    {
        return super.isEnabled() ^ true;
    }

    private static final String TAG = "PreferenceCategory";
}
