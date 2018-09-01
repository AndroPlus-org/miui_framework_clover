// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.usage;

import android.os.*;
import android.util.ArrayMap;
import java.util.Iterator;

public final class UsageStats
    implements Parcelable
{

    public UsageStats()
    {
    }

    public UsageStats(UsageStats usagestats)
    {
        mPackageName = usagestats.mPackageName;
        mBeginTimeStamp = usagestats.mBeginTimeStamp;
        mEndTimeStamp = usagestats.mEndTimeStamp;
        mLastTimeUsed = usagestats.mLastTimeUsed;
        mTotalTimeInForeground = usagestats.mTotalTimeInForeground;
        mLaunchCount = usagestats.mLaunchCount;
        mLastEvent = usagestats.mLastEvent;
        mChooserCounts = usagestats.mChooserCounts;
    }

    public void add(UsageStats usagestats)
    {
        if(!mPackageName.equals(usagestats.mPackageName))
            throw new IllegalArgumentException((new StringBuilder()).append("Can't merge UsageStats for package '").append(mPackageName).append("' with UsageStats for package '").append(usagestats.mPackageName).append("'.").toString());
        if(usagestats.mBeginTimeStamp > mBeginTimeStamp)
        {
            mLastEvent = Math.max(mLastEvent, usagestats.mLastEvent);
            mLastTimeUsed = Math.max(mLastTimeUsed, usagestats.mLastTimeUsed);
        }
        mBeginTimeStamp = Math.min(mBeginTimeStamp, usagestats.mBeginTimeStamp);
        mEndTimeStamp = Math.max(mEndTimeStamp, usagestats.mEndTimeStamp);
        mTotalTimeInForeground = mTotalTimeInForeground + usagestats.mTotalTimeInForeground;
        mLaunchCount = mLaunchCount + usagestats.mLaunchCount;
        if(mChooserCounts != null) goto _L2; else goto _L1
_L1:
        mChooserCounts = usagestats.mChooserCounts;
_L4:
        return;
_L2:
        if(usagestats.mChooserCounts != null)
        {
            int i = usagestats.mChooserCounts.size();
            int j = 0;
            while(j < i) 
            {
                String s = (String)usagestats.mChooserCounts.keyAt(j);
                ArrayMap arraymap = (ArrayMap)usagestats.mChooserCounts.valueAt(j);
                if(!mChooserCounts.containsKey(s) || mChooserCounts.get(s) == null)
                {
                    mChooserCounts.put(s, arraymap);
                } else
                {
                    int k = arraymap.size();
                    int l = 0;
                    while(l < k) 
                    {
                        String s1 = (String)arraymap.keyAt(l);
                        int i1 = ((Integer)arraymap.valueAt(l)).intValue();
                        int j1 = ((Integer)((ArrayMap)mChooserCounts.get(s)).getOrDefault(s1, Integer.valueOf(0))).intValue();
                        ((ArrayMap)mChooserCounts.get(s)).put(s1, Integer.valueOf(j1 + i1));
                        l++;
                    }
                }
                j++;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int describeContents()
    {
        return 0;
    }

    public long getFirstTimeStamp()
    {
        return mBeginTimeStamp;
    }

    public long getLastTimeStamp()
    {
        return mEndTimeStamp;
    }

    public long getLastTimeUsed()
    {
        return mLastTimeUsed;
    }

    public UsageStats getObfuscatedForInstantApp()
    {
        UsageStats usagestats = new UsageStats(this);
        usagestats.mPackageName = "android.instant_app";
        return usagestats;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public long getTotalTimeInForeground()
    {
        return mTotalTimeInForeground;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mPackageName);
        parcel.writeLong(mBeginTimeStamp);
        parcel.writeLong(mEndTimeStamp);
        parcel.writeLong(mLastTimeUsed);
        parcel.writeLong(mTotalTimeInForeground);
        parcel.writeInt(mLaunchCount);
        parcel.writeInt(mLastEvent);
        Bundle bundle = new Bundle();
        if(mChooserCounts != null)
        {
            int j = mChooserCounts.size();
            for(i = 0; i < j; i++)
            {
                String s = (String)mChooserCounts.keyAt(i);
                ArrayMap arraymap = (ArrayMap)mChooserCounts.valueAt(i);
                Bundle bundle1 = new Bundle();
                int k = arraymap.size();
                for(int l = 0; l < k; l++)
                    bundle1.putInt((String)arraymap.keyAt(l), ((Integer)arraymap.valueAt(l)).intValue());

                bundle.putBundle(s, bundle1);
            }

        }
        parcel.writeBundle(bundle);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public UsageStats createFromParcel(Parcel parcel)
        {
            UsageStats usagestats = new UsageStats();
            usagestats.mPackageName = parcel.readString();
            usagestats.mBeginTimeStamp = parcel.readLong();
            usagestats.mEndTimeStamp = parcel.readLong();
            usagestats.mLastTimeUsed = parcel.readLong();
            usagestats.mTotalTimeInForeground = parcel.readLong();
            usagestats.mLaunchCount = parcel.readInt();
            usagestats.mLastEvent = parcel.readInt();
            parcel = parcel.readBundle();
            if(parcel != null)
            {
                usagestats.mChooserCounts = new ArrayMap();
                for(Iterator iterator = parcel.keySet().iterator(); iterator.hasNext();)
                {
                    String s = (String)iterator.next();
                    if(!usagestats.mChooserCounts.containsKey(s))
                    {
                        ArrayMap arraymap = new ArrayMap();
                        usagestats.mChooserCounts.put(s, arraymap);
                    }
                    Bundle bundle = parcel.getBundle(s);
                    if(bundle != null)
                    {
                        Iterator iterator1 = bundle.keySet().iterator();
                        while(iterator1.hasNext()) 
                        {
                            String s1 = (String)iterator1.next();
                            int i = bundle.getInt(s1);
                            if(i > 0)
                                ((ArrayMap)usagestats.mChooserCounts.get(s)).put(s1, Integer.valueOf(i));
                        }
                    }
                }

            }
            return usagestats;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public UsageStats[] newArray(int i)
        {
            return new UsageStats[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public long mBeginTimeStamp;
    public ArrayMap mChooserCounts;
    public long mEndTimeStamp;
    public int mLastEvent;
    public long mLastTimeUsed;
    public int mLaunchCount;
    public String mPackageName;
    public long mTotalTimeInForeground;

}
