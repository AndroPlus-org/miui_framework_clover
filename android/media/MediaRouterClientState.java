// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class MediaRouterClientState
    implements Parcelable
{
    public static final class RouteInfo
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public String toString()
        {
            return (new StringBuilder()).append("RouteInfo{ id=").append(id).append(", name=").append(name).append(", description=").append(description).append(", supportedTypes=0x").append(Integer.toHexString(supportedTypes)).append(", enabled=").append(enabled).append(", statusCode=").append(statusCode).append(", playbackType=").append(playbackType).append(", playbackStream=").append(playbackStream).append(", volume=").append(volume).append(", volumeMax=").append(volumeMax).append(", volumeHandling=").append(volumeHandling).append(", presentationDisplayId=").append(presentationDisplayId).append(", deviceType=").append(deviceType).append(" }").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(id);
            parcel.writeString(name);
            parcel.writeString(description);
            parcel.writeInt(supportedTypes);
            if(enabled)
                i = 1;
            else
                i = 0;
            parcel.writeInt(i);
            parcel.writeInt(statusCode);
            parcel.writeInt(playbackType);
            parcel.writeInt(playbackStream);
            parcel.writeInt(volume);
            parcel.writeInt(volumeMax);
            parcel.writeInt(volumeHandling);
            parcel.writeInt(presentationDisplayId);
            parcel.writeInt(deviceType);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public RouteInfo createFromParcel(Parcel parcel)
            {
                return new RouteInfo(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public RouteInfo[] newArray(int i)
            {
                return new RouteInfo[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public String description;
        public int deviceType;
        public boolean enabled;
        public String id;
        public String name;
        public int playbackStream;
        public int playbackType;
        public int presentationDisplayId;
        public int statusCode;
        public int supportedTypes;
        public int volume;
        public int volumeHandling;
        public int volumeMax;


        public RouteInfo(RouteInfo routeinfo)
        {
            id = routeinfo.id;
            name = routeinfo.name;
            description = routeinfo.description;
            supportedTypes = routeinfo.supportedTypes;
            enabled = routeinfo.enabled;
            statusCode = routeinfo.statusCode;
            playbackType = routeinfo.playbackType;
            playbackStream = routeinfo.playbackStream;
            volume = routeinfo.volume;
            volumeMax = routeinfo.volumeMax;
            volumeHandling = routeinfo.volumeHandling;
            presentationDisplayId = routeinfo.presentationDisplayId;
            deviceType = routeinfo.deviceType;
        }

        RouteInfo(Parcel parcel)
        {
            boolean flag = false;
            super();
            id = parcel.readString();
            name = parcel.readString();
            description = parcel.readString();
            supportedTypes = parcel.readInt();
            if(parcel.readInt() != 0)
                flag = true;
            enabled = flag;
            statusCode = parcel.readInt();
            playbackType = parcel.readInt();
            playbackStream = parcel.readInt();
            volume = parcel.readInt();
            volumeMax = parcel.readInt();
            volumeHandling = parcel.readInt();
            presentationDisplayId = parcel.readInt();
            deviceType = parcel.readInt();
        }

        public RouteInfo(String s)
        {
            id = s;
            enabled = true;
            statusCode = 0;
            playbackType = 1;
            playbackStream = -1;
            volumeHandling = 0;
            presentationDisplayId = -1;
            deviceType = 0;
        }
    }


    public MediaRouterClientState()
    {
        routes = new ArrayList();
    }

    MediaRouterClientState(Parcel parcel)
    {
        routes = parcel.createTypedArrayList(RouteInfo.CREATOR);
    }

    public int describeContents()
    {
        return 0;
    }

    public RouteInfo getRoute(String s)
    {
        int i = routes.size();
        for(int j = 0; j < i; j++)
        {
            RouteInfo routeinfo = (RouteInfo)routes.get(j);
            if(routeinfo.id.equals(s))
                return routeinfo;
        }

        return null;
    }

    public String toString()
    {
        return (new StringBuilder()).append("MediaRouterClientState{ routes=").append(routes.toString()).append(" }").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeTypedList(routes);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MediaRouterClientState createFromParcel(Parcel parcel)
        {
            return new MediaRouterClientState(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MediaRouterClientState[] newArray(int i)
        {
            return new MediaRouterClientState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public final ArrayList routes;

}
