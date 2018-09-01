// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.nsd;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.*;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class NsdServiceInfo
    implements Parcelable
{

    static ArrayMap _2D_get0(NsdServiceInfo nsdserviceinfo)
    {
        return nsdserviceinfo.mTxtRecord;
    }

    static InetAddress _2D_set0(NsdServiceInfo nsdserviceinfo, InetAddress inetaddress)
    {
        nsdserviceinfo.mHost = inetaddress;
        return inetaddress;
    }

    static int _2D_set1(NsdServiceInfo nsdserviceinfo, int i)
    {
        nsdserviceinfo.mPort = i;
        return i;
    }

    static String _2D_set2(NsdServiceInfo nsdserviceinfo, String s)
    {
        nsdserviceinfo.mServiceName = s;
        return s;
    }

    static String _2D_set3(NsdServiceInfo nsdserviceinfo, String s)
    {
        nsdserviceinfo.mServiceType = s;
        return s;
    }

    public NsdServiceInfo()
    {
        mTxtRecord = new ArrayMap();
    }

    public NsdServiceInfo(String s, String s1)
    {
        mTxtRecord = new ArrayMap();
        mServiceName = s;
        mServiceType = s1;
    }

    private int getTxtRecordSize()
    {
        int i = 0;
        Iterator iterator = mTxtRecord.entrySet().iterator();
        while(iterator.hasNext()) 
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            int j = ((String)entry.getKey()).length();
            byte abyte0[] = (byte[])entry.getValue();
            int k;
            if(abyte0 == null)
                k = 0;
            else
                k = abyte0.length;
            i = i + 2 + j + k;
        }
        return i;
    }

    public int describeContents()
    {
        return 0;
    }

    public Map getAttributes()
    {
        return Collections.unmodifiableMap(mTxtRecord);
    }

    public InetAddress getHost()
    {
        return mHost;
    }

    public int getPort()
    {
        return mPort;
    }

    public String getServiceName()
    {
        return mServiceName;
    }

    public String getServiceType()
    {
        return mServiceType;
    }

    public byte[] getTxtRecord()
    {
        int i = getTxtRecordSize();
        if(i == 0)
            return new byte[0];
        byte abyte0[] = new byte[i];
        i = 0;
        Iterator iterator = mTxtRecord.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            byte abyte1[] = (byte[])entry.getValue();
            int j = i + 1;
            int k = s.length();
            int l;
            if(abyte1 == null)
                l = 0;
            else
                l = abyte1.length;
            abyte0[i] = (byte)(l + k + 1);
            System.arraycopy(s.getBytes(StandardCharsets.US_ASCII), 0, abyte0, j, s.length());
            l = j + s.length();
            i = l + 1;
            abyte0[l] = (byte)61;
            if(abyte1 != null)
            {
                System.arraycopy(abyte1, 0, abyte0, i, abyte1.length);
                i += abyte1.length;
            }
        } while(true);
        return abyte0;
    }

    public void removeAttribute(String s)
    {
        mTxtRecord.remove(s);
    }

    public void setAttribute(String s, String s1)
    {
        if(s1 != null)
            break MISSING_BLOCK_LABEL_16;
        s1 = (byte[])null;
_L1:
        setAttribute(s, ((byte []) (s1)));
        return;
        try
        {
            s1 = s1.getBytes("UTF-8");
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw new IllegalArgumentException("Value must be UTF-8");
        }
          goto _L1
    }

    public void setAttribute(String s, byte abyte0[])
    {
        boolean flag = false;
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Key cannot be empty");
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(c < ' ' || c > '~')
                throw new IllegalArgumentException("Key strings must be printable US-ASCII");
            if(c == '=')
                throw new IllegalArgumentException("Key strings must not include '='");
        }

        int k = s.length();
        int j;
        if(abyte0 == null)
            j = 0;
        else
            j = abyte0.length;
        if(j + k >= 255)
            throw new IllegalArgumentException("Key length + value length must be < 255 bytes");
        if(s.length() > 9)
            Log.w("NsdServiceInfo", (new StringBuilder()).append("Key lengths > 9 are discouraged: ").append(s).toString());
        int l = getTxtRecordSize();
        k = s.length();
        if(abyte0 == null)
            j = ((flag) ? 1 : 0);
        else
            j = abyte0.length;
        j = j + (k + l) + 2;
        if(j > 1300)
            throw new IllegalArgumentException("Total length of attributes must be < 1300 bytes");
        if(j > 400)
            Log.w("NsdServiceInfo", "Total length of all attributes exceeds 400 bytes; truncation may occur");
        mTxtRecord.put(s, abyte0);
    }

    public void setHost(InetAddress inetaddress)
    {
        mHost = inetaddress;
    }

    public void setPort(int i)
    {
        mPort = i;
    }

    public void setServiceName(String s)
    {
        mServiceName = s;
    }

    public void setServiceType(String s)
    {
        mServiceType = s;
    }

    public void setTxtRecords(String s)
    {
        byte abyte0[];
        int i;
        abyte0 = Base64.decode(s, 0);
        i = 0;
_L2:
        int j;
        int k;
        int l;
        if(i >= abyte0.length)
            break MISSING_BLOCK_LABEL_460;
        j = abyte0[i] & 0xff;
        k = i + 1;
        if(j != 0)
            break; /* Loop/switch isn't completed */
        l = j;
        s = JVM INSTR new #149 <Class IllegalArgumentException>;
        l = j;
        s.IllegalArgumentException("Zero sized txt record");
        l = j;
        try
        {
            throw s;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("NsdServiceInfo", (new StringBuilder()).append("While parsing txt records (pos = ").append(k).append("): ").append(s.getMessage()).toString());
        }
        i = l;
_L6:
        i = k + i;
        if(true) goto _L2; else goto _L1
_L1:
        l = j;
        i = j;
        if(k + j <= abyte0.length)
            break MISSING_BLOCK_LABEL_184;
        l = j;
        s = JVM INSTR new #173 <Class StringBuilder>;
        l = j;
        s.StringBuilder();
        l = j;
        Log.w("NsdServiceInfo", s.append("Corrupt record length (pos = ").append(k).append("): ").append(j).toString());
        l = j;
        i = abyte0.length - k;
        Object obj;
        int i1;
        obj = null;
        s = null;
        i1 = 0;
        j = k;
_L4:
        Object obj1;
        if(j >= k + i)
            break MISSING_BLOCK_LABEL_311;
        if(obj != null)
            break; /* Loop/switch isn't completed */
        obj1 = s;
        l = i1;
        if(abyte0[j] != 61)
            break MISSING_BLOCK_LABEL_256;
        l = i;
        obj = new String(abyte0, k, j - k, StandardCharsets.US_ASCII);
        l = i1;
        obj1 = s;
_L5:
        j++;
        s = ((String) (obj1));
        i1 = l;
        if(true) goto _L4; else goto _L3
_L3:
        obj1 = s;
        if(s != null)
            break MISSING_BLOCK_LABEL_292;
        l = i;
        obj1 = new byte[i - ((String) (obj)).length() - 1];
        obj1[i1] = abyte0[j];
        l = i1 + 1;
          goto _L5
        obj1 = obj;
        if(obj != null)
            break MISSING_BLOCK_LABEL_343;
        l = i;
        obj1 = JVM INSTR new #85  <Class String>;
        l = i;
        ((String) (obj1)).String(abyte0, k, i, StandardCharsets.US_ASCII);
        l = i;
        if(!TextUtils.isEmpty(((CharSequence) (obj1))))
            break MISSING_BLOCK_LABEL_375;
        l = i;
        s = JVM INSTR new #149 <Class IllegalArgumentException>;
        l = i;
        s.IllegalArgumentException("Invalid txt record (key is empty)");
        l = i;
        throw s;
        l = i;
        if(!getAttributes().containsKey(obj1))
            break MISSING_BLOCK_LABEL_447;
        l = i;
        s = JVM INSTR new #149 <Class IllegalArgumentException>;
        l = i;
        obj = JVM INSTR new #173 <Class StringBuilder>;
        l = i;
        ((StringBuilder) (obj)).StringBuilder();
        l = i;
        s.IllegalArgumentException(((StringBuilder) (obj)).append("Invalid txt record (duplicate key \"").append(((String) (obj1))).append("\")").toString());
        l = i;
        throw s;
        l = i;
        setAttribute(((String) (obj1)), s);
          goto _L6
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("name: ").append(mServiceName).append(", type: ").append(mServiceType).append(", host: ").append(mHost).append(", port: ").append(mPort);
        byte abyte0[] = getTxtRecord();
        if(abyte0 != null)
            stringbuffer.append(", txtRecord: ").append(new String(abyte0, StandardCharsets.UTF_8));
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mServiceName);
        parcel.writeString(mServiceType);
        Iterator iterator;
        if(mHost != null)
        {
            parcel.writeInt(1);
            parcel.writeByteArray(mHost.getAddress());
        } else
        {
            parcel.writeInt(0);
        }
        parcel.writeInt(mPort);
        parcel.writeInt(mTxtRecord.size());
        iterator = mTxtRecord.keySet().iterator();
        while(iterator.hasNext()) 
        {
            String s = (String)iterator.next();
            byte abyte0[] = (byte[])mTxtRecord.get(s);
            if(abyte0 != null)
            {
                parcel.writeInt(1);
                parcel.writeInt(abyte0.length);
                parcel.writeByteArray(abyte0);
            } else
            {
                parcel.writeInt(0);
            }
            parcel.writeString(s);
        }
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public NsdServiceInfo createFromParcel(Parcel parcel)
        {
            NsdServiceInfo nsdserviceinfo = new NsdServiceInfo();
            NsdServiceInfo._2D_set2(nsdserviceinfo, parcel.readString());
            NsdServiceInfo._2D_set3(nsdserviceinfo, parcel.readString());
            int i;
            if(parcel.readInt() == 1)
                try
                {
                    NsdServiceInfo._2D_set0(nsdserviceinfo, InetAddress.getByAddress(parcel.createByteArray()));
                }
                catch(UnknownHostException unknownhostexception) { }
            NsdServiceInfo._2D_set1(nsdserviceinfo, parcel.readInt());
            i = parcel.readInt();
            for(int j = 0; j < i; j++)
            {
                byte abyte0[] = null;
                if(parcel.readInt() == 1)
                {
                    abyte0 = new byte[parcel.readInt()];
                    parcel.readByteArray(abyte0);
                }
                NsdServiceInfo._2D_get0(nsdserviceinfo).put(parcel.readString(), abyte0);
            }

            return nsdserviceinfo;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public NsdServiceInfo[] newArray(int i)
        {
            return new NsdServiceInfo[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private static final String TAG = "NsdServiceInfo";
    private InetAddress mHost;
    private int mPort;
    private String mServiceName;
    private String mServiceType;
    private final ArrayMap mTxtRecord;

}
