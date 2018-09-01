// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.*;

// Referenced classes of package android.hardware.hdmi:
//            HdmiDeviceInfo

public interface IHdmiDeviceEventListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IHdmiDeviceEventListener
    {

        public static IHdmiDeviceEventListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.hdmi.IHdmiDeviceEventListener");
            if(iinterface != null && (iinterface instanceof IHdmiDeviceEventListener))
                return (IHdmiDeviceEventListener)iinterface;
            else
                return new Proxy(ibinder);
        }

        public IBinder asBinder()
        {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel1, int j)
            throws RemoteException
        {
            switch(i)
            {
            default:
                return super.onTransact(i, parcel, parcel1, j);

            case 1598968902: 
                parcel1.writeString("android.hardware.hdmi.IHdmiDeviceEventListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiDeviceEventListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel1 = (HdmiDeviceInfo)HdmiDeviceInfo.CREATOR.createFromParcel(parcel);
            else
                parcel1 = null;
            onStatusChanged(parcel1, parcel.readInt());
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiDeviceEventListener";
        static final int TRANSACTION_onStatusChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.hdmi.IHdmiDeviceEventListener");
        }
    }

    private static class Stub.Proxy
        implements IHdmiDeviceEventListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.hdmi.IHdmiDeviceEventListener";
        }

        public void onStatusChanged(HdmiDeviceInfo hdmideviceinfo, int i)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiDeviceEventListener");
            if(hdmideviceinfo == null)
                break MISSING_BLOCK_LABEL_49;
            parcel.writeInt(1);
            hdmideviceinfo.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            hdmideviceinfo;
            parcel.recycle();
            throw hdmideviceinfo;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onStatusChanged(HdmiDeviceInfo hdmideviceinfo, int i)
        throws RemoteException;
}
