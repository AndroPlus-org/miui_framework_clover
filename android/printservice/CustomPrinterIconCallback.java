// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice;

import android.graphics.drawable.Icon;
import android.os.RemoteException;
import android.print.PrinterId;
import android.util.Log;

// Referenced classes of package android.printservice:
//            IPrintServiceClient

public final class CustomPrinterIconCallback
{

    CustomPrinterIconCallback(PrinterId printerid, IPrintServiceClient iprintserviceclient)
    {
        mPrinterId = printerid;
        mObserver = iprintserviceclient;
    }

    public boolean onCustomPrinterIconLoaded(Icon icon)
    {
        try
        {
            mObserver.onCustomPrinterIconLoaded(mPrinterId, icon);
        }
        // Misplaced declaration of an exception variable
        catch(Icon icon)
        {
            Log.e("CustomPrinterIconCB", "Could not update icon", icon);
            return false;
        }
        return true;
    }

    private static final String LOG_TAG = "CustomPrinterIconCB";
    private final IPrintServiceClient mObserver;
    private final PrinterId mPrinterId;
}
