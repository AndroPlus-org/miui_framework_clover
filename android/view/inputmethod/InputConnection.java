// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.inputmethod;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

// Referenced classes of package android.view.inputmethod:
//            CompletionInfo, InputContentInfo, CorrectionInfo, ExtractedTextRequest, 
//            ExtractedText

public interface InputConnection
{

    public abstract boolean beginBatchEdit();

    public abstract boolean clearMetaKeyStates(int i);

    public abstract void closeConnection();

    public abstract boolean commitCompletion(CompletionInfo completioninfo);

    public abstract boolean commitContent(InputContentInfo inputcontentinfo, int i, Bundle bundle);

    public abstract boolean commitCorrection(CorrectionInfo correctioninfo);

    public abstract boolean commitText(CharSequence charsequence, int i);

    public abstract boolean deleteSurroundingText(int i, int j);

    public abstract boolean deleteSurroundingTextInCodePoints(int i, int j);

    public abstract boolean endBatchEdit();

    public abstract boolean finishComposingText();

    public abstract int getCursorCapsMode(int i);

    public abstract ExtractedText getExtractedText(ExtractedTextRequest extractedtextrequest, int i);

    public abstract Handler getHandler();

    public abstract CharSequence getSelectedText(int i);

    public abstract CharSequence getTextAfterCursor(int i, int j);

    public abstract CharSequence getTextBeforeCursor(int i, int j);

    public abstract boolean performContextMenuAction(int i);

    public abstract boolean performEditorAction(int i);

    public abstract boolean performPrivateCommand(String s, Bundle bundle);

    public abstract boolean reportFullscreenMode(boolean flag);

    public abstract boolean requestCursorUpdates(int i);

    public abstract boolean sendKeyEvent(KeyEvent keyevent);

    public abstract boolean setComposingRegion(int i, int j);

    public abstract boolean setComposingText(CharSequence charsequence, int i);

    public abstract boolean setSelection(int i, int j);

    public static final int CURSOR_UPDATE_IMMEDIATE = 1;
    public static final int CURSOR_UPDATE_MONITOR = 2;
    public static final int GET_EXTRACTED_TEXT_MONITOR = 1;
    public static final int GET_TEXT_WITH_STYLES = 1;
    public static final int INPUT_CONTENT_GRANT_READ_URI_PERMISSION = 1;
}
