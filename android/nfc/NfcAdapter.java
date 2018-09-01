// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.nfc;

import android.app.*;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.net.Uri;
import android.os.*;
import android.util.Log;
import java.util.HashMap;

// Referenced classes of package android.nfc:
//            NfcActivityManager, NfcManager, INfcAdapter, INfcUnlockHandler, 
//            Tag, TechListParcel, INfcCardEmulation, INfcFCardEmulation, 
//            INfcTag, ITagRemovedCallback, NdefMessage, INfcAdapterExtras, 
//            INfcDta, BeamShareData, NfcEvent

public final class NfcAdapter
{
    public static interface CreateBeamUrisCallback
    {

        public abstract Uri[] createBeamUris(NfcEvent nfcevent);
    }

    public static interface CreateNdefMessageCallback
    {

        public abstract NdefMessage createNdefMessage(NfcEvent nfcevent);
    }

    public static interface NfcUnlockHandler
    {

        public abstract boolean onUnlockAttempted(Tag tag);
    }

    public static interface OnNdefPushCompleteCallback
    {

        public abstract void onNdefPushComplete(NfcEvent nfcevent);
    }

    public static interface OnTagRemovedListener
    {

        public abstract void onTagRemoved();
    }

    public static interface ReaderCallback
    {

        public abstract void onTagDiscovered(Tag tag);
    }


    NfcAdapter(Context context)
    {
        mForegroundDispatchListener = new OnActivityPausedListener() {

            public void onPaused(Activity activity)
            {
                disableForegroundDispatchInternal(activity, true);
            }

            final NfcAdapter this$0;

            
            {
                this$0 = NfcAdapter.this;
                super();
            }
        }
;
        mContext = context;
        mTagRemovedListener = null;
    }

    public static NfcAdapter getDefaultAdapter()
    {
        Log.w("NFC", "WARNING: NfcAdapter.getDefaultAdapter() is deprecated, use NfcAdapter.getDefaultAdapter(Context) instead", new Exception());
        return getNfcAdapter(null);
    }

    public static NfcAdapter getDefaultAdapter(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context cannot be null");
        context = context.getApplicationContext();
        if(context == null)
            throw new IllegalArgumentException("context not associated with any application (using a mock context?)");
        context = (NfcManager)context.getSystemService("nfc");
        if(context == null)
            return null;
        else
            return context.getDefaultAdapter();
    }

    public static NfcAdapter getNfcAdapter(Context context)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        boolean flag;
        if(sIsInitialized)
            break MISSING_BLOCK_LABEL_134;
        sHasNfcFeature = hasNfcFeature();
        flag = hasNfcHceFeature();
        if(sHasNfcFeature || !(flag ^ true))
            break MISSING_BLOCK_LABEL_55;
        Log.v("NFC", "this device does not have NFC support");
        context = JVM INSTR new #223 <Class UnsupportedOperationException>;
        context.UnsupportedOperationException();
        throw context;
        context;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw context;
        boolean flag1;
        sService = getServiceInterface();
        if(sService == null)
        {
            Log.e("NFC", "could not retrieve NFC service");
            context = JVM INSTR new #223 <Class UnsupportedOperationException>;
            context.UnsupportedOperationException();
            throw context;
        }
        flag1 = sHasNfcFeature;
        if(!flag1)
            break MISSING_BLOCK_LABEL_104;
        sTagService = sService.getNfcTagInterface();
        if(!flag)
            break MISSING_BLOCK_LABEL_130;
        sNfcFCardEmulationService = sService.getNfcFCardEmulationInterface();
        sCardEmulationService = sService.getNfcCardEmulationInterface();
        sIsInitialized = true;
        if(context != null)
            break MISSING_BLOCK_LABEL_226;
        if(sNullContextNfcAdapter == null)
        {
            context = JVM INSTR new #2   <Class NfcAdapter>;
            context.NfcAdapter(null);
            sNullContextNfcAdapter = context;
        }
        context = sNullContextNfcAdapter;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        return context;
        context;
        Log.e("NFC", "could not retrieve NFC Tag service");
        context = JVM INSTR new #223 <Class UnsupportedOperationException>;
        context.UnsupportedOperationException();
        throw context;
        context;
        Log.e("NFC", "could not retrieve NFC-F card emulation service");
        context = JVM INSTR new #223 <Class UnsupportedOperationException>;
        context.UnsupportedOperationException();
        throw context;
        context;
        Log.e("NFC", "could not retrieve card emulation service");
        context = JVM INSTR new #223 <Class UnsupportedOperationException>;
        context.UnsupportedOperationException();
        throw context;
        NfcAdapter nfcadapter = (NfcAdapter)sNfcAdapters.get(context);
        NfcAdapter nfcadapter1;
        nfcadapter1 = nfcadapter;
        if(nfcadapter != null)
            break MISSING_BLOCK_LABEL_265;
        nfcadapter1 = JVM INSTR new #2   <Class NfcAdapter>;
        nfcadapter1.NfcAdapter(context);
        sNfcAdapters.put(context, nfcadapter1);
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        return nfcadapter1;
    }

    private static INfcAdapter getServiceInterface()
    {
        android.os.IBinder ibinder = ServiceManager.getService("nfc");
        if(ibinder == null)
            return null;
        else
            return INfcAdapter.Stub.asInterface(ibinder);
    }

    private static boolean hasNfcFeature()
    {
        IPackageManager ipackagemanager = ActivityThread.getPackageManager();
        if(ipackagemanager == null)
        {
            Log.e("NFC", "Cannot get package manager, assuming no NFC feature");
            return false;
        }
        boolean flag;
        try
        {
            flag = ipackagemanager.hasSystemFeature("android.hardware.nfc", 0);
        }
        catch(RemoteException remoteexception)
        {
            Log.e("NFC", "Package manager query failed, assuming no NFC feature", remoteexception);
            return false;
        }
        return flag;
    }

    private static boolean hasNfcHceFeature()
    {
        IPackageManager ipackagemanager;
        ipackagemanager = ActivityThread.getPackageManager();
        if(ipackagemanager == null)
        {
            Log.e("NFC", "Cannot get package manager, assuming no NFC feature");
            return false;
        }
        if(ipackagemanager.hasSystemFeature("android.hardware.nfc.hce", 0)) goto _L2; else goto _L1
_L1:
        boolean flag = ipackagemanager.hasSystemFeature("android.hardware.nfc.hcef", 0);
_L4:
        return flag;
_L2:
        flag = true;
        if(true) goto _L4; else goto _L3
_L3:
        RemoteException remoteexception;
        remoteexception;
        Log.e("NFC", "Package manager query failed, assuming no NFC feature", remoteexception);
        return false;
    }

    public boolean addNfcUnlockHandler(NfcUnlockHandler nfcunlockhandler, String as[])
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            nfcunlockhandler = JVM INSTR new #223 <Class UnsupportedOperationException>;
            nfcunlockhandler.UnsupportedOperationException();
            throw nfcunlockhandler;
        }
        break MISSING_BLOCK_LABEL_25;
        nfcunlockhandler;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw nfcunlockhandler;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        if(as.length == 0)
            return false;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mNfcUnlockHandlers.containsKey(nfcunlockhandler))
        {
            sService.removeNfcUnlockHandler((INfcUnlockHandler)mNfcUnlockHandlers.get(nfcunlockhandler));
            mNfcUnlockHandlers.remove(nfcunlockhandler);
        }
        INfcUnlockHandler.Stub stub = JVM INSTR new #12  <Class NfcAdapter$3>;
        stub.this. _cls3();
        sService.addNfcUnlockHandler(stub, Tag.getTechCodesFromStrings(as));
        mNfcUnlockHandlers.put(nfcunlockhandler, stub);
        obj;
        JVM INSTR monitorexit ;
        return true;
        nfcunlockhandler;
        obj;
        JVM INSTR monitorexit ;
        throw nfcunlockhandler;
        nfcunlockhandler;
        attemptDeadServiceRecovery(nfcunlockhandler);
        return false;
        nfcunlockhandler;
        Log.e("NFC", "Unable to register LockscreenDispatch", nfcunlockhandler);
        return false;
    }

    public void attemptDeadServiceRecovery(Exception exception)
    {
        Log.e("NFC", "NFC service dead - attempting to recover", exception);
        INfcAdapter infcadapter = getServiceInterface();
        if(infcadapter == null)
        {
            Log.e("NFC", "could not retrieve NFC service during service recovery");
            return;
        }
        sService = infcadapter;
        try
        {
            sTagService = infcadapter.getNfcTagInterface();
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            Log.e("NFC", "could not retrieve NFC tag service during service recovery");
            return;
        }
        try
        {
            sCardEmulationService = infcadapter.getNfcCardEmulationInterface();
        }
        // Misplaced declaration of an exception variable
        catch(Exception exception)
        {
            Log.e("NFC", "could not retrieve NFC card emulation service during service recovery");
        }
        sNfcFCardEmulationService = infcadapter.getNfcFCardEmulationInterface();
_L1:
        return;
        exception;
        Log.e("NFC", "could not retrieve NFC-F card emulation service during service recovery");
          goto _L1
    }

    public boolean disable()
    {
        boolean flag;
        try
        {
            flag = sService.disable(true);
        }
        catch(RemoteException remoteexception)
        {
            attemptDeadServiceRecovery(remoteexception);
            return false;
        }
        return flag;
    }

    public boolean disable(boolean flag)
    {
        try
        {
            flag = sService.disable(flag);
        }
        catch(RemoteException remoteexception)
        {
            attemptDeadServiceRecovery(remoteexception);
            return false;
        }
        return flag;
    }

    public void disableForegroundDispatch(Activity activity)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            activity = JVM INSTR new #223 <Class UnsupportedOperationException>;
            activity.UnsupportedOperationException();
            throw activity;
        }
        break MISSING_BLOCK_LABEL_25;
        activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        ActivityThread.currentActivityThread().unregisterOnActivityPausedListener(activity, mForegroundDispatchListener);
        disableForegroundDispatchInternal(activity, false);
        return;
    }

    void disableForegroundDispatchInternal(Activity activity, boolean flag)
    {
        sService.setForegroundDispatch(null, null, null);
        if(!flag)
            try
            {
                if(activity.isResumed() ^ true)
                {
                    activity = JVM INSTR new #381 <Class IllegalStateException>;
                    activity.IllegalStateException("You must disable foreground dispatching while your activity is still resumed");
                    throw activity;
                }
            }
            // Misplaced declaration of an exception variable
            catch(Activity activity)
            {
                attemptDeadServiceRecovery(activity);
            }
        return;
    }

    public void disableForegroundNdefPush(Activity activity)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            activity = JVM INSTR new #223 <Class UnsupportedOperationException>;
            activity.UnsupportedOperationException();
            throw activity;
        }
        break MISSING_BLOCK_LABEL_25;
        activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        if(activity == null)
        {
            throw new NullPointerException();
        } else
        {
            enforceResumed(activity);
            mNfcActivityManager.setNdefPushMessage(activity, null, 0);
            mNfcActivityManager.setNdefPushMessageCallback(activity, null, 0);
            mNfcActivityManager.setOnNdefPushCompleteCallback(activity, null);
            return;
        }
    }

    public boolean disableNdefPush()
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            UnsupportedOperationException unsupportedoperationexception = JVM INSTR new #223 <Class UnsupportedOperationException>;
            unsupportedoperationexception.UnsupportedOperationException();
            throw unsupportedoperationexception;
        }
        break MISSING_BLOCK_LABEL_25;
        Exception exception;
        exception;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw exception;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        boolean flag;
        try
        {
            flag = sService.disableNdefPush();
        }
        catch(RemoteException remoteexception)
        {
            attemptDeadServiceRecovery(remoteexception);
            return false;
        }
        return flag;
    }

    public void disableReaderMode(Activity activity)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            activity = JVM INSTR new #223 <Class UnsupportedOperationException>;
            activity.UnsupportedOperationException();
            throw activity;
        }
        break MISSING_BLOCK_LABEL_25;
        activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        mNfcActivityManager.disableReaderMode(activity);
        return;
    }

    public void dispatch(Tag tag)
    {
        if(tag == null)
            throw new NullPointerException("tag cannot be null");
        sService.dispatch(tag);
_L1:
        return;
        tag;
        attemptDeadServiceRecovery(tag);
          goto _L1
    }

    public boolean enable()
    {
        boolean flag;
        try
        {
            flag = sService.enable();
        }
        catch(RemoteException remoteexception)
        {
            attemptDeadServiceRecovery(remoteexception);
            return false;
        }
        return flag;
    }

    public void enableForegroundDispatch(Activity activity, PendingIntent pendingintent, IntentFilter aintentfilter[], String as[][])
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            activity = JVM INSTR new #223 <Class UnsupportedOperationException>;
            activity.UnsupportedOperationException();
            throw activity;
        }
        break MISSING_BLOCK_LABEL_25;
        activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        TechListParcel techlistparcel;
        if(activity == null || pendingintent == null)
            throw new NullPointerException();
        if(!activity.isResumed())
            throw new IllegalStateException("Foreground dispatch can only be enabled when your activity is resumed");
        Object obj = null;
        techlistparcel = obj;
        if(as == null)
            break MISSING_BLOCK_LABEL_96;
        techlistparcel = obj;
        if(as.length > 0)
        {
            techlistparcel = JVM INSTR new #425 <Class TechListParcel>;
            techlistparcel.TechListParcel(as);
        }
        ActivityThread.currentActivityThread().registerOnActivityPausedListener(activity, mForegroundDispatchListener);
        sService.setForegroundDispatch(pendingintent, aintentfilter, techlistparcel);
_L1:
        return;
        activity;
        attemptDeadServiceRecovery(activity);
          goto _L1
    }

    public void enableForegroundNdefPush(Activity activity, NdefMessage ndefmessage)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            activity = JVM INSTR new #223 <Class UnsupportedOperationException>;
            activity.UnsupportedOperationException();
            throw activity;
        }
        break MISSING_BLOCK_LABEL_25;
        activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        if(activity == null || ndefmessage == null)
        {
            throw new NullPointerException();
        } else
        {
            enforceResumed(activity);
            mNfcActivityManager.setNdefPushMessage(activity, ndefmessage, 0);
            return;
        }
    }

    public boolean enableNdefPush()
    {
        if(!sHasNfcFeature)
            throw new UnsupportedOperationException();
        boolean flag;
        try
        {
            flag = sService.enableNdefPush();
        }
        catch(RemoteException remoteexception)
        {
            attemptDeadServiceRecovery(remoteexception);
            return false;
        }
        return flag;
    }

    public void enableReaderMode(Activity activity, ReaderCallback readercallback, int i, Bundle bundle)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            activity = JVM INSTR new #223 <Class UnsupportedOperationException>;
            activity.UnsupportedOperationException();
            throw activity;
        }
        break MISSING_BLOCK_LABEL_25;
        activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        mNfcActivityManager.enableReaderMode(activity, readercallback, i, bundle);
        return;
    }

    void enforceResumed(Activity activity)
    {
        if(!activity.isResumed())
            throw new IllegalStateException("API cannot be called while activity is paused");
        else
            return;
    }

    public int getAdapterState()
    {
        int i;
        try
        {
            i = sService.getState();
        }
        catch(RemoteException remoteexception)
        {
            attemptDeadServiceRecovery(remoteexception);
            return 1;
        }
        return i;
    }

    public INfcCardEmulation getCardEmulationService()
    {
        isEnabled();
        return sCardEmulationService;
    }

    public Context getContext()
    {
        return mContext;
    }

    public INfcAdapterExtras getNfcAdapterExtrasInterface()
    {
        if(mContext == null)
            throw new UnsupportedOperationException("You need a context on NfcAdapter to use the  NFC extras APIs");
        INfcAdapterExtras infcadapterextras;
        try
        {
            infcadapterextras = sService.getNfcAdapterExtrasInterface(mContext.getPackageName());
        }
        catch(RemoteException remoteexception)
        {
            attemptDeadServiceRecovery(remoteexception);
            return null;
        }
        return infcadapterextras;
    }

    public INfcDta getNfcDtaInterface()
    {
        if(mContext == null)
            throw new UnsupportedOperationException("You need a context on NfcAdapter to use the  NFC extras APIs");
        INfcDta infcdta;
        try
        {
            infcdta = sService.getNfcDtaInterface(mContext.getPackageName());
        }
        catch(RemoteException remoteexception)
        {
            attemptDeadServiceRecovery(remoteexception);
            return null;
        }
        return infcdta;
    }

    public INfcFCardEmulation getNfcFCardEmulationService()
    {
        isEnabled();
        return sNfcFCardEmulationService;
    }

    int getSdkVersion()
    {
        if(mContext == null)
            return 9;
        else
            return mContext.getApplicationInfo().targetSdkVersion;
    }

    public INfcAdapter getService()
    {
        isEnabled();
        return sService;
    }

    public INfcTag getTagService()
    {
        isEnabled();
        return sTagService;
    }

    public boolean ignore(Tag tag, int i, final OnTagRemovedListener tagRemovedListener, final Handler handler)
    {
        ITagRemovedCallback.Stub stub;
        stub = null;
        if(tagRemovedListener != null)
            stub = new ITagRemovedCallback.Stub() {

                public void onTagRemoved()
                    throws RemoteException
                {
                    Object obj;
                    if(handler != null)
                        handler.post(tagRemovedListener. new Runnable() {

                            public void run()
                            {
                                tagRemovedListener.onTagRemoved();
                            }

                            final _cls2 this$1;
                            final OnTagRemovedListener val$tagRemovedListener;

            
            {
                this$1 = final__pcls2;
                tagRemovedListener = OnTagRemovedListener.this;
                super();
            }
                        }
);
                    else
                        tagRemovedListener.onTagRemoved();
                    obj = mLock;
                    obj;
                    JVM INSTR monitorenter ;
                    mTagRemovedListener = null;
                    obj;
                    JVM INSTR monitorexit ;
                    return;
                    Exception exception;
                    exception;
                    throw exception;
                }

                final NfcAdapter this$0;
                final Handler val$handler;
                final OnTagRemovedListener val$tagRemovedListener;

            
            {
                this$0 = NfcAdapter.this;
                handler = handler1;
                tagRemovedListener = ontagremovedlistener;
                super();
            }
            }
;
        tagRemovedListener = ((OnTagRemovedListener) (mLock));
        tagRemovedListener;
        JVM INSTR monitorenter ;
        mTagRemovedListener = stub;
        tagRemovedListener;
        JVM INSTR monitorexit ;
        boolean flag;
        try
        {
            flag = sService.ignore(tag.getServiceHandle(), i, stub);
        }
        // Misplaced declaration of an exception variable
        catch(Tag tag)
        {
            return false;
        }
        return flag;
        tag;
        throw tag;
    }

    public boolean invokeBeam(Activity activity)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            activity = JVM INSTR new #223 <Class UnsupportedOperationException>;
            activity.UnsupportedOperationException();
            throw activity;
        }
        break MISSING_BLOCK_LABEL_25;
        activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw activity;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        if(activity == null)
            throw new NullPointerException("activity may not be null.");
        enforceResumed(activity);
        try
        {
            sService.invokeBeam();
        }
        // Misplaced declaration of an exception variable
        catch(Activity activity)
        {
            Log.e("NFC", "invokeBeam: NFC process has died.");
            attemptDeadServiceRecovery(activity);
            return false;
        }
        return true;
    }

    public boolean invokeBeam(BeamShareData beamsharedata)
    {
        try
        {
            Log.e("NFC", "invokeBeamInternal()");
            sService.invokeBeamInternal(beamsharedata);
        }
        // Misplaced declaration of an exception variable
        catch(BeamShareData beamsharedata)
        {
            Log.e("NFC", "invokeBeam: NFC process has died.");
            attemptDeadServiceRecovery(beamsharedata);
            return false;
        }
        return true;
    }

    public boolean isEnabled()
    {
        boolean flag = false;
        int i;
        try
        {
            i = sService.getState();
        }
        catch(RemoteException remoteexception)
        {
            attemptDeadServiceRecovery(remoteexception);
            return false;
        }
        if(i == 3)
            flag = true;
        return flag;
    }

    public boolean isNdefPushEnabled()
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            UnsupportedOperationException unsupportedoperationexception = JVM INSTR new #223 <Class UnsupportedOperationException>;
            unsupportedoperationexception.UnsupportedOperationException();
            throw unsupportedoperationexception;
        }
        break MISSING_BLOCK_LABEL_25;
        Exception exception;
        exception;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw exception;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        boolean flag;
        try
        {
            flag = sService.isNdefPushEnabled();
        }
        catch(RemoteException remoteexception)
        {
            attemptDeadServiceRecovery(remoteexception);
            return false;
        }
        return flag;
    }

    public void pausePolling(int i)
    {
        sService.pausePolling(i);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        attemptDeadServiceRecovery(remoteexception);
          goto _L1
    }

    public boolean removeNfcUnlockHandler(NfcUnlockHandler nfcunlockhandler)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            nfcunlockhandler = JVM INSTR new #223 <Class UnsupportedOperationException>;
            nfcunlockhandler.UnsupportedOperationException();
            throw nfcunlockhandler;
        }
        break MISSING_BLOCK_LABEL_25;
        nfcunlockhandler;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw nfcunlockhandler;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mNfcUnlockHandlers.containsKey(nfcunlockhandler))
            sService.removeNfcUnlockHandler((INfcUnlockHandler)mNfcUnlockHandlers.remove(nfcunlockhandler));
        obj;
        JVM INSTR monitorexit ;
        return true;
        nfcunlockhandler;
        obj;
        JVM INSTR monitorexit ;
        throw nfcunlockhandler;
        nfcunlockhandler;
        attemptDeadServiceRecovery(nfcunlockhandler);
        return false;
    }

    public void resumePolling()
    {
        sService.resumePolling();
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        attemptDeadServiceRecovery(remoteexception);
          goto _L1
    }

    public void setBeamPushUris(Uri auri[], Activity activity)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            auri = JVM INSTR new #223 <Class UnsupportedOperationException>;
            auri.UnsupportedOperationException();
            throw auri;
        }
        break MISSING_BLOCK_LABEL_25;
        auri;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw auri;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        if(activity == null)
            throw new NullPointerException("activity cannot be null");
        if(auri != null)
        {
            int i = 0;
            for(int j = auri.length; i < j; i++)
            {
                Object obj = auri[i];
                if(obj == null)
                    throw new NullPointerException("Uri not allowed to be null");
                obj = ((Uri) (obj)).getScheme();
                if(obj == null || !((String) (obj)).equalsIgnoreCase("file") && ((String) (obj)).equalsIgnoreCase("content") ^ true)
                    throw new IllegalArgumentException("URI needs to have either scheme file or scheme content");
            }

        }
        mNfcActivityManager.setNdefPushContentUri(activity, auri);
        return;
    }

    public void setBeamPushUrisCallback(CreateBeamUrisCallback createbeamuriscallback, Activity activity)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            createbeamuriscallback = JVM INSTR new #223 <Class UnsupportedOperationException>;
            createbeamuriscallback.UnsupportedOperationException();
            throw createbeamuriscallback;
        }
        break MISSING_BLOCK_LABEL_25;
        createbeamuriscallback;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw createbeamuriscallback;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        if(activity == null)
        {
            throw new NullPointerException("activity cannot be null");
        } else
        {
            mNfcActivityManager.setNdefPushContentUriCallback(activity, createbeamuriscallback);
            return;
        }
    }

    public void setNdefPushMessage(NdefMessage ndefmessage, Activity activity, int i)
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            ndefmessage = JVM INSTR new #223 <Class UnsupportedOperationException>;
            ndefmessage.UnsupportedOperationException();
            throw ndefmessage;
        }
        break MISSING_BLOCK_LABEL_25;
        ndefmessage;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw ndefmessage;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        if(activity == null)
        {
            throw new NullPointerException("activity cannot be null");
        } else
        {
            mNfcActivityManager.setNdefPushMessage(activity, ndefmessage, i);
            return;
        }
    }

    public transient void setNdefPushMessage(NdefMessage ndefmessage, Activity activity, Activity aactivity[])
    {
        int i = 0;
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            ndefmessage = JVM INSTR new #223 <Class UnsupportedOperationException>;
            ndefmessage.UnsupportedOperationException();
            throw ndefmessage;
        }
        break MISSING_BLOCK_LABEL_28;
        ndefmessage;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw ndefmessage;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        int j = getSdkVersion();
        if(activity != null) goto _L2; else goto _L1
_L1:
        try
        {
            ndefmessage = JVM INSTR new #387 <Class NullPointerException>;
            ndefmessage.NullPointerException("activity cannot be null");
            throw ndefmessage;
        }
        // Misplaced declaration of an exception variable
        catch(NdefMessage ndefmessage) { }
        if(j >= 16)
            break MISSING_BLOCK_LABEL_132;
        Log.e("NFC", "Cannot call API with Activity that has already been destroyed", ndefmessage);
_L6:
        return;
_L2:
        int k;
        mNfcActivityManager.setNdefPushMessage(activity, ndefmessage, 0);
        k = aactivity.length;
_L4:
        if(i >= k)
            break; /* Loop/switch isn't completed */
        activity = aactivity[i];
        if(activity != null)
            break MISSING_BLOCK_LABEL_116;
        ndefmessage = JVM INSTR new #387 <Class NullPointerException>;
        ndefmessage.NullPointerException("activities cannot contain null");
        throw ndefmessage;
        mNfcActivityManager.setNdefPushMessage(activity, ndefmessage, 0);
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L5:
        throw ndefmessage;
    }

    public void setNdefPushMessageCallback(CreateNdefMessageCallback createndefmessagecallback, Activity activity, int i)
    {
        if(activity == null)
        {
            throw new NullPointerException("activity cannot be null");
        } else
        {
            mNfcActivityManager.setNdefPushMessageCallback(activity, createndefmessagecallback, i);
            return;
        }
    }

    public transient void setNdefPushMessageCallback(CreateNdefMessageCallback createndefmessagecallback, Activity activity, Activity aactivity[])
    {
        int i = 0;
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            createndefmessagecallback = JVM INSTR new #223 <Class UnsupportedOperationException>;
            createndefmessagecallback.UnsupportedOperationException();
            throw createndefmessagecallback;
        }
        break MISSING_BLOCK_LABEL_28;
        createndefmessagecallback;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw createndefmessagecallback;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        int j = getSdkVersion();
        if(activity != null) goto _L2; else goto _L1
_L1:
        try
        {
            createndefmessagecallback = JVM INSTR new #387 <Class NullPointerException>;
            createndefmessagecallback.NullPointerException("activity cannot be null");
            throw createndefmessagecallback;
        }
        // Misplaced declaration of an exception variable
        catch(CreateNdefMessageCallback createndefmessagecallback) { }
        if(j >= 16)
            break MISSING_BLOCK_LABEL_132;
        Log.e("NFC", "Cannot call API with Activity that has already been destroyed", createndefmessagecallback);
_L6:
        return;
_L2:
        int k;
        mNfcActivityManager.setNdefPushMessageCallback(activity, createndefmessagecallback, 0);
        k = aactivity.length;
_L4:
        if(i >= k)
            break; /* Loop/switch isn't completed */
        activity = aactivity[i];
        if(activity != null)
            break MISSING_BLOCK_LABEL_116;
        createndefmessagecallback = JVM INSTR new #387 <Class NullPointerException>;
        createndefmessagecallback.NullPointerException("activities cannot contain null");
        throw createndefmessagecallback;
        mNfcActivityManager.setNdefPushMessageCallback(activity, createndefmessagecallback, 0);
        i++;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L5:
        throw createndefmessagecallback;
    }

    public transient void setOnNdefPushCompleteCallback(OnNdefPushCompleteCallback onndefpushcompletecallback, Activity activity, Activity aactivity[])
    {
        android/nfc/NfcAdapter;
        JVM INSTR monitorenter ;
        if(!sHasNfcFeature)
        {
            onndefpushcompletecallback = JVM INSTR new #223 <Class UnsupportedOperationException>;
            onndefpushcompletecallback.UnsupportedOperationException();
            throw onndefpushcompletecallback;
        }
        break MISSING_BLOCK_LABEL_25;
        onndefpushcompletecallback;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        throw onndefpushcompletecallback;
        android/nfc/NfcAdapter;
        JVM INSTR monitorexit ;
        int i = getSdkVersion();
        if(activity != null) goto _L2; else goto _L1
_L1:
        try
        {
            onndefpushcompletecallback = JVM INSTR new #387 <Class NullPointerException>;
            onndefpushcompletecallback.NullPointerException("activity cannot be null");
            throw onndefpushcompletecallback;
        }
        // Misplaced declaration of an exception variable
        catch(OnNdefPushCompleteCallback onndefpushcompletecallback) { }
        if(i >= 16)
            break MISSING_BLOCK_LABEL_130;
        Log.e("NFC", "Cannot call API with Activity that has already been destroyed", onndefpushcompletecallback);
_L6:
        return;
_L2:
        mNfcActivityManager.setOnNdefPushCompleteCallback(activity, onndefpushcompletecallback);
        int j = 0;
        int k = aactivity.length;
_L4:
        if(j >= k)
            break; /* Loop/switch isn't completed */
        activity = aactivity[j];
        if(activity != null)
            break MISSING_BLOCK_LABEL_115;
        onndefpushcompletecallback = JVM INSTR new #387 <Class NullPointerException>;
        onndefpushcompletecallback.NullPointerException("activities cannot contain null");
        throw onndefpushcompletecallback;
        mNfcActivityManager.setOnNdefPushCompleteCallback(activity, onndefpushcompletecallback);
        j++;
        if(true) goto _L4; else goto _L3
_L3:
        if(true) goto _L6; else goto _L5
_L5:
        throw onndefpushcompletecallback;
    }

    public void setP2pModes(int i, int j)
    {
        sService.setP2pModes(i, j);
_L1:
        return;
        RemoteException remoteexception;
        remoteexception;
        attemptDeadServiceRecovery(remoteexception);
          goto _L1
    }

    public static final String ACTION_ADAPTER_STATE_CHANGED = "android.nfc.action.ADAPTER_STATE_CHANGED";
    public static final String ACTION_HANDOVER_TRANSFER_DONE = "android.nfc.action.HANDOVER_TRANSFER_DONE";
    public static final String ACTION_HANDOVER_TRANSFER_STARTED = "android.nfc.action.HANDOVER_TRANSFER_STARTED";
    public static final String ACTION_NDEF_DISCOVERED = "android.nfc.action.NDEF_DISCOVERED";
    public static final String ACTION_TAG_DISCOVERED = "android.nfc.action.TAG_DISCOVERED";
    public static final String ACTION_TAG_LEFT_FIELD = "android.nfc.action.TAG_LOST";
    public static final String ACTION_TECH_DISCOVERED = "android.nfc.action.TECH_DISCOVERED";
    public static final String EXTRA_ADAPTER_STATE = "android.nfc.extra.ADAPTER_STATE";
    public static final String EXTRA_HANDOVER_TRANSFER_STATUS = "android.nfc.extra.HANDOVER_TRANSFER_STATUS";
    public static final String EXTRA_HANDOVER_TRANSFER_URI = "android.nfc.extra.HANDOVER_TRANSFER_URI";
    public static final String EXTRA_ID = "android.nfc.extra.ID";
    public static final String EXTRA_NDEF_MESSAGES = "android.nfc.extra.NDEF_MESSAGES";
    public static final String EXTRA_READER_PRESENCE_CHECK_DELAY = "presence";
    public static final String EXTRA_TAG = "android.nfc.extra.TAG";
    public static final int FLAG_NDEF_PUSH_NO_CONFIRM = 1;
    public static final int FLAG_READER_NFC_A = 1;
    public static final int FLAG_READER_NFC_B = 2;
    public static final int FLAG_READER_NFC_BARCODE = 16;
    public static final int FLAG_READER_NFC_F = 4;
    public static final int FLAG_READER_NFC_V = 8;
    public static final int FLAG_READER_NO_PLATFORM_SOUNDS = 256;
    public static final int FLAG_READER_SKIP_NDEF_CHECK = 128;
    public static final int HANDOVER_TRANSFER_STATUS_FAILURE = 1;
    public static final int HANDOVER_TRANSFER_STATUS_SUCCESS = 0;
    public static final int STATE_OFF = 1;
    public static final int STATE_ON = 3;
    public static final int STATE_TURNING_OFF = 4;
    public static final int STATE_TURNING_ON = 2;
    static final String TAG = "NFC";
    static INfcCardEmulation sCardEmulationService;
    static boolean sHasNfcFeature;
    static boolean sIsInitialized = false;
    static HashMap sNfcAdapters = new HashMap();
    static INfcFCardEmulation sNfcFCardEmulationService;
    static NfcAdapter sNullContextNfcAdapter;
    static INfcAdapter sService;
    static INfcTag sTagService;
    final Context mContext;
    OnActivityPausedListener mForegroundDispatchListener;
    final Object mLock = new Object();
    final NfcActivityManager mNfcActivityManager = new NfcActivityManager(this);
    final HashMap mNfcUnlockHandlers = new HashMap();
    ITagRemovedCallback mTagRemovedListener;


    // Unreferenced inner class android/nfc/NfcAdapter$3

/* anonymous class */
    class _cls3 extends INfcUnlockHandler.Stub
    {

        public boolean onUnlockAttempted(Tag tag)
            throws RemoteException
        {
            return unlockHandler.onUnlockAttempted(tag);
        }

        final NfcAdapter this$0;
        final NfcUnlockHandler val$unlockHandler;

            
            {
                this$0 = NfcAdapter.this;
                unlockHandler = nfcunlockhandler;
                super();
            }
    }

}
