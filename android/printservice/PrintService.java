// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice;

import android.app.Service;
import android.content.*;
import android.os.*;
import android.print.PrintJobInfo;
import android.print.PrinterId;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.*;

// Referenced classes of package android.printservice:
//            IPrintServiceClient, PrintJob, PrinterDiscoverySession

public abstract class PrintService extends Service
{
    private final class ServiceHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            int i = message.what;
            i;
            JVM INSTR tableswitch 1 11: default 64
        //                       1 91
        //                       2 170
        //                       3 202
        //                       4 234
        //                       5 257
        //                       6 289
        //                       7 321
        //                       8 353
        //                       9 422
        //                       10 385
        //                       11 459;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown message: ").append(i).toString());
_L2:
            message = onCreatePrinterDiscoverySession();
            if(message == null)
                throw new NullPointerException("session cannot be null");
            if(message.getId() == PrintService._2D_get3(PrintService.this))
                throw new IllegalStateException("cannot reuse session instances");
            PrintService._2D_set1(PrintService.this, message);
            PrintService._2D_set2(PrintService.this, message.getId());
            message.setObserver(PrintService._2D_get0(PrintService.this));
_L14:
            return;
_L3:
            if(PrintService._2D_get1(PrintService.this) != null)
            {
                PrintService._2D_get1(PrintService.this).destroy();
                PrintService._2D_set1(PrintService.this, null);
            }
            continue; /* Loop/switch isn't completed */
_L4:
            if(PrintService._2D_get1(PrintService.this) != null)
            {
                message = (ArrayList)message.obj;
                PrintService._2D_get1(PrintService.this).startPrinterDiscovery(message);
            }
            continue; /* Loop/switch isn't completed */
_L5:
            if(PrintService._2D_get1(PrintService.this) != null)
                PrintService._2D_get1(PrintService.this).stopPrinterDiscovery();
            continue; /* Loop/switch isn't completed */
_L6:
            if(PrintService._2D_get1(PrintService.this) != null)
            {
                message = (List)message.obj;
                PrintService._2D_get1(PrintService.this).validatePrinters(message);
            }
            continue; /* Loop/switch isn't completed */
_L7:
            if(PrintService._2D_get1(PrintService.this) != null)
            {
                message = (PrinterId)message.obj;
                PrintService._2D_get1(PrintService.this).startPrinterStateTracking(message);
            }
            continue; /* Loop/switch isn't completed */
_L8:
            if(PrintService._2D_get1(PrintService.this) != null)
            {
                message = (PrinterId)message.obj;
                PrintService._2D_get1(PrintService.this).requestCustomPrinterIcon(message);
            }
            continue; /* Loop/switch isn't completed */
_L9:
            if(PrintService._2D_get1(PrintService.this) != null)
            {
                message = (PrinterId)message.obj;
                PrintService._2D_get1(PrintService.this).stopPrinterStateTracking(message);
            }
            continue; /* Loop/switch isn't completed */
_L11:
            message = (PrintJobInfo)message.obj;
            onRequestCancelPrintJob(new PrintJob(PrintService.this, message, PrintService._2D_get0(PrintService.this)));
            continue; /* Loop/switch isn't completed */
_L10:
            message = (PrintJobInfo)message.obj;
            onPrintJobQueued(new PrintJob(PrintService.this, message, PrintService._2D_get0(PrintService.this)));
            continue; /* Loop/switch isn't completed */
_L12:
            PrintService._2D_set0(PrintService.this, (IPrintServiceClient)message.obj);
            if(PrintService._2D_get0(PrintService.this) != null)
                onConnected();
            else
                onDisconnected();
            if(true) goto _L14; else goto _L13
_L13:
        }

        public static final int MSG_CREATE_PRINTER_DISCOVERY_SESSION = 1;
        public static final int MSG_DESTROY_PRINTER_DISCOVERY_SESSION = 2;
        public static final int MSG_ON_PRINTJOB_QUEUED = 9;
        public static final int MSG_ON_REQUEST_CANCEL_PRINTJOB = 10;
        public static final int MSG_REQUEST_CUSTOM_PRINTER_ICON = 7;
        public static final int MSG_SET_CLIENT = 11;
        public static final int MSG_START_PRINTER_DISCOVERY = 3;
        public static final int MSG_START_PRINTER_STATE_TRACKING = 6;
        public static final int MSG_STOP_PRINTER_DISCOVERY = 4;
        public static final int MSG_STOP_PRINTER_STATE_TRACKING = 8;
        public static final int MSG_VALIDATE_PRINTERS = 5;
        final PrintService this$0;

        public ServiceHandler(Looper looper)
        {
            this$0 = PrintService.this;
            super(looper, null, true);
        }
    }


    static IPrintServiceClient _2D_get0(PrintService printservice)
    {
        return printservice.mClient;
    }

    static PrinterDiscoverySession _2D_get1(PrintService printservice)
    {
        return printservice.mDiscoverySession;
    }

    static Handler _2D_get2(PrintService printservice)
    {
        return printservice.mHandler;
    }

    static int _2D_get3(PrintService printservice)
    {
        return printservice.mLastSessionId;
    }

    static IPrintServiceClient _2D_set0(PrintService printservice, IPrintServiceClient iprintserviceclient)
    {
        printservice.mClient = iprintserviceclient;
        return iprintserviceclient;
    }

    static PrinterDiscoverySession _2D_set1(PrintService printservice, PrinterDiscoverySession printerdiscoverysession)
    {
        printservice.mDiscoverySession = printerdiscoverysession;
        return printerdiscoverysession;
    }

    static int _2D_set2(PrintService printservice, int i)
    {
        printservice.mLastSessionId = i;
        return i;
    }

    public PrintService()
    {
        mLastSessionId = -1;
    }

    static void throwIfNotCalledOnMainThread()
    {
        if(!Looper.getMainLooper().isCurrentThread())
            throw new IllegalAccessError("must be called from the main thread");
        else
            return;
    }

    protected final void attachBaseContext(Context context)
    {
        super.attachBaseContext(context);
        mHandler = new ServiceHandler(context.getMainLooper());
    }

    public final PrinterId generatePrinterId(String s)
    {
        throwIfNotCalledOnMainThread();
        s = (String)Preconditions.checkNotNull(s, "localId cannot be null");
        return new PrinterId(new ComponentName(getPackageName(), getClass().getName()), s);
    }

    public final List getActivePrintJobs()
    {
        ArrayList arraylist;
        throwIfNotCalledOnMainThread();
        if(mClient == null)
            return Collections.emptyList();
        arraylist = null;
        List list = mClient.getPrintJobInfos();
        if(list == null) goto _L2; else goto _L1
_L1:
        int i;
        i = list.size();
        arraylist = JVM INSTR new #159 <Class ArrayList>;
        arraylist.ArrayList(i);
        int j = 0;
_L3:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        PrintJob printjob = JVM INSTR new #164 <Class PrintJob>;
        printjob.PrintJob(this, (PrintJobInfo)list.get(j), mClient);
        arraylist.add(printjob);
        j++;
        if(true) goto _L3; else goto _L2
_L2:
        if(arraylist != null)
            return arraylist;
          goto _L4
        RemoteException remoteexception;
        remoteexception;
_L6:
        Log.e("PrintService", "Error calling getPrintJobs()", remoteexception);
_L4:
        return Collections.emptyList();
        remoteexception;
        if(true) goto _L6; else goto _L5
_L5:
    }

    public final IBinder onBind(Intent intent)
    {
        return new IPrintService.Stub() {

            public void createPrinterDiscoverySession()
            {
                PrintService._2D_get2(PrintService.this).sendEmptyMessage(1);
            }

            public void destroyPrinterDiscoverySession()
            {
                PrintService._2D_get2(PrintService.this).sendEmptyMessage(2);
            }

            public void onPrintJobQueued(PrintJobInfo printjobinfo)
            {
                PrintService._2D_get2(PrintService.this).obtainMessage(9, printjobinfo).sendToTarget();
            }

            public void requestCancelPrintJob(PrintJobInfo printjobinfo)
            {
                PrintService._2D_get2(PrintService.this).obtainMessage(10, printjobinfo).sendToTarget();
            }

            public void requestCustomPrinterIcon(PrinterId printerid)
            {
                PrintService._2D_get2(PrintService.this).obtainMessage(7, printerid).sendToTarget();
            }

            public void setClient(IPrintServiceClient iprintserviceclient)
            {
                PrintService._2D_get2(PrintService.this).obtainMessage(11, iprintserviceclient).sendToTarget();
            }

            public void startPrinterDiscovery(List list)
            {
                PrintService._2D_get2(PrintService.this).obtainMessage(3, list).sendToTarget();
            }

            public void startPrinterStateTracking(PrinterId printerid)
            {
                PrintService._2D_get2(PrintService.this).obtainMessage(6, printerid).sendToTarget();
            }

            public void stopPrinterDiscovery()
            {
                PrintService._2D_get2(PrintService.this).sendEmptyMessage(4);
            }

            public void stopPrinterStateTracking(PrinterId printerid)
            {
                PrintService._2D_get2(PrintService.this).obtainMessage(8, printerid).sendToTarget();
            }

            public void validatePrinters(List list)
            {
                PrintService._2D_get2(PrintService.this).obtainMessage(5, list).sendToTarget();
            }

            final PrintService this$0;

            
            {
                this$0 = PrintService.this;
                super();
            }
        }
;
    }

    protected void onConnected()
    {
    }

    protected abstract PrinterDiscoverySession onCreatePrinterDiscoverySession();

    protected void onDisconnected()
    {
    }

    protected abstract void onPrintJobQueued(PrintJob printjob);

    protected abstract void onRequestCancelPrintJob(PrintJob printjob);

    private static final boolean DEBUG = false;
    public static final String EXTRA_CAN_SELECT_PRINTER = "android.printservice.extra.CAN_SELECT_PRINTER";
    public static final String EXTRA_PRINTER_INFO = "android.intent.extra.print.EXTRA_PRINTER_INFO";
    public static final String EXTRA_PRINT_DOCUMENT_INFO = "android.printservice.extra.PRINT_DOCUMENT_INFO";
    public static final String EXTRA_PRINT_JOB_INFO = "android.intent.extra.print.PRINT_JOB_INFO";
    public static final String EXTRA_SELECT_PRINTER = "android.printservice.extra.SELECT_PRINTER";
    private static final String LOG_TAG = "PrintService";
    public static final String SERVICE_INTERFACE = "android.printservice.PrintService";
    public static final String SERVICE_META_DATA = "android.printservice";
    private IPrintServiceClient mClient;
    private PrinterDiscoverySession mDiscoverySession;
    private Handler mHandler;
    private int mLastSessionId;
}
