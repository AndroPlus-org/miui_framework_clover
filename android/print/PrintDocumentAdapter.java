// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.os.*;

// Referenced classes of package android.print:
//            PrintAttributes, PageRange, PrintDocumentInfo

public abstract class PrintDocumentAdapter
{
    public static abstract class LayoutResultCallback
    {

        public void onLayoutCancelled()
        {
        }

        public void onLayoutFailed(CharSequence charsequence)
        {
        }

        public void onLayoutFinished(PrintDocumentInfo printdocumentinfo, boolean flag)
        {
        }

        public LayoutResultCallback()
        {
        }
    }

    public static abstract class WriteResultCallback
    {

        public void onWriteCancelled()
        {
        }

        public void onWriteFailed(CharSequence charsequence)
        {
        }

        public void onWriteFinished(PageRange apagerange[])
        {
        }

        public WriteResultCallback()
        {
        }
    }


    public PrintDocumentAdapter()
    {
    }

    public void onFinish()
    {
    }

    public abstract void onLayout(PrintAttributes printattributes, PrintAttributes printattributes1, CancellationSignal cancellationsignal, LayoutResultCallback layoutresultcallback, Bundle bundle);

    public void onStart()
    {
    }

    public abstract void onWrite(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, WriteResultCallback writeresultcallback);

    public static final String EXTRA_PRINT_PREVIEW = "EXTRA_PRINT_PREVIEW";
}
