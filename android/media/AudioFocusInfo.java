// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

// Referenced classes of package android.media:
//            AudioAttributes

public final class AudioFocusInfo
    implements Parcelable
{

    public AudioFocusInfo(AudioAttributes audioattributes, int i, String s, String s1, int j, int k, int l, 
            int i1)
    {
        AudioAttributes audioattributes1 = audioattributes;
        if(audioattributes == null)
            audioattributes1 = (new AudioAttributes.Builder()).build();
        mAttributes = audioattributes1;
        mClientUid = i;
        audioattributes = s;
        if(s == null)
            audioattributes = "";
        mClientId = audioattributes;
        audioattributes = s1;
        if(s1 == null)
            audioattributes = "";
        mPackageName = audioattributes;
        mGainRequest = j;
        mLossReceived = k;
        mFlags = l;
        mSdkTarget = i1;
    }

    public void clearLossReceived()
    {
        mLossReceived = 0;
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
        obj = (AudioFocusInfo)obj;
        if(!mAttributes.equals(((AudioFocusInfo) (obj)).mAttributes))
            return false;
        if(mClientUid != ((AudioFocusInfo) (obj)).mClientUid)
            return false;
        if(!mClientId.equals(((AudioFocusInfo) (obj)).mClientId))
            return false;
        if(!mPackageName.equals(((AudioFocusInfo) (obj)).mPackageName))
            return false;
        if(mGainRequest != ((AudioFocusInfo) (obj)).mGainRequest)
            return false;
        if(mLossReceived != ((AudioFocusInfo) (obj)).mLossReceived)
            return false;
        if(mFlags != ((AudioFocusInfo) (obj)).mFlags)
            return false;
        return mSdkTarget == ((AudioFocusInfo) (obj)).mSdkTarget;
    }

    public AudioAttributes getAttributes()
    {
        return mAttributes;
    }

    public String getClientId()
    {
        return mClientId;
    }

    public int getClientUid()
    {
        return mClientUid;
    }

    public int getFlags()
    {
        return mFlags;
    }

    public int getGainRequest()
    {
        return mGainRequest;
    }

    public int getLossReceived()
    {
        return mLossReceived;
    }

    public String getPackageName()
    {
        return mPackageName;
    }

    public int getSdkTarget()
    {
        return mSdkTarget;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            mAttributes, Integer.valueOf(mClientUid), mClientId, mPackageName, Integer.valueOf(mGainRequest), Integer.valueOf(mFlags)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        mAttributes.writeToParcel(parcel, i);
        parcel.writeInt(mClientUid);
        parcel.writeString(mClientId);
        parcel.writeString(mPackageName);
        parcel.writeInt(mGainRequest);
        parcel.writeInt(mLossReceived);
        parcel.writeInt(mFlags);
        parcel.writeInt(mSdkTarget);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AudioFocusInfo createFromParcel(Parcel parcel)
        {
            return new AudioFocusInfo((AudioAttributes)AudioAttributes.CREATOR.createFromParcel(parcel), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AudioFocusInfo[] newArray(int i)
        {
            return new AudioFocusInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final AudioAttributes mAttributes;
    private final String mClientId;
    private final int mClientUid;
    private int mFlags;
    private int mGainRequest;
    private int mLossReceived;
    private final String mPackageName;
    private final int mSdkTarget;

}
