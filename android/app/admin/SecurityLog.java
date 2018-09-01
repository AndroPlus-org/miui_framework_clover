// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.os.*;
import java.io.IOException;
import java.util.Collection;

public class SecurityLog
{
    public static final class SecurityEvent
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this == obj)
                return true;
            if(obj == null || getClass() != obj.getClass())
            {
                return false;
            } else
            {
                obj = (SecurityEvent)obj;
                return mEvent.equals(((SecurityEvent) (obj)).mEvent);
            }
        }

        public Object getData()
        {
            return mEvent.getData();
        }

        public int getTag()
        {
            return mEvent.getTag();
        }

        public long getTimeNanos()
        {
            return mEvent.getTimeNanos();
        }

        public int hashCode()
        {
            return mEvent.hashCode();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeByteArray(mEvent.getBytes());
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public SecurityEvent createFromParcel(Parcel parcel)
            {
                return new SecurityEvent(parcel.createByteArray());
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public SecurityEvent[] newArray(int i)
            {
                return new SecurityEvent[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private android.util.EventLog.Event mEvent;


        SecurityEvent(byte abyte0[])
        {
            mEvent = android.util.EventLog.Event.fromBytes(abyte0);
        }
    }


    public SecurityLog()
    {
    }

    public static boolean getLoggingEnabledProperty()
    {
        return SystemProperties.getBoolean("persist.logd.security", false);
    }

    public static native boolean isLoggingEnabled();

    public static native void readEvents(Collection collection)
        throws IOException;

    public static native void readEventsOnWrapping(long l, Collection collection)
        throws IOException;

    public static native void readEventsSince(long l, Collection collection)
        throws IOException;

    public static native void readPreviousEvents(Collection collection)
        throws IOException;

    public static void setLoggingEnabledProperty(boolean flag)
    {
        String s;
        if(flag)
            s = "true";
        else
            s = "false";
        SystemProperties.set("persist.logd.security", s);
    }

    public static native int writeEvent(int i, String s);

    public static transient native int writeEvent(int i, Object aobj[]);

    private static final String PROPERTY_LOGGING_ENABLED = "persist.logd.security";
    public static final int TAG_ADB_SHELL_CMD = 0x33452;
    public static final int TAG_ADB_SHELL_INTERACTIVE = 0x33451;
    public static final int TAG_APP_PROCESS_START = 0x33455;
    public static final int TAG_KEYGUARD_DISMISSED = 0x33456;
    public static final int TAG_KEYGUARD_DISMISS_AUTH_ATTEMPT = 0x33457;
    public static final int TAG_KEYGUARD_SECURED = 0x33458;
    public static final int TAG_SYNC_RECV_FILE = 0x33453;
    public static final int TAG_SYNC_SEND_FILE = 0x33454;
}
