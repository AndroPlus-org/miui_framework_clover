// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package android.net.wifi.p2p:
//            WifiP2pDevice

public class WifiP2pGroup
    implements Parcelable
{

    public WifiP2pGroup()
    {
        mClients = new ArrayList();
    }

    public WifiP2pGroup(WifiP2pGroup wifip2pgroup)
    {
        mClients = new ArrayList();
        if(wifip2pgroup != null)
        {
            mNetworkName = wifip2pgroup.getNetworkName();
            mOwner = new WifiP2pDevice(wifip2pgroup.getOwner());
            mIsGroupOwner = wifip2pgroup.mIsGroupOwner;
            WifiP2pDevice wifip2pdevice;
            for(Iterator iterator = wifip2pgroup.getClientList().iterator(); iterator.hasNext(); mClients.add(wifip2pdevice))
                wifip2pdevice = (WifiP2pDevice)iterator.next();

            mPassphrase = wifip2pgroup.getPassphrase();
            mInterface = wifip2pgroup.getInterface();
            mNetId = wifip2pgroup.getNetworkId();
        }
    }

    public WifiP2pGroup(String s)
        throws IllegalArgumentException
    {
        mClients = new ArrayList();
        String as[] = s.split(" ");
        if(as.length < 3)
            throw new IllegalArgumentException("Malformed supplicant event");
        if(as[0].startsWith("P2P-GROUP"))
        {
            mInterface = as[1];
            mIsGroupOwner = as[2].equals("GO");
            s = groupStartedPattern.matcher(s);
            if(!s.find())
                return;
            mNetworkName = s.group(1);
            mPassphrase = s.group(4);
            mOwner = new WifiP2pDevice(s.group(5));
            if(s.group(6) != null)
                mNetId = -2;
            else
                mNetId = -1;
        } else
        if(as[0].equals("P2P-INVITATION-RECEIVED"))
        {
            mNetId = -2;
            int i = as.length;
            int j = 0;
            while(j < i) 
            {
                s = as[j].split("=");
                if(s.length == 2)
                    if(s[0].equals("sa"))
                    {
                        WifiP2pDevice wifip2pdevice = s[1];
                        wifip2pdevice = new WifiP2pDevice();
                        wifip2pdevice.deviceAddress = s[1];
                        mClients.add(wifip2pdevice);
                    } else
                    if(s[0].equals("go_dev_addr"))
                        mOwner = new WifiP2pDevice(s[1]);
                    else
                    if(s[0].equals("persistent"))
                        mNetId = Integer.parseInt(s[1]);
                j++;
            }
        } else
        {
            throw new IllegalArgumentException("Malformed supplicant event");
        }
    }

    public void addClient(WifiP2pDevice wifip2pdevice)
    {
        for(Iterator iterator = mClients.iterator(); iterator.hasNext();)
            if(((WifiP2pDevice)iterator.next()).equals(wifip2pdevice))
                return;

        mClients.add(wifip2pdevice);
    }

    public void addClient(String s)
    {
        addClient(new WifiP2pDevice(s));
    }

    public boolean contains(WifiP2pDevice wifip2pdevice)
    {
        return mOwner.equals(wifip2pdevice) || mClients.contains(wifip2pdevice);
    }

    public int describeContents()
    {
        return 0;
    }

    public Collection getClientList()
    {
        return Collections.unmodifiableCollection(mClients);
    }

    public String getInterface()
    {
        return mInterface;
    }

    public int getNetworkId()
    {
        return mNetId;
    }

    public String getNetworkName()
    {
        return mNetworkName;
    }

    public WifiP2pDevice getOwner()
    {
        return mOwner;
    }

    public String getPassphrase()
    {
        return mPassphrase;
    }

    public boolean isClientListEmpty()
    {
        boolean flag = false;
        if(mClients.size() == 0)
            flag = true;
        return flag;
    }

    public boolean isGroupOwner()
    {
        return mIsGroupOwner;
    }

    public boolean removeClient(WifiP2pDevice wifip2pdevice)
    {
        return mClients.remove(wifip2pdevice);
    }

    public boolean removeClient(String s)
    {
        return mClients.remove(new WifiP2pDevice(s));
    }

    public void setInterface(String s)
    {
        mInterface = s;
    }

    public void setIsGroupOwner(boolean flag)
    {
        mIsGroupOwner = flag;
    }

    public void setNetworkId(int i)
    {
        mNetId = i;
    }

    public void setNetworkName(String s)
    {
        mNetworkName = s;
    }

    public void setOwner(WifiP2pDevice wifip2pdevice)
    {
        mOwner = wifip2pdevice;
    }

    public void setPassphrase(String s)
    {
        mPassphrase = s;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("network: ").append(mNetworkName);
        stringbuffer.append("\n isGO: ").append(mIsGroupOwner);
        stringbuffer.append("\n GO: ").append(mOwner);
        WifiP2pDevice wifip2pdevice;
        for(Iterator iterator = mClients.iterator(); iterator.hasNext(); stringbuffer.append("\n Client: ").append(wifip2pdevice))
            wifip2pdevice = (WifiP2pDevice)iterator.next();

        stringbuffer.append("\n interface: ").append(mInterface);
        stringbuffer.append("\n networkId: ").append(mNetId);
        return stringbuffer.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mNetworkName);
        parcel.writeParcelable(mOwner, i);
        byte byte0;
        if(mIsGroupOwner)
        {
            boolean flag = true;
            byte0 = flag;
        } else
        {
            boolean flag1 = false;
            byte0 = flag1;
        }
        parcel.writeByte(byte0);
        parcel.writeInt(mClients.size());
        for(Iterator iterator = mClients.iterator(); iterator.hasNext(); parcel.writeParcelable((WifiP2pDevice)iterator.next(), i));
        parcel.writeString(mPassphrase);
        parcel.writeString(mInterface);
        parcel.writeInt(mNetId);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiP2pGroup createFromParcel(Parcel parcel)
        {
            WifiP2pGroup wifip2pgroup = new WifiP2pGroup();
            wifip2pgroup.setNetworkName(parcel.readString());
            wifip2pgroup.setOwner((WifiP2pDevice)parcel.readParcelable(null));
            boolean flag;
            int i;
            if(parcel.readByte() == 1)
                flag = true;
            else
                flag = false;
            wifip2pgroup.setIsGroupOwner(flag);
            i = parcel.readInt();
            for(int j = 0; j < i; j++)
                wifip2pgroup.addClient((WifiP2pDevice)parcel.readParcelable(null));

            wifip2pgroup.setPassphrase(parcel.readString());
            wifip2pgroup.setInterface(parcel.readString());
            wifip2pgroup.setNetworkId(parcel.readInt());
            return wifip2pgroup;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiP2pGroup[] newArray(int i)
        {
            return new WifiP2pGroup[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int PERSISTENT_NET_ID = -2;
    public static final int TEMPORARY_NET_ID = -1;
    private static final Pattern groupStartedPattern = Pattern.compile("ssid=\"(.+)\" freq=(\\d+) (?:psk=)?([0-9a-fA-F]{64})?(?:passphrase=)?(?:\"(.{0,63})\")? go_dev_addr=((?:[0-9a-f]{2}:){5}[0-9a-f]{2}) ?(\\[PERSISTENT\\])?");
    private List mClients;
    private String mInterface;
    private boolean mIsGroupOwner;
    private int mNetId;
    private String mNetworkName;
    private WifiP2pDevice mOwner;
    private String mPassphrase;

}
