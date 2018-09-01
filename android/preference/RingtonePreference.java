// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.RingtoneManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

// Referenced classes of package android.preference:
//            Preference, PreferenceManager, PreferenceFragment, RingtonePreferenceInjector

public class RingtonePreference extends Preference
    implements PreferenceManager.OnActivityResultListener
{

    public RingtonePreference(Context context)
    {
        this(context, null);
    }

    public RingtonePreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010093);
    }

    public RingtonePreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public RingtonePreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.RingtonePreference, i, j);
        mRingtoneType = context.getInt(0, 1);
        mShowDefault = context.getBoolean(1, true);
        mShowSilent = context.getBoolean(2, true);
        context.recycle();
    }

    public int getRingtoneType()
    {
        return mRingtoneType;
    }

    public boolean getShowDefault()
    {
        return mShowDefault;
    }

    public boolean getShowSilent()
    {
        return mShowSilent;
    }

    public boolean onActivityResult(int i, int j, Intent intent)
    {
        if(i == mRequestCode)
        {
            if(intent != null)
            {
                Uri uri = (Uri)intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
                if(uri != null)
                    intent = uri.toString();
                else
                    intent = "";
                if(callChangeListener(intent))
                    onSaveRingtone(uri);
            }
            return true;
        } else
        {
            return false;
        }
    }

    protected void onAttachedToHierarchy(PreferenceManager preferencemanager)
    {
        super.onAttachedToHierarchy(preferencemanager);
        preferencemanager.registerOnActivityResultListener(this);
        mRequestCode = preferencemanager.getNextRequestCode();
    }

    protected void onClick()
    {
        Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
        onPrepareRingtonePickerIntent(intent);
        PreferenceFragment preferencefragment = getPreferenceManager().getFragment();
        if(preferencefragment != null)
            preferencefragment.startActivityForResult(intent, mRequestCode);
        else
            getPreferenceManager().getActivity().startActivityForResult(intent, mRequestCode);
    }

    protected Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return typedarray.getString(i);
    }

    protected void onPrepareRingtonePickerIntent(Intent intent)
    {
        intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", onRestoreRingtone());
        intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", mShowDefault);
        if(mShowDefault)
            intent.putExtra("android.intent.extra.ringtone.DEFAULT_URI", RingtoneManager.getDefaultUri(getRingtoneType()));
        intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", mShowSilent);
        intent.putExtra("android.intent.extra.ringtone.TYPE", mRingtoneType);
        intent.putExtra("android.intent.extra.ringtone.TITLE", getTitle());
        intent.putExtra("android.intent.extra.ringtone.AUDIO_ATTRIBUTES_FLAGS", 64);
        RingtonePreferenceInjector.specifyRingtonePickIntentActivity(intent);
    }

    protected Uri onRestoreRingtone()
    {
        Uri uri = null;
        String s = getPersistedString(null);
        if(!TextUtils.isEmpty(s))
            uri = Uri.parse(s);
        return uri;
    }

    protected void onSaveRingtone(Uri uri)
    {
        if(uri != null)
            uri = uri.toString();
        else
            uri = "";
        persistString(uri);
    }

    protected void onSetInitialValue(boolean flag, Object obj)
    {
        obj = (String)obj;
        if(flag)
            return;
        if(!TextUtils.isEmpty(((CharSequence) (obj))))
            onSaveRingtone(Uri.parse(((String) (obj))));
    }

    public void setRingtoneType(int i)
    {
        mRingtoneType = i;
    }

    public void setShowDefault(boolean flag)
    {
        mShowDefault = flag;
    }

    public void setShowSilent(boolean flag)
    {
        mShowSilent = flag;
    }

    private static final String TAG = "RingtonePreference";
    private int mRequestCode;
    private int mRingtoneType;
    private boolean mShowDefault;
    private boolean mShowSilent;
}
