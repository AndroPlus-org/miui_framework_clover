// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view;

import android.os.Build;
import android.util.Log;

// Referenced classes of package android.view:
//            InputEvent, MotionEvent, KeyEvent

public final class InputEventConsistencyVerifier
{
    private static final class KeyState
    {

        public static KeyState obtain(int i, int j, int k)
        {
            Object obj = mRecycledListLock;
            obj;
            JVM INSTR monitorenter ;
            KeyState keystate = mRecycledList;
            if(keystate == null)
                break MISSING_BLOCK_LABEL_53;
            mRecycledList = keystate.next;
_L1:
            obj;
            JVM INSTR monitorexit ;
            keystate.deviceId = i;
            keystate.source = j;
            keystate.keyCode = k;
            keystate.unhandled = false;
            return keystate;
            keystate = new KeyState();
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        public void recycle()
        {
            Object obj = mRecycledListLock;
            obj;
            JVM INSTR monitorenter ;
            next = mRecycledList;
            mRecycledList = next;
            obj;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private static KeyState mRecycledList;
        private static Object mRecycledListLock = new Object();
        public int deviceId;
        public int keyCode;
        public KeyState next;
        public int source;
        public boolean unhandled;


        private KeyState()
        {
        }
    }


    public InputEventConsistencyVerifier(Object obj, int i)
    {
        this(obj, i, null);
    }

    public InputEventConsistencyVerifier(Object obj, int i, String s)
    {
        mTouchEventStreamDeviceId = -1;
        mCaller = obj;
        mFlags = i;
        if(s == null)
            s = "InputEventConsistencyVerifier";
        mLogTag = s;
    }

    private void addKeyState(int i, int j, int k)
    {
        KeyState keystate = KeyState.obtain(i, j, k);
        keystate.next = mKeyStateList;
        mKeyStateList = keystate;
    }

    private static void appendEvent(StringBuilder stringbuilder, int i, InputEvent inputevent, boolean flag)
    {
        stringbuilder.append(i).append(": sent at ").append(inputevent.getEventTimeNano());
        stringbuilder.append(", ");
        if(flag)
            stringbuilder.append("(unhandled) ");
        stringbuilder.append(inputevent);
    }

    private void ensureActionButtonIsNonZeroForThisAction(MotionEvent motionevent)
    {
        if(motionevent.getActionButton() == 0)
            problem((new StringBuilder()).append("No action button set. Action button should always be non-zero for ").append(MotionEvent.actionToString(motionevent.getAction())).toString());
    }

    private void ensureHistorySizeIsZeroForThisAction(MotionEvent motionevent)
    {
        int i = motionevent.getHistorySize();
        if(i != 0)
            problem((new StringBuilder()).append("History size is ").append(i).append(" but it should always be 0 for ").append(MotionEvent.actionToString(motionevent.getAction())).toString());
    }

    private void ensureMetaStateIsNormalized(int i)
    {
        int j = KeyEvent.normalizeMetaState(i);
        if(j != i)
            problem(String.format("Metastate not normalized.  Was 0x%08x but expected 0x%08x.", new Object[] {
                Integer.valueOf(i), Integer.valueOf(j)
            }));
    }

    private void ensurePointerCountIsOneForThisAction(MotionEvent motionevent)
    {
        int i = motionevent.getPointerCount();
        if(i != 1)
            problem((new StringBuilder()).append("Pointer count is ").append(i).append(" but it should always be 1 for ").append(MotionEvent.actionToString(motionevent.getAction())).toString());
    }

    private KeyState findKeyState(int i, int j, int k, boolean flag)
    {
        KeyState keystate = null;
        for(KeyState keystate1 = mKeyStateList; keystate1 != null; keystate1 = keystate1.next)
        {
            if(keystate1.deviceId == i && keystate1.source == j && keystate1.keyCode == k)
            {
                if(flag)
                {
                    if(keystate != null)
                        keystate.next = keystate1.next;
                    else
                        mKeyStateList = keystate1.next;
                    keystate1.next = null;
                }
                return keystate1;
            }
            keystate = keystate1;
        }

        return null;
    }

    private void finishEvent()
    {
        if(mViolationMessage == null || mViolationMessage.length() == 0) goto _L2; else goto _L1
_L1:
        if(mCurrentEvent.isTainted()) goto _L4; else goto _L3
_L3:
        mViolationMessage.append("\n  in ").append(mCaller);
        mViolationMessage.append("\n  ");
        appendEvent(mViolationMessage, 0, mCurrentEvent, false);
        if(mRecentEvents == null) goto _L6; else goto _L5
_L5:
        int i;
        mViolationMessage.append("\n  -- recent events --");
        i = 0;
_L10:
        if(i >= 5) goto _L6; else goto _L7
_L7:
        int j;
        InputEvent inputevent;
        j = ((mMostRecentEventIndex + 5) - i) % 5;
        inputevent = mRecentEvents[j];
        if(inputevent != null) goto _L8; else goto _L6
_L6:
        Log.d(mLogTag, mViolationMessage.toString());
        mCurrentEvent.setTainted(true);
_L4:
        mViolationMessage.setLength(0);
_L2:
        if(mRecentEvents == null)
        {
            mRecentEvents = new InputEvent[5];
            mRecentEventsUnhandled = new boolean[5];
        }
        i = (mMostRecentEventIndex + 1) % 5;
        mMostRecentEventIndex = i;
        if(mRecentEvents[i] != null)
            mRecentEvents[i].recycle();
        mRecentEvents[i] = mCurrentEvent.copy();
        mRecentEventsUnhandled[i] = false;
        mCurrentEvent = null;
        mCurrentEventType = null;
        return;
_L8:
        mViolationMessage.append("\n  ");
        appendEvent(mViolationMessage, i + 1, inputevent, mRecentEventsUnhandled[j]);
        i++;
        if(true) goto _L10; else goto _L9
_L9:
    }

    public static boolean isInstrumentationEnabled()
    {
        return IS_ENG_BUILD;
    }

    private void problem(String s)
    {
        if(mViolationMessage == null)
            mViolationMessage = new StringBuilder();
        if(mViolationMessage.length() == 0)
            mViolationMessage.append(mCurrentEventType).append(": ");
        else
            mViolationMessage.append("\n  ");
        mViolationMessage.append(s);
    }

    private boolean startEvent(InputEvent inputevent, int i, String s)
    {
        int j = inputevent.getSequenceNumber();
        if(j == mLastEventSeq && i < mLastNestingLevel && s == mLastEventType)
            return false;
        if(i > 0)
        {
            mLastEventSeq = j;
            mLastEventType = s;
            mLastNestingLevel = i;
        } else
        {
            mLastEventSeq = -1;
            mLastEventType = null;
            mLastNestingLevel = 0;
        }
        mCurrentEvent = inputevent;
        mCurrentEventType = s;
        return true;
    }

    public void onGenericMotionEvent(MotionEvent motionevent, int i)
    {
        if(!startEvent(motionevent, i, "GenericMotionEvent"))
            return;
        int j;
        int k;
        int l;
        ensureMetaStateIsNormalized(motionevent.getMetaState());
        j = motionevent.getAction();
        i = motionevent.getSource();
        k = motionevent.getButtonState();
        l = motionevent.getActionButton();
        if((i & 2) == 0) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 7 12: default 88
    //                   7 120
    //                   8 155
    //                   9 100
    //                   10 128
    //                   11 168
    //                   12 328;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L3:
        problem("Invalid action for generic pointer event.");
_L33:
        finishEvent();
        return;
_L6:
        ensurePointerCountIsOneForThisAction(motionevent);
        mHoverEntered = true;
          goto _L10
        motionevent;
        finishEvent();
        throw motionevent;
_L4:
        ensurePointerCountIsOneForThisAction(motionevent);
          goto _L10
_L7:
        ensurePointerCountIsOneForThisAction(motionevent);
        if(!mHoverEntered)
            problem("ACTION_HOVER_EXIT without prior ACTION_HOVER_ENTER");
        mHoverEntered = false;
          goto _L10
_L5:
        ensureHistorySizeIsZeroForThisAction(motionevent);
        ensurePointerCountIsOneForThisAction(motionevent);
          goto _L10
_L8:
        ensureActionButtonIsNonZeroForThisAction(motionevent);
        if((mButtonsPressed & l) != 0)
        {
            motionevent = JVM INSTR new #97  <Class StringBuilder>;
            motionevent.StringBuilder();
            problem(motionevent.append("Action button for ACTION_BUTTON_PRESS event is ").append(l).append(", but it has already been pressed and ").append("has yet to be released.").toString());
        }
        mButtonsPressed = mButtonsPressed | l;
        if(l != 32 || (k & 2) == 0) goto _L12; else goto _L11
_L11:
        mButtonsPressed = mButtonsPressed | 2;
_L14:
        if(mButtonsPressed != k)
            problem(String.format("Reported button state differs from expected button state based on press and release events. Is 0x%08x but expected 0x%08x.", new Object[] {
                Integer.valueOf(k), Integer.valueOf(mButtonsPressed)
            }));
          goto _L10
_L12:
        if(l != 64 || (k & 4) == 0) goto _L14; else goto _L13
_L13:
        mButtonsPressed = mButtonsPressed | 4;
          goto _L14
_L9:
        ensureActionButtonIsNonZeroForThisAction(motionevent);
        if((mButtonsPressed & l) != l)
        {
            motionevent = JVM INSTR new #97  <Class StringBuilder>;
            motionevent.StringBuilder();
            problem(motionevent.append("Action button for ACTION_BUTTON_RELEASE event is ").append(l).append(", but it was either never pressed or has ").append("already been released.").toString());
        }
        mButtonsPressed = mButtonsPressed & l;
        if(l != 32 || (k & 2) != 0) goto _L16; else goto _L15
_L15:
        mButtonsPressed = mButtonsPressed & -3;
_L18:
        if(mButtonsPressed != k)
            problem(String.format("Reported button state differs from expected button state based on press and release events. Is 0x%08x but expected 0x%08x.", new Object[] {
                Integer.valueOf(k), Integer.valueOf(mButtonsPressed)
            }));
          goto _L10
_L16:
        if(l != 64 || (k & 4) != 0) goto _L18; else goto _L17
_L17:
        mButtonsPressed = mButtonsPressed & -5;
          goto _L18
_L2:
        if((i & 0x10) == 0) goto _L10; else goto _L19
_L19:
        j;
        JVM INSTR tableswitch 2 2: default 520
    //                   2 530;
           goto _L20 _L21
        if(true) goto _L10; else goto _L22
_L10:
        if(true) goto _L23; else goto _L22
_L23:
        if(true) goto _L24; else goto _L22
_L24:
        if(true) goto _L25; else goto _L22
_L25:
        if(true) goto _L26; else goto _L22
_L26:
        if(true) goto _L27; else goto _L22
_L27:
        if(true) goto _L28; else goto _L22
_L28:
        if(true) goto _L29; else goto _L22
_L29:
        if(true) goto _L30; else goto _L22
_L30:
        if(true) goto _L31; else goto _L22
_L31:
        if(true) goto _L32; else goto _L22
_L32:
        if(true) goto _L33; else goto _L22
_L22:
_L20:
        problem("Invalid action for generic joystick event.");
          goto _L33
_L21:
        ensurePointerCountIsOneForThisAction(motionevent);
          goto _L33
    }

    public void onInputEvent(InputEvent inputevent, int i)
    {
        if(inputevent instanceof KeyEvent)
        {
            onKeyEvent((KeyEvent)inputevent, i);
        } else
        {
            inputevent = (MotionEvent)inputevent;
            if(inputevent.isTouchEvent())
                onTouchEvent(inputevent, i);
            else
            if((inputevent.getSource() & 4) != 0)
                onTrackballEvent(inputevent, i);
            else
                onGenericMotionEvent(inputevent, i);
        }
    }

    public void onKeyEvent(KeyEvent keyevent, int i)
    {
        if(!startEvent(keyevent, i, "KeyEvent"))
            return;
        int j;
        int k;
        int l;
        ensureMetaStateIsNormalized(keyevent.getMetaState());
        j = keyevent.getAction();
        i = keyevent.getDeviceId();
        k = keyevent.getSource();
        l = keyevent.getKeyCode();
        j;
        JVM INSTR tableswitch 0 2: default 68
    //                   0 108
    //                   1 187
    //                   2 103;
           goto _L1 _L2 _L3 _L4
_L1:
        keyevent = JVM INSTR new #97  <Class StringBuilder>;
        keyevent.StringBuilder();
        problem(keyevent.append("Invalid action ").append(KeyEvent.actionToString(j)).append(" for key event.").toString());
_L4:
        finishEvent();
        return;
_L2:
        KeyState keystate = findKeyState(i, k, l, false);
        if(keystate == null) goto _L6; else goto _L5
_L5:
        if(!keystate.unhandled) goto _L8; else goto _L7
_L7:
        keystate.unhandled = false;
          goto _L4
        keyevent;
        finishEvent();
        throw keyevent;
_L8:
        if((mFlags & 1) != 0 || keyevent.getRepeatCount() != 0) goto _L4; else goto _L9
_L9:
        problem("ACTION_DOWN but key is already down and this event is not a key repeat.");
          goto _L4
_L6:
        addKeyState(i, k, l);
          goto _L4
_L3:
        keyevent = findKeyState(i, k, l, true);
        if(keyevent != null) goto _L11; else goto _L10
_L10:
        problem("ACTION_UP but key was not down.");
          goto _L4
_L11:
        keyevent.recycle();
          goto _L4
    }

    public void onTouchEvent(MotionEvent motionevent, int i)
    {
        int j;
        int k;
        int l;
        if(!startEvent(motionevent, i, "TouchEvent"))
            return;
        j = motionevent.getAction();
        StringBuilder stringbuilder;
        if(j == 0 || j == 3)
            i = 1;
        else
        if(j == 4)
            i = 1;
        else
            i = 0;
        if(i != 0 && (mTouchEventStreamIsTainted || mTouchEventStreamUnhandled))
        {
            mTouchEventStreamIsTainted = false;
            mTouchEventStreamUnhandled = false;
            mTouchEventStreamPointers = 0;
        }
        if(mTouchEventStreamIsTainted)
            motionevent.setTainted(true);
        ensureMetaStateIsNormalized(motionevent.getMetaState());
        k = motionevent.getDeviceId();
        l = motionevent.getSource();
        if(i != 0)
            break MISSING_BLOCK_LABEL_196;
        if(mTouchEventStreamDeviceId != -1 && (mTouchEventStreamDeviceId != k || mTouchEventStreamSource != l))
        {
            stringbuilder = JVM INSTR new #97  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            problem(stringbuilder.append("Touch event stream contains events from multiple sources: previous device id ").append(mTouchEventStreamDeviceId).append(", previous source ").append(Integer.toHexString(mTouchEventStreamSource)).append(", new device id ").append(k).append(", new source ").append(Integer.toHexString(l)).toString());
        }
        mTouchEventStreamDeviceId = k;
        mTouchEventStreamSource = l;
        i = motionevent.getPointerCount();
        if((l & 2) == 0)
            break MISSING_BLOCK_LABEL_823;
        j;
        JVM INSTR tableswitch 0 4: default 256
    //                   0 380
    //                   1 425
    //                   2 448
    //                   3 511
    //                   4 524;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        k = motionevent.getActionMasked();
        l = motionevent.getActionIndex();
        if(k != 5) goto _L8; else goto _L7
_L7:
        if(mTouchEventStreamPointers == 0)
        {
            problem("ACTION_POINTER_DOWN but no other pointers were down.");
            mTouchEventStreamIsTainted = true;
        }
        if(l >= 0 && l < i) goto _L10; else goto _L9
_L9:
        stringbuilder = JVM INSTR new #97  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        problem(stringbuilder.append("ACTION_POINTER_DOWN index is ").append(l).append(" but the pointer count is ").append(i).append(".").toString());
        mTouchEventStreamIsTainted = true;
_L13:
        ensureHistorySizeIsZeroForThisAction(motionevent);
_L11:
        finishEvent();
        return;
_L2:
        if(mTouchEventStreamPointers != 0)
            problem("ACTION_DOWN but pointers are already down.  Probably missing ACTION_UP from previous gesture.");
        ensureHistorySizeIsZeroForThisAction(motionevent);
        ensurePointerCountIsOneForThisAction(motionevent);
        mTouchEventStreamPointers = 1 << motionevent.getPointerId(0);
          goto _L11
        motionevent;
        finishEvent();
        throw motionevent;
_L3:
        ensureHistorySizeIsZeroForThisAction(motionevent);
        ensurePointerCountIsOneForThisAction(motionevent);
        mTouchEventStreamPointers = 0;
        mTouchEventStreamIsTainted = false;
          goto _L11
_L4:
        j = Integer.bitCount(mTouchEventStreamPointers);
        if(i == j) goto _L11; else goto _L12
_L12:
        motionevent = JVM INSTR new #97  <Class StringBuilder>;
        motionevent.StringBuilder();
        problem(motionevent.append("ACTION_MOVE contained ").append(i).append(" pointers but there are currently ").append(j).append(" pointers down.").toString());
        mTouchEventStreamIsTainted = true;
          goto _L11
_L5:
        mTouchEventStreamPointers = 0;
        mTouchEventStreamIsTainted = false;
          goto _L11
_L6:
        if(mTouchEventStreamPointers != 0)
            problem("ACTION_OUTSIDE but pointers are still down.");
        ensureHistorySizeIsZeroForThisAction(motionevent);
        ensurePointerCountIsOneForThisAction(motionevent);
        mTouchEventStreamIsTainted = false;
          goto _L11
_L10:
        j = motionevent.getPointerId(l);
        i = 1 << j;
label0:
        {
            if((mTouchEventStreamPointers & i) == 0)
                break label0;
            StringBuilder stringbuilder1 = JVM INSTR new #97  <Class StringBuilder>;
            stringbuilder1.StringBuilder();
            problem(stringbuilder1.append("ACTION_POINTER_DOWN specified pointer id ").append(j).append(" which is already down.").toString());
            mTouchEventStreamIsTainted = true;
        }
          goto _L13
        mTouchEventStreamPointers = mTouchEventStreamPointers | i;
          goto _L13
_L8:
        if(k != 6)
            break MISSING_BLOCK_LABEL_785;
        if(l >= 0 && l < i) goto _L15; else goto _L14
_L14:
        StringBuilder stringbuilder2 = JVM INSTR new #97  <Class StringBuilder>;
        stringbuilder2.StringBuilder();
        problem(stringbuilder2.append("ACTION_POINTER_UP index is ").append(l).append(" but the pointer count is ").append(i).append(".").toString());
        mTouchEventStreamIsTainted = true;
_L16:
        ensureHistorySizeIsZeroForThisAction(motionevent);
          goto _L11
_L15:
        j = motionevent.getPointerId(l);
        i = 1 << j;
label1:
        {
            if((mTouchEventStreamPointers & i) != 0)
                break label1;
            StringBuilder stringbuilder3 = JVM INSTR new #97  <Class StringBuilder>;
            stringbuilder3.StringBuilder();
            problem(stringbuilder3.append("ACTION_POINTER_UP specified pointer id ").append(j).append(" which is not currently down.").toString());
            mTouchEventStreamIsTainted = true;
        }
          goto _L16
        mTouchEventStreamPointers = mTouchEventStreamPointers & i;
          goto _L16
        motionevent = JVM INSTR new #97  <Class StringBuilder>;
        motionevent.StringBuilder();
        problem(motionevent.append("Invalid action ").append(MotionEvent.actionToString(j)).append(" for touch event.").toString());
          goto _L11
        problem("Source was not SOURCE_CLASS_POINTER.");
          goto _L11
    }

    public void onTrackballEvent(MotionEvent motionevent, int i)
    {
        if(!startEvent(motionevent, i, "TrackballEvent"))
            return;
        ensureMetaStateIsNormalized(motionevent.getMetaState());
        i = motionevent.getAction();
        if((motionevent.getSource() & 4) == 0) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 0 2: default 60
    //                   0 123
    //                   1 179
    //                   2 219;
           goto _L3 _L4 _L5 _L6
_L3:
        StringBuilder stringbuilder = JVM INSTR new #97  <Class StringBuilder>;
        stringbuilder.StringBuilder();
        problem(stringbuilder.append("Invalid action ").append(MotionEvent.actionToString(i)).append(" for trackball event.").toString());
_L11:
        if(!mTrackballDown || motionevent.getPressure() > 0.0F) goto _L8; else goto _L7
_L7:
        problem("Trackball is down but pressure is not greater than 0.");
_L17:
        finishEvent();
        return;
_L4:
        if(!mTrackballDown || !(mTrackballUnhandled ^ true)) goto _L10; else goto _L9
_L9:
        problem("ACTION_DOWN but trackball is already down.");
_L12:
        ensureHistorySizeIsZeroForThisAction(motionevent);
        ensurePointerCountIsOneForThisAction(motionevent);
          goto _L11
        motionevent;
        finishEvent();
        throw motionevent;
_L10:
        mTrackballDown = true;
        mTrackballUnhandled = false;
          goto _L12
_L5:
        if(mTrackballDown) goto _L14; else goto _L13
_L13:
        problem("ACTION_UP but trackball is not down.");
_L15:
        ensureHistorySizeIsZeroForThisAction(motionevent);
        ensurePointerCountIsOneForThisAction(motionevent);
          goto _L11
_L14:
        mTrackballDown = false;
        mTrackballUnhandled = false;
          goto _L15
_L6:
        ensurePointerCountIsOneForThisAction(motionevent);
          goto _L11
_L8:
        if(mTrackballDown || motionevent.getPressure() == 0.0F) goto _L17; else goto _L16
_L16:
        problem("Trackball is up but pressure is not equal to 0.");
          goto _L17
_L2:
        problem("Source was not SOURCE_CLASS_TRACKBALL.");
          goto _L17
    }

    public void onUnhandledEvent(InputEvent inputevent, int i)
    {
        if(i != mLastNestingLevel)
            return;
        if(mRecentEventsUnhandled != null)
            mRecentEventsUnhandled[mMostRecentEventIndex] = true;
        if(!(inputevent instanceof KeyEvent)) goto _L2; else goto _L1
_L1:
        inputevent = (KeyEvent)inputevent;
        inputevent = findKeyState(inputevent.getDeviceId(), inputevent.getSource(), inputevent.getKeyCode(), false);
        if(inputevent != null)
            inputevent.unhandled = true;
_L4:
        return;
_L2:
        inputevent = (MotionEvent)inputevent;
        if(inputevent.isTouchEvent())
            mTouchEventStreamUnhandled = true;
        else
        if((inputevent.getSource() & 4) != 0 && mTrackballDown)
            mTrackballUnhandled = true;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void reset()
    {
        mLastEventSeq = -1;
        mLastNestingLevel = 0;
        mTrackballDown = false;
        mTrackballUnhandled = false;
        mTouchEventStreamPointers = 0;
        mTouchEventStreamIsTainted = false;
        mTouchEventStreamUnhandled = false;
        mHoverEntered = false;
        mButtonsPressed = 0;
        while(mKeyStateList != null) 
        {
            KeyState keystate = mKeyStateList;
            mKeyStateList = keystate.next;
            keystate.recycle();
        }
    }

    private static final String EVENT_TYPE_GENERIC_MOTION = "GenericMotionEvent";
    private static final String EVENT_TYPE_KEY = "KeyEvent";
    private static final String EVENT_TYPE_TOUCH = "TouchEvent";
    private static final String EVENT_TYPE_TRACKBALL = "TrackballEvent";
    public static final int FLAG_RAW_DEVICE_INPUT = 1;
    private static final boolean IS_ENG_BUILD;
    private static final int RECENT_EVENTS_TO_LOG = 5;
    private int mButtonsPressed;
    private final Object mCaller;
    private InputEvent mCurrentEvent;
    private String mCurrentEventType;
    private final int mFlags;
    private boolean mHoverEntered;
    private KeyState mKeyStateList;
    private int mLastEventSeq;
    private String mLastEventType;
    private int mLastNestingLevel;
    private final String mLogTag;
    private int mMostRecentEventIndex;
    private InputEvent mRecentEvents[];
    private boolean mRecentEventsUnhandled[];
    private int mTouchEventStreamDeviceId;
    private boolean mTouchEventStreamIsTainted;
    private int mTouchEventStreamPointers;
    private int mTouchEventStreamSource;
    private boolean mTouchEventStreamUnhandled;
    private boolean mTrackballDown;
    private boolean mTrackballUnhandled;
    private StringBuilder mViolationMessage;

    static 
    {
        IS_ENG_BUILD = Build.IS_ENG;
    }
}
