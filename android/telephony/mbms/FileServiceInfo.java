// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;

// Referenced classes of package android.telephony.mbms:
//            ServiceInfo, FileInfo

public final class FileServiceInfo extends ServiceInfo
    implements Parcelable
{

    FileServiceInfo(Parcel parcel)
    {
        super(parcel);
        files = new ArrayList();
        parcel.readList(files, android/telephony/mbms/FileInfo.getClassLoader());
    }

    public FileServiceInfo(Map map, String s, List list, String s1, Date date, Date date1, List list1)
    {
        super(map, s, list, s1, date, date1);
        files = new ArrayList(list1);
    }

    public int describeContents()
    {
        return 0;
    }

    public List getFiles()
    {
        return files;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        super.writeToParcel(parcel, i);
        parcel.writeList(files);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public FileServiceInfo createFromParcel(Parcel parcel)
        {
            return new FileServiceInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public FileServiceInfo[] newArray(int i)
        {
            return new FileServiceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final List files;

}
