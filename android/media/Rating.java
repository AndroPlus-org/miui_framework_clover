// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public final class Rating
    implements Parcelable
{

    private Rating(int i, float f)
    {
        mRatingStyle = i;
        mRatingValue = f;
    }

    Rating(int i, float f, Rating rating)
    {
        this(i, f);
    }

    public static Rating newHeartRating(boolean flag)
    {
        float f;
        if(flag)
            f = 1.0F;
        else
            f = 0.0F;
        return new Rating(1, f);
    }

    public static Rating newPercentageRating(float f)
    {
        if(f < 0.0F || f > 100F)
        {
            Log.e("Rating", "Invalid percentage-based rating value");
            return null;
        } else
        {
            return new Rating(6, f);
        }
    }

    public static Rating newStarRating(int i, float f)
    {
        i;
        JVM INSTR tableswitch 3 5: default 28
    //                   3 60
    //                   4 85
    //                   5 91;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_91;
_L1:
        Log.e("Rating", (new StringBuilder()).append("Invalid rating style (").append(i).append(") for a star rating").toString());
        return null;
_L2:
        float f1 = 3F;
_L5:
        if(f < 0.0F || f > f1)
        {
            Log.e("Rating", "Trying to set out of range star-based rating");
            return null;
        } else
        {
            return new Rating(i, f);
        }
_L3:
        f1 = 4F;
          goto _L5
        f1 = 5F;
          goto _L5
    }

    public static Rating newThumbRating(boolean flag)
    {
        float f;
        if(flag)
            f = 1.0F;
        else
            f = 0.0F;
        return new Rating(2, f);
    }

    public static Rating newUnratedRating(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
            return new Rating(i, -1F);
        }
    }

    public int describeContents()
    {
        return mRatingStyle;
    }

    public float getPercentRating()
    {
        if(mRatingStyle != 6 || isRated() ^ true)
            return -1F;
        else
            return mRatingValue;
    }

    public int getRatingStyle()
    {
        return mRatingStyle;
    }

    public float getStarRating()
    {
        mRatingStyle;
        JVM INSTR tableswitch 3 5: default 32
    //                   3 35
    //                   4 35
    //                   5 35;
           goto _L1 _L2 _L2 _L2
_L1:
        return -1F;
_L2:
        if(isRated())
            return mRatingValue;
        if(true) goto _L1; else goto _L3
_L3:
    }

    public boolean hasHeart()
    {
        boolean flag = true;
        if(mRatingStyle != 1)
            return false;
        if(mRatingValue != 1.0F)
            flag = false;
        return flag;
    }

    public boolean isRated()
    {
        boolean flag;
        if(mRatingValue >= 0.0F)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isThumbUp()
    {
        boolean flag = false;
        if(mRatingStyle != 2)
            return false;
        if(mRatingValue == 1.0F)
            flag = true;
        return flag;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("Rating:style=").append(mRatingStyle).append(" rating=");
        String s;
        if(mRatingValue < 0.0F)
            s = "unrated";
        else
            s = String.valueOf(mRatingValue);
        return stringbuilder.append(s).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mRatingStyle);
        parcel.writeFloat(mRatingValue);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Rating createFromParcel(Parcel parcel)
        {
            return new Rating(parcel.readInt(), parcel.readFloat(), null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Rating[] newArray(int i)
        {
            return new Rating[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    private static final float RATING_NOT_RATED = -1F;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;
    private static final String TAG = "Rating";
    private final int mRatingStyle;
    private final float mRatingValue;

}
