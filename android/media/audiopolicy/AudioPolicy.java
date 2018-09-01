// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.media.audiopolicy;

import android.content.Context;
import android.media.*;
import android.os.*;
import android.util.Log;
import android.util.Slog;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package android.media.audiopolicy:
//            AudioMix, AudioPolicyConfig, IAudioPolicyCallback

public class AudioPolicy
{
    public static abstract class AudioPolicyFocusListener
    {

        public void onAudioFocusAbandon(AudioFocusInfo audiofocusinfo)
        {
        }

        public void onAudioFocusGrant(AudioFocusInfo audiofocusinfo, int i)
        {
        }

        public void onAudioFocusLoss(AudioFocusInfo audiofocusinfo, boolean flag)
        {
        }

        public void onAudioFocusRequest(AudioFocusInfo audiofocusinfo, int i)
        {
        }

        public AudioPolicyFocusListener()
        {
        }
    }

    public static abstract class AudioPolicyStatusListener
    {

        public void onMixStateUpdate(AudioMix audiomix)
        {
        }

        public void onStatusChange()
        {
        }

        public AudioPolicyStatusListener()
        {
        }
    }

    public static class Builder
    {

        public Builder addMix(AudioMix audiomix)
            throws IllegalArgumentException
        {
            if(audiomix == null)
            {
                throw new IllegalArgumentException("Illegal null AudioMix argument");
            } else
            {
                mMixes.add(audiomix);
                return this;
            }
        }

        public AudioPolicy build()
        {
            if(mStatusListener != null)
            {
                for(Iterator iterator = mMixes.iterator(); iterator.hasNext();)
                {
                    AudioMix audiomix = (AudioMix)iterator.next();
                    audiomix.mCallbackFlags = audiomix.mCallbackFlags | 1;
                }

            }
            if(mIsFocusPolicy && mFocusListener == null)
                throw new IllegalStateException("Cannot be a focus policy without an AudioPolicyFocusListener");
            else
                return new AudioPolicy(new AudioPolicyConfig(mMixes), mContext, mLooper, mFocusListener, mStatusListener, mIsFocusPolicy, null);
        }

        public void setAudioPolicyFocusListener(AudioPolicyFocusListener audiopolicyfocuslistener)
        {
            mFocusListener = audiopolicyfocuslistener;
        }

        public void setAudioPolicyStatusListener(AudioPolicyStatusListener audiopolicystatuslistener)
        {
            mStatusListener = audiopolicystatuslistener;
        }

        public Builder setIsAudioFocusPolicy(boolean flag)
        {
            mIsFocusPolicy = flag;
            return this;
        }

        public Builder setLooper(Looper looper)
            throws IllegalArgumentException
        {
            if(looper == null)
            {
                throw new IllegalArgumentException("Illegal null Looper argument");
            } else
            {
                mLooper = looper;
                return this;
            }
        }

        private Context mContext;
        private AudioPolicyFocusListener mFocusListener;
        private boolean mIsFocusPolicy;
        private Looper mLooper;
        private ArrayList mMixes;
        private AudioPolicyStatusListener mStatusListener;

        public Builder(Context context)
        {
            mIsFocusPolicy = false;
            mMixes = new ArrayList();
            mContext = context;
        }
    }

    private class EventHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            boolean flag = false;
            message.what;
            JVM INSTR tableswitch 0 5: default 44
        //                       0 73
        //                       1 83
        //                       2 117
        //                       3 163
        //                       4 193
        //                       5 238;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
            Log.e("AudioPolicy", (new StringBuilder()).append("Unknown event ").append(message.what).toString());
_L9:
            return;
_L2:
            AudioPolicy._2D_wrap0(AudioPolicy.this);
            continue; /* Loop/switch isn't completed */
_L3:
            if(AudioPolicy._2D_get1(AudioPolicy.this) != null)
                AudioPolicy._2D_get1(AudioPolicy.this).onAudioFocusGrant((AudioFocusInfo)message.obj, message.arg1);
            continue; /* Loop/switch isn't completed */
_L4:
            if(AudioPolicy._2D_get1(AudioPolicy.this) != null)
            {
                AudioPolicyFocusListener audiopolicyfocuslistener = AudioPolicy._2D_get1(AudioPolicy.this);
                AudioFocusInfo audiofocusinfo = (AudioFocusInfo)message.obj;
                if(message.arg1 != 0)
                    flag = true;
                audiopolicyfocuslistener.onAudioFocusLoss(audiofocusinfo, flag);
            }
            continue; /* Loop/switch isn't completed */
_L5:
            if(AudioPolicy._2D_get2(AudioPolicy.this) != null)
                AudioPolicy._2D_get2(AudioPolicy.this).onMixStateUpdate((AudioMix)message.obj);
            continue; /* Loop/switch isn't completed */
_L6:
            if(AudioPolicy._2D_get1(AudioPolicy.this) != null)
                AudioPolicy._2D_get1(AudioPolicy.this).onAudioFocusRequest((AudioFocusInfo)message.obj, message.arg1);
            else
                Log.e("AudioPolicy", "Invalid null focus listener for focus request event");
            continue; /* Loop/switch isn't completed */
_L7:
            if(AudioPolicy._2D_get1(AudioPolicy.this) != null)
                AudioPolicy._2D_get1(AudioPolicy.this).onAudioFocusAbandon((AudioFocusInfo)message.obj);
            else
                Log.e("AudioPolicy", "Invalid null focus listener for focus abandon event");
            if(true) goto _L9; else goto _L8
_L8:
        }

        final AudioPolicy this$0;

        public EventHandler(AudioPolicy audiopolicy1, Looper looper)
        {
            this$0 = AudioPolicy.this;
            super(looper);
        }
    }


    static AudioPolicyConfig _2D_get0(AudioPolicy audiopolicy)
    {
        return audiopolicy.mConfig;
    }

    static AudioPolicyFocusListener _2D_get1(AudioPolicy audiopolicy)
    {
        return audiopolicy.mFocusListener;
    }

    static AudioPolicyStatusListener _2D_get2(AudioPolicy audiopolicy)
    {
        return audiopolicy.mStatusListener;
    }

    static void _2D_wrap0(AudioPolicy audiopolicy)
    {
        audiopolicy.onPolicyStatusChange();
    }

    static void _2D_wrap1(AudioPolicy audiopolicy, int i, Object obj, int j)
    {
        audiopolicy.sendMsg(i, obj, j);
    }

    private AudioPolicy(AudioPolicyConfig audiopolicyconfig, Context context, Looper looper, AudioPolicyFocusListener audiopolicyfocuslistener, AudioPolicyStatusListener audiopolicystatuslistener, boolean flag)
    {
        mLock = new Object();
        mPolicyCb = new IAudioPolicyCallback.Stub() {

            public void notifyAudioFocusAbandon(AudioFocusInfo audiofocusinfo)
            {
                AudioPolicy._2D_wrap1(AudioPolicy.this, 5, audiofocusinfo, 0);
            }

            public void notifyAudioFocusGrant(AudioFocusInfo audiofocusinfo, int i)
            {
                AudioPolicy._2D_wrap1(AudioPolicy.this, 1, audiofocusinfo, i);
            }

            public void notifyAudioFocusLoss(AudioFocusInfo audiofocusinfo, boolean flag1)
            {
                AudioPolicy audiopolicy = AudioPolicy.this;
                int i;
                if(flag1)
                    i = 1;
                else
                    i = 0;
                AudioPolicy._2D_wrap1(audiopolicy, 2, audiofocusinfo, i);
            }

            public void notifyAudioFocusRequest(AudioFocusInfo audiofocusinfo, int i)
            {
                AudioPolicy._2D_wrap1(AudioPolicy.this, 4, audiofocusinfo, i);
            }

            public void notifyMixStateUpdate(String s, int i)
            {
                Iterator iterator = AudioPolicy._2D_get0(AudioPolicy.this).getMixes().iterator();
                do
                {
                    if(!iterator.hasNext())
                        break;
                    AudioMix audiomix = (AudioMix)iterator.next();
                    if(audiomix.getRegistration().equals(s))
                    {
                        audiomix.mMixState = i;
                        AudioPolicy._2D_wrap1(AudioPolicy.this, 3, audiomix, 0);
                    }
                } while(true);
            }

            final AudioPolicy this$0;

            
            {
                this$0 = AudioPolicy.this;
                super();
            }
        }
;
        mConfig = audiopolicyconfig;
        mStatus = 1;
        mContext = context;
        audiopolicyconfig = looper;
        if(looper == null)
            audiopolicyconfig = Looper.getMainLooper();
        if(audiopolicyconfig != null)
        {
            mEventHandler = new EventHandler(this, audiopolicyconfig);
        } else
        {
            mEventHandler = null;
            Log.e("AudioPolicy", "No event handler due to looper without a thread");
        }
        mFocusListener = audiopolicyfocuslistener;
        mStatusListener = audiopolicystatuslistener;
        mIsFocusPolicy = flag;
    }

    AudioPolicy(AudioPolicyConfig audiopolicyconfig, Context context, Looper looper, AudioPolicyFocusListener audiopolicyfocuslistener, AudioPolicyStatusListener audiopolicystatuslistener, boolean flag, AudioPolicy audiopolicy)
    {
        this(audiopolicyconfig, context, looper, audiopolicyfocuslistener, audiopolicystatuslistener, flag);
    }

    private static String addressForTag(AudioMix audiomix)
    {
        return (new StringBuilder()).append("addr=").append(audiomix.getRegistration()).toString();
    }

    private void checkMixReadyToUse(AudioMix audiomix, boolean flag)
        throws IllegalArgumentException
    {
        if(audiomix == null)
        {
            if(flag)
                audiomix = "Invalid null AudioMix for AudioTrack creation";
            else
                audiomix = "Invalid null AudioMix for AudioRecord creation";
            throw new IllegalArgumentException(audiomix);
        }
        if(!mConfig.mMixes.contains(audiomix))
            throw new IllegalArgumentException("Invalid mix: not part of this policy");
        if((audiomix.getRouteFlags() & 2) != 2)
            throw new IllegalArgumentException("Invalid AudioMix: not defined for loop back");
        if(flag && audiomix.getMixType() != 1)
            throw new IllegalArgumentException("Invalid AudioMix: not defined for being a recording source");
        if(!flag && audiomix.getMixType() != 0)
            throw new IllegalArgumentException("Invalid AudioMix: not defined for capturing playback");
        else
            return;
    }

    private static IAudioService getService()
    {
        if(sService != null)
        {
            return sService;
        } else
        {
            sService = android.media.IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
            return sService;
        }
    }

    private void onPolicyStatusChange()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        AudioPolicyStatusListener audiopolicystatuslistener = mStatusListener;
        if(audiopolicystatuslistener != null)
            break MISSING_BLOCK_LABEL_19;
        obj;
        JVM INSTR monitorexit ;
        return;
        audiopolicystatuslistener = mStatusListener;
        obj;
        JVM INSTR monitorexit ;
        audiopolicystatuslistener.onStatusChange();
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private boolean policyReadyToUse()
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        if(mStatus == 2)
            break MISSING_BLOCK_LABEL_27;
        Log.e("AudioPolicy", "Cannot use unregistered AudioPolicy");
        obj;
        JVM INSTR monitorexit ;
        return false;
        if(mContext != null)
            break MISSING_BLOCK_LABEL_46;
        Log.e("AudioPolicy", "Cannot use AudioPolicy without context");
        obj;
        JVM INSTR monitorexit ;
        return false;
        if(mRegistrationId != null)
            break MISSING_BLOCK_LABEL_65;
        Log.e("AudioPolicy", "Cannot use unregistered AudioPolicy");
        obj;
        JVM INSTR monitorexit ;
        return false;
        obj;
        JVM INSTR monitorexit ;
        Exception exception;
        if(mContext.checkCallingOrSelfPermission("android.permission.MODIFY_AUDIO_ROUTING") != 0)
        {
            Slog.w("AudioPolicy", (new StringBuilder()).append("Cannot use AudioPolicy for pid ").append(Binder.getCallingPid()).append(" / uid ").append(Binder.getCallingUid()).append(", needs MODIFY_AUDIO_ROUTING").toString());
            return false;
        } else
        {
            return true;
        }
        exception;
        throw exception;
    }

    private void sendMsg(int i)
    {
        if(mEventHandler != null)
            mEventHandler.sendEmptyMessage(i);
    }

    private void sendMsg(int i, Object obj, int j)
    {
        if(mEventHandler != null)
            mEventHandler.sendMessage(mEventHandler.obtainMessage(i, j, 0, obj));
    }

    public IAudioPolicyCallback cb()
    {
        return mPolicyCb;
    }

    public AudioRecord createAudioRecordSink(AudioMix audiomix)
        throws IllegalArgumentException
    {
        if(!policyReadyToUse())
        {
            Log.e("AudioPolicy", "Cannot create AudioRecord sink for AudioMix");
            return null;
        } else
        {
            checkMixReadyToUse(audiomix, false);
            AudioFormat audioformat = (new android.media.AudioFormat.Builder(audiomix.getFormat())).setChannelMask(AudioFormat.inChannelMaskFromOutChannelMask(audiomix.getFormat().getChannelMask())).build();
            return new AudioRecord((new android.media.AudioAttributes.Builder()).setInternalCapturePreset(8).addTag(addressForTag(audiomix)).build(), audioformat, AudioRecord.getMinBufferSize(audiomix.getFormat().getSampleRate(), 12, audiomix.getFormat().getEncoding()), 0);
        }
    }

    public AudioTrack createAudioTrackSource(AudioMix audiomix)
        throws IllegalArgumentException
    {
        if(!policyReadyToUse())
        {
            Log.e("AudioPolicy", "Cannot create AudioTrack source for AudioMix");
            return null;
        } else
        {
            checkMixReadyToUse(audiomix, true);
            return new AudioTrack((new android.media.AudioAttributes.Builder()).setUsage(15).addTag(addressForTag(audiomix)).build(), audiomix.getFormat(), AudioTrack.getMinBufferSize(audiomix.getFormat().getSampleRate(), audiomix.getFormat().getChannelMask(), audiomix.getFormat().getEncoding()), 1, 0);
        }
    }

    public AudioPolicyConfig getConfig()
    {
        return mConfig;
    }

    public int getFocusDuckingBehavior()
    {
        return mConfig.mDuckingPolicy;
    }

    public int getStatus()
    {
        return mStatus;
    }

    public boolean hasFocusListener()
    {
        boolean flag;
        if(mFocusListener != null)
            flag = true;
        else
            flag = false;
        return flag;
    }

    public boolean isFocusPolicy()
    {
        return mIsFocusPolicy;
    }

    public int setFocusDuckingBehavior(int i)
        throws IllegalArgumentException, IllegalStateException
    {
label0:
        {
            if(i != 0 && i != 1)
                throw new IllegalArgumentException((new StringBuilder()).append("Invalid ducking behavior ").append(i).toString());
            synchronized(mLock)
            {
                if(mStatus != 2)
                {
                    IllegalStateException illegalstateexception = JVM INSTR new #345 <Class IllegalStateException>;
                    illegalstateexception.IllegalStateException("Cannot change ducking behavior for unregistered policy");
                    throw illegalstateexception;
                }
                break label0;
            }
        }
        if(i != 1)
            break MISSING_BLOCK_LABEL_95;
        if(mFocusListener == null)
        {
            IllegalStateException illegalstateexception1 = JVM INSTR new #345 <Class IllegalStateException>;
            illegalstateexception1.IllegalStateException("Cannot handle ducking without an audio focus listener");
            throw illegalstateexception1;
        }
        IAudioService iaudioservice = getService();
        int j = iaudioservice.setFocusPropertiesForPolicy(i, cb());
        if(j != 0)
            break MISSING_BLOCK_LABEL_125;
        mConfig.mDuckingPolicy = i;
        obj;
        JVM INSTR monitorexit ;
        return j;
        RemoteException remoteexception;
        remoteexception;
        Log.e("AudioPolicy", "Dead object in setFocusPropertiesForPolicy for behavior", remoteexception);
        obj;
        JVM INSTR monitorexit ;
        return -1;
    }

    public void setRegistration(String s)
    {
        Object obj = mLock;
        obj;
        JVM INSTR monitorenter ;
        mRegistrationId = s;
        mConfig.setRegistration(s);
        if(s == null)
            break MISSING_BLOCK_LABEL_37;
        mStatus = 2;
_L1:
        obj;
        JVM INSTR monitorexit ;
        sendMsg(0);
        return;
        mStatus = 1;
          goto _L1
        s;
        throw s;
    }

    public String toLogFriendlyString()
    {
        String s = new String("android.media.audiopolicy.AudioPolicy:\n");
        return (new StringBuilder()).append(s).append("config=").append(mConfig.toLogFriendlyString()).toString();
    }

    private static final boolean DEBUG = false;
    public static final int FOCUS_POLICY_DUCKING_DEFAULT = 0;
    public static final int FOCUS_POLICY_DUCKING_IN_APP = 0;
    public static final int FOCUS_POLICY_DUCKING_IN_POLICY = 1;
    private static final int MSG_FOCUS_ABANDON = 5;
    private static final int MSG_FOCUS_GRANT = 1;
    private static final int MSG_FOCUS_LOSS = 2;
    private static final int MSG_FOCUS_REQUEST = 4;
    private static final int MSG_MIX_STATE_UPDATE = 3;
    private static final int MSG_POLICY_STATUS_CHANGE = 0;
    public static final int POLICY_STATUS_REGISTERED = 2;
    public static final int POLICY_STATUS_UNREGISTERED = 1;
    private static final String TAG = "AudioPolicy";
    private static IAudioService sService;
    private AudioPolicyConfig mConfig;
    private Context mContext;
    private final EventHandler mEventHandler;
    private AudioPolicyFocusListener mFocusListener;
    private boolean mIsFocusPolicy;
    private final Object mLock;
    private final IAudioPolicyCallback mPolicyCb;
    private String mRegistrationId;
    private int mStatus;
    private AudioPolicyStatusListener mStatusListener;
}
