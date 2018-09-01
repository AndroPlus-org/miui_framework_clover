// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.app.Activity;
import android.app.Application;
import android.content.ContentProvider;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import android.view.Window;
import java.util.*;

// Referenced classes of package android.nfc:
//            NfcEvent, BeamShareData, NfcAdapter, INfcAdapter, 
//            Tag, NdefMessage

public final class NfcActivityManager extends IAppCallback.Stub
    implements android.app.Application.ActivityLifecycleCallbacks
{
    class NfcActivityState
    {

        public void destroy()
        {
            unregisterApplication(activity.getApplication());
            resumed = false;
            activity = null;
            ndefMessage = null;
            ndefMessageCallback = null;
            onNdefPushCompleteCallback = null;
            uriCallback = null;
            uris = null;
            readerModeFlags = 0;
            token = null;
        }

        public String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder("[")).append(" ");
            stringbuilder.append(ndefMessage).append(" ").append(ndefMessageCallback).append(" ");
            stringbuilder.append(uriCallback).append(" ");
            if(uris != null)
            {
                Uri auri[] = uris;
                int i = 0;
                for(int j = auri.length; i < j; i++)
                {
                    Uri uri = auri[i];
                    stringbuilder.append(onNdefPushCompleteCallback).append(" ").append(uri).append("]");
                }

            }
            return stringbuilder.toString();
        }

        Activity activity;
        int flags;
        NdefMessage ndefMessage;
        NfcAdapter.CreateNdefMessageCallback ndefMessageCallback;
        NfcAdapter.OnNdefPushCompleteCallback onNdefPushCompleteCallback;
        NfcAdapter.ReaderCallback readerCallback;
        Bundle readerModeExtras;
        int readerModeFlags;
        boolean resumed;
        final NfcActivityManager this$0;
        Binder token;
        NfcAdapter.CreateBeamUrisCallback uriCallback;
        Uri uris[];

        public NfcActivityState(Activity activity1)
        {
            this$0 = NfcActivityManager.this;
            super();
            resumed = false;
            ndefMessage = null;
            ndefMessageCallback = null;
            onNdefPushCompleteCallback = null;
            uriCallback = null;
            uris = null;
            flags = 0;
            readerModeFlags = 0;
            readerCallback = null;
            readerModeExtras = null;
            if(activity1.getWindow().isDestroyed())
            {
                throw new IllegalStateException("activity is already destroyed");
            } else
            {
                resumed = activity1.isResumed();
                activity = activity1;
                token = new Binder();
                registerApplication(activity1.getApplication());
                return;
            }
        }
    }

    class NfcApplicationState
    {

        public void register()
        {
            refCount = refCount + 1;
            if(refCount == 1)
                app.registerActivityLifecycleCallbacks(NfcActivityManager.this);
        }

        public void unregister()
        {
            refCount = refCount - 1;
            if(refCount != 0) goto _L2; else goto _L1
_L1:
            app.unregisterActivityLifecycleCallbacks(NfcActivityManager.this);
_L4:
            return;
_L2:
            if(refCount < 0)
                Log.e("NFC", (new StringBuilder()).append("-ve refcount for ").append(app).toString());
            if(true) goto _L4; else goto _L3
_L3:
        }

        final Application app;
        int refCount;
        final NfcActivityManager this$0;

        public NfcApplicationState(Application application)
        {
            this$0 = NfcActivityManager.this;
            super();
            refCount = 0;
            app = application;
        }
    }


    public NfcActivityManager(NfcAdapter nfcadapter)
    {
        mAdapter = nfcadapter;
    }

    public BeamShareData createBeamShareData(byte byte0)
    {
        NfcEvent nfcevent = new NfcEvent(mAdapter, byte0);
        this;
        JVM INSTR monitorenter ;
        Object obj = findResumedActivityState();
        if(obj != null)
            break MISSING_BLOCK_LABEL_28;
        this;
        JVM INSTR monitorexit ;
        return null;
        Object obj1;
        Object obj2;
        NdefMessage ndefmessage;
        Object obj3;
        int i;
        obj1 = ((NfcActivityState) (obj)).ndefMessageCallback;
        obj2 = ((NfcActivityState) (obj)).uriCallback;
        ndefmessage = ((NfcActivityState) (obj)).ndefMessage;
        obj3 = ((NfcActivityState) (obj)).uris;
        i = ((NfcActivityState) (obj)).flags;
        obj = ((NfcActivityState) (obj)).activity;
        this;
        JVM INSTR monitorexit ;
        long l;
        l = Binder.clearCallingIdentity();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_85;
        ndefmessage = ((NfcAdapter.CreateNdefMessageCallback) (obj1)).createNdefMessage(nfcevent);
        if(obj2 == null)
            break MISSING_BLOCK_LABEL_252;
        Uri auri[] = ((NfcAdapter.CreateBeamUrisCallback) (obj2)).createBeamUris(nfcevent);
        obj3 = auri;
        if(auri == null)
            break MISSING_BLOCK_LABEL_252;
        obj2 = JVM INSTR new #49  <Class ArrayList>;
        ((ArrayList) (obj2)).ArrayList();
        int j = 0;
        int k = auri.length;
_L2:
        if(j >= k)
            break MISSING_BLOCK_LABEL_234;
        obj1 = auri[j];
        if(obj1 != null)
            break; /* Loop/switch isn't completed */
        Log.e("NFC", "Uri not allowed to be null.");
_L3:
        j++;
        if(true) goto _L2; else goto _L1
        obj3;
        throw obj3;
_L1:
        obj3 = ((Uri) (obj1)).getScheme();
        if(obj3 == null)
            break MISSING_BLOCK_LABEL_196;
        if(((String) (obj3)).equalsIgnoreCase("file") || !(((String) (obj3)).equalsIgnoreCase("content") ^ true))
            break MISSING_BLOCK_LABEL_217;
        Log.e("NFC", "Uri needs to have either scheme file or scheme content");
          goto _L3
        obj3;
        Binder.restoreCallingIdentity(l);
        throw obj3;
        ((ArrayList) (obj2)).add(ContentProvider.maybeAddUserId(((Uri) (obj1)), UserHandle.myUserId()));
          goto _L3
        obj3 = (Uri[])((ArrayList) (obj2)).toArray(new Uri[((ArrayList) (obj2)).size()]);
        if(obj3 == null) goto _L5; else goto _L4
_L4:
        if(obj3.length <= 0) goto _L5; else goto _L6
_L6:
        j = 0;
        k = obj3.length;
_L7:
        if(j >= k)
            break; /* Loop/switch isn't completed */
        ((Activity) (obj)).grantUriPermission("com.android.nfc", obj3[j], 1);
        j++;
        if(true) goto _L7; else goto _L5
_L5:
        Binder.restoreCallingIdentity(l);
        return new BeamShareData(ndefmessage, ((Uri []) (obj3)), new UserHandle(UserHandle.myUserId()), i);
    }

    void destroyActivityState(Activity activity)
    {
        this;
        JVM INSTR monitorenter ;
        activity = findActivityState(activity);
        if(activity == null)
            break MISSING_BLOCK_LABEL_27;
        activity.destroy();
        mActivities.remove(activity);
        this;
        JVM INSTR monitorexit ;
        return;
        activity;
        throw activity;
    }

    public void disableReaderMode(Activity activity)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        NfcActivityState nfcactivitystate = getActivityState(activity);
        nfcactivitystate.readerCallback = null;
        nfcactivitystate.readerModeFlags = 0;
        nfcactivitystate.readerModeExtras = null;
        activity = nfcactivitystate.token;
        flag = nfcactivitystate.resumed;
        this;
        JVM INSTR monitorexit ;
        if(flag)
            setReaderMode(activity, 0, null);
        return;
        activity;
        throw activity;
    }

    public void enableReaderMode(Activity activity, NfcAdapter.ReaderCallback readercallback, int i, Bundle bundle)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        activity = getActivityState(activity);
        activity.readerCallback = readercallback;
        activity.readerModeFlags = i;
        activity.readerModeExtras = bundle;
        readercallback = ((NfcActivityState) (activity)).token;
        flag = ((NfcActivityState) (activity)).resumed;
        this;
        JVM INSTR monitorexit ;
        if(flag)
            setReaderMode(readercallback, i, bundle);
        return;
        activity;
        throw activity;
    }

    NfcActivityState findActivityState(Activity activity)
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator = mActivities.iterator();
        NfcActivityState nfcactivitystate;
        Activity activity1;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_47;
            nfcactivitystate = (NfcActivityState)iterator.next();
            activity1 = nfcactivitystate.activity;
        } while(activity1 != activity);
        this;
        JVM INSTR monitorexit ;
        return nfcactivitystate;
        this;
        JVM INSTR monitorexit ;
        return null;
        activity;
        throw activity;
    }

    NfcApplicationState findAppState(Application application)
    {
        for(Iterator iterator = mApps.iterator(); iterator.hasNext();)
        {
            NfcApplicationState nfcapplicationstate = (NfcApplicationState)iterator.next();
            if(nfcapplicationstate.app == application)
                return nfcapplicationstate;
        }

        return null;
    }

    NfcActivityState findResumedActivityState()
    {
        this;
        JVM INSTR monitorenter ;
        Iterator iterator = mActivities.iterator();
        NfcActivityState nfcactivitystate;
        boolean flag;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_44;
            nfcactivitystate = (NfcActivityState)iterator.next();
            flag = nfcactivitystate.resumed;
        } while(!flag);
        this;
        JVM INSTR monitorexit ;
        return nfcactivitystate;
        this;
        JVM INSTR monitorexit ;
        return null;
        Exception exception;
        exception;
        throw exception;
    }

    NfcActivityState getActivityState(Activity activity)
    {
        this;
        JVM INSTR monitorenter ;
        NfcActivityState nfcactivitystate = findActivityState(activity);
        NfcActivityState nfcactivitystate1;
        nfcactivitystate1 = nfcactivitystate;
        if(nfcactivitystate != null)
            break MISSING_BLOCK_LABEL_35;
        nfcactivitystate1 = JVM INSTR new #8   <Class NfcActivityManager$NfcActivityState>;
        nfcactivitystate1.this. NfcActivityState(activity);
        mActivities.add(nfcactivitystate1);
        this;
        JVM INSTR monitorexit ;
        return nfcactivitystate1;
        activity;
        throw activity;
    }

    public void onActivityCreated(Activity activity, Bundle bundle)
    {
    }

    public void onActivityDestroyed(Activity activity)
    {
        this;
        JVM INSTR monitorenter ;
        NfcActivityState nfcactivitystate;
        nfcactivitystate = findActivityState(activity);
        if(DBG.booleanValue())
        {
            StringBuilder stringbuilder = JVM INSTR new #253 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("NFC", stringbuilder.append("onDestroy() for ").append(activity).append(" ").append(nfcactivitystate).toString());
        }
        if(nfcactivitystate == null)
            break MISSING_BLOCK_LABEL_64;
        destroyActivityState(activity);
        this;
        JVM INSTR monitorexit ;
        return;
        activity;
        throw activity;
    }

    public void onActivityPaused(Activity activity)
    {
        this;
        JVM INSTR monitorenter ;
        NfcActivityState nfcactivitystate;
        nfcactivitystate = findActivityState(activity);
        if(DBG.booleanValue())
        {
            StringBuilder stringbuilder = JVM INSTR new #253 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("NFC", stringbuilder.append("onPause() for ").append(activity).append(" ").append(nfcactivitystate).toString());
        }
        if(nfcactivitystate != null)
            break MISSING_BLOCK_LABEL_62;
        this;
        JVM INSTR monitorexit ;
        return;
        int i;
        nfcactivitystate.resumed = false;
        activity = nfcactivitystate.token;
        i = nfcactivitystate.readerModeFlags;
        boolean flag;
        if(i != 0)
            flag = true;
        else
            flag = false;
        this;
        JVM INSTR monitorexit ;
        if(flag)
            setReaderMode(activity, 0, null);
        return;
        activity;
        throw activity;
    }

    public void onActivityResumed(Activity activity)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        obj = findActivityState(activity);
        if(DBG.booleanValue())
        {
            StringBuilder stringbuilder = JVM INSTR new #253 <Class StringBuilder>;
            stringbuilder.StringBuilder();
            Log.d("NFC", stringbuilder.append("onResume() for ").append(activity).append(" ").append(obj).toString());
        }
        if(obj != null)
            break MISSING_BLOCK_LABEL_62;
        this;
        JVM INSTR monitorexit ;
        return;
        int i;
        obj.resumed = true;
        activity = ((NfcActivityState) (obj)).token;
        i = ((NfcActivityState) (obj)).readerModeFlags;
        obj = ((NfcActivityState) (obj)).readerModeExtras;
        this;
        JVM INSTR monitorexit ;
        if(i != 0)
            setReaderMode(activity, i, ((Bundle) (obj)));
        requestNfcServiceCallback();
        return;
        activity;
        throw activity;
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle)
    {
    }

    public void onActivityStarted(Activity activity)
    {
    }

    public void onActivityStopped(Activity activity)
    {
    }

    public void onNdefPushComplete(byte byte0)
    {
        this;
        JVM INSTR monitorenter ;
        NfcActivityState nfcactivitystate = findResumedActivityState();
        if(nfcactivitystate != null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        NfcAdapter.OnNdefPushCompleteCallback onndefpushcompletecallback = nfcactivitystate.onNdefPushCompleteCallback;
        this;
        JVM INSTR monitorexit ;
        NfcEvent nfcevent = new NfcEvent(mAdapter, byte0);
        if(onndefpushcompletecallback != null)
            onndefpushcompletecallback.onNdefPushComplete(nfcevent);
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onTagDiscovered(Tag tag)
        throws RemoteException
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = findResumedActivityState();
        if(obj != null)
            break MISSING_BLOCK_LABEL_14;
        this;
        JVM INSTR monitorexit ;
        return;
        obj = ((NfcActivityState) (obj)).readerCallback;
        this;
        JVM INSTR monitorexit ;
        if(obj != null)
            ((NfcAdapter.ReaderCallback) (obj)).onTagDiscovered(tag);
        return;
        tag;
        throw tag;
    }

    void registerApplication(Application application)
    {
        NfcApplicationState nfcapplicationstate = findAppState(application);
        NfcApplicationState nfcapplicationstate1 = nfcapplicationstate;
        if(nfcapplicationstate == null)
        {
            nfcapplicationstate1 = new NfcApplicationState(application);
            mApps.add(nfcapplicationstate1);
        }
        nfcapplicationstate1.register();
    }

    void requestNfcServiceCallback()
    {
        NfcAdapter.sService.setAppCallback(this);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        mAdapter.attemptDeadServiceRecovery(remoteexception);
          goto _L1
    }

    public void setNdefPushContentUri(Activity activity, Uri auri[])
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        activity = getActivityState(activity);
        activity.uris = auri;
        flag = ((NfcActivityState) (activity)).resumed;
        this;
        JVM INSTR monitorexit ;
        if(flag)
            requestNfcServiceCallback();
        else
            verifyNfcPermission();
        return;
        activity;
        throw activity;
    }

    public void setNdefPushContentUriCallback(Activity activity, NfcAdapter.CreateBeamUrisCallback createbeamuriscallback)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        activity = getActivityState(activity);
        activity.uriCallback = createbeamuriscallback;
        flag = ((NfcActivityState) (activity)).resumed;
        this;
        JVM INSTR monitorexit ;
        if(flag)
            requestNfcServiceCallback();
        else
            verifyNfcPermission();
        return;
        activity;
        throw activity;
    }

    public void setNdefPushMessage(Activity activity, NdefMessage ndefmessage, int i)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        activity = getActivityState(activity);
        activity.ndefMessage = ndefmessage;
        activity.flags = i;
        flag = ((NfcActivityState) (activity)).resumed;
        this;
        JVM INSTR monitorexit ;
        if(flag)
            requestNfcServiceCallback();
        else
            verifyNfcPermission();
        return;
        activity;
        throw activity;
    }

    public void setNdefPushMessageCallback(Activity activity, NfcAdapter.CreateNdefMessageCallback createndefmessagecallback, int i)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        activity = getActivityState(activity);
        activity.ndefMessageCallback = createndefmessagecallback;
        activity.flags = i;
        flag = ((NfcActivityState) (activity)).resumed;
        this;
        JVM INSTR monitorexit ;
        if(flag)
            requestNfcServiceCallback();
        else
            verifyNfcPermission();
        return;
        activity;
        throw activity;
    }

    public void setOnNdefPushCompleteCallback(Activity activity, NfcAdapter.OnNdefPushCompleteCallback onndefpushcompletecallback)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        activity = getActivityState(activity);
        activity.onNdefPushCompleteCallback = onndefpushcompletecallback;
        flag = ((NfcActivityState) (activity)).resumed;
        this;
        JVM INSTR monitorexit ;
        if(flag)
            requestNfcServiceCallback();
        else
            verifyNfcPermission();
        return;
        activity;
        throw activity;
    }

    public void setReaderMode(Binder binder, int i, Bundle bundle)
    {
        if(DBG.booleanValue())
            Log.d("NFC", "Setting reader mode");
        NfcAdapter.sService.setReaderMode(binder, this, i, bundle);
_L1:
        return;
        binder;
        mAdapter.attemptDeadServiceRecovery(binder);
          goto _L1
    }

    void unregisterApplication(Application application)
    {
        NfcApplicationState nfcapplicationstate = findAppState(application);
        if(nfcapplicationstate == null)
        {
            Log.e("NFC", (new StringBuilder()).append("app was not registered ").append(application).toString());
            return;
        } else
        {
            nfcapplicationstate.unregister();
            return;
        }
    }

    void verifyNfcPermission()
    {
        NfcAdapter.sService.verifyNfcPermission();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        mAdapter.attemptDeadServiceRecovery(remoteexception);
          goto _L1
    }

    static final Boolean DBG = Boolean.valueOf(false);
    static final String TAG = "NFC";
    final List mActivities = new LinkedList();
    final NfcAdapter mAdapter;
    final List mApps = new ArrayList(1);

}
