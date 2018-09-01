// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.*;
import com.android.internal.telecom.IVideoProvider;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.telecom:
//            Connection, DisconnectCause, PhoneAccountHandle, StatusHints

public final class ParcelableConnection
    implements Parcelable
{

    public ParcelableConnection(PhoneAccountHandle phoneaccounthandle, int i, int j, int k, int l, Uri uri, int i1, 
            String s, int j1, IVideoProvider ivideoprovider, int k1, boolean flag, boolean flag1, long l1, long l2, StatusHints statushints, DisconnectCause disconnectcause, List list, Bundle bundle)
    {
        mPhoneAccount = phoneaccounthandle;
        mState = i;
        mConnectionCapabilities = j;
        mConnectionProperties = k;
        mSupportedAudioRoutes = l;
        mAddress = uri;
        mAddressPresentation = i1;
        mCallerDisplayName = s;
        mCallerDisplayNamePresentation = j1;
        mVideoProvider = ivideoprovider;
        mVideoState = k1;
        mRingbackRequested = flag;
        mIsVoipAudioMode = flag1;
        mConnectTimeMillis = l1;
        mConnectElapsedTimeMillis = l2;
        mStatusHints = statushints;
        mDisconnectCause = disconnectcause;
        mConferenceableConnectionIds = list;
        mExtras = bundle;
        mParentCallId = null;
    }

    public ParcelableConnection(PhoneAccountHandle phoneaccounthandle, int i, int j, int k, int l, Uri uri, int i1, 
            String s, int j1, IVideoProvider ivideoprovider, int k1, boolean flag, boolean flag1, long l1, long l2, StatusHints statushints, DisconnectCause disconnectcause, List list, Bundle bundle, 
            String s1)
    {
        this(phoneaccounthandle, i, j, k, l, uri, i1, s, j1, ivideoprovider, k1, flag, flag1, l1, l2, statushints, disconnectcause, list, bundle);
        mParentCallId = s1;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getCallerDisplayName()
    {
        return mCallerDisplayName;
    }

    public int getCallerDisplayNamePresentation()
    {
        return mCallerDisplayNamePresentation;
    }

    public final List getConferenceableConnectionIds()
    {
        return mConferenceableConnectionIds;
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

    public int getConnectionProperties()
    {
        return mConnectionProperties;
    }

    public final DisconnectCause getDisconnectCause()
    {
        return mDisconnectCause;
    }

    public final Bundle getExtras()
    {
        return mExtras;
    }

    public Uri getHandle()
    {
        return mAddress;
    }

    public int getHandlePresentation()
    {
        return mAddressPresentation;
    }

    public boolean getIsVoipAudioMode()
    {
        return mIsVoipAudioMode;
    }

    public final String getParentCallId()
    {
        return mParentCallId;
    }

    public PhoneAccountHandle getPhoneAccount()
    {
        return mPhoneAccount;
    }

    public int getState()
    {
        return mState;
    }

    public final StatusHints getStatusHints()
    {
        return mStatusHints;
    }

    public int getSupportedAudioRoutes()
    {
        return mSupportedAudioRoutes;
    }

    public IVideoProvider getVideoProvider()
    {
        return mVideoProvider;
    }

    public int getVideoState()
    {
        return mVideoState;
    }

    public boolean isRingbackRequested()
    {
        return mRingbackRequested;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ParcelableConnection [act:").append(mPhoneAccount).append("], state:").append(mState).append(", capabilities:").append(Connection.capabilitiesToString(mConnectionCapabilities)).append(", properties:").append(Connection.propertiesToString(mConnectionProperties)).append(", extras:").append(mExtras).append(", parent:").append(mParentCallId).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        android.os.IBinder ibinder = null;
        boolean flag = true;
        parcel.writeParcelable(mPhoneAccount, 0);
        parcel.writeInt(mState);
        parcel.writeInt(mConnectionCapabilities);
        parcel.writeParcelable(mAddress, 0);
        parcel.writeInt(mAddressPresentation);
        parcel.writeString(mCallerDisplayName);
        parcel.writeInt(mCallerDisplayNamePresentation);
        if(mVideoProvider != null)
            ibinder = mVideoProvider.asBinder();
        parcel.writeStrongBinder(ibinder);
        parcel.writeInt(mVideoState);
        if(mRingbackRequested)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        if(mIsVoipAudioMode)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeByte((byte)i);
        parcel.writeLong(mConnectTimeMillis);
        parcel.writeParcelable(mStatusHints, 0);
        parcel.writeParcelable(mDisconnectCause, 0);
        parcel.writeStringList(mConferenceableConnectionIds);
        parcel.writeBundle(mExtras);
        parcel.writeInt(mConnectionProperties);
        parcel.writeInt(mSupportedAudioRoutes);
        parcel.writeString(mParentCallId);
        parcel.writeLong(mConnectElapsedTimeMillis);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ParcelableConnection createFromParcel(Parcel parcel)
        {
            Object obj = android/telecom/ParcelableConnection.getClassLoader();
            PhoneAccountHandle phoneaccounthandle = (PhoneAccountHandle)parcel.readParcelable(((ClassLoader) (obj)));
            int i = parcel.readInt();
            int j = parcel.readInt();
            Uri uri = (Uri)parcel.readParcelable(((ClassLoader) (obj)));
            int k = parcel.readInt();
            String s = parcel.readString();
            int l = parcel.readInt();
            IVideoProvider ivideoprovider = com.android.internal.telecom.IVideoProvider.Stub.asInterface(parcel.readStrongBinder());
            int i1 = parcel.readInt();
            boolean flag;
            boolean flag1;
            long l1;
            StatusHints statushints;
            DisconnectCause disconnectcause;
            ArrayList arraylist;
            Bundle bundle;
            int j1;
            int k1;
            if(parcel.readByte() == 1)
                flag = true;
            else
                flag = false;
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            l1 = parcel.readLong();
            statushints = (StatusHints)parcel.readParcelable(((ClassLoader) (obj)));
            disconnectcause = (DisconnectCause)parcel.readParcelable(((ClassLoader) (obj)));
            arraylist = new ArrayList();
            parcel.readStringList(arraylist);
            bundle = Bundle.setDefusable(parcel.readBundle(((ClassLoader) (obj))), true);
            j1 = parcel.readInt();
            k1 = parcel.readInt();
            obj = parcel.readString();
            return new ParcelableConnection(phoneaccounthandle, i, j, j1, k1, uri, k, s, l, ivideoprovider, i1, flag, flag1, l1, parcel.readLong(), statushints, disconnectcause, arraylist, bundle, ((String) (obj)));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ParcelableConnection[] newArray(int i)
        {
            return new ParcelableConnection[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Uri mAddress;
    private final int mAddressPresentation;
    private final String mCallerDisplayName;
    private final int mCallerDisplayNamePresentation;
    private final List mConferenceableConnectionIds;
    private final long mConnectElapsedTimeMillis;
    private final long mConnectTimeMillis;
    private final int mConnectionCapabilities;
    private final int mConnectionProperties;
    private final DisconnectCause mDisconnectCause;
    private final Bundle mExtras;
    private final boolean mIsVoipAudioMode;
    private String mParentCallId;
    private final PhoneAccountHandle mPhoneAccount;
    private final boolean mRingbackRequested;
    private final int mState;
    private final StatusHints mStatusHints;
    private final int mSupportedAudioRoutes;
    private final IVideoProvider mVideoProvider;
    private final int mVideoState;

}
