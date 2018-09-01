// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.*;
import com.android.internal.telecom.IVideoProvider;
import java.util.*;

// Referenced classes of package android.telecom:
//            VideoCallImpl, PhoneAccountHandle, DisconnectCause, GatewayInfo, 
//            ParcelableRttCall, StatusHints

public final class ParcelableCall
    implements Parcelable
{

    public ParcelableCall(String s, int i, DisconnectCause disconnectcause, List list, int j, int k, int l, 
            long l1, Uri uri, int i1, String s1, int j1, GatewayInfo gatewayinfo, 
            PhoneAccountHandle phoneaccounthandle, boolean flag, IVideoProvider ivideoprovider, boolean flag1, ParcelableRttCall parcelablerttcall, String s2, List list1, 
            StatusHints statushints, int k1, List list2, Bundle bundle, Bundle bundle1, long l2)
    {
        mId = s;
        mState = i;
        mDisconnectCause = disconnectcause;
        mCannedSmsResponses = list;
        mCapabilities = j;
        mProperties = k;
        mSupportedAudioRoutes = l;
        mConnectTimeMillis = l1;
        mHandle = uri;
        mHandlePresentation = i1;
        mCallerDisplayName = s1;
        mCallerDisplayNamePresentation = j1;
        mGatewayInfo = gatewayinfo;
        mAccountHandle = phoneaccounthandle;
        mIsVideoCallProviderChanged = flag;
        mVideoCallProvider = ivideoprovider;
        mIsRttCallChanged = flag1;
        mRttCall = parcelablerttcall;
        mParentCallId = s2;
        mChildCallIds = list1;
        mStatusHints = statushints;
        mVideoState = k1;
        mConferenceableCallIds = Collections.unmodifiableList(list2);
        mIntentExtras = bundle;
        mExtras = bundle1;
        mCreationTimeMillis = l2;
    }

    public int describeContents()
    {
        return 0;
    }

    public PhoneAccountHandle getAccountHandle()
    {
        return mAccountHandle;
    }

    public String getCallerDisplayName()
    {
        return mCallerDisplayName;
    }

    public int getCallerDisplayNamePresentation()
    {
        return mCallerDisplayNamePresentation;
    }

    public List getCannedSmsResponses()
    {
        return mCannedSmsResponses;
    }

    public int getCapabilities()
    {
        return mCapabilities;
    }

    public List getChildCallIds()
    {
        return mChildCallIds;
    }

    public List getConferenceableCallIds()
    {
        return mConferenceableCallIds;
    }

    public long getConnectTimeMillis()
    {
        return mConnectTimeMillis;
    }

    public long getCreationTimeMillis()
    {
        return mCreationTimeMillis;
    }

    public DisconnectCause getDisconnectCause()
    {
        return mDisconnectCause;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public GatewayInfo getGatewayInfo()
    {
        return mGatewayInfo;
    }

    public Uri getHandle()
    {
        return mHandle;
    }

    public int getHandlePresentation()
    {
        return mHandlePresentation;
    }

    public String getId()
    {
        return mId;
    }

    public Bundle getIntentExtras()
    {
        return mIntentExtras;
    }

    public boolean getIsRttCallChanged()
    {
        return mIsRttCallChanged;
    }

    public ParcelableRttCall getParcelableRttCall()
    {
        return mRttCall;
    }

    public String getParentCallId()
    {
        return mParentCallId;
    }

    public int getProperties()
    {
        return mProperties;
    }

    public int getState()
    {
        return mState;
    }

    public StatusHints getStatusHints()
    {
        return mStatusHints;
    }

    public int getSupportedAudioRoutes()
    {
        return mSupportedAudioRoutes;
    }

    public VideoCallImpl getVideoCallImpl(String s, int i)
    {
        if(mVideoCall == null && mVideoCallProvider != null)
            try
            {
                VideoCallImpl videocallimpl = JVM INSTR new #168 <Class VideoCallImpl>;
                videocallimpl.VideoCallImpl(mVideoCallProvider, s, i);
                mVideoCall = videocallimpl;
            }
            // Misplaced declaration of an exception variable
            catch(String s) { }
        return mVideoCall;
    }

    public int getVideoState()
    {
        return mVideoState;
    }

    public boolean isVideoCallProviderChanged()
    {
        return mIsVideoCallProviderChanged;
    }

    public String toString()
    {
        return String.format("[%s, parent:%s, children:%s]", new Object[] {
            mId, mParentCallId, mChildCallIds
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(mId);
        parcel.writeInt(mState);
        parcel.writeParcelable(mDisconnectCause, 0);
        parcel.writeList(mCannedSmsResponses);
        parcel.writeInt(mCapabilities);
        parcel.writeInt(mProperties);
        parcel.writeLong(mConnectTimeMillis);
        parcel.writeParcelable(mHandle, 0);
        parcel.writeInt(mHandlePresentation);
        parcel.writeString(mCallerDisplayName);
        parcel.writeInt(mCallerDisplayNamePresentation);
        parcel.writeParcelable(mGatewayInfo, 0);
        parcel.writeParcelable(mAccountHandle, 0);
        android.os.IBinder ibinder;
        if(mIsVideoCallProviderChanged)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        if(mVideoCallProvider != null)
            ibinder = mVideoCallProvider.asBinder();
        else
            ibinder = null;
        parcel.writeStrongBinder(ibinder);
        parcel.writeString(mParentCallId);
        parcel.writeList(mChildCallIds);
        parcel.writeParcelable(mStatusHints, 0);
        parcel.writeInt(mVideoState);
        parcel.writeList(mConferenceableCallIds);
        parcel.writeBundle(mIntentExtras);
        parcel.writeBundle(mExtras);
        parcel.writeInt(mSupportedAudioRoutes);
        if(mIsRttCallChanged)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeByte((byte)i);
        parcel.writeParcelable(mRttCall, 0);
        parcel.writeLong(mCreationTimeMillis);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ParcelableCall createFromParcel(Parcel parcel)
        {
            ClassLoader classloader = android/telecom/ParcelableCall.getClassLoader();
            String s = parcel.readString();
            int i = parcel.readInt();
            DisconnectCause disconnectcause = (DisconnectCause)parcel.readParcelable(classloader);
            ArrayList arraylist = new ArrayList();
            parcel.readList(arraylist, classloader);
            int j = parcel.readInt();
            int k = parcel.readInt();
            long l = parcel.readLong();
            Uri uri = (Uri)parcel.readParcelable(classloader);
            int i1 = parcel.readInt();
            String s1 = parcel.readString();
            int j1 = parcel.readInt();
            GatewayInfo gatewayinfo = (GatewayInfo)parcel.readParcelable(classloader);
            PhoneAccountHandle phoneaccounthandle = (PhoneAccountHandle)parcel.readParcelable(classloader);
            boolean flag;
            IVideoProvider ivideoprovider;
            String s2;
            ArrayList arraylist1;
            StatusHints statushints;
            int k1;
            ArrayList arraylist2;
            Bundle bundle;
            Bundle bundle1;
            int l1;
            boolean flag1;
            if(parcel.readByte() == 1)
                flag = true;
            else
                flag = false;
            ivideoprovider = com.android.internal.telecom.IVideoProvider.Stub.asInterface(parcel.readStrongBinder());
            s2 = parcel.readString();
            arraylist1 = new ArrayList();
            parcel.readList(arraylist1, classloader);
            statushints = (StatusHints)parcel.readParcelable(classloader);
            k1 = parcel.readInt();
            arraylist2 = new ArrayList();
            parcel.readList(arraylist2, classloader);
            bundle = parcel.readBundle(classloader);
            bundle1 = parcel.readBundle(classloader);
            l1 = parcel.readInt();
            if(parcel.readByte() == 1)
                flag1 = true;
            else
                flag1 = false;
            return new ParcelableCall(s, i, disconnectcause, arraylist, j, k, l1, l, uri, i1, s1, j1, gatewayinfo, phoneaccounthandle, flag, ivideoprovider, flag1, (ParcelableRttCall)parcel.readParcelable(classloader), s2, arraylist1, statushints, k1, arraylist2, bundle, bundle1, parcel.readLong());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ParcelableCall[] newArray(int i)
        {
            return new ParcelableCall[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final PhoneAccountHandle mAccountHandle;
    private final String mCallerDisplayName;
    private final int mCallerDisplayNamePresentation;
    private final List mCannedSmsResponses;
    private final int mCapabilities;
    private final List mChildCallIds;
    private final List mConferenceableCallIds;
    private final long mConnectTimeMillis;
    private final long mCreationTimeMillis;
    private final DisconnectCause mDisconnectCause;
    private final Bundle mExtras;
    private final GatewayInfo mGatewayInfo;
    private final Uri mHandle;
    private final int mHandlePresentation;
    private final String mId;
    private final Bundle mIntentExtras;
    private final boolean mIsRttCallChanged;
    private final boolean mIsVideoCallProviderChanged;
    private final String mParentCallId;
    private final int mProperties;
    private final ParcelableRttCall mRttCall;
    private final int mState;
    private final StatusHints mStatusHints;
    private final int mSupportedAudioRoutes;
    private VideoCallImpl mVideoCall;
    private final IVideoProvider mVideoCallProvider;
    private final int mVideoState;

}
