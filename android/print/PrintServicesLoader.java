// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.content.Context;
import android.content.Loader;
import android.os.Handler;
import android.os.Message;
import com.android.internal.util.Preconditions;
import java.util.List;

// Referenced classes of package android.print:
//            PrintManager

public class PrintServicesLoader extends Loader
{
    private class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(isStarted())
                deliverResult((List)message.obj);
        }

        final PrintServicesLoader this$0;

        public MyHandler()
        {
            this$0 = PrintServicesLoader.this;
            super(getContext().getMainLooper());
        }
    }


    static void _2D_wrap0(PrintServicesLoader printservicesloader)
    {
        printservicesloader.queueNewResult();
    }

    public PrintServicesLoader(PrintManager printmanager, Context context, int i)
    {
        super((Context)Preconditions.checkNotNull(context));
        mPrintManager = (PrintManager)Preconditions.checkNotNull(printmanager);
        mSelectionFlags = Preconditions.checkFlagsArgument(i, 3);
    }

    private void queueNewResult()
    {
        Message message = mHandler.obtainMessage(0);
        message.obj = mPrintManager.getPrintServices(mSelectionFlags);
        mHandler.sendMessage(message);
    }

    protected void onForceLoad()
    {
        queueNewResult();
    }

    protected void onReset()
    {
        onStopLoading();
    }

    protected void onStartLoading()
    {
        mListener = new PrintManager.PrintServicesChangeListener() {

            public void onPrintServicesChanged()
            {
                PrintServicesLoader._2D_wrap0(PrintServicesLoader.this);
            }

            final PrintServicesLoader this$0;

            
            {
                this$0 = PrintServicesLoader.this;
                super();
            }
        }
;
        mPrintManager.addPrintServicesChangeListener(mListener, null);
        deliverResult(mPrintManager.getPrintServices(mSelectionFlags));
    }

    protected void onStopLoading()
    {
        if(mListener != null)
        {
            mPrintManager.removePrintServicesChangeListener(mListener);
            mListener = null;
        }
        mHandler.removeMessages(0);
    }

    private final Handler mHandler = new MyHandler();
    private PrintManager.PrintServicesChangeListener mListener;
    private final PrintManager mPrintManager;
    private final int mSelectionFlags;
}
