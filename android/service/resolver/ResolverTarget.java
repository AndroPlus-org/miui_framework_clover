// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.service.resolver;

import android.os.Parcel;
import android.os.Parcelable;

public final class ResolverTarget
    implements Parcelable
{

    public ResolverTarget()
    {
    }

    ResolverTarget(Parcel parcel)
    {
        mRecencyScore = parcel.readFloat();
        mTimeSpentScore = parcel.readFloat();
        mLaunchScore = parcel.readFloat();
        mChooserScore = parcel.readFloat();
        mSelectProbability = parcel.readFloat();
    }

    public int describeContents()
    {
        return 0;
    }

    public float getChooserScore()
    {
        return mChooserScore;
    }

    public float getLaunchScore()
    {
        return mLaunchScore;
    }

    public float getRecencyScore()
    {
        return mRecencyScore;
    }

    public float getSelectProbability()
    {
        return mSelectProbability;
    }

    public float getTimeSpentScore()
    {
        return mTimeSpentScore;
    }

    public void setChooserScore(float f)
    {
        mChooserScore = f;
    }

    public void setLaunchScore(float f)
    {
        mLaunchScore = f;
    }

    public void setRecencyScore(float f)
    {
        mRecencyScore = f;
    }

    public void setSelectProbability(float f)
    {
        mSelectProbability = f;
    }

    public void setTimeSpentScore(float f)
    {
        mTimeSpentScore = f;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ResolverTarget{").append(mRecencyScore).append(", ").append(mTimeSpentScore).append(", ").append(mLaunchScore).append(", ").append(mChooserScore).append(", ").append(mSelectProbability).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeFloat(mRecencyScore);
        parcel.writeFloat(mTimeSpentScore);
        parcel.writeFloat(mLaunchScore);
        parcel.writeFloat(mChooserScore);
        parcel.writeFloat(mSelectProbability);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ResolverTarget createFromParcel(Parcel parcel)
        {
            return new ResolverTarget(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ResolverTarget[] newArray(int i)
        {
            return new ResolverTarget[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "ResolverTarget";
    private float mChooserScore;
    private float mLaunchScore;
    private float mRecencyScore;
    private float mSelectProbability;
    private float mTimeSpentScore;

}
