// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.graphics.Matrix;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

// Referenced classes of package android.view:
//            InputEvent, KeyEvent

public final class MotionEvent extends InputEvent
    implements Parcelable
{
    public static final class PointerCoords
    {

        public static PointerCoords[] createArray(int i)
        {
            PointerCoords apointercoords[] = new PointerCoords[i];
            for(int j = 0; j < i; j++)
                apointercoords[j] = new PointerCoords();

            return apointercoords;
        }

        public void clear()
        {
            mPackedAxisBits = 0L;
            x = 0.0F;
            y = 0.0F;
            pressure = 0.0F;
            size = 0.0F;
            touchMajor = 0.0F;
            touchMinor = 0.0F;
            toolMajor = 0.0F;
            toolMinor = 0.0F;
            orientation = 0.0F;
        }

        public void copyFrom(PointerCoords pointercoords)
        {
label0:
            {
                float af[];
                int i;
                float af2[];
label1:
                {
                    long l = pointercoords.mPackedAxisBits;
                    mPackedAxisBits = l;
                    if(l == 0L)
                        break label0;
                    af = pointercoords.mPackedAxisValues;
                    i = Long.bitCount(l);
                    float af1[] = mPackedAxisValues;
                    if(af1 != null)
                    {
                        af2 = af1;
                        if(i <= af1.length)
                            break label1;
                    }
                    af2 = new float[af.length];
                    mPackedAxisValues = af2;
                }
                System.arraycopy(af, 0, af2, 0, i);
            }
            x = pointercoords.x;
            y = pointercoords.y;
            pressure = pointercoords.pressure;
            size = pointercoords.size;
            touchMajor = pointercoords.touchMajor;
            touchMinor = pointercoords.touchMinor;
            toolMajor = pointercoords.toolMajor;
            toolMinor = pointercoords.toolMinor;
            orientation = pointercoords.orientation;
        }

        public float getAxisValue(int i)
        {
            switch(i)
            {
            default:
                if(i < 0 || i > 63)
                    throw new IllegalArgumentException("Axis out of range.");
                break;

            case 0: // '\0'
                return x;

            case 1: // '\001'
                return y;

            case 2: // '\002'
                return pressure;

            case 3: // '\003'
                return size;

            case 4: // '\004'
                return touchMajor;

            case 5: // '\005'
                return touchMinor;

            case 6: // '\006'
                return toolMajor;

            case 7: // '\007'
                return toolMinor;

            case 8: // '\b'
                return orientation;
            }
            long l = mPackedAxisBits;
            if((l & 0x8000000000000000L >>> i) == 0L)
            {
                return 0.0F;
            } else
            {
                i = Long.bitCount(-1L >>> i & l);
                return mPackedAxisValues[i];
            }
        }

        public void setAxisValue(int i, float f)
        {
            i;
            JVM INSTR tableswitch 0 8: default 52
        //                       0 72
        //                       1 78
        //                       2 86
        //                       3 94
        //                       4 102
        //                       5 110
        //                       6 118
        //                       7 126
        //                       8 134;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
            if(i < 0 || i > 63)
                throw new IllegalArgumentException("Axis out of range.");
            long l = mPackedAxisBits;
            long l1 = 0x8000000000000000L >>> i;
            i = Long.bitCount(-1L >>> i & l);
            float af[] = mPackedAxisValues;
            float af1[] = af;
            if((l & l1) == 0L)
            {
                if(af == null)
                {
                    af1 = new float[8];
                    mPackedAxisValues = af1;
                } else
                {
                    int j = Long.bitCount(l);
                    if(j < af.length)
                    {
                        af1 = af;
                        if(i != j)
                        {
                            System.arraycopy(af, i, af, i + 1, j - i);
                            af1 = af;
                        }
                    } else
                    {
                        float af2[] = new float[j * 2];
                        System.arraycopy(af, 0, af2, 0, i);
                        System.arraycopy(af, i, af2, i + 1, j - i);
                        af1 = af2;
                        mPackedAxisValues = af2;
                    }
                }
                mPackedAxisBits = l | l1;
            }
            af1[i] = f;
              goto _L11
_L2:
            x = f;
_L13:
            return;
_L3:
            y = f;
            continue; /* Loop/switch isn't completed */
_L4:
            pressure = f;
            continue; /* Loop/switch isn't completed */
_L5:
            size = f;
            continue; /* Loop/switch isn't completed */
_L6:
            touchMajor = f;
            continue; /* Loop/switch isn't completed */
_L7:
            touchMinor = f;
            continue; /* Loop/switch isn't completed */
_L8:
            toolMajor = f;
            continue; /* Loop/switch isn't completed */
_L9:
            toolMinor = f;
            continue; /* Loop/switch isn't completed */
_L10:
            orientation = f;
            continue; /* Loop/switch isn't completed */
_L11:
            if(true) goto _L13; else goto _L12
_L12:
        }

        private static final int INITIAL_PACKED_AXIS_VALUES = 8;
        private long mPackedAxisBits;
        private float mPackedAxisValues[];
        public float orientation;
        public float pressure;
        public float size;
        public float toolMajor;
        public float toolMinor;
        public float touchMajor;
        public float touchMinor;
        public float x;
        public float y;

        public PointerCoords()
        {
        }

        public PointerCoords(PointerCoords pointercoords)
        {
            copyFrom(pointercoords);
        }
    }

    public static final class PointerProperties
    {

        static boolean _2D_wrap0(PointerProperties pointerproperties, PointerProperties pointerproperties1)
        {
            return pointerproperties.equals(pointerproperties1);
        }

        public static PointerProperties[] createArray(int i)
        {
            PointerProperties apointerproperties[] = new PointerProperties[i];
            for(int j = 0; j < i; j++)
                apointerproperties[j] = new PointerProperties();

            return apointerproperties;
        }

        private boolean equals(PointerProperties pointerproperties)
        {
            boolean flag = false;
            boolean flag1 = flag;
            if(pointerproperties != null)
            {
                flag1 = flag;
                if(id == pointerproperties.id)
                {
                    flag1 = flag;
                    if(toolType == pointerproperties.toolType)
                        flag1 = true;
                }
            }
            return flag1;
        }

        public void clear()
        {
            id = -1;
            toolType = 0;
        }

        public void copyFrom(PointerProperties pointerproperties)
        {
            id = pointerproperties.id;
            toolType = pointerproperties.toolType;
        }

        public boolean equals(Object obj)
        {
            if(obj instanceof PointerProperties)
                return equals((PointerProperties)obj);
            else
                return false;
        }

        public int hashCode()
        {
            return id | toolType << 8;
        }

        public int id;
        public int toolType;

        public PointerProperties()
        {
            clear();
        }

        public PointerProperties(PointerProperties pointerproperties)
        {
            copyFrom(pointerproperties);
        }
    }


    private MotionEvent()
    {
    }

    public static String actionToString(int i)
    {
        switch(i)
        {
        case 5: // '\005'
        case 6: // '\006'
        default:
            int j = (0xff00 & i) >> 8;
            switch(i & 0xff)
            {
            default:
                return Integer.toString(i);

            case 5: // '\005'
                return (new StringBuilder()).append("ACTION_POINTER_DOWN(").append(j).append(")").toString();

            case 6: // '\006'
                return (new StringBuilder()).append("ACTION_POINTER_UP(").append(j).append(")").toString();
            }

        case 0: // '\0'
            return "ACTION_DOWN";

        case 1: // '\001'
            return "ACTION_UP";

        case 3: // '\003'
            return "ACTION_CANCEL";

        case 4: // '\004'
            return "ACTION_OUTSIDE";

        case 2: // '\002'
            return "ACTION_MOVE";

        case 7: // '\007'
            return "ACTION_HOVER_MOVE";

        case 8: // '\b'
            return "ACTION_SCROLL";

        case 9: // '\t'
            return "ACTION_HOVER_ENTER";

        case 10: // '\n'
            return "ACTION_HOVER_EXIT";

        case 11: // '\013'
            return "ACTION_BUTTON_PRESS";

        case 12: // '\f'
            return "ACTION_BUTTON_RELEASE";
        }
    }

    public static int axisFromString(String s)
    {
        String s1 = s;
        if(s.startsWith("AXIS_"))
        {
            s1 = s.substring("AXIS_".length());
            int i = nativeAxisFromString(s1);
            if(i >= 0)
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
            return -1;
        }
        return j;
    }

    public static String axisToString(int i)
    {
        String s = nativeAxisToString(i);
        if(s != null)
            s = (new StringBuilder()).append("AXIS_").append(s).toString();
        else
            s = Integer.toString(i);
        return s;
    }

    public static String buttonStateToString(int i)
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
                obj1 = BUTTON_SYMBOLIC_NAMES[j];
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

    private static final float clamp(float f, float f1, float f2)
    {
        if(f < f1)
            return f1;
        if(f > f2)
            return f2;
        else
            return f;
    }

    public static MotionEvent createFromParcelBody(Parcel parcel)
    {
        MotionEvent motionevent = obtain();
        motionevent.mNativePtr = nativeReadFromParcel(motionevent.mNativePtr, parcel);
        return motionevent;
    }

    private static final void ensureSharedTempPointerCapacity(int i)
    {
        if(gSharedTempPointerCoords == null || gSharedTempPointerCoords.length < i)
        {
            int j;
            if(gSharedTempPointerCoords != null)
                j = gSharedTempPointerCoords.length;
            else
                j = 8;
            for(; j < i; j *= 2);
            gSharedTempPointerCoords = PointerCoords.createArray(j);
            gSharedTempPointerProperties = PointerProperties.createArray(j);
            gSharedTempPointerIndexMap = new int[j];
        }
    }

    private static native void nativeAddBatch(long l, long l1, PointerCoords apointercoords[], int i);

    private static native int nativeAxisFromString(String s);

    private static native String nativeAxisToString(int i);

    private static native long nativeCopy(long l, long l1, boolean flag);

    private static native void nativeDispose(long l);

    private static native int nativeFindPointerIndex(long l, int i);

    private static native int nativeGetAction(long l);

    private static native int nativeGetActionButton(long l);

    private static native float nativeGetAxisValue(long l, int i, int j, int k);

    private static native int nativeGetButtonState(long l);

    private static native int nativeGetDeviceId(long l);

    private static native long nativeGetDownTimeNanos(long l);

    private static native int nativeGetEdgeFlags(long l);

    private static native long nativeGetEventTimeNanos(long l, int i);

    private static native int nativeGetFlags(long l);

    private static native int nativeGetHistorySize(long l);

    private static native int nativeGetMetaState(long l);

    private static native void nativeGetPointerCoords(long l, int i, int j, PointerCoords pointercoords);

    private static native int nativeGetPointerCount(long l);

    private static native int nativeGetPointerId(long l, int i);

    private static native void nativeGetPointerProperties(long l, int i, PointerProperties pointerproperties);

    private static native float nativeGetRawAxisValue(long l, int i, int j, int k);

    private static native int nativeGetSource(long l);

    private static native int nativeGetToolType(long l, int i);

    private static native float nativeGetXOffset(long l);

    private static native float nativeGetXPrecision(long l);

    private static native float nativeGetYOffset(long l);

    private static native float nativeGetYPrecision(long l);

    private static native long nativeInitialize(long l, int i, int j, int k, int i1, int j1, int k1, 
            int l1, float f, float f1, float f2, float f3, long l2, 
            long l3, int i2, PointerProperties apointerproperties[], PointerCoords apointercoords[]);

    private static native boolean nativeIsTouchEvent(long l);

    private static native void nativeOffsetLocation(long l, float f, float f1);

    private static native long nativeReadFromParcel(long l, Parcel parcel);

    private static native void nativeScale(long l, float f);

    private static native void nativeSetAction(long l, int i);

    private static native void nativeSetActionButton(long l, int i);

    private static native void nativeSetButtonState(long l, int i);

    private static native void nativeSetDownTimeNanos(long l, long l1);

    private static native void nativeSetEdgeFlags(long l, int i);

    private static native void nativeSetFlags(long l, int i);

    private static native int nativeSetSource(long l, int i);

    private static native void nativeTransform(long l, long l1);

    private static native void nativeWriteToParcel(long l, Parcel parcel);

    private static MotionEvent obtain()
    {
        Object obj = gRecyclerLock;
        obj;
        JVM INSTR monitorenter ;
        MotionEvent motionevent = gRecyclerTop;
        if(motionevent != null)
            break MISSING_BLOCK_LABEL_26;
        motionevent = new MotionEvent();
        obj;
        JVM INSTR monitorexit ;
        return motionevent;
        gRecyclerTop = motionevent.mNext;
        gRecyclerUsed--;
        obj;
        JVM INSTR monitorexit ;
        motionevent.mNext = null;
        motionevent.prepareForReuse();
        return motionevent;
        Exception exception;
        exception;
        throw exception;
    }

    public static MotionEvent obtain(long l, long l1, int i, float f, float f1, float f2, 
            float f3, int j, float f4, float f5, int k, int i1)
    {
        MotionEvent motionevent = obtain();
        Object obj = gSharedTempLock;
        obj;
        JVM INSTR monitorenter ;
        ensureSharedTempPointerCapacity(1);
        PointerProperties apointerproperties[] = gSharedTempPointerProperties;
        apointerproperties[0].clear();
        apointerproperties[0].id = 0;
        PointerCoords apointercoords[] = gSharedTempPointerCoords;
        apointercoords[0].clear();
        apointercoords[0].x = f;
        apointercoords[0].y = f1;
        apointercoords[0].pressure = f2;
        apointercoords[0].size = f3;
        motionevent.mNativePtr = nativeInitialize(motionevent.mNativePtr, k, 0, i, 0, i1, j, 0, 0.0F, 0.0F, f4, f5, l * 0xf4240L, l1 * 0xf4240L, 1, apointerproperties, apointercoords);
        obj;
        JVM INSTR monitorexit ;
        return motionevent;
        Exception exception;
        exception;
        throw exception;
    }

    public static MotionEvent obtain(long l, long l1, int i, float f, float f1, int j)
    {
        return obtain(l, l1, i, f, f1, 1.0F, 1.0F, j, 1.0F, 1.0F, 0, 0);
    }

    public static MotionEvent obtain(long l, long l1, int i, int j, float f, float f1, 
            float f2, float f3, int k, float f4, float f5, int i1, int j1)
    {
        return obtain(l, l1, i, f, f1, f2, f3, k, f4, f5, i1, j1);
    }

    public static MotionEvent obtain(long l, long l1, int i, int j, int ai[], PointerCoords apointercoords[], 
            int k, float f, float f1, int i1, int j1, int k1, int i2)
    {
        Object obj = gSharedTempLock;
        obj;
        JVM INSTR monitorenter ;
        PointerProperties apointerproperties[];
        ensureSharedTempPointerCapacity(j);
        apointerproperties = gSharedTempPointerProperties;
        int j2 = 0;
_L2:
        if(j2 >= j)
            break; /* Loop/switch isn't completed */
        apointerproperties[j2].clear();
        apointerproperties[j2].id = ai[j2];
        j2++;
        if(true) goto _L2; else goto _L1
_L1:
        ai = obtain(l, l1, i, j, apointerproperties, apointercoords, k, 0, f, f1, i1, j1, k1, i2);
        obj;
        JVM INSTR monitorexit ;
        return ai;
        ai;
        throw ai;
    }

    public static MotionEvent obtain(long l, long l1, int i, int j, PointerProperties apointerproperties[], PointerCoords apointercoords[], 
            int k, int i1, float f, float f1, int j1, int k1, int i2, 
            int j2)
    {
        MotionEvent motionevent = obtain();
        motionevent.mNativePtr = nativeInitialize(motionevent.mNativePtr, j1, i2, i, j2, k1, k, i1, 0.0F, 0.0F, f, f1, l * 0xf4240L, l1 * 0xf4240L, j, apointerproperties, apointercoords);
        return motionevent;
    }

    public static MotionEvent obtain(MotionEvent motionevent)
    {
        if(motionevent == null)
        {
            throw new IllegalArgumentException("other motion event must not be null");
        } else
        {
            MotionEvent motionevent1 = obtain();
            motionevent1.mNativePtr = nativeCopy(motionevent1.mNativePtr, motionevent.mNativePtr, true);
            return motionevent1;
        }
    }

    public static MotionEvent obtainNoHistory(MotionEvent motionevent)
    {
        if(motionevent == null)
        {
            throw new IllegalArgumentException("other motion event must not be null");
        } else
        {
            MotionEvent motionevent1 = obtain();
            motionevent1.mNativePtr = nativeCopy(motionevent1.mNativePtr, motionevent.mNativePtr, false);
            return motionevent1;
        }
    }

    public static String toolTypeToString(int i)
    {
        String s = (String)TOOL_TYPE_SYMBOLIC_NAMES.get(i);
        if(s == null)
            s = Integer.toString(i);
        return s;
    }

    public final void addBatch(long l, float f, float f1, float f2, float f3, int i)
    {
        Object obj = gSharedTempLock;
        obj;
        JVM INSTR monitorenter ;
        ensureSharedTempPointerCapacity(1);
        PointerCoords apointercoords[] = gSharedTempPointerCoords;
        apointercoords[0].clear();
        apointercoords[0].x = f;
        apointercoords[0].y = f1;
        apointercoords[0].pressure = f2;
        apointercoords[0].size = f3;
        nativeAddBatch(mNativePtr, 0xf4240L * l, apointercoords, i);
        obj;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void addBatch(long l, PointerCoords apointercoords[], int i)
    {
        nativeAddBatch(mNativePtr, 0xf4240L * l, apointercoords, i);
    }

    public final boolean addBatch(MotionEvent motionevent)
    {
        int k;
        int i = nativeGetAction(mNativePtr);
        if(i != 2 && i != 7)
            return false;
        if(i != nativeGetAction(motionevent.mNativePtr))
            return false;
        while(nativeGetDeviceId(mNativePtr) != nativeGetDeviceId(motionevent.mNativePtr) || nativeGetSource(mNativePtr) != nativeGetSource(motionevent.mNativePtr) || nativeGetFlags(mNativePtr) != nativeGetFlags(motionevent.mNativePtr)) 
            return false;
        k = nativeGetPointerCount(mNativePtr);
        if(k != nativeGetPointerCount(motionevent.mNativePtr))
            return false;
        Object obj = gSharedTempLock;
        obj;
        JVM INSTR monitorenter ;
        PointerProperties apointerproperties[];
        PointerCoords apointercoords[];
        ensureSharedTempPointerCapacity(Math.max(k, 2));
        apointerproperties = gSharedTempPointerProperties;
        apointercoords = gSharedTempPointerCoords;
        int j = 0;
_L2:
        if(j >= k)
            break; /* Loop/switch isn't completed */
        boolean flag;
        nativeGetPointerProperties(mNativePtr, j, apointerproperties[0]);
        nativeGetPointerProperties(motionevent.mNativePtr, j, apointerproperties[1]);
        flag = PointerProperties._2D_wrap0(apointerproperties[0], apointerproperties[1]);
        if(flag)
            break MISSING_BLOCK_LABEL_188;
        obj;
        JVM INSTR monitorexit ;
        return false;
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        int l;
        int i1;
        l = nativeGetMetaState(motionevent.mNativePtr);
        i1 = nativeGetHistorySize(motionevent.mNativePtr);
        j = 0;
_L6:
        int j1;
        if(j > i1)
            break; /* Loop/switch isn't completed */
        int k1;
        if(j == i1)
            j1 = 0x80000000;
        else
            j1 = j;
        k1 = 0;
_L4:
        if(k1 >= k)
            break; /* Loop/switch isn't completed */
        nativeGetPointerCoords(motionevent.mNativePtr, k1, j1, apointercoords[k1]);
        k1++;
        if(true) goto _L4; else goto _L3
_L3:
        long l1 = nativeGetEventTimeNanos(motionevent.mNativePtr, j1);
        nativeAddBatch(mNativePtr, l1, apointercoords, l);
        j++;
        if(true) goto _L6; else goto _L5
_L5:
        return true;
        motionevent;
        throw motionevent;
    }

    public final void cancel()
    {
        setAction(3);
    }

    public final MotionEvent clampNoHistory(float f, float f1, float f2, float f3)
    {
        MotionEvent motionevent = obtain();
        Object obj = gSharedTempLock;
        obj;
        JVM INSTR monitorenter ;
        int i;
        PointerProperties apointerproperties[];
        PointerCoords apointercoords[];
        i = nativeGetPointerCount(mNativePtr);
        ensureSharedTempPointerCapacity(i);
        apointerproperties = gSharedTempPointerProperties;
        apointercoords = gSharedTempPointerCoords;
        int j = 0;
_L2:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        nativeGetPointerProperties(mNativePtr, j, apointerproperties[j]);
        nativeGetPointerCoords(mNativePtr, j, 0x80000000, apointercoords[j]);
        apointercoords[j].x = clamp(apointercoords[j].x, f, f2);
        apointercoords[j].y = clamp(apointercoords[j].y, f1, f3);
        j++;
        if(true) goto _L2; else goto _L1
_L1:
        motionevent.mNativePtr = nativeInitialize(motionevent.mNativePtr, nativeGetDeviceId(mNativePtr), nativeGetSource(mNativePtr), nativeGetAction(mNativePtr), nativeGetFlags(mNativePtr), nativeGetEdgeFlags(mNativePtr), nativeGetMetaState(mNativePtr), nativeGetButtonState(mNativePtr), nativeGetXOffset(mNativePtr), nativeGetYOffset(mNativePtr), nativeGetXPrecision(mNativePtr), nativeGetYPrecision(mNativePtr), nativeGetDownTimeNanos(mNativePtr), nativeGetEventTimeNanos(mNativePtr, 0x80000000), i, apointerproperties, apointercoords);
        obj;
        JVM INSTR monitorexit ;
        return motionevent;
        Exception exception;
        exception;
        throw exception;
    }

    public volatile InputEvent copy()
    {
        return copy();
    }

    public MotionEvent copy()
    {
        return obtain(this);
    }

    protected void finalize()
        throws Throwable
    {
        if(mNativePtr != 0L)
        {
            nativeDispose(mNativePtr);
            mNativePtr = 0L;
        }
        super.finalize();
        return;
        Exception exception;
        exception;
        super.finalize();
        throw exception;
    }

    public final int findPointerIndex(int i)
    {
        return nativeFindPointerIndex(mNativePtr, i);
    }

    public final int getAction()
    {
        return nativeGetAction(mNativePtr);
    }

    public final int getActionButton()
    {
        return nativeGetActionButton(mNativePtr);
    }

    public final int getActionIndex()
    {
        return (nativeGetAction(mNativePtr) & 0xff00) >> 8;
    }

    public final int getActionMasked()
    {
        return nativeGetAction(mNativePtr) & 0xff;
    }

    public final float getAxisValue(int i)
    {
        return nativeGetAxisValue(mNativePtr, i, 0, 0x80000000);
    }

    public final float getAxisValue(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, i, j, 0x80000000);
    }

    public final int getButtonState()
    {
        return nativeGetButtonState(mNativePtr);
    }

    public final int getDeviceId()
    {
        return nativeGetDeviceId(mNativePtr);
    }

    public final long getDownTime()
    {
        return nativeGetDownTimeNanos(mNativePtr) / 0xf4240L;
    }

    public final int getEdgeFlags()
    {
        return nativeGetEdgeFlags(mNativePtr);
    }

    public final long getEventTime()
    {
        return nativeGetEventTimeNanos(mNativePtr, 0x80000000) / 0xf4240L;
    }

    public final long getEventTimeNano()
    {
        return nativeGetEventTimeNanos(mNativePtr, 0x80000000);
    }

    public final int getFlags()
    {
        return nativeGetFlags(mNativePtr);
    }

    public final float getHistoricalAxisValue(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, i, 0, j);
    }

    public final float getHistoricalAxisValue(int i, int j, int k)
    {
        return nativeGetAxisValue(mNativePtr, i, j, k);
    }

    public final long getHistoricalEventTime(int i)
    {
        return nativeGetEventTimeNanos(mNativePtr, i) / 0xf4240L;
    }

    public final long getHistoricalEventTimeNano(int i)
    {
        return nativeGetEventTimeNanos(mNativePtr, i);
    }

    public final float getHistoricalOrientation(int i)
    {
        return nativeGetAxisValue(mNativePtr, 8, 0, i);
    }

    public final float getHistoricalOrientation(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, 8, i, j);
    }

    public final void getHistoricalPointerCoords(int i, int j, PointerCoords pointercoords)
    {
        nativeGetPointerCoords(mNativePtr, i, j, pointercoords);
    }

    public final float getHistoricalPressure(int i)
    {
        return nativeGetAxisValue(mNativePtr, 2, 0, i);
    }

    public final float getHistoricalPressure(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, 2, i, j);
    }

    public final float getHistoricalSize(int i)
    {
        return nativeGetAxisValue(mNativePtr, 3, 0, i);
    }

    public final float getHistoricalSize(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, 3, i, j);
    }

    public final float getHistoricalToolMajor(int i)
    {
        return nativeGetAxisValue(mNativePtr, 6, 0, i);
    }

    public final float getHistoricalToolMajor(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, 6, i, j);
    }

    public final float getHistoricalToolMinor(int i)
    {
        return nativeGetAxisValue(mNativePtr, 7, 0, i);
    }

    public final float getHistoricalToolMinor(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, 7, i, j);
    }

    public final float getHistoricalTouchMajor(int i)
    {
        return nativeGetAxisValue(mNativePtr, 4, 0, i);
    }

    public final float getHistoricalTouchMajor(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, 4, i, j);
    }

    public final float getHistoricalTouchMinor(int i)
    {
        return nativeGetAxisValue(mNativePtr, 5, 0, i);
    }

    public final float getHistoricalTouchMinor(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, 5, i, j);
    }

    public final float getHistoricalX(int i)
    {
        return nativeGetAxisValue(mNativePtr, 0, 0, i);
    }

    public final float getHistoricalX(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, 0, i, j);
    }

    public final float getHistoricalY(int i)
    {
        return nativeGetAxisValue(mNativePtr, 1, 0, i);
    }

    public final float getHistoricalY(int i, int j)
    {
        return nativeGetAxisValue(mNativePtr, 1, i, j);
    }

    public final int getHistorySize()
    {
        return nativeGetHistorySize(mNativePtr);
    }

    public final int getMetaState()
    {
        return nativeGetMetaState(mNativePtr);
    }

    public final float getOrientation()
    {
        return nativeGetAxisValue(mNativePtr, 8, 0, 0x80000000);
    }

    public final float getOrientation(int i)
    {
        return nativeGetAxisValue(mNativePtr, 8, i, 0x80000000);
    }

    public final void getPointerCoords(int i, PointerCoords pointercoords)
    {
        nativeGetPointerCoords(mNativePtr, i, 0x80000000, pointercoords);
    }

    public final int getPointerCount()
    {
        return nativeGetPointerCount(mNativePtr);
    }

    public final int getPointerId(int i)
    {
        return nativeGetPointerId(mNativePtr, i);
    }

    public final int getPointerIdBits()
    {
        int i = 0;
        int j = nativeGetPointerCount(mNativePtr);
        for(int k = 0; k < j; k++)
            i |= 1 << nativeGetPointerId(mNativePtr, k);

        return i;
    }

    public final void getPointerProperties(int i, PointerProperties pointerproperties)
    {
        nativeGetPointerProperties(mNativePtr, i, pointerproperties);
    }

    public final float getPressure()
    {
        return nativeGetAxisValue(mNativePtr, 2, 0, 0x80000000);
    }

    public final float getPressure(int i)
    {
        return nativeGetAxisValue(mNativePtr, 2, i, 0x80000000);
    }

    public final float getRawX()
    {
        return nativeGetRawAxisValue(mNativePtr, 0, 0, 0x80000000);
    }

    public final float getRawY()
    {
        return nativeGetRawAxisValue(mNativePtr, 1, 0, 0x80000000);
    }

    public final float getSize()
    {
        return nativeGetAxisValue(mNativePtr, 3, 0, 0x80000000);
    }

    public final float getSize(int i)
    {
        return nativeGetAxisValue(mNativePtr, 3, i, 0x80000000);
    }

    public final int getSource()
    {
        return nativeGetSource(mNativePtr);
    }

    public final float getToolMajor()
    {
        return nativeGetAxisValue(mNativePtr, 6, 0, 0x80000000);
    }

    public final float getToolMajor(int i)
    {
        return nativeGetAxisValue(mNativePtr, 6, i, 0x80000000);
    }

    public final float getToolMinor()
    {
        return nativeGetAxisValue(mNativePtr, 7, 0, 0x80000000);
    }

    public final float getToolMinor(int i)
    {
        return nativeGetAxisValue(mNativePtr, 7, i, 0x80000000);
    }

    public final int getToolType(int i)
    {
        return nativeGetToolType(mNativePtr, i);
    }

    public final float getTouchMajor()
    {
        return nativeGetAxisValue(mNativePtr, 4, 0, 0x80000000);
    }

    public final float getTouchMajor(int i)
    {
        return nativeGetAxisValue(mNativePtr, 4, i, 0x80000000);
    }

    public final float getTouchMinor()
    {
        return nativeGetAxisValue(mNativePtr, 5, 0, 0x80000000);
    }

    public final float getTouchMinor(int i)
    {
        return nativeGetAxisValue(mNativePtr, 5, i, 0x80000000);
    }

    public final float getX()
    {
        return nativeGetAxisValue(mNativePtr, 0, 0, 0x80000000);
    }

    public final float getX(int i)
    {
        return nativeGetAxisValue(mNativePtr, 0, i, 0x80000000);
    }

    public final float getXPrecision()
    {
        return nativeGetXPrecision(mNativePtr);
    }

    public final float getY()
    {
        return nativeGetAxisValue(mNativePtr, 1, 0, 0x80000000);
    }

    public final float getY(int i)
    {
        return nativeGetAxisValue(mNativePtr, 1, i, 0x80000000);
    }

    public final float getYPrecision()
    {
        return nativeGetYPrecision(mNativePtr);
    }

    public final boolean isButtonPressed(int i)
    {
        boolean flag = false;
        if(i == 0)
            return false;
        if((getButtonState() & i) == i)
            flag = true;
        return flag;
    }

    public final boolean isHoverExitPending()
    {
        boolean flag = false;
        if((getFlags() & 4) != 0)
            flag = true;
        return flag;
    }

    public final boolean isTainted()
    {
        boolean flag = false;
        if((0x80000000 & getFlags()) != 0)
            flag = true;
        return flag;
    }

    public final boolean isTargetAccessibilityFocus()
    {
        boolean flag = false;
        if((0x40000000 & getFlags()) != 0)
            flag = true;
        return flag;
    }

    public final boolean isTouchEvent()
    {
        return nativeIsTouchEvent(mNativePtr);
    }

    public final boolean isWithinBoundsNoHistory(float f, float f1, float f2, float f3)
    {
        int i = nativeGetPointerCount(mNativePtr);
        for(int j = 0; j < i; j++)
        {
            float f4 = nativeGetAxisValue(mNativePtr, 0, j, 0x80000000);
            for(float f5 = nativeGetAxisValue(mNativePtr, 1, j, 0x80000000); f4 < f || f4 > f2 || f5 < f1 || f5 > f3;)
                return false;

        }

        return true;
    }

    public final void offsetLocation(float f, float f1)
    {
        if(f != 0.0F || f1 != 0.0F)
            nativeOffsetLocation(mNativePtr, f, f1);
    }

    public final void recycle()
    {
        super.recycle();
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

    public final void scale(float f)
    {
        if(f != 1.0F)
            nativeScale(mNativePtr, f);
    }

    public final void setAction(int i)
    {
        nativeSetAction(mNativePtr, i);
    }

    public final void setActionButton(int i)
    {
        nativeSetActionButton(mNativePtr, i);
    }

    public final void setButtonState(int i)
    {
        nativeSetButtonState(mNativePtr, i);
    }

    public final void setDownTime(long l)
    {
        nativeSetDownTimeNanos(mNativePtr, 0xf4240L * l);
    }

    public final void setEdgeFlags(int i)
    {
        nativeSetEdgeFlags(mNativePtr, i);
    }

    public void setHoverExitPending(boolean flag)
    {
        int i = getFlags();
        long l = mNativePtr;
        if(flag)
            i |= 4;
        else
            i &= -5;
        nativeSetFlags(l, i);
    }

    public final void setLocation(float f, float f1)
    {
        offsetLocation(f - getX(), f1 - getY());
    }

    public final void setSource(int i)
    {
        nativeSetSource(mNativePtr, i);
    }

    public final void setTainted(boolean flag)
    {
        int i = getFlags();
        long l = mNativePtr;
        if(flag)
            i = 0x80000000 | i;
        else
            i = 0x7fffffff & i;
        nativeSetFlags(l, i);
    }

    public final void setTargetAccessibilityFocus(boolean flag)
    {
        int i = getFlags();
        long l = mNativePtr;
        if(flag)
            i = 0x40000000 | i;
        else
            i = 0xbfffffff & i;
        nativeSetFlags(l, i);
    }

    public final MotionEvent split(int i)
    {
        MotionEvent motionevent = obtain();
        Object obj = gSharedTempLock;
        obj;
        JVM INSTR monitorenter ;
        int j;
        PointerProperties apointerproperties[];
        Object obj1;
        int ai[];
        int k;
        j = nativeGetPointerCount(mNativePtr);
        ensureSharedTempPointerCapacity(j);
        apointerproperties = gSharedTempPointerProperties;
        obj1 = gSharedTempPointerCoords;
        ai = gSharedTempPointerIndexMap;
        k = nativeGetAction(mNativePtr);
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        l = k & 0xff;
        i1 = -1;
        j1 = 0;
        k1 = 0;
        l1 = 0;
_L3:
        if(l1 >= j) goto _L2; else goto _L1
_L1:
        int j2;
        nativeGetPointerProperties(mNativePtr, l1, apointerproperties[j1]);
        j2 = 1 << apointerproperties[j1].id;
        int k2 = j1;
        int l2 = i1;
        int i3 = k1;
        if((j2 & i) != 0)
        {
            if(l1 == (0xff00 & k) >> 8)
                i1 = j1;
            ai[j1] = l1;
            k2 = j1 + 1;
            i3 = k1 | j2;
            l2 = i1;
        }
        l1++;
        j1 = k2;
        i1 = l2;
        k1 = i3;
          goto _L3
_L2:
        if(j1 != 0)
            break MISSING_BLOCK_LABEL_207;
        obj1 = JVM INSTR new #542 <Class IllegalArgumentException>;
        ((IllegalArgumentException) (obj1)).IllegalArgumentException("idBits did not match any ids in the event");
        throw obj1;
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        int j3;
        if(l == 5 || l == 6)
        {
            if(i1 < 0)
                i = 2;
            else
            if(j1 == 1)
            {
                if(l == 5)
                    i = 0;
                else
                    i = 1;
            } else
            {
                i = l | i1 << 8;
            }
        } else
        {
            i = k;
        }
        j3 = nativeGetHistorySize(mNativePtr);
        i1 = 0;
_L12:
        if(i1 > j3) goto _L5; else goto _L4
_L4:
        int i2;
        if(i1 == j3)
            k1 = 0x80000000;
        else
            k1 = i1;
        i2 = 0;
        if(i2 >= j1)
            break; /* Loop/switch isn't completed */
        nativeGetPointerCoords(mNativePtr, ai[i2], k1, obj1[i2]);
        i2++;
        if(true) goto _L7; else goto _L6
_L7:
        break MISSING_BLOCK_LABEL_260;
_L6:
        long l3 = nativeGetEventTimeNanos(mNativePtr, k1);
        if(i1 != 0) goto _L9; else goto _L8
_L8:
        motionevent.mNativePtr = nativeInitialize(motionevent.mNativePtr, nativeGetDeviceId(mNativePtr), nativeGetSource(mNativePtr), i, nativeGetFlags(mNativePtr), nativeGetEdgeFlags(mNativePtr), nativeGetMetaState(mNativePtr), nativeGetButtonState(mNativePtr), nativeGetXOffset(mNativePtr), nativeGetYOffset(mNativePtr), nativeGetXPrecision(mNativePtr), nativeGetYPrecision(mNativePtr), nativeGetDownTimeNanos(mNativePtr), l3, j1, apointerproperties, ((PointerCoords []) (obj1)));
_L10:
        i1++;
        continue; /* Loop/switch isn't completed */
_L9:
        nativeAddBatch(motionevent.mNativePtr, l3, ((PointerCoords []) (obj1)), 0);
        if(true) goto _L10; else goto _L5
_L5:
        return motionevent;
        if(true) goto _L12; else goto _L11
_L11:
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("MotionEvent { action=").append(actionToString(getAction()));
        stringbuilder.append(", actionButton=").append(buttonStateToString(getActionButton()));
        int i = getPointerCount();
        for(int j = 0; j < i; j++)
        {
            stringbuilder.append(", id[").append(j).append("]=").append(getPointerId(j));
            stringbuilder.append(", x[").append(j).append("]=").append(getX(j));
            stringbuilder.append(", y[").append(j).append("]=").append(getY(j));
            stringbuilder.append(", toolType[").append(j).append("]=").append(toolTypeToString(getToolType(j)));
        }

        stringbuilder.append(", buttonState=").append(buttonStateToString(getButtonState()));
        stringbuilder.append(", metaState=").append(KeyEvent.metaStateToString(getMetaState()));
        stringbuilder.append(", flags=0x").append(Integer.toHexString(getFlags()));
        stringbuilder.append(", edgeFlags=0x").append(Integer.toHexString(getEdgeFlags()));
        stringbuilder.append(", pointerCount=").append(i);
        stringbuilder.append(", historySize=").append(getHistorySize());
        stringbuilder.append(", eventTime=").append(getEventTime());
        stringbuilder.append(", downTime=").append(getDownTime());
        stringbuilder.append(", deviceId=").append(getDeviceId());
        stringbuilder.append(", source=0x").append(Integer.toHexString(getSource()));
        stringbuilder.append(" }");
        return stringbuilder.toString();
    }

    public final void transform(Matrix matrix)
    {
        if(matrix == null)
        {
            throw new IllegalArgumentException("matrix must not be null");
        } else
        {
            nativeTransform(mNativePtr, matrix.native_instance);
            return;
        }
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(1);
        nativeWriteToParcel(mNativePtr, parcel);
    }

    public static final int ACTION_BUTTON_PRESS = 11;
    public static final int ACTION_BUTTON_RELEASE = 12;
    public static final int ACTION_CANCEL = 3;
    public static final int ACTION_DOWN = 0;
    public static final int ACTION_HOVER_ENTER = 9;
    public static final int ACTION_HOVER_EXIT = 10;
    public static final int ACTION_HOVER_MOVE = 7;
    public static final int ACTION_MASK = 255;
    public static final int ACTION_MOVE = 2;
    public static final int ACTION_OUTSIDE = 4;
    public static final int ACTION_POINTER_1_DOWN = 5;
    public static final int ACTION_POINTER_1_UP = 6;
    public static final int ACTION_POINTER_2_DOWN = 261;
    public static final int ACTION_POINTER_2_UP = 262;
    public static final int ACTION_POINTER_3_DOWN = 517;
    public static final int ACTION_POINTER_3_UP = 518;
    public static final int ACTION_POINTER_DOWN = 5;
    public static final int ACTION_POINTER_ID_MASK = 65280;
    public static final int ACTION_POINTER_ID_SHIFT = 8;
    public static final int ACTION_POINTER_INDEX_MASK = 65280;
    public static final int ACTION_POINTER_INDEX_SHIFT = 8;
    public static final int ACTION_POINTER_UP = 6;
    public static final int ACTION_SCROLL = 8;
    public static final int ACTION_UP = 1;
    public static final int AXIS_BRAKE = 23;
    public static final int AXIS_DISTANCE = 24;
    public static final int AXIS_GAS = 22;
    public static final int AXIS_GENERIC_1 = 32;
    public static final int AXIS_GENERIC_10 = 41;
    public static final int AXIS_GENERIC_11 = 42;
    public static final int AXIS_GENERIC_12 = 43;
    public static final int AXIS_GENERIC_13 = 44;
    public static final int AXIS_GENERIC_14 = 45;
    public static final int AXIS_GENERIC_15 = 46;
    public static final int AXIS_GENERIC_16 = 47;
    public static final int AXIS_GENERIC_2 = 33;
    public static final int AXIS_GENERIC_3 = 34;
    public static final int AXIS_GENERIC_4 = 35;
    public static final int AXIS_GENERIC_5 = 36;
    public static final int AXIS_GENERIC_6 = 37;
    public static final int AXIS_GENERIC_7 = 38;
    public static final int AXIS_GENERIC_8 = 39;
    public static final int AXIS_GENERIC_9 = 40;
    public static final int AXIS_HAT_X = 15;
    public static final int AXIS_HAT_Y = 16;
    public static final int AXIS_HSCROLL = 10;
    public static final int AXIS_LTRIGGER = 17;
    public static final int AXIS_ORIENTATION = 8;
    public static final int AXIS_PRESSURE = 2;
    public static final int AXIS_RELATIVE_X = 27;
    public static final int AXIS_RELATIVE_Y = 28;
    public static final int AXIS_RTRIGGER = 18;
    public static final int AXIS_RUDDER = 20;
    public static final int AXIS_RX = 12;
    public static final int AXIS_RY = 13;
    public static final int AXIS_RZ = 14;
    public static final int AXIS_SCROLL = 26;
    public static final int AXIS_SIZE = 3;
    private static final SparseArray AXIS_SYMBOLIC_NAMES;
    public static final int AXIS_THROTTLE = 19;
    public static final int AXIS_TILT = 25;
    public static final int AXIS_TOOL_MAJOR = 6;
    public static final int AXIS_TOOL_MINOR = 7;
    public static final int AXIS_TOUCH_MAJOR = 4;
    public static final int AXIS_TOUCH_MINOR = 5;
    public static final int AXIS_VSCROLL = 9;
    public static final int AXIS_WHEEL = 21;
    public static final int AXIS_X = 0;
    public static final int AXIS_Y = 1;
    public static final int AXIS_Z = 11;
    public static final int BUTTON_BACK = 8;
    public static final int BUTTON_FORWARD = 16;
    public static final int BUTTON_PRIMARY = 1;
    public static final int BUTTON_SECONDARY = 2;
    public static final int BUTTON_STYLUS_PRIMARY = 32;
    public static final int BUTTON_STYLUS_SECONDARY = 64;
    private static final String BUTTON_SYMBOLIC_NAMES[] = {
        "BUTTON_PRIMARY", "BUTTON_SECONDARY", "BUTTON_TERTIARY", "BUTTON_BACK", "BUTTON_FORWARD", "BUTTON_STYLUS_PRIMARY", "BUTTON_STYLUS_SECONDARY", "0x00000080", "0x00000100", "0x00000200", 
        "0x00000400", "0x00000800", "0x00001000", "0x00002000", "0x00004000", "0x00008000", "0x00010000", "0x00020000", "0x00040000", "0x00080000", 
        "0x00100000", "0x00200000", "0x00400000", "0x00800000", "0x01000000", "0x02000000", "0x04000000", "0x08000000", "0x10000000", "0x20000000", 
        "0x40000000", "0x80000000"
    };
    public static final int BUTTON_TERTIARY = 4;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MotionEvent createFromParcel(Parcel parcel)
        {
            parcel.readInt();
            return MotionEvent.createFromParcelBody(parcel);
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MotionEvent[] newArray(int i)
        {
            return new MotionEvent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int EDGE_BOTTOM = 2;
    public static final int EDGE_LEFT = 4;
    public static final int EDGE_RIGHT = 8;
    public static final int EDGE_TOP = 1;
    public static final int FLAG_HOVER_EXIT_PENDING = 4;
    public static final int FLAG_IS_GENERATED_GESTURE = 8;
    public static final int FLAG_TAINTED = 0x80000000;
    public static final int FLAG_TARGET_ACCESSIBILITY_FOCUS = 0x40000000;
    public static final int FLAG_WINDOW_IS_OBSCURED = 1;
    public static final int FLAG_WINDOW_IS_PARTIALLY_OBSCURED = 2;
    private static final int HISTORY_CURRENT = 0x80000000;
    public static final int INVALID_POINTER_ID = -1;
    private static final String LABEL_PREFIX = "AXIS_";
    private static final int MAX_RECYCLED = 10;
    private static final long NS_PER_MS = 0xf4240L;
    public static final int TOOL_TYPE_ERASER = 4;
    public static final int TOOL_TYPE_FINGER = 1;
    public static final int TOOL_TYPE_MOUSE = 3;
    public static final int TOOL_TYPE_STYLUS = 2;
    private static final SparseArray TOOL_TYPE_SYMBOLIC_NAMES;
    public static final int TOOL_TYPE_UNKNOWN = 0;
    private static final Object gRecyclerLock = new Object();
    private static MotionEvent gRecyclerTop;
    private static int gRecyclerUsed;
    private static final Object gSharedTempLock = new Object();
    private static PointerCoords gSharedTempPointerCoords[];
    private static int gSharedTempPointerIndexMap[];
    private static PointerProperties gSharedTempPointerProperties[];
    private long mNativePtr;
    private MotionEvent mNext;

    static 
    {
        AXIS_SYMBOLIC_NAMES = new SparseArray();
        SparseArray sparsearray = AXIS_SYMBOLIC_NAMES;
        sparsearray.append(0, "AXIS_X");
        sparsearray.append(1, "AXIS_Y");
        sparsearray.append(2, "AXIS_PRESSURE");
        sparsearray.append(3, "AXIS_SIZE");
        sparsearray.append(4, "AXIS_TOUCH_MAJOR");
        sparsearray.append(5, "AXIS_TOUCH_MINOR");
        sparsearray.append(6, "AXIS_TOOL_MAJOR");
        sparsearray.append(7, "AXIS_TOOL_MINOR");
        sparsearray.append(8, "AXIS_ORIENTATION");
        sparsearray.append(9, "AXIS_VSCROLL");
        sparsearray.append(10, "AXIS_HSCROLL");
        sparsearray.append(11, "AXIS_Z");
        sparsearray.append(12, "AXIS_RX");
        sparsearray.append(13, "AXIS_RY");
        sparsearray.append(14, "AXIS_RZ");
        sparsearray.append(15, "AXIS_HAT_X");
        sparsearray.append(16, "AXIS_HAT_Y");
        sparsearray.append(17, "AXIS_LTRIGGER");
        sparsearray.append(18, "AXIS_RTRIGGER");
        sparsearray.append(19, "AXIS_THROTTLE");
        sparsearray.append(20, "AXIS_RUDDER");
        sparsearray.append(21, "AXIS_WHEEL");
        sparsearray.append(22, "AXIS_GAS");
        sparsearray.append(23, "AXIS_BRAKE");
        sparsearray.append(24, "AXIS_DISTANCE");
        sparsearray.append(25, "AXIS_TILT");
        sparsearray.append(26, "AXIS_SCROLL");
        sparsearray.append(27, "AXIS_REALTIVE_X");
        sparsearray.append(28, "AXIS_REALTIVE_Y");
        sparsearray.append(32, "AXIS_GENERIC_1");
        sparsearray.append(33, "AXIS_GENERIC_2");
        sparsearray.append(34, "AXIS_GENERIC_3");
        sparsearray.append(35, "AXIS_GENERIC_4");
        sparsearray.append(36, "AXIS_GENERIC_5");
        sparsearray.append(37, "AXIS_GENERIC_6");
        sparsearray.append(38, "AXIS_GENERIC_7");
        sparsearray.append(39, "AXIS_GENERIC_8");
        sparsearray.append(40, "AXIS_GENERIC_9");
        sparsearray.append(41, "AXIS_GENERIC_10");
        sparsearray.append(42, "AXIS_GENERIC_11");
        sparsearray.append(43, "AXIS_GENERIC_12");
        sparsearray.append(44, "AXIS_GENERIC_13");
        sparsearray.append(45, "AXIS_GENERIC_14");
        sparsearray.append(46, "AXIS_GENERIC_15");
        sparsearray.append(47, "AXIS_GENERIC_16");
        TOOL_TYPE_SYMBOLIC_NAMES = new SparseArray();
        sparsearray = TOOL_TYPE_SYMBOLIC_NAMES;
        sparsearray.append(0, "TOOL_TYPE_UNKNOWN");
        sparsearray.append(1, "TOOL_TYPE_FINGER");
        sparsearray.append(2, "TOOL_TYPE_STYLUS");
        sparsearray.append(3, "TOOL_TYPE_MOUSE");
        sparsearray.append(4, "TOOL_TYPE_ERASER");
    }
}
