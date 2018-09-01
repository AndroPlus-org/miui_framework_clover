// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package miui.mqsas.sdk.event;

import android.os.Parcel;
import android.os.Parcelable;

public class KillProcessEvent
    implements Parcelable
{

    public KillProcessEvent()
    {
        policy = "";
        killedReason = "";
        killedProc = "";
        procState = -1;
        procAdj = 0x7fffffff;
        killedTime = -1L;
        isInterestingToUser = false;
    }

    private KillProcessEvent(Parcel parcel)
    {
        boolean flag = true;
        super();
        policy = parcel.readString();
        killedReason = parcel.readString();
        killedProc = parcel.readString();
        procState = parcel.readInt();
        procAdj = parcel.readInt();
        killedTime = parcel.readLong();
        if(parcel.readInt() != 1)
            flag = false;
        isInterestingToUser = flag;
    }

    KillProcessEvent(Parcel parcel, KillProcessEvent killprocessevent)
    {
        this(parcel);
    }

    public int describeContents()
    {
        return 0;
    }

    public String getKilledProc()
    {
        return killedProc;
    }

    public String getKilledReason()
    {
        return killedReason;
    }

    public long getKilledTime()
    {
        return killedTime;
    }

    public String getPolicy()
    {
        return policy;
    }

    public int getProcAdj()
    {
        return procAdj;
    }

    public int getProcState()
    {
        return procState;
    }

    public boolean isInterestingToUser()
    {
        return isInterestingToUser;
    }

    public void setInterestingToUser(boolean flag)
    {
        isInterestingToUser = flag;
    }

    public void setKilledProc(String s)
    {
        killedProc = s;
    }

    public void setKilledReason(String s)
    {
        killedReason = s;
    }

    public void setKilledTime(long l)
    {
        killedTime = l;
    }

    public void setPolicy(String s)
    {
        policy = s;
    }

    public void setProcAdj(int i)
    {
        procAdj = i;
    }

    public void setProcState(int i)
    {
        procState = i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("KillProcessEvent { policy=").append(policy).append(" reason=").append(killedReason).append(" killedProcess=").append(killedProc).append(" processState=").append(procState).append(" processAdj=").append(procAdj).append(" killedTime=").append(killedTime).append(" isInterestingToUser=").append(isInterestingToUser).append("}");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(policy);
        parcel.writeString(killedReason);
        parcel.writeString(killedProc);
        parcel.writeInt(procState);
        parcel.writeInt(procAdj);
        parcel.writeLong(killedTime);
        if(isInterestingToUser)
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

        public KillProcessEvent createFromParcel(Parcel parcel)
        {
            return new KillProcessEvent(parcel, null);
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

        public KillProcessEvent[] newArray(int i)
        {
            return new KillProcessEvent[i];
        }

    }
;
    public static final String POLICY_EXCEPTION = "exception";
    public static final String POLICY_KILL_SELF = "killself";
    public static final String POLICY_LMK = "lowmemorykiller";
    public static final String POLICY_OTHER = "other";
    public static final String POLICY_POWERKEEPER = "powerkeeper";
    public static final String POLICY_SECURITY = "securitycenter";
    public static final String POLICY_SYSTEM = "system";
    public static final String POLICY_SYSTEMUI = "systemui";
    public static final String POLICY_UNUSE = "userunused";
    public static final String POLICY_WHETSTONE = "whetstone";
    public static final int PROCESS_STATE_NONEXISTENT = -1;
    public static final int UNKNOWN_ADJ = 0x7fffffff;
    private boolean isInterestingToUser;
    private String killedProc;
    private String killedReason;
    private long killedTime;
    private String policy;
    private int procAdj;
    private int procState;

}
