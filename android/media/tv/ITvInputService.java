// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.tv;

import android.hardware.hdmi.HdmiDeviceInfo;
import android.os.*;
import android.view.InputChannel;

// Referenced classes of package android.media.tv:
//            ITvInputSessionCallback, TvInputHardwareInfo, ITvInputServiceCallback

public interface ITvInputService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements ITvInputService
    {

        public static ITvInputService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.media.tv.ITvInputService");
            if(iinterface != null && (iinterface instanceof ITvInputService))
                return (ITvInputService)iinterface;
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
                parcel1.writeString("android.media.tv.ITvInputService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.media.tv.ITvInputService");
                registerCallback(ITvInputServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.media.tv.ITvInputService");
                unregisterCallback(ITvInputServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.media.tv.ITvInputService");
                if(parcel.readInt() != 0)
                    parcel1 = (InputChannel)InputChannel.CREATOR.createFromParcel(parcel);
                else
                    parcel1 = null;
                createSession(parcel1, ITvInputSessionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.media.tv.ITvInputService");
                createRecordingSession(ITvInputSessionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.media.tv.ITvInputService");
                if(parcel.readInt() != 0)
                    parcel = (TvInputHardwareInfo)TvInputHardwareInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyHardwareAdded(parcel);
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.media.tv.ITvInputService");
                if(parcel.readInt() != 0)
                    parcel = (TvInputHardwareInfo)TvInputHardwareInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyHardwareRemoved(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.media.tv.ITvInputService");
                if(parcel.readInt() != 0)
                    parcel = (HdmiDeviceInfo)HdmiDeviceInfo.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                notifyHdmiDeviceAdded(parcel);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.media.tv.ITvInputService");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (HdmiDeviceInfo)HdmiDeviceInfo.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            notifyHdmiDeviceRemoved(parcel);
            return true;
        }

        private static final String DESCRIPTOR = "android.media.tv.ITvInputService";
        static final int TRANSACTION_createRecordingSession = 4;
        static final int TRANSACTION_createSession = 3;
        static final int TRANSACTION_notifyHardwareAdded = 5;
        static final int TRANSACTION_notifyHardwareRemoved = 6;
        static final int TRANSACTION_notifyHdmiDeviceAdded = 7;
        static final int TRANSACTION_notifyHdmiDeviceRemoved = 8;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unregisterCallback = 2;

        public Stub()
        {
            attachInterface(this, "android.media.tv.ITvInputService");
        }
    }

    private static class Stub.Proxy
        implements ITvInputService
    {

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void createRecordingSession(ITvInputSessionCallback itvinputsessioncallback, String s)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputService");
            if(itvinputsessioncallback == null)
                break MISSING_BLOCK_LABEL_25;
            ibinder = itvinputsessioncallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeString(s);
            mRemote.transact(4, parcel, null, 1);
            parcel.recycle();
            return;
            itvinputsessioncallback;
            parcel.recycle();
            throw itvinputsessioncallback;
        }

        public void createSession(InputChannel inputchannel, ITvInputSessionCallback itvinputsessioncallback, String s)
            throws RemoteException
        {
            Object obj;
            Parcel parcel;
            obj = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputService");
            if(inputchannel == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            inputchannel.writeToParcel(parcel, 0);
_L4:
            inputchannel = obj;
            if(itvinputsessioncallback == null)
                break MISSING_BLOCK_LABEL_46;
            inputchannel = itvinputsessioncallback.asBinder();
            parcel.writeStrongBinder(inputchannel);
            parcel.writeString(s);
            mRemote.transact(3, parcel, null, 1);
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
            if(true) goto _L4; else goto _L3
_L3:
            inputchannel;
            parcel.recycle();
            throw inputchannel;
        }

        public String getInterfaceDescriptor()
        {
            return "android.media.tv.ITvInputService";
        }

        public void notifyHardwareAdded(TvInputHardwareInfo tvinputhardwareinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputService");
            if(tvinputhardwareinfo == null)
                break MISSING_BLOCK_LABEL_44;
            parcel.writeInt(1);
            tvinputhardwareinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(5, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            tvinputhardwareinfo;
            parcel.recycle();
            throw tvinputhardwareinfo;
        }

        public void notifyHardwareRemoved(TvInputHardwareInfo tvinputhardwareinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputService");
            if(tvinputhardwareinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            tvinputhardwareinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(6, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            tvinputhardwareinfo;
            parcel.recycle();
            throw tvinputhardwareinfo;
        }

        public void notifyHdmiDeviceAdded(HdmiDeviceInfo hdmideviceinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputService");
            if(hdmideviceinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            hdmideviceinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(7, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            hdmideviceinfo;
            parcel.recycle();
            throw hdmideviceinfo;
        }

        public void notifyHdmiDeviceRemoved(HdmiDeviceInfo hdmideviceinfo)
            throws RemoteException
        {
            Parcel parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputService");
            if(hdmideviceinfo == null)
                break MISSING_BLOCK_LABEL_45;
            parcel.writeInt(1);
            hdmideviceinfo.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(8, parcel, null, 1);
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            hdmideviceinfo;
            parcel.recycle();
            throw hdmideviceinfo;
        }

        public void registerCallback(ITvInputServiceCallback itvinputservicecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputService");
            if(itvinputservicecallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = itvinputservicecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(1, parcel, null, 1);
            parcel.recycle();
            return;
            itvinputservicecallback;
            parcel.recycle();
            throw itvinputservicecallback;
        }

        public void unregisterCallback(ITvInputServiceCallback itvinputservicecallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel.writeInterfaceToken("android.media.tv.ITvInputService");
            if(itvinputservicecallback == null)
                break MISSING_BLOCK_LABEL_23;
            ibinder = itvinputservicecallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(2, parcel, null, 1);
            parcel.recycle();
            return;
            itvinputservicecallback;
            parcel.recycle();
            throw itvinputservicecallback;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void createRecordingSession(ITvInputSessionCallback itvinputsessioncallback, String s)
        throws RemoteException;

    public abstract void createSession(InputChannel inputchannel, ITvInputSessionCallback itvinputsessioncallback, String s)
        throws RemoteException;

    public abstract void notifyHardwareAdded(TvInputHardwareInfo tvinputhardwareinfo)
        throws RemoteException;

    public abstract void notifyHardwareRemoved(TvInputHardwareInfo tvinputhardwareinfo)
        throws RemoteException;

    public abstract void notifyHdmiDeviceAdded(HdmiDeviceInfo hdmideviceinfo)
        throws RemoteException;

    public abstract void notifyHdmiDeviceRemoved(HdmiDeviceInfo hdmideviceinfo)
        throws RemoteException;

    public abstract void registerCallback(ITvInputServiceCallback itvinputservicecallback)
        throws RemoteException;

    public abstract void unregisterCallback(ITvInputServiceCallback itvinputservicecallback)
        throws RemoteException;
}
