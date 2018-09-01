// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DebugUtils;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.Preconditions;
import java.io.CharArrayWriter;
import java.util.Objects;

public class DiskInfo
    implements Parcelable
{

    public DiskInfo(Parcel parcel)
    {
        id = parcel.readString();
        flags = parcel.readInt();
        size = parcel.readLong();
        label = parcel.readString();
        volumeCount = parcel.readInt();
        sysPath = parcel.readString();
    }

    public DiskInfo(String s, int i)
    {
        id = (String)Preconditions.checkNotNull(s);
        flags = i;
    }

    private boolean isInteresting(String s)
    {
        if(TextUtils.isEmpty(s))
            return false;
        if(s.equalsIgnoreCase("ata"))
            return false;
        if(s.toLowerCase().contains("generic"))
            return false;
        if(s.toLowerCase().startsWith("usb"))
            return false;
        return !s.toLowerCase().startsWith("multiple");
    }

    public DiskInfo clone()
    {
        Parcel parcel = Parcel.obtain();
        DiskInfo diskinfo;
        writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        diskinfo = (DiskInfo)CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return diskinfo;
        Exception exception;
        exception;
        parcel.recycle();
        throw exception;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public int describeContents()
    {
        return 0;
    }

    public void dump(IndentingPrintWriter indentingprintwriter)
    {
        indentingprintwriter.println((new StringBuilder()).append("DiskInfo{").append(id).append("}:").toString());
        indentingprintwriter.increaseIndent();
        indentingprintwriter.printPair("flags", DebugUtils.flagsToString(getClass(), "FLAG_", flags));
        indentingprintwriter.printPair("size", Long.valueOf(size));
        indentingprintwriter.printPair("label", label);
        indentingprintwriter.println();
        indentingprintwriter.printPair("sysPath", sysPath);
        indentingprintwriter.decreaseIndent();
        indentingprintwriter.println();
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof DiskInfo)
            return Objects.equals(id, ((DiskInfo)obj).id);
        else
            return false;
    }

    public String getDescription()
    {
        Resources resources = Resources.getSystem();
        if((flags & 4) != 0)
            if(isInteresting(label))
                return resources.getString(0x1040620, new Object[] {
                    label
                });
            else
                return resources.getString(0x104061f);
        if((flags & 8) != 0)
        {
            if(isInteresting(label))
                return resources.getString(0x1040623, new Object[] {
                    label
                });
            else
                return resources.getString(0x1040622);
        } else
        {
            return null;
        }
    }

    public String getId()
    {
        return id;
    }

    public int hashCode()
    {
        return id.hashCode();
    }

    public boolean isAdoptable()
    {
        boolean flag = false;
        if((flags & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean isDefaultPrimary()
    {
        boolean flag = false;
        if((flags & 2) != 0)
            flag = true;
        return flag;
    }

    public boolean isSd()
    {
        boolean flag = false;
        if((flags & 4) != 0)
            flag = true;
        return flag;
    }

    public boolean isUsb()
    {
        boolean flag = false;
        if((flags & 8) != 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        CharArrayWriter chararraywriter = new CharArrayWriter();
        dump(new IndentingPrintWriter(chararraywriter, "    ", 80));
        return chararraywriter.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(id);
        parcel.writeInt(flags);
        parcel.writeLong(size);
        parcel.writeString(label);
        parcel.writeInt(volumeCount);
        parcel.writeString(sysPath);
    }

    public static final String ACTION_DISK_SCANNED = "android.os.storage.action.DISK_SCANNED";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DiskInfo createFromParcel(Parcel parcel)
        {
            return new DiskInfo(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DiskInfo[] newArray(int i)
        {
            return new DiskInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String EXTRA_DISK_ID = "android.os.storage.extra.DISK_ID";
    public static final String EXTRA_VOLUME_COUNT = "android.os.storage.extra.VOLUME_COUNT";
    public static final int FLAG_ADOPTABLE = 1;
    public static final int FLAG_DEFAULT_PRIMARY = 2;
    public static final int FLAG_SD = 4;
    public static final int FLAG_USB = 8;
    public final int flags;
    public final String id;
    public String label;
    public long size;
    public String sysPath;
    public int volumeCount;

}
