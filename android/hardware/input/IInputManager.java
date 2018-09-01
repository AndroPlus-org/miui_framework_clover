// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.input;

import android.app.IInputForwarder;
import android.os.*;
import android.view.*;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;

// Referenced classes of package android.hardware.input:
//            InputDeviceIdentifier, KeyboardLayout, TouchCalibration, IInputDevicesChangedListener, 
//            ITabletModeChangedListener

public interface IInputManager
    extends IInterface
{
    public static abstract class Stub extends Binder
        implements IInputManager
    {

        public static IInputManager asInterface(IBinder ibinder)
        {
            if(ibinder == null)
                return null;
            IInterface iinterface = ibinder.queryLocalInterface("android.hardware.input.IInputManager");
            if(iinterface != null && (iinterface instanceof IInputManager))
                return (IInputManager)iinterface;
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
                parcel1.writeString("android.hardware.input.IInputManager");
                return true;

            case 1: // '\001'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                parcel = getInputDevice(parcel.readInt());
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
                parcel.enforceInterface("android.hardware.input.IInputManager");
                parcel = getInputDeviceIds();
                parcel1.writeNoException();
                parcel1.writeIntArray(parcel);
                return true;

            case 3: // '\003'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                boolean flag = isInputDeviceEnabled(parcel.readInt());
                parcel1.writeNoException();
                if(flag)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 4: // '\004'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                enableInputDevice(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 5: // '\005'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                disableInputDevice(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 6: // '\006'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                i = parcel.readInt();
                int k = parcel.readInt();
                int ai[] = parcel.createIntArray();
                j = parcel.readInt();
                boolean flag1;
                if(j < 0)
                    parcel = null;
                else
                    parcel = new boolean[j];
                flag1 = hasKeys(i, k, ai, parcel);
                parcel1.writeNoException();
                if(flag1)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                parcel1.writeBooleanArray(parcel);
                return true;

            case 7: // '\007'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                tryPointerSpeed(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 8: // '\b'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                boolean flag2;
                InputEvent inputevent;
                if(parcel.readInt() != 0)
                    inputevent = (InputEvent)InputEvent.CREATOR.createFromParcel(parcel);
                else
                    inputevent = null;
                flag2 = injectInputEvent(inputevent, parcel.readInt());
                parcel1.writeNoException();
                if(flag2)
                    i = 1;
                else
                    i = 0;
                parcel1.writeInt(i);
                return true;

            case 9: // '\t'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                parcel = getTouchCalibrationForInputDevice(parcel.readString(), parcel.readInt());
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

            case 10: // '\n'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                String s = parcel.readString();
                i = parcel.readInt();
                if(parcel.readInt() != 0)
                    parcel = (TouchCalibration)TouchCalibration.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setTouchCalibrationForInputDevice(s, i, parcel);
                parcel1.writeNoException();
                return true;

            case 11: // '\013'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                parcel = getKeyboardLayouts();
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 12: // '\f'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                if(parcel.readInt() != 0)
                    parcel = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getKeyboardLayoutsForInputDevice(parcel);
                parcel1.writeNoException();
                parcel1.writeTypedArray(parcel, 1);
                return true;

            case 13: // '\r'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                parcel = getKeyboardLayout(parcel.readString());
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

            case 14: // '\016'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                if(parcel.readInt() != 0)
                    parcel = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getCurrentKeyboardLayoutForInputDevice(parcel);
                parcel1.writeNoException();
                parcel1.writeString(parcel);
                return true;

            case 15: // '\017'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                InputDeviceIdentifier inputdeviceidentifier;
                if(parcel.readInt() != 0)
                    inputdeviceidentifier = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel(parcel);
                else
                    inputdeviceidentifier = null;
                setCurrentKeyboardLayoutForInputDevice(inputdeviceidentifier, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 16: // '\020'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                if(parcel.readInt() != 0)
                    parcel = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getEnabledKeyboardLayoutsForInputDevice(parcel);
                parcel1.writeNoException();
                parcel1.writeStringArray(parcel);
                return true;

            case 17: // '\021'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                InputDeviceIdentifier inputdeviceidentifier1;
                if(parcel.readInt() != 0)
                    inputdeviceidentifier1 = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel(parcel);
                else
                    inputdeviceidentifier1 = null;
                addKeyboardLayoutForInputDevice(inputdeviceidentifier1, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 18: // '\022'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                InputDeviceIdentifier inputdeviceidentifier2;
                if(parcel.readInt() != 0)
                    inputdeviceidentifier2 = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel(parcel);
                else
                    inputdeviceidentifier2 = null;
                removeKeyboardLayoutForInputDevice(inputdeviceidentifier2, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 19: // '\023'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                InputDeviceIdentifier inputdeviceidentifier3;
                InputMethodInfo inputmethodinfo;
                if(parcel.readInt() != 0)
                    inputdeviceidentifier3 = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel(parcel);
                else
                    inputdeviceidentifier3 = null;
                if(parcel.readInt() != 0)
                    inputmethodinfo = (InputMethodInfo)InputMethodInfo.CREATOR.createFromParcel(parcel);
                else
                    inputmethodinfo = null;
                if(parcel.readInt() != 0)
                    parcel = (InputMethodSubtype)InputMethodSubtype.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                parcel = getKeyboardLayoutForInputDevice(inputdeviceidentifier3, inputmethodinfo, parcel);
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

            case 20: // '\024'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                InputDeviceIdentifier inputdeviceidentifier4;
                InputMethodInfo inputmethodinfo1;
                InputMethodSubtype inputmethodsubtype;
                if(parcel.readInt() != 0)
                    inputdeviceidentifier4 = (InputDeviceIdentifier)InputDeviceIdentifier.CREATOR.createFromParcel(parcel);
                else
                    inputdeviceidentifier4 = null;
                if(parcel.readInt() != 0)
                    inputmethodinfo1 = (InputMethodInfo)InputMethodInfo.CREATOR.createFromParcel(parcel);
                else
                    inputmethodinfo1 = null;
                if(parcel.readInt() != 0)
                    inputmethodsubtype = (InputMethodSubtype)InputMethodSubtype.CREATOR.createFromParcel(parcel);
                else
                    inputmethodsubtype = null;
                setKeyboardLayoutForInputDevice(inputdeviceidentifier4, inputmethodinfo1, inputmethodsubtype, parcel.readString());
                parcel1.writeNoException();
                return true;

            case 21: // '\025'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                registerInputDevicesChangedListener(IInputDevicesChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 22: // '\026'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                i = isInTabletMode();
                parcel1.writeNoException();
                parcel1.writeInt(i);
                return true;

            case 23: // '\027'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                registerTabletModeChangedListener(ITabletModeChangedListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel1.writeNoException();
                return true;

            case 24: // '\030'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                vibrate(parcel.readInt(), parcel.createLongArray(), parcel.readInt(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 25: // '\031'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                cancelVibrate(parcel.readInt(), parcel.readStrongBinder());
                parcel1.writeNoException();
                return true;

            case 26: // '\032'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                setPointerIconType(parcel.readInt());
                parcel1.writeNoException();
                return true;

            case 27: // '\033'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                if(parcel.readInt() != 0)
                    parcel = (PointerIcon)PointerIcon.CREATOR.createFromParcel(parcel);
                else
                    parcel = null;
                setCustomPointerIcon(parcel);
                parcel1.writeNoException();
                return true;

            case 28: // '\034'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                IBinder ibinder = parcel.readStrongBinder();
                boolean flag3;
                if(parcel.readInt() != 0)
                    flag3 = true;
                else
                    flag3 = false;
                requestPointerCapture(ibinder, flag3);
                parcel1.writeNoException();
                return true;

            case 29: // '\035'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                parcel = createInputForwarder(parcel.readInt());
                parcel1.writeNoException();
                if(parcel != null)
                    parcel = parcel.asBinder();
                else
                    parcel = null;
                parcel1.writeStrongBinder(parcel);
                return true;

            case 30: // '\036'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                boolean flag4;
                if(parcel.readInt() != 0)
                    flag4 = true;
                else
                    flag4 = false;
                switchTouchSensitiveMode(flag4);
                parcel1.writeNoException();
                return true;

            case 31: // '\037'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                boolean flag5;
                if(parcel.readInt() != 0)
                    flag5 = true;
                else
                    flag5 = false;
                switchTouchStylusMode(flag5);
                parcel1.writeNoException();
                return true;

            case 32: // ' '
                parcel.enforceInterface("android.hardware.input.IInputManager");
                boolean flag6;
                if(parcel.readInt() != 0)
                    flag6 = true;
                else
                    flag6 = false;
                switchTouchWakeupMode(flag6);
                parcel1.writeNoException();
                return true;

            case 33: // '!'
                parcel.enforceInterface("android.hardware.input.IInputManager");
                break;
            }
            boolean flag7;
            if(parcel.readInt() != 0)
                flag7 = true;
            else
                flag7 = false;
            switchTouchCoverMode(flag7);
            parcel1.writeNoException();
            return true;
        }

        private static final String DESCRIPTOR = "android.hardware.input.IInputManager";
        static final int TRANSACTION_addKeyboardLayoutForInputDevice = 17;
        static final int TRANSACTION_cancelVibrate = 25;
        static final int TRANSACTION_createInputForwarder = 29;
        static final int TRANSACTION_disableInputDevice = 5;
        static final int TRANSACTION_enableInputDevice = 4;
        static final int TRANSACTION_getCurrentKeyboardLayoutForInputDevice = 14;
        static final int TRANSACTION_getEnabledKeyboardLayoutsForInputDevice = 16;
        static final int TRANSACTION_getInputDevice = 1;
        static final int TRANSACTION_getInputDeviceIds = 2;
        static final int TRANSACTION_getKeyboardLayout = 13;
        static final int TRANSACTION_getKeyboardLayoutForInputDevice = 19;
        static final int TRANSACTION_getKeyboardLayouts = 11;
        static final int TRANSACTION_getKeyboardLayoutsForInputDevice = 12;
        static final int TRANSACTION_getTouchCalibrationForInputDevice = 9;
        static final int TRANSACTION_hasKeys = 6;
        static final int TRANSACTION_injectInputEvent = 8;
        static final int TRANSACTION_isInTabletMode = 22;
        static final int TRANSACTION_isInputDeviceEnabled = 3;
        static final int TRANSACTION_registerInputDevicesChangedListener = 21;
        static final int TRANSACTION_registerTabletModeChangedListener = 23;
        static final int TRANSACTION_removeKeyboardLayoutForInputDevice = 18;
        static final int TRANSACTION_requestPointerCapture = 28;
        static final int TRANSACTION_setCurrentKeyboardLayoutForInputDevice = 15;
        static final int TRANSACTION_setCustomPointerIcon = 27;
        static final int TRANSACTION_setKeyboardLayoutForInputDevice = 20;
        static final int TRANSACTION_setPointerIconType = 26;
        static final int TRANSACTION_setTouchCalibrationForInputDevice = 10;
        static final int TRANSACTION_switchTouchCoverMode = 33;
        static final int TRANSACTION_switchTouchSensitiveMode = 30;
        static final int TRANSACTION_switchTouchStylusMode = 31;
        static final int TRANSACTION_switchTouchWakeupMode = 32;
        static final int TRANSACTION_tryPointerSpeed = 7;
        static final int TRANSACTION_vibrate = 24;

        public Stub()
        {
            attachInterface(this, "android.hardware.input.IInputManager");
        }
    }

    private static class Stub.Proxy
        implements IInputManager
    {

        public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(inputdeviceidentifier == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            inputdeviceidentifier.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(17, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            inputdeviceidentifier;
            parcel1.recycle();
            parcel.recycle();
            throw inputdeviceidentifier;
        }

        public IBinder asBinder()
        {
            return mRemote;
        }

        public void cancelVibrate(int i, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeInt(i);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(25, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public IInputForwarder createInputForwarder(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            IInputForwarder iinputforwarder;
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeInt(i);
            mRemote.transact(29, parcel, parcel1, 0);
            parcel1.readException();
            iinputforwarder = android.app.IInputForwarder.Stub.asInterface(parcel1.readStrongBinder());
            parcel1.recycle();
            parcel.recycle();
            return iinputforwarder;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public void disableInputDevice(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeInt(i);
            mRemote.transact(5, parcel, parcel1, 0);
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

        public void enableInputDevice(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeInt(i);
            mRemote.transact(4, parcel, parcel1, 0);
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

        public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(inputdeviceidentifier == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            inputdeviceidentifier.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(14, parcel, parcel1, 0);
            parcel1.readException();
            inputdeviceidentifier = parcel1.readString();
            parcel1.recycle();
            parcel.recycle();
            return inputdeviceidentifier;
            parcel.writeInt(0);
              goto _L1
            inputdeviceidentifier;
            parcel1.recycle();
            parcel.recycle();
            throw inputdeviceidentifier;
        }

        public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputdeviceidentifier)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(inputdeviceidentifier == null)
                break MISSING_BLOCK_LABEL_63;
            parcel.writeInt(1);
            inputdeviceidentifier.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(16, parcel, parcel1, 0);
            parcel1.readException();
            inputdeviceidentifier = parcel1.createStringArray();
            parcel1.recycle();
            parcel.recycle();
            return inputdeviceidentifier;
            parcel.writeInt(0);
              goto _L1
            inputdeviceidentifier;
            parcel1.recycle();
            parcel.recycle();
            throw inputdeviceidentifier;
        }

        public InputDevice getInputDevice(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeInt(i);
            mRemote.transact(1, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            InputDevice inputdevice = (InputDevice)InputDevice.CREATOR.createFromParcel(parcel1);
_L4:
            parcel1.recycle();
            parcel.recycle();
            return inputdevice;
_L2:
            inputdevice = null;
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public int[] getInputDeviceIds()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int ai[];
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            mRemote.transact(2, parcel, parcel1, 0);
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

        public String getInterfaceDescriptor()
        {
            return "android.hardware.input.IInputManager";
        }

        public KeyboardLayout getKeyboardLayout(String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeString(s);
            mRemote.transact(13, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (KeyboardLayout)KeyboardLayout.CREATOR.createFromParcel(parcel1);
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

        public KeyboardLayout getKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(inputdeviceidentifier == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            inputdeviceidentifier.writeToParcel(parcel, 0);
_L7:
            if(inputmethodinfo == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            inputmethodinfo.writeToParcel(parcel, 0);
_L8:
            if(inputmethodsubtype == null) goto _L6; else goto _L5
_L5:
            parcel.writeInt(1);
            inputmethodsubtype.writeToParcel(parcel, 0);
_L9:
            mRemote.transact(19, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0)
                break MISSING_BLOCK_LABEL_164;
            inputdeviceidentifier = (KeyboardLayout)KeyboardLayout.CREATOR.createFromParcel(parcel1);
_L10:
            parcel1.recycle();
            parcel.recycle();
            return inputdeviceidentifier;
_L2:
            parcel.writeInt(0);
              goto _L7
            inputdeviceidentifier;
            parcel1.recycle();
            parcel.recycle();
            throw inputdeviceidentifier;
_L4:
            parcel.writeInt(0);
              goto _L8
_L6:
            parcel.writeInt(0);
              goto _L9
            inputdeviceidentifier = null;
              goto _L10
        }

        public KeyboardLayout[] getKeyboardLayouts()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            KeyboardLayout akeyboardlayout[];
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            mRemote.transact(11, parcel, parcel1, 0);
            parcel1.readException();
            akeyboardlayout = (KeyboardLayout[])parcel1.createTypedArray(KeyboardLayout.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return akeyboardlayout;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputdeviceidentifier)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(inputdeviceidentifier == null)
                break MISSING_BLOCK_LABEL_69;
            parcel.writeInt(1);
            inputdeviceidentifier.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(12, parcel, parcel1, 0);
            parcel1.readException();
            inputdeviceidentifier = (KeyboardLayout[])parcel1.createTypedArray(KeyboardLayout.CREATOR);
            parcel1.recycle();
            parcel.recycle();
            return inputdeviceidentifier;
            parcel.writeInt(0);
              goto _L1
            inputdeviceidentifier;
            parcel1.recycle();
            parcel.recycle();
            throw inputdeviceidentifier;
        }

        public TouchCalibration getTouchCalibrationForInputDevice(String s, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            mRemote.transact(9, parcel, parcel1, 0);
            parcel1.readException();
            if(parcel1.readInt() == 0) goto _L2; else goto _L1
_L1:
            s = (TouchCalibration)TouchCalibration.CREATOR.createFromParcel(parcel1);
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

        public boolean hasKeys(int i, int j, int ai[], boolean aflag[])
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeInt(i);
            parcel.writeInt(j);
            parcel.writeIntArray(ai);
            if(aflag != null)
                break MISSING_BLOCK_LABEL_99;
            parcel.writeInt(-1);
_L1:
            mRemote.transact(6, parcel, parcel1, 0);
            parcel1.readException();
            boolean flag;
            if(parcel1.readInt() != 0)
                flag = true;
            else
                flag = false;
            parcel1.readBooleanArray(aflag);
            parcel1.recycle();
            parcel.recycle();
            return flag;
            parcel.writeInt(aflag.length);
              goto _L1
            ai;
            parcel1.recycle();
            parcel.recycle();
            throw ai;
        }

        public boolean injectInputEvent(InputEvent inputevent, int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(inputevent == null)
                break MISSING_BLOCK_LABEL_81;
            parcel.writeInt(1);
            inputevent.writeToParcel(parcel, 0);
_L1:
            parcel.writeInt(i);
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
            inputevent;
            parcel1.recycle();
            parcel.recycle();
            throw inputevent;
        }

        public int isInTabletMode()
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            int i;
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            mRemote.transact(22, parcel, parcel1, 0);
            parcel1.readException();
            i = parcel1.readInt();
            parcel1.recycle();
            parcel.recycle();
            return i;
            Exception exception;
            exception;
            parcel1.recycle();
            parcel.recycle();
            throw exception;
        }

        public boolean isInputDeviceEnabled(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeInt(i);
            mRemote.transact(3, parcel, parcel1, 0);
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

        public void registerInputDevicesChangedListener(IInputDevicesChangedListener iinputdeviceschangedlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(iinputdeviceschangedlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = iinputdeviceschangedlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(21, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            iinputdeviceschangedlistener;
            parcel1.recycle();
            parcel.recycle();
            throw iinputdeviceschangedlistener;
        }

        public void registerTabletModeChangedListener(ITabletModeChangedListener itabletmodechangedlistener)
            throws RemoteException
        {
            IBinder ibinder;
            Parcel parcel;
            Parcel parcel1;
            ibinder = null;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(itabletmodechangedlistener == null)
                break MISSING_BLOCK_LABEL_28;
            ibinder = itabletmodechangedlistener.asBinder();
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(23, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            itabletmodechangedlistener;
            parcel1.recycle();
            parcel.recycle();
            throw itabletmodechangedlistener;
        }

        public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(inputdeviceidentifier == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            inputdeviceidentifier.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(18, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            inputdeviceidentifier;
            parcel1.recycle();
            parcel.recycle();
            throw inputdeviceidentifier;
        }

        public void requestPointerCapture(IBinder ibinder, boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeStrongBinder(ibinder);
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(28, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            ibinder;
            parcel1.recycle();
            parcel.recycle();
            throw ibinder;
        }

        public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(inputdeviceidentifier == null)
                break MISSING_BLOCK_LABEL_66;
            parcel.writeInt(1);
            inputdeviceidentifier.writeToParcel(parcel, 0);
_L1:
            parcel.writeString(s);
            mRemote.transact(15, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            inputdeviceidentifier;
            parcel1.recycle();
            parcel.recycle();
            throw inputdeviceidentifier;
        }

        public void setCustomPointerIcon(PointerIcon pointericon)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(pointericon == null)
                break MISSING_BLOCK_LABEL_57;
            parcel.writeInt(1);
            pointericon.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(27, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            pointericon;
            parcel1.recycle();
            parcel.recycle();
            throw pointericon;
        }

        public void setKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype, String s)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(inputdeviceidentifier == null) goto _L2; else goto _L1
_L1:
            parcel.writeInt(1);
            inputdeviceidentifier.writeToParcel(parcel, 0);
_L5:
            if(inputmethodinfo == null) goto _L4; else goto _L3
_L3:
            parcel.writeInt(1);
            inputmethodinfo.writeToParcel(parcel, 0);
_L6:
            if(inputmethodsubtype == null)
                break MISSING_BLOCK_LABEL_139;
            parcel.writeInt(1);
            inputmethodsubtype.writeToParcel(parcel, 0);
_L7:
            parcel.writeString(s);
            mRemote.transact(20, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
_L2:
            parcel.writeInt(0);
              goto _L5
            inputdeviceidentifier;
            parcel1.recycle();
            parcel.recycle();
            throw inputdeviceidentifier;
_L4:
            parcel.writeInt(0);
              goto _L6
            parcel.writeInt(0);
              goto _L7
        }

        public void setPointerIconType(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeInt(i);
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

        public void setTouchCalibrationForInputDevice(String s, int i, TouchCalibration touchcalibration)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeString(s);
            parcel.writeInt(i);
            if(touchcalibration == null)
                break MISSING_BLOCK_LABEL_79;
            parcel.writeInt(1);
            touchcalibration.writeToParcel(parcel, 0);
_L1:
            mRemote.transact(10, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            parcel.writeInt(0);
              goto _L1
            s;
            parcel1.recycle();
            parcel.recycle();
            throw s;
        }

        public void switchTouchCoverMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(33, parcel, parcel1, 0);
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

        public void switchTouchSensitiveMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(30, parcel, parcel1, 0);
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

        public void switchTouchStylusMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(31, parcel, parcel1, 0);
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

        public void switchTouchWakeupMode(boolean flag)
            throws RemoteException
        {
            int i;
            Parcel parcel;
            Parcel parcel1;
            i = 0;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            if(flag)
                i = 1;
            parcel.writeInt(i);
            mRemote.transact(32, parcel, parcel1, 0);
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

        public void tryPointerSpeed(int i)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeInt(i);
            mRemote.transact(7, parcel, parcel1, 0);
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

        public void vibrate(int i, long al[], int j, IBinder ibinder)
            throws RemoteException
        {
            Parcel parcel;
            Parcel parcel1;
            parcel = Parcel.obtain();
            parcel1 = Parcel.obtain();
            parcel.writeInterfaceToken("android.hardware.input.IInputManager");
            parcel.writeInt(i);
            parcel.writeLongArray(al);
            parcel.writeInt(j);
            parcel.writeStrongBinder(ibinder);
            mRemote.transact(24, parcel, parcel1, 0);
            parcel1.readException();
            parcel1.recycle();
            parcel.recycle();
            return;
            al;
            parcel1.recycle();
            parcel.recycle();
            throw al;
        }

        private IBinder mRemote;

        Stub.Proxy(IBinder ibinder)
        {
            mRemote = ibinder;
        }
    }


    public abstract void addKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, String s)
        throws RemoteException;

    public abstract void cancelVibrate(int i, IBinder ibinder)
        throws RemoteException;

    public abstract IInputForwarder createInputForwarder(int i)
        throws RemoteException;

    public abstract void disableInputDevice(int i)
        throws RemoteException;

    public abstract void enableInputDevice(int i)
        throws RemoteException;

    public abstract String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier)
        throws RemoteException;

    public abstract String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputdeviceidentifier)
        throws RemoteException;

    public abstract InputDevice getInputDevice(int i)
        throws RemoteException;

    public abstract int[] getInputDeviceIds()
        throws RemoteException;

    public abstract KeyboardLayout getKeyboardLayout(String s)
        throws RemoteException;

    public abstract KeyboardLayout getKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
        throws RemoteException;

    public abstract KeyboardLayout[] getKeyboardLayouts()
        throws RemoteException;

    public abstract KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputdeviceidentifier)
        throws RemoteException;

    public abstract TouchCalibration getTouchCalibrationForInputDevice(String s, int i)
        throws RemoteException;

    public abstract boolean hasKeys(int i, int j, int ai[], boolean aflag[])
        throws RemoteException;

    public abstract boolean injectInputEvent(InputEvent inputevent, int i)
        throws RemoteException;

    public abstract int isInTabletMode()
        throws RemoteException;

    public abstract boolean isInputDeviceEnabled(int i)
        throws RemoteException;

    public abstract void registerInputDevicesChangedListener(IInputDevicesChangedListener iinputdeviceschangedlistener)
        throws RemoteException;

    public abstract void registerTabletModeChangedListener(ITabletModeChangedListener itabletmodechangedlistener)
        throws RemoteException;

    public abstract void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, String s)
        throws RemoteException;

    public abstract void requestPointerCapture(IBinder ibinder, boolean flag)
        throws RemoteException;

    public abstract void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, String s)
        throws RemoteException;

    public abstract void setCustomPointerIcon(PointerIcon pointericon)
        throws RemoteException;

    public abstract void setKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype, String s)
        throws RemoteException;

    public abstract void setPointerIconType(int i)
        throws RemoteException;

    public abstract void setTouchCalibrationForInputDevice(String s, int i, TouchCalibration touchcalibration)
        throws RemoteException;

    public abstract void switchTouchCoverMode(boolean flag)
        throws RemoteException;

    public abstract void switchTouchSensitiveMode(boolean flag)
        throws RemoteException;

    public abstract void switchTouchStylusMode(boolean flag)
        throws RemoteException;

    public abstract void switchTouchWakeupMode(boolean flag)
        throws RemoteException;

    public abstract void tryPointerSpeed(int i)
        throws RemoteException;

    public abstract void vibrate(int i, long al[], int j, IBinder ibinder)
        throws RemoteException;
}
