// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.InputChannel;

// Referenced classes of package com.android.internal.view:
//            IInputMethodSession

public final class InputBindResult
    implements Parcelable
{

    InputBindResult(Parcel parcel)
    {
        method = IInputMethodSession.Stub.asInterface(parcel.readStrongBinder());
        if(parcel.readInt() != 0)
            channel = (InputChannel)InputChannel.CREATOR.createFromParcel(parcel);
        else
            channel = null;
        id = parcel.readString();
        sequence = parcel.readInt();
        userActionNotificationSequenceNumber = parcel.readInt();
    }

    public InputBindResult(IInputMethodSession iinputmethodsession, InputChannel inputchannel, String s, int i, int j)
    {
        method = iinputmethodsession;
        channel = inputchannel;
        id = s;
        sequence = i;
        userActionNotificationSequenceNumber = j;
    }

    public int describeContents()
    {
        int i;
        if(channel != null)
            i = channel.describeContents();
        else
            i = 0;
        return i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("InputBindResult{").append(method).append(" ").append(id).append(" sequence:").append(sequence).append(" userActionNotificationSequenceNumber:").append(userActionNotificationSequenceNumber).append("}").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongInterface(method);
        if(channel != null)
        {
            parcel.writeInt(1);
            channel.writeToParcel(parcel, i);
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeString(id);
        parcel.writeInt(sequence);
        parcel.writeInt(userActionNotificationSequenceNumber);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InputBindResult createFromParcel(Parcel parcel)
        {
            return new InputBindResult(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InputBindResult[] newArray(int i)
        {
            return new InputBindResult[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final String TAG = "InputBindResult";
    public final InputChannel channel;
    public final String id;
    public final IInputMethodSession method;
    public final int sequence;
    public final int userActionNotificationSequenceNumber;

}
