// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

// Referenced classes of package android.view.inputmethod:
//            InputConnection, InputConnectionInspector, CompletionInfo, InputContentInfo, 
//            CorrectionInfo, ExtractedTextRequest, ExtractedText

public class InputConnectionWrapper
    implements InputConnection
{

    public InputConnectionWrapper(InputConnection inputconnection, boolean flag)
    {
        mMutable = flag;
        mTarget = inputconnection;
        mMissingMethodFlags = InputConnectionInspector.getMissingMethodFlags(inputconnection);
    }

    public boolean beginBatchEdit()
    {
        return mTarget.beginBatchEdit();
    }

    public boolean clearMetaKeyStates(int i)
    {
        return mTarget.clearMetaKeyStates(i);
    }

    public void closeConnection()
    {
        mTarget.closeConnection();
    }

    public boolean commitCompletion(CompletionInfo completioninfo)
    {
        return mTarget.commitCompletion(completioninfo);
    }

    public boolean commitContent(InputContentInfo inputcontentinfo, int i, Bundle bundle)
    {
        return mTarget.commitContent(inputcontentinfo, i, bundle);
    }

    public boolean commitCorrection(CorrectionInfo correctioninfo)
    {
        return mTarget.commitCorrection(correctioninfo);
    }

    public boolean commitText(CharSequence charsequence, int i)
    {
        return mTarget.commitText(charsequence, i);
    }

    public boolean deleteSurroundingText(int i, int j)
    {
        return mTarget.deleteSurroundingText(i, j);
    }

    public boolean deleteSurroundingTextInCodePoints(int i, int j)
    {
        return mTarget.deleteSurroundingTextInCodePoints(i, j);
    }

    public boolean endBatchEdit()
    {
        return mTarget.endBatchEdit();
    }

    public boolean finishComposingText()
    {
        return mTarget.finishComposingText();
    }

    public int getCursorCapsMode(int i)
    {
        return mTarget.getCursorCapsMode(i);
    }

    public ExtractedText getExtractedText(ExtractedTextRequest extractedtextrequest, int i)
    {
        return mTarget.getExtractedText(extractedtextrequest, i);
    }

    public Handler getHandler()
    {
        return mTarget.getHandler();
    }

    public int getMissingMethodFlags()
    {
        return mMissingMethodFlags;
    }

    public CharSequence getSelectedText(int i)
    {
        return mTarget.getSelectedText(i);
    }

    public CharSequence getTextAfterCursor(int i, int j)
    {
        return mTarget.getTextAfterCursor(i, j);
    }

    public CharSequence getTextBeforeCursor(int i, int j)
    {
        return mTarget.getTextBeforeCursor(i, j);
    }

    public boolean performContextMenuAction(int i)
    {
        return mTarget.performContextMenuAction(i);
    }

    public boolean performEditorAction(int i)
    {
        return mTarget.performEditorAction(i);
    }

    public boolean performPrivateCommand(String s, Bundle bundle)
    {
        return mTarget.performPrivateCommand(s, bundle);
    }

    public boolean reportFullscreenMode(boolean flag)
    {
        return mTarget.reportFullscreenMode(flag);
    }

    public boolean requestCursorUpdates(int i)
    {
        return mTarget.requestCursorUpdates(i);
    }

    public boolean sendKeyEvent(KeyEvent keyevent)
    {
        return mTarget.sendKeyEvent(keyevent);
    }

    public boolean setComposingRegion(int i, int j)
    {
        return mTarget.setComposingRegion(i, j);
    }

    public boolean setComposingText(CharSequence charsequence, int i)
    {
        return mTarget.setComposingText(charsequence, i);
    }

    public boolean setSelection(int i, int j)
    {
        return mTarget.setSelection(i, j);
    }

    public void setTarget(InputConnection inputconnection)
    {
        if(mTarget != null && mMutable ^ true)
        {
            throw new SecurityException("not mutable");
        } else
        {
            mTarget = inputconnection;
            mMissingMethodFlags = InputConnectionInspector.getMissingMethodFlags(inputconnection);
            return;
        }
    }

    private int mMissingMethodFlags;
    final boolean mMutable;
    private InputConnection mTarget;
}
