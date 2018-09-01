// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk.event;

import android.os.Parcel;
import android.os.Parcelable;

public class PackageForegroundEvent
    implements Parcelable
{

    public PackageForegroundEvent()
    {
        packageName = "";
        componentName = "";
        identity = -1;
        pid = -1;
        foregroundTime = -1L;
        isColdStart = false;
    }

    protected PackageForegroundEvent(Parcel parcel)
    {
        boolean flag = true;
        super();
        packageName = parcel.readString();
        componentName = parcel.readString();
        identity = parcel.readInt();
        pid = parcel.readInt();
        foregroundTime = parcel.readLong();
        if(parcel.readInt() != 1)
            flag = false;
        isColdStart = flag;
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
        obj = (PackageForegroundEvent)obj;
        boolean flag1 = flag;
        if(packageName.equals(((PackageForegroundEvent) (obj)).packageName))
        {
            flag1 = flag;
            if(componentName.equals(((PackageForegroundEvent) (obj)).componentName))
            {
                flag1 = flag;
                if(identity == ((PackageForegroundEvent) (obj)).identity)
                {
                    flag1 = flag;
                    if(pid == ((PackageForegroundEvent) (obj)).pid)
                        flag1 = true;
                }
            }
        }
        return flag1;
    }

    public String getComponentName()
    {
        return componentName;
    }

    public long getForegroundTime()
    {
        return foregroundTime;
    }

    public int getIdentity()
    {
        return identity;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public int getPid()
    {
        return pid;
    }

    public boolean isColdStart()
    {
        return isColdStart;
    }

    public void setColdStart(boolean flag)
    {
        isColdStart = flag;
    }

    public void setComponentName(String s)
    {
        componentName = s;
    }

    public void setForegroundTime(long l)
    {
        foregroundTime = l;
    }

    public void setIdentity(int i)
    {
        identity = i;
    }

    public void setPackageName(String s)
    {
        packageName = s;
    }

    public void setPid(int i)
    {
        pid = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append("PackageForegroundEvent{packageName='").append(packageName).append('\'').append(", componentName='").append(componentName).append('\'').append(", identity=").append(identity).append(", pid=").append(pid).append(", foregroundTime=").append(foregroundTime).append(", isColdStart=").append(isColdStart).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(packageName);
        parcel.writeString(componentName);
        parcel.writeInt(identity);
        parcel.writeInt(pid);
        parcel.writeLong(foregroundTime);
        if(isColdStart)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PackageForegroundEvent createFromParcel(Parcel parcel)
        {
            return new PackageForegroundEvent(parcel);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public PackageForegroundEvent[] newArray(int i)
        {
            return new PackageForegroundEvent[i];
        }

    }
;
    private String componentName;
    private long foregroundTime;
    private int identity;
    private boolean isColdStart;
    private String packageName;
    private int pid;

}
