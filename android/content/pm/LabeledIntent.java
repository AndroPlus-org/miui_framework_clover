// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.text.TextUtils;

// Referenced classes of package android.content.pm:
//            PackageManager

public class LabeledIntent extends Intent
{

    public LabeledIntent(Intent intent, String s, int i, int j)
    {
        super(intent);
        mSourcePackage = s;
        mLabelRes = i;
        mNonLocalizedLabel = null;
        mIcon = j;
    }

    public LabeledIntent(Intent intent, String s, CharSequence charsequence, int i)
    {
        super(intent);
        mSourcePackage = s;
        mLabelRes = 0;
        mNonLocalizedLabel = charsequence;
        mIcon = i;
    }

    protected LabeledIntent(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    public LabeledIntent(String s, int i, int j)
    {
        mSourcePackage = s;
        mLabelRes = i;
        mNonLocalizedLabel = null;
        mIcon = j;
    }

    public LabeledIntent(String s, CharSequence charsequence, int i)
    {
        mSourcePackage = s;
        mLabelRes = 0;
        mNonLocalizedLabel = charsequence;
        mIcon = i;
    }

    public int getIconResource()
    {
        return mIcon;
    }

    public int getLabelResource()
    {
        return mLabelRes;
    }

    public CharSequence getNonLocalizedLabel()
    {
        return mNonLocalizedLabel;
    }

    public String getSourcePackage()
    {
        return mSourcePackage;
    }

    public Drawable loadIcon(PackageManager packagemanager)
    {
        if(mIcon != 0 && mSourcePackage != null)
        {
            packagemanager = packagemanager.getDrawable(mSourcePackage, mIcon, null);
            if(packagemanager != null)
                return packagemanager;
        }
        return null;
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        if(mNonLocalizedLabel != null)
            return mNonLocalizedLabel;
        if(mLabelRes != 0 && mSourcePackage != null)
        {
            packagemanager = packagemanager.getText(mSourcePackage, mLabelRes, null);
            if(packagemanager != null)
                return packagemanager;
        }
        return null;
    }

    public void readFromParcel(Parcel parcel)
    {
        super.readFromParcel(parcel);
        mSourcePackage = parcel.readString();
        mLabelRes = parcel.readInt();
        mNonLocalizedLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mIcon = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeString(mSourcePackage);
        parcel.writeInt(mLabelRes);
        TextUtils.writeToParcel(mNonLocalizedLabel, parcel, i);
        parcel.writeInt(mIcon);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public LabeledIntent createFromParcel(Parcel parcel)
        {
            return new LabeledIntent(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public LabeledIntent[] newArray(int i)
        {
            return new LabeledIntent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private int mIcon;
    private int mLabelRes;
    private CharSequence mNonLocalizedLabel;
    private String mSourcePackage;

}
