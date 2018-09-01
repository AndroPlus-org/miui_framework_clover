// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.chooser;

import android.content.ComponentName;
import android.graphics.drawable.Icon;
import android.os.*;

public final class ChooserTarget
    implements Parcelable
{

    ChooserTarget(Parcel parcel)
    {
        mTitle = parcel.readCharSequence();
        if(parcel.readInt() != 0)
            mIcon = (Icon)Icon.CREATOR.createFromParcel(parcel);
        else
            mIcon = null;
        mScore = parcel.readFloat();
        mComponentName = ComponentName.readFromParcel(parcel);
        mIntentExtras = parcel.readBundle();
    }

    public ChooserTarget(CharSequence charsequence, Icon icon, float f, ComponentName componentname, Bundle bundle)
    {
        mTitle = charsequence;
        mIcon = icon;
        if(f > 1.0F || f < 0.0F)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Score ").append(f).append(" out of range; ").append("must be between 0.0f and 1.0f").toString());
        } else
        {
            mScore = f;
            mComponentName = componentname;
            mIntentExtras = bundle;
            return;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public ComponentName getComponentName()
    {
        return mComponentName;
    }

    public Icon getIcon()
    {
        return mIcon;
    }

    public Bundle getIntentExtras()
    {
        return mIntentExtras;
    }

    public float getScore()
    {
        return mScore;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ChooserTarget{").append(mComponentName).append(", ").append(mIntentExtras).append(", '").append(mTitle).append("', ").append(mScore).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeCharSequence(mTitle);
        if(mIcon != null)
        {
            parcel.writeInt(1);
            mIcon.writeToParcel(parcel, 0);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeFloat(mScore);
        ComponentName.writeToParcel(mComponentName, parcel);
        parcel.writeBundle(mIntentExtras);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ChooserTarget createFromParcel(Parcel parcel)
        {
            return new ChooserTarget(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ChooserTarget[] newArray(int i)
        {
            return new ChooserTarget[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "ChooserTarget";
    private ComponentName mComponentName;
    private Icon mIcon;
    private Bundle mIntentExtras;
    private float mScore;
    private CharSequence mTitle;

}
