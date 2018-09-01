// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p.nsd;

import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.*;
import java.util.*;

// Referenced classes of package android.net.wifi.p2p.nsd:
//            WifiP2pDnsSdServiceResponse, WifiP2pUpnpServiceResponse

public class WifiP2pServiceResponse
    implements Parcelable
{
    public static class Status
    {

        public static String toString(int i)
        {
            switch(i)
            {
            default:
                return "UNKNOWN";

            case 0: // '\0'
                return "SUCCESS";

            case 1: // '\001'
                return "SERVICE_PROTOCOL_NOT_AVAILABLE";

            case 2: // '\002'
                return "REQUESTED_INFORMATION_NOT_AVAILABLE";

            case 3: // '\003'
                return "BAD_REQUEST";
            }
        }

        public static final int BAD_REQUEST = 3;
        public static final int REQUESTED_INFORMATION_NOT_AVAILABLE = 2;
        public static final int SERVICE_PROTOCOL_NOT_AVAILABLE = 1;
        public static final int SUCCESS = 0;

        private Status()
        {
        }
    }


    protected WifiP2pServiceResponse(int i, int j, int k, WifiP2pDevice wifip2pdevice, byte abyte0[])
    {
        mServiceType = i;
        mStatus = j;
        mTransId = k;
        mDevice = wifip2pdevice;
        mData = abyte0;
    }

    private boolean equals(Object obj, Object obj1)
    {
        if(obj == null && obj1 == null)
            return true;
        if(obj != null)
            return obj.equals(obj1);
        else
            return false;
    }

    private static byte[] hexStr2Bin(String s)
    {
        int i = s.length() / 2;
        byte abyte0[] = new byte[s.length() / 2];
        int j = 0;
        do
        {
            if(j >= i)
                break;
            try
            {
                abyte0[j] = (byte)Integer.parseInt(s.substring(j * 2, j * 2 + 2), 16);
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                return null;
            }
            j++;
        } while(true);
        return abyte0;
    }

    public static List newInstance(String s, byte abyte0[])
    {
        ArrayList arraylist;
        WifiP2pDevice wifip2pdevice;
        arraylist = new ArrayList();
        wifip2pdevice = new WifiP2pDevice();
        wifip2pdevice.deviceAddress = s;
        if(abyte0 == null)
            return null;
        abyte0 = new DataInputStream(new ByteArrayInputStream(abyte0));
_L7:
        if(abyte0.available() <= 0) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        int k;
        int l;
        i = (abyte0.readUnsignedByte() + (abyte0.readUnsignedByte() << 8)) - 3;
        j = abyte0.readUnsignedByte();
        k = abyte0.readUnsignedByte();
        l = abyte0.readUnsignedByte();
        if(i < 0)
            return null;
        if(i == 0)
        {
            if(l != 0)
                continue; /* Loop/switch isn't completed */
            try
            {
                s = JVM INSTR new #2   <Class WifiP2pServiceResponse>;
                s.WifiP2pServiceResponse(j, l, k, wifip2pdevice, null);
                arraylist.add(s);
                continue; /* Loop/switch isn't completed */
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                s.printStackTrace();
                if(arraylist.size() > 0)
                    return arraylist;
            }
            break MISSING_BLOCK_LABEL_251;
        }
        if(i > MAX_BUF_SIZE)
        {
            abyte0.skip(i);
            continue; /* Loop/switch isn't completed */
        }
        s = new byte[i];
        abyte0.readFully(s);
        if(j != 1) goto _L4; else goto _L3
_L3:
        s = WifiP2pDnsSdServiceResponse.newInstance(l, k, wifip2pdevice, s);
_L5:
        if(s == null)
            continue; /* Loop/switch isn't completed */
        if(s.getStatus() == 0)
            arraylist.add(s);
        continue; /* Loop/switch isn't completed */
_L4:
        if(j != 2)
            break MISSING_BLOCK_LABEL_230;
        s = WifiP2pUpnpServiceResponse.newInstance(l, k, wifip2pdevice, s);
        continue; /* Loop/switch isn't completed */
        s = new WifiP2pServiceResponse(j, l, k, wifip2pdevice, s);
        if(true) goto _L5; else goto _L2
_L2:
        return arraylist;
        return null;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        boolean flag = false;
        if(obj == this)
            return true;
        if(!(obj instanceof WifiP2pServiceResponse))
            return false;
        obj = (WifiP2pServiceResponse)obj;
        boolean flag1 = flag;
        if(((WifiP2pServiceResponse) (obj)).mServiceType == mServiceType)
        {
            flag1 = flag;
            if(((WifiP2pServiceResponse) (obj)).mStatus == mStatus)
            {
                flag1 = flag;
                if(equals(((WifiP2pServiceResponse) (obj)).mDevice.deviceAddress, mDevice.deviceAddress))
                    flag1 = Arrays.equals(((WifiP2pServiceResponse) (obj)).mData, mData);
            }
        }
        return flag1;
    }

    public byte[] getRawData()
    {
        return mData;
    }

    public int getServiceType()
    {
        return mServiceType;
    }

    public WifiP2pDevice getSrcDevice()
    {
        return mDevice;
    }

    public int getStatus()
    {
        return mStatus;
    }

    public int getTransactionId()
    {
        return mTransId;
    }

    public int hashCode()
    {
        int i = 0;
        int j = mServiceType;
        int k = mStatus;
        int l = mTransId;
        int i1;
        if(mDevice.deviceAddress == null)
            i1 = 0;
        else
            i1 = mDevice.deviceAddress.hashCode();
        if(mData != null)
            i = Arrays.hashCode(mData);
        return ((((j + 527) * 31 + k) * 31 + l) * 31 + i1) * 31 + i;
    }

    public void setSrcDevice(WifiP2pDevice wifip2pdevice)
    {
        if(wifip2pdevice == null)
        {
            return;
        } else
        {
            mDevice = wifip2pdevice;
            return;
        }
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("serviceType:").append(mServiceType);
        stringbuffer.append(" status:").append(Status.toString(mStatus));
        stringbuffer.append(" srcAddr:").append(mDevice.deviceAddress);
        stringbuffer.append(" data:").append(Arrays.toString(mData));
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mServiceType);
        parcel.writeInt(mStatus);
        parcel.writeInt(mTransId);
        parcel.writeParcelable(mDevice, i);
        if(mData == null || mData.length == 0)
        {
            parcel.writeInt(0);
        } else
        {
            parcel.writeInt(mData.length);
            parcel.writeByteArray(mData);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiP2pServiceResponse createFromParcel(Parcel parcel)
        {
            int i = parcel.readInt();
            int j = parcel.readInt();
            int k = parcel.readInt();
            WifiP2pDevice wifip2pdevice = (WifiP2pDevice)parcel.readParcelable(null);
            int l = parcel.readInt();
            byte abyte0[] = null;
            if(l > 0)
            {
                abyte0 = new byte[l];
                parcel.readByteArray(abyte0);
            }
            if(i == 1)
                return WifiP2pDnsSdServiceResponse.newInstance(j, k, wifip2pdevice, abyte0);
            if(i == 2)
                return WifiP2pUpnpServiceResponse.newInstance(j, k, wifip2pdevice, abyte0);
            else
                return new WifiP2pServiceResponse(i, j, k, wifip2pdevice, abyte0);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiP2pServiceResponse[] newArray(int i)
        {
            return new WifiP2pServiceResponse[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static int MAX_BUF_SIZE = 1024;
    protected byte mData[];
    protected WifiP2pDevice mDevice;
    protected int mServiceType;
    protected int mStatus;
    protected int mTransId;

}
