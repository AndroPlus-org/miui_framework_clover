// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.app;

import android.content.*;
import android.os.*;
import android.util.AndroidException;

// Referenced classes of package android.app:
//            ActivityManager, IActivityManager, ActivityThread

public final class PendingIntent
    implements Parcelable
{
    public static class CanceledException extends AndroidException
    {

        public CanceledException()
        {
        }

        public CanceledException(Exception exception)
        {
            super(exception);
        }

        public CanceledException(String s)
        {
            super(s);
        }
    }

    private static class FinishedDispatcher extends android.content.IIntentReceiver.Stub
        implements Runnable
    {

        public void performReceive(Intent intent, int i, String s, Bundle bundle, boolean flag, boolean flag1, int j)
        {
            mIntent = intent;
            mResultCode = i;
            mResultData = s;
            mResultExtras = bundle;
            if(mHandler == null)
                run();
            else
                mHandler.post(this);
        }

        public void run()
        {
            mWho.onSendFinished(mPendingIntent, mIntent, mResultCode, mResultData, mResultExtras);
        }

        private static Handler sDefaultSystemHandler;
        private final Handler mHandler;
        private Intent mIntent;
        private final PendingIntent mPendingIntent;
        private int mResultCode;
        private String mResultData;
        private Bundle mResultExtras;
        private final OnFinished mWho;

        FinishedDispatcher(PendingIntent pendingintent, OnFinished onfinished, Handler handler)
        {
            mPendingIntent = pendingintent;
            mWho = onfinished;
            if(handler == null && ActivityThread.isSystem())
            {
                if(sDefaultSystemHandler == null)
                    sDefaultSystemHandler = new Handler(Looper.getMainLooper());
                mHandler = sDefaultSystemHandler;
            } else
            {
                mHandler = handler;
            }
        }
    }

    public static interface OnFinished
    {

        public abstract void onSendFinished(PendingIntent pendingintent, Intent intent, int i, String s, Bundle bundle);
    }

    public static interface OnMarshaledListener
    {

        public abstract void onMarshaled(PendingIntent pendingintent, Parcel parcel, int i);
    }


    PendingIntent(IIntentSender iintentsender)
    {
        mTarget = iintentsender;
    }

    PendingIntent(IBinder ibinder, Object obj)
    {
        mTarget = android.content.IIntentSender.Stub.asInterface(ibinder);
        if(obj != null)
            mWhitelistToken = (IBinder)obj;
    }

    private static PendingIntent buildServicePendingIntent(Context context, int i, Intent intent, int j, int k)
    {
        String s = context.getPackageName();
        String s1;
        IActivityManager iactivitymanager;
        int l;
        if(intent != null)
            s1 = intent.resolveTypeIfNeeded(context.getContentResolver());
        else
            s1 = null;
        try
        {
            intent.prepareToLeaveProcess(context);
            iactivitymanager = ActivityManager.getService();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        if(s1 == null) goto _L2; else goto _L1
_L1:
        context = new String[1];
        context[0] = s1;
_L3:
        l = UserHandle.myUserId();
        context = iactivitymanager.getIntentSender(k, s, null, null, i, new Intent[] {
            intent
        }, context, j, null, l);
        if(context == null)
            break MISSING_BLOCK_LABEL_104;
        context = new PendingIntent(context);
_L4:
        return context;
_L2:
        context = null;
          goto _L3
        context = null;
          goto _L4
    }

    public static PendingIntent getActivities(Context context, int i, Intent aintent[], int j)
    {
        return getActivities(context, i, aintent, j, null);
    }

    public static PendingIntent getActivities(Context context, int i, Intent aintent[], int j, Bundle bundle)
    {
        String s = context.getPackageName();
        String as[] = new String[aintent.length];
        for(int k = 0; k < aintent.length; k++)
        {
            aintent[k].migrateExtraStreamToClipData();
            aintent[k].prepareToLeaveProcess(context);
            as[k] = aintent[k].resolveTypeIfNeeded(context.getContentResolver());
        }

        try
        {
            context = ActivityManager.getService().getIntentSender(2, s, null, null, i, aintent, as, j, bundle, UserHandle.myUserId());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        if(context == null) goto _L2; else goto _L1
_L1:
        context = new PendingIntent(context);
_L4:
        return context;
_L2:
        context = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static PendingIntent getActivitiesAsUser(Context context, int i, Intent aintent[], int j, Bundle bundle, UserHandle userhandle)
    {
        String s = context.getPackageName();
        String as[] = new String[aintent.length];
        for(int k = 0; k < aintent.length; k++)
        {
            aintent[k].migrateExtraStreamToClipData();
            aintent[k].prepareToLeaveProcess(context);
            as[k] = aintent[k].resolveTypeIfNeeded(context.getContentResolver());
        }

        try
        {
            context = ActivityManager.getService().getIntentSender(2, s, null, null, i, aintent, as, j, bundle, userhandle.getIdentifier());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        if(context == null) goto _L2; else goto _L1
_L1:
        context = new PendingIntent(context);
_L4:
        return context;
_L2:
        context = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static PendingIntent getActivity(Context context, int i, Intent intent, int j)
    {
        return getActivity(context, i, intent, j, null);
    }

    public static PendingIntent getActivity(Context context, int i, Intent intent, int j, Bundle bundle)
    {
        String s = context.getPackageName();
        String s1;
        IActivityManager iactivitymanager;
        int k;
        if(intent != null)
            s1 = intent.resolveTypeIfNeeded(context.getContentResolver());
        else
            s1 = null;
        try
        {
            intent.migrateExtraStreamToClipData();
            intent.prepareToLeaveProcess(context);
            iactivitymanager = ActivityManager.getService();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        if(s1 == null) goto _L2; else goto _L1
_L1:
        context = new String[1];
        context[0] = s1;
_L3:
        k = UserHandle.myUserId();
        context = iactivitymanager.getIntentSender(2, s, null, null, i, new Intent[] {
            intent
        }, context, j, bundle, k);
        if(context == null)
            break MISSING_BLOCK_LABEL_109;
        context = new PendingIntent(context);
_L4:
        return context;
_L2:
        context = null;
          goto _L3
        context = null;
          goto _L4
    }

    public static PendingIntent getActivityAsUser(Context context, int i, Intent intent, int j, Bundle bundle, UserHandle userhandle)
    {
        String s = context.getPackageName();
        String s1;
        IActivityManager iactivitymanager;
        int k;
        if(intent != null)
            s1 = intent.resolveTypeIfNeeded(context.getContentResolver());
        else
            s1 = null;
        try
        {
            intent.migrateExtraStreamToClipData();
            intent.prepareToLeaveProcess(context);
            iactivitymanager = ActivityManager.getService();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        if(s1 == null) goto _L2; else goto _L1
_L1:
        context = new String[1];
        context[0] = s1;
_L3:
        k = userhandle.getIdentifier();
        context = iactivitymanager.getIntentSender(2, s, null, null, i, new Intent[] {
            intent
        }, context, j, bundle, k);
        if(context == null)
            break MISSING_BLOCK_LABEL_111;
        context = new PendingIntent(context);
_L4:
        return context;
_L2:
        context = null;
          goto _L3
        context = null;
          goto _L4
    }

    public static PendingIntent getBroadcast(Context context, int i, Intent intent, int j)
    {
        return getBroadcastAsUser(context, i, intent, j, new UserHandle(UserHandle.myUserId()));
    }

    public static PendingIntent getBroadcastAsUser(Context context, int i, Intent intent, int j, UserHandle userhandle)
    {
        String s = context.getPackageName();
        String s1;
        IActivityManager iactivitymanager;
        int k;
        if(intent != null)
            s1 = intent.resolveTypeIfNeeded(context.getContentResolver());
        else
            s1 = null;
        try
        {
            intent.prepareToLeaveProcess(context);
            iactivitymanager = ActivityManager.getService();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            throw context.rethrowFromSystemServer();
        }
        if(s1 == null) goto _L2; else goto _L1
_L1:
        context = new String[1];
        context[0] = s1;
_L3:
        k = userhandle.getIdentifier();
        context = iactivitymanager.getIntentSender(1, s, null, null, i, new Intent[] {
            intent
        }, context, j, null, k);
        if(context == null)
            break MISSING_BLOCK_LABEL_105;
        context = new PendingIntent(context);
_L4:
        return context;
_L2:
        context = null;
          goto _L3
        context = null;
          goto _L4
    }

    public static PendingIntent getForegroundService(Context context, int i, Intent intent, int j)
    {
        return buildServicePendingIntent(context, i, intent, j, 5);
    }

    public static PendingIntent getService(Context context, int i, Intent intent, int j)
    {
        return buildServicePendingIntent(context, i, intent, j, 4);
    }

    public static PendingIntent readPendingIntentOrNullFromParcel(Parcel parcel)
    {
        PendingIntent pendingintent = null;
        IBinder ibinder = parcel.readStrongBinder();
        if(ibinder != null)
            pendingintent = new PendingIntent(ibinder, parcel.getClassCookie(android/app/PendingIntent));
        return pendingintent;
    }

    public static void setOnMarshaledListener(OnMarshaledListener onmarshaledlistener)
    {
        sOnMarshaledListener.set(onmarshaledlistener);
    }

    public static void writePendingIntentOrNullToParcel(PendingIntent pendingintent, Parcel parcel)
    {
        IBinder ibinder = null;
        if(pendingintent != null)
            ibinder = pendingintent.mTarget.asBinder();
        parcel.writeStrongBinder(ibinder);
    }

    public void cancel()
    {
        ActivityManager.getService().cancelIntentSender(mTarget);
_L2:
        return;
        RemoteException remoteexception;
        remoteexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof PendingIntent)
            return mTarget.asBinder().equals(((PendingIntent)obj).mTarget.asBinder());
        else
            return false;
    }

    public String getCreatorPackage()
    {
        String s;
        try
        {
            s = ActivityManager.getService().getPackageForIntentSender(mTarget);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public int getCreatorUid()
    {
        int i;
        try
        {
            i = ActivityManager.getService().getUidForIntentSender(mTarget);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return i;
    }

    public UserHandle getCreatorUserHandle()
    {
        int i;
        UserHandle userhandle;
        try
        {
            i = ActivityManager.getService().getUidForIntentSender(mTarget);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        if(i <= 0) goto _L2; else goto _L1
_L1:
        userhandle = new UserHandle(UserHandle.getUserId(i));
_L4:
        return userhandle;
_L2:
        userhandle = null;
        if(true) goto _L4; else goto _L3
_L3:
    }

    public Intent getIntent()
    {
        Intent intent;
        try
        {
            intent = ActivityManager.getService().getIntentForIntentSender(mTarget);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return intent;
    }

    public IntentSender getIntentSender()
    {
        return new IntentSender(mTarget, mWhitelistToken);
    }

    public String getTag(String s)
    {
        try
        {
            s = ActivityManager.getService().getTagForIntentSender(mTarget, s);
        }
        // Misplaced declaration of an exception variable
        catch(String s)
        {
            throw s.rethrowFromSystemServer();
        }
        return s;
    }

    public IIntentSender getTarget()
    {
        return mTarget;
    }

    public String getTargetPackage()
    {
        String s;
        try
        {
            s = ActivityManager.getService().getPackageForIntentSender(mTarget);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return s;
    }

    public IBinder getWhitelistToken()
    {
        return mWhitelistToken;
    }

    public int hashCode()
    {
        return mTarget.asBinder().hashCode();
    }

    public boolean isActivity()
    {
        boolean flag;
        try
        {
            flag = ActivityManager.getService().isIntentSenderAnActivity(mTarget);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public boolean isTargetedToPackage()
    {
        boolean flag;
        try
        {
            flag = ActivityManager.getService().isIntentSenderTargetedToPackage(mTarget);
        }
        catch(RemoteException remoteexception)
        {
            throw remoteexception.rethrowFromSystemServer();
        }
        return flag;
    }

    public void send()
        throws CanceledException
    {
        send(null, 0, null, null, null, null, null);
    }

    public void send(int i)
        throws CanceledException
    {
        send(null, i, null, null, null, null, null);
    }

    public void send(int i, OnFinished onfinished, Handler handler)
        throws CanceledException
    {
        send(null, i, null, onfinished, handler, null, null);
    }

    public void send(Context context, int i, Intent intent)
        throws CanceledException
    {
        send(context, i, intent, null, null, null, null);
    }

    public void send(Context context, int i, Intent intent, OnFinished onfinished, Handler handler)
        throws CanceledException
    {
        send(context, i, intent, onfinished, handler, null, null);
    }

    public void send(Context context, int i, Intent intent, OnFinished onfinished, Handler handler, String s)
        throws CanceledException
    {
        send(context, i, intent, onfinished, handler, s, null);
    }

    public void send(Context context, int i, Intent intent, OnFinished onfinished, Handler handler, String s, Bundle bundle)
        throws CanceledException
    {
        if(intent == null)
            break MISSING_BLOCK_LABEL_97;
        context = intent.resolveTypeIfNeeded(context.getContentResolver());
_L1:
        IActivityManager iactivitymanager;
        IIntentSender iintentsender;
        IBinder ibinder;
        iactivitymanager = ActivityManager.getService();
        iintentsender = mTarget;
        ibinder = mWhitelistToken;
        if(onfinished != null)
        {
            FinishedDispatcher finisheddispatcher;
            try
            {
                finisheddispatcher = JVM INSTR new #13  <Class PendingIntent$FinishedDispatcher>;
                finisheddispatcher.FinishedDispatcher(this, onfinished, handler);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw new CanceledException(context);
            }
            onfinished = finisheddispatcher;
        } else
        {
            onfinished = null;
        }
        if(iactivitymanager.sendIntentSender(iintentsender, ibinder, i, intent, context, onfinished, s, bundle) < 0)
        {
            context = JVM INSTR new #10  <Class PendingIntent$CanceledException>;
            context.CanceledException();
            throw context;
        }
        break MISSING_BLOCK_LABEL_108;
        context = null;
          goto _L1
    }

    public String toString()
    {
        IBinder ibinder = null;
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("PendingIntent{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringbuilder.append(": ");
        if(mTarget != null)
            ibinder = mTarget.asBinder();
        stringbuilder.append(ibinder);
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeStrongBinder(mTarget.asBinder());
        OnMarshaledListener onmarshaledlistener = (OnMarshaledListener)sOnMarshaledListener.get();
        if(onmarshaledlistener != null)
            onmarshaledlistener.onMarshaled(this, parcel, i);
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public PendingIntent createFromParcel(Parcel parcel)
        {
            PendingIntent pendingintent = null;
            IBinder ibinder = parcel.readStrongBinder();
            if(ibinder != null)
                pendingintent = new PendingIntent(ibinder, parcel.getClassCookie(android/app/PendingIntent));
            return pendingintent;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public PendingIntent[] newArray(int i)
        {
            return new PendingIntent[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final int FLAG_CANCEL_CURRENT = 0x10000000;
    public static final int FLAG_IMMUTABLE = 0x4000000;
    public static final int FLAG_NO_CREATE = 0x20000000;
    public static final int FLAG_ONE_SHOT = 0x40000000;
    public static final int FLAG_UPDATE_CURRENT = 0x8000000;
    private static final ThreadLocal sOnMarshaledListener = new ThreadLocal();
    private final IIntentSender mTarget;
    private IBinder mWhitelistToken;

}
