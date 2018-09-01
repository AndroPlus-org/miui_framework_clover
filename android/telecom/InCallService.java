// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.telecom;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.*;
import android.view.Surface;
import com.android.internal.os.SomeArgs;
import com.android.internal.telecom.IInCallAdapter;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.telecom:
//            Phone, AudioState, CallAudioState, Call, 
//            ParcelableCall, VideoProfile, InCallAdapter

public abstract class InCallService extends Service
{
    private final class InCallServiceBinder extends com.android.internal.telecom.IInCallService.Stub
    {

        public void addCall(ParcelableCall parcelablecall)
        {
            InCallService._2D_get0(InCallService.this).obtainMessage(2, parcelablecall).sendToTarget();
        }

        public void bringToForeground(boolean flag)
        {
            Handler handler = InCallService._2D_get0(InCallService.this);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            handler.obtainMessage(6, i, 0).sendToTarget();
        }

        public void onCallAudioStateChanged(CallAudioState callaudiostate)
        {
            InCallService._2D_get0(InCallService.this).obtainMessage(5, callaudiostate).sendToTarget();
        }

        public void onCanAddCallChanged(boolean flag)
        {
            Handler handler = InCallService._2D_get0(InCallService.this);
            int i;
            if(flag)
                i = 1;
            else
                i = 0;
            handler.obtainMessage(7, i, 0).sendToTarget();
        }

        public void onConnectionEvent(String s, String s1, Bundle bundle)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = s;
            someargs.arg2 = s1;
            someargs.arg3 = bundle;
            InCallService._2D_get0(InCallService.this).obtainMessage(9, someargs).sendToTarget();
        }

        public void onRttInitiationFailure(String s, int i)
        {
            InCallService._2D_get0(InCallService.this).obtainMessage(11, i, 0, s).sendToTarget();
        }

        public void onRttUpgradeRequest(String s, int i)
        {
            InCallService._2D_get0(InCallService.this).obtainMessage(10, i, 0, s).sendToTarget();
        }

        public void setInCallAdapter(IInCallAdapter iincalladapter)
        {
            InCallService._2D_get0(InCallService.this).obtainMessage(1, iincalladapter).sendToTarget();
        }

        public void setPostDial(String s, String s1)
        {
        }

        public void setPostDialWait(String s, String s1)
        {
            SomeArgs someargs = SomeArgs.obtain();
            someargs.arg1 = s;
            someargs.arg2 = s1;
            InCallService._2D_get0(InCallService.this).obtainMessage(4, someargs).sendToTarget();
        }

        public void silenceRinger()
        {
            InCallService._2D_get0(InCallService.this).obtainMessage(8).sendToTarget();
        }

        public void updateCall(ParcelableCall parcelablecall)
        {
            InCallService._2D_get0(InCallService.this).obtainMessage(3, parcelablecall).sendToTarget();
        }

        final InCallService this$0;

        private InCallServiceBinder()
        {
            this$0 = InCallService.this;
            super();
        }

        InCallServiceBinder(InCallServiceBinder incallservicebinder)
        {
            this();
        }
    }

    public static abstract class VideoCall
    {

        public abstract void destroy();

        public abstract void registerCallback(Callback callback);

        public abstract void registerCallback(Callback callback, Handler handler);

        public abstract void requestCallDataUsage();

        public abstract void requestCameraCapabilities();

        public abstract void sendSessionModifyRequest(VideoProfile videoprofile);

        public abstract void sendSessionModifyResponse(VideoProfile videoprofile);

        public abstract void setCamera(String s);

        public abstract void setDeviceOrientation(int i);

        public abstract void setDisplaySurface(Surface surface);

        public abstract void setPauseImage(Uri uri);

        public abstract void setPreviewSurface(Surface surface);

        public abstract void setZoom(float f);

        public abstract void unregisterCallback(Callback callback);

        public VideoCall()
        {
        }
    }

    public static abstract class VideoCall.Callback
    {

        public abstract void onCallDataUsageChanged(long l);

        public abstract void onCallSessionEvent(int i);

        public abstract void onCameraCapabilitiesChanged(VideoProfile.CameraCapabilities cameracapabilities);

        public abstract void onPeerDimensionsChanged(int i, int j);

        public abstract void onSessionModifyRequestReceived(VideoProfile videoprofile);

        public abstract void onSessionModifyResponseReceived(int i, VideoProfile videoprofile, VideoProfile videoprofile1);

        public abstract void onVideoQualityChanged(int i);

        public VideoCall.Callback()
        {
        }
    }


    static Handler _2D_get0(InCallService incallservice)
    {
        return incallservice.mHandler;
    }

    static Phone _2D_get1(InCallService incallservice)
    {
        return incallservice.mPhone;
    }

    static Phone.Listener _2D_get2(InCallService incallservice)
    {
        return incallservice.mPhoneListener;
    }

    static Phone _2D_set0(InCallService incallservice, Phone phone)
    {
        incallservice.mPhone = phone;
        return phone;
    }

    public InCallService()
    {
        mPhoneListener = new Phone.Listener() {

            public void onAudioStateChanged(Phone phone, AudioState audiostate)
            {
                InCallService.this.onAudioStateChanged(audiostate);
            }

            public void onBringToForeground(Phone phone, boolean flag)
            {
                InCallService.this.onBringToForeground(flag);
            }

            public void onCallAdded(Phone phone, Call call)
            {
                InCallService.this.onCallAdded(call);
            }

            public void onCallAudioStateChanged(Phone phone, CallAudioState callaudiostate)
            {
                InCallService.this.onCallAudioStateChanged(callaudiostate);
            }

            public void onCallRemoved(Phone phone, Call call)
            {
                InCallService.this.onCallRemoved(call);
            }

            public void onCanAddCallChanged(Phone phone, boolean flag)
            {
                InCallService.this.onCanAddCallChanged(flag);
            }

            public void onSilenceRinger(Phone phone)
            {
                InCallService.this.onSilenceRinger();
            }

            final InCallService this$0;

            
            {
                this$0 = InCallService.this;
                super();
            }
        }
;
    }

    public final boolean canAddCall()
    {
        boolean flag;
        if(mPhone == null)
            flag = false;
        else
            flag = mPhone.canAddCall();
        return flag;
    }

    public final AudioState getAudioState()
    {
        AudioState audiostate = null;
        if(mPhone != null)
            audiostate = mPhone.getAudioState();
        return audiostate;
    }

    public final CallAudioState getCallAudioState()
    {
        CallAudioState callaudiostate = null;
        if(mPhone != null)
            callaudiostate = mPhone.getCallAudioState();
        return callaudiostate;
    }

    public final List getCalls()
    {
        List list;
        if(mPhone == null)
            list = Collections.emptyList();
        else
            list = mPhone.getCalls();
        return list;
    }

    public Phone getPhone()
    {
        return mPhone;
    }

    public void onAudioStateChanged(AudioState audiostate)
    {
    }

    public IBinder onBind(Intent intent)
    {
        return new InCallServiceBinder(null);
    }

    public void onBringToForeground(boolean flag)
    {
    }

    public void onCallAdded(Call call)
    {
    }

    public void onCallAudioStateChanged(CallAudioState callaudiostate)
    {
    }

    public void onCallRemoved(Call call)
    {
    }

    public void onCanAddCallChanged(boolean flag)
    {
    }

    public void onConnectionEvent(Call call, String s, Bundle bundle)
    {
    }

    public void onPhoneCreated(Phone phone)
    {
    }

    public void onPhoneDestroyed(Phone phone)
    {
    }

    public void onSilenceRinger()
    {
    }

    public boolean onUnbind(Intent intent)
    {
        if(mPhone != null)
        {
            intent = mPhone;
            mPhone = null;
            intent.destroy();
            intent.removeListener(mPhoneListener);
            onPhoneDestroyed(intent);
        }
        return false;
    }

    public final void setAudioRoute(int i)
    {
        if(mPhone != null)
            mPhone.setAudioRoute(i);
    }

    public final void setMuted(boolean flag)
    {
        if(mPhone != null)
            mPhone.setMuted(flag);
    }

    private static final int MSG_ADD_CALL = 2;
    private static final int MSG_BRING_TO_FOREGROUND = 6;
    private static final int MSG_ON_CALL_AUDIO_STATE_CHANGED = 5;
    private static final int MSG_ON_CAN_ADD_CALL_CHANGED = 7;
    private static final int MSG_ON_CONNECTION_EVENT = 9;
    private static final int MSG_ON_RTT_INITIATION_FAILURE = 11;
    private static final int MSG_ON_RTT_UPGRADE_REQUEST = 10;
    private static final int MSG_SET_IN_CALL_ADAPTER = 1;
    private static final int MSG_SET_POST_DIAL_WAIT = 4;
    private static final int MSG_SILENCE_RINGER = 8;
    private static final int MSG_UPDATE_CALL = 3;
    public static final String SERVICE_INTERFACE = "android.telecom.InCallService";
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {

        public void handleMessage(Message message)
        {
            boolean flag;
            boolean flag1;
            flag = true;
            flag1 = true;
            if(InCallService._2D_get1(InCallService.this) == null && message.what != 1)
                return;
            message.what;
            JVM INSTR tableswitch 1 11: default 84
        //                       1 85
        //                       2 175
        //                       3 195
        //                       4 215
        //                       5 271
        //                       6 291
        //                       7 322
        //                       8 355
        //                       9 368
        //                       10 435
        //                       11 467;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12
_L1:
            return;
_L2:
            String s = getApplicationContext().getOpPackageName();
            InCallService._2D_set0(InCallService.this, new Phone(new InCallAdapter((IInCallAdapter)message.obj), s, getApplicationContext().getApplicationInfo().targetSdkVersion));
            InCallService._2D_get1(InCallService.this).addListener(InCallService._2D_get2(InCallService.this));
            onPhoneCreated(InCallService._2D_get1(InCallService.this));
            continue; /* Loop/switch isn't completed */
_L3:
            InCallService._2D_get1(InCallService.this).internalAddCall((ParcelableCall)message.obj);
            continue; /* Loop/switch isn't completed */
_L4:
            InCallService._2D_get1(InCallService.this).internalUpdateCall((ParcelableCall)message.obj);
            continue; /* Loop/switch isn't completed */
_L5:
            message = (SomeArgs)message.obj;
            String s1 = (String)((SomeArgs) (message)).arg1;
            String s5 = (String)((SomeArgs) (message)).arg2;
            InCallService._2D_get1(InCallService.this).internalSetPostDialWait(s1, s5);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            Exception exception;
            exception;
            message.recycle();
            throw exception;
_L6:
            InCallService._2D_get1(InCallService.this).internalCallAudioStateChanged((CallAudioState)message.obj);
            continue; /* Loop/switch isn't completed */
_L7:
            Phone phone = InCallService._2D_get1(InCallService.this);
            if(message.arg1 != 1)
                flag1 = false;
            phone.internalBringToForeground(flag1);
            continue; /* Loop/switch isn't completed */
_L8:
            Phone phone1 = InCallService._2D_get1(InCallService.this);
            boolean flag2;
            if(message.arg1 == 1)
                flag2 = flag;
            else
                flag2 = false;
            phone1.internalSetCanAddCall(flag2);
            continue; /* Loop/switch isn't completed */
_L9:
            InCallService._2D_get1(InCallService.this).internalSilenceRinger();
            continue; /* Loop/switch isn't completed */
_L10:
            message = (SomeArgs)message.obj;
            String s2 = (String)((SomeArgs) (message)).arg1;
            String s6 = (String)((SomeArgs) (message)).arg2;
            Bundle bundle = (Bundle)((SomeArgs) (message)).arg3;
            InCallService._2D_get1(InCallService.this).internalOnConnectionEvent(s2, s6, bundle);
            message.recycle();
            continue; /* Loop/switch isn't completed */
            s2;
            message.recycle();
            throw s2;
_L11:
            String s3 = (String)message.obj;
            int i = message.arg1;
            InCallService._2D_get1(InCallService.this).internalOnRttUpgradeRequest(s3, i);
            continue; /* Loop/switch isn't completed */
_L12:
            String s4 = (String)message.obj;
            int j = message.arg1;
            InCallService._2D_get1(InCallService.this).internalOnRttInitiationFailure(s4, j);
            if(true) goto _L1; else goto _L13
_L13:
        }

        final InCallService this$0;

            
            {
                this$0 = InCallService.this;
                super(looper);
            }
    }
;
    private Phone mPhone;
    private Phone.Listener mPhoneListener;
}
