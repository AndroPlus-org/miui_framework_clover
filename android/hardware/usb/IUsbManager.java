// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.usb;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.*;

// Referenced classes of package android.hardware.usb:
//            UsbAccessory, UsbPortStatus, UsbPort, UsbDevice

public interface IUsbManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IUsbManager
    {

        public static IUsbManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.usb.IUsbManager");
            if(iinterface != null && (iinterface instanceof IUsbManager))
                return (IUsbManager)iinterface;
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
                parcel1.writeString("android.hardware.usb.IUsbManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                parcel = new Bundle();
                getDeviceList(parcel);
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

            case 2: // '\002'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                parcel = openDevice(parcel.readString());
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
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                parcel = getCurrentAccessory();
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

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                if(parcel.readInt() != 0)
                    parcel = (UsbAccessory)UsbAccessory.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = openAccessory(parcel);
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

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                UsbDevice usbdevice;
                if(parcel.readInt() != 0)
                    usbdevice = (UsbDevice)UsbDevice.CREATOR.createFromParcel(parcel);
                else
                    usbdevice = null;
                setDevicePackage(usbdevice, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                UsbAccessory usbaccessory;
                if(parcel.readInt() != 0)
                    usbaccessory = (UsbAccessory)UsbAccessory.CREATOR.createFromParcel(parcel);
                else
                    usbaccessory = null;
                setAccessoryPackage(usbaccessory, parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                boolean flag;
                if(parcel.readInt() != 0)
                    parcel = (UsbDevice)UsbDevice.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag = hasDevicePermission(parcel);
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                boolean flag1;
                if(parcel.readInt() != 0)
                    parcel = (UsbAccessory)UsbAccessory.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                flag1 = hasAccessoryPermission(parcel);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                UsbDevice usbdevice1;
                String s1;
                if(parcel.readInt() != 0)
                    usbdevice1 = (UsbDevice)UsbDevice.CREATOR.createFromParcel(parcel);
                else
                    usbdevice1 = null;
                s1 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestDevicePermission(usbdevice1, s1, parcel);
                parcel1.writeNoException();
                return true;

            case 10: // '\n'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                UsbAccessory usbaccessory1;
                String s2;
                if(parcel.readInt() != 0)
                    usbaccessory1 = (UsbAccessory)UsbAccessory.CREATOR.createFromParcel(parcel);
                else
                    usbaccessory1 = null;
                s2 = parcel.readString();
                if(parcel.readInt() != 0)
                    parcel = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                requestAccessoryPermission(usbaccessory1, s2, parcel);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                UsbDevice usbdevice2;
                if(parcel.readInt() != 0)
                    usbdevice2 = (UsbDevice)UsbDevice.CREATOR.createFromParcel(parcel);
                else
                    usbdevice2 = null;
                grantDevicePermission(usbdevice2, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                UsbAccessory usbaccessory2;
                if(parcel.readInt() != 0)
                    usbaccessory2 = (UsbAccessory)UsbAccessory.CREATOR.createFromParcel(parcel);
                else
                    usbaccessory2 = null;
                grantAccessoryPermission(usbaccessory2, parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                boolean flag2 = hasDefaults(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 14: // '\016'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                clearDefaults(parcel.readString(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                boolean flag3 = isFunctionEnabled(parcel.readString());
                parcel1.writeNoException();
                if(flag3)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                String s = parcel.readString();
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                setCurrentFunction(s, flag4);
                parcel1.writeNoException();
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                allowUsbDebugging(flag5, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                denyUsbDebugging();
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                clearUsbDebuggingKeys();
                parcel1.writeNoException();
                return true;

            case 20: // '\024'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                parcel = getPorts();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                parcel = getPortStatus(parcel.readString());
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

            case 22: // '\026'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                setPortRoles(parcel.readString(), parcel.readInt(), parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.hardware.usb.IUsbManager");
                break;
            }
            if(parcel.readInt() != 0)
                parcel = (ComponentName)ComponentName.CREATOR.createFromParcel(parcel);
            else
                parcel = null;
            setUsbDeviceConnectionHandler(parcel);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.usb.IUsbManager";
        static final int TRANSACTION_allowUsbDebugging = 17;
        static final int TRANSACTION_clearDefaults = 14;
        static final int TRANSACTION_clearUsbDebuggingKeys = 19;
        static final int TRANSACTION_denyUsbDebugging = 18;
        static final int TRANSACTION_getCurrentAccessory = 3;
        static final int TRANSACTION_getDeviceList = 1;
        static final int TRANSACTION_getPortStatus = 21;
        static final int TRANSACTION_getPorts = 20;
        static final int TRANSACTION_grantAccessoryPermission = 12;
        static final int TRANSACTION_grantDevicePermission = 11;
        static final int TRANSACTION_hasAccessoryPermission = 8;
        static final int TRANSACTION_hasDefaults = 13;
        static final int TRANSACTION_hasDevicePermission = 7;
        static final int TRANSACTION_isFunctionEnabled = 15;
        static final int TRANSACTION_openAccessory = 4;
        static final int TRANSACTION_openDevice = 2;
        static final int TRANSACTION_requestAccessoryPermission = 10;
        static final int TRANSACTION_requestDevicePermission = 9;
        static final int TRANSACTION_setAccessoryPackage = 6;
        static final int TRANSACTION_setCurrentFunction = 16;
        static final int TRANSACTION_setDevicePackage = 5;
        static final int TRANSACTION_setPortRoles = 22;
        static final int TRANSACTION_setUsbDeviceConnectionHandler = 23;

        public Stub()
        {
            attachInterface(this, "android.hardware.usb.IUsbManager");
        }
    }

    private static class Stub.Proxy
        implements IUsbManager
    {

        public void allowUsbDebugging(boolean flag, String s)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void clearDefaults(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void clearUsbDebuggingKeys()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
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

        public void denyUsbDebugging()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
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

        public UsbAccessory getCurrentAccessory()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            mRemote.transact(3, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            UsbAccessory usbaccessory = (UsbAccessory)UsbAccessory.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return usbaccessory;
_L2:
            usbaccessory = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void getDeviceList(Bundle bundle)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() != 0)
                bundle.readFromParcel(parcel1);
            parcel1.recycle();
            parcel.recycle();
            return;
            bundle;
            parcel1.recycle();
            parcel.recycle();
            throw bundle;
        }

        public String getInterfaceDescriptor()
        {
            return "android.hardware.usb.IUsbManager";
        }

        public UsbPortStatus getPortStatus(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            parcel.writeString(s);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (UsbPortStatus)UsbPortStatus.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public UsbPort[] getPorts()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            UsbPort ausbport[];
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            ausbport = (UsbPort[])parcel1.createTypedArray(UsbPort.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return ausbport;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void grantAccessoryPermission(UsbAccessory usbaccessory, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(usbaccessory == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            usbaccessory.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            usbaccessory;
            parcel1.recycle();
            parcel.recycle();
            throw usbaccessory;
        }

        public void grantDevicePermission(UsbDevice usbdevice, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(usbdevice == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            usbdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            usbdevice;
            parcel1.recycle();
            parcel.recycle();
            throw usbdevice;
        }

        public boolean hasAccessoryPermission(UsbAccessory usbaccessory)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(usbaccessory == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            usbaccessory.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(8, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            usbaccessory;
            parcel1.recycle();
            parcel.recycle();
            throw usbaccessory;
        }

        public boolean hasDefaults(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            parcel.writeString(s);
            parcel.writeInt(i);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public boolean hasDevicePermission(UsbDevice usbdevice)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(usbdevice == null)
                break MISSING_BLOCK_LABEL_73;
            parcel.writeInt(1);
            usbdevice.writeToParcel(parcel, 0);
_L1:
            int i;
            mRemote.transact(7, parcel, parcel1, 0);
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
            parcel.writeInt(0);
              goto _L1
            usbdevice;
            parcel1.recycle();
            parcel.recycle();
            throw usbdevice;
        }

        public boolean isFunctionEnabled(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            parcel.writeString(s);
            mRemote.transact(15, parcel, parcel1, 0);
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
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public ParcelFileDescriptor openAccessory(UsbAccessory usbaccessory)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(usbaccessory == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            usbaccessory.writeToParcel(parcel, 0);
_L3:
            mRemote.transact(4, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_96;
            usbaccessory = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return usbaccessory;
_L2:
            parcel.writeInt(0);
              goto _L3
            usbaccessory;
            parcel1.recycle();
            parcel.recycle();
            throw usbaccessory;
            usbaccessory = null;
              goto _L4
        }

        public ParcelFileDescriptor openDevice(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            parcel.writeString(s);
            mRemote.transact(2, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return s;
_L2:
            s = null;
            if(true) goto _L4; else goto _L3
_L3:
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void requestAccessoryPermission(UsbAccessory usbaccessory, String s, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(usbaccessory == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            usbaccessory.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            usbaccessory;
            parcel1.recycle();
            parcel.recycle();
            throw usbaccessory;
            parcel.writeInt(0);
              goto _L4
        }

        public void requestDevicePermission(UsbDevice usbdevice, String s, PendingIntent pendingintent)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(usbdevice == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            usbdevice.writeToParcel(parcel, 0);
_L3:
            parcel.writeString(s);
            if(pendingintent == null)
                break MISSING_BLOCK_LABEL_112;
            parcel.writeInt(1);
            pendingintent.writeToParcel(parcel, 0);
_L4:
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L3
            usbdevice;
            parcel1.recycle();
            parcel.recycle();
            throw usbdevice;
            parcel.writeInt(0);
              goto _L4
        }

        public void setAccessoryPackage(UsbAccessory usbaccessory, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(usbaccessory == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            usbaccessory.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            usbaccessory;
            parcel1.recycle();
            parcel.recycle();
            throw usbaccessory;
        }

        public void setCurrentFunction(String s, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            parcel.writeString(s);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setDevicePackage(UsbDevice usbdevice, String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(usbdevice == null)
                break MISSING_BLOCK_LABEL_78;
            parcel.writeInt(1);
            usbdevice.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            usbdevice;
            parcel1.recycle();
            parcel.recycle();
            throw usbdevice;
        }

        public void setPortRoles(String s, int i, int j)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            parcel.writeInt(j);
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void setUsbDeviceConnectionHandler(ComponentName componentname)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.usb.IUsbManager");
            if(componentname == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            componentname.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            componentname;
            parcel1.recycle();
            parcel.recycle();
            throw componentname;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void allowUsbDebugging(boolean flag, String s)
        throws RemoteException;

    public abstract void clearDefaults(String s, int i)
        throws RemoteException;

    public abstract void clearUsbDebuggingKeys()
        throws RemoteException;

    public abstract void denyUsbDebugging()
        throws RemoteException;

    public abstract UsbAccessory getCurrentAccessory()
        throws RemoteException;

    public abstract void getDeviceList(Bundle bundle)
        throws RemoteException;

    public abstract UsbPortStatus getPortStatus(String s)
        throws RemoteException;

    public abstract UsbPort[] getPorts()
        throws RemoteException;

    public abstract void grantAccessoryPermission(UsbAccessory usbaccessory, int i)
        throws RemoteException;

    public abstract void grantDevicePermission(UsbDevice usbdevice, int i)
        throws RemoteException;

    public abstract boolean hasAccessoryPermission(UsbAccessory usbaccessory)
        throws RemoteException;

    public abstract boolean hasDefaults(String s, int i)
        throws RemoteException;

    public abstract boolean hasDevicePermission(UsbDevice usbdevice)
        throws RemoteException;

    public abstract boolean isFunctionEnabled(String s)
        throws RemoteException;

    public abstract ParcelFileDescriptor openAccessory(UsbAccessory usbaccessory)
        throws RemoteException;

    public abstract ParcelFileDescriptor openDevice(String s)
        throws RemoteException;

    public abstract void requestAccessoryPermission(UsbAccessory usbaccessory, String s, PendingIntent pendingintent)
        throws RemoteException;

    public abstract void requestDevicePermission(UsbDevice usbdevice, String s, PendingIntent pendingintent)
        throws RemoteException;

    public abstract void setAccessoryPackage(UsbAccessory usbaccessory, String s, int i)
        throws RemoteException;

    public abstract void setCurrentFunction(String s, boolean flag)
        throws RemoteException;

    public abstract void setDevicePackage(UsbDevice usbdevice, String s, int i)
        throws RemoteException;

    public abstract void setPortRoles(String s, int i, int j)
        throws RemoteException;

    public abstract void setUsbDeviceConnectionHandler(ComponentName componentname)
        throws RemoteException;
}
