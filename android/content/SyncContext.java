// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.os.*;

// Referenced classes of package android.content:
//            ISyncContext, SyncResult

public class SyncContext
{

    public SyncContext(ISyncContext isynccontext)
    {
        mSyncContext = isynccontext;
        mLastHeartbeatSendTime = 0L;
    }

    private void updateHeartbeat()
    {
        long l;
        l = SystemClock.elapsedRealtime();
        if(l < mLastHeartbeatSendTime + 1000L)
            return;
        mLastHeartbeatSendTime = l;
        if(mSyncContext != null)
            mSyncContext.sendHeartbeat();
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public IBinder getSyncContextBinder()
    {
        IBinder ibinder = null;
        if(mSyncContext != null)
            ibinder = mSyncContext.asBinder();
        return ibinder;
    }

    public void onFinished(SyncResult syncresult)
    {
        if(mSyncContext != null)
            mSyncContext.onFinished(syncresult);
_L2:
        return;
        syncresult;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void setStatusText(String s)
    {
        updateHeartbeat();
    }

    private static final long HEARTBEAT_SEND_INTERVAL_IN_MS = 1000L;
    private long mLastHeartbeatSendTime;
    private ISyncContext mSyncContext;
}
