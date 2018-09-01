// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.hardware.input.InputManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AndroidRuntimeException;
import android.util.SparseIntArray;
import java.text.Normalizer;

// Referenced classes of package android.view:
//            InputDevice, KeyEvent

public class KeyCharacterMap
    implements Parcelable
{
    public static final class FallbackAction
    {

        public static FallbackAction obtain()
        {
            Object obj = sRecycleLock;
            obj;
            JVM INSTR monitorenter ;
            FallbackAction fallbackaction;
            if(sRecycleBin != null)
                break MISSING_BLOCK_LABEL_24;
            fallbackaction = new FallbackAction();
_L1:
            obj;
            JVM INSTR monitorexit ;
            return fallbackaction;
            fallbackaction = sRecycleBin;
            sRecycleBin = fallbackaction.next;
            sRecycledCount--;
            fallbackaction.next = null;
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        public void recycle()
        {
            Object obj = sRecycleLock;
            obj;
            JVM INSTR monitorenter ;
            if(sRecycledCount >= 10)
                break MISSING_BLOCK_LABEL_36;
            next = sRecycleBin;
            sRecycleBin = this;
            sRecycledCount++;
_L1:
            obj;
            JVM INSTR monitorexit ;
            return;
            next = null;
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        private static final int MAX_RECYCLED = 10;
        private static FallbackAction sRecycleBin;
        private static final Object sRecycleLock = new Object();
        private static int sRecycledCount;
        public int keyCode;
        public int metaState;
        private FallbackAction next;


        private FallbackAction()
        {
        }
    }

    public static class KeyData
    {

        public static final int META_LENGTH = 4;
        public char displayLabel;
        public char meta[];
        public char number;

        public KeyData()
        {
            meta = new char[4];
        }
    }

    public static class UnavailableException extends AndroidRuntimeException
    {

        public UnavailableException(String s)
        {
            super(s);
        }
    }


    private KeyCharacterMap(long l)
    {
        mPtr = l;
    }

    private KeyCharacterMap(Parcel parcel)
    {
        if(parcel == null)
            throw new IllegalArgumentException("parcel must not be null");
        mPtr = nativeReadFromParcel(parcel);
        if(mPtr == 0L)
            throw new RuntimeException("Could not read KeyCharacterMap from parcel.");
        else
            return;
    }

    KeyCharacterMap(Parcel parcel, KeyCharacterMap keycharactermap)
    {
        this(parcel);
    }

    private static void addCombining(int i, int j)
    {
        sCombiningToAccent.append(i, j);
        sAccentToCombining.append(j, i);
    }

    private static void addDeadKey(int i, int j, int k)
    {
        i = sAccentToCombining.get(i);
        if(i == 0)
        {
            throw new IllegalStateException("Invalid dead key declaration.");
        } else
        {
            sDeadKeyCache.put(i << 16 | j, k);
            return;
        }
    }

    public static boolean deviceHasKey(int i)
    {
        return InputManager.getInstance().deviceHasKeys(new int[] {
            i
        })[0];
    }

    public static boolean[] deviceHasKeys(int ai[])
    {
        return InputManager.getInstance().deviceHasKeys(ai);
    }

    public static int getDeadChar(int i, int j)
    {
        int k;
        int l;
        if(j == i || 32 == j)
            return i;
        k = sAccentToCombining.get(i);
        if(k == 0)
            return 0;
        l = k << 16 | j;
        SparseIntArray sparseintarray = sDeadKeyCache;
        sparseintarray;
        JVM INSTR monitorenter ;
        int i1 = sDeadKeyCache.get(l, -1);
        i = i1;
        if(i1 != -1) goto _L2; else goto _L1
_L1:
        sDeadKeyBuilder.setLength(0);
        sDeadKeyBuilder.append((char)j);
        sDeadKeyBuilder.append((char)k);
        String s = Normalizer.normalize(sDeadKeyBuilder, java.text.Normalizer.Form.NFC);
        if(s.codePointCount(0, s.length()) != 1)
            break MISSING_BLOCK_LABEL_132;
        i = s.codePointAt(0);
_L3:
        sDeadKeyCache.put(l, i);
_L2:
        sparseintarray;
        JVM INSTR monitorexit ;
        return i;
        i = 0;
          goto _L3
        Exception exception;
        exception;
        throw exception;
    }

    public static KeyCharacterMap load(int i)
    {
        InputManager inputmanager = InputManager.getInstance();
        InputDevice inputdevice = inputmanager.getInputDevice(i);
        InputDevice inputdevice2 = inputdevice;
        if(inputdevice == null)
        {
            InputDevice inputdevice1 = inputmanager.getInputDevice(-1);
            inputdevice2 = inputdevice1;
            if(inputdevice1 == null)
                throw new UnavailableException((new StringBuilder()).append("Could not load key character map for device ").append(i).toString());
        }
        return inputdevice2.getKeyCharacterMap();
    }

    private static native void nativeDispose(long l);

    private static native char nativeGetCharacter(long l, int i, int j);

    private static native char nativeGetDisplayLabel(long l, int i);

    private static native KeyEvent[] nativeGetEvents(long l, char ac[]);

    private static native boolean nativeGetFallbackAction(long l, int i, int j, FallbackAction fallbackaction);

    private static native int nativeGetKeyboardType(long l);

    private static native char nativeGetMatch(long l, int i, char ac[], int j);

    private static native char nativeGetNumber(long l, int i);

    private static native long nativeReadFromParcel(Parcel parcel);

    private static native void nativeWriteToParcel(long l, Parcel parcel);

    public int describeContents()
    {
        return 0;
    }

    protected void finalize()
        throws Throwable
    {
        if(mPtr != 0L)
        {
            nativeDispose(mPtr);
            mPtr = 0L;
        }
    }

    public int get(int i, int j)
    {
        j = KeyEvent.normalizeMetaState(j);
        i = nativeGetCharacter(mPtr, i, j);
        j = sCombiningToAccent.get(i);
        if(j != 0)
            return 0x80000000 | j;
        else
            return i;
    }

    public char getDisplayLabel(int i)
    {
        return nativeGetDisplayLabel(mPtr, i);
    }

    public KeyEvent[] getEvents(char ac[])
    {
        if(ac == null)
            throw new IllegalArgumentException("chars must not be null.");
        else
            return nativeGetEvents(mPtr, ac);
    }

    public FallbackAction getFallbackAction(int i, int j)
    {
        FallbackAction fallbackaction = FallbackAction.obtain();
        j = KeyEvent.normalizeMetaState(j);
        if(nativeGetFallbackAction(mPtr, i, j, fallbackaction))
        {
            fallbackaction.metaState = KeyEvent.normalizeMetaState(fallbackaction.metaState);
            return fallbackaction;
        } else
        {
            fallbackaction.recycle();
            return null;
        }
    }

    public boolean getKeyData(int i, KeyData keydata)
    {
        if(keydata.meta.length < 4)
            throw new IndexOutOfBoundsException("results.meta.length must be >= 4");
        int j = nativeGetDisplayLabel(mPtr, i);
        if(j == 0)
        {
            return false;
        } else
        {
            keydata.displayLabel = (char)j;
            keydata.number = nativeGetNumber(mPtr, i);
            keydata.meta[0] = nativeGetCharacter(mPtr, i, 0);
            keydata.meta[1] = nativeGetCharacter(mPtr, i, 1);
            keydata.meta[2] = nativeGetCharacter(mPtr, i, 2);
            keydata.meta[3] = nativeGetCharacter(mPtr, i, 3);
            return true;
        }
    }

    public int getKeyboardType()
    {
        return nativeGetKeyboardType(mPtr);
    }

    public char getMatch(int i, char ac[])
    {
        return getMatch(i, ac, 0);
    }

    public char getMatch(int i, char ac[], int j)
    {
        if(ac == null)
        {
            throw new IllegalArgumentException("chars must not be null.");
        } else
        {
            j = KeyEvent.normalizeMetaState(j);
            return nativeGetMatch(mPtr, i, ac, j);
        }
    }

    public int getModifierBehavior()
    {
        switch(getKeyboardType())
        {
        default:
            return 1;

        case 4: // '\004'
        case 5: // '\005'
            return 0;
        }
    }

    public char getNumber(int i)
    {
        return nativeGetNumber(mPtr, i);
    }

    public boolean isPrintingKey(int i)
    {
        switch(Character.getType(nativeGetDisplayLabel(mPtr, i)))
        {
        default:
            return true;

        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
            return false;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(parcel == null)
        {
            throw new IllegalArgumentException("parcel must not be null");
        } else
        {
            nativeWriteToParcel(mPtr, parcel);
            return;
        }
    }

    private static final int ACCENT_ACUTE = 180;
    private static final int ACCENT_BREVE = 728;
    private static final int ACCENT_CARON = 711;
    private static final int ACCENT_CEDILLA = 184;
    private static final int ACCENT_CIRCUMFLEX = 710;
    private static final int ACCENT_CIRCUMFLEX_LEGACY = 94;
    private static final int ACCENT_COMMA_ABOVE = 8125;
    private static final int ACCENT_COMMA_ABOVE_RIGHT = 700;
    private static final int ACCENT_DOT_ABOVE = 729;
    private static final int ACCENT_DOT_BELOW = 46;
    private static final int ACCENT_DOUBLE_ACUTE = 733;
    private static final int ACCENT_GRAVE = 715;
    private static final int ACCENT_GRAVE_LEGACY = 96;
    private static final int ACCENT_HOOK_ABOVE = 704;
    private static final int ACCENT_HORN = 39;
    private static final int ACCENT_MACRON = 175;
    private static final int ACCENT_MACRON_BELOW = 717;
    private static final int ACCENT_OGONEK = 731;
    private static final int ACCENT_REVERSED_COMMA_ABOVE = 701;
    private static final int ACCENT_RING_ABOVE = 730;
    private static final int ACCENT_STROKE = 45;
    private static final int ACCENT_TILDE = 732;
    private static final int ACCENT_TILDE_LEGACY = 126;
    private static final int ACCENT_TURNED_COMMA_ABOVE = 699;
    private static final int ACCENT_UMLAUT = 168;
    private static final int ACCENT_VERTICAL_LINE_ABOVE = 712;
    private static final int ACCENT_VERTICAL_LINE_BELOW = 716;
    public static final int ALPHA = 3;
    public static final int BUILT_IN_KEYBOARD = 0;
    private static final int CHAR_SPACE = 32;
    public static final int COMBINING_ACCENT = 0x80000000;
    public static final int COMBINING_ACCENT_MASK = 0x7fffffff;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public KeyCharacterMap createFromParcel(Parcel parcel)
        {
            return new KeyCharacterMap(parcel, null);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public KeyCharacterMap[] newArray(int i)
        {
            return new KeyCharacterMap[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FULL = 4;
    public static final char HEX_INPUT = 61184;
    public static final int MODIFIER_BEHAVIOR_CHORDED = 0;
    public static final int MODIFIER_BEHAVIOR_CHORDED_OR_TOGGLED = 1;
    public static final int NUMERIC = 1;
    public static final char PICKER_DIALOG_INPUT = 61185;
    public static final int PREDICTIVE = 2;
    public static final int SPECIAL_FUNCTION = 5;
    public static final int VIRTUAL_KEYBOARD = -1;
    private static final SparseIntArray sAccentToCombining;
    private static final SparseIntArray sCombiningToAccent;
    private static final StringBuilder sDeadKeyBuilder = new StringBuilder();
    private static final SparseIntArray sDeadKeyCache = new SparseIntArray();
    private long mPtr;

    static 
    {
        sCombiningToAccent = new SparseIntArray();
        sAccentToCombining = new SparseIntArray();
        addCombining(768, 715);
        addCombining(769, 180);
        addCombining(770, 710);
        addCombining(771, 732);
        addCombining(772, 175);
        addCombining(774, 728);
        addCombining(775, 729);
        addCombining(776, 168);
        addCombining(777, 704);
        addCombining(778, 730);
        addCombining(779, 733);
        addCombining(780, 711);
        addCombining(781, 712);
        addCombining(786, 699);
        addCombining(787, 8125);
        addCombining(788, 701);
        addCombining(789, 700);
        addCombining(795, 39);
        addCombining(803, 46);
        addCombining(807, 184);
        addCombining(808, 731);
        addCombining(809, 716);
        addCombining(817, 717);
        addCombining(821, 45);
        sCombiningToAccent.append(832, 715);
        sCombiningToAccent.append(833, 180);
        sCombiningToAccent.append(835, 8125);
        sAccentToCombining.append(96, 768);
        sAccentToCombining.append(94, 770);
        sAccentToCombining.append(126, 771);
        addDeadKey(45, 68, 272);
        addDeadKey(45, 71, 484);
        addDeadKey(45, 72, 294);
        addDeadKey(45, 73, 407);
        addDeadKey(45, 76, 321);
        addDeadKey(45, 79, 216);
        addDeadKey(45, 84, 358);
        addDeadKey(45, 100, 273);
        addDeadKey(45, 103, 485);
        addDeadKey(45, 104, 295);
        addDeadKey(45, 105, 616);
        addDeadKey(45, 108, 322);
        addDeadKey(45, 111, 248);
        addDeadKey(45, 116, 359);
    }
}
