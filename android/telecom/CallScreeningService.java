// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import com.android.internal.os.SomeArgs;
import com.android.internal.telecom.ICallScreeningAdapter;

// Referenced classes of package android.telecom:
//            Log, ParcelableCall

public abstract class CallScreeningService extends Service
{
    public static class CallResponse
    {

        public boolean getDisallowCall()
        {
            return mShouldDisallowCall;
        }

        public boolean getRejectCall()
        {
            return mShouldRejectCall;
        }

        public boolean getSkipCallLog()
        {
            return mShouldSkipCallLog;
        }

        public boolean getSkipNotification()
        {
            return mShouldSkipNotification;
        }

        private final boolean mShouldDisallowCall;
        private final boolean mShouldRejectCall;
        private final boolean mShouldSkipCallLog;
        private final boolean mShouldSkipNotification;

        private CallResponse(boolean flag, boolean flag1, boolean flag2, boolean flag3)
        {
            if(!flag && (flag1 || flag2 || flag3))
            {
                throw new IllegalStateException("Invalid response state for allowed call.");
            } else
            {
                mShouldDisallowCall = flag;
                mShouldRejectCall = flag1;
                mShouldSkipCallLog = flag2;
                mShouldSkipNotification = flag3;
                return;
            }
        }

        CallResponse(boolean flag, boolean flag1, boolean flag2, boolean flag3, CallResponse callresponse)
        {
            this(flag, flag1, flag2, flag3);
        }
    }

    public static class CallResponse.Builder
    {

        public CallResponse build()
        {
            return new CallResponse(mShouldDisallowCall, mShouldRejectCall, mShouldSkipCallLog, mShouldSkipNotification, null);
        }

        public CallResponse.Builder setDisallowCall(boolean flag)
        {
            mShouldDisallowCall = flag;
            return this;
        }

        public CallResponse.Builder setRejectCall(boolean flag)
        {
            mShouldRejectCall = flag;
            return this;
        }

        public CallResponse.Builder setSkipCallLog(boolean flag)
        {
            mShouldSkipCallLog = flag;
            return this;
        }

        public CallResponse.Builder setSkipNotification(boolean flag)
        {
            mShouldSkipNotification = flag;
            return this;
        }

        private boolean mShouldDisallowCall;
        private boolean mShouldRejectCall;
        private boolean mShouldSkipCallLog;
        private boolean mShouldSkipNotification;

        public CallResponse.Builder()
        {
        }
    }

    private final class CallScreeningBinder extends com.android.internal.telecom.ICallScreeningService.Stub
    {

        public void screenCall(ICallScreeningAdapter icallscreeningadapter, ParcelableCall parcelablecall)
        {
            Log.v(this, "screenCall", new Object[0]);
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = icallscreeningadapter;
            someargs.arg2 = parcelablecall;
            CallScreeningService._2D_get0(CallScreeningService.this).obtainMessage(1, someargs).sendToTarget();
        }

        final CallScreeningService this$0;

        private CallScreeningBinder()
        {
            this$0 = CallScreeningService.this;
            super();
        }

        CallScreeningBinder(CallScreeningBinder callscreeningbinder)
        {
            this();
        }
    }


    static Handler _2D_get0(CallScreeningService callscreeningservice)
    {
        return callscreeningservice.mHandler;
    }

    static ICallScreeningAdapter _2D_set0(CallScreeningService callscreeningservice, ICallScreeningAdapter icallscreeningadapter)
    {
        callscreeningservice.mCallScreeningAdapter = icallscreeningadapter;
        return icallscreeningadapter;
    }

    public CallScreeningService()
    {
    }

    public IBinder onBind(Intent intent)
    {
        Log.v(this, "onBind", new Object[0]);
        return new CallScreeningBinder(null);
    }

    public abstract void onScreenCall(Call.Details details);

    public boolean onUnbind(Intent intent)
    {
        Log.v(this, "onUnbind", new Object[0]);
        return false;
    }

    public final void respondToCall(Call.Details details, CallResponse callresponse)
    {
        if(!callresponse.getDisallowCall())
            break MISSING_BLOCK_LABEL_37;
        mCallScreeningAdapter.disallowCall(details.getTelecomCallId(), callresponse.getRejectCall(), callresponse.getSkipCallLog() ^ true, callresponse.getSkipNotification() ^ true);
_L1:
        return;
        try
        {
            mCallScreeningAdapter.allowCall(details.getTelecomCallId());
        }
        // Misplaced declaration of an exception variable
        catch(Call.Details details) { }
          goto _L1
    }

    private static final int MSG_SCREEN_CALL = 1;
    public static final String SERVICE_INTERFACE = "android.telecom.CallScreeningService";
    private ICallScreeningAdapter mCallScreeningAdapter;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 1: default 24
        //                       1 25;
               goto _L1 _L2
_L1:
            return;
_L2:
            message = (SomeArgs)message.obj;
            CallScreeningService._2D_set0(CallScreeningService.this, (ICallScreeningAdapter)((SomeArgs) (message)).arg1);
            onScreenCall(Call.Details.createFromParcelableCall((ParcelableCall)((SomeArgs) (message)).arg2));
            message.recycle();
            if(true) goto _L1; else goto _L3
_L3:
            Exception exception;
            exception;
            message.recycle();
            throw exception;
        }

        final CallScreeningService this$0;

            
            {
                this$0 = CallScreeningService.this;
                super(looper);
            }
    }
;
}
