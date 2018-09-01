// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.Objects;

public final class DisconnectCause
    implements Parcelable
{

    public DisconnectCause(int i)
    {
        this(i, null, null, null, -1);
    }

    public DisconnectCause(int i, CharSequence charsequence, CharSequence charsequence1, String s)
    {
        this(i, charsequence, charsequence1, s, -1);
    }

    public DisconnectCause(int i, CharSequence charsequence, CharSequence charsequence1, String s, int j)
    {
        mDisconnectCode = i;
        mDisconnectLabel = charsequence;
        mDisconnectDescription = charsequence1;
        mDisconnectReason = s;
        mToneToPlay = j;
    }

    public DisconnectCause(int i, String s)
    {
        this(i, null, null, s, -1);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof DisconnectCause)
        {
            obj = (DisconnectCause)obj;
            boolean flag1 = flag;
            if(Objects.equals(Integer.valueOf(mDisconnectCode), Integer.valueOf(((DisconnectCause) (obj)).getCode())))
            {
                flag1 = flag;
                if(Objects.equals(mDisconnectLabel, ((DisconnectCause) (obj)).getLabel()))
                {
                    flag1 = flag;
                    if(Objects.equals(mDisconnectDescription, ((DisconnectCause) (obj)).getDescription()))
                    {
                        flag1 = flag;
                        if(Objects.equals(mDisconnectReason, ((DisconnectCause) (obj)).getReason()))
                            flag1 = Objects.equals(Integer.valueOf(mToneToPlay), Integer.valueOf(((DisconnectCause) (obj)).getTone()));
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public int getCode()
    {
        return mDisconnectCode;
    }

    public CharSequence getDescription()
    {
        return mDisconnectDescription;
    }

    public CharSequence getLabel()
    {
        return mDisconnectLabel;
    }

    public String getReason()
    {
        return mDisconnectReason;
    }

    public int getTone()
    {
        return mToneToPlay;
    }

    public int hashCode()
    {
        return Objects.hashCode(Integer.valueOf(mDisconnectCode)) + Objects.hashCode(mDisconnectLabel) + Objects.hashCode(mDisconnectDescription) + Objects.hashCode(mDisconnectReason) + Objects.hashCode(Integer.valueOf(mToneToPlay));
    }

    public String toString()
    {
        mDisconnectCode;
        JVM INSTR tableswitch 0 12: default 72
    //                   0 211
    //                   1 217
    //                   2 223
    //                   3 229
    //                   4 235
    //                   5 241
    //                   6 247
    //                   7 253
    //                   8 259
    //                   9 265
    //                   10 271
    //                   11 283
    //                   12 277;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L13:
        break MISSING_BLOCK_LABEL_283;
_L1:
        String s = (new StringBuilder()).append("invalid code: ").append(mDisconnectCode).toString();
_L15:
        String s1;
        String s2;
        String s3;
        if(mDisconnectLabel == null)
            s1 = "";
        else
            s1 = mDisconnectLabel.toString();
        if(mDisconnectDescription == null)
            s2 = "";
        else
            s2 = mDisconnectDescription.toString();
        if(mDisconnectReason == null)
            s3 = "";
        else
            s3 = mDisconnectReason;
        return (new StringBuilder()).append("DisconnectCause [ Code: (").append(s).append(")").append(" Label: (").append(s1).append(")").append(" Description: (").append(s2).append(")").append(" Reason: (").append(s3).append(")").append(" Tone: (").append(mToneToPlay).append(") ]").toString();
_L2:
        s = "UNKNOWN";
          goto _L15
_L3:
        s = "ERROR";
          goto _L15
_L4:
        s = "LOCAL";
          goto _L15
_L5:
        s = "REMOTE";
          goto _L15
_L6:
        s = "CANCELED";
          goto _L15
_L7:
        s = "MISSED";
          goto _L15
_L8:
        s = "REJECTED";
          goto _L15
_L9:
        s = "BUSY";
          goto _L15
_L10:
        s = "RESTRICTED";
          goto _L15
_L11:
        s = "OTHER";
          goto _L15
_L12:
        s = "CONNECTION_MANAGER_NOT_SUPPORTED";
          goto _L15
_L14:
        s = "CALL_PULLED";
          goto _L15
        s = "ANSWERED_ELSEWHERE";
          goto _L15
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mDisconnectCode);
        TextUtils.writeToParcel(mDisconnectLabel, parcel, i);
        TextUtils.writeToParcel(mDisconnectDescription, parcel, i);
        parcel.writeString(mDisconnectReason);
        parcel.writeInt(mToneToPlay);
    }

    public static final int ANSWERED_ELSEWHERE = 11;
    public static final int BUSY = 7;
    public static final int CALL_PULLED = 12;
    public static final int CANCELED = 4;
    public static final int CONNECTION_MANAGER_NOT_SUPPORTED = 10;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DisconnectCause createFromParcel(Parcel parcel)
        {
            return new DisconnectCause(parcel.readInt(), (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel), (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DisconnectCause[] newArray(int i)
        {
            return new DisconnectCause[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int ERROR = 1;
    public static final int LOCAL = 2;
    public static final int MISSED = 5;
    public static final int OTHER = 9;
    public static final String REASON_IMS_ACCESS_BLOCKED = "REASON_IMS_ACCESS_BLOCKED";
    public static final String REASON_WIFI_ON_BUT_WFC_OFF = "REASON_WIFI_ON_BUT_WFC_OFF";
    public static final int REJECTED = 6;
    public static final int REMOTE = 3;
    public static final int RESTRICTED = 8;
    public static final int UNKNOWN = 0;
    private int mDisconnectCode;
    private CharSequence mDisconnectDescription;
    private CharSequence mDisconnectLabel;
    private String mDisconnectReason;
    private int mToneToPlay;

}
