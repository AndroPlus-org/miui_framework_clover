// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

// Referenced classes of package android.view:
//            InputEvent, KeyCharacterMap

public class KeyEvent extends InputEvent
    implements Parcelable
{
    public static interface Callback
    {

        public abstract boolean onKeyDown(int i, KeyEvent keyevent);

        public abstract boolean onKeyLongPress(int i, KeyEvent keyevent);

        public abstract boolean onKeyMultiple(int i, int j, KeyEvent keyevent);

        public abstract boolean onKeyUp(int i, KeyEvent keyevent);
    }

    public static class DispatcherState
    {

        public void handleUpEvent(KeyEvent keyevent)
        {
            int i = keyevent.getKeyCode();
            int j = mActiveLongPresses.indexOfKey(i);
            if(j >= 0)
            {
                KeyEvent._2D_set0(keyevent, KeyEvent._2D_get0(keyevent) | 0x120);
                mActiveLongPresses.removeAt(j);
            }
            if(mDownKeyCode == i)
            {
                KeyEvent._2D_set0(keyevent, KeyEvent._2D_get0(keyevent) | 0x200);
                mDownKeyCode = 0;
                mDownTarget = null;
            }
        }

        public boolean isTracking(KeyEvent keyevent)
        {
            boolean flag;
            if(mDownKeyCode == keyevent.getKeyCode())
                flag = true;
            else
                flag = false;
            return flag;
        }

        public void performedLongPress(KeyEvent keyevent)
        {
            mActiveLongPresses.put(keyevent.getKeyCode(), 1);
        }

        public void reset()
        {
            mDownKeyCode = 0;
            mDownTarget = null;
            mActiveLongPresses.clear();
        }

        public void reset(Object obj)
        {
            if(mDownTarget == obj)
            {
                mDownKeyCode = 0;
                mDownTarget = null;
            }
        }

        public void startTracking(KeyEvent keyevent, Object obj)
        {
            if(keyevent.getAction() != 0)
            {
                throw new IllegalArgumentException("Can only start tracking on a down event");
            } else
            {
                mDownKeyCode = keyevent.getKeyCode();
                mDownTarget = obj;
                return;
            }
        }

        SparseIntArray mActiveLongPresses;
        int mDownKeyCode;
        Object mDownTarget;

        public DispatcherState()
        {
            mActiveLongPresses = new SparseIntArray();
        }
    }


    static int _2D_get0(KeyEvent keyevent)
    {
        return keyevent.mFlags;
    }

    static int _2D_set0(KeyEvent keyevent, int i)
    {
        keyevent.mFlags = i;
        return i;
    }

    private KeyEvent()
    {
    }

    public KeyEvent(int i, int j)
    {
        mAction = i;
        mKeyCode = j;
        mRepeatCount = 0;
        mDeviceId = -1;
    }

    public KeyEvent(long l, long l1, int i, int j, int k)
    {
        mDownTime = l;
        mEventTime = l1;
        mAction = i;
        mKeyCode = j;
        mRepeatCount = k;
        mDeviceId = -1;
    }

    public KeyEvent(long l, long l1, int i, int j, int k, 
            int i1)
    {
        mDownTime = l;
        mEventTime = l1;
        mAction = i;
        mKeyCode = j;
        mRepeatCount = k;
        mMetaState = i1;
        mDeviceId = -1;
    }

    public KeyEvent(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1)
    {
        mDownTime = l;
        mEventTime = l1;
        mAction = i;
        mKeyCode = j;
        mRepeatCount = k;
        mMetaState = i1;
        mDeviceId = j1;
        mScanCode = k1;
    }

    public KeyEvent(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, int i2)
    {
        mDownTime = l;
        mEventTime = l1;
        mAction = i;
        mKeyCode = j;
        mRepeatCount = k;
        mMetaState = i1;
        mDeviceId = j1;
        mScanCode = k1;
        mFlags = i2;
    }

    public KeyEvent(long l, long l1, int i, int j, int k, 
            int i1, int j1, int k1, int i2, int j2)
    {
        mDownTime = l;
        mEventTime = l1;
        mAction = i;
        mKeyCode = j;
        mRepeatCount = k;
        mMetaState = i1;
        mDeviceId = j1;
        mScanCode = k1;
        mFlags = i2;
        mSource = j2;
    }

    public KeyEvent(long l, String s, int i, int j)
    {
        mDownTime = l;
        mEventTime = l;
        mCharacters = s;
        mAction = 2;
        mKeyCode = 0;
        mRepeatCount = 0;
        mDeviceId = i;
        mFlags = j;
        mSource = 257;
    }

    private KeyEvent(Parcel parcel)
    {
        mDeviceId = parcel.readInt();
        mSource = parcel.readInt();
        mAction = parcel.readInt();
        mKeyCode = parcel.readInt();
        mRepeatCount = parcel.readInt();
        mMetaState = parcel.readInt();
        mScanCode = parcel.readInt();
        mFlags = parcel.readInt();
        mDownTime = parcel.readLong();
        mEventTime = parcel.readLong();
    }

    public KeyEvent(KeyEvent keyevent)
    {
        mDownTime = keyevent.mDownTime;
        mEventTime = keyevent.mEventTime;
        mAction = keyevent.mAction;
        mKeyCode = keyevent.mKeyCode;
        mRepeatCount = keyevent.mRepeatCount;
        mMetaState = keyevent.mMetaState;
        mDeviceId = keyevent.mDeviceId;
        mSource = keyevent.mSource;
        mScanCode = keyevent.mScanCode;
        mFlags = keyevent.mFlags;
        mCharacters = keyevent.mCharacters;
    }

    private KeyEvent(KeyEvent keyevent, int i)
    {
        mDownTime = keyevent.mDownTime;
        mEventTime = keyevent.mEventTime;
        mAction = i;
        mKeyCode = keyevent.mKeyCode;
        mRepeatCount = keyevent.mRepeatCount;
        mMetaState = keyevent.mMetaState;
        mDeviceId = keyevent.mDeviceId;
        mSource = keyevent.mSource;
        mScanCode = keyevent.mScanCode;
        mFlags = keyevent.mFlags;
    }

    public KeyEvent(KeyEvent keyevent, long l, int i)
    {
        mDownTime = keyevent.mDownTime;
        mEventTime = l;
        mAction = keyevent.mAction;
        mKeyCode = keyevent.mKeyCode;
        mRepeatCount = i;
        mMetaState = keyevent.mMetaState;
        mDeviceId = keyevent.mDeviceId;
        mSource = keyevent.mSource;
        mScanCode = keyevent.mScanCode;
        mFlags = keyevent.mFlags;
        mCharacters = keyevent.mCharacters;
    }

    public static String actionToString(int i)
    {
        switch(i)
        {
        default:
            return Integer.toString(i);

        case 0: // '\0'
            return "ACTION_DOWN";

        case 1: // '\001'
            return "ACTION_UP";

        case 2: // '\002'
            return "ACTION_MULTIPLE";
        }
    }

    public static KeyEvent changeAction(KeyEvent keyevent, int i)
    {
        return new KeyEvent(keyevent, i);
    }

    public static KeyEvent changeFlags(KeyEvent keyevent, int i)
    {
        keyevent = new KeyEvent(keyevent);
        keyevent.mFlags = i;
        return keyevent;
    }

    public static KeyEvent changeTimeRepeat(KeyEvent keyevent, long l, int i)
    {
        return new KeyEvent(keyevent, l, i);
    }

    public static KeyEvent changeTimeRepeat(KeyEvent keyevent, long l, int i, int j)
    {
        keyevent = new KeyEvent(keyevent);
        keyevent.mEventTime = l;
        keyevent.mRepeatCount = i;
        keyevent.mFlags = j;
        return keyevent;
    }

    public static KeyEvent createFromParcelBody(Parcel parcel)
    {
        return new KeyEvent(parcel);
    }

    public static int getDeadChar(int i, int j)
    {
        return KeyCharacterMap.getDeadChar(i, j);
    }

    public static int getMaxKeyCode()
    {
        return 284;
    }

    public static int getModifierMetaStateMask()
    {
        return 0x770ff;
    }

    public static final boolean isAltKey(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 57)
            if(i == 58)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static final boolean isConfirmKey(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 23: // '\027'
        case 62: // '>'
        case 66: // 'B'
        case 160: 
            return true;
        }
    }

    public static final boolean isGamepadButton(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 96: // '`'
        case 97: // 'a'
        case 98: // 'b'
        case 99: // 'c'
        case 100: // 'd'
        case 101: // 'e'
        case 102: // 'f'
        case 103: // 'g'
        case 104: // 'h'
        case 105: // 'i'
        case 106: // 'j'
        case 107: // 'k'
        case 108: // 'l'
        case 109: // 'm'
        case 110: // 'n'
        case 188: 
        case 189: 
        case 190: 
        case 191: 
        case 192: 
        case 193: 
        case 194: 
        case 195: 
        case 196: 
        case 197: 
        case 198: 
        case 199: 
        case 200: 
        case 201: 
        case 202: 
        case 203: 
            return true;
        }
    }

    public static final boolean isMediaKey(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 79: // 'O'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        case 90: // 'Z'
        case 91: // '['
        case 126: // '~'
        case 127: // '\177'
        case 130: 
            return true;
        }
    }

    public static final boolean isMetaKey(int i)
    {
        boolean flag = true;
        boolean flag1 = flag;
        if(i != 117)
            if(i == 118)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public static boolean isModifierKey(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 57: // '9'
        case 58: // ':'
        case 59: // ';'
        case 60: // '<'
        case 63: // '?'
        case 78: // 'N'
        case 113: // 'q'
        case 114: // 'r'
        case 117: // 'u'
        case 118: // 'v'
        case 119: // 'w'
            return true;
        }
    }

    public static final boolean isSystemKey(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 24: // '\030'
        case 25: // '\031'
        case 26: // '\032'
        case 27: // '\033'
        case 79: // 'O'
        case 80: // 'P'
        case 82: // 'R'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        case 88: // 'X'
        case 89: // 'Y'
        case 90: // 'Z'
        case 91: // '['
        case 126: // '~'
        case 127: // '\177'
        case 130: 
        case 164: 
        case 220: 
        case 221: 
        case 222: 
        case 280: 
        case 281: 
        case 282: 
        case 283: 
            return true;
        }
    }

    public static final boolean isWakeKey(int i)
    {
        switch(i)
        {
        default:
            return false;

        case 3: // '\003'
        case 224: 
        case 225: 
        case 265: 
        case 266: 
        case 267: 
            return true;
        }
    }

    public static int keyCodeFromString(String s)
    {
        String s1 = s;
        if(s.startsWith("KEYCODE_"))
        {
            s1 = s.substring("KEYCODE_".length());
            int i = nativeKeyCodeFromString(s1);
            if(i > 0)
                return i;
        }
        int j;
        try
        {
            j = Integer.parseInt(s1, 10);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return 0;
        }
        return j;
    }

    public static String keyCodeToString(int i)
    {
        String s = nativeKeyCodeToString(i);
        if(s != null)
            s = (new StringBuilder()).append("KEYCODE_").append(s).toString();
        else
            s = Integer.toString(i);
        return s;
    }

    private static int metaStateFilterDirectionalModifiers(int i, int j, int k, int l, int i1)
    {
        boolean flag;
        int j1;
        if((j & k) != 0)
            flag = true;
        else
            flag = false;
        j1 = l | i1;
        if((j & j1) != 0)
            j = 1;
        else
            j = 0;
        if(flag)
            if(j != 0)
                throw new IllegalArgumentException((new StringBuilder()).append("modifiers must not contain ").append(metaStateToString(k)).append(" combined with ").append(metaStateToString(l)).append(" or ").append(metaStateToString(i1)).toString());
            else
                return j1 & i;
        if(j != 0)
            return k & i;
        else
            return i;
    }

    public static boolean metaStateHasModifiers(int i, int j)
    {
        boolean flag = true;
        if((0x700f00 & j) != 0)
            throw new IllegalArgumentException("modifiers must not contain META_CAPS_LOCK_ON, META_NUM_LOCK_ON, META_SCROLL_LOCK_ON, META_CAP_LOCKED, META_ALT_LOCKED, META_SYM_LOCKED, or META_SELECTING");
        if(metaStateFilterDirectionalModifiers(metaStateFilterDirectionalModifiers(metaStateFilterDirectionalModifiers(metaStateFilterDirectionalModifiers(normalizeMetaState(i) & 0x770ff, j, 1, 64, 128), j, 2, 16, 32), j, 4096, 8192, 16384), j, 0x10000, 0x20000, 0x40000) != j)
            flag = false;
        return flag;
    }

    public static boolean metaStateHasNoModifiers(int i)
    {
        boolean flag = false;
        if((normalizeMetaState(i) & 0x770ff) == 0)
            flag = true;
        return flag;
    }

    public static String metaStateToString(int i)
    {
        if(i == 0)
            return "0";
        Object obj = null;
        int j = 0;
        while(i != 0) 
        {
            boolean flag;
            Object obj1;
            if((i & 1) != 0)
                flag = true;
            else
                flag = false;
            i >>>= 1;
            obj1 = obj;
            if(flag)
            {
                obj1 = META_SYMBOLIC_NAMES[j];
                if(obj == null)
                {
                    if(i == 0)
                        return ((String) (obj1));
                    obj1 = new StringBuilder(((String) (obj1)));
                } else
                {
                    ((StringBuilder) (obj)).append('|');
                    ((StringBuilder) (obj)).append(((String) (obj1)));
                    obj1 = obj;
                }
            }
            j++;
            obj = obj1;
        }
        return ((StringBuilder) (obj)).toString();
    }

    private static native int nativeKeyCodeFromString(String s);

    private static native String nativeKeyCodeToString(int i);

    public static int normalizeMetaState(int i)
    {
        int j = i;
        if((i & 0xc0) != 0)
            j = i | 1;
        i = j;
        if((j & 0x30) != 0)
            i = j | 2;
        j = i;
        if((i & 0x6000) != 0)
            j = i | 0x1000;
        i = j;
        if((0x60000 & j) != 0)
            i = j | 0x10000;
        j = i;
        if((i & 0x100) != 0)
            j = i | 0x100000;
        i = j;
        if((j & 0x200) != 0)
            i = j | 2;
        j = i;
        if((i & 0x400) != 0)
            j = i | 4;
        return 0x7770ff & j;
    }

    private static KeyEvent obtain()
    {
        Object obj = gRecyclerLock;
        obj;
        JVM INSTR monitorenter ;
        KeyEvent keyevent = gRecyclerTop;
        if(keyevent != null)
            break MISSING_BLOCK_LABEL_26;
        keyevent = new KeyEvent();
        obj;
        JVM INSTR monitorexit ;
        return keyevent;
        gRecyclerTop = keyevent.mNext;
        gRecyclerUsed--;
        obj;
        JVM INSTR monitorexit ;
        keyevent.mNext = null;
        keyevent.prepareForReuse();
        return keyevent;
        Exception exception;
        exception;
        throw exception;
    }

    public static KeyEvent obtain(long l, long l1, int i, int j, int k, int i1, 
            int j1, int k1, int i2, int j2, String s)
    {
        KeyEvent keyevent = obtain();
        keyevent.mDownTime = l;
        keyevent.mEventTime = l1;
        keyevent.mAction = i;
        keyevent.mKeyCode = j;
        keyevent.mRepeatCount = k;
        keyevent.mMetaState = i1;
        keyevent.mDeviceId = j1;
        keyevent.mScanCode = k1;
        keyevent.mFlags = i2;
        keyevent.mSource = j2;
        keyevent.mCharacters = s;
        return keyevent;
    }

    public static KeyEvent obtain(KeyEvent keyevent)
    {
        KeyEvent keyevent1 = obtain();
        keyevent1.mDownTime = keyevent.mDownTime;
        keyevent1.mEventTime = keyevent.mEventTime;
        keyevent1.mAction = keyevent.mAction;
        keyevent1.mKeyCode = keyevent.mKeyCode;
        keyevent1.mRepeatCount = keyevent.mRepeatCount;
        keyevent1.mMetaState = keyevent.mMetaState;
        keyevent1.mDeviceId = keyevent.mDeviceId;
        keyevent1.mScanCode = keyevent.mScanCode;
        keyevent1.mFlags = keyevent.mFlags;
        keyevent1.mSource = keyevent.mSource;
        keyevent1.mCharacters = keyevent.mCharacters;
        return keyevent1;
    }

    public final void cancel()
    {
        mFlags = mFlags | 0x20;
    }

    public volatile InputEvent copy()
    {
        return copy();
    }

    public KeyEvent copy()
    {
        return obtain(this);
    }

    public final boolean dispatch(Callback callback)
    {
        return dispatch(callback, null, null);
    }

    public final boolean dispatch(Callback callback, DispatcherState dispatcherstate, Object obj)
    {
        mAction;
        JVM INSTR tableswitch 0 2: default 32
    //                   0 34
    //                   1 154
    //                   2 175;
           goto _L1 _L2 _L3 _L4
_L1:
        return false;
_L2:
        boolean flag;
        boolean flag1;
        mFlags = mFlags & 0xbfffffff;
        flag = callback.onKeyDown(mKeyCode, this);
        flag1 = flag;
        if(dispatcherstate == null) goto _L6; else goto _L5
_L5:
        if(!flag || mRepeatCount != 0 || (mFlags & 0x40000000) == 0) goto _L8; else goto _L7
_L7:
        dispatcherstate.startTracking(this, obj);
        flag1 = flag;
_L6:
        return flag1;
_L8:
        flag1 = flag;
        if(!isLongPress())
            continue; /* Loop/switch isn't completed */
        flag1 = flag;
        if(!dispatcherstate.isTracking(this))
            continue; /* Loop/switch isn't completed */
        flag1 = flag;
        if(!callback.onKeyLongPress(mKeyCode, this))
            continue; /* Loop/switch isn't completed */
        dispatcherstate.performedLongPress(this);
        flag1 = true;
        continue; /* Loop/switch isn't completed */
_L3:
        if(dispatcherstate != null)
            dispatcherstate.handleUpEvent(this);
        return callback.onKeyUp(mKeyCode, this);
_L4:
        int i = mRepeatCount;
        int j = mKeyCode;
        if(callback.onKeyMultiple(j, i, this))
            return true;
        if(j != 0)
        {
            mAction = 0;
            mRepeatCount = 0;
            flag1 = callback.onKeyDown(j, this);
            if(flag1)
            {
                mAction = 1;
                callback.onKeyUp(j, this);
            }
            mAction = 2;
            mRepeatCount = i;
            return flag1;
        } else
        {
            return false;
        }
        callback;
        flag1 = flag;
        if(true) goto _L6; else goto _L9
_L9:
    }

    public final int getAction()
    {
        return mAction;
    }

    public final String getCharacters()
    {
        return mCharacters;
    }

    public final int getDeviceId()
    {
        return mDeviceId;
    }

    public char getDisplayLabel()
    {
        return getKeyCharacterMap().getDisplayLabel(mKeyCode);
    }

    public final long getDownTime()
    {
        return mDownTime;
    }

    public final long getEventTime()
    {
        return mEventTime;
    }

    public final long getEventTimeNano()
    {
        return mEventTime * 0xf4240L;
    }

    public final int getFlags()
    {
        return mFlags;
    }

    public final KeyCharacterMap getKeyCharacterMap()
    {
        return KeyCharacterMap.load(mDeviceId);
    }

    public final int getKeyCode()
    {
        return mKeyCode;
    }

    public boolean getKeyData(KeyCharacterMap.KeyData keydata)
    {
        return getKeyCharacterMap().getKeyData(mKeyCode, keydata);
    }

    public final int getKeyboardDevice()
    {
        return mDeviceId;
    }

    public char getMatch(char ac[])
    {
        return getMatch(ac, 0);
    }

    public char getMatch(char ac[], int i)
    {
        return getKeyCharacterMap().getMatch(mKeyCode, ac, i);
    }

    public final int getMetaState()
    {
        return mMetaState;
    }

    public final int getModifiers()
    {
        return normalizeMetaState(mMetaState) & 0x770ff;
    }

    public char getNumber()
    {
        return getKeyCharacterMap().getNumber(mKeyCode);
    }

    public final int getRepeatCount()
    {
        return mRepeatCount;
    }

    public final int getScanCode()
    {
        return mScanCode;
    }

    public final int getSource()
    {
        return mSource;
    }

    public int getUnicodeChar()
    {
        return getUnicodeChar(mMetaState);
    }

    public int getUnicodeChar(int i)
    {
        return getKeyCharacterMap().get(mKeyCode, i);
    }

    public final boolean hasModifiers(int i)
    {
        return metaStateHasModifiers(mMetaState, i);
    }

    public final boolean hasNoModifiers()
    {
        return metaStateHasNoModifiers(mMetaState);
    }

    public final boolean isAltPressed()
    {
        boolean flag = false;
        if((mMetaState & 2) != 0)
            flag = true;
        return flag;
    }

    public final boolean isCanceled()
    {
        boolean flag = false;
        if((mFlags & 0x20) != 0)
            flag = true;
        return flag;
    }

    public final boolean isCapsLockOn()
    {
        boolean flag = false;
        if((mMetaState & 0x100000) != 0)
            flag = true;
        return flag;
    }

    public final boolean isCtrlPressed()
    {
        boolean flag = false;
        if((mMetaState & 0x1000) != 0)
            flag = true;
        return flag;
    }

    public final boolean isDown()
    {
        boolean flag = false;
        if(mAction == 0)
            flag = true;
        return flag;
    }

    public final boolean isFunctionPressed()
    {
        boolean flag = false;
        if((mMetaState & 8) != 0)
            flag = true;
        return flag;
    }

    public final boolean isLongPress()
    {
        boolean flag = false;
        if((mFlags & 0x80) != 0)
            flag = true;
        return flag;
    }

    public final boolean isMetaPressed()
    {
        boolean flag = false;
        if((mMetaState & 0x10000) != 0)
            flag = true;
        return flag;
    }

    public final boolean isNumLockOn()
    {
        boolean flag = false;
        if((mMetaState & 0x200000) != 0)
            flag = true;
        return flag;
    }

    public boolean isPrintingKey()
    {
        return getKeyCharacterMap().isPrintingKey(mKeyCode);
    }

    public final boolean isScrollLockOn()
    {
        boolean flag = false;
        if((mMetaState & 0x400000) != 0)
            flag = true;
        return flag;
    }

    public final boolean isShiftPressed()
    {
        boolean flag = false;
        if((mMetaState & 1) != 0)
            flag = true;
        return flag;
    }

    public final boolean isSymPressed()
    {
        boolean flag = false;
        if((mMetaState & 4) != 0)
            flag = true;
        return flag;
    }

    public final boolean isSystem()
    {
        return isSystemKey(mKeyCode);
    }

    public final boolean isTainted()
    {
        boolean flag = false;
        if((mFlags & 0x80000000) != 0)
            flag = true;
        return flag;
    }

    public final boolean isTracking()
    {
        boolean flag = false;
        if((mFlags & 0x200) != 0)
            flag = true;
        return flag;
    }

    public final boolean isWakeKey()
    {
        return isWakeKey(mKeyCode);
    }

    public final void recycle()
    {
        super.recycle();
        mCharacters = null;
        Object obj = gRecyclerLock;
        obj;
        JVM INSTR monitorenter ;
        if(gRecyclerUsed < 10)
        {
            gRecyclerUsed++;
            mNext = gRecyclerTop;
            gRecyclerTop = this;
        }
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void recycleIfNeededAfterDispatch()
    {
    }

    public final void setSource(int i)
    {
        mSource = i;
    }

    public final void setTainted(boolean flag)
    {
        int i;
        if(flag)
            i = mFlags | 0x80000000;
        else
            i = mFlags & 0x7fffffff;
        mFlags = i;
    }

    public final void startTracking()
    {
        mFlags = mFlags | 0x40000000;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("KeyEvent { action=").append(actionToString(mAction));
        stringbuilder.append(", keyCode=").append(keyCodeToString(mKeyCode));
        stringbuilder.append(", scanCode=").append(mScanCode);
        if(mCharacters != null)
            stringbuilder.append(", characters=\"").append(mCharacters).append("\"");
        stringbuilder.append(", metaState=").append(metaStateToString(mMetaState));
        stringbuilder.append(", flags=0x").append(Integer.toHexString(mFlags));
        stringbuilder.append(", repeatCount=").append(mRepeatCount);
        stringbuilder.append(", eventTime=").append(mEventTime);
        stringbuilder.append(", downTime=").append(mDownTime);
        stringbuilder.append(", deviceId=").append(mDeviceId);
        stringbuilder.append(", source=0x").append(Integer.toHexString(mSource));
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(2);
        parcel.writeInt(mDeviceId);
        parcel.writeInt(mSource);
        parcel.writeInt(mAction);
        parcel.writeInt(mKeyCode);
        parcel.writeInt(mRepeatCount);
        parcel.writeInt(mMetaState);
        parcel.writeInt(mScanCode);
        parcel.writeInt(mFlags);
        parcel.writeLong(mDownTime);
        parcel.writeLong(mEventTime);
    }

    public static final int ACTION_DOWN = 0;
    public static final int ACTION_MULTIPLE = 2;
    public static final int ACTION_UP = 1;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeyEvent createFromParcel(Parcel parcel)
        {
            parcel.readInt();
            return KeyEvent.createFromParcelBody(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeyEvent[] newArray(int i)
        {
            return new KeyEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    static final boolean DEBUG = false;
    public static final int FLAG_CANCELED = 32;
    public static final int FLAG_CANCELED_LONG_PRESS = 256;
    public static final int FLAG_EDITOR_ACTION = 16;
    public static final int FLAG_FALLBACK = 1024;
    public static final int FLAG_FROM_SYSTEM = 8;
    public static final int FLAG_KEEP_TOUCH_MODE = 4;
    public static final int FLAG_LONG_PRESS = 128;
    public static final int FLAG_PREDISPATCH = 0x20000000;
    public static final int FLAG_SOFT_KEYBOARD = 2;
    public static final int FLAG_START_TRACKING = 0x40000000;
    public static final int FLAG_TAINTED = 0x80000000;
    public static final int FLAG_TRACKING = 512;
    public static final int FLAG_VIRTUAL_HARD_KEY = 64;
    public static final int FLAG_WOKE_HERE = 1;
    public static final int KEYCODE_0 = 7;
    public static final int KEYCODE_1 = 8;
    public static final int KEYCODE_11 = 227;
    public static final int KEYCODE_12 = 228;
    public static final int KEYCODE_2 = 9;
    public static final int KEYCODE_3 = 10;
    public static final int KEYCODE_3D_MODE = 206;
    public static final int KEYCODE_4 = 11;
    public static final int KEYCODE_5 = 12;
    public static final int KEYCODE_6 = 13;
    public static final int KEYCODE_7 = 14;
    public static final int KEYCODE_8 = 15;
    public static final int KEYCODE_9 = 16;
    public static final int KEYCODE_A = 29;
    public static final int KEYCODE_ALL_APPS = 284;
    public static final int KEYCODE_ALT_LEFT = 57;
    public static final int KEYCODE_ALT_RIGHT = 58;
    public static final int KEYCODE_APOSTROPHE = 75;
    public static final int KEYCODE_APP_SWITCH = 187;
    public static final int KEYCODE_ASSIST = 219;
    public static final int KEYCODE_AT = 77;
    public static final int KEYCODE_AVR_INPUT = 182;
    public static final int KEYCODE_AVR_POWER = 181;
    public static final int KEYCODE_B = 30;
    public static final int KEYCODE_BACK = 4;
    public static final int KEYCODE_BACKSLASH = 73;
    public static final int KEYCODE_BOOKMARK = 174;
    public static final int KEYCODE_BREAK = 121;
    public static final int KEYCODE_BRIGHTNESS_DOWN = 220;
    public static final int KEYCODE_BRIGHTNESS_UP = 221;
    public static final int KEYCODE_BUTTON_1 = 188;
    public static final int KEYCODE_BUTTON_10 = 197;
    public static final int KEYCODE_BUTTON_11 = 198;
    public static final int KEYCODE_BUTTON_12 = 199;
    public static final int KEYCODE_BUTTON_13 = 200;
    public static final int KEYCODE_BUTTON_14 = 201;
    public static final int KEYCODE_BUTTON_15 = 202;
    public static final int KEYCODE_BUTTON_16 = 203;
    public static final int KEYCODE_BUTTON_2 = 189;
    public static final int KEYCODE_BUTTON_3 = 190;
    public static final int KEYCODE_BUTTON_4 = 191;
    public static final int KEYCODE_BUTTON_5 = 192;
    public static final int KEYCODE_BUTTON_6 = 193;
    public static final int KEYCODE_BUTTON_7 = 194;
    public static final int KEYCODE_BUTTON_8 = 195;
    public static final int KEYCODE_BUTTON_9 = 196;
    public static final int KEYCODE_BUTTON_A = 96;
    public static final int KEYCODE_BUTTON_B = 97;
    public static final int KEYCODE_BUTTON_C = 98;
    public static final int KEYCODE_BUTTON_L1 = 102;
    public static final int KEYCODE_BUTTON_L2 = 104;
    public static final int KEYCODE_BUTTON_MODE = 110;
    public static final int KEYCODE_BUTTON_R1 = 103;
    public static final int KEYCODE_BUTTON_R2 = 105;
    public static final int KEYCODE_BUTTON_SELECT = 109;
    public static final int KEYCODE_BUTTON_START = 108;
    public static final int KEYCODE_BUTTON_THUMBL = 106;
    public static final int KEYCODE_BUTTON_THUMBR = 107;
    public static final int KEYCODE_BUTTON_X = 99;
    public static final int KEYCODE_BUTTON_Y = 100;
    public static final int KEYCODE_BUTTON_Z = 101;
    public static final int KEYCODE_C = 31;
    public static final int KEYCODE_CALCULATOR = 210;
    public static final int KEYCODE_CALENDAR = 208;
    public static final int KEYCODE_CALL = 5;
    public static final int KEYCODE_CAMERA = 27;
    public static final int KEYCODE_CAPS_LOCK = 115;
    public static final int KEYCODE_CAPTIONS = 175;
    public static final int KEYCODE_CHANNEL_DOWN = 167;
    public static final int KEYCODE_CHANNEL_UP = 166;
    public static final int KEYCODE_CLEAR = 28;
    public static final int KEYCODE_COMMA = 55;
    public static final int KEYCODE_CONTACTS = 207;
    public static final int KEYCODE_COPY = 278;
    public static final int KEYCODE_CTRL_LEFT = 113;
    public static final int KEYCODE_CTRL_RIGHT = 114;
    public static final int KEYCODE_CUT = 277;
    public static final int KEYCODE_D = 32;
    public static final int KEYCODE_DEL = 67;
    public static final int KEYCODE_DPAD_CENTER = 23;
    public static final int KEYCODE_DPAD_DOWN = 20;
    public static final int KEYCODE_DPAD_DOWN_LEFT = 269;
    public static final int KEYCODE_DPAD_DOWN_RIGHT = 271;
    public static final int KEYCODE_DPAD_LEFT = 21;
    public static final int KEYCODE_DPAD_RIGHT = 22;
    public static final int KEYCODE_DPAD_UP = 19;
    public static final int KEYCODE_DPAD_UP_LEFT = 268;
    public static final int KEYCODE_DPAD_UP_RIGHT = 270;
    public static final int KEYCODE_DVR = 173;
    public static final int KEYCODE_E = 33;
    public static final int KEYCODE_EISU = 212;
    public static final int KEYCODE_ENDCALL = 6;
    public static final int KEYCODE_ENTER = 66;
    public static final int KEYCODE_ENVELOPE = 65;
    public static final int KEYCODE_EQUALS = 70;
    public static final int KEYCODE_ESCAPE = 111;
    public static final int KEYCODE_EXPLORER = 64;
    public static final int KEYCODE_F = 34;
    public static final int KEYCODE_F1 = 131;
    public static final int KEYCODE_F10 = 140;
    public static final int KEYCODE_F11 = 141;
    public static final int KEYCODE_F12 = 142;
    public static final int KEYCODE_F2 = 132;
    public static final int KEYCODE_F3 = 133;
    public static final int KEYCODE_F4 = 134;
    public static final int KEYCODE_F5 = 135;
    public static final int KEYCODE_F6 = 136;
    public static final int KEYCODE_F7 = 137;
    public static final int KEYCODE_F8 = 138;
    public static final int KEYCODE_F9 = 139;
    public static final int KEYCODE_FOCUS = 80;
    public static final int KEYCODE_FORWARD = 125;
    public static final int KEYCODE_FORWARD_DEL = 112;
    public static final int KEYCODE_FUNCTION = 119;
    public static final int KEYCODE_G = 35;
    public static final int KEYCODE_GRAVE = 68;
    public static final int KEYCODE_GUIDE = 172;
    public static final int KEYCODE_H = 36;
    public static final int KEYCODE_HEADSETHOOK = 79;
    public static final int KEYCODE_HELP = 259;
    public static final int KEYCODE_HENKAN = 214;
    public static final int KEYCODE_HOME = 3;
    public static final int KEYCODE_I = 37;
    public static final int KEYCODE_INFO = 165;
    public static final int KEYCODE_INSERT = 124;
    public static final int KEYCODE_J = 38;
    public static final int KEYCODE_K = 39;
    public static final int KEYCODE_KANA = 218;
    public static final int KEYCODE_KATAKANA_HIRAGANA = 215;
    public static final int KEYCODE_L = 40;
    public static final int KEYCODE_LANGUAGE_SWITCH = 204;
    public static final int KEYCODE_LAST_CHANNEL = 229;
    public static final int KEYCODE_LEFT_BRACKET = 71;
    public static final int KEYCODE_M = 41;
    public static final int KEYCODE_MANNER_MODE = 205;
    public static final int KEYCODE_MEDIA_AUDIO_TRACK = 222;
    public static final int KEYCODE_MEDIA_CLOSE = 128;
    public static final int KEYCODE_MEDIA_EJECT = 129;
    public static final int KEYCODE_MEDIA_FAST_FORWARD = 90;
    public static final int KEYCODE_MEDIA_NEXT = 87;
    public static final int KEYCODE_MEDIA_PAUSE = 127;
    public static final int KEYCODE_MEDIA_PLAY = 126;
    public static final int KEYCODE_MEDIA_PLAY_PAUSE = 85;
    public static final int KEYCODE_MEDIA_PREVIOUS = 88;
    public static final int KEYCODE_MEDIA_RECORD = 130;
    public static final int KEYCODE_MEDIA_REWIND = 89;
    public static final int KEYCODE_MEDIA_SKIP_BACKWARD = 273;
    public static final int KEYCODE_MEDIA_SKIP_FORWARD = 272;
    public static final int KEYCODE_MEDIA_STEP_BACKWARD = 275;
    public static final int KEYCODE_MEDIA_STEP_FORWARD = 274;
    public static final int KEYCODE_MEDIA_STOP = 86;
    public static final int KEYCODE_MEDIA_TOP_MENU = 226;
    public static final int KEYCODE_MENU = 82;
    public static final int KEYCODE_META_LEFT = 117;
    public static final int KEYCODE_META_RIGHT = 118;
    public static final int KEYCODE_MINUS = 69;
    public static final int KEYCODE_MOVE_END = 123;
    public static final int KEYCODE_MOVE_HOME = 122;
    public static final int KEYCODE_MUHENKAN = 213;
    public static final int KEYCODE_MUSIC = 209;
    public static final int KEYCODE_MUTE = 91;
    public static final int KEYCODE_N = 42;
    public static final int KEYCODE_NAVIGATE_IN = 262;
    public static final int KEYCODE_NAVIGATE_NEXT = 261;
    public static final int KEYCODE_NAVIGATE_OUT = 263;
    public static final int KEYCODE_NAVIGATE_PREVIOUS = 260;
    public static final int KEYCODE_NOTIFICATION = 83;
    public static final int KEYCODE_NUM = 78;
    public static final int KEYCODE_NUMPAD_0 = 144;
    public static final int KEYCODE_NUMPAD_1 = 145;
    public static final int KEYCODE_NUMPAD_2 = 146;
    public static final int KEYCODE_NUMPAD_3 = 147;
    public static final int KEYCODE_NUMPAD_4 = 148;
    public static final int KEYCODE_NUMPAD_5 = 149;
    public static final int KEYCODE_NUMPAD_6 = 150;
    public static final int KEYCODE_NUMPAD_7 = 151;
    public static final int KEYCODE_NUMPAD_8 = 152;
    public static final int KEYCODE_NUMPAD_9 = 153;
    public static final int KEYCODE_NUMPAD_ADD = 157;
    public static final int KEYCODE_NUMPAD_COMMA = 159;
    public static final int KEYCODE_NUMPAD_DIVIDE = 154;
    public static final int KEYCODE_NUMPAD_DOT = 158;
    public static final int KEYCODE_NUMPAD_ENTER = 160;
    public static final int KEYCODE_NUMPAD_EQUALS = 161;
    public static final int KEYCODE_NUMPAD_LEFT_PAREN = 162;
    public static final int KEYCODE_NUMPAD_MULTIPLY = 155;
    public static final int KEYCODE_NUMPAD_RIGHT_PAREN = 163;
    public static final int KEYCODE_NUMPAD_SUBTRACT = 156;
    public static final int KEYCODE_NUM_LOCK = 143;
    public static final int KEYCODE_O = 43;
    public static final int KEYCODE_P = 44;
    public static final int KEYCODE_PAGE_DOWN = 93;
    public static final int KEYCODE_PAGE_UP = 92;
    public static final int KEYCODE_PAIRING = 225;
    public static final int KEYCODE_PASTE = 279;
    public static final int KEYCODE_PERIOD = 56;
    public static final int KEYCODE_PICTSYMBOLS = 94;
    public static final int KEYCODE_PLUS = 81;
    public static final int KEYCODE_POUND = 18;
    public static final int KEYCODE_POWER = 26;
    public static final int KEYCODE_PROG_BLUE = 186;
    public static final int KEYCODE_PROG_GREEN = 184;
    public static final int KEYCODE_PROG_RED = 183;
    public static final int KEYCODE_PROG_YELLOW = 185;
    public static final int KEYCODE_Q = 45;
    public static final int KEYCODE_R = 46;
    public static final int KEYCODE_RIGHT_BRACKET = 72;
    public static final int KEYCODE_RO = 217;
    public static final int KEYCODE_S = 47;
    public static final int KEYCODE_SCROLL_LOCK = 116;
    public static final int KEYCODE_SEARCH = 84;
    public static final int KEYCODE_SEMICOLON = 74;
    public static final int KEYCODE_SETTINGS = 176;
    public static final int KEYCODE_SHIFT_LEFT = 59;
    public static final int KEYCODE_SHIFT_RIGHT = 60;
    public static final int KEYCODE_SLASH = 76;
    public static final int KEYCODE_SLEEP = 223;
    public static final int KEYCODE_SOFT_LEFT = 1;
    public static final int KEYCODE_SOFT_RIGHT = 2;
    public static final int KEYCODE_SOFT_SLEEP = 276;
    public static final int KEYCODE_SPACE = 62;
    public static final int KEYCODE_STAR = 17;
    public static final int KEYCODE_STB_INPUT = 180;
    public static final int KEYCODE_STB_POWER = 179;
    public static final int KEYCODE_STEM_1 = 265;
    public static final int KEYCODE_STEM_2 = 266;
    public static final int KEYCODE_STEM_3 = 267;
    public static final int KEYCODE_STEM_PRIMARY = 264;
    public static final int KEYCODE_SWITCH_CHARSET = 95;
    public static final int KEYCODE_SYM = 63;
    public static final int KEYCODE_SYSRQ = 120;
    public static final int KEYCODE_SYSTEM_NAVIGATION_DOWN = 281;
    public static final int KEYCODE_SYSTEM_NAVIGATION_LEFT = 282;
    public static final int KEYCODE_SYSTEM_NAVIGATION_RIGHT = 283;
    public static final int KEYCODE_SYSTEM_NAVIGATION_UP = 280;
    public static final int KEYCODE_T = 48;
    public static final int KEYCODE_TAB = 61;
    public static final int KEYCODE_TV = 170;
    public static final int KEYCODE_TV_ANTENNA_CABLE = 242;
    public static final int KEYCODE_TV_AUDIO_DESCRIPTION = 252;
    public static final int KEYCODE_TV_AUDIO_DESCRIPTION_MIX_DOWN = 254;
    public static final int KEYCODE_TV_AUDIO_DESCRIPTION_MIX_UP = 253;
    public static final int KEYCODE_TV_CONTENTS_MENU = 256;
    public static final int KEYCODE_TV_DATA_SERVICE = 230;
    public static final int KEYCODE_TV_INPUT = 178;
    public static final int KEYCODE_TV_INPUT_COMPONENT_1 = 249;
    public static final int KEYCODE_TV_INPUT_COMPONENT_2 = 250;
    public static final int KEYCODE_TV_INPUT_COMPOSITE_1 = 247;
    public static final int KEYCODE_TV_INPUT_COMPOSITE_2 = 248;
    public static final int KEYCODE_TV_INPUT_HDMI_1 = 243;
    public static final int KEYCODE_TV_INPUT_HDMI_2 = 244;
    public static final int KEYCODE_TV_INPUT_HDMI_3 = 245;
    public static final int KEYCODE_TV_INPUT_HDMI_4 = 246;
    public static final int KEYCODE_TV_INPUT_VGA_1 = 251;
    public static final int KEYCODE_TV_MEDIA_CONTEXT_MENU = 257;
    public static final int KEYCODE_TV_NETWORK = 241;
    public static final int KEYCODE_TV_NUMBER_ENTRY = 234;
    public static final int KEYCODE_TV_POWER = 177;
    public static final int KEYCODE_TV_RADIO_SERVICE = 232;
    public static final int KEYCODE_TV_SATELLITE = 237;
    public static final int KEYCODE_TV_SATELLITE_BS = 238;
    public static final int KEYCODE_TV_SATELLITE_CS = 239;
    public static final int KEYCODE_TV_SATELLITE_SERVICE = 240;
    public static final int KEYCODE_TV_TELETEXT = 233;
    public static final int KEYCODE_TV_TERRESTRIAL_ANALOG = 235;
    public static final int KEYCODE_TV_TERRESTRIAL_DIGITAL = 236;
    public static final int KEYCODE_TV_TIMER_PROGRAMMING = 258;
    public static final int KEYCODE_TV_ZOOM_MODE = 255;
    public static final int KEYCODE_U = 49;
    public static final int KEYCODE_UNKNOWN = 0;
    public static final int KEYCODE_V = 50;
    public static final int KEYCODE_VOICE_ASSIST = 231;
    public static final int KEYCODE_VOLUME_DOWN = 25;
    public static final int KEYCODE_VOLUME_MUTE = 164;
    public static final int KEYCODE_VOLUME_UP = 24;
    public static final int KEYCODE_W = 51;
    public static final int KEYCODE_WAKEUP = 224;
    public static final int KEYCODE_WINDOW = 171;
    public static final int KEYCODE_X = 52;
    public static final int KEYCODE_Y = 53;
    public static final int KEYCODE_YEN = 216;
    public static final int KEYCODE_Z = 54;
    public static final int KEYCODE_ZENKAKU_HANKAKU = 211;
    public static final int KEYCODE_ZOOM_IN = 168;
    public static final int KEYCODE_ZOOM_OUT = 169;
    private static final String LABEL_PREFIX = "KEYCODE_";
    private static final int LAST_KEYCODE = 284;
    public static final int MAX_KEYCODE = 84;
    private static final int MAX_RECYCLED = 10;
    private static final int META_ALL_MASK = 0x7770ff;
    public static final int META_ALT_LEFT_ON = 16;
    public static final int META_ALT_LOCKED = 512;
    public static final int META_ALT_MASK = 50;
    public static final int META_ALT_ON = 2;
    public static final int META_ALT_RIGHT_ON = 32;
    public static final int META_CAPS_LOCK_ON = 0x100000;
    public static final int META_CAP_LOCKED = 256;
    public static final int META_CTRL_LEFT_ON = 8192;
    public static final int META_CTRL_MASK = 28672;
    public static final int META_CTRL_ON = 4096;
    public static final int META_CTRL_RIGHT_ON = 16384;
    public static final int META_FUNCTION_ON = 8;
    private static final int META_INVALID_MODIFIER_MASK = 0x700f00;
    private static final int META_LOCK_MASK = 0x700000;
    public static final int META_META_LEFT_ON = 0x20000;
    public static final int META_META_MASK = 0x70000;
    public static final int META_META_ON = 0x10000;
    public static final int META_META_RIGHT_ON = 0x40000;
    private static final int META_MODIFIER_MASK = 0x770ff;
    public static final int META_NUM_LOCK_ON = 0x200000;
    public static final int META_SCROLL_LOCK_ON = 0x400000;
    public static final int META_SELECTING = 2048;
    public static final int META_SHIFT_LEFT_ON = 64;
    public static final int META_SHIFT_MASK = 193;
    public static final int META_SHIFT_ON = 1;
    public static final int META_SHIFT_RIGHT_ON = 128;
    private static final String META_SYMBOLIC_NAMES[] = {
        "META_SHIFT_ON", "META_ALT_ON", "META_SYM_ON", "META_FUNCTION_ON", "META_ALT_LEFT_ON", "META_ALT_RIGHT_ON", "META_SHIFT_LEFT_ON", "META_SHIFT_RIGHT_ON", "META_CAP_LOCKED", "META_ALT_LOCKED", 
        "META_SYM_LOCKED", "0x00000800", "META_CTRL_ON", "META_CTRL_LEFT_ON", "META_CTRL_RIGHT_ON", "0x00008000", "META_META_ON", "META_META_LEFT_ON", "META_META_RIGHT_ON", "0x00080000", 
        "META_CAPS_LOCK_ON", "META_NUM_LOCK_ON", "META_SCROLL_LOCK_ON", "0x00800000", "0x01000000", "0x02000000", "0x04000000", "0x08000000", "0x10000000", "0x20000000", 
        "0x40000000", "0x80000000"
    };
    public static final int META_SYM_LOCKED = 1024;
    public static final int META_SYM_ON = 4;
    private static final int META_SYNTHETIC_MASK = 3840;
    static final String TAG = "KeyEvent";
    private static final Object gRecyclerLock = new Object();
    private static KeyEvent gRecyclerTop;
    private static int gRecyclerUsed;
    private int mAction;
    private String mCharacters;
    private int mDeviceId;
    private long mDownTime;
    private long mEventTime;
    private int mFlags;
    private int mKeyCode;
    private int mMetaState;
    private KeyEvent mNext;
    private int mRepeatCount;
    private int mScanCode;
    private int mSource;

}
