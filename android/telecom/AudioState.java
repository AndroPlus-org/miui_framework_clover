// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

// Referenced classes of package android.telecom:
//            CallAudioState

public class AudioState
    implements Parcelable
{

    public AudioState(AudioState audiostate)
    {
        isMuted = audiostate.isMuted();
        route = audiostate.getRoute();
        supportedRouteMask = audiostate.getSupportedRouteMask();
    }

    public AudioState(CallAudioState callaudiostate)
    {
        isMuted = callaudiostate.isMuted();
        route = callaudiostate.getRoute();
        supportedRouteMask = callaudiostate.getSupportedRouteMask();
    }

    public AudioState(boolean flag, int i, int j)
    {
        isMuted = flag;
        route = i;
        supportedRouteMask = j;
    }

    public static String audioRouteToString(int i)
    {
        if(i == 0 || (i & 0xfffffff0) != 0)
            return "UNKNOWN";
        StringBuffer stringbuffer = new StringBuffer();
        if((i & 1) == 1)
            listAppend(stringbuffer, "EARPIECE");
        if((i & 2) == 2)
            listAppend(stringbuffer, "BLUETOOTH");
        if((i & 4) == 4)
            listAppend(stringbuffer, "WIRED_HEADSET");
        if((i & 8) == 8)
            listAppend(stringbuffer, "SPEAKER");
        return stringbuffer.toString();
    }

    private static void listAppend(StringBuffer stringbuffer, String s)
    {
        if(stringbuffer.length() > 0)
            stringbuffer.append(", ");
        stringbuffer.append(s);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null)
            return false;
        if(!(obj instanceof AudioState))
            return false;
        obj = (AudioState)obj;
        boolean flag1 = flag;
        if(isMuted() == ((AudioState) (obj)).isMuted())
        {
            flag1 = flag;
            if(getRoute() == ((AudioState) (obj)).getRoute())
            {
                flag1 = flag;
                if(getSupportedRouteMask() == ((AudioState) (obj)).getSupportedRouteMask())
                    flag1 = true;
            }
        }
        return flag1;
    }

    public int getRoute()
    {
        return route;
    }

    public int getSupportedRouteMask()
    {
        return supportedRouteMask;
    }

    public boolean isMuted()
    {
        return isMuted;
    }

    public String toString()
    {
        return String.format(Locale.US, "[AudioState isMuted: %b, route: %s, supportedRouteMask: %s]", new Object[] {
            Boolean.valueOf(isMuted), audioRouteToString(route), audioRouteToString(supportedRouteMask)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(isMuted)
            i = 1;
        else
            i = 0;
        parcel.writeByte((byte)i);
        parcel.writeInt(route);
        parcel.writeInt(supportedRouteMask);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public AudioState createFromParcel(Parcel parcel)
        {
            boolean flag;
            if(parcel.readByte() == 0)
                flag = false;
            else
                flag = true;
            return new AudioState(flag, parcel.readInt(), parcel.readInt());
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public AudioState[] newArray(int i)
        {
            return new AudioState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final int ROUTE_ALL = 15;
    public static final int ROUTE_BLUETOOTH = 2;
    public static final int ROUTE_EARPIECE = 1;
    public static final int ROUTE_SPEAKER = 8;
    public static final int ROUTE_WIRED_HEADSET = 4;
    public static final int ROUTE_WIRED_OR_EARPIECE = 5;
    private final boolean isMuted;
    private final int route;
    private final int supportedRouteMask;

}
