// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.ims;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import miui.telephony.PhoneNumberUtils;

public class ImsSuppServiceNotification
    implements Parcelable
{

    public ImsSuppServiceNotification()
    {
    }

    public ImsSuppServiceNotification(Parcel parcel)
    {
        readFromParcel(parcel);
    }

    private void readFromParcel(Parcel parcel)
    {
        notificationType = parcel.readInt();
        code = parcel.readInt();
        index = parcel.readInt();
        type = parcel.readInt();
        number = parcel.readString();
        history = parcel.createStringArray();
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return (new StringBuilder()).append("{ notificationType=").append(notificationType).append(", code=").append(code).append(", index=").append(index).append(", type=").append(type).append(", number=").append(number).append(", history=").append(PhoneNumberUtils.toLogSafePhoneNumber(Arrays.toString(history))).append(" }").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(notificationType);
        parcel.writeInt(code);
        parcel.writeInt(index);
        parcel.writeInt(type);
        parcel.writeString(number);
        parcel.writeStringArray(history);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public ImsSuppServiceNotification createFromParcel(Parcel parcel)
        {
            return new ImsSuppServiceNotification(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public ImsSuppServiceNotification[] newArray(int i)
        {
            return new ImsSuppServiceNotification[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "ImsSuppServiceNotification";
    public int code;
    public String history[];
    public int index;
    public int notificationType;
    public String number;
    public int type;

}
