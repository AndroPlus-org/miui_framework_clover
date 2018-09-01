// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p.nsd;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class WifiP2pServiceInfo
    implements Parcelable
{

    protected WifiP2pServiceInfo(List list)
    {
        if(list == null)
        {
            throw new IllegalArgumentException("query list cannot be null");
        } else
        {
            mQueryList = list;
            return;
        }
    }

    static String bin2HexStr(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = 0;
        int j = abyte0.length;
        do
        {
            if(i >= j)
                break;
            byte byte0 = abyte0[i];
            String s;
            try
            {
                s = Integer.toHexString(byte0 & 0xff);
            }
            // Misplaced declaration of an exception variable
            catch(byte abyte0[])
            {
                abyte0.printStackTrace();
                return null;
            }
            if(s.length() == 1)
                stringbuffer.append('0');
            stringbuffer.append(s);
            i++;
        } while(true);
        return stringbuffer.toString();
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof WifiP2pServiceInfo))
        {
            return false;
        } else
        {
            obj = (WifiP2pServiceInfo)obj;
            return mQueryList.equals(((WifiP2pServiceInfo) (obj)).mQueryList);
        }
    }

    public List getSupplicantQueryList()
    {
        return mQueryList;
    }

    public int hashCode()
    {
        int i;
        if(mQueryList == null)
            i = 0;
        else
            i = mQueryList.hashCode();
        return i + 527;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStringList(mQueryList);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiP2pServiceInfo createFromParcel(Parcel parcel)
        {
            ArrayList arraylist = new ArrayList();
            parcel.readStringList(arraylist);
            return new WifiP2pServiceInfo(arraylist);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiP2pServiceInfo[] newArray(int i)
        {
            return new WifiP2pServiceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int SERVICE_TYPE_ALL = 0;
    public static final int SERVICE_TYPE_BONJOUR = 1;
    public static final int SERVICE_TYPE_UPNP = 2;
    public static final int SERVICE_TYPE_VENDOR_SPECIFIC = 255;
    public static final int SERVICE_TYPE_WS_DISCOVERY = 3;
    private List mQueryList;

}
