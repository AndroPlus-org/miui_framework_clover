// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import android.content.Context;
import android.content.pm.ParceledListSlice;
import android.os.*;
import android.util.ArrayMap;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.*;

// Referenced classes of package android.print:
//            IPrintManager, PrinterInfo, PrinterId, IPrinterDiscoveryObserver

public final class PrinterDiscoverySession
{
    public static interface OnPrintersChangeListener
    {

        public abstract void onPrintersChanged();
    }

    public static final class PrinterDiscoveryObserver extends IPrinterDiscoveryObserver.Stub
    {

        public void onPrintersAdded(ParceledListSlice parceledlistslice)
        {
            PrinterDiscoverySession printerdiscoverysession = (PrinterDiscoverySession)mWeakSession.get();
            if(printerdiscoverysession != null)
                PrinterDiscoverySession._2D_get0(printerdiscoverysession).obtainMessage(1, parceledlistslice.getList()).sendToTarget();
        }

        public void onPrintersRemoved(ParceledListSlice parceledlistslice)
        {
            PrinterDiscoverySession printerdiscoverysession = (PrinterDiscoverySession)mWeakSession.get();
            if(printerdiscoverysession != null)
                PrinterDiscoverySession._2D_get0(printerdiscoverysession).obtainMessage(2, parceledlistslice.getList()).sendToTarget();
        }

        private final WeakReference mWeakSession;

        public PrinterDiscoveryObserver(PrinterDiscoverySession printerdiscoverysession)
        {
            mWeakSession = new WeakReference(printerdiscoverysession);
        }
    }

    private final class SessionHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 29
        //                       2 48;
               goto _L1 _L2 _L3
_L1:
            return;
_L2:
            message = (List)message.obj;
            PrinterDiscoverySession._2D_wrap0(PrinterDiscoverySession.this, message);
            continue; /* Loop/switch isn't completed */
_L3:
            message = (List)message.obj;
            PrinterDiscoverySession._2D_wrap1(PrinterDiscoverySession.this, message);
            if(true) goto _L1; else goto _L4
_L4:
        }

        final PrinterDiscoverySession this$0;

        public SessionHandler(Looper looper)
        {
            this$0 = PrinterDiscoverySession.this;
            super(looper, null, false);
        }
    }


    static Handler _2D_get0(PrinterDiscoverySession printerdiscoverysession)
    {
        return printerdiscoverysession.mHandler;
    }

    static void _2D_wrap0(PrinterDiscoverySession printerdiscoverysession, List list)
    {
        printerdiscoverysession.handlePrintersAdded(list);
    }

    static void _2D_wrap1(PrinterDiscoverySession printerdiscoverysession, List list)
    {
        printerdiscoverysession.handlePrintersRemoved(list);
    }

    PrinterDiscoverySession(IPrintManager iprintmanager, Context context, int i)
    {
        mPrinters = new LinkedHashMap();
        mPrintManager = iprintmanager;
        mUserId = i;
        mHandler = new SessionHandler(context.getMainLooper());
        mObserver = new PrinterDiscoveryObserver(this);
        mPrintManager.createPrinterDiscoverySession(mObserver, mUserId);
_L1:
        return;
        iprintmanager;
        Log.e("PrinterDiscoverySession", "Error creating printer discovery session", iprintmanager);
          goto _L1
    }

    private void destroyNoCheck()
    {
        stopPrinterDiscovery();
        mPrintManager.destroyPrinterDiscoverySession(mObserver, mUserId);
        mObserver = null;
        mPrinters.clear();
_L2:
        return;
        Object obj;
        obj;
        Log.e("PrinterDiscoverySession", "Error destroying printer discovery session", ((Throwable) (obj)));
        mObserver = null;
        mPrinters.clear();
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mObserver = null;
        mPrinters.clear();
        throw obj;
    }

    private void handlePrintersAdded(List list)
    {
        if(isDestroyed())
            return;
        if(mPrinters.isEmpty())
        {
            int i = list.size();
            for(int k = 0; k < i; k++)
            {
                PrinterInfo printerinfo = (PrinterInfo)list.get(k);
                mPrinters.put(printerinfo.getId(), printerinfo);
            }

            notifyOnPrintersChanged();
            return;
        }
        ArrayMap arraymap = new ArrayMap();
        int j = list.size();
        for(int l = 0; l < j; l++)
        {
            PrinterInfo printerinfo1 = (PrinterInfo)list.get(l);
            arraymap.put(printerinfo1.getId(), printerinfo1);
        }

        list = mPrinters.keySet().iterator();
        do
        {
            if(!list.hasNext())
                break;
            PrinterId printerid = (PrinterId)list.next();
            PrinterInfo printerinfo2 = (PrinterInfo)arraymap.remove(printerid);
            if(printerinfo2 != null)
                mPrinters.put(printerid, printerinfo2);
        } while(true);
        mPrinters.putAll(arraymap);
        notifyOnPrintersChanged();
    }

    private void handlePrintersRemoved(List list)
    {
        if(isDestroyed())
            return;
        boolean flag = false;
        int i = list.size();
        for(int j = 0; j < i; j++)
        {
            PrinterId printerid = (PrinterId)list.get(j);
            if(mPrinters.remove(printerid) != null)
                flag = true;
        }

        if(flag)
            notifyOnPrintersChanged();
    }

    private boolean isDestroyedNoCheck()
    {
        boolean flag;
        if(mObserver == null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void notifyOnPrintersChanged()
    {
        if(mListener != null)
            mListener.onPrintersChanged();
    }

    private static void throwIfNotCalledOnMainThread()
    {
        if(!Looper.getMainLooper().isCurrentThread())
            throw new IllegalAccessError("must be called from the main thread");
        else
            return;
    }

    public final void destroy()
    {
        if(isDestroyed())
            Log.w("PrinterDiscoverySession", "Ignoring destroy - session destroyed");
        destroyNoCheck();
    }

    protected final void finalize()
        throws Throwable
    {
        if(!isDestroyedNoCheck())
        {
            Log.e("PrinterDiscoverySession", "Destroying leaked printer discovery session");
            destroyNoCheck();
        }
        super.finalize();
    }

    public final List getPrinters()
    {
        if(isDestroyed())
        {
            Log.w("PrinterDiscoverySession", "Ignoring get printers - session destroyed");
            return Collections.emptyList();
        } else
        {
            return new ArrayList(mPrinters.values());
        }
    }

    public final boolean isDestroyed()
    {
        throwIfNotCalledOnMainThread();
        return isDestroyedNoCheck();
    }

    public final boolean isPrinterDiscoveryStarted()
    {
        throwIfNotCalledOnMainThread();
        return mIsPrinterDiscoveryStarted;
    }

    public final void setOnPrintersChangeListener(OnPrintersChangeListener onprinterschangelistener)
    {
        throwIfNotCalledOnMainThread();
        mListener = onprinterschangelistener;
    }

    public final void startPrinterDiscovery(List list)
    {
        if(isDestroyed())
        {
            Log.w("PrinterDiscoverySession", "Ignoring start printers discovery - session destroyed");
            return;
        }
        if(mIsPrinterDiscoveryStarted)
            break MISSING_BLOCK_LABEL_46;
        mIsPrinterDiscoveryStarted = true;
        mPrintManager.startPrinterDiscovery(mObserver, list, mUserId);
_L1:
        return;
        list;
        Log.e("PrinterDiscoverySession", "Error starting printer discovery", list);
          goto _L1
    }

    public final void startPrinterStateTracking(PrinterId printerid)
    {
        if(isDestroyed())
        {
            Log.w("PrinterDiscoverySession", "Ignoring start printer state tracking - session destroyed");
            return;
        }
        mPrintManager.startPrinterStateTracking(printerid, mUserId);
_L1:
        return;
        printerid;
        Log.e("PrinterDiscoverySession", "Error starting printer state tracking", printerid);
          goto _L1
    }

    public final void stopPrinterDiscovery()
    {
        if(isDestroyed())
        {
            Log.w("PrinterDiscoverySession", "Ignoring stop printers discovery - session destroyed");
            return;
        }
        if(!mIsPrinterDiscoveryStarted)
            break MISSING_BLOCK_LABEL_46;
        mIsPrinterDiscoveryStarted = false;
        mPrintManager.stopPrinterDiscovery(mObserver, mUserId);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("PrinterDiscoverySession", "Error stopping printer discovery", remoteexception);
          goto _L1
    }

    public final void stopPrinterStateTracking(PrinterId printerid)
    {
        if(isDestroyed())
        {
            Log.w("PrinterDiscoverySession", "Ignoring stop printer state tracking - session destroyed");
            return;
        }
        mPrintManager.stopPrinterStateTracking(printerid, mUserId);
_L1:
        return;
        printerid;
        Log.e("PrinterDiscoverySession", "Error stopping printer state tracking", printerid);
          goto _L1
    }

    public final void validatePrinters(List list)
    {
        if(isDestroyed())
        {
            Log.w("PrinterDiscoverySession", "Ignoring validate printers - session destroyed");
            return;
        }
        mPrintManager.validatePrinters(list, mUserId);
_L1:
        return;
        list;
        Log.e("PrinterDiscoverySession", "Error validating printers", list);
          goto _L1
    }

    private static final String LOG_TAG = "PrinterDiscoverySession";
    private static final int MSG_PRINTERS_ADDED = 1;
    private static final int MSG_PRINTERS_REMOVED = 2;
    private final Handler mHandler;
    private boolean mIsPrinterDiscoveryStarted;
    private OnPrintersChangeListener mListener;
    private IPrinterDiscoveryObserver mObserver;
    private final IPrintManager mPrintManager;
    private final LinkedHashMap mPrinters;
    private final int mUserId;
}
