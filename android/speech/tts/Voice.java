// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.speech.tts;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

public class Voice
    implements Parcelable
{

    private Voice(Parcel parcel)
    {
        mName = parcel.readString();
        mLocale = (Locale)parcel.readSerializable();
        mQuality = parcel.readInt();
        mLatency = parcel.readInt();
        boolean flag;
        if(parcel.readByte() == 1)
            flag = true;
        else
            flag = false;
        mRequiresNetworkConnection = flag;
        mFeatures = new HashSet();
        Collections.addAll(mFeatures, parcel.readStringArray());
    }

    Voice(Parcel parcel, Voice voice)
    {
        this(parcel);
    }

    public Voice(String s, Locale locale, int i, int j, boolean flag, Set set)
    {
        mName = s;
        mLocale = locale;
        mQuality = i;
        mLatency = j;
        mRequiresNetworkConnection = flag;
        mFeatures = set;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        obj = (Voice)obj;
        if(mFeatures == null)
        {
            if(((Voice) (obj)).mFeatures != null)
                return false;
        } else
        if(!mFeatures.equals(((Voice) (obj)).mFeatures))
            return false;
        if(mLatency != ((Voice) (obj)).mLatency)
            return false;
        if(mLocale == null)
        {
            if(((Voice) (obj)).mLocale != null)
                return false;
        } else
        if(!mLocale.equals(((Voice) (obj)).mLocale))
            return false;
        if(mName == null)
        {
            if(((Voice) (obj)).mName != null)
                return false;
        } else
        if(!mName.equals(((Voice) (obj)).mName))
            return false;
        if(mQuality != ((Voice) (obj)).mQuality)
            return false;
        return mRequiresNetworkConnection == ((Voice) (obj)).mRequiresNetworkConnection;
    }

    public Set getFeatures()
    {
        return mFeatures;
    }

    public int getLatency()
    {
        return mLatency;
    }

    public Locale getLocale()
    {
        return mLocale;
    }

    public String getName()
    {
        return mName;
    }

    public int getQuality()
    {
        return mQuality;
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        int l;
        int i1;
        char c;
        if(mFeatures == null)
            j = 0;
        else
            j = mFeatures.hashCode();
        k = mLatency;
        if(mLocale == null)
            l = 0;
        else
            l = mLocale.hashCode();
        if(mName != null)
            i = mName.hashCode();
        i1 = mQuality;
        if(mRequiresNetworkConnection)
            c = '\u04CF';
        else
            c = '\u04D5';
        return (((((j + 31) * 31 + k) * 31 + l) * 31 + i) * 31 + i1) * 31 + c;
    }

    public boolean isNetworkConnectionRequired()
    {
        return mRequiresNetworkConnection;
    }

    public String toString()
    {
        return (new StringBuilder(64)).append("Voice[Name: ").append(mName).append(", locale: ").append(mLocale).append(", quality: ").append(mQuality).append(", latency: ").append(mLatency).append(", requiresNetwork: ").append(mRequiresNetworkConnection).append(", features: ").append(mFeatures.toString()).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mName);
        parcel.writeSerializable(mLocale);
        parcel.writeInt(mQuality);
        parcel.writeInt(mLatency);
        if(mRequiresNetworkConnection)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        parcel.writeStringList(new ArrayList(mFeatures));
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Voice createFromParcel(Parcel parcel)
        {
            return new Voice(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Voice[] newArray(int i)
        {
            return new Voice[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int LATENCY_HIGH = 400;
    public static final int LATENCY_LOW = 200;
    public static final int LATENCY_NORMAL = 300;
    public static final int LATENCY_VERY_HIGH = 500;
    public static final int LATENCY_VERY_LOW = 100;
    public static final int QUALITY_HIGH = 400;
    public static final int QUALITY_LOW = 200;
    public static final int QUALITY_NORMAL = 300;
    public static final int QUALITY_VERY_HIGH = 500;
    public static final int QUALITY_VERY_LOW = 100;
    private final Set mFeatures;
    private final int mLatency;
    private final Locale mLocale;
    private final String mName;
    private final int mQuality;
    private final boolean mRequiresNetworkConnection;

}
