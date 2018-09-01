// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.content;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.os.*;
import android.util.AndroidException;

// Referenced classes of package android.content:
//            IIntentSender, Context, Intent

public class IntentSender
    implements Parcelable
{
    private static class FinishedDispatcher extends IIntentReceiver.Stub
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
            mWho.onSendFinished(mIntentSender, mIntent, mResultCode, mResultData, mResultExtras);
        }

        private final Handler mHandler;
        private Intent mIntent;
        private final IntentSender mIntentSender;
        private int mResultCode;
        private String mResultData;
        private Bundle mResultExtras;
        private final OnFinished mWho;

        FinishedDispatcher(IntentSender intentsender, OnFinished onfinished, Handler handler)
        {
            mIntentSender = intentsender;
            mWho = onfinished;
            mHandler = handler;
        }
    }

    public static interface OnFinished
    {

        public abstract void onSendFinished(IntentSender intentsender, Intent intent, int i, String s, Bundle bundle);
    }

    public static class SendIntentException extends AndroidException
    {

        public SendIntentException()
        {
        }

        public SendIntentException(Exception exception)
        {
            super(exception);
        }

        public SendIntentException(String s)
        {
            super(s);
        }
    }


    public IntentSender(IIntentSender iintentsender)
    {
        mTarget = iintentsender;
    }

    public IntentSender(IIntentSender iintentsender, IBinder ibinder)
    {
        mTarget = iintentsender;
        mWhitelistToken = ibinder;
    }

    public IntentSender(IBinder ibinder)
    {
        mTarget = IIntentSender.Stub.asInterface(ibinder);
    }

    public static IntentSender readIntentSenderOrNullFromParcel(Parcel parcel)
    {
        Object obj = null;
        IBinder ibinder = parcel.readStrongBinder();
        parcel = obj;
        if(ibinder != null)
            parcel = new IntentSender(ibinder);
        return parcel;
    }

    public static void writeIntentSenderOrNullToParcel(IntentSender intentsender, Parcel parcel)
    {
        IBinder ibinder = null;
        if(intentsender != null)
            ibinder = intentsender.mTarget.asBinder();
        parcel.writeStrongBinder(ibinder);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof IntentSender)
            return mTarget.asBinder().equals(((IntentSender)obj).mTarget.asBinder());
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
            return null;
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
            return -1;
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
            return null;
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
            return null;
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

    public void sendIntent(Context context, int i, Intent intent, OnFinished onfinished, Handler handler)
        throws SendIntentException
    {
        sendIntent(context, i, intent, onfinished, handler, null);
    }

    public void sendIntent(Context context, int i, Intent intent, OnFinished onfinished, Handler handler, String s)
        throws SendIntentException
    {
        if(intent == null)
            break MISSING_BLOCK_LABEL_95;
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
                finisheddispatcher = JVM INSTR new #10  <Class IntentSender$FinishedDispatcher>;
                finisheddispatcher.FinishedDispatcher(this, onfinished, handler);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                throw new SendIntentException();
            }
            onfinished = finisheddispatcher;
        } else
        {
            onfinished = null;
        }
        if(iactivitymanager.sendIntentSender(iintentsender, ibinder, i, intent, context, onfinished, s, null) < 0)
        {
            context = JVM INSTR new #16  <Class IntentSender$SendIntentException>;
            context.SendIntentException();
            throw context;
        }
        break MISSING_BLOCK_LABEL_106;
        context = null;
          goto _L1
    }

    public String toString()
    {
        IBinder ibinder = null;
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("IntentSender{");
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
    }

    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public IntentSender createFromParcel(Parcel parcel)
        {
            Object obj = null;
            IBinder ibinder = parcel.readStrongBinder();
            parcel = obj;
            if(ibinder != null)
                parcel = new IntentSender(ibinder);
            return parcel;
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public IntentSender[] newArray(int i)
        {
            return new IntentSender[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    private final IIntentSender mTarget;
    IBinder mWhitelistToken;

}
