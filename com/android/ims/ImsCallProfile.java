// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;

import android.os.*;
import android.telecom.VideoProfile;

// Referenced classes of package com.android.ims:
//            ImsStreamMediaProfile

public class ImsCallProfile
    implements Parcelable
{

    public ImsCallProfile()
    {
        mRestrictCause = 0;
        mServiceType = 1;
        mCallType = 1;
        mCallExtras = new Bundle();
        mMediaProfile = new ImsStreamMediaProfile();
    }

    public ImsCallProfile(int i, int j)
    {
        mRestrictCause = 0;
        mServiceType = i;
        mCallType = j;
        mCallExtras = new Bundle();
        mMediaProfile = new ImsStreamMediaProfile();
    }

    public ImsCallProfile(Parcel parcel)
    {
        mRestrictCause = 0;
        readFromParcel(parcel);
    }

    public static int OIRToPresentation(int i)
    {
        switch(i)
        {
        default:
            return 3;

        case 1: // '\001'
            return 2;

        case 2: // '\002'
            return 1;

        case 4: // '\004'
            return 4;

        case 3: // '\003'
            return 3;
        }
    }

    public static int getCallTypeFromVideoState(int i)
    {
        boolean flag = isVideoStateSet(i, 1);
        boolean flag1 = isVideoStateSet(i, 2);
        if(isVideoStateSet(i, 4))
            return 7;
        if(flag && flag1 ^ true)
            return 5;
        if(!flag && flag1)
            return 6;
        return !flag || !flag1 ? 2 : 4;
    }

    public static int getVideoStateFromCallType(int i)
    {
        i;
        JVM INSTR tableswitch 2 6: default 36
    //                   2 55
    //                   3 36
    //                   4 50
    //                   5 40
    //                   6 45;
           goto _L1 _L2 _L1 _L3 _L4 _L5
_L1:
        i = 0;
_L7:
        return i;
_L4:
        i = 1;
        continue; /* Loop/switch isn't completed */
_L5:
        i = 2;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 3;
        continue; /* Loop/switch isn't completed */
_L2:
        i = 0;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public static int getVideoStateFromImsCallProfile(ImsCallProfile imscallprofile)
    {
        int i = getVideoStateFromCallType(imscallprofile.mCallType);
        if(imscallprofile.isVideoPaused() && VideoProfile.isAudioOnly(i) ^ true)
            i |= 4;
        else
            i &= -5;
        return i;
    }

    private static boolean isVideoStateSet(int i, int j)
    {
        boolean flag;
        if((i & j) == j)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public static int presentationToOIR(int i)
    {
        switch(i)
        {
        default:
            return 0;

        case 2: // '\002'
            return 1;

        case 1: // '\001'
            return 2;

        case 4: // '\004'
            return 4;

        case 3: // '\003'
            return 3;
        }
    }

    private void readFromParcel(Parcel parcel)
    {
        mServiceType = parcel.readInt();
        mCallType = parcel.readInt();
        mCallExtras = (Bundle)parcel.readParcelable(null);
        mMediaProfile = (ImsStreamMediaProfile)parcel.readParcelable(null);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getCallExtra(String s)
    {
        return getCallExtra(s, "");
    }

    public String getCallExtra(String s, String s1)
    {
        if(mCallExtras == null)
            return s1;
        else
            return mCallExtras.getString(s, s1);
    }

    public boolean getCallExtraBoolean(String s)
    {
        return getCallExtraBoolean(s, false);
    }

    public boolean getCallExtraBoolean(String s, boolean flag)
    {
        if(mCallExtras == null)
            return flag;
        else
            return mCallExtras.getBoolean(s, flag);
    }

    public int getCallExtraInt(String s)
    {
        return getCallExtraInt(s, -1);
    }

    public int getCallExtraInt(String s, int i)
    {
        if(mCallExtras == null)
            return i;
        else
            return mCallExtras.getInt(s, i);
    }

    public boolean isVideoCall()
    {
        return VideoProfile.isVideo(getVideoStateFromCallType(mCallType));
    }

    public boolean isVideoPaused()
    {
        boolean flag = false;
        if(mMediaProfile.mVideoDirection == 0)
            flag = true;
        return flag;
    }

    public void setCallExtra(String s, String s1)
    {
        if(mCallExtras != null)
            mCallExtras.putString(s, s1);
    }

    public void setCallExtraBoolean(String s, boolean flag)
    {
        if(mCallExtras != null)
            mCallExtras.putBoolean(s, flag);
    }

    public void setCallExtraInt(String s, int i)
    {
        if(mCallExtras != null)
            mCallExtras.putInt(s, i);
    }

    public String toString()
    {
        return (new StringBuilder()).append("{ serviceType=").append(mServiceType).append(", callType=").append(mCallType).append(", restrictCause=").append(mRestrictCause).append(", mediaProfile=").append(mMediaProfile.toString()).append(" }").toString();
    }

    public void updateCallExtras(ImsCallProfile imscallprofile)
    {
        mCallExtras.clear();
        mCallExtras = (Bundle)imscallprofile.mCallExtras.clone();
    }

    public void updateCallType(ImsCallProfile imscallprofile)
    {
        mCallType = imscallprofile.mCallType;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mServiceType);
        parcel.writeInt(mCallType);
        parcel.writeParcelable(mCallExtras, 0);
        parcel.writeParcelable(mMediaProfile, 0);
    }

    public static final int CALL_RESTRICT_CAUSE_DISABLED = 2;
    public static final int CALL_RESTRICT_CAUSE_HD = 3;
    public static final int CALL_RESTRICT_CAUSE_NONE = 0;
    public static final int CALL_RESTRICT_CAUSE_RAT = 1;
    public static final int CALL_TYPE_VIDEO_N_VOICE = 3;
    public static final int CALL_TYPE_VOICE = 2;
    public static final int CALL_TYPE_VOICE_N_VIDEO = 1;
    public static final int CALL_TYPE_VS = 8;
    public static final int CALL_TYPE_VS_RX = 10;
    public static final int CALL_TYPE_VS_TX = 9;
    public static final int CALL_TYPE_VT = 4;
    public static final int CALL_TYPE_VT_NODIR = 7;
    public static final int CALL_TYPE_VT_RX = 6;
    public static final int CALL_TYPE_VT_TX = 5;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImsCallProfile createFromParcel(Parcel parcel)
        {
            return new ImsCallProfile(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImsCallProfile[] newArray(int i)
        {
            return new ImsCallProfile[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DIALSTRING_NORMAL = 0;
    public static final int DIALSTRING_SS_CONF = 1;
    public static final int DIALSTRING_USSD = 2;
    public static final String EXTRA_ADDITIONAL_CALL_INFO = "AdditionalCallInfo";
    public static final String EXTRA_CALL_MODE_CHANGEABLE = "call_mode_changeable";
    public static final String EXTRA_CALL_RAT_TYPE = "CallRadioTech";
    public static final String EXTRA_CALL_RAT_TYPE_ALT = "callRadioTech";
    public static final String EXTRA_CHILD_NUMBER = "ChildNum";
    public static final String EXTRA_CNA = "cna";
    public static final String EXTRA_CNAP = "cnap";
    public static final String EXTRA_CODEC = "Codec";
    public static final String EXTRA_CONFERENCE = "conference";
    public static final String EXTRA_CONFERENCE_AVAIL = "conference_avail";
    public static final String EXTRA_DIALSTRING = "dialstring";
    public static final String EXTRA_DISPLAY_TEXT = "DisplayText";
    public static final String EXTRA_E_CALL = "e_call";
    public static final String EXTRA_IS_CALL_PULL = "CallPull";
    public static final String EXTRA_OEM_EXTRAS = "OemCallExtras";
    public static final String EXTRA_OI = "oi";
    public static final String EXTRA_OIR = "oir";
    public static final String EXTRA_REMOTE_URI = "remote_uri";
    public static final String EXTRA_USSD = "ussd";
    public static final String EXTRA_VMS = "vms";
    public static final int OIR_DEFAULT = 0;
    public static final int OIR_PRESENTATION_NOT_RESTRICTED = 2;
    public static final int OIR_PRESENTATION_PAYPHONE = 4;
    public static final int OIR_PRESENTATION_RESTRICTED = 1;
    public static final int OIR_PRESENTATION_UNKNOWN = 3;
    public static final int SERVICE_TYPE_EMERGENCY = 2;
    public static final int SERVICE_TYPE_NONE = 0;
    public static final int SERVICE_TYPE_NORMAL = 1;
    private static final String TAG = "ImsCallProfile";
    public Bundle mCallExtras;
    public int mCallType;
    public ImsStreamMediaProfile mMediaProfile;
    public int mRestrictCause;
    public int mServiceType;

}
