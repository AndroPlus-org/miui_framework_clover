// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.DebugUtils;
import android.util.TimeUtils;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.Preconditions;
import java.util.Objects;

// Referenced classes of package android.os.storage:
//            VolumeInfo

public class VolumeRecord
    implements Parcelable
{

    public VolumeRecord(int i, String s)
    {
        type = i;
        fsUuid = (String)Preconditions.checkNotNull(s);
    }

    public VolumeRecord(Parcel parcel)
    {
        type = parcel.readInt();
        fsUuid = parcel.readString();
        partGuid = parcel.readString();
        nickname = parcel.readString();
        userFlags = parcel.readInt();
        createdMillis = parcel.readLong();
        lastTrimMillis = parcel.readLong();
        lastBenchMillis = parcel.readLong();
    }

    public VolumeRecord clone()
    {
        Parcel parcel = Parcel.obtain();
        VolumeRecord volumerecord;
        writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        volumerecord = (VolumeRecord)CREATOR.createFromParcel(parcel);
        parcel.recycle();
        return volumerecord;
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
        indentingprintwriter.println("VolumeRecord:");
        indentingprintwriter.increaseIndent();
        indentingprintwriter.printPair("type", DebugUtils.valueToString(android/os/storage/VolumeInfo, "TYPE_", type));
        indentingprintwriter.printPair("fsUuid", fsUuid);
        indentingprintwriter.printPair("partGuid", partGuid);
        indentingprintwriter.println();
        indentingprintwriter.printPair("nickname", nickname);
        indentingprintwriter.printPair("userFlags", DebugUtils.flagsToString(android/os/storage/VolumeRecord, "USER_FLAG_", userFlags));
        indentingprintwriter.println();
        indentingprintwriter.printPair("createdMillis", TimeUtils.formatForLogging(createdMillis));
        indentingprintwriter.printPair("lastTrimMillis", TimeUtils.formatForLogging(lastTrimMillis));
        indentingprintwriter.printPair("lastBenchMillis", TimeUtils.formatForLogging(lastBenchMillis));
        indentingprintwriter.decreaseIndent();
        indentingprintwriter.println();
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof VolumeRecord)
            return Objects.equals(fsUuid, ((VolumeRecord)obj).fsUuid);
        else
            return false;
    }

    public String getFsUuid()
    {
        return fsUuid;
    }

    public String getNickname()
    {
        return nickname;
    }

    public int getType()
    {
        return type;
    }

    public int hashCode()
    {
        return fsUuid.hashCode();
    }

    public boolean isInited()
    {
        boolean flag = false;
        if((userFlags & 1) != 0)
            flag = true;
        return flag;
    }

    public boolean isSnoozed()
    {
        boolean flag = false;
        if((userFlags & 2) != 0)
            flag = true;
        return flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(type);
        parcel.writeString(fsUuid);
        parcel.writeString(partGuid);
        parcel.writeString(nickname);
        parcel.writeInt(userFlags);
        parcel.writeLong(createdMillis);
        parcel.writeLong(lastTrimMillis);
        parcel.writeLong(lastBenchMillis);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public VolumeRecord createFromParcel(Parcel parcel)
        {
            return new VolumeRecord(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public VolumeRecord[] newArray(int i)
        {
            return new VolumeRecord[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String EXTRA_FS_UUID = "android.os.storage.extra.FS_UUID";
    public static final int USER_FLAG_INITED = 1;
    public static final int USER_FLAG_SNOOZED = 2;
    public long createdMillis;
    public final String fsUuid;
    public long lastBenchMillis;
    public long lastTrimMillis;
    public String nickname;
    public String partGuid;
    public final int type;
    public int userFlags;

}
