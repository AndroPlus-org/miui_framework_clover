// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.os.*;
import java.io.IOException;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public final class SystemUpdateInfo
    implements Parcelable
{

    private SystemUpdateInfo(long l, int i)
    {
        mReceivedTime = l;
        mSecurityPatchState = i;
    }

    private SystemUpdateInfo(Parcel parcel)
    {
        mReceivedTime = parcel.readLong();
        mSecurityPatchState = parcel.readInt();
    }

    SystemUpdateInfo(Parcel parcel, SystemUpdateInfo systemupdateinfo)
    {
        this(parcel);
    }

    public static SystemUpdateInfo of(long l)
    {
        SystemUpdateInfo systemupdateinfo;
        if(l == -1L)
            systemupdateinfo = null;
        else
            systemupdateinfo = new SystemUpdateInfo(l, 0);
        return systemupdateinfo;
    }

    public static SystemUpdateInfo of(long l, boolean flag)
    {
        SystemUpdateInfo systemupdateinfo;
        if(l == -1L)
        {
            systemupdateinfo = null;
        } else
        {
            byte byte0;
            if(flag)
                byte0 = 2;
            else
                byte0 = 1;
            systemupdateinfo = new SystemUpdateInfo(l, byte0);
        }
        return systemupdateinfo;
    }

    public static SystemUpdateInfo readFromXml(XmlPullParser xmlpullparser)
    {
        String s = xmlpullparser.getAttributeValue(null, "original-build");
        if(!Build.FINGERPRINT.equals(s))
            return null;
        else
            return new SystemUpdateInfo(Long.parseLong(xmlpullparser.getAttributeValue(null, "received-time")), Integer.parseInt(xmlpullparser.getAttributeValue(null, "security-patch-state")));
    }

    private static String securityPatchStateToString(int i)
    {
        switch(i)
        {
        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unrecognized security patch state: ").append(i).toString());

        case 1: // '\001'
            return "false";

        case 2: // '\002'
            return "true";

        case 0: // '\0'
            return "unknown";
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (SystemUpdateInfo)obj;
        if(mReceivedTime == ((SystemUpdateInfo) (obj)).mReceivedTime)
        {
            if(mSecurityPatchState != ((SystemUpdateInfo) (obj)).mSecurityPatchState)
                flag = false;
        } else
        {
            flag = false;
        }
        return flag;
    }

    public long getReceivedTime()
    {
        return mReceivedTime;
    }

    public int getSecurityPatchState()
    {
        return mSecurityPatchState;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Long.valueOf(mReceivedTime), Integer.valueOf(mSecurityPatchState)
        });
    }

    public String toString()
    {
        return String.format("SystemUpdateInfo (receivedTime = %d, securityPatchState = %s)", new Object[] {
            Long.valueOf(mReceivedTime), securityPatchStateToString(mSecurityPatchState)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(getReceivedTime());
        parcel.writeInt(getSecurityPatchState());
    }

    public void writeToXml(XmlSerializer xmlserializer, String s)
        throws IOException
    {
        xmlserializer.startTag(null, s);
        xmlserializer.attribute(null, "received-time", String.valueOf(mReceivedTime));
        xmlserializer.attribute(null, "security-patch-state", String.valueOf(mSecurityPatchState));
        xmlserializer.attribute(null, "original-build", Build.FINGERPRINT);
        xmlserializer.endTag(null, s);
    }

    private static final String ATTR_ORIGINAL_BUILD = "original-build";
    private static final String ATTR_RECEIVED_TIME = "received-time";
    private static final String ATTR_SECURITY_PATCH_STATE = "security-patch-state";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SystemUpdateInfo createFromParcel(Parcel parcel)
        {
            return new SystemUpdateInfo(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SystemUpdateInfo[] newArray(int i)
        {
            return new SystemUpdateInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int SECURITY_PATCH_STATE_FALSE = 1;
    public static final int SECURITY_PATCH_STATE_TRUE = 2;
    public static final int SECURITY_PATCH_STATE_UNKNOWN = 0;
    private final long mReceivedTime;
    private final int mSecurityPatchState;

}
