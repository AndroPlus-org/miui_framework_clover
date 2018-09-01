// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.*;

// Referenced classes of package android.telecom:
//            PhoneAccountHandle, Connection

public final class ConnectionRequest
    implements Parcelable
{
    public static final class Builder
    {

        public ConnectionRequest build()
        {
            return new ConnectionRequest(mAccountHandle, mAddress, mExtras, mVideoState, mTelecomCallId, mShouldShowIncomingCallUi, mRttPipeFromInCall, mRttPipeToInCall, null);
        }

        public Builder setAccountHandle(PhoneAccountHandle phoneaccounthandle)
        {
            mAccountHandle = phoneaccounthandle;
            return this;
        }

        public Builder setAddress(Uri uri)
        {
            mAddress = uri;
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        public Builder setRttPipeFromInCall(ParcelFileDescriptor parcelfiledescriptor)
        {
            mRttPipeFromInCall = parcelfiledescriptor;
            return this;
        }

        public Builder setRttPipeToInCall(ParcelFileDescriptor parcelfiledescriptor)
        {
            mRttPipeToInCall = parcelfiledescriptor;
            return this;
        }

        public Builder setShouldShowIncomingCallUi(boolean flag)
        {
            mShouldShowIncomingCallUi = flag;
            return this;
        }

        public Builder setTelecomCallId(String s)
        {
            mTelecomCallId = s;
            return this;
        }

        public Builder setVideoState(int i)
        {
            mVideoState = i;
            return this;
        }

        private PhoneAccountHandle mAccountHandle;
        private Uri mAddress;
        private Bundle mExtras;
        private ParcelFileDescriptor mRttPipeFromInCall;
        private ParcelFileDescriptor mRttPipeToInCall;
        private boolean mShouldShowIncomingCallUi;
        private String mTelecomCallId;
        private int mVideoState;

        public Builder()
        {
            mVideoState = 0;
            mShouldShowIncomingCallUi = false;
        }
    }


    private ConnectionRequest(Parcel parcel)
    {
        mAccountHandle = (PhoneAccountHandle)parcel.readParcelable(getClass().getClassLoader());
        mAddress = (Uri)parcel.readParcelable(getClass().getClassLoader());
        mExtras = (Bundle)parcel.readParcelable(getClass().getClassLoader());
        mVideoState = parcel.readInt();
        mTelecomCallId = parcel.readString();
        boolean flag;
        if(parcel.readInt() == 1)
            flag = true;
        else
            flag = false;
        mShouldShowIncomingCallUi = flag;
        mRttPipeFromInCall = (ParcelFileDescriptor)parcel.readParcelable(getClass().getClassLoader());
        mRttPipeToInCall = (ParcelFileDescriptor)parcel.readParcelable(getClass().getClassLoader());
    }

    ConnectionRequest(Parcel parcel, ConnectionRequest connectionrequest)
    {
        this(parcel);
    }

    public ConnectionRequest(PhoneAccountHandle phoneaccounthandle, Uri uri, Bundle bundle)
    {
        this(phoneaccounthandle, uri, bundle, 0, null, false, null, null);
    }

    public ConnectionRequest(PhoneAccountHandle phoneaccounthandle, Uri uri, Bundle bundle, int i)
    {
        this(phoneaccounthandle, uri, bundle, i, null, false, null, null);
    }

    public ConnectionRequest(PhoneAccountHandle phoneaccounthandle, Uri uri, Bundle bundle, int i, String s, boolean flag)
    {
        this(phoneaccounthandle, uri, bundle, i, s, flag, null, null);
    }

    private ConnectionRequest(PhoneAccountHandle phoneaccounthandle, Uri uri, Bundle bundle, int i, String s, boolean flag, ParcelFileDescriptor parcelfiledescriptor, 
            ParcelFileDescriptor parcelfiledescriptor1)
    {
        mAccountHandle = phoneaccounthandle;
        mAddress = uri;
        mExtras = bundle;
        mVideoState = i;
        mTelecomCallId = s;
        mShouldShowIncomingCallUi = flag;
        mRttPipeFromInCall = parcelfiledescriptor;
        mRttPipeToInCall = parcelfiledescriptor1;
    }

    ConnectionRequest(PhoneAccountHandle phoneaccounthandle, Uri uri, Bundle bundle, int i, String s, boolean flag, ParcelFileDescriptor parcelfiledescriptor, 
            ParcelFileDescriptor parcelfiledescriptor1, ConnectionRequest connectionrequest)
    {
        this(phoneaccounthandle, uri, bundle, i, s, flag, parcelfiledescriptor, parcelfiledescriptor1);
    }

    public int describeContents()
    {
        return 0;
    }

    public PhoneAccountHandle getAccountHandle()
    {
        return mAccountHandle;
    }

    public Uri getAddress()
    {
        return mAddress;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public ParcelFileDescriptor getRttPipeFromInCall()
    {
        return mRttPipeFromInCall;
    }

    public ParcelFileDescriptor getRttPipeToInCall()
    {
        return mRttPipeToInCall;
    }

    public Connection.RttTextStream getRttTextStream()
    {
        if(isRequestingRtt())
            return new Connection.RttTextStream(mRttPipeToInCall, mRttPipeFromInCall);
        else
            return null;
    }

    public String getTelecomCallId()
    {
        return mTelecomCallId;
    }

    public int getVideoState()
    {
        return mVideoState;
    }

    public boolean isRequestingRtt()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mRttPipeFromInCall != null)
        {
            flag1 = flag;
            if(mRttPipeToInCall != null)
                flag1 = true;
        }
        return flag1;
    }

    public void setAccountHandle(PhoneAccountHandle phoneaccounthandle)
    {
        mAccountHandle = phoneaccounthandle;
    }

    public boolean shouldShowIncomingCallUi()
    {
        return mShouldShowIncomingCallUi;
    }

    public String toString()
    {
        Object obj;
        Object obj1;
        if(mAddress == null)
            obj = Uri.EMPTY;
        else
            obj = Connection.toLogSafePhoneNumber(mAddress.toString());
        if(mExtras == null)
            obj1 = "";
        else
            obj1 = mExtras;
        return String.format("ConnectionRequest %s %s", new Object[] {
            obj, obj1
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeParcelable(mAccountHandle, 0);
        parcel.writeParcelable(mAddress, 0);
        parcel.writeParcelable(mExtras, 0);
        parcel.writeInt(mVideoState);
        parcel.writeString(mTelecomCallId);
        if(mShouldShowIncomingCallUi)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeParcelable(mRttPipeFromInCall, 0);
        parcel.writeParcelable(mRttPipeToInCall, 0);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ConnectionRequest createFromParcel(Parcel parcel)
        {
            return new ConnectionRequest(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ConnectionRequest[] newArray(int i)
        {
            return new ConnectionRequest[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private PhoneAccountHandle mAccountHandle;
    private final Uri mAddress;
    private final Bundle mExtras;
    private final ParcelFileDescriptor mRttPipeFromInCall;
    private final ParcelFileDescriptor mRttPipeToInCall;
    private final boolean mShouldShowIncomingCallUi;
    private final String mTelecomCallId;
    private final int mVideoState;

}
