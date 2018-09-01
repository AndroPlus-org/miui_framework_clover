// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.telecom:
//            PhoneAccountHandle

public class Voicemail
    implements Parcelable
{
    public static class Builder
    {

        public Voicemail build()
        {
            long l = 0L;
            long l1;
            boolean flag;
            if(mBuilderId == null)
                l1 = -1L;
            else
                l1 = mBuilderId.longValue();
            mBuilderId = Long.valueOf(l1);
            if(mBuilderTimestamp == null)
                l1 = 0L;
            else
                l1 = mBuilderTimestamp.longValue();
            mBuilderTimestamp = Long.valueOf(l1);
            if(mBuilderDuration == null)
                l1 = l;
            else
                l1 = mBuilderDuration.longValue();
            mBuilderDuration = Long.valueOf(l1);
            if(mBuilderIsRead == null)
                flag = false;
            else
                flag = mBuilderIsRead.booleanValue();
            mBuilderIsRead = Boolean.valueOf(flag);
            return new Voicemail(mBuilderTimestamp, mBuilderNumber, mBuilderPhoneAccount, mBuilderId, mBuilderDuration, mBuilderSourcePackage, mBuilderSourceData, mBuilderUri, mBuilderIsRead, Boolean.valueOf(mBuilderHasContent), mBuilderTranscription, null);
        }

        public Builder setDuration(long l)
        {
            mBuilderDuration = Long.valueOf(l);
            return this;
        }

        public Builder setHasContent(boolean flag)
        {
            mBuilderHasContent = flag;
            return this;
        }

        public Builder setId(long l)
        {
            mBuilderId = Long.valueOf(l);
            return this;
        }

        public Builder setIsRead(boolean flag)
        {
            mBuilderIsRead = Boolean.valueOf(flag);
            return this;
        }

        public Builder setNumber(String s)
        {
            mBuilderNumber = s;
            return this;
        }

        public Builder setPhoneAccount(PhoneAccountHandle phoneaccounthandle)
        {
            mBuilderPhoneAccount = phoneaccounthandle;
            return this;
        }

        public Builder setSourceData(String s)
        {
            mBuilderSourceData = s;
            return this;
        }

        public Builder setSourcePackage(String s)
        {
            mBuilderSourcePackage = s;
            return this;
        }

        public Builder setTimestamp(long l)
        {
            mBuilderTimestamp = Long.valueOf(l);
            return this;
        }

        public Builder setTranscription(String s)
        {
            mBuilderTranscription = s;
            return this;
        }

        public Builder setUri(Uri uri)
        {
            mBuilderUri = uri;
            return this;
        }

        private Long mBuilderDuration;
        private boolean mBuilderHasContent;
        private Long mBuilderId;
        private Boolean mBuilderIsRead;
        private String mBuilderNumber;
        private PhoneAccountHandle mBuilderPhoneAccount;
        private String mBuilderSourceData;
        private String mBuilderSourcePackage;
        private Long mBuilderTimestamp;
        private String mBuilderTranscription;
        private Uri mBuilderUri;

        private Builder()
        {
        }

        Builder(Builder builder)
        {
            this();
        }
    }


    private Voicemail(Parcel parcel)
    {
        boolean flag = true;
        super();
        mTimestamp = Long.valueOf(parcel.readLong());
        mNumber = (String)parcel.readCharSequence();
        boolean flag1;
        if(parcel.readInt() > 0)
            mPhoneAccount = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
        else
            mPhoneAccount = null;
        mId = Long.valueOf(parcel.readLong());
        mDuration = Long.valueOf(parcel.readLong());
        mSource = (String)parcel.readCharSequence();
        mProviderData = (String)parcel.readCharSequence();
        if(parcel.readInt() > 0)
            mUri = (Uri)Uri.CREATOR.createFromParcel(parcel);
        else
            mUri = null;
        if(parcel.readInt() > 0)
            flag1 = true;
        else
            flag1 = false;
        mIsRead = Boolean.valueOf(flag1);
        if(parcel.readInt() > 0)
            flag1 = flag;
        else
            flag1 = false;
        mHasContent = Boolean.valueOf(flag1);
        mTranscription = (String)parcel.readCharSequence();
    }

    Voicemail(Parcel parcel, Voicemail voicemail)
    {
        this(parcel);
    }

    private Voicemail(Long long1, String s, PhoneAccountHandle phoneaccounthandle, Long long2, Long long3, String s1, String s2, 
            Uri uri, Boolean boolean1, Boolean boolean2, String s3)
    {
        mTimestamp = long1;
        mNumber = s;
        mPhoneAccount = phoneaccounthandle;
        mId = long2;
        mDuration = long3;
        mSource = s1;
        mProviderData = s2;
        mUri = uri;
        mIsRead = boolean1;
        mHasContent = boolean2;
        mTranscription = s3;
    }

    Voicemail(Long long1, String s, PhoneAccountHandle phoneaccounthandle, Long long2, Long long3, String s1, String s2, 
            Uri uri, Boolean boolean1, Boolean boolean2, String s3, Voicemail voicemail)
    {
        this(long1, s, phoneaccounthandle, long2, long3, s1, s2, uri, boolean1, boolean2, s3);
    }

    public static Builder createForInsertion(long l, String s)
    {
        return (new Builder(null)).setNumber(s).setTimestamp(l);
    }

    public static Builder createForUpdate(long l, String s)
    {
        return (new Builder(null)).setId(l).setSourceData(s);
    }

    public int describeContents()
    {
        return 0;
    }

    public long getDuration()
    {
        return mDuration.longValue();
    }

    public long getId()
    {
        return mId.longValue();
    }

    public String getNumber()
    {
        return mNumber;
    }

    public PhoneAccountHandle getPhoneAccount()
    {
        return mPhoneAccount;
    }

    public String getSourceData()
    {
        return mProviderData;
    }

    public String getSourcePackage()
    {
        return mSource;
    }

    public long getTimestampMillis()
    {
        return mTimestamp.longValue();
    }

    public String getTranscription()
    {
        return mTranscription;
    }

    public Uri getUri()
    {
        return mUri;
    }

    public boolean hasContent()
    {
        return mHasContent.booleanValue();
    }

    public boolean isRead()
    {
        return mIsRead.booleanValue();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mTimestamp.longValue());
        parcel.writeCharSequence(mNumber);
        if(mPhoneAccount == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            mPhoneAccount.writeToParcel(parcel, i);
        }
        parcel.writeLong(mId.longValue());
        parcel.writeLong(mDuration.longValue());
        parcel.writeCharSequence(mSource);
        parcel.writeCharSequence(mProviderData);
        if(mUri == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            mUri.writeToParcel(parcel, i);
        }
        if(mIsRead.booleanValue())
            parcel.writeInt(1);
        else
            parcel.writeInt(0);
        if(mHasContent.booleanValue())
            parcel.writeInt(1);
        else
            parcel.writeInt(0);
        parcel.writeCharSequence(mTranscription);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public Voicemail createFromParcel(Parcel parcel)
        {
            return new Voicemail(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public Voicemail[] newArray(int i)
        {
            return new Voicemail[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final Long mDuration;
    private final Boolean mHasContent;
    private final Long mId;
    private final Boolean mIsRead;
    private final String mNumber;
    private final PhoneAccountHandle mPhoneAccount;
    private final String mProviderData;
    private final String mSource;
    private final Long mTimestamp;
    private final String mTranscription;
    private final Uri mUri;

}
