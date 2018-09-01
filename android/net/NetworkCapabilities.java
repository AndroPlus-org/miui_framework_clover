// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.BitUtils;
import com.android.internal.util.Preconditions;
import java.util.Objects;
import java.util.StringJoiner;

// Referenced classes of package android.net:
//            NetworkSpecifier, MatchAllNetworkSpecifier

public final class NetworkCapabilities
    implements Parcelable
{

    static int _2D_set0(NetworkCapabilities networkcapabilities, int i)
    {
        networkcapabilities.mLinkDownBandwidthKbps = i;
        return i;
    }

    static int _2D_set1(NetworkCapabilities networkcapabilities, int i)
    {
        networkcapabilities.mLinkUpBandwidthKbps = i;
        return i;
    }

    static long _2D_set2(NetworkCapabilities networkcapabilities, long l)
    {
        networkcapabilities.mNetworkCapabilities = l;
        return l;
    }

    static NetworkSpecifier _2D_set3(NetworkCapabilities networkcapabilities, NetworkSpecifier networkspecifier)
    {
        networkcapabilities.mNetworkSpecifier = networkspecifier;
        return networkspecifier;
    }

    static int _2D_set4(NetworkCapabilities networkcapabilities, int i)
    {
        networkcapabilities.mSignalStrength = i;
        return i;
    }

    static long _2D_set5(NetworkCapabilities networkcapabilities, long l)
    {
        networkcapabilities.mTransportTypes = l;
        return l;
    }

    public NetworkCapabilities()
    {
        mNetworkSpecifier = null;
        clearAll();
        mNetworkCapabilities = 57344L;
    }

    public NetworkCapabilities(NetworkCapabilities networkcapabilities)
    {
        mNetworkSpecifier = null;
        if(networkcapabilities != null)
        {
            mNetworkCapabilities = networkcapabilities.mNetworkCapabilities;
            mTransportTypes = networkcapabilities.mTransportTypes;
            mLinkUpBandwidthKbps = networkcapabilities.mLinkUpBandwidthKbps;
            mLinkDownBandwidthKbps = networkcapabilities.mLinkDownBandwidthKbps;
            mNetworkSpecifier = networkcapabilities.mNetworkSpecifier;
            mSignalStrength = networkcapabilities.mSignalStrength;
        }
    }

    public static String capabilityNameOf(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 0: // '\0'
            return "MMS";

        case 1: // '\001'
            return "SUPL";

        case 2: // '\002'
            return "DUN";

        case 3: // '\003'
            return "FOTA";

        case 4: // '\004'
            return "IMS";

        case 5: // '\005'
            return "CBS";

        case 6: // '\006'
            return "WIFI_P2P";

        case 7: // '\007'
            return "IA";

        case 8: // '\b'
            return "RCS";

        case 9: // '\t'
            return "XCAP";

        case 10: // '\n'
            return "EIMS";

        case 11: // '\013'
            return "NOT_METERED";

        case 12: // '\f'
            return "INTERNET";

        case 13: // '\r'
            return "NOT_RESTRICTED";

        case 14: // '\016'
            return "TRUSTED";

        case 15: // '\017'
            return "NOT_VPN";

        case 16: // '\020'
            return "VALIDATED";

        case 17: // '\021'
            return "CAPTIVE_PORTAL";

        case 18: // '\022'
            return "FOREGROUND";
        }
    }

    public static String capabilityNamesOf(int ai[])
    {
        StringJoiner stringjoiner = new StringJoiner("|");
        if(ai != null)
        {
            int i = 0;
            for(int j = ai.length; i < j; i++)
                stringjoiner.add(capabilityNameOf(ai[i]));

        }
        return stringjoiner.toString();
    }

    private static void checkValidTransportType(int i)
    {
        Preconditions.checkArgument(isValidTransport(i), (new StringBuilder()).append("Invalid TransportType ").append(i).toString());
    }

    private void combineLinkBandwidths(NetworkCapabilities networkcapabilities)
    {
        mLinkUpBandwidthKbps = Math.max(mLinkUpBandwidthKbps, networkcapabilities.mLinkUpBandwidthKbps);
        mLinkDownBandwidthKbps = Math.max(mLinkDownBandwidthKbps, networkcapabilities.mLinkDownBandwidthKbps);
    }

    private void combineNetCapabilities(NetworkCapabilities networkcapabilities)
    {
        mNetworkCapabilities = mNetworkCapabilities | networkcapabilities.mNetworkCapabilities;
    }

    private void combineSignalStrength(NetworkCapabilities networkcapabilities)
    {
        mSignalStrength = Math.max(mSignalStrength, networkcapabilities.mSignalStrength);
    }

    private void combineSpecifiers(NetworkCapabilities networkcapabilities)
    {
        if(mNetworkSpecifier != null && mNetworkSpecifier.equals(networkcapabilities.mNetworkSpecifier) ^ true)
        {
            throw new IllegalStateException("Can't combine two networkSpecifiers");
        } else
        {
            setNetworkSpecifier(networkcapabilities.mNetworkSpecifier);
            return;
        }
    }

    private void combineTransportTypes(NetworkCapabilities networkcapabilities)
    {
        mTransportTypes = mTransportTypes | networkcapabilities.mTransportTypes;
    }

    private boolean equalsLinkBandwidths(NetworkCapabilities networkcapabilities)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mLinkUpBandwidthKbps == networkcapabilities.mLinkUpBandwidthKbps)
        {
            flag1 = flag;
            if(mLinkDownBandwidthKbps == networkcapabilities.mLinkDownBandwidthKbps)
                flag1 = true;
        }
        return flag1;
    }

    private boolean equalsNetCapabilitiesRequestable(NetworkCapabilities networkcapabilities)
    {
        boolean flag;
        if((mNetworkCapabilities & 0xfffffffffff8ffffL) == (networkcapabilities.mNetworkCapabilities & 0xfffffffffff8ffffL))
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean equalsSignalStrength(NetworkCapabilities networkcapabilities)
    {
        boolean flag;
        if(mSignalStrength == networkcapabilities.mSignalStrength)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean equalsSpecifier(NetworkCapabilities networkcapabilities)
    {
        return Objects.equals(mNetworkSpecifier, networkcapabilities.mNetworkSpecifier);
    }

    public static boolean isValidTransport(int i)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(i >= 0)
        {
            flag1 = flag;
            if(i <= 6)
                flag1 = true;
        }
        return flag1;
    }

    private boolean satisfiedByLinkBandwidths(NetworkCapabilities networkcapabilities)
    {
        boolean flag = false;
        boolean flag1 = flag;
        if(mLinkUpBandwidthKbps <= networkcapabilities.mLinkUpBandwidthKbps)
        {
            flag1 = flag;
            if(mLinkDownBandwidthKbps <= networkcapabilities.mLinkDownBandwidthKbps)
                flag1 = true;
        }
        return flag1;
    }

    private boolean satisfiedByNetCapabilities(NetworkCapabilities networkcapabilities, boolean flag)
    {
        long l = mNetworkCapabilities;
        long l1 = l;
        if(flag)
            l1 = l & 0xfffffffffff8bfffL;
        if((networkcapabilities.mNetworkCapabilities & l1) == l1)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean satisfiedByNetworkCapabilities(NetworkCapabilities networkcapabilities, boolean flag)
    {
        if(networkcapabilities != null && satisfiedByNetCapabilities(networkcapabilities, flag) && satisfiedByTransportTypes(networkcapabilities) && (flag || satisfiedByLinkBandwidths(networkcapabilities)) && satisfiedBySpecifier(networkcapabilities))
        {
            if(!flag)
                flag = satisfiedBySignalStrength(networkcapabilities);
            else
                flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }

    private boolean satisfiedBySignalStrength(NetworkCapabilities networkcapabilities)
    {
        boolean flag;
        if(mSignalStrength <= networkcapabilities.mSignalStrength)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private boolean satisfiedBySpecifier(NetworkCapabilities networkcapabilities)
    {
        boolean flag;
        if(mNetworkSpecifier != null && !mNetworkSpecifier.satisfiedBy(networkcapabilities.mNetworkSpecifier))
            flag = networkcapabilities.mNetworkSpecifier instanceof MatchAllNetworkSpecifier;
        else
            flag = true;
        return flag;
    }

    private boolean satisfiedByTransportTypes(NetworkCapabilities networkcapabilities)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(mTransportTypes != 0L)
            if((mTransportTypes & networkcapabilities.mTransportTypes) != 0L)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static String transportNameOf(int i)
    {
        if(!isValidTransport(i))
            return "UNKNOWN";
        else
            return TRANSPORT_NAMES[i];
    }

    public static String transportNamesOf(int ai[])
    {
        StringJoiner stringjoiner = new StringJoiner("|");
        if(ai != null)
        {
            int i = 0;
            for(int j = ai.length; i < j; i++)
                stringjoiner.add(transportNameOf(ai[i]));

        }
        return stringjoiner.toString();
    }

    public NetworkCapabilities addCapability(int i)
    {
        if(i < 0 || i > 18)
        {
            throw new IllegalArgumentException("NetworkCapability out of range");
        } else
        {
            mNetworkCapabilities = mNetworkCapabilities | (long)(1 << i);
            return this;
        }
    }

    public NetworkCapabilities addTransportType(int i)
    {
        checkValidTransportType(i);
        mTransportTypes = mTransportTypes | (long)(1 << i);
        setNetworkSpecifier(mNetworkSpecifier);
        return this;
    }

    public void clearAll()
    {
        mTransportTypes = 0L;
        mNetworkCapabilities = 0L;
        mLinkDownBandwidthKbps = 0;
        mLinkUpBandwidthKbps = 0;
        mNetworkSpecifier = null;
        mSignalStrength = 0x80000000;
    }

    public void combineCapabilities(NetworkCapabilities networkcapabilities)
    {
        combineNetCapabilities(networkcapabilities);
        combineTransportTypes(networkcapabilities);
        combineLinkBandwidths(networkcapabilities);
        combineSpecifiers(networkcapabilities);
        combineSignalStrength(networkcapabilities);
    }

    public int describeContents()
    {
        return 0;
    }

    public String describeFirstNonRequestableCapability()
    {
        if(hasCapability(16))
            return "NET_CAPABILITY_VALIDATED";
        if(hasCapability(17))
            return "NET_CAPABILITY_CAPTIVE_PORTAL";
        if(hasCapability(18))
            return "NET_CAPABILITY_FOREGROUND";
        if((mNetworkCapabilities & 0x70000L) != 0L)
            return (new StringBuilder()).append("unknown non-requestable capabilities ").append(Long.toHexString(mNetworkCapabilities)).toString();
        if(mLinkUpBandwidthKbps != 0 || mLinkDownBandwidthKbps != 0)
            return "link bandwidth";
        if(hasSignalStrength())
            return "signalStrength";
        else
            return null;
    }

    public String describeImmutableDifferences(NetworkCapabilities networkcapabilities)
    {
        if(networkcapabilities == null)
            return "other NetworkCapabilities was null";
        StringJoiner stringjoiner = new StringJoiner(", ");
        long l = mNetworkCapabilities & 0xfffffffffff8b7fbL;
        long l1 = networkcapabilities.mNetworkCapabilities & 0xfffffffffff8b7fbL;
        if(l != l1)
            stringjoiner.add(String.format("immutable capabilities changed: %s -> %s", new Object[] {
                capabilityNamesOf(BitUtils.unpackBits(l)), capabilityNamesOf(BitUtils.unpackBits(l1))
            }));
        if(!equalsSpecifier(networkcapabilities))
            stringjoiner.add(String.format("specifier changed: %s -> %s", new Object[] {
                getNetworkSpecifier(), networkcapabilities.getNetworkSpecifier()
            }));
        if(!equalsTransportTypes(networkcapabilities))
            stringjoiner.add(String.format("transports changed: %s -> %s", new Object[] {
                transportNamesOf(getTransportTypes()), transportNamesOf(networkcapabilities.getTransportTypes())
            }));
        return stringjoiner.toString();
    }

    public boolean equalRequestableCapabilities(NetworkCapabilities networkcapabilities)
    {
        boolean flag = false;
        if(networkcapabilities == null)
            return false;
        boolean flag1 = flag;
        if(equalsNetCapabilitiesRequestable(networkcapabilities))
        {
            flag1 = flag;
            if(equalsTransportTypes(networkcapabilities))
                flag1 = equalsSpecifier(networkcapabilities);
        }
        return flag1;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == null || !(obj instanceof NetworkCapabilities))
            return false;
        obj = (NetworkCapabilities)obj;
        boolean flag1 = flag;
        if(equalsNetCapabilities(((NetworkCapabilities) (obj))))
        {
            flag1 = flag;
            if(equalsTransportTypes(((NetworkCapabilities) (obj))))
            {
                flag1 = flag;
                if(equalsLinkBandwidths(((NetworkCapabilities) (obj))))
                {
                    flag1 = flag;
                    if(equalsSignalStrength(((NetworkCapabilities) (obj))))
                        flag1 = equalsSpecifier(((NetworkCapabilities) (obj)));
                }
            }
        }
        return flag1;
    }

    public boolean equalsNetCapabilities(NetworkCapabilities networkcapabilities)
    {
        boolean flag;
        if(networkcapabilities.mNetworkCapabilities == mNetworkCapabilities)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean equalsTransportTypes(NetworkCapabilities networkcapabilities)
    {
        boolean flag;
        if(networkcapabilities.mTransportTypes == mTransportTypes)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public int[] getCapabilities()
    {
        return BitUtils.unpackBits(mNetworkCapabilities);
    }

    public int getLinkDownstreamBandwidthKbps()
    {
        return mLinkDownBandwidthKbps;
    }

    public int getLinkUpstreamBandwidthKbps()
    {
        return mLinkUpBandwidthKbps;
    }

    public NetworkSpecifier getNetworkSpecifier()
    {
        return mNetworkSpecifier;
    }

    public int getSignalStrength()
    {
        return mSignalStrength;
    }

    public int[] getTransportTypes()
    {
        return BitUtils.unpackBits(mTransportTypes);
    }

    public boolean hasCapability(int i)
    {
        boolean flag = true;
        if(i < 0 || i > 18)
            return false;
        if((mNetworkCapabilities & (long)(1 << i)) == 0L)
            flag = false;
        return flag;
    }

    public boolean hasSignalStrength()
    {
        boolean flag;
        if(mSignalStrength > 0x80000000)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean hasTransport(int i)
    {
        boolean flag = true;
        if(!isValidTransport(i) || (mTransportTypes & (long)(1 << i)) == 0L)
            flag = false;
        return flag;
    }

    public int hashCode()
    {
        return (int)(mNetworkCapabilities & -1L) + (int)(mNetworkCapabilities >> 32) * 3 + (int)(mTransportTypes & -1L) * 5 + (int)(mTransportTypes >> 32) * 7 + mLinkUpBandwidthKbps * 11 + mLinkDownBandwidthKbps * 13 + Objects.hashCode(mNetworkSpecifier) * 17 + mSignalStrength * 19;
    }

    public void maybeMarkCapabilitiesRestricted()
    {
        boolean flag;
        boolean flag1;
        if((mNetworkCapabilities & 4163L) != 0L)
            flag = true;
        else
            flag = false;
        if((mNetworkCapabilities & 1980L) != 0L)
            flag1 = true;
        else
            flag1 = false;
        if(flag1 && flag ^ true)
            removeCapability(13);
    }

    public NetworkCapabilities removeCapability(int i)
    {
        if(i < 0 || i > 18)
        {
            throw new IllegalArgumentException("NetworkCapability out of range");
        } else
        {
            mNetworkCapabilities = mNetworkCapabilities & (long)(1 << i);
            return this;
        }
    }

    public NetworkCapabilities removeTransportType(int i)
    {
        checkValidTransportType(i);
        mTransportTypes = mTransportTypes & (long)(1 << i);
        setNetworkSpecifier(mNetworkSpecifier);
        return this;
    }

    public boolean satisfiedByImmutableNetworkCapabilities(NetworkCapabilities networkcapabilities)
    {
        return satisfiedByNetworkCapabilities(networkcapabilities, true);
    }

    public boolean satisfiedByNetworkCapabilities(NetworkCapabilities networkcapabilities)
    {
        return satisfiedByNetworkCapabilities(networkcapabilities, false);
    }

    public void setLinkDownstreamBandwidthKbps(int i)
    {
        mLinkDownBandwidthKbps = i;
    }

    public void setLinkUpstreamBandwidthKbps(int i)
    {
        mLinkUpBandwidthKbps = i;
    }

    public NetworkCapabilities setNetworkSpecifier(NetworkSpecifier networkspecifier)
    {
        if(networkspecifier != null && Long.bitCount(mTransportTypes) != 1)
        {
            throw new IllegalStateException("Must have a single transport specified to use setNetworkSpecifier");
        } else
        {
            mNetworkSpecifier = networkspecifier;
            return this;
        }
    }

    public void setSignalStrength(int i)
    {
        mSignalStrength = i;
    }

    public String toString()
    {
        int ai[] = getTransportTypes();
        String s;
        String s1;
        int ai1[];
        int i;
        if(ai.length > 0)
            s1 = (new StringBuilder()).append(" Transports: ").append(transportNamesOf(ai)).toString();
        else
            s1 = "";
        ai1 = getCapabilities();
        if(ai1.length > 0)
            s = " Capabilities: ";
        else
            s = "";
        i = 0;
        do
        {
            if(i >= ai1.length)
                break;
            String s3 = (new StringBuilder()).append(s).append(capabilityNameOf(ai1[i])).toString();
            int j = i + 1;
            s = s3;
            i = j;
            if(j < ai1.length)
            {
                s = (new StringBuilder()).append(s3).append("&").toString();
                i = j;
            }
        } while(true);
        String s2;
        String s4;
        String s5;
        String s6;
        if(mLinkUpBandwidthKbps > 0)
            s4 = (new StringBuilder()).append(" LinkUpBandwidth>=").append(mLinkUpBandwidthKbps).append("Kbps").toString();
        else
            s4 = "";
        if(mLinkDownBandwidthKbps > 0)
            s2 = (new StringBuilder()).append(" LinkDnBandwidth>=").append(mLinkDownBandwidthKbps).append("Kbps").toString();
        else
            s2 = "";
        if(mNetworkSpecifier == null)
            s5 = "";
        else
            s5 = (new StringBuilder()).append(" Specifier: <").append(mNetworkSpecifier).append(">").toString();
        if(hasSignalStrength())
            s6 = (new StringBuilder()).append(" SignalStrength: ").append(mSignalStrength).toString();
        else
            s6 = "";
        return (new StringBuilder()).append("[").append(s1).append(s).append(s4).append(s2).append(s5).append(s6).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeLong(mNetworkCapabilities);
        parcel.writeLong(mTransportTypes);
        parcel.writeInt(mLinkUpBandwidthKbps);
        parcel.writeInt(mLinkDownBandwidthKbps);
        parcel.writeParcelable((Parcelable)mNetworkSpecifier, i);
        parcel.writeInt(mSignalStrength);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NetworkCapabilities createFromParcel(Parcel parcel)
        {
            NetworkCapabilities networkcapabilities = new NetworkCapabilities();
            NetworkCapabilities._2D_set2(networkcapabilities, parcel.readLong());
            NetworkCapabilities._2D_set5(networkcapabilities, parcel.readLong());
            NetworkCapabilities._2D_set1(networkcapabilities, parcel.readInt());
            NetworkCapabilities._2D_set0(networkcapabilities, parcel.readInt());
            NetworkCapabilities._2D_set3(networkcapabilities, (NetworkSpecifier)parcel.readParcelable(null));
            NetworkCapabilities._2D_set4(networkcapabilities, parcel.readInt());
            return networkcapabilities;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NetworkCapabilities[] newArray(int i)
        {
            return new NetworkCapabilities[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final long DEFAULT_CAPABILITIES = 57344L;
    private static final int MAX_NET_CAPABILITY = 18;
    public static final int MAX_TRANSPORT = 6;
    private static final int MIN_NET_CAPABILITY = 0;
    public static final int MIN_TRANSPORT = 0;
    private static final long MUTABLE_CAPABILITIES = 0x74000L;
    public static final int NET_CAPABILITY_CAPTIVE_PORTAL = 17;
    public static final int NET_CAPABILITY_CBS = 5;
    public static final int NET_CAPABILITY_DUN = 2;
    public static final int NET_CAPABILITY_EIMS = 10;
    public static final int NET_CAPABILITY_FOREGROUND = 18;
    public static final int NET_CAPABILITY_FOTA = 3;
    public static final int NET_CAPABILITY_IA = 7;
    public static final int NET_CAPABILITY_IMS = 4;
    public static final int NET_CAPABILITY_INTERNET = 12;
    public static final int NET_CAPABILITY_MMS = 0;
    public static final int NET_CAPABILITY_NOT_METERED = 11;
    public static final int NET_CAPABILITY_NOT_RESTRICTED = 13;
    public static final int NET_CAPABILITY_NOT_VPN = 15;
    public static final int NET_CAPABILITY_RCS = 8;
    public static final int NET_CAPABILITY_SUPL = 1;
    public static final int NET_CAPABILITY_TRUSTED = 14;
    public static final int NET_CAPABILITY_VALIDATED = 16;
    public static final int NET_CAPABILITY_WIFI_P2P = 6;
    public static final int NET_CAPABILITY_XCAP = 9;
    private static final long NON_REQUESTABLE_CAPABILITIES = 0x70000L;
    static final long RESTRICTED_CAPABILITIES = 1980L;
    public static final int SIGNAL_STRENGTH_UNSPECIFIED = 0x80000000;
    private static final String TAG = "NetworkCapabilities";
    public static final int TRANSPORT_BLUETOOTH = 2;
    public static final int TRANSPORT_CELLULAR = 0;
    public static final int TRANSPORT_ETHERNET = 3;
    public static final int TRANSPORT_LOWPAN = 6;
    private static final String TRANSPORT_NAMES[] = {
        "CELLULAR", "WIFI", "BLUETOOTH", "ETHERNET", "VPN", "WIFI_AWARE", "LOWPAN"
    };
    public static final int TRANSPORT_VPN = 4;
    public static final int TRANSPORT_WIFI = 1;
    public static final int TRANSPORT_WIFI_AWARE = 5;
    static final long UNRESTRICTED_CAPABILITIES = 4163L;
    private int mLinkDownBandwidthKbps;
    private int mLinkUpBandwidthKbps;
    private long mNetworkCapabilities;
    private NetworkSpecifier mNetworkSpecifier;
    private int mSignalStrength;
    private long mTransportTypes;

}
