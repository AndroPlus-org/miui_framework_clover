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
import android.widget.*;

// Referenced classes of package android.preference:
//            PreferenceGroup, PreferenceManager, PreferenceGroupAdapter, Preference

public final class PreferenceScreen extends PreferenceGroup
    implements android.widget.AdapterView.OnItemClickListener, android.content.DialogInterface.OnDismissListener
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


    public PreferenceScreen(Context context, AttributeSet attributeset)
    {
        super(context, attributeset, 0x101008b);
        mLayoutResId = 0x10900ca;
        context = context.obtainStyledAttributes(null, com.android.internal.R.styleable.PreferenceScreen, 0x101008b, 0);
        mLayoutResId = context.getResourceId(1, mLayoutResId);
        if(context.hasValueOrEmpty(0))
        {
            mDividerDrawable = context.getDrawable(0);
            mDividerSpecified = true;
        }
        context.recycle();
    }

    private void showDialog(Bundle bundle)
    {
        Object obj = getContext();
        if(mListView != null)
            mListView.setAdapter(null);
        View view = ((LayoutInflater)((Context) (obj)).getSystemService("layout_inflater")).inflate(mLayoutResId, null);
        View view1 = view.findViewById(0x1020016);
        mListView = (ListView)view.findViewById(0x102000a);
        if(mDividerSpecified)
            mListView.setDivider(mDividerDrawable);
        bind(mListView);
        CharSequence charsequence = getTitle();
        obj = new Dialog(((Context) (obj)), ((Context) (obj)).getThemeResId());
        mDialog = ((Dialog) (obj));
        if(TextUtils.isEmpty(charsequence))
        {
            if(view1 != null)
                view1.setVisibility(8);
            ((Dialog) (obj)).getWindow().requestFeature(1);
        } else
        if(view1 instanceof TextView)
        {
            ((TextView)view1).setText(charsequence);
            view1.setVisibility(0);
        } else
        {
            ((Dialog) (obj)).setTitle(charsequence);
        }
        ((Dialog) (obj)).setContentView(view);
        ((Dialog) (obj)).setOnDismissListener(this);
        if(bundle != null)
            ((Dialog) (obj)).onRestoreInstanceState(bundle);
        getPreferenceManager().addPreferencesScreen(((DialogInterface) (obj)));
        ((Dialog) (obj)).show();
    }

    public void bind(ListView listview)
    {
        listview.setOnItemClickListener(this);
        listview.setAdapter(getRootAdapter());
        onAttachedToActivity();
    }

    public Dialog getDialog()
    {
        return mDialog;
    }

    public ListAdapter getRootAdapter()
    {
        if(mRootAdapter == null)
            mRootAdapter = onCreateRootAdapter();
        return mRootAdapter;
    }

    protected boolean isOnSameScreenAsChildren()
    {
        return false;
    }

    protected void onClick()
    {
        while(getIntent() != null || getFragment() != null || getPreferenceCount() == 0) 
            return;
        showDialog(null);
    }

    protected ListAdapter onCreateRootAdapter()
    {
        return new PreferenceGroupAdapter(this);
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        mDialog = null;
        getPreferenceManager().removePreferencesScreen(dialoginterface);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        int j = i;
        if(adapterview instanceof ListView)
            j = i - ((ListView)adapterview).getHeaderViewsCount();
        adapterview = ((AdapterView) (getRootAdapter().getItem(j)));
        if(!(adapterview instanceof Preference))
        {
            return;
        } else
        {
            ((Preference)adapterview).performClick(this);
            return;
        }
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(parcelable == null || parcelable.getClass().equals(android/preference/PreferenceScreen$SavedState) ^ true)
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
        Dialog dialog = mDialog;
        if(dialog == null || dialog.isShowing() ^ true)
        {
            return ((Parcelable) (obj));
        } else
        {
            obj = new SavedState(((Parcelable) (obj)));
            obj.isDialogShowing = true;
            obj.dialogBundle = dialog.onSaveInstanceState();
            return ((Parcelable) (obj));
        }
    }

    private Dialog mDialog;
    private Drawable mDividerDrawable;
    private boolean mDividerSpecified;
    private int mLayoutResId;
    private ListView mListView;
    private ListAdapter mRootAdapter;
}
