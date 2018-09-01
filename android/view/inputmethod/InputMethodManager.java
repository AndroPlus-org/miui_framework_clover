// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.*;
import android.text.style.SuggestionSpan;
import android.util.*;
import android.view.*;
import com.android.internal.os.SomeArgs;
import com.android.internal.view.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// Referenced classes of package android.view.inputmethod:
//            BaseInputConnection, EditorInfo, InputContentInfo, InputMethodInfo, 
//            InputMethodSubtype, InputConnectionInspector, InputConnection, CompletionInfo, 
//            CursorAnchorInfo, ExtractedText

public final class InputMethodManager
{
    private static class ControlledInputConnectionWrapper extends IInputConnectionWrapper
    {

        void deactivate()
        {
            if(isFinished())
            {
                return;
            } else
            {
                closeConnection();
                return;
            }
        }

        public boolean isActive()
        {
            boolean flag;
            if(mParentInputMethodManager.mActive)
                flag = isFinished() ^ true;
            else
                flag = false;
            return flag;
        }

        protected void onUserAction()
        {
            mParentInputMethodManager.notifyUserAction();
        }

        public String toString()
        {
            return (new StringBuilder()).append("ControlledInputConnectionWrapper{connection=").append(getInputConnection()).append(" finished=").append(isFinished()).append(" mParentInputMethodManager.mActive=").append(mParentInputMethodManager.mActive).append("}").toString();
        }

        private final InputMethodManager mParentInputMethodManager;

        public ControlledInputConnectionWrapper(Looper looper, InputConnection inputconnection, InputMethodManager inputmethodmanager)
        {
            super(looper, inputconnection);
            mParentInputMethodManager = inputmethodmanager;
        }
    }

    public static interface FinishedInputEventCallback
    {

        public abstract void onFinishedInputEvent(Object obj, boolean flag);
    }

    class H extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 10: default 60
        //                       1 61
        //                       2 159
        //                       3 351
        //                       4 466
        //                       5 629
        //                       6 644
        //                       7 658
        //                       8 60
        //                       9 672
        //                       10 702;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L1 _L9 _L10
_L1:
            return;
_L2:
            SomeArgs someargs = (SomeArgs)message.obj;
            try
            {
                doDump((FileDescriptor)someargs.arg1, (PrintWriter)someargs.arg2, (String[])someargs.arg3);
            }
            // Misplaced declaration of an exception variable
            catch(Message message)
            {
                ((PrintWriter)someargs.arg2).println((new StringBuilder()).append("Exception: ").append(message).toString());
            }
            message = ((Message) (someargs.arg4));
            message;
            JVM INSTR monitorenter ;
            ((CountDownLatch)someargs.arg4).countDown();
            message;
            JVM INSTR monitorexit ;
            someargs.recycle();
            return;
            Exception exception;
            exception;
            throw exception;
_L3:
            Object obj = (InputBindResult)message.obj;
            message = mH;
            message;
            JVM INSTR monitorenter ;
            if(mBindSequence >= 0 && mBindSequence == ((InputBindResult) (obj)).sequence) goto _L12; else goto _L11
_L11:
            StringBuilder stringbuilder = JVM INSTR new #68  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.w("InputMethodManager", stringbuilder.append("Ignoring onBind: cur seq=").append(mBindSequence).append(", given seq=").append(((InputBindResult) (obj)).sequence).toString());
            if(((InputBindResult) (obj)).channel != null && ((InputBindResult) (obj)).channel != mCurChannel)
                ((InputBindResult) (obj)).channel.dispose();
            message;
            JVM INSTR monitorexit ;
            return;
_L12:
            InputMethodManager._2D_set1(InputMethodManager.this, 0);
            setInputChannelLocked(((InputBindResult) (obj)).channel);
            mCurMethod = ((InputBindResult) (obj)).method;
            mCurId = ((InputBindResult) (obj)).id;
            mBindSequence = ((InputBindResult) (obj)).sequence;
            message;
            JVM INSTR monitorexit ;
            startInputInner(5, null, 0, 0, 0);
            return;
            obj;
            throw obj;
_L4:
            int i;
            i = message.arg1;
            int j = message.arg2;
            obj = mH;
            obj;
            JVM INSTR monitorenter ;
            int k = mBindSequence;
            if(k == i) goto _L14; else goto _L13
_L13:
            obj;
            JVM INSTR monitorexit ;
            return;
_L14:
            boolean flag;
            clearBindingLocked();
            if(mServedView != null && mServedView.isFocused())
                mServedConnecting = true;
            flag = mActive;
            obj;
            JVM INSTR monitorexit ;
            if(flag)
                startInputInner(6, null, 0, 0, 0);
            return;
            message;
            throw message;
_L5:
            boolean flag1;
            boolean flag3;
            if(message.arg1 != 0)
                flag1 = true;
            else
                flag1 = false;
            if(message.arg2 != 0)
                flag3 = true;
            else
                flag3 = false;
            message = mH;
            message;
            JVM INSTR monitorenter ;
            mActive = flag1;
            mFullscreenMode = flag3;
            if(flag1) goto _L16; else goto _L15
_L15:
            mHasBeenInactive = true;
            try
            {
                mIInputContext.finishComposingText();
            }
            catch(RemoteException remoteexception) { }
_L16:
            if(mServedView == null || !mServedView.hasWindowFocus() || !InputMethodManager._2D_wrap0(InputMethodManager.this, mHasBeenInactive)) goto _L18; else goto _L17
_L17:
            if(flag1)
                i = 7;
            else
                i = 8;
            startInputInner(i, null, 0, 0, 0);
_L18:
            message;
            JVM INSTR monitorexit ;
            return;
            obj;
            throw obj;
_L6:
            sendInputEventAndReportResultOnMainLooper((PendingEvent)message.obj);
            return;
_L7:
            finishedInputEvent(message.arg1, false, true);
            return;
_L8:
            finishedInputEvent(message.arg1, false, false);
            return;
_L9:
            obj = mH;
            obj;
            JVM INSTR monitorenter ;
            InputMethodManager._2D_set0(InputMethodManager.this, message.arg1);
            obj;
            JVM INSTR monitorexit ;
            return;
            message;
            throw message;
_L10:
            boolean flag2;
            if(message.arg1 != 0)
                flag2 = true;
            else
                flag2 = false;
            message = null;
            obj = mH;
            obj;
            JVM INSTR monitorenter ;
            mFullscreenMode = flag2;
            if(mServedInputConnectionWrapper != null)
                message = mServedInputConnectionWrapper.getInputConnection();
            obj;
            JVM INSTR monitorexit ;
            if(message != null)
                message.reportFullscreenMode(flag2);
            return;
            message;
            throw message;
        }

        final InputMethodManager this$0;

        H(Looper looper)
        {
            this$0 = InputMethodManager.this;
            super(looper, null, true);
        }
    }

    private final class ImeInputEventSender extends InputEventSender
    {

        public void onInputEventFinished(int i, boolean flag)
        {
            finishedInputEvent(i, flag, false);
        }

        final InputMethodManager this$0;

        public ImeInputEventSender(InputChannel inputchannel, Looper looper)
        {
            this$0 = InputMethodManager.this;
            super(inputchannel, looper);
        }
    }

    private final class PendingEvent
        implements Runnable
    {

        public void recycle()
        {
            mEvent = null;
            mToken = null;
            mInputMethodId = null;
            mCallback = null;
            mHandler = null;
            mHandled = false;
        }

        public void run()
        {
            mCallback.onFinishedInputEvent(mToken, mHandled);
            H h = mH;
            h;
            JVM INSTR monitorenter ;
            InputMethodManager._2D_wrap1(InputMethodManager.this, this);
            h;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public FinishedInputEventCallback mCallback;
        public InputEvent mEvent;
        public boolean mHandled;
        public Handler mHandler;
        public String mInputMethodId;
        public Object mToken;
        final InputMethodManager this$0;

        private PendingEvent()
        {
            this$0 = InputMethodManager.this;
            super();
        }

        PendingEvent(PendingEvent pendingevent)
        {
            this();
        }
    }


    static int _2D_set0(InputMethodManager inputmethodmanager, int i)
    {
        inputmethodmanager.mNextUserActionNotificationSequenceNumber = i;
        return i;
    }

    static int _2D_set1(InputMethodManager inputmethodmanager, int i)
    {
        inputmethodmanager.mRequestUpdateCursorAnchorInfoMonitorMode = i;
        return i;
    }

    static boolean _2D_wrap0(InputMethodManager inputmethodmanager, boolean flag)
    {
        return inputmethodmanager.checkFocusNoStartInput(flag);
    }

    static void _2D_wrap1(InputMethodManager inputmethodmanager, PendingEvent pendingevent)
    {
        inputmethodmanager.recyclePendingEventLocked(pendingevent);
    }

    InputMethodManager(Looper looper)
        throws android.os.ServiceManager.ServiceNotFoundException
    {
        this(com.android.internal.view.IInputMethodManager.Stub.asInterface(ServiceManager.getServiceOrThrow("input_method")), looper);
    }

    InputMethodManager(IInputMethodManager iinputmethodmanager, Looper looper)
    {
        mActive = false;
        mHasBeenInactive = true;
        mTmpCursorRect = new Rect();
        mCursorRect = new Rect();
        mNextUserActionNotificationSequenceNumber = -1;
        mLastSentUserActionNotificationSequenceNumber = -1;
        mCursorAnchorInfo = null;
        mBindSequence = -1;
        mRequestUpdateCursorAnchorInfoMonitorMode = 0;
        mPendingEventPool = new android.util.Pools.SimplePool(20);
        mPendingEvents = new SparseArray(20);
        mClient = new com.android.internal.view.IInputMethodClient.Stub() {

            protected void dump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
            {
                CountDownLatch countdownlatch;
                countdownlatch = new CountDownLatch(1);
                SomeArgs someargs = SomeArgs.obtain();
                someargs.arg1 = filedescriptor;
                someargs.arg2 = printwriter;
                someargs.arg3 = as;
                someargs.arg4 = countdownlatch;
                mH.sendMessage(mH.obtainMessage(1, someargs));
                if(!countdownlatch.await(5L, TimeUnit.SECONDS))
                    printwriter.println("Timeout waiting for dump");
_L1:
                return;
                filedescriptor;
                printwriter.println("Interrupted waiting for dump");
                  goto _L1
            }

            public void onBindMethod(InputBindResult inputbindresult)
            {
                mH.obtainMessage(2, inputbindresult).sendToTarget();
            }

            public void onUnbindMethod(int i, int j)
            {
                mH.obtainMessage(3, i, j).sendToTarget();
            }

            public void reportFullscreenMode(boolean flag)
            {
                H h = mH;
                int i;
                if(flag)
                    i = 1;
                else
                    i = 0;
                h.obtainMessage(10, i, 0).sendToTarget();
            }

            public void setActive(boolean flag, boolean flag1)
            {
                int i = 1;
                H h = mH;
                int j;
                if(flag)
                    j = 1;
                else
                    j = 0;
                if(!flag1)
                    i = 0;
                h.obtainMessage(4, j, i).sendToTarget();
            }

            public void setUserActionNotificationSequenceNumber(int i)
            {
                mH.obtainMessage(9, i, 0).sendToTarget();
            }

            public void setUsingInputMethod(boolean flag)
            {
            }

            final InputMethodManager this$0;

            
            {
                this$0 = InputMethodManager.this;
                super();
            }
        }
;
        mDummyInputConnection = new BaseInputConnection(this, false);
        mService = iinputmethodmanager;
        mMainLooper = looper;
        mH = new H(looper);
        mIInputContext = new ControlledInputConnectionWrapper(looper, mDummyInputConnection, this);
    }

    private boolean checkFocusNoStartInput(boolean flag)
    {
        if(mServedView == mNextServedView && flag ^ true)
            return false;
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        View view;
        Object obj;
        view = mServedView;
        obj = mNextServedView;
        if(view != obj || !(flag ^ true))
            break MISSING_BLOCK_LABEL_53;
        h;
        JVM INSTR monitorexit ;
        return false;
        if(mNextServedView != null)
            break MISSING_BLOCK_LABEL_72;
        finishInputLocked();
        closeCurrentInput();
        h;
        JVM INSTR monitorexit ;
        return false;
        obj = mServedInputConnectionWrapper;
        mServedView = mNextServedView;
        mCurrentTextBoxAttribute = null;
        mCompletions = null;
        mServedConnecting = true;
        h;
        JVM INSTR monitorexit ;
        if(obj != null)
            ((ControlledInputConnectionWrapper) (obj)).finishComposingText();
        return true;
        Exception exception;
        exception;
        throw exception;
    }

    private static String dumpViewInfo(View view)
    {
        if(view == null)
        {
            return "null";
        } else
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append(view);
            stringbuilder.append(",focus=").append(view.hasFocus());
            stringbuilder.append(",windowFocus=").append(view.hasWindowFocus());
            stringbuilder.append(",window=").append(view.getWindowToken());
            stringbuilder.append(",temporaryDetach=").append(view.isTemporarilyDetached());
            return stringbuilder.toString();
        }
    }

    private void flushPendingEventsLocked()
    {
        mH.removeMessages(7);
        int i = mPendingEvents.size();
        for(int j = 0; j < i; j++)
        {
            int k = mPendingEvents.keyAt(j);
            Message message = mH.obtainMessage(7, k, 0);
            message.setAsynchronous(true);
            message.sendToTarget();
        }

    }

    public static InputMethodManager getInstance()
    {
        android/view/inputmethod/InputMethodManager;
        JVM INSTR monitorenter ;
        InputMethodManager inputmethodmanager = sInstance;
        if(inputmethodmanager != null)
            break MISSING_BLOCK_LABEL_26;
        inputmethodmanager = JVM INSTR new #2   <Class InputMethodManager>;
        inputmethodmanager.InputMethodManager(Looper.getMainLooper());
        sInstance = inputmethodmanager;
        inputmethodmanager = sInstance;
        android/view/inputmethod/InputMethodManager;
        JVM INSTR monitorexit ;
        return inputmethodmanager;
        Object obj;
        obj;
        IllegalStateException illegalstateexception = JVM INSTR new #337 <Class IllegalStateException>;
        illegalstateexception.IllegalStateException(((Throwable) (obj)));
        throw illegalstateexception;
        obj;
        android/view/inputmethod/InputMethodManager;
        JVM INSTR monitorexit ;
        throw obj;
    }

    private PendingEvent obtainPendingEventLocked(InputEvent inputevent, Object obj, String s, FinishedInputEventCallback finishedinputeventcallback, Handler handler)
    {
        PendingEvent pendingevent = (PendingEvent)mPendingEventPool.acquire();
        PendingEvent pendingevent1 = pendingevent;
        if(pendingevent == null)
            pendingevent1 = new PendingEvent(null);
        pendingevent1.mEvent = inputevent;
        pendingevent1.mToken = obj;
        pendingevent1.mInputMethodId = s;
        pendingevent1.mCallback = finishedinputeventcallback;
        pendingevent1.mHandler = handler;
        return pendingevent1;
    }

    public static InputMethodManager peekInstance()
    {
        return sInstance;
    }

    private void recyclePendingEventLocked(PendingEvent pendingevent)
    {
        pendingevent.recycle();
        mPendingEventPool.release(pendingevent);
    }

    static void scheduleCheckFocusLocked(View view)
    {
        view = view.getViewRootImpl();
        if(view != null)
            view.dispatchCheckFocus();
    }

    private void showInputMethodPickerLocked()
    {
        try
        {
            mService.showInputMethodPickerFromClient(mClient, 0);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public void checkFocus()
    {
        if(checkFocusNoStartInput(false))
            startInputInner(4, null, 0, 0, 0);
    }

    void clearBindingLocked()
    {
        clearConnectionLocked();
        setInputChannelLocked(null);
        mBindSequence = -1;
        mCurId = null;
        mCurMethod = null;
    }

    void clearConnectionLocked()
    {
        mCurrentTextBoxAttribute = null;
        if(mServedInputConnectionWrapper != null)
        {
            mServedInputConnectionWrapper.deactivate();
            mServedInputConnectionWrapper = null;
        }
    }

    public void clearLastInputMethodWindowForTransition(IBinder ibinder)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        mService.clearLastInputMethodWindowForTransition(ibinder);
        h;
        JVM INSTR monitorexit ;
        return;
        ibinder;
        throw ibinder.rethrowFromSystemServer();
        ibinder;
        h;
        JVM INSTR monitorexit ;
        throw ibinder;
    }

    void closeCurrentInput()
    {
        try
        {
            mService.hideSoftInput(mClient, 2, null);
            return;
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
    }

    public int dispatchInputEvent(InputEvent inputevent, Object obj, FinishedInputEventCallback finishedinputeventcallback, Handler handler)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(mCurMethod == null)
            break MISSING_BLOCK_LABEL_130;
        if(!(inputevent instanceof KeyEvent))
            break MISSING_BLOCK_LABEL_64;
        KeyEvent keyevent = (KeyEvent)inputevent;
        if(keyevent.getAction() != 0 || keyevent.getKeyCode() != 63 || keyevent.getRepeatCount() != 0)
            break MISSING_BLOCK_LABEL_64;
        showInputMethodPickerLocked();
        h;
        JVM INSTR monitorexit ;
        return 1;
        int i;
        inputevent = obtainPendingEventLocked(inputevent, obj, mCurId, finishedinputeventcallback, handler);
        if(!mMainLooper.isCurrentThread())
            break MISSING_BLOCK_LABEL_101;
        i = sendInputEventOnMainLooperLocked(inputevent);
        h;
        JVM INSTR monitorexit ;
        return i;
        inputevent = mH.obtainMessage(5, inputevent);
        inputevent.setAsynchronous(true);
        mH.sendMessage(inputevent);
        h;
        JVM INSTR monitorexit ;
        return -1;
        h;
        JVM INSTR monitorexit ;
        return 0;
        inputevent;
        throw inputevent;
    }

    public void dispatchKeyEventFromInputMethod(View view, KeyEvent keyevent)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(view == null) goto _L2; else goto _L1
_L1:
        view = view.getViewRootImpl();
_L4:
        Object obj;
        obj = view;
        if(view != null)
            break MISSING_BLOCK_LABEL_42;
        obj = view;
        if(mServedView != null)
            obj = mServedView.getViewRootImpl();
        if(obj == null)
            break MISSING_BLOCK_LABEL_53;
        ((ViewRootImpl) (obj)).dispatchKeyFromIme(keyevent);
        h;
        JVM INSTR monitorexit ;
        return;
_L2:
        view = null;
        if(true) goto _L4; else goto _L3
_L3:
        view;
        throw view;
    }

    public void displayCompletions(View view, CompletionInfo acompletioninfo[])
    {
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mServedView == view)
            break MISSING_BLOCK_LABEL_46;
        if(mServedView == null)
            break MISSING_BLOCK_LABEL_43;
        flag = mServedView.checkInputConnectionProxy(view);
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_46;
        h;
        JVM INSTR monitorexit ;
        return;
        mCompletions = acompletioninfo;
        view = mCurMethod;
        if(view == null)
            break MISSING_BLOCK_LABEL_73;
        try
        {
            mCurMethod.displayCompletions(mCompletions);
        }
        // Misplaced declaration of an exception variable
        catch(View view) { }
        h;
        JVM INSTR monitorexit ;
        return;
        view;
        throw view;
    }

    void doDump(FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        filedescriptor = new PrintWriterPrinter(printwriter);
        filedescriptor.println((new StringBuilder()).append("Input method client state for ").append(this).append(":").toString());
        filedescriptor.println((new StringBuilder()).append("  mService=").append(mService).toString());
        filedescriptor.println((new StringBuilder()).append("  mMainLooper=").append(mMainLooper).toString());
        filedescriptor.println((new StringBuilder()).append("  mIInputContext=").append(mIInputContext).toString());
        filedescriptor.println((new StringBuilder()).append("  mActive=").append(mActive).append(" mHasBeenInactive=").append(mHasBeenInactive).append(" mBindSequence=").append(mBindSequence).append(" mCurId=").append(mCurId).toString());
        filedescriptor.println((new StringBuilder()).append("  mFullscreenMode=").append(mFullscreenMode).toString());
        filedescriptor.println((new StringBuilder()).append("  mCurMethod=").append(mCurMethod).toString());
        filedescriptor.println((new StringBuilder()).append("  mCurRootView=").append(mCurRootView).toString());
        filedescriptor.println((new StringBuilder()).append("  mServedView=").append(mServedView).toString());
        filedescriptor.println((new StringBuilder()).append("  mNextServedView=").append(mNextServedView).toString());
        filedescriptor.println((new StringBuilder()).append("  mServedConnecting=").append(mServedConnecting).toString());
        if(mCurrentTextBoxAttribute != null)
        {
            filedescriptor.println("  mCurrentTextBoxAttribute:");
            mCurrentTextBoxAttribute.dump(filedescriptor, "    ");
        } else
        {
            filedescriptor.println("  mCurrentTextBoxAttribute: null");
        }
        filedescriptor.println((new StringBuilder()).append("  mServedInputConnectionWrapper=").append(mServedInputConnectionWrapper).toString());
        filedescriptor.println((new StringBuilder()).append("  mCompletions=").append(Arrays.toString(mCompletions)).toString());
        filedescriptor.println((new StringBuilder()).append("  mCursorRect=").append(mCursorRect).toString());
        filedescriptor.println((new StringBuilder()).append("  mCursorSelStart=").append(mCursorSelStart).append(" mCursorSelEnd=").append(mCursorSelEnd).append(" mCursorCandStart=").append(mCursorCandStart).append(" mCursorCandEnd=").append(mCursorCandEnd).toString());
        filedescriptor.println((new StringBuilder()).append("  mNextUserActionNotificationSequenceNumber=").append(mNextUserActionNotificationSequenceNumber).append(" mLastSentUserActionNotificationSequenceNumber=").append(mLastSentUserActionNotificationSequenceNumber).toString());
    }

    public void exposeContent(IBinder ibinder, InputContentInfo inputcontentinfo, EditorInfo editorinfo)
    {
        Uri uri = inputcontentinfo.getContentUri();
        try
        {
            ibinder = mService.createInputContentUriToken(ibinder, uri, editorinfo.packageName);
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            Log.e("InputMethodManager", (new StringBuilder()).append("createInputContentAccessToken failed. contentUri=").append(uri.toString()).append(" packageName=").append(editorinfo.packageName).toString(), ibinder);
            return;
        }
        if(ibinder == null)
        {
            return;
        } else
        {
            inputcontentinfo.setUriToken(ibinder);
            return;
        }
    }

    void finishInputLocked()
    {
        mNextServedView = null;
        if(mServedView != null)
        {
            if(mCurrentTextBoxAttribute != null)
                try
                {
                    mService.finishInput(mClient);
                }
                catch(RemoteException remoteexception)
                {
                    throw remoteexception.rethrowFromSystemServer();
                }
            mServedView = null;
            mCompletions = null;
            mServedConnecting = false;
            clearConnectionLocked();
        }
    }

    void finishedInputEvent(int i, boolean flag, boolean flag1)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        i = mPendingEvents.indexOfKey(i);
        if(i >= 0)
            break MISSING_BLOCK_LABEL_26;
        h;
        JVM INSTR monitorexit ;
        return;
        PendingEvent pendingevent;
        pendingevent = (PendingEvent)mPendingEvents.valueAt(i);
        mPendingEvents.removeAt(i);
        Trace.traceCounter(4L, "aq:imm", mPendingEvents.size());
        if(!flag1) goto _L2; else goto _L1
_L1:
        StringBuilder stringbuilder = JVM INSTR new #259 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("InputMethodManager", stringbuilder.append("Timeout waiting for IME to handle input event after 2500 ms: ").append(pendingevent.mInputMethodId).toString());
_L4:
        h;
        JVM INSTR monitorexit ;
        invokeFinishedInputEventCallback(pendingevent, flag);
        return;
_L2:
        mH.removeMessages(6, pendingevent);
        if(true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void focusIn(View view)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        focusInLocked(view);
        h;
        JVM INSTR monitorexit ;
        return;
        view;
        throw view;
    }

    void focusInLocked(View view)
    {
        if(view != null && view.isTemporarilyDetached())
            return;
        if(mCurRootView != view.getRootView())
        {
            return;
        } else
        {
            mNextServedView = view;
            scheduleCheckFocusLocked(view);
            return;
        }
    }

    public void focusOut(View view)
    {
        view = mH;
        view;
        JVM INSTR monitorenter ;
        View view1 = mServedView;
        view;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public IInputMethodClient getClient()
    {
        return mClient;
    }

    public InputMethodSubtype getCurrentInputMethodSubtype()
    {
        InputMethodSubtype inputmethodsubtype;
        try
        {
            inputmethodsubtype = mService.getCurrentInputMethodSubtype();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return inputmethodsubtype;
    }

    public List getEnabledInputMethodList()
    {
        List list;
        try
        {
            list = mService.getEnabledInputMethodList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public List getEnabledInputMethodSubtypeList(InputMethodInfo inputmethodinfo, boolean flag)
    {
        Object obj = null;
        IInputMethodManager iinputmethodmanager;
        try
        {
            iinputmethodmanager = mService;
        }
        // Misplaced declaration of an exception variable
        catch(InputMethodInfo inputmethodinfo)
        {
            throw inputmethodinfo.rethrowFromSystemServer();
        }
        if(inputmethodinfo != null) goto _L2; else goto _L1
_L1:
        inputmethodinfo = obj;
_L4:
        return iinputmethodmanager.getEnabledInputMethodSubtypeList(inputmethodinfo, flag);
_L2:
        inputmethodinfo = inputmethodinfo.getId();
        if(true) goto _L4; else goto _L3
_L3:
    }

    public IInputContext getInputContext()
    {
        return mIInputContext;
    }

    public List getInputMethodList()
    {
        List list;
        try
        {
            list = mService.getInputMethodList();
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return list;
    }

    public int getInputMethodWindowVisibleHeight()
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        int i = mService.getInputMethodWindowVisibleHeight();
        h;
        JVM INSTR monitorexit ;
        return i;
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowFromSystemServer();
        obj;
        h;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public InputMethodSubtype getLastInputMethodSubtype()
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        InputMethodSubtype inputmethodsubtype = mService.getLastInputMethodSubtype();
        h;
        JVM INSTR monitorexit ;
        return inputmethodsubtype;
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowFromSystemServer();
        obj;
        h;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public Map getShortcutInputMethodsAndSubtypes()
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        HashMap hashmap;
        hashmap = JVM INSTR new #690 <Class HashMap>;
        hashmap.HashMap();
        List list = mService.getShortcutInputMethodsAndSubtypes();
        ArrayList arraylist = null;
        if(list == null) goto _L2; else goto _L1
_L1:
        if(!(list.isEmpty() ^ true)) goto _L2; else goto _L3
_L3:
        int i = list.size();
        int j = 0;
_L8:
        if(j >= i) goto _L2; else goto _L4
_L4:
        Object obj1;
        obj1 = list.get(j);
        if(!(obj1 instanceof InputMethodInfo))
            break; /* Loop/switch isn't completed */
        if(!hashmap.containsKey(obj1)) goto _L6; else goto _L5
_L5:
        Log.e("InputMethodManager", "IMI list already contains the same InputMethod.");
_L2:
        h;
        JVM INSTR monitorexit ;
        return hashmap;
_L6:
        ArrayList arraylist1;
        arraylist1 = JVM INSTR new #711 <Class ArrayList>;
        arraylist1.ArrayList();
        hashmap.put((InputMethodInfo)obj1, arraylist1);
_L10:
        j++;
        arraylist = arraylist1;
        if(true) goto _L8; else goto _L7
_L7:
        arraylist1 = arraylist;
        if(arraylist == null) goto _L10; else goto _L9
_L9:
        arraylist1 = arraylist;
        if(!(obj1 instanceof InputMethodSubtype)) goto _L10; else goto _L11
_L11:
        arraylist.add((InputMethodSubtype)obj1);
        arraylist1 = arraylist;
          goto _L10
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowFromSystemServer();
        obj;
        h;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public void hideSoftInputFromInputMethod(IBinder ibinder, int i)
    {
        try
        {
            mService.hideMySoftInput(ibinder, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public boolean hideSoftInputFromWindow(IBinder ibinder, int i)
    {
        return hideSoftInputFromWindow(ibinder, i, null);
    }

    public boolean hideSoftInputFromWindow(IBinder ibinder, int i, ResultReceiver resultreceiver)
    {
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        IBinder ibinder1;
        if(mServedView == null)
            break MISSING_BLOCK_LABEL_35;
        ibinder1 = mServedView.getWindowToken();
        if(ibinder1 == ibinder)
            break MISSING_BLOCK_LABEL_40;
        h;
        JVM INSTR monitorexit ;
        return false;
        boolean flag = mService.hideSoftInput(mClient, i, resultreceiver);
        h;
        JVM INSTR monitorexit ;
        return flag;
        ibinder;
        throw ibinder.rethrowFromSystemServer();
        ibinder;
        h;
        JVM INSTR monitorexit ;
        throw ibinder;
    }

    public void hideStatusIcon(IBinder ibinder)
    {
        try
        {
            mService.updateStatusIcon(ibinder, null, 0);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    void invokeFinishedInputEventCallback(PendingEvent pendingevent, boolean flag)
    {
        pendingevent.mHandled = flag;
        if(pendingevent.mHandler.getLooper().isCurrentThread())
        {
            pendingevent.run();
        } else
        {
            pendingevent = Message.obtain(pendingevent.mHandler, pendingevent);
            pendingevent.setAsynchronous(true);
            pendingevent.sendToTarget();
        }
    }

    public boolean isAcceptingText()
    {
        boolean flag = false;
        checkFocus();
        boolean flag1 = flag;
        if(mServedInputConnectionWrapper != null)
        {
            flag1 = flag;
            if(mServedInputConnectionWrapper.getInputConnection() != null)
                flag1 = true;
        }
        return flag1;
    }

    public boolean isActive()
    {
        boolean flag;
        flag = false;
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag1 = flag;
        EditorInfo editorinfo;
        if(mServedView == null)
            break MISSING_BLOCK_LABEL_37;
        editorinfo = mCurrentTextBoxAttribute;
        flag1 = flag;
        if(editorinfo != null)
            flag1 = true;
        h;
        JVM INSTR monitorexit ;
        return flag1;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isActive(View view)
    {
        boolean flag;
        flag = false;
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(mServedView == view)
            break MISSING_BLOCK_LABEL_45;
        boolean flag1 = flag;
        if(mServedView == null)
            break MISSING_BLOCK_LABEL_60;
        flag1 = flag;
        if(!mServedView.checkInputConnectionProxy(view))
            break MISSING_BLOCK_LABEL_60;
        view = mCurrentTextBoxAttribute;
        flag1 = flag;
        if(view != null)
            flag1 = true;
        h;
        JVM INSTR monitorexit ;
        return flag1;
        view;
        throw view;
    }

    public boolean isCursorAnchorInfoEnabled()
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag;
        int i;
        if((mRequestUpdateCursorAnchorInfoMonitorMode & 1) != 0)
            flag = true;
        else
            flag = false;
        i = mRequestUpdateCursorAnchorInfoMonitorMode;
        boolean flag1;
        if((i & 2) != 0)
            flag1 = true;
        else
            flag1 = false;
        if(flag)
            flag1 = true;
        h;
        JVM INSTR monitorexit ;
        return flag1;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isFullscreenMode()
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag = mFullscreenMode;
        h;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isWatchingCursor(View view)
    {
        return false;
    }

    public void notifySuggestionPicked(SuggestionSpan suggestionspan, String s, int i)
    {
        try
        {
            mService.notifySuggestionPicked(suggestionspan, s, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(SuggestionSpan suggestionspan)
        {
            throw suggestionspan.rethrowFromSystemServer();
        }
    }

    public void notifyUserAction()
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        int i;
        int j;
        i = mLastSentUserActionNotificationSequenceNumber;
        j = mNextUserActionNotificationSequenceNumber;
        if(i != j)
            break MISSING_BLOCK_LABEL_25;
        h;
        JVM INSTR monitorexit ;
        return;
        mService.notifyUserAction(mNextUserActionNotificationSequenceNumber);
        mLastSentUserActionNotificationSequenceNumber = mNextUserActionNotificationSequenceNumber;
        h;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowFromSystemServer();
        obj;
        h;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public void onPostWindowFocus(View view, View view1, int i, boolean flag, int j)
    {
        boolean flag1 = false;
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(!mHasBeenInactive)
            break MISSING_BLOCK_LABEL_27;
        mHasBeenInactive = false;
        flag1 = true;
        View view2;
        int l;
        if(view1 != null)
            view2 = view1;
        else
            view2 = view;
        focusInLocked(view2);
        h;
        JVM INSTR monitorexit ;
        int k = 0;
        if(view1 != null)
        {
            k = 1;
            if(view1.onCheckIsTextEditor())
                k = 1 | 2;
        }
        l = k;
        if(flag)
            l = k | 4;
        if(checkFocusNoStartInput(flag1) && startInputInner(1, view.getWindowToken(), l, i, j))
            return;
        break MISSING_BLOCK_LABEL_119;
        view;
        throw view;
        view1 = mH;
        view1;
        JVM INSTR monitorenter ;
        mService.startInputOrWindowGainedFocus(2, mClient, view.getWindowToken(), l, i, j, null, null, 0);
        view1;
        JVM INSTR monitorexit ;
        return;
        view;
        throw view.rethrowFromSystemServer();
        view;
        view1;
        JVM INSTR monitorexit ;
        throw view;
    }

    public void onPreWindowFocus(View view, boolean flag)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(view != null)
            break MISSING_BLOCK_LABEL_16;
        mCurRootView = null;
        if(!flag) goto _L2; else goto _L1
_L1:
        mCurRootView = view;
_L4:
        h;
        JVM INSTR monitorexit ;
        return;
_L2:
        if(view != mCurRootView) goto _L4; else goto _L3
_L3:
        mCurRootView = null;
          goto _L4
        view;
        throw view;
    }

    public void onViewDetachedFromWindow(View view)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(mServedView == view)
        {
            mNextServedView = null;
            scheduleCheckFocusLocked(view);
        }
        h;
        JVM INSTR monitorexit ;
        return;
        view;
        throw view;
    }

    public void registerSuggestionSpansForNotification(SuggestionSpan asuggestionspan[])
    {
        try
        {
            mService.registerSuggestionSpansForNotification(asuggestionspan);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(SuggestionSpan asuggestionspan[])
        {
            throw asuggestionspan.rethrowFromSystemServer();
        }
    }

    public void reportFullscreenMode(IBinder ibinder, boolean flag)
    {
        try
        {
            mService.reportFullscreenMode(ibinder, flag);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public void restartInput(View view)
    {
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mServedView == view)
            break MISSING_BLOCK_LABEL_44;
        if(mServedView == null)
            break MISSING_BLOCK_LABEL_41;
        flag = mServedView.checkInputConnectionProxy(view);
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_44;
        h;
        JVM INSTR monitorexit ;
        return;
        mServedConnecting = true;
        h;
        JVM INSTR monitorexit ;
        startInputInner(3, null, 0, 0, 0);
        return;
        view;
        throw view;
    }

    public void sendAppPrivateCommand(View view, String s, Bundle bundle)
    {
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(mServedView != view && (mServedView == null || mServedView.checkInputConnectionProxy(view) ^ true)) goto _L2; else goto _L1
_L1:
        view = mCurrentTextBoxAttribute;
        if(view != null) goto _L3; else goto _L2
_L2:
        h;
        JVM INSTR monitorexit ;
        return;
_L3:
        if((view = mCurMethod) == null) goto _L2; else goto _L4
_L4:
        mCurMethod.appPrivateCommand(s, bundle);
_L6:
        h;
        JVM INSTR monitorexit ;
        return;
        view;
        s = JVM INSTR new #259 <Class StringBuilder>;
        s.StringBuilder();
        Log.w("InputMethodManager", s.append("IME died: ").append(mCurId).toString(), view);
        if(true) goto _L6; else goto _L5
_L5:
        view;
        throw view;
    }

    void sendInputEventAndReportResultOnMainLooper(PendingEvent pendingevent)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        int i = sendInputEventOnMainLooperLocked(pendingevent);
        if(i != -1)
            break MISSING_BLOCK_LABEL_21;
        h;
        JVM INSTR monitorexit ;
        return;
        boolean flag;
        if(i == 1)
            flag = true;
        else
            flag = false;
        invokeFinishedInputEventCallback(pendingevent, flag);
        return;
        pendingevent;
        throw pendingevent;
    }

    int sendInputEventOnMainLooperLocked(PendingEvent pendingevent)
    {
        if(mCurChannel != null)
        {
            if(mCurSender == null)
                mCurSender = new ImeInputEventSender(mCurChannel, mH.getLooper());
            InputEvent inputevent = pendingevent.mEvent;
            int i = inputevent.getSequenceNumber();
            if(mCurSender.sendInputEvent(i, inputevent))
            {
                mPendingEvents.put(i, pendingevent);
                Trace.traceCounter(4L, "aq:imm", mPendingEvents.size());
                pendingevent = mH.obtainMessage(6, i, 0, pendingevent);
                pendingevent.setAsynchronous(true);
                mH.sendMessageDelayed(pendingevent, 2500L);
                return -1;
            }
            Log.w("InputMethodManager", (new StringBuilder()).append("Unable to send input event to IME: ").append(mCurId).append(" dropping: ").append(inputevent).toString());
        }
        return 0;
    }

    public void setAdditionalInputMethodSubtypes(String s, InputMethodSubtype ainputmethodsubtype[])
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        mService.setAdditionalInputMethodSubtypes(s, ainputmethodsubtype);
        h;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s.rethrowFromSystemServer();
        s;
        h;
        JVM INSTR monitorexit ;
        throw s;
    }

    public boolean setCurrentInputMethodSubtype(InputMethodSubtype inputmethodsubtype)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag = mService.setCurrentInputMethodSubtype(inputmethodsubtype);
        h;
        JVM INSTR monitorexit ;
        return flag;
        inputmethodsubtype;
        throw inputmethodsubtype.rethrowFromSystemServer();
        inputmethodsubtype;
        h;
        JVM INSTR monitorexit ;
        throw inputmethodsubtype;
    }

    public void setImeWindowStatus(IBinder ibinder, IBinder ibinder1, int i, int j)
    {
        try
        {
            mService.setImeWindowStatus(ibinder, ibinder1, i, j);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    void setInputChannelLocked(InputChannel inputchannel)
    {
        if(mCurChannel != inputchannel)
        {
            if(mCurSender != null)
            {
                flushPendingEventsLocked();
                mCurSender.dispose();
                mCurSender = null;
            }
            if(mCurChannel != null)
                mCurChannel.dispose();
            mCurChannel = inputchannel;
        }
    }

    public void setInputMethod(IBinder ibinder, String s)
    {
        try
        {
            mService.setInputMethod(ibinder, s);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public void setInputMethodAndSubtype(IBinder ibinder, String s, InputMethodSubtype inputmethodsubtype)
    {
        try
        {
            mService.setInputMethodAndSubtype(ibinder, s, inputmethodsubtype);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public void setUpdateCursorAnchorInfoMode(int i)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        mRequestUpdateCursorAnchorInfoMonitorMode = i;
        h;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean shouldOfferSwitchingToNextInputMethod(IBinder ibinder)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag = mService.shouldOfferSwitchingToNextInputMethod(ibinder);
        h;
        JVM INSTR monitorexit ;
        return flag;
        ibinder;
        throw ibinder.rethrowFromSystemServer();
        ibinder;
        h;
        JVM INSTR monitorexit ;
        throw ibinder;
    }

    public void showInputMethodAndSubtypeEnabler(String s)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        mService.showInputMethodAndSubtypeEnablerFromClient(mClient, s);
        h;
        JVM INSTR monitorexit ;
        return;
        s;
        throw s.rethrowFromSystemServer();
        s;
        h;
        JVM INSTR monitorexit ;
        throw s;
    }

    public void showInputMethodPicker()
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        showInputMethodPickerLocked();
        h;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void showInputMethodPicker(boolean flag)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        int i;
        if(flag)
            i = 1;
        else
            i = 2;
        mService.showInputMethodPickerFromClient(mClient, i);
        h;
        JVM INSTR monitorexit ;
        return;
        Object obj;
        obj;
        throw ((RemoteException) (obj)).rethrowFromSystemServer();
        obj;
        h;
        JVM INSTR monitorexit ;
        throw obj;
    }

    public boolean showSoftInput(View view, int i)
    {
        return showSoftInput(view, i, null);
    }

    public boolean showSoftInput(View view, int i, ResultReceiver resultreceiver)
    {
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mServedView == view)
            break MISSING_BLOCK_LABEL_50;
        if(mServedView == null)
            break MISSING_BLOCK_LABEL_45;
        flag = mServedView.checkInputConnectionProxy(view);
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_50;
        h;
        JVM INSTR monitorexit ;
        return false;
        flag = mService.showSoftInput(mClient, i, resultreceiver);
        h;
        JVM INSTR monitorexit ;
        return flag;
        view;
        throw view.rethrowFromSystemServer();
        view;
        h;
        JVM INSTR monitorexit ;
        throw view;
    }

    public void showSoftInputFromInputMethod(IBinder ibinder, int i)
    {
        try
        {
            mService.showMySoftInput(ibinder, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    public void showSoftInputUnchecked(int i, ResultReceiver resultreceiver)
    {
        try
        {
            Log.w("InputMethodManager", "showSoftInputUnchecked() is a hidden method, which will be removed soon. If you are using android.support.v7.widget.SearchView, please update to version 26.0 or newer version.");
            mService.showSoftInput(mClient, i, resultreceiver);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(ResultReceiver resultreceiver)
        {
            throw resultreceiver.rethrowFromSystemServer();
        }
    }

    public void showStatusIcon(IBinder ibinder, String s, int i)
    {
        try
        {
            mService.updateStatusIcon(ibinder, s, i);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder)
        {
            throw ibinder.rethrowFromSystemServer();
        }
    }

    boolean startInputInner(final int startInputReason, IBinder ibinder, int i, int j, int k)
    {
        Object obj = mH;
        obj;
        JVM INSTR monitorenter ;
        Object obj1 = mServedView;
        if(obj1 != null)
            break MISSING_BLOCK_LABEL_25;
        obj;
        JVM INSTR monitorexit ;
        return false;
        obj;
        JVM INSTR monitorexit ;
        Handler handler;
        handler = ((View) (obj1)).getHandler();
        if(handler == null)
        {
            closeCurrentInput();
            return false;
        }
        break MISSING_BLOCK_LABEL_52;
        ibinder;
        throw ibinder;
        EditorInfo editorinfo;
        InputConnection inputconnection;
        if(handler.getLooper() != Looper.myLooper())
        {
            handler.post(new Runnable() {

                public void run()
                {
                    startInputInner(startInputReason, null, 0, 0, 0);
                }

                final InputMethodManager this$0;
                final int val$startInputReason;

            
            {
                this$0 = InputMethodManager.this;
                startInputReason = i;
                super();
            }
            }
);
            return false;
        }
        editorinfo = new EditorInfo();
        editorinfo.packageName = ((View) (obj1)).getContext().getOpPackageName();
        editorinfo.fieldId = ((View) (obj1)).getId();
        inputconnection = ((View) (obj1)).onCreateInputConnection(editorinfo);
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mServedView != obj1)
            break MISSING_BLOCK_LABEL_152;
        flag = mServedConnecting;
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_157;
        h;
        JVM INSTR monitorexit ;
        return false;
        int l = i;
        if(mCurrentTextBoxAttribute == null)
            l = i | 0x100;
        mCurrentTextBoxAttribute = editorinfo;
        mServedConnecting = false;
        if(mServedInputConnectionWrapper != null)
        {
            mServedInputConnectionWrapper.deactivate();
            mServedInputConnectionWrapper = null;
        }
        if(inputconnection == null) goto _L2; else goto _L1
_L1:
        mCursorSelStart = editorinfo.initialSelStart;
        mCursorSelEnd = editorinfo.initialSelEnd;
        mCursorCandStart = -1;
        mCursorCandEnd = -1;
        mCursorRect.setEmpty();
        mCursorAnchorInfo = null;
        i = InputConnectionInspector.getMissingMethodFlags(inputconnection);
        if((i & 0x20) == 0) goto _L4; else goto _L3
_L3:
        obj1 = null;
_L14:
        obj = JVM INSTR new #10  <Class InputMethodManager$ControlledInputConnectionWrapper>;
        if(obj1 == null) goto _L6; else goto _L5
_L5:
        obj1 = ((Handler) (obj1)).getLooper();
_L15:
        ((ControlledInputConnectionWrapper) (obj)).ControlledInputConnectionWrapper(((Looper) (obj1)), inputconnection, this);
        obj1 = obj;
_L16:
        mServedInputConnectionWrapper = ((ControlledInputConnectionWrapper) (obj1));
        ibinder = mService.startInputOrWindowGainedFocus(startInputReason, mClient, ibinder, l, j, k, editorinfo, ((IInputContext) (obj1)), i);
        if(ibinder == null) goto _L8; else goto _L7
_L7:
        if(((InputBindResult) (ibinder)).id == null) goto _L10; else goto _L9
_L9:
        setInputChannelLocked(((InputBindResult) (ibinder)).channel);
        mBindSequence = ((InputBindResult) (ibinder)).sequence;
        mCurMethod = ((InputBindResult) (ibinder)).method;
        mCurId = ((InputBindResult) (ibinder)).id;
        mNextUserActionNotificationSequenceNumber = ((InputBindResult) (ibinder)).userActionNotificationSequenceNumber;
_L18:
        if(mCurMethod == null) goto _L12; else goto _L11
_L11:
        ibinder = mCompletions;
        if(ibinder == null) goto _L12; else goto _L13
_L13:
        mCurMethod.displayCompletions(mCompletions);
_L12:
        h;
        JVM INSTR monitorexit ;
        return true;
_L4:
        obj1 = inputconnection.getHandler();
          goto _L14
_L6:
        obj1 = handler.getLooper();
          goto _L15
_L2:
        obj1 = null;
        i = 0;
          goto _L16
_L10:
        if(((InputBindResult) (ibinder)).channel != null && ((InputBindResult) (ibinder)).channel != mCurChannel)
            ((InputBindResult) (ibinder)).channel.dispose();
        ibinder = mCurMethod;
        if(ibinder != null) goto _L18; else goto _L17
_L17:
        h;
        JVM INSTR monitorexit ;
        return true;
_L8:
        if(startInputReason != 1) goto _L18; else goto _L19
_L19:
        if(mActive) goto _L18; else goto _L20
_L20:
        mHasBeenInactive = true;
          goto _L18
        RemoteException remoteexception;
        remoteexception;
        ibinder = JVM INSTR new #259 <Class StringBuilder>;
        ibinder.StringBuilder();
        Log.w("InputMethodManager", ibinder.append("IME died: ").append(mCurId).toString(), remoteexception);
          goto _L12
        ibinder;
        throw ibinder;
        ibinder;
        Log.w("InputMethodManager", ibinder);
          goto _L12
        ibinder;
          goto _L12
    }

    public boolean switchToLastInputMethod(IBinder ibinder)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag = mService.switchToLastInputMethod(ibinder);
        h;
        JVM INSTR monitorexit ;
        return flag;
        ibinder;
        throw ibinder.rethrowFromSystemServer();
        ibinder;
        h;
        JVM INSTR monitorexit ;
        throw ibinder;
    }

    public boolean switchToNextInputMethod(IBinder ibinder, boolean flag)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        flag = mService.switchToNextInputMethod(ibinder, flag);
        h;
        JVM INSTR monitorexit ;
        return flag;
        ibinder;
        throw ibinder.rethrowFromSystemServer();
        ibinder;
        h;
        JVM INSTR monitorexit ;
        throw ibinder;
    }

    public void toggleSoftInput(int i, int j)
    {
        if(mCurMethod == null)
            break MISSING_BLOCK_LABEL_18;
        mCurMethod.toggleSoftInput(i, j);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void toggleSoftInputFromWindow(IBinder ibinder, int i, int j)
    {
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        IBinder ibinder1;
        if(mServedView == null)
            break MISSING_BLOCK_LABEL_31;
        ibinder1 = mServedView.getWindowToken();
        if(ibinder1 == ibinder)
            break MISSING_BLOCK_LABEL_35;
        h;
        JVM INSTR monitorexit ;
        return;
        ibinder = mCurMethod;
        if(ibinder == null)
            break MISSING_BLOCK_LABEL_55;
        try
        {
            mCurMethod.toggleSoftInput(i, j);
        }
        // Misplaced declaration of an exception variable
        catch(IBinder ibinder) { }
        h;
        JVM INSTR monitorexit ;
        return;
        ibinder;
        throw ibinder;
    }

    public void updateCursor(View view, int i, int j, int k, int l)
    {
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(mServedView != view && (mServedView == null || mServedView.checkInputConnectionProxy(view) ^ true)) goto _L2; else goto _L1
_L1:
        view = mCurrentTextBoxAttribute;
        if(view != null) goto _L3; else goto _L2
_L2:
        h;
        JVM INSTR monitorexit ;
        return;
_L3:
        if(mCurMethod == null) goto _L2; else goto _L4
_L4:
        boolean flag;
        mTmpCursorRect.set(i, j, k, l);
        flag = mCursorRect.equals(mTmpCursorRect);
        if(flag)
            break MISSING_BLOCK_LABEL_116;
        mCurMethod.updateCursor(mTmpCursorRect);
        mCursorRect.set(mTmpCursorRect);
_L6:
        h;
        JVM INSTR monitorexit ;
        return;
        view;
        StringBuilder stringbuilder = JVM INSTR new #259 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("InputMethodManager", stringbuilder.append("IME died: ").append(mCurId).toString(), view);
        if(true) goto _L6; else goto _L5
_L5:
        view;
        throw view;
    }

    public void updateCursorAnchorInfo(View view, CursorAnchorInfo cursoranchorinfo)
    {
        if(view == null || cursoranchorinfo == null)
            return;
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(mServedView != view && (mServedView == null || mServedView.checkInputConnectionProxy(view) ^ true)) goto _L2; else goto _L1
_L1:
        view = mCurrentTextBoxAttribute;
        if(view != null) goto _L3; else goto _L2
_L2:
        h;
        JVM INSTR monitorexit ;
        return;
_L3:
        if(mCurMethod == null) goto _L2; else goto _L4
_L4:
        boolean flag;
        boolean flag1;
        if((mRequestUpdateCursorAnchorInfoMonitorMode & 1) != 0)
            flag = true;
        else
            flag = false;
        if(flag)
            break MISSING_BLOCK_LABEL_108;
        flag1 = Objects.equals(mCursorAnchorInfo, cursoranchorinfo);
        if(!flag1)
            break MISSING_BLOCK_LABEL_108;
        h;
        JVM INSTR monitorexit ;
        return;
        mCurMethod.updateCursorAnchorInfo(cursoranchorinfo);
        mCursorAnchorInfo = cursoranchorinfo;
        mRequestUpdateCursorAnchorInfoMonitorMode = mRequestUpdateCursorAnchorInfoMonitorMode & -2;
_L5:
        h;
        JVM INSTR monitorexit ;
        return;
        view;
        cursoranchorinfo = JVM INSTR new #259 <Class StringBuilder>;
        cursoranchorinfo.StringBuilder();
        Log.w("InputMethodManager", cursoranchorinfo.append("IME died: ").append(mCurId).toString(), view);
          goto _L5
        view;
        throw view;
    }

    public void updateExtractedText(View view, int i, ExtractedText extractedtext)
    {
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        boolean flag;
        if(mServedView == view)
            break MISSING_BLOCK_LABEL_49;
        if(mServedView == null)
            break MISSING_BLOCK_LABEL_45;
        flag = mServedView.checkInputConnectionProxy(view);
        if(!(flag ^ true))
            break MISSING_BLOCK_LABEL_49;
        h;
        JVM INSTR monitorexit ;
        return;
        view = mCurMethod;
        if(view == null)
            break MISSING_BLOCK_LABEL_69;
        try
        {
            mCurMethod.updateExtractedText(i, extractedtext);
        }
        // Misplaced declaration of an exception variable
        catch(View view) { }
        h;
        JVM INSTR monitorexit ;
        return;
        view;
        throw view;
    }

    public void updateSelection(View view, int i, int j, int k, int l)
    {
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(mServedView != view && (mServedView == null || mServedView.checkInputConnectionProxy(view) ^ true)) goto _L2; else goto _L1
_L1:
        view = mCurrentTextBoxAttribute;
        if(view != null) goto _L3; else goto _L2
_L2:
        h;
        JVM INSTR monitorexit ;
        return;
_L3:
        if(mCurMethod == null) goto _L2; else goto _L4
_L4:
        if(mCursorSelStart != i) goto _L6; else goto _L5
_L5:
        int i1 = mCursorSelEnd;
        if(i1 == j) goto _L7; else goto _L6
_L6:
        int k1 = mCursorSelStart;
        int j1 = mCursorSelEnd;
        mCursorSelStart = i;
        mCursorSelEnd = j;
        mCursorCandStart = k;
        mCursorCandEnd = l;
        mCurMethod.updateSelection(k1, j1, i, j, k, l);
_L8:
        h;
        JVM INSTR monitorexit ;
        return;
_L7:
        if(mCursorCandStart == k && mCursorCandEnd == l) goto _L8; else goto _L6
        RemoteException remoteexception;
        remoteexception;
        view = JVM INSTR new #259 <Class StringBuilder>;
        view.StringBuilder();
        Log.w("InputMethodManager", view.append("IME died: ").append(mCurId).toString(), remoteexception);
          goto _L8
        view;
        throw view;
    }

    public void viewClicked(View view)
    {
        boolean flag;
        H h;
        if(mServedView != mNextServedView)
            flag = true;
        else
            flag = false;
        checkFocus();
        h = mH;
        h;
        JVM INSTR monitorenter ;
        if(mServedView != view && (mServedView == null || mServedView.checkInputConnectionProxy(view) ^ true)) goto _L2; else goto _L1
_L1:
        view = mCurrentTextBoxAttribute;
        if(view != null) goto _L3; else goto _L2
_L2:
        h;
        JVM INSTR monitorexit ;
        return;
_L3:
        if((view = mCurMethod) == null) goto _L2; else goto _L4
_L4:
        mCurMethod.viewClicked(flag);
_L5:
        h;
        JVM INSTR monitorexit ;
        return;
        view;
        StringBuilder stringbuilder = JVM INSTR new #259 <Class StringBuilder>;
        stringbuilder.StringBuilder();
        Log.w("InputMethodManager", stringbuilder.append("IME died: ").append(mCurId).toString(), view);
          goto _L5
        view;
        throw view;
    }

    public void windowDismissed(IBinder ibinder)
    {
        checkFocus();
        H h = mH;
        h;
        JVM INSTR monitorenter ;
        if(mServedView != null && mServedView.getWindowToken() == ibinder)
            finishInputLocked();
        h;
        JVM INSTR monitorexit ;
        return;
        ibinder;
        throw ibinder;
    }

    public static final int CONTROL_START_INITIAL = 256;
    public static final int CONTROL_WINDOW_FIRST = 4;
    public static final int CONTROL_WINDOW_IS_TEXT_EDITOR = 2;
    public static final int CONTROL_WINDOW_VIEW_HAS_FOCUS = 1;
    static final boolean DEBUG = false;
    public static final int DISPATCH_HANDLED = 1;
    public static final int DISPATCH_IN_PROGRESS = -1;
    public static final int DISPATCH_NOT_HANDLED = 0;
    public static final int HIDE_IMPLICIT_ONLY = 1;
    public static final int HIDE_NOT_ALWAYS = 2;
    static final long INPUT_METHOD_NOT_RESPONDING_TIMEOUT = 2500L;
    static final int MSG_BIND = 2;
    static final int MSG_DUMP = 1;
    static final int MSG_FLUSH_INPUT_EVENT = 7;
    static final int MSG_REPORT_FULLSCREEN_MODE = 10;
    static final int MSG_SEND_INPUT_EVENT = 5;
    static final int MSG_SET_ACTIVE = 4;
    static final int MSG_SET_USER_ACTION_NOTIFICATION_SEQUENCE_NUMBER = 9;
    static final int MSG_TIMEOUT_INPUT_EVENT = 6;
    static final int MSG_UNBIND = 3;
    private static final int NOT_AN_ACTION_NOTIFICATION_SEQUENCE_NUMBER = -1;
    static final String PENDING_EVENT_COUNTER = "aq:imm";
    private static final int REQUEST_UPDATE_CURSOR_ANCHOR_INFO_NONE = 0;
    public static final int RESULT_HIDDEN = 3;
    public static final int RESULT_SHOWN = 2;
    public static final int RESULT_UNCHANGED_HIDDEN = 1;
    public static final int RESULT_UNCHANGED_SHOWN = 0;
    public static final int SHOW_FORCED = 2;
    public static final int SHOW_IMPLICIT = 1;
    public static final int SHOW_IM_PICKER_MODE_AUTO = 0;
    public static final int SHOW_IM_PICKER_MODE_EXCLUDE_AUXILIARY_SUBTYPES = 2;
    public static final int SHOW_IM_PICKER_MODE_INCLUDE_AUXILIARY_SUBTYPES = 1;
    static final String TAG = "InputMethodManager";
    static InputMethodManager sInstance;
    boolean mActive;
    int mBindSequence;
    final com.android.internal.view.IInputMethodClient.Stub mClient;
    CompletionInfo mCompletions[];
    InputChannel mCurChannel;
    String mCurId;
    IInputMethodSession mCurMethod;
    View mCurRootView;
    ImeInputEventSender mCurSender;
    EditorInfo mCurrentTextBoxAttribute;
    private CursorAnchorInfo mCursorAnchorInfo;
    int mCursorCandEnd;
    int mCursorCandStart;
    Rect mCursorRect;
    int mCursorSelEnd;
    int mCursorSelStart;
    final InputConnection mDummyInputConnection;
    boolean mFullscreenMode;
    final H mH;
    boolean mHasBeenInactive;
    final IInputContext mIInputContext;
    private int mLastSentUserActionNotificationSequenceNumber;
    final Looper mMainLooper;
    View mNextServedView;
    private int mNextUserActionNotificationSequenceNumber;
    final android.util.Pools.Pool mPendingEventPool;
    final SparseArray mPendingEvents;
    private int mRequestUpdateCursorAnchorInfoMonitorMode;
    boolean mServedConnecting;
    ControlledInputConnectionWrapper mServedInputConnectionWrapper;
    View mServedView;
    final IInputMethodManager mService;
    Rect mTmpCursorRect;
}
