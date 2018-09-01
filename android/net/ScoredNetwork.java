// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.*;
import java.util.Objects;

// Referenced classes of package android.net:
//            NetworkKey, RssiCurve

public class ScoredNetwork
    implements Parcelable
{

    public ScoredNetwork(NetworkKey networkkey, RssiCurve rssicurve)
    {
        this(networkkey, rssicurve, false);
    }

    public ScoredNetwork(NetworkKey networkkey, RssiCurve rssicurve, boolean flag)
    {
        this(networkkey, rssicurve, flag, null);
    }

    public ScoredNetwork(NetworkKey networkkey, RssiCurve rssicurve, boolean flag, Bundle bundle)
    {
        networkKey = networkkey;
        rssiCurve = rssicurve;
        meteredHint = flag;
        attributes = bundle;
    }

    private ScoredNetwork(Parcel parcel)
    {
        networkKey = (NetworkKey)NetworkKey.CREATOR.createFromParcel(parcel);
        boolean flag;
        if(parcel.readByte() == 1)
            rssiCurve = (RssiCurve)RssiCurve.CREATOR.createFromParcel(parcel);
        else
            rssiCurve = null;
        if(parcel.readByte() == 1)
            flag = true;
        else
            flag = false;
        meteredHint = flag;
        attributes = parcel.readBundle();
    }

    ScoredNetwork(Parcel parcel, ScoredNetwork scorednetwork)
    {
        this(parcel);
    }

    public int calculateBadge(int i)
    {
        if(attributes != null && attributes.containsKey("android.net.attributes.key.BADGING_CURVE"))
            return ((RssiCurve)attributes.getParcelable("android.net.attributes.key.BADGING_CURVE")).lookupScore(i);
        else
            return 0;
    }

    public int calculateRankingScore(int i)
        throws UnsupportedOperationException
    {
        if(!hasRankingScore())
            throw new UnsupportedOperationException("Either rssiCurve or rankingScoreOffset is required to calculate the ranking score");
        int j = 0;
        if(attributes != null)
            j = attributes.getInt("android.net.attributes.key.RANKING_SCORE_OFFSET", 0) + 0;
        if(rssiCurve == null)
            i = 0;
        else
            i = rssiCurve.lookupScore(i) << 8;
        try
        {
            j = Math.addExact(i, j);
        }
        catch(ArithmeticException arithmeticexception)
        {
            if(i < 0)
                i = 0x80000000;
            else
                i = 0x7fffffff;
            return i;
        }
        return j;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (ScoredNetwork)obj;
        boolean flag1 = flag;
        if(Objects.equals(networkKey, ((ScoredNetwork) (obj)).networkKey))
        {
            flag1 = flag;
            if(Objects.equals(rssiCurve, ((ScoredNetwork) (obj)).rssiCurve))
            {
                flag1 = flag;
                if(Objects.equals(Boolean.valueOf(meteredHint), Boolean.valueOf(((ScoredNetwork) (obj)).meteredHint)))
                    flag1 = Objects.equals(attributes, ((ScoredNetwork) (obj)).attributes);
            }
        }
        return flag1;
    }

    public boolean hasRankingScore()
    {
        boolean flag;
        if(rssiCurve == null)
        {
            if(attributes != null)
                flag = attributes.containsKey("android.net.attributes.key.RANKING_SCORE_OFFSET");
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            networkKey, rssiCurve, Boolean.valueOf(meteredHint), attributes
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder((new StringBuilder()).append("ScoredNetwork{networkKey=").append(networkKey).append(", rssiCurve=").append(rssiCurve).append(", meteredHint=").append(meteredHint).toString());
        if(attributes != null && attributes.isEmpty() ^ true)
            stringbuilder.append(", attributes=").append(attributes);
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        networkKey.writeToParcel(parcel, i);
        if(rssiCurve != null)
        {
            parcel.writeByte((byte)1);
            rssiCurve.writeToParcel(parcel, i);
        } else
        {
            parcel.writeByte((byte)0);
        }
        if(meteredHint)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeByte((byte)i);
        parcel.writeBundle(attributes);
    }

    public static final String ATTRIBUTES_KEY_BADGING_CURVE = "android.net.attributes.key.BADGING_CURVE";
    public static final String ATTRIBUTES_KEY_HAS_CAPTIVE_PORTAL = "android.net.attributes.key.HAS_CAPTIVE_PORTAL";
    public static final String ATTRIBUTES_KEY_RANKING_SCORE_OFFSET = "android.net.attributes.key.RANKING_SCORE_OFFSET";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ScoredNetwork createFromParcel(Parcel parcel)
        {
            return new ScoredNetwork(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ScoredNetwork[] newArray(int i)
        {
            return new ScoredNetwork[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final Bundle attributes;
    public final boolean meteredHint;
    public final NetworkKey networkKey;
    public final RssiCurve rssiCurve;

}
