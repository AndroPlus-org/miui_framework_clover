// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.notification;

import android.os.*;

public class NotificationRankingUpdate
    implements Parcelable
{

    public NotificationRankingUpdate(Parcel parcel)
    {
        mKeys = parcel.readStringArray();
        mInterceptedKeys = parcel.readStringArray();
        mVisibilityOverrides = parcel.readBundle();
        mSuppressedVisualEffects = parcel.readBundle();
        mImportance = new int[mKeys.length];
        parcel.readIntArray(mImportance);
        mImportanceExplanation = parcel.readBundle();
        mOverrideGroupKeys = parcel.readBundle();
        mChannels = parcel.readBundle();
        mOverridePeople = parcel.readBundle();
        mSnoozeCriteria = parcel.readBundle();
        mShowBadge = parcel.readBundle();
    }

    public NotificationRankingUpdate(String as[], String as1[], Bundle bundle, Bundle bundle1, int ai[], Bundle bundle2, Bundle bundle3, 
            Bundle bundle4, Bundle bundle5, Bundle bundle6, Bundle bundle7)
    {
        mKeys = as;
        mInterceptedKeys = as1;
        mVisibilityOverrides = bundle;
        mSuppressedVisualEffects = bundle1;
        mImportance = ai;
        mImportanceExplanation = bundle2;
        mOverrideGroupKeys = bundle3;
        mChannels = bundle4;
        mOverridePeople = bundle5;
        mSnoozeCriteria = bundle6;
        mShowBadge = bundle7;
    }

    public int describeContents()
    {
        return 0;
    }

    public Bundle getChannels()
    {
        return mChannels;
    }

    public int[] getImportance()
    {
        return mImportance;
    }

    public Bundle getImportanceExplanation()
    {
        return mImportanceExplanation;
    }

    public String[] getInterceptedKeys()
    {
        return mInterceptedKeys;
    }

    public String[] getOrderedKeys()
    {
        return mKeys;
    }

    public Bundle getOverrideGroupKeys()
    {
        return mOverrideGroupKeys;
    }

    public Bundle getOverridePeople()
    {
        return mOverridePeople;
    }

    public Bundle getShowBadge()
    {
        return mShowBadge;
    }

    public Bundle getSnoozeCriteria()
    {
        return mSnoozeCriteria;
    }

    public Bundle getSuppressedVisualEffects()
    {
        return mSuppressedVisualEffects;
    }

    public Bundle getVisibilityOverrides()
    {
        return mVisibilityOverrides;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStringArray(mKeys);
        parcel.writeStringArray(mInterceptedKeys);
        parcel.writeBundle(mVisibilityOverrides);
        parcel.writeBundle(mSuppressedVisualEffects);
        parcel.writeIntArray(mImportance);
        parcel.writeBundle(mImportanceExplanation);
        parcel.writeBundle(mOverrideGroupKeys);
        parcel.writeBundle(mChannels);
        parcel.writeBundle(mOverridePeople);
        parcel.writeBundle(mSnoozeCriteria);
        parcel.writeBundle(mShowBadge);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NotificationRankingUpdate createFromParcel(Parcel parcel)
        {
            return new NotificationRankingUpdate(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NotificationRankingUpdate[] newArray(int i)
        {
            return new NotificationRankingUpdate[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Bundle mChannels;
    private final int mImportance[];
    private final Bundle mImportanceExplanation;
    private final String mInterceptedKeys[];
    private final String mKeys[];
    private final Bundle mOverrideGroupKeys;
    private final Bundle mOverridePeople;
    private final Bundle mShowBadge;
    private final Bundle mSnoozeCriteria;
    private final Bundle mSuppressedVisualEffects;
    private final Bundle mVisibilityOverrides;

}
