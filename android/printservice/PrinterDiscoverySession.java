// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice;

import android.content.pm.ParceledListSlice;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.print.PrinterId;
import android.print.PrinterInfo;
import android.util.ArrayMap;
import android.util.Log;
import java.util.*;

// Referenced classes of package android.printservice:
//            IPrintServiceClient, PrintService, CustomPrinterIconCallback

public abstract class PrinterDiscoverySession
{

    public PrinterDiscoverySession()
    {
        int i = sIdCounter;
        sIdCounter = i + 1;
        mId = i;
    }

    private void sendOutOfDiscoveryPeriodPrinterChanges()
    {
        if(mLastSentPrinters == null || mLastSentPrinters.isEmpty())
        {
            mLastSentPrinters = null;
            return;
        }
        Object obj = null;
        Object obj1 = mPrinters.values().iterator();
        do
        {
            if(!((Iterator) (obj1)).hasNext())
                break;
            PrinterInfo printerinfo = (PrinterInfo)((Iterator) (obj1)).next();
            PrinterInfo printerinfo2 = (PrinterInfo)mLastSentPrinters.get(printerinfo.getId());
            if(printerinfo2 == null || printerinfo2.equals(printerinfo) ^ true)
            {
                ArrayList arraylist = ((ArrayList) (obj));
                if(obj == null)
                    arraylist = new ArrayList();
                arraylist.add(printerinfo);
                obj = arraylist;
            }
        } while(true);
        if(obj != null)
            try
            {
                IPrintServiceClient iprintserviceclient = mObserver;
                obj1 = JVM INSTR new #94  <Class ParceledListSlice>;
                ((ParceledListSlice) (obj1)).ParceledListSlice(((List) (obj)));
                iprintserviceclient.onPrintersAdded(((ParceledListSlice) (obj1)));
            }
            // Misplaced declaration of an exception variable
            catch(Object obj)
            {
                Log.e("PrinterDiscoverySession", "Error sending added printers", ((Throwable) (obj)));
            }
        obj = null;
        obj1 = mLastSentPrinters.values().iterator();
        do
        {
            if(!((Iterator) (obj1)).hasNext())
                break;
            PrinterInfo printerinfo1 = (PrinterInfo)((Iterator) (obj1)).next();
            if(!mPrinters.containsKey(printerinfo1.getId()))
            {
                Object obj2 = obj;
                if(obj == null)
                    obj2 = new ArrayList();
                ((List) (obj2)).add(printerinfo1.getId());
                obj = obj2;
            }
        } while(true);
        if(obj != null)
            try
            {
                IPrintServiceClient iprintserviceclient1 = mObserver;
                ParceledListSlice parceledlistslice = JVM INSTR new #94  <Class ParceledListSlice>;
                parceledlistslice.ParceledListSlice(((List) (obj)));
                iprintserviceclient1.onPrintersRemoved(parceledlistslice);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("PrinterDiscoverySession", "Error sending removed printers", remoteexception);
            }
        mLastSentPrinters = null;
    }

    public final void addPrinters(List list)
    {
        ArrayList arraylist;
        PrintService.throwIfNotCalledOnMainThread();
        if(mIsDestroyed)
        {
            Log.w("PrinterDiscoverySession", "Not adding printers - session destroyed.");
            return;
        }
        if(!mIsDiscoveryStarted)
            break MISSING_BLOCK_LABEL_172;
        arraylist = null;
        int i = list.size();
        for(int k = 0; k < i;)
        {
            ArrayList arraylist1;
label0:
            {
                PrinterInfo printerinfo1 = (PrinterInfo)list.get(k);
                PrinterInfo printerinfo2 = (PrinterInfo)mPrinters.put(printerinfo1.getId(), printerinfo1);
                if(printerinfo2 != null)
                {
                    arraylist1 = arraylist;
                    if(!(printerinfo2.equals(printerinfo1) ^ true))
                        break label0;
                }
                arraylist1 = arraylist;
                if(arraylist == null)
                    arraylist1 = new ArrayList();
                arraylist1.add(printerinfo1);
            }
            k++;
            arraylist = arraylist1;
        }

        if(arraylist == null)
            break MISSING_BLOCK_LABEL_158;
        IPrintServiceClient iprintserviceclient = mObserver;
        list = JVM INSTR new #94  <Class ParceledListSlice>;
        list.ParceledListSlice(arraylist);
        iprintserviceclient.onPrintersAdded(list);
_L1:
        return;
        list;
        Log.e("PrinterDiscoverySession", "Error sending added printers", list);
          goto _L1
        if(mLastSentPrinters == null)
            mLastSentPrinters = new ArrayMap(mPrinters);
        int j = list.size();
        int l = 0;
        while(l < j) 
        {
            PrinterInfo printerinfo = (PrinterInfo)list.get(l);
            if(mPrinters.get(printerinfo.getId()) == null)
                mPrinters.put(printerinfo.getId(), printerinfo);
            l++;
        }
          goto _L1
    }

    void destroy()
    {
        if(!mIsDestroyed)
        {
            mIsDestroyed = true;
            mIsDiscoveryStarted = false;
            mPrinters.clear();
            mLastSentPrinters = null;
            mObserver = null;
            onDestroy();
        }
    }

    int getId()
    {
        return mId;
    }

    public final List getPrinters()
    {
        PrintService.throwIfNotCalledOnMainThread();
        if(mIsDestroyed)
            return Collections.emptyList();
        else
            return new ArrayList(mPrinters.values());
    }

    public final List getTrackedPrinters()
    {
        PrintService.throwIfNotCalledOnMainThread();
        if(mIsDestroyed)
            return Collections.emptyList();
        else
            return new ArrayList(mTrackedPrinters);
    }

    public final boolean isDestroyed()
    {
        PrintService.throwIfNotCalledOnMainThread();
        return mIsDestroyed;
    }

    public final boolean isPrinterDiscoveryStarted()
    {
        PrintService.throwIfNotCalledOnMainThread();
        return mIsDiscoveryStarted;
    }

    public abstract void onDestroy();

    public void onRequestCustomPrinterIcon(PrinterId printerid, CancellationSignal cancellationsignal, CustomPrinterIconCallback customprintericoncallback)
    {
    }

    public abstract void onStartPrinterDiscovery(List list);

    public abstract void onStartPrinterStateTracking(PrinterId printerid);

    public abstract void onStopPrinterDiscovery();

    public abstract void onStopPrinterStateTracking(PrinterId printerid);

    public abstract void onValidatePrinters(List list);

    public final void removePrinters(List list)
    {
        ArrayList arraylist;
        PrintService.throwIfNotCalledOnMainThread();
        if(mIsDestroyed)
        {
            Log.w("PrinterDiscoverySession", "Not removing printers - session destroyed.");
            return;
        }
        if(!mIsDiscoveryStarted)
            break MISSING_BLOCK_LABEL_136;
        arraylist = new ArrayList();
        int i = list.size();
        for(int k = 0; k < i; k++)
        {
            PrinterId printerid1 = (PrinterId)list.get(k);
            if(mPrinters.remove(printerid1) != null)
                arraylist.add(printerid1);
        }

        if(arraylist.isEmpty())
            break MISSING_BLOCK_LABEL_122;
        IPrintServiceClient iprintserviceclient = mObserver;
        list = JVM INSTR new #94  <Class ParceledListSlice>;
        list.ParceledListSlice(arraylist);
        iprintserviceclient.onPrintersRemoved(list);
_L1:
        return;
        list;
        Log.e("PrinterDiscoverySession", "Error sending removed printers", list);
          goto _L1
        if(mLastSentPrinters == null)
            mLastSentPrinters = new ArrayMap(mPrinters);
        int j = list.size();
        int l = 0;
        while(l < j) 
        {
            PrinterId printerid = (PrinterId)list.get(l);
            mPrinters.remove(printerid);
            l++;
        }
          goto _L1
    }

    void requestCustomPrinterIcon(PrinterId printerid)
    {
        if(!mIsDestroyed && mObserver != null)
        {
            CustomPrinterIconCallback customprintericoncallback = new CustomPrinterIconCallback(printerid, mObserver);
            onRequestCustomPrinterIcon(printerid, new CancellationSignal(), customprintericoncallback);
        }
    }

    void setObserver(IPrintServiceClient iprintserviceclient)
    {
        mObserver = iprintserviceclient;
        if(mPrinters.isEmpty())
            break MISSING_BLOCK_LABEL_39;
        IPrintServiceClient iprintserviceclient1 = mObserver;
        iprintserviceclient = JVM INSTR new #94  <Class ParceledListSlice>;
        iprintserviceclient.ParceledListSlice(getPrinters());
        iprintserviceclient1.onPrintersAdded(iprintserviceclient);
_L1:
        return;
        iprintserviceclient;
        Log.e("PrinterDiscoverySession", "Error sending added printers", iprintserviceclient);
          goto _L1
    }

    void startPrinterDiscovery(List list)
    {
        if(!mIsDestroyed)
        {
            mIsDiscoveryStarted = true;
            sendOutOfDiscoveryPeriodPrinterChanges();
            List list1 = list;
            if(list == null)
                list1 = Collections.emptyList();
            onStartPrinterDiscovery(list1);
        }
    }

    void startPrinterStateTracking(PrinterId printerid)
    {
        if(!mIsDestroyed && mObserver != null && mTrackedPrinters.contains(printerid) ^ true)
        {
            mTrackedPrinters.add(printerid);
            onStartPrinterStateTracking(printerid);
        }
    }

    void stopPrinterDiscovery()
    {
        if(!mIsDestroyed)
        {
            mIsDiscoveryStarted = false;
            onStopPrinterDiscovery();
        }
    }

    void stopPrinterStateTracking(PrinterId printerid)
    {
        if(!mIsDestroyed && mObserver != null && mTrackedPrinters.remove(printerid))
            onStopPrinterStateTracking(printerid);
    }

    void validatePrinters(List list)
    {
        if(!mIsDestroyed && mObserver != null)
            onValidatePrinters(list);
    }

    private static final String LOG_TAG = "PrinterDiscoverySession";
    private static int sIdCounter = 0;
    private final int mId;
    private boolean mIsDestroyed;
    private boolean mIsDiscoveryStarted;
    private ArrayMap mLastSentPrinters;
    private IPrintServiceClient mObserver;
    private final ArrayMap mPrinters = new ArrayMap();
    private final List mTrackedPrinters = new ArrayList();

}
