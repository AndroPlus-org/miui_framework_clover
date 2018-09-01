// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.*;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package android.hardware.usb:
//            IUsbManager, UsbAccessory, UsbDevice, UsbPort, 
//            UsbDeviceConnection, UsbPortStatus

public class UsbManager
{

    public UsbManager(Context context, IUsbManager iusbmanager)
    {
        mContext = context;
        mService = iusbmanager;
    }

    public static String addFunction(String s, String s1)
    {
        if("none".equals(s))
            return s1;
        String s2 = s;
        if(!containsFunction(s, s1))
        {
            s2 = s;
            if(s.length() > 0)
                s2 = (new StringBuilder()).append(s).append(",").toString();
            s2 = (new StringBuilder()).append(s2).append(s1).toString();
        }
        return s2;
    }

    public static boolean containsFunction(String s, String s1)
    {
        int i = s.indexOf(s1);
        if(i < 0)
            return false;
        if(i > 0 && s.charAt(i - 1) != ',')
            return false;
        i += s1.length();
        return i >= s.length() || s.charAt(i) == ',';
    }

    public static String removeFunction(String s, String s1)
    {
        s = s.split(",");
        for(int i = 0; i < s.length; i++)
            if(s1.equals(s[i]))
                s[i] = null;

        if(s.length == 1 && s[0] == null)
            return "none";
        StringBuilder stringbuilder = new StringBuilder();
        for(int j = 0; j < s.length; j++)
        {
            s1 = s[j];
            if(s1 == null)
                continue;
            if(stringbuilder.length() > 0)
                stringbuilder.append(",");
            stringbuilder.append(s1);
        }

        return stringbuilder.toString();
    }

    public UsbAccessory[] getAccessoryList()
    {
        if(mService == null)
            return null;
        UsbAccessory usbaccessory;
        try
        {
            usbaccessory = mService.getCurrentAccessory();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(usbaccessory == null)
            return null;
        else
            return (new UsbAccessory[] {
                usbaccessory
            });
    }

    public HashMap getDeviceList()
    {
        HashMap hashmap = new HashMap();
        if(mService == null)
            return hashmap;
        Bundle bundle = new Bundle();
        try
        {
            mService.getDeviceList(bundle);
            String s;
            for(Iterator iterator = bundle.keySet().iterator(); iterator.hasNext(); hashmap.put(s, (UsbDevice)bundle.get(s)))
                s = (String)iterator.next();

        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return hashmap;
    }

    public UsbPortStatus getPortStatus(UsbPort usbport)
    {
        Preconditions.checkNotNull(usbport, "port must not be null");
        try
        {
            usbport = mService.getPortStatus(usbport.getId());
        }
        // Misplaced declaration of an exception variable
        catch(UsbPort usbport)
        {
            throw usbport.rethrowFromSystemServer();
        }
        return usbport;
    }

    public UsbPort[] getPorts()
    {
        if(mService == null)
            return null;
        UsbPort ausbport[];
        try
        {
            ausbport = mService.getPorts();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return ausbport;
    }

    public void grantPermission(UsbDevice usbdevice)
    {
        grantPermission(usbdevice, Process.myUid());
    }

    public void grantPermission(UsbDevice usbdevice, int i)
    {
        try
        {
            mService.grantDevicePermission(usbdevice, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(UsbDevice usbdevice)
        {
            throw usbdevice.rethrowFromSystemServer();
        }
    }

    public void grantPermission(UsbDevice usbdevice, String s)
    {
        grantPermission(usbdevice, mContext.getPackageManager().getPackageUidAsUser(s, mContext.getUserId()));
_L1:
        return;
        usbdevice;
        Log.e("UsbManager", (new StringBuilder()).append("Package ").append(s).append(" not found.").toString(), usbdevice);
          goto _L1
    }

    public boolean hasPermission(UsbAccessory usbaccessory)
    {
        if(mService == null)
            return false;
        boolean flag;
        try
        {
            flag = mService.hasAccessoryPermission(usbaccessory);
        }
        // Misplaced declaration of an exception variable
        catch(UsbAccessory usbaccessory)
        {
            throw usbaccessory.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean hasPermission(UsbDevice usbdevice)
    {
        if(mService == null)
            return false;
        boolean flag;
        try
        {
            flag = mService.hasDevicePermission(usbdevice);
        }
        // Misplaced declaration of an exception variable
        catch(UsbDevice usbdevice)
        {
            throw usbdevice.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isFunctionEnabled(String s)
    {
        if(mService == null)
            return false;
        boolean flag;
        try
        {
            flag = mService.isFunctionEnabled(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return flag;
    }

    public ParcelFileDescriptor openAccessory(UsbAccessory usbaccessory)
    {
        try
        {
            usbaccessory = mService.openAccessory(usbaccessory);
        }
        // Misplaced declaration of an exception variable
        catch(UsbAccessory usbaccessory)
        {
            throw usbaccessory.rethrowFromSystemServer();
        }
        return usbaccessory;
    }

    public UsbDeviceConnection openDevice(UsbDevice usbdevice)
    {
        String s;
        ParcelFileDescriptor parcelfiledescriptor;
        s = usbdevice.getDeviceName();
        parcelfiledescriptor = mService.openDevice(s);
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_67;
        UsbDeviceConnection usbdeviceconnection;
        boolean flag;
        usbdeviceconnection = JVM INSTR new #285 <Class UsbDeviceConnection>;
        usbdeviceconnection.UsbDeviceConnection(usbdevice);
        flag = usbdeviceconnection.open(s, parcelfiledescriptor, mContext);
        parcelfiledescriptor.close();
        if(flag)
            return usbdeviceconnection;
        break MISSING_BLOCK_LABEL_67;
        usbdevice;
        Log.e("UsbManager", "exception in UsbManager.openDevice", usbdevice);
        return null;
    }

    public void requestPermission(UsbAccessory usbaccessory, PendingIntent pendingintent)
    {
        try
        {
            mService.requestAccessoryPermission(usbaccessory, mContext.getPackageName(), pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(UsbAccessory usbaccessory)
        {
            throw usbaccessory.rethrowFromSystemServer();
        }
    }

    public void requestPermission(UsbDevice usbdevice, PendingIntent pendingintent)
    {
        try
        {
            mService.requestDevicePermission(usbdevice, mContext.getPackageName(), pendingintent);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(UsbDevice usbdevice)
        {
            throw usbdevice.rethrowFromSystemServer();
        }
    }

    public void setCurrentFunction(String s, boolean flag)
    {
        try
        {
            mService.setCurrentFunction(s, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void setPortRoles(UsbPort usbport, int i, int j)
    {
        Preconditions.checkNotNull(usbport, "port must not be null");
        UsbPort.checkRoles(i, j);
        Log.d("UsbManager", (new StringBuilder()).append("setPortRoles Package:").append(mContext.getPackageName()).toString());
        try
        {
            mService.setPortRoles(usbport.getId(), i, j);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(UsbPort usbport)
        {
            throw usbport.rethrowFromSystemServer();
        }
    }

    public void setUsbDeviceConnectionHandler(ComponentName componentname)
    {
        try
        {
            mService.setUsbDeviceConnectionHandler(componentname);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ComponentName componentname)
        {
            throw componentname.rethrowFromSystemServer();
        }
    }

    public static final String ACTION_USB_ACCESSORY_ATTACHED = "android.hardware.usb.action.USB_ACCESSORY_ATTACHED";
    public static final String ACTION_USB_ACCESSORY_DETACHED = "android.hardware.usb.action.USB_ACCESSORY_DETACHED";
    public static final String ACTION_USB_DEVICE_ATTACHED = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
    public static final String ACTION_USB_DEVICE_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED";
    public static final String ACTION_USB_PORT_CHANGED = "android.hardware.usb.action.USB_PORT_CHANGED";
    public static final String ACTION_USB_STATE = "android.hardware.usb.action.USB_STATE";
    public static final String EXTRA_ACCESSORY = "accessory";
    public static final String EXTRA_DEVICE = "device";
    public static final String EXTRA_PERMISSION_GRANTED = "permission";
    public static final String EXTRA_PORT = "port";
    public static final String EXTRA_PORT_STATUS = "portStatus";
    private static final String TAG = "UsbManager";
    public static final String USB_CONFIGURED = "configured";
    public static final String USB_CONFIG_CHANGED = "config_changed";
    public static final String USB_CONNECTED = "connected";
    public static final String USB_DATA_UNLOCKED = "unlocked";
    public static final String USB_FUNCTION_ACCESSORY = "accessory";
    public static final String USB_FUNCTION_ADB = "adb";
    public static final String USB_FUNCTION_AUDIO_SOURCE = "audio_source";
    public static final String USB_FUNCTION_MIDI = "midi";
    public static final String USB_FUNCTION_MTP = "mtp";
    public static final String USB_FUNCTION_NONE = "none";
    public static final String USB_FUNCTION_PTP = "ptp";
    public static final String USB_FUNCTION_RNDIS = "rndis";
    public static final String USB_HOST_CONNECTED = "host_connected";
    private final Context mContext;
    private final IUsbManager mService;
}
