// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.admin;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public class SystemUpdatePolicy
    implements Parcelable
{

    static int _2D_set0(SystemUpdatePolicy systemupdatepolicy, int i)
    {
        systemupdatepolicy.mMaintenanceWindowEnd = i;
        return i;
    }

    static int _2D_set1(SystemUpdatePolicy systemupdatepolicy, int i)
    {
        systemupdatepolicy.mMaintenanceWindowStart = i;
        return i;
    }

    static int _2D_set2(SystemUpdatePolicy systemupdatepolicy, int i)
    {
        systemupdatepolicy.mPolicyType = i;
        return i;
    }

    private SystemUpdatePolicy()
    {
        mPolicyType = -1;
    }

    SystemUpdatePolicy(SystemUpdatePolicy systemupdatepolicy)
    {
        this();
    }

    public static SystemUpdatePolicy createAutomaticInstallPolicy()
    {
        SystemUpdatePolicy systemupdatepolicy = new SystemUpdatePolicy();
        systemupdatepolicy.mPolicyType = 1;
        return systemupdatepolicy;
    }

    public static SystemUpdatePolicy createPostponeInstallPolicy()
    {
        SystemUpdatePolicy systemupdatepolicy = new SystemUpdatePolicy();
        systemupdatepolicy.mPolicyType = 3;
        return systemupdatepolicy;
    }

    public static SystemUpdatePolicy createWindowedInstallPolicy(int i, int j)
    {
        while(i < 0 || i >= 1440 || j < 0 || j >= 1440) 
            throw new IllegalArgumentException("startTime and endTime must be inside [0, 1440)");
        SystemUpdatePolicy systemupdatepolicy = new SystemUpdatePolicy();
        systemupdatepolicy.mPolicyType = 2;
        systemupdatepolicy.mMaintenanceWindowStart = i;
        systemupdatepolicy.mMaintenanceWindowEnd = j;
        return systemupdatepolicy;
    }

    public static SystemUpdatePolicy restoreFromXml(XmlPullParser xmlpullparser)
    {
        SystemUpdatePolicy systemupdatepolicy;
        String s;
        systemupdatepolicy = JVM INSTR new #2   <Class SystemUpdatePolicy>;
        systemupdatepolicy.SystemUpdatePolicy();
        s = xmlpullparser.getAttributeValue(null, "policy_type");
        if(s == null)
            break MISSING_BLOCK_LABEL_77;
        systemupdatepolicy.mPolicyType = Integer.parseInt(s);
        s = xmlpullparser.getAttributeValue(null, "install_window_start");
        if(s == null)
            break MISSING_BLOCK_LABEL_52;
        systemupdatepolicy.mMaintenanceWindowStart = Integer.parseInt(s);
        xmlpullparser = xmlpullparser.getAttributeValue(null, "install_window_end");
        if(xmlpullparser == null)
            break MISSING_BLOCK_LABEL_74;
        systemupdatepolicy.mMaintenanceWindowEnd = Integer.parseInt(xmlpullparser);
        return systemupdatepolicy;
        xmlpullparser;
        return null;
    }

    public int describeContents()
    {
        return 0;
    }

    public int getInstallWindowEnd()
    {
        if(mPolicyType == 2)
            return mMaintenanceWindowEnd;
        else
            return -1;
    }

    public int getInstallWindowStart()
    {
        if(mPolicyType == 2)
            return mMaintenanceWindowStart;
        else
            return -1;
    }

    public int getPolicyType()
    {
        return mPolicyType;
    }

    public boolean isValid()
    {
        boolean flag = true;
        if(mPolicyType == 1 || mPolicyType == 3)
            return true;
        if(mPolicyType == 2)
        {
            if(mMaintenanceWindowStart >= 0 && mMaintenanceWindowStart < 1440 && mMaintenanceWindowEnd >= 0)
            {
                if(mMaintenanceWindowEnd >= 1440)
                    flag = false;
            } else
            {
                flag = false;
            }
            return flag;
        } else
        {
            return false;
        }
    }

    public void saveToXml(XmlSerializer xmlserializer)
        throws IOException
    {
        xmlserializer.attribute(null, "policy_type", Integer.toString(mPolicyType));
        xmlserializer.attribute(null, "install_window_start", Integer.toString(mMaintenanceWindowStart));
        xmlserializer.attribute(null, "install_window_end", Integer.toString(mMaintenanceWindowEnd));
    }

    public String toString()
    {
        return String.format("SystemUpdatePolicy (type: %d, windowStart: %d, windowEnd: %d)", new Object[] {
            Integer.valueOf(mPolicyType), Integer.valueOf(mMaintenanceWindowStart), Integer.valueOf(mMaintenanceWindowEnd)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mPolicyType);
        parcel.writeInt(mMaintenanceWindowStart);
        parcel.writeInt(mMaintenanceWindowEnd);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public SystemUpdatePolicy createFromParcel(Parcel parcel)
        {
            SystemUpdatePolicy systemupdatepolicy = new SystemUpdatePolicy(null);
            SystemUpdatePolicy._2D_set2(systemupdatepolicy, parcel.readInt());
            SystemUpdatePolicy._2D_set1(systemupdatepolicy, parcel.readInt());
            SystemUpdatePolicy._2D_set0(systemupdatepolicy, parcel.readInt());
            return systemupdatepolicy;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public SystemUpdatePolicy[] newArray(int i)
        {
            return new SystemUpdatePolicy[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String KEY_INSTALL_WINDOW_END = "install_window_end";
    private static final String KEY_INSTALL_WINDOW_START = "install_window_start";
    private static final String KEY_POLICY_TYPE = "policy_type";
    public static final int TYPE_INSTALL_AUTOMATIC = 1;
    public static final int TYPE_INSTALL_WINDOWED = 2;
    public static final int TYPE_POSTPONE = 3;
    private static final int TYPE_UNKNOWN = -1;
    private static final int WINDOW_BOUNDARY = 1440;
    private int mMaintenanceWindowEnd;
    private int mMaintenanceWindowStart;
    private int mPolicyType;

}
