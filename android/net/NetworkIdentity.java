// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Slog;
import android.util.proto.ProtoOutputStream;
import java.util.Objects;

// Referenced classes of package android.net:
//            NetworkState, NetworkInfo, NetworkCapabilities, ConnectivityManager

public class NetworkIdentity
    implements Comparable
{

    public NetworkIdentity(int i, int j, String s, String s1, boolean flag, boolean flag1)
    {
        mType = i;
        mSubscriberId = s;
        mNetworkId = s1;
        mRoaming = flag;
        mMetered = flag1;
    }

    public static NetworkIdentity buildNetworkIdentity(Context context, NetworkState networkstate)
    {
        int i;
        int j;
        Object obj;
        String s;
        boolean flag;
        boolean flag1;
        i = networkstate.networkInfo.getType();
        j = networkstate.networkInfo.getSubtype();
        obj = null;
        s = null;
        flag = false;
        flag1 = networkstate.networkCapabilities.hasCapability(11);
        if(!ConnectivityManager.isNetworkTypeMobile(i)) goto _L2; else goto _L1
_L1:
        String s1;
        boolean flag2;
        if(networkstate.subscriberId == null && networkstate.networkInfo.getState() != NetworkInfo.State.DISCONNECTED && networkstate.networkInfo.getState() != NetworkInfo.State.UNKNOWN)
            Slog.w("NetworkIdentity", (new StringBuilder()).append("Active mobile network without subscriber! ni = ").append(networkstate.networkInfo).toString());
        s1 = networkstate.subscriberId;
        flag2 = networkstate.networkInfo.isRoaming();
_L4:
        return new NetworkIdentity(i, j, s1, s, flag2, flag1 ^ true);
_L2:
        s1 = obj;
        flag2 = flag;
        if(i == 1)
            if(networkstate.networkId != null)
            {
                s = networkstate.networkId;
                s1 = obj;
                flag2 = flag;
            } else
            {
                context = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo();
                if(context != null)
                {
                    s = context.getSSID();
                    s1 = obj;
                    flag2 = flag;
                } else
                {
                    s = null;
                    s1 = obj;
                    flag2 = flag;
                }
            }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static String scrubSubscriberId(String s)
    {
        if(Build.IS_ENG)
            return s;
        if(s != null)
            return (new StringBuilder()).append(s.substring(0, Math.min(6, s.length()))).append("...").toString();
        else
            return "null";
    }

    public static String[] scrubSubscriberId(String as[])
    {
        if(as == null)
            return null;
        String as1[] = new String[as.length];
        for(int i = 0; i < as1.length; i++)
            as1[i] = scrubSubscriberId(as[i]);

        return as1;
    }

    public int compareTo(NetworkIdentity networkidentity)
    {
        int i = Integer.compare(mType, networkidentity.mType);
        int j = i;
        if(i == 0)
            j = Integer.compare(mSubType, networkidentity.mSubType);
        i = j;
        if(j == 0)
        {
            i = j;
            if(mSubscriberId != null)
            {
                i = j;
                if(networkidentity.mSubscriberId != null)
                    i = mSubscriberId.compareTo(networkidentity.mSubscriberId);
            }
        }
        j = i;
        if(i == 0)
        {
            j = i;
            if(mNetworkId != null)
            {
                j = i;
                if(networkidentity.mNetworkId != null)
                    j = mNetworkId.compareTo(networkidentity.mNetworkId);
            }
        }
        i = j;
        if(j == 0)
            i = Boolean.compare(mRoaming, networkidentity.mRoaming);
        j = i;
        if(i == 0)
            j = Boolean.compare(mMetered, networkidentity.mMetered);
        return j;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((NetworkIdentity)obj);
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj instanceof NetworkIdentity)
        {
            obj = (NetworkIdentity)obj;
            boolean flag1 = flag;
            if(mType == ((NetworkIdentity) (obj)).mType)
            {
                flag1 = flag;
                if(mSubType == ((NetworkIdentity) (obj)).mSubType)
                {
                    flag1 = flag;
                    if(mRoaming == ((NetworkIdentity) (obj)).mRoaming)
                    {
                        flag1 = flag;
                        if(Objects.equals(mSubscriberId, ((NetworkIdentity) (obj)).mSubscriberId))
                        {
                            flag1 = flag;
                            if(Objects.equals(mNetworkId, ((NetworkIdentity) (obj)).mNetworkId))
                            {
                                flag1 = flag;
                                if(mMetered == ((NetworkIdentity) (obj)).mMetered)
                                    flag1 = true;
                            }
                        }
                    }
                }
            }
            return flag1;
        } else
        {
            return false;
        }
    }

    public boolean getMetered()
    {
        return mMetered;
    }

    public String getNetworkId()
    {
        return mNetworkId;
    }

    public boolean getRoaming()
    {
        return mRoaming;
    }

    public int getSubType()
    {
        return mSubType;
    }

    public String getSubscriberId()
    {
        return mSubscriberId;
    }

    public int getType()
    {
        return mType;
    }

    public int hashCode()
    {
        return Objects.hash(new Object[] {
            Integer.valueOf(mType), Integer.valueOf(mSubType), mSubscriberId, mNetworkId, Boolean.valueOf(mRoaming), Boolean.valueOf(mMetered)
        });
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("{");
        stringbuilder.append("type=").append(ConnectivityManager.getNetworkTypeName(mType));
        stringbuilder.append(", subType=");
        stringbuilder.append("COMBINED");
        if(mSubscriberId != null)
            stringbuilder.append(", subscriberId=").append(scrubSubscriberId(mSubscriberId));
        if(mNetworkId != null)
            stringbuilder.append(", networkId=").append(mNetworkId);
        if(mRoaming)
            stringbuilder.append(", ROAMING");
        stringbuilder.append(", metered=").append(mMetered);
        return stringbuilder.append("}").toString();
    }

    public void writeToProto(ProtoOutputStream protooutputstream, long l)
    {
        l = protooutputstream.start(l);
        protooutputstream.write(0x10300000001L, mType);
        if(mSubscriberId != null)
            protooutputstream.write(0x10e00000002L, scrubSubscriberId(mSubscriberId));
        protooutputstream.write(0x10e00000003L, mNetworkId);
        protooutputstream.write(0x10d00000004L, mRoaming);
        protooutputstream.write(0x10d00000005L, mMetered);
        protooutputstream.end(l);
    }

    public static final boolean COMBINE_SUBTYPE_ENABLED = true;
    public static final int SUBTYPE_COMBINED = -1;
    private static final String TAG = "NetworkIdentity";
    final boolean mMetered;
    final String mNetworkId;
    final boolean mRoaming;
    final int mSubType = -1;
    final String mSubscriberId;
    final int mType;
}
