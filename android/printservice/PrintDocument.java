// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.print.PrintDocumentInfo;
import android.print.PrintJobId;
import android.util.Log;
import java.io.IOException;

// Referenced classes of package android.printservice:
//            PrintService, IPrintServiceClient

public final class PrintDocument
{

    PrintDocument(PrintJobId printjobid, IPrintServiceClient iprintserviceclient, PrintDocumentInfo printdocumentinfo)
    {
        mPrintJobId = printjobid;
        mPrintServiceClient = iprintserviceclient;
        mInfo = printdocumentinfo;
    }

    public ParcelFileDescriptor getData()
    {
        Object obj;
        Object obj1;
        ParcelFileDescriptor parcelfiledescriptor;
        PrintService.throwIfNotCalledOnMainThread();
        obj = null;
        obj1 = null;
        parcelfiledescriptor = null;
        ParcelFileDescriptor aparcelfiledescriptor[] = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelfiledescriptor1;
        ParcelFileDescriptor parcelfiledescriptor2;
        parcelfiledescriptor2 = aparcelfiledescriptor[0];
        parcelfiledescriptor1 = aparcelfiledescriptor[1];
        parcelfiledescriptor = parcelfiledescriptor1;
        obj = parcelfiledescriptor1;
        obj1 = parcelfiledescriptor1;
        mPrintServiceClient.writePrintJobData(parcelfiledescriptor1, mPrintJobId);
        if(parcelfiledescriptor1 != null)
            try
            {
                parcelfiledescriptor1.close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1) { }
        return parcelfiledescriptor2;
        obj;
        obj1 = parcelfiledescriptor;
        Log.e("PrintDocument", "Error calling getting print job data!", ((Throwable) (obj)));
        if(parcelfiledescriptor != null)
            try
            {
                parcelfiledescriptor.close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1) { }
_L2:
        return null;
        Object obj2;
        obj2;
        obj1 = obj;
        Log.e("PrintDocument", "Error calling getting print job data!", ((Throwable) (obj2)));
        if(obj != null)
            try
            {
                ((ParcelFileDescriptor) (obj)).close();
            }
            // Misplaced declaration of an exception variable
            catch(Object obj1) { }
        if(true) goto _L2; else goto _L1
_L1:
        obj2;
        if(obj1 != null)
            try
            {
                ((ParcelFileDescriptor) (obj1)).close();
            }
            catch(IOException ioexception) { }
        throw obj2;
    }

    public PrintDocumentInfo getInfo()
    {
        PrintService.throwIfNotCalledOnMainThread();
        return mInfo;
    }

    private static final String LOG_TAG = "PrintDocument";
    private final PrintDocumentInfo mInfo;
    private final PrintJobId mPrintJobId;
    private final IPrintServiceClient mPrintServiceClient;
}
