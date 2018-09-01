// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import miui.telephony.PhoneNumberUtils;

// Referenced classes of package android.telecom:
//            Log, Connection, ParcelableCall

public class ConferenceParticipant
    implements Parcelable
{

    public ConferenceParticipant(Uri uri, String s, Uri uri1, int i)
    {
        mHandle = uri;
        mDisplayName = s;
        mEndpoint = uri1;
        mState = i;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getDisplayName()
    {
        return mDisplayName;
    }

    public Uri getEndpoint()
    {
        return mEndpoint;
    }

    public Uri getHandle()
    {
        return mHandle;
    }

    public int getState()
    {
        return mState;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[ConferenceParticipant Handle: ");
        stringbuilder.append(PhoneNumberUtils.toLogSafePhoneNumber(mHandle.toString()));
        stringbuilder.append(" DisplayName: ");
        stringbuilder.append(Log.pii(mDisplayName));
        stringbuilder.append(" Endpoint: ");
        stringbuilder.append(Log.pii(mEndpoint));
        stringbuilder.append(" State: ");
        stringbuilder.append(Connection.stateToString(mState));
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mHandle, 0);
        parcel.writeString(mDisplayName);
        parcel.writeParcelable(mEndpoint, 0);
        parcel.writeInt(mState);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ConferenceParticipant createFromParcel(Parcel parcel)
        {
            ClassLoader classloader = android/telecom/ParcelableCall.getClassLoader();
            return new ConferenceParticipant((Uri)parcel.readParcelable(classloader), parcel.readString(), (Uri)parcel.readParcelable(classloader), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ConferenceParticipant[] newArray(int i)
        {
            return new ConferenceParticipant[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final String mDisplayName;
    private final Uri mEndpoint;
    private final Uri mHandle;
    private final int mState;

}
