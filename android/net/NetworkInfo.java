// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.EnumMap;

// Referenced classes of package android.net:
//            ConnectivityManager

public class NetworkInfo
    implements Parcelable
{
    public static final class DetailedState extends Enum
    {

        public static DetailedState valueOf(String s)
        {
            return (DetailedState)Enum.valueOf(android/net/NetworkInfo$DetailedState, s);
        }

        public static DetailedState[] values()
        {
            return $VALUES;
        }

        private static final DetailedState $VALUES[];
        public static final DetailedState AUTHENTICATING;
        public static final DetailedState BLOCKED;
        public static final DetailedState CAPTIVE_PORTAL_CHECK;
        public static final DetailedState CONNECTED;
        public static final DetailedState CONNECTING;
        public static final DetailedState DISCONNECTED;
        public static final DetailedState DISCONNECTING;
        public static final DetailedState FAILED;
        public static final DetailedState IDLE;
        public static final DetailedState OBTAINING_IPADDR;
        public static final DetailedState SCANNING;
        public static final DetailedState SUSPENDED;
        public static final DetailedState VERIFYING_POOR_LINK;

        static 
        {
            IDLE = new DetailedState("IDLE", 0);
            SCANNING = new DetailedState("SCANNING", 1);
            CONNECTING = new DetailedState("CONNECTING", 2);
            AUTHENTICATING = new DetailedState("AUTHENTICATING", 3);
            OBTAINING_IPADDR = new DetailedState("OBTAINING_IPADDR", 4);
            CONNECTED = new DetailedState("CONNECTED", 5);
            SUSPENDED = new DetailedState("SUSPENDED", 6);
            DISCONNECTING = new DetailedState("DISCONNECTING", 7);
            DISCONNECTED = new DetailedState("DISCONNECTED", 8);
            FAILED = new DetailedState("FAILED", 9);
            BLOCKED = new DetailedState("BLOCKED", 10);
            VERIFYING_POOR_LINK = new DetailedState("VERIFYING_POOR_LINK", 11);
            CAPTIVE_PORTAL_CHECK = new DetailedState("CAPTIVE_PORTAL_CHECK", 12);
            $VALUES = (new DetailedState[] {
                IDLE, SCANNING, CONNECTING, AUTHENTICATING, OBTAINING_IPADDR, CONNECTED, SUSPENDED, DISCONNECTING, DISCONNECTED, FAILED, 
                BLOCKED, VERIFYING_POOR_LINK, CAPTIVE_PORTAL_CHECK
            });
        }

        private DetailedState(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class State extends Enum
    {

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(android/net/NetworkInfo$State, s);
        }

        public static State[] values()
        {
            return $VALUES;
        }

        private static final State $VALUES[];
        public static final State CONNECTED;
        public static final State CONNECTING;
        public static final State DISCONNECTED;
        public static final State DISCONNECTING;
        public static final State SUSPENDED;
        public static final State UNKNOWN;

        static 
        {
            CONNECTING = new State("CONNECTING", 0);
            CONNECTED = new State("CONNECTED", 1);
            SUSPENDED = new State("SUSPENDED", 2);
            DISCONNECTING = new State("DISCONNECTING", 3);
            DISCONNECTED = new State("DISCONNECTED", 4);
            UNKNOWN = new State("UNKNOWN", 5);
            $VALUES = (new State[] {
                CONNECTING, CONNECTED, SUSPENDED, DISCONNECTING, DISCONNECTED, UNKNOWN
            });
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    static DetailedState _2D_set0(NetworkInfo networkinfo, DetailedState detailedstate)
    {
        networkinfo.mDetailedState = detailedstate;
        return detailedstate;
    }

    static String _2D_set1(NetworkInfo networkinfo, String s)
    {
        networkinfo.mExtraInfo = s;
        return s;
    }

    static boolean _2D_set2(NetworkInfo networkinfo, boolean flag)
    {
        networkinfo.mIsAvailable = flag;
        return flag;
    }

    static boolean _2D_set3(NetworkInfo networkinfo, boolean flag)
    {
        networkinfo.mIsFailover = flag;
        return flag;
    }

    static boolean _2D_set4(NetworkInfo networkinfo, boolean flag)
    {
        networkinfo.mIsRoaming = flag;
        return flag;
    }

    static String _2D_set5(NetworkInfo networkinfo, String s)
    {
        networkinfo.mReason = s;
        return s;
    }

    static State _2D_set6(NetworkInfo networkinfo, State state)
    {
        networkinfo.mState = state;
        return state;
    }

    public NetworkInfo(int i, int j, String s, String s1)
    {
        if(!ConnectivityManager.isNetworkTypeValid(i) && i != -1)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid network type: ").append(i).toString());
        } else
        {
            mNetworkType = i;
            mSubtype = j;
            mTypeName = s;
            mSubtypeName = s1;
            setDetailedState(DetailedState.IDLE, null, null);
            mState = State.UNKNOWN;
            return;
        }
    }

    public NetworkInfo(NetworkInfo networkinfo)
    {
        if(networkinfo == null) goto _L2; else goto _L1
_L1:
        networkinfo;
        JVM INSTR monitorenter ;
        mNetworkType = networkinfo.mNetworkType;
        mSubtype = networkinfo.mSubtype;
        mTypeName = networkinfo.mTypeName;
        mSubtypeName = networkinfo.mSubtypeName;
        mState = networkinfo.mState;
        mDetailedState = networkinfo.mDetailedState;
        mReason = networkinfo.mReason;
        mExtraInfo = networkinfo.mExtraInfo;
        mIsFailover = networkinfo.mIsFailover;
        mIsAvailable = networkinfo.mIsAvailable;
        mIsRoaming = networkinfo.mIsRoaming;
        networkinfo;
        JVM INSTR monitorexit ;
_L2:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public int describeContents()
    {
        return 0;
    }

    public DetailedState getDetailedState()
    {
        this;
        JVM INSTR monitorenter ;
        DetailedState detailedstate = mDetailedState;
        this;
        JVM INSTR monitorexit ;
        return detailedstate;
        Exception exception;
        exception;
        throw exception;
    }

    public String getExtraInfo()
    {
        this;
        JVM INSTR monitorenter ;
        String s = mExtraInfo;
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public String getReason()
    {
        this;
        JVM INSTR monitorenter ;
        String s = mReason;
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public State getState()
    {
        this;
        JVM INSTR monitorenter ;
        State state = mState;
        this;
        JVM INSTR monitorexit ;
        return state;
        Exception exception;
        exception;
        throw exception;
    }

    public int getSubtype()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mSubtype;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public String getSubtypeName()
    {
        this;
        JVM INSTR monitorenter ;
        String s = mSubtypeName;
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public int getType()
    {
        this;
        JVM INSTR monitorenter ;
        int i = mNetworkType;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public String getTypeName()
    {
        this;
        JVM INSTR monitorenter ;
        String s = mTypeName;
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isAvailable()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIsAvailable;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isConnected()
    {
        this;
        JVM INSTR monitorenter ;
        State state;
        State state1;
        state = mState;
        state1 = State.CONNECTED;
        boolean flag;
        if(state == state1)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isConnectedOrConnecting()
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        boolean flag1 = flag;
        State state;
        State state1;
        if(mState == State.CONNECTED)
            break MISSING_BLOCK_LABEL_34;
        state = mState;
        state1 = State.CONNECTING;
        if(state == state1)
            flag1 = flag;
        else
            flag1 = false;
        this;
        JVM INSTR monitorexit ;
        return flag1;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isFailover()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIsFailover;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isRoaming()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = mIsRoaming;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public void setDetailedState(DetailedState detailedstate, String s, String s1)
    {
        this;
        JVM INSTR monitorenter ;
        mDetailedState = detailedstate;
        mState = (State)stateMap.get(detailedstate);
        mReason = s;
        mExtraInfo = s1;
        this;
        JVM INSTR monitorexit ;
        return;
        detailedstate;
        throw detailedstate;
    }

    public void setExtraInfo(String s)
    {
        this;
        JVM INSTR monitorenter ;
        mExtraInfo = s;
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void setFailover(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mIsFailover = flag;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setIsAvailable(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mIsAvailable = flag;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setRoaming(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        mIsRoaming = flag;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setSubtype(int i, String s)
    {
        this;
        JVM INSTR monitorenter ;
        mSubtype = i;
        mSubtypeName = s;
        this;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s;
    }

    public void setType(int i)
    {
        this;
        JVM INSTR monitorenter ;
        mNetworkType = i;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public String toString()
    {
        this;
        JVM INSTR monitorenter ;
        StringBuilder stringbuilder;
        StringBuilder stringbuilder1;
        stringbuilder = JVM INSTR new #141 <Class StringBuilder>;
        stringbuilder.StringBuilder("[");
        stringbuilder1 = stringbuilder.append("type: ").append(getTypeName()).append("[").append(getSubtypeName()).append("], state: ").append(mState).append("/").append(mDetailedState).append(", reason: ");
        if(mReason != null) goto _L2; else goto _L1
_L1:
        String s = "(unspecified)";
_L7:
        stringbuilder1 = stringbuilder1.append(s).append(", extra: ");
        if(mExtraInfo != null) goto _L4; else goto _L3
_L3:
        s = "(none)";
_L5:
        stringbuilder1.append(s).append(", failover: ").append(mIsFailover).append(", available: ").append(mIsAvailable).append(", roaming: ").append(mIsRoaming).append("]");
        s = stringbuilder.toString();
        this;
        JVM INSTR monitorexit ;
        return s;
_L2:
        s = mReason;
        continue; /* Loop/switch isn't completed */
_L4:
        s = mExtraInfo;
          goto _L5
        Exception exception;
        exception;
        throw exception;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        parcel.writeInt(mNetworkType);
        parcel.writeInt(mSubtype);
        parcel.writeString(mTypeName);
        parcel.writeString(mSubtypeName);
        if(mState == null) goto _L2; else goto _L1
_L1:
        String s = mState.name();
_L5:
        parcel.writeString(s);
        if(mDetailedState == null) goto _L4; else goto _L3
_L3:
        s = mDetailedState.name();
_L6:
        parcel.writeString(s);
        if(mIsFailover)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIsAvailable)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mIsRoaming)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        parcel.writeString(mReason);
        parcel.writeString(mExtraInfo);
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        s = State.UNKNOWN.name();
          goto _L5
_L4:
        s = DetailedState.IDLE.name();
          goto _L6
        parcel;
        throw parcel;
          goto _L5
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkInfo createFromParcel(Parcel parcel)
        {
            boolean flag = true;
            NetworkInfo networkinfo = new NetworkInfo(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString());
            NetworkInfo._2D_set6(networkinfo, State.valueOf(parcel.readString()));
            NetworkInfo._2D_set0(networkinfo, DetailedState.valueOf(parcel.readString()));
            boolean flag1;
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            NetworkInfo._2D_set3(networkinfo, flag1);
            if(parcel.readInt() != 0)
                flag1 = true;
            else
                flag1 = false;
            NetworkInfo._2D_set2(networkinfo, flag1);
            if(parcel.readInt() != 0)
                flag1 = flag;
            else
                flag1 = false;
            NetworkInfo._2D_set4(networkinfo, flag1);
            NetworkInfo._2D_set5(networkinfo, parcel.readString());
            NetworkInfo._2D_set1(networkinfo, parcel.readString());
            return networkinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkInfo[] newArray(int i)
        {
            return new NetworkInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final EnumMap stateMap;
    private DetailedState mDetailedState;
    private String mExtraInfo;
    private boolean mIsAvailable;
    private boolean mIsFailover;
    private boolean mIsRoaming;
    private int mNetworkType;
    private String mReason;
    private State mState;
    private int mSubtype;
    private String mSubtypeName;
    private String mTypeName;

    static 
    {
        stateMap = new EnumMap(android/net/NetworkInfo$DetailedState);
        stateMap.put(DetailedState.IDLE, State.DISCONNECTED);
        stateMap.put(DetailedState.SCANNING, State.DISCONNECTED);
        stateMap.put(DetailedState.CONNECTING, State.CONNECTING);
        stateMap.put(DetailedState.AUTHENTICATING, State.CONNECTING);
        stateMap.put(DetailedState.OBTAINING_IPADDR, State.CONNECTING);
        stateMap.put(DetailedState.VERIFYING_POOR_LINK, State.CONNECTING);
        stateMap.put(DetailedState.CAPTIVE_PORTAL_CHECK, State.CONNECTING);
        stateMap.put(DetailedState.CONNECTED, State.CONNECTED);
        stateMap.put(DetailedState.SUSPENDED, State.SUSPENDED);
        stateMap.put(DetailedState.DISCONNECTING, State.DISCONNECTING);
        stateMap.put(DetailedState.DISCONNECTED, State.DISCONNECTED);
        stateMap.put(DetailedState.FAILED, State.DISCONNECTED);
        stateMap.put(DetailedState.BLOCKED, State.DISCONNECTED);
    }
}
