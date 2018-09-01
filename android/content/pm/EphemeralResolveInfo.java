// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content.pm;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.content.pm:
//            InstantAppResolveInfo, EphemeralIntentFilter, InstantAppIntentFilter

public final class EphemeralResolveInfo
    implements Parcelable
{
    public static final class EphemeralDigest
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public byte[][] getDigestBytes()
        {
            return mInstantAppDigest.getDigestBytes();
        }

        public int[] getDigestPrefix()
        {
            return mInstantAppDigest.getDigestPrefix();
        }

        InstantAppResolveInfo.InstantAppDigest getInstantAppDigest()
        {
            return mInstantAppDigest;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeParcelable(mInstantAppDigest, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public EphemeralDigest createFromParcel(Parcel parcel)
            {
                return new EphemeralDigest(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public EphemeralDigest[] newArray(int i)
            {
                return new EphemeralDigest[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final InstantAppResolveInfo.InstantAppDigest mInstantAppDigest;


        EphemeralDigest(Parcel parcel)
        {
            mInstantAppDigest = (InstantAppResolveInfo.InstantAppDigest)parcel.readParcelable(null);
        }

        public EphemeralDigest(String s)
        {
            this(s, -1);
        }

        public EphemeralDigest(String s, int i)
        {
            mInstantAppDigest = new InstantAppResolveInfo.InstantAppDigest(s, i);
        }
    }


    public EphemeralResolveInfo(EphemeralDigest ephemeraldigest, String s, List list)
    {
        this(ephemeraldigest, s, list, -1);
    }

    public EphemeralResolveInfo(EphemeralDigest ephemeraldigest, String s, List list, int i)
    {
        mInstantAppResolveInfo = new InstantAppResolveInfo(ephemeraldigest.getInstantAppDigest(), s, createInstantAppIntentFilterList(list), i);
        mLegacyFilters = null;
    }

    public EphemeralResolveInfo(Uri uri, String s, List list)
    {
        while(uri == null || s == null || list == null || list.isEmpty()) 
            throw new IllegalArgumentException();
        ArrayList arraylist = new ArrayList(1);
        arraylist.add(new EphemeralIntentFilter(s, list));
        mInstantAppResolveInfo = new InstantAppResolveInfo(uri.getHost(), s, createInstantAppIntentFilterList(arraylist));
        mLegacyFilters = new ArrayList(list.size());
        mLegacyFilters.addAll(list);
    }

    EphemeralResolveInfo(Parcel parcel)
    {
        mInstantAppResolveInfo = (InstantAppResolveInfo)parcel.readParcelable(null);
        mLegacyFilters = new ArrayList();
        parcel.readList(mLegacyFilters, null);
    }

    public EphemeralResolveInfo(String s, String s1, List list)
    {
        this(new EphemeralDigest(s), s1, list);
    }

    private static List createInstantAppIntentFilterList(List list)
    {
        if(list == null)
            return null;
        int i = list.size();
        ArrayList arraylist = new ArrayList(i);
        for(int j = 0; j < i; j++)
            arraylist.add(((EphemeralIntentFilter)list.get(j)).getInstantAppIntentFilter());

        return arraylist;
    }

    public int describeContents()
    {
        return 0;
    }

    public byte[] getDigestBytes()
    {
        return mInstantAppResolveInfo.getDigestBytes();
    }

    public int getDigestPrefix()
    {
        return mInstantAppResolveInfo.getDigestPrefix();
    }

    public List getFilters()
    {
        return mLegacyFilters;
    }

    public InstantAppResolveInfo getInstantAppResolveInfo()
    {
        return mInstantAppResolveInfo;
    }

    public List getIntentFilters()
    {
        List list = mInstantAppResolveInfo.getIntentFilters();
        int i = list.size();
        ArrayList arraylist = new ArrayList(i);
        for(int j = 0; j < i; j++)
            arraylist.add(new EphemeralIntentFilter((InstantAppIntentFilter)list.get(j)));

        return arraylist;
    }

    public String getPackageName()
    {
        return mInstantAppResolveInfo.getPackageName();
    }

    public int getVersionCode()
    {
        return mInstantAppResolveInfo.getVersionCode();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mInstantAppResolveInfo, i);
        parcel.writeList(mLegacyFilters);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public EphemeralResolveInfo createFromParcel(Parcel parcel)
        {
            return new EphemeralResolveInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public EphemeralResolveInfo[] newArray(int i)
        {
            return new EphemeralResolveInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String SHA_ALGORITHM = "SHA-256";
    private final InstantAppResolveInfo mInstantAppResolveInfo;
    private final List mLegacyFilters;

}
