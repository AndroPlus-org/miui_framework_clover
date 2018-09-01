// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.*;

// Referenced classes of package android.hardware.hdmi:
//            HdmiDeviceInfo

public interface IHdmiInputChangeListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IHdmiInputChangeListener
    {

        public static IHdmiInputChangeListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.hdmi.IHdmiInputChangeListener");
            if(iinterface != null && (iinterface instanceof IHdmiInputChangeListener))
                return (IHdmiInputChangeListener)iinterface;
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
                parcel1.writeString("android.hardware.hdmi.IHdmiInputChangeListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiInputChangeListener");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (HdmiDeviceInfo)HdmiDeviceInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            onChanged(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiInputChangeListener";
        static final int TRANSACTION_onChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.hdmi.IHdmiInputChangeListener");
        }
    }

    private static class Stub.Proxy
        implements IHdmiInputChangeListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.hdmi.IHdmiInputChangeListener";
        }

        public void onChanged(HdmiDeviceInfo hdmideviceinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiInputChangeListener");
            if(hdmideviceinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            hdmideviceinfo.writeToParcel(parcel, 0);
_L1:
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


    public abstract void onChanged(HdmiDeviceInfo hdmideviceinfo)
        throws RemoteException;
}
