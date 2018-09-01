// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.os.*;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.*;
import com.android.internal.os.SomeArgs;

// Referenced classes of package com.android.internal.view:
//            IInputContextCallback

public abstract class IInputConnectionWrapper extends IInputContext.Stub
{
    class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            executeMessage(message);
        }

        final IInputConnectionWrapper this$0;

        MyHandler(Looper looper)
        {
            this$0 = IInputConnectionWrapper.this;
            super(looper);
        }
    }


    public IInputConnectionWrapper(Looper looper, InputConnection inputconnection)
    {
        mLock = new Object();
        mFinished = false;
        mInputConnection = inputconnection;
        mMainLooper = looper;
        mH = new MyHandler(mMainLooper);
    }

    public void beginBatchEdit()
    {
        dispatchMessage(obtainMessage(90));
    }

    public void clearMetaKeyStates(int i)
    {
        dispatchMessage(obtainMessageII(130, i, 0));
    }

    public void closeConnection()
    {
        dispatchMessage(obtainMessage(150));
    }

    public void commitCompletion(CompletionInfo completioninfo)
    {
        dispatchMessage(obtainMessageO(55, completioninfo));
    }

    public void commitContent(InputContentInfo inputcontentinfo, int i, Bundle bundle, int j, IInputContextCallback iinputcontextcallback)
    {
        dispatchMessage(obtainMessageIOOSC(160, i, inputcontentinfo, bundle, j, iinputcontextcallback));
    }

    public void commitCorrection(CorrectionInfo correctioninfo)
    {
        dispatchMessage(obtainMessageO(56, correctioninfo));
    }

    public void commitText(CharSequence charsequence, int i)
    {
        dispatchMessage(obtainMessageIO(50, i, charsequence));
    }

    public void deleteSurroundingText(int i, int j)
    {
        dispatchMessage(obtainMessageII(80, i, j));
    }

    public void deleteSurroundingTextInCodePoints(int i, int j)
    {
        dispatchMessage(obtainMessageII(81, i, j));
    }

    void dispatchMessage(Message message)
    {
        if(Looper.myLooper() == mMainLooper)
        {
            executeMessage(message);
            message.recycle();
            return;
        } else
        {
            mH.sendMessage(message);
            return;
        }
    }

    public void endBatchEdit()
    {
        dispatchMessage(obtainMessage(95));
    }

    void executeMessage(Message message)
    {
        message.what;
        JVM INSTR lookupswitch 24: default 208
    //                   10: 237
    //                   20: 353
    //                   25: 469
    //                   30: 581
    //                   40: 694
    //                   50: 815
    //                   55: 990
    //                   56: 1033
    //                   57: 866
    //                   58: 910
    //                   59: 950
    //                   60: 1076
    //                   63: 1127
    //                   65: 1171
    //                   70: 1206
    //                   80: 1293
    //                   81: 1337
    //                   90: 1381
    //                   95: 1417
    //                   120: 1453
    //                   130: 1253
    //                   140: 1532
    //                   150: 1646
    //                   160: 1756;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25
_L1:
        Log.w("IInputConnectionWrapper", (new StringBuilder()).append("Unhandled message code: ").append(message.what).toString());
        return;
_L2:
        Object obj = (SomeArgs)message.obj;
        Object obj2;
        int i;
        Object obj3;
        obj2 = (IInputContextCallback)((SomeArgs) (obj)).arg6;
        i = ((SomeArgs) (obj)).argi6;
        obj3 = getInputConnection();
        if(obj3 == null) goto _L27; else goto _L26
_L26:
        if(!(isActive() ^ true)) goto _L28; else goto _L27
_L27:
        Log.w("IInputConnectionWrapper", "getTextAfterCursor on inactive InputConnection");
        ((IInputContextCallback) (obj2)).setTextAfterCursor(null, i);
        ((SomeArgs) (obj)).recycle();
        return;
_L28:
        ((IInputContextCallback) (obj2)).setTextAfterCursor(((InputConnection) (obj3)).getTextAfterCursor(message.arg1, message.arg2), i);
        ((SomeArgs) (obj)).recycle();
_L30:
        return;
        message;
        Log.w("IInputConnectionWrapper", "Got RemoteException calling setTextAfterCursor", message);
        ((SomeArgs) (obj)).recycle();
        if(true) goto _L30; else goto _L29
_L29:
        message;
        ((SomeArgs) (obj)).recycle();
        throw message;
_L3:
        obj = (SomeArgs)message.obj;
        obj3 = (IInputContextCallback)((SomeArgs) (obj)).arg6;
        i = ((SomeArgs) (obj)).argi6;
        obj2 = getInputConnection();
        if(obj2 == null) goto _L32; else goto _L31
_L31:
        if(!(isActive() ^ true)) goto _L33; else goto _L32
_L32:
        Log.w("IInputConnectionWrapper", "getTextBeforeCursor on inactive InputConnection");
        ((IInputContextCallback) (obj3)).setTextBeforeCursor(null, i);
        ((SomeArgs) (obj)).recycle();
        return;
_L33:
        ((IInputContextCallback) (obj3)).setTextBeforeCursor(((InputConnection) (obj2)).getTextBeforeCursor(message.arg1, message.arg2), i);
        ((SomeArgs) (obj)).recycle();
_L35:
        return;
        message;
        Log.w("IInputConnectionWrapper", "Got RemoteException calling setTextBeforeCursor", message);
        ((SomeArgs) (obj)).recycle();
        if(true) goto _L35; else goto _L34
_L34:
        message;
        ((SomeArgs) (obj)).recycle();
        throw message;
_L4:
        obj = (SomeArgs)message.obj;
        obj3 = (IInputContextCallback)((SomeArgs) (obj)).arg6;
        i = ((SomeArgs) (obj)).argi6;
        obj2 = getInputConnection();
        if(obj2 == null) goto _L37; else goto _L36
_L36:
        if(!(isActive() ^ true)) goto _L38; else goto _L37
_L37:
        Log.w("IInputConnectionWrapper", "getSelectedText on inactive InputConnection");
        ((IInputContextCallback) (obj3)).setSelectedText(null, i);
        ((SomeArgs) (obj)).recycle();
        return;
_L38:
        ((IInputContextCallback) (obj3)).setSelectedText(((InputConnection) (obj2)).getSelectedText(message.arg1), i);
        ((SomeArgs) (obj)).recycle();
_L40:
        return;
        message;
        Log.w("IInputConnectionWrapper", "Got RemoteException calling setSelectedText", message);
        ((SomeArgs) (obj)).recycle();
        if(true) goto _L40; else goto _L39
_L39:
        message;
        ((SomeArgs) (obj)).recycle();
        throw message;
_L5:
        obj = (SomeArgs)message.obj;
        obj3 = (IInputContextCallback)((SomeArgs) (obj)).arg6;
        i = ((SomeArgs) (obj)).argi6;
        obj2 = getInputConnection();
        if(obj2 == null) goto _L42; else goto _L41
_L41:
        if(!(isActive() ^ true)) goto _L43; else goto _L42
_L42:
        Log.w("IInputConnectionWrapper", "getCursorCapsMode on inactive InputConnection");
        ((IInputContextCallback) (obj3)).setCursorCapsMode(0, i);
        ((SomeArgs) (obj)).recycle();
        return;
_L43:
        ((IInputContextCallback) (obj3)).setCursorCapsMode(((InputConnection) (obj2)).getCursorCapsMode(message.arg1), i);
        ((SomeArgs) (obj)).recycle();
_L45:
        return;
        message;
        Log.w("IInputConnectionWrapper", "Got RemoteException calling setCursorCapsMode", message);
        ((SomeArgs) (obj)).recycle();
        if(true) goto _L45; else goto _L44
_L44:
        message;
        ((SomeArgs) (obj)).recycle();
        throw message;
_L6:
        obj = (SomeArgs)message.obj;
        obj2 = (IInputContextCallback)((SomeArgs) (obj)).arg6;
        i = ((SomeArgs) (obj)).argi6;
        obj3 = getInputConnection();
        if(obj3 == null) goto _L47; else goto _L46
_L46:
        if(!(isActive() ^ true)) goto _L48; else goto _L47
_L47:
        Log.w("IInputConnectionWrapper", "getExtractedText on inactive InputConnection");
        ((IInputContextCallback) (obj2)).setExtractedText(null, i);
        ((SomeArgs) (obj)).recycle();
        return;
_L48:
        ((IInputContextCallback) (obj2)).setExtractedText(((InputConnection) (obj3)).getExtractedText((ExtractedTextRequest)((SomeArgs) (obj)).arg1, message.arg1), i);
        ((SomeArgs) (obj)).recycle();
_L50:
        return;
        message;
        Log.w("IInputConnectionWrapper", "Got RemoteException calling setExtractedText", message);
        ((SomeArgs) (obj)).recycle();
        if(true) goto _L50; else goto _L49
_L49:
        message;
        ((SomeArgs) (obj)).recycle();
        throw message;
_L7:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "commitText on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).commitText((CharSequence)message.obj, message.arg1);
            onUserAction();
            return;
        }
_L10:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "setSelection on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).setSelection(message.arg1, message.arg2);
            return;
        }
_L11:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "performEditorAction on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).performEditorAction(message.arg1);
            return;
        }
_L12:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "performContextMenuAction on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).performContextMenuAction(message.arg1);
            return;
        }
_L8:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "commitCompletion on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).commitCompletion((CompletionInfo)message.obj);
            return;
        }
_L9:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "commitCorrection on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).commitCorrection((CorrectionInfo)message.obj);
            return;
        }
_L13:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "setComposingText on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).setComposingText((CharSequence)message.obj, message.arg1);
            onUserAction();
            return;
        }
_L14:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "setComposingRegion on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).setComposingRegion(message.arg1, message.arg2);
            return;
        }
_L15:
        if(isFinished())
            return;
        message = getInputConnection();
        if(message == null)
        {
            Log.w("IInputConnectionWrapper", "finishComposingText on inactive InputConnection");
            return;
        } else
        {
            message.finishComposingText();
            return;
        }
_L16:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "sendKeyEvent on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).sendKeyEvent((KeyEvent)message.obj);
            onUserAction();
            return;
        }
_L22:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "clearMetaKeyStates on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).clearMetaKeyStates(message.arg1);
            return;
        }
_L17:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "deleteSurroundingText on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).deleteSurroundingText(message.arg1, message.arg2);
            return;
        }
_L18:
        obj = getInputConnection();
        if(obj == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "deleteSurroundingTextInCodePoints on inactive InputConnection");
            return;
        } else
        {
            ((InputConnection) (obj)).deleteSurroundingTextInCodePoints(message.arg1, message.arg2);
            return;
        }
_L19:
        message = getInputConnection();
        if(message == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "beginBatchEdit on inactive InputConnection");
            return;
        } else
        {
            message.beginBatchEdit();
            return;
        }
_L20:
        message = getInputConnection();
        if(message == null || isActive() ^ true)
        {
            Log.w("IInputConnectionWrapper", "endBatchEdit on inactive InputConnection");
            return;
        } else
        {
            message.endBatchEdit();
            return;
        }
_L21:
        message = (SomeArgs)message.obj;
        obj3 = (String)((SomeArgs) (message)).arg1;
        obj2 = (Bundle)((SomeArgs) (message)).arg2;
        obj = getInputConnection();
        if(obj == null) goto _L52; else goto _L51
_L51:
        if(!(isActive() ^ true)) goto _L53; else goto _L52
_L52:
        Log.w("IInputConnectionWrapper", "performPrivateCommand on inactive InputConnection");
        message.recycle();
        return;
_L53:
        ((InputConnection) (obj)).performPrivateCommand(((String) (obj3)), ((Bundle) (obj2)));
        message.recycle();
        return;
        Object obj1;
        obj1;
        message.recycle();
        throw obj1;
_L23:
        obj1 = (SomeArgs)message.obj;
        obj3 = (IInputContextCallback)((SomeArgs) (obj1)).arg6;
        i = ((SomeArgs) (obj1)).argi6;
        obj2 = getInputConnection();
        if(obj2 == null) goto _L55; else goto _L54
_L54:
        if(!(isActive() ^ true)) goto _L56; else goto _L55
_L55:
        Log.w("IInputConnectionWrapper", "requestCursorAnchorInfo on inactive InputConnection");
        ((IInputContextCallback) (obj3)).setRequestUpdateCursorAnchorInfoResult(false, i);
        ((SomeArgs) (obj1)).recycle();
        return;
_L56:
        ((IInputContextCallback) (obj3)).setRequestUpdateCursorAnchorInfoResult(((InputConnection) (obj2)).requestCursorUpdates(message.arg1), i);
        ((SomeArgs) (obj1)).recycle();
_L58:
        return;
        message;
        Log.w("IInputConnectionWrapper", "Got RemoteException calling requestCursorAnchorInfo", message);
        ((SomeArgs) (obj1)).recycle();
        if(true) goto _L58; else goto _L57
_L57:
        message;
        ((SomeArgs) (obj1)).recycle();
        throw message;
_L24:
        if(isFinished())
            return;
        message = getInputConnection();
        if(message != null) goto _L60; else goto _L59
_L59:
        message = ((Message) (mLock));
        message;
        JVM INSTR monitorenter ;
        mInputConnection = null;
        mFinished = true;
        message;
        JVM INSTR monitorexit ;
        return;
        obj1;
        throw obj1;
_L60:
        if((InputConnectionInspector.getMissingMethodFlags(message) & 0x40) == 0)
            message.closeConnection();
        message = ((Message) (mLock));
        message;
        JVM INSTR monitorenter ;
        mInputConnection = null;
        mFinished = true;
        message;
        JVM INSTR monitorexit ;
        return;
        obj1;
        throw obj1;
        obj1;
        message = ((Message) (mLock));
        message;
        JVM INSTR monitorenter ;
        mInputConnection = null;
        mFinished = true;
        message;
        JVM INSTR monitorexit ;
        throw obj1;
        obj1;
        throw obj1;
_L25:
        i = message.arg1;
        message = (SomeArgs)message.obj;
        int j;
        obj1 = (IInputContextCallback)((SomeArgs) (message)).arg6;
        j = ((SomeArgs) (message)).argi6;
        obj3 = getInputConnection();
        if(obj3 == null) goto _L62; else goto _L61
_L61:
        if(!(isActive() ^ true)) goto _L63; else goto _L62
_L62:
        Log.w("IInputConnectionWrapper", "commitContent on inactive InputConnection");
        ((IInputContextCallback) (obj1)).setCommitContentResult(false, j);
        message.recycle();
        return;
_L63:
        obj2 = (InputContentInfo)((SomeArgs) (message)).arg1;
        if(obj2 == null) goto _L65; else goto _L64
_L64:
        if(!(((InputContentInfo) (obj2)).validate() ^ true)) goto _L66; else goto _L65
_L65:
        obj3 = JVM INSTR new #163 <Class StringBuilder>;
        ((StringBuilder) (obj3)).StringBuilder();
        Log.w("IInputConnectionWrapper", ((StringBuilder) (obj3)).append("commitContent with invalid inputContentInfo=").append(obj2).toString());
        ((IInputContextCallback) (obj1)).setCommitContentResult(false, j);
        message.recycle();
        return;
_L66:
        ((IInputContextCallback) (obj1)).setCommitContentResult(((InputConnection) (obj3)).commitContent(((InputContentInfo) (obj2)), i, (Bundle)((SomeArgs) (message)).arg2), j);
        message.recycle();
_L68:
        return;
        obj1;
        Log.w("IInputConnectionWrapper", "Got RemoteException calling commitContent", ((Throwable) (obj1)));
        message.recycle();
        if(true) goto _L68; else goto _L67
_L67:
        obj1;
        message.recycle();
        throw obj1;
    }

    public void finishComposingText()
    {
        dispatchMessage(obtainMessage(65));
    }

    public void getCursorCapsMode(int i, int j, IInputContextCallback iinputcontextcallback)
    {
        dispatchMessage(obtainMessageISC(30, i, j, iinputcontextcallback));
    }

    public void getExtractedText(ExtractedTextRequest extractedtextrequest, int i, int j, IInputContextCallback iinputcontextcallback)
    {
        dispatchMessage(obtainMessageIOSC(40, i, extractedtextrequest, j, iinputcontextcallback));
    }

    public InputConnection getInputConnection()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        InputConnection inputconnection = mInputConnection;
        obj;
        JVM INSTR monitorexit ;
        return inputconnection;
        Exception exception;
        exception;
        throw exception;
    }

    public void getSelectedText(int i, int j, IInputContextCallback iinputcontextcallback)
    {
        dispatchMessage(obtainMessageISC(25, i, j, iinputcontextcallback));
    }

    public void getTextAfterCursor(int i, int j, int k, IInputContextCallback iinputcontextcallback)
    {
        dispatchMessage(obtainMessageIISC(10, i, j, k, iinputcontextcallback));
    }

    public void getTextBeforeCursor(int i, int j, int k, IInputContextCallback iinputcontextcallback)
    {
        dispatchMessage(obtainMessageIISC(20, i, j, k, iinputcontextcallback));
    }

    protected abstract boolean isActive();

    protected boolean isFinished()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        boolean flag = mFinished;
        obj;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    Message obtainMessage(int i)
    {
        return mH.obtainMessage(i);
    }

    Message obtainMessageII(int i, int j, int k)
    {
        return mH.obtainMessage(i, j, k);
    }

    Message obtainMessageIISC(int i, int j, int k, int l, IInputContextCallback iinputcontextcallback)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg6 = iinputcontextcallback;
        someargs.argi6 = l;
        return mH.obtainMessage(i, j, k, someargs);
    }

    Message obtainMessageIO(int i, int j, Object obj)
    {
        return mH.obtainMessage(i, j, 0, obj);
    }

    Message obtainMessageIOOSC(int i, int j, Object obj, Object obj1, int k, IInputContextCallback iinputcontextcallback)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        someargs.arg6 = iinputcontextcallback;
        someargs.argi6 = k;
        return mH.obtainMessage(i, j, 0, someargs);
    }

    Message obtainMessageIOSC(int i, int j, Object obj, int k, IInputContextCallback iinputcontextcallback)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg6 = iinputcontextcallback;
        someargs.argi6 = k;
        return mH.obtainMessage(i, j, 0, someargs);
    }

    Message obtainMessageISC(int i, int j, int k, IInputContextCallback iinputcontextcallback)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg6 = iinputcontextcallback;
        someargs.argi6 = k;
        return mH.obtainMessage(i, j, 0, someargs);
    }

    Message obtainMessageO(int i, Object obj)
    {
        return mH.obtainMessage(i, 0, 0, obj);
    }

    Message obtainMessageOO(int i, Object obj, Object obj1)
    {
        SomeArgs someargs = SomeArgs.obtain();
        someargs.arg1 = obj;
        someargs.arg2 = obj1;
        return mH.obtainMessage(i, 0, 0, someargs);
    }

    protected abstract void onUserAction();

    public void performContextMenuAction(int i)
    {
        dispatchMessage(obtainMessageII(59, i, 0));
    }

    public void performEditorAction(int i)
    {
        dispatchMessage(obtainMessageII(58, i, 0));
    }

    public void performPrivateCommand(String s, Bundle bundle)
    {
        dispatchMessage(obtainMessageOO(120, s, bundle));
    }

    public void requestUpdateCursorAnchorInfo(int i, int j, IInputContextCallback iinputcontextcallback)
    {
        dispatchMessage(obtainMessageISC(140, i, j, iinputcontextcallback));
    }

    public void sendKeyEvent(KeyEvent keyevent)
    {
        dispatchMessage(obtainMessageO(70, keyevent));
    }

    public void setComposingRegion(int i, int j)
    {
        dispatchMessage(obtainMessageII(63, i, j));
    }

    public void setComposingText(CharSequence charsequence, int i)
    {
        dispatchMessage(obtainMessageIO(60, i, charsequence));
    }

    public void setSelection(int i, int j)
    {
        dispatchMessage(obtainMessageII(57, i, j));
    }

    private static final boolean DEBUG = false;
    private static final int DO_BEGIN_BATCH_EDIT = 90;
    private static final int DO_CLEAR_META_KEY_STATES = 130;
    private static final int DO_CLOSE_CONNECTION = 150;
    private static final int DO_COMMIT_COMPLETION = 55;
    private static final int DO_COMMIT_CONTENT = 160;
    private static final int DO_COMMIT_CORRECTION = 56;
    private static final int DO_COMMIT_TEXT = 50;
    private static final int DO_DELETE_SURROUNDING_TEXT = 80;
    private static final int DO_DELETE_SURROUNDING_TEXT_IN_CODE_POINTS = 81;
    private static final int DO_END_BATCH_EDIT = 95;
    private static final int DO_FINISH_COMPOSING_TEXT = 65;
    private static final int DO_GET_CURSOR_CAPS_MODE = 30;
    private static final int DO_GET_EXTRACTED_TEXT = 40;
    private static final int DO_GET_SELECTED_TEXT = 25;
    private static final int DO_GET_TEXT_AFTER_CURSOR = 10;
    private static final int DO_GET_TEXT_BEFORE_CURSOR = 20;
    private static final int DO_PERFORM_CONTEXT_MENU_ACTION = 59;
    private static final int DO_PERFORM_EDITOR_ACTION = 58;
    private static final int DO_PERFORM_PRIVATE_COMMAND = 120;
    private static final int DO_REQUEST_UPDATE_CURSOR_ANCHOR_INFO = 140;
    private static final int DO_SEND_KEY_EVENT = 70;
    private static final int DO_SET_COMPOSING_REGION = 63;
    private static final int DO_SET_COMPOSING_TEXT = 60;
    private static final int DO_SET_SELECTION = 57;
    private static final String TAG = "IInputConnectionWrapper";
    private boolean mFinished;
    private Handler mH;
    private InputConnection mInputConnection;
    private Object mLock;
    private Looper mMainLooper;
}
