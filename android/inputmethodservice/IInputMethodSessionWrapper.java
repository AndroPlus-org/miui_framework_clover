// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.inputmethodservice;

import android.content.Context;
import android.graphics.Rect;
import android.os.*;
import android.util.Log;
import android.util.SparseArray;
import android.view.*;
import android.view.inputmethod.*;
import com.android.internal.os.HandlerCaller;
import com.android.internal.os.SomeArgs;

class IInputMethodSessionWrapper extends com.android.internal.view.IInputMethodSession.Stub
    implements com.android.internal.os.HandlerCaller.Callback
{
    private final class ImeInputEventReceiver extends InputEventReceiver
        implements android.view.inputmethod.InputMethodSession.EventCallback
    {

        public void finishedEvent(int i, boolean flag)
        {
            i = mPendingEvents.indexOfKey(i);
            if(i >= 0)
            {
                InputEvent inputevent = (InputEvent)mPendingEvents.valueAt(i);
                mPendingEvents.removeAt(i);
                finishInputEvent(inputevent, flag);
            }
        }

        public void onInputEvent(InputEvent inputevent, int i)
        {
            if(mInputMethodSession == null)
            {
                finishInputEvent(inputevent, false);
                return;
            }
            i = inputevent.getSequenceNumber();
            mPendingEvents.put(i, inputevent);
            if(inputevent instanceof KeyEvent)
            {
                inputevent = (KeyEvent)inputevent;
                mInputMethodSession.dispatchKeyEvent(i, inputevent, this);
            } else
            {
                inputevent = (MotionEvent)inputevent;
                if(inputevent.isFromSource(4))
                    mInputMethodSession.dispatchTrackballEvent(i, inputevent, this);
                else
                    mInputMethodSession.dispatchGenericMotionEvent(i, inputevent, this);
            }
        }

        private final SparseArray mPendingEvents = new SparseArray();
        final IInputMethodSessionWrapper this$0;

        public ImeInputEventReceiver(InputChannel inputchannel, Looper looper)
        {
            this$0 = IInputMethodSessionWrapper.this;
            super(inputchannel, looper);
        }
    }


    public IInputMethodSessionWrapper(Context context, InputMethodSession inputmethodsession, InputChannel inputchannel)
    {
        mCaller = new HandlerCaller(context, null, this, true);
        mInputMethodSession = inputmethodsession;
        mChannel = inputchannel;
        if(inputchannel != null)
            mReceiver = new ImeInputEventReceiver(inputchannel, context.getMainLooper());
    }

    private void doFinishSession()
    {
        mInputMethodSession = null;
        if(mReceiver != null)
        {
            mReceiver.dispose();
            mReceiver = null;
        }
        if(mChannel != null)
        {
            mChannel.dispose();
            mChannel = null;
        }
    }

    public void appPrivateCommand(String s, Bundle bundle)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageOO(100, s, bundle));
    }

    public void displayCompletions(CompletionInfo acompletioninfo[])
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(65, acompletioninfo));
    }

    public void executeMessage(Message message)
    {
        boolean flag = true;
        if(mInputMethodSession != null) goto _L2; else goto _L1
_L1:
        message.what;
        JVM INSTR lookupswitch 2: default 40
    //                   90: 41
    //                   100: 41;
           goto _L3 _L4 _L4
_L3:
        return;
_L4:
        ((SomeArgs)message.obj).recycle();
        if(true) goto _L3; else goto _L2
_L2:
        InputMethodSession inputmethodsession;
        switch(message.what)
        {
        default:
            Log.w("InputMethodWrapper", (new StringBuilder()).append("Unhandled message code: ").append(message.what).toString());
            return;

        case 60: // '<'
            mInputMethodSession.finishInput();
            return;

        case 65: // 'A'
            mInputMethodSession.displayCompletions((CompletionInfo[])message.obj);
            return;

        case 67: // 'C'
            mInputMethodSession.updateExtractedText(message.arg1, (ExtractedText)message.obj);
            return;

        case 90: // 'Z'
            message = (SomeArgs)message.obj;
            mInputMethodSession.updateSelection(((SomeArgs) (message)).argi1, ((SomeArgs) (message)).argi2, ((SomeArgs) (message)).argi3, ((SomeArgs) (message)).argi4, ((SomeArgs) (message)).argi5, ((SomeArgs) (message)).argi6);
            message.recycle();
            return;

        case 95: // '_'
            mInputMethodSession.updateCursor((Rect)message.obj);
            return;

        case 99: // 'c'
            mInputMethodSession.updateCursorAnchorInfo((CursorAnchorInfo)message.obj);
            return;

        case 100: // 'd'
            message = (SomeArgs)message.obj;
            mInputMethodSession.appPrivateCommand((String)((SomeArgs) (message)).arg1, (Bundle)((SomeArgs) (message)).arg2);
            message.recycle();
            return;

        case 105: // 'i'
            mInputMethodSession.toggleSoftInput(message.arg1, message.arg2);
            return;

        case 110: // 'n'
            doFinishSession();
            return;

        case 115: // 's'
            inputmethodsession = mInputMethodSession;
            break;
        }
        if(message.arg1 != 1)
            flag = false;
        inputmethodsession.viewClicked(flag);
        return;
    }

    public void finishInput()
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessage(60));
    }

    public void finishSession()
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessage(110));
    }

    public InputMethodSession getInternalInputMethodSession()
    {
        return mInputMethodSession;
    }

    public void toggleSoftInput(int i, int j)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageII(105, i, j));
    }

    public void updateCursor(Rect rect)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(95, rect));
    }

    public void updateCursorAnchorInfo(CursorAnchorInfo cursoranchorinfo)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageO(99, cursoranchorinfo));
    }

    public void updateExtractedText(int i, ExtractedText extractedtext)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageIO(67, i, extractedtext));
    }

    public void updateSelection(int i, int j, int k, int l, int i1, int j1)
    {
        mCaller.executeOrSendMessage(mCaller.obtainMessageIIIIII(90, i, j, k, l, i1, j1));
    }

    public void viewClicked(boolean flag)
    {
        HandlerCaller handlercaller = mCaller;
        HandlerCaller handlercaller1 = mCaller;
        int i;
        if(flag)
            i = 1;
        else
            i = 0;
        handlercaller.executeOrSendMessage(handlercaller1.obtainMessageI(115, i));
    }

    private static final int DO_APP_PRIVATE_COMMAND = 100;
    private static final int DO_DISPLAY_COMPLETIONS = 65;
    private static final int DO_FINISH_INPUT = 60;
    private static final int DO_FINISH_SESSION = 110;
    private static final int DO_TOGGLE_SOFT_INPUT = 105;
    private static final int DO_UPDATE_CURSOR = 95;
    private static final int DO_UPDATE_CURSOR_ANCHOR_INFO = 99;
    private static final int DO_UPDATE_EXTRACTED_TEXT = 67;
    private static final int DO_UPDATE_SELECTION = 90;
    private static final int DO_VIEW_CLICKED = 115;
    private static final String TAG = "InputMethodWrapper";
    HandlerCaller mCaller;
    InputChannel mChannel;
    InputMethodSession mInputMethodSession;
    ImeInputEventReceiver mReceiver;
}
