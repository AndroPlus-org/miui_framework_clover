// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.timezone;

import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package android.app.timezone:
//            Utils, DistroFormatVersion, DistroRulesVersion

public final class RulesState
    implements Parcelable
{

    static RulesState _2D_wrap0(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public RulesState(String s, DistroFormatVersion distroformatversion, boolean flag, int i, DistroRulesVersion distrorulesversion, int j, DistroRulesVersion distrorulesversion1)
    {
        boolean flag1 = true;
        super();
        mSystemRulesVersion = Utils.validateRulesVersion("systemRulesVersion", s);
        mDistroFormatVersionSupported = (DistroFormatVersion)Utils.validateNotNull("distroFormatVersionSupported", distroformatversion);
        mOperationInProgress = flag;
        if(flag && i != 0)
            throw new IllegalArgumentException("stagedOperationType != STAGED_OPERATION_UNKNOWN");
        mStagedOperationType = validateStagedOperation(i);
        boolean flag2;
        if(mStagedOperationType == 3)
            flag2 = true;
        else
            flag2 = false;
        mStagedDistroRulesVersion = (DistroRulesVersion)Utils.validateConditionalNull(flag2, "stagedDistroRulesVersion", distrorulesversion);
        if(flag && j != 0)
            throw new IllegalArgumentException("distroInstalled != DISTRO_STATUS_UNKNOWN");
        mDistroStatus = validateDistroStatus(j);
        if(mDistroStatus == 2)
            flag = flag1;
        else
            flag = false;
        mInstalledDistroRulesVersion = (DistroRulesVersion)Utils.validateConditionalNull(flag, "installedDistroRulesVersion", distrorulesversion1);
    }

    private static RulesState createFromParcel(Parcel parcel)
    {
        String s = parcel.readString();
        DistroFormatVersion distroformatversion = (DistroFormatVersion)parcel.readParcelable(null);
        boolean flag;
        if(parcel.readByte() == 1)
            flag = true;
        else
            flag = false;
        return new RulesState(s, distroformatversion, flag, parcel.readByte(), (DistroRulesVersion)parcel.readParcelable(null), parcel.readByte(), (DistroRulesVersion)parcel.readParcelable(null));
    }

    private static int validateDistroStatus(int i)
    {
        if(i < 0 || i > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown distro status=").append(i).toString());
        else
            return i;
    }

    private static int validateStagedOperation(int i)
    {
        if(i < 0 || i > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown operation type=").append(i).toString());
        else
            return i;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = true;
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        obj = (RulesState)obj;
        if(mOperationInProgress != ((RulesState) (obj)).mOperationInProgress)
            return false;
        if(mStagedOperationType != ((RulesState) (obj)).mStagedOperationType)
            return false;
        if(mDistroStatus != ((RulesState) (obj)).mDistroStatus)
            return false;
        if(!mSystemRulesVersion.equals(((RulesState) (obj)).mSystemRulesVersion))
            return false;
        if(!mDistroFormatVersionSupported.equals(((RulesState) (obj)).mDistroFormatVersionSupported))
            return false;
        if(mStagedDistroRulesVersion == null ? ((RulesState) (obj)).mStagedDistroRulesVersion != null : mStagedDistroRulesVersion.equals(((RulesState) (obj)).mStagedDistroRulesVersion) ^ true)
            return false;
        if(mInstalledDistroRulesVersion == null) goto _L2; else goto _L1
_L1:
        flag = mInstalledDistroRulesVersion.equals(((RulesState) (obj)).mInstalledDistroRulesVersion);
_L4:
        return flag;
_L2:
        if(((RulesState) (obj)).mInstalledDistroRulesVersion != null)
            flag = false;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int getDistroStatus()
    {
        return mDistroStatus;
    }

    public DistroRulesVersion getInstalledDistroRulesVersion()
    {
        return mInstalledDistroRulesVersion;
    }

    public DistroRulesVersion getStagedDistroRulesVersion()
    {
        return mStagedDistroRulesVersion;
    }

    public int getStagedOperationType()
    {
        return mStagedOperationType;
    }

    public String getSystemRulesVersion()
    {
        return mSystemRulesVersion;
    }

    public int hashCode()
    {
        int i = 0;
        int j = mSystemRulesVersion.hashCode();
        int k = mDistroFormatVersionSupported.hashCode();
        int l;
        int i1;
        int j1;
        int k1;
        if(mOperationInProgress)
            l = 1;
        else
            l = 0;
        i1 = mStagedOperationType;
        if(mStagedDistroRulesVersion != null)
            j1 = mStagedDistroRulesVersion.hashCode();
        else
            j1 = 0;
        k1 = mDistroStatus;
        if(mInstalledDistroRulesVersion != null)
            i = mInstalledDistroRulesVersion.hashCode();
        return (((((j * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + i;
    }

    public boolean isDistroFormatVersionSupported(DistroFormatVersion distroformatversion)
    {
        return mDistroFormatVersionSupported.supports(distroformatversion);
    }

    public boolean isOperationInProgress()
    {
        return mOperationInProgress;
    }

    public boolean isSystemVersionNewerThan(DistroRulesVersion distrorulesversion)
    {
        boolean flag = false;
        if(mSystemRulesVersion.compareTo(distrorulesversion.getRulesVersion()) > 0)
            flag = true;
        return flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("RulesState{mSystemRulesVersion='").append(mSystemRulesVersion).append('\'').append(", mDistroFormatVersionSupported=").append(mDistroFormatVersionSupported).append(", mOperationInProgress=").append(mOperationInProgress).append(", mStagedOperationType=").append(mStagedOperationType).append(", mStagedDistroRulesVersion=").append(mStagedDistroRulesVersion).append(", mDistroStatus=").append(mDistroStatus).append(", mInstalledDistroRulesVersion=").append(mInstalledDistroRulesVersion).append('}').toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mSystemRulesVersion);
        parcel.writeParcelable(mDistroFormatVersionSupported, 0);
        int j;
        if(mOperationInProgress)
        {
            i = 1;
            j = i;
        } else
        {
            i = 0;
            j = i;
        }
        parcel.writeByte(j);
        parcel.writeByte((byte)mStagedOperationType);
        parcel.writeParcelable(mStagedDistroRulesVersion, 0);
        parcel.writeByte((byte)mDistroStatus);
        parcel.writeParcelable(mInstalledDistroRulesVersion, 0);
    }

    private static final byte BYTE_FALSE = 0;
    private static final byte BYTE_TRUE = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public RulesState createFromParcel(Parcel parcel)
        {
            return RulesState._2D_wrap0(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public RulesState[] newArray(int i)
        {
            return new RulesState[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DISTRO_STATUS_INSTALLED = 2;
    public static final int DISTRO_STATUS_NONE = 1;
    public static final int DISTRO_STATUS_UNKNOWN = 0;
    public static final int STAGED_OPERATION_INSTALL = 3;
    public static final int STAGED_OPERATION_NONE = 1;
    public static final int STAGED_OPERATION_UNINSTALL = 2;
    public static final int STAGED_OPERATION_UNKNOWN = 0;
    private final DistroFormatVersion mDistroFormatVersionSupported;
    private final int mDistroStatus;
    private final DistroRulesVersion mInstalledDistroRulesVersion;
    private final boolean mOperationInProgress;
    private final DistroRulesVersion mStagedDistroRulesVersion;
    private final int mStagedOperationType;
    private final String mSystemRulesVersion;

}
