// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telephony.mbms;


// Referenced classes of package android.telephony.mbms:
//            DownloadRequest, FileInfo

public class DownloadStateCallback
{

    public DownloadStateCallback()
    {
        mCallbackFilterFlags = 0;
    }

    public DownloadStateCallback(int i)
    {
        mCallbackFilterFlags = i;
    }

    public int getCallbackFilterFlags()
    {
        return mCallbackFilterFlags;
    }

    public final boolean isFilterFlagSet(int i)
    {
        boolean flag = true;
        if(mCallbackFilterFlags == 0)
            return true;
        if((mCallbackFilterFlags & i) <= 0)
            flag = false;
        return flag;
    }

    public void onProgressUpdated(DownloadRequest downloadrequest, FileInfo fileinfo, int i, int j, int k, int l)
    {
    }

    public void onStateUpdated(DownloadRequest downloadrequest, FileInfo fileinfo, int i)
    {
    }

    public static final int ALL_UPDATES = 0;
    public static final int PROGRESS_UPDATES = 1;
    public static final int STATE_UPDATES = 2;
    private final int mCallbackFilterFlags;
}
