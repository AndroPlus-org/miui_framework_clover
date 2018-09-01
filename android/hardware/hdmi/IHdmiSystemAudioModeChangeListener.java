// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.*;

public interface IHdmiSystemAudioModeChangeListener
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IHdmiSystemAudioModeChangeListener
    {

        public static IHdmiSystemAudioModeChangeListener asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.hdmi.IHdmiSystemAudioModeChangeListener");
            if(iinterface != null && (iinterface instanceof IHdmiSystemAudioModeChangeListener))
                return (IHdmiSystemAudioModeChangeListener)iinterface;
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
                parcel1.writeString("android.hardware.hdmi.IHdmiSystemAudioModeChangeListener");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiSystemAudioModeChangeListener");
                break;
            }
            boolean flag;
            if(parcel.readInt() != 0)
                flag = true;
            else
                flag = false;
            onStatusChanged(flag);
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiSystemAudioModeChangeListener";
        static final int TRANSACTION_onStatusChanged = 1;

        public Stub()
        {
            attachInterface(this, "android.hardware.hdmi.IHdmiSystemAudioModeChangeListener");
        }
    }

    private static class Stub.Proxy
        implements IHdmiSystemAudioModeChangeListener
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.hdmi.IHdmiSystemAudioModeChangeListener";
        }

        public void onStatusChanged(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            i = 1;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiSystemAudioModeChangeListener");
            if(!flag)
                i = 0;
            parcel.writeInt(i);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void onStatusChanged(boolean flag)
        throws RemoteException;
}
