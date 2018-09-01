// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.hdmi;

import android.os.*;
import java.util.List;

// Referenced classes of package android.hardware.hdmi:
//            IHdmiDeviceEventListener, IHdmiMhlVendorCommandListener, IHdmiHotplugEventListener, IHdmiSystemAudioModeChangeListener, 
//            IHdmiVendorCommandListener, IHdmiControlCallback, HdmiDeviceInfo, IHdmiRecordListener, 
//            IHdmiInputChangeListener, HdmiPortInfo

public interface IHdmiControlService
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IHdmiControlService
    {

        public static IHdmiControlService asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.hdmi.IHdmiControlService");
            if(iinterface != null && (iinterface instanceof IHdmiControlService))
                return (IHdmiControlService)iinterface;
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
                parcel1.writeString("android.hardware.hdmi.IHdmiControlService");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                parcel = getSupportedTypes();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                parcel = getActiveSource();
                parcel1.writeNoException();
                if(parcel != null)
                {
                    parcel1.writeInt(1);
                    parcel.writeToParcel(parcel1, 1);
                } else
                {
                    parcel1.writeInt(0);
                }
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                oneTouchPlay(IHdmiControlCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                queryDisplayStatus(IHdmiControlCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                addHotplugEventListener(IHdmiHotplugEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                removeHotplugEventListener(IHdmiHotplugEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                addDeviceEventListener(IHdmiDeviceEventListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                deviceSelect(parcel.readInt(), IHdmiControlCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                portSelect(parcel.readInt(), IHdmiControlCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                j = parcel.readInt();
                i = parcel.readInt();
                boolean flag;
                if(parcel.readInt() != 0)
                    flag = true;
                else
                    flag = false;
                sendKeyEvent(j, i, flag);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                parcel = getPortInfo();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                boolean flag1 = canChangeSystemAudioMode();
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                boolean flag2 = getSystemAudioMode();
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                setSystemAudioMode(flag3, IHdmiControlCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setArcMode(flag4);
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                setProhibitMode(flag5);
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                setSystemAudioVolume(parcel.readInt(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                setSystemAudioMute(flag6);
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                setInputChangeListener(IHdmiInputChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                parcel = getInputDevices();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                parcel = getDeviceList();
                parcel1.writeNoException();
                parcel1.writeTypedList(parcel);
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                j = parcel.readInt();
                i = parcel.readInt();
                byte abyte0[] = parcel.createByteArray();
                boolean flag7;
                if(parcel.readInt() != 0)
                    flag7 = true;
                else
                    flag7 = false;
                sendVendorCommand(j, i, abyte0, flag7);
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                addVendorCommandListener(IHdmiVendorCommandListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                sendStandby(parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                setHdmiRecordListener(IHdmiRecordListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                startOneTouchRecord(parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                stopOneTouchRecord(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                startTimerRecording(parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                clearTimerRecording(parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                sendMhlVendorCommand(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 34: // '"'
                parcel.enforceInterface("android.hardware.hdmi.IHdmiControlService");
                break;
            }
            boolean flag8;
            if(parcel.readInt() != 0)
                flag8 = true;
            else
                flag8 = false;
            setStandbyMode(flag8);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.hdmi.IHdmiControlService";
        static final int TRANSACTION_addDeviceEventListener = 7;
        static final int TRANSACTION_addHdmiMhlVendorCommandListener = 33;
        static final int TRANSACTION_addHotplugEventListener = 5;
        static final int TRANSACTION_addSystemAudioModeChangeListener = 15;
        static final int TRANSACTION_addVendorCommandListener = 25;
        static final int TRANSACTION_canChangeSystemAudioMode = 12;
        static final int TRANSACTION_clearTimerRecording = 31;
        static final int TRANSACTION_deviceSelect = 8;
        static final int TRANSACTION_getActiveSource = 2;
        static final int TRANSACTION_getDeviceList = 23;
        static final int TRANSACTION_getInputDevices = 22;
        static final int TRANSACTION_getPortInfo = 11;
        static final int TRANSACTION_getSupportedTypes = 1;
        static final int TRANSACTION_getSystemAudioMode = 13;
        static final int TRANSACTION_oneTouchPlay = 3;
        static final int TRANSACTION_portSelect = 9;
        static final int TRANSACTION_queryDisplayStatus = 4;
        static final int TRANSACTION_removeHotplugEventListener = 6;
        static final int TRANSACTION_removeSystemAudioModeChangeListener = 16;
        static final int TRANSACTION_sendKeyEvent = 10;
        static final int TRANSACTION_sendMhlVendorCommand = 32;
        static final int TRANSACTION_sendStandby = 26;
        static final int TRANSACTION_sendVendorCommand = 24;
        static final int TRANSACTION_setArcMode = 17;
        static final int TRANSACTION_setHdmiRecordListener = 27;
        static final int TRANSACTION_setInputChangeListener = 21;
        static final int TRANSACTION_setProhibitMode = 18;
        static final int TRANSACTION_setStandbyMode = 34;
        static final int TRANSACTION_setSystemAudioMode = 14;
        static final int TRANSACTION_setSystemAudioMute = 20;
        static final int TRANSACTION_setSystemAudioVolume = 19;
        static final int TRANSACTION_startOneTouchRecord = 28;
        static final int TRANSACTION_startTimerRecording = 30;
        static final int TRANSACTION_stopOneTouchRecord = 29;

        public Stub()
        {
            attachInterface(this, "android.hardware.hdmi.IHdmiControlService");
        }
    }

    private static class Stub.Proxy
        implements IHdmiControlService
    {

        public void addDeviceEventListener(IHdmiDeviceEventListener ihdmideviceeventlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmideviceeventlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ihdmideviceeventlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(7, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmideviceeventlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmideviceeventlistener;
        }

        public void addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener ihdmimhlvendorcommandlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmimhlvendorcommandlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ihdmimhlvendorcommandlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(33, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmimhlvendorcommandlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmimhlvendorcommandlistener;
        }

        public void addHotplugEventListener(IHdmiHotplugEventListener ihdmihotplugeventlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmihotplugeventlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ihdmihotplugeventlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmihotplugeventlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmihotplugeventlistener;
        }

        public void addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener ihdmisystemaudiomodechangelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmisystemaudiomodechangelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ihdmisystemaudiomodechangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmisystemaudiomodechangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmisystemaudiomodechangelistener;
        }

        public void addVendorCommandListener(IHdmiVendorCommandListener ihdmivendorcommandlistener, int i)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmivendorcommandlistener == null)
                break MISSING_BLOCK_LABEL_30;
            ibinder = ihdmivendorcommandlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            parcel.writeInt(i);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmivendorcommandlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmivendorcommandlistener;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public boolean canChangeSystemAudioMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void clearTimerRecording(int i, int j, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            mRemote.transact(31, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void deviceSelect(int i, IHdmiControlCallback ihdmicontrolcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            if(ihdmicontrolcallback == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = ihdmicontrolcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(8, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmicontrolcallback;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmicontrolcallback;
        }

        public HdmiDeviceInfo getActiveSource()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            HdmiDeviceInfo hdmideviceinfo = (HdmiDeviceInfo)HdmiDeviceInfo.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return hdmideviceinfo;
_L2:
            hdmideviceinfo = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getDeviceList()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(HdmiDeviceInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public List getInputDevices()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(HdmiDeviceInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.hdmi.IHdmiControlService";
        }

        public List getPortInfo()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            java.util.ArrayList arraylist;
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            arraylist = parcel1.createTypedArrayList(HdmiPortInfo.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return arraylist;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int[] getSupportedTypes()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            ai = parcel1.createIntArray();
            parcel1.recycle();
            parcel.recycle();
            return ai;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean getSystemAudioMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            boolean flag;
            if(i != 0)
                flag = true;
            else
                flag = false;
            parcel1.recycle();
            parcel.recycle();
            return flag;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void oneTouchPlay(IHdmiControlCallback ihdmicontrolcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmicontrolcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ihdmicontrolcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmicontrolcallback;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmicontrolcallback;
        }

        public void portSelect(int i, IHdmiControlCallback ihdmicontrolcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            if(ihdmicontrolcallback == null)
                break MISSING_BLOCK_LABEL_36;
            ibinder = ihdmicontrolcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmicontrolcallback;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmicontrolcallback;
        }

        public void queryDisplayStatus(IHdmiControlCallback ihdmicontrolcallback)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmicontrolcallback == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ihdmicontrolcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmicontrolcallback;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmicontrolcallback;
        }

        public void removeHotplugEventListener(IHdmiHotplugEventListener ihdmihotplugeventlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmihotplugeventlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ihdmihotplugeventlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmihotplugeventlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmihotplugeventlistener;
        }

        public void removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener ihdmisystemaudiomodechangelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmisystemaudiomodechangelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ihdmisystemaudiomodechangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmisystemaudiomodechangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmisystemaudiomodechangelistener;
        }

        public void sendKeyEvent(int i, int j, boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void sendMhlVendorCommand(int i, int j, int k, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            parcel.writeByteArray(abyte0);
            mRemote.transact(32, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void sendStandby(int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(26, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void sendVendorCommand(int i, int j, byte abyte0[], boolean flag)
            throws RemoteException
        {
            boolean flag1;
            Parcel parcel;
            Parcel parcel1;
            flag1 = false;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            i = ((flag1) ? 1 : 0);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void setArcMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setHdmiRecordListener(IHdmiRecordListener ihdmirecordlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmirecordlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ihdmirecordlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmirecordlistener;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmirecordlistener;
        }

        public void setInputChangeListener(IHdmiInputChangeListener ihdmiinputchangelistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(ihdmiinputchangelistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = ihdmiinputchangelistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmiinputchangelistener;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmiinputchangelistener;
        }

        public void setProhibitMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setStandbyMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(34, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setSystemAudioMode(boolean flag, IHdmiControlCallback ihdmicontrolcallback)
            throws RemoteException
        {
            IBinder ibinder;
            int i;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            if(ihdmicontrolcallback == null)
                break MISSING_BLOCK_LABEL_47;
            ibinder = ihdmicontrolcallback.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ihdmicontrolcallback;
            parcel1.recycle();
            parcel.recycle();
            throw ihdmicontrolcallback;
        }

        public void setSystemAudioMute(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void setSystemAudioVolume(int i, int j, int k)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeInt(k);
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void startOneTouchRecord(int i, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            parcel.writeByteArray(abyte0);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void startTimerRecording(int i, int j, byte abyte0[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeByteArray(abyte0);
            mRemote.transact(30, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            abyte0;
            parcel1.recycle();
            parcel.recycle();
            throw abyte0;
        }

        public void stopOneTouchRecord(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.hdmi.IHdmiControlService");
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addDeviceEventListener(IHdmiDeviceEventListener ihdmideviceeventlistener)
        throws RemoteException;

    public abstract void addHdmiMhlVendorCommandListener(IHdmiMhlVendorCommandListener ihdmimhlvendorcommandlistener)
        throws RemoteException;

    public abstract void addHotplugEventListener(IHdmiHotplugEventListener ihdmihotplugeventlistener)
        throws RemoteException;

    public abstract void addSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener ihdmisystemaudiomodechangelistener)
        throws RemoteException;

    public abstract void addVendorCommandListener(IHdmiVendorCommandListener ihdmivendorcommandlistener, int i)
        throws RemoteException;

    public abstract boolean canChangeSystemAudioMode()
        throws RemoteException;

    public abstract void clearTimerRecording(int i, int j, byte abyte0[])
        throws RemoteException;

    public abstract void deviceSelect(int i, IHdmiControlCallback ihdmicontrolcallback)
        throws RemoteException;

    public abstract HdmiDeviceInfo getActiveSource()
        throws RemoteException;

    public abstract List getDeviceList()
        throws RemoteException;

    public abstract List getInputDevices()
        throws RemoteException;

    public abstract List getPortInfo()
        throws RemoteException;

    public abstract int[] getSupportedTypes()
        throws RemoteException;

    public abstract boolean getSystemAudioMode()
        throws RemoteException;

    public abstract void oneTouchPlay(IHdmiControlCallback ihdmicontrolcallback)
        throws RemoteException;

    public abstract void portSelect(int i, IHdmiControlCallback ihdmicontrolcallback)
        throws RemoteException;

    public abstract void queryDisplayStatus(IHdmiControlCallback ihdmicontrolcallback)
        throws RemoteException;

    public abstract void removeHotplugEventListener(IHdmiHotplugEventListener ihdmihotplugeventlistener)
        throws RemoteException;

    public abstract void removeSystemAudioModeChangeListener(IHdmiSystemAudioModeChangeListener ihdmisystemaudiomodechangelistener)
        throws RemoteException;

    public abstract void sendKeyEvent(int i, int j, boolean flag)
        throws RemoteException;

    public abstract void sendMhlVendorCommand(int i, int j, int k, byte abyte0[])
        throws RemoteException;

    public abstract void sendStandby(int i, int j)
        throws RemoteException;

    public abstract void sendVendorCommand(int i, int j, byte abyte0[], boolean flag)
        throws RemoteException;

    public abstract void setArcMode(boolean flag)
        throws RemoteException;

    public abstract void setHdmiRecordListener(IHdmiRecordListener ihdmirecordlistener)
        throws RemoteException;

    public abstract void setInputChangeListener(IHdmiInputChangeListener ihdmiinputchangelistener)
        throws RemoteException;

    public abstract void setProhibitMode(boolean flag)
        throws RemoteException;

    public abstract void setStandbyMode(boolean flag)
        throws RemoteException;

    public abstract void setSystemAudioMode(boolean flag, IHdmiControlCallback ihdmicontrolcallback)
        throws RemoteException;

    public abstract void setSystemAudioMute(boolean flag)
        throws RemoteException;

    public abstract void setSystemAudioVolume(int i, int j, int k)
        throws RemoteException;

    public abstract void startOneTouchRecord(int i, byte abyte0[])
        throws RemoteException;

    public abstract void startTimerRecording(int i, int j, byte abyte0[])
        throws RemoteException;

    public abstract void stopOneTouchRecord(int i)
        throws RemoteException;
}
