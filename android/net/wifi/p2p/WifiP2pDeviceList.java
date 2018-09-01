// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.net.wifi.p2p;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.*;

// Referenced classes of package android.net.wifi.p2p:
//            WifiP2pDevice

public class WifiP2pDeviceList
    implements Parcelable
{

    public WifiP2pDeviceList()
    {
        mDevices = new HashMap();
    }

    public WifiP2pDeviceList(WifiP2pDeviceList wifip2pdevicelist)
    {
        mDevices = new HashMap();
        if(wifip2pdevicelist != null)
        {
            WifiP2pDevice wifip2pdevice;
            for(wifip2pdevicelist = wifip2pdevicelist.getDeviceList().iterator(); wifip2pdevicelist.hasNext(); mDevices.put(wifip2pdevice.deviceAddress, new WifiP2pDevice(wifip2pdevice)))
                wifip2pdevice = (WifiP2pDevice)wifip2pdevicelist.next();

        }
    }

    public WifiP2pDeviceList(ArrayList arraylist)
    {
        mDevices = new HashMap();
        Iterator iterator = arraylist.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            arraylist = (WifiP2pDevice)iterator.next();
            if(((WifiP2pDevice) (arraylist)).deviceAddress != null)
                mDevices.put(((WifiP2pDevice) (arraylist)).deviceAddress, new WifiP2pDevice(arraylist));
        } while(true);
    }

    private void validateDevice(WifiP2pDevice wifip2pdevice)
    {
        if(wifip2pdevice == null)
            throw new IllegalArgumentException("Null device");
        if(TextUtils.isEmpty(wifip2pdevice.deviceAddress))
            throw new IllegalArgumentException("Empty deviceAddress");
        else
            return;
    }

    private void validateDeviceAddress(String s)
    {
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("Empty deviceAddress");
        else
            return;
    }

    public boolean clear()
    {
        if(mDevices.isEmpty())
        {
            return false;
        } else
        {
            mDevices.clear();
            return true;
        }
    }

    public int describeContents()
    {
        return 0;
    }

    public WifiP2pDevice get(String s)
    {
        validateDeviceAddress(s);
        return (WifiP2pDevice)mDevices.get(s);
    }

    public Collection getDeviceList()
    {
        return Collections.unmodifiableCollection(mDevices.values());
    }

    public boolean isGroupOwner(String s)
    {
        validateDeviceAddress(s);
        WifiP2pDevice wifip2pdevice = (WifiP2pDevice)mDevices.get(s);
        if(wifip2pdevice == null)
            throw new IllegalArgumentException((new StringBuilder()).append("Device not found ").append(s).toString());
        else
            return wifip2pdevice.isGroupOwner();
    }

    public WifiP2pDevice remove(String s)
    {
        validateDeviceAddress(s);
        return (WifiP2pDevice)mDevices.remove(s);
    }

    public boolean remove(WifiP2pDevice wifip2pdevice)
    {
        validateDevice(wifip2pdevice);
        boolean flag;
        if(mDevices.remove(wifip2pdevice.deviceAddress) != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean remove(WifiP2pDeviceList wifip2pdevicelist)
    {
        boolean flag = false;
        wifip2pdevicelist = wifip2pdevicelist.mDevices.values().iterator();
        do
        {
            if(!wifip2pdevicelist.hasNext())
                break;
            if(remove((WifiP2pDevice)wifip2pdevicelist.next()))
                flag = true;
        } while(true);
        return flag;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        WifiP2pDevice wifip2pdevice;
        for(Iterator iterator = mDevices.values().iterator(); iterator.hasNext(); stringbuffer.append("\n").append(wifip2pdevice))
            wifip2pdevice = (WifiP2pDevice)iterator.next();

        return stringbuffer.toString();
    }

    public void update(WifiP2pDevice wifip2pdevice)
    {
        updateSupplicantDetails(wifip2pdevice);
        ((WifiP2pDevice)mDevices.get(wifip2pdevice.deviceAddress)).status = wifip2pdevice.status;
    }

    public void updateGroupCapability(String s, int i)
    {
        validateDeviceAddress(s);
        s = (WifiP2pDevice)mDevices.get(s);
        if(s != null)
            s.groupCapability = i;
    }

    public void updateStatus(String s, int i)
    {
        validateDeviceAddress(s);
        s = (WifiP2pDevice)mDevices.get(s);
        if(s != null)
            s.status = i;
    }

    public void updateSupplicantDetails(WifiP2pDevice wifip2pdevice)
    {
        validateDevice(wifip2pdevice);
        WifiP2pDevice wifip2pdevice1 = (WifiP2pDevice)mDevices.get(wifip2pdevice.deviceAddress);
        if(wifip2pdevice1 != null)
        {
            wifip2pdevice1.deviceName = wifip2pdevice.deviceName;
            wifip2pdevice1.primaryDeviceType = wifip2pdevice.primaryDeviceType;
            wifip2pdevice1.secondaryDeviceType = wifip2pdevice.secondaryDeviceType;
            wifip2pdevice1.wpsConfigMethodsSupported = wifip2pdevice.wpsConfigMethodsSupported;
            wifip2pdevice1.deviceCapability = wifip2pdevice.deviceCapability;
            wifip2pdevice1.groupCapability = wifip2pdevice.groupCapability;
            wifip2pdevice1.wfdInfo = wifip2pdevice.wfdInfo;
            return;
        } else
        {
            mDevices.put(wifip2pdevice.deviceAddress, wifip2pdevice);
            return;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(mDevices.size());
        for(Iterator iterator = mDevices.values().iterator(); iterator.hasNext(); parcel.writeParcelable((WifiP2pDevice)iterator.next(), i));
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public WifiP2pDeviceList createFromParcel(Parcel parcel)
        {
            WifiP2pDeviceList wifip2pdevicelist = new WifiP2pDeviceList();
            int i = parcel.readInt();
            for(int j = 0; j < i; j++)
                wifip2pdevicelist.update((WifiP2pDevice)parcel.readParcelable(null));

            return wifip2pdevicelist;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public WifiP2pDeviceList[] newArray(int i)
        {
            return new WifiP2pDeviceList[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final HashMap mDevices;

}
