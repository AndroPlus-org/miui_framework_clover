// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.text.TextUtils;
import com.android.internal.util.Preconditions;
import java.util.*;

public final class TvContentRating
{

    private TvContentRating(String s, String s1, String s2, String as[])
    {
        mDomain = s;
        mRatingSystem = s1;
        mRating = s2;
        if(as == null || as.length == 0)
        {
            mSubRatings = null;
        } else
        {
            Arrays.sort(as);
            mSubRatings = as;
        }
        mHashCode = Objects.hash(new Object[] {
            mDomain, mRating
        }) * 31 + Arrays.hashCode(mSubRatings);
    }

    public static transient TvContentRating createRating(String s, String s1, String s2, String as[])
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("domain cannot be empty");
        if(TextUtils.isEmpty(s1))
            throw new IllegalArgumentException("ratingSystem cannot be empty");
        if(TextUtils.isEmpty(s2))
            throw new IllegalArgumentException("rating cannot be empty");
        else
            return new TvContentRating(s, s1, s2, as);
    }

    public static TvContentRating unflattenFromString(String s)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("ratingString cannot be empty");
        String as[] = s.split("/");
        if(as.length < 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid rating string: ").append(s).toString());
        if(as.length > 3)
        {
            s = new String[as.length - 3];
            System.arraycopy(as, 3, s, 0, s.length);
            return new TvContentRating(as[0], as[1], as[2], s);
        } else
        {
            return new TvContentRating(as[0], as[1], as[2], null);
        }
    }

    public final boolean contains(TvContentRating tvcontentrating)
    {
        Preconditions.checkNotNull(tvcontentrating);
        if(!tvcontentrating.getMainRating().equals(mRating))
            return false;
        if(!tvcontentrating.getDomain().equals(mDomain) || tvcontentrating.getRatingSystem().equals(mRatingSystem) ^ true || tvcontentrating.getMainRating().equals(mRating) ^ true)
            return false;
        List list = getSubRatings();
        tvcontentrating = tvcontentrating.getSubRatings();
        if(list == null && tvcontentrating == null)
            return true;
        if(list == null && tvcontentrating != null)
            return false;
        if(list != null && tvcontentrating == null)
            return true;
        else
            return list.containsAll(tvcontentrating);
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof TvContentRating))
            return false;
        obj = (TvContentRating)obj;
        if(mHashCode != ((TvContentRating) (obj)).mHashCode)
            return false;
        if(!TextUtils.equals(mDomain, ((TvContentRating) (obj)).mDomain))
            return false;
        if(!TextUtils.equals(mRatingSystem, ((TvContentRating) (obj)).mRatingSystem))
            return false;
        if(!TextUtils.equals(mRating, ((TvContentRating) (obj)).mRating))
            return false;
        else
            return Arrays.equals(mSubRatings, ((TvContentRating) (obj)).mSubRatings);
    }

    public String flattenToString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(mDomain);
        stringbuilder.append("/");
        stringbuilder.append(mRatingSystem);
        stringbuilder.append("/");
        stringbuilder.append(mRating);
        if(mSubRatings != null)
        {
            String as[] = mSubRatings;
            int i = 0;
            for(int j = as.length; i < j; i++)
            {
                String s = as[i];
                stringbuilder.append("/");
                stringbuilder.append(s);
            }

        }
        return stringbuilder.toString();
    }

    public String getDomain()
    {
        return mDomain;
    }

    public String getMainRating()
    {
        return mRating;
    }

    public String getRatingSystem()
    {
        return mRatingSystem;
    }

    public List getSubRatings()
    {
        if(mSubRatings == null)
            return null;
        else
            return Collections.unmodifiableList(Arrays.asList(mSubRatings));
    }

    public int hashCode()
    {
        return mHashCode;
    }

    private static final String DELIMITER = "/";
    public static final TvContentRating UNRATED = new TvContentRating("null", "null", "null", null);
    private final String mDomain;
    private final int mHashCode;
    private final String mRating;
    private final String mRatingSystem;
    private final String mSubRatings[];

}
