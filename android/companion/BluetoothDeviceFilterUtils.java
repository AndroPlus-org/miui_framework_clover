// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.companion;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanFilter;
import android.net.wifi.ScanResult;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BluetoothDeviceFilterUtils
{

    private BluetoothDeviceFilterUtils()
    {
    }

    private static void debugLogMatchResult(boolean flag, BluetoothDevice bluetoothdevice, Object obj)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(getDeviceDisplayNameInternal(bluetoothdevice));
        if(flag)
            bluetoothdevice = " ~ ";
        else
            bluetoothdevice = " !~ ";
        Log.i("BluetoothDeviceFilterUtils", stringbuilder.append(bluetoothdevice).append(obj).toString());
    }

    private static void debugLogMatchResult(boolean flag, ScanResult scanresult, Object obj)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(getDeviceDisplayNameInternal(scanresult));
        if(flag)
            scanresult = " ~ ";
        else
            scanresult = " !~ ";
        Log.i("BluetoothDeviceFilterUtils", stringbuilder.append(scanresult).append(obj).toString());
    }

    public static String getDeviceDisplayNameInternal(BluetoothDevice bluetoothdevice)
    {
        return TextUtils.firstNotEmpty(bluetoothdevice.getAliasName(), bluetoothdevice.getAddress());
    }

    public static String getDeviceDisplayNameInternal(ScanResult scanresult)
    {
        return TextUtils.firstNotEmpty(scanresult.SSID, scanresult.BSSID);
    }

    public static String getDeviceMacAddress(Parcelable parcelable)
    {
        if(parcelable instanceof BluetoothDevice)
            return ((BluetoothDevice)parcelable).getAddress();
        if(parcelable instanceof ScanResult)
            return ((ScanResult)parcelable).BSSID;
        if(parcelable instanceof android.bluetooth.le.ScanResult)
            return getDeviceMacAddress(((Parcelable) (((android.bluetooth.le.ScanResult)parcelable).getDevice())));
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown device type: ").append(parcelable).toString());
    }

    static boolean matches(ScanFilter scanfilter, BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        if(matchesAddress(scanfilter.getDeviceAddress(), bluetoothdevice))
            flag = matchesServiceUuid(scanfilter.getServiceUuid(), scanfilter.getServiceUuidMask(), bluetoothdevice);
        else
            flag = false;
        return flag;
    }

    static boolean matchesAddress(String s, BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        if(s != null)
        {
            if(bluetoothdevice != null)
                flag = s.equals(bluetoothdevice.getAddress());
            else
                flag = false;
        } else
        {
            flag = true;
        }
        return flag;
    }

    static boolean matchesName(Pattern pattern, BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        if(pattern == null)
            flag = true;
        else
        if(bluetoothdevice == null)
        {
            flag = false;
        } else
        {
            bluetoothdevice = bluetoothdevice.getName();
            if(bluetoothdevice != null)
                flag = pattern.matcher(bluetoothdevice).find();
            else
                flag = false;
        }
        return flag;
    }

    static boolean matchesName(Pattern pattern, ScanResult scanresult)
    {
        boolean flag;
        if(pattern == null)
            flag = true;
        else
        if(scanresult == null)
        {
            flag = false;
        } else
        {
            scanresult = scanresult.SSID;
            if(scanresult != null)
                flag = pattern.matcher(scanresult).find();
            else
                flag = false;
        }
        return flag;
    }

    static boolean matchesServiceUuid(ParcelUuid parceluuid, ParcelUuid parceluuid1, BluetoothDevice bluetoothdevice)
    {
        boolean flag;
        if(parceluuid != null)
            flag = ScanFilter.matchesServiceUuids(parceluuid, parceluuid1, Arrays.asList(bluetoothdevice.getUuids()));
        else
            flag = true;
        return flag;
    }

    static boolean matchesServiceUuids(List list, List list1, BluetoothDevice bluetoothdevice)
    {
        for(int i = 0; i < list.size(); i++)
            if(!matchesServiceUuid((ParcelUuid)list.get(i), (ParcelUuid)list1.get(i), bluetoothdevice))
                return false;

        return true;
    }

    static Pattern patternFromString(String s)
    {
        Object obj = null;
        if(s == null)
            s = obj;
        else
            s = Pattern.compile(s);
        return s;
    }

    static String patternToString(Pattern pattern)
    {
        Object obj = null;
        if(pattern == null)
            pattern = obj;
        else
            pattern = pattern.pattern();
        return pattern;
    }

    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "BluetoothDeviceFilterUtils";
}
