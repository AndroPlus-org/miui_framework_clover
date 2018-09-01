// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.content.Context;
import android.os.*;
import android.util.Log;

// Referenced classes of package android.app.backup:
//            IRestoreSession, RestoreObserver, BackupManagerMonitor, RestoreSet

public class RestoreSession
{
    private class BackupManagerMonitorWrapper extends IBackupManagerMonitor.Stub
    {

        public void onEvent(Bundle bundle)
            throws RemoteException
        {
            mMonitor.onEvent(bundle);
        }

        final BackupManagerMonitor mMonitor;
        final RestoreSession this$0;

        BackupManagerMonitorWrapper(BackupManagerMonitor backupmanagermonitor)
        {
            this$0 = RestoreSession.this;
            super();
            mMonitor = backupmanagermonitor;
        }
    }

    private class RestoreObserverWrapper extends IRestoreObserver.Stub
    {

        public void onUpdate(int i, String s)
        {
            mHandler.sendMessage(mHandler.obtainMessage(2, i, 0, s));
        }

        public void restoreFinished(int i)
        {
            mHandler.sendMessage(mHandler.obtainMessage(3, i, 0));
        }

        public void restoreSetsAvailable(RestoreSet arestoreset[])
        {
            mHandler.sendMessage(mHandler.obtainMessage(4, arestoreset));
        }

        public void restoreStarting(int i)
        {
            mHandler.sendMessage(mHandler.obtainMessage(1, i, 0));
        }

        static final int MSG_RESTORE_FINISHED = 3;
        static final int MSG_RESTORE_SETS_AVAILABLE = 4;
        static final int MSG_RESTORE_STARTING = 1;
        static final int MSG_UPDATE = 2;
        final RestoreObserver mAppObserver;
        final Handler mHandler;
        final RestoreSession this$0;

        RestoreObserverWrapper(Context context, RestoreObserver restoreobserver)
        {
            this$0 = RestoreSession.this;
            super();
            mHandler = new _cls1(context.getMainLooper());
            mAppObserver = restoreobserver;
        }
    }


    RestoreSession(Context context, IRestoreSession irestoresession)
    {
        mObserver = null;
        mContext = context;
        mBinder = irestoresession;
    }

    public void endRestoreSession()
    {
        mBinder.endRestoreSession();
_L2:
        mBinder = null;
        return;
        Object obj;
        obj;
        Log.d("RestoreSession", "Can't contact server to get available sets");
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        mBinder = null;
        throw obj;
    }

    public int getAvailableRestoreSets(RestoreObserver restoreobserver)
    {
        return getAvailableRestoreSets(restoreobserver, null);
    }

    public int getAvailableRestoreSets(RestoreObserver restoreobserver, BackupManagerMonitor backupmanagermonitor)
    {
        int i = -1;
        RestoreObserverWrapper restoreobserverwrapper = new RestoreObserverWrapper(mContext, restoreobserver);
        int j;
        if(backupmanagermonitor == null)
            restoreobserver = null;
        else
            restoreobserver = new BackupManagerMonitorWrapper(backupmanagermonitor);
        j = mBinder.getAvailableRestoreSets(restoreobserverwrapper, restoreobserver);
        i = j;
_L2:
        return i;
        restoreobserver;
        Log.d("RestoreSession", "Can't contact server to get available sets");
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int restoreAll(long l, RestoreObserver restoreobserver)
    {
        return restoreAll(l, restoreobserver, null);
    }

    public int restoreAll(long l, RestoreObserver restoreobserver, BackupManagerMonitor backupmanagermonitor)
    {
        int i = -1;
        if(mObserver != null)
        {
            Log.d("RestoreSession", "restoreAll() called during active restore");
            return -1;
        }
        mObserver = new RestoreObserverWrapper(mContext, restoreobserver);
        int j;
        if(backupmanagermonitor == null)
            restoreobserver = null;
        else
            restoreobserver = new BackupManagerMonitorWrapper(backupmanagermonitor);
        j = mBinder.restoreAll(l, mObserver, restoreobserver);
        i = j;
_L2:
        return i;
        restoreobserver;
        Log.d("RestoreSession", "Can't contact server to restore");
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int restorePackage(String s, RestoreObserver restoreobserver)
    {
        return restorePackage(s, restoreobserver, null);
    }

    public int restorePackage(String s, RestoreObserver restoreobserver, BackupManagerMonitor backupmanagermonitor)
    {
        byte byte0 = -1;
        if(mObserver != null)
        {
            Log.d("RestoreSession", "restorePackage() called during active restore");
            return -1;
        }
        mObserver = new RestoreObserverWrapper(mContext, restoreobserver);
        int i;
        if(backupmanagermonitor == null)
            restoreobserver = null;
        else
            restoreobserver = new BackupManagerMonitorWrapper(backupmanagermonitor);
        try
        {
            i = mBinder.restorePackage(s, mObserver, restoreobserver);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            Log.d("RestoreSession", "Can't contact server to restore package");
            i = byte0;
        }
        return i;
    }

    public int restoreSome(long l, RestoreObserver restoreobserver, BackupManagerMonitor backupmanagermonitor, String as[])
    {
        int i = -1;
        if(mObserver != null)
        {
            Log.d("RestoreSession", "restoreAll() called during active restore");
            return -1;
        }
        mObserver = new RestoreObserverWrapper(mContext, restoreobserver);
        int j;
        if(backupmanagermonitor == null)
            restoreobserver = null;
        else
            restoreobserver = new BackupManagerMonitorWrapper(backupmanagermonitor);
        j = mBinder.restoreSome(l, mObserver, restoreobserver, as);
        i = j;
_L2:
        return i;
        restoreobserver;
        Log.d("RestoreSession", "Can't contact server to restore packages");
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int restoreSome(long l, RestoreObserver restoreobserver, String as[])
    {
        return restoreSome(l, restoreobserver, null, as);
    }

    static final String TAG = "RestoreSession";
    IRestoreSession mBinder;
    final Context mContext;
    RestoreObserverWrapper mObserver;

    // Unreferenced inner class android/app/backup/RestoreSession$RestoreObserverWrapper$1

/* anonymous class */
    class RestoreObserverWrapper._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 4: default 36
        //                       1 37
        //                       2 54
        //                       3 78
        //                       4 95;
               goto _L1 _L2 _L3 _L4 _L5
_L1:
            return;
_L2:
            mAppObserver.restoreStarting(message.arg1);
            continue; /* Loop/switch isn't completed */
_L3:
            mAppObserver.onUpdate(message.arg1, (String)message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            mAppObserver.restoreFinished(message.arg1);
            continue; /* Loop/switch isn't completed */
_L5:
            mAppObserver.restoreSetsAvailable((RestoreSet[])message.obj);
            if(true) goto _L1; else goto _L6
_L6:
        }

        final RestoreObserverWrapper this$1;

            
            {
                this$1 = RestoreObserverWrapper.this;
                super(looper);
            }
    }

}
