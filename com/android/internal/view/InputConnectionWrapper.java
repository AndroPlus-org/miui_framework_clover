// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.view;

import android.inputmethodservice.AbstractInputMethodService;
import android.os.*;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.*;
import java.lang.ref.WeakReference;

// Referenced classes of package com.android.internal.view:
//            IInputContext

public class InputConnectionWrapper
    implements InputConnection
{
    static class InputContextCallback extends IInputContextCallback.Stub
    {

        static InputContextCallback _2D_wrap0()
        {
            return getInstance();
        }

        static void _2D_wrap1(InputContextCallback inputcontextcallback)
        {
            inputcontextcallback.dispose();
        }

        private void dispose()
        {
            com/android/internal/view/InputConnectionWrapper$InputContextCallback;
            JVM INSTR monitorenter ;
            if(sInstance == null)
            {
                mTextAfterCursor = null;
                mTextBeforeCursor = null;
                mExtractedText = null;
                sInstance = this;
            }
            com/android/internal/view/InputConnectionWrapper$InputContextCallback;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        private static InputContextCallback getInstance()
        {
            com/android/internal/view/InputConnectionWrapper$InputContextCallback;
            JVM INSTR monitorenter ;
            InputContextCallback inputcontextcallback;
            if(sInstance == null)
                break MISSING_BLOCK_LABEL_42;
            inputcontextcallback = sInstance;
            sInstance = null;
            inputcontextcallback.mHaveValue = false;
_L1:
            int i = sSequenceNumber;
            sSequenceNumber = i + 1;
            inputcontextcallback.mSeq = i;
            com/android/internal/view/InputConnectionWrapper$InputContextCallback;
            JVM INSTR monitorexit ;
            return inputcontextcallback;
            inputcontextcallback = new InputContextCallback();
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        public void setCommitContentResult(boolean flag, int i)
        {
            this;
            JVM INSTR monitorenter ;
            if(i != mSeq)
                break MISSING_BLOCK_LABEL_27;
            mCommitContentResult = flag;
            mHaveValue = true;
            notifyAll();
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            StringBuilder stringbuilder = JVM INSTR new #67  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("InputConnectionWrapper.ICC", stringbuilder.append("Got out-of-sequence callback ").append(i).append(" (expected ").append(mSeq).append(") in setCommitContentResult, ignoring.").toString());
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        public void setCursorCapsMode(int i, int j)
        {
            this;
            JVM INSTR monitorenter ;
            if(j != mSeq)
                break MISSING_BLOCK_LABEL_27;
            mCursorCapsMode = i;
            mHaveValue = true;
            notifyAll();
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            StringBuilder stringbuilder = JVM INSTR new #67  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("InputConnectionWrapper.ICC", stringbuilder.append("Got out-of-sequence callback ").append(j).append(" (expected ").append(mSeq).append(") in setCursorCapsMode, ignoring.").toString());
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        public void setExtractedText(ExtractedText extractedtext, int i)
        {
            this;
            JVM INSTR monitorenter ;
            if(i != mSeq)
                break MISSING_BLOCK_LABEL_27;
            mExtractedText = extractedtext;
            mHaveValue = true;
            notifyAll();
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            extractedtext = JVM INSTR new #67  <Class StringBuilder>;
            extractedtext.StringBuilder();
            Log.i("InputConnectionWrapper.ICC", extractedtext.append("Got out-of-sequence callback ").append(i).append(" (expected ").append(mSeq).append(") in setExtractedText, ignoring.").toString());
              goto _L1
            extractedtext;
            throw extractedtext;
        }

        public void setRequestUpdateCursorAnchorInfoResult(boolean flag, int i)
        {
            this;
            JVM INSTR monitorenter ;
            if(i != mSeq)
                break MISSING_BLOCK_LABEL_27;
            mRequestUpdateCursorAnchorInfoResult = flag;
            mHaveValue = true;
            notifyAll();
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            StringBuilder stringbuilder = JVM INSTR new #67  <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.i("InputConnectionWrapper.ICC", stringbuilder.append("Got out-of-sequence callback ").append(i).append(" (expected ").append(mSeq).append(") in setCursorAnchorInfoRequestResult, ignoring.").toString());
              goto _L1
            Exception exception;
            exception;
            throw exception;
        }

        public void setSelectedText(CharSequence charsequence, int i)
        {
            this;
            JVM INSTR monitorenter ;
            if(i != mSeq)
                break MISSING_BLOCK_LABEL_27;
            mSelectedText = charsequence;
            mHaveValue = true;
            notifyAll();
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            charsequence = JVM INSTR new #67  <Class StringBuilder>;
            charsequence.StringBuilder();
            Log.i("InputConnectionWrapper.ICC", charsequence.append("Got out-of-sequence callback ").append(i).append(" (expected ").append(mSeq).append(") in setSelectedText, ignoring.").toString());
              goto _L1
            charsequence;
            throw charsequence;
        }

        public void setTextAfterCursor(CharSequence charsequence, int i)
        {
            this;
            JVM INSTR monitorenter ;
            if(i != mSeq)
                break MISSING_BLOCK_LABEL_27;
            mTextAfterCursor = charsequence;
            mHaveValue = true;
            notifyAll();
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            charsequence = JVM INSTR new #67  <Class StringBuilder>;
            charsequence.StringBuilder();
            Log.i("InputConnectionWrapper.ICC", charsequence.append("Got out-of-sequence callback ").append(i).append(" (expected ").append(mSeq).append(") in setTextAfterCursor, ignoring.").toString());
              goto _L1
            charsequence;
            throw charsequence;
        }

        public void setTextBeforeCursor(CharSequence charsequence, int i)
        {
            this;
            JVM INSTR monitorenter ;
            if(i != mSeq)
                break MISSING_BLOCK_LABEL_27;
            mTextBeforeCursor = charsequence;
            mHaveValue = true;
            notifyAll();
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            charsequence = JVM INSTR new #67  <Class StringBuilder>;
            charsequence.StringBuilder();
            Log.i("InputConnectionWrapper.ICC", charsequence.append("Got out-of-sequence callback ").append(i).append(" (expected ").append(mSeq).append(") in setTextBeforeCursor, ignoring.").toString());
              goto _L1
            charsequence;
            throw charsequence;
        }

        void waitForResultLocked()
        {
            long l = SystemClock.uptimeMillis();
            while(!mHaveValue) 
            {
                long l1 = (l + 2000L) - SystemClock.uptimeMillis();
                if(l1 <= 0L)
                {
                    Log.w("InputConnectionWrapper.ICC", "Timed out waiting on IInputContextCallback");
                    return;
                }
                try
                {
                    wait(l1);
                }
                catch(InterruptedException interruptedexception) { }
            }
        }

        private static final String TAG = "InputConnectionWrapper.ICC";
        private static InputContextCallback sInstance = new InputContextCallback();
        private static int sSequenceNumber = 1;
        public boolean mCommitContentResult;
        public int mCursorCapsMode;
        public ExtractedText mExtractedText;
        public boolean mHaveValue;
        public boolean mRequestUpdateCursorAnchorInfoResult;
        public CharSequence mSelectedText;
        public int mSeq;
        public CharSequence mTextAfterCursor;
        public CharSequence mTextBeforeCursor;


        InputContextCallback()
        {
        }
    }


    public InputConnectionWrapper(WeakReference weakreference, IInputContext iinputcontext, int i)
    {
        mInputMethodService = weakreference;
        mIInputContext = iinputcontext;
        mMissingMethods = i;
    }

    private boolean isMethodMissing(int i)
    {
        boolean flag;
        if((mMissingMethods & i) == i)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean beginBatchEdit()
    {
        try
        {
            mIInputContext.beginBatchEdit();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean clearMetaKeyStates(int i)
    {
        try
        {
            mIInputContext.clearMetaKeyStates(i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public void closeConnection()
    {
    }

    public boolean commitCompletion(CompletionInfo completioninfo)
    {
        if(isMethodMissing(4))
            return false;
        try
        {
            mIInputContext.commitCompletion(completioninfo);
        }
        // Misplaced declaration of an exception variable
        catch(CompletionInfo completioninfo)
        {
            return false;
        }
        return true;
    }

    public boolean commitContent(InputContentInfo inputcontentinfo, int i, Bundle bundle)
    {
        boolean flag = false;
        if(isMethodMissing(128))
            return false;
        if((i & 1) == 0)
            break MISSING_BLOCK_LABEL_47;
        Object obj;
        try
        {
            obj = (AbstractInputMethodService)mInputMethodService.get();
        }
        // Misplaced declaration of an exception variable
        catch(InputContentInfo inputcontentinfo)
        {
            return false;
        }
        if(obj == null)
            return false;
        ((AbstractInputMethodService) (obj)).exposeContent(inputcontentinfo, this);
        obj = InputContextCallback._2D_wrap0();
        mIInputContext.commitContent(inputcontentinfo, i, bundle, ((InputContextCallback) (obj)).mSeq, ((IInputContextCallback) (obj)));
        obj;
        JVM INSTR monitorenter ;
        ((InputContextCallback) (obj)).waitForResultLocked();
        if(((InputContextCallback) (obj)).mHaveValue)
            flag = ((InputContextCallback) (obj)).mCommitContentResult;
        obj;
        JVM INSTR monitorexit ;
        InputContextCallback._2D_wrap1(((InputContextCallback) (obj)));
        return flag;
        inputcontentinfo;
        obj;
        JVM INSTR monitorexit ;
        throw inputcontentinfo;
    }

    public boolean commitCorrection(CorrectionInfo correctioninfo)
    {
        try
        {
            mIInputContext.commitCorrection(correctioninfo);
        }
        // Misplaced declaration of an exception variable
        catch(CorrectionInfo correctioninfo)
        {
            return false;
        }
        return true;
    }

    public boolean commitText(CharSequence charsequence, int i)
    {
        try
        {
            mIInputContext.commitText(charsequence, i);
        }
        // Misplaced declaration of an exception variable
        catch(CharSequence charsequence)
        {
            return false;
        }
        return true;
    }

    public boolean deleteSurroundingText(int i, int j)
    {
        try
        {
            mIInputContext.deleteSurroundingText(i, j);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean deleteSurroundingTextInCodePoints(int i, int j)
    {
        if(isMethodMissing(16))
            return false;
        try
        {
            mIInputContext.deleteSurroundingTextInCodePoints(i, j);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean endBatchEdit()
    {
        try
        {
            mIInputContext.endBatchEdit();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean finishComposingText()
    {
        try
        {
            mIInputContext.finishComposingText();
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public int getCursorCapsMode(int i)
    {
        boolean flag = false;
        InputContextCallback inputcontextcallback;
        inputcontextcallback = InputContextCallback._2D_wrap0();
        mIInputContext.getCursorCapsMode(i, inputcontextcallback.mSeq, inputcontextcallback);
        inputcontextcallback;
        JVM INSTR monitorenter ;
        inputcontextcallback.waitForResultLocked();
        i = ((flag) ? 1 : 0);
        if(inputcontextcallback.mHaveValue)
            i = inputcontextcallback.mCursorCapsMode;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        InputContextCallback._2D_wrap1(inputcontextcallback);
        return i;
        Object obj;
        obj;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        return 0;
    }

    public ExtractedText getExtractedText(ExtractedTextRequest extractedtextrequest, int i)
    {
        Object obj = null;
        InputContextCallback inputcontextcallback;
        inputcontextcallback = InputContextCallback._2D_wrap0();
        mIInputContext.getExtractedText(extractedtextrequest, i, inputcontextcallback.mSeq, inputcontextcallback);
        inputcontextcallback;
        JVM INSTR monitorenter ;
        inputcontextcallback.waitForResultLocked();
        extractedtextrequest = obj;
        if(inputcontextcallback.mHaveValue)
            extractedtextrequest = inputcontextcallback.mExtractedText;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        InputContextCallback._2D_wrap1(inputcontextcallback);
        return extractedtextrequest;
        extractedtextrequest;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        throw extractedtextrequest;
        extractedtextrequest;
        return null;
    }

    public Handler getHandler()
    {
        return null;
    }

    public CharSequence getSelectedText(int i)
    {
        CharSequence charsequence;
        if(isMethodMissing(1))
            return null;
        charsequence = null;
        InputContextCallback inputcontextcallback;
        inputcontextcallback = InputContextCallback._2D_wrap0();
        mIInputContext.getSelectedText(i, inputcontextcallback.mSeq, inputcontextcallback);
        inputcontextcallback;
        JVM INSTR monitorenter ;
        inputcontextcallback.waitForResultLocked();
        if(inputcontextcallback.mHaveValue)
            charsequence = inputcontextcallback.mSelectedText;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        InputContextCallback._2D_wrap1(inputcontextcallback);
        return charsequence;
        Object obj;
        obj;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        return null;
    }

    public CharSequence getTextAfterCursor(int i, int j)
    {
        CharSequence charsequence = null;
        InputContextCallback inputcontextcallback;
        inputcontextcallback = InputContextCallback._2D_wrap0();
        mIInputContext.getTextAfterCursor(i, j, inputcontextcallback.mSeq, inputcontextcallback);
        inputcontextcallback;
        JVM INSTR monitorenter ;
        inputcontextcallback.waitForResultLocked();
        if(inputcontextcallback.mHaveValue)
            charsequence = inputcontextcallback.mTextAfterCursor;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        InputContextCallback._2D_wrap1(inputcontextcallback);
        return charsequence;
        Object obj;
        obj;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        return null;
    }

    public CharSequence getTextBeforeCursor(int i, int j)
    {
        CharSequence charsequence = null;
        InputContextCallback inputcontextcallback;
        inputcontextcallback = InputContextCallback._2D_wrap0();
        mIInputContext.getTextBeforeCursor(i, j, inputcontextcallback.mSeq, inputcontextcallback);
        inputcontextcallback;
        JVM INSTR monitorenter ;
        inputcontextcallback.waitForResultLocked();
        if(inputcontextcallback.mHaveValue)
            charsequence = inputcontextcallback.mTextBeforeCursor;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        InputContextCallback._2D_wrap1(inputcontextcallback);
        return charsequence;
        Object obj;
        obj;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        return null;
    }

    public boolean performContextMenuAction(int i)
    {
        try
        {
            mIInputContext.performContextMenuAction(i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean performEditorAction(int i)
    {
        try
        {
            mIInputContext.performEditorAction(i);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean performPrivateCommand(String s, Bundle bundle)
    {
        try
        {
            mIInputContext.performPrivateCommand(s, bundle);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            return false;
        }
        return true;
    }

    public boolean reportFullscreenMode(boolean flag)
    {
        return false;
    }

    public boolean requestCursorUpdates(int i)
    {
        boolean flag;
        flag = false;
        if(isMethodMissing(8))
            return false;
        InputContextCallback inputcontextcallback;
        inputcontextcallback = InputContextCallback._2D_wrap0();
        mIInputContext.requestUpdateCursorAnchorInfo(i, inputcontextcallback.mSeq, inputcontextcallback);
        inputcontextcallback;
        JVM INSTR monitorenter ;
        inputcontextcallback.waitForResultLocked();
        if(inputcontextcallback.mHaveValue)
            flag = inputcontextcallback.mRequestUpdateCursorAnchorInfoResult;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        InputContextCallback._2D_wrap1(inputcontextcallback);
        return flag;
        Exception exception;
        exception;
        inputcontextcallback;
        JVM INSTR monitorexit ;
        throw exception;
        RemoteException remoteexception;
        remoteexception;
        return false;
    }

    public boolean sendKeyEvent(KeyEvent keyevent)
    {
        try
        {
            mIInputContext.sendKeyEvent(keyevent);
        }
        // Misplaced declaration of an exception variable
        catch(KeyEvent keyevent)
        {
            return false;
        }
        return true;
    }

    public boolean setComposingRegion(int i, int j)
    {
        if(isMethodMissing(2))
            return false;
        try
        {
            mIInputContext.setComposingRegion(i, j);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public boolean setComposingText(CharSequence charsequence, int i)
    {
        try
        {
            mIInputContext.setComposingText(charsequence, i);
        }
        // Misplaced declaration of an exception variable
        catch(CharSequence charsequence)
        {
            return false;
        }
        return true;
    }

    public boolean setSelection(int i, int j)
    {
        try
        {
            mIInputContext.setSelection(i, j);
        }
        catch(RemoteException remoteexception)
        {
            return false;
        }
        return true;
    }

    public String toString()
    {
        return (new StringBuilder()).append("InputConnectionWrapper{idHash=#").append(Integer.toHexString(System.identityHashCode(this))).append(" mMissingMethods=").append(InputConnectionInspector.getMissingMethodFlagsAsString(mMissingMethods)).append("}").toString();
    }

    private static final int MAX_WAIT_TIME_MILLIS = 2000;
    private final IInputContext mIInputContext;
    private final WeakReference mInputMethodService;
    private final int mMissingMethods;
}
