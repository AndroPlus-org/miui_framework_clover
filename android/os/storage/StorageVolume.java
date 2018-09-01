// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.os.storage;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.Preconditions;
import java.io.CharArrayWriter;
import java.io.File;

public final class StorageVolume
    implements Parcelable
{

    private StorageVolume(Parcel parcel)
    {
        boolean flag = true;
        super();
        mId = parcel.readString();
        mStorageId = parcel.readInt();
        mPath = new File(parcel.readString());
        mDescription = parcel.readString();
        boolean flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mPrimary = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mRemovable = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mEmulated = flag1;
        mMtpReserveSize = parcel.readLong();
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mAllowMassStorage = flag1;
        mMaxFileSize = parcel.readLong();
        mOwner = (UserHandle)parcel.readParcelable(null);
        mFsUuid = parcel.readString();
        mState = parcel.readString();
    }

    StorageVolume(Parcel parcel, StorageVolume storagevolume)
    {
        this(parcel);
    }

    public StorageVolume(String s, int i, File file, String s1, boolean flag, boolean flag1, boolean flag2, 
            long l, boolean flag3, long l1, UserHandle userhandle, String s2, 
            String s3)
    {
        mId = (String)Preconditions.checkNotNull(s);
        mStorageId = i;
        mPath = (File)Preconditions.checkNotNull(file);
        mDescription = (String)Preconditions.checkNotNull(s1);
        mPrimary = flag;
        mRemovable = flag1;
        mEmulated = flag2;
        mMtpReserveSize = l;
        mAllowMassStorage = flag3;
        mMaxFileSize = l1;
        mOwner = (UserHandle)Preconditions.checkNotNull(userhandle);
        mFsUuid = s2;
        mState = (String)Preconditions.checkNotNull(s3);
    }

    public boolean allowMassStorage()
    {
        return mAllowMassStorage;
    }

    public Intent createAccessIntent(String s)
    {
        while(isPrimary() && s == null || s != null && Environment.isStandardDirectory(s) ^ true) 
            return null;
        Intent intent = new Intent("android.os.storage.action.OPEN_EXTERNAL_DIRECTORY");
        intent.putExtra("android.os.storage.extra.STORAGE_VOLUME", this);
        intent.putExtra("android.os.storage.extra.DIRECTORY_NAME", s);
        return intent;
    }

    public int describeContents()
    {
        return 0;
    }

    public String dump()
    {
        CharArrayWriter chararraywriter = new CharArrayWriter();
        dump(new IndentingPrintWriter(chararraywriter, "    ", 80));
        return chararraywriter.toString();
    }

    public void dump(IndentingPrintWriter indentingprintwriter)
    {
        indentingprintwriter.println("StorageVolume:");
        indentingprintwriter.increaseIndent();
        indentingprintwriter.printPair("mId", mId);
        indentingprintwriter.printPair("mStorageId", Integer.valueOf(mStorageId));
        indentingprintwriter.printPair("mPath", mPath);
        indentingprintwriter.printPair("mDescription", mDescription);
        indentingprintwriter.printPair("mPrimary", Boolean.valueOf(mPrimary));
        indentingprintwriter.printPair("mRemovable", Boolean.valueOf(mRemovable));
        indentingprintwriter.printPair("mEmulated", Boolean.valueOf(mEmulated));
        indentingprintwriter.printPair("mMtpReserveSize", Long.valueOf(mMtpReserveSize));
        indentingprintwriter.printPair("mAllowMassStorage", Boolean.valueOf(mAllowMassStorage));
        indentingprintwriter.printPair("mMaxFileSize", Long.valueOf(mMaxFileSize));
        indentingprintwriter.printPair("mOwner", mOwner);
        indentingprintwriter.printPair("mFsUuid", mFsUuid);
        indentingprintwriter.printPair("mState", mState);
        indentingprintwriter.decreaseIndent();
    }

    public boolean equals(Object obj)
    {
        if((obj instanceof StorageVolume) && mPath != null)
        {
            obj = (StorageVolume)obj;
            return mPath.equals(((StorageVolume) (obj)).mPath);
        } else
        {
            return false;
        }
    }

    public String getDescription(Context context)
    {
        return mDescription;
    }

    public int getFatVolumeId()
    {
        if(mFsUuid == null || mFsUuid.length() != 9)
            return -1;
        long l;
        try
        {
            l = Long.parseLong(mFsUuid.replace("-", ""), 16);
        }
        catch(NumberFormatException numberformatexception)
        {
            return -1;
        }
        return (int)l;
    }

    public String getId()
    {
        return mId;
    }

    public long getMaxFileSize()
    {
        return mMaxFileSize;
    }

    public int getMtpReserveSpace()
    {
        return (int)(mMtpReserveSize / 0x100000L);
    }

    public UserHandle getOwner()
    {
        return mOwner;
    }

    public String getPath()
    {
        return mPath.toString();
    }

    public File getPathFile()
    {
        return mPath;
    }

    public String getState()
    {
        return mState;
    }

    public int getStorageId()
    {
        return mStorageId;
    }

    public String getUserLabel()
    {
        return mDescription;
    }

    public String getUuid()
    {
        return mFsUuid;
    }

    public int hashCode()
    {
        return mPath.hashCode();
    }

    public boolean isEmulated()
    {
        return mEmulated;
    }

    public boolean isPrimary()
    {
        return mPrimary;
    }

    public boolean isRemovable()
    {
        return mRemovable;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder("StorageVolume: ")).append(mDescription);
        if(mFsUuid != null)
            stringbuilder.append(" (").append(mFsUuid).append(")");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeString(mId);
        parcel.writeInt(mStorageId);
        parcel.writeString(mPath.toString());
        parcel.writeString(mDescription);
        int j;
        if(mPrimary)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(mRemovable)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        if(mEmulated)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeLong(mMtpReserveSize);
        if(mAllowMassStorage)
            j = ((flag) ? 1 : 0);
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeLong(mMaxFileSize);
        parcel.writeParcelable(mOwner, i);
        parcel.writeString(mFsUuid);
        parcel.writeString(mState);
    }

    private static final String ACTION_OPEN_EXTERNAL_DIRECTORY = "android.os.storage.action.OPEN_EXTERNAL_DIRECTORY";
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public StorageVolume createFromParcel(Parcel parcel)
        {
            return new StorageVolume(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public StorageVolume[] newArray(int i)
        {
            return new StorageVolume[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String EXTRA_DIRECTORY_NAME = "android.os.storage.extra.DIRECTORY_NAME";
    public static final String EXTRA_STORAGE_VOLUME = "android.os.storage.extra.STORAGE_VOLUME";
    public static final int STORAGE_ID_INVALID = 0;
    public static final int STORAGE_ID_PRIMARY = 0x10001;
    private final boolean mAllowMassStorage;
    private final String mDescription;
    private final boolean mEmulated;
    private final String mFsUuid;
    private final String mId;
    private final long mMaxFileSize;
    private final long mMtpReserveSize;
    private final UserHandle mOwner;
    private final File mPath;
    private final boolean mPrimary;
    private final boolean mRemovable;
    private final String mState;
    private final int mStorageId;

}
