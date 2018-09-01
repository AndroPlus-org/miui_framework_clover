// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.*;
import com.android.internal.telecom.IVideoProvider;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.telecom:
//            Connection, PhoneAccountHandle, StatusHints

public final class ParcelableConference
    implements Parcelable
{

    public ParcelableConference(PhoneAccountHandle phoneaccounthandle, int i, int j, int k, List list, IVideoProvider ivideoprovider, int l, 
            long l1, long l2, StatusHints statushints, Bundle bundle)
    {
        mConnectTimeMillis = 0L;
        mConnectElapsedTimeMillis = 0L;
        mPhoneAccount = phoneaccounthandle;
        mState = i;
        mConnectionCapabilities = j;
        mConnectionProperties = k;
        mConnectionIds = list;
        mVideoProvider = ivideoprovider;
        mVideoState = l;
        mConnectTimeMillis = l1;
        mStatusHints = statushints;
        mExtras = bundle;
        mConnectElapsedTimeMillis = l2;
    }

    public int describeContents()
    {
        return 0;
    }

    public long getConnectElapsedTimeMillis()
    {
        return mConnectElapsedTimeMillis;
    }

    public long getConnectTimeMillis()
    {
        return mConnectTimeMillis;
    }

    public int getConnectionCapabilities()
    {
        return mConnectionCapabilities;
    }

    public List getConnectionIds()
    {
        return mConnectionIds;
    }

    public int getConnectionProperties()
    {
        return mConnectionProperties;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public PhoneAccountHandle getPhoneAccount()
    {
        return mPhoneAccount;
    }

    public int getState()
    {
        return mState;
    }

    public StatusHints getStatusHints()
    {
        return mStatusHints;
    }

    public IVideoProvider getVideoProvider()
    {
        return mVideoProvider;
    }

    public int getVideoState()
    {
        return mVideoState;
    }

    public String toString()
    {
        return "account: " + mPhoneAccount + ", state: " + Connection.stateToString(mState) + ", capabilities: " + Connection.capabilitiesToString(mConnectionCapabilities) + ", properties: " + Connection.propertiesToString(mConnectionProperties) + ", connectTime: " + mConnectTimeMillis + ", children: " + mConnectionIds + ", VideoState: " + mVideoState + ", VideoProvider: " + mVideoProvider;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        android.os.IBinder ibinder = null;
        parcel.writeParcelable(mPhoneAccount, 0);
        parcel.writeInt(mState);
        parcel.writeInt(mConnectionCapabilities);
        parcel.writeList(mConnectionIds);
        parcel.writeLong(mConnectTimeMillis);
        if(mVideoProvider != null)
            ibinder = mVideoProvider.asBinder();
        parcel.writeStrongBinder(ibinder);
        parcel.writeInt(mVideoState);
        parcel.writeParcelable(mStatusHints, 0);
        parcel.writeBundle(mExtras);
        parcel.writeInt(mConnectionProperties);
        parcel.writeLong(mConnectElapsedTimeMillis);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ParcelableConference createFromParcel(Parcel parcel)
        {
            Object obj = android/telecom/ParcelableConference.getClassLoader();
            PhoneAccountHandle phoneaccounthandle = (PhoneAccountHandle)parcel.readParcelable(((ClassLoader) (obj)));
            int i = parcel.readInt();
            int j = parcel.readInt();
            ArrayList arraylist = new ArrayList(2);
            parcel.readList(arraylist, ((ClassLoader) (obj)));
            long l = parcel.readLong();
            IVideoProvider ivideoprovider = com.android.internal.telecom.IVideoProvider.Stub.asInterface(parcel.readStrongBinder());
            int k = parcel.readInt();
            StatusHints statushints = (StatusHints)parcel.readParcelable(((ClassLoader) (obj)));
            obj = parcel.readBundle(((ClassLoader) (obj)));
            return new ParcelableConference(phoneaccounthandle, i, j, parcel.readInt(), arraylist, ivideoprovider, k, l, parcel.readLong(), statushints, ((Bundle) (obj)));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ParcelableConference[] newArray(int i)
        {
            return new ParcelableConference[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private long mConnectElapsedTimeMillis;
    private long mConnectTimeMillis;
    private int mConnectionCapabilities;
    private List mConnectionIds;
    private int mConnectionProperties;
    private Bundle mExtras;
    private PhoneAccountHandle mPhoneAccount;
    private int mState;
    private StatusHints mStatusHints;
    private final IVideoProvider mVideoProvider;
    private final int mVideoState;

}
