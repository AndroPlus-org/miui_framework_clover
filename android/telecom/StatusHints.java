// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.*;
import java.util.Objects;

public final class StatusHints
    implements Parcelable
{

    public StatusHints(ComponentName componentname, CharSequence charsequence, int i, Bundle bundle)
    {
        if(i == 0)
            componentname = null;
        else
            componentname = Icon.createWithResource(componentname.getPackageName(), i);
        this(charsequence, ((Icon) (componentname)), bundle);
    }

    private StatusHints(Parcel parcel)
    {
        mLabel = parcel.readCharSequence();
        mIcon = (Icon)parcel.readParcelable(getClass().getClassLoader());
        mExtras = (Bundle)parcel.readParcelable(getClass().getClassLoader());
    }

    StatusHints(Parcel parcel, StatusHints statushints)
    {
        this(parcel);
    }

    public StatusHints(CharSequence charsequence, Icon icon, Bundle bundle)
    {
        mLabel = charsequence;
        mIcon = icon;
        mExtras = bundle;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj != null && (obj instanceof StatusHints))
        {
            obj = (StatusHints)obj;
            boolean flag1 = flag;
            if(Objects.equals(((StatusHints) (obj)).getLabel(), getLabel()))
            {
                flag1 = flag;
                if(Objects.equals(((StatusHints) (obj)).getIcon(), getIcon()))
                    flag1 = Objects.equals(((StatusHints) (obj)).getExtras(), getExtras());
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public Drawable getIcon(Context context)
    {
        return mIcon.loadDrawable(context);
    }

    public Icon getIcon()
    {
        return mIcon;
    }

    public int getIconResId()
    {
        return 0;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public ComponentName getPackageName()
    {
        return new ComponentName("", "");
    }

    public int hashCode()
    {
        return Objects.hashCode(mLabel) + Objects.hashCode(mIcon) + Objects.hashCode(mExtras);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeCharSequence(mLabel);
        parcel.writeParcelable(mIcon, 0);
        parcel.writeParcelable(mExtras, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public StatusHints createFromParcel(Parcel parcel)
        {
            return new StatusHints(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public StatusHints[] newArray(int i)
        {
            return new StatusHints[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Bundle mExtras;
    private final Icon mIcon;
    private final CharSequence mLabel;

}
