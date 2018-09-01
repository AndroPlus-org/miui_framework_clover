// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.hardware.input;

import android.app.IInputForwarder;
import android.content.Context;
import android.media.AudioAttributes;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import android.view.*;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import com.android.internal.os.SomeArgs;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.hardware.input:
//            IInputManager, InputDeviceIdentifier, KeyboardLayout, TouchCalibration

public final class InputManager
{
    public static interface InputDeviceListener
    {

        public abstract void onInputDeviceAdded(int i);

        public abstract void onInputDeviceChanged(int i);

        public abstract void onInputDeviceRemoved(int i);
    }

    private static final class InputDeviceListenerDelegate extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 3: default 32
        //                       1 33
        //                       2 49
        //                       3 65;
               goto _L1 _L2 _L3 _L4
_L1:
            return;
_L2:
            mListener.onInputDeviceAdded(message.arg1);
            continue; /* Loop/switch isn't completed */
_L3:
            mListener.onInputDeviceRemoved(message.arg1);
            continue; /* Loop/switch isn't completed */
_L4:
            mListener.onInputDeviceChanged(message.arg1);
            if(true) goto _L1; else goto _L5
_L5:
        }

        public final InputDeviceListener mListener;

        public InputDeviceListenerDelegate(InputDeviceListener inputdevicelistener, Handler handler)
        {
            if(handler != null)
                handler = handler.getLooper();
            else
                handler = Looper.myLooper();
            super(handler);
            mListener = inputdevicelistener;
        }
    }

    private final class InputDeviceVibrator extends Vibrator
    {

        public void cancel()
        {
            try
            {
                InputManager._2D_get0(InputManager.this).cancelVibrate(mDeviceId, mToken);
                return;
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
        }

        public boolean hasAmplitudeControl()
        {
            return false;
        }

        public boolean hasVibrator()
        {
            return true;
        }

        public void vibrate(int i, String s, VibrationEffect vibrationeffect, AudioAttributes audioattributes)
        {
            if(vibrationeffect instanceof android.os.VibrationEffect.OneShot)
            {
                vibrationeffect = (android.os.VibrationEffect.OneShot)vibrationeffect;
                s = new long[2];
                s[0] = 0L;
                s[1] = vibrationeffect.getTiming();
                i = -1;
            } else
            if(vibrationeffect instanceof android.os.VibrationEffect.Waveform)
            {
                vibrationeffect = (android.os.VibrationEffect.Waveform)vibrationeffect;
                s = vibrationeffect.getTimings();
                i = vibrationeffect.getRepeatIndex();
            } else
            {
                Log.w("InputManager", "Pre-baked effects aren't supported on input devices");
                return;
            }
            try
            {
                InputManager._2D_get0(InputManager.this).vibrate(mDeviceId, s, i, mToken);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(String s)
            {
                throw s.rethrowFromSystemServer();
            }
        }

        private final int mDeviceId;
        private final Binder mToken = new Binder();
        final InputManager this$0;

        public InputDeviceVibrator(int i)
        {
            this$0 = InputManager.this;
            super();
            mDeviceId = i;
        }
    }

    private final class InputDevicesChangedListener extends IInputDevicesChangedListener.Stub
    {

        public void onInputDevicesChanged(int ai[])
            throws RemoteException
        {
            InputManager._2D_wrap0(InputManager.this, ai);
        }

        final InputManager this$0;

        private InputDevicesChangedListener()
        {
            this$0 = InputManager.this;
            super();
        }

        InputDevicesChangedListener(InputDevicesChangedListener inputdeviceschangedlistener)
        {
            this();
        }
    }

    public static interface OnTabletModeChangedListener
    {

        public abstract void onTabletModeChanged(long l, boolean flag);
    }

    private static final class OnTabletModeChangedListenerDelegate extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 0 0: default 24
        //                       0 25;
               goto _L1 _L2
_L1:
            return;
_L2:
            message = (SomeArgs)message.obj;
            long l = ((SomeArgs) (message)).argi1;
            long l1 = ((SomeArgs) (message)).argi2;
            boolean flag = ((Boolean)((SomeArgs) (message)).arg1).booleanValue();
            mListener.onTabletModeChanged(l & 0xffffffffL | l1 << 32, flag);
            if(true) goto _L1; else goto _L3
_L3:
        }

        public void sendTabletModeChanged(long l, boolean flag)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.argi1 = (int)(-1L & l);
            someargs.argi2 = (int)(l >> 32);
            someargs.arg1 = Boolean.valueOf(flag);
            obtainMessage(0, someargs).sendToTarget();
        }

        private static final int MSG_TABLET_MODE_CHANGED = 0;
        public final OnTabletModeChangedListener mListener;

        public OnTabletModeChangedListenerDelegate(OnTabletModeChangedListener ontabletmodechangedlistener, Handler handler)
        {
            if(handler != null)
                handler = handler.getLooper();
            else
                handler = Looper.myLooper();
            super(handler);
            mListener = ontabletmodechangedlistener;
        }
    }

    private final class TabletModeChangedListener extends ITabletModeChangedListener.Stub
    {

        public void onTabletModeChanged(long l, boolean flag)
        {
            InputManager._2D_wrap1(InputManager.this, l, flag);
        }

        final InputManager this$0;

        private TabletModeChangedListener()
        {
            this$0 = InputManager.this;
            super();
        }

        TabletModeChangedListener(TabletModeChangedListener tabletmodechangedlistener)
        {
            this();
        }
    }


    static IInputManager _2D_get0(InputManager inputmanager)
    {
        return inputmanager.mIm;
    }

    static void _2D_wrap0(InputManager inputmanager, int ai[])
    {
        inputmanager.onInputDevicesChanged(ai);
    }

    static void _2D_wrap1(InputManager inputmanager, long l, boolean flag)
    {
        inputmanager.onTabletModeChanged(l, flag);
    }

    private InputManager(IInputManager iinputmanager)
    {
        mIm = iinputmanager;
    }

    private static boolean containsDeviceId(int ai[], int i)
    {
        for(int j = 0; j < ai.length; j += 2)
            if(ai[j] == i)
                return true;

        return false;
    }

    private int findInputDeviceListenerLocked(InputDeviceListener inputdevicelistener)
    {
        int i = mInputDeviceListeners.size();
        for(int j = 0; j < i; j++)
            if(((InputDeviceListenerDelegate)mInputDeviceListeners.get(j)).mListener == inputdevicelistener)
                return j;

        return -1;
    }

    private int findOnTabletModeChangedListenerLocked(OnTabletModeChangedListener ontabletmodechangedlistener)
    {
        int i = mOnTabletModeChangedListeners.size();
        for(int j = 0; j < i; j++)
            if(((OnTabletModeChangedListenerDelegate)mOnTabletModeChangedListeners.get(j)).mListener == ontabletmodechangedlistener)
                return j;

        return -1;
    }

    public static InputManager getInstance()
    {
        android/hardware/input/InputManager;
        JVM INSTR monitorenter ;
        InputManager inputmanager = sInstance;
        if(inputmanager != null)
            break MISSING_BLOCK_LABEL_31;
        inputmanager = JVM INSTR new #2   <Class InputManager>;
        inputmanager.InputManager(IInputManager.Stub.asInterface(ServiceManager.getServiceOrThrow("input")));
        sInstance = inputmanager;
        inputmanager = sInstance;
        android/hardware/input/InputManager;
        JVM INSTR monitorexit ;
        return inputmanager;
        Object obj;
        obj;
        IllegalStateException illegalstateexception = JVM INSTR new #159 <Class IllegalStateException>;
        illegalstateexception.IllegalStateException(((Throwable) (obj)));
        throw illegalstateexception;
        obj;
        android/hardware/input/InputManager;
        JVM INSTR monitorexit ;
        throw obj;
    }

    private void initializeTabletModeListenerLocked()
    {
        TabletModeChangedListener tabletmodechangedlistener = new TabletModeChangedListener(null);
        try
        {
            mIm.registerTabletModeChangedListener(tabletmodechangedlistener);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        mTabletModeChangedListener = tabletmodechangedlistener;
        mOnTabletModeChangedListeners = new ArrayList();
    }

    private void onInputDevicesChanged(int ai[])
    {
        Object obj = mInputDevicesLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mInputDevices.size();
_L2:
        int j;
        j = i - 1;
        if(j <= 0)
            break MISSING_BLOCK_LABEL_75;
        int k = mInputDevices.keyAt(j);
        i = j;
        if(containsDeviceId(ai, k)) goto _L2; else goto _L1
_L1:
        mInputDevices.removeAt(j);
        sendMessageToInputDeviceListenersLocked(2, k);
        i = j;
          goto _L2
        ai;
        throw ai;
        i = 0;
_L4:
        if(i >= ai.length)
            break MISSING_BLOCK_LABEL_183;
        k = ai[i];
        j = mInputDevices.indexOfKey(k);
        if(j < 0)
            break; /* Loop/switch isn't completed */
        InputDevice inputdevice = (InputDevice)mInputDevices.valueAt(j);
        int l;
        if(inputdevice == null)
            break MISSING_BLOCK_LABEL_157;
        l = ai[i + 1];
        if(inputdevice.getGeneration() != l)
        {
            mInputDevices.setValueAt(j, null);
            sendMessageToInputDeviceListenersLocked(3, k);
        }
_L5:
        i += 2;
        if(true) goto _L4; else goto _L3
_L3:
        mInputDevices.put(k, null);
        sendMessageToInputDeviceListenersLocked(1, k);
          goto _L5
        obj;
        JVM INSTR monitorexit ;
    }

    private void onTabletModeChanged(long l, boolean flag)
    {
        Object obj = mTabletModeLock;
        obj;
        JVM INSTR monitorenter ;
        int i = mOnTabletModeChangedListeners.size();
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ((OnTabletModeChangedListenerDelegate)mOnTabletModeChangedListeners.get(j)).sendTabletModeChanged(l, flag);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private void populateInputDevicesLocked()
    {
        if(mInputDevicesChangedListener == null)
        {
            InputDevicesChangedListener inputdeviceschangedlistener = new InputDevicesChangedListener(null);
            int ai[];
            int i;
            try
            {
                mIm.registerInputDevicesChangedListener(inputdeviceschangedlistener);
            }
            catch(RemoteException remoteexception)
            {
                throw remoteexception.rethrowFromSystemServer();
            }
            mInputDevicesChangedListener = inputdeviceschangedlistener;
        }
        if(mInputDevices == null)
        {
            try
            {
                ai = mIm.getInputDeviceIds();
            }
            catch(RemoteException remoteexception1)
            {
                throw remoteexception1.rethrowFromSystemServer();
            }
            mInputDevices = new SparseArray();
            for(i = 0; i < ai.length; i++)
                mInputDevices.put(ai[i], null);

        }
    }

    private void sendMessageToInputDeviceListenersLocked(int i, int j)
    {
        int k = mInputDeviceListeners.size();
        for(int l = 0; l < k; l++)
        {
            InputDeviceListenerDelegate inputdevicelistenerdelegate = (InputDeviceListenerDelegate)mInputDeviceListeners.get(l);
            inputdevicelistenerdelegate.sendMessage(inputdevicelistenerdelegate.obtainMessage(i, j, 0));
        }

    }

    public void addKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, String s)
    {
        if(inputdeviceidentifier == null)
            throw new IllegalArgumentException("inputDeviceDescriptor must not be null");
        if(s == null)
            throw new IllegalArgumentException("keyboardLayoutDescriptor must not be null");
        try
        {
            mIm.addKeyboardLayoutForInputDevice(inputdeviceidentifier, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(InputDeviceIdentifier inputdeviceidentifier)
        {
            throw inputdeviceidentifier.rethrowFromSystemServer();
        }
    }

    public IInputForwarder createInputForwarder(int i)
    {
        IInputForwarder iinputforwarder;
        try
        {
            iinputforwarder = mIm.createInputForwarder(i);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return iinputforwarder;
    }

    public boolean[] deviceHasKeys(int i, int ai[])
    {
        boolean aflag[] = new boolean[ai.length];
        try
        {
            mIm.hasKeys(i, -256, ai, aflag);
        }
        // Misplaced declaration of an exception variable
        catch(int ai[])
        {
            throw ai.rethrowFromSystemServer();
        }
        return aflag;
    }

    public boolean[] deviceHasKeys(int ai[])
    {
        return deviceHasKeys(-1, ai);
    }

    public void disableInputDevice(int i)
    {
        try
        {
            mIm.disableInputDevice(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            Log.w("InputManager", (new StringBuilder()).append("Could not disable input device with id = ").append(i).toString());
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void enableInputDevice(int i)
    {
        try
        {
            mIm.enableInputDevice(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            Log.w("InputManager", (new StringBuilder()).append("Could not enable input device with id = ").append(i).toString());
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public String getCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier)
    {
        try
        {
            inputdeviceidentifier = mIm.getCurrentKeyboardLayoutForInputDevice(inputdeviceidentifier);
        }
        // Misplaced declaration of an exception variable
        catch(InputDeviceIdentifier inputdeviceidentifier)
        {
            throw inputdeviceidentifier.rethrowFromSystemServer();
        }
        return inputdeviceidentifier;
    }

    public String[] getEnabledKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputdeviceidentifier)
    {
        if(inputdeviceidentifier == null)
            throw new IllegalArgumentException("inputDeviceDescriptor must not be null");
        try
        {
            inputdeviceidentifier = mIm.getEnabledKeyboardLayoutsForInputDevice(inputdeviceidentifier);
        }
        // Misplaced declaration of an exception variable
        catch(InputDeviceIdentifier inputdeviceidentifier)
        {
            throw inputdeviceidentifier.rethrowFromSystemServer();
        }
        return inputdeviceidentifier;
    }

    public InputDevice getInputDevice(int i)
    {
        Object obj = mInputDevicesLock;
        obj;
        JVM INSTR monitorenter ;
        int j;
        populateInputDevicesLocked();
        j = mInputDevices.indexOfKey(i);
        if(j >= 0)
            break MISSING_BLOCK_LABEL_28;
        obj;
        JVM INSTR monitorexit ;
        return null;
        InputDevice inputdevice = (InputDevice)mInputDevices.valueAt(j);
        InputDevice inputdevice1;
        inputdevice1 = inputdevice;
        if(inputdevice != null)
            break MISSING_BLOCK_LABEL_85;
        inputdevice = mIm.getInputDevice(i);
        inputdevice1 = inputdevice;
        if(inputdevice == null)
            break MISSING_BLOCK_LABEL_85;
        mInputDevices.setValueAt(j, inputdevice);
        inputdevice1 = inputdevice;
        obj;
        JVM INSTR monitorexit ;
        return inputdevice1;
        Object obj1;
        obj1;
        throw ((RemoteException) (obj1)).rethrowFromSystemServer();
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public InputDevice getInputDeviceByDescriptor(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("descriptor must not be null.");
        Object obj = mInputDevicesLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        populateInputDevicesLocked();
        i = mInputDevices.size();
        int j = 0;
_L5:
        if(j >= i)
            break MISSING_BLOCK_LABEL_145;
        InputDevice inputdevice = (InputDevice)mInputDevices.valueAt(j);
        InputDevice inputdevice1 = inputdevice;
        if(inputdevice != null) goto _L2; else goto _L1
_L1:
        int k = mInputDevices.keyAt(j);
        inputdevice1 = mIm.getInputDevice(k);
        if(inputdevice1 != null) goto _L4; else goto _L3
_L3:
        j++;
          goto _L5
        s;
        throw s.rethrowFromSystemServer();
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
_L4:
        mInputDevices.setValueAt(j, inputdevice1);
_L2:
        boolean flag = s.equals(inputdevice1.getDescriptor());
        if(!flag) goto _L3; else goto _L6
_L6:
        obj;
        JVM INSTR monitorexit ;
        return inputdevice1;
        return null;
    }

    public int[] getInputDeviceIds()
    {
        Object obj = mInputDevicesLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        int ai[];
        populateInputDevicesLocked();
        i = mInputDevices.size();
        ai = new int[i];
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        ai[j] = mInputDevices.keyAt(j);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        return ai;
        Exception exception;
        exception;
        throw exception;
    }

    public Vibrator getInputDeviceVibrator(int i)
    {
        return new InputDeviceVibrator(i);
    }

    public KeyboardLayout getKeyboardLayout(String s)
    {
        if(s == null)
            throw new IllegalArgumentException("keyboardLayoutDescriptor must not be null");
        try
        {
            s = mIm.getKeyboardLayout(s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public KeyboardLayout getKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype)
    {
        try
        {
            inputdeviceidentifier = mIm.getKeyboardLayoutForInputDevice(inputdeviceidentifier, inputmethodinfo, inputmethodsubtype);
        }
        // Misplaced declaration of an exception variable
        catch(InputDeviceIdentifier inputdeviceidentifier)
        {
            throw inputdeviceidentifier.rethrowFromSystemServer();
        }
        return inputdeviceidentifier;
    }

    public KeyboardLayout[] getKeyboardLayouts()
    {
        KeyboardLayout akeyboardlayout[];
        try
        {
            akeyboardlayout = mIm.getKeyboardLayouts();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return akeyboardlayout;
    }

    public KeyboardLayout[] getKeyboardLayoutsForInputDevice(InputDeviceIdentifier inputdeviceidentifier)
    {
        try
        {
            inputdeviceidentifier = mIm.getKeyboardLayoutsForInputDevice(inputdeviceidentifier);
        }
        // Misplaced declaration of an exception variable
        catch(InputDeviceIdentifier inputdeviceidentifier)
        {
            throw inputdeviceidentifier.rethrowFromSystemServer();
        }
        return inputdeviceidentifier;
    }

    public int getPointerSpeed(Context context)
    {
        boolean flag = false;
        int i;
        try
        {
            i = android.provider.Settings.System.getInt(context.getContentResolver(), "pointer_speed");
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            i = ((flag) ? 1 : 0);
        }
        return i;
    }

    public TouchCalibration getTouchCalibration(String s, int i)
    {
        try
        {
            s = mIm.getTouchCalibrationForInputDevice(s, i);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public boolean injectInputEvent(InputEvent inputevent, int i)
    {
        if(inputevent == null)
            throw new IllegalArgumentException("event must not be null");
        if(i != 0 && i != 2 && i != 1)
            throw new IllegalArgumentException("mode is invalid");
        boolean flag;
        try
        {
            flag = mIm.injectInputEvent(inputevent, i);
        }
        // Misplaced declaration of an exception variable
        catch(InputEvent inputevent)
        {
            throw inputevent.rethrowFromSystemServer();
        }
        return flag;
    }

    public int isInTabletMode()
    {
        int i;
        try
        {
            i = mIm.isInTabletMode();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public boolean isInputDeviceEnabled(int i)
    {
        boolean flag;
        try
        {
            flag = mIm.isInputDeviceEnabled(i);
        }
        catch(RemoteException remoteexception)
        {
            Log.w("InputManager", (new StringBuilder()).append("Could not check enabled status of input device with id = ").append(i).toString());
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void registerInputDeviceListener(InputDeviceListener inputdevicelistener, Handler handler)
    {
        if(inputdevicelistener == null)
            throw new IllegalArgumentException("listener must not be null");
        Object obj = mInputDevicesLock;
        obj;
        JVM INSTR monitorenter ;
        populateInputDevicesLocked();
        if(findInputDeviceListenerLocked(inputdevicelistener) < 0)
        {
            ArrayList arraylist = mInputDeviceListeners;
            InputDeviceListenerDelegate inputdevicelistenerdelegate = JVM INSTR new #9   <Class InputManager$InputDeviceListenerDelegate>;
            inputdevicelistenerdelegate.InputDeviceListenerDelegate(inputdevicelistener, handler);
            arraylist.add(inputdevicelistenerdelegate);
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        inputdevicelistener;
        throw inputdevicelistener;
    }

    public void registerOnTabletModeChangedListener(OnTabletModeChangedListener ontabletmodechangedlistener, Handler handler)
    {
        if(ontabletmodechangedlistener == null)
            throw new IllegalArgumentException("listener must not be null");
        Object obj = mTabletModeLock;
        obj;
        JVM INSTR monitorenter ;
        if(mOnTabletModeChangedListeners == null)
            initializeTabletModeListenerLocked();
        if(findOnTabletModeChangedListenerLocked(ontabletmodechangedlistener) < 0)
        {
            OnTabletModeChangedListenerDelegate ontabletmodechangedlistenerdelegate = JVM INSTR new #21  <Class InputManager$OnTabletModeChangedListenerDelegate>;
            ontabletmodechangedlistenerdelegate.OnTabletModeChangedListenerDelegate(ontabletmodechangedlistener, handler);
            mOnTabletModeChangedListeners.add(ontabletmodechangedlistenerdelegate);
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        ontabletmodechangedlistener;
        throw ontabletmodechangedlistener;
    }

    public void removeKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, String s)
    {
        if(inputdeviceidentifier == null)
            throw new IllegalArgumentException("inputDeviceDescriptor must not be null");
        if(s == null)
            throw new IllegalArgumentException("keyboardLayoutDescriptor must not be null");
        try
        {
            mIm.removeKeyboardLayoutForInputDevice(inputdeviceidentifier, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(InputDeviceIdentifier inputdeviceidentifier)
        {
            throw inputdeviceidentifier.rethrowFromSystemServer();
        }
    }

    public void requestPointerCapture(IBinder ibinder, boolean flag)
    {
        try
        {
            mIm.requestPointerCapture(ibinder, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public void setCurrentKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, String s)
    {
        if(inputdeviceidentifier == null)
            throw new IllegalArgumentException("identifier must not be null");
        if(s == null)
            throw new IllegalArgumentException("keyboardLayoutDescriptor must not be null");
        try
        {
            mIm.setCurrentKeyboardLayoutForInputDevice(inputdeviceidentifier, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(InputDeviceIdentifier inputdeviceidentifier)
        {
            throw inputdeviceidentifier.rethrowFromSystemServer();
        }
    }

    public void setCustomPointerIcon(PointerIcon pointericon)
    {
        try
        {
            mIm.setCustomPointerIcon(pointericon);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PointerIcon pointericon)
        {
            throw pointericon.rethrowFromSystemServer();
        }
    }

    public void setKeyboardLayoutForInputDevice(InputDeviceIdentifier inputdeviceidentifier, InputMethodInfo inputmethodinfo, InputMethodSubtype inputmethodsubtype, String s)
    {
        try
        {
            mIm.setKeyboardLayoutForInputDevice(inputdeviceidentifier, inputmethodinfo, inputmethodsubtype, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(InputDeviceIdentifier inputdeviceidentifier)
        {
            throw inputdeviceidentifier.rethrowFromSystemServer();
        }
    }

    public void setPointerIconType(int i)
    {
        try
        {
            mIm.setPointerIconType(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void setPointerSpeed(Context context, int i)
    {
        if(i < -7 || i > 7)
        {
            throw new IllegalArgumentException("speed out of range");
        } else
        {
            android.provider.Settings.System.putInt(context.getContentResolver(), "pointer_speed", i);
            return;
        }
    }

    public void setTouchCalibration(String s, int i, TouchCalibration touchcalibration)
    {
        try
        {
            mIm.setTouchCalibrationForInputDevice(s, i, touchcalibration);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
    }

    public void switchTouchCoverMode(boolean flag)
    {
        if(!flag)
            break MISSING_BLOCK_LABEL_15;
        mIm.switchTouchCoverMode(true);
_L1:
        return;
        try
        {
            mIm.switchTouchCoverMode(false);
        }
        catch(RemoteException remoteexception)
        {
            Log.w("InputManager", "SwitchTouchCoverMode: Error is :", remoteexception);
            return;
        }
          goto _L1
    }

    public void switchTouchSensitiveMode(boolean flag)
    {
        if(!flag)
            break MISSING_BLOCK_LABEL_15;
        mIm.switchTouchSensitiveMode(true);
_L1:
        return;
        try
        {
            mIm.switchTouchSensitiveMode(false);
        }
        catch(RemoteException remoteexception)
        {
            Log.w("InputManager", "SwitchTouchSensitiveMode: Error is :", remoteexception);
            return;
        }
          goto _L1
    }

    public void switchTouchStylusMode(boolean flag)
    {
        if(!flag)
            break MISSING_BLOCK_LABEL_15;
        mIm.switchTouchStylusMode(true);
_L1:
        return;
        try
        {
            mIm.switchTouchStylusMode(false);
        }
        catch(RemoteException remoteexception)
        {
            Log.w("InputManager", "SwitchTouchStylusMode: Error is :", remoteexception);
            return;
        }
          goto _L1
    }

    public void switchTouchWakeupMode(boolean flag)
    {
        if(!flag)
            break MISSING_BLOCK_LABEL_15;
        mIm.switchTouchWakeupMode(true);
_L1:
        return;
        try
        {
            mIm.switchTouchWakeupMode(false);
        }
        catch(RemoteException remoteexception)
        {
            Log.w("InputManager", "SwitchTouchWakeupMode: Error is :", remoteexception);
            return;
        }
          goto _L1
    }

    public void tryPointerSpeed(int i)
    {
        if(i < -7 || i > 7)
            throw new IllegalArgumentException("speed out of range");
        try
        {
            mIm.tryPointerSpeed(i);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void unregisterInputDeviceListener(InputDeviceListener inputdevicelistener)
    {
        if(inputdevicelistener == null)
            throw new IllegalArgumentException("listener must not be null");
        Object obj = mInputDevicesLock;
        obj;
        JVM INSTR monitorenter ;
        int i = findInputDeviceListenerLocked(inputdevicelistener);
        if(i < 0)
            break MISSING_BLOCK_LABEL_56;
        ((InputDeviceListenerDelegate)mInputDeviceListeners.get(i)).removeCallbacksAndMessages(null);
        mInputDeviceListeners.remove(i);
        obj;
        JVM INSTR monitorexit ;
        return;
        inputdevicelistener;
        throw inputdevicelistener;
    }

    public void unregisterOnTabletModeChangedListener(OnTabletModeChangedListener ontabletmodechangedlistener)
    {
        if(ontabletmodechangedlistener == null)
            throw new IllegalArgumentException("listener must not be null");
        Object obj = mTabletModeLock;
        obj;
        JVM INSTR monitorenter ;
        int i = findOnTabletModeChangedListenerLocked(ontabletmodechangedlistener);
        if(i < 0)
            break MISSING_BLOCK_LABEL_49;
        ((OnTabletModeChangedListenerDelegate)mOnTabletModeChangedListeners.remove(i)).removeCallbacksAndMessages(null);
        obj;
        JVM INSTR monitorexit ;
        return;
        ontabletmodechangedlistener;
        throw ontabletmodechangedlistener;
    }

    public static final String ACTION_QUERY_KEYBOARD_LAYOUTS = "android.hardware.input.action.QUERY_KEYBOARD_LAYOUTS";
    private static final boolean DEBUG = false;
    public static final int DEFAULT_POINTER_SPEED = 0;
    public static final int INJECT_INPUT_EVENT_MODE_ASYNC = 0;
    public static final int INJECT_INPUT_EVENT_MODE_WAIT_FOR_FINISH = 2;
    public static final int INJECT_INPUT_EVENT_MODE_WAIT_FOR_RESULT = 1;
    public static final int MAX_POINTER_SPEED = 7;
    public static final String META_DATA_KEYBOARD_LAYOUTS = "android.hardware.input.metadata.KEYBOARD_LAYOUTS";
    public static final int MIN_POINTER_SPEED = -7;
    private static final int MSG_DEVICE_ADDED = 1;
    private static final int MSG_DEVICE_CHANGED = 3;
    private static final int MSG_DEVICE_REMOVED = 2;
    public static final int SWITCH_STATE_OFF = 0;
    public static final int SWITCH_STATE_ON = 1;
    public static final int SWITCH_STATE_UNKNOWN = -1;
    private static final String TAG = "InputManager";
    private static InputManager sInstance;
    private final IInputManager mIm;
    private final ArrayList mInputDeviceListeners = new ArrayList();
    private SparseArray mInputDevices;
    private InputDevicesChangedListener mInputDevicesChangedListener;
    private final Object mInputDevicesLock = new Object();
    private List mOnTabletModeChangedListeners;
    private TabletModeChangedListener mTabletModeChangedListener;
    private final Object mTabletModeLock = new Object();
}
