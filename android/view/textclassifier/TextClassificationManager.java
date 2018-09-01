// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.view.textclassifier;

import android.content.Context;
import com.android.internal.util.Preconditions;

// Referenced classes of package android.view.textclassifier:
//            TextClassifierImpl, TextClassifier

public final class TextClassificationManager
{

    public TextClassificationManager(Context context)
    {
        mContext = (Context)Preconditions.checkNotNull(context);
    }

    public TextClassifier getTextClassifier()
    {
        Object obj = mTextClassifierLock;
        obj;
        JVM INSTR monitorenter ;
        TextClassifier textclassifier;
        if(mTextClassifier == null)
        {
            TextClassifierImpl textclassifierimpl = JVM INSTR new #34  <Class TextClassifierImpl>;
            textclassifierimpl.TextClassifierImpl(mContext);
            mTextClassifier = textclassifierimpl;
        }
        textclassifier = mTextClassifier;
        obj;
        JVM INSTR monitorexit ;
        return textclassifier;
        Exception exception;
        exception;
        throw exception;
    }

    public void setTextClassifier(TextClassifier textclassifier)
    {
        Object obj = mTextClassifierLock;
        obj;
        JVM INSTR monitorenter ;
        mTextClassifier = textclassifier;
        obj;
        JVM INSTR monitorexit ;
        return;
        textclassifier;
        throw textclassifier;
    }

    private final Context mContext;
    private TextClassifier mTextClassifier;
    private final Object mTextClassifierLock = new Object();
}
