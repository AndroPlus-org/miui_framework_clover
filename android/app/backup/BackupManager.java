// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app.backup;

import android.content.ComponentName;
import android.content.Context;
import android.os.*;
import android.util.Log;
import android.util.Pair;

// Referenced classes of package android.app.backup:
//            IBackupManager, RestoreSession, BackupObserver, BackupManagerMonitor, 
//            RestoreObserver, SelectBackupTransportCallback, BackupProgress

public class BackupManager
{
    private class BackupManagerMonitorWrapper extends IBackupManagerMonitor.Stub
    {

        public void onEvent(Bundle bundle)
            throws RemoteException
        {
            mMonitor.onEvent(bundle);
        }

        final BackupManagerMonitor mMonitor;
        final BackupManager this$0;

        BackupManagerMonitorWrapper(BackupManagerMonitor backupmanagermonitor)
        {
            this$0 = BackupManager.this;
            super();
            mMonitor = backupmanagermonitor;
        }
    }

    private class BackupObserverWrapper extends IBackupObserver.Stub
    {

        public void backupFinished(int i)
        {
            mHandler.sendMessage(mHandler.obtainMessage(3, i, 0));
        }

        public void onResult(String s, int i)
        {
            mHandler.sendMessage(mHandler.obtainMessage(2, i, 0, s));
        }

        public void onUpdate(String s, BackupProgress backupprogress)
        {
            mHandler.sendMessage(mHandler.obtainMessage(1, Pair.create(s, backupprogress)));
        }

        static final int MSG_FINISHED = 3;
        static final int MSG_RESULT = 2;
        static final int MSG_UPDATE = 1;
        final Handler mHandler;
        final BackupObserver mObserver;
        final BackupManager this$0;

        BackupObserverWrapper(Context context, BackupObserver backupobserver)
        {
            this$0 = BackupManager.this;
            super();
            mHandler = new _cls1(context.getMainLooper());
            mObserver = backupobserver;
        }
    }

    private class SelectTransportListenerWrapper extends ISelectBackupTransportCallback.Stub
    {

        static SelectBackupTransportCallback _2D_get0(SelectTransportListenerWrapper selecttransportlistenerwrapper)
        {
            return selecttransportlistenerwrapper.mListener;
        }

        public void onFailure(int i)
        {
            mHandler.post(i. new Runnable() {

                public void run()
                {
                    SelectTransportListenerWrapper._2D_get0(SelectTransportListenerWrapper.this).onFailure(reason);
                }

                final SelectTransportListenerWrapper this$1;
                final int val$reason;

            
            {
                this$1 = final_selecttransportlistenerwrapper;
                reason = I.this;
                super();
            }
            }
);
        }

        public void onSuccess(String s)
        {
            mHandler.post(s. new Runnable() {

                public void run()
                {
                    SelectTransportListenerWrapper._2D_get0(SelectTransportListenerWrapper.this).onSuccess(transportName);
                }

                final SelectTransportListenerWrapper this$1;
                final String val$transportName;

            
            {
                this$1 = final_selecttransportlistenerwrapper;
                transportName = String.this;
                super();
            }
            }
);
        }

        private final Handler mHandler;
        private final SelectBackupTransportCallback mListener;
        final BackupManager this$0;

        SelectTransportListenerWrapper(Context context, SelectBackupTransportCallback selectbackuptransportcallback)
        {
            this$0 = BackupManager.this;
            super();
            mHandler = new Handler(context.getMainLooper());
            mListener = selectbackuptransportcallback;
        }
    }


    public BackupManager(Context context)
    {
        mContext = context;
    }

    private static void checkServiceBinder()
    {
        if(sService == null)
            sService = IBackupManager.Stub.asInterface(ServiceManager.getService("backup"));
    }

    public static void dataChanged(String s)
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_18;
        sService.dataChanged(s);
_L1:
        return;
        s;
        Log.e("BackupManager", "dataChanged(pkg) couldn't connect");
          goto _L1
    }

    public void backupNow()
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_17;
        sService.backupNow();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BackupManager", "backupNow() couldn't connect");
          goto _L1
    }

    public RestoreSession beginRestoreSession()
    {
        Object obj;
        Object obj1;
        obj = null;
        checkServiceBinder();
        obj1 = obj;
        if(sService == null)
            break MISSING_BLOCK_LABEL_43;
        IRestoreSession irestoresession = sService.beginRestoreSession(null, null);
        obj1 = obj;
        if(irestoresession != null)
            try
            {
                obj1 = JVM INSTR new #111 <Class RestoreSession>;
                ((RestoreSession) (obj1)).RestoreSession(mContext, irestoresession);
            }
            catch(RemoteException remoteexception)
            {
                Log.e("BackupManager", "beginRestoreSession() couldn't connect");
                remoteexception = obj;
            }
        return ((RestoreSession) (obj1));
    }

    public void cancelBackups()
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_17;
        sService.cancelBackups();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BackupManager", "cancelBackups() couldn't connect.");
          goto _L1
    }

    public void dataChanged()
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_24;
        sService.dataChanged(mContext.getPackageName());
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.d("BackupManager", "dataChanged() couldn't connect");
          goto _L1
    }

    public long getAvailableRestoreToken(String s)
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_30;
        long l = sService.getAvailableRestoreToken(s);
        return l;
        s;
        Log.e("BackupManager", "getAvailableRestoreToken() couldn't connect");
        return 0L;
    }

    public String getCurrentTransport()
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_29;
        String s = sService.getCurrentTransport();
        return s;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BackupManager", "getCurrentTransport() couldn't connect");
        return null;
    }

    public boolean isAppEligibleForBackup(String s)
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_30;
        boolean flag = sService.isAppEligibleForBackup(s);
        return flag;
        s;
        Log.e("BackupManager", "isAppEligibleForBackup(pkg) couldn't connect");
        return false;
    }

    public boolean isBackupEnabled()
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_29;
        boolean flag = sService.isBackupEnabled();
        return flag;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BackupManager", "isBackupEnabled() couldn't connect");
        return false;
    }

    public String[] listAllTransports()
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_29;
        String as[] = sService.listAllTransports();
        return as;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BackupManager", "listAllTransports() couldn't connect");
        return null;
    }

    public int requestBackup(String as[], BackupObserver backupobserver)
    {
        return requestBackup(as, backupobserver, null, 0);
    }

    public int requestBackup(String as[], BackupObserver backupobserver, BackupManagerMonitor backupmanagermonitor, int i)
    {
        checkServiceBinder();
        if(sService == null) goto _L2; else goto _L1
_L1:
        if(backupobserver != null) goto _L4; else goto _L3
_L3:
        backupobserver = null;
_L9:
        if(backupmanagermonitor != null) goto _L6; else goto _L5
_L5:
        backupmanagermonitor = null;
_L7:
        return sService.requestBackup(as, backupobserver, backupmanagermonitor, i);
_L4:
        backupobserver = new BackupObserverWrapper(mContext, backupobserver);
        continue; /* Loop/switch isn't completed */
_L6:
        backupmanagermonitor = new BackupManagerMonitorWrapper(backupmanagermonitor);
          goto _L7
        as;
        Log.e("BackupManager", "requestBackup() couldn't connect");
_L2:
        return -1;
        if(true) goto _L9; else goto _L8
_L8:
    }

    public int requestRestore(RestoreObserver restoreobserver)
    {
        return requestRestore(restoreobserver, null);
    }

    public int requestRestore(RestoreObserver restoreobserver, BackupManagerMonitor backupmanagermonitor)
    {
        byte byte0;
        int i;
        Object obj;
        Object obj1;
        RestoreSession restoresession;
        Object obj2;
        byte0 = -1;
        checkServiceBinder();
        i = byte0;
        if(sService == null)
            break MISSING_BLOCK_LABEL_119;
        obj = null;
        obj1 = null;
        restoresession = null;
        obj2 = obj1;
        IRestoreSession irestoresession = sService.beginRestoreSession(mContext.getPackageName(), null);
        int j;
        j = byte0;
        obj2 = restoresession;
        if(irestoresession == null)
            break MISSING_BLOCK_LABEL_101;
        obj2 = obj1;
        restoresession = JVM INSTR new #111 <Class RestoreSession>;
        obj2 = obj1;
        restoresession.RestoreSession(mContext, irestoresession);
        j = restoresession.restorePackage(mContext.getPackageName(), restoreobserver, backupmanagermonitor);
        obj2 = restoresession;
        i = j;
        if(obj2 != null)
        {
            ((RestoreSession) (obj2)).endRestoreSession();
            i = j;
        }
_L1:
        return i;
        restoreobserver;
        restoreobserver = obj;
_L4:
        obj2 = restoreobserver;
        Log.e("BackupManager", "restoreSelf() unable to contact service");
        i = byte0;
        if(restoreobserver != null)
        {
            restoreobserver.endRestoreSession();
            i = byte0;
        }
          goto _L1
        restoreobserver;
_L3:
        if(obj2 != null)
            ((RestoreSession) (obj2)).endRestoreSession();
        throw restoreobserver;
        restoreobserver;
        obj2 = restoresession;
        if(true) goto _L3; else goto _L2
_L2:
        restoreobserver;
        restoreobserver = restoresession;
          goto _L4
    }

    public String selectBackupTransport(String s)
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_30;
        s = sService.selectBackupTransport(s);
        return s;
        s;
        Log.e("BackupManager", "selectBackupTransport() couldn't connect");
        return null;
    }

    public void selectBackupTransport(ComponentName componentname, SelectBackupTransportCallback selectbackuptransportcallback)
    {
        checkServiceBinder();
        if(sService == null) goto _L2; else goto _L1
_L1:
        if(selectbackuptransportcallback != null) goto _L4; else goto _L3
_L3:
        selectbackuptransportcallback = null;
_L5:
        sService.selectBackupTransportAsync(componentname, selectbackuptransportcallback);
_L2:
        return;
_L4:
        selectbackuptransportcallback = new SelectTransportListenerWrapper(mContext, selectbackuptransportcallback);
          goto _L5
        componentname;
        Log.e("BackupManager", "selectBackupTransportAsync() couldn't connect");
          goto _L2
    }

    public void setAutoRestore(boolean flag)
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_18;
        sService.setAutoRestore(flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BackupManager", "setAutoRestore() couldn't connect");
          goto _L1
    }

    public void setBackupEnabled(boolean flag)
    {
        checkServiceBinder();
        if(sService == null)
            break MISSING_BLOCK_LABEL_18;
        sService.setBackupEnabled(flag);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        Log.e("BackupManager", "setBackupEnabled() couldn't connect");
          goto _L1
    }

    public static final int ERROR_AGENT_FAILURE = -1003;
    public static final int ERROR_BACKUP_CANCELLED = -2003;
    public static final int ERROR_BACKUP_NOT_ALLOWED = -2001;
    public static final int ERROR_PACKAGE_NOT_FOUND = -2002;
    public static final int ERROR_TRANSPORT_ABORTED = -1000;
    public static final int ERROR_TRANSPORT_INVALID = -2;
    public static final int ERROR_TRANSPORT_PACKAGE_REJECTED = -1002;
    public static final int ERROR_TRANSPORT_QUOTA_EXCEEDED = -1005;
    public static final int ERROR_TRANSPORT_UNAVAILABLE = -1;
    public static final String EXTRA_BACKUP_SERVICES_AVAILABLE = "backup_services_available";
    public static final int FLAG_NON_INCREMENTAL_BACKUP = 1;
    public static final String PACKAGE_MANAGER_SENTINEL = "@pm@";
    public static final int SUCCESS = 0;
    private static final String TAG = "BackupManager";
    private static IBackupManager sService;
    private Context mContext;

    // Unreferenced inner class android/app/backup/BackupManager$BackupObserverWrapper$1

/* anonymous class */
    class BackupObserverWrapper._cls1 extends Handler
    {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 3: default 32
        //                       1 58
        //                       2 93
        //                       3 117;
               goto _L1 _L2 _L3 _L4
_L1:
            Log.w("BackupManager", (new StringBuilder()).append("Unknown message: ").append(message).toString());
_L6:
            return;
_L2:
            message = (Pair)message.obj;
            mObserver.onUpdate((String)((Pair) (message)).first, (BackupProgress)((Pair) (message)).second);
            continue; /* Loop/switch isn't completed */
_L3:
            mObserver.onResult((String)message.obj, message.arg1);
            continue; /* Loop/switch isn't completed */
_L4:
            mObserver.backupFinished(message.arg1);
            if(true) goto _L6; else goto _L5
_L5:
        }

        final BackupObserverWrapper this$1;

            
            {
                this$1 = BackupObserverWrapper.this;
                super(looper);
            }
    }

}
