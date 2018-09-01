// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;
import java.util.*;

// Referenced classes of package android.telecom:
//            PhoneAccountHandle, Log

public final class PhoneAccount
    implements Parcelable
{
    public static class Builder
    {

        public Builder addSupportedUriScheme(String s)
        {
            if(!TextUtils.isEmpty(s) && mSupportedUriSchemes.contains(s) ^ true)
                mSupportedUriSchemes.add(s);
            return this;
        }

        public PhoneAccount build()
        {
            if(mSupportedUriSchemes.isEmpty())
                addSupportedUriScheme("tel");
            return new PhoneAccount(mAccountHandle, mAddress, mSubscriptionAddress, mCapabilities, mIcon, mHighlightColor, mLabel, mShortDescription, mSupportedUriSchemes, mExtras, mSupportedAudioRoutes, mIsEnabled, mGroupId, null);
        }

        public Builder setAddress(Uri uri)
        {
            mAddress = uri;
            return this;
        }

        public Builder setCapabilities(int i)
        {
            mCapabilities = i;
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        public Builder setGroupId(String s)
        {
            if(s != null)
                mGroupId = s;
            else
                mGroupId = "";
            return this;
        }

        public Builder setHighlightColor(int i)
        {
            mHighlightColor = i;
            return this;
        }

        public Builder setIcon(Icon icon)
        {
            mIcon = icon;
            return this;
        }

        public Builder setIsEnabled(boolean flag)
        {
            mIsEnabled = flag;
            return this;
        }

        public Builder setLabel(CharSequence charsequence)
        {
            mLabel = charsequence;
            return this;
        }

        public Builder setShortDescription(CharSequence charsequence)
        {
            mShortDescription = charsequence;
            return this;
        }

        public Builder setSubscriptionAddress(Uri uri)
        {
            mSubscriptionAddress = uri;
            return this;
        }

        public Builder setSupportedAudioRoutes(int i)
        {
            mSupportedAudioRoutes = i;
            return this;
        }

        public Builder setSupportedUriSchemes(List list)
        {
            mSupportedUriSchemes.clear();
            if(list != null && list.isEmpty() ^ true)
                for(list = list.iterator(); list.hasNext(); addSupportedUriScheme((String)list.next()));
            return this;
        }

        private PhoneAccountHandle mAccountHandle;
        private Uri mAddress;
        private int mCapabilities;
        private Bundle mExtras;
        private String mGroupId;
        private int mHighlightColor;
        private Icon mIcon;
        private boolean mIsEnabled;
        private CharSequence mLabel;
        private CharSequence mShortDescription;
        private Uri mSubscriptionAddress;
        private int mSupportedAudioRoutes;
        private List mSupportedUriSchemes;

        public Builder(PhoneAccount phoneaccount)
        {
            mSupportedAudioRoutes = 15;
            mHighlightColor = 0;
            mSupportedUriSchemes = new ArrayList();
            mIsEnabled = false;
            mGroupId = "";
            mAccountHandle = phoneaccount.getAccountHandle();
            mAddress = phoneaccount.getAddress();
            mSubscriptionAddress = phoneaccount.getSubscriptionAddress();
            mCapabilities = phoneaccount.getCapabilities();
            mHighlightColor = phoneaccount.getHighlightColor();
            mLabel = phoneaccount.getLabel();
            mShortDescription = phoneaccount.getShortDescription();
            mSupportedUriSchemes.addAll(phoneaccount.getSupportedUriSchemes());
            mIcon = phoneaccount.getIcon();
            mIsEnabled = phoneaccount.isEnabled();
            mExtras = phoneaccount.getExtras();
            mGroupId = phoneaccount.getGroupId();
            mSupportedAudioRoutes = phoneaccount.getSupportedAudioRoutes();
        }

        public Builder(PhoneAccountHandle phoneaccounthandle, CharSequence charsequence)
        {
            mSupportedAudioRoutes = 15;
            mHighlightColor = 0;
            mSupportedUriSchemes = new ArrayList();
            mIsEnabled = false;
            mGroupId = "";
            mAccountHandle = phoneaccounthandle;
            mLabel = charsequence;
        }
    }


    private PhoneAccount(Parcel parcel)
    {
        boolean flag;
        if(parcel.readInt() > 0)
            mAccountHandle = (PhoneAccountHandle)PhoneAccountHandle.CREATOR.createFromParcel(parcel);
        else
            mAccountHandle = null;
        if(parcel.readInt() > 0)
            mAddress = (Uri)Uri.CREATOR.createFromParcel(parcel);
        else
            mAddress = null;
        if(parcel.readInt() > 0)
            mSubscriptionAddress = (Uri)Uri.CREATOR.createFromParcel(parcel);
        else
            mSubscriptionAddress = null;
        mCapabilities = parcel.readInt();
        mHighlightColor = parcel.readInt();
        mLabel = parcel.readCharSequence();
        mShortDescription = parcel.readCharSequence();
        mSupportedUriSchemes = Collections.unmodifiableList(parcel.createStringArrayList());
        if(parcel.readInt() > 0)
            mIcon = (Icon)Icon.CREATOR.createFromParcel(parcel);
        else
            mIcon = null;
        if(parcel.readByte() == 1)
            flag = true;
        else
            flag = false;
        mIsEnabled = flag;
        mExtras = parcel.readBundle();
        mGroupId = parcel.readString();
        mSupportedAudioRoutes = parcel.readInt();
    }

    PhoneAccount(Parcel parcel, PhoneAccount phoneaccount)
    {
        this(parcel);
    }

    private PhoneAccount(PhoneAccountHandle phoneaccounthandle, Uri uri, Uri uri1, int i, Icon icon, int j, CharSequence charsequence, 
            CharSequence charsequence1, List list, Bundle bundle, int k, boolean flag, String s)
    {
        mAccountHandle = phoneaccounthandle;
        mAddress = uri;
        mSubscriptionAddress = uri1;
        mCapabilities = i;
        mIcon = icon;
        mHighlightColor = j;
        mLabel = charsequence;
        mShortDescription = charsequence1;
        mSupportedUriSchemes = Collections.unmodifiableList(list);
        mExtras = bundle;
        mSupportedAudioRoutes = k;
        mIsEnabled = flag;
        mGroupId = s;
    }

    PhoneAccount(PhoneAccountHandle phoneaccounthandle, Uri uri, Uri uri1, int i, Icon icon, int j, CharSequence charsequence, 
            CharSequence charsequence1, List list, Bundle bundle, int k, boolean flag, String s, PhoneAccount phoneaccount)
    {
        this(phoneaccounthandle, uri, uri1, i, icon, j, charsequence, charsequence1, list, bundle, k, flag, s);
    }

    private String audioRoutesToString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(hasAudioRoutes(2))
            stringbuilder.append("B");
        if(hasAudioRoutes(1))
            stringbuilder.append("E");
        if(hasAudioRoutes(8))
            stringbuilder.append("S");
        if(hasAudioRoutes(4))
            stringbuilder.append("W");
        return stringbuilder.toString();
    }

    public static Builder builder(PhoneAccountHandle phoneaccounthandle, CharSequence charsequence)
    {
        return new Builder(phoneaccounthandle, charsequence);
    }

    private String capabilitiesToString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(hasCapabilities(2048))
            stringbuilder.append("SelfManaged ");
        if(hasCapabilities(1024))
            stringbuilder.append("SuppVideo ");
        if(hasCapabilities(8))
            stringbuilder.append("Video ");
        if(hasCapabilities(256))
            stringbuilder.append("Presence ");
        if(hasCapabilities(2))
            stringbuilder.append("CallProvider ");
        if(hasCapabilities(64))
            stringbuilder.append("CallSubject ");
        if(hasCapabilities(1))
            stringbuilder.append("ConnectionMgr ");
        if(hasCapabilities(128))
            stringbuilder.append("EmergOnly ");
        if(hasCapabilities(32))
            stringbuilder.append("MultiUser ");
        if(hasCapabilities(16))
            stringbuilder.append("PlaceEmerg ");
        if(hasCapabilities(512))
            stringbuilder.append("EmergVideo ");
        if(hasCapabilities(4))
            stringbuilder.append("SimSub ");
        return stringbuilder.toString();
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

    public int getCapabilities()
    {
        return mCapabilities;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public String getGroupId()
    {
        return mGroupId;
    }

    public int getHighlightColor()
    {
        return mHighlightColor;
    }

    public Icon getIcon()
    {
        return mIcon;
    }

    public CharSequence getLabel()
    {
        return mLabel;
    }

    public CharSequence getShortDescription()
    {
        return mShortDescription;
    }

    public Uri getSubscriptionAddress()
    {
        return mSubscriptionAddress;
    }

    public int getSupportedAudioRoutes()
    {
        return mSupportedAudioRoutes;
    }

    public List getSupportedUriSchemes()
    {
        return mSupportedUriSchemes;
    }

    public boolean hasAudioRoutes(int i)
    {
        boolean flag;
        if((mSupportedAudioRoutes & i) == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasCapabilities(int i)
    {
        boolean flag;
        if((mCapabilities & i) == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isEnabled()
    {
        return mIsEnabled;
    }

    public boolean isSelfManaged()
    {
        boolean flag;
        if((mCapabilities & 0x800) == 2048)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setIsEnabled(boolean flag)
    {
        mIsEnabled = flag;
    }

    public boolean supportsUriScheme(String s)
    {
        if(mSupportedUriSchemes == null || s == null)
            return false;
        for(Iterator iterator = mSupportedUriSchemes.iterator(); iterator.hasNext();)
        {
            String s1 = (String)iterator.next();
            if(s1 != null && s1.equals(s))
                return true;
        }

        return false;
    }

    public Builder toBuilder()
    {
        return new Builder(this);
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("[[");
        char c;
        if(mIsEnabled)
        {
            byte byte0 = 88;
            c = byte0;
        } else
        {
            byte byte1 = 32;
            c = byte1;
        }
        stringbuilder = stringbuilder.append(c).append("] PhoneAccount: ").append(mAccountHandle).append(" Capabilities: ").append(capabilitiesToString()).append(" Audio Routes: ").append(audioRoutesToString()).append(" Schemes: ");
        for(Iterator iterator = mSupportedUriSchemes.iterator(); iterator.hasNext(); stringbuilder.append((String)iterator.next()).append(" "));
        stringbuilder.append(" Extras: ");
        stringbuilder.append(mExtras);
        stringbuilder.append(" GroupId: ");
        stringbuilder.append(Log.pii(mGroupId));
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        if(mAccountHandle == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            mAccountHandle.writeToParcel(parcel, i);
        }
        if(mAddress == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            mAddress.writeToParcel(parcel, i);
        }
        if(mSubscriptionAddress == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            mSubscriptionAddress.writeToParcel(parcel, i);
        }
        parcel.writeInt(mCapabilities);
        parcel.writeInt(mHighlightColor);
        parcel.writeCharSequence(mLabel);
        parcel.writeCharSequence(mShortDescription);
        parcel.writeStringList(mSupportedUriSchemes);
        if(mIcon == null)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(1);
            mIcon.writeToParcel(parcel, i);
        }
        if(mIsEnabled)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeByte((byte)i);
        parcel.writeBundle(mExtras);
        parcel.writeString(mGroupId);
        parcel.writeInt(mSupportedAudioRoutes);
    }

    public static final int CAPABILITY_CALL_PROVIDER = 2;
    public static final int CAPABILITY_CALL_SUBJECT = 64;
    public static final int CAPABILITY_CONNECTION_MANAGER = 1;
    public static final int CAPABILITY_EMERGENCY_CALLS_ONLY = 128;
    public static final int CAPABILITY_EMERGENCY_VIDEO_CALLING = 512;
    public static final int CAPABILITY_MULTI_USER = 32;
    public static final int CAPABILITY_PLACE_EMERGENCY_CALLS = 16;
    public static final int CAPABILITY_RTT = 4096;
    public static final int CAPABILITY_SELF_MANAGED = 2048;
    public static final int CAPABILITY_SIM_SUBSCRIPTION = 4;
    public static final int CAPABILITY_SUPPORTS_VIDEO_CALLING = 1024;
    public static final int CAPABILITY_VIDEO_CALLING = 8;
    public static final int CAPABILITY_VIDEO_CALLING_RELIES_ON_PRESENCE = 256;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PhoneAccount createFromParcel(Parcel parcel)
        {
            return new PhoneAccount(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PhoneAccount[] newArray(int i)
        {
            return new PhoneAccount[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String EXTRA_ALWAYS_USE_VOIP_AUDIO_MODE = "codeaurora.org.extra.ALWAYS_USE_VOIP_AUDIO_MODE";
    public static final String EXTRA_CALL_SUBJECT_CHARACTER_ENCODING = "android.telecom.extra.CALL_SUBJECT_CHARACTER_ENCODING";
    public static final String EXTRA_CALL_SUBJECT_MAX_LENGTH = "android.telecom.extra.CALL_SUBJECT_MAX_LENGTH";
    public static final String EXTRA_LOG_SELF_MANAGED_CALLS = "android.telecom.extra.LOG_SELF_MANAGED_CALLS";
    public static final String EXTRA_SORT_ORDER = "android.telecom.extra.SORT_ORDER";
    public static final String EXTRA_SUPPORTS_HANDOVER_FROM = "android.telecom.extra.SUPPORTS_HANDOVER_FROM";
    public static final String EXTRA_SUPPORTS_HANDOVER_TO = "android.telecom.extra.SUPPORTS_HANDOVER_TO";
    public static final String EXTRA_SUPPORTS_VIDEO_CALLING_FALLBACK = "android.telecom.extra.SUPPORTS_VIDEO_CALLING_FALLBACK";
    public static final int NO_HIGHLIGHT_COLOR = 0;
    public static final int NO_ICON_TINT = 0;
    public static final int NO_RESOURCE_ID = -1;
    public static final String SCHEME_SIP = "sip";
    public static final String SCHEME_TEL = "tel";
    public static final String SCHEME_VOICEMAIL = "voicemail";
    private final PhoneAccountHandle mAccountHandle;
    private final Uri mAddress;
    private final int mCapabilities;
    private final Bundle mExtras;
    private String mGroupId;
    private final int mHighlightColor;
    private final Icon mIcon;
    private boolean mIsEnabled;
    private final CharSequence mLabel;
    private final CharSequence mShortDescription;
    private final Uri mSubscriptionAddress;
    private final int mSupportedAudioRoutes;
    private final List mSupportedUriSchemes;

}
