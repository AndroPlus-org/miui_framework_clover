// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.RemoteException;
import android.util.Log;

// Referenced classes of package android.hardware.hdmi:
//            IHdmiControlService, IHdmiVendorCommandListener, HdmiDeviceInfo

public abstract class HdmiClient
{

    HdmiClient(IHdmiControlService ihdmicontrolservice)
    {
        mService = ihdmicontrolservice;
    }

    private static IHdmiVendorCommandListener getListenerWrapper(HdmiControlManager.VendorCommandListener vendorcommandlistener)
    {
        return new IHdmiVendorCommandListener.Stub(vendorcommandlistener) {

            public void onControlStateChanged(boolean flag, int i)
            {
                listener.onControlStateChanged(flag, i);
            }

            public void onReceived(int i, int j, byte abyte0[], boolean flag)
            {
                listener.onReceived(i, j, abyte0, flag);
            }

            final HdmiControlManager.VendorCommandListener val$listener;

            
            {
                listener = vendorcommandlistener;
                super();
            }
        }
;
    }

    public HdmiDeviceInfo getActiveSource()
    {
        HdmiDeviceInfo hdmideviceinfo;
        try
        {
            hdmideviceinfo = mService.getActiveSource();
        }
        catch(RemoteException remoteexception)
        {
            Log.e("HdmiClient", "getActiveSource threw exception ", remoteexception);
            return null;
        }
        return hdmideviceinfo;
    }

    abstract int getDeviceType();

    public void sendKeyEvent(int i, boolean flag)
    {
        mService.sendKeyEvent(getDeviceType(), i, flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("HdmiClient", "sendKeyEvent threw exception ", remoteexception);
          goto _L1
    }

    public void sendVendorCommand(int i, byte abyte0[], boolean flag)
    {
        mService.sendVendorCommand(getDeviceType(), i, abyte0, flag);
_L1:
        return;
        abyte0;
        Log.e("HdmiClient", "failed to send vendor command: ", abyte0);
          goto _L1
    }

    public void setVendorCommandListener(HdmiControlManager.VendorCommandListener vendorcommandlistener)
    {
        if(vendorcommandlistener == null)
            throw new IllegalArgumentException("listener cannot be null");
        if(mIHdmiVendorCommandListener != null)
            throw new IllegalStateException("listener was already set");
        vendorcommandlistener = getListenerWrapper(vendorcommandlistener);
        mService.addVendorCommandListener(vendorcommandlistener, getDeviceType());
        mIHdmiVendorCommandListener = vendorcommandlistener;
_L1:
        return;
        vendorcommandlistener;
        Log.e("HdmiClient", "failed to set vendor command listener: ", vendorcommandlistener);
          goto _L1
    }

    private static final String TAG = "HdmiClient";
    private IHdmiVendorCommandListener mIHdmiVendorCommandListener;
    final IHdmiControlService mService;
}
