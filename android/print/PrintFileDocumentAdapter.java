// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.content.Context;
import android.os.*;
import android.util.Log;
import java.io.*;
import libcore.io.IoUtils;

// Referenced classes of package android.print:
//            PrintDocumentAdapter, PrintDocumentInfo, PrintAttributes, PageRange

public class PrintFileDocumentAdapter extends PrintDocumentAdapter
{
    private final class WriteFileAsyncTask extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected transient Void doInBackground(Void avoid[])
        {
            Object obj;
            Object obj2;
            FileOutputStream fileoutputstream;
            byte abyte0[];
            obj = null;
            obj2 = null;
            fileoutputstream = new FileOutputStream(mDestination.getFileDescriptor());
            abyte0 = new byte[8192];
            avoid = obj;
            Object obj3 = JVM INSTR new #63  <Class FileInputStream>;
            avoid = obj;
            ((FileInputStream) (obj3)).FileInputStream(PrintFileDocumentAdapter._2D_get1(PrintFileDocumentAdapter.this));
_L4:
            boolean flag = isCancelled();
            if(!flag) goto _L2; else goto _L1
_L1:
            IoUtils.closeQuietly(((AutoCloseable) (obj3)));
            IoUtils.closeQuietly(fileoutputstream);
_L5:
            int i;
            return null;
_L2:
            if((i = ((InputStream) (obj3)).read(abyte0)) < 0) goto _L1; else goto _L3
_L3:
            fileoutputstream.write(abyte0, 0, i);
              goto _L4
            Object obj1;
            obj1;
_L8:
            avoid = ((Void []) (obj3));
            Log.e("PrintedFileDocAdapter", "Error writing data!", ((Throwable) (obj1)));
            avoid = ((Void []) (obj3));
            mResultCallback.onWriteFailed(PrintFileDocumentAdapter._2D_get0(PrintFileDocumentAdapter.this).getString(0x10406df));
            IoUtils.closeQuietly(((AutoCloseable) (obj3)));
            IoUtils.closeQuietly(fileoutputstream);
              goto _L5
            obj1;
            obj3 = avoid;
_L7:
            IoUtils.closeQuietly(((AutoCloseable) (obj3)));
            IoUtils.closeQuietly(fileoutputstream);
            throw obj1;
            avoid;
            obj1 = avoid;
            if(true) goto _L7; else goto _L6
_L6:
            obj1;
            obj3 = obj2;
              goto _L8
        }

        protected volatile void onCancelled(Object obj)
        {
            onCancelled((Void)obj);
        }

        protected void onCancelled(Void void1)
        {
            mResultCallback.onWriteFailed(PrintFileDocumentAdapter._2D_get0(PrintFileDocumentAdapter.this).getString(0x10406de));
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Void)obj);
        }

        protected void onPostExecute(Void void1)
        {
            mResultCallback.onWriteFinished(new PageRange[] {
                PageRange.ALL_PAGES
            });
        }

        private final CancellationSignal mCancellationSignal;
        private final ParcelFileDescriptor mDestination;
        private final PrintDocumentAdapter.WriteResultCallback mResultCallback;
        final PrintFileDocumentAdapter this$0;

        public WriteFileAsyncTask(ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, PrintDocumentAdapter.WriteResultCallback writeresultcallback)
        {
            this$0 = PrintFileDocumentAdapter.this;
            super();
            mDestination = parcelfiledescriptor;
            mResultCallback = writeresultcallback;
            mCancellationSignal = cancellationsignal;
            mCancellationSignal.setOnCancelListener(new _cls1());
        }
    }


    static Context _2D_get0(PrintFileDocumentAdapter printfiledocumentadapter)
    {
        return printfiledocumentadapter.mContext;
    }

    static File _2D_get1(PrintFileDocumentAdapter printfiledocumentadapter)
    {
        return printfiledocumentadapter.mFile;
    }

    public PrintFileDocumentAdapter(Context context, File file, PrintDocumentInfo printdocumentinfo)
    {
        if(file == null)
            throw new IllegalArgumentException("File cannot be null!");
        if(printdocumentinfo == null)
        {
            throw new IllegalArgumentException("documentInfo cannot be null!");
        } else
        {
            mContext = context;
            mFile = file;
            mDocumentInfo = printdocumentinfo;
            return;
        }
    }

    public void onLayout(PrintAttributes printattributes, PrintAttributes printattributes1, CancellationSignal cancellationsignal, PrintDocumentAdapter.LayoutResultCallback layoutresultcallback, Bundle bundle)
    {
        layoutresultcallback.onLayoutFinished(mDocumentInfo, false);
    }

    public void onWrite(PageRange apagerange[], ParcelFileDescriptor parcelfiledescriptor, CancellationSignal cancellationsignal, PrintDocumentAdapter.WriteResultCallback writeresultcallback)
    {
        mWriteFileAsyncTask = new WriteFileAsyncTask(parcelfiledescriptor, cancellationsignal, writeresultcallback);
        mWriteFileAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
    }

    private static final String LOG_TAG = "PrintedFileDocAdapter";
    private final Context mContext;
    private final PrintDocumentInfo mDocumentInfo;
    private final File mFile;
    private WriteFileAsyncTask mWriteFileAsyncTask;

    // Unreferenced inner class android/print/PrintFileDocumentAdapter$WriteFileAsyncTask$1

/* anonymous class */
    class WriteFileAsyncTask._cls1
        implements android.os.CancellationSignal.OnCancelListener
    {

        public void onCancel()
        {
            cancel(true);
        }

        final WriteFileAsyncTask this$1;

            
            {
                this$1 = WriteFileAsyncTask.this;
                super();
            }
    }

}
