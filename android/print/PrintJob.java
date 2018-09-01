// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.print;

import java.util.Objects;

// Referenced classes of package android.print:
//            PrintJobInfo, PrintManager, PrintJobId

public final class PrintJob
{

    PrintJob(PrintJobInfo printjobinfo, PrintManager printmanager)
    {
        mCachedInfo = printjobinfo;
        mPrintManager = printmanager;
    }

    private boolean isInImmutableState()
    {
        boolean flag = true;
        int i = mCachedInfo.getState();
        boolean flag1 = flag;
        if(i != 5)
            if(i == 7)
                flag1 = flag;
            else
                flag1 = false;
        return flag1;
    }

    public void cancel()
    {
        int i;
        i = getInfo().getState();
        break MISSING_BLOCK_LABEL_8;
        if(i == 2 || i == 3 || i == 4 || i == 6)
            mPrintManager.cancelPrintJob(mCachedInfo.getId());
        return;
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
            return Objects.equals(mCachedInfo.getId(), ((PrintJob) (obj)).mCachedInfo.getId());
        }
    }

    public PrintJobId getId()
    {
        return mCachedInfo.getId();
    }

    public PrintJobInfo getInfo()
    {
        if(isInImmutableState())
            return mCachedInfo;
        PrintJobInfo printjobinfo = mPrintManager.getPrintJobInfo(mCachedInfo.getId());
        if(printjobinfo != null)
            mCachedInfo = printjobinfo;
        return mCachedInfo;
    }

    public int hashCode()
    {
        PrintJobId printjobid = mCachedInfo.getId();
        if(printjobid == null)
            return 0;
        else
            return printjobid.hashCode();
    }

    public boolean isBlocked()
    {
        boolean flag;
        if(getInfo().getState() == 4)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isCancelled()
    {
        boolean flag;
        if(getInfo().getState() == 7)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isCompleted()
    {
        boolean flag;
        if(getInfo().getState() == 5)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isFailed()
    {
        boolean flag;
        if(getInfo().getState() == 6)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isQueued()
    {
        boolean flag;
        if(getInfo().getState() == 2)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isStarted()
    {
        boolean flag;
        if(getInfo().getState() == 3)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public void restart()
    {
        if(isFailed())
            mPrintManager.restartPrintJob(mCachedInfo.getId());
    }

    private PrintJobInfo mCachedInfo;
    private final PrintManager mPrintManager;
}
