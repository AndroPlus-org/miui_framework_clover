// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.printservice;

import android.content.Context;
import android.os.RemoteException;
import android.print.PrintJobId;
import android.print.PrintJobInfo;
import android.util.Log;

// Referenced classes of package android.printservice:
//            PrintDocument, IPrintServiceClient, PrintService

public final class PrintJob
{

    PrintJob(Context context, PrintJobInfo printjobinfo, IPrintServiceClient iprintserviceclient)
    {
        mContext = context;
        mCachedInfo = printjobinfo;
        mPrintServiceClient = iprintserviceclient;
        mDocument = new PrintDocument(mCachedInfo.getId(), iprintserviceclient, printjobinfo.getDocumentInfo());
    }

    private boolean isInImmutableState()
    {
        boolean flag;
        int i;
        boolean flag1;
        flag = true;
        i = mCachedInfo.getState();
        flag1 = flag;
        if(i == 5) goto _L2; else goto _L1
_L1:
        if(i != 7) goto _L4; else goto _L3
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(i != 6)
            flag1 = false;
        if(true) goto _L2; else goto _L5
_L5:
    }

    private boolean setState(int i, String s)
    {
        if(!mPrintServiceClient.setPrintJobState(mCachedInfo.getId(), i, s))
            break MISSING_BLOCK_LABEL_72;
        mCachedInfo.setState(i);
        mCachedInfo.setStatus(s);
        return true;
        s;
        Log.e("PrintJob", (new StringBuilder()).append("Error setting the state of job: ").append(mCachedInfo.getId()).toString(), s);
        return false;
    }

    public boolean block(String s)
    {
        PrintService.throwIfNotCalledOnMainThread();
        int i = getInfo().getState();
        if(i == 3 || i == 4)
            return setState(4, s);
        else
            return false;
    }

    public boolean cancel()
    {
        PrintService.throwIfNotCalledOnMainThread();
        if(!isInImmutableState())
            return setState(7, null);
        else
            return false;
    }

    public boolean complete()
    {
        PrintService.throwIfNotCalledOnMainThread();
        if(isStarted())
            return setState(5, null);
        else
            return false;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
        {
            return false;
        } else
        {
            obj = (PrintJob)obj;
            return mCachedInfo.getId().equals(((PrintJob) (obj)).mCachedInfo.getId());
        }
    }

    public boolean fail(String s)
    {
        PrintService.throwIfNotCalledOnMainThread();
        if(!isInImmutableState())
            return setState(6, s);
        else
            return false;
    }

    public int getAdvancedIntOption(String s)
    {
        PrintService.throwIfNotCalledOnMainThread();
        return getInfo().getAdvancedIntOption(s);
    }

    public String getAdvancedStringOption(String s)
    {
        PrintService.throwIfNotCalledOnMainThread();
        return getInfo().getAdvancedStringOption(s);
    }

    public PrintDocument getDocument()
    {
        PrintService.throwIfNotCalledOnMainThread();
        return mDocument;
    }

    public PrintJobId getId()
    {
        PrintService.throwIfNotCalledOnMainThread();
        return mCachedInfo.getId();
    }

    public PrintJobInfo getInfo()
    {
        PrintJobInfo printjobinfo;
        PrintService.throwIfNotCalledOnMainThread();
        if(isInImmutableState())
            return mCachedInfo;
        printjobinfo = null;
        PrintJobInfo printjobinfo1 = mPrintServiceClient.getPrintJobInfo(mCachedInfo.getId());
        printjobinfo = printjobinfo1;
_L2:
        if(printjobinfo != null)
            mCachedInfo = printjobinfo;
        return mCachedInfo;
        RemoteException remoteexception;
        remoteexception;
        Log.e("PrintJob", (new StringBuilder()).append("Couldn't get info for job: ").append(mCachedInfo.getId()).toString(), remoteexception);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public String getTag()
    {
        PrintService.throwIfNotCalledOnMainThread();
        return getInfo().getTag();
    }

    public boolean hasAdvancedOption(String s)
    {
        PrintService.throwIfNotCalledOnMainThread();
        return getInfo().hasAdvancedOption(s);
    }

    public int hashCode()
    {
        return mCachedInfo.getId().hashCode();
    }

    public boolean isBlocked()
    {
        PrintService.throwIfNotCalledOnMainThread();
        boolean flag;
        if(getInfo().getState() == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isCancelled()
    {
        PrintService.throwIfNotCalledOnMainThread();
        boolean flag;
        if(getInfo().getState() == 7)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isCompleted()
    {
        PrintService.throwIfNotCalledOnMainThread();
        boolean flag;
        if(getInfo().getState() == 5)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isFailed()
    {
        PrintService.throwIfNotCalledOnMainThread();
        boolean flag;
        if(getInfo().getState() == 6)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isQueued()
    {
        PrintService.throwIfNotCalledOnMainThread();
        boolean flag;
        if(getInfo().getState() == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isStarted()
    {
        PrintService.throwIfNotCalledOnMainThread();
        boolean flag;
        if(getInfo().getState() == 3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void setProgress(float f)
    {
        PrintService.throwIfNotCalledOnMainThread();
        mPrintServiceClient.setProgress(mCachedInfo.getId(), f);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("PrintJob", (new StringBuilder()).append("Error setting progress for job: ").append(mCachedInfo.getId()).toString(), remoteexception);
          goto _L1
    }

    public void setStatus(int i)
    {
        PrintService.throwIfNotCalledOnMainThread();
        mPrintServiceClient.setStatusRes(mCachedInfo.getId(), i, mContext.getPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("PrintJob", (new StringBuilder()).append("Error setting status for job: ").append(mCachedInfo.getId()).toString(), remoteexception);
          goto _L1
    }

    public void setStatus(CharSequence charsequence)
    {
        PrintService.throwIfNotCalledOnMainThread();
        mPrintServiceClient.setStatus(mCachedInfo.getId(), charsequence);
_L1:
        return;
        charsequence;
        Log.e("PrintJob", (new StringBuilder()).append("Error setting status for job: ").append(mCachedInfo.getId()).toString(), charsequence);
          goto _L1
    }

    public boolean setTag(String s)
    {
        PrintService.throwIfNotCalledOnMainThread();
        if(isInImmutableState())
            return false;
        boolean flag;
        try
        {
            flag = mPrintServiceClient.setPrintJobTag(mCachedInfo.getId(), s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.e("PrintJob", (new StringBuilder()).append("Error setting tag for job: ").append(mCachedInfo.getId()).toString(), s);
            return false;
        }
        return flag;
    }

    public boolean start()
    {
        PrintService.throwIfNotCalledOnMainThread();
        int i = getInfo().getState();
        if(i == 2 || i == 4)
            return setState(3, null);
        else
            return false;
    }

    private static final String LOG_TAG = "PrintJob";
    private PrintJobInfo mCachedInfo;
    private final Context mContext;
    private final PrintDocument mDocument;
    private final IPrintServiceClient mPrintServiceClient;
}
