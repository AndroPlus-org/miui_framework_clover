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

public class PrintServiceRecommendationsLoader extends Loader
{
    private class MyHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            if(isStarted())
                deliverResult((List)message.obj);
        }

        final PrintServiceRecommendationsLoader this$0;

        public MyHandler()
        {
            this$0 = PrintServiceRecommendationsLoader.this;
            super(getContext().getMainLooper());
        }
    }


    static void _2D_wrap0(PrintServiceRecommendationsLoader printservicerecommendationsloader)
    {
        printservicerecommendationsloader.queueNewResult();
    }

    public PrintServiceRecommendationsLoader(PrintManager printmanager, Context context)
    {
        super((Context)Preconditions.checkNotNull(context));
        mPrintManager = (PrintManager)Preconditions.checkNotNull(printmanager);
    }

    private void queueNewResult()
    {
        Message message = mHandler.obtainMessage(0);
        message.obj = mPrintManager.getPrintServiceRecommendations();
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
        mListener = new PrintManager.PrintServiceRecommendationsChangeListener() {

            public void onPrintServiceRecommendationsChanged()
            {
                PrintServiceRecommendationsLoader._2D_wrap0(PrintServiceRecommendationsLoader.this);
            }

            final PrintServiceRecommendationsLoader this$0;

            
            {
                this$0 = PrintServiceRecommendationsLoader.this;
                super();
            }
        }
;
        mPrintManager.addPrintServiceRecommendationsChangeListener(mListener, null);
        deliverResult(mPrintManager.getPrintServiceRecommendations());
    }

    protected void onStopLoading()
    {
        if(mListener != null)
        {
            mPrintManager.removePrintServiceRecommendationsChangeListener(mListener);
            mListener = null;
        }
        mHandler.removeMessages(0);
    }

    private final Handler mHandler = new MyHandler();
    private PrintManager.PrintServiceRecommendationsChangeListener mListener;
    private final PrintManager mPrintManager;
}
