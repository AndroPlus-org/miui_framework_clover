// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

public final class RemoteDisplayState
    implements Parcelable
{
    public static final class RemoteDisplayInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean isValid()
        {
            boolean flag;
            if(!TextUtils.isEmpty(id))
                flag = TextUtils.isEmpty(name) ^ true;
            else
                flag = false;
            return flag;
        }

        public String toString()
        {
            return (new StringBuilder()).append("RemoteDisplayInfo{ id=").append(id).append(", name=").append(name).append(", description=").append(description).append(", status=").append(status).append(", volume=").append(volume).append(", volumeMax=").append(volumeMax).append(", volumeHandling=").append(volumeHandling).append(", presentationDisplayId=").append(presentationDisplayId).append(" }").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(id);
            parcel.writeString(name);
            parcel.writeString(description);
            parcel.writeInt(status);
            parcel.writeInt(volume);
            parcel.writeInt(volumeMax);
            parcel.writeInt(volumeHandling);
            parcel.writeInt(presentationDisplayId);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RemoteDisplayInfo createFromParcel(Parcel parcel)
            {
                return new RemoteDisplayInfo(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RemoteDisplayInfo[] newArray(int i)
            {
                return new RemoteDisplayInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int PLAYBACK_VOLUME_FIXED = 0;
        public static final int PLAYBACK_VOLUME_VARIABLE = 1;
        public static final int STATUS_AVAILABLE = 2;
        public static final int STATUS_CONNECTED = 4;
        public static final int STATUS_CONNECTING = 3;
        public static final int STATUS_IN_USE = 1;
        public static final int STATUS_NOT_AVAILABLE = 0;
        public String description;
        public String id;
        public String name;
        public int presentationDisplayId;
        public int status;
        public int volume;
        public int volumeHandling;
        public int volumeMax;


        public RemoteDisplayInfo(RemoteDisplayInfo remotedisplayinfo)
        {
            id = remotedisplayinfo.id;
            name = remotedisplayinfo.name;
            description = remotedisplayinfo.description;
            status = remotedisplayinfo.status;
            volume = remotedisplayinfo.volume;
            volumeMax = remotedisplayinfo.volumeMax;
            volumeHandling = remotedisplayinfo.volumeHandling;
            presentationDisplayId = remotedisplayinfo.presentationDisplayId;
        }

        RemoteDisplayInfo(Parcel parcel)
        {
            id = parcel.readString();
            name = parcel.readString();
            description = parcel.readString();
            status = parcel.readInt();
            volume = parcel.readInt();
            volumeMax = parcel.readInt();
            volumeHandling = parcel.readInt();
            presentationDisplayId = parcel.readInt();
        }

        public RemoteDisplayInfo(String s)
        {
            id = s;
            status = 0;
            volumeHandling = 0;
            presentationDisplayId = -1;
        }
    }


    public RemoteDisplayState()
    {
        displays = new ArrayList();
    }

    RemoteDisplayState(Parcel parcel)
    {
        displays = parcel.createTypedArrayList(RemoteDisplayInfo.CREATOR);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean isValid()
    {
        if(displays == null)
            return false;
        int i = displays.size();
        for(int j = 0; j < i; j++)
            if(!((RemoteDisplayInfo)displays.get(j)).isValid())
                return false;

        return true;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(displays);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RemoteDisplayState createFromParcel(Parcel parcel)
        {
            return new RemoteDisplayState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RemoteDisplayState[] newArray(int i)
        {
            return new RemoteDisplayState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DISCOVERY_MODE_ACTIVE = 2;
    public static final int DISCOVERY_MODE_NONE = 0;
    public static final int DISCOVERY_MODE_PASSIVE = 1;
    public static final String SERVICE_INTERFACE = "com.android.media.remotedisplay.RemoteDisplayProvider";
    public final ArrayList displays;

}
