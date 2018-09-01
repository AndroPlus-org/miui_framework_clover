// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

// Referenced classes of package android.preference:
//            DialogPreference

public class EditTextPreference extends DialogPreference
{
    private static class SavedState extends Preference.BaseSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeString(text);
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
        String text;


        public SavedState(Parcel parcel)
        {
            super(parcel);
            text = parcel.readString();
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public EditTextPreference(Context context)
    {
        this(context, null);
    }

    public EditTextPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010092);
    }

    public EditTextPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public EditTextPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        mEditText = new EditText(context, attributeset);
        mEditText.setId(0x1020003);
        mEditText.setEnabled(true);
    }

    public EditText getEditText()
    {
        return mEditText;
    }

    public String getText()
    {
        return mText;
    }

    protected boolean needInputMethod()
    {
        return true;
    }

    protected void onAddEditTextToDialogView(View view, EditText edittext)
    {
        view = (ViewGroup)view.findViewById(0x1020230);
        if(view != null)
            view.addView(edittext, -1, -2);
    }

    protected void onBindDialogView(View view)
    {
        super.onBindDialogView(view);
        EditText edittext = mEditText;
        edittext.setText(getText());
        android.view.ViewParent viewparent = edittext.getParent();
        if(viewparent != view)
        {
            if(viewparent != null)
                ((ViewGroup)viewparent).removeView(edittext);
            onAddEditTextToDialogView(view, edittext);
        }
    }

    protected void onDialogClosed(boolean flag)
    {
        super.onDialogClosed(flag);
        if(flag)
        {
            String s = mEditText.getText().toString();
            if(callChangeListener(s))
                setText(s);
        }
    }

    protected Object onGetDefaultValue(TypedArray typedarray, int i)
    {
        return typedarray.getString(i);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable == null || parcelable.getClass().equals(android/preference/EditTextPreference$SavedState) ^ true)
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            setText(((SavedState) (parcelable)).text);
            return;
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        Object obj = super.onSaveInstanceState();
        if(isPersistent())
        {
            return ((Parcelable) (obj));
        } else
        {
            obj = new SavedState(((Parcelable) (obj)));
            obj.text = getText();
            return ((Parcelable) (obj));
        }
    }

    protected void onSetInitialValue(boolean flag, Object obj)
    {
        if(flag)
            obj = getPersistedString(mText);
        else
            obj = (String)obj;
        setText(((String) (obj)));
    }

    public void setText(String s)
    {
        boolean flag = TextUtils.equals(mText, s) ^ true;
        if(flag || mTextSet ^ true)
        {
            mText = s;
            mTextSet = true;
            persistString(s);
            if(flag)
            {
                notifyDependencyChange(shouldDisableDependents());
                notifyChanged();
            }
        }
    }

    public boolean shouldDisableDependents()
    {
        boolean flag;
        if(!TextUtils.isEmpty(mText))
            flag = super.shouldDisableDependents();
        else
            flag = true;
        return flag;
    }

    private EditText mEditText;
    private String mText;
    private boolean mTextSet;
}
