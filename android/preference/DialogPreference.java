// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.*;
import android.widget.TextView;

// Referenced classes of package android.preference:
//            Preference, PreferenceManager

public abstract class DialogPreference extends Preference
    implements android.content.DialogInterface.OnClickListener, android.content.DialogInterface.OnDismissListener, PreferenceManager.OnActivityDestroyListener
{
    private static class SavedState extends Preference.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            if(isDialogShowing)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeBundle(dialogBundle);
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
        Bundle dialogBundle;
        boolean isDialogShowing;


        public SavedState(Parcel parcel)
        {
            boolean flag = true;
            super(parcel);
            if(parcel.readInt() != 1)
                flag = false;
            isDialogShowing = flag;
            dialogBundle = parcel.readBundle();
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public DialogPreference(Context context)
    {
        this(context, null);
    }

    public DialogPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010091);
    }

    public DialogPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public DialogPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        context = context.obtainStyledAttributes(attributeset, com.android.internal.R.styleable.DialogPreference, i, j);
        mDialogTitle = context.getString(0);
        if(mDialogTitle == null)
            mDialogTitle = getTitle();
        mDialogMessage = context.getString(1);
        mDialogIcon = context.getDrawable(2);
        mPositiveButtonText = context.getString(3);
        mNegativeButtonText = context.getString(4);
        mDialogLayoutResId = context.getResourceId(5, mDialogLayoutResId);
        context.recycle();
    }

    private void requestInputMethod(Dialog dialog)
    {
        dialog.getWindow().setSoftInputMode(5);
    }

    public Dialog getDialog()
    {
        return mDialog;
    }

    public Drawable getDialogIcon()
    {
        return mDialogIcon;
    }

    public int getDialogLayoutResource()
    {
        return mDialogLayoutResId;
    }

    public CharSequence getDialogMessage()
    {
        return mDialogMessage;
    }

    public CharSequence getDialogTitle()
    {
        return mDialogTitle;
    }

    public CharSequence getNegativeButtonText()
    {
        return mNegativeButtonText;
    }

    public CharSequence getPositiveButtonText()
    {
        return mPositiveButtonText;
    }

    protected boolean needInputMethod()
    {
        return false;
    }

    public void onActivityDestroy()
    {
        if(mDialog == null || mDialog.isShowing() ^ true)
        {
            return;
        } else
        {
            mDialog.dismiss();
            return;
        }
    }

    protected void onBindDialogView(View view)
    {
        view = view.findViewById(0x102000b);
        if(view != null)
        {
            CharSequence charsequence = getDialogMessage();
            byte byte0 = 8;
            if(!TextUtils.isEmpty(charsequence))
            {
                if(view instanceof TextView)
                    ((TextView)view).setText(charsequence);
                byte0 = 0;
            }
            if(view.getVisibility() != byte0)
                view.setVisibility(byte0);
        }
    }

    protected void onClick()
    {
        if(mDialog != null && mDialog.isShowing())
        {
            return;
        } else
        {
            showDialog(null);
            return;
        }
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        mWhichButtonClicked = i;
    }

    protected View onCreateDialogView()
    {
        if(mDialogLayoutResId == 0)
            return null;
        else
            return LayoutInflater.from(mBuilder.getContext()).inflate(mDialogLayoutResId, null);
    }

    protected void onDialogClosed(boolean flag)
    {
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        getPreferenceManager().unregisterOnActivityDestroyListener(this);
        mDialog = null;
        boolean flag;
        if(mWhichButtonClicked == -1)
            flag = true;
        else
            flag = false;
        onDialogClosed(flag);
    }

    protected void onPrepareDialogBuilder(android.app.AlertDialog.Builder builder)
    {
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable == null || parcelable.getClass().equals(android/preference/DialogPreference$SavedState) ^ true)
        {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        parcelable = (SavedState)parcelable;
        super.onRestoreInstanceState(parcelable.getSuperState());
        if(((SavedState) (parcelable)).isDialogShowing)
            showDialog(((SavedState) (parcelable)).dialogBundle);
    }

    protected Parcelable onSaveInstanceState()
    {
        Object obj = super.onSaveInstanceState();
        if(mDialog == null || mDialog.isShowing() ^ true)
        {
            return ((Parcelable) (obj));
        } else
        {
            obj = new SavedState(((Parcelable) (obj)));
            obj.isDialogShowing = true;
            obj.dialogBundle = mDialog.onSaveInstanceState();
            return ((Parcelable) (obj));
        }
    }

    public void setDialogIcon(int i)
    {
        mDialogIcon = getContext().getDrawable(i);
    }

    public void setDialogIcon(Drawable drawable)
    {
        mDialogIcon = drawable;
    }

    public void setDialogLayoutResource(int i)
    {
        mDialogLayoutResId = i;
    }

    public void setDialogMessage(int i)
    {
        setDialogMessage(((CharSequence) (getContext().getString(i))));
    }

    public void setDialogMessage(CharSequence charsequence)
    {
        mDialogMessage = charsequence;
    }

    public void setDialogTitle(int i)
    {
        setDialogTitle(((CharSequence) (getContext().getString(i))));
    }

    public void setDialogTitle(CharSequence charsequence)
    {
        mDialogTitle = charsequence;
    }

    public void setNegativeButtonText(int i)
    {
        setNegativeButtonText(((CharSequence) (getContext().getString(i))));
    }

    public void setNegativeButtonText(CharSequence charsequence)
    {
        mNegativeButtonText = charsequence;
    }

    public void setPositiveButtonText(int i)
    {
        setPositiveButtonText(((CharSequence) (getContext().getString(i))));
    }

    public void setPositiveButtonText(CharSequence charsequence)
    {
        mPositiveButtonText = charsequence;
    }

    protected void showDialog(Bundle bundle)
    {
        Object obj = getContext();
        mWhichButtonClicked = -2;
        mBuilder = (new android.app.AlertDialog.Builder(((Context) (obj)))).setTitle(mDialogTitle).setIcon(mDialogIcon).setPositiveButton(mPositiveButtonText, this).setNegativeButton(mNegativeButtonText, this);
        obj = onCreateDialogView();
        if(obj != null)
        {
            onBindDialogView(((View) (obj)));
            mBuilder.setView(((View) (obj)));
        } else
        {
            mBuilder.setMessage(mDialogMessage);
        }
        onPrepareDialogBuilder(mBuilder);
        getPreferenceManager().registerOnActivityDestroyListener(this);
        obj = mBuilder.create();
        mDialog = ((Dialog) (obj));
        if(bundle != null)
            ((Dialog) (obj)).onRestoreInstanceState(bundle);
        if(needInputMethod())
            requestInputMethod(((Dialog) (obj)));
        ((Dialog) (obj)).setOnDismissListener(this);
        ((Dialog) (obj)).show();
    }

    private android.app.AlertDialog.Builder mBuilder;
    private Dialog mDialog;
    private Drawable mDialogIcon;
    private int mDialogLayoutResId;
    private CharSequence mDialogMessage;
    private CharSequence mDialogTitle;
    private CharSequence mNegativeButtonText;
    private CharSequence mPositiveButtonText;
    private int mWhichButtonClicked;
}
