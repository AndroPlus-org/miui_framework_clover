// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.android.internal.widget;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.method.KeyListener;
import android.text.style.SuggestionSpan;
import android.view.inputmethod.*;
import android.widget.TextView;

public class EditableInputConnection extends BaseInputConnection
{

    public EditableInputConnection(TextView textview)
    {
        super(textview, true);
        mTextView = textview;
    }

    public boolean beginBatchEdit()
    {
        this;
        JVM INSTR monitorenter ;
        if(mBatchEditNesting < 0)
            break MISSING_BLOCK_LABEL_30;
        mTextView.beginBatchEdit();
        mBatchEditNesting = mBatchEditNesting + 1;
        return true;
        this;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean clearMetaKeyStates(int i)
    {
        Editable editable = getEditable();
        if(editable == null)
            return false;
        KeyListener keylistener = mTextView.getKeyListener();
        if(keylistener != null)
            try
            {
                keylistener.clearMetaKeyState(mTextView, editable, i);
            }
            catch(AbstractMethodError abstractmethoderror) { }
        return true;
    }

    public void closeConnection()
    {
        super.closeConnection();
        this;
        JVM INSTR monitorenter ;
        while(mBatchEditNesting > 0) 
            endBatchEdit();
        break MISSING_BLOCK_LABEL_26;
        Exception exception;
        exception;
        throw exception;
        mBatchEditNesting = -1;
        this;
        JVM INSTR monitorexit ;
    }

    public boolean commitCompletion(CompletionInfo completioninfo)
    {
        mTextView.beginBatchEdit();
        mTextView.onCommitCompletion(completioninfo);
        mTextView.endBatchEdit();
        return true;
    }

    public boolean commitCorrection(CorrectionInfo correctioninfo)
    {
        mTextView.beginBatchEdit();
        mTextView.onCommitCorrection(correctioninfo);
        mTextView.endBatchEdit();
        return true;
    }

    public boolean commitText(CharSequence charsequence, int i)
    {
        if(mTextView == null)
            return super.commitText(charsequence, i);
        if(charsequence instanceof Spanned)
        {
            SuggestionSpan asuggestionspan[] = (SuggestionSpan[])((Spanned)charsequence).getSpans(0, charsequence.length(), android/text/style/SuggestionSpan);
            mIMM.registerSuggestionSpansForNotification(asuggestionspan);
        }
        mTextView.resetErrorChangedFlag();
        boolean flag = super.commitText(charsequence, i);
        mTextView.hideErrorIfUnchanged();
        return flag;
    }

    public boolean endBatchEdit()
    {
        this;
        JVM INSTR monitorenter ;
        if(mBatchEditNesting <= 0)
            break MISSING_BLOCK_LABEL_30;
        mTextView.endBatchEdit();
        mBatchEditNesting = mBatchEditNesting - 1;
        return true;
        this;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        throw exception;
    }

    public Editable getEditable()
    {
        TextView textview = mTextView;
        if(textview != null)
            return textview.getEditableText();
        else
            return null;
    }

    public ExtractedText getExtractedText(ExtractedTextRequest extractedtextrequest, int i)
    {
        if(mTextView != null)
        {
            ExtractedText extractedtext = new ExtractedText();
            if(mTextView.extractText(extractedtextrequest, extractedtext))
            {
                if((i & 1) != 0)
                    mTextView.setExtracting(extractedtextrequest);
                return extractedtext;
            }
        }
        return null;
    }

    public boolean performContextMenuAction(int i)
    {
        mTextView.beginBatchEdit();
        mTextView.onTextContextMenuItem(i);
        mTextView.endBatchEdit();
        return true;
    }

    public boolean performEditorAction(int i)
    {
        mTextView.onEditorAction(i);
        return true;
    }

    public boolean performPrivateCommand(String s, Bundle bundle)
    {
        mTextView.onPrivateIMECommand(s, bundle);
        return true;
    }

    public boolean requestCursorUpdates(int i)
    {
        if((i & -4) != 0)
            return false;
        if(mIMM == null)
            return false;
        mIMM.setUpdateCursorAnchorInfoMode(i);
        break MISSING_BLOCK_LABEL_26;
        if((i & 1) != 0 && mTextView != null && !mTextView.isInLayout())
            mTextView.requestLayout();
        return true;
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "EditableInputConnection";
    private int mBatchEditNesting;
    private final TextView mTextView;
}
