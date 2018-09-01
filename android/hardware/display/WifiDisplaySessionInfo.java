// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

public final class WifiDisplaySessionInfo
    implements Parcelable
{

    public WifiDisplaySessionInfo()
    {
        this(true, 0, "", "", "");
    }

    public WifiDisplaySessionInfo(boolean flag, int i, String s, String s1, String s2)
    {
        mClient = flag;
        mSessionId = i;
        mGroupId = s;
        mPassphrase = s1;
        mIP = s2;
    }

    public int describeContents()
    {
        return 0;
    }

    public String getGroupId()
    {
        return mGroupId;
    }

    public String getIP()
    {
        return mIP;
    }

    public String getPassphrase()
    {
        return mPassphrase;
    }

    public int getSessionId()
    {
        return mSessionId;
    }

    public boolean isClient()
    {
        return mClient;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("WifiDisplaySessionInfo:\n    Client/Owner: ");
        String s;
        if(mClient)
            s = "Client";
        else
            s = "Owner";
        return stringbuilder.append(s).append("\n    GroupId: ").append(mGroupId).append("\n    Passphrase: ").append(mPassphrase).append("\n    SessionId: ").append(mSessionId).append("\n    IP Address: ").append(mIP).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(mClient)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeInt(mSessionId);
        parcel.writeString(mGroupId);
        parcel.writeString(mPassphrase);
        parcel.writeString(mIP);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiDisplaySessionInfo createFromParcel(Parcel parcel)
        {
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            return new WifiDisplaySessionInfo(flag, parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiDisplaySessionInfo[] newArray(int i)
        {
            return new WifiDisplaySessionInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final boolean mClient;
    private final String mGroupId;
    private final String mIP;
    private final String mPassphrase;
    private final int mSessionId;

}
