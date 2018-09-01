// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.hardware.input.InputDeviceIdentifier;
import android.hardware.input.InputManager;
import android.os.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.view:
//            KeyCharacterMap, MotionEvent, PointerIcon

public final class InputDevice
    implements Parcelable
{
    public static final class MotionRange
    {

        static int _2D_get0(MotionRange motionrange)
        {
            return motionrange.mAxis;
        }

        static float _2D_get1(MotionRange motionrange)
        {
            return motionrange.mFlat;
        }

        static float _2D_get2(MotionRange motionrange)
        {
            return motionrange.mFuzz;
        }

        static float _2D_get3(MotionRange motionrange)
        {
            return motionrange.mMax;
        }

        static float _2D_get4(MotionRange motionrange)
        {
            return motionrange.mMin;
        }

        static float _2D_get5(MotionRange motionrange)
        {
            return motionrange.mResolution;
        }

        static int _2D_get6(MotionRange motionrange)
        {
            return motionrange.mSource;
        }

        public int getAxis()
        {
            return mAxis;
        }

        public float getFlat()
        {
            return mFlat;
        }

        public float getFuzz()
        {
            return mFuzz;
        }

        public float getMax()
        {
            return mMax;
        }

        public float getMin()
        {
            return mMin;
        }

        public float getRange()
        {
            return mMax - mMin;
        }

        public float getResolution()
        {
            return mResolution;
        }

        public int getSource()
        {
            return mSource;
        }

        public boolean isFromSource(int i)
        {
            boolean flag;
            if((getSource() & i) == i)
                flag = true;
            else
                flag = false;
            return flag;
        }

        private int mAxis;
        private float mFlat;
        private float mFuzz;
        private float mMax;
        private float mMin;
        private float mResolution;
        private int mSource;

        private MotionRange(int i, int j, float f, float f1, float f2, float f3, float f4)
        {
            mAxis = i;
            mSource = j;
            mMin = f;
            mMax = f1;
            mFlat = f2;
            mFuzz = f3;
            mResolution = f4;
        }

        MotionRange(int i, int j, float f, float f1, float f2, float f3, float f4, 
                MotionRange motionrange)
        {
            this(i, j, f, f1, f2, f3, f4);
        }
    }


    private InputDevice(int i, int j, int k, String s, int l, int i1, String s1, 
            boolean flag, int j1, int k1, KeyCharacterMap keycharactermap, boolean flag1, boolean flag2, boolean flag3)
    {
        mMotionRanges = new ArrayList();
        mId = i;
        mGeneration = j;
        mControllerNumber = k;
        mName = s;
        mVendorId = l;
        mProductId = i1;
        mDescriptor = s1;
        mIsExternal = flag;
        mSources = j1;
        mKeyboardType = k1;
        mKeyCharacterMap = keycharactermap;
        mHasVibrator = flag1;
        mHasMicrophone = flag2;
        mHasButtonUnderPad = flag3;
        mIdentifier = new InputDeviceIdentifier(s1, l, i1);
    }

    private InputDevice(Parcel parcel)
    {
        boolean flag = true;
        super();
        mMotionRanges = new ArrayList();
        mId = parcel.readInt();
        mGeneration = parcel.readInt();
        mControllerNumber = parcel.readInt();
        mName = parcel.readString();
        mVendorId = parcel.readInt();
        mProductId = parcel.readInt();
        mDescriptor = parcel.readString();
        boolean flag1;
        int i;
        int k;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mIsExternal = flag1;
        mSources = parcel.readInt();
        mKeyboardType = parcel.readInt();
        mKeyCharacterMap = (KeyCharacterMap)KeyCharacterMap.CREATOR.createFromParcel(parcel);
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mHasVibrator = flag1;
        if(parcel.readInt() != 0)
            flag1 = true;
        else
            flag1 = false;
        mHasMicrophone = flag1;
        if(parcel.readInt() != 0)
            flag1 = flag;
        else
            flag1 = false;
        mHasButtonUnderPad = flag1;
        mIdentifier = new InputDeviceIdentifier(mDescriptor, mVendorId, mProductId);
        i = parcel.readInt();
        k = i;
        if(i > 1000)
            k = 1000;
        for(int j = 0; j < k; j++)
            addMotionRange(parcel.readInt(), parcel.readInt(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat());

    }

    InputDevice(Parcel parcel, InputDevice inputdevice)
    {
        this(parcel);
    }

    private void addMotionRange(int i, int j, float f, float f1, float f2, float f3, float f4)
    {
        mMotionRanges.add(new MotionRange(i, j, f, f1, f2, f3, f4, null));
    }

    private void appendSourceDescriptionIfApplicable(StringBuilder stringbuilder, int i, String s)
    {
        if((mSources & i) == i)
        {
            stringbuilder.append(" ");
            stringbuilder.append(s);
        }
    }

    public static InputDevice getDevice(int i)
    {
        return InputManager.getInstance().getInputDevice(i);
    }

    public static int[] getDeviceIds()
    {
        return InputManager.getInstance().getInputDeviceIds();
    }

    public int describeContents()
    {
        return 0;
    }

    public void disable()
    {
        InputManager.getInstance().disableInputDevice(mId);
    }

    public void enable()
    {
        InputManager.getInstance().enableInputDevice(mId);
    }

    public int getControllerNumber()
    {
        return mControllerNumber;
    }

    public String getDescriptor()
    {
        return mDescriptor;
    }

    public int getGeneration()
    {
        return mGeneration;
    }

    public int getId()
    {
        return mId;
    }

    public InputDeviceIdentifier getIdentifier()
    {
        return mIdentifier;
    }

    public KeyCharacterMap getKeyCharacterMap()
    {
        return mKeyCharacterMap;
    }

    public int getKeyboardType()
    {
        return mKeyboardType;
    }

    public MotionRange getMotionRange(int i)
    {
        int j = mMotionRanges.size();
        for(int k = 0; k < j; k++)
        {
            MotionRange motionrange = (MotionRange)mMotionRanges.get(k);
            if(MotionRange._2D_get0(motionrange) == i)
                return motionrange;
        }

        return null;
    }

    public MotionRange getMotionRange(int i, int j)
    {
        int k = mMotionRanges.size();
        for(int l = 0; l < k; l++)
        {
            MotionRange motionrange = (MotionRange)mMotionRanges.get(l);
            if(MotionRange._2D_get0(motionrange) == i && MotionRange._2D_get6(motionrange) == j)
                return motionrange;
        }

        return null;
    }

    public List getMotionRanges()
    {
        return mMotionRanges;
    }

    public String getName()
    {
        return mName;
    }

    public int getProductId()
    {
        return mProductId;
    }

    public int getSources()
    {
        return mSources;
    }

    public int getVendorId()
    {
        return mVendorId;
    }

    public Vibrator getVibrator()
    {
        ArrayList arraylist = mMotionRanges;
        arraylist;
        JVM INSTR monitorenter ;
        if(mVibrator == null)
        {
            if(!mHasVibrator)
                break MISSING_BLOCK_LABEL_44;
            mVibrator = InputManager.getInstance().getInputDeviceVibrator(mId);
        }
_L1:
        Vibrator vibrator = mVibrator;
        arraylist;
        JVM INSTR monitorexit ;
        return vibrator;
        mVibrator = NullVibrator.getInstance();
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public boolean hasButtonUnderPad()
    {
        return mHasButtonUnderPad;
    }

    public transient boolean[] hasKeys(int ai[])
    {
        return InputManager.getInstance().deviceHasKeys(mId, ai);
    }

    public boolean hasMicrophone()
    {
        return mHasMicrophone;
    }

    public boolean isEnabled()
    {
        return InputManager.getInstance().isInputDeviceEnabled(mId);
    }

    public boolean isExternal()
    {
        return mIsExternal;
    }

    public boolean isFullKeyboard()
    {
        boolean flag = false;
        boolean flag1 = flag;
        if((mSources & 0x101) == 257)
        {
            flag1 = flag;
            if(mKeyboardType == 2)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isVirtual()
    {
        boolean flag = false;
        if(mId < 0)
            flag = true;
        return flag;
    }

    public void setCustomPointerIcon(PointerIcon pointericon)
    {
        InputManager.getInstance().setCustomPointerIcon(pointericon);
    }

    public void setPointerType(int i)
    {
        InputManager.getInstance().setPointerIconType(i);
    }

    public boolean supportsSource(int i)
    {
        boolean flag;
        if((mSources & i) == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public String toString()
    {
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        stringbuilder.append("Input Device ").append(mId).append(": ").append(mName).append("\n");
        stringbuilder.append("  Descriptor: ").append(mDescriptor).append("\n");
        stringbuilder.append("  Generation: ").append(mGeneration).append("\n");
        StringBuilder stringbuilder1 = stringbuilder.append("  Location: ");
        Object obj;
        int i;
        int j;
        if(mIsExternal)
            obj = "external";
        else
            obj = "built-in";
        stringbuilder1.append(((String) (obj))).append("\n");
        stringbuilder.append("  Keyboard Type: ");
        mKeyboardType;
        JVM INSTR tableswitch 0 2: default 152
    //                   0 504
    //                   1 515
    //                   2 526;
           goto _L1 _L2 _L3 _L4
_L1:
        stringbuilder.append("\n");
        stringbuilder.append("  Has Vibrator: ").append(mHasVibrator).append("\n");
        stringbuilder.append("  Has mic: ").append(mHasMicrophone).append("\n");
        stringbuilder.append("  Sources: 0x").append(Integer.toHexString(mSources)).append(" (");
        appendSourceDescriptionIfApplicable(stringbuilder, 257, "keyboard");
        appendSourceDescriptionIfApplicable(stringbuilder, 513, "dpad");
        appendSourceDescriptionIfApplicable(stringbuilder, 4098, "touchscreen");
        appendSourceDescriptionIfApplicable(stringbuilder, 8194, "mouse");
        appendSourceDescriptionIfApplicable(stringbuilder, 16386, "stylus");
        appendSourceDescriptionIfApplicable(stringbuilder, 0x10004, "trackball");
        appendSourceDescriptionIfApplicable(stringbuilder, 0x20004, "mouse_relative");
        appendSourceDescriptionIfApplicable(stringbuilder, 0x100008, "touchpad");
        appendSourceDescriptionIfApplicable(stringbuilder, 0x1000010, "joystick");
        appendSourceDescriptionIfApplicable(stringbuilder, 1025, "gamepad");
        stringbuilder.append(" )\n");
        i = mMotionRanges.size();
        for(j = 0; j < i; j++)
        {
            obj = (MotionRange)mMotionRanges.get(j);
            stringbuilder.append("    ").append(MotionEvent.axisToString(MotionRange._2D_get0(((MotionRange) (obj)))));
            stringbuilder.append(": source=0x").append(Integer.toHexString(MotionRange._2D_get6(((MotionRange) (obj)))));
            stringbuilder.append(" min=").append(MotionRange._2D_get4(((MotionRange) (obj))));
            stringbuilder.append(" max=").append(MotionRange._2D_get3(((MotionRange) (obj))));
            stringbuilder.append(" flat=").append(MotionRange._2D_get1(((MotionRange) (obj))));
            stringbuilder.append(" fuzz=").append(MotionRange._2D_get2(((MotionRange) (obj))));
            stringbuilder.append(" resolution=").append(MotionRange._2D_get5(((MotionRange) (obj))));
            stringbuilder.append("\n");
        }

        break; /* Loop/switch isn't completed */
_L2:
        stringbuilder.append("none");
        continue; /* Loop/switch isn't completed */
_L3:
        stringbuilder.append("non-alphabetic");
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuilder.append("alphabetic");
        if(true) goto _L1; else goto _L5
_L5:
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(mId);
        parcel.writeInt(mGeneration);
        parcel.writeInt(mControllerNumber);
        parcel.writeString(mName);
        parcel.writeInt(mVendorId);
        parcel.writeInt(mProductId);
        parcel.writeString(mDescriptor);
        int j;
        if(mIsExternal)
            j = 1;
        else
            j = 0;
        parcel.writeInt(j);
        parcel.writeInt(mSources);
        parcel.writeInt(mKeyboardType);
        mKeyCharacterMap.writeToParcel(parcel, i);
        if(mHasVibrator)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mHasMicrophone)
            i = 1;
        else
            i = 0;
        parcel.writeInt(i);
        if(mHasButtonUnderPad)
            i = ((flag) ? 1 : 0);
        else
            i = 0;
        parcel.writeInt(i);
        j = mMotionRanges.size();
        parcel.writeInt(j);
        for(i = 0; i < j; i++)
        {
            MotionRange motionrange = (MotionRange)mMotionRanges.get(i);
            parcel.writeInt(MotionRange._2D_get0(motionrange));
            parcel.writeInt(MotionRange._2D_get6(motionrange));
            parcel.writeFloat(MotionRange._2D_get4(motionrange));
            parcel.writeFloat(MotionRange._2D_get3(motionrange));
            parcel.writeFloat(MotionRange._2D_get1(motionrange));
            parcel.writeFloat(MotionRange._2D_get2(motionrange));
            parcel.writeFloat(MotionRange._2D_get5(motionrange));
        }

    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public InputDevice createFromParcel(Parcel parcel)
        {
            return new InputDevice(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public InputDevice[] newArray(int i)
        {
            return new InputDevice[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int KEYBOARD_TYPE_ALPHABETIC = 2;
    public static final int KEYBOARD_TYPE_NONE = 0;
    public static final int KEYBOARD_TYPE_NON_ALPHABETIC = 1;
    private static final int MAX_RANGES = 1000;
    public static final int MOTION_RANGE_ORIENTATION = 8;
    public static final int MOTION_RANGE_PRESSURE = 2;
    public static final int MOTION_RANGE_SIZE = 3;
    public static final int MOTION_RANGE_TOOL_MAJOR = 6;
    public static final int MOTION_RANGE_TOOL_MINOR = 7;
    public static final int MOTION_RANGE_TOUCH_MAJOR = 4;
    public static final int MOTION_RANGE_TOUCH_MINOR = 5;
    public static final int MOTION_RANGE_X = 0;
    public static final int MOTION_RANGE_Y = 1;
    public static final int SOURCE_ANY = -256;
    public static final int SOURCE_BLUETOOTH_STYLUS = 49154;
    public static final int SOURCE_CLASS_BUTTON = 1;
    public static final int SOURCE_CLASS_JOYSTICK = 16;
    public static final int SOURCE_CLASS_MASK = 255;
    public static final int SOURCE_CLASS_NONE = 0;
    public static final int SOURCE_CLASS_POINTER = 2;
    public static final int SOURCE_CLASS_POSITION = 8;
    public static final int SOURCE_CLASS_TRACKBALL = 4;
    public static final int SOURCE_DPAD = 513;
    public static final int SOURCE_GAMEPAD = 1025;
    public static final int SOURCE_HDMI = 0x2000001;
    public static final int SOURCE_JOYSTICK = 0x1000010;
    public static final int SOURCE_KEYBOARD = 257;
    public static final int SOURCE_MOUSE = 8194;
    public static final int SOURCE_MOUSE_RELATIVE = 0x20004;
    public static final int SOURCE_ROTARY_ENCODER = 0x400000;
    public static final int SOURCE_STYLUS = 16386;
    public static final int SOURCE_TOUCHPAD = 0x100008;
    public static final int SOURCE_TOUCHSCREEN = 4098;
    public static final int SOURCE_TOUCH_NAVIGATION = 0x200000;
    public static final int SOURCE_TRACKBALL = 0x10004;
    public static final int SOURCE_UNKNOWN = 0;
    private final int mControllerNumber;
    private final String mDescriptor;
    private final int mGeneration;
    private final boolean mHasButtonUnderPad;
    private final boolean mHasMicrophone;
    private final boolean mHasVibrator;
    private final int mId;
    private final InputDeviceIdentifier mIdentifier;
    private final boolean mIsExternal;
    private final KeyCharacterMap mKeyCharacterMap;
    private final int mKeyboardType;
    private final ArrayList mMotionRanges;
    private final String mName;
    private final int mProductId;
    private final int mSources;
    private final int mVendorId;
    private Vibrator mVibrator;

}
