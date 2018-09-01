// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.metrics;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.android.internal.util.MessageUtils;

public final class DhcpErrorEvent
    implements Parcelable
{
    static final class Decoder
    {

        static final SparseArray constants = MessageUtils.findMessageNames(new Class[] {
            android/net/metrics/DhcpErrorEvent
        }, new String[] {
            "L2_", "L3_", "L4_", "BOOTP_", "DHCP_", "BUFFER_", "RECEIVE_", "PARSING_"
        });


        Decoder()
        {
        }
    }


    public DhcpErrorEvent(int i)
    {
        errorCode = i;
    }

    private DhcpErrorEvent(Parcel parcel)
    {
        errorCode = parcel.readInt();
    }

    DhcpErrorEvent(Parcel parcel, DhcpErrorEvent dhcperrorevent)
    {
        this(parcel);
    }

    public static int errorCodeWithOption(int i, int j)
    {
        return 0xffff0000 & i | j & 0xff;
    }

    private static int makeErrorCode(int i, int j)
    {
        return i << 24 | (j & 0xff) << 16;
    }

    public int describeContents()
    {
        return 0;
    }

    public String toString()
    {
        return String.format("DhcpErrorEvent(%s)", new Object[] {
            Decoder.constants.get(errorCode)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(errorCode);
    }

    public static final int BOOTP_TOO_SHORT = makeErrorCode(4, 1);
    public static final int BUFFER_UNDERFLOW = makeErrorCode(5, 1);
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public DhcpErrorEvent createFromParcel(Parcel parcel)
        {
            return new DhcpErrorEvent(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public DhcpErrorEvent[] newArray(int i)
        {
            return new DhcpErrorEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int DHCP_BAD_MAGIC_COOKIE = makeErrorCode(4, 2);
    public static final int DHCP_ERROR = 4;
    public static final int DHCP_INVALID_OPTION_LENGTH = makeErrorCode(4, 3);
    public static final int DHCP_NO_COOKIE = makeErrorCode(4, 6);
    public static final int DHCP_NO_MSG_TYPE = makeErrorCode(4, 4);
    public static final int DHCP_UNKNOWN_MSG_TYPE = makeErrorCode(4, 5);
    public static final int L2_ERROR = 1;
    public static final int L2_TOO_SHORT = makeErrorCode(1, 1);
    public static final int L2_WRONG_ETH_TYPE = makeErrorCode(1, 2);
    public static final int L3_ERROR = 2;
    public static final int L3_INVALID_IP = makeErrorCode(2, 3);
    public static final int L3_NOT_IPV4 = makeErrorCode(2, 2);
    public static final int L3_TOO_SHORT = makeErrorCode(2, 1);
    public static final int L4_ERROR = 3;
    public static final int L4_NOT_UDP = makeErrorCode(3, 1);
    public static final int L4_WRONG_PORT = makeErrorCode(3, 2);
    public static final int MISC_ERROR = 5;
    public static final int PARSING_ERROR = makeErrorCode(5, 3);
    public static final int RECEIVE_ERROR = makeErrorCode(5, 2);
    public final int errorCode;

}
