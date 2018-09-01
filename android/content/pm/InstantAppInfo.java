// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.content.pm:
//            ApplicationInfo, PackageManager

public final class InstantAppInfo
    implements Parcelable
{

    public InstantAppInfo(ApplicationInfo applicationinfo, String as[], String as1[])
    {
        mApplicationInfo = applicationinfo;
        mPackageName = null;
        mLabelText = null;
        mRequestedPermissions = as;
        mGrantedPermissions = as1;
    }

    private InstantAppInfo(Parcel parcel)
    {
        mPackageName = parcel.readString();
        mLabelText = parcel.readCharSequence();
        mRequestedPermissions = parcel.readStringArray();
        mGrantedPermissions = parcel.createStringArray();
        mApplicationInfo = (ApplicationInfo)parcel.readParcelable(null);
    }

    InstantAppInfo(Parcel parcel, InstantAppInfo instantappinfo)
    {
        this(parcel);
    }

    public InstantAppInfo(String s, CharSequence charsequence, String as[], String as1[])
    {
        mApplicationInfo = null;
        mPackageName = s;
        mLabelText = charsequence;
        mRequestedPermissions = as;
        mGrantedPermissions = as1;
    }

    public int describeContents()
    {
        return 0;
    }

    public ApplicationInfo getApplicationInfo()
    {
        return mApplicationInfo;
    }

    public String[] getGrantedPermissions()
    {
        return mGrantedPermissions;
    }

    public String getPackageName()
    {
        if(mApplicationInfo != null)
            return mApplicationInfo.packageName;
        else
            return mPackageName;
    }

    public String[] getRequestedPermissions()
    {
        return mRequestedPermissions;
    }

    public Drawable loadIcon(PackageManager packagemanager)
    {
        if(mApplicationInfo != null)
            return mApplicationInfo.loadIcon(packagemanager);
        else
            return packagemanager.getInstantAppIcon(mPackageName);
    }

    public CharSequence loadLabel(PackageManager packagemanager)
    {
        if(mApplicationInfo != null)
            return mApplicationInfo.loadLabel(packagemanager);
        else
            return mLabelText;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPackageName);
        parcel.writeCharSequence(mLabelText);
        parcel.writeStringArray(mRequestedPermissions);
        parcel.writeStringArray(mGrantedPermissions);
        parcel.writeParcelable(mApplicationInfo, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InstantAppInfo createFromParcel(Parcel parcel)
        {
            return new InstantAppInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InstantAppInfo[] newArray(int i)
        {
            return new InstantAppInfo[0];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final ApplicationInfo mApplicationInfo;
    private final String mGrantedPermissions[];
    private final CharSequence mLabelText;
    private final String mPackageName;
    private final String mRequestedPermissions[];

}
